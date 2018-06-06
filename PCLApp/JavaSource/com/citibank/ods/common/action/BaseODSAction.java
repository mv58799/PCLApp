package com.citibank.ods.common.action;

import java.util.Iterator;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.logger.ApplicationLogger;
import com.citibank.ods.common.logger.LoggerFactory;
import com.citibank.ods.common.user.User;

//
//�2002-2007 Accenture. All rights reserved. 
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

public abstract class BaseODSAction extends BaseAction
{
  private String className = this.getClass().getSimpleName();
  /**
   * C_SPLITTER_CHAR - Caracter de divis�o de partes do invokepath_
   */
  public static final String C_SPLITTER_CHAR = ".";

  /**
   * C_ACTION_BACK - Nome l�gico da acao de voltar � tela anterior
   */
  public static final String C_ACTION_BACK = "Back";

  /**
   * C_ACTION_EXECUTE - Nome l�gico da acao de executar
   */
  public static final String C_ACTION_EXECUTE = "Execute";

  /**
   * C_ACTION_REPROVE - Nome l�gico da acao de reprovar
   */
  public static final String C_ACTION_REPROVE = "Reprove";

  /**
   * C_ACTION_APPROVE - Nome l�gico da acao de aprovar
   */
  public static final String C_ACTION_APPROVE = "Approve";

  /**
   * C_ACTION_SHOW - Nome l�gico da acao de exibir tela
   */
  public static final String C_ACTION_SHOW = "Show";

  /**
   * C_ACTION_PREPARED_SEARCH - Nome l�gico da acao de buscar valores em outra
   * tela
   */
  public static final String C_ACTION_PREPARED_SEARCH = "PreparedSearch";

  /**
   * C_MODE_APPROVE - Nome l�gico do modo de aprovar
   */
  public static final String C_MODE_APPROVAL = "Approval";

  /**
   * C_MODE_CONSULT - Nome l�gico do modo de detail
   */
  public static final String C_MODE_CONSULT = "Consult";

  /**
   * C_MODE_DELETE - Nome l�gico do modo de delete
   */
  public static final String C_MODE_DELETE = "Delete";

  /**
   * C_MODE_INSERT - Nome l�gico do modo de inserir
   */
  public static final String C_MODE_INSERT = "Insert";

  /**
   * C_MODE_LIST - Nome l�gico do modo de consulta em lista
   */
  public static final String C_MODE_LIST = "List";

  /**
   * C_MODE_UPDATE - Nome l�gico do modo de atualizar
   */
  public static final String C_MODE_UPDATE = "Update";

  /**
   * A��o de remover um registro em um grid
   */
  public static final String C_ACTION_DELETE_DOMAIN = "DeleteDomain";
  public static final String C_ACTION_DELETE_ISSUE = "DeleteIssue";

  /**
   * A��o de inserir um registro em um grid
   */
  public static final String C_ACTION_INSERT_DOMAIN = "InsertDomain";

  public static final String C_ACTION_INSERT_DOCTRANSFER = "InsertDocTransfer";

  public static final String C_ACTION_INSERT_IPDOCCALLBACK = "InsertCallbacks";

  public static final String C_ACTION_LIST_BROKER = "List";

  public static final String C_ACTION_LIST_BKR_PORTF_MGMT = "ListBkrPortfMgmt";
  
  public static final String C_MODE_INSERT_EMISSOR = "InsertEmissor";  
  
  public static final String C_MODE_DELETE_EMISSOR = "DeleteEmissor";

  public static final String C_ACTION_INSERT_ISSUE = "InsertIssue";

