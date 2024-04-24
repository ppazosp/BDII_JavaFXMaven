/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.aplicacion;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author alumnogreibd
 */
public class DLC {
    private int idVideojuego;
    private int idDLC;
    private String nombre;
    private String descripcion;
    private double precio;
    private Date fechaLanzamiento;

    public DLC(int idVideojuego, int idDLC, String nombre, String descripcion, double precio, Date fechaLanzamiento) {
        this.idVideojuego = idVideojuego;
        this.idDLC = idDLC;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio= precio;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public int getIdVideojuego() {
        return idVideojuego;
    }

    public int getIdDLC() {
        return idDLC;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DLC dlc = (DLC) o;
        return idVideojuego == dlc.idVideojuego && idDLC == dlc.idDLC;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVideojuego, idDLC);
    }
}
