/*
 * Created on Mar 2, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplOfficerCmplHistoryEntity;

/**
 * @author bruno.zanetti
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface TplOfficerCmplHistDAO extends BaseTplOfficerCmplDAO
{

  public TplOfficerCmplHistoryEntity insert( TplOfficerCmplHistoryEntity officerCmplHistoryEntity_ );

  /**
   * @return
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */

  public DataSet list( BigInteger offcrIntnlNbr_, BigInteger offcrNbr_,
                             String offcrTypeCode_, Date offcrRefDate_ );

}