/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;

import com.bdii.stimfx.aplicacion.Usuario;
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
            stmUsuario=con.prepareStatement("insert into usuario(id, nombre, contraseña, email, telefono, tipoUsuario) "+
                                            "values (?,?,?,?,?,?)");          
            stmUsuario.setInt(1, u.getId());
            stmUsuario.setString(2, u.getNombre());
            stmUsuario.setString(3, u.getContrasena());
            stmUsuario.setString(4, u.getEmail());
            stmUsuario.setString(5, u.getTelefono());
            stmUsuario.setString(6, u.getTipoUsuario());
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
            stmUsuario=con.prepareStatement("delete from usuario where id_usuario = ?");
            stmUsuario.setInt(1, id);
            stmUsuario.executeUpdate();
        
        }catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
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
    }
*/
}

