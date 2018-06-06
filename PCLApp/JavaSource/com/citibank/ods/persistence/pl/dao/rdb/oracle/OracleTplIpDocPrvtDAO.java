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
import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplIpDocPrvtEntity;
import com.citibank.ods.entity.pl.TplIpDocPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplIpDocPrvtEntityVO;
import com.citibank.ods.persistence.pl.dao.TplIpDocPrvtDAO;
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

public class OracleTplIpDocPrvtDAO extends BaseOracleTplIpDocPrvtDAO implements
    TplIpDocPrvtDAO
{

  public String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  public String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  public String C_REC_STAT_CODE = "REC_STAT_CODE";

  public String C_TPL_IP_DOC_PRVT = C_PL_SCHEMA + "TPL_PRMNT_INSTR_PRVT";

  public String C_TPL_IP_DOC_PRVT_MOV = C_PL_SCHEMA
                                        + "TPL_PRMNT_INSTR_PRVT_MOV";

  public String C_CUST_FULL_NAME_TEXT = "CUST_FULL_NAME_TEXT";

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocPrvtDAO#list()
   */
  public DataSet list( BigInteger custNbr_, BigInteger ipDocCode_,
                      String ipDocTextSrc_, String ipInvstCurAcctInd_, String custFullName_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet resultSetDataSet = null;
    StringBuffer query = new StringBuffer();
    String iPDocPrvt = "iPDocPrvt";
    String customerPrvt = "customerPrvt";

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " SELECT " );
      query.append( iPDocPrvt + "." + C_CUST_NBR + ", " );
      query.append( iPDocPrvt + "." + C_IP_DOC_CODE + ", " );
      query.append( iPDocPrvt + "." + C_IP_INVST_CUR_ACCT_IND + ", " );
      query.append( iPDocPrvt + "." + C_IP_DOC_TEXT + ", " );
      query.append( iPDocPrvt + "." + C_IP_INVST_CUR_ACCT_IND + ", " );
      query.append( customerPrvt + "." + C_CUST_FULL_NAME_TEXT );
      query.append( " FROM " );
      query.append( C_TPL_IP_DOC_PRVT + " " + iPDocPrvt + ", " );
      query.append( OracleTplCustomerPrvtDAO.C_TPL_CUSTOMER_PRVT + " "
                    + customerPrvt + " " );

      String criteria = "";

      if ( custNbr_ != null && custNbr_.longValue() != 0 )
      {
        criteria = criteria + "AND iPDocPrvt." + C_CUST_NBR + " = ? ";
      }

      if ( ipDocCode_ != null && ipDocCode_.longValue() != 0 )
      {
        criteria = criteria + "AND iPDocPrvt." + C_IP_DOC_CODE + " = ? ";
      }

      if ( ipDocTextSrc_ != null && !( ipDocTextSrc_.equals( "" ) ) )
      {
        criteria = criteria + "AND UPPER (iPDocPrvt." + C_IP_DOC_TEXT
                   + ") LIKE ( ? ) ";
      }

      if ( ipInvstCurAcctInd_ != null && !( ipInvstCurAcctInd_.equals( "" ) ) )
      {
        criteria = criteria + "AND UPPER (iPDocPrvt." + C_IP_INVST_CUR_ACCT_IND
                   + ") =  ? ";
      }
      
      if ( custFullName_ != null && !( custFullName_.equals( "" ) ) )
      {
        criteria = criteria + "AND UPPER (customerPrvt." + C_CUST_FULL_NAME_TEXT
        + ") LIKE ( ? ) ";
      }

      query.append( "WHERE customerPrvt.CUST_NBR = iPDocPrvt.CUST_NBR AND iPDocPrvt.REC_STAT_CODE != '"
                    + BaseTplIpDocPrvtEntity.C_REC_STAT_CODE_INACTIVE
                    + "'"
                    + criteria );

      query.append( " ORDER BY " + customerPrvt + "." + C_CUST_FULL_NAME_TEXT );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( custNbr_ != null && custNbr_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
      }

      if ( ipDocCode_ != null && ipDocCode_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, ipDocCode_.longValue() );
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

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocPrvtDAO#insert(com.citibank.ods.entity.pl.TplIpDocPrvtEntity)
   */
  public TplIpDocPrvtEntity insert( TplIpDocPrvtEntity tplIpDocPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_IP_DOC_PRVT + " (" );
      query.append( C_IP_DOC_CODE + ", " );
      query.append( C_IP_DOC_TEXT + ", " );
      query.append( C_IP_INVST_CUR_ACCT_IND + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_CUST_NBR );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplIpDocPrvtEntity_.getData().getIpDocCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocPrvtEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplIpDocPrvtEntity_.getData().getIpDocText() != null )
      {
        preparedStatement.setString( count++,
                             tplIpDocPrvtEntity_.getData().getIpDocText() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocPrvtEntity_.getData().getIpInvstCurAcctInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplIpDocPrvtEntity_.getData().getIpInvstCurAcctInd() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( ( ( TplIpDocPrvtEntityVO ) tplIpDocPrvtEntity_.getData() ).getLastAuthUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplIpDocPrvtEntityVO ) tplIpDocPrvtEntity_.getData() ).getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( ( ( TplIpDocPrvtEntityVO ) tplIpDocPrvtEntity_.getData() ).getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplIpDocPrvtEntityVO ) tplIpDocPrvtEntity_.getData() ).getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( tplIpDocPrvtEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplIpDocPrvtEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocPrvtEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplIpDocPrvtEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( ( ( TplIpDocPrvtEntityVO ) tplIpDocPrvtEntity_.getData() ).getRecStatCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplIpDocPrvtEntityVO ) tplIpDocPrvtEntity_.getData() ).getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( ( ( TplIpDocPrvtEntityVO ) tplIpDocPrvtEntity_.getData() ).getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           ( ( TplIpDocPrvtEntityVO ) tplIpDocPrvtEntity_.getData() ).getCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
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

    return tplIpDocPrvtEntity_;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocPrvtDAO#update(com.citibank.ods.entity.pl.TplIpDocPrvtEntity)
   */
  public void update( TplIpDocPrvtEntity tplIpDocPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_IP_DOC_PRVT + " SET " );
      query.append( C_IP_DOC_TEXT + " = ?, " );
      query.append( C_IP_INVST_CUR_ACCT_IND + " = ?, " );
      query.append( C_LAST_AUTH_USER_ID + " = ?, " );
      query.append( C_LAST_AUTH_DATE + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_REC_STAT_CODE + " = ? " );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + " = ? AND " );
      query.append( C_IP_DOC_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplIpDocPrvtEntity_.getData().getIpDocText() != null )
      {
        preparedStatement.setString( count++,
                             tplIpDocPrvtEntity_.getData().getIpDocText() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocPrvtEntity_.getData().getIpInvstCurAcctInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplIpDocPrvtEntity_.getData().getIpInvstCurAcctInd() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( ( ( TplIpDocPrvtEntityVO ) tplIpDocPrvtEntity_.getData() ).getLastAuthUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplIpDocPrvtEntityVO ) tplIpDocPrvtEntity_.getData() ).getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( ( ( TplIpDocPrvtEntityVO ) tplIpDocPrvtEntity_.getData() ).getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplIpDocPrvtEntityVO ) tplIpDocPrvtEntity_.getData() ).getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( tplIpDocPrvtEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplIpDocPrvtEntity_.getData().getLastUpdUserId() );
      }
      else

      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocPrvtEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplIpDocPrvtEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( ( ( TplIpDocPrvtEntityVO ) tplIpDocPrvtEntity_.getData() ).getRecStatCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplIpDocPrvtEntityVO ) tplIpDocPrvtEntity_.getData() ).getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( ( ( TplIpDocPrvtEntityVO ) tplIpDocPrvtEntity_.getData() ).getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           ( ( TplIpDocPrvtEntityVO ) tplIpDocPrvtEntity_.getData() ).getCustNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT );
      }

      if ( tplIpDocPrvtEntity_.getData().getIpDocCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocPrvtEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT );
      }

	  preparedStatement.replaceParametersInQuery(query.toString());
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

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocPrvtDAO#delete(com.citibank.ods.entity.pl.TplIpDocPrvtEntity)
   */
  public void delete( TplIpDocPrvtEntity tplIpDocPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " UPDATE " + C_TPL_IP_DOC_PRVT );
      query.append( " SET " );
      query.append( C_REC_STAT_CODE + "= ?" );
      query.append( " WHERE " + C_CUST_NBR + " = ? AND " );
      query.append( C_IP_DOC_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString( 1, BaseODSEntity.C_REC_STAT_CODE_INACTIVE );

      preparedStatement.setLong( 2,
                         tplIpDocPrvtEntity_.getData().getCustNbr().longValue() );

      preparedStatement.setLong(
                         2,
                         tplIpDocPrvtEntity_.getData().getIpDocCode().longValue() );

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
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocPrvtDAO#exists(com.citibank.ods.entity.pl.TplIpDocPrvtEntity)
   */
  public boolean exists( TplIpDocPrvtEntity tplIpDocPrvtEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tplIpDocPrvtEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocPrvtDAO#existsActive(com.citibank.ods.entity.pl.TplIpDocPrvtEntity)
   */
  public boolean existsActive( TplIpDocPrvtEntity tplIpDocPrvtEntity_ )
  {
    boolean exists = true;

    try
    {
      TplIpDocPrvtEntity tplIpDocPrvtEntity = ( TplIpDocPrvtEntity ) this.find( tplIpDocPrvtEntity_ );
      TplIpDocPrvtEntityVO tplIpDocPrvtEntityVO = ( TplIpDocPrvtEntityVO ) tplIpDocPrvtEntity.getData();
      if ( !BaseODSEntity.C_REC_STAT_CODE_ACTIVE.equals( tplIpDocPrvtEntityVO.getRecStatCode() ) )
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
   * 
   * @see com.citibank.ods.persistence.pl.dao.BaseTplIpDocPrvtDAO#find(com.citibank.ods.entity.pl.BaseTplIpDocPrvtEntity)
   */
  public BaseTplIpDocPrvtEntity find(
                                     BaseTplIpDocPrvtEntity baseTplIpDocPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    ArrayList tplIpDocPrvtEntities;
    BaseTplIpDocPrvtEntity tplIpDocPrvtEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );	  
      query.append( C_IP_DOC_CODE + ", " );
      query.append( C_IP_DOC_TEXT + ", " );
      query.append( C_IP_INVST_CUR_ACCT_IND + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_CUST_NBR );
      query.append( " FROM " + C_TPL_IP_DOC_PRVT );
      query.append( " WHERE " );
      //query.append( C_CUST_NBR + " = ? AND " );
      query.append( C_IP_DOC_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      /*if ( baseTplIpDocPrvtEntity_.getData().getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           ( ( TplIpDocPrvtEntityVO ) baseTplIpDocPrvtEntity_.getData() ).getCustNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT );
      }*/

      if ( baseTplIpDocPrvtEntity_.getData().getIpDocCode() != null )
      {
        preparedStatement.setLong(1,baseTplIpDocPrvtEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT );
      }

	  preparedStatement.replaceParametersInQuery(query.toString());
      resultSet = preparedStatement.executeQuery();

      tplIpDocPrvtEntities = instantiateFromResultSet( resultSet );

      if ( tplIpDocPrvtEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplIpDocPrvtEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplIpDocPrvtEntity = ( BaseTplIpDocPrvtEntity ) tplIpDocPrvtEntities.get( 0 );
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
    TplIpDocPrvtEntity tplIpDocPrvtEntity;
    TplIpDocPrvtEntityVO tplIpDocPrvtEntityVO;
    ArrayList tplIpDocPrvtEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplIpDocPrvtEntity = new TplIpDocPrvtEntity();

        tplIpDocPrvtEntity.getData().setIpDocCode(
                                                   new BigInteger(
                                                                   resultSet_.getString( C_IP_DOC_CODE ) ) );
        tplIpDocPrvtEntity.getData().setIpDocText(
                                                   resultSet_.getString( C_IP_DOC_TEXT ) );
        tplIpDocPrvtEntity.getData().setLastUpdDate(
                                                     resultSet_.getTimestamp( C_LAST_UPD_DATE ) );
        tplIpDocPrvtEntity.getData().setLastUpdUserId(
                                                       resultSet_.getString( C_LAST_UPD_USER_ID ) );
        tplIpDocPrvtEntity.getData().setCustNbr(
                                                 new BigInteger(
                                                                 resultSet_.getString( C_CUST_NBR ) ) );

        tplIpDocPrvtEntity.getData().setIpInvstCurAcctInd(
                                                           resultSet_.getString( C_IP_INVST_CUR_ACCT_IND ) );

        // Casting para a atribuicao das colunas especificas
        tplIpDocPrvtEntityVO = ( TplIpDocPrvtEntityVO ) tplIpDocPrvtEntity.getData();
        tplIpDocPrvtEntityVO.setLastAuthDate( resultSet_.getTimestamp( C_LAST_AUTH_DATE ) );
        tplIpDocPrvtEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );
        tplIpDocPrvtEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );

        tplIpDocPrvtEntities.add( tplIpDocPrvtEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return tplIpDocPrvtEntities;
  }

  public Integer getNextIpCode()
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    Integer nextVal = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "select PL.SQ_PRMNT_INSTR_PRVT_CODE.Nextval id from dual");

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      
	  preparedStatement.replaceParametersInQuery(query.toString());
      resultSet = preparedStatement.executeQuery();
	  

      if ( resultSet.next() )
      {
        nextVal = new Integer( resultSet.getInt( "id" ) );
      }

      return nextVal;
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

}