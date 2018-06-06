package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.DataSetRow;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplBrokerEntity;
import com.citibank.ods.entity.pl.TplBrokerEntity;
import com.citibank.ods.entity.pl.valueobject.TplBrokerEntityVO;
import com.citibank.ods.persistence.pl.dao.TplBrokerDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * Implementação Oracle para DAO da tabela TPL_BROKER
 * @author Hamilton Matos
 */
public class OracleTplBrokerDAO extends BaseOracleTplBrokerDAO implements
    TplBrokerDAO
{
  private static final String C_TABLE_COLUMNS = "BKR_CNPJ_NBR, BKR_NAME_TEXT, BKR_ADDR_TEXT, BKR_BMF_MKT_CODE, BKR_BOVESPA_MKT_CODE, BKR_RBT_BMF_RATE, BKR_RBT_BOVESPA_RATE, BKR_REQ_DATE, BKR_RNW_DATE, BKR_APPRV_STAT_CODE, BKR_APPRV_DATE, BKR_AUTH_PROC_SIT_TEXT, BKR_REQ_CR_LIM_LCY_AMT, BKR_APPRV_CR_LIM_LCY_AMT, BKR_REQ_CR_LIM_DLR_AMT, BKR_APPRV_CR_LIM_DLR_AMT, BKR_SUPL_SERV_TEXT, BKR_COMMENT_TEXT, LAST_UPD_USER_ID, LAST_UPD_DATE, LAST_AUTH_DATE, LAST_AUTH_USER_ID, REC_STAT_CODE";

  private static final String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private static final String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private static final String C_OPERN_CODE = "OPERN_CODE";

  private static final String C_REC_STAT_CODE = "REC_STAT_CODE";

  private static final String C_BKR_ADDR_TEXT = "BKR_ADDR_TEXT";

  private static final String C_BKR_APPRV_CR_LIM_DLR_AMT = "BKR_APPRV_CR_LIM_DLR_AMT";

  private static final String C_BKR_APPRV_CR_LIM_LCY_AMT = "BKR_APPRV_CR_LIM_LCY_AMT";

  private static final String C_BKR_APPRV_DATE = "BKR_APPRV_DATE";

  private static final String C_BKR_APPRV_STAT_CODE = "BKR_APPRV_STAT_CODE";

  private static final String C_BKR_AUTH_PROC_SIT_TEXT = "BKR_AUTH_PROC_SIT_TEXT";

  private static final String C_BKR_BMF_MKT_CODE = "BKR_BMF_MKT_CODE";

  private static final String C_BKR_BOVESPA_MKT_CODE = "BKR_BOVESPA_MKT_CODE";

  private static final String C_BKR_CNPJ_NBR = "BKR_CNPJ_NBR";

  private static final String C_BKR_COMMENT_TEXT = "BKR_COMMENT_TEXT";

  private static final String C_BKR_NAME_TEXT = "BKR_NAME_TEXT";

  private static final String C_BKR_RBT_BMF_RATE = "BKR_RBT_BMF_RATE";

  private static final String C_BKR_RBT_BOVESPA_RATE = "BKR_RBT_BOVESPA_RATE";

  private static final String C_BKR_REQ_CR_LIM_DLR_AMT = "BKR_REQ_CR_LIM_DLR_AMT";

  private static final String C_BKR_REQ_CR_LIM_LCY_AMT = "BKR_REQ_CR_LIM_LCY_AMT";

  private static final String C_BKR_REQ_DATE = "BKR_REQ_DATE";

  private static final String C_BKR_RNW_DATE = "BKR_RNW_DATE";

  private static final String C_BKR_SUPL_SERV_TEXT = "BKR_SUPL_SERV_TEXT";

  private static final String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  private static final String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  private static final String C_TABLE_NAME = C_PL_SCHEMA + "TPL_BROKER";

  public TplBrokerEntity insert( TplBrokerEntity tplBrokerEntity_ )
                                                                   throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer sqlQuery = new StringBuffer();
    sqlQuery.append( "INSERT INTO " + C_TABLE_NAME + "( " );

    sqlQuery.append( C_BKR_ADDR_TEXT + ", " );
    sqlQuery.append( C_BKR_APPRV_CR_LIM_DLR_AMT + ", " );
    sqlQuery.append( C_BKR_APPRV_CR_LIM_LCY_AMT + ", " );
    sqlQuery.append( C_BKR_APPRV_DATE + ", " );
    sqlQuery.append( C_BKR_APPRV_STAT_CODE + ", " );
    sqlQuery.append( C_BKR_AUTH_PROC_SIT_TEXT + ", " );
    sqlQuery.append( C_BKR_BMF_MKT_CODE + ", " );
    sqlQuery.append( C_BKR_BOVESPA_MKT_CODE + ", " );
    sqlQuery.append( C_BKR_CNPJ_NBR + ", " );
    sqlQuery.append( C_BKR_COMMENT_TEXT + ", " );
    sqlQuery.append( C_BKR_NAME_TEXT + ", " );
    sqlQuery.append( C_BKR_RBT_BMF_RATE + ", " );
    sqlQuery.append( C_BKR_RBT_BOVESPA_RATE + ", " );
    sqlQuery.append( C_BKR_REQ_CR_LIM_DLR_AMT + ", " );
    sqlQuery.append( C_BKR_REQ_CR_LIM_LCY_AMT + ", " );
    sqlQuery.append( C_BKR_REQ_DATE + ", " );
    sqlQuery.append( C_BKR_RNW_DATE + ", " );
    sqlQuery.append( C_BKR_SUPL_SERV_TEXT + ", " );
    sqlQuery.append( C_LAST_UPD_DATE + ", " );
    sqlQuery.append( C_LAST_UPD_USER_ID + ", " );
    sqlQuery.append( C_LAST_AUTH_USER_ID + ", " );
    sqlQuery.append( C_LAST_AUTH_DATE + ", " );
    sqlQuery.append( C_REC_STAT_CODE + ") " );

    sqlQuery.append( " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" );
    try
    {
      connection = OracleODSDAOFactory.getConnection();
      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

      int count = 1;

      if ( tplBrokerEntity_.getData().getBkrAddrText() != null
           && !tplBrokerEntity_.getData().getBkrAddrText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getBkrAddrText() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplBrokerEntity_.getData().getBkrApprvCrLimDlrAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerEntity_.getData().getBkrApprvCrLimDlrAmt() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerEntity_.getData().getBkrApprvCrLimLcyAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerEntity_.getData().getBkrApprvCrLimLcyAmt() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerEntity_.getData().getBkrApprvDate() != null
           && !tplBrokerEntity_.getData().getBkrApprvDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerEntity_.getData().getBkrApprvDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplBrokerEntity_.getData().getBkrApprvStatCode() != null
           && !tplBrokerEntity_.getData().getBkrApprvStatCode().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getBkrApprvStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerEntity_.getData().getBkrAuthProcSitText() != null
           && !tplBrokerEntity_.getData().getBkrAuthProcSitText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getBkrAuthProcSitText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerEntity_.getData().getBkrBmfMktCode() != null
           && !tplBrokerEntity_.getData().getBkrBmfMktCode().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getBkrBmfMktCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplBrokerEntity_.getData().getBkrBovespaMktCode() != null
           && !tplBrokerEntity_.getData().getBkrBovespaMktCode().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getBkrBovespaMktCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplBrokerEntity_.getData().getBkrCnpjNbr() != null
           && !tplBrokerEntity_.getData().getBkrCnpjNbr().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getBkrCnpjNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBrokerEntity_.getData().getBkrCommentText() != null
           && !tplBrokerEntity_.getData().getBkrCommentText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getBkrCommentText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerEntity_.getData().getBkrNameText() != null
           && !tplBrokerEntity_.getData().getBkrNameText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getBkrNameText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerEntity_.getData().getBkrRbtBmfRate() != null )
      {
        preparedStatement.setBigDecimal( count++,
                                 tplBrokerEntity_.getData().getBkrRbtBmfRate() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerEntity_.getData().getBkrRbtBovespaRate() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerEntity_.getData().getBkrRbtBovespaRate() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerEntity_.getData().getBkrReqCrLimDlrAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerEntity_.getData().getBkrReqCrLimDlrAmt() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerEntity_.getData().getBkrReqCrLimLcyAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerEntity_.getData().getBkrReqCrLimLcyAmt() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerEntity_.getData().getBkrReqDate() != null
           && !tplBrokerEntity_.getData().getBkrReqDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerEntity_.getData().getBkrReqDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplBrokerEntity_.getData().getBkrRnwDate() != null
           && !tplBrokerEntity_.getData().getBkrRnwDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerEntity_.getData().getBkrRnwDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplBrokerEntity_.getData().getBkrSuplServText() != null
           && !tplBrokerEntity_.getData().getBkrSuplServText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getBkrSuplServText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerEntity_.getData().getLastUpdDate() != null
           && !tplBrokerEntity_.getData().getLastUpdDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBrokerEntity_.getData().getLastUpdUserId() != null
           && !tplBrokerEntity_.getData().getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      TplBrokerEntityVO tplBrokerEntityVO = ( TplBrokerEntityVO ) tplBrokerEntity_.getData();

      if ( tplBrokerEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++, tplBrokerEntityVO.getLastAuthUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBrokerEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBrokerEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, tplBrokerEntityVO.getRecStatCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

	  preparedStatement.replaceParametersInQuery(sqlQuery.toString());
      preparedStatement.executeUpdate();
	  
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
    return tplBrokerEntity_;

  }

  /**
   * Atualiza uma linha na tabela TPL_BROKER de acordo com ID contido na
   * entidade passada como parâmetro.
   * @param tplBrokerEntity__
   * @throws UnexpectedException
   * @author Hamilton Matos
   */
  public void update( TplBrokerEntity tplBrokerEntity_ )
                                                        throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer sqlQuery = new StringBuffer();
    int rowCount;
    sqlQuery.append( "UPDATE " );
    sqlQuery.append( C_TABLE_NAME );
    sqlQuery.append( " SET " );
    sqlQuery.append( C_BKR_ADDR_TEXT + " = ?, " );
    sqlQuery.append( C_BKR_APPRV_CR_LIM_DLR_AMT + " = ?, " );
    sqlQuery.append( C_BKR_APPRV_CR_LIM_LCY_AMT + " = ?, " );
    sqlQuery.append( C_BKR_APPRV_DATE + " = ?, " );
    sqlQuery.append( C_BKR_APPRV_STAT_CODE + " = ?, " );
    sqlQuery.append( C_BKR_AUTH_PROC_SIT_TEXT + " = ?, " );
    sqlQuery.append( C_BKR_BMF_MKT_CODE + " = ?, " );
    sqlQuery.append( C_BKR_BOVESPA_MKT_CODE + " = ?, " );
    sqlQuery.append( C_BKR_COMMENT_TEXT + " = ?, " );
    sqlQuery.append( C_BKR_NAME_TEXT + " = ?, " );
    sqlQuery.append( C_BKR_RBT_BMF_RATE + " = ?, " );
    sqlQuery.append( C_BKR_RBT_BOVESPA_RATE + " = ?, " );
    sqlQuery.append( C_BKR_REQ_CR_LIM_DLR_AMT + " = ?, " );
    sqlQuery.append( C_BKR_REQ_CR_LIM_LCY_AMT + " = ?, " );
    sqlQuery.append( C_BKR_REQ_DATE + " = ?, " );
    sqlQuery.append( C_BKR_RNW_DATE + " = ?, " );
    sqlQuery.append( C_BKR_SUPL_SERV_TEXT + " = ?, " );
    sqlQuery.append( C_LAST_UPD_DATE + " = ?, " );
    sqlQuery.append( C_LAST_UPD_USER_ID + " = ?, " );

    sqlQuery.append( C_LAST_AUTH_DATE + " = ?, " );
    sqlQuery.append( C_LAST_AUTH_USER_ID + " = ?, " );
    sqlQuery.append( C_REC_STAT_CODE + " = ? " );

    sqlQuery.append( " WHERE " );
    sqlQuery.append( C_BKR_CNPJ_NBR + " = ? " );
    try
    {
      connection = OracleODSDAOFactory.getConnection();
      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

      int count = 1;

      if ( tplBrokerEntity_.getData().getBkrAddrText() != null
           && !tplBrokerEntity_.getData().getBkrAddrText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getBkrAddrText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerEntity_.getData().getBkrApprvCrLimDlrAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerEntity_.getData().getBkrApprvCrLimDlrAmt() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerEntity_.getData().getBkrApprvCrLimLcyAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerEntity_.getData().getBkrApprvCrLimLcyAmt() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerEntity_.getData().getBkrApprvDate() != null
           && !tplBrokerEntity_.getData().getBkrApprvDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerEntity_.getData().getBkrApprvDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplBrokerEntity_.getData().getBkrApprvStatCode() != null
           && !tplBrokerEntity_.getData().getBkrApprvStatCode().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getBkrApprvStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerEntity_.getData().getBkrAuthProcSitText() != null
           && !tplBrokerEntity_.getData().getBkrAuthProcSitText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getBkrAuthProcSitText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerEntity_.getData().getBkrBmfMktCode() != null
           && !tplBrokerEntity_.getData().getBkrBmfMktCode().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getBkrBmfMktCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerEntity_.getData().getBkrBovespaMktCode() != null
           && !tplBrokerEntity_.getData().getBkrBovespaMktCode().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getBkrBovespaMktCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerEntity_.getData().getBkrCommentText() != null
           && !tplBrokerEntity_.getData().getBkrCommentText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getBkrCommentText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerEntity_.getData().getBkrNameText() != null
           && !tplBrokerEntity_.getData().getBkrNameText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getBkrNameText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerEntity_.getData().getBkrRbtBmfRate() != null )
      {
        preparedStatement.setBigDecimal( count++,
                                 tplBrokerEntity_.getData().getBkrRbtBmfRate() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerEntity_.getData().getBkrRbtBovespaRate() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerEntity_.getData().getBkrRbtBovespaRate() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerEntity_.getData().getBkrReqCrLimDlrAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerEntity_.getData().getBkrReqCrLimDlrAmt() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerEntity_.getData().getBkrReqCrLimLcyAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerEntity_.getData().getBkrReqCrLimLcyAmt() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerEntity_.getData().getBkrReqDate() != null
           && !tplBrokerEntity_.getData().getBkrReqDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerEntity_.getData().getBkrReqDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplBrokerEntity_.getData().getBkrRnwDate() != null
           && !tplBrokerEntity_.getData().getBkrRnwDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerEntity_.getData().getBkrRnwDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplBrokerEntity_.getData().getBkrSuplServText() != null
           && !tplBrokerEntity_.getData().getBkrSuplServText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getBkrSuplServText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerEntity_.getData().getLastUpdDate() != null
           && !tplBrokerEntity_.getData().getLastUpdDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBrokerEntity_.getData().getLastUpdUserId() != null
           && !tplBrokerEntity_.getData().getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      TplBrokerEntityVO tplBrokerEntityVO = ( TplBrokerEntityVO ) tplBrokerEntity_.getData();

      if ( tplBrokerEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBrokerEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++, tplBrokerEntityVO.getLastAuthUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBrokerEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, tplBrokerEntityVO.getRecStatCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBrokerEntity_.getData().getBkrCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplBrokerEntity_.getData().getBkrCnpjNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

	  preparedStatement.replaceParametersInQuery(sqlQuery.toString());
      preparedStatement.executeUpdate();
	  
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
   * @see com.citibank.ods.persistence.pl.dao.TplBrokerDAO#delete(java.math.BigInteger)
   */
  public void delete( BigInteger entityKey_ ) throws UnexpectedException
  {
    //
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplBrokerDAO#list(java.util.Date,
   *      java.lang.String, java.lang.String, java.lang.String,
   *      java.lang.String, java.math.BigInteger, java.math.BigInteger,
   *      java.util.Date, java.lang.String, java.lang.String, java.lang.String,
   *      java.lang.String, java.lang.String, java.lang.String,
   *      java.lang.String, java.math.BigInteger, java.math.BigInteger,
   *      java.math.BigInteger, java.math.BigInteger, java.util.Date,
   *      java.util.Date, java.lang.String, java.util.Date, java.lang.String)
   */
//  public DataSet list( Date lastAuthDate_, String lastAuthUserId_,
//                      String opernCode_, String recStatCode_,
//                      String bkrAddrText_, BigDecimal bkrApprvCrLimDlrAmt_,
//                      BigDecimal bkrApprvCrLimLcyAmt_, Date bkrApprvDate_,
//                      String bkrApprvStatCode_, String bkrAuthProcSitText_,
//                      String bkrBmfMktCode_, String bkrBovespaMktCode_,
//                      String bkrCnpjNbr_, String bkrCommentText_,
//                      String bkrNameText_, BigDecimal bkrRbtBmfRate_,
//                      BigDecimal bkrRbtBovespaRate_,
//                      BigDecimal bkrReqCrLimDlrAmt_,
//                      BigDecimal bkrReqCrLimLcyAmt_, Date bkrReqDate_,
//                      Date bkrRnwDate_, String bkrSuplServText_,
//                      Date lastUpdDate_, String lastUpdUserId_ )
//                                                                throws UnexpectedException
//  {
//    ResultSet resultSet = null;
//    ResultSetDataSet resultSetDataSet = null;
//    CitiStatement preparedStatement = null;
//    ManagedRdbConnection connection = null;
//    StringBuffer sqlQuery = new StringBuffer();
//
//    try
//    {
//      connection = OracleODSDAOFactory.getConnection();
//      sqlQuery.append( "SELECT " );
//
//      sqlQuery.append( C_TABLE_COLUMNS );
//      sqlQuery.append( " FROM " );
//      sqlQuery.append( C_TABLE_NAME );
//      sqlQuery.append( "WHERE " );
//
//      if ( lastAuthDate_ != null && !lastAuthDate_.equals( "" ) )
//      {
//        sqlQuery.append( " TRUNC(LAST_AUTH_DATE) = ? " );
//      }
//      if ( lastAuthUserId_ != null && !lastAuthUserId_.equals( "" ) )
//      {
//        sqlQuery.append( " LAST_AUTH_USER_ID = ? " );
//      }
//
//      if ( opernCode_ != null && !opernCode_.equals( "" ) )
//      {
//        sqlQuery.append( " OPERN_CODE = ? " );
//      }
//
//      if ( recStatCode_ != null && !recStatCode_.equals( "" ) )
//      {
//        sqlQuery.append( " REC_STAT_CODE = ? " );
//      }
//
//      if ( bkrAddrText_ != null && !bkrAddrText_.equals( "" ) )
//      {
//        sqlQuery.append( " BKR_ADDR_TEXT = ? AND " );
//      }
//
//      if ( bkrApprvCrLimDlrAmt_ != null && !bkrApprvCrLimDlrAmt_.equals( "" ) )
//      {
//        sqlQuery.append( " BKR_APPRV_CR_LIM_DLR_AMT = ? AND " );
//      }
//
//      if ( bkrApprvCrLimLcyAmt_ != null && !bkrApprvCrLimLcyAmt_.equals( "" ) )
//      {
//        sqlQuery.append( " BKR_APPRV_CR_LIM_LCY_AMT = ? AND " );
//      }
//
//      if ( bkrApprvDate_ != null && !bkrApprvDate_.equals( "" ) )
//      {
//        sqlQuery.append( " BKR_APPRV_DATE = ? AND " );
//      }
//
//      if ( bkrApprvStatCode_ != null && !bkrApprvStatCode_.equals( "" ) )
//      {
//        sqlQuery.append( " BKR_APPRV_STAT_CODE = ? AND " );
//      }
//
//      if ( bkrAuthProcSitText_ != null && !bkrAuthProcSitText_.equals( "" ) )
//      {
//        sqlQuery.append( " BKR_AUTH_PROC_SIT_TEXT = ? AND " );
//      }
//
//      if ( bkrBmfMktCode_ != null && !bkrBmfMktCode_.equals( "" ) )
//      {
//        sqlQuery.append( " BKR_BMF_MKT_CODE = ? AND " );
//      }
//
//      if ( bkrBovespaMktCode_ != null && !bkrBovespaMktCode_.equals( "" ) )
//      {
//        sqlQuery.append( " BKR_BOVESPA_MKT_CODE = ? AND " );
//      }
//
//      if ( bkrCnpjNbr_ != null && !bkrCnpjNbr_.equals( "" ) )
//      {
//        sqlQuery.append( " BKR_CNPJ_NBR = ? AND " );
//      }
//
//      if ( bkrCommentText_ != null && !bkrCommentText_.equals( "" ) )
//      {
//        sqlQuery.append( " BKR_COMMENT_TEXT = ? AND " );
//      }
//
//      if ( bkrNameText_ != null && !bkrNameText_.equals( "" ) )
//      {
//        sqlQuery.append( " BKR_NAME_TEXT = ? AND " );
//      }
//
//      if ( bkrRbtBmfRate_ != null && !bkrRbtBmfRate_.equals( "" ) )
//      {
//        sqlQuery.append( " BKR_RBT_BMF_RATE = ? AND " );
//      }
//
//      if ( bkrRbtBovespaRate_ != null && !bkrRbtBovespaRate_.equals( "" ) )
//      {
//        sqlQuery.append( " BKR_RBT_BOVESPA_RATE = ? AND " );
//      }
//
//      if ( bkrReqCrLimDlrAmt_ != null && !bkrReqCrLimDlrAmt_.equals( "" ) )
//      {
//        sqlQuery.append( " BKR_REQ_CR_LIM_DLR_AMT = ? AND " );
//      }
//
//      if ( bkrReqCrLimLcyAmt_ != null && !bkrReqCrLimLcyAmt_.equals( "" ) )
//      {
//        sqlQuery.append( " BKR_REQ_CR_LIM_LCY_AMT = ? AND " );
//      }
//
//      if ( bkrReqDate_ != null && !bkrReqDate_.equals( "" ) )
//      {
//        sqlQuery.append( " BKR_REQ_DATE = ? AND " );
//      }
//
//      if ( bkrRnwDate_ != null && !bkrRnwDate_.equals( "" ) )
//      {
//        sqlQuery.append( " BKR_RNW_DATE = ? AND " );
//      }
//
//      if ( bkrSuplServText_ != null && !bkrSuplServText_.equals( "" ) )
//      {
//        sqlQuery.append( " BKR_SUPL_SERV_TEXT = ? AND " );
//      }
//
//      if ( lastUpdDate_ != null && !lastUpdDate_.equals( "" ) )
//      {
//        sqlQuery.append( " TRUNC(LAST_UPD_DATE) = ? " );
//      }
//
//      if ( lastUpdUserId_ != null && !lastUpdUserId_.equals( "" ) )
//      {
//        sqlQuery.append( " LAST_UPD_USER_ID = ? " );
//      }
//
//      sqlQuery.append( " ORDER BY BKR_CNPJ_NBR" );
//
//      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));
//
//      int count = 1;
//
//      if ( lastAuthDate_ != null || !lastAuthDate_.equals( "" ) )
//      {
//        preparedStatement.setDate( count++, new java.sql.Date( lastAuthDate_.getTime() ) );
//      }
//      if ( lastAuthUserId_ != null || !lastAuthUserId_.equals( "" ) )
//      {
//        preparedStatement.setString( count++, lastAuthUserId_ );
//      }
//
//      if ( opernCode_ != null || !opernCode_.equals( "" ) )
//      {
//        preparedStatement.setString( count++, opernCode_ );
//      }
//      if ( recStatCode_ != null || !recStatCode_.equals( "" ) )
//      {
//        preparedStatement.setString( count++, recStatCode_ );
//      }
//
//      if ( bkrAddrText_ != null || !bkrAddrText_.equals( "" ) )
//      {
//        preparedStatement.setString( count++, bkrAddrText_ );
//      }
//
//      if ( bkrApprvCrLimDlrAmt_ != null )
//      {
//        preparedStatement.setBigDecimal( count++, bkrApprvCrLimDlrAmt_ );
//      }
//
//      if ( bkrApprvCrLimLcyAmt_ != null )
//      {
//        preparedStatement.setBigDecimal( count++, bkrApprvCrLimLcyAmt_ );
//      }
//
//      if ( bkrApprvDate_ != null || !bkrApprvDate_.equals( "" ) )
//      {
//        preparedStatement.setDate( count++, new java.sql.Date( bkrApprvDate_.getTime() ) );
//      }
//
//      if ( bkrApprvStatCode_ != null || !bkrApprvStatCode_.equals( "" ) )
//      {
//        preparedStatement.setString( count++, bkrApprvStatCode_ );
//      }
//
//      if ( bkrAuthProcSitText_ != null || !bkrAuthProcSitText_.equals( "" ) )
//      {
//        preparedStatement.setString( count++, bkrAuthProcSitText_ );
//      }
//
//      if ( bkrBmfMktCode_ != null || !bkrBmfMktCode_.equals( "" ) )
//      {
//        preparedStatement.setString( count++, bkrBmfMktCode_ );
//      }
//
//      if ( bkrBovespaMktCode_ != null || !bkrBovespaMktCode_.equals( "" ) )
//      {
//        preparedStatement.setString( count++, bkrBovespaMktCode_ );
//      }
//
//      if ( bkrCnpjNbr_ != null || !bkrCnpjNbr_.equals( "" ) )
//      {
//        preparedStatement.setString( count++, bkrCnpjNbr_ );
//      }
//
//      if ( bkrCommentText_ != null || !bkrCommentText_.equals( "" ) )
//      {
//        preparedStatement.setString( count++, bkrCommentText_ );
//      }
//
//      if ( bkrNameText_ != null || !bkrNameText_.equals( "" ) )
//      {
//        preparedStatement.setString( count++, bkrNameText_ );
//      }
//
//      if ( bkrRbtBmfRate_ != null )
//      {
//        preparedStatement.setBigDecimal( count++, bkrRbtBmfRate_ );
//      }
//
//      if ( bkrRbtBovespaRate_ != null )
//      {
//        preparedStatement.setBigDecimal( count++, bkrRbtBovespaRate_ );
//      }
//
//      if ( bkrReqCrLimDlrAmt_ != null )
//      {
//        preparedStatement.setBigDecimal( count++, bkrReqCrLimDlrAmt_ );
//      }
//
//      if ( bkrReqCrLimLcyAmt_ != null )
//      {
//        preparedStatement.setBigDecimal( count++, bkrReqCrLimLcyAmt_ );
//      }
//
//      if ( bkrReqDate_ != null || !bkrReqDate_.equals( "" ) )
//      {
//        preparedStatement.setDate( count++, new java.sql.Date( bkrReqDate_.getTime() ) );
//      }
//
//      if ( bkrRnwDate_ != null || !bkrRnwDate_.equals( "" ) )
//      {
//        preparedStatement.setDate( count++, new java.sql.Date( bkrRnwDate_.getTime() ) );
//      }
//
//      if ( bkrSuplServText_ != null || !bkrSuplServText_.equals( "" ) )
//      {
//        preparedStatement.setString( count++, bkrSuplServText_ );
//      }
//
//      if ( lastUpdDate_ != null || !lastUpdDate_.equals( "" ) )
//      {
//        preparedStatement.setDate( count++, new java.sql.Date( lastUpdDate_.getTime() ) );
//      }
//
//      if ( lastUpdUserId_ != null || !lastUpdUserId_.equals( "" ) )
//      {
//        preparedStatement.setString( count++, lastUpdUserId_ );
//      }
//
//	  preparedStatement.replaceParametersInQuery(sqlQuery.toString());
//      resultSet = preparedStatement.executeQuery();
//	  
//
//      resultSetDataSet = new ResultSetDataSet( resultSet );
//
//      resultSet.close();
//    }
//    catch ( SQLException e )
//    {
//      throw new UnexpectedException( e.getErrorCode(),
//                                     C_ERROR_EXECUTING_STATEMENT, e );
//    }
//    finally
//    {
//      closeStatement( preparedStatement );
//      closeConnection( connection );
//    }
//    return resultSetDataSet;
//  }

  public DataSet list( String bkrCnpjNbr_, String bkrNameText_ )
                                                                throws UnexpectedException
  {
    ResultSet resultSet = null;
    ResultSetDataSet resultSetDataSet = null;
    CitiStatement preparedStatement = null;
    ManagedRdbConnection connection = null;
    StringBuffer sqlQuery = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      sqlQuery.append( "SELECT " );
      sqlQuery.append( C_TABLE_COLUMNS );
      sqlQuery.append( " FROM " );
      sqlQuery.append( C_TABLE_NAME );

      String criteria = "";

      criteria = criteria + C_REC_STAT_CODE + " != '"
                 + BaseTplBrokerEntity.C_REC_STAT_CODE_INACTIVE + "'" + " AND ";

      if ( bkrCnpjNbr_ != null && !bkrCnpjNbr_.equals( "" ) )
      {
        criteria = criteria + C_BKR_CNPJ_NBR + " = ? AND ";
      }

      if ( bkrNameText_ != null && !bkrNameText_.equals( "" ) )
      {
        criteria = criteria + "UPPER(" + C_BKR_NAME_TEXT + ") like ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        sqlQuery.append( " WHERE " + criteria );

      }

      sqlQuery.append( " ORDER BY " + C_BKR_NAME_TEXT);

      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

      int count = 1;

      if ( bkrCnpjNbr_ != null && !"".equals( bkrCnpjNbr_ ) )
      {
        preparedStatement.setString( count++, bkrCnpjNbr_ );
      }

      if ( bkrNameText_ != null && !"".equals( bkrNameText_ ) )
      {
        preparedStatement.setString( count++, "%" + bkrNameText_.toUpperCase() + "%" );
      }

	  preparedStatement.replaceParametersInQuery(sqlQuery.toString());
      resultSet = preparedStatement.executeQuery();

      resultSetDataSet = new ResultSetDataSet( resultSet );

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
    return resultSetDataSet;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTplBrokerDAO#find(com.citibank.ods.entity.pl.BaseTplBrokerEntity)
   */
  public BaseTplBrokerEntity find( BaseTplBrokerEntity baseTplBrokerEntity_ )
                                                                             throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer sqlQuery = new StringBuffer();

    ArrayList tplBrokerEntities;
    BaseTplBrokerEntity tplBrokerEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      sqlQuery.append( "SELECT " );
      sqlQuery.append( C_TABLE_COLUMNS );
      sqlQuery.append( " FROM " );
      sqlQuery.append( C_TABLE_NAME );
      sqlQuery.append( " WHERE " );
      sqlQuery.append( " BKR_CNPJ_NBR = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

      preparedStatement.setString( 1, baseTplBrokerEntity_.getData().getBkrCnpjNbr() );

	  preparedStatement.replaceParametersInQuery(sqlQuery.toString());
      resultSet = preparedStatement.executeQuery();

      tplBrokerEntities = instantiateFromResultSet( resultSet );

      if ( tplBrokerEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplBrokerEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplBrokerEntity = ( BaseTplBrokerEntity ) tplBrokerEntities.get( 0 );
      }

      return tplBrokerEntity;
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
   * Retorna uma coleção de entities a partir do rs
   */

  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    TplBrokerEntity tplBrokerEntity;
    ArrayList tplBrokerEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplBrokerEntity = new TplBrokerEntity();
        tplBrokerEntity.getData().setBkrCnpjNbr(
                                                 resultSet_.getString( C_BKR_CNPJ_NBR ) );

        tplBrokerEntity.getData().setBkrNameText(
                                                  resultSet_.getString( C_BKR_NAME_TEXT ) );

        tplBrokerEntity.getData().setBkrAddrText(
                                                  resultSet_.getString( C_BKR_ADDR_TEXT ) );

        tplBrokerEntity.getData().setBkrBmfMktCode(
                                                    resultSet_.getString( C_BKR_BMF_MKT_CODE ) );

        tplBrokerEntity.getData().setBkrBovespaMktCode(
                                                        resultSet_.getString( C_BKR_BOVESPA_MKT_CODE ) );

        tplBrokerEntity.getData().setBkrRbtBmfRate(
                                                    resultSet_.getString( C_BKR_RBT_BMF_RATE ) != null
                                                                                                      ? new BigDecimal(
                                                                                                                        resultSet_.getString( C_BKR_RBT_BMF_RATE ) )
                                                                                                      : null );

        tplBrokerEntity.getData().setBkrRbtBovespaRate(
                                                        resultSet_.getString( C_BKR_RBT_BOVESPA_RATE ) != null
                                                                                                              ? new BigDecimal(
                                                                                                                                resultSet_.getString( C_BKR_RBT_BOVESPA_RATE ) )
                                                                                                              : null );

        tplBrokerEntity.getData().setBkrReqDate(
                                                 resultSet_.getDate( C_BKR_REQ_DATE ) );

        tplBrokerEntity.getData().setBkrRnwDate(
                                                 resultSet_.getDate( C_BKR_RNW_DATE ) );

        tplBrokerEntity.getData().setBkrApprvStatCode(
                                                       resultSet_.getString( C_BKR_APPRV_STAT_CODE ) );

        tplBrokerEntity.getData().setBkrApprvDate(
                                                   resultSet_.getDate( C_BKR_APPRV_DATE ) );

        tplBrokerEntity.getData().setBkrAuthProcSitText(
                                                         resultSet_.getString( C_BKR_AUTH_PROC_SIT_TEXT ) );

        tplBrokerEntity.getData().setBkrReqCrLimLcyAmt(
                                                        resultSet_.getString( C_BKR_REQ_CR_LIM_LCY_AMT ) != null
                                                                                                                ? new BigDecimal(
                                                                                                                                  resultSet_.getString( C_BKR_REQ_CR_LIM_LCY_AMT ) )
                                                                                                                : null );
        tplBrokerEntity.getData().setBkrApprvCrLimLcyAmt(
                                                          resultSet_.getString( C_BKR_APPRV_CR_LIM_LCY_AMT ) != null
                                                                                                                    ? new BigDecimal(
                                                                                                                                      resultSet_.getString( C_BKR_APPRV_CR_LIM_LCY_AMT ) )
                                                                                                                    : null );
        tplBrokerEntity.getData().setBkrReqCrLimDlrAmt(
                                                        resultSet_.getString( C_BKR_REQ_CR_LIM_DLR_AMT ) != null
                                                                                                                ? new BigDecimal(
                                                                                                                                  resultSet_.getString( C_BKR_REQ_CR_LIM_DLR_AMT ) )
                                                                                                                : null );
        tplBrokerEntity.getData().setBkrApprvCrLimDlrAmt(
                                                          resultSet_.getString( C_BKR_APPRV_CR_LIM_DLR_AMT ) != null
                                                                                                                    ? new BigDecimal(
                                                                                                                                      resultSet_.getString( C_BKR_APPRV_CR_LIM_DLR_AMT ) )
                                                                                                                    : null );
        tplBrokerEntity.getData().setBkrSuplServText(
                                                      resultSet_.getString( C_BKR_SUPL_SERV_TEXT ) );
        tplBrokerEntity.getData().setBkrCommentText(
                                                     resultSet_.getString( C_BKR_COMMENT_TEXT ) );
        tplBrokerEntity.getData().setLastUpdUserId(
                                                    resultSet_.getString( C_LAST_UPD_USER_ID ) );
        tplBrokerEntity.getData().setLastUpdDate(
                                                  resultSet_.getDate( C_LAST_UPD_DATE ) );
        //      Casting para a atribuicao das colunas especificas
        TplBrokerEntityVO tplBrokerEntityVO = ( TplBrokerEntityVO ) tplBrokerEntity.getData();
        tplBrokerEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );
        tplBrokerEntityVO.setLastAuthDate( resultSet_.getDate( C_LAST_AUTH_DATE ) );
        tplBrokerEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );

        tplBrokerEntities.add( tplBrokerEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return tplBrokerEntities;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplBrokerDAO#existsActive(com.citibank.ods.entity.pl.TplBrokerEntity)
   */
  public boolean existsActive( TplBrokerEntity tplBrokerEntity_ )
                                                                 throws UnexpectedException
  {
    boolean exists = true;

    try
    {
      TplBrokerEntity tplBrokerEntity = ( TplBrokerEntity ) this.find( tplBrokerEntity_ );
      TplBrokerEntityVO brokerEntityVO = ( TplBrokerEntityVO ) tplBrokerEntity.getData();
      if ( !TplBrokerEntity.C_REC_STAT_CODE_ACTIVE.equals( brokerEntityVO.getRecStatCode() ) )
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
   * @see com.citibank.ods.persistence.pl.dao.TplBrokerDAO#exists(com.citibank.ods.entity.pl.TplBrokerEntity)
   */
  public boolean exists( TplBrokerEntity tplBrokerEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tplBrokerEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  /**
   * Realiza uma consulta em lista na tabela e cria uma ArrayList de Entities
   * para ser utilizada
   */
  public ArrayList listForBrokerGrid( String bkrCnpjNbr_, String bkrNameText_ )
  {
    TplBrokerEntity tplBrokerEntity;
    TplBrokerEntityVO tplBrokerEntityVO;
    DataSetRow row;

    ArrayList result = new ArrayList();

    DataSet rds = this.list( bkrCnpjNbr_, bkrNameText_ );

    for ( int indexRow = 0; indexRow < rds.size(); indexRow++ )
    {
      tplBrokerEntity = new TplBrokerEntity();
      tplBrokerEntityVO = ( TplBrokerEntityVO ) tplBrokerEntity.getData();

      row = rds.getRow( indexRow );

      tplBrokerEntityVO.setBkrCnpjNbr( row.getStringByName( C_BKR_CNPJ_NBR ) );
      tplBrokerEntityVO.setBkrNameText( row.getStringByName( C_BKR_NAME_TEXT ) );
      tplBrokerEntityVO.setBkrAddrText( row.getStringByName( C_BKR_ADDR_TEXT ) );

      result.add( tplBrokerEntity );
    }

    return result;
  }

}

