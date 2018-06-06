package com.citibank.ods.common.action;

import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.transaction.ManagedUserTransaction;
import com.citibank.ods.modules.product.product.functionality.ProductDetailFnc;

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

public abstract class BaseODSDetailAction extends BaseODSAction
{

  protected String executeAction( BaseFncVO fncVO_, String mode_ )
  {
    ODSDetailFnc fnc = ( ODSDetailFnc ) getFuncionality();
    String forwardKey = "";
    ManagedUserTransaction transaction = getUserTransaction();

    beginTransaction( transaction );

    if ( mode_.equals( C_MODE_UPDATE ) )
    {
      fnc.update( fncVO_ );
    }
    else if ( mode_.equals( C_MODE_INSERT ) )
    {
      fnc.insert( fncVO_ );
	  
    }
    else if ( mode_.equals( C_MODE_DELETE ) )
    {
      fnc.delete( fncVO_ );
    }
    
    if ( !fncVO_.hasErrors() )
    {
      
	  
      commitTransaction( transaction );
      fncVO_.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
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

    ODSDetailFnc fnc = ( ODSDetailFnc ) getFuncionality();
    
    //Se o modo for diferente de insert limpa os erros e as mensagens.
    if(! mode_.equals( C_MODE_INSERT )){
      fncVO_.clearErrors();
      fncVO_.clearMessages();
    }
    
    if ( mode_.equals( C_MODE_CONSULT ) )
    {
      fnc.loadForConsult( fncVO_ );

    }
    else if ( mode_.equals( C_MODE_UPDATE ) )
    {
      fnc.loadForUpdate( fncVO_ );

    }
    else if ( mode_.equals( C_MODE_INSERT ) )
    {
      fnc.loadForInsert( fncVO_ );

    }
    else if ( mode_.equals( C_MODE_DELETE ) )
    {
      fnc.loadForDelete( fncVO_ );

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