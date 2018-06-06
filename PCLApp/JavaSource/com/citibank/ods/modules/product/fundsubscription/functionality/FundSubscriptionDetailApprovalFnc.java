package com.citibank.ods.modules.product.fundsubscription.functionality;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.logger.ApplicationLogger;
import com.citibank.ods.common.logger.LoggerFactory;
import com.citibank.ods.common.util.TableTypeEnum;
import com.citibank.ods.entity.pl.valueobject.TplDataSubscptEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplFundMortEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplInfoSubscptImpEntityVO;
import com.citibank.ods.modules.product.fundsubscription.form.valueobject.FundSubscriptionInvestorDataVO;
import com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.FundSubscriptionDetailApprovalFncVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplDataSubscptDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplFundMortDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplInfoSubscptImpDAO;

public class FundSubscriptionDetailApprovalFnc extends BaseFnc implements com.citibank.ods.common.functionality.ODSListFnc
{

	ApplicationLogger applicationLogger = LoggerFactory.getApplicationLoggerInstance();
	@Override
	public BaseFncVO createFncVO() {
		return new FundSubscriptionDetailApprovalFncVO();
	}

	public void clearPage(BaseFncVO fncVO_) {
		FundSubscriptionDetailApprovalFncVO fnc = ( FundSubscriptionDetailApprovalFncVO ) fncVO_;

	    fnc.clearErrors();
	    fnc.clearMessages();
	    
	    fnc.setNomeCliente(null);
	    fnc.setEvento(null);
	    fnc.setTipoProduto(null);
	    fnc.setNomeProduto(null);
	    fnc.setAssunto(null);
	    fnc.setAtestadoDivergencia(null);
	    fnc.setBoletimReserva(null);
	    fnc.setCcDebito(null);
	    fnc.setCodigoCorretora(null);
	    fnc.setCodigoFundo(null);
	    fnc.setCodigoInvestidor(null);
	    fnc.setComentario(null);
	    fnc.setConhecimentoProduto(null);
	    fnc.setCpfInvestidor(null);
	    fnc.setCustNbr(null);
	    fnc.setDataAbertura(null);
	    fnc.setDataResolucaoFase(null);
	    fnc.setDataSolucao(null);
	    fnc.setDocumentacao(null);
	    fnc.setFase(null);
	    fnc.setFuncionalGerente(null);
	    fnc.setNivelRisco(null);
	    fnc.setNomeAprovador(null);
	    fnc.setNomeCliente(null);
	    fnc.setNomeFundo(null);
	    fnc.setNomeInvestidor(null);
	    fnc.setOta(null);
	    fnc.setPerfilGRB(null);
	    fnc.setProtocolo(null);
	    fnc.setTermoTreinamento(null);
	    fnc.setTipoTransacao(null);
	    fnc.setValorInvestimento(null);
	    fnc.setValorNovo(null);
	    
	}

	@Override
	public void updateFormFromFncVO(ActionForm form_, BaseFncVO fncVO_) {
		try {
			BeanUtils.copyProperties(form_, fncVO_);
		}catch(Exception e) {
			applicationLogger.error("Not impeditive error in updateFormFromFncVO", e);
		}
	}
	
	@Override
	public void updateFncVOFromForm(ActionForm form_, BaseFncVO fncVO_) {
		try {
			BeanUtils.copyProperties(fncVO_, form_);
		}catch(Exception e) {
			applicationLogger.error("Not impeditive error in updateFncVOFromForm", e);
		}
	}

	public void list(BaseFncVO fncVO_) {
		
	}

	public void load(BaseFncVO fncVO_) {
		
	}

