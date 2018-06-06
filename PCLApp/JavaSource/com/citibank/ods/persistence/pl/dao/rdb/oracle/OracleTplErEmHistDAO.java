package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplErEmEntity;
import com.citibank.ods.entity.pl.TplErEmHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplErEmHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplErEmHistDAO;
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

public class OracleTplErEmHistDAO extends BaseOracleTplErEmDAO implements
    TplErEmHistDAO
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplErEmHistDAO#insert(com.citibank.ods.entity.pl.TplErEmHistEntity)
   */

  public String C_TPL_ER_EM_HIST = C_PL_SCHEMA + "TPL_ER_EM_HIST";

  public String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  public String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  public String C_REC_STAT_CODE = "REC_STAT_CODE";

  public String C_REC_STAT_TEXT = "REC_STAT_TEXT";

  public String C_ER_EM_REF_DATE = "ER_EM_REF_DATE";

  public TplErEmHistEntity insert( TplErEmHistEntity erEmEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_ER_EM_HIST + " (" );
      query.append( C_ER_NBR + ", " );
      query.append( C_EM_NBR + ", " );
      query.append( C_ROLE_CUST_CODE + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_ER_EM_REF_DATE + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ?, ?)" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( erEmEntity_.getData().getErNbr() != null
           && erEmEntity_.getData().getErNbr() != "" )
      {
        preparedStatement.setString( count++, erEmEntity_.getData().getErNbr() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( erEmEntity_.getData().getEmNbr() != null
           && erEmEntity_.getData().getEmNbr() != "" )
      {
        preparedStatement.setString( count++, erEmEntity_.getData().getEmNbr() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( erEmEntity_.getData().getRoleCustCode() != null
           && erEmEntity_.getData().getRoleCustCode() != "" )
      {
        preparedStatement.setString( count++, erEmEntity_.getData().getRoleCustCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

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

      if ( erEmEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++, erEmEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      TplErEmHistEntityVO emHistEntityVO = ( TplErEmHistEntityVO ) erEmEntity_.getData();
      if ( emHistEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               emHistEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( emHistEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++, emHistEntityVO.getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( emHistEntityVO.getErEmRefDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( emHistEntityVO.getErEmRefDate().getTime() ) ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( emHistEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, emHistEntityVO.getRecStatCode() );
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
      System.err.println( e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }

    return erEmEntity_;
  } /*
     * (non-Javadoc)
     * @see com.citibank.ods.persistence.pl.dao.TplErEmHistDAO#list(java.math.BigInteger,
     *      java.math.BigInteger, java.lang.String, java.math.BigInteger,
     *      java.util.Date)
     */

  public DataSet list( String emNbrSrc_, String erNbrSrc_, Date erEmRefDate_,
                      BigInteger custNbr_, String custFullName_,
                      BigInteger reltnNbr_, BigInteger curAcctNbr_ )
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
      query.append( C_TPL_ER_EM_HIST + "." + C_ER_NBR + ", " );
      query.append( C_TPL_ER_EM_HIST + "." + C_EM_NBR + ", " );
      query.append( C_TPL_CUSTOMER_PRVT + "." + C_CUST_FULL_NAME_TEXT + ", " );
      query.append( C_TPL_CUSTOMER_PRVT + "." + C_CUST_NBR + ", " );
      query.append( C_TPL_ROLE_CUST + "." + C_ROLE_CUST_TEXT + ", " );
      query.append( C_TPL_ER_EM_HIST + "." + C_ROLE_CUST_CODE + ", " );
      query.append( C_TPL_ER_EM_HIST + "." + C_LAST_UPD_DATE + ", " );
      query.append( C_TPL_ER_EM_HIST + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( C_TPL_ER_EM_HIST + "." + C_LAST_AUTH_DATE + ", " );
      query.append( C_TPL_ER_EM_HIST + "." + C_LAST_AUTH_USER_ID + ", " );
      query.append( C_TPL_ER_EM_HIST + "." + C_ER_EM_REF_DATE + ", " );
      query.append( C_TPL_ER_EM_HIST + "." + C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_ER_EM_HIST + "," + C_TPL_CUSTOMER_PRVT_CMPL + ","
                    + C_TPL_CUSTOMER_PRVT + "," + C_TPL_ROLE_CUST );
      query.append( " WHERE " );
      query.append( C_TPL_ER_EM_HIST + "." + C_EM_NBR + "="
                    + C_TPL_CUSTOMER_PRVT_CMPL + "." + C_EM_NBR );
      query.append( " AND " + C_TPL_CUSTOMER_PRVT_CMPL + "." + C_CUST_NBR + "="
                    + C_TPL_CUSTOMER_PRVT + "." + C_CUST_NBR );
      query.append( " AND " + C_TPL_ROLE_CUST + "." + C_ROLE_CUST_CODE + "="
                    + C_TPL_ER_EM_HIST + "." + C_ROLE_CUST_CODE );

      String criteria = "";
      if ( emNbrSrc_ != null && !emNbrSrc_.equals( "" ) )
      {
        criteria = criteria + C_TPL_ER_EM_HIST + "." + C_EM_NBR + " = ? AND ";
      }

      if ( erNbrSrc_ != null && !erNbrSrc_.equals( "" ) )
      {
        criteria = criteria + C_TPL_ER_EM_HIST + "." + C_ER_NBR + " = ? AND ";
      }

      if ( erEmRefDate_ != null )
      {
        criteria = criteria + "TRUNC (" + C_TPL_ER_EM_HIST + "."
                   + C_ER_EM_REF_DATE + ", \'DD\') >= ? AND ";
      }
      if ( custNbr_ != null )
      {
        criteria = criteria + C_TPL_CUSTOMER_PRVT_CMPL + "." + C_CUST_NBR
                   + " = ? AND ";
      }
      if ( custFullName_ != null && !custFullName_.equals( "" ) )
      {
        criteria = criteria
                   + ( " UPPER(" + C_TPL_CUSTOMER_PRVT + "."
                       + C_CUST_FULL_NAME_TEXT + ") LIKE ? AND " );
      }

      if ( reltnNbr_ != null )
      {
        criteria = criteria + C_TPL_CUSTOMER_PRVT_CMPL + "." + C_CUST_NBR
                   + " IN ( SELECT " + C_TPL_RELATION_PRVT + "."
                   + C_RELTN_CUST_1_NBR + " FROM " + C_TPL_RELATION_PRVT
                   + " WHERE " + C_TPL_RELATION_PRVT + "." + C_RELTN_NBR
                   + " = ? ) AND ";
      }

      if ( curAcctNbr_ != null )
      {
        criteria = criteria + C_TPL_CUSTOMER_PRVT_CMPL + "." + C_CUST_NBR
                   + " IN ( SELECT " + C_TPL_CUSTOMER_PRVT_CMPL + "."
                   + C_CUST_NBR + " FROM " + C_TPL_CUSTOMER_PRVT_CMPL + ","
                   + C_TO3_PRODUCT_ACCOUNT + " PROD_ACC WHERE " + " PROD_ACC."
                   + C_SYS_CODE + " = '" + C_SYS_CODE_VALUE + "' AND PROD_ACC."
                   + C_SYS_SEG_CODE + " = " + C_SYS_SEG_CODE_VALUE
                   + " AND TRIM(PROD_ACC." + C_PROD_CODE + ") = "
                   + C_PROD_CODE_VALUE + " AND PROD_ACC." + C_CUR_ACCT_NBR
                   + " = ? ) AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( " AND " + criteria );
      }

      query.append( " ORDER BY " + C_CUST_FULL_NAME_TEXT + ", "
                    + C_ROLE_CUST_TEXT + ", " + C_ER_EM_REF_DATE );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( emNbrSrc_ != null && !emNbrSrc_.equals( "" ) )
      {
        preparedStatement.setString( count++, emNbrSrc_ );
      }

      if ( erNbrSrc_ != null && !erNbrSrc_.equals( "" ) )
      {
        preparedStatement.setString( count++, erNbrSrc_ );
      }

      if ( erEmRefDate_ != null )
      {
        preparedStatement.setDate( count++, new java.sql.Date( erEmRefDate_.getTime() ) );
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
  } /*
     * (non-Javadoc)
     * @see com.citibank.ods.persistence.pl.dao.BaseTplErEmDAO#find(com.citibank.ods.entity.pl.BaseTplErEmEntity)
     */

  public BaseTplErEmEntity find( BaseTplErEmEntity erEmEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList tplErEmEntitys;
    BaseTplErEmEntity tplErEmEntity;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_ER_NBR + ", " );
      query.append( C_EM_NBR + ", " );
      query.append( C_ROLE_CUST_CODE + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_ER_EM_REF_DATE + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_ER_EM_HIST );
      query.append( " WHERE " );
      //query.append( C_ER_NBR + " = ? " );
      query.append( C_ER_NBR + " = ? AND " );
      //query.append( C_EM_NBR + " = ? AND " );
      query.append( C_ER_EM_REF_DATE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString( 1, erEmEntity_.getData().getErNbr().trim() );

      // preparedStatement.setString( 2, erEmEntity_.getData().getEmNbr() );

      preparedStatement.setTimestamp(
                              2,
                              new Timestamp(
                                             ( ( TplErEmHistEntityVO ) erEmEntity_.getData() ).getErEmRefDate().getTime() ) );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      tplErEmEntitys = instantiateFromResultSet( resultSet );

      if ( tplErEmEntitys.size() == 0 )
      {
        //throw new NoRowsReturnedException();
        tplErEmEntity = null;
      }
      else
      {
        tplErEmEntity = ( BaseTplErEmEntity ) tplErEmEntitys.get( 0 );
      }

      return tplErEmEntity;
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
  }

  /*
   * Retorna uma coleção de entities a partir do rs
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    TplErEmHistEntity tplErEmEntity;
    TplErEmHistEntityVO emHistEntityVO;
    ArrayList tplErEmHistEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplErEmEntity = new TplErEmHistEntity();
        emHistEntityVO = ( TplErEmHistEntityVO ) tplErEmEntity.getData();

        tplErEmEntity.getData().setEmNbr( resultSet_.getString( C_EM_NBR ) );
        tplErEmEntity.getData().setErNbr( resultSet_.getString( C_ER_NBR ) );
        emHistEntityVO.setLastAuthDate( resultSet_.getDate( C_LAST_AUTH_DATE ) );
        emHistEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );
        tplErEmEntity.getData().setLastUpdDate(
                                                resultSet_.getDate( C_LAST_UPD_DATE ) );
        tplErEmEntity.getData().setLastUpdUserId(
                                                  resultSet_.getString( C_LAST_UPD_USER_ID ) );
        tplErEmEntity.getData().setRoleCustCode(
                                                 resultSet_.getString( C_ROLE_CUST_CODE ) );

        // atribuicao das colunas especificas
        ( ( TplErEmHistEntityVO ) tplErEmEntity.getData() ).setErEmRefDate( resultSet_.getDate( C_ER_EM_REF_DATE ) );

        tplErEmHistEntities.add( tplErEmEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return tplErEmHistEntities;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplEREMHistDAO#listHistory(com.citibank.ods.entity.pl.TplErEmHistEntity)
   */
  public DataSet listHistory( TplErEmHistEntity erEmEntity_ )
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
      query.append( C_TPL_ER_EM_HIST + "." + C_ER_NBR + ", " );
      query.append( C_TPL_ER_EM_HIST + "." + C_EM_NBR + ", " );
      query.append( C_TPL_CUSTOMER_PRVT + "." + C_CUST_FULL_NAME_TEXT + ", " );
      query.append( C_TPL_CUSTOMER_PRVT + "." + C_CUST_NBR + ", " );
      query.append( C_TPL_ROLE_CUST + "." + C_ROLE_CUST_TEXT + ", " );
      query.append( C_TPL_ER_EM_HIST + "." + C_ROLE_CUST_CODE + ", " );
      query.append( C_TPL_ER_EM_HIST + "." + C_LAST_UPD_DATE + ", " );
      query.append( C_TPL_ER_EM_HIST + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( C_TPL_ER_EM_HIST + "." + C_LAST_AUTH_DATE + ", " );
      query.append( C_TPL_ER_EM_HIST + "." + C_LAST_AUTH_USER_ID + ", " );
      query.append( C_TPL_ER_EM_HIST + "." + C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_ER_EM_HIST + "," + C_TPL_CUSTOMER_PRVT_CMPL + ","
                    + C_TPL_CUSTOMER_PRVT + "," + C_TPL_ROLE_CUST );
      query.append( " WHERE " );
      query.append( C_TPL_ER_EM_HIST + "." + C_EM_NBR + "="
                    + C_TPL_CUSTOMER_PRVT_CMPL + "." + C_EM_NBR );
      query.append( " AND " + C_TPL_CUSTOMER_PRVT_CMPL + "." + C_CUST_NBR + "="
                    + C_TPL_CUSTOMER_PRVT + "." + C_CUST_NBR );
      query.append( " AND " + C_TPL_ROLE_CUST + "." + C_ROLE_CUST_CODE + "="
                    + C_TPL_ER_EM_HIST + "." + C_ROLE_CUST_CODE );
      query.append( " AND " + C_TPL_ER_EM_HIST + "." + C_ER_NBR + " = ? " );
      query.append( " AND " + C_TPL_ER_EM_HIST + "." + C_ER_EM_REF_DATE
                    + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString( 1, erEmEntity_.getData().getErNbr() );

      preparedStatement.setTimestamp(
                              2,
                              new Timestamp(
                                             ( ( TplErEmHistEntityVO ) erEmEntity_.getData() ).getErEmRefDate().getTime() ) );

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

    String[] codeColumn = { C_REC_STAT_CODE };
    String[] nameColumn = { C_REC_STAT_TEXT };
    rsds.outerJoin( ODSConstraintDecoder.decodeRecStatus(), codeColumn,
                    codeColumn, nameColumn );

    return rsds;
  }

}