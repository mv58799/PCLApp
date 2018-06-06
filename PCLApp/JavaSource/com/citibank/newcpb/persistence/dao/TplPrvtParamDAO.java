package com.citibank.newcpb.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.exception.UnexpectedException;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class TplPrvtParamDAO extends BaseOracleDAO {

	public String getParamValue(String paramKey) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		String paramValue="";

		try {
			connection = OracleODSDAOFactory.getConnection();
			
			query.append("SELECT PARAM_VALUE "
			+ "FROM " + C_PL_SCHEMA + "TPL_PRVT_PARAM WHERE PARAM_CODE = ? ");
			
			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));	

			if (!StringUtils.isBlank(paramKey)) {
				preparedStatement.setString(1, paramKey);
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			
			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				while (rs.next()){
					paramValue =  rs.getString("PARAM_VALUE") != null ? rs.getString("PARAM_VALUE").toString() : null;
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return paramValue;
	}
}
