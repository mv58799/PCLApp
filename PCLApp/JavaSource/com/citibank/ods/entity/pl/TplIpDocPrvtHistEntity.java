package com.citibank.ods.entity.pl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.citibank.ods.entity.pl.valueobject.TplIpDocPrvtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplIpDocPrvtHistEntityVO;

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

public class TplIpDocPrvtHistEntity extends BaseTplIpDocPrvtEntity
{

  public TplIpDocPrvtHistEntity( TplIpDocPrvtEntity tplIpDocPrvtEntity,
                                Date ipDocRefDate )
  {
    m_data = new TplIpDocPrvtHistEntityVO();
    m_baseDocTransferEntities = new ArrayList();
    m_baseIpDocCallbackEntities = new ArrayList();

    TplIpDocPrvtHistEntityVO prvtHistEntityVO = ( TplIpDocPrvtHistEntityVO ) m_data;
    TplIpDocPrvtEntityVO prvtEntityVO = ( TplIpDocPrvtEntityVO ) tplIpDocPrvtEntity.getData();

    prvtHistEntityVO.setCustNbr( prvtEntityVO.getCustNbr() );
    prvtHistEntityVO.setIpDocRefDate( ipDocRefDate );
    prvtHistEntityVO.setLastAuthDate( prvtEntityVO.getLastAuthDate() );
    prvtHistEntityVO.setLastAuthUserId( prvtEntityVO.getLastAuthUserId() );
    prvtHistEntityVO.setLastUpdDate( prvtEntityVO.getLastUpdDate() );
    prvtHistEntityVO.setLastUpdUserId( prvtEntityVO.getLastUpdUserId() );
    prvtHistEntityVO.setRecStatCode( prvtEntityVO.getRecStatCode() );
    prvtHistEntityVO.setIpDocCode( prvtEntityVO.getIpDocCode() );
    prvtHistEntityVO.setIpDocText( prvtEntityVO.getIpDocText() );
    prvtHistEntityVO.setIpInvstCurAcctInd( prvtEntityVO.getIpInvstCurAcctInd() );

    Iterator ipDocCallbackIt = tplIpDocPrvtEntity.getBaseIpDocCallbackEntities().iterator();
    TplIpDocCallbackEntity tplIpDocCallbackEntity;
    TplIpDocCallbackHistEntity tplIpDocCallbackHistEntity;

    while ( ipDocCallbackIt.hasNext() )
    {
      tplIpDocCallbackEntity = ( TplIpDocCallbackEntity ) ipDocCallbackIt.next();
      tplIpDocCallbackHistEntity = new TplIpDocCallbackHistEntity(
                                                                   tplIpDocCallbackEntity,
                                                                   new Date() );
      m_baseIpDocCallbackEntities.add( tplIpDocCallbackHistEntity );
    }

    Iterator docTransferIt = tplIpDocPrvtEntity.getBaseDocTransferEntities().iterator();
    TplDocTransferEntity tplDocTransferEntity;
    TplDocTransferHistEntity tplDocTransferHistEntity;

    while ( docTransferIt.hasNext() )
    {
      tplDocTransferEntity = ( TplDocTransferEntity ) docTransferIt.next();
      tplDocTransferHistEntity = new TplDocTransferHistEntity(
                                                               tplDocTransferEntity,
                                                               new Date() );
      m_baseDocTransferEntities.add( tplDocTransferHistEntity );
    }

  }

  public TplIpDocPrvtHistEntity()
  {
    m_data = new TplIpDocPrvtHistEntityVO();
    m_baseDocTransferEntities = new ArrayList();
    m_baseIpDocCallbackEntities = new ArrayList();
  }

}