/*
 * Created on 14/11/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.entity.pl;

import java.util.ArrayList;

import com.citibank.ods.entity.pl.valueobject.TplIpDocTransFinancEntityVO;

/**
 * @author lfabiano
 * @since 14/11/2008
 */
public class TplIpDocTransFinancEntity extends BaseTplIpDocTransFinancEntity
{
	  
	  public TplIpDocTransFinancEntity()
	  {
		m_data = new TplIpDocTransFinancEntityVO();
		m_baseDocTransferEntities = new ArrayList();
		m_baseIpDocCallbackEntities = new ArrayList();
	  }
 
}