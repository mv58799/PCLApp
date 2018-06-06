/*
 * Created on Mar 2, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplOfficerCmplMovementEntityVO;

/**
 * @author bruno.zanetti
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TplOfficerCmplMovementEntity extends BaseTplOfficerCmplEntity
{
  /*
   * Tamanho máximo dos campos
   */
  public static final int C_LAST_UPD_USER_ID_SIZE = 11;

  /*
   * Construtor
   */
  public TplOfficerCmplMovementEntity()
  {
    m_data = new TplOfficerCmplMovementEntityVO();
  }
}