package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplDocTransferEntity;
import com.citibank.ods.entity.pl.TplDocTransferMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplDocTransferMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplDocTransferMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

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

public class OracleTplDocTransferMovDAO extends BaseOracleTplDocTransferDAO
    implements TplDocTransferMovDAO
{

  public String C_OPERN_CODE = "OPERN_CODE";

  public String C_TPL_DOC_TRANSFER_MOV = C_PL_SCHEMA
                                         + "TPL_PRMNT_INSTR_DATA_TRF_MOV";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplDocTransferMovDAO#update(com.citibank.ods.entity.pl.TplDocTransferMovEntity)
   */
  public void update( TplDocTransferMovEntity tplDocTransferMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_DOC_TRANSFER_MOV );
      query.append( " SET " );
      query.append( C_OWN_DEST_ACCT_IND + " = ?, " );
      query.append( C_TXN_TYPE_CODE + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_OPERN_CODE + " = ?, " );
      query.append( C_AGN_BANK_CODE + " = ?, " );
      query.append( C_BRCH_CODE + " = ?, " );
      query.append( C_CUR_ACCT_NBR + " = ? " );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + "= ? AND " );
      query.append( C_IP_DOC_CODE + "= ? AND " );
      query.append( C_DOC_TRANSFER_CODE + "= ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplDocTransferMovEntity_.getData().getOwnDestAcctInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplDocTransferMovEntity_.getData().getOwnDestAcctInd() );
      }
      else
      {
        preparedStatement.setString( count++, "" );

      }

      if ( tplDocTransferMovEntity_.getData().getTxnTypeCode() != null
           && tplDocTransferMovEntity_.getData().getTxnTypeCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferMovEntity_.getData().getTxnTypeCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferMovEntity_.getData().getLastUpdUserId() != null
           && !tplDocTransferMovEntity_.getData().getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplDocTransferMovEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplDocTransferMovEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplDocTransferMovEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( ( ( TplDocTransferMovEntityVO ) tplDocTransferMovEntity_.getData() ).getOpernCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplDocTransferMovEntityVO ) tplDocTransferMovEntity_.getData() ).getOpernCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplDocTransferMovEntity_.getData().getAgnBankCode() != null
           && tplDocTransferMovEntity_.getData().getAgnBankCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferMovEntity_.getData().getAgnBankCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferMovEntity_.getData().getBrchCode() != null
           && tplDocTransferMovEntity_.getData().getBrchCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferMovEntity_.getData().getBrchCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferMovEntity_.getData().getCurAcctNbr() != null
           && tplDocTransferMovEntity_.getData().getCurAcctNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferMovEntity_.getData().getCurAcctNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferMovEntity_.getData().getCustNbr() != null
           && tplDocTransferMovEntity_.getData().getCustNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferMovEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplDocTransferMovEntity_.getData().getIpDocCode() != null
           && tplDocTransferMovEntity_.getData().getIpDocCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferMovEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplDocTransferMovEntity_.getData().getDocTransferCode() != null
           && tplDocTransferMovEntity_.getData().getDocTransferCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferMovEntity_.getData().getDocTransferCode().longValue() );
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
   * @see com.citibank.ods.persistence.pl.dao.TplDocTransferMovDAO#delete(com.citibank.ods.entity.pl.TplDocTransferMovEntity)
   */
  public void delete( TplDocTransferMovEntity tplDocTransferMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();
    TplDocTransferMovEntityVO tplDocTransferMovEntityVO = ( TplDocTransferMovEntityVO ) tplDocTransferMovEntity_.getData() ;
    
    if (tplDocTransferMovEntityVO != null) {

    	try
    	{
    		connection = OracleODSDAOFactory.getConnection();
    		query.append( " DELETE FROM " + C_TPL_DOC_TRANSFER_MOV );
    		query.append( " WHERE " + C_CUST_NBR + " = ? AND " );
    		query.append( C_IP_DOC_CODE + " = ? " );
    		preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));


    		if (tplDocTransferMovEntityVO.getCustNbr() != null ) {
    			preparedStatement.setLong(
    					1,
    					tplDocTransferMovEntityVO.getCustNbr().longValue() );
    		} else {
    			preparedStatement.setLong( 1, 0 );
    		}

    		if (tplDocTransferMovEntityVO.getIpDocCode() != null ) {
    		preparedStatement.setLong(
    				2,
    				tplDocTransferMovEntityVO.getIpDocCode().longValue() );
    		} else {
    			preparedStatement.setLong( 2, 0 );
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
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplDocTransferMovDAO#insert(com.citibank.ods.entity.pl.TplDocTransferMovEntity)
   */
  public TplDocTransferMovEntity insert(
                                        TplDocTransferMovEntity tplDocTransferMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_DOC_TRANSFER_MOV + " (" );
      query.append( C_OWN_DEST_ACCT_IND + "," );
      query.append( C_TXN_TYPE_CODE + "," );
      query.append( C_LAST_UPD_USER_ID + "," );
      query.append( C_LAST_UPD_DATE + "," );
      query.append( C_AGN_BANK_CODE + "," );
      query.append( C_CUST_NBR + "," );
      query.append( C_BRCH_CODE + "," );
      query.append( C_IP_DOC_CODE + "," );
      query.append( C_DOC_TRANSFER_CODE + "," );
      query.append( C_CUR_ACCT_NBR + "," );
      query.append( C_OPERN_CODE + ",");
	  query.append( C_BENE_NAME_TEXT + ",");
	  query.append( C_BENE_CPF_CNPJ_NBR + ",");
	  query.append( C_BENE_ACCT_TYPE_CODE + ",");
	  query.append( C_BENE_MAIN_DEST_ACCT_IND );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString(count++,"N" );      

      preparedStatement.setLong(count++, 3);
      
      if ( tplDocTransferMovEntity_.getData().getLastUpdUserId() != null
           && !tplDocTransferMovEntity_.getData().getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplDocTransferMovEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplDocTransferMovEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplDocTransferMovEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( tplDocTransferMovEntity_.getData().getAgnBankCode() != null
           && tplDocTransferMovEntity_.getData().getAgnBankCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferMovEntity_.getData().getAgnBankCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferMovEntity_.getData().getCustNbr() != null
           && tplDocTransferMovEntity_.getData().getCustNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferMovEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferMovEntity_.getData().getBrchCode() != null
           && tplDocTransferMovEntity_.getData().getBrchCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferMovEntity_.getData().getBrchCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferMovEntity_.getData().getIpDocCode() != null
           && tplDocTransferMovEntity_.getData().getIpDocCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferMovEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferMovEntity_.getData().getDocTransferCode() != null
           && tplDocTransferMovEntity_.getData().getDocTransferCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferMovEntity_.getData().getDocTransferCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferMovEntity_.getData().getCurAcctNbr() != null
           && tplDocTransferMovEntity_.getData().getCurAcctNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferMovEntity_.getData().getCurAcctNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( ( ( TplDocTransferMovEntityVO ) tplDocTransferMovEntity_.getData() ).getOpernCode() != null
           && !( ( TplDocTransferMovEntityVO ) tplDocTransferMovEntity_.getData() ).getOpernCode().equals(
                                                                                                           "" ) )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplDocTransferMovEntityVO ) tplDocTransferMovEntity_.getData() ).getOpernCode() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }      
	  
      if ( tplDocTransferMovEntity_.getData().getBeneNameText() != null
              && !tplDocTransferMovEntity_.getData().getBeneNameText().equals( "" ) )
         {
           preparedStatement.setString(
                                count++,
                                tplDocTransferMovEntity_.getData().getBeneNameText() );
         }
         else
         {
           preparedStatement.setString( count++, "" );
         }
    
      if ( tplDocTransferMovEntity_.getData().getBeneCpfCnpjNbr() != null
              && !tplDocTransferMovEntity_.getData().getBeneCpfCnpjNbr().equals( "" ) )
         {
           preparedStatement.setString(
                                count++,
                                tplDocTransferMovEntity_.getData().getBeneCpfCnpjNbr() );
         }
         else
         {
           preparedStatement.setString( count++, "" );
         }

      if ( tplDocTransferMovEntity_.getData().getBeneAcctTypeCode() != null
              && !tplDocTransferMovEntity_.getData().getBeneAcctTypeCode().equals( "" ) )
         {
           preparedStatement.setString(
                                count++,
                                tplDocTransferMovEntity_.getData().getBeneAcctTypeCode() );
         }
         else
         {
           preparedStatement.setString( count++, "" );
         }
      
      if ( tplDocTransferMovEntity_.getData().getBeneMainDestAcctInd() != null
              && !tplDocTransferMovEntity_.getData().getBeneMainDestAcctInd().equals( "" ) )
         {
           preparedStatement.setString(
                                count++,
                                tplDocTransferMovEntity_.getData().getBeneMainDestAcctInd() );
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

    return tplDocTransferMovEntity_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplDocTransferMovDAO#exists(com.citibank.ods.entity.pl.TplDocTransferMovEntity)
   */
  public boolean exists( TplDocTransferMovEntity tplDocTransferMovEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tplDocTransferMovEntity_ );
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
      query.append( C_LAST_UPD_USER_ID + "," );
      query.append( C_LAST_UPD_DATE + "," );
      query.append( C_AGN_BANK_CODE + "," );
      query.append( C_CUST_NBR + "," );
      query.append( C_BRCH_CODE + "," );
      query.append( C_DOC_TRANSFER_CODE + "," );
      query.append( C_IP_DOC_CODE + "," );
      query.append( C_CUR_ACCT_NBR + "," );
      query.append( C_OPERN_CODE + ", ");
	  query.append( C_BENE_ACCT_TYPE_CODE + ", ");
	  query.append( C_BENE_CPF_CNPJ_NBR + ", ");
	  query.append( C_BENE_MAIN_DEST_ACCT_IND + ", ");
	  query.append( C_BENE_NAME_TEXT);
      query.append( " FROM " );
      query.append( C_TPL_DOC_TRANSFER_MOV );
      query.append( " WHERE " + C_CUST_NBR + " = ? AND " );
      query.append( C_IP_DOC_CODE + " = ? AND " );
      query.append( C_DOC_TRANSFER_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         ( ( TplDocTransferMovEntityVO ) baseTplDocTransferEntity_.getData() ).getCustNbr().longValue() );

      preparedStatement.setLong(
                         2,
                         ( ( TplDocTransferMovEntityVO ) baseTplDocTransferEntity_.getData() ).getIpDocCode().longValue() );

      preparedStatement.setLong(
                         3,
                         ( ( TplDocTransferMovEntityVO ) baseTplDocTransferEntity_.getData() ).getDocTransferCode().longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

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

    return tplDocTransferEntity;
  }

  /*
   * Retorna uma coleção de entities a partir do rs
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {

    TplDocTransferMovEntity tplDocTransferMovEntity;
    TplDocTransferMovEntityVO tplDocTransferMovEntityVO;
    ArrayList tplDocTransferEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplDocTransferMovEntity = new TplDocTransferMovEntity();

        tplDocTransferMovEntity.getData().setOwnDestAcctInd(
                                                             resultSet_.getString( C_OWN_DEST_ACCT_IND ) != null
                                                                                                                ? ( resultSet_.getString( C_OWN_DEST_ACCT_IND ) )
                                                                                                                : null );
        tplDocTransferMovEntity.getData().setTxnTypeCode(
                                                          resultSet_.getString( C_TXN_TYPE_CODE ) != null
                                                                                                         ? new BigInteger(
                                                                                                                           resultSet_.getString( C_TXN_TYPE_CODE ) )
                                                                                                         : null );
        tplDocTransferMovEntity.getData().setLastUpdUserId(
                                                            resultSet_.getString( C_LAST_UPD_USER_ID ) != null
                                                                                                              ? ( resultSet_.getString( C_LAST_UPD_USER_ID ) )
                                                                                                              : null );
        tplDocTransferMovEntity.getData().setLastUpdDate(
                                                          resultSet_.getTimestamp( C_LAST_UPD_DATE ) != null
                                                                                                       ? ( resultSet_.getTimestamp( C_LAST_UPD_DATE ) )
                                                                                                       : null );
        tplDocTransferMovEntity.getData().setAgnBankCode(
                                                          resultSet_.getString( C_AGN_BANK_CODE ) != null
                                                                                                         ? new BigInteger(
                                                                                                                           resultSet_.getString( C_AGN_BANK_CODE ) )
                                                                                                         : null );
        tplDocTransferMovEntity.getData().setCustNbr(
                                                      resultSet_.getString( C_CUST_NBR ) != null
                                                                                                ? new BigInteger(
                                                                                                                  resultSet_.getString( C_CUST_NBR ) )
                                                                                                : null );
        tplDocTransferMovEntity.getData().setBrchCode(
                                                       resultSet_.getString( C_BRCH_CODE ) != null
                                                                                                  ? new BigInteger(
                                                                                                                    resultSet_.getString( C_BRCH_CODE ) )
                                                                                                  : null );
        tplDocTransferMovEntity.getData().setIpDocCode(
                                                        resultSet_.getString( C_IP_DOC_CODE ) != null
                                                                                                     ? new BigInteger(
                                                                                                                       resultSet_.getString( C_IP_DOC_CODE ) )
                                                                                                     : null );
        tplDocTransferMovEntity.getData().setCurAcctNbr(
                                                         resultSet_.getString( C_CUR_ACCT_NBR ) != null
                                                                                                       ? new BigInteger(
                                                                                                                         resultSet_.getString( C_CUR_ACCT_NBR ) )
                                                                                                       : null );
        tplDocTransferMovEntity.getData().setDocTransferCode(
                                                              resultSet_.getString( C_DOC_TRANSFER_CODE ) != null
                                                                                                                 ? new BigInteger(
                                                                                                                                   resultSet_.getString( C_DOC_TRANSFER_CODE ) )
                                                                                                                 : null );
                                                                                                                 
		tplDocTransferMovEntity.getData().setBeneAcctTypeCode(resultSet_.getString( C_BENE_ACCT_TYPE_CODE ) != null ? resultSet_.getString( C_BENE_ACCT_TYPE_CODE ) : null );
		
		tplDocTransferMovEntity.getData().setBeneCpfCnpjNbr(resultSet_.getString( C_BENE_CPF_CNPJ_NBR ) != null ? resultSet_.getString( C_BENE_CPF_CNPJ_NBR ) : null );
		
		tplDocTransferMovEntity.getData().setBeneMainDestAcctInd(resultSet_.getString( C_BENE_MAIN_DEST_ACCT_IND ) != null ? resultSet_.getString( C_BENE_MAIN_DEST_ACCT_IND ) : null );
		
		tplDocTransferMovEntity.getData().setBeneNameText(resultSet_.getString( C_BENE_NAME_TEXT ) != null ? resultSet_.getString( C_BENE_NAME_TEXT ) : null );
                                                                                                                 

        // Casting para a atribuicao das colunas especificas

        tplDocTransferMovEntityVO = ( TplDocTransferMovEntityVO ) tplDocTransferMovEntity.getData();
        tplDocTransferMovEntityVO.setOpernCode( resultSet_.getString( C_OPERN_CODE ) != null
                                                                                            ? resultSet_.getString( C_OPERN_CODE )
                                                                                            : null );

        tplDocTransferEntities.add( tplDocTransferMovEntity );
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
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_AGN_BANK_CODE + ", " );
      query.append( C_DOC_TRANSFER_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_BRCH_CODE + ", " );
      query.append( C_IP_DOC_CODE + ", " );
      query.append( C_CUR_ACCT_NBR + ", " );
      query.append( C_OPERN_CODE + ", ");
	  query.append( C_BENE_ACCT_TYPE_CODE + ", ");
	  query.append( C_BENE_CPF_CNPJ_NBR + ", ");
	  query.append( C_BENE_MAIN_DEST_ACCT_IND + ", ");
	  query.append( C_BENE_NAME_TEXT);
      query.append( " FROM " );
      query.append( C_TPL_DOC_TRANSFER_MOV );
      query.append( " WHERE " + C_CUST_NBR + " = ? AND " );
      query.append( C_IP_DOC_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, custNbr_.longValue() );

      preparedStatement.setLong( 2, ipDocCode_.longValue() );

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

}