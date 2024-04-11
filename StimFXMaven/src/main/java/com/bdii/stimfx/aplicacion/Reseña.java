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
    private Videojuego videojuego;
    private int idReseña;
    private String comentario;
    private Date fecha;
    private float valoracion;

    public Reseña(Videojuego videojuego, int idReseña, String comentario) {
        this.videojuego = videojuego;
        this.idReseña = idReseña;
        this.comentario = comentario;
    }

    public Reseña(Videojuego videojuego, int idReseña, String comentario, Date fecha, float valoracion) {
        this.videojuego = videojuego;
        this.idReseña = idReseña;
        this.comentario = comentario;
        this.fecha = fecha;
        this.valoracion = valoracion;
    }
    

    public Videojuego getIdVideojuego() {
        return videojuego;
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

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
