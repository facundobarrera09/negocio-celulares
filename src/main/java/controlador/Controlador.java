/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.*;
import vistas.*;

/**
 *
 * @author root
 */
public class Controlador {
    private Fecha hoy;
    private Negocio negocio;
    
    // vistas
    private VistaNegocio vistaNegocio;
    private RegistrarCliente registrarCliente;
    private RegistrarProducto registrarProducto;
    private ProcesoVenta procesoVenta;
    private VisualizarVenta visualizarVenta;

    public Controlador(Fecha hoy) {
        this.hoy = hoy;
        this.negocio = new Negocio();
    }  
    
    public void inicio() {
        vistaNegocio = new VistaNegocio(this);
        vistaNegocio.setLocationRelativeTo(null);
        vistaNegocio.setVisible(true);
    }
    
    public void salir(int disable, int enable) {
        switch (disable) {
            case 0 -> vistaNegocio.dispose();
            case 1 -> registrarCliente.dispose();
            case 2 -> registrarProducto.dispose();
            case 3 -> procesoVenta.dispose();
            case 4 -> visualizarVenta.dispose();
        }
        switch (enable) {
            case 0 -> vistaNegocio.setEnabled(true);
            case 1 -> registrarCliente.setEnabled(true);
            case 2 -> registrarProducto.setEnabled(true);
            case 3 -> procesoVenta.setEnabled(true);
            case 4 -> visualizarVenta.setEnabled(true);
        }
    }

    public void mostrar(int id) {
        this.mostrar(id, null);
    }
    public void mostrar(int id, Venta venta) {
        vistaNegocio.setEnabled(false);
        
        switch (id) {
            case 1 -> {
                registrarCliente = new RegistrarCliente(this);
                registrarCliente.setLocationRelativeTo(null);
                registrarCliente.setVisible(true);
            }
            case 2 -> { 
                registrarProducto = new RegistrarProducto(this);
                registrarProducto.setLocationRelativeTo(null);
                registrarProducto.setVisible(true);
            }
            case 3 -> { 
                procesoVenta = new ProcesoVenta(this);
                procesoVenta.setLocationRelativeTo(null);
                procesoVenta.setVisible(true);
            }
            case 4 -> {
                visualizarVenta = new VisualizarVenta(this, venta);
                visualizarVenta.setLocationRelativeTo(null);
                visualizarVenta.setVisible(true);
            }
        }
    }
    
