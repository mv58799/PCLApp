/*
 * Created on Mar 14, 2007
 *
 */
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
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplProdRiskCatPrvtEntity;
import com.citibank.ods.entity.pl.TplProdRiskCatPrvtHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdRiskCatPrvtHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author leonardo.nakada
 * 
 * 20110321
 * 
 * As funcionalidades que utilizavam a tabela PL.TPL_PROD_RISK_CAT_PRVT devem utilizar a 
 * tabela PL.TPL_RISK_INVST_PROD_RDIP (apenas funcionalidades que utilizem consulta, as 
 * telas de inserção e alteração serão removidas)
 * 
 * Com isso as funcionalidades da Hist nao seram mais usadas
 * 
 * *** 20110321 ***
 * As funcionalidades que utilizavam a tabela PL.TPL_PROD_RISK_CAT_PRVT devem utilizar a tabela
 * PL.TPL_RISK_INVST_PROD_RDIP (apenas funcionalidades que utilizem consulta, as telas de 
 * inserção e alteração serão removidas) 
 * 
 */
public class OracleTplProdRiskCatPrvtHistDAO extends
    BaseOracleTplProdRiskCatPrvtDAO implements TplProdRiskCatPrvtHistDAO 
{
  /*
   * Constante do nome da coluna da tabela de data de referência da tabela de
   * histórico
   */
  private String C_PROD_RISK_CAT_REF_DATE = "PROD_RISK_CAT_REF_DATE";

  /*
   * Constante que identifica o nome da tabel de histórico
   */
  private static final String C_TPL_PROD_RISK_CAT_PRVT_HIST = C_PL_SCHEMA + "TPL_PROD_RISK_CAT_PRVT_HIST";

  /*
   * Campos específicos da tabela
   */
//  private String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";
//
//  private String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";
//
//  private String C_REC_STAT_CODE = "REC_STAT_CODE";
//  
//  protected String C_REC_STAT_TEXT = "REC_STAT_TEXT";
//
//  /**
//   * Realiza a consulta de registros de acordo com um filtro pré-determinado.
//   * @see com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtHistDAO#list(java.math.BigInteger,
//   *      java.lang.String, java.util.Date)
//   */
//  public DataSet list( BigInteger prodRiskCatCode_, String prodRiskCatText_,
//                      Date prodRiskCatRefDate_ )
//  {
//    ManagedRdbConnection connection = null;
//    CitiStatement preparedStatement = null;
//    ResultSet resultSet = null;
//    ResultSetDataSet rsds = null;
//    StringBuffer query = new StringBuffer();
//
//    try
//    {
//      connection = OracleODSDAOFactory.getConnection();
//      query.append( "SELECT " );
//      query.append( C_PROD_INVST_RISK_CODE + ", " );
//      query.append( C_PROD_RISK_CAT_REF_DATE + ", " );
//      query.append( C_PROD_INVST_RISK_TEXT + ", " );
//      query.append( C_LAST_UPD_DATE + ", " );
//      query.append( C_LAST_UPD_USER_ID + ", " );
//      query.append( C_LAST_AUTH_DATE + ", " );
//      query.append( C_LAST_AUTH_USER_ID + ", " );
//      query.append( C_REC_STAT_CODE );
//      query.append( " FROM " );
//      query.append( C_TPL_PROD_RISK_CAT_PRVT_HIST );
//
//      String criteria = "";
//
//      if ( prodRiskCatCode_ != null )
//      {
//        criteria = criteria + C_PROD_INVST_RISK_CODE + " = ? AND ";
//      }
//
//      if ( prodRiskCatText_ != null && !"".equals( prodRiskCatText_ ) )
//      {
//        criteria = criteria + "UPPER(\"" + C_PROD_INVST_RISK_TEXT
//                   + "\") like ? AND ";
//      }
//      if ( prodRiskCatRefDate_ != null )
//      {
//        criteria = criteria + "TRUNC(" + C_PROD_RISK_CAT_REF_DATE
//                   + ") >= ? AND ";
//      }
//      if ( criteria.length() > 0 )
//      {
//        criteria = criteria.substring( 0, criteria.length() - 5 );
//        query.append( "	WHERE " + criteria + " ORDER BY " + C_PROD_INVST_RISK_TEXT + " ASC " + " , " + C_PROD_RISK_CAT_REF_DATE + " DESC " + "," + C_PROD_INVST_RISK_CODE );
//      }
//      else
//      {
//        query.append( " ORDER BY " + C_PROD_INVST_RISK_TEXT + " ASC " + " , " + C_PROD_RISK_CAT_REF_DATE + " DESC " + "," + C_PROD_INVST_RISK_CODE );
//      }
//
//      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
//      int count = 1;
//
//      if ( prodRiskCatCode_ != null )
//      {
//        preparedStatement.setLong( count++, prodRiskCatCode_.longValue() );
//      }
//
//      if ( prodRiskCatText_ != null && !"".equals( prodRiskCatText_ ) )
//      {
//        preparedStatement.setString( count++, "%" + prodRiskCatText_.toUpperCase()
//                                      + "%" );
//      }
//
//      if ( prodRiskCatRefDate_ != null )
//      {
//        preparedStatement.setTimestamp(
//                                count++,
//                                new java.sql.Timestamp(
//                                                        prodRiskCatRefDate_.getTime() ) );
//      }
//
//      resultSet = preparedStatement.executeQuery();
//	  preparedStatement.replaceParametersInQuery(query.toString());
//
//      rsds = new ResultSetDataSet( resultSet );
//      resultSet.close();
//    }
//    catch ( SQLException e )
//    {
//      throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
//    }
//    finally
//    {
//      closeStatement( preparedStatement );
//      closeConnection( connection );
//    }
//
//    String[] codeColumn = {C_REC_STAT_CODE};
//    String[] nameColumn = {C_REC_STAT_TEXT};
//    rsds.outerJoin(ODSConstraintDecoder.decodeRecStatus(), codeColumn, codeColumn, nameColumn);
//    
//    return rsds;
//  }
//
//  /**
//   * Insere os dados do histórico de uma Categoria de Risco do Produto.
//   * @see com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtHistDAO#insert(com.citibank.ods.entity.pl.TplProdRiskCatPrvtHistEntity)
//   */
//  public TplProdRiskCatPrvtHistEntity insert(
//                                             TplProdRiskCatPrvtHistEntity tplProdRiskCatPrvtHistEntity_ )
//  {
//    ManagedRdbConnection connection = null;
//    CitiStatement preparedStatement = null;
//    StringBuffer query = new StringBuffer();
//    
//    try
//    {
//      connection = OracleODSDAOFactory.getConnection();
//      query.append( "INSERT INTO " + C_TPL_PROD_RISK_CAT_PRVT_HIST + " (" );
//      query.append( C_PROD_INVST_RISK_CODE + ", " );
//      query.append( C_PROD_RISK_CAT_REF_DATE + ", " );
//      query.append( C_PROD_INVST_RISK_TEXT + ", " );
//      query.append( C_LAST_UPD_DATE + ", " );
//      query.append( C_LAST_UPD_USER_ID + ", " );
//      query.append( C_LAST_AUTH_DATE + ", " );
//      query.append( C_LAST_AUTH_USER_ID + ", " );
//      query.append( C_REC_STAT_CODE );
//      query.append( ") VALUES ( " );
//      query.append( "?, ?, ?, ?, ?, ?, ?, ? )" );
//
//      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
//      int count = 1;
//
//      if ( tplProdRiskCatPrvtHistEntity_.getData().getProdRiskCatCode() != null )
//      {
//        preparedStatement.setLong(
//                          count++,
//                          tplProdRiskCatPrvtHistEntity_.getData().getProdRiskCatCode().longValue() );
//      }
//
//      if ( ( ( TplProdRiskCatPrvtHistEntityVO ) tplProdRiskCatPrvtHistEntity_.getData() ).getProdRiskCatRefDate() != null )
//      {
//        preparedStatement.setTimestamp(
//                                count++,
//                                new Timestamp(
//                                               ( ( TplProdRiskCatPrvtHistEntityVO ) tplProdRiskCatPrvtHistEntity_.getData() ).getProdRiskCatRefDate().getTime() ) );
//      }
//
//      if ( tplProdRiskCatPrvtHistEntity_.getData().getProdRiskCatText() != null )
//      {
//        preparedStatement.setString(
//                             count++,
//                             tplProdRiskCatPrvtHistEntity_.getData().getProdRiskCatText() );
//      }
//
//      if ( tplProdRiskCatPrvtHistEntity_.getData().getLastUpdDate() != null )
//      {
//        preparedStatement.setTimestamp(
//                                count++,
//                                new Timestamp(
//                                               tplProdRiskCatPrvtHistEntity_.getData().getLastUpdDate().getTime() ) );
//      }
//
//      if ( tplProdRiskCatPrvtHistEntity_.getData().getLastUpdUserID() != null )
//      {
//        preparedStatement.setString(
//                             count++,
//                             tplProdRiskCatPrvtHistEntity_.getData().getLastUpdUserID() );
//      }
//
//      if ( ( ( TplProdRiskCatPrvtHistEntityVO ) tplProdRiskCatPrvtHistEntity_.getData() ).getLastAuthDate() != null )
//      {
//        preparedStatement.setTimestamp(
//                                count++,
//                                new Timestamp(
//                                               ( ( TplProdRiskCatPrvtHistEntityVO ) tplProdRiskCatPrvtHistEntity_.getData() ).getLastAuthDate().getTime() ) );
//      }
//
//      if ( ( ( TplProdRiskCatPrvtHistEntityVO ) tplProdRiskCatPrvtHistEntity_.getData() ).getLastAuthUserID() != null )
//      {
//        preparedStatement.setString(
//                             count++,
//                             ( ( TplProdRiskCatPrvtHistEntityVO ) tplProdRiskCatPrvtHistEntity_.getData() ).getLastAuthUserID() );
//      }
//
//      if ( ( ( TplProdRiskCatPrvtHistEntityVO ) tplProdRiskCatPrvtHistEntity_.getData() ).getRecStatCode() != null )
//      {
//        preparedStatement.setString(
//                             count++,
//                             ( ( TplProdRiskCatPrvtHistEntityVO ) tplProdRiskCatPrvtHistEntity_.getData() ).getRecStatCode() );
//      }
//
//      preparedStatement.executeUpdate();
//	  preparedStatement.replaceParametersInQuery(query.toString());
//	  
//
//      return null;
//
//    }
//    catch ( Exception e )
//    {
//      throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, e );
//    }
//    finally
//    {
//      closeStatement( preparedStatement );
//      closeConnection( connection );
//    }
//  }

  /**
   * Busca um elementos do histórico com os critérios informados
   * @see com.citibank.ods.persistence.pl.dao.BaseTplProdRiskCatPrvtDAO#find(com.citibank.ods.entity.pl.BaseTplProdRiskCatPrvtEntity)
   */
  public BaseTplProdRiskCatPrvtEntity find(
                                           BaseTplProdRiskCatPrvtEntity baseTplProdRiskCatPrvtEntity_ )
  {
    return null;
  }
}