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
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplMrDocCallbackEntity;
import com.citibank.ods.entity.pl.TplMrDocCallbackEntity;
import com.citibank.ods.entity.pl.TplMrDocCallbackHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplMrDocCallbackEntityVO;
import com.citibank.ods.persistence.pl.dao.TplMrDocCallbackDAO;
import com.citibank.ods.persistence.pl.dao.TplMrDocCallbackHistDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de Telefones de Confirmação da
 * Memória de Risco.
 */
public class OracleTplMrDocCallbackDAO extends BaseOracleTplMrDocCallbackDAO
    implements TplMrDocCallbackDAO
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

  // Tabela TPL_MR_DOC_CALLBACK
  private static final String C_TPL_MR_DOC_CALLBACK = C_PL_SCHEMA
                                                      + "TPL_MR_CALLBACK";

  // Tabela TPL_CONTACT_CUST
  private static final String C_TPL_CONTACT_CUST = C_PL_SCHEMA
                                                   + "TPL_CONTACT_CUST";

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplMrDocCallbackDAO#find(com.citibank.ods.entity.pl.BaseTplMrDocCallbackEntity)
   */
  public BaseTplMrDocCallbackEntity find(
                                         BaseTplMrDocCallbackEntity mrDocCallbackEntity_ )
  {
    TplMrDocCallbackEntityVO mrDocCallbackEntityVO = ( TplMrDocCallbackEntityVO ) mrDocCallbackEntity_.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList mrDocCallbackEntities;
    BaseTplMrDocCallbackEntity mrDocCallbackEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "SELECT " );
      query.append( C_MR_CALLBACK_CODE + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_CTC_NBR + ", " );
      query.append( C_MR_DOC_CODE );
      query.append( " FROM " );
      query.append( C_TPL_MR_DOC_CALLBACK );
      query.append( " WHERE " );
      query.append( C_MR_DOC_CODE + " = ? AND " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? AND " );
      query.append( C_CUST_NBR + " = ? AND " );
      query.append( C_CTC_NBR + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, mrDocCallbackEntityVO.getMrDocCode().longValue() );
      preparedStatement.setLong( 2, mrDocCallbackEntityVO.getProdAcctCode().longValue() );
      preparedStatement.setLong(
                         3,
                         mrDocCallbackEntityVO.getProdUnderAcctCode().longValue() );
      preparedStatement.setLong( 4, mrDocCallbackEntityVO.getCustNbr().longValue() );
      preparedStatement.setLong( 5, mrDocCallbackEntityVO.getCtcNbr().longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      mrDocCallbackEntities = instantiateFromResultSet( resultSet );

      if ( mrDocCallbackEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( mrDocCallbackEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        mrDocCallbackEntity = ( BaseTplMrDocCallbackEntity ) mrDocCallbackEntities.get( 0 );
      }

      return mrDocCallbackEntity;
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
   * @see com.citibank.ods.persistence.pl.dao.BaseTplMrDocCallbackDAO#findAssociatedContactCustByPK(java.math.BigInteger,
   *      java.math.BigInteger, java.math.BigInteger)
   */
  public ArrayList findAssociatedContactCustByPK( BigInteger mrDocPrvt_,
                                                 BigInteger prodAcctCode_,
                                                 BigInteger prodUnderAcctCode_ )
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
	  query.append( "CUST.FULL_NAME_1_TEXT" + ", " );
	  query.append( "CUST.FULL_NAME_2_TEXT" + ", " );
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
      query.append( C_TPL_MR_DOC_CALLBACK + " CALLBACK, " );
      query.append( C_TPL_CONTACT_CUST + " CUST " );
      query.append( " WHERE " );
      query.append( "CALLBACK." + C_MR_DOC_CODE + " = ? AND " );
      query.append( "CALLBACK." + C_PROD_ACCT_CODE + " = ? AND " );
      query.append( "CALLBACK." + C_PROD_UNDER_ACCT_CODE + " = ? AND " );
      query.append( "CALLBACK." + C_REC_STAT_CODE + " <> ? AND " );
      query.append( "CALLBACK." + C_CUST_NBR + " = CUST." + C_CUST_NBR
                    + " AND " );
      query.append( "CALLBACK." + C_CTC_NBR + " = CUST." + C_CTC_NBR );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, mrDocPrvt_.longValue() );
      preparedStatement.setLong( 2, prodAcctCode_.longValue() );
      preparedStatement.setLong( 3, prodUnderAcctCode_.longValue() );
      preparedStatement.setString( 4, "I" );

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

  /**
   * Instancia entidades a partir do ResultSet.
   * 
   * @param resultSet_ - ResultSet utilizado para instanciar entidades.
   * @return ArrayList - Entidades instanciadas a partir do ResultSet.
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {

    TplMrDocCallbackEntityVO mrDocCallbackEntityVO;
    TplMrDocCallbackEntity mrDocCallbackEntity;
    ArrayList mrDocCallbackEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        mrDocCallbackEntity = new TplMrDocCallbackEntity();
        mrDocCallbackEntityVO = ( TplMrDocCallbackEntityVO ) mrDocCallbackEntity.getData();

        mrDocCallbackEntityVO.setCtcNbr( new BigInteger(
                                                         resultSet_.getString( C_CTC_NBR ) ) );

        mrDocCallbackEntityVO.setCustNbr( new BigInteger(
                                                          resultSet_.getString( C_CUST_NBR ) ) );

        mrDocCallbackEntityVO.setMrCallbackCode( new BigInteger(
                                                                 resultSet_.getString( C_MR_CALLBACK_CODE ) ) );

        mrDocCallbackEntityVO.setProdAcctCode( new BigInteger(
                                                               resultSet_.getString( C_PROD_ACCT_CODE ) ) );

        mrDocCallbackEntityVO.setProdUnderAcctCode( new BigInteger(
                                                                    resultSet_.getString( C_PROD_UNDER_ACCT_CODE ) ) );

        mrDocCallbackEntityVO.setMrDocCode( new BigInteger(
                                                            resultSet_.getString( C_MR_DOC_CODE ) ) );

        if ( resultSet_.getTimestamp( "LAST_AUTH_DATE" ) != null )
        {
          mrDocCallbackEntityVO.setLastAuthDate( resultSet_.getTimestamp( "LAST_AUTH_DATE" ) );
        }

        if ( resultSet_.getString( "LAST_AUTH_USER_ID" ) != null )
        {
          mrDocCallbackEntityVO.setLastAuthUserId( resultSet_.getString( "LAST_AUTH_USER_ID" ) );
        }

        if ( resultSet_.getTimestamp( "LAST_UPD_DATE" ) != null )
        {
          mrDocCallbackEntityVO.setLastUpdDate( resultSet_.getTimestamp( "LAST_UPD_DATE" ) );
        }

        if ( resultSet_.getString( "LAST_UPD_USER_ID" ) != null )
        {
          mrDocCallbackEntityVO.setLastUpdUserId( resultSet_.getString( "LAST_UPD_USER_ID" ) );
        }

        if ( resultSet_.getString( "REC_STAT_CODE" ) != null )
        {
          mrDocCallbackEntityVO.setRecStatCode( resultSet_.getString( "REC_STAT_CODE" ) );
        }

        mrDocCallbackEntities.add( mrDocCallbackEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return mrDocCallbackEntities;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocCallbackDAO#copyFromCurrentToHist(com.citibank.ods.entity.pl.BaseTplMrDocCallbackEntity,
   *      java.util.Date)
   */
  public void copyFromCurrentToHist(
                                    BaseTplMrDocCallbackEntity baseTplMrDocCallbackEntity_,
                                    Date mrDocRefDate_ )
  {
    BaseTplMrDocCallbackEntity mrDocCallbackEntity = find( baseTplMrDocCallbackEntity_ );
    TplMrDocCallbackHistEntity mrDocCallbackHistEntity = new TplMrDocCallbackHistEntity(
                                                                                         ( TplMrDocCallbackEntity ) mrDocCallbackEntity,
                                                                                         mrDocRefDate_ );

    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
    TplMrDocCallbackHistDAO mrDocCallbackHistDAO = odsDAOFactory.getTplMrDocCallbackHistDAO();
    mrDocCallbackHistDAO.insert( mrDocCallbackHistEntity );
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocCallbackDAO#update(com.citibank.ods.entity.pl.TplMrDocCallbackEntity)
   */
  public void update( TplMrDocCallbackEntity mrDocCallbackEntity_ )
  {
    TplMrDocCallbackEntityVO mrDocCallbackEntityVO = ( TplMrDocCallbackEntityVO ) mrDocCallbackEntity_.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " );
      query.append( C_TPL_MR_DOC_CALLBACK );
      query.append( " SET " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_LAST_AUTH_USER_ID + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_LAST_AUTH_DATE + " = ?, " );
      query.append( C_REC_STAT_CODE + " = ? " );
      query.append( " WHERE " );
      query.append( C_MR_DOC_CODE + " = ? AND " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? AND " );
      query.append( C_CUST_NBR + " = ? AND " );
      query.append( C_CTC_NBR + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString( 1, mrDocCallbackEntityVO.getLastUpdUserId() );

      preparedStatement.setString( 2, mrDocCallbackEntityVO.getLastAuthUserId() );

      preparedStatement.setTimestamp(
                              3,
                              new Timestamp(
                                             mrDocCallbackEntityVO.getLastUpdDate().getTime() ) );

      preparedStatement.setTimestamp(
                              4,
                              new Timestamp(
                                             mrDocCallbackEntityVO.getLastAuthDate().getTime() ) );

      preparedStatement.setString( 5, mrDocCallbackEntityVO.getRecStatCode() );

      preparedStatement.setLong( 6, mrDocCallbackEntityVO.getMrDocCode().longValue() );

      preparedStatement.setLong( 7, mrDocCallbackEntityVO.getProdAcctCode().longValue() );

      preparedStatement.setLong(
                         8,
                         mrDocCallbackEntityVO.getProdUnderAcctCode().longValue() );

      preparedStatement.setLong( 9, mrDocCallbackEntityVO.getCustNbr().longValue() );

      preparedStatement.setLong( 10, mrDocCallbackEntityVO.getCtcNbr().longValue() );

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

  public void insert( TplMrDocCallbackEntity mrDocCallbackEntity )
  {
    TplMrDocCallbackEntityVO mrDocCallbackEntityVO = ( TplMrDocCallbackEntityVO ) mrDocCallbackEntity.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_MR_DOC_CALLBACK + " ( " );
      query.append( C_MR_CALLBACK_CODE + ", " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_CTC_NBR + ", " );
      query.append( C_MR_DOC_CODE );

      int attCount = 0;

      if ( mrDocCallbackEntityVO.getLastAuthUserId() != null
           && !mrDocCallbackEntityVO.getLastAuthUserId().equals( "" ) )
      {
        query.append( ", " + C_LAST_AUTH_USER_ID );
        attCount++;
      }

      if ( mrDocCallbackEntityVO.getLastAuthDate() != null )
      {
        query.append( ", " + C_LAST_AUTH_DATE );
        attCount++;
      }

      if ( mrDocCallbackEntityVO.getLastUpdUserId() != null
           && !mrDocCallbackEntityVO.getLastUpdUserId().equals( "" ) )
      {
        query.append( ", " + C_LAST_UPD_USER_ID );
        attCount++;
      }

      if ( mrDocCallbackEntityVO.getLastUpdDate() != null )
      {
        query.append( ", " + C_LAST_UPD_DATE );
        attCount++;
      }

      if ( mrDocCallbackEntityVO.getRecStatCode() != null
           && !mrDocCallbackEntityVO.getRecStatCode().equals( "" ) )
      {
        query.append( ", " + C_REC_STAT_CODE );
        attCount++;
      }

      query.append( " ) " );

      query.append( "VALUES ( ?, ?, ?, ?, ?, ? " );

      for ( int i = 0; i < attCount; i++ )
      {
        query.append( ", ? " );
      }

      query.append( ") " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1,
                         mrDocCallbackEntityVO.getMrCallbackCode().longValue() );
      preparedStatement.setLong( 2, mrDocCallbackEntityVO.getProdAcctCode().longValue() );
      preparedStatement.setLong(
                         3,
                         mrDocCallbackEntityVO.getProdUnderAcctCode().longValue() );
      preparedStatement.setLong( 4, mrDocCallbackEntityVO.getCustNbr().longValue() );
      preparedStatement.setLong( 5, mrDocCallbackEntityVO.getCtcNbr().longValue() );
      preparedStatement.setLong( 6, mrDocCallbackEntityVO.getMrDocCode().longValue() );

      int index = 7;

      if ( mrDocCallbackEntityVO.getLastAuthUserId() != null
           && !mrDocCallbackEntityVO.getLastAuthUserId().equals( "" ) )
      {
        preparedStatement.setString( index++, mrDocCallbackEntityVO.getLastAuthUserId() );
      }

      if ( mrDocCallbackEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                index++,
                                new Timestamp(
                                               mrDocCallbackEntityVO.getLastAuthDate().getTime() ) );
      }

      if ( mrDocCallbackEntityVO.getLastUpdUserId() != null
           && !mrDocCallbackEntityVO.getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString( index++, mrDocCallbackEntityVO.getLastUpdUserId() );
      }

      if ( mrDocCallbackEntityVO.getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                index++,
                                new Timestamp(
                                               mrDocCallbackEntityVO.getLastUpdDate().getTime() ) );
      }

      if ( mrDocCallbackEntityVO.getRecStatCode() != null
           && !mrDocCallbackEntityVO.getRecStatCode().equals( "" ) )
      {
        preparedStatement.setString( index++, mrDocCallbackEntityVO.getRecStatCode() );
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

  public Integer getNextMrCallBackCode()
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    Integer nextVal = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT PL.SQ_MR_CALLBACK_CODE.NEXTVAL FROM DUAL " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.next() )
      {
        nextVal = new Integer( resultSet.getInt( "NEXTVAL" ) );
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

  public void inativateAllMrDocCallBacks(
                                         TplMrDocCallbackEntity tplMrDocCallbackEntity_ )
  {
    TplMrDocCallbackEntityVO mrDocCallbackEntityVO = ( TplMrDocCallbackEntityVO ) tplMrDocCallbackEntity_.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " );
      query.append( C_TPL_MR_DOC_CALLBACK );
      query.append( " SET " );
      query.append( C_REC_STAT_CODE + " = 'I' " );
      query.append( " WHERE " );
      query.append( C_MR_DOC_CODE + " = ? AND " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, mrDocCallbackEntityVO.getMrDocCode().longValue() );

      preparedStatement.setLong( 2, mrDocCallbackEntityVO.getProdAcctCode().longValue() );

      preparedStatement.setLong(
                         3,
                         mrDocCallbackEntityVO.getProdUnderAcctCode().longValue() );

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

  public boolean exists( TplMrDocCallbackEntity tplMrDocCallbackEntity_ )
  {
    TplMrDocCallbackEntityVO mrDocCallbackEntityVO = ( TplMrDocCallbackEntityVO ) tplMrDocCallbackEntity_.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT COUNT(*)" );
      query.append( " FROM " );
      query.append( C_TPL_MR_DOC_CALLBACK );
      query.append( " WHERE " );
      query.append( C_MR_DOC_CODE + " = ? AND " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? AND " );
      query.append( C_CUST_NBR + " = ? AND " );
      query.append( C_CTC_NBR + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, mrDocCallbackEntityVO.getMrDocCode().longValue() );

      preparedStatement.setLong( 2, mrDocCallbackEntityVO.getProdAcctCode().longValue() );

      preparedStatement.setLong(
                         3,
                         mrDocCallbackEntityVO.getProdUnderAcctCode().longValue() );

      preparedStatement.setLong( 4, mrDocCallbackEntityVO.getCustNbr().longValue() );

      preparedStatement.setLong( 5, mrDocCallbackEntityVO.getCtcNbr().longValue() );
      
      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.next() )
      {
        if ( resultSet.getInt( 1 ) != 0 )
        {
          return true;
        }
        else
        {
          return false;
        }
      }
      else
      {
        return false;
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
  }
}