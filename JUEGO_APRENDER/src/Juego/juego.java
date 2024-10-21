/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego;

import Graficos.Sprite;
import Graficos.pantalla;
import control.teclado;
import entes.criatura.CARRO2;
import entes.criatura.CARRO3;
import entes.criatura.CARRO4;
import entes.criatura.CARRO5;
import entes.criatura.CARRO6;
import entes.criatura.CARRO7;
import entes.criatura.CARRO8;
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
import semaforos.Semaforo1;
import semaforos.Semaforo2;
import semaforos.Semaforo3;
import semaforos.Semaforo4;

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
    Semaforo1 semaforo1 = new Semaforo1(5000, 5000);
    Semaforo2 semaforo2 = new Semaforo2(5000, 5000);
    Semaforo3 semaforo3 = new Semaforo3(5000, 5000);
    Semaforo4 semaforo4 = new Semaforo4(5000, 5000);
    Thread hiloSemaforo1 = new Thread(semaforo1);
    Thread hiloSemaforo2 = new Thread(semaforo2);
    Thread hiloSemaforo3 = new Thread(semaforo3);
    Thread hiloSemaforo4 = new Thread(semaforo4);
    private static Jugador jugador;
    private static CARRO2 carro;
    private static CARRO3 carro3;
    private static CARRO4 carro4;
    private static CARRO5 carro5;
    private static CARRO6 carro6;
    private static CARRO7 carro7;
    private static CARRO8 carro8;
    
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
        
        
       jugador = new Jugador(mapa,teclado, Sprite.DER,281,203);
       carro = new CARRO2(mapa, Sprite.DER,-1,5,semaforo3,semaforo2);
       carro3 = new CARRO3(mapa, Sprite.DER,183,384,semaforo2,semaforo3);
       carro4 = new CARRO4(mapa, Sprite.DER,564,263,semaforo1,semaforo2);
       carro5 = new CARRO5(mapa, Sprite.DER,-13,219,semaforo1,semaforo2,semaforo3,semaforo4);
       carro6 = new CARRO6(mapa, Sprite.DER,423,332,semaforo1,semaforo2,semaforo3,semaforo4);
       carro7 = new CARRO7(mapa, Sprite.DER,-90,214);
       carro8 = new CARRO8(mapa, Sprite.DER,561,55,semaforo2,semaforo3,semaforo4);
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
        hiloSemaforo1.start();
        hiloSemaforo2.start();
        hiloSemaforo3.start();
        hiloSemaforo4.start();
        
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
        if(!carro.estaEliminado()){
            carro.actualizar();
        }
        
        carro3.actualizar();
        carro4.actualizar();
        carro5.actualizar();
        carro6.actualizar();
        carro7.actualizar();
        carro8.actualizar();
        
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
        if(!carro.estaEliminado()){
           
            carro.mostrar(pantalla);
        }else{
            
            
            carro.eliminarSprite();
        }
        
        
         
        if(!carro3.estaEliminado()){
            carro3.mostrar(pantalla);
        }else{
            carro3.eliminarSprite();
        }
        if(!carro4.estaEliminado()){
            carro4.mostrar(pantalla);
        }else{
            carro4.eliminarSprite();
        }
        if(!carro5.estaEliminado()){
            carro5.mostrar(pantalla);
        }else{
            carro5.eliminarSprite();
        }
        if(!carro6.estaEliminado()){
            carro6.mostrar(pantalla);
        }else{
            carro6.eliminarSprite();
        }
        if(!carro7.estaEliminado()){
            carro7.mostrar(pantalla);
        }else{
            carro8.eliminarSprite();
        }
        if(!carro8.estaEliminado()){
            carro8.mostrar(pantalla);
        }else{
            carro8.eliminarSprite();
        }
        
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
