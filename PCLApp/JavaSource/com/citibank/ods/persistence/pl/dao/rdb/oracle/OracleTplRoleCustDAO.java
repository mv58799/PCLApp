package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplRoleCustEntity;
import com.citibank.ods.entity.pl.TplRoleCustEntity;
import com.citibank.ods.entity.pl.valueobject.TplRoleCustEntityVO;
import com.citibank.ods.persistence.pl.dao.TplRoleCustDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author leonardo.nakada
 * 
 * Classe DAO da tabela TPL_ROLE_CUST
 */
public class OracleTplRoleCustDAO extends BaseOracleTplRoleCustDAO implements
    TplRoleCustDAO
{
  /*
   * Nome da tabela
   */
  private static final String C_TABLE_NAME = C_PL_SCHEMA + "TPL_ROLE_CUST";

  /**
   * Carregamento do combo com as informações de Role CUst
   */
  public DataSet loadDomain()
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_ROLE_CUST_CODE + ", " );
      query.append( C_ROLE_CUST_TEXT );
      query.append( " FROM " );
      query.append( C_TABLE_NAME );
      query.append( " WHERE " );
      query.append( C_REC_STAT_CODE + " <> ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++, TplRoleCustEntity.C_REC_STAT_CODE_INACTIVE );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      rsds = new ResultSetDataSet( resultSet );
      resultSet.close();
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }

    return rsds;
  }

  /**
   * Recupera um registro a partir de sua chave
   */
  public BaseTplRoleCustEntity find( BaseTplRoleCustEntity tplRoleCustEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList tplRoleCustEntities;
    BaseTplRoleCustEntity tplRoleCustEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_ROLE_CUST_CODE + ", " );
      query.append( C_ROLE_CUST_TEXT + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_REC_STAT_CODE + " " );
      query.append( " FROM " );
      query.append( C_TABLE_NAME );
      query.append( " WHERE " );
      query.append( C_ROLE_CUST_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString( 1, tplRoleCustEntity_.getData().getRoleCustCode() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());
	  

      tplRoleCustEntities = instantiateFromResultSet( resultSet );

      if ( tplRoleCustEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplRoleCustEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplRoleCustEntity = ( BaseTplRoleCustEntity ) tplRoleCustEntities.get( 0 );
      }

      return tplRoleCustEntity;
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }
  }

  /*
   * Recupera um ArrayList a partir de um ResultSet
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    TplRoleCustEntityVO roleCustVO;
    TplRoleCustEntity roleCustEntity;
    Timestamp lastUpdDateTs;
    ArrayList roleCustEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        roleCustEntity = new TplRoleCustEntity();
        roleCustVO = ( TplRoleCustEntityVO ) roleCustEntity.getData();

        roleCustVO.setRoleCustCode( resultSet_.getString( C_ROLE_CUST_CODE ) );

        roleCustVO.setRoleCustText( resultSet_.getString( C_ROLE_CUST_TEXT ) );

        lastUpdDateTs = resultSet_.getTimestamp( C_LAST_UPD_DATE );
        roleCustVO.setLastUpdDate( new Date( lastUpdDateTs.getTime() ) );

        roleCustVO.setLastUpdUserId( resultSet_.getString( C_LAST_UPD_USER_ID ) );

        roleCustEntities.add( roleCustEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return roleCustEntities;
  }

}