package com.citibank.ods.persistence.bg.dao.rdb.oracle;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.bg.BaseTbgBranchEntity;
import com.citibank.ods.entity.bg.TbgBranchEntity;
import com.citibank.ods.entity.bg.valueobject.TbgBranchEntityVO;
import com.citibank.ods.persistence.bg.dao.BaseTbgBranchDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.bg.dao.rdb.oracle;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 18, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseOracleTbgBranchDAO extends BaseOracleDAO implements
	BaseTbgBranchDAO
{

  public String C_BANK_NBR= "BANK_NBR";

  public String C_BRANCH_NBR = "BRANCH_NBR";

  public String C_BRANCH_DIG = "BRANCH_DIG";

  public String C_BRANCH_NAME = "BRANCH_NAME";

  public String C_BRANCH_ADDR = "BRANCH_ADDR";

  public String C_BRANCH_CITY = "BRANCH_CITY";

  public String C_BRANCH_STATE = "BRANCH_STATE";

  public String C_BRANCH_ZIP = "BRANCH_ZIP";

  public String C_BRANCH_AREA_CODE = "BRANCH_AREA_CODE";

  public String C_BRANCH_PHONE_NBR = "BRANCH_PHONE_NBR";

  public String C_BRANCH_FAX_NBR = "BRANCH_FAX_NBR";

  public String C_BRANCH_TELEX_NBR = "BRANCH_TELEX_NBR";

  public String C_CLEARANCE_NBR = "CLEARANCE_NBR";

  public String C_ACTIVITY_CODE = "ACTIVITY_CODE";

	//É um sinonimo, nao modificar ou colocar schema.
  public String C_TBG_BRANCH = "TBG_BRANCH";

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.persistence.bg.dao.BaseTbgBranchDAO#find(com.citibank.ods.entity.bg.BaseTbgBranchEntity)
   */
  public BaseTbgBranchEntity find( BaseTbgBranchEntity BaseTbgBranchEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	ResultSet resultSet = null;
	StringBuffer query = new StringBuffer();

	ArrayList tbgBranchEntities;
	BaseTbgBranchEntity tbgBranchEntity = null;

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "SELECT " );
	  query.append( C_BANK_NBR + ", " );
	  query.append( C_BRANCH_NBR + ", " );
	  query.append( C_BRANCH_DIG + ", " );
	  query.append( C_BRANCH_NAME + ", " );
	  query.append( C_BRANCH_ADDR + ", " );
	  query.append( C_BRANCH_CITY + ", " );
	  query.append( C_BRANCH_STATE + ", " );
	  query.append( C_BRANCH_ZIP + ", " );
	  query.append( C_BRANCH_AREA_CODE + ", " );
	  query.append( C_BRANCH_PHONE_NBR + ", " );
	  query.append( C_BRANCH_FAX_NBR + ", " );
	  query.append( C_BRANCH_TELEX_NBR + ", " );
	  query.append( C_CLEARANCE_NBR + ", " );
	  query.append( C_ACTIVITY_CODE );
	  query.append( " FROM " );
	  query.append( C_TBG_BRANCH );
	  query.append( " WHERE " );
	  query.append(C_BANK_NBR + " = ? AND " );
	  query.append(C_BRANCH_NBR + " = ?" );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));


	  preparedStatement.setLong( 1, Long.parseLong(BaseTbgBranchEntity_.getData().getAgnBankCode()) );
	  preparedStatement.setLong( 2, Long.parseLong(BaseTbgBranchEntity_.getData().getAgnCode()) );

      

	  resultSet = preparedStatement.executeQuery();

	  preparedStatement.replaceParametersInQuery(query.toString());

	  tbgBranchEntities = instantiateFromResultSet( resultSet );

	  if ( tbgBranchEntities.size() == 0 )
	  {
		throw new NoRowsReturnedException();
	  }
	  else if ( tbgBranchEntities.size() > 1 )
	  {
		throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
	  }
	  else
	  {
		tbgBranchEntity = ( BaseTbgBranchEntity ) tbgBranchEntities.get( 0 );
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
	return tbgBranchEntity;
  }

  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
	TbgBranchEntity tbgBranchEntity;
	TbgBranchEntityVO tbgBranchEntityVO;
	ArrayList oracleTbgBranchEntities = new ArrayList();

	try
	{
	  while ( resultSet_.next() )
	  {
		tbgBranchEntity = new TbgBranchEntity();
		tbgBranchEntityVO = ( TbgBranchEntityVO ) tbgBranchEntity.getData();

		tbgBranchEntityVO.setActyCode( resultSet_.getString( this.C_ACTIVITY_CODE ) );
		tbgBranchEntityVO.setAgencyTwshpStateCode( resultSet_.getString( this.C_BRANCH_STATE) );
		tbgBranchEntityVO.setAgencyTwshpText( resultSet_.getString( this.C_BRANCH_CITY) );
		tbgBranchEntityVO.setAgnAddrText( resultSet_.getString( this.C_BRANCH_ADDR ) );
		tbgBranchEntityVO.setAgnAreaCode( resultSet_.getString( this.C_BRANCH_AREA_CODE ) );
		tbgBranchEntityVO.setAgnBankCode( resultSet_.getString( this.C_BANK_NBR) );
		tbgBranchEntityVO.setAgnClearHouseCode( resultSet_.getString( this.C_CLEARANCE_NBR) == null
																										   ? null
																										   : new BigInteger(
																															 resultSet_.getString( this.C_CLEARANCE_NBR ) ) );
		tbgBranchEntityVO.setAgnCode( resultSet_.getString( this.C_BRANCH_NBR ) );
		tbgBranchEntityVO.setAgnFaxNbr( resultSet_.getString( this.C_BRANCH_FAX_NBR ) );
		tbgBranchEntityVO.setAgnPhoneNbr( resultSet_.getString( this.C_BRANCH_PHONE_NBR ) );
		tbgBranchEntityVO.setAgnTelexNbr( resultSet_.getString( this.C_BRANCH_TELEX_NBR ) );
		tbgBranchEntityVO.setAgnText( resultSet_.getString( this.C_BRANCH_NAME) );
		tbgBranchEntityVO.setAgnVrfyCode( resultSet_.getString( this.C_BRANCH_DIG ) );
		tbgBranchEntityVO.setAgnZipCode( resultSet_.getString( this.C_BRANCH_ZIP ) );

		oracleTbgBranchEntities.add( tbgBranchEntity );
	  }
	}
	catch ( SQLException e )
	{
	  throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
	}
	return oracleTbgBranchEntities;
  }

}