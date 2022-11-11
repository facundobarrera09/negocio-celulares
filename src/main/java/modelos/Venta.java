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
public abstract class Venta {
    private int id;
    private Cliente comprador;
    private ArrayList<Producto> productos;
    private Fecha fechaCompra;
    private float monto;

    public Venta() {
        
    }
    public Venta(int id, Cliente comprador, ArrayList<Producto> productos, Fecha fechaCompra) {
        this.id = id;
        this.comprador = comprador;
        this.productos = productos;
        this.fechaCompra = fechaCompra;
        
        this.monto = 0;
        for (Producto p: productos) {
            this.monto += p.getPrecio();
        }
    }
    
    public abstract Ticket generarTicket();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getComprador() {
        return comprador;
    }

    public void setComprador(Cliente comprador) {
        this.comprador = comprador;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public Fecha getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Fecha fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", comprador=" + comprador + ", productos=" + productos + ", fechaCompra=" + fechaCompra + ", monto=" + monto + '}';
    }
}
