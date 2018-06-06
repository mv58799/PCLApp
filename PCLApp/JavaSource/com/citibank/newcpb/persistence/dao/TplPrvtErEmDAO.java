package com.citibank.newcpb.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.vo.ErEmVO;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class TplPrvtErEmDAO extends BaseOracleDAO {

	public void deleteByEM_ER(String emNbr, String erNbr) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			query.append(" DELETE FROM " + C_PL_SCHEMA + "TPL_PRVT_ER_EM WHERE 1=1 ");
			
			if (!StringUtils.isBlank(emNbr)) {
				query.append(" AND EM_NBR = '" + emNbr + "' ");
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(erNbr)) {
				query.append(" AND ER_NBR = '" + erNbr + "' ");
			}			
			 
			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));

			preparedStatement.replaceParametersInQuery(query.toString());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
	}

	public ErEmVO insert(ErEmVO vo) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			sqlQuery.append("INSERT INTO " + C_PL_SCHEMA + "TPL_PRVT_ER_EM ( ");
			sqlQuery.append("    ER_NBR, ");
			sqlQuery.append("    EM_NBR, ");
			sqlQuery.append("    ROLE_CUST_CODE, ");
			sqlQuery.append("    LAST_AUTH_DATE, ");
			sqlQuery.append("    LAST_AUTH_USER_ID, ");
			sqlQuery.append("    LAST_UPD_DATE, ");
			sqlQuery.append("    LAST_UPD_USER_ID, ");
			sqlQuery.append("    REC_STAT_CODE ");
			sqlQuery.append("  ) VALUES(?, ?, ?, ?, ?, ?, ?, ?) ");

			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));

			int count = 1;

			if (vo.getErNbr() != null) {
				preparedStatement.setString(count++, vo.getErNbr());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (vo.getEmNbr() != null) {
				preparedStatement.setString(count++, vo.getEmNbr());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(vo.getRoleCustCode())) {
				preparedStatement.setString(count++, vo.getRoleCustCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
						
			if (vo.getLastAuthDate() != null) {
				preparedStatement.setTimestamp(count++, new java.sql.Timestamp(vo.getLastAuthDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getLastAuthUser())) {
				preparedStatement.setString(count++, vo.getLastAuthUser());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getLastUpdDate() != null) {
				preparedStatement.setTimestamp(count++, new java.sql.Timestamp(vo.getLastUpdDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getLastUpdUser())) {
				preparedStatement.setString(count++, vo.getLastUpdUser());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getRecStatCode())) {
				preparedStatement.setString(count++, vo.getRecStatCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			
			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return vo;
	}
	
	public ErEmVO getErEm(String emNbr){
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer sqlQuery = new StringBuffer();
		ErEmVO result = null;
		
		
		try {
			connection = OracleODSDAOFactory.getConnection();
			
			sqlQuery.append(" SELECT  ");
			sqlQuery.append("    ER_NBR, ");
			sqlQuery.append("    EM_NBR, ");
			sqlQuery.append("    ROLE_CUST_CODE, ");
			sqlQuery.append("    LAST_AUTH_DATE, ");
			sqlQuery.append("    LAST_AUTH_USER_ID, ");
			sqlQuery.append("    LAST_UPD_DATE, ");
			sqlQuery.append("    LAST_UPD_USER_ID, ");
			sqlQuery.append("    REC_STAT_CODE ");	
			sqlQuery.append(" FROM " + C_PL_SCHEMA + "TPL_PRVT_ER_EM ");

			String criteria = "";
						
			if (emNbr != null && !emNbr.equals("")) {
				if (criteria.length() > 0) {
					criteria = criteria + "AND UPPER(EM_NBR) = (?) ";
				} else {
					criteria = criteria + "UPPER(EM_NBR) = TRIM (?) ";
				}
			}

			if (criteria.length() > 0) {
				criteria = " WHERE " + criteria + "ORDER BY EM_NBR";
			} else {
				criteria = " ORDER BY EM_NBR";
			}

			sqlQuery.append(criteria);
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			 
			int count = 1;

			if (emNbr != null && !emNbr.equals("")) {
				preparedStatement.setString(count++, emNbr.toUpperCase());
			}

			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				
				result = new ErEmVO();
				while (rs.next()){
					
	
					result.setEmNbr(rs.getString("EM_NBR"));					
					result.setErNbr(rs.getString("ER_NBR"));
					result.setRoleCustCode(rs.getString("ROLE_CUST_CODE"));
					result.setRecStatCode(rs.getString("REC_STAT_CODE"));
					
					if(rs.getDate("LAST_AUTH_DATE") != null){						
						Date docDate = new Date(rs.getDate("LAST_AUTH_DATE").getTime());
						result.setLastAuthDate(docDate);
					}
					
					result.setLastAuthUser(rs.getString("LAST_AUTH_USER_ID") != null ? rs.getString("LAST_AUTH_USER_ID").toString() : null);
					
					if(rs.getDate("LAST_UPD_DATE") != null){						
						Date docDate = new Date(rs.getDate("LAST_UPD_DATE").getTime());
						result.setLastUpdDate(docDate);
					}

					result.setLastUpdUser(rs.getString("LAST_UPD_USER_ID") != null ? rs.getString("LAST_UPD_USER_ID").toString() : null);
					result.setTableOrigin(TableTypeEnum.MOVEMENT);
						
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
	
	public ErEmVO getEr(String erNbr){
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer sqlQuery = new StringBuffer();
		ErEmVO result = null;
		
		
		try {
			connection = OracleODSDAOFactory.getConnection();
			
			sqlQuery.append(" SELECT  ");
			sqlQuery.append("    ER_NBR, ");
			sqlQuery.append("    EM_NBR, ");
			sqlQuery.append("    ROLE_CUST_CODE, ");
			sqlQuery.append("    LAST_AUTH_DATE, ");
			sqlQuery.append("    LAST_AUTH_USER_ID, ");
			sqlQuery.append("    LAST_UPD_DATE, ");
			sqlQuery.append("    LAST_UPD_USER_ID, ");
			sqlQuery.append("    REC_STAT_CODE ");	
			sqlQuery.append(" FROM " + C_PL_SCHEMA + "TPL_PRVT_ER_EM ");

			String criteria = "";
						
			if (erNbr != null && !erNbr.equals("")) {
				if (criteria.length() > 0) {
					criteria = criteria + "AND UPPER(ER_NBR) = (?) ";
				} else {
					criteria = criteria + "UPPER(ER_NBR) = TRIM (?) ";
				}
			}

			if (criteria.length() > 0) {
				criteria = " WHERE " + criteria + "ORDER BY ER_NBR";
			} else {
				criteria = " ORDER BY ER_NBR";
			}

			sqlQuery.append(criteria);
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			 
			int count = 1;

			if (erNbr != null && !erNbr.equals("")) {
				preparedStatement.setString(count++, erNbr.toUpperCase());
			}

			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				
				result = new ErEmVO();
				while (rs.next()){
					
	
					result.setEmNbr(rs.getString("EM_NBR"));					
					result.setErNbr(rs.getString("ER_NBR"));
					result.setRoleCustCode(rs.getString("ROLE_CUST_CODE"));
					result.setRecStatCode(rs.getString("REC_STAT_CODE"));
					
					if(rs.getDate("LAST_AUTH_DATE") != null){						
						Date docDate = new Date(rs.getDate("LAST_AUTH_DATE").getTime());
						result.setLastAuthDate(docDate);
					}
					
					result.setLastAuthUser(rs.getString("LAST_AUTH_USER_ID") != null ? rs.getString("LAST_AUTH_USER_ID").toString() : null);
					
					if(rs.getDate("LAST_UPD_DATE") != null){						
						Date docDate = new Date(rs.getDate("LAST_UPD_DATE").getTime());
						result.setLastUpdDate(docDate);
					}

					result.setLastUpdUser(rs.getString("LAST_UPD_USER_ID") != null ? rs.getString("LAST_UPD_USER_ID").toString() : null);
					result.setTableOrigin(TableTypeEnum.MOVEMENT);
						
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
