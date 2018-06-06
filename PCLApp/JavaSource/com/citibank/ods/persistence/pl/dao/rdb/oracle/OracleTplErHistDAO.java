/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.sql.Timestamp;
import java.util.Date;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplErEntity;
import com.citibank.ods.entity.pl.TplErHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplErHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplErHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class OracleTplErHistDAO extends BaseOracleTplErDAO implements
  TplErHistDAO
{
	public String C_TPL_ER_HIST = C_PL_SCHEMA + "TPL_ER_HIST";
	
	public String C_ER_REF_DATE = "ER_REF_DATE";	
	
	

	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.TplErHistDAO#insert(com.citibank.ods.entity.pl.TplErHistEntity)
	 */
	public TplErHistEntity insert(TplErHistEntity erHistEntity_) 
	{
	  ManagedRdbConnection connection = null;
	  CitiStatement preparedStatement = null;
	  StringBuffer query = new StringBuffer();

	  try
	  {
		connection = OracleODSDAOFactory.getConnection();
		query.append( "INSERT INTO " + C_TPL_ER_HIST + " (" );
		query.append( C_ER_NBR + ", " );
		query.append( C_ER_REF_DATE + ", ");
		query.append( C_ER_RELTN_TRF_IND + ", " );
		query.append( C_RELTN_END_REAS_CODE + ", " );
		query.append( C_RELTN_END_REAS_TEXT + ", " );
		query.append( C_EQUITY_CLASS_CODE + ", " );
		query.append( C_LAST_UPD_DATE + ", " );
		query.append( C_LAST_UPD_USER_ID + ", " );
		query.append( C_REC_STAT_CODE +", ");
		query.append( C_LAST_AUTH_DATE + ", " );
		query.append( C_LAST_AUTH_USER_ID);
		query.append( ") VALUES ( " );
		query.append( "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );
		
		preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
		int count = 1;

		TplErHistEntityVO tplErHistEntityVO = (TplErHistEntityVO) erHistEntity_.getData();
      
		preparedStatement.setString( count++,
							  erHistEntity_.getData().getErNbr() );
		
		preparedStatement.setTimestamp( count++,
							 (new Timestamp(tplErHistEntityVO.getErRefDate().getTime()) ));
	  
      
		preparedStatement.setString( count++,
		                      erHistEntity_.getData().getErReltnTrfInd() );
	  
		if ( tplErHistEntityVO.getReltnEndReasCode() != null )
		{    	
    	preparedStatement.setLong( count++,
						  erHistEntity_.getData().getReltnEndReasCode().longValue() );
		}
		else
		{
		  preparedStatement.setString( count++, null );
		}		
		preparedStatement.setString( count++,
							  erHistEntity_.getData().getReltnEndReasText() );
	  
		if ( tplErHistEntityVO.getEquityClassCode() != null ) // correcao em 23/09/2015 - estava validando o campo errado
		{    	

		preparedStatement.setLong( count++,
						  erHistEntity_.getData().getEquityClassCode().longValue() );
		}
		else
		{
		  preparedStatement.setString( count++, null );
		}		

		preparedStatement.setTimestamp(count++,
								new Timestamp(erHistEntity_.getData().getLastUpdDate().getTime() ) );

		preparedStatement.setString( count++,
			                  erHistEntity_.getData().getLastUpdUserId() );
	  	  

		preparedStatement.setString( count++, 
		                    tplErHistEntityVO.getRecStatCode() );
	  

		preparedStatement.setTimestamp(count++,
								new Timestamp( tplErHistEntityVO.getLastAuthDate().getTime() ) );

		preparedStatement.setString( count++,
			                  tplErHistEntityVO.getLastAuthUserId() );


		preparedStatement.replaceParametersInQuery(query.toString());
		preparedStatement.executeUpdate();

		return erHistEntity_;

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
	 * @see com.citibank.ods.persistence.pl.dao.TplErHistDAO#list(java.lang.String, java.util.Date)
	 */
	public DataSet list(String erNbrSrc_, Date erRefDate_) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.TplErHistDAO#listHistory(com.citibank.ods.entity.pl.TplErHistEntity)
	 */
	public DataSet listHistory(TplErHistEntity erEntity_) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.BaseTplErDAO#find(com.citibank.ods.entity.pl.BaseTplErEntity)
	 */
	public BaseTplErEntity find(BaseTplErEntity erEntity_) {
		
		return null;
	}

}
