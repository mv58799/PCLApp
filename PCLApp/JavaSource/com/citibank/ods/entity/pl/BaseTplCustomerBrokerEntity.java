package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplCustomerBrokerEntityVO;
/**
 * Classe que instancia a entidade correspondente a tabela :
 * BaseTplCustomerBroker
 * @author Hamilton Matos
 */

public class BaseTplCustomerBrokerEntity extends BaseODSEntity
{
  /**
   * Constante do tamanho do campo CNPJ do broker.
   */
  public static final int C_BKR_CNPJ_NBR_SIZE = 18;

  /**
   * Constante do tamanho do campo Nome do broker.
   */
  public static final int C_BKR_NAME_TEXT_SIZE = 60;

  /**
   * Constante do tamanho do campo Número do cliente.
   */
  public static final int C_CUST_NBR_SIZE = 11;

  /**
   * Constante do tamanho do campo Nome do cliente.
   */
  public static final int C_CUST_FULL_NAME_TEXT_SIZE = 60;

  protected BaseTplCustomerBrokerEntityVO m_data = null;

  public BaseTplCustomerBrokerEntityVO getData()
  {
    return m_data;
  }

  protected BaseTplBrokerEntity m_baseTplBrokerEntity;
  
  
  /**
   * @return Returns baseTplBrokerEntity.
   */
  public BaseTplBrokerEntity getBaseTplBrokerEntity()
  {
    return m_baseTplBrokerEntity;
  }
  /**
   * @param baseTplBrokerEntity_ Field baseTplBrokerEntity to be setted.
   */
  public void setBaseTplBrokerEntity( BaseTplBrokerEntity baseTplBrokerEntity_ )
  {
    m_baseTplBrokerEntity = baseTplBrokerEntity_;
  }
}