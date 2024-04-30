/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;
import java.sql.Connection;

import com.bdii.stimfx.aplicacion.FachadaAplicacion;
import com.bdii.stimfx.aplicacion.Resenha;
import com.bdii.stimfx.aplicacion.Videojuego;

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
            stmResenhas=con.prepareStatement("insert into resenha(id_videojuego, id_usr, comentario) "+
                                            "values (?,?,?)");

            stmResenhas.setInt(1, r.getId_videojuego());
            stmResenhas.setString(2, r.getId_usuario());
            stmResenhas.setString(3, r.getComentario());
            stmResenhas.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmResenhas.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public void updateResenha(Resenha r) {
        Connection con;
        PreparedStatement stmResenha = null;

        con = super.getConexion();

        try {
            stmResenha = con.prepareStatement("update resenha set" +
                    " comentario = ?," +
                    " valoracion = ?" +
                    " where id_videojuego = ? and id_usr = ?;");

            stmResenha.setInt(3, r.getId_videojuego());
            stmResenha.setString(4, r.getId_usuario());
            stmResenha.setString(1, r.getComentario());
            stmResenha.setInt(2, r.getValoracion());

            stmResenha.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmResenha.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public Resenha consultarResenha(int id_v, String id_usr){
        Connection con;
        Resenha r = null;
        PreparedStatement stmResenhas=null;
        ResultSet rsResenha = null;

        con=super.getConexion();


        String consulta= "select * from resenha where id_videojuego = ? and id_usr = ?;";
        try {
            stmResenhas=con.prepareStatement(consulta);
            stmResenhas.setInt(1, id_v);
            stmResenhas.setString(2, id_usr);
            rsResenha=stmResenhas.executeQuery();

            if(rsResenha.next()){
                r = new Resenha(id_v, rsResenha.getInt("id_resenha"), id_usr, rsResenha.getString("comentario"),
                        rsResenha.getInt("valoracion"));
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmResenhas.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }

        return r;
    }

    public void consultarResenhas(Videojuego v){
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
                      rsResenha.getInt("valoracion"));
                v.addResenha(resenha);
            }
            
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmResenhas.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public float consultarMediaResenhas(Videojuego v)
    {
        Connection con;
        PreparedStatement stmResenhas=null;
        ResultSet rsResenha = null;

        float avg = 0;
        int count = 0;

        con=super.getConexion();


        String consulta= "select valoracion from resenha where id_videojuego = ?;";
        try {
            stmResenhas=con.prepareStatement(consulta);
            stmResenhas.setInt(1, v.getId());
            rsResenha=stmResenhas.executeQuery();

            while(rsResenha.next()){
                avg+=rsResenha.getFloat("valoracion");
                count++;
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmResenhas.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        if(avg!=0 && count!=0)
        {
            avg /= count;
            return avg;
        }

        else return 0;
    }

}
