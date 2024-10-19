/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entes.criatura;

import Graficos.Sprite;
import entes.Ente;

/**
 *
 * @author lucho
 */
public abstract class Criatura extends Ente{
    protected Sprite sprite;
    protected char direccion = 'n';
    protected boolean enMovimiento = false;
    
    public void actualizar(){
        
    }
    
    public void mostrar(){
        
    }
    
    public void mover(int desplazamientoX, int desplazamientoY){
        if(desplazamientoX > 0){
            direccion = 'e';
        }
        if(desplazamientoX<0){
            direccion = 'o';
        }
        if(desplazamientoY>0){
            direccion = 's';
        }
        if(desplazamientoY<0){
            direccion = 'n';
        }
        if(!estaEliminado()){
            if(!enColision(desplazamientoX,0)){
                modificarPosicionX(desplazamientoX);
        }
            if(!enColision(0,desplazamientoY)){
                modificarPosicionY(desplazamientoY);
            }
            
            
            
        }
    }
    
    private boolean enColision(int desplazamientoX, int desplazamientoY){
        boolean colision=false;
        
        int posicionX = x + desplazamientoX;
        int posicionY = y + desplazamientoY;
        
        int margenIzquierdo = -3;
        int margenDerecho = -3;
        
        int margenSuperior = 0;
        int margenInferior = -15;
        
        int bordeIzquierdo = (posicionX + margenDerecho) / sprite.obtenLado();
        int bordeDerecho = (posicionX + margenDerecho + margenIzquierdo) / sprite.obtenLado();
        int bordeSuperior = (posicionY + margenInferior) / sprite.obtenLado();
        int bordeInferior = (posicionY + margenInferior + margenSuperior) / sprite.obtenLado();
        
        System.out.println("Posición X: " + posicionX + " - Posición Y: " + posicionY);
    System.out.println("Borde Izquierdo: " + bordeIzquierdo + " - Borde Derecho: " + bordeDerecho);
    System.out.println("Borde Superior: " + bordeSuperior + " - Borde Inferior: " + bordeInferior);
        
        if(mapa.obtenerCuadroCatalogo(bordeIzquierdo+bordeSuperior * mapa.obtenerAncho()).esSolido()){
            
            colision = true;
        }
        if(mapa.obtenerCuadroCatalogo(bordeIzquierdo+bordeInferior * mapa.obtenerAncho()).esSolido()){
            
            colision = true;
        }
        if(mapa.obtenerCuadroCatalogo(bordeDerecho+bordeSuperior * mapa.obtenerAncho()).esSolido()){
            
            colision = true;
        }
        if(mapa.obtenerCuadroCatalogo(bordeDerecho+bordeInferior * mapa.obtenerAncho()).esSolido()){
            
            colision = true;
        }
        
        System.out.println("Cuadro en (" + bordeIzquierdo + "," + bordeSuperior + "): " + mapa.obtenerCuadroCatalogo(bordeIzquierdo+bordeSuperior * mapa.obtenerAncho()).mostrarNombre());
        
        return colision;
    }
    
    public Sprite obtenSprite(){
        return sprite;
    }
}
