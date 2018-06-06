/*
 * Created on Jan 17, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.dataset.DataSet;

/**
 * @author gerson.a.rodrigues
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public interface TplOfficerTypeDAO {
	/**
	 * @return
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	
//	public DataSet listOfficerType(OfficerTypeFncVO customerFncVO);
	public DataSet loadOfficerType();
	/*
	public boolean insertOfficerType(OfficerTypeFncVO customerFncVO);
	public boolean alterOfficerType(OfficerTypeFncVO customerFncVO);
	public boolean deleteOfficerType(OfficerTypeFncVO customerFncVO);
	public OfficerEntity detailOfficerType(OfficerTypeFncVO customerFncVO);
	public boolean alterApprovingOfficerType(OfficerTypeFncVO customerFncVO);
	public boolean approveOfficerType(OfficerTypeFncVO customerFncVO);
	public boolean notApproveOfficerType(OfficerTypeFncVO customerFncVO);
	public DataSet selectApprovingOfficerType(OfficerTypeFncVO officerFncVO);
    */
}