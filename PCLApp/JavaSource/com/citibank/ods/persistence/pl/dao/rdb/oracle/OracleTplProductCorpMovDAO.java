package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplProductCorpEntity;
import com.citibank.ods.entity.pl.BaseTplProductEntity;
import com.citibank.ods.entity.pl.TplProductCorpMovEntity;
import com.citibank.ods.entity.pl.TplProductEntity;
import com.citibank.ods.entity.pl.valueobject.TplProductCorpMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProductCorpMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class OracleTplProductCorpMovDAO extends BaseOracleTplProductCorpDAO implements TplProductCorpMovDAO {

	/*
	 * Nome da tabela
	 */
	private static final String C_TPL_PRODUCT_CORP_MOV = C_PL_SCHEMA + "TPL_PRODUCT_CORP_MOV";

	private String ALIAS_PROD_SUB_FAML = "SUB";

	private String ALIAS_PROD_MOV = "MOV";

	/*
	 * Campos específicos da tabela
	 */
	private String C_OPERN_CODE = "OPERN_CODE";

	protected String C_OPERN_TEXT = "OPERN_TEXT";

	public TplProductCorpMovEntity delete(TplProductCorpMovEntity tplProductCorpMovEntity) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("DELETE FROM ");
			query.append(C_TPL_PRODUCT_CORP_MOV);
			query.append(" WHERE ");
			query.append(C_PROD_CODE + " = ?");
			query.append(" AND " + C_SYS_CODE + " = ?");
			query.append(" AND " + C_SYS_SEG_CODE + " = ?");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));

			int count = 1;

			preparedStatement.setString(count++, tplProductCorpMovEntity.getData().getProdCode());

			preparedStatement.setString(count++, tplProductCorpMovEntity.getData().getSysCode());

			preparedStatement.setLong(count++, tplProductCorpMovEntity.getData().getSysSegCode().longValue());

			preparedStatement.executeUpdate();
			preparedStatement.replaceParametersInQuery(query.toString());

			return tplProductCorpMovEntity;

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
	}

	public boolean exists(TplProductEntity tplProductEntity) {
		boolean exists = true;

		try {
			this.find(tplProductEntity);
		} catch (NoRowsReturnedException exception) {
			exists = false;
		}

		return exists;
	}

	public TplProductCorpMovEntity insert(TplProductCorpMovEntity tplProductCorpMovEntity) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("INSERT INTO " + C_TPL_PRODUCT_CORP_MOV + " (");

			query.append(C_PROD_CODE + ", ");
			query.append(C_SYS_CODE + ", ");
			query.append(C_SYS_SEG_CODE + ", ");

			query.append(C_PROD_EVN_CONTRB_DATE + ", ");
			query.append(C_PROD_FUND_PRFL_TYP + ", ");
			query.append(C_PROD_CNPJ_NBR + ", ");
			query.append(C_PROD_CLOSE_TYP_CODE + ", ");
			query.append(C_PROD_OPEN_TYP_CODE + ", ");
			query.append(C_PROD_ORIG_NAME + ", ");
			query.append(C_PROD_OPEN_EVN_DATE + ", ");
			query.append(C_PROD_ADMIN_RATE + ", ");
			query.append(C_PROD_PERFM_RATE + ", ");
			query.append(C_PROD_PRTFINV_APPL_RATE + ", ");
			query.append(C_PROD_EXIT_RATE + ", ");
			query.append(C_PROD_QUOT_TYPE_CODE + ", ");
			query.append(C_PROD_CLOSE_TIME + ", ");
			query.append(C_PROD_DEP_QUOT_DATE_TYPE + ", ");
			query.append(C_PROD_WTHDR_CR_DATE_TYPE + ", ");
			query.append(C_PROD_MIN_STA_APPL_AMT + ", ");
			query.append(C_PROD_MOV_MIN_AMT + ", ");
			query.append(C_PROD_MIN_WTHDR_AMT + ", ");
			query.append(C_PROD_HOLD_MIN_AMT + ", ");
			query.append(C_PROD_GRACE_IND + ", ");
			query.append(C_PROD_BAL_CLOSE_DATE + ", ");
			query.append(C_PROD_CVM_DIST_CODE + ", ");
			query.append(C_PROD_QUOT_CNDM_CODE + ", ");
			query.append(C_PROD_RSTRN_CODE + ", ");
			query.append(C_PROD_GAZETA_DCLR_FORM_CODE + ", ");
			query.append(C_ANBID_FUND_CLASS_CODE + ", ");
			query.append(C_PROD_ANBID_DCLR_CODE + ", ");
			query.append(C_PROD_CVM_TAX_CODE + ", ");
			query.append(C_PROD_TERM_LONG_IND + ", ");
			query.append(C_PROD_JRNL_DCLR_IND + ", ");
			query.append(C_PROD_TAX_TYPE + ", ");
			query.append(C_PROD_IRF_EXMP_IND + ", ");
			query.append(C_PROD_IOF_EXMP_IND + ", ");
			query.append(C_PROD_GRACE_TERM + ", ");
			query.append(C_PROD_PERFM_PAY_DATE + ", ");
			query.append(C_PROD_BNCH_IX_TEXT + ", ");
			query.append(C_PROD_QUOT_INIT_AMT + ", ");
			query.append(C_PROD_MARK_TYPE_CODE + ", ");
			query.append(C_PROD_APPL_LIQ_DATE_TYPE + ", ");
			query.append(C_PROD_WTHDR_LIQ_DATE_TYPE + ", ");			

			query.append(C_LAST_UPD_DATE + ", ");
			query.append(C_LAST_UPD_USER_ID + ", ");
			query.append(C_OPERN_CODE + ", ");
			
			query.append(C_FUND_DIST_FORM_TYPE_CODE+ ", ");
			query.append(C_TERM_TEXT+ ", ");
			query.append(C_STRATEGY_START_DATE+ ", ");
			query.append(C_STRATEGY_CLOSE_DATE+ ", ");
			query.append(C_APPLICATION_STAT_CODE+ ", ");
			query.append(C_WTHDR_STAT_CODE+ ", ");
			query.append(C_PERFM_RATE_TEXT+ ", ");
			query.append(C_CLOSE_DATE+ " ");

			query.append(" ) VALUES ( ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));

			int count = 1;

			TplProductCorpMovEntityVO tplProductCorpMovEntityVO = (TplProductCorpMovEntityVO) tplProductCorpMovEntity.getData();

			if (tplProductCorpMovEntityVO.getProdCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductCorpMovEntityVO.getSysCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getSysCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductCorpMovEntityVO.getSysSegCode() != null) {
				preparedStatement.setLong(count++, tplProductCorpMovEntityVO.getSysSegCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductCorpMovEntityVO.getProdEvnContrbDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpMovEntityVO.getProdEvnContrbDate().getTime()));
			} else {
				preparedStatement.setTimestamp(count++, null);
			}

			//				query.append(C_PROD_FUND_PRFL_TYP + ", ");
			if (tplProductCorpMovEntityVO.getProdFundPrflTyp() != null) {
				preparedStatement.setLong(count++, tplProductCorpMovEntityVO.getProdFundPrflTyp().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CNPJ_NBR + ", ");
			if (tplProductCorpMovEntityVO.getProdCnpjNbr() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdCnpjNbr());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CLOSE_TYP_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdCloseTypCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdCloseTypCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_OPEN_TYP_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdOpenTypCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdOpenTypCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_ORIG_NAME + ", ");
			if (tplProductCorpMovEntityVO.getProdOrigName() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdOrigName());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_OPEN_EVN_DATE + ", ");
			if (tplProductCorpMovEntityVO.getProdOpenEvnDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpMovEntityVO.getProdOpenEvnDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_ADMIN_RATE + ", ");
			if (tplProductCorpMovEntityVO.getProdAdminRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpMovEntityVO.getProdAdminRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_PERFM_RATE + ", ");
			if (tplProductCorpMovEntityVO.getProdPerfmRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpMovEntityVO.getProdPerfmRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_PRTFINV_APPL_RATE + ", ");
			if (tplProductCorpMovEntityVO.getProdPrtfinvApplRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpMovEntityVO.getProdPrtfinvApplRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_EXIT_RATE + ", ");
			if (tplProductCorpMovEntityVO.getProdExitRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpMovEntityVO.getProdExitRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_QUOT_TYPE_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdQuotTypeCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdQuotTypeCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CLOSE_TIME + ", ");
			if (tplProductCorpMovEntityVO.getProdCloseTime() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpMovEntityVO.getProdCloseTime().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_DEP_QUOT_DATE_TYPE + ", ");
			if (tplProductCorpMovEntityVO.getProdDepQuotDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdDepQuotDateType());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_WTHDR_CR_DATE_TYPE + ", ");
			if (tplProductCorpMovEntityVO.getProdWthdrCrDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdWthdrCrDateType());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MIN_STA_APPL_AMT + ", ");
			if (tplProductCorpMovEntityVO.getProdMinStaApplAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpMovEntityVO.getProdMinStaApplAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MOV_MIN_AMT + ", ");
			if (tplProductCorpMovEntityVO.getProdMovMinAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpMovEntityVO.getProdMovMinAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MIN_WTHDR_AMT + ", ");
			if (tplProductCorpMovEntityVO.getProdMinWthdrAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpMovEntityVO.getProdMinWthdrAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_HOLD_MIN_AMT + ", ");
			if (tplProductCorpMovEntityVO.getProdHoldMinAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpMovEntityVO.getProdHoldMinAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_GRACE_IND + ", ");
			if (tplProductCorpMovEntityVO.getProdGraceInd() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdGraceInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_BAL_CLOSE_DATE + ", ");
			if (tplProductCorpMovEntityVO.getProdBalCloseDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpMovEntityVO.getProdBalCloseDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CVM_DIST_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdCvmDistCode() != null) {
				preparedStatement.setLong(count++, tplProductCorpMovEntityVO.getProdCvmDistCode().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_QUOT_CNDM_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdQuotCndmCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdQuotCndmCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_RSTRN_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdRstrnCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdRstrnCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_GAZETA_DCLR_FORM_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdGazetaDclrFormCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdGazetaDclrFormCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_ANBID_FUND_CLASS_CODE + ", ");
			if (tplProductCorpMovEntityVO.getAnbidFundClassCode() != null) {
				preparedStatement.setLong(count++, tplProductCorpMovEntityVO.getAnbidFundClassCode().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_ANBID_DCLR_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdAnbidDclrCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdAnbidDclrCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CVM_TAX_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdCvmTaxCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdCvmTaxCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_TERM_LONG_IND + ", ");
			if (tplProductCorpMovEntityVO.getProdTermLongInd() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdTermLongInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_JRNL_DCLR_IND + ", ");
			if (tplProductCorpMovEntityVO.getProdJrnlDclrInd() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdJrnlDclrInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_TAX_TYPE + ", ");
			if (tplProductCorpMovEntityVO.getProdTaxType() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdTaxType());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_IRF_EXMP_IND + ", ");
			if (tplProductCorpMovEntityVO.getProdIrfExmpInd() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdIrfExmpInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_IOF_EXMP_IND + ", ");
			if (tplProductCorpMovEntityVO.getProdIofExmpInd() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdIofExmpInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_GRACE_TERM + ", ");
			if (tplProductCorpMovEntityVO.getProdGraceTerm() != null) {
				preparedStatement.setLong(count++, tplProductCorpMovEntityVO.getProdGraceTerm().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_PERFM_PAY_DATE + ", ");
			if (tplProductCorpMovEntityVO.getProdPerfmPayDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpMovEntityVO.getProdPerfmPayDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_BNCH_IX_TEXT + ", ");
			if (tplProductCorpMovEntityVO.getProdBnchIxText() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdBnchIxText());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_QUOT_INIT_AMT + ", ");
			if (tplProductCorpMovEntityVO.getProdQuotInitAmt() != null) {
				preparedStatement.setLong(count++, tplProductCorpMovEntityVO.getProdQuotInitAmt().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MARK_TYPE_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdMarkTypeCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdMarkTypeCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			//				query.append(C_PROD_APPL_LIQ_DATE_TYPE + ", ");
			if (tplProductCorpMovEntityVO.getProdApplLiqDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdApplLiqDateType());
			} else {
				preparedStatement.setString(count++, null);
			}			

			//				query.append(C_PROD_WTHDR_LIQ_DATE_TYPE + ", ");
			if (tplProductCorpMovEntityVO.getProdWthdrLiqDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdWthdrLiqDateType());
			} else {
				preparedStatement.setString(count++, null);
			}			

			//					query.append(C_LAST_UPD_DATE + ", ");
			if (tplProductCorpMovEntityVO.getLastUpdDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpMovEntityVO.getLastUpdDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				    query.append(C_LAST_UPD_USER_ID + ", ");
			if (tplProductCorpMovEntityVO.getLastUpdUserId() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getLastUpdUserId());
			} else {
				preparedStatement.setString(count++, null);
			}

			if (tplProductCorpMovEntityVO.getOpernCode() != null && !tplProductCorpMovEntityVO.getOpernCode().equals("")) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getOpernCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			
			//
			
			
			if (tplProductCorpMovEntityVO.getFundDistFormTypeCode()!= null && !"".equals(tplProductCorpMovEntityVO.getFundDistFormTypeCode())) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getFundDistFormTypeCode());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpMovEntityVO.getTermText()!= null ) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getTermText());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpMovEntityVO.getStrategyStartDate()!= null ) {
				preparedStatement.setDate(count++, com.citibank.ods.common.util.Util.parseSqlDate(tplProductCorpMovEntityVO.getStrategyStartDate()));
			} else {
				preparedStatement.setNull(count++, Types.DATE);
			}
			if (tplProductCorpMovEntityVO.getStrategyCloseDate()!= null ) {
				preparedStatement.setDate(count++, com.citibank.ods.common.util.Util.parseSqlDate(tplProductCorpMovEntityVO.getStrategyCloseDate()));
			} else {
				preparedStatement.setNull(count++, Types.DATE);
			}
			
			if (tplProductCorpMovEntityVO.getApplicationStatCode()!= null ) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getApplicationStatCode());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpMovEntityVO.getWthdrStatCode()!= null ) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getWthdrStatCode());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpMovEntityVO.getPerfmRateText()!= null ) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getPerfmRateText());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpMovEntityVO.getCloseDate()!= null ) {
				preparedStatement.setDate(count++, com.citibank.ods.common.util.Util.parseSqlDate(tplProductCorpMovEntityVO.getCloseDate()));
			} else {
				preparedStatement.setNull(count++, Types.DATE);
			}
			
			preparedStatement.executeUpdate();
			preparedStatement.replaceParametersInQuery(query.toString());

			return tplProductCorpMovEntity;

		} catch (Exception e) {
			throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}

	}

	public TplProductCorpMovEntity update(TplProductCorpMovEntity tplProductCorpMovEntity) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("UPDATE " + C_TPL_PRODUCT_CORP_MOV);

			query.append(" SET ");

			query.append(C_PROD_EVN_CONTRB_DATE + " = ?, ");
			query.append(C_PROD_FUND_PRFL_TYP + " = ?, ");
			query.append(C_PROD_CNPJ_NBR + " = ?, ");
			query.append(C_PROD_CLOSE_TYP_CODE + " = ?, ");
			query.append(C_PROD_OPEN_TYP_CODE + " = ?, ");
			query.append(C_PROD_ORIG_NAME + " = ?, ");
			query.append(C_PROD_OPEN_EVN_DATE + " = ?, ");
			query.append(C_PROD_ADMIN_RATE + " = ?, ");
			query.append(C_PROD_PERFM_RATE + " = ?, ");
			query.append(C_PROD_PRTFINV_APPL_RATE + " = ?, ");
			query.append(C_PROD_EXIT_RATE + " = ?, ");
			query.append(C_PROD_QUOT_TYPE_CODE + " = ?, ");
			query.append(C_PROD_CLOSE_TIME + " = ?, ");
			query.append(C_PROD_DEP_QUOT_DATE_TYPE + " = ?, ");
			query.append(C_PROD_WTHDR_CR_DATE_TYPE + " = ?, ");
			query.append(C_PROD_MIN_STA_APPL_AMT + " = ?, ");
			query.append(C_PROD_MOV_MIN_AMT + " = ?, ");
			query.append(C_PROD_MIN_WTHDR_AMT + " = ?, ");
			query.append(C_PROD_HOLD_MIN_AMT + " = ?, ");
			query.append(C_PROD_GRACE_IND + " = ?, ");
			query.append(C_PROD_BAL_CLOSE_DATE + " = ?, ");
			query.append(C_PROD_CVM_DIST_CODE + " = ?, ");
			query.append(C_PROD_QUOT_CNDM_CODE + " = ?, ");
			query.append(C_PROD_RSTRN_CODE + " = ?, ");
			query.append(C_PROD_GAZETA_DCLR_FORM_CODE + " = ?, ");
			query.append(C_ANBID_FUND_CLASS_CODE + " = ?, ");
			query.append(C_PROD_ANBID_DCLR_CODE + " = ?, ");
			query.append(C_PROD_CVM_TAX_CODE + " = ?, ");
			query.append(C_PROD_TERM_LONG_IND + " = ?, ");
			query.append(C_PROD_JRNL_DCLR_IND + " = ?, ");
			query.append(C_PROD_TAX_TYPE + " = ?, ");
			query.append(C_PROD_IRF_EXMP_IND + " = ?, ");
			query.append(C_PROD_IOF_EXMP_IND + " = ?, ");
			query.append(C_PROD_GRACE_TERM + " = ?, ");
			query.append(C_PROD_PERFM_PAY_DATE + " = ?, ");
			query.append(C_PROD_BNCH_IX_TEXT + " = ?, ");
			query.append(C_PROD_QUOT_INIT_AMT + " = ?, ");
			query.append(C_PROD_MARK_TYPE_CODE + " = ?, ");
			query.append(C_PROD_APPL_LIQ_DATE_TYPE + " = ?, ");
			query.append(C_PROD_WTHDR_LIQ_DATE_TYPE + " = ?, ");

			query.append(C_LAST_UPD_DATE + " = ?, ");
			query.append(C_LAST_UPD_USER_ID + " = ?, ");
			query.append(C_OPERN_CODE + "= ?, ");


			query.append(C_FUND_DIST_FORM_TYPE_CODE+ " = ? , ");
			query.append(C_TERM_TEXT+ " = ? , ");
			query.append(C_STRATEGY_START_DATE+ " = ? , ");
			query.append(C_STRATEGY_CLOSE_DATE+ " = ? , ");
			query.append(C_APPLICATION_STAT_CODE+ " = ? , ");
			query.append(C_WTHDR_STAT_CODE+ " = ? , ");
			query.append(C_PERFM_RATE_TEXT+ " = ? , ");
			query.append(C_CLOSE_DATE+ " = ?  ");
			
			query.append(" WHERE ");
			query.append(C_PROD_CODE + " = ? AND ");
			query.append(C_SYS_CODE + " = ? AND ");
			query.append(C_SYS_SEG_CODE + " = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));

			int count = 1;

			TplProductCorpMovEntityVO tplProductCorpMovEntityVO = (TplProductCorpMovEntityVO) tplProductCorpMovEntity.getData();

			if (tplProductCorpMovEntityVO.getProdEvnContrbDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpMovEntityVO.getProdEvnContrbDate().getTime()));
			} else {
				preparedStatement.setTimestamp(count++, null);
			}

			//				query.append(C_PROD_FUND_PRFL_TYP + ", ");
			if (tplProductCorpMovEntityVO.getProdFundPrflTyp() != null) {
				preparedStatement.setLong(count++, tplProductCorpMovEntityVO.getProdFundPrflTyp().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CNPJ_NBR + ", ");
			if (tplProductCorpMovEntityVO.getProdCnpjNbr() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdCnpjNbr());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CLOSE_TYP_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdCloseTypCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdCloseTypCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_OPEN_TYP_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdOpenTypCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdOpenTypCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_ORIG_NAME + ", ");
			if (tplProductCorpMovEntityVO.getProdOrigName() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdOrigName());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_OPEN_EVN_DATE + ", ");
			if (tplProductCorpMovEntityVO.getProdOpenEvnDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpMovEntityVO.getProdOpenEvnDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_ADMIN_RATE + ", ");
			if (tplProductCorpMovEntityVO.getProdAdminRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpMovEntityVO.getProdAdminRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_PERFM_RATE + ", ");
			if (tplProductCorpMovEntityVO.getProdPerfmRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpMovEntityVO.getProdPerfmRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_PRTFINV_APPL_RATE + ", ");
			if (tplProductCorpMovEntityVO.getProdPrtfinvApplRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpMovEntityVO.getProdPrtfinvApplRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_EXIT_RATE + ", ");
			if (tplProductCorpMovEntityVO.getProdExitRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpMovEntityVO.getProdExitRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_QUOT_TYPE_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdQuotTypeCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdQuotTypeCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CLOSE_TIME + ", ");
			if (tplProductCorpMovEntityVO.getProdCloseTime() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpMovEntityVO.getProdCloseTime().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_DEP_QUOT_DATE_TYPE + ", ");
			if (tplProductCorpMovEntityVO.getProdDepQuotDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdDepQuotDateType());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_WTHDR_CR_DATE_TYPE + ", ");
			if (tplProductCorpMovEntityVO.getProdWthdrCrDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdWthdrCrDateType());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MIN_STA_APPL_AMT + ", ");
			if (tplProductCorpMovEntityVO.getProdMinStaApplAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpMovEntityVO.getProdMinStaApplAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MOV_MIN_AMT + ", ");
			if (tplProductCorpMovEntityVO.getProdMovMinAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpMovEntityVO.getProdMovMinAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MIN_WTHDR_AMT + ", ");
			if (tplProductCorpMovEntityVO.getProdMinWthdrAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpMovEntityVO.getProdMinWthdrAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_HOLD_MIN_AMT + ", ");
			if (tplProductCorpMovEntityVO.getProdHoldMinAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpMovEntityVO.getProdHoldMinAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_GRACE_IND + ", ");
			if (tplProductCorpMovEntityVO.getProdGraceInd() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdGraceInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_BAL_CLOSE_DATE + ", ");
			if (tplProductCorpMovEntityVO.getProdBalCloseDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpMovEntityVO.getProdBalCloseDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CVM_DIST_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdCvmDistCode() != null) {
				preparedStatement.setLong(count++, tplProductCorpMovEntityVO.getProdCvmDistCode().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_QUOT_CNDM_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdQuotCndmCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdQuotCndmCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_RSTRN_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdRstrnCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdRstrnCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_GAZETA_DCLR_FORM_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdGazetaDclrFormCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdGazetaDclrFormCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_ANBID_FUND_CLASS_CODE + ", ");
			if (tplProductCorpMovEntityVO.getAnbidFundClassCode() != null) {
				preparedStatement.setLong(count++, tplProductCorpMovEntityVO.getAnbidFundClassCode().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_ANBID_DCLR_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdAnbidDclrCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdAnbidDclrCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CVM_TAX_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdCvmTaxCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdCvmTaxCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_TERM_LONG_IND + ", ");
			if (tplProductCorpMovEntityVO.getProdTermLongInd() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdTermLongInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_JRNL_DCLR_IND + ", ");
			if (tplProductCorpMovEntityVO.getProdJrnlDclrInd() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdJrnlDclrInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_TAX_TYPE + ", ");
			if (tplProductCorpMovEntityVO.getProdTaxType() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdTaxType());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_IRF_EXMP_IND + ", ");
			if (tplProductCorpMovEntityVO.getProdIrfExmpInd() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdIrfExmpInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_IOF_EXMP_IND + ", ");
			if (tplProductCorpMovEntityVO.getProdIofExmpInd() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdIofExmpInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_GRACE_TERM + ", ");
			if (tplProductCorpMovEntityVO.getProdGraceTerm() != null) {
				preparedStatement.setLong(count++, tplProductCorpMovEntityVO.getProdGraceTerm().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_PERFM_PAY_DATE + ", ");
			if (tplProductCorpMovEntityVO.getProdPerfmPayDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpMovEntityVO.getProdPerfmPayDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_BNCH_IX_TEXT + ", ");
			if (tplProductCorpMovEntityVO.getProdBnchIxText() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdBnchIxText());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_QUOT_INIT_AMT + ", ");
			if (tplProductCorpMovEntityVO.getProdQuotInitAmt() != null) {
				preparedStatement.setLong(count++, tplProductCorpMovEntityVO.getProdQuotInitAmt().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MARK_TYPE_CODE + ", ");
			if (tplProductCorpMovEntityVO.getProdMarkTypeCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdMarkTypeCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			//				query.append(C_PROD_APPL_LIQ_DATE_TYPE + ", ");
			if (tplProductCorpMovEntityVO.getProdApplLiqDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdApplLiqDateType());
			} else {
				preparedStatement.setString(count++, null);
			}			

			//				query.append(C_PROD_WTHDR_LIQ_DATE_TYPE + ", ");
			if (tplProductCorpMovEntityVO.getProdWthdrLiqDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdWthdrLiqDateType());
			} else {
				preparedStatement.setString(count++, null);
			}				

			//					query.append(C_LAST_UPD_DATE + ", ");
			if (tplProductCorpMovEntityVO.getLastUpdDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpMovEntityVO.getLastUpdDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				    query.append(C_LAST_UPD_USER_ID + ", ");
			if (tplProductCorpMovEntityVO.getLastUpdUserId() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getLastUpdUserId());
			} else {
				preparedStatement.setString(count++, null);
			}

			if (tplProductCorpMovEntityVO.getOpernCode() != null && !tplProductCorpMovEntityVO.getOpernCode().equals("")) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getOpernCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			//Fund SUbscription

			if (tplProductCorpMovEntityVO.getFundDistFormTypeCode()!= null && !"".equals(tplProductCorpMovEntityVO.getFundDistFormTypeCode())) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getFundDistFormTypeCode());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpMovEntityVO.getTermText()!= null ) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getTermText());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpMovEntityVO.getStrategyStartDate()!= null ) {
				preparedStatement.setDate(count++, com.citibank.ods.common.util.Util.parseSqlDate(tplProductCorpMovEntityVO.getStrategyStartDate()));
			} else {
				preparedStatement.setNull(count++, Types.DATE);
			}
			if (tplProductCorpMovEntityVO.getStrategyCloseDate()!= null ) {
				preparedStatement.setDate(count++, com.citibank.ods.common.util.Util.parseSqlDate(tplProductCorpMovEntityVO.getStrategyCloseDate()));
			} else {
				preparedStatement.setNull(count++, Types.DATE);
			}
			
			if (tplProductCorpMovEntityVO.getApplicationStatCode()!= null ) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getApplicationStatCode());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpMovEntityVO.getWthdrStatCode()!= null ) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getWthdrStatCode());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpMovEntityVO.getPerfmRateText()!= null ) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getPerfmRateText());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpMovEntityVO.getCloseDate()!= null ) {
				preparedStatement.setDate(count++, com.citibank.ods.common.util.Util.parseSqlDate(tplProductCorpMovEntityVO.getCloseDate()));
			} else {
				preparedStatement.setNull(count++, Types.DATE);
			}
			
			

			if (tplProductCorpMovEntityVO.getProdCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getProdCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductCorpMovEntityVO.getSysCode() != null) {
				preparedStatement.setString(count++, tplProductCorpMovEntityVO.getSysCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductCorpMovEntityVO.getSysSegCode() != null) {
				preparedStatement.setLong(count++, tplProductCorpMovEntityVO.getSysSegCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			preparedStatement.replaceParametersInQuery(query.toString());
			preparedStatement.executeUpdate();

			return tplProductCorpMovEntity;

		} catch (Exception e) {
			throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}

	}

	public BaseTplProductCorpEntity find(BaseTplProductEntity tplProductEntity) throws UnexpectedException {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetDataSet rsds = null;
		StringBuffer query = new StringBuffer();
		ArrayList tplProductCorpEntities;
		TplProductCorpMovEntity tplProductCorpMovEntity = null;

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("SELECT ");

			query.append(C_PROD_CODE + ", ");
			query.append(C_SYS_CODE + ", ");
			query.append(C_SYS_SEG_CODE + ", ");

			query.append(C_PROD_EVN_CONTRB_DATE + ", ");
			query.append(C_PROD_FUND_PRFL_TYP + ", ");
			query.append(C_PROD_CNPJ_NBR + ", ");
			query.append(C_PROD_CLOSE_TYP_CODE + ", ");
			query.append(C_PROD_OPEN_TYP_CODE + ", ");
			query.append(C_PROD_ORIG_NAME + ", ");
			query.append(C_PROD_OPEN_EVN_DATE + ", ");
			query.append(C_PROD_ADMIN_RATE + ", ");
			query.append(C_PROD_PERFM_RATE + ", ");
			query.append(C_PROD_PRTFINV_APPL_RATE + ", ");
			query.append(C_PROD_EXIT_RATE + ", ");
			query.append(C_PROD_QUOT_TYPE_CODE + ", ");
			query.append(C_PROD_CLOSE_TIME + ", ");
			query.append(C_PROD_DEP_QUOT_DATE_TYPE + ", ");
			query.append(C_PROD_WTHDR_CR_DATE_TYPE + ", ");
			query.append(C_PROD_MIN_STA_APPL_AMT + ", ");
			query.append(C_PROD_MOV_MIN_AMT + ", ");
			query.append(C_PROD_MIN_WTHDR_AMT + ", ");
			query.append(C_PROD_HOLD_MIN_AMT + ", ");
			query.append(C_PROD_GRACE_IND + ", ");
			query.append(C_PROD_BAL_CLOSE_DATE + ", ");
			query.append(C_PROD_CVM_DIST_CODE + ", ");
			query.append(C_PROD_QUOT_CNDM_CODE + ", ");
			query.append(C_PROD_RSTRN_CODE + ", ");
			query.append(C_PROD_GAZETA_DCLR_FORM_CODE + ", ");
			query.append(C_ANBID_FUND_CLASS_CODE + ", ");
			query.append(C_PROD_ANBID_DCLR_CODE + ", ");
			query.append(C_PROD_CVM_TAX_CODE + ", ");
			query.append(C_PROD_TERM_LONG_IND + ", ");
			query.append(C_PROD_JRNL_DCLR_IND + ", ");
			query.append(C_PROD_TAX_TYPE + ", ");
			query.append(C_PROD_IRF_EXMP_IND + ", ");
			query.append(C_PROD_IOF_EXMP_IND + ", ");
			query.append(C_PROD_GRACE_TERM + ", ");
			query.append(C_PROD_PERFM_PAY_DATE + ", ");
			query.append(C_PROD_BNCH_IX_TEXT + ", ");
			query.append(C_PROD_QUOT_INIT_AMT + ", ");
			query.append(C_PROD_MARK_TYPE_CODE + ", ");
			query.append(C_PROD_APPL_LIQ_DATE_TYPE + ", ");
			query.append(C_PROD_WTHDR_LIQ_DATE_TYPE + ", ");			

			query.append(C_LAST_UPD_DATE + ", ");
			query.append(C_LAST_UPD_USER_ID + ", ");


			query.append(C_FUND_DIST_FORM_TYPE_CODE+ ", ");
			query.append(C_TERM_TEXT+ " , ");
			query.append(C_STRATEGY_START_DATE+ " , ");
			query.append(C_STRATEGY_CLOSE_DATE+ ", ");
			query.append(C_APPLICATION_STAT_CODE+ ", ");
			query.append(C_WTHDR_STAT_CODE+ ", ");
			query.append(C_PERFM_RATE_TEXT+ ", ");
			query.append(C_CLOSE_DATE+ ",  ");
			
			
			query.append(C_OPERN_CODE + " ");

			query.append(" FROM ");

			query.append(C_TPL_PRODUCT_CORP_MOV + " ");

			TplProductMovEntityVO tplProductEntityVO = (TplProductMovEntityVO) tplProductEntity.getData();

			String criteria = "";

			if (tplProductEntityVO.getProdCode() != null && !tplProductEntityVO.getProdCode().equals("")) {
				criteria = criteria + "UPPER(TRIM(" + C_PROD_CODE + ")) = ? AND ";
			}

			if (tplProductEntityVO.getSysCode() != null && !tplProductEntityVO.getSysCode().equals("")) {
				criteria = criteria + C_SYS_CODE + " = ? AND ";
			}

			if (tplProductEntityVO.getSysSegCode() != null && !tplProductEntityVO.getSysSegCode().equals("")) {
				criteria = criteria + C_SYS_SEG_CODE + " = ? AND ";
			}

			if (criteria.length() > 0) {
				criteria = criteria.substring(0, criteria.length() - 5);
				query.append("	WHERE " + criteria);
			}

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));
			int count = 1;

			if (tplProductEntityVO.getProdCode() != null && !tplProductEntityVO.getProdCode().equals("")) {
				preparedStatement.setString(count++, tplProductEntityVO.getProdCode().toUpperCase().trim());
			}

			if (tplProductEntityVO.getSysCode() != null && !tplProductEntityVO.getSysCode().equals("")) {
				preparedStatement.setString(count++, tplProductEntityVO.getSysCode());
			}

			if (tplProductEntityVO.getSysSegCode() != null && tplProductEntityVO.getSysSegCode().longValue() != 0) {
				preparedStatement.setLong(count++, tplProductEntityVO.getSysSegCode().longValue());
			}

			resultSet = preparedStatement.executeQuery();
			preparedStatement.replaceParametersInQuery(query.toString());

			tplProductCorpEntities = instantiateFromResultSet(resultSet);

			if (tplProductCorpEntities.size() == 0) {
				throw new NoRowsReturnedException();
			} else if (tplProductCorpEntities.size() > 1) {
				throw new UnexpectedException(C_ERROR_TOO_MANY_ROWS_RETURNED);
			} else {
				tplProductCorpMovEntity = (TplProductCorpMovEntity) tplProductCorpEntities.get(0);
			}

			return tplProductCorpMovEntity;

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}

	}

	/*
	 * Retorna uma coleção de entities a partir do rs
	 */
	private ArrayList instantiateFromResultSet(ResultSet resultSet_) {

		TplProductCorpMovEntity tplProductCorpMovEntity;
		TplProductCorpMovEntityVO tplProductCorpMovEntityVO;
		ArrayList tplProductCorpEntities = new ArrayList();

		try {

			while (resultSet_.next()) {

				tplProductCorpMovEntity = new TplProductCorpMovEntity();

				tplProductCorpMovEntityVO = (TplProductCorpMovEntityVO) tplProductCorpMovEntity.getData();

				tplProductCorpMovEntity.getData().setProdCode(resultSet_.getString(C_PROD_CODE));
				tplProductCorpMovEntity.getData().setSysCode(resultSet_.getString(C_SYS_CODE));
				tplProductCorpMovEntity.getData().setSysSegCode(new BigInteger(resultSet_.getString(C_SYS_SEG_CODE)));

				if (resultSet_.getDate(C_PROD_EVN_CONTRB_DATE) != null) {
					tplProductCorpMovEntity.getData().setProdEvnContrbDate(new Timestamp(resultSet_.getDate(C_PROD_EVN_CONTRB_DATE).getTime()));
				} else {
					tplProductCorpMovEntity.getData().setProdEvnContrbDate(null);
				}

				if (resultSet_.getString(C_PROD_FUND_PRFL_TYP) != null) {
					tplProductCorpMovEntity.getData().setProdFundPrflTyp(new BigInteger(resultSet_.getString(C_PROD_FUND_PRFL_TYP)));
				} else {
					tplProductCorpMovEntity.getData().setProdFundPrflTyp(null);
				}

				tplProductCorpMovEntity.getData().setProdCnpjNbr(resultSet_.getString(C_PROD_CNPJ_NBR));
				tplProductCorpMovEntity.getData().setProdCloseTypCode(resultSet_.getString(C_PROD_CLOSE_TYP_CODE));
				tplProductCorpMovEntity.getData().setProdOpenTypCode(resultSet_.getString(C_PROD_OPEN_TYP_CODE));
				tplProductCorpMovEntity.getData().setProdOrigName(resultSet_.getString(C_PROD_ORIG_NAME));

				if (resultSet_.getDate(C_PROD_OPEN_EVN_DATE) != null) {
					tplProductCorpMovEntity.getData().setProdOpenEvnDate(new Timestamp(resultSet_.getDate(C_PROD_OPEN_EVN_DATE).getTime()));
				} else {
					tplProductCorpMovEntity.getData().setProdOpenEvnDate(null);
				}

				if (resultSet_.getString(C_PROD_ADMIN_RATE) != null) {
					tplProductCorpMovEntity.getData().setProdAdminRate(new BigDecimal(resultSet_.getString(C_PROD_ADMIN_RATE)));
				} else {
					tplProductCorpMovEntity.getData().setProdAdminRate(null);
				}

				if (resultSet_.getString(C_PROD_PERFM_RATE) != null) {
					tplProductCorpMovEntity.getData().setProdPerfmRate(new BigDecimal(resultSet_.getString(C_PROD_PERFM_RATE)));
				} else {
					tplProductCorpMovEntity.getData().setProdPerfmRate(null);
				}

				if (resultSet_.getString(C_PROD_PRTFINV_APPL_RATE) != null) {
					tplProductCorpMovEntity.getData().setProdPrtfinvApplRate(new BigDecimal(resultSet_.getString(C_PROD_PRTFINV_APPL_RATE)));
				} else {
					tplProductCorpMovEntity.getData().setProdPrtfinvApplRate(null);
				}

				if (resultSet_.getString(C_PROD_EXIT_RATE) != null) {
					tplProductCorpMovEntity.getData().setProdExitRate(new BigDecimal(resultSet_.getString(C_PROD_EXIT_RATE)));
				} else {
					tplProductCorpMovEntity.getData().setProdExitRate(null);
				}

				tplProductCorpMovEntity.getData().setProdQuotTypeCode(resultSet_.getString(C_PROD_QUOT_TYPE_CODE));

				if (resultSet_.getDate(C_PROD_CLOSE_TIME) != null) {
					tplProductCorpMovEntity.getData().setProdCloseTime(resultSet_.getTimestamp(C_PROD_CLOSE_TIME));
				} else {
					tplProductCorpMovEntity.getData().setProdCloseTime(null);
				}

				tplProductCorpMovEntity.getData().setProdDepQuotDateType(resultSet_.getString(C_PROD_DEP_QUOT_DATE_TYPE));
				tplProductCorpMovEntity.getData().setProdWthdrCrDateType(resultSet_.getString(C_PROD_WTHDR_CR_DATE_TYPE));

				if (resultSet_.getString(C_PROD_MIN_STA_APPL_AMT) != null) {
					tplProductCorpMovEntity.getData().setProdMinStaApplAmt(new BigDecimal(resultSet_.getString(C_PROD_MIN_STA_APPL_AMT)));
				} else {
					tplProductCorpMovEntity.getData().setProdMinStaApplAmt(null);
				}

				if (resultSet_.getString(C_PROD_MOV_MIN_AMT) != null) {
					tplProductCorpMovEntity.getData().setProdMovMinAmt(new BigDecimal(resultSet_.getString(C_PROD_MOV_MIN_AMT)));
				} else {
					tplProductCorpMovEntity.getData().setProdMovMinAmt(null);
				}

				if (resultSet_.getString(C_PROD_MIN_WTHDR_AMT) != null) {
					tplProductCorpMovEntity.getData().setProdMinWthdrAmt(new BigDecimal(resultSet_.getString(C_PROD_MIN_WTHDR_AMT)));
				} else {
					tplProductCorpMovEntity.getData().setProdMinWthdrAmt(null);
				}

				if (resultSet_.getString(C_PROD_HOLD_MIN_AMT) != null) {
					tplProductCorpMovEntity.getData().setProdHoldMinAmt(new BigDecimal(resultSet_.getString(C_PROD_HOLD_MIN_AMT)));
				} else {
					tplProductCorpMovEntity.getData().setProdHoldMinAmt(null);
				}

				tplProductCorpMovEntity.getData().setProdGraceInd(resultSet_.getString(C_PROD_GRACE_IND));

				if (resultSet_.getDate(C_PROD_BAL_CLOSE_DATE) != null) {
					tplProductCorpMovEntity.getData().setProdBalCloseDate(new Timestamp(resultSet_.getDate(C_PROD_BAL_CLOSE_DATE).getTime()));
				} else {
					tplProductCorpMovEntity.getData().setProdBalCloseDate(null);
				}

				if (resultSet_.getString(C_PROD_CVM_DIST_CODE) != null) {
					tplProductCorpMovEntity.getData().setProdCvmDistCode(new BigInteger(resultSet_.getString(C_PROD_CVM_DIST_CODE)));
				} else {
					tplProductCorpMovEntity.getData().setProdCvmDistCode(null);
				}

				tplProductCorpMovEntity.getData().setProdQuotCndmCode(resultSet_.getString(C_PROD_QUOT_CNDM_CODE));
				tplProductCorpMovEntity.getData().setProdRstrnCode(resultSet_.getString(C_PROD_RSTRN_CODE));
				tplProductCorpMovEntity.getData().setProdGazetaDclrFormCode(resultSet_.getString(C_PROD_GAZETA_DCLR_FORM_CODE));

				if (resultSet_.getString(C_ANBID_FUND_CLASS_CODE) != null) {
					tplProductCorpMovEntity.getData().setAnbidFundClassCode(new BigInteger(resultSet_.getString(C_ANBID_FUND_CLASS_CODE)));
				} else {
					tplProductCorpMovEntity.getData().setAnbidFundClassCode(null);
				}

				tplProductCorpMovEntity.getData().setProdAnbidDclrCode(resultSet_.getString(C_PROD_ANBID_DCLR_CODE));
				tplProductCorpMovEntity.getData().setProdCvmTaxCode(resultSet_.getString(C_PROD_CVM_TAX_CODE));
				tplProductCorpMovEntity.getData().setProdTermLongInd(resultSet_.getString(C_PROD_TERM_LONG_IND));
				tplProductCorpMovEntity.getData().setProdJrnlDclrInd(resultSet_.getString(C_PROD_JRNL_DCLR_IND));
				tplProductCorpMovEntity.getData().setProdTaxType(resultSet_.getString(C_PROD_TAX_TYPE));
				tplProductCorpMovEntity.getData().setProdIrfExmpInd(resultSet_.getString(C_PROD_IRF_EXMP_IND));
				tplProductCorpMovEntity.getData().setProdIofExmpInd(resultSet_.getString(C_PROD_IOF_EXMP_IND));

				if (resultSet_.getString(C_PROD_GRACE_TERM) != null) {
					tplProductCorpMovEntity.getData().setProdGraceTerm(new BigInteger(resultSet_.getString(C_PROD_GRACE_TERM)));
				} else {
					tplProductCorpMovEntity.getData().setProdGraceTerm(null);
				}

				if (resultSet_.getDate(C_PROD_PERFM_PAY_DATE) != null) {
					tplProductCorpMovEntity.getData().setProdPerfmPayDate(new Timestamp(resultSet_.getDate(C_PROD_PERFM_PAY_DATE).getTime()));
				} else {
					tplProductCorpMovEntity.getData().setProdPerfmPayDate(null);
				}

				tplProductCorpMovEntity.getData().setProdBnchIxText(resultSet_.getString(C_PROD_BNCH_IX_TEXT));

				if (resultSet_.getString(C_PROD_QUOT_INIT_AMT) != null) {
					tplProductCorpMovEntity.getData().setProdQuotInitAmt(new BigDecimal(resultSet_.getString(C_PROD_QUOT_INIT_AMT)));
				} else {
					tplProductCorpMovEntity.getData().setProdQuotInitAmt(null);
				}

				tplProductCorpMovEntity.getData().setProdMarkTypeCode(resultSet_.getString(C_PROD_MARK_TYPE_CODE));
				
				tplProductCorpMovEntity.getData().setProdApplLiqDateType(resultSet_.getString(C_PROD_APPL_LIQ_DATE_TYPE));
				tplProductCorpMovEntity.getData().setProdWthdrLiqDateType(resultSet_.getString(C_PROD_WTHDR_LIQ_DATE_TYPE));
				

				if (resultSet_.getDate(C_LAST_UPD_DATE) != null) {
					tplProductCorpMovEntity.getData().setLastUpdDate(new Timestamp(resultSet_.getDate(C_LAST_UPD_DATE).getTime()));
				} else {
					tplProductCorpMovEntity.getData().setLastUpdDate(null);
				}

				tplProductCorpMovEntity.getData().setLastAuthUserId(resultSet_.getString(C_LAST_UPD_USER_ID));

				tplProductCorpMovEntityVO.setOpernCode(resultSet_.getString(C_OPERN_CODE));

				

				tplProductCorpMovEntity.getData().setFundDistFormTypeCode(resultSet_.getString(C_FUND_DIST_FORM_TYPE_CODE));
				tplProductCorpMovEntity.getData().setTermText(resultSet_.getString(C_TERM_TEXT));
				tplProductCorpMovEntity.getData().setStrategyStartDate(resultSet_.getDate(C_STRATEGY_START_DATE));
				tplProductCorpMovEntity.getData().setStrategyCloseDate(resultSet_.getDate(C_STRATEGY_CLOSE_DATE));
				tplProductCorpMovEntity.getData().setApplicationStatCode(resultSet_.getString(C_APPLICATION_STAT_CODE));
				tplProductCorpMovEntity.getData().setWthdrStatCode(resultSet_.getString(C_WTHDR_STAT_CODE));
				tplProductCorpMovEntity.getData().setPerfmRateText(resultSet_.getString(C_PERFM_RATE_TEXT));
				tplProductCorpMovEntity.getData().setCloseDate(resultSet_.getDate(C_CLOSE_DATE));
				
				
				tplProductCorpEntities.add(tplProductCorpMovEntity);

			}
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e);
		}
		return tplProductCorpEntities;
	}

}
