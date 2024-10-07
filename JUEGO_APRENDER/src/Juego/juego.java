/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author angel
 */
public class juego extends Canvas implements Runnable{
    
    private static final int ANCHO = 800;
    private static final int ALTO = 600;
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
    
    private void iniciar(){
        thread = new Thread(this,"graficos");
        thread.start();
    }
    
    private void detener(){
        
    }

    @Override
    public void run() {
        System.out.println("El thread 2 se está ejecutando con éxito");
    }
    
}
