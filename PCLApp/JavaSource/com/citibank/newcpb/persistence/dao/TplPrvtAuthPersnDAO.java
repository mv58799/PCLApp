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
import com.citibank.newcpb.vo.AuthorizationPersonVO;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class TplPrvtAuthPersnDAO extends BaseOracleDAO {

	public ArrayList<AuthorizationPersonVO> list(String filterNumberEM, String filterCpfCnpj, String filterName, boolean isLike) throws ParseException {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<AuthorizationPersonVO> resultList = null;

		try {
			connection = OracleODSDAOFactory.getConnection();			
			query.append(" SELECT EM_NBR, ");
			query.append("   AUTH_PERSN_NAME, ");
			query.append("   DOC_ID, ");
			query.append("   BIRTH_DATE, ");
			query.append("   CPF_CNPJ_NBR, ");
			query.append("   PROF_TEXT, ");
			query.append("   LAST_AUTH_DATE, ");
			query.append("   LAST_AUTH_USER_ID, ");
			query.append("   LAST_UPD_DATE, ");
			query.append("   LAST_UPD_USER_ID, ");
			query.append("   REC_STAT_CODE, ");
			query.append("   ADDR_NAME_TEXT, ");
			query.append("   ADDR_NEIGHB_TEXT, ");
			query.append("   ADDR_CITY_TEXT, ");
			query.append("   ADDR_STATE_CODE, ");
			query.append("   ADDR_CNTRY_CODE, ");
			query.append("   ZIP_CODE ");
			query.append(" FROM " + C_PL_SCHEMA + "TPL_PRVT_AUTH_PERSN ");
			query.append(" WHERE TPL_PRVT_AUTH_PERSN.REC_STAT_CODE <> 'I' ");
			
			if (filterNumberEM != null && !filterNumberEM.equals("")) {
				if(isLike){
					query.append(" AND UPPER(TPL_PRVT_AUTH_PERSN.EM_NBR) LIKE TRIM(?) ");
				}else{
					query.append(" AND UPPER(TPL_PRVT_AUTH_PERSN.EM_NBR) = TRIM(?) ");
				}				
			}
			
			if (filterCpfCnpj != null && !filterCpfCnpj.equals("")) {
				if(isLike){
					query.append(" AND UPPER(TPL_PRVT_AUTH_PERSN.CPF_CNPJ_NBR) LIKE TRIM(?) ");
				}else{
					query.append(" AND UPPER(TPL_PRVT_AUTH_PERSN.CPF_CNPJ_NBR) = TRIM(?) ");
				}
			}
			
			if (filterName != null && !filterName.equals("")) {
				if(isLike){
					query.append(" AND UPPER(TPL_PRVT_AUTH_PERSN.AUTH_PERSN_NAME) LIKE TRIM(?) ");
				}else{
					query.append(" AND UPPER(TPL_PRVT_AUTH_PERSN.AUTH_PERSN_NAME) = TRIM(?) ");
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
			
			if (filterCpfCnpj != null && !StringUtils.isBlank(filterCpfCnpj)) {
				if(isLike){
					preparedStatement.setString(count++, "%" + FormatUtils.unformatterDoc(filterCpfCnpj).toUpperCase() + "%");
				}else{
					preparedStatement.setString(count++, FormatUtils.unformatterDoc(filterCpfCnpj).toUpperCase());
				}
			}
			
			if (filterName != null && !StringUtils.isBlank(filterName)) {
				if(isLike){
					preparedStatement.setString(count++, "%" + filterName.toUpperCase() + "%");
				}else{
					preparedStatement.setString(count++, filterName.toUpperCase());
				}
			}

			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<AuthorizationPersonVO>();
				while (rs.next()){
					AuthorizationPersonVO result = new AuthorizationPersonVO();
					result.setEmNbr(rs.getString("EM_NBR") != null ? rs.getString("EM_NBR").toString() : null);	
					result.setAuthPersnName(rs.getString("AUTH_PERSN_NAME") != null ? rs.getString("AUTH_PERSN_NAME").toString() : null);	
					result.setDocId(rs.getString("DOC_ID") != null ? rs.getString("DOC_ID").toString() : null);	
					result.setBirthDate(rs.getTimestamp("BIRTH_DATE") != null ? FormatUtils.dateToStringFormated(rs.getTimestamp("BIRTH_DATE"), FormatUtils.C_FORMAT_DATE_DD_MM_YYYY) : null);	
					result.setCpfCnpjNbr(rs.getString("CPF_CNPJ_NBR") != null ? FormatUtils.formatterDoc(rs.getString("CPF_CNPJ_NBR").toString()) : null);	
					result.setProfText(rs.getString("PROF_TEXT") != null ? rs.getString("PROF_TEXT").toString() : null);	
					result.setLastAuthDate(rs.getTimestamp("LAST_AUTH_DATE") != null ? rs.getTimestamp("LAST_AUTH_DATE") : null);	
					result.setLastAuthUser(rs.getString("LAST_AUTH_USER_ID") != null ? rs.getString("LAST_AUTH_USER_ID").toString() : null);	
					result.setLastUpdDate(rs.getTimestamp("LAST_UPD_DATE") != null ? rs.getTimestamp("LAST_UPD_DATE") : null);	
					result.setLastUpdUser(rs.getString("LAST_UPD_USER_ID") != null ? rs.getString("LAST_UPD_USER_ID").toString() : null);	
					result.setRecStatCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);					 
					result.setStreet(rs.getString("ADDR_NAME_TEXT")!= null ? rs.getString("ADDR_NAME_TEXT").toString() : null);
					result.setNeighborhood(rs.getString("ADDR_NEIGHB_TEXT")!= null ? rs.getString("ADDR_NEIGHB_TEXT").toString() : null);
					result.setCity(rs.getString("ADDR_CITY_TEXT")!= null ? rs.getString("ADDR_CITY_TEXT").toString() : null);
					result.setUf(rs.getString("ADDR_STATE_CODE")!= null ? rs.getString("ADDR_STATE_CODE").toString() : null);
					result.setAddrCntryCode(rs.getString("ADDR_CNTRY_CODE")!= null ? rs.getString("ADDR_CNTRY_CODE").toString() : null);
					result.setZipCode(rs.getString("ZIP_CODE")!= null ? rs.getString("ZIP_CODE").toString() : null);					
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
	
	
	public boolean validateEmNbr(String filterNumberEM){
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		boolean aux = false;

		try {
			connection = OracleODSDAOFactory.getConnection();			
			query.append(" SELECT * ");
	        query.append(" FROM " + C_PL_SCHEMA + "TPL_PRVT_AUTH_PERSN ");
			query.append(" WHERE TPL_PRVT_AUTH_PERSN.EM_NBR =  '" + filterNumberEM  + "'");
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
		
			rs = preparedStatement.executeQuery();
			
			if(rs.next()){
		       aux = true;
			}
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		
		return aux;
		
	}

	
	public AuthorizationPersonVO insert(AuthorizationPersonVO vo) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();		

		try {
			connection = OracleODSDAOFactory.getConnection();
			
			sqlQuery.append("  INSERT INTO " + C_PL_SCHEMA + "TPL_PRVT_AUTH_PERSN ( " );
			sqlQuery.append(" EM_NBR, ");
			sqlQuery.append(" AUTH_PERSN_NAME, ");
			sqlQuery.append(" DOC_ID, ");
			sqlQuery.append(" BIRTH_DATE, ");
			sqlQuery.append(" CPF_CNPJ_NBR, ");
			sqlQuery.append(" PROF_TEXT, ");
			sqlQuery.append(" LAST_AUTH_DATE, ");
			sqlQuery.append(" LAST_AUTH_USER_ID, ");
			sqlQuery.append(" LAST_UPD_DATE, ");
			sqlQuery.append(" LAST_UPD_USER_ID, ");
			sqlQuery.append(" REC_STAT_CODE, ");
			sqlQuery.append(" ADDR_NAME_TEXT, ");
			sqlQuery.append(" ADDR_NEIGHB_TEXT, ");
			sqlQuery.append(" ADDR_CITY_TEXT, ");
			sqlQuery.append(" ADDR_STATE_CODE, ");
			sqlQuery.append(" ADDR_CNTRY_CODE, ");
			sqlQuery.append(" ZIP_CODE ");
			sqlQuery.append( ") VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );

			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			
			int count = 1;

			if (!StringUtils.isBlank(vo.getEmNbr())) {
				preparedStatement.setString(count++, FormatUtils.unformatterDoc(vo.getEmNbr()));
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT);
			}
			
			if (!StringUtils.isBlank(vo.getAuthPersnName())) {				
				preparedStatement.setString(count++, vo.getAuthPersnName());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getDocId())) {				
				preparedStatement.setString(count++, vo.getDocId());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getBirthDate())) {				
				Date lastIosRevDate =  FormatUtils.formatToDate(vo.getBirthDate(),FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
				preparedStatement.setDate(count++, new java.sql.Date(lastIosRevDate.getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getCpfCnpjNbr())) {				
				preparedStatement.setString(count++, FormatUtils.unformatterDoc(vo.getCpfCnpjNbr()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getProfText())) {				
				preparedStatement.setString(count++, vo.getProfText());
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
			
			if (!StringUtils.isBlank(vo.getRecStatCode())) {
				preparedStatement.setString(count++, vo.getRecStatCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getStreet())) {
				preparedStatement.setString(count++, vo.getStreet());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getNeighborhood())) {
				preparedStatement.setString(count++, vo.getNeighborhood());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getCity())) {
				preparedStatement.setString(count++, vo.getCity());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getUf())) {
				preparedStatement.setString(count++, vo.getUf());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getAddrCntryCode())) {
				preparedStatement.setString(count++, vo.getAddrCntryCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getZipCode())) {
				preparedStatement.setString(count++, vo.getZipCode());
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
	
	public AuthorizationPersonVO update(AuthorizationPersonVO vo) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();		

		try {
			connection = OracleODSDAOFactory.getConnection();
			
			sqlQuery.append("  UPDATE " + C_PL_SCHEMA + "TPL_PRVT_AUTH_PERSN SET " );
			sqlQuery.append(" AUTH_PERSN_NAME = ?, ");
			sqlQuery.append(" DOC_ID = ?, ");
			sqlQuery.append(" BIRTH_DATE = ?, ");
			sqlQuery.append(" CPF_CNPJ_NBR = ?, ");
			sqlQuery.append(" PROF_TEXT = ?, ");
			sqlQuery.append(" LAST_AUTH_DATE = ?, ");
			sqlQuery.append(" LAST_AUTH_USER_ID = ?, ");
			sqlQuery.append(" LAST_UPD_DATE = ?, ");
			sqlQuery.append(" LAST_UPD_USER_ID = ?, ");
			sqlQuery.append(" REC_STAT_CODE = ?, ");
			sqlQuery.append(" ADDR_NAME_TEXT = ?, ");
			sqlQuery.append(" ADDR_NEIGHB_TEXT = ?, ");
			sqlQuery.append(" ADDR_CITY_TEXT = ?, ");
			sqlQuery.append(" ADDR_STATE_CODE = ?, ");
			sqlQuery.append(" ADDR_CNTRY_CODE = ?, ");
			sqlQuery.append(" ZIP_CODE = ? ");
			sqlQuery.append("WHERE TPL_PRVT_AUTH_PERSN.EM_NBR = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			
			int count = 1;
			
			if (!StringUtils.isBlank(vo.getAuthPersnName())) {				
				preparedStatement.setString(count++, vo.getAuthPersnName());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getDocId())) {				
				preparedStatement.setString(count++, vo.getDocId());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getBirthDate())) {				
				Date lastIosRevDate =  FormatUtils.formatToDate(vo.getBirthDate(),FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
				preparedStatement.setDate(count++, new java.sql.Date(lastIosRevDate.getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getCpfCnpjNbr())) {				
				preparedStatement.setString(count++, FormatUtils.unformatterDoc(vo.getCpfCnpjNbr()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getProfText())) {				
				preparedStatement.setString(count++, vo.getProfText());
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
			
			if (!StringUtils.isBlank(vo.getRecStatCode())) {
				preparedStatement.setString(count++, vo.getRecStatCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getStreet())) {
				preparedStatement.setString(count++, vo.getStreet());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getNeighborhood())) {
				preparedStatement.setString(count++, vo.getNeighborhood());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getCity())) {
				preparedStatement.setString(count++, vo.getCity());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getUf())) {
				preparedStatement.setString(count++, vo.getUf());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getAddrCntryCode())) {
				preparedStatement.setString(count++, vo.getAddrCntryCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getZipCode())) {
				preparedStatement.setString(count++, vo.getZipCode());
			} else {
				preparedStatement.setString(count++, null);
			}
						
			if (!StringUtils.isBlank(vo.getEmNbr())) {
				preparedStatement.setString(count++, FormatUtils.unformatterDoc(vo.getEmNbr()));
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
	
	public void delete(String emNbr) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			query.append(" DELETE FROM " + C_PL_SCHEMA + "TPL_PRVT_AUTH_PERSN WHERE EM_NBR = ? ");

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
}
