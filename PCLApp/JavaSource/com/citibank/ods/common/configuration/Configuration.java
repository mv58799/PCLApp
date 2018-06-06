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
 * Singleton que representa as configurações definidas para a aplicação. Este
 * singleton faz a leitura do arquivo de proporiedade definido como arquivo de
 * configuração da aplicação no momento de sua instanciação. A instanciação
 * deste singleton ocorre através do método bootstrap.
 * 
 * Caso haja necessidade de o arquivo de configurações seja lido novamente, em
 * função de modificações realizadas no arquivo, o método reload deverá ser
 * invocado. Neste caso, não será criada uma nova instância para o singleton: a
 * instância atual do Singleton será atualizada.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class Configuration
{

  /**
   * C_CONFIGURATION_TYPE_DAO_FACTORY - Tipo de configuração que indica que é
   * uma configuração de DAO Factory
   */
  private static final int C_CONFIGURATION_TYPE_DAO_FACTORY = 1;

  /**
   * C_CONFIGURATION_TYPE_UNKNOWN - Tipo de configuração que indica que não é
   * uma configuração que possui tratamento especial
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
   * m_properties - tabela de nomes de configuração e seus respectivos valores.
   */
  private Properties m_properties = null;

  /**
   * m_daoFactoriesKeys - lista dos nomes de configurações que caracterizam uma
   * configuração de DAO Factory.
   */
  private ArrayList m_daoFactoriesKeys = null;

  /**
   * Construtor privado, disponível apenas para esta classe, pelo fato de
   * tratar-se de um Singleton.
   * 
   * @param configurationName_ - Nome do arquivo de propriedades que contempla a
   *          configuração da aplicação.
   */
  private Configuration( String configurationName_ )
  {
    reload( configurationName_ );
    organizeProperties();
  }

  /**
   * 
   * Inicializa este singleton, criando a instância deste singleton e lendo o
   * arquivo de propriedades informado. O método bootstrap poderá ser invocado
   * somente uma vez durante o ciclo de vida do ClassLoader que carrega a
   * instância desta classe. Caso haja a necessidade de nova leitura do arquivo
   * de configurações pelo fato deste arquivo ter sido modificado, o método
   * reload deverá ser invocado.
   * 
   * @param configurationName_ - nome do arquivo de propriendades que possui as
   *          configurações da aplicação.
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
   * Obtém um iterator da lista de nomes de configuração que correspondem a
   * configurações de DAO Factories.
   * 
   * @return Iterator da lista de nomes de configuração que correspondem a
   *         configurações de DAO Factories.
   */
  public Iterator getDAOFactoryKeys()
  {
    return m_daoFactoriesKeys.iterator();
  }

  /**
   * 
   * Organiza as configurações definidas no arquivo de configurações, criando
   * uma lista específica de nomes de configuração que representam apenas
   * configurações de DAO Factories. A configurações que são específicas de DAO
   * Factories são caracterizadas por possuírem em seu nome o sufixo
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
   * Identifica se o nome de configuração informado consiste em uma configuração
   * específica de DAO Factory. Inicialmente, procura pelo token "persistence".
   * Se encontrar este token, continua a avaliação. Caso contrário, conclui que
   * a configuração não é uma configuração específica.
   * 
   * @param tokenizerConfigurationKey_ - nome da configuração "quebrado" por
   *          ".".
   * @return C_CONFIGURATION_TYPE_UNKNOWN (-1) - se o nome de configuração não
   *         corresponde a uma configuração específica.
   *         C_CONFIGURATION_TYPE_DAO_FACTORY (1) - se o nome de configuração
   *         corresponde a uma configuração específica de DAO FActory.
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
   * Identifica se o nome de configuração informado consiste em uma configuração
   * específica de DAO Factory. Este método é invocado quando o token
   * "persistence" é encontrado no nome da configuração. Caso o próximo token
   * encontrado for o token "dao", continua a avaliação. Caso contrário, conclui
   * que a configuração não é uma configuração específica.
   * 
   * @param tokenizerConfigurationKey_ - nome da configuração "quebrado" por
   *          ".".
   * @return C_CONFIGURATION_TYPE_UNKNOWN (-1) - se o nome de configuração não
   *         corresponde a uma configuração específica.
   *         C_CONFIGURATION_TYPE_DAO_FACTORY (1) - se o nome de configuração
   *         corresponde a uma configuração específica de DAO FActory.
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
   * Identifica se o nome de configuração informado consiste em uma configuração
   * específica de DAO Factory. Este método é invocado quando os tokens
   * "persistence" e "dao" são encontrado no nome da configuração, exatamente
   * nesta sequência. Caso o próximo token encontrado for o token "factory",
   * continua a avaliação. Caso contrário, conclui que a configuração não é uma
   * configuração específica.
   * 
   * @param tokenizerConfigurationKey_ - nome da configuração "quebrado" por
   *          ".".
   * @return C_CONFIGURATION_TYPE_UNKNOWN (-1) - se o nome de configuração não
   *         corresponde a uma configuração específica.
   *         C_CONFIGURATION_TYPE_DAO_FACTORY (1) - se o nome de configuração
   *         corresponde a uma configuração específica de DAO FActory.
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
   * Identifica se o nome de configuração informado consiste em uma configuração
   * específica de DAO Factory. Este método é invocado quando os tokens
   * "persistence", "dao" e "factory" são encontrado no nome da configuração,
   * exatamente nesta sequência. Caso o próximo token encontrado for o token
   * "implementation" e não houverem mais tokens no nome da configuração, esta
   * configuração é definida como configuração específica de DAO Factory. Caso
   * contrário, conclui que a configuração não é uma configuração específica.
   * 
   * @param tokenizerConfigurationKey_ - nome da configuração "quebrado" por
   *          ".".
   * @return C_CONFIGURATION_TYPE_UNKNOWN (-1) - se o nome de configuração não
   *         corresponde a uma configuração específica.
   *         C_CONFIGURATION_TYPE_DAO_FACTORY (1) - se o nome de configuração
   *         corresponde a uma configuração específica de DAO FActory.
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
   * Obtém a instância deste Singleton.
   * 
   * @return instância deste Singleton.
   */
  public static Configuration getInstance()
  {
    return ms_instance;
  }

  /**
   * 
   * Faz a leitura do arquivo de configuração informado como parâmetro para
   * atualizar os dados do Singleton. O nome do arquivo de configurações é
   * obrigatório
   * 
   * @param configurationName_ - nome do arquivo de configurações da aplicação.
   * @throws UnexpectedException - quando configurationName_ for null; ou
   *           informar um nome de arquivo de configuração inexistente; ou o
   *           arquivo informado não puder ser lido.
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
   * Obtém um valor de configuração definido no arquivo de configurações da
   * aplicação.
   * 
   * @param parameterName_ - nome da configuração para a qual se deseja obter o
   *          valor parametrizado.
   * @return o valor parametrizado para o nome de configuração informado. Caso o
   *         nome da configuração não exista, será retornado null.
   * @throws UnexpectedException quando parameterName_ for null ou o arquivo de
   *           configurações não tenha sido ainda carregado.
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