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

class TicketEnCuotas extends Ticket {
    private ArrayList<Cuota> cuotas;
    
    public TicketEnCuotas(String nombreCliente, Fecha fechaVenta, ArrayList<Producto> productos, float monto, ArrayList<Cuota> cuotas) {
        super(nombreCliente, fechaVenta, productos, monto);
        
        this.cuotas = cuotas;
    }

    @Override
    public String toString() {
        return "Ticket {" + "Cliente=" + super.getNombreCliente() + ", Fecha de Venta=" + super.getFechaVenta() + 
                ", Productos=" + super.getProductos() + ", Monto a Pagar=" + super.getMonto() + ", cuotas=" + cuotas;
    }
}
