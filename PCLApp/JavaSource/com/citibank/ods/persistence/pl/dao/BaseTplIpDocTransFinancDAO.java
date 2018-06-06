/*
 * Created on 14/11/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.entity.pl.BaseTplIpDocTransFinancEntity;

/**
 * @author lfabiano
 * @since 14/11/2008
 */
public interface BaseTplIpDocTransFinancDAO 
{
	public BaseTplIpDocTransFinancEntity find( BaseTplIpDocTransFinancEntity tplIpDocTransFinancEntity_ );

	public void setBaseTplIpDocTransFinancEntity(BaseTplIpDocTransFinancEntity tplIpDocTransFinancEntity);

	public void getBaseTplIpDocTransFinancEntity();
	
}
