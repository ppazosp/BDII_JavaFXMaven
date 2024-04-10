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


import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author alumnogreibd
 */
public class DAODLCs extends AbstractDAO{
    public DAODLCs (Connection conexion, com.bdii.stimfx.aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public java.util.List<DLC> consultarDLCsVideojuego(Videojuego v){
        
        java.util.List<DLC> resultado = new java.util.ArrayList<DLC>();
        DLC dlcActual =null;
        Connection con;
        PreparedStatement stmDLC=null;
        ResultSet rsDLC;
        
        con=this.getConexion();
        
        String consulta = "select * "+
                            "from DLC "+
                            "where idVideojuego = ?";
        
        
        try{
            stmDLC=con.prepareStatement(consulta);
            rsDLC=stmDLC.executeQuery();
            while (rsDLC.next())
            {
                dlcActual = new DLC(v.getId(), rsDLC.getInt("idDLC"), rsDLC.getString("nombre"),
                                        rsDLC.getString("nombre"), rsDLC.getInt("precio"), rsDLC.getDate("fechaLanzamiento"));
                resultado.add(dlcActual);
            }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmDLC.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
        return resultado;   
    }
    
    
    
    
}
