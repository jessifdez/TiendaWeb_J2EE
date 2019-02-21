package tiendaweb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mañanas
 */
public class Producto {
    private long id;
    private String producto;
    private int precio, stock;

    public Producto(long id, String producto, int precio) {
        this.id = id;
        this.producto = producto;
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    //Este se usara para insertar productos en la tabla de la tienda. Lo hace el administrador
    public Producto(String producto, int precio, int stock) {
        this.producto = producto;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto(long id, String producto, int precio, int stock) {
        this.id = id;
        this.producto = producto;
        this.precio = precio;
        this.stock = stock;
    }

   

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    
    
}
