package flappybird;

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
    
    ventana.setVisible(true);
  } 
}