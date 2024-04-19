/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;

import com.bdii.stimfx.aplicacion.FachadaAplicacion;
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
    
    public DAOTorneos (Connection conexion, com.bdii.stimfx.aplicacion.FachadaAplicacion fa){  //MIRAR TEMA FECHAS, POR AHORA AUTOMATICO
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
        public void insertarTorneo(Torneo t){
        Connection con;
        PreparedStatement stmTorneo=null;
        
        con=super.getConexion();
        
        
        
        try {
            stmTorneo=con.prepareStatement("insert into torneo(id, nombre, fecha_inicio, fecha_fin, premio, id_videojuego, id_usradmin) "+
                                            "values (?,?,?,?,?,?,?)");
            
            // Obtener la fecha actual como un objeto java.sql.Date
            java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());
            
            // Convertir la fecha actual a LocalDate
            LocalDate localDate = fechaActual.toLocalDate();

            // Sumar 30 días
            LocalDate nuevaFecha = localDate.plusDays(30);

            // Convertir la nueva fecha de LocalDate a java.sql.Date
            Date fechaSumada = Date.valueOf(nuevaFecha);
            
            t.setFecha_inicio(fechaActual);
            t.setFecha_final(fechaSumada);
            
            stmTorneo.setInt(1, t.getId());
            stmTorneo.setString(2, t.getNombre());
            stmTorneo.setDate(3, fechaActual);
            stmTorneo.setDate(4, fechaSumada);
            stmTorneo.setInt(5, t.getPremio());
            stmTorneo.setInt(6, t.getVideojuego().getId());
            stmTorneo.setString(7, t.getAdministrador().getId());
            stmTorneo.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmTorneo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }


    }

    public int torneosGanados(String id){
        int resultado = 0;
        Connection con;
        PreparedStatement stmUsuarios=null;
        ResultSet rsUsuarios;

        con=this.getConexion();

        String consulta = "select count(*) as torneos_ganados\n" +
                "from torneo \n" +
                "where ganador like ?";



        try  {
            stmUsuarios=con.prepareStatement(consulta);
            stmUsuarios.setString(1, id);
            rsUsuarios=stmUsuarios.executeQuery();

            if (rsUsuarios.next())
            {
                resultado = rsUsuarios.getInt("torneos_ganados");

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
    
}