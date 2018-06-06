package com.citibank.ods.modules.product.fundsubscription.action;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.citibank.ods.common.action.BaseAction;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.logger.ApplicationLogger;
import com.citibank.ods.common.user.User;
import com.citibank.ods.common.util.TableTypeEnum;
import com.citibank.ods.entity.pl.valueobject.TplDataSubscptEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplInfoSubscptImpEntityVO;
import com.citibank.ods.modules.product.fundsubscription.form.FundSubscriptionImportDetailForm;
import com.citibank.ods.modules.product.fundsubscription.functionality.FundSubscriptionImportDetailFnc;
import com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.FundSubscriptionImportDetailVO;
import com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.FundSubscriptionImportOthersDetailVO;
import com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplDataSubscptDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplInfoSubscptImpDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

public class FundsSubscriptionImportAction extends Action {

	ApplicationLogger logger = ApplicationLogger.getInstance();

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OracleTplDataSubscptDAO daoSubscription = new OracleTplDataSubscptDAO();
		ManagedRdbConnection connection = daoSubscription.getConnection();
		connection.setAutoCommit(false);
		
		try {
			FundSubscriptionImportDetailForm f = (FundSubscriptionImportDetailForm)form;
			List<String[]> messages = new ArrayList<String[]>();
			List<? extends IFundSubscriptionImportVO> readed = null;
			ActionMessages actionMessages = new ActionMessages();
			try {
				com.citibank.ods.common.util.Util.isEmpty(f.getProductType());
				
				if ("F".equals(f.getProductType())){
					readed = FundSubscriptionImportDetailFnc.readXlsFile(f.getFile().getInputStream(), FundSubscriptionImportDetailVO.class, messages);
				}else{
					readed = FundSubscriptionImportDetailFnc.readXlsFile(f.getFile().getInputStream(), FundSubscriptionImportOthersDetailVO.class, messages);
				}
			}catch(Exception e){
				String msg = "ERROR_IMPORT_GENERIC";
				if (e instanceof InvalidFormatException){
					msg = "ERROR_IMPORT_FORMAT";
				}
				actionMessages.add(null, new ActionMessage(msg));
				this.addErrors(request, actionMessages);
				return mapping.getInputForward();
			}
			
			if (readed ==null || readed.size() ==0){
				
				actionMessages.add(null, new ActionMessage("ERROR_NO_DATA" ));
				this.addErrors(request, actionMessages);
				return mapping.getInputForward();
			}
			
			OracleTplInfoSubscptImpDAO dao = new OracleTplInfoSubscptImpDAO();
			Long cd = dao.getSequence();
			TplInfoSubscptImpEntityVO voImp = new TplInfoSubscptImpEntityVO();
			voImp.setFileImportCode(cd);
			voImp.setFileImportDate(new Date());
			voImp.setFileImportRecQty(BigInteger.valueOf(readed.size()));
			voImp.setImportFileNameText(f.getFile().getFileName());
			voImp.setLastUpdDate(new Date());
			User u = (User)request.getSession().getAttribute(BaseAction.C_USER_SESSION_ID );
			voImp.setLastUpdUserId(u.getUserID());
			voImp.setTableType(TableTypeEnum.MOVEMENT);
			voImp.setOpernTypeCode("I");
			dao.setConnection(daoSubscription.getConnection());
			dao.insert(voImp, false);
			List<IFundSubscriptionImportVO> fundsAlreadyImported = new ArrayList<IFundSubscriptionImportVO>();
			int importAmt = 0;
			for (IFundSubscriptionImportVO vo : readed) {
				if (vo.getProtocolo() == null){
					continue;
				}
				if (daoSubscription.existsDataSubscrpt(String.valueOf(vo.getProtocolo()), TableTypeEnum.EFFECTIVE)){
					fundsAlreadyImported.add(vo);
					actionMessages.add(null, new ActionMessage("ERROR_IMPORT_ALREADY_IMPORTED", String.valueOf(vo.getProtocolo())));
					continue;
				}
				TplDataSubscptEntityVO d = new TplDataSubscptEntityVO();
				if (daoSubscription.existsDataSubscrpt(String.valueOf(vo.getProtocolo()), TableTypeEnum.MOVEMENT)){
					fundsAlreadyImported.add(vo);
					actionMessages.add(null, new ActionMessage("ERROR_IMPORT_ALREADY_IMPORTED_APROV", String.valueOf(vo.getProtocolo())));
					continue;
				}
				if (vo instanceof FundSubscriptionImportOthersDetailVO){
					d.setProdNameText(vo.getNomeDoFundo());
				}else {
					String fundName = vo.getNomeDoFundo();
//					FundSubscriptionImportDetailFnc.defineFund(u, d, fundName, true);
					d.setFundNameText(fundName);
				}
				d.setSubscptOpenDate(vo.getDataDeAbertura());
				d.setSubjectText(vo.getAssunto());
				d.setEventText(vo.getEvento());
				d.setSubjectSolDate(vo.getDataDeSolucao());
				d.setSubjectPhaseText(vo.getFase());
				d.setPhaseRsltnDate(vo.getDataDaResolucaoDaFase());
				d.setCommentText(vo.getComentario());
				d.setReserveInd(vo.getBoletimReservaOK()?"S":"N");
				d.setCnfrmIncpatInd(vo.getAttestationDeMismatch()?"S":"N");
				d.setTrainingTermInd(vo.getTermoDeTreinamentoOK()?"S":"N");
				d.setBkrCodeText(vo.getCodigoCorretora()==null? null:String.valueOf(vo.getCodigoCorretora()));
				d.setProdKnwlgText(vo.getConhecimentoDoProduto());
				d.setPlyrCpfNbr(vo.getCpfDoInvestidor()==null?null:vo.getCpfDoInvestidor().toString());
				d.setProdRiskLvlQty(vo.getNivelDeRiscoDoProduto());
				d.setIncpatApprvNameText(vo.getNomeDoAprovadorDoMismatch());
				d.setTransactionTypeText(vo.getTipoDeTransacao());
				d.setAcctNbr(BigInteger.valueOf(vo.getContaCorrenteDoDebito()));
				
				
//				String custFullName = daoSubscription.searchCustFullName(vo.getContaCorrenteDoDebito().toString());
				if (!daoSubscription.existsAccount(vo.getContaCorrenteDoDebito().toString())){
					messages.add(new String[]{"ERROR_IMPORT_NO_ACCT_FOUND",vo.getContaCorrenteDoDebito().toString() });
					fundsAlreadyImported.add(vo);
				}
				d.setAddDiffAmt(com.citibank.ods.common.util.Util.parseBigDecimal(vo.getQuantoDesteValorNovo()));
				d.setInvestimentAmt(com.citibank.ods.common.util.Util.parseBigDecimal(vo.getValorDoInvestimento()));
				d.setEnrollment(String.valueOf(vo.getProtocolo()));
				d.setProdImpTypeCode(f.getProductType());
				d.setFileImportCode(BigInteger.valueOf(voImp.getFileImportCode()));
				d.setTableType(TableTypeEnum.MOVEMENT);
				d.setLastUpdDate(new Date());
				d.setLastUpdUserId(u.getUserID());
				if (messages.size() == 0){
					importAmt ++;
					daoSubscription.insert(d, false);
				}
			}
			for (String[] m : messages){
				actionMessages.add(null, new ActionMessage(m[0], ArrayUtils.subarray(m, 1, m.length)));
			}
			
			if (importAmt > 0){
				connection.commit();
				ActionMessages confirmacao = new ActionMessages();
				confirmacao.add(null,new ActionMessage("SUCCESS_IMPORT",importAmt));
				this.addMessages(request, confirmacao);
			}else {
				connection.rollback();
				actionMessages.add(null, new ActionMessage("ERROR_NO_DATA" ));
			}
			
			this.addErrors(request, actionMessages);
			return mapping.getInputForward();
		}catch(Exception e){
			connection.rollback();
			logger.error("Erro na importação do arquivo", e);
			System.err.println(e);
			e.printStackTrace();
			throw e;
		}finally{
			connection.setAutoCommit(true);
			connection.close();
		}
	}

}
