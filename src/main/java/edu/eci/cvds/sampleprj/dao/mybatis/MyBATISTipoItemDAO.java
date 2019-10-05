package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;

public class MyBATISTipoItemDAO implements TipoItemDAO {
	@Inject
	  private TipoItemMapper tipoItemMapper;    

	  @Override
	  public void save(TipoItem it) throws PersistenceException{
	  try{
		  tipoItemMapper.insertarTipoItem(it);
	  }
	  catch(org.apache.ibatis.exceptions.PersistenceException e){
	      throw new PersistenceException("Error al registrar el item "+it.toString(),e);
	  }        

	  }

	  @Override
	  public TipoItem load(int id) throws PersistenceException {
	  try{
	      return tipoItemMapper.getTipoItem(id);
	  }
	  catch(org.apache.ibatis.exceptions.PersistenceException e){
	      throw new PersistenceException("Error al consultar el item "+id,e);
	  }


	  }
}
