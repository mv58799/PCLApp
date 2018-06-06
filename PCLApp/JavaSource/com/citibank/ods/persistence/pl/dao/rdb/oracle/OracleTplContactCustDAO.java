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
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplContactCustEntity;
import com.citibank.ods.entity.pl.TplContactCustEntity;
import com.citibank.ods.entity.pl.valueobject.TplContactCustEntityVO;
import com.citibank.ods.persistence.pl.dao.TplContactCustDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * 
 * @author Hamilton Matos
 */

  /*
  * @author Ronaldo Machado G&P Java Team
  * descrição: Criação de novo método para corrigir a maneira em que é gravado o ID na tpl_contact_cust
  * data:04/07/2008
  */
public class OracleTplContactCustDAO extends BaseOracleTplContactCustDAO
    implements TplContactCustDAO
{
  private static final String C_TABLE_COLUMNS = "CTC_NBR,CUST_NBR,FULL_NAME_TEXT,LAST_AUTH_DATE,LAST_AUTH_USER_ID,LAST_UPD_DATE,LAST_UPD_USER_ID,PHONE_DDD_CODE,PHONE_EXTN_NBR,PHONE_NBR,REC_STAT_CODE,FULL_NAME_1_TEXT,FULL_NAME_2_TEXT";

  private static final String C_TABLE_NAME = C_PL_SCHEMA + "TPL_CONTACT_CUST";

  private static final String C_TPL_CONTACT_CUST = C_PL_SCHEMA + "TPL_CONTACT_CUST";

  /**
   * Insere uma nova linha na tabela TPL_CONTACT_CUST com os dados da entidade
   * correspondente passada como parametro
   * @param tplContactCustEntity__
   * @throws UnexpectedException
   * @author Hamilton Matos
   * @date 27/03/2007
   */
  public TplContactCustEntity insert( TplContactCustEntity tplContactCustEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer sqlQuery = new StringBuffer();

    sqlQuery.append( "INSERT INTO " + C_TABLE_NAME + "( " );
    sqlQuery.append( C_CTC_NBR + ", " + C_CUST_NBR + ", " + C_FULL_NAME_TEXT
                     + ", " + C_LAST_AUTH_DATE + ", " + C_LAST_AUTH_USER_ID
                     + ", " + C_LAST_UPD_DATE + ", " + C_LAST_UPD_USER_ID
                     + ", " + C_PHONE_DDD_CODE + ", " + C_PHONE_EXTN_NBR + ", "
                     + C_PHONE_NBR + ", " + C_REC_STAT_CODE + ", " + C_FULL_NAME_TEXT_2 + ", " + C_FULL_NAME_TEXT_3 + ") " );
    sqlQuery.append( "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" );

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

      int count = 1;

      if ( tplContactCustEntity_.getData().getCtcNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplContactCustEntity_.getData().getCtcNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplContactCustEntity_.getData().getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplContactCustEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplContactCustEntity_.getData().getFullNameText() != null )
      {
        preparedStatement.setString( count++,
                             tplContactCustEntity_.getData().getFullNameText() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplContactCustEntity_.getData().getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplContactCustEntity_.getData().getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( tplContactCustEntity_.getData().getLastAuthUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplContactCustEntity_.getData().getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplContactCustEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplContactCustEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( tplContactCustEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplContactCustEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplContactCustEntity_.getData().getPhoneDddCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplContactCustEntity_.getData().getPhoneDddCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplContactCustEntity_.getData().getPhoneExtnNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplContactCustEntity_.getData().getPhoneExtnNbr().longValue() );
      }
      else
      {
      	preparedStatement.setString( count++, null );
      }

      if ( tplContactCustEntity_.getData().getPhoneNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplContactCustEntity_.getData().getPhoneNbr().longValue() );
      }
      else
      {
      	preparedStatement.setString( count++, null );
      }

      if ( tplContactCustEntity_.getData().getRecStatCode() != null )
      {
        preparedStatement.setString( count++,
                             tplContactCustEntity_.getData().getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }
	  
	  if ( tplContactCustEntity_.getData().getFullNameText_2() != null )
	  {
			  preparedStatement.setString( count++,
								   tplContactCustEntity_.getData().getFullNameText_2() );
	  }
	  else
	  {
	     preparedStatement.setString( count++, "" );
	  }
	  
	  if ( tplContactCustEntity_.getData().getFullNameText_3() != null )
	  {
	    preparedStatement.setString( count++,
										 tplContactCustEntity_.getData().getFullNameText_3() );
	  }
	  else
	  {
	     preparedStatement.setString( count++, "" );
	  }

	  preparedStatement.replaceParametersInQuery(sqlQuery.toString());
      preparedStatement.executeUpdate();
      

      return ( TplContactCustEntity ) find( tplContactCustEntity_ );

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

  /**
   * Atualiza uma linha na tabela TPL_CONTACT_CUST de acordo com ID contido na
   * entidade passada como parâmetro.
   * @param tplContactCustEntity__
   * @throws UnexpectedException
   * @author Hamilton Matos
   * @date 27/03/2007
   */
  public void update( TplContactCustEntity tplContactCustEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "UPDATE " );
      query.append( C_TABLE_NAME );
      query.append( " SET " );
      query.append( "FULL_NAME_TEXT = ?, LAST_AUTH_DATE = ?, LAST_AUTH_USER_ID = ?, LAST_UPD_DATE = ?, LAST_UPD_USER_ID = ?, PHONE_DDD_CODE = ?, PHONE_EXTN_NBR = ?, PHONE_NBR = ?, REC_STAT_CODE = ?" );
      query.append( " WHERE CTC_NBR = "
                    + tplContactCustEntity_.getData().getCtcNbr()
                    + " AND CUST_NBR = "
                    + tplContactCustEntity_.getData().getCustNbr() );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( tplContactCustEntity_.getData().getFullNameText() != null )
      {
        preparedStatement.setString( count++,
                             tplContactCustEntity_.getData().getFullNameText() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplContactCustEntity_.getData().getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplContactCustEntity_.getData().getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( tplContactCustEntity_.getData().getLastAuthUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplContactCustEntity_.getData().getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplContactCustEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplContactCustEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( tplContactCustEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplContactCustEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplContactCustEntity_.getData().getPhoneDddCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplContactCustEntity_.getData().getPhoneDddCode().longValue() );
      }
      else
      {
      	preparedStatement.setString( count++, null );
      }

      if ( tplContactCustEntity_.getData().getPhoneExtnNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplContactCustEntity_.getData().getPhoneExtnNbr().longValue() );
      }
      else
      {
      	preparedStatement.setString( count++, null );
      }

      if ( tplContactCustEntity_.getData().getPhoneNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplContactCustEntity_.getData().getPhoneNbr().longValue() );
      }
      else
      {
      	preparedStatement.setString( count++, null );
      }

      if ( tplContactCustEntity_.getData().getRecStatCode() != null )
      {
        preparedStatement.setString( count++,
                             tplContactCustEntity_.getData().getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

	  preparedStatement.replaceParametersInQuery(query.toString());
      preparedStatement.executeUpdate();
	  
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

  /**
   * Remove uma linha na tabela TPL_CONTACT_CUST de acordo com ID passado como
   * parâmetro.
   * @param entityKey_
   * @throws UnexpectedException
   * @author Hamilton Matos
   * @date 27/03/2007
   */

  public void delete( TplContactCustEntity tplContactCustEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "UPDATE " );
      query.append( C_TABLE_NAME );
      query.append( " SET " );
      query.append( "LAST_AUTH_DATE = ?, LAST_AUTH_USER_ID = ?, LAST_UPD_DATE = ?, LAST_UPD_USER_ID = ?, REC_STAT_CODE = ?" );

      query.append( " WHERE CTC_NBR = "
                    + tplContactCustEntity_.getData().getCtcNbr()
                    + " AND CUST_NBR = "
                    + tplContactCustEntity_.getData().getCustNbr() );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( tplContactCustEntity_.getData().getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplContactCustEntity_.getData().getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( tplContactCustEntity_.getData().getLastAuthUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplContactCustEntity_.getData().getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplContactCustEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplContactCustEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( tplContactCustEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplContactCustEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplContactCustEntity_.getData().getRecStatCode() != null )
      {
        preparedStatement.setString( count++,
                             tplContactCustEntity_.getData().getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

	  preparedStatement.replaceParametersInQuery(query.toString());
      preparedStatement.executeUpdate();
	  
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

  /**
   * Remove uma linha na tabela TPL_CONTACT_CUST de acordo com ID passado como
   * parâmetro.
   * @param entityKey_
   * @throws UnexpectedException
   * @author Hamilton Matos
   * @date 27/03/2007
   */

  public void deleteBatch( TplContactCustEntity tplContactCustEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "UPDATE " );
      query.append( C_TABLE_NAME );
      query.append( " SET " );
      query.append( "LAST_AUTH_DATE = ?, LAST_AUTH_USER_ID = ?, LAST_UPD_DATE = ?, LAST_UPD_USER_ID = ?, REC_STAT_CODE = ?" );

      String criteria = "";

      if ( tplContactCustEntity_.getData().getCustNbr() != null
           && tplContactCustEntity_.getData().getCustNbr().longValue() != 0 )
      {
        criteria = criteria + C_CUST_NBR + " = ? AND ";
      }

      if ( tplContactCustEntity_.getData().getCtcNbr() != null
           && tplContactCustEntity_.getData().getCtcNbr().longValue() != 0 )
      {
        criteria = criteria + C_CTC_NBR + " = ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( " WHERE " + criteria );
      }

      int count = 1;

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      if ( tplContactCustEntity_.getData().getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplContactCustEntity_.getData().getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( tplContactCustEntity_.getData().getLastAuthUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplContactCustEntity_.getData().getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplContactCustEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplContactCustEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( tplContactCustEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplContactCustEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplContactCustEntity_.getData().getRecStatCode() != null )
      {
        preparedStatement.setString( count++,
                             tplContactCustEntity_.getData().getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplContactCustEntity_.getData().getCustNbr() != null
           && tplContactCustEntity_.getData().getCustNbr().longValue() != 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplContactCustEntity_.getData().getCustNbr().longValue() );
      }

      if ( tplContactCustEntity_.getData().getCtcNbr() != null
           && tplContactCustEntity_.getData().getCtcNbr().longValue() != 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplContactCustEntity_.getData().getCtcNbr().longValue() );
      }

	  preparedStatement.replaceParametersInQuery(query.toString());
      preparedStatement.executeUpdate();
	  
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

  /**
   * Cria uma entidade representando um registro da tabela TPL_CONTACT_CUST com
   * os dados do ID passado como parametro
   * @param entityKey_
   * @throws UnexpectedException
   * @author Hamilton Matos
   * @date 27/03/2007
   */

  public BaseTplContactCustEntity find(
                                       BaseTplContactCustEntity tplContactCustEntity_ )
  {
    ResultSet resultSet = null;
    CitiStatement preparedStatement = null;
    ManagedRdbConnection connection = null;
    StringBuffer query = new StringBuffer();
    ArrayList tplContactCustEntities;
    BaseTplContactCustEntity contactCustEntity = null;
    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_TABLE_COLUMNS );
      query.append( " FROM " );
      query.append( C_TABLE_NAME );
      query.append( " WHERE " );
      query.append( " CTC_NBR = ? AND CUST_NBR = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      preparedStatement.setLong(
                         1,
                         tplContactCustEntity_.getData().getCtcNbr().longValue() );
      preparedStatement.setLong(
                         2,
                         tplContactCustEntity_.getData().getCustNbr().longValue() );

	  preparedStatement.replaceParametersInQuery(query.toString());
      resultSet = preparedStatement.executeQuery();
	  

      tplContactCustEntities = instantiateFromResultSet( resultSet );
      if ( tplContactCustEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplContactCustEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        contactCustEntity = ( BaseTplContactCustEntity ) tplContactCustEntities.get( 0 );
      }
      return contactCustEntity;
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

  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
                                                                    throws UnexpectedException
  {

    TplContactCustEntity tplContactCustEntity;
    TplContactCustEntityVO tplContactCustEntityVO;
    ArrayList oracleTplContactCustEntities = new ArrayList();
    try
    {
      while ( resultSet_.next() )
      {

        tplContactCustEntity = new TplContactCustEntity();

        tplContactCustEntityVO = ( TplContactCustEntityVO ) tplContactCustEntity.getData();

        tplContactCustEntityVO.setCtcNbr( new BigInteger(
                                                          resultSet_.getString( this.C_CTC_NBR ) ) );

        tplContactCustEntityVO.setCustNbr( new BigInteger(
                                                           resultSet_.getString( this.C_CUST_NBR ) ) );

        if ( resultSet_.getString( this.C_FULL_NAME_TEXT ) != null )
        {
          tplContactCustEntityVO.setFullNameText( resultSet_.getString( this.C_FULL_NAME_TEXT ) );
        }
        
		if ( resultSet_.getString( this.C_FULL_NAME_TEXT_2 ) != null )
		{
		  tplContactCustEntityVO.setFullNameText_2(resultSet_.getString( this.C_FULL_NAME_TEXT_2 )) ;
		}
		
		if ( resultSet_.getString( this.C_FULL_NAME_TEXT_3 ) != null )
		{
		  tplContactCustEntityVO.setFullNameText_3(resultSet_.getString( this.C_FULL_NAME_TEXT_3 )) ;
		}

        if ( resultSet_.getDate( this.C_LAST_AUTH_DATE ) != null )
        {
          tplContactCustEntityVO.setLastAuthDate( resultSet_.getDate( this.C_LAST_AUTH_DATE ) );
        }

        if ( resultSet_.getString( this.C_LAST_AUTH_USER_ID ) != null )
        {
          tplContactCustEntityVO.setLastAuthUserId( resultSet_.getString( this.C_LAST_AUTH_USER_ID ) );
        }

        if ( resultSet_.getDate( this.C_LAST_UPD_DATE ) != null )
        {
          tplContactCustEntityVO.setLastUpdDate( resultSet_.getDate( this.C_LAST_UPD_DATE ) );
        }

        if ( resultSet_.getString( this.C_LAST_UPD_USER_ID ) != null )
        {
          tplContactCustEntityVO.setLastUpdUserId( resultSet_.getString( this.C_LAST_UPD_USER_ID ) );
        }

        if ( resultSet_.getString( this.C_PHONE_DDD_CODE ) != null )
        {
          tplContactCustEntityVO.setPhoneDddCode( new BigInteger(
                                                                  resultSet_.getString( this.C_PHONE_DDD_CODE ) ) );
        }

        if ( resultSet_.getString( this.C_PHONE_EXTN_NBR ) != null )
        {
          tplContactCustEntityVO.setPhoneExtnNbr( new BigInteger(
                                                                  resultSet_.getString( this.C_PHONE_EXTN_NBR ) ) );
        }

        if ( resultSet_.getString( this.C_PHONE_NBR ) != null )
        {
          tplContactCustEntityVO.setPhoneNbr( new BigInteger(
                                                              resultSet_.getString( this.C_PHONE_NBR ) ) );
        }

        if ( resultSet_.getString( this.C_REC_STAT_CODE ) != null )
        {
          tplContactCustEntityVO.setRecStatCode( resultSet_.getString( this.C_REC_STAT_CODE ) );
        }

        oracleTplContactCustEntities.add( tplContactCustEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    
    return oracleTplContactCustEntities;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplContactCustDAO#list(java.math.BigInteger,
   *      java.math.BigInteger, java.lang.String)
   */
  public DataSet list( BigInteger custNbr_, BigInteger ctcNbrSrc_,
                      String fullNameTextSrc_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet resultSetDataSet = null;
    StringBuffer query = new StringBuffer();
    String contactCust = "contactCust";
    String customerPrvt = "customerPrvt";

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( contactCust + "." + C_CUST_NBR + ", " );
      query.append( contactCust + "." + C_CTC_NBR + ", " );
      query.append( contactCust + "." + C_FULL_NAME_TEXT + ", " );
      query.append( contactCust + "." + C_PHONE_DDD_CODE + ", " );
      query.append( contactCust + "." + C_PHONE_NBR + ", " );
      query.append( contactCust + "." + C_PHONE_EXTN_NBR + " " );
      query.append( " FROM " );
      query.append( C_TPL_CONTACT_CUST + " contactCust, " );
      query.append( OracleTplCustomerPrvtDAO.C_TPL_CUSTOMER_PRVT
                    + " " + customerPrvt + " " );

      String criteria = " ";

      if ( custNbr_ != null && custNbr_.longValue() != 0 )
      {
        criteria = criteria + "AND contactCust."
                   + OracleTplCustomerPrvtDAO.C_CUST_NBR + " = ? ";
      }

      if ( ctcNbrSrc_ != null && !( ctcNbrSrc_.toString().equals( "" ) ) )
      {
        criteria = criteria + "AND contactCust." + C_CTC_NBR + " = ? ";
      }

      if ( fullNameTextSrc_ != null && !( fullNameTextSrc_.equals( "" ) ) )
      {
        criteria = criteria + "AND UPPER (contactCust." + C_FULL_NAME_TEXT
                   + ") LIKE ( ? ) ";
      }

      criteria = criteria + " ORDER BY contactCust." + C_FULL_NAME_TEXT;

      query.append( "WHERE " + customerPrvt + ".CUST_NBR = contactCust.CUST_NBR AND contactCust.REC_STAT_CODE != '"
                    + BaseTplContactCustEntity.C_REC_STAT_CODE_INACTIVE
                    + "'"
                    + criteria );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( custNbr_ != null && custNbr_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
      }

      if ( ctcNbrSrc_ != null && ctcNbrSrc_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, ctcNbrSrc_.longValue() );
      }

      if ( fullNameTextSrc_ != null && !( fullNameTextSrc_.equals( "" ) ) )
      {
        preparedStatement.setString( count++, "%" + fullNameTextSrc_.toUpperCase()
                                              + "%" );
      }

	  preparedStatement.replaceParametersInQuery(query.toString());
      resultSet = preparedStatement.executeQuery();
	  

      resultSetDataSet = new ResultSetDataSet( resultSet );

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

    return resultSetDataSet;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplContactCustDAO#existsActive(com.citibank.ods.entity.pl.TplContactCustEntity)
   */
  public boolean existsActive( TplContactCustEntity tplContactCustEntity_ )
  {
    boolean exists = true;

    try
    {
      TplContactCustEntity contactCustEntity = ( TplContactCustEntity ) this.find( tplContactCustEntity_ );
      TplContactCustEntityVO contactCustEntityVO = ( TplContactCustEntityVO ) contactCustEntity.getData();
      if ( !TplContactCustEntity.C_REC_STAT_CODE_ACTIVE.equals( contactCustEntityVO.getRecStatCode() ) )
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
  
  public Integer getNextContactCustNbr()
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    Integer nextVal = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      //Alteraçao G&P INICIO3 20/05/2008
      query.append("SELECT MAX(CTC_NBR) AS ULTIMO FROM " + C_TPL_CONTACT_CUST);
	  //Alteraçao G&P FIM3 20/05/2008
      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

	  preparedStatement.replaceParametersInQuery(query.toString());
      resultSet = preparedStatement.executeQuery();
	  

      if ( resultSet.next() )
      {
		//Alteraçao G&P INICIO3 20/05/2008
        nextVal = new Integer( resultSet.getInt( "ULTIMO" ) + 1);
		//Alteraçao G&P FIM3 20/05/2008
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
  
  public Integer getNextValContactCustNbr()
	{
	  ManagedRdbConnection connection = null;
	  CitiStatement preparedStatement = null;
	  ResultSet resultSet = null;
	  StringBuffer query = new StringBuffer();
	  Integer nextVal = null;

	  try
	  {
		connection = OracleODSDAOFactory.getConnection();
		
		query.append("select PL.SQ_CONTACT_CUST_CTC_NBR.Nextval id from dual");
		
		preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

		preparedStatement.replaceParametersInQuery(query.toString());
		resultSet = preparedStatement.executeQuery();

		if ( resultSet.next() )
		{		  
		  nextVal = new Integer( resultSet.getInt( "id" ));		  
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