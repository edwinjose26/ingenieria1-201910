package flappybird;

import java.awt.Point;

/**
 * @author josearielpereyra
 */
public abstract class Tunel {
  private Point ubicacion;
  private int anchura;
  private int altura;
  
  abstract void dibujar();
}
