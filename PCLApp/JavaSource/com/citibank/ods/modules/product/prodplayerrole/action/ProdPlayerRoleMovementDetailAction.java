package com.citibank.ods.modules.product.prodplayerrole.action;

import com.citibank.ods.common.action.BaseODSMovementDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.prodplayerrole.functionality.ProdPlayerRoleMovementDetailFnc;
import com.citibank.ods.modules.product.prodplayerrole.functionality.valueobject.ProdPlayerRoleMovementDetailFncVO;

/**
 * @author atilio.l.araujo
 *
 */
public class ProdPlayerRoleMovementDetailAction extends BaseODSMovementDetailAction
{
  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "ProdPlayerRole.ProdPlayerRoleMovementDetail";
  
  private static final String C_DELETE_DOMAIN = "DeleteDomain";

  private static final String C_INSERT_DOMAIN = "InsertDomain";
  
  private static final String C_LIST_PRODUCT = "ListProduct";
  
  private static final String C_CLEAR_PAGE = "ClearPage";
  

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return ProdPlayerRoleMovementDetailFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * 
   */
  protected BaseFnc getFuncionality()
  {
    return new ProdPlayerRoleMovementDetailFnc();
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
    ProdPlayerRoleMovementDetailFnc detailFnc = ( ProdPlayerRoleMovementDetailFnc ) getFuncionality();
    String forwardKey = "";

    if ( splittedInvokePath[ 2 ].equals( C_MODE_UPDATE )
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
    else if ( splittedInvokePath[ 2 ].equals( C_MODE_UPDATE )
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
    
    else if ( splittedInvokePath[ 2 ].equals( C_MODE_UPDATE )
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
    
    else if ( splittedInvokePath[ 2 ].equals( C_MODE_UPDATE )
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

    return forwardKey;
  }
  
  protected String searchActions( BaseFncVO fncVO_, String invokePath_ )
  {
    ProdPlayerRoleMovementDetailFncVO fncVO = ( ProdPlayerRoleMovementDetailFncVO ) fncVO_;
    fncVO.setFromSearch(true);
    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];
    fncVO.setClickedSearch( nextPage );

    return getScreenName();
    
  }
}
