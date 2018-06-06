package com.citibank.newcpb.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.exception.UnexpectedException;
import com.citibank.newcpb.util.FormatUtils;
import com.citibank.newcpb.vo.AuthorizationPersonAccountVO;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class TplPrvtAutPrsnAcctDAO extends BaseOracleDAO {

	public AuthorizationPersonAccountVO insert(AuthorizationPersonAccountVO vo) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();		

		try {
			connection = OracleODSDAOFactory.getConnection();
			
			sqlQuery.append("  INSERT INTO " + C_PL_SCHEMA + "TPL_PRVT_AUT_PRSN_ACCT ( " );
			sqlQuery.append(" EM_NBR, ");
			sqlQuery.append(" ACCT_NBR, ");
			sqlQuery.append(" LAST_AUTH_DATE, ");
			sqlQuery.append(" LAST_AUTH_USER_ID, ");
			sqlQuery.append(" LAST_UPD_DATE, ");
			sqlQuery.append(" LAST_UPD_USER_ID, ");	
			sqlQuery.append(" AUTH_IND, ");
			sqlQuery.append(" AUTH_EFF_DATE, ");
			sqlQuery.append(" AUTH_COMMENT, ");
			sqlQuery.append(" REC_STAT_CODE ");
			sqlQuery.append( ") VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" );

			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			
			int count = 1;

			if (!StringUtils.isBlank(vo.getEmNbr())) {
				preparedStatement.setString(count++, FormatUtils.unformatterDoc(vo.getEmNbr()));
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT);
			}
			
			if (!StringUtils.isBlank(vo.getAcctNbr())) {				
				preparedStatement.setString(count++, vo.getAcctNbr());
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
			
			if (vo.getAuthInd()!=null) {
				preparedStatement.setString(count++, vo.getAuthInd());
			} else {
				preparedStatement.setString(count++, "X");
			}
			
			if (!StringUtils.isBlank(vo.getEffectiveDate())) {				
				Date effectiveDate =  FormatUtils.formatToDate(vo.getEffectiveDate(),FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
				preparedStatement.setDate(count++, new java.sql.Date(effectiveDate.getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getAuthComment())) {				
				preparedStatement.setString(count++, vo.getAuthComment());
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
		} catch (ParseException a) {
			throw new UnexpectedException(a.getErrorOffset(), C_ERROR_EXECUTING_STATEMENT, a);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return vo;
	}
		
	public void delete(String emNbr) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			query.append(" DELETE FROM " + C_PL_SCHEMA + "TPL_PRVT_AUT_PRSN_ACCT WHERE EM_NBR = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));

			if (!StringUtils.isBlank(emNbr)) {
				preparedStatement.setString(1, emNbr);
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
	
	public ArrayList<AuthorizationPersonAccountVO> list(String filterNumberEM, String filterAcctNbr, boolean isLike) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<AuthorizationPersonAccountVO> resultList = null;

		try {
			connection = OracleODSDAOFactory.getConnection();			
			query.append(" SELECT TPL_PRVT_AUT_PRSN_ACCT.EM_NBR, ");
			query.append("   TPL_PRVT_AUT_PRSN_ACCT.ACCT_NBR, ");
			query.append("   TPL_PRVT_AUT_PRSN_ACCT.LAST_AUTH_DATE, ");
			query.append("   TPL_PRVT_AUT_PRSN_ACCT.LAST_AUTH_USER_ID, ");
			query.append("   TPL_PRVT_AUT_PRSN_ACCT.LAST_UPD_DATE, ");
			query.append("   TPL_PRVT_AUT_PRSN_ACCT.LAST_UPD_USER_ID, ");
			query.append("   TPL_PRVT_AUT_PRSN_ACCT.AUTH_IND, ");
			query.append("   TPL_PRVT_AUT_PRSN_ACCT.AUTH_EFF_DATE, ");	
			query.append("   TPL_PRVT_AUT_PRSN_ACCT.AUTH_COMMENT, ");				
			query.append("   TPL_PRVT_AUT_PRSN_ACCT.REC_STAT_CODE ");
			query.append(" FROM " + C_PL_SCHEMA + "TPL_PRVT_AUT_PRSN_ACCT ");
			query.append(" WHERE 1=1 ");
			
			if (filterNumberEM != null && !filterNumberEM.equals("")) {
				if(isLike){
					query.append(" AND UPPER(TPL_PRVT_AUT_PRSN_ACCT.EM_NBR) LIKE TRIM(?) ");
				}else{
					query.append(" AND UPPER(TPL_PRVT_AUT_PRSN_ACCT.EM_NBR) = TRIM(?) ");
				}				
			}
			
			if (filterAcctNbr != null && !filterAcctNbr.equals("")) {
				if(isLike){
					query.append(" AND UPPER(TPL_PRVT_AUT_PRSN_ACCT.ACCT_NBR) LIKE TRIM(?) ");
				}else{
					query.append(" AND UPPER(TPL_PRVT_AUT_PRSN_ACCT.ACCT_NBR) = TRIM(?) ");
				}
			}
						
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;

			if (filterNumberEM != null && !StringUtils.isBlank(filterNumberEM)) {
				if(isLike){
					preparedStatement.setString(count++, "%" + filterNumberEM.toUpperCase() + "%");
				}else{
					preparedStatement.setString(count++, filterNumberEM.toUpperCase());
				}
			}
			
			if (filterAcctNbr != null && !StringUtils.isBlank(filterAcctNbr)) {
				if(isLike){
					preparedStatement.setString(count++, "%" + filterAcctNbr.toUpperCase() + "%");
				}else{
					preparedStatement.setString(count++, filterAcctNbr.toUpperCase());
				}
			}

			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<AuthorizationPersonAccountVO>();
				while (rs.next()){
					AuthorizationPersonAccountVO result = new AuthorizationPersonAccountVO();
					result.setEmNbr(rs.getString("EM_NBR") != null ? rs.getString("EM_NBR").toString() : null);	
					result.setAcctNbr(rs.getString("ACCT_NBR") != null ? rs.getString("ACCT_NBR").toString() : null);	
					result.setLastAuthDate(rs.getTimestamp("LAST_AUTH_DATE") != null ? rs.getTimestamp("LAST_AUTH_DATE") : null);	
					result.setLastAuthUser(rs.getString("LAST_AUTH_USER_ID") != null ? rs.getString("LAST_AUTH_USER_ID").toString() : null);	
					result.setLastUpdDate(rs.getTimestamp("LAST_UPD_DATE") != null ? rs.getTimestamp("LAST_UPD_DATE") : null);	
					result.setLastUpdUser(rs.getString("LAST_UPD_USER_ID") != null ? rs.getString("LAST_UPD_USER_ID").toString() : null);						
					result.setAuthInd(rs.getString("AUTH_IND") != null ? rs.getString("AUTH_IND")  : null);
					result.setEffectiveDate(rs.getTimestamp("AUTH_EFF_DATE") != null ? FormatUtils.dateToStringFormated(rs.getTimestamp("AUTH_EFF_DATE"), FormatUtils.C_FORMAT_DATE_DD_MM_YYYY) : null);
					result.setAuthComment(rs.getString("AUTH_COMMENT") != null ? rs.getString("AUTH_COMMENT").toString() : null);	
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
}
