/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;

import com.bdii.stimfx.aplicacion.FachadaAplicacion;
import com.bdii.stimfx.aplicacion.Torneo;
import com.bdii.stimfx.aplicacion.Usuario;
import com.bdii.stimfx.aplicacion.Videojuego;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

            stmTorneo.setInt(1, t.getId());
            stmTorneo.setString(2, t.getNombre());
            stmTorneo.setDate(3, t.getFecha_inicio());
            stmTorneo.setDate(4, t.getFecha_final());
            stmTorneo.setInt(5, t.getPremio());
            stmTorneo.setInt(6, t.getVideojuego().getId());
            stmTorneo.setString(7, t.getAdministrador().getId());
            stmTorneo.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          FachadaAplicacion.muestraExcepcion(e.getMessage());
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

    public List<Torneo> consultarTorneos(String nombre)
    {
        List<Torneo> resultado = new ArrayList<>();
        Torneo torneoActual;
        Connection con;
        PreparedStatement stmTorneos=null;
        ResultSet rsTorneos;

        con=this.getConexion();

        try{
            stmTorneos=con.prepareStatement(" select t.id, t.nombre, fecha_inicio, fecha_fin, premio, ganador, id_videojuego, id_usradmin, imagen " +
                                                "from torneo t join videojuego v on id_videojuego = v.id " +
                    " where LOWER(t.nombre) like LOWER(?)" +
                    "order by t.nombre");
            stmTorneos.setString(1, "%"+nombre+"%");
            rsTorneos=stmTorneos.executeQuery();
            while (rsTorneos.next())
            {
                Videojuego v = new Videojuego(rsTorneos.getInt("id_videojuego"), FachadaAplicacion.bytesToImage(rsTorneos.getBytes("imagen")));
                Usuario u = new Usuario(rsTorneos.getString("id_usradmin"));
                torneoActual = new Torneo(rsTorneos.getInt("id"), rsTorneos.getString("nombre"), rsTorneos.getDate("fecha_inicio"),
                rsTorneos.getDate("fecha_fin"), rsTorneos.getInt("premio"), rsTorneos.getString("ganador"), v, u);
                resultado.add(torneoActual);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        }finally{
            try {stmTorneos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }

        return resultado;
    }

    public void participarTorneo(String u_id, int t_id)
    {
        Connection con;
        PreparedStatement stmTorneo=null;

        con=super.getConexion();

        try {
            stmTorneo=con.prepareStatement("insert into jug_participa_torneo(id_jugador, id_torneo) "+
                    "values (?,?)");

            stmTorneo.setString(1, u_id);
            stmTorneo.setInt(2, t_id);

            stmTorneo.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        }finally{
            try {stmTorneo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }

    }

    public void retirarseTorneo(String u_id, int t_id)
    {
        Connection con;
        PreparedStatement stmTorneo=null;

        con=super.getConexion();

        try {
            stmTorneo=con.prepareStatement("delete from jug_participa_torneo " +
                    "where id_jugador = ? and id_torneo = ? ");

            stmTorneo.setString(1, u_id);
            stmTorneo.setInt(2, t_id);

            stmTorneo.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        }finally{
            try {stmTorneo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public List<Usuario> consultarParticipantes(int t_id)
    {
        List<Usuario> resultado = new ArrayList<>();
        Usuario usuarioActual;
        Connection con;
        PreparedStatement stmParticipantes=null;
        ResultSet rsTorneos;

        con=this.getConexion();

        try{
            stmParticipantes=con.prepareStatement(" select u.id from usuario u " +
                    "where u.id in " +
                    "(select id_jugador from jug_participa_torneo where id_torneo = ?)  ");

            stmParticipantes.setInt(1, t_id);
            rsTorneos=stmParticipantes.executeQuery();
            while (rsTorneos.next())
            {
                Usuario u = new Usuario(rsTorneos.getString("id"));

                resultado.add(u);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        }finally{
            try {stmParticipantes.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }

        return resultado;
    }

    public void setGanador(String u_id, int t_id) {
        Connection con;
        PreparedStatement stmTorneo = null;

        con = super.getConexion();

        try {
            stmTorneo = con.prepareStatement("update torneo " +
                    "set ganador = ? " +
                    "where id = ? ");

            stmTorneo.setString(1, u_id);
            stmTorneo.setInt(2, t_id);

            stmTorneo.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmTorneo.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }


}