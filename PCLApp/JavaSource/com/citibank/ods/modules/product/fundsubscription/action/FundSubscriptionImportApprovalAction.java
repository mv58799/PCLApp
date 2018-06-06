package com.citibank.ods.modules.product.fundsubscription.action;

import java.util.List;

import com.citibank.ods.common.action.BaseODSAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.TableTypeEnum;
import com.citibank.ods.entity.pl.valueobject.TplDataSubscptEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplInfoSubscptImpEntityVO;
import com.citibank.ods.modules.product.fundsubscription.functionality.FundSubscriptionDetailApprovalFnc;
import com.citibank.ods.modules.product.fundsubscription.functionality.FundSubscriptionImportApprovalFnc;
import com.citibank.ods.modules.product.fundsubscription.functionality.FundSubscriptionImportDetailFnc;
import com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.FundSubscriptionImportApprovalFncVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplDataSubscptDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplInfoSubscptImpDAO;
 
public class FundSubscriptionImportApprovalAction extends BaseODSAction{

	
	private static final String C_SCREEN_NAME = "FundSubscriptionImport.FundSubscriptionImportDetail";
	 
	@Override
	protected BaseFnc getFuncionality() {
		return new FundSubscriptionImportApprovalFnc();
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
		FundSubscriptionImportApprovalFncVO vo = (FundSubscriptionImportApprovalFncVO) fncVO_;
		OracleTplInfoSubscptImpDAO dao = new OracleTplInfoSubscptImpDAO();
		String selectedCode = vo.getSelectedCode();
		String enrNumber = selectedCode.substring(0, selectedCode.indexOf(","));
		String type = selectedCode.substring(selectedCode.indexOf(","));
		
		TplInfoSubscptImpEntityVO result = dao.searchImpSubscript(Long.valueOf(enrNumber), TableTypeEnum.MOVEMENT);
		
		vo.setCodigo(result.getFileImportCode());
		vo.setDataImportacao(result.getFileImportDate());
		vo.setNomeArquivo(result.getImportFileNameText());
		vo.setRegistros(com.citibank.ods.common.util.Util.parseInteger(result.getFileImportRecQty()));
		vo.setTipoProduto(result.getOpernTypeCode());
		vo.setCanApprove(!vo.getLoggedUser().getUserID().equals(result.getLastUpdUserId()));
		return getScreenName() + C_SPLITTER_CHAR + mode_;
	}

	@Override
	protected String specificActions(BaseFncVO fncVO_, String invokePath_) {
		System.out.println(this.getClass().getName() +": " + invokePath_);
		FundSubscriptionImportApprovalFncVO vo = (FundSubscriptionImportApprovalFncVO)fncVO_;
//		OracleTplInfoSubscptImpDAO dao = new OracleTplInfoSubscptImpDAO();
		if (invokePath_.matches(".*\\.Approve$")){
			FundSubscriptionDetailApprovalFnc.approveimport(vo.getCodigo(), vo.getLoggedUser().getUserID());
//			
//			TplInfoSubscptImpEntityVO tpl = new TplInfoSubscptImpEntityVO();
//			tpl.setFileImportCode(vo.getCodigo());
//			tpl.setFileImportDate(vo.getDataImportacao());
//			tpl.setFileImportRecQty(Util.parseBigInteger(vo.getRegistros()));
//			tpl.setImportFileNameText(vo.getNomeArquivo());
//			tpl.setLastApprvDate(new Date());
//			tpl.setLastApprvUserId(vo.getLoggedUser().getUserID());
//			tpl.setTableType(TableTypeEnum.EFFECTIVE);
//
//			TplInfoSubscptImpEntityVO eff = dao.searchImpSubscript(vo.getCodigo(), TableTypeEnum.EFFECTIVE);
//			if (eff != null){
//				dao.update(tpl);
//			}else {
//				dao.insert(tpl);
//			}
//			dao.delete(tpl, TableTypeEnum.MOVEMENT);
			vo.addMessage(BaseODSFncVO.C_MESSAGE_SUCESS);
		}else if (invokePath_.matches(".*\\.Reprove$")){
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
		return "CentrApproval.CentrApprovalMovementList.List";
	}

	@Override
	protected String extraActions(BaseFncVO fncVO_, String invokePath_) {
		String[] splittedInvokePath = invokePath_.split( "\\." );
		FundSubscriptionImportDetailFnc listFnc = ( FundSubscriptionImportDetailFnc ) getFuncionality();
	    String forwardKey = "";

	    if ( splittedInvokePath[ 2 ].equals( C_MODE_CONSULT ) )

	    {
	      listFnc.clearPage( fncVO_ );

	      return forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
	    }
	    return forwardKey;
	}

	@Override
	public String getFncVOPublishName() {
		return FundSubscriptionImportApprovalFncVO.class.getName();
	}

}
