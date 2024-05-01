/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;

import com.bdii.stimfx.aplicacion.*;

import java.sql.Date;
import java.sql.DriverManager;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class FachadaBaseDatos {
    private final FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private final DAOVideojuegos daoV;
    private final DAODLCs daoD;
    private final DAOCategorias daoCategorias;
    private final DAOResenhas daoR;
    private final DAOUsuarios daoU;
    private final DAOTorneos daoT;
    private final DAOCompras daoCompras;
    private final DAOPlataformas daoP;
    private final DAOComunidades daoComunidades;
    private final DAODemos daoDemos;
    private final DAOOpUnicas daoOpUnicas;

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
        daoR = new DAOResenhas(conexion, fa);
        daoT = new DAOTorneos(conexion, fa);
        daoP = new DAOPlataformas(conexion, fa);
        daoDemos = new DAODemos(conexion, fa);
        daoOpUnicas = new DAOOpUnicas(conexion, fa);
    }

    public void inicializarbd()
    {
        daoV.inicializarBaseDatos();
    }

    public java.util.List<Integer> consultarJuegosUsuario(int id_usuario){
        return daoCompras.consultarJuegosUsuario(id_usuario);
    }

    public void devolverCompra(int id_v, String id_usr)
    {
        daoCompras.devolverCompra(id_v, id_usr);
    }

    public void consultarNumeroDescargas(Videojuego v){
        daoCompras.contarComprasJuego(v);
    }

    public void insertarDemo(Demo d) {daoDemos.insertarDemo(d);}
    public void updateDemo(Demo d) {daoDemos.updateDemo(d);}

    public Demo consultarDemo(int mes, int ano) {
        return daoDemos.consultarDemo(mes, ano);
    }

    public List<Demo> consultarDemoAdmin(String a_id)
    {
        return daoDemos.consultarDemoAdmin(a_id);
    }

    //Hace falta el id
    public List<Videojuego> consultarVideojuegos(String nombre){  // Abajo hay una con id, aunq con nombre hace falta
        return daoV.consultarVideojuegos(nombre);
    }

    public List<String> consultarVideojuegos(){  // Abajo hay una con id, aunq con nombre hace falta
        return daoV.consultarVideojuegos();
    }
    
    public void insertarVideojuego(Videojuego v){
        daoV.insertarVideojuego(v);
    }

    public void updateVideojuego(Videojuego v) {
        daoV.updateVideojuego(v);
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
    
    public void insertarResenha(Resenha r){
        daoR.insertarResenha(r);
    }

    public void updateResenha(Resenha r)
    {
        daoR.updateResenha(r);
    }

    public Resenha consultarResenha(int id_v, String id_usr)
    {
        return daoR.consultarResenha(id_v, id_usr);
    }

    public void insertarMeGusta( String id_usr, int id_v, int i_res)
    {
        daoR.insertarMeGusta(id_usr, id_v, i_res);
    }

    public void borrarMeGusta(String id_usr, int id_v, int i_res)
    {
        daoR.borrarMeGusta(id_usr, id_v, i_res);
    }

    public boolean isLiked(String id_usr, int id_v, int i_res)
    {
        return daoR.isLiked(id_usr, id_v, i_res);
    }

    public void updateLikes(Resenha r)
    {
        daoR.updateLikes(r);
    }
    
    public void insertarUsuario(Usuario u){
        daoU.insertarUsuario(u);
    }

    public void hacerAdmin(String u_id)
    {
        daoU.hacerAdmin(u_id);
    }

    public void quitarAdmin(String u_id)
    {
        daoU.quitarAdmin(u_id);
    }

    public void hacerJugadorCompetitivo(String u_id)
    {
        daoU.hacerJugadorCompetitivo(u_id);
    }

    public void hacerEditor(String u_id)
    {
        daoU.hacerEditor(u_id);
    }

    public Usuario consultarUsuario(String id_usr)
    {
        return daoU.consultarUsuario(id_usr);
    }

    public void insertarTorneo(Torneo t){
        daoT.insertarTorneo(t);
    }
    
    public void borrarUsuario(String id){
        daoU.borrarUsuario(id);
    }
    
    public java.util.List<Usuario> consultarUsuariosNoAdmins(Integer id, String nombre){
        return daoU.consultarUsuariosNoAdmins(id, nombre);
    }

    public java.util.List<Usuario> consultarUsuariosNoAdmins(){
        return daoU.consultarUsuariosNoAdmins();
    }

    public java.util.List<Usuario> consultarUsuariosNoSeguidos(String id, String busq){
        return daoU.consultarUsuariosNoSeguidos(id, busq);
    }
    
    public void insertarCompra(int id_videojuego, double precio,  String id_usuario){
        daoCompras.insertarCompra(id_videojuego, precio,  id_usuario);
    }

    public void insertarFondos(String u_id, double valor)
    {
        daoU.insertarFondos(u_id, valor);
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

    public DLC consultarDLC(int id_v, int id_dlc)
    {
        return daoD.consultarDLC(id_v, id_dlc);
    }

    public void updateDLC(DLC d)
    {
        daoD.updateDLC(d);
    }
    
    public void updatePlataforma(String nombre, String path){
        daoP.updatePlataforma(nombre, path);
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

    public List<Torneo> consultarTorneosAdmin(String a_id)
    {
        return daoT.consultarTorneosAdmin(a_id);
    }

    public void updateTorneo(Torneo t)
    {
        daoT.updateTorneo(t);
    }

    public Torneo consultarTorneo(int t_id)
    {
        return daoT.consultarTorneo(t_id);
    }


    public List<Usuario> consultarParticipantes(int t_id)
    {
        return daoT.consultarParticipantes(t_id);
    }

    public void setGanador(String u_id, int t_id) {
        daoT.setGanador(u_id, t_id);
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
    }

    public boolean tieneComunidad(String usr_id){
        return daoComunidades.tieneComunidad(usr_id);
    }

    public List<Videojuego> consultarVideosjuegosEditor(String id_editor) {
        return daoV.consultarVideojuegosEditor(id_editor);
    }

    public void consultarResenhas(Videojuego v)
    {
        daoR.consultarResenhas(v);
    }

    public float consultarMediaResenhas(Videojuego v)
    {
        return daoR.consultarMediaResenhas(v);
    }


    // SOLO UNA VEZ POR FAVOR POR FAVOR POR FAVOR POR FAVOR POR FAVOR
    public void hashAllPasswords() {
        daoOpUnicas.hashAllPasswords();
    }
}
