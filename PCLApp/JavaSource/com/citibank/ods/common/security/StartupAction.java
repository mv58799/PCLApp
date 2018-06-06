 //
//�2002-2007 Accenture. All rights reserved. 
//
/**
 * Action de inicializa��o do sistema que carrega fun��es cadastradas no SG no
 * profile do usu�rio. Esta classe realiza integra��o com o SG atrav�s da
 * factory SecurityGatewayFactory.
 * 
 * @see com.citibank.ods.common.security;
 * @version 1.0
 * @author marcelo.s.oliveira,June 1 , 2007
 *  
 */
package com.citibank.ods.common.security;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.citibank.latam.sgway.util.RecordList;
import com.citibank.newcpb.security.SecurityUtil;
import com.citibank.ods.common.configuration.Configuration;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.logger.ApplicationLogger;
import com.citibank.ods.common.logger.LoggerFactory;
import com.citibank.ods.common.security.connector.SecurityGatewayFactory;
import com.citibank.ods.common.security.connector.SecurityGatewayInterface;
import com.citibank.ods.common.user.User;

public class StartupAction extends Action
{

  public final ActionForward execute( ActionMapping actionMapping_,
									 ActionForm actionForm_,
									 HttpServletRequest request_,
									 HttpServletResponse response_ )
																	throws Exception
  {
	MokAuthenticationForm mokActionForm = ( MokAuthenticationForm ) actionForm_;
	String isMock = Configuration.getInstance().getValue( "isMock" );
	String systemID = Configuration.getInstance().getValue( "systemID" );
	String userID = null;
	String ipAddress = request_.getRemoteAddr();
	String sessionSpecs = null;
	String firstName = null;
	String lastName = null;
	String actionForward = "";

	// Inicializa factory de Login
	LoggerFactory.initialize();

	// Recupera inst�ncia de Application
	ApplicationLogger applicationLogger = LoggerFactory.getApplicationLoggerInstance();

	if ( "true".equals( isMock ) )
	{
	  sessionSpecs = request_.getSession().getId();
	  userID = mokActionForm.getm_username();
	}
	else
	{
	  sessionSpecs = request_.getHeader( "sm_serversessionspec" );
	  userID = request_.getHeader( "sm_user" ).trim();
	}

	// Cria nova inst�ncia de usu�rio
	User user = new User();

	// Carrega dados do ambiente para o usu�rio
	setUserData( userID, ipAddress, sessionSpecs, user );

	// Inicializa flag de permiss�o para acesso ao sistema
	boolean canAccessSystemVar = false;

	// Resgata inst�ncia de Security Gatewaty
	SecurityGatewayInterface legacyConnector = SecurityGatewayFactory.getSecurityGatewayLegacyConnector();

	// Mensagem de verifica��o de acesso ao sistema escrita no arquivo de log.
	// (LogManager em modo DEBUG)
	applicationLogger.debug( "Verificando se usu�rio pode acessar o sistema (m�todo canAccessSystem): [usu�rio - "
							 + user.getUserID()
							 + "][IP - "
							 + user.getIpAddress()
							 + "][SessionSpecs - "
							 + user.getSessionSpecs()
							 + "][System ID - "
							 + Integer.parseInt( systemID ) + "]" );

	// Verifica se usu�rio tem permiss�o para acessar o sistema atribuindo flag
	// de permiss�o
	canAccessSystemVar = legacyConnector.canAccessSystem(
														  user.getUserID(),
														  user.getIpAddress(),
														  user.getSessionSpecs(),
														  Integer.parseInt( systemID ) );

	// Atribui nome e sobrenome para modo de simula��o
	if ( "true".equals( isMock ) )
	{
	  firstName = "Teste";
	  lastName = "ODS";
	}
	else
	{
	  // Resgata nome e sobrenome do usu�rio
	  RecordList userBasicProfileRecordList = legacyConnector.getUserBasicProfile(
																				   user.getUserID(),
																				   user.getIpAddress(),
																				   user.getSessionSpecs() );

	  for ( int i = 0; i < userBasicProfileRecordList.getRecordCount(); i++ )
	  {
		firstName = ( String ) userBasicProfileRecordList.getString( i,
																	 "FIRST_NAME" );
		lastName = ( String ) userBasicProfileRecordList.getString( i,
																	"LAST_NAME" );
	  }
	}

	// Atribui FIRST_NAME e LAST_NAME resgatado do security gateway
	user.setFirstName( firstName );
	user.setLastName( lastName );

	try
	{
	  // Se usu�rio puder acessar o sistema
	  if ( canAccessSystemVar )
	  {
		// Define redirecionamento para a primeira tela do sistema
		actionForward = Configuration.getInstance().getValue( "firstFoward" );

		// Carrega fun��es permitidas para o usu�rio
		ArrayList functions = loadUserFunctions( mokActionForm, isMock,
												 systemID, applicationLogger,
												 user, legacyConnector );

		// Atribui a lista de fun��es ao usu�rio
		user.setFunctions( functions );
		
		SecurityUtil securityUtil = new SecurityUtil();
		user = securityUtil.initUserNewCPB(applicationLogger, user, systemID, isMock);

		// Guarda inst�ncia de usu�rio criado na se��o
		request_.getSession().setAttribute( "user", user );
	  }

	  // Se usu�rio n�o puder acessar o sistema � realizado um redirecionamento
	  // para p�gina de acesso negado
	  else
	  {
		actionForward = "CannotAcessSystem";
	  }
	}
	catch ( Exception e )
	{
	  applicationLogger.error("ERRO STATUP:" );
	  applicationLogger.error( e.getLocalizedMessage() );
	  e.printStackTrace();
	  throw new UnexpectedException( "Erro ao acessar SG" );
	}
	return actionMapping_.findForward( actionForward );
  }

