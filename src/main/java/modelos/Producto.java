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
public class Producto {
    private int codigo;
    private String marca;
    private String descripcion;
    private float precio;

    public Producto(int codigo, String marca, String descripcion, float precio) {
        this.codigo = codigo;
        this.marca = marca;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    
    public static ArrayList<Producto> generarLista(ArrayList<Producto> productos, ArrayList<Integer> codigos) {
        ArrayList<Producto> lista = new ArrayList();
        
        for (int codigo: codigos) {
            for (Producto p: productos) {
                if (p.getCodigo() == codigo) {
                    lista.add(p);
                    break;
                }
            }
        }
        
        return lista;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", marca=" + marca + ", descripcion=" + descripcion + ", precio=" + precio + '}';
    }
}
