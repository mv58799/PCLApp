package com.citibank.newcpb.action;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.citibank.newcpb.bean.ResultTableBean;
import com.citibank.newcpb.enums.ScreenNamesEnum;
import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.enun.AccountClosingReasonCitibankEnum;
import com.citibank.newcpb.enun.AccountClosingReasonExternalEnum;
import com.citibank.newcpb.enun.AccountTypeEnum;
import com.citibank.newcpb.enun.AccountTypeRDIPEnum;
import com.citibank.newcpb.form.AccountComplementDataForm;
import com.citibank.newcpb.form.CentralApprovalForm;
import com.citibank.newcpb.form.RegisterDataCustomerForm;
import com.citibank.newcpb.logger.ApplicationLogger;
import com.citibank.newcpb.logger.LoggerFactory;
import com.citibank.newcpb.service.AccountComplementDataService;
import com.citibank.newcpb.service.ValidationService;
import com.citibank.newcpb.util.FormatUtils;
import com.citibank.newcpb.vo.AcctCmplVO;
import com.citibank.newcpb.vo.EgVO;
import com.citibank.newcpb.vo.RegisterDataCustomerVO;
import com.citibank.ods.common.action.BaseAction;
import com.citibank.ods.common.user.User;

public class AccountComplementDataAction extends CommonAction {
	

	public final ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward actionForward = null;
		ActionErrors actionErrors = null;
		User loggedUser = null;
		ApplicationLogger applicationLogger = null;
		String forwardKey = "";
		
