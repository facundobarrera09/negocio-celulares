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
public class VentaEnContado extends Venta {

    public VentaEnContado(int id, Cliente comprador, ArrayList<Producto> productos, Fecha fechaCompra) {
        super(id, comprador, productos, fechaCompra);
    }

    @Override
    public Ticket generarTicket() {
        Cliente comprador = super.getComprador();
        Ticket ticket = new Ticket(comprador.getNombre(), super.getFechaCompra(), super.getProductos(), super.getMonto());
        return ticket;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
