<%-- 
    Document   : index
    Created on : 17-dic-2018, 13:07:17
    Author     : Mañanas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Object o=request.getAttribute("respuesta");
    String respuesta_registro="";
    if (o!=null)
    {
         int respuesta=Integer.parseInt(o.toString());
         switch(respuesta)
         {
             case 0:
                 respuesta_registro="Todo ha ido bien";
                 break;
             case 1:
                 respuesta_registro="El usuario está repetido";
                 break;
                 
             case 2: 
                 respuesta_registro="Ha habido algún problema";
                 break;
             case 3:
                 respuesta_registro="Usuario o pwd incorrectos";
                 break;
        }
    }
    
    
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Supermercado</title>
    </head>
    <body>
        <p><%=respuesta_registro%></p>
        <form action="ServletCompra" method="POST">
            <h3> Login</h3>
        <label for="usuario"> Usuario: </label>
	<input id="usuario" type="text" name="usuario" placeholder="Introduzca usuario"/>
	<br/>
        <label for="password"> Contraseña: </label>
	<input id="password" type="password" name="password" placeholder="Introduzca contraseña"/>
	<br/><br/>
        <input type="hidden" name="accion" value="entrar"/>
        <input type="submit" value="Entrar a la tienda"/>
        </form>
        <form action="ServletCompra" method="POST">
            <h3>Registrarse:</h3>
            <br/>
            <label for="nombre"> Nombre: </label>
            <input id="nombre" type="text" name="nombre" placeholder="Introduzca nombre"/>
            <br/>
            <label for="usuario"> Usuario: </label>
            <input id="usuario" type="text" name="usuario" placeholder="Introduzca usuario"/>
            <br/>
            <label for="password"> Contraseña: </label>
            <input id="password" type="password" name="password" placeholder="Introduzca contraseña"/>
            <br/><br/>
            <input type="hidden" name="accion" value="registrar"/>
            <input type="submit" value="Registrar"/>
        </form>
        <!--<a href="ServletCompra?accion=entrar">Entrar a la tienda</a>-->
    </body>
</html>
