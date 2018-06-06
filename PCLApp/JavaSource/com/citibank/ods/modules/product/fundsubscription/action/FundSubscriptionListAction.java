package com.citibank.ods.modules.product.fundsubscription.action;

import java.util.HashSet;
import java.util.List;

import com.citibank.ods.common.action.BaseODSAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.TableTypeEnum;
import com.citibank.ods.entity.pl.valueobject.TplDataSubscptEntityVO;
import com.citibank.ods.modules.product.fundsubscription.form.valueobject.FundSubscriptionListVO;
import com.citibank.ods.modules.product.fundsubscription.functionality.FundSubscriptionListFnc;
import com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.FundSubscriptionListFncVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplDataSubscptDAO;
 
public class FundSubscriptionListAction extends BaseODSAction{

	private static final String C_CLEAR_PAGE = "ClearPage";
	
	private static final String C_SCREEN_NAME = "FundSubscription.FundSubscriptionList";
	
	@Override
	protected BaseFnc getFuncionality() {
		return new FundSubscriptionListFnc();
	}

	@Override
	protected String getScreenName() {
		return C_SCREEN_NAME;
	}

	@Override
	protected String executeAction(BaseFncVO fncVO, String mode_) {
		FundSubscriptionListFncVO vo = (FundSubscriptionListFncVO)fncVO;
		OracleTplDataSubscptDAO dao = new OracleTplDataSubscptDAO();
		if (com.citibank.ods.common.util.Util.isAllEmpty(vo.getNomeCliente(), vo.getNumeroConta(), vo.getTipoProduto(), vo.getNomeProduto())){
			vo.addError("ERROR_EMPTY_FILTER");
			return showAction(fncVO, mode_);
		}
		List<TplDataSubscptEntityVO> result = dao.searchByFilter(vo.getNomeCliente(), vo.getNumeroConta(), vo.getTipoProduto(), vo.getNomeProduto(), 
				TableTypeEnum.EFFECTIVE);
		vo.setResultado(new HashSet<FundSubscriptionListVO>());
		
		for (TplDataSubscptEntityVO t : result){
			FundSubscriptionListVO f = new FundSubscriptionListVO();
			f.setProtocolo(t.getEnrollment().toString());
			f.setCodigoFundo(t.getFundCode()==null?null:t.getFundCode().toString());
			f.setNomeProduto(t.getFundCode()==null?t.getProdNameText():t.getFundNameText());
			f.setNomeCliente(t.getCustName());
			f.setEvento(t.getEventText());
			vo.getResultado().add(f);
		}
		return showAction(fncVO, mode_);
	}

	@Override
	protected String showAction(BaseFncVO fncVO_, String mode_) {
		return getScreenName() + C_SPLITTER_CHAR + mode_;
	}

	@Override
	protected String specificActions(BaseFncVO fncVO_, String invokePath_) {
		System.out.println("specific Actions");
		String[] splittedInvokePath = invokePath_.split( "\\." );
		if (splittedInvokePath[ 3 ].equals( "Clean" )) {
			(( FundSubscriptionListFnc ) getFuncionality()).clearPage( fncVO_ );
			return getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ] + C_SPLITTER_CHAR + "Show";
		}
		
		return null;
	}

	@Override
	protected String extraActions(BaseFncVO fncVO_, String invokePath_) {
		String[] splittedInvokePath = invokePath_.split( "\\." );
		FundSubscriptionListFnc listFnc = ( FundSubscriptionListFnc ) getFuncionality();
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
		return FundSubscriptionListFncVO.class.getName();
	}

}
