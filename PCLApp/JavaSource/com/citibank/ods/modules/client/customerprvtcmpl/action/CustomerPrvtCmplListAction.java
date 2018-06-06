/*
 * Created on Mar 2, 2007
 *
 */
package com.citibank.ods.modules.client.customerprvtcmpl.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.customerprvtcmpl.functionality.CustomerPrvtCmplListFnc;
import com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject.CustomerPrvtCmplListFncVO;

/**
 * @author gerson.a.rodrigues
 *  
 */

public class CustomerPrvtCmplListAction extends BaseODSListAction
{

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "CustomerPrvtCmpl.CustomerPrvtCmplList";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return CustomerPrvtCmplListFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new CustomerPrvtCmplListFnc();
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

  protected String searchActions( BaseFncVO fncVO_, String invokePath_ )
  {
    CustomerPrvtCmplListFncVO fncVO = ( CustomerPrvtCmplListFncVO ) fncVO_;
    fncVO.setFromSearch( true );
    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];
    fncVO.setClickedSearch( nextPage );

    return getScreenName();
  };
}