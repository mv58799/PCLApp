package com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.BaseTplContactCustEntity;
import com.citibank.ods.entity.pl.To3ProductAccountEntity;
import com.citibank.ods.entity.pl.TplContactCustEntity;
import com.citibank.ods.entity.pl.TplDocTransferMovEntity;
import com.citibank.ods.entity.pl.TplIpDocCallbackMovEntity;
import com.citibank.ods.entity.pl.TplIpDocPrvtMovEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject; 
 *@version 1.0
 *@author gerson.a.rodrigues,Apr 12, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class IpDocPrvtMovementDetailFncVO extends BaseIpDocPrvtDetailFncVO
{
  
  private BigInteger m_selectedCtcNbr = null;

  private BigInteger m_selectedFullNameText = null;

  private BigInteger m_selectedDocTransferCode = null;

  public IpDocPrvtMovementDetailFncVO()
  {
    m_baseTplIpDocPrvtEntity = new TplIpDocPrvtMovEntity();

    m_baseTplDocTransferEntity = new TplDocTransferMovEntity();

    m_baseTplIpDocCallbackEntity = new TplIpDocCallbackMovEntity();
    
	m_to3ProductAccountEntity = new To3ProductAccountEntity();
	
	m_tplContactCustEntity = new TplContactCustEntity();
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
  public BigInteger getSelectedFullNameText()
  {
    return m_selectedFullNameText;
  }

  /**
   * @param selectedFullNameText_ Field selectedFullNameText to be setted.
   */
  public void setSelectedFullNameText( BigInteger selectedFullNameText_ )
  {
    m_selectedFullNameText = selectedFullNameText_;
  }

}
