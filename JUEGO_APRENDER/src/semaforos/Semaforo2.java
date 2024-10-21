/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semaforos;

/**
 *
 * @author lucho
 */
public class Semaforo2 extends Semaforo {

    public Semaforo2(int tiempoVerdeSentido1, int tiempoVerdeSentido2) {
        super(tiempoVerdeSentido1, tiempoVerdeSentido2);
    }

    @Override
    public void cambiarEstado() {
        try {
            synchronized(this) {
                System.out.println("SENTIDO 1 TRUE");
                verdeSentido1 = true;//fdsf
                verdeSentido2 = false;
            }
            Thread.sleep(tiempoVerdeSentido1);

            synchronized(this) {
                System.out.println("SENTIDO 1 FALSE");
                verdeSentido1 = false;
                verdeSentido2 = true;//fdf
            }
            Thread.sleep(tiempoVerdeSentido2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
