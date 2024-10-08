/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author angel
 */
public class juego extends Canvas implements Runnable{
    
    private static final int ANCHO = 800;
    private static final int ALTO = 600;
    
    private static volatile boolean enFuncionamiento = false;
    
    private static final String NOMBRE = "Juego";
    private static JFrame ventana;
    private static Thread thread;
    
    private juego(){
        setPreferredSize(new Dimension(ANCHO,ALTO));
        
        ventana = new JFrame(NOMBRE);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLayout(new BorderLayout());
        ventana.add(this,BorderLayout.CENTER);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
    
    public static void main(String[] args) {
        juego jueg = new juego();
        jueg.iniciar();
    }
    
    private synchronized void iniciar(){
        
        enFuncionamiento = true;
        
        
        thread = new Thread(this,"graficos");
        thread.start();
    }
    
    private synchronized void detener(){
        enFuncionamiento = false;
        
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    private void actualizar(){
        
    }
    
    private void mostrar(){
        
    }
    
    
    
    @Override
    public void run() {
        
        final int NS_POR_SEGUNDO = 1000000000;
        final byte APS_OBJETIVO = 60;
        //CUANTOS NANOSEGUNDOS TIENEN QUE TRANSCURRIR PARA QUE SIGAMOS EL OBJETIVO DE ACTUALIZACIONES POR SEGUNDO
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
        
        
        
        while (enFuncionamiento){
            actualizar();
            mostrar();
        }
    }
    
}
