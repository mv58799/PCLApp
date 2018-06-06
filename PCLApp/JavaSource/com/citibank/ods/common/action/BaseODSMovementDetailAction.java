package com.citibank.ods.common.action;

import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.transaction.ManagedUserTransaction;

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

public abstract class BaseODSMovementDetailAction extends BaseODSAction
{

  protected String executeAction( BaseFncVO fncVO_, String mode_ )
  {
    ODSMovementDetailFnc fnc = ( ODSMovementDetailFnc ) getFuncionality();
    String forwardKey = "";
    ManagedUserTransaction transaction = getUserTransaction();

    beginTransaction( transaction );

    if ( mode_.equals( C_MODE_UPDATE ) )
    {
      fnc.update( fncVO_ );
    }

    if ( !fncVO_.hasErrors() )
    {
      commitTransaction( transaction );
      fncVO_.addMessage(BaseODSFncVO.C_MESSAGE_SUCESS);
      forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
    }
    else
    {
      rollbackTransaction( transaction );
      forwardKey = getScreenName() + C_SPLITTER_CHAR + mode_;
    }

    return forwardKey;
  }

  protected String showAction( BaseFncVO fncVO_, String mode_ )
  {
    ODSMovementDetailFnc fnc = ( ODSMovementDetailFnc ) getFuncionality();
    fncVO_.clearErrors();
    fncVO_.clearMessages();

    if ( mode_.equals( C_MODE_UPDATE ) )
    {
      fnc.loadForUpdate( fncVO_ );
    }
    else if ( mode_.equals( C_MODE_APPROVAL ) )
    {
      fnc.loadForApprove( fncVO_ );
    }
    else if ( mode_.equals( C_MODE_CONSULT ) )
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
    String[] splittedInvokePath = invokePath_.split( "\\." );
    ODSMovementDetailFnc fnc = ( ODSMovementDetailFnc ) getFuncionality();
    String forwardKey = "";
    ManagedUserTransaction transaction = getUserTransaction();

    beginTransaction( transaction );

    if ( splittedInvokePath[ 2 ].equals( C_MODE_APPROVAL )
         && splittedInvokePath[ 3 ].equals( C_ACTION_REPROVE ) )
    {
      fnc.reprove( fncVO_ );

      if ( !fncVO_.hasErrors() )
      {
        commitTransaction( transaction );
        fncVO_.addMessage(BaseODSFncVO.C_MESSAGE_SUCESS);
        forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
      }
      else
      {
        rollbackTransaction( transaction );
        forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
      }
    }
    else if ( splittedInvokePath[ 2 ].equals( C_MODE_APPROVAL )
              && splittedInvokePath[ 3 ].equals( C_ACTION_APPROVE ) )
    {
      fnc.approve( fncVO_ );

      if ( !fncVO_.hasErrors() )
      {
        commitTransaction( transaction );
        fncVO_.addMessage(BaseODSFncVO.C_MESSAGE_SUCESS);
        forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
      }
      else
      {
        rollbackTransaction( transaction );
        forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
      }
    }
    else
    {
      forwardKey = extraActions( fncVO_, invokePath_ );
    }
    return forwardKey;
  }
}