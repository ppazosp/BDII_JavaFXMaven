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
            stmResenhas=con.prepareStatement("insert into resenha(id_videojuego, id_usr, comentario, valoracion) "+
                                            "values (?,?,?,?)");

            stmResenhas.setInt(1, r.getId_videojuego());
            stmResenhas.setString(2, r.getId_usuario());
            stmResenhas.setString(3, r.getComentario());
            stmResenhas.setFloat(4, r.getValoracion());
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
        PreparedStatement stmResenhas=null, stmMeGusta=null;
        ResultSet rsResenha = null, rsMeGusta =null;
        
        con=super.getConexion();

        
        String consulta= "select * from resenha where id_videojuego = ?;";
                try {
            stmResenhas=con.prepareStatement(consulta);
            stmResenhas.setInt(1, v.getId());
            rsResenha=stmResenhas.executeQuery();
            
            while(rsResenha.next()){
                Resenha resenha = new Resenha(v.getId(), rsResenha.getInt("id_resenha"), rsResenha.getString("id_usr"), rsResenha.getString("comentario"),
                      rsResenha.getInt("valoracion"));

                String consulta1= "SELECT id_videojuego, id_resenha, COUNT(*) AS numero_megustas\n" +
                        "FROM me_gusta " +
                        "WHERE id_videojuego = ? AND id_resenha = ?\n" +
                        "GROUP BY id_videojuego, id_resenha;";
                try {
                    stmMeGusta=con.prepareStatement(consulta1);
                    stmMeGusta.setInt(1, v.getId());
                    stmMeGusta.setInt(2, resenha.getIdResenha());
                    rsMeGusta=stmMeGusta.executeQuery();

                    if(rsMeGusta.next()){
                        resenha.setLikes(rsMeGusta.getInt("numero_megustas"));
                    }

                } catch (SQLException e){
                    System.out.println(e.getMessage());
                    this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
                }finally{
                    try {stmMeGusta.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
                }
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

    public void insertarMeGusta(String id_usr, int id_v, int id_resenha){
        Connection con;
        PreparedStatement stmResenha = null;

        con = super.getConexion();

        try {
            stmResenha = con.prepareStatement("insert into me_gusta(id_usuario, id_videojuego, id_resenha) " +
                                                               " values (?,?,?)");

            stmResenha.setString(1, id_usr);
            stmResenha.setInt(2, id_v);
            stmResenha.setInt(3, id_resenha);

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

    public void borrarMeGusta(String id_usr, int id_v, int id_resenha){
        Connection con;
        PreparedStatement stmResenha = null;

        con = super.getConexion();

        try {
            stmResenha = con.prepareStatement("delete from me_gusta " +
                    "where id_usuario = ? and id_videojuego = ? and id_resenha = ? ");

            stmResenha.setString(1, id_usr);
            stmResenha.setInt(2, id_v);
            stmResenha.setInt(3, id_resenha);

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

    public boolean isLiked(String id_usr, int id_v, int id_resenha)
    {
        Connection con;
        PreparedStatement stmResenhas=null;
        ResultSet rsResenha = null;

        con=super.getConexion();


        String consulta= "select * from me_gusta " +
                "where id_usuario = ? and id_videojuego = ? and id_resenha = ? ;";
        try {
            stmResenhas=con.prepareStatement(consulta);
            stmResenhas.setString(1, id_usr);
            stmResenhas.setInt(2, id_v);
            stmResenhas.setInt(3, id_resenha);

            rsResenha=stmResenhas.executeQuery();

            if(rsResenha.next()){
                return true;
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        }finally{
            try {stmResenhas.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }

        return false;

    }

    public void updateLikes(Resenha r)
    {
        Connection con;
        PreparedStatement stmResenhas=null;
        ResultSet rsResenha = null;

        con=super.getConexion();


        String consulta= "SELECT COUNT(*) AS numero_megustas " +
                "FROM me_gusta " +
                "WHERE id_videojuego = ? AND id_resenha = ? ;";

        try {
            stmResenhas=con.prepareStatement(consulta);
            stmResenhas.setInt(1, r.getId_videojuego());
            stmResenhas.setInt(2, r.getIdResenha());

            rsResenha=stmResenhas.executeQuery();

            if(rsResenha.next()){
                r.setLikes(rsResenha.getInt("numero_megustas"));
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        }finally{
            try {stmResenhas.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
}
