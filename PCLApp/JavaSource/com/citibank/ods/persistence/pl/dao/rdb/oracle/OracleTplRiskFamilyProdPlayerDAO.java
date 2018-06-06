package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplPlayerRoleEntity;
import com.citibank.ods.entity.pl.TplProductEntity;
import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerEntity;
import com.citibank.ods.entity.pl.valueobject.TplProductEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRiskFamilyProdPlayerEntityVO;
import com.citibank.ods.persistence.pl.dao.TplRiskFamilyProdPlayerDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class OracleTplRiskFamilyProdPlayerDAO extends BaseOracleTplRiskFamilyProdPlayerDAO implements TplRiskFamilyProdPlayerDAO{

	/*
	 * Nome da tabela
	 */
	private static final String C_TPL_RISK_FAMILY_PROD_PLAYER = C_PL_SCHEMA
	                                                  + "TPL_RISK_FAMILY_PROD_PLAYER";
	
	public TplRiskFamilyProdPlayerEntity delete(TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean exists(TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuffer query = new StringBuffer();
		
		try {
			connection = OracleODSDAOFactory.getConnection();

			query.append("SELECT COUNT(*)");
			query.append(" FROM ");
			query.append(C_TPL_RISK_FAMILY_PROD_PLAYER);
			query.append(" WHERE ");
			query.append("    PROD_CODE  = ?  ");
			query.append("    AND SYS_CODE = ?   ");
			query.append("    AND SYS_SEG_CODE = ?  ");
			query.append("    AND PLYR_CNPJ_NBR = ?  ");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));

			TplRiskFamilyProdPlayerEntityVO tplRiskFamilyProdPlayerEntityVO = (TplRiskFamilyProdPlayerEntityVO) tplRiskFamilyProdPlayerEntity.getData();

			int count = 1;

			if (tplRiskFamilyProdPlayerEntityVO.getProdCode() != null && !tplRiskFamilyProdPlayerEntityVO.getProdCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerEntityVO.getProdCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerEntityVO.getSysCode() != null && !tplRiskFamilyProdPlayerEntityVO.getSysCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerEntityVO.getSysCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerEntityVO.getSysSegCode() != null) {
				preparedStatement.setLong(count++, tplRiskFamilyProdPlayerEntityVO.getSysSegCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerEntityVO.getPlyrCnpjNbr() != null && !tplRiskFamilyProdPlayerEntityVO.getPlyrCnpjNbr().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerEntityVO.getPlyrCnpjNbr());
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
		   finally
		    {
		      closeStatement( preparedStatement );
		      closeConnection( connection );
		    }
	}

	/**
	 * 
	 */
	public TplRiskFamilyProdPlayerEntity insert(TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity) {
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			sqlQuery.append("INSERT INTO " + C_TPL_RISK_FAMILY_PROD_PLAYER + " ( ");
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
			sqlQuery.append(" LAST_AUTH_DATE  ");
			
			sqlQuery.append(" ) ");
			sqlQuery.append(" VALUES ( ?,?,?,?,?,?,?,?,?,?,? ) ");

			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));

			TplRiskFamilyProdPlayerEntityVO tplRiskFamilyProdPlayerEntityVO = (TplRiskFamilyProdPlayerEntityVO) tplRiskFamilyProdPlayerEntity.getData();

			int count = 1;

			if (tplRiskFamilyProdPlayerEntityVO.getPlyrCnpjNbr() != null && !tplRiskFamilyProdPlayerEntityVO.getPlyrCnpjNbr().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerEntityVO.getPlyrCnpjNbr());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerEntityVO.getProdFamlCode() != null && !tplRiskFamilyProdPlayerEntityVO.getProdFamlCode().equals("")) {
				preparedStatement.setLong(count++, tplRiskFamilyProdPlayerEntityVO.getProdFamlCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerEntityVO.getProdInvstRiskCode() != null && !tplRiskFamilyProdPlayerEntityVO.getProdInvstRiskCode().equals("")) {
				preparedStatement.setLong(count++, tplRiskFamilyProdPlayerEntityVO.getProdInvstRiskCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerEntityVO.getLastUpdUserId() != null && !tplRiskFamilyProdPlayerEntityVO.getLastUpdUserId().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerEntityVO.getLastUpdUserId());
			} else {
				preparedStatement.setString(count++, null);
			}

			if (tplRiskFamilyProdPlayerEntityVO.getLastUpdDate() != null && !tplRiskFamilyProdPlayerEntityVO.getLastUpdDate().equals("")) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplRiskFamilyProdPlayerEntityVO.getLastUpdDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			if (tplRiskFamilyProdPlayerEntityVO.getProdCode() != null && !tplRiskFamilyProdPlayerEntityVO.getProdCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerEntityVO.getProdCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerEntityVO.getSysCode() != null && !tplRiskFamilyProdPlayerEntityVO.getSysCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerEntityVO.getSysCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerEntityVO.getSysSegCode() != null && !tplRiskFamilyProdPlayerEntityVO.getSysSegCode().equals("")) {
				preparedStatement.setLong(count++, tplRiskFamilyProdPlayerEntityVO.getSysSegCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			
			if (tplRiskFamilyProdPlayerEntityVO.getRecStatCode() != null && !tplRiskFamilyProdPlayerEntityVO.getRecStatCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerEntityVO.getRecStatCode());
			} else {
				preparedStatement.setString(count++, null);
			}			
			
			if (tplRiskFamilyProdPlayerEntityVO.getLastAuthUserId() != null && !tplRiskFamilyProdPlayerEntityVO.getLastAuthUserId().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerEntityVO.getLastAuthUserId());
			} else {
				preparedStatement.setString(count++, null);
			}	
			
			if (tplRiskFamilyProdPlayerEntityVO.getLastAuthDate() != null && !tplRiskFamilyProdPlayerEntityVO.getLastAuthDate().equals("")) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplRiskFamilyProdPlayerEntityVO.getLastAuthDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}
			

			preparedStatement.execute();
			preparedStatement.replaceParametersInQuery(sqlQuery.toString());

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		}
		   finally
		    {
		      closeStatement( preparedStatement );
		      closeConnection( connection );
		    }
		return tplRiskFamilyProdPlayerEntity;
	
	}

	public List<TplRiskFamilyProdPlayerEntity> list(TplProductEntity tplProductEntity, String recStatCode) {
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		StringBuffer sqlQuery = new StringBuffer();

		List<TplRiskFamilyProdPlayerEntity> listTplRiskFamilyProdPlayerEntity = new ArrayList<TplRiskFamilyProdPlayerEntity>();
		
		TplProductEntityVO tplProductEntityVO = (TplProductEntityVO)tplProductEntity.getData();		
		
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
			sqlQuery.append(" LAST_AUTH_DATE  ");
			
			sqlQuery.append(" FROM " + C_TPL_RISK_FAMILY_PROD_PLAYER + "  ");
			sqlQuery.append(" WHERE ");
			sqlQuery.append("    PROD_CODE  = ?  ");
			sqlQuery.append("    AND SYS_CODE = ?   ");
			sqlQuery.append("    AND SYS_SEG_CODE = ?  ");
			
			if(recStatCode != null){
				sqlQuery.append("    AND REC_STAT_CODE = ?  ");	
			}
			
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			
			int count = 1;

			if (tplProductEntityVO.getProdCode() != null && !tplProductEntityVO.getProdCode().equals("")) {
				preparedStatement.setString(count++, tplProductEntityVO.getProdCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductEntityVO.getSysCode() != null && !tplProductEntityVO.getSysCode().equals("")) {
				preparedStatement.setString(count++, tplProductEntityVO.getSysCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductEntityVO.getSysSegCode() != null) {
				preparedStatement.setLong(count++, tplProductEntityVO.getSysSegCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if(recStatCode != null){
				preparedStatement.setString(count++, recStatCode);
			}

			resultSet = preparedStatement.executeQuery();
			preparedStatement.replaceParametersInQuery(sqlQuery.toString());

			while (resultSet.next()) {
				
				TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity = new TplRiskFamilyProdPlayerEntity();
				TplRiskFamilyProdPlayerEntityVO tplRiskFamilyProdPlayerEntityVO = (TplRiskFamilyProdPlayerEntityVO) tplRiskFamilyProdPlayerEntity.getData();
				
				tplRiskFamilyProdPlayerEntityVO.setPlyrCnpjNbr(resultSet.getString(1));
				tplRiskFamilyProdPlayerEntityVO.setProdFamlCode(resultSet.getString(2) == null ? null : new BigInteger( resultSet.getString(2) ));
				tplRiskFamilyProdPlayerEntityVO.setProdInvstRiskCode(resultSet.getString(3) == null ? null : new BigInteger( resultSet.getString(3) ));
				tplRiskFamilyProdPlayerEntityVO.setLastUpdUserId(resultSet.getString(4));
				tplRiskFamilyProdPlayerEntityVO.setLastUpdDate(resultSet.getTimestamp(5));
				tplRiskFamilyProdPlayerEntityVO.setProdCode(resultSet.getString(6));
				tplRiskFamilyProdPlayerEntityVO.setSysCode(resultSet.getString(7));
				tplRiskFamilyProdPlayerEntityVO.setSysSegCode(resultSet.getString(8) == null ? null : new BigInteger( resultSet.getString(8) ));

				tplRiskFamilyProdPlayerEntityVO.setRecStatCode(resultSet.getString(9));
				tplRiskFamilyProdPlayerEntityVO.setLastAuthUserId(resultSet.getString(10));
				tplRiskFamilyProdPlayerEntityVO.setLastAuthDate(resultSet.getTimestamp(11));
				
				listTplRiskFamilyProdPlayerEntity.add(tplRiskFamilyProdPlayerEntity);
			}

			resultSet.close();

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}

		return listTplRiskFamilyProdPlayerEntity;
	}

	public TplRiskFamilyProdPlayerEntity update(TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity) {
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			sqlQuery.append("UPDATE " + C_TPL_RISK_FAMILY_PROD_PLAYER + " SET ");
			
			sqlQuery.append(" PLYR_CNPJ_NBR = ?,  ");
			sqlQuery.append(" PROD_FAML_CODE = ?,  ");
			sqlQuery.append(" PROD_INVST_RISK_CODE = ?,  ");
			sqlQuery.append(" LAST_UPD_USER_ID = ?,  ");
			sqlQuery.append(" LAST_UPD_DATE = ?,  ");
			sqlQuery.append(" PROD_CODE = ?,  ");
			sqlQuery.append(" SYS_CODE = ?,  ");
			sqlQuery.append(" SYS_SEG_CODE = ?,  ");
			
			sqlQuery.append(" REC_STAT_CODE = ?,  ");
			sqlQuery.append(" LAST_AUTH_USER_ID = ?,  ");
			sqlQuery.append(" LAST_AUTH_DATE = ?  ");			
			
			sqlQuery.append(" WHERE ");
			sqlQuery.append("    PROD_CODE  = ?  ");
			sqlQuery.append("    AND SYS_CODE = ?   ");
			sqlQuery.append("    AND SYS_SEG_CODE = ?  ");
			sqlQuery.append("    AND PLYR_CNPJ_NBR = ?  ");

			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			TplRiskFamilyProdPlayerEntityVO tplRiskFamilyProdPlayerEntityVO = (TplRiskFamilyProdPlayerEntityVO) tplRiskFamilyProdPlayerEntity.getData();
			
			int count = 1;

			if (tplRiskFamilyProdPlayerEntityVO.getPlyrCnpjNbr() != null && !tplRiskFamilyProdPlayerEntityVO.getPlyrCnpjNbr().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerEntityVO.getPlyrCnpjNbr());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerEntityVO.getProdFamlCode() != null && !tplRiskFamilyProdPlayerEntityVO.getProdFamlCode().equals("")) {
				preparedStatement.setLong(count++, tplRiskFamilyProdPlayerEntityVO.getProdFamlCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerEntityVO.getProdInvstRiskCode() != null && !tplRiskFamilyProdPlayerEntityVO.getProdInvstRiskCode().equals("")) {
				preparedStatement.setLong(count++, tplRiskFamilyProdPlayerEntityVO.getProdInvstRiskCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerEntityVO.getLastUpdUserId() != null && !tplRiskFamilyProdPlayerEntityVO.getLastUpdUserId().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerEntityVO.getLastUpdUserId());
			} else {
				preparedStatement.setString(count++, null);
			}

			if (tplRiskFamilyProdPlayerEntityVO.getLastUpdDate() != null && !tplRiskFamilyProdPlayerEntityVO.getLastUpdDate().equals("")) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplRiskFamilyProdPlayerEntityVO.getLastUpdDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			if (tplRiskFamilyProdPlayerEntityVO.getProdCode() != null && !tplRiskFamilyProdPlayerEntityVO.getProdCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerEntityVO.getProdCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerEntityVO.getSysCode() != null && !tplRiskFamilyProdPlayerEntityVO.getSysCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerEntityVO.getSysCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerEntityVO.getSysSegCode() != null && !tplRiskFamilyProdPlayerEntityVO.getSysSegCode().equals("")) {
				preparedStatement.setLong(count++, tplRiskFamilyProdPlayerEntityVO.getSysSegCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			
			if (tplRiskFamilyProdPlayerEntityVO.getRecStatCode() != null && !tplRiskFamilyProdPlayerEntityVO.getRecStatCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerEntityVO.getRecStatCode());
			} else {
				preparedStatement.setString(count++, null);
			}			
			
			if (tplRiskFamilyProdPlayerEntityVO.getLastAuthUserId() != null && !tplRiskFamilyProdPlayerEntityVO.getLastAuthUserId().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerEntityVO.getLastAuthUserId());
			} else {
				preparedStatement.setString(count++, null);
			}	
			
			if (tplRiskFamilyProdPlayerEntityVO.getLastAuthDate() != null && !tplRiskFamilyProdPlayerEntityVO.getLastAuthDate().equals("")) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplRiskFamilyProdPlayerEntityVO.getLastAuthDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			//where			
			if (tplRiskFamilyProdPlayerEntityVO.getProdCode() != null && !tplRiskFamilyProdPlayerEntityVO.getProdCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerEntityVO.getProdCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerEntityVO.getSysCode() != null && !tplRiskFamilyProdPlayerEntityVO.getSysCode().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerEntityVO.getSysCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplRiskFamilyProdPlayerEntityVO.getSysSegCode() != null && !tplRiskFamilyProdPlayerEntityVO.getSysSegCode().equals("")) {
				preparedStatement.setLong(count++, tplRiskFamilyProdPlayerEntityVO.getSysSegCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}	

			if (tplRiskFamilyProdPlayerEntityVO.getPlyrCnpjNbr() != null && !tplRiskFamilyProdPlayerEntityVO.getPlyrCnpjNbr().equals("")) {
				preparedStatement.setString(count++, tplRiskFamilyProdPlayerEntityVO.getPlyrCnpjNbr());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			

			preparedStatement.executeUpdate();
			preparedStatement.replaceParametersInQuery(sqlQuery.toString());

			return tplRiskFamilyProdPlayerEntity;

		}

		catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		}
		   finally
		    {
		      closeStatement( preparedStatement );
		      closeConnection( connection );
		    }		
	}

	/**
	 * inativa os emissores(renda fixa) do produto
	 * 
	 */
	public void inactivateEmissor(TplProductEntity tplProductEntity) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			TplProductEntityVO tplProductEntityVO = (TplProductEntityVO) tplProductEntity.getData();

			query.append(" UPDATE " + C_TPL_RISK_FAMILY_PROD_PLAYER);
			query.append(" SET ");
			query.append("   REC_STAT_CODE = ? ");
			query.append(" WHERE ");
			query.append("    PROD_CODE  = ?  ");
			query.append("    AND SYS_CODE = ?   ");
			query.append("    AND SYS_SEG_CODE = ?  ");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));
			int count = 1;

			preparedStatement.setString(count++, TplPlayerRoleEntity.C_REC_STAT_CODE_INACTIVE);

			if (tplProductEntityVO.getProdCode() != null && !tplProductEntityVO.getProdCode().equals("")) {
				preparedStatement.setString(count++, tplProductEntityVO.getProdCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductEntityVO.getSysCode() != null && !tplProductEntityVO.getSysCode().equals("")) {
				preparedStatement.setString(count++, tplProductEntityVO.getSysCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductEntityVO.getSysSegCode() != null) {
				preparedStatement.setLong(count++, tplProductEntityVO.getSysSegCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			preparedStatement.executeUpdate();
			preparedStatement.replaceParametersInQuery(query.toString());
			
		} catch (SQLException e) {
			throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
	}

}
