package edu.eci.cvds.test;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import edu.eci.cvds.samples.services.client.MyBatisExample;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class ServiciosAlquilerTest {

    @Inject
    private SqlSession sqlSession;

    ServiciosAlquiler serviciosAlquiler;

    public ServiciosAlquilerTest() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    }

    @Before
    public void setUp() {
    }

    /*@Test
    public void emptyDB() {
        for(long i = 0; i < 100; i += 10) {
            boolean r = false;
            try {
            	System.out.print("ENtr1o");
                Cliente cliente = serviciosAlquiler.consultarCliente(i);
                System.out.print("ENtr5o");
            } catch(ExcepcionServiciosAlquiler e) {
                r = true;
                System.out.print("ENtr2o");
            } catch(IndexOutOfBoundsException e) {
                r = true;
                System.out.print("ENtr3");
            }
            // Validate no Client was found;
            System.out.print("ENtro9");
            Assert.assertTrue(r);
        };
    }*/
    
    @Test
    public void deberiaRegistrarCliente() {
    	Cliente c = new Cliente("Brayan", 2153042, "3002543874", "Transversal 45A #75A - 32", "rojasbernalfelipe@gmail.com");
    	try {
			serviciosAlquiler.registrarCliente(c);
		} catch (ExcepcionServiciosAlquiler e) {
			Assert.assertTrue(false);
		}
    	Assert.assertTrue(true);
    }
    
    @Test 
    public void deberiaConsultarCliente() {
    	try {
			serviciosAlquiler.consultarCliente(2153042);
		} catch (ExcepcionServiciosAlquiler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test 
    public void deberiaConsultarClientes() {
    	try {
			serviciosAlquiler.consultarClientes();
		} catch (ExcepcionServiciosAlquiler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test 
    public void deberiaRegistrarItem() {
    	MyBatisExample m = new MyBatisExample();
    	TipoItem newTipoItem= new TipoItem(1,"Videojuego");
    	Item i = new Item(newTipoItem, 1, "CarlosDuty", "Juego shotter", m.parseDate("2000-07-29"), 3000, "Confiabilidad", "Masculino");
    	try {
			serviciosAlquiler.registrarItem(i);
		} catch (ExcepcionServiciosAlquiler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 
    
    @Test 
    public void deberiaConsultarItem() {
    	try {
			serviciosAlquiler.consultarItem(1);
		} catch (ExcepcionServiciosAlquiler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test 
    public void deberiaConsultarTipoItem() {
    	TipoItem newTipoItem= new TipoItem(1,"Videojuego");
    	try {
			serviciosAlquiler.consultarTipoItem(1);
		} catch (ExcepcionServiciosAlquiler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test 
    public void deberiaconsultarTiposItems() {
    	try {
			serviciosAlquiler.consultarTiposItem();
		} catch (ExcepcionServiciosAlquiler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
}