  /**
   * @see com.citibank.ods.common.action.BaseAction#execute(java.lang.String,
   *      org.apache.struts.action.ActionForm,
   *      org.apache.struts.action.ActionErrors,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO, User)
   */
  public ActionResult execute( String invokePath_, ActionForm actionForm_,
                              ActionErrors actionErrors_, BaseFncVO fncVo_,
                              User loggedUser_, ActionErrors previousErrors_,
                              ActionMessages previousMessages_,String ordenar )
  {
	  LoggerFactory.initialize();
		
	//Recupera inst�ncia de Aplication
	ApplicationLogger applicationLogger = LoggerFactory.getApplicationLoggerInstance();
 
    if ( invokePath_.equals( C_LOGOFF_FOWARD_KEY ) )
    {
      ActionResult actionResult = buildLogoffActionResult();
    }
    ActionResult actionResult = null;
    String forwardKey = "";
    BaseFncVO fncVO = ( BaseFncVO ) fncVo_;
    BaseForm form = ( BaseForm ) actionForm_;

    // Instancia funcionalidade correta
    BaseFnc odsFnc = getFuncionality();

    if ( fncVo_ == null )
    {
      fncVO = ( BaseFncVO ) odsFnc.createFncVO();
    }

    fncVO.setLoggedUser( loggedUser_ );

    // Tratamento das mensagens de erro
    String[] splittedInvokePath = null;
    splittedInvokePath = invokePath_.split( "\\." );

    String invokePathMode = splittedInvokePath[ 2 ];
    String invokePathAction = splittedInvokePath[ 3 ];
    
    applicationLogger.debug(className + " invoke:"+invokePath_);
    
    boolean hasDataFormErrors = actionErrors_ != null
                                && !actionErrors_.isEmpty();

    if ( invokePathAction.equals( C_ACTION_EXECUTE )
         || invokePathAction.equals( C_ACTION_SHOW )
         || invokePathAction.equals( C_ACTION_INSERT_DOMAIN )
         || invokePathAction.equals( C_MODE_INSERT_EMISSOR )
         || invokePathAction.equals( C_MODE_DELETE_EMISSOR )
         || invokePathAction.equals( C_ACTION_INSERT_ISSUE )
         || invokePathAction.equals( C_ACTION_DELETE_ISSUE )         
         || invokePathAction.equals( C_ACTION_INSERT_DOCTRANSFER )
         || invokePathAction.equals( C_ACTION_INSERT_IPDOCCALLBACK ) )
    {
      fncVO.clearErrors();
      fncVO.clearMessages();

    }

    if ( invokePathAction.equals( C_ACTION_SHOW ) )
    {
      if ( previousErrors_ != null )
      {
        fncVO.getErrors().add( previousErrors_ );
      }

      if ( previousMessages_ != null )
      {
        fncVO.getMessages().add( previousMessages_ );
      }
    }

    if ( !hasDataFormErrors )
    {
    	
      // Executa a atualiza��o do FncVO
      odsFnc.updateFncVOFromForm( form, fncVO );
      
      //Limpa informa��o de ordena��o de queries
	  form.setOrderBy("");

      
      if(ordenar == null){
	        //Verificando a a��o a ser executada:
			//M�todos de Execu��o
			if ( invokePathAction.equals( C_ACTION_EXECUTE ) )
			{
			  forwardKey = this.executeAction( fncVO, invokePathMode );
			}
			// M�todos de Show
			else if ( invokePathAction.equals( C_ACTION_SHOW ) )
			{
			  forwardKey = this.showAction( fncVO, invokePathMode );
			}
			// M�todos de Search
			else if ( invokePathMode.equals( C_ACTION_PREPARED_SEARCH ) )
			{
			  //Vari�vel de controle para limpar os crit�rios de pesquisa.
			  fncVO.setExecutedList( false );
			  forwardKey = this.searchActions( fncVO, invokePath_ );
			}
			else
			{
			  forwardKey = this.specificActions( fncVO, invokePath_ );
			}
      }
      else{
		forwardKey = getScreenName() + C_SPLITTER_CHAR + invokePathMode;
      }
      
		
      // Executa a atualiza��o
      odsFnc.updateFormFromFncVO( form, fncVO );
    }
    else
    {
      forwardKey = getScreenName() + C_SPLITTER_CHAR + invokePathMode;

      if ( invokePathAction.equals( C_ACTION_EXECUTE )
           || invokePathAction.equals( C_ACTION_INSERT_DOMAIN )
           || invokePathAction.equals( C_ACTION_INSERT_ISSUE )
           || invokePathAction.equals( C_ACTION_DELETE_ISSUE )
           || invokePathAction.equals( C_ACTION_INSERT_DOCTRANSFER )
           || invokePathAction.equals( C_ACTION_INSERT_IPDOCCALLBACK ) )
      {
        // Quando o modo � de Execute e tem erros, devemos copiar
        // cada msg do ActionErrors para o FncVO, para que estes erros
        // sejam mostrados para os usu�rios.
        for ( Iterator ite = actionErrors_.get(); ite.hasNext(); )
        {
          ActionMessage message = ( ActionMessage ) ite.next();
          fncVO.addError( message.getKey(), message.getValues() );
        }
      }
      else if ( invokePathAction.equals( C_ACTION_SHOW ) )
      {
        // Quando o modo � de Show e tem erros, devemos desconsiderar
        // estes erros para o usu�rio. Lembrando que os erros
        // mencionados
        // s�o aqueles obtidos na valida��o do Form, e n�o na valida��o
        // de neg�cio feito pelo Fnc. Portanto, precisamos apenas
        // atualizar
        // o Form com os dados que est�o no FncVO.

        // Executa a atualiza��o
        odsFnc.updateFormFromFncVO( form, fncVO );
      }
    }

    actionResult = buildActionResult( fncVO, forwardKey );

    return actionResult;
  }

  protected abstract BaseFnc getFuncionality();

  protected abstract String getScreenName();

  protected abstract String executeAction( BaseFncVO fncVO, String mode_ );

  protected abstract String showAction( BaseFncVO fncVO_, String mode_ );

  protected abstract String specificActions( BaseFncVO fncVO_,
                                            String invokePath_ );

  protected abstract String extraActions( BaseFncVO fncVO_, String invokePath_ );

  /**
   * 
   * "M�todo para tratamento de evento de in�cio de busca de dados em outra
   * tela"
   * 
   * @param fncVO_ - Objeto contendo o estado atual da funcionalidade acionada
   * @param invokePath_ - ActionMapping chamado para iniciar o evento
   * @return forwardKey utilizada para redirecionar o fluxo de navega��o
   */
  protected String searchActions( BaseFncVO fncVO_, String invokePath_ )
  {
    return "";
  };
}