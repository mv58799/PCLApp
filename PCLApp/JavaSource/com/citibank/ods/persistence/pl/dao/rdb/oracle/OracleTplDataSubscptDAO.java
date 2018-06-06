package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.logger.ApplicationLogger;
import com.citibank.ods.common.util.TableTypeEnum;
import com.citibank.ods.entity.pl.valueobject.TplDataSubscptEntityVO;
import com.citibank.ods.modules.product.fundsubscription.form.valueobject.FundSubscriptionImportHistoryVO;
import com.citibank.ods.modules.product.fundsubscription.form.valueobject.FundSubscriptionInvestorDataVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

public class OracleTplDataSubscptDAO extends BaseOracleTplDataSubscptDAO {

	

	public String getPerfGrb(Long acct){
		 ManagedRdbConnection connection = null;
		    PreparedStatement ps = null;
		    StringBuffer query = new StringBuffer();

		    try
		    {
		      connection = OracleODSDAOFactory.getConnection();
		      query.append("select  " ) 
		    		.append("CASE " ) 
		      		.append("  WHEN (select count(rpo.RELTN_NBR) FROM PL.vpl_relation_prvt_only rpo WHERE rpo.RELTN_NBR = TR.RELTN_NBR) > 0 THEN  " ) 
		      		.append("  TR.RELTN_RISK_LEVEL_CODE " ) 
		      		.append("  ELSE " ) 
		      		.append("  c.CUST_RDIP_CODE " ) 
		      		.append(" END AS LEVEL_RISK " ) 
		      		.append("from BG.TBG_CURRENT_ACCOUNT CA " ) 
		      		.append("JOIN BG.tbg_relationship TR " ) 
		      		.append("ON ca.acct_reltn_nbr = TR.RELTN_NBR " ) 
		      		.append("LEFT JOIN BG.tbg_data_additional_cust c  " ) 
		      		.append("ON c.CUST_NBR = TR.RELTN_CUST_1_NBR " ) 
		      		.append("WHERE ca.acct_nbr = ? ");
		      ps = connection.prepareStatement(query.toString());
		      ps.setLong(1, acct);
		      
		      log(query.toString(), acct);
		      ResultSet rs = ps.executeQuery();
		      if (rs.next()){
		    	  return rs.getString("LEVEL_RISK");
		      }
		      return null;
		    }
		    catch ( SQLException e )
		    {
		      throw new UnexpectedException( e.getErrorCode(),
		                                     C_ERROR_EXECUTING_STATEMENT, e );
		    }
		    finally
		    {
		      closeStatement( ps );
		      closeConnection( connection );
		    }
	}
	
	public static void log(String str, Object... var){
		if (str==null)
		return;
		
		try {
			str = str.replaceAll("?", "%s");
			
			ApplicationLogger.getInstance().info(String.format(str, var));
		}catch(Exception e ){
			ApplicationLogger.getInstance().info(str);
		}
	}
	
	
	public String getInvestorName(Long cpf){
		 ManagedRdbConnection connection = null;
		    PreparedStatement ps = null;
		    StringBuffer query = new StringBuffer();

		    try
		    {
		      connection = OracleODSDAOFactory.getConnection();
		      query.append("SELECT cust_full_name_text from BG.tbg_customer " ) 
		      		.append("WHERE cust_cpf_cgc_nbr = ? ");
		      ps = connection.prepareStatement(query.toString());
		      ps.setLong(1, cpf);
		      log(query.toString(), cpf);
		      ResultSet rs = ps.executeQuery();
		      if (rs.next()){
		    	  return rs.getString("cust_full_name_text");
		      }
		      return null;
		    }
		    catch ( SQLException e )
		    {
		      throw new UnexpectedException( e.getErrorCode(),
		                                     C_ERROR_EXECUTING_STATEMENT, e );
		    }
		    finally
		    {
		      closeStatement( ps );
		      closeConnection( connection );
		    }
	}
	public FundSubscriptionInvestorDataVO defInvestorNameAndCode(Long cpf){
		
		 ManagedRdbConnection connection = null;
		    PreparedStatement ps = null;
		    StringBuffer query = new StringBuffer();

		    try
		    {
		      connection = OracleODSDAOFactory.getConnection();
		      query.append("SELECT CUST_NBR, cust_full_name_text from BG.tbg_customer " ) 
		      		.append("WHERE cust_cpf_cgc_nbr = ? ");
		      ps = connection.prepareStatement(query.toString());
		      ps.setLong(1,cpf);
		      log(query.toString(), cpf);
		      ResultSet rs = ps.executeQuery();
		      if (rs.next()){
		    	  FundSubscriptionInvestorDataVO vo = new FundSubscriptionInvestorDataVO();
		    	  vo.setInvestorName(rs.getString("cust_full_name_text"));
		    	  vo.setInvestorCode(rs.getLong("CUST_NBR"));
		    	  return vo;
		      }
		      return null;
		    }
		    catch ( SQLException e )
		    {
		      throw new UnexpectedException( e.getErrorCode(),
		                                     C_ERROR_EXECUTING_STATEMENT, e );
		    }
		    finally
		    {
		      closeStatement( ps );
		      closeConnection( connection );
		    }
	}

	public void insert( TplDataSubscptEntityVO vo )
	  {
		insert(vo, true);
	  }
	
