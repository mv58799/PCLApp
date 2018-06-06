package com.citibank.newcpb.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.exception.UnexpectedException;
import com.citibank.newcpb.vo.AcctEgVO;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class TplPrvtAcctEgDAO extends BaseOracleDAO {

	public AcctEgVO getAccountEg(String acctNbr, boolean onlyActive) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		AcctEgVO result = null;

		try {
			connection = OracleODSDAOFactory.getConnection();			
			query.append(" SELECT TPL_PRVT_ACCT_EG.ER_NBR, "
			+ "   TPL_PRVT_ACCT_EG.ACCT_NBR, "
			+ "   TPL_PRVT_ACCT_EG.EG_NBR, "
			+ "   TPL_PRVT_ACCT_EG.LAST_AUTH_DATE, "
			+ "   TPL_PRVT_ACCT_EG.LAST_AUTH_USER_ID, "
			+ "   TPL_PRVT_ACCT_EG.LAST_UPD_DATE, "
			+ "   TPL_PRVT_ACCT_EG.LAST_UPD_USER_ID, "
			+ "   TPL_PRVT_ACCT_EG.REC_STAT_CODE"
			+ " FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_EG "
			+ " WHERE UPPER(TPL_PRVT_ACCT_EG.ACCT_NBR) = TRIM(?) ");
			
			if (onlyActive) {
					query.append(" AND TPL_PRVT_ACCT_EG.REC_STAT_CODE <> 'I' ");
			}
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			if (!StringUtils.isBlank(acctNbr)) {
				preparedStatement.setString(1, acctNbr);
			}
				
			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				result = new AcctEgVO();
				while (rs.next()){					
					result.setErNbr(rs.getString("ER_NBR") != null ? rs.getString("ER_NBR").toString() : null);	
					result.setAcctNbr(rs.getString("ACCT_NBR") != null ? rs.getString("ACCT_NBR").toString() : null);
					result.setEgNbr(rs.getString("EG_NBR") != null ? rs.getString("EG_NBR").toString() : null);												
					result.setLastAuthDate(rs.getDate("LAST_AUTH_DATE") != null ? new Date(rs.getDate("LAST_AUTH_DATE").getTime()) : null);	
					result.setLastAuthUser(rs.getString("LAST_AUTH_USER_ID") != null ? rs.getString("LAST_AUTH_USER_ID").toString() : null);					
					result.setLastUpdDate(rs.getDate("LAST_UPD_DATE") != null ? new Date(rs.getDate("LAST_UPD_DATE").getTime()) : null);
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
	public ArrayList<AcctEgVO> listByFilter(AcctEgVO filter, Boolean notInative) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<AcctEgVO> resultList = null;

		try {
			connection = OracleODSDAOFactory.getConnection();			
			query.append(" SELECT TPL_PRVT_ACCT_EG.ER_NBR, "
			+ "   TPL_PRVT_ACCT_EG.ACCT_NBR, "
			+ "   TPL_PRVT_ACCT_EG.EG_NBR, "
			+ "   TPL_PRVT_ACCT_EG.LAST_AUTH_DATE, "
			+ "   TPL_PRVT_ACCT_EG.LAST_AUTH_USER_ID, "
			+ "   TPL_PRVT_ACCT_EG.LAST_UPD_DATE, "
			+ "   TPL_PRVT_ACCT_EG.LAST_UPD_USER_ID, "
			+ "   TPL_PRVT_ACCT_EG.REC_STAT_CODE"
			+ " FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_EG "
			+ " WHERE 1=1 ");
			
			
			if(filter!=null){
				if (!StringUtils.isBlank(filter.getErNbr())) {
					query.append(" AND UPPER(TPL_PRVT_ACCT_EG.ER_NBR) = TRIM(?) ");
				}
				
				if (!StringUtils.isBlank(filter.getAcctNbr())) {
					query.append(" AND UPPER(TPL_PRVT_ACCT_EG.ACCT_NBR) = TRIM(?) ");
				}
				
				if (!StringUtils.isBlank(filter.getEgNbr())) {
					query.append(" AND UPPER(TPL_PRVT_ACCT_EG.EG_NBR) = TRIM(?) ");
				}
				
				if (!StringUtils.isBlank(filter.getRecStatCode())) {
					query.append(" AND UPPER(TPL_PRVT_ACCT_EG.REC_STAT_CODE) = TRIM(?) ");
				}
				
				if (notInative!=null && notInative) {
					query.append(" AND TPL_PRVT_ACCT_EG.REC_STAT_CODE <> 'I' ");
				}
				
				
			}
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;
			
			if(filter!=null){
				if (!StringUtils.isBlank(filter.getErNbr())) {
					preparedStatement.setString(count++, filter.getErNbr().toUpperCase());
				}
				
				if (!StringUtils.isBlank(filter.getAcctNbr())) {
					preparedStatement.setString(count++, filter.getAcctNbr().toUpperCase());
				}
				
				if (!StringUtils.isBlank(filter.getEgNbr())) {
					preparedStatement.setString(count++, filter.getEgNbr().toUpperCase());
				}
				
				if (!StringUtils.isBlank(filter.getRecStatCode())) {
					preparedStatement.setString(count++, filter.getRecStatCode().toUpperCase());
				}
			}

			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<AcctEgVO>();
				while (rs.next()){
					AcctEgVO result = new AcctEgVO();
					result.setErNbr(rs.getString("ER_NBR") != null ? rs.getString("ER_NBR").toString() : null);	
					result.setAcctNbr(rs.getString("ACCT_NBR") != null ? rs.getString("ACCT_NBR").toString() : null);
					result.setEgNbr(rs.getString("EG_NBR") != null ? rs.getString("EG_NBR").toString() : null);												
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
	
	
	public ArrayList<AcctEgVO> listByFilterWithLike(AcctEgVO filter) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<AcctEgVO> resultList = null;

		try {
			connection = OracleODSDAOFactory.getConnection();			
			query.append(" SELECT ACCT_EG.ER_NBR, "
			+ "   ACCT_EG.ACCT_NBR, "
			+ "   ACCT_EG.EG_NBR, "
			+ "   ACCT_EG.LAST_AUTH_DATE, "
			+ "   ACCT_EG.LAST_AUTH_USER_ID, "
			+ "   ACCT_EG.LAST_UPD_DATE, "
			+ "   ACCT_EG.LAST_UPD_USER_ID, "
			+ "   ACCT_EG.REC_STAT_CODE, "
			+ "   CUST.CUST_FULL_NAME "
			+ "   FROM " + C_PL_SCHEMA +  "TPL_PRVT_ACCT_EG ACCT_EG " 
			+ "	  LEFT JOIN " + C_PL_SCHEMA + " TPL_PRVT_ACCT_CMPL ACCT_COMPL ON ACCT_EG.ACCT_NBR = ACCT_COMPL.ACCT_NBR "
		    + "   LEFT JOIN " + C_PL_SCHEMA + " TPL_PRVT_CUSTOMER CUST ON CUST.CUST_CPF_CNPJ_NBR = ACCT_COMPL.CPF_CNPJ_NBR "
		    + "   WHERE ACCT_EG.REC_STAT_CODE <> 'I' ");
			


			if(filter!=null){
				if (!StringUtils.isBlank(filter.getErNbr())) {
					query.append(" AND UPPER(ACCT_EG.ER_NBR) LIKE TRIM(?) ");
				}
				
				if (!StringUtils.isBlank(filter.getAcctNbr())) {
					query.append(" AND UPPER(ACCT_EG.ACCT_NBR) LIKE TRIM(?) ");
				}
				
				if (!StringUtils.isBlank(filter.getEgNbr())) {
					query.append(" AND UPPER(ACCT_EG.EG_NBR) LIKE TRIM(?) ");
				}
				
				query.append(" AND ACCT_EG.REC_STAT_CODE <> 'I' ");
				
			}
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;
			
			if(filter!=null){
				if (!StringUtils.isBlank(filter.getErNbr())) {
					preparedStatement.setString(count++, "%" + filter.getErNbr().toUpperCase() + "%");
				}
				
				if (!StringUtils.isBlank(filter.getAcctNbr())) {
					preparedStatement.setString(count++, "%" + filter.getAcctNbr().toUpperCase() + "%");
				}
				
				if (!StringUtils.isBlank(filter.getEgNbr())) {
					preparedStatement.setString(count++, "%" + filter.getEgNbr().toUpperCase()+ "%");
				}
				
				if (!StringUtils.isBlank(filter.getRecStatCode())) {
					preparedStatement.setString(count++, "%" + filter.getRecStatCode().toUpperCase()+ "%");
				}
			}

			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<AcctEgVO>();
				while (rs.next()){
					AcctEgVO result = new AcctEgVO();
					result.setErNbr(rs.getString("ER_NBR") != null ? rs.getString("ER_NBR").toString() : null);	
					result.setAcctNbr(rs.getString("ACCT_NBR") != null ? rs.getString("ACCT_NBR").toString() : null);
					result.setEgNbr(rs.getString("EG_NBR") != null ? rs.getString("EG_NBR").toString() : null);		
					result.setCustomerName(rs.getString("CUST_FULL_NAME") != null ? rs.getString("CUST_FULL_NAME").toString() : null);	
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
	
	
	public void insert(AcctEgVO vo) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();
		

		try {
			connection = OracleODSDAOFactory.getConnection();
			
			sqlQuery.append("  INSERT INTO " + C_PL_SCHEMA + "TPL_PRVT_ACCT_EG ( " );
			sqlQuery.append("  ER_NBR            , ");//1
			sqlQuery.append("  ACCT_NBR               , ");//2
			sqlQuery.append("  EG_NBR               , ");//3
			sqlQuery.append("  LAST_UPD_DATE                , ");//1
			sqlQuery.append("  LAST_UPD_USER_ID                , ");//2
			sqlQuery.append("  LAST_AUTH_DATE               , ");//8
			sqlQuery.append("  LAST_AUTH_USER_ID               , ");//9
			sqlQuery.append("  REC_STAT_CODE                  "); //11
			sqlQuery.append( ") VALUES ( " );
			sqlQuery.append( " ?, ?, ?, ?, ?, ?, ?, ? ) ");


			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			
			int count = 1;

			if (!StringUtils.isBlank(vo.getErNbr())) {
				preparedStatement.setString(count++, vo.getErNbr());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getAcctNbr())) {
				preparedStatement.setString(count++, vo.getAcctNbr());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getEgNbr())) {
				preparedStatement.setString(count++, vo.getEgNbr());
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
	
	
	public void delete(String acctNbr) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			query.append(" DELETE FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_EG WHERE ACCT_NBR = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));
			
			if (!StringUtils.isBlank(acctNbr)) {
				preparedStatement.setString(1, acctNbr);
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
