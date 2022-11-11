/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.ArrayList;

/**
 *
 * @author root
 */
public class Ticket {
    private String nombreCliente;
    private Fecha fechaVenta;
    private ArrayList<Producto> productos;
    private float monto;

    public Ticket(String nombreCliente, Fecha fechaVenta, ArrayList<Producto> productos, float monto) {
        this.nombreCliente = nombreCliente;
        this.fechaVenta = fechaVenta;
        this.productos = productos;
        this.monto = monto;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Fecha getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Fecha fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Ticket {" + "Cliente=" + nombreCliente + ", Fecha de Venta=" + fechaVenta + ", Productos=" + productos + ", Monto a Pagar=" + monto + '}';
    }
}
