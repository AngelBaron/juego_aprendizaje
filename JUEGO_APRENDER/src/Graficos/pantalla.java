/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graficos;

import entes.criatura.Jugador;
import mapa.cuadro.Cuadro;

/**
 *
 * @author lucho
 */
public class pantalla {
    
    private final int ancho;
    private final int alto;
    
    private int diferenciaX;
    private int diferenciaY;
    
    public final int[] pixeles;
    

    
    public pantalla(final int ancho, final int alto){
        this.ancho = ancho;
        this.alto = alto;
        
        pixeles = new int[ancho*alto];
    }
    
    public void limpiar(){
        for(int i =0; i<pixeles.length; i++){
            pixeles[i] = 0;
        }
    }

    
    
    public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro){
        compensacionX -= diferenciaX;
        compensacionY -= diferenciaY ;
        for(int y=0; y < cuadro.sprite.obtenLado(); y++){
            int posicionY = y+compensacionY;
            for(int x = 0; x<cuadro.sprite.obtenLado(); x++){
                int posicionX = x + compensacionX;
                if(posicionX<-cuadro.sprite.obtenLado() || posicionX>=ancho|| posicionY<0 || posicionY>=alto){
                    break;
                }
                if(posicionX < 0){
                    posicionX = 0;
                }
                pixeles[posicionX + posicionY * ancho] = cuadro.sprite.pixeles[x+y * cuadro.sprite.obtenLado()];
            }
        }
    }
    
    public void mostrarJugador(int compensacionX, int compensacionY, Jugador jugador){
        compensacionX -= diferenciaX;
        compensacionY -= diferenciaY ;
        for(int y=0; y < jugador.obtenSprite().obtenLado(); y++){
            int posicionY = y+compensacionY;
            for(int x = 0; x<jugador.obtenSprite().obtenLado(); x++){
                int posicionX = x + compensacionX;
                if(posicionX<-jugador.obtenSprite().obtenLado() || posicionX>=ancho|| posicionY<0 || posicionY>=alto){
                    break;
                }
                if(posicionX < 0){
                    posicionX = 0;
                }
                //pixeles[posicionX + posicionY * ancho] = jugador.obtenSprite().pixeles[x+y * jugador.obtenSprite().obtenLado()];
                int colorPixelJugador = jugador.obtenSprite().pixeles[x + y * jugador.obtenSprite().obtenLado()];
                if(colorPixelJugador != 0xffbc0000){
                    pixeles[posicionX + posicionY * ancho] = colorPixelJugador;
                }
            }
        }
    }
    
    public void estableceDiferencia(int diferenciaX, int diferenciaY){
        this.diferenciaX = diferenciaX;
        this.diferenciaY = diferenciaY;
    }
    
    public int obtenAncho(){
        return ancho;
       
    }
    
    public int obtenAlto(){
        return alto;
    }
    
    
}
