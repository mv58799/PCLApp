package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.DataSetRow;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplBkrPortfMgmtEntity;
import com.citibank.ods.entity.pl.TplBkrPortfMgmtMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplBkrPortfMgmtMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author Hamilton Matos
 */

public class OracleTplBkrPortfMgmtMovDAO extends BaseOracleTplBkrPortfMgmtDAO
    implements TplBkrPortfMgmtMovDAO
{
  private static final String C_TABLE_COLUMNS = "PROD_ACCT_CODE,PROD_UNDER_ACCT_CODE,BKR_CNPJ_NBR,BOVESPA_CUR_ACCT_NBR,BOVESPA_INVST_ACCT_NBR,BMF_CUR_ACCT_NBR,BMF_INVST_ACCT_NBR,LAST_UPD_DATE,LAST_UPD_USER_ID,OPERN_CODE";

  private static final String C_OPERN_CODE = "OPERN_CODE";

  private static final String C_OPERN_TEXT = "OPERN_TEXT";

  private static final String C_TABLE_NAME = C_PL_SCHEMA
                                             + "TPL_BKR_PORTF_MGMT_MOV";

  private static final String C_TPL_BKR_PORTF_MGMT_MOV = C_PL_SCHEMA
                                                         + ".TPL_BKR_PORTF_MGMT_MOV";

  private static final String C_TO3_PROD_ACCT_PORTF_MGMT = C_O3_SCHEMA
                                                           + ".TO3_PROD_ACCT_PORTF_MGMT";

  private static final String C_TO3_PRODUCT_ACCOUNT = C_O3_SCHEMA
                                                      + ".TO3_PRODUCT_ACCOUNT";

  private static final String C_TPL_CUSTOMER_PRVT = C_PL_SCHEMA
                                                    + ".TPL_CUSTOMER_PRVT";

  private static final String C_ALIAS_TPL_BKR_PORTF_MGMT_MOV = "BKR_PORTF_MGMT_MOV";

  private static final String C_ALIAS_TO3_PROD_ACCT_PORTF_MGMT = "PROD_ACCT_PRODUCT_ACCOUNT";

  private static final String C_ALIAS_TO3_PRODUCT_ACCOUNT = "PRODUCT_ACCOUNT";

  private static final String C_ALIAS_TPL_CUSTOMER_PRVT = "CUSTOMER_PRVT";

  private static final String C_CUST_NBR = "CUST_NBR";

  private static final String C_CUST_NAME = "CUST_NAME";

  private static final String C_CUST_FULL_NAME_TEXT = "CUST_FULL_NAME_TEXT";

  private static final String C_ALIAS_BROKER = "BROKER";

  private static final String C_TABLE_BROKER = C_PL_SCHEMA + "TPL_BROKER";

  private static final String C_ALIAS_PORTF_MGMT = "PORTF_MGMT";

  private static final String C_ALIAS_POINT_ACCT_INVST = "POINT_INVST";

  private static final String C_ALIAS_PRODUCT_ACCOUNT = "PRODUCT_ACCOUNT";

  private static final String C_ALIAS_RELATION_PRVT = "RELATION_PRVT";

  private static final String C_TABLE_PROD_ACCT_PORTF_MGMT = C_O3_SCHEMA
                                                             + "TO3_PROD_ACCT_PORTF_MGMT";

  private static final String C_TABLE_POINT_ACCT_INVST = C_BG_SCHEMA
                                                         + "TBG_POINT_ACCT_INVST";

  private static final String C_TABLE_PRODUCT_ACCOUNT = C_O3_SCHEMA
                                                        + "TO3_PRODUCT_ACCOUNT";

  private static final String C_TABLE_RELATION_PRVT = C_PL_SCHEMA
                                                      + "TPL_RELATION_PRVT";

  private static final String C_TABLE_BKR_PORTF_MGMT_MOV = C_PL_SCHEMA
                                                           + "TPL_BKR_PORTF_MGMT_MOV";

  private String C_REC_STAT_CODE = "REC_STAT_CODE";

  private String C_REC_STAT_TEXT = "REC_STAT_TEXT";

  private static final String C_QTY_PORTF_MGMT = "QTY_PORTF_MGMT";

  private static final String C_CPF_CNPJ_NBR = "CPF_CNPJ_NBR";

  private static final String C_COUNT_USR = "COUNT_USR";

  private static final String C_CUST_CPF_CNPJ_NBR = "CUST_CPF_CNPJ_NBR";

  private static final String C_TABLE_CUSTOMER_PRVT = C_PL_SCHEMA
                                                      + "TPL_CUSTOMER_PRVT";

  /**
   * Insere uma nova linha na tabela tpl_bkr_portf_mgmt_mov com os dados da
   * entidade correspondente passada como parâmetro
   */
  public TplBkrPortfMgmtMovEntity insert(
                                         TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity_ )
                                                                                             throws UnexpectedException
  {

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TABLE_NAME + " ( " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_BKR_CNPJ_NBR + ", " );
      query.append( C_BOVESPA_CUR_ACCT_NBR + ", " );
      query.append( C_BOVESPA_INVST_ACCT_NBR + ", " );
      query.append( C_BMF_CUR_ACCT_NBR + ", " );
      query.append( C_BMF_INVST_ACCT_NBR + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_OPERN_CODE + ")" );
      query.append( " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      TplBkrPortfMgmtMovEntityVO tplBkrPortfMgmtMovEntityVO = ( TplBkrPortfMgmtMovEntityVO ) tplBkrPortfMgmtMovEntity_.getData();

      int count = 1;

      if ( tplBkrPortfMgmtMovEntityVO.getProdAcctCode() != null
           && !tplBkrPortfMgmtMovEntityVO.getProdAcctCode().equals( "" ) )
      {
        preparedStatement.setLong(
                           count++,
                           tplBkrPortfMgmtMovEntity_.getData().getProdAcctCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPortfMgmtMovEntityVO.getProdUnderAcctCode() != null
           && !tplBkrPortfMgmtMovEntityVO.getProdUnderAcctCode().equals( "" ) )
      {
        preparedStatement.setLong(
                           count++,
                           tplBkrPortfMgmtMovEntity_.getData().getProdUnderAcctCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPortfMgmtMovEntityVO.getBkrCnpjNbr() != null
           && !tplBkrPortfMgmtMovEntityVO.getBkrCnpjNbr().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPortfMgmtMovEntity_.getData().getBkrCnpjNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPortfMgmtMovEntityVO.getBovespaCurAcctNbr() != null
           && !tplBkrPortfMgmtMovEntityVO.getBovespaCurAcctNbr().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPortfMgmtMovEntity_.getData().getBovespaCurAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBkrPortfMgmtMovEntityVO.getBovespaInvstAcctNbr() != null
           && !tplBkrPortfMgmtMovEntityVO.getBovespaInvstAcctNbr().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPortfMgmtMovEntity_.getData().getBovespaInvstAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBkrPortfMgmtMovEntityVO.getBmfCurAcctNbr() != null
           && !tplBkrPortfMgmtMovEntityVO.getBmfCurAcctNbr().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPortfMgmtMovEntity_.getData().getBmfCurAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBkrPortfMgmtMovEntityVO.getBmfInvstAcctNbr() != null
           && !tplBkrPortfMgmtMovEntityVO.getBmfInvstAcctNbr().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPortfMgmtMovEntity_.getData().getBmfInvstAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBkrPortfMgmtMovEntityVO.getLastUpdDate() != null
           && !tplBkrPortfMgmtMovEntityVO.getLastUpdDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBkrPortfMgmtMovEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPortfMgmtMovEntityVO.getLastUpdUserId() != null
           && !tplBkrPortfMgmtMovEntityVO.getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPortfMgmtMovEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPortfMgmtMovEntityVO.getOpernCode() != null
           && !tplBkrPortfMgmtMovEntityVO.getOpernCode().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplBkrPortfMgmtMovEntityVO ) tplBkrPortfMgmtMovEntity_.getData() ).getOpernCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

	  preparedStatement.replaceParametersInQuery(query.toString());
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
    return tplBkrPortfMgmtMovEntity_;

  }

  /**
   * Atualiza uma linha na tabela tpl_bkr_portf_mgmt_mov de acordo com ID
   * contido na entidade passada como parâmetro.
   */
  public TplBkrPortfMgmtMovEntity update(
                                         TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity_ )
                                                                                             throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TABLE_NAME + " SET " );
      query.append( C_BOVESPA_CUR_ACCT_NBR + " = ?, " );
      query.append( C_BOVESPA_INVST_ACCT_NBR + " = ?, " );
      query.append( C_BMF_CUR_ACCT_NBR + " = ?, " );
      query.append( C_BMF_INVST_ACCT_NBR + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_OPERN_CODE + " = ? " );
      query.append( " WHERE " );
      query.append( C_BKR_CNPJ_NBR + " = ? AND " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      TplBkrPortfMgmtMovEntityVO tplBkrPortfMgmtMovEntityVO = ( TplBkrPortfMgmtMovEntityVO ) tplBkrPortfMgmtMovEntity_.getData();

      int count = 1;

      if ( tplBkrPortfMgmtMovEntityVO.getBovespaCurAcctNbr() != null
           && !tplBkrPortfMgmtMovEntityVO.getBovespaCurAcctNbr().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPortfMgmtMovEntity_.getData().getBovespaCurAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBkrPortfMgmtMovEntityVO.getBovespaInvstAcctNbr() != null
           && !tplBkrPortfMgmtMovEntityVO.getBovespaInvstAcctNbr().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPortfMgmtMovEntity_.getData().getBovespaInvstAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBkrPortfMgmtMovEntityVO.getBmfCurAcctNbr() != null
           && !tplBkrPortfMgmtMovEntityVO.getBmfCurAcctNbr().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPortfMgmtMovEntity_.getData().getBmfCurAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBkrPortfMgmtMovEntityVO.getBmfInvstAcctNbr() != null
           && !tplBkrPortfMgmtMovEntityVO.getBmfInvstAcctNbr().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPortfMgmtMovEntity_.getData().getBmfInvstAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBkrPortfMgmtMovEntityVO.getLastUpdDate() != null
           && !tplBkrPortfMgmtMovEntityVO.getLastUpdDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBkrPortfMgmtMovEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPortfMgmtMovEntityVO.getLastUpdUserId() != null
           && !tplBkrPortfMgmtMovEntityVO.getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPortfMgmtMovEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPortfMgmtMovEntityVO.getOpernCode() != null
           && !tplBkrPortfMgmtMovEntityVO.getOpernCode().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplBkrPortfMgmtMovEntityVO ) tplBkrPortfMgmtMovEntity_.getData() ).getOpernCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPortfMgmtMovEntityVO.getBkrCnpjNbr() != null
           && !tplBkrPortfMgmtMovEntityVO.getBkrCnpjNbr().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPortfMgmtMovEntity_.getData().getBkrCnpjNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      if ( tplBkrPortfMgmtMovEntityVO.getProdAcctCode() != null
           && !tplBkrPortfMgmtMovEntityVO.getProdAcctCode().equals( "" ) )
      {
        preparedStatement.setLong(
                           count++,
                           tplBkrPortfMgmtMovEntity_.getData().getProdAcctCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPortfMgmtMovEntityVO.getProdUnderAcctCode() != null
           && !tplBkrPortfMgmtMovEntityVO.getProdUnderAcctCode().equals( "" ) )
      {
        preparedStatement.setLong(
                           count++,
                           tplBkrPortfMgmtMovEntity_.getData().getProdUnderAcctCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

	  preparedStatement.replaceParametersInQuery(query.toString());
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
    return tplBkrPortfMgmtMovEntity_;

  }

  /**
   * Remove uma linha na tabela tpl_bkr_portf_mgmt_mov de acordo com ID passado
   * como parâmetro.
   */
  public TplBkrPortfMgmtMovEntity delete(
                                         TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity_ )
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
      sqlQuery.append( C_PROD_ACCT_CODE + " = ? AND " );
      sqlQuery.append( C_PROD_UNDER_ACCT_CODE + " = ? AND " );
      sqlQuery.append( C_BKR_CNPJ_NBR + " = ? " );
      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));
      preparedStatement.setLong(
                         1,
                         tplBkrPortfMgmtMovEntity_.getData().getProdAcctCode().longValue() );
      preparedStatement.setLong(
                         2,
                         tplBkrPortfMgmtMovEntity_.getData().getProdUnderAcctCode().longValue() );
      preparedStatement.setString( 3,
                           tplBkrPortfMgmtMovEntity_.getData().getBkrCnpjNbr().toString() );

	  preparedStatement.replaceParametersInQuery(sqlQuery.toString());
      preparedStatement.executeUpdate();
      
	  

      return tplBkrPortfMgmtMovEntity_;
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
   * Cria uma entidade representando um registro da tabela
   * tpl_bkr_portf_mgmt_mov com os dados do ID passado como parâmetro
   */

  public BaseTplBkrPortfMgmtEntity find(
                                        BaseTplBkrPortfMgmtEntity tplBkrPortfMgmtEntity_ )
                                                                                          throws UnexpectedException
  {
    ResultSet resultSet = null;
    CitiStatement preparedStatement = null;
    ManagedRdbConnection connection = null;
    StringBuffer sqlQuery = new StringBuffer();
    ArrayList tplBkrPortfMgmtMovEntities = new ArrayList();
    BaseTplBkrPortfMgmtEntity tplBkrPortfMgmtEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      sqlQuery.append( "SELECT " );
      sqlQuery.append( C_TABLE_COLUMNS );
      sqlQuery.append( " FROM " );
      sqlQuery.append( C_TABLE_NAME );
      sqlQuery.append( " WHERE " );
      sqlQuery.append( C_PROD_ACCT_CODE + " = ? " );
      sqlQuery.append( " AND " );
      sqlQuery.append( C_PROD_UNDER_ACCT_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

      preparedStatement.setLong(
                         1,
                         ( tplBkrPortfMgmtEntity_.getData().getProdAcctCode() ).longValue() );

      preparedStatement.setLong(
                         2,
                         ( tplBkrPortfMgmtEntity_.getData().getProdUnderAcctCode() ).longValue() );

	  preparedStatement.replaceParametersInQuery(sqlQuery.toString());
      resultSet = preparedStatement.executeQuery(); 
	  

      tplBkrPortfMgmtMovEntities = instantiateFromResultSet( resultSet );

      if ( tplBkrPortfMgmtMovEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }

      else
      {
        tplBkrPortfMgmtEntity = ( BaseTplBkrPortfMgmtEntity ) tplBkrPortfMgmtMovEntities.get( 0 );
      }

      return tplBkrPortfMgmtEntity;
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
    TplBkrPortfMgmtMovEntityVO tplBkrPortfMgmtMovEntityVO;
    TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity;
    ArrayList tplBkrPortfMgmtEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplBkrPortfMgmtMovEntity = new TplBkrPortfMgmtMovEntity();

        tplBkrPortfMgmtMovEntity.getData().setProdAcctCode(
                                                            new BigInteger(
                                                                            resultSet_.getString( C_PROD_ACCT_CODE ) ) );
        tplBkrPortfMgmtMovEntity.getData().setProdUnderAcctCode(
                                                                 new BigInteger(
                                                                                 resultSet_.getString( C_PROD_UNDER_ACCT_CODE ) ) );
        tplBkrPortfMgmtMovEntity.getData().setBkrCnpjNbr(
                                                          resultSet_.getString( C_BKR_CNPJ_NBR ) );
        tplBkrPortfMgmtMovEntity.getData().setBovespaCurAcctNbr(
                                                                 resultSet_.getString( C_BOVESPA_CUR_ACCT_NBR ) );
        tplBkrPortfMgmtMovEntity.getData().setBovespaInvstAcctNbr(
                                                                   resultSet_.getString( C_BOVESPA_INVST_ACCT_NBR ) );
        tplBkrPortfMgmtMovEntity.getData().setBmfCurAcctNbr(
                                                             resultSet_.getString( C_BMF_CUR_ACCT_NBR ) );
        tplBkrPortfMgmtMovEntity.getData().setBmfInvstAcctNbr(
                                                               resultSet_.getString( C_BMF_INVST_ACCT_NBR ) );
        tplBkrPortfMgmtMovEntity.getData().setLastUpdDate(
                                                           resultSet_.getDate( C_LAST_UPD_DATE ) );
        tplBkrPortfMgmtMovEntity.getData().setLastUpdUserId(
                                                             resultSet_.getString( C_LAST_UPD_USER_ID ) );

        // Casting para a atribuição das colunas específicas
        tplBkrPortfMgmtMovEntityVO = ( TplBkrPortfMgmtMovEntityVO ) tplBkrPortfMgmtMovEntity.getData();

        tplBkrPortfMgmtMovEntityVO.setOpernCode( resultSet_.getString( C_OPERN_CODE ) );

        tplBkrPortfMgmtEntities.add( tplBkrPortfMgmtMovEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    
    return tplBkrPortfMgmtEntities;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtMovDAO#exists(com.citibank.ods.entity.pl.TplBkrPortfMgmtMovEntity)
   */
  public boolean exists( TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tplBkrPortfMgmtMovEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  public DataSet list( String bkrCnpjNbr_, String bkrNameText_,
                      String custNbr_, String custFullNameText_,
                      String lastUpdUserId_ ) throws UnexpectedException
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

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtMovDAO#list(java.lang.String,
   *      java.lang.String, java.math.BigInteger, java.lang.String,
   *      java.lang.String, java.lang.String, java.lang.String)
   */
  public DataSet list( String custNbrSrc_, String custFullNameTextSrc_,
                      BigInteger curAcctNbr_, String custMnmcNameTextSrc_,
                      String portfMgmtProdNameSrc_, String prodCodeSrc_,
                      String lastUpdUserIdSrc_, String loggedUserSrc_ )
                                                                       throws UnexpectedException
  {

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " SELECT " );
      query.append( C_CUST_NBR + ", " + C_CUST_NAME + ", " );
      query.append( " COUNT(*) AS " );
      query.append( C_QTY_PORTF_MGMT + ", Sum(COUNTER_USER) AS " );
      query.append( C_COUNT_USR + ", " + C_CPF_CNPJ_NBR );
      query.append( " FROM " );
      query.append( " ( " );
      query.append( " SELECT " );
      query.append( " C." + C_RELTN_CUST_1_NBR + " AS " + C_CUST_NBR + ", D."
                    + C_CUST_FULL_NAME_TEXT + " AS " + C_CUST_NAME + ", " );
      query.append( " D. " + C_CUST_CPF_CNPJ_NBR + " AS " + C_CPF_CNPJ_NBR
                    + ", B." + C_PROD_CODE + " AS " + C_PROD_CODE + ", A."
                    + C_PROD_ACCT_CODE + " AS " + C_PROD_ACCT_CODE + ", A."
                    + C_PROD_UNDER_ACCT_CODE + " AS " + C_PROD_UNDER_ACCT_CODE
                    + ", " );
      query.append( " CASE WHEN A. " + C_LAST_UPD_USER_ID + " = ? " );
      query.append( " THEN 1 ELSE 0 END AS COUNTER_USER " );
      query.append( " FROM " );
      query.append( C_TABLE_BKR_PORTF_MGMT_MOV + " A, "
                    + C_TABLE_PRODUCT_ACCOUNT + " B, " );
      query.append( C_TABLE_RELATION_PRVT + " C, " + C_TABLE_CUSTOMER_PRVT
                    + " D, " + C_TABLE_PROD_ACCT_PORTF_MGMT + " E " );
      query.append( " WHERE " );
      query.append( " A." + C_PROD_ACCT_CODE + " = B." + C_PROD_ACCT_CODE
                    + " AND " );
      query.append( " A." + C_PROD_UNDER_ACCT_CODE + " = B."
                    + C_PROD_UNDER_ACCT_CODE + " AND " );
      query.append( " B." + C_RELTN_NBR + " = C." + C_RELTN_NBR + " AND " );
      query.append( " (C." + C_RELTN_CUST_1_NBR + " = D." + C_CUST_NBR
                    + " OR C." + C_RELTN_CUST_2_NBR + " = D." + C_CUST_NBR
                    + " OR C." + C_RELTN_CUST_3_NBR + " = D." + C_CUST_NBR
                    + " OR C." + C_RELTN_CUST_4_NBR + " = D." + C_CUST_NBR
                    + " OR C." + C_RELTN_CUST_5_NBR + "= D." + C_CUST_NBR
                    + ") AND " );
      query.append( " E." + C_PROD_ACCT_CODE + " = B." + C_PROD_ACCT_CODE
                    + " AND " );
      query.append( " E." + C_PROD_UNDER_ACCT_CODE + " = B."
                    + C_PROD_UNDER_ACCT_CODE );

      String criteria = "";

      if ( custNbrSrc_ != null && !"".equals( custNbrSrc_ ) )
      {
        criteria = criteria + " D." + C_CUST_NBR + " = ? AND ";
      }

      if ( custFullNameTextSrc_ != null && !"".equals( custFullNameTextSrc_ ) )
      {
        criteria = criteria + " UPPER(D." + C_CUST_FULL_NAME_TEXT
                   + ") LIKE ? AND ";
      }

      if ( curAcctNbr_ != null && !"".equals( curAcctNbr_ ) )
      {
        criteria = criteria + " B." + C_CUR_ACCT_NBR + " = ? AND ";
      }

      if ( custMnmcNameTextSrc_ != null && !"".equals( custMnmcNameTextSrc_ ) )
      {
        criteria = criteria + " UPPER(E." + C_CUST_MNMC_NAME + ") LIKE ? AND ";
      }

      if ( portfMgmtProdNameSrc_ != null && !"".equals( portfMgmtProdNameSrc_ ) )
      {
        criteria = criteria + " UPPER(E." + C_PORTF_MGMT_PROD_NAME
                   + ") LIKE ? AND ";
      }
      if ( prodCodeSrc_ != null && !"".equals( prodCodeSrc_ ) )
      {
        criteria = criteria + " UPPER(B." + C_PROD_CODE + ") = ? AND ";
      }
      if ( lastUpdUserIdSrc_ != null && !"".equals( lastUpdUserIdSrc_ ) )
      {
        criteria = criteria + " A." + C_LAST_UPD_USER_ID + " = ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( " AND " + criteria );
      }

      query.append( " ) TAB_ASS GROUP BY " + C_CUST_NBR + ", " + C_CUST_NAME
                    + ", " + C_CPF_CNPJ_NBR );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( loggedUserSrc_ != null && !"".equals( loggedUserSrc_ ) )
      {
        preparedStatement.setString( count++, loggedUserSrc_ );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( custNbrSrc_ != null && !"".equals( custNbrSrc_ ) )
      {
        preparedStatement.setString( count++, custNbrSrc_ );
      }

      if ( custFullNameTextSrc_ != null && !"".equals( custFullNameTextSrc_ ) )
      {
        preparedStatement.setString( count++, "%" + custFullNameTextSrc_.toUpperCase()
                                      + "%" );
      }

      if ( curAcctNbr_ != null && !"".equals( curAcctNbr_ ) )
      {
        preparedStatement.setLong( count++, curAcctNbr_.longValue() );
      }

      if ( custMnmcNameTextSrc_ != null && !"".equals( custMnmcNameTextSrc_ ) )
      {
        preparedStatement.setString( count++, custMnmcNameTextSrc_.toUpperCase() );
      }

      if ( portfMgmtProdNameSrc_ != null && !"".equals( portfMgmtProdNameSrc_ ) )
      {
        preparedStatement.setString( count++, "%" + portfMgmtProdNameSrc_.toUpperCase()
                                      + "%" );
      }

      if ( prodCodeSrc_ != null && !"".equals( prodCodeSrc_ ) )
      {
        preparedStatement.setString( count++, prodCodeSrc_.toUpperCase() );
      }

      if ( lastUpdUserIdSrc_ != null && !"".equals( lastUpdUserIdSrc_ ) )
      {
        preparedStatement.setString( count++, lastUpdUserIdSrc_ );
      }

	  preparedStatement.replaceParametersInQuery(query.toString());
      resultSet = preparedStatement.executeQuery();

      rsds = new ResultSetDataSet( resultSet );
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

    return rsds;

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtDAO#listPortfolio(java.math.BigInteger)
   */
  public DataSet listPortfolio( BigInteger custNbr_ )
                                                     throws UnexpectedException
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
      query.append( C_ALIAS_PRODUCT_ACCOUNT + "." + C_CUR_ACCT_NBR + ", " );
      query.append( C_ALIAS_POINT_ACCT_INVST + "." + C_INVST_CUR_ACCT_NBR
                    + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_PROD_ACCT_CODE + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_PORTF_MGMT_PROD_NAME + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_CUST_MNMC_NAME + ", " );
      query.append( C_ALIAS_PRODUCT_ACCOUNT + "." + C_PROD_CODE + ", " );
      query.append( C_ALIAS_PRODUCT_ACCOUNT + "." + C_REC_STAT_CODE );

      query.append( " FROM " );
      query.append( C_TABLE_PROD_ACCT_PORTF_MGMT + " " + C_ALIAS_PORTF_MGMT
                    + ", " );
      query.append( C_TABLE_POINT_ACCT_INVST + " " + C_ALIAS_POINT_ACCT_INVST
                    + ", " );
      query.append( C_TABLE_PRODUCT_ACCOUNT + " " + C_ALIAS_PRODUCT_ACCOUNT
                    + ", " );
      query.append( C_TABLE_RELATION_PRVT + " " + C_ALIAS_RELATION_PRVT );

      String criteria = "";

      criteria = criteria + C_ALIAS_PORTF_MGMT + "." + C_PROD_ACCT_CODE + " = ";
      criteria = criteria + C_ALIAS_PRODUCT_ACCOUNT + "." + C_PROD_ACCT_CODE
                 + " AND ";
      criteria = criteria + C_ALIAS_PORTF_MGMT + "." + C_PROD_UNDER_ACCT_CODE
                 + " = ";
      criteria = criteria + C_ALIAS_PRODUCT_ACCOUNT + "."
                 + C_PROD_UNDER_ACCT_CODE + " AND ";
      criteria = criteria + "TRIM(CAST(" + C_ALIAS_PRODUCT_ACCOUNT + "."
                 + C_CUR_ACCT_NBR + " AS CHAR(11))) = ";
      criteria = criteria + "TRIM(" + C_ALIAS_POINT_ACCT_INVST + "."
                 + C_CUR_ACCT_NBR + ") AND ";
      criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_NBR + " = "
                 + C_ALIAS_PRODUCT_ACCOUNT + "." + C_RELTN_NBR + " AND ";
      criteria = criteria + C_ALIAS_PORTF_MGMT + "." + C_REC_STAT_CODE + " != "
                 + "'" + BaseTplBkrPortfMgmtEntity.C_REC_STAT_CODE_INACTIVE
                 + "'" + " AND (";

      if ( custNbr_ != null && !"".equals( custNbr_ ) )
      {
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_1_NBR
                   + " = ? OR ";
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_2_NBR
                   + " = ? OR ";
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_3_NBR
                   + " = ? OR ";
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_4_NBR
                   + " = ? OR ";
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_5_NBR
                   + " = ? )";
      }

      if ( criteria.length() > 0 )
      {
        query.append( "	WHERE " + criteria );
      }

      query.append( " ORDER BY " + C_ALIAS_PRODUCT_ACCOUNT + "."
                    + C_CUR_ACCT_NBR );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( custNbr_ != null && !"".equals( custNbr_ ) )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
        preparedStatement.setLong( count++, custNbr_.longValue() );
        preparedStatement.setLong( count++, custNbr_.longValue() );
        preparedStatement.setLong( count++, custNbr_.longValue() );
        preparedStatement.setLong( count++, custNbr_.longValue() );
      }

	  preparedStatement.replaceParametersInQuery(query.toString());
      resultSet = preparedStatement.executeQuery();

      rsds = new ResultSetDataSet( resultSet );
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

    return rsds;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtDAO#listPortfolio(java.math.BigInteger)
   */
  public DataSet listPortfolio( BigInteger custNbr_, String loggedUser_ )
                                                                         throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "SELECT DISTINCT " );
      query.append( C_ALIAS_PRODUCT_ACCOUNT + "." + C_CUR_ACCT_NBR + ", " );
      query.append( C_ALIAS_POINT_ACCT_INVST + "." + C_INVST_CUR_ACCT_NBR
                    + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_PROD_ACCT_CODE + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_PORTF_MGMT_PROD_NAME + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_CUST_MNMC_NAME + ", " );
      query.append( C_ALIAS_PRODUCT_ACCOUNT + "." + C_PROD_CODE + ", " );
      query.append( C_ALIAS_PRODUCT_ACCOUNT + "." + C_REC_STAT_CODE + ", " );
      query.append( C_ALIAS_TPL_BKR_PORTF_MGMT_MOV + "." + C_LAST_UPD_USER_ID );
      query.append( " FROM " );
      query.append( C_TABLE_PROD_ACCT_PORTF_MGMT + " " + C_ALIAS_PORTF_MGMT
                    + ", " );
      query.append( C_TABLE_POINT_ACCT_INVST + " " + C_ALIAS_POINT_ACCT_INVST
                    + ", " );
      query.append( C_TABLE_PRODUCT_ACCOUNT + " " + C_ALIAS_PRODUCT_ACCOUNT
                    + ", " );
      query.append( C_TABLE_RELATION_PRVT + " " + C_ALIAS_RELATION_PRVT + ", " );

      query.append( C_TABLE_BKR_PORTF_MGMT_MOV + " "
                    + C_ALIAS_TPL_BKR_PORTF_MGMT_MOV );

      String criteria = "";

      criteria = criteria + C_ALIAS_PORTF_MGMT + "." + C_PROD_ACCT_CODE + " = ";
      criteria = criteria + C_ALIAS_PRODUCT_ACCOUNT + "." + C_PROD_ACCT_CODE
                 + " AND ";

      criteria = criteria + C_ALIAS_PORTF_MGMT + "." + C_PROD_UNDER_ACCT_CODE
                 + " = ";
      criteria = criteria + C_ALIAS_PRODUCT_ACCOUNT + "."
                 + C_PROD_UNDER_ACCT_CODE + " AND ";

      criteria = criteria + C_ALIAS_PORTF_MGMT + "." + C_PROD_ACCT_CODE + " = ";
      criteria = criteria + C_ALIAS_TPL_BKR_PORTF_MGMT_MOV + "."
                 + C_PROD_ACCT_CODE + " AND ";
      criteria = criteria + C_ALIAS_PORTF_MGMT + "." + C_PROD_UNDER_ACCT_CODE
                 + " = ";
      criteria = criteria + C_ALIAS_TPL_BKR_PORTF_MGMT_MOV + "."
                 + C_PROD_UNDER_ACCT_CODE + " AND ";

      /*criteria = criteria + "TRIM(CAST(" + C_ALIAS_PRODUCT_ACCOUNT + "."
                 + C_CUR_ACCT_NBR + " AS CHAR(11))) = ";
      criteria = criteria + "TRIM(" + C_ALIAS_POINT_ACCT_INVST + "."
                 + C_CUR_ACCT_NBR + ") AND ";*/
                 
	  //Alteraçao G&P INICIO 19/06/2008    
	  criteria = criteria + " " + C_ALIAS_PRODUCT_ACCOUNT + "."
				 + C_CUR_ACCT_NBR + " = ";
	  criteria = criteria + " " + C_ALIAS_POINT_ACCT_INVST + "."
				 + C_CUR_ACCT_NBR + " AND ";
      //Alteraçao G&P FIM                 
  
        
                 
      criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_NBR + " = "
                 + C_ALIAS_PRODUCT_ACCOUNT + "." + C_RELTN_NBR + " AND ";
      criteria = criteria + C_ALIAS_PORTF_MGMT + "." + C_REC_STAT_CODE + " != "
                 + "'" + BaseTplBkrPortfMgmtEntity.C_REC_STAT_CODE_INACTIVE
                 + "'" + " AND (";

      if ( custNbr_ != null && !"".equals( custNbr_ ) )
      {
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_1_NBR
                   + " = ? OR ";
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_2_NBR
                   + " = ? OR ";
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_3_NBR
                   + " = ? OR ";
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_4_NBR
                   + " = ? OR ";
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_5_NBR
                   + " = ? )";
      }

      if ( criteria.length() > 0 )
      {
        query.append( "	WHERE " + criteria );
      }

      query.append( " ORDER BY " + C_ALIAS_PRODUCT_ACCOUNT + "."
                    + C_CUR_ACCT_NBR );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( custNbr_ != null && !"".equals( custNbr_ ) )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
        preparedStatement.setLong( count++, custNbr_.longValue() );
        preparedStatement.setLong( count++, custNbr_.longValue() );
        preparedStatement.setLong( count++, custNbr_.longValue() );
        preparedStatement.setLong( count++, custNbr_.longValue() );
      }

	  preparedStatement.replaceParametersInQuery(query.toString());
      resultSet = preparedStatement.executeQuery();

      rsds = new ResultSetDataSet( resultSet );
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

    return rsds;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtDAO#listForBkrPorftMgmtGrid(java.lang.String)
   */
  public ArrayList listForBkrPorftMgmtGrid( BigInteger prodAcctCode_,
                                           BigInteger prodUnderAcctCode_ )
  {
    TplBkrPortfMgmtMovEntity tplBkrPortfMgmtEntity;
    TplBkrPortfMgmtMovEntityVO tplBkrPortfMgmtEntityVO;
    DataSetRow row;

    ArrayList result = new ArrayList();

    DataSet rds = this.list( prodAcctCode_, prodUnderAcctCode_ );

    for ( int indexRow = 0; indexRow < rds.size(); indexRow++ )
    {
      tplBkrPortfMgmtEntity = new TplBkrPortfMgmtMovEntity();
      tplBkrPortfMgmtEntityVO = ( TplBkrPortfMgmtMovEntityVO ) tplBkrPortfMgmtEntity.getData();

      row = rds.getRow( indexRow );

      tplBkrPortfMgmtEntityVO.setBkrCnpjNbr( row.getStringByName( C_BKR_CNPJ_NBR ) );
      tplBkrPortfMgmtEntityVO.setBkrNameText( row.getStringByName( C_BKR_NAME_TEXT ) );
      tplBkrPortfMgmtEntityVO.setBovespaCurAcctNbr( row.getStringByName( C_BOVESPA_CUR_ACCT_NBR ) );
      tplBkrPortfMgmtEntityVO.setBovespaInvstAcctNbr( row.getStringByName( C_BOVESPA_INVST_ACCT_NBR ) );
      tplBkrPortfMgmtEntityVO.setBmfCurAcctNbr( row.getStringByName( C_BMF_CUR_ACCT_NBR ) );
      tplBkrPortfMgmtEntityVO.setBmfInvstAcctNbr( row.getStringByName( C_BMF_INVST_ACCT_NBR ) );
      tplBkrPortfMgmtEntityVO.setProdAcctCode( new BigInteger(
                                                               row.getStringByName( C_PROD_ACCT_CODE ) ) );
      tplBkrPortfMgmtEntityVO.setProdUnderAcctCode( new BigInteger(
                                                                    row.getStringByName( C_PROD_UNDER_ACCT_CODE ) ) );

      java.util.Date lastUpdDate = null;
      try
      {
        SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        lastUpdDate = df.parse( row.getStringByName( C_LAST_UPD_DATE ) );
      }
      catch ( Exception e )
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, e );
      }

      tplBkrPortfMgmtEntityVO.setLastUpdDate( lastUpdDate );

      tplBkrPortfMgmtEntityVO.setLastUpdUserId( row.getStringByName( C_LAST_UPD_USER_ID ) );
      tplBkrPortfMgmtEntityVO.setOpernCode( row.getStringByName( C_OPERN_TEXT ) );

      result.add( tplBkrPortfMgmtEntity );
    }

    return result;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtDAO#list(java.math.BigInteger,
   *      java.math.BigInteger)
   */
  public DataSet list( BigInteger prodAcctCode_, BigInteger prodUnderAcctCode_ )
                                                                                throws UnexpectedException
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
      query.append( C_ALIAS_BROKER + "." + C_BKR_CNPJ_NBR + ", " );
      query.append( C_ALIAS_BROKER + "." + C_BKR_NAME_TEXT + ", " );
      query.append( C_ALIAS_TPL_BKR_PORTF_MGMT_MOV + "."
                    + C_BOVESPA_CUR_ACCT_NBR + ", " );
      query.append( C_ALIAS_TPL_BKR_PORTF_MGMT_MOV + "."
                    + C_BOVESPA_INVST_ACCT_NBR + ", " );
      query.append( C_ALIAS_TPL_BKR_PORTF_MGMT_MOV + "." + C_BMF_CUR_ACCT_NBR
                    + ", " );
      query.append( C_ALIAS_TPL_BKR_PORTF_MGMT_MOV + "." + C_BMF_INVST_ACCT_NBR
                    + ", " );
      query.append( C_ALIAS_TPL_BKR_PORTF_MGMT_MOV + "." + C_PROD_ACCT_CODE
                    + ", " );
      query.append( C_ALIAS_TPL_BKR_PORTF_MGMT_MOV + "."
                    + C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_ALIAS_TPL_BKR_PORTF_MGMT_MOV + "." + C_LAST_UPD_DATE
                    + ", " );
      query.append( C_ALIAS_TPL_BKR_PORTF_MGMT_MOV + "." + C_LAST_UPD_USER_ID
                    + ", " );
      query.append( C_ALIAS_TPL_BKR_PORTF_MGMT_MOV + "." + C_OPERN_CODE );
      query.append( " FROM " );
      query.append( C_TABLE_BROKER + " " + C_ALIAS_BROKER + ", " );
      query.append( C_TABLE_BKR_PORTF_MGMT_MOV + " "
                    + C_ALIAS_TPL_BKR_PORTF_MGMT_MOV );
      query.append( " WHERE " );

      String criteria = "";

      criteria = criteria + C_ALIAS_TPL_BKR_PORTF_MGMT_MOV + "."
                 + C_BKR_CNPJ_NBR + " = " + C_ALIAS_BROKER + "."
                 + C_BKR_CNPJ_NBR + " AND ";

      if ( prodAcctCode_ != null && prodAcctCode_.longValue() != 0 )
      {
        criteria = criteria + C_ALIAS_TPL_BKR_PORTF_MGMT_MOV + "."
                   + C_PROD_ACCT_CODE + " = ? AND ";
      }
      if ( prodUnderAcctCode_ != null && prodUnderAcctCode_.longValue() != 0 )
      {
        criteria = criteria + C_ALIAS_TPL_BKR_PORTF_MGMT_MOV + "."
                   + C_PROD_UNDER_ACCT_CODE + " = ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        criteria = criteria + " ORDER BY " + C_ALIAS_BROKER + "."
                   + C_BKR_NAME_TEXT;
        query.append( criteria );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( prodAcctCode_ != null && prodAcctCode_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, prodAcctCode_.longValue() );
      }
      if ( prodUnderAcctCode_ != null && prodUnderAcctCode_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, prodUnderAcctCode_.longValue() );
      }

	  preparedStatement.replaceParametersInQuery(query.toString());
      resultSet = preparedStatement.executeQuery();

      rsds = new ResultSetDataSet( resultSet );
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
    rsds.outerJoin( ODSConstraintDecoder.decodeOpern(), codeColumn, codeColumn,
                    nameColumn );
    return rsds;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtDAO#listPortfolio(java.math.BigInteger)
   */
  public DataSet listAuthorizedPortfolio( BigInteger custNbr_,
                                         String loggedUser_ )
                                                             throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "SELECT DISTINCT " );
      query.append( C_ALIAS_PRODUCT_ACCOUNT + "." + C_CUR_ACCT_NBR + ", " );
      query.append( C_ALIAS_POINT_ACCT_INVST + "." + C_INVST_CUR_ACCT_NBR
                    + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_PROD_ACCT_CODE + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_PORTF_MGMT_PROD_NAME + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_CUST_MNMC_NAME + ", " );
      query.append( C_ALIAS_PRODUCT_ACCOUNT + "." + C_PROD_CODE + ", " );
      query.append( C_ALIAS_PRODUCT_ACCOUNT + "." + C_REC_STAT_CODE + ", " );
      query.append( C_ALIAS_TPL_BKR_PORTF_MGMT_MOV + "." + C_LAST_UPD_USER_ID );
      query.append( " FROM " );
      query.append( C_TABLE_PROD_ACCT_PORTF_MGMT + " " + C_ALIAS_PORTF_MGMT
                    + ", " );
      query.append( C_TABLE_POINT_ACCT_INVST + " " + C_ALIAS_POINT_ACCT_INVST
                    + ", " );
      query.append( C_TABLE_PRODUCT_ACCOUNT + " " + C_ALIAS_PRODUCT_ACCOUNT
                    + ", " );
      query.append( C_TABLE_RELATION_PRVT + " " + C_ALIAS_RELATION_PRVT + ", " );

      query.append( C_TABLE_BKR_PORTF_MGMT_MOV + " "
                    + C_ALIAS_TPL_BKR_PORTF_MGMT_MOV );

      String criteria = "";

      criteria = criteria + C_ALIAS_PORTF_MGMT + "." + C_PROD_ACCT_CODE + " = ";
      criteria = criteria + C_ALIAS_PRODUCT_ACCOUNT + "." + C_PROD_ACCT_CODE
                 + " AND ";

      criteria = criteria + C_ALIAS_PORTF_MGMT + "." + C_PROD_UNDER_ACCT_CODE
                 + " = ";
      criteria = criteria + C_ALIAS_PRODUCT_ACCOUNT + "."
                 + C_PROD_UNDER_ACCT_CODE + " AND ";

      criteria = criteria + C_ALIAS_PORTF_MGMT + "." + C_PROD_ACCT_CODE + " = ";
      criteria = criteria + C_ALIAS_TPL_BKR_PORTF_MGMT_MOV + "."
                 + C_PROD_ACCT_CODE + " AND ";
      criteria = criteria + C_ALIAS_PORTF_MGMT + "." + C_PROD_UNDER_ACCT_CODE
                 + " = ";
      criteria = criteria + C_ALIAS_TPL_BKR_PORTF_MGMT_MOV + "."
                 + C_PROD_UNDER_ACCT_CODE + " AND ";

      criteria = criteria + "TRIM(CAST(" + C_ALIAS_PRODUCT_ACCOUNT + "."
                 + C_CUR_ACCT_NBR + " AS CHAR(11))) = ";
      criteria = criteria + "TRIM(" + C_ALIAS_POINT_ACCT_INVST + "."
                 + C_CUR_ACCT_NBR + ") AND ";
      criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_NBR + " = "
                 + C_ALIAS_PRODUCT_ACCOUNT + "." + C_RELTN_NBR + " AND ";
      criteria = criteria + C_ALIAS_PORTF_MGMT + "." + C_REC_STAT_CODE + " != "
                 + "'" + BaseTplBkrPortfMgmtEntity.C_REC_STAT_CODE_INACTIVE
                 + "'" + " AND (";

      if ( custNbr_ != null && !"".equals( custNbr_ ) )
      {
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_1_NBR
                   + " = ? OR ";
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_2_NBR
                   + " = ? OR ";
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_3_NBR
                   + " = ? OR ";
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_4_NBR
                   + " = ? OR ";
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_5_NBR
                   + " = ? )";
      }

      if ( loggedUser_ != null && !"".equals( loggedUser_ ) )
      {
        criteria = criteria + " AND " + C_ALIAS_TPL_BKR_PORTF_MGMT_MOV + "."
                   + C_LAST_UPD_USER_ID + " = ? ";

      }

      if ( criteria.length() > 0 )
      {
        query.append( "	WHERE " + criteria );
      }

      query.append( " ORDER BY " + C_ALIAS_PRODUCT_ACCOUNT + "."
                    + C_CUR_ACCT_NBR );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( custNbr_ != null && !"".equals( custNbr_ ) )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
        preparedStatement.setLong( count++, custNbr_.longValue() );
        preparedStatement.setLong( count++, custNbr_.longValue() );
        preparedStatement.setLong( count++, custNbr_.longValue() );
        preparedStatement.setLong( count++, custNbr_.longValue() );
      }

      if ( loggedUser_ != null && !"".equals( loggedUser_ ) )
      {
        preparedStatement.setString( count++, loggedUser_ );
      }

	  preparedStatement.replaceParametersInQuery(query.toString());
      resultSet = preparedStatement.executeQuery();

      rsds = new ResultSetDataSet( resultSet );
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

    return rsds;
  }

  /**
   * Remove uma linha na tabela TPL_BROKERMov de acordo com ID passado como
   * parâmetro.
   * @param entityKey_
   * @throws UnexpectedException
   * @author Hamilton Matos
   */

  public TplBkrPortfMgmtMovEntity deleteFromMovement(
                                                     TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity_ )
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
      sqlQuery.append( C_PROD_ACCT_CODE + " = ? AND " );
      sqlQuery.append( C_PROD_UNDER_ACCT_CODE + " = ? AND " );
      sqlQuery.append( C_BKR_CNPJ_NBR + " = ? " );
      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));
      preparedStatement.setLong(
                         1,
                         tplBkrPortfMgmtMovEntity_.getData().getProdAcctCode().longValue() );
      preparedStatement.setLong(
                         2,
                         tplBkrPortfMgmtMovEntity_.getData().getProdUnderAcctCode().longValue() );

      preparedStatement.setString( 3,
                           tplBkrPortfMgmtMovEntity_.getData().getBkrCnpjNbr() );

	  preparedStatement.replaceParametersInQuery(sqlQuery.toString());
      preparedStatement.executeUpdate();
	  
      return tplBkrPortfMgmtMovEntity_;
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

}