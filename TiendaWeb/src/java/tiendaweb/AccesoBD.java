package tiendaweb;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mañanas
 */
public class AccesoBD {

    public static ArrayList<Producto> recuperarProductos() {
		ArrayList<Producto> lista_productos=new ArrayList<Producto>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda_web?serverTimezone=UTC","root","");
			Statement stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM productos ORDER BY Producto");
			while(rs.next()) 
			{
				long id=rs.getLong("id");
                                String producto=rs.getString("Producto");
				int precio=rs.getInt("Precio");
                                int stock=rs.getInt("Stock");
				Producto p=new Producto(id, producto, precio,stock);
				lista_productos.add(p);
			}
			stmt.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista_productos;
	}

    static ArrayList<Producto> recuperarProductosPorId(ArrayList<Integer> productos_comprados) {
        //ArrayList<Integer> productos_comprados=(ArrayList<Integer>)session.getAttribute("productos_comprados");
        //ArrayList<Producto> objeto_productos=AccesoBD.recuperarProductosPorId(productos_comprados);
        ArrayList<Producto> obj_productos_comprados=new ArrayList();
        try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda_web?serverTimezone=UTC","root","");
		Statement stmt=connection.createStatement();
                for (int i=0; i<productos_comprados.size(); i++)
                    {
                        int id_producto=productos_comprados.get(i);
			ResultSet rs=stmt.executeQuery("SELECT * FROM productos WHERE id='"+id_producto+"'");
			while(rs.next()) 
			{
				long id=rs.getLong("id");
                                String producto=rs.getString("Producto");
				int precio=rs.getInt("Precio");
                                int stock=rs.getInt("Stock");
				Producto p=new Producto(id, producto, precio,stock);
				obj_productos_comprados.add(p);
			}
                        
                    }
                stmt.close();
                connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj_productos_comprados;
    }

    static boolean verificarUsuario(String usuario, String password) {
        String query="SELECT * FROM usuarios WHERE Usuario=? AND Password=?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/usuario_contraseña?serverTimezone=UTC", "root","");
            PreparedStatement stmt=connection.prepareStatement(query);
            stmt.setString(1, usuario);
            stmt.setString(2, password);
            ResultSet datos=stmt.executeQuery();
            if(datos.next())
            {
                return true;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    static int insertarUsuario(String nombre, String usuario, String password) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/usuario_contraseña?serverTimezone=UTC", "root",""); 
            //Verificar si existe el usuario
            String query="SELECT * FROM usuarios WHERE Usuario=?";
            PreparedStatement stmt=connection.prepareStatement(query);
            stmt.setString(1, usuario);
            ResultSet datos=stmt.executeQuery();
            if(datos.next())
            {
                return 1;
            }
            //Para insertar
            String query_insert="INSERT INTO usuarios VALUES(?,?,?)";
            //Para ver el fallo hago un catch
            stmt=connection.prepareStatement(query_insert);
            stmt.setString(1, nombre);
            stmt.setString(2, usuario);
            stmt.setString(3, password);
            stmt.execute();
        }catch (Exception e){
            return 2;
        }
        return 0;       
    }

    static void actualizarStock(int id) {
        String query_update="UPDATE productos SET Stock=Stock-1 WHERE id=?";
        try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda_web?serverTimezone=UTC","root","");
			int[] argumentos={id};
                        PreparedStatement stmt=connection.prepareStatement(query_update);
                        stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
    }
    
}
