package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplBrokerEntity;
import com.citibank.ods.entity.pl.TplBrokerHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplBrokerHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplBrokerHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * Implementação Oracle para DAO da tabela TPL_BROKERHist
 * @author Hamilton Matos
 */
public class OracleTplBrokerHistDAO extends BaseOracleTplBrokerDAO implements
    TplBrokerHistDAO
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
   * @param tplBrokerHistEntity__
   * @throws UnexpectedException
   * @author Hamilton Matos
   * @date 13/07/2007
   */
  public TplBrokerHistEntity insert( TplBrokerHistEntity tplBrokerHistEntity_ )
                                                                               throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer sqlQuery = new StringBuffer();
    sqlQuery.append( "INSERT INTO " + C_TABLE_NAME + "( " );
    sqlQuery.append( C_BKR_REF_DATE + ", " );
    sqlQuery.append( C_LAST_AUTH_DATE + ", " );
    sqlQuery.append( C_LAST_AUTH_USER_ID + ", " );
    sqlQuery.append( C_REC_STAT_CODE + ", " + C_BKR_ADDR_TEXT + ", " );
    sqlQuery.append( C_BKR_APPRV_CR_LIM_DLR_AMT + ", " );
    sqlQuery.append( C_BKR_APPRV_CR_LIM_LCY_AMT + ", " );
    sqlQuery.append( C_BKR_APPRV_DATE + ", " );
    sqlQuery.append( C_BKR_APPRV_STAT_CODE + ", " );
    sqlQuery.append( C_BKR_AUTH_PROC_SIT_TEXT + ", " );
    sqlQuery.append( C_BKR_BMF_MKT_CODE + ", " );
    sqlQuery.append( C_BKR_BOVESPA_MKT_CODE + ", " );
    sqlQuery.append( C_BKR_CNPJ_NBR + ", " + C_BKR_COMMENT_TEXT + ", " );
    sqlQuery.append( C_BKR_NAME_TEXT + ", " );
    sqlQuery.append( C_BKR_RBT_BMF_RATE + ", " );
    sqlQuery.append( C_BKR_RBT_BOVESPA_RATE + ", " );
    sqlQuery.append( C_BKR_REQ_CR_LIM_DLR_AMT + ", " );
    sqlQuery.append( C_BKR_REQ_CR_LIM_LCY_AMT + ", " );
    sqlQuery.append( C_BKR_REQ_DATE + ", " + C_BKR_RNW_DATE + ", " );
    sqlQuery.append( C_BKR_SUPL_SERV_TEXT + ", " );
    sqlQuery.append( C_LAST_UPD_DATE + ", " + C_LAST_UPD_USER_ID );
    sqlQuery.append( ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" );

    int count = 1;
    try
    {
      connection = OracleODSDAOFactory.getConnection();
      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

      if ( ( ( TplBrokerHistEntityVO ) tplBrokerHistEntity_.getData() ).getBkrRefDate() != null )
      {

        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplBrokerHistEntityVO ) tplBrokerHistEntity_.getData() ).getBkrRefDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( ( ( TplBrokerHistEntityVO ) tplBrokerHistEntity_.getData() ).getLastAuthDate() != null )
      {

        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplBrokerHistEntityVO ) tplBrokerHistEntity_.getData() ).getLastAuthDate().getTime() ) );

      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( ( ( TplBrokerHistEntityVO ) tplBrokerHistEntity_.getData() ).getLastAuthUserId() != null )
      {

        preparedStatement.setString(
                             count++,
                             ( ( TplBrokerHistEntityVO ) tplBrokerHistEntity_.getData() ).getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( ( ( TplBrokerHistEntityVO ) tplBrokerHistEntity_.getData() ).getRecStatCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplBrokerHistEntityVO ) tplBrokerHistEntity_.getData() ).getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerHistEntity_.getData().getBkrAddrText() != null )
      {
        preparedStatement.setString( count++,
                             tplBrokerHistEntity_.getData().getBkrAddrText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerHistEntity_.getData().getBkrApprvCrLimDlrAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerHistEntity_.getData().getBkrApprvCrLimDlrAmt() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerHistEntity_.getData().getBkrApprvCrLimLcyAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerHistEntity_.getData().getBkrApprvCrLimLcyAmt() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerHistEntity_.getData().getBkrApprvDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerHistEntity_.getData().getBkrApprvDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplBrokerHistEntity_.getData().getBkrApprvStatCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBrokerHistEntity_.getData().getBkrApprvStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerHistEntity_.getData().getBkrAuthProcSitText() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBrokerHistEntity_.getData().getBkrAuthProcSitText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerHistEntity_.getData().getBkrBmfMktCode() != null )
      {
        preparedStatement.setString( count++,
                             tplBrokerHistEntity_.getData().getBkrBmfMktCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplBrokerHistEntity_.getData().getBkrBovespaMktCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBrokerHistEntity_.getData().getBkrBovespaMktCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerHistEntity_.getData().getBkrCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplBrokerHistEntity_.getData().getBkrCnpjNbr() );
      }
      else
        preparedStatement.setString( count++, null );

      if ( tplBrokerHistEntity_.getData().getBkrCommentText() != null )
      {
        preparedStatement.setString( count++,
                             tplBrokerHistEntity_.getData().getBkrCommentText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerHistEntity_.getData().getBkrNameText() != null )
      {
        preparedStatement.setString( count++,
                             tplBrokerHistEntity_.getData().getBkrNameText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerHistEntity_.getData().getBkrRbtBmfRate() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerHistEntity_.getData().getBkrRbtBmfRate() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerHistEntity_.getData().getBkrRbtBovespaRate() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerHistEntity_.getData().getBkrRbtBovespaRate() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerHistEntity_.getData().getBkrReqCrLimDlrAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerHistEntity_.getData().getBkrReqCrLimDlrAmt() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerHistEntity_.getData().getBkrReqCrLimLcyAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerHistEntity_.getData().getBkrReqCrLimLcyAmt() );
      }

      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerHistEntity_.getData().getBkrReqDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerHistEntity_.getData().getBkrReqDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplBrokerHistEntity_.getData().getBkrRnwDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerHistEntity_.getData().getBkrRnwDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplBrokerHistEntity_.getData().getBkrSuplServText() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBrokerHistEntity_.getData().getBkrSuplServText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplBrokerHistEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerHistEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }
      if ( tplBrokerHistEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplBrokerHistEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, null );
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

    return tplBrokerHistEntity_;
  }

  public void update( TplBrokerHistEntity tplBrokerHistEntity_ )
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

  public TplBrokerHistEntity find( TplBrokerHistEntity tplBrokerHistEntity_ )
                                                                             throws UnexpectedException
  {
    return null;
  }

  public BaseTplBrokerEntity find( BaseTplBrokerEntity baseTplBrokerEntity_ )
                                                                             throws UnexpectedException
  {
    return null;
  }

}

