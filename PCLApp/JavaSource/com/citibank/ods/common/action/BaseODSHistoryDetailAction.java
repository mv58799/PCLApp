package com.citibank.ods.common.action;

import com.citibank.ods.common.functionality.ODSHistoryDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.common.action;
 * @version 1.0
 * @author bruno.zanetti,Mar 9, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class BaseODSHistoryDetailAction extends BaseODSAction
{

  protected String executeAction( BaseFncVO fncVO_, String mode_ )
  {
    String forwardKey = getScreenName() + C_SPLITTER_CHAR + mode_;
    return forwardKey;
  }

  protected String showAction( BaseFncVO fncVO_, String mode_ )
  {
    ODSHistoryDetailFnc fnc = ( ODSHistoryDetailFnc ) getFuncionality();
    fncVO_.clearErrors();
    fncVO_.clearMessages();

    if ( mode_.equals( C_MODE_CONSULT ) )
    {
      fnc.loadForConsult( fncVO_ );
    }

    String forwardKey = "";
    if ( fncVO_.hasErrors() )
    {
      forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
    }
    else
    {
      forwardKey = getScreenName() + C_SPLITTER_CHAR + mode_;
    }
    return forwardKey;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#otherAction(com.citibank.ods.common.functionality.valueobject.BaseFncVO,
   *      java.lang.String)
   */
  protected String specificActions( BaseFncVO fncVO_, String invokePath_ )
  {
    return extraActions( fncVO_, invokePath_ );
  }

}