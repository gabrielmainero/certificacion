/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.decol.campo.modelos;

import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author gabrielmainero
 */
public final class TablaLotesModel extends AbstractTableModel {

    private static final String[] COLUMNAS = { "Nro.", "Superficie", "Tipo de Suelo" };
    
    private List<Lote> lotes;
   

    public TablaLotesModel (List<Lote> lotes) {
        super();
        this.lotes = lotes;
    }

    @Override
    public int getRowCount() {
        return lotes.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Object retorno = null;
        Lote lote = lotes.get(fila);
        
        // según la columna deseada obtenemos el valor a mostrar
        switch (columna) {
            case 0:
                retorno = lote.getNumero();
                break;
            case 1:
                retorno = lote.getSuperficie()+ " ha";
                break;
            case 2:
                retorno = lote.getTipoSuelo();
                break;
        }
        
        return retorno;
    }
    
    @Override
    public String getColumnName(int index) {
        return COLUMNAS[index];
    }
    
    public Lote obtenerLoteEn (int fila) {
        return lotes.get(fila);
    }

    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }
    
}
