/*
 * Created on Mar 2, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplOfficerCmplMovementEntity;

/**
 * @author bruno.zanetti
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface TplOfficerCmplMovDAO extends BaseTplOfficerCmplDAO
{

  public void update( TplOfficerCmplMovementEntity officerCmplMovementEntity_ );

  public void delete( TplOfficerCmplMovementEntity officerCmplMovementEntity_ );

  public TplOfficerCmplMovementEntity insert( TplOfficerCmplMovementEntity officerCmplMovementEntity_ );

  /**
   * @param lastUpdUserId_ TODO
   * @param offcrRefDate TODO
   * @return
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */

  public DataSet list( BigInteger offcrIntnlNbr_, BigInteger offcrNbr_,
                             String offcrTypeCode_, String lastUpdUserId_, String offcrNameText_ );
  
  public boolean exists( TplOfficerCmplMovementEntity tplOfficerCmplMovementEntity_ );

}