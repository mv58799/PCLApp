package com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.BaseTplContactCustEntity;
import com.citibank.ods.entity.pl.To3ProductAccountEntity;
import com.citibank.ods.entity.pl.TplContactCustEntity;
import com.citibank.ods.entity.pl.TplDocTransferEntity;
import com.citibank.ods.entity.pl.TplIpDocCallbackEntity;
import com.citibank.ods.entity.pl.TplIpDocPrvtEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package
 *      com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 12, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class IpDocPrvtDetailFncVO extends BaseIpDocPrvtDetailFncVO
{

  private BigInteger m_selectedCtcNbr = null;

  private String m_selectedFullNameText = null;

  private BigInteger m_selectedDocTransferCode = null;

  public IpDocPrvtDetailFncVO()
  {
    m_baseTplIpDocPrvtEntity = new TplIpDocPrvtEntity();

    m_baseTplDocTransferEntity = new TplDocTransferEntity();

    m_baseTplIpDocCallbackEntity = new TplIpDocCallbackEntity();
    
    m_tplContactCustEntity = new TplContactCustEntity();
    
    m_to3ProductAccountEntity = new To3ProductAccountEntity();
    
  }

  /**
   * @return Returns selectedCtcNbr.
   */
  public BigInteger getSelectedCtcNbr()
  {
    return m_selectedCtcNbr;
  }

  /**
   * @param selectedCtcNbr_ Field selectedCtcNbr to be setted.
   */
  public void setSelectedCtcNbr( BigInteger selectedCtcNbr_ )
  {
    m_selectedCtcNbr = selectedCtcNbr_;
  }

  /**
   * @return Returns selectedDocTransferCode.
   */
  public BigInteger getSelectedDocTransferCode()
  {
    return m_selectedDocTransferCode;
  }

  /**
   * @param selectedDocTransferCode_ Field selectedDocTransferCode to be setted.
   */
  public void setSelectedDocTransferCode( BigInteger selectedDocTransferCode_ )
  {
    m_selectedDocTransferCode = selectedDocTransferCode_;
  }

  /**
   * @return Returns selectedFullNameText.
   */
  public String getSelectedFullNameText()
  {
    return m_selectedFullNameText;
  }

  /**
   * @param selectedFullNameText_ Field selectedFullNameText to be setted.
   */
  public void setSelectedFullNameText( String selectedFullNameText_ )
  {
    m_selectedFullNameText = selectedFullNameText_;
  }

}