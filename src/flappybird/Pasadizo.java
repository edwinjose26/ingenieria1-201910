package flappybird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JPanel;

/**
 * @author josearielpereyra
 */
public class Pasadizo {
  public static final int anchuraTunel = 80;
  private static final int alturaDeLaCabeza = 30;
  private final int altura;
  int alturaDelTunelDeArriba;
  Point posicion;
  JPanel panelDeJuego;
  Color color;
  private Rectangle rectanguloDeArriba;
  private Rectangle rectanguloDeAbajo;

  public Rectangle getRectanguloDeArriba() {
    return rectanguloDeArriba;
  }

  public void setRectanguloDeArriba(Rectangle rectanguloDeArriba) {
    this.rectanguloDeArriba = rectanguloDeArriba;
  }

  public Rectangle getRectanguloDeAbajo() {
    return rectanguloDeAbajo;
  }

  public void setRectanguloDeAbajo(Rectangle rectanguloDeAbajo) {
    this.rectanguloDeAbajo = rectanguloDeAbajo;
  }

  public Pasadizo(JPanel panelDeJuego, Point posicion, int altura, Color color) {
    this.alturaDelTunelDeArriba = posicion.y;
    this.posicion = posicion;
    this.panelDeJuego = panelDeJuego;
    this.color = color;
    this.altura = altura;
  }
  
  public Rectangle obtenerRectanguloDeArriba() {
    return rectanguloDeArriba;
  }
  
  public Rectangle obtenerRectanguloDeAbajo() {
    return rectanguloDeAbajo;
  }
  
  public void dibujar(Graphics g) {
    int alturaPanel = panelDeJuego.getHeight();
    
    ////////////////////TUNEL DE ARRIBA////////////////////////////////////
    //dibujar la base del tunel
    int y = 0;
    g.setColor( new Color(165, 198, 93) );
    g.fillRect(posicion.x, y, anchuraTunel,  alturaDelTunelDeArriba);
    g.setColor(Color.BLACK);
    g.drawRect(posicion.x, y, anchuraTunel,  alturaDelTunelDeArriba);

    //dibujar la cabeza del tunel
    y += posicion.y;
    g.setColor( new Color(165, 198, 93) );
    g.fillRoundRect(posicion.x - 5, y, anchuraTunel + 10, alturaDeLaCabeza, 10, 10 );
    g.setColor(Color.BLACK);
    g.drawRoundRect(posicion.x - 5, y, anchuraTunel + 10, alturaDeLaCabeza, 10, 10 );
    


    ////////////////////TUNEL DE ABAJO////////////////////////////////////
    //dibujar la base del tunel
    g.setColor( color );
    y += altura;
    g.fillRect(posicion.x, y, anchuraTunel,  alturaPanel - (alturaDelTunelDeArriba + altura) );
    g.setColor(Color.BLACK);
    g.drawRect(posicion.x, y, anchuraTunel,  alturaPanel - (alturaDelTunelDeArriba + altura) );

    //dibujar la cabeza del tunel
    g.setColor( color );
    g.fillRoundRect(posicion.x - 5, y, anchuraTunel + 10, alturaDeLaCabeza, 10, 10);
    g.setColor(Color.BLACK);
    g.drawRoundRect(posicion.x - 5, y, anchuraTunel + 10, alturaDeLaCabeza, 10, 10);
  }
}