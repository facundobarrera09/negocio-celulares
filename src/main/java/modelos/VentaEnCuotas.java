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
public class VentaEnCuotas extends Venta {
    private int cantidadDeCuotas;
    private ArrayList<Cuota> cuotas = new ArrayList<Cuota>();

    public VentaEnCuotas(int id, Cliente comprador, ArrayList<Producto> productos, Fecha fechaCompra, int cantidadDeCuotas) throws Exception {        
        super(id, comprador, productos, fechaCompra);

        if (cantidadDeCuotas != 6 && cantidadDeCuotas != 12) {
            throw new Exception("La financiacion disponible es de 6 u 12 cuotas");
        }
        
        this.cantidadDeCuotas = cantidadDeCuotas;
        
        super.setMonto((float) (super.getMonto() * ((cantidadDeCuotas == 6) ? 1.30 : 1.60 )));
        
        Fecha fechaDeCuota = fechaCompra;
        for (int x = 0; x < cantidadDeCuotas; x++) {
            float importe = (float) (super.getMonto() / cantidadDeCuotas);
            Cuota nuevaCuota = new Cuota(x+1, importe, fechaDeCuota);
            fechaDeCuota = fechaDeCuota.aumentarFecha(1);
            
            cuotas.add(nuevaCuota);
        }
    }

    public ArrayList<Cuota> getCuotas() {
        return cuotas;
    }

    public Cuota getCuotaById(int id) {
        Cuota cuota = new Cuota();

        for (Cuota c: cuotas) {
            if (c.getNumeroCuota() == id) {
                cuota = c;
            }
        }

        return cuota;
    }

    @Override
    public Ticket generarTicket() {
        TicketEnCuotas ticket = new TicketEnCuotas(super.getComprador().getNombre(), super.getFechaCompra(), super.getProductos(), super.getMonto(), cuotas);
        return ticket;
    }

    @Override
    public String toString() {
        return "VentaEnCuotas{" + "id=" + super.getId() + ", comprador=" + super.getComprador() + ", productos=" + super.getProductos() + 
                ", fechaCompra=" + super.getFechaCompra() + ", monto=" + super.getMonto()
                + "\t\ncantidadDeCuotas=" + cantidadDeCuotas + ", cuotas=" + cuotas + '}';
    }
    
    
}
