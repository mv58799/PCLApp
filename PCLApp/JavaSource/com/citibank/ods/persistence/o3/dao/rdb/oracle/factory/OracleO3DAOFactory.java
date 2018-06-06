package com.citibank.ods.persistence.o3.dao.rdb.oracle.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.citibank.ods.common.configuration.Configuration;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.factory.JNDIFactory;
import com.citibank.ods.common.persistence.dao.factory.BaseDAOFactory;
import com.citibank.ods.persistence.o3.dao.factory.O3DAOFactory;
import com.citibank.sad.passwordmanager.PasswordManager;

/**
 * <p>
 * Title: Classe DAO Factory do sistema ODS para o banco de dados Oracle
 * </p>
 * <p>
 * Description: Classe para acesso a dados do sistema ODS no banco de dados
 * Oracle
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * <p>
 * Company: ACCENTURE
 * </p>
 * @author ralf.davi.filho
 * @version 1.0
 */
public class OracleO3DAOFactory extends O3DAOFactory
{

  private final static String C_ODS_DATA_SOURCE_JNDI_NAME = "datasource.jndi.name.ods";

  private static DataSource ms_dataSource = null;

  private static final String C_DAO_FACTORY_SYSTEM = "o3";

  public OracleO3DAOFactory()
  {
    super();
  }

  protected final void initializeDataSource()
  {
    if ( ms_dataSource != null )
    {
      throw new UnexpectedException( "Datasource already initialized." );
    }

    String dataSourceJNDIName = Configuration.getInstance().getValue(
                                                                      C_ODS_DATA_SOURCE_JNDI_NAME );

    if ( dataSourceJNDIName == null || "".equals( dataSourceJNDIName ) )
    {
      throw new UnexpectedException( "configuration ["
                                     + C_ODS_DATA_SOURCE_JNDI_NAME
                                     + "] undefined." );
    }

    ms_dataSource = ( DataSource ) JNDIFactory.createObject( dataSourceJNDIName );

  }

  public static ManagedRdbConnection getConnection()
  {
    Connection connection = null;
    ManagedRdbConnection managedRDBConnection = null;
    if ( ms_dataSource == null )
    {
      throw new UnexpectedException( "DataSource not initialized." );
    }

    try
    {
      {
        String dbProvider = Configuration.getInstance().getValue( "DBProvider" );
        if ( "PasswordManager".equals( dbProvider ) )
        {
          String userDB = Configuration.getInstance().getValue( "userDB" );
          String passwordFile = Configuration.getInstance().getValue(
                                                                      "passwordFile" );
          String keyFile = Configuration.getInstance().getValue( "keyFile" );
          String serverDB = Configuration.getInstance().getValue( "serverDB" );
          String password = getPassword( userDB, passwordFile, keyFile,
                                         serverDB );
          connection = ms_dataSource.getConnection( userDB, password );
        }
        else
        {
          if ( "RSADatasource".equals( dbProvider ) )
          {
            connection = ms_dataSource.getConnection();
          }
        }
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException(
                                     e.getErrorCode(),
                                     "Could not create a connection from DataSource", e );
    }
    catch ( Exception e_ )
    {
      throw new UnexpectedException( e_.getMessage() );
    }

    managedRDBConnection = new ManagedRdbConnection( connection );
    return managedRDBConnection;
  }

  private static String getPassword( String userDB, String passwordFile,
                                    String keyFile, String serverDB )
                                                                     throws Exception
  {

    PasswordManager passwordManager = new PasswordManager( passwordFile,
                                                           keyFile, userDB,
                                                           serverDB );
    int result = passwordManager.get();
    if ( result == PasswordManager.TRUE_ )
    {
      return passwordManager.getPassword();
    }
    else
    {
      throw new UnexpectedException( result,
                                     "Erro ao recuperar senha de criptografia" );
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getCustomerDAO()
   */

  /**
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  public static void bootstrap()
  {
    if ( ms_instance != null )
    {
      throw new UnexpectedException(
                                     "bootstrap cannot be invoke more than once." );
    }
    ms_instance = ( O3DAOFactory ) BaseDAOFactory.createDAOFactory( C_DAO_FACTORY_SYSTEM );
  }

}