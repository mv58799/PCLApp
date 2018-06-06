package com.citibank.ods.modules.product.broker.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.broker.functionality.valueobject.BrokerListFncVO;
import com.citibank.ods.persistence.pl.dao.TplBrokerDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author Hamilton Matos
 * 
 *  
 */
public class BrokerListFnc extends BaseBrokerListFnc implements ODSListFnc
{

  /**
   * Retorna instancia do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new BrokerListFncVO();
  }

  /**
   * Recupera uma lista de Corretoras com os critérios especificados
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    BrokerListFncVO brokerListFncVO = ( BrokerListFncVO ) fncVO_;

    TplBrokerDAO tplBrokerDAO = ODSDAOFactory.getInstance().getTplBrokerDAO();
    DataSet results = tplBrokerDAO.list( brokerListFncVO.getBkrCnpjNbrSrc(),
                                         brokerListFncVO.getBkrNameTextSrc() );

    brokerListFncVO.setResults( results );

    if ( results.size() > 0 )
    {
      brokerListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      brokerListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

  }

  /**
   * Carregameto inicial - consulta em lista
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    BrokerListFncVO brokerListFncVO = ( BrokerListFncVO ) fncVO_;
    brokerListFncVO.setResults( null );
  }

  /**
   * Validação da consulta em lista
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {

  }
}