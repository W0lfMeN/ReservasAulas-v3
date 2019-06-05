/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import java.util.ArrayList;
import java.util.List;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;

/**
 *
 * @author carlo
 */
public class Profesores {
    private List<Profesor> coleccionProfesores;
    private static final String NOMBRE_FICHERO_PROFESORES="/ficheros/profesores.dat";
    
    public Profesores(){ //constructor
        coleccionProfesores= new ArrayList<Profesor>();
    }
    
    public Profesores(Profesores profesores){
        if (profesores==null){
            throw new IllegalArgumentException("No se pueden copiar profesores nulos.");
        }
        setProfesores(profesores);
    }
    
    public List<Profesor> getProfesores() {
        return copiaProfundaProfesores(this.coleccionProfesores);
    }

    public int getNumProfesores() {
        return this.coleccionProfesores.size();
    }

    private void setProfesores(Profesores profesores){
        if(profesores==null){
            throw new IllegalArgumentException("No se pueden copiar profesores nulos.");
        }
        this.coleccionProfesores = copiaProfundaProfesores(profesores.getProfesores());
    }
    
    private List<Profesor> copiaProfundaProfesores(List<Profesor> profesores){
        List<Profesor> copia= new ArrayList<Profesor>();
        for(Profesor p: profesores){
            copia.add(new Profesor(p));
        }
        return copia;
    }
    
    public void insertar(Profesor profesor) throws OperationNotSupportedException, IllegalArgumentException {
        if(profesor==null){
            throw new IllegalArgumentException("No se puede insertar un profesor nulo.");
        }
        if(this.coleccionProfesores.contains(profesor)){
            throw new OperationNotSupportedException("El profesor ya existe.");
        }
        coleccionProfesores.add(profesor);
    }
    
    public Profesor buscar(Profesor profesor){
        if (profesor==null){
            return null;
        }
        if(this.coleccionProfesores.indexOf(profesor)== -1){
            return null;
        }
        return this.coleccionProfesores.get(this.coleccionProfesores.indexOf(profesor));
    }
    
    public void borrar(Profesor profesor) throws OperationNotSupportedException{
        if(profesor==null){
            throw new IllegalArgumentException("No se puede borrar un profesor nulo.");
        }
        
        if(!this.coleccionProfesores.remove(profesor)){
            throw new OperationNotSupportedException("El profesor a borrar no existe.");
        }
        this.coleccionProfesores.remove(profesor);
    }
    
    public List<String> representar(){
        List<String> representar= new ArrayList<String>();
        for(Profesor p: this.coleccionProfesores){
            representar.add(p.toString());
        }
        return representar;
    }
    
    public void leer() throws FileNotFoundException, IOException, ClassNotFoundException{
        try{
            Profesor profesor;
            File fichero= new File(NOMBRE_FICHERO_PROFESORES);
            FileInputStream fileIn= new FileInputStream(fichero);
            ObjectInputStream dataIS= new ObjectInputStream(fileIn);
        
            try{
                while(true){
                    profesor= (Profesor) dataIS.readObject();
                    coleccionProfesores.add(profesor);
                }
            }catch(IOException eo){
            dataIS.close();
            }
        }catch(IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void escribir() throws FileNotFoundException, IOException{
        try{
            File fichero= new File(NOMBRE_FICHERO_PROFESORES);
            FileOutputStream fileOut= new FileOutputStream(fichero);
            ObjectOutputStream dataOS= new ObjectOutputStream(fileOut);
            
            for(Profesor profesor: coleccionProfesores){
                dataOS.writeObject(profesor);
            }
            dataOS.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
            
}
