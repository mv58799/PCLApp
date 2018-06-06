package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.DataSetRow;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplErEmEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.TplErEmEntity;
import com.citibank.ods.entity.pl.TplProductEntity;
import com.citibank.ods.entity.pl.TplRoleCustEntity;
import com.citibank.ods.entity.pl.valueobject.TplErEmEntityVO;
import com.citibank.ods.persistence.pl.dao.TplErEmDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 9, 2007
 *  
 */

public class OracleTplErEmDAO extends BaseOracleTplErEmDAO implements
    TplErEmDAO
{
  /*
   * Nome da tabela
   */
  private static final String C_TABLE_NAME = C_PL_SCHEMA + "TPL_ER_EM";

  /*
   * Colunas da tabela
   */
  public String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  public String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  public String C_REC_STAT_CODE = "REC_STAT_CODE";

  /**
   * Método que realiza a inserção na tabela
   */
  public TplErEmEntity insert( TplErEmEntity erEmEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TABLE_NAME + " (" );
      query.append( C_ER_NBR + ", " );
      query.append( C_EM_NBR + ", " );
      query.append( C_ROLE_CUST_CODE + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ?)" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      // C_ER_NBR
      if ( erEmEntity_.getData().getErNbr() != null
           && erEmEntity_.getData().getErNbr() != "" )
      {
        preparedStatement.setString( count++, erEmEntity_.getData().getErNbr() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      // C_EM_NBR
      if ( erEmEntity_.getData().getEmNbr() != null
           && erEmEntity_.getData().getEmNbr() != "" )
      {
        preparedStatement.setString( count++, erEmEntity_.getData().getEmNbr() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      //C_ROLE_CUST_CODE
      if ( erEmEntity_.getData().getRoleCustCode() != null
           && erEmEntity_.getData().getRoleCustCode() != "" )
      {
        preparedStatement.setString( count++, erEmEntity_.getData().getRoleCustCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      // C_LAST_UPD_DATE
      if ( erEmEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               erEmEntity_.getData().getLastUpdDate().getTime() ) );

      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      // C_LAST_UPD_USER_ID
      if ( erEmEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++, erEmEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      TplErEmEntityVO tplErEmEntityVO = ( TplErEmEntityVO ) erEmEntity_.getData();

      //C_LAST_AUTH_DSATE
      if ( tplErEmEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplErEmEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      //    C_LAST_AUTH_USER_ID
      if ( tplErEmEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++, tplErEmEntityVO.getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      //    RECSTATCODE
      if ( tplErEmEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, tplErEmEntityVO.getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
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

    return erEmEntity_;
  }

  /**
   * Remove os relacionamentos
   */
  public void deleteRelations( String erNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer sqlQuery = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      sqlQuery.append( " DELETE FROM " );
      sqlQuery.append( C_TABLE_NAME );
      sqlQuery.append( " WHERE " );
      sqlQuery.append( C_ER_NBR + " like ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

      preparedStatement.setString( 1, erNbr_ );

	  preparedStatement.replaceParametersInQuery(sqlQuery.toString());
      preparedStatement.executeUpdate();
	  
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
   * Atualiza status para inativo
   */
  public TplErEmEntity update( TplErEmEntity tplErEmEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TABLE_NAME + "  SET " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_LAST_AUTH_USER_ID + " = ?, " );
      query.append( C_LAST_AUTH_DATE + " = ?, " );
      query.append( C_REC_STAT_CODE + " = ?, " );
      query.append( C_ROLE_CUST_CODE + " = ? " );     
      query.append( " WHERE " );
      query.append( C_ER_NBR + " = ? AND " );
      query.append( C_EM_NBR + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplErEmEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplErEmEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplErEmEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplErEmEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      // Casting para Obter os campos especificos da tabela
      TplErEmEntityVO tplErEmEntityVO = ( TplErEmEntityVO ) tplErEmEntity_.getData();

      if ( tplErEmEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++, tplErEmEntityVO.getLastAuthUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplErEmEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplErEmEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      if ( tplErEmEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, tplErEmEntityVO.getRecStatCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      
      if ( tplErEmEntityVO.getRoleCustCode() != null )
      {
        preparedStatement.setString( count++, tplErEmEntityVO.getRoleCustCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplErEmEntityVO.getErNbr() != null )
      {
        preparedStatement.setString( count++, tplErEmEntityVO.getErNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplErEmEntityVO.getEmNbr() != null )
      {
        preparedStatement.setString( count++, tplErEmEntityVO.getEmNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplErEmEntity_;
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
   * Realiza a consulta em Lista a partir dos critérios de pesquisa definidos
   */
  public DataSet list( String erNbr_, String emNbr_, BigInteger custNbr_,
                      String custFullName_, BigInteger reltnNbr_,
                      BigInteger curAcctNbr_, boolean includeInactive )
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
      query.append( C_TABLE_NAME + "." + C_ER_NBR + ", " );
      query.append( C_TABLE_NAME + "." + C_EM_NBR + ", " );
      query.append( C_TPL_CUSTOMER_PRVT + "." + C_CUST_FULL_NAME_TEXT + ", " );
      query.append( C_TPL_CUSTOMER_PRVT + "." + C_CUST_NBR + ", " );
      query.append( C_TPL_ROLE_CUST + "." + C_ROLE_CUST_TEXT + ", " );
      query.append( C_TABLE_NAME + "." + C_ROLE_CUST_CODE + ", " );
      query.append( C_TABLE_NAME + "." + C_LAST_UPD_DATE + ", " );
      query.append( C_TABLE_NAME + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( C_TABLE_NAME + "." + C_LAST_AUTH_DATE + ", " );
      query.append( C_TABLE_NAME + "." + C_LAST_AUTH_USER_ID + ", " );
      query.append( C_TABLE_NAME + "." + C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TABLE_NAME + "," + C_TPL_CUSTOMER_PRVT_CMPL + ","
                    + C_TPL_CUSTOMER_PRVT + "," + C_TPL_ROLE_CUST );
      query.append( " WHERE " );
      query.append( C_TABLE_NAME + "." + C_EM_NBR + "="
                    + C_TPL_CUSTOMER_PRVT_CMPL + "." + C_EM_NBR );
      query.append( " AND " + C_TPL_CUSTOMER_PRVT_CMPL + "." + C_CUST_NBR + "="
                    + C_TPL_CUSTOMER_PRVT + "." + C_CUST_NBR );
      query.append( " AND " + C_TPL_ROLE_CUST + "." + C_ROLE_CUST_CODE + "="
                    + C_TABLE_NAME + "." + C_ROLE_CUST_CODE );
      if (!includeInactive) {      
    	  query.append( " AND " + C_TABLE_NAME + "." + C_REC_STAT_CODE + " != '"
                    + BaseODSEntity.C_REC_STAT_CODE_INACTIVE + "' " );
      }

      if ( erNbr_ != null && !erNbr_.equals( "" ) )
      {
        query.append( " AND " + C_TABLE_NAME + "." + C_ER_NBR + " = ?" );
      }

      if ( emNbr_ != null && !emNbr_.equals( "" ) )
      {
        query.append( " AND " + C_TABLE_NAME + "." + C_EM_NBR + " = ?" );
      }

      if ( custNbr_ != null )
      {
        query.append( " AND " + C_TPL_CUSTOMER_PRVT_CMPL + "." + C_CUST_NBR
                      + " = ?" );
      }

      if ( custFullName_ != null && !custFullName_.equals( "" ) )
      {
        query.append( " AND UPPER(" + C_TPL_CUSTOMER_PRVT + "."
                      + C_CUST_FULL_NAME_TEXT + ") LIKE ?" );
      }

      if ( reltnNbr_ != null )
      {
        query.append( " AND " + C_TPL_CUSTOMER_PRVT_CMPL + "." + C_CUST_NBR
                      + " IN " );
        query.append( " ( " );
        query.append( " SELECT " + C_TPL_RELATION_PRVT + "."
                      + C_RELTN_CUST_1_NBR );
        query.append( " FROM " );
        query.append( C_TPL_RELATION_PRVT );
        query.append( " WHERE " );
        query.append( C_TPL_RELATION_PRVT + "." + C_RELTN_NBR + "= ?" );
        query.append( " ) " );
      }

      if ( curAcctNbr_ != null )
      {
        query.append( " AND " + C_TPL_CUSTOMER_PRVT_CMPL + "." + C_CUST_NBR
                      + " IN " );
        query.append( " ( " );
        query.append( " SELECT " + C_TPL_CUSTOMER_PRVT_CMPL + "." + C_CUST_NBR );
        query.append( " FROM " );
        query.append( C_TPL_CUSTOMER_PRVT_CMPL + "," + C_TO3_PRODUCT_ACCOUNT
                      + " PROD_ACC" );
        query.append( " WHERE " );
        query.append( " PROD_ACC." + C_SYS_CODE + " = '" + C_SYS_CODE_VALUE
                      + "'" );
        query.append( " AND PROD_ACC." + C_SYS_SEG_CODE + " = "
                      + C_SYS_SEG_CODE_VALUE );
        query.append( " AND TRIM(PROD_ACC." + C_PROD_CODE + ") = "
                      + C_PROD_CODE_VALUE );
        query.append( " AND PROD_ACC." + C_CUR_ACCT_NBR + " = ?" );
        query.append( " ) " );
      }
      query.append( " ORDER BY " + C_CUST_FULL_NAME_TEXT + ", "
                    + C_ROLE_CUST_TEXT + ", " + C_EM_NBR + ", " + C_ER_NBR );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( erNbr_ != null && !erNbr_.equals( "" ) )
      {
        preparedStatement.setString( count++, erNbr_ );
      }

      if ( emNbr_ != null && !emNbr_.equals( "" ) )
      {
        preparedStatement.setString( count++, emNbr_ );
      }

      if ( custNbr_ != null )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
      }

      if ( custFullName_ != null && !custFullName_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + custFullName_.toUpperCase() + "%" );
      }

      if ( reltnNbr_ != null )
      {
        preparedStatement.setLong( count++, reltnNbr_.longValue() );
      }

      if ( curAcctNbr_ != null )
      {
        preparedStatement.setLong( count++, curAcctNbr_.longValue() );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      rsds = new ResultSetDataSet( resultSet );
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

    return rsds;
  }

  /**
   * Consulta dos registros a partir do Número de ER
   */
  public ArrayList listByErNbr( String erNbr_,String emNbr_ )
  {
    DataSetRow row;
    TplErEmEntity tplErEmEntity;
    TplErEmEntityVO tplErEmEntityVO;
    TplRoleCustEntity tplRoleCustEntity;
    TplCustomerPrvtEntity tplCustomerPrvtEntity;
    ArrayList result = new ArrayList();

    DataSet rds = this.list( erNbr_, emNbr_, null, null, null, null, false );
    for ( int indexRow = 0; indexRow < rds.size(); indexRow++ )
    {
      row = rds.getRow( indexRow );

      tplErEmEntity = new TplErEmEntity();
      tplErEmEntityVO = ( TplErEmEntityVO ) tplErEmEntity.getData();

      tplErEmEntityVO.setErNbr( row.getStringByName( C_ER_NBR ) );
      tplErEmEntityVO.setEmNbr( row.getStringByName( C_EM_NBR ) );
      tplErEmEntityVO.setRoleCustCode( row.getStringByName( C_ROLE_CUST_CODE ) );
      tplErEmEntityVO.setLastUpdDate( row.getDateByName( C_LAST_UPD_DATE ) );
      tplErEmEntityVO.setLastUpdUserId( row.getStringByName( C_LAST_UPD_USER_ID ) );
      tplErEmEntityVO.setLastAuthDate( row.getDateByName( C_LAST_AUTH_DATE ) );
      tplErEmEntityVO.setLastAuthUserId( row.getStringByName( C_LAST_AUTH_USER_ID ) );
      tplErEmEntityVO.setRecStatCode( row.getStringByName( C_REC_STAT_CODE ) );

      tplRoleCustEntity = new TplRoleCustEntity();
      tplRoleCustEntity.getData().setRoleCustCode(
                                                   row.getStringByName( C_ROLE_CUST_CODE ) );
      tplRoleCustEntity.getData().setRoleCustText(
                                                   row.getStringByName( C_ROLE_CUST_TEXT ) );
      tplErEmEntity.setRoleCustEntity( tplRoleCustEntity );

      tplCustomerPrvtEntity = new TplCustomerPrvtEntity();
      tplCustomerPrvtEntity.getData().setCustNbr(
                                                  new BigInteger(
                                                                  row.getStringByName( C_CUST_NBR ) ) );
      tplCustomerPrvtEntity.getData().setCustFullNameText(
                                                           row.getStringByName( C_CUST_FULL_NAME_TEXT ) );
      tplErEmEntity.setCustomerPrvtEntity( tplCustomerPrvtEntity );
      result.add( tplErEmEntity );
    }

    return result;
  }

  /**
   * Verifica se existe um relacionamento ativo
   */
  public boolean existsRelationActive( String erNbr_ , String emNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    boolean existsRelationActive = false;

    try
    {
      query.append( "SELECT " );
      query.append( "COUNT ( " + C_ER_NBR + ") " );
      query.append( "FROM " + C_TABLE_NAME );
      query.append( " WHERE " );
      query.append( C_ER_NBR + " like ? AND " );
      query.append( C_EM_NBR + " like ? AND " );
      query.append( C_REC_STAT_CODE + " like '"
                    + TplErEmEntity.C_REC_STAT_CODE_ACTIVE + "'" );

      connection = OracleODSDAOFactory.getConnection();

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++, erNbr_ );
      preparedStatement.setString( count++, emNbr_ );
      
      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.next() )
      {
        int rowNumber = resultSet.getInt( 1 );
        if ( rowNumber > 0 )
        {
          existsRelationActive = true;
        }
      }
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

    return existsRelationActive;
  }

  /**
   * Verifica se existe um relacionamento
   */
  public boolean existsRelation( String erNbr_, String emNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    boolean existsRelation = false;

    try
    {
      query.append( "SELECT " );
      query.append( "COUNT ( " + C_ER_NBR + ") " );
      query.append( "FROM " + C_TABLE_NAME );
      query.append( " WHERE " );
      query.append( C_ER_NBR + " LIKE ? " );

      connection = OracleODSDAOFactory.getConnection();

      String criteria = "";
      if ( emNbr_ != null && !emNbr_.equals( "" ) )
      {
        criteria = ( "AND " + C_EM_NBR + " LIKE ? " );
      }

      if ( criteria.length() > 0 )
      {
        query.append( criteria );
      }
      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      preparedStatement.setString( count++, erNbr_ );

      if ( emNbr_ != null && !emNbr_.equals( "" ) )
      {
        preparedStatement.setString( count++, emNbr_ );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.next() )
      {
        int rowNumber = resultSet.getInt( 1 );
        if ( rowNumber > 0 )
        {
          existsRelation = true;
        }
      }
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

    return existsRelation;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTplErEmDAO#find(com.citibank.ods.entity.pl.BaseTplErEmEntity)
   */
  public BaseTplErEmEntity find( BaseTplErEmEntity erEmEntity_ )
  {
    return null;
  }

  //Combo com os números do ER que serão carregados na Tela de Relation X EG.
  public DataSet loadErNbr()
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT DISTINCT " );
      query.append( C_ER_NBR );
      query.append( " FROM " );
      query.append( C_TABLE_NAME );
      query.append( " WHERE " );
      query.append( C_REC_STAT_CODE + " <> ?" );
      query.append( " ORDER BY " + C_ER_NBR );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++, TplProductEntity.C_REC_STAT_CODE_INACTIVE );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      rsds = new ResultSetDataSet( resultSet );
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
    return rsds;
  }

  /**
   * Verifica se existe um relacionamento inativo
   */
  public boolean existsRelationInactive( String erNbr_ , String emNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    boolean existsRelationActive = false;

    try
    {
      query.append( "SELECT " );
      query.append( "COUNT ( " + C_ER_NBR + ") " );
      query.append( "FROM " + C_TABLE_NAME );
      query.append( " WHERE " );
      query.append( C_ER_NBR + " like ? AND " );
      query.append( C_EM_NBR + " like ? AND " );
      query.append( C_REC_STAT_CODE + " like '"
                    + TplErEmEntity.C_REC_STAT_CODE_INACTIVE + "'" );

      connection = OracleODSDAOFactory.getConnection();

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++, erNbr_ );
      preparedStatement.setString( count++, emNbr_ );
      
      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.next() )
      {
        int rowNumber = resultSet.getInt( 1 );
        if ( rowNumber > 0 )
        {
          existsRelationActive = true;
        }
      }
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

    return existsRelationActive;
  }
  
  /**
   * Consulta dos registros a partir do Número de ER e EM
   */
  public ArrayList listAllByErEM( String erNbr_,String emNbr_ )
  {
    DataSetRow row;
    TplErEmEntity tplErEmEntity;
    TplErEmEntityVO tplErEmEntityVO;
    TplRoleCustEntity tplRoleCustEntity;
    TplCustomerPrvtEntity tplCustomerPrvtEntity;
    ArrayList result = new ArrayList();

    DataSet rds = this.list( erNbr_, emNbr_, null, null, null, null, true );
    for ( int indexRow = 0; indexRow < rds.size(); indexRow++ )
    {
      row = rds.getRow( indexRow );

      tplErEmEntity = new TplErEmEntity();
      tplErEmEntityVO = ( TplErEmEntityVO ) tplErEmEntity.getData();

      tplErEmEntityVO.setErNbr( row.getStringByName( C_ER_NBR ) );
      tplErEmEntityVO.setEmNbr( row.getStringByName( C_EM_NBR ) );
      tplErEmEntityVO.setRoleCustCode( row.getStringByName( C_ROLE_CUST_CODE ) );
      tplErEmEntityVO.setLastUpdDate( row.getDateByName( C_LAST_UPD_DATE ) );
      tplErEmEntityVO.setLastUpdUserId( row.getStringByName( C_LAST_UPD_USER_ID ) );
      tplErEmEntityVO.setLastAuthDate( row.getDateByName( C_LAST_AUTH_DATE ) );
      tplErEmEntityVO.setLastAuthUserId( row.getStringByName( C_LAST_AUTH_USER_ID ) );
      tplErEmEntityVO.setRecStatCode( row.getStringByName( C_REC_STAT_CODE ) );

      tplRoleCustEntity = new TplRoleCustEntity();
      tplRoleCustEntity.getData().setRoleCustCode(
                                                   row.getStringByName( C_ROLE_CUST_CODE ) );
      tplRoleCustEntity.getData().setRoleCustText(
                                                   row.getStringByName( C_ROLE_CUST_TEXT ) );
      tplErEmEntity.setRoleCustEntity( tplRoleCustEntity );

      tplCustomerPrvtEntity = new TplCustomerPrvtEntity();
      tplCustomerPrvtEntity.getData().setCustNbr(
                                                  new BigInteger(
                                                                  row.getStringByName( C_CUST_NBR ) ) );
      tplCustomerPrvtEntity.getData().setCustFullNameText(
                                                           row.getStringByName( C_CUST_FULL_NAME_TEXT ) );
      tplErEmEntity.setCustomerPrvtEntity( tplCustomerPrvtEntity );
      result.add( tplErEmEntity );
    }

    return result;
  }

}