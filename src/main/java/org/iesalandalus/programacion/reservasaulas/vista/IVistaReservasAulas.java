/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.vista;

import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;



/**
 *
 * @author carlo
 */
public interface IVistaReservasAulas {
    public void setControlador(IControladorReservasAulas controlador);
    
    public void comenzar();
    
    public void salir();
    
    public void insertarAula();
    
    public void borrarAula();
    
    public void buscarAula();
    
    public void listarAulas();
    
    public void insertarProfesor();
    
    public void borrarProfesor();
    
    public void buscarProfesor();
    
    public void listarProfesores();
    
    public void realizarReserva();
    
    public void anularReserva();
    
    public void listarReservas();
    
    public void listarReservasAula();
    
    public void listarReservasProfesor();
    
    public void listarReservasPermanencia();
    
    public void consultarDisponibilidad();
}
