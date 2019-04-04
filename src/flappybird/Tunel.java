package flappybird;

import java.awt.Color;
import java.awt.Point;

/**
 * @author josearielpereyra
 */
public abstract class Tunel {
  private Point ubicacion;
  private int anchura;
  private int altura;
  private Color color;

  public Tunel() {
    
  }
  
  abstract void dibujar();
}
