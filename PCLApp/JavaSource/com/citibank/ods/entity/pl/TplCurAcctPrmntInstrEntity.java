package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.bg.valueobject.TbgPointAcctInvstEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplCurAcctPrmntInstrEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplCurAcctPrmntInstrMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplIpDocPrvtEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.entity.pl;
 * @version 1.0
 * @author michele.monteiro,04/06/2007
 *  
 */

public class TplCurAcctPrmntInstrEntity extends BaseTplCurAcctPrmntInstrEntity
{

  /**
   * Construtor padrão
   */
  public TplCurAcctPrmntInstrEntity()
  {
    m_data = new TplCurAcctPrmntInstrEntityVO();
    m_dataIP = new TplIpDocPrvtEntityVO();
    m_dataPoint = new TbgPointAcctInvstEntityVO();
  }

  public TplCurAcctPrmntInstrEntity(
                                    TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntity_,
                                    Date lastAuthDate_, String lastAuthUserId_,
                                    String recStatCode_ )
  {
    m_data = new TplCurAcctPrmntInstrEntityVO();
    TplCurAcctPrmntInstrMovEntityVO tplCurAcctPrmntInstrMovEntityVO = ( TplCurAcctPrmntInstrMovEntityVO ) tplCurAcctPrmntInstrMovEntity_.getData();

    m_data.setPrmntInstrCode( tplCurAcctPrmntInstrMovEntityVO.getPrmntInstrCode() );
    m_data.setProdAcctCode( tplCurAcctPrmntInstrMovEntityVO.getProdAcctCode() );
    m_data.setProdUnderAcctCode( tplCurAcctPrmntInstrMovEntityVO.getProdUnderAcctCode() );
    m_data.setCustNbr( tplCurAcctPrmntInstrMovEntityVO.getCustNbr() );
    m_data.setLastUpdUserId( tplCurAcctPrmntInstrMovEntityVO.getLastUpdUserId() );
    m_data.setLastUpdDate( tplCurAcctPrmntInstrMovEntityVO.getLastUpdDate() );
    ( ( TplCurAcctPrmntInstrEntityVO ) m_data ).setLastAuthUserId( lastAuthUserId_ );
    ( ( TplCurAcctPrmntInstrEntityVO ) m_data ).setLastAuthDate( lastAuthDate_ );
    ( ( TplCurAcctPrmntInstrEntityVO ) m_data ).setRecStatCode( recStatCode_ );
  }

  public boolean equals( TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity_ )
  {
    TplCurAcctPrmntInstrEntityVO tplCurAcctPrmntInstrEntityVO = ( TplCurAcctPrmntInstrEntityVO ) tplCurAcctPrmntInstrEntity_.getData();

    if ( m_data.getPrmntInstrCode().equals(
                                            tplCurAcctPrmntInstrEntityVO.getPrmntInstrCode() )
         && m_dataIP.getIpDocText().equals(
                                            tplCurAcctPrmntInstrEntity_.getDataIP().getIpDocText() )
         && m_dataIP.getIpInvstCurAcctInd().equals(
                                                    tplCurAcctPrmntInstrEntity_.getDataIP().getIpInvstCurAcctInd() ) )
    {
      return true;
    }
    else
    {
      return false;
    }
  }

}