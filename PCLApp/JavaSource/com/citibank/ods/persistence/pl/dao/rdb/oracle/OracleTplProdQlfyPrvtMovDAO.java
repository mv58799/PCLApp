/*
 * Created on Mar 17, 2007
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
import com.citibank.ods.entity.pl.BaseTplProdQlfyPrvtEntity;
import com.citibank.ods.entity.pl.TplProdQlfyPrvtMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdQlfyPrvtMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author fernando.salgado
 *  
 */
public class OracleTplProdQlfyPrvtMovDAO extends BaseOracleTplProdQlfyPrvtDAO
    implements TplProdQlfyPrvtMovDAO
{
  /*
   * Nome da tabela
   */
  private static final String C_TPL_PROD_QLFY_PRVT_MOV = C_PL_SCHEMA
                                            + "TPL_PROD_QLFY_PRVT_MOV";

  /*
   * Campos específicos da tabela
   */
  private String C_OPERN_CODE = "OPERN_CODE";

  protected String C_OPERN_TEXT = "OPERN_TEXT";

  /**
   * Este método retorna uma lista de qualificador de produto que se enquadre
   * com os critérios informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtMovDAO#list(java.math.BigInteger,
   *      java.lang.String, java.lang.String)
   */
  public DataSet list( BigInteger prodQlfyCode_, String prodQlfyText_,
                      String lastUpdUserId_ )
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
      query.append( C_PROD_QLFY_CODE + ", " );
      query.append( C_PROD_QLFY_TEXT + ", " );
      query.append( C_OPERN_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + " " );
      query.append( " FROM " );
      query.append( C_TPL_PROD_QLFY_PRVT_MOV );

      String criteria = "";

      if ( prodQlfyCode_ != null && prodQlfyCode_.longValue() != 0 )
      {
        criteria = criteria + C_PROD_QLFY_CODE + " = ? AND ";
      }
      if ( prodQlfyText_ != null && !prodQlfyText_.equals( "" ) )
      {
        criteria = criteria + "UPPER(\"" + C_PROD_QLFY_TEXT + "\") like ? AND ";
      }
      if ( lastUpdUserId_ != null && lastUpdUserId_.length() != 0 )
      {
        criteria = criteria + "UPPER(\"" + C_LAST_UPD_USER_ID
                   + "\") like ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria + " ORDER BY " + C_PROD_QLFY_TEXT
                      + " ASC " + " , " + C_PROD_QLFY_CODE );
      }
      else
      {
        query.append( " ORDER BY " + C_PROD_QLFY_TEXT + " ASC " + " , "
                      + C_PROD_QLFY_CODE );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( prodQlfyCode_ != null && prodQlfyCode_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, prodQlfyCode_.longValue() );
      }
      if ( prodQlfyText_ != null && !prodQlfyText_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + prodQlfyText_.toUpperCase() + "%" );
      }
      if ( lastUpdUserId_ != null && lastUpdUserId_.length() != 0 )
      {
        preparedStatement.setString( count++, "%" + lastUpdUserId_.toUpperCase() + "%" );
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

    String[] codeColumn = { C_OPERN_CODE };
    String[] nameColumn = { C_OPERN_TEXT };
    rsds.outerJoin( ODSConstraintDecoder.decodeOpern(), codeColumn, codeColumn,
                    nameColumn );

    return rsds;
  }

  /**
   * Este método insere um qualificador de produto
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtMovDAO#insert(com.citibank.ods.entity.pl.TplProdQlfyPrvtMovEntity)
   */
  public TplProdQlfyPrvtMovEntity insert(
                                         TplProdQlfyPrvtMovEntity tplProdQlfyPrvtMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_PROD_QLFY_PRVT_MOV + " (" );
      query.append( C_PROD_QLFY_CODE + ", " );
      query.append( C_PROD_QLFY_TEXT + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_OPERN_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setLong(
                         count++,
                         tplProdQlfyPrvtMovEntity_.getData().getProdQlfyCode().longValue() );

      preparedStatement.setString(
                           count++,
                           tplProdQlfyPrvtMovEntity_.getData().getProdQlfyText() );

      preparedStatement.setTimestamp(
                              count++,
                              new Timestamp(
                                             tplProdQlfyPrvtMovEntity_.getData().getLastUpdDate().getTime() ) );

      preparedStatement.setString(
                           count++,
                           tplProdQlfyPrvtMovEntity_.getData().getLastUpdUserId() );

      preparedStatement.setString(
                           count++,
                           ( ( TplProdQlfyPrvtMovEntityVO ) tplProdQlfyPrvtMovEntity_.getData() ).getOpernCode() );

      preparedStatement.execute();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return ( TplProdQlfyPrvtMovEntity ) find( tplProdQlfyPrvtMovEntity_ );
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
   * Este método atualiza um qualificador de produto que se enquadre com os
   * critérios informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtMovDAO#update(com.citibank.ods.entity.pl.TplProdQlfyPrvtMovEntity)
   */
  public void update( TplProdQlfyPrvtMovEntity tplProdQlfyPrvtMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_PROD_QLFY_PRVT_MOV + " SET " );
      query.append( C_PROD_QLFY_TEXT + "= ?," );
      query.append( C_LAST_UPD_DATE + "= ?," );
      query.append( C_LAST_UPD_USER_ID + "= ?," );
      query.append( C_OPERN_CODE + "= ? " );
      query.append( "WHERE " + C_PROD_QLFY_CODE + "= ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString(
                           1,
                           tplProdQlfyPrvtMovEntity_.getData().getProdQlfyText() );
      preparedStatement.setTimestamp(
                              2,
                              new Timestamp(
                                             tplProdQlfyPrvtMovEntity_.getData().getLastUpdDate().getTime() ) );
      preparedStatement.setString(
                           3,
                           tplProdQlfyPrvtMovEntity_.getData().getLastUpdUserId() );
      preparedStatement.setString(
                           4,
                           ( ( TplProdQlfyPrvtMovEntityVO ) tplProdQlfyPrvtMovEntity_.getData() ).getOpernCode() );
      preparedStatement.setLong(
                         5,
                         tplProdQlfyPrvtMovEntity_.getData().getProdQlfyCode().longValue() );

      preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

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

  /**
   * Este método exclui um qualificador de produto que se enquadre com os
   * critérios informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtMovDAO#delete(com.citibank.ods.entity.pl.TplProdQlfyPrvtMovEntity)
   */
  public void delete( TplProdQlfyPrvtMovEntity tplProdQlfyPrvtMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "DELETE FROM " );
      query.append( C_TPL_PROD_QLFY_PRVT_MOV );
      query.append( " WHERE " );
      query.append( C_PROD_QLFY_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         tplProdQlfyPrvtMovEntity_.getData().getProdQlfyCode().longValue() );

      preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());
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

  /**
   * Verifica se um determinado registro existe na base de dados.
   * 
   * @param tplProdQlfyPrvtMovEntity___ Entidade contendo o identificador do
   *          registro que será verificada a existência.
   * @return Indicador de existência do registro (true/false).
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtMovDAO#exists(com.citibank.ods.entity.pl.TplProdQlfyPrvtMovEntity)
   */
  public boolean exists( TplProdQlfyPrvtMovEntity tplProdQlfyPrvtMovEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tplProdQlfyPrvtMovEntity_ );
    }
    catch ( NoRowsReturnedException e )
    {
      exists = false;
    }

    return exists;
  }

  /**
   * Este método busca um qualficador de produto que se enquadre com os
   * critérios informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.BaseTplProdQlfyPrvtDAO#find(com.citibank.ods.entity.pl.BaseTplProdQlfyPrvtEntity)
   */
  public BaseTplProdQlfyPrvtEntity find(
                                        BaseTplProdQlfyPrvtEntity baseTplProdQlfyPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList tplProdQlfyEntities;
    BaseTplProdQlfyPrvtEntity prodQlfyPrvtEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_PROD_QLFY_CODE + ", " );
      query.append( C_PROD_QLFY_TEXT + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_OPERN_CODE + " " );
      query.append( " FROM " );
      query.append( C_TPL_PROD_QLFY_PRVT_MOV );
      query.append( " WHERE " );
      query.append( C_PROD_QLFY_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         baseTplProdQlfyPrvtEntity_.getData().getProdQlfyCode().longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      tplProdQlfyEntities = instantiateFromResultSet( resultSet );

      if ( tplProdQlfyEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplProdQlfyEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        prodQlfyPrvtEntity = ( BaseTplProdQlfyPrvtEntity ) tplProdQlfyEntities.get( 0 );
      }

      return prodQlfyPrvtEntity;
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

  /**
   * Este método cria um array de entity apartir de um result set
   * 
   * @param resultSet_
   * @return ArrayList de Entity
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    TplProdQlfyPrvtMovEntity tplProdQlfyPrvtMovEntity;
    Timestamp timestamp;
    Date date;
    ArrayList oracleTplProdQlfyEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplProdQlfyPrvtMovEntity = new TplProdQlfyPrvtMovEntity();

        timestamp = resultSet_.getTimestamp( C_LAST_UPD_DATE );
        date = new Date( timestamp.getTime() );

        tplProdQlfyPrvtMovEntity.getData().setLastUpdDate( date );
        tplProdQlfyPrvtMovEntity.getData().setLastUpdUserId(
                                                             resultSet_.getString( this.C_LAST_UPD_USER_ID ) );
        tplProdQlfyPrvtMovEntity.getData().setProdQlfyCode(
                                                            new BigInteger(
                                                                            resultSet_.getString( this.C_PROD_QLFY_CODE ) ) );
        tplProdQlfyPrvtMovEntity.getData().setProdQlfyText(
                                                            resultSet_.getString( this.C_PROD_QLFY_TEXT ) );
        ( ( TplProdQlfyPrvtMovEntityVO ) tplProdQlfyPrvtMovEntity.getData() ).setOpernCode( resultSet_.getString( this.C_OPERN_CODE ) );

        oracleTplProdQlfyEntities.add( tplProdQlfyPrvtMovEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    
    return oracleTplProdQlfyEntities;
  }

}