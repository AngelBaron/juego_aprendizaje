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
    public static final Sprite IZQ = new Sprite(32, 0, 0, HojaSprites.jugador);
    public static final Sprite DER = new Sprite(32, 1, 0, HojaSprites.jugador);
    public static final Sprite ARR = new Sprite(32, 0, 1, HojaSprites.jugador);
    public static final Sprite ABA = new Sprite(32, 1, 1, HojaSprites.jugador);

    // Agrega estos sprites para el semáforo en rojo y verde
    public static final Sprite SEMAFORO_ROJO = new Sprite(24, 0, 0, HojaSprites.semaforoRojo); // Configura la posición y hoja correcta
    public static final Sprite SEMAFORO_VERDE = new Sprite(24, 0, 0, HojaSprites.semaforoVerde); // Configura la posición y hoja correcta

    //Fin de la coleccion
    // Personaje Verde
    public static final Sprite PERSONA_VERDE_PARADO_DER = new Sprite(8, 0, 0, HojaSprites.personas);  // Parado mirando a la derecha
    public static final Sprite PERSONA_VERDE_PARADO_FRENTE = new Sprite(8, 0, 1, HojaSprites.personas);  // Parado mirando al frente
    public static final Sprite PERSONA_VERDE_PARADO_IZQ = new Sprite(8, 0, 2, HojaSprites.personas);  // Parado mirando a la izquierda
    public static final Sprite PERSONA_VERDE_PARADO_ATRAS = new Sprite(8, 0, 3, HojaSprites.personas);  // Parado mirando hacia atrás

    public static final Sprite PERSONA_VERDE_CAMINANDO_IZQ_1 = new Sprite(8, 1, 0, HojaSprites.personas);  // Caminando hacia la izquierda
    public static final Sprite PERSONA_VERDE_CAMINANDO_FRENTE_1 = new Sprite(8, 1, 1, HojaSprites.personas);  // Caminando hacia el frente
    public static final Sprite PERSONA_VERDE_CAMINANDO_DER_1 = new Sprite(8, 1, 2, HojaSprites.personas);  // Caminando hacia la derecha
    public static final Sprite PERSONA_VERDE_CAMINANDO_ATRAS_1 = new Sprite(8, 1, 3, HojaSprites.personas);  // Caminando hacia atrás

    public static final Sprite PERSONA_VERDE_PARADO_DER_2 = new Sprite(8, 2, 0, HojaSprites.personas);  // Otra pose parado mirando a la derecha
    public static final Sprite PERSONA_VERDE_PARADO_FRENTE_2 = new Sprite(8, 2, 1, HojaSprites.personas);  // Otra pose parado mirando al frente
    public static final Sprite PERSONA_VERDE_PARADO_IZQ_2 = new Sprite(8, 2, 2, HojaSprites.personas);  // Otra pose parado mirando a la izquierda
    public static final Sprite PERSONA_VERDE_PARADO_ATRAS_2 = new Sprite(8, 2, 3, HojaSprites.personas);  // Otra pose parado mirando hacia atrás

    public static final Sprite PERSONA_VERDE_CAMINANDO_DER_2 = new Sprite(8, 3, 0, HojaSprites.personas);  // Caminando hacia la derecha
    public static final Sprite PERSONA_VERDE_CAMINANDO_FRENTE_2 = new Sprite(8, 3, 1, HojaSprites.personas);  // Caminando hacia el frente
    public static final Sprite PERSONA_VERDE_CAMINANDO_IZQ_2 = new Sprite(8, 3, 2, HojaSprites.personas);  // Caminando hacia la izquierda
    public static final Sprite PERSONA_VERDE_CAMINANDO_ATRAS_2 = new Sprite(8, 3, 3, HojaSprites.personas);  // Caminando hacia atrás

    // Personaje Rosa
    public static final Sprite PERSONA_ROSA_PARADO_DER = new Sprite(8, 0, 4, HojaSprites.personas);  // Parado mirando a la derecha
    public static final Sprite PERSONA_ROSA_PARADO_FRENTE = new Sprite(8, 0, 5, HojaSprites.personas);  // Parado mirando al frente
    public static final Sprite PERSONA_ROSA_PARADO_IZQ = new Sprite(8, 0, 6, HojaSprites.personas);  // Parado mirando a la izquierda
    public static final Sprite PERSONA_ROSA_PARADO_ATRAS = new Sprite(8, 0, 7, HojaSprites.personas);  // Parado mirando hacia atrás

    public static final Sprite PERSONA_ROSA_CAMINANDO_IZQ_1 = new Sprite(8, 1, 4, HojaSprites.personas);  // Caminando hacia la izquierda
    public static final Sprite PERSONA_ROSA_CAMINANDO_FRENTE_1 = new Sprite(8, 1, 5, HojaSprites.personas);  // Caminando hacia el frente
    public static final Sprite PERSONA_ROSA_CAMINANDO_DER_1 = new Sprite(8, 1, 6, HojaSprites.personas);  // Caminando hacia la derecha
    public static final Sprite PERSONA_ROSA_CAMINANDO_ATRAS_1 = new Sprite(8, 1, 7, HojaSprites.personas);  // Caminando hacia atrás

    public static final Sprite PERSONA_ROSA_PARADO_DER_2 = new Sprite(8, 2, 4, HojaSprites.personas);  // Otra pose parado mirando a la derecha
    public static final Sprite PERSONA_ROSA_PARADO_FRENTE_2 = new Sprite(8, 2, 5, HojaSprites.personas);  // Otra pose parado mirando al frente
    public static final Sprite PERSONA_ROSA_PARADO_IZQ_2 = new Sprite(8, 2, 6, HojaSprites.personas);  // Otra pose parado mirando a la izquierda
    public static final Sprite PERSONA_ROSA_PARADO_ATRAS_2 = new Sprite(8, 2, 7, HojaSprites.personas);  // Otra pose parado mirando hacia atrás

    public static final Sprite PERSONA_ROSA_CAMINANDO_DER_2 = new Sprite(8, 3, 4, HojaSprites.personas);  // Caminando hacia la derecha
    public static final Sprite PERSONA_ROSA_CAMINANDO_FRENTE_2 = new Sprite(8, 3, 5, HojaSprites.personas);  // Caminando hacia el frente
    public static final Sprite PERSONA_ROSA_CAMINANDO_IZQ_2 = new Sprite(8, 3, 6, HojaSprites.personas);  // Caminando hacia la izquierda
    public static final Sprite PERSONA_ROSA_CAMINANDO_ATRAS_2 = new Sprite(8, 3, 7, HojaSprites.personas);  // Caminando hacia atrás

    //coleccion de sprites del mapa 
    public static final Sprite VACIO = new Sprite(16, 0);
    public static final Sprite VACIO2 = new Sprite(16, 0x00000000);
    public static final Sprite ASFALTO = new Sprite(16, 1, 0, HojaSprites.desierto);
    public static final Sprite BANQUETA = new Sprite(16, 0, 0, HojaSprites.desierto);
    public static final Sprite CALLE_MA = new Sprite(16, 2, 0, HojaSprites.desierto);
    public static final Sprite CALLE_Ma = new Sprite(16, 3, 0, HojaSprites.desierto);
    public static final Sprite BANQUETA_N = new Sprite(16, 4, 0, HojaSprites.desierto);
    public static final Sprite PASTO_E_SI = new Sprite(16, 5, 0, HojaSprites.desierto);
    public static final Sprite PASTO_L_I = new Sprite(16, 6, 0, HojaSprites.desierto);
    public static final Sprite PASTO_E_II = new Sprite(16, 7, 0, HojaSprites.desierto);
    public static final Sprite PASTO_L_S = new Sprite(16, 0, 1, HojaSprites.desierto);
    public static final Sprite PASTO_L_a = new Sprite(16, 1, 1, HojaSprites.desierto);
    public static final Sprite PASTO_E_S_D = new Sprite(16, 2, 1, HojaSprites.desierto);
    public static final Sprite PASTO_L_D = new Sprite(16, 3, 1, HojaSprites.desierto);
    public static final Sprite PASTO_E_I_D = new Sprite(16, 4, 1, HojaSprites.desierto);
    public static final Sprite PASTO_N = new Sprite(16, 5, 1, HojaSprites.desierto);
    public static final Sprite FLECHA_ABAJO = new Sprite(16, 6, 1, HojaSprites.desierto);
    public static final Sprite FLECHA_ARRIBA = new Sprite(16, 7, 1, HojaSprites.desierto);
    public static final Sprite PASO_HOR = new Sprite(16, 0, 2, HojaSprites.desierto);
    public static final Sprite PASO_VER = new Sprite(16, 1, 2, HojaSprites.desierto);
    public static final Sprite FLECHA_IZ = new Sprite(16, 2, 2, HojaSprites.desierto);
    public static final Sprite FLECHA_DER = new Sprite(16, 3, 2, HojaSprites.desierto);

    //Fin de la coleccion
    public Sprite(int lado, final int columna, final int fila, final HojaSprites hoja) {
        this.lado = lado;
        this.pixeles = new int[this.lado * this.lado];

        this.x = columna * lado;

        this.y = fila * lado;

        this.hoja = hoja;

        for (int y = 0; y < lado; y++) {
            for (int x = 0; x < lado; x++) {
                pixeles[x + y * lado] = hoja.pixeles[(x + this.x) + (y + this.y) * hoja.obtenAncho()];
            }
        }

    }
    
    

    public void ModificarSprite(final int color) {
        pixeles = new int[lado * lado];

        for (int i = 0; i < pixeles.length; i++) {
            pixeles[i] = color;
        }
    }

    public Sprite(final int lado, final int color) {
        this.lado = lado;
        pixeles = new int[lado * lado];

        for (int i = 0; i < pixeles.length; i++) {
            pixeles[i] = color;
        }
    }

    public int obtenLado() {
        return lado;
    }
}
