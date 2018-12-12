/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.decol.campo.modelos;

/**
 *
 * @author gabrielmainero
 */
public class Estado {
    private Integer id;
    private String nombre;

    public Estado() {
    }

    public Estado(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean esCreado(){
        boolean resultado = false;
        if(nombre.equals("Creado")){
            resultado = true;
        }
        return resultado;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}