    public void registrarCliente() {
        try {
            int dni = Integer.parseInt(registrarCliente.getDNI().getText());
            String nombre = registrarCliente.getNombre().getText();
            String email = registrarCliente.getEmail().getText();
            
            Cliente nuevoCliente = negocio.registrarCliente(new Cliente(-1, nombre, dni, email));
            
            DefaultTableModel modeloTabla = (DefaultTableModel) vistaNegocio.getTablaClientes().getModel();
            modeloTabla.addRow(new Object[]{nuevoCliente.getId(), nuevoCliente.getDni(), nuevoCliente.getNombre(), nuevoCliente.getEmail()});
            
            this.salir(1, 0);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(registrarCliente, "Dato invalido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void registrarProducto() {
        try {
            int codigo = Integer.parseInt(registrarProducto.getCodigo().getText());
            String marca = registrarProducto.getMarca().getText();
            String descripcion = registrarProducto.getDescripcion().getText();
            float precio = Float.parseFloat(registrarProducto.getPrecio().getText());
            
            Producto nuevoProducto = negocio.registrarProducto(new Producto(codigo, marca, descripcion, precio));
            
            DefaultTableModel modeloTabla = (DefaultTableModel) vistaNegocio.getTablaProductos().getModel();
            modeloTabla.addRow(new Object[]{nuevoProducto.getCodigo(), nuevoProducto.getMarca(), nuevoProducto.getDescripcion(), nuevoProducto.getPrecio()});
            
            this.salir(2, 0);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(registrarProducto, "Dato invalido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarCliente() {
        try {
            int id = Integer.parseInt(procesoVenta.getIdCliente().getText());
            
            Cliente cliente = negocio.getClienteById(id);
            DefaultTableModel modeloTabla = (DefaultTableModel) procesoVenta.getCliente().getModel();
            
            modeloTabla.setValueAt(cliente.getId(), 0, 0);
            modeloTabla.setValueAt(cliente.getDni(), 0, 1);
            modeloTabla.setValueAt(cliente.getNombre(), 0, 2);
            modeloTabla.setValueAt(cliente.getEmail(), 0, 3);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(registrarProducto, "Dato invalido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void agregarProductosALista() {
        ArrayList<Integer> codigos = new ArrayList<Integer>();
        
        try {
            JTextField textoCodigos = procesoVenta.getCodigos();
            for (String nuevoCodigo: textoCodigos.getText().split(",")) {
                codigos.add(Integer.valueOf(nuevoCodigo));
            }
            textoCodigos.setText("");
            
            DefaultTableModel modeloTabla = (DefaultTableModel) procesoVenta.getLista().getModel();
            
            ArrayList<Producto> nuevosProductos = Producto.generarLista(negocio.getProductos(), codigos);
            
            for (Producto p: nuevosProductos) {
                modeloTabla.addRow(new Object[]{p.getCodigo(), p.getMarca(), p.getDescripcion(), p.getPrecio()});
            }
            
            this.cuotificar(true);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(procesoVenta, "Dato invalido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cuotificar() {
        this.cuotificar(false);
    }
    public void cuotificar(boolean ignorarError) {
        try {
            DefaultTableModel modeloTabla = (DefaultTableModel) procesoVenta.getLista().getModel();
            int rows = modeloTabla.getRowCount();
            int cantidadDeCuotas = Integer.parseInt(procesoVenta.getCuotas().getText());
            float monto, importe;
            ArrayList<Producto> lista = new ArrayList<Producto>();
            
            if (rows == 0) {
                throw new Exception("No se cargaron productos");
            }
            
            for (int x = 0; x < rows; x++) {
                lista.add(new Producto((int) modeloTabla.getValueAt(x, 0), (String) modeloTabla.getValueAt(x, 1), (String) modeloTabla.getValueAt(x, 2), (Float) modeloTabla.getValueAt(x, 3)));
            }
            
            monto = 0;
            for (Producto p: lista) {
                monto += p.getPrecio();
            }
            monto *= (cantidadDeCuotas >= 12) ? 1.60 :
                     (cantidadDeCuotas >= 6) ? 1.30 :
                     1 ;
            
            importe = monto / cantidadDeCuotas;
            
            procesoVenta.getMonto().setText(String.valueOf(monto));
            procesoVenta.getImporteCuota().setText(String.valueOf(importe));
        }
        catch (Exception e) {
            if (!ignorarError) JOptionPane.showMessageDialog(procesoVenta, "Dato invalido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void registrarVenta() {
        try {
            DefaultTableModel modeloTablaCliente = (DefaultTableModel) procesoVenta.getCliente().getModel();
            DefaultTableModel modeloTablaLista = (DefaultTableModel) procesoVenta.getLista().getModel();
            int rows = modeloTablaLista.getRowCount();
            int cantidadDeCuotas = Integer.parseInt(procesoVenta.getCuotas().getText());
            ArrayList<Producto> lista = new ArrayList<Producto>();
            
            Cliente comprador = new Cliente((int) modeloTablaCliente.getValueAt(0, 0), (String) modeloTablaCliente.getValueAt(0, 2), (int) modeloTablaCliente.getValueAt(0, 1), (String) modeloTablaCliente.getValueAt(0, 3));
            
            if (rows == 0) {
                throw new Exception("No se cargaron productos");
            }
            
            for (int x = 0; x < rows; x++) {
                lista.add(new Producto((int) modeloTablaLista.getValueAt(x, 0), (String) modeloTablaLista.getValueAt(x, 1), (String) modeloTablaLista.getValueAt(x, 2), (Float) modeloTablaLista.getValueAt(x, 3)));
            }
            
            DefaultTableModel modeloTablaVentas = (DefaultTableModel) vistaNegocio.getTablaVentas().getModel();
            Venta nuevaVenta = negocio.iniciarVenta(cantidadDeCuotas, comprador, lista, hoy);
            modeloTablaVentas.addRow(new Object[]{nuevaVenta.getId(), nuevaVenta.getComprador().getNombre(), nuevaVenta.getFechaCompra(), nuevaVenta.getMonto()});
            
            this.salir(3, 0);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(procesoVenta, "Dato invalido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void visualizarVenta() {
        JTable tablaVentas = vistaNegocio.getTablaVentas();
        int filaElegida = tablaVentas.getSelectedRow();
        
        Venta venta = negocio.getVentaById((int) tablaVentas.getValueAt(filaElegida, 0));
        
        this.mostrar(4, venta);
    }

    public void pagarCuota() {
        JTable tablaCuotas = visualizarVenta.getCuotas();
        int filaElegida = tablaCuotas.getSelectedRow();

        Cuota cuota = ((VentaEnCuotas) visualizarVenta.getVenta()).getCuotaById((int) tablaCuotas.getValueAt(filaElegida, 0));

        cuota.setPagada(true);
        tablaCuotas.setValueAt("Pagada", filaElegida, 4);
    }
}
