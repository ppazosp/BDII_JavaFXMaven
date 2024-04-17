/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import com.bdii.stimfx.aplicacion.FachadaAplicacion;
import java.util.List;
import com.bdii.stimfx.aplicacion.Videojuego;
import com.bdii.stimfx.aplicacion.DLC;
import com.bdii.stimfx.aplicacion.Reseña;
import com.bdii.stimfx.aplicacion.Usuario;
import com.bdii.stimfx.aplicacion.Torneo;
import com.bdii.stimfx.aplicacion.Categoria;

/**
 *
 * @author alumnogreibd
 */
public class FachadaBaseDatos {
    private FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOVideojuegos daoV;
    private DAODLCs daoD;
    private DAOCategorias daoCategorias;
    private DAOReseñas daoR;
    private DAOUsuarios daoU;
    private DAOTorneos daoT;
    private DAOCompras daoCompras;
    private DAOPlataformas daoP;

    public FachadaBaseDatos (com.bdii.stimfx.aplicacion.FachadaAplicacion fa){


    this.fa=fa;
    daoV = new DAOVideojuegos(conexion, fa);
    daoCategorias = new DAOCategorias(conexion, fa);

        try {
            Class.forName("C:\\Users\\Usuario\\.m2\\repository\\org\\postgresql\\Driver");
        }
        catch (java.lang.ClassNotFoundException e) {
            System.out.println("EXCEPCION: " +e.getMessage());
        }

        String url = "jdbc:postgresql://surus.db.elephantsql.com:5432/vzgfiqrg";
        String username = "vzgfiqrg";
        String password = "VguSZP9OqTMKB_gk-05FtRIK-OmTPTLF";

        try {
            conexion = DriverManager.getConnection(url, username, password);

        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    //Hace falta el id
    public List<Videojuego> consultaVideojuegos(String nombre){  // Abajo hay una con id, aunq con nombre hace falta
        return daoV.consultaVideojuegos(nombre);
    }
    
    public void insertarVideojuego(Videojuego v){
        daoV.insertarVideojuego(v);
    }
    
    public java.util.List<DLC> consultarDLCsVideojuego(Videojuego v){
        return daoD.consultarDLCsVideojuego(v);
    }
    
    public Videojuego consultarVideojuego(Integer v){
        return daoV.consultarVideojuego(v);
    }
    
    public java.util.List<Integer> consultarVideojuegosCategoria(String c){
        return daoCategorias.consultarVideojuegosCategoria(c);
    }
    
    public void insertarReseña(Reseña r){
        daoR.insertarReseña(r);
    }
    
    public void insertarUsuario(Usuario u){
        daoU.insertarUsuario(u);
    }
    
    public void insertarTorneo(Torneo t){
        daoT.insertarTorneo(t);
    }
    
    public void borrarUsuario(int id){
        daoU.borrarUsuario(id);
    }
    
    public java.util.List<Usuario> consultarUsuarios(Integer id, String nombre){
        return daoU.consultarUsuarios(id, nombre);
    }
    
    public void insertarCompra(int id_videojuego, int id_usuario, int precio){
        daoCompras.insertarCompra(id_videojuego, id_usuario, precio);
    }
    
    public void insertarCategoria(Categoria c){
        daoCategorias.insertarCategoria(c);
    }
    
    public void borrarCategoria(String nombre){
        daoCategorias.borrarCategoria(nombre);
    }
    
    public java.util.List<Categoria> consultarCategorias(String nombre){
        return daoCategorias.consultarCategorias(nombre);
    }
    
    public void insertarDLC(DLC d){
        daoD.insertarDLC(d);
    }
    
    public void borrarDLC(int d){
        daoD.borrarDLC(d);
    }
    
    public void insertarPlataforma(String nombre){
        daoP.insertarPlataforma(nombre);
    }
    
    public void borrarPlataforma(String nombre){
        daoP.borrarPlataforma(nombre);
    }
    
    public java.util.List<String> consultarPlataformas(String nombre){
        return daoP.consultarPlataformas(nombre);
    }
    
    public java.util.List<String> consultarPlataformasVideojuego(int id_videojuego){
        return daoV.consultarPlataformasVideojuego(id_videojuego);
    }
    
    public void insertarPlataformaVideojuego(String nombre, int id_videojuego){
        daoP.insertarPlataformaVideojuego(nombre, id_videojuego);
    }
    
    public void borrarPlataformaVideojuego(String nombre, int id_videojuego){
        daoP.borrarPlataformaVideojuego(nombre, id_videojuego);
    }
    
    public java.util.List<String> consultarCategoriasVideojuego(int id_videojuego){
        return daoV.consultarCategoriasVideojuego(id_videojuego);
    }
    
    public void seguir(int idU1, int idU2){
        daoU.seguir(idU1, idU2);
    }
    
    public void dejarSeguir(int idU1, int idU2){
        daoU.dejarSeguir(idU1, idU2);
    }
    
    public java.util.List<Integer> consultarSeguidos(int idU1){
        return daoU.consultarSeguidos(idU1);
    }
    
    public java.util.List<Integer> consultarSeguidores(int idU2){
        return daoU.consultarSeguidores(idU2);
    }
    
    public void bloquearSeguidor(int idU1, int idU2){
        daoU.bloquearSeguidor(idU2, idU1);
    }
}
