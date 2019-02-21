<%-- 
    Document   : carrito
    Created on : 18-dic-2018, 10:25:52
    Author     : Mañanas
--%>

<%@page import="tiendaweb.PintarHTML"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tiendaweb.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Producto> lista_comprados=(ArrayList<Producto>)request.getAttribute("objeto_productos");
    String html_factura=PintarHTML.pintarTabla(lista_comprados);
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
        <title>Mi carrito de la compra</title>
    </head>
    <body>
        <h1>Factura <%=usuario%></h1>
        <%=html_factura%>
    </body>
</html>
