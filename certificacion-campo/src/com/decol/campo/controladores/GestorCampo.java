/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.decol.campo.controladores;

import com.decol.campo.dao.CamposDao;
import com.decol.campo.dao.CamposDaoHibernateImpl;
import com.decol.campo.dao.EstadosDao;
import com.decol.campo.dao.EstadosDaoHibernateImpl;
import com.decol.campo.dao.TiposSueloDao;
import com.decol.campo.dao.TiposSueloDaoHibernateImpl;
import com.decol.campo.modelos.Campo;
import com.decol.campo.modelos.Estado;
import com.decol.campo.modelos.Lote;
import com.decol.campo.modelos.TipoSuelo;
import com.decol.campo.ventanas.PantallaAdministracionCampo;
import com.decol.campo.ventanas.PantallaRegistroCampo;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.SessionFactory;

/**
 *
 * @author gabrielmainero
 */
public class GestorCampo {
    private PantallaAdministracionCampo pantallaAdministracionCampo;
    private final TiposSueloDao tiposSueloDao;
    private final CamposDao camposDao;
    private final EstadosDao estadosDao;
    private Campo campoTemporal;

    public GestorCampo(SessionFactory sessionFactory) {
        // creamos las instancias de los objetos de acceso a datos
        this.tiposSueloDao = new TiposSueloDaoHibernateImpl(sessionFactory);
        this.camposDao = new CamposDaoHibernateImpl(sessionFactory);
        this.estadosDao = new EstadosDaoHibernateImpl(sessionFactory);
        this.campoTemporal = new Campo();
    }
    
    public void run(){
        // obtenemos los objetos para completar las listas de seleccion
        List<TipoSuelo> tiposSuelos = tiposSueloDao.obtenerTodos();
        
        // creamos una instancia del formulario y lo mostramos
        pantallaAdministracionCampo = new PantallaAdministracionCampo(tiposSuelos, this);
        pantallaAdministracionCampo.setVisible(true);
        //centramos
        pantallaAdministracionCampo.setLocationRelativeTo(null);
    }
    
    public List<Lote> getLotesTemporales(){
        return campoTemporal.getLotes();
    }
    
    public boolean tomarNombreCampo(String nombre){
        return validarCampo(nombre);
    }
    
    /**
     * Valida la existencia de un nombre de campo ya registrado
     * @param nombre de campo a verificar existencia
     * @return true si ya existe un nombre registrado, false si no
     */
    private boolean validarCampo(String nombre) {
        return camposDao.existeNombre(nombre);
    }
    
    
    /**
     * Recibe el numero de lote y valida que no exista
     * @param numeroLote  a validar
     * @return true si ya existe el numero de lote, false si no
     */
    public boolean tomarNumeroLote(Integer numeroLote){
        return validarLote(numeroLote);
    }
    
    /**
     * Valida la existencia de un lote en un campo a través de su numero de lote
     * @param numeroLote para verificar existencia
     * @return true si exite lote, false si no
     */
    private boolean validarLote(Integer numeroLote){
        return campoTemporal.existeLote(numeroLote);
    }
    
    /**
     * Agregar un lote al campo temporal
     * @param numeroLote a registrar en lote
     * @param superficieLote a registrar en lote
     * @param tipoSuelo  seleccionado a registrar en lote
     */
    public void agregarLoteTemporal(Integer numeroLote,   BigDecimal superficieLote, TipoSuelo tipoSuelo){
        Lote lote = new Lote(numeroLote, superficieLote, tipoSuelo);
        campoTemporal.agregarLote(lote);
        pantallaAdministracionCampo.mostrarLotesRegistrados();
    }

    public void tomarFinIngresoLotes(String nombreCampo, BigDecimal superficieCampo){
        campoTemporal.setNombre(nombreCampo);
        campoTemporal.setSuperficie(superficieCampo);
        validarSuperficies();
    }
    
    /**
     * Valida si la suma de las superficies de todos los lotes es igual a la del campo
     */
    private void validarSuperficies(){
        if(campoTemporal.esValidaSuperficieLote()){
            campoTemporal.setEstado(buscarEstadoCreado());
            pantallaAdministracionCampo.pedirConfirmacionRegistrarCampo();
        }
        else{
           JOptionPane.showMessageDialog(pantallaAdministracionCampo, "La suma de superficies de los Lotes no es igual a la del Campo");
        }
    }
    
    /**
     * Busca en la base de datos el estado creado
     * @return estado creado
     */
    private Estado buscarEstadoCreado(){
        return estadosDao.buscarPorNombre("Creado");
    }
    
    public void tomarConfirmacionRegistrarCampo(){
        validarDatosObligatorios();
    }
    
    /**
     * valida si nombre, superficie, estado y al menos un lote fueron cargados
     */
    public void validarDatosObligatorios(){
        if(campoTemporal.getNombre().isEmpty() || campoTemporal.getSuperficie()==null || campoTemporal.getEstado()==null){
            JOptionPane.showMessageDialog(pantallaAdministracionCampo, "Todos los campos del Campo son obligatorios");
        }
        else{
            if (campoTemporal.getLotes().isEmpty()) {
                JOptionPane.showMessageDialog(pantallaAdministracionCampo, "Debe ingresar al menos UN Lote");
            }
            else{
                crearCampo();
            }
        }
    }
    
    /**
     * Guarda el campo temporal y sus lotes en la base de datos y abre la pantalla de confirmacion de registro
     */
    public void crearCampo(){
        camposDao.guardar(campoTemporal);
        // creamos una instancia del formulario registrado y lo mostramos
        PantallaRegistroCampo pantalla = new PantallaRegistroCampo(this, campoTemporal);
        pantalla.setVisible(true);
        //centramos
        pantalla.setLocationRelativeTo(null);
    }
    
}