		try {

			LoggerFactory.initialize();
			applicationLogger = LoggerFactory.getApplicationLoggerInstance();
			handleLocale(request);
			handleForm(actionForm, request);			
			actionErrors = actionForm.validate(actionMapping, request);
			AccountComplementDataForm accountComplementDataForm = (AccountComplementDataForm) actionForm;
			AccountComplementDataService service = new AccountComplementDataService();
			
			
			try {
				HttpSession session = request.getSession();
				loggedUser = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
				accountComplementDataForm.setShowConfirmInsertPopup(false);
				accountComplementDataForm.setShowConfirmRedirectPageRiskPopup(false);
				accountComplementDataForm.setShowConfirmDeletePopup(false);
				accountComplementDataForm.setLoggedUser(loggedUser);
				accountComplementDataForm.setApprove(false);
				accountComplementDataForm.setOnlyView(false);
				accountComplementDataForm.setHasEmList(false);
				accountComplementDataForm.setHasAccountAss(false);
				accountComplementDataForm.setApprovedDisapproved(false);
				accountComplementDataForm.setUpdateFromApprove(false);
				accountComplementDataForm.setIdDiffList("");				
				accountComplementDataForm.clearErrors();
				accountComplementDataForm.clearMessages();					

				boolean hasDataFormErrors = actionErrors != null && !actionErrors.isEmpty();

				accountComplementDataForm.clearErrors();
				accountComplementDataForm.clearMessages();
				
				String method = request.getParameter("method");
				applicationLogger.debug(className + " invoke:" + method);

				if (!hasDataFormErrors) {
					
					if(StringUtils.isBlank(method)){
						request.getSession().removeAttribute("resultListAccountComplementData");
						accountComplementDataForm.setAccountTypeValues(AccountTypeEnum.getAccountTypeList());
						accountComplementDataForm.setAccountTypeRDIPValues(AccountTypeRDIPEnum.getAccountTypeRDIPList());
						forwardKey = "search";	
						
					}else if(StringUtils.isBlank(method) || "search".equalsIgnoreCase(method)){
						
						accountComplementDataForm.setAccountTypeValues(AccountTypeEnum.getAccountTypeList());
						accountComplementDataForm.setScreenTitle("Conta - Dados Complementares");
						
						request.getSession().removeAttribute("resultListAccountComplementData");
						if(StringUtils.isBlank(accountComplementDataForm.getFilterNumberAccount()) 
								&& StringUtils.isBlank(accountComplementDataForm.getFilterNumberCpfCnpj()) &&
								StringUtils.isBlank(accountComplementDataForm.getFilterAccountType())){
							
							accountComplementDataForm.addError(AccountComplementDataForm.C_ERROR_EMPTY_FILTER);
							forwardKey = "search";
							
						}else{

							ArrayList<AcctCmplVO> resultListAccountComplementData = service.list(accountComplementDataForm);	
							
							if(resultListAccountComplementData!=null && resultListAccountComplementData.size()>0){
								
								request.getSession().setAttribute("resultListAccountComplementData", resultListAccountComplementData);	
								
							}else{
								
								request.getSession().removeAttribute("resultListAccountComplementData");
								accountComplementDataForm.addMessage(CentralApprovalForm.C_NO_REGISTER_FOUND);								
							}

							forwardKey = "search";
						}

					}else if(method!=null && "openAccountComplementUpdateView".equalsIgnoreCase(method)){
						
						
						AcctCmplVO vo = null;
						String fromApprove = request.getParameter("fromApprove");
						
						if(!StringUtils.isBlank(fromApprove) && fromApprove.equals("true")){
							
							String accNbr = request.getParameter("processCode1");
							String cpfCnpj = request.getParameter("processCode2");
							String accType = request.getParameter("processCode3");
							
							accountComplementDataForm.setFromApprove(true);
							vo = service.getAccountComplementMov(accNbr, cpfCnpj,accType);
							
							accountComplementDataForm.setScreenTitle("Conta - Alterar Dados Complementares");
							
							
							ArrayList<ResultTableBean> accountClosingReasonValues = null;
							
							if(vo!=null){
								if(vo.getAccountType()!=null && vo.getAccountType().equals("T")){
									accountClosingReasonValues = AccountClosingReasonExternalEnum.getAccountClosingReasonExternalList();
								}else if(vo.getAccountType()!=null && vo.getAccountType().equals("C")){
									accountClosingReasonValues = AccountClosingReasonCitibankEnum.getAccountClosingCitiBankList();
								}			
							}
							
							accountComplementDataForm.setAccountClosingReasonValues(accountClosingReasonValues);
							accountComplementDataForm.setAccountTypeValues(AccountTypeEnum.getAccountTypeList());							
							accountComplementDataForm.setSelectRegisterAccountComplement(vo);
							accountComplementDataForm.setAccountTypeRDIPValues(AccountTypeRDIPEnum.getAccountTypeRDIPList());
							
							if (StringUtils.isBlank(vo.getErNbr())) {
								accountComplementDataForm.setHasEmList(false);
								accountComplementDataForm.setSelectRegister(new EgVO());														
							}else{
								ArrayList<RegisterDataCustomerVO> resultList = service.searchCustomerListByER(vo.getErNbr());							
								if (resultList != null && resultList.size()>0) {
									EgVO egVo = new EgVO();
									egVo.setCustomerList(resultList);
									egVo.setErNbr(vo.getErNbr());
									accountComplementDataForm.setHasEmList(true);
									accountComplementDataForm.setSelectRegister(egVo);	
								}
							}	

							request.getSession().setAttribute("AccountComplementData", accountComplementDataForm);
							
							forwardKey = "viewInsert";
									
						}else{
							
							
							if(accountComplementDataForm.getLoggedUser()!=null && accountComplementDataForm.getLoggedUser().getUserAccess()!=null 
									&& !accountComplementDataForm.getLoggedUser().getUserAccess().isHasAccessNovoCPBContaAtualDadosCompdaConta()){
								accountComplementDataForm.setOnlyView(true);
							}
							
							accountComplementDataForm.setFromApprove(false);
							
							if(!service.hasRegisterMov(accountComplementDataForm.getSelectRegisterAcctNbr(), 
									accountComplementDataForm.getSelectRegisterCpfCnpjNbr(), accountComplementDataForm.getSelectRegisterAccountType())){
								
								vo = service.getAccountComplement(accountComplementDataForm.getSelectRegisterAcctNbr(), 
										accountComplementDataForm.getSelectRegisterCpfCnpjNbr(), accountComplementDataForm.getSelectRegisterAccountType());
								
								
								accountComplementDataForm.setScreenTitle("Conta - Alterar Dados Complementares");
								
								
								ArrayList<ResultTableBean> accountClosingReasonValues = null;
								
								if(vo!=null){
									if(vo.getAccountType()!=null && vo.getAccountType().equals("T")){
										accountClosingReasonValues = AccountClosingReasonExternalEnum.getAccountClosingReasonExternalList();
									}else if(vo.getAccountType()!=null && vo.getAccountType().equals("C")){
										accountClosingReasonValues = AccountClosingReasonCitibankEnum.getAccountClosingCitiBankList();
									}			
								}
								
								accountComplementDataForm.setAccountClosingReasonValues(accountClosingReasonValues);
								accountComplementDataForm.setAccountTypeValues(AccountTypeEnum.getAccountTypeList());
								accountComplementDataForm.setAccountTypeRDIPValues(AccountTypeRDIPEnum.getAccountTypeRDIPList());
								accountComplementDataForm.setSelectRegisterAccountComplement(vo);
								
								if (StringUtils.isBlank(vo.getErNbr())) {
									accountComplementDataForm.setHasEmList(false);
									accountComplementDataForm.setSelectRegister(new EgVO());														
								}else{
									ArrayList<RegisterDataCustomerVO> resultList = service.searchCustomerListByER(vo.getErNbr());							
									if (resultList != null && resultList.size()>0) {
										EgVO egVo = new EgVO();
										egVo.setCustomerList(resultList);
										egVo.setErNbr(vo.getErNbr());
										accountComplementDataForm.setHasEmList(true);
										accountComplementDataForm.setSelectRegister(egVo);	
									}
								}	

								request.getSession().setAttribute("AccountComplementData", accountComplementDataForm);
								
								forwardKey = "viewInsert";
								
							}else{
								
								accountComplementDataForm.addError(RegisterDataCustomerForm.C_ERROR_REGISTER_BLOCK);
								saveMessages(request, cloneMessage(accountComplementDataForm));
								saveErrors(request, (ActionMessages) cloneErrors(accountComplementDataForm));
								request.getSession().setAttribute("AccountComplementDataForm", accountComplementDataForm);
								forwardKey = "search";
							}	
						}
				
						
					}else if (method!=null && method.equals("save")){
											
							ValidationService validationService = new ValidationService();
							validationService.validate(accountComplementDataForm);
							
							AcctCmplVO acctCmplVO = accountComplementDataForm.getSelectRegisterAccountComplement();						
							if(accountComplementDataForm.getErrors()!=null && accountComplementDataForm.getErrors().size()==0){
								acctCmplVO.setLastUpdUser(accountComplementDataForm.getLoggedUser().getUserID());
								if(acctCmplVO !=null && acctCmplVO.getTableOrigin()!=null){
									if(acctCmplVO.getTableOrigin().equals(TableTypeEnum.EFFECTIVE)){
										service.saveForAprove(acctCmplVO);
										accountComplementDataForm.setOnlyView(true);
										accountComplementDataForm.addMessage(RegisterDataCustomerForm.C_MESSAGE_SUCESS);
									}else if(acctCmplVO.getTableOrigin().equals(TableTypeEnum.MOVEMENT)){
										service.updateMov(acctCmplVO);
										accountComplementDataForm.addMessage(RegisterDataCustomerForm.C_MESSAGE_SUCESS);
									}
								}								
							} 
						
							saveMessages(request, cloneMessage(accountComplementDataForm));
							saveErrors(request, (ActionMessages) cloneErrors(accountComplementDataForm));
							request.getSession().setAttribute("AccountComplementDataForm", accountComplementDataForm);
							forwardKey = "viewInsert";

					
					
					} else if("viewApproveReprove".equalsIgnoreCase(method)){
						
						accountComplementDataForm.setFromApprove(true);
							if(accountComplementDataForm.getLoggedUser()!=null && accountComplementDataForm.getLoggedUser().getUserAccess()!=null 
									&& accountComplementDataForm.getLoggedUser().getUserAccess().isHasAccessNovoCPBContaAprovRejDadosCompdaConta()){
								
								String accNbr = request.getParameter("processCode1");
								String cpfCnpj = request.getParameter("processCode2");
								String accType = request.getParameter("processCode3");
								

								AcctCmplVO vo = service.getAccountComplementMov(accNbr, cpfCnpj,accType);
								
								accountComplementDataForm.setScreenTitle("Aprovação - Conta - Alterar Dados Complementares");
								
								
								ArrayList<ResultTableBean> accountClosingReasonValues = null;
								
								if(vo!=null){
									if(vo.getAccountType()!=null && vo.getAccountType().equals("T")){
										accountClosingReasonValues = AccountClosingReasonExternalEnum.getAccountClosingReasonExternalList();
									}else if(vo.getAccountType()!=null && vo.getAccountType().equals("C")){
										accountClosingReasonValues = AccountClosingReasonCitibankEnum.getAccountClosingCitiBankList();
									}			
								}
								
								accountComplementDataForm.setAccountClosingReasonValues(accountClosingReasonValues);
								accountComplementDataForm.setAccountTypeValues(AccountTypeEnum.getAccountTypeList());
								accountComplementDataForm.setAccountTypeRDIPValues(AccountTypeRDIPEnum.getAccountTypeRDIPList());
								accountComplementDataForm.setSelectRegisterAccountComplement(vo);
								
								ArrayList<String> idDiffList = service.getFieldsWithDifference(vo);
								if(idDiffList!=null && !idDiffList.isEmpty()){
									accountComplementDataForm.setIdDiffList(idDiffList.toString());
								}
								if (StringUtils.isBlank(vo.getErNbr())) {
									accountComplementDataForm.setHasEmList(false);
									accountComplementDataForm.setSelectRegister(new EgVO());														
								}else{
									ArrayList<RegisterDataCustomerVO> resultList = service.searchCustomerListByER(vo.getErNbr());							
									if (resultList != null && resultList.size()>0) {
										EgVO egVo = new EgVO();
										egVo.setCustomerList(resultList);
										egVo.setErNbr(vo.getErNbr());
										accountComplementDataForm.setHasEmList(true);
										accountComplementDataForm.setSelectRegister(egVo);	
									}
								}	
																
								accountComplementDataForm.setApprove(true);
								accountComplementDataForm.setOnlyView(true);
								if (accountComplementDataForm != null) {
									
									saveMessages(request, cloneMessage(accountComplementDataForm));
									saveErrors(request, (ActionMessages) cloneErrors(accountComplementDataForm));
								}
								
								request.getSession().setAttribute("AccountComplementDataForm", accountComplementDataForm);
								forwardKey = "viewInsert";
							}else{
								forwardKey = "accessDenied";
							}	
							
							
						}else if("approve".equalsIgnoreCase(method)){
							if(accountComplementDataForm.getLoggedUser()!=null && accountComplementDataForm.getLoggedUser().getUserAccess()!=null 
									&& accountComplementDataForm.getLoggedUser().getUserAccess().isHasAccessNovoCPBContaAprovRejDadosCompdaConta()){
								
								accountComplementDataForm.setSelectedModuleCode(ScreenNamesEnum.ACCOUNT_COMPLEMENTARY_DATA.getNome());
								
								service.approve(accountComplementDataForm, new Date());
								if (accountComplementDataForm != null) {
									saveMessages(request, cloneMessage(accountComplementDataForm));
									saveErrors(request, (ActionMessages) cloneErrors(accountComplementDataForm));
								}
								request.getSession().setAttribute("AccountComplementDataForm", accountComplementDataForm);
								forwardKey = "viewInsert";				
							}else{
								forwardKey = "accessDenied";
							}

						}else if("reprove".equalsIgnoreCase(method)){
							if(accountComplementDataForm.getLoggedUser()!=null && accountComplementDataForm.getLoggedUser().getUserAccess()!=null 
									&& accountComplementDataForm.getLoggedUser().getUserAccess().isHasAccessNovoCPBCliAprovRejDadosCad()){
								
								accountComplementDataForm.setSelectedModuleCode(ScreenNamesEnum.REGISTER_DATA_CUSTOMER.getNome());
								service.reprove(accountComplementDataForm);
								if (accountComplementDataForm != null) {
									saveMessages(request, cloneMessage(accountComplementDataForm));
									saveErrors(request, (ActionMessages) cloneErrors(accountComplementDataForm));
								}
								request.getSession().setAttribute("AccountComplementDataForm", accountComplementDataForm);
								forwardKey = "viewInsert";				
							}else{
								forwardKey = "accessDenied";
							}

					}else if("backApprove".equalsIgnoreCase(method)){
						
						forwardKey = "backApprove";
					}else if(!StringUtils.isBlank(method) && "searchER".equalsIgnoreCase(method)){	
						
						AcctCmplVO acctCmplvo = accountComplementDataForm.getSelectRegisterAccountComplement();
						if(StringUtils.isBlank(acctCmplvo.getErNbr())){
							accountComplementDataForm.addError(AccountComplementDataForm.C_ERROR_ER_NOT_EXIST);
							accountComplementDataForm.setSelectRegister(new EgVO());
							
						}else{
							ArrayList<RegisterDataCustomerVO> resultList = service.searchCustomerListByER(acctCmplvo.getErNbr());
							
							if (resultList != null && resultList.size()>0) {
								EgVO egVo = new EgVO();
								egVo.setCustomerList(resultList);
								egVo.setErNbr(acctCmplvo.getErNbr());
								accountComplementDataForm.setHasEmList(true);
								accountComplementDataForm.setSelectRegister(egVo);
								
							}else{
								accountComplementDataForm.setHasEmList(false);
								accountComplementDataForm.addError(AccountComplementDataForm.C_ERROR_ER_NOT_EXIST);
							}					
							request.getSession().setAttribute("AccountComplementDataForm", accountComplementDataForm);
						}
						forwardKey = "viewInsert";
					
					}else if(method!=null && "openAccountComplementView".equalsIgnoreCase(method)){
						
						
						AcctCmplVO vo = null;
							String accNbr = accountComplementDataForm.getSelectRegisterAcctNbr();								
							String cpfCnpj = accountComplementDataForm.getSelectRegisterCpfCnpjNbr();
							String accType = accountComplementDataForm.getSelectRegisterAccountType();
							
							accountComplementDataForm.setFromApprove(false);
							vo = service.getAccountComplement(accNbr, cpfCnpj,accType);
							
							accountComplementDataForm.setScreenTitle("Conta - Consultar Dados Complementares");
							
							
							ArrayList<ResultTableBean> accountClosingReasonValues = null;
							
							if(vo!=null){
								if(vo.getAccountType()!=null && vo.getAccountType().equals("T")){
									accountClosingReasonValues = AccountClosingReasonExternalEnum.getAccountClosingReasonExternalList();
								}else if(vo.getAccountType()!=null && vo.getAccountType().equals("C")){
									accountClosingReasonValues = AccountClosingReasonCitibankEnum.getAccountClosingCitiBankList();
								}			
							}
							
							accountComplementDataForm.setAccountClosingReasonValues(accountClosingReasonValues);
							accountComplementDataForm.setAccountTypeValues(AccountTypeEnum.getAccountTypeList());							
							accountComplementDataForm.setSelectRegisterAccountComplement(vo);
							accountComplementDataForm.setAccountTypeRDIPValues(AccountTypeRDIPEnum.getAccountTypeRDIPList());
							
							if (StringUtils.isBlank(vo.getErNbr())) {
								accountComplementDataForm.setHasEmList(false);
								accountComplementDataForm.setSelectRegister(new EgVO());														
							}else{
								ArrayList<RegisterDataCustomerVO> resultList = service.searchCustomerListByER(vo.getErNbr());							
								if (resultList != null && resultList.size()>0) {
									EgVO egVo = new EgVO();
									egVo.setCustomerList(resultList);
									egVo.setErNbr(vo.getErNbr());
									accountComplementDataForm.setHasEmList(true);
									accountComplementDataForm.setSelectRegister(egVo);	
								}
							}	
							accountComplementDataForm.setOnlyView(true);
							request.getSession().setAttribute("AccountComplementData", accountComplementDataForm);
							
							forwardKey = "viewInsert";
										
	
					}else{
						
						forwardKey = "search";
					}
					
				}

				// Publica as mensagens de erro e aviso, através das API do struts para publicação destes elementos.
				if (accountComplementDataForm != null) {
					saveMessages(request, cloneMessage(accountComplementDataForm));
					saveErrors(request, (ActionMessages) cloneErrors(accountComplementDataForm));
					request.getSession().setAttribute("AccountComplementData", accountComplementDataForm);
				}
				
				actionForward = actionMapping.findForward(forwardKey);
				
			} catch (Throwable t_) {
				actionForward = actionMapping.findForward("search");
				String idLog = FormatUtils.generateIdLog();				
				if (accountComplementDataForm != null) {
					accountComplementDataForm.clearErrors();
					accountComplementDataForm.clearMessages();
					accountComplementDataForm.addError(AccountComplementDataForm.C_ERROR_GENERIC, idLog);
					saveMessages(request, cloneMessage(accountComplementDataForm));
					saveErrors(request, (ActionMessages) cloneErrors(accountComplementDataForm));
				}
				applicationLogger.error("Ocorreu um erro interno na aplicação [LOG: " + idLog + "]: " + t_.toString(), t_);
				return actionForward;
			}
			
		} catch (Throwable t_) {			
			actionForward = actionMapping.findForward(forwardKey);
			applicationLogger.error("Erro inesperado: " + t_.getMessage(), t_);
			actionForward = actionMapping.findForward("SYSTEMERROR");
			return actionForward;
		}

		return actionForward;
	}
}
