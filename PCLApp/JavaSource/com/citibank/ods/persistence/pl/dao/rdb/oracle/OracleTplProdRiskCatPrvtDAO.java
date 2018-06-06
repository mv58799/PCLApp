/*
 * Created on Mar 14, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplProdRiskCatPrvtEntity;
import com.citibank.ods.entity.pl.TplProdRiskCatPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdRiskCatPrvtEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class OracleTplProdRiskCatPrvtDAO extends
    BaseOracleTplProdRiskCatPrvtDAO implements TplProdRiskCatPrvtDAO
{
  /*
   * Nome da tabela
   * 
   * As funcionalidades que utilizavam a tabela PL.TPL_PROD_RISK_CAT_PRVT devem utilizar a tabela
   * PL.TPL_RISK_INVST_PROD_RDIP (apenas funcionalidades que utilizem consulta, as telas de 
   * inserção e alteração serão removidas)
   */
  private static final String C_TPL_PROD_RISK_CAT_PRVT = C_PL_SCHEMA
                                            + "TPL_RISK_INVST_PROD_RDIP";

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

  /**
   * Este método busca uma lista de Categoria de Risco do produto que se
   * enquadre com os critérios informados e que esteja com status ativo.
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtDAO#list(java.lang.String,
   *      java.lang.String)
   */
  public DataSet list( BigInteger prodRiskCatCode_, String prodRiskCatText_ )
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
      query.append( C_PROD_INVST_RISK_CODE + ", " );
      query.append( C_PROD_INVST_RISK_TEXT + " " );
      query.append( " FROM " );
      query.append( C_TPL_PROD_RISK_CAT_PRVT );

      String criteria = "";

      if ( prodRiskCatCode_ != null && !"".equals( prodRiskCatCode_ ) )
      {
        criteria = criteria + C_PROD_INVST_RISK_CODE + " = ? AND ";
      }

      if ( prodRiskCatText_ != null && !"".equals( prodRiskCatText_ ) )
      {
        criteria = criteria + "UPPER(\"" + C_PROD_INVST_RISK_TEXT
                   + "\") like ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria + " ORDER BY "
                      + C_PROD_INVST_RISK_TEXT + " ASC " + " , "
                      + C_PROD_INVST_RISK_CODE );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( prodRiskCatCode_ != null )
      {
        preparedStatement.setLong( count++, prodRiskCatCode_.longValue() );
      }

      if ( prodRiskCatText_ != null && !"".equals( prodRiskCatText_ ) )
      {
        preparedStatement.setString( count++, "%" + prodRiskCatText_.toUpperCase() + "%" );
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

//    String[] codeColumn = { C_REC_STAT_CODE };
//    String[] nameColumn = { C_REC_STAT_TEXT };
//    rsds.outerJoin( ODSConstraintDecoder.decodeRecStatus(), codeColumn,
//                    codeColumn, nameColumn );

    return rsds;
  }

  /**
   * Este método insere um novo registro de Categoria de Risco.
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtDAO#insert(com.citibank.ods.entity.pl.TplProdRiskCatPrvtEntity)
   */
  public TplProdRiskCatPrvtEntity insert(
                                         TplProdRiskCatPrvtEntity tplProdRiskCatPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_PROD_RISK_CAT_PRVT + " (" );
      query.append( C_PROD_INVST_RISK_CODE + ", " );
      query.append( C_PROD_INVST_RISK_TEXT + " " );
      query.append( ") VALUES ( " );
      query.append( "?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplProdRiskCatPrvtEntity_.getData().getProdRiskCatCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProdRiskCatPrvtEntity_.getData().getProdRiskCatCode().longValue() );
      }

      if ( tplProdRiskCatPrvtEntity_.getData().getProdRiskCatText() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdRiskCatPrvtEntity_.getData().getProdRiskCatText() );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return ( TplProdRiskCatPrvtEntity ) find( tplProdRiskCatPrvtEntity_ );

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
   * Altera os dados de uma Categoria de Risco do Produto.
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtDAO#update(com.citibank.ods.entity.pl.TplProdRiskCatPrvtEntity)
   */
  public void update( TplProdRiskCatPrvtEntity tplProdRiskCatPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_PROD_RISK_CAT_PRVT );
      query.append( " SET " );
      query.append( C_PROD_INVST_RISK_TEXT + " = ? " );
      query.append( " WHERE " );
      query.append( C_PROD_INVST_RISK_CODE + "= ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplProdRiskCatPrvtEntity_.getData().getProdRiskCatText() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdRiskCatPrvtEntity_.getData().getProdRiskCatText() );
      }


      if ( tplProdRiskCatPrvtEntity_.getData().getProdRiskCatCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProdRiskCatPrvtEntity_.getData().getProdRiskCatCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

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
   * Remove um registro de Categoria de Risco do Produto.
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtDAO#delete(com.citibank.ods.entity.pl.TplProdRiskCatPrvtEntity)
   */
  public void delete( TplProdRiskCatPrvtEntity tplProdRiskCatPrvtEntity_ )
  {
//    ManagedRdbConnection connection = null;
//    CitiStatement preparedStatement = null;
//    StringBuffer query = new StringBuffer();
//
//    try
//    {
//      connection = OracleODSDAOFactory.getConnection();
//      query.append( " UPDATE " + C_TPL_PROD_RISK_CAT_PRVT );
//      query.append( " SET " );
//      query.append( C_REC_STAT_CODE + "= ?" );
//      query.append( " WHERE " + C_PROD_INVST_RISK_CODE + " = ? " );
//
//      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
//
//      preparedStatement.setString(
//                           1,
//                           ( ( TplProdRiskCatPrvtEntityVO ) tplProdRiskCatPrvtEntity_.getData() ).getRecStatCode() );
//
//      preparedStatement.setLong(
//                         2,
//                         tplProdRiskCatPrvtEntity_.getData().getProdRiskCatCode().longValue() );
//
//      preparedStatement.executeUpdate();
//	  preparedStatement.replaceParametersInQuery(query.toString());
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
  }

  /**
   * Este método busca uma categoria de risco do produto que se enquadre com os
   * critérios informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.BaseTplProdRiskCatPrvtDAO#find(com.citibank.ods.entity.pl.BaseTplProdRiskCatPrvtEntity)
   */
  public BaseTplProdRiskCatPrvtEntity find(
                                           BaseTplProdRiskCatPrvtEntity baseTplProdRiskCatPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    ArrayList tplProdRiskCatPrvtEntities;
    BaseTplProdRiskCatPrvtEntity tplProdRiskCatPrvtEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_PROD_INVST_RISK_CODE + ", " );
      query.append( C_PROD_INVST_RISK_TEXT + " " );

      query.append( " FROM " );
      query.append( C_TPL_PROD_RISK_CAT_PRVT );
      query.append( " WHERE " );
      query.append( C_PROD_INVST_RISK_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         baseTplProdRiskCatPrvtEntity_.getData().getProdRiskCatCode().longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      tplProdRiskCatPrvtEntities = instantiateFromResultSet( resultSet );

      if ( tplProdRiskCatPrvtEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplProdRiskCatPrvtEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplProdRiskCatPrvtEntity = ( BaseTplProdRiskCatPrvtEntity ) tplProdRiskCatPrvtEntities.get( 0 );
      }

      return tplProdRiskCatPrvtEntity;
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

  /*
   * Retorna uma coleção de entities a partir do rs
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    TplProdRiskCatPrvtEntity tplProdRiskCatPrvtEntity;
    TplProdRiskCatPrvtEntityVO tplProdRiskCatPrvtEntityVO;
    ArrayList tplProdRiskCatPrvtEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplProdRiskCatPrvtEntity = new TplProdRiskCatPrvtEntity();

        tplProdRiskCatPrvtEntity.getData().setProdRiskCatCode(
                                                               new BigInteger(
                                                                               resultSet_.getString( C_PROD_INVST_RISK_CODE ) ) );
        tplProdRiskCatPrvtEntity.getData().setProdRiskCatText(
                                                               resultSet_.getString( C_PROD_INVST_RISK_TEXT ) );

        tplProdRiskCatPrvtEntities.add( tplProdRiskCatPrvtEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return tplProdRiskCatPrvtEntities;
  }

  /**
   * Verifica se existe um registro com o código passado
   */
  public boolean exists( TplProdRiskCatPrvtEntity TplProdRiskCatPrvtEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( TplProdRiskCatPrvtEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  /**
   * Verifica se existe um registro com o código passado e o status deste
   * registro é Ativo
   */
  public boolean existsActive(
                              TplProdRiskCatPrvtEntity TplProdRiskCatPrvtEntity_ )
  {
    boolean exists = true;

    try
    {
      TplProdRiskCatPrvtEntity prodPrvtEntity = ( TplProdRiskCatPrvtEntity ) this.find( TplProdRiskCatPrvtEntity_ );
      TplProdRiskCatPrvtEntityVO prodRiskCatPrvtEntityVO = ( TplProdRiskCatPrvtEntityVO ) prodPrvtEntity.getData();
      if ( !TplProdRiskCatPrvtEntity.C_REC_STAT_CODE_ACTIVE.equals( prodRiskCatPrvtEntityVO.getRecStatCode() ) )
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

  /**
   * Realiza o carregamento dos registros cadastrados na tabela de Current para
   * ser utilizado em outras transa- ções
   */
  public DataSet loadDomain()
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
      query.append( C_PROD_INVST_RISK_CODE + ", " );      
      query.append( C_PROD_INVST_RISK_CODE +"||'-'||" +C_PROD_INVST_RISK_TEXT + " AS " + C_PROD_INVST_RISK_TEXT );
      query.append( " FROM " );
      query.append( C_TPL_PROD_RISK_CAT_PRVT );
      query.append( " ORDER BY " +  C_PROD_INVST_RISK_CODE);

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

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

}