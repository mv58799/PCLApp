package com.citibank.ods.modules.client.bkrportfmgmt.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.bkrportfmgmt.functionality.BkrPortfMgmtMovementListFnc;
import com.citibank.ods.modules.client.bkrportfmgmt.functionality.valueobject.BkrPortfMgmtMovementListFncVO;

/**
 * @author Hamilton Matos
 *  
 */

public class BkrPortfMgmtMovementListAction extends BaseODSListAction
{
  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "BkrPortfMgmt.BkrPortfMgmtMovementList";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return BkrPortfMgmtMovementListFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new BkrPortfMgmtMovementListFnc();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getScreenName()
   */
  protected String getScreenName()
  {
    return C_SCREEN_NAME;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#extraActions(com.citibank.ods.common.functionality.valueobject.BaseFncVO,
   *      java.lang.String)
   */
  protected String extraActions( BaseFncVO fncVO_, String invokePath_ )
  {
    return null;
  }
}