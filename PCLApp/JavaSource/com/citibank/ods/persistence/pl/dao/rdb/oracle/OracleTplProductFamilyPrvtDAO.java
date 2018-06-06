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

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplProductFamilyPrvtEntity;
import com.citibank.ods.entity.pl.TplProductFamilyPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplProductFamilyPrvtEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProductFamilyPrvtDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author leonardo.nakada
 * 
 */
public class OracleTplProductFamilyPrvtDAO extends
    BaseOracleTplProductFamilyPrvtDAO implements TplProductFamilyPrvtDAO
{
  /*
   * Campos específicos da tabela
   */
  private static final String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private static final String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private static final String C_REC_STAT_CODE = "REC_STAT_CODE";

  private static final String C_REC_STAT_TEXT = "REC_STAT_TEXT";

  /*
   * Nome da tabela
   */
  private static final String C_TABLE_NAME = C_PL_SCHEMA + "TPL_PRODUCT_FAMILY_PRVT";

  /**
   * Realiza a consulta em lista
   */
  public DataSet list( BigInteger prodFamlCode_, String prodFamlName_,
                      String prodFamlText_ )
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
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TABLE_NAME );

      String criteria = "";

      criteria = criteria + C_REC_STAT_CODE + " != '"
                 + BaseTplProductFamilyPrvtEntity.C_REC_STAT_CODE_INACTIVE
                 + "' AND ";

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

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria + " ORDER BY " + C_PROD_FAML_NAME
                      + " ASC " + " , " + C_PROD_FAML_CODE );
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
   * Insere uma nova linha na tabela TPL_PRODUCT_FAMILY_PRVT com os dados da
   * entidade correspondente passada como parametro
   * 
   * @param tplProductFamilyPrvtEntity__
   * @throws UnexpectedException
   * @author marcelo.s.oliveira
   * @date 19/03/2007
   */
  public TplProductFamilyPrvtEntity insert(
                                           TplProductFamilyPrvtEntity tplProductFamilyPrvtEntity_ )
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
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplProductFamilyPrvtEntity_.getData().getProdFamlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductFamilyPrvtEntity_.getData().getProdFamlCode().longValue() );
      }

      if ( tplProductFamilyPrvtEntity_.getData().getProdFamlName() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductFamilyPrvtEntity_.getData().getProdFamlName() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductFamilyPrvtEntity_.getData().getProdFamlText() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductFamilyPrvtEntity_.getData().getProdFamlText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      preparedStatement.setTimestamp(
                              count++,
                              new Timestamp(
                                             tplProductFamilyPrvtEntity_.getData().getLastUpdDate().getTime() ) );

      if ( tplProductFamilyPrvtEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductFamilyPrvtEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      TplProductFamilyPrvtEntityVO familyPrvtEntityVO = ( TplProductFamilyPrvtEntityVO ) tplProductFamilyPrvtEntity_.getData();

      if ( familyPrvtEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               familyPrvtEntityVO.getLastAuthDate().getTime() ) );
      }

      if ( familyPrvtEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++, familyPrvtEntityVO.getLastAuthUserId() );
      }

      if ( familyPrvtEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, familyPrvtEntityVO.getRecStatCode() );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplProductFamilyPrvtEntity_;

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
   * Atualiza uma linha na tabela TPL_PRODUCT_FAMILY_PRVT com os dados da
   * entidade correspondente passada como parametro
   * 
   * @param tplProductFamilyPrvtEntity__
   * @throws UnexpectedException
   * @author marcelo.s.oliveira
   * @date 19/03/2007
   */
  public void update( TplProductFamilyPrvtEntity tplProductFamilyPrvtEntity_ )
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
      query.append( C_LAST_AUTH_DATE + " = ?, " );
      query.append( C_LAST_AUTH_USER_ID + " = ?, " );
      query.append( C_REC_STAT_CODE + " = ?" );
      query.append( " WHERE " );
      query.append( C_PROD_FAML_CODE + "= ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplProductFamilyPrvtEntity_.getData().getProdFamlName() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductFamilyPrvtEntity_.getData().getProdFamlName() );
      }

      if ( tplProductFamilyPrvtEntity_.getData().getProdFamlText() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductFamilyPrvtEntity_.getData().getProdFamlText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      preparedStatement.setTimestamp(
                              count++,
                              new Timestamp(
                                             tplProductFamilyPrvtEntity_.getData().getLastUpdDate().getTime() ) );

      if ( tplProductFamilyPrvtEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductFamilyPrvtEntity_.getData().getLastUpdUserId() );
      }

      TplProductFamilyPrvtEntityVO familyPrvtEntityVO = ( TplProductFamilyPrvtEntityVO ) tplProductFamilyPrvtEntity_.getData();

      if ( familyPrvtEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               familyPrvtEntityVO.getLastAuthDate().getTime() ) );
      }

      if ( familyPrvtEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++, familyPrvtEntityVO.getLastAuthUserId() );
      }

      if ( familyPrvtEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, familyPrvtEntityVO.getRecStatCode() );
      } else
      {
          preparedStatement.setString( count++, null );
      }

      if ( tplProductFamilyPrvtEntity_.getData().getProdFamlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductFamilyPrvtEntity_.getData().getProdFamlCode().longValue() );
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
   * Remove um registro
   */
  public void delete( TplProductFamilyPrvtEntity tplProductFamilyPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " UPDATE " + C_TABLE_NAME );
      query.append( " SET " );
      query.append( C_REC_STAT_CODE + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ? " );
      query.append( " WHERE " + C_PROD_FAML_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      preparedStatement.setString(
                           count++,
                           ( ( TplProductFamilyPrvtEntityVO ) tplProductFamilyPrvtEntity_.getData() ).getRecStatCode() );

      preparedStatement.setTimestamp(
                              count++,
                              new Timestamp(
                                             tplProductFamilyPrvtEntity_.getData().getLastUpdDate().getTime() ) );

      preparedStatement.setString(
                           count++,
                           tplProductFamilyPrvtEntity_.getData().getLastUpdUserId() );

      preparedStatement.setLong(
                         count++,
                         tplProductFamilyPrvtEntity_.getData().getProdFamlCode().longValue() );

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
   * Verifica se existe um registro cadastrado na tabela que possua o mesmo
   * código da entity passada por parâmetro
   */
  public boolean exists( TplProductFamilyPrvtEntity tplProductFamilyPrvtEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tplProductFamilyPrvtEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  /**
   * Recupera um registro que tenha o mesmo código passado
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
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE );
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
    TplProductFamilyPrvtEntity productFamilyPrvtEntity;
    TplProductFamilyPrvtEntityVO familyPrvtEntityVO;
    ArrayList tplProductFamilyPrvtEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        productFamilyPrvtEntity = new TplProductFamilyPrvtEntity();

        productFamilyPrvtEntity.getData().setProdFamlCode(
                                                           new BigInteger(
                                                                           resultSet_.getString( C_PROD_FAML_CODE ) ) );
        productFamilyPrvtEntity.getData().setProdFamlName(
                                                           resultSet_.getString( C_PROD_FAML_NAME ) );
        productFamilyPrvtEntity.getData().setProdFamlText(
                                                           resultSet_.getString( C_PROD_FAML_TEXT ) );
        productFamilyPrvtEntity.getData().setLastUpdDate(
                                                          resultSet_.getDate( C_LAST_UPD_DATE ) );
        productFamilyPrvtEntity.getData().setLastUpdUserId(
                                                            resultSet_.getString( C_LAST_UPD_USER_ID ) );
        // Casting para a atribuicao das colunas especificas
        familyPrvtEntityVO = ( TplProductFamilyPrvtEntityVO ) productFamilyPrvtEntity.getData();
        familyPrvtEntityVO.setLastAuthDate( resultSet_.getDate( C_LAST_AUTH_DATE ) );
        familyPrvtEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );
        familyPrvtEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );

        tplProductFamilyPrvtEntities.add( productFamilyPrvtEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return tplProductFamilyPrvtEntities;
  }

  /**
   * Verifica se existe um registro cadastrado na tabela ,com o mesmo código da
   * entity passada, com o status "Ativo"
   */
  public boolean existsActive(
                              TplProductFamilyPrvtEntity tplProductFamilyPrvtEntity_ )
  {
    boolean exists = true;

    try
    {
      TplProductFamilyPrvtEntity entity = ( TplProductFamilyPrvtEntity ) this.find( tplProductFamilyPrvtEntity_ );
      TplProductFamilyPrvtEntityVO entityVO = ( TplProductFamilyPrvtEntityVO ) entity.getData();
      if ( !TplProductFamilyPrvtEntity.C_REC_STAT_CODE_ACTIVE.equals( entityVO.getRecStatCode() ) )
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
      query.append( C_PROD_FAML_CODE + ", " );
      query.append( C_PROD_FAML_NAME );
      query.append( " FROM " );
      query.append( C_TABLE_NAME );
      query.append( " WHERE " );
      query.append( C_REC_STAT_CODE + " <> ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++,
                           TplProductFamilyPrvtEntity.C_REC_STAT_CODE_INACTIVE );

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