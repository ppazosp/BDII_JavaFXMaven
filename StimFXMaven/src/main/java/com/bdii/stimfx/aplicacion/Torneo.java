/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.aplicacion;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author alumnogreibd
 */
public class Torneo {
    private int id;
    private String nombre;
    private Date fecha_inicio;
    private Date fecha_final;
    private int premio;
    private int ganador;
    private Videojuego videojuego;
    private Usuario administrador;

    public Torneo(int id, String nombre, Date fecha_inicio, Date fecha_final, int premio, int ganador, Videojuego videojuego, Usuario administrador) {
        this.id = id;
        this.nombre = nombre;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
        this.premio = premio;
        this.ganador = ganador;
        this.videojuego = videojuego;
        this.administrador = administrador;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public Date getFecha_final() {
        return fecha_final;
    }

    public int getPremio() {
        return premio;
    }

    public int getGanador() {
        return ganador;
    }

    public Videojuego getVideojuego() {
        return videojuego;
    }

    public Usuario getAdministrador() {
        return administrador;
    }
    
}
