/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author carlo
 */
public class PermanenciaPorTramo extends Permanencia implements Serializable {
    private static final int PUNTOS= 10;
    private Tramo tramo;
    
    public PermanenciaPorTramo(LocalDate dia, Tramo tramo){
        super(dia); //llama a la variable dia de la clase Permanencia
        setTramo(tramo);
    }
    
    public PermanenciaPorTramo(String dia, Tramo tramo){
        super(dia); //llama al String dia
        setTramo(tramo);
    }
    
    public PermanenciaPorTramo(PermanenciaPorTramo p) {
        super();
        if(p==null){
            throw new IllegalArgumentException("No se puede copiar una permanencia nula.");
        }
        setTramo(p.getTramo());
        super.setDia(p.getDia());
    }

    public Tramo getTramo() {
        return tramo;
    }

    private void setTramo(Tramo tramo) {
        if(tramo==null){
            throw new IllegalArgumentException("El tramo de una permanencia no puede ser nulo.");
        }
        this.tramo = tramo;
    }

    @Override
    public int getPuntos() {
        return PUNTOS;
    }

    @Override
    public int hashCode() {
        return Objects.hash(PUNTOS, dia, tramo);
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
        final PermanenciaPorTramo other = (PermanenciaPorTramo) obj;
        if(Objects.equals(this.tramo, other.tramo) && Objects.equals(this.dia, other.dia)){
            return true;
        }
        return false;
    }

    

    @Override
    public String toString() {
        return "[dia=" +getDia().format(FORMATO_DIA)+ ", tramo=" +tramo+ "]";
    }
    
    
    
}
