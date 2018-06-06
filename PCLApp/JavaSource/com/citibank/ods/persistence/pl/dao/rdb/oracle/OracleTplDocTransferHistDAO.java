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
import com.citibank.ods.entity.pl.TplDocTransferHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplDocTransferEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplDocTransferHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplDocTransferHistDAO;
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

public class OracleTplDocTransferHistDAO extends BaseOracleTplDocTransferDAO
    implements TplDocTransferHistDAO

{

  public String C_DOC_TRF_REF_DATE = "PRMNT_INSTR_TRF_REF_DATE";

  public String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  public String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  public String C_REC_STAT_CODE = "REC_STAT_CODE";

  public String C_TPL_DOC_TRANSFER_HIST = C_PL_SCHEMA
                                          + "TPL_PRMNT_INSTR_DATA_TRF_HIST";

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplDocTransferHistDAO#insert(com.citibank.ods.entity.pl.TplDocTransferHistEntity)
   */
  public TplDocTransferHistEntity insert(
                                         TplDocTransferHistEntity tplDocTransferHistEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_DOC_TRANSFER_HIST + " (" );
      query.append( C_AGN_BANK_CODE + ", " );
      query.append( C_BRCH_CODE + ", " );
      query.append( C_CUR_ACCT_NBR + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_DOC_TRANSFER_CODE + ", " );
      query.append( C_DOC_TRF_REF_DATE + ", " );
      query.append( C_IP_DOC_CODE + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_OWN_DEST_ACCT_IND + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_TXN_TYPE_CODE + ", ");
	  query.append( C_BENE_NAME_TEXT + ", ");
	  query.append( C_BENE_CPF_CNPJ_NBR + ", ");
	  query.append( C_BENE_ACCT_TYPE_CODE + ", ");
	  query.append( C_BENE_MAIN_DEST_ACCT_IND);
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? , ?)" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplDocTransferHistEntity_.getData().getAgnBankCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferHistEntity_.getData().getAgnBankCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferHistEntity_.getData().getBrchCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferHistEntity_.getData().getBrchCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferHistEntity_.getData().getCurAcctNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferHistEntity_.getData().getCurAcctNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferHistEntity_.getData().getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferHistEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplDocTransferHistEntity_.getData().getDocTransferCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferHistEntity_.getData().getDocTransferCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( ( ( TplDocTransferHistEntityVO ) tplDocTransferHistEntity_.getData() ).getDocTrfRefDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplDocTransferHistEntityVO ) tplDocTransferHistEntity_.getData() ).getDocTrfRefDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( tplDocTransferHistEntity_.getData().getIpDocCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferHistEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( ( ( TplDocTransferHistEntityVO ) tplDocTransferHistEntity_.getData() ).getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplDocTransferHistEntityVO ) tplDocTransferHistEntity_.getData() ).getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( ( ( TplDocTransferHistEntityVO ) tplDocTransferHistEntity_.getData() ).getLastAuthUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplDocTransferHistEntityVO ) tplDocTransferHistEntity_.getData() ).getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplDocTransferHistEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplDocTransferHistEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( tplDocTransferHistEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplDocTransferHistEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplDocTransferHistEntity_.getData().getOwnDestAcctInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplDocTransferHistEntity_.getData().getOwnDestAcctInd() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( ( ( TplDocTransferHistEntityVO ) tplDocTransferHistEntity_.getData() ).getRecStatCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplDocTransferHistEntityVO ) tplDocTransferHistEntity_.getData() ).getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplDocTransferHistEntity_.getData().getTxnTypeCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplDocTransferHistEntity_.getData().getTxnTypeCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }
      
	  if ( tplDocTransferHistEntity_.getData().getBeneNameText() != null )
	  {
	 	  preparedStatement.setString(count++,tplDocTransferHistEntity_.getData().getBeneNameText() );
	  }
	  else
	  {
		  preparedStatement.setString( count++, "" );
	  }
	  
	  if ( tplDocTransferHistEntity_.getData().getBeneCpfCnpjNbr() != null )
	  {
		preparedStatement.setString(count++,tplDocTransferHistEntity_.getData().getBeneCpfCnpjNbr() );
	  }
	  else
	  {
		preparedStatement.setString( count++, "" );
	  }
	  
	  if ( tplDocTransferHistEntity_.getData().getBeneAcctTypeCode() != null )
	  {
	    preparedStatement.setString(count++,tplDocTransferHistEntity_.getData().getBeneAcctTypeCode() );
	  }
	  else
	  {
	    preparedStatement.setString( count++, "" );
	  }
	  
	  if ( tplDocTransferHistEntity_.getData().getBeneMainDestAcctInd() != null )
	  {
	    preparedStatement.setString(count++,tplDocTransferHistEntity_.getData().getBeneMainDestAcctInd() );
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

    return tplDocTransferHistEntity_;

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplDocTransferHistDAO#find(com.citibank.ods.entity.pl.TplDocTransferHistEntity)
   */
  public BaseTplDocTransferEntity find(
                                       BaseTplDocTransferEntity baseTplDocTransferHistEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    ArrayList tplDocTransferHistEntities;
    BaseTplDocTransferEntity tplDocTransferEntity = null;

    TplDocTransferHistEntity tplDocTransferHistEntity_ = ( TplDocTransferHistEntity ) baseTplDocTransferHistEntity_;

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
      query.append( C_CUR_ACCT_NBR + "," );
      query.append( C_DOC_TRF_REF_DATE );
      query.append( " FROM " );
      query.append( C_TPL_DOC_TRANSFER_HIST );
      query.append( " WHERE " + C_CUST_NBR + " = ? AND " );
      query.append( C_IP_DOC_CODE + " = ? AND " );
      query.append( C_DOC_TRANSFER_CODE + " = ? AND " );
      query.append( C_DOC_TRF_REF_DATE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         ( ( TplDocTransferEntityVO ) tplDocTransferHistEntity_.getData() ).getCustNbr().longValue() );

      preparedStatement.setLong(
                         2,
                         ( ( TplDocTransferEntityVO ) tplDocTransferHistEntity_.getData() ).getIpDocCode().longValue() );

      preparedStatement.setLong(
                         3,
                         ( ( TplDocTransferEntityVO ) tplDocTransferHistEntity_.getData() ).getDocTransferCode().longValue() );

      preparedStatement.setTimestamp(
                              4,
                              new Timestamp(
                                             ( ( TplDocTransferHistEntityVO ) tplDocTransferHistEntity_.getData() ).getDocTrfRefDate().getTime() ) );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      tplDocTransferHistEntities = instantiateFromResultSet( resultSet );

      if ( tplDocTransferHistEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplDocTransferHistEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplDocTransferEntity = ( TplDocTransferHistEntity ) tplDocTransferHistEntities.get( 0 );
      }

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

    return ( TplDocTransferHistEntity ) tplDocTransferEntity;
  }

  /*
   * Retorna uma coleção de entities a partir do rs
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {

    TplDocTransferHistEntity tplDocTransferHistEntity;
    ArrayList tplDocTransferEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplDocTransferHistEntity = new TplDocTransferHistEntity();

        tplDocTransferHistEntity.getData().setOwnDestAcctInd(
                                                              ( resultSet_.getString( C_OWN_DEST_ACCT_IND ) ) );
        tplDocTransferHistEntity.getData().setTxnTypeCode(
                                                           new BigInteger(
                                                                           resultSet_.getString( C_TXN_TYPE_CODE ) ) );
        tplDocTransferHistEntity.getData().setLastUpdUserId(
                                                             ( resultSet_.getString( C_LAST_UPD_USER_ID ) ) );
        tplDocTransferHistEntity.getData().setLastUpdDate(
                                                           ( resultSet_.getDate( C_LAST_UPD_DATE ) ) );

        tplDocTransferHistEntity.getData().setAgnBankCode(
                                                           new BigInteger(
                                                                           resultSet_.getString( C_AGN_BANK_CODE ) ) );
        tplDocTransferHistEntity.getData().setCustNbr(
                                                       new BigInteger(
                                                                       resultSet_.getString( C_CUST_NBR ) ) );
        tplDocTransferHistEntity.getData().setBrchCode(
                                                        new BigInteger(
                                                                        resultSet_.getString( C_BRCH_CODE ) ) );
        tplDocTransferHistEntity.getData().setIpDocCode(
                                                         new BigInteger(
                                                                         resultSet_.getString( C_IP_DOC_CODE ) ) );
        tplDocTransferHistEntity.getData().setCurAcctNbr(
                                                          new BigInteger(
                                                                          resultSet_.getString( C_CUR_ACCT_NBR ) ) );

        tplDocTransferHistEntity.getData().setDocTransferCode(
                                                               new BigInteger(
                                                                               resultSet_.getString( C_DOC_TRANSFER_CODE ) ) );

        // Casting para a atribuicao das colunas especificas

        TplDocTransferHistEntityVO tplDocTransferHistEntityVO = ( TplDocTransferHistEntityVO ) tplDocTransferHistEntity.getData();
        tplDocTransferHistEntityVO.setLastAuthDate( resultSet_.getDate( C_LAST_AUTH_DATE ) );
        tplDocTransferHistEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );
        tplDocTransferHistEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );
        tplDocTransferHistEntityVO.setDocTrfRefDate( resultSet_.getDate( C_DOC_TRF_REF_DATE ) );

        tplDocTransferEntities.add( tplDocTransferHistEntity );

      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return tplDocTransferEntities;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplDocTransferHistDAO#findByPK(java.math.BigInteger,
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
      query.append( C_CUST_NBR + ", " );
      query.append( C_DOC_TRANSFER_CODE + ", " );
      query.append( C_IP_DOC_CODE + ", " );
      query.append( C_DOC_TRF_REF_DATE + ", " );
      query.append( C_OWN_DEST_ACCT_IND + ", " );
      query.append( C_TXN_TYPE_CODE + ", " );
      query.append( C_AGN_BANK_CODE + ", " );
      query.append( C_BRCH_CODE + ", " );
      query.append( C_CUR_ACCT_NBR + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_DOC_TRANSFER_HIST );
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
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }

    return docTransferEntities;
  }

}