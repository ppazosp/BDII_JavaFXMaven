/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.aplicacion;

import com.bdii.stimfx.baseDatos.FachadaBaseDatos;
import com.bdii.stimfx.gui.FachadaGUI;
import javafx.scene.image.Image;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
//PONER A TODOS LOS USUARIOS RESPONABILIDADES DE PASAR DE IMAGE A BYTES

/**
 *
 * @author alumnogreibd
 */
public class GestionTorneos {
    FachadaGUI fgui;
    FachadaBaseDatos fbd;

        public GestionTorneos(FachadaGUI fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }

    public void insertarTorneo(Torneo t){
        fbd.insertarTorneo(t);
    }

    public int torneosGanados(Usuario u){
        return fbd.torneosGanados(u.getId());
    }

    public List<Torneo> consultarTorneos(String nombre)
    {
        return fbd.consultarTorneos(nombre);
    }


    public void participarTorneo(Usuario u, Torneo t)
    {
        fbd.participarTorneo(u.getId(), t.getId());
    }

    public List<Usuario> consultarParticipantes(Torneo t)
    {
        return fbd.consultarParticipantes(t.getId());
    }

    public boolean isParticipante(Usuario u, Torneo t)
    {
        List<Usuario> userList = fbd.consultarParticipantes(t.getId());
        for(Usuario user : userList) if (user.getId().equals(u.getId())) return true;
        return false;
    }

    public boolean puedeRetirarse(Torneo t)
    {
        return t.getFecha_inicio().toLocalDate().isAfter(LocalDate.now());
    }

    public void retirarseTorneo(Usuario u, Torneo t)
    {
        fbd.retirarseTorneo(u.getId(), t.getId());
    }

}
