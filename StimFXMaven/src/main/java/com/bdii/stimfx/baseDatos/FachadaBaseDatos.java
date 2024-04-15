/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import com.bdii.stimfx.aplicacion.FachadaAplicacion;
import java.util.ArrayList;
import java.util.List;
import com.bdii.stimfx.aplicacion.Videojuego;
import com.bdii.stimfx.aplicacion.DLC;
import com.bdii.stimfx.aplicacion.Reseña;
import com.bdii.stimfx.aplicacion.Usuario;
import com.bdii.stimfx.aplicacion.Torneo;


/**
 *
 * @author alumnogreibd
 */
public class FachadaBaseDatos {
    private FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOVideojuegos daoV;
    private DAODLCs daoD;
    private DAOCategorias daoC;
    private DAOReseñas daoR;
    private DAOUsuarios daoU;
    private DAOTorneos daoT;

    public FachadaBaseDatos (com.bdii.stimfx.aplicacion.FachadaAplicacion fa){

    Properties configuracion = new Properties();
    this.fa=fa;
    FileInputStream arqConfiguracion;

    try {
        arqConfiguracion = new FileInputStream("baseDatos.properties");
        configuracion.load(arqConfiguracion);
        arqConfiguracion.close();

        Properties usuario = new Properties();


        String gestor = configuracion.getProperty("gestor");

        usuario.setProperty("user", configuracion.getProperty("usuario"));
        usuario.setProperty("password", configuracion.getProperty("clave"));
        this.conexion=java.sql.DriverManager.getConnection("jdbc:"+gestor+"://"+
                configuracion.getProperty("servidor")+":"+
                configuracion.getProperty("puerto")+"/"+
                configuracion.getProperty("baseDatos"),
                usuario);

        //Crear DAOs
        daoV= new DAOVideojuegos(conexion, fa);

        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
            //fa.muestraExcepcion(f.getMessage());
        } catch (IOException i){
            System.out.println(i.getMessage());
            //fa.muestraExcepcion(i.getMessage());
        } 
        catch (java.sql.SQLException e){
            System.out.println(e.getMessage());
            //fa.muestraExcepcion(e.getMessage());
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
        return daoC.consultarVideojuegosCategoria(c);
    }
    
    public void insertarReseña(Reseña r){
        daoR.insertarReseña(r);
    }
    
    public void insertarUsuario(Usuario u){
        daoU.insertarUsuario(u);
    }
    
    public void insertarTorneo(Torneo t){
        
    }
}
