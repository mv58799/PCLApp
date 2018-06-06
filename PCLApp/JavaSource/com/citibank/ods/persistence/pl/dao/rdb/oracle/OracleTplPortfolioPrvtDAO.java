package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTbgOfficerEntity;
import com.citibank.ods.entity.pl.BaseTplPortfolioPrvtEntity;
import com.citibank.ods.entity.pl.TplPortfolioPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplPortfolioPrvtEntityVO;
import com.citibank.ods.persistence.pl.dao.TplPortfolioPrvtDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.common.logger.LoggerFactory;
import com.citibank.ods.common.logger.ApplicationLogger;
import com.citibank.ods.common.util.BaseConstraintDecoder;

/**
 * Implementação Oracle para DAO da tabela TPL_PORTFOLIO_PRVT
 * 
 * @author l.braga
 * @date 28/03/2007
 */
public class OracleTplPortfolioPrvtDAO extends BaseOracleTplPortfolioPrvtDAO
		implements TplPortfolioPrvtDAO {
	/**
	 * Procura um registro ou um conjunto de registro uma linha na tabela
	 * TPL_PORTFOLIO_PRVT de acordo com os parametos relacionados a opções de
	 * buscas na tela. Este método deve ser utilizado para consulta em lista com
	 * filtros.
	 * 
	 * @param BigIntegerportfBrchCode_
	 *            StringportfCode_ BigIntegerportfCostBusGrpCode_
	 *            BigIntegerportfCostDivCode_ BigIntegerportfCostPrftyCtrCode_
	 *            BigIntegerportfCostRegionCode_
	 *            BigIntegerportfCostRespOffcrCode_ StringportfCservSuplCode_
	 *            StringportfNameText_ BigIntegerportfNetwkSubGrpCode_
	 *            BigIntegerportfNetwkSubNetwkGrpCode_ BigIntegerportfOffcrNbr_
	 *            StringportfOpernType_ StringportfRegionCode_
	 *            StringportfSegCode_ BigIntegerportfSegSubCode_
	 *            DateportfStartDate_ StringportfStatCode_ StringportfUnitCode_
	 *            StringrecStatCode_*
	 * @returns dataSet_
	 * @throws UnexpectedException
	 * @author l.braga
	 * @date 28/03/2007
	 */

	public DataSet listPortfolio(String portfCode_, String portfNameText_,
			BigInteger portfOffcrNbr_, String offcrNameText_)
			throws UnexpectedException {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetDataSet rsds = null;
		StringBuffer query = new StringBuffer();

		try {
			LoggerFactory.initialize();

			ApplicationLogger applicationLogger = LoggerFactory.getApplicationLoggerInstance();

			connection = OracleODSDAOFactory.getConnection();
			query.append("SELECT ");
			query.append("PORT." + C_PORTF_CODE + ", ");
			query.append("PORT." + C_PORTF_NAME_TEXT + ", ");
			query.append("PORT." + C_PORTF_START_DATE + ", ");
			query.append("PORT." + C_PORTF_STAT_CODE + ", ");
			query.append("PORT." + C_PORTF_OFFCR_NBR + ", ");
			query.append("OFFIC." + BaseTbgOfficerEntity.C_OFFCR_NAME_TEXT
					+ ", ");
			query.append("PORT." + C_PORTF_OPERN_TYPE);
			query.append(" FROM ");
			query.append(C_PORTFOLIO_PRVT + " PORT, " + C_OFFICER + " OFFIC ");
			query.append(" WHERE ");
			query.append(" PORT." + C_PORTF_OFFCR_NBR + " = ");
			query.append(" OFFIC." + C_OFFCR_NBR + "(+)");			
			query.append("AND SUBSTR(PORT." + C_PORTF_CODE +", 5, 2) >= 80 ");
			query.append("AND SUBSTR(PORT." + C_PORTF_CODE +", 5, 2) <= 89 ");
			query.append("AND OFFIC." + C_OFFCR_CAT_CODE + " = '1' ");
			query.append("AND OFFIC." + C_OFFCR_STAT_CODE + " = '2' ");
			
			if (portfCode_ != null && !portfCode_.equals("")) {
				query.append(" AND UPPER(\"PORTF_CODE" + "\") like ? ");
			}

			if (portfNameText_ != null && !portfNameText_.equals("")) {
				query.append(" AND UPPER(\"PORTF_NAME_TEXT" + "\") like ? ");
			}
			if (portfOffcrNbr_ != null && !portfOffcrNbr_.equals("")) {
				query.append(" AND PORT.PORTF_OFFCR_NBR = ? ");
			}
			if (offcrNameText_ != null && !offcrNameText_.equals("")) {
				query.append(" AND UPPER(\"OFFCR_NAME_TEXT" + "\") like ? ");
			}
			query.append(" ORDER BY " + C_PORTF_NAME_TEXT);

			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			int count = 1;

			if (portfCode_ != null && !portfCode_.equals("")) {
				preparedStatement.setString(count++, "%" + portfCode_.toUpperCase()
						+ "%");
			}
			if (portfNameText_ != null && !portfNameText_.equals("")) {
				preparedStatement.setString(count++, "%" + portfNameText_.toUpperCase()
						+ "%");
			}
			if (portfOffcrNbr_ != null && !portfOffcrNbr_.equals("")) {
				preparedStatement.setLong(count++, portfOffcrNbr_.longValue());
			}
			if (offcrNameText_ != null && !offcrNameText_.equals("")) {
				preparedStatement.setString(count++, "%" + offcrNameText_.toUpperCase()
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
		
		return rsds;
	}

	/**
	 * Cria uma entidade representando um registro da tabela TPL_PORTFOLIO_PRVT
	 * com os dados do ID passado como parametro
	 * 
	 * @param entityKey_
	 * @throws UnexpectedException
	 * @author l.braga
	 * @date 28/03/2007
	 */

	public BaseTplPortfolioPrvtEntity find(
			BaseTplPortfolioPrvtEntity tplPortfolioPrvtEntity_)
			throws UnexpectedException {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuffer query = new StringBuffer();
		ArrayList tplPortfolioPrvtEntites;
		BaseTplPortfolioPrvtEntity portfolioPrvtEntity = null;
		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append(" SELECT ");
			query.append(C_TABLE_COLUMNS);
			query.append(" FROM ");
			query.append(C_PORTFOLIO_PRVT + " PORT");
			query.append(" WHERE ");
			query.append(" PORT.PORTF_CODE = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

			preparedStatement.setString(1, tplPortfolioPrvtEntity_.getData()
					.getPortfCode().toString());

			resultSet = preparedStatement.executeQuery();
			preparedStatement.replaceParametersInQuery(query.toString());

			tplPortfolioPrvtEntites = instantiateFromResultSet(resultSet);

			if (tplPortfolioPrvtEntites.size() == 0) {
				throw new NoRowsReturnedException();
			} else if (tplPortfolioPrvtEntites.size() > 1) {
				throw new UnexpectedException(C_ERROR_TOO_MANY_ROWS_RETURNED);
			} else {
				portfolioPrvtEntity = (BaseTplPortfolioPrvtEntity) tplPortfolioPrvtEntites
						.get(0);
			}
			return portfolioPrvtEntity;
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(),
					C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
	}

	private ArrayList instantiateFromResultSet(ResultSet resultSet_)
			throws UnexpectedException {
		TplPortfolioPrvtEntity tplPortfolioPrvtEntity;
		TplPortfolioPrvtEntityVO portfolioPrvtEntityVO;
		ArrayList TplPortfolioPrvtEntities = new ArrayList();
		try {
			while (resultSet_.next()) {
				tplPortfolioPrvtEntity = new TplPortfolioPrvtEntity();
				portfolioPrvtEntityVO = (TplPortfolioPrvtEntityVO) tplPortfolioPrvtEntity
						.getData();

				portfolioPrvtEntityVO.setPortfBrchCode(resultSet_
						.getString(C_PORTF_BRCH_CODE) != null ? new BigInteger(
						resultSet_.getString(C_PORTF_BRCH_CODE)) : null);

				portfolioPrvtEntityVO.setPortfCode(resultSet_
						.getString(C_PORTF_CODE));

				portfolioPrvtEntityVO
						.setPortfCostBusGrpCode(resultSet_
								.getString(C_PORTF_COST_BUS_GRP_CODE) != null ? new BigInteger(
								resultSet_.getString(C_PORTF_COST_BUS_GRP_CODE))
								: null);

				portfolioPrvtEntityVO
						.setPortfCostDivCode(resultSet_
								.getString(C_PORTF_COST_DIV_CODE) != null ? new BigInteger(
								resultSet_.getString(C_PORTF_COST_DIV_CODE))
								: null);

				portfolioPrvtEntityVO
						.setPortfCostPrftyCtrCode(resultSet_
								.getString(C_PORTF_COST_PRFTY_CTR_CODE) != null ? new BigInteger(
								resultSet_
										.getString(C_PORTF_COST_PRFTY_CTR_CODE))
								: null);

				portfolioPrvtEntityVO
						.setPortfCostRegionCode(resultSet_
								.getString(C_PORTF_COST_REGION_CODE) != null ? new BigInteger(
								resultSet_.getString(C_PORTF_COST_REGION_CODE))
								: null);

				portfolioPrvtEntityVO
						.setPortfCostRespOffcrCode(resultSet_
								.getString(C_PORTF_COST_RESP_OFFCR_CODE) != null ? new BigInteger(
								resultSet_
										.getString(C_PORTF_COST_RESP_OFFCR_CODE))
								: null);

				portfolioPrvtEntityVO.setPortfCservSuplCode(resultSet_
						.getString(C_PORTF_CSERV_SUPL_CODE));

				portfolioPrvtEntityVO.setPortfNameText(resultSet_
						.getString(C_PORTF_NAME_TEXT));

				portfolioPrvtEntityVO
						.setPortfNetwkSubGrpCode(resultSet_
								.getString(C_PORTF_NETWK_SUB_GRP_CODE) != null ? new BigInteger(
								resultSet_
										.getString(C_PORTF_NETWK_SUB_GRP_CODE))
								: null);

				portfolioPrvtEntityVO
						.setPortfNetwkSubNetwkGrpCode(resultSet_
								.getString(C_PORTF_NETWK_SUB_NETWK_GRP_CODE) != null ? new BigInteger(
								resultSet_
										.getString(C_PORTF_NETWK_SUB_NETWK_GRP_CODE))
								: null);

				portfolioPrvtEntityVO.setPortfOffcrNbr(resultSet_
						.getString(C_PORTF_OFFCR_NBR) != null ? new BigInteger(
						resultSet_.getString(C_PORTF_OFFCR_NBR)) : null);

				portfolioPrvtEntityVO.setPortfOpernType(resultSet_
						.getString(C_PORTF_OPERN_TYPE));

				portfolioPrvtEntityVO.setPortfRegionCode(resultSet_
						.getString(C_PORTF_REGION_CODE));

				portfolioPrvtEntityVO.setPortfSegCode(resultSet_
						.getString(C_PORTF_SEG_CODE));

				portfolioPrvtEntityVO
						.setPortfSegSubCode(resultSet_
								.getString(C_PORTF_SEG_SUB_CODE) != null ? new BigInteger(
								resultSet_.getString(C_PORTF_SEG_SUB_CODE))
								: null);

				portfolioPrvtEntityVO.setPortfStartDate(resultSet_
						.getDate(C_PORTF_START_DATE));

				portfolioPrvtEntityVO.setPortfStatCode(resultSet_
						.getString(C_PORTF_STAT_CODE));

				portfolioPrvtEntityVO.setPortfUnitCode(resultSet_
						.getString(C_PORTF_UNIT_CODE));

				portfolioPrvtEntityVO.setrecStatCode(resultSet_
						.getString(C_REC_STAT_CODE));

				TplPortfolioPrvtEntities.add(tplPortfolioPrvtEntity);
			}
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(),
					C_ERROR_INSTANTIATE_FROM_RESULT_SET, e);
		}
		return TplPortfolioPrvtEntities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.citibank.ods.persistence.pl.dao.TplPortfolioPrvtDAO#listPortfolio(java.lang.String,
	 *      java.math.BigInteger, java.lang.String)
	 */
	public DataSet listPortfolio(String portfNameText_,
			BigInteger portfOffcrNbr_, String offcrNameText_) {
		return null;
	}
}