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
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlo
 */
public class Aulas {
    
    private List<Aula> coleccionAulas;
    private static final String NOMBRE_FICHERO_AULAS="/ficheros/aulas.dat";
    
    public Aulas(){ //construcctor
        coleccionAulas=new ArrayList<Aula>(); //al comienzo no hay ningún alua
    }
    
    public Aulas(Aulas aulas){
        if (aulas==null){
            throw new IllegalArgumentException("No se pueden copiar aulas nulas.");
        }
        setAulas(aulas);
    }
    
    public List<Aula> getAulas() {
        return copiaProfundaAulas(this.coleccionAulas);
    }

    public int getNumAulas() {
        return this.coleccionAulas.size();
    }

    private void setAulas(Aulas aulas) {
        if(aulas==null){
            throw new IllegalArgumentException("No se pueden copiar aulas nulas.");
        }
        this.coleccionAulas = copiaProfundaAulas(aulas.getAulas());
    }
    
    private List<Aula> copiaProfundaAulas(List<Aula> coleccionAulas){
        List<Aula> copia= new ArrayList<Aula>();
        for(Aula a: coleccionAulas){
            copia.add(new Aula(a));
        }
        return copia;
    }
    
    public void insertar(Aula aula) throws OperationNotSupportedException {
        if(aula==null){
            throw new IllegalArgumentException("No se puede insertar un aula nula.");
        }
        
        if(this.coleccionAulas.contains(aula)){
            throw new OperationNotSupportedException("El aula ya existe.");
        }
        coleccionAulas.add(aula);
    }
    
    public Aula buscar(Aula aula){
        if (aula==null){
            return null;
        }
        if(this.coleccionAulas.indexOf(aula)== -1){
            return null;
        }
        return (this.coleccionAulas.get(this.coleccionAulas.indexOf(aula)));
        
    }
    
    public void borrar(Aula aula) throws OperationNotSupportedException{
        if(aula==null){
            throw new IllegalArgumentException("No se puede borrar un aula nula.");
        }
        if(!this.coleccionAulas.remove(aula)){
            throw new OperationNotSupportedException("El aula a borrar no existe.");
        }
        this.coleccionAulas.remove(aula);
    }
    
    public List<String> representar(){
        List<String> representar= new ArrayList<String>();
        for(Aula aula: coleccionAulas){
            representar.add(aula.toString());
        }
        return representar;
    }
    
    public void leer() throws FileNotFoundException, IOException, ClassNotFoundException{
        try{
            Aula aula;
            File fichero= new File(NOMBRE_FICHERO_AULAS);
            FileInputStream fileIn= new FileInputStream(fichero);
            ObjectInputStream dataIS= new ObjectInputStream(fileIn);
        
            try{
                while(true){
                    aula= (Aula) dataIS.readObject();
                    coleccionAulas.add(aula);
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
            File fichero= new File(NOMBRE_FICHERO_AULAS);
            FileOutputStream fileOut= new FileOutputStream(fichero);
            ObjectOutputStream dataOS= new ObjectOutputStream(fileOut);
            
            for(Aula aula: coleccionAulas){
                dataOS.writeObject(aula);
            }
            dataOS.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
            
}
