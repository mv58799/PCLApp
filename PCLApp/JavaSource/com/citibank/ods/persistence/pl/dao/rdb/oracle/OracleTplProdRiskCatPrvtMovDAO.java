/*
 * Created on Jan 17, 2007
 *
 */
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
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplProdRiskCatPrvtEntity;
import com.citibank.ods.entity.pl.TplProdRiskCatPrvtMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdRiskCatPrvtMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
/**
 * @author angelica.almeida
 * 
 * *** 20110321 ***
 * As funcionalidades que utilizavam a tabela PL.TPL_PROD_RISK_CAT_PRVT devem utilizar a tabela
 * PL.TPL_RISK_INVST_PROD_RDIP (apenas funcionalidades que utilizem consulta, as telas de 
 * inserção e alteração serão removidas) 
 * 
 */
public class OracleTplProdRiskCatPrvtMovDAO extends
    BaseOracleTplProdRiskCatPrvtDAO implements TplProdRiskCatPrvtMovDAO

{

  private static final String C_TPL_PROD_RISK_CAT_PRVT_MOV = C_PL_SCHEMA
                                                + "TPL_PROD_RISK_CAT_PRVT_MOV";

  private String C_OPERN_CODE = "OPERN_CODE";

  protected String C_OPERN_TEXT = "OPERN_TEXT";

//  /**
//   * Este método altera os dados de uma categoria de risco
//   * 
//   * @see com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtMovDAO#update(com.citibank.ods.entity.pl.TplProdRiskCatPrvtMovEntity)
//   */
//  public void update( TplProdRiskCatPrvtMovEntity prodRiskCatPrvtMovEntity_ )
//  {
//    ManagedRdbConnection connection = null;
//    CitiStatement preparedStatement = null;
//    StringBuffer query = new StringBuffer();
//
//    try
//    {
//      connection = OracleODSDAOFactory.getConnection();
//      query.append( "UPDATE " + C_TPL_PROD_RISK_CAT_PRVT_MOV + " SET " );
//      query.append( C_PROD_INVST_RISK_TEXT + "= ?," );
//      query.append( C_LAST_UPD_DATE + "= ?," );
//      query.append( C_LAST_UPD_USER_ID + "= ?," );
//      query.append( C_OPERN_CODE + "= ? " );
//      query.append( "WHERE " + C_PROD_INVST_RISK_CODE + "= ? " );
//
//      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
//
//      preparedStatement.setString(
//                           1,
//                           prodRiskCatPrvtMovEntity_.getData().getProdRiskCatText() );
//      preparedStatement.setTimestamp(
//                         2,
//                         new java.sql.Timestamp(
//                                            prodRiskCatPrvtMovEntity_.getData().getLastUpdDate().getTime() ) );
//      preparedStatement.setString(
//                           3,
//                           prodRiskCatPrvtMovEntity_.getData().getLastUpdUserID() );
//      preparedStatement.setString(
//                           4,
//                           ( ( TplProdRiskCatPrvtMovEntityVO ) prodRiskCatPrvtMovEntity_.getData() ).getOpernCode() );
//      preparedStatement.setLong(
//                         5,
//                         prodRiskCatPrvtMovEntity_.getData().getProdRiskCatCode().longValue() );
//
//      preparedStatement.executeUpdate();
//	  preparedStatement.replaceParametersInQuery(query.toString());
//
//    }
//    catch ( SQLException e )
//    {
//      throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
//    }
//  }
//
//  /**
//   * Este método exclui uma categoria de risco que se enquadre com o critérios
//   * informados
//   * 
//   * @see com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtMovDAO#delete(com.citibank.ods.entity.pl.TplProdRiskCatPrvtMovEntity)
//   */
//  public void delete( TplProdRiskCatPrvtMovEntity prodRiskCatPrvtMovEntity_ )
//  {
//    ManagedRdbConnection connection = null;
//    CitiStatement preparedStatement = null;
//    StringBuffer query = new StringBuffer();
//
//    try
//    {
//      connection = OracleODSDAOFactory.getConnection();
//      query.append( "DELETE FROM " );
//      query.append( C_TPL_PROD_RISK_CAT_PRVT_MOV );
//      query.append( " WHERE " );
//      query.append( C_PROD_INVST_RISK_CODE + " = ?" );
//
//      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
//
//      preparedStatement.setLong(
//                         1,
//                         prodRiskCatPrvtMovEntity_.getData().getProdRiskCatCode().longValue() );
//
//      preparedStatement.executeUpdate();
//	  preparedStatement.replaceParametersInQuery(query.toString());
//    }
//    catch ( SQLException e )
//    {
//      throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
//    }
//
//  }
//
//  /**
//   * Este método insere um novo registro de categoria de risco
//   * 
//   * @see com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtMovDAO#insert(com.citibank.ods.entity.pl.TplProdRiskCatPrvtMovEntity)
//   */
//  public TplProdRiskCatPrvtMovEntity insert(
//                                            TplProdRiskCatPrvtMovEntity prodRiskCatPrvtMovEntity_ )
//  {
//    ManagedRdbConnection connection = null;
//    CitiStatement preparedStatement = null;
//    StringBuffer query = new StringBuffer();
//
//    try
//    {
//      connection = OracleODSDAOFactory.getConnection();
//      query.append( "INSERT INTO " + C_TPL_PROD_RISK_CAT_PRVT_MOV + " ( " );
//      query.append( C_PROD_INVST_RISK_CODE + ", " );
//      query.append( C_PROD_INVST_RISK_TEXT + ", " );
//      query.append( C_LAST_UPD_DATE + ", " );
//      query.append( C_LAST_UPD_USER_ID + ", " );
//      query.append( C_OPERN_CODE + ") " );
//      query.append( "VALUES ( ?, ?, ?, ?, ? ) " );
//
//      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
//
//      preparedStatement.setLong(
//                         1,
//                         prodRiskCatPrvtMovEntity_.getData().getProdRiskCatCode().longValue() );
//      preparedStatement.setString(
//                           2,
//                           prodRiskCatPrvtMovEntity_.getData().getProdRiskCatText() );
//      preparedStatement.setTimestamp(
//                              3,
//                              new Timestamp(
//                                             prodRiskCatPrvtMovEntity_.getData().getLastUpdDate().getTime() ) );
//      preparedStatement.setString(
//                           4,
//                           prodRiskCatPrvtMovEntity_.getData().getLastUpdUserID() );
//      preparedStatement.setString(
//                           5,
//                           ( ( TplProdRiskCatPrvtMovEntityVO ) prodRiskCatPrvtMovEntity_.getData() ).getOpernCode() );
//      preparedStatement.execute();
//	  preparedStatement.replaceParametersInQuery(query.toString());
//    }
//    catch ( SQLException e )
//    {
//      throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
//    }
//
//    return prodRiskCatPrvtMovEntity_;
//  }
//
//  /**
//   * Este método retorna uma lista de categoria de risco que se enquadre com os
//   * critérios informados
//   * 
//   * @see com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtMovDAO#update(
//   *      java.math.BigInteger,java.lang.String,java.lang.String)
//   */
//  public DataSet list( BigInteger prodRiskCode_, String prodRiskText_,
//                      String lastUpdUserId_ )
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
//      query.append( C_PROD_INVST_RISK_TEXT + ", " );
//      query.append( C_OPERN_CODE + ", " );
//      query.append( C_LAST_UPD_USER_ID + ", " );
//      query.append( C_LAST_UPD_DATE + " " );
//      query.append( " FROM " );
//      query.append( C_TPL_PROD_RISK_CAT_PRVT_MOV );
//
//      String criteria = "";
//
//      if ( prodRiskCode_ != null && prodRiskCode_.longValue() != 0 )
//      {
//        criteria = criteria + C_PROD_INVST_RISK_CODE + " = ? AND ";
//      }
//      if ( prodRiskText_ != null && !prodRiskText_.equals( "" ) )
//      {
//        criteria = criteria + "UPPER(\"" + C_PROD_INVST_RISK_TEXT
//                   + "\") LIKE ? AND ";
//      }
//      if ( lastUpdUserId_ != null && lastUpdUserId_.length() != 0 )
//      {
//        criteria = criteria + "UPPER(\"" + C_LAST_UPD_USER_ID
//                   + "\") LIKE ? AND ";
//      }
//
//      if ( criteria.length() > 0 )
//      {
//        criteria = criteria.substring( 0, criteria.length() - 5 );
//        query.append( "	WHERE " + criteria + " ORDER BY "
//                      + C_PROD_INVST_RISK_TEXT + " ASC " + " , "
//                      + C_PROD_INVST_RISK_CODE );
//      }
//      else
//      {
//        query.append( " ORDER BY " + C_PROD_INVST_RISK_TEXT + " ASC " + " , "
//                      + C_PROD_INVST_RISK_CODE );
//      }
//
//      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
//      int count = 1;
//
//      if ( prodRiskCode_ != null && prodRiskCode_.longValue() != 0 )
//      {
//        preparedStatement.setLong( count++, prodRiskCode_.longValue() );
//      }
//      if ( prodRiskText_ != null && !prodRiskText_.equals( "" ) )
//      {
//        preparedStatement.setString( count++, "%" + prodRiskText_.toUpperCase() + "%" );
//      }
//      if ( lastUpdUserId_ != null && lastUpdUserId_.length() != 0 )
//      {
//        preparedStatement.setString( count++, "%" + lastUpdUserId_.toUpperCase() + "%" );
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
//    String[] codeColumn = { C_OPERN_CODE };
//    String[] nameColumn = { C_OPERN_TEXT };
//    rsds.outerJoin( ODSConstraintDecoder.decodeOpern(), codeColumn, codeColumn,
//                    nameColumn );
//
//    return rsds;
//  }
//
//  /**
//   * Este método busca uma categoria de risco que se enquadre com os critérios
//   * informados
//   * 
//   * @see com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtMovDAO#insert(com.citibank.ods.entity.pl.TplProdRiskCatPrvtMovEntity)
//   */
//  public BaseTplProdRiskCatPrvtEntity find(
//                                           BaseTplProdRiskCatPrvtEntity prodRiskCatPrvtEntity_ )
//  {
//    ManagedRdbConnection connection = null;
//    CitiStatement preparedStatement = null;
//    ResultSet resultSet = null;
//    StringBuffer query = new StringBuffer();
//    ArrayList tplProdRiskCatEntities;
//    BaseTplProdRiskCatPrvtEntity prodRiskCatPrvtEntity = null;
//
//    try
//    {
//      connection = OracleODSDAOFactory.getConnection();
//      query.append( "SELECT " );
//      query.append( C_PROD_INVST_RISK_CODE + ", " );
//      query.append( C_PROD_INVST_RISK_TEXT + ", " );
//      query.append( C_LAST_UPD_DATE + ", " );
//      query.append( C_LAST_UPD_USER_ID + ", " );
//      query.append( C_OPERN_CODE + " " );
//      query.append( " FROM " );
//      query.append( C_TPL_PROD_RISK_CAT_PRVT_MOV );
//      query.append( " WHERE " );
//      query.append( C_PROD_INVST_RISK_CODE + " = ?" );
//
//      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
//
//      preparedStatement.setLong(
//                         1,
//                         prodRiskCatPrvtEntity_.getData().getProdRiskCatCode().longValue() );
//
//      resultSet = preparedStatement.executeQuery();
//	  preparedStatement.replaceParametersInQuery(query.toString());
//
//      tplProdRiskCatEntities = instantiateFromResultSet( resultSet );
//
//      if ( tplProdRiskCatEntities.size() == 0 )
//      {
//        throw new NoRowsReturnedException();
//      }
//      else if ( tplProdRiskCatEntities.size() > 1 )
//      {
//        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
//      }
//      else
//      {
//        prodRiskCatPrvtEntity = ( BaseTplProdRiskCatPrvtEntity ) tplProdRiskCatEntities.get( 0 );
//      }
//
//      return prodRiskCatPrvtEntity;
//    }
//    catch ( SQLException e )
//    {
//      throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
//    }
//  }
//
//  /**
//   * Este método cria um array de Entity apartir de um result set
//   * 
//   * @param resultSet_
//   * @return ArrayList de TplProdRiskCatPrvtMovEntity
//   */
//  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
//  {
//
//    TplProdRiskCatPrvtMovEntity tplProdRiskCatPrvtMovEntity;
//    Timestamp timestamp;
//    Date date;
//    ArrayList oracleTplOfficerCmplEntities = new ArrayList();
//
//    try
//    {
//      while ( resultSet_.next() )
//      {
//        tplProdRiskCatPrvtMovEntity = new TplProdRiskCatPrvtMovEntity();
//
//        timestamp = resultSet_.getTimestamp( this.C_LAST_UPD_DATE );
//        date = new Date( timestamp.getTime() );
//        tplProdRiskCatPrvtMovEntity.getData().setLastUpdDate( date );
//        tplProdRiskCatPrvtMovEntity.getData().setLastUpdUserID(
//                                                                resultSet_.getString( this.C_LAST_UPD_USER_ID ) );
//        tplProdRiskCatPrvtMovEntity.getData().setProdRiskCatCode(
//                                                                  new BigInteger(
//                                                                                  resultSet_.getString( this.C_PROD_INVST_RISK_CODE ) ) );
//        tplProdRiskCatPrvtMovEntity.getData().setProdRiskCatText(
//                                                                  resultSet_.getString( this.C_PROD_INVST_RISK_TEXT ) );
//        ( ( TplProdRiskCatPrvtMovEntityVO ) tplProdRiskCatPrvtMovEntity.getData() ).setOpernCode( resultSet_.getString( this.C_OPERN_CODE ) );
//
//        oracleTplOfficerCmplEntities.add( tplProdRiskCatPrvtMovEntity );
//      }
//    }
//    catch ( SQLException e )
//    {
//      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
//    }
//    return oracleTplOfficerCmplEntities;
//  }
//
//  /**
//   * 
//   * Verifica se um determinado registro existe na base de dados.
//   * 
//   * @param catPrvtMovEntity___ Entidade contendo o identificador do registro
//   *          que será verificada a existência.
//   * @return Indicador de existência do registro (true/false).
//   */
//  public boolean exists( TplProdRiskCatPrvtMovEntity catPrvtMovEntity_ )
//  {
//    boolean exists = true;
//
//    try
//    {
//      this.find( catPrvtMovEntity_ );
//    }
//    catch ( NoRowsReturnedException e )
//    {
//      exists = false;
//    }
//
//    return exists;
//  }
  
  public BaseTplProdRiskCatPrvtEntity find(BaseTplProdRiskCatPrvtEntity baseTplProdRiskCatPrvtEntity_) {
		// TODO Auto-generated method stub
		return null;
  }
  
}