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
public class CARRO8 extends Criatura{
    private ArrayList<int[]> nodos;
    private int nodoActual = 0;
    
    private Semaforo semaforo2;
     private Semaforo semaforo3; // Referencia al semáforo del cruce 1
    private Semaforo semaforo4; // Referencia al semáforo del cruce 2
    private static final int ZONA_ESPERA = 10;// Referencia al semáforo del cruce 2
     public CARRO8(Mapa mapa, Sprite sprite) {
         super();
        this.mapa = mapa;
        this.sprite = sprite;
        this.esSolido=true;
        this.nodos = new ArrayList<>();
         this.agregarNodo(508, 11);
        
    }

    public CARRO8(Mapa mapa, Sprite sprite, int posicionX, int posicionY,Semaforo semaforo2, Semaforo semaforo3, Semaforo semaforo4) {
        super();
        this.esSolido=true;
        this.mapa = mapa;
        this.x = posicionX;
        this.y = posicionY;
        this.sprite = sprite;
        this.semaforo2 = semaforo2;
        this.semaforo3 = semaforo3;
        this.semaforo4 = semaforo4;
        this.nodos = new ArrayList<>();
        this.agregarNodo(561, 55);
        this.agregarNodo(465, 50);
        this.agregarNodo(227, 50);
        this.agregarNodo(142, 50);
        this.agregarNodo(142, 173);
        this.agregarNodo(142, 390);
        
        
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
            
            if (nodoActual == 1&& estaEnZonaDeEspera(nodo) && !semaforo4.puedeAvanzarSentido1()) {
                
                
                return; // Esperar a que el semáforo permita avanzars
            }
            if (nodoActual == 2 && estaEnZonaDeEspera(nodo) && !semaforo3.puedeAvanzarSentido1()) {
               
                return; // Esperar a que el semáforo permita avanzar
            }
            if (nodoActual == 4 && estaEnZonaDeEspera(nodo) && !semaforo2.puedeAvanzarSentido2()) {
               
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
        if(!estaEliminado()){
            pantalla.mostrarJugador(x, y, this);
        }else{
            System.out.println("ELIMINADOOOOOOOO");
        }
        
    }
}
