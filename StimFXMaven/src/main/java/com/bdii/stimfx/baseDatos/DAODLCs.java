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
    
    public void insertarDLC(DLC d){
        Connection con;
        PreparedStatement stmDLC=null;
        
        con=super.getConexion();
        
        try {
            stmDLC=con.prepareStatement("insert into dlc(id_videojuego, id_dlc, nombre, descripcion, precio, fecha_lanzamiento) "+
                                            "values (?,?,?,?,?,?)");          
            
            // Obtener la fecha actual como un objeto java.sql.Date
            java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());        
                
            stmDLC.setInt(1, d.getIdVideojuego());
            stmDLC.setInt(2, d.getIdDLC());
            stmDLC.setString(3, d.getNombre());
            stmDLC.setString(4, d.getDescripcion());
            stmDLC.setDouble(5, d.getPrecio());
            stmDLC.setDate(6, fechaActual);
            stmDLC.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmDLC.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    public void borrarDLC(int id){
        Connection con;
        PreparedStatement stmDLC=null;
        
        con=super.getConexion();
        
        try{
            stmDLC=con.prepareStatement("delete from dlc where id_dlc = ?");
            stmDLC.setInt(1, id);
            stmDLC.executeUpdate();
        
        }catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmDLC.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    
    public java.util.List<DLC> consultarDLCsVideojuego(Videojuego v){
        java.util.List<DLC> resultado = new java.util.ArrayList<DLC>();
        DLC dlcActual =null;
        Connection con;
        PreparedStatement stmDLC=null;
        ResultSet rsDLC;
        
        con=this.getConexion();
        
        String consulta = "select * "+
                            "from dlc "+
                            "where id_videojuego = ?";
        
        
        try{
            stmDLC=con.prepareStatement(consulta);
            rsDLC=stmDLC.executeQuery();
            while (rsDLC.next())
            {
                dlcActual = new DLC(v.getId(), rsDLC.getInt("id_dlc"), rsDLC.getString("nombre"),
                                        rsDLC.getString("nombre"), rsDLC.getInt("precio"), rsDLC.getDate("fecha_lanzamiento"));
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
