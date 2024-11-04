/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author lucho
 */
public class HojaSprites {

    private final int ancho;
    private final int alto;
    public final int[] pixeles;

    //coleccion de hojas de sprites
    public static HojaSprites desierto = new HojaSprites("/recursos/texturas/HOJA_SPRITES.png", 128, 128);
    public static HojaSprites jugador = new HojaSprites("/recursos/texturas/CARROSPRITE.png", 128, 128);
    // hoja de sprites para las personas
    public static HojaSprites personas = new HojaSprites("/recursos/texturas/people_8px.png", 32, 64);

    
    public static HojaSprites semaforoRojo = new HojaSprites("/recursos/texturas/semaforo_rojo.png", 24, 24); // Ajusta el tamaño según el sprite
    public static HojaSprites semaforoVerde = new HojaSprites("/recursos/texturas/semaforo_verde.png", 24, 24); // Ajusta el tamaño según el sprite

    //fin de la coleccion
    public int obtenAncho() {
        return ancho;
    }

    public int obtenAlto() {
        return alto;
    }

    public HojaSprites(final String ruta, final int ancho, final int alto) {
        this.ancho = ancho;
        this.alto = alto;

        pixeles = new int[ancho * alto];
        BufferedImage imagen;
        try {
            imagen = ImageIO.read(HojaSprites.class.getResource(ruta));
            imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
