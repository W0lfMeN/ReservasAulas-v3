/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo;

import java.util.List;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;


/**
 *
 * @author carlo
 */
public interface IModeloReservasAulas {
    public List<Aula> getAulas();
    
    public int getNumAulas();
    
    public List<String> representarAulas();
    
    public Aula buscarAula(Aula aula);
    
    public void insertarAula(Aula aula);
    
    public void borrarAula(Aula aula);
    
    public List<Profesor> getProfesores();
    
    public int getNumProfesores();
    
    public List<String> representarProfesores();
    
    public Profesor buscarProfesor(Profesor profesor);
    
    public void insertarProfesor(Profesor insertar);
    
    public void borrarProfesor(Profesor profesor);
    
    public List<Reserva> getReservas();
    
    public int getNumReservas();
    
    public List<String> representarReservas();
    
    public Reserva buscarReserva(Reserva reserva);
    
    public void realizarReserva(Reserva reserva);
    
    public void anularReserva(Reserva reserva);
    
    public List<Reserva> getReservasAula(Aula aula);
    
    public List<Reserva> getReservasProfesor(Profesor profesor);
    
    public List<Reserva> getReservasPermanencia(Permanencia permanencia);
    
    public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia);
}