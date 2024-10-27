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
 * @author kenne
 */
public class Persona extends Criatura {

    private ArrayList<int[]> nodos;
    private int nodoActual = 0;
    private int contadorAnimacion = 0; // Contador para la animación
    private int velocidadAnimacion = 10; //frames que pasan antes de cambiar el sprite 
    private String tipo; // "verde" o "rosa"

    public Persona(Mapa mapa, Sprite sprite, int posicionX, int posicionY, String tipo) {
        super();
        this.mapa = mapa;
        this.x = posicionX;
        this.y = posicionY;
        this.sprite = sprite;
        this.tipo = tipo; // Almacena el tipo del personaje
        this.esSolido = false; // No es sólido para no bloquear el movimiento
        this.nodos = new ArrayList<>();
    }

   
    public void agregarNodo(int x, int y) {
        nodos.add(new int[]{x, y});
    }

    
    public void moverAlSiguienteNodo() {
        if (nodoActual < nodos.size()) {
            int[] nodo = nodos.get(nodoActual);
            int targetX = nodo[0];
            int targetY = nodo[1];

            
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

            
            if (x == targetX && y == targetY) {
                nodoActual++;
            }
        }

        if (nodoActual >= nodos.size()) {
            nodoActual = 0; // Reiniciar el ciclo de nodos para que la persona siga rondando
        }
    }

   
    public void moverArriba() {
        moverSinColision(0, -1);
        direccion = 'n';
        
        if (tipo.equals("verde")) {
            if (contadorAnimacion % (velocidadAnimacion * 2) < velocidadAnimacion) {
                sprite = Sprite.PERSONA_VERDE_CAMINANDO_ATRAS_1;
            } else {
                sprite = Sprite.PERSONA_VERDE_CAMINANDO_ATRAS_2;
            }
        } else if (tipo.equals("rosa")) {
            if (contadorAnimacion % (velocidadAnimacion * 2) < velocidadAnimacion) {
                sprite = Sprite.PERSONA_ROSA_CAMINANDO_ATRAS_1;
            } else {
                sprite = Sprite.PERSONA_ROSA_CAMINANDO_ATRAS_2;
            }
        }
        contadorAnimacion++;
    }

    public void moverAbajo() {
        moverSinColision(0, 1);
        direccion = 's';
        
        if (tipo.equals("verde")) {
            if (contadorAnimacion % (velocidadAnimacion * 2) < velocidadAnimacion) {
                sprite = Sprite.PERSONA_VERDE_CAMINANDO_FRENTE_1;
            } else {
                sprite = Sprite.PERSONA_VERDE_CAMINANDO_FRENTE_2;
            }
        } else if (tipo.equals("rosa")) {
            if (contadorAnimacion % (velocidadAnimacion * 2) < velocidadAnimacion) {
                sprite = Sprite.PERSONA_ROSA_CAMINANDO_FRENTE_1;
            } else {
                sprite = Sprite.PERSONA_ROSA_CAMINANDO_FRENTE_2;
            }
        }
        contadorAnimacion++;
    }

    public void moverDerecha() {
        moverSinColision(1, 0);
        direccion = 'e';
      
        if (tipo.equals("verde")) {
            if (contadorAnimacion % (velocidadAnimacion * 2) < velocidadAnimacion) {
                sprite = Sprite.PERSONA_VERDE_PARADO_DER;
            } else {
                sprite = Sprite.PERSONA_VERDE_CAMINANDO_DER_1;
            }
        } else if (tipo.equals("rosa")) {
            if (contadorAnimacion % (velocidadAnimacion * 2) < velocidadAnimacion) {
                sprite = Sprite.PERSONA_ROSA_PARADO_DER;
            } else {
                sprite = Sprite.PERSONA_ROSA_CAMINANDO_DER_1;
            }
        }
        contadorAnimacion++;
    }

    public void moverIzquierda() {
        moverSinColision(-1, 0);
        direccion = 'o';
        
        if (tipo.equals("verde")) {
            if (contadorAnimacion % (velocidadAnimacion * 2) < velocidadAnimacion) {
                sprite = Sprite.PERSONA_VERDE_PARADO_IZQ;
            } else {
                sprite = Sprite.PERSONA_VERDE_CAMINANDO_IZQ_2;
            }
        } else if (tipo.equals("rosa")) {
            if (contadorAnimacion % (velocidadAnimacion * 2) < velocidadAnimacion) {
                sprite = Sprite.PERSONA_ROSA_PARADO_IZQ;
            } else {
                sprite = Sprite.PERSONA_ROSA_CAMINANDO_IZQ_2;
            }
        }
        contadorAnimacion++;
    }

   
    private void moverSinColision(int desplazamientoX, int desplazamientoY) {
        modificarPosicionX(desplazamientoX);
        modificarPosicionY(desplazamientoY);
    }

    @Override
    public void actualizar() {
        moverAlSiguienteNodo();
    }

    @Override
    public void mostrar(pantalla pantalla) {
        pantalla.mostrarJugador(x, y, this);
    }
}
