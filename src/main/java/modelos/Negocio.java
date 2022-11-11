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
public class Negocio {
    private ArrayList<Cliente> clientes;
    private ArrayList<Producto> productos;
    private ArrayList<Venta> ventas;

    public Negocio(){
        this.clientes = new ArrayList<Cliente>();
        this.productos = new ArrayList<Producto>();
        this.ventas = new ArrayList<Venta>();
    }
    
    public Negocio(ArrayList<Cliente> clientes, ArrayList<Producto> productos) {
        this.clientes = clientes;
        this.productos = productos;
        this.ventas = new ArrayList<Venta>();
    }
    
    public Cliente registrarCliente(Cliente nuevoCliente) {
        if (nuevoCliente.getId() == -1) {
            nuevoCliente.setId(clientes.size()+1);
        }
        
        for (Cliente c: clientes) {
            assert c.getId() != nuevoCliente.getId();
        }
        clientes.add(nuevoCliente);
        
        return nuevoCliente;
    }
    
    public Producto registrarProducto(Producto nuevoProducto) throws Exception {
        if (nuevoProducto.getCodigo() == -1){
            nuevoProducto.setCodigo(productos.size()+1);
        }
        
        for (Producto p: productos) {
            if (p.getCodigo() == nuevoProducto.getCodigo()) {
                throw new Exception("El codigo del producto ya se encuentra registrado");
            }
        }
        productos.add(nuevoProducto);
        
        return nuevoProducto;
    }
    
    public Venta iniciarVenta(int cantidadDeCuotas, Cliente comprador, ArrayList<Producto> productos, Fecha fechaCompra) throws Exception {
        assert !(this.estaRegistrado(comprador));
        for (Producto p: productos) {
            assert !(this.estaRegistrado(p));
        }
        
        Venta nuevaVenta;
        
        if (cantidadDeCuotas == 1) {
            nuevaVenta = new VentaEnContado(ventas.size()+1, comprador, productos, fechaCompra);
        }
        else {
            nuevaVenta = new VentaEnCuotas(ventas.size()+1, comprador, productos, fechaCompra, cantidadDeCuotas);
        }
        
        this.ventas.add(nuevaVenta);
        return nuevaVenta;
    }

    private boolean estaRegistrado(Cliente comprador) {
        boolean encontrado = false;
        
        for (Cliente c: clientes) {
            if (c.getId() == comprador.getId()) {
                encontrado = true;
                break;
            }
        }
        
        return encontrado;
    }

    private boolean estaRegistrado(Producto producto) {
        boolean encontrado = false;
        
        for (Producto p: productos) {
            if (p.getCodigo() == producto.getCodigo()) {
                encontrado = true;
                break;
            }
        }
        
        return encontrado;
    }
    
    public Cliente getClienteById(int id) {
        Cliente cliente = new Cliente(0, "", 0, "");
        
        for (Cliente c: clientes) {
            if (c.getId() == id) {
                cliente = c;
                break;
            }
        }
        
        return cliente;
    }
    
    public Venta getVentaById(int id) {
        Venta venta = new VentaEnContado();
        
        for (Venta v: ventas) {
            if (v.getId() == id) {
                venta = v;
                break;
            }
        }
        
        return venta;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }
    

    @Override
    public String toString() {
        return "Negocio{" + "\n\tclientes=" + clientes + "\n\tproductos=" + productos + "\n\tventas=" + ventas + '}';
    }
}
