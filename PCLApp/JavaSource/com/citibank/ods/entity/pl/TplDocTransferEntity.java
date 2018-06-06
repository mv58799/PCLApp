package com.citibank.ods.entity.pl;

import java.math.BigInteger;

import com.citibank.ods.entity.pl.valueobject.TplDocTransferEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.entity.pl;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 16, 2007
 *  
 */

public class TplDocTransferEntity extends BaseTplDocTransferEntity
{

  /**
   * Construtor padrão - sem argumentos
   */
  public TplDocTransferEntity()
  {
    m_data = new TplDocTransferEntityVO();
  }

  public TplDocTransferEntity( BaseTplDocTransferEntity TplDocTransferEntity_ )
  {
    m_data = new TplDocTransferEntityVO();

    m_data.setAgnBankCode( TplDocTransferEntity_.getData().getAgnBankCode() != null
                                                                                   ? new BigInteger(
                                                                                                     TplDocTransferEntity_.getData().getAgnBankCode().toString() )
                                                                                   : null );
    m_data.setBrchCode( TplDocTransferEntity_.getData().getBrchCode() != null
                                                                             ? new BigInteger(
                                                                                               TplDocTransferEntity_.getData().getBrchCode().toString() )
                                                                             : null );
    m_data.setCurAcctNbr( TplDocTransferEntity_.getData().getCurAcctNbr() != null
                                                                                 ? new BigInteger(
                                                                                                   TplDocTransferEntity_.getData().getCurAcctNbr().toString() )
                                                                                 : null );
    m_data.setCustNbr( TplDocTransferEntity_.getData().getCustNbr() != null
                                                                           ? new BigInteger(
                                                                                             TplDocTransferEntity_.getData().getCustNbr().toString() )
                                                                           : null );
    m_data.setDocTransferCode( TplDocTransferEntity_.getData().getDocTransferCode() != null
                                                                                           ? new BigInteger(
                                                                                                             TplDocTransferEntity_.getData().getDocTransferCode().toString() )
                                                                                           : null );
    m_data.setIpDocCode( TplDocTransferEntity_.getData().getIpDocCode() != null
                                                                               ? new BigInteger(
                                                                                                 TplDocTransferEntity_.getData().getIpDocCode().toString() )
                                                                               : null );
    m_data.setLastUpdDate( TplDocTransferEntity_.getData().getLastUpdDate() != null
                                                                                   ? TplDocTransferEntity_.getData().getLastUpdDate()
                                                                                   : null );
    m_data.setLastUpdUserId( TplDocTransferEntity_.getData().getLastUpdUserId() != null
                                                                                       ? TplDocTransferEntity_.getData().getLastUpdUserId().toString()
                                                                                       : null );
    m_data.setOwnDestAcctInd( TplDocTransferEntity_.getData().getOwnDestAcctInd() != null
                                                                                         ? TplDocTransferEntity_.getData().getOwnDestAcctInd().toString()
                                                                                         : null );
    m_data.setTxnTypeCode( TplDocTransferEntity_.getData().getTxnTypeCode() != null
                                                                                   ? new BigInteger(
                                                                                                     TplDocTransferEntity_.getData().getTxnTypeCode().toString() )
                                                                                   : null );
	
	m_data.setBeneAcctTypeCode(TplDocTransferEntity_.getData().getBeneAcctTypeCode() != null
	                            ? TplDocTransferEntity_.getData().getBeneAcctTypeCode()
	                           : null );	                                                                               
	                           
	m_data.setBeneCpfCnpjNbr(TplDocTransferEntity_.getData().getBeneCpfCnpjNbr() != null
									? TplDocTransferEntity_.getData().getBeneCpfCnpjNbr()
								   : null );
    
	m_data.setBeneMainDestAcctInd(TplDocTransferEntity_.getData().getBeneMainDestAcctInd() != null
									? TplDocTransferEntity_.getData().getBeneMainDestAcctInd()
								   : null );		                           
								   
	m_data.setBeneNameText(TplDocTransferEntity_.getData().getBeneNameText() != null
										? TplDocTransferEntity_.getData().getBeneNameText()
									   : null );		                            
                                                                                       
  }

  public boolean equals( Object obj )
  {

    TplDocTransferEntity tplDocTransferEntity = ( TplDocTransferEntity ) obj;

    if ( m_data.getAgnBankCode().equals(
                                         tplDocTransferEntity.getData().getAgnBankCode() )
         && m_data.getBrchCode().equals(
                                         tplDocTransferEntity.getData().getBrchCode() )
         && m_data.getCurAcctNbr().equals(
                                           tplDocTransferEntity.getData().getCurAcctNbr() )
         && m_data.getOwnDestAcctInd().equals(
                                               tplDocTransferEntity.getData().getOwnDestAcctInd() )
         && m_data.getTxnTypeCode().equals(
                                            tplDocTransferEntity.getData().getTxnTypeCode() ) )
    {
      return true;
    }
    return false;
  }

}