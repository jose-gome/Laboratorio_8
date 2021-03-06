package edu.eci.cvds.sampleprj.dao.mybatis.mappers;


import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.TipoItem;

public interface TipoItemMapper {
    
    
    public List<TipoItem> consultarTiposItem();
    
    public TipoItem getTipoItem(@Param("id")int id);
    
    public void addTipoItem(@Param("des")String des);
    
    public void insertarTipoItem(@Param("tipoItem")TipoItem tipoItem);
    
}
