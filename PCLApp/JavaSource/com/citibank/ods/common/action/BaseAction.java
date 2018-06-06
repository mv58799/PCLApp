package com.citibank.ods.common.action;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.ModuleUtils;

import com.citibank.ods.common.configuration.Configuration;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.factory.JNDIFactory;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.logger.ApplicationLogger;
import com.citibank.ods.common.logger.LoggerFactory;
import com.citibank.ods.common.transaction.ActionManagedUserTransaction;
import com.citibank.ods.common.transaction.ManagedUserTransaction;
import com.citibank.ods.common.user.User;
import com.citibank.ods.common.util.DefStaticVars;

/**
 * Classe Action que � a classe base de todas as classes Action implementadas
 * pela aplica��o.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public abstract class BaseAction extends Action
{
  /**
   * Nested class que representa o par de valores conclu�dos pela execu��o de
   * uma classe Action.
   * 
   * @version: 1.00
   * @author Luciano Marubayashi, Dec 21, 2006
   */
  protected class ActionResult
  {
    /**
     * Construtor privado. Esta classe somente poder� ser instanciada pela
     * classe BaseAction
     */
    private ActionResult()
    {
    }

    /**
     * m_fncVO - inst�ncia de uma FncVO obtida como resultado da execu��o de uma
     * Action
     */
    private BaseFncVO m_fncVO;

    /**
     * m_forwardKey - c�digo de pesquisa de forward obtito como resultado da
     * execu��o de uma Action
     */
    private String m_forwardKey;

    /**
     * m_logoff - flag representando logoff do usu�rio
     */
    private boolean m_logoff;

  }

  /**
   * C_USER_SESSION_ID - Nome da vari�vel de sess�o que armazena o usu�rio
   * logado.
   */
  public static final String C_USER_SESSION_ID = "user";

  /**
   * C_CONFIGURATION_USER_LOCALE_LANGUAGE_CODE_NAME - Nome da configura��o do
   * idioma que ser� utilizado para cria��o do locale de sess�o.
   */
  private static final String C_CONFIGURATION_USER_LOCALE_LANGUAGE_CODE_NAME = "locale.language";

  /**
   * C_CONFIGURATION_USER_LOCALE_COUNTRY_CODE_NAME - Nome da configura��o de
   * pa�s que ser� utilizado para cria��o do locale de sess�o.
   */
  private static final String C_CONFIGURATION_USER_LOCALE_COUNTRY_CODE_NAME = "locale.country";

  /**
   * C_CONFIGURATION_USER_LOCALE_VARIANT_CODE_NAME - Nome da configura��o de
   * variant que ser� utilizado para cria��o do locale de sess�o
   */
  private static final String C_CONFIGURATION_USER_LOCALE_VARIANT_CODE_NAME = "locale.variant";

  /**
   * C_CONFIGURATION_USER_TRANSACTION_JNDI_NAME_FULL_PATH - Nome da configura��o
   * do caminho completo JNDI do UserTransaction
   */
  private static final String C_CONFIGURATION_USER_TRANSACTION_JNDI_NAME_FULL_PATH = "usertransaction.jndi.full.path";

  /**
   * C_LOGOFF_FOWARD_KEY- Foward para tela de LogOff do caminho completo JNDI do
   * UserTransaction
   */
  protected static String C_LOGOFF_FOWARD_KEY = "ODSExit";

  /*
   * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
   *      org.apache.struts.action.ActionForm,
   *      javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse)
   */
  public final ActionForward execute( ActionMapping actionMapping_,
                                     ActionForm actionForm_,
                                     HttpServletRequest request_,
                                     HttpServletResponse response_ )
                                                                    throws Exception
  {
    String invokePath = null;
    int invokePathSize = 0;
    ActionResult actionResult = null;
    ActionForward actionForward = null;
    ActionErrors actionErrors = null;
    ActionErrors previousErrors = null;
    ActionMessages previousMessages = null;
    BaseFncVO fncVO_ = null;
    User loggedUser = null;
    String doSerialize = null;

    //Inicializa factory de Login
    LoggerFactory.initialize();

    //Recupera inst�ncia de Aplication
    ApplicationLogger applicationLogger = LoggerFactory.getApplicationLoggerInstance();

    //protected Logger m_logger = null;
    //Logger m_logger = Logger.getLogger("BaseAction");

    //Obt�m a sess�o do usu�rio.
    HttpSession session = request_.getSession();

    //Manipula o objeto Locale utilizado pela sess�o do usu�rio.
    handleLocale( request_ );

    //adiciona ao Form os dados necess�rios para formata��o e parsing de
    // valores:
    //	- Locale da sess�o e MessageResources da aplica��o.
    //	- MessageResources da aplica��o.
    handleForm( actionForm_, request_ );

    //obt�m o valor da URL solicitada
    invokePath = actionMapping_.getPath().substring( 1 );

    //obtem a quantidadde de caracteres correspondente a URL solicitada
    invokePathSize = invokePath.length();

    //Verifica se a URL deve executar a��o limpeza atrev�s do sufixo "Clear"
    if ( invokePath.substring( invokePathSize - 5 ).equals( "Clear" ) )
    {
      //Invoca m�todo para limpeza de FNCVO�s da mem�ria
      clearVOandFormsfromSession( request_, actionForm_ );
      //Remove sufixo que indica limpeza "Clear"
      invokePath = invokePath.substring( 0, invokePathSize - 5 );
    }

    try
    {
      actionErrors = actionForm_.validate( actionMapping_, request_ );
    }
    catch ( Exception e_ )
    {
      applicationLogger.error( "Erro inesperado: " + e_.getMessage(), e_ );
      actionForward = actionMapping_.findForward( "MAPPINGERROR" );
      return actionForward;
    }

    try
    {
      // Recupera value objects da sess�o
      fncVO_ = ( BaseFncVO ) request_.getSession().getAttribute(
                                                                 getFncVOPublishName() );
      
      //Remove da sessao VO especificado
      if (fncVO_ != null){
    	  if (!StringUtils.isEmpty(fncVO_.getNameCleanSession())){
    		  clearVOFromSession(request_, fncVO_.getNameCleanSession());
    	  }
      }

      // Recupera usu�rio logado na sess�o
      loggedUser = ( User ) request_.getSession().getAttribute(
                                                                C_USER_SESSION_ID );

      // Recupera erros enviados pela request
      previousErrors = ( ActionErrors ) request_.getAttribute( "org.apache.struts.action.ERROR" );

      // Recupera mensagens enviadas pela request
      previousMessages = ( ActionMessages ) request_.getAttribute( "org.apache.struts.action.ACTION_MESSAGE" );

      //Esse par�metro identifica os requests atrav�s do DisplayTags
      String ordenar = request_.getParameter("ordenar");
	  
	  //Executa a a��o      
	  actionResult = execute( invokePath, actionForm_, actionErrors, fncVO_,
							  loggedUser, previousErrors, previousMessages,ordenar );
	  
	  
      
      //Verifica se a��o corresponde a um LogOff
      if ( actionResult.m_logoff )
      {
        //Remove usu�rio da se��o.
        session.removeAttribute( "user" );
        
        //Verifica em qual ambiente est� logado e direciona para URL correta de logout do sitminder
        //Ambiente de Desenvolvimento
        if ( actionResult.m_forwardKey.equals( "ODSExitDEV" ) )
        {
          response_.sendRedirect( "https://brazilweb.br.citicorp.com/siteminderagent/forms/logout.html" );
        }
        //Ambiente de SIT
        else if ( actionResult.m_forwardKey.equals( "ODSExitSIT" ) )
        {
          response_.sendRedirect( "https://brazilweb-desv.br.citicorp.com/siteminderagent/forms/logout.html" );
        }
        //Ambiente de UAT
        else if ( actionResult.m_forwardKey.equals( "ODSExitUAT" ) )
        {
          response_.sendRedirect( "https://cgtiweb-uat.br.citicorp.com/siteminderagent/forms/logout.html" );
        }

        actionResult.m_forwardKey = null;

      }
      else
      {
        // Coloca value object de volta na sess�o
        request_.getSession().setAttribute( getFncVOPublishName(),
                                            actionResult.m_fncVO );

        // Publica as mensagens de erro e aviso, atrav�s das API do struts para
        // publica��o destes elementos.
        if ( actionResult.m_fncVO != null )
        {
          saveMessages( request_, cloneMessage( actionResult.m_fncVO ) );
          saveErrors( request_,
                      ( ActionMessages ) cloneErrors( actionResult.m_fncVO ) );

        }
      }
    }
    catch ( Throwable t_ )
    {
      applicationLogger.error( "Erro inesperado: " + t_.getMessage(), t_ );
      actionForward = actionMapping_.findForward( "SYSTEMERROR" );
      return actionForward;
    }

    // Constru��o do ActionForward que ser� utilizado pelo struts para
    // Loga menssagem de erro e pilha em arquivo definido na configura��o do
    // log(log4j.properties)
    // apresenta��o do JSP escolhido.
    try
    {
      actionForward = actionMapping_.findForward( actionResult.m_forwardKey );

    }
    catch ( Exception e_ )
    {
      applicationLogger.error( "Erro inesperado: " + e_.getMessage(), e_ );
      actionForward = actionMapping_.findForward( "FORWARDERROR" );
    }

    //Resgatando flag de ativa��o de serializa��o dos objetos em se��o
    doSerialize = Configuration.getInstance().getValue( "doSerialize" );

    return actionForward;

  }  
  

  /**
   * 
   * Constr�i o par de valores que definem a resposta a execu��o de uma Action.
   * 
   * @param fncVO_ - FncVO que ser� salvo em sess�o.
   * @param forwardKey_ - Valor de forward que ser� utilizado para constru��o do
   *          ActionForward para resposta � requisi��o.
   * @return ActionResult contemplando os valores informado como par�metro.
   */
  public final ActionResult buildActionResult( BaseFncVO fncVO_,
                                              String forwardKey_ )
  {
    ActionResult actionResult = new ActionResult();
    actionResult.m_forwardKey = forwardKey_;
    actionResult.m_fncVO = fncVO_;
    actionResult.m_logoff = false;
    return actionResult;

  }

  /**
   * 
   * Constr�i uma actionresult de LogOff
   * 
   * 
   * @return ActionResult contemplando os valores de logoff
   */
  public final ActionResult buildLogoffActionResult()
  {
    ActionResult actionResult = new ActionResult();
    actionResult.m_forwardKey = C_LOGOFF_FOWARD_KEY
                                + DefStaticVars.getInstance().getCurrentEnvironment;
    actionResult.m_fncVO = null;
    actionResult.m_logoff = true;
    return actionResult;
  }

  /**
   * 
   * Remove refer�ncia de Value Objects e Forms carregados na mem�ria.
   * 
   * @param request_ - HttpServletRequest de onde ser�o obtidos os objetos que
   *          estar�o sem se��o que v�o ser removidos.
   */
  public final void clearVOandFormsfromSession( HttpServletRequest request_,
                                               ActionForm form_ )
  {
    Enumeration attributeNames = request_.getSession().getAttributeNames();
    while ( attributeNames.hasMoreElements() )
    {
      String nextSessionKey = attributeNames.nextElement().toString();
      if ( nextSessionKey.endsWith( "VO" ) )
      {
        request_.getSession().removeAttribute( nextSessionKey );
      }
      if ( nextSessionKey.endsWith( "Form" )
           && !form_.getClass().getName().endsWith( nextSessionKey ) )
      {
        request_.getSession().removeAttribute( nextSessionKey );
      }

    }
  }

  /**
   * 
   * Adiciona ao form, por refer�ncia, os objetos necess�rios ao processo de
   * forma��o e parsing de valores com m�scaras: - Locale da sess�o e
   * MessageResources da aplica��o. - MessageResources da aplica��o.
   * 
   * @param actionForm_ inst�ncia do Form onde os objetos Locale
   *          MessageResources ser�o adicionados.
   * @param request_ - HttpServletRequest de onde ser�o obtidos os objetos que
   *          ser�o adicionados ao Form.
   */
  private void handleForm( ActionForm actionForm_, HttpServletRequest request_ )
  {
    if ( actionForm_ instanceof BaseForm )
    {
      BaseForm baseForm = ( BaseForm ) actionForm_;
      Locale userLocale = getUserLocale( request_ );
      MessageResources messageResources = getMessageResources( request_ );
      baseForm.setCurrentLocale( userLocale );
      baseForm.setCurrentMessageResources( messageResources );
    }
  }

  /**
   * 
   * Obt�m o MessageResources utilizado pela aplica��o.
   * 
   * @param request_ HttpServletRequest de onde ser�o obtidos os dados para
   *          pesquisa do MessageResources da aplica��o
   * @return MessageResource da aplica��o.
   */
  private MessageResources getMessageResources( HttpServletRequest request_ )
  {
    MessageResources messageResources = ( MessageResources ) request_.getAttribute( Globals.MESSAGES_KEY );
    if ( messageResources == null )
    {
      HttpSession session = request_.getSession();
      ServletContext servletContext = session.getServletContext();
      ModuleConfig moduleConfig = ModuleUtils.getInstance().getModuleConfig(
                                                                             null,
                                                                             request_,
                                                                             servletContext );
      messageResources = ( MessageResources ) servletContext.getAttribute( Globals.MESSAGES_KEY
                                                                           + moduleConfig.getPrefix() );
      if ( messageResources == null )
      {
        messageResources = ( MessageResources ) servletContext.getAttribute( Globals.MESSAGES_KEY );
      }
    }
    return messageResources;
  }

  /**
   * 
   * Obt�m o Locale definido na sess�o do usu�rio.
   * 
   * @param request_ - HttpServletRequest de onde ser� obtido o Locale da
   *          sess�o.
   * @return Locale obtido da sess�o do usu�rio.
   */
  private Locale getUserLocale( HttpServletRequest request_ )
  {
    HttpSession session = request_.getSession();
    Locale userLocale = ( Locale ) session.getAttribute( Globals.LOCALE_KEY );
    return userLocale;
  }

  /**
   * 
   * Publica em sess�o um Locale parametrizado para a aplica��o. Caso n�o exista
   * uma parametriza��o de Locale para a aplica��o, ser� utilizado o Locale
   * default do request.
   * 
   * @param request_- HttpServletRequest de onde ser� obtido o Locale defult.
   */
  private void handleLocale( HttpServletRequest request_ )
  {
    HttpSession session = request_.getSession();
    Locale userLocale = ( Locale ) session.getAttribute( Globals.LOCALE_KEY );
    if ( userLocale == null )
    {
      userLocale = createConfiguredLocale();
      if ( userLocale == null )
      {
        userLocale = request_.getLocale();
      }
      session.setAttribute( Globals.LOCALE_KEY, userLocale );
    }
  }

  /**
   * 
   * Constr�i uma inst�ncia de Locale conforme parametriza��o existente para a
   * aplica��o (arquivo de configura��o da aplica��o definido pelo par�metro de
   * aplica��o application.configuration)
   * 
   * @return inst�ncia de Locale que contempla as caracter�sticas parametrizadas
   *         para a aplica��o ou a inst�ncia default de Locale do Request, caso
   *         n�o exista esta parametriza��o.
   */
  private Locale createConfiguredLocale()
  {
    Locale userLocale = null;

    //Obt�m a configura��o para languageCode
    String languageCode = Configuration.getInstance().getValue(
                                                                C_CONFIGURATION_USER_LOCALE_LANGUAGE_CODE_NAME );
    //Obt�m a configura��o para countryCode
    String countryCode = Configuration.getInstance().getValue(
                                                               C_CONFIGURATION_USER_LOCALE_COUNTRY_CODE_NAME );
    //Obt�m a configura��o para variantCode
    String variantCode = Configuration.getInstance().getValue(
                                                               C_CONFIGURATION_USER_LOCALE_VARIANT_CODE_NAME );

    if ( languageCode != null )
    {
      if ( countryCode != null )
      {
        if ( variantCode != null )
        {
          // cria inst�ncia de Locale utilizando todas as caracter�sticas
          // permitidas
          userLocale = new Locale( languageCode, countryCode, variantCode );
        }
        else
        {
          //cria inst�ncia de Locale sem definir a caracter�stica de Variant
          userLocale = new Locale( languageCode, countryCode );
        }
      }
      else
      {
        //cria inst�ncia de Locale sem definir as caracter�sticas de
        // CountryCode e Variant
        userLocale = new Locale( languageCode );
      }
    }

    return userLocale;
  }

  /**
   * 
   * Cria uma inst�ncia de ManagedUserTransaction (que encapsula
   * UserTransaction)
   * 
   * @return inst�ncia de ManagedUserTransaction
   */
  protected ManagedUserTransaction getUserTransaction()
  {
    String userTransactionJNDINameFullPath = Configuration.getInstance().getValue(
                                                                                   C_CONFIGURATION_USER_TRANSACTION_JNDI_NAME_FULL_PATH );
    if ( userTransactionJNDINameFullPath == null
         || "".equals( userTransactionJNDINameFullPath ) )
    {
      throw new UnexpectedException(
                                     C_CONFIGURATION_USER_TRANSACTION_JNDI_NAME_FULL_PATH
                                                                          + " key must be configured." );
    }
    UserTransaction userTransaction = ( UserTransaction ) JNDIFactory.createObject( userTransactionJNDINameFullPath );
    ManagedUserTransaction managedUserTransaction = new ActionManagedUserTransaction(
                                                                                      userTransaction );
    return managedUserTransaction;
  }

  /**
   * 
   * Inicia uma transa��o de UserTransaction.
   * 
   * @param managedUserTransaction_ - inst�ncia do objeto ManagedUserTransaction
   *          que faz o controle de transa��es.
   */
  protected void beginTransaction(
                                  ManagedUserTransaction managedUserTransaction_ )
  {
    if ( managedUserTransaction_ == null )
    {
      throw new UnexpectedException( "managedUserTransaction_ cannot be null." );
    }
    ActionManagedUserTransaction actionManagedUserTransaction = ( ActionManagedUserTransaction ) managedUserTransaction_;
    actionManagedUserTransaction.beginTransaction();
  }

  /**
   * 
   * Executa o commit de uma transa��o iniciada.
   * 
   * @param managedUserTransaction_ - inst�ncia do objeto ManagedUserTransaction
   *          que faz o controle de transa��es.
   */
  protected void commitTransaction(
                                   ManagedUserTransaction managedUserTransaction_ )
  {
    if ( managedUserTransaction_ == null )
    {
      throw new UnexpectedException( "managedUserTransaction_ cannot be null." );
    }
    ActionManagedUserTransaction actionManagedUserTransaction = ( ActionManagedUserTransaction ) managedUserTransaction_;
    actionManagedUserTransaction.commitTransaction();
  }

  /**
   * 
   * Executa o rollback de uma transa��o iniciada.
   * 
   * @param managedUserTransaction_ - inst�ncia do objeto ManagedUserTransaction
   *          que faz o controle de transa��es.
   */
  protected void rollbackTransaction(
                                     ManagedUserTransaction managedUserTransaction_ )
  {
    if ( managedUserTransaction_ == null )
    {
      throw new UnexpectedException( "managedUserTransaction_ cannot be null." );
    }
    ActionManagedUserTransaction actionManagedUserTransaction = ( ActionManagedUserTransaction ) managedUserTransaction_;
    actionManagedUserTransaction.rollbackTransaction();
  }

  /**
   * 
   * M�todo abstrato que ser� executado para o processamento de uma requisi��o
   * ao sistema. O objetivo deste m�todo � executar as fun��es de neg�cio
   * espec�ficas para a Action que implementa este m�todo. A partir do retorno
   * deste m�todo ser� identificado o JSP que ser� respons�vel por apresentar a
   * tela de resposta � requisi��o.
   * @param invokePath_ - URL que originou a requisi��o
   * @param actionForm_ - Inst�ncia de ActionForm que j� existia em sess�o ou
   *          foi instanciada automaticamente pelo Servlet do Struts, sendo que
   *          os dados do provenientes no request j� foram utilizados para
   *          atualizar o Action Form (somente os dados coincidentes entre o
   *          ActionForm e os dados de Request).
   * @param actionErrors_ - Lista de ActionError que foi obtida pela chamada ao
   *          validate() do ActionForm.
   * @param fncVo_ - Inst�ncia de FncVo que j� existia em session. A
   *          identifica��o desta inst�ncia na session � realizada atrav�s do
   *          nome definido em getFncVOPublishName(). Caso n�o exista uma
   *          inst�ncia publicada com este nome, ser� informado null.
   * @param loggedUser_ - Inst�ncia de User, que representa o usu�rio logado
   *          atualmente no sistema. Caso o usu�rio n�o estaja logado, ser�
   *          informado null.
   * @param previousErrors_ - Lista de erros recebidas na request, passadas pela
   *          tela anterior no fluxo de navega��o.
   * @param previousMessaages_ - Lista de mensagens recebidas na request,
   *          passadas pela tela anterior no fluxo de navega��o.
   * 
   * @return Par de valores que determinam a resposta � requisi��o.
   */
  public abstract ActionResult execute( String invokePath_,
                                       ActionForm actionForm_,
                                       ActionErrors actionErrors_,
                                       BaseFncVO fncVo_, User loggedUser_,
                                       ActionErrors previousErrors_,
                                       ActionMessages previousMessages_,String ordenar );

  /**
   * 
   * Determina o nome de publica��o do FncVO em session.
   * 
   * @return nome para publica��o do FncVO em session.
   */
  public abstract String getFncVOPublishName();
  
 

  public ActionMessages cloneMessage( BaseFncVO fncVO_ )
  {

    ActionMessages messages = new ActionMessages();

    if ( fncVO_.getMessages() != null )
    {
      Iterator actualMessages = fncVO_.getMessages().get();

      while ( actualMessages.hasNext() )
      {
        ActionMessage actualMsg = ( ActionMessage ) actualMessages.next();
        ActionMessage newMessage = new ActionMessage( actualMsg.getKey(),
                                                      actualMsg.getValues() );

        messages.add( newMessage.getKey(), newMessage );

      }
    }
    return messages;
  }

  public ActionMessages cloneErrors( BaseFncVO fncVO_ )
  {

    ActionErrors errors = new ActionErrors();

    if ( fncVO_.getErrors() != null )
    {
      Iterator actualErrors = fncVO_.getErrors().get();

      while ( actualErrors.hasNext() )
      {
        ActionMessage teste = ( ActionMessage ) actualErrors.next();
        ActionMessage newMessage = new ActionMessage( teste.getKey(),
                                                      teste.getValues() );

        errors.add( newMessage.getKey(), newMessage );

      }
    }
    return errors;
  }

  /**
   * 
   * Remove refer�ncia de Value Object carregado na mem�ria.
   * 
   * @param request_ - HttpServletRequest de onde ser�o obtidos os objetos que
   *          estar�o sem sess�o que v�o ser removidos.
   */
  private final void clearVOFromSession( HttpServletRequest request_,
                                               String nameVO )
  {
    request_.getSession().removeAttribute( nameVO );
  }
}