/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;
import java.sql.Connection;

import com.bdii.stimfx.aplicacion.Resenha;
import com.bdii.stimfx.aplicacion.Videojuego;

import java.time.LocalDate;

import java.sql.*;

/**
 *
 * @author alumnogreibd
 */
public class DAOResenhas extends AbstractDAO {
    
    public DAOResenhas (Connection conexion, com.bdii.stimfx.aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public void insertarResenha(Resenha r){
        Connection con;
        PreparedStatement stmResenhas=null;
        
        con=super.getConexion();
        
        try {
            stmResenhas=con.prepareStatement("insert into resenha(id_videojuego, id_usr, comentario, fecha, likes, dislikes) "+
                                            "values (?,?,?,?,?,?)");
            // Obtener la fecha actual como un objeto java.sql.Date
            java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());
            // Convertir la fecha actual a LocalDate
            LocalDate localDate = fechaActual.toLocalDate();

            r.setFecha(fechaActual);
            stmResenhas.setInt(1, r.getId_videojuego());
            stmResenhas.setString(2, r.getId_usuario());
            stmResenhas.setString(3, r.getComentario());
            stmResenhas.setDate(4, fechaActual);
            stmResenhas.setInt(5, r.getLikes());
            stmResenhas.setInt(6, r.getDislikes());
            stmResenhas.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmResenhas.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public void consultarResenha(Videojuego v){
        Connection con;
        PreparedStatement stmResenhas=null;
        ResultSet rsResenha = null;
        
        con=super.getConexion();

        
        String consulta= "select * from resenha where id_videojuego = ?;";
                try {
            stmResenhas=con.prepareStatement(consulta);
            stmResenhas.setInt(1, v.getId());
            rsResenha=stmResenhas.executeQuery();
            
            while(rsResenha.next()){
                Resenha resenha = new Resenha(v.getId(), rsResenha.getInt("id_resenha"), rsResenha.getString("id_usr"), rsResenha.getString("comentario"),
                      rsResenha.getInt("valoracion"), rsResenha.getDate("fecha"));
                v.addResenha(resenha);
            }
            
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmResenhas.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

}