	public void insert( TplDataSubscptEntityVO vo, boolean autoCommit )
	  {
	    ManagedRdbConnection connection = null;
	    PreparedStatement ps = null;
	    StringBuffer query = new StringBuffer();

	    try
	    {
	      connection = getConnection();
	      connection.setAutoCommit(autoCommit);
	      query.append( "INSERT INTO PL.TPL_DATA_SUBSCPT").append(vo.getTableType().getSufixo())
	      		.append("(")
	      		.append( "ENROLLMENT_NBR,PROD_IMP_TYPE_CODE, ")
	      		.append("FUND_NAME_TEXT, ")
	    		.append( "ACCT_NBR, ")
	      		.append("FILE_IMPORT_CODE, ")
	      		.append("SUBSCPT_OPEN_DATE, ");
	      		query.append("SUBJECT_TEXT, " );
	      		query.append("EVENT_TEXT, " );
	      		
	      		if (!vo.getTableType().equals(TableTypeEnum.MOVEMENT))
	      			query.append("SUBJECT_SOL_DATE, ");
	      		else 
	      			query.append("SUBSCPT_SOL_DATE, ");
	      		
	      		query.append("SUBJECT_PHASE_TEXT, ")
	      		.append("PHASE_RSLTN_DATE, " )
	      		.append("COMMENT_TEXT, ");
	      		if (!vo.getTableType().equals(TableTypeEnum.MOVEMENT))
	      			query.append("CNFRM_INCPAT_IND, ");
	      		else 
	      			query.append("INCPAT_CNFRM_IND, ");
	      			
	      		query.append("RESERVE_IND, ")
	      		.append("TRAINING_TERM_IND, ")
	      		.append("DOCUMENT_CHECK_IND, ");
	      		if (!vo.getTableType().equals(TableTypeEnum.MOVEMENT))
	      			query.append("STOCK_TRF_ORDER_IND, ");
	      		else 
	      			query.append("TRF_ORDER_IND, ");
	      		
	      		query.append("BKR_CODE_TEXT, ")
	      		.append("PROD_KNWLG_TEXT, ")
	      		.append("PLYR_CPF_NBR, ")
	      		.append("PROD_RISK_LVL_QTY, ")
	      		.append("INCPAT_APPRV_NAME_TEXT, ")
	      		.append("PROD_NAME_TEXT, ");
	      		if (!vo.getTableType().equals(TableTypeEnum.MOVEMENT))
	      			query.append("TRANSACTION_TYPE_TEXT, ");
	      		else 
	      			query.append("TRANSACTION_TYPE, ");
	      		
	      		query.append("LAST_APPRV_DATE, ")
	      		.append("LAST_APPRV_USER_ID, ");
	      		if (!vo.getTableType().equals(TableTypeEnum.EFFECTIVE))
	      			query.append("OPERN_TYPE_CODE,");
	      		
	      		query.append("LAST_UPD_DATE, ");
	      		query.append("LAST_UPD_USER_ID,   ADD_DIFF_AMT, INVESTMENT_AMT )" );
	      query.append( " VALUES " );
	      if (!vo.getTableType().equals(TableTypeEnum.EFFECTIVE)){
	    	  query.append( " ( "+com.citibank.ods.common.util.Util.printNTimes("?", 31)+ ") " );
	      }else{
	    	  query.append( " ( "+com.citibank.ods.common.util.Util.printNTimes("?", 30)+ ") " );
	      }

	      ps = connection.prepareStatement( query.toString());
	      int param = 0;
	      ps.setString(++param, vo.getEnrollment());
	      ps.setString(++param, vo.getProdImpTypeCode());
	      ps.setString(++param, vo.getFundNameText());
	      ps.setLong(++param, vo.getAcctNbr().longValue());
	      if (vo.getFileImportCode() !=null && vo.getFileImportCode().longValue() != 0 ){
	    	  ps.setLong(++param,vo.getFileImportCode().longValue());
	      }else {
	    	  ps.setNull(++param, java.sql.Types.NUMERIC);
	      }
	      com.citibank.ods.common.util.Util.setTimestamp(++param, ps, vo.getSubscptOpenDate());
	      ps.setString(++param, vo.getSubjectText());
	      ps.setString(++param, vo.getEventText());
	      com.citibank.ods.common.util.Util.setTimestamp(++param, ps,vo.getSubjectSolDate());
	      ps.setString(++param, vo.getSubjectPhaseText());
	      com.citibank.ods.common.util.Util.setTimestamp(++param, ps,vo.getPhaseRsltnDate());
	      ps.setString(++param, vo.getCommentText());
	      ps.setString(++param, vo.getCnfrmIncpatInd());
	      ps.setString(++param, vo.getReserveInd());
	      ps.setString(++param,vo.getTrainingTermInd());
	      ps.setString(++param,vo.getDocumentCheckInd());
	      ps.setString(++param,vo.getStockTrfOrderInd());
	      ps.setString(++param,vo.getBkrCodeText());
	      ps.setString(++param,vo.getProdKnwlgText());
	      if (vo.getPlyrCpfNbr()!=null)
	    	  ps.setString(++param,vo.getPlyrCpfNbr().replaceAll("\\D", ""));
	      else 
	    	  ps.setNull(++param,Types.NUMERIC);
	      
	      if (vo.getProdRiskLvlQty()==null){
	    	  ps.setNull(++param, java.sql.Types.NUMERIC);
	      }else {
	    	  ps.setLong(++param,vo.getProdRiskLvlQty());
	      }
	      ps.setString(++param,vo.getIncpatApprvNameText());
	      ps.setString(++param,vo.getProdNameText());
	      ps.setString(++param,vo.getTransactionTypeText());
	      com.citibank.ods.common.util.Util.setTimestamp(++param, ps,vo.getLastApprvDate());
	      ps.setString(++param,vo.getLastApprvUserId());
	      if (!vo.getTableType().equals(TableTypeEnum.EFFECTIVE)){
	    	  ps.setString(++param,vo.getOpernTypeCode());
	      }
	      com.citibank.ods.common.util.Util.setTimestamp(++param, ps,vo.getLastUpdDate());
	      ps.setString(++param,vo.getLastUpdUserId());
	      if (vo.getAddDiffAmt() == null){
	    	  ps.setNull(++param, Types.NUMERIC);
	      }else {
	    	  ps.setDouble(++param, vo.getAddDiffAmt().doubleValue());
	      }
	      if (vo.getInvestimentAmt() == null){
	    	  ps.setNull(++param, Types.NUMERIC);
	      }else {
	    	  ps.setDouble(++param, vo.getInvestimentAmt().doubleValue());
	      }
	      
	      ApplicationLogger.getInstance().info(query.toString());
	      ps.execute();
	    }
	    catch ( SQLException e )
	    {
	      throw new UnexpectedException( e.getErrorCode(),
	                                     C_ERROR_EXECUTING_STATEMENT, e );
	    }
	    finally
	    {
	      closeStatement( ps );
	      if (connection.isAutoCommit())
	    	  closeConnection( connection );
	    }
	  }

	

	
	public void update( TplDataSubscptEntityVO vo )
	  {
	    ManagedRdbConnection connection = null;
	    PreparedStatement ps = null;
	    StringBuffer query = new StringBuffer();

	    try
	    {
	      connection = getConnection();
	      query.append( "UPDATE PL.TPL_DATA_SUBSCPT").append(vo.getTableType().getSufixo())
	      		.append(" set PROD_IMP_TYPE_CODE = ?,")
	      		.append("FUND_NAME_TEXT = ?, ")
	    		.append( "ACCT_NBR = ?, ")
	      		.append("FILE_IMPORT_CODE= ?, ")
	      		.append("SUBSCPT_OPEN_DATE= ?, ");
	      		query.append("SUBJECT_TEXT= ?, " );
	      		query.append("EVENT_TEXT= ?, " );
	      		
	      		if (vo.getTableType().equals(TableTypeEnum.EFFECTIVE))
	      			query.append("SUBJECT_SOL_DATE = ?, ");
	      		else 
	      			query.append("SUBSCPT_SOL_DATE = ?, ");
	      		
	      		query.append("SUBJECT_PHASE_TEXT = ?, ")
	      		.append("PHASE_RSLTN_DATE = ?, " )
	      		.append("COMMENT_TEXT = ?, ");
	      		if (vo.getTableType().equals(TableTypeEnum.EFFECTIVE))
	      			query.append("CNFRM_INCPAT_IND= ? , ");
	      		else 
	      			query.append("INCPAT_CNFRM_IND = ?, ");
	      			
	      		query.append("RESERVE_IND= ?, ")
	      		.append("TRAINING_TERM_IND= ?, ")
	      		.append("DOCUMENT_CHECK_IND= ?, ");
	      		if (vo.getTableType().equals(TableTypeEnum.EFFECTIVE))
	      			query.append("STOCK_TRF_ORDER_IND = ?, ");
	      		else 
	      			query.append("TRF_ORDER_IND = ?, ");
	      		
	      		query.append("BKR_CODE_TEXT = ?, ")
	      		.append("PROD_KNWLG_TEXT = ?, ")
	      		.append("PLYR_CPF_NBR = ?, ")
	      		.append("PROD_RISK_LVL_QTY = ?, ")
	      		.append("INCPAT_APPRV_NAME_TEXT = ?, ")
	      		.append("PROD_NAME_TEXT = ?, ");
	      		if (vo.getTableType().equals(TableTypeEnum.EFFECTIVE))
	      			query.append("TRANSACTION_TYPE_TEXT = ?, ");
	      		else {
	      			query.append("TRANSACTION_TYPE = ?, ");
	      		}
	      		query.append("LAST_APPRV_DATE = ?, ")
	      		.append("LAST_APPRV_USER_ID = ?, ")
	      		.append("LAST_UPD_DATE = ?, ")
	      		.append("LAST_UPD_USER_ID = ?,  ADD_DIFF_AMT = ? , INVESTMENT_AMT = ?");
	      		if (!vo.getTableType().equals(TableTypeEnum.EFFECTIVE))
	      			query.append(",OPERN_TYPE_CODE = ?");
	      		
	      		query.append(" WHERE ")	
	      		.append( "ENROLLMENT_NBR = ? ");
	      ps = connection.prepareStatement( query.toString());
	      int param = 0;
	      ps.setString(++param, vo.getProdImpTypeCode());
	      ps.setString(++param, vo.getFundNameText());
	      ps.setLong(++param, vo.getAcctNbr().longValue());
	      if (vo.getFileImportCode() !=null && vo.getFileImportCode().longValue() != 0 ){
	    	  ps.setLong(++param,vo.getFileImportCode().longValue());
	      }else {
	    	  ps.setNull(++param, java.sql.Types.NUMERIC);
	      }
	      com.citibank.ods.common.util.Util.setTimestamp(++param, ps,vo.getSubscptOpenDate());
	      ps.setString(++param, vo.getSubjectText());
	      ps.setString(++param, vo.getEventText());
	      com.citibank.ods.common.util.Util.setTimestamp(++param, ps,vo.getSubjectSolDate());
	      ps.setString(++param, vo.getSubjectPhaseText());
	      com.citibank.ods.common.util.Util.setTimestamp(++param, ps,vo.getPhaseRsltnDate());
	      ps.setString(++param, vo.getCommentText());
	      ps.setString(++param, vo.getCnfrmIncpatInd());
	      ps.setString(++param, vo.getReserveInd());
	      ps.setString(++param,vo.getTrainingTermInd());
	      ps.setString(++param,vo.getDocumentCheckInd());
	      ps.setString(++param,vo.getStockTrfOrderInd());
	      ps.setString(++param,vo.getBkrCodeText());
	      ps.setString(++param,vo.getProdKnwlgText());
	      ps.setString(++param,vo.getPlyrCpfNbr());
	      if (vo.getProdRiskLvlQty()==null){
	    	  ps.setNull(++param, java.sql.Types.NUMERIC);
	      }else {
	    	  ps.setLong(++param,vo.getProdRiskLvlQty());
	      }
	      ps.setString(++param,vo.getIncpatApprvNameText());
	      ps.setString(++param,vo.getProdNameText());
	      ps.setString(++param,vo.getTransactionTypeText());
	      com.citibank.ods.common.util.Util.setTimestamp(++param, ps,vo.getLastApprvDate());
	      ps.setString(++param,vo.getLastApprvUserId());
	      com.citibank.ods.common.util.Util.setTimestamp(++param, ps,vo.getLastUpdDate());
	      ps.setString(++param,vo.getLastUpdUserId());
	      if (vo.getAddDiffAmt() == null){
	    	  ps.setNull(++param, Types.NUMERIC);
	      }else {
	    	  ps.setDouble(++param, vo.getAddDiffAmt().longValue());
	      }
	      if (vo.getInvestimentAmt() == null){
	    	  ps.setNull(++param, Types.NUMERIC);
	      }else {
	    	  ps.setDouble(++param, vo.getInvestimentAmt().doubleValue());
	      }
	      if (!vo.getTableType().equals(TableTypeEnum.EFFECTIVE))
	    	  ps.setString(++param, vo.getOpernTypeCode());
	      
	      ps.setString(++param, vo.getEnrollment());
	      
	      ApplicationLogger.getInstance().info(query.toString());
	      ps.execute();
	      
	    }
	    catch ( SQLException e )
	    {
	      throw new UnexpectedException( e.getErrorCode(),
	                                     C_ERROR_EXECUTING_STATEMENT, e );
	    }
	    finally
	    {
	      closeStatement( ps );
	      if (connection.isAutoCommit())
	    	  closeConnection( connection );
	    }
	  }

	
	public TplDataSubscptEntityVO searchDataSubscrptByEnrollment( String enrollment, TableTypeEnum tableType)
	  {
 	    ManagedRdbConnection connection = null;
	    PreparedStatement ps = null;
	    StringBuffer query = new StringBuffer();

	    try
	    {
	      connection = OracleODSDAOFactory.getConnection();
	      query.append( "SELECT ");
	      		appendQueryItens(tableType, query);
	      		query.append( ", FM.FUND_NAME_TEXT, FM.FUND_CODE, C.cust_full_name_text");		
	      		query.append(" FROM PL.TPL_DATA_SUBSCPT").append(tableType.getSufixo()).append(" DS")
	      		.append(" LEFT JOIN PL.TPL_FUND_MORT").append(tableType.getSufixo()).append("  FM ")
	      				.append(" ON DS.FUND_NAME_TEXT = FM.FUND_NAME_TEXT ");
	      		query.append(" LEFT JOIN BG.TBG_CURRENT_ACCOUNT CA ON CA.ACCT_NBR = DS.ACCT_NBR");
	      		query.append(" LEFT JOIN BG.TBG_RELATIONSHIP R ON CA.ACCT_RELTN_NBR = R.RELTN_NBR ");
	      		query.append(" LEFT JOIN BG.TBG_CUSTOMER C ON C.CUST_NBR = R.RELTN_CUST_1_NBR ");
	      		
	      		 query.append(" WHERE DS.ENROLLMENT_NBR = ? ");

	      ps = connection.prepareStatement( query.toString());
	      ps.setString(1, enrollment);
	      log(query.toString(), enrollment);
	      ResultSet rs = ps.executeQuery();
	      if (rs.next()){
	    	  TplDataSubscptEntityVO vo = new TplDataSubscptEntityVO();
	    	  filVOWithResultSet(tableType, rs, vo);
	    	  
	    	  vo.setFundCode(rs.getString("FUND_CODE"));
	    	  vo.setFundNameText(rs.getString("FUND_NAME_TEXT"));
	    	  vo.setCustName(rs.getString("cust_full_name_text"));
	    	  return vo;
	      }
	      
	      return null;
	    }
	    catch ( SQLException e )
	    {
	      throw new UnexpectedException( e.getErrorCode(),
	                                     C_ERROR_EXECUTING_STATEMENT, e );
	    }
	    finally
	    {
	      closeStatement( ps );
	    	  closeConnection( connection );
	    }
	  }

