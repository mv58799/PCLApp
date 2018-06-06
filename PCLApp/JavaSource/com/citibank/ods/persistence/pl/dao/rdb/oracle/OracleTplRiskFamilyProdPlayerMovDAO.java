package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplProductMovEntity;
import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplProductMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRiskFamilyProdPlayerMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplRiskFamilyProdPlayerMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class OracleTplRiskFamilyProdPlayerMovDAO extends BaseOracleTplRiskFamilyProdPlayerDAO implements TplRiskFamilyProdPlayerMovDAO{

	/*
	 * Nome da tabela
	 */
	private static final String C_TPL_RISK_FAMILY_PROD_PLYR_MOV = C_PL_SCHEMA
	                                                  + "TPL_RISK_FAMILY_PRD_PLYR_MOV";
	
	public TplRiskFamilyProdPlayerMovEntity delete(TplRiskFamilyProdPlayerMovEntity tplRiskFamilyProdPlayerMovEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean exists(TplRiskFamilyProdPlayerMovEntity tplRiskFamilyProdPlayerMovEntity) {
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuffer query = new StringBuffer();
		
		try {
			connection = OracleODSDAOFactory.getConnection();

			query.append("SELECT COUNT(*)");
			query.append(" FROM ");
			query.append(C_TPL_RISK_FAMILY_PROD_PLYR_MOV);
			query.append(" WHERE ");
			query.append("    PROD_CODE  = ?  ");
			query.append("    AND SYS_CODE = ?   ");
			query.append("    AND SYS_SEG_CODE = ?  ");
			query.append("    AND PLYR_CNPJ_NBR = ?  ");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));

			TplRiskFamilyProdPlayerMovEntityVO tplRiskFamilyProdPlayerMovEntityVO = (TplRiskFamilyProdPlayerMovEntityVO) tplRiskFamilyProdPlayerMovEntity.getData();

			int count = 1;

			if (tplRiskFamilyProdPlayerMovEntityVO.getProdCode() != null && !tplRiskFamilyProdPlayerMovEntityVO.getProdCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerMovEntityVO.getProdCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getSysCode() != null && !tplRiskFamilyProdPlayerMovEntityVO.getSysCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerMovEntityVO.getSysCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getSysSegCode() != null) {
				preparedStatement.setLong(count++, tplRiskFamilyProdPlayerMovEntityVO.getSysSegCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getPlyrCnpjNbr() != null && !tplRiskFamilyProdPlayerMovEntityVO.getPlyrCnpjNbr().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerMovEntityVO.getPlyrCnpjNbr());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			resultSet = preparedStatement.executeQuery();
			preparedStatement.replaceParametersInQuery(query.toString());

			if (resultSet.next()) {
				if (resultSet.getInt(1) != 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, e);
		}
		 finally {
				closeStatement(preparedStatement);
				closeConnection(connection);
			}		
	}

	public TplRiskFamilyProdPlayerMovEntity insert(TplRiskFamilyProdPlayerMovEntity tplRiskFamilyProdPlayerMovEntity) {
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			sqlQuery.append("INSERT INTO " + C_TPL_RISK_FAMILY_PROD_PLYR_MOV + " ( ");
			sqlQuery.append(" PLYR_CNPJ_NBR,  ");
			sqlQuery.append(" PROD_FAML_CODE,  ");
			sqlQuery.append(" PROD_INVST_RISK_CODE,  ");
			sqlQuery.append(" LAST_UPD_USER_ID,  ");
			sqlQuery.append(" LAST_UPD_DATE,  ");
			sqlQuery.append(" PROD_CODE,  ");
			sqlQuery.append(" SYS_CODE,  ");
			sqlQuery.append(" SYS_SEG_CODE,  ");
			sqlQuery.append(" OPERN_CODE  ");
			sqlQuery.append(" ) ");
			sqlQuery.append(" VALUES ( ?,?,?,?,?,?,?,?,? ) ");

			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));

			TplRiskFamilyProdPlayerMovEntityVO tplRiskFamilyProdPlayerMovEntityVO = (TplRiskFamilyProdPlayerMovEntityVO) tplRiskFamilyProdPlayerMovEntity.getData();

			int count = 1;

			if (tplRiskFamilyProdPlayerMovEntityVO.getPlyrCnpjNbr() != null && !tplRiskFamilyProdPlayerMovEntityVO.getPlyrCnpjNbr().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerMovEntityVO.getPlyrCnpjNbr());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getProdFamlCode() != null && !tplRiskFamilyProdPlayerMovEntityVO.getProdFamlCode().equals("")) {
				preparedStatement.setLong(count++, tplRiskFamilyProdPlayerMovEntityVO.getProdFamlCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getProdInvstRiskCode() != null && !tplRiskFamilyProdPlayerMovEntityVO.getProdInvstRiskCode().equals("")) {
				preparedStatement.setLong(count++, tplRiskFamilyProdPlayerMovEntityVO.getProdInvstRiskCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getLastUpdUserId() != null && !tplRiskFamilyProdPlayerMovEntityVO.getLastUpdUserId().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerMovEntityVO.getLastUpdUserId());
			} else {
				preparedStatement.setString(count++, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getLastUpdDate() != null && !tplRiskFamilyProdPlayerMovEntityVO.getLastUpdDate().equals("")) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplRiskFamilyProdPlayerMovEntityVO.getLastUpdDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getProdCode() != null && !tplRiskFamilyProdPlayerMovEntityVO.getProdCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerMovEntityVO.getProdCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getSysCode() != null && !tplRiskFamilyProdPlayerMovEntityVO.getSysCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerMovEntityVO.getSysCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getSysSegCode() != null && !tplRiskFamilyProdPlayerMovEntityVO.getSysSegCode().equals("")) {
				preparedStatement.setLong(count++, tplRiskFamilyProdPlayerMovEntityVO.getSysSegCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getOpernCode() != null && !tplRiskFamilyProdPlayerMovEntityVO.getOpernCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerMovEntityVO.getOpernCode());
			} else {
				preparedStatement.setString(count++, null);
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
		return tplRiskFamilyProdPlayerMovEntity;
	}

	/**
	 * 
	 */
	public List<TplRiskFamilyProdPlayerMovEntity> list(TplProductMovEntity tplProductMovEntity) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		StringBuffer sqlQuery = new StringBuffer();

		List<TplRiskFamilyProdPlayerMovEntity> listTplRiskFamilyProdPlayerMovEntity = new ArrayList<TplRiskFamilyProdPlayerMovEntity>();
		
		TplProductMovEntityVO tplProductMovEntityVO = (TplProductMovEntityVO)tplProductMovEntity.getData();
		
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
			sqlQuery.append(" OPERN_CODE  ");
			sqlQuery.append(" FROM " + C_TPL_RISK_FAMILY_PROD_PLYR_MOV + "  ");
			sqlQuery.append(" WHERE ");
			sqlQuery.append("    PROD_CODE  = ?  ");
			sqlQuery.append("    AND SYS_CODE = ?   ");
			sqlQuery.append("    AND SYS_SEG_CODE = ?  ");
			
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			
			int count = 1;

			if (tplProductMovEntityVO.getProdCode() != null && !tplProductMovEntityVO.getProdCode().equals("")) {
				preparedStatement.setString(count++, tplProductMovEntityVO.getProdCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductMovEntityVO.getSysCode() != null && !tplProductMovEntityVO.getSysCode().equals("")) {
				preparedStatement.setString(count++, tplProductMovEntityVO.getSysCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductMovEntityVO.getSysSegCode() != null) {
				preparedStatement.setLong(count++, tplProductMovEntityVO.getSysSegCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			resultSet = preparedStatement.executeQuery();
			preparedStatement.replaceParametersInQuery(sqlQuery.toString());

			while (resultSet.next()) {
				
				TplRiskFamilyProdPlayerMovEntity tplRiskFamilyProdPlayerMovEntity = new TplRiskFamilyProdPlayerMovEntity();
				TplRiskFamilyProdPlayerMovEntityVO tplRiskFamilyProdPlayerMovEntityVO = (TplRiskFamilyProdPlayerMovEntityVO) tplRiskFamilyProdPlayerMovEntity.getData();
				
				tplRiskFamilyProdPlayerMovEntityVO.setPlyrCnpjNbr(resultSet.getString(1));
				tplRiskFamilyProdPlayerMovEntityVO.setProdFamlCode(resultSet.getString(2) == null ? null : new BigInteger( resultSet.getString(2) ));
				tplRiskFamilyProdPlayerMovEntityVO.setProdInvstRiskCode(resultSet.getString(3) == null ? null : new BigInteger( resultSet.getString(3) ));
	    		tplRiskFamilyProdPlayerMovEntityVO.setLastUpdUserId(resultSet.getString(4));
	    		tplRiskFamilyProdPlayerMovEntityVO.setLastUpdDate(resultSet.getTimestamp(5));
	    		tplRiskFamilyProdPlayerMovEntityVO.setProdCode(resultSet.getString(6));
	    		tplRiskFamilyProdPlayerMovEntityVO.setSysCode(resultSet.getString(7));
	    		tplRiskFamilyProdPlayerMovEntityVO.setSysSegCode(resultSet.getString(8) == null ? null : new BigInteger( resultSet.getString(8) ));
	    		tplRiskFamilyProdPlayerMovEntityVO.setOpernCode(resultSet.getString(9));
	    		
				listTplRiskFamilyProdPlayerMovEntity.add(tplRiskFamilyProdPlayerMovEntity);
			}

			resultSet.close();

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}

		return listTplRiskFamilyProdPlayerMovEntity;
	}

	/**
	 * 
	 */
	public TplRiskFamilyProdPlayerMovEntity update(TplRiskFamilyProdPlayerMovEntity tplRiskFamilyProdPlayerMovEntity) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			sqlQuery.append("UPDATE " + C_TPL_RISK_FAMILY_PROD_PLYR_MOV + " SET ");
			
			sqlQuery.append(" PLYR_CNPJ_NBR = ?,  ");
			sqlQuery.append(" PROD_FAML_CODE = ?,  ");
			sqlQuery.append(" PROD_INVST_RISK_CODE = ?,  ");
			sqlQuery.append(" LAST_UPD_USER_ID = ?,  ");
			sqlQuery.append(" LAST_UPD_DATE = ?,  ");
			sqlQuery.append(" PROD_CODE = ?,  ");
			sqlQuery.append(" SYS_CODE = ?,  ");
			sqlQuery.append(" SYS_SEG_CODE = ?,  ");
			sqlQuery.append(" OPERN_CODE  = ? ");		
			
			sqlQuery.append(" WHERE ");
			sqlQuery.append("    PROD_CODE  = ?  ");
			sqlQuery.append("    AND SYS_CODE = ?   ");
			sqlQuery.append("    AND SYS_SEG_CODE = ?  ");
			sqlQuery.append("    AND PLYR_CNPJ_NBR = ?  ");

			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			TplRiskFamilyProdPlayerMovEntityVO tplRiskFamilyProdPlayerMovEntityVO = (TplRiskFamilyProdPlayerMovEntityVO) tplRiskFamilyProdPlayerMovEntity.getData();
			
			int count = 1;

			if (tplRiskFamilyProdPlayerMovEntityVO.getPlyrCnpjNbr() != null && !tplRiskFamilyProdPlayerMovEntityVO.getPlyrCnpjNbr().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerMovEntityVO.getPlyrCnpjNbr());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getProdFamlCode() != null && !tplRiskFamilyProdPlayerMovEntityVO.getProdFamlCode().equals("")) {
				preparedStatement.setLong(count++, tplRiskFamilyProdPlayerMovEntityVO.getProdFamlCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getProdInvstRiskCode() != null && !tplRiskFamilyProdPlayerMovEntityVO.getProdInvstRiskCode().equals("")) {
				preparedStatement.setLong(count++, tplRiskFamilyProdPlayerMovEntityVO.getProdInvstRiskCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getLastUpdUserId() != null && !tplRiskFamilyProdPlayerMovEntityVO.getLastUpdUserId().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerMovEntityVO.getLastUpdUserId());
			} else {
				preparedStatement.setString(count++, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getLastUpdDate() != null && !tplRiskFamilyProdPlayerMovEntityVO.getLastUpdDate().equals("")) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplRiskFamilyProdPlayerMovEntityVO.getLastUpdDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getProdCode() != null && !tplRiskFamilyProdPlayerMovEntityVO.getProdCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerMovEntityVO.getProdCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getSysCode() != null && !tplRiskFamilyProdPlayerMovEntityVO.getSysCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerMovEntityVO.getSysCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getSysSegCode() != null && !tplRiskFamilyProdPlayerMovEntityVO.getSysSegCode().equals("")) {
				preparedStatement.setLong(count++, tplRiskFamilyProdPlayerMovEntityVO.getSysSegCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getOpernCode()!= null && !tplRiskFamilyProdPlayerMovEntityVO.getOpernCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerMovEntityVO.getOpernCode());
			} else {
				preparedStatement.setString(count++, null);
			}			
			
			//where			
			if (tplRiskFamilyProdPlayerMovEntityVO.getProdCode() != null && !tplRiskFamilyProdPlayerMovEntityVO.getProdCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerMovEntityVO.getProdCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getSysCode() != null && !tplRiskFamilyProdPlayerMovEntityVO.getSysCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerMovEntityVO.getSysCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerMovEntityVO.getSysSegCode() != null && !tplRiskFamilyProdPlayerMovEntityVO.getSysSegCode().equals("")) {
				preparedStatement.setLong(count++, tplRiskFamilyProdPlayerMovEntityVO.getSysSegCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}	

			if (tplRiskFamilyProdPlayerMovEntityVO.getPlyrCnpjNbr() != null && !tplRiskFamilyProdPlayerMovEntityVO.getPlyrCnpjNbr().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerMovEntityVO.getPlyrCnpjNbr());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			

			preparedStatement.executeUpdate();
			preparedStatement.replaceParametersInQuery(sqlQuery.toString());

			return tplRiskFamilyProdPlayerMovEntity;

		}

		catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		}
		 finally {
				closeStatement(preparedStatement);
				closeConnection(connection);
			}
	}

	/**
	 * Delete de todos os emissores de renda fixa do produto
	 * 
	 */
	public void deleteAll(TplProductMovEntity tplProductMovEntity) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			query.append(" DELETE FROM  " + C_TPL_RISK_FAMILY_PROD_PLYR_MOV);
			query.append(" WHERE ");
			query.append("    PROD_CODE  = ?  ");
			query.append("    AND SYS_CODE = ?   ");
			query.append("    AND SYS_SEG_CODE = ?  ");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));

			TplProductMovEntityVO tplProductMovEntityVO = (TplProductMovEntityVO) tplProductMovEntity.getData();

			int count = 1;

			if (tplProductMovEntityVO.getProdCode() != null && !tplProductMovEntityVO.getProdCode().equals("")) {
				preparedStatement.setString(count++, tplProductMovEntityVO.getProdCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductMovEntityVO.getSysCode() != null && !tplProductMovEntityVO.getSysCode().equals("")) {
				preparedStatement.setString(count++, tplProductMovEntityVO.getSysCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductMovEntityVO.getSysSegCode() != null) {
				preparedStatement.setLong(count++, tplProductMovEntityVO.getSysSegCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			preparedStatement.executeUpdate();
			preparedStatement.replaceParametersInQuery(query.toString());

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
	}

}
