<%-- 
    Document   : comprar
    Created on : 17-dic-2018, 13:23:46
    Author     : Mañanas
--%>

<%@page import="tiendaweb.PintarHTML"%>
<%@page import="tiendaweb.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    ArrayList<Producto> productos_tienda=(ArrayList<Producto>)request.getAttribute("productos_tienda");
    //String lista=PintarHTML.pintarLista(lista_productos);
    ArrayList<Integer> productos_comprados=(ArrayList<Integer>)session.getAttribute("productos_comprados");
    String usuario=(String)session.getAttribute("usuario");
    if(usuario==null)
    {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista productos</title>
    </head>
    <body>
        <h1><%=usuario%>, llevas comprados: <%=productos_comprados.size()%> productos</h1>
        <%
                for(int i=0;i<productos_tienda.size();i++){
                Producto p=productos_tienda.get(i);
            %>
            <a href="ServletCompra?accion=comprar&id=<%=p.getId() %>&precio=<%=p.getPrecio()%>"><%=p.getProducto()%></a><%=p.getStock()%>
            <br>
          <%} %>
          <br>
          <a href="ServletCompra?accion=vercarrito">VER CARRITO</a>;
    </body>
</html>
