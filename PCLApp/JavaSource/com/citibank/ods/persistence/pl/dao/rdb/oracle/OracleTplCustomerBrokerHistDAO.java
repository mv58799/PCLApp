package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplCustomerBrokerEntity;
import com.citibank.ods.entity.pl.TplCustomerBrokerHistEntity;
import com.citibank.ods.persistence.pl.dao.TplCustomerBrokerHistDAO;

/**
 * Implementação Oracle para DAO da tabela TPL_BROKERHist
 * @author Hamilton Matos
 */
public class OracleTplCustomerBrokerHistDAO extends BaseOracleTplCustomerBrokerDAO implements
    TplCustomerBrokerHistDAO
{
  private static final String C_TABLE_COLUMNS = "BKR_REF_DATE,LAST_AUTH_DATE,LAST_AUTH_USER_ID,REC_STAT_CODE,BKR_ADDR_TEXT,BKR_APPRV_CR_LIM_DLR_AMT,BKR_APPRV_CR_LIM_LCY_AMT,BKR_APPRV_DATE,BKR_APPRV_STAT_CODE,BKR_AUTH_PROC_SIT_TEXT,BKR_BMF_MKT_CODE,BKR_BOVESPA_MKT_CODE,BKR_CNPJ_NBR,BKR_COMMENT_TEXT,BKR_NAME_TEXT,BKR_RBT_BMF_RATE,BKR_RBT_BOVESPA_RATE,BKR_REQ_CR_LIM_DLR_AMT,BKR_REQ_CR_LIM_LCY_AMT,BKR_REQ_DATE,BKR_RNW_DATE,BKR_SUPL_SERV_TEXT,LAST_UPD_DATE,LAST_UPD_USER_ID";

  private static final String C_BKR_REF_DATE = "BKR_REF_DATE";

  private static final String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private static final String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private static final String C_REC_STAT_CODE = "REC_STAT_CODE";

  private static final String C_TABLE_NAME = C_PL_SCHEMA + "TPL_BROKER_HIST";

  /**
   * Insere uma nova linha na tabela TPL_BROKER_Hist com os dados da entidade
   * correspondente passada como parametro
   * @param tplCustomerBrokerHistEntity__
   * @throws UnexpectedException
   * @author Hamilton Matos
   * @date 13/07/2007
   */
  public TplCustomerBrokerHistEntity insert( TplCustomerBrokerHistEntity tplCustomerBrokerHistEntity_ )
                                                                               throws UnexpectedException
  {
//    ManagedRdbConnection connection = null;
//    CitiStatement preparedStatement = null;
//    ResultSet resultSet = null;
//    StringBuffer sqlQuery = new StringBuffer();
//    sqlQuery.append( "INSERT INTO " + C_TABLE_NAME + "( " );
//    sqlQuery.append( C_BKR_REF_DATE + ", " );
//    sqlQuery.append( C_LAST_AUTH_DATE + ", " );
//    sqlQuery.append( C_LAST_AUTH_USER_ID + ", " );
//    sqlQuery.append( C_REC_STAT_CODE + ", " + C_BKR_ADDR_TEXT + ", " );
//    sqlQuery.append( C_BKR_APPRV_CR_LIM_DLR_AMT + ", " );
//    sqlQuery.append( C_BKR_APPRV_CR_LIM_LCY_AMT + ", " );
//    sqlQuery.append( C_BKR_APPRV_DATE + ", " );
//    sqlQuery.append( C_BKR_APPRV_STAT_CODE + ", " );
//    sqlQuery.append( C_BKR_AUTH_PROC_SIT_TEXT + ", " );
//    sqlQuery.append( C_BKR_BMF_MKT_CODE + ", " );
//    sqlQuery.append( C_BKR_BOVESPA_MKT_CODE + ", " );
//    sqlQuery.append( C_BKR_CNPJ_NBR + ", " + C_BKR_COMMENT_TEXT + ", " );
//    sqlQuery.append( C_BKR_NAME_TEXT + ", " );
//    sqlQuery.append( C_BKR_RBT_BMF_RATE + ", " );
//    sqlQuery.append( C_BKR_RBT_BOVESPA_RATE + ", " );
//    sqlQuery.append( C_BKR_REQ_CR_LIM_DLR_AMT + ", " );
//    sqlQuery.append( C_BKR_REQ_CR_LIM_LCY_AMT + ", " );
//    sqlQuery.append( C_BKR_REQ_DATE + ", " + C_BKR_RNW_DATE + ", " );
//    sqlQuery.append( C_BKR_SUPL_SERV_TEXT + ", " );
//    sqlQuery.append( C_LAST_UPD_DATE + ", " + C_LAST_UPD_USER_ID );
//    sqlQuery.append( ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" );
//
//    int count = 1;
//    try
//    {
//      connection = OracleODSDAOFactory.getConnection();
//      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));
//
//      if ( ( ( TplCustomerBrokerHistEntityVO ) tplCustomerBrokerHistEntity_.getData() ).getBkrRefDate() != null )
//      {
//
//        preparedStatement.setTimestamp(
//                                count++,
//                                new Timestamp(
//                                               ( ( TplCustomerBrokerHistEntityVO ) tplCustomerBrokerHistEntity_.getData() ).getBkrRefDate().getTime() ) );
//      }
//      else
//      {
//        preparedStatement.setTimestamp( count++, null );
//      }
//
//      if ( ( ( TplCustomerBrokerHistEntityVO ) tplCustomerBrokerHistEntity_.getData() ).getLastAuthDate() != null )
//      {
//
//        preparedStatement.setTimestamp(
//                                count++,
//                                new Timestamp(
//                                               ( ( TplCustomerBrokerHistEntityVO ) tplCustomerBrokerHistEntity_.getData() ).getLastAuthDate().getTime() ) );
//
//      }
//      else
//      {
//        preparedStatement.setTimestamp( count++, null );
//      }
//
//      if ( ( ( TplCustomerBrokerHistEntityVO ) tplCustomerBrokerHistEntity_.getData() ).getLastAuthUserId() != null )
//      {
//
//        preparedStatement.setString(
//                             count++,
//                             ( ( TplCustomerBrokerHistEntityVO ) tplCustomerBrokerHistEntity_.getData() ).getLastAuthUserId() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( ( ( TplCustomerBrokerHistEntityVO ) tplCustomerBrokerHistEntity_.getData() ).getRecStatCode() != null )
//      {
//        preparedStatement.setString(
//                             count++,
//                             ( ( TplCustomerBrokerHistEntityVO ) tplCustomerBrokerHistEntity_.getData() ).getRecStatCode() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerHistEntity_.getData().getBkrAddrText() != null )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerHistEntity_.getData().getBkrAddrText() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerHistEntity_.getData().getBkrApprvCrLimDlrAmt() != null )
//      {
//        preparedStatement.setBigDecimal(
//                                 count++,
//                                 tplCustomerBrokerHistEntity_.getData().getBkrApprvCrLimDlrAmt() );
//      }
//      else
//      {
//        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
//      }
//
//      if ( tplCustomerBrokerHistEntity_.getData().getBkrApprvCrLimLcyAmt() != null )
//      {
//        preparedStatement.setBigDecimal(
//                                 count++,
//                                 tplCustomerBrokerHistEntity_.getData().getBkrApprvCrLimLcyAmt() );
//      }
//      else
//      {
//        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
//      }
//
//      if ( tplCustomerBrokerHistEntity_.getData().getBkrApprvDate() != null )
//      {
//        preparedStatement.setTimestamp(
//                                count++,
//                                new Timestamp(
//                                               tplCustomerBrokerHistEntity_.getData().getBkrApprvDate().getTime() ) );
//      }
//      else
//      {
//        preparedStatement.setTimestamp( count++, null );
//      }
//
//      if ( tplCustomerBrokerHistEntity_.getData().getBkrApprvStatCode() != null )
//      {
//        preparedStatement.setString(
//                             count++,
//                             tplCustomerBrokerHistEntity_.getData().getBkrApprvStatCode() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerHistEntity_.getData().getBkrAuthProcSitText() != null )
//      {
//        preparedStatement.setString(
//                             count++,
//                             tplCustomerBrokerHistEntity_.getData().getBkrAuthProcSitText() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerHistEntity_.getData().getBkrBmfMktCode() != null )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerHistEntity_.getData().getBkrBmfMktCode() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//      if ( tplCustomerBrokerHistEntity_.getData().getBkrBovespaMktCode() != null )
//      {
//        preparedStatement.setString(
//                             count++,
//                             tplCustomerBrokerHistEntity_.getData().getBkrBovespaMktCode() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerHistEntity_.getData().getBkrCnpjNbr() != null )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerHistEntity_.getData().getBkrCnpjNbr() );
//      }
//      else
//        preparedStatement.setString( count++, null );
//
//      if ( tplCustomerBrokerHistEntity_.getData().getBkrCommentText() != null )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerHistEntity_.getData().getBkrCommentText() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerHistEntity_.getData().getBkrNameText() != null )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerHistEntity_.getData().getBkrNameText() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      if ( tplCustomerBrokerHistEntity_.getData().getBkrRbtBmfRate() != null )
//      {
//        preparedStatement.setBigDecimal(
//                                 count++,
//                                 tplCustomerBrokerHistEntity_.getData().getBkrRbtBmfRate() );
//      }
//      else
//      {
//        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
//      }
//
//      if ( tplCustomerBrokerHistEntity_.getData().getBkrRbtBovespaRate() != null )
//      {
//        preparedStatement.setBigDecimal(
//                                 count++,
//                                 tplCustomerBrokerHistEntity_.getData().getBkrRbtBovespaRate() );
//      }
//      else
//      {
//        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
//      }
//
//      if ( tplCustomerBrokerHistEntity_.getData().getBkrReqCrLimDlrAmt() != null )
//      {
//        preparedStatement.setBigDecimal(
//                                 count++,
//                                 tplCustomerBrokerHistEntity_.getData().getBkrReqCrLimDlrAmt() );
//      }
//      else
//      {
//        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
//      }
//
//      if ( tplCustomerBrokerHistEntity_.getData().getBkrReqCrLimLcyAmt() != null )
//      {
//        preparedStatement.setBigDecimal(
//                                 count++,
//                                 tplCustomerBrokerHistEntity_.getData().getBkrReqCrLimLcyAmt() );
//      }
//
//      else
//      {
//        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
//      }
//
//      if ( tplCustomerBrokerHistEntity_.getData().getBkrReqDate() != null )
//      {
//        preparedStatement.setTimestamp(
//                                count++,
//                                new Timestamp(
//                                               tplCustomerBrokerHistEntity_.getData().getBkrReqDate().getTime() ) );
//      }
//      else
//      {
//        preparedStatement.setTimestamp( count++, null );
//      }
//
//      if ( tplCustomerBrokerHistEntity_.getData().getBkrRnwDate() != null )
//      {
//        preparedStatement.setTimestamp(
//                                count++,
//                                new Timestamp(
//                                               tplCustomerBrokerHistEntity_.getData().getBkrRnwDate().getTime() ) );
//      }
//      else
//      {
//        preparedStatement.setTimestamp( count++, null );
//      }
//
//      if ( tplCustomerBrokerHistEntity_.getData().getBkrSuplServText() != null )
//      {
//        preparedStatement.setString(
//                             count++,
//                             tplCustomerBrokerHistEntity_.getData().getBkrSuplServText() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//      if ( tplCustomerBrokerHistEntity_.getData().getLastUpdDate() != null )
//      {
//        preparedStatement.setTimestamp(
//                                count++,
//                                new Timestamp(
//                                               tplCustomerBrokerHistEntity_.getData().getLastUpdDate().getTime() ) );
//      }
//      else
//      {
//        preparedStatement.setTimestamp( count++, null );
//      }
//      if ( tplCustomerBrokerHistEntity_.getData().getLastUpdUserId() != null )
//      {
//        preparedStatement.setString( count++,
//                             tplCustomerBrokerHistEntity_.getData().getLastUpdUserId() );
//      }
//      else
//      {
//        preparedStatement.setString( count++, null );
//      }
//
//      preparedStatement.executeUpdate();
//
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
//
//    return tplCustomerBrokerHistEntity_;
    
    return null;
  }

  public void update( TplCustomerBrokerHistEntity tplCustomerBrokerHistEntity_ )
                                                                throws UnexpectedException
  {
    //
  }

  public void delete( BigInteger entityKey_ ) throws UnexpectedException
  {
    //
  }

  public DataSet list( Date bkrRefDate_, Date lastAuthDate_,
                      String lastAuthUserId_, String recStatCode_,
                      String bkrAddrText_, BigInteger bkrApprvCrLimDlrAmt_,
                      BigInteger bkrApprvCrLimLcyAmt_, Date bkrApprvDate_,
                      String bkrApprvStatCode_, String bkrAuthProcSitText_,
                      String bkrBmfMktCode_, String bkrBovespaMktCode_,
                      String bkrCnpjNbr_, String bkrCommentText_,
                      String bkrNameText_, BigInteger bkrRbtBmfRate_,
                      BigInteger bkrRbtBovespaRate_,
                      BigInteger bkrReqCrLimDlrAmt_,
                      BigInteger bkrReqCrLimLcyAmt_, Date bkrReqDate_,
                      Date bkrRnwDate_, String bkrSuplServText_,
                      Date lastUpdDate_, String lastUpdUserId_ )
                                                                throws UnexpectedException
  {

    return null;
  }

  public TplCustomerBrokerHistEntity find( TplCustomerBrokerHistEntity tplCustomerBrokerHistEntity_ )
                                                                             throws UnexpectedException
  {
    return null;
  }

  public BaseTplCustomerBrokerEntity find( BaseTplCustomerBrokerEntity baseTplCustomerBrokerEntity_ )
                                                                             throws UnexpectedException
  {
    return null;
  }

}