	public boolean existsDataSubscrpt( String enrollment, TableTypeEnum tableType)
	  {
	    ManagedRdbConnection connection = null;
	    PreparedStatement ps = null;
	    StringBuffer query = new StringBuffer();

	    try
	    {
	      connection = OracleODSDAOFactory.getConnection();
	      query.append( "SELECT COUNT(*) as NUM ");
	      		query.append(" FROM PL.TPL_DATA_SUBSCPT").append(tableType.getSufixo()).append(" DS");
	      		 query.append(" WHERE DS.ENROLLMENT_NBR = ? ");

	      ps = connection.prepareStatement( query.toString());
	      ps.setString(1, enrollment);
	      log(query.toString(), enrollment);
	      ResultSet rs = ps.executeQuery();
	      if (rs.next()){
	    	 int count = rs.getInt("NUM");
	    	  return count > 0;
	      }
	      
	      return false;
	    }
	    catch ( SQLException e )
	    {
	      throw new UnexpectedException( e.getErrorCode(),
	                                     C_ERROR_EXECUTING_STATEMENT, e );
	    }
	    finally
	    {
	      closeStatement( ps );
	    	  closeConnection( connection );
	    }
	  }
	


	public void appendQueryItens(TableTypeEnum tableType, StringBuffer query) {
		query.append( "DS.ENROLLMENT_NBR,DS.PROD_IMP_TYPE_CODE, ")
		.append("DS.FUND_NAME_TEXT, ")
		.append( "DS.ACCT_NBR, ")
		.append("DS.FILE_IMPORT_CODE, ")
		.append("DS.SUBSCPT_OPEN_DATE, ");
		query.append("DS.SUBJECT_TEXT, " );
		query.append("DS.EVENT_TEXT, " );
		
		if (tableType.equals(TableTypeEnum.EFFECTIVE))
			query.append("DS.SUBJECT_SOL_DATE, ");
		else 
			query.append("DS.SUBSCPT_SOL_DATE, ");
		
		query.append("DS.SUBJECT_PHASE_TEXT, ")
		.append("DS.PHASE_RSLTN_DATE, " )
		.append("DS.COMMENT_TEXT, ");
		if (tableType.equals(TableTypeEnum.EFFECTIVE))
			query.append("DS.CNFRM_INCPAT_IND, ");
		else 
			query.append("DS.INCPAT_CNFRM_IND, ");
			
		query.append("DS.RESERVE_IND, ")
		.append("DS.TRAINING_TERM_IND, ")
		.append("DS.DOCUMENT_CHECK_IND, ");
		if (tableType.equals(TableTypeEnum.EFFECTIVE))
			query.append("DS.STOCK_TRF_ORDER_IND, ");
		else 
			query.append("DS.TRF_ORDER_IND, ");
		
		query.append("DS.BKR_CODE_TEXT, ")
		.append("DS.PROD_KNWLG_TEXT, ")
		.append("DS.PLYR_CPF_NBR, ")
		.append("DS.PROD_RISK_LVL_QTY, ")
		.append("DS.INCPAT_APPRV_NAME_TEXT, ")
		.append("DS.PROD_NAME_TEXT, ");
		if (tableType.equals(TableTypeEnum.EFFECTIVE)){
			query.append("DS.TRANSACTION_TYPE_TEXT, ");
		}
		else{ 
			query.append("DS.TRANSACTION_TYPE, ");
			query.append("DS.OPERN_TYPE_CODE, ");
		}
		query.append("DS.LAST_APPRV_DATE, ")
		.append("DS.LAST_APPRV_USER_ID, ")
		.append("DS.LAST_UPD_DATE, ")
		.append("DS.LAST_UPD_USER_ID,   DS.ADD_DIFF_AMT,  DS.INVESTMENT_AMT");
	}

	

