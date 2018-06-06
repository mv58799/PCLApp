/*
 * Created on 14/11/2008
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplIpDocTransFinancEntity;
import com.citibank.ods.entity.pl.TplIpDocTransFinancEntity;
import com.citibank.ods.entity.pl.valueobject.TplIpDocTransFinancEntityVO;
import com.citibank.ods.persistence.pl.dao.TplIpDocTransFinancDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author lfabiano
 * @since 14/11/2008
 */
public class OracleTplIpDocTransFinancDAO extends BaseOracleTplIpDocTransFinancDAO	implements TplIpDocTransFinancDAO {

	public String C_TPL_PRMNT_INSTR_DATA_TRF_LOG =
		C_PL_SCHEMA + "TPL_PRMNT_INSTR_DATA_TRF_LOG";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.citibank.ods.persistence.pl.dao.TplIpDocTransFinancDAO#insert(com.citibank.ods.entity.pl.TplIpDocTransFinancEntity)
	 */

	public TplIpDocTransFinancEntity insert(TplIpDocTransFinancEntity tplIpDocTransFinancEntity_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("INSERT INTO " + C_TPL_PRMNT_INSTR_DATA_TRF_LOG + " (");
			query.append(C_CUST_NBR + ", ");
			query.append(C_PRMNT_INSTR_CODE + ", ");
			query.append(C_PRMNT_INSTR_TRF_DATA_CODE + ", ");
			query.append(C_PRMNT_INSTR_TRF_SEQ_NBR + ", ");
			query.append(C_CHNNL_ATTD_TEXT + ", ");
			query.append(C_TRF_ACCT_TYPE + ", ");
			query.append(C_TRF_AMT + ", ");
			query.append(C_TRF_DATE);
			query.append(") VALUES ( ");
			query.append("?, ?, ?, ?,? , ?, ?, ? )");

			preparedStatement =	new CitiStatement(connection.prepareStatement(query.toString()));
			int count = 1;
			
			preparedStatement.setLong(count++,tplIpDocTransFinancEntity_.getData().getCustNbr().longValue());
			preparedStatement.setLong(count++,tplIpDocTransFinancEntity_.getData().getPrmntInstrCode().longValue());
			preparedStatement.setLong(count++,tplIpDocTransFinancEntity_.getData().getPrmntInstrTrfDataCode().longValue());
			preparedStatement.setLong(count++,tplIpDocTransFinancEntity_.getData().getPrmntInstrTrfSeqNbr().longValue());
			preparedStatement.setString(count++,tplIpDocTransFinancEntity_.getData().getChnnlAttdText());
			preparedStatement.setLong(count++,tplIpDocTransFinancEntity_.getData().getTrfAcctType().longValue());
			preparedStatement.setBigDecimal(count++,tplIpDocTransFinancEntity_.getData().getTrfAmtNbr());
			preparedStatement.setTimestamp(count++,new Timestamp( tplIpDocTransFinancEntity_.getData().getTrfDate().getTime()));
			
			preparedStatement.replaceParametersInQuery(query.toString());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}

		return tplIpDocTransFinancEntity_;
	}

	public void update(TplIpDocTransFinancEntity tplIpDocTransFinancEntity_) {

	}

	public void delete(TplIpDocTransFinancEntity tplIpDocTransFinancEntity_) {

	}

	public boolean exists(TplIpDocTransFinancEntity tplIpDocTransFinancEntity_) {

		return false;
	}

	public boolean existsActive(TplIpDocTransFinancEntity tplIpDocTransFinancEntity_) {

		return false;
	}

	public BaseTplIpDocTransFinancEntity find(BaseTplIpDocTransFinancEntity tplIpDocTransFinancEntity_) {

		return null;
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.BaseTplIpDocTransFinancDAO#setBaseTplIpDocTransFinancEntity(com.citibank.ods.entity.pl.BaseTplIpDocTransFinancEntity)
	 */
	public void setBaseTplIpDocTransFinancEntity(BaseTplIpDocTransFinancEntity tplIpDocTransFinancEntity) {

	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.BaseTplIpDocTransFinancDAO#getBaseTplIpDocTransFinancEntity()
	 */
	public void getBaseTplIpDocTransFinancEntity() {

	}
	
	public Integer getSeqNextVal()
	{
	  ManagedRdbConnection connection = null;
	  CitiStatement preparedStatement = null;
	  ResultSet resultSet = null;
	  StringBuffer query = new StringBuffer();
	  Integer nextVal = null;

	  try
	  {
		connection = OracleODSDAOFactory.getConnection();
		
		query.append("select PL.SQ_PRMNT_INSTR_TRF_SEQ_NBR.Nextval id from dual");
		
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
	  finally {
		closeStatement(preparedStatement);
		closeConnection(connection);
	  }
	}


}
