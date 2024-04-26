/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;

import java.sql.Date;
import java.sql.DriverManager;

import com.bdii.stimfx.aplicacion.*;

import java.util.List;

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
    private DAOComunidades daoComunidades;
    private DAODemos daoDemos;

    public FachadaBaseDatos (com.bdii.stimfx.aplicacion.FachadaAplicacion fa){

        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
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

        this.fa=fa;
        daoV = new DAOVideojuegos(conexion, fa);
        daoCategorias = new DAOCategorias(conexion, fa);
        daoU = new DAOUsuarios(conexion, fa);
        daoD = new DAODLCs(conexion, fa);
        daoCompras = new DAOCompras(conexion, fa);
        daoComunidades = new DAOComunidades(conexion, fa);
        daoR = new DAOReseñas(conexion, fa);
        daoT = new DAOTorneos(conexion, fa);
        daoP = new DAOPlataformas(conexion, fa);
        daoDemos = new DAODemos(conexion, fa);
    }

    public void inicializarbd()
    {
        daoV.inicializarBaseDatos();
    }

    public java.util.List<Integer> consultarJuegosUsuario(int id_usuario){
        return daoCompras.consultarJuegosUsuario(id_usuario);
    }

    public void consultarNumeroDescargas(Videojuego v){
        daoCompras.contarComprasJuego(v);
    }

    public void insertarDemo(Demo d) {daoDemos.insertarDemo(d);}

    public Demo consultarDemo(int mes, int ano){return daoDemos.consultarDemo(mes, ano);};

    //Hace falta el id
    public List<Videojuego> consultaVideojuegos(String nombre){  // Abajo hay una con id, aunq con nombre hace falta
        return daoV.consultaVideojuegos(nombre);
    }
    
    public void insertarVideojuego(Videojuego v){
        daoV.insertarVideojuego(v);
    }

    public void borrarVideojuego(int id) {
        daoV.borrarVideojuego(id);
    }
    
    public Videojuego consultarVideojuego(Integer v){
        return daoV.consultarVideojuego(v);
    }
    public Videojuego consultarVideojuego(String v){
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
    
    public void borrarUsuario(String id){
        daoU.borrarUsuario(id);
    }
    
    public java.util.List<Usuario> consultarUsuarios(Integer id, String nombre){
        return daoU.consultarUsuarios(id, nombre);
    }

    public java.util.List<Usuario> consultarUsuariosNoSeguidos(String id, String busq){
        return daoU.consultarUsuariosNoSeguidos(id, busq);
    }
    
    public void insertarCompra(int id_videojuego, String id_usuario){
        daoCompras.insertarCompra(id_videojuego, id_usuario);
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

    public java.util.List<DLC> consultarDLCsVideojuego(Videojuego v){
        return daoD.consultarDLCsVideojuego(v);
    }
    
    public void insertarDLC(DLC d){
        daoD.insertarDLC(d);
    }
    
    public void borrarDLC(int d){
        daoD.borrarDLC(d);
    }
    
    public void insertarPlataforma(String nombre, String path){
        daoP.insertarPlataforma(nombre, path);
    }
    
    public void borrarPlataforma(String nombre){
        daoP.borrarPlataforma(nombre);
    }
    
    public java.util.List<String> consultarPlataformas(String nombre){
        return daoP.consultarPlataformas(nombre);
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
    
    public void seguir(String idU1, String idU2){
        daoU.seguir(idU1, idU2);
    }
    
    public void dejarSeguir(String idU1, String idU2){
        daoU.dejarSeguir(idU1, idU2);
    }

    
    public java.util.List<Usuario> consultarSeguidos(String idU1){
        return daoU.consultarSeguidos(idU1);
    }
    
    public java.util.List<String> consultarSeguidores(String idU2){
        return daoU.consultarSeguidores(idU2);
    }
    
    public void bloquearSeguidor(String idU1, String idU2){
        daoU.bloquearSeguidor(idU2, idU1);
    }

    public Integer contarJuegosUsuario(String id_usuario){
        return daoCompras.contarJuegosUsuario(id_usuario);
    }

    public void insertarComunidad(Comunidad c){
        daoComunidades.insertarComunidad(c);
    }

    public void borrarComunidad(Comunidad c){
        daoComunidades.borrarComunidad(c);
    }

    public java.util.List<Comunidad> consultarComunidades(String nombre){
        return daoComunidades.consultarComunidades(nombre);
    }

    public void insertarJugadorEquipo(String id_usuario, Comunidad c){
        daoComunidades.insertarJugadorEquipo(id_usuario, c);
    }

    public void salirJugadorEquipo(String id_usuario){
        daoComunidades.salirJugadorEquipo(id_usuario);
    }

    public Comunidad consultarEquipoJugador(String id_usuario){
        return daoComunidades.consultarEquipoJugador(id_usuario);
    }

    public java.util.List<Integer> consultarJugadoresEquipo(Comunidad c){
        return daoComunidades.consultarJugadoresEquipo(c);
    }

    public Integer contarMiembrosEquipo(Comunidad c) {
        return daoComunidades.contarMiembrosEquipo(c);
    }

    public List<Videojuego> consultaVideoJuegosInicio(){
        return daoV.consultaVideoJuegosInicio();
    }

    public Usuario validarUsuario(String id, String clave){
        return daoU.validarUsuario(id, clave);
    }

    public List<Torneo> consultarTorneos(String nombre)
    {
        return daoT.consultarTorneos(nombre);
    }

    public Videojuego proximoVideojuego(){
        return  daoV.proximoVideojuego();
    }

    public int torneosGanados(String id){
        return daoT.torneosGanados(id);
    }

    public void participarTorneo(String u_id, int t_id)
    {
        daoT.participarTorneo(u_id, t_id);
    }

    public void retirarseTorneo(String u_id, int t_id)
    {
        daoT.retirarseTorneo(u_id, t_id);
    }

    public List<Usuario> consultarParticipantes(int t_id)
    {
        return daoT.consultarParticipantes(t_id);
    }

    public List<Plataforma> consultarPlataformasVideoJuego(int id){
        return daoP.consultarPlataformasVideoJuego(id);
    }

    public Usuario modificarUsuario(Usuario u){
        return daoU.modificarUsuario(u);
    }

    public List<DLC> consultarDLCsVideojuegoUsuario(int id_v, String id_u){
        return daoD.consultarDLCsVideojuegoUsuario(id_v, id_u);
    }

    public void comprarDLC(DLC d, String id_u, Date date){
        daoD.comprarDLC(d, id_u, date);
    }

    public void devolverDLC(DLC d, String id_u){
        daoD.devolverDLC(d, id_u);
    }
    public boolean existeUsuario(String id){
        return daoU.existeUsuario(id);
    }

    public java.util.List<Videojuego> consultarVideojuegosUsuario(String id){
        return daoU.consultarVideojuegos(id);
    };

    public boolean tieneComunidad(String usr_id){
        return daoComunidades.tieneComunidad(usr_id);
    }
}
