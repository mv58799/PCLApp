package com.citibank.ods.modules.product.fundsubscription.action;

import java.util.List;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.TableTypeEnum;
import com.citibank.ods.entity.pl.valueobject.TplDataSubscptEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplInfoSubscptImpEntityVO;
import com.citibank.ods.modules.product.fundsubscription.functionality.FundSubscriptionDetailApprovalFnc;
import com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.FundSubscriptionDetailApprovalFncVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplDataSubscptDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplInfoSubscptImpDAO;

public class FundSubscriptionDetailApprovalAction extends BaseODSListAction{

	/*
	* Parte do nome do módulo ou ação
	*/
    private static final String C_SCREEN_NAME = "FundSubscription.FundSubscriptionDetail";	

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.action.BaseODSAction#getFuncionality()
	 */
	protected BaseFnc getFuncionality() {
		return new FundSubscriptionDetailApprovalFnc();
		
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.action.BaseODSAction#getScreenName()
	 */
	protected String getScreenName() {		
		return C_SCREEN_NAME;
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.action.BaseODSAction#extraActions(com.citibank.ods.common.functionality.valueobject.BaseFncVO, java.lang.String)
	 */
	protected String extraActions(BaseFncVO fncVO_, String invokePath_) {
		return null;		
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.action.BaseAction#getFncVOPublishName()
	 */
	public String getFncVOPublishName() {
		return FundSubscriptionDetailApprovalFncVO.class.getName();		
	}
	@Override
	protected String specificActions(BaseFncVO fncVO_, String invokePath_) {
		FundSubscriptionDetailApprovalFncVO vo = ( FundSubscriptionDetailApprovalFncVO ) fncVO_;
		
		String selectedCode = vo.getSelectedCode();
		String enrNumber = selectedCode.substring(0, selectedCode.indexOf(","));
		String type = selectedCode.substring(selectedCode.indexOf(",") + 1);
		
		
		if (invokePath_.matches(".*\\.Approve$")){
			if ("M".equals(type)){
				FundSubscriptionDetailApprovalFnc.approve(vo);
			}else {
				FundSubscriptionDetailApprovalFnc.approveimport(vo.getCodigo(), vo.getLoggedUser().getUserID());
			}
		}else if (invokePath_.matches(".*\\.Reprove$")){
			if ("M".equals(type)){
				FundSubscriptionDetailApprovalFnc.repprove(vo);
			}else {
				OracleTplDataSubscptDAO dataDao = new OracleTplDataSubscptDAO();
				OracleTplInfoSubscptImpDAO subDao = new OracleTplInfoSubscptImpDAO();
				List<TplDataSubscptEntityVO> list = dataDao.searchDataSubscrptImportCode(vo.getCodigo(),  TableTypeEnum.MOVEMENT);
				for (TplDataSubscptEntityVO dataSubVo : list){
					if (dataSubVo!=null){
						dataDao.delete(dataSubVo.getEnrollment(), TableTypeEnum.MOVEMENT);
					}
				}
				subDao.delete(vo.getCodigo(), TableTypeEnum.MOVEMENT);
			}
		}
		return "CentrApproval.CentrApprovalMovementList.List";
	}


	protected String showAction( BaseFncVO fncVO_, String mode_ )
	{
		FundSubscriptionDetailApprovalFncVO vo = ( FundSubscriptionDetailApprovalFncVO ) fncVO_;
		

		String selectedCode = vo.getSelectedCode();
		String enrNumber = selectedCode.substring(0, selectedCode.indexOf(","));
		String type = selectedCode.substring(selectedCode.indexOf(",") + 1);
		
		if ("M".equals(type)){
			
			if (mode_.equals("Update")){
				vo.addError("ERROR_IMPORT_NO_UPDATE");
				return super.showAction(fncVO_, mode_);
			}else 
				FundSubscriptionDetailApprovalFnc.loadForApproval(vo);
			
			return super.showAction(vo, mode_);
		}else {
			OracleTplInfoSubscptImpDAO dao = new OracleTplInfoSubscptImpDAO();
			TplInfoSubscptImpEntityVO result = dao.searchImpSubscript(Long.valueOf(enrNumber), TableTypeEnum.MOVEMENT);
			vo.setCodigo(result.getFileImportCode());
			vo.setDataImportacao(result.getFileImportDate());
			vo.setNomeArquivo(result.getImportFileNameText());
			vo.setRegistros(com.citibank.ods.common.util.Util.parseInteger(result.getFileImportRecQty()));
			vo.setTipoProduto(result.getOpernTypeCode());
			vo.setCanApprove(!vo.getLoggedUser().getUserID().equals(result.getLastUpdUserId()));
			return "FundSubscription.FundSubscriptionImpDetail.Update";
		}
	}

	@Override
	protected String executeAction(BaseFncVO fncVO, String mode_) {
		
		
		return "";
		
		
	}
}
