/*
 * Created on Mar 9, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
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
import com.citibank.ods.entity.pl.BaseTplCustomerPrvtCmplEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplCustomerPrvtCmplHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class OracleTplCustomerPrvtCmplHistDAO extends
    BaseOracleTplCustomerPrvtCmplDAO implements TplCustomerPrvtCmplHistDAO
{

  private static final String C_TPL_CUSTOMER_PRVT_CMPL_HIST = C_PL_SCHEMA
                                                              + "TPL_CUSTOMER_PRVT_CMPL_HIST";

  private String C_EFF_STA_DATE = "EFF_STA_DATE";

  //Status do cliente
  public static final String C_PRVT_CUST_ATTD_STAT_CODE = "PRVT_CUST_ATTD_STAT_CODE";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.CustomerPrvtCmplDAO#findCustomer(com.citibank.ods.entity.pl.BaseTplCustomerPrvtCmplEntity)
   */
  public BaseTplCustomerPrvtCmplEntity find(
                                            BaseTplCustomerPrvtCmplEntity baseTplCustomerPrvtCmplEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList tplCustomerCmplEntities;
    BaseTplCustomerPrvtCmplEntity tplCustomerPrvtCmplEntity;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_EM_NBR + ", " );
      query.append( C_MAIL_RECV_IND + ", " );
      query.append( C_OFFCL_MAIL_RECV_IND + ", " );
      query.append( C_GLB_REVEN_SYS_OFFCR_NBR + ", " );
      query.append( C_PRVT_CUST_NBR + ", " );
      query.append( C_PRVT_KEY_NBR + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_WEALTH_POTNL_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_OFFCR_NBR + ", " );
      query.append( C_PRVT_CUST_ATTD_STAT_CODE + ", " );
      query.append( C_CLASS_CMPLC_CODE + ", " );
	  query.append( C_PRVT_CUST_TYPE_CODE );
      query.append( " FROM " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL_HIST );
      query.append( " WHERE " + C_CUST_NBR + " = ? AND " );
      query.append( "TRUNC( " + C_EFF_STA_DATE + ") = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         baseTplCustomerPrvtCmplEntity_.getData().getCustNbr().longValue() );

      Date custRefDate = ( ( TplCustomerPrvtCmplHistEntityVO ) baseTplCustomerPrvtCmplEntity_.getData() ).getCustRefDate();
      preparedStatement.setTimestamp( 2, new Timestamp( custRefDate.getTime() ) );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      tplCustomerCmplEntities = instantiateFromResultSet( resultSet );

      if ( tplCustomerCmplEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplCustomerCmplEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplCustomerPrvtCmplEntity = ( BaseTplCustomerPrvtCmplEntity ) tplCustomerCmplEntities.get( 0 );
      }

      return tplCustomerPrvtCmplEntity;
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
   * Consulta de Costumer
   */
  public boolean search(
                        TplCustomerPrvtCmplHistEntity tplCustomerPrvtCmplEntity_ )
  {

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " SELECT " );
      query.append( C_EM_NBR + ", " );
      query.append( C_GLB_REVEN_SYS_OFFCR_NBR + ", " );
      query.append( C_PRVT_CUST_NBR + ", " );
      query.append( C_PRVT_KEY_NBR + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_WEALTH_POTNL_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_OFFCR_NBR + ", " );
      query.append( C_CLASS_CMPLC_CODE + ", " );
	  query.append( C_PRVT_CUST_TYPE_CODE );
      query.append( "	FROM " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL_HIST );

      String criteria = "";
      if ( tplCustomerPrvtCmplEntity_.getData().getPrvtCustNbr() != null )
      {
        criteria = C_PRVT_CUST_NBR + " = ? AND ";
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getPrvtKeyNbr() != null )
      {
        criteria = criteria + C_PRVT_KEY_NBR + " = ? AND ";
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getWealthPotnlCode() != null )
      {
        criteria = criteria + C_WEALTH_POTNL_CODE + "= ? AND ";
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getCustNbr() != null )
      {
        criteria = criteria + C_CUST_NBR + "= ? AND ";
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getOffcrNbr() != null )
      {
        criteria = criteria + C_OFFCR_NBR + "= ? AND ";
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getClassCmplcCode() != null )
      {
        criteria = criteria + C_CLASS_CMPLC_CODE + "= ? AND ";
      }

	  if ( tplCustomerPrvtCmplEntity_.getData().getPrvtCustTypeCode() != null )
	  {
		criteria = criteria + C_PRVT_CUST_TYPE_CODE + "= ? AND ";
	  }
      
      if ( tplCustomerPrvtCmplEntity_.getData().getEmNbr() != null
           && !tplCustomerPrvtCmplEntity_.getData().getEmNbr().equals( "" ) )
      {
        criteria = " " + criteria + C_EM_NBR + "= ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplCustomerPrvtCmplEntity_.getData().getPrvtCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCustomerPrvtCmplEntity_.getData().getPrvtCustNbr().longValue() );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getPrvtKeyNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCustomerPrvtCmplEntity_.getData().getPrvtKeyNbr().longValue() );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getWealthPotnlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCustomerPrvtCmplEntity_.getData().getWealthPotnlCode().longValue() );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCustomerPrvtCmplEntity_.getData().getCustNbr().longValue() );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getOffcrNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCustomerPrvtCmplEntity_.getData().getOffcrNbr().longValue() );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getClassCmplcCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCustomerPrvtCmplEntity_.getData().getClassCmplcCode().longValue() );
      }

	  if ( tplCustomerPrvtCmplEntity_.getData().getPrvtCustTypeCode() != null )
	  {
		preparedStatement.setLong(
						   count++,
						   tplCustomerPrvtCmplEntity_.getData().getPrvtCustTypeCode().longValue() );
	 }
      
      if ( tplCustomerPrvtCmplEntity_.getData().getEmNbr() != null
           && !tplCustomerPrvtCmplEntity_.getData().getEmNbr().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplCustomerPrvtCmplEntity_.getData().getEmNbr() );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      rsds = new ResultSetDataSet( resultSet );
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

    return ( rsds.size() > 0 );
  }

  /*
   * Cria uma lista de entities a partir de um resultset
   */
  private ArrayList instantiateFromResultSet( ResultSet rs_ )
  {

    TplCustomerPrvtCmplHistEntity customerPrvtCmplHistEntity;
    TplCustomerPrvtCmplHistEntityVO customerEntityVO;
    ArrayList tplCustomerCmplEntities = new ArrayList();

    try
    {
      while ( rs_.next() )
      {
        customerPrvtCmplHistEntity = new TplCustomerPrvtCmplHistEntity();
        customerEntityVO = ( TplCustomerPrvtCmplHistEntityVO ) customerPrvtCmplHistEntity.getData();
        customerEntityVO.setCustNbr( new BigInteger( rs_.getString( C_CUST_NBR ) ) );
        customerEntityVO.setCustRefDate( rs_.getDate( C_EFF_STA_DATE ) );
        customerEntityVO.setEmNbr( rs_.getString( C_EM_NBR ) );
        customerEntityVO.setLastAuthDate( rs_.getDate( C_LAST_AUTH_DATE ) );
        customerEntityVO.setLastAuthUserId( rs_.getString( C_LAST_AUTH_USER_ID ) );
        customerEntityVO.setLastUpdDate( rs_.getDate( C_LAST_UPD_DATE ) );
        customerEntityVO.setLastUpdUserId( rs_.getString( C_LAST_UPD_USER_ID ) );
        customerEntityVO.setMailRecvInd( rs_.getString( C_MAIL_RECV_IND ) );
        customerEntityVO.setOffclMailRecvInd( rs_.getString( C_OFFCL_MAIL_RECV_IND ) );
        customerEntityVO.setCustPrvtStatCode( rs_.getString( C_PRVT_CUST_ATTD_STAT_CODE ) );
        if ( rs_.getString( C_PRVT_KEY_NBR ) != null )
        {
          customerEntityVO.setPrvtKeyNbr( new BigInteger(
                                                                rs_.getString( C_PRVT_KEY_NBR ) ) );
        }
        if ( rs_.getString( C_OFFCR_NBR ) != null )
        {
          customerEntityVO.setOffcrNbr( new BigInteger(
                                                        rs_.getString( C_OFFCR_NBR ) ) );
        }
        if ( rs_.getString( C_GLB_REVEN_SYS_OFFCR_NBR ) != null )
        {
          customerEntityVO.setGlbRevenSysOffcrNbr( new BigInteger(
                                                                   rs_.getString( C_GLB_REVEN_SYS_OFFCR_NBR ) ) );
        }
        if ( rs_.getString( C_PRVT_CUST_NBR ) != null )
        {
          customerEntityVO.setPrvtCustNbr( new BigInteger(
                                                           rs_.getString( C_PRVT_CUST_NBR ) ) );
        }
        customerEntityVO.setRecStatCode( rs_.getString( C_REC_STAT_CODE ) );
        if ( rs_.getString( C_WEALTH_POTNL_CODE ) != null )
        {
          customerEntityVO.setWealthPotnlCode( new BigInteger(
                                                               rs_.getString( C_WEALTH_POTNL_CODE ) ) );
        }
		if ( rs_.getString( C_PRVT_CUST_TYPE_CODE ) != null )
		{
		  customerEntityVO.setPrvtCustTypeCode( new BigInteger(
		  						                               rs_.getString( C_PRVT_CUST_TYPE_CODE ) ) );
		}

        tplCustomerCmplEntities.add( customerPrvtCmplHistEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return tplCustomerCmplEntities;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplHistDAO#listCustomer(java.lang.String,
   *      java.lang.String, java.lang.String, java.math.BigInteger,
   *      java.math.BigInteger, java.math.BigInteger, java.math.BigInteger,
   *      java.math.BigInteger, java.math.BigInteger, java.math.BigInteger,
   *      java.util.Date)
   */
  public DataSet list( String emNbr_, String mailRecvInd_,
                      String offclMailRecvInd_,
                      BigInteger glbRevenSysOffcrNbr_, BigInteger prvtCustNbr_,
                      BigInteger prvtKeyNbr_, Long custNbr_,
                      Long wealthPotnlCode_, BigInteger OffcrNbr_,
                      BigInteger classCmplcCode_, Date custRefDate_,
	                  BigInteger prvtCustTypeCode_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " SELECT " );
      query.append( C_EM_NBR + ", " );
      query.append( C_MAIL_RECV_IND + ", " );
      query.append( C_OFFCL_MAIL_RECV_IND + ", " );
      query.append( C_GLB_REVEN_SYS_OFFCR_NBR + ", " );
      query.append( C_PRVT_CUST_NBR + ", " );
      query.append( C_PRVT_KEY_NBR + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_WEALTH_POTNL_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_OFFCR_NBR + ", " );
      query.append( C_CLASS_CMPLC_CODE + ", " );
      query.append( C_CUST_REF_DATE + ", " );
      query.append( C_PRVT_CUST_TYPE_CODE + " ");
      query.append( "	FROM " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL_HIST );

      String criteria = "";
      if ( prvtCustNbr_ != null )
      {
        criteria = C_PRVT_CUST_NBR + " = ? AND ";
      }
      if ( prvtKeyNbr_ != null )
      {
        criteria = criteria + C_PRVT_KEY_NBR + " = ? AND ";
      }
      if ( wealthPotnlCode_ != null )
      {
        criteria = criteria + C_WEALTH_POTNL_CODE + "= ? AND ";
      }
      if ( custNbr_ != null )
      {
        criteria = criteria + C_CUST_NBR + "= ? AND ";
      }
      if ( OffcrNbr_ != null )
      {
        criteria = criteria + C_OFFCR_NBR + "= ? AND ";
      }
      if ( classCmplcCode_ != null )
      {
        criteria = criteria + C_CLASS_CMPLC_CODE + "= ? AND ";
      }
	  if ( prvtCustTypeCode_ != null )
	  {
	    criteria = criteria + C_PRVT_CUST_TYPE_CODE + "= ? AND ";
	  }
      if ( emNbr_ != null && emNbr_ != "" )
      {
        criteria = criteria + "\") like ? AND " + C_EM_NBR + "\") like ? AND";
      }
      if ( custRefDate_ != null )
      {
        criteria = criteria + C_EFF_STA_DATE + "= ? AND ";
      }

	  if ( criteria.length() > 5 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( prvtCustNbr_ != null )
      {
        preparedStatement.setLong( count++, prvtCustNbr_.longValue() );
      }
      if ( prvtKeyNbr_ != null )
      {
        preparedStatement.setLong( count++, prvtKeyNbr_.longValue() );
      }
      if ( wealthPotnlCode_ != null )
      {
        preparedStatement.setLong( count++, wealthPotnlCode_.longValue() );
      }
	  if ( custNbr_ != null )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
      }
      if ( OffcrNbr_ != null )
      {
        preparedStatement.setLong( count++, OffcrNbr_.longValue() );
      }
      if ( classCmplcCode_ != null )
      {
        preparedStatement.setLong( count++, classCmplcCode_.longValue() );
      }
	  if ( prvtCustTypeCode_ != null )
	  {
	    preparedStatement.setLong( count++, prvtCustTypeCode_.longValue() );
	  }
      if ( emNbr_ != null && !emNbr_.equals( "" ) )
      {
        preparedStatement.setString( count++, emNbr_.toUpperCase() );
      }
      if ( custRefDate_ != null )
      {
        preparedStatement.setTimestamp( count++,
                                new java.sql.Timestamp( custRefDate_.getTime() ) );
      }
	  resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      rsds = new ResultSetDataSet( resultSet );
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

    return rsds;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplHistDAO#insert(com.citibank.ods.entity.pl.TplCustomerPrvtCmplHistEntity)
   */
  public TplCustomerPrvtCmplHistEntity insert(
                                              TplCustomerPrvtCmplHistEntity tplCustomerPrvtCmplEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_CUSTOMER_PRVT_CMPL_HIST + " (" );
      query.append( C_EM_NBR + ", " );
      query.append( C_MAIL_RECV_IND + ", " );
      query.append( C_OFFCL_MAIL_RECV_IND + ", " );
      query.append( C_GLB_REVEN_SYS_OFFCR_NBR + ", " );
      query.append( C_PRVT_CUST_NBR + ", " );
      query.append( C_PRVT_KEY_NBR + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_WEALTH_POTNL_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_OFFCR_NBR + ", " );
      query.append( C_CLASS_CMPLC_CODE + ", " );
	  query.append( C_CUST_REF_DATE + ", " );
	  query.append( C_PRVT_CUST_TYPE_CODE  + ", "); 
	  query.append( C_PRVT_CUST_ATTD_STAT_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplCustomerPrvtCmplEntity_.getData().getEmNbr() != null
           && tplCustomerPrvtCmplEntity_.getData().getEmNbr() != "" )
      {
        preparedStatement.setString( count++,
                             tplCustomerPrvtCmplEntity_.getData().getEmNbr() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getMailRecvInd() != null
           && tplCustomerPrvtCmplEntity_.getData().getMailRecvInd() != "" )
      {
        preparedStatement.setString(
                             count++,
                             tplCustomerPrvtCmplEntity_.getData().getMailRecvInd() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getOffclMailRecvInd() != null
           && tplCustomerPrvtCmplEntity_.getData().getOffclMailRecvInd() != "" )
      {
        preparedStatement.setString(
                             count++,
                             tplCustomerPrvtCmplEntity_.getData().getOffclMailRecvInd() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getGlbRevenSysOffcrNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCustomerPrvtCmplEntity_.getData().getGlbRevenSysOffcrNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getPrvtCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCustomerPrvtCmplEntity_.getData().getPrvtCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getPrvtKeyNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCustomerPrvtCmplEntity_.getData().getPrvtKeyNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplCustomerPrvtCmplEntity_.getData().getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getLastAuthUserId() != null
           && tplCustomerPrvtCmplEntity_.getData().getLastAuthUserId() != "" )
      {
        preparedStatement.setString(
                             count++,
                             tplCustomerPrvtCmplEntity_.getData().getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplCustomerPrvtCmplEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getLastUpdUserId() != null
           && tplCustomerPrvtCmplEntity_.getData().getLastUpdUserId() != "" )
      {
        preparedStatement.setString(
                             count++,
                             tplCustomerPrvtCmplEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( ( ( TplCustomerPrvtCmplHistEntityVO ) tplCustomerPrvtCmplEntity_.getData() ).getRecStatCode() != null
           && ( ( TplCustomerPrvtCmplHistEntityVO ) tplCustomerPrvtCmplEntity_.getData() ).getRecStatCode() != "" )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplCustomerPrvtCmplHistEntityVO ) tplCustomerPrvtCmplEntity_.getData() ).getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getWealthPotnlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCustomerPrvtCmplEntity_.getData().getWealthPotnlCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCustomerPrvtCmplEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getOffcrNbr() != null
           && tplCustomerPrvtCmplEntity_.getData().getOffcrNbr().longValue() != 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplCustomerPrvtCmplEntity_.getData().getOffcrNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getClassCmplcCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCustomerPrvtCmplEntity_.getData().getClassCmplcCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

	  if ( ( ( TplCustomerPrvtCmplHistEntityVO ) tplCustomerPrvtCmplEntity_.getData() ).getCustRefDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplCustomerPrvtCmplHistEntityVO ) tplCustomerPrvtCmplEntity_.getData() ).getCustRefDate().getTime() ) );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

	  if ( tplCustomerPrvtCmplEntity_.getData().getPrvtCustTypeCode() != null )
	  {
	    preparedStatement.setLong(
		   				   count++,
						   tplCustomerPrvtCmplEntity_.getData().getPrvtCustTypeCode().longValue() );
	  }
	  else
	  {
		preparedStatement.setString(count++,null);
	  }
	  
	  if ( tplCustomerPrvtCmplEntity_.getData().getCustPrvtStatCode() != null )
	  {
		  preparedStatement.setString(count++, tplCustomerPrvtCmplEntity_.getData().getCustPrvtStatCode() );
	  }
	  else
	  {
	  	  preparedStatement.setString( count++, null );
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

    return tplCustomerPrvtCmplEntity_;
  }
}