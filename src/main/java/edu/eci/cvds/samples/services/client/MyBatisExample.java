/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.samples.services.client;



import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import edu.eci.cvds.samples.services.impl.ServiciosAlquilerImpl;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    public static void main(String args[]) throws SQLException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();

        
        //Crear el mapper y usarlo: 
        //ClienteMapper cm=sqlss.getMapper(ClienteMapper.class)
        //cm...
        ClienteMapper cm=sqlss.getMapper(ClienteMapper.class);
        //System.out.println(cm.consultarCliente(4));
        //cm.agregarItemRentadoACliente(4, 1, parseDate("2019-03-12"), parseDate("2019-04-12"));
        ItemMapper prueba= sqlss.getMapper(ItemMapper.class);
        TipoItem newTipoItem= new TipoItem(1,"Videojuego");
        //Item newInsertion= new Item(newTipoItem, 56985, "Prueba", "No se que poner", parseDate("2000-07-29"), 1514854, "hola", "hgfhf");
        //prueba.insertarItem(newInsertion);
        //System.out.println(prueba.consultarItems());
        //System.out.println(prueba.consultarItem(56985));
        //System.out.println(cm.consultarClientes());
        //Cliente c = new Cliente("Jose", 10254862, "1852832", "No sea sapo", "goku@gmail.com");
        //cm.insertarCliente(c);   
        ServiciosAlquiler servicios = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
        //System.out.println(cm.consultarCliente(0));
        
        TipoItemMapper itemmapper = sqlss.getMapper(TipoItemMapper.class);
        System.out.println(itemmapper.consultarTiposItem());
        	
        	
        
        

        
        
        sqlss.commit();
        
        
        sqlss.close();

        
    }

    public static java.util.Date parseDate(String date) {
        try {
            return  new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
