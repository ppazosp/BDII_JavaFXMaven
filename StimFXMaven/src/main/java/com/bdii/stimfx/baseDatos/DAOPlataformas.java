/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;
import com.bdii.stimfx.aplicacion.FachadaAplicacion;
import com.bdii.stimfx.aplicacion.Plataforma;
import javafx.scene.image.Image;

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
        
    public void insertarPlataforma(String nombre, String path){
        Connection con;
        PreparedStatement stmPlataforma=null;
        
        con=super.getConexion();

        byte[] icono = FachadaAplicacion.pathToBytes(path);

        try {
            stmPlataforma=con.prepareStatement("update plataforma "+
                                                    "set icono = ? " +
                                                    "where nombre = ?;");
            stmPlataforma.setBytes(1, icono);
            stmPlataforma.setString(2, nombre);
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

    public java.util.List<Plataforma> consultarPlataformasVideoJuego(int id){
        java.util.List<Plataforma> resultado = new java.util.ArrayList<Plataforma>();
        Plataforma plataformaActual;
        Connection con;
        PreparedStatement stmPlataformas=null;
        ResultSet rsPlataformas;

        con=this.getConexion();

        String consulta = "SELECT ptv.nombre_plataforma, p.icono\n" +
                "FROM videojuego v\n" +
                "JOIN plataforma_tiene_videojuego ptv ON v.id = ptv.id_videojuego \n" +
                "JOIN plataforma p ON ptv.nombre_plataforma = p.nombre\n" +
                "WHERE v.id = ?;";

        try  {
            stmPlataformas=con.prepareStatement(consulta);
            stmPlataformas.setInt(1, id);
            rsPlataformas=stmPlataformas.executeQuery();
            while (rsPlataformas.next())
            {
                plataformaActual = new Plataforma(rsPlataformas.getString("nombre_plataforma"),
                        FachadaAplicacion.bytesToImage(rsPlataformas.getBytes("icono")));

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