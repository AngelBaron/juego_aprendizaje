/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semaforos;

import Graficos.Sprite;

/**
 *
 * @author lucho
 */
public class Semaforo1 extends Semaforo {

    public Semaforo1(int tiempoVerdeSentido1, int tiempoVerdeSentido2) {
        super(tiempoVerdeSentido1, tiempoVerdeSentido2);
    }

    @Override
    public void cambiarEstado() {
        try {
            // Cambiar a verde para el sentido 1
            synchronized (this) {
                verdeSentido1 = true;
                verdeSentido2 = false;
                spriteActual = Sprite.SEMAFORO_VERDE; // Cambiar a verde
                spriteActual2 = Sprite.SEMAFORO_ROJO; // Cambiar a verde
            }
            Thread.sleep(tiempoVerdeSentido1);

            // Cambiar a rojo para el sentido 2
            synchronized (this) {
                verdeSentido1 = false;
                verdeSentido2 = true;
                spriteActual = Sprite.SEMAFORO_ROJO; // Cambiar a rojo
                spriteActual2 = Sprite.SEMAFORO_VERDE; // Cambiar a verde
            }
            Thread.sleep(tiempoVerdeSentido2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
