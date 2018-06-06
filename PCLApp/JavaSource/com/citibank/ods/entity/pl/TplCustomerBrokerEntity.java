package com.citibank.ods.entity.pl;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplCustomerBrokerEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplCustomerBrokerMovEntityVO;
/**
 * Classe que instancia a entidade correspondente a tabela : TplCustomerBroker
 * @author Hamilton Matos
 */

public class TplCustomerBrokerEntity extends BaseTplCustomerBrokerEntity
{

  public TplCustomerBrokerEntity()
  {
    m_data = new TplCustomerBrokerEntityVO();
  }

  public TplCustomerBrokerEntity(
                                 TplCustomerBrokerMovEntity tplCustomerBrokerMovEntity_,
                                 Date lastAuthDate_, String lastAuthUserId_,
                                 String recStatCode_ )
  {
    m_data = new TplCustomerBrokerEntityVO();

    TplCustomerBrokerEntityVO tplCustomerBrokerEntityVO = ( TplCustomerBrokerEntityVO ) m_data;
    TplCustomerBrokerMovEntityVO tplCustomerBrokerMovEntityVO = ( TplCustomerBrokerMovEntityVO ) tplCustomerBrokerMovEntity_.getData();

    tplCustomerBrokerEntityVO.setBkrCnpjNbr( tplCustomerBrokerMovEntityVO.getBkrCnpjNbr() );

    tplCustomerBrokerEntityVO.setLastUpdDate( tplCustomerBrokerMovEntityVO.getLastUpdDate() );
    tplCustomerBrokerEntityVO.setLastUpdUserId( tplCustomerBrokerMovEntityVO.getLastUpdUserId() );
    tplCustomerBrokerEntityVO.setLastAuthDate( lastAuthDate_ );
    tplCustomerBrokerEntityVO.setLastAuthUserId( lastAuthUserId_ );
    tplCustomerBrokerEntityVO.setRecStatCode( recStatCode_ );
  }

  public TplCustomerBrokerEntity( TplBrokerEntity tplBrokerEntity_,
                                 BigInteger custNbr_, BigInteger bkrCustNbr_ )
  {
    m_data = new TplCustomerBrokerEntityVO();

    TplCustomerBrokerEntityVO tplCustomerBrokerEntityVO = ( TplCustomerBrokerEntityVO ) m_data;
    
    tplCustomerBrokerEntityVO.setCustNbr( custNbr_ );
    tplCustomerBrokerEntityVO.setBkrCnpjNbr( tplBrokerEntity_.getData().getBkrCnpjNbr() );
    tplCustomerBrokerEntityVO.setBkrCustNbr( bkrCustNbr_ );
    tplCustomerBrokerEntityVO.setBkrNameText( tplBrokerEntity_.getData().getBkrNameText() );
    tplCustomerBrokerEntityVO.setBkrAddrText( tplBrokerEntity_.getData().getBkrAddrText() );
    

  }

}