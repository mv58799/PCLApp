/*
 * Created on 14/11/2008
 */
package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.entity.pl.TplIpDocTransFinancEntity;

/**
 * @author lfabiano
 * @since 14/11/2008
 */
public interface TplIpDocTransFinancDAO extends BaseTplIpDocTransFinancDAO
{
	public void update( TplIpDocTransFinancEntity tplIpDocTransFinancEntity_ );

	public void delete( TplIpDocTransFinancEntity tplIpDocTransFinancEntity_ );

	public boolean exists( TplIpDocTransFinancEntity tplIpDocTransFinancEntity_ );

	public boolean existsActive( TplIpDocTransFinancEntity tplIpDocTransFinancEntity_ );
	
	public TplIpDocTransFinancEntity insert( TplIpDocTransFinancEntity tplIpDocTransFinancEntity_ );
	
	public Integer getSeqNextVal();

}
