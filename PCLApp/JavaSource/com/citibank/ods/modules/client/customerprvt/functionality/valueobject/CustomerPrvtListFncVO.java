package com.citibank.ods.modules.client.customerprvt.functionality.valueobject;


//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.modules.client.customerPrvt.functionality.valueObject; 
 *@version 1.0
 *@author l.braga,13/03/2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class CustomerPrvtListFncVO extends BaseCustomerPrvtListFncVO
{
	//indica se todos os dados da pesquisa foram retornados para a tela
	private String flagAllData;

	public String getFlagAllData() {
		return flagAllData;
	}

	public void setFlagAllData(String flagAllData) {
		this.flagAllData = flagAllData;
	}
}
