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
import entes.criatura.Persona;
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
import java.util.Iterator;
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

    private int contadorCarritos = 0;
    private int totalCarritosSolicitados = 0;

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

    private static List<Persona> personas = new ArrayList<>();

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
        // Generar personas
        generarPersonas();
    }

    public static void main(String[] args) {

        juego jueg = new juego();
        jueg.solicitarMasCarritos();

        jueg.iniciar();
    }

    private void generarCarritos(int numCarritos) {
        for (int i = 0; i < numCarritos; i++) {

            int spawnIndex = i % puntosDeSpawn.size();// aqui se genera el punto de spawn de forma ciclica pra cada punto
            generarCarritoEnPuntoDeSpawn(spawnIndex);
        }
    }

    private void generarPersonas() {

        Persona personaVerde = new Persona(mapa, Sprite.PERSONA_VERDE_PARADO_FRENTE, 200, 80, "verde");
        personaVerde.agregarNodo(208, 80);
        personaVerde.agregarNodo(208, 205);
        personaVerde.agregarNodo(368, 205);
        personaVerde.agregarNodo(368, 80);
        personas.add(personaVerde);

        Persona personaVerde2 = new Persona(mapa, Sprite.PERSONA_VERDE_PARADO_FRENTE, 50, 80, "verde");
        personaVerde2.agregarNodo(208, 80);
        personaVerde2.agregarNodo(208, 205);
        personaVerde2.agregarNodo(368, 205);
        personaVerde2.agregarNodo(368, 80);
        personas.add(personaVerde2);

        Persona personaVerde3 = new Persona(mapa, Sprite.PERSONA_VERDE_PARADO_FRENTE, -30, 285, "verde");
        personaVerde3.agregarNodo(210, 285);
        personaVerde3.agregarNodo(370, 285);
        personaVerde3.agregarNodo(370, 450);
        personaVerde3.agregarNodo(210, 450);
        personaVerde3.agregarNodo(210, 285);
        personas.add(personaVerde3);

        Persona personaVerde4 = new Persona(mapa, Sprite.PERSONA_VERDE_PARADO_FRENTE, 700, 80, "verde");
        personaVerde4.agregarNodo(447, 80);
        personaVerde4.agregarNodo(447, 500);
        personaVerde4.agregarNodo(447, 285);
        personaVerde4.agregarNodo(700, 285);
        personaVerde4.agregarNodo(447, 285);
        personas.add(personaVerde4);

        Persona personaRosa = new Persona(mapa, Sprite.PERSONA_ROSA_PARADO_FRENTE, 368, 80, "rosa");
        personaRosa.agregarNodo(368, 80);
        personaRosa.agregarNodo(368, 205);
        personaRosa.agregarNodo(208, 205);
        personaRosa.agregarNodo(208, 80);
        personas.add(personaRosa);

        Persona personaRosa2 = new Persona(mapa, Sprite.PERSONA_ROSA_PARADO_FRENTE, 368, 80, "rosa");
        personaRosa2.agregarNodo(-30, 80);
        personaRosa2.agregarNodo(-30, 205);
        personaRosa2.agregarNodo(130, 205);
        personaRosa2.agregarNodo(130, 80);
        personas.add(personaRosa2);

        Persona personaRosa3 = new Persona(mapa, Sprite.PERSONA_ROSA_PARADO_FRENTE, 700, 285, "rosa");
        personaRosa3.agregarNodo(-30, 285);
        personaRosa3.agregarNodo(-30, 480);
        personaRosa3.agregarNodo(130, 480);
        personaRosa3.agregarNodo(130, 285);
        personaRosa3.agregarNodo(130, 80);
        personaRosa3.agregarNodo(130, 285);
        personas.add(personaRosa3);

        Persona personaRosa4 = new Persona(mapa, Sprite.PERSONA_ROSA_PARADO_FRENTE, 447, 500, "rosa");
        personaRosa4.agregarNodo(447, 285);
        personaRosa4.agregarNodo(700, 285);
        personaRosa4.agregarNodo(700, 500);
        personaRosa4.agregarNodo(447, 500);

        personas.add(personaRosa4);

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

    private boolean primeraSolicitud = true;

    private void actualizar() {
        teclado.actualizar();
        jugador.actualizar();

        if (!esperandoMasCarritos && carritosIntroducidos < totalCarritos) {
            tiempoUltimoCarrito++;

            if (tiempoUltimoCarrito >= tiempoEntreCarritos) {
                generarCarritoEnPuntoDeSpawn(carritosIntroducidos % puntosDeSpawn.size());
                carritosIntroducidos++;
                tiempoUltimoCarrito = 0; // Reiniciar el contador
                contadorCarritos++; // Incrementar el contador cuando se introduce un carrito nuevo
            }
        }

        if (carritosIntroducidos == totalCarritos && !esperandoMasCarritos) {
            esperandoMasCarritos = true;

            if (!primeraSolicitud) {
                solicitarMasCarritos();
            } else {

                primeraSolicitud = false;
            }
        }

        // Actualizar carritos y eliminar los que hayan terminado su recorrido
        Iterator<Criatura> iterator = carritos.iterator();
        while (iterator.hasNext()) {
            Criatura carrito = iterator.next();
            if (!carrito.estaEliminado()) {
                carrito.actualizar();
            } else {
                carrito.eliminarSprite();
                iterator.remove();
                contadorCarritos--;
            }
        }

        // Actualizar todas las personas
        for (Persona persona : personas) {
            if (!persona.estaEliminado()) {
                persona.actualizar();
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
                    totalCarritosSolicitados = numCarritos;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido");
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

        // Mostrar los semáforos en posiciones específicas
        pantalla.mostrarSemaforo(367, 205, semaforo1.getSpriteActual());
        pantalla.mostrarSemaforo(204, 203, semaforo2.getSpriteActual());
        pantalla.mostrarSemaforo(204, 70, semaforo3.getSpriteActual());
        pantalla.mostrarSemaforo(367, 70, semaforo4.getSpriteActual());

        for (Criatura carrito : carritos) {
            if (!carrito.estaEliminado()) {
                carrito.mostrar(pantalla);
            } else {
                carrito.eliminarSprite();
            }
        }

        for (Persona persona : personas) {
            if (!persona.estaEliminado()) {
                persona.mostrar(pantalla);
            }
        }

        System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);

        Graphics g = estrategia.getDrawGraphics();
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.white);
        g.drawString("X: " + jugador.obtenerPosicionX(), 10, 20);
        g.drawString("Y: " + jugador.obtenerPosicionY(), 10, 35);
        g.drawString("Carritos en pantalla: " + contadorCarritos, 420, 20); // Mostrar el contador de carritos
        g.drawString("Total de carritos solicitados: " + totalCarritosSolicitados, 420, 35);

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
