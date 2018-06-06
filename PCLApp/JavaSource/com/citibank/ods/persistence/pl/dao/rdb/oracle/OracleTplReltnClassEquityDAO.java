/*
 * Created on 11/12/2008
 *
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.TplReltnClassEquityDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author lfabiano
 * @since 11/12/2008
 */
public class OracleTplReltnClassEquityDAO extends BaseOracleDAO implements TplReltnClassEquityDAO {

	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.TplReasonEndRelationDAO#loadDomain()
	 */
	public DataSet loadDomain(){

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetDataSet rsds = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("SELECT ");
			query.append("EQUITY_CLASS_CODE , ");
			query.append("EQUITY_CLASS_TEXT ");
			query.append("FROM ");
			query.append("PL.TPL_RELTN_CLASS_EQUITY ");
			query.append(" WHERE ");
			query.append("REC_STAT_CODE <> ?");

			preparedStatement =	new CitiStatement(connection.prepareStatement(query.toString()));
			int count = 1;

			preparedStatement.setString(count++,"I");

			preparedStatement.replaceParametersInQuery(query.toString());
			resultSet = preparedStatement.executeQuery();

			rsds = new ResultSetDataSet(resultSet);
			resultSet.close();
		} catch (SQLException e) {
			throw new UnexpectedException(
				e.getErrorCode(),
				C_ERROR_EXECUTING_STATEMENT,
				e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return rsds;

	}

}