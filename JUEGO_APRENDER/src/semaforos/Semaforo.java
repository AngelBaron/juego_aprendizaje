/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semaforos;

/**
 *
 * @author lucho
 */
import Graficos.HojaSprites;
import Graficos.Sprite; // Asegúrate de importar la clase Sprite

public abstract class Semaforo implements Runnable {

    protected  boolean verdeSentido1 = false;
    protected  boolean verdeSentido2 = false;
    protected int tiempoVerdeSentido1;
    protected int tiempoVerdeSentido2;
    protected boolean activo = true;

    protected Sprite spriteActual; // Cambiar tipo a Sprite}
    protected Sprite spriteActual2; // Cambiar tipo a Sprite

    public Semaforo(int tiempoVerdeSentido1, int tiempoVerdeSentido2) {
        this.tiempoVerdeSentido1 = tiempoVerdeSentido1;
        this.tiempoVerdeSentido2 = tiempoVerdeSentido2;
        spriteActual = Sprite.SEMAFORO_ROJO; // Iniciar en rojo
        spriteActual2 = Sprite.SEMAFORO_VERDE; // Iniciar en rojo
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

    public Sprite getSpriteActual() {
        return spriteActual;
    }
    
    public Sprite getSpriteActual2() {
        return spriteActual2;
    }
}
