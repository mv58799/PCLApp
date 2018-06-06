/*
 * Created on Jan 17, 2007
 *
 */
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
import com.citibank.ods.entity.pl.BaseTplCustomerPrvtCmplEntity;
import com.citibank.ods.entity.pl.TplContactCustEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplEntity;
import com.citibank.ods.entity.pl.valueobject.TplCustomerPrvtCmplEntityVO;
import com.citibank.ods.persistence.pl.dao.TplContactCustDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author gerson.a.rodrigues
 *  
 */
public class OracleTplCustomerPrvtCmplDAO extends
    BaseOracleTplCustomerPrvtCmplDAO implements TplCustomerPrvtCmplDAO
{
  public static String C_TPL_CUSTOMER_PRVT_CMPL = C_PL_SCHEMA
                                                  + "TPL_CUSTOMER_PRVT_CMPL";

  public static String C_TBG_OFFICER = C_BG_SCHEMA + "TBG_OFFICER";

  public static String C_TPL_CUSTOMER_PRVT = C_PL_SCHEMA + "TPL_CUSTOMER_PRVT";

  public static String C_STATUS_INATIVO = "I";

  public static final String C_PRVT_CUST_ATTD_STAT_CODE = "PRVT_CUST_ATTD_STAT_CODE";

  /**
   * Verifica se existe o cliente
   * 
   * @param customerFncVO
   * @return
   */
  public boolean exists( TplCustomerPrvtCmplEntity tplCustomerPrvtCmplEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tplCustomerPrvtCmplEntity_ );
    }
    catch ( NoRowsReturnedException e )
    {
      exists = false;
    }

    return exists;
  }

  /**
   * Consulta de Costumer
   */
  public DataSet list( String emNbr_, BigInteger glbRevenSysOffcrNbr_,
                      BigInteger prvtCustNbr_, BigInteger prvtKeyNbr_,
                      BigInteger custNbr_, BigInteger wealthPotnlCode_,
                      BigInteger OffcrNbr_, BigInteger classCmplcCode_,
                      String recStatCode_, String custFullName_ )
	                  
  {

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " SELECT CUSTCMPL." );
      query.append( C_EM_NBR + ", CUSTCMPL." );
      query.append( C_GLB_REVEN_SYS_OFFCR_NBR + ", CUSTCMPL." );
      query.append( C_PRVT_CUST_NBR + ", CUST." );
      query.append( C_CUST_FULL_NAME_TEXT + ", CUSTCMPL." );
      query.append( C_PRVT_KEY_NBR + ", CUSTCMPL." );
      query.append( C_LAST_AUTH_DATE + ", CUSTCMPL." );
      query.append( C_LAST_AUTH_USER_ID + ", CUSTCMPL." );
      query.append( C_LAST_UPD_DATE + ", CUSTCMPL." );
      query.append( C_LAST_UPD_USER_ID + ", CUSTCMPL." );
      query.append( C_REC_STAT_CODE + ", CUSTCMPL." );
      query.append( C_WEALTH_POTNL_CODE + ", CUSTCMPL." );
      query.append( C_CUST_NBR + ", CUSTCMPL." );
      query.append( C_OFFCR_NBR + ", OFFC." );
      query.append( C_OFFCR_NAME_TEXT + ", CUSTCMPL." );
      query.append( C_CLASS_CMPLC_CODE );
      query.append( "	FROM " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL + " CUSTCMPL , " );
      query.append( C_TPL_CUSTOMER_PRVT + " CUST , " );
      query.append( C_TBG_OFFICER + " OFFC " );
      query.append( " WHERE " );
      query.append( " CUST." + C_CUST_NBR + " = CUSTCMPL." + C_CUST_NBR
                    + " AND " );
      query.append( " CUSTCMPL." + C_OFFCR_NBR + " =  OFFC." + C_OFFCR_NBR
                    + "(+)" + " AND " );
      query.append( "CUSTCMPL." + C_REC_STAT_CODE + " = ?" );

      String criteria = "";
      if ( prvtCustNbr_ != null )
      {
        criteria = "CUSTCMPL." + C_PRVT_CUST_NBR + " = ? AND ";
      }

      if ( prvtKeyNbr_ != null )
      {
        criteria = criteria + "CUSTCMPL." + C_PRVT_KEY_NBR + " = ? AND ";
      }

      if ( wealthPotnlCode_ != null )
      {
        criteria = criteria + "CUSTCMPL." + C_WEALTH_POTNL_CODE + "= ? AND ";
      }
      
      if ( custNbr_ != null )
      {
        criteria = criteria + "CUSTCMPL." + C_CUST_NBR + "= ? AND ";
      }

      if ( OffcrNbr_ != null )
      {
        criteria = criteria + "CUSTCMPL." + C_OFFCR_NBR + "= ? AND ";
      }

      if ( classCmplcCode_ != null )
      {
        criteria = criteria + "CUSTCMPL." + C_CLASS_CMPLC_CODE + "= ? AND ";
      }

      if ( emNbr_ != null && !emNbr_.equals( "" ) )
      {
        criteria = criteria + "UPPER(CUSTCMPL." + C_EM_NBR + ") like ? AND ";
      }

      if ( glbRevenSysOffcrNbr_ != null && glbRevenSysOffcrNbr_.longValue() > 0 )
      {
        criteria = criteria + "CUSTCMPL." + C_GLB_REVEN_SYS_OFFCR_NBR
                   + "= ? AND ";
      }
      if ( custFullName_ != null && !custFullName_.equals( "" ) )
      {
        criteria = criteria + "UPPER(CUST." + C_CUST_FULL_NAME_TEXT
                   + " )like ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	AND " + criteria );
      }

      query.append( " ORDER BY CUST." + C_CUST_FULL_NAME_TEXT );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( recStatCode_ != null && recStatCode_.length() > 0 )
      {
        preparedStatement.setString( count++, recStatCode_ );
      }
      else
      {
        preparedStatement.setString( count++, BaseODSEntity.C_REC_STAT_CODE_ACTIVE );
      }

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
      
      if ( emNbr_ != null && !emNbr_.equals( "" ) )
      {
        preparedStatement.setString( count++, emNbr_.toUpperCase() );
      }

      if ( glbRevenSysOffcrNbr_ != null && glbRevenSysOffcrNbr_.longValue() > 0 )
      {
        preparedStatement.setLong( count++, glbRevenSysOffcrNbr_.longValue() );
      }
      if ( custFullName_ != null && !custFullName_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + custFullName_.toUpperCase() + "%" );
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

  /**
   * Consulta de Costumer
   */
  public boolean search( TplCustomerPrvtCmplEntity tplCustomerPrvtCmplEntity_ )
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
      query.append( C_TPL_CUSTOMER_PRVT_CMPL );

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
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pcl.dao.ICustomerDAO#insertCustomer(java.lang.String[])
   */
  public TplCustomerPrvtCmplEntity insert(
                                          TplCustomerPrvtCmplEntity tplCustomerPrvtCmplEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_CUSTOMER_PRVT_CMPL + " (" );
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
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,? )" );

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
        preparedStatement.setString( count++, null );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getPrvtKeyNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCustomerPrvtCmplEntity_.getData().getPrvtKeyNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
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

      if ( ( ( TplCustomerPrvtCmplEntityVO ) tplCustomerPrvtCmplEntity_.getData() ).getRecStatCode() != null
           && ( ( TplCustomerPrvtCmplEntityVO ) tplCustomerPrvtCmplEntity_.getData() ).getRecStatCode() != "" )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplCustomerPrvtCmplEntityVO ) tplCustomerPrvtCmplEntity_.getData() ).getRecStatCode() );
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
        preparedStatement.setString( count++, null );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCustomerPrvtCmplEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
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
        preparedStatement.setString( count++, null );
      }
