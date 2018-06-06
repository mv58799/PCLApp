/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplErEntity;
import com.citibank.ods.entity.pl.TplErEntity;
import com.citibank.ods.entity.pl.valueobject.TplErEntityVO;
import com.citibank.ods.persistence.pl.dao.TplErDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class OracleTplErDAO extends BaseOracleTplErDAO implements TplErDAO {

	public static final String C_TPL_ER = C_PL_SCHEMA + "TPL_ER";

	/**
	 * Este método insere um novo registro de ER
	 * 
	 * @see com.citibank.ods.persistence.pl.dao.TplErDAO#insert(com.citibank.ods.entity.pl.TplErEntity)
	 */
	public TplErEntity insert(TplErEntity erEntity_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("INSERT INTO " + C_TPL_ER + " (");
			query.append(C_ER_NBR + ", ");
			query.append(C_ER_RELTN_TRF_IND + ", ");
			query.append(C_RELTN_END_REAS_CODE + ", ");
			query.append(C_RELTN_END_REAS_TEXT + ", ");
			query.append(C_EQUITY_CLASS_CODE + ", ");
			query.append(C_LAST_UPD_DATE + ", ");
			query.append(C_LAST_UPD_USER_ID + ", ");
			query.append(C_REC_STAT_CODE + ", ");
			query.append(C_LAST_AUTH_DATE + ", ");
			query.append(C_LAST_AUTH_USER_ID);
			query.append(") VALUES ( ");
			query.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));
			int count = 1;

			preparedStatement.setString(count++, erEntity_.getData().getErNbr());

			preparedStatement.setString(count++, erEntity_.getData().getErReltnTrfInd());

			if (erEntity_.getData().getReltnEndReasCode() != null) {
				preparedStatement.setLong(count++, erEntity_.getData().getReltnEndReasCode().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			preparedStatement.setString(count++, erEntity_.getData().getReltnEndReasText());

			if (erEntity_.getData().getEquityClassCode() != null) {
				preparedStatement.setLong(count++, erEntity_.getData().getEquityClassCode().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			preparedStatement.setTimestamp(count++, new Timestamp(erEntity_.getData().getLastUpdDate().getTime()));

			preparedStatement.setString(count++, erEntity_.getData().getLastUpdUserId());

			TplErEntityVO tplErEntityVO = (TplErEntityVO) erEntity_.getData();

			preparedStatement.setString(count++, tplErEntityVO.getRecStatCode());

			preparedStatement.setTimestamp(count++, new Timestamp(tplErEntityVO.getLastAuthDate().getTime()));

			preparedStatement.setString(count++, tplErEntityVO.getLastAuthUserId());

			preparedStatement.replaceParametersInQuery(query.toString());
			preparedStatement.executeUpdate();

			return erEntity_;
		} catch (Exception e) {
			throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}

	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.TplErDAO#deleteRelations(java.lang.String)
	 */
	public void deleteRelations(String erNbr_) {

	}

	/**
	 * Este método altera os dados de um ER
	 * 
	 * @see com.citibank.ods.persistence.pl.dao.TplErDAO#update(com.citibank.ods.entity.pl.TplErEntity)
	 */
	public TplErEntity update(TplErEntity tplErEntity_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("UPDATE " + C_TPL_ER);
			query.append(" SET ");
			query.append(C_ER_RELTN_TRF_IND + " = ?, ");
			query.append(C_RELTN_END_REAS_CODE + " = ?, ");
			query.append(C_RELTN_END_REAS_TEXT + " = ?, ");
			query.append(C_EQUITY_CLASS_CODE + " = ?, ");
			query.append(C_LAST_UPD_DATE + " = ?, ");
			query.append(C_LAST_UPD_USER_ID + " = ?, ");
			query.append(C_LAST_AUTH_DATE + " = ?, ");
			query.append(C_LAST_AUTH_USER_ID + " = ?, ");
			query.append(C_REC_STAT_CODE + " = ? ");
			query.append(" WHERE ");
			query.append(C_ER_NBR + "= ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));
			int count = 1;

			preparedStatement.setString(count++, tplErEntity_.getData().getErReltnTrfInd());

			if (tplErEntity_.getData().getReltnEndReasCode() != null) {
				preparedStatement.setLong(count++, tplErEntity_.getData().getReltnEndReasCode().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			preparedStatement.setString(count++, tplErEntity_.getData().getReltnEndReasText());

			if (tplErEntity_.getData().getEquityClassCode() != null) {
				preparedStatement.setLong(count++, tplErEntity_.getData().getEquityClassCode().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			preparedStatement.setTimestamp(count++, new Timestamp(tplErEntity_.getData().getLastUpdDate().getTime()));

			preparedStatement.setString(count++, tplErEntity_.getData().getLastUpdUserId());

			TplErEntityVO tplErEntityVO = (TplErEntityVO) tplErEntity_.getData();

			preparedStatement.setTimestamp(count++, new Timestamp(tplErEntityVO.getLastAuthDate().getTime()));

			preparedStatement.setString(count++, tplErEntityVO.getLastAuthUserId());

			preparedStatement.setString(count++, tplErEntityVO.getRecStatCode());

			preparedStatement.setString(count++, tplErEntity_.getData().getErNbr());

			preparedStatement.replaceParametersInQuery(query.toString());
			preparedStatement.executeUpdate();

			return tplErEntity_;

		} catch (Exception e) {
			throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}

	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.TplErDAO#existsRelationActive(java.lang.String)
	 */
	public boolean existsRelationActive(String erNbr_) {

		return false;
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.TplErDAO#existsRelation(java.lang.String, java.lang.String)
	 */
	public boolean existsRelation(String erNbr_, String emNbr_) {

		return false;
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.TplErDAO#loadErNbr()
	 */
	public DataSet loadErNbr() {

		return null;
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.BaseTplErEmDAO#find(com.citibank.ods.entity.pl.BaseTplErEmEntity)
	 */
	public BaseTplErEntity find(BaseTplErEntity erEntity_) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuffer query = new StringBuffer();

		ArrayList tplErEntityEntities;
		BaseTplErEntity entityReturn = null;

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("SELECT ");
			query.append(C_ER_NBR + ", ");
			query.append(C_ER_RELTN_TRF_IND + ", ");
			query.append(C_RELTN_END_REAS_CODE + ", ");
			query.append(C_RELTN_END_REAS_TEXT + ", ");
			query.append(C_EQUITY_CLASS_CODE + ", ");
			query.append(C_LAST_UPD_DATE + ", ");
			query.append(C_LAST_UPD_USER_ID + ", ");
			query.append(C_LAST_AUTH_DATE + ", ");
			query.append(C_LAST_AUTH_USER_ID + ", ");
			query.append(C_REC_STAT_CODE);
			query.append(" FROM ");
			query.append(C_TPL_ER);
			query.append(" WHERE ");
			query.append(C_ER_NBR + " = ?");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));

			preparedStatement.setString(1, erEntity_.getData().getErNbr());
			preparedStatement.replaceParametersInQuery(query.toString());
			resultSet = preparedStatement.executeQuery();

			tplErEntityEntities = instantiateFromResultSet(resultSet);

			if (tplErEntityEntities.size() == 0) {
				throw new NoRowsReturnedException();
			} else if (tplErEntityEntities.size() > 1) {
				throw new UnexpectedException(C_ERROR_TOO_MANY_ROWS_RETURNED);
			} else {
				entityReturn = (BaseTplErEntity) tplErEntityEntities.get(0);
			}

			return entityReturn;
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		}
		finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
	}

	private ArrayList instantiateFromResultSet(ResultSet resultSet_) {
		TplErEntity tplErEntity;
		Timestamp timestamp;
		Date date;
		ArrayList tplErEntities = new ArrayList();

		try {
			while (resultSet_.next()) {
				tplErEntity = new TplErEntity();
				tplErEntity.getData().setErNbr(resultSet_.getString(C_ER_NBR));

				if (resultSet_.getString(C_ER_RELTN_TRF_IND) != null) {
					tplErEntity.getData().setErReltnTrfInd(resultSet_.getString(C_ER_RELTN_TRF_IND));
				} else {
					tplErEntity.getData().setErReltnTrfInd(null);
				}

				if (resultSet_.getString(C_RELTN_END_REAS_CODE) != null) {
					tplErEntity.getData().setReltnEndReasCode(new BigInteger(resultSet_.getString(C_RELTN_END_REAS_CODE)));
				} else {
					tplErEntity.getData().setReltnEndReasCode(null);
				}

				if (resultSet_.getString(C_RELTN_END_REAS_TEXT) != null) {
					tplErEntity.getData().setReltnEndReasText(resultSet_.getString(C_RELTN_END_REAS_TEXT));
				} else {
					tplErEntity.getData().setReltnEndReasText(null);
				}

				if (resultSet_.getString(C_EQUITY_CLASS_CODE) != null) {
					tplErEntity.getData().setEquityClassCode(new BigInteger(resultSet_.getString(C_EQUITY_CLASS_CODE)));
				} else {
					tplErEntity.getData().setEquityClassCode(null);
				}

				timestamp = resultSet_.getTimestamp(C_LAST_UPD_DATE);
				date = new Date(timestamp.getTime());

				tplErEntity.getData().setLastUpdDate(date);
				tplErEntity.getData().setLastUpdUserId(resultSet_.getString(C_LAST_UPD_USER_ID));

				timestamp = resultSet_.getTimestamp(C_LAST_AUTH_DATE);
				date = new Date(timestamp.getTime());

				tplErEntity.getData().setLastAuthDate(date);
				tplErEntity.getData().setLastAuthUserId(resultSet_.getString("LAST_AUTH_USER_ID"));
				tplErEntity.getData().setRecStatCode(resultSet_.getString(C_REC_STAT_CODE));

				tplErEntities.add(tplErEntity);
			}
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e);
		}

		return tplErEntities;
	}

}
