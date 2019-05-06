/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dominio;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author carlo
 */
public class Aula implements Serializable {
    private String nombre;
    private int puestos;
    private static final float PUNTOS_POR_PUESTO= (float) 0.5;
    private static final int MIN_PUESTOS= 10;
    private static final int MAX_PUESTOS= 100;
    
    public Aula(String nombre, int puestos){ //constructor
        setNombre(nombre);
        setPuestos(puestos);
    }
    
    public Aula(Aula a){ //constructor por defecto
        if(a==null){
            throw new IllegalArgumentException("No se puede copiar un aula nula.");
        }
        setNombre(a.getNombre());
        setPuestos(a.getPuestos());
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuestos() {
        return this.puestos;
    }
    
    private void setNombre(String nombre) {
        if(nombre==null){
            throw new IllegalArgumentException("El nombre del aula no puede ser nulo.");
        }
        if (nombre.equals("")){
            throw new IllegalArgumentException("El nombre del aula no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    private void setPuestos(int puestos) {
        if(puestos<MIN_PUESTOS || puestos>MAX_PUESTOS){
            throw new IllegalArgumentException("El número de puestos no es correcto.");
        }
        this.puestos = puestos;
    }
    
    public float getPuntos(){
        return getPuestos()* PUNTOS_POR_PUESTO;
    }
    

    @Override
    public int hashCode(){
        return Objects.hash(nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aula other = (Aula) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[nombre=" +nombre+", puestos=" +puestos+ "]";
    }
    
    
}
