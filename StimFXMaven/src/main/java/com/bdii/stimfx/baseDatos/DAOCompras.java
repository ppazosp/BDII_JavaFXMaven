/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import com.bdii.stimfx.aplicacion.Videojuego;


import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author alumnogreibd
 */
public class DAOCompras extends AbstractDAO{
    
    public DAOCompras (Connection conexion, com.bdii.stimfx.aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public void insertarCompra(int id_videojuego, int id_usuario, int precio){
        Connection con;
        PreparedStatement stmCompra=null;
        
        con=super.getConexion();
        
        try {   // ID DE COMPRA AUTOMATICO???
            stmCompra=con.prepareStatement("insert into comprar(id_videojuego, id_usr, fecha_compra, precio) "+
                                            "values (?,?,?,?)");
            // Obtener la fecha actual como un objeto java.sql.Date
            java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());
            
            stmCompra.setInt(1, id_videojuego);
            stmCompra.setInt(2, id_usuario);
            stmCompra.setDate(3, fechaActual);
            stmCompra.setDouble(4, precio);
            stmCompra.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCompra.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    
    
    
    
    
    
    
}