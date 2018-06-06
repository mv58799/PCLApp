package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplCurAcctPrmntInstrEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrEntity;
import com.citibank.ods.entity.pl.valueobject.TplCurAcctPrmntInstrEntityVO;
import com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author michele.monteiro,05/06/2007
 *  
 */

public class OracleTplCurAcctPrmntInstrDAO extends
		BaseOracleTplCurAcctPrmntInstrDAO implements TplCurAcctPrmntInstrDAO {

	/*
	 * Nome da tabela
	 */
	private static final String C_TPL_CUR_ACCT_PRMNT_INSTR = C_PL_SCHEMA
			+ "TPL_CUR_ACCT_PRMNT_INSTR";

	private static final String C_TPL_PRMNT_INSTR_PRVT = C_PL_SCHEMA
			+ "TPL_PRMNT_INSTR_PRVT";

	private static final String C_TO3_PRODUCT_ACCOUNT = C_O3_SCHEMA
			+ "TO3_PRODUCT_ACCOUNT";

	private static final String C_TPL_CUSTOMER_PRVT = C_PL_SCHEMA
			+ "TPL_CUSTOMER_PRVT";

	private static final String C_TBG_POINT_ACCT_INVST = C_BG_SCHEMA
			+ "TBG_POINT_ACCT_INVST";

	private static final String C_ALIAS_TPL_CUR_ACCT_PRMNT = "CCIP";

	private static final String C_ALIAS_TPL_PRMNT_INSTR_PRVT = "IP";

	private static final String C_ALIAS_TO3_PRODUCT_ACCOUNT = "CC";

	private static final String C_ALIAS_TPL_CUSTOMER = "CUST";

	private static final String C_ALIAS_TBG_POINT_ACCT_INVST = "CCI";

	private static final String C_CUST_FULL_NAME_TEXT = "CUST_FULL_NAME_TEXT";

	private static final String C_CUR_ACCT_NBR = "CUR_ACCT_NBR";

	private static final String C_PRMNT_INSTR_INVST_CUR_ACCT_IND = "PRMNT_INSTR_INVST_CUR_ACCT_IND";

	private static final String C_INVST_CUR_ACCT_NBR = "INVST_CUR_ACCT_NBR";

	private static final String C_PRMNT_INSTR_TEXT = "PRMNT_INSTR_TEXT";

	private static final String C_PROD_CODE = "PROD_CODE";

	private static final String C_IND_YES = "'S'";

	/*
	 * Campos específicos da tabela
	 */
	private String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

	private String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

	private String C_REC_STAT_CODE = "REC_STAT_CODE";

	private String C_REC_STAT_TEXT = "REC_STAT_TEXT";

	private String C_INDICATOR_TEXT = "INDICATOR_TEXT";

	private String C_INDICATOR_CODE = "INDICATOR_CODE";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.citibank.ods.persistence.pl.dao.BaseTplCurAcctPrmntInstrDAO#find(com.citibank.ods.entity.pl.BaseTplCurAcctPrmntInstrEntity)
	 */
	public BaseTplCurAcctPrmntInstrEntity find(
			BaseTplCurAcctPrmntInstrEntity baseTplCurAcctPrmntInstrEntity_) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrDAO#delete(java.lang.String,
	 *      java.math.BigInteger)
	 */
	public void delete(String recStatCode_, BigInteger custNbr_,
			BigInteger prodAcctCode_, BigInteger prodUnderAcctCode_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append(" UPDATE " + C_TPL_CUR_ACCT_PRMNT_INSTR);
			query.append(" SET ");
			query.append(C_REC_STAT_CODE + "= ?");
			query.append(" WHERE " + C_CUST_NBR + " = ? AND ");
			query.append(C_PROD_ACCT_CODE + " = ? AND ");
			query.append(C_PROD_UNDER_ACCT_CODE + " = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			int count = 1;

			if (recStatCode_ != null) {
				preparedStatement.setString(count++, recStatCode_);
			} else {
				preparedStatement.setString(count++, null);
			}

			if (custNbr_ != null && !custNbr_.equals("")) {
				preparedStatement.setLong(count++, custNbr_.longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			if (prodAcctCode_ != null && !prodAcctCode_.equals("")) {
				preparedStatement.setLong(count++, prodAcctCode_.longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			if (prodUnderAcctCode_ != null && !prodUnderAcctCode_.equals("")) {
				preparedStatement.setLong(count++, prodUnderAcctCode_.longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			preparedStatement.executeUpdate();
			preparedStatement.replaceParametersInQuery(query.toString());
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(),
					C_ERROR_EXECUTING_STATEMENT, e);
		} 
	    finally
	    {
	      closeStatement( preparedStatement );
	      closeConnection( connection );
	    }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrDAO#exists(com.citibank.ods.entity.pl.TplCurAcctPrmntInstrEntity)
	 */
	public boolean exists(TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity_) {
		boolean exists = true;

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("SELECT COUNT(*)");
			query.append(" FROM ");
			query.append(C_TPL_CUR_ACCT_PRMNT_INSTR);
			query.append(" WHERE " + C_PRMNT_INSTR_CODE + " = ? AND ");
			query.append(C_PROD_ACCT_CODE + " = ? AND ");
			query.append(C_PROD_UNDER_ACCT_CODE + " = ? AND ");
			query.append(C_CUST_NBR + " = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			int count = 1;

			TplCurAcctPrmntInstrEntityVO tplCurAcctPrmntInstrEntityVO = (TplCurAcctPrmntInstrEntityVO) tplCurAcctPrmntInstrEntity_
					.getData();

			preparedStatement.setLong(count++, tplCurAcctPrmntInstrEntityVO
					.getPrmntInstrCode().longValue());
			preparedStatement.setLong(count++, tplCurAcctPrmntInstrEntityVO
					.getProdAcctCode().longValue());
			preparedStatement.setLong(count++, tplCurAcctPrmntInstrEntityVO
					.getProdUnderAcctCode().longValue());
			preparedStatement.setLong(count++, tplCurAcctPrmntInstrEntityVO
					.getCustNbr().longValue());

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
			throw new UnexpectedException(e.getErrorCode(),
					C_ERROR_EXECUTING_STATEMENT, e);
		}
	    finally
	    {
	      closeStatement( preparedStatement );
	      closeConnection( connection );
	    }
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrDAO#insert(com.citibank.ods.entity.pl.TplCurAcctPrmntInstrEntity)
	 */
	public TplCurAcctPrmntInstrEntity insert(
			TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("INSERT INTO " + C_TPL_CUR_ACCT_PRMNT_INSTR + " (");
			query.append(C_PRMNT_INSTR_CODE + ", ");
			query.append(C_PROD_ACCT_CODE + ", ");
			query.append(C_PROD_UNDER_ACCT_CODE + ", ");
			query.append(C_CUST_NBR + ", ");
			query.append(C_LAST_UPD_DATE + ", ");
			query.append(C_LAST_UPD_USER_ID + ", ");
			query.append(C_REC_STAT_CODE + ", ");
			query.append(C_LAST_AUTH_DATE + ", ");
			query.append(C_LAST_AUTH_USER_ID);
			query.append(" ) VALUES ( ");
			query.append("?, ?, ?, ?, ?, ?, ?, ?, ? ) ");

			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			int count = 1;

			if (tplCurAcctPrmntInstrEntity_.getData().getPrmntInstrCode() != null) {
				preparedStatement.setLong(count++, tplCurAcctPrmntInstrEntity_
						.getData().getPrmntInstrCode().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			if (tplCurAcctPrmntInstrEntity_.getData().getProdAcctCode() != null) {
				preparedStatement.setLong(count++, tplCurAcctPrmntInstrEntity_
						.getData().getProdAcctCode().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			if (tplCurAcctPrmntInstrEntity_.getData().getProdUnderAcctCode() != null) {
				preparedStatement.setLong(count++, tplCurAcctPrmntInstrEntity_
						.getData().getProdUnderAcctCode().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			if (tplCurAcctPrmntInstrEntity_.getData().getCustNbr() != null) {
				preparedStatement.setLong(count++, tplCurAcctPrmntInstrEntity_
						.getData().getCustNbr().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			if (tplCurAcctPrmntInstrEntity_.getData().getLastUpdDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(
						tplCurAcctPrmntInstrEntity_.getData().getLastUpdDate()
								.getTime()));
			} else {
				preparedStatement.setTimestamp(count++, null);
			}

			if (tplCurAcctPrmntInstrEntity_.getData().getLastUpdUserId() != null) {
				preparedStatement.setString(count++, tplCurAcctPrmntInstrEntity_
						.getData().getLastUpdUserId());
			} else {
				preparedStatement.setString(count++, null);
			}

			//    Casting para Obter os campos especificos da tabela

			TplCurAcctPrmntInstrEntityVO tplCurAcctPrmntInstrEntityVO = (TplCurAcctPrmntInstrEntityVO) tplCurAcctPrmntInstrEntity_
					.getData();
			if (tplCurAcctPrmntInstrEntityVO.getRecStatCode() != null) {
				preparedStatement.setString(count++, tplCurAcctPrmntInstrEntityVO
						.getRecStatCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (tplCurAcctPrmntInstrEntityVO.getLastAuthDate() != null) {
						preparedStatement.setTimestamp(count++, new Timestamp(tplCurAcctPrmntInstrEntityVO.getLastAuthDate().getTime()));
			} else {
					preparedStatement.setTimestamp(count++, null);
			}

			if (tplCurAcctPrmntInstrEntityVO.getLastAuthUserId() != null) {
					preparedStatement.setString(count++,tplCurAcctPrmntInstrEntityVO.getLastAuthUserId());
			} else {
					preparedStatement.setString(count++, null);
			}			
			
			preparedStatement.replaceParametersInQuery(query.toString());
			preparedStatement.executeUpdate();
			
			return tplCurAcctPrmntInstrEntity_;
		} catch (Exception e) {
			throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrDAO#update(com.citibank.ods.entity.pl.TplCurAcctPrmntInstrEntity)
	 */
	public TplCurAcctPrmntInstrEntity update(
			TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("UPDATE " + C_TPL_CUR_ACCT_PRMNT_INSTR + "  SET ");
			query.append(C_LAST_UPD_USER_ID + " = ?, ");
			query.append(C_LAST_UPD_DATE + " = ?, ");
			query.append(C_LAST_AUTH_USER_ID + " = ?, ");
			query.append(C_LAST_AUTH_DATE + " = ?, ");
			query.append(C_REC_STAT_CODE + " = ? ");
			query.append(" WHERE ");
			query.append(C_CUST_NBR + " = ? AND ");
			query.append(C_PROD_ACCT_CODE + " = ? AND ");
			query.append(C_PROD_UNDER_ACCT_CODE + " = ? AND ");
			query.append(C_PRMNT_INSTR_CODE + " = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			int count = 1;

			if (tplCurAcctPrmntInstrEntity_.getData().getLastUpdUserId() != null) {
				preparedStatement.setString(count++, tplCurAcctPrmntInstrEntity_
						.getData().getLastUpdUserId());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplCurAcctPrmntInstrEntity_.getData().getLastUpdDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(
						tplCurAcctPrmntInstrEntity_.getData().getLastUpdDate()
								.getTime()));
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			// Casting para Obter os campos especificos da tabela
			TplCurAcctPrmntInstrEntityVO tplCurAcctPrmntInstrEntityVO = (TplCurAcctPrmntInstrEntityVO) tplCurAcctPrmntInstrEntity_
					.getData();

			if (tplCurAcctPrmntInstrEntityVO.getLastAuthUserId() != null) {
				preparedStatement.setString(count++, tplCurAcctPrmntInstrEntityVO
						.getLastAuthUserId());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplCurAcctPrmntInstrEntityVO.getLastAuthDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(
						tplCurAcctPrmntInstrEntityVO.getLastAuthDate()
								.getTime()));
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			if (tplCurAcctPrmntInstrEntityVO.getRecStatCode() != null) {
				preparedStatement.setString(count++, tplCurAcctPrmntInstrEntityVO
						.getRecStatCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplCurAcctPrmntInstrEntityVO.getCustNbr() != null) {
				preparedStatement.setLong(count++, tplCurAcctPrmntInstrEntityVO
						.getCustNbr().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplCurAcctPrmntInstrEntityVO.getProdAcctCode() != null) {
				preparedStatement.setLong(count++, tplCurAcctPrmntInstrEntityVO
						.getProdAcctCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplCurAcctPrmntInstrEntityVO.getProdUnderAcctCode() != null) {
				preparedStatement.setLong(count++, tplCurAcctPrmntInstrEntityVO
						.getProdUnderAcctCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplCurAcctPrmntInstrEntityVO.getPrmntInstrCode() != null) {
				preparedStatement.setLong(count++, tplCurAcctPrmntInstrEntityVO
						.getPrmntInstrCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			preparedStatement.executeUpdate();
			preparedStatement.replaceParametersInQuery(query.toString());

			return tplCurAcctPrmntInstrEntity_;
		} catch (Exception e) {
			throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
	}

	public ArrayList selectByPK(BigInteger custNbr_, BigInteger prodAcctCode_,
			BigInteger prodUnderAcctCode_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuffer query = new StringBuffer();
		ArrayList curAcctPrmntInstrEntities;

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("SELECT ");
			query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "." + C_PRMNT_INSTR_CODE
					+ ", ");
			query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "." + C_PROD_ACCT_CODE
					+ ", ");
			query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "."
					+ C_PROD_UNDER_ACCT_CODE + ", ");
			query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "." + C_CUST_NBR + ", ");
			query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "." + C_LAST_UPD_USER_ID
					+ ", ");
			query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "." + C_LAST_UPD_DATE
					+ ", ");
			query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "." + C_LAST_AUTH_USER_ID
					+ ", ");
			query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "." + C_LAST_AUTH_DATE
					+ ", ");
			query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "." + C_REC_STAT_CODE);
			query.append(" FROM ");
			query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + " , ");
			query.append(C_TPL_PRMNT_INSTR_PRVT);

			query.append(" WHERE " + C_TPL_CUR_ACCT_PRMNT_INSTR + "."
					+ C_PRMNT_INSTR_CODE);
			query.append(" = " + C_TPL_PRMNT_INSTR_PRVT + "."
					+ C_PRMNT_INSTR_CODE);
			query.append(" AND " + C_TPL_CUR_ACCT_PRMNT_INSTR + "."
					+ C_CUST_NBR);
			query.append(" = " + C_TPL_PRMNT_INSTR_PRVT + "." + C_CUST_NBR);
			query.append(" AND " + C_TPL_CUR_ACCT_PRMNT_INSTR + "."
					+ C_REC_STAT_CODE);
			query
					.append(" <> "
							+ "'"
							+ TplCurAcctPrmntInstrEntity.C_REC_STAT_CODE_INACTIVE
							+ "'");
			query.append(" AND " + C_TPL_CUR_ACCT_PRMNT_INSTR + "."
					+ C_CUST_NBR + " = ? AND ");
			query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "." + C_PROD_ACCT_CODE
					+ " = ? AND ");
			query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "."
					+ C_PROD_UNDER_ACCT_CODE + " = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

			int count = 1;

			if (custNbr_ != null && !custNbr_.equals("")) {
				preparedStatement.setLong(count++, custNbr_.longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			if (prodAcctCode_ != null) {
				preparedStatement.setLong(count++, prodAcctCode_.longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			if (prodUnderAcctCode_ != null) {
				preparedStatement.setLong(count++, prodUnderAcctCode_.longValue());
			} else {
				preparedStatement.setString(count++, null);
			}
			resultSet = preparedStatement.executeQuery();
			preparedStatement.replaceParametersInQuery(query.toString());
			curAcctPrmntInstrEntities = instantiateFromResultSet(resultSet);

			resultSet.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(),
					C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}

		return curAcctPrmntInstrEntities;
	}

	/*
	 * Retorna uma coleção de entities a partir do rs
	 */
	private ArrayList instantiateFromResultSet(ResultSet resultSet_) {
		TplCurAcctPrmntInstrEntityVO tplCurAcctPrmntInstrEntityVO;
		TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity;
		ArrayList tplCurAcctPrmntEntities = new ArrayList();

		try {
			while (resultSet_.next()) {
				tplCurAcctPrmntInstrEntity = new TplCurAcctPrmntInstrEntity();

				if (resultSet_.getString(C_CUST_NBR) != null) {
					tplCurAcctPrmntInstrEntity.getData().setCustNbr(
							new BigInteger(resultSet_.getString(C_CUST_NBR)));

				}

				if (resultSet_.getString(C_PRMNT_INSTR_CODE) != null) {
					tplCurAcctPrmntInstrEntity.getData().setPrmntInstrCode(
							new BigInteger(resultSet_
									.getString(C_PRMNT_INSTR_CODE)));

				}

				if (resultSet_.getString(C_PROD_ACCT_CODE) != null) {

					tplCurAcctPrmntInstrEntity.getData().setProdAcctCode(
							new BigInteger(resultSet_
									.getString(C_PROD_ACCT_CODE)));

				}

				if (resultSet_.getString(C_PROD_UNDER_ACCT_CODE) != null) {
					tplCurAcctPrmntInstrEntity.getData().setProdUnderAcctCode(
							new BigInteger(resultSet_
									.getString(C_PROD_UNDER_ACCT_CODE)));
				}

				tplCurAcctPrmntInstrEntity.getData().setLastUpdDate(
						resultSet_.getTimestamp(C_LAST_UPD_DATE));

				tplCurAcctPrmntInstrEntity.getData().setLastUpdUserId(
						resultSet_.getString(C_LAST_UPD_USER_ID));

				//Casting para a atribuicao das colunas especificas
				tplCurAcctPrmntInstrEntityVO = (TplCurAcctPrmntInstrEntityVO) tplCurAcctPrmntInstrEntity
						.getData();

				tplCurAcctPrmntInstrEntityVO.setRecStatCode(resultSet_
						.getString(C_REC_STAT_CODE));

				tplCurAcctPrmntInstrEntityVO.setLastAuthDate(resultSet_
						.getTimestamp(C_LAST_AUTH_DATE));

				tplCurAcctPrmntInstrEntityVO.setLastAuthUserId(resultSet_
						.getString(C_LAST_AUTH_USER_ID));

				tplCurAcctPrmntEntities.add(tplCurAcctPrmntInstrEntity);
			}
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(),
					C_ERROR_INSTANTIATE_FROM_RESULT_SET, e);
		}
		return tplCurAcctPrmntEntities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrDAO#list(java.math.BigInteger,
	 *      java.math.BigInteger, java.lang.String, java.lang.String)
	 */
	public DataSet list(BigInteger curAcctNbr_, BigInteger custNbr_,
			BigInteger prmntInstrCode_, String prmntInstrInvstCurAcctInd_,
			String custFullName_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetDataSet rsds = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("SELECT ");
			query.append(C_ALIAS_TPL_CUSTOMER + "." + C_CUST_FULL_NAME_TEXT
					+ ", ");
			query.append(C_ALIAS_TPL_CUSTOMER + "." + C_CUST_NBR + ", ");
			query.append(C_ALIAS_TO3_PRODUCT_ACCOUNT + "." + C_CUR_ACCT_NBR
					+ ", ");
			query.append(" ( CASE WHEN " + C_ALIAS_TPL_PRMNT_INSTR_PRVT + "."
					+ C_PRMNT_INSTR_INVST_CUR_ACCT_IND + " = " + C_IND_YES);
			query.append(" THEN " + C_ALIAS_TBG_POINT_ACCT_INVST + "."
					+ C_INVST_CUR_ACCT_NBR);
			query.append(" ELSE '' END )AS " + C_INVST_CUR_ACCT_NBR + ", ");
			query.append(C_ALIAS_TPL_PRMNT_INSTR_PRVT + "."
					+ C_PRMNT_INSTR_INVST_CUR_ACCT_IND + " AS "
					+ C_INDICATOR_CODE + ", ");
			query.append(C_ALIAS_TPL_PRMNT_INSTR_PRVT + "."
					+ C_PRMNT_INSTR_CODE + ", ");
			query.append(C_ALIAS_TPL_PRMNT_INSTR_PRVT + "."
					+ C_PRMNT_INSTR_TEXT + ", ");
			query.append(C_ALIAS_TPL_CUR_ACCT_PRMNT + "." + C_PROD_ACCT_CODE
					+ ", ");
			query.append(C_ALIAS_TPL_CUR_ACCT_PRMNT + "."
					+ C_PROD_UNDER_ACCT_CODE);
			query.append(" FROM " + C_TPL_CUR_ACCT_PRMNT_INSTR + " "
					+ C_ALIAS_TPL_CUR_ACCT_PRMNT + ", ");
			query.append(C_TPL_PRMNT_INSTR_PRVT + " "
					+ C_ALIAS_TPL_PRMNT_INSTR_PRVT + ", ");
			query.append(C_TO3_PRODUCT_ACCOUNT + " "
					+ C_ALIAS_TO3_PRODUCT_ACCOUNT + ", ");
			query.append(C_TPL_CUSTOMER_PRVT + " " + C_ALIAS_TPL_CUSTOMER
					+ ", ");
			query.append(C_TBG_POINT_ACCT_INVST + " "
					+ C_ALIAS_TBG_POINT_ACCT_INVST);
			query.append(" WHERE " + C_ALIAS_TPL_CUR_ACCT_PRMNT + "."
					+ C_CUST_NBR + " = ");
			query.append(C_ALIAS_TPL_PRMNT_INSTR_PRVT + "." + C_CUST_NBR
					+ " AND ");
			query.append(C_ALIAS_TPL_CUR_ACCT_PRMNT + "." + C_PRMNT_INSTR_CODE
					+ " = ");
			query.append(C_ALIAS_TPL_PRMNT_INSTR_PRVT + "."
					+ C_PRMNT_INSTR_CODE + " AND ");
			query.append(C_ALIAS_TPL_CUR_ACCT_PRMNT + "." + C_PROD_ACCT_CODE
					+ " = ");
			query.append(C_ALIAS_TO3_PRODUCT_ACCOUNT + "." + C_PROD_ACCT_CODE
					+ " AND ");
			query.append(C_ALIAS_TPL_CUR_ACCT_PRMNT + "."
					+ C_PROD_UNDER_ACCT_CODE + " = ");
			query.append(C_ALIAS_TO3_PRODUCT_ACCOUNT + "."
					+ C_PROD_UNDER_ACCT_CODE + " AND ");
			query.append(C_ALIAS_TPL_CUSTOMER + "." + C_CUST_NBR + " = ");
			query.append(C_ALIAS_TPL_CUR_ACCT_PRMNT + "." + C_CUST_NBR
					+ " AND ");
			query.append(C_ALIAS_TO3_PRODUCT_ACCOUNT + "." + C_CUR_ACCT_NBR
					+ " = ");
			query.append(C_ALIAS_TBG_POINT_ACCT_INVST + "." + C_CUR_ACCT_NBR
					+ " (+) ");

			query.append(" AND " + C_ALIAS_TPL_CUR_ACCT_PRMNT + "."
					+ C_REC_STAT_CODE + " != '"
					+ BaseTplCurAcctPrmntInstrEntity.C_REC_STAT_CODE_INACTIVE
					+ "'");

			String criteria = "";

			if (custNbr_ != null && custNbr_.longValue() != 0) {
				criteria = criteria + C_ALIAS_TPL_CUR_ACCT_PRMNT + "."
						+ C_CUST_NBR + " = ? AND ";
			}

			if (prmntInstrCode_ != null && prmntInstrCode_.longValue() != 0) {
				criteria = criteria + C_ALIAS_TPL_CUR_ACCT_PRMNT + "."
						+ C_PRMNT_INSTR_CODE + " = ? AND ";
			}

			if (prmntInstrInvstCurAcctInd_ != null
					&& !prmntInstrInvstCurAcctInd_.equals("")) {
				criteria = criteria + "UPPER(" + C_ALIAS_TPL_PRMNT_INSTR_PRVT
						+ "." + C_PRMNT_INSTR_INVST_CUR_ACCT_IND + ") = ? AND ";
			}

			if (curAcctNbr_ != null && curAcctNbr_.longValue() != 0) {
				criteria = criteria + "( " + C_ALIAS_TO3_PRODUCT_ACCOUNT + "."
						+ C_CUR_ACCT_NBR + " = ?";
				criteria = criteria + " OR (" + C_ALIAS_TBG_POINT_ACCT_INVST
						+ "." + C_INVST_CUR_ACCT_NBR + " = ? AND ";
				criteria = criteria + C_ALIAS_TPL_PRMNT_INSTR_PRVT + "."
						+ C_PRMNT_INSTR_INVST_CUR_ACCT_IND + " = 'S')) AND ";

			}

			if (custFullName_ != null && !custFullName_.equals("")) {
				criteria = criteria + "UPPER(" + C_ALIAS_TPL_CUSTOMER + "."
						+ C_CUST_FULL_NAME_TEXT + ") like ? AND ";
			}

			if (criteria.length() > 0) {
				criteria = criteria.substring(0, criteria.length() - 5);
				query.append(" AND " + criteria);
			}

			query.append(" ORDER BY " + C_ALIAS_TPL_CUSTOMER + "."
					+ C_CUST_FULL_NAME_TEXT + ",");
			query.append(C_ALIAS_TO3_PRODUCT_ACCOUNT + "." + C_CUR_ACCT_NBR
					+ ",");
			query.append(C_ALIAS_TPL_PRMNT_INSTR_PRVT + "."
					+ C_PRMNT_INSTR_CODE);

			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			int count = 1;

			if (custNbr_ != null && custNbr_.longValue() != 0) {
				preparedStatement.setLong(count++, custNbr_.longValue());
			}

			if (prmntInstrCode_ != null && prmntInstrCode_.longValue() != 0) {
				preparedStatement.setLong(count++, prmntInstrCode_.longValue());
			}

			if (prmntInstrInvstCurAcctInd_ != null
					&& !prmntInstrInvstCurAcctInd_.equals("")) {
				preparedStatement.setString(count++, prmntInstrInvstCurAcctInd_
						.toUpperCase());
			}

			if (curAcctNbr_ != null && curAcctNbr_.longValue() != 0) {
				preparedStatement.setLong(count++, curAcctNbr_.longValue());
				preparedStatement.setLong(count++, curAcctNbr_.longValue());
			}
			if (custFullName_ != null && !custFullName_.equals("")) {
				preparedStatement.setString(count++, "%" + custFullName_.toUpperCase()
						+ "%");

			}

			resultSet = preparedStatement.executeQuery();
			preparedStatement.replaceParametersInQuery(query.toString());

			rsds = new ResultSetDataSet(resultSet);
			resultSet.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(),
					C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}

		String[] codeColumn = { C_INDICATOR_CODE };
		String[] nameColumn = { C_INDICATOR_TEXT };

		rsds.outerJoin(ODSConstraintDecoder.decodeIndicador(), codeColumn,
				codeColumn, nameColumn);

		return rsds;

	}
	
	public BaseTplCurAcctPrmntInstrEntity findById(BigInteger prmntCode_ )
	{
	  ManagedRdbConnection connection = null;
	  CitiStatement preparedStatement = null;
	  ResultSet resultSet = null;
	  StringBuffer query = new StringBuffer();

	  ArrayList tplCurAcctPrmntEntities;
	  BaseTplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity = null;

	  try
	  {
		connection = OracleODSDAOFactory.getConnection();
		query.append( "SELECT " );	
		
		query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "." + C_PRMNT_INSTR_CODE
					+ ", ");
		query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "." + C_PROD_ACCT_CODE
					+ ", ");
		query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "."
					+ C_PROD_UNDER_ACCT_CODE + ", ");
		query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "." + C_CUST_NBR + ", ");
		query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "." + C_LAST_UPD_USER_ID
					+ ", ");
		query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "." + C_LAST_UPD_DATE
					+ ", ");
		query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "." + C_LAST_AUTH_USER_ID
					+ ", ");
		query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "." + C_LAST_AUTH_DATE
					+ ", ");
		query.append(C_TPL_CUR_ACCT_PRMNT_INSTR + "." + C_REC_STAT_CODE);		
		query.append( " FROM " );
		query.append( C_TPL_CUR_ACCT_PRMNT_INSTR );
		query.append( " WHERE ");
		query.append( C_PRMNT_INSTR_CODE + " = ? " );
		
		int count = 1;
		preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

		preparedStatement.setLong(count++,prmntCode_.longValue() );
		
		preparedStatement.replaceParametersInQuery(query.toString());
		resultSet = preparedStatement.executeQuery();		

		tplCurAcctPrmntEntities = instantiateFromResultSet( resultSet );

		if ( tplCurAcctPrmntEntities.size() == 0 )
		{
		  throw new NoRowsReturnedException();
		}
		else if ( tplCurAcctPrmntEntities.size() > 1 )
		{
		  throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
		}
		else
		{
			tplCurAcctPrmntInstrEntity = ( BaseTplCurAcctPrmntInstrEntity ) tplCurAcctPrmntEntities.get( 0 );
		}

		resultSet.close();
	  }
	  catch ( SQLException e )
	  {
		throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
	  }
	  finally
	  {
		closeStatement( preparedStatement );
		closeConnection( connection );
	  }
	  return tplCurAcctPrmntInstrEntity;
	}

}