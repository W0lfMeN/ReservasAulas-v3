
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.iesalandalus.programacion.reservasaulas.modelo;
import java.io.IOException;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dao.Aulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dao.Profesores;
import org.iesalandalus.programacion.reservasaulas.modelo.dao.Reservas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author carlo
 */
public class ModeloReservasAulas implements IModeloReservasAulas {
    private Profesores profesores;
    private Aulas aulas;
    private Reservas reservas;
    
    public ModeloReservasAulas(){
        this.profesores=new Profesores();
        this.reservas=new Reservas();
        this.aulas=new Aulas();
    }
    
    @Override
    public List<Aula> getAulas(){
        return aulas.getAulas();
    }
    
    @Override
    public int getNumAulas(){
        return aulas.getNumAulas();
    }
    
    @Override
    public List<String> representarAulas(){
        return aulas.representar();
    }
    
    @Override
    public Aula buscarAula(Aula buscar){
        return aulas.buscar(buscar);
    }
    
    @Override
    public void insertarAula(Aula insertar) {
        try {
            aulas.insertar(insertar);
        } catch (OperationNotSupportedException ex) {
            Logger.getLogger(ModeloReservasAulas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void borrarAula(Aula borrar) {
        try {
            aulas.borrar(borrar);
        } catch (OperationNotSupportedException ex) {
            Logger.getLogger(ModeloReservasAulas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Profesor> getProfesores(){
        return profesores.getProfesores();
    }
    
    @Override
    public int getNumProfesores(){
        return profesores.getNumProfesores();
    }
    
    @Override
    public List<String> representarProfesores(){
        return profesores.representar();
    }
    
    @Override
    public Profesor buscarProfesor(Profesor buscar){
        return profesores.buscar(buscar);
    }
    
    @Override
    public void insertarProfesor(Profesor insertar) {
        try {
            profesores.insertar(insertar);
        } catch (OperationNotSupportedException ex) {
            Logger.getLogger(ModeloReservasAulas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void borrarProfesor(Profesor borrar) {
        try {
            profesores.borrar(borrar);
        } catch (OperationNotSupportedException ex) {
            Logger.getLogger(ModeloReservasAulas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Reserva> getReservas(){
        return reservas.getReservas();
    }
    
    @Override
    public int getNumReservas(){
        return reservas.getNumReservas();
    }
    
    @Override
    public List<String> representarReservas(){
        return reservas.representar();
    }
    
    @Override
    public Reserva buscarReserva(Reserva buscar){
        return reservas.buscar(buscar);
    }
    
    @Override
    public void realizarReserva(Reserva insertar) {
        try {
            reservas.insertar(insertar);
        } catch (OperationNotSupportedException ex) {
            Logger.getLogger(ModeloReservasAulas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void anularReserva(Reserva borrar){
        try {
            reservas.borrar(borrar);
        } catch (OperationNotSupportedException ex) {
            Logger.getLogger(ModeloReservasAulas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Reserva> getReservasAula(Aula aula){
        return reservas.getReservasAula(aula);
    }
    
    @Override
    public List<Reserva> getReservasProfesor(Profesor profesor){
        return reservas.getReservasProfesor(profesor);
    }
    
    @Override
    public List<Reserva> getReservasPermanencia(Permanencia permanencia){
        return reservas.getReservasPermanencia(permanencia);
    }
    
    @Override
    public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia){
        return reservas.consultarDisponibilidad(aula, permanencia);
    }

    @Override
    public void leerAulas() {
        try {
            aulas.leer();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ModeloReservasAulas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void escribirAulas() {
        try {
            aulas.escribir();
        } catch (IOException ex) {
            Logger.getLogger(ModeloReservasAulas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void leerProfesores() {
        try {
            profesores.leer();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ModeloReservasAulas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void escribirProfesores() {
        try {
            profesores.escribir();
        } catch (IOException ex) {
            Logger.getLogger(ModeloReservasAulas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void leerReservas() {
        try {
            reservas.leer();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ModeloReservasAulas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void escribirReservas() {
        try {
            reservas.escribir();
        } catch (IOException ex) {
            Logger.getLogger(ModeloReservasAulas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
