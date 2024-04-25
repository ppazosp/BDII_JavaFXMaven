/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;

import com.bdii.stimfx.aplicacion.Comunidad;
import com.bdii.stimfx.aplicacion.FachadaAplicacion;
import com.bdii.stimfx.aplicacion.Usuario;

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
public class DAOComunidades extends AbstractDAO{
    public DAOComunidades(Connection conexion, FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public void insertarComunidad(Comunidad c){
        Connection con;
        PreparedStatement stmEquipo=null;
        
        con=super.getConexion();
        
        try {
            stmEquipo=con.prepareStatement("insert into comunidad(nombre) "+
                                            "values (?)");
            stmEquipo.setString(1, c.getNombre());
            stmEquipo.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmEquipo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public void borrarComunidad(Comunidad c){
        Connection con;
        PreparedStatement stmEquipo=null;

        con=super.getConexion();

        try{
            stmEquipo=con.prepareStatement("delete from comunidad where nombre = ?");
            stmEquipo.setString(1, c.getNombre());
            stmEquipo.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmEquipo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public java.util.List<Comunidad> consultarComunidades(String nombre){
        java.util.List<Comunidad> resultado = new java.util.ArrayList<Comunidad>();
        Comunidad comunidadActual;
        Connection con;
        PreparedStatement stmEquipos=null;
        ResultSet rsEquipos;

        con=this.getConexion();

        String consulta = "select * from equipo_competitivo ";

        if (nombre != null) consulta += "where nombre like ? ";


        try  {
            stmEquipos=con.prepareStatement(consulta);
            if (nombre != null) stmEquipos.setString(1, "%"+nombre+"%");
            rsEquipos=stmEquipos.executeQuery();
            while (rsEquipos.next())
            {
                comunidadActual = new Comunidad(rsEquipos.getString("nombre"));

                resultado.add(comunidadActual);
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        }finally{
            try {
                if (stmEquipos != null) {
                    stmEquipos.close();
                }
            } catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public void insertarJugadorEquipo(int id_usuario, Comunidad c){
        Connection con;
        PreparedStatement stmEquipo=null;

        con=super.getConexion();

        try {
            stmEquipo=con.prepareStatement("insert into forma_parte_equipo(id_jugador, nombre_equipo, fecha_inicio) "+
                    "values (?,?,?)");

            // Obtener la fecha actual como un objeto java.sql.Date
            java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());

            stmEquipo.setInt(1, id_usuario);
            stmEquipo.setString(2, c.getNombre());
            stmEquipo.setDate(3, fechaActual);
            stmEquipo.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmEquipo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public void salirJugadorEquipo(int id_usuario, Comunidad c){
        Connection con;
        PreparedStatement stmEquipo=null;

        con=super.getConexion();

        try {
            stmEquipo=con.prepareStatement("update forma_parte_equipo " +
                                                "set fecha_fin = ? " +
                                                "where id_jugador = ? and nombre_equipo = ? " +
                                                "and fecha_fin is null");
            // Obtener la fecha actual como un objeto java.sql.Date
            java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());

            stmEquipo.setDate(1, fechaActual);
            stmEquipo.setInt(2, id_usuario);
            stmEquipo.setString(3, c.getNombre());
            stmEquipo.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmEquipo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public Comunidad consultarEquipoJugador(int id_usuario){
        Comunidad resultado=null;
        Connection con;
        PreparedStatement stmEquipos=null;
        ResultSet rsEquipos;

        con=this.getConexion();

        try  {
            stmEquipos=con.prepareStatement("select nombre_equipo from forma_parte_equipo " +
                                                "where id_jugador like ? and fecha_fin is null");
            stmEquipos.setInt(1, id_usuario);
            rsEquipos=stmEquipos.executeQuery();
            while (rsEquipos.next())
            {
                resultado = new Comunidad(rsEquipos.getString("nombre"));
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        }finally{
            try {
                if (stmEquipos != null) {
                    stmEquipos.close();
                }
            } catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public java.util.List<Integer> consultarJugadoresEquipo(Comunidad c){
        java.util.List<Integer> resultado = new java.util.ArrayList<Integer>();
        Integer jugadorActual;
        Connection con;
        PreparedStatement stmEquipos=null;
        ResultSet rsEquipos;

        con=this.getConexion();

        try  {
            stmEquipos=con.prepareStatement("select id_jugador from forma_parte_equipo " +
                                                "where nombre_equipo like ? ");
            stmEquipos.setString(1, c.getNombre());
            rsEquipos=stmEquipos.executeQuery();
            while (rsEquipos.next())
            {
                jugadorActual = rsEquipos.getInt("nombre");

                resultado.add(jugadorActual);
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        }finally{
            try {
                if (stmEquipos != null) {
                    stmEquipos.close();
                }
            } catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public Integer contarMiembrosEquipo(Comunidad c){
        Integer miembrosEquipo=0;
        Connection con;
        PreparedStatement stmEquipos=null;
        ResultSet rsEquipos;

        con=this.getConexion();

        String consulta = "select count(*) "+
                "from forma_parte_equipo "+
                "where nombre_equipo like ? "+
                "and fecha_fin is null";

        try {
            stmEquipos=con.prepareStatement(consulta);
            stmEquipos.setString(1, c.getNombre());
            rsEquipos=stmEquipos.executeQuery();

            if (rsEquipos.next()) {
                miembrosEquipo = rsEquipos.getInt(1);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmEquipos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return miembrosEquipo;
    }

    public boolean tieneComunidad(String id_usr){
        boolean tieneComunidad=false;
        Connection con;
        PreparedStatement stmEquipos=null;
        ResultSet rsEquipos;

        con=this.getConexion();

        String consulta = "select * from forma_parte_equipo " +
                "where  id_jugador = ? and fecha_fin is null";

        try {
            stmEquipos=con.prepareStatement(consulta);
            stmEquipos.setString(1, id_usr);
            rsEquipos=stmEquipos.executeQuery();

            if (rsEquipos.next()) {
               tieneComunidad= true;
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmEquipos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return tieneComunidad;
    }

}

