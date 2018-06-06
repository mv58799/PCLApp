package com.citibank.ods.modules.product.fundsubscription.action;

import java.util.ArrayList;
import java.util.List;

import com.citibank.ods.common.action.BaseODSAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.TableTypeEnum;
import com.citibank.ods.entity.pl.valueobject.TplFundMortEntityVO;
import com.citibank.ods.modules.product.fundsubscription.form.FundSubscriptionFundCodeUpdateForm;
import com.citibank.ods.modules.product.fundsubscription.functionality.FundSubscriptionFundCodeListFnc;
import com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.FundSubscriptionFundCodeListFncVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplFundMortDAO;
 
public class FundSubscriptionFundCodeListAction extends BaseODSAction{

	private static final String C_CLEAR_PAGE = "ClearPage";
	
	private static final String C_SCREEN_NAME = "FundSubscription.FundSubscriptionFundCode";
	
	@Override
	protected BaseFnc getFuncionality() {
		return new FundSubscriptionFundCodeListFnc();
	}

	@Override
	protected String getScreenName() {
		return C_SCREEN_NAME;
	}

	@Override
	protected String executeAction(BaseFncVO fncVO, String mode_) {
		FundSubscriptionFundCodeListFncVO vo = (FundSubscriptionFundCodeListFncVO)fncVO;
		OracleTplFundMortDAO dao = new OracleTplFundMortDAO();
		List<TplFundMortEntityVO> funds = dao.getFundsByNameLike(vo.getNomeFundo(), TableTypeEnum.EFFECTIVE, vo.isFundosComCodigo());
		vo.setResultado(new ArrayList<FundSubscriptionFundCodeUpdateForm>());
		for (TplFundMortEntityVO fund : funds){
			FundSubscriptionFundCodeUpdateForm f = new FundSubscriptionFundCodeUpdateForm();
			f.setCodigo(fund.getFundCode());
			f.setNome(fund.getFundName());
			vo.getResultado().add(f);
		}
		return getScreenName() + C_SPLITTER_CHAR + mode_;
	}

	@Override
	protected String showAction(BaseFncVO fncVO_, String mode_) {
		FundSubscriptionFundCodeListFnc listFnc = ( FundSubscriptionFundCodeListFnc ) getFuncionality();
		listFnc.clearPage(fncVO_);
		return getScreenName() + C_SPLITTER_CHAR + mode_;
	}

	@Override
	protected String specificActions(BaseFncVO fncVO_, String invokePath_) {
		 
		return null;
	}

	@Override
	protected String extraActions(BaseFncVO fncVO_, String invokePath_) {
		String[] splittedInvokePath = invokePath_.split( "\\." );
		FundSubscriptionFundCodeListFnc listFnc = ( FundSubscriptionFundCodeListFnc ) getFuncionality();
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
		return FundSubscriptionFundCodeListFncVO.class.getName();
	}

}
