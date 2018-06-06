package com.citibank.ods.modules.client.bkrportfmgmt.functionality;

import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.bkrportfmgmt.functionality.valueobject.BkrPortfMgmtListFncVO;

/**
 * @author hamilton matos
 *  
 */

public class BkrPortfMgmtListFnc extends BaseBkrPortfMgmtListFnc implements
    ODSListFnc
{

  /**
   * Retorna instancia do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new BkrPortfMgmtListFncVO();
  }

  /**
   * Recupera uma lista de corretoras com os critérios especificados
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Carregameto inicial - consulta em lista
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    BkrPortfMgmtListFncVO customerBrokerListFncVO = ( BkrPortfMgmtListFncVO ) fncVO_;
    customerBrokerListFncVO.setCustNbrSrc( null );
    customerBrokerListFncVO.setCustFullNameTextSrc( null );
    customerBrokerListFncVO.setBkrCnpjNbrSrc( null );
    customerBrokerListFncVO.setBkrNameTextSrc( null );
    customerBrokerListFncVO.setResults( null );
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