/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.aplicacion;
import java.sql.*;
//import java.time.LocalDate;

/**
 *
 * @author alumnogreibd
 */
public class Reseña {
    private int id_videojuego;
    private String id_usuario;
    private int idReseña;
    private String comentario;
    private Date fecha;
    private float valoracion;

    public Reseña(int id_videojuego, int idReseña, String comentario) {
        this.id_videojuego = id_videojuego;
        this.idReseña = idReseña;
        this.comentario = comentario;
    }

    public Reseña(int id_videojuego, int idReseña, String id_usuario,  String comentario, float valoracion,Date fecha) {
        this.id_videojuego = id_videojuego;
        this.id_usuario = id_usuario;
        this.idReseña = idReseña;
        this.comentario = comentario;
        this.valoracion = valoracion;
        this.fecha= fecha;
    }


    public Reseña(String texto, float valoracion, String id_usr,int id_juego) {
        this.id_videojuego = id_juego;
        this.idReseña = -1;
        this.comentario = texto;
        this.valoracion = valoracion;
        this.id_usuario = id_usr;
    }

    public int getIdReseña() {
        return idReseña;
    }

    public String getComentario() {
        return comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public float getValoracion() {
        return valoracion;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public int getId_videojuego() {
        return id_videojuego;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
