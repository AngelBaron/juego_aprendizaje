/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego;

import Graficos.Sprite;
import Graficos.pantalla;
import control.teclado;
import entes.criatura.Jugador;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import mapa.Mapa;
import mapa.MapaCargado;
import mapa.MapaGenerado;

/**
 *
 * @author angel
 */
public class juego extends Canvas implements Runnable {

    private static final int ANCHO = 600;
    private static final int ALTO = 420;

    private static volatile boolean enFuncionamiento = false;

    private static final String NOMBRE = "Juego";
    
    private static int aps=0;
    private static int fps =0;
    

    
    private static JFrame ventana;
    private static Thread thread;
    private static teclado teclado;
    private static pantalla pantalla;
    
    private static Mapa mapa;
    
    private static Jugador jugador;
    
    private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
    
    private static int[] pixeles = ((DataBufferInt)imagen.getRaster().getDataBuffer()).getData();
    
    private static final ImageIcon icono = new ImageIcon(juego.class.getResource("/icono/CARROICONO.png"));

    private juego() {
        setPreferredSize(new Dimension(ANCHO, ALTO));
        pantalla  = new pantalla(ANCHO, ALTO);
       // mapa = new MapaGenerado(128, 128);
       
        teclado = new teclado();
        addKeyListener(teclado);
        
        mapa =new MapaCargado("/mapas/MAPA.png");
       jugador = new Jugador(mapa,teclado, Sprite.DER,334,276);

        ventana = new JFrame(NOMBRE);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setIconImage(icono.getImage());
        ventana.setLayout(new BorderLayout());
        ventana.add(this, BorderLayout.CENTER);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        juego jueg = new juego();
        jueg.iniciar();
    }

    private synchronized void iniciar() {

        enFuncionamiento = true;

        thread = new Thread(this, "graficos");
        thread.start();
    }

    private synchronized void detener() {
        enFuncionamiento = false;

        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void actualizar() {
        
        teclado.actualizar();
        
        jugador.actualizar();
        
        
        aps++;

    }

    private void mostrar() {
        BufferStrategy estrategia = getBufferStrategy();
        
        if(estrategia ==null){
            createBufferStrategy(3);
            return;
        }
        
        pantalla.limpiar();
        mapa.mostrar(jugador.obtenerPosicionX() - pantalla.obtenAncho()/2 + jugador.obtenSprite().obtenLado()/2, jugador.obtenerPosicionY() - pantalla.obtenAlto()/2 + jugador.obtenSprite().obtenLado()/2, pantalla);
        jugador.mostrar(pantalla);
        
        
        System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);
        
        //for(int i =0; i<pixeles.length;i++){
        //  pixeles[i] = pantalla.pixeles[i];
        //}
        
        Graphics g = estrategia.getDrawGraphics();
        g.drawImage(imagen, 0, 0, getWidth(),getHeight(),null);
        g.setColor(Color.white);
        g.drawString("X: " + jugador.obtenerPosicionX(), 10, 20);
        g.drawString("Y: " + jugador.obtenerPosicionY(), 10, 35);
        
        g.dispose();
        
        estrategia.show();
        
        fps++;
    }

    @Override
    public void run() {

        final int NS_POR_SEGUNDO = 1000000000;
        final byte APS_OBJETIVO = 60;
        //CUANTOS NANOSEGUNDOS TIENEN QUE TRANSCURRIR PARA QUE SIGAMOS EL OBJETIVO DE ACTUALIZACIONES POR SEGUNDO
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
        
        long referenciaActualizacion= System.nanoTime();
        long referenciaContador =System.nanoTime();
        double tiempoTranscurrido;
        double delta =0;
        
        requestFocus();
        
        while (enFuncionamiento) {
            final long inicioBucle = System.nanoTime();
            
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;
            
            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
            
            while (delta>= 1){
                actualizar();
                
                delta--;
                
            }
            
           mostrar();
            
            
            if(System.nanoTime() - referenciaContador > NS_POR_SEGUNDO){
                ventana.setTitle(NOMBRE + "|| APS: " + aps + " || FPS: "+ fps);
                System.out.println(fps);
                aps=0;
                fps=0;
                referenciaContador = System.nanoTime();
            }
        }
    }

}
