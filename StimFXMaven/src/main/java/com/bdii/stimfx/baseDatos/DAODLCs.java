/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;

import com.bdii.stimfx.aplicacion.DLC;
import com.bdii.stimfx.aplicacion.FachadaAplicacion;
import com.bdii.stimfx.aplicacion.Videojuego;

import java.sql.*;

/**
 *
 * @author alumnogreibd
 */
public class DAODLCs extends AbstractDAO {
    public DAODLCs(Connection conexion, com.bdii.stimfx.aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public void insertarDLC(DLC d) {
        Connection con;
        PreparedStatement stmDLC = null;

        con = super.getConexion();

        try {
            stmDLC = con.prepareStatement("insert into dlc(id_videojuego, nombre, precio) " +
                    "values (?,?,?)");


            stmDLC.setInt(1, d.getIdVideojuego());
            stmDLC.setString(2, d.getNombre());
            stmDLC.setDouble(3, d.getPrecio());

            stmDLC.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmDLC.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public void updateDLC(DLC d) {
        Connection con;
        PreparedStatement stmDLC = null;

        con = super.getConexion();

        try {
            stmDLC = con.prepareStatement("update dlc set" +
                    " nombre = ?," +
                    " precio = ? " +
                    "where id_videojuego = ? and id_dlc = ?;");

            stmDLC.setString(1, d.getNombre());
            stmDLC.setDouble(2, d.getPrecio());
            stmDLC.setInt(3, d.getIdVideojuego());
            stmDLC.setInt(4, d.getIdDLC());

            stmDLC.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmDLC.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public DLC consultarDLC(int id_v, int id_dlc){  // Sirve para la transaccion de obtener el videojuego asociado a un dlc y tmbn para obtener videojuegos asociados a una cartegoria
        DLC dlc = null;
        Connection con;
        PreparedStatement stmDLCs=null;
        ResultSet rsDLCs;

        con=this.getConexion();

        String consulta = "select * "+
                "from dlc "+
                "where id_videojuego = ? and id_dlc = ?";


        try{
            stmDLCs=con.prepareStatement(consulta);
            stmDLCs.setInt(1, id_v);
            stmDLCs.setInt(2, id_dlc);
            rsDLCs=stmDLCs.executeQuery();
            if (rsDLCs.next()){
                dlc = new DLC(rsDLCs.getInt("id_videojuego"), rsDLCs.getInt("id_dlc"),
                        rsDLCs.getString("nombre"), rsDLCs.getDouble("precio"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        }finally{
            try {stmDLCs.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }

        return dlc;
    }

    public void borrarDLC(int id) {
        Connection con;
        PreparedStatement stmDLC = null;

        con = super.getConexion();

        try {
            stmDLC = con.prepareStatement("delete from dlc where id_dlc = ?");
            stmDLC.setInt(1, id);
            stmDLC.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmDLC.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }


    public java.util.List<DLC> consultarDLCsVideojuego(Videojuego v) {
        java.util.List<DLC> resultado = new java.util.ArrayList<DLC>();
        DLC dlcActual = null;
        Connection con;
        PreparedStatement stmDLC = null;
        ResultSet rsDLC;

        con = this.getConexion();

        String consulta = "select * " +
                "from dlc " +
                "where id_videojuego = ?";


        try {
            stmDLC = con.prepareStatement(consulta);
            stmDLC.setInt(1, v.getId());
            rsDLC = stmDLC.executeQuery();
            while (rsDLC.next()) {
                dlcActual = new DLC(v.getId(), rsDLC.getInt("id_dlc"), rsDLC.getString("nombre"),
                         rsDLC.getInt("precio"));
                resultado.add(dlcActual);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmDLC.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    //Funcion para pazosmaster
    public java.util.List<DLC> consultarDLCsVideojuegoUsuario(int id_v, String id_u) {
        java.util.List<DLC> resultado = new java.util.ArrayList<DLC>();
        DLC dlcActual = null;
        Connection con;
        PreparedStatement stmDLC = null;
        ResultSet rsDLC;

        con = this.getConexion();

        String consulta = "select * " +
                "from dlc  d inner join comprardlc cd on d.id_dlc = cd.id_dlc" +//tabla comprar dlc
                " where d.id_videojuego = ? and id_usr = ?";


        try {
            stmDLC = con.prepareStatement(consulta);
            stmDLC.setInt(1, id_v);
            stmDLC.setString(2, id_u);
            rsDLC = stmDLC.executeQuery();
            while (rsDLC.next()) {
                dlcActual = new DLC(id_v, rsDLC.getInt("id_dlc"), rsDLC.getString("nombre"),
                        rsDLC.getInt("precio"));
                resultado.add(dlcActual);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmDLC.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    public void comprarDLC(DLC d, String id_u, Date date) {
        Connection con;
        PreparedStatement stmDLC = null, stmMonedero = null;

        con = super.getConexion();

        try {
            stmDLC = con.prepareStatement("insert into comprardlc(id_videojuego, id_dlc, id_usr, fechacompra) " +
                    "values (?,?,?,?)");//Cambiar insert

            // Obtener la fecha actual como un objeto java.sql.Date
            java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());

            stmDLC.setInt(1, d.getIdVideojuego());
            stmDLC.setInt(2, d.getIdDLC());
            stmDLC.setString(3, id_u);
            stmDLC.setDate(4, date);
            stmDLC.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        } finally {
            try {
                assert stmDLC != null;
                stmDLC.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        try {
            stmMonedero = con.prepareStatement("UPDATE monedero\n" +
                    "SET dinero = dinero - ?\n" +
                    "WHERE id_comun = ?;");//Cambiar insert

            // Obtener la fecha actual como un objeto java.sql.Date
            java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());

            stmMonedero.setDouble(1, d.getPrecio());
            stmMonedero.setString(2, id_u);
            stmMonedero.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        } finally {
            try {
                assert stmMonedero != null;
                stmMonedero.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public void devolverDLC(DLC d, String id_u) {
        Connection con;
        PreparedStatement stmDLC = null;

        con = super.getConexion();

        try {
            stmDLC = con.prepareStatement("delete from comprardlc where id_dlc = ? and id_videojuego  = ? and id_usr = ?");
            stmDLC.setInt(1, d.getIdDLC());
            stmDLC.setInt(1, d.getIdVideojuego());
            stmDLC.setString(1, id_u);
            stmDLC.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmDLC.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }
}
