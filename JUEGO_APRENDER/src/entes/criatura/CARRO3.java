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
import semaforos.Semaforo2;
import semaforos.Semaforo3;

/**
 *
 * @author lucho
 */
public class CARRO3 extends Criatura {
    private ArrayList<int[]> nodos;
    private int nodoActual = 0;
    private static final int ZONA_ESPERA = 10;
    private Semaforo semaforo2; // Referencia al semáforo del cruce 1
    private Semaforo semaforo3; // Referencia al semáforo del cruce 2
     public CARRO3(Mapa mapa, Sprite sprite) {
         super();
        this.mapa = mapa;
        this.sprite = sprite;
        this.esSolido=true;
        this.nodos = new ArrayList<>();
         this.agregarNodo(508, 11);
        
    }

    public CARRO3(Mapa mapa, Sprite sprite, int posicionX, int posicionY, Semaforo semaforo2, Semaforo semaforo3) {
        super();
        this.esSolido=true;
        this.mapa = mapa;
        this.x = posicionX;
        this.y = posicionY;
        this.sprite = sprite;
        this.semaforo2 = semaforo2;
        this.semaforo3 = semaforo3;
        this.nodos = new ArrayList<>();
        this.agregarNodo(183, 384);
        this.agregarNodo(183, 306);
        this.agregarNodo(183, 174);
        this.agregarNodo(183, 97);
        this.agregarNodo(182, 54);
        this.agregarNodo(-14, 54);
        
        
    }
    
    private boolean estaEnZonaDeEspera(int[] nodo) {
        int distanciaX = Math.abs(x - nodo[0]);
        int distanciaY = Math.abs(y - nodo[1]);
        return distanciaX <= ZONA_ESPERA && distanciaY <= ZONA_ESPERA;
    }
    
    public void agregarNodo(int x, int y) {
        nodos.add(new int[]{x, y});
    }

    // Método para mover al siguiente nodo
    public void moverAlSiguienteNodo() {
        if (nodoActual < nodos.size()) {
            int[] nodo = nodos.get(nodoActual);
            int targetX = nodo[0];
            int targetY = nodo[1];
            
            if (nodoActual == 1&& estaEnZonaDeEspera(nodo) && !semaforo2.puedeAvanzarSentido2()) {
                
               
                return; // Esperar a que el semáforo permita avanzars
            }
            if (nodoActual == 3&& estaEnZonaDeEspera(nodo) && !semaforo3.puedeAvanzarSentido2()) {
                
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
        sprite = Sprite.ARR;
    }

    public void moverAbajo() {
        mover(0, 1);
        direccion = 's';
        sprite = Sprite.ABA;
    }

    public void moverDerecha() {
        mover(1, 0);  
        direccion = 'e';
        sprite = Sprite.DER;
    }

    public void moverIzquierda() {
        mover(-1, 0); 
        direccion = 'o';
        sprite = Sprite.IZQ;
    }

    // Método actualizar puede recibir lógica desde otra clase o sistema de nodos.
    public void actualizar() {
         moverAlSiguienteNodo();
       
        
        
    }

    public void mostrar(pantalla pantalla) {
        pantalla.mostrarJugador(x, y, this);
    }
}
