package com.citibank.ods.modules.product.fundsubscription.action;

import com.citibank.ods.common.action.BaseODSAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.fundsubscription.functionality.FundSubscriptionClientFnc;
import com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.FundSubscriptionDetailFncVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplDataSubscptDAO;
 
public class FundSubscriptionClientAction extends BaseODSAction{

	private static final String C_CLEAR_PAGE = "ClearPage";
	
	private static final String C_SCREEN_NAME = "FundSubscription.FundSubscriptionClient";
	
	@Override
	protected BaseFnc getFuncionality() {
		return new FundSubscriptionClientFnc();
	}

	@Override
	protected String getScreenName() {
		return C_SCREEN_NAME;
	}

	@Override
	protected String executeAction(BaseFncVO fncVO, String mode_) {
		System.out.println("Execute");
		OracleTplDataSubscptDAO dao = new OracleTplDataSubscptDAO();
		FundSubscriptionDetailFncVO vo = (FundSubscriptionDetailFncVO)fncVO;
		
		String custFullName = dao.searchCustFullName(vo.getAcctNbr());
		vo.setNomeCliente(custFullName);
		vo.setFuncionalGerente(dao.searchOffcrNbr(Long.valueOf(vo.getAcctNbr())));
		
		vo.setCcDebito(vo.getAcctNbr());
		String perfGrb = dao.getPerfGrb(Long.valueOf(vo.getAcctNbr()));
		vo.setPerfilGRB(perfGrb);
		if (custFullName==null){
			vo.addError("ERROR_ACCT_NOT_FOUND");
			return "FundSubscription.FundSubscriptionClient.Consult";
		}else {
			return "FundSubscription.FundSubscriptionDetail."+vo.getOperation()+".Show";
		}
	}

	@Override
	protected String showAction(BaseFncVO fncVO_, String mode_) {
		
		return getScreenName() + C_SPLITTER_CHAR + mode_;
	}

	@Override
	protected String specificActions(BaseFncVO fncVO_, String invokePath_) {
		 System.out.println("specific action");
		 return getScreenName() + C_SPLITTER_CHAR + "Show";
	}

	@Override
	protected String extraActions(BaseFncVO fncVO_, String invokePath_) {
		String[] splittedInvokePath = invokePath_.split( "\\." );
		FundSubscriptionClientFnc listFnc = ( FundSubscriptionClientFnc ) getFuncionality();
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
		return FundSubscriptionDetailFncVO.class.getName();
	}

}
