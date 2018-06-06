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
import com.citibank.ods.common.dataset.DataSetRow;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplErEmEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.TplErEmMovEntity;
import com.citibank.ods.entity.pl.TplRoleCustEntity;
import com.citibank.ods.entity.pl.valueobject.TplErEmMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplErEmMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 9, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class OracleTplErEmMovDAO extends BaseOracleTplErEmDAO implements
    TplErEmMovDAO
{
  /*
   * Nome da tabela
   */
  private static final String C_TABLE_NAME = C_PL_SCHEMA + "TPL_ER_EM_MOV";

  /*
   * Colunas da tabela
   */
  public String C_OPERN_CODE = "OPERN_CODE";

  public String C_OPERN_TEXT = "OPERN_TEXT";

  /**
   * Realiza a inserção das informações
   */
  public TplErEmMovEntity insert( TplErEmMovEntity tplRelationEgMovEntity_ )
                                                                            throws UnexpectedException
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
      query.append( C_OPERN_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?)" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      TplErEmMovEntityVO tplRelationEgMovEntityVO = ( TplErEmMovEntityVO ) tplRelationEgMovEntity_.getData();

      // C_ER_NBR
      if ( tplRelationEgMovEntityVO.getErNbr() != null
           && tplRelationEgMovEntityVO.getErNbr() != "" )
      {
        preparedStatement.setString( count++, tplRelationEgMovEntityVO.getErNbr() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      // C_EM_NBR
      if ( tplRelationEgMovEntityVO.getEmNbr() != null
           && tplRelationEgMovEntityVO.getEmNbr() != "" )
      {
        preparedStatement.setString( count++, tplRelationEgMovEntityVO.getEmNbr() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      //C_ROLE_CUST_CODE
      if ( tplRelationEgMovEntityVO.getRoleCustCode() != null
           && tplRelationEgMovEntityVO.getRoleCustCode() != "" )
      {
        preparedStatement.setString( count++,
                             tplRelationEgMovEntityVO.getRoleCustCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      // C_LAST_UPD_DATE
      if ( tplRelationEgMovEntityVO.getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplRelationEgMovEntityVO.getLastUpdDate().getTime() ) );

      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      // C_LAST_UPD_USER_ID
      if ( tplRelationEgMovEntityVO.getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplRelationEgMovEntityVO.getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      //    OPernCode
      if ( tplRelationEgMovEntityVO.getOpernCode() != null )
      {
        preparedStatement.setString( count++, tplRelationEgMovEntityVO.getOpernCode() );
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
      throw new UnexpectedException (C_ERROR_EXECUTING_STATEMENT, e);
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }

    return tplRelationEgMovEntity_;
  }

  /**
   * Remove os relacionamentos
   */
  public void deleteRelations( String erNbr_ ) throws UnexpectedException
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
   * Realiza a consulta em lista
   */
  public DataSet list( String erNbr_, String emNbr_, BigInteger custNbr_,
                      String custFullName_, BigInteger reltnNbr_,
                      BigInteger curAcctNbr_, String lastUpdUserId_ )
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
      query.append( C_TPL_ROLE_CUST + "." + C_ROLE_CUST_TEXT + ", " );
      query.append( C_TABLE_NAME + "." + C_ROLE_CUST_CODE + ", " );
      query.append( C_TABLE_NAME + "." + C_LAST_UPD_DATE + ", " );
      query.append( C_TABLE_NAME + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( C_TABLE_NAME + "." + C_OPERN_CODE );
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

      if ( lastUpdUserId_ != null && !lastUpdUserId_.equals( "" ) )
      {
        query.append( " AND UPPER(" + C_TABLE_NAME + "." + C_LAST_UPD_USER_ID
                      + ") LIKE ?" );
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

      if ( lastUpdUserId_ != null && !lastUpdUserId_.equals( "" ) )
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
   * Realiza a consulta a partir do Er
   */
  public ArrayList listByErNbr( String erNbr_ )
  {

    DataSetRow row;
    TplErEmMovEntity tplErEmMovEntity;
    TplErEmMovEntityVO tplErEmMovEntityVO;
    TplRoleCustEntity tplRoleCustEntity;
    TplCustomerPrvtEntity tplCustomerPrvtEntity;
    ArrayList result = new ArrayList();

    DataSet rds = this.list( erNbr_, null, null, null, null, null, null );
    for ( int indexRow = 0; indexRow < rds.size(); indexRow++ )
    {
      row = rds.getRow( indexRow );

      tplErEmMovEntity = new TplErEmMovEntity();
      tplErEmMovEntityVO = ( TplErEmMovEntityVO ) tplErEmMovEntity.getData();

      tplErEmMovEntityVO.setErNbr( row.getStringByName( C_ER_NBR ) );
      tplErEmMovEntityVO.setEmNbr( row.getStringByName( C_EM_NBR ) );
      tplErEmMovEntityVO.setRoleCustCode( row.getStringByName( C_ROLE_CUST_CODE ) );
      tplErEmMovEntityVO.setLastUpdDate( row.getDateByName( C_LAST_UPD_DATE ) );
      tplErEmMovEntityVO.setLastUpdUserId( row.getStringByName( C_LAST_UPD_USER_ID ) );
      tplErEmMovEntityVO.setOpernCode( row.getStringByName( C_OPERN_CODE ) );

      tplRoleCustEntity = new TplRoleCustEntity();
      tplRoleCustEntity.getData().setRoleCustCode(
                                                   row.getStringByName( C_ROLE_CUST_CODE ) );
      tplRoleCustEntity.getData().setRoleCustText(
                                                   row.getStringByName( C_ROLE_CUST_TEXT ) );
      tplErEmMovEntity.setRoleCustEntity( tplRoleCustEntity );

      tplCustomerPrvtEntity = new TplCustomerPrvtEntity();
      tplCustomerPrvtEntity.getData().setCustFullNameText(
                                                           row.getStringByName( C_CUST_FULL_NAME_TEXT ) );
      tplErEmMovEntity.setCustomerPrvtEntity( tplCustomerPrvtEntity );

      result.add( tplErEmMovEntity );
    }

    return result;
  }

  /**
   * Verifica se existe alguma relacionamento deste er
   */
  public boolean existsRelation( String erNbr_ )
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
      query.append( C_ER_NBR + " like ?" );

      connection = OracleODSDAOFactory.getConnection();

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++, erNbr_ );

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
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
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

}