package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplCustomerBrokerEntity;
import com.citibank.ods.entity.pl.TplCustomerBrokerMovEntity;
import com.citibank.ods.persistence.pl.dao.TplCustomerBrokerMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * Implementação Oracle para DAO da tabela TPL_BROKERMov
 * @author Hamilton Matos
 */
public class OracleTplCustomerBrokerMovDAO extends BaseOracleTplCustomerBrokerDAO implements
    TplCustomerBrokerMovDAO
{
  private static final String C_TABLE_COLUMNS = "OPERN_CODE,BKR_ADDR_TEXT,BKR_APPRV_CR_LIM_DLR_AMT,BKR_APPRV_CR_LIM_LCY_AMT,BKR_APPRV_DATE,BKR_APPRV_STAT_CODE,BKR_AUTH_PROC_SIT_TEXT,BKR_BMF_MKT_CODE,BKR_BOVESPA_MKT_CODE,BKR_CNPJ_NBR,BKR_COMMENT_TEXT,BKR_NAME_TEXT,BKR_RBT_BMF_RATE,BKR_RBT_BOVESPA_RATE,BKR_REQ_CR_LIM_DLR_AMT,BKR_REQ_CR_LIM_LCY_AMT,BKR_REQ_DATE,BKR_RNW_DATE,BKR_SUPL_SERV_TEXT,LAST_UPD_DATE,LAST_UPD_USER_ID";

  private static final String C_OPERN_CODE = "OPERN_CODE";

  private static final String C_OPERN_TEXT = "OPERN_TEXT";

  private static final String C_TABLE_NAME = C_PL_SCHEMA + "TPL_BROKER_MOV";

  /**
   * Insere uma nova linha na tabela TPL_BROKERMov com os dados da entidade
   * correspondente passada como parametro
   * @param tplBrokerMovEntity__
   * @throws UnexpectedException
   * @author Hamilton Matos
   */
  public TplCustomerBrokerMovEntity insert( TplCustomerBrokerMovEntity tplCustomerBrokerMovEntity_ )
                                                                            throws UnexpectedException
  {
//    ManagedRdbConnection connection = null;
//    CitiStatement preparedStatement = null;
//    ResultSet resultSet = null;
//    StringBuffer sqlQuery = new StringBuffer();
//    sqlQuery.append( "INSERT INTO " + C_TABLE_NAME + "( " );
//    sqlQuery.append( C_OPERN_CODE + ", " );
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
//    sqlQuery.append( C_LAST_UPD_USER_ID + ") " );
//
//    sqlQuery.append( " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" );
//    try
//    {
//      connection = OracleODSDAOFactory.getConnection();
//      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));
//
//      TplCustomerBrokerMovEntityVO tplCustomerBrokerMovEntityVO = ( TplCustomerBrokerMovEntityVO ) tplCustomerBrokerMovEntity_.getData();
//
//      int count = 1;
//
//      if ( tplCustomerBrokerMovEntityVO.getOpernCode() != null
//           && !tplCustomerBrokerMovEntityVO.getOpernCode().equals( "" ) )
//      {
//        preparedStatement.setString(
//                             count++,
//                             ( ( TplCustomerBrokerMovEntityVO ) tplCustomerBrokerMovEntity_.getData() ).getOpernCode() );
//      }
//      else
//      {
//        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrAddrText() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrAddrText().equals( "" ) )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerMovEntity_.getData().getBkrAddrText() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, "" );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrApprvCrLimDlrAmt() != null )
//      {
//        preparedStatement.setBigDecimal(
//                                 count++,
//                                 tplCustomerBrokerMovEntity_.getData().getBkrApprvCrLimDlrAmt() );
//      }
//      else
//      {
//        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrApprvCrLimLcyAmt() != null )
//      {
//        preparedStatement.setBigDecimal(
//                                 count++,
//                                 tplCustomerBrokerMovEntity_.getData().getBkrApprvCrLimLcyAmt() );
//      }
//      else
//      {
//        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrApprvDate() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrApprvDate().equals( "" ) )
//      {
//        preparedStatement.setTimestamp(
//                                count++,
//                                new Timestamp(
//                                               tplCustomerBrokerMovEntity_.getData().getBkrApprvDate().getTime() ) );
//      }
//      else
//      {
//        preparedStatement.setTimestamp( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrApprvStatCode() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrApprvStatCode().equals( "" ) )
//      {
//        preparedStatement.setString(
//                             count++,
//                             tplCustomerBrokerMovEntity_.getData().getBkrApprvStatCode() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrAuthProcSitText() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrAuthProcSitText().equals( "" ) )
//      {
//        preparedStatement.setString(
//                             count++,
//                             tplCustomerBrokerMovEntity_.getData().getBkrAuthProcSitText() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrBmfMktCode() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrBmfMktCode().equals( "" ) )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerMovEntity_.getData().getBkrBmfMktCode() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrBovespaMktCode() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrBovespaMktCode().equals( "" ) )
//      {
//        preparedStatement.setString(
//                             count++,
//                             tplCustomerBrokerMovEntity_.getData().getBkrBovespaMktCode() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrCnpjNbr() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrCnpjNbr().equals( "" ) )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerMovEntity_.getData().getBkrCnpjNbr() );
//      }
//      else
//      {
//        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrCommentText() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrCommentText().equals( "" ) )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerMovEntity_.getData().getBkrCommentText() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrNameText() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrNameText().equals( "" ) )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerMovEntity_.getData().getBkrNameText() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrRbtBmfRate() != null )
//      {
//        preparedStatement.setBigDecimal(
//                                 count++,
//                                 tplCustomerBrokerMovEntity_.getData().getBkrRbtBmfRate() );
//      }
//      else
//      {
//        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrRbtBovespaRate() != null )
//      {
//        preparedStatement.setBigDecimal(
//                                 count++,
//                                 tplCustomerBrokerMovEntity_.getData().getBkrRbtBovespaRate() );
//      }
//      else
//      {
//        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrReqCrLimDlrAmt() != null )
//      {
//        preparedStatement.setBigDecimal(
//                                 count++,
//                                 tplCustomerBrokerMovEntity_.getData().getBkrReqCrLimDlrAmt() );
//      }
//      else
//      {
//        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrReqCrLimLcyAmt() != null )
//      {
//        preparedStatement.setBigDecimal(
//                                 count++,
//                                 tplCustomerBrokerMovEntity_.getData().getBkrReqCrLimLcyAmt() );
//      }
//      else
//      {
//        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrReqDate() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrReqDate().equals( "" ) )
//      {
//        preparedStatement.setTimestamp(
//                                count++,
//                                new Timestamp(
//                                               tplCustomerBrokerMovEntity_.getData().getBkrReqDate().getTime() ) );
//      }
//      else
//      {
//        preparedStatement.setTimestamp( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrRnwDate() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrRnwDate().equals( "" ) )
//      {
//        preparedStatement.setTimestamp(
//                                count++,
//                                new Timestamp(
//                                               tplCustomerBrokerMovEntity_.getData().getBkrRnwDate().getTime() ) );
//      }
//      else
//      {
//        preparedStatement.setTimestamp( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrSuplServText() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrSuplServText().equals( "" ) )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerMovEntity_.getData().getBkrSuplServText() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getLastUpdDate() != null
//           && !tplCustomerBrokerMovEntityVO.getLastUpdDate().equals( "" ) )
//      {
//        preparedStatement.setTimestamp(
//                                count++,
//                                new Timestamp(
//                                               tplCustomerBrokerMovEntity_.getData().getLastUpdDate().getTime() ) );
//      }
//      else
//      {
//        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getLastUpdUserId() != null
//           && !tplCustomerBrokerMovEntityVO.getLastUpdUserId().equals( "" ) )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerMovEntity_.getData().getLastUpdUserId() );
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
//    return tplCustomerBrokerMovEntity_;
    
    return null;
  }

  /**
   * Atualiza uma linha na tabela TPL_BROKER_Mov de acordo com ID contido na
   * entidade passada como parâmetro.
   * @param tplCustomerBrokerMovEntity__
   * @throws UnexpectedException
   * @author Hamilton Matos
   */
  public TplCustomerBrokerMovEntity update( TplCustomerBrokerMovEntity tplCustomerBrokerMovEntity_ )
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
//    sqlQuery.append( C_OPERN_CODE + " = ?, " );
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
//    sqlQuery.append( C_LAST_UPD_USER_ID + " = ? " );
//
//    sqlQuery.append( " WHERE " );
//    sqlQuery.append( C_BKR_CNPJ_NBR + " = ? " );
//    try
//    {
//      connection = OracleODSDAOFactory.getConnection();
//      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));
//
//      TplCustomerBrokerMovEntityVO tplCustomerBrokerMovEntityVO = ( TplCustomerBrokerMovEntityVO ) tplCustomerBrokerMovEntity_.getData();
//
//      int count = 1;
//
//      if ( tplCustomerBrokerMovEntityVO.getOpernCode() != null
//           && !tplCustomerBrokerMovEntityVO.getOpernCode().equals( "" ) )
//      {
//        preparedStatement.setString(
//                             count++,
//                             ( ( TplCustomerBrokerMovEntityVO ) tplCustomerBrokerMovEntity_.getData() ).getOpernCode() );
//      }
//      else
//      {
//        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrAddrText() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrAddrText().equals( "" ) )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerMovEntity_.getData().getBkrAddrText() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrApprvCrLimDlrAmt() != null )
//      {
//        preparedStatement.setBigDecimal(
//                                 count++,
//                                 tplCustomerBrokerMovEntity_.getData().getBkrApprvCrLimDlrAmt() );
//      }
//      else
//      {
//        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrApprvCrLimLcyAmt() != null )
//      {
//        preparedStatement.setBigDecimal(
//                                 count++,
//                                 tplCustomerBrokerMovEntity_.getData().getBkrApprvCrLimLcyAmt() );
//      }
//      else
//      {
//        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrApprvDate() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrApprvDate().equals( "" ) )
//      {
//        preparedStatement.setTimestamp(
//                                count++,
//                                new Timestamp(
//                                               tplCustomerBrokerMovEntity_.getData().getBkrApprvDate().getTime() ) );
//      }
//      else
//      {
//        preparedStatement.setTimestamp( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrApprvStatCode() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrApprvStatCode().equals( "" ) )
//      {
//        preparedStatement.setString(
//                             count++,
//                             tplCustomerBrokerMovEntity_.getData().getBkrApprvStatCode() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrAuthProcSitText() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrAuthProcSitText().equals( "" ) )
//      {
//        preparedStatement.setString(
//                             count++,
//                             tplCustomerBrokerMovEntity_.getData().getBkrAuthProcSitText() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrBmfMktCode() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrBmfMktCode().equals( "" ) )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerMovEntity_.getData().getBkrBmfMktCode() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrBovespaMktCode() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrBovespaMktCode().equals( "" ) )
//      {
//        preparedStatement.setString(
//                             count++,
//                             tplCustomerBrokerMovEntity_.getData().getBkrBovespaMktCode() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrCommentText() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrCommentText().equals( "" ) )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerMovEntity_.getData().getBkrCommentText() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrNameText() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrNameText().equals( "" ) )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerMovEntity_.getData().getBkrNameText() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrRbtBmfRate() != null )
//      {
//        preparedStatement.setBigDecimal(
//                                 count++,
//                                 tplCustomerBrokerMovEntity_.getData().getBkrRbtBmfRate() );
//      }
//      else
//      {
//        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrRbtBovespaRate() != null )
//      {
//        preparedStatement.setBigDecimal(
//                                 count++,
//                                 tplCustomerBrokerMovEntity_.getData().getBkrRbtBovespaRate() );
//      }
//      else
//      {
//        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrReqCrLimDlrAmt() != null )
//      {
//        preparedStatement.setBigDecimal(
//                                 count++,
//                                 tplCustomerBrokerMovEntity_.getData().getBkrReqCrLimDlrAmt() );
//      }
//      else
//      {
//        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrReqCrLimLcyAmt() != null )
//      {
//        preparedStatement.setBigDecimal(
//                                 count++,
//                                 tplCustomerBrokerMovEntity_.getData().getBkrReqCrLimLcyAmt() );
//      }
//      else
//      {
//        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrReqDate() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrReqDate().equals( "" ) )
//      {
//        preparedStatement.setTimestamp(
//                                count++,
//                                new Timestamp(
//                                               tplCustomerBrokerMovEntity_.getData().getBkrReqDate().getTime() ) );
//      }
//      else
//      {
//        preparedStatement.setTimestamp( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrRnwDate() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrRnwDate().equals( "" ) )
//      {
//        preparedStatement.setTimestamp(
//                                count++,
//                                new Timestamp(
//                                               tplCustomerBrokerMovEntity_.getData().getBkrRnwDate().getTime() ) );
//      }
//      else
//      {
//        preparedStatement.setTimestamp( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrSuplServText() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrSuplServText().equals( "" ) )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerMovEntity_.getData().getBkrSuplServText() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getLastUpdDate() != null
//           && !tplCustomerBrokerMovEntityVO.getLastUpdDate().equals( "" ) )
//      {
//        preparedStatement.setTimestamp(
//                                count++,
//                                new Timestamp(
//                                               tplCustomerBrokerMovEntity_.getData().getLastUpdDate().getTime() ) );
//      }
//      else
//      {
//        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getLastUpdUserId() != null
//           && !tplCustomerBrokerMovEntityVO.getLastUpdUserId().equals( "" ) )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerMovEntity_.getData().getLastUpdUserId() );
//      }
//      else
//      {
//        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
//      }
//
//      if ( tplCustomerBrokerMovEntityVO.getBkrCnpjNbr() != null
//           && !tplCustomerBrokerMovEntityVO.getBkrCnpjNbr().equals( "" ) )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerMovEntity_.getData().getBkrCnpjNbr() );
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
//    return tplCustomerBrokerMovEntity_;

    return null;
  }

  /**
   * Remove uma linha na tabela TPL_BROKERMov de acordo com ID passado como
   * parâmetro.
   * @param entityKey_
   * @throws UnexpectedException
   * @author Hamilton Matos
   */

  public TplCustomerBrokerMovEntity delete( TplCustomerBrokerMovEntity tplCustomerBrokerMovEntity_ )
                                                                            throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer sqlQuery = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      sqlQuery.append( "DELETE FROM " );
      sqlQuery.append( C_TABLE_NAME );
      sqlQuery.append( " WHERE " );
      sqlQuery.append( C_BKR_CNPJ_NBR + " = ? " );
      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));
      preparedStatement.setString( 1, tplCustomerBrokerMovEntity_.getData().getBkrCnpjNbr() );
	  preparedStatement.replaceParametersInQuery(sqlQuery.toString());
      preparedStatement.executeUpdate();
	  
      return tplCustomerBrokerMovEntity_;
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

  /**
   * Procura um registro ou um conjunto de registros, uma linha na tabela
   * TPL_BROKERMov de acordo com os parametos relacionados a opções de buscas na
   * tela. Este método deve ser utilizado para consulta em lista com filtros.
   * @param StringopernCode_ StringbkrAddrText_ BigIntegerbkrApprvCrLimDlrAmt_
   *          BigIntegerbkrApprvCrLimLcyAmt_ DatebkrApprvDate_
   *          StringbkrApprvStatCode_ StringbkrAuthProcSitText_
   *          StringbkrBmfMktCode_ StringbkrBovespaMktCode_ StringbkrCnpjNbr_
   *          StringbkrCommentText_ StringbkrNameText_ BigIntegerbkrRbtBmfRate_
   *          BigIntegerbkrRbtBovespaRate_ BigIntegerbkrReqCrLimDlrAmt_
   *          BigIntegerbkrReqCrLimLcyAmt_ DatebkrReqDate_ DatebkrRnwDate_
   *          StringbkrSuplServText_ DatelastUpdDate_ StringlastUpdUserId_*
   * @returns dataSet_
   * @throws UnexpectedException
   * @author Hamilton Matos
   */

