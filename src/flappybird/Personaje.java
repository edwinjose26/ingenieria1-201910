package flappybird;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * @author josearielpereyra
 */
public class Personaje {
  private Point posicion;
  private int puntaje;
  private Dimension dimension = new Dimension(50, 50);
  private Image imagen = new ImageIcon( getClass().getResource("../imagenes/flappy.png") ).getImage();

  boolean chocaCon(Tunel tunel) {
    Rectangle rectanguloImagen = new Rectangle(posicion, dimension);
    return rectanguloImagen.intersects(tunel);
  }
  enum Direccion {ARRIBA, ABAJO, ADELANTE, EN_EL_SUELO};
  Direccion direccion;
  
  private int cantidadABajar = 1;
  private int maximoABajar = 5;
  
  private int cantidadASubir = 1;
  private int maximoASubir = 10;
  


  public Personaje(Point posicion, int puntaje, Dimension dimension, String imagen) {
    this.posicion = posicion;
    this.puntaje = puntaje;
    this.dimension = dimension;
    this.imagen = new ImageIcon( getClass().getResource("../imagenes/" + imagen) ).getImage();;
    direccion = Direccion.ABAJO;
  }
  
  public void subir() {
    cantidadABajar = 1;
    cantidadASubir = (cantidadASubir < maximoASubir) ? cantidadASubir + 4 : maximoASubir;
    setImagen( "flappyArriba.png" );
    posicion.y -= cantidadASubir;
  }
  
  public void bajar() {
    cantidadASubir = 1;
    cantidadABajar = (cantidadABajar < maximoABajar) ? cantidadABajar + 2 : maximoABajar;
    setImagen( "flappyAbajo.png" );
    posicion.y += cantidadABajar;
  }
  
  public void moverse() {
   switch (direccion) {
      case ARRIBA: subir(); break;
      case ABAJO: bajar(); break;
      case ADELANTE: setImagen( "flappy.png" ); break;
      default: setImagen( "flappyPerdido.png" ); break;
    }
    
    if(posicion.y < 0) {
      posicion.y = 0;
    }
  }

  public Direccion getDireccion() {
    return direccion;
  }

  public void setDireccion(Direccion direccion) {
    this.direccion = direccion;
  }
  
  public Point getPosicion() {
    return posicion;
  }

  public void setPosicion(Point posicion) {
    this.posicion = posicion;
  }

  public int getPuntaje() {
    return puntaje;
  }

  public void setPuntaje(int puntaje) {
    this.puntaje = puntaje;
  }

  public Dimension getDimension() {
    return dimension;
  }

  public void setDimension(Dimension dimension) {
    this.dimension = dimension;
  }

  public Image getImagen() {
    return imagen;
  }

  public void setImagen(String imagen) {
    this.imagen = new ImageIcon( getClass().getResource( "../imagenes/" + imagen ) ).getImage();
  }
  
  public void dibujar(Graphics g) {
    if(imagen != null) {
      g.drawImage(imagen, posicion.x, posicion.y, dimension.width, dimension.height, null);
    }
  }
}
