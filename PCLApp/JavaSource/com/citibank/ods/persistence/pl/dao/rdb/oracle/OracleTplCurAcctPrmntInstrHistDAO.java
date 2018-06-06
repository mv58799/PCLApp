package com.citibank.ods.persistence.pl.dao.rdb.oracle;

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
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplCurAcctPrmntInstrEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplCurAcctPrmntInstrHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author michele.monteiro,15/06/2007
 *  
 */

public class OracleTplCurAcctPrmntInstrHistDAO extends
    BaseOracleTplCurAcctPrmntInstrDAO implements TplCurAcctPrmntInstrHistDAO

{

  // Tabela TPL_CUR_ACCT_PRMNT_INSTR_HIST
  private static final String C_TPL_CUR_ACCT_PRMNT_INSTR_HIST = C_PL_SCHEMA
                                                                + "TPL_CUR_ACCT_PRMNT_INSTR_HIST";

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

  //Usuário que aprovou cadastro do registro
  protected String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  //Data/hora de aprovação do cadastro do registro
  protected String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private String C_REC_STAT_CODE = "REC_STAT_CODE";

  private String C_REC_STAT_TEXT = "REC_STAT_TEXT";

  private String C_INDICATOR_TEXT = "INDICATOR_TEXT";

  private String C_INDICATOR_CODE = "INDICATOR_CODE";

  //Data de referência
  protected String C_CUR_ACCT_PRMNT_INSTR_REF_DATE = "CUR_ACCT_PRMNT_INSTR_REF_DATE";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrHistDAO#insert(com.citibank.ods.entity.pl.TplCurAcctPrmntInstrHistEntity)
   */
  public TplCurAcctPrmntInstrHistEntity insert(
                                               TplCurAcctPrmntInstrHistEntity tplCurAcctPrmntInstrHistEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + " (" );
      query.append( C_PRMNT_INSTR_CODE + ", " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_CUR_ACCT_PRMNT_INSTR_REF_DATE + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " ) VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ? , ? , ?) " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplCurAcctPrmntInstrHistEntity_.getData().getPrmntInstrCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCurAcctPrmntInstrHistEntity_.getData().getPrmntInstrCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplCurAcctPrmntInstrHistEntity_.getData().getProdAcctCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCurAcctPrmntInstrHistEntity_.getData().getProdAcctCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplCurAcctPrmntInstrHistEntity_.getData().getProdUnderAcctCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCurAcctPrmntInstrHistEntity_.getData().getProdUnderAcctCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplCurAcctPrmntInstrHistEntity_.getData().getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCurAcctPrmntInstrHistEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplCurAcctPrmntInstrHistEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplCurAcctPrmntInstrHistEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplCurAcctPrmntInstrHistEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplCurAcctPrmntInstrHistEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      //    Casting para Obter os campos especificos da tabela
      TplCurAcctPrmntInstrHistEntityVO tplCurAcctPrmntInstrHistEntityVO = ( TplCurAcctPrmntInstrHistEntityVO ) tplCurAcctPrmntInstrHistEntity_.getData();

      if ( tplCurAcctPrmntInstrHistEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplCurAcctPrmntInstrHistEntityVO.getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplCurAcctPrmntInstrHistEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplCurAcctPrmntInstrHistEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplCurAcctPrmntInstrHistEntityVO.getCurAcctPrmntInstrRefDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplCurAcctPrmntInstrHistEntityVO.getCurAcctPrmntInstrRefDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplCurAcctPrmntInstrHistEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++,
                             tplCurAcctPrmntInstrHistEntityVO.getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplCurAcctPrmntInstrHistEntity_;

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
   * @see com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrHistDAO#list(java.math.BigInteger,
   *      java.math.BigInteger, java.math.BigInteger, java.lang.String)
   */
  public DataSet list( BigInteger curAcctNbr_, BigInteger custNbr_,
                      BigInteger prmntInstrCode_,
                      String prmntInstrInvstCurAcctInd_,
                      Date curAcctPrmntInstrRefDate_, String custFullName_ )
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
      query.append( C_ALIAS_TPL_CUR_ACCT_PRMNT + "."
                    + C_CUR_ACCT_PRMNT_INSTR_REF_DATE );
      query.append( " FROM " + C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + " "
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
      query.append( " AND " + C_ALIAS_TPL_CUR_ACCT_PRMNT + "."
                    + C_REC_STAT_CODE + " != '"
                    + BaseTplCurAcctPrmntInstrEntity.C_REC_STAT_CODE_INACTIVE
                    + "'" );

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

      if ( curAcctPrmntInstrRefDate_ != null )
      {
        criteria = criteria + "TRUNC(" + C_CUR_ACCT_PRMNT_INSTR_REF_DATE
                   + ") >= ? AND ";
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

      if ( curAcctPrmntInstrRefDate_ != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new java.sql.Timestamp(
                                                        curAcctPrmntInstrRefDate_.getTime() ) );
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

    String[] codeColumn = { C_INDICATOR_CODE };
    String[] nameColumn = { C_INDICATOR_TEXT };

    rsds.outerJoin( ODSConstraintDecoder.decodeIndicador(), codeColumn,
                    codeColumn, nameColumn );

    return rsds;

  }

  public ArrayList selectByPK( BigInteger custNbr_, BigInteger prodAcctCode_,
                              BigInteger prodUnderAcctCode_, Date refDate_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList curAcctPrmntInstrHistEntities;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + "." + C_PRMNT_INSTR_CODE
                    + ", " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + "." + C_PROD_ACCT_CODE
                    + ", " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + "."
                    + C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + "."
                    + C_CUR_ACCT_PRMNT_INSTR_REF_DATE + ", " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + "." + C_CUST_NBR + ", " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + "." + C_LAST_UPD_USER_ID
                    + ", " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + "." + C_LAST_UPD_DATE
                    + ", " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + "." + C_LAST_AUTH_USER_ID
                    + ", " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + "." + C_LAST_AUTH_DATE
                    + ", " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + "." + C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + " , " );
      query.append( C_TPL_PRMNT_INSTR_PRVT );

      query.append( " WHERE " + C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + "."
                    + C_PRMNT_INSTR_CODE );
      query.append( " = " + C_TPL_PRMNT_INSTR_PRVT + "." + C_PRMNT_INSTR_CODE );
      query.append( " AND " + C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + "."
                    + C_CUST_NBR );
      query.append( " = " + C_TPL_PRMNT_INSTR_PRVT + "." + C_CUST_NBR );
      query.append( " AND " + C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + "."
                    + C_REC_STAT_CODE );
      query.append( " <> '"
                    + TplCurAcctPrmntInstrHistEntity.C_REC_STAT_CODE_INACTIVE
                    + "'" );
      query.append( " AND " + C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + "."
                    + C_CUST_NBR + " = ? AND " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + "." + C_PROD_ACCT_CODE
                    + " = ? AND " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + "."
                    + C_PROD_UNDER_ACCT_CODE + " = ? AND " );
      query.append( C_TPL_CUR_ACCT_PRMNT_INSTR_HIST + "."
                    + C_CUR_ACCT_PRMNT_INSTR_REF_DATE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( custNbr_ != null && !custNbr_.equals( "" ) )
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

      if ( refDate_ != null )
      {
        preparedStatement.setTimestamp( count++, new Timestamp( refDate_.getTime() ) );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());
      curAcctPrmntInstrHistEntities = instantiateFromResultSet( resultSet );

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

    return curAcctPrmntInstrHistEntities;
  } /*
     * Retorna uma coleção de entities a partir do rs
     */

  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    TplCurAcctPrmntInstrHistEntityVO tplCurAcctPrmntInstrHistEntityVO;
    TplCurAcctPrmntInstrHistEntity tplCurAcctPrmntInstrHistEntity;
    ArrayList tplCurAcctPrmntHistEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplCurAcctPrmntInstrHistEntity = new TplCurAcctPrmntInstrHistEntity();

        if ( resultSet_.getString( C_CUST_NBR ) != null )
        {
          tplCurAcctPrmntInstrHistEntity.getData().setCustNbr(
                                                               new BigInteger(
                                                                               resultSet_.getString( C_CUST_NBR ) ) );

        }

        if ( resultSet_.getString( C_PRMNT_INSTR_CODE ) != null )
        {
          tplCurAcctPrmntInstrHistEntity.getData().setPrmntInstrCode(
                                                                      new BigInteger(
                                                                                      resultSet_.getString( C_PRMNT_INSTR_CODE ) ) );

        }

        if ( resultSet_.getString( C_PROD_ACCT_CODE ) != null )
        {

          tplCurAcctPrmntInstrHistEntity.getData().setProdAcctCode(
                                                                    new BigInteger(
                                                                                    resultSet_.getString( C_PROD_ACCT_CODE ) ) );

        }

        if ( resultSet_.getString( C_PROD_UNDER_ACCT_CODE ) != null )
        {
          tplCurAcctPrmntInstrHistEntity.getData().setProdUnderAcctCode(
                                                                         new BigInteger(
                                                                                         resultSet_.getString( C_PROD_UNDER_ACCT_CODE ) ) );
        }

        tplCurAcctPrmntInstrHistEntity.getData().setLastUpdDate(
                                                                 resultSet_.getTimestamp( C_LAST_UPD_DATE ) );

        tplCurAcctPrmntInstrHistEntity.getData().setLastUpdUserId(
                                                                   resultSet_.getString( C_LAST_UPD_USER_ID ) );

        //Casting para a atribuicao das colunas especificas
        tplCurAcctPrmntInstrHistEntityVO = ( TplCurAcctPrmntInstrHistEntityVO ) tplCurAcctPrmntInstrHistEntity.getData();

        tplCurAcctPrmntInstrHistEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );

        tplCurAcctPrmntInstrHistEntityVO.setLastAuthDate( resultSet_.getTimestamp( C_LAST_AUTH_DATE ) );

        tplCurAcctPrmntInstrHistEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );

        tplCurAcctPrmntInstrHistEntityVO.setCurAcctPrmntInstrRefDate( resultSet_.getTimestamp( C_CUR_ACCT_PRMNT_INSTR_REF_DATE ) );

        tplCurAcctPrmntHistEntities.add( tplCurAcctPrmntInstrHistEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return tplCurAcctPrmntHistEntities;
  }
}