	public static void loadForApproval(BaseFncVO fncVO_){

		FundSubscriptionDetailApprovalFncVO vo = ( FundSubscriptionDetailApprovalFncVO ) fncVO_;
		OracleTplDataSubscptDAO dao = new OracleTplDataSubscptDAO();
		TplDataSubscptEntityVO d = dao.searchDataSubscrptByEnrollment(vo.getSelectedCode().replaceAll(",\\w$", ""), TableTypeEnum.MOVEMENT);
		vo.setAssunto(d.getSubjectText());
		vo.setAtestadoDivergencia("S".equals(d.getCnfrmIncpatInd())? "Sim":"Não");
		vo.setBoletimReserva("S".equals(d.getReserveInd())? "Sim":"Não");
		vo.setCcDebito(com.citibank.ods.common.util.Util.stringOf(d.getAcctNbr()));
		vo.setCodigoCorretora(com.citibank.ods.common.util.Util.isEmpty(d.getBkrCodeText())? null: d.getBkrCodeText());
		vo.setCodigoFundo(d.getFundCode());
		vo.setComentario(d.getCommentText());
		vo.setConhecimentoProduto(d.getProdKnwlgText());
		vo.setCpfInvestidor(d.getPlyrCpfNbr());
		vo.setDataAbertura(com.citibank.ods.common.util.Util.dateToString(d.getSubscptOpenDate(), "dd/MM/yyyy" ));
		vo.setDataResolucaoFase(com.citibank.ods.common.util.Util.dateToString(d.getPhaseRsltnDate(), "dd/MM/yyyy"));
		vo.setDataSolucao(com.citibank.ods.common.util.Util.dateToString(d.getSubjectSolDate(), "dd/MM/yyyy"));
		vo.setDocumentacao("S".equals(d.getDocumentCheckInd())? "Sim":"Não");
		vo.setEvento(d.getEventText());
		vo.setFase(d.getSubjectPhaseText());
		vo.setNivelRisco(d.getProdRiskLvlQty()==null?null:d.getProdRiskLvlQty().toString());
		vo.setNomeAprovador(d.getIncpatApprvNameText());
		if (d.getAcctNbr() != null){
			vo.setNomeCliente(dao.searchCustFullName(com.citibank.ods.common.util.Util.stringOf(d.getAcctNbr())));
		}
		vo.setNomeFundo(d.getFundNameText());
		vo.setNomeProduto(d.getProdNameText());
		vo.setOta("S".equals(d.getStockTrfOrderInd())? "Sim":"Não");
		if (!com.citibank.ods.common.util.Util.isEmpty(d.getPlyrCpfNbr())){
			FundSubscriptionInvestorDataVO investorVO = dao.defInvestorNameAndCode(Long.valueOf(d.getPlyrCpfNbr().replaceAll("\\D", "")));
			if (investorVO!= null){
				vo.setCodigoInvestidor(investorVO.getInvestorCode());
				vo.setNomeInvestidor(investorVO.getInvestorName());
			}
		}
		if (d.getAcctNbr()!= null){
			vo.setPerfilGRB(dao.getPerfGrb(d.getAcctNbr().longValue()));
			vo.setFuncionalGerente(dao.searchOffcrNbr(d.getAcctNbr().longValue()));
		}
		vo.setProtocolo(d.getEnrollment().toString());
		vo.setTermoTreinamento("S".equals(d.getTrainingTermInd())? "Sim":"Não");
		vo.setTipoProduto(d.getProdImpTypeCode());
		vo.setTipoTransacao(d.getTransactionTypeText());
		vo.setValorInvestimento(com.citibank.ods.common.util.Util.stringOf(d.getInvestimentAmt()));
		vo.setValorNovo(com.citibank.ods.common.util.Util.stringOf(d.getAddDiffAmt()));
		
		vo.setCanApprove(d.getLastUpdUserId() == null?false:!d.getLastUpdUserId().equals(vo.getLoggedUser().getUserID())  );
	}
	
	public void validateList(BaseFncVO fncVO_) {
	}


	public static void approve(FundSubscriptionDetailApprovalFncVO vo) {
		OracleTplDataSubscptDAO dao = new OracleTplDataSubscptDAO();
		TplDataSubscptEntityVO voInMov = dao.searchDataSubscrptByEnrollment(vo.getProtocolo(), TableTypeEnum.MOVEMENT);
		String userID = vo.getLoggedUser().getUserID();
		String protocol = vo.getProtocolo();
		if (voInMov!=null){
			if ("E".equals(voInMov.getOpernTypeCode())){
				dao.delete(protocol, TableTypeEnum.EFFECTIVE);
				dao.delete(protocol, TableTypeEnum.MOVEMENT);
				voInMov.setLastApprvDate(new Date());
				voInMov.setLastApprvUserId(userID);
				voInMov.setTableType(TableTypeEnum.HISTORIC);
				dao.insert(voInMov);
			}else {
				FundSubscriptionImportDetailFnc.defineFund(userID, voInMov, vo.getNomeFundo(), true);
				insertDataSubscrpt(dao, voInMov, userID, protocol);
			}
			vo.addMessage(BaseODSFncVO.C_MESSAGE_SUCESS);
		}
	}

