/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;

import com.bdii.stimfx.aplicacion.Editor;
import com.bdii.stimfx.aplicacion.FachadaAplicacion;
import com.bdii.stimfx.aplicacion.Usuario;
import com.bdii.stimfx.aplicacion.Videojuego;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author alumnogreibd
 */
public class DAOUsuarios extends AbstractDAO{
    public DAOUsuarios (Connection conexion, com.bdii.stimfx.aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public void insertarUsuario(Usuario u){
        Connection con;
        PreparedStatement stmUsuario=null;

        con=super.getConexion();

        try {
            stmUsuario=con.prepareStatement("insert into usuario(id, nombre, contraseña, email) "+
                                            "values (?,?,?,?,?,?)");
            stmUsuario.setString(1, u.getId());
            stmUsuario.setString(2, u.getNombre());
            stmUsuario.setString(3, u.getContrasena());
            stmUsuario.setString(4, u.getEmail());
            stmUsuario.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    public void borrarUsuario(int id){
        Connection con;
        PreparedStatement stmUsuario=null;
        
        con=super.getConexion();
        
        try{
            stmUsuario=con.prepareStatement("delete from usuario where id = ?");
            stmUsuario.setInt(1, id);
            stmUsuario.executeUpdate();
        
        }catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public void modificarUsuario(Usuario u){
        Connection con;
        PreparedStatement stmUsuario=null;

        con=super.getConexion();

        try{

            stmUsuario=con.prepareStatement("update usuario "+
                    " set nombre=?, "+
                         "contraseña=?, "+
                         "email=? "+
                    "where id = ?");

            stmUsuario.setString(1, u.getNombre());
            stmUsuario.setString(2, u.getContrasena());
            stmUsuario.setString(3, u.getEmail());
            stmUsuario.setString(4, u.getId());
            stmUsuario.executeUpdate();


        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }

    }

    public java.util.List<Usuario> consultarUsuarios(Integer id, String nombre){
        java.util.List<Usuario> resultado = new java.util.ArrayList<Usuario>();
        Usuario usuarioActual;
        Connection con;
        PreparedStatement stmUsuarios=null;
        ResultSet rsUsuarios;

        con=this.getConexion();
        
        String consulta = "select * from usuario ";
        
        if (nombre != null) consulta += "where nombre like ? ";
        
        if (id != null) {
            if (nombre != null) consulta += "and ";
            else consulta += "where ";
            consulta += "id = ?";
        }
        

        try  {
        stmUsuarios=con.prepareStatement(consulta);
        if (nombre != null) stmUsuarios.setString(1, "%"+nombre+"%");
        if (id != null) {
            if (nombre==null) stmUsuarios.setString(2, "%"+id+"%");
            else stmUsuarios.setString(1, "%"+id+"%");
            
        }
        rsUsuarios=stmUsuarios.executeQuery();
        while (rsUsuarios.next())
        {
            usuarioActual = new Usuario(rsUsuarios.getString("id"), rsUsuarios.getString("nombre"),
                                  rsUsuarios.getString("contraseña"), null,
                                      rsUsuarios.getString("email"));
            
            resultado.add(usuarioActual);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          FachadaAplicacion.muestraExcepcion(e.getMessage());
        }finally{
          try {
              if (stmUsuarios != null) {
                  stmUsuarios.close();
              }
          } catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
    public void seguir(int idU1, int idU2){
        Connection con;
        PreparedStatement stmSeguidos=null;
        
        con=super.getConexion();
        
        try {
            stmSeguidos=con.prepareStatement("insert into ser_amigo(id_usr1, id_usr2) "+
                                            "values (?,?)");       
            stmSeguidos.setInt(1, idU1);
            stmSeguidos.setInt(2, idU2);
            stmSeguidos.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmSeguidos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    public void dejarSeguir(int idU1, int idU2){
        Connection con;
        PreparedStatement stmSeguidos=null;
        
        con=super.getConexion();
        
        try{
            stmSeguidos=con.prepareStatement("delete from ser_amigo where id_usr1 = ? and id_usr2 = ?");
            stmSeguidos.setInt(1, idU1);
            stmSeguidos.setInt(2, idU2);
            stmSeguidos.executeUpdate();
        
        }catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmSeguidos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    //usuarios en vez de ints
    public java.util.List<Integer> consultarSeguidos(int idU1){
        java.util.List<Integer> resultado = new java.util.ArrayList<Integer>();
        Integer idUsuarioActual;
        Connection con;
        PreparedStatement stmSeguidos=null;
        ResultSet rsSeguidos;
        
        con=this.getConexion();
        
        String consulta = "select id_usr2 from ser_amigo where id_usr1 = ?";
        
        try{
            stmSeguidos=con.prepareStatement(consulta);
            stmSeguidos.setInt(1, idU1);
            rsSeguidos=stmSeguidos.executeQuery();
            while (rsSeguidos.next())
            {
                idUsuarioActual = rsSeguidos.getInt("id_usr2");//new Prestamo(rsPrestamos.getDate("fecha_prestamo"), rsPrestamos.getDate("fecha_devolucion"), rsPrestamos.getDate("fecha_vencimiento"),
                                        //rsPrestamos.getInt("num_ejemplar"), rsPrestamos.getInt("libro"), rsPrestamos.getString("id_usuario"));
                resultado.add(idUsuarioActual);
            }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmSeguidos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;  
    }

    //MEter usuarios en vez de ids, no?
    public java.util.List<String> consultarSeguidores(String idU2){
        java.util.List<String> resultado = new java.util.ArrayList<String>();
        String idUsuarioActual;
        Connection con;
        PreparedStatement stmSeguidos=null;
        ResultSet rsSeguidos;
        
        con=this.getConexion();
        
        String consulta = "select id_usr1 from ser_amigo where id_usr2 = ?";
        
        try{
            stmSeguidos=con.prepareStatement(consulta);
            stmSeguidos.setString(1, idU2);
            rsSeguidos=stmSeguidos.executeQuery();
            while (rsSeguidos.next())
            {
                idUsuarioActual = rsSeguidos.getString("id_usr2");//new Prestamo(rsPrestamos.getDate("fecha_prestamo"), rsPrestamos.getDate("fecha_devolucion"), rsPrestamos.getDate("fecha_vencimiento"),
                                        //rsPrestamos.getInt("num_ejemplar"), rsPrestamos.getInt("libro"), rsPrestamos.getString("id_usuario"));
                resultado.add(idUsuarioActual);
            }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmSeguidos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado; 
    }
    
    public void bloquearSeguidor(String idU2, String idU1){
        Connection con;
        PreparedStatement stmSeguidos=null;
        
        con=super.getConexion();
        
        try{
            stmSeguidos=con.prepareStatement("delete from ser_amigo where id_usr1 = ? and id_usr2 = ?");
            stmSeguidos.setString(1, idU1);
            stmSeguidos.setString(2, idU2);
            stmSeguidos.executeUpdate();
        
        }catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmSeguidos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public Usuario validarUsuario(String id, String clave){
        Usuario resultado = null;
        Usuario usuarioActual;
        Connection con;
        PreparedStatement stmUsuarios=null;
        ResultSet rsUsuarios;

        con=this.getConexion();

        String consulta = "select * from usuario where id like ? and contraseña like ?";



        try  {
            stmUsuarios=con.prepareStatement(consulta);
            stmUsuarios.setString(1, id);
            stmUsuarios.setString(2, clave);
            rsUsuarios=stmUsuarios.executeQuery();

            if (rsUsuarios.next())
            {
                resultado = new Usuario(rsUsuarios.getString("id"), rsUsuarios.getString("nombre"),
                        rsUsuarios.getString("contraseña"), null,
                        rsUsuarios.getString("email"));  //TELEFONO ESTA EN LA BASE?

            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        }finally{
            try {
                if (stmUsuarios != null) {
                    stmUsuarios.close();
                }
            } catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public java.util.List<Videojuego> consultarVideojuegos(String id){
        java.util.List<Videojuego> resultado = new java.util.ArrayList<>();
        Connection con;
        PreparedStatement stmVideojuego=null;
        ResultSet rs;

        con=this.getConexion();

        String consulta = "select  v.id, v.nombre , v.fechasubida , v.id_usreditor , v.precio , v.descripcion \n" +
                "from comprar as c join videojuego as v on c.id_videojuego=v.id\n" +
                "where c.id_usr like ?;";
        //un segundo porfa
        try{
            stmVideojuego=con.prepareStatement(consulta);
            stmVideojuego.setString(1, id);
            rs=stmVideojuego.executeQuery();
            while (rs.next())
            {
                Videojuego videojuego = new Videojuego(rs.getInt("id"),rs.getString("nombre"),
                        rs.getDate("fechasubida"), rs.getString("descripcion"), rs.getDouble("precio"));
                // SOlo tiene nombre este usuario
                Editor usuario = new Editor(rs.getString("id_usr"));
                videojuego.setEditor(usuario);
                resultado.add(videojuego);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmVideojuego.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
/*
    public List<Usuario> consultarUsuario(String nombre){
        Usuario usuarioActual;
        Connection con;
        PreparedStatement busqueda=null;
        ResultSet rsCatalogo;
        PreparedStatement stmDLC=null;
        ResultSet rsDLC;
        List<Usuario> resultado = new ArrayList<Usuario>();
        con=this.getConexion();
        //Cambiar consulta para q devulva tb nombre de usuario
        String consulta = "select *";
        try  {
        busqueda= con.prepareStatement(consulta);
        busqueda.setString(1, "%"+nombre+"%");
        rsCatalogo=busqueda.executeQuery();
        while (rsCatalogo.next())
        {
            int id=rsCatalogo.getInt("v.id");
            Videojuego videojuego = new Videojuego(id, rsCatalogo.getString("v.nombre"),
                        rsCatalogo.getDate("v.fechaSubida"),
                    rsCatalogo.getString("v.descripcion"), rsCatalogo.getDouble("precio"));
            Editor editor = new Editor(rsCatalogo.getInt("e.id"), rsCatalogo.getString("e.nombre"));
            videojuego.setEditor(editor);
            resultado.add(videojuego);
            
            
            consulta= "select * from DLC where idVideojuego = ?;";
            try{
            stmDLC=con.prepareStatement(consulta);
            stmDLC.setInt(1, id);
            rsDLC= stmDLC.executeQuery();
            while(rsDLC.next()){
                DLC dlc= new DLC(id, rsDLC.getInt("idDLC"),rsDLC.getString("nombre"),
                        rsDLC.getString("descripcion"), rsDLC.getDouble("precio"), 
                        rsDLC.getDate("fechaLanzamiento"));
                videojuego.addDLC(dlc);
            }
                   // (idVideojuego, idDLC, nombre, descripcion, precio, fechaLanzamiento)
            } catch (SQLException e){
          System.out.println(e.getMessage());
          //Mostar excepcion
          //this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {busqueda.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        }
        
        } catch (SQLException e){
          System.out.println(e.getMessage());
          //Mostar excepcion
          //this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {busqueda.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }*/
}

