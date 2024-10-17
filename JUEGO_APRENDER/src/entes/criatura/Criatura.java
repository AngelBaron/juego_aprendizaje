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
            modificarPosicionX(desplazamientoX);
            modificarPosicionY(desplazamientoY);
        }
    }
    
    private boolean enColision(){
        return false;
    }
    
    public Sprite obtenSprite(){
        return sprite;
    }
}
