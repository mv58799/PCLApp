package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.persistence.pl.dao.TbgClassTypeFundAnbidDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class OracleTbgClassTypeFundAnbidDAO extends BaseOracleTbgClassTypeFundAnbidDAO implements TbgClassTypeFundAnbidDAO {
	private static final String C_TBG_CLASS_TYPE_FUND_ANBID = C_BG_SCHEMA + "TBG_CLASS_TYPE_FUND_ANBID";
	
	//Campos
	private static final String C_ANBID_FUND_CLASS_CODE = "anbid_fund_class_code";
	private static final String C_ANBID_FUND_CLASS_TEXT = "anbid_fund_class_text";
	
	public DataSet listClassTypeFundAnbid() {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetDataSet rsds = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append(" select " + C_ANBID_FUND_CLASS_CODE + ",");
			query.append("		  " + C_ANBID_FUND_CLASS_TEXT);
			query.append(" from " +  C_TBG_CLASS_TYPE_FUND_ANBID);

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));

			resultSet = preparedStatement.executeQuery();
			preparedStatement.replaceParametersInQuery(query.toString());

			rsds = new ResultSetDataSet(resultSet);
			resultSet.close();
			
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(),
					C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		
		return rsds;
	}

}
