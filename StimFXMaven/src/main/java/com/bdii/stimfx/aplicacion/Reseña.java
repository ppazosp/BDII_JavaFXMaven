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
    private int id_usuario;
    private int idReseña;
    private String comentario;
    private Date fecha;
    private float valoracion;

    public Reseña(int id_videojuego, int idReseña, String comentario) {
        this.id_videojuego = id_videojuego;
        this.idReseña = idReseña;
        this.comentario = comentario;
    }

    public Reseña(int id_videojuego, int id_usuario, int idReseña, String comentario) {
        this.id_videojuego = id_videojuego;
        this.id_usuario = id_usuario;
        this.idReseña = idReseña;
        this.comentario = comentario;
    }

    public Reseña(int id_videojuego, int idReseña, String comentario, Date fecha, float valoracion) {
        this.id_videojuego = id_usuario;
        this.idReseña = idReseña;
        this.comentario = comentario;
        this.fecha = fecha;
        this.valoracion = valoracion;
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

    public int getId_usuario() {
        return id_usuario;
    }

    public int getId_videojuego() {
        return id_videojuego;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
