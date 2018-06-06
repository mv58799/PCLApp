package com.citibank.ods.entity.pl;

import java.util.ArrayList;

import com.citibank.ods.entity.pl.valueobject.TplIpDocPrvtMovEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.entity.pl;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 12, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class TplIpDocPrvtMovEntity extends BaseTplIpDocPrvtEntity
{
  public static final int C_LAST_UPD_USER_ID_SIZE = 20;

  public static final int C_OPERN_CODE_SIZE = 1;

  public TplIpDocPrvtMovEntity()
  {
    m_data = new TplIpDocPrvtMovEntityVO();
    m_baseDocTransferEntities = new ArrayList();
    m_baseIpDocCallbackEntities = new ArrayList();
  }

  /*
   * Construtor parametro current
   */
  public TplIpDocPrvtMovEntity( TplIpDocPrvtEntity prvtEntity_,
                                ArrayList docTransferEntities,
                                ArrayList ipDocCallbackEntities,
                               String opernCode_ )
  {
    m_data = new TplIpDocPrvtMovEntityVO();

    m_data.setCustNbr( prvtEntity_.getData().getCustNbr() );
    m_data.setIpDocCode( prvtEntity_.getData().getIpDocCode() );
    m_data.setIpDocText( prvtEntity_.getData().getIpDocText() );
    m_data.setIpInvstCurAcctInd( prvtEntity_.getData().getIpInvstCurAcctInd() );
    m_data.setLastUpdDate( prvtEntity_.getData().getLastUpdDate() );
    m_data.setLastUpdUserId( prvtEntity_.getData().getLastUpdUserId() );
    setBaseDocTransferEntities(docTransferEntities);
    setBaseIpDocCallbackEntities(ipDocCallbackEntities);
    ( ( TplIpDocPrvtMovEntityVO ) m_data ).setOpernCode( opernCode_ );
  }
}