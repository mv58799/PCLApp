package com.citibank.ods.modules.product.prodplayerrole.action;

import com.citibank.ods.common.action.BaseODSDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.prodplayerrole.functionality.ProdPlayerRoleDetailFnc;
import com.citibank.ods.modules.product.prodplayerrole.functionality.valueobject.ProdPlayerRoleDetailFncVO;

/**
 * @author atilio.l.araujo
 *
 */

public class ProdPlayerRoleDetailAction extends BaseODSDetailAction
{
  private static final String C_DELETE_DOMAIN = "DeleteDomain";

  private static final String C_INSERT_DOMAIN = "InsertDomain";
  
  private static final String C_ASSOCIATION = "Association";
  
  private static final String C_ACTION_BACK_PLAYER = "Player";
  
  private static final String C_LIST_PRODUCT = "ListProduct";
  
  private static final String C_CLEAR_PAGE = "ClearPage";

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "ProdPlayerRole.ProdPlayerRoleDetail";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return ProdPlayerRoleDetailFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new ProdPlayerRoleDetailFnc();
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
    ProdPlayerRoleDetailFnc detailFnc = ( ProdPlayerRoleDetailFnc ) getFuncionality();
    String forwardKey = "";

    if ( splittedInvokePath[ 2 ].equals( C_MODE_INSERT )
         && ( splittedInvokePath[ 3 ].equals( C_INSERT_DOMAIN ) ) )
    {
      detailFnc.insertDomain( fncVO_ );

      if ( !fncVO_.hasErrors() )
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
      }
      else
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
      }
    }
    else if ( splittedInvokePath[ 2 ].equals( C_MODE_INSERT )
        && ( splittedInvokePath[ 3 ].equals( C_DELETE_DOMAIN ) ) )
    {
      detailFnc.deleteDomain( fncVO_ );

      if ( !fncVO_.hasErrors() )
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
      }
      else
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
      }
      
    }
    else if ( splittedInvokePath[ 2 ].equals( C_ASSOCIATION ))
    {
      detailFnc.existsInMovement(fncVO_);

      if ( fncVO_.hasErrors() )
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK_PLAYER;
      }
      else
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + C_MODE_INSERT;
      }
    }
    else if ( splittedInvokePath[ 2 ].equals( C_MODE_INSERT )
        && ( splittedInvokePath[ 3 ].equals( C_LIST_PRODUCT ) ) )
    {
      detailFnc.listProduct( fncVO_ );

      if ( !fncVO_.hasErrors() )
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
      }
      else
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
      }
      
    }
    
    else if ( splittedInvokePath[ 2 ].equals( C_MODE_INSERT )
        && ( splittedInvokePath[ 3 ].equals( C_CLEAR_PAGE ) ) )
    {
      detailFnc.clearPage( fncVO_ );

      if ( !fncVO_.hasErrors() )
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
      }
      else
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
      }
      
    }
    
    return forwardKey;
  }
  
  protected String searchActions( BaseFncVO fncVO_, String invokePath_ )
  {
    ProdPlayerRoleDetailFncVO fncVO = ( ProdPlayerRoleDetailFncVO ) fncVO_;
    fncVO.setFromSearch(true);
    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];
    fncVO.setClickedSearch( nextPage );

    return getScreenName();
    
  }
}