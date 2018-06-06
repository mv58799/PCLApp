package com.citibank.ods.modules.client.customerprvt.form;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.modules.client.customerprvt.form; 
 *@version 1.0
 *@author l.braga,19/03/2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class CustomerPrvtListForm extends BaseCustomerPrvtListForm
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
