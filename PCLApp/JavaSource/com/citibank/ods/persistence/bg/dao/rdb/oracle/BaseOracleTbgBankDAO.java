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
import com.citibank.ods.entity.bg.BaseTbgBankEntity;
import com.citibank.ods.entity.bg.TbgBankEntity;
import com.citibank.ods.entity.bg.valueobject.TbgBankEntityVO;
import com.citibank.ods.persistence.bg.dao.BaseTbgBankDAO;
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

public class BaseOracleTbgBankDAO extends BaseOracleDAO implements
	BaseTbgBankDAO
{

  public String C_BANK_NBR = "BANK_NBR";

  public String C_BANK_NAME = "BANK_NAME";

  public String C_BANK_SHORT_NAME_TEXT = "BANK_SHORT_NAME_TEXT";

  public String C_BANK_PARTPT_COMPN_IND = "BANK_PARTPT_COMPN_IND";

  public String C_BANK_PARTPT_CR_IND = "BANK_PARTPT_CR_IND";

  public String C_BANK_PARTPT_DEBT_IND = "BANK_PARTPT_DEBT_IND";

  public String C_BANK_DEBT_LIMIT_AMT = "BANK_DEBT_LIMIT_AMT";

  public String C_BANK_ISPB_CODE = "BANK_ISPB_CODE";

  public String C_BANK_INC_CODE = "BANK_INC_CODE";

  public String C_BANK_TRF_IND = "BANK_TRF_IND";

  public String C_TBG_BANK = "BG.TBG_BANK";

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.persistence.bg.dao.BaseTbgBankDAO#find(com.citibank.ods.entity.bg.BaseTbgBankEntity)
   */
  public BaseTbgBankEntity find( BaseTbgBankEntity baseTbgBankEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	ResultSet resultSet = null;
	StringBuffer query = new StringBuffer();

	ArrayList tbgBankEntities;
	BaseTbgBankEntity tbgBankEntity = null;

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "SELECT " );
	  query.append( C_BANK_NBR + ", " );
	  query.append( C_BANK_DEBT_LIMIT_AMT + ", " );
	  query.append( C_BANK_INC_CODE + ", " );
	  query.append( C_BANK_ISPB_CODE + ", " );
	  query.append( C_BANK_NAME + ", " );
	  query.append( C_BANK_PARTPT_COMPN_IND + ", " );
	  query.append( C_BANK_PARTPT_CR_IND + ", " );
	  query.append( C_BANK_PARTPT_DEBT_IND + ", " );
	  query.append( C_BANK_SHORT_NAME_TEXT + ", " );
	  query.append( C_BANK_TRF_IND );
	  query.append( " FROM " );
	  query.append( C_TBG_BANK );
	  query.append( " WHERE " );
	  query.append(C_BANK_NBR + " = ? " );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

	  preparedStatement.setLong( 1, Long.parseLong(baseTbgBankEntity_.getData().getBankCode()) );


	  resultSet = preparedStatement.executeQuery();
      
	  preparedStatement.replaceParametersInQuery(query.toString());

	  tbgBankEntities = instantiateFromResultSet( resultSet );

	  resultSet.close();

	  if ( tbgBankEntities.size() == 0 )
	  {
		throw new NoRowsReturnedException();
	  }
	  else if ( tbgBankEntities.size() > 1 )
	  {
		throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
	  }
	  else
	  {
		tbgBankEntity = ( BaseTbgBankEntity ) tbgBankEntities.get( 0 );
	  }
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
	return tbgBankEntity;
  }

  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
	ArrayList oracleTbgBankEntities = new ArrayList();
	TbgBankEntity tbgBankEntity;
	TbgBankEntityVO tbgBankEntityVO;

	try
	{
	  while ( resultSet_.next() )
	  {
		tbgBankEntity = new TbgBankEntity();
		tbgBankEntityVO = ( TbgBankEntityVO ) tbgBankEntity.getData();

		tbgBankEntityVO.setBankCode( resultSet_.getString( this.C_BANK_NBR) );
		tbgBankEntityVO.setBankDebtLimitAmt( resultSet_.getString( this.C_BANK_DEBT_LIMIT_AMT ) == null
																									   ? null
																									   : new BigInteger(
																														 resultSet_.getString( this.C_BANK_DEBT_LIMIT_AMT ) ) );
		tbgBankEntityVO.setBankIncCode( resultSet_.getString( this.C_BANK_INC_CODE ) == null
																							? null
																							: new BigInteger(
																											  resultSet_.getString( this.C_BANK_INC_CODE ) ) );
		tbgBankEntityVO.setBankIspbCode( resultSet_.getString( this.C_BANK_ISPB_CODE ) == null
																							  ? null
																							  : new BigInteger(
																												resultSet_.getString( this.C_BANK_ISPB_CODE ) ) );
		tbgBankEntityVO.setBankNameText( resultSet_.getString( this.C_BANK_NAME ) );
		tbgBankEntityVO.setBankPartptCompnInd( resultSet_.getString( this.C_BANK_PARTPT_COMPN_IND ) );
		tbgBankEntityVO.setBankPartptCrInd( resultSet_.getString( this.C_BANK_PARTPT_CR_IND ) );
		tbgBankEntityVO.setBankPartptDebtInd( resultSet_.getString( this.C_BANK_PARTPT_DEBT_IND ) );
		tbgBankEntityVO.setBankShortNameText( resultSet_.getString( this.C_BANK_SHORT_NAME_TEXT ) );
		tbgBankEntityVO.setBankTrfInd( resultSet_.getString( this.C_BANK_TRF_IND ) );

		oracleTbgBankEntities.add( tbgBankEntity );
	  }
	}
	catch ( SQLException e )
	{
	  throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
	}
	return oracleTbgBankEntities;
  }

}