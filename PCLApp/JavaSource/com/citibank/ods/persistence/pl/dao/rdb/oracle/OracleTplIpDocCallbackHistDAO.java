package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplIpDocCallbackEntity;
import com.citibank.ods.entity.pl.TplIpDocCallbackHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplIpDocCallbackHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplIpDocCallbackHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 16, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class OracleTplIpDocCallbackHistDAO extends
    BaseOracleTplIpDocCallbackDAO implements TplIpDocCallbackHistDAO
{

  public String C_IP_DOC_CALLBACK_REF_DATE = "PRMNT_INSTR_CALLBACK_REF_DATE";

  public String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  public String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  public String C_REC_STAT_CODE = "REC_STAT_CODE";

  public String C_TPL_IP_DOC_CALLBACK_HIST = C_PL_SCHEMA
                                             + "TPL_PRMNT_INSTR_CALLBACK_HIST";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocCallbackHistDAO#insert(com.citibank.ods.entity.pl.TplIpDocCallbackHistEntity)
   */
  public TplIpDocCallbackHistEntity insert(
                                           TplIpDocCallbackHistEntity tplIpDocCallbackHistEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_IP_DOC_CALLBACK_HIST + " (" );
      query.append( C_IP_CALLBACK_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_CTC_NBR + ", " );
      query.append( C_IP_DOC_CODE + ", " );
      query.append( C_IP_DOC_CALLBACK_REF_DATE + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplIpDocCallbackHistEntity_.getData().getIpCallbackCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackHistEntity_.getData().getIpCallbackCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );

      }

      if ( tplIpDocCallbackHistEntity_.getData().getLastUpdUserId() != null
           && !tplIpDocCallbackHistEntity_.getData().getLastUpdUserId().equals(
                                                                                "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplIpDocCallbackHistEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocCallbackHistEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplIpDocCallbackHistEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( tplIpDocCallbackHistEntity_.getData().getCustNbr() != null
           && tplIpDocCallbackHistEntity_.getData().getCustNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackHistEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplIpDocCallbackHistEntity_.getData().getCtcNbr() != null
           && tplIpDocCallbackHistEntity_.getData().getCtcNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackHistEntity_.getData().getCtcNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplIpDocCallbackHistEntity_.getData().getIpDocCode() != null
           && tplIpDocCallbackHistEntity_.getData().getIpDocCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackHistEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( ( ( TplIpDocCallbackHistEntityVO ) tplIpDocCallbackHistEntity_.getData() ).getIpDocCallbackRefDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplIpDocCallbackHistEntityVO ) tplIpDocCallbackHistEntity_.getData() ).getIpDocCallbackRefDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( ( ( TplIpDocCallbackHistEntityVO ) tplIpDocCallbackHistEntity_.getData() ).getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplIpDocCallbackHistEntityVO ) tplIpDocCallbackHistEntity_.getData() ).getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( ( ( TplIpDocCallbackHistEntityVO ) tplIpDocCallbackHistEntity_.getData() ).getLastAuthUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplIpDocCallbackHistEntityVO ) tplIpDocCallbackHistEntity_.getData() ).getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( ( ( TplIpDocCallbackHistEntityVO ) tplIpDocCallbackHistEntity_.getData() ).getRecStatCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplIpDocCallbackHistEntityVO ) tplIpDocCallbackHistEntity_.getData() ).getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

    }
    catch ( Exception e )
    {
      throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }

    return tplIpDocCallbackHistEntity_;

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocCallbackHistDAO#find(com.citibank.ods.entity.pl.TplIpDocCallbackHistEntity)
   */
  public TplIpDocCallbackHistEntity find(
                                         TplIpDocCallbackHistEntity tplIpDocCallbackHistEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    ResultSet resultSet = null;
    ArrayList tplIpDocCallbackHistEntities;
    BaseTplIpDocCallbackEntity tplIpDocCallbackEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_IP_CALLBACK_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_IP_DOC_CALLBACK_REF_DATE + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_CTC_NBR + ", " );
      query.append( C_IP_DOC_CODE );
      query.append( " FROM " + C_TPL_IP_DOC_CALLBACK_HIST );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + "= ? AND " );
      query.append( C_IP_DOC_CODE + "= ? AND " );
      query.append( C_IP_CALLBACK_CODE + "= ? AND " );
      query.append( C_CTC_NBR + "= ? AND " );
      query.append( C_IP_DOC_CALLBACK_REF_DATE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplIpDocCallbackHistEntity_.getData().getCustNbr() != null
           && tplIpDocCallbackHistEntity_.getData().getCustNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackHistEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplIpDocCallbackHistEntity_.getData().getIpDocCode() != null
           && tplIpDocCallbackHistEntity_.getData().getIpDocCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackHistEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplIpDocCallbackHistEntity_.getData().getIpCallbackCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackHistEntity_.getData().getIpCallbackCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplIpDocCallbackHistEntity_.getData().getCtcNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackHistEntity_.getData().getCtcNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      if ( ( ( TplIpDocCallbackHistEntityVO ) tplIpDocCallbackHistEntity_.getData() ).getIpDocCallbackRefDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplIpDocCallbackHistEntityVO ) tplIpDocCallbackHistEntity_.getData() ).getIpDocCallbackRefDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      tplIpDocCallbackHistEntities = instantiateFromResultSet( resultSet );

      if ( tplIpDocCallbackHistEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplIpDocCallbackHistEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplIpDocCallbackEntity = ( BaseTplIpDocCallbackEntity ) tplIpDocCallbackHistEntities.get( 0 );
      }

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

    return ( TplIpDocCallbackHistEntity ) tplIpDocCallbackEntity;
  }

  /*
   * Retorna uma coleção de entities a partir do rs
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {

    TplIpDocCallbackHistEntity tplIpDocCallbackHistEntity;
    ArrayList tplIpDocCallbackHistEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplIpDocCallbackHistEntity = new TplIpDocCallbackHistEntity();

        tplIpDocCallbackHistEntity.getData().setIpCallbackCode(
                                                                new BigInteger(
                                                                                ( resultSet_.getString( C_IP_CALLBACK_CODE ) ) ) );
        tplIpDocCallbackHistEntity.getData().setLastUpdUserId(
                                                               ( resultSet_.getString( C_LAST_UPD_USER_ID ) ) );
        tplIpDocCallbackHistEntity.getData().setLastUpdDate(
                                                             ( resultSet_.getDate( C_LAST_UPD_DATE ) ) );
        tplIpDocCallbackHistEntity.getData().setCustNbr(
                                                         new BigInteger(
                                                                         ( resultSet_.getString( C_CUST_NBR ) ) ) );
        tplIpDocCallbackHistEntity.getData().setCtcNbr(
                                                        new BigInteger(
                                                                        ( resultSet_.getString( C_CTC_NBR ) ) ) );
        tplIpDocCallbackHistEntity.getData().setIpDocCode(
                                                           new BigInteger(
                                                                           ( resultSet_.getString( C_IP_DOC_CODE ) ) ) );

        ( ( TplIpDocCallbackHistEntityVO ) tplIpDocCallbackHistEntity.getData() ).setIpDocCallbackRefDate( ( resultSet_.getDate( C_IP_DOC_CALLBACK_REF_DATE ) ) );

        ( ( TplIpDocCallbackHistEntityVO ) tplIpDocCallbackHistEntity.getData() ).setLastAuthDate( ( resultSet_.getDate( C_LAST_AUTH_DATE ) ) );

        ( ( TplIpDocCallbackHistEntityVO ) tplIpDocCallbackHistEntity.getData() ).setLastAuthUserId( ( resultSet_.getString( C_LAST_AUTH_USER_ID ) ) );

        ( ( TplIpDocCallbackHistEntityVO ) tplIpDocCallbackHistEntity.getData() ).setRecStatCode( ( resultSet_.getString( C_REC_STAT_CODE ) ) );

        tplIpDocCallbackHistEntities.add( tplIpDocCallbackHistEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return tplIpDocCallbackHistEntities;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTplIpDocCallbackDAO#find(com.citibank.ods.entity.pl.BaseTplIpDocCallbackEntity)
   */
  public BaseTplIpDocCallbackEntity find(
                                         BaseTplIpDocCallbackEntity baseTplIpDocCallbackEntity_ )
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocCallbackHistDAO#findByPK(java.math.BigInteger,
   *      java.math.BigInteger)
   */

  public ArrayList findByPK( BigInteger ipDocCode_, BigInteger custNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList ipDocCallbackEntities;
    
    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_IP_DOC_CODE + ", " );
      query.append( C_IP_CALLBACK_CODE + ", " );
      query.append( C_CTC_NBR + ", " );
      query.append( C_IP_DOC_CALLBACK_REF_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " FROM " + C_TPL_IP_DOC_CALLBACK_HIST );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + "= ? AND " );
      query.append( C_IP_DOC_CODE + "= ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, custNbr_.longValue() );

      preparedStatement.setLong( 2, ipDocCode_.longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      ipDocCallbackEntities = instantiateFromResultSet( resultSet );

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

    return ipDocCallbackEntities;
  }

}