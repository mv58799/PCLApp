package com.citibank.newcpb.persistence.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import oracle.sql.BLOB;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.util.FormatUtils;
import com.citibank.newcpb.vo.KeCustFileVO;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class TplPrvtKeCustFileDAO extends BaseOracleDAO {
	
	public void deleteByAcctNbr(String acctNbr, String cpfCnpj)  {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			query.append(" DELETE FROM " + C_PL_SCHEMA + "TPL_PRVT_KE_CUST_FILE WHERE ACCT_NBR = ? AND CUST_CPF_CNPJ_NBR = ? ");

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
		} catch (ParseException e) {
			e.printStackTrace();
			throw new UnexpectedException(e.getErrorOffset(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
	}

	public KeCustFileVO insert(KeCustFileVO vo)  {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			sqlQuery.append("INSERT INTO " + C_PL_SCHEMA + "TPL_PRVT_KE_CUST_FILE ( ");
			sqlQuery.append("    ACCT_NBR, ");
			sqlQuery.append("    CUST_CPF_CNPJ_NBR, ");	
			sqlQuery.append("    FILE_SEQ, ");
			sqlQuery.append("    FILE_NAME, ");
			sqlQuery.append("    CREATE_DATE, ");
			sqlQuery.append("    CREATE_USER, ");
			sqlQuery.append("    REC_STAT_CODE, ");
			sqlQuery.append("    QUEST_CUST_FILE ");			
			sqlQuery.append("  ) VALUES(?, ?, ?, ?, ?, ?, ?, empty_blob()) ");
			
	
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
			
			if (vo.getFileSeq() != null) {
				preparedStatement.setLong(count++, vo.getFileSeq());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(vo.getFileName())) {
				preparedStatement.setString(count++, vo.getFileName());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getCreateDate() != null) {
				preparedStatement.setTimestamp(count++, new java.sql.Timestamp(vo.getCreateDate().getTime())); 

			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getCreateUser())) {
				preparedStatement.setString(count++, vo.getCreateUser());
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

	public ArrayList<KeCustFileVO> list(String acctNbr, String cpfCnpj) {
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer sqlQuery = new StringBuffer();
		ArrayList<KeCustFileVO> resultList = null;
		
		try {
			connection = OracleODSDAOFactory.getConnection();
			
			sqlQuery.append(" SELECT  ");
			sqlQuery.append("    ACCT_NBR, ");
			sqlQuery.append("    CUST_CPF_CNPJ_NBR, ");	
			sqlQuery.append("    FILE_SEQ, ");
			sqlQuery.append("    QUEST_CUST_FILE, ");
			sqlQuery.append("    FILE_NAME, ");
			sqlQuery.append("    CREATE_DATE, ");
			sqlQuery.append("    CREATE_USER, ");
			sqlQuery.append("    REC_STAT_CODE ");
			sqlQuery.append(" FROM " + C_PL_SCHEMA + "TPL_PRVT_KE_CUST_FILE WHERE ACCT_NBR = ? "
					+ "AND CUST_CPF_CNPJ_NBR = ? ORDER BY FILE_SEQ ");
		
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			 
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

			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<KeCustFileVO>();
				while (rs.next()){
					
					KeCustFileVO result = new KeCustFileVO();
					result.setAcctNbr(rs.getString("ACCT_NBR"));	
					result.setCpfCnpjNbr(rs.getString("CUST_CPF_CNPJ_NBR"));
					result.setFileSeq(rs.getInt("FILE_SEQ"));
					if(rs.getBlob("QUEST_CUST_FILE")!=null){

						Blob blob = rs.getBlob("QUEST_CUST_FILE");		
						InputStream inputStream = blob.getBinaryStream();					
						byte[] byteFile = IOUtils.toByteArray(inputStream);	
						result.setFile(byteFile);
					}
					result.setFileName(rs.getString("FILE_NAME"));					
					if(rs.getDate("CREATE_DATE") != null){						
						Date CREATE_DATE = new Date(rs.getDate("CREATE_DATE").getTime());
						result.setCreateDate(CREATE_DATE);
					}					
					result.setCreateUser(rs.getString("CREATE_USER"));
					result.setRecStatCode(rs.getString("REC_STAT_CODE"));
					result.setTableOrigin(TableTypeEnum.EFFECTIVE);
					resultList.add(result);		
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
			throw new UnexpectedException(e.getErrorOffset(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		
		return resultList;
				
	}
	
	public ArrayList<KeCustFileVO> getInsertBlob(String acctNbr, String cpfCnpj, Integer fileSeq, byte[] file) {
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer sqlQuery = new StringBuffer();
		ArrayList<KeCustFileVO> resultList = null;
		
		try {
			connection = OracleODSDAOFactory.getConnection();
			
			sqlQuery.append(" SELECT  ");
			sqlQuery.append("    QUEST_CUST_FILE ");
			sqlQuery.append(" FROM " + C_PL_SCHEMA + "TPL_PRVT_KE_CUST_FILE  WHERE ACCT_NBR = ? AND CUST_CPF_CNPJ_NBR = ?  AND FILE_SEQ = ? for update");
		
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			 
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
			
			if (fileSeq!=null) {
				preparedStatement.setLong(3, fileSeq);
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){

				while (rs.next()){				
					if(rs.getBlob("QUEST_CUST_FILE")!=null){						
						Blob  blob = rs.getBlob("QUEST_CUST_FILE");  					
						byte[] bbuf = new byte[1024];  		
						ByteArrayInputStream bin = new ByteArrayInputStream(file);
						OutputStream bout = ((BLOB) blob).getBinaryOutputStream(); 
						int bytesRead = 0;  
						while ((bytesRead = bin.read(bbuf)) != -1) {  
						    bout.write(bbuf, 0, bytesRead);  
						}  
						bin.close();  
						bout.close(); 							
					}	
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
			throw new UnexpectedException(e.getErrorOffset(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		
		return resultList;
				
	}
}
