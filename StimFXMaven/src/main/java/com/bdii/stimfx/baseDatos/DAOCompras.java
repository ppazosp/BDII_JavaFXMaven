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
    
    public void insertarCompra(int id_videojuego, int id_usuario){
        Connection con;
        PreparedStatement stmCompra=null;
        
        con=super.getConexion();
        
        try {   // ID DE COMPRA AUTOMATICO???
            stmCompra=con.prepareStatement("insert into comprar(id_videojuego, id_usr, fecha_compra) "+
                                            "values (?,?,?)");
            // Obtener la fecha actual como un objeto java.sql.Date
            java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());
            
            stmCompra.setInt(1, id_videojuego);
            stmCompra.setInt(2, id_usuario);
            stmCompra.setDate(3, fechaActual);
            stmCompra.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCompra.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public Integer contarJuegosUsuario(String id_usuario){
        int juegosUsuario=0;
        Connection con;
        PreparedStatement stmCompras=null;
        ResultSet rsCompras;

        con=this.getConexion();

        String consulta = "select count(*) "+
                "from comprar "+
                "where id_usr::text like ? ";

        try {
            stmCompras=con.prepareStatement(consulta);
            stmCompras.setString(1, "%"+id_usuario+"%");
            rsCompras=stmCompras.executeQuery();

            if (rsCompras.next()) {
                juegosUsuario = rsCompras.getInt(1);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmCompras.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return juegosUsuario;
    }
    
    public java.util.List<Integer> consultarJuegosUsuario(int id_usr){
        List<Integer> resultado = new ArrayList<Integer>();
        Integer videojuegoActual;
        Connection con;
        PreparedStatement stmCompras=null;
        ResultSet rsCompras;

        con=this.getConexion();

        String consulta = "select id_videojuego from comprar " +
                            "where id_usr = ? " +
                            "and fecha_devolucion is null " +
                            "order by fecha_compra desc";

        try{
            stmCompras=con.prepareStatement(consulta);
            stmCompras.setInt(1, id_usr);
            rsCompras=stmCompras.executeQuery();
            while (rsCompras.next())
            {
                videojuegoActual = rsCompras.getInt("id_usr");
                resultado.add(videojuegoActual);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmCompras.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
    
    
    
    
    
}