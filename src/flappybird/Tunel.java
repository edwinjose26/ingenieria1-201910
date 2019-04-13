package flappybird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * @author josearielpereyra
 */
public abstract class Tunel extends Rectangle{
  protected Color color;
  protected int alturaDeLaCabeza = 30;

  public Tunel(Point ubicacion, Dimension dimension, Color color) {
    super(ubicacion, dimension);
    this.color = color;
  }
  
  public void dibujarCuerpo(Graphics g) {
    g.setColor( color );
    g.fillRect(x, y, width,  height);
    g.setColor(Color.BLACK);
    g.drawRect(x, y, width,  height);
  }
  
  public abstract void dibujarCabeza(Graphics g);
  
  public void dibujar(Graphics g) {
    dibujarCuerpo(g);
    dibujarCabeza(g);
  }
}
