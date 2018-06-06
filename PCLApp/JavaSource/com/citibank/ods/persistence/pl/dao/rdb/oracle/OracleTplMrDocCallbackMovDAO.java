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
import com.citibank.ods.entity.pl.TplContactCustEntity;
import com.citibank.ods.entity.pl.TplMrDocCallbackEntity;
import com.citibank.ods.entity.pl.TplMrDocCallbackMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplMrDocCallbackMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplMrDocCallbackDAO;
import com.citibank.ods.persistence.pl.dao.TplMrDocCallbackMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de movimento de Telefones de
 * Confirmação da Memória de Risco.
 */
public class OracleTplMrDocCallbackMovDAO extends BaseOracleTplMrDocCallbackDAO
    implements TplMrDocCallbackMovDAO
{
  // Codigo Documento MR
  private static final String C_MR_DOC_PRVT = "PRVT_MR_CODE";

  // Código da ação que originou o registro
  private static final String C_OPERN_CODE = "OPERN_CODE";

  // Tabela TPL_MR_DOC_CALLBACK_MOV
  private static final String C_TPL_MR_DOC_CALLBACK_MOV = C_PL_SCHEMA
                                                          + "TPL_MR_CALLBACK_MOV";

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
                                         BaseTplMrDocCallbackEntity baseTplMrDocCallbackEntity_ )
  {
    TplMrDocCallbackMovEntityVO mrDocCallbackMovEntityVO = ( TplMrDocCallbackMovEntityVO ) baseTplMrDocCallbackEntity_.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList mrDocCallbackMovEntities;
    TplMrDocCallbackMovEntity mrDocCallbackMovEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_MR_DOC_PRVT + ", " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_MR_CALLBACK_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_OPERN_CODE + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_CTC_NBR );
      query.append( " FROM " );
      query.append( C_TPL_MR_DOC_CALLBACK_MOV );
      query.append( " WHERE " );
      query.append( C_MR_DOC_PRVT + " = ? AND " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? AND " );
      query.append( C_CUST_NBR + " = ? AND " );
      query.append( C_CTC_NBR + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, mrDocCallbackMovEntityVO.getMrDocPrvt().longValue() );
      preparedStatement.setLong( 2,
                         mrDocCallbackMovEntityVO.getProdAcctCode().longValue() );
      preparedStatement.setLong(
                         3,
                         mrDocCallbackMovEntityVO.getProdUnderAcctCode().longValue() );
      preparedStatement.setLong( 4, mrDocCallbackMovEntityVO.getCustNbr().longValue() );
      preparedStatement.setLong( 5, mrDocCallbackMovEntityVO.getCtcNbr().longValue() );

	  preparedStatement.replaceParametersInQuery(query.toString());
      resultSet = preparedStatement.executeQuery();

      mrDocCallbackMovEntities = instantiateFromResultSet( resultSet );

      if ( mrDocCallbackMovEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( mrDocCallbackMovEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        mrDocCallbackMovEntity = ( TplMrDocCallbackMovEntity ) mrDocCallbackMovEntities.get( 0 );
      }

      return mrDocCallbackMovEntity;
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

    TplMrDocCallbackMovEntityVO mrDocCallbackMovEntityVO;
    TplMrDocCallbackMovEntity mrDocCallbackMovEntity;
    ArrayList mrDocCallbackMovEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        mrDocCallbackMovEntity = new TplMrDocCallbackMovEntity();
        mrDocCallbackMovEntityVO = ( TplMrDocCallbackMovEntityVO ) mrDocCallbackMovEntity.getData();

        mrDocCallbackMovEntityVO.setMrDocPrvt( new BigInteger(
                                                               resultSet_.getString( C_MR_DOC_PRVT ) ) );

        mrDocCallbackMovEntityVO.setProdAcctCode( new BigInteger(
                                                                  resultSet_.getString( C_PROD_ACCT_CODE ) ) );

        mrDocCallbackMovEntityVO.setProdUnderAcctCode( new BigInteger(
                                                                       resultSet_.getString( C_PROD_UNDER_ACCT_CODE ) ) );

        mrDocCallbackMovEntityVO.setCustNbr( new BigInteger(
                                                             resultSet_.getString( C_CUST_NBR ) ) );

        mrDocCallbackMovEntityVO.setCtcNbr( new BigInteger(
                                                            resultSet_.getString( C_CTC_NBR ) ) );

        if ( resultSet_.getString( C_MR_CALLBACK_CODE ) != null )
        {
          mrDocCallbackMovEntityVO.setMrCallbackCode( new BigInteger(
                                                                      resultSet_.getString( C_MR_CALLBACK_CODE ) ) );
        }

        if ( resultSet_.getString( C_LAST_UPD_USER_ID ) != null )
        {
          mrDocCallbackMovEntityVO.setLastUpdUserId( resultSet_.getString( C_LAST_UPD_USER_ID ) );
        }

        if ( resultSet_.getTimestamp( C_LAST_UPD_DATE ) != null )
        {
          mrDocCallbackMovEntityVO.setLastUpdDate( resultSet_.getTimestamp( C_LAST_UPD_DATE ) );
        }

        if ( resultSet_.getString( C_OPERN_CODE ) != null )
        {
          mrDocCallbackMovEntityVO.setOpernCode( resultSet_.getString( C_OPERN_CODE ) );
        }

        mrDocCallbackMovEntities.add( mrDocCallbackMovEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return mrDocCallbackMovEntities;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocCallbackMovDAO#insert(com.citibank.ods.entity.pl.TplMrDocCallbackMovEntity)
   */
  public void insert( TplMrDocCallbackMovEntity mrDocCallbackMovEntity_ )
  {
    TplMrDocCallbackMovEntityVO mrDocCallbackMovEntityVO = ( TplMrDocCallbackMovEntityVO ) mrDocCallbackMovEntity_.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_MR_DOC_CALLBACK_MOV + " ( " );
      query.append( C_MR_DOC_PRVT + ", " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_MR_CALLBACK_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_CTC_NBR );

      int attCount = 0;
      if ( mrDocCallbackMovEntityVO.getLastUpdUserId() != null
           && !mrDocCallbackMovEntityVO.getLastUpdUserId().equals( "" ) )
      {
        query.append( ", " + C_LAST_UPD_USER_ID );
        attCount++;
      }

      if ( mrDocCallbackMovEntityVO.getLastUpdDate() != null )
      {
        query.append( ", " + C_LAST_UPD_DATE );
        attCount++;
      }

      if ( mrDocCallbackMovEntityVO.getOpernCode() != null
           && !mrDocCallbackMovEntityVO.getOpernCode().equals( "" ) )
      {
        query.append( ", " + C_OPERN_CODE );
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

      preparedStatement.setLong( 1, mrDocCallbackMovEntityVO.getMrDocPrvt().longValue() );
      preparedStatement.setLong( 2,
                         mrDocCallbackMovEntityVO.getProdAcctCode().longValue() );
      preparedStatement.setLong(
                         3,
                         mrDocCallbackMovEntityVO.getProdUnderAcctCode().longValue() );
      preparedStatement.setLong(
                         4,
                         mrDocCallbackMovEntityVO.getMrCallbackCode().longValue() );
      preparedStatement.setLong( 5, mrDocCallbackMovEntityVO.getCustNbr().longValue() );
      preparedStatement.setLong( 6, mrDocCallbackMovEntityVO.getCtcNbr().longValue() );

      int index = 7;

      if ( mrDocCallbackMovEntityVO.getLastUpdUserId() != null
           && !mrDocCallbackMovEntityVO.getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString( index++,
                             mrDocCallbackMovEntityVO.getLastUpdUserId() );
      }

      if ( mrDocCallbackMovEntityVO.getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                index++,
                                new Timestamp(
                                               mrDocCallbackMovEntityVO.getLastUpdDate().getTime() ) );
      }

      if ( mrDocCallbackMovEntityVO.getOpernCode() != null
           && !mrDocCallbackMovEntityVO.getOpernCode().equals( "" ) )
      {
        preparedStatement.setString( index++, mrDocCallbackMovEntityVO.getOpernCode() );
      }

	  preparedStatement.replaceParametersInQuery(query.toString());
      preparedStatement.execute();

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
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocCallbackMovDAO#findAssociatedContactCustByPK(java.math.BigInteger,
   *      java.math.BigInteger, java.math.BigInteger)
   */
  public ArrayList findAssociatedContactCustByPK( BigInteger mrDocPrvt_,
                                                 BigInteger prodAcctCode_,
                                                 BigInteger prodUnderAcctCode_ )
  {

    TplContactCustEntity contactCustEntity;
    ArrayList contactCustEntities;

    // Recupera os contatos correntes ativos
    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
    TplMrDocCallbackDAO mrDocCallbackDAO = odsDAOFactory.getTplMrDocCallbackDAO();
    contactCustEntities = mrDocCallbackDAO.findAssociatedContactCustByPK(
                                                                          mrDocPrvt_,
                                                                          prodAcctCode_,
                                                                          prodUnderAcctCode_ );

    return contactCustEntities;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocCallbackMovDAO#findContactCustByPK(java.math.BigInteger,
   *      java.math.BigInteger, java.math.BigInteger, java.lang.String)
   */
  public ArrayList findContactCustByPK( BigInteger mrDocPrvt_,
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
      // Recupera os contatos removidos
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
      query.append( "CUST.CUST_NBR" + ", ");
      query.append( "CALLBACKMOV.OPERN_CODE" );      
      query.append( " FROM " );
      query.append( C_TPL_MR_DOC_CALLBACK_MOV + " CALLBACKMOV, " );
      query.append( C_TPL_CONTACT_CUST + " CUST " );
      query.append( " WHERE " );
      query.append( "CALLBACKMOV." + C_MR_DOC_PRVT + " = ? AND " );
      query.append( "CALLBACKMOV." + C_PROD_ACCT_CODE + " = ? AND " );
      query.append( "CALLBACKMOV." + C_PROD_UNDER_ACCT_CODE + " = ? AND " );
      query.append( "CALLBACKMOV." + C_CUST_NBR + " = CUST." + C_CUST_NBR
                    + " AND " );
      query.append( "CALLBACKMOV." + C_CTC_NBR + " = CUST." + C_CTC_NBR );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, mrDocPrvt_.longValue() );
      preparedStatement.setLong( 2, prodAcctCode_.longValue() );
      preparedStatement.setLong( 3, prodUnderAcctCode_.longValue() );

	  preparedStatement.replaceParametersInQuery(query.toString());
      resultSet = preparedStatement.executeQuery();

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
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocCallbackMovDAO#delete(com.citibank.ods.entity.pl.TplMrDocCallbackMovEntity)
   */
  public void delete( TplMrDocCallbackMovEntity mrDocCallbackMovEntity_ )
  {
    TplMrDocCallbackMovEntityVO mrDocCallbackMovEntityVO = ( TplMrDocCallbackMovEntityVO ) mrDocCallbackMovEntity_.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "DELETE FROM " );
      query.append( C_TPL_MR_DOC_CALLBACK_MOV );
      query.append( " WHERE " );
      query.append( C_MR_DOC_PRVT + " = ? AND " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? AND " );
      query.append( C_CUST_NBR + " = ? AND " );
      query.append( C_CTC_NBR + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, mrDocCallbackMovEntityVO.getMrDocPrvt().longValue() );

      preparedStatement.setLong( 2,
                         mrDocCallbackMovEntityVO.getProdAcctCode().longValue() );

      preparedStatement.setLong(
                         3,
                         mrDocCallbackMovEntityVO.getProdUnderAcctCode().longValue() );

      preparedStatement.setLong( 4, mrDocCallbackMovEntityVO.getCustNbr().longValue() );

      preparedStatement.setLong( 5, mrDocCallbackMovEntityVO.getCtcNbr().longValue() );

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

  /**
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocCallbackMovDAO#deleteAll(com.citibank.ods.entity.pl.TplMrDocCallbackMovEntity)
   */
  public void deleteAll( TplMrDocCallbackMovEntity mrDocCallbackMovEntity_ )
  {
    TplMrDocCallbackMovEntityVO mrDocCallbackMovEntityVO = ( TplMrDocCallbackMovEntityVO ) mrDocCallbackMovEntity_.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "DELETE FROM " );
      query.append( C_TPL_MR_DOC_CALLBACK_MOV );
      query.append( " WHERE " );
      query.append( C_MR_DOC_PRVT + " = ? AND " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, mrDocCallbackMovEntityVO.getMrDocPrvt().longValue() );

      preparedStatement.setLong( 2,
                         mrDocCallbackMovEntityVO.getProdAcctCode().longValue() );

      preparedStatement.setLong(
                         3,
                         mrDocCallbackMovEntityVO.getProdUnderAcctCode().longValue() );

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

  /**
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocCallbackMovDAO#existsMovement(com.citibank.ods.entity.pl.TplMrDocCallbackMovEntity)
   */
  public boolean existsMovement(
                                TplMrDocCallbackMovEntity mrDocCallbackMovEntity_ )
  {
    boolean exists = false;

    try
    {
      find( mrDocCallbackMovEntity_ );
      exists = true;
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocCallbackMovDAO#copyFromMovToCurrent(com.citibank.ods.entity.pl.BaseTplMrDocCallbackEntity,
   *      java.util.Date, java.lang.String)
   */
  public void copyFromMovToCurrent(
                                   BaseTplMrDocCallbackEntity baseTplMrDocCallbackEntity_,
                                   Date lastAuthDate, String lastAuthUserId )
  {
    TplMrDocCallbackMovEntity mrDocCallbackMovEntity = ( TplMrDocCallbackMovEntity ) find( baseTplMrDocCallbackEntity_ );

    // Monta entidade de Currente com dados de Movimento
    TplMrDocCallbackEntity mrDocCallbackEntity = new TplMrDocCallbackEntity(
                                                                             mrDocCallbackMovEntity,
                                                                             lastAuthDate,
                                                                             lastAuthUserId );

    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
    TplMrDocCallbackDAO mrDocCallbackDAO = odsDAOFactory.getTplMrDocCallbackDAO();

    if ( mrDocCallbackDAO.exists( mrDocCallbackEntity ) )
    {
      mrDocCallbackDAO.copyFromCurrentToHist( mrDocCallbackEntity, lastAuthDate );
      mrDocCallbackDAO.update( mrDocCallbackEntity );
    }
    else
    {
      mrDocCallbackDAO.insert( mrDocCallbackEntity );
    }

    // Insere dados de Movemento na tabela Corrente.
    
  }

  public TplMrDocCallbackMovEntity findCallBack(
                                                TplMrDocCallbackMovEntity tplMrDocCallbackMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList callBackEntities;

    try
    {
      // Recupera os contatos removidos
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_MR_DOC_PRVT + ", " );
      query.append( C_MR_CALLBACK_CODE + ", " );
      query.append( C_CTC_NBR + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_OPERN_CODE + ", ");
      query.append( C_PROD_ACCT_CODE + ", ");
      query.append( C_PROD_UNDER_ACCT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_MR_DOC_CALLBACK_MOV );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + " = ? AND " );
      query.append( C_CTC_NBR + " = ? AND " );
      query.append( C_MR_DOC_PRVT + " =? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         tplMrDocCallbackMovEntity_.getData().getCustNbr().longValue() );
      preparedStatement.setLong(
                         2,
                         tplMrDocCallbackMovEntity_.getData().getCtcNbr().longValue() );
      preparedStatement.setLong(
                         3,
                         ( ( TplMrDocCallbackMovEntityVO ) tplMrDocCallbackMovEntity_.getData() ).getMrDocPrvt().longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      callBackEntities = instantiateCallBack( resultSet );

      if ( callBackEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( callBackEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplMrDocCallbackMovEntity_ = ( TplMrDocCallbackMovEntity ) callBackEntities.get( 0 );
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

    return tplMrDocCallbackMovEntity_;
  }

  protected ArrayList instantiateCallBack( ResultSet resultSet_ )
  {

    TplMrDocCallbackMovEntity tplMrDocCallbackMovEntity;
    TplMrDocCallbackMovEntityVO tplMrDocCallbackMovEntityVO;
    ArrayList callBackEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplMrDocCallbackMovEntity = new TplMrDocCallbackMovEntity();
        tplMrDocCallbackMovEntityVO = ( TplMrDocCallbackMovEntityVO ) tplMrDocCallbackMovEntity.getData();
        
        tplMrDocCallbackMovEntityVO.setMrDocPrvt(new BigInteger( resultSet_.getString( C_MR_DOC_PRVT )));

        tplMrDocCallbackMovEntityVO.setCtcNbr( new BigInteger(
                                                               resultSet_.getString( C_CTC_NBR ) ) );

        tplMrDocCallbackMovEntityVO.setCustNbr( new BigInteger(
                                                                resultSet_.getString( C_CUST_NBR ) ) );

        if ( resultSet_.getString( C_OPERN_CODE ) != null )
        {
          tplMrDocCallbackMovEntityVO.setOpernCode( resultSet_.getString( C_OPERN_CODE ) );
        }

        if ( resultSet_.getString( C_MR_CALLBACK_CODE ) != null )
        {
          tplMrDocCallbackMovEntityVO.setMrCallbackCode( new BigInteger(
                                                                         resultSet_.getString( C_MR_CALLBACK_CODE ) ) );
        }

        if ( resultSet_.getString( C_PROD_ACCT_CODE) != null )
        {
          tplMrDocCallbackMovEntityVO.setProdAcctCode( new BigInteger( resultSet_.getString( C_PROD_ACCT_CODE ) ) );
        }
        
        if ( resultSet_.getString( C_PROD_UNDER_ACCT_CODE ) != null )
        {
          tplMrDocCallbackMovEntityVO.setProdUnderAcctCode( new BigInteger(
                                                                         resultSet_.getString( C_PROD_UNDER_ACCT_CODE ) ) );
        }
        callBackEntities.add( tplMrDocCallbackMovEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return callBackEntities;
  }
  
  /**
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocCallbackMovDAO#update(com.citibank.ods.entity.pl.TplMrDocCallbackMovEntity)
   */
  public void update( TplMrDocCallbackMovEntity mrDocCallbackMovEntity_ )
  {
    TplMrDocCallbackMovEntityVO mrDocCallbackMovEntityVO = ( TplMrDocCallbackMovEntityVO ) mrDocCallbackMovEntity_.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_MR_DOC_CALLBACK_MOV + " SET " );
      query.append(  C_LAST_UPD_USER_ID + " = ? ,");
      query.append(  C_LAST_UPD_DATE + " = ? , " );
      query.append(  C_OPERN_CODE + "= ?  " );
      query.append( " WHERE ");
      query.append( C_MR_DOC_PRVT + " = ? AND " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? AND " );
      query.append( C_CUST_NBR + " = ? AND " );
      query.append( C_CTC_NBR + " = ? " );


    
      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      
      preparedStatement.setString( 1, mrDocCallbackMovEntityVO.getLastUpdUserId() );

      preparedStatement.setTimestamp( 2, new Timestamp(  mrDocCallbackMovEntityVO.getLastUpdDate().getTime() ) );

      preparedStatement.setString( 3, mrDocCallbackMovEntityVO.getOpernCode() );

      preparedStatement.setLong( 4, mrDocCallbackMovEntityVO.getMrDocPrvt().longValue() );
      
      preparedStatement.setLong( 5, mrDocCallbackMovEntityVO.getProdAcctCode().longValue() );
      
      preparedStatement.setLong( 6, mrDocCallbackMovEntityVO.getProdUnderAcctCode().longValue() );
      
      preparedStatement.setLong( 7, mrDocCallbackMovEntityVO.getCustNbr().longValue() );
      
      preparedStatement.setLong( 8, mrDocCallbackMovEntityVO.getCtcNbr().longValue() );

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