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
import com.citibank.ods.entity.pl.BaseTplCurAcctPrmntInstrEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplCurAcctPrmntInstrMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author michele.monteiro,13/06/2007
 *  
 */

public class OracleTplCurAcctPrmntInstrMovDAO extends
    BaseOracleTplCurAcctPrmntInstrDAO implements TplCurAcctPrmntInstrMovDAO
{

  /*
   * Nome da tabela
   */
  private String C_TPL_CUR_ACCT_PRMNT_INSTR_MOV = C_PL_SCHEMA
                                                  + "TPL_CUR_ACCT_PRMNT_INSTR_MOV";

  private static final String C_TPL_PRMNT_INSTR_PRVT = C_PL_SCHEMA
                                                       + "TPL_PRMNT_INSTR_PRVT";

  private static final String C_TO3_PRODUCT_ACCOUNT = C_O3_SCHEMA
                                                      + "TO3_PRODUCT_ACCOUNT";

  private static final String C_TPL_CUSTOMER_PRVT = C_PL_SCHEMA
                                                    + "TPL_CUSTOMER_PRVT";

  private static final String C_TBG_POINT_ACCT_INVST = C_BG_SCHEMA
                                                       + "TBG_POINT_ACCT_INVST";

  private static final String C_ALIAS_TPL_CUR_ACCT_PRMNT = "CCIP";

  private static final String C_ALIAS_TPL_PRMNT_INSTR_PRVT = "IP";

  private static final String C_ALIAS_TO3_PRODUCT_ACCOUNT = "CC";

  private static final String C_ALIAS_TPL_CUSTOMER = "CUST";

  private static final String C_ALIAS_TBG_POINT_ACCT_INVST = "CCI";

  private static final String C_CUST_FULL_NAME_TEXT = "CUST_FULL_NAME_TEXT";

  private static final String C_CUR_ACCT_NBR = "CUR_ACCT_NBR";

  private static final String C_PRMNT_INSTR_INVST_CUR_ACCT_IND = "PRMNT_INSTR_INVST_CUR_ACCT_IND";

  private static final String C_INVST_CUR_ACCT_NBR = "INVST_CUR_ACCT_NBR";

  private static final String C_PRMNT_INSTR_TEXT = "PRMNT_INSTR_TEXT";

  private static final String C_PROD_CODE = "PROD_CODE";

  private static final String C_IND_YES = "'S'";

  /*
   * Campos específicos da tabela
   */
  private String C_OPERN_CODE = "OPERN_CODE";

  private String C_OPERN_TEXT = "OPERN_TEXT";

  private String C_INDICATOR_TEXT = "INDICATOR_TEXT";

  private String C_INDICATOR_CODE = "INDICATOR_CODE";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrMovDAO#delete(java.lang.String)
   */
  public void delete( BigInteger custNbr_, BigInteger prodCode_,
                     BigInteger prodUnderCode_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "DELETE FROM " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_MOV );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + " = ? AND " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( custNbr_ != null )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( prodCode_ != null )
      {
        preparedStatement.setLong( count++, prodCode_.longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( prodUnderCode_ != null )
      {
        preparedStatement.setLong( count++, prodUnderCode_.longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

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
  
  public void deleteById(BigInteger prmntCode_)
  {
    ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	StringBuffer query = new StringBuffer();

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "DELETE FROM " );
	  query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_MOV );
	  query.append( " WHERE " );
	  query.append( C_PRMNT_INSTR_CODE + " = ? " );	  

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

	  int count = 1;
	  if ( prmntCode_ != null )
	  {
	  preparedStatement.setLong( count++, prmntCode_.longValue() );
	  }
	  else
	  {
	    throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
	  }
		
	  preparedStatement.replaceParametersInQuery(query.toString());
	  preparedStatement.executeUpdate();	

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
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrMovDAO#exists(java.math.BigInteger)
   */
  public boolean exists( BigInteger prodAcctCode_,
                        BigInteger prodUnderAcctCode_, BigInteger custNbr_ )
  {
    boolean exists = true;

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT COUNT(*)" );
      query.append( " FROM " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_MOV );
      query.append( " WHERE " + C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? AND " );
      query.append( C_CUST_NBR + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      if ( prodAcctCode_ != null )
      {
        preparedStatement.setLong( 1, prodAcctCode_.longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( prodUnderAcctCode_ != null )
      {
        preparedStatement.setLong( 2, prodUnderAcctCode_.longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( custNbr_ != null )
      {
        preparedStatement.setLong( 3, custNbr_.longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

	  preparedStatement.replaceParametersInQuery(query.toString());
      resultSet = preparedStatement.executeQuery();	  

      if ( resultSet.next() )
      {
        if ( resultSet.getInt( 1 ) != 0 )
        {
          return true;
        }
        else
        {
          return false;
        }
      }
      else
      {
        return false;
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
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrMovDAO#existsRelation(java.math.BigInteger,
   *      java.math.BigInteger, java.math.BigInteger)
   */
  public boolean existsRelation( BigInteger prmntInstrCode_,
                                BigInteger prodAcctCode_,
                                BigInteger prodUnderAcctCode_ )
  {
    boolean exists = true;

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT COUNT(*)" );
      query.append( " FROM " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_MOV );
      query.append( "WHERE " + C_PRMNT_INSTR_CODE + " = ? AND " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setLong( count++, prmntInstrCode_.longValue() );
      preparedStatement.setLong( count++, prodAcctCode_.longValue() );
      preparedStatement.setLong( count++, prodUnderAcctCode_.longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.getInt( 0 ) != 0 )
      {
        return true;
      }
      else
      {
        return false;
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
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrMovDAO#insert(com.citibank.ods.entity.pl.TplCurAcctPrmntInstrMovEntity)
   */
  public TplCurAcctPrmntInstrMovEntity insert(
                                              TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntity_ )
  {

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_CUR_ACCT_PRMNT_INSTR_MOV + " (" );
      query.append( C_PRMNT_INSTR_CODE + ", " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_OPERN_CODE );
      query.append( " ) VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ? ) " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplCurAcctPrmntInstrMovEntity_.getData().getPrmntInstrCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCurAcctPrmntInstrMovEntity_.getData().getPrmntInstrCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplCurAcctPrmntInstrMovEntity_.getData().getProdAcctCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCurAcctPrmntInstrMovEntity_.getData().getProdAcctCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplCurAcctPrmntInstrMovEntity_.getData().getProdUnderAcctCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCurAcctPrmntInstrMovEntity_.getData().getProdUnderAcctCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplCurAcctPrmntInstrMovEntity_.getData().getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCurAcctPrmntInstrMovEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplCurAcctPrmntInstrMovEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplCurAcctPrmntInstrMovEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }
      if ( tplCurAcctPrmntInstrMovEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplCurAcctPrmntInstrMovEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      //    Casting para Obter os campos especificos da tabela

      TplCurAcctPrmntInstrMovEntityVO tplCurAcctPrmntInstrEntityVO = ( TplCurAcctPrmntInstrMovEntityVO ) tplCurAcctPrmntInstrMovEntity_.getData();
      if ( tplCurAcctPrmntInstrEntityVO.getOpernCode() != null )
      {
        preparedStatement.setString( count++,
                             tplCurAcctPrmntInstrEntityVO.getOpernCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplCurAcctPrmntInstrMovEntity_;
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

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrMovDAO#list(java.math.BigInteger,
   *      java.math.BigInteger, java.lang.String, java.lang.String)
   */
  public DataSet list( BigInteger curAcctNbr_, BigInteger custNbr_,
                      BigInteger prmntInstrCode_,
                      String prmntInstrInvstCurAcctInd_, String lastUpdUserId_,
                      String custFullName_ )
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
      query.append( C_ALIAS_TPL_CUSTOMER + "." + C_CUST_FULL_NAME_TEXT + ", " );
      query.append( C_ALIAS_TPL_CUSTOMER + "." + C_CUST_NBR + ", " );
      query.append( C_ALIAS_TO3_PRODUCT_ACCOUNT + "." + C_CUR_ACCT_NBR + ", " );
      query.append( " ( CASE WHEN " + C_ALIAS_TPL_PRMNT_INSTR_PRVT + "."
                    + C_PRMNT_INSTR_INVST_CUR_ACCT_IND + " = " + C_IND_YES );
      query.append( " THEN " + C_ALIAS_TBG_POINT_ACCT_INVST + "."
                    + C_INVST_CUR_ACCT_NBR );
      query.append( " ELSE '' END )AS " + C_INVST_CUR_ACCT_NBR + ", " );
      query.append( C_ALIAS_TPL_PRMNT_INSTR_PRVT + "."
                    + C_PRMNT_INSTR_INVST_CUR_ACCT_IND + " AS "
                    + C_INDICATOR_CODE + ", " );
      query.append( C_ALIAS_TPL_PRMNT_INSTR_PRVT + "." + C_PRMNT_INSTR_CODE
                    + ", " );
      query.append( C_ALIAS_TPL_PRMNT_INSTR_PRVT + "." + C_PRMNT_INSTR_TEXT
                    + ", " );
      query.append( C_ALIAS_TPL_CUR_ACCT_PRMNT + "." + C_PROD_ACCT_CODE + ", " );
      query.append( C_ALIAS_TPL_CUR_ACCT_PRMNT + "." + C_PROD_UNDER_ACCT_CODE
                    + ", " );
      query.append( C_ALIAS_TPL_CUR_ACCT_PRMNT + "." + C_LAST_UPD_USER_ID
                    + ", " );
      query.append( C_ALIAS_TPL_CUR_ACCT_PRMNT + "." + C_OPERN_CODE );
      query.append( " FROM " + C_TPL_CUR_ACCT_PRMNT_INSTR_MOV + " "
                    + C_ALIAS_TPL_CUR_ACCT_PRMNT + ", " );
      query.append( C_TPL_PRMNT_INSTR_PRVT + " " + C_ALIAS_TPL_PRMNT_INSTR_PRVT
                    + ", " );
      query.append( C_TO3_PRODUCT_ACCOUNT + " " + C_ALIAS_TO3_PRODUCT_ACCOUNT
                    + ", " );
      query.append( C_TPL_CUSTOMER_PRVT + " " + C_ALIAS_TPL_CUSTOMER + ", " );
      query.append( C_TBG_POINT_ACCT_INVST + " " + C_ALIAS_TBG_POINT_ACCT_INVST );
      query.append( " WHERE " + C_ALIAS_TPL_CUR_ACCT_PRMNT + "." + C_CUST_NBR
                    + " = " );
      query.append( C_ALIAS_TPL_PRMNT_INSTR_PRVT + "." + C_CUST_NBR + " AND " );
      query.append( C_ALIAS_TPL_CUR_ACCT_PRMNT + "." + C_PRMNT_INSTR_CODE
                    + " = " );
      query.append( C_ALIAS_TPL_PRMNT_INSTR_PRVT + "." + C_PRMNT_INSTR_CODE
                    + " AND " );
      query.append( C_ALIAS_TPL_CUR_ACCT_PRMNT + "." + C_PROD_ACCT_CODE + " = " );
      query.append( C_ALIAS_TO3_PRODUCT_ACCOUNT + "." + C_PROD_ACCT_CODE
                    + " AND " );
      query.append( C_ALIAS_TPL_CUR_ACCT_PRMNT + "." + C_PROD_UNDER_ACCT_CODE
                    + " = " );
      query.append( C_ALIAS_TO3_PRODUCT_ACCOUNT + "." + C_PROD_UNDER_ACCT_CODE
                    + " AND " );
      query.append( C_ALIAS_TPL_CUSTOMER + "." + C_CUST_NBR + " = " );
      query.append( C_ALIAS_TPL_CUR_ACCT_PRMNT + "." + C_CUST_NBR + " AND " );
      query.append( C_ALIAS_TO3_PRODUCT_ACCOUNT + "." + C_CUR_ACCT_NBR + " = " );
      query.append( C_ALIAS_TBG_POINT_ACCT_INVST + "." + C_CUR_ACCT_NBR
                    + " (+) " );

      String criteria = "";

      if ( custNbr_ != null && custNbr_.longValue() != 0 )
      {
        criteria = criteria + C_ALIAS_TPL_CUR_ACCT_PRMNT + "." + C_CUST_NBR
                   + " = ? AND ";
      }

      if ( prmntInstrCode_ != null && prmntInstrCode_.longValue() != 0 )
      {
        criteria = criteria + C_ALIAS_TPL_CUR_ACCT_PRMNT + "."
                   + C_PRMNT_INSTR_CODE + " = ? AND ";
      }

      if ( prmntInstrInvstCurAcctInd_ != null
           && !prmntInstrInvstCurAcctInd_.equals( "" ) )
      {
        criteria = criteria + "UPPER(" + C_ALIAS_TPL_PRMNT_INSTR_PRVT + "."
                   + C_PRMNT_INSTR_INVST_CUR_ACCT_IND + ") = ? AND ";
      }

      if ( curAcctNbr_ != null && curAcctNbr_.longValue() != 0 )
      {
        criteria = criteria + "( " + C_ALIAS_TO3_PRODUCT_ACCOUNT + "."
                   + C_CUR_ACCT_NBR + " = ?";
        criteria = criteria + " OR (" + C_ALIAS_TBG_POINT_ACCT_INVST + "."
                   + C_INVST_CUR_ACCT_NBR + " = ? AND ";
        criteria = criteria + C_ALIAS_TPL_PRMNT_INSTR_PRVT + "."
                   + C_PRMNT_INSTR_INVST_CUR_ACCT_IND + " = 'S')) AND ";

      }

      if ( lastUpdUserId_ != null && !lastUpdUserId_.equals( "" ) )
      {
        criteria = criteria + "UPPER(" + C_ALIAS_TPL_CUR_ACCT_PRMNT + "."
                   + C_LAST_UPD_USER_ID + ") = ? AND ";
      }

      if ( custFullName_ != null && !custFullName_.equals( "" ) )
      {
        criteria = criteria + "UPPER(" + C_ALIAS_TPL_CUSTOMER + "."
                   + C_CUST_FULL_NAME_TEXT + ") like ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( " AND " + criteria );
      }

      query.append( " ORDER BY " + C_ALIAS_TPL_CUSTOMER + "."
                    + C_CUST_FULL_NAME_TEXT + "," );
      query.append( C_ALIAS_TO3_PRODUCT_ACCOUNT + "." + C_CUR_ACCT_NBR + "," );
      query.append( C_ALIAS_TPL_PRMNT_INSTR_PRVT + "." + C_PRMNT_INSTR_CODE );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( custNbr_ != null && custNbr_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
      }

      if ( prmntInstrCode_ != null && prmntInstrCode_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, prmntInstrCode_.longValue() );
      }

      if ( prmntInstrInvstCurAcctInd_ != null
           && !prmntInstrInvstCurAcctInd_.equals( "" ) )
      {
        preparedStatement.setString( count++, prmntInstrInvstCurAcctInd_.toUpperCase() );
      }

      if ( curAcctNbr_ != null && curAcctNbr_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, curAcctNbr_.longValue() );
        preparedStatement.setLong( count++, curAcctNbr_.longValue() );
      }

      if ( lastUpdUserId_ != null && lastUpdUserId_.length() != 0 )
      {
        preparedStatement.setString( count++, lastUpdUserId_.toUpperCase() );
      }
      if ( custFullName_ != null && !custFullName_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + custFullName_.toUpperCase() + "%" );

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

    String[] codeColumn = { C_OPERN_CODE };
    String[] nameColumn = { C_OPERN_TEXT };
    rsds.outerJoin( ODSConstraintDecoder.decodeOpern(), codeColumn, codeColumn,
                    nameColumn );

    String[] codeColumnIndicator = { C_INDICATOR_CODE };
    String[] nameColumnIndicator = { C_INDICATOR_TEXT };

    rsds.outerJoin( ODSConstraintDecoder.decodeIndicador(),
                    codeColumnIndicator, codeColumnIndicator,
                    nameColumnIndicator );

    return rsds;

  }

  public ArrayList selectByPK( BigInteger custNbr_, BigInteger prodAcctCode_,
                              BigInteger prodUnderAcctCode_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList curAcctPrmntInstrMovEntities;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_MOV + "." + C_PRMNT_INSTR_CODE
                    + ", " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_MOV + "." + C_PROD_ACCT_CODE
                    + ", " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_MOV + "."
                    + C_PROD_UNDER_ACCT_CODE + ", " );

      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_MOV + "." + C_CUST_NBR + ", " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_MOV + "." + C_LAST_UPD_USER_ID
                    + ", " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_MOV + "." + C_LAST_UPD_DATE
                    + ", " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_MOV + "." + C_OPERN_CODE );
      query.append( " FROM " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_MOV + " , " );
      query.append( C_TPL_PRMNT_INSTR_PRVT );

      query.append( " WHERE " + C_TPL_CUR_ACCT_PRMNT_INSTR_MOV + "."
                    + C_PRMNT_INSTR_CODE );
      query.append( " = " + C_TPL_PRMNT_INSTR_PRVT + "." + C_PRMNT_INSTR_CODE );
      query.append( " AND " + C_TPL_CUR_ACCT_PRMNT_INSTR_MOV + "." + C_CUST_NBR );
      query.append( " = " + C_TPL_PRMNT_INSTR_PRVT + "." + C_CUST_NBR );
      query.append( " AND " + C_TPL_CUR_ACCT_PRMNT_INSTR_MOV + "." + C_CUST_NBR
                    + " = ? AND " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_MOV + "." + C_PROD_ACCT_CODE
                    + " = ? AND " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_MOV + "."
                    + C_PROD_UNDER_ACCT_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( custNbr_ != null )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( prodAcctCode_ != null )
      {
        preparedStatement.setLong( count++, prodAcctCode_.longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( prodUnderAcctCode_ != null )
      {
        preparedStatement.setLong( count++, prodUnderAcctCode_.longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());
      curAcctPrmntInstrMovEntities = instantiateFromResultSet( resultSet );

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

    return curAcctPrmntInstrMovEntities;
  }

  /*
   * Retorna uma coleção de entities a partir do rs
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    TplCurAcctPrmntInstrMovEntityVO tplCurAcctPrmntInstrMovEntityVO;
    TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntity;
    ArrayList tplCurAcctPrmntMovEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplCurAcctPrmntInstrMovEntity = new TplCurAcctPrmntInstrMovEntity();

        if ( resultSet_.getString( C_CUST_NBR ) != null )
        {
          tplCurAcctPrmntInstrMovEntity.getData().setCustNbr(
                                                              new BigInteger(
                                                                              resultSet_.getString( C_CUST_NBR ) ) );

        }

        if ( resultSet_.getString( C_PRMNT_INSTR_CODE ) != null )
        {
          tplCurAcctPrmntInstrMovEntity.getData().setPrmntInstrCode(
                                                                     new BigInteger(
                                                                                     resultSet_.getString( C_PRMNT_INSTR_CODE ) ) );

        }

        if ( resultSet_.getString( C_PROD_ACCT_CODE ) != null )
        {

          tplCurAcctPrmntInstrMovEntity.getData().setProdAcctCode(
                                                                   new BigInteger(
                                                                                   resultSet_.getString( C_PROD_ACCT_CODE ) ) );

        }

        if ( resultSet_.getString( C_PROD_UNDER_ACCT_CODE ) != null )
        {
          tplCurAcctPrmntInstrMovEntity.getData().setProdUnderAcctCode(
                                                                        new BigInteger(
                                                                                        resultSet_.getString( C_PROD_UNDER_ACCT_CODE ) ) );
        }

        tplCurAcctPrmntInstrMovEntity.getData().setLastUpdDate(
                                                                resultSet_.getTimestamp( C_LAST_UPD_DATE ) );

        tplCurAcctPrmntInstrMovEntity.getData().setLastUpdUserId(
                                                                  resultSet_.getString( C_LAST_UPD_USER_ID ) );

        //Casting para a atribuicao das colunas especificas
        tplCurAcctPrmntInstrMovEntityVO = ( TplCurAcctPrmntInstrMovEntityVO ) tplCurAcctPrmntInstrMovEntity.getData();

        tplCurAcctPrmntInstrMovEntityVO.setOpernCode( resultSet_.getString( C_OPERN_CODE ) );

        tplCurAcctPrmntMovEntities.add( tplCurAcctPrmntInstrMovEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    
    return tplCurAcctPrmntMovEntities;
  }
  
  /*
	 * (non-Javadoc)
	 * 
	 * @see com.citibank.ods.persistence.pl.dao.BaseTplIpDocPrvtDAO#find(com.citibank.ods.entity.pl.BaseTplIpDocPrvtEntity)
	 */
	public BaseTplCurAcctPrmntInstrEntity findById(BigInteger prmntCode_ )
	{
	  ManagedRdbConnection connection = null;
	  CitiStatement preparedStatement = null;
	  ResultSet resultSet = null;
	  StringBuffer query = new StringBuffer();

	  ArrayList tplCurAcctPrmntMovEntities;
	  BaseTplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity = null;

	  try
	  {
		connection = OracleODSDAOFactory.getConnection();
		query.append( "SELECT " );		
	    query.append(C_PRMNT_INSTR_CODE	+ ", " );
	    query.append(C_PROD_ACCT_CODE	+ ", " );
	    query.append(C_PROD_UNDER_ACCT_CODE + ", " );
	    query.append(C_CUST_NBR + ", " );
	    query.append(C_LAST_UPD_USER_ID	+ ", " );
	    query.append(C_LAST_UPD_DATE + ", " );
	    query.append(C_OPERN_CODE );
	    query.append( " FROM " );
	    query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_MOV );
	    query.append( " WHERE ");
	    query.append( C_PRMNT_INSTR_CODE + " = ? " );	    


		int count = 1;
		preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

		preparedStatement.setLong(count++,prmntCode_.longValue() );
		
		preparedStatement.replaceParametersInQuery(query.toString());
		resultSet = preparedStatement.executeQuery();		

		tplCurAcctPrmntMovEntities = instantiateFromResultSet( resultSet );

		if ( tplCurAcctPrmntMovEntities.size() == 0 )
		{
		  throw new NoRowsReturnedException();
		}
		else if ( tplCurAcctPrmntMovEntities.size() > 1 )
		{
		  throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
		}
		else
		{
			tplCurAcctPrmntInstrEntity = ( BaseTplCurAcctPrmntInstrEntity ) tplCurAcctPrmntMovEntities.get( 0 );
		}

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

	  return tplCurAcctPrmntInstrEntity;
	}

  
  
  
}