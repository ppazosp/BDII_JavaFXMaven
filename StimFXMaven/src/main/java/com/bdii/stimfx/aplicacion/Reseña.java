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
    private int idVideojuego;
    private int idReseña;
    private String comentario;
    private Date fecha;
    private float valoracion;

    public Reseña(int idVideojuego, int idReseña, String comentario) {
        this.idVideojuego = idVideojuego;
        this.idReseña = idReseña;
        this.comentario = comentario;
    }

    public int getIdVideojuego() {
        return idVideojuego;
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
