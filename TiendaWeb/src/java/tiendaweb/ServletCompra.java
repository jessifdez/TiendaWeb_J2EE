package tiendaweb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import tiendaweb.Producto;
import tiendaweb.AccesoBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mañanas
 */
@WebServlet(urlPatterns = {"/ServletCompra"})
public class ServletCompra extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion=request.getParameter("accion");
        HttpSession session=request.getSession();
        if(accion.equals("entrar")){
           String usuario=request.getParameter("usuario");
           String password=request.getParameter("password");
           boolean usuario_ok=AccesoBD.verificarUsuario(usuario, password);
           if (usuario_ok)
           {
                //HttpSession session=request.getSession();
                session.setAttribute("usuario", usuario);
                //1-Recupero productos de la tienda
                ArrayList<Producto> productos_tienda=AccesoBD.recuperarProductos();
                request.setAttribute("productos_tienda", productos_tienda);
                //Poner en sesion el ArrayList de producto comprados
                ArrayList<Integer> productos_comprados=new ArrayList();
                session.setAttribute("productos_comprados", productos_comprados);
                //Mandarle a comprar.jsp con los productos
               request.getRequestDispatcher("comprar.jsp").forward(request, response);
           }else{       
               request.setAttribute("respuesta",3);
               request.getRequestDispatcher("index.jsp").forward(request, response);
           
           }
        }else if (accion.equals("registrar")){
           //acciones de registro
           String nombre=request.getParameter("nombre");
           String usuario=request.getParameter("usuario");
           String password=request.getParameter("password");
           int respuesta=AccesoBD.insertarUsuario(nombre, usuario, password);
           //0-Todo bien; 1-Usuario repe; 2-Fallo conexion o algo así
           request.setAttribute("respuesta", respuesta);
           request.getRequestDispatcher("index.jsp").forward(request, response);
        }else if(accion.equals("vercarrito")){
            ArrayList<Integer> productos_comprados=(ArrayList<Integer>)session.getAttribute("productos_comprados");
            ArrayList<Producto> objeto_productos=AccesoBD.recuperarProductosPorId(productos_comprados);
            request.setAttribute("objeto_productos",objeto_productos);
            request.getRequestDispatcher("carrito.jsp").forward(request, response);
        }else if(accion.equals("comprar")){
            //Veo el producto que quiero comprar
            //Recupero de la sesion productos comprados
            String id_producto=request.getParameter("id");
            int id=Integer.parseInt(id_producto);
            ArrayList<Integer> productos_comprados=(ArrayList<Integer>)session.getAttribute("productos_comprados");
            productos_comprados.add(id);
            //Se podria mdiicar e stock
            session.setAttribute("productos_comprados", productos_comprados);
            AccesoBD.actualizarStock(id);
            ArrayList<Producto> productos_tienda=AccesoBD.recuperarProductos();
            request.setAttribute("productos_tienda", productos_tienda);
            request.getRequestDispatcher("comprar.jsp").forward(request, response);   
       
    }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
