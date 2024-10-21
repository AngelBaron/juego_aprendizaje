/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semaforos;

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
            // Permitir paso en sentido 1
            synchronized(this) {
                verdeSentido1 = true;
                verdeSentido2 = false;
            }
            Thread.sleep(tiempoVerdeSentido1);

            // Permitir paso en sentido 2
            synchronized(this) {
                verdeSentido1 = false;
                verdeSentido2 = true;
            }
            Thread.sleep(tiempoVerdeSentido2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
