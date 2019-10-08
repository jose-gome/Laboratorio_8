package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;
import java.sql.SQLException;
import java.util.List;

public class MyBATISClienteDAO implements ClienteDAO {

	@Inject
	private ClienteMapper clienteMapper;

	@Override
	public void save(Cliente it) throws PersistenceException {
		try {
			clienteMapper.insertarCliente(it);
		} catch (org.apache.ibatis.exceptions.PersistenceException e) {
			throw new PersistenceException("Error al registrar el item " + it.toString(), e);
		}

	}

	@Override
	public Cliente load(long id) throws PersistenceException {
		try {
			return clienteMapper.consultarCliente(id);
		} catch (org.apache.ibatis.exceptions.PersistenceException e) {
			throw new PersistenceException("Error al consultar el item " + id, e);
		}

	}

	@Override
	public List<Cliente> consultarClientes() throws PersistenceException {
		try {
			return clienteMapper.consultarClientes();
		} catch (org.apache.ibatis.exceptions.PersistenceException e) {
			throw new PersistenceException("Error al consultar los clientes ", e);
		}

	}

}