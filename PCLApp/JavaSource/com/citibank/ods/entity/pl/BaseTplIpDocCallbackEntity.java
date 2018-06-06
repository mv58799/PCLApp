/*
 * Created on Apr 16, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplIpDocCallbackEntityVO;

/**
 * @author gerson.a.rodrigues
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseTplIpDocCallbackEntity extends BaseODSEntity
{

  public static final int C_CUST_NBR_SIZE = 11;

  public static final int C_IP_DOC_CODE_SIZE = 6;

  public static final int C_IP_DOC_TEXT_SIZE = 40;

  public static final int C_IP_INVST_CUR_ACCT_IND_SIZE = 1;

  /**
   * Entity VO da categoria de risco
   */
  protected BaseTplIpDocCallbackEntityVO m_data;

  private String m_fullNameText;

  /**
   * Retorna o entity VO do agregador de produtos
   * @return
   */
  public BaseTplIpDocCallbackEntityVO getData()
  {
    return m_data;
  }

  public boolean equals( Object obj_ )
  {
    BaseTplIpDocCallbackEntity baseTplIpDocCallbackEntity = ( BaseTplIpDocCallbackEntity ) obj_;

    boolean isCtcNbrEqual = baseTplIpDocCallbackEntity.getData().getCtcNbr().equals(
                                                                                     this.getData().getCtcNbr() );

    return isCtcNbrEqual;
  }

  /**
   * @return Returns fullNameText.
   */
  public String getFullNameText()
  {
    return m_fullNameText;
  }

  /**
   * @param fullNameText_ Field fullNameText to be setted.
   */
  public void setFullNameText( String fullNameText_ )
  {
    m_fullNameText = fullNameText_;
  }
}