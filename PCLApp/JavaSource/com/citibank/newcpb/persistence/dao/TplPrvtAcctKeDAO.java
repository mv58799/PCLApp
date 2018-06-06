package com.citibank.newcpb.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.exception.UnexpectedException;
import com.citibank.newcpb.util.FormatUtils;
import com.citibank.newcpb.vo.QuestionsKeVO;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class TplPrvtAcctKeDAO extends BaseOracleDAO {
	
		
	public QuestionsKeVO insertAcctKe(QuestionsKeVO vo)  {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			sqlQuery.append("INSERT INTO " + C_PL_SCHEMA + "TPL_PRVT_ACCT_KE ( ");
			sqlQuery.append("    ACCT_NBR, ");
			sqlQuery.append("    CUST_CPF_CNPJ_NBR, ");			
			sqlQuery.append("    LAST_AUTH_DATE, ");
			sqlQuery.append("    LAST_AUTH_USER, ");
			sqlQuery.append("    LAST_UPD_DATE, ");
			sqlQuery.append("    LAST_UPD_USER, ");
			sqlQuery.append("    REC_STAT_CODE ");
			sqlQuery.append("  ) VALUES(?, ?, ?, ?, ?, ?, ?) ");
			
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));

			int count = 1;

			if (vo.getAcctNbr() != null) {
				preparedStatement.setLong(count++, Long.valueOf(vo.getAcctNbr()));
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			
			if (!StringUtils.isBlank(vo.getCpfCnpjNbr())) {
				preparedStatement.setString(count++, FormatUtils.unformatterDoc(vo.getCpfCnpjNbr()));
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
		} catch (ParseException e) {
			e.printStackTrace();
			throw new UnexpectedException(e.getErrorOffset(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return vo;
	}
	
	public QuestionsKeVO updateAcctKe(QuestionsKeVO vo)  {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			sqlQuery.append("UPDATE  " + C_PL_SCHEMA + "TPL_PRVT_ACCT_KE ");
			sqlQuery.append("  SET ");
			sqlQuery.append("  LAST_UPD_DATE                 = ? , ");
			sqlQuery.append("  LAST_UPD_USER                 = ? , ");
			sqlQuery.append("  LAST_AUTH_DATE                = ? , ");
			sqlQuery.append("  LAST_AUTH_USER                = ? ,");
			sqlQuery.append("  REC_STAT_CODE                   = ? ");			
			sqlQuery.append("  WHERE ACCT_NBR         = ? ");
			sqlQuery.append(" AND CUST_CPF_CNPJ_NBR   = ? ");	
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			
			int count = 1;
			
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
			
			if (!StringUtils.isBlank(vo.getAcctNbr())) {
				preparedStatement.setLong(count++, Long.valueOf(vo.getAcctNbr()));
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
		} catch (ParseException e) {
			e.printStackTrace();
			throw new UnexpectedException(e.getErrorOffset(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return vo;
	}
		
	public QuestionsKeVO getAcctKe(String acctNbr, String cpfCnpj)  {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		QuestionsKeVO result = null;

		try {
			connection = OracleODSDAOFactory.getConnection();		
			
			query.append(" SELECT ACCT_NBR, "	
			+ "   CUST_CPF_CNPJ_NBR, "
			+ "   LAST_AUTH_DATE, "
			+ "   LAST_AUTH_USER, "
			+ "   LAST_UPD_DATE, "
			+ "   LAST_UPD_USER, "
			+ "   REC_STAT_CODE "
			+ " FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_KE "
			+ " WHERE 1=1 ");			

			
			if (!StringUtils.isBlank(acctNbr)) {
				query.append(" AND UPPER(ACCT_NBR) = TRIM(?) ");
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(cpfCnpj)) {
				query.append(" AND UPPER(CUST_CPF_CNPJ_NBR) = TRIM(?) ");
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;
			
			if(acctNbr!=null){				
				if (!StringUtils.isBlank(acctNbr)) {
					preparedStatement.setString(count++, acctNbr.toUpperCase());
				}	
			}
			
			if(cpfCnpj!=null){				
				if (!StringUtils.isBlank(cpfCnpj)) {
					preparedStatement.setString(count++, FormatUtils.unformatterDoc(cpfCnpj));
				}	
			}
			
			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			
			if(rs!=null){
				
				while (rs.next()){
					
					result = new QuestionsKeVO();
					
					result.setAcctNbr(rs.getString("ACCT_NBR") != null ? rs.getString("ACCT_NBR").toString() : null);									
					//result.setAcctNbr(rs.getString("CUST_CPF_CNPJ_NBR") != null ? rs.getString("CUST_CPF_CNPJ_NBR").toString() : null);	
					if(rs.getDate("LAST_AUTH_DATE") != null){						
						Date lastAuthDate = new Date(rs.getDate("LAST_AUTH_DATE").getTime());
						result.setLastAuthDate(lastAuthDate);
					}
					
					result.setLastAuthUser(rs.getString("LAST_AUTH_USER") != null ? rs.getString("LAST_AUTH_USER").toString() : null);	
					
					if(rs.getDate("LAST_UPD_DATE") != null){						
						Date lastUpdateDate = new Date(rs.getDate("LAST_UPD_DATE").getTime());
						result.setLastUpdDate(lastUpdateDate);
					}
									
					result.setLastUpdUser(rs.getString("LAST_UPD_USER") != null ? rs.getString("LAST_UPD_USER").toString() : null);	
					result.setRecStatCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);
					
					result.setTableOrigin(TableTypeEnum.EFFECTIVE);
		
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new UnexpectedException(e.getErrorOffset(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return result;
	}
	
	public void deleteByAcctNbr(String acctNbr, String cpfCnpj)  {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			query.append(" DELETE FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_KE WHERE ACCT_NBR = ? AND CUST_CPF_CNPJ_NBR = ?  ");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));	

			if (!StringUtils.isBlank(acctNbr)) {
				preparedStatement.setString(1, acctNbr);
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
				
			if (!StringUtils.isBlank(cpfCnpj)) {
				preparedStatement.setString(2, FormatUtils.unformatterDoc(cpfCnpj));
			}else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			
			preparedStatement.replaceParametersInQuery(query.toString());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new UnexpectedException(e.getErrorOffset(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
	}		
}
