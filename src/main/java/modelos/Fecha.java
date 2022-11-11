/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author root
 */
public class Fecha {
    private int dia, mes, anio;

    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public Fecha aumentarFecha(int meses) {
        Fecha nuevaFecha = new Fecha(this.dia, this.mes, this.anio);
        nuevaFecha.mes += meses;
        
        if (nuevaFecha.mes > 12) {
            nuevaFecha.mes -= 12;
            nuevaFecha.anio += 1;
        }
        
        return nuevaFecha;
    }
    
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + anio;
    }   
}
