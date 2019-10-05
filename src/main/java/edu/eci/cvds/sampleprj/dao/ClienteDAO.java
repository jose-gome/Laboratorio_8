package edu.eci.cvds.sampleprj.dao;

import com.mysql.fabric.xmlrpc.Client;

import edu.eci.cvds.samples.entities.Cliente;


public interface ClienteDAO {
	
	public void save(Cliente it) throws PersistenceException;

	public Cliente load(int id) throws PersistenceException;
}
