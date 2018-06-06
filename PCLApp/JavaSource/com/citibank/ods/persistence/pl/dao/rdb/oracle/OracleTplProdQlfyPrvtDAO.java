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
import com.citibank.ods.entity.pl.TplProdQlfyPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdQlfyPrvtEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
/**
 * @author fernando.salgado
 *  
 */
public class OracleTplProdQlfyPrvtDAO extends BaseOracleTplProdQlfyPrvtDAO
    implements TplProdQlfyPrvtDAO
{
  /*
   * Nome da tabela
   */
  private static final String C_TPL_PROD_QLFY_PRVT = C_PL_SCHEMA + "TPL_PROD_QLFY_PRVT";

  /*
   * Campos específicos da tabela
   */
  private String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private String C_REC_STAT_CODE = "REC_STAT_CODE";

  protected String C_REC_STAT_TEXT = "REC_STAT_TEXT";

  /**
   * Este método retorna uma lista de qualificador de produto que se enquadre
   * com os critérios informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtDAO#list(java.math.BigInteger,
   *      java.lang.String)
   */
  public DataSet list( BigInteger prodQlfyCode_, String prodQlfyText_ )
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
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_PROD_QLFY_PRVT );

      String criteria = "";

      criteria = criteria + C_REC_STAT_CODE + " != '"
                 + BaseTplProdQlfyPrvtEntity.C_REC_STAT_CODE_INACTIVE
                 + "' AND ";

      if ( prodQlfyCode_ != null && prodQlfyCode_.longValue() != 0 )
      {
        criteria = criteria + C_PROD_QLFY_CODE + " = ? AND ";
      }
      if ( prodQlfyText_ != null && !prodQlfyText_.equals( "" ) )
      {
        criteria = criteria + "UPPER(\"" + C_PROD_QLFY_TEXT + "\") LIKE ? AND ";
      }
      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria + " ORDER BY " + C_PROD_QLFY_TEXT
                      + " ASC " + " , " + C_PROD_QLFY_CODE );
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

    String[] codeColumn = { C_REC_STAT_CODE };
    String[] nameColumn = { C_REC_STAT_TEXT };
    rsds.outerJoin( ODSConstraintDecoder.decodeRecStatus(), codeColumn,
                    codeColumn, nameColumn );

    return rsds;
  }

  /**
   * Este método insere um novo registro de qualificador de produto
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtDAO#insert(com.citibank.ods.entity.pl.TplProdQlfyPrvtEntity)
   */
  public TplProdQlfyPrvtEntity insert(
                                      TplProdQlfyPrvtEntity tplProdQlfyPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_PROD_QLFY_PRVT + " (" );
      query.append( C_PROD_QLFY_CODE + ", " );
      query.append( C_PROD_QLFY_TEXT + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplProdQlfyPrvtEntity_.getData().getProdQlfyCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProdQlfyPrvtEntity_.getData().getProdQlfyCode().longValue() );
      }

      if ( tplProdQlfyPrvtEntity_.getData().getProdQlfyText() != null )
      {
        preparedStatement.setString( count++,
                             tplProdQlfyPrvtEntity_.getData().getProdQlfyText() );
      }

      if ( tplProdQlfyPrvtEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProdQlfyPrvtEntity_.getData().getLastUpdDate().getTime() ) );
      }

      if ( tplProdQlfyPrvtEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdQlfyPrvtEntity_.getData().getLastUpdUserId() );
      }

      TplProdQlfyPrvtEntityVO tplProdQlfyPrvtEntityVO = ( TplProdQlfyPrvtEntityVO ) tplProdQlfyPrvtEntity_.getData();

      if ( tplProdQlfyPrvtEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProdQlfyPrvtEntityVO.getLastAuthDate().getTime() ) );
      }

      if ( tplProdQlfyPrvtEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplProdQlfyPrvtEntityVO.getLastAuthUserId() );
      }

      if ( tplProdQlfyPrvtEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, tplProdQlfyPrvtEntityVO.getRecStatCode() );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplProdQlfyPrvtEntity_;
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
   * Este método altera os dados de um qualificador de produto
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtDAO#update(com.citibank.ods.entity.pl.TplProdQlfyPrvtEntity)
   */
  public void update( TplProdQlfyPrvtEntity tplProdQlfyPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_PROD_QLFY_PRVT );
      query.append( " SET " );
      query.append( C_PROD_QLFY_TEXT + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_LAST_AUTH_DATE + " = ?, " );
      query.append( C_LAST_AUTH_USER_ID + " = ?, " );
      query.append( C_REC_STAT_CODE + " = ?" );
      query.append( " WHERE " );
      query.append( C_PROD_QLFY_CODE + "= ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplProdQlfyPrvtEntity_.getData().getProdQlfyText() != null )
      {
        preparedStatement.setString( count++,
                             tplProdQlfyPrvtEntity_.getData().getProdQlfyText() );
      }

      if ( tplProdQlfyPrvtEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProdQlfyPrvtEntity_.getData().getLastUpdDate().getTime() ) );
      }

      if ( tplProdQlfyPrvtEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdQlfyPrvtEntity_.getData().getLastUpdUserId() );
      }

      TplProdQlfyPrvtEntityVO tplProdQlfyPrvtEntityVO = ( TplProdQlfyPrvtEntityVO ) tplProdQlfyPrvtEntity_.getData();

      if ( tplProdQlfyPrvtEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProdQlfyPrvtEntityVO.getLastAuthDate().getTime() ) );
      }

      if ( tplProdQlfyPrvtEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplProdQlfyPrvtEntityVO.getLastAuthUserId() );
      }

      if ( tplProdQlfyPrvtEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, tplProdQlfyPrvtEntityVO.getRecStatCode() );
      }

      if ( tplProdQlfyPrvtEntity_.getData().getProdQlfyCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProdQlfyPrvtEntity_.getData().getProdQlfyCode().longValue() );
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
   * Este método efetua a deleção lógica de um qualificador de produto que se
   * enquadre nos critérios informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtDAO#delete(com.citibank.ods.entity.pl.TplProdQlfyPrvtEntity)
   */
  public void delete( TplProdQlfyPrvtEntity tplProdQlfyPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " UPDATE " + C_TPL_PROD_QLFY_PRVT );
      query.append( " SET " );
      query.append( C_REC_STAT_CODE + "= ?" );
      query.append( " WHERE " + C_PROD_QLFY_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString(
                           1,
                           ( ( TplProdQlfyPrvtEntityVO ) tplProdQlfyPrvtEntity_.getData() ).getRecStatCode() );

      preparedStatement.setLong(
                         2,
                         tplProdQlfyPrvtEntity_.getData().getProdQlfyCode().longValue() );

      preparedStatement.executeUpdate();
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

    ArrayList tplProdQlfyPrvtEntities;
    BaseTplProdQlfyPrvtEntity tplProdQlfyPrvtEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_PROD_QLFY_CODE + ", " );
      query.append( C_PROD_QLFY_TEXT + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_PROD_QLFY_PRVT );
      query.append( " WHERE " );
      query.append( C_PROD_QLFY_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         baseTplProdQlfyPrvtEntity_.getData().getProdQlfyCode().longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      tplProdQlfyPrvtEntities = instantiateFromResultSet( resultSet );

      if ( tplProdQlfyPrvtEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplProdQlfyPrvtEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplProdQlfyPrvtEntity = ( BaseTplProdQlfyPrvtEntity ) tplProdQlfyPrvtEntities.get( 0 );
      }

      return tplProdQlfyPrvtEntity;
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
    TplProdQlfyPrvtEntity tplProdQlfyPrvtEntity;
    Timestamp timestamp;
    Date date;
    ArrayList tplProdQlfyPrvtEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplProdQlfyPrvtEntity = new TplProdQlfyPrvtEntity();

        tplProdQlfyPrvtEntity.getData().setProdQlfyCode(
                                                         new BigInteger(
                                                                         resultSet_.getString( C_PROD_QLFY_CODE ) ) );
        tplProdQlfyPrvtEntity.getData().setProdQlfyText(
                                                         resultSet_.getString( C_PROD_QLFY_TEXT ) );
        timestamp = resultSet_.getTimestamp( C_LAST_UPD_DATE );
        date = new Date( timestamp.getTime() );
        tplProdQlfyPrvtEntity.getData().setLastUpdDate( date );
        tplProdQlfyPrvtEntity.getData().setLastUpdUserId(
                                                          resultSet_.getString( C_LAST_UPD_USER_ID ) );
        // Casting para a atribuicao das colunas especificas
        TplProdQlfyPrvtEntityVO tplProdQlfyPrvtEntityVO = ( TplProdQlfyPrvtEntityVO ) tplProdQlfyPrvtEntity.getData();
        tplProdQlfyPrvtEntityVO.setLastAuthDate( resultSet_.getDate( C_LAST_AUTH_DATE ) );
        tplProdQlfyPrvtEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );
        tplProdQlfyPrvtEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );

        tplProdQlfyPrvtEntities.add( tplProdQlfyPrvtEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return tplProdQlfyPrvtEntities;
  }

  /**
   * Verifica se um determinado registro existe na base de dados.
   * 
   * @param tplProdQlfyPrvtEntity___ Entidade contendo o identificador do
   *          registro que será verificada a existência.
   * @return Indicador de existência do registro (true/false).
   *  
   */
  public boolean exists( TplProdQlfyPrvtEntity tplProdQlfyPrvtEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tplProdQlfyPrvtEntity_ );
    }
    catch ( NoRowsReturnedException e )
    {
      exists = false;
    }

    return exists;
  }

  public boolean existsActive( TplProdQlfyPrvtEntity tplProdQlfyPrvtEntity_ )
  {
    boolean exists = true;

    try
    {
      TplProdQlfyPrvtEntity prodQlfyPrvtEntity = ( TplProdQlfyPrvtEntity ) this.find( tplProdQlfyPrvtEntity_ );
      if ( !TplProdQlfyPrvtEntity.C_REC_STAT_CODE_ACTIVE.equals( prodQlfyPrvtEntity.getData().getRecStatCode() ) )
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
      query.append( C_PROD_QLFY_CODE + " , " );
      query.append( C_PROD_QLFY_TEXT );
      query.append( " FROM " );
      query.append( C_TPL_PROD_QLFY_PRVT + " " );
      query.append( " WHERE " );
      query.append( C_REC_STAT_CODE + " <> ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++,
                           TplProdQlfyPrvtEntity.C_REC_STAT_CODE_INACTIVE );

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