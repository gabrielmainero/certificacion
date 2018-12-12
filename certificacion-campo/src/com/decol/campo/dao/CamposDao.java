/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.decol.campo.dao;

import com.decol.campo.modelos.Campo;




/**
 *
 * @author gabrielmainero
 */
public interface CamposDao {
    /**
     * Verifica la existencia de un objeto a traves del nombre
     * @param nombre paso un valor String para comprar
     * @return retorno un valor true o false
     */
    public boolean existeNombre(String nombre);
    public void guardar(Campo campos);
}
