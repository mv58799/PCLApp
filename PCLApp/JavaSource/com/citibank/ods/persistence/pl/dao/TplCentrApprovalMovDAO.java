/*
 * Created on 06/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;

/**
 * @author citi
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface TplCentrApprovalMovDAO {
	
	public DataSet list( String m_moduleProcessText_,String lastUpdUserId_, boolean hasAccess5001 );
						  
	public DataSet loadDomain(boolean hasAccess5001 );						  

}