  /**
   * "Carrega fun��es permitidas para o usu�rio"
   */
  private ArrayList loadUserFunctions( MokAuthenticationForm mokActionForm_,
									  String isMock_, String systemID_,
									  ApplicationLogger applicationLogger_,
									  User user_,
									  SecurityGatewayInterface legacyConnector_ )
																				 throws Exception
  {
	ArrayList functions = new ArrayList();
	RecordList modulesRecordList = null;
	RecordList functionsRecordList = null;
	RecordList modulesAndFunctionsRecordList = null;

	// Se est� em modo de simula��o
	if ( "true".equals( isMock_ ) )
	{
	  // Popula lista de fun��es para usu�rio. (Modo de simula��o)
	  populateMockFunctionList( mokActionForm_, systemID_, applicationLogger_,
								user_, legacyConnector_, functions );
	}

	// Se n�o est� em modo de simula��o
	else
	{
	  // Popula lista de fun��es para usu�rio
	  populateFunctionList( systemID_, applicationLogger_, user_,
							legacyConnector_, functions );
	}
	return functions;
  }

  /**
   * "Carrega lista de fun��es para usu�rio"
   */
  private void populateFunctionList( String systemID_,
									ApplicationLogger applicationLogger_,
									User user_,
									SecurityGatewayInterface legacyConnector_,
									ArrayList functions_ ) throws Exception
  {
	RecordList modulesRecordList;
	// Carrega os m�dulos definidos para o usu�rio
	modulesRecordList = legacyConnector_.getSystemModules(
														   user_.getUserID(),
														   user_.getIpAddress(),
														   user_.getSessionSpecs(),
														   Integer.parseInt( systemID_ ) );

	// Adiciona as fun��es cadastradas para o usu�rio no ArrayList de
	// fun��es
	addModuleFunctions( systemID_, applicationLogger_, user_, legacyConnector_,
						functions_, modulesRecordList );
  }

  /**
   * "Carrega lista de fun��es para usu�rio. (Modo de simula��o)"
   */
  private void populateMockFunctionList(
										MokAuthenticationForm mokActionForm_,
										String systemID_,
										ApplicationLogger applicationLogger_,
										User user_,
										SecurityGatewayInterface legacyConnector_,
										ArrayList functions_ ) throws Exception
  {
	RecordList modulesAndFunctionsRecordList;
	StringTokenizer tokenizer;
	String groupName;

	// Resgata groupName do Form de Login Mock
	String groupNamesFromForm = mokActionForm_.getm_groupName();

	// Carrega os m�dulos e fun��es definidos para o usu�rio
	modulesAndFunctionsRecordList = legacyConnector_.getSystemModulesAndFunctions(
																				   user_.getUserID(),
																				   user_.getIpAddress(),
																				   user_.getSessionSpecs(),
																				   Integer.parseInt( systemID_ ) );

	// Adiciona as fun��es cadastradas para o usu�rio no ArrayList de
	// fun��es
	addMockModuleFunctions( applicationLogger_, user_, functions_,
							modulesAndFunctionsRecordList, groupNamesFromForm );
  }

