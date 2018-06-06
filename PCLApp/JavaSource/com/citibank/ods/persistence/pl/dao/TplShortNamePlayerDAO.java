package com.citibank.ods.persistence.pl.dao;

import java.util.List;

import com.citibank.ods.entity.pl.TplShortNamePlayerEntity;
import com.citibank.ods.modules.product.player.functionality.valueobject.ShortNameVO;

/**
 * 
 * @author aribas
 *
 */
public interface TplShortNamePlayerDAO {
	public void insert( TplShortNamePlayerEntity tplShortNamePlayerEntity);
	
	public void update( String plyrCnpjNbr );
	
	public List<ShortNameVO> selectByPlyr( String plyrCnpjNbr_ );
	
	public List<ShortNameVO> selectByPlyrCnpj( String plyrCnpjNbr_ );
	
	public List<ShortNameVO> selectByPk( String plyrCnpjNbr_ );
	
	public void inactivate( String plyrCnpjNbr_ );
	
	public boolean exists( TplShortNamePlayerEntity tplShortNamePlayerEntity );
	
	public void activate( TplShortNamePlayerEntity tplShortNamePlayerEntity );
}
