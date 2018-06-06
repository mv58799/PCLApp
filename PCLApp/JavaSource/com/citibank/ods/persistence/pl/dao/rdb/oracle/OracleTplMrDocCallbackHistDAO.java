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
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplMrDocCallbackEntity;
import com.citibank.ods.entity.pl.TplMrDocCallbackHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplMrDocCallbackHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplMrDocCallbackHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados do histórico de Telefones de
 * Confirmação da Memória de Risco.
 */
public class OracleTplMrDocCallbackHistDAO extends
    BaseOracleTplMrDocCallbackDAO implements TplMrDocCallbackHistDAO
{
  // Data e Hora que o Usuario Aprovou o Registro Cadastrado.
  private static final String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  // Codigo do Usuario que Aprovou o Cadastro do Registro.
  private static final String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  // Codigo Documento MR
  private static final String C_MR_DOC_CODE = "PRVT_MR_CODE";

  // Status do Registro: 'S' (Ativo), 'N' (Inativo), 'A' (Aguardando
  // Aprovacao)
  private static final String C_REC_STAT_CODE = "REC_STAT_CODE";

  // Data de referência do histórico.
  private static final String C_MR_DOC_CALLBACK_REF_DATE = "MR_CALLBACK_REF_DATE";

  //Tabela TPL_MR_DOC_CALLBACK_HIST.
  private static final String C_TPL_MR_DOC_CALLBACK_HIST = C_PL_SCHEMA
                                                           + "TPL_MR_CALLBACK_HIST";

  // Tabela TPL_CONTACT_CUST
  private static final String C_TPL_CONTACT_CUST = C_PL_SCHEMA
                                                   + "TPL_CONTACT_CUST";

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplMrDocCallbackDAO#find(com.citibank.ods.entity.pl.BaseTplMrDocCallbackEntity)
   */
  public BaseTplMrDocCallbackEntity find(
                                         BaseTplMrDocCallbackEntity baseTplMrDocCallbackEntity_ )
  {
    return null;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplMrDocCallbackDAO#findAssociatedContactCustByPK(java.math.BigInteger,
   *      java.math.BigInteger, java.math.BigInteger)
   */
  public ArrayList findAssociatedContactCustByPK( BigInteger mrDocPrvt_,
                                                 BigInteger prodAcctCode_,
                                                 BigInteger prodUnderAcctCode_,
                                                 Date mrDocRefDate_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList contactCustEntities;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( "CUST.CTC_NBR" + ", " );
      query.append( "CUST.FULL_NAME_TEXT" + ", " );
      query.append( "CUST.PHONE_DDD_CODE" + ", " );
      query.append( "CUST.PHONE_NBR" + ", " );
      query.append( "CUST.PHONE_EXTN_NBR" + ", " );
      query.append( "CUST.LAST_AUTH_DATE" + ", " );
      query.append( "CUST.LAST_AUTH_USER_ID" + ", " );
      query.append( "CUST.LAST_UPD_DATE" + ", " );
      query.append( "CUST.LAST_UPD_USER_ID" + ", " );
      query.append( "CUST.REC_STAT_CODE" + ", " );
      query.append( "CUST.CUST_NBR" );
      query.append( " FROM " );
      query.append( C_TPL_MR_DOC_CALLBACK_HIST + " CALLBACK, " );
      query.append( C_TPL_CONTACT_CUST + " CUST " );
      query.append( " WHERE " );
      query.append( "CALLBACK." + C_MR_DOC_CODE + " = ? AND " );
      query.append( "CALLBACK." + C_PROD_ACCT_CODE + " = ? AND " );
      query.append( "CALLBACK." + C_PROD_UNDER_ACCT_CODE + " = ? AND " );
      query.append( "CALLBACK." + C_MR_DOC_CALLBACK_REF_DATE + " = ? AND " );
      query.append( "CALLBACK." + C_CUST_NBR + " = CUST." + C_CUST_NBR
                    + " AND " );
      query.append( "CALLBACK." + C_CTC_NBR + " = CUST." + C_CTC_NBR );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, mrDocPrvt_.longValue() );
      preparedStatement.setLong( 2, prodAcctCode_.longValue() );
      preparedStatement.setLong( 3, prodUnderAcctCode_.longValue() );
      preparedStatement.setTimestamp( 4, new Timestamp( mrDocRefDate_.getTime() ) );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      contactCustEntities = instantiateContactCustFromResultSet( resultSet );

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

    return contactCustEntities;

  }

  public void insert( TplMrDocCallbackHistEntity mrDocCallbackHistEntity_ )
  {
    TplMrDocCallbackHistEntityVO mrDocCallbackHistEntityVO = ( TplMrDocCallbackHistEntityVO ) mrDocCallbackHistEntity_.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_MR_DOC_CALLBACK_HIST + " ( " );
      query.append( C_MR_DOC_CODE + ", " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_MR_CALLBACK_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_CTC_NBR + ", " );
      query.append( C_MR_DOC_CALLBACK_REF_DATE );

      int attCount = 0;
      if ( mrDocCallbackHistEntityVO.getLastAuthUserId() != null
           && !mrDocCallbackHistEntityVO.getLastAuthUserId().equals( "" ) )
      {
        query.append( ", " + C_LAST_AUTH_USER_ID );
        attCount++;
      }

      if ( mrDocCallbackHistEntityVO.getLastUpdUserId() != null
           && !mrDocCallbackHistEntityVO.getLastUpdUserId().equals( "" ) )
      {
        query.append( ", " + C_LAST_UPD_USER_ID );
        attCount++;
      }

      if ( mrDocCallbackHistEntityVO.getLastAuthDate() != null )
      {
        query.append( ", " + C_LAST_AUTH_DATE );
        attCount++;
      }

      if ( mrDocCallbackHistEntityVO.getLastUpdDate() != null )
      {
        query.append( ", " + C_LAST_UPD_DATE );
        attCount++;
      }

      if ( mrDocCallbackHistEntityVO.getRecStatCode() != null
           && !mrDocCallbackHistEntityVO.getRecStatCode().equals( "" ) )
      {
        query.append( ", " + C_REC_STAT_CODE );
        attCount++;
      }

      query.append( " ) " );

      query.append( "VALUES ( ?, ?, ?, ?, ?, ?, ? " );

      for ( int i = 0; i < attCount; i++ )
      {
        query.append( ", ? " );
      }

      query.append( ") " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1,
                         mrDocCallbackHistEntityVO.getMrDocCode().longValue() );
      preparedStatement.setLong(
                         2,
                         mrDocCallbackHistEntityVO.getProdAcctCode().longValue() );
      preparedStatement.setLong(
                         3,
                         mrDocCallbackHistEntityVO.getProdUnderAcctCode().longValue() );
      preparedStatement.setLong(
                         4,
                         mrDocCallbackHistEntityVO.getMrCallbackCode().longValue() );
      preparedStatement.setLong( 5, mrDocCallbackHistEntityVO.getCustNbr().longValue() );
      preparedStatement.setLong( 6, mrDocCallbackHistEntityVO.getCtcNbr().longValue() );
      preparedStatement.setTimestamp(
                              7,
                              new Timestamp(
                                             mrDocCallbackHistEntityVO.getMrDocCallbackRefDate().getTime() ) );

      int index = 8;

      if ( mrDocCallbackHistEntityVO.getLastAuthUserId() != null
           && !mrDocCallbackHistEntityVO.getLastAuthUserId().equals( "" ) )
      {
        preparedStatement.setString( index++,
                             mrDocCallbackHistEntityVO.getLastAuthUserId() );
      }

      if ( mrDocCallbackHistEntityVO.getLastUpdUserId() != null
           && !mrDocCallbackHistEntityVO.getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString( index++,
                             mrDocCallbackHistEntityVO.getLastUpdUserId() );
      }

      if ( mrDocCallbackHistEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                index++,
                                new Timestamp(
                                               mrDocCallbackHistEntityVO.getLastAuthDate().getTime() ) );
      }

      if ( mrDocCallbackHistEntityVO.getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                index++,
                                new Timestamp(
                                               mrDocCallbackHistEntityVO.getLastUpdDate().getTime() ) );
      }

      if ( mrDocCallbackHistEntityVO.getRecStatCode() != null
           && !mrDocCallbackHistEntityVO.getRecStatCode().equals( "" ) )
      {
        preparedStatement.setString( index++,
                             mrDocCallbackHistEntityVO.getRecStatCode() );
      }

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
}