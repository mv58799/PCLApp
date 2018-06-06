package com.citibank.ods.entity.pl;

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
 * @author michele.monteiro,13/06/2007
 *  
 */

public class TplCurAcctPrmntInstrMovEntity extends
    BaseTplCurAcctPrmntInstrEntity
{

  /**
   * Construtor padrão
   */
  public TplCurAcctPrmntInstrMovEntity()
  {
    m_data = new TplCurAcctPrmntInstrMovEntityVO();
    m_dataIP = new TplIpDocPrvtEntityVO();
    m_dataPoint = new TbgPointAcctInvstEntityVO();

  }

  /**
   * Construtor - Carrega os atributos de movimento com os atributos da current
   */

  public TplCurAcctPrmntInstrMovEntity(
                                       TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity_,
                                       String opernCode_ )
  {
    m_data = new TplCurAcctPrmntInstrMovEntityVO();
    TplCurAcctPrmntInstrEntityVO tplCurAcctPrmntInstrEntityVO = ( TplCurAcctPrmntInstrEntityVO ) tplCurAcctPrmntInstrEntity_.getData();

    m_data.setCustNbr( tplCurAcctPrmntInstrEntityVO.getCustNbr() );
    m_data.setLastUpdDate( tplCurAcctPrmntInstrEntityVO.getLastUpdDate() );
    m_data.setLastUpdUserId( tplCurAcctPrmntInstrEntityVO.getLastUpdUserId() );
    m_data.setPrmntInstrCode( tplCurAcctPrmntInstrEntityVO.getPrmntInstrCode() );
    m_data.setProdAcctCode( tplCurAcctPrmntInstrEntityVO.getProdAcctCode() );
    m_data.setProdUnderAcctCode( tplCurAcctPrmntInstrEntityVO.getProdUnderAcctCode() );
    ( ( TplCurAcctPrmntInstrMovEntityVO ) m_data ).setOpernCode( opernCode_ );
  }

}