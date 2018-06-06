package com.citibank.ods.modules.client.ipdocprvt.form;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.modules.client.ipdocprvt.form; 
 *@version 1.0
 *@author Hamilton Matos,Apr 18, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public interface IpDocPrvtDetailable
{
	/**
	 * @return Returns the m_selectedLoadProcNbr.
	 */
	public String getSelectedCustNbr();
	/**
	 * @param offcrNbr The m_selectedLoadProcNbr to set.
	 */
	public void setSelectedCustNbr(String CustNbr_);
	
	/**
	 * @return Returns the m_selectedLoadProcNbr.
	 */
	public String getSelectedIpDocCode();
	/**
	 * @param offcrNbr The m_selectedLoadProcNbr to set.
	 */
	public void setSelectedIpDocCode(String ipDocCode_);

}
