/*
 * Created on Mar 19, 2007
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

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplProdSubFamlPrvtEntity;
import com.citibank.ods.entity.pl.BaseTplProductFamilyPrvtEntity;
import com.citibank.ods.entity.pl.TplProdSubFamlPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdSubFamlPrvtEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdSubFamlPrvtDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class OracleTplProdSubFamlPrvtDAO extends
    BaseOracleTplProdSubFamlPrvtDAO implements TplProdSubFamlPrvtDAO
{
  /*
   * Campos específicos da tabela
   */
  private static final String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private static final String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private static final String C_REC_STAT_CODE = "REC_STAT_CODE";

  private static final String C_REC_STAT_TEXT = "REC_STAT_TEXT";

  private static final String C_PROD_FAML_NAME = "PROD_FAML_NAME";

  /*
   * Nome da tabela
   */
  private static final String C_TABLE_NAME = C_PL_SCHEMA + "TPL_PRODUCT_SUB_FAMILY_PRVT";

  /*
   * Nome da tabela referenciada - Família
   */
  private String C_REF_TABLE_PRODUCT_FAMILY = C_PL_SCHEMA
                                              + "TPL_PRODUCT_FAMILY_PRVT";

  /**
   * Realiza a consulta em lista na tabela de "Current" dado os critérios
   * passados
   */
  public DataSet list( BigInteger prodSubFamlCode_, String prodSubFamlName_,
                      String prodSubFamlText_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();
    String logicNameSub = "sub";
    String logicNameProd = "prod";

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( logicNameSub + "." + C_PROD_SUB_FAML_CODE + ", " );
      query.append( logicNameSub + "." + C_PROD_SUB_FAML_NAME + ", " );
      query.append( logicNameSub + "." + C_PROD_SUB_FAML_TEXT + ", " );
      query.append( logicNameSub + "." + C_PROD_FAML_CODE + ", " );
      query.append( logicNameSub + "." + C_LAST_UPD_DATE + ", " );
      query.append( logicNameSub + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( logicNameSub + "." + C_LAST_AUTH_DATE + ", " );
      query.append( logicNameSub + "." + C_LAST_AUTH_USER_ID + ", " );
      query.append( logicNameSub + "." + C_REC_STAT_CODE + ", " );
      query.append( logicNameProd + "." + C_PROD_FAML_NAME );
      query.append( " FROM " );
      query.append( C_TABLE_NAME + " " + logicNameSub );
      query.append( " LEFT JOIN  " );
      query.append( C_REF_TABLE_PRODUCT_FAMILY + " " + logicNameProd );
      query.append( " ON " );
      query.append( logicNameSub + "." + C_PROD_FAML_CODE );
      query.append( " = " );
      query.append( logicNameProd + "." + C_PROD_FAML_CODE );

      String criteria = "";

      criteria = criteria + logicNameSub + "." + C_REC_STAT_CODE + " != '"
                 + BaseTplProductFamilyPrvtEntity.C_REC_STAT_CODE_INACTIVE
                 + "' AND ";

      if ( prodSubFamlCode_ != null )
      {
        criteria = criteria + logicNameSub + "." + C_PROD_SUB_FAML_CODE
                   + " = ? AND ";
      }

      if ( prodSubFamlName_ != null && !"".equals( prodSubFamlName_ ) )
      {
        criteria = criteria + "UPPER(\"" + C_PROD_SUB_FAML_NAME
                   + "\") like ? AND ";
      }

      if ( prodSubFamlText_ != null && !"".equals( prodSubFamlText_ ) )
      {
        criteria = criteria + "UPPER(\"" + C_PROD_SUB_FAML_TEXT
                   + "\") like ? AND ";

      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria + " ORDER BY "
                      + C_PROD_SUB_FAML_NAME + " ASC " + " , "
                      + C_PROD_SUB_FAML_CODE );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( prodSubFamlCode_ != null )
      {
        preparedStatement.setLong( count++, prodSubFamlCode_.longValue() );
      }

      if ( prodSubFamlName_ != null && !"".equals( prodSubFamlName_ ) )
      {
        preparedStatement.setString( count++, "%" + prodSubFamlName_.toUpperCase() + "%" );
      }

      if ( prodSubFamlText_ != null && !"".equals( prodSubFamlText_ ) )
      {
        preparedStatement.setString( count++, "%" + prodSubFamlText_.toUpperCase() + "%" );
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
   * Realiza a inserção de um registro de sub-família de produtos
   */
  public TplProdSubFamlPrvtEntity insert(
                                         TplProdSubFamlPrvtEntity tplProductSubFamilyPrvtEntity_ )
  {

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TABLE_NAME + " (" );
      query.append( C_PROD_SUB_FAML_CODE + ", " );
      query.append( C_PROD_SUB_FAML_NAME + ", " );
      query.append( C_PROD_SUB_FAML_TEXT + ", " );
      query.append( C_PROD_FAML_CODE + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplProductSubFamilyPrvtEntity_.getData().getProdSubFamlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductSubFamilyPrvtEntity_.getData().getProdSubFamlCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProductSubFamilyPrvtEntity_.getData().getProdSubFamlName() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductSubFamilyPrvtEntity_.getData().getProdSubFamlName() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProductSubFamilyPrvtEntity_.getData().getProdSubFamlText() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductSubFamilyPrvtEntity_.getData().getProdSubFamlText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductSubFamilyPrvtEntity_.getData().getProdFamlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductSubFamilyPrvtEntity_.getData().getProdFamlCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProductSubFamilyPrvtEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductSubFamilyPrvtEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProductSubFamilyPrvtEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductSubFamilyPrvtEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      TplProdSubFamlPrvtEntityVO familyPrvtEntityVO = ( TplProdSubFamlPrvtEntityVO ) tplProductSubFamilyPrvtEntity_.getData();

      if ( familyPrvtEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               familyPrvtEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( familyPrvtEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++, familyPrvtEntityVO.getLastAuthUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( familyPrvtEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, familyPrvtEntityVO.getRecStatCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplProductSubFamilyPrvtEntity_;

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
   * Realiza a atualização de um registro de sub-família de produtos
   */
  public void update( TplProdSubFamlPrvtEntity tplProductSubFamilyPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TABLE_NAME );
      query.append( " SET " );
      query.append( C_PROD_SUB_FAML_NAME + " = ?, " );
      query.append( C_PROD_SUB_FAML_TEXT + " = ?, " );
      query.append( C_PROD_FAML_CODE + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_LAST_AUTH_DATE + " = ?, " );
      query.append( C_LAST_AUTH_USER_ID + " = ?, " );
      query.append( C_REC_STAT_CODE + " = ?" );
      query.append( " WHERE " );
      query.append( C_PROD_SUB_FAML_CODE + "= ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplProductSubFamilyPrvtEntity_.getData().getProdSubFamlName() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductSubFamilyPrvtEntity_.getData().getProdSubFamlName() );
      }

      preparedStatement.setString(
                           count++,
                           tplProductSubFamilyPrvtEntity_.getData().getProdSubFamlText() );

      if ( tplProductSubFamilyPrvtEntity_.getData().getProdFamlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductSubFamilyPrvtEntity_.getData().getProdFamlCode().longValue() );
      }

      preparedStatement.setTimestamp(
                              count++,
                              new Timestamp(
                                             tplProductSubFamilyPrvtEntity_.getData().getLastUpdDate().getTime() ) );

      if ( tplProductSubFamilyPrvtEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductSubFamilyPrvtEntity_.getData().getLastUpdUserId() );
      }

      TplProdSubFamlPrvtEntityVO familyPrvtEntityVO = ( TplProdSubFamlPrvtEntityVO ) tplProductSubFamilyPrvtEntity_.getData();

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

      if ( tplProductSubFamilyPrvtEntity_.getData().getProdSubFamlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductSubFamilyPrvtEntity_.getData().getProdSubFamlCode().longValue() );
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
   * Realiza a remoção de um registro de sub-família de produtos
   */
  public void delete( TplProdSubFamlPrvtEntity tplProductSubFamilyPrvtEntity_ )
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
      query.append( " WHERE " + C_PROD_SUB_FAML_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      preparedStatement.setString(
                           count++,
                           ( ( TplProdSubFamlPrvtEntityVO ) tplProductSubFamilyPrvtEntity_.getData() ).getRecStatCode() );

      preparedStatement.setTimestamp(
                              count++,
                              new Timestamp(
                                             tplProductSubFamilyPrvtEntity_.getData().getLastUpdDate().getTime() ) );

      preparedStatement.setString(
                           count++,
                           tplProductSubFamilyPrvtEntity_.getData().getLastUpdUserId() );

      preparedStatement.setLong(
                         count++,
                         tplProductSubFamilyPrvtEntity_.getData().getProdSubFamlCode().longValue() );

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
   * Verifica se existe um registro de sub-família de produtos com o código
   * passado
   */
  public boolean exists( TplProdSubFamlPrvtEntity tplProductSubFamilyPrvtEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tplProductSubFamilyPrvtEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  /**
   * Recupera um registro de sub-família, dado o código passado
   */
  public BaseTplProdSubFamlPrvtEntity find(
                                           BaseTplProdSubFamlPrvtEntity baseTplProductFamilyPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    ArrayList tplProductFamilyPrvtEntities;
    BaseTplProdSubFamlPrvtEntity baseTplProductSubFamilyPrvtEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_PROD_SUB_FAML_CODE + ", " );
      query.append( C_PROD_SUB_FAML_NAME + ", " );
      query.append( C_PROD_SUB_FAML_TEXT + ", " );
      query.append( C_PROD_FAML_CODE + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TABLE_NAME );
      query.append( " WHERE " );
      query.append( C_PROD_SUB_FAML_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         baseTplProductFamilyPrvtEntity_.getData().getProdSubFamlCode().longValue() );

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
        baseTplProductSubFamilyPrvtEntity = ( BaseTplProdSubFamlPrvtEntity ) tplProductFamilyPrvtEntities.get( 0 );
      }

      return baseTplProductSubFamilyPrvtEntity;
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
    TplProdSubFamlPrvtEntity productFamilyPrvtEntity;
    TplProdSubFamlPrvtEntityVO familyPrvtEntityVO;
    ArrayList tplProductFamilyPrvtEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        productFamilyPrvtEntity = new TplProdSubFamlPrvtEntity();

        if(resultSet_.getString( C_PROD_SUB_FAML_CODE ) != null){ 
        
          productFamilyPrvtEntity.getData().setProdSubFamlCode(
                                                              new BigInteger(
                                                                              resultSet_.getString( C_PROD_SUB_FAML_CODE ) ) );
        }                                                                      
        else{
			productFamilyPrvtEntity.getData().setProdSubFamlCode(null);
        }
                                                                              
        if(resultSet_.getString( C_PROD_SUB_FAML_NAME ) != null){        
        
          productFamilyPrvtEntity.getData().setProdSubFamlName(
                                                              resultSet_.getString( C_PROD_SUB_FAML_NAME ) );
        }
        else{
			productFamilyPrvtEntity.getData().setProdSubFamlName(null);        	                                                             
        }
        
        if(resultSet_.getString( C_PROD_SUB_FAML_TEXT )!= null){
        
          productFamilyPrvtEntity.getData().setProdSubFamlText(
                                                              resultSet_.getString( C_PROD_SUB_FAML_TEXT ) );
        }
        else{
			productFamilyPrvtEntity.getData().setProdSubFamlText(null);                                                     
        }

        if(resultSet_.getString( C_PROD_FAML_CODE ) != null)
          productFamilyPrvtEntity.getData().setProdFamlCode(
                                                           new BigInteger(
                                                                           resultSet_.getString( C_PROD_FAML_CODE ) ) );
		if(resultSet_.getDate( C_LAST_UPD_DATE )!= null)
          productFamilyPrvtEntity.getData().setLastUpdDate(
                                                          resultSet_.getDate( C_LAST_UPD_DATE ) );
        
        if(resultSet_.getString( C_LAST_UPD_USER_ID )!= null)
          productFamilyPrvtEntity.getData().setLastUpdUserId(
                                                            resultSet_.getString( C_LAST_UPD_USER_ID ) );
        // Casting para a atribuicao das colunas especificas
        familyPrvtEntityVO = ( TplProdSubFamlPrvtEntityVO ) productFamilyPrvtEntity.getData();
        
        if(resultSet_.getDate( C_LAST_AUTH_DATE ) != null)
          familyPrvtEntityVO.setLastAuthDate( resultSet_.getDate( C_LAST_AUTH_DATE ) );
        
        if(resultSet_.getString( C_LAST_AUTH_USER_ID ) != null)
          familyPrvtEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );
        
        if(resultSet_.getString( C_REC_STAT_CODE ) != null)
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
                              TplProdSubFamlPrvtEntity tplProductSubFamilyPrvtEntity_ )
  {
    boolean exists = true;

    try
    {
      TplProdSubFamlPrvtEntity famlPrvtEntity = ( TplProdSubFamlPrvtEntity ) this.find( tplProductSubFamilyPrvtEntity_ );
      TplProdSubFamlPrvtEntityVO entityVO = ( TplProdSubFamlPrvtEntityVO ) famlPrvtEntity.getData();

      if ( !TplProdSubFamlPrvtEntity.C_REC_STAT_CODE_ACTIVE.equals( entityVO.getRecStatCode() ) )
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
   * Existe alguma subfamília com status ativo que utiliza o código de família
   * passada por parâmetros
   */
  public boolean existsProductFamilyPrvtDependency(
                                                   TplProdSubFamlPrvtEntity tplProductSubFamilyPrvtEntity_ )
  {
    boolean exists = false;

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT COUNT ( " );
      query.append( C_PROD_SUB_FAML_CODE + " ) " );
      query.append( " FROM " );
      query.append( C_TABLE_NAME );
      query.append( " WHERE " );
      query.append( C_REC_STAT_CODE + " = ? " );
      query.append( " AND " );
      query.append( C_PROD_FAML_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++,
                           BaseTplProdSubFamlPrvtEntity.C_REC_STAT_CODE_ACTIVE );
      if ( tplProductSubFamilyPrvtEntity_.getData().getProdFamlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductSubFamilyPrvtEntity_.getData().getProdFamlCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.next() )
      {
        int numberOfSubFamilyDependents = resultSet.getInt( 1 );
        if ( numberOfSubFamilyDependents > 0 )
        {
          exists = true;
        }
      }
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
      query.append( C_PROD_SUB_FAML_CODE + ", " );
      query.append( C_PROD_SUB_FAML_NAME );
      query.append( " FROM " );
      query.append( C_TABLE_NAME );
      query.append( " WHERE " );
      query.append( C_REC_STAT_CODE + " <> ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++,
                           TplProdSubFamlPrvtEntity.C_REC_STAT_CODE_INACTIVE );

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

/* (non-Javadoc)
 * @see com.citibank.ods.persistence.pl.dao.BaseTplProdSubFamlPrvtDAO#findByProdCode(com.citibank.ods.entity.pl.BaseTplProdSubFamlPrvtEntity)
 */
  public BaseTplProdSubFamlPrvtEntity findByProdCode(String prodPk)
  {
	ManagedRdbConnection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	StringBuffer query = new StringBuffer();

	ArrayList tplProductFamilyPrvtEntities;
	BaseTplProdSubFamlPrvtEntity baseTplProductSubFamilyPrvtEntity = null;

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "SELECT PROD_SUB_FAML_CODE, ");
	  query.append( "PROD_SUB_FAML_NAME, ");
      query.append( "PROD_SUB_FAML_TEXT, ");
      query.append( "PROD_FAML_CODE, ");
      query.append( "LAST_UPD_DATE,    ");
      query.append( "LAST_UPD_USER_ID,    ");
      query.append( "LAST_AUTH_DATE, ");
      query.append( "LAST_AUTH_USER_ID, ");
      query.append( "REC_STAT_CODE ");
      query.append( "  FROM (SELECT PROD_SUB_FAML_CODE,       ");
      query.append( " PROD_SUB_FAML_NAME,");
      query.append( " PROD_SUB_FAML_TEXT,   ");
      query.append( " PROD_FAML_CODE,  ");
      query.append( " LAST_UPD_DATE,      ");
      query.append( " LAST_UPD_USER_ID,      ");
      query.append( " LAST_AUTH_DATE,   ");
      query.append( " LAST_AUTH_USER_ID,   ");
      query.append( " REC_STAT_CODE,  ");
      query.append( " ROWNUM     ");
      query.append( "   FROM (SELECT SUBF.PROD_SUB_FAML_CODE,       ");
      query.append( "  SUBF.PROD_SUB_FAML_NAME,");
      query.append( "  SUBF.PROD_SUB_FAML_TEXT,   ");
      query.append( "  SUBF.PROD_FAML_CODE,  ");
      query.append( "  SUBF.LAST_UPD_DATE,      ");
      query.append( "  SUBF.LAST_UPD_USER_ID,      ");
      query.append( "  SUBF.LAST_AUTH_DATE,   ");
      query.append( "  SUBF.LAST_AUTH_USER_ID,   ");
      query.append( "  SUBF.REC_STAT_CODE   ");
      query.append( "    FROM PL.TPL_PRODUCT_SUB_FAMILY_PRVT SUBF,     ");
      query.append( "  PL.TPL_PRODUCT   PROD      ");
	  query.append( "   WHERE PROD.PROD_SUB_FAML_CODE = SUBF.PROD_SUB_FAML_CODE(+)   ");
	  query.append( "     AND PROD.PROD_CODE = ?    ");
	  query.append( "  UNION ALL   ");
	  query.append( "  SELECT SUBF.PROD_SUB_FAML_CODE,");
	  query.append( "  SUBF.PROD_SUB_FAML_NAME,  ");
	  query.append( "  SUBF.PROD_SUB_FAML_TEXT,     ");
	  query.append( "  SUBF.PROD_FAML_CODE,    ");
	  query.append( "  SUBF.LAST_UPD_DATE,");
	  query.append( "  SUBF.LAST_UPD_USER_ID,");
	  query.append( "  SUBF.LAST_AUTH_DATE,     ");
	  query.append( "  SUBF.LAST_AUTH_USER_ID,     ");
	  query.append( "  SUBF.REC_STAT_CODE     ");
	  query.append( "    FROM PL.TPL_PRODUCT_SUB_FAMILY_PRVT SUBF,       ");
	  query.append( "  PL.TPL_PRODUCT_MOV      PROD ");
	  query.append( "   WHERE PROD.PROD_SUB_FAML_CODE = SUBF.PROD_SUB_FAML_CODE(+)     ");
	  query.append( "     AND PROD.PROD_CODE = ?)     ");
	  query.append( "  ORDER BY ROWNUM DESC) ");
	  query.append( " WHERE ROWNUM < 2  ");       

	  statement = connection.prepareStatement( query.toString() );

	  statement.setString(1,prodPk);
	  statement.setString(2,prodPk);

	  resultSet = statement.executeQuery();

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
		baseTplProductSubFamilyPrvtEntity = ( BaseTplProdSubFamlPrvtEntity ) tplProductFamilyPrvtEntities.get( 0 );
	  }

	  return baseTplProductSubFamilyPrvtEntity;
	}
	catch ( SQLException e )
	{
	  throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
	}
	
		
  }


}