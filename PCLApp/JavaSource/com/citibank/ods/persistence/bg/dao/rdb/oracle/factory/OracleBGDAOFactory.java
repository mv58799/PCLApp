package com.citibank.ods.persistence.bg.dao.rdb.oracle.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.citibank.ods.common.configuration.Configuration;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.factory.JNDIFactory;
import com.citibank.ods.persistence.bg.dao.TbgBankDAO;
import com.citibank.ods.persistence.bg.dao.TbgBranchDAO;
import com.citibank.ods.persistence.bg.dao.TbgCustAddressDAO;
import com.citibank.ods.persistence.bg.dao.TbgCustCellDAO;
import com.citibank.ods.persistence.bg.dao.TbgCustMailDAO;
import com.citibank.ods.persistence.bg.dao.TbgOfficerDAO;
import com.citibank.ods.persistence.bg.dao.factory.BGDAOFactory;
import com.citibank.ods.persistence.bg.dao.rdb.oracle.OracleTbgBankDAO;
import com.citibank.ods.persistence.bg.dao.rdb.oracle.OracleTbgBranchDAO;
import com.citibank.ods.persistence.bg.dao.rdb.oracle.OracleTbgCustAddressDAO;
import com.citibank.ods.persistence.bg.dao.rdb.oracle.OracleTbgCustCellDAO;
import com.citibank.ods.persistence.bg.dao.rdb.oracle.OracleTbgCustMailDAO;
import com.citibank.ods.persistence.bg.dao.rdb.oracle.OracleTbgOfficerDAO;
import com.citibank.sad.passwordmanager.PasswordManager;

/**
 * <p>
 * Title: Classe DAO Factory do sistema BG para o banco de dados Oracle
 * </p>
 * <p>
 * Description: Classe para acesso a dados do sistema BG no banco de dados
 * Oracle
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * <p>
 * Company: ACCENTURE
 * </p>
 * @author gerson.a.rodrigues
 * @version 1.0
 */
public class OracleBGDAOFactory extends BGDAOFactory
{

  private final static String C_ODS_DATA_SOURCE_JNDI_NAME = "datasource.jndi.name.ods";

  private static DataSource ms_dataSource = null;

  public OracleBGDAOFactory()
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
      String dbProvider = Configuration.getInstance().getValue( "DBProvider" );
      if ( "PasswordManager".equals( dbProvider ) )
      {
        String userDB = Configuration.getInstance().getValue( "userDB" );
        String passwordFile = Configuration.getInstance().getValue(
                                                                    "passwordFile" );
        String keyFile = Configuration.getInstance().getValue( "keyFile" );
        String serverDB = Configuration.getInstance().getValue( "serverDB" );
        String password = getPassword( userDB, passwordFile, keyFile, serverDB );
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
   * @see com.citibank.ods.persistence.bg.dao.factory.BGDAOFactory#getTbgBankDAO()
   */
  public TbgBankDAO getTbgBankDAO()
  {
    return new OracleTbgBankDAO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.bg.dao.factory.BGDAOFactory#getTbgBranchDAO()
   */
  public TbgBranchDAO getTbgBranchDAO()
  {
    return new OracleTbgBranchDAO();
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTbgCustAddressDAO()
   */
  public TbgCustAddressDAO getTbgCustAddressDAO()
  {
    return new OracleTbgCustAddressDAO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTbgOfficerDAO()
   */
  public TbgOfficerDAO getTbgOfficerDAO()
  {
    return new OracleTbgOfficerDAO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.bg.dao.factory.BGDAOFactory#getTbgCustMailDAO()
   */
  public TbgCustMailDAO getTbgCustMailDAO()
  {
    return new OracleTbgCustMailDAO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.bg.dao.factory.BGDAOFactory#getTbgCustCellDAO()
   */
  public TbgCustCellDAO getTbgCustCellDAO()
  {
    return new OracleTbgCustCellDAO();
  }

}