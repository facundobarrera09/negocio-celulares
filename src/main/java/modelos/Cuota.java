/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author root
 */
public class Cuota {
    private float importe;
    private Fecha fechaPago;
    private Fecha vencimiento;
    private int numeroCuota;
    private boolean pagada;

    public Cuota() {
        
    }
    public Cuota(int numeroCuota, float importe, Fecha fechaPago) {
        this.numeroCuota = numeroCuota;
        this.importe = importe;
        this.fechaPago = fechaPago;
        this.pagada = false;
        this.vencimiento = fechaPago.aumentarFecha(1);
    }

    public Fecha getVencimiento() {
        return vencimiento;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public boolean isPagada() {
        return pagada;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public Fecha getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Fecha fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Boolean getPagada() {
        return pagada;
    }

    public void setPagada(Boolean pagada) {
        this.pagada = pagada;
    }

    @Override
    public String toString() {
        return "Cuota{" + "importe=" + importe + ", fechaPago=" + fechaPago + ", vencimiento=" + vencimiento + ", numeroCuota=" + numeroCuota + ", pagada=" + pagada + '}';
    }
    
    
}
