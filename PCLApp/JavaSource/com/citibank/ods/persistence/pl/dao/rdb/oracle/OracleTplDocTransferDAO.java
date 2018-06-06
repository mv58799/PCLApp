package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplDocTransferEntity;
import com.citibank.ods.entity.pl.TplDocTransferEntity;
import com.citibank.ods.entity.pl.valueobject.TplDocTransferEntityVO;
import com.citibank.ods.persistence.pl.dao.TplDocTransferDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 16, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class OracleTplDocTransferDAO extends BaseOracleTplDocTransferDAO
    implements TplDocTransferDAO
{

  public String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  public String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  public String C_REC_STAT_CODE = "REC_STAT_CODE";

  public String C_TPL_DOC_TRANSFER = C_PL_SCHEMA + "TPL_PRMNT_INSTR_DATA_TRF";

  public String C_TPL_DOC_TRANSFER_MOV = C_PL_SCHEMA
                                         + "TPL_PRMNT_INSTR_DATA_TRF_MOV";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplDocTransferDAO#list()
   */
  public DataSet list()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplDocTransferDAO#insert(com.citibank.ods.entity.pl.TplDocTransferEntity)
   */
  public TplDocTransferEntity insert( TplDocTransferEntity tplDocTransferEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_DOC_TRANSFER + " (" );
      query.append( C_OWN_DEST_ACCT_IND + "," );
      query.append( C_TXN_TYPE_CODE + "," );
      query.append( C_LAST_AUTH_USER_ID + "," );
      query.append( C_LAST_AUTH_DATE + "," );
      query.append( C_LAST_UPD_USER_ID + "," );
      query.append( C_LAST_UPD_DATE + "," );
      query.append( C_REC_STAT_CODE + "," );
      query.append( C_AGN_BANK_CODE + "," );
      query.append( C_CUST_NBR + "," );
      query.append( C_BRCH_CODE + "," );
      query.append( C_IP_DOC_CODE + "," );
      query.append( C_DOC_TRANSFER_CODE + "," );
      query.append( C_CUR_ACCT_NBR + "," );
	  query.append( C_BENE_ACCT_TYPE_CODE + ", ");
	  query.append( C_BENE_CPF_CNPJ_NBR + ", ");
	  query.append( C_BENE_MAIN_DEST_ACCT_IND + ", ");
	  query.append( C_BENE_NAME_TEXT);
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;
	  
      if ( tplDocTransferEntity_.getData().getOwnDestAcctInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplDocTransferEntity_.getData().getOwnDestAcctInd() );
      }
      else
      {
        preparedStatement.setString( count++, "" );

      }

      if ( tplDocTransferEntity_.getData().getTxnTypeCode() != null
           && tplDocTransferEntity_.getData().getTxnTypeCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferEntity_.getData().getTxnTypeCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( ( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getLastAuthUserId() != null
           && !( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getLastAuthUserId().equals(
                                                                                                          "" ) )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( ( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( ( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getLastUpdUserId() != null
           && !( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getLastUpdUserId().equals(
                                                                                                         "" ) )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplDocTransferEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplDocTransferEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( ( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getRecStatCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplDocTransferEntity_.getData().getAgnBankCode() != null
           && tplDocTransferEntity_.getData().getAgnBankCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferEntity_.getData().getAgnBankCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferEntity_.getData().getCustNbr() != null
           && tplDocTransferEntity_.getData().getCustNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferEntity_.getData().getBrchCode() != null
           && tplDocTransferEntity_.getData().getBrchCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferEntity_.getData().getBrchCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferEntity_.getData().getIpDocCode() != null
           && tplDocTransferEntity_.getData().getIpDocCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferEntity_.getData().getDocTransferCode() != null
           && tplDocTransferEntity_.getData().getDocTransferCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferEntity_.getData().getDocTransferCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferEntity_.getData().getCurAcctNbr() != null
           && tplDocTransferEntity_.getData().getCurAcctNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferEntity_.getData().getCurAcctNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }
      
	  if ( tplDocTransferEntity_.getData().getBeneAcctTypeCode() != null )
	  {
		preparedStatement.setString(count++,tplDocTransferEntity_.getData().getBeneAcctTypeCode() );
	  }
	  else
	  {
	  	preparedStatement.setString( count++, "" );
	  }
	  
	  if ( tplDocTransferEntity_.getData().getBeneCpfCnpjNbr() != null )
	  {
	    preparedStatement.setString(count++,tplDocTransferEntity_.getData().getBeneCpfCnpjNbr() );
	  }
	  else
	  {
	    preparedStatement.setString( count++, "" );
	  }
	  
	  if ( tplDocTransferEntity_.getData().getBeneMainDestAcctInd() != null )
	  {
	    preparedStatement.setString(count++,tplDocTransferEntity_.getData().getBeneMainDestAcctInd() );
	  }
	  else
	  {
	    preparedStatement.setString( count++, "" );
	  }
	  
	  if ( tplDocTransferEntity_.getData().getBeneNameText() != null )
	  {
	    preparedStatement.setString(count++,tplDocTransferEntity_.getData().getBeneNameText() );
	  }
	  else
	  {
	    preparedStatement.setString( count++, "" );
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

    return ( TplDocTransferEntity ) find( tplDocTransferEntity_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplDocTransferDAO#update(com.citibank.ods.entity.pl.TplDocTransferEntity)
   */
  public void update( TplDocTransferEntity tplDocTransferEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_DOC_TRANSFER );
      query.append( " SET " );
      query.append( C_OWN_DEST_ACCT_IND + " = ?, " );
      query.append( C_TXN_TYPE_CODE + " = ?, " );
      query.append( C_LAST_AUTH_USER_ID + " = ?, " );
      query.append( C_LAST_AUTH_DATE + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_REC_STAT_CODE + " = ?, " );
      query.append( C_AGN_BANK_CODE + " = ?, " );
      query.append( C_BRCH_CODE + " = ?, " );
      query.append( C_CUR_ACCT_NBR + " = ? " );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + "= ? AND " );
      query.append( C_IP_DOC_CODE + "= ? AND " );
      query.append( C_DOC_TRANSFER_CODE + "= ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplDocTransferEntity_.getData().getOwnDestAcctInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplDocTransferEntity_.getData().getOwnDestAcctInd() );
      }
      else
      {
        preparedStatement.setString( count++, "" );

      }

      if ( tplDocTransferEntity_.getData().getTxnTypeCode() != null
           && tplDocTransferEntity_.getData().getTxnTypeCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferEntity_.getData().getTxnTypeCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( ( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getLastAuthUserId() != null
           && !( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getLastAuthUserId().equals(
                                                                                                          "" ) )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( ( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( tplDocTransferEntity_.getData().getLastUpdUserId() != null
           && !tplDocTransferEntity_.getData().getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplDocTransferEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplDocTransferEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplDocTransferEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( ( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getRecStatCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplDocTransferEntity_.getData().getAgnBankCode() != null
           && tplDocTransferEntity_.getData().getAgnBankCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferEntity_.getData().getAgnBankCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferEntity_.getData().getBrchCode() != null
           && tplDocTransferEntity_.getData().getBrchCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferEntity_.getData().getBrchCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferEntity_.getData().getCurAcctNbr() != null
           && tplDocTransferEntity_.getData().getCurAcctNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferEntity_.getData().getCurAcctNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferEntity_.getData().getCustNbr() != null
           && tplDocTransferEntity_.getData().getCustNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplDocTransferEntity_.getData().getIpDocCode() != null
           && tplDocTransferEntity_.getData().getIpDocCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplDocTransferEntity_.getData().getDocTransferCode() != null
           && tplDocTransferEntity_.getData().getDocTransferCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferEntity_.getData().getDocTransferCode().longValue() );
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
   * @see com.citibank.ods.persistence.pl.dao.TplDocTransferDAO#delete(com.citibank.ods.entity.pl.TplDocTransferEntity)
   */
  public void delete( TplDocTransferEntity tplDocTransferEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " UPDATE " + C_TPL_DOC_TRANSFER );
      query.append( " SET " );
      query.append( C_REC_STAT_CODE + "= ?" );
      query.append( " WHERE " + C_CUST_NBR + " = ? AND " );
      query.append( C_IP_DOC_CODE + " = ? AND " );
      query.append( C_DOC_TRANSFER_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString( 1, BaseODSEntity.C_REC_STAT_CODE_INACTIVE );

      preparedStatement.setLong(
                         2,
                         ( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getCustNbr().longValue() );

      preparedStatement.setLong(
                         3,
                         ( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getIpDocCode().longValue() );

      preparedStatement.setLong(
                         4,
                         ( ( TplDocTransferEntityVO ) tplDocTransferEntity_.getData() ).getDocTransferCode().longValue() );

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
   * @see com.citibank.ods.persistence.pl.dao.TplDocTransferDAO#exists(com.citibank.ods.entity.pl.TplDocTransferEntity)
   */
  public boolean exists( TplDocTransferEntity tplDocTransferEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tplDocTransferEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplDocTransferDAO#existsActive(com.citibank.ods.entity.pl.TplDocTransferEntity)
   */
  public boolean existsActive( TplDocTransferEntity tplDocTransferEntity_ )
  {
    boolean exists = true;

    try
    {
      TplDocTransferEntity tplDocTransferEntity = ( TplDocTransferEntity ) this.find( tplDocTransferEntity_ );
      TplDocTransferEntityVO tplDocTransferEntityVO = ( TplDocTransferEntityVO ) tplDocTransferEntity.getData();
      if ( !TplDocTransferEntity.C_REC_STAT_CODE_ACTIVE.equals( tplDocTransferEntityVO.getRecStatCode() ) )
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
   * @see com.citibank.ods.persistence.pl.dao.BaseTplDocTransferDAO#find(com.citibank.ods.entity.pl.BaseTplDocTransferEntity)
   */
  public BaseTplDocTransferEntity find(
                                       BaseTplDocTransferEntity baseTplDocTransferEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    ArrayList tplDocTransferEntities;
    BaseTplDocTransferEntity tplDocTransferEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_OWN_DEST_ACCT_IND + "," );
      query.append( C_TXN_TYPE_CODE + "," );
      query.append( C_LAST_AUTH_USER_ID + "," );
      query.append( C_LAST_AUTH_DATE + "," );
      query.append( C_LAST_UPD_USER_ID + "," );
      query.append( C_LAST_UPD_DATE + "," );
      query.append( C_REC_STAT_CODE + "," );
      query.append( C_AGN_BANK_CODE + "," );
      query.append( C_DOC_TRANSFER_CODE + "," );
      query.append( C_CUST_NBR + "," );
      query.append( C_BRCH_CODE + "," );
      query.append( C_IP_DOC_CODE + "," );
      query.append( C_CUR_ACCT_NBR + ",");
	  query.append( C_BENE_ACCT_TYPE_CODE + ", ");
	  query.append( C_BENE_CPF_CNPJ_NBR + ", ");
	  query.append( C_BENE_MAIN_DEST_ACCT_IND + ", ");
	  query.append( C_BENE_NAME_TEXT);
      query.append( " FROM " );
      query.append( C_TPL_DOC_TRANSFER );
      query.append( " WHERE " + C_CUST_NBR + " = ? AND " );
      query.append( C_IP_DOC_CODE + " = ? AND " );
      query.append( C_DOC_TRANSFER_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         ( ( TplDocTransferEntityVO ) baseTplDocTransferEntity_.getData() ).getCustNbr().longValue() );

      preparedStatement.setLong(
                         2,
                         ( ( TplDocTransferEntityVO ) baseTplDocTransferEntity_.getData() ).getIpDocCode().longValue() );

      preparedStatement.setLong(
                         3,
                         ( ( TplDocTransferEntityVO ) baseTplDocTransferEntity_.getData() ).getDocTransferCode().longValue() );

	  preparedStatement.replaceParametersInQuery(query.toString());
      resultSet = preparedStatement.executeQuery();
	  

      tplDocTransferEntities = instantiateFromResultSet( resultSet );

      if ( tplDocTransferEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplDocTransferEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplDocTransferEntity = ( BaseTplDocTransferEntity ) tplDocTransferEntities.get( 0 );
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

    return tplDocTransferEntity;
  }

  /*
   * Retorna uma coleção de entities a partir do rs
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {

    TplDocTransferEntity tplDocTransferEntity;
    ArrayList tplDocTransferEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplDocTransferEntity = new TplDocTransferEntity();

        tplDocTransferEntity.getData().setOwnDestAcctInd(
                                                          ( resultSet_.getString( C_OWN_DEST_ACCT_IND ) ) );
        tplDocTransferEntity.getData().setTxnTypeCode(
                                                       new BigInteger(
                                                                       resultSet_.getString( C_TXN_TYPE_CODE ) ) );
        tplDocTransferEntity.getData().setLastUpdUserId(
                                                         ( resultSet_.getString( C_LAST_UPD_USER_ID ) ) );
        tplDocTransferEntity.getData().setLastUpdDate(
                                                       ( resultSet_.getTimestamp( C_LAST_UPD_DATE ) ) );

        tplDocTransferEntity.getData().setAgnBankCode(
                                                       new BigInteger(
                                                                       resultSet_.getString( C_AGN_BANK_CODE ) ) );
        tplDocTransferEntity.getData().setCustNbr(
                                                   new BigInteger(
                                                                   resultSet_.getString( C_CUST_NBR ) ) );
        tplDocTransferEntity.getData().setBrchCode(
                                                    new BigInteger(
                                                                    resultSet_.getString( C_BRCH_CODE ) ) );
        tplDocTransferEntity.getData().setIpDocCode(
                                                     new BigInteger(
                                                                     resultSet_.getString( C_IP_DOC_CODE ) ) );
        tplDocTransferEntity.getData().setCurAcctNbr(
                                                      new BigInteger(
                                                                      resultSet_.getString( C_CUR_ACCT_NBR ) ) );

        tplDocTransferEntity.getData().setDocTransferCode(
                                                           new BigInteger(
                                                                           resultSet_.getString( C_DOC_TRANSFER_CODE ) ) );
                                                                           
		tplDocTransferEntity.getData().setBeneAcctTypeCode(resultSet_.getString( C_BENE_ACCT_TYPE_CODE ) != null ? resultSet_.getString( C_BENE_ACCT_TYPE_CODE ) : null );
		
		tplDocTransferEntity.getData().setBeneCpfCnpjNbr(resultSet_.getString( C_BENE_CPF_CNPJ_NBR ) != null ? resultSet_.getString( C_BENE_CPF_CNPJ_NBR ) : null );
		
		tplDocTransferEntity.getData().setBeneMainDestAcctInd(resultSet_.getString( C_BENE_MAIN_DEST_ACCT_IND ) != null ? resultSet_.getString( C_BENE_MAIN_DEST_ACCT_IND ) : null );
		
		tplDocTransferEntity.getData().setBeneNameText(resultSet_.getString( C_BENE_NAME_TEXT ) != null ? resultSet_.getString( C_BENE_NAME_TEXT ) : null );                                                                           

        // Casting para a atribuicao das colunas especificas

        TplDocTransferEntityVO tplDocTransferEntityVO = ( TplDocTransferEntityVO ) tplDocTransferEntity.getData();
        tplDocTransferEntityVO.setLastAuthDate( resultSet_.getTimestamp( C_LAST_AUTH_DATE ) );
        tplDocTransferEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );
        tplDocTransferEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );

        tplDocTransferEntities.add( tplDocTransferEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return tplDocTransferEntities;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplDocTransferDAO#deleteAll(java.math.BigInteger,
   *      java.math.BigInteger)
   */
  public void deleteAll( BigInteger ipDocCode_, BigInteger custNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " UPDATE " + C_TPL_DOC_TRANSFER );
      query.append( " SET " );
      query.append( C_REC_STAT_CODE + "= ?" );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + " = ? AND " );
      query.append( C_IP_DOC_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString( 1, TplDocTransferEntity.C_REC_STAT_CODE_INACTIVE );
      preparedStatement.setLong( 2, custNbr_.longValue() );

      preparedStatement.setLong( 3, ipDocCode_.longValue() );

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
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplDocTransferDAO#findByPK(java.math.BigInteger,
   *      java.math.BigInteger)
   */
  public ArrayList findByPK( BigInteger ipDocCode_, BigInteger custNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList docTransferEntities;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_OWN_DEST_ACCT_IND + ", " );
      query.append( C_TXN_TYPE_CODE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_AGN_BANK_CODE + ", " );
      query.append( C_DOC_TRANSFER_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_BRCH_CODE + ", " );
      query.append( C_IP_DOC_CODE + ", " );
      query.append( C_CUR_ACCT_NBR + ", ");
	  query.append( C_BENE_ACCT_TYPE_CODE + ", ");
	  query.append( C_BENE_CPF_CNPJ_NBR + ", ");
	  query.append( C_BENE_MAIN_DEST_ACCT_IND + ", ");
	  query.append( C_BENE_NAME_TEXT);
      query.append( " FROM " );
      query.append( C_TPL_DOC_TRANSFER );
      query.append( " WHERE " + C_CUST_NBR + " = ? AND " );
      query.append( C_IP_DOC_CODE + " = ? AND " );
      query.append( C_REC_STAT_CODE + "<> ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, custNbr_.longValue() );

      preparedStatement.setLong( 2, ipDocCode_.longValue() );

      preparedStatement.setString( 3, TplDocTransferEntity.C_REC_STAT_CODE_INACTIVE );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      docTransferEntities = instantiateFromResultSet( resultSet );

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

    return docTransferEntities;
  }

  public Long getNextDocTransferCode()
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    Long nextVal = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT PL.SQ_PRMNT_INSTR_DATA_TRF_CODE.NEXTVAL FROM DUAL" );
      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.next() )
      {
        nextVal = new Long( resultSet.getLong( "NEXTVAL" ) );
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