package com.citibank.ods.entity.pl;

import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplIpDocPrvtEntityVO;

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

public class TplIpDocPrvtEntity extends BaseTplIpDocPrvtEntity
{
  public TplIpDocPrvtEntity( TplIpDocPrvtMovEntity TplIpDocPrvtMovEntity_,
                            Date lastAuthDate_, String lastAuthUserID_,
                            String recStatCode_ )
  {
    TplIpDocCallbackEntity tplIpDocCallbackEntity;
    TplDocTransferEntity tplDocTransferEntity;
    m_data = new TplIpDocPrvtEntityVO();
    m_baseDocTransferEntities = new ArrayList();
    m_baseIpDocCallbackEntities = new ArrayList();

    m_data.setCustNbr( TplIpDocPrvtMovEntity_.getData().getCustNbr() );
    m_data.setIpDocCode( TplIpDocPrvtMovEntity_.getData().getIpDocCode() );
    m_data.setIpDocText( TplIpDocPrvtMovEntity_.getData().getIpDocText() );
    m_data.setIpInvstCurAcctInd( TplIpDocPrvtMovEntity_.getData().getIpInvstCurAcctInd() );
    m_data.setLastUpdDate( TplIpDocPrvtMovEntity_.getData().getLastUpdDate() );
    m_data.setLastUpdUserId( TplIpDocPrvtMovEntity_.getData().getLastUpdUserId() );

    for ( int i = 0; i < TplIpDocPrvtMovEntity_.getBaseIpDocCallbackEntities().size(); i++ )
    {
      tplIpDocCallbackEntity = new TplIpDocCallbackEntity(
                                                           ( TplIpDocCallbackMovEntity ) TplIpDocPrvtMovEntity_.getBaseIpDocCallbackEntities().get(
                                                                                                                                                    i ) );
      m_baseIpDocCallbackEntities.add( tplIpDocCallbackEntity );
    }

    for ( int i = 0; i < TplIpDocPrvtMovEntity_.getBaseDocTransferEntities().size(); i++ )
    {
      tplDocTransferEntity = new TplDocTransferEntity(
                                                       ( TplDocTransferMovEntity ) TplIpDocPrvtMovEntity_.getBaseDocTransferEntities().get(
                                                                                                                                            i ) );
      m_baseDocTransferEntities.add( tplDocTransferEntity );
    }

    ( ( TplIpDocPrvtEntityVO ) m_data ).setLastAuthDate( lastAuthDate_ );
    ( ( TplIpDocPrvtEntityVO ) m_data ).setLastAuthUserId( lastAuthUserID_ );
    ( ( TplIpDocPrvtEntityVO ) m_data ).setRecStatCode( recStatCode_ );
  }

  public TplIpDocPrvtEntity()
  {
    m_data = new TplIpDocPrvtEntityVO();
    m_baseDocTransferEntities = new ArrayList();
    m_baseIpDocCallbackEntities = new ArrayList();
  }

}