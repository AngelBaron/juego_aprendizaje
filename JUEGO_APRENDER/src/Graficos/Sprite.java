/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graficos;

/**
 *
 * @author lucho
 */
public class Sprite {
    private final int lado;
    
    private int x;
    private int y;
    
    public int[] pixeles;
    private HojaSprites hoja;
    
    //Coleccion de sprites del carro
    public static final Sprite IZQ  = new Sprite(32,0,0,HojaSprites.jugador);
    public static final Sprite DER  = new Sprite(32,1,0,HojaSprites.jugador);
    public static final Sprite ARR  = new Sprite(32,0,1,HojaSprites.jugador);
    public static final Sprite ABA  = new Sprite(32,1,1,HojaSprites.jugador);
    
    //Fin de la coleccion
    
    
    
    //coleccion de sprites del mapa 
    public static final Sprite VACIO = new Sprite(16,0);
    public static final Sprite ASFALTO = new Sprite(16,1,0,HojaSprites.desierto);
    public static final Sprite BANQUETA = new Sprite(16,0,0,HojaSprites.desierto);
    public static final Sprite CALLE_MA = new Sprite(16,2,0,HojaSprites.desierto);
    public static final Sprite CALLE_Ma = new Sprite(16,3,0,HojaSprites.desierto);
    public static final Sprite BANQUETA_N = new Sprite(16,4,0,HojaSprites.desierto);
    public static final Sprite PASTO_E_SI = new Sprite(16,5,0,HojaSprites.desierto);
    public static final Sprite PASTO_L_I = new Sprite(16,6,0,HojaSprites.desierto);
    public static final Sprite PASTO_E_II = new Sprite(16,7,0,HojaSprites.desierto);
    public static final Sprite PASTO_L_S = new Sprite(16,0,1,HojaSprites.desierto);
    public static final Sprite PASTO_L_a = new Sprite(16,1,1,HojaSprites.desierto);
    public static final Sprite PASTO_E_S_D = new Sprite(16,2,1,HojaSprites.desierto);
    public static final Sprite PASTO_L_D = new Sprite(16,3,1,HojaSprites.desierto);
    public static final Sprite PASTO_E_I_D = new Sprite(16,4,1,HojaSprites.desierto);
    public static final Sprite PASTO_N = new Sprite(16,5,1,HojaSprites.desierto);
    public static final Sprite FLECHA_ABAJO = new Sprite(16,6,1,HojaSprites.desierto);
    public static final Sprite FLECHA_ARRIBA = new Sprite(16,7,1,HojaSprites.desierto);
    public static final Sprite PASO_HOR = new Sprite(16,0,2,HojaSprites.desierto);
    public static final Sprite PASO_VER = new Sprite(16,1,2,HojaSprites.desierto);
    public static final Sprite FLECHA_IZ = new Sprite(16,2,2,HojaSprites.desierto);
    public static final Sprite FLECHA_DER = new Sprite(16,3,2,HojaSprites.desierto);
    
    //Fin de la coleccion
    
    public Sprite(int lado, final int columna, final int fila, final HojaSprites hoja){
        this.lado = lado;
        this.pixeles = new int[this.lado * this.lado];
        
        this.x = columna * lado;
        
        this.y = fila * lado;
        
        this.hoja = hoja;
        
        for(int y = 0; y<lado; y++){
            for(int x = 0; x<lado; x++){
                pixeles[x+y * lado] = hoja.pixeles[(x + this.x) + (y+this.y) * hoja.obtenAncho()];
            }
        }
        
    }
    
    public Sprite(final int lado, final int color){
        this.lado = lado;
        pixeles = new int[lado * lado];
        
        for(int i = 0; i< pixeles.length; i++){
            pixeles[i] = color;
        }
    }
    
    public int obtenLado(){
        return lado;
    }
}
