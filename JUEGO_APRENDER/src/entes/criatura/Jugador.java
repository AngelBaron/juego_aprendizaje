/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entes.criatura;

import Graficos.Sprite;
import Graficos.pantalla;
import control.teclado;
import mapa.Mapa;

/**
 *
 * @author lucho
 */
public class Jugador extends Criatura{
    private teclado teclado;
    
    public Jugador(Mapa mapa,teclado teclado, Sprite sprite){
        super();
        this.mapa = mapa;
        this.teclado = teclado;
        this.sprite = sprite;
        this.esSolido=true;
    }
    
    public Jugador(Mapa mapa,teclado teclado,Sprite sprite, int posicionX, int posicionY){
        super();
        this.esSolido=true;
        this.mapa = mapa;
        this.teclado = teclado;
        this.x = posicionX;
        this.y = posicionY;
        this.sprite = sprite;
        
    }
    
    public void actualizar(){
        int desplazamientoX = 0;
        int desplazamientoY=0;
        
        if(teclado.arriba){
            desplazamientoY--;
        }
        if (teclado.abajo){
            desplazamientoY++;
        }
        if(teclado.izquierda){
            desplazamientoX--;
        }
        if(teclado.derecha){
            desplazamientoX++;
        }
        
        if(desplazamientoX != 0 || desplazamientoY!=0){
            
             if(direccion =='n'){
                sprite = Sprite.ARR;
            }
            
            if(direccion =='s'){
                sprite = Sprite.ABA;
            }
            
            if(direccion == 'o'){
                sprite = Sprite.IZQ;
            }
            if(direccion == 'e'){
                sprite = Sprite.DER;
            }
            mover(desplazamientoX,desplazamientoY);
        }
    }
    
    public void mostrar(pantalla pantalla){
        pantalla.mostrarJugador(x, y, this);
    }
}
