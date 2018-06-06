package com.citibank.ods.modules.product.fundsubscription.action;

import java.util.ArrayList;

import com.citibank.ods.common.action.BaseODSAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.fundsubscription.form.valueobject.FundSubscriptionImportHistoryVO;
import com.citibank.ods.modules.product.fundsubscription.functionality.FundSubscriptionImportDetailFnc;
import com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.FundSubscriptionImportDetailVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplDataSubscptDAO;
 
public class FundSubscriptionImportDetailAction extends BaseODSAction{

	
	private static final String C_CLEAR_PAGE = "ClearPage";
	
	private static final String C_SCREEN_NAME = "FundSubscriptionImport.FundSubscriptionImportDetail";
	 
	@Override
	protected BaseFnc getFuncionality() {
		return new FundSubscriptionImportDetailFnc();
	}

	@Override
	protected String getScreenName() {
		return C_SCREEN_NAME;
	}

	@Override
	protected String executeAction(BaseFncVO fncVO, String mode_) {
		
		return null;
	}

	@Override
	protected String showAction(BaseFncVO fncVO_, String mode_) {

		FundSubscriptionImportDetailVO vo = (FundSubscriptionImportDetailVO)fncVO_;
		
		OracleTplDataSubscptDAO dao = new OracleTplDataSubscptDAO();
		ArrayList<FundSubscriptionImportHistoryVO> arr = dao.searchImportHistory();
		vo.setHistory(arr);
		return getScreenName() + C_SPLITTER_CHAR + mode_;
	}

	@Override
	protected String specificActions(BaseFncVO fncVO_, String invokePath_) {
		 
		return null;
	}

	@Override
	protected String extraActions(BaseFncVO fncVO_, String invokePath_) {
		String[] splittedInvokePath = invokePath_.split( "\\." );
		FundSubscriptionImportDetailFnc listFnc = ( FundSubscriptionImportDetailFnc ) getFuncionality();
	    String forwardKey = "";

	    if ( splittedInvokePath[ 2 ].equals( C_MODE_CONSULT )
	         && ( splittedInvokePath[ 3 ].equals( C_CLEAR_PAGE ) ) )

	    {
	      listFnc.clearPage( fncVO_ );

	      forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
	    }
	    return forwardKey;
	}

	@Override
	public String getFncVOPublishName() {
		return FundSubscriptionImportDetailVO.class.getName();
	}

}
