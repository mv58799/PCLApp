package com.citibank.ods.persistence.pl.dao;

import java.util.List;

import com.citibank.ods.entity.pl.TplShortNamePlayerMovEntity;
import com.citibank.ods.modules.product.player.functionality.valueobject.ShortNameVO;

/**
 * 
 * @author aribas
 *
 */
public interface TplShortNamePlayerMovDAO {
	
	public TplShortNamePlayerMovEntity insert( TplShortNamePlayerMovEntity tplShortNamePlayerMovEntity);
	
	public void delete( String plyrCnpjNbr );
	
	public List<ShortNameVO> selectByPlyr( String plyrCnpjNbr_ );
	
	public List<ShortNameVO> selectByPlyrCnpj( String plyrCnpjNbr_ );

}
