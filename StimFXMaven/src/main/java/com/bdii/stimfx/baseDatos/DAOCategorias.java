/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import com.bdii.stimfx.aplicacion.Videojuego;
import com.bdii.stimfx.aplicacion.Editor;
import com.bdii.stimfx.aplicacion.DLC;
import com.bdii.stimfx.aplicacion.Categoria;


import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author alumnogreibd
 */
public class DAOCategorias extends AbstractDAO{
    
    public DAOCategorias (Connection conexion, com.bdii.stimfx.aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    
    public java.util.List<Integer> consultarVideojuegosCategoria(String nombreCategoria){
        java.util.List<Integer> resultado = new java.util.ArrayList<Integer>();
        Integer idVideojuegoActual;
        Connection con;
        PreparedStatement stmCategorias=null;
        ResultSet rsCategorias;
        
        con=this.getConexion();
        
        String consulta = "select idVideojuego from TenerCategoria where nombreCategoria like ?";
        
        try{
            stmCategorias=con.prepareStatement(consulta);
            stmCategorias.setString(1, "%"+nombreCategoria+"%");
            rsCategorias=stmCategorias.executeQuery();
            while (rsCategorias.next())
            {
                idVideojuegoActual = rsCategorias.getInt("idVideojuego");//new Prestamo(rsPrestamos.getDate("fecha_prestamo"), rsPrestamos.getDate("fecha_devolucion"), rsPrestamos.getDate("fecha_vencimiento"),
                                        //rsPrestamos.getInt("num_ejemplar"), rsPrestamos.getInt("libro"), rsPrestamos.getString("id_usuario"));
                resultado.add(idVideojuegoActual);
            }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCategorias.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;   
        }
    
    
    
    
    
    
    
    
}