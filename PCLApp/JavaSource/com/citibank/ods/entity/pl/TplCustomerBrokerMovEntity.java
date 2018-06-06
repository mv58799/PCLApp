package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplCustomerBrokerMovEntityVO;
/**
 * 
 * @see package
 *      com.citibank.ods.modules.client.customerbroker.functionality.valueobject;
 * @author Hamilton Matos,Jul 26, 2007
 * 
 * </PRE>
 */

public class TplCustomerBrokerMovEntity extends BaseTplCustomerBrokerEntity
{

  /**
   * Construtor padrão - sem argumentos
   */
  public TplCustomerBrokerMovEntity()
  {
    m_data = new TplCustomerBrokerMovEntityVO();
  }

  /**
   * Construtor - Carrega os atributos de movimento com os atributos da current
   */
  public TplCustomerBrokerMovEntity(
                                    TplCustomerBrokerEntity tplCustomerBrokerEntity_,
                                    String opernCode_ )
  {
    m_data = new TplCustomerBrokerMovEntityVO();
    TplCustomerBrokerMovEntityVO tplCustomerBrokerMovEntityVO = ( TplCustomerBrokerMovEntityVO ) m_data;

    tplCustomerBrokerMovEntityVO.setCustNbr( tplCustomerBrokerEntity_.getData().getCustNbr() );
    tplCustomerBrokerMovEntityVO.setBkrCnpjNbr( tplCustomerBrokerEntity_.getData().getBkrCnpjNbr() );
    tplCustomerBrokerMovEntityVO.setBkrCustNbr( tplCustomerBrokerEntity_.getData().getBkrCustNbr() );
    tplCustomerBrokerMovEntityVO.setLastUpdUserId( tplCustomerBrokerEntity_.getData().getLastUpdUserId() );
    tplCustomerBrokerMovEntityVO.setLastUpdDate( tplCustomerBrokerEntity_.getData().getLastUpdDate() );
    tplCustomerBrokerMovEntityVO.setOpernCode( opernCode_ );

  }
}