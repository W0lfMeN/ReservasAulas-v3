/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 *
 * @author carlo
 */
public class PermanenciaPorHora extends Permanencia implements Serializable {
    private static final int PUNTOS= 3;
    private static final int HORA_INICIO= 8;
    private static final int HORA_FIN= 22;
    private static final DateTimeFormatter FORMATO_HORA=DateTimeFormatter.ofPattern("HH:mm"); //Horas y minutos
    private LocalTime hora;
    
    public PermanenciaPorHora(LocalDate dia, LocalTime hora){
        super(dia);
        setHora(hora);
    }
    
    public PermanenciaPorHora(String dia, LocalTime hora){
        super(dia);
        setHora(hora);
    }
    
    public PermanenciaPorHora(LocalDate dia, String hora){
        super(dia);
        setHora(hora);
    }
    
    public PermanenciaPorHora(String dia, String hora){
        super(dia);
        setHora(hora);
    }
    
    public PermanenciaPorHora(PermanenciaPorHora p){
       super();
        if(p==null){
            throw new IllegalArgumentException("No se puede copiar una permanencia nula.");
        }
        super.setDia(p.getDia());
        setHora(p.getHora());
    }
    
    public LocalTime getHora() {
        return hora;
    }

    private void setHora(LocalTime hora) {
        if(hora==null){
            throw new IllegalArgumentException("La hora de una permanencia no puede ser nula.");
        }
        if(hora.getHour()<HORA_INICIO || hora.getHour()>HORA_FIN){ //getHour devuleve hora (Solo valido para variables de tipo LocalTime)
            throw new IllegalArgumentException("La hora de una permanencia debe estar comprendida entre las 8 y las 22.");
        }
        if(hora.getMinute()!=0){
            throw new IllegalArgumentException("La hora de una permanencia debe ser una hora en punto.");
        }
        this.hora = hora;
    }
    
    private void setHora(String hora) {

	if (hora == null){
            throw new IllegalArgumentException("La hora de una permanencia no puede ser nula.");
        }
        try {
            this.setHora(LocalTime.parse(hora, FORMATO_HORA));
        }catch (DateTimeParseException e) {
            throw new IllegalArgumentException("El formato de la hora de la permanencia no es correcto.");
        }
    }

    @Override
    public int getPuntos() {
        return PUNTOS;
    }

    @Override
    public int hashCode() {
        return Objects.hash(PUNTOS, dia, hora);
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
        final PermanenciaPorHora other = (PermanenciaPorHora) obj;
        if(Objects.equals(this.hora, other.hora) && Objects.equals(this.dia, other.dia)){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return "[dia=" +getDia().format(FORMATO_DIA)+ ", hora=" +getHora().format(FORMATO_HORA)+ "]";
    }
    
    
}
