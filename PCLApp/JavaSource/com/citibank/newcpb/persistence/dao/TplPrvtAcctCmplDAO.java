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
import com.citibank.newcpb.vo.AcctCmplVO;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class TplPrvtAcctCmplDAO extends BaseOracleDAO {

	public ArrayList<AcctCmplVO> listByAcctNbr(String acctNbr) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<AcctCmplVO> resultList = null;

		try {
			connection = OracleODSDAOFactory.getConnection();			
			query.append(" SELECT TPL_PRVT_ACCT_CMPL.ACCT_NBR, "
			+ "   TPL_PRVT_ACCT_CMPL.LAST_AUTH_DATE, "
			+ "   TPL_PRVT_ACCT_CMPL.LAST_AUTH_USER_ID, "
			+ "   TPL_PRVT_ACCT_CMPL.LAST_UPD_DATE, "
			+ "   TPL_PRVT_ACCT_CMPL.LAST_UPD_USER_ID, "
			+ "   TPL_PRVT_ACCT_CMPL.REC_STAT_CODE"
			+ " FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_CMPL "
			+ " WHERE 1=1 ");
			
			
			if(acctNbr!=null){
				if (!StringUtils.isBlank(acctNbr)) {
					query.append(" AND UPPER(TPL_PRVT_ACCT_CMPL.ACCT_NBR) = TRIM(?) ");
				}
				
			}
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;
			
			if(acctNbr!=null){
				
				if (!StringUtils.isBlank(acctNbr)) {
					preparedStatement.setString(count++, acctNbr.toUpperCase());
				}
				
			}

			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<AcctCmplVO>();
				while (rs.next()){
					
					AcctCmplVO result = new AcctCmplVO();
					
					result.setAcctNbr(rs.getString("ACCT_NBR") != null ? rs.getString("ACCT_NBR").toString() : null);						
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
		
	public ArrayList<AcctCmplVO> listByFilter(String acctNbr, String cpfCnpjNbr, String accountType) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<AcctCmplVO> resultList = null;

		try {
			connection = OracleODSDAOFactory.getConnection();			
			query.append(" SELECT TPL_PRVT_ACCT_CMPL.ACCT_NBR, "
			
			+ "   TPL_PRVT_ACCT_CMPL.ACCT_SRC_TYPE, "
			+ "   TPL_PRVT_ACCT_CMPL.CPF_CNPJ_NBR, "	
			+ "   TPL_PRVT_ACCT_CMPL.CTRCT_STA_DATE, "	
			+ "   TPL_PRVT_ACCT_CMPL.AGENT_IND, "			
			+ "   TPL_PRVT_ACCT_CMPL.LAST_AUTH_DATE, "
			+ "   TPL_PRVT_ACCT_CMPL.LAST_AUTH_USER_ID, "
			+ "   TPL_PRVT_ACCT_CMPL.LAST_UPD_DATE, "
			+ "   TPL_PRVT_ACCT_CMPL.LAST_UPD_USER_ID, "
			+ "   TPL_PRVT_ACCT_CMPL.REC_STAT_CODE, "
			+ "   TPL_PRVT_ACCT_CMPL.RISK_LEVEL_CODE, "
			+ "   TO_CHAR(TPL_PRVT_ACCT_CMPL.LAST_IOS_REV_DATE, 'DD/MM/YYYY') AS LAST_IOS_REV_DATE , "
			+ "   TPL_PRVT_ACCT_CMPL.ACCT_TYPE "
			+ " FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_CMPL "
			+ " WHERE 1=1 ");
			
			
						
				if (!StringUtils.isBlank(acctNbr)) {
					query.append(" AND UPPER(TPL_PRVT_ACCT_CMPL.ACCT_NBR) LIKE TRIM(?) ");
				}
				
				if (!StringUtils.isBlank(cpfCnpjNbr)) {
					query.append(" AND UPPER(TPL_PRVT_ACCT_CMPL.CPF_CNPJ_NBR) LIKE TRIM(?) ");
				}
				
				if (!StringUtils.isBlank(accountType)) {
					query.append(" AND UPPER(TPL_PRVT_ACCT_CMPL.ACCT_SRC_TYPE) LIKE TRIM(?) ");
				}
			
			
			query.append(" AND TPL_PRVT_ACCT_CMPL.REC_STAT_CODE <> 'I' ");
			
			
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;
			
				if (!StringUtils.isBlank(acctNbr)) {
					preparedStatement.setString(count++, "%" + acctNbr.toUpperCase() + "%");
				}
				
				if (!StringUtils.isBlank(cpfCnpjNbr)) {
					preparedStatement.setString(count++, "%" + Long.valueOf(FormatUtils.unformatterDoc(cpfCnpjNbr)) + "%");
				}
				
				if (!StringUtils.isBlank(accountType)) {
					preparedStatement.setString(count++, "%" + accountType.toUpperCase()+ "%");
				}
				
			
			

			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<AcctCmplVO>();
				while (rs.next()){
					
					AcctCmplVO result = new AcctCmplVO();
					
					result.setAcctNbr(rs.getString("ACCT_NBR") != null ? rs.getString("ACCT_NBR").toString() : null);	
					result.setAccountType(rs.getString("ACCT_SRC_TYPE") != null ? rs.getString("ACCT_SRC_TYPE").toString() : null);
					result.setCpfCnpjNbr(rs.getString("CPF_CNPJ_NBR") != null ? rs.getString("CPF_CNPJ_NBR").toString() : null);
					
					if(!StringUtils.isBlank(result.getCpfCnpjNbr())){
						result.setCpfCnpjNbr(FormatUtils.formatterDoc(result.getCpfCnpjNbr()));
					}
					
					if(rs.getDate("CTRCT_STA_DATE") != null){						
						Date ctrctDate = new Date(rs.getDate("CTRCT_STA_DATE").getTime());
						result.setContractSignatureDate(FormatUtils.dateToString(ctrctDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
										
					if(rs.getString("AGENT_IND") != null && !rs.getString("AGENT_IND").equals("")){
						if(rs.getString("AGENT_IND").trim().equals("S")){
							result.setHasAngent(true);
						}else if (rs.getString("AGENT_IND").trim().equals("N")){
							result.setHasAngent(false);
						}
					}
					
					if(rs.getDate("LAST_AUTH_DATE") != null){						
						Date lastAuthDate = new Date(rs.getDate("LAST_AUTH_DATE").getTime());
						result.setLastAuthDate(lastAuthDate);
					}
					
					result.setLastAuthUser(rs.getString("LAST_AUTH_USER_ID") != null ? rs.getString("LAST_AUTH_USER_ID").toString() : null);	
					
					if(rs.getDate("LAST_UPD_DATE") != null){						
						Date lastUpdateDate = new Date(rs.getDate("LAST_UPD_DATE").getTime());
						result.setLastUpdDate(lastUpdateDate);
					}
					
					result.setLastUpdUser(rs.getString("LAST_UPD_USER_ID") != null ? rs.getString("LAST_UPD_USER_ID").toString() : null);	
					result.setRecStatCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);
					
					result.setRiskLevelCode(rs.getString("RISK_LEVEL_CODE") != null ? rs.getString("RISK_LEVEL_CODE").toString() : null);
					result.setLastIosRevDate(rs.getString("LAST_IOS_REV_DATE") != null ? rs.getString("LAST_IOS_REV_DATE").toString() : null);
					result.setAccountTypeRDIP(rs.getString("ACCT_TYPE") != null ? rs.getString("ACCT_TYPE").toString() : null);
					
					
					result.setTableOrigin(TableTypeEnum.EFFECTIVE);
					
					resultList.add(result);
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} catch (ParseException a) {
			a.printStackTrace();
			throw new UnexpectedException(a.getErrorOffset(), C_ERROR_EXECUTING_STATEMENT, a);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return resultList;
	}
	
	public ArrayList<AcctCmplVO> listByAcctNbrToAuth(String acctNbr) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<AcctCmplVO> resultList = null;

		try {
			connection = OracleODSDAOFactory.getConnection();			
			query.append(" SELECT TPL_PRVT_ACCT_CMPL.ACCT_NBR, "
			+ "   TPL_PRVT_ACCT_CMPL.LAST_AUTH_DATE, "
			+ "   TPL_PRVT_ACCT_CMPL.LAST_AUTH_USER_ID, "
			+ "   TPL_PRVT_ACCT_CMPL.LAST_UPD_DATE, "
			+ "   TPL_PRVT_ACCT_CMPL.LAST_UPD_USER_ID, "
			+ "   TPL_PRVT_ACCT_CMPL.REC_STAT_CODE, "
			+ "   TPL_PRVT_ACCT_CMPL.RISK_LEVEL_CODE, "
			+ "   TO_CHAR(TPL_PRVT_ACCT_CMPL.LAST_IOS_REV_DATE, 'DD/MM/YYYY') AS LAST_IOS_REV_DATE , "
			+ "   TPL_PRVT_ACCT_CMPL.ACCT_TYPE "

			+ " FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_CMPL "
			+ " WHERE TPL_PRVT_ACCT_CMPL.AGENT_IND = 'S' "
			+ " AND TPL_PRVT_ACCT_CMPL.REC_STAT_CODE <> 'I' ");
			
			
			if(acctNbr!=null){
				if (!StringUtils.isBlank(acctNbr)) {
					query.append(" AND UPPER(TPL_PRVT_ACCT_CMPL.ACCT_NBR) = TRIM(?) ");
				}
				
			}
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;
			
			if(acctNbr!=null){
				
				if (!StringUtils.isBlank(acctNbr)) {
					preparedStatement.setString(count++, acctNbr.toUpperCase());
				}
				
			}

			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<AcctCmplVO>();
				while (rs.next()){
					
					AcctCmplVO result = new AcctCmplVO();
					
					result.setAcctNbr(rs.getString("ACCT_NBR") != null ? rs.getString("ACCT_NBR").toString() : null);						
					result.setLastAuthDate(rs.getDate("LAST_AUTH_DATE") != null ? new Date(rs.getDate("LAST_AUTH_DATE").getTime()) : null);	
					result.setLastAuthUser(rs.getString("LAST_AUTH_USER_ID") != null ? rs.getString("LAST_AUTH_USER_ID").toString() : null);					
					result.setLastUpdDate(rs.getDate("LAST_UPD_DATE") != null ? new Date(rs.getDate("LAST_UPD_DATE").getTime()) : null);
					result.setLastUpdUser(rs.getString("LAST_UPD_USER_ID") != null ? rs.getString("LAST_UPD_USER_ID").toString() : null);	
					result.setRecStatCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);	
					result.setRiskLevelCode(rs.getString("RISK_LEVEL_CODE") != null ? rs.getString("RISK_LEVEL_CODE").toString() : null);
					result.setLastIosRevDate(rs.getString("LAST_IOS_REV_DATE") != null ? rs.getString("LAST_IOS_REV_DATE").toString() : null);
					result.setAccountTypeRDIP(rs.getString("ACCT_TYPE") != null ? rs.getString("ACCT_TYPE").toString() : null);
	
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
	
	public AcctCmplVO getAccountComplementToQuestionKe(String acctNbr, String cpfCnpjNbr, String accountType) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		AcctCmplVO result = null;

		try {
			connection = OracleODSDAOFactory.getConnection();		
			
			query.append(" SELECT ACCT_COMPL.ACCT_NBR, "				
			+ "   CUST.CUST_FULL_NAME, "
			+ "   CUST.CUST_EM_NBR, "
			+ "   CUST.CUST_GFCID_NBR, "
			+ "   CUST.CUST_CPF_CNPJ_NBR, "
			+ "	  CUST.CUST_OFFICER_SOEID, "
			+ "   ACCT_COMPL.RISK_LEVEL_CODE, "	
			+ "   ACCT_COMPL.LAST_AUTH_DATE, "
			+ "   ACCT_COMPL.LAST_AUTH_USER_ID, "
			+ "   ACCT_COMPL.LAST_UPD_DATE, "
			+ "   ACCT_COMPL.LAST_UPD_USER_ID, "
			+ "   ACCT_COMPL.REC_STAT_CODE, "
			+ "   ACCT_COMPL.ACCT_SRC_TYPE, "		
			+ "   ACCT_COMPL.ACCT_TYPE, "
			+ "   TO_CHAR(ACCT_COMPL.LAST_IOS_REV_DATE, 'DD/MM/YYYY') AS LAST_IOS_REV_DATE"
			+ " FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_CMPL ACCT_COMPL "
			+ " LEFT JOIN " + C_PL_SCHEMA + "TPL_PRVT_CUSTOMER CUST "
			+ " ON  ACCT_COMPL.CPF_CNPJ_NBR = CUST.CUST_CPF_CNPJ_NBR "
			+ " WHERE 1=1 ");			

			if(acctNbr!=null){
				if (!StringUtils.isBlank(acctNbr)) {
					query.append(" AND UPPER(ACCT_COMPL.ACCT_NBR) = TRIM(?) ");
				}
			}
			
			if(cpfCnpjNbr!=null){
				if (!StringUtils.isBlank(cpfCnpjNbr)) {
					query.append(" AND UPPER(ACCT_COMPL.CPF_CNPJ_NBR) = TRIM(?) ");
				}
			}
			
			if(accountType!=null){
				if (!StringUtils.isBlank(accountType)) {
					query.append(" AND UPPER(ACCT_COMPL.ACCT_SRC_TYPE) = TRIM(?) ");
				}
			}
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;
			
			if(acctNbr!=null){
				
				if (!StringUtils.isBlank(acctNbr)) {
					preparedStatement.setString(count++, acctNbr.toUpperCase());
				}	
			}
			
			if(cpfCnpjNbr!=null){
				
				if (!StringUtils.isBlank(cpfCnpjNbr)) {
					preparedStatement.setString(count++, Long.valueOf(FormatUtils.unformatterDoc(cpfCnpjNbr)).toString());
				}	
			}
			
			
			if(accountType!=null){
				
				if (!StringUtils.isBlank(accountType)) {
					preparedStatement.setString(count++, accountType.toUpperCase());
				}	
			}

			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			
			if(rs!=null){
				
				while (rs.next()){
					
					result = new AcctCmplVO();
					result.setAcctNbr(rs.getString("ACCT_NBR") != null ? rs.getString("ACCT_NBR").toString() : null);	
					result.setAccountType(rs.getString("ACCT_SRC_TYPE") != null ? rs.getString("ACCT_SRC_TYPE").toString() : null);						
					result.setCustomerName(rs.getString("CUST_FULL_NAME") != null ? rs.getString("CUST_FULL_NAME").toString() : null);	
					result.setEmNbr(rs.getString("CUST_EM_NBR") != null ? rs.getString("CUST_EM_NBR").toString() : null);	
					result.setGfcid(rs.getString("CUST_GFCID_NBR") != null ? rs.getString("CUST_GFCID_NBR").toString() : null);
					result.setCpfCnpjNbr(rs.getString("CUST_CPF_CNPJ_NBR") != null ? rs.getString("CUST_CPF_CNPJ_NBR").toString() : null);	
					result.setOfficeSoeId(rs.getString("CUST_OFFICER_SOEID") != null ? rs.getString("CUST_OFFICER_SOEID").toString() : null);	
					
					
					
					if(!StringUtils.isBlank(result.getCpfCnpjNbr())){
						result.setCpfCnpjNbrFormated(FormatUtils.formatterDoc(result.getCpfCnpjNbr()));
					}
									
					if(rs.getDate("LAST_AUTH_DATE") != null){						
						Date lastAuthDate = new Date(rs.getDate("LAST_AUTH_DATE").getTime());
						result.setLastAuthDate(lastAuthDate);
					}
					
					
					result.setLastAuthUser(rs.getString("LAST_AUTH_USER_ID") != null ? rs.getString("LAST_AUTH_USER_ID").toString() : null);	
					
					if(rs.getDate("LAST_UPD_DATE") != null){						
						Date lastUpdateDate = new Date(rs.getDate("LAST_UPD_DATE").getTime());
						result.setLastUpdDate(lastUpdateDate);
					}
					
				
					result.setLastUpdUser(rs.getString("LAST_UPD_USER_ID") != null ? rs.getString("LAST_UPD_USER_ID").toString() : null);	
					result.setRecStatCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);
					result.setRiskLevelCode(rs.getString("RISK_LEVEL_CODE") != null ? rs.getString("RISK_LEVEL_CODE").toString() : null);
					result.setAccountTypeRDIP(rs.getString("ACCT_TYPE") != null ? rs.getString("ACCT_TYPE").toString() : null);			
					result.setLastIosRevDate(rs.getString("LAST_IOS_REV_DATE") != null ? rs.getString("LAST_IOS_REV_DATE").toString() : null);
					
				
					result.setTableOrigin(TableTypeEnum.EFFECTIVE);
					
				
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} catch (ParseException a) {
			a.printStackTrace();
			throw new UnexpectedException(a.getErrorOffset(), C_ERROR_EXECUTING_STATEMENT, a);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return result;
	}
	
	
	
	public ArrayList<AcctCmplVO> listByFilterToQuestionKe(String acctNbr, String cpfCnpjNbr, 
			String emNbr, String gfcidNbr, String name)  {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<AcctCmplVO> resultList = null;

		try {
			connection = OracleODSDAOFactory.getConnection();			
			query.append(" SELECT ACCT_COMPL.ACCT_NBR, "				
			+ "   CUST.CUST_FULL_NAME, "
			+ "   CUST.CUST_EM_NBR, "
			+ "   CUST.CUST_GFCID_NBR, "
			+ "   CUST.CUST_CPF_CNPJ_NBR, "
			+ "   ACCT_COMPL.RISK_LEVEL_CODE, "	
			+ "   ACCT_COMPL.LAST_AUTH_DATE, "
			+ "   ACCT_COMPL.LAST_AUTH_USER_ID, "
			+ "   ACCT_COMPL.LAST_UPD_DATE, "
			+ "   ACCT_COMPL.LAST_UPD_USER_ID, "
			+ "   ACCT_COMPL.REC_STAT_CODE, "
			+ "   ACCT_COMPL.ACCT_SRC_TYPE, "
			+ "  (CASE WHEN "
			+ "  (SELECT DISTINCT KE.ACCT_NBR FROM " + C_PL_SCHEMA +  " TPL_PRVT_ACCT_KE KE WHERE ACCT_COMPL.ACCT_NBR = KE.ACCT_NBR) "
			+ "   is not null THEN 'S' ELSE 'N' END ) AS HAS_KE, "
			+ "   TO_CHAR(ACCT_COMPL.LAST_IOS_REV_DATE, 'DD/MM/YYYY') AS LAST_IOS_REV_DATE , "
			+ "   ACCT_COMPL.ACCT_TYPE "
			+ " FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_CMPL ACCT_COMPL "
			+ " LEFT JOIN " + C_PL_SCHEMA + "TPL_PRVT_CUSTOMER CUST "
			+ " ON  ACCT_COMPL.CPF_CNPJ_NBR = CUST.CUST_CPF_CNPJ_NBR "
			+ " WHERE ACCT_COMPL.REC_STAT_CODE <> 'I' ");
			
			
			if(acctNbr!=null){
				if (!StringUtils.isBlank(acctNbr)) {
					query.append(" AND UPPER(ACCT_COMPL.ACCT_NBR) LIKE TRIM(?) ");
				}
			}
			
			if(cpfCnpjNbr!=null){
				if (!StringUtils.isBlank(cpfCnpjNbr)) {
					query.append(" AND UPPER(CUST.CUST_CPF_CNPJ_NBR) LIKE TRIM(?) ");
				}
			}
			
			if(emNbr!=null){
				if (!StringUtils.isBlank(emNbr)) {
					query.append(" AND UPPER(CUST.CUST_EM_NBR) LIKE TRIM(?) ");
				}
			}
			
			if(gfcidNbr!=null){
				if (!StringUtils.isBlank(gfcidNbr)) {
					query.append(" AND UPPER(CUST.CUST_GFCID_NBR) LIKE TRIM(?) ");
				}	
			}
			
			if(name!=null){
				if (!StringUtils.isBlank(name)) {
					query.append(" AND UPPER(CUST.CUST_FULL_NAME) LIKE TRIM(?) ");
				}	
			}
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;
			
			if(acctNbr!=null){
				if (!StringUtils.isBlank(acctNbr)) {
					preparedStatement.setString(count++, "%" + acctNbr.toUpperCase() + "%");
				}				
			}
			
			if(cpfCnpjNbr!=null){
				if (!StringUtils.isBlank(cpfCnpjNbr)) {
					preparedStatement.setString(count++, "%" + Long.valueOf(FormatUtils.unformatterDoc(cpfCnpjNbr)) + "%");
				}				
			}
			
			if(emNbr!=null){
				if (!StringUtils.isBlank(emNbr)) {
					preparedStatement.setString(count++, "%" + emNbr.toUpperCase() + "%" );
				}				
			}
			
			if(gfcidNbr!=null){
				if (!StringUtils.isBlank(gfcidNbr)) {
					preparedStatement.setString(count++, "%" + gfcidNbr.toUpperCase() + "%");
				}				
			}
			
			if(name!=null){
				if (!StringUtils.isBlank(name)) {
					preparedStatement.setString(count++, "%" + name.toUpperCase() + "%");
				}				
			}
			
			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<AcctCmplVO>();
				while (rs.next()){
					
					AcctCmplVO result = new AcctCmplVO();
					
					result.setAcctNbr(rs.getString("ACCT_NBR") != null ? rs.getString("ACCT_NBR").toString() : null);
					result.setCustomerName(rs.getString("CUST_FULL_NAME") != null ? rs.getString("CUST_FULL_NAME").toString() : null);	
					result.setEmNbr(rs.getString("CUST_EM_NBR") != null ? rs.getString("CUST_EM_NBR").toString() : null);	
					result.setGfcid(rs.getString("CUST_GFCID_NBR") != null ? rs.getString("CUST_GFCID_NBR").toString() : null);
					result.setCpfCnpjNbr(rs.getString("CUST_CPF_CNPJ_NBR") != null ? rs.getString("CUST_CPF_CNPJ_NBR").toString() : null);	
					result.setLastAuthDate(rs.getDate("LAST_AUTH_DATE") != null ? new Date(rs.getDate("LAST_AUTH_DATE").getTime()) : null);	
					result.setLastAuthUser(rs.getString("LAST_AUTH_USER_ID") != null ? rs.getString("LAST_AUTH_USER_ID").toString() : null);					
					result.setLastUpdDate(rs.getDate("LAST_UPD_DATE") != null ? new Date(rs.getDate("LAST_UPD_DATE").getTime()) : null);
					result.setLastUpdUser(rs.getString("LAST_UPD_USER_ID") != null ? rs.getString("LAST_UPD_USER_ID").toString() : null);	
					result.setRecStatCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);	
					result.setRiskLevelCode(rs.getString("RISK_LEVEL_CODE") != null ? rs.getString("RISK_LEVEL_CODE").toString() : null);
					result.setLastIosRevDate(rs.getString("LAST_IOS_REV_DATE") != null ? rs.getString("LAST_IOS_REV_DATE").toString() : null);
					result.setAccountTypeRDIP(rs.getString("ACCT_TYPE") != null ? rs.getString("ACCT_TYPE").toString() : null);			
					result.setAccountType(rs.getString("ACCT_SRC_TYPE") != null ? rs.getString("ACCT_SRC_TYPE").toString() : null);	
				
					if(!StringUtils.isBlank(result.getCpfCnpjNbr())){
						result.setCpfCnpjNbrFormated(FormatUtils.formatterDoc(result.getCpfCnpjNbr()));
					}
					
					if(rs.getString("HAS_KE") != null && !rs.getString("HAS_KE").equals("")){
						if(rs.getString("HAS_KE").trim().equals("S")){
							result.setHasQuestionsKe(true);
						}else {
							result.setHasQuestionsKe(false);
						}
					}

					
	
					result.setTableOrigin(TableTypeEnum.EFFECTIVE);
					
					resultList.add(result);
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new NumberFormatException();
		} catch (ParseException e) {
			e.printStackTrace();
			throw new UnexpectedException(e.getErrorOffset(), C_ERROR_EXECUTING_STATEMENT, e);			
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return resultList;
	}

	public void insert(AcctCmplVO vo) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();
		
		
		try {
			connection = OracleODSDAOFactory.getConnection();
			
			sqlQuery.append("  INSERT INTO " + C_PL_SCHEMA + "TPL_PRVT_ACCT_CMPL ( " );
			sqlQuery.append("  ACCT_NBR            , ");//1
			sqlQuery.append("  ACCT_SRC_TYPE               , ");//2
			sqlQuery.append("  CPF_CNPJ_NBR               , ");//3
			
			sqlQuery.append("  CTRCT_STA_DATE            , ");//1
			sqlQuery.append("  ACCT_STA_DATE               , ");//2
			sqlQuery.append("  ACCT_END_DATE               , ");//3
			
			sqlQuery.append("  END_SRC_IND            , ");//1
			sqlQuery.append("  AGENT_IND               , ");//2
			sqlQuery.append("  CETIP_CODE               , ");//3
			
			sqlQuery.append("  SELIC_CODE            , ");//1
			sqlQuery.append("  BOVESP_CODE               , ");//2
			sqlQuery.append("  BVRJ_CODE               , ");//3
			
			sqlQuery.append("  BMF_CODE            , ");//1

			
			sqlQuery.append("  LAST_UPD_DATE                , ");//1
			sqlQuery.append("  LAST_UPD_USER_ID                , ");//2
			sqlQuery.append("  LAST_AUTH_DATE               , ");//8
			sqlQuery.append("  LAST_AUTH_USER_ID               , ");//9
			sqlQuery.append("  REC_STAT_CODE , "); //11  20
			sqlQuery.append("  RISK_LEVEL_CODE , "); 
			sqlQuery.append("  LAST_IOS_REV_DATE , ");
			sqlQuery.append("  ACCT_TYPE "); 
			sqlQuery.append( ") VALUES ( " );
			sqlQuery.append( " ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");

			
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			
			int count = 1;
			
			if (!StringUtils.isBlank(vo.getAcctNbr())) {
				preparedStatement.setString(count++, vo.getAcctNbr());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(vo.getAccountType())) {
				preparedStatement.setString(count++, vo.getAccountType());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(vo.getCpfCnpjNbr())) {
				preparedStatement.setString(count++, Long.valueOf(FormatUtils.unformatterDoc(vo.getCpfCnpjNbr())).toString());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(vo.getContractSignatureDate())) {
				
				Date contractSignatureDate =  FormatUtils.formatToDate(vo.getContractSignatureDate(),FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
				preparedStatement.setDate(count++, new java.sql.Date(contractSignatureDate.getTime()));
				
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getAccountOpenDate())) {				
				Date accountOpenDate =  FormatUtils.formatToDate(vo.getAccountOpenDate(),FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
				preparedStatement.setDate(count++, new java.sql.Date(accountOpenDate.getTime()));

			} else {
				preparedStatement.setString(count++, null);
			}
					
			if (!StringUtils.isBlank(vo.getAccountClosingDate())) {
				
				Date accountClosingDate =  FormatUtils.formatToDate(vo.getAccountClosingDate(),FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
				preparedStatement.setDate(count++, new java.sql.Date(accountClosingDate.getTime()));
				
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getAccountClosingReason())) {
				preparedStatement.setString(count++, vo.getAccountClosingReason());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.isHasAngent()) {
				preparedStatement.setString(count++,"S");
			} else {
				preparedStatement.setString(count++,"N");
			}
			
			if (!StringUtils.isBlank(vo.getCetipNumber())) {
				preparedStatement.setString(count++, vo.getCetipNumber());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			
			if (!StringUtils.isBlank(vo.getSelicNumber())) {
				preparedStatement.setString(count++, vo.getSelicNumber());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getBovespaNumber())) {
				preparedStatement.setString(count++, vo.getBovespaNumber());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getBvrjNumber())) {
				preparedStatement.setString(count++, vo.getBvrjNumber());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getBmfNumber())) {
				preparedStatement.setString(count++, vo.getBmfNumber());
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
			
			if (!StringUtils.isBlank(vo.getRiskLevelCode())) {
				preparedStatement.setString(count++, vo.getRiskLevelCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getLastIosRevDate())) {
				Date lastIosRevDate =  FormatUtils.formatToDate(vo.getLastIosRevDate(),FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
				preparedStatement.setDate(count++, new java.sql.Date(lastIosRevDate.getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getAccountTypeRDIP())) {
				preparedStatement.setString(count++, vo.getAccountTypeRDIP());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			
			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			preparedStatement.execute();
			

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} catch (ParseException a) {
			a.printStackTrace();
			throw new UnexpectedException(a.getErrorOffset(), C_ERROR_EXECUTING_STATEMENT, a);
			
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		
	}

	public AcctCmplVO getAccountComplement(String acctNbr, String cpfCnpjNbr, String accountType) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		AcctCmplVO result = null;

		try {
			connection = OracleODSDAOFactory.getConnection();			
			query.append(" SELECT TPL_PRVT_ACCT_CMPL.ACCT_NBR, "		
			+ "   TPL_PRVT_ACCT_CMPL.ACCT_SRC_TYPE, "	
			+ "   TPL_PRVT_ACCT_CMPL.CPF_CNPJ_NBR, "
			+ "   TPL_PRVT_ACCT_CMPL.CTRCT_STA_DATE, "
			+ "   TPL_PRVT_ACCT_CMPL.ACCT_STA_DATE, "
			+ "   TPL_PRVT_ACCT_CMPL.ACCT_END_DATE, "
			+ "   TPL_PRVT_ACCT_CMPL.END_SRC_IND, "
			+ "   TPL_PRVT_ACCT_CMPL.AGENT_IND, "
			+ "   TPL_PRVT_ACCT_CMPL.CETIP_CODE, "
			+ "   TPL_PRVT_ACCT_CMPL.SELIC_CODE, "
			+ "   TPL_PRVT_ACCT_CMPL.BOVESP_CODE, "
			+ "   TPL_PRVT_ACCT_CMPL.BVRJ_CODE, "
			+ "   TPL_PRVT_ACCT_CMPL.BMF_CODE, "	
			+ "   TPL_PRVT_ACCT_CMPL.LAST_AUTH_DATE, "
			+ "   TPL_PRVT_ACCT_CMPL.LAST_AUTH_USER_ID, "
			+ "   TPL_PRVT_ACCT_CMPL.LAST_UPD_DATE, "
			+ "   TPL_PRVT_ACCT_CMPL.LAST_UPD_USER_ID, "
			+ "   TPL_PRVT_ACCT_CMPL.REC_STAT_CODE, "
			+ "   TPL_PRVT_ACCT_CMPL.RISK_LEVEL_CODE, "
			+ "   TO_CHAR(TPL_PRVT_ACCT_CMPL.LAST_IOS_REV_DATE, 'DD/MM/YYYY') AS LAST_IOS_REV_DATE , "
			+ "   TPL_PRVT_ACCT_CMPL.ACCT_TYPE "
			+ " FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_CMPL "
			+ " WHERE 1=1 ");
			

			if(acctNbr!=null){
				if (!StringUtils.isBlank(acctNbr)) {
					query.append(" AND UPPER(TPL_PRVT_ACCT_CMPL.ACCT_NBR) = TRIM(?) ");
				}
			}
			
			if(cpfCnpjNbr!=null){
				if (!StringUtils.isBlank(cpfCnpjNbr)) {
					query.append(" AND UPPER(TPL_PRVT_ACCT_CMPL.CPF_CNPJ_NBR) = TRIM(?) ");
				}
			}
			
			if(accountType!=null){
				if (!StringUtils.isBlank(accountType)) {
					query.append(" AND UPPER(TPL_PRVT_ACCT_CMPL.ACCT_SRC_TYPE) = TRIM(?) ");
				}
			}
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;
			
			if(acctNbr!=null){
				
				if (!StringUtils.isBlank(acctNbr)) {
					preparedStatement.setString(count++, acctNbr.toUpperCase());
				}	
			}
			
			if(cpfCnpjNbr!=null){
				
				if (!StringUtils.isBlank(cpfCnpjNbr)) {
					preparedStatement.setString(count++, Long.valueOf(FormatUtils.unformatterDoc(cpfCnpjNbr)).toString());
				}	
			}
			
			
			if(accountType!=null){
				
				if (!StringUtils.isBlank(accountType)) {
					preparedStatement.setString(count++, accountType.toUpperCase());
				}	
			}

			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			
			if(rs!=null){
				
				while (rs.next()){
					
					result = new AcctCmplVO();
					result.setAcctNbr(rs.getString("ACCT_NBR") != null ? rs.getString("ACCT_NBR").toString() : null);	
					result.setAccountType(rs.getString("ACCT_SRC_TYPE") != null ? rs.getString("ACCT_SRC_TYPE").toString() : null);	
					result.setCpfCnpjNbr(rs.getString("CPF_CNPJ_NBR") != null ? rs.getString("CPF_CNPJ_NBR").toString() : null);
					
					if(!StringUtils.isBlank(result.getCpfCnpjNbr())){
						result.setCpfCnpjNbr(FormatUtils.formatterDoc(result.getCpfCnpjNbr()));
					}
					
					if(rs.getDate("CTRCT_STA_DATE") != null){						
						Date contractSignatureDate = new Date(rs.getDate("CTRCT_STA_DATE").getTime());
						result.setContractSignatureDate(FormatUtils.dateToString(contractSignatureDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
					
					
					if(rs.getDate("ACCT_STA_DATE") != null){						
						Date accountOpenDate = new Date(rs.getDate("ACCT_STA_DATE").getTime());
						result.setAccountOpenDate(FormatUtils.dateToString(accountOpenDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
					
					if(rs.getDate("ACCT_END_DATE") != null){						
						Date accountClosingDate = new Date(rs.getDate("ACCT_END_DATE").getTime());
						result.setAccountClosingDate(FormatUtils.dateToString(accountClosingDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
					
					
					result.setAccountClosingReason(rs.getString("END_SRC_IND") != null ? rs.getString("END_SRC_IND").toString() : null);
				
					
					if(rs.getString("AGENT_IND") != null && !rs.getString("AGENT_IND").equals("")){
						if(rs.getString("AGENT_IND").trim().equals("S")){
							result.setHasAngent(true);
						}else if (rs.getString("AGENT_IND").trim().equals("N")){
							result.setHasAngent(false);
						}
					}
					
					result.setCetipNumber(rs.getString("CETIP_CODE") != null ? rs.getString("CETIP_CODE").toString() : null);	
					result.setSelicNumber(rs.getString("SELIC_CODE") != null ? rs.getString("SELIC_CODE").toString() : null);	
					result.setBovespaNumber(rs.getString("BOVESP_CODE") != null ? rs.getString("BOVESP_CODE").toString() : null);	
					result.setBvrjNumber(rs.getString("BVRJ_CODE") != null ? rs.getString("BVRJ_CODE").toString() : null);	
					result.setBmfNumber(rs.getString("BMF_CODE") != null ? rs.getString("BMF_CODE").toString() : null);	
					
					
					if(rs.getDate("LAST_AUTH_DATE") != null){						
						Date lastAuthDate = new Date(rs.getDate("LAST_AUTH_DATE").getTime());
						result.setLastAuthDate(lastAuthDate);
					}
					
					
					result.setLastAuthUser(rs.getString("LAST_AUTH_USER_ID") != null ? rs.getString("LAST_AUTH_USER_ID").toString() : null);	
					
					if(rs.getDate("LAST_UPD_DATE") != null){						
						Date lastUpdateDate = new Date(rs.getDate("LAST_UPD_DATE").getTime());
						result.setLastUpdDate(lastUpdateDate);
					}
					
				
					result.setLastUpdUser(rs.getString("LAST_UPD_USER_ID") != null ? rs.getString("LAST_UPD_USER_ID").toString() : null);	
					result.setRecStatCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);
					result.setRiskLevelCode(rs.getString("RISK_LEVEL_CODE") != null ? rs.getString("RISK_LEVEL_CODE").toString() : null);
					result.setLastIosRevDate(rs.getString("LAST_IOS_REV_DATE") != null ? rs.getString("LAST_IOS_REV_DATE").toString() : null);
					result.setAccountTypeRDIP(rs.getString("ACCT_TYPE") != null ? rs.getString("ACCT_TYPE").toString() : null);

					
					result.setTableOrigin(TableTypeEnum.EFFECTIVE);
					
				
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} catch (ParseException a) {
			a.printStackTrace();
			throw new UnexpectedException(a.getErrorOffset(), C_ERROR_EXECUTING_STATEMENT, a);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return result;
	}
		
	public AcctCmplVO update(AcctCmplVO vo) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			sqlQuery.append("UPDATE  " + C_PL_SCHEMA + "TPL_PRVT_ACCT_CMPL ");
			sqlQuery.append("  SET ACCT_NBR         = ? , ");
			sqlQuery.append("  ACCT_SRC_TYPE                = ? , ");
			sqlQuery.append("  CPF_CNPJ_NBR                = ? , ");
			sqlQuery.append("  CTRCT_STA_DATE                   = ? , ");
			sqlQuery.append("  ACCT_STA_DATE                = ? , ");
			sqlQuery.append("  ACCT_END_DATE                = ? , ");
			sqlQuery.append("  END_SRC_IND          = ? , ");
			sqlQuery.append("  AGENT_IND              = ? , ");
			sqlQuery.append("  CETIP_CODE                 = ? , ");
			sqlQuery.append("  SELIC_CODE               = ? , ");
			sqlQuery.append("  BOVESP_CODE         = ? , ");
			sqlQuery.append("  BVRJ_CODE          = ? , ");
			sqlQuery.append("  BMF_CODE         = ? , ");
			sqlQuery.append("  LAST_UPD_DATE                = ? , ");
			sqlQuery.append("  LAST_UPD_USER_ID               = ? , ");
			sqlQuery.append("  LAST_AUTH_DATE                 = ? , ");
			sqlQuery.append("  LAST_AUTH_USER_ID                = ? , ");
			sqlQuery.append("  REC_STAT_CODE          = ?, ");
			sqlQuery.append("  RISK_LEVEL_CODE = ?, "); 
			sqlQuery.append("  LAST_IOS_REV_DATE = ?, ");
			sqlQuery.append("  ACCT_TYPE = ?"); 

			
			sqlQuery.append("WHERE ACCT_NBR         = ? ");
			sqlQuery.append("AND ACCT_SRC_TYPE         = ? ");
			sqlQuery.append("AND CPF_CNPJ_NBR         = ? ");


			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			
			int count = 1;

			if (!StringUtils.isBlank(vo.getAcctNbr())) {
				preparedStatement.setString(count++, vo.getAcctNbr());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(vo.getAccountType())) {
				preparedStatement.setString(count++, vo.getAccountType());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(vo.getCpfCnpjNbr())) {
				preparedStatement.setString(count++, Long.valueOf(FormatUtils.unformatterDoc(vo.getCpfCnpjNbr())).toString());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(vo.getContractSignatureDate())) {
				
				Date contractSignatureDate =  FormatUtils.formatToDate(vo.getContractSignatureDate(),FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
				preparedStatement.setDate(count++, new java.sql.Date(contractSignatureDate.getTime()));
				
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getAccountOpenDate())) {
				
				Date accountOpenDate =  FormatUtils.formatToDate(vo.getAccountOpenDate(),FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
				preparedStatement.setDate(count++, new java.sql.Date(accountOpenDate.getTime()));
				
			} else {
				preparedStatement.setString(count++, null);
			}
					
			if (!StringUtils.isBlank(vo.getAccountClosingDate())) {
				
				Date accountClosingDate =  FormatUtils.formatToDate(vo.getAccountClosingDate(),FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
				preparedStatement.setDate(count++, new java.sql.Date(accountClosingDate.getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getAccountClosingReason())) {
				preparedStatement.setString(count++, vo.getAccountClosingReason());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.isHasAngent()) {
				preparedStatement.setString(count++,"S");
			} else {
				preparedStatement.setString(count++,"N");
			}
			
			if (!StringUtils.isBlank(vo.getCetipNumber())) {
				preparedStatement.setString(count++, vo.getCetipNumber());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			
			if (!StringUtils.isBlank(vo.getSelicNumber())) {
				preparedStatement.setString(count++, vo.getSelicNumber());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getBovespaNumber())) {
				preparedStatement.setString(count++, vo.getBovespaNumber());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getBvrjNumber())) {
				preparedStatement.setString(count++, vo.getBvrjNumber());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getBmfNumber())) {
				preparedStatement.setString(count++, vo.getBmfNumber());
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

			if (!StringUtils.isBlank(vo.getRiskLevelCode())) {
				preparedStatement.setString(count++, vo.getRiskLevelCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getLastIosRevDate())) {
				Date lastIosRevDate =  FormatUtils.formatToDate(vo.getLastIosRevDate(),FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
				preparedStatement.setDate(count++, new java.sql.Date(lastIosRevDate.getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getAccountTypeRDIP())) {
				preparedStatement.setString(count++, vo.getAccountTypeRDIP());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getAcctNbr())) {
				preparedStatement.setString(count++, vo.getAcctNbr());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(vo.getAccountType())) {
				preparedStatement.setString(count++, vo.getAccountType());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(vo.getCpfCnpjNbr())) {
				preparedStatement.setString(count++, FormatUtils.unformatterDoc(vo.getCpfCnpjNbr()));
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			preparedStatement.execute();
			

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
			
		} catch (ParseException a) {
			a.printStackTrace();
			throw new UnexpectedException(a.getErrorOffset(), C_ERROR_EXECUTING_STATEMENT, a);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return vo;
	}

	public ArrayList<AcctCmplVO> listByFilterAuthorizedToQuestionKe(String acctNbr, String cpfCnpjNbr, 
			String emNbr, String name)  {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<AcctCmplVO> resultList = null;
				
		try {
			connection = OracleODSDAOFactory.getConnection();	
			query.append(" SELECT DISTINCT AUTH_PERSN.AUTH_PERSN_NAME, "
			+ " AUTH_PERSN.EM_NBR, "
			+ "	AUTH_PERSN.CPF_CNPJ_NBR, "
			+ " PRSN_ACCT.ACCT_NBR, "
			+ " ACCT_COMPL.RISK_LEVEL_CODE, "
			+ " AUTH_PERSN.REC_STAT_CODE, "
			+ " (CASE WHEN   (SELECT DISTINCT KE.ACCT_NBR FROM " + C_PL_SCHEMA + " TPL_PRVT_ACCT_KE KE " 
			+ " WHERE PRSN_ACCT.ACCT_NBR = KE.ACCT_NBR "
			+ " AND AUTH_PERSN.CPF_CNPJ_NBR = KE.CUST_CPF_CNPJ_NBR) "   
			+ " is not null THEN 'S' ELSE 'N' END ) AS HAS_KE " 
			+ " FROM " + C_PL_SCHEMA + " TPL_PRVT_AUTH_PERSN AUTH_PERSN " 
			+ " INNER JOIN  " + C_PL_SCHEMA + " TPL_PRVT_AUT_PRSN_ACCT PRSN_ACCT ON AUTH_PERSN.EM_NBR = PRSN_ACCT.EM_NBR " 
			+ " LEFT JOIN " + C_PL_SCHEMA + " TPL_PRVT_ACCT_CMPL ACCT_COMPL  ON PRSN_ACCT.ACCT_NBR = ACCT_COMPL.ACCT_NBR "
			+ " WHERE 1=1 ");
			
			

						
			if(acctNbr!=null){
				if (!StringUtils.isBlank(acctNbr)) {
					query.append(" AND UPPER(PRSN_ACCT.ACCT_NBR) LIKE TRIM(?) ");
				}
			}
			
			if(cpfCnpjNbr!=null){
				if (!StringUtils.isBlank(cpfCnpjNbr)) {
					query.append(" AND UPPER(AUTH_PERSN.CPF_CNPJ_NBR) LIKE TRIM(?) ");
				}
			}
			
			if(emNbr!=null){
				if (!StringUtils.isBlank(emNbr)) {
					query.append(" AND UPPER(AUTH_PERSN.EM_NBR) LIKE TRIM(?) ");
				}
			}
			
			if(name!=null){
				if (!StringUtils.isBlank(name)) {
					query.append(" AND UPPER(AUTH_PERSN.AUTH_PERSN_NAME) LIKE TRIM(?) ");
				}	
			}
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;
			
			if(acctNbr!=null){
				if (!StringUtils.isBlank(acctNbr)) {
					preparedStatement.setString(count++, "%" + acctNbr.toUpperCase() + "%");
				}				
			}
			
			if(cpfCnpjNbr!=null){
				if (!StringUtils.isBlank(cpfCnpjNbr)) {
					preparedStatement.setString(count++, "%" + Long.valueOf(FormatUtils.unformatterDoc(cpfCnpjNbr)) + "%");
				}				
			}
			
			if(emNbr!=null){
				if (!StringUtils.isBlank(emNbr)) {
					preparedStatement.setString(count++, "%" + emNbr.toUpperCase() + "%" );
				}				
			}
		
			if(name!=null){
				if (!StringUtils.isBlank(name)) {
					preparedStatement.setString(count++, "%" + name.toUpperCase() + "%");
				}				
			}
			
			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<AcctCmplVO>();
				while (rs.next()){
					AcctCmplVO result = new AcctCmplVO();					
					result.setAcctNbr(rs.getString("ACCT_NBR") != null ? rs.getString("ACCT_NBR").toString() : null);
					result.setCustomerName(rs.getString("AUTH_PERSN_NAME") != null ? rs.getString("AUTH_PERSN_NAME").toString() : null);	
					result.setEmNbr(rs.getString("EM_NBR") != null ? rs.getString("EM_NBR").toString() : null);	
					result.setCpfCnpjNbr(rs.getString("CPF_CNPJ_NBR") != null ? rs.getString("CPF_CNPJ_NBR").toString() : null);	
					result.setRecStatCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);	
					result.setRiskLevelCode(rs.getString("RISK_LEVEL_CODE") != null ? rs.getString("RISK_LEVEL_CODE").toString() : null);
				
					if(!StringUtils.isBlank(result.getCpfCnpjNbr())){
						result.setCpfCnpjNbrFormated(FormatUtils.formatterDoc(result.getCpfCnpjNbr()));
					}
					
					if(rs.getString("HAS_KE") != null && !rs.getString("HAS_KE").equals("")){
						if(rs.getString("HAS_KE").trim().equals("S")){
							result.setHasQuestionsKe(true);
						}else {
							result.setHasQuestionsKe(false);
						}
					}

					result.setTableOrigin(TableTypeEnum.EFFECTIVE);					
					resultList.add(result);
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new NumberFormatException(C_ERROR_EXECUTING_STATEMENT);			
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return resultList;
	}
	
	public AcctCmplVO getAccountComplementAuthorizedToQuestionKe(String acctNbr, String cpfCnpjNbr, String accountType) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		AcctCmplVO result = null;

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append(" SELECT AUTH_PERSN.AUTH_PERSN_NAME, "				
			+ " AUTH_PERSN.CPF_CNPJ_NBR, "
			+ " PRSN_ACCT.ACCT_NBR, "
			+ " TO_CHAR(PRSN_ACCT.AUTH_EFF_DATE, 'DD/MM/YYYY') AS AUTH_EFF_DATE, "
			+ " PRSN_ACCT.AUTH_IND, "
			+ " CUST.CUST_OFFICER_SOEID, "
			+ " ACCT_COMPL.LAST_AUTH_DATE, "
			+ " ACCT_COMPL.LAST_AUTH_USER_ID, "
			+ " ACCT_COMPL.LAST_UPD_DATE, "
			+ " ACCT_COMPL.LAST_UPD_USER_ID, " 
			+ " ACCT_COMPL.REC_STAT_CODE, "
			+ " ACCT_COMPL.RISK_LEVEL_CODE, "
			+ " TO_CHAR(ACCT_COMPL.LAST_IOS_REV_DATE, 'DD/MM/YYYY') AS LAST_IOS_REV_DATE "
			+ " FROM " + C_PL_SCHEMA + " TPL_PRVT_AUTH_PERSN AUTH_PERSN "
			+ " LEFT JOIN " + C_PL_SCHEMA + " TPL_PRVT_AUT_PRSN_ACCT PRSN_ACCT ON AUTH_PERSN.EM_NBR = PRSN_ACCT.EM_NBR " 
			+ " LEFT JOIN " + C_PL_SCHEMA + " TPL_PRVT_ACCT_CMPL ACCT_COMPL  ON PRSN_ACCT.ACCT_NBR = ACCT_COMPL.ACCT_NBR "
			+ " LEFT JOIN " + C_PL_SCHEMA + " TPL_PRVT_CUSTOMER CUST ON  ACCT_COMPL.CPF_CNPJ_NBR = CUST.CUST_CPF_CNPJ_NBR "
			+ " WHERE 1=1 ");
			
			if(acctNbr!=null){
				if (!StringUtils.isBlank(acctNbr)) {
					query.append(" AND UPPER(PRSN_ACCT.ACCT_NBR) = TRIM(?) ");
				}
			}
			
			if(cpfCnpjNbr!=null){
				if (!StringUtils.isBlank(cpfCnpjNbr)) {
					query.append(" AND UPPER(AUTH_PERSN.CPF_CNPJ_NBR) = TRIM(?) ");
				}
			}
			
			if(accountType!=null){
				if (!StringUtils.isBlank(accountType)) {
					query.append(" AND UPPER(ACCT_COMPL.ACCT_SRC_TYPE) = TRIM(?) ");
				}
			}
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;
			
			if(acctNbr!=null){
				
				if (!StringUtils.isBlank(acctNbr)) {
					preparedStatement.setString(count++, acctNbr.toUpperCase());
				}	
			}
			
			if(cpfCnpjNbr!=null){
				
				if (!StringUtils.isBlank(cpfCnpjNbr)) {
					preparedStatement.setString(count++, Long.valueOf(FormatUtils.unformatterDoc(cpfCnpjNbr)).toString());
				}	
			}
			
			
			if(accountType!=null){
				
				if (!StringUtils.isBlank(accountType)) {
					preparedStatement.setString(count++, accountType.toUpperCase());
				}	
			}

			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			
			if(rs!=null){
				result = new AcctCmplVO();
				while (rs.next()){
					
					
					result.setCustomerName(rs.getString("AUTH_PERSN_NAME") != null ? rs.getString("AUTH_PERSN_NAME").toString() : null);
					result.setCpfCnpjNbr(rs.getString("CPF_CNPJ_NBR") != null ? rs.getString("CPF_CNPJ_NBR").toString() : null);
					
					if(!StringUtils.isBlank(result.getCpfCnpjNbr())){
						result.setCpfCnpjNbrFormated(FormatUtils.formatterDoc(result.getCpfCnpjNbr()));
					}					
					result.setAcctNbr(rs.getString("ACCT_NBR") != null ? rs.getString("ACCT_NBR").toString() : null);
					
					if(rs.getString("AUTH_IND") !=null && rs.getString("AUTH_IND").equals("P")){
						result.setAgentInd(true);	
						result.setEffectiveDateAgent(rs.getString("AUTH_EFF_DATE") != null ? rs.getString("AUTH_EFF_DATE").toString() : null);						
					}else if(rs.getString("AUTH_IND") !=null && rs.getString("AUTH_IND").equals("A")){						
						result.setAuthPersonInd(true);	
						result.setEffectiveDateAuthPerson(rs.getString("AUTH_EFF_DATE") != null ? rs.getString("AUTH_EFF_DATE").toString() : null);
					}					
					result.setOfficeSoeId(rs.getString("CUST_OFFICER_SOEID") != null ? rs.getString("CUST_OFFICER_SOEID").toString() : null);
									
					if(rs.getDate("LAST_AUTH_DATE") != null){						
						Date lastAuthDate = new Date(rs.getDate("LAST_AUTH_DATE").getTime());
						result.setLastAuthDate(lastAuthDate);
					}					
					result.setLastAuthUser(rs.getString("LAST_AUTH_USER_ID") != null ? rs.getString("LAST_AUTH_USER_ID").toString() : null);	
					
					if(rs.getDate("LAST_UPD_DATE") != null){						
						Date lastUpdateDate = new Date(rs.getDate("LAST_UPD_DATE").getTime());
						result.setLastUpdDate(lastUpdateDate);
					}									
					result.setLastUpdUser(rs.getString("LAST_UPD_USER_ID") != null ? rs.getString("LAST_UPD_USER_ID").toString() : null);	
					result.setRecStatCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);
					result.setRiskLevelCode(rs.getString("RISK_LEVEL_CODE") != null ? rs.getString("RISK_LEVEL_CODE").toString() : null);		
					result.setLastIosRevDate(rs.getString("LAST_IOS_REV_DATE") != null ? rs.getString("LAST_IOS_REV_DATE").toString() : null);
					result.setTableOrigin(TableTypeEnum.EFFECTIVE);
				}
			}
			
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new NumberFormatException(C_ERROR_EXECUTING_STATEMENT);			
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return result;
	}	
}


