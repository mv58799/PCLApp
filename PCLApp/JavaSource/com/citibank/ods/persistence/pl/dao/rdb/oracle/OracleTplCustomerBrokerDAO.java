package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.DataSetRow;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplCustomerBrokerEntity;
import com.citibank.ods.entity.pl.TplCustomerBrokerEntity;
import com.citibank.ods.entity.pl.valueobject.TplCustomerBrokerEntityVO;
import com.citibank.ods.persistence.pl.dao.TplCustomerBrokerDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * Implementação Oracle para DAO da tabela TPL_BROKER
 * @author Hamilton Matos
 */
public class OracleTplCustomerBrokerDAO extends BaseOracleTplCustomerBrokerDAO
    implements TplCustomerBrokerDAO
{
  private static final String C_TABLE_COLUMNS = "BKR_CNPJ_NBR, BKR_NAME_TEXT, BKR_ADDR_TEXT, BKR_BMF_MKT_CODE, BKR_BOVESPA_MKT_CODE, BKR_RBT_BMF_RATE, BKR_RBT_BOVESPA_RATE, BKR_REQ_DATE, BKR_RNW_DATE, BKR_APPRV_STAT_CODE, BKR_APPRV_DATE, BKR_AUTH_PROC_SIT_TEXT, BKR_REQ_CR_LIM_LCY_AMT, BKR_APPRV_CR_LIM_LCY_AMT, BKR_REQ_CR_LIM_DLR_AMT, BKR_APPRV_CR_LIM_DLR_AMT, BKR_SUPL_SERV_TEXT, BKR_COMMENT_TEXT, LAST_UPD_USER_ID, LAST_UPD_DATE, LAST_AUTH_DATE, LAST_AUTH_USER_ID, REC_STAT_CODE";

  private static final String C_CUST_NBR = "CUST_NBR";

  private static final String C_BKR_CNPJ_NBR = "BKR_CNPJ_NBR";

  private static final String C_BKR_CUST_NBR = "BKR_CUST_NBR";

  private static final String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  private static final String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  private static final String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private static final String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private static final String C_REC_STAT_CODE = "REC_STAT_CODE";

  private static final String C_TABLE_CUSTOMER_BROKER = C_PL_SCHEMA
                                                        + "TPL_CUSTOMER_BROKER";

  private static final String C_TABLE_BROKER = C_PL_SCHEMA + "TPL_BROKER";

  private static final String C_TABLE_CUSTOMER_PRVT = C_PL_SCHEMA
                                                      + "TPL_CUSTOMER_PRVT";

  private static final String C_ALIAS_CUSTOMER_PRVT = "CUSTOMER";

  private static final String C_ALIAS_CUSTOMER_BROKER = "CUSTOMERBROKER";

  private static final String C_ALIAS_BROKER = "BROKER";

  public TplCustomerBrokerEntity insert(
                                        TplCustomerBrokerEntity tplCustomerBrokerEntity_ )
                                                                                          throws UnexpectedException
  {
    //    ManagedRdbConnection connection = null;
    //    CitiStatement preparedStatement = null;
    //    ResultSet resultSet = null;
    //    StringBuffer sqlQuery = new StringBuffer();
    //    sqlQuery.append( "INSERT INTO " + C_TABLE_NAME + "( " );
    //
    //    sqlQuery.append( C_BKR_ADDR_TEXT + ", " );
    //    sqlQuery.append( C_BKR_APPRV_CR_LIM_DLR_AMT + ", " );
    //    sqlQuery.append( C_BKR_APPRV_CR_LIM_LCY_AMT + ", " );
    //    sqlQuery.append( C_BKR_APPRV_DATE + ", " );
    //    sqlQuery.append( C_BKR_APPRV_STAT_CODE + ", " );
    //    sqlQuery.append( C_BKR_AUTH_PROC_SIT_TEXT + ", " );
    //    sqlQuery.append( C_BKR_BMF_MKT_CODE + ", " );
    //    sqlQuery.append( C_BKR_BOVESPA_MKT_CODE + ", " );
    //    sqlQuery.append( C_BKR_CNPJ_NBR + ", " );
    //    sqlQuery.append( C_BKR_COMMENT_TEXT + ", " );
    //    sqlQuery.append( C_BKR_NAME_TEXT + ", " );
    //    sqlQuery.append( C_BKR_RBT_BMF_RATE + ", " );
    //    sqlQuery.append( C_BKR_RBT_BOVESPA_RATE + ", " );
    //    sqlQuery.append( C_BKR_REQ_CR_LIM_DLR_AMT + ", " );
    //    sqlQuery.append( C_BKR_REQ_CR_LIM_LCY_AMT + ", " );
    //    sqlQuery.append( C_BKR_REQ_DATE + ", " );
    //    sqlQuery.append( C_BKR_RNW_DATE + ", " );
    //    sqlQuery.append( C_BKR_SUPL_SERV_TEXT + ", " );
    //    sqlQuery.append( C_LAST_UPD_DATE + ", " );
    //    sqlQuery.append( C_LAST_UPD_USER_ID + ", " );
    //    sqlQuery.append( C_LAST_AUTH_USER_ID + ", " );
    //    sqlQuery.append( C_LAST_AUTH_DATE + ", " );
    //    sqlQuery.append( C_REC_STAT_CODE + ") " );
    //
    //    sqlQuery.append( " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
    // ?, ?, ?, ?, ?, ?, ?, ?)" );
    //    try
    //    {
    //      connection = OracleODSDAOFactory.getConnection();
    //      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));
    //
    //      int count = 1;
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrAddrText() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrAddrText().equals( "" ) )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getBkrAddrText() );
    //      }
    //      else
    //      {
    //        preparedStatement.setString( count++, "" );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrApprvCrLimDlrAmt() != null
    // )
    //      {
    //        preparedStatement.setBigDecimal(
    //                                 count++,
    //                                 tplCustomerBrokerEntity_.getData().getBkrApprvCrLimDlrAmt() );
    //      }
    //      else
    //      {
    //        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrApprvCrLimLcyAmt() != null
    // )
    //      {
    //        preparedStatement.setBigDecimal(
    //                                 count++,
    //                                 tplCustomerBrokerEntity_.getData().getBkrApprvCrLimLcyAmt() );
    //      }
    //      else
    //      {
    //        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrApprvDate() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrApprvDate().equals( "" ) )
    //      {
    //        preparedStatement.setTimestamp(
    //                                count++,
    //                                new Timestamp(
    //                                               tplCustomerBrokerEntity_.getData().getBkrApprvDate().getTime() ) );
    //      }
    //      else
    //      {
    //        preparedStatement.setTimestamp( count++, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrApprvStatCode() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrApprvStatCode().equals( "" )
    // )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getBkrApprvStatCode() );
    //      }
    //      else
    //      {
    //        preparedStatement.setString( count++, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrAuthProcSitText() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrAuthProcSitText().equals( ""
    // ) )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getBkrAuthProcSitText() );
    //      }
    //      else
    //      {
    //        preparedStatement.setString( count++, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrBmfMktCode() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrBmfMktCode().equals( "" ) )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getBkrBmfMktCode() );
    //      }
    //      else
    //      {
    //        preparedStatement.setString( count++, null );
    //      }
    //      if ( tplCustomerBrokerEntity_.getData().getBkrBovespaMktCode() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrBovespaMktCode().equals( ""
    // ) )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getBkrBovespaMktCode() );
    //      }
    //      else
    //      {
    //        preparedStatement.setString( count++, null );
    //      }
    //      if ( tplCustomerBrokerEntity_.getData().getBkrCnpjNbr() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrCnpjNbr().equals( "" ) )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getBkrCnpjNbr() );
    //      }
    //      else
    //      {
    //        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrCommentText() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrCommentText().equals( "" ) )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getBkrCommentText() );
    //      }
    //      else
    //      {
    //        preparedStatement.setString( count++, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrNameText() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrNameText().equals( "" ) )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getBkrNameText() );
    //      }
    //      else
    //      {
    //        preparedStatement.setString( count++, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrRbtBmfRate() != null )
    //      {
    //        preparedStatement.setBigDecimal( count++,
    //                                 tplCustomerBrokerEntity_.getData().getBkrRbtBmfRate() );
    //      }
    //      else
    //      {
    //        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrRbtBovespaRate() != null )
    //      {
    //        preparedStatement.setBigDecimal(
    //                                 count++,
    //                                 tplCustomerBrokerEntity_.getData().getBkrRbtBovespaRate() );
    //      }
    //      else
    //      {
    //        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrReqCrLimDlrAmt() != null )
    //      {
    //        preparedStatement.setBigDecimal(
    //                                 count++,
    //                                 tplCustomerBrokerEntity_.getData().getBkrReqCrLimDlrAmt() );
    //      }
    //      else
    //      {
    //        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrReqCrLimLcyAmt() != null )
    //      {
    //        preparedStatement.setBigDecimal(
    //                                 count++,
    //                                 tplCustomerBrokerEntity_.getData().getBkrReqCrLimLcyAmt() );
    //      }
    //      else
    //      {
    //        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrReqDate() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrReqDate().equals( "" ) )
    //      {
    //        preparedStatement.setTimestamp(
    //                                count++,
    //                                new Timestamp(
    //                                               tplCustomerBrokerEntity_.getData().getBkrReqDate().getTime() ) );
    //      }
    //      else
    //      {
    //        preparedStatement.setTimestamp( count++, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrRnwDate() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrRnwDate().equals( "" ) )
    //      {
    //        preparedStatement.setTimestamp(
    //                                count++,
    //                                new Timestamp(
    //                                               tplCustomerBrokerEntity_.getData().getBkrRnwDate().getTime() ) );
    //      }
    //      else
    //      {
    //        preparedStatement.setTimestamp( count++, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrSuplServText() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrSuplServText().equals( "" )
    // )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getBkrSuplServText() );
    //      }
    //      else
    //      {
    //        preparedStatement.setString( count++, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getLastUpdDate() != null
    //           && !tplCustomerBrokerEntity_.getData().getLastUpdDate().equals( "" ) )
    //      {
    //        preparedStatement.setTimestamp(
    //                                count++,
    //                                new Timestamp(
    //                                               tplCustomerBrokerEntity_.getData().getLastUpdDate().getTime() ) );
    //      }
    //      else
    //      {
    //        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getLastUpdUserId() != null
    //           && !tplCustomerBrokerEntity_.getData().getLastUpdUserId().equals( "" ) )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getLastUpdUserId() );
    //      }
    //      else
    //      {
    //        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
    //      }
    //
    //      TplCustomerBrokerEntityVO tplCustomerBrokerEntityVO = (
    // TplCustomerBrokerEntityVO ) tplCustomerBrokerEntity_.getData();
    //
    //      if ( tplCustomerBrokerEntityVO.getLastAuthUserId() != null )
    //      {
    //        preparedStatement.setString( count++,
    // tplCustomerBrokerEntityVO.getLastAuthUserId() );
    //      }
    //      else
    //      {
    //        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntityVO.getLastAuthDate() != null )
    //      {
    //        preparedStatement.setTimestamp(
    //                                count++,
    //                                new Timestamp(
    //                                               tplCustomerBrokerEntityVO.getLastAuthDate().getTime() ) );
    //      }
    //      else
    //      {
    //        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntityVO.getRecStatCode() != null )
    //      {
    //        preparedStatement.setString( count++, tplCustomerBrokerEntityVO.getRecStatCode()
    // );
    //      }
    //      else
    //      {
    //        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
    //      }
    //
    //      preparedStatement.executeUpdate();
    //    }
    //    catch ( SQLException e )
    //    {
    //      throw new UnexpectedException( e.getErrorCode(),
    //                                     C_ERROR_EXECUTING_STATEMENT, e );
    //    }
    //    return tplCustomerBrokerEntity_;
    //

    return null;
  }

  /**
   * Atualiza uma linha na tabela TPL_BROKER de acordo com ID contido na
   * entidade passada como parâmetro.
   * @param tplCustomerBrokerEntity__
   * @throws UnexpectedException
   * @author Hamilton Matos
   */
  public void update( TplCustomerBrokerEntity tplCustomerBrokerEntity_ )
                                                                        throws UnexpectedException
  {
    //    ManagedRdbConnection connection = null;
    //    CitiStatement preparedStatement = null;
    //    ResultSet resultSet = null;
    //    StringBuffer sqlQuery = new StringBuffer();
    //    int rowCount;
    //    sqlQuery.append( "UPDATE " );
    //    sqlQuery.append( C_TABLE_NAME );
    //    sqlQuery.append( " SET " );
    //    sqlQuery.append( C_BKR_ADDR_TEXT + " = ?, " );
    //    sqlQuery.append( C_BKR_APPRV_CR_LIM_DLR_AMT + " = ?, " );
    //    sqlQuery.append( C_BKR_APPRV_CR_LIM_LCY_AMT + " = ?, " );
    //    sqlQuery.append( C_BKR_APPRV_DATE + " = ?, " );
    //    sqlQuery.append( C_BKR_APPRV_STAT_CODE + " = ?, " );
    //    sqlQuery.append( C_BKR_AUTH_PROC_SIT_TEXT + " = ?, " );
    //    sqlQuery.append( C_BKR_BMF_MKT_CODE + " = ?, " );
    //    sqlQuery.append( C_BKR_BOVESPA_MKT_CODE + " = ?, " );
    //    sqlQuery.append( C_BKR_COMMENT_TEXT + " = ?, " );
    //    sqlQuery.append( C_BKR_NAME_TEXT + " = ?, " );
    //    sqlQuery.append( C_BKR_RBT_BMF_RATE + " = ?, " );
    //    sqlQuery.append( C_BKR_RBT_BOVESPA_RATE + " = ?, " );
    //    sqlQuery.append( C_BKR_REQ_CR_LIM_DLR_AMT + " = ?, " );
    //    sqlQuery.append( C_BKR_REQ_CR_LIM_LCY_AMT + " = ?, " );
    //    sqlQuery.append( C_BKR_REQ_DATE + " = ?, " );
    //    sqlQuery.append( C_BKR_RNW_DATE + " = ?, " );
    //    sqlQuery.append( C_BKR_SUPL_SERV_TEXT + " = ?, " );
    //    sqlQuery.append( C_LAST_UPD_DATE + " = ?, " );
    //    sqlQuery.append( C_LAST_UPD_USER_ID + " = ?, " );
    //
    //    sqlQuery.append( C_LAST_AUTH_DATE + " = ?, " );
    //    sqlQuery.append( C_LAST_AUTH_USER_ID + " = ?, " );
    //    sqlQuery.append( C_REC_STAT_CODE + " = ? " );
    //
    //    sqlQuery.append( " WHERE " );
    //    sqlQuery.append( C_BKR_CNPJ_NBR + " = ? " );
    //    try
    //    {
    //      connection = OracleODSDAOFactory.getConnection();
    //      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));
    //
    //      int count = 1;
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrAddrText() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrAddrText().equals( "" ) )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getBkrAddrText() );
    //      }
    //      else
    //      {
    //        preparedStatement.setString( count++, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrApprvCrLimDlrAmt() != null
    // )
    //      {
    //        preparedStatement.setBigDecimal(
    //                                 count++,
    //                                 tplCustomerBrokerEntity_.getData().getBkrApprvCrLimDlrAmt() );
    //      }
    //      else
    //      {
    //        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrApprvCrLimLcyAmt() != null
    // )
    //      {
    //        preparedStatement.setBigDecimal(
    //                                 count++,
    //                                 tplCustomerBrokerEntity_.getData().getBkrApprvCrLimLcyAmt() );
    //      }
    //      else
    //      {
    //        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrApprvDate() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrApprvDate().equals( "" ) )
    //      {
    //        preparedStatement.setTimestamp(
    //                                count++,
    //                                new Timestamp(
    //                                               tplCustomerBrokerEntity_.getData().getBkrApprvDate().getTime() ) );
    //      }
    //      else
    //      {
    //        preparedStatement.setTimestamp( count++, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrApprvStatCode() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrApprvStatCode().equals( "" )
    // )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getBkrApprvStatCode() );
    //      }
    //      else
    //      {
    //        preparedStatement.setString( count++, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrAuthProcSitText() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrAuthProcSitText().equals( ""
    // ) )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getBkrAuthProcSitText() );
    //      }
    //      else
    //      {
    //        preparedStatement.setString( count++, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrBmfMktCode() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrBmfMktCode().equals( "" ) )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getBkrBmfMktCode() );
    //      }
    //      else
    //      {
    //        preparedStatement.setString( count++, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrBovespaMktCode() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrBovespaMktCode().equals( ""
    // ) )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getBkrBovespaMktCode() );
    //      }
    //      else
    //      {
    //        preparedStatement.setString( count++, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrCommentText() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrCommentText().equals( "" ) )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getBkrCommentText() );
    //      }
    //      else
    //      {
    //        preparedStatement.setString( count++, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrNameText() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrNameText().equals( "" ) )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getBkrNameText() );
    //      }
    //      else
    //      {
    //        preparedStatement.setString( count++, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrRbtBmfRate() != null )
    //      {
    //        preparedStatement.setBigDecimal( count++,
    //                                 tplCustomerBrokerEntity_.getData().getBkrRbtBmfRate() );
    //      }
    //      else
    //      {
    //        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrRbtBovespaRate() != null )
    //      {
    //        preparedStatement.setBigDecimal(
    //                                 count++,
    //                                 tplCustomerBrokerEntity_.getData().getBkrRbtBovespaRate() );
    //      }
    //      else
    //      {
    //        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrReqCrLimDlrAmt() != null )
    //      {
    //        preparedStatement.setBigDecimal(
    //                                 count++,
    //                                 tplCustomerBrokerEntity_.getData().getBkrReqCrLimDlrAmt() );
    //      }
    //      else
    //      {
    //        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrReqCrLimLcyAmt() != null )
    //      {
    //        preparedStatement.setBigDecimal(
    //                                 count++,
    //                                 tplCustomerBrokerEntity_.getData().getBkrReqCrLimLcyAmt() );
    //      }
    //      else
    //      {
    //        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrReqDate() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrReqDate().equals( "" ) )
    //      {
    //        preparedStatement.setTimestamp(
    //                                count++,
    //                                new Timestamp(
    //                                               tplCustomerBrokerEntity_.getData().getBkrReqDate().getTime() ) );
    //      }
    //      else
    //      {
    //        preparedStatement.setTimestamp( count++, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrRnwDate() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrRnwDate().equals( "" ) )
    //      {
    //        preparedStatement.setTimestamp(
    //                                count++,
    //                                new Timestamp(
    //                                               tplCustomerBrokerEntity_.getData().getBkrRnwDate().getTime() ) );
    //      }
    //      else
    //      {
    //        preparedStatement.setTimestamp( count++, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrSuplServText() != null
    //           && !tplCustomerBrokerEntity_.getData().getBkrSuplServText().equals( "" )
    // )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getBkrSuplServText() );
    //      }
    //      else
    //      {
    //        preparedStatement.setString( count++, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getLastUpdDate() != null
    //           && !tplCustomerBrokerEntity_.getData().getLastUpdDate().equals( "" ) )
    //      {
    //        preparedStatement.setTimestamp(
    //                                count++,
    //                                new Timestamp(
    //                                               tplCustomerBrokerEntity_.getData().getLastUpdDate().getTime() ) );
    //      }
    //      else
    //      {
    //        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getLastUpdUserId() != null
    //           && !tplCustomerBrokerEntity_.getData().getLastUpdUserId().equals( "" ) )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getLastUpdUserId() );
    //      }
    //      else
    //      {
    //        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
    //      }
    //
    //      TplCustomerBrokerEntityVO tplCustomerBrokerEntityVO = (
    // TplCustomerBrokerEntityVO ) tplCustomerBrokerEntity_.getData();
    //
    //      if ( tplCustomerBrokerEntityVO.getLastAuthDate() != null )
    //      {
    //        preparedStatement.setTimestamp(
    //                                count++,
    //                                new Timestamp(
    //                                               tplCustomerBrokerEntityVO.getLastAuthDate().getTime() ) );
    //      }
    //      else
    //      {
    //        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntityVO.getLastAuthUserId() != null )
    //      {
    //        preparedStatement.setString( count++,
    // tplCustomerBrokerEntityVO.getLastAuthUserId() );
    //      }
    //      else
    //      {
    //        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntityVO.getRecStatCode() != null )
    //      {
    //        preparedStatement.setString( count++, tplCustomerBrokerEntityVO.getRecStatCode()
    // );
    //      }
    //      else
    //      {
    //        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
    //      }
    //
    //      if ( tplCustomerBrokerEntity_.getData().getBkrCnpjNbr() != null )
    //      {
    //        preparedStatement.setString( count++,
    //                             tplCustomerBrokerEntity_.getData().getBkrCnpjNbr() );
    //      }
    //      else
    //      {
    //        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
    //      }
    //
    //      preparedStatement.executeUpdate();
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

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplCustomerBrokerDAO#delete(java.math.BigInteger)
   */
  public void delete( BigInteger entityKey_ ) throws UnexpectedException
  {
    //
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplCustomerBrokerDAO#list(java.util.Date,
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

  public DataSet list( BigInteger custNbr_ ) throws UnexpectedException
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
      sqlQuery.append( C_ALIAS_BROKER + "." + C_BKR_NAME_TEXT + ", " );
      sqlQuery.append( C_ALIAS_BROKER + "." + C_BKR_ADDR_TEXT + ", " );      
      sqlQuery.append( C_ALIAS_CUSTOMER_BROKER + "." + C_CUST_NBR + ", " );
      sqlQuery.append( C_ALIAS_CUSTOMER_BROKER + "." + C_BKR_CNPJ_NBR + ", " );
      sqlQuery.append( C_ALIAS_CUSTOMER_BROKER + "." + C_BKR_CUST_NBR + ", " );
      sqlQuery.append( C_ALIAS_CUSTOMER_BROKER + "." + C_LAST_UPD_USER_ID
                       + ", " );
      sqlQuery.append( C_ALIAS_CUSTOMER_BROKER + "." + C_LAST_UPD_DATE + ", " );
      sqlQuery.append( C_ALIAS_CUSTOMER_BROKER + "." + C_LAST_AUTH_USER_ID
                       + ", " );
      sqlQuery.append( C_ALIAS_CUSTOMER_BROKER + "." + C_LAST_AUTH_DATE + ", " );
      sqlQuery.append( C_ALIAS_CUSTOMER_BROKER + "." + C_REC_STAT_CODE );
      sqlQuery.append( " FROM " );
      sqlQuery.append( C_TABLE_CUSTOMER_PRVT + " " + C_ALIAS_CUSTOMER_PRVT
                       + ", " );
      sqlQuery.append( C_TABLE_CUSTOMER_BROKER + " " + C_ALIAS_CUSTOMER_BROKER
                       + ", " );
      sqlQuery.append( C_TABLE_BROKER + " " + C_ALIAS_BROKER + " " );

      String criteria = "";

      criteria = criteria + C_ALIAS_CUSTOMER_BROKER + "." + C_BKR_CNPJ_NBR
                 + " = " + C_ALIAS_BROKER + "." + C_BKR_CNPJ_NBR + " AND ";
      criteria = criteria + C_ALIAS_CUSTOMER_BROKER + "." + C_CUST_NBR + " = "
                 + C_ALIAS_CUSTOMER_PRVT + "." + C_CUST_NBR + " AND ";
      criteria = criteria + C_ALIAS_CUSTOMER_BROKER + "." + C_REC_STAT_CODE
                 + " <> '"
                 + BaseTplCustomerBrokerEntity.C_REC_STAT_CODE_INACTIVE + "'";

      if ( custNbr_ != null && !custNbr_.equals( "" ) )
      {
        criteria = criteria + " AND " + C_ALIAS_CUSTOMER_PRVT + "."
                   + C_CUST_NBR + " = ? ";
      }

      if ( criteria.length() > 0 )
      {

        sqlQuery.append( " WHERE " + criteria );

      }

      sqlQuery.append( " ORDER BY " + C_ALIAS_CUSTOMER_BROKER + "."
                       + C_BKR_CNPJ_NBR );

      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

      int count = 1;

      if ( custNbr_ != null && !"".equals( custNbr_ ) )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
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
   * @see com.citibank.ods.persistence.pl.dao.BaseTplCustomerBrokerDAO#find(com.citibank.ods.entity.pl.BaseTplCustomerBrokerEntity)
   */
  public BaseTplCustomerBrokerEntity find(
                                          BaseTplCustomerBrokerEntity baseTplCustomerBrokerEntity_ )
                                                                                                    throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer sqlQuery = new StringBuffer();

    ArrayList tplCustomerBrokerEntities;
    BaseTplCustomerBrokerEntity tplCustomerBrokerEntity = null;

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

      preparedStatement.setString(
                           1,
                           baseTplCustomerBrokerEntity_.getData().getBkrCnpjNbr() );

	  preparedStatement.replaceParametersInQuery(sqlQuery.toString());
      resultSet = preparedStatement.executeQuery();
	  

      tplCustomerBrokerEntities = instantiateFromResultSet( resultSet );

      if ( tplCustomerBrokerEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplCustomerBrokerEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplCustomerBrokerEntity = ( BaseTplCustomerBrokerEntity ) tplCustomerBrokerEntities.get( 0 );
      }

      return tplCustomerBrokerEntity;
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
    TplCustomerBrokerEntity tplCustomerBrokerEntity;
    ArrayList tplCustomerBrokerEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplCustomerBrokerEntity = new TplCustomerBrokerEntity();
        tplCustomerBrokerEntity.getData().setBkrCnpjNbr(
                                                         resultSet_.getString( C_BKR_CNPJ_NBR ) );

        //        tplCustomerBrokerEntity.getData().setBkrNameText(
        //                                                  resultSet_.getString( C_BKR_NAME_TEXT ) );
        //
        //        tplCustomerBrokerEntity.getData().setBkrAddrText(
        //                                                  resultSet_.getString( C_BKR_ADDR_TEXT ) );
        //
        //        tplCustomerBrokerEntity.getData().setBkrBmfMktCode(
        //                                                    resultSet_.getString( C_BKR_BMF_MKT_CODE ) );
        //
        //        tplCustomerBrokerEntity.getData().setBkrBovespaMktCode(
        //                                                        resultSet_.getString( C_BKR_BOVESPA_MKT_CODE ) );
        //
        //        tplCustomerBrokerEntity.getData().setBkrRbtBmfRate(
        //                                                    resultSet_.getString( C_BKR_RBT_BMF_RATE ) != null
        //                                                                                                      ? new BigDecimal(
        //                                                                                                                        resultSet_.getString( C_BKR_RBT_BMF_RATE ) )
        //                                                                                                      : null );
        //
        //        tplCustomerBrokerEntity.getData().setBkrRbtBovespaRate(
        //                                                        resultSet_.getString( C_BKR_RBT_BOVESPA_RATE ) != null
        //                                                                                                              ? new BigDecimal(
        //                                                                                                                                resultSet_.getString( C_BKR_RBT_BOVESPA_RATE ) )
        //                                                                                                              : null );
        //
        //        tplCustomerBrokerEntity.getData().setBkrReqDate(
        //                                                 resultSet_.getDate( C_BKR_REQ_DATE ) );
        //
        //        tplCustomerBrokerEntity.getData().setBkrRnwDate(
        //                                                 resultSet_.getDate( C_BKR_RNW_DATE ) );
        //
        //        tplCustomerBrokerEntity.getData().setBkrApprvStatCode(
        //                                                       resultSet_.getString( C_BKR_APPRV_STAT_CODE ) );
        //
        //        tplCustomerBrokerEntity.getData().setBkrApprvDate(
        //                                                   resultSet_.getDate( C_BKR_APPRV_DATE ) );
        //
        //        tplCustomerBrokerEntity.getData().setBkrAuthProcSitText(
        //                                                         resultSet_.getString( C_BKR_AUTH_PROC_SIT_TEXT ) );
        //
        //        tplCustomerBrokerEntity.getData().setBkrReqCrLimLcyAmt(
        //                                                        resultSet_.getString( C_BKR_REQ_CR_LIM_LCY_AMT ) != null
        //                                                                                                                ? new BigDecimal(
        //                                                                                                                                  resultSet_.getString( C_BKR_REQ_CR_LIM_LCY_AMT ) )
        //                                                                                                                : null );
        //        tplCustomerBrokerEntity.getData().setBkrApprvCrLimLcyAmt(
        //                                                          resultSet_.getString( C_BKR_APPRV_CR_LIM_LCY_AMT ) != null
        //                                                                                                                    ? new BigDecimal(
        //                                                                                                                                      resultSet_.getString( C_BKR_APPRV_CR_LIM_LCY_AMT ) )
        //                                                                                                                    : null );
        //        tplCustomerBrokerEntity.getData().setBkrReqCrLimDlrAmt(
        //                                                        resultSet_.getString( C_BKR_REQ_CR_LIM_DLR_AMT ) != null
        //                                                                                                                ? new BigDecimal(
        //                                                                                                                                  resultSet_.getString( C_BKR_REQ_CR_LIM_DLR_AMT ) )
        //                                                                                                                : null );
        //        tplCustomerBrokerEntity.getData().setBkrApprvCrLimDlrAmt(
        //                                                          resultSet_.getString( C_BKR_APPRV_CR_LIM_DLR_AMT ) != null
        //                                                                                                                    ? new BigDecimal(
        //                                                                                                                                      resultSet_.getString( C_BKR_APPRV_CR_LIM_DLR_AMT ) )
        //                                                                                                                    : null );
        //        tplCustomerBrokerEntity.getData().setBkrSuplServText(
        //                                                      resultSet_.getString( C_BKR_SUPL_SERV_TEXT ) );
        //        tplCustomerBrokerEntity.getData().setBkrCommentText(
        //                                                     resultSet_.getString( C_BKR_COMMENT_TEXT ) );
        tplCustomerBrokerEntity.getData().setLastUpdUserId(
                                                            resultSet_.getString( C_LAST_UPD_USER_ID ) );
        tplCustomerBrokerEntity.getData().setLastUpdDate(
                                                          resultSet_.getDate( C_LAST_UPD_DATE ) );
        //      Casting para a atribuicao das colunas especificas
        TplCustomerBrokerEntityVO tplCustomerBrokerEntityVO = ( TplCustomerBrokerEntityVO ) tplCustomerBrokerEntity.getData();
        tplCustomerBrokerEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );
        tplCustomerBrokerEntityVO.setLastAuthDate( resultSet_.getDate( C_LAST_AUTH_DATE ) );
        tplCustomerBrokerEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );

        tplCustomerBrokerEntities.add( tplCustomerBrokerEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return tplCustomerBrokerEntities;

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplCustomerBrokerDAO#existsActive(com.citibank.ods.entity.pl.TplCustomerBrokerEntity)
   */
  public boolean existsActive( TplCustomerBrokerEntity tplCustomerBrokerEntity_ )
                                                                                 throws UnexpectedException
  {
    //    boolean exists = true;
    //
    //    try
    //    {
    //      TplCustomerBrokerEntity tplCustomerBrokerEntity = (
    // TplCustomerBrokerEntity ) this.find( tplCustomerBrokerEntity_ );
    //      TplCustomerBrokerEntityVO brokerEntityVO = ( TplCustomerBrokerEntityVO )
    // tplCustomerBrokerEntity.getData();
    //      if ( !TplCustomerBrokerEntity.C_REC_STAT_CODE_ACTIVE.equals(
    // brokerEntityVO.getRecStatCode() ) )
    //      {
    //        exists = false;
    //      }
    //    }
    //    catch ( NoRowsReturnedException exception )
    //    {
    //      exists = false;
    //    }
    //
    //    return exists;

    return false;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplCustomerBrokerDAO#exists(com.citibank.ods.entity.pl.TplCustomerBrokerEntity)
   */
  public boolean exists( TplCustomerBrokerEntity tplCustomerBrokerEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tplCustomerBrokerEntity_ );
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
  public ArrayList listForCustomerBrokerGrid( String custNbr_ )
  {
    TplCustomerBrokerEntity tplCustomerBrokerEntity;
    TplCustomerBrokerEntityVO tplCustomerBrokerEntityVO;
    DataSetRow row;

    ArrayList result = new ArrayList();

    DataSet rds = this.list( new BigInteger( custNbr_ ) );

    for ( int indexRow = 0; indexRow < rds.size(); indexRow++ )
    {
      tplCustomerBrokerEntity = new TplCustomerBrokerEntity();
      tplCustomerBrokerEntityVO = ( TplCustomerBrokerEntityVO ) tplCustomerBrokerEntity.getData();

      row = rds.getRow( indexRow );

      tplCustomerBrokerEntityVO.setBkrCnpjNbr( row.getStringByName( C_BKR_CNPJ_NBR ) );
      tplCustomerBrokerEntityVO.setBkrNameText( row.getStringByName( C_BKR_NAME_TEXT ) );
      tplCustomerBrokerEntityVO.setBkrAddrText( row.getStringByName( C_BKR_ADDR_TEXT ) );
      tplCustomerBrokerEntityVO.setBkrCustNbr( new BigInteger(
                                                               row.getStringByName( C_BKR_CUST_NBR ) ) );

      result.add( tplCustomerBrokerEntity );
    }

    return result;
  }

}

