/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.decol.campo.modelos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author gabrielmainero
 */
public class Campo {
    private Integer id;
    private String nombre;
    private BigDecimal superficie;
    private Estado estado;
    private List<Lote> lotes;

    public Campo() {
        lotes=new ArrayList<>();
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

    public BigDecimal getSuperficie() {
        return superficie;
    }

    public void setSuperficie(BigDecimal superficie) {
        this.superficie = superficie;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Lote> getLotes() {
        return lotes;
    }

    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }
    
    public void agregarLote(Lote lote){
        lotes.add(lote);
    }
    /**
     * Verifica si existe ya un lote ingresado en el campo a través de su número
     * @param numero de lote
     * @return true si ya existe lote ingresado, false si no existe
     */
    public boolean existeLote(Integer numero){
        Iterator<Lote> ite = lotes.iterator();
        while(ite.hasNext()){
            Lote lote = ite.next();
            if(numero.equals(lote.getNumero())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Valida si la superficie del campo es igual a la suma de todas las superficies de sus lotes
     * @return true si son iguales las superficies del campo y de los lotes, false si son distintas.
     */
    public boolean esValidaSuperficieLote(){
        boolean retorno = false;
        if(superficie==null)
            return retorno;
        BigDecimal suma = BigDecimal.ZERO;
        Iterator<Lote> ite = lotes.iterator();
        while(ite.hasNext()){
            Lote lote = ite.next();
            suma = suma.add(lote.getSuperficie());
        }
        if(suma.compareTo(this.superficie)==0)
            retorno = true;
        return retorno;
    }
    
}
