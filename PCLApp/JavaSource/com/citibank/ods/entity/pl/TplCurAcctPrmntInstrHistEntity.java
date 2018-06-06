package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.bg.valueobject.TbgPointAcctInvstEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplCurAcctPrmntInstrEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplCurAcctPrmntInstrHistEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplIpDocPrvtEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.entity.pl;
 * @version 1.0
 * @author michele.monteiro,15/06/2007
 *  
 */

public class TplCurAcctPrmntInstrHistEntity extends
    BaseTplCurAcctPrmntInstrEntity
{

  public TplCurAcctPrmntInstrHistEntity()
  {
    m_data = new TplCurAcctPrmntInstrHistEntityVO();
    m_dataIP = new TplIpDocPrvtEntityVO();
    m_dataPoint = new TbgPointAcctInvstEntityVO();
  }

  public TplCurAcctPrmntInstrHistEntity(
                                        TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity_,
                                        Date curAcctPrmntInstrRefDate_ )
  {
    m_data = new TplCurAcctPrmntInstrHistEntityVO();

    TplCurAcctPrmntInstrEntityVO tplCurAcctPrmntInstrEntityVO = ( TplCurAcctPrmntInstrEntityVO ) tplCurAcctPrmntInstrEntity_.getData();

    m_data.setCustNbr( tplCurAcctPrmntInstrEntityVO.getCustNbr() );
    m_data.setPrmntInstrCode( tplCurAcctPrmntInstrEntityVO.getPrmntInstrCode() );
    m_data.setProdAcctCode( tplCurAcctPrmntInstrEntityVO.getProdAcctCode() );
    m_data.setProdUnderAcctCode( tplCurAcctPrmntInstrEntityVO.getProdUnderAcctCode() );
    m_data.setLastUpdUserId( tplCurAcctPrmntInstrEntityVO.getLastUpdUserId() );
    m_data.setLastUpdDate( tplCurAcctPrmntInstrEntityVO.getLastUpdDate() );
    ( ( TplCurAcctPrmntInstrHistEntityVO ) m_data ).setLastAuthUserId( tplCurAcctPrmntInstrEntityVO.getLastAuthUserId() );
    ( ( TplCurAcctPrmntInstrHistEntityVO ) m_data ).setLastAuthDate( tplCurAcctPrmntInstrEntityVO.getLastAuthDate() );
    ( ( TplCurAcctPrmntInstrHistEntityVO ) m_data ).setRecStatCode( tplCurAcctPrmntInstrEntityVO.getRecStatCode() );
    ( ( TplCurAcctPrmntInstrHistEntityVO ) m_data ).setCurAcctPrmntInstrRefDate( curAcctPrmntInstrRefDate_ );
  }

}