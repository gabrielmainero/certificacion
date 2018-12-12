/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.decol.campo.dao;

import com.decol.campo.modelos.Estado;



/**
 *
 * @author gabrielmainero
 */
public interface EstadosDao {
    public Estado buscarPorNombre(String nombre);   
}
