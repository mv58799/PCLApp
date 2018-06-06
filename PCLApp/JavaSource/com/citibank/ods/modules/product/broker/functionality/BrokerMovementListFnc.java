package com.citibank.ods.modules.product.broker.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.broker.form.BrokerMovementListForm;
import com.citibank.ods.modules.product.broker.functionality.valueobject.BrokerMovementListFncVO;
import com.citibank.ods.persistence.pl.dao.TplBrokerMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author Hamilton Matos
 *  
 */
public class BrokerMovementListFnc extends BaseBrokerListFnc implements
    ODSListFnc
{

  /**
   * Retorna instancia do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new BrokerMovementListFncVO();
  }

  /**
   * Recupera uma lista de Corretoras com pendência de aprovação com os
   * critérios especificados
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    BrokerMovementListFncVO brokerMovementListFncVO = ( BrokerMovementListFncVO ) fncVO_;

    TplBrokerMovDAO tplBrokerMovDAO = ODSDAOFactory.getInstance().getTplBrokerMovDAO();
    DataSet results = tplBrokerMovDAO.list(
                                            brokerMovementListFncVO.getBkrCnpjNbrSrc(),
                                            brokerMovementListFncVO.getBkrNameTextSrc(),
                                            brokerMovementListFncVO.getLastUpdUserIdSrc() );

    brokerMovementListFncVO.setResults( results );

    if ( results.size() > 0 )
    {
      brokerMovementListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      brokerMovementListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }
  }

  /**
   * Carregameto inicial - consulta em lista
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    BrokerMovementListFncVO brokerMovementListFncVO = ( BrokerMovementListFncVO ) fncVO_;
    brokerMovementListFncVO.setBkrCnpjNbrSrc( null );
    brokerMovementListFncVO.setBkrNameTextSrc( null );
    brokerMovementListFncVO.setLastUpdUserIdSrc( null );
    brokerMovementListFncVO.setResults( null );
  }

  /**
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {

    super.updateFncVOFromForm( form_, fncVO_ );

    // Acertando os tipos
    BrokerMovementListFncVO brokerMovementListFncVO = ( BrokerMovementListFncVO ) fncVO_;
    BrokerMovementListForm brokerMovementListForm = ( BrokerMovementListForm ) form_;

    String lastUpdUserIdSrc = ( brokerMovementListForm.getLastUpdUserIdSrc() != null
                                && brokerMovementListForm.getLastUpdUserIdSrc().length() > 0
                                                                                            ? brokerMovementListForm.getLastUpdUserIdSrc()
                                                                                            : null );
    brokerMovementListFncVO.setLastUpdUserIdSrc( lastUpdUserIdSrc );

  }

  /**
   * Validação da consulta em lista
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    //
  }

}