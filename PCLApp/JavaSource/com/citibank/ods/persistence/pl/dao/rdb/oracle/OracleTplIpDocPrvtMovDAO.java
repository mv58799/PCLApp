package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplDocTransferEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocCallbackEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocPrvtEntity;
import com.citibank.ods.entity.pl.TplDocTransferMovEntity;
import com.citibank.ods.entity.pl.TplIpDocCallbackMovEntity;
import com.citibank.ods.entity.pl.TplIpDocPrvtMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplDocTransferMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplIpDocCallbackMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplIpDocPrvtMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplDocTransferMovDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocCallbackMovDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 12, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class OracleTplIpDocPrvtMovDAO extends BaseOracleTplIpDocPrvtDAO
    implements TplIpDocPrvtMovDAO
{

  public String C_OPERN_CODE = "OPERN_CODE";

  public String C_TPL_IP_DOC_PRVT_MOV = C_PL_SCHEMA
                                        + "TPL_PRMNT_INSTR_PRVT_MOV";

  private String C_CUST_FULL_NAME_TEXT = "CUST_FULL_NAME_TEXT";  

  protected String C_OPERN_TEXT = "OPERN_TEXT";

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocPrvtMovDAO#insert(com.citibank.ods.entity.pl.TplIpDocPrvtMovEntity)
   */
  public TplIpDocPrvtMovEntity insert(
                                      TplIpDocPrvtMovEntity tplIpDocPrvtMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_IP_DOC_PRVT_MOV + " (" );
      query.append( C_IP_DOC_CODE + ", " );
      query.append( C_IP_DOC_TEXT + ", " );
      query.append( C_IP_INVST_CUR_ACCT_IND + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_OPERN_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?)" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplIpDocPrvtMovEntity_.getData().getIpDocCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocPrvtMovEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplIpDocPrvtMovEntity_.getData().getIpDocText() != null )
      {
        preparedStatement.setString( count++,
                             tplIpDocPrvtMovEntity_.getData().getIpDocText() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocPrvtMovEntity_.getData().getIpInvstCurAcctInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplIpDocPrvtMovEntity_.getData().getIpInvstCurAcctInd() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocPrvtMovEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplIpDocPrvtMovEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocPrvtMovEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplIpDocPrvtMovEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( tplIpDocPrvtMovEntity_.getData().getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocPrvtMovEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( ( ( TplIpDocPrvtMovEntityVO ) tplIpDocPrvtMovEntity_.getData() ).getOpernCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplIpDocPrvtMovEntityVO ) tplIpDocPrvtMovEntity_.getData() ).getOpernCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
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
    
    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();

    Iterator ipDocCallbackEntities = tplIpDocPrvtMovEntity_.getBaseIpDocCallbackEntities().iterator();
    BaseTplIpDocCallbackEntity tplIpDocCallbackEntity;

    TplIpDocCallbackMovDAO ipDocCallbackMovDAO = odsDAOFactory.getTplIpDocCallbackMovDAO();
    while ( ipDocCallbackEntities.hasNext() )
    {
      tplIpDocCallbackEntity = ( BaseTplIpDocCallbackEntity ) ipDocCallbackEntities.next();
      ipDocCallbackMovDAO.insert( ( TplIpDocCallbackMovEntity ) tplIpDocCallbackEntity );
    }

    Iterator docTransferEntities = tplIpDocPrvtMovEntity_.getBaseDocTransferEntities().iterator();
    BaseTplDocTransferEntity tplDocTransferMovEntity;

    TplDocTransferMovDAO docTransferMovDAO = odsDAOFactory.getTplDocTransferMovDAO();
    while ( docTransferEntities.hasNext() )
    {
      tplDocTransferMovEntity = ( BaseTplDocTransferEntity ) docTransferEntities.next();
      docTransferMovDAO.insert( ( TplDocTransferMovEntity ) tplDocTransferMovEntity );
    }

    return ( TplIpDocPrvtMovEntity ) find( tplIpDocPrvtMovEntity_ );

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocPrvtMovDAO#update(com.citibank.ods.entity.pl.TplIpDocPrvtMovEntity)
   */
  public void update( TplIpDocPrvtMovEntity tplIpDocPrvtMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_IP_DOC_PRVT_MOV + " SET " );
      query.append( C_IP_DOC_TEXT + " = ?, " );
      query.append( C_IP_INVST_CUR_ACCT_IND + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_OPERN_CODE + " = ? " );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + " = ? AND " );
      query.append( C_IP_DOC_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplIpDocPrvtMovEntity_.getData().getIpDocText() != null )
      {
        preparedStatement.setString( count++,
                             tplIpDocPrvtMovEntity_.getData().getIpDocText() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocPrvtMovEntity_.getData().getIpInvstCurAcctInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplIpDocPrvtMovEntity_.getData().getIpInvstCurAcctInd() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocPrvtMovEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplIpDocPrvtMovEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocPrvtMovEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplIpDocPrvtMovEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( ( ( TplIpDocPrvtMovEntityVO ) tplIpDocPrvtMovEntity_.getData() ).getOpernCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplIpDocPrvtMovEntityVO ) tplIpDocPrvtMovEntity_.getData() ).getOpernCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplIpDocPrvtMovEntity_.getData().getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocPrvtMovEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT );
      }

      if ( tplIpDocPrvtMovEntity_.getData().getIpDocCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocPrvtMovEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT );
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

    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();

    Iterator ipDocCallbackEntities = tplIpDocPrvtMovEntity_.getBaseIpDocCallbackEntities().iterator();
    TplIpDocCallbackMovEntity tplIpDocCallbackEntity;

    TplIpDocCallbackMovDAO ipDocCallbackMovDAO = odsDAOFactory.getTplIpDocCallbackMovDAO();

    TplIpDocCallbackMovEntity tplIpDocCallbackMovEntity = new TplIpDocCallbackMovEntity();
    tplIpDocCallbackMovEntity.getData().setIpDocCode(
                                                      tplIpDocPrvtMovEntity_.getData().getIpDocCode() );
    tplIpDocCallbackMovEntity.getData().setCustNbr(
                                                    tplIpDocPrvtMovEntity_.getData().getCustNbr() );
    ipDocCallbackMovDAO.delete( tplIpDocCallbackMovEntity );

    while ( ipDocCallbackEntities.hasNext() )
    {
    	tplIpDocCallbackEntity = (TplIpDocCallbackMovEntity) ipDocCallbackEntities.next();

    	if ( ipDocCallbackMovDAO.exists( tplIpDocCallbackEntity ) )
    	{
    		ipDocCallbackMovDAO.update( tplIpDocCallbackEntity );
    	}
    	else
    	{
    		ipDocCallbackMovDAO.insert( tplIpDocCallbackEntity );
    	}

    }

    Iterator docTransferEntities = tplIpDocPrvtMovEntity_.getBaseDocTransferEntities().iterator();
    BaseTplDocTransferEntity tplDocTransferMovEntity;

    TplDocTransferMovDAO docTransferMovDAO = odsDAOFactory.getTplDocTransferMovDAO();

    TplDocTransferMovEntity docTransferMovEntity = new TplDocTransferMovEntity();
    docTransferMovEntity.getData().setIpDocCode(
                                                 tplIpDocPrvtMovEntity_.getData().getIpDocCode() );
    docTransferMovEntity.getData().setCustNbr(
                                               tplIpDocPrvtMovEntity_.getData().getCustNbr() );

    docTransferMovDAO.delete( docTransferMovEntity );

    while ( docTransferEntities.hasNext() )
    {
    	tplDocTransferMovEntity = ( BaseTplDocTransferEntity ) docTransferEntities.next();


    	if ( docTransferMovDAO.exists( ( TplDocTransferMovEntity ) tplDocTransferMovEntity ) )
    	{
    		docTransferMovDAO.update( ( TplDocTransferMovEntity ) tplDocTransferMovEntity );
    	}
    	else
    	{
    		docTransferMovDAO.insert( ( TplDocTransferMovEntity ) tplDocTransferMovEntity );
    	}

    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocPrvtMovDAO#delete(com.citibank.ods.entity.pl.TplIpDocPrvtMovEntity)
   */
  public void delete( TplIpDocPrvtMovEntity tplIpDocPrvtMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "DELETE FROM " + C_TPL_IP_DOC_PRVT_MOV );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + " = ? AND " );
      query.append( C_IP_DOC_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( ( ( TplIpDocPrvtMovEntityVO ) tplIpDocPrvtMovEntity_.getData() ).getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           ( ( TplIpDocPrvtMovEntityVO ) tplIpDocPrvtMovEntity_.getData() ).getCustNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT );
      }

      if ( tplIpDocPrvtMovEntity_.getData().getIpDocCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplIpDocPrvtMovEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT );
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
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocPrvtMovDAO#exists(com.citibank.ods.entity.pl.TplIpDocPrvtMovEntity)
   */
  public boolean exists( TplIpDocPrvtMovEntity tplIpDocPrvtMovEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tplIpDocPrvtMovEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.persistence.pl.dao.BaseTplIpDocPrvtDAO#find(com.citibank.ods.entity.pl.BaseTplIpDocPrvtEntity)
   */
  public BaseTplIpDocPrvtEntity find(
                                     BaseTplIpDocPrvtEntity baseTplIpDocPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    ArrayList tplIpDocPrvtMovEntities;
    BaseTplIpDocPrvtEntity tplIpDocPrvtEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_IP_DOC_CODE + ", " );
      query.append( C_IP_DOC_TEXT + ", " );
      query.append( C_IP_INVST_CUR_ACCT_IND + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_OPERN_CODE );
      query.append( " FROM " + C_TPL_IP_DOC_PRVT_MOV );
      query.append( " WHERE " );
      //query.append( C_CUST_NBR + " = ? AND " );
      query.append( C_IP_DOC_CODE + " = ? " );

      int count = 1;
      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      /*if ( baseTplIpDocPrvtEntity_.getData().getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           baseTplIpDocPrvtEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT );
      }*/

      if ( baseTplIpDocPrvtEntity_.getData().getIpDocCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           baseTplIpDocPrvtEntity_.getData().getIpDocCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      tplIpDocPrvtMovEntities = instantiateFromResultSet( resultSet );

      if ( tplIpDocPrvtMovEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplIpDocPrvtMovEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplIpDocPrvtEntity = ( BaseTplIpDocPrvtEntity ) tplIpDocPrvtMovEntities.get( 0 );
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

    return tplIpDocPrvtEntity;
  }

  /*
   * Retorna uma coleção de entities a partir do rs
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    TplIpDocPrvtMovEntity tplIpDocPrvtMovEntity;
    TplIpDocPrvtMovEntityVO tplIpDocPrvtMovEntityVO;
    ArrayList<TplIpDocPrvtMovEntity> tplIpDocPrvtMovEntities = new ArrayList<TplIpDocPrvtMovEntity>();

    try
    {
      while ( resultSet_.next() )
      {
        tplIpDocPrvtMovEntity = new TplIpDocPrvtMovEntity();

        tplIpDocPrvtMovEntity.getData().setIpDocCode(
                                                      new BigInteger(
                                                                      resultSet_.getString( C_IP_DOC_CODE ) ) );
        tplIpDocPrvtMovEntity.getData().setIpDocText(
                                                      resultSet_.getString( C_IP_DOC_TEXT ) );
        tplIpDocPrvtMovEntity.getData().setLastUpdDate(
                                                        resultSet_.getTimestamp( C_LAST_UPD_DATE ) );
        tplIpDocPrvtMovEntity.getData().setLastUpdUserId(
                                                          resultSet_.getString( C_LAST_UPD_USER_ID ) );
        tplIpDocPrvtMovEntity.getData().setCustNbr(
                                                    new BigInteger(
                                                                    resultSet_.getString( C_CUST_NBR ) ) );

        tplIpDocPrvtMovEntity.getData().setIpInvstCurAcctInd(
                                                              resultSet_.getString( C_IP_INVST_CUR_ACCT_IND ) );

        // Casting para a atribuicao das colunas especificas
        tplIpDocPrvtMovEntityVO = ( TplIpDocPrvtMovEntityVO ) tplIpDocPrvtMovEntity.getData();
        tplIpDocPrvtMovEntityVO.setOpernCode( resultSet_.getString( C_OPERN_CODE ) );

        tplIpDocPrvtMovEntities.add( tplIpDocPrvtMovEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return tplIpDocPrvtMovEntities;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocPrvtMovDAO#list(java.math.BigInteger,
   *      java.math.BigInteger, java.lang.String, java.lang.String,
   *      java.math.BigInteger)
   */
  public DataSet list( BigInteger custNbrSrc_, BigInteger ipDocCodeSrc_,
                      String ipDocTextSrc_, String ipInvstCurAcctInd_,
                      String lastUpdUserId_, String custFullName_)
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet resultSetDataSet = null;
    StringBuffer query = new StringBuffer();
    String iPDocPrvtMov = "iPDocPrvtMov";
    String customerPrvt = "customerPrvt";

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " SELECT " );

      query.append( iPDocPrvtMov + "." + C_IP_DOC_CODE + ", " );
      query.append( iPDocPrvtMov + "." + C_IP_DOC_TEXT + ", " );
      query.append( iPDocPrvtMov + "." + C_CUST_NBR + ", " );
      query.append( customerPrvt + "." + C_CUST_FULL_NAME_TEXT + ", " );
      query.append( iPDocPrvtMov + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( iPDocPrvtMov + "." + C_LAST_UPD_DATE + ", " );
      query.append( iPDocPrvtMov + "." + C_OPERN_CODE );
      query.append( " FROM " );
      query.append( C_TPL_IP_DOC_PRVT_MOV + " " + iPDocPrvtMov + ", " );
      query.append( OracleTplCustomerPrvtDAO.C_TPL_CUSTOMER_PRVT + " "
                    + customerPrvt + " " );

      String criteria = "";

      if ( custNbrSrc_ != null && custNbrSrc_.longValue() != 0 )
      {
        criteria = criteria + "AND iPDocPrvtMov." + C_CUST_NBR + " = ? ";
      }

      if ( ipDocCodeSrc_ != null && ipDocCodeSrc_.longValue() != 0 )
      {
        criteria = criteria + "AND iPDocPrvtMov." + C_IP_DOC_CODE + " = ? ";
      }

      if ( ipDocTextSrc_ != null && !( ipDocTextSrc_.equals( "" ) ) )
      {
        criteria = criteria + "AND UPPER (iPDocPrvtMov." + C_IP_DOC_TEXT
                   + ") LIKE ( ? ) ";
      }

      if ( ipInvstCurAcctInd_ != null && !( ipInvstCurAcctInd_.equals( "" ) ) )
      {
        criteria = criteria + "AND UPPER (iPDocPrvtMov."
                   + C_IP_INVST_CUR_ACCT_IND + ") =  ? ";
      }

      if ( lastUpdUserId_ != null && !( lastUpdUserId_.equals( "" ) ) )
      {
        criteria = criteria + "AND UPPER (iPDocPrvtMov." + C_LAST_UPD_USER_ID
                   + ") LIKE ( ? ) ";
      }
      
      if ( custFullName_ != null && !( custFullName_.equals( "" ) ) )
      {
        criteria = criteria + "AND UPPER (customerPrvt." + C_CUST_FULL_NAME_TEXT
                   + ") LIKE ( ? ) ";
      }

      query.append( "WHERE customerPrvt.CUST_NBR = iPDocPrvtMov.CUST_NBR "
                    + criteria );

      query.append( " ORDER BY customerPrvt." + C_CUST_FULL_NAME_TEXT );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( custNbrSrc_ != null && custNbrSrc_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, custNbrSrc_.longValue() );
      }

      if ( ipDocCodeSrc_ != null && ipDocCodeSrc_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, ipDocCodeSrc_.longValue() );
      }

      if ( ipDocTextSrc_ != null && !( ipDocTextSrc_.equals( "" ) ) )
      {
        preparedStatement.setString( count++, "%" + ipDocTextSrc_.toUpperCase()
                                              + "%" );
      }

      if ( ipInvstCurAcctInd_ != null && !( ipInvstCurAcctInd_.equals( "" ) ) )
      {
        preparedStatement.setString( count++, ipInvstCurAcctInd_.toUpperCase() );
      }

      if ( lastUpdUserId_ != null && !( lastUpdUserId_.equals( "" ) ) )
      {
        preparedStatement.setString( count++, "%"
                                              + lastUpdUserId_.toUpperCase()
                                              + "%" );
      }

      if ( custFullName_ != null && !( custFullName_.equals( "" ) ) )
      {
        preparedStatement.setString( count++, "%" + custFullName_.toUpperCase()
                                              + "%" );
      }
      
      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

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

    String[] codeColumn = { C_OPERN_CODE };
    String[] nameColumn = { C_OPERN_TEXT };
    resultSetDataSet.outerJoin( ODSConstraintDecoder.decodeOpern(), codeColumn,
                                codeColumn, nameColumn );

    return resultSetDataSet;

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplIpDocPrvtMovDAO#list()
   */
  public DataSet list()
  {
    return null;
  }

/* (non-Javadoc)
 * @see com.citibank.ods.persistence.pl.dao.TplIpDocPrvtMovDAO#getNextIpCode(java.math.BigInteger)
 */
public Integer getNextIpCode(BigInteger custNbrPk) {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	ResultSet resultSet = null;
	StringBuffer query = new StringBuffer();
	Integer nextVal = null;

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "select max(PRMNT_INSTR_CODE) ULTIMO "+
					"from PL.TPL_PRMNT_INSTR_PRVT_MOV "+
					"where "+
					"cust_nbr = ?");

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      
	  preparedStatement.setLong(1,custNbrPk.longValue());

	  resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

	  if ( resultSet.next() )
	  {
		nextVal = new Integer( resultSet.getInt( "ULTIMO" ) );
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