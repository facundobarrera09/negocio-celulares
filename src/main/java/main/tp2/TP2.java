/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.tp2;

import controlador.Controlador;
import java.util.ArrayList;
import modelos.*;

/**
 *
 * @author root
 */
public class TP2 {
    public static void main(String[] args) {
     
        Fecha hoy = new Fecha(10,11,2022);
        
        Controlador controlador = new Controlador(hoy);
        
        controlador.inicio();
    }
}
