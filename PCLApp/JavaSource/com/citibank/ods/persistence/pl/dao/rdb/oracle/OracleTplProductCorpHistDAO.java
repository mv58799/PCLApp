package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplProductCorpEntity;
import com.citibank.ods.entity.pl.BaseTplProductEntity;
import com.citibank.ods.entity.pl.TplProductCorpHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplProductCorpHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProductCorpHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class OracleTplProductCorpHistDAO extends BaseOracleTplProductCorpDAO implements TplProductCorpHistDAO {
	/*
	 * Constante que identifica o nome da tabela de histórico
	 */
	private static final String C_TPL_PRODUCT_CORP_HIST = C_PL_SCHEMA + "TPL_PRODUCT_CORP_HIST";

	/*
	 * Campos específicos da tabela
	 */
	private String C_PROD_REF_DATE = "PROD_REF_DATE";

	private String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

	private String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

	private String C_REC_STAT_CODE = "REC_STAT_CODE";

	public TplProductCorpHistEntity insert(TplProductCorpHistEntity tplProductCorpHistEntity) throws UnexpectedException {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("INSERT INTO " + C_TPL_PRODUCT_CORP_HIST + " (");

			query.append(C_PROD_CODE + ", ");
			query.append(C_SYS_CODE + ", ");
			query.append(C_SYS_SEG_CODE + ", ");
			query.append(C_PROD_REF_DATE + ", ");

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
			
			query.append(" ) VALUES ( ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?)");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));

			int count = 1;

			TplProductCorpHistEntityVO tplProductCorpHistEntityVO = (TplProductCorpHistEntityVO) tplProductCorpHistEntity.getData();

			if (tplProductCorpHistEntity.getData().getProdCode() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductCorpHistEntity.getData().getSysCode() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getSysCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductCorpHistEntity.getData().getSysSegCode() != null) {
				preparedStatement.setLong(count++, tplProductCorpHistEntity.getData().getSysSegCode().longValue());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (tplProductCorpHistEntityVO.getProdRefDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpHistEntityVO.getProdRefDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			if (tplProductCorpHistEntity.getData().getProdEvnContrbDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpHistEntity.getData().getProdEvnContrbDate().getTime()));
			} else {
				preparedStatement.setTimestamp(count++, null);
			}

			//				query.append(C_PROD_FUND_PRFL_TYP + ", ");
			if (tplProductCorpHistEntity.getData().getProdFundPrflTyp() != null) {
				preparedStatement.setLong(count++, tplProductCorpHistEntity.getData().getProdFundPrflTyp().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CNPJ_NBR + ", ");
			if (tplProductCorpHistEntity.getData().getProdCnpjNbr() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdCnpjNbr());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CLOSE_TYP_CODE + ", ");
			if (tplProductCorpHistEntity.getData().getProdCloseTypCode() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdCloseTypCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_OPEN_TYP_CODE + ", ");
			if (tplProductCorpHistEntity.getData().getProdOpenTypCode() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdOpenTypCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_ORIG_NAME + ", ");
			if (tplProductCorpHistEntity.getData().getProdOrigName() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdOrigName());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_OPEN_EVN_DATE + ", ");
			if (tplProductCorpHistEntity.getData().getProdOpenEvnDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpHistEntity.getData().getProdOpenEvnDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_ADMIN_RATE + ", ");
			if (tplProductCorpHistEntity.getData().getProdAdminRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpHistEntity.getData().getProdAdminRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_PERFM_RATE + ", ");
			if (tplProductCorpHistEntity.getData().getProdPerfmRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpHistEntity.getData().getProdPerfmRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_PRTFINV_APPL_RATE + ", ");
			if (tplProductCorpHistEntity.getData().getProdPrtfinvApplRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpHistEntity.getData().getProdPrtfinvApplRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_EXIT_RATE + ", ");
			if (tplProductCorpHistEntity.getData().getProdExitRate() != null) {
				preparedStatement.setDouble(count++, tplProductCorpHistEntity.getData().getProdExitRate().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_QUOT_TYPE_CODE + ", ");
			if (tplProductCorpHistEntity.getData().getProdQuotTypeCode() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdQuotTypeCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CLOSE_TIME + ", ");
			if (tplProductCorpHistEntity.getData().getProdCloseTime() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpHistEntity.getData().getProdCloseTime().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_DEP_QUOT_DATE_TYPE + ", ");
			if (tplProductCorpHistEntity.getData().getProdDepQuotDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdDepQuotDateType());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_WTHDR_CR_DATE_TYPE + ", ");
			if (tplProductCorpHistEntity.getData().getProdWthdrCrDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdWthdrCrDateType());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MIN_STA_APPL_AMT + ", ");
			if (tplProductCorpHistEntity.getData().getProdMinStaApplAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpHistEntity.getData().getProdMinStaApplAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MOV_MIN_AMT + ", ");
			if (tplProductCorpHistEntity.getData().getProdMovMinAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpHistEntity.getData().getProdMovMinAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MIN_WTHDR_AMT + ", ");
			if (tplProductCorpHistEntity.getData().getProdMinWthdrAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpHistEntity.getData().getProdMinWthdrAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_HOLD_MIN_AMT + ", ");
			if (tplProductCorpHistEntity.getData().getProdHoldMinAmt() != null) {
				preparedStatement.setDouble(count++, tplProductCorpHistEntity.getData().getProdHoldMinAmt().doubleValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_GRACE_IND + ", ");
			if (tplProductCorpHistEntity.getData().getProdGraceInd() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdGraceInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_BAL_CLOSE_DATE + ", ");
			if (tplProductCorpHistEntity.getData().getProdBalCloseDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpHistEntity.getData().getProdBalCloseDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CVM_DIST_CODE + ", ");
			if (tplProductCorpHistEntity.getData().getProdCvmDistCode() != null) {
				preparedStatement.setLong(count++, tplProductCorpHistEntity.getData().getProdCvmDistCode().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_QUOT_CNDM_CODE + ", ");
			if (tplProductCorpHistEntity.getData().getProdQuotCndmCode() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdQuotCndmCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_RSTRN_CODE + ", ");
			if (tplProductCorpHistEntity.getData().getProdRstrnCode() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdRstrnCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_GAZETA_DCLR_FORM_CODE + ", ");
			if (tplProductCorpHistEntity.getData().getProdGazetaDclrFormCode() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdGazetaDclrFormCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_ANBID_FUND_CLASS_CODE + ", ");
			if (tplProductCorpHistEntity.getData().getAnbidFundClassCode() != null) {
				preparedStatement.setLong(count++, tplProductCorpHistEntity.getData().getAnbidFundClassCode().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_ANBID_DCLR_CODE + ", ");
			if (tplProductCorpHistEntity.getData().getProdAnbidDclrCode() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdAnbidDclrCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_CVM_TAX_CODE + ", ");
			if (tplProductCorpHistEntity.getData().getProdCvmTaxCode() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdCvmTaxCode());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_TERM_LONG_IND + ", ");
			if (tplProductCorpHistEntity.getData().getProdTermLongInd() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdTermLongInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_JRNL_DCLR_IND + ", ");
			if (tplProductCorpHistEntity.getData().getProdJrnlDclrInd() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdJrnlDclrInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_TAX_TYPE + ", ");
			if (tplProductCorpHistEntity.getData().getProdTaxType() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdTaxType());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_IRF_EXMP_IND + ", ");
			if (tplProductCorpHistEntity.getData().getProdIrfExmpInd() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdIrfExmpInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_IOF_EXMP_IND + ", ");
			if (tplProductCorpHistEntity.getData().getProdIofExmpInd() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdIofExmpInd());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_GRACE_TERM + ", ");
			if (tplProductCorpHistEntity.getData().getProdGraceTerm() != null) {
				preparedStatement.setLong(count++, tplProductCorpHistEntity.getData().getProdGraceTerm().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_PERFM_PAY_DATE + ", ");
			if (tplProductCorpHistEntity.getData().getProdPerfmPayDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpHistEntity.getData().getProdPerfmPayDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_BNCH_IX_TEXT + ", ");
			if (tplProductCorpHistEntity.getData().getProdBnchIxText() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdBnchIxText());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_QUOT_INIT_AMT + ", ");
			if (tplProductCorpHistEntity.getData().getProdQuotInitAmt() != null) {
				preparedStatement.setLong(count++, tplProductCorpHistEntity.getData().getProdQuotInitAmt().longValue());
			} else {
				preparedStatement.setString(count++, null);
			}

			//				query.append(C_PROD_MARK_TYPE_CODE + ", ");
			if (tplProductCorpHistEntity.getData().getProdMarkTypeCode() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdMarkTypeCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			//				query.append(C_PROD_APPL_LIQ_DATE_TYPE + ", ");
			if (tplProductCorpHistEntity.getData().getProdApplLiqDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdApplLiqDateType());
			} else {
				preparedStatement.setString(count++, null);
			}			

			//				query.append(C_PROD_WTHDR_LIQ_DATE_TYPE + ", ");
			if (tplProductCorpHistEntity.getData().getProdWthdrLiqDateType() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getProdWthdrLiqDateType());
			} else {
				preparedStatement.setString(count++, null);
			}			

			//					query.append(C_REC_STAT_CODE + ", ");
			if (tplProductCorpHistEntityVO.getRecStatCode() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntityVO.getRecStatCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			//					query.append(C_LAST_AUTH_DATE + ", ");
			if (tplProductCorpHistEntityVO.getLastAuthDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpHistEntityVO.getLastAuthDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//					query.append(C_LAST_AUTH_USER_ID + ", ");
			if (tplProductCorpHistEntityVO.getLastAuthUserId() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntityVO.getLastAuthUserId());
			} else {
				preparedStatement.setString(count++, null);
			}			
			
			//					query.append(C_LAST_UPD_DATE + ", ");
			if (tplProductCorpHistEntity.getData().getLastUpdDate() != null) {
				preparedStatement.setTimestamp(count++, new Timestamp(tplProductCorpHistEntity.getData().getLastUpdDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}

			//				    query.append(C_LAST_UPD_USER_ID + ", ");
			if (tplProductCorpHistEntity.getData().getLastUpdUserId() != null) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getLastUpdUserId());
			} else {
				preparedStatement.setString(count++, null);
			}			

			
	//Funds 
			

			
			if (tplProductCorpHistEntity.getData().getFundDistFormTypeCode()!= null && !"".equals(tplProductCorpHistEntity.getData().getFundDistFormTypeCode())) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getFundDistFormTypeCode());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpHistEntity.getData().getTermText()!= null ) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getTermText());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpHistEntity.getData().getStrategyStartDate()!= null ) {
				preparedStatement.setDate(count++, com.citibank.ods.common.util.Util.parseSqlDate(tplProductCorpHistEntity.getData().getStrategyStartDate()));
			} else {
				preparedStatement.setNull(count++, Types.DATE);
			}
			if (tplProductCorpHistEntity.getData().getStrategyCloseDate()!= null ) {
				preparedStatement.setDate(count++, com.citibank.ods.common.util.Util.parseSqlDate(tplProductCorpHistEntity.getData().getStrategyCloseDate()));
			} else {
				preparedStatement.setNull(count++, Types.DATE);
			}
			
			if (tplProductCorpHistEntity.getData().getApplicationStatCode()!= null ) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getApplicationStatCode());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpHistEntity.getData().getWthdrStatCode()!= null ) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getWthdrStatCode());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpHistEntity.getData().getPerfmRateText()!= null ) {
				preparedStatement.setString(count++, tplProductCorpHistEntity.getData().getPerfmRateText());
			} else {
				preparedStatement.setNull(count++, Types.VARCHAR);
			}
			if (tplProductCorpHistEntity.getData().getCloseDate()!= null ) {
				preparedStatement.setDate(count++, com.citibank.ods.common.util.Util.parseSqlDate(tplProductCorpHistEntity.getData().getCloseDate()));
			} else {
				preparedStatement.setNull(count++, Types.DATE);
			}
			
			
			preparedStatement.executeUpdate();
			preparedStatement.replaceParametersInQuery(query.toString());

			return tplProductCorpHistEntity;

		} catch (Exception e) {
			throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}

	}

	public DataSet list(String prodCode_, Date refDate_) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseTplProductCorpEntity find(BaseTplProductEntity baseTplProductEntity) throws UnexpectedException {
		// TODO Auto-generated method stub
		return null;
	}

}
