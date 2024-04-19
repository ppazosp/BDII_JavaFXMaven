/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;


import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author alumnogreibd
 */
public class DAOPlataformas extends AbstractDAO{
    
    public DAOPlataformas (Connection conexion, com.bdii.stimfx.aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
        
    public void insertarPlataforma(String nombre, byte[]icono){
        Connection con;
        PreparedStatement stmPlataforma=null;
        
        con=super.getConexion();


        try {
            stmPlataforma=con.prepareStatement("insert into plataforma(nombre, icono) "+
                                            "values (?, ?)");
            stmPlataforma.setString(1, nombre);
            stmPlataforma.setBytes(2, icono);
            stmPlataforma.executeUpdate();

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {
              assert stmPlataforma != null;
              stmPlataforma.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public void borrarPlataforma(String nombre){
        Connection con;
        PreparedStatement stmPlataforma=null;
        
        con=super.getConexion();
        
        try{
            stmPlataforma=con.prepareStatement("delete from plataforma where nombre = ?");
            stmPlataforma.setString(1, nombre);
            stmPlataforma.executeUpdate();
        }catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmPlataforma.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }  
    }
    
    public java.util.List<String> consultarPlataformas(String nombre){
        java.util.List<String> resultado = new java.util.ArrayList<String>();
        String plataformaActual;
        Connection con;
        PreparedStatement stmPlataformas=null;
        ResultSet rsPlataformas;

        con=this.getConexion();
        
        String consulta = "select * from plataforma ";
        
        if (!nombre.isEmpty()) consulta += "where nombre like ?";

        try  {
        stmPlataformas=con.prepareStatement(consulta);
        if (!nombre.isEmpty()) stmPlataformas.setString(1, "%"+nombre+"%");
        rsPlataformas=stmPlataformas.executeQuery();
        while (rsPlataformas.next())
        {
            plataformaActual = rsPlataformas.getString("nombre");
            
            resultado.add(plataformaActual);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmPlataformas.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
    
    public void insertarPlataformaVideojuego(String nombre, int id_videojuego){
        Connection con;
        PreparedStatement stmPlataforma=null;
        
        con=super.getConexion();
        
        try {
            stmPlataforma=con.prepareStatement("insert into plataforma_tiene_videojuego(nombre_plataforma, id_videojuego) "+
                                            "values (?,?)");          
            stmPlataforma.setString(1, nombre);
            stmPlataforma.setInt(2, id_videojuego);
            stmPlataforma.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmPlataforma.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    public void borrarPlataformaVideojuego(String nombre, int id_videojuego){
        Connection con;
        PreparedStatement stmPlataforma=null;
        
        con=super.getConexion();
        
        try{
            stmPlataforma=con.prepareStatement("delete from plataforma_tiene_videojuego where nombre_plataforma = ? and id_videojuego = ?");
            stmPlataforma.setString(1, nombre);
            stmPlataforma.setInt(2, id_videojuego);
            stmPlataforma.executeUpdate();
        }catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmPlataforma.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }  
    }
    
    
}