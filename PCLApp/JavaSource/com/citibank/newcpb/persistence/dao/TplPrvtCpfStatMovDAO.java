package com.citibank.newcpb.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.exception.UnexpectedException;
import com.citibank.newcpb.util.FormatUtils;
import com.citibank.newcpb.vo.StatusCpfCnpjVO;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class TplPrvtCpfStatMovDAO extends BaseOracleDAO {

	public ArrayList<StatusCpfCnpjVO> list(String filterNumberEM, String filterCpfCnpj, String filterName, 
			String filterStatus, String filterMonthYear, String filterCpfCnpjList, boolean isLike) throws ParseException {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<StatusCpfCnpjVO> resultList = null;

		try {
			connection = OracleODSDAOFactory.getConnection();			
			query.append(" SELECT TPL_PRVT_CPF_STAT_MOV.CPF_CNPJ_NBR, ");
			query.append("   TPL_PRVT_CPF_STAT_MOV.CPF_STATUS, ");
			query.append("   TPL_PRVT_CPF_STAT_MOV.CPF_UPD_MTH_YR, ");
			query.append("   TPL_PRVT_CPF_STAT_MOV.LAST_AUTH_DATE, ");
			query.append("   TPL_PRVT_CPF_STAT_MOV.LAST_AUTH_USER_ID, ");
			query.append("   TPL_PRVT_CPF_STAT_MOV.LAST_UPD_DATE, ");
			query.append("   TPL_PRVT_CPF_STAT_MOV.LAST_UPD_USER_ID, ");
			query.append("   TPL_PRVT_CPF_STAT_MOV.REC_STAT_CODE ");
			query.append(" FROM " + C_PL_SCHEMA + "TPL_PRVT_CPF_STAT_MOV, " + C_PL_SCHEMA + "TPL_PRVT_CUSTOMER, " + C_PL_SCHEMA + "TPL_PRVT_AUTH_PERSN");
			query.append(" WHERE TPL_PRVT_CPF_STAT_MOV.CPF_CNPJ_NBR = TPL_PRVT_CUSTOMER.CUST_CPF_CNPJ_NBR (+) ");
			query.append(" AND TPL_PRVT_CPF_STAT_MOV.CPF_CNPJ_NBR = TPL_PRVT_AUTH_PERSN.CPF_CNPJ_NBR (+) ");
			
			if (filterNumberEM != null && !filterNumberEM.equals("")) {
				if(isLike){
					query.append(" AND (UPPER(TPL_PRVT_CUSTOMER.CUST_EM_NBR) LIKE TRIM(?) OR UPPER(TPL_PRVT_AUTH_PERSN.EM_NBR) LIKE TRIM(?))");
				}else{
					query.append(" AND (UPPER(TPL_PRVT_CUSTOMER.CUST_EM_NBR) = TRIM(?) OR UPPER(TPL_PRVT_AUTH_PERSN.EM_NBR) = TRIM(?))");
				}				
			}
			
			if (filterCpfCnpj != null && !filterCpfCnpj.equals("")) {
				if(isLike){
					query.append(" AND UPPER(TPL_PRVT_CPF_STAT.CPF_CNPJ_NBR) LIKE TRIM(?) ");
				}else{
					query.append(" AND UPPER(TPL_PRVT_CPF_STAT.CPF_CNPJ_NBR) = TRIM(?) ");
				}
			}
			
			if (filterName != null && !filterName.equals("")) {
				if(isLike){
					query.append(" AND (UPPER(TPL_PRVT_CUSTOMER.CUST_FULL_NAME) LIKE TRIM(?) OR UPPER(TPL_PRVT_AUTH_PERSN.AUTH_PERSN_NAME) LIKE TRIM(?))");
				}else{
					query.append(" AND (UPPER(TPL_PRVT_CUSTOMER.CUST_FULL_NAME) = TRIM(?) OR UPPER(TPL_PRVT_AUTH_PERSN.AUTH_PERSN_NAME) = TRIM(?))");
				}				
			}
			
			if (filterStatus != null && !filterStatus.equals("")) {
				if(isLike){
					query.append(" AND UPPER(TPL_PRVT_CPF_STAT_MOV.CPF_STATUS) LIKE TRIM(?) ");
				}else{
					query.append(" AND UPPER(TPL_PRVT_CPF_STAT_MOV.CPF_STATUS) = TRIM(?) ");
				}				
			}
			
			if (filterMonthYear != null && !filterMonthYear.equals("")) {
				if(isLike){
					query.append(" AND UPPER(TPL_PRVT_CPF_STAT_MOV.CPF_UPD_MTH_YR) LIKE TRIM(?) ");
				}else{
					query.append(" AND UPPER(TPL_PRVT_CPF_STAT_MOV.CPF_UPD_MTH_YR) = TRIM(?) ");
				}				
			}
			
			if (filterCpfCnpjList != null && !filterCpfCnpjList.equals("")) {
				query.append(" AND UPPER(TPL_PRVT_CPF_STAT_MOV.CPF_CNPJ_NBR) IN (?) ");	
			}
			
			query.append(" GROUP BY TPL_PRVT_CPF_STAT_MOV.CPF_CNPJ_NBR, ");
			query.append("   TPL_PRVT_CPF_STAT_MOV.CPF_STATUS, ");
			query.append("   TPL_PRVT_CPF_STAT_MOV.CPF_UPD_MTH_YR, ");
			query.append("   TPL_PRVT_CPF_STAT_MOV.LAST_AUTH_DATE, ");
			query.append("   TPL_PRVT_CPF_STAT_MOV.LAST_AUTH_USER_ID, ");
			query.append("   TPL_PRVT_CPF_STAT_MOV.LAST_UPD_DATE, ");
			query.append("   TPL_PRVT_CPF_STAT_MOV.LAST_UPD_USER_ID, ");
			query.append("   TPL_PRVT_CPF_STAT_MOV.REC_STAT_CODE ");
						
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;

			if (filterNumberEM != null && !StringUtils.isBlank(filterNumberEM)) {
				if(isLike){
					preparedStatement.setString(count++, "%" + filterNumberEM.toUpperCase() + "%");
				}else{
					preparedStatement.setString(count++, filterNumberEM.toUpperCase());
				}
			}
			
			if (filterCpfCnpj != null && !StringUtils.isBlank(filterCpfCnpj)) {
				if(isLike){
					preparedStatement.setString(count++, "%" + FormatUtils.unformatterDoc(filterCpfCnpj.toUpperCase()) + "%");
				}else{
					preparedStatement.setString(count++, FormatUtils.unformatterDoc(filterCpfCnpj.toUpperCase()));
				}
			}
			
			if (filterName != null && !StringUtils.isBlank(filterName)) {
				if(isLike){
					preparedStatement.setString(count++, "%" + filterName.toUpperCase() + "%");
				}else{
					preparedStatement.setString(count++, filterName.toUpperCase());
				}
			}
			
			if (filterStatus != null && !StringUtils.isBlank(filterStatus)) {
				if(isLike){
					preparedStatement.setString(count++, "%" + filterStatus.toUpperCase() + "%");
				}else{
					preparedStatement.setString(count++, filterStatus.toUpperCase());
				}
			}
			
			if (filterMonthYear != null && !StringUtils.isBlank(filterMonthYear)) {
				if(isLike){
					preparedStatement.setString(count++, "%" + filterMonthYear.toUpperCase().replaceAll("/", "") + "%");
				}else{
					preparedStatement.setString(count++, filterMonthYear.toUpperCase().replaceAll("/", ""));
				}
			}
			
			if (filterCpfCnpjList != null && !StringUtils.isBlank(filterCpfCnpjList)) {
				preparedStatement.setString(count++, FormatUtils.unformatterDoc(filterCpfCnpjList).toUpperCase());
			}

			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<StatusCpfCnpjVO>();
				while (rs.next()){
					
					StatusCpfCnpjVO result = new StatusCpfCnpjVO();					
					result.setCpfCnpjNbr(rs.getString("CPF_CNPJ_NBR") != null ? rs.getString("CPF_CNPJ_NBR").toString() : null);	
					result.setCpfStatus(rs.getString("CPF_STATUS") != null ? rs.getString("CPF_STATUS").toString() : null);	
					result.setCpfUpdMthYr(rs.getString("CPF_UPD_MTH_YR") != null ? rs.getString("CPF_UPD_MTH_YR").toString() : null);	
					result.setLastAuthDate(rs.getTimestamp("LAST_AUTH_DATE") != null ? rs.getTimestamp("LAST_AUTH_DATE") : null);	
					result.setLastAuthUserId(rs.getString("LAST_AUTH_USER_ID") != null ? rs.getString("LAST_AUTH_USER_ID").toString() : null);	
					result.setLastUpdDate(rs.getTimestamp("LAST_UPD_DATE") != null ? rs.getTimestamp("LAST_UPD_DATE") : null);	
					result.setLastUpdUserId(rs.getString("LAST_UPD_USER_ID") != null ? rs.getString("LAST_UPD_USER_ID").toString() : null);	
					result.setRecStatCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);	
					
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
	
	public StatusCpfCnpjVO insert(StatusCpfCnpjVO vo) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();		

		try {
			connection = OracleODSDAOFactory.getConnection();
			
			sqlQuery.append("  INSERT INTO " + C_PL_SCHEMA + "TPL_PRVT_CPF_STAT_MOV ( " );
			sqlQuery.append(" CPF_CNPJ_NBR, ");
			sqlQuery.append(" CPF_STATUS, ");
			sqlQuery.append(" CPF_UPD_MTH_YR, ");
			sqlQuery.append(" LAST_AUTH_DATE, ");
			sqlQuery.append(" LAST_AUTH_USER_ID, ");
			sqlQuery.append(" LAST_UPD_DATE, ");
			sqlQuery.append(" LAST_UPD_USER_ID, ");
			sqlQuery.append(" REC_STAT_CODE ");
			sqlQuery.append( ") VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )" );

			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			
			int count = 1;

			if (!StringUtils.isBlank(vo.getCpfCnpjNbr())) {
				preparedStatement.setString(count++, FormatUtils.unformatterDoc(vo.getCpfCnpjNbr().trim()));
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT);
			}
			
			if (!StringUtils.isBlank(vo.getCpfStatus())) {				
				preparedStatement.setString(count++, vo.getCpfStatus());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getCpfUpdMthYr())) {				
				preparedStatement.setString(count++, vo.getCpfUpdMthYr().replaceAll("/", ""));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getLastAuthDate()!=null) {
				preparedStatement.setTimestamp(count++, new java.sql.Timestamp(vo.getLastAuthDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getLastAuthUserId())) {
				preparedStatement.setString(count++, vo.getLastAuthUserId());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getLastUpdDate()!=null) {
				preparedStatement.setTimestamp(count++, new java.sql.Timestamp(vo.getLastUpdDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getLastUpdUserId())) {
				preparedStatement.setString(count++, vo.getLastUpdUserId());
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
	
	public StatusCpfCnpjVO update(StatusCpfCnpjVO vo) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();		

		try {
			connection = OracleODSDAOFactory.getConnection();
			
			sqlQuery.append("  UPDATE " + C_PL_SCHEMA + "TPL_PRVT_CPF_STAT_MOV SET " );
			sqlQuery.append(" CPF_STATUS    = ?, ");
			sqlQuery.append(" CPF_UPD_MTH_YR    = ?, ");
			sqlQuery.append(" LAST_UPD_DATE     = ?, ");
			sqlQuery.append(" LAST_UPD_USER_ID  = ?, ");
			sqlQuery.append(" REC_STAT_CODE     = ? ");
			sqlQuery.append("WHERE TPL_PRVT_CPF_STAT_MOV.CPF_CNPJ_NBR = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			
			int count = 1;
			
			if (!StringUtils.isBlank(vo.getCpfStatus())) {				
				preparedStatement.setString(count++, vo.getCpfStatus().replaceAll("/", ""));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getCpfUpdMthYr())) {				
				preparedStatement.setString(count++, vo.getCpfUpdMthYr().replaceAll("/", ""));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getLastUpdDate()!=null) {
				preparedStatement.setTimestamp(count++, new java.sql.Timestamp(vo.getLastUpdDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getLastUpdUserId())) {
				preparedStatement.setString(count++, vo.getLastUpdUserId());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getRecStatCode())) {
				preparedStatement.setString(count++, vo.getRecStatCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getCpfCnpjNbr())) {
				preparedStatement.setString(count++, FormatUtils.unformatterDoc(vo.getCpfCnpjNbr()));
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT);
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
	
	public void delete(String cpfCnpjNbr) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			query.append(" DELETE FROM " + C_PL_SCHEMA + "TPL_PRVT_CPF_STAT_MOV WHERE CPF_CNPJ_NBR = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));

			if (!StringUtils.isBlank(cpfCnpjNbr)) {
				preparedStatement.setString(1, cpfCnpjNbr);
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
	
	public void deleteByMonthYear(String monthYear) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			query.append(" DELETE FROM " + C_PL_SCHEMA + "TPL_PRVT_CPF_STAT_MOV WHERE CPF_UPD_MTH_YR = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));

			if (!StringUtils.isBlank(monthYear)) {
				preparedStatement.setString(1, monthYear.trim().replace("/", ""));
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
