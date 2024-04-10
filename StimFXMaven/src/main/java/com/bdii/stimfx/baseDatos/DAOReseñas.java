/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import com.bdii.stimfx.aplicacion.Reseña;
import java.time.LocalDate;

import java.sql.*;

/**
 *
 * @author alumnogreibd
 */
public class DAOReseñas extends AbstractDAO {
    
    public DAOReseñas (Connection conexion, com.bdii.stimfx.aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public void insertarReseña(Reseña r){
        Connection con;
        PreparedStatement stmReseñas=null;
        
        con=super.getConexion();
        
        try {
            stmReseñas=con.prepareStatement("insert into reseñas(idVideojuego, idReseñas, comentario, fecha) "+
                                            "values (?,?,?,?)");
            // Obtener la fecha actual como un objeto java.sql.Date
            java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());
            // Convertir la fecha actual a LocalDate
            LocalDate localDate = fechaActual.toLocalDate();

            r.setFecha(fechaActual);
            
            stmReseñas.setInt(1, r.getIdVideojuego());
            stmReseñas.setInt(2, r.getIdReseña());
            stmReseñas.setString(3, r.getComentario());
            stmReseñas.setDate(4, fechaActual);
            stmReseñas.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReseñas.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
}
