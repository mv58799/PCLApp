package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplRiskFamilyProdPlayerHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplRiskFamilyProdPlayerHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class OracleTplRiskFamilyProdPlayerHistDAO extends BaseOracleTplRiskFamilyProdPlayerDAO implements TplRiskFamilyProdPlayerHistDAO{

	/*
	 * Nome da tabela
	 */
	private static final String C_TPL_RISK_FAMILY_PROD_PLYR_HIST = C_PL_SCHEMA
	                                                  + "TPL_RISK_FAMILY_PRD_PLYR_HIST";
	
	public TplRiskFamilyProdPlayerHistEntity delete(TplRiskFamilyProdPlayerHistEntity tplRiskFamilyProdPlayerHistEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean exists(TplRiskFamilyProdPlayerHistEntity tplRiskFamilyProdPlayerHistEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	public TplRiskFamilyProdPlayerHistEntity insert(TplRiskFamilyProdPlayerHistEntity tplRiskFamilyProdPlayerHistEntity) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			sqlQuery.append("INSERT INTO " + C_TPL_RISK_FAMILY_PROD_PLYR_HIST + " ( ");
			sqlQuery.append(" PLYR_CNPJ_NBR,  ");
			sqlQuery.append(" PROD_FAML_CODE,  ");
			sqlQuery.append(" PROD_INVST_RISK_CODE,  ");
			sqlQuery.append(" LAST_UPD_USER_ID,  ");
			sqlQuery.append(" LAST_UPD_DATE,  ");
			sqlQuery.append(" PROD_CODE,  ");
			sqlQuery.append(" SYS_CODE,  ");
			sqlQuery.append(" SYS_SEG_CODE,  ");
			
			sqlQuery.append(" REC_STAT_CODE,   ");
			sqlQuery.append(" LAST_AUTH_USER_ID,  ");
			sqlQuery.append(" LAST_AUTH_DATE,  ");
			
			sqlQuery.append(" RISK_FAML_PROD_PLYR_REF_DATE  "); 
			
			sqlQuery.append(" ) ");
			sqlQuery.append(" VALUES ( ?,?,?,?,?,?,?,?,?,?,?,? ) ");

			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));

			TplRiskFamilyProdPlayerHistEntityVO tplRiskFamilyProdPlayerHistEntityVO = (TplRiskFamilyProdPlayerHistEntityVO) tplRiskFamilyProdPlayerHistEntity.getData();

			int count = 1;

			if (tplRiskFamilyProdPlayerHistEntityVO.getPlyrCnpjNbr() != null && !tplRiskFamilyProdPlayerHistEntityVO.getPlyrCnpjNbr().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerHistEntityVO.getPlyrCnpjNbr());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerHistEntityVO.getProdFamlCode() != null && !tplRiskFamilyProdPlayerHistEntityVO.getProdFamlCode().equals("")) {
				preparedStatement.setLong(count++, tplRiskFamilyProdPlayerHistEntityVO.getProdFamlCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerHistEntityVO.getProdInvstRiskCode() != null && !tplRiskFamilyProdPlayerHistEntityVO.getProdInvstRiskCode().equals("")) {
				preparedStatement.setLong(count++, tplRiskFamilyProdPlayerHistEntityVO.getProdInvstRiskCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerHistEntityVO.getLastUpdUserId() != null && !tplRiskFamilyProdPlayerHistEntityVO.getLastUpdUserId().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerHistEntityVO.getLastUpdUserId());
			} else {
				preparedStatement.setString(count++, null);
			}

			if (tplRiskFamilyProdPlayerHistEntityVO.getLastUpdDate() != null && !tplRiskFamilyProdPlayerHistEntityVO.getLastUpdDate().equals("")) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplRiskFamilyProdPlayerHistEntityVO.getLastUpdDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			if (tplRiskFamilyProdPlayerHistEntityVO.getProdCode() != null && !tplRiskFamilyProdPlayerHistEntityVO.getProdCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerHistEntityVO.getProdCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerHistEntityVO.getSysCode() != null && !tplRiskFamilyProdPlayerHistEntityVO.getSysCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerHistEntityVO.getSysCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerHistEntityVO.getSysSegCode() != null && !tplRiskFamilyProdPlayerHistEntityVO.getSysSegCode().equals("")) {
				preparedStatement.setLong(count++, tplRiskFamilyProdPlayerHistEntityVO.getSysSegCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			
			if (tplRiskFamilyProdPlayerHistEntityVO.getRecStatCode() != null && !tplRiskFamilyProdPlayerHistEntityVO.getRecStatCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerHistEntityVO.getRecStatCode());
			} else {
				preparedStatement.setString(count++, null);
			}			
			
			if (tplRiskFamilyProdPlayerHistEntityVO.getLastAuthUserId() != null && !tplRiskFamilyProdPlayerHistEntityVO.getLastAuthUserId().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerHistEntityVO.getLastAuthUserId());
			} else {
				preparedStatement.setString(count++, null);
			}	
			
			if (tplRiskFamilyProdPlayerHistEntityVO.getLastAuthDate() != null && !tplRiskFamilyProdPlayerHistEntityVO.getLastAuthDate().equals("")) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplRiskFamilyProdPlayerHistEntityVO.getLastAuthDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (tplRiskFamilyProdPlayerHistEntityVO.getRiskFamlProdPlyrRefDate() != null && !tplRiskFamilyProdPlayerHistEntityVO.getRiskFamlProdPlyrRefDate().equals("")) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplRiskFamilyProdPlayerHistEntityVO.getRiskFamlProdPlyrRefDate().getTime()));
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}			
			

			preparedStatement.execute();
			preparedStatement.replaceParametersInQuery(sqlQuery.toString());

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		}
		 finally {
				closeStatement(preparedStatement);
				closeConnection(connection);
			}
		return tplRiskFamilyProdPlayerHistEntity;
	}

	public List<TplRiskFamilyProdPlayerHistEntity> list(String prodCode) {
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		StringBuffer sqlQuery = new StringBuffer();

		List<TplRiskFamilyProdPlayerHistEntity> listTplRiskFamilyProdPlayerHistEntity = new ArrayList<TplRiskFamilyProdPlayerHistEntity>();
		
		try {
			connection = OracleODSDAOFactory.getConnection();

			sqlQuery.append(" SELECT   ");
			sqlQuery.append(" PLYR_CNPJ_NBR,  ");
			sqlQuery.append(" PROD_FAML_CODE,  ");
			sqlQuery.append(" PROD_INVST_RISK_CODE,  ");
			sqlQuery.append(" LAST_UPD_USER_ID,  ");
			sqlQuery.append(" LAST_UPD_DATE,  ");
			sqlQuery.append(" PROD_CODE,  ");
			sqlQuery.append(" SYS_CODE,  ");
			sqlQuery.append(" SYS_SEG_CODE,  ");

			sqlQuery.append(" REC_STAT_CODE,  ");
			sqlQuery.append(" LAST_AUTH_USER_ID,  ");
			sqlQuery.append(" LAST_AUTH_DATE,  ");
			sqlQuery.append(" RISK_FAML_PROD_PLYR_REF_DATE  ");
			 
			
			sqlQuery.append(" FROM " + C_TPL_RISK_FAMILY_PROD_PLYR_HIST + "  ");
			sqlQuery.append(" WHERE PROD_CODE = ?  ");

			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));			
			
			int count = 1;

			preparedStatement.setString(count++, prodCode);

			resultSet = preparedStatement.executeQuery();
			preparedStatement.replaceParametersInQuery(sqlQuery.toString());

			while (resultSet.next()) {
				
				TplRiskFamilyProdPlayerHistEntity tplRiskFamilyProdPlayerHistEntity = new TplRiskFamilyProdPlayerHistEntity();
				TplRiskFamilyProdPlayerHistEntityVO tplRiskFamilyProdPlayerHistEntityVO = (TplRiskFamilyProdPlayerHistEntityVO) tplRiskFamilyProdPlayerHistEntity.getData();
				
				tplRiskFamilyProdPlayerHistEntityVO.setPlyrCnpjNbr(resultSet.getString(1));
				tplRiskFamilyProdPlayerHistEntityVO.setProdFamlCode(resultSet.getString(2) == null ? null : new BigInteger( resultSet.getString(2) ));
				tplRiskFamilyProdPlayerHistEntityVO.setProdInvstRiskCode(resultSet.getString(3) == null ? null : new BigInteger( resultSet.getString(3) ));
				tplRiskFamilyProdPlayerHistEntityVO.setLastUpdUserId(resultSet.getString(4));
				tplRiskFamilyProdPlayerHistEntityVO.setLastUpdDate(resultSet.getTimestamp(5));
				tplRiskFamilyProdPlayerHistEntityVO.setProdCode(resultSet.getString(6));
				tplRiskFamilyProdPlayerHistEntityVO.setSysCode(resultSet.getString(7));
				tplRiskFamilyProdPlayerHistEntityVO.setSysSegCode(resultSet.getString(8) == null ? null : new BigInteger( resultSet.getString(8) ));
	    		
				tplRiskFamilyProdPlayerHistEntityVO.setRecStatCode(resultSet.getString(9));
				tplRiskFamilyProdPlayerHistEntityVO.setLastAuthUserId(resultSet.getString(10));
				tplRiskFamilyProdPlayerHistEntityVO.setLastAuthDate(resultSet.getTimestamp(11));
				tplRiskFamilyProdPlayerHistEntityVO.setLastAuthDate(resultSet.getTimestamp(12));
	    		
	    		listTplRiskFamilyProdPlayerHistEntity.add(tplRiskFamilyProdPlayerHistEntity);
			}

			resultSet.close();

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}

		return listTplRiskFamilyProdPlayerHistEntity;
	}

	public TplRiskFamilyProdPlayerHistEntity update(TplRiskFamilyProdPlayerHistEntity tplRiskFamilyProdPlayerHistEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
