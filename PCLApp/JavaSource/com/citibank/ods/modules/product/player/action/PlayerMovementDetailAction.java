package com.citibank.ods.modules.product.player.action;

import com.citibank.ods.common.action.BaseODSMovementDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.player.functionality.PlayerDetailFnc;
import com.citibank.ods.modules.product.player.functionality.PlayerMovementDetailFnc;
import com.citibank.ods.modules.product.player.functionality.valueobject.PlayerMovementDetailFncVO;

/**
 * @author ACAdmin
 *
 */

public class PlayerMovementDetailAction extends BaseODSMovementDetailAction
{
	
  /*
   * Parte do nome do m�dulo ou a��o
   */
  private static final String C_SCREEN_NAME = "Player.PlayerMovementDetail";
  
  private static final String C_DELETE_ISSUE = "DeleteIssue";

  private static final String C_INSERT_ISSUE = "InsertIssue";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return PlayerMovementDetailFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new PlayerMovementDetailFnc();
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
	String[] splittedInvokePath = invokePath_.split( "\\." );
	PlayerMovementDetailFnc detailFnc = ( PlayerMovementDetailFnc ) getFuncionality();
    String forwardKey = "";
    //Verifica se o m�todo chamado foi InsertIssue
    if ( splittedInvokePath[ 2 ].equals( C_MODE_UPDATE ) && splittedInvokePath[ 3 ].equals( C_INSERT_ISSUE )) {
		 //Insere Mnem�nico
    	 detailFnc.insertIssue( fncVO_ );
		 if ( !fncVO_.hasErrors() ){
			 forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
		 }else{
			 forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
		 }
    }
    //Verifica se o m�todo chamado foi DeleteIssue
    if ( splittedInvokePath[ 2 ].equals( C_MODE_UPDATE ) && splittedInvokePath[ 3 ].equals( C_DELETE_ISSUE ) ){ 
    	 //Exclui Mnem�nico
    	 detailFnc.deleteIssue(fncVO_);
         if ( !fncVO_.hasErrors() ){
           forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
         }
         else{
           forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
         }
    }    
    return forwardKey;
  }
}
