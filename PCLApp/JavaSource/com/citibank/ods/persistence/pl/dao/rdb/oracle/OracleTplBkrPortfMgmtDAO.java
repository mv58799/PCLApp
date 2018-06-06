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
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplBkrPortfMgmtEntity;
import com.citibank.ods.entity.pl.TplBkrPortfMgmtEntity;
import com.citibank.ods.entity.pl.valueobject.TplBkrPortfMgmtEntityVO;
import com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author Hamilton Matos
 */

public class OracleTplBkrPortfMgmtDAO extends BaseOracleTplBkrPortfMgmtDAO
    implements TplBkrPortfMgmtDAO
{
  private static final String C_BKR_CUST_NBR = "BKR_CUST_NBR";

  private static final String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private static final String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private static final String C_TABLE_BKR_PORTF_MGMT = C_PL_SCHEMA
                                                       + "TPL_BKR_PORTF_MGMT";

  private static final String C_TABLE_PROD_ACCT_PORTF_MGMT = C_O3_SCHEMA
                                                             + "TO3_PROD_ACCT_PORTF_MGMT";

  private static final String C_TABLE_POINT_ACCT_INVST = C_BG_SCHEMA
                                                         + "TBG_POINT_ACCT_INVST";

  private static final String C_TABLE_PRODUCT_ACCOUNT = C_O3_SCHEMA
                                                        + "TO3_PRODUCT_ACCOUNT";

  private static final String C_TABLE_RELATION_PRVT = C_PL_SCHEMA
                                                      + "TPL_RELATION_PRVT";

  private static final String C_TABLE_BROKER = C_PL_SCHEMA + "TPL_BROKER";

  private static final String C_TABLE_CUSTOMER_PRVT = C_PL_SCHEMA
                                                      + "TPL_CUSTOMER_PRVT";

  protected static final String C_PROD_NAME = "PROD_NAME";

  private static final String C_TPL_PRODUCT = C_PL_SCHEMA + "TPL_PRODUCT";

  private static final String C_ALIAS_PORTF_MGMT = "PORTF_MGMT";

  private static final String C_ALIAS_POINT_ACCT_INVST = "POINT_INVST";

  private static final String C_ALIAS_PRODUCT_ACCOUNT = "PRODUCT_ACCOUNT";

  private static final String C_ALIAS_RELATION_PRVT = "RELATION_PRVT";

  private static final String C_ALIAS_BROKER = "BROKER";

  private String C_REC_STAT_CODE = "REC_STAT_CODE";

  private String C_REC_STAT_TEXT = "REC_STAT_TEXT";

  private static final String C_TABLE_COLUMNS = "PROD_ACCT_CODE,PROD_UNDER_ACCT_CODE,BKR_CNPJ_NBR,BOVESPA_CUR_ACCT_NBR,BOVESPA_INVST_ACCT_NBR,BMF_CUR_ACCT_NBR,BMF_INVST_ACCT_NBR,LAST_UPD_DATE,LAST_UPD_USER_ID,LAST_AUTH_DATE,LAST_AUTH_USER_ID,OPERN_CODE";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtDAO#insert(com.citibank.ods.entity.pl.TplBkrPortfMgmtEntity)
   */
  public TplBkrPortfMgmtEntity insert(
                                      TplBkrPortfMgmtEntity tplBkrPorftMgmtEntity_ )
                                                                                    throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TABLE_BKR_PORTF_MGMT + " (" );
      query.append( C_BKR_CNPJ_NBR + ", " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_BOVESPA_CUR_ACCT_NBR + ", " );
      query.append( C_BOVESPA_INVST_ACCT_NBR + ", " );
      query.append( C_BMF_CUR_ACCT_NBR + ", " );
      query.append( C_BMF_INVST_ACCT_NBR + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " ) VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplBkrPorftMgmtEntity_.getData().getBkrCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplBkrPorftMgmtEntity_.getData().getBkrCnpjNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      if ( tplBkrPorftMgmtEntity_.getData().getProdAcctCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplBkrPorftMgmtEntity_.getData().getProdAcctCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      if ( tplBkrPorftMgmtEntity_.getData().getProdUnderAcctCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplBkrPorftMgmtEntity_.getData().getProdUnderAcctCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPorftMgmtEntity_.getData().getBovespaCurAcctNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPorftMgmtEntity_.getData().getBovespaCurAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBkrPorftMgmtEntity_.getData().getBovespaInvstAcctNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPorftMgmtEntity_.getData().getBovespaInvstAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplBkrPorftMgmtEntity_.getData().getBmfCurAcctNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPorftMgmtEntity_.getData().getBmfCurAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBkrPorftMgmtEntity_.getData().getBmfInvstAcctNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPorftMgmtEntity_.getData().getBmfInvstAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplBkrPorftMgmtEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBkrPorftMgmtEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPorftMgmtEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPorftMgmtEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      TplBkrPortfMgmtEntityVO tplBkrPortfMgmtEntityVO = ( TplBkrPortfMgmtEntityVO ) tplBkrPorftMgmtEntity_.getData();

      if ( tplBkrPortfMgmtEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBkrPortfMgmtEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPortfMgmtEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplBkrPortfMgmtEntityVO.getLastAuthUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPortfMgmtEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, tplBkrPortfMgmtEntityVO.getRecStatCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement.executeUpdate();
      
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplBkrPorftMgmtEntity_;

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
   * @see com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtDAO#update(com.citibank.ods.entity.pl.TplBkrPortfMgmtEntity)
   */
  public void update( TplBkrPortfMgmtEntity tplBkrPorftMgmtEntity_ )
                                                                    throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TABLE_BKR_PORTF_MGMT );
      query.append( " SET " );
      query.append( C_BOVESPA_CUR_ACCT_NBR + " = ?, " );
      query.append( C_BOVESPA_INVST_ACCT_NBR + " = ?, " );
      query.append( C_BMF_CUR_ACCT_NBR + " = ?, " );
      query.append( C_BMF_INVST_ACCT_NBR + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_LAST_AUTH_DATE + " = ?, " );
      query.append( C_LAST_AUTH_USER_ID + " = ?, " );
      query.append( C_REC_STAT_CODE + " = ? " );
      query.append( " WHERE " );
      query.append( C_BKR_CNPJ_NBR + " = ? AND " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplBkrPorftMgmtEntity_.getData().getBovespaCurAcctNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPorftMgmtEntity_.getData().getBovespaCurAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBkrPorftMgmtEntity_.getData().getBovespaInvstAcctNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPorftMgmtEntity_.getData().getBovespaInvstAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplBkrPorftMgmtEntity_.getData().getBmfCurAcctNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPorftMgmtEntity_.getData().getBmfCurAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBkrPorftMgmtEntity_.getData().getBmfInvstAcctNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPorftMgmtEntity_.getData().getBmfInvstAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplBkrPorftMgmtEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBkrPorftMgmtEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPorftMgmtEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPorftMgmtEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      TplBkrPortfMgmtEntityVO tplBkrPortfMgmtEntityVO = ( TplBkrPortfMgmtEntityVO ) tplBkrPorftMgmtEntity_.getData();

      if ( tplBkrPortfMgmtEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBkrPortfMgmtEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPortfMgmtEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplBkrPortfMgmtEntityVO.getLastAuthUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPortfMgmtEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, tplBkrPortfMgmtEntityVO.getRecStatCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPorftMgmtEntity_.getData().getBkrCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplBkrPorftMgmtEntity_.getData().getBkrCnpjNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      if ( tplBkrPorftMgmtEntity_.getData().getProdAcctCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplBkrPorftMgmtEntity_.getData().getProdAcctCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      if ( tplBkrPorftMgmtEntity_.getData().getProdUnderAcctCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplBkrPorftMgmtEntity_.getData().getProdUnderAcctCode().longValue() );
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

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtDAO#delete(java.math.BigInteger)
   */
  public void delete( BigInteger entityKey_ ) throws UnexpectedException
  {
    //
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtDAO#existsActive(com.citibank.ods.entity.pl.TplBkrPortfMgmtEntity)
   */
  public boolean existsActive( TplBkrPortfMgmtEntity tplBkrPorftMgmtEntity_ )
                                                                             throws UnexpectedException
  {
    boolean exists = true;

    try
    {
      TplBkrPortfMgmtEntity tplBkrPortfMgmtEntity = ( TplBkrPortfMgmtEntity ) this.find( tplBkrPorftMgmtEntity_ );
      TplBkrPortfMgmtEntityVO tplBkrPortfMgmtEntityVO = ( TplBkrPortfMgmtEntityVO ) tplBkrPortfMgmtEntity.getData();
      if ( !TplBkrPortfMgmtEntity.C_REC_STAT_CODE_ACTIVE.equals( tplBkrPortfMgmtEntityVO.getRecStatCode() ) )
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

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtDAO#exists(com.citibank.ods.entity.pl.TplBkrPortfMgmtEntity)
   */
  public boolean exists( TplBkrPortfMgmtEntity tplBkrPorftMgmtEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tplBkrPorftMgmtEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtDAO#listForBkrPorftMgmtGrid(java.lang.String)
   */
  public ArrayList listForBkrPorftMgmtGrid( BigInteger prodAcctCode_,
                                           BigInteger prodUnderAcctCode_ )
  {
    TplBkrPortfMgmtEntity tplBkrPortfMgmtEntity;
    TplBkrPortfMgmtEntityVO tplBkrPortfMgmtEntityVO;
    DataSetRow row;

    ArrayList result = new ArrayList();

    DataSet rds = this.list( prodAcctCode_, prodUnderAcctCode_ );

    for ( int indexRow = 0; indexRow < rds.size(); indexRow++ )
    {
      tplBkrPortfMgmtEntity = new TplBkrPortfMgmtEntity();
      tplBkrPortfMgmtEntityVO = ( TplBkrPortfMgmtEntityVO ) tplBkrPortfMgmtEntity.getData();

      row = rds.getRow( indexRow );

      tplBkrPortfMgmtEntityVO.setBkrCnpjNbr( row.getStringByName( C_BKR_CNPJ_NBR ) );
      tplBkrPortfMgmtEntityVO.setBkrNameText( row.getStringByName( C_BKR_NAME_TEXT ) );
      tplBkrPortfMgmtEntityVO.setBovespaCurAcctNbr( row.getStringByName( C_BOVESPA_CUR_ACCT_NBR ) );
      tplBkrPortfMgmtEntityVO.setBovespaInvstAcctNbr( row.getStringByName( C_BOVESPA_INVST_ACCT_NBR ) );
      tplBkrPortfMgmtEntityVO.setBmfCurAcctNbr( row.getStringByName( C_BMF_CUR_ACCT_NBR ) );
      tplBkrPortfMgmtEntityVO.setBmfInvstAcctNbr( row.getStringByName( C_BMF_INVST_ACCT_NBR ) );
      tplBkrPortfMgmtEntityVO.setProdAcctCode( new BigInteger(
                                                               row.getStringByName( C_PROD_ACCT_CODE ) ) );
      tplBkrPortfMgmtEntityVO.setProdUnderAcctCode( new BigInteger(
                                                                    row.getStringByName( C_PROD_UNDER_ACCT_CODE ) ) );

      result.add( tplBkrPortfMgmtEntity );
    }

    return result;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTplBkrPortfMgmtDAO#find(com.citibank.ods.entity.pl.BaseTplBkrPortfMgmtEntity)
   */
  public BaseTplBkrPortfMgmtEntity find(
                                        BaseTplBkrPortfMgmtEntity tplBkrPortfMgmtEntity_ )
                                                                                          throws UnexpectedException
  {
    ResultSet resultSet = null;
    CitiStatement preparedStatement = null;
    ManagedRdbConnection connection = null;
    StringBuffer sqlQuery = new StringBuffer();
    ArrayList tplBkrPortfMgmtEntities = new ArrayList();
    BaseTplBkrPortfMgmtEntity tplBkrPortfMgmtEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      sqlQuery.append( "SELECT " );
      sqlQuery.append( C_PROD_ACCT_CODE + ", " );
      sqlQuery.append( C_PROD_UNDER_ACCT_CODE + ", " );
      sqlQuery.append( C_BKR_CNPJ_NBR + ", " );
      sqlQuery.append( C_BOVESPA_CUR_ACCT_NBR + ", " );
      sqlQuery.append( C_BOVESPA_INVST_ACCT_NBR + ", " );
      sqlQuery.append( C_BMF_CUR_ACCT_NBR + ", " );
      sqlQuery.append( C_BMF_INVST_ACCT_NBR + ", " );
      sqlQuery.append( C_LAST_UPD_DATE + ", " );
      sqlQuery.append( C_LAST_UPD_USER_ID + ", " );
      sqlQuery.append( C_LAST_AUTH_DATE + ", " );
      sqlQuery.append( C_LAST_AUTH_USER_ID + ", " );
      sqlQuery.append( C_REC_STAT_CODE );
      sqlQuery.append( " FROM " );
      sqlQuery.append( C_TABLE_BKR_PORTF_MGMT );
      sqlQuery.append( " WHERE " );
      sqlQuery.append( C_BKR_CNPJ_NBR + " = ? " );
      sqlQuery.append( " AND " );
      sqlQuery.append( C_PROD_ACCT_CODE + " = ? " );
      sqlQuery.append( " AND " );
      sqlQuery.append( C_PROD_UNDER_ACCT_CODE + " = ? " );

	  preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

      preparedStatement.setString( 1, tplBkrPortfMgmtEntity_.getData().getBkrCnpjNbr() );

      preparedStatement.setLong(
                         2,
                         ( tplBkrPortfMgmtEntity_.getData().getProdAcctCode() ).longValue() );

      preparedStatement.setLong(
                         3,
                         ( tplBkrPortfMgmtEntity_.getData().getProdUnderAcctCode() ).longValue() );

      resultSet = preparedStatement.executeQuery();
      
	  preparedStatement.replaceParametersInQuery(sqlQuery.toString());

      tplBkrPortfMgmtEntities = instantiateFromResultSet( resultSet );

      if ( tplBkrPortfMgmtEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplBkrPortfMgmtEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplBkrPortfMgmtEntity = ( BaseTplBkrPortfMgmtEntity ) tplBkrPortfMgmtEntities.get( 0 );
      }

      return tplBkrPortfMgmtEntity;
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
   * @see com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtDAO#listPortfolio(java.math.BigInteger)
   */
  public DataSet listPortfolio( BigInteger custNbr_ )
                                                     throws UnexpectedException
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
      query.append( C_ALIAS_PRODUCT_ACCOUNT + "." + C_CUR_ACCT_NBR + ", " );
      query.append( C_ALIAS_POINT_ACCT_INVST + "." + C_INVST_CUR_ACCT_NBR
                    + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_PROD_ACCT_CODE + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_PORTF_MGMT_PROD_NAME + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_CUST_MNMC_NAME + ", " );
      query.append( C_ALIAS_PRODUCT_ACCOUNT + "." + C_PROD_CODE + ", " );
      query.append( C_ALIAS_PRODUCT_ACCOUNT + "." + C_REC_STAT_CODE );

      query.append( " FROM " );
      query.append( C_TABLE_PROD_ACCT_PORTF_MGMT + " " + C_ALIAS_PORTF_MGMT
                    + ", " );
      query.append( C_TABLE_POINT_ACCT_INVST + " " + C_ALIAS_POINT_ACCT_INVST
                    + ", " );
      query.append( C_TABLE_PRODUCT_ACCOUNT + " " + C_ALIAS_PRODUCT_ACCOUNT
                    + ", " );
      query.append( C_TABLE_RELATION_PRVT + " " + C_ALIAS_RELATION_PRVT );

      String criteria = "";

      criteria = criteria + C_ALIAS_PORTF_MGMT + "." + C_PROD_ACCT_CODE + " = ";
      criteria = criteria + C_ALIAS_PRODUCT_ACCOUNT + "." + C_PROD_ACCT_CODE
                 + " AND ";
      criteria = criteria + C_ALIAS_PORTF_MGMT + "." + C_PROD_UNDER_ACCT_CODE
                 + " = ";
      criteria = criteria + C_ALIAS_PRODUCT_ACCOUNT + "."
                 + C_PROD_UNDER_ACCT_CODE + " AND ";
//Alteraçao G&P INICIO 20/05/2008    
      criteria = criteria + " " + C_ALIAS_PRODUCT_ACCOUNT + "."
                 + C_CUR_ACCT_NBR + " = ";
      criteria = criteria + " " + C_ALIAS_POINT_ACCT_INVST + "."
                 + C_CUR_ACCT_NBR + " AND ";
//Alteraçao G&P FIM                 
      criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_NBR + " = "
                 + C_ALIAS_PRODUCT_ACCOUNT + "." + C_RELTN_NBR + " AND ";
      criteria = criteria + C_ALIAS_PORTF_MGMT + "." + C_REC_STAT_CODE + " != "
                 + "'" + BaseTplBkrPortfMgmtEntity.C_REC_STAT_CODE_INACTIVE
                 + "'" + " AND (";

      if ( custNbr_ != null && !"".equals( custNbr_ ) )
      {
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_1_NBR
                   + " = ? OR ";
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_2_NBR
                   + " = ? OR ";
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_3_NBR
                   + " = ? OR ";
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_4_NBR
                   + " = ? OR ";
        criteria = criteria + C_ALIAS_RELATION_PRVT + "." + C_RELTN_CUST_5_NBR
                   + " = ? )";
      }

      if ( criteria.length() > 0 )
      {
        query.append( "	WHERE " + criteria );
      }

      query.append( " ORDER BY " + C_ALIAS_PRODUCT_ACCOUNT + "."
                    + C_CUR_ACCT_NBR );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( custNbr_ != null && !"".equals( custNbr_ ) )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
        preparedStatement.setLong( count++, custNbr_.longValue() );
        preparedStatement.setLong( count++, custNbr_.longValue() );
        preparedStatement.setLong( count++, custNbr_.longValue() );
        preparedStatement.setLong( count++, custNbr_.longValue() );
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
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtDAO#list(java.math.BigInteger,
   *      java.math.BigInteger)
   */
  public DataSet list( BigInteger prodAcctCode_, BigInteger prodUnderAcctCode_ )
                                                                                throws UnexpectedException
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
      query.append( C_ALIAS_BROKER + "." + C_BKR_CNPJ_NBR + ", " );
      query.append( C_ALIAS_BROKER + "." + C_BKR_NAME_TEXT + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_BOVESPA_CUR_ACCT_NBR + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_BOVESPA_INVST_ACCT_NBR + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_BMF_CUR_ACCT_NBR + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_BMF_INVST_ACCT_NBR + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_PROD_ACCT_CODE + ", " );
      query.append( C_ALIAS_PORTF_MGMT + "." + C_PROD_UNDER_ACCT_CODE );
      query.append( " FROM " );
      query.append( C_TABLE_BROKER + " " + C_ALIAS_BROKER + ", " );
      query.append( C_TABLE_BKR_PORTF_MGMT + " " + C_ALIAS_PORTF_MGMT );
      query.append( " WHERE " );

      String criteria = "";

      criteria = criteria + C_ALIAS_PORTF_MGMT + "." + C_REC_STAT_CODE
                 + " != '" + BaseTplBkrPortfMgmtEntity.C_REC_STAT_CODE_INACTIVE
                 + "'" + " AND " + C_ALIAS_PORTF_MGMT + "." + C_BKR_CNPJ_NBR
                 + " = " + C_ALIAS_BROKER + "." + C_BKR_CNPJ_NBR + " AND ";

      if ( prodAcctCode_ != null && prodAcctCode_.longValue() != 0 )
      {
        criteria = criteria + C_ALIAS_PORTF_MGMT + "." + C_PROD_ACCT_CODE
                   + " = ? AND ";
      }
      if ( prodUnderAcctCode_ != null && prodUnderAcctCode_.longValue() != 0 )
      {
        criteria = criteria + C_ALIAS_PORTF_MGMT + "." + C_PROD_UNDER_ACCT_CODE
                   + " = ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        criteria = criteria + " ORDER BY " + C_ALIAS_BROKER + "."
                   + C_BKR_NAME_TEXT;
        query.append( criteria );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( prodAcctCode_ != null && prodAcctCode_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, prodAcctCode_.longValue() );
      }
      if ( prodUnderAcctCode_ != null && prodUnderAcctCode_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, prodUnderAcctCode_.longValue() );
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

  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
                                                                    throws UnexpectedException
  {
    TplBkrPortfMgmtEntityVO tplBkrPortfMgmtEntityVO;
    TplBkrPortfMgmtEntity tplBkrPortfMgmtEntity;
    ArrayList tplBkrPortfMgmtEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplBkrPortfMgmtEntity = new TplBkrPortfMgmtEntity();

        tplBkrPortfMgmtEntity.getData().setProdAcctCode(
                                                         new BigInteger(
                                                                         resultSet_.getString( C_PROD_ACCT_CODE ) ) );
        tplBkrPortfMgmtEntity.getData().setProdUnderAcctCode(
                                                              new BigInteger(
                                                                              resultSet_.getString( C_PROD_UNDER_ACCT_CODE ) ) );
        tplBkrPortfMgmtEntity.getData().setBkrCnpjNbr(
                                                       resultSet_.getString( C_BKR_CNPJ_NBR ) );
        tplBkrPortfMgmtEntity.getData().setBovespaCurAcctNbr(
                                                              resultSet_.getString( C_BOVESPA_CUR_ACCT_NBR ) );
        tplBkrPortfMgmtEntity.getData().setBovespaInvstAcctNbr(
                                                                resultSet_.getString( C_BOVESPA_INVST_ACCT_NBR ) );
        tplBkrPortfMgmtEntity.getData().setBmfCurAcctNbr(
                                                          resultSet_.getString( C_BMF_CUR_ACCT_NBR ) );
        tplBkrPortfMgmtEntity.getData().setBmfInvstAcctNbr(
                                                            resultSet_.getString( C_BMF_INVST_ACCT_NBR ) );
        tplBkrPortfMgmtEntity.getData().setLastUpdDate(
                                                        resultSet_.getDate( C_LAST_UPD_DATE ) );
        tplBkrPortfMgmtEntity.getData().setLastUpdUserId(
                                                          resultSet_.getString( C_LAST_UPD_USER_ID ) );

        // Casting para a atribuição das colunas específicas
        tplBkrPortfMgmtEntityVO = ( TplBkrPortfMgmtEntityVO ) tplBkrPortfMgmtEntity.getData();

        tplBkrPortfMgmtEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );
        tplBkrPortfMgmtEntityVO.setLastAuthDate( resultSet_.getDate( C_LAST_AUTH_DATE ) );
        tplBkrPortfMgmtEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );
        tplBkrPortfMgmtEntities.add( tplBkrPortfMgmtEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return tplBkrPortfMgmtEntities;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtDAO#delete(com.citibank.ods.entity.pl.TplBkrPortfMgmtEntity)
   */
  public void delete( TplBkrPortfMgmtEntity tplBkrPorftMgmtEntity_ )
                                                                    throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " UPDATE " + C_TABLE_BKR_PORTF_MGMT );
      query.append( " SET " );
      query.append( C_REC_STAT_CODE + " = ?" );
      query.append( " WHERE " + C_BKR_CNPJ_NBR + " = ? " );
      query.append( " AND " + C_PROD_ACCT_CODE + " = ? " );
      query.append( " AND " + C_PROD_UNDER_ACCT_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString( 1, BaseODSEntity.C_REC_STAT_CODE_INACTIVE );

      preparedStatement.setString( 2, tplBkrPorftMgmtEntity_.getData().getBkrCnpjNbr() );
      preparedStatement.setLong(
                         3,
                         tplBkrPorftMgmtEntity_.getData().getProdAcctCode().longValue() );

      preparedStatement.setLong(
                         4,
                         tplBkrPorftMgmtEntity_.getData().getProdUnderAcctCode().longValue() );

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

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtDAO#existsInactive(com.citibank.ods.entity.pl.TplBkrPortfMgmtEntity)
   */
  public boolean existsInactive( TplBkrPortfMgmtEntity tplBkrPorftMgmtEntity_ )
                                                                               throws UnexpectedException
  {
    boolean exists = true;

    try
    {
      TplBkrPortfMgmtEntity tplBkrPortfMgmtEntity = ( TplBkrPortfMgmtEntity ) this.find( tplBkrPorftMgmtEntity_ );
      TplBkrPortfMgmtEntityVO tplBkrPortfMgmtEntityVO = ( TplBkrPortfMgmtEntityVO ) tplBkrPortfMgmtEntity.getData();
      if ( !TplBkrPortfMgmtEntity.C_REC_STAT_CODE_INACTIVE.equals( tplBkrPortfMgmtEntityVO.getRecStatCode() ) )
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

}