	public List<TplDataSubscptEntityVO> searchDataSubscrptImportCode( Long importCode, TableTypeEnum tableType)
	  {
	    ManagedRdbConnection connection = null;
	    PreparedStatement ps = null;
	    StringBuffer query = new StringBuffer();
	    List<TplDataSubscptEntityVO> ret  = new ArrayList<TplDataSubscptEntityVO>();
	    try
	    {
	      connection = OracleODSDAOFactory.getConnection();
	      query.append( "SELECT ");
	      		appendQueryItens(tableType, query);
	      		query.append(", FM.FUND_NAME_TEXT, FM.FUND_CODE FROM PL.TPL_DATA_SUBSCPT").append(tableType.getSufixo()).append(" DS")
	      		.append(" LEFT JOIN PL.TPL_FUND_MORT").append(tableType.getSufixo()).append("  FM ")
	      				.append(" ON DS.FUND_NAME_TEXT = FM.FUND_NAME_TEXT ")
	      		 .append(" WHERE DS.FILE_IMPORT_CODE = ? ");

	      ps = connection.prepareStatement( query.toString());
	      ps.setLong(1, importCode);
	      log(query.toString(), importCode);
	      ResultSet rs = ps.executeQuery();
	      while (rs.next()){
	    	  TplDataSubscptEntityVO vo = new TplDataSubscptEntityVO();
	    	  filVOWithResultSet(tableType, rs, vo);
	    	  vo.setFundCode(rs.getString("FUND_CODE"));
	    	  vo.setFundNameText(rs.getString("FUND_NAME_TEXT"));
	    	  
	    	  ret.add(vo);
	      }
	      
	      return ret;
	    }
	    catch ( SQLException e )
	    {
	      throw new UnexpectedException( e.getErrorCode(),
	                                     C_ERROR_EXECUTING_STATEMENT, e );
	    }
	    finally
	    {
	      closeStatement( ps );
	      closeConnection( connection );
	    }
	  }

	

