package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
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
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplIpDocPrvtEntity;
import com.citibank.ods.entity.pl.TplIpDocPrvtHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplIpDocPrvtHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplIpDocPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 12, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class OracleTplIpDocPrvtHistDAO extends BaseOracleTplIpDocPrvtDAO
    implements TplIpDocPrvtHistDAO
{

  public String C_IP_DOC_REF_DATE = "PRMNT_INSTR_REF_DATE";

  public String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  public String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  public String C_REC_STAT_CODE = "REC_STAT_CODE";

  public String C_TPL_IP_DOC_PRVT_HIST = C_PL_SCHEMA
                                         + "TPL_PRMNT_INSTR_PRVT_HIST";

  protected String C_CUST_FULL_NAME_TEXT = "CUST_FULL_NAME_TEXT";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocPrvtHistDAO#insert(com.citibank.ods.entity.pl.TplIpDocPrvtHistEntity)
   */
  public void insert( TplIpDocPrvtHistEntity tplIpDocPrvtHistEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_IP_DOC_PRVT_HIST + " (" );
      query.append( C_IP_DOC_CODE + ", " );
      query.append( C_IP_DOC_TEXT + ", " );
      query.append( C_IP_INVST_CUR_ACCT_IND + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_IP_DOC_REF_DATE + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplIpDocPrvtHistEntity_.getData().getIpDocCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocPrvtHistEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplIpDocPrvtHistEntity_.getData().getIpDocText() != null )
      {
        preparedStatement.setString( count++,
                             tplIpDocPrvtHistEntity_.getData().getIpDocText() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocPrvtHistEntity_.getData().getIpInvstCurAcctInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplIpDocPrvtHistEntity_.getData().getIpInvstCurAcctInd() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocPrvtHistEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplIpDocPrvtHistEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocPrvtHistEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplIpDocPrvtHistEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( tplIpDocPrvtHistEntity_.getData().getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocPrvtHistEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( ( ( TplIpDocPrvtHistEntityVO ) tplIpDocPrvtHistEntity_.getData() ).getIpDocRefDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplIpDocPrvtHistEntityVO ) tplIpDocPrvtHistEntity_.getData() ).getIpDocRefDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( ( ( TplIpDocPrvtHistEntityVO ) tplIpDocPrvtHistEntity_.getData() ).getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplIpDocPrvtHistEntityVO ) tplIpDocPrvtHistEntity_.getData() ).getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( ( ( TplIpDocPrvtHistEntityVO ) tplIpDocPrvtHistEntity_.getData() ).getLastAuthUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplIpDocPrvtHistEntityVO ) tplIpDocPrvtHistEntity_.getData() ).getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( ( ( TplIpDocPrvtHistEntityVO ) tplIpDocPrvtHistEntity_.getData() ).getRecStatCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplIpDocPrvtHistEntityVO ) tplIpDocPrvtHistEntity_.getData() ).getRecStatCode() );
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

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTplIpDocPrvtDAO#find(com.citibank.ods.entity.pl.BaseTplIpDocPrvtEntity)
   */
  public BaseTplIpDocPrvtEntity find(
                                     BaseTplIpDocPrvtEntity baseTplIpDocPrvtEntity_ )
  {

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    ArrayList tplIpDocPrvtHistEntities;
    BaseTplIpDocPrvtEntity tplIpDocPrvtEntity = null;

    String ipDocPrvtHistory = "ipDocPrvtHistory";
    String customerPrvt = "customerPrvt";

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( ipDocPrvtHistory + "." + C_CUST_NBR + ", " );
      query.append( customerPrvt + "."
                    + OracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT + ", " );
      query.append( ipDocPrvtHistory + "." + C_IP_DOC_CODE + ", " );
      query.append( ipDocPrvtHistory + "." + C_IP_DOC_TEXT + ", " );
      query.append( ipDocPrvtHistory + "." + C_IP_INVST_CUR_ACCT_IND + ", " );
      query.append( ipDocPrvtHistory + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( ipDocPrvtHistory + "." + C_LAST_UPD_DATE + ", " );
      query.append( ipDocPrvtHistory + "." + C_IP_DOC_REF_DATE + ", " );
      query.append( ipDocPrvtHistory + "." + C_LAST_AUTH_DATE + ", " );
      query.append( ipDocPrvtHistory + "." + C_LAST_AUTH_USER_ID + ", " );
      query.append( ipDocPrvtHistory + "." + C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_IP_DOC_PRVT_HIST + " " + ipDocPrvtHistory + ", " );
      query.append( OracleTplCustomerPrvtDAO.C_TPL_CUSTOMER_PRVT + " "
                    + customerPrvt );
      query.append( " WHERE " );
      query.append( ipDocPrvtHistory + "." + C_CUST_NBR + " = " + customerPrvt
                    + "." + C_CUST_NBR + " AND " );
      query.append( ipDocPrvtHistory + "." + C_CUST_NBR + " = ? AND " );
      query.append( ipDocPrvtHistory + "." + C_IP_DOC_CODE + " = ? AND " );
      query.append( ipDocPrvtHistory + "." + C_IP_DOC_REF_DATE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( baseTplIpDocPrvtEntity_.getData().getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           baseTplIpDocPrvtEntity_.getData().getCustNbr().longValue() );
      }

      if ( baseTplIpDocPrvtEntity_.getData().getIpDocCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           baseTplIpDocPrvtEntity_.getData().getIpDocCode().longValue() );
      }

      if ( ( ( TplIpDocPrvtHistEntityVO ) baseTplIpDocPrvtEntity_.getData() ).getIpDocRefDate() != null )
      {

        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplIpDocPrvtHistEntityVO ) baseTplIpDocPrvtEntity_.getData() ).getIpDocRefDate().getTime() ) );

      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      tplIpDocPrvtHistEntities = instantiateFromResultSet( resultSet );

      if ( tplIpDocPrvtHistEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplIpDocPrvtHistEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplIpDocPrvtEntity = ( BaseTplIpDocPrvtEntity ) tplIpDocPrvtHistEntities.get( 0 );
      }

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

    return tplIpDocPrvtEntity;

  }

  /*
   * Retorna uma coleção de entities a partir do rs
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {

    TplIpDocPrvtHistEntity tplIpDocPrvtHistEntity;
    TplIpDocPrvtHistEntityVO tplIpDocPrvtHistEntityVO;
    ArrayList tplIpDocPrvtHistEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplIpDocPrvtHistEntity = new TplIpDocPrvtHistEntity();

        tplIpDocPrvtHistEntity.getData().setIpDocCode(
                                                       new BigInteger(
                                                                       resultSet_.getString( C_IP_DOC_CODE ) ) );
        tplIpDocPrvtHistEntity.getData().setIpDocText(
                                                       resultSet_.getString( C_IP_DOC_TEXT ) );
        tplIpDocPrvtHistEntity.getData().setLastUpdDate(
                                                         resultSet_.getDate( C_LAST_UPD_DATE ) );
        tplIpDocPrvtHistEntity.getData().setLastUpdUserId(
                                                           resultSet_.getString( C_LAST_UPD_USER_ID ) );
        tplIpDocPrvtHistEntity.getData().setCustNbr(
                                                     new BigInteger(
                                                                     resultSet_.getString( C_CUST_NBR ) ) );

        tplIpDocPrvtHistEntity.getData().setIpInvstCurAcctInd(
                                                               resultSet_.getString( C_IP_INVST_CUR_ACCT_IND ) );

        // Casting para a atribuicao das colunas especificas
        tplIpDocPrvtHistEntityVO = ( TplIpDocPrvtHistEntityVO ) tplIpDocPrvtHistEntity.getData();
        tplIpDocPrvtHistEntityVO.setIpDocRefDate( resultSet_.getTimestamp( C_IP_DOC_REF_DATE ) );
        tplIpDocPrvtHistEntityVO.setLastAuthDate( resultSet_.getTimestamp( C_LAST_AUTH_DATE ) );
        tplIpDocPrvtHistEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );
        tplIpDocPrvtHistEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );

        tplIpDocPrvtHistEntities.add( tplIpDocPrvtHistEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return tplIpDocPrvtHistEntities;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocPrvtHistDAO#list(java.math.BigInteger,
   *      java.math.BigInteger, java.lang.String, java.lang.String,
   *      java.util.Date)
   */
  public DataSet list( BigInteger custNbrSrc_, BigInteger ipDocCodeSrc_,
                      String ipDocTextSrc_, String ipInvstCurAcctInd_,
                      Date ipDocRefDateSrc_, String custFullName_)
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet resultSetDataSet = null;
    StringBuffer query = new StringBuffer();
    String iPDocPrvtHist = "iPDocPrvtHist";
    String customerPrvt = "customerPrvt";

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " SELECT " );

      query.append( iPDocPrvtHist + "." + C_IP_DOC_CODE + ", " );
      query.append( iPDocPrvtHist + "." + C_IP_DOC_TEXT + ", " );
      query.append( iPDocPrvtHist + "." + C_CUST_NBR + ", " );
      query.append( customerPrvt + "." + C_CUST_FULL_NAME_TEXT + ", " );
      query.append( iPDocPrvtHist + "." + C_IP_DOC_REF_DATE );

      query.append( " FROM " );
      query.append( C_TPL_IP_DOC_PRVT_HIST + " " + iPDocPrvtHist + ", " );
      query.append( OracleTplCustomerPrvtDAO.C_TPL_CUSTOMER_PRVT + " "
                    + customerPrvt + " " );

      String criteria = "";

      if ( custNbrSrc_ != null && custNbrSrc_.longValue() != 0 )
      {
        criteria = criteria + "AND iPDocPrvtHist." + C_CUST_NBR + " = ? ";
      }

      if ( ipDocCodeSrc_ != null && ipDocCodeSrc_.longValue() != 0 )
      {
        criteria = criteria + "AND iPDocPrvtHist." + C_IP_DOC_CODE + " = ? ";
      }

      if ( ipDocTextSrc_ != null && !( ipDocTextSrc_.equals( "" ) ) )
      {
        criteria = criteria + "AND UPPER (iPDocPrvtHist." + C_IP_DOC_TEXT
                   + ") LIKE ( ? ) ";
      }

      if ( ipInvstCurAcctInd_ != null && !( ipInvstCurAcctInd_.equals( "" ) ) )
      {
        criteria = criteria + "AND UPPER (iPDocPrvtHist."
                   + C_IP_INVST_CUR_ACCT_IND + ") =  ? ";
      }

      if ( ipDocRefDateSrc_ != null
           && !( ipDocRefDateSrc_.toString().equals( "" ) ) )
      {
        criteria = criteria + "AND TRUNC(" + C_IP_DOC_REF_DATE + ") >= ? ";
      }
      
      if ( custFullName_ != null && !( custFullName_.equals( "" ) ) )
      {
        criteria = criteria + "AND UPPER (customerPrvt." + C_CUST_FULL_NAME_TEXT
                   + ") LIKE ( ? ) ";
      }


      query.append( "WHERE customerPrvt.CUST_NBR = iPDocPrvtHist.CUST_NBR "
                    + criteria );

      query.append( " ORDER BY customerPrvt." + C_CUST_FULL_NAME_TEXT + "," + C_IP_DOC_REF_DATE );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( custNbrSrc_ != null && custNbrSrc_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, custNbrSrc_.longValue() );
      }

      if ( ipDocCodeSrc_ != null && ipDocCodeSrc_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, ipDocCodeSrc_.longValue() );
      }

      if ( ipDocTextSrc_ != null && !( ipDocTextSrc_.equals( "" ) ) )
      {
        preparedStatement.setString( count++, "%" + ipDocTextSrc_.toUpperCase()
                                              + "%" );
      }

      if ( ipInvstCurAcctInd_ != null && !( ipInvstCurAcctInd_.equals( "" ) ) )
      {
        preparedStatement.setString( count++, ipInvstCurAcctInd_.toUpperCase() );
      }

      if ( ipDocRefDateSrc_ != null )
      {
        preparedStatement.setTimestamp(
                                        count++,
                                        new java.sql.Timestamp(
                                                                ipDocRefDateSrc_.getTime() ) );
      }
      
      if ( custFullName_ != null && !( custFullName_.equals( "" ) ) )
      {
        preparedStatement.setString( count++, "%" + custFullName_.toUpperCase()
                                              + "%" );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      resultSetDataSet = new ResultSetDataSet( resultSet );

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

    return resultSetDataSet;
  }

}