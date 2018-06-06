package com.citibank.ods.modules.product.player.action;

import com.citibank.ods.common.action.BaseODSDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.player.functionality.PlayerDetailFnc;
import com.citibank.ods.modules.product.player.functionality.valueobject.PlayerDetailFncVO;

/**
 * @author ACAdmin
 *
 */

public class PlayerDetailAction extends BaseODSDetailAction
{
  
  private static final String C_DELETE_ISSUE = "DeleteIssue";

  private static final String C_INSERT_ISSUE = "InsertIssue";
  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "Player.PlayerDetail";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return PlayerDetailFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc	getFuncionality()
  {
    return new PlayerDetailFnc();
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
  protected String extraActions( BaseFncVO fncVO_, String invokePath_ ){
	String[] splittedInvokePath = invokePath_.split( "\\." );
    PlayerDetailFnc detailFnc = ( PlayerDetailFnc ) getFuncionality();
    String forwardKey = "";
    //Verifica se o método chamado foi InsertIssue
    if ( splittedInvokePath[ 2 ].equals( C_MODE_INSERT ) && splittedInvokePath[ 3 ].equals( C_INSERT_ISSUE )) {
		 //Insere Mnemônico
    	 detailFnc.insertIssue( fncVO_ );
		 if ( !fncVO_.hasErrors() ){
			 forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
		 }else{
			 forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
		 }
    }
    
    if ( splittedInvokePath[ 2 ].equals( C_MODE_UPDATE ) && splittedInvokePath[ 3 ].equals( C_INSERT_ISSUE )) {
		 //Insere Mnemônico
   	 	 detailFnc.insertIssue( fncVO_ );
		 if ( !fncVO_.hasErrors() ){
			 forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
		 }else{
			 forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
		 }
   }
    //Verifica se o método chamado foi DeleteIssue
    if ( splittedInvokePath[ 2 ].equals( C_MODE_INSERT ) && splittedInvokePath[ 3 ].equals( C_DELETE_ISSUE ) ){ 
    	 //Exclui Mnemônico
    	 detailFnc.deleteIssue(fncVO_);
         if ( !fncVO_.hasErrors() ){
           forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
         }
         else{
           forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
         }
    }
    
    if ( splittedInvokePath[ 2 ].equals( C_MODE_UPDATE ) && splittedInvokePath[ 3 ].equals( C_DELETE_ISSUE ) ){ 
   	 	//Exclui Mnemônico
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
