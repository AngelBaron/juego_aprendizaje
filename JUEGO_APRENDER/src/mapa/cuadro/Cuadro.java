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
    
    private String nombre;
    
    public Sprite sprite;
    
    private boolean solido;
    
    public static final int LADO = 16;
    
    //Coleccion de cuadros
    public static final Cuadro VACIO = new Cuadro(Sprite.VACIO,true, "vacio");
    public static final Cuadro ASFALTO = new Cuadro(Sprite.ASFALTO,"asfalto");
    public static final Cuadro BANQUETA = new Cuadro(Sprite.BANQUETA,true,"banqueta");
    public static final Cuadro CALLE_MA = new Cuadro(Sprite.CALLE_MA,"calle_MA");
    public static final Cuadro CALLE_Ma = new Cuadro(Sprite.CALLE_Ma,"calle_Ma");
    public static final Cuadro BANQUETA_N = new Cuadro(Sprite.BANQUETA_N,true,"Banqueta_N");
    public static final Cuadro PASTO_E_SI = new Cuadro(Sprite.PASTO_E_SI,"PASTO_E_SI");
    public static final Cuadro PASTO_L_I = new Cuadro(Sprite.PASTO_L_I,"PASTO_L_I");
    public static final Cuadro PASTO_E_II = new Cuadro(Sprite.PASTO_E_II,"PASTO_E_II");
    public static final Cuadro PASTO_L_S = new Cuadro(Sprite.PASTO_L_S,"PASTO_L_S");
    public static final Cuadro PASTO_L_a = new Cuadro(Sprite.PASTO_L_a,"PASTO_L_a");
    public static final Cuadro PASTO_E_S_D = new Cuadro(Sprite.PASTO_E_S_D,"PASTO_E_S_D");
    public static final Cuadro PASTO_L_D = new Cuadro(Sprite.PASTO_L_D,"PASTO_L_D");
    public static final Cuadro PASTO_E_I_D = new Cuadro(Sprite.PASTO_E_I_D,"PASTO_E_I_D");
    public static final Cuadro PASTO_N = new Cuadro(Sprite.PASTO_N,"PASTO_L_N");
    public static final Cuadro FLECHA_ABAJO = new Cuadro(Sprite.FLECHA_ABAJO,"FLECHA_ABAJO");
    public static final Cuadro FLECHA_ARRIBA = new Cuadro(Sprite.FLECHA_ARRIBA,"FLECHA_ARRIBA");
    public static final Cuadro PASO_HOR = new Cuadro(Sprite.PASO_HOR,"PASO_HOR");
    public static final Cuadro PASO_VER = new Cuadro(Sprite.PASO_VER,"PASO_VER");
    public static final Cuadro FLECHA_IZ = new Cuadro(Sprite.FLECHA_IZ,"FLECHA_IZ");
    public static final Cuadro FLECHA_DER = new Cuadro(Sprite.FLECHA_DER,"FLECHA_DER");
    //Fin de la coleccion de cuadros
    
    public Cuadro(Sprite sprite){
        this.sprite = sprite;
        solido = false;
    }
    
    public Cuadro (Sprite sprite, boolean solido, String nombre){
        this.sprite = sprite;
        this.solido = solido;
        this.nombre= nombre;
    }
    
    public Cuadro(Sprite sprite, String nombre){
        this.sprite = sprite;
        solido = false;
        this.nombre = nombre;
    }
    
    
    public void mostrar(int x, int y, pantalla pantalla){
        pantalla.mostrarCuadro(x<<4, y<<4, this);
    }
    
    public String mostrarNombre(){
        
        return nombre;
    }
    
    public boolean esSolido(){
        return solido;
    }
}
