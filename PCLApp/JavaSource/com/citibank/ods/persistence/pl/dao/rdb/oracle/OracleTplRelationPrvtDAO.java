package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplRelationPrvtEntity;
import com.citibank.ods.entity.pl.TplRelationPrvtEntity;
import com.citibank.ods.persistence.pl.dao.TplRelationPrvtDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 29, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class OracleTplRelationPrvtDAO extends BaseOracleTplRelationPrvtDAO
    implements TplRelationPrvtDAO
{

  public String C_TPL_RELATION_PRVT = C_PL_SCHEMA + "TPL_RELATION_PRVT";

  public String C_TPL_CUSTOMER_PRVT = C_PL_SCHEMA + "TPL_CUSTOMER_PRVT";

  public String C_TPL_CUSTOMER_PRVT_ALIAS = "CUST";

  public String C_RELATION_PRVT_ALIAS = "RELTN";

  public static String C_CUR_ACCT_ALIAS = "CUR_ACCT";

  public String C_TPL_CUSTOMER_PRVT_ALIAS_1 = "TPL_CUSTOMER_PRVT_1";

  public String C_TPL_CUSTOMER_PRVT_ALIAS_2 = "TPL_CUSTOMER_PRVT_2";

  public String C_TPL_CUSTOMER_PRVT_ALIAS_3 = "TPL_CUSTOMER_PRVT_3";

  public String C_TPL_CUSTOMER_PRVT_ALIAS_4 = "TPL_CUSTOMER_PRVT_4";

  public String C_TPL_CUSTOMER_PRVT_ALIAS_5 = "TPL_CUSTOMER_PRVT_5";

  public String C_CUST_FULL_NAME_TEXT_1 = "CUST_FULL_NAME_TEXT_1";

  public String C_CUST_FULL_NAME_TEXT = "CUST_FULL_NAME_TEXT";

  public String C_CUST_FULL_NAME_TEXT_2 = "CUST_FULL_NAME_TEXT_2";

  public String C_TO3_PRODUCT_ACCOUNT = C_O3_SCHEMA + "TO3_PRODUCT_ACCOUNT";

  public String C_TO3_PRODUCT_ACCOUNT_ALIAS_1 = C_O3_SCHEMA
                                                + "TO3_PRODUCT_ACCOUNT_1";

  public String C_TO3_PRODUCT_ACCOUNT_ALIAS_2 = C_O3_SCHEMA
                                                + "TO3_PRODUCT_ACCOUNT_2";

  public String C_TO3_PRODUCT_ACCOUNT_ALIAS_3 = C_O3_SCHEMA
                                                + "TO3_PRODUCT_ACCOUNT_3";

  public String C_TO3_PRODUCT_ACCOUNT_ALIAS_4 = C_O3_SCHEMA
                                                + "TO3_PRODUCT_ACCOUNT_4";

  public String C_TO3_PRODUCT_ACCOUNT_ALIAS_5 = C_O3_SCHEMA
                                                + "TO3_PRODUCT_ACCOUNT_5";

  public String C_PRODUCT_ACCOUNT_RELTN_NBR = "RELTN_NBR";

  public String C_PRODUCT_ACCOUNT_CUST_NBR = "CUST_NBR";

  public String C_CUR_ACCT_NBR = "CUR_ACCT_NBR";

  public String C_CUST_CPF_CNPJ_NBR_1 = "CUST_CPF_CNPJ_NBR_1";

  /* campos */
  public static String C_CUST_NBR = "CUST_NBR";

  public static String C_PROD_CODE = "PROD_CODE";

  public static String C_PROD_CODE_ACCT = "'010'";

  public static String C_SYS_SEG_CODE = "SYS_SEG_CODE";

  public static String C_SYS_SEG_CODE_ACCT = "1";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplRelationPrvtDAO#list()
   */
  public DataSet list( BigInteger reltnNbr_, BigInteger reltnCust1Nbr_,
                      String custFullNameText_, String ownerSelected_,
                      BigInteger acctNbr_, BigInteger custCpfCnpjNbr_ )
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
      query.append( C_TPL_CUSTOMER_PRVT_ALIAS + "."
                    + BaseOracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT + ", " );
      query.append( C_RELATION_PRVT_ALIAS + "." + C_RELTN_CUST_1_NBR + ", " );
      query.append( C_TPL_CUSTOMER_PRVT_ALIAS + "."
                    + BaseOracleTplCustomerPrvtDAO.C_CUST_CPF_CNPJ_NBR + " AS "
                    + C_CUST_CPF_CNPJ_NBR_1 + ", " );
      query.append( C_RELATION_PRVT_ALIAS + "." + C_RELTN_NBR + ", " );
      query.append( C_RELATION_PRVT_ALIAS + "." + C_RELTN_RISK_LEVEL_CODE
                    + ", " );
      query.append( C_CUR_ACCT_ALIAS + "." + C_CUR_ACCT_NBR );
      query.append( " FROM " );
      query.append( C_TPL_RELATION_PRVT + " " + C_RELATION_PRVT_ALIAS + ", " );
      query.append( C_TPL_CUSTOMER_PRVT + " " + C_TPL_CUSTOMER_PRVT_ALIAS
                    + ", " );
      query.append( " ( SELECT " + C_CUST_NBR + ", " + C_RELTN_NBR + ", "
                    + C_CUR_ACCT_NBR + ", " + C_PROD_CODE + ", "
                    + C_SYS_SEG_CODE );
      query.append( " FROM " + C_TO3_PRODUCT_ACCOUNT );
      query.append( " WHERE " + C_PROD_CODE + " = " + C_PROD_CODE_ACCT );
      query.append( " AND " + C_SYS_SEG_CODE + " = " + C_SYS_SEG_CODE_ACCT
                    + " ) " + C_CUR_ACCT_ALIAS );
      query.append( " WHERE " + C_RELATION_PRVT_ALIAS + "."
                    + C_RELTN_CUST_1_NBR + " = " );
      query.append( C_TPL_CUSTOMER_PRVT_ALIAS + "." + C_CUST_NBR );
      query.append( " AND " + C_RELATION_PRVT_ALIAS + "." + C_RELTN_NBR + " = " );
      query.append( C_CUR_ACCT_ALIAS + "." + C_RELTN_NBR + "(+)" );

      String criteria = "";
      String critCust = "";

      if ( custFullNameText_ != null && custFullNameText_.length() > 0 )
      {

        critCust = critCust + "(SELECT " + C_CUST_NBR + " FROM "
                   + C_TPL_CUSTOMER_PRVT + " WHERE UPPER("
                   + C_CUST_FULL_NAME_TEXT + ") LIKE '%"
                   + custFullNameText_.toUpperCase() + "%')";

      }

      if ( reltnNbr_ != null && reltnNbr_.longValue() != 0 )
      {
        criteria = criteria + " (" + C_RELATION_PRVT_ALIAS + "." + C_RELTN_NBR
                   + " = ? ) AND ";
      }
      else
      {
        if ( reltnCust1Nbr_ != null && reltnCust1Nbr_.longValue() != 0 )
        {
          if ( ownerSelected_ != null
               && ownerSelected_.equals( com.citibank.ods.Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_NAO ) )
          {
            criteria = criteria + " (" + C_RELATION_PRVT_ALIAS + "."
                       + C_RELTN_CUST_1_NBR + " = ? OR ";
            criteria = criteria + C_RELATION_PRVT_ALIAS + "."
                       + C_RELTN_CUST_2_NBR + " = ? OR ";
            criteria = criteria + C_RELATION_PRVT_ALIAS + "."
                       + C_RELTN_CUST_3_NBR + " = ? OR ";
            criteria = criteria + C_RELATION_PRVT_ALIAS + "."
                       + C_RELTN_CUST_4_NBR + " = ? OR ";
            criteria = criteria + C_RELATION_PRVT_ALIAS + "."
                       + C_RELTN_CUST_5_NBR + " = ? ) AND ";
          }
          else
          {
            criteria = criteria + " (" + C_RELATION_PRVT_ALIAS + "."
                       + C_RELTN_CUST_1_NBR + " = ? ) AND ";
          }
        }

      }
      if ( acctNbr_ != null && acctNbr_.longValue() != 0 )
      {
        criteria = criteria + " (" + C_CUR_ACCT_ALIAS + "." + C_CUR_ACCT_NBR
                   + " = ? ) AND ";
      }

      if ( custFullNameText_ != null && custFullNameText_.length() > 0 )
      {
        if ( ownerSelected_ != null
             && ownerSelected_.equals( com.citibank.ods.Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_NAO ) )
        {
          criteria = criteria + " (" + C_RELATION_PRVT_ALIAS + "."
                     + C_RELTN_CUST_1_NBR + " IN " + critCust + " OR ";
          criteria = criteria + C_RELATION_PRVT_ALIAS + "."
                     + C_RELTN_CUST_2_NBR + " IN " + critCust + " OR ";
          criteria = criteria + C_RELATION_PRVT_ALIAS + "."
                     + C_RELTN_CUST_3_NBR + " IN " + critCust + " OR ";
          criteria = criteria + C_RELATION_PRVT_ALIAS + "."
                     + C_RELTN_CUST_4_NBR + " IN " + critCust + " OR ";
          criteria = criteria + C_RELATION_PRVT_ALIAS + "."
                     + C_RELTN_CUST_5_NBR + " IN " + critCust + " ) AND ";
        }
        else
        {
          criteria = criteria + " (" + C_RELATION_PRVT_ALIAS + "."
                     + C_RELTN_CUST_1_NBR + " IN " + critCust + ") AND ";
        }
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( " AND " + criteria );
      }

      query.append( " ORDER BY "
                    + BaseOracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( reltnNbr_ != null && reltnNbr_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, reltnNbr_.longValue() );
      }
      else
      {
        if ( reltnCust1Nbr_ != null && reltnCust1Nbr_.longValue() != 0 )
        {
          if ( ownerSelected_ != null
               && ownerSelected_.equals( com.citibank.ods.Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_NAO ) )
          {
            preparedStatement.setLong( count++, reltnCust1Nbr_.longValue() );
            preparedStatement.setLong( count++, reltnCust1Nbr_.longValue() );
            preparedStatement.setLong( count++, reltnCust1Nbr_.longValue() );
            preparedStatement.setLong( count++, reltnCust1Nbr_.longValue() );
            preparedStatement.setLong( count++, reltnCust1Nbr_.longValue() );
          }
          else
          {
            preparedStatement.setLong( count++, reltnCust1Nbr_.longValue() );
          }
        }

      }

      if ( acctNbr_ != null && acctNbr_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, acctNbr_.longValue() );

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

  /*
   * public DataSet list( BigInteger reltnNbr_, BigInteger reltnCust1Nbr_,
   * String custFullNameText_, String ownerSelected_, BigInteger acctNbr_,
   * BigInteger custCpfCnpjNbr_ ) { ManagedRdbConnection connection = null;
   * CitiStatement preparedStatement = null; ResultSet resultSet = null;
   * ResultSetDataSet rsds = null; StringBuffer query = new StringBuffer();
   * 
   * try { connection = OracleODSDAOFactory.getConnection(); query.append(
   * "SELECT " ); query.append( C_RELATION_PRVT_ALIAS_1 + "." + C_RELTN_NBR + ", " );
   * query.append( C_RELATION_PRVT_ALIAS_1 + "." + C_RELTN_CUST_1_NBR + ", " );
   * query.append( C_TPL_CUSTOMER_PRVT_ALIAS_1 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_CPF_CNPJ_NBR + " AS " +
   * C_CUST_CPF_CNPJ_NBR_1 + ", " ); query.append( C_TPL_CUSTOMER_PRVT_ALIAS_1 +
   * "." + BaseOracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT + " AS " +
   * C_CUST_FULL_NAME_TEXT_1 + ", " ); query.append( C_TPL_CUSTOMER_PRVT_ALIAS_2 +
   * "." + BaseOracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT + " AS " +
   * C_CUST_FULL_NAME_TEXT_2 + ", " ); query.append( C_RELATION_PRVT_ALIAS_1 +
   * "." + C_RELTN_RISK_LEVEL_CODE ); query.append( " FROM " ); query.append(
   * C_TPL_RELATION_PRVT + " " + C_RELATION_PRVT_ALIAS_1 );
   * 
   * //se filtrar por todos os clientes do relacionamento if ( ownerSelected_ !=
   * null && ownerSelected_.equals(
   * com.citibank.ods.Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_NAO ) ) {
   * query.append( " INNER JOIN " + C_TPL_CUSTOMER_PRVT + " " +
   * C_TPL_CUSTOMER_PRVT_ALIAS_1 + " ON " + C_RELATION_PRVT_ALIAS_1 + "." +
   * C_RELTN_CUST_1_NBR + " = " + C_TPL_CUSTOMER_PRVT_ALIAS_1 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_NBR ); query.append( " LEFT OUTER JOIN " +
   * C_TPL_CUSTOMER_PRVT + " " + C_TPL_CUSTOMER_PRVT_ALIAS_2 + " ON " +
   * C_RELATION_PRVT_ALIAS_1 + "." + C_RELTN_CUST_2_NBR + " = " +
   * C_TPL_CUSTOMER_PRVT_ALIAS_2 + "." + BaseOracleTplCustomerPrvtDAO.C_CUST_NBR );
   * query.append( " LEFT OUTER JOIN " + C_TPL_CUSTOMER_PRVT + " " +
   * C_TPL_CUSTOMER_PRVT_ALIAS_3 + " ON " + C_RELATION_PRVT_ALIAS_1 + "." +
   * C_RELTN_CUST_3_NBR + " = " + C_TPL_CUSTOMER_PRVT_ALIAS_3 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_NBR ); query.append( " LEFT OUTER JOIN " +
   * C_TPL_CUSTOMER_PRVT + " " + C_TPL_CUSTOMER_PRVT_ALIAS_4 + " ON " +
   * C_RELATION_PRVT_ALIAS_1 + "." + C_RELTN_CUST_4_NBR + " = " +
   * C_TPL_CUSTOMER_PRVT_ALIAS_4 + "." + BaseOracleTplCustomerPrvtDAO.C_CUST_NBR );
   * query.append( " LEFT OUTER JOIN " + C_TPL_CUSTOMER_PRVT + " " +
   * C_TPL_CUSTOMER_PRVT_ALIAS_5 + " ON " + C_RELATION_PRVT_ALIAS_1 + "." +
   * C_RELTN_CUST_5_NBR + " = " + C_TPL_CUSTOMER_PRVT_ALIAS_5 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_NBR );
   * 
   * //se o filtro por conta corrente foi acionado if ( acctNbr_ != null &&
   * acctNbr_.longValue() > 0 ) { query.append( " LEFT OUTER JOIN " +
   * C_TO3_PRODUCT_ACCOUNT + " " + C_TO3_PRODUCT_ACCOUNT_ALIAS_1 + " ON " +
   * C_RELATION_PRVT_ALIAS_1 + "." + C_RELTN_NBR + " = " +
   * C_TO3_PRODUCT_ACCOUNT_ALIAS_1 + "." + C_PRODUCT_ACCOUNT_RELTN_NBR + " AND " +
   * C_RELATION_PRVT_ALIAS_1 + "." + C_RELTN_CUST_1_NBR + " = " +
   * C_TO3_PRODUCT_ACCOUNT_ALIAS_1 + "." + C_PRODUCT_ACCOUNT_CUST_NBR );
   * query.append( " LEFT OUTER JOIN " + C_TO3_PRODUCT_ACCOUNT + " " +
   * C_TO3_PRODUCT_ACCOUNT_ALIAS_2 + " ON " + C_RELATION_PRVT_ALIAS_1 + "." +
   * C_RELTN_NBR + " = " + C_TO3_PRODUCT_ACCOUNT_ALIAS_2 + "." +
   * C_PRODUCT_ACCOUNT_RELTN_NBR + " AND " + C_RELATION_PRVT_ALIAS_1 + "." +
   * C_RELTN_CUST_2_NBR + " = " + C_TO3_PRODUCT_ACCOUNT_ALIAS_2 + "." +
   * C_PRODUCT_ACCOUNT_CUST_NBR ); query.append( " LEFT OUTER JOIN " +
   * C_TO3_PRODUCT_ACCOUNT + " " + C_TO3_PRODUCT_ACCOUNT_ALIAS_3 + " ON " +
   * C_RELATION_PRVT_ALIAS_1 + "." + C_RELTN_NBR + " = " +
   * C_TO3_PRODUCT_ACCOUNT_ALIAS_3 + "." + C_PRODUCT_ACCOUNT_RELTN_NBR + " AND " +
   * C_RELATION_PRVT_ALIAS_1 + "." + C_RELTN_CUST_3_NBR + " = " +
   * C_TO3_PRODUCT_ACCOUNT_ALIAS_3 + "." + C_PRODUCT_ACCOUNT_CUST_NBR );
   * query.append( " LEFT OUTER JOIN " + C_TO3_PRODUCT_ACCOUNT + " " +
   * C_TO3_PRODUCT_ACCOUNT_ALIAS_4 + " ON " + C_RELATION_PRVT_ALIAS_1 + "." +
   * C_RELTN_NBR + " = " + C_TO3_PRODUCT_ACCOUNT_ALIAS_4 + "." +
   * C_PRODUCT_ACCOUNT_RELTN_NBR + " AND " + C_RELATION_PRVT_ALIAS_1 + "." +
   * C_RELTN_CUST_4_NBR + " = " + C_TO3_PRODUCT_ACCOUNT_ALIAS_4 + "." +
   * C_PRODUCT_ACCOUNT_CUST_NBR ); query.append( " LEFT OUTER JOIN " +
   * C_TO3_PRODUCT_ACCOUNT + " " + C_TO3_PRODUCT_ACCOUNT_ALIAS_5 + " ON " +
   * C_RELATION_PRVT_ALIAS_1 + "." + C_RELTN_NBR + " = " +
   * C_TO3_PRODUCT_ACCOUNT_ALIAS_5 + "." + C_PRODUCT_ACCOUNT_RELTN_NBR + " AND " +
   * C_RELATION_PRVT_ALIAS_1 + "." + C_RELTN_CUST_5_NBR + " = " +
   * C_TO3_PRODUCT_ACCOUNT_ALIAS_5 + "." + C_PRODUCT_ACCOUNT_CUST_NBR ); } }
   * //se os critérios de busca de clientes forem apenas referentes ao owner //
   * do relacionamento else { query.append( " INNER JOIN " + C_TPL_CUSTOMER_PRVT + " " +
   * C_TPL_CUSTOMER_PRVT_ALIAS_1 + " ON " + C_RELATION_PRVT_ALIAS_1 + "." +
   * C_RELTN_CUST_1_NBR + " = " + C_TPL_CUSTOMER_PRVT_ALIAS_1 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_NBR ); query.append( " LEFT OUTER JOIN " +
   * C_TPL_CUSTOMER_PRVT + " " + C_TPL_CUSTOMER_PRVT_ALIAS_2 + " ON " +
   * C_RELATION_PRVT_ALIAS_1 + "." + C_RELTN_CUST_2_NBR + " = " +
   * C_TPL_CUSTOMER_PRVT_ALIAS_2 + "." + BaseOracleTplCustomerPrvtDAO.C_CUST_NBR );
   * 
   * //se o filtro por conta corrente foi acionado if ( acctNbr_ != null &&
   * acctNbr_.longValue() > 0 ) { query.append( " LEFT OUTER JOIN " +
   * C_TO3_PRODUCT_ACCOUNT + " " + C_TO3_PRODUCT_ACCOUNT_ALIAS_1 + " ON " +
   * C_RELATION_PRVT_ALIAS_1 + "." + C_RELTN_NBR + " = " +
   * C_TO3_PRODUCT_ACCOUNT_ALIAS_1 + "." + C_PRODUCT_ACCOUNT_RELTN_NBR + " AND " +
   * C_RELATION_PRVT_ALIAS_1 + "." + C_RELTN_CUST_1_NBR + " = " +
   * C_TO3_PRODUCT_ACCOUNT_ALIAS_1 + "." + C_PRODUCT_ACCOUNT_CUST_NBR ); } }
   * 
   * query.append( " WHERE " );
   * 
   * String criteria = "";
   * 
   * criteria = C_RELATION_PRVT_ALIAS_1 + "." + C_REC_STAT_CODE + " != '" +
   * BaseTplRelationPrvtEntity.C_REC_STAT_CODE_INACTIVE + "'" + " AND ";
   * 
   * if ( reltnNbr_ != null && reltnNbr_.longValue() != 0 ) { criteria =
   * criteria + " (" + C_RELATION_PRVT_ALIAS_1 + "." + C_RELTN_NBR + " = ? ) AND "; }
   * 
   * if ( reltnCust1Nbr_ != null && reltnCust1Nbr_.longValue() != 0 ) { if (
   * ownerSelected_ != null && ownerSelected_.equals(
   * com.citibank.ods.Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_NAO ) ) {
   * criteria = criteria + " (" + C_TPL_CUSTOMER_PRVT_ALIAS_1 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_NBR + " = ? OR "; criteria = criteria +
   * C_TPL_CUSTOMER_PRVT_ALIAS_2 + "." + BaseOracleTplCustomerPrvtDAO.C_CUST_NBR + " = ?
   * OR "; criteria = criteria + C_TPL_CUSTOMER_PRVT_ALIAS_3 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_NBR + " = ? OR "; criteria = criteria +
   * C_TPL_CUSTOMER_PRVT_ALIAS_4 + "." + BaseOracleTplCustomerPrvtDAO.C_CUST_NBR + " = ?
   * OR "; criteria = criteria + C_TPL_CUSTOMER_PRVT_ALIAS_5 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_NBR + " = ? ) AND "; } else { criteria =
   * criteria + " (" + C_TPL_CUSTOMER_PRVT_ALIAS_1 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_NBR + " = ? ) AND "; } }
   * 
   * if ( custFullNameText_ != null && !"".equals( custFullNameText_ ) ) { if (
   * ownerSelected_ != null && ownerSelected_.equals(
   * com.citibank.ods.Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_NAO ) ) { //
   * criteria = criteria + " (" + C_TPL_CUSTOMER_PRVT_ALIAS_1 + "." // +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT // + " = UPPER ( ? ) OR "; //
   * criteria = criteria + C_TPL_CUSTOMER_PRVT_ALIAS_2 + "." // +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT // + " = UPPER ( ? ) OR "; //
   * criteria = criteria + C_TPL_CUSTOMER_PRVT_ALIAS_3 + "." // +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT // + " = UPPER ( ? ) OR "; //
   * criteria = criteria + C_TPL_CUSTOMER_PRVT_ALIAS_4 + "." // +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT // + " = UPPER ( ? ) OR "; //
   * criteria = criteria + C_TPL_CUSTOMER_PRVT_ALIAS_5 + "." // +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT // + " = UPPER ( ? ))
   * AND ";
   * 
   * criteria = criteria + " ( UPPER(" + C_TPL_CUSTOMER_PRVT_ALIAS_1 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT + ") like ? OR ";
   * 
   * criteria = criteria + " UPPER(" + C_TPL_CUSTOMER_PRVT_ALIAS_2 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT + ") like ? OR ";
   * 
   * criteria = criteria + " UPPER(" + C_TPL_CUSTOMER_PRVT_ALIAS_3 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT + ") like ? OR ";
   * 
   * criteria = criteria + " UPPER(" + C_TPL_CUSTOMER_PRVT_ALIAS_4 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT + ") like ? OR ";
   * 
   * criteria = criteria + " UPPER(" + C_TPL_CUSTOMER_PRVT_ALIAS_5 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT + ") like ? ) AND "; }
   * else { // criteria = criteria + " (" + C_TPL_CUSTOMER_PRVT_ALIAS_1 + "." // +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT // + " = UPPER ( ? ))
   * AND ";
   * 
   * criteria = criteria + " ( UPPER(" + C_TPL_CUSTOMER_PRVT_ALIAS_1 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT + ") like ? ) AND "; } }
   * 
   * if ( acctNbr_ != null && acctNbr_.longValue() != 0 ) { if ( ownerSelected_ !=
   * null && ownerSelected_.equals(
   * com.citibank.ods.Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_NAO ) ) {
   * criteria = criteria + " (" + C_TO3_PRODUCT_ACCOUNT_ALIAS_1 + "." +
   * C_CUR_ACCT_NBR + " = ? OR "; criteria = criteria +
   * C_TO3_PRODUCT_ACCOUNT_ALIAS_2 + "." + C_CUR_ACCT_NBR + " = ? OR "; criteria =
   * criteria + C_TO3_PRODUCT_ACCOUNT_ALIAS_3 + "." + C_CUR_ACCT_NBR + " = ? OR ";
   * criteria = criteria + C_TO3_PRODUCT_ACCOUNT_ALIAS_4 + "." + C_CUR_ACCT_NBR + " = ?
   * OR "; criteria = criteria + C_TO3_PRODUCT_ACCOUNT_ALIAS_5 + "." +
   * C_CUR_ACCT_NBR + " = ? ) AND "; } else { criteria = criteria + " (" +
   * C_TO3_PRODUCT_ACCOUNT_ALIAS_1 + "." + C_CUR_ACCT_NBR + " = ? ) AND "; } }
   * 
   * if ( custCpfCnpjNbr_ != null && custCpfCnpjNbr_.longValue() != 0 ) { if (
   * ownerSelected_ != null && ownerSelected_.equals(
   * com.citibank.ods.Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_NAO ) ) {
   * criteria = criteria + " (" + C_TPL_CUSTOMER_PRVT_ALIAS_1 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_CPF_CNPJ_NBR + " = ? OR "; criteria =
   * criteria + C_TPL_CUSTOMER_PRVT_ALIAS_2 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_CPF_CNPJ_NBR + " = ? OR "; criteria =
   * criteria + C_TPL_CUSTOMER_PRVT_ALIAS_3 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_CPF_CNPJ_NBR + " = ? OR "; criteria =
   * criteria + C_TPL_CUSTOMER_PRVT_ALIAS_4 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_CPF_CNPJ_NBR + " = ? OR "; criteria =
   * criteria + C_TPL_CUSTOMER_PRVT_ALIAS_5 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_CPF_CNPJ_NBR + " = ? ) AND "; } else {
   * criteria = criteria + " (" + C_TPL_CUSTOMER_PRVT_ALIAS_1 + "." +
   * BaseOracleTplCustomerPrvtDAO.C_CUST_CPF_CNPJ_NBR + " = ? ) AND "; } }
   * 
   * if ( criteria.length() > 0 ) { criteria = criteria.substring( 0,
   * criteria.length() - 5 ); query.append( criteria ); }
   * 
   * query.append( " ORDER BY " + C_CUST_FULL_NAME_TEXT_1 );
   * 
   * preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() )); int count = 1;
   * 
   * if ( reltnNbr_ != null && reltnNbr_.longValue() != 0 ) { preparedStatement.setLong(
   * count++, reltnNbr_.longValue() ); } if ( reltnCust1Nbr_ != null &&
   * reltnCust1Nbr_.longValue() != 0 ) { if ( ownerSelected_ != null &&
   * ownerSelected_.equals(
   * com.citibank.ods.Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_NAO ) ) {
   * preparedStatement.setLong( count++, reltnCust1Nbr_.longValue() );
   * preparedStatement.setLong( count++, reltnCust1Nbr_.longValue() );
   * preparedStatement.setLong( count++, reltnCust1Nbr_.longValue() );
   * preparedStatement.setLong( count++, reltnCust1Nbr_.longValue() );
   * preparedStatement.setLong( count++, reltnCust1Nbr_.longValue() ); } else {
   * preparedStatement.setLong( count++, reltnCust1Nbr_.longValue() ); } } if (
   * custFullNameText_ != null && !"".equals( custFullNameText_ ) ) { if (
   * ownerSelected_ != null && ownerSelected_.equals(
   * com.citibank.ods.Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_NAO ) ) {
   * preparedStatement.setString( count++, "%" + custFullNameText_.toUpperCase() + "%" );
   * preparedStatement.setString( count++, "%" + custFullNameText_.toUpperCase() + "%" );
   * preparedStatement.setString( count++, "%" + custFullNameText_.toUpperCase() + "%" );
   * preparedStatement.setString( count++, "%" + custFullNameText_.toUpperCase() + "%" );
   * preparedStatement.setString( count++, "%" + custFullNameText_.toUpperCase() + "%" ); }
   * else { preparedStatement.setString( count++, "%" + custFullNameText_.toUpperCase() +
   * "%" ); } } if ( acctNbr_ != null && acctNbr_.longValue() != 0 ) { if (
   * ownerSelected_ != null && ownerSelected_.equals(
   * com.citibank.ods.Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_NAO ) ) {
   * preparedStatement.setLong( count++, acctNbr_.longValue() ); preparedStatement.setLong(
   * count++, acctNbr_.longValue() ); preparedStatement.setLong( count++,
   * acctNbr_.longValue() ); preparedStatement.setLong( count++, acctNbr_.longValue() );
   * preparedStatement.setLong( count++, acctNbr_.longValue() ); } else {
   * preparedStatement.setLong( count++, acctNbr_.longValue() ); } } if (
   * custCpfCnpjNbr_ != null && custCpfCnpjNbr_.longValue() != 0 ) { if (
   * ownerSelected_ != null && ownerSelected_.equals(
   * com.citibank.ods.Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_NAO ) ) {
   * preparedStatement.setLong( count++, custCpfCnpjNbr_.longValue() );
   * preparedStatement.setLong( count++, custCpfCnpjNbr_.longValue() );
   * preparedStatement.setLong( count++, custCpfCnpjNbr_.longValue() );
   * preparedStatement.setLong( count++, custCpfCnpjNbr_.longValue() );
   * preparedStatement.setLong( count++, custCpfCnpjNbr_.longValue() ); } else {
   * preparedStatement.setLong( count++, custCpfCnpjNbr_.longValue() ); } }
   * 
   * resultSet = preparedStatement.executeQuery();
   * 	  preparedStatement.replaceParametersInQuery(query.toString());
   * 
   * rsds = new ResultSetDataSet( resultSet ); resultSet.close(); } catch (
   * SQLException e ) { throw new UnexpectedException(
   * C_ERROR_EXECUTING_STATEMENT, e ); } finally { closeStatement( preparedStatement );
   * closeConnection( connection ); }
   * 
   * return rsds; }
   */

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTplRelationPrvtDAO#find(com.citibank.ods.entity.pl.BaseTplRelationPrvtEntity)
   */
  public BaseTplRelationPrvtEntity find(
                                        BaseTplRelationPrvtEntity baseTplRelationPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    ArrayList tplRelationPrvtEntities;
    BaseTplRelationPrvtEntity tplRelationPrvtEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_RELTN_NBR + ", " );
      query.append( C_RELTN_CLASS_CODE + ", " );
      query.append( C_RELTN_TYPE_CODE + ", " );
      query.append( C_RELTN_CAT_CODE + ", " );
      query.append( C_RELTN_SEG_CODE + ", " );
      query.append( C_RELTN_CUST_ADDR_NBR + ", " );
      query.append( C_RELTN_CUST_ADDR_SEQ_NBR + ", " );
      query.append( C_RELTN_ESTAB_DATE + ", " );
      query.append( C_RELTN_PRMT_CODE + ", " );
      query.append( C_RELTN_PORTF_CODE + ", " );
      query.append( C_RELTN_STMT_OPTN_CODE + ", " );
      query.append( C_RELTN_CLASS_SCORE_CODE + ", " );
      query.append( C_RELTN_MF_STMT_IND + ", " );
      query.append( C_RELTN_SAV_STMT_IND + ", " );
      query.append( C_RELTN_ACCT_STMT_IND + ", " );
      query.append( C_RELTN_TD_STMT_IND + ", " );
      query.append( C_RELTN_CORP_BASE_NBR + ", " );
      query.append( C_RELTN_SPCF_CLASS_SERV_PACK_IND + ", " );
      query.append( C_RELTN_RISK_LEVEL_CODE + ", " );
      query.append( C_RELTN_ADDR_EMAIL_CUST_NBR + ", " );
      query.append( C_RELTN_ADDR_EMAIL_SEQ_NBR + ", " );
      query.append( C_RELTN_ADDR_CELL_CUST_NBR + ", " );
      query.append( C_RELTN_ADDR_CELL_SEQ_NBR + ", " );
      query.append( C_RELTN_COMM_TYPE_CODE + ", " );
      query.append( C_RELTN_CUST_1_NBR + ", " );
      query.append( C_RELTN_CUST_2_NBR + ", " );
      query.append( C_RELTN_CUST_3_NBR + ", " );
      query.append( C_RELTN_CUST_4_NBR + ", " );
      query.append( C_RELTN_CUST_5_NBR + ", " );
      query.append( C_LAST_UPDATE_OP_ID + ", " );
      query.append( C_LAST_UPDATE_DATE + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_RELATION_PRVT );
      query.append( " WHERE " );
      query.append( C_RELTN_NBR + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         baseTplRelationPrvtEntity_.getData().getReltnNbr().longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());
	  

      tplRelationPrvtEntities = instantiateFromResultSet( resultSet );

      if ( tplRelationPrvtEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplRelationPrvtEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplRelationPrvtEntity = ( BaseTplRelationPrvtEntity ) tplRelationPrvtEntities.get( 0 );
      }

      return tplRelationPrvtEntity;
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
    TplRelationPrvtEntity tplRelationPrvtEntity;
    ArrayList tplRelationPrvtEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplRelationPrvtEntity = new TplRelationPrvtEntity();

        if ( resultSet_.getDate( C_LAST_UPDATE_DATE ) != null )
        {
          tplRelationPrvtEntity.getData().setLastUpdateDate(
                                                             resultSet_.getDate( C_LAST_UPDATE_DATE ) );
        }
        else
        {
          tplRelationPrvtEntity.getData().setLastUpdateDate( null );
        }
        if ( resultSet_.getString( C_LAST_UPDATE_OP_ID ) != null )
        {
          tplRelationPrvtEntity.getData().setLastUpdateOpId(
                                                             resultSet_.getString( C_LAST_UPDATE_OP_ID ) );
        }
        else
        {
          tplRelationPrvtEntity.getData().setLastUpdateOpId( null );
        }

        tplRelationPrvtEntity.getData().setRecStatCode(
                                                        ( resultSet_.getString( C_REC_STAT_CODE ) ) != null
                                                                                                           ? resultSet_.getString( C_REC_STAT_CODE )
                                                                                                           : null );

        tplRelationPrvtEntity.getData().setRecStatCode(
                                                        ( resultSet_.getString( C_REC_STAT_CODE ) ) != null
                                                                                                           ? resultSet_.getString( C_REC_STAT_CODE )
                                                                                                           : null );
        tplRelationPrvtEntity.getData().setReltnAcctStmtInd(
                                                             ( resultSet_.getString( C_RELTN_ACCT_STMT_IND ) ) != null
                                                                                                                      ? resultSet_.getString( C_RELTN_ACCT_STMT_IND )
                                                                                                                      : null );
        tplRelationPrvtEntity.getData().setReltnAddrCellCustNbr(
                                                                 ( resultSet_.getString( C_RELTN_ADDR_CELL_CUST_NBR ) ) != null
                                                                                                                               ? new BigInteger(
                                                                                                                                                 resultSet_.getString( C_RELTN_ADDR_CELL_CUST_NBR ) )
                                                                                                                               : null );
        tplRelationPrvtEntity.getData().setReltnAddrCellSeqNbr(
                                                                ( resultSet_.getString( C_RELTN_ADDR_CELL_SEQ_NBR ) ) != null
                                                                                                                             ? new BigInteger(
                                                                                                                                               resultSet_.getString( C_RELTN_ADDR_CELL_SEQ_NBR ) )
                                                                                                                             : null );
        tplRelationPrvtEntity.getData().setReltnAddrEmailCustNbr(
                                                                  ( resultSet_.getString( C_RELTN_ADDR_EMAIL_CUST_NBR ) ) != null
                                                                                                                                 ? new BigInteger(
                                                                                                                                                   resultSet_.getString( C_RELTN_ADDR_EMAIL_CUST_NBR ) )
                                                                                                                                 : null );
        tplRelationPrvtEntity.getData().setReltnAddrEmailSeqNbr(
                                                                 ( resultSet_.getString( C_RELTN_ADDR_EMAIL_SEQ_NBR ) ) != null
                                                                                                                               ? new BigInteger(
                                                                                                                                                 resultSet_.getString( C_RELTN_ADDR_EMAIL_SEQ_NBR ) )
                                                                                                                               : null );
        tplRelationPrvtEntity.getData().setReltnCatCode(
                                                         ( resultSet_.getString( C_RELTN_CAT_CODE ) ) != null
                                                                                                             ? resultSet_.getString( C_RELTN_CAT_CODE )
                                                                                                             : null );
        tplRelationPrvtEntity.getData().setReltnClassCode(
                                                           ( resultSet_.getString( C_RELTN_CLASS_CODE ) ) != null
                                                                                                                 ? resultSet_.getString( C_RELTN_CLASS_CODE )
                                                                                                                 : null );
        tplRelationPrvtEntity.getData().setReltnClassScoreCode(
                                                                ( resultSet_.getString( C_RELTN_CLASS_SCORE_CODE ) ) != null
                                                                                                                            ? resultSet_.getString( C_RELTN_CLASS_SCORE_CODE )
                                                                                                                            : null );
        tplRelationPrvtEntity.getData().setReltnCommTypeCode(
                                                              ( resultSet_.getString( C_RELTN_COMM_TYPE_CODE ) ) != null
                                                                                                                        ? resultSet_.getString( C_RELTN_COMM_TYPE_CODE )
                                                                                                                        : null );
        tplRelationPrvtEntity.getData().setReltnCorpBaseNbr(
                                                             ( resultSet_.getString( C_RELTN_CORP_BASE_NBR ) ) != null
                                                                                                                      ? new BigInteger(
                                                                                                                                        resultSet_.getString( C_RELTN_CORP_BASE_NBR ) )
                                                                                                                      : null );
        tplRelationPrvtEntity.getData().setReltnCust1Nbr(
                                                          ( resultSet_.getString( C_RELTN_CUST_1_NBR ) ) != null
                                                                                                                ? new BigInteger(
                                                                                                                                  resultSet_.getString( C_RELTN_CUST_1_NBR ) )
                                                                                                                : null );
        tplRelationPrvtEntity.getData().setReltnCust2Nbr(
                                                          ( resultSet_.getString( C_RELTN_CUST_2_NBR ) ) != null
                                                                                                                ? new BigInteger(
                                                                                                                                  resultSet_.getString( C_RELTN_CUST_2_NBR ) )
                                                                                                                : null );
        tplRelationPrvtEntity.getData().setReltnCust3Nbr(
                                                          ( resultSet_.getString( C_RELTN_CUST_3_NBR ) ) != null
                                                                                                                ? new BigInteger(
                                                                                                                                  resultSet_.getString( C_RELTN_CUST_3_NBR ) )
                                                                                                                : null );
        tplRelationPrvtEntity.getData().setReltnCust4Nbr(
                                                          ( resultSet_.getString( C_RELTN_CUST_4_NBR ) ) != null
                                                                                                                ? new BigInteger(
                                                                                                                                  resultSet_.getString( C_RELTN_CUST_4_NBR ) )
                                                                                                                : null );
        tplRelationPrvtEntity.getData().setReltnCust5Nbr(
                                                          ( resultSet_.getString( C_RELTN_CUST_5_NBR ) ) != null
                                                                                                                ? new BigInteger(
                                                                                                                                  resultSet_.getString( C_RELTN_CUST_5_NBR ) )
                                                                                                                : null );
        tplRelationPrvtEntity.getData().setReltnCustAddrNbr(
                                                             ( resultSet_.getString( C_RELTN_CUST_ADDR_NBR ) ) != null
                                                                                                                      ? new BigInteger(
                                                                                                                                        resultSet_.getString( C_RELTN_CUST_ADDR_NBR ) )
                                                                                                                      : null );
        tplRelationPrvtEntity.getData().setReltnCustAddrSeqNbr(
                                                                ( resultSet_.getString( C_RELTN_CUST_ADDR_SEQ_NBR ) ) != null
                                                                                                                             ? new BigInteger(
                                                                                                                                               resultSet_.getString( C_RELTN_CUST_ADDR_SEQ_NBR ) )
                                                                                                                             : null );
        tplRelationPrvtEntity.getData().setReltnEstabDate(
                                                           ( resultSet_.getDate( C_RELTN_ESTAB_DATE ) ) != null
                                                                                                               ? resultSet_.getDate( C_RELTN_ESTAB_DATE )
                                                                                                               : null );
        tplRelationPrvtEntity.getData().setReltnMfStmtInd(
                                                           ( resultSet_.getString( C_RELTN_MF_STMT_IND ) ) != null
                                                                                                                  ? resultSet_.getString( C_RELTN_MF_STMT_IND )
                                                                                                                  : null );
        tplRelationPrvtEntity.getData().setReltnNbr(
                                                     ( resultSet_.getString( C_RELTN_NBR ) ) != null
                                                                                                    ? new BigInteger(
                                                                                                                      resultSet_.getString( C_RELTN_NBR ) )
                                                                                                    : null );
        tplRelationPrvtEntity.getData().setReltnPortfCode(
                                                           ( resultSet_.getString( C_RELTN_PORTF_CODE ) ) != null
                                                                                                                 ? resultSet_.getString( C_RELTN_PORTF_CODE )
                                                                                                                 : null );
        tplRelationPrvtEntity.getData().setReltnPrmtCode(
                                                          ( resultSet_.getString( C_RELTN_PRMT_CODE ) ) != null
                                                                                                               ? new BigInteger(
                                                                                                                                 resultSet_.getString( C_RELTN_PRMT_CODE ) )
                                                                                                               : null );
        tplRelationPrvtEntity.getData().setReltnRiskLevelCode(
                                                               ( resultSet_.getString( C_RELTN_RISK_LEVEL_CODE ) ) != null
                                                                                                                          ? resultSet_.getString( C_RELTN_RISK_LEVEL_CODE )
                                                                                                                          : null );
        tplRelationPrvtEntity.getData().setReltnSavStmtInd(
                                                            ( resultSet_.getString( C_RELTN_SAV_STMT_IND ) ) != null
                                                                                                                    ? resultSet_.getString( C_RELTN_SAV_STMT_IND )
                                                                                                                    : null );
        tplRelationPrvtEntity.getData().setReltnSegCode(
                                                         ( resultSet_.getString( C_RELTN_SEG_CODE ) ) != null
                                                                                                             ? resultSet_.getString( C_RELTN_SEG_CODE )
                                                                                                             : null );
        tplRelationPrvtEntity.getData().setReltnSpcfClassServPackInd(
                                                                      ( resultSet_.getString( C_RELTN_SPCF_CLASS_SERV_PACK_IND ) ) != null
                                                                                                                                          ? resultSet_.getString( C_RELTN_SPCF_CLASS_SERV_PACK_IND )
                                                                                                                                          : null );
        tplRelationPrvtEntity.getData().setReltnStmtOptnCode(
                                                              ( resultSet_.getString( C_RELTN_STMT_OPTN_CODE ) ) != null
                                                                                                                        ? resultSet_.getString( C_RELTN_STMT_OPTN_CODE )
                                                                                                                        : null );
        tplRelationPrvtEntity.getData().setReltnTdStmtInd(
                                                           ( resultSet_.getString( C_RELTN_TD_STMT_IND ) ) != null
                                                                                                                  ? resultSet_.getString( C_RELTN_TD_STMT_IND )
                                                                                                                  : null );
        tplRelationPrvtEntity.getData().setReltnTypeCode(
                                                          ( resultSet_.getString( C_RELTN_TYPE_CODE ) ) != null
                                                                                                               ? resultSet_.getString( C_RELTN_TYPE_CODE )
                                                                                                               : null );

        tplRelationPrvtEntities.add( tplRelationPrvtEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return tplRelationPrvtEntities;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplRelationPrvtDAO#existsActive(com.citibank.ods.entity.pl.TplRelationPrvtEntity)
   */
  public boolean existsActive( TplRelationPrvtEntity tplRelationPrvtEntity_ )
  {
    boolean exists = true;
    try
    {
      TplRelationPrvtEntity entity = ( TplRelationPrvtEntity ) this.find( tplRelationPrvtEntity_ );
      if ( !TplRelationPrvtEntity.C_REC_STAT_CODE_ACTIVE.equals( entity.getData().getRecStatCode() ) )
      {
        exists = false;
      }
    }
    catch ( NoRowsReturnedException e )
    {
      exists = false;
    }
    return exists;
  }
}