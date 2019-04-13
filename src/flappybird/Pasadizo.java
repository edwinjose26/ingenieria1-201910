package flappybird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

/**
 * @author josearielpereyra
 */
public class Pasadizo {
  public static final int anchuraTunel = 80;
  private final int altura;
  int alturaDelTunelDeArriba;
  Point posicion;
  JPanel panelDeJuego;
  Color color;
  private Tunel tunelSuperior;
  private Tunel tunelInferior;

  public Tunel getTunelSuperior() {
    return tunelSuperior;
  }

  public void setTunelSuperior(Tunel rectanguloDeArriba) {
    this.tunelSuperior = rectanguloDeArriba;
  }

  public Tunel getTunelInferior() {
    return tunelInferior;
  }

  public void setTunelInferior(Tunel tunelInferior) {
    this.tunelInferior = tunelInferior;
  }

  public Pasadizo(JPanel panelDeJuego, Point posicion, int altura, Color color) {
    this.alturaDelTunelDeArriba = posicion.y;
    this.posicion = posicion;
    this.panelDeJuego = panelDeJuego;
    this.altura = altura;
    
    Point posicionTunelSuperior = new Point(posicion.x, 0);
    Dimension dimensionTunelSuperior = new Dimension(anchuraTunel, posicion.y);
    tunelSuperior = new TunelSuperior(posicionTunelSuperior, dimensionTunelSuperior, color);
    
    Point posicionTunelInferior = new Point(posicion.x, posicion.y + altura);
    Dimension dimensionTunelInferior = new Dimension(anchuraTunel, panelDeJuego.getHeight() - posicionTunelInferior.y);
    tunelInferior = new TunelInferior(posicionTunelInferior, dimensionTunelInferior, color);
  }
  
  public void dibujar(Graphics g) {
    tunelSuperior.x = tunelInferior.x = posicion.x;
    tunelSuperior.dibujar(g);
    tunelInferior.dibujar(g);
  }
}