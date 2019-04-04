package flappybird;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author josearielpereyra
 */
public class FlappyBird {
  public static void main(String[] args) {
    JFrame ventana = new JFrame("Flappy Bird");
    ventana.setSize(800, 600);
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ventana.setLocationRelativeTo(null);
    
    PanelDeJuego panelDeJuego = new PanelDeJuego();
    ventana.add( panelDeJuego );
    
    JButton botonReiniciar = new JButton("Reiniciar Juego");
    ventana.add(botonReiniciar, BorderLayout.NORTH);
    
    botonReiniciar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        panelDeJuego.reiniciarJuego();
        panelDeJuego.repaint();
      }
    });
    ventana.setVisible(true);
  } 
}