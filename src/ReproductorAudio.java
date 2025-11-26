import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

public class ReproductorAudio extends JFrame {

    private Clip clip;
    private JLabel etiquetaEstado;

    private final String RUTA_ARCHIVO = "Walking On A Dream.wav"; 

    public ReproductorAudio() {

        setTitle("Control de Música");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        cargarAudio(RUTA_ARCHIVO);


        etiquetaEstado = new JLabel("Estado: Detenido", SwingConstants.CENTER);
        etiquetaEstado.setFont(new Font("Arial", Font.PLAIN, 16));
        etiquetaEstado.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(etiquetaEstado, BorderLayout.NORTH);


        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton btnReproducir = new JButton("Reproducir");
        JButton btnPausar = new JButton("Pausar");
        JButton btnReanudar = new JButton("Reanudar");

        btnReproducir.addActionListener(e -> {
            if (clip != null) {
                clip.stop(); 
                clip.setMicrosecondPosition(0); 
                clip.start(); 
                etiquetaEstado.setText("Estado: Reproduciendo desde el inicio");
            }
        });

        btnPausar.addActionListener(e -> {
            if (clip != null && clip.isRunning()) {
                clip.stop(); 
                etiquetaEstado.setText("Estado: Pausado");
            }
        });


        btnReanudar.addActionListener(e -> {
            if (clip != null && !clip.isRunning()) {

                clip.start(); 
                etiquetaEstado.setText("Estado: Reanudado");
            }
        });


        panelBotones.add(btnReproducir);
        panelBotones.add(btnPausar);
        panelBotones.add(btnReanudar);

        add(panelBotones, BorderLayout.CENTER);
    }


    private void cargarAudio(String ruta) {
        try {
            File archivo = new File(ruta);
            if (archivo.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(archivo);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
            } else {
                JOptionPane.showMessageDialog(this, "No se encuentra 'musica.wav'", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar el audio: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ReproductorAudio().setVisible(true);
        });
    }
}