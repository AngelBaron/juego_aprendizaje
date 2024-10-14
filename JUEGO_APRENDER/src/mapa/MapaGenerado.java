/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa;

import java.util.Random;

/**
 *
 * @author lucho
 */
public class MapaGenerado extends Mapa{
    
    private static final Random aleatorio = new Random();
    
    public MapaGenerado(int ancho, int alto) {
        super(ancho, alto);
    }
    
    @Override
    protected void generarMapa(){
        for(int y=0; y<alto;y++){
            for(int x= 0;x<ancho;x++){
                cuadros[x + y * ancho] = aleatorio.nextInt(20);
            }
        }
    }
    
}
