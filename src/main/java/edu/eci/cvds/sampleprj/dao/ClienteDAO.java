package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import com.mysql.fabric.xmlrpc.Client;

import edu.eci.cvds.samples.entities.Cliente;
import java.util.Date;
import org.apache.ibatis.annotations.Param;


public interface ClienteDAO {
	
	public void insertarCliente(Cliente it) throws PersistenceException;

	public Cliente load(long id) throws PersistenceException;
	
	public List<Cliente> consultarClientes() throws PersistenceException;
	
	public Cliente consultarCliente(long id) throws PersistenceException;
        
        public void agregarItemRentadoACliente(long id, int idit, Date fechainicio, Date fechafin) throws PersistenceException;
}
