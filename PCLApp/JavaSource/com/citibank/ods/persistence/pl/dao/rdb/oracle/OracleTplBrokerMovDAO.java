package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplBrokerEntity;
import com.citibank.ods.entity.pl.TplBrokerMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplBrokerMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplBrokerMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * Implementação Oracle para DAO da tabela TPL_BROKERMov
 * @author Hamilton Matos
 */
public class OracleTplBrokerMovDAO extends BaseOracleTplBrokerDAO implements
    TplBrokerMovDAO
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
  public TplBrokerMovEntity insert( TplBrokerMovEntity tplBrokerMovEntity_ )
                                                                            throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer sqlQuery = new StringBuffer();
    sqlQuery.append( "INSERT INTO " + C_TABLE_NAME + "( " );
    sqlQuery.append( C_OPERN_CODE + ", " );
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
    sqlQuery.append( C_LAST_UPD_USER_ID + ") " );

    sqlQuery.append( " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" );
    try
    {
      connection = OracleODSDAOFactory.getConnection();
      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

      TplBrokerMovEntityVO tplBrokerMovEntityVO = ( TplBrokerMovEntityVO ) tplBrokerMovEntity_.getData();

      int count = 1;

      if ( tplBrokerMovEntityVO.getOpernCode() != null
           && !tplBrokerMovEntityVO.getOpernCode().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplBrokerMovEntityVO ) tplBrokerMovEntity_.getData() ).getOpernCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBrokerMovEntityVO.getBkrAddrText() != null
           && !tplBrokerMovEntityVO.getBkrAddrText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerMovEntity_.getData().getBkrAddrText() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplBrokerMovEntityVO.getBkrApprvCrLimDlrAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerMovEntity_.getData().getBkrApprvCrLimDlrAmt() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerMovEntityVO.getBkrApprvCrLimLcyAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerMovEntity_.getData().getBkrApprvCrLimLcyAmt() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerMovEntityVO.getBkrApprvDate() != null
           && !tplBrokerMovEntityVO.getBkrApprvDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerMovEntity_.getData().getBkrApprvDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplBrokerMovEntityVO.getBkrApprvStatCode() != null
           && !tplBrokerMovEntityVO.getBkrApprvStatCode().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplBrokerMovEntity_.getData().getBkrApprvStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerMovEntityVO.getBkrAuthProcSitText() != null
           && !tplBrokerMovEntityVO.getBkrAuthProcSitText().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplBrokerMovEntity_.getData().getBkrAuthProcSitText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerMovEntityVO.getBkrBmfMktCode() != null
           && !tplBrokerMovEntityVO.getBkrBmfMktCode().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerMovEntity_.getData().getBkrBmfMktCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerMovEntityVO.getBkrBovespaMktCode() != null
           && !tplBrokerMovEntityVO.getBkrBovespaMktCode().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplBrokerMovEntity_.getData().getBkrBovespaMktCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerMovEntityVO.getBkrCnpjNbr() != null
           && !tplBrokerMovEntityVO.getBkrCnpjNbr().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerMovEntity_.getData().getBkrCnpjNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBrokerMovEntityVO.getBkrCommentText() != null
           && !tplBrokerMovEntityVO.getBkrCommentText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerMovEntity_.getData().getBkrCommentText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerMovEntityVO.getBkrNameText() != null
           && !tplBrokerMovEntityVO.getBkrNameText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerMovEntity_.getData().getBkrNameText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerMovEntityVO.getBkrRbtBmfRate() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerMovEntity_.getData().getBkrRbtBmfRate() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerMovEntityVO.getBkrRbtBovespaRate() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerMovEntity_.getData().getBkrRbtBovespaRate() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerMovEntityVO.getBkrReqCrLimDlrAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerMovEntity_.getData().getBkrReqCrLimDlrAmt() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerMovEntityVO.getBkrReqCrLimLcyAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerMovEntity_.getData().getBkrReqCrLimLcyAmt() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerMovEntityVO.getBkrReqDate() != null
           && !tplBrokerMovEntityVO.getBkrReqDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerMovEntity_.getData().getBkrReqDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplBrokerMovEntityVO.getBkrRnwDate() != null
           && !tplBrokerMovEntityVO.getBkrRnwDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerMovEntity_.getData().getBkrRnwDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplBrokerMovEntityVO.getBkrSuplServText() != null
           && !tplBrokerMovEntityVO.getBkrSuplServText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerMovEntity_.getData().getBkrSuplServText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerMovEntityVO.getLastUpdDate() != null
           && !tplBrokerMovEntityVO.getLastUpdDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerMovEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      
      if ( tplBrokerMovEntityVO.getLastUpdUserId() != null
           && !tplBrokerMovEntityVO.getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerMovEntity_.getData().getLastUpdUserId() );
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
    return tplBrokerMovEntity_;
  }

  /**
   * Atualiza uma linha na tabela TPL_BROKER_Mov de acordo com ID contido na
   * entidade passada como parâmetro.
   * @param tplBrokerMovEntity__
   * @throws UnexpectedException
   * @author Hamilton Matos
   */
  public TplBrokerMovEntity update( TplBrokerMovEntity tplBrokerMovEntity_ )
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
    sqlQuery.append( C_OPERN_CODE + " = ?, " );
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
    sqlQuery.append( C_LAST_UPD_USER_ID + " = ? " );

    sqlQuery.append( " WHERE " );
    sqlQuery.append( C_BKR_CNPJ_NBR + " = ? " );
    try
    {
      connection = OracleODSDAOFactory.getConnection();
      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

      TplBrokerMovEntityVO tplBrokerMovEntityVO = ( TplBrokerMovEntityVO ) tplBrokerMovEntity_.getData();

      int count = 1;

      if ( tplBrokerMovEntityVO.getOpernCode() != null
           && !tplBrokerMovEntityVO.getOpernCode().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplBrokerMovEntityVO ) tplBrokerMovEntity_.getData() ).getOpernCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBrokerMovEntityVO.getBkrAddrText() != null
           && !tplBrokerMovEntityVO.getBkrAddrText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerMovEntity_.getData().getBkrAddrText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerMovEntityVO.getBkrApprvCrLimDlrAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerMovEntity_.getData().getBkrApprvCrLimDlrAmt() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerMovEntityVO.getBkrApprvCrLimLcyAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerMovEntity_.getData().getBkrApprvCrLimLcyAmt() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerMovEntityVO.getBkrApprvDate() != null
           && !tplBrokerMovEntityVO.getBkrApprvDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerMovEntity_.getData().getBkrApprvDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplBrokerMovEntityVO.getBkrApprvStatCode() != null
           && !tplBrokerMovEntityVO.getBkrApprvStatCode().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplBrokerMovEntity_.getData().getBkrApprvStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerMovEntityVO.getBkrAuthProcSitText() != null
           && !tplBrokerMovEntityVO.getBkrAuthProcSitText().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplBrokerMovEntity_.getData().getBkrAuthProcSitText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerMovEntityVO.getBkrBmfMktCode() != null
           && !tplBrokerMovEntityVO.getBkrBmfMktCode().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerMovEntity_.getData().getBkrBmfMktCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerMovEntityVO.getBkrBovespaMktCode() != null
           && !tplBrokerMovEntityVO.getBkrBovespaMktCode().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplBrokerMovEntity_.getData().getBkrBovespaMktCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerMovEntityVO.getBkrCommentText() != null
           && !tplBrokerMovEntityVO.getBkrCommentText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerMovEntity_.getData().getBkrCommentText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerMovEntityVO.getBkrNameText() != null
           && !tplBrokerMovEntityVO.getBkrNameText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerMovEntity_.getData().getBkrNameText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerMovEntityVO.getBkrRbtBmfRate() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerMovEntity_.getData().getBkrRbtBmfRate() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerMovEntityVO.getBkrRbtBovespaRate() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerMovEntity_.getData().getBkrRbtBovespaRate() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerMovEntityVO.getBkrReqCrLimDlrAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerMovEntity_.getData().getBkrReqCrLimDlrAmt() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerMovEntityVO.getBkrReqCrLimLcyAmt() != null )
      {
        preparedStatement.setBigDecimal(
                                 count++,
                                 tplBrokerMovEntity_.getData().getBkrReqCrLimLcyAmt() );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.DECIMAL );
      }

      if ( tplBrokerMovEntityVO.getBkrReqDate() != null
           && !tplBrokerMovEntityVO.getBkrReqDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerMovEntity_.getData().getBkrReqDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplBrokerMovEntityVO.getBkrRnwDate() != null
           && !tplBrokerMovEntityVO.getBkrRnwDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerMovEntity_.getData().getBkrRnwDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplBrokerMovEntityVO.getBkrSuplServText() != null
           && !tplBrokerMovEntityVO.getBkrSuplServText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerMovEntity_.getData().getBkrSuplServText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBrokerMovEntityVO.getLastUpdDate() != null
           && !tplBrokerMovEntityVO.getLastUpdDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBrokerMovEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBrokerMovEntityVO.getLastUpdUserId() != null
           && !tplBrokerMovEntityVO.getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerMovEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBrokerMovEntityVO.getBkrCnpjNbr() != null
           && !tplBrokerMovEntityVO.getBkrCnpjNbr().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplBrokerMovEntity_.getData().getBkrCnpjNbr() );
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
    return tplBrokerMovEntity_;

  }

  /**
   * Remove uma linha na tabela TPL_BROKERMov de acordo com ID passado como
   * parâmetro.
   * @param entityKey_
   * @throws UnexpectedException
   * @author Hamilton Matos
   */

  public TplBrokerMovEntity delete( TplBrokerMovEntity tplBrokerMovEntity_ )
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
      preparedStatement.setString( 1, tplBrokerMovEntity_.getData().getBkrCnpjNbr() );
	  preparedStatement.replaceParametersInQuery(sqlQuery.toString());
      preparedStatement.executeUpdate();
	  
      return tplBrokerMovEntity_;
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

  public BaseTplBrokerEntity find( BaseTplBrokerEntity tplBrokerEntity_ )
                                                                         throws UnexpectedException
  {
    ResultSet resultSet = null;
    CitiStatement preparedStatement = null;
    ManagedRdbConnection connection = null;
    StringBuffer sqlQuery = new StringBuffer();
    ArrayList tplBrokerMovEntities = new ArrayList();
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

      preparedStatement.setString( 1, tplBrokerEntity_.getData().getBkrCnpjNbr() );

	  preparedStatement.replaceParametersInQuery(sqlQuery.toString());
      resultSet = preparedStatement.executeQuery();

      tplBrokerMovEntities = instantiateFromResultSet( resultSet );

      if ( tplBrokerMovEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplBrokerMovEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplBrokerEntity = ( BaseTplBrokerEntity ) tplBrokerMovEntities.get( 0 );
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

  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
                                                                    throws UnexpectedException
  {
    TplBrokerMovEntityVO tplBrokerMovEntityVO;
    TplBrokerMovEntity tplBrokerMovEntity;
    ArrayList tplBrokerMovEntities = new ArrayList();
    try
    {
      while ( resultSet_.next() )
      {

        tplBrokerMovEntity = new TplBrokerMovEntity();

        tplBrokerMovEntity.getData().setBkrAddrText(
                                                     resultSet_.getString( C_BKR_ADDR_TEXT ) );

        if ( resultSet_.getString( C_BKR_APPRV_CR_LIM_DLR_AMT ) != null )
        {
          tplBrokerMovEntity.getData().setBkrApprvCrLimDlrAmt(
                                                               new BigDecimal(
                                                                               resultSet_.getString( C_BKR_APPRV_CR_LIM_DLR_AMT ) ) );
        }
        else
        {
          tplBrokerMovEntity.getData().setBkrApprvCrLimDlrAmt( null );
        }

        if ( resultSet_.getString( C_BKR_APPRV_CR_LIM_LCY_AMT ) != null )
        {
          tplBrokerMovEntity.getData().setBkrApprvCrLimLcyAmt(
                                                               new BigDecimal(
                                                                               resultSet_.getString( C_BKR_APPRV_CR_LIM_LCY_AMT ) ) );
        }
        else
        {
          tplBrokerMovEntity.getData().setBkrApprvCrLimLcyAmt( null );

        }
        tplBrokerMovEntity.getData().setBkrApprvDate(
                                                      resultSet_.getDate( C_BKR_APPRV_DATE ) );

        tplBrokerMovEntity.getData().setBkrApprvStatCode(
                                                          resultSet_.getString( C_BKR_APPRV_STAT_CODE ) );

        tplBrokerMovEntity.getData().setBkrAuthProcSitText(
                                                            resultSet_.getString( C_BKR_AUTH_PROC_SIT_TEXT ) );

        tplBrokerMovEntity.getData().setBkrBmfMktCode(
                                                       resultSet_.getString( C_BKR_BMF_MKT_CODE ) );

        tplBrokerMovEntity.getData().setBkrBovespaMktCode(
                                                           resultSet_.getString( C_BKR_BOVESPA_MKT_CODE ) );

        tplBrokerMovEntity.getData().setBkrCnpjNbr(
                                                    resultSet_.getString( C_BKR_CNPJ_NBR ) );

        tplBrokerMovEntity.getData().setBkrCommentText(
                                                        resultSet_.getString( C_BKR_COMMENT_TEXT ) );

        tplBrokerMovEntity.getData().setBkrNameText(
                                                     resultSet_.getString( C_BKR_NAME_TEXT ) );

        if ( resultSet_.getString( C_BKR_RBT_BMF_RATE ) != null )
        {

          tplBrokerMovEntity.getData().setBkrRbtBmfRate(
                                                         new BigDecimal(
                                                                         resultSet_.getString( C_BKR_RBT_BMF_RATE ) ) );
        }
        else
        {
          tplBrokerMovEntity.getData().setBkrRbtBmfRate( null );
        }

        if ( resultSet_.getString( C_BKR_RBT_BOVESPA_RATE ) != null )
        {
          tplBrokerMovEntity.getData().setBkrRbtBovespaRate(
                                                             new BigDecimal(
                                                                             resultSet_.getString( C_BKR_RBT_BOVESPA_RATE ) ) );
        }
        else
        {
          tplBrokerMovEntity.getData().setBkrRbtBovespaRate( null );
        }

        if ( resultSet_.getString( C_BKR_REQ_CR_LIM_DLR_AMT ) != null )
        {
          tplBrokerMovEntity.getData().setBkrReqCrLimDlrAmt(
                                                             new BigDecimal(
                                                                             resultSet_.getString( C_BKR_REQ_CR_LIM_DLR_AMT ) ) );
        }
        else
        {
          tplBrokerMovEntity.getData().setBkrReqCrLimDlrAmt( null );

        }
        if ( resultSet_.getString( C_BKR_REQ_CR_LIM_LCY_AMT ) != null )
        {
          tplBrokerMovEntity.getData().setBkrReqCrLimLcyAmt(
                                                             new BigDecimal(
                                                                             resultSet_.getString( C_BKR_REQ_CR_LIM_LCY_AMT ) ) );
        }
        else
        {
          tplBrokerMovEntity.getData().setBkrReqCrLimLcyAmt( null );
        }
        tplBrokerMovEntity.getData().setBkrReqDate(
                                                    resultSet_.getDate( C_BKR_REQ_DATE ) );

        tplBrokerMovEntity.getData().setBkrRnwDate(
                                                    resultSet_.getDate( C_BKR_RNW_DATE ) );

        tplBrokerMovEntity.getData().setBkrSuplServText(
                                                         resultSet_.getString( C_BKR_SUPL_SERV_TEXT ) );

        tplBrokerMovEntity.getData().setLastUpdDate(
                                                     resultSet_.getTimestamp( C_LAST_UPD_DATE ) );

        tplBrokerMovEntity.getData().setLastUpdUserId(
                                                       resultSet_.getString( C_LAST_UPD_USER_ID ) );

        //      Casting para a atribuicao das colunas especificas
        tplBrokerMovEntityVO = ( TplBrokerMovEntityVO ) tplBrokerMovEntity.getData();
        tplBrokerMovEntityVO.setOpernCode( resultSet_.getString( C_OPERN_CODE ) );

        tplBrokerMovEntities.add( tplBrokerMovEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return tplBrokerMovEntities;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplBrokerMovDAO#exists(com.citibank.ods.entity.pl.TplBrokerMovEntity)
   */
  public boolean exists( TplBrokerMovEntity tplBrokerMovEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tplBrokerMovEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  public DataSet list( String bkrCnpjNbr_, String bkrNameText_,
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
      sqlQuery.append( " ORDER BY " + C_BKR_NAME_TEXT );

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