	private void filVOWithResultSet(TableTypeEnum tableType, ResultSet rs,
			TplDataSubscptEntityVO vo) throws SQLException {
		vo.setEnrollment(rs.getString("ENROLLMENT_NBR"));
		  vo.setProdImpTypeCode(rs.getString("PROD_IMP_TYPE_CODE"));
		  vo.setFundNameText(rs.getString("FUND_NAME_TEXT"));
		  vo.setAcctNbr(BigInteger.valueOf(rs.getLong("ACCT_NBR")));
		  vo.setFileImportCode(BigInteger.valueOf(rs.getLong("FILE_IMPORT_CODE")));
		  vo.setSubscptOpenDate(rs.getTimestamp("SUBSCPT_OPEN_DATE"));
		  vo.setSubjectText(rs.getString("SUBJECT_TEXT"));
		  vo.setEventText(rs.getString("EVENT_TEXT"));
		  vo.setSubjectSolDate(rs.getTimestamp((tableType.equals(TableTypeEnum.EFFECTIVE))? "SUBJECT_SOL_DATE": "SUBSCPT_SOL_DATE"));
		  vo.setSubjectPhaseText(rs.getString("SUBJECT_PHASE_TEXT"));
		  vo.setPhaseRsltnDate(rs.getTimestamp("PHASE_RSLTN_DATE"));
		  vo.setCommentText(rs.getString("COMMENT_TEXT"));
		  vo.setStockTrfOrderInd(rs.getString((tableType.equals(TableTypeEnum.EFFECTIVE))? "STOCK_TRF_ORDER_IND": "TRF_ORDER_IND"));
		  vo.setBkrCodeText(rs.getString("BKR_CODE_TEXT"));
		  vo.setProdKnwlgText(rs.getString("PROD_KNWLG_TEXT"));
		  vo.setPlyrCpfNbr(rs.getString("PLYR_CPF_NBR"));
		  vo.setProdRiskLvlQty(rs.getLong("PROD_RISK_LVL_QTY"));
		  vo.setIncpatApprvNameText(rs.getString("INCPAT_APPRV_NAME_TEXT"));
		  vo.setProdNameText(rs.getString("PROD_NAME_TEXT"));
		  vo.setTransactionTypeText(rs.getString(tableType.equals(TableTypeEnum.EFFECTIVE)?"TRANSACTION_TYPE_TEXT":"TRANSACTION_TYPE"));
		  vo.setLastApprvDate(rs.getTimestamp("LAST_APPRV_DATE"));
		  vo.setLastApprvUserId(rs.getString("LAST_APPRV_USER_ID"));
		  vo.setLastUpdDate(rs.getTimestamp("LAST_UPD_DATE"));
		  vo.setLastUpdUserId(rs.getString("LAST_UPD_USER_ID"));
		  vo.setDocumentCheckInd(rs.getString("DOCUMENT_CHECK_IND"));
		  vo.setCnfrmIncpatInd(rs.getString(tableType.equals(TableTypeEnum.EFFECTIVE)?"CNFRM_INCPAT_IND":"INCPAT_CNFRM_IND"));
		  vo.setReserveInd(rs.getString("RESERVE_IND"));
		  vo.setTrainingTermInd(rs.getString("TRAINING_TERM_IND"));
		  vo.setAddDiffAmt(com.citibank.ods.common.util.Util.parseBigDecimal(rs.getDouble("ADD_DIFF_AMT")));
    	  vo.setInvestimentAmt(com.citibank.ods.common.util.Util.parseBigDecimal(rs.getDouble("INVESTMENT_AMT")));
    	  if (!tableType.equals(TableTypeEnum.EFFECTIVE)){
    		  vo.setOpernTypeCode(rs.getString("OPERN_TYPE_CODE"));
    	  }
	}
	
	

	
	public List<TplDataSubscptEntityVO> searchByFilter( String custName, String acct, String productType, String fundName, TableTypeEnum tableType)
	  {
		List<TplDataSubscptEntityVO> list = new ArrayList<TplDataSubscptEntityVO>();
	    ManagedRdbConnection connection = null;
	    PreparedStatement ps = null;
	    StringBuffer query = new StringBuffer();

	    try
	    {
	      connection = OracleODSDAOFactory.getConnection();
	      query.append( "SELECT ");
	      		appendQueryItens(tableType, query);
	      		query.append(", FM.FUND_NAME_TEXT, FM.FUND_CODE,C.cust_full_name_text ");
	      		query.append(" FROM PL.TPL_DATA_SUBSCPT").append(tableType.getSufixo()).append("  DS")
	      		.append(" LEFT JOIN PL.TPL_FUND_MORT").append(tableType.getSufixo()).append("  FM")
	      		.append(" ON DS.FUND_NAME_TEXT = FM.FUND_NAME_TEXT");
	      		query.append(" LEFT JOIN BG.TBG_CURRENT_ACCOUNT CA ON CA.ACCT_NBR = DS.ACCT_NBR");
	      		query.append(" LEFT JOIN BG.TBG_RELATIONSHIP R ON CA.ACCT_RELTN_NBR = R.RELTN_NBR ");
	      		query.append(" LEFT JOIN BG.TBG_CUSTOMER C ON C.CUST_NBR = R.RELTN_CUST_1_NBR ");
	      		
	      		 query.append(" WHERE TO_CHAR(CA.ACCT_NBR) like ? ");
	      		 
	      		if (!com.citibank.ods.common.util.Util.isEmpty(productType))
	      			query.append(" AND DS.PROD_IMP_TYPE_CODE = ? ");
	      		
	      		if (!com.citibank.ods.common.util.Util.isEmpty(fundName))
	      			query.append(" AND DS.FUND_NAME_TEXT like ? ");

	      		if (!com.citibank.ods.common.util.Util.isEmpty(custName))
	      			query.append(" AND C.cust_full_name_text like ? ");
	      		
	      ps = connection.prepareStatement( query.toString());
	      int param = 0;
	      ps.setString(++param, "%" + acct + "%");
	      
	      if (!com.citibank.ods.common.util.Util.isEmpty(productType))
	    	  ps.setString(++param, productType );
	      
	      if (!com.citibank.ods.common.util.Util.isEmpty(fundName))
	    	  ps.setString(++param, "%" + fundName + "%");
	      
	      if (!com.citibank.ods.common.util.Util.isEmpty(custName))
	    	  ps.setString(++param, "%" + custName + "%");
	      
	      log(query.toString(), "%" + acct + "%","%" + fundName + "%",  "%" + custName + "%" );
	      
	      ResultSet rs = ps.executeQuery();
	      while (rs.next()){
	    	  TplDataSubscptEntityVO vo = new TplDataSubscptEntityVO();
	    	  filVOWithResultSet(tableType, rs, vo);
	    	  vo.setFundCode(rs.getString("FUND_CODE"));
	    	  vo.setCustName(rs.getString("cust_full_name_text"));
	    	  list.add(vo);
	      }
	      return list;
	    }
	    catch ( SQLException e )
	    {
	      throw new UnexpectedException( e.getErrorCode(),
	                                     C_ERROR_EXECUTING_STATEMENT, e );
	    }
	    finally
	    {
	      closeStatement( ps );
	      closeConnection( connection );
	    }
	  }
	
	
	public String searchCustFullName(String accountNbr){
		 ManagedRdbConnection connection = null;
		    PreparedStatement ps = null;
		    StringBuffer query = new StringBuffer();

		    try
		    {
		      connection = OracleODSDAOFactory.getConnection();
		      query.append("") 
				.append("SELECT ") 
				.append("TBG_CUSTOMER.cust_full_name_text ") 
				.append("from  ") 
				.append("BG.TBG_CURRENT_ACCOUNT TBG_CURRENT_ACCOUNT, ") 
				.append("BG.TBG_RELATIONSHIP TBG_RELATIONSHIP, ") 
				.append("BG.TBG_CUSTOMER TBG_CUSTOMER ") 
				.append("where ") 
				.append(" TBG_CURRENT_ACCOUNT.ACCT_RELTN_NBR = TBG_RELATIONSHIP.RELTN_NBR ") 
				.append("AND TBG_RELATIONSHIP.RELTN_CUST_1_NBR = TBG_CUSTOMER.CUST_NBR ")
				.append(" AND TBG_CURRENT_ACCOUNT.ACCT_NBR = ? ");
		      ps = connection.prepareStatement(query.toString());
		      ps.setLong(1, Long.valueOf(accountNbr));
		      
		      log(query.toString(),accountNbr );
		      ResultSet rs = ps.executeQuery();
		      if (rs.next()){
		    	  return rs.getString("CUST_FULL_NAME_TEXT");
		      }
		      return null;
		    }
		    catch ( SQLException e )
		    {
		      throw new UnexpectedException( e.getErrorCode(),
		                                     C_ERROR_EXECUTING_STATEMENT, e );
		    }
		    finally
		    {
		      closeStatement( ps );
		    	  closeConnection( connection );
		    }
	}
	
