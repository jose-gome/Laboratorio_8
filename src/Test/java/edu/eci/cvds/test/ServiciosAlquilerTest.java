package edu.eci.cvds.test;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
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
    public void hola() {
    	try {
			System.out.println(serviciosAlquiler.consultarTiposItem());
		} catch (ExcepcionServiciosAlquiler e) {
			Assert.assertTrue(false);
		}
    	Assert.assertTrue(true);
    }
}