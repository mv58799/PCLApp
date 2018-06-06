/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;


import java.sql.Timestamp;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplErEntity;
import com.citibank.ods.entity.pl.TplErMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplErMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplErMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class OracleTplErMovDAO extends BaseOracleTplErDAO implements
	TplErMovDAO
{
	/*
	 * Nome da tabela
	 */
	private static final String C_TPL_ER_MOV = C_PL_SCHEMA + "TPL_ER_MOV";
	
	/*
	 * Colunas da tabela
	 */
	public String C_OPERN_CODE = "OPERN_CODE";
	
	/**
	 * Realiza a inserção das informações
	 */
	public TplErMovEntity insert( TplErMovEntity tplErMovEntity_ )
																			  throws UnexpectedException
	{
	  ManagedRdbConnection connection = null;
	  CitiStatement preparedStatement = null;
	  StringBuffer query = new StringBuffer();

	  try
	  {
		connection = OracleODSDAOFactory.getConnection();
		query.append( "INSERT INTO " + C_TPL_ER_MOV + " (" );
		query.append( C_ER_NBR + ", " );
		query.append( C_ER_RELTN_TRF_IND + ", " );
		query.append( C_RELTN_END_REAS_CODE + ", " );
		query.append( C_RELTN_END_REAS_TEXT + ", " );
		query.append( C_EQUITY_CLASS_CODE + ", " );
		query.append( C_LAST_UPD_DATE + ", " );
		query.append( C_LAST_UPD_USER_ID + ", " );
		query.append( C_REC_STAT_CODE + ", " );
		query.append( C_LAST_AUTH_DATE + ", " );
		query.append( C_LAST_AUTH_USER_ID + ", " );
		query.append( C_OPERN_CODE );
		query.append( ") VALUES ( " );
		query.append( "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" );

		preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
		int count = 1;

		TplErMovEntityVO tplErMovEntityVO = ( TplErMovEntityVO ) tplErMovEntity_.getData();

		// C_ER_NBR
		if ( tplErMovEntityVO.getErNbr() != null
			 && tplErMovEntityVO.getErNbr() != "" )
		{
		  preparedStatement.setString( count++, tplErMovEntityVO.getErNbr() );
		}
		else
		{
		  preparedStatement.setString( count++, "" );
		}

		// C_ER_RELTN_TRF_IND
		if ( tplErMovEntityVO.getErReltnTrfInd() != null
			 && tplErMovEntityVO.getErReltnTrfInd() != "" )
		{
		  preparedStatement.setString( count++, tplErMovEntityVO.getErReltnTrfInd() );
		}
		else
		{
		  preparedStatement.setString( count++, "" );
		}

		//C_RELTN_END_REAS_CODE
		if ( tplErMovEntityVO.getReltnEndReasCode() != null )
			 
		{
		  preparedStatement.setLong( count++, tplErMovEntityVO.getReltnEndReasCode().longValue() );
		}
		else
		{
		  preparedStatement.setString( count++, null );
		}

		// C_RELTN_END_REAS_TEXT
		if ( tplErMovEntityVO.getReltnEndReasText() != null
			 && tplErMovEntityVO.getReltnEndReasText() != "" )
		{
		  preparedStatement.setString( count++, tplErMovEntityVO.getReltnEndReasText() );
		}
		else
		{
		  preparedStatement.setString( count++, "" );
		}

		// C_EQUITY_CLASS_CODE
		if ( tplErMovEntityVO.getEquityClassCode() != null )
		{
		  preparedStatement.setLong( count++, tplErMovEntityVO.getEquityClassCode().longValue() );
		}
		else
		{
		  preparedStatement.setString( count++, null );
		}

		// C_LAST_UPD_DATE
		if ( tplErMovEntityVO.getLastUpdDate() != null )
		{
		  preparedStatement.setTimestamp( count++,
								          new Timestamp(tplErMovEntityVO.getLastUpdDate().getTime() ) );
		}
		else
		{
		  preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
		}

		// C_LAST_UPD_USER_ID
		if ( tplErMovEntityVO.getLastUpdUserId() != null )
		{
		  preparedStatement.setString( count++, tplErMovEntityVO.getLastUpdUserId() );
		}
		else
		{
		  preparedStatement.setString( count++, "" );
		}

		// C_REC_STAT_CODE
		if ( tplErMovEntityVO.getRecStatCode() != null
			 && tplErMovEntityVO.getRecStatCode() != "" )
		{
		  preparedStatement.setString( count++, tplErMovEntityVO.getRecStatCode() );
		}
		else
		{
		  preparedStatement.setString( count++, "" );
		}

		// C_LAST_AUTH_DATE
		if ( tplErMovEntityVO.getLastUpdDate() != null )
		{
		  preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
		}

		// C_LAST_UPD_USER_ID
		if ( tplErMovEntityVO.getLastUpdUserId() != null )
		{
		  preparedStatement.setString( count++, "" );
		}

		//    OPernCode
		if ( tplErMovEntityVO.getOpernCode() != null )
		{
		  preparedStatement.setString( count++, tplErMovEntityVO.getOpernCode() );
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
		throw new UnexpectedException (C_ERROR_EXECUTING_STATEMENT, e);
	  }
	  finally
	  {
		closeStatement( preparedStatement );
		closeConnection( connection );
	  }

	  return tplErMovEntity_;
	}	

	/**
	 * Remove os relacionamentos
	 */
 	public void delete(TplErMovEntity tplErMovEntity_) 
	{
	  ManagedRdbConnection connection = null;
	  CitiStatement preparedStatement = null;
	  StringBuffer sqlQuery = new StringBuffer();

	  try
	  {
		connection = OracleODSDAOFactory.getConnection();
		sqlQuery.append( "DELETE " );
		sqlQuery.append( C_TPL_ER_MOV );
		sqlQuery.append( " WHERE " );
		sqlQuery.append( C_ER_NBR + " = ?" );

		preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

		preparedStatement.setString( 1, tplErMovEntity_.getData().getErNbr());

		preparedStatement.replaceParametersInQuery(sqlQuery.toString());
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
	}
	
	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.TplErMovDAO#list(java.lang.String)
	 */
	public DataSet list(String erNbr_) {
	
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.TplErMovDAO#listByErNbr(java.lang.String)
	 */
	public ArrayList listByErNbr(String erNbr_) {
	
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.TplErMovDAO#existsRelation(java.lang.String)
	 */
	public boolean existsRelation(String erNbr_) {
	
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.BaseTplErDAO#find(com.citibank.ods.entity.pl.BaseTplErEntity)
	 */
	public BaseTplErEntity find(BaseTplErEntity baseTplErEntity_)
	{
	
	  ManagedRdbConnection connection = null;
	  CitiStatement preparedStatement = null;
	  ResultSet resultSet = null;
	  StringBuffer query = new StringBuffer();

	  ArrayList tplErEntityEntities;
	  TplErMovEntity movEntity = null;

	  try
	  {
	    connection = OracleODSDAOFactory.getConnection();
	    query.append( "SELECT " );
	    query.append( C_ER_NBR + ", " );
	    query.append( C_ER_RELTN_TRF_IND + ", " );
	    query.append( C_RELTN_END_REAS_CODE + ", " );
	    query.append( C_RELTN_END_REAS_TEXT + ", " );
	    query.append( C_EQUITY_CLASS_CODE + ", " );	  
	    query.append( C_LAST_UPD_DATE + ", " );
	    query.append( C_LAST_UPD_USER_ID + ", " );	  
	    query.append( C_OPERN_CODE + ", " );	  
	    query.append( C_REC_STAT_CODE );
	    query.append( " FROM " );
	    query.append( C_TPL_ER_MOV);
	    query.append( " WHERE " );
	    query.append( C_ER_NBR + " = ?" );

	    preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

	    preparedStatement.setString(1,baseTplErEntity_.getData().getErNbr() );
	    preparedStatement.replaceParametersInQuery(query.toString());
	    resultSet = preparedStatement.executeQuery();  

	    tplErEntityEntities = instantiateFromResultSet( resultSet );

	    if ( tplErEntityEntities.size() == 0 )
	    {
		  throw new NoRowsReturnedException();
	    }
	    else if ( tplErEntityEntities.size() > 1 )
	    {
		  throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
	    }
	    else
	    {
		  movEntity = ( TplErMovEntity ) tplErEntityEntities.get( 0 );
	    }

	    return movEntity;
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

	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.TplErMovDAO#existsMovement(com.citibank.ods.entity.pl.TplErMovEntity)
	 */
	public boolean existsMovement(TplErMovEntity tplErMovEntity ) {	 
	  
	  boolean exists; 
	  try
	  {
		TplErMovEntity movEntity = ( TplErMovEntity ) find( tplErMovEntity );
	    exists = true;
	  }
	  catch ( NoRowsReturnedException exception )
 	  {
	    exists = false;
	  }
	  
	  return exists;

	}
	
	private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
	{
		TplErMovEntity tplErMovEntity;
		Timestamp timestamp;
		Date date;
		ArrayList tplErMovEntities = new ArrayList();

		try
		{
		  while ( resultSet_.next() )
		  {
			tplErMovEntity = new TplErMovEntity();

			tplErMovEntity.getData().setErNbr(resultSet_.getString( C_ER_NBR )  );
			
			if(resultSet_.getString( C_ER_RELTN_TRF_IND )!= null){
				tplErMovEntity.getData().setErReltnTrfInd(resultSet_.getString( C_ER_RELTN_TRF_IND )  );
			}
			else{
				tplErMovEntity.getData().setErReltnTrfInd(null);
			}
			
			if(resultSet_.getString( C_RELTN_END_REAS_CODE )!= null){
				tplErMovEntity.getData().setReltnEndReasCode(new BigInteger(resultSet_.getString( C_RELTN_END_REAS_CODE ))  );
			}
			else{
				tplErMovEntity.getData().setReltnEndReasCode(null);
			}
			
			if(resultSet_.getString( C_RELTN_END_REAS_TEXT )!= null){
				tplErMovEntity.getData().setReltnEndReasText(resultSet_.getString( C_RELTN_END_REAS_TEXT )  );
			}
			else{
				tplErMovEntity.getData().setReltnEndReasText(null);
			}
			
			if(resultSet_.getString( C_EQUITY_CLASS_CODE )!= null){
				tplErMovEntity.getData().setEquityClassCode(new BigInteger(resultSet_.getString( C_EQUITY_CLASS_CODE ))  );
			}
			else{
				tplErMovEntity.getData().setEquityClassCode(null);
			}
			
			timestamp = resultSet_.getTimestamp( C_LAST_UPD_DATE );
			date = new Date( timestamp.getTime() );			
			tplErMovEntity.getData().setLastUpdDate(date);
			
			tplErMovEntity.getData().setLastUpdUserId(resultSet_.getString( C_LAST_UPD_USER_ID ));
			tplErMovEntity.getData().setRecStatCode(resultSet_.getString(C_REC_STAT_CODE));
			
			// Casting para a atribuicao das colunas especificas
		    TplErMovEntityVO tplErMovEntityVO = ( TplErMovEntityVO ) tplErMovEntity.getData();
			tplErMovEntityVO.setOpernCode(resultSet_.getString( C_OPERN_CODE ));
			
			tplErMovEntities.add(tplErMovEntity);
		  }
		}
		catch ( SQLException e )
		{
		  throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
		}

		return tplErMovEntities;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.TplErMovDAO#update(com.citibank.ods.entity.pl.TplErMovEntity)
	 */
	public TplErMovEntity update(TplErMovEntity tplErMovEntity_) {
		  ManagedRdbConnection connection = null;
		  CitiStatement preparedStatement = null;
		  StringBuffer query = new StringBuffer();

		  try
		  {
			connection = OracleODSDAOFactory.getConnection();
			query.append( "UPDATE " + C_TPL_ER_MOV + " SET " );
			query.append( C_ER_RELTN_TRF_IND).append(" = ? , " );
			query.append( C_RELTN_END_REAS_CODE).append(" = ? , " );
			query.append( C_RELTN_END_REAS_TEXT).append(" = ? , " );
			query.append( C_EQUITY_CLASS_CODE).append(" = ? , " );
			query.append( C_LAST_UPD_DATE).append(" = ? , " );
			query.append( C_LAST_UPD_USER_ID).append(" = ? , " );
			query.append( C_REC_STAT_CODE ).append(" = ? , " );
			query.append( C_LAST_AUTH_DATE).append(" = ? , " );
			query.append( C_LAST_AUTH_USER_ID ).append(" = ? , " );
			query.append( C_OPERN_CODE ).append(" = ? " );
			query.append( " WHERE " );
			query.append( C_ER_NBR ).append(" = ? " );			

			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			int count = 1;

			TplErMovEntityVO tplErMovEntityVO = ( TplErMovEntityVO ) tplErMovEntity_.getData();

			// C_ER_RELTN_TRF_IND
			if ( tplErMovEntityVO.getErReltnTrfInd() != null
				 && tplErMovEntityVO.getErReltnTrfInd() != "" )
			{
			  preparedStatement.setString( count++, tplErMovEntityVO.getErReltnTrfInd() );
			}
			else
			{
			  preparedStatement.setString( count++, "" );
			}

			//C_RELTN_END_REAS_CODE
			if ( tplErMovEntityVO.getReltnEndReasCode() != null )
				 
			{
			  preparedStatement.setLong( count++, tplErMovEntityVO.getReltnEndReasCode().longValue() );
			}
			else
			{
			  preparedStatement.setString( count++, null );
			}

			// C_RELTN_END_REAS_TEXT
			if ( tplErMovEntityVO.getReltnEndReasText() != null
				 && tplErMovEntityVO.getReltnEndReasText() != "" )
			{
			  preparedStatement.setString( count++, tplErMovEntityVO.getReltnEndReasText() );
			}
			else
			{
			  preparedStatement.setString( count++, "" );
			}

			// C_EQUITY_CLASS_CODE
			if ( tplErMovEntityVO.getEquityClassCode() != null )
			{
			  preparedStatement.setLong( count++, tplErMovEntityVO.getEquityClassCode().longValue() );
			}
			else
			{
			  preparedStatement.setString( count++, null );
			}

			// C_LAST_UPD_DATE
			if ( tplErMovEntityVO.getLastUpdDate() != null )
			{
			  preparedStatement.setTimestamp( count++,
									          new Timestamp(tplErMovEntityVO.getLastUpdDate().getTime() ) );
			}
			else
			{
			  preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
			}

			// C_LAST_UPD_USER_ID
			if ( tplErMovEntityVO.getLastUpdUserId() != null )
			{
			  preparedStatement.setString( count++, tplErMovEntityVO.getLastUpdUserId() );
			}
			else
			{
			  preparedStatement.setString( count++, "" );
			}

			// C_REC_STAT_CODE
			if ( tplErMovEntityVO.getRecStatCode() != null
				 && tplErMovEntityVO.getRecStatCode() != "" )
			{
			  preparedStatement.setString( count++, tplErMovEntityVO.getRecStatCode() );
			}
			else
			{
			  preparedStatement.setString( count++, "" );
			}

			// C_LAST_AUTH_DATE
			if ( tplErMovEntityVO.getLastUpdDate() != null )
			{
			  preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
			}

			// C_LAST_UPD_USER_ID
			if ( tplErMovEntityVO.getLastUpdUserId() != null )
			{
			  preparedStatement.setString( count++, "" );
			}

			//    OPernCode
			if ( tplErMovEntityVO.getOpernCode() != null )
			{
			  preparedStatement.setString( count++, tplErMovEntityVO.getOpernCode() );
			}
			else
			{
			  preparedStatement.setString( count++, "" );
			}
			// C_ER_NBR
			if ( tplErMovEntityVO.getErNbr() != null
				 && tplErMovEntityVO.getErNbr() != "" )
			{
			  preparedStatement.setString( count++, tplErMovEntityVO.getErNbr() );
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
			throw new UnexpectedException (C_ERROR_EXECUTING_STATEMENT, e);
		  }
		  finally
		  {
			closeStatement( preparedStatement );
			closeConnection( connection );
		  }

		  return tplErMovEntity_;		
	}


}