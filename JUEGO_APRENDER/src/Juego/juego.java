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
import entes.criatura.Criatura;
import entes.criatura.Jugador;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

    private static int aps = 0;
    private static int fps = 0;

    private int tiempoEntreCarritos = 120; // Intervalo entre la introducción de carritos (en frames)
    private int tiempoUltimoCarrito = 0;   // Contador para el último carrito introducido
    private int carritosIntroducidos = 0;  // Número de carritos que ya se han introducido
    private int totalCarritos = 0;         // Total de carritos a introducir

    private boolean esperandoMasCarritos = false;

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

    private static int[] pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();

    private static final ImageIcon icono = new ImageIcon(juego.class.getResource("/icono/CARROICONO.png"));

    private static List<Criatura> carritos = new ArrayList<>(); 
    private static List<int[]> puntosDeSpawn = Arrays.asList(
            new int[]{-1, 5}, // Punto de spawn 1
            new int[]{183, 384}, // Punto de spawn 2
            new int[]{564, 263}, // Punto de spawn 3
            new int[]{-13, 219}, // Punto de spawn 4
            new int[]{423, 332}, // Punto de spawn 5
            new int[]{561, 55} // Punto de spawn 6
    );

    private juego() {
        setPreferredSize(new Dimension(ANCHO, ALTO));
        pantalla = new pantalla(ANCHO, ALTO);
        // mapa = new MapaGenerado(128, 128);

        teclado = new teclado();
        addKeyListener(teclado);

        mapa = new MapaCargado("/mapas/MAPA.png");

        jugador = new Jugador(mapa, teclado, Sprite.DER, 281, 203);
        /*carro = new CARRO2(mapa, Sprite.DER, -1, 5, semaforo3, semaforo2);
        carro3 = new CARRO3(mapa, Sprite.DER, 183, 384, semaforo2, semaforo3);
        carro4 = new CARRO4(mapa, Sprite.DER, 564, 263, semaforo1, semaforo2);
        carro5 = new CARRO5(mapa, Sprite.DER, -13, 219, semaforo1, semaforo2, semaforo3, semaforo4);
        carro6 = new CARRO6(mapa, Sprite.DER, 423, 332, semaforo1, semaforo2, semaforo3, semaforo4);
        //carro7 = new CARRO7(mapa, Sprite.DER,-90,214);
        carro8 = new CARRO8(mapa, Sprite.DER, 561, 55, semaforo2, semaforo3, semaforo4);*/
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
        Scanner scanner = new Scanner(System.in);

      
        System.out.print("Ingrese el número de carritos que desea generar: ");
        int numCarritos = scanner.nextInt();

       
        juego jueg = new juego();

        
        jueg.totalCarritos = numCarritos;

        jueg.iniciar();
    }

    private void generarCarritos(int numCarritos) {
        for (int i = 0; i < numCarritos; i++) {
           
            int spawnIndex = i % puntosDeSpawn.size();// aqui se genera el punto de spawn de forma ciclica pra cada punto
            generarCarritoEnPuntoDeSpawn(spawnIndex);
        }
    }

    private void generarCarritoEnPuntoDeSpawn(int index) {
        int[] spawnPos = puntosDeSpawn.get(index);
        switch (index) {
            case 0:
                carritos.add(new CARRO2(mapa, Sprite.DER, spawnPos[0], spawnPos[1], semaforo3, semaforo2));
                break;
            case 1:
                carritos.add(new CARRO3(mapa, Sprite.DER, spawnPos[0], spawnPos[1], semaforo2, semaforo3));
                break;
            case 2:
                carritos.add(new CARRO4(mapa, Sprite.DER, spawnPos[0], spawnPos[1], semaforo1, semaforo2));
                break;
            case 3:
                carritos.add(new CARRO5(mapa, Sprite.DER, spawnPos[0], spawnPos[1], semaforo1, semaforo2, semaforo3, semaforo4));
                break;
            case 4:
                carritos.add(new CARRO6(mapa, Sprite.DER, spawnPos[0], spawnPos[1], semaforo1, semaforo2, semaforo3, semaforo4));
                break;
            case 5:
                carritos.add(new CARRO8(mapa, Sprite.DER, spawnPos[0], spawnPos[1], semaforo2, semaforo3, semaforo4));
                break;
        }
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

        
        if (!esperandoMasCarritos && carritosIntroducidos < totalCarritos) {
            tiempoUltimoCarrito++;

            if (tiempoUltimoCarrito >= tiempoEntreCarritos) {
                // Introducir un nuevo carrito
                generarCarritoEnPuntoDeSpawn(carritosIntroducidos % puntosDeSpawn.size());
                carritosIntroducidos++;
                tiempoUltimoCarrito = 0; // Reiniciar el contador
            }
        }

        
        if (carritosIntroducidos == totalCarritos && !esperandoMasCarritos) {
            esperandoMasCarritos = true; 
            solicitarMasCarritos();      
        }

        
        for (Criatura carrito : carritos) {
            if (!carrito.estaEliminado()) {
                carrito.actualizar();
            }
        }

        aps++;
    }

    private void solicitarMasCarritos() {
        new Thread(() -> {
            try {
                String input = JOptionPane.showInputDialog(null, "Ingrese el número de carritos que desea agregar:");
                if (input != null) {
                    int numCarritos = Integer.parseInt(input);  // Convertir el input a entero
                    totalCarritos = numCarritos;                // Cambiar totalCarritos al nuevo número
                    carritosIntroducidos = 0;                   // Reiniciar el contador de carritos introducidos
                    tiempoUltimoCarrito = 0;                    // Reiniciar el tiempo del último carrito
                    esperandoMasCarritos = false;               // Reanudar la introducción de carritos
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ingrese un numero valido");
                solicitarMasCarritos();  
            }
        }).start();
    }

    private void mostrar() {
        BufferStrategy estrategia = getBufferStrategy();

        if (estrategia == null) {
            createBufferStrategy(3);
            return;
        }

        pantalla.limpiar();
        mapa.mostrar(jugador.obtenerPosicionX() - pantalla.obtenAncho() / 2 + jugador.obtenSprite().obtenLado() / 2,
                jugador.obtenerPosicionY() - pantalla.obtenAlto() / 2 + jugador.obtenSprite().obtenLado() / 2, pantalla);
        jugador.mostrar(pantalla);

        for (Criatura carrito : carritos) {
            if (!carrito.estaEliminado()) {
                carrito.mostrar(pantalla);
            } else {
                carrito.eliminarSprite(); 
            }
        }

        System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);

        Graphics g = estrategia.getDrawGraphics();
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
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

        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();
        double tiempoTranscurrido;
        double delta = 0;

        requestFocus();

        while (enFuncionamiento) {
            final long inicioBucle = System.nanoTime();

            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;

            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

            while (delta >= 1) {
                actualizar();

                delta--;

            }

            mostrar();

            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
                ventana.setTitle(NOMBRE + "|| APS: " + aps + " || FPS: " + fps);
                System.out.println(fps);
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
            }
        }
    }

}
