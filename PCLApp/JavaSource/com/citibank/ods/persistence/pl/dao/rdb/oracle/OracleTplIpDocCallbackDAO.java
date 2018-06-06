package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplIpDocCallbackEntity;
import com.citibank.ods.entity.pl.TplIpDocCallbackEntity;
import com.citibank.ods.entity.pl.valueobject.TplIpDocCallbackEntityVO;
import com.citibank.ods.persistence.pl.dao.TplIpDocCallbackDAO;
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

public class OracleTplIpDocCallbackDAO extends BaseOracleTplIpDocCallbackDAO
    implements TplIpDocCallbackDAO
{

  public String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  public String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  public String C_REC_STAT_CODE = "REC_STAT_CODE";

  public String C_TPL_IP_DOC_CALLBACK = C_PL_SCHEMA
                                        + "TPL_PRMNT_INSTR_CALLBACK";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocCallbackDAO#insert(com.citibank.ods.entity.pl.TplIpDocCallbackEntity)
   */
  public TplIpDocCallbackEntity insert(
                                       TplIpDocCallbackEntity tplIpDocCallbackEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_IP_DOC_CALLBACK + " (" );
      query.append( C_IP_CALLBACK_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_CTC_NBR + ", " );
      query.append( C_IP_DOC_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplIpDocCallbackEntity_.getData().getIpCallbackCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackEntity_.getData().getIpCallbackCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );

      }

      if ( tplIpDocCallbackEntity_.getData().getLastUpdUserId() != null
           && !tplIpDocCallbackEntity_.getData().getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplIpDocCallbackEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocCallbackEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplIpDocCallbackEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( tplIpDocCallbackEntity_.getData().getRecStatCode() != null )
      {
        preparedStatement.setString( count++,
                             tplIpDocCallbackEntity_.getData().getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( ( ( TplIpDocCallbackEntityVO ) tplIpDocCallbackEntity_.getData() ).getLastAuthUserId() != null
           && !( ( TplIpDocCallbackEntityVO ) tplIpDocCallbackEntity_.getData() ).getLastAuthUserId().equals(
                                                                                                              "" ) )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplIpDocCallbackEntityVO ) tplIpDocCallbackEntity_.getData() ).getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( ( ( TplIpDocCallbackEntityVO ) tplIpDocCallbackEntity_.getData() ).getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplIpDocCallbackEntityVO ) tplIpDocCallbackEntity_.getData() ).getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( tplIpDocCallbackEntity_.getData().getCustNbr() != null
           && tplIpDocCallbackEntity_.getData().getCustNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplIpDocCallbackEntity_.getData().getCtcNbr() != null
           && tplIpDocCallbackEntity_.getData().getCtcNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackEntity_.getData().getCtcNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplIpDocCallbackEntity_.getData().getIpDocCode() != null
           && tplIpDocCallbackEntity_.getData().getIpDocCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return ( TplIpDocCallbackEntity ) find( tplIpDocCallbackEntity_ );

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
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocCallbackDAO#update(com.citibank.ods.entity.pl.TplIpDocCallbackEntity)
   */
  public void update( TplIpDocCallbackEntity tplIpDocCallbackEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_IP_DOC_CALLBACK + " " );
      query.append( " SET " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_REC_STAT_CODE + " = ?, " );
      query.append( C_LAST_AUTH_USER_ID + " = ?, " );
      query.append( C_LAST_AUTH_DATE + " = ? " );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + "= ? AND " );
      query.append( C_IP_DOC_CODE + "= ? AND " );
      query.append( C_IP_CALLBACK_CODE + "= ? AND " );
      query.append( C_CTC_NBR + "= ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplIpDocCallbackEntity_.getData().getLastUpdUserId() != null
           && !tplIpDocCallbackEntity_.getData().getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplIpDocCallbackEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocCallbackEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplIpDocCallbackEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( tplIpDocCallbackEntity_.getData().getRecStatCode() != null )
      {
        preparedStatement.setString( count++,
                             tplIpDocCallbackEntity_.getData().getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( ( ( TplIpDocCallbackEntityVO ) tplIpDocCallbackEntity_.getData() ).getLastAuthUserId() != null
           && !( ( TplIpDocCallbackEntityVO ) tplIpDocCallbackEntity_.getData() ).getLastAuthUserId().equals(
                                                                                                              "" ) )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplIpDocCallbackEntityVO ) tplIpDocCallbackEntity_.getData() ).getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( ( ( TplIpDocCallbackEntityVO ) tplIpDocCallbackEntity_.getData() ).getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplIpDocCallbackEntityVO ) tplIpDocCallbackEntity_.getData() ).getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( tplIpDocCallbackEntity_.getData().getCustNbr() != null
           && tplIpDocCallbackEntity_.getData().getCustNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplIpDocCallbackEntity_.getData().getIpDocCode() != null
           && tplIpDocCallbackEntity_.getData().getIpDocCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplIpDocCallbackEntity_.getData().getIpCallbackCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackEntity_.getData().getIpCallbackCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplIpDocCallbackEntity_.getData().getCtcNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackEntity_.getData().getCtcNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
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

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocCallbackDAO#delete(com.citibank.ods.entity.pl.TplIpDocCallbackEntity)
   */
  public void delete( TplIpDocCallbackEntity tplIpDocCallbackEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " UPDATE " + C_TPL_IP_DOC_CALLBACK );
      query.append( " SET " );
      query.append( C_REC_STAT_CODE + "= ?" );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + "= ? AND " );
      query.append( C_IP_DOC_CODE + "= ? AND " );
      query.append( C_IP_CALLBACK_CODE + "= ? AND " );
      query.append( C_CTC_NBR + "= ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString( 1, BaseODSEntity.C_REC_STAT_CODE_INACTIVE );

      preparedStatement.setLong(
                         2,
                         ( ( TplIpDocCallbackEntityVO ) tplIpDocCallbackEntity_.getData() ).getCustNbr().longValue() );

      preparedStatement.setLong(
                         3,
                         ( ( TplIpDocCallbackEntityVO ) tplIpDocCallbackEntity_.getData() ).getIpDocCode().longValue() );

      preparedStatement.setLong(
                         3,
                         ( ( TplIpDocCallbackEntityVO ) tplIpDocCallbackEntity_.getData() ).getIpCallbackCode().longValue() );

      preparedStatement.setLong(
                         4,
                         ( ( TplIpDocCallbackEntityVO ) tplIpDocCallbackEntity_.getData() ).getCtcNbr().longValue() );

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocCallbackDAO#exists(com.citibank.ods.entity.pl.TplIpDocCallbackEntity)
   */
  public boolean exists( TplIpDocCallbackEntity tplIpDocCallbackEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tplIpDocCallbackEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocCallbackDAO#existsActive(com.citibank.ods.entity.pl.TplIpDocCallbackEntity)
   */
  public boolean existsActive( TplIpDocCallbackEntity tplIpDocCallbackEntity_ )
  {
    boolean exists = true;

    try
    {
      TplIpDocCallbackEntity tplIpDocCallbackEntity = ( TplIpDocCallbackEntity ) this.find( tplIpDocCallbackEntity_ );
      TplIpDocCallbackEntityVO tplIpDocCallbackEntityVO = ( TplIpDocCallbackEntityVO ) tplIpDocCallbackEntity.getData();
      if ( !TplIpDocCallbackEntity.C_REC_STAT_CODE_ACTIVE.equals( tplIpDocCallbackEntityVO.getRecStatCode() ) )
      {
        exists = false;
      }
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTplIpDocCallbackDAO#find(com.citibank.ods.entity.pl.BaseTplIpDocCallbackEntity)
   */
  public BaseTplIpDocCallbackEntity find(
                                         BaseTplIpDocCallbackEntity baseTplIpDocCallbackEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    ResultSet resultSet = null;
    ArrayList tplIpDocCallbackEntities;
    BaseTplIpDocCallbackEntity tplIpDocCallbackEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_IP_CALLBACK_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_CTC_NBR + ", " );
      query.append( C_IP_DOC_CODE );
      query.append( " FROM " + C_TPL_IP_DOC_CALLBACK );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + "= ? AND " );
      query.append( C_IP_DOC_CODE + "= ? AND " );
      query.append( C_IP_CALLBACK_CODE + "= ? AND " );
      query.append( C_CTC_NBR + "= ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( baseTplIpDocCallbackEntity_.getData().getCustNbr() != null
           && baseTplIpDocCallbackEntity_.getData().getCustNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           baseTplIpDocCallbackEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( baseTplIpDocCallbackEntity_.getData().getIpDocCode() != null
           && baseTplIpDocCallbackEntity_.getData().getIpDocCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           baseTplIpDocCallbackEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( baseTplIpDocCallbackEntity_.getData().getIpCallbackCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           baseTplIpDocCallbackEntity_.getData().getIpCallbackCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( baseTplIpDocCallbackEntity_.getData().getCtcNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           baseTplIpDocCallbackEntity_.getData().getCtcNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      tplIpDocCallbackEntities = instantiateFromResultSet( resultSet );

      if ( tplIpDocCallbackEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplIpDocCallbackEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplIpDocCallbackEntity = ( BaseTplIpDocCallbackEntity ) tplIpDocCallbackEntities.get( 0 );
      }

      resultSet.close();

    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }

    return tplIpDocCallbackEntity;
  }

  /*
   * Retorna uma coleção de entities a partir do rs
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    TplIpDocCallbackEntity tplIpDocCallbackEntity;
    ArrayList tplIpDocCallbackEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplIpDocCallbackEntity = new TplIpDocCallbackEntity();

        tplIpDocCallbackEntity.getData().setIpCallbackCode(
                                                            new BigInteger(
                                                                            ( resultSet_.getString( C_IP_CALLBACK_CODE ) ) ) );
        tplIpDocCallbackEntity.getData().setLastUpdUserId(
                                                           ( resultSet_.getString( C_LAST_UPD_USER_ID ) ) );
        tplIpDocCallbackEntity.getData().setLastUpdDate(
                                                         ( resultSet_.getTimestamp( C_LAST_UPD_DATE ) ) );
        tplIpDocCallbackEntity.getData().setRecStatCode(
                                                         ( resultSet_.getString( C_REC_STAT_CODE ) ) );
        tplIpDocCallbackEntity.getData().setLastAuthUserId(
                                                            ( resultSet_.getString( C_LAST_AUTH_USER_ID ) ) );
        tplIpDocCallbackEntity.getData().setLastAuthDate(
                                                          ( resultSet_.getTimestamp( C_LAST_AUTH_DATE ) ) );
        tplIpDocCallbackEntity.getData().setCustNbr(
                                                     new BigInteger(
                                                                     ( resultSet_.getString( C_CUST_NBR ) ) ) );
        tplIpDocCallbackEntity.getData().setCtcNbr(
                                                    new BigInteger(
                                                                    ( resultSet_.getString( C_CTC_NBR ) ) ) );
        tplIpDocCallbackEntity.getData().setIpDocCode(
                                                       new BigInteger(
                                                                       ( resultSet_.getString( C_IP_DOC_CODE ) ) ) );

        tplIpDocCallbackEntities.add( tplIpDocCallbackEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return tplIpDocCallbackEntities;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocCallbackDAO#deleteAll(java.math.BigInteger,
   *      java.math.BigInteger)
   */
  public void deleteAll( BigInteger ipDocCode_, BigInteger custNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " UPDATE " + C_TPL_IP_DOC_CALLBACK );
      query.append( " SET " );
      query.append( C_REC_STAT_CODE + "= ?" );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + " = ? AND " );
      query.append( C_IP_DOC_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString( 1, TplIpDocCallbackEntity.C_REC_STAT_CODE_INACTIVE );
      preparedStatement.setLong( 2, custNbr_.longValue() );

      preparedStatement.setLong( 3, ipDocCode_.longValue() );

      preparedStatement.execute();
	  preparedStatement.replaceParametersInQuery(query.toString());
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocCallbackDAO#findByPK(java.math.BigInteger,
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
      query.append( C_IP_CALLBACK_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_CTC_NBR + ", " );
      query.append( C_IP_DOC_CODE );
      query.append( " FROM " + C_TPL_IP_DOC_CALLBACK );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + "= ? AND " );
      query.append( C_IP_DOC_CODE + "= ? AND " );
      query.append( C_REC_STAT_CODE + "<> ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, custNbr_.longValue() );

      preparedStatement.setLong( 2, ipDocCode_.longValue() );

      preparedStatement.setString( 3, TplIpDocCallbackEntity.C_REC_STAT_CODE_INACTIVE );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      ipDocCallbackEntities = instantiateFromResultSet( resultSet );

      resultSet.close();

    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }

    return ipDocCallbackEntities;
  }

  public Integer getNextIpCallBackCode()
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    Integer nextVal = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT PL.SQ_PRMNT_INSTR_CALLBACK_CODE.NEXTVAL FROM DUAL " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.next() )
      {
        nextVal = new Integer( resultSet.getInt( "NEXTVAL" ) );
      }

      return nextVal;
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }
  }

}