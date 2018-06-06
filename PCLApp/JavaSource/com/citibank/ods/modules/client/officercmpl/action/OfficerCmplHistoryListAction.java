/*
 * Created on Mar 2, 2007
 *
 */
package com.citibank.ods.modules.client.officercmpl.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.officercmpl.functionality.OfficerCmplHistoryListFnc;
import com.citibank.ods.modules.client.officercmpl.functionality.valueobject.OfficerCmplHistoryListFncVO;
/**
 * @author bruno.zanetti
 *  
 */
public class OfficerCmplHistoryListAction extends BaseODSListAction
{
  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "OfficerCmpl.OfficerCmplHistoryList";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return OfficerCmplHistoryListFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new OfficerCmplHistoryListFnc();
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
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    return null;
  }

  /**
   * Realiza a pesquisa
   */
  protected String searchActions( BaseFncVO fncVO_, String invokePath_ )
  {
    OfficerCmplHistoryListFncVO fncVO = ( OfficerCmplHistoryListFncVO ) fncVO_;
    fncVO.setFromSearch( true );
    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];
    fncVO.setClickedSearch( nextPage );

    return getScreenName();

  }
}