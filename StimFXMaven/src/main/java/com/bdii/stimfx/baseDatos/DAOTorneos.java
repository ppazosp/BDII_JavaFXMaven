/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;

import com.bdii.stimfx.aplicacion.Videojuego;
import com.bdii.stimfx.aplicacion.Usuario;
import com.bdii.stimfx.aplicacion.Torneo;

import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author alumnogreibd
 */
public class DAOTorneos extends AbstractDAO{
    
    public DAOTorneos (Connection conexion, com.bdii.stimfx.aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
        public void insertarTorneo(Torneo t){
        Connection con;
        PreparedStatement stmUsuario=null;
 /*       
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

*/
    }
    
}