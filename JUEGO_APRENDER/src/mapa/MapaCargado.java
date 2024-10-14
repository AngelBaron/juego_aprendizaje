/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import mapa.cuadro.Cuadro;

/**
 *
 * @author lucho
 */
public class MapaCargado extends Mapa{
    
    private int[] pixeles;
    
    public MapaCargado(String ruta) {
        super(ruta);
    }
    
    @Override
    protected void cargarMapa(String ruta){
        try {
            BufferedImage imagen = ImageIO.read(MapaCargado.class.getResource(ruta));
            ancho = imagen.getWidth();
            alto = imagen.getHeight();
            
            cuadrosCatalogo = new Cuadro[ancho * alto];
            pixeles = new int[ancho*alto];
            
            imagen.getRGB(0, 0,ancho,alto,pixeles,0,ancho);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    protected void generarMapa(){
        for(int i=0; i<pixeles.length; i++){
            switch(pixeles[i]){
                case 0xff9daaab: 
                    cuadrosCatalogo[i] = Cuadro.BANQUETA;
                continue;
                case 0xff404040:
                    cuadrosCatalogo[i] = Cuadro.ASFALTO;
                continue;
                case 0xffd6dbe6:
                    cuadrosCatalogo[i] = Cuadro.CALLE_MA;
                    continue;
                case 0xff9d9ea1:
                    cuadrosCatalogo[i] = Cuadro.CALLE_Ma;
                    continue;
                case 0xff737c7c:
                    cuadrosCatalogo[i] = Cuadro.BANQUETA_N;
                    continue;
                case 0xffb48355:
                    cuadrosCatalogo[i] = Cuadro.PASTO_E_SI;
                    continue;
                case 0xffb19576:
                    cuadrosCatalogo[i] = Cuadro.PASTO_L_I;
                    continue;
                case 0xffc08c5b:
                    cuadrosCatalogo[i] = Cuadro.PASTO_E_II;
                    continue;
                case 0xff3d905b:
                    cuadrosCatalogo[i] = Cuadro.PASTO_L_S;
                    continue;
                case 0xff4db071:
                    cuadrosCatalogo[i] = Cuadro.PASTO_L_a;
                    continue;
                case 0xff2a7e48:
                    cuadrosCatalogo[i] = Cuadro.PASTO_E_S_D;
                    continue;
                case 0xff22b456:
                    cuadrosCatalogo[i] = Cuadro.PASTO_L_D;
                    continue;
                case 0xff528564:
                    cuadrosCatalogo[i] = Cuadro.PASTO_E_I_D;
                    continue;
                case 0xff166533:
                    cuadrosCatalogo[i] = Cuadro.PASTO_N;
                    continue;
                case 0xff96aff4:
                    cuadrosCatalogo[i] = Cuadro.FLECHA_ABAJO;
                    continue;
                case 0xff617ed1:
                    cuadrosCatalogo[i] = Cuadro.FLECHA_ARRIBA;
                    continue;
                case 0xffc3d188:
                    cuadrosCatalogo[i] = Cuadro.PASO_HOR;
                    continue;
                case 0xffabc051:
                    cuadrosCatalogo[i] = Cuadro.PASO_VER;
                    continue;    
                case 0xff343f62:
                    cuadrosCatalogo[i] = Cuadro.FLECHA_IZ;
                    continue; 
                case 0xff465580:
                    cuadrosCatalogo[i] = Cuadro.FLECHA_DER;
                    continue;
                case 0xff3a8c58:
                    cuadrosCatalogo[i] = Cuadro.PASTO_L_S;
                    continue;
                case 0xff21ae53:
                    cuadrosCatalogo[i] = Cuadro.PASTO_L_D;
                    continue;
                case 0xffa99171:
                    cuadrosCatalogo[i] = Cuadro.PASTO_L_I;
                    continue;
                case 0xff22b356:
                    cuadrosCatalogo[i] = Cuadro.PASTO_L_D;
                    continue;
                
                default:
                    cuadrosCatalogo[i] = Cuadro.VACIO;
                    
                
                
        }
        }
    }
    
}
