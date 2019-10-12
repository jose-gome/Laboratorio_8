package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {

	@Inject
	private ItemDAO itemDAO;
	@Inject
	private ClienteDAO clienteDAO;
	@Inject
	private TipoItemDAO tipoItemDAO;

	@Override
	public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
		try {
			clienteDAO.insertarCliente(c);
		} catch (PersistenceException e) {
			throw new ExcepcionServiciosAlquiler("Error al registrar el cliente " + c.getDocumento());
		}
	}

	@Override
	public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
		try {
			return clienteDAO.load(docu);
		} catch (PersistenceException e) {
			throw new ExcepcionServiciosAlquiler("Error al consultar el cliente con documento" + docu);
		}

	}

	@Override
	public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
		try {
			return clienteDAO.consultarClientes();
		} catch (PersistenceException e) {
			throw new ExcepcionServiciosAlquiler("Error al consultar clientes", e);
		}
	}
	
	@Override
	public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
		try {
			itemDAO.save(i);
		} catch (PersistenceException e) {
			throw new ExcepcionServiciosAlquiler("Error al registrar el item " + i.getId(), e);
		}
	}

	@Override
	public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
		try {
			return itemDAO.consultarItem(id);
		} catch (PersistenceException ex) {
			throw new ExcepcionServiciosAlquiler("Error al consultar el item " + id, ex);
		}
	}


	@Override
	public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
		try {
			return tipoItemDAO.load(id);
		} catch (PersistenceException e) {
			throw new ExcepcionServiciosAlquiler("Error al consultar el tipo item" + id, e);
		}
	}
        
        @Override
	public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {

		try {
			return tipoItemDAO.consultarTiposItem();
		} catch (PersistenceException e) {
			throw new ExcepcionServiciosAlquiler("Error al consultar los tipos items", e);
		}

	}

	// falta
	@Override
	public List<Item> consultarItemsDisponibles() {
		
		throw new UnsupportedOperationException("Not supported yet.");
	}
	

	@Override
	public long valorMultaRetrasoxDia(int itemId) {
            try {
                return itemDAO.consultarItem(itemId).getTarifaxDia();
            } catch (PersistenceException ex) {
                throw new UnsupportedOperationException("No disponemos del item" + itemId);
            }
	}

	@Override
	public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
            long res = 0;
		List<Cliente> clientes = consultarClientes();
                for (Cliente cliente: clientes) {
                    ArrayList<ItemRentado> rentados = cliente.getRentados();
                    for (ItemRentado item: rentados) { 
                        if (iditem ==  item.getItem().getId()) {
                             LocalDate fechafinrenta = item.getFechafinrenta().toLocalDate();
                             LocalDate fechaEntregaItem = fechaDevolucion.toLocalDate();
                             long diferenciaDias = ChronoUnit.DAYS.between(fechafinrenta, fechaEntregaItem); 
                             if (diferenciaDias < 0) {
                                 return res;
                             }
                             return diferenciaDias * valorMultaRetrasoxDia(item.getItem().getId());
                        }
                        
                    }
                }
                throw  new ExcepcionServiciosAlquiler("El item "+ iditem +"no está rentado");
	}

	@Override
	public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
            LocalDate actual = date.toLocalDate();
            LocalDate fechaFinal = actual.plusDays(numdias);
            if (numdias < 1)throw  new ExcepcionServiciosAlquiler("El número de dias debe ser mayor o igual que 1");
            if (consultarCliente(docu) == null)throw new ExcepcionServiciosAlquiler("El cliente no esta registrado");
            if(consultarItem(item.getId()) == null)throw new ExcepcionServiciosAlquiler("El item no esta registrado");
            for (ItemRentado itemRentado:consultarCliente(docu).getRentados()) {
                if (itemRentado.getItem().getId() == item.getId())
                    throw new ExcepcionServiciosAlquiler("El item con id: " + item.getId() + " ya se encuentra rentado");
            }   
            try {
                clienteDAO.agregarItemRentadoACliente(docu, item.getId(), date, java.sql.Date.valueOf(fechaFinal));
            } catch (PersistenceException e) {
                throw new ExcepcionServiciosAlquiler("Error al agregar el item" + item + " a los items rentados del cliente" + docu, e);
            }   
        
	}
        

	@Override
	public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
		throw new UnsupportedOperationException("Not supported yet.");
	}
        // fin
	@Override
	public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

}