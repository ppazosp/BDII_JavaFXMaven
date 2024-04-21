/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;
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
            stmReseñas=con.prepareStatement("insert into reseña(id_videojuego, id_reseña, id_usr, comentario, fecha) "+
                                            "values (?,?,?,?,?)");
            // Obtener la fecha actual como un objeto java.sql.Date
            java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());
            // Convertir la fecha actual a LocalDate
            LocalDate localDate = fechaActual.toLocalDate();

            r.setFecha(fechaActual);
            
            stmReseñas.setInt(1, r.getId_videojuego());
            stmReseñas.setInt(2, r.getIdReseña());
            stmReseñas.setInt(3, r.getId_usuario());
            stmReseñas.setString(4, r.getComentario());
            stmReseñas.setDate(5, fechaActual);
            stmReseñas.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReseñas.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
  /*
    public void consultarReseña(int id_videojuego){
        Connection con;
        PreparedStatement stmReseñas=null;
        ResultSet rsReseña = null;
        
        con=super.getConexion();

        
        String consulta= "select * from reseña where id_juego = ?;";
                try {
            stmReseñas=con.prepareStatement(consulta);
            stmReseñas.setInt(1, v.getId());
            rsReseña=stmReseñas.executeQuery();
            
            while(rsReseña.next()){
                Reseña reseña = new Reseña(vj, rsReseña.getInt("id"), rsReseña.getString("comentario"),
                    rsReseña.getDate("fecha"),  rsReseña.getFloat("valoracion"));
                vj.addReseña(reseña);
            }
            
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReseñas.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    */
}
