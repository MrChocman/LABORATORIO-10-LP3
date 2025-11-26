import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Efectosonido extends JFrame {

    public Efectosonido() {

        setTitle("Reproductor de Efectos de Sonido");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        

        setLayout(new GridLayout(1, 3, 20, 20)); 


        agregarBoton("Aplausos", "aplausos.wav");
        agregarBoton("Campana", "campana.wav");
        agregarBoton("Explosión", "explosion.wav");
    }

    private void agregarBoton(String texto, String archivoSonido) {
        JButton boton = new JButton(texto);
        

        boton.setFont(new Font("Arial", Font.BOLD, 18));
        boton.setFocusPainted(false);


        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reproducirSonido(archivoSonido);
            }
        });

        add(boton);
    }


    private void reproducirSonido(String nombreArchivo) {
        try {

            File archivoDeSonido = new File(nombreArchivo);

            if (archivoDeSonido.exists()) {

                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivoDeSonido);
                

                Clip clip = AudioSystem.getClip();
                

                clip.open(audioInputStream);
                clip.start();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el archivo: " + nombreArchivo, 
                        "Error de Archivo", JOptionPane.ERROR_MESSAGE);
            }

        } catch (UnsupportedAudioFileException e) {
            JOptionPane.showMessageDialog(this, "Formato de audio no soportado. Usa WAV.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Efectosonido().setVisible(true);
        });
    }
}