package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplDocTransferMovEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.entity.pl;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 16, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class TplDocTransferMovEntity extends BaseTplDocTransferEntity
{
  public TplDocTransferMovEntity()
  {
    m_data = new TplDocTransferMovEntityVO();
  }

  public TplDocTransferMovEntity( BaseTplDocTransferEntity TplDocTransferEntity_ )
  {
    m_data = new TplDocTransferMovEntityVO();
    m_data.setAgnBankCode( TplDocTransferEntity_.getData().getAgnBankCode() );
    m_data.setBrchCode( TplDocTransferEntity_.getData().getBrchCode() );
    m_data.setCurAcctNbr( TplDocTransferEntity_.getData().getCurAcctNbr() );
    m_data.setCustNbr( TplDocTransferEntity_.getData().getCustNbr() );
    m_data.setDocTransferCode( TplDocTransferEntity_.getData().getDocTransferCode() );
    m_data.setIpDocCode( TplDocTransferEntity_.getData().getIpDocCode() );
    m_data.setLastUpdDate( TplDocTransferEntity_.getData().getLastUpdDate() );
    m_data.setLastUpdUserId( TplDocTransferEntity_.getData().getLastUpdUserId() );
    m_data.setOwnDestAcctInd( TplDocTransferEntity_.getData().getOwnDestAcctInd() );
    m_data.setTxnTypeCode( TplDocTransferEntity_.getData().getTxnTypeCode() );
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
    
    ( ( TplDocTransferMovEntityVO ) m_data ).setOpernCode( ( ( TplDocTransferMovEntityVO ) TplDocTransferEntity_.getData() ).getOpernCode() );
  }

  public boolean equals( Object obj )
  {

    TplDocTransferMovEntity tplDocTransferMovEntity = ( TplDocTransferMovEntity ) obj;

    if ( m_data.getAgnBankCode().equals(
                                         tplDocTransferMovEntity.getData().getAgnBankCode() )
         && m_data.getBrchCode().equals(
                                         tplDocTransferMovEntity.getData().getBrchCode() )
         && m_data.getCurAcctNbr().equals(
                                           tplDocTransferMovEntity.getData().getCurAcctNbr() )
         && m_data.getTxnTypeCode().equals(
                                            tplDocTransferMovEntity.getData().getTxnTypeCode() )
         && m_data.getOwnDestAcctInd().equals(
                                         tplDocTransferMovEntity.getData().getOwnDestAcctInd() ) )
    {
      return true;
    }
    return false;
  }
}