/*
 * Created on Mar 6, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplCustomerPrvtCmplEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplCustomerPrvtCmplMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class OracleTplCustomerPrvtCmplMovDAO extends
    BaseOracleTplCustomerPrvtCmplDAO implements TplCustomerPrvtCmplMovDAO
{
  private static final String C_OPERN_CODE = "OPERN_CODE";

  protected static final String C_OPERN_TEXT = "OPERN_TEXT";

  private static final String C_TPL_CUSTOMER_PRVT_CMPL_MOV = C_PL_SCHEMA
                                                             + "TPL_CUSTOMER_PRVT_CMPL_MOV";

  private static final String C_TPL_CUSTOMER_PRVT = C_PL_SCHEMA
                                                    + "TPL_CUSTOMER_PRVT";

  private static final String C_TBG_OFFICER = C_BG_SCHEMA + "TBG_OFFICER";

  //Status do cliente
  public static final String C_PRVT_CUST_ATTD_STAT_CODE = "PRVT_CUST_ATTD_STAT_CODE";

  /**
   * Este método busca uma lista dos dados complementares de cliente que se
   * enquadre com os critérios informados.
   */
  public DataSet list( String emNbr_, BigInteger glbRevenSysOffcrNbr_,
                      BigInteger prvtCustNbr_, BigInteger prvtKeyNbr_,
                      BigInteger custNbr_, BigInteger wealthPotnlCode_,
                      BigInteger offcrNbr_, BigInteger classCmplcCode_,
                      String lastUpdUserId_, String custFullName_,
                      String officerName_, String custPrvtStatCode_,
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
      query.append( " SELECT CUSTMOV." );
      query.append( C_EM_NBR + ", CUSTMOV." );
      query.append( C_MAIL_RECV_IND + ", CUSTMOV." );
      query.append( C_OFFCL_MAIL_RECV_IND + ", CUSTMOV." );
      query.append( C_GLB_REVEN_SYS_OFFCR_NBR + ", CUSTMOV." );
      query.append( C_PRVT_CUST_NBR + ", CUSTMOV." );
      query.append( C_PRVT_KEY_NBR + ", CUSTMOV." );
      query.append( C_LAST_AUTH_DATE + ", CUSTMOV." );
      query.append( C_LAST_AUTH_USER_ID + ", CUSTMOV." );
      query.append( C_LAST_UPD_DATE + ", CUSTMOV." );
      query.append( C_LAST_UPD_USER_ID + ", CUSTMOV." );
      query.append( C_OPERN_CODE + ", CUSTMOV." );
      query.append( C_WEALTH_POTNL_CODE + ", CUSTMOV." );
      query.append( C_CUST_NBR + ", CUST." );
      query.append( C_CUST_FULL_NAME_TEXT + ", CUSTMOV." );
      query.append( C_OFFCR_NBR + ", OFFC." );
      query.append( C_OFFCR_NAME_TEXT + ", CUSTMOV." );
      query.append( C_CLASS_CMPLC_CODE + ", CUSTMOV." );
      query.append( C_PRVT_CUST_TYPE_CODE );
      query.append( " FROM " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL_MOV + " CUSTMOV " );
      query.append( " INNER JOIN " );
      query.append( C_TPL_CUSTOMER_PRVT + " CUST " );
      query.append( " ON " );
      query.append( " CUSTMOV." + C_CUST_NBR + " = " + "CUST." + C_CUST_NBR );
      query.append( " LEFT JOIN " );
      query.append( C_TBG_OFFICER + " OFFC " );
      query.append( " ON " );
      query.append( " CUSTMOV." + C_OFFCR_NBR + " = " + " OFFC. " + C_OFFCR_NBR );

      String criteria = "";
      if ( custNbr_ != null )
      {
        criteria = criteria + "CUST." + C_CUST_NBR + " = ? AND ";
      }
      if ( prvtKeyNbr_ != null )
      {
        criteria = criteria + C_PRVT_KEY_NBR + " = ? AND ";
      }
      if ( wealthPotnlCode_ != null )
      {
        criteria = criteria + C_WEALTH_POTNL_CODE + "= ? AND ";
      }
      if ( offcrNbr_ != null )
      {
        criteria = criteria + "OFFC." + C_OFFCR_NBR + "= ? AND ";
      }
      if ( classCmplcCode_ != null )
      {
        criteria = criteria + C_CLASS_CMPLC_CODE + "= ? AND ";
      }
      if ( prvtCustTypeCode_ != null) 
      {
      	criteria = criteria + C_PRVT_CUST_TYPE_CODE + "= ? AND ";     
      }
      if ( emNbr_ != null && !"".equals( emNbr_ ) )
      {
        criteria = criteria + "UPPER(\"" + C_EM_NBR + "\") like ? AND ";
      }
      if ( glbRevenSysOffcrNbr_ != null )
      {
        criteria = criteria + C_GLB_REVEN_SYS_OFFCR_NBR + "= ? AND ";
      }
      if ( prvtCustNbr_ != null )
      {
        criteria = criteria + C_PRVT_CUST_NBR + "= ? AND ";
      }
      if ( lastUpdUserId_ != null && lastUpdUserId_.length() > 0 )
      {
        criteria = criteria + "UPPER(\"" + C_LAST_UPD_USER_ID
                   + "\") like ? AND ";
      }
      if ( custFullName_ != null && !custFullName_.equals( "" ) )
      {

        criteria = criteria + "UPPER ( CUST." + C_CUST_FULL_NAME_TEXT
                   + ") like ? AND ";
      }

      if ( officerName_ != null && !officerName_.equals( "" ) )
      {

        criteria = criteria + "UPPER ( OFFC." + C_OFFCR_NAME_TEXT
                   + ") like ? AND ";
      }

      if ( custPrvtStatCode_ != null && !custPrvtStatCode_.equals( "" ) )
      {
        criteria = criteria + "CUSTMOV." + C_PRVT_CUST_ATTD_STAT_CODE + " =? AND ";
      }
      if ( criteria.length() > 5 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria );
      }

      query.append( " ORDER BY CUST." + C_CUST_FULL_NAME_TEXT );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( custNbr_ != null )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
      }
      if ( prvtKeyNbr_ != null )
      {
        preparedStatement.setLong( count++, prvtKeyNbr_.longValue() );
      }
      if ( wealthPotnlCode_ != null )
      {
        preparedStatement.setLong( count++, wealthPotnlCode_.longValue() );
      }
      if ( offcrNbr_ != null )
      {
        preparedStatement.setLong( count++, offcrNbr_.longValue() );
      }
      if ( classCmplcCode_ != null )
      {
        preparedStatement.setLong( count++, classCmplcCode_.longValue() );
      }
	  if ( prvtCustTypeCode_ != null )
	  {
		  preparedStatement.setLong( count++, prvtCustTypeCode_.longValue() );
	  }
      if ( emNbr_ != null && !"".equals( emNbr_ ) )
      {
        preparedStatement.setString( count++, emNbr_.toUpperCase() );
      }

      if ( glbRevenSysOffcrNbr_ != null )
      {
        preparedStatement.setLong( count++, glbRevenSysOffcrNbr_.longValue() );
      }
      if ( prvtCustNbr_ != null )
      {
        preparedStatement.setLong( count++, prvtCustNbr_.longValue() );
      }
      if ( lastUpdUserId_ != null && lastUpdUserId_.length() > 0 )
      {
        preparedStatement.setString( count++, "%" + lastUpdUserId_.toUpperCase() + "%" );
      }
      if ( custFullName_ != null && !custFullName_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + custFullName_.toUpperCase() + "%" );
      }

      if ( officerName_ != null && !officerName_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + officerName_.toUpperCase() + "%" );
      }
      if ( custPrvtStatCode_ != null && !custPrvtStatCode_.equals( "" ) )
      {
        preparedStatement.setString( count++, custPrvtStatCode_ );
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

    String[] codeColumn = { C_OPERN_CODE };
    String[] nameColumn = { C_OPERN_TEXT };
    rsds.outerJoin( ODSConstraintDecoder.decodeOpern(), codeColumn, codeColumn,
                    nameColumn );

    return rsds;
  }

  /**
   * Consulta de Customer
   */
  public boolean search( TplCustomerPrvtCmplMovEntity tplCustomerPrvtCmplEntity_ )
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
      query.append( C_WEALTH_POTNL_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_OFFCR_NBR + ", " );
      query.append( C_CLASS_CMPLC_CODE + ", " );
      query.append( C_PRVT_CUST_TYPE_CODE );
      query.append( "	FROM " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL_MOV );

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
   * @see com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplDAO#detailCustomer(com.citibank.ods.modules.client.customer.functionality.valueobject.CustomerCurrentDetailFncVO)
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
      query.append( C_OPERN_CODE + ", " );
      query.append( C_WEALTH_POTNL_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_OFFCR_NBR + ", " );
      query.append( C_PRVT_CUST_ATTD_STAT_CODE + ", " );
      query.append( C_CLASS_CMPLC_CODE + ", " );
	  query.append( C_PRVT_CUST_TYPE_CODE );
      query.append( " FROM " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL_MOV );
      query.append( " WHERE " + C_CUST_NBR + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         ( baseTplCustomerPrvtCmplEntity_.getData().getCustNbr().longValue() ) );

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
    TplCustomerPrvtCmplMovEntity customerPrvtCmplMovEntity;
    TplCustomerPrvtCmplMovEntityVO customerMovEntityVO;
    ArrayList tplCustomerCmplEntities = new ArrayList();

    try
    {
      while ( rs_.next() )
      {
        customerPrvtCmplMovEntity = new TplCustomerPrvtCmplMovEntity();
        customerMovEntityVO = ( TplCustomerPrvtCmplMovEntityVO ) customerPrvtCmplMovEntity.getData();
        customerMovEntityVO.setCustNbr( new BigInteger(
                                                        rs_.getString( C_CUST_NBR ) ) );
        customerMovEntityVO.setEmNbr( rs_.getString( C_EM_NBR ) );
        if ( rs_.getDate( C_LAST_AUTH_DATE ) != null )
        {
        	customerMovEntityVO.setLastAuthDate( new Date(rs_.getDate( C_LAST_AUTH_DATE ).getTime()) );
        }
        customerMovEntityVO.setLastAuthUserId( rs_.getString( C_LAST_AUTH_USER_ID ) );
        if ( rs_.getString( C_CLASS_CMPLC_CODE ) != null )
        {
          customerMovEntityVO.setClassCmplcCode( new BigInteger(
                                                                 rs_.getString( C_CLASS_CMPLC_CODE ) ) );
        }
        if ( rs_.getDate( C_LAST_UPD_DATE ) != null )
        {
        	customerMovEntityVO.setLastUpdDate( new Date(rs_.getTimestamp( C_LAST_UPD_DATE ).getTime()) );
         }
        customerMovEntityVO.setLastUpdUserId( rs_.getString( C_LAST_UPD_USER_ID ) );
        customerMovEntityVO.setMailRecvInd( rs_.getString( C_MAIL_RECV_IND ) );
        customerMovEntityVO.setOffclMailRecvInd( rs_.getString( C_OFFCL_MAIL_RECV_IND ) );
        if ( rs_.getString( C_PRVT_KEY_NBR ) != null )
        {
          customerMovEntityVO.setPrvtKeyNbr( new BigInteger(
                                                                   rs_.getString( C_PRVT_KEY_NBR ) ) );
        }
        if ( rs_.getString( C_OFFCR_NBR ) != null )
        {
          customerMovEntityVO.setOffcrNbr( new BigInteger(
                                                           rs_.getString( C_OFFCR_NBR ) ) );
        }
        if ( rs_.getString( C_GLB_REVEN_SYS_OFFCR_NBR ) != null )
        {
          customerMovEntityVO.setGlbRevenSysOffcrNbr( new BigInteger(
                                                                      rs_.getString( C_GLB_REVEN_SYS_OFFCR_NBR ) ) );
        }
        if ( rs_.getString( C_PRVT_CUST_NBR ) != null )
        {
          customerMovEntityVO.setPrvtCustNbr( new BigInteger(
                                                              rs_.getString( C_PRVT_CUST_NBR ) ) );
        }
        if ( rs_.getString( C_WEALTH_POTNL_CODE ) != null )
        {
          customerMovEntityVO.setWealthPotnlCode( new BigInteger(
                                                                  rs_.getString( C_WEALTH_POTNL_CODE ) ) );
        }

		if ( rs_.getString( C_PRVT_CUST_TYPE_CODE ) != null )
		{
		  customerMovEntityVO.setPrvtCustTypeCode( new BigInteger(
		  													    rs_.getString( C_PRVT_CUST_TYPE_CODE ) ) );
		}
        
        customerMovEntityVO.setOpernCode( rs_.getString( C_OPERN_CODE ) );
        customerMovEntityVO.setCustPrvtStatCode( rs_.getString( C_PRVT_CUST_ATTD_STAT_CODE ) );

        tplCustomerCmplEntities.add( customerPrvtCmplMovEntity );
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
   * @see com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplDAO#existsCustomer(com.citibank.ods.entity.pl.TplCustomerPrvtCmplEntity)
   */
  public boolean exists(
                        TplCustomerPrvtCmplMovEntity tplCustomerPrvtCmplMovEntity_ )
  {
    boolean exists = true;

    BaseTplCustomerPrvtCmplEntity baseTplCustomerPrvtCmplEntity = this.find( tplCustomerPrvtCmplMovEntity_ );

    if ( baseTplCustomerPrvtCmplEntity == null )
    {
      exists = false;
    }

    return exists;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplMovDAO#update(com.citibank.ods.entity.pl.TplCustomerPrvtCmplMovEntity)
   */
  public void update( TplCustomerPrvtCmplMovEntity customerPrvtCmplEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_CUSTOMER_PRVT_CMPL_MOV + " SET " );
      query.append( C_CLASS_CMPLC_CODE + "= ?," );
      query.append( C_EM_NBR + "= ?," );
      query.append( C_GLB_REVEN_SYS_OFFCR_NBR + "= ?," );
      query.append( C_LAST_UPD_DATE + "= ?," );
      query.append( C_LAST_UPD_USER_ID + "= ?," );
      query.append( C_MAIL_RECV_IND + "= ?," );
      query.append( C_OFFCL_MAIL_RECV_IND + "= ?," );
      query.append( C_OFFCR_NBR + "= ?," );
      query.append( C_OPERN_CODE + "= ?, " );
      query.append( C_PRVT_CUST_NBR + "= ?, " );
      query.append( C_PRVT_KEY_NBR + "= ?, " );
      query.append( C_PRVT_CUST_ATTD_STAT_CODE + " = ?, " );
      query.append( C_WEALTH_POTNL_CODE + "= ?, " );
	  query.append( C_PRVT_CUST_TYPE_CODE + "= ? " );
      query.append( "WHERE " + C_CUST_NBR + "= ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( customerPrvtCmplEntity_.getData().getClassCmplcCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           customerPrvtCmplEntity_.getData().getClassCmplcCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

	        
      if ( customerPrvtCmplEntity_.getData().getEmNbr() != null )
      {
        preparedStatement.setString( count++,
                             customerPrvtCmplEntity_.getData().getEmNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( customerPrvtCmplEntity_.getData().getGlbRevenSysOffcrNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           customerPrvtCmplEntity_.getData().getGlbRevenSysOffcrNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      
      if (customerPrvtCmplEntity_.getData().getLastUpdDate() != null) {
          preparedStatement.setTimestamp(
                  count++,
                  new Timestamp(
                                     customerPrvtCmplEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }
      
      if ( customerPrvtCmplEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             customerPrvtCmplEntity_.getData().getLastUpdUserId() );

      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( customerPrvtCmplEntity_.getData().getMailRecvInd() != null )
      {
        preparedStatement.setString( count++,
                             customerPrvtCmplEntity_.getData().getMailRecvInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( customerPrvtCmplEntity_.getData().getOffclMailRecvInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             customerPrvtCmplEntity_.getData().getOffclMailRecvInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( customerPrvtCmplEntity_.getData().getOffcrNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           customerPrvtCmplEntity_.getData().getOffcrNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      preparedStatement.setString(
                           count++,
                           ( ( TplCustomerPrvtCmplMovEntityVO ) customerPrvtCmplEntity_.getData() ).getOpernCode() );

      if ( customerPrvtCmplEntity_.getData().getPrvtCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           customerPrvtCmplEntity_.getData().getPrvtCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( customerPrvtCmplEntity_.getData().getPrvtKeyNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           customerPrvtCmplEntity_.getData().getPrvtKeyNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( customerPrvtCmplEntity_.getData().getCustPrvtStatCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             customerPrvtCmplEntity_.getData().getCustPrvtStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( customerPrvtCmplEntity_.getData().getWealthPotnlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           customerPrvtCmplEntity_.getData().getWealthPotnlCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
	  if ( customerPrvtCmplEntity_.getData().getPrvtCustTypeCode() != null )
	  {
		preparedStatement.setLong(
	                       count++,
						   customerPrvtCmplEntity_.getData().getPrvtCustTypeCode().longValue() );
	  }
	  else
	  {
	    preparedStatement.setString( count++, null );
	  }
      
      preparedStatement.setLong(
                         count++,
                         customerPrvtCmplEntity_.getData().getCustNbr().longValue() );
	  preparedStatement.replaceParametersInQuery(query.toString());
      preparedStatement.executeQuery();
	  
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
   * @see com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplMovDAO#delete(com.citibank.ods.entity.pl.TplCustomerPrvtCmplMovEntity)
   */
  public void delete( TplCustomerPrvtCmplMovEntity customerPrvtCmplEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "DELETE FROM " );
      query.append( C_TPL_CUSTOMER_PRVT_CMPL_MOV );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         customerPrvtCmplEntity_.getData().getCustNbr().longValue() );

      preparedStatement.executeQuery();
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
   * @see com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplMovDAO#insert(com.citibank.ods.entity.pl.TplCustomerPrvtCmplMovEntity)
   */
  public TplCustomerPrvtCmplMovEntity insert(
                                             TplCustomerPrvtCmplMovEntity customerPrvtCmplMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_CUSTOMER_PRVT_CMPL_MOV + " ( " );
      query.append( C_CLASS_CMPLC_CODE + ", " );
      query.append( C_EM_NBR + ", " );
      query.append( C_GLB_REVEN_SYS_OFFCR_NBR + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_MAIL_RECV_IND + ", " );
      query.append( C_OFFCL_MAIL_RECV_IND + ", " );
      query.append( C_OFFCR_NBR + ", " );
      query.append( C_OPERN_CODE + ", " );
      query.append( C_PRVT_CUST_NBR + ", " );
      query.append( C_PRVT_KEY_NBR + ", " );
      query.append( C_WEALTH_POTNL_CODE + ", " );
      query.append( C_PRVT_CUST_ATTD_STAT_CODE + ", " );
	  query.append( C_PRVT_CUST_TYPE_CODE + ", " );
      query.append( C_CUST_NBR + " )" );
      query.append( " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( customerPrvtCmplMovEntity_.getData().getClassCmplcCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           customerPrvtCmplMovEntity_.getData().getClassCmplcCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
	  
      
      preparedStatement.setString( count++,
                           customerPrvtCmplMovEntity_.getData().getEmNbr() );

      if ( customerPrvtCmplMovEntity_.getData().getGlbRevenSysOffcrNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           customerPrvtCmplMovEntity_.getData().getGlbRevenSysOffcrNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if (customerPrvtCmplMovEntity_.getData().getLastUpdDate() != null) {
    	  preparedStatement.setTimestamp(
                              count++,
                              new Timestamp(
                                             customerPrvtCmplMovEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }


      preparedStatement.setString(
                           count++,
                           customerPrvtCmplMovEntity_.getData().getLastUpdUserId() );
      preparedStatement.setString(
                           count++,
                           customerPrvtCmplMovEntity_.getData().getMailRecvInd() );

      preparedStatement.setString(
                           count++,
                           customerPrvtCmplMovEntity_.getData().getOffclMailRecvInd() );

      if ( customerPrvtCmplMovEntity_.getData().getOffcrNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           customerPrvtCmplMovEntity_.getData().getOffcrNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      preparedStatement.setString(
                           count++,
                           ( ( TplCustomerPrvtCmplMovEntityVO ) customerPrvtCmplMovEntity_.getData() ).getOpernCode() );

      if ( customerPrvtCmplMovEntity_.getData().getPrvtCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           customerPrvtCmplMovEntity_.getData().getPrvtCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( customerPrvtCmplMovEntity_.getData().getPrvtKeyNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           customerPrvtCmplMovEntity_.getData().getPrvtKeyNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( customerPrvtCmplMovEntity_.getData().getWealthPotnlCode() != null
           && customerPrvtCmplMovEntity_.getData().getWealthPotnlCode().longValue() > 0 )
      {
        preparedStatement.setLong(
                           count++,
                           customerPrvtCmplMovEntity_.getData().getWealthPotnlCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

	  if ( customerPrvtCmplMovEntity_.getData().getCustPrvtStatCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             customerPrvtCmplMovEntity_.getData().getCustPrvtStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
	        
	  if ( customerPrvtCmplMovEntity_.getData().getPrvtCustTypeCode() != null )
	  {
	    preparedStatement.setLong(count++,customerPrvtCmplMovEntity_.getData().getPrvtCustTypeCode().longValue() );
	  }
	  else
	  {
	    preparedStatement.setString( count++, null );
	  }
      
      if ( customerPrvtCmplMovEntity_.getData().getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           customerPrvtCmplMovEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
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
    return customerPrvtCmplMovEntity_;
  }
}