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
import com.citibank.ods.entity.pl.TplProductCorpEntity;
import com.citibank.ods.entity.pl.TplProductEntity;
import com.citibank.ods.entity.pl.valueobject.TplProductCorpEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProductCorpDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class OracleTplProductCorpDAO extends BaseOracleTplProductCorpDAO implements TplProductCorpDAO {

	/*
	 * Nome da tabela
	 */
	private static final String C_TPL_PRODUCT_CORP = C_PL_SCHEMA + "TPL_PRODUCT_CORP";

	private String ALIAS_PROD_SUB_FAML = "SUB";

	private String ALIAS_PROD_MOV = "MOV";

	public BaseTplProductCorpEntity find(BaseTplProductEntity baseTplProductEntity) throws UnexpectedException {
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetDataSet rsds = null;
		StringBuffer query = new StringBuffer();
		ArrayList tplProductCorpEntities;
		TplProductCorpEntity tplProductCorpEntity = null;

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

			query.append(C_REC_STAT_CODE + ", ");
			query.append(C_LAST_AUTH_DATE + ", ");
			query.append(C_LAST_AUTH_USER_ID + ", ");
			query.append(C_LAST_UPD_DATE + ", ");
			query.append(C_LAST_UPD_USER_ID + ", ");

			query.append(C_FUND_DIST_FORM_TYPE_CODE+ ", ");
			query.append(C_TERM_TEXT+ " , ");
			query.append(C_STRATEGY_START_DATE+ " , ");
			query.append(C_STRATEGY_CLOSE_DATE+ ", ");
			query.append(C_APPLICATION_STAT_CODE+ ", ");
			query.append(C_WTHDR_STAT_CODE+ ", ");
			query.append(C_PERFM_RATE_TEXT+ ", ");
			query.append(C_CLOSE_DATE+ "  ");
			
			
			query.append(" FROM ");

			query.append(C_TPL_PRODUCT_CORP + " ");

			TplProductEntityVO tplProductEntityVO = (TplProductEntityVO) baseTplProductEntity.getData();

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
				tplProductCorpEntity = (TplProductCorpEntity) tplProductCorpEntities.get(0);
			}

			return tplProductCorpEntity;
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

		TplProductCorpEntity tplProductCorpEntity;
		ArrayList tplProductCorpEntities = new ArrayList();

		try {

			while (resultSet_.next()) {

				tplProductCorpEntity = new TplProductCorpEntity();

				tplProductCorpEntity.getData().setProdCode(resultSet_.getString(C_PROD_CODE));
				tplProductCorpEntity.getData().setSysCode(resultSet_.getString(C_SYS_CODE));
				tplProductCorpEntity.getData().setSysSegCode(new BigInteger(resultSet_.getString(C_SYS_SEG_CODE)));

				if (resultSet_.getDate(C_PROD_EVN_CONTRB_DATE) != null) {
					tplProductCorpEntity.getData().setProdEvnContrbDate(new Timestamp(resultSet_.getDate(C_PROD_EVN_CONTRB_DATE).getTime()));
				} else {
					tplProductCorpEntity.getData().setProdEvnContrbDate(null);
				}

				if (resultSet_.getString(C_PROD_FUND_PRFL_TYP) != null) {
					tplProductCorpEntity.getData().setProdFundPrflTyp(new BigInteger(resultSet_.getString(C_PROD_FUND_PRFL_TYP)));
				} else {
					tplProductCorpEntity.getData().setProdFundPrflTyp(null);
				}

				tplProductCorpEntity.getData().setProdCnpjNbr(resultSet_.getString(C_PROD_CNPJ_NBR));
				tplProductCorpEntity.getData().setProdCloseTypCode(resultSet_.getString(C_PROD_CLOSE_TYP_CODE));
				tplProductCorpEntity.getData().setProdOpenTypCode(resultSet_.getString(C_PROD_OPEN_TYP_CODE));
				tplProductCorpEntity.getData().setProdOrigName(resultSet_.getString(C_PROD_ORIG_NAME));

				if (resultSet_.getDate(C_PROD_OPEN_EVN_DATE) != null) {
					tplProductCorpEntity.getData().setProdOpenEvnDate(new Timestamp(resultSet_.getDate(C_PROD_OPEN_EVN_DATE).getTime()));
				} else {
					tplProductCorpEntity.getData().setProdOpenEvnDate(null);
				}

				if (resultSet_.getString(C_PROD_ADMIN_RATE) != null) {
					tplProductCorpEntity.getData().setProdAdminRate(new BigDecimal(resultSet_.getString(C_PROD_ADMIN_RATE)));
				} else {
					tplProductCorpEntity.getData().setProdAdminRate(null);
				}

				if (resultSet_.getString(C_PROD_PERFM_RATE) != null) {
					tplProductCorpEntity.getData().setProdPerfmRate(new BigDecimal(resultSet_.getString(C_PROD_PERFM_RATE)));
				} else {
					tplProductCorpEntity.getData().setProdPerfmRate(null);
				}

				if (resultSet_.getString(C_PROD_PRTFINV_APPL_RATE) != null) {
					tplProductCorpEntity.getData().setProdPrtfinvApplRate(new BigDecimal(resultSet_.getString(C_PROD_PRTFINV_APPL_RATE)));
				} else {
					tplProductCorpEntity.getData().setProdPrtfinvApplRate(null);
				}

				if (resultSet_.getString(C_PROD_EXIT_RATE) != null) {
					tplProductCorpEntity.getData().setProdExitRate(new BigDecimal(resultSet_.getString(C_PROD_EXIT_RATE)));
				} else {
					tplProductCorpEntity.getData().setProdExitRate(null);
				}

				tplProductCorpEntity.getData().setProdQuotTypeCode(resultSet_.getString(C_PROD_QUOT_TYPE_CODE));

				if (resultSet_.getDate(C_PROD_CLOSE_TIME) != null) {
					tplProductCorpEntity.getData().setProdCloseTime(resultSet_.getTimestamp(C_PROD_CLOSE_TIME));
				} else {
					tplProductCorpEntity.getData().setProdCloseTime(null);
				}

				tplProductCorpEntity.getData().setProdDepQuotDateType(resultSet_.getString(C_PROD_DEP_QUOT_DATE_TYPE));
				tplProductCorpEntity.getData().setProdWthdrCrDateType(resultSet_.getString(C_PROD_WTHDR_CR_DATE_TYPE));

				if (resultSet_.getString(C_PROD_MIN_STA_APPL_AMT) != null) {
					tplProductCorpEntity.getData().setProdMinStaApplAmt(new BigDecimal(resultSet_.getString(C_PROD_MIN_STA_APPL_AMT)));
				} else {
					tplProductCorpEntity.getData().setProdMinStaApplAmt(null);
				}

				if (resultSet_.getString(C_PROD_MOV_MIN_AMT) != null) {
					tplProductCorpEntity.getData().setProdMovMinAmt(new BigDecimal(resultSet_.getString(C_PROD_MOV_MIN_AMT)));
				} else {
					tplProductCorpEntity.getData().setProdMovMinAmt(null);
				}

				if (resultSet_.getString(C_PROD_MIN_WTHDR_AMT) != null) {
					tplProductCorpEntity.getData().setProdMinWthdrAmt(new BigDecimal(resultSet_.getString(C_PROD_MIN_WTHDR_AMT)));
				} else {
					tplProductCorpEntity.getData().setProdMinWthdrAmt(null);
				}

				if (resultSet_.getString(C_PROD_HOLD_MIN_AMT) != null) {
					tplProductCorpEntity.getData().setProdHoldMinAmt(new BigDecimal(resultSet_.getString(C_PROD_HOLD_MIN_AMT)));
				} else {
					tplProductCorpEntity.getData().setProdHoldMinAmt(null);
				}

				tplProductCorpEntity.getData().setProdGraceInd(resultSet_.getString(C_PROD_GRACE_IND));

				if (resultSet_.getDate(C_PROD_BAL_CLOSE_DATE) != null) {
					tplProductCorpEntity.getData().setProdBalCloseDate(new Timestamp(resultSet_.getDate(C_PROD_BAL_CLOSE_DATE).getTime()));
				} else {
					tplProductCorpEntity.getData().setProdBalCloseDate(null);
				}

				if (resultSet_.getString(C_PROD_CVM_DIST_CODE) != null) {
					tplProductCorpEntity.getData().setProdCvmDistCode(new BigInteger(resultSet_.getString(C_PROD_CVM_DIST_CODE)));
				} else {
					tplProductCorpEntity.getData().setProdCvmDistCode(null);
				}

				tplProductCorpEntity.getData().setProdQuotCndmCode(resultSet_.getString(C_PROD_QUOT_CNDM_CODE));
				tplProductCorpEntity.getData().setProdRstrnCode(resultSet_.getString(C_PROD_RSTRN_CODE));
				tplProductCorpEntity.getData().setProdGazetaDclrFormCode(resultSet_.getString(C_PROD_GAZETA_DCLR_FORM_CODE));

				if (resultSet_.getString(C_ANBID_FUND_CLASS_CODE) != null) {
					tplProductCorpEntity.getData().setAnbidFundClassCode(new BigInteger(resultSet_.getString(C_ANBID_FUND_CLASS_CODE)));
				} else {
					tplProductCorpEntity.getData().setAnbidFundClassCode(null);
				}

				tplProductCorpEntity.getData().setProdAnbidDclrCode(resultSet_.getString(C_PROD_ANBID_DCLR_CODE));
				tplProductCorpEntity.getData().setProdCvmTaxCode(resultSet_.getString(C_PROD_CVM_TAX_CODE));
				tplProductCorpEntity.getData().setProdTermLongInd(resultSet_.getString(C_PROD_TERM_LONG_IND));
				tplProductCorpEntity.getData().setProdJrnlDclrInd(resultSet_.getString(C_PROD_JRNL_DCLR_IND));
				tplProductCorpEntity.getData().setProdTaxType(resultSet_.getString(C_PROD_TAX_TYPE));
				tplProductCorpEntity.getData().setProdIrfExmpInd(resultSet_.getString(C_PROD_IRF_EXMP_IND));
				tplProductCorpEntity.getData().setProdIofExmpInd(resultSet_.getString(C_PROD_IOF_EXMP_IND));

				if (resultSet_.getString(C_PROD_GRACE_TERM) != null) {
					tplProductCorpEntity.getData().setProdGraceTerm(new BigInteger(resultSet_.getString(C_PROD_GRACE_TERM)));
				} else {
					tplProductCorpEntity.getData().setProdGraceTerm(null);
				}

				if (resultSet_.getDate(C_PROD_PERFM_PAY_DATE) != null) {
					tplProductCorpEntity.getData().setProdPerfmPayDate(new Timestamp(resultSet_.getDate(C_PROD_PERFM_PAY_DATE).getTime()));
				} else {
					tplProductCorpEntity.getData().setProdPerfmPayDate(null);
				}

				tplProductCorpEntity.getData().setProdBnchIxText(resultSet_.getString(C_PROD_BNCH_IX_TEXT));

				if (resultSet_.getString(C_PROD_QUOT_INIT_AMT) != null) {
					tplProductCorpEntity.getData().setProdQuotInitAmt(new BigDecimal(resultSet_.getString(C_PROD_QUOT_INIT_AMT)));
				} else {
					tplProductCorpEntity.getData().setProdQuotInitAmt(null);
				}

				tplProductCorpEntity.getData().setProdMarkTypeCode(resultSet_.getString(C_PROD_MARK_TYPE_CODE));

				tplProductCorpEntity.getData().setProdApplLiqDateType(resultSet_.getString(C_PROD_APPL_LIQ_DATE_TYPE));
				tplProductCorpEntity.getData().setProdWthdrLiqDateType(resultSet_.getString(C_PROD_WTHDR_LIQ_DATE_TYPE));
				
				tplProductCorpEntity.getData().setRecStatCode(resultSet_.getString(C_REC_STAT_CODE));
				
				if (resultSet_.getDate(C_LAST_AUTH_DATE) != null) {
					tplProductCorpEntity.getData().setLastUpdDate(new Timestamp(resultSet_.getDate(C_LAST_AUTH_DATE).getTime()));
				} else {
					tplProductCorpEntity.getData().setLastUpdDate(null);
				}
				
				tplProductCorpEntity.getData().setLastAuthUserId(resultSet_.getString(C_LAST_AUTH_USER_ID));
				
				if (resultSet_.getDate(C_LAST_AUTH_DATE) != null) {
					tplProductCorpEntity.getData().setLastAuthDate(new Timestamp(resultSet_.getDate(C_LAST_AUTH_DATE).getTime()));
				} else {
					tplProductCorpEntity.getData().setLastAuthDate(null);
				}
				
				tplProductCorpEntity.getData().setLastUpdUserId(resultSet_.getString(C_LAST_UPD_USER_ID));
				
				

				tplProductCorpEntity.getData().setFundDistFormTypeCode(resultSet_.getString(C_FUND_DIST_FORM_TYPE_CODE));
				tplProductCorpEntity.getData().setTermText(resultSet_.getString(C_TERM_TEXT));
				tplProductCorpEntity.getData().setStrategyStartDate(resultSet_.getDate(C_STRATEGY_START_DATE));
				tplProductCorpEntity.getData().setStrategyCloseDate(resultSet_.getDate(C_STRATEGY_CLOSE_DATE));
				tplProductCorpEntity.getData().setApplicationStatCode(resultSet_.getString(C_APPLICATION_STAT_CODE));
				tplProductCorpEntity.getData().setWthdrStatCode(resultSet_.getString(C_WTHDR_STAT_CODE));
				tplProductCorpEntity.getData().setPerfmRateText(resultSet_.getString(C_PERFM_RATE_TEXT));
				tplProductCorpEntity.getData().setCloseDate(resultSet_.getDate(C_CLOSE_DATE));
				
				tplProductCorpEntities.add(tplProductCorpEntity);

			}
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e);
		}
		return tplProductCorpEntities;
	}
	        
	        

	public void delete(TplProductCorpEntity tplProductCorpEntity_) throws UnexpectedException {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append(" UPDATE " + C_TPL_PRODUCT_CORP);
			query.append(" SET ");
			query.append(C_REC_STAT_CODE + "= ?");
			query.append(" WHERE " + "TRIM(" + C_PROD_CODE + ") = ? ");
			query.append(" AND " + C_SYS_CODE + " = ? ");
			query.append(" AND " + C_SYS_SEG_CODE + " = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));

			int count = 1;

			preparedStatement.setString(count++, ((TplProductCorpEntityVO) tplProductCorpEntity_.getData()).getRecStatCode());

			preparedStatement.setString(count++, tplProductCorpEntity_.getData().getProdCode());

			preparedStatement.setString(count++, tplProductCorpEntity_.getData().getSysCode());

			preparedStatement.setLong(count++, tplProductCorpEntity_.getData().getSysSegCode().longValue());

			preparedStatement.executeUpdate();
			preparedStatement.replaceParametersInQuery(query.toString());

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
	}

	public TplProductCorpEntity insert(TplProductCorpEntity tplProductCorpEntity_) throws UnexpectedException {
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("INSERT INTO " + C_TPL_PRODUCT_CORP + " (");

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
			

			query.append(C_REC_STAT_CODE + ", ");
			query.append(C_LAST_AUTH_DATE + ", ");
			query.append(C_LAST_AUTH_USER_ID + ", ");
			query.append(C_LAST_UPD_DATE + ", ");
			query.append(C_LAST_UPD_USER_ID + ", ");


			query.append(C_FUND_DIST_FORM_TYPE_CODE+ ", ");
			query.append(C_TERM_TEXT+ ", ");
			query.append(C_STRATEGY_START_DATE+ ", ");
			query.append(C_STRATEGY_CLOSE_DATE+ ", ");
			query.append(C_APPLICATION_STAT_CODE+ ", ");
			query.append(C_WTHDR_STAT_CODE+ ", ");
			query.append(C_PERFM_RATE_TEXT+ ", ");
			query.append(C_CLOSE_DATE+ " ");
			
			query.append(" ) VALUES ( ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));

			int count = 1;

			TplProductCorpEntityVO tplProductCorpEntityVO = (TplProductCorpEntityVO) tplProductCorpEntity_.getData();

			if (tplProductCorpEntityVO.getProdCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductCorpEntityVO.getSysCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getSysCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductCorpEntityVO.getSysSegCode() != null) {
				preparedStatement.setLong(count++, tplProductCorpEntityVO.getSysSegCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductCorpEntityVO.getProdEvnContrbDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpEntityVO.getProdEvnContrbDate().getTime()));
			} else {
				preparedStatement.setTimestamp(count++, null);
			}

			//				query.append(C_PROD_FUND_PRFL_TYP + ", ");
			if (tplProductCorpEntityVO.getProdFundPrflTyp() != null) {
				preparedStatement.setLong(count++, tplProductCorpEntityVO.getProdFundPrflTyp().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CNPJ_NBR + ", ");
			if (tplProductCorpEntityVO.getProdCnpjNbr() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdCnpjNbr());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CLOSE_TYP_CODE + ", ");
			if (tplProductCorpEntityVO.getProdCloseTypCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdCloseTypCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_OPEN_TYP_CODE + ", ");
			if (tplProductCorpEntityVO.getProdOpenTypCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdOpenTypCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_ORIG_NAME + ", ");
			if (tplProductCorpEntityVO.getProdOrigName() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdOrigName());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_OPEN_EVN_DATE + ", ");
			if (tplProductCorpEntityVO.getProdOpenEvnDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpEntityVO.getProdOpenEvnDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_ADMIN_RATE + ", ");
			if (tplProductCorpEntityVO.getProdAdminRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpEntityVO.getProdAdminRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_PERFM_RATE + ", ");
			if (tplProductCorpEntityVO.getProdPerfmRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpEntityVO.getProdPerfmRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_PRTFINV_APPL_RATE + ", ");
			if (tplProductCorpEntityVO.getProdPrtfinvApplRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpEntityVO.getProdPrtfinvApplRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_EXIT_RATE + ", ");
			if (tplProductCorpEntityVO.getProdExitRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpEntityVO.getProdExitRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_QUOT_TYPE_CODE + ", ");
			if (tplProductCorpEntityVO.getProdQuotTypeCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdQuotTypeCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CLOSE_TIME + ", ");
			if (tplProductCorpEntityVO.getProdCloseTime() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpEntityVO.getProdCloseTime().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_DEP_QUOT_DATE_TYPE + ", ");
			if (tplProductCorpEntityVO.getProdDepQuotDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdDepQuotDateType());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_WTHDR_CR_DATE_TYPE + ", ");
			if (tplProductCorpEntityVO.getProdWthdrCrDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdWthdrCrDateType());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MIN_STA_APPL_AMT + ", ");
			if (tplProductCorpEntityVO.getProdMinStaApplAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpEntityVO.getProdMinStaApplAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MOV_MIN_AMT + ", ");
			if (tplProductCorpEntityVO.getProdMovMinAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpEntityVO.getProdMovMinAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MIN_WTHDR_AMT + ", ");
			if (tplProductCorpEntityVO.getProdMinWthdrAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpEntityVO.getProdMinWthdrAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_HOLD_MIN_AMT + ", ");
			if (tplProductCorpEntityVO.getProdHoldMinAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpEntityVO.getProdHoldMinAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_GRACE_IND + ", ");
			if (tplProductCorpEntityVO.getProdGraceInd() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdGraceInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_BAL_CLOSE_DATE + ", ");
			if (tplProductCorpEntityVO.getProdBalCloseDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpEntityVO.getProdBalCloseDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CVM_DIST_CODE + ", ");
			if (tplProductCorpEntityVO.getProdCvmDistCode() != null) {
				preparedStatement.setLong(count++, tplProductCorpEntityVO.getProdCvmDistCode().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_QUOT_CNDM_CODE + ", ");
			if (tplProductCorpEntityVO.getProdQuotCndmCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdQuotCndmCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_RSTRN_CODE + ", ");
			if (tplProductCorpEntityVO.getProdRstrnCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdRstrnCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_GAZETA_DCLR_FORM_CODE + ", ");
			if (tplProductCorpEntityVO.getProdGazetaDclrFormCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdGazetaDclrFormCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_ANBID_FUND_CLASS_CODE + ", ");
			if (tplProductCorpEntityVO.getAnbidFundClassCode() != null) {
				preparedStatement.setLong(count++, tplProductCorpEntityVO.getAnbidFundClassCode().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_ANBID_DCLR_CODE + ", ");
			if (tplProductCorpEntityVO.getProdAnbidDclrCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdAnbidDclrCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CVM_TAX_CODE + ", ");
			if (tplProductCorpEntityVO.getProdCvmTaxCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdCvmTaxCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_TERM_LONG_IND + ", ");
			if (tplProductCorpEntityVO.getProdTermLongInd() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdTermLongInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_JRNL_DCLR_IND + ", ");
			if (tplProductCorpEntityVO.getProdJrnlDclrInd() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdJrnlDclrInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_TAX_TYPE + ", ");
			if (tplProductCorpEntityVO.getProdTaxType() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdTaxType());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_IRF_EXMP_IND + ", ");
			if (tplProductCorpEntityVO.getProdIrfExmpInd() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdIrfExmpInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_IOF_EXMP_IND + ", ");
			if (tplProductCorpEntityVO.getProdIofExmpInd() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdIofExmpInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_GRACE_TERM + ", ");
			if (tplProductCorpEntityVO.getProdGraceTerm() != null) {
				preparedStatement.setLong(count++, tplProductCorpEntityVO.getProdGraceTerm().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_PERFM_PAY_DATE + ", ");
			if (tplProductCorpEntityVO.getProdPerfmPayDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpEntityVO.getProdPerfmPayDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_BNCH_IX_TEXT + ", ");
			if (tplProductCorpEntityVO.getProdBnchIxText() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdBnchIxText());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_QUOT_INIT_AMT + ", ");
			if (tplProductCorpEntityVO.getProdQuotInitAmt() != null) {
				preparedStatement.setLong(count++, tplProductCorpEntityVO.getProdQuotInitAmt().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MARK_TYPE_CODE + ", ");
			if (tplProductCorpEntityVO.getProdMarkTypeCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdMarkTypeCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			//				query.append(C_PROD_APPL_LIQ_DATE_TYPE + ", ");
			if (tplProductCorpEntityVO.getProdApplLiqDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdApplLiqDateType());
			} else {
				preparedStatement.setString(count++, null);
			}			

			//				query.append(C_PROD_WTHDR_LIQ_DATE_TYPE + ", ");
			if (tplProductCorpEntityVO.getProdWthdrLiqDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdWthdrLiqDateType());
			} else {
				preparedStatement.setString(count++, null);
			}			

			//					query.append(C_REC_STAT_CODE + ", ");
			if (tplProductCorpEntityVO.getRecStatCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getRecStatCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			//					query.append(C_LAST_AUTH_DATE + ", ");
			if (tplProductCorpEntityVO.getLastAuthDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpEntityVO.getLastAuthDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//					query.append(C_LAST_AUTH_USER_ID + ", ");
			if (tplProductCorpEntityVO.getLastAuthUserId() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getLastAuthUserId());
			} else {
				preparedStatement.setString(count++, null);
			}			
			
			//					query.append(C_LAST_UPD_DATE + ", ");
			if (tplProductCorpEntityVO.getLastUpdDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpEntityVO.getLastUpdDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				    query.append(C_LAST_UPD_USER_ID + ", ");
			if (tplProductCorpEntityVO.getLastUpdUserId() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getLastUpdUserId());
			} else {
				preparedStatement.setString(count++, null);
			}			

			
			//Funds 
			

			
			if (tplProductCorpEntityVO.getFundDistFormTypeCode()!= null && !"".equals(tplProductCorpEntityVO.getFundDistFormTypeCode()) ) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getFundDistFormTypeCode());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpEntityVO.getTermText()!= null ) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getTermText());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpEntityVO.getStrategyStartDate()!= null ) {
				preparedStatement.setDate(count++, com.citibank.ods.common.util.Util.parseSqlDate(tplProductCorpEntityVO.getStrategyStartDate()));
			} else {
				preparedStatement.setNull(count++, Types.DATE);
			}
			if (tplProductCorpEntityVO.getStrategyCloseDate()!= null ) {
				preparedStatement.setDate(count++, com.citibank.ods.common.util.Util.parseSqlDate(tplProductCorpEntityVO.getStrategyCloseDate()));
			} else {
				preparedStatement.setNull(count++, Types.DATE);
			}
			
			if (tplProductCorpEntityVO.getApplicationStatCode()!= null ) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getApplicationStatCode());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpEntityVO.getWthdrStatCode()!= null ) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getWthdrStatCode());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpEntityVO.getPerfmRateText()!= null ) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getPerfmRateText());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpEntityVO.getCloseDate()!= null ) {
				preparedStatement.setDate(count++, com.citibank.ods.common.util.Util.parseSqlDate(tplProductCorpEntityVO.getCloseDate()));
			} else {
				preparedStatement.setNull(count++, Types.DATE);
			}
			
			preparedStatement.executeUpdate();
			preparedStatement.replaceParametersInQuery(query.toString());

			return tplProductCorpEntity_;

		} catch (Exception e) {
			throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
	}

	public void update(TplProductCorpEntity tplProductCorpEntity_) throws UnexpectedException {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("UPDATE " + C_TPL_PRODUCT_CORP);
			
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

			query.append(C_REC_STAT_CODE + " = ?, ");
			query.append(C_LAST_AUTH_DATE + " = ?, ");
			query.append(C_LAST_AUTH_USER_ID + " = ?, ");
			query.append(C_LAST_UPD_DATE + " = ?, ");
			query.append(C_LAST_UPD_USER_ID + " = ?, ");

			

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

			TplProductCorpEntityVO tplProductCorpEntityVO = (TplProductCorpEntityVO) tplProductCorpEntity_.getData();


			if (tplProductCorpEntityVO.getProdEvnContrbDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpEntityVO.getProdEvnContrbDate().getTime()));
			} else {
				preparedStatement.setTimestamp(count++, null);
			}

			//				query.append(C_PROD_FUND_PRFL_TYP + ", ");
			if (tplProductCorpEntityVO.getProdFundPrflTyp() != null) {
				preparedStatement.setLong(count++, tplProductCorpEntityVO.getProdFundPrflTyp().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CNPJ_NBR + ", ");
			if (tplProductCorpEntityVO.getProdCnpjNbr() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdCnpjNbr());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CLOSE_TYP_CODE + ", ");
			if (tplProductCorpEntityVO.getProdCloseTypCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdCloseTypCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_OPEN_TYP_CODE + ", ");
			if (tplProductCorpEntityVO.getProdOpenTypCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdOpenTypCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_ORIG_NAME + ", ");
			if (tplProductCorpEntityVO.getProdOrigName() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdOrigName());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_OPEN_EVN_DATE + ", ");
			if (tplProductCorpEntityVO.getProdOpenEvnDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpEntityVO.getProdOpenEvnDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_ADMIN_RATE + ", ");
			if (tplProductCorpEntityVO.getProdAdminRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpEntityVO.getProdAdminRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_PERFM_RATE + ", ");
			if (tplProductCorpEntityVO.getProdPerfmRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpEntityVO.getProdPerfmRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_PRTFINV_APPL_RATE + ", ");
			if (tplProductCorpEntityVO.getProdPrtfinvApplRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpEntityVO.getProdPrtfinvApplRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_EXIT_RATE + ", ");
			if (tplProductCorpEntityVO.getProdExitRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpEntityVO.getProdExitRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_QUOT_TYPE_CODE + ", ");
			if (tplProductCorpEntityVO.getProdQuotTypeCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdQuotTypeCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CLOSE_TIME + ", ");
			if (tplProductCorpEntityVO.getProdCloseTime() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpEntityVO.getProdCloseTime().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_DEP_QUOT_DATE_TYPE + ", ");
			if (tplProductCorpEntityVO.getProdDepQuotDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdDepQuotDateType());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_WTHDR_CR_DATE_TYPE + ", ");
			if (tplProductCorpEntityVO.getProdWthdrCrDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdWthdrCrDateType());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MIN_STA_APPL_AMT + ", ");
			if (tplProductCorpEntityVO.getProdMinStaApplAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpEntityVO.getProdMinStaApplAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MOV_MIN_AMT + ", ");
			if (tplProductCorpEntityVO.getProdMovMinAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpEntityVO.getProdMovMinAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MIN_WTHDR_AMT + ", ");
			if (tplProductCorpEntityVO.getProdMinWthdrAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpEntityVO.getProdMinWthdrAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_HOLD_MIN_AMT + ", ");
			if (tplProductCorpEntityVO.getProdHoldMinAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpEntityVO.getProdHoldMinAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_GRACE_IND + ", ");
			if (tplProductCorpEntityVO.getProdGraceInd() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdGraceInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_BAL_CLOSE_DATE + ", ");
			if (tplProductCorpEntityVO.getProdBalCloseDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpEntityVO.getProdBalCloseDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CVM_DIST_CODE + ", ");
			if (tplProductCorpEntityVO.getProdCvmDistCode() != null) {
				preparedStatement.setLong(count++, tplProductCorpEntityVO.getProdCvmDistCode().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_QUOT_CNDM_CODE + ", ");
			if (tplProductCorpEntityVO.getProdQuotCndmCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdQuotCndmCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_RSTRN_CODE + ", ");
			if (tplProductCorpEntityVO.getProdRstrnCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdRstrnCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_GAZETA_DCLR_FORM_CODE + ", ");
			if (tplProductCorpEntityVO.getProdGazetaDclrFormCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdGazetaDclrFormCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_ANBID_FUND_CLASS_CODE + ", ");
			if (tplProductCorpEntityVO.getAnbidFundClassCode() != null) {
				preparedStatement.setLong(count++, tplProductCorpEntityVO.getAnbidFundClassCode().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_ANBID_DCLR_CODE + ", ");
			if (tplProductCorpEntityVO.getProdAnbidDclrCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdAnbidDclrCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CVM_TAX_CODE + ", ");
			if (tplProductCorpEntityVO.getProdCvmTaxCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdCvmTaxCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_TERM_LONG_IND + ", ");
			if (tplProductCorpEntityVO.getProdTermLongInd() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdTermLongInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_JRNL_DCLR_IND + ", ");
			if (tplProductCorpEntityVO.getProdJrnlDclrInd() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdJrnlDclrInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_TAX_TYPE + ", ");
			if (tplProductCorpEntityVO.getProdTaxType() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdTaxType());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_IRF_EXMP_IND + ", ");
			if (tplProductCorpEntityVO.getProdIrfExmpInd() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdIrfExmpInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_IOF_EXMP_IND + ", ");
			if (tplProductCorpEntityVO.getProdIofExmpInd() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdIofExmpInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_GRACE_TERM + ", ");
			if (tplProductCorpEntityVO.getProdGraceTerm() != null) {
				preparedStatement.setLong(count++, tplProductCorpEntityVO.getProdGraceTerm().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_PERFM_PAY_DATE + ", ");
			if (tplProductCorpEntityVO.getProdPerfmPayDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpEntityVO.getProdPerfmPayDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_BNCH_IX_TEXT + ", ");
			if (tplProductCorpEntityVO.getProdBnchIxText() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdBnchIxText());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_QUOT_INIT_AMT + ", ");
			if (tplProductCorpEntityVO.getProdQuotInitAmt() != null) {
				preparedStatement.setLong(count++, tplProductCorpEntityVO.getProdQuotInitAmt().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MARK_TYPE_CODE + ", ");
			if (tplProductCorpEntityVO.getProdMarkTypeCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdMarkTypeCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			//				query.append(C_PROD_APPL_LIQ_DATE_TYPE + ", ");
			if (tplProductCorpEntityVO.getProdApplLiqDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdApplLiqDateType());
			} else {
				preparedStatement.setString(count++, null);
			}			

			//				query.append(C_PROD_WTHDR_LIQ_DATE_TYPE + ", ");
			if (tplProductCorpEntityVO.getProdWthdrLiqDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdWthdrLiqDateType());
			} else {
				preparedStatement.setString(count++, null);
			}	

			//					query.append(C_REC_STAT_CODE + ", ");
			if (tplProductCorpEntityVO.getRecStatCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getRecStatCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//					query.append(C_LAST_AUTH_DATE + ", ");
			if (tplProductCorpEntityVO.getLastAuthDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpEntityVO.getLastAuthDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//					query.append(C_LAST_AUTH_USER_ID + ", ");
			if (tplProductCorpEntityVO.getLastAuthUserId() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getLastAuthUserId());
			} else {
				preparedStatement.setString(count++, null);
			}			

			//					query.append(C_LAST_UPD_DATE + ", ");
			if (tplProductCorpEntityVO.getLastUpdDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpEntityVO.getLastUpdDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				    query.append(C_LAST_UPD_USER_ID + ", ");
			if (tplProductCorpEntityVO.getLastUpdUserId() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getLastUpdUserId());
			} else {
				preparedStatement.setString(count++, null);
			}			
			
			
			//Fund SUbscription

			if (tplProductCorpEntityVO.getFundDistFormTypeCode()!= null && !"".equals(tplProductCorpEntityVO.getFundDistFormTypeCode())) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getFundDistFormTypeCode());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpEntityVO.getTermText()!= null ) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getTermText());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpEntityVO.getStrategyStartDate()!= null ) {
				preparedStatement.setDate(count++, com.citibank.ods.common.util.Util.parseSqlDate(tplProductCorpEntityVO.getStrategyStartDate()));
			} else {
				preparedStatement.setNull(count++, Types.DATE);
			}
			if (tplProductCorpEntityVO.getStrategyCloseDate()!= null ) {
				preparedStatement.setDate(count++, com.citibank.ods.common.util.Util.parseSqlDate(tplProductCorpEntityVO.getStrategyCloseDate()));
			} else {
				preparedStatement.setNull(count++, Types.DATE);
			}
			
			if (tplProductCorpEntityVO.getApplicationStatCode()!= null ) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getApplicationStatCode());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpEntityVO.getWthdrStatCode()!= null ) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getWthdrStatCode());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpEntityVO.getPerfmRateText()!= null ) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getPerfmRateText());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpEntityVO.getCloseDate()!= null ) {
				preparedStatement.setDate(count++, com.citibank.ods.common.util.Util.parseSqlDate(tplProductCorpEntityVO.getCloseDate()));
			} else {
				preparedStatement.setNull(count++, Types.DATE);
			}
			
			if (tplProductCorpEntityVO.getProdCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getProdCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductCorpEntityVO.getSysCode() != null) {
				preparedStatement.setString(count++, tplProductCorpEntityVO.getSysCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductCorpEntityVO.getSysSegCode() != null) {
				preparedStatement.setLong(count++, tplProductCorpEntityVO.getSysSegCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			
			preparedStatement.replaceParametersInQuery(query.toString());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}

	}

	public boolean exists(TplProductEntity tplProductEntity_) throws UnexpectedException {

		boolean exists = true;

		try {
			this.find(tplProductEntity_);
		} catch (NoRowsReturnedException exception) {
			exists = false;
		}

		return exists;

	}

}