//	  Alteraçao G&P INICIO - 19/05/2008
	  if ( tplCustomerPrvtCmplEntity_.getData().getCustPrvtStatCode() != null )
	  {
		preparedStatement.setString(
							 count++,
							 tplCustomerPrvtCmplEntity_.getData().getCustPrvtStatCode() );
	  }
	  else
	  {
		preparedStatement.setString( count++, null );
	  }

      if ( tplCustomerPrvtCmplEntity_.getData().getClassCmplcCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCustomerPrvtCmplEntity_.getData().getClassCmplcCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
	  if ( tplCustomerPrvtCmplEntity_.getData().getPrvtCustTypeCode() != null )
			{
			  preparedStatement.setLong(
								 count++,
								 tplCustomerPrvtCmplEntity_.getData().getPrvtCustTypeCode().longValue() );
			}
			else
			{
			  preparedStatement.setString( count++, null );
			}
//	  Alteraçao G&P FIM


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

    return tplCustomerPrvtCmplEntity_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pcl.dao.ICustomerDAO#alterCustomer(java.lang.String[])
   */
  public void update( TplCustomerPrvtCmplEntity tplCustomerPrvtCmplEntity_ )
  {

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " UPDATE " ).append( C_TPL_CUSTOMER_PRVT_CMPL );
      query.append( " SET " );
      query.append( C_MAIL_RECV_IND ).append( " = ?, " );
      query.append( C_OFFCL_MAIL_RECV_IND ).append( " = ?, " );
      query.append( C_GLB_REVEN_SYS_OFFCR_NBR ).append( " = ?, " );
      query.append( C_PRVT_CUST_NBR ).append( "= ?, " );
      query.append( C_PRVT_KEY_NBR ).append( " = ?, " );
      query.append( C_LAST_UPD_DATE ).append( " = ?, " );
      query.append( C_LAST_UPD_USER_ID ).append( " = ?, " );
      query.append( C_REC_STAT_CODE ).append( "= ?, " );
      query.append( C_WEALTH_POTNL_CODE ).append( "= ?, " );
      query.append( C_OFFCR_NBR ).append( "= ?, " );
      query.append( C_CLASS_CMPLC_CODE ).append( "= ?, " );
      query.append( C_PRVT_CUST_ATTD_STAT_CODE).append( "= ?, " );
      query.append( C_PRVT_CUST_TYPE_CODE).append("= ?, " );
      query.append( C_EM_NBR).append("= ?, " );
      query.append( C_LAST_AUTH_DATE).append("= ?, " );
      query.append( C_LAST_AUTH_USER_ID).append("= ? " );
	  query.append( " WHERE " );
      query.append( C_CUST_NBR ).append( "= ?" );



      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

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
        preparedStatement.setLong( count++, 0 );
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

      if ( ( ( TplCustomerPrvtCmplEntityVO ) tplCustomerPrvtCmplEntity_.getData() ).getRecStatCode() != null
           && ( ( TplCustomerPrvtCmplEntityVO ) tplCustomerPrvtCmplEntity_.getData() ).getRecStatCode() != "" )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplCustomerPrvtCmplEntityVO ) tplCustomerPrvtCmplEntity_.getData() ).getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getWealthPotnlCode() != null
           && tplCustomerPrvtCmplEntity_.getData().getWealthPotnlCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplCustomerPrvtCmplEntity_.getData().getWealthPotnlCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getOffcrNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplCustomerPrvtCmplEntity_.getData().getOffcrNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplCustomerPrvtCmplEntity_.getData().getClassCmplcCode() != null
           && tplCustomerPrvtCmplEntity_.getData().getClassCmplcCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           tplCustomerPrvtCmplEntity_.getData().getClassCmplcCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

	  if ( tplCustomerPrvtCmplEntity_.getData().getCustPrvtStatCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplCustomerPrvtCmplEntity_.getData().getCustPrvtStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

	  if ( tplCustomerPrvtCmplEntity_.getData().getPrvtCustTypeCode() != null
		   && tplCustomerPrvtCmplEntity_.getData().getPrvtCustTypeCode().longValue() > 0 )
	  {
	     preparedStatement.setLong(count++,tplCustomerPrvtCmplEntity_.getData().getPrvtCustTypeCode().longValue() );
	  }
	  else
	  {
	    preparedStatement.setString( count++, null );
	  }
      
      
      
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

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pcl.dao.ICustomerDAO#deleteCustomer(java.lang.String[])
   */
  public void delete( TplCustomerPrvtCmplEntity tplCustomerPrvtCmplEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();
    TplCustomerPrvtCmplEntityVO tplCustomerPrvtCmplEntityVO = ( TplCustomerPrvtCmplEntityVO ) tplCustomerPrvtCmplEntity_.getData();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " UPDATE " + C_TPL_CUSTOMER_PRVT_CMPL );
      query.append( " SET " + C_REC_STAT_CODE + " = ? " );
      query.append( " WHERE " + C_CUST_NBR + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplCustomerPrvtCmplEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++,
                             tplCustomerPrvtCmplEntityVO.getRecStatCode() );
      }
      if ( tplCustomerPrvtCmplEntityVO.getCustNbr() != null )
      {
        preparedStatement.setLong( count++,
                           tplCustomerPrvtCmplEntityVO.getCustNbr().longValue() );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();

      TplContactCustDAO tplContactCustDAO = odsDAOFactory.getTplContactCustDAO();
      TplContactCustEntity tplContactCustEntity = new TplContactCustEntity();
      tplContactCustEntity.getData().setCustNbr(
                                                 tplCustomerPrvtCmplEntityVO.getCustNbr() );
      tplContactCustEntity.getData().setRecStatCode(
                                                     tplCustomerPrvtCmplEntityVO.getRecStatCode() );
      tplContactCustEntity.getData().setLastAuthDate(
                                                      tplCustomerPrvtCmplEntityVO.getLastAuthDate() );
      tplContactCustEntity.getData().setLastAuthUserId(
                                                        tplCustomerPrvtCmplEntityVO.getLastAuthUserId() );
      tplContactCustEntity.getData().setLastUpdDate(
                                                     tplCustomerPrvtCmplEntityVO.getLastUpdDate() );
      tplContactCustEntity.getData().setLastUpdUserId(
                                                       tplCustomerPrvtCmplEntityVO.getLastUpdUserId() );

      tplContactCustDAO.deleteBatch( ( TplContactCustEntity ) tplContactCustEntity );

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
   * @see com.citibank.ods.persistence.pcl.dao.ICustomerDAO#detailCustomer(java.lang.String[])
   */
  public BaseTplCustomerPrvtCmplEntity find(
                                            BaseTplCustomerPrvtCmplEntity tplCustomerPrvtCmplEntity_ )
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
      query.append( C_TPL_CUSTOMER_PRVT_CMPL+"."+ C_EM_NBR + ", " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL+"."+ C_MAIL_RECV_IND + ", " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL+"."+ C_OFFCL_MAIL_RECV_IND + ", " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL+"."+ C_GLB_REVEN_SYS_OFFCR_NBR + ", " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL+"."+ C_PRVT_CUST_NBR + ", " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL+"."+ C_PRVT_KEY_NBR + ", " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL+"."+ C_LAST_AUTH_DATE + ", " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL+"."+ C_LAST_AUTH_USER_ID + ", " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL+"."+ C_LAST_UPD_DATE + ", " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL+"."+ C_LAST_UPD_USER_ID + ", " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL+"."+ C_REC_STAT_CODE + ", " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL+"."+ C_WEALTH_POTNL_CODE + ", " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL+"."+ C_CUST_NBR + ", " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL+"."+ C_OFFCR_NBR + ", " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL+"."+ C_PRVT_CUST_ATTD_STAT_CODE + ", " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL+"."+ C_CLASS_CMPLC_CODE + ", ");
      query.append( C_TPL_CUSTOMER_PRVT_CMPL+"."+ C_PRVT_CUST_TYPE_CODE );
      
      query.append( " FROM " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL );
      //query.append( C_TPL_ER_EM + " " + C_TPL_ER_EM_ALIAS);
      query.append( " WHERE " + C_CUST_NBR + " = ? " );
      //query.append( " AND " + C_TPL_CUSTOMER_PRVT_CMPL + "." + C_EM_NBR + " = "  + C_TPL_ER_EM_ALIAS + "." + C_EM_NBR + "(+)" );
      //query.append( " AND " + C_TPL_ER_EM_ALIAS + "." + C_ROLE_CUST_CODE + "(+) = '01'" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         tplCustomerPrvtCmplEntity_.getData().getCustNbr().longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      tplCustomerCmplEntities = instantiateFromResultSet( resultSet );

      if ( tplCustomerCmplEntities.size() == 0 )
      {
        //throw new NoRowsReturnedException();
        tplCustomerPrvtCmplEntity = null;
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

  /*
   * Cria uma lista de entities a partir de um resultset
   */
  private ArrayList instantiateFromResultSet( ResultSet rs_ )
  {

    TplCustomerPrvtCmplEntity customerPrvtCmplEntity;
    TplCustomerPrvtCmplEntityVO customerEntityVO;
    ArrayList tplCustomerCmplEntities = new ArrayList();

    try
    {
      while ( rs_.next() )
      {
        customerPrvtCmplEntity = new TplCustomerPrvtCmplEntity();
        customerEntityVO = ( TplCustomerPrvtCmplEntityVO ) customerPrvtCmplEntity.getData();
        customerEntityVO.setCustNbr( new BigInteger( rs_.getString( C_CUST_NBR ) ) );
        if ( rs_.getString( C_CLASS_CMPLC_CODE ) != null )
        {
          customerEntityVO.setClassCmplcCode( ( new BigInteger(
                                                                rs_.getString( C_CLASS_CMPLC_CODE ) ) ) );

        }
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

	if (rs_.getString( C_PRVT_CUST_TYPE_CODE) != null)
	{
	  customerEntityVO.setPrvtCustTypeCode( new BigInteger(
		  					      rs_.getString( C_PRVT_CUST_TYPE_CODE))); 	
	}
	        
        tplCustomerCmplEntities.add( customerPrvtCmplEntity );
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
   * @see com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplDAO#existsActive(com.citibank.ods.entity.pl.TplCustomerPrvtCmplEntity)
   */
  public boolean existsActive(
                              TplCustomerPrvtCmplEntity tplCustomerPrvtCmplEntity_ )
  {
    boolean exists = true;

    try
    {
      TplCustomerPrvtCmplEntity tplCustomerPrvtCmplEntity = ( TplCustomerPrvtCmplEntity ) this.find( tplCustomerPrvtCmplEntity_ );
      if ( tplCustomerPrvtCmplEntity != null )
      {
        if ( !TplCustomerPrvtCmplEntity.C_REC_STAT_CODE_ACTIVE.equals( ( ( TplCustomerPrvtCmplEntityVO ) tplCustomerPrvtCmplEntity.getData() ).getRecStatCode() ) )
        {
          exists = false;
        }
      }
      else
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
   * Verifica se existe algum registro com este EM Nbr
   */
  public boolean existsEmNbr( String emNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    boolean existsEm = false;

    try
    {
      query.append( "SELECT " );
      query.append( "COUNT ( " + C_EM_NBR + ") " );
      query.append( "FROM " + C_TPL_CUSTOMER_PRVT_CMPL );
      query.append( " WHERE " );
      query.append( C_EM_NBR + " like ? " );

      connection = OracleODSDAOFactory.getConnection();

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++, emNbr_ );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.next() )
      {
        int rowNumber = resultSet.getInt( 1 );
        if ( rowNumber > 0 )
        {
          existsEm = true;
        }
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

    return existsEm;
  }

  /*
   * Recupera a entidade a partir do número Em informado
   */
  public TplCustomerPrvtCmplEntity findEmNbr(
                                             TplCustomerPrvtCmplEntity tplCustomerPrvtCmplEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList tplCustomerCmplEntities = new ArrayList();
    TplCustomerPrvtCmplEntity tplCustomerPrvtCmplEntity;
    TplCustomerPrvtCmplEntity customerPrvtCmplEntity;
    TplCustomerPrvtCmplEntityVO customerEntityVO;

    try
    {
      query.append( "SELECT " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_EM_NBR );
      query.append( " FROM " + C_TPL_CUSTOMER_PRVT_CMPL );
      query.append( " WHERE " );
      query.append( C_EM_NBR + " = ? " );

      connection = OracleODSDAOFactory.getConnection();

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString( 1, tplCustomerPrvtCmplEntity_.getData().getEmNbr() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      while ( resultSet.next() )
      {
        customerPrvtCmplEntity = new TplCustomerPrvtCmplEntity();
        customerEntityVO = ( TplCustomerPrvtCmplEntityVO ) customerPrvtCmplEntity.getData();
        customerEntityVO.setCustNbr( new BigInteger(
                                                     resultSet.getString( C_CUST_NBR ) ) );
        customerEntityVO.setEmNbr( resultSet.getString( C_EM_NBR ) );

        tplCustomerCmplEntities.add( customerPrvtCmplEntity );
      }

      if ( tplCustomerCmplEntities.size() == 0 )
      {
        tplCustomerPrvtCmplEntity = null;
      }
      else if ( tplCustomerCmplEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplCustomerPrvtCmplEntity = ( TplCustomerPrvtCmplEntity ) tplCustomerCmplEntities.get( 0 );
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
    return tplCustomerPrvtCmplEntity;
  }
}