	public boolean existsAccount(String accountNbr){
		 ManagedRdbConnection connection = null;
		    PreparedStatement ps = null;
		    StringBuffer query = new StringBuffer();

		    try
		    {
		      connection = OracleODSDAOFactory.getConnection();
		      query.append("") 
				.append("SELECT ") 
				.append("count(*) as NUM ") 
				.append("from  ") 
				.append("BG.TBG_CURRENT_ACCOUNT TBG_CURRENT_ACCOUNT ") 
				.append("where ") 
				.append(" TBG_CURRENT_ACCOUNT.ACCT_NBR = ? ");
		      ps = connection.prepareStatement(query.toString());
		      ps.setLong(1, Long.valueOf(accountNbr));
		      
		      log(query.toString(),accountNbr );
		      ResultSet rs = ps.executeQuery();
		      if (rs.next()){
		    	  int count = rs.getInt("NUM");
		    	  return count > 0;
		      }
		      return false;
		    }
		    catch ( SQLException e )
		    {
		      throw new UnexpectedException( e.getErrorCode(),
		                                     C_ERROR_EXECUTING_STATEMENT, e );
		    }
		    finally
		    {
		      closeStatement( ps );
		    	  closeConnection( connection );
		    }
	}
	

	public String searchPlayerFullNameByCGC(String cpfPLayer){
		 ManagedRdbConnection connection = null;
		    PreparedStatement ps = null;
		    StringBuffer query = new StringBuffer();

		    try
		    {
		      connection = OracleODSDAOFactory.getConnection();
		      query.append("") 
				.append("SELECT ") 
				.append("TBG_CUSTOMER.cust_full_name_text ") 
				.append("from pl.TPL_DATA_SUBSCPT TPL_DATA_SUBSCPT, ") 
				.append("BG.TBG_CURRENT_ACCOUNT TBG_CURRENT_ACCOUNT, ") 
				.append("BG.TBG_RELATIONSHIP TBG_RELATIONSHIP, ") 
				.append("BG.TBG_CUSTOMER TBG_CUSTOMER ") 
				.append("where ") 
				.append("TPL_DATA_SUBSCPT.ACCT_NBR = TBG_CURRENT_ACCOUNT.ACCT_NBR ") 
				.append("AND TBG_CURRENT_ACCOUNT.ACCT_RELTN_NBR = TBG_RELATIONSHIP.RELTN_NBR ") 
				.append("AND TBG_RELATIONSHIP.RELTN_CUST_1_NBR = TBG_CUSTOMER.CUST_NBR ")
				.append(" AND TBG_CUSTOMER.CUST_CPF_CGC_NBR = ? ");
		      ps = connection.prepareStatement(query.toString());
		      ps.setLong(1, Long.valueOf(cpfPLayer));
		      
		      log(query.toString(),cpfPLayer );
		      
		      ResultSet rs = ps.executeQuery();
		      if (rs.next()){
		    	  return rs.getString("CUST_FULL_NAME_TEXT");
		      }
		      return null;
		    }
		    catch ( SQLException e )
		    {
		      throw new UnexpectedException( e.getErrorCode(),
		                                     C_ERROR_EXECUTING_STATEMENT, e );
		    }
		    finally
		    {
		      closeStatement( ps );
		      closeConnection( connection );
		    }
	}
	
