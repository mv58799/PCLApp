package com.citibank.ods.common.bootstrap;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.citibank.ods.common.configuration.Configuration;
import com.citibank.ods.common.exception.UnexpectedException;

/**
 * 
 * Servlet respons�vel pela inicializa��o de classes que ser�o utilizadas pela
 * aplica��o: - Configuration; - DAO Factories.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class BootstrapServlet extends HttpServlet implements Servlet
{
  /**
   * C_APPLICATION_CONFIGURATION - Nome da configura��o do servlet que contempla
   * o nome do arquivo de properties que possui as configura��es da aplica��o.
   */
  private static final String C_APPLICATION_CONFIGURATION = "application.configuration";

  /**
   * C_DAO_FACTORY_BOOTSTRAP_METHOD - nome do m�todo que � executado para
   * efetuar o bootstrap de uma classe.
   */
  private static final String C_DAO_FACTORY_BOOTSTRAP_METHOD = "bootstrap";

  /*
   * (non-Java-doc)
   * 
   * @see javax.servlet.http.HttpServlet#HttpServlet()
   */
  public BootstrapServlet()
  {
    super();
  }

  /*
   * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
   */
  public void init( ServletConfig servletConfig_ ) throws ServletException
  {
    //inicializa��o de COnfiguration;
    initConfiguration( servletConfig_ );
    //inicializa��o de DAO Factories registradas
    initDAOFactories();
  }

  /**
   * 
   * Inicializa (bootstrap) a classe Configuration.
   * 
   * @param servletConfig_ - Configura��es definidas para este servlet.
   */
  private void initConfiguration( ServletConfig servletConfig_ )
  {
    String configurationName = servletConfig_.getInitParameter( C_APPLICATION_CONFIGURATION );
    if ( configurationName == null )
    {
      throw new UnexpectedException( C_APPLICATION_CONFIGURATION
                                     + " parameter undefined." );
    }
    Configuration.bootstrap( configurationName );
  }

  /**
   * 
   * Inicializa as DAO Factories registradas para a aplica��o. As DAO Factories
   * registradas para a aplica��o s�o todas as entradas definidas no arquivo de
   * configura��es da aplica��o que possuem o sufixo:
   * "persistence.dao.factory.implementation". <br>
   * Para cada DAO FActory registrada o m�todo bootstrap p�blico, est�tico e sem
   * par�metros � invocado.
   *  
   */
  private void initDAOFactories()
  {
    Iterator daoFactoryKeys = Configuration.getInstance().getDAOFactoryKeys();
    while ( daoFactoryKeys.hasNext() )
    {
      String daoFactoryKey = ( String ) daoFactoryKeys.next();
      String daoFactoryImplementationName = Configuration.getInstance().getValue(
                                                                                  daoFactoryKey );
      invokeDAOFactoryBootstrap( daoFactoryImplementationName );
    }
  }

  /**
   * 
   * Executa o m�todo bootstrap, p�blico, est�tico e sem par�metros de uma
   * implementa��o de DAO Factory.
   * 
   * @param daoFactoryImplementationName_ - nome completo da DAOFactory que ser�
   *          inicializada
   */
  private void invokeDAOFactoryBootstrap( String daoFactoryImplementationName_ )
  {
    try
    {
      Class daoFactoryClass = this.getClass().getClassLoader().loadClass(
                                                                          daoFactoryImplementationName_ );
      //Obt�m o m�todo bootrap p�blico, est�tico e sem par�metros da classe
      // informada em daoFactoryImplementationName_
      Method bootstrapMethod = daoFactoryClass.getMethod(
                                                          C_DAO_FACTORY_BOOTSTRAP_METHOD,
                                                          null );
      //Executa o m�todo bootstrap encontrado
      bootstrapMethod.invoke( null, null );
    }
    catch ( ClassNotFoundException e_ )
    {
      throw new UnexpectedException( "DAOFactory implementation class ["
                                     + daoFactoryImplementationName_
                                     + "] not found.", e_ );
    }
    catch ( NoSuchMethodException e_ )
    {
      throw new UnexpectedException(

      "DAOFactory implementation class [" + daoFactoryImplementationName_
            + "] must implement public static void bootstrap() method", e_ );
    }
    catch ( InvocationTargetException e_ )
    {
      throw new UnexpectedException(

      "Error executing DAOFactory implementation class ["
            + daoFactoryImplementationName_ + "] bootstrap",
                                     e_.getTargetException() );
    }
    catch ( IllegalAccessException e_ )
    {
      throw new UnexpectedException(

      "Method bootstrap of DAOFactory implementation class ["
            + daoFactoryImplementationName_ + "] must have public access", e_ );
    }
  }

  /*
   * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse)
   */
  protected void doGet( HttpServletRequest arg0, HttpServletResponse arg1 )
                                                                           throws ServletException,
                                                                           IOException
  {
    throw new UnsupportedOperationException(
                                             "BootstrapServelt is only for bootrstraping" );
    // TODO Auto-generated method stub
  }

  /*
   * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse)
   */
  protected void doPost( HttpServletRequest arg0, HttpServletResponse arg1 )
                                                                            throws ServletException,
                                                                            IOException
  {
    throw new UnsupportedOperationException(
                                             "BootstrapServelt is only for bootrstraping" );
    // TODO Auto-generated method stub
  }

  /*
   * @see javax.servlet.Servlet#destroy()
   */
  public void destroy()
  {
    //    
  }
}