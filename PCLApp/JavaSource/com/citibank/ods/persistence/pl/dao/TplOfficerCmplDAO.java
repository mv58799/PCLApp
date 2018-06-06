/*
 * Created on Mar 2, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplOfficerCmplEntity;

/**
 * @author bruno.zanetti
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface TplOfficerCmplDAO extends BaseTplOfficerCmplDAO
{

  public void update( TplOfficerCmplEntity officerCmplEntity_ );

  public void delete( TplOfficerCmplEntity officerCmplEntity_ );

  public TplOfficerCmplEntity insert( TplOfficerCmplEntity officerCmplEntity_ );

  /**
   * @param offcrRefDate TODO
   * @return
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */

  public DataSet list( BigInteger offcrIntnlNbr_, BigInteger offcrNbr_,
                      String offcrTypeCode_ );

  public boolean exists( TplOfficerCmplEntity officerCmplEntity_ );

  public boolean existsActive( TplOfficerCmplEntity officerCmplEntity_ );

}