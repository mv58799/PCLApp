package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplCustomerBrokerMovEntity;

/**
 * @author Hamilton Matos
 */

public interface TplCustomerBrokerMovDAO extends BaseTplCustomerBrokerDAO
{

  /**
   * Métodos Abstratos
   *  
   */

  public TplCustomerBrokerMovEntity insert(
                                           TplCustomerBrokerMovEntity tplCustomerBrokerMovEntity_ )
                                                                                                   throws UnexpectedException;

  public TplCustomerBrokerMovEntity update(
                                           TplCustomerBrokerMovEntity tplCustomerBrokerMovEntity_ )
                                                                                                   throws UnexpectedException;

  public TplCustomerBrokerMovEntity delete(
                                           TplCustomerBrokerMovEntity tplCustomerBrokerMovEntity_ )
                                                                                                   throws UnexpectedException;

  public boolean exists( TplCustomerBrokerMovEntity tplCustomerBrokerMovEntity_ );

  public DataSet list( String custNbrSrc_, String custFullNameTextSrc_,
                      String bkrCnpjSrc_, String bkrNameText_,
                      String lastUpdUserIdSrc_ );

}