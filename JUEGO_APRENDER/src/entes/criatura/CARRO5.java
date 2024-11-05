/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entes.criatura;

import Graficos.Sprite;
import Graficos.pantalla;
import java.util.ArrayList;
import mapa.Mapa;
import semaforos.Semaforo;

/**
 *
 * @author lucho
 */
public class CARRO5 extends Criatura {
    private ArrayList<int[]> nodos;
    private int nodoActual = 0;
    private Semaforo semaforo1; // Referencia al semáforo del cruce 1
    private Semaforo semaforo2; // Referencia al semáforo del cruce 2
    private Semaforo semaforo3; // Referencia al semáforo del cruce 3
    private Semaforo semaforo4; // Referencia al semáforo del cruce 4
    private static final int ZONA_ESPERA = 10;
    private Sprite[] spritesCarrito; // Array de sprites para el color del carrito

    public CARRO5(Mapa mapa, Sprite sprite, int posicionX, int posicionY, Semaforo semaforo1, Semaforo semaforo2, Semaforo semaforo3, Semaforo semaforo4, Sprite[] spritesCarrito) {
        super();
        this.esSolido = true;
        this.mapa = mapa;
        this.x = posicionX;
        this.y = posicionY;
        this.sprite = sprite;
        this.semaforo1 = semaforo1;
        this.semaforo2 = semaforo2;
        this.semaforo3 = semaforo3;
        this.semaforo4 = semaforo4;
        this.spritesCarrito = spritesCarrito; 
        this.nodos = new ArrayList<>();

        // Agregar nodos
        this.agregarNodo(-13, 219);
        this.agregarNodo(96, 219);
        this.agregarNodo(182, 216);
        this.agregarNodo(182, 96);
        this.agregarNodo(182, 11);
        this.agregarNodo(331, 11);
        this.agregarNodo(379, 11);
        this.agregarNodo(379, 177);
        this.agregarNodo(379, 220);
        this.agregarNodo(578, 220);
    }

    public void agregarNodo(int x, int y) {
        nodos.add(new int[]{x, y});
    }

    private boolean estaEnZonaDeEspera(int[] nodo) {
        int distanciaX = Math.abs(x - nodo[0]);
        int distanciaY = Math.abs(y - nodo[1]);
        return distanciaX <= ZONA_ESPERA && distanciaY <= ZONA_ESPERA;
    }

    // Método para mover al siguiente nodo
    public void moverAlSiguienteNodo() {
        if (nodoActual < nodos.size()) {
            int[] nodo = nodos.get(nodoActual);
            int targetX = nodo[0];
            int targetY = nodo[1];

            if (nodoActual == 1 && estaEnZonaDeEspera(nodo) && !semaforo2.puedeAvanzarSentido1()) {
                return; // Esperar a que el semáforo permita avanzar
            }
            if (nodoActual == 3 && estaEnZonaDeEspera(nodo) && !semaforo3.puedeAvanzarSentido2()) {
                return; // Esperar a que el semáforo permita avanzar
            }
            if (nodoActual == 5 && estaEnZonaDeEspera(nodo) && !semaforo4.puedeAvanzarSentido1()) {
                return; // Esperar a que el semáforo permita avanzar
            }
            if (nodoActual == 7 && estaEnZonaDeEspera(nodo) && !semaforo1.puedeAvanzarSentido2()) {
                return; // Esperar a que el semáforo permita avanzar
            }

            // Lógica para mover hacia el nodo
            if (x < targetX) {
                moverDerecha();
            } else if (x > targetX) {
                moverIzquierda();
            }
            if (y < targetY) {
                moverAbajo();
            } else if (y > targetY) {
                moverArriba();
            }

            // Verificar si hemos alcanzado el nodo actual
            if (x == targetX && y == targetY) {
                nodoActual++; // Mover al siguiente nodo
            }
        }
        if (nodoActual >= nodos.size()) {
            eliminar(); // Llamar al método para eliminar el objeto
        }
    }

    public void moverArriba() {
        mover(0, -1);
        direccion = 'n';
        sprite = spritesCarrito[2]; // Cambiar al sprite hacia arriba
    }

    public void moverAbajo() {
        mover(0, 1);
        direccion = 's';
        sprite = spritesCarrito[3]; // Cambiar al sprite hacia abajo
    }

    public void moverDerecha() {
        mover(1, 0);
        direccion = 'e';
        sprite = spritesCarrito[1]; // Cambiar al sprite hacia la derecha
    }

    public void moverIzquierda() {
        mover(-1, 0);
        direccion = 'o';
        sprite = spritesCarrito[0]; // Cambiar al sprite hacia la izquierda
    }

    // Método actualizar puede recibir lógica desde otra clase o sistema de nodos.
    public void actualizar() {
        moverAlSiguienteNodo();
    }

    public void mostrar(pantalla pantalla) {
        pantalla.mostrarJugador(x, y, this);
    }
}

