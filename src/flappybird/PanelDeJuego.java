package flappybird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author josearielpereyra
 */
public class PanelDeJuego extends JPanel{

  ArrayList<Pasadizo> pasadizos;
  Personaje personaje;
  int totalDePasadizos;
  int alturaPasadizos = 150;
  int xInicial = 10;
  int yFlappy = 0;  
  Color colorTunel = new Color(165, 198, 93);
  Random numerosAleatorios = new Random();
  boolean elJuegoHaTerminado;
  
  private final Timer temporizador;
  private final Timer temporizadorPersonaje;
  private int alturaPiso;

  public PanelDeJuego() {
    reiniciarJuego();
    
    temporizador = new Timer(15, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        repaint();
      }
    });
    
    temporizadorPersonaje = new Timer(200, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        personaje.setDireccion(Personaje.Direccion.ABAJO);
      }
    });
    
    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
          personaje.setDireccion(Personaje.Direccion.ARRIBA);
        }
        else if( e.getKeyCode() == KeyEvent.VK_SPACE) {
          xInicial = getWidth()/ 2 + 100;
          jugar();
        }
      }
      
      @Override
      public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
          personaje.setDireccion(Personaje.Direccion.ADELANTE);
        }
      }
    });
    
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
          personaje.setDireccion(Personaje.Direccion.ARRIBA);
      } 
      
      @Override
      public void mouseReleased(MouseEvent e) {
          personaje.setDireccion(Personaje.Direccion.ADELANTE);
      } 
    });
  }

  public void reiniciarJuego() {
    pasadizos = new ArrayList<>();
    totalDePasadizos = 1000;
    personaje = new Personaje( new Point(200, 200), 0, new Dimension(50, 50), "flappy.png");
    personaje.setDireccion(Personaje.Direccion.ADELANTE);
    elJuegoHaTerminado = false;
  }
  
  private void jugar(){
    if(pasadizos.size() == 0) {
      Random numerosAleatorios = new Random();
      int anchuraTunel = 100;

      int x = xInicial;
      for (int i = 0; i < totalDePasadizos; i++) {
        int alturaAleatoria = numerosAleatorios.nextInt( 40 ) + 20;
        int alturaDelTunelDeArriba = (int)((getHeight() - alturaPiso) * ((double)alturaAleatoria/100) );

        pasadizos.add( new Pasadizo(PanelDeJuego.this, new Point(x, alturaDelTunelDeArriba), alturaPasadizos, colorTunel) );

        x += anchuraTunel * ((alturaAleatoria % 2) + 2);
      }
    }
    
    if(elJuegoHaTerminado) {
      personaje.setDireccion(Personaje.Direccion.EN_EL_SUELO);
    }
    else {
      if( estaCorriendoElJuego() ){
        detenerJuego();
      } 
      else {
        reanudarJuego();
      }
    }
  }
  
  private boolean estaCorriendoElJuego() {
    return temporizador.isRunning();
  }

  private void detenerJuego() {
    temporizador.stop();
    temporizadorPersonaje.stop();
  }

  private void reanudarJuego() {
    temporizador.start();
    temporizadorPersonaje.start();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    requestFocusInWindow();
    
    int anchura = getWidth();
    int altura = getHeight();
    alturaPiso = altura/10;
    int yPiso = altura - alturaPiso;
    
    //dibujar fondo
    g.setColor(new Color(50, 50, 50));
    g.setColor(new Color(135, 206, 235));
    g.fillRect(0,0, anchura, altura);
    
    //dibujar la luna (o el sol)
    g.setColor(new Color(230, 230, 230));
    g.setColor(new Color(255, 255, 0));
    g.fillOval(100, 100, 150, 150);
    
    xInicial -= 20;
    if(xInicial < -(210 * 30) ) {
      xInicial = getWidth();
    }
   
    dibujarTuneles( g);
    
    //dibujar piso
    g.setColor(new Color(100, 100, 255, 240));
    g.fillRect(0, yPiso, anchura, alturaPiso);
    
    //dibujar el borde del piso
    g.setColor(colorTunel);
    g.fillRect(0, yPiso, anchura, alturaPiso / 5);

    g.setColor(Color.GRAY);
    g.drawRect(0, yPiso, anchura, alturaPiso / 5);
    
    personaje.moverse();
    if(personaje.getPosicion().y >= yPiso - personaje.getDimension().height ) {
      personaje.setPosicion( new Point(personaje.getPosicion().x, yPiso - personaje.getDimension().height) );
      personaje.setDireccion(Personaje.Direccion.EN_EL_SUELO);
      elJuegoHaTerminado = true;
      personaje.moverse();
      personaje.dibujar(g);
      detenerJuego();
    }
    personaje.dibujar(g);
    
    g.setFont(new Font("serif", Font.BOLD, 30));
    g.drawString("" + personaje.getPuntaje(), getWidth()/2, getHeight()/3);
  }

  private void dibujarTuneles( Graphics g ) {
    int x = xInicial;
    for (int i = 0; i < pasadizos.size(); i ++) {
      Pasadizo pasadizo = pasadizos.get(i);
      pasadizo.posicion.x -= 5;
      if(pasadizo.posicion.x <= getWidth() &&  pasadizo.posicion.x > - 200) {
        personaje.setPuntaje(i);
        pasadizo.dibujar(g);
        System.out.println(pasadizo.posicion);
      }
    }
  }
}