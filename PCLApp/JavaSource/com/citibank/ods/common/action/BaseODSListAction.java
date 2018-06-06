package com.citibank.ods.common.action;

import com.citibank.ods.common.functionality.ODSListFnc;
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

public abstract class BaseODSListAction extends BaseODSAction
{

  protected String executeAction( BaseFncVO fncVO_, String mode_ )
  {
    ODSListFnc fnc = ( ODSListFnc ) getFuncionality();

    if ( mode_.equals( C_MODE_LIST ) )
    { 
	  if ( !fncVO_.isExecutedList() )
	  {
	    fnc.load( fncVO_ );
		fnc.list( fncVO_ );
	    //Seta true quando a consulta é executada
		fncVO_.setExecutedList( true );
	  }
	  else{
		fnc.list( fncVO_ );
	  }
	  
    }

    return getScreenName() + C_SPLITTER_CHAR + mode_;
  }

  protected String showAction( BaseFncVO fncVO_, String mode_ )
  {
    ODSListFnc fnc = ( ODSListFnc ) getFuncionality();

    if ( mode_.equals( C_MODE_LIST ) )
    {
      //Se a consulta já foi efetuada não chama o método load da tela que limpa
      // os campos e o resulSet
      if ( !fncVO_.isExecutedList() )
      {
        fnc.load( fncVO_ );
		fncVO_.setExecutedList( true );
      }
    }
    return getScreenName() + C_SPLITTER_CHAR + mode_;
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