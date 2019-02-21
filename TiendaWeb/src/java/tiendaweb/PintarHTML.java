/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendaweb;

import java.util.ArrayList;

/**
 *
 * @author Mañanas
 */
public class PintarHTML {
    public static String pintarTabla(ArrayList<Producto> lista_comprados){
        String html="";
        int precio_total=0;
        html+="<table>";
        html+="<tr><th>PRODUCTO</th><th>PRECIO</th><th>STOCK</th></tr>";
        for (Producto p : lista_comprados) {
             html+="<tr><td>"+p.getProducto()+"</td><td>"+p.getPrecio()+"</td></tr>";
             precio_total+=p.getPrecio();
        }
        html+="<tr><td>TOTAL:</td><td>"+precio_total+"</td></tr>";
        html+="</table>";
       
       return html;
    }
}
