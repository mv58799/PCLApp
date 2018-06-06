package com.citibank.ods.modules.client.mrdocprvt.action;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessages;

import com.citibank.ods.common.action.BaseODSDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.user.User;
import com.citibank.ods.modules.client.mrdocprvt.functionality.MrDocPrvtDetailFnc;
import com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject.MrDocPrvtDetailFncVO;

/**
 * @author m.nakamura
 * 
 * Action da tela de detalhe de memória de risco.
 */
public class MrDocPrvtDetailAction extends BaseODSDetailAction
{
  // Parte do nome do módulo ou ação
  private static final String C_SCREEN_NAME = "MrDocPrvt.MrDocPrvtDetail";

  private static final String C_DELETE_DOMAIN = "DeleteDomain";

  private static final String C_INSERT_DOMAIN = "InsertDomain";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return MrDocPrvtDetailFncVO.class.getName();
  }

  /**
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new MrDocPrvtDetailFnc();
  }

  /**
   * @see com.citibank.ods.common.action.BaseODSAction#getScreenName()
   */
  protected String getScreenName()
  {
    return C_SCREEN_NAME;
  }

  /**
   * @see com.citibank.ods.common.action.BaseODSAction#extraActions(com.citibank.ods.common.functionality.valueobject.BaseFncVO,
   *      java.lang.String)
   */
  protected String extraActions( BaseFncVO fncVO_, String invokePath_ )
  {
    String[] splittedInvokePath = invokePath_.split( "\\." );
    MrDocPrvtDetailFnc detailFnc = ( MrDocPrvtDetailFnc ) getFuncionality();
    String forwardKey = "";

    detailFnc.setInsertDeleteContactCustAction( true );

    if ( splittedInvokePath[ 2 ].equals( C_MODE_UPDATE )
         && ( splittedInvokePath[ 3 ].equals( C_DELETE_DOMAIN ) ) )

    {
      fncVO_.clearErrors();
      fncVO_.clearMessages();

      detailFnc.deleteContactCust( fncVO_ );

      if ( !fncVO_.hasErrors() )
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
      }
      else
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
      }
    }

    if ( splittedInvokePath[ 2 ].equals( C_MODE_INSERT )
         && ( splittedInvokePath[ 3 ].equals( C_DELETE_DOMAIN ) ) )

    {
      fncVO_.clearErrors();
      fncVO_.clearMessages();

      detailFnc.deleteContactCust( fncVO_ );

      if ( !fncVO_.hasErrors() )
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
      }
      else
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
      }
    }

    if ( splittedInvokePath[ 2 ].equals( C_MODE_UPDATE )
         && ( splittedInvokePath[ 3 ].equals( C_INSERT_DOMAIN ) ) )

    {

      fncVO_.clearErrors();
      fncVO_.clearMessages();

      detailFnc.insertContactCust( fncVO_ );

      if ( !fncVO_.hasErrors() )
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
      }
      else
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
      }
    }
    if ( splittedInvokePath[ 2 ].equals( C_MODE_INSERT )
         && ( splittedInvokePath[ 3 ].equals( C_INSERT_DOMAIN ) ) )

    {

      fncVO_.clearErrors();
      fncVO_.clearMessages();

      detailFnc.insertContactCust( fncVO_ );

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

  /**
   * Action do Botão Buscar.
   *  
   */
  protected String searchActions( BaseFncVO fncVO_, String invokePath_ )
  {
    MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVO_;
    String[] splittedInvokePath = invokePath_.split( "\\." );
    fncVO.setFromSearch( true );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];
    fncVO.setClickedSearch( nextPage );

    return getScreenName();
  }

  /**
   * @see com.citibank.ods.common.action.BaseAction#execute(java.lang.String,
   *      org.apache.struts.action.ActionForm,
   *      org.apache.struts.action.ActionErrors,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO,
   *      com.citibank.ods.common.user.User,
   *      org.apache.struts.action.ActionErrors,
   *      org.apache.struts.action.ActionMessages)
   */
  public ActionResult execute( String invokePath_, ActionForm actionForm_,
                              ActionErrors actionErrors_, BaseFncVO fncVo_,
                              User loggedUser_, ActionErrors previousErrors_,
                              ActionMessages previousMessages_ )
  {
    MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVo_;

    if ( fncVO != null )
    {
      fncVO.clearErrors();
      fncVO.clearMessages();
    }

    MrDocPrvtDetailFnc mrDocPrvtDetailFnc = ( MrDocPrvtDetailFnc ) getFuncionality();

    if ( fncVo_ == null )
    {
      fncVO = ( MrDocPrvtDetailFncVO ) mrDocPrvtDetailFnc.createFncVO();
    }

    if ( !fncVO.getClickedSearch().equals( "" ) )
    {
      mrDocPrvtDetailFnc.setInsertDeleteContactCustAction( true );
    } 

    return super.execute( invokePath_, actionForm_, actionErrors_, fncVO,
                          loggedUser_, previousErrors_, previousMessages_,null );
  }
}