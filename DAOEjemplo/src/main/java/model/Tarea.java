/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author FranciscoRomeroGuill
 */
public class Tarea implements Serializable {
    
    private Integer id;
    private String nombre;
    private String responsable;
    private String prioridad;

    public Tarea() {
    }

    public Tarea(Integer id, String nombre, String responsable, String prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.responsable = responsable;
        this.prioridad = prioridad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "Tarea{" + "id=" + id + ", nombre=" + nombre + ", responsable=" + responsable + ", prioridad=" + prioridad + '}';
    }

    
}
