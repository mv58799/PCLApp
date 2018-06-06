package com.citibank.ods.modules.product.fundsubscription.action;

import org.apache.commons.beanutils.BeanUtils;

import com.citibank.ods.common.action.BaseODSAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.logger.ApplicationLogger;
import com.citibank.ods.modules.product.fundsubscription.functionality.FundSubscriptionDetailFnc;
import com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.FundSubscriptionDetailFncVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplDataSubscptDAO;
 
public class FundSubscriptionDetailAction extends BaseODSAction{

	
	private static final String C_CLEAR_PAGE = "ClearPage";
	
	private static final String C_SCREEN_NAME = "FundSubscription.FundSubscriptionDetail";
	
	
	
	@Override
	protected BaseFnc getFuncionality() {
		return new FundSubscriptionDetailFnc();
	}

	@Override
	protected String getScreenName() {
		return C_SCREEN_NAME;
	}

	@Override
	protected String executeAction(BaseFncVO fncVO, String mode_) {
		
		FundSubscriptionDetailFncVO	vo = (FundSubscriptionDetailFncVO)fncVO;
		
		vo.setOperation(mode_);
		if (vo.getIsReturnConsult()){
			vo.setIsReturnConsult(false);
			vo.setIsConsultName(false);
			return executeSearchAction(fncVO, mode_);
		}else {
		
			if ("Insert".equals(mode_)){
				FundSubscriptionDetailFnc.insert(vo);
			}else if ("Update".equals(mode_)){
				FundSubscriptionDetailFnc.update(vo);
			}else if ("Delete".equals(mode_)){
				FundSubscriptionDetailFnc.delete(vo);
			}
		}
		
		return getScreenName() + C_SPLITTER_CHAR + mode_;
	}

	@Override
	protected String showAction(BaseFncVO fncVO_, String mode_) {
		FundSubscriptionDetailFncVO vo = (FundSubscriptionDetailFncVO)fncVO_;
		if ("Update".equals(mode_)){
			FundSubscriptionDetailFnc.loadToShow(vo);
		}else{
			if (vo.getClear()){
				try {
					//renew bean
					BeanUtils.copyProperties(vo, new FundSubscriptionDetailFncVO());
				} catch (Exception e) {
					ApplicationLogger.getInstance().error("Erro ao copiar propriedades do Bean", e);
				} 
			}
		}
		vo.setOperation(mode_);
		if (!vo.getIsConsultName()){
			if (vo.getSelectedCode() != null && vo.getSelectedCode().matches(".*,I$")){
				vo.addError("ERROR_IMPORT_NO_UPDATE");
				vo.setBlockConfirm(true);
			}
			vo.setIsConsultName(false);
			return getScreenName() + C_SPLITTER_CHAR + mode_;
		} else {
			vo.setIsConsultName(false);
			return "FundSubscription.FundSubscriptionDetail.Consult";
		}
	}


	@Override
	protected String specificActions(BaseFncVO fncVO_, String invokePath_) {
		
		return "FundSubscription.FundSubscriptionDetail.Consult";
	}

	@Override
	protected String extraActions(BaseFncVO fncVO_, String invokePath_) {
		String[] splittedInvokePath = invokePath_.split( "\\." );
		FundSubscriptionDetailFnc listFnc = ( FundSubscriptionDetailFnc ) getFuncionality();
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

	protected String executeSearchAction(BaseFncVO fncVO, String mode_) {
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
			return "FundSubscription.FundSubscriptionDetail.Consult";
		}else {
			return "FundSubscription.FundSubscriptionDetail."+vo.getOperation()+".Show";
		}
	}
	
	
}