	public String searchOffcrNbr(Long acctNbr){
		ManagedRdbConnection connection = null;
	    PreparedStatement ps = null;
	    StringBuffer query = new StringBuffer();

	    try
	    {
	      connection = OracleODSDAOFactory.getConnection();
	      query.append("SELECT ") 
	      		.append(" OFFCR_NBR ") 
	      		.append("FROM ") 
	      		.append(" BG.TBG_CURRENT_ACCOUNT, ") 
	      		.append(" BG.TBG_OFFICER ,  ") 
	      		.append(" BG.TBG_RELATIONSHIP, ") 
	      		.append(" BG.TBG_PORTFOLIO ") 
	      		.append("WHERE ") 
	      		.append(" BG.TBG_RELATIONSHIP.RELTN_PORTF_CODE = BG.TBG_PORTFOLIO.PORTF_CODE ") 
	      		.append(" AND BG.TBG_OFFICER.OFFCR_NBR = BG.TBG_PORTFOLIO.PORTF_OFFCR_NBR ") 
	      		.append(" AND BG.TBG_RELATIONSHIP.RELTN_NBR = BG.TBG_CURRENT_ACCOUNT.ACCT_RELTN_NBR ") 
	      		.append(" AND  BG.TBG_CURRENT_ACCOUNT.ACCT_NBR =  ? ");
	      
	      ps = connection.prepareStatement(query.toString());
	      ps.setLong(1, acctNbr);
	      
	      log(query.toString(),acctNbr );
	      
	      ResultSet rs = ps.executeQuery();
	      if (rs.next()){
	    	  return rs.getString("OFFCR_NBR");
	      }
	      return null;
	    }
	    catch ( SQLException e )
	    {
	      throw new UnexpectedException( e.getErrorCode(),
	                                     C_ERROR_EXECUTING_STATEMENT, e );
	    }
	    finally
	    {
	      closeStatement( ps );
	      closeConnection( connection );
	    }
	}
	

