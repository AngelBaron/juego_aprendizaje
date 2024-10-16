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
    private int x;
    private int y;
    
    private boolean eliminado = false;
    
    protected Mapa mapa;
    
    public void actualizar(){
        
    }
    
    public void mostrar(){
        
    }
    
    public void eliminar(){
        eliminado = true;
    }
    
    public int posicionX(){
        return x;
    }
    
    public int posicionY(){
        return y;
    }
    
    public boolean estaEliminado(){
        return eliminado;
    }
    
}
