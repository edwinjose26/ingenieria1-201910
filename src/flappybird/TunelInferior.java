package flappybird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/**
 * @author josearielpereyra
 */
public class TunelInferior extends Tunel{
  
  public TunelInferior(Point ubicacion, Dimension dimension, Color color) {
    super(ubicacion, dimension, color);
  }

  @Override
  public void dibujarCabeza(Graphics g) {
    //dibujar la cabeza del tunel
    g.setColor( color );
    g.fillRoundRect(x - 5, y, width + 10, alturaDeLaCabeza, 10, 10);
    g.setColor(Color.BLACK);
    g.drawRoundRect(x - 5, y, width + 10, alturaDeLaCabeza, 10, 10);
  }
}
