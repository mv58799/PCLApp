package com.citibank.ods.modules.product.fundsubscription.action;

import java.util.Date;

import com.citibank.ods.common.action.BaseODSAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.TableTypeEnum;
import com.citibank.ods.entity.pl.valueobject.TplFundMortEntityVO;
import com.citibank.ods.modules.product.fundsubscription.functionality.FundSubscriptionFundCodeUpdateFnc;
import com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.FundSubscriptionFundCodeUpdateFncVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplFundMortDAO;
 
public class FundSubscriptionFundCodeUpdateAction extends BaseODSAction{

	private static final String C_CLEAR_PAGE = "ClearPage";
	
	private static final String C_SCREEN_NAME = "FundSubscription.FundSubscriptionFundCode";
	
	@Override
	protected BaseFnc getFuncionality() {
		return new FundSubscriptionFundCodeUpdateFnc();
	}

	@Override
	protected String getScreenName() {
		return C_SCREEN_NAME;
	}

	@Override
	protected String executeAction(BaseFncVO fncVO, String mode_) {
		OracleTplFundMortDAO dao = new OracleTplFundMortDAO();
		FundSubscriptionFundCodeUpdateFncVO vo = (FundSubscriptionFundCodeUpdateFncVO)fncVO;
		TplFundMortEntityVO tplEntity = new TplFundMortEntityVO();
		tplEntity.setFundCode(vo.getCodigo());
		tplEntity.setFundName(vo.getNome());
		tplEntity.setLastUpdDate(new Date());
		tplEntity.setLastUpdUserId(fncVO.getLoggedUser().getUserID());
		tplEntity.setOpernTypeCode("A");
		tplEntity.setTableType(TableTypeEnum.MOVEMENT);
		
		TplFundMortEntityVO fundInMovement = dao.getFundsByName(vo.getNomeAnterior(), TableTypeEnum.MOVEMENT);
		if (fundInMovement != null){
			dao.update(tplEntity, vo.getNomeAnterior());
		}else {
			dao.insert(tplEntity);
		}
		vo.addMessage(BaseODSFncVO.C_MESSAGE_SUCESS);
		return getScreenName() + C_SPLITTER_CHAR + mode_;
	}

	@Override
	protected String showAction(BaseFncVO fncVO_, String mode_) {
		FundSubscriptionFundCodeUpdateFncVO vo = (FundSubscriptionFundCodeUpdateFncVO)fncVO_;
		OracleTplFundMortDAO dao = new OracleTplFundMortDAO();
		if (!com.citibank.ods.common.util.Util.isEmpty(vo.getSelectedCode())){
			TplFundMortEntityVO fund = null;
			if (!com.citibank.ods.common.util.Util.isEmpty(vo.getSelectedCode())){
				fund  = dao.getFundsByName(vo.getSelectedCode(), TableTypeEnum.MOVEMENT);
			}else {
				fund  = dao.getFundsByName(vo.getSelectedCode(), TableTypeEnum.EFFECTIVE);
			}
			if (fund!= null){
				vo.setBlockConfirm(false);
				vo.setCodigo(fund.getFundCode());
				vo.setNome(fund.getFundName());
				vo.setNomeAnterior(fund.getFundName());
			}
			vo.setSelectedCode(null);
		}else {
			TplFundMortEntityVO fund  = dao.getFundsByName(vo.getNome(), TableTypeEnum.MOVEMENT);
			if (fund!= null){
				vo.addError("IN_MOVEMENT");
				vo.setBlockConfirm(true);
			}else {
				vo.setBlockConfirm(false);
			}
			vo.setCodigoAnterior(vo.getCodigo());
			vo.setNomeAnterior(vo.getNome());
		}
		return getScreenName() + C_SPLITTER_CHAR + mode_;
	}

	@Override
	protected String specificActions(BaseFncVO fncVO_, String invokePath_) {
		 
		return null;
	}

	@Override
	protected String extraActions(BaseFncVO fncVO_, String invokePath_) {
		String[] splittedInvokePath = invokePath_.split( "\\." );
		FundSubscriptionFundCodeUpdateFnc listFnc = ( FundSubscriptionFundCodeUpdateFnc ) getFuncionality();
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
		return FundSubscriptionFundCodeUpdateFncVO.class.getName();
	}

}
