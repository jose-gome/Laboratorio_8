package edu.eci.cvds.view;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.SysexMessage;






@ManagedBean(name="AlquilerItemsBean")
@ApplicationScoped
public class AlquilerItemsBean extends BasePageBean {
    
    @Inject
    private ServiciosAlquiler serviciosAlquiler;
    private Cliente selectedCliente;
    private long costo;
    
    
    
    public List<Cliente> consultarClientes() {
        List<Cliente> clientes = null;
        try {
            clientes = serviciosAlquiler.consultarClientes();
        } catch (ExcepcionServiciosAlquiler ex) {
            
        }
        return clientes;
    }
    
    public Cliente consultarCliente(long documento){
        Cliente cliente = null;
        try {
            cliente = serviciosAlquiler.consultarCliente(documento);
        } catch (ExcepcionServiciosAlquiler ex) {           
        }
        return cliente;
    }
    
    public void setSelectedCliente(Cliente cliente) {
        this.selectedCliente = cliente;
    }

    public Cliente getSelectedCliente() {
        return selectedCliente;
    }
    
    
    public void insertarCliente(long documento, String nombre, String telefono, String direccion, String mail){
        
        try {
            serviciosAlquiler.registrarCliente(new Cliente(nombre, documento, telefono, direccion, mail));
        } catch (ExcepcionServiciosAlquiler ex) {
            System.out.println("documento " + documento);
            Logger.getLogger(AlquilerItemsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
    }
    
    
    
}