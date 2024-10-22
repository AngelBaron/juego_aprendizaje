/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entes.criatura;

import Graficos.Sprite;
import Graficos.pantalla;
import entes.Ente;
import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author lucho
 */
public abstract class Criatura extends Ente{
    protected Sprite sprite;
    protected char direccion = 'n';
    protected boolean enMovimiento = false;
    protected boolean esSolido = false;
    
    public abstract void mostrar(pantalla pantalla);
    int margenIzquierdo = -9;
        int margenDerecho = 24;
        
        int margenSuperior = -3;
        int margenInferior = 24;
        
    private static ArrayList<Criatura> criaturas = new ArrayList<>();

    public Criatura() {
        // Agregar la nueva criatura a la lista
        System.out.println("SE AÑADE CRIATURA");
        criaturas.add(this);
    }

    public void eliminar() {
        // Eliminar la criatura de la lista si es eliminada
        criaturas.remove(this);
        super.eliminar();
    }

    public static ArrayList<Criatura> obtenerTodasLasCriaturas() {
        System.out.println(criaturas);
        
        return criaturas;
    }
    
    public void actualizar(){
        
    }
    
    public void mostrar(){
        
    }
    
    public void eliminarSprite(){
        sprite = Sprite.VACIO2;
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
        
        
        
        int bordeIzquierdo = (posicionX + margenDerecho) /16 ;
        int bordeDerecho = (posicionX + margenDerecho + margenIzquierdo) /16 ;
        int bordeSuperior = (posicionY + margenInferior)/16;
        int bordeInferior = (posicionY + margenInferior + margenSuperior)/16 ;
        
      //  System.out.println("BORDEIZsdaQ" + bordeIzquierdo + "|| BBORDEDERECHO" + bordeDerecho + "|| BORDESUPERIOR" + bordeSuperior + "|| BORDEINFERIOR" + bordeInferior);
        
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
        
        for (Criatura otraCriatura : criaturas) {
            if (otraCriatura != this && otraCriatura.esSolido) {
                int otraX = otraCriatura.x;
                int otraY = otraCriatura.y;

                if (posicionX + margenDerecho > otraX + otraCriatura.margenIzquierdo &&
                    posicionX + margenIzquierdo < otraX + otraCriatura.margenDerecho &&
                    posicionY + margenInferior > otraY + otraCriatura.margenSuperior &&
                    posicionY + margenSuperior < otraY + otraCriatura.margenInferior) {
                    colision = true;
                      // Si ya hay colisión, no necesitas seguir comprobando
                }
            }
        
        
        
        
       // System.out.println(posicionX + "||" + posicionY);
        //System.out.println("Cuadro en (" + bordeIzquierdo + "," + bordeSuperior + "): " + mapa.obtenerCuadroCatalogo(bordeIzquierdo+bordeSuperior * mapa.obtenerAncho()).mostrarNombre());
        }
        return colision;
    }
    
    
    
    
    
    public Sprite obtenSprite(){
        return sprite;
    }
}
