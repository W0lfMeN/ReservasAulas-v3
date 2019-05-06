/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.controlador;

import java.util.List;
import org.iesalandalus.programacion.reservasaulas.modelo.IModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.vista.IVistaReservasAulas;


/**
 *
 * @author carlo
 */
public class ControladorReservasAulas implements IControladorReservasAulas {
    private IModeloReservasAulas modelo;
    private IVistaReservasAulas vista;
    
    public ControladorReservasAulas(IModeloReservasAulas modelo, IVistaReservasAulas vista){
        this.modelo=modelo;
        this.vista=vista;
        
    }

    @Override
    public void comenzar() {
        vista.comenzar();
    }

    @Override
    public void salir() {
        vista.salir();
    }

    @Override
    public void insertarAula(Aula aula) {
        modelo.insertarAula(aula);
    }

    @Override
    public void borrarAula(Aula aula) {
        modelo.borrarAula(aula);
    }

    @Override
    public Aula buscarAula(Aula aula) {
        modelo.buscarAula(aula);
        return aula;
    }

    @Override
    public List<String> representarAulas() {
        return modelo.representarAulas();
    }

    @Override
    public void insertarProfesor(Profesor profesor) {
        modelo.insertarProfesor(profesor);
    }

    @Override
    public void borrarProfesor(Profesor profesor) {
        modelo.borrarProfesor(profesor);
    }

    @Override
    public Profesor buscarProfesor(Profesor profesor) {
        modelo.buscarProfesor(profesor);
        return profesor;
    }

    @Override
    public List<String> representarProfesores() {
        return modelo.representarProfesores();
    }

    @Override
    public void realizarReserva(Reserva reserva) {
        modelo.realizarReserva(reserva);
    }

    @Override
    public void anularReserva(Reserva reserva) {
        modelo.anularReserva(reserva);
    }

    @Override
    public List<String> representarReservas() {
       return modelo.representarReservas();
    }

    @Override
    public List<Reserva> getReservasAula(Aula aula) {
        return modelo.getReservasAula(aula);
    }

    @Override
    public List<Reserva> getReservasProfesor(Profesor profesor) {
        return modelo.getReservasProfesor(profesor);
    }

    @Override
    public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
        return modelo.getReservasPermanencia(permanencia);
    }

    @Override
    public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
        return modelo.consultarDisponibilidad(aula, permanencia);
    }
    
}
