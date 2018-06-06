package com.citibank.newcpb.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.exception.UnexpectedException;
import com.citibank.newcpb.util.FormatUtils;
import com.citibank.newcpb.vo.QuestionsKeAnswVO;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class TplPrvtAcctKeAnswMovDAO extends BaseOracleDAO {
		
	public QuestionsKeAnswVO insertAcctQuestionsKeAnswer(QuestionsKeAnswVO vo,String questTypeCode, String answer ) throws ParseException {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			sqlQuery.append("INSERT INTO " + C_PL_SCHEMA + "TPL_PRVT_ACCT_KE_ANSW_MOV ( ");
			sqlQuery.append("    ACCT_NBR, ");
			sqlQuery.append("    CUST_CPF_CNPJ_NBR, ");	
			sqlQuery.append("    PROD_KE_CODE, ");
			sqlQuery.append("    PROD_FAML_KE_CODE, ");
			sqlQuery.append("    QUEST_TYPE_CODE, ");
			sqlQuery.append("    ASNW_CODE, ");
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
			
			if (vo.getProdKeCode() != null) {
				preparedStatement.setString(count++, vo.getProdKeCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (vo.getProdFamlKeCode() != null) {
				preparedStatement.setString(count++, vo.getProdFamlKeCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (questTypeCode != null) {
				preparedStatement.setString(count++, questTypeCode);
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (answer != null) {
				preparedStatement.setString(count++, answer);
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
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
	
	public QuestionsKeAnswVO updateAcctQuestionsKeAnswer(QuestionsKeAnswVO vo,String questTypeCode, String answer) throws ParseException {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			sqlQuery.append("UPDATE  " + C_PL_SCHEMA + "TPL_PRVT_ACCT_KE_ANSW_MOV ");
			sqlQuery.append("  SET ASNW_CODE                = ? , ");			
			sqlQuery.append("  WHERE ACCT_NBR         = ? ");
			sqlQuery.append("  AND PROD_KE_CODE         = ? ");
			sqlQuery.append("  AND PROD_FAML_KE_CODE         = ? ");
			sqlQuery.append("  AND QUEST_TYPE_CODE         = ? ");
			sqlQuery.append("  AND CUST_CPF_CNPJ_NBR = ? ");	
			
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			
			int count = 1;
			
			if (answer != null) {
				preparedStatement.setString(count++, answer);
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (!StringUtils.isBlank(vo.getRecStatCode())) {
				preparedStatement.setString(count++, vo.getRecStatCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getAcctNbr() != null) {
				preparedStatement.setLong(count++, Long.valueOf(vo.getAcctNbr()));
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (vo.getProdKeCode() != null) {
				preparedStatement.setString(count++, vo.getProdKeCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (vo.getProdFamlKeCode() != null) {
				preparedStatement.setString(count++, vo.getProdFamlKeCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (questTypeCode != null) {
				preparedStatement.setString(count++, questTypeCode);
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
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return vo;
	}

	public ArrayList<QuestionsKeAnswVO> listAcctQuestionsKeAnswer(String acctNbr, String cpfCnpj ) throws ParseException {
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer sqlQuery = new StringBuffer();
		ArrayList<QuestionsKeAnswVO> voList = null;
		
		try {
			connection = OracleODSDAOFactory.getConnection();
			
			sqlQuery.append("SELECT ");		
			sqlQuery.append("    ACCT_NBR, ");
			sqlQuery.append("    CUST_CPF_CNPJ_NBR, ");
			sqlQuery.append("    PROD_KE_CODE, ");
			sqlQuery.append("    PROD_FAML_KE_CODE, ");
			sqlQuery.append("    QUEST_TYPE_CODE, ");
			sqlQuery.append("    ASNW_CODE, ");
			sqlQuery.append("    REC_STAT_CODE ");
			sqlQuery.append(" FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_KE_ANSW_MOV ");
			sqlQuery.append("WHERE ACCT_NBR = ?   ");
			sqlQuery.append("AND CUST_CPF_CNPJ_NBR = ? ");
			sqlQuery.append("AND REC_STAT_CODE <> 'I' ORDER BY PROD_FAML_KE_CODE, PROD.PROD_KE_CODE ");
			
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			 
		
			if (!StringUtils.isBlank(acctNbr)) {
				preparedStatement.setString(1, acctNbr.toUpperCase());
			}else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(cpfCnpj)) {
				preparedStatement.setString(2, FormatUtils.unformatterDoc(cpfCnpj));
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			rs = preparedStatement.executeQuery();
			
			
			if(rs!=null){
				voList = new ArrayList<QuestionsKeAnswVO>();
				while (rs.next()){
					QuestionsKeAnswVO result = new QuestionsKeAnswVO();
					
					result.setAcctNbr(rs.getString("ACCT_NBR") != null ? rs.getString("ACCT_NBR").toString() : null);
					result.setCpfCnpjNbr(rs.getString("CUST_CPF_CNPJ_NBR") != null ? rs.getString("CUST_CPF_CNPJ_NBR").toString() : null);
					result.setProdKeCode(rs.getString("PROD_KE_CODE") != null ? rs.getString("PROD_KE_CODE").toString() : null);
					result.setProdFamlKeCode(rs.getString("PROD_FAML_KE_CODE") != null ? rs.getString("PROD_FAML_KE_CODE").toString() : null);
					result.setQuestTypeCode(rs.getString("QUEST_TYPE_CODE") != null ? rs.getString("QUEST_TYPE_CODE").toString() : null);	
					
					result.setQuestTypeCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);	
					
					if(!StringUtils.isBlank(result.getQuestTypeCode())){
						
						if(result.getQuestTypeCode().trim().equals("PROD")){
							result.setProdAnswer(rs.getString("ASNW_CODE") != null ? rs.getString("ASNW_CODE").toString() : null);
						}else if(result.getQuestTypeCode().trim().equals("FMA")){
							result.setFmaAnswer(rs.getString("ASNW_CODE") != null ? rs.getString("ASNW_CODE").toString() : null);
						}else if(result.getQuestTypeCode().trim().equals("VMA")){
							result.setVmaAnswer(rs.getString("ASNW_CODE") != null ? rs.getString("ASNW_CODE").toString() : null);
						}						
					}					
					voList.add(result);
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}		
		return voList;		
	}
			
	public void deleteByAcctNbr(String acctNbr, String cpfCnpj) throws ParseException {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			query.append(" DELETE FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_KE_ANSW_MOV WHERE ACCT_NBR = ?  AND CUST_CPF_CNPJ_NBR = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));	

			if (!StringUtils.isBlank(acctNbr)) {
				preparedStatement.setString(1, acctNbr);
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(cpfCnpj)) {
				preparedStatement.setString(2, FormatUtils.unformatterDoc(cpfCnpj));
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
