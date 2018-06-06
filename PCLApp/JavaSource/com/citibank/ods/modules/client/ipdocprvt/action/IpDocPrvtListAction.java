package com.citibank.ods.modules.client.ipdocprvt.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.modules.client.ipdocprvt.functionality.IpDocPrvtListFnc;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.IpDocPrvtListFncVO;
//import com.ibm.jvm.svcdump.Function;

/**
 * @author l.braga
 *  
 */

public class IpDocPrvtListAction extends BaseODSListAction
{

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "IpDocPrvt.IpDocPrvtList";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return IpDocPrvtListFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new IpDocPrvtListFnc();
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
    IpDocPrvtListFncVO fncVO = ( IpDocPrvtListFncVO ) fncVO_;
    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];
	   
    fncVO.setClickedSearch( nextPage );
    fncVO.setFromSearch(true);

    return getScreenName();
  }
}