	public void delete(String enrollment, TableTypeEnum tableType)
	  {
	    ManagedRdbConnection connection = null;
	    PreparedStatement ps = null;
	    StringBuffer query = new StringBuffer();

	    try
	    {
	      connection = getConnection();
	      query.append( "DELETE FROM ")
	      		.append(" PL.TPL_DATA_SUBSCPT").append(tableType.getSufixo())
	      		 .append(" WHERE ENROLLMENT_NBR = ? ");

	      ps = connection.prepareStatement( query.toString());
	      ps.setString(1, enrollment);
	      
	      log(query.toString(),enrollment );
	      
	      ps.execute();
	      return;
	    }
	    catch ( SQLException e )
	    {
	      throw new UnexpectedException( e.getErrorCode(),
	                                     C_ERROR_EXECUTING_STATEMENT, e );
	    }
	    finally
	    {
	      closeStatement( ps );
	      if (connection.isAutoCommit())
	    	  closeConnection( connection );
	    }
	  }

	public ArrayList<FundSubscriptionImportHistoryVO> searchImportHistory() {
		return searchImportHistory(TableTypeEnum.EFFECTIVE);
	}
	public ArrayList<FundSubscriptionImportHistoryVO> searchImportHistory(TableTypeEnum tableType) {
		
		 ManagedRdbConnection connection = null;
		    PreparedStatement ps = null;
		    StringBuffer query = new StringBuffer();
		    ArrayList<FundSubscriptionImportHistoryVO> ret = new ArrayList<FundSubscriptionImportHistoryVO>();
		    try
		    {
		      connection = OracleODSDAOFactory.getConnection();
		      query.append("") 
				.append(" select ISI.FILE_IMPORT_CODE,  " ) 
				.append("  ISI.FILE_IMPORT_DATE,  " ) 
				.append("  ISI.IMPORT_FILE_NAME_TEXT,  " ) 
				.append("  ISI.FILE_IMPORT_REC_QTY,  " ) 
				.append("  (select DISTINCT DS.PROD_IMP_TYPE_CODE FROM PL.TPL_DATA_SUBSCPT").append(tableType.getSufixo())
				.append(" DS WHERE ISI.FILE_IMPORT_CODE = DS.FILE_IMPORT_CODE ) AS PROD_IMP_TYPE_CODE   " ) 
				.append("  FROM PL.TPL_INFO_SUBSCPT_IMP").append(tableType.getSufixo())
				.append( " ISI");
		      ps = connection.prepareStatement(query.toString());
		      
		      log(query.toString());
		      ResultSet rs = ps.executeQuery();
		      while (rs.next()){
		    	  FundSubscriptionImportHistoryVO vo = new FundSubscriptionImportHistoryVO();
		    	  vo.setCodigo(rs.getLong("FILE_IMPORT_CODE"));
		    	  vo.setDataImportacao(rs.getTimestamp("FILE_IMPORT_DATE"));
		    	  vo.setNomeArquivo(rs.getString("IMPORT_FILE_NAME_TEXT"));
		    	  vo.setRegistros(rs.getInt("FILE_IMPORT_REC_QTY"));
		    	  String code = rs.getString("PROD_IMP_TYPE_CODE");
		    	  vo.setTipoProduto("F".equals(code)?"Fundos Imobiliários":"Outros");
		    	  ret.add(vo);
		      }
		      return ret;
		    }
		    catch ( SQLException e )
		    {
		      throw new UnexpectedException( e.getErrorCode(),
		                                     C_ERROR_EXECUTING_STATEMENT, e );
		    }
		    finally
		    {
		      closeStatement( ps );
		      closeConnection( connection );
		    }
	}
	
	private ManagedRdbConnection connection;
	public ManagedRdbConnection getConnection(){
		if (connection==null ||connection.isAutoCommit() )
			connection = OracleODSDAOFactory.getConnection();
		
		return connection;
	}
	
}
