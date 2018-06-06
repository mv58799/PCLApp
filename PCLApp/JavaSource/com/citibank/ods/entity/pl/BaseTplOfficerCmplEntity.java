/*
 * Created on Dec 13, 2006
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplOfficerCmplEntityVO;
/**
 * @author User
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BaseTplOfficerCmplEntity extends BaseODSEntity {
  /*
   * Tamanho máximo dos campos
   */
  public static final int C_OFFCR_NBR_SIZE = 6;
  
  public static final int C_OFFCR_TYPE_CODE_SIZE = 2;
  
  public static final int C_OFFCR_INTL_NBR_SIZE = 11;
  
  
  /*
   * VO representando os campos da tabela
   */
  protected BaseTplOfficerCmplEntityVO m_data;

  /*
   * Construtor
   */
  public BaseTplOfficerCmplEntityVO getData()
  {
    return m_data;
  }
}