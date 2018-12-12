/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.decol.campo.modelos;

import java.math.BigDecimal;

/**
 *
 * @author gabrielmainero
 */
public class Lote {
    private Integer id;
    private Integer numero;
    private BigDecimal superficie;
    private TipoSuelo tipoSuelo;
    
    public Lote() {
    }

    public Lote(Integer numero, BigDecimal superficie, TipoSuelo tipoSuelo) {
        this.numero = numero;
        this.superficie = superficie;
        this.tipoSuelo = tipoSuelo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public BigDecimal getSuperficie() {
        return superficie;
    }

    public void setSuperficie(BigDecimal superficie) {
        this.superficie = superficie;
    }

    public TipoSuelo getTipoSuelo() {
        return tipoSuelo;
    }

    public void setTipoSuelo(TipoSuelo tipoSuelo) {
        this.tipoSuelo = tipoSuelo;
    }

    @Override
    public String toString() {
        return "Lote{" + "numero=" + numero + ", superficie=" + superficie + ", tipoSuelo=" + tipoSuelo + '}';
    }
    
}
