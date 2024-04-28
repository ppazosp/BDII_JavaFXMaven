package com.bdii.stimfx.aplicacion;

import com.bdii.stimfx.baseDatos.FachadaBaseDatos;
import com.bdii.stimfx.gui.FachadaGUI;

public class GestionComunidad {
    FachadaGUI fgui;
    FachadaBaseDatos fbd;

    public GestionComunidad(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }
    public void insertarComunidad(Comunidad c){
        fbd.insertarComunidad(c);
    }

    // Funcion para borrar una comunidad
    public void borrarComunidad(Comunidad c){
        fbd.borrarComunidad(c);
    }

    // Funcion para mirar comunidades en el buscador, encontrar una comunidad especifica, a partir de algo o todas si la barra esta vacía
    public java.util.List<Comunidad> consultarComunidades(String nombre){
        return fbd.consultarComunidades(nombre);
    }

    // Funcion para insertar a un usuario en una comunidad.
    // QUE SEA COMPETITIVO EL USUARIO NO ESTA IMPLEMENTADO A NIVEL BAJO (por lo menos por ahora) !!!!!!!!!!!!!!!!
    public void insertarJugadorEquipo(String id_usuario, Comunidad c){
        fbd.insertarJugadorEquipo(id_usuario, c);
    }

    // Funcion para hacer salir de una comunidad a un usuario
    public void salirJugadorEquipo(String id_usuario){
        fbd.salirJugadorEquipo(id_usuario);
    }

    // Funcion para consultar el equipo en el que esta un usuario
    public Comunidad consultarEquipoJugador(String id_usuario){
        return fbd.consultarEquipoJugador(id_usuario);
    }

    // Funcion para consultar los jugadores que pertenecen a un equipo en concreto
    public java.util.List<Integer> consultarJugadoresEquipo(Comunidad c){
        return fbd.consultarJugadoresEquipo(c);
    }

    // Funcion para contar los miembros de un equipo
    public Integer contarMiembrosEquipo(Comunidad c) {
        return fbd.contarMiembrosEquipo(c);
    }

    public boolean tieneComunidad(Usuario u){
        return fbd.tieneComunidad(u.getId());
    }
}
