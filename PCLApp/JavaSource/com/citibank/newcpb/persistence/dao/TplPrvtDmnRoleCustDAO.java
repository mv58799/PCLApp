package com.citibank.newcpb.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.citibank.newcpb.bean.ResultTableBean;
import com.citibank.newcpb.exception.UnexpectedException;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class TplPrvtDmnRoleCustDAO extends BaseOracleDAO {

	public ArrayList<ResultTableBean> list() {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<ResultTableBean> resultList = null;

		try {
			connection = OracleODSDAOFactory.getConnection();
						
			query.append("SELECT ROLE_CUST_CODE, ROLE_CUST_TEXT "
			+ "FROM " + C_PL_SCHEMA + "TPL_PRVT_DMN_ROLE_CUST  WHERE REC_STAT_CODE <> 'I' ORDER BY ROLE_CUST_TEXT");
			
			
			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<ResultTableBean>();
				while (rs.next()){
					
					ResultTableBean result = new ResultTableBean();
					result.setResultDescription(rs.getString("ROLE_CUST_TEXT") != null ? rs.getString("ROLE_CUST_TEXT").toString() : null);								
					result.setResultCode(rs.getString("ROLE_CUST_CODE") != null ? rs.getString("ROLE_CUST_CODE").toString() : null);

					resultList.add(result);		
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return resultList;
	}
}
