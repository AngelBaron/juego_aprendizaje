/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa;

import Graficos.pantalla;
import mapa.cuadro.Cuadro;

/**
 *
 * @author lucho
 */
public abstract class Mapa {
    protected int ancho;
    protected int alto;
    
    protected int[] cuadros;
    protected Cuadro[] cuadrosCatalogo;
    
    public Mapa(int ancho, int alto){
        this.ancho = ancho;
        this.alto = alto;
        
        cuadros = new int[ancho*alto];
        generarMapa();
    }
    
    public Mapa(String ruta){
        cargarMapa(ruta);
        generarMapa();
    }
    
    
    
    
    
    
    protected void generarMapa(){
        
    }
    
    protected void cargarMapa(String ruta){
        
    }
     
    public void actualizar(){
        
    }
    
    public void mostrar(int compensacionX,int compensacionY, pantalla pantalla){
        pantalla.estableceDiferencia(compensacionX, compensacionY);
        int o = compensacionX >> 4;
        int e = (compensacionX + pantalla.obtenAncho() + Cuadro.LADO)>>4;
        int n = compensacionY>>4;
        int s = (compensacionY + pantalla.obtenAlto() + Cuadro.LADO)>>4;
        
        for(int y= n ; y<s;y++){
            for(int x= o; x<e;x++){
                
               // obtenCuadro(x, y).mostrar(x, y, pantalla);
               if(x<0||y<0||x>=ancho||y>=alto){
                   Cuadro.VACIO.mostrar(x, y, pantalla);
               }else{
                   cuadrosCatalogo[x + y*ancho].mostrar(x, y, pantalla);
               }
            }
        }
    }
    
    public Cuadro obtenCuadro(final int x, final int y){
        //if(x< 0 || y<0 || x>=ancho || y>=alto){
          //  return Cuadro.VACIO;
        //}
        switch(cuadros[x+y*ancho]){
            case 0: 
                return Cuadro.ASFALTO;
            case 1: return Cuadro.BANQUETA;
            case 2: return Cuadro.CALLE_MA;
            case 3: return Cuadro.CALLE_Ma;
            case 4: return Cuadro.BANQUETA_N;
            case 5: return Cuadro.PASTO_E_SI;
            case 6:return Cuadro.PASTO_L_I;
            case 7: return Cuadro.PASTO_E_II;
            case 8: return Cuadro.PASTO_L_S;
            case 9: return Cuadro.PASTO_L_a;
            case 10: return Cuadro.PASTO_E_S_D;
            case 11:return Cuadro.PASTO_L_D;
            case 12: return Cuadro.PASTO_E_I_D;
            case 13: return Cuadro.PASTO_N;
            case 14: return Cuadro.FLECHA_ABAJO;
            case 15: return Cuadro.FLECHA_ARRIBA;
            case 16:return Cuadro.PASO_HOR;
            case 17: return Cuadro.PASO_VER;
            case 18: return Cuadro.FLECHA_IZ;
            case 19: return Cuadro.FLECHA_DER;
            default:
                return Cuadro.VACIO;
        }
        
        
    }
    
    public Cuadro obtenerCuadroCatalogo(int posicion){
        
        return cuadrosCatalogo[posicion];
    }
    
    public int obtenerAncho(){
        return ancho;
    }
    
}
