package com.citibank.ods.common.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;

import com.citibank.ods.common.exception.UnexpectedException;

/**
 * 
 * Singleton que representa as configura��es definidas para a aplica��o. Este
 * singleton faz a leitura do arquivo de proporiedade definido como arquivo de
 * configura��o da aplica��o no momento de sua instancia��o. A instancia��o
 * deste singleton ocorre atrav�s do m�todo bootstrap.
 * 
 * Caso haja necessidade de o arquivo de configura��es seja lido novamente, em
 * fun��o de modifica��es realizadas no arquivo, o m�todo reload dever� ser
 * invocado. Neste caso, n�o ser� criada uma nova inst�ncia para o singleton: a
 * inst�ncia atual do Singleton ser� atualizada.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class Configuration
{

  /**
   * C_CONFIGURATION_TYPE_DAO_FACTORY - Tipo de configura��o que indica que �
   * uma configura��o de DAO Factory
   */
  private static final int C_CONFIGURATION_TYPE_DAO_FACTORY = 1;

  /**
   * C_CONFIGURATION_TYPE_UNKNOWN - Tipo de configura��o que indica que n�o �
   * uma configura��o que possui tratamento especial
   */
  private static final int C_CONFIGURATION_TYPE_UNKNOWN = -1;

  /**
   * Constantes para Tokens
   */
  private static final String C_TOKEN_DELIMITER = ".";

  private static final String C_TOKEN_PERSISTENCE = "persistence";

  private static final String C_TOKEN_DAO = "dao";

  private static final String C_TOKEN_FACTORY = "factory";

  private static final String C_TOKEN_IMPLEMENTATION = "implementation";

  private static Configuration ms_instance = null;

  /**
   * m_properties - tabela de nomes de configura��o e seus respectivos valores.
   */
  private Properties m_properties = null;

  /**
   * m_daoFactoriesKeys - lista dos nomes de configura��es que caracterizam uma
   * configura��o de DAO Factory.
   */
  private ArrayList m_daoFactoriesKeys = null;

  /**
   * Construtor privado, dispon�vel apenas para esta classe, pelo fato de
   * tratar-se de um Singleton.
   * 
   * @param configurationName_ - Nome do arquivo de propriedades que contempla a
   *          configura��o da aplica��o.
   */
  private Configuration( String configurationName_ )
  {
    reload( configurationName_ );
    organizeProperties();
  }

  /**
   * 
   * Inicializa este singleton, criando a inst�ncia deste singleton e lendo o
   * arquivo de propriedades informado. O m�todo bootstrap poder� ser invocado
   * somente uma vez durante o ciclo de vida do ClassLoader que carrega a
   * inst�ncia desta classe. Caso haja a necessidade de nova leitura do arquivo
   * de configura��es pelo fato deste arquivo ter sido modificado, o m�todo
   * reload dever� ser invocado.
   * 
   * @param configurationName_ - nome do arquivo de propriendades que possui as
   *          configura��es da aplica��o.
   */
  public static synchronized void bootstrap( String configurationName_ )
  {
    if ( ms_instance != null )
    {
      throw new UnexpectedException( "bootstrap cannot be executed twice" );
    }

    ms_instance = new Configuration( configurationName_ );
  }

  /**
   * 
   * Elimina os recursos utilizados pelo singleton.
   *  
   */
  public static synchronized void destroy()
  {
    ms_instance = null;
  }

  /*
   * @see java.lang.Object#finalize()
   */
  protected void finalize()
  {
    m_properties = null;
    m_daoFactoriesKeys = null;
  }

  /**
   * 
   * Obt�m um iterator da lista de nomes de configura��o que correspondem a
   * configura��es de DAO Factories.
   * 
   * @return Iterator da lista de nomes de configura��o que correspondem a
   *         configura��es de DAO Factories.
   */
  public Iterator getDAOFactoryKeys()
  {
    return m_daoFactoriesKeys.iterator();
  }

  /**
   * 
   * Organiza as configura��es definidas no arquivo de configura��es, criando
   * uma lista espec�fica de nomes de configura��o que representam apenas
   * configura��es de DAO Factories. A configura��es que s�o espec�ficas de DAO
   * Factories s�o caracterizadas por possu�rem em seu nome o sufixo
   * "persistence.dao.factory.implementation".
   *  
   */
  private void organizeProperties()
  {
    String configurationKey;
    StringTokenizer tokenizerConfigurationKey;
    m_daoFactoriesKeys = new ArrayList();
    Enumeration keys = m_properties.keys();
    int configurationKeyType;

    while ( keys.hasMoreElements() )
    {
      configurationKey = ( String ) keys.nextElement();
      tokenizerConfigurationKey = new StringTokenizer( configurationKey,
                                                       C_TOKEN_DELIMITER );
      configurationKeyType = parseConfiguration( tokenizerConfigurationKey );
      if ( configurationKeyType == C_CONFIGURATION_TYPE_DAO_FACTORY )
      {
        m_daoFactoriesKeys.add( configurationKey );
      }
    }
  }

  /**
   * 
   * Identifica se o nome de configura��o informado consiste em uma configura��o
   * espec�fica de DAO Factory. Inicialmente, procura pelo token "persistence".
   * Se encontrar este token, continua a avalia��o. Caso contr�rio, conclui que
   * a configura��o n�o � uma configura��o espec�fica.
   * 
   * @param tokenizerConfigurationKey_ - nome da configura��o "quebrado" por
   *          ".".
   * @return C_CONFIGURATION_TYPE_UNKNOWN (-1) - se o nome de configura��o n�o
   *         corresponde a uma configura��o espec�fica.
   *         C_CONFIGURATION_TYPE_DAO_FACTORY (1) - se o nome de configura��o
   *         corresponde a uma configura��o espec�fica de DAO FActory.
   */
  private int parseConfiguration( StringTokenizer tokenizerConfigurationKey_ )
  {
    String token;
    int configurationType = C_CONFIGURATION_TYPE_UNKNOWN;

    while ( tokenizerConfigurationKey_.hasMoreTokens() )
    {
      token = tokenizerConfigurationKey_.nextToken();
      if ( C_TOKEN_PERSISTENCE.equals( token ) )
      {
        configurationType = parseConfigurationPersistence( tokenizerConfigurationKey_ );
      }
    }
    return configurationType;
  }

  /**
   * 
   * Identifica se o nome de configura��o informado consiste em uma configura��o
   * espec�fica de DAO Factory. Este m�todo � invocado quando o token
   * "persistence" � encontrado no nome da configura��o. Caso o pr�ximo token
   * encontrado for o token "dao", continua a avalia��o. Caso contr�rio, conclui
   * que a configura��o n�o � uma configura��o espec�fica.
   * 
   * @param tokenizerConfigurationKey_ - nome da configura��o "quebrado" por
   *          ".".
   * @return C_CONFIGURATION_TYPE_UNKNOWN (-1) - se o nome de configura��o n�o
   *         corresponde a uma configura��o espec�fica.
   *         C_CONFIGURATION_TYPE_DAO_FACTORY (1) - se o nome de configura��o
   *         corresponde a uma configura��o espec�fica de DAO FActory.
   */
  private int parseConfigurationPersistence(
                                            StringTokenizer tokenizerConfigurationKey_ )
  {
    String token;
    int configurationType = C_CONFIGURATION_TYPE_UNKNOWN;
    if ( tokenizerConfigurationKey_.hasMoreTokens() )
    {
      token = tokenizerConfigurationKey_.nextToken();
      if ( C_TOKEN_DAO.equals( token ) )
      {
        configurationType = parseConfigurationDAO( tokenizerConfigurationKey_ );
      }
    }
    return configurationType;
  }

  /**
   * 
   * Identifica se o nome de configura��o informado consiste em uma configura��o
   * espec�fica de DAO Factory. Este m�todo � invocado quando os tokens
   * "persistence" e "dao" s�o encontrado no nome da configura��o, exatamente
   * nesta sequ�ncia. Caso o pr�ximo token encontrado for o token "factory",
   * continua a avalia��o. Caso contr�rio, conclui que a configura��o n�o � uma
   * configura��o espec�fica.
   * 
   * @param tokenizerConfigurationKey_ - nome da configura��o "quebrado" por
   *          ".".
   * @return C_CONFIGURATION_TYPE_UNKNOWN (-1) - se o nome de configura��o n�o
   *         corresponde a uma configura��o espec�fica.
   *         C_CONFIGURATION_TYPE_DAO_FACTORY (1) - se o nome de configura��o
   *         corresponde a uma configura��o espec�fica de DAO FActory.
   */
  private int parseConfigurationDAO( StringTokenizer tokenizerConfigurationKey_ )
  {
    String token;
    int configurationType = C_CONFIGURATION_TYPE_UNKNOWN;
    if ( tokenizerConfigurationKey_.hasMoreTokens() )
    {
      token = tokenizerConfigurationKey_.nextToken();
      if ( C_TOKEN_FACTORY.equals( token ) )
      {
        configurationType = parseConfigurationFactory( tokenizerConfigurationKey_ );
      }
    }
    return configurationType;
  }

  /**
   * 
   * Identifica se o nome de configura��o informado consiste em uma configura��o
   * espec�fica de DAO Factory. Este m�todo � invocado quando os tokens
   * "persistence", "dao" e "factory" s�o encontrado no nome da configura��o,
   * exatamente nesta sequ�ncia. Caso o pr�ximo token encontrado for o token
   * "implementation" e n�o houverem mais tokens no nome da configura��o, esta
   * configura��o � definida como configura��o espec�fica de DAO Factory. Caso
   * contr�rio, conclui que a configura��o n�o � uma configura��o espec�fica.
   * 
   * @param tokenizerConfigurationKey_ - nome da configura��o "quebrado" por
   *          ".".
   * @return C_CONFIGURATION_TYPE_UNKNOWN (-1) - se o nome de configura��o n�o
   *         corresponde a uma configura��o espec�fica.
   *         C_CONFIGURATION_TYPE_DAO_FACTORY (1) - se o nome de configura��o
   *         corresponde a uma configura��o espec�fica de DAO FActory.
   */
  private int parseConfigurationFactory(
                                        StringTokenizer tokenizerConfigurationKey_ )
  {
    String token;
    int configurationType = C_CONFIGURATION_TYPE_UNKNOWN;
    if ( tokenizerConfigurationKey_.hasMoreTokens() )
    {
      token = tokenizerConfigurationKey_.nextToken();
      if ( C_TOKEN_IMPLEMENTATION.equals( token )
           && !tokenizerConfigurationKey_.hasMoreTokens() )
      {
        configurationType = C_CONFIGURATION_TYPE_DAO_FACTORY;
      }
      else
      {
        while ( tokenizerConfigurationKey_.hasMoreTokens() )
        {
          tokenizerConfigurationKey_.nextToken();
        }
      }
    }
    return configurationType;
  }

  /**
   * 
   * Obt�m a inst�ncia deste Singleton.
   * 
   * @return inst�ncia deste Singleton.
   */
  public static Configuration getInstance()
  {
    return ms_instance;
  }

  /**
   * 
   * Faz a leitura do arquivo de configura��o informado como par�metro para
   * atualizar os dados do Singleton. O nome do arquivo de configura��es �
   * obrigat�rio
   * 
   * @param configurationName_ - nome do arquivo de configura��es da aplica��o.
   * @throws UnexpectedException - quando configurationName_ for null; ou
   *           informar um nome de arquivo de configura��o inexistente; ou o
   *           arquivo informado n�o puder ser lido.
   *  
   */
  public void reload( String configurationName_ ) throws UnexpectedException
  {
    InputStream configurationStream = null;

    if ( configurationName_ == null )
    {
      throw new UnexpectedException( "Configuration name cannot be null" );
    }

    configurationName_ += ".properties";
    configurationStream = this.getClass().getClassLoader().getResourceAsStream(
                                                                                configurationName_ );

    if ( configurationStream == null )
    {
      throw new UnexpectedException( "Configuration resource ["
                                     + configurationName_
                                     + "could not be found" );
    }
    try
    {
      m_properties = new Properties();
      m_properties.load( configurationStream );
    }
    catch ( FileNotFoundException e_ )
    {
      throw new UnexpectedException( "Properties file [" + configurationName_
                                                                   + "] not found", e_ );
    }
    catch ( IOException e_ )
    {
      m_properties = null;
      throw new UnexpectedException( "Error loading configuration file ["
                                     + configurationName_ + "]" );
    }
    finally
    {
      if ( configurationStream != null )
      {
        try
        {
          configurationStream.close();
        }
        catch ( IOException e_ )
        {
          throw new UnexpectedException( "Error closing configuration file ["
                                         + configurationName_ + "]" );
        }
      }
    }
  }

  /**
   * 
   * Obt�m um valor de configura��o definido no arquivo de configura��es da
   * aplica��o.
   * 
   * @param parameterName_ - nome da configura��o para a qual se deseja obter o
   *          valor parametrizado.
   * @return o valor parametrizado para o nome de configura��o informado. Caso o
   *         nome da configura��o n�o exista, ser� retornado null.
   * @throws UnexpectedException quando parameterName_ for null ou o arquivo de
   *           configura��es n�o tenha sido ainda carregado.
   */
  public String getValue( String parameterName_ ) throws UnexpectedException
  {
    if ( m_properties == null )
    {
      throw new UnexpectedException( "Configuration not loaded." );
    }
    if ( parameterName_ == null )
    {
      throw new UnexpectedException(
                                     "Name of configuratio paramenter cannot be null" );
    }
    return m_properties.getProperty( parameterName_ );
  }

}