package com.bdii.stimfx.baseDatos;
import java.util.Calendar;
import java.sql.Connection;

import com.bdii.stimfx.aplicacion.*;


import java.sql.*;

/**
 *
 * @author alumnogreibd
 */
public class DAODemos extends AbstractDAO {
    public DAODemos(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public void insertarDemo(Demo d) {
        Connection con;
        PreparedStatement stmDemo = null;

        con = this.getConexion();

        try {
            stmDemo = con.prepareStatement("insert into demo(nombre, mes, ano, imagen, id_usradmin) " +
                    "values (?,?,?,?,?)");

            stmDemo.setString(1, d.getNombre());
            stmDemo.setInt(2, d.getMes());
            stmDemo.setInt(3, d.getAno());
            stmDemo.setBytes(4, FachadaAplicacion.imageToBytes(d.getImagen()));
            stmDemo.setString(5, d.getId_usreditor());
            stmDemo.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        } finally {
            try {
                assert stmDemo != null;
                stmDemo.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public Demo consultarDemo(int mes, int ano)
    {
        Connection con;
        PreparedStatement stmDemo=null;
        ResultSet rsDemos;
        Demo resultado = null;

        con=this.getConexion();

        String consulta = "select nombre, mes, ano, imagen, id_usradmin" +
                "   from demo  \n" +
                "   where mes = ? and ano = ?;";

        try{
            stmDemo=con.prepareStatement(consulta);
            stmDemo.setInt(1, mes);
            stmDemo.setInt(2, ano);
            rsDemos=stmDemo.executeQuery();
            while (rsDemos.next())
            {
                resultado = new Demo(rsDemos.getString("nombre"),
                        rsDemos.getInt("mes"), rsDemos.getInt("ano"),
                        FachadaAplicacion.bytesToImage(rsDemos.getBytes("imagen")),
                        rsDemos.getString("id_usradmin"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        }finally{
            try {stmDemo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

}


