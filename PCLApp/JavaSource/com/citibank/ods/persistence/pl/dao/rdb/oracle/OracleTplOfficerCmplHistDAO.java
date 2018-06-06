package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.PreparedStatement;
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
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplOfficerCmplEntity;
import com.citibank.ods.entity.pl.TplOfficerCmplHistoryEntity;
import com.citibank.ods.entity.pl.valueobject.TplOfficerCmplHistoryEntityVO;
import com.citibank.ods.persistence.pl.dao.TplOfficerCmplHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
/**
 * @author bruno.zanetti
 * 
 * DAO da tabela de TPL_OFFICER_CMPL_HIST
 */
public class OracleTplOfficerCmplHistDAO extends BaseOracleTplOfficerCmplDAO
    implements TplOfficerCmplHistDAO
{

  /*
   * Tabela de histórico
   */
  private static final String C_TPL_OFFICER_CMPL_HIST = C_PL_SCHEMA
                                           + "TPL_OFFICER_CMPL_HIST";

  private static final String C_TPL_OFFICER_TYPE = C_PL_SCHEMA + "TPL_OFFICER_TYPE";

  protected static final String C_OFFCR_TYPE_TEXT = "OFFCR_TYPE_TEXT";

  private static final String C_TBG_OFFICER = C_BG_SCHEMA + "TBG_OFFICER";

  /*
   * Campos data de referencia
   */
  private String C_OFFCR_REF_DATE = "OFFCR_REF_DATE";

  /**
   * Realiza a inserção de um registro na tabela
   */
  public TplOfficerCmplHistoryEntity insert(
                                            TplOfficerCmplHistoryEntity officerCmplHistoryEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " );
      query.append( C_TPL_OFFICER_CMPL_HIST );
      query.append( " ( " );
      query.append( C_OFFCR_NBR + ", " );
      query.append( C_OFFCR_TYPE_CODE + ", " );
      query.append( C_OFFCR_INTL_NBR + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_OFFCR_REF_DATE + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE + ") " );
      query.append( "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? ) " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      TplOfficerCmplHistoryEntityVO historyEntityVO = ( TplOfficerCmplHistoryEntityVO ) officerCmplHistoryEntity_.getData();

      int count = 1;

      preparedStatement.setLong( count++, historyEntityVO.getOffcrNbr().longValue() );

      preparedStatement.setString( count++, historyEntityVO.getOffcrTypeCode() );

      
      if (historyEntityVO.getOffcrIntlNbr() == null ){
    	  preparedStatement.setNull( count++,  java.sql.Types.DOUBLE );
      } else {
    	  preparedStatement.setLong( count++, historyEntityVO.getOffcrIntlNbr().longValue() );    	  
      }
      
      

      Date lastUpdDate = historyEntityVO.getLastUpdDate();
      preparedStatement.setTimestamp( count++, new Timestamp( lastUpdDate.getTime() ) );

      preparedStatement.setString( count++, historyEntityVO.getLastUpdUserId() );

      Date offcrRefDate = historyEntityVO.getOffcrRefDate();
      preparedStatement.setTimestamp( count++, new Timestamp( offcrRefDate.getTime() ) );

      preparedStatement.setTimestamp(
                              count++,
                              new Timestamp(
                                             historyEntityVO.getLastAuthDate().getTime() ) );

      preparedStatement.setString( count++, historyEntityVO.getLastAuthUserId() );

      preparedStatement.setString( count++, historyEntityVO.getRecStatCode() );

      preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return officerCmplHistoryEntity_;
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally {
		closeStatement(preparedStatement);
		closeConnection(connection);
	}
  }

  /**
   * Realiza a consulta em lista dos dados de histórico a partir dos critérios
   * de pesquisa passados por parâmetro
   */
  public DataSet list( BigInteger offcrIntnlNbr_, BigInteger offcrNbr_,
                      String offcrTypeCode_, java.util.Date offcrRefDate_ )
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
      query.append( C_TPL_OFFICER_CMPL_HIST + "." + C_OFFCR_TYPE_CODE + ", " );
      query.append( C_TPL_OFFICER_TYPE + "." + C_OFFCR_TYPE_TEXT + ", " );
      query.append( C_TBG_OFFICER + "." + C_OFFCR_NAME_TEXT + ", " );
      query.append( C_TPL_OFFICER_CMPL_HIST + "." + C_OFFCR_INTL_NBR + ", " );
      query.append( C_TPL_OFFICER_CMPL_HIST + "." + C_LAST_UPD_DATE + ", " );
      query.append( C_TPL_OFFICER_CMPL_HIST + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( C_TPL_OFFICER_CMPL_HIST + "." + C_LAST_AUTH_DATE + ", " );
      query.append( C_TPL_OFFICER_CMPL_HIST + "." + C_OFFCR_NBR + ", " );
      query.append( C_TPL_OFFICER_CMPL_HIST + "." + C_LAST_AUTH_USER_ID + ", " );
      query.append( C_TPL_OFFICER_CMPL_HIST + "." + C_REC_STAT_CODE + ", " );
      query.append( C_TPL_OFFICER_CMPL_HIST + "." + C_OFFCR_REF_DATE + " " );
      query.append( " FROM " );
      query.append( C_TPL_OFFICER_CMPL_HIST + "," + C_TPL_OFFICER_TYPE + ","
                    + C_TBG_OFFICER );

      String criteria = "";

      criteria = criteria + C_TPL_OFFICER_CMPL_HIST + "." + C_OFFCR_TYPE_CODE
                 + " = " + C_TPL_OFFICER_TYPE + "." + C_OFFCR_TYPE_CODE
                 + " AND ";

      criteria = criteria + C_TPL_OFFICER_CMPL_HIST + "." + C_OFFCR_NBR + " = "
                 + C_TBG_OFFICER + "." + C_OFFCR_NBR + " AND ";

      if ( offcrIntnlNbr_ != null && offcrIntnlNbr_.longValue() != 0 )
      {
        criteria = criteria + C_TPL_OFFICER_CMPL_HIST + "." + C_OFFCR_INTL_NBR
                   + " = ? AND ";
      }
      if ( offcrNbr_ != null && offcrNbr_.longValue() != 0 )
      {
        criteria = criteria + C_TPL_OFFICER_CMPL_HIST + "." + C_OFFCR_NBR
                   + " = ? AND ";
      }
      if ( offcrTypeCode_ != null && !offcrTypeCode_.equals( "" ) )
      {
        criteria = criteria + C_TPL_OFFICER_CMPL_HIST + "." + C_OFFCR_TYPE_CODE
                   + " = ? AND ";
      }
      if ( offcrRefDate_ != null )
      {
        criteria = criteria + C_TPL_OFFICER_CMPL_HIST + "." + C_OFFCR_REF_DATE
                   + " >= ? AND ";
      }
      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria );
      }

      query.append( " ORDER BY " + C_OFFCR_NAME_TEXT );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( offcrIntnlNbr_ != null && offcrIntnlNbr_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, offcrIntnlNbr_.longValue() );
      }
      if ( offcrNbr_ != null && offcrNbr_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, offcrNbr_.longValue() );
      }
      if ( offcrTypeCode_ != null && !offcrTypeCode_.equals( "" ) )
      {
        preparedStatement.setString( count++, offcrTypeCode_ );
      }
      if ( offcrRefDate_ != null )
      {
        preparedStatement.setDate( count++, new java.sql.Date( offcrRefDate_.getTime() ) );

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

  /**
   * Recupera um registro no banco de dados
   */
  public BaseTplOfficerCmplEntity find( BaseTplOfficerCmplEntity officerEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList tplOfficerCmplEntities;
    BaseTplOfficerCmplEntity officerCmplEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_OFFCR_TYPE_CODE + ", " );
      query.append( C_OFFCR_REF_DATE + ", " );
      query.append( C_OFFCR_INTL_NBR + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_OFFCR_NBR + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE + " " );
      query.append( " FROM " );
      query.append( C_TPL_OFFICER_CMPL_HIST );
      query.append( " WHERE " );
      query.append( C_OFFCR_NBR + " = ? AND " );
      query.append( C_OFFCR_REF_DATE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      if ( officerEntity_.getData().getOffcrNbr() != null )
      {
        preparedStatement.setLong( 1,
                           officerEntity_.getData().getOffcrNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( ( ( TplOfficerCmplHistoryEntityVO ) officerEntity_.getData() ).getOffcrRefDate() != null )
      {
        preparedStatement.setTimestamp(
                                2,
                                new Timestamp(
                                               ( ( TplOfficerCmplHistoryEntityVO ) officerEntity_.getData() ).getOffcrRefDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());
	  

      tplOfficerCmplEntities = instantiateFromResultSet( resultSet );

      if ( tplOfficerCmplEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplOfficerCmplEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        officerCmplEntity = ( BaseTplOfficerCmplEntity ) tplOfficerCmplEntities.get( 0 );
      }

      return officerCmplEntity;
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally {
		closeStatement(preparedStatement);
		closeConnection(connection);
	}
  }

  /*
   * Recupera uma coleção a partir do DataSet retornado pela consulta
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    TplOfficerCmplHistoryEntity tplOfficerCmplHistoryEntity;
    TplOfficerCmplHistoryEntityVO tplOfficerCmplHistoryEntityVO;
    Timestamp lastUpdDateTs;
    Timestamp lastAuthDateTs;
    Timestamp offcrRefDateTs;
    ArrayList oracleTplOfficerCmplEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplOfficerCmplHistoryEntity = new TplOfficerCmplHistoryEntity();
        tplOfficerCmplHistoryEntityVO = ( TplOfficerCmplHistoryEntityVO ) tplOfficerCmplHistoryEntity.getData();
        lastUpdDateTs = resultSet_.getTimestamp( this.C_LAST_UPD_DATE );
        tplOfficerCmplHistoryEntityVO.setLastUpdDate( new Date(
                                                                lastUpdDateTs.getTime() ) );

        tplOfficerCmplHistoryEntityVO.setLastUpdUserId( resultSet_.getString( this.C_LAST_UPD_USER_ID ) );
        tplOfficerCmplHistoryEntityVO.setOffcrIntlNbr( new BigInteger(
                                                                       resultSet_.getString( this.C_OFFCR_INTL_NBR ) ) );
        tplOfficerCmplHistoryEntityVO.setOffcrNbr( new BigInteger(
                                                                   resultSet_.getString( this.C_OFFCR_NBR ) ) );
        tplOfficerCmplHistoryEntityVO.setOffcrTypeCode( resultSet_.getString( this.C_OFFCR_TYPE_CODE ) );

        tplOfficerCmplHistoryEntityVO.setRecStatCode( resultSet_.getString( this.C_REC_STAT_CODE ) );

        lastAuthDateTs = resultSet_.getTimestamp( this.C_LAST_AUTH_DATE );
        tplOfficerCmplHistoryEntityVO.setLastAuthDate( new Date(
                                                                 lastAuthDateTs.getTime() ) );

        tplOfficerCmplHistoryEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );

        offcrRefDateTs = resultSet_.getTimestamp( this.C_OFFCR_REF_DATE );
        tplOfficerCmplHistoryEntityVO.setOffcrRefDate( new Date(
                                                                 offcrRefDateTs.getTime() ) );

        oracleTplOfficerCmplEntities.add( tplOfficerCmplHistoryEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return oracleTplOfficerCmplEntities;
  }
}