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
import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.To3ProductAccountMovEntity;
import com.citibank.ods.entity.pl.valueobject.To3ProductAccountMovEntityVO;
import com.citibank.ods.persistence.pl.dao.To3ProductAccountMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * @author michele.monteiro,16/05/2007
 *  
 */

public class OracleTo3ProductAccountMovDAO extends
    BaseOracleTo3ProductAccountDAO implements To3ProductAccountMovDAO
{

  /*
   * Nome da tabela
   */
  private static final String C_TO3_PRODUCT_ACCOUNT_MOV = C_O3_SCHEMA
                                                          + "TO3_PRODUCT_ACCOUNT_MOV";

  private static final String C_TPL_CUSTOMER_PRVT = C_PL_SCHEMA
                                                    + "TPL_CUSTOMER_PRVT";

  private static final String C_PRODUCT_ACCOUNT_MOV_ALIAS = "ACCT.";

  // Tabela TPL_PRODUCT
  private static final String C_TPL_PRODUCT = C_PL_SCHEMA + "TPL_PRODUCT";

  //Tabele TBG_POINT_ACCT
  private static final String C_TBG_POINT_ACCT_INVST = C_BG_SCHEMA
                                                       + "TBG_POINT_ACCT_INVST";

  //Número da conta investimento
  protected String C_INVST_CUR_ACCT_NBR = "INVST_CUR_ACCT_NBR";

  /*
   * Campos específicos da tabela
   */
  private String C_OPERN_CODE = "OPERN_CODE";

  protected String C_OPERN_TEXT = "OPERN_TEXT";

  public DataSet list( BigInteger reltnNbr_, BigInteger custNbr_,
                      BigInteger curAcctNbr_, String prodCode_,
                      String curAcctNbrBlank_, String custNbrBlank_,
                      String reltnNbrBlank_, String lastUpdUserId_,
                      String custFullNameText_ )
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
      query.append( "ACCT." + C_PROD_ACCT_CODE + ", " );
      query.append( "ACCT." + C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( "ACCT." + C_PROD_CODE + ", " );
      query.append( "PROD." + C_PROD_NAME + ", " );
      query.append( "ACCT." + C_SYS_SEG_CODE + ", " );
      query.append( "ACCT." + C_SYS_CODE + ", " );
      query.append( "ACCT." + C_CUST_NBR + ", " );
      query.append( "ACCT." + C_RELTN_NBR + ", " );
      query.append( "ACCT." + C_CUR_ACCT_NBR + ", " );
      query.append( "ACCT." + C_LAST_UPD_USER_ID + ", " );
      query.append( "ACCT." + C_LAST_UPD_DATE + ", " );
      query.append( "ACCT." + C_OPERN_CODE + ", " );
      query.append( "CUST." + C_CUST_FULL_NAME_TEXT + ", " );
      query.append( " POINT." + C_INVST_CUR_ACCT_NBR );
      query.append( " FROM " );
      query.append( C_TO3_PRODUCT_ACCOUNT_MOV + " ACCT " );
      query.append( " LEFT JOIN " );
      query.append( C_TPL_CUSTOMER_PRVT + " CUST " );
      query.append( " ON " );
      query.append( " ACCT." + C_CUST_NBR + " = " + "CUST." + C_CUST_NBR );
      query.append( " LEFT JOIN " );
      query.append( C_TPL_PRODUCT + " PROD " );
      query.append( " ON " );
      query.append( " ACCT.PROD_CODE = " + "PROD.PROD_CODE AND" );
      query.append( " ACCT.SYS_SEG_CODE = " + "PROD.SYS_SEG_CODE " );
      query.append( " LEFT JOIN " );
      query.append( C_TBG_POINT_ACCT_INVST + " POINT  " );
      query.append( " ON " );
      query.append( "ACCT." + C_CUR_ACCT_NBR + " = " );
      query.append( " POINT." + C_CUR_ACCT_NBR );

      String criteria = "";

      // Verifica se número de relacionamento deve ser NULL (checkbox
      // selecionado)
      if ( reltnNbrBlank_ != null && reltnNbrBlank_.equals( "true" ) )
      {
        criteria = criteria + "ACCT." + C_RELTN_NBR + " IS NULL AND ";
      }
      else if ( reltnNbr_ != null )
      {
        criteria = criteria + "ACCT." + C_RELTN_NBR + " = ? AND ";
      }

      // Verifica se número do client deve ser NULL (checkbox selecionado)
      if ( custNbrBlank_ != null && custNbrBlank_.equals( "true" ) )
      {
        criteria = criteria + "ACCT." + C_CUST_NBR + " IS NULL AND ";
      }
      else if ( custNbr_ != null )
      {
        criteria = criteria + "ACCT." + C_CUST_NBR + " = ? AND ";
      }

      // Verifica se número da conta deve ser NULL (checkbox selecionado)
      if ( curAcctNbrBlank_ != null && curAcctNbrBlank_.equals( "true" ) )
      {
        criteria = criteria + "ACCT." + C_CUR_ACCT_NBR + " IS NULL AND ";
      }
      else if ( curAcctNbr_ != null )
      {
        criteria = criteria + "ACCT." + C_CUR_ACCT_NBR + " = ? AND ";
      }

      if ( prodCode_ != null && !prodCode_.equals( "" ) )
      {
        criteria = criteria + "UPPER(\"" + C_PROD_CODE + "\") LIKE ? AND ";
      }

      if ( lastUpdUserId_ != null && !lastUpdUserId_.equals( "" ) )
      {
        criteria = criteria + "UPPER(" + C_PRODUCT_ACCOUNT_MOV_ALIAS
                   + C_LAST_UPD_USER_ID + ") LIKE  ? AND ";
      }

      if ( custFullNameText_ != null && custFullNameText_.length() > 0 )
      {
        criteria = criteria + "UPPER( CUST." + C_CUST_FULL_NAME_TEXT
                   + ") like ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        query.append( " WHERE " + criteria.substring( 0, criteria.length() - 5 ) );
      }

      query.append( " ORDER BY " + C_CUST_FULL_NAME_TEXT );

      String test = query.toString();
      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( reltnNbr_ != null && reltnNbrBlank_.equals( "false" )
           && reltnNbr_ != null )
      {
        preparedStatement.setLong( count++, reltnNbr_.longValue() );
      }

      if ( custNbr_ != null && custNbrBlank_.equals( "false" ) )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
      }

      if ( curAcctNbr_ != null && curAcctNbrBlank_.equals( "false" ) )
      {
        preparedStatement.setLong( count++, curAcctNbr_.longValue() );
      }

      if ( prodCode_ != null && !prodCode_.equals( "" ) )
      {
        preparedStatement.setString( count++, prodCode_.toUpperCase() );
      }

      if ( lastUpdUserId_ != null && lastUpdUserId_.length() != 0 )
      {
        preparedStatement.setString( count++, lastUpdUserId_.toUpperCase() );
      }

      if ( custFullNameText_ != null && custFullNameText_.length() > 0 )
      {
        preparedStatement.setString( count++, "%" + custFullNameText_.toUpperCase()
                                      + "%" );
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

    return rsds;
  }

  public To3ProductAccountMovEntity insert(
                                           To3ProductAccountMovEntity to3ProductAccountMovEntity_ )
  {

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TO3_PRODUCT_ACCOUNT_MOV + " ( " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE + "," );
      query.append( C_CUST_NBR + ", " );
      query.append( C_RELTN_NBR + ", " );
      query.append( C_CUR_ACCT_NBR + ", " );
      query.append( C_PROD_CODE + ", " );
      query.append( C_PROD_PLCY_23A_IND + ", " );
      query.append( C_PROD_PLCY_23B_IND + ", " );
      query.append( C_PROD_ACCT_ISIN_CODE + ", " );
      query.append( C_PROD_ACCT_PORTF_MGMT_CODE + ", " );
      query.append( C_PROD_ACCT_LEGAL_BUS_CODE + ", " );
      query.append( C_SYS_CODE + ", " );
      query.append( C_SYS_SEG_CODE + ", " );
      query.append( C_ORIG_PROD_ACCT_NBR + ", " );
      query.append( C_PROD_ACCT_SIT_CODE + "," );
      query.append( C_PROD_ACCT_END_DATE + "," );
      query.append( C_PROD_ACCT_STA_DATE + "," );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_OPERN_CODE + ") " );
      query.append( "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;
      if ( to3ProductAccountMovEntity_.getData().getProdAcctCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           to3ProductAccountMovEntity_.getData().getProdAcctCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );

      }

      if ( to3ProductAccountMovEntity_.getData().getProdUnderAcctCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           to3ProductAccountMovEntity_.getData().getProdUnderAcctCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );

      }

      if ( to3ProductAccountMovEntity_.getData().getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           to3ProductAccountMovEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );

      }

      if ( to3ProductAccountMovEntity_.getData().getReltnNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           to3ProductAccountMovEntity_.getData().getReltnNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );

      }

      if ( to3ProductAccountMovEntity_.getData().getCurAcctNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           to3ProductAccountMovEntity_.getData().getCurAcctNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );

      }

      if ( to3ProductAccountMovEntity_.getData().getProdCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             to3ProductAccountMovEntity_.getData().getProdCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );

      }
      if ( to3ProductAccountMovEntity_.getData().getProdAcctPlcy23aInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             to3ProductAccountMovEntity_.getData().getProdAcctPlcy23aInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );

      }
      if ( to3ProductAccountMovEntity_.getData().getProdAcctPlcy23bInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             to3ProductAccountMovEntity_.getData().getProdAcctPlcy23bInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );

      }
      if ( to3ProductAccountMovEntity_.getData().getProdAcctIsinCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             to3ProductAccountMovEntity_.getData().getProdAcctIsinCode() );
      }

      else
      {
        preparedStatement.setString( count++, null );

      }
      if ( to3ProductAccountMovEntity_.getData().getProdAcctPortfMgmtCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             to3ProductAccountMovEntity_.getData().getProdAcctPortfMgmtCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );

      }
      if ( to3ProductAccountMovEntity_.getData().getProdAcctLegalBusCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           to3ProductAccountMovEntity_.getData().getProdAcctLegalBusCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );

      }
      if ( to3ProductAccountMovEntity_.getData().getSysCode() != null )
      {
        preparedStatement.setString( count++,
                             to3ProductAccountMovEntity_.getData().getSysCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );

      }
      if ( to3ProductAccountMovEntity_.getData().getSysSegCode() != null )
      {
        preparedStatement.setInt(
                          count++,
                          to3ProductAccountMovEntity_.getData().getSysSegCode().intValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );

      }

      if ( to3ProductAccountMovEntity_.getData().getOrigProdAcctNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             to3ProductAccountMovEntity_.getData().getOrigProdAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );

      }
      if ( to3ProductAccountMovEntity_.getData().getProdAcctSitCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             to3ProductAccountMovEntity_.getData().getProdAcctSitCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );

      }

      if ( to3ProductAccountMovEntity_.getData().getProdAcctEndDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               to3ProductAccountMovEntity_.getData().getProdAcctEndDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( to3ProductAccountMovEntity_.getData().getProdAcctStaDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               to3ProductAccountMovEntity_.getData().getProdAcctStaDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( to3ProductAccountMovEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               to3ProductAccountMovEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      preparedStatement.setString(
                           count++,
                           to3ProductAccountMovEntity_.getData().getLastUpdUserId() );

      preparedStatement.setString(
                           count++,
                           ( ( To3ProductAccountMovEntityVO ) to3ProductAccountMovEntity_.getData() ).getOpernCode() );
      preparedStatement.execute();
      
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
    return to3ProductAccountMovEntity_;
  }

  public To3ProductAccountMovEntity update(
                                           To3ProductAccountMovEntity to3ProductAccountMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "UPDATE " );
      query.append( C_TO3_PRODUCT_ACCOUNT_MOV );
      query.append( " SET " );
      query.append( C_CUST_NBR ).append( " = ?, " );
      query.append( C_RELTN_NBR  ).append( " = ?, " );
      query.append( C_CUR_ACCT_NBR  ).append( " = ?, " );
      query.append( C_PROD_CODE  ).append( " = ?, " );
      query.append( C_PROD_PLCY_23A_IND ).append( " = ?, " );
      query.append( C_PROD_PLCY_23B_IND  ).append( " = ?, " );
      query.append( C_PROD_ACCT_ISIN_CODE ).append( " = ?, " );
      query.append( C_PROD_ACCT_PORTF_MGMT_CODE  ).append( " = ?, " );
      query.append( C_PROD_ACCT_LEGAL_BUS_CODE ).append(" = ?,  " );
      query.append( C_LAST_UPD_DATE ).append(" = ?, " );
      query.append( C_LAST_UPD_USER_ID ).append(" = ? ");

      query.append( "WHERE " );
      query.append( C_PROD_ACCT_CODE  ).append( " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE  ).append( " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( to3ProductAccountMovEntity_.getData().getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           to3ProductAccountMovEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( to3ProductAccountMovEntity_.getData().getReltnNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           to3ProductAccountMovEntity_.getData().getReltnNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, java.sql.Types.BIGINT );
      }

      if ( to3ProductAccountMovEntity_.getData().getCurAcctNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           to3ProductAccountMovEntity_.getData().getCurAcctNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( to3ProductAccountMovEntity_.getData().getProdCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             to3ProductAccountMovEntity_.getData().getProdCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( to3ProductAccountMovEntity_.getData().getProdAcctPlcy23aInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             to3ProductAccountMovEntity_.getData().getProdAcctPlcy23aInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( to3ProductAccountMovEntity_.getData().getProdAcctPlcy23bInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             to3ProductAccountMovEntity_.getData().getProdAcctPlcy23bInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( to3ProductAccountMovEntity_.getData().getProdAcctIsinCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             to3ProductAccountMovEntity_.getData().getProdAcctIsinCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( to3ProductAccountMovEntity_.getData().getProdAcctPortfMgmtCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             to3ProductAccountMovEntity_.getData().getProdAcctPortfMgmtCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( to3ProductAccountMovEntity_.getData().getProdAcctLegalBusCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           to3ProductAccountMovEntity_.getData().getProdAcctLegalBusCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      if ( to3ProductAccountMovEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               to3ProductAccountMovEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      preparedStatement.setString(
                           count++,
                           to3ProductAccountMovEntity_.getData().getLastUpdUserId() );

      if ( to3ProductAccountMovEntity_.getData().getProdAcctCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           to3ProductAccountMovEntity_.getData().getProdAcctCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( to3ProductAccountMovEntity_.getData().getProdUnderAcctCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           to3ProductAccountMovEntity_.getData().getProdUnderAcctCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      preparedStatement.executeUpdate();
	  
	  preparedStatement.replaceParametersInQuery(query.toString());
      
      return to3ProductAccountMovEntity_;

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

  public To3ProductAccountMovEntity delete(
                                           To3ProductAccountMovEntity to3ProductAccountMovEntity_ )
  {

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "DELETE FROM " );
      query.append( C_TO3_PRODUCT_ACCOUNT_MOV );
      query.append( " WHERE " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         to3ProductAccountMovEntity_.getData().getProdAcctCode().longValue() );
      preparedStatement.setLong(
                         2,
                         to3ProductAccountMovEntity_.getData().getProdUnderAcctCode().longValue() );

      preparedStatement.executeUpdate();
      
	  preparedStatement.replaceParametersInQuery(query.toString());

      return to3ProductAccountMovEntity_;
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

  public boolean exists( To3ProductAccountMovEntity to3ProductAccountMovEntity_ )
  {

    boolean exists = true;

    try
    {
      this.find( to3ProductAccountMovEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  public BaseTo3ProductAccountEntity find(
                                          BaseTo3ProductAccountEntity productAccountEntity_ )
  {

    To3ProductAccountMovEntity productAccountEntity = ( To3ProductAccountMovEntity ) productAccountEntity_;
    To3ProductAccountMovEntityVO productAccountMovEntityVO = ( To3ProductAccountMovEntityVO ) productAccountEntity.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList productAccountMovEntities;
    To3ProductAccountMovEntity productAccountMovEntity = null;
	//TESTE
	if(productAccountMovEntityVO.getProdAcctCode()==null
	|| productAccountMovEntityVO.getProdUnderAcctCode()==null)
		throw new NoRowsReturnedException();
		
    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "SELECT " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_RELTN_NBR + ", " );
      query.append( C_CUR_ACCT_NBR + ", " );
      query.append( C_PROD_CODE + ", " );
      query.append( C_SYS_CODE + ", " );
      query.append( C_SYS_SEG_CODE + ", " );
      query.append( C_ORIG_PROD_ACCT_NBR + ", " );
      query.append( C_PROD_ACCT_STA_DATE + ", " );
      query.append( C_PROD_ACCT_END_DATE + ", " );
      query.append( C_PROD_ACCT_SIT_CODE + ", " );
      query.append( C_PROD_ACCT_ISIN_CODE + ", " );
      query.append( C_PROD_ACCT_LEGAL_BUS_CODE + ", " );
      query.append( C_PROD_PLCY_23A_IND + ", " );
      query.append( C_PROD_PLCY_23B_IND + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_PROD_ACCT_PORTF_MGMT_CODE + ", " );
      query.append( C_OPERN_CODE );
      query.append( " FROM " );
      query.append( C_TO3_PRODUCT_ACCOUNT_MOV );
      query.append( " WHERE " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         productAccountMovEntityVO.getProdAcctCode().longValue() );
      preparedStatement.setLong(
                         2,
                         productAccountMovEntityVO.getProdUnderAcctCode().longValue() );

	  preparedStatement.replaceParametersInQuery(query.toString());
      resultSet = preparedStatement.executeQuery();
      
      productAccountMovEntities = instantiateFromResultSet( resultSet );

      if ( productAccountMovEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( productAccountMovEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        productAccountMovEntity = ( To3ProductAccountMovEntity ) productAccountMovEntities.get( 0 );
      }

      return productAccountMovEntity;
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

  /**
   * Instancia entidades a partir do ResultSet.
   * 
   * @param resultSet_ - ResultSet utilizado para instanciar entidades.
   * @return ArrayList - Entidades instanciadas a partir do ResultSet.
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {

    To3ProductAccountMovEntity productAccountMovEntity;
    To3ProductAccountMovEntityVO productAccountMovEntityVO;
    ArrayList productAccountMovEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        productAccountMovEntity = new To3ProductAccountMovEntity();
        productAccountMovEntityVO = ( To3ProductAccountMovEntityVO ) productAccountMovEntity.getData();

        productAccountMovEntityVO.setProdAcctCode( new BigInteger(
                                                                   resultSet_.getString( this.C_PROD_ACCT_CODE ) ) );
        productAccountMovEntityVO.setProdUnderAcctCode( new BigInteger(
                                                                        resultSet_.getString( this.C_PROD_UNDER_ACCT_CODE ) ) );
        productAccountMovEntityVO.setCustNbr( new BigInteger(
                                                              resultSet_.getString( this.C_CUST_NBR ) ) );
        productAccountMovEntityVO.setReltnNbr( new BigInteger(
                                                               resultSet_.getString( this.C_RELTN_NBR ) ) );
        
        //Alteração Ronaldo Machado G&P - Inicio
        if (resultSet_.getString( this.C_CUR_ACCT_NBR ) != null){
			productAccountMovEntityVO.setCurAcctNbr( new BigInteger(
													 resultSet_.getString( this.C_CUR_ACCT_NBR ) ) );
        }
        //Alteração Ronaldo Machado G&P - Fim
        productAccountMovEntityVO.setProdCode( resultSet_.getString( this.C_PROD_CODE ) );
        if ( resultSet_.getString( this.C_SYS_CODE ) != null )
        {
          productAccountMovEntityVO.setSysCode( resultSet_.getString( this.C_SYS_CODE ) );
        }
        if ( resultSet_.getString( this.C_SYS_SEG_CODE ) != null )
        {
          productAccountMovEntityVO.setSysSegCode( new BigInteger(
                                                                   resultSet_.getString( this.C_SYS_SEG_CODE ) ) );

        }
        if ( resultSet_.getString( this.C_ORIG_PROD_ACCT_NBR ) != null )
        {
          productAccountMovEntityVO.setOrigProdAcctNbr( resultSet_.getString( this.C_ORIG_PROD_ACCT_NBR ) );

        }
        if ( resultSet_.getTimestamp( this.C_PROD_ACCT_STA_DATE ) != null )
        {
          productAccountMovEntityVO.setProdAcctStaDate( resultSet_.getTimestamp( this.C_PROD_ACCT_STA_DATE ) );
        }

        if ( resultSet_.getTimestamp( this.C_PROD_ACCT_END_DATE ) != null )
        {
          productAccountMovEntityVO.setProdAcctEndDate( resultSet_.getTimestamp( this.C_PROD_ACCT_END_DATE ) );
        }
        if ( resultSet_.getString( this.C_PROD_ACCT_SIT_CODE ) != null )
        {
          productAccountMovEntityVO.setProdAcctSitCode( resultSet_.getString( this.C_PROD_ACCT_SIT_CODE ) );
        }

        if ( resultSet_.getString( this.C_PROD_ACCT_LEGAL_BUS_CODE ) != null )
        {

          productAccountMovEntityVO.setProdAcctLegalBusCode( new BigInteger(
                                                                             resultSet_.getString( this.C_PROD_ACCT_LEGAL_BUS_CODE ) ) );
        }

        productAccountMovEntityVO.setLastUpdDate( resultSet_.getTimestamp( this.C_LAST_UPD_DATE ) );
        productAccountMovEntityVO.setLastUpdUserId( resultSet_.getString( this.C_LAST_UPD_USER_ID ) );
        productAccountMovEntityVO.setProdAcctIsinCode( resultSet_.getString( this.C_PROD_ACCT_ISIN_CODE ) );
        productAccountMovEntityVO.setProdAcctPortfMgmtCode( resultSet_.getString( this.C_PROD_ACCT_PORTF_MGMT_CODE ) );
        productAccountMovEntityVO.setProdAcctPlcy23aInd( resultSet_.getString( this.C_PROD_PLCY_23A_IND ) );
        productAccountMovEntityVO.setProdAcctPlcy23bInd( resultSet_.getString( this.C_PROD_PLCY_23B_IND ) );
        productAccountMovEntityVO.setOpernCode( resultSet_.getString( this.C_OPERN_CODE ) );

        productAccountMovEntities.add( productAccountMovEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return productAccountMovEntities;
  }

  public ArrayList selectByPk( BigInteger prodAcctCode_,
                              BigInteger prodUnderAcctCode_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList productAccountMovEntities = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_OPERN_CODE );
      query.append( " FROM " );
      query.append( C_TO3_PRODUCT_ACCOUNT_MOV );

      String criteria = "";

      if ( prodAcctCode_ != null && !prodAcctCode_.equals( "" ) )
      {
        criteria = criteria + C_PROD_ACCT_CODE + " = ? AND";
        query.append( criteria );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( prodUnderAcctCode_ != null && !prodUnderAcctCode_.equals( "" ) )
      {
        criteria = criteria + C_PROD_UNDER_ACCT_CODE + " = ? ";
        query.append( "	WHERE " + criteria );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      if ( prodAcctCode_ != null && !prodAcctCode_.equals( "" ) )
      {
        preparedStatement.setLong( 1, prodAcctCode_.longValue() );
      }

      if ( prodUnderAcctCode_ != null && !prodUnderAcctCode_.equals( "" ) )
      {
        preparedStatement.setLong( 2, prodUnderAcctCode_.longValue() );
      }

      resultSet = preparedStatement.executeQuery();
      
	  preparedStatement.replaceParametersInQuery(query.toString());
	  
      productAccountMovEntities = instantiateFromResultSet( resultSet );

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

    return productAccountMovEntities;
  }

/* (non-Javadoc)
 * @see com.citibank.ods.persistence.pl.dao.BaseTo3ProductAccountDAO#findByPK(com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity)
 */
public BaseTo3ProductAccountEntity findByPK(BaseTo3ProductAccountEntity productAccountEntity_) {
	// TODO Auto-generated method stub
	return null;
}

}