	public static void insertDataSubscrpt(OracleTplDataSubscptDAO dao,
			TplDataSubscptEntityVO voInMov, String userID, String protocol) {
		TplDataSubscptEntityVO voEffective = dao.searchDataSubscrptByEnrollment(protocol, TableTypeEnum.EFFECTIVE);
		if (voEffective!=null){
			voInMov.setLastApprvDate(new Date());
			voInMov.setLastApprvUserId(userID);
			voInMov.setTableType(TableTypeEnum.EFFECTIVE);
			voInMov.setFileImportCode(voEffective.getFileImportCode());//Necessário para que o mesmo código de importação seja mantido
			dao.update(voInMov);
			dao.delete(voInMov.getEnrollment(), TableTypeEnum.MOVEMENT);
			voEffective.setTableType(TableTypeEnum.HISTORIC);
			voEffective.setLastApprvDate(new Date());
			voEffective.setLastApprvUserId(userID);
			dao.insert(voEffective);
		}else {
			voInMov.setLastApprvDate(new Date());
			voInMov.setLastApprvUserId(userID);
			voInMov.setTableType(TableTypeEnum.EFFECTIVE);
			dao.insert(voInMov);
			dao.delete(voInMov.getEnrollment(), TableTypeEnum.MOVEMENT);
		}
		if (!com.citibank.ods.common.util.Util.isEmpty(voInMov.getFundNameText())){
			OracleTplFundMortDAO fundDao = new OracleTplFundMortDAO();
			TplFundMortEntityVO fundsEffective = fundDao.getFundsByName(voInMov.getFundNameText(), TableTypeEnum.EFFECTIVE);
			if (fundsEffective==null){
				TplFundMortEntityVO fundsMov = fundDao.getFundsByName(voInMov.getFundNameText(), TableTypeEnum.MOVEMENT);
				if (fundsMov != null){
					fundsMov.setLastUpdDate(new Date());
					fundsMov.setLastApprvDate(new Date());
					fundsMov.setLastUpdUserId(userID);
					fundsMov.setTableType(TableTypeEnum.EFFECTIVE);
					fundDao.insert(fundsMov);
				}
			}
		}
	}


	public static void repprove(FundSubscriptionDetailApprovalFncVO vo) {
		OracleTplDataSubscptDAO dao = new OracleTplDataSubscptDAO();
		dao.delete(vo.getProtocolo(), TableTypeEnum.MOVEMENT);
		vo.addMessage(BaseODSFncVO.C_MESSAGE_SUCESS);
	}
	
	public static void approveimport(Long impCode, String userID){
		
		OracleTplDataSubscptDAO dataDao = new OracleTplDataSubscptDAO();
		OracleTplInfoSubscptImpDAO subDao = new OracleTplInfoSubscptImpDAO();
		
		TplInfoSubscptImpEntityVO impSubscriptMov = subDao.searchImpSubscript(impCode, TableTypeEnum.MOVEMENT);
		if (impSubscriptMov!= null){//is never supposed to be null
			impSubscriptMov.setTableType(TableTypeEnum.EFFECTIVE);
			impSubscriptMov.setLastApprvDate(new Date());
			impSubscriptMov.setLastApprvUserId(userID);
			subDao.insert(impSubscriptMov);
			List<TplDataSubscptEntityVO> dataSubscrpts = dataDao.searchDataSubscrptImportCode(impCode, TableTypeEnum.MOVEMENT);
			if (dataSubscrpts!= null){
				for (TplDataSubscptEntityVO vo : dataSubscrpts){
					FundSubscriptionImportDetailFnc.defineFund(userID, vo, vo.getFundNameText(), true);
					insertDataSubscrpt(dataDao, vo, userID, vo.getEnrollment());
				}
			}
			subDao.delete(impCode, TableTypeEnum.MOVEMENT);
		}
	}
	
}
