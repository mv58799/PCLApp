package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.Globals;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplLoadProcExecEntity;
import com.citibank.ods.entity.pl.TplLoadProcExecEntity;
import com.citibank.ods.entity.pl.valueobject.TplLoadProcExecEntityVO;
import com.citibank.ods.persistence.pl.dao.TplLoadProcExecDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Implementação da interface para acesso ao banco de dados de Execução de
 * Processos de Carga.
 */
public class OracleTplLoadProcExecDAO extends BaseOracleTplLoadProcExecDAO
    implements TplLoadProcExecDAO
{
  private static final String C_TPL_LOAD_PROC_EXEC = C_PL_SCHEMA + "TPL_LOAD_PROC_EXEC";

  private static final String C_TPL_LOAD_PROCESS = C_PL_SCHEMA + "TPL_LOAD_PROCESS";

  private static final String C_TPL_LOAD_PROC_EXEC_COL_REJC = C_PL_SCHEMA
                                                 + "TPL_LOAD_PROC_EXEC_COL_REJC";

  private static final String C_TPL_ENTRY_INTER = C_PL_SCHEMA + "TPL_ENTRY_INTER";

  private static final String C_TPL_ENTRY_INTER_COL = C_PL_SCHEMA + "TPL_ENTRY_INTER_COLUMN";

  private static final String C_TPL_LOGIC_CRIT = C_PL_SCHEMA + "TPL_LOGIC_CRIT";

  private static final String C_TPL_COL_LOGIC_CRIT = C_PL_SCHEMA + "TPL_COL_LOGIC_CRIT";

  private String C_DATA_TYPE_TEXT = "DATA_TYPE_TEXT";

  /**
   * Retorna Data Set com os campos do grid da consulta em lista.
   * 
   * @param loadProcNbr - Código do processo.
   * @param loadProcText - Descrição do processo.
   * @param execRefDate - Data de refência de execução do processo de carga.
   * @return DataSet - Data Set com os campos do grid da consulta em lista.
   */
  public DataSet list( BigInteger loadProcNbr_, String loadProcText_,
                      Date execRefDate_ )
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
      query.append( "EXE." + C_LOAD_PROC_NBR + ", " );
      query.append( "PROC." + C_LOAD_PROC_TEXT + ", " );
      query.append( "EXE." + C_EXEC_SEQ_NBR + ", " );
      query.append( "EXE." + C_EXEC_REF_DATE + ", " );
      query.append( "PROC." + C_LAST_EXEC_DATE );
      query.append( " FROM " );
      query.append( C_TPL_LOAD_PROC_EXEC + " EXE ," );
      query.append( C_TPL_LOAD_PROCESS + " PROC " );
      query.append( " WHERE " );
      query.append( "PROC." + C_LOAD_PROC_NBR + " = " );
      query.append( "EXE." + C_LOAD_PROC_NBR );

      String criteria = "";
      if ( loadProcNbr_ != null )
      {
        criteria = criteria + "PROC." + C_LOAD_PROC_NBR + " = ? AND ";
      }

      if ( loadProcText_ != null && !loadProcText_.equals( "" ) )
      {
        criteria = criteria + "UPPER(\"" + C_LOAD_PROC_TEXT + "\") LIKE ? AND ";
      }

      if ( execRefDate_ != null )
      {
        criteria = criteria + "TRUNC (" + "EXE." + C_EXEC_REF_DATE
                   + ", \'DD\') >= ? AND ";
      }
      if ( criteria.length() > 0 )
      {
        query.append( "	AND " + criteria.substring( 0, criteria.length() - 5 ) );
      }

      query.append( " ORDER BY " + C_LOAD_PROC_NBR );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( loadProcNbr_ != null )
      {
        preparedStatement.setLong( count++, loadProcNbr_.longValue() );
      }
      if ( loadProcText_ != null && !loadProcText_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + loadProcText_.toUpperCase() + "%" );
      }

      if ( execRefDate_ != null )
      {
        preparedStatement.setDate( count, new java.sql.Date( execRefDate_.getTime() ) );
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
   * Retorna uma entidade com os campos de detalhes.
   * 
   * @param loadProcExecEntity_ - Entidade que possui a chave usada para busca
   *          na tabela.
   * @return BaseTplLoadProcExecEntity - Entidade com os campos de detalhes.
   */
  public BaseTplLoadProcExecEntity find(
                                        BaseTplLoadProcExecEntity loadProcExecEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList loadProcExecEntities;
    BaseTplLoadProcExecEntity loadProcExecEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( "PROC_EXEC." + C_EXEC_END_DATE + ", " );
      query.append( "PROC_EXEC." + C_EXEC_PER_SEC_QTY + ", " );
      query.append( "PROC_EXEC." + C_EXEC_REF_DATE + ", " );
      query.append( "PROC_EXEC." + C_EXEC_SEQ_NBR + ", " );
      query.append( "PROC_EXEC." + C_INP_REC_QTY + ", " );
      query.append( "PROC_EXEC." + C_LAST_UPD_DATE + ", " );
      query.append( "PROC_EXEC." + C_LAST_UPD_USER_ID + ", " );
      query.append( "PROC_EXEC." + C_LOAD_EXEC_SIT_CODE + ", " );
      query.append( "PROC_EXEC." + C_LOAD_PROC_NBR + ", " );
      query.append( "PROC_EXEC." + C_MNL_EXEC_IND + ", " );
      query.append( "PROC_EXEC." + C_READ_REC_QTY + ", " );
      query.append( "PROC_EXEC." + C_REJC_REC_QTY + ", " );
      query.append( "PROC_EXEC." + C_UPD_REC_QTY + ", " );
      query.append( "PROC." + C_LOAD_PROC_TEXT );
      query.append( " FROM " );
      query.append( C_TPL_LOAD_PROC_EXEC + " PROC_EXEC, " );
      query.append( C_TPL_LOAD_PROCESS + " PROC " );
      query.append( " WHERE " );
      query.append( "PROC_EXEC." + C_LOAD_PROC_NBR + " = " + "PROC."
                    + C_LOAD_PROC_NBR );
      query.append( " AND " );
      query.append( "PROC_EXEC." + C_LOAD_PROC_NBR + " = ?" );
      query.append( " AND " );
      query.append( "PROC_EXEC." + C_EXEC_REF_DATE + " = ?" );
      query.append( " AND " );
      query.append( "PROC_EXEC." + C_EXEC_SEQ_NBR + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         loadProcExecEntity_.getData().getLoadProcNbr().longValue() );

      preparedStatement.setTimestamp(
                              2,
                              new Timestamp(
                                             loadProcExecEntity_.getData().getExecRefDate().getTime() ) );

      preparedStatement.setLong(
                         3,
                         loadProcExecEntity_.getData().getExecSeqNbr().longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      loadProcExecEntities = instantiateFromResultSet( resultSet );

      if ( loadProcExecEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( loadProcExecEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        loadProcExecEntity = ( BaseTplLoadProcExecEntity ) loadProcExecEntities.get( 0 );
      }

      return loadProcExecEntity;
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

  /**
   * Instancia entidades a partir do ResultSet.
   * 
   * @param resultSet_ - ResultSet utilizado para instanciar entidades.
   * @return ArrayList - Entidades instanciadas a partir do ResultSet.
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    TplLoadProcExecEntity tplLoadProcExecEntity;
    TplLoadProcExecEntityVO tplLoadProcExecEntityVO;
    ArrayList oracleTplLoadProcExecEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplLoadProcExecEntity = new TplLoadProcExecEntity();
        tplLoadProcExecEntityVO = ( TplLoadProcExecEntityVO ) tplLoadProcExecEntity.getData();

        tplLoadProcExecEntityVO.setExecEndDate( parseDateTime( resultSet_.getString( this.C_EXEC_END_DATE ) ) );
        tplLoadProcExecEntityVO.setExecPerSecQty( new BigInteger(
                                                                  resultSet_.getString( this.C_EXEC_PER_SEC_QTY ) ) );
        tplLoadProcExecEntityVO.setExecRefDate( parseDateTime( resultSet_.getString( this.C_EXEC_REF_DATE ) ) );
        tplLoadProcExecEntityVO.setExecSeqNbr( new BigInteger(
                                                               resultSet_.getString( this.C_EXEC_SEQ_NBR ) ) );
        tplLoadProcExecEntityVO.setInpRecQty( new BigInteger(
                                                              resultSet_.getString( this.C_INP_REC_QTY ) ) );
        tplLoadProcExecEntityVO.setLastUpdDate( parseDateTime( resultSet_.getString( this.C_LAST_UPD_DATE ) ) );
        tplLoadProcExecEntityVO.setLastUpdUserId( resultSet_.getString( this.C_LAST_UPD_USER_ID ) );
        tplLoadProcExecEntityVO.setLoadExecSitCode( new BigInteger(
                                                                    resultSet_.getString( this.C_LOAD_EXEC_SIT_CODE ) ) );
        tplLoadProcExecEntityVO.setLoadProcNbr( new BigInteger(
                                                                resultSet_.getString( this.C_LOAD_PROC_NBR ) ) );
        tplLoadProcExecEntityVO.setLoadProcText( resultSet_.getString( this.C_LOAD_PROC_TEXT ) );

        tplLoadProcExecEntityVO.setMnlExecInd( resultSet_.getString( this.C_MNL_EXEC_IND ) );
        tplLoadProcExecEntityVO.setReadRecQty( new BigInteger(
                                                               resultSet_.getString( this.C_READ_REC_QTY ) ) );
        tplLoadProcExecEntityVO.setRejcRecQty( new BigInteger(
                                                               resultSet_.getString( this.C_REJC_REC_QTY ) ) );
        tplLoadProcExecEntityVO.setUpdRecQty( new BigInteger(
                                                              resultSet_.getString( this.C_UPD_REC_QTY ) ) );

        oracleTplLoadProcExecEntities.add( tplLoadProcExecEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return oracleTplLoadProcExecEntities;
  }

  /**
   * Cria uma data a partir de um TimeStamp.
   * 
   * @param date_ - A data a ser convertida - TimeStamp
   * @return Date - A data criada.
   */
  private Date parseDateTime( String date_ )
  {
    SimpleDateFormat formatter = new SimpleDateFormat(
                                                       Globals.FuncionalityFormatKeys.C_FORMAT_TIMESTAMP );

    Date date = null;
    try
    {
      date = ( date_ != null && date_.length() > 0 ? formatter.parse( date_ )
                                                  : null );
    }
    catch ( ParseException e )
    {
      throw new UnexpectedException( "Error parsing input field.", e );
    }

    return date;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLoadProcExecDAO#findLoadProcText(java.math.BigInteger)
   */
  public String findLoadProcText( BigInteger loadProcNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_LOAD_PROC_TEXT );
      query.append( " FROM " );
      query.append( C_TPL_LOAD_PROCESS );
      query.append( " WHERE " );
      query.append( C_LOAD_PROC_NBR + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, loadProcNbr_.longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.next() )
      {
        return resultSet.getString( C_LOAD_PROC_TEXT );
      }
      return "";
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

  public DataSet listLogicCriteria( BigInteger loadProcNbr_,
                                   BigInteger execSeqNbr_, Date execRefDate_ )
  {

    ResultSet resultSet;
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSetEntry = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      // Constrói consulta das entradas das consultas que montam o grid
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( "LOGIC_CRIT_CODE" + ", " );
      query.append( "ENTRY_INTER_CODE" + ", " );
      query.append( "COL_NAME" );
      query.append( " FROM " );
      query.append( C_TPL_LOAD_PROC_EXEC_COL_REJC );
      query.append( " WHERE " );
      query.append( C_LOAD_PROC_NBR + " = ? AND " );
      query.append( C_EXEC_REF_DATE + " = ? AND " );
      query.append( C_EXEC_SEQ_NBR + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, loadProcNbr_.longValue() );
      preparedStatement.setTimestamp( 2, new Timestamp( execRefDate_.getTime() ) );
      preparedStatement.setLong( 3, execSeqNbr_.longValue() );

      resultSetEntry = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      // Constrói consulta do grid
      query = new StringBuffer();
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );

      query.append( "INTER.ENTRY_INTER_TEXT" + ", " );
      query.append( "COL.COL_NAME" + ", " );
      query.append( "COL.COL_SIZE" + ", " );
      query.append( "COL.COL_DATA_TYPE_CODE AS DATA_TYPE_CODE" + ", " );
      query.append( "LOGIC.LOGIC_CRIT_TEXT" );

      query.append( " FROM " );

      query.append( C_TPL_ENTRY_INTER + " INTER, " );
      query.append( C_TPL_ENTRY_INTER_COL + " COL, " );
      query.append( C_TPL_LOGIC_CRIT + " LOGIC, " );
      query.append( C_TPL_COL_LOGIC_CRIT + " COLXLOGIC " );

      query.append( " WHERE " );

      query.append( "INTER.ENTRY_INTER_CODE" + " = COL.ENTRY_INTER_CODE AND " );
      query.append( "INTER.ENTRY_INTER_CODE" + " = ? AND " );
      query.append( "COL.COL_NAME" + " = ? AND " );
      query.append( "LOGIC.LOGIC_CRIT_CODE" + " = ? AND " );
      query.append( "COLXLOGIC.LOGIC_CRIT_CODE" + " = ? AND " );
      query.append( "COLXLOGIC.ENTRY_INTER_CODE" + " = ? AND " );
      query.append( "COLXLOGIC.COL_NAME" + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      while ( resultSetEntry.next() )
      {
        preparedStatement.setLong( 1, resultSetEntry.getInt( "ENTRY_INTER_CODE" ) );
        preparedStatement.setString( 2, resultSetEntry.getString( "COL_NAME" ) );
        preparedStatement.setLong( 3, resultSetEntry.getInt( "LOGIC_CRIT_CODE" ) );
        preparedStatement.setLong( 4, resultSetEntry.getInt( "LOGIC_CRIT_CODE" ) );
        preparedStatement.setLong( 5, resultSetEntry.getInt( "ENTRY_INTER_CODE" ) );
        preparedStatement.setString( 6, resultSetEntry.getString( "COL_NAME" ) );

        resultSet = preparedStatement.executeQuery();
		preparedStatement.replaceParametersInQuery(query.toString());

        if ( rsds == null )
        {
          rsds = new ResultSetDataSet( resultSet );

        }
        else
        {
          rsds.append( new ResultSetDataSet( resultSet ) );
          String[] codeColumn = { "DATA_TYPE_CODE" };
          String[] nameColumn = { C_DATA_TYPE_TEXT };

          rsds.outerJoin( ODSConstraintDecoder.decodeDataType(), codeColumn,
                          codeColumn, nameColumn );

        }
      }

      resultSetEntry.close();
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
}