  /**
   * "Carrega dados do ambiente para o usu�rio"
   */
  private void setUserData( String userID_, String ipAddress_,
						   String sessionSpecs_, User user_ )
  {
	// Atribui ID do usu�rio na nova inst�ncia
	user_.setUserID( userID_ );

	// Atribui IP do usu�rio na nova inst�ncia
	user_.setIpAddress( ipAddress_ );

	// Atribui SessionSpecs na nova inst�ncia
	user_.setSessionSpecs( sessionSpecs_ );
  }

  /**
   * "Adiciona as fun��es cadastradas para o usu�rio no ArrayList de fun��es"
   */
  private void addModuleFunctions( String systemID_,
								  ApplicationLogger applicationLogger_,
								  User user_, SecurityGatewayInterface lc_,
								  ArrayList functions_,
								  RecordList modulesRecordList_ )
																 throws Exception
  {
	RecordList functionsRecordList;
	applicationLogger_.debug( "***URIs permitidas para usu�rio "
							  + user_.getUserID() + ": " );
	// Para cada m�dulo do RecordList modulesRecordList_
	for ( int i = 0; i < modulesRecordList_.getRecordCount(); i++ )
	{
	  // Recupera fun��o para cada elemento de modulesRecordList_
	  functionsRecordList = lc_.getSystemModuleFunctions(
														  user_.getUserID(),
														  user_.getIpAddress(),
														  user_.getSessionSpecs(),
														  Integer.parseInt( systemID_ ),
														  modulesRecordList_.getInt(
																					 i,
																					 0 ) );

	  // Para cada fun��o em functionsRecordList
	  for ( int j = 0; j < functionsRecordList.getRecordCount(); j++ )
	  {
		// Imprime as fun��es no arquivo de log. (LogManager em modo DEBUG)
//		  applicationLogger_.debug( "FUNCTION_NAME:"  + functionsRecordList.getString( j, "FUNCTION_NAME" ) + "" );

		// Verifica se fun��o j� est� presente na lista de fun��s do usu�rio
		if ( !functions_.contains( functionsRecordList.getString( j,
																  "FUNCTION_NAME" ) ) )
		{
		  // Adiciona entrada no ArrayList de fun��es
		  functions_.add( functionsRecordList.getString( j, "FUNCTION_NAME" ) );
		}

	  }
	}
  }

  /**
   * "Adiciona as fun��es cadastradas para o usu�rio no ArrayList de fun��es.
   * (Modo de simula��o)"
   */
  private void addMockModuleFunctions( ApplicationLogger applicationLogger_,
									  User user_, ArrayList functions_,
									  RecordList modulesRecordList_,
									  String groupNamesFromForm_ )
  {
	StringTokenizer tokenizer;
	String groupName;

	// Para cada perfil contido em uma linha do arquivo access.xml
	for ( StringTokenizer groupTokenizer = new StringTokenizer(
																groupNamesFromForm_,
																"-" ); groupTokenizer.hasMoreTokens(); )
	{
	  // Resgata perfil a partir da tela de login
	  groupName = groupTokenizer.nextToken();

	  applicationLogger_.debug( "MOCK - URIs permitidas para usu�rio "
								+ user_.getUserID() + ": " );

	  // Para cada registro trazido do arquivo access.xml
	  for ( int i = 0; i < modulesRecordList_.getRecordCount(); i++ )
	  {

		applicationLogger_.debug( "	"
								  + modulesRecordList_.getString( i,
																  "FUNCTION_NAME" ) );

		// Resgata perfil (GROUP_NAME) do xml
		tokenizer = new StringTokenizer(
										 modulesRecordList_.getString( i,
																	   "GROUP_NAME" ),
										 "-" );
		// Verifica se fun��o j� est� presente na lista de fun��s do usu�rio
		if ( !functions_.contains( modulesRecordList_.getString( i,
																 "FUNCTION_NAME" ) ) )
		{
		  // Enquanto houver mais perfis lidos do xml
		  while ( tokenizer.hasMoreTokens() )
		  {
			// Se nome do perfil lido do formul�rio for igual ao nome do perfil
			// lido do xml
			if ( groupName.equals( tokenizer.nextToken() ) )
			{
			  // Adiciona entrada no ArrayList de fun��es
			  functions_.add( modulesRecordList_.getString( i, "FUNCTION_NAME" ) );
			  break;
			}
		  }
		}
	  }

	}
  }
}

