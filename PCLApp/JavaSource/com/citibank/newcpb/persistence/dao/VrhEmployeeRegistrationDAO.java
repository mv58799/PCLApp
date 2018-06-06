package com.citibank.newcpb.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.citibank.newcpb.exception.UnexpectedException;
import com.citibank.newcpb.vo.EmployeeRegistrationVO;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class VrhEmployeeRegistrationDAO extends BaseOracleDAO {

	public EmployeeRegistrationVO getEmployeeRegistration(String numberSOEID) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		EmployeeRegistrationVO result = null;
		
		try {
			connection = OracleODSDAOFactory.getConnection();
			
		
			query.append("SELECT DISTINCT EMPL_SOEID_ID, EMPL_FULL_NAME "
			+  "FROM  " + C_RH_SCHEMA + "VRH_EMPLOYEE_REGISTRATION  ");

			String criteria = "";

			if (numberSOEID != null && !numberSOEID.equals("")) {
					criteria = criteria + "UPPER(EMPL_SOEID_ID) = TRIM (?) ";
			}

			if (criteria.length() > 0) {
				criteria = " WHERE EMPL_ID = (SELECT MAX(EMPL_ID) FROM  RH.VRH_EMPLOYEE_REGISTRATION "+   
								" WHERE " + criteria + ") "; //excluir Soeids duplicados
			} else {
				criteria = " ORDER BY EMPL_SOEID_ID";
			}

			query.append(criteria);
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;

			
			if (numberSOEID != null && !numberSOEID.equals("")) {
				preparedStatement.setString(count++, numberSOEID.toUpperCase());
			}

			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				while (rs.next()){
					result = new EmployeeRegistrationVO();
					result.setEmployeeName(rs.getString("EMPL_FULL_NAME") != null ? rs.getString("EMPL_FULL_NAME").toString() : null);								
					result.setEmployeeSOEID(rs.getString("EMPL_SOEID_ID") != null ? rs.getString("EMPL_SOEID_ID").toString() : null);	
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return result;
	}
}
