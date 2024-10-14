/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa.cuadro;

import Graficos.HojaSprites;
import Graficos.Sprite;
import Graficos.pantalla;

/**
 *
 * @author lucho
 */
public class Cuadro {
    public int x;
    public int y;
    
    public Sprite sprite;
    
    public static final int LADO = 16;
    
    //Coleccion de cuadros
    public static final Cuadro VACIO = new Cuadro(Sprite.VACIO);
    public static final Cuadro ASFALTO = new Cuadro(Sprite.ASFALTO);
    public static final Cuadro BANQUETA = new Cuadro(Sprite.BANQUETA);
    public static final Cuadro CALLE_MA = new Cuadro(Sprite.CALLE_MA);
    public static final Cuadro CALLE_Ma = new Cuadro(Sprite.CALLE_Ma);
    public static final Cuadro BANQUETA_N = new Cuadro(Sprite.BANQUETA_N);
    public static final Cuadro PASTO_E_SI = new Cuadro(Sprite.PASTO_E_SI);
    public static final Cuadro PASTO_L_I = new Cuadro(Sprite.PASTO_L_I);
    public static final Cuadro PASTO_E_II = new Cuadro(Sprite.PASTO_E_II);
    public static final Cuadro PASTO_L_S = new Cuadro(Sprite.PASTO_L_S);
    public static final Cuadro PASTO_L_a = new Cuadro(Sprite.PASTO_L_a);
    public static final Cuadro PASTO_E_S_D = new Cuadro(Sprite.PASTO_E_S_D);
    public static final Cuadro PASTO_L_D = new Cuadro(Sprite.PASTO_L_D);
    public static final Cuadro PASTO_E_I_D = new Cuadro(Sprite.PASTO_E_I_D);
    public static final Cuadro PASTO_N = new Cuadro(Sprite.PASTO_N);
    public static final Cuadro FLECHA_ABAJO = new Cuadro(Sprite.FLECHA_ABAJO);
    public static final Cuadro FLECHA_ARRIBA = new Cuadro(Sprite.FLECHA_ARRIBA);
    public static final Cuadro PASO_HOR = new Cuadro(Sprite.PASO_HOR);
    public static final Cuadro PASO_VER = new Cuadro(Sprite.PASO_VER);
    public static final Cuadro FLECHA_IZ = new Cuadro(Sprite.FLECHA_IZ);
    public static final Cuadro FLECHA_DER = new Cuadro(Sprite.FLECHA_DER);
    //Fin de la coleccion de cuadros
    
    public Cuadro(Sprite sprite){
        this.sprite = sprite;
    }
    
    public void mostrar(int x, int y, pantalla pantalla){
        pantalla.mostrarCuadro(x<<4, y<<4, this);
    }
    
    public boolean solido(){
        return false;
    }
}
