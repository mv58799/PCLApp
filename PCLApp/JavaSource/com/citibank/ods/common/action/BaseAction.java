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
 * Classe Action que é a classe base de todas as classes Action implementadas
 * pela aplicação.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public abstract class BaseAction extends Action
{
  /**
   * Nested class que representa o par de valores concluídos pela execução de
   * uma classe Action.
   * 
   * @version: 1.00
   * @author Luciano Marubayashi, Dec 21, 2006
   */
  protected class ActionResult
  {
    /**
     * Construtor privado. Esta classe somente poderá ser instanciada pela
     * classe BaseAction
     */
    private ActionResult()
    {
    }

    /**
     * m_fncVO - instância de uma FncVO obtida como resultado da execução de uma
     * Action
     */
    private BaseFncVO m_fncVO;

    /**
     * m_forwardKey - código de pesquisa de forward obtito como resultado da
     * execução de uma Action
     */
    private String m_forwardKey;

    /**
     * m_logoff - flag representando logoff do usuário
     */
    private boolean m_logoff;

  }

  /**
   * C_USER_SESSION_ID - Nome da variável de sessão que armazena o usuário
   * logado.
   */
  public static final String C_USER_SESSION_ID = "user";

  /**
   * C_CONFIGURATION_USER_LOCALE_LANGUAGE_CODE_NAME - Nome da configuração do
   * idioma que será utilizado para criação do locale de sessão.
   */
  private static final String C_CONFIGURATION_USER_LOCALE_LANGUAGE_CODE_NAME = "locale.language";

  /**
   * C_CONFIGURATION_USER_LOCALE_COUNTRY_CODE_NAME - Nome da configuração de
   * país que será utilizado para criação do locale de sessão.
   */
  private static final String C_CONFIGURATION_USER_LOCALE_COUNTRY_CODE_NAME = "locale.country";

  /**
   * C_CONFIGURATION_USER_LOCALE_VARIANT_CODE_NAME - Nome da configuração de
   * variant que será utilizado para criação do locale de sessão
   */
  private static final String C_CONFIGURATION_USER_LOCALE_VARIANT_CODE_NAME = "locale.variant";

  /**
   * C_CONFIGURATION_USER_TRANSACTION_JNDI_NAME_FULL_PATH - Nome da configuração
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

    //Recupera instância de Aplication
    ApplicationLogger applicationLogger = LoggerFactory.getApplicationLoggerInstance();

    //protected Logger m_logger = null;
    //Logger m_logger = Logger.getLogger("BaseAction");

    //Obtém a sessão do usuário.
    HttpSession session = request_.getSession();

    //Manipula o objeto Locale utilizado pela sessão do usuário.
    handleLocale( request_ );

    //adiciona ao Form os dados necessários para formatação e parsing de
    // valores:
    //	- Locale da sessão e MessageResources da aplicação.
    //	- MessageResources da aplicação.
    handleForm( actionForm_, request_ );

    //obtém o valor da URL solicitada
    invokePath = actionMapping_.getPath().substring( 1 );

    //obtem a quantidadde de caracteres correspondente a URL solicitada
    invokePathSize = invokePath.length();

    //Verifica se a URL deve executar ação limpeza atrevés do sufixo "Clear"
    if ( invokePath.substring( invokePathSize - 5 ).equals( "Clear" ) )
    {
      //Invoca método para limpeza de FNCVO´s da memória
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
      // Recupera value objects da sessão
      fncVO_ = ( BaseFncVO ) request_.getSession().getAttribute(
                                                                 getFncVOPublishName() );
      
      //Remove da sessao VO especificado
      if (fncVO_ != null){
    	  if (!StringUtils.isEmpty(fncVO_.getNameCleanSession())){
    		  clearVOFromSession(request_, fncVO_.getNameCleanSession());
    	  }
      }

      // Recupera usuário logado na sessão
      loggedUser = ( User ) request_.getSession().getAttribute(
                                                                C_USER_SESSION_ID );

      // Recupera erros enviados pela request
      previousErrors = ( ActionErrors ) request_.getAttribute( "org.apache.struts.action.ERROR" );

      // Recupera mensagens enviadas pela request
      previousMessages = ( ActionMessages ) request_.getAttribute( "org.apache.struts.action.ACTION_MESSAGE" );

      //Esse parâmetro identifica os requests através do DisplayTags
      String ordenar = request_.getParameter("ordenar");
	  
	  //Executa a ação      
	  actionResult = execute( invokePath, actionForm_, actionErrors, fncVO_,
							  loggedUser, previousErrors, previousMessages,ordenar );
	  
	  
      
      //Verifica se ação corresponde a um LogOff
      if ( actionResult.m_logoff )
      {
        //Remove usuário da seção.
        session.removeAttribute( "user" );
        
        //Verifica em qual ambiente está logado e direciona para URL correta de logout do sitminder
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
        // Coloca value object de volta na sessão
        request_.getSession().setAttribute( getFncVOPublishName(),
                                            actionResult.m_fncVO );

        // Publica as mensagens de erro e aviso, através das API do struts para
        // publicação destes elementos.
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

    // Construção do ActionForward que será utilizado pelo struts para
    // Loga menssagem de erro e pilha em arquivo definido na configuração do
    // log(log4j.properties)
    // apresentação do JSP escolhido.
    try
    {
      actionForward = actionMapping_.findForward( actionResult.m_forwardKey );

    }
    catch ( Exception e_ )
    {
      applicationLogger.error( "Erro inesperado: " + e_.getMessage(), e_ );
      actionForward = actionMapping_.findForward( "FORWARDERROR" );
    }

    //Resgatando flag de ativação de serialização dos objetos em seção
    doSerialize = Configuration.getInstance().getValue( "doSerialize" );

    return actionForward;

  }  
  

  /**
   * 
   * Constrói o par de valores que definem a resposta a execução de uma Action.
   * 
   * @param fncVO_ - FncVO que será salvo em sessão.
   * @param forwardKey_ - Valor de forward que será utilizado para construção do
   *          ActionForward para resposta à requisição.
   * @return ActionResult contemplando os valores informado como parâmetro.
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
   * Constrói uma actionresult de LogOff
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
   * Remove referência de Value Objects e Forms carregados na memória.
   * 
   * @param request_ - HttpServletRequest de onde serão obtidos os objetos que
   *          estarão sem seção que vão ser removidos.
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
   * Adiciona ao form, por referência, os objetos necessários ao processo de
   * formação e parsing de valores com máscaras: - Locale da sessão e
   * MessageResources da aplicação. - MessageResources da aplicação.
   * 
   * @param actionForm_ instância do Form onde os objetos Locale
   *          MessageResources serão adicionados.
   * @param request_ - HttpServletRequest de onde serão obtidos os objetos que
   *          serão adicionados ao Form.
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
   * Obtém o MessageResources utilizado pela aplicação.
   * 
   * @param request_ HttpServletRequest de onde serão obtidos os dados para
   *          pesquisa do MessageResources da aplicação
   * @return MessageResource da aplicação.
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
   * Obtém o Locale definido na sessão do usuário.
   * 
   * @param request_ - HttpServletRequest de onde será obtido o Locale da
   *          sessão.
   * @return Locale obtido da sessão do usuário.
   */
  private Locale getUserLocale( HttpServletRequest request_ )
  {
    HttpSession session = request_.getSession();
    Locale userLocale = ( Locale ) session.getAttribute( Globals.LOCALE_KEY );
    return userLocale;
  }

  /**
   * 
   * Publica em sessão um Locale parametrizado para a aplicação. Caso não exista
   * uma parametrização de Locale para a aplicação, será utilizado o Locale
   * default do request.
   * 
   * @param request_- HttpServletRequest de onde será obtido o Locale defult.
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
   * Constrói uma instância de Locale conforme parametrização existente para a
   * aplicação (arquivo de configuração da aplicação definido pelo parâmetro de
   * aplicação application.configuration)
   * 
   * @return instância de Locale que contempla as características parametrizadas
   *         para a aplicação ou a instância default de Locale do Request, caso
   *         não exista esta parametrização.
   */
  private Locale createConfiguredLocale()
  {
    Locale userLocale = null;

    //Obtém a configuração para languageCode
    String languageCode = Configuration.getInstance().getValue(
                                                                C_CONFIGURATION_USER_LOCALE_LANGUAGE_CODE_NAME );
    //Obtém a configuração para countryCode
    String countryCode = Configuration.getInstance().getValue(
                                                               C_CONFIGURATION_USER_LOCALE_COUNTRY_CODE_NAME );
    //Obtém a configuração para variantCode
    String variantCode = Configuration.getInstance().getValue(
                                                               C_CONFIGURATION_USER_LOCALE_VARIANT_CODE_NAME );

    if ( languageCode != null )
    {
      if ( countryCode != null )
      {
        if ( variantCode != null )
        {
          // cria instância de Locale utilizando todas as características
          // permitidas
          userLocale = new Locale( languageCode, countryCode, variantCode );
        }
        else
        {
          //cria instância de Locale sem definir a característica de Variant
          userLocale = new Locale( languageCode, countryCode );
        }
      }
      else
      {
        //cria instância de Locale sem definir as características de
        // CountryCode e Variant
        userLocale = new Locale( languageCode );
      }
    }

    return userLocale;
  }

  /**
   * 
   * Cria uma instância de ManagedUserTransaction (que encapsula
   * UserTransaction)
   * 
   * @return instância de ManagedUserTransaction
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
   * Inicia uma transação de UserTransaction.
   * 
   * @param managedUserTransaction_ - instância do objeto ManagedUserTransaction
   *          que faz o controle de transações.
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
   * Executa o commit de uma transação iniciada.
   * 
   * @param managedUserTransaction_ - instância do objeto ManagedUserTransaction
   *          que faz o controle de transações.
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
   * Executa o rollback de uma transação iniciada.
   * 
   * @param managedUserTransaction_ - instância do objeto ManagedUserTransaction
   *          que faz o controle de transações.
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
   * Método abstrato que será executado para o processamento de uma requisição
   * ao sistema. O objetivo deste método é executar as funções de negócio
   * específicas para a Action que implementa este método. A partir do retorno
   * deste método será identificado o JSP que será responsável por apresentar a
   * tela de resposta à requisição.
   * @param invokePath_ - URL que originou a requisição
   * @param actionForm_ - Instância de ActionForm que já existia em sessão ou
   *          foi instanciada automaticamente pelo Servlet do Struts, sendo que
   *          os dados do provenientes no request já foram utilizados para
   *          atualizar o Action Form (somente os dados coincidentes entre o
   *          ActionForm e os dados de Request).
   * @param actionErrors_ - Lista de ActionError que foi obtida pela chamada ao
   *          validate() do ActionForm.
   * @param fncVo_ - Instância de FncVo que já existia em session. A
   *          identificação desta instância na session é realizada através do
   *          nome definido em getFncVOPublishName(). Caso não exista uma
   *          instância publicada com este nome, será informado null.
   * @param loggedUser_ - Instância de User, que representa o usuário logado
   *          atualmente no sistema. Caso o usuário não estaja logado, será
   *          informado null.
   * @param previousErrors_ - Lista de erros recebidas na request, passadas pela
   *          tela anterior no fluxo de navegação.
   * @param previousMessaages_ - Lista de mensagens recebidas na request,
   *          passadas pela tela anterior no fluxo de navegação.
   * 
   * @return Par de valores que determinam a resposta à requisição.
   */
  public abstract ActionResult execute( String invokePath_,
                                       ActionForm actionForm_,
                                       ActionErrors actionErrors_,
                                       BaseFncVO fncVo_, User loggedUser_,
                                       ActionErrors previousErrors_,
                                       ActionMessages previousMessages_,String ordenar );

  /**
   * 
   * Determina o nome de publicação do FncVO em session.
   * 
   * @return nome para publicação do FncVO em session.
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
   * Remove referência de Value Object carregado na memória.
   * 
   * @param request_ - HttpServletRequest de onde serão obtidos os objetos que
   *          estarão sem sessão que vão ser removidos.
   */
  private final void clearVOFromSession( HttpServletRequest request_,
                                               String nameVO )
  {
    request_.getSession().removeAttribute( nameVO );
  }
}