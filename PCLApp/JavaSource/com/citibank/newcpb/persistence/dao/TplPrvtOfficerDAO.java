package com.citibank.newcpb.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.exception.UnexpectedException;
import com.citibank.newcpb.vo.OfficerBankerVO;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class TplPrvtOfficerDAO extends BaseOracleDAO {

	public ArrayList<OfficerBankerVO> listBySOEIDAndName(String employeeSOEID, String employeeName) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<OfficerBankerVO> resultList = null;

		try {
			connection = OracleODSDAOFactory.getConnection();						
			query.append(" SELECT DISTINCT"					
			+ "   OFFICER.OFFICER_SOEID, "
			+ "   VRH.EMPL_FULL_NAME, "
			+ "   OFFICER.SUPERVISOR_SOEID, "
			+ "   OFFICER.ASSOCIATE_SOEID, "
			+ "   OFFICER.LAST_UPD_DATE, "
			+ "   OFFICER.LAST_UPD_USER_ID, "
			+ "   OFFICER.LAST_AUTH_DATE, "
			+ "   OFFICER.LAST_AUTH_USER_ID, "
			+ "   OFFICER.REC_STAT_CODE               "
			+ "   FROM " + C_PL_SCHEMA + " TPL_PRVT_OFFICER OFFICER "
			+ "   JOIN " + C_RH_SCHEMA + " VRH_EMPLOYEE_REGISTRATION VRH "
			+ "   ON VRH.EMPL_SOEID_ID = OFFICER.OFFICER_SOEID "
			+ "   WHERE (OFFICER.REC_STAT_CODE = 'A' OR OFFICER.REC_STAT_CODE = 'U') ");
			
			
			
			
			if(employeeSOEID!=null){
				if (!StringUtils.isBlank(employeeSOEID)) {
					query.append(" AND UPPER(OFFICER.OFFICER_SOEID) LIKE TRIM(?) ");
				}
				
			}
			
			if(employeeName!=null){
				if (!StringUtils.isBlank(employeeName)) {
					query.append(" AND UPPER(VRH.EMPL_FULL_NAME) LIKE TRIM(?) ");
				}
				
			}
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;
			
			if(employeeSOEID!=null){
				
				if (!StringUtils.isBlank(employeeSOEID)) {
					preparedStatement.setString(count++, "%"+ employeeSOEID.toUpperCase() +"%");
				}
				
			}
			
			if(employeeName!=null){
				
				if (!StringUtils.isBlank(employeeName)) {
					preparedStatement.setString(count++, "%" + employeeName.toUpperCase() + "%");
				}
				
			}

			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<OfficerBankerVO>();
				while (rs.next()){
					
					OfficerBankerVO result = new OfficerBankerVO();
					
					
					
					result.setEmployeeSOEID(rs.getString("OFFICER_SOEID") != null ? rs.getString("OFFICER_SOEID").toString() : null);		
					result.setEmployeeName(rs.getString("EMPL_FULL_NAME") != null ? rs.getString("EMPL_FULL_NAME").toString() : null);	
					result.setSupervisorSOEID(rs.getString("SUPERVISOR_SOEID") != null ? rs.getString("SUPERVISOR_SOEID").toString() : null);	
					result.setAssociateSOEID(rs.getString("ASSOCIATE_SOEID") != null ? rs.getString("ASSOCIATE_SOEID").toString() : null);	
					
					result.setLastAuthDate(rs.getDate("LAST_AUTH_DATE") != null ? new Date(rs.getDate("LAST_AUTH_DATE").getTime()) : null);	
					result.setLastAuthUser(rs.getString("LAST_AUTH_USER_ID") != null ? rs.getString("LAST_AUTH_USER_ID").toString() : null);					
					result.setLastUpdDate(rs.getDate("LAST_UPD_DATE") != null ? new Date(rs.getDate("LAST_UPD_DATE").getTime()) : null);
					result.setLastUpdUser(rs.getString("LAST_UPD_USER_ID") != null ? rs.getString("LAST_UPD_USER_ID").toString() : null);	
					result.setRecStatCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);	
					
					result.setTableOrigin(TableTypeEnum.EFFECTIVE);
					
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
		
	public void insert(OfficerBankerVO vo) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();
		
		
		try {
			connection = OracleODSDAOFactory.getConnection();
			
			sqlQuery.append("  INSERT INTO " + C_PL_SCHEMA + " TPL_PRVT_OFFICER ( " );
			sqlQuery.append("  OFFICER_SOEID            , ");
			sqlQuery.append("  SUPERVISOR_SOEID         , ");
			sqlQuery.append("  ASSOCIATE_SOEID          , ");
			sqlQuery.append("  LAST_UPD_DATE            , ");
			sqlQuery.append("  LAST_UPD_USER_ID         , ");
			sqlQuery.append("  LAST_AUTH_DATE           , ");
			sqlQuery.append("  LAST_AUTH_USER_ID        , ");
			sqlQuery.append("  REC_STAT_CODE              ");
			sqlQuery.append( ") VALUES ( " );
			sqlQuery.append( " ?, ?, ?, ?, ?, ?, ?, ? ) ");

			
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			
			int count = 1;
			
			if (!StringUtils.isBlank(vo.getEmployeeSOEID())) {
				preparedStatement.setString(count++, vo.getEmployeeSOEID());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(vo.getSupervisorSOEID())) {
				preparedStatement.setString(count++, vo.getSupervisorSOEID());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getAssociateSOEID())) {
				preparedStatement.setString(count++, vo.getAssociateSOEID());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			
			if (vo.getLastUpdDate()!=null) {
				
				preparedStatement.setTimestamp(count++, new java.sql.Timestamp(vo.getLastUpdDate().getTime())); 
				
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getLastUpdUser())) {
				preparedStatement.setString(count++, vo.getLastUpdUser());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getLastAuthDate()!=null) {
				preparedStatement.setTimestamp(count++, new java.sql.Timestamp(vo.getLastAuthDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getLastAuthUser())) {
				preparedStatement.setString(count++, vo.getLastAuthUser());
			} else {
				preparedStatement.setString(count++, null);
			}
				
			if (!StringUtils.isBlank(vo.getRecStatCode())) {
				preparedStatement.setString(count++, vo.getRecStatCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			preparedStatement.execute();
			

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		
	}

	public OfficerBankerVO getOfficer(String employeeSOEID) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		OfficerBankerVO result = null;

		try {
			connection = OracleODSDAOFactory.getConnection();			
			query.append(" SELECT TPL_PRVT_OFFICER.OFFICER_SOEID, "		
			+ "   TPL_PRVT_OFFICER.SUPERVISOR_SOEID, "	
			+ "   TPL_PRVT_OFFICER.ASSOCIATE_SOEID, "	
			+ "   TPL_PRVT_OFFICER.LAST_AUTH_DATE, "
			+ "   TPL_PRVT_OFFICER.LAST_AUTH_USER_ID, "
			+ "   TPL_PRVT_OFFICER.LAST_UPD_DATE, "
			+ "   TPL_PRVT_OFFICER.LAST_UPD_USER_ID, "
			+ "   TPL_PRVT_OFFICER.REC_STAT_CODE"
			+ " FROM " + C_PL_SCHEMA + "TPL_PRVT_OFFICER "
			+ " WHERE 1=1 ");
			

			if(employeeSOEID!=null){
				if (!StringUtils.isBlank(employeeSOEID)) {
					query.append(" AND UPPER(TPL_PRVT_OFFICER.OFFICER_SOEID) = TRIM(?) ");
				}
			}
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;
			
			if(employeeSOEID!=null){
				
				if (!StringUtils.isBlank(employeeSOEID)) {
					preparedStatement.setString(count++, employeeSOEID.toUpperCase());
				}	
			}

			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			
			if(rs!=null){
				
				while (rs.next()){
					
					result = new OfficerBankerVO();
					result.setEmployeeSOEID(rs.getString("OFFICER_SOEID") != null ? rs.getString("OFFICER_SOEID").toString() : null);		
					result.setSupervisorSOEID(rs.getString("SUPERVISOR_SOEID") != null ? rs.getString("SUPERVISOR_SOEID").toString() : null);	
					result.setAssociateSOEID(rs.getString("ASSOCIATE_SOEID") != null ? rs.getString("ASSOCIATE_SOEID").toString() : null);	
					result.setLastAuthDate(rs.getDate("LAST_AUTH_DATE") != null ? new Date(rs.getDate("LAST_AUTH_DATE").getTime()) : null);	
					result.setLastAuthUser(rs.getString("LAST_AUTH_USER_ID") != null ? rs.getString("LAST_AUTH_USER_ID").toString() : null);					
					result.setLastUpdDate(rs.getDate("LAST_UPD_DATE") != null ? new Date(rs.getDate("LAST_UPD_DATE").getTime()) : null);
					result.setLastUpdUser(rs.getString("LAST_UPD_USER_ID") != null ? rs.getString("LAST_UPD_USER_ID").toString() : null);	
					result.setRecStatCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);	
					result.setTableOrigin(TableTypeEnum.EFFECTIVE);
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
		
	public OfficerBankerVO update(OfficerBankerVO vo) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			sqlQuery.append("UPDATE  " + C_PL_SCHEMA + "TPL_PRVT_OFFICER ");
			sqlQuery.append("  SET SUPERVISOR_SOEID         = ? , ");
			sqlQuery.append("  ASSOCIATE_SOEID              = ? , ");
			sqlQuery.append("  LAST_UPD_DATE                = ? , ");
			sqlQuery.append("  LAST_UPD_USER_ID             = ? , ");
			sqlQuery.append("  LAST_AUTH_DATE               = ? , ");
			sqlQuery.append("  LAST_AUTH_USER_ID            = ? , ");
			sqlQuery.append("  REC_STAT_CODE                = ?   ");
			sqlQuery.append("  WHERE OFFICER_SOEID          = ? ");



			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			
			int count = 1;

			if (!StringUtils.isBlank(vo.getSupervisorSOEID())) {
				preparedStatement.setString(count++, vo.getSupervisorSOEID());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getAssociateSOEID())) {
				preparedStatement.setString(count++, vo.getAssociateSOEID());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getLastUpdDate()!=null) {
				
				preparedStatement.setTimestamp(count++, new java.sql.Timestamp(vo.getLastUpdDate().getTime())); 
				
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getLastUpdUser())) {
				preparedStatement.setString(count++, vo.getLastUpdUser());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getLastAuthDate()!=null) {
				preparedStatement.setTimestamp(count++, new java.sql.Timestamp(vo.getLastAuthDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getLastAuthUser())) {
				preparedStatement.setString(count++, vo.getLastAuthUser());
			} else {
				preparedStatement.setString(count++, null);
			}
				
			if (!StringUtils.isBlank(vo.getRecStatCode())) {
				preparedStatement.setString(count++, vo.getRecStatCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			
			if (!StringUtils.isBlank(vo.getEmployeeSOEID())) {
				preparedStatement.setString(count++, vo.getEmployeeSOEID());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			preparedStatement.execute();
			

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
			
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return vo;
	}

	public void delete(String employeeSOEID) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			query.append(" DELETE FROM " + C_PL_SCHEMA + "TPL_PRVT_OFFICER"
					+ " WHERE OFFICER_SOEID = ?  ");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));

			if (!StringUtils.isBlank(employeeSOEID)) {
				preparedStatement.setString(1, employeeSOEID);
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			
			preparedStatement.replaceParametersInQuery(query.toString());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
	}
}
