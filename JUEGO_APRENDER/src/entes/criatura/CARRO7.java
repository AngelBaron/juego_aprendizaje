/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entes.criatura;

import Graficos.Sprite;
import Graficos.pantalla;
import java.util.ArrayList;
import mapa.Mapa;

/**
 *
 * @author lucho
 */
public class CARRO7 extends Criatura{
    private ArrayList<int[]> nodos;
    private int nodoActual = 0;
     public CARRO7(Mapa mapa, Sprite sprite) {
         super();
        this.mapa = mapa;
        this.sprite = sprite;
        this.esSolido=true;
        this.nodos = new ArrayList<>();
         this.agregarNodo(508, 11);
        
    }

    public CARRO7(Mapa mapa, Sprite sprite, int posicionX, int posicionY) {
        super();
        this.esSolido=true;
        this.mapa = mapa;
        this.x = posicionX;
        this.y = posicionY;
        this.sprite = sprite;
        this.nodos = new ArrayList<>();
        this.agregarNodo(-1, 214);
        this.agregarNodo(93, 214);
        this.agregarNodo(331, 214);
        this.agregarNodo(558, 214);
        
        
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
