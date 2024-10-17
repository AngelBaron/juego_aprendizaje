/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entes;

import mapa.Mapa;

/**
 *
 * @author lucho
 */
public abstract class Ente {
    protected int x;
    protected int y;
    
    private boolean eliminado = false;
    
    protected Mapa mapa;
    
    public void actualizar(){
        
    }
    
    public void mostrar(){
        
    }
    
    public void eliminar(){
        eliminado = true;
    }
    
    public int obtenerPosicionX(){
        return x;
    }
    
    public void modificarPosicionX(int desplazamientoX){
        x += desplazamientoX;
    }
    
    public int obtenerPosicionY(){
        return y;
    }
    
    public void modificarPosicionY(int desplazamientoY){
        y += desplazamientoY;
    }
    
    public boolean estaEliminado(){
        return eliminado;
    }
    
    
    
    
}
