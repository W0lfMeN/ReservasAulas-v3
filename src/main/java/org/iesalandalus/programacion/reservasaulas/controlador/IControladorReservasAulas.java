/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.controlador;

import java.util.List;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;

/**
 *
 * @author carlo
 */
public interface IControladorReservasAulas {
    public void comenzar();
    
    public void salir();
    
    public void insertarAula(Aula aula);
    
    public void borrarAula(Aula aula);
    
    public Aula buscarAula(Aula aula);
    
    public List<String> representarAulas();
    
    public void insertarProfesor(Profesor profesor);
    
    public void borrarProfesor(Profesor profesor);
    
    public Profesor buscarProfesor(Profesor profesor);
    
    public List<String> representarProfesores();
    
    public void realizarReserva(Reserva reserva);
    
    public void anularReserva(Reserva reserva);
    
    public List<String> representarReservas();
    
    public List<Reserva> getReservasAula(Aula aula);
    
    public List<Reserva> getReservasProfesor(Profesor profesor);
    
    public List<Reserva> getReservasPermanencia(Permanencia permanencia);
    
    public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia);
}