//  public DataSet list( String opernCode_, String bkrAddrText_,
//                      BigDecimal bkrApprvCrLimDlrAmt_,
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
//
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
//      if ( opernCode_ != null && !opernCode_.equals( "" ) )
//      {
//        sqlQuery.append( " OPERN_CODE = ? " );
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
//      if ( opernCode_ != null || !opernCode_.equals( "" ) )
//      {
//        preparedStatement.setString( count++, opernCode_ );
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
//        preparedStatement.setDate( count++, bkrApprvDate_ );
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
//        preparedStatement.setDate( count++, bkrReqDate_ );
//      }
//
//      if ( bkrRnwDate_ != null || !bkrRnwDate_.equals( "" ) )
//      {
//        preparedStatement.setDate( count++, bkrRnwDate_ );
//      }
//
//      if ( bkrSuplServText_ != null || !bkrSuplServText_.equals( "" ) )
//      {
//        preparedStatement.setString( count++, bkrSuplServText_ );
//      }
//
//      if ( lastUpdDate_ != null || !lastUpdDate_.equals( "" ) )
//      {
//        preparedStatement.setDate( count++, lastUpdDate_ );
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

  /**
   * Cria uma entidade representando um registro da tabela TPL_BROKERMov com os
   * dados do ID passado como parametro
   * @param entityKey_
   * @throws UnexpectedException
   * @author Hamilton Matos
   */

  public BaseTplCustomerBrokerEntity find( BaseTplCustomerBrokerEntity tplCustomerBrokerEntity_ )
                                                                         throws UnexpectedException
  {
    ResultSet resultSet = null;
    CitiStatement preparedStatement = null;
    ManagedRdbConnection connection = null;
    StringBuffer sqlQuery = new StringBuffer();
    ArrayList tplCustomerBrokerMovEntities = new ArrayList();
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

      preparedStatement.setString( 1, tplCustomerBrokerEntity_.getData().getBkrCnpjNbr() );

	  preparedStatement.replaceParametersInQuery(sqlQuery.toString());
      resultSet = preparedStatement.executeQuery();
	  

      tplCustomerBrokerMovEntities = instantiateFromResultSet( resultSet );

      if ( tplCustomerBrokerMovEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplCustomerBrokerMovEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplCustomerBrokerEntity = ( BaseTplCustomerBrokerEntity ) tplCustomerBrokerMovEntities.get( 0 );
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

  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
                                                                    throws UnexpectedException
  {
//    TplCustomerBrokerMovEntityVO tplCustomerBrokerMovEntityVO;
//    TplCustomerBrokerMovEntity tplCustomerBrokerMovEntity;
//    ArrayList tplCustomerBrokerMovEntities = new ArrayList();
//    try
//    {
//      while ( resultSet_.next() )
//      {
//
//        tplCustomerBrokerMovEntity = new TplCustomerBrokerMovEntity();
//
//        tplCustomerBrokerMovEntity.getData().setBkrAddrText(
//                                                     resultSet_.getString( C_BKR_ADDR_TEXT ) );
//
//        if ( resultSet_.getString( C_BKR_APPRV_CR_LIM_DLR_AMT ) != null )
//        {
//          tplCustomerBrokerMovEntity.getData().setBkrApprvCrLimDlrAmt(
//                                                               new BigDecimal(
//                                                                               resultSet_.getString( C_BKR_APPRV_CR_LIM_DLR_AMT ) ) );
//        }
//        else
//        {
//          tplCustomerBrokerMovEntity.getData().setBkrApprvCrLimDlrAmt( null );
//        }
//
//        if ( resultSet_.getString( C_BKR_APPRV_CR_LIM_LCY_AMT ) != null )
//        {
//          tplCustomerBrokerMovEntity.getData().setBkrApprvCrLimLcyAmt(
//                                                               new BigDecimal(
//                                                                               resultSet_.getString( C_BKR_APPRV_CR_LIM_LCY_AMT ) ) );
//        }
//        else
//        {
//          tplCustomerBrokerMovEntity.getData().setBkrApprvCrLimLcyAmt( null );
//
//        }
//        tplCustomerBrokerMovEntity.getData().setBkrApprvDate(
//                                                      resultSet_.getDate( C_BKR_APPRV_DATE ) );
//
//        tplCustomerBrokerMovEntity.getData().setBkrApprvStatCode(
//                                                          resultSet_.getString( C_BKR_APPRV_STAT_CODE ) );
//
//        tplCustomerBrokerMovEntity.getData().setBkrAuthProcSitText(
//                                                            resultSet_.getString( C_BKR_AUTH_PROC_SIT_TEXT ) );
//
//        tplCustomerBrokerMovEntity.getData().setBkrBmfMktCode(
//                                                       resultSet_.getString( C_BKR_BMF_MKT_CODE ) );
//
//        tplCustomerBrokerMovEntity.getData().setBkrBovespaMktCode(
//                                                           resultSet_.getString( C_BKR_BOVESPA_MKT_CODE ) );
//
//        tplCustomerBrokerMovEntity.getData().setBkrCnpjNbr(
//                                                    resultSet_.getString( C_BKR_CNPJ_NBR ) );
//
//        tplCustomerBrokerMovEntity.getData().setBkrCommentText(
//                                                        resultSet_.getString( C_BKR_COMMENT_TEXT ) );
//
//        tplCustomerBrokerMovEntity.getData().setBkrNameText(
//                                                     resultSet_.getString( C_BKR_NAME_TEXT ) );
//
//        if ( resultSet_.getString( C_BKR_RBT_BMF_RATE ) != null )
//        {
//
//          tplCustomerBrokerMovEntity.getData().setBkrRbtBmfRate(
//                                                         new BigDecimal(
//                                                                         resultSet_.getString( C_BKR_RBT_BMF_RATE ) ) );
//        }
//        else
//        {
//          tplCustomerBrokerMovEntity.getData().setBkrRbtBmfRate( null );
//        }
//
//        if ( resultSet_.getString( C_BKR_RBT_BOVESPA_RATE ) != null )
//        {
//          tplCustomerBrokerMovEntity.getData().setBkrRbtBovespaRate(
//                                                             new BigDecimal(
//                                                                             resultSet_.getString( C_BKR_RBT_BOVESPA_RATE ) ) );
//        }
//        else
//        {
//          tplCustomerBrokerMovEntity.getData().setBkrRbtBovespaRate( null );
//        }
//
//        if ( resultSet_.getString( C_BKR_REQ_CR_LIM_DLR_AMT ) != null )
//        {
//          tplCustomerBrokerMovEntity.getData().setBkrReqCrLimDlrAmt(
//                                                             new BigDecimal(
//                                                                             resultSet_.getString( C_BKR_REQ_CR_LIM_DLR_AMT ) ) );
//        }
//        else
//        {
//          tplCustomerBrokerMovEntity.getData().setBkrReqCrLimDlrAmt( null );
//
//        }
//        if ( resultSet_.getString( C_BKR_REQ_CR_LIM_LCY_AMT ) != null )
//        {
//          tplCustomerBrokerMovEntity.getData().setBkrReqCrLimLcyAmt(
//                                                             new BigDecimal(
//                                                                             resultSet_.getString( C_BKR_REQ_CR_LIM_LCY_AMT ) ) );
//        }
//        else
//        {
//          tplCustomerBrokerMovEntity.getData().setBkrReqCrLimLcyAmt( null );
//        }
//        tplCustomerBrokerMovEntity.getData().setBkrReqDate(
//                                                    resultSet_.getDate( C_BKR_REQ_DATE ) );
//
//        tplCustomerBrokerMovEntity.getData().setBkrRnwDate(
//                                                    resultSet_.getDate( C_BKR_RNW_DATE ) );
//
//        tplCustomerBrokerMovEntity.getData().setBkrSuplServText(
//                                                         resultSet_.getString( C_BKR_SUPL_SERV_TEXT ) );
//
//        tplCustomerBrokerMovEntity.getData().setLastUpdDate(
//                                                     resultSet_.getDate( C_LAST_UPD_DATE ) );
//
//        tplCustomerBrokerMovEntity.getData().setLastUpdUserId(
//                                                       resultSet_.getString( C_LAST_UPD_USER_ID ) );
//
//        //      Casting para a atribuicao das colunas especificas
//        tplCustomerBrokerMovEntityVO = ( TplCustomerBrokerMovEntityVO ) tplCustomerBrokerMovEntity.getData();
//        tplCustomerBrokerMovEntityVO.setOpernCode( resultSet_.getString( C_OPERN_CODE ) );
//
//        tplCustomerBrokerMovEntities.add( tplCustomerBrokerMovEntity );
//      }
//    }
//    catch ( SQLException e )
//    {
//      throw new UnexpectedException( C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
//    }
//    return tplCustomerBrokerMovEntities;
    
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplCustomerBrokerMovDAO#exists(com.citibank.ods.entity.pl.TplCustomerBrokerMovEntity)
   */
  public boolean exists( TplCustomerBrokerMovEntity tplCustomerBrokerMovEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tplCustomerBrokerMovEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  public DataSet list( String bkrCnpjNbr_, String bkrNameText_,
                      String custNbr_, String custFullNameText_, String lastUpdUserId_ ) throws UnexpectedException
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

      if ( bkrCnpjNbr_ != null && !bkrCnpjNbr_.equals( "" ) )
      {
        criteria = criteria + C_BKR_CNPJ_NBR + " = ? AND ";
      }

      if ( bkrNameText_ != null && !bkrNameText_.equals( "" ) )
      {
        criteria = criteria + "UPPER(" + C_BKR_NAME_TEXT + ") like ? AND ";
      }

      if ( lastUpdUserId_ != null && !lastUpdUserId_.equals( "" ) )
      {
        criteria = criteria + "UPPER(" + C_LAST_UPD_USER_ID + ") like ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        sqlQuery.append( "	WHERE " + criteria );
      }
      sqlQuery.append( " ORDER BY " + C_BKR_CNPJ_NBR );

      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

      int count = 1;

      if ( bkrCnpjNbr_ != null && !bkrCnpjNbr_.equals( "" ) )
      {
        preparedStatement.setString( count++, bkrCnpjNbr_ );
      }

      if ( bkrNameText_ != null && !bkrNameText_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + bkrNameText_.toUpperCase() + "%" );
      }

      if ( lastUpdUserId_ != null && !lastUpdUserId_.equals( "" ) )
      {
        preparedStatement.setString( count++, lastUpdUserId_.toUpperCase() );
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

    String[] codeColumn = { C_OPERN_CODE };
    String[] nameColumn = { C_OPERN_TEXT };
    resultSetDataSet.outerJoin( ODSConstraintDecoder.decodeOpern(), codeColumn,
                                codeColumn, nameColumn );

    return resultSetDataSet;
  }

}