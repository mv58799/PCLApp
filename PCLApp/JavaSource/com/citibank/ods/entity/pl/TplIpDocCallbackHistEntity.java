package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplIpDocCallbackHistEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.entity.pl;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 16, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class TplIpDocCallbackHistEntity extends BaseTplIpDocCallbackEntity {
	/**
	 * Construtor padrão - sem argumentos
	 */
	public TplIpDocCallbackHistEntity() {
		m_data = new TplIpDocCallbackHistEntityVO();
	}

	public TplIpDocCallbackHistEntity(TplIpDocCallbackEntity callbackEntity__,
			Date docTransferRefDate) {
		m_data = new TplIpDocCallbackHistEntityVO();

		m_data.setCtcNbr(callbackEntity__.getData().getCtcNbr());
		m_data.setCustNbr(callbackEntity__.getData().getCustNbr());
		m_data
				.setIpCallbackCode(callbackEntity__.getData()
						.getIpCallbackCode());
		m_data.setIpDocCode(callbackEntity__.getData().getIpDocCode());
		m_data.setLastAuthDate(callbackEntity__.getData().getLastAuthDate());
		m_data.setLastAuthUserId(callbackEntity__.getData().getLastAuthUserId());
		m_data.setLastUpdDate(callbackEntity__.getData().getLastUpdDate());
		m_data.setLastUpdUserId(callbackEntity__.getData().getLastUpdUserId());
		m_data.setRecStatCode(callbackEntity__.getData().getRecStatCode());
		((TplIpDocCallbackHistEntityVO)m_data).setIpDocCallbackRefDate(docTransferRefDate);
	}

}

