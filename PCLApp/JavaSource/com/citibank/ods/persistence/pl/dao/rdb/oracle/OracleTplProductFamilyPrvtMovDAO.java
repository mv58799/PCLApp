/*
 * Created on Mar 19, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
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
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplProductFamilyPrvtEntity;
import com.citibank.ods.entity.pl.TplProductFamilyPrvtMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplProductFamilyPrvtMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProductFamilyPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class OracleTplProductFamilyPrvtMovDAO extends
    BaseOracleTplProductFamilyPrvtDAO implements TplProductFamilyPrvtMovDAO
{
  /*
   * Campos específicos da tabela
   */
  private static final String C_OPERN_CODE = "OPERN_CODE";

  private static final String C_OPERN_TEXT = "OPERN_TEXT";

  /*
   * Nome da tabela
   */
  private static final String C_TABLE_NAME = C_PL_SCHEMA + "TPL_PRODUCT_FAMILY_PRVT_MOV";

  /**
   * Realiza a consulta em lista - Current
   */
  public DataSet list( BigInteger prodFamlCode_, String prodFamlName_,
                      String prodFamlText_, String lastUpdUserId_ )
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
      query.append( C_PROD_FAML_CODE + ", " );
      query.append( C_PROD_FAML_NAME + ", " );
      query.append( C_PROD_FAML_TEXT + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_OPERN_CODE );
      query.append( " FROM " );
      query.append( C_TABLE_NAME );

      String criteria = "";

      if ( prodFamlCode_ != null )
      {
        criteria = criteria + C_PROD_FAML_CODE + " = ? AND ";
      }

      if ( prodFamlName_ != null && !"".equals( prodFamlName_ ) )
      {
        criteria = criteria + "UPPER(\"" + C_PROD_FAML_NAME + "\") like ? AND ";
      }

      if ( prodFamlText_ != null && !"".equals( prodFamlText_ ) )
      {
        criteria = criteria + "UPPER(\"" + C_PROD_FAML_TEXT + "\") like ? AND ";
      }

      if ( lastUpdUserId_ != null && !"".equals( lastUpdUserId_ ) )
      {
        criteria = criteria + "UPPER(\"" + C_LAST_UPD_USER_ID
                   + "\") like ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria + " ORDER BY " + C_PROD_FAML_NAME
                      + " ASC " + " , " + C_PROD_FAML_CODE );
      }
      else
      {
        query.append( " ORDER BY " + C_PROD_FAML_NAME + " ASC " + " , "
                      + C_PROD_FAML_CODE );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( prodFamlCode_ != null )
      {
        preparedStatement.setLong( count++, prodFamlCode_.longValue() );
      }

      if ( prodFamlName_ != null && !"".equals( prodFamlName_ ) )
      {
        preparedStatement.setString( count++, "%" + prodFamlName_.toUpperCase() + "%" );
      }

      if ( prodFamlText_ != null && !"".equals( prodFamlText_ ) )
      {
        preparedStatement.setString( count++, "%" + prodFamlText_.toUpperCase() + "%" );
      }

      if ( lastUpdUserId_ != null && !"".equals( lastUpdUserId_ ) )
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
   * Realiza a inserção de um registro de Família de Produtos
   */
  public TplProductFamilyPrvtMovEntity insert(
                                              TplProductFamilyPrvtMovEntity productFamilyPrvtMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TABLE_NAME + " (" );
      query.append( C_PROD_FAML_CODE + ", " );
      query.append( C_PROD_FAML_NAME + ", " );
      query.append( C_PROD_FAML_TEXT + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_OPERN_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ? ) " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( productFamilyPrvtMovEntity_.getData().getProdFamlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           productFamilyPrvtMovEntity_.getData().getProdFamlCode().longValue() );
      }

      if ( productFamilyPrvtMovEntity_.getData().getProdFamlName() != null )
      {
        preparedStatement.setString(
                             count++,
                             productFamilyPrvtMovEntity_.getData().getProdFamlName() );
      }

      if ( productFamilyPrvtMovEntity_.getData().getProdFamlText() != null )
      {
        preparedStatement.setString(
                             count++,
                             productFamilyPrvtMovEntity_.getData().getProdFamlText() );
      } else {
    	  preparedStatement.setNull(count++, java.sql.Types.VARCHAR);  
      }

      preparedStatement.setTimestamp(
                              count++,
                              new Timestamp(
                                             productFamilyPrvtMovEntity_.getData().getLastUpdDate().getTime() ) );

      if ( productFamilyPrvtMovEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             productFamilyPrvtMovEntity_.getData().getLastUpdUserId() );
      }else {
    	  preparedStatement.setNull(count++, java.sql.Types.VARCHAR);  
      }

      TplProductFamilyPrvtMovEntityVO familyPrvtMovEntityVO = ( TplProductFamilyPrvtMovEntityVO ) productFamilyPrvtMovEntity_.getData();

      if ( familyPrvtMovEntityVO.getOpernCode() != null )
      {
        preparedStatement.setString( count++, familyPrvtMovEntityVO.getOpernCode() );
      }else {
    	  preparedStatement.setNull(count++, java.sql.Types.VARCHAR);  
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return productFamilyPrvtMovEntity_;

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
   * Realiza o update de um registro de família de produtos
   */
  public void update( TplProductFamilyPrvtMovEntity productFamilyPrvtMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();
   
    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TABLE_NAME );
      query.append( " SET " );
      query.append( C_PROD_FAML_NAME + " = ?, " );
      query.append( C_PROD_FAML_TEXT + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_OPERN_CODE + " = ? " );
      query.append( " WHERE " );
      query.append( C_PROD_FAML_CODE + "= ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( productFamilyPrvtMovEntity_.getData().getProdFamlName() != null )
      {
        preparedStatement.setString(
                             count++,
                             productFamilyPrvtMovEntity_.getData().getProdFamlName() );
      }

      if ( productFamilyPrvtMovEntity_.getData().getProdFamlText() != null )
      {
        preparedStatement.setString(
                             count++,
                             productFamilyPrvtMovEntity_.getData().getProdFamlText() );
      }

      preparedStatement.setTimestamp(
                              count++,
                              new Timestamp(
                                             productFamilyPrvtMovEntity_.getData().getLastUpdDate().getTime() ) );

      if ( productFamilyPrvtMovEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             productFamilyPrvtMovEntity_.getData().getLastUpdUserId() );
      }

      TplProductFamilyPrvtMovEntityVO familyPrvtMovEntityVO = ( TplProductFamilyPrvtMovEntityVO ) productFamilyPrvtMovEntity_.getData();

      if ( familyPrvtMovEntityVO.getOpernCode() != null )
      {
        preparedStatement.setString( count++, familyPrvtMovEntityVO.getOpernCode() );
      }

      if ( productFamilyPrvtMovEntity_.getData().getProdFamlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           productFamilyPrvtMovEntity_.getData().getProdFamlCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement.execute();
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
   * Remove um registro de família de produtos
   */
  public void delete( TplProductFamilyPrvtMovEntity productFamilyPrvtMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "DELETE FROM " );
      query.append( C_TABLE_NAME );
      query.append( " WHERE " );
      query.append( C_PROD_FAML_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         productFamilyPrvtMovEntity_.getData().getProdFamlCode().longValue() );

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
   * Verifica se existe um registro na tabela com o mesmo código passado
   */
  public boolean exists(
                        TplProductFamilyPrvtMovEntity productFamilyPrvtMovEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( productFamilyPrvtMovEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  /**
   * Recupera uma entity populada a partir do código passado
   */
  public BaseTplProductFamilyPrvtEntity find(
                                             BaseTplProductFamilyPrvtEntity baseTplProductFamilyPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    ArrayList tplProductFamilyPrvtEntities;
    BaseTplProductFamilyPrvtEntity baseTplProductFamilyPrvtEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_PROD_FAML_CODE + ", " );
      query.append( C_PROD_FAML_NAME + ", " );
      query.append( C_PROD_FAML_TEXT + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_OPERN_CODE );
      query.append( " FROM " );
      query.append( C_TABLE_NAME );
      query.append( " WHERE " );
      query.append( C_PROD_FAML_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         baseTplProductFamilyPrvtEntity_.getData().getProdFamlCode().longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      tplProductFamilyPrvtEntities = instantiateFromResultSet( resultSet );

      if ( tplProductFamilyPrvtEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplProductFamilyPrvtEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        baseTplProductFamilyPrvtEntity = ( BaseTplProductFamilyPrvtEntity ) tplProductFamilyPrvtEntities.get( 0 );
      }

      return baseTplProductFamilyPrvtEntity;
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
    TplProductFamilyPrvtMovEntity productFamilyPrvtEntity;
    TplProductFamilyPrvtMovEntityVO familyPrvtEntityVO;
    Timestamp timestamp;
    ArrayList tplProductFamilyPrvtEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        productFamilyPrvtEntity = new TplProductFamilyPrvtMovEntity();

        productFamilyPrvtEntity.getData().setProdFamlCode(
                                                           new BigInteger(
                                                                           resultSet_.getString( C_PROD_FAML_CODE ) ) );
        productFamilyPrvtEntity.getData().setProdFamlName(
                                                           resultSet_.getString( C_PROD_FAML_NAME ) );
        productFamilyPrvtEntity.getData().setProdFamlText(
                                                           resultSet_.getString( C_PROD_FAML_TEXT ) );
        timestamp = resultSet_.getTimestamp( C_LAST_UPD_DATE );
        productFamilyPrvtEntity.getData().setLastUpdDate(
                                                          new Date(
                                                                    timestamp.getTime() ) );
        productFamilyPrvtEntity.getData().setLastUpdUserId(
                                                            resultSet_.getString( C_LAST_UPD_USER_ID ) );
        // Casting para a atribuicao das colunas especificas
        familyPrvtEntityVO = ( TplProductFamilyPrvtMovEntityVO ) productFamilyPrvtEntity.getData();
        familyPrvtEntityVO.setOpernCode( resultSet_.getString( C_OPERN_CODE ) );

        tplProductFamilyPrvtEntities.add( productFamilyPrvtEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return tplProductFamilyPrvtEntities;
  }
}