/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia;

import java.io.Serializable;

/**
 *
 * @author carlo
 */
public enum Tramo implements Serializable {
    MANANA("Mañana"), TARDE("Tarde");
    private String cadenaAMostrar;
    
    private Tramo(String cadenaAMostrar){
        this.cadenaAMostrar=cadenaAMostrar;
    }

    @Override
    public String toString() {
        return cadenaAMostrar;
    }
    
    
}
