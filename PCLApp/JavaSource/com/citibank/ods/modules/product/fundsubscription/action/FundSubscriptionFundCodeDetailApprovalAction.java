package com.citibank.ods.modules.product.fundsubscription.action;

import java.util.Date;

import com.citibank.ods.common.action.BaseODSAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.TableTypeEnum;
import com.citibank.ods.entity.pl.valueobject.TplFundMortEntityVO;
import com.citibank.ods.modules.product.fundsubscription.functionality.FundSubscriptionFundCodeDetailApprovalFnc;
import com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.FundSubscriptionFundCodeDetailApprovalFncVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplFundMortDAO;

public class FundSubscriptionFundCodeDetailApprovalAction extends BaseODSAction{

	
	private static final String C_SCREEN_NAME = "FundSubscription.FundSubscriptionFundCodeDetail";
	@Override
	protected BaseFnc getFuncionality() {
		return new FundSubscriptionFundCodeDetailApprovalFnc();
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
		
		FundSubscriptionFundCodeDetailApprovalFncVO vo = (FundSubscriptionFundCodeDetailApprovalFncVO) fncVO_;
		OracleTplFundMortDAO dao = new OracleTplFundMortDAO();
		TplFundMortEntityVO funds = dao.getFundsByName(vo.getSelectedCode(), TableTypeEnum.MOVEMENT);
		
		vo.setCanApprove(!vo.getLoggedUser().getUserID().equals(funds.getLastUpdUserId()));
		vo.setNome(funds.getFundName());
		vo.setCodigo(funds.getFundCode());
		
		return getScreenName() + C_SPLITTER_CHAR + mode_;
	}

	@Override
	protected String specificActions(BaseFncVO fncVO_, String invokePath_) {
		FundSubscriptionFundCodeDetailApprovalFncVO vo = (FundSubscriptionFundCodeDetailApprovalFncVO)fncVO_;
		OracleTplFundMortDAO dao = new OracleTplFundMortDAO();
		if (invokePath_.matches(".*\\.Approve$")){
			TplFundMortEntityVO tplFundMortEntityVO = new TplFundMortEntityVO();
			tplFundMortEntityVO.setFundCode(vo.getCodigo());
			tplFundMortEntityVO.setFundName(vo.getNome());
			tplFundMortEntityVO.setLastApprvDate(new Date());
			tplFundMortEntityVO.setLastApprvUserId(vo.getLoggedUser().getUserID());
			tplFundMortEntityVO.setTableType(TableTypeEnum.EFFECTIVE);
			TplFundMortEntityVO eff = dao.getFundsByName(vo.getNome(), TableTypeEnum.EFFECTIVE);
			if (eff != null){
				
				
				dao.update(tplFundMortEntityVO, eff.getFundName());
				eff.setTableType(TableTypeEnum.HISTORIC);
				if (eff.getLastUpdDate() ==null){
					eff.setLastUpdDate(new Date());
				}
				
				dao.insert(eff);
			}else {
				dao.insert(tplFundMortEntityVO);
			}
			dao.delete(tplFundMortEntityVO.getFundName(), TableTypeEnum.MOVEMENT);
			vo.addMessage(BaseODSFncVO.C_MESSAGE_SUCESS);
		}else if (invokePath_.matches(".*\\.Reprove$")){
			dao.delete(vo.getNome(), TableTypeEnum.MOVEMENT);
		}
		return "CentrApproval.CentrApprovalMovementList.List";
	}

	@Override
	protected String extraActions(BaseFncVO fncVO_, String invokePath_) {
		return null;
	}

	@Override
	public String getFncVOPublishName() {
		return FundSubscriptionFundCodeDetailApprovalFncVO.class.getName();
	}

}
