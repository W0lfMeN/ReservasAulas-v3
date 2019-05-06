/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dominio;
import java.io.Serializable;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import java.util.Objects;

/**
 *
 * @author carlo
 */

public class Reserva implements Serializable {
    private Aula aula;
    private Profesor profesor;
    private Permanencia permanencia;

    public Reserva(Profesor profesor, Aula aula, Permanencia permanencia){ //constructor
        setAula(aula);
        setPermanencia(permanencia);
        setProfesor(profesor);
    }
    
    public Reserva(Reserva r){ //constructor copia
        if(r==null){
            throw new IllegalArgumentException("No se puede copiar una reserva nula.");
        }
        setAula(r.aula);
        setPermanencia(r.permanencia);
        setProfesor(r.profesor);
    }
    
    public Aula getAula() {
        return aula;
    }

    private void setAula(Aula aula) {
        if(aula==null){
            throw new IllegalArgumentException("La reserva debe ser para un aula concreta.");
        }
        this.aula = new Aula(aula);
    }

    public Profesor getProfesor() {
        return profesor;
    }

    private void setProfesor(Profesor profesor) {
        if(profesor==null){
            throw new IllegalArgumentException("La reserva debe estar a nombre de un profesor.");
        }
        this.profesor = new Profesor(profesor);
    }

    public Permanencia getPermanencia() {
        return permanencia;
    }

    private void setPermanencia(Permanencia permanencia) {
        if(permanencia==null){
            throw new IllegalArgumentException("La reserva se debe hacer para una permanencia concreta.");
        }
        //a continuacion se selecciona el tipo de permanencia en base a los datos introducidos
        if(permanencia instanceof PermanenciaPorHora){
            this.permanencia=new PermanenciaPorHora((PermanenciaPorHora) permanencia);
        }
        if(permanencia instanceof PermanenciaPorTramo){
            this.permanencia=new PermanenciaPorTramo((PermanenciaPorTramo) permanencia);
        }
    }
    
    public float getPuntos(){
        return permanencia.getPuntos()+ aula.getPuntos();
    }

    @Override
    public int hashCode() {
        return Objects.hash(aula, permanencia, profesor);
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
        final Reserva other = (Reserva) obj;
        if (!Objects.equals(this.aula, other.aula)) {
            return false;
        }
        if (!Objects.equals(this.permanencia, other.permanencia)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[profesor=" +getProfesor()+ ", aula=" +getAula()+ ", permanencia=" +getPermanencia()+ ", puntos=" +getPuntos()+ "]";
    }
     
}
