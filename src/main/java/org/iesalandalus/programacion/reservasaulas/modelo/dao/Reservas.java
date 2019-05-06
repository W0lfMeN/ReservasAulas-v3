/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.iesalandalus.programacion.reservasaulas.modelo.dao;
import java.time.LocalDate;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlo
 */

public class Reservas {
    private List<Reserva> coleccionReservas;
    private static final float MAX_PUNTOS_PROFESOR_MES= 200;
    
    public Reservas(){ //construcctor
        coleccionReservas= new ArrayList<Reserva>(); 
    }
    
    public Reservas(Reservas reservas){
        if (reservas==null){
            throw new IllegalArgumentException("No se pueden copiar reservas nulas.");
        }
        setReservas(reservas);
    }
    
    public List<Reserva> getReservas() {
        return copiaProfundaReservas(this.coleccionReservas);
    }

    public int getNumReservas() {
        return this.coleccionReservas.size();
    }

    private void setReservas(Reservas reservas){
        if(reservas==null){
            throw new IllegalArgumentException("No se pueden copiar reservas nulas.");
        }
        this.coleccionReservas = copiaProfundaReservas(reservas.getReservas());
    }
    
    private List<Reserva> copiaProfundaReservas(List<Reserva> reservas){
        List<Reserva> copia= new ArrayList<Reserva>();
        for(Reserva r: reservas){
            copia.add(new Reserva(r));
        }
        return copia;
    }
    
    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if(reserva==null){
            throw new IllegalArgumentException("No se puede realizar una reserva nula.");
        }        
        if(this.coleccionReservas.contains(reserva)){
            throw new OperationNotSupportedException("La reserva ya existe.");
        }
        //valida si es por tramo o por hora con el uso de instanceof
        if(reserva!=null){
            if(reserva.getPermanencia()instanceof PermanenciaPorHora && reserva.getPermanencia() instanceof PermanenciaPorTramo){
                throw new OperationNotSupportedException("Ya se ha realizado una reserva por hora para este día y aula.");
            }
            if(getReservaDia(reserva.getPermanencia().getDia()).getPermanencia()instanceof PermanenciaPorTramo && reserva.getPermanencia()instanceof PermanenciaPorHora){
                throw new OperationNotSupportedException("Ya se ha realizado una reserva por tramo para este día y aula.");
            }
        }
        //valida si es el mes siguiente o posterior
        if(!esMesSiguienteOPorsterior(reserva)){
            throw new OperationNotSupportedException("Sólo se pueden hacer reservas para el mes que viene o posteriores.");
        }
        //valida si la reserva excede los puntos del profesor
        List<Reserva> reservasProfesor = getReservasProfesorMes(reserva.getProfesor(), reserva.getPermanencia().getDia());
        float puntosDelProfesor=0;
        for(Reserva r: reservasProfesor){
            float name = puntosDelProfesor + r.getPuntos();
        }
        if(getPuntosGastadosReserva(reserva)>(MAX_PUNTOS_PROFESOR_MES - puntosDelProfesor)){
            throw new OperationNotSupportedException("Esta reserva excede los puntos máximos por mes para dicho profesor.");
        }
        //tras todas las comprobaciones
        this.coleccionReservas.add(reserva);
    }
    
    private boolean esMesSiguienteOPorsterior (Reserva laReserva){
        if(laReserva==null){
            throw new IllegalArgumentException("Sólo se pueden hacer reservas para el mes que viene o posteriores.");
        }
        LocalDate fecha= LocalDate.now().plusMonths(1);
        if(laReserva.getPermanencia().getDia().isBefore(fecha)){
            return false;
        }
        return true;
    }
    
    private float getPuntosGastadosReserva(Reserva reserva){
        if(reserva==null){
            throw new IllegalArgumentException("Esta reserva excede los puntos máximos por mes para dicho profesor.");
        }
        return reserva.getPuntos();
    }
    
    private List<Reserva> getReservasProfesorMes(Profesor elProfesor, LocalDate dia){
        List<Reserva> devolver= new ArrayList<Reserva>();
        for(Reserva reserva: coleccionReservas){
            if(reserva.getProfesor().equals(elProfesor)&& reserva.getPermanencia().getDia().getMonthValue()==dia.getMonthValue()){
                devolver.add(reserva);
            }
        }
        return devolver;
    }
    
    private Reserva getReservaDia(LocalDate dia){
        for(Reserva reserva: coleccionReservas){
            if(reserva.getPermanencia().getDia().equals(dia)){
                return new Reserva(reserva);
            }
        }
        return null;
    }
    
    public Reserva buscar(Reserva reserva){
        if (reserva==null){
            return null;
        }
        if(this.coleccionReservas.indexOf(reserva)== -1){
          return null;  
        }
        return this.coleccionReservas.get(this.coleccionReservas.indexOf(reserva));
        
    }
    
    public void borrar(Reserva reserva) throws OperationNotSupportedException{
        if(reserva==null){
            throw new IllegalArgumentException("No se puede anular una reserva nula.");
        }
        if(!this.coleccionReservas.remove(reserva)){
            throw new OperationNotSupportedException("La reserva a anular no existe.");
        }
        this.coleccionReservas.remove(reserva);
    }
    
    public List<String> representar(){
        List<String> representar= new ArrayList<String>();
        for(Reserva r: this.coleccionReservas){
            representar.add(r.toString());
        }
        return representar;
    }
    
    public List<Reserva> getReservasProfesor(Profesor profesor){
        if(profesor==null){
            throw new IllegalArgumentException("No se puede consultar las reservas de un profesor nulo.");
        }
        List<Reserva> devolver= new ArrayList<Reserva>();
        for(Reserva r: this.coleccionReservas){
            if(r.getProfesor().equals(profesor)){
                devolver.add(new Reserva(r));
            }
        }
        return devolver;
    }
    
    public List<Reserva> getReservasAula(Aula aula){
        if(aula==null){
            throw new IllegalArgumentException("No se pueden comprobar las reservas de una aula nula.");
        }
        List<Reserva> devolver= new ArrayList<Reserva>();
        for(Reserva r: this.coleccionReservas){
            if(r.getAula().equals(aula)){
                devolver.add(new Reserva(r));
            }
        }
        return devolver;
    }
    
    public List<Reserva> getReservasPermanencia(Permanencia permanencia){
        if(permanencia==null){
            throw new IllegalArgumentException("No se puede consultar las reservas de una permanencia nula.");
        }
        List<Reserva> devolver= new ArrayList<Reserva>();
        for(Reserva r: this.coleccionReservas){
                if(r.getPermanencia().equals(permanencia)){
                    devolver.add(new Reserva(r));
            }
        }
        return devolver;
    }
    
    public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia){
        if(aula==null){
            throw new IllegalArgumentException("No se puede consultar la disponibilidad de un aula nula.");
        }
        if(permanencia==null){
            throw new IllegalArgumentException("No se puede consultar la disponibilidad de una permanencia nula.");
        }
        for(Reserva r: this.coleccionReservas){
            if(r.getAula().equals(aula) && r.getPermanencia().equals(permanencia)){
                return false;
            }
        }
        return true;
    }
    
}

