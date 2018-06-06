package com.citibank.ods.common.persistence.dao.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.citibank.ods.common.BaseObject;
import com.citibank.ods.common.configuration.Configuration;
import com.citibank.ods.common.exception.UnexpectedException;

/**
 * 
 * Classe base para as classes do tipo DAO Factory.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public abstract class BaseDAOFactory extends BaseObject
{
  /**
   * C_DAO_FACTORY_IMPLEMENTATION - identificador de sufixo de nome de
   * configuração que caratcteriza uma configuração de DAO Factory.
   */
  private static final String C_DAO_FACTORY_IMPLEMENTATION = "persistence.dao.factory.implementation";

  /**
   * 
   * Cria uma instâncida de DAOFactory, através de reflection, invocando o
   * construtor sem parâmetros da classe parametrizada em <systemName_>+
   * ".persistence.dao.factory.implementation" do arquivo de configurações da
   * aplicação e inicializa seu data source.
   * 
   * @param systemName_ sigla do sistema que identifica a DAOFactory responsável
   *          pela persistência de dados neste sistema.
   * @return Instância da DAOFactory criada.
   */
  protected static BaseDAOFactory createDAOFactory( String systemName_ )
  {
    String daoFactoryClassName = getDAOFactoryClassName( systemName_ );
    Class daoFactoryClassDefinition = getDAOFactoryClass( daoFactoryClassName );
    BaseDAOFactory daoFactory = instantiateDAOFactory( daoFactoryClassDefinition );
    daoFactory.initializeDataSource();
    return daoFactory;
  }

  /**
   * 
   * Obtém o nome completo da classe que implementa uma DAOFactory. Este nome é
   * obtido da configuração da aplicação identificada pelo nome <systemName_>+
   * ".persistence.dao.factory.implementation"
   * 
   * @param systemName_ sigla do sistema que identifica a DAOFactory responsável
   *          pela persistência de dados neste sistema.
   * @return Nome completo da implementação da DAOFactory.
   * @throws UnexpectedException quando a parametrização <systemName_>+
   *           ".persistence.dao.factory.implementation" não existir ou não for
   *           possível obter o valor desta configuração.
   */
  private static String getDAOFactoryClassName( String systemName_ )
                                                                    throws UnexpectedException
  {
    String daoFactoryClassName = Configuration.getInstance().getValue(
                                                                       systemName_
                                                                                                                                              + "."
                                                                                                                                              + C_DAO_FACTORY_IMPLEMENTATION );

    if ( daoFactoryClassName == null || "".equals( daoFactoryClassName ) )
    {
      throw new UnexpectedException( "configuration ["
                                     + C_DAO_FACTORY_IMPLEMENTATION
                                     + " undefined." );
    }

    return daoFactoryClassName;
  }

  /**
   * 
   * Obtém o descritor (Class) da classe que implementa uma DAOFactory.
   * 
   * @param daoFactoryClassName_ - nome completo da classe que implementa uma
   *          DAOFactory.
   * @return instância do descritor (Class) da classe que implementa uma
   *         DAOFactory.
   * @throws UnexpectedException quando a classe informada daoFactoryClassName_
   *           não pode ser encontrada pelo class loader ou a classe encontrada
   *           não é especialização de BaseDAOFactory.
   */
  private static Class getDAOFactoryClass( String daoFactoryClassName_ )
                                                                        throws UnexpectedException
  {
    Class daoFactoryClass = null;
    try
    {
      daoFactoryClass = BaseDAOFactory.class.getClassLoader().loadClass(
                                                                         daoFactoryClassName_ );
    }
    catch ( ClassNotFoundException e_ )
    {
      throw new UnexpectedException( "class [" + daoFactoryClassName_
                                                                   + "] not found.", e_ );
    }

    if ( !BaseDAOFactory.class.isAssignableFrom( daoFactoryClass ) )
    {
      throw new UnexpectedException( "class [" + daoFactoryClass.getName()
                                     + "] is not a BaseDAOFactory inheritance." );
    }
    return daoFactoryClass;
  }

  /**
   * 
   * Instancia uma DAOFactory invocando, por reflection, seu construtor sem
   * parâmetros.
   * 
   * @param daoFactoryClassDefinition_ - descritor (Class) da DAOFactory que
   *          será instanciada.
   * @return instância da DAOFactory.
   * @throws UnexpectedException quando a DAOFactory que será instanciada não
   *           possuir um construtor sem parâmetros; ou seu construtor não for
   *           acessível (visibilidade); ou não for instanciável por ser uma
   *           interface ou classe abstrata; ou se ocorrer alguma exceção no
   *           cosntrutor invocado.
   */
  private static BaseDAOFactory instantiateDAOFactory(
                                                      Class daoFactoryClassDefinition_ )
                                                                                        throws UnexpectedException
  {
    BaseDAOFactory daoFactoryInstance = null;
    Constructor daoFactoryDefaultConstructor = null;
    try
    {
      daoFactoryDefaultConstructor = daoFactoryClassDefinition_.getConstructor( null );
      daoFactoryInstance = ( BaseDAOFactory ) daoFactoryDefaultConstructor.newInstance( null );
    }
    catch ( NoSuchMethodException e_ )
    {
      throw new UnexpectedException(
                                     
                                     "class ["
                                                                          + daoFactoryClassDefinition_.getName()
                                                                          + "] doesn't have the default constructor.", e_ );
    }
    catch ( InvocationTargetException e_ )
    {
      throw new UnexpectedException( 
                                     "Exception occurred in ["
                                                                   + daoFactoryClassDefinition_.getName()
                                                                   + "] default constructor.", e_.getTargetException() );
    }
    catch ( IllegalAccessException e_ )
    {
      throw new UnexpectedException(
                                     
                                     "["
                                                                          + daoFactoryClassDefinition_.getName()
                                                                          + "] default constructor is not accessible.", e_ );
    }
    catch ( InstantiationException e_ )
    {
      throw new UnexpectedException( "[" + daoFactoryClassDefinition_.getName()
                                                                   + "] is abstract or an interface.", e_ );
    }
    return daoFactoryInstance;
  }

  protected abstract void initializeDataSource();
}