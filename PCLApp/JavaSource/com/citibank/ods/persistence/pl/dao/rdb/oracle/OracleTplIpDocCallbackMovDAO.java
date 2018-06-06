package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
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
import com.citibank.ods.entity.pl.BaseTplIpDocCallbackEntity;
import com.citibank.ods.entity.pl.TplContactCustEntity;
import com.citibank.ods.entity.pl.TplIpDocCallbackMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplContactCustEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplIpDocCallbackMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplIpDocCallbackMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 16, 2007
 *  
 */

public class OracleTplIpDocCallbackMovDAO extends BaseOracleTplIpDocCallbackDAO
    implements TplIpDocCallbackMovDAO
{

  public String C_OPERN_CODE = "OPERN_CODE";

  public String C_TPL_IP_DOC_CALLBACK_MOV = C_PL_SCHEMA
                                            + "TPL_PRMNT_INSTR_CALLBACK_MOV";
  
  public String C_TPL_CONTACT_CUST = C_PL_SCHEMA  + "TPL_CONTACT_CUST";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocCallbackMovDAO#update(com.citibank.ods.entity.pl.TplIpDocCallbackMovEntity)
   */
  public void update( TplIpDocCallbackMovEntity tplIpDocCallbackMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_IP_DOC_CALLBACK_MOV + " " );
      query.append( " SET " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_OPERN_CODE + " = ? " );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + "= ? AND " );
      query.append( C_IP_DOC_CODE + "= ? AND " );
      query.append( C_IP_CALLBACK_CODE + "= ? AND " );
      query.append( C_CTC_NBR + "= ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

  
      if ( tplIpDocCallbackMovEntity_.getData().getLastAuthUserId() != null
           && !tplIpDocCallbackMovEntity_.getData().getLastAuthUserId().equals(
                                                                                "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplIpDocCallbackMovEntity_.getData().getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocCallbackMovEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplIpDocCallbackMovEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( ( ( TplIpDocCallbackMovEntityVO ) tplIpDocCallbackMovEntity_.getData() ).getOpernCode() != null
           && !( ( TplIpDocCallbackMovEntityVO ) tplIpDocCallbackMovEntity_.getData() ).getOpernCode().equals(
                                                                                                               "" ) )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplIpDocCallbackMovEntityVO ) tplIpDocCallbackMovEntity_.getData() ).getOpernCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocCallbackMovEntity_.getData().getCustNbr() != null
           && tplIpDocCallbackMovEntity_.getData().getCustNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackMovEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplIpDocCallbackMovEntity_.getData().getIpDocCode() != null
           && tplIpDocCallbackMovEntity_.getData().getIpDocCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackMovEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplIpDocCallbackMovEntity_.getData().getIpCallbackCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackMovEntity_.getData().getIpCallbackCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplIpDocCallbackMovEntity_.getData().getCtcNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackMovEntity_.getData().getCtcNbr().longValue() );
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
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocCallbackMovDAO#delete(com.citibank.ods.entity.pl.TplIpDocCallbackMovEntity)
   */
  public void delete( TplIpDocCallbackMovEntity tplIpDocCallbackMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " DELETE FROM " + C_TPL_IP_DOC_CALLBACK_MOV );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + "= ? AND " );
      query.append( C_IP_DOC_CODE + "= ? " );
      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplIpDocCallbackMovEntity_.getData().getCustNbr() != null
           && tplIpDocCallbackMovEntity_.getData().getCustNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackMovEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplIpDocCallbackMovEntity_.getData().getIpDocCode() != null
           && tplIpDocCallbackMovEntity_.getData().getIpDocCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackMovEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());
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

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocCallbackMovDAO#insert(com.citibank.ods.entity.pl.TplIpDocCallbackMovEntity)
   */
  public TplIpDocCallbackMovEntity insert(
                                          TplIpDocCallbackMovEntity tplIpDocCallbackMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_IP_DOC_CALLBACK_MOV + " (" );
      query.append( C_IP_CALLBACK_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_OPERN_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_CTC_NBR + ", " );
      query.append( C_IP_DOC_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?)" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplIpDocCallbackMovEntity_.getData().getIpCallbackCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackMovEntity_.getData().getIpCallbackCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );

      }

      if ( tplIpDocCallbackMovEntity_.getData().getLastUpdUserId() != null
           && !tplIpDocCallbackMovEntity_.getData().getLastUpdUserId().equals(
                                                                               "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplIpDocCallbackMovEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocCallbackMovEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplIpDocCallbackMovEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( ( ( TplIpDocCallbackMovEntityVO ) tplIpDocCallbackMovEntity_.getData() ).getOpernCode() != null
           && !( ( TplIpDocCallbackMovEntityVO ) tplIpDocCallbackMovEntity_.getData() ).getOpernCode().equals(
                                                                                                               "" ) )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplIpDocCallbackMovEntityVO ) tplIpDocCallbackMovEntity_.getData() ).getOpernCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocCallbackMovEntity_.getData().getCustNbr() != null
           && tplIpDocCallbackMovEntity_.getData().getCustNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackMovEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplIpDocCallbackMovEntity_.getData().getCtcNbr() != null
           && tplIpDocCallbackMovEntity_.getData().getCtcNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackMovEntity_.getData().getCtcNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplIpDocCallbackMovEntity_.getData().getIpDocCode() != null
           && tplIpDocCallbackMovEntity_.getData().getIpDocCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocCallbackMovEntity_.getData().getIpDocCode().longValue() );
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

    return ( TplIpDocCallbackMovEntity ) find( tplIpDocCallbackMovEntity_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocCallbackMovDAO#exists(com.citibank.ods.entity.pl.TplIpDocCallbackMovEntity)
   */
  public boolean exists( TplIpDocCallbackMovEntity tplIpDocCallbackMovEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tplIpDocCallbackMovEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTplIpDocCallbackDAO#find(com.citibank.ods.entity.pl.BaseTplIpDocCallbackEntity)
   */
  public BaseTplIpDocCallbackEntity find(
                                         BaseTplIpDocCallbackEntity baseTplIpDocCallbackEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    ResultSet resultSet = null;
    ArrayList tplIpDocCallbackMovEntities;
    BaseTplIpDocCallbackEntity tplIpDocCallbackEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_IP_CALLBACK_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_OPERN_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_CTC_NBR + ", " );
      query.append( C_IP_DOC_CODE );
      query.append( " FROM " + C_TPL_IP_DOC_CALLBACK_MOV );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + "= ? AND " );
      query.append( C_IP_DOC_CODE + "= ? AND " );
      query.append( C_IP_CALLBACK_CODE + "= ? AND " );
      query.append( C_CTC_NBR + "= ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( baseTplIpDocCallbackEntity_.getData().getCustNbr() != null
           && baseTplIpDocCallbackEntity_.getData().getCustNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           baseTplIpDocCallbackEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( baseTplIpDocCallbackEntity_.getData().getIpDocCode() != null
           && baseTplIpDocCallbackEntity_.getData().getIpDocCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           baseTplIpDocCallbackEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( baseTplIpDocCallbackEntity_.getData().getIpCallbackCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           baseTplIpDocCallbackEntity_.getData().getIpCallbackCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( baseTplIpDocCallbackEntity_.getData().getCtcNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           baseTplIpDocCallbackEntity_.getData().getCtcNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      tplIpDocCallbackMovEntities = instantiateFromResultSet( resultSet );

      if ( tplIpDocCallbackMovEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplIpDocCallbackMovEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplIpDocCallbackEntity = ( BaseTplIpDocCallbackEntity ) tplIpDocCallbackMovEntities.get( 0 );
      }

      resultSet.close();

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

    return tplIpDocCallbackEntity;
  }

  /*
   * Retorna uma coleção de entities a partir do rs
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {

    TplIpDocCallbackMovEntity tplIpDocCallbackMovEntity;
    ArrayList tplIpDocCallbackMovEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplIpDocCallbackMovEntity = new TplIpDocCallbackMovEntity();

        tplIpDocCallbackMovEntity.getData().setCtcNbr(
                                                       resultSet_.getString( C_CTC_NBR ) != null
                                                                                                ? new BigInteger(
                                                                                                                  ( resultSet_.getString( C_CTC_NBR ) ) )
                                                                                                : null );

        tplIpDocCallbackMovEntity.getData().setCustNbr(
                                                        resultSet_.getString( C_CUST_NBR ) != null
                                                                                                  ? new BigInteger(
                                                                                                                    ( resultSet_.getString( C_CUST_NBR ) ) )
                                                                                                  : null );

        tplIpDocCallbackMovEntity.getData().setIpCallbackCode(
                                                               resultSet_.getString( C_IP_CALLBACK_CODE ) != null
                                                                                                                 ? new BigInteger(
                                                                                                                                   ( resultSet_.getString( C_IP_CALLBACK_CODE ) ) )
                                                                                                                 : null );

        tplIpDocCallbackMovEntity.getData().setIpDocCode(
                                                          resultSet_.getString( C_IP_DOC_CODE ) != null
                                                                                                       ? new BigInteger(
                                                                                                                         ( resultSet_.getString( C_IP_DOC_CODE ) ) )
                                                                                                       : null );
        tplIpDocCallbackMovEntity.getData().setLastUpdUserId(
                                                              resultSet_.getString( C_LAST_UPD_USER_ID ) != null
                                                                                                                ? ( resultSet_.getString( C_LAST_UPD_USER_ID ) )
                                                                                                                : null );
        tplIpDocCallbackMovEntity.getData().setLastUpdDate(
                                                            resultSet_.getTimestamp( C_LAST_UPD_DATE ) != null
                                                                                                         ? ( resultSet_.getTimestamp( C_LAST_UPD_DATE ) )
                                                                                                         : null );

        ( ( TplIpDocCallbackMovEntityVO ) tplIpDocCallbackMovEntity.getData() ).setOpernCode( resultSet_.getString( C_OPERN_CODE ) != null
                                                                                                                                          ? resultSet_.getString( C_OPERN_CODE )
                                                                                                                                          : null );

        tplIpDocCallbackMovEntities.add( tplIpDocCallbackMovEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return tplIpDocCallbackMovEntities;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocCallbackMovDAO#findByPK(java.math.BigInteger,
   *      java.math.BigInteger)
   */
  public ArrayList findByPK( BigInteger ipDocCode_, BigInteger custNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList ipDocCallbackEntities;
    
    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_IP_CALLBACK_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_OPERN_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_CTC_NBR + ", " );
      query.append( C_IP_DOC_CODE );
      query.append( " FROM " + C_TPL_IP_DOC_CALLBACK_MOV );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + "= ? AND " );
      query.append( C_IP_DOC_CODE + "= ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, custNbr_.longValue() );

      preparedStatement.setLong( 2, ipDocCode_.longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      ipDocCallbackEntities = instantiateFromResultSet( resultSet );

      resultSet.close();

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

    return ipDocCallbackEntities;
  }
  
  /**
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocCallbackMovDAO#findContactCustByPK(java.math.BigInteger,
   *      java.math.BigInteger)
   */
  public ArrayList findContactCustByPK( BigInteger ipDocPrvt_,
                                       BigInteger custNbr_)
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
      query.append( C_TPL_IP_DOC_CALLBACK_MOV + " CALLBACKMOV, " );
      query.append( C_TPL_CONTACT_CUST + " CUST " );
      query.append( " WHERE " );
      query.append( "CALLBACKMOV." + C_IP_DOC_CODE + " = ? AND " );
      query.append( "CALLBACKMOV." + C_CUST_NBR + " = ?  AND " );
      query.append( "CALLBACKMOV." + C_CUST_NBR + " = CUST." + C_CUST_NBR + " AND " );
      query.append( "CALLBACKMOV." + C_CTC_NBR + " = CUST." + C_CTC_NBR );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, ipDocPrvt_.longValue() );
      preparedStatement.setLong( 2, custNbr_.longValue() );

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
   * Instancia entidades de contatos a partir do ResultSet.
   * 
   * @param resultSet_ - ResultSet utilizado para instanciar entidades.
   * @return ArrayList - Entidades instanciadas a partir do ResultSet.
   */
  protected ArrayList instantiateContactCustFromResultSet( ResultSet resultSet_ )
  {

    TplContactCustEntity contactCustEntity;
    TplContactCustEntityVO contactCustEntityVO;
    ArrayList contactCustEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        contactCustEntity = new TplContactCustEntity();
        contactCustEntityVO = ( TplContactCustEntityVO ) contactCustEntity.getData();

        contactCustEntityVO.setCtcNbr( new BigInteger(
                                                       resultSet_.getString( C_CTC_NBR ) ) );

        contactCustEntityVO.setCustNbr( new BigInteger(
                                                        resultSet_.getString( C_CUST_NBR ) ) );

        if ( resultSet_.getString( "FULL_NAME_TEXT" ) != null )
        {
          contactCustEntityVO.setFullNameText( resultSet_.getString( "FULL_NAME_TEXT" ) );
        }
        
		if ( resultSet_.getString( "FULL_NAME_1_TEXT" ) != null )
		{
		  contactCustEntityVO.setFullNameText_2( resultSet_.getString( "FULL_NAME_1_TEXT" ) );
		}

		if ( resultSet_.getString( "FULL_NAME_2_TEXT" ) != null )
		{
		  contactCustEntityVO.setFullNameText_3( resultSet_.getString( "FULL_NAME_2_TEXT" ) );
		}

        if ( resultSet_.getString( "PHONE_DDD_CODE" ) != null )
        {
          contactCustEntityVO.setPhoneDddCode( new BigInteger(
                                                               resultSet_.getString( "PHONE_DDD_CODE" ) ) );
        }

        if ( resultSet_.getString( "PHONE_NBR" ) != null )
        {
          contactCustEntityVO.setPhoneNbr( new BigInteger(
                                                           resultSet_.getString( "PHONE_NBR" ) ) );
        }

        if ( resultSet_.getString( "PHONE_EXTN_NBR" ) != null )
        {
          contactCustEntityVO.setPhoneExtnNbr( new BigInteger(
                                                               resultSet_.getString( "PHONE_EXTN_NBR" ) ) );
        }

        if ( resultSet_.getTimestamp( "LAST_AUTH_DATE" ) != null )
        {
          contactCustEntityVO.setLastAuthDate( resultSet_.getTimestamp( "LAST_AUTH_DATE" ) );
        }

        if ( resultSet_.getString( "LAST_AUTH_USER_ID" ) != null )
        {
          contactCustEntityVO.setLastAuthUserId( resultSet_.getString( "LAST_AUTH_USER_ID" ) );
        }

        if ( resultSet_.getTimestamp( "LAST_UPD_DATE" ) != null )
        {
          contactCustEntityVO.setLastUpdDate( resultSet_.getTimestamp( "LAST_UPD_DATE" ) );
        }

        if ( resultSet_.getString( "LAST_UPD_USER_ID" ) != null )
        {
          contactCustEntityVO.setLastUpdUserId( resultSet_.getString( "LAST_UPD_USER_ID" ) );
        }

        if ( resultSet_.getString( "REC_STAT_CODE" ) != null )
        {
          contactCustEntityVO.setRecStatCode( resultSet_.getString( "REC_STAT_CODE" ) );
        }
        
        try {        
        	if (resultSet_.getString( "OPERN_CODE" ) != null )
        	{
        		contactCustEntityVO.setOpernCode( resultSet_.getString( "OPERN_CODE" ) );
        	}
        } catch ( SQLException e )
            {
            }	
        
        	

        contactCustEntities.add( contactCustEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return contactCustEntities;
  }
}