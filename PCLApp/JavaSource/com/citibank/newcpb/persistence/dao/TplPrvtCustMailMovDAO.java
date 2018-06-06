package com.citibank.newcpb.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.vo.MailVO;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class TplPrvtCustMailMovDAO extends BaseOracleDAO {
	
	public void deleteByGFCID(String custGfcidNbr) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			query.append(" DELETE FROM " + C_PL_SCHEMA + "TPL_PRVT_CUST_MAIL_MOV WHERE CUST_GFCID_NBR = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));	

			if (!StringUtils.isBlank(custGfcidNbr)) {
				preparedStatement.setString(1, custGfcidNbr);
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

	public MailVO insert(MailVO vo) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			sqlQuery.append("INSERT INTO " + C_PL_SCHEMA + "TPL_PRVT_CUST_MAIL_MOV ( ");
			sqlQuery.append("    CUST_GFCID_NBR, ");
			sqlQuery.append("    EMAIL_SEQ_NBR, ");
			sqlQuery.append("    EMAIL_TEXT, ");
			sqlQuery.append("    LAST_AUTH_DATE, ");
			sqlQuery.append("    LAST_AUTH_USER, ");
			sqlQuery.append("    LAST_UPD_DATE, ");
			sqlQuery.append("    LAST_UPD_USER, ");
			sqlQuery.append("  REC_STAT_CODE     "); //11
			sqlQuery.append("  ) VALUES(?, ?, ?, ?, ?, ?, ?, ? ) ");

			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));

			int count = 1;

			if (vo.getCustGfcidNbr() != null) {
				preparedStatement.setLong(count++, vo.getCustGfcidNbr());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (vo.getPosition() != null) {
				preparedStatement.setLong(count++, vo.getPosition());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(vo.getMail())) {
				preparedStatement.setString(count++, vo.getMail());
			} else {
				preparedStatement.setString(count++, null);
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
			preparedStatement.execute();

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return vo;
	}

	public ArrayList<MailVO> list(String custGfcidNbr){
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer sqlQuery = new StringBuffer();
		ArrayList<MailVO> resultList = null;
		
		try {
			connection = OracleODSDAOFactory.getConnection();
						
			sqlQuery.append(" SELECT  ");
			sqlQuery.append("    CUST_GFCID_NBR, ");
			sqlQuery.append("    EMAIL_SEQ_NBR, ");
			sqlQuery.append("    EMAIL_TEXT, ");
			sqlQuery.append("    LAST_AUTH_DATE, ");
			sqlQuery.append("    LAST_AUTH_USER, ");
			sqlQuery.append("    LAST_UPD_DATE, ");
			sqlQuery.append("    LAST_UPD_USER ");	
	
			sqlQuery.append(" FROM " + C_PL_SCHEMA + "TPL_PRVT_CUST_MAIL_MOV ");

			String criteria = "";
						
			if (custGfcidNbr != null && !custGfcidNbr.equals("")) {
				if (criteria.length() > 0) {
					criteria = criteria + "AND CUST_GFCID_NBR = TRIM (?) ";
				} else {
					criteria = criteria + "CUST_GFCID_NBR = TRIM (?) ";
				}
			}

			if (criteria.length() > 0) {
				criteria = " WHERE " + criteria + "ORDER BY EMAIL_SEQ_NBR";
			} else {
				criteria = " ORDER BY EMAIL_SEQ_NBR";
			}

			sqlQuery.append(criteria);
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			 
			int count = 1;

			if (custGfcidNbr != null && !custGfcidNbr.equals("")) {
				preparedStatement.setString(count++, custGfcidNbr.toUpperCase());
			}

			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<MailVO>();
				while (rs.next()){
					
					MailVO result = new MailVO();
					result.setCustGfcidNbr(rs.getLong("CUST_GFCID_NBR"));
					result.setPosition(rs.getInt("EMAIL_SEQ_NBR"));
					result.setMail(rs.getString("EMAIL_TEXT"));
					
					if(rs.getDate("LAST_AUTH_DATE") != null){						
						Date docDate = new Date(rs.getDate("LAST_AUTH_DATE").getTime());
						result.setLastAuthDate(docDate);
					}
					
					result.setLastAuthUser(rs.getString("LAST_AUTH_USER") != null ? rs.getString("LAST_AUTH_USER").toString() : null);
					
					if(rs.getDate("LAST_UPD_DATE") != null){						
						Date docDate = new Date(rs.getDate("LAST_UPD_DATE").getTime());
						result.setLastUpdDate(docDate);
					}
					
					result.setLastUpdUser(rs.getString("LAST_UPD_USER") != null ? rs.getString("LAST_UPD_USER").toString() : null);
					result.setTableOrigin(TableTypeEnum.MOVEMENT);
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
