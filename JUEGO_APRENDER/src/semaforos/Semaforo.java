/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semaforos;

/**
 *
 * @author lucho
 */
public abstract class Semaforo implements Runnable {
    protected static boolean verdeSentido1 = false;
    protected static boolean verdeSentido2 = false;
    protected int tiempoVerdeSentido1; // Tiempo en milisegundos para el sentido 1
    protected int tiempoVerdeSentido2; // Tiempo en milisegundos para el sentido 2
    protected boolean activo = true; // Control de ejecución del semáforo

    public Semaforo(int tiempoVerdeSentido1, int tiempoVerdeSentido2) {
        this.tiempoVerdeSentido1 = tiempoVerdeSentido1;
        this.tiempoVerdeSentido2 = tiempoVerdeSentido2;
    }

    public synchronized boolean puedeAvanzarSentido1() {
        return verdeSentido1;
    }

    public synchronized boolean puedeAvanzarSentido2() {
        return verdeSentido2;
    }

    public abstract void cambiarEstado();

    @Override
    public void run() {
        while (activo) {
            cambiarEstado();
        }
    }

    public void detenerSemaforo() {
        activo = false;
    }
}
