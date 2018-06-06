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
import com.citibank.newcpb.vo.DocumentsVO;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class TplPrvtDocumentsDAO extends BaseOracleDAO {

	public ArrayList<DocumentsVO> list( String filterCpfCnpj, String filterName, String filterTitulo, boolean isLike) throws ParseException {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<DocumentsVO> resultList = null;

		try {
			connection = OracleODSDAOFactory.getConnection();			
			query.append(" SELECT CUST_AUTH_PERS.CPF_CNPJ_NBR, ");
			query.append("   CUST_AUTH_PERS.CUST_NAME, ");
			query.append("   CUST_AUTH_PERS.EM_NBR, ");
			query.append("   CUST_AUTH_PERS.CUST_TYPE, ");
			query.append("   NVL(DMN_TIT.DOC_TIT_TEXT, '') as TITLE_TEXT, ");
			query.append("   NVL(DOC_TIT.LAST_AUTH_DATE,  '') as AUTH_DATE ");
			query.append("   FROM ( ");
			query.append("   SELECT CUST.CUST_FULL_NAME AS CUST_NAME, ");
			query.append("   CUST.CUST_CPF_CNPJ_NBR AS CPF_CNPJ_NBR , ");
			query.append("   CUST.CUST_EM_NBR AS EM_NBR, ");
			query.append("   'Cliente' as CUST_TYPE, ");
			query.append("   'X' as AUTH_IND ");
			query.append("   FROM PL.TPL_PRVT_CUSTOMER CUST ");
			query.append("   WHERE CUST_STAT_CODE = 'A' ");
			
			if (filterName != null && !filterName.equals("")) {
				if(isLike){
					query.append(" AND UPPER(CUST.CUST_FULL_NAME) LIKE TRIM(?) ");
				}else{
					query.append(" AND UPPER(CUST.CUST_FULL_NAME) = TRIM(?) ");
				}				
			}
		    
			if (filterCpfCnpj != null && !filterCpfCnpj.equals("")) {
				if(isLike){
					query.append(" AND UPPER(CUST_CPF_CNPJ_NBR) LIKE TRIM(?) ");
				}else{
					query.append(" AND UPPER(CUST_CPF_CNPJ_NBR) = TRIM(?) ");
				}
			}
			
			query.append("   UNION ");
			query.append("   SELECT AUTH_PERSN.AUTH_PERSN_NAME AS CUST_NAME, ");
			query.append("   AUTH_PERSN.CPF_CNPJ_NBR AS CPF_CNPJ_NBR ,");
			query.append("   AUTH_PERSN.EM_NBR AS EM_NBR, ");
			query.append("   CASE ('A') ");
			query.append("   WHEN 'P' THEN 'PROCURADOR' ");
			query.append("   WHEN 'A' THEN 'PESSOA AUTORIZADA' ");
			query.append("   ELSE '' ");
			query.append("   END CUST_TYPE, ");
			query.append("   PRSN_ACCT.AUTH_IND as AUTH_IND");
			query.append("   FROM PL.TPL_PRVT_AUTH_PERSN AUTH_PERSN, ");
			query.append("   PL.TPL_PRVT_AUT_PRSN_ACCT PRSN_ACCT ");
			query.append("   WHERE AUTH_PERSN.EM_NBR = PRSN_ACCT.EM_NBR ");
		    
			if (filterName != null && !filterName.equals("")) {
				if(isLike){
					query.append(" AND UPPER(CUST.CUST_FULL_NAME) LIKE TRIM(?) ");
				}else{
					query.append(" AND UPPER(CUST.CUST_FULL_NAME) = TRIM(?) ");
				}				
			}
		    
			if (filterCpfCnpj != null && !filterCpfCnpj.equals("")) {
				if(isLike){
					query.append(" AND UPPER(CPF_CNPJ_NBR) LIKE TRIM(?) ");
				}else{
					query.append(" AND UPPER(CPF_CNPJ_NBR) = TRIM(?) ");
				}
			}
			
			query.append("   ) CUST_AUTH_PERS, ");
			query.append("   PL.TPL_PRVT_DOC_TIT DOC_TIT, ");
			query.append("   PL.TPL_PRVT_DMN_DOC_TIT DMN_TIT");
			query.append("   WHERE CUST_AUTH_PERS.CPF_CNPJ_NBR = DOC_TIT.CPF_CNPJ_NBR(+) ");
			query.append("   AND CUST_AUTH_PERS.AUTH_IND = DOC_TIT.AUTH_IND (+) ");
			query.append("   AND DOC_TIT.DOC_TIT_CODE = DMN_TIT.DOC_TIT_CODE (+) ");
			
			if (filterTitulo != null && !filterTitulo.equals("")) {
				if(isLike){
					query.append(" AND UPPER(DOC_TIT.DOC_TIT_CODE) LIKE TRIM(?) ");
				}else{
					query.append(" AND UPPER(DOC_TIT.DOC_TIT_CODE) = TRIM(?) ");
				}
			}
			
		   
			query.append("   ORDER BY CUST_AUTH_PERS.CPF_CNPJ_NBR, ");
			query.append("   CUST_AUTH_PERS.CUST_NAME, ");
			query.append("   CUST_AUTH_PERS.EM_NBR,");
			query.append("   CUST_AUTH_PERS. CUST_TYPE ");
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;

			if (filterName != null && !StringUtils.isBlank(filterName)) {
				if(isLike){
					preparedStatement.setString(count++, "%" + filterName.toUpperCase() + "%");
				}else{
					preparedStatement.setString(count++, filterName.toUpperCase());
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
			
			
			if (filterCpfCnpj != null && !StringUtils.isBlank(filterCpfCnpj)) {
				if(isLike){
					preparedStatement.setString(count++, "%" + FormatUtils.unformatterDoc(filterCpfCnpj).toUpperCase() + "%");
				}else{
					preparedStatement.setString(count++, FormatUtils.unformatterDoc(filterCpfCnpj).toUpperCase());
				}
			}
			
			if (filterTitulo != null && !StringUtils.isBlank(filterTitulo)) {
				if(isLike){
					preparedStatement.setString(count++, "%" + filterTitulo.toUpperCase() + "%");
				}else{
					preparedStatement.setString(count++, filterTitulo.toUpperCase());
				}
			}
			
			
            preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<DocumentsVO>();
				while (rs.next()){
					DocumentsVO result = new DocumentsVO();
						
					result.setName(rs.getString("CUST_NAME") != null ? rs.getString("CUST_NAME").toString() : null);
					result.setCpfCnpjNbr(rs.getString("CPF_CNPJ_NBR") != null ? FormatUtils.formatterDoc(rs.getString("CPF_CNPJ_NBR").toString()) : null);
					result.setEmNbr(rs.getString("EM_NBR") != null ? rs.getString("EM_NBR") : null);	
				    result.setType(rs.getString("CUST_TYPE") != null ? rs.getString("CUST_TYPE").toString() : null);
				    result.setDocumentsTitulo(rs.getString("DOC_TIT_TEXT") != null ? rs.getString("DOC_TIT_TEXT").toString() : null);		
				    result.setDocumentsDate(rs.getString("LAST_AUTH_DATE") != null ? rs.getString("LAST_AUTH_DATE").toString() : null);		
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
	
	public ArrayList<DocumentsVO> listInsert( String filterCpfCnpj, String filterName, String filterProcPA) throws ParseException {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<DocumentsVO> resultList = null;

		try {
			connection = OracleODSDAOFactory.getConnection();			
			query.append("   SELECT CUST_AUTH_PERS.CPF_CNPJ_NBR, ");
			query.append("   CUST_AUTH_PERS.CUST_NAME, ");
			query.append("   CUST_AUTH_PERS.EM_NBR, ");
			query.append("   CUST_AUTH_PERS.CUST_TYPE, ");
			query.append("   DECODE(count (DOC_TIT.DOC_TIT_CODE), 0, 'N', 'S') as HAS_DOC ");
            query.append("   FROM ( ");
            
        	if (filterProcPA == null) {
            
            
			query.append("   SELECT CUST.CUST_FULL_NAME AS CUST_NAME, ");
			query.append("   CUST.CUST_CPF_CNPJ_NBR AS CPF_CNPJ_NBR , ");
			query.append("   CUST.CUST_EM_NBR AS EM_NBR, ");
			query.append("   'Cliente' as CUST_TYPE, ");
			query.append("   'X' as AUTH_IND ");
			query.append("   FROM PL.TPL_PRVT_CUSTOMER CUST ");
			query.append("   WHERE CUST_STAT_CODE = 'A' ");
			
			if (filterName != null && !filterName.equals("")) {
				//if(isLike){
					query.append(" AND UPPER(CUST_FULL_NAME) LIKE TRIM('" + "%" + filterName + "%" + "') ");
				//}else{
				//	query.append(" AND UPPER(CUST.CUST_FULL_NAME) = TRIM(?) ");
				//}				
			}
		    
			if (filterCpfCnpj != null && !filterCpfCnpj.equals("")) {
				//if(isLike){
					query.append(" AND UPPER(CUST_CPF_CNPJ_NBR) LIKE TRIM('" + "%" + filterCpfCnpj + "%" + "') ");
			//	}else{
			//		query.append(" AND UPPER(CUST_CPF_CNPJ_NBR) = TRIM(?) ");
			//	}
			}
			
        	}else{
        		
        	
			//query.append("   UNION ");
			query.append("   SELECT AUTH_PERSN.AUTH_PERSN_NAME AS CUST_NAME, ");
			query.append("   AUTH_PERSN.CPF_CNPJ_NBR AS CPF_CNPJ_NBR ,");
			query.append("   AUTH_PERSN.EM_NBR AS EM_NBR, ");
			query.append("   CASE (PRSN_ACCT.AUTH_IND) ");
			query.append("   WHEN 'P' THEN 'PROCURADOR' ");
			query.append("   WHEN 'A' THEN 'PESSOA AUTORIZADA' ");
			query.append("   ELSE '' ");
			query.append("   END CUST_TYPE, ");
			query.append("   PRSN_ACCT.AUTH_IND as AUTH_IND");
			query.append("   FROM PL.TPL_PRVT_AUTH_PERSN AUTH_PERSN, ");
			query.append("   PL.TPL_PRVT_AUT_PRSN_ACCT PRSN_ACCT ");
			query.append("   WHERE AUTH_PERSN.EM_NBR = PRSN_ACCT.EM_NBR ");
		    
			if (filterName != null && !filterName.equals("")) {
			//	if(isLike){
					query.append(" AND UPPER(AUTH_PERSN_NAME) LIKE TRIM('" + "%" + filterName + "%" + "') ");
			//	}else{
			//		query.append(" AND UPPER(CUST.CUST_FULL_NAME) = TRIM(?) ");
			//	}				
			}
		    
			if (filterCpfCnpj != null && !filterCpfCnpj.equals("")) {
			//	if(isLike){
					query.append(" AND UPPER(CPF_CNPJ_NBR) LIKE TRIM('" + "%" + filterCpfCnpj + "%" + "') ");
			//	}else{
			//		query.append(" AND UPPER(CPF_CNPJ_NBR) = TRIM(?) ");
			//	}
			}
			
        	}
			
			query.append("   ) CUST_AUTH_PERS, ");
			query.append("   PL.TPL_PRVT_DOC_TIT DOC_TIT ");
			//query.append("   PL.TPL_PRVT_DMN_DOC_TIT DMN_TIT");
			query.append("   WHERE CUST_AUTH_PERS.CPF_CNPJ_NBR = DOC_TIT.CPF_CNPJ_NBR(+) ");
			query.append("   AND CUST_AUTH_PERS.AUTH_IND = DOC_TIT.AUTH_IND (+) ");
			//query.append("   AND DOC_TIT.DOC_TIT_CODE = DMN_TIT.DOC_TIT_CODE (+) ");
			query.append("   GROUP BY CUST_AUTH_PERS.CPF_CNPJ_NBR, ");
			query.append("   CUST_AUTH_PERS.CUST_NAME, ");
			query.append("   CUST_AUTH_PERS.EM_NBR,");
			query.append("   CUST_AUTH_PERS.CUST_TYPE ");
			query.append("   ORDER BY CUST_AUTH_PERS.CPF_CNPJ_NBR, ");
			query.append("   CUST_AUTH_PERS.CUST_NAME, ");
			query.append("   CUST_AUTH_PERS.EM_NBR,");
			query.append("   CUST_AUTH_PERS.CUST_TYPE ");
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			rs = preparedStatement.executeQuery();
			 
			/*int count = 1;

			if (filterName != null && !StringUtils.isBlank(filterName)) {
				if(isLike){
					preparedStatement.setString(count++, "%" + filterName.toUpperCase() + "%");
				}else{
					preparedStatement.setString(count++, filterName.toUpperCase());
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
			
			
			if (filterCpfCnpj != null && !StringUtils.isBlank(filterCpfCnpj)) {
				if(isLike){
					preparedStatement.setString(count++, "%" + FormatUtils.unformatterDoc(filterCpfCnpj).toUpperCase() + "%");
				}else{
					preparedStatement.setString(count++, FormatUtils.unformatterDoc(filterCpfCnpj).toUpperCase());
				}
			}
			
			if (filterTitulo != null && !StringUtils.isBlank(filterTitulo)) {
				if(isLike){
					preparedStatement.setString(count++, "%" + filterTitulo.toUpperCase() + "%");
				}else{
					preparedStatement.setString(count++, filterTitulo.toUpperCase());
				}
			}
			
			
            preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();*/
			
			if(rs!=null){
				resultList = new ArrayList<DocumentsVO>();
				while (rs.next()){
					DocumentsVO result = new DocumentsVO();
						
					result.setName(rs.getString("CUST_NAME") != null ? rs.getString("CUST_NAME").toString() : null);
					result.setCpfCnpjNbr(rs.getString("CPF_CNPJ_NBR") != null ? FormatUtils.formatterDoc(rs.getString("CPF_CNPJ_NBR").toString()) : null);
					result.setEmNbr(rs.getString("EM_NBR") != null ? rs.getString("EM_NBR") : null);	
				    result.setType(rs.getString("CUST_TYPE") != null ? rs.getString("CUST_TYPE").toString() : null);
				    result.setHasDoc(rs.getString("HAS_DOC") != null ? rs.getString("HAS_DOC").toString() : null);		
				    //result.setDocumentsDate(rs.getString("LAST_AUTH_DATE") != null ? rs.getString("LAST_AUTH_DATE").toString() : null);		
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

