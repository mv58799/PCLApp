/*
 * Created on Apr 16, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplDocTransferEntityVO;

/**
 * @author gerson.a.rodrigues
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseTplDocTransferEntity extends BaseODSEntity
{
  /**
   * Entity VO da categoria de risco
   */
  protected BaseTplDocTransferEntityVO m_data;

  public static final int C_DOC_TRANFER_CODE_SIZE = 11;

  public static final int C_OWN_DEST_ACCT_IND_SIZE = 1;

  public static final int C_TXN_TYPE_CODE_SIZE = 1;

  public static final int C_AGN_BANK_CODE_SIZE = 3;

  public static final int C_CUST_NBR_SIZE = 11;

  public static final int C_BRCH_CODE_SIZE = 4;

  public static final int C_IP_DOC_CODE_SIZE = 6;

  public static final int C_CUR_ACCT_NBR_SIZE = 15;

  /**
   * Retorna o entity VO do agregador de produtos
   * @return
   */
  public BaseTplDocTransferEntityVO getData()
  {
    return m_data;
  }

}