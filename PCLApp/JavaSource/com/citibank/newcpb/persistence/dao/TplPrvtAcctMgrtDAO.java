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
import com.citibank.newcpb.vo.AcctMgrtVO;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class TplPrvtAcctMgrtDAO extends BaseOracleDAO {
	
	public AcctMgrtVO getNameCpfCustomerCitiBankAccount(String accountNumber){
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer sqlQuery = new StringBuffer();
		AcctMgrtVO vo = null;
		
		try {
			connection = OracleODSDAOFactory.getConnection();
			
			
			sqlQuery.append("SELECT "); 
			sqlQuery.append("  TPL_CUSTOMER_PRVT.CUST_FULL_NAME_TEXT             , ");
			sqlQuery.append("  TPL_CUSTOMER_PRVT.CUST_CPF_CNPJ_NBR               , ");	
			sqlQuery.append("  TO3_PRODUCT_ACCOUNT.CUST_NBR                      , ");
			sqlQuery.append("  TO3_PRODUCT_ACCOUNT.RELTN_NBR                       ");
			sqlQuery.append(" FROM "  + C_PL_SCHEMA + " TPL_CUSTOMER_PRVT        , ");
			sqlQuery.append(C_O3_SCHEMA + " TO3_PRODUCT_ACCOUNT                  ");
			sqlQuery.append(" WHERE " + C_PL_SCHEMA +  " TPL_CUSTOMER_PRVT.CUST_NBR = " + C_O3_SCHEMA + " TO3_PRODUCT_ACCOUNT.CUST_NBR ");
			sqlQuery.append(" AND " + C_O3_SCHEMA +  " TO3_PRODUCT_ACCOUNT.CUR_ACCT_NBR = ? ");
			sqlQuery.append(" AND " + C_O3_SCHEMA +  " TO3_PRODUCT_ACCOUNT.PROD_CODE = '010' ");
			

			
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			 
		
			if (!StringUtils.isBlank(accountNumber)) {
				preparedStatement.setString(1, accountNumber);
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			rs = preparedStatement.executeQuery();
			
			
			if(rs!=null){
				
				while (rs.next()){
					vo = new AcctMgrtVO();
					vo.setAccountGrbName(rs.getString("CUST_FULL_NAME_TEXT") != null ? rs.getString("CUST_FULL_NAME_TEXT").toString() : null);
					vo.setAccountGrbCpfCnpjNumber(rs.getString("CUST_CPF_CNPJ_NBR") != null ? rs.getString("CUST_CPF_CNPJ_NBR").toString() : null);
					vo.setCustomerNumber(rs.getString("CUST_NBR") != null ? rs.getString("CUST_NBR").toString() : null);
					vo.setRelationNumber(rs.getString("RELTN_NBR") != null ? rs.getString("RELTN_NBR").toString() : null);
					vo.setTableOrigin(TableTypeEnum.EFFECTIVE);
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		
		
		return vo;
		
	}
	
	public AcctMgrtVO getNameCpfCustomerCustodiaAccount(String accountNumber){
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer sqlQuery = new StringBuffer();
		AcctMgrtVO vo = null;
		
		try {
			connection = OracleODSDAOFactory.getConnection();
			
			
			sqlQuery.append("SELECT "); 
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FULL_NAME             ,");	
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CPF_CNPJ_NBR         , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_EM_NBR                 ");
			
			sqlQuery.append(" FROM "  + C_PL_SCHEMA + " TPL_PRVT_CUSTOMER        , ");
			sqlQuery.append(C_PL_SCHEMA + " TPL_PRVT_ACCT_CMPL                  ");
			sqlQuery.append(" WHERE " + C_PL_SCHEMA + " TPL_PRVT_CUSTOMER. CUST_CPF_CNPJ_NBR = " + C_PL_SCHEMA + " TPL_PRVT_ACCT_CMPL.CPF_CNPJ_NBR ");
			sqlQuery.append(" AND " + C_PL_SCHEMA +  " TPL_PRVT_ACCT_CMPL.ACCT_NBR = ? ");
			
			
			
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			 
		
			if (!StringUtils.isBlank(accountNumber)) {
				preparedStatement.setString(1, accountNumber);
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			rs = preparedStatement.executeQuery();
			
			
			if(rs!=null){
				
				while (rs.next()){
					vo = new AcctMgrtVO();
					vo.setAccountCustodiaName(rs.getString("CUST_FULL_NAME") != null ? rs.getString("CUST_FULL_NAME").toString() : null);
					vo.setAccountCustodiaCpfCnpjNumber(rs.getString("CUST_CPF_CNPJ_NBR") != null ? rs.getString("CUST_CPF_CNPJ_NBR").toString() : null);
					vo.setEmNumber(rs.getString("CUST_EM_NBR") != null ? rs.getString("CUST_EM_NBR").toString() : null);
					
					vo.setTableOrigin(TableTypeEnum.EFFECTIVE);
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		
		
		return vo;
		
	}

	public AcctMgrtVO hasRegisterAccountMgrtCitiBank(String accountNumberCitiBank) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		AcctMgrtVO acctMgrtVO = null;

		try {
			connection = OracleODSDAOFactory.getConnection();
			
			query.append("SELECT CUR_ACCT_NBR, NEW_ACCT_NBR "
			+ "FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_MGRT WHERE CUR_ACCT_NBR = ? ");		
			
			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));
			
			int count = 1;

			if (!StringUtils.isBlank(accountNumberCitiBank)) {
				preparedStatement.setString(count++, accountNumberCitiBank);
			} else {
				preparedStatement.setString(count++, null);
			}
			

			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				
				while (rs.next()){
					acctMgrtVO = new  AcctMgrtVO();
					
					acctMgrtVO.setAccountGrbNumber(rs.getString("CUR_ACCT_NBR") != null ? rs.getString("CUR_ACCT_NBR").toString() : null);	
					acctMgrtVO.setAccountCustodiaNumber(rs.getString("NEW_ACCT_NBR") != null ? rs.getString("NEW_ACCT_NBR").toString() : null);
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return acctMgrtVO;
	}
	
	public AcctMgrtVO hasRegisterAccountMgrtCustodia(String accountNumberCustodia) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		AcctMgrtVO acctMgrtVO = null;

		try {
			connection = OracleODSDAOFactory.getConnection();
			
			query.append("SELECT CUR_ACCT_NBR, NEW_ACCT_NBR "
			+ "FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_MGRT WHERE NEW_ACCT_NBR  = ? ");		
			
			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));
			
			int count = 1;

			if (!StringUtils.isBlank(accountNumberCustodia)) {
				preparedStatement.setString(count++, accountNumberCustodia);
			} else {
				preparedStatement.setString(count++, null);
			}
			

			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				
				while (rs.next()){
					acctMgrtVO = new  AcctMgrtVO();
					
					acctMgrtVO.setAccountGrbNumber(rs.getString("CUR_ACCT_NBR") != null ? rs.getString("CUR_ACCT_NBR").toString() : null);	
					acctMgrtVO.setAccountCustodiaNumber(rs.getString("NEW_ACCT_NBR") != null ? rs.getString("NEW_ACCT_NBR").toString() : null);
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return acctMgrtVO;
	}
	
	public AcctMgrtVO hasRegisterAccountMgrtCustodiaDifferentAccountCiti(String accountNumberCustodia, String accountNumberCitibank) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		AcctMgrtVO acctMgrtVO = null;

		try {
			connection = OracleODSDAOFactory.getConnection();
			
			query.append("SELECT CUR_ACCT_NBR, NEW_ACCT_NBR "
			+ "FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_MGRT WHERE NEW_ACCT_NBR  = ? AND CUR_ACCT_NBR <> ?");		
			
			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));
			
			int count = 1;

			if (!StringUtils.isBlank(accountNumberCustodia)) {
				preparedStatement.setString(count++, accountNumberCustodia);
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(accountNumberCitibank)) {
				preparedStatement.setString(count++, accountNumberCitibank);
			} else {
				preparedStatement.setString(count++, null);
			}
			

			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				
				while (rs.next()){
					acctMgrtVO = new  AcctMgrtVO();
					
					acctMgrtVO.setAccountGrbNumber(rs.getString("CUR_ACCT_NBR") != null ? rs.getString("CUR_ACCT_NBR").toString() : null);	
					acctMgrtVO.setAccountCustodiaNumber(rs.getString("NEW_ACCT_NBR") != null ? rs.getString("NEW_ACCT_NBR").toString() : null);
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return acctMgrtVO;
	}
	
	
	
	
	
	public void insert(AcctMgrtVO vo) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();
		
		
		
		try {
			connection = OracleODSDAOFactory.getConnection();
			
			sqlQuery.append("  INSERT INTO " + C_PL_SCHEMA + "TPL_PRVT_ACCT_MGRT ( " );
			sqlQuery.append("  CUR_ACCT_NBR            , ");
			sqlQuery.append("  CUST_CPF_CNPJ_NBR               , ");//2
			sqlQuery.append("  NEW_ACCT_NBR               , ");//
			sqlQuery.append("  MIGRATION_DATE            , ");//1
			sqlQuery.append("  CUST_NBR               , ");//2
			sqlQuery.append("  RELTN_NBR               , ");//3
			sqlQuery.append("  EM_NBR            , ");//1	
			sqlQuery.append("  LAST_UPD_DATE                , ");//1
			sqlQuery.append("  LAST_UPD_USER_ID                , ");//2
			sqlQuery.append("  LAST_AUTH_DATE               , ");//8
			sqlQuery.append("  LAST_AUTH_USER_ID               , ");//9
			sqlQuery.append("  REC_STAT_CODE                  "); //11  20
			sqlQuery.append( ") VALUES ( " );
			sqlQuery.append( " ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ? ) ");

			
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			
			int count = 1;
			
			if (!StringUtils.isBlank(vo.getAccountGrbNumber())) {
				preparedStatement.setString(count++, vo.getAccountGrbNumber());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(vo.getAccountCustodiaCpfCnpjNumber())) {
				preparedStatement.setString(count++, vo.getAccountCustodiaCpfCnpjNumber());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(vo.getAccountCustodiaNumber())) {
				preparedStatement.setString(count++, vo.getAccountCustodiaNumber());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}


			if (!StringUtils.isBlank(vo.getMigrationDateString())) {
				
				Date migrationDateString =  FormatUtils.formatToDate(vo.getMigrationDateString(),FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
				preparedStatement.setDate(count++, new java.sql.Date(migrationDateString.getTime()));
				
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getCustomerNumber())) {
				preparedStatement.setString(count++, vo.getCustomerNumber());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getRelationNumber())) {
				preparedStatement.setString(count++, vo.getRelationNumber());
			} else {
				preparedStatement.setString(count++, null);
			}

			if (!StringUtils.isBlank(vo.getEmNumber())) {
				preparedStatement.setString(count++, vo.getEmNumber());
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
	
	public ArrayList<AcctMgrtVO> listByFilter(String accountNumberGrb, String accountCustodiaNumber, String cpfCnpjNumber,
			String customerNumber, String emNumber) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		ArrayList<AcctMgrtVO> resultList = null;
	

		try {
			connection = OracleODSDAOFactory.getConnection();			
			query.append(" SELECT TPL_PRVT_ACCT_MGRT.CUR_ACCT_NBR, "	
			+ "   TPL_PRVT_ACCT_MGRT.CUST_CPF_CNPJ_NBR, "
			+ "   TPL_PRVT_ACCT_MGRT.NEW_ACCT_NBR, "	
			+ "   TPL_PRVT_ACCT_MGRT.MIGRATION_DATE, "	
			+ "   TPL_PRVT_ACCT_MGRT.CUST_NBR, "
			+ "   TPL_PRVT_ACCT_MGRT.RELTN_NBR, "
			+ "   TPL_PRVT_ACCT_MGRT.EM_NBR, "
			+ "   TPL_PRVT_CUSTOMER.CUST_FULL_NAME, "
			+ "   TPL_PRVT_ACCT_MGRT.LAST_AUTH_DATE, "
			+ "   TPL_PRVT_ACCT_MGRT.LAST_AUTH_USER_ID, "
			+ "   TPL_PRVT_ACCT_MGRT.LAST_UPD_DATE, "
			+ "   TPL_PRVT_ACCT_MGRT.LAST_UPD_USER_ID, "
			+ "   TPL_PRVT_ACCT_MGRT.REC_STAT_CODE"
			+ " FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_MGRT "
			+ " LEFT JOIN " + C_PL_SCHEMA + " TPL_PRVT_CUSTOMER ON TPL_PRVT_ACCT_MGRT.CUST_CPF_CNPJ_NBR = TPL_PRVT_CUSTOMER.CUST_CPF_CNPJ_NBR "
			+ " WHERE 1=1 ");
			
						
			if (!StringUtils.isBlank(accountNumberGrb)) {
				query.append(" AND UPPER(TPL_PRVT_ACCT_MGRT.CUR_ACCT_NBR) LIKE TRIM(?) ");
			}
			
			if (!StringUtils.isBlank(accountCustodiaNumber)) {
				query.append(" AND UPPER(TPL_PRVT_ACCT_MGRT.NEW_ACCT_NBR) LIKE TRIM(?) ");
			}
			
			if (!StringUtils.isBlank(cpfCnpjNumber)) {
				query.append(" AND UPPER(TPL_PRVT_ACCT_MGRT.CUST_CPF_CNPJ_NBR) LIKE TRIM(?) ");
			}
			
			if (!StringUtils.isBlank(customerNumber)) {
				query.append(" AND UPPER(TPL_PRVT_ACCT_MGRT.CUST_NBR) LIKE TRIM(?) ");
			}
			
			if (!StringUtils.isBlank(emNumber)) {
				query.append(" AND UPPER(TPL_PRVT_ACCT_MGRT.EM_NBR) LIKE TRIM(?) ");
			}
			
			
			//query.append(" AND TPL_PRVT_ACCT_MGRT.REC_STAT_CODE = 'A' ");
			query.append(" AND TPL_PRVT_ACCT_MGRT.MIGRATION_DATE is not null ");
			
			
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;
			
			
			if (!StringUtils.isBlank(accountNumberGrb)) {
				preparedStatement.setString(count++, "%" + accountNumberGrb.toUpperCase() + "%");
			}
			
			if (!StringUtils.isBlank(accountCustodiaNumber)) {
				preparedStatement.setString(count++, "%" + accountCustodiaNumber.toUpperCase() + "%");
			}
			
			if (!StringUtils.isBlank(cpfCnpjNumber)) {
				preparedStatement.setString(count++, "%" + FormatUtils.unformatterDoc(cpfCnpjNumber.toUpperCase()) + "%");
			}
			
			if (!StringUtils.isBlank(customerNumber)) {
				preparedStatement.setString(count++, "%" + customerNumber.toUpperCase() + "%");
			}
			
			if (!StringUtils.isBlank(emNumber)) {
				preparedStatement.setString(count++, "%" + emNumber.toUpperCase()+ "%");
			}
				
			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<AcctMgrtVO>();
				while (rs.next()){
					
					AcctMgrtVO result = new AcctMgrtVO();
					

					
					result.setAccountGrbNumber(rs.getString("CUR_ACCT_NBR") != null ? rs.getString("CUR_ACCT_NBR").toString() : null);	
					result.setAccountCustodiaNumber(rs.getString("NEW_ACCT_NBR") != null ? rs.getString("NEW_ACCT_NBR").toString() : null);
					result.setAccountCustodiaCpfCnpjNumber(rs.getString("CUST_CPF_CNPJ_NBR") != null ? rs.getString("CUST_CPF_CNPJ_NBR").toString() : null);
					
					if(rs.getDate("MIGRATION_DATE") != null){						
						Date migrationDate = new Date(rs.getDate("MIGRATION_DATE").getTime());
						result.setMigrationDate(migrationDate);
						result.setMigrationDateString(FormatUtils.dateToString(migrationDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));	
					}
					
					result.setCustomerNumber(rs.getString("CUST_NBR") != null ? rs.getString("CUST_NBR").toString() : null);
					result.setRelationNumber(rs.getString("RELTN_NBR") != null ? rs.getString("RELTN_NBR").toString() : null);
					result.setEmNumber(rs.getString("EM_NBR") != null ? rs.getString("EM_NBR").toString() : null);
					result.setAccountGrbName(rs.getString("CUST_FULL_NAME") != null ? rs.getString("CUST_FULL_NAME").toString() : null);

					
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
					
					result.setTableOrigin(TableTypeEnum.MOVEMENT);
					
					resultList.add(result);
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
		return resultList;
	}
	
	public AcctMgrtVO getAccountMigrate(String accountGrbNumber, String accountCustodiaNumber, String accountCustodiaCpfCnpjNumber) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		AcctMgrtVO result = null;
	

		try {
			connection = OracleODSDAOFactory.getConnection();			
			query.append(" SELECT TPL_PRVT_ACCT_MGRT.CUR_ACCT_NBR, "	
			+ "   TPL_PRVT_ACCT_MGRT.CUST_CPF_CNPJ_NBR, "
			+ "   TPL_PRVT_ACCT_MGRT.NEW_ACCT_NBR, "	
			+ "   TPL_PRVT_ACCT_MGRT.MIGRATION_DATE, "	
			+ "   TPL_PRVT_ACCT_MGRT.CUST_NBR, "
			+ "   TPL_PRVT_ACCT_MGRT.RELTN_NBR, "
			+ "   TPL_PRVT_ACCT_MGRT.EM_NBR, "
			+ "   TPL_PRVT_ACCT_MGRT.LAST_AUTH_DATE, "
			+ "   TPL_PRVT_ACCT_MGRT.LAST_AUTH_USER_ID, "
			+ "   TPL_PRVT_ACCT_MGRT.LAST_UPD_DATE, "
			+ "   TPL_PRVT_ACCT_MGRT.LAST_UPD_USER_ID, "
			+ "   TPL_PRVT_ACCT_MGRT.REC_STAT_CODE"
			+ " FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_MGRT "
			+ " WHERE 1=1 ");
			
						
			if (!StringUtils.isBlank(accountGrbNumber)) {
				query.append(" AND UPPER(TPL_PRVT_ACCT_MGRT.CUR_ACCT_NBR) = TRIM(?) ");
			}
			
			if (!StringUtils.isBlank(accountCustodiaCpfCnpjNumber)) {
				query.append(" AND UPPER(TPL_PRVT_ACCT_MGRT.CUST_CPF_CNPJ_NBR) = TRIM(?) ");
			}
			
			if (!StringUtils.isBlank(accountCustodiaNumber)) {
				query.append(" AND UPPER(TPL_PRVT_ACCT_MGRT.NEW_ACCT_NBR) = TRIM(?) ");
			}
			
			query.append(" AND TPL_PRVT_ACCT_MGRT.MIGRATION_DATE is not null ");
			
			
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;
			
			
			if (!StringUtils.isBlank(accountGrbNumber)) {
				preparedStatement.setString(count++,  accountGrbNumber.toUpperCase());
			}
			
			if (!StringUtils.isBlank(accountCustodiaCpfCnpjNumber)) {
				preparedStatement.setString(count++, accountCustodiaCpfCnpjNumber.toUpperCase());
			}
			
			if (!StringUtils.isBlank(accountCustodiaNumber)) {
				preparedStatement.setString(count++, accountCustodiaNumber.toUpperCase());
			}
			

			

				
			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				while (rs.next()){
					
					result = new AcctMgrtVO();
					

					
					result.setAccountGrbNumber(rs.getString("CUR_ACCT_NBR") != null ? rs.getString("CUR_ACCT_NBR").toString() : null);	
					result.setAccountCustodiaNumber(rs.getString("NEW_ACCT_NBR") != null ? rs.getString("NEW_ACCT_NBR").toString() : null);
					result.setAccountCustodiaCpfCnpjNumber(rs.getString("CUST_CPF_CNPJ_NBR") != null ? rs.getString("CUST_CPF_CNPJ_NBR").toString() : null);
					
					if(rs.getDate("MIGRATION_DATE") != null){						
						Date migrationDate = new Date(rs.getDate("MIGRATION_DATE").getTime());
						result.setMigrationDate(migrationDate);
						result.setMigrationDateString(FormatUtils.dateToString(migrationDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));	
					}
					
					result.setCustomerNumber(rs.getString("CUST_NBR") != null ? rs.getString("CUST_NBR").toString() : null);
					result.setRelationNumber(rs.getString("RELTN_NBR") != null ? rs.getString("RELTN_NBR").toString() : null);
					result.setEmNumber(rs.getString("EM_NBR") != null ? rs.getString("EM_NBR").toString() : null);
					
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
	
	public void delete(String accountGrbNumber, String accountCustodiaNumber, String accountCustodiaCpfCnpjNumber) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			query.append(" DELETE FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_MGRT"
					+ " WHERE CUR_ACCT_NBR = ?  AND NEW_ACCT_NBR = ? AND CUST_CPF_CNPJ_NBR = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));

			if (!StringUtils.isBlank(accountGrbNumber)) {
				preparedStatement.setString(1, accountGrbNumber);
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(accountCustodiaNumber)) {
				preparedStatement.setString(2, accountCustodiaNumber);
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(accountCustodiaCpfCnpjNumber)) {
				preparedStatement.setString(3, accountCustodiaCpfCnpjNumber);
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
	
	public AcctMgrtVO update(AcctMgrtVO vo) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			sqlQuery.append("UPDATE  " + C_PL_SCHEMA + "TPL_PRVT_ACCT_MGRT ");
			sqlQuery.append("  SET CUR_ACCT_NBR            = ? , ");
			sqlQuery.append("  CUST_CPF_CNPJ_NBR           = ? , ");//2
			sqlQuery.append("  NEW_ACCT_NBR                = ? , ");//
			sqlQuery.append("  MIGRATION_DATE              = ? , ");//1
			sqlQuery.append("  CUST_NBR                    = ? , ");//2
			sqlQuery.append("  RELTN_NBR                   = ? , ");//3
			sqlQuery.append("  EM_NBR                      = ?  , ");//1	
			sqlQuery.append("  LAST_UPD_DATE               = ? , ");
			sqlQuery.append("  LAST_UPD_USER_ID                 = ? , ");			
			sqlQuery.append("  LAST_AUTH_DATE                = ? , ");
			sqlQuery.append("  LAST_AUTH_USER_ID                = ? , ");
			sqlQuery.append("  REC_STAT_CODE                 = ?   "); //11  20
			sqlQuery.append("WHERE CUR_ACCT_NBR         = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			
			int count = 1;

			if (!StringUtils.isBlank(vo.getAccountGrbNumber())) {
				preparedStatement.setString(count++, vo.getAccountGrbNumber());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(vo.getAccountCustodiaCpfCnpjNumber())) {
				preparedStatement.setString(count++, vo.getAccountCustodiaCpfCnpjNumber());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(vo.getAccountCustodiaNumber())) {
				preparedStatement.setString(count++, vo.getAccountCustodiaNumber());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}


			if (!StringUtils.isBlank(vo.getMigrationDateString())) {
				
				Date migrationDateString =  FormatUtils.formatToDate(vo.getMigrationDateString(),FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
				preparedStatement.setDate(count++, new java.sql.Date(migrationDateString.getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getCustomerNumber())) {
				preparedStatement.setString(count++, vo.getCustomerNumber());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getRelationNumber())) {
				preparedStatement.setString(count++, vo.getRelationNumber());
			} else {
				preparedStatement.setString(count++, null);
			}

			if (!StringUtils.isBlank(vo.getEmNumber())) {
				preparedStatement.setString(count++, vo.getEmNumber());
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
			
			if (!StringUtils.isBlank(vo.getAccountGrbNumber())) {
				preparedStatement.setString(count++, vo.getAccountGrbNumber());
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


}
