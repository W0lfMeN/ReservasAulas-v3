/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.vista;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.ModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.vista.Consola;
import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.ArrayList;
import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;
/**
 *
 * @author carlo
 */

public class VistaReservasAulas implements IVistaReservasAulas {
    private static final String CORREO_VALIDO="car@car.car";
    private static final String NOMBRE_VALIDO="Carlos";
    private static final String ERROR="ERROR";
    private IControladorReservasAulas controlador;
    
    public VistaReservasAulas(){
        Opcion.setVista(this);
    }
    
    public void setControlador(IControladorReservasAulas controlador){
        this.controlador=controlador;
    }
    @Override
    public void comenzar(){
        Opcion opcion;
        do{
            Consola.mostrarMenu();
            opcion= Opcion.getOpcionSegunOrdinal(Consola.elegirOpcion());
            opcion.ejecutar();
        }while(opcion != Opcion.SALIR);
    }
    @Override
    public void salir(){
        System.out.println("Hasta pronto");
    }
    @Override
    public void insertarAula(){
        Consola.mostrarCabecera("Insertar aula");
        try{
            Aula aula=Consola.leerAula();
            controlador.insertarAula(aula);
            System.out.println("Aula insertada");
        }catch (IllegalArgumentException e){
            System.out.println(ERROR +e.getMessage());
        }
    }
    @Override
    public void borrarAula(){
        Consola.mostrarCabecera("Borrar aula");
        try{
            Aula aula= Consola.leerAula();
            controlador.borrarAula(aula);
            System.out.println("Aula borrada");
        }catch (IllegalArgumentException e){
            System.out.println(ERROR + e.getMessage());
        }
    }
    @Override
    public void buscarAula(){
        Consola.mostrarCabecera("Buscar aula");
        Aula aula=null;
        try{
            aula=Consola.leerAula();
            aula=controlador.buscarAula(aula);
            if(aula==null){
                System.out.println("El aula no existe");
            }else{
                System.out.println("El aula buscada es: "+ aula);
            }
        }catch(IllegalArgumentException e){
            System.out.println(ERROR + e.getMessage());
        }
    }
    @Override
    public void listarAulas(){
        Consola.mostrarCabecera("Listar aula");
        List<String> aula= controlador.representarAulas();
        if(aula.size()> 0){
            for(String aulas : aula){
                System.out.println(aulas);
            }
        }else{
            System.out.println("No hay aulas");
        }
    }
    @Override
    public void insertarProfesor(){
        Consola.mostrarCabecera("Insertar profesor");
        try{
            Profesor profesor=Consola.leerProfesor();
            controlador.insertarProfesor(profesor);
            System.out.println("profesor insertado");
        }catch (IllegalArgumentException e){
            System.out.println(ERROR +e.getMessage());
        }
    }
    @Override
    public void borrarProfesor(){
        Consola.mostrarCabecera("Borrar profesor");
        try{
            Profesor profesor= new Profesor (Consola.leerNombreProfesor(), CORREO_VALIDO);
            controlador.borrarProfesor(profesor);
            System.out.println("profesor borrado");
        }catch (IllegalArgumentException e){
            System.out.println(ERROR + e.getMessage());
        }
    }
    
    @Override
    public void buscarProfesor(){
        Consola.mostrarCabecera("Buscar Profesor");
        Profesor profesor=null;
        try{
            profesor=Consola.leerProfesor();
            profesor=controlador.buscarProfesor(profesor);
            if(profesor==null){
                System.out.println("El profesor no existe");
            }else{
                System.out.println("El profesor buscado es: "+ profesor);
            }
        }catch(IllegalArgumentException e){
            System.out.println(ERROR + e.getMessage());
        }
    }
    
    @Override
    public void listarProfesores(){
        Consola.mostrarCabecera("Listar profesores");
        List<String> profesor= controlador.representarProfesores();
        if(profesor.size()> 0){
            for(String profesores : profesor){
                System.out.println(profesores);
            }
        }else{
            System.out.println("No hay profesores");
        }
    }
    @Override
    public void realizarReserva(){
        Consola.mostrarCabecera("Realizar reserva");
        Profesor profesor= controlador.buscarProfesor(new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO));
        if(profesor==null){
            System.out.println("El profesor no existe");
        }
        try{
            Reserva reserva= leerReserva(profesor);
            controlador.realizarReserva(reserva);
            System.out.println("Reserva realizada");
        }catch(IllegalArgumentException e){
            System.out.println(ERROR + e.getMessage());
        }
    }
    private Reserva leerReserva(Profesor profesor){
        Aula aula=Consola.leerAula();
        Permanencia permanencia=Consola.leerPermanencia();
        return new Reserva(profesor, aula, permanencia);
    }
    @Override
    public void anularReserva(){
        Consola.mostrarCabecera("Anular reserva");
        Reserva reserva= leerReserva(new Profesor(NOMBRE_VALIDO, CORREO_VALIDO));
        try{
            controlador.anularReserva(reserva);
            System.out.println("Reserva anulada");
        }catch(IllegalArgumentException e){
            System.out.println(ERROR + e.getMessage());
        }
    }
    @Override
    public void listarReservas(){
        Consola.mostrarCabecera("Listar reservas");
        List<String> reserva= controlador.representarReservas();
        if(reserva.size()> 0){
            for(String reservas : reserva){
                System.out.println(reserva);
            }
        }else{
            System.out.println("No hay reservas");
        }
    }
    @Override
    public void listarReservasAula(){
        Consola.mostrarCabecera("Listar reservas de aulas");
        Aula aula=Consola.leerAula();
        List<Reserva> reservas= controlador.getReservasAula(aula);
        if(reservas.size()==0){
                System.out.println("No existen reservas para el aula: "+aula);
            }
            System.out.println(reservas);
        }
    @Override
    public void listarReservasProfesor(){
        Consola.mostrarCabecera("Listar reservas de profesor");
        Profesor profesor= new Profesor (Consola.leerNombreProfesor(), CORREO_VALIDO);
        List<Reserva> reservas= controlador.getReservasProfesor(profesor);
        if(reservas.size()==0){
  
                System.out.println("No existen reservas para el profesor: "+profesor);
        }
            System.out.println(reservas);
       }
    @Override
    public void listarReservasPermanencia(){
        Consola.mostrarCabecera("Listar reservas de permanencia");
        Permanencia permanencia= Consola.leerPermanencia();
        List<Reserva> reservas= controlador.getReservasPermanencia(permanencia);
        if(reservas.size()==0){
                System.out.println("No existen reservas para la permanencia: "+permanencia);
            }
            System.out.println(reservas);
        }
    @Override
    public void consultarDisponibilidad(){
        Consola.mostrarCabecera("Consultar la disponibilidad");
        Aula aula=Consola.leerAula();
        Permanencia permanencia= Consola.leerPermanencia();
        boolean disponibilidad= true;
        if(disponibilidad==true){
            System.out.println("La reserva está disponible");
        }else{
            System.out.println("La reserva no está disponible");
        }
    }

}


