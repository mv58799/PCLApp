/*
 * Created on Mar 2, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplClassCmplcEntity;
import com.citibank.ods.entity.pl.TplClassCmplcEntity;
import com.citibank.ods.persistence.pl.dao.TplClassCmplcDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
/**
 * @author gerson.a.rodrigues
 *  
 */
public class OracleTplClassCmplcDAO extends BaseOracleTplClassCmplcDAO
    implements TplClassCmplcDAO
{

  private static final String C_TPL_CLASS_CMPLC = C_PL_SCHEMA + "TPL_CLASS_CMPLC";

  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {

    TplClassCmplcEntity tplClassCmplcEntity;
    ArrayList oracleTplClassCmplcEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplClassCmplcEntity = new TplClassCmplcEntity();
        tplClassCmplcEntity.getData().setLastUpdDate(
                                                      resultSet_.getDate( this.C_LAST_UPD_DATE ) );
        tplClassCmplcEntity.getData().setLastUpdUserId(
                                                        resultSet_.getString( this.C_LAST_UPD_USER_ID ) );
        tplClassCmplcEntity.getData().setClassCmplcCode(
                                                         new BigInteger(
                                                                         resultSet_.getString( this.C_CLASS_CMPLC_CODE ) ) );
        tplClassCmplcEntity.getData().setClassCmplcText(
                                                         resultSet_.getString( this.C_CLASS_CMPLC_TEXT ) );
        tplClassCmplcEntity.getData().setSensInd(
                                                  resultSet_.getString( this.C_SENS_IND ) );
        tplClassCmplcEntity.getData().setRecStatCode(
                                                      resultSet_.getString( this.C_REC_STAT_CODE ) );

        oracleTplClassCmplcEntities.add( tplClassCmplcEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return oracleTplClassCmplcEntities;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseClassCmplcDAO#find(com.citibank.ods.entity.pl.BaseTplClassCmplcEntity)
   */
  public BaseTplClassCmplcEntity find( BaseTplClassCmplcEntity classCmplcEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList tplClassCmplcEntities;
    BaseTplClassCmplcEntity classCmplcEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_CLASS_CMPLC_CODE + ", " );
      query.append( C_CLASS_CMPLC_TEXT + ", " );
      query.append( C_SENS_IND + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_REC_STAT_CODE + " " );
      query.append( " FROM " );
      query.append( C_TPL_CLASS_CMPLC );
      query.append( " WHERE " );
      query.append( C_CLASS_CMPLC_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         classCmplcEntity_.getData().getClassCmplcCode().longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      tplClassCmplcEntities = instantiateFromResultSet( resultSet );

      if ( tplClassCmplcEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplClassCmplcEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        classCmplcEntity = ( BaseTplClassCmplcEntity ) tplClassCmplcEntities.get( 0 );
      }

      return classCmplcEntity;
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
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplClassCmplcDAO#list(java.math.BigInteger,
   *      java.lang.String)
   */
  public DataSet list( BigInteger classCmplcCode_, String classCmplcText_,
                      String sensInd_ )
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
      query.append( C_CLASS_CMPLC_CODE + ", " );
      query.append( C_CLASS_CMPLC_TEXT + ", " );
      query.append( C_SENS_IND + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_REC_STAT_CODE + " " );
      query.append( " FROM " );
      query.append( C_TPL_CLASS_CMPLC );
      query.append( " WHERE " );

      String criteria = "";

      criteria = criteria + C_REC_STAT_CODE + " != '"
                 + BaseTplClassCmplcEntity.C_REC_STAT_CODE_INACTIVE + "'"
                 + " AND ";

      if ( classCmplcCode_ != null && classCmplcCode_.longValue() != 0 )
      {
        criteria = criteria + C_CLASS_CMPLC_CODE + " = ? AND ";
      }
      if ( classCmplcText_ != null && classCmplcText_ != "" )
      {
        criteria = criteria + "UPPER(\"" + C_CLASS_CMPLC_TEXT
                   + "\") like ? AND ";
      }
      if ( sensInd_ != null && sensInd_ != "" )
      {
        criteria = criteria + "UPPER(\"" + C_SENS_IND + "\") like ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        criteria = criteria + " ORDER BY " + C_CLASS_CMPLC_TEXT;
        query.append( criteria );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( classCmplcCode_ != null && classCmplcCode_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, classCmplcCode_.longValue() );
      }
      if ( classCmplcText_ != null && classCmplcText_ != "" )
      {
        preparedStatement.setString( count++, "%" + classCmplcText_.toUpperCase() + "%" );
      }
      if ( sensInd_ != null && sensInd_ != "" )
      {
        preparedStatement.setString( count++, "%" + sensInd_.toUpperCase() + "%" );
      }

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

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplClassCmplcDAO#update(com.citibank.ods.entity.pl.TplOfficerCmplEntity)
   */
  public void update( TplClassCmplcEntity classCmplcCurrentEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_CLASS_CMPLC + " SET " );
      query.append( C_CLASS_CMPLC_CODE + " = ?, " );
      query.append( C_CLASS_CMPLC_TEXT + " = ?, " );
      query.append( C_SENS_IND + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( classCmplcCurrentEntity_.getData().getClassCmplcCode() != null
           && classCmplcCurrentEntity_.getData().getClassCmplcCode().longValue() != 0 )
      {
        preparedStatement.setLong(
                           count++,
                           classCmplcCurrentEntity_.getData().getClassCmplcCode().longValue() );
      }
      if ( classCmplcCurrentEntity_.getData().getClassCmplcText() != null
           && classCmplcCurrentEntity_.getData().getClassCmplcText() != "" )
      {
        preparedStatement.setString(
                             count++,
                             classCmplcCurrentEntity_.getData().getClassCmplcText() );
      }
      if ( classCmplcCurrentEntity_.getData().getSensInd() != null )
      {
        preparedStatement.setString( count++,
                             classCmplcCurrentEntity_.getData().getSensInd() );
      }
      if ( classCmplcCurrentEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               classCmplcCurrentEntity_.getData().getLastUpdDate().getTime() ) );
      }
      if ( classCmplcCurrentEntity_.getData().getLastUpdUserId() != null
           && classCmplcCurrentEntity_.getData().getLastUpdUserId() != "" )
      {
        preparedStatement.setString(
                             count++,
                             classCmplcCurrentEntity_.getData().getLastUpdUserId() );
      }

      preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());
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
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplClassCmplcDAO#delete(com.citibank.ods.entity.pl.TplOfficerCmplEntity)
   */
  public void delete( TplClassCmplcEntity classCmplcEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();
    
    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_CLASS_CMPLC );
      query.append( " SET " + C_REC_STAT_CODE + " = ? " );
      query.append( " WHERE " );
      query.append( C_CLASS_CMPLC_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( classCmplcEntity_.getData().getRecStatCode() != null
           && classCmplcEntity_.getData().getRecStatCode() != "" )
      {
        preparedStatement.setString( count++,
                             classCmplcEntity_.getData().getRecStatCode() );
      }

      if ( classCmplcEntity_.getData().getClassCmplcCode() != null
           && classCmplcEntity_.getData().getClassCmplcCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           classCmplcEntity_.getData().getClassCmplcCode().longValue() );
      }

      preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

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
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplClassCmplcDAO#insert(com.citibank.ods.entity.pl.TplOfficerCmplEntity)
   */
  public TplClassCmplcEntity insert( TplClassCmplcEntity classCmplcEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_CLASS_CMPLC + " (" );
      query.append( C_CLASS_CMPLC_CODE + ", " );
      query.append( C_CLASS_CMPLC_TEXT + ", " );
      query.append( C_SENS_IND + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_REC_STAT_CODE + " ) " );
      query.append( " VALUES ( " );
      query.append( " ?," );
      query.append( " ?," );
      query.append( " ?," );
      query.append( " ?," );
      query.append( " ?," );
      query.append( " ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( classCmplcEntity_.getData().getClassCmplcCode() != null
           && classCmplcEntity_.getData().getClassCmplcCode().longValue() != 0 )
      {
        preparedStatement.setLong(
                           count++,
                           classCmplcEntity_.getData().getClassCmplcCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }
      if ( classCmplcEntity_.getData().getClassCmplcText() != null
           && classCmplcEntity_.getData().getClassCmplcText() != "" )
      {
        preparedStatement.setString( count++,
                             classCmplcEntity_.getData().getClassCmplcText() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }
      if ( classCmplcEntity_.getData().getSensInd() != null )
      {
        preparedStatement.setString( count++, classCmplcEntity_.getData().getSensInd() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }
      if ( classCmplcEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               classCmplcEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }
      if ( classCmplcEntity_.getData().getLastUpdUserId() != null
           && classCmplcEntity_.getData().getLastUpdUserId() != "" )
      {
        preparedStatement.setString( count++,
                             classCmplcEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }
      if ( classCmplcEntity_.getData().getRecStatCode() != null
           && classCmplcEntity_.getData().getRecStatCode() != "" )
      {
        preparedStatement.setString( count++,
                             classCmplcEntity_.getData().getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

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

    return classCmplcEntity_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplClassCmplcDAO#loadDomain()
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
      query.append( C_CLASS_CMPLC_CODE + ", " );
      query.append( C_CLASS_CMPLC_TEXT + " " );
      query.append( " FROM " );
      query.append( C_TPL_CLASS_CMPLC );
      query.append( " WHERE " );
      query.append( C_REC_STAT_CODE + " <> ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++, C_STATUS_INATIVO );

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

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplClassCmplcDAO#exists(com.citibank.ods.entity.pl.TplClassCmplcEntity)
   */
  public boolean exists( TplClassCmplcEntity classCmplcEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( classCmplcEntity_ );
    }
    catch ( NoRowsReturnedException e )
    {
      exists = false;
    }

    return exists;
  }
}