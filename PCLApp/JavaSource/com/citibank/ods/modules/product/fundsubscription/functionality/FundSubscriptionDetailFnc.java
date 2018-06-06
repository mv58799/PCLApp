package com.citibank.ods.modules.product.fundsubscription.functionality;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.logger.ApplicationLogger;
import com.citibank.ods.common.logger.LoggerFactory;
import com.citibank.ods.common.util.TableTypeEnum;
import com.citibank.ods.common.util.Util;
import com.citibank.ods.entity.pl.valueobject.TplDataSubscptEntityVO;
import com.citibank.ods.modules.product.fundsubscription.form.valueobject.FundSubscriptionInvestorDataVO;
import com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.FundSubscriptionDetailFncVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplDataSubscptDAO;

public class FundSubscriptionDetailFnc extends BaseFnc{

	ApplicationLogger applicationLogger = LoggerFactory.getApplicationLoggerInstance();
	
	@Override
	public BaseFncVO createFncVO() {
		return new FundSubscriptionDetailFncVO();
	}

	public void clearPage(BaseFncVO fncVO_) {
		FundSubscriptionDetailFncVO fnc = ( FundSubscriptionDetailFncVO ) fncVO_;

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
	
	
	public static void delete(FundSubscriptionDetailFncVO vo) {
		OracleTplDataSubscptDAO daoSubscription = new OracleTplDataSubscptDAO();
		String enrollment = vo.getProtocolo();
		TplDataSubscptEntityVO existMov = daoSubscription.searchDataSubscrptByEnrollment(vo.getProtocolo(), TableTypeEnum.MOVEMENT);
		if (existMov!= null && !com.citibank.ods.common.util.Util.isEmpty(vo.getSelectedCode())){
				vo.addError("CANNOT_UPD_DELETE_IN_MOVEMENT");
		}else { 
			TplDataSubscptEntityVO d = daoSubscription.searchDataSubscrptByEnrollment(enrollment, TableTypeEnum.EFFECTIVE);
			d.setOpernTypeCode("E");
			d.setTableType(TableTypeEnum.MOVEMENT);
			insertOrUpdateIfExists(daoSubscription, d);
			vo.addMessage(BaseODSFncVO.C_MESSAGE_SUCESS);
		}
	}

	public static void update(FundSubscriptionDetailFncVO vo) {
		final String msgErro = "MANDATORY_FIELD"; 
		OracleTplDataSubscptDAO daoSubscription = new OracleTplDataSubscptDAO();
		TplDataSubscptEntityVO existMov = daoSubscription.searchDataSubscrptByEnrollment(vo.getProtocolo(), TableTypeEnum.MOVEMENT);
		String selectedCode = vo.getSelectedCode() ==null? null: vo.getSelectedCode().replaceAll(",\\w$","");
		if (existMov!= null && com.citibank.ods.common.util.Util.isEmpty(selectedCode)){
				vo.addError("IN_MOVEMENT");
		}else {
			TplDataSubscptEntityVO d = fillVOForInsertOrUpdate(msgErro, vo);
			d.setOpernTypeCode("A");
			if (!vo.hasErrors()){
				insertOrUpdateIfExists(daoSubscription, d);
				vo.addMessage(BaseODSFncVO.C_MESSAGE_SUCESS);
			}
		}
	}

	public static void insertOrUpdateIfExists(
			OracleTplDataSubscptDAO daoSubscription, TplDataSubscptEntityVO d) {
		TplDataSubscptEntityVO mov = daoSubscription.searchDataSubscrptByEnrollment(d.getEnrollment(), TableTypeEnum.MOVEMENT);
//		OracleTplInfoSubscptImpDAO dao = new OracleTplInfoSubscptImpDAO();
//		if (d.getFileImportCode()!=null){
//			TplInfoSubscptImpEntityVO impSubMov = dao.searchImpSubscript(d.getFileImportCode().longValue(), TableTypeEnum.MOVEMENT);
//			if (impSubMov == null){
//				TplInfoSubscptImpEntityVO impSubEff = dao.searchImpSubscript(d.getEnrollment(), TableTypeEnum.EFFECTIVE);
//				impSubEff.setTableType(TableTypeEnum.MOVEMENT);
//				impSubEff.setLastUpdDate(new Date());
//				impSubEff.setLastUpdUserId(d.getLastUpdUserId());
//				dao.
//			}
//		}
		d.setFileImportCode(null);
		if (mov == null){
			daoSubscription.insert(d);
		}else {
			daoSubscription.update(d);
		}
	}

	public static void insert(FundSubscriptionDetailFncVO vo) {
		final String msgErro = "MANDATORY_FIELD"; 
		OracleTplDataSubscptDAO daoSubscription = new OracleTplDataSubscptDAO();
		TplDataSubscptEntityVO d = fillVOForInsertOrUpdate(msgErro, vo);
		d.setOpernTypeCode("I");
		
		TplDataSubscptEntityVO voEff = daoSubscription.searchDataSubscrptByEnrollment(d.getEnrollment(), TableTypeEnum.EFFECTIVE);
		TplDataSubscptEntityVO voMov = daoSubscription.searchDataSubscrptByEnrollment(d.getEnrollment(), TableTypeEnum.MOVEMENT);
		
		if (voEff != null || voMov != null ){
			vo.addError("DUPLICATE_PK");
		}
		
		if (!vo.hasErrors()){
			daoSubscription.insert(d);
			vo.addMessage(BaseODSFncVO.C_MESSAGE_SUCESS);
		}
	}


	public static void loadToShow(FundSubscriptionDetailFncVO vo) {
		OracleTplDataSubscptDAO dao = new OracleTplDataSubscptDAO();
		TplDataSubscptEntityVO d = null;
		String selectedCode = vo.getSelectedCode() ==null? null: vo.getSelectedCode().replaceAll(",\\w$","");
		if (!com.citibank.ods.common.util.Util.isEmpty(selectedCode)){
			vo.setProtocolo(selectedCode);
			d = dao.searchDataSubscrptByEnrollment(selectedCode, TableTypeEnum.MOVEMENT);
			vo.setBlockConfirm(false);
		}else {
			d = dao.searchDataSubscrptByEnrollment(vo.getProtocolo(), TableTypeEnum.EFFECTIVE);
			if (d==null)
				return;
			
			TplDataSubscptEntityVO dInMOv = dao.searchDataSubscrptByEnrollment(d.getEnrollment(), TableTypeEnum.MOVEMENT);
			if (dInMOv != null){
				vo.addError("IN_MOVEMENT");
				vo.setBlockConfirm(true);
			}else {
				vo.setBlockConfirm(false);
			}
		}
		if (d==null)
			return;
		
		vo.setAssunto(d.getSubjectText());
		vo.setAtestadoDivergencia("S".equals(d.getCnfrmIncpatInd())? "S":"N");
		vo.setBoletimReserva("S".equals(d.getReserveInd())? "S":"N");
		vo.setCcDebito(com.citibank.ods.common.util.Util.stringOf(d.getAcctNbr()));
		vo.setCodigoCorretora(d.getBkrCodeText());
		vo.setCodigoFundo(d.getFundCode());
		vo.setComentario(d.getCommentText());
		vo.setConhecimentoProduto(d.getProdKnwlgText());
		vo.setCpfInvestidor(d.getPlyrCpfNbr());
		vo.setDataAbertura(com.citibank.ods.common.util.Util.dateToString(d.getSubscptOpenDate(), "dd/MM/yyyy" ));
		vo.setDataResolucaoFase(com.citibank.ods.common.util.Util.dateToString(d.getPhaseRsltnDate(), "dd/MM/yyyy"));
		vo.setDataSolucao(com.citibank.ods.common.util.Util.dateToString(d.getSubjectSolDate(), "dd/MM/yyyy"));
		vo.setDocumentacao("S".equals(d.getDocumentCheckInd())? "S":"N");
		vo.setEvento(d.getEventText());
		vo.setFase(d.getSubjectPhaseText());
		vo.setNivelRisco(d.getProdRiskLvlQty()==null?null:d.getProdRiskLvlQty().toString());
		vo.setNomeAprovador(d.getIncpatApprvNameText());
		vo.setNomeCliente(d.getCustName());
		vo.setNomeFundo(d.getFundNameText());
		vo.setNomeProduto(d.getProdNameText());
		vo.setOta("S".equals(d.getStockTrfOrderInd())? "S":"N");
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
		vo.setTermoTreinamento("S".equals(d.getTrainingTermInd())? "S":"N");
		vo.setTipoProduto("F".equals(d.getProdImpTypeCode())? "F":"O");
		vo.setTipoTransacao(d.getTransactionTypeText());
		vo.setValorInvestimento(com.citibank.ods.common.util.Util.stringOf(d.getInvestimentAmt()));
		vo.setValorNovo(com.citibank.ods.common.util.Util.stringOf(d.getAddDiffAmt()));
	}
	
	public static TplDataSubscptEntityVO fillVOForInsertOrUpdate(final String msgErro,
			FundSubscriptionDetailFncVO vo) {
		TplDataSubscptEntityVO d = new TplDataSubscptEntityVO();
		Date dataAbertura = com.citibank.ods.common.util.Util.parseDate(vo.getDataAbertura());
		if (dataAbertura!=null){
			d.setSubscptOpenDate(dataAbertura);
		}else {
			vo.addError(msgErro, "Data de Abertura");
		}
		if (!com.citibank.ods.common.util.Util.isEmpty(vo.getAssunto()))
			d.setSubjectText(vo.getAssunto());
		else 
			vo.addError(msgErro, "Assunto");
			
		
//		FundSubscriptionImportDetailFnc.defineFund(vo.getLoggedUser(), d, vo.getNomeFundo());
		
		if (Util.isEmpty(vo.getNomeFundo()) && "F".equals(vo.getTipoProduto())){
			vo.addError(msgErro, "Nome do Fundo");
		}
		
		
		
		d.setEventText(vo.getEvento());
		d.setSubjectSolDate(com.citibank.ods.common.util.Util.parseDate(vo.getDataSolucao()));
		d.setSubjectPhaseText(vo.getFase());
		d.setPhaseRsltnDate(com.citibank.ods.common.util.Util.parseDate(vo.getDataResolucaoFase()));
		d.setCommentText(vo.getComentario());
		d.setReserveInd(vo.getBoletimReserva());
		d.setCnfrmIncpatInd(vo.getAtestadoDivergencia());
		d.setTrainingTermInd(vo.getTermoTreinamento());
		d.setBkrCodeText(vo.getCodigoCorretora()==null?null: String.valueOf(vo.getCodigoCorretora()));
		d.setProdKnwlgText(vo.getConhecimentoProduto());
		if (!com.citibank.ods.common.util.Util.isEmpty(vo.getCpfInvestidor()) && !com.citibank.ods.common.util.Util.isEmpty(vo.getCpfInvestidor().replaceAll("\\D", ""))){
			d.setPlyrCpfNbr(vo.getCpfInvestidor().replaceAll("\\D", ""));
		}else {
			vo.addError(msgErro, "CPF do investidor");
		}
		
		d.setProdRiskLvlQty(com.citibank.ods.common.util.Util.isEmpty(vo.getNivelRisco())?null:Long.valueOf(vo.getNivelRisco()));
		d.setIncpatApprvNameText(vo.getNomeAprovador());
		d.setTransactionTypeText(vo.getTipoTransacao());
		if (!com.citibank.ods.common.util.Util.isEmpty(vo.getCcDebito())){
			d.setAcctNbr(com.citibank.ods.common.util.Util.parseBigInteger(vo.getCcDebito()));
			String custFullName = new OracleTplDataSubscptDAO().searchCustFullName(vo.getCcDebito());
			if (com.citibank.ods.common.util.Util.isEmpty(custFullName)){
				vo.addError("ERROR_IMPORT_NO_ACCT_FOUND");
			}
		}else {	
			vo.addError(msgErro, "Conta corrente do débito");
		}
		if (!com.citibank.ods.common.util.Util.isEmpty(vo.getValorNovo())){
			d.setAddDiffAmt(com.citibank.ods.common.util.Util.parseBigDecimal(vo.getValorNovo()));
		}else {
			vo.addError(msgErro, "Valor Novo -> Diff Amt");
		}
		
		if (!com.citibank.ods.common.util.Util.isEmpty(vo.getValorInvestimento())){
			d.setInvestimentAmt(com.citibank.ods.common.util.Util.parseBigDecimal(vo.getValorInvestimento()));
		}else {
			vo.addError(msgErro, "Valor Investimento");
		}
		
		if (!com.citibank.ods.common.util.Util.isEmpty(vo.getProtocolo())){
			d.setEnrollment(vo.getProtocolo());
		}else{
			vo.addError(msgErro, "Nº Protocolo");
		}
		if (!com.citibank.ods.common.util.Util.isEmpty(vo.getTipoProduto())){
			d.setProdImpTypeCode(vo.getTipoProduto());
			if ("F".equals(vo.getTipoProduto())){
				d.setProdNameText(null);
				d.setFundNameText(vo.getNomeFundo());
			}else {
				d.setFundNameText(null);
				d.setProdNameText(vo.getNomeProduto());
			}
			
		}else {
			vo.addError(msgErro, "Tipo de Produto");
		}
		
		
		d.setFileImportCode(null);
		d.setTableType(TableTypeEnum.MOVEMENT);
		d.setLastUpdDate(new Date());
		d.setLastUpdUserId(vo.getLoggedUser().getUserID());
		d.setDocumentCheckInd(vo.getDocumentacao());
		d.setStockTrfOrderInd(vo.getOta());
		return d;
	}

	
	
}
