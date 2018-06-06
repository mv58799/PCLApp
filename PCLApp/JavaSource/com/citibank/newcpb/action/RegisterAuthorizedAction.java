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
import org.apache.struts.action.ActionRedirect;

import com.citibank.newcpb.enums.ScreenNamesEnum;
import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.form.CentralApprovalForm;
import com.citibank.newcpb.form.RegisterAuthorizedForm;
import com.citibank.newcpb.logger.ApplicationLogger;
import com.citibank.newcpb.logger.LoggerFactory;
import com.citibank.newcpb.service.RegisterAuthorizedService;
import com.citibank.newcpb.service.ValidationService;
import com.citibank.newcpb.util.FormatUtils;
import com.citibank.newcpb.vo.AuthorizationPersonAccountVO;
import com.citibank.newcpb.vo.AuthorizationPersonVO;
import com.citibank.ods.common.action.BaseAction;
import com.citibank.ods.common.user.User;

public class RegisterAuthorizedAction extends CommonAction {
	
	private RegisterAuthorizedService service;
	
	

	public RegisterAuthorizedAction() {
		service = new RegisterAuthorizedService();
	}

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
			RegisterAuthorizedForm formLocal = (RegisterAuthorizedForm) actionForm;
			request.getSession().removeAttribute("lista");
			
			try {
				HttpSession session = request.getSession();
				loggedUser = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
				formLocal.setApprove(false);
				formLocal.setOnlyView(false);
				formLocal.setHasAccountAss(false);
				formLocal.setLoggedUser(loggedUser);
				formLocal.setScreenTitle("");
				formLocal.setIdDiffList("");

				boolean hasDataFormErrors = actionErrors != null && !actionErrors.isEmpty();

				formLocal.clearErrors();
				formLocal.clearMessages();
				
				String method = request.getParameter("method");
				applicationLogger.debug(className + " invoke:" + method);
				forwardKey = "search";
				
				if (!hasDataFormErrors) {
					
					if(StringUtils.isBlank(method)){
						formLocal = new RegisterAuthorizedForm();							
					}else if(StringUtils.isBlank(method) || "search".equalsIgnoreCase(method)){
						if(StringUtils.isBlank(formLocal.getFilterCpfCnpj()) && StringUtils.isBlank(formLocal.getFilterNumberEM()) &&
								StringUtils.isBlank(formLocal.getFilterName())){
							
							formLocal.addError(RegisterAuthorizedForm.C_ERROR_EMPTY_FILTER);
							formLocal.setResultList(new ArrayList<AuthorizationPersonVO>());
							
						}else{
							
							ArrayList<AuthorizationPersonVO> resultList = service.listCad(formLocal.getFilterNumberEM(), formLocal.getFilterCpfCnpj(), formLocal.getFilterName(), true);					
							formLocal.setResultList(resultList);

							if (resultList == null || resultList.isEmpty()) {
								formLocal.addMessage(CentralApprovalForm.C_NO_REGISTER_FOUND);
							}
						}
					
					}else if("viewInsert".equalsIgnoreCase(method)){
						if(formLocal.getLoggedUser()!=null && formLocal.getLoggedUser().getUserAccess()!=null 
								&& formLocal.getLoggedUser().getUserAccess().isHasAccessNovoCPBEGInsRisco()){
							formLocal.setSelectRegister(new AuthorizationPersonVO());
							formLocal.setFilterAccountList("");
							forwardKey = "viewDetail";	
						}else{
							forwardKey = "accessDenied";	
						}
					
					}else if("insertAccountList".equalsIgnoreCase(method)){
						
						forwardKey = "viewDetail";		
						service.validateExistAccount(formLocal);						
						formLocal.setHasAccountAss(true);		
						formLocal.getSelectRegister().setAuthInd(null);
						formLocal.getSelectRegister().setComments(null);
						formLocal.getSelectRegister().setEffectiveDate(null);
						
					}else if ("save".equalsIgnoreCase(method)){
						
						ValidationService validationService = new ValidationService();
						validationService.validate(formLocal);
						
						if(formLocal.getErrors()==null || formLocal.getErrors().isEmpty()){
							
							formLocal.getSelectRegister().setLastUpdUser(formLocal.getLoggedUser().getUserID());
							if(formLocal.getSelectRegister().getTableOrigin()==null
									|| formLocal.getSelectRegister().getTableOrigin().equals(TableTypeEnum.EFFECTIVE)){
								ArrayList<AuthorizationPersonVO> resultListMov = service.listMov(formLocal.getSelectRegister().getEmNbr(), null, null, false);			
								
								if(resultListMov!=null && !resultListMov.isEmpty()){
									forwardKey = "viewDetail";
									formLocal.setSelectRegister(resultListMov.get(0));
									formLocal.setOnlyView(true);
									formLocal.addMessage(RegisterAuthorizedForm.C_CANNOT_UPDATE_REGISTER_IN_MOVEMENT);									
								}else{
									forwardKey = "viewDetail";
									ArrayList<AuthorizationPersonVO> resultListCad = service.listCad(formLocal.getSelectRegister().getEmNbr(), null, null, false);					
									if(resultListCad==null || resultListCad.isEmpty()){
										formLocal.getSelectRegister().setRecStatCode("A");										
									}else{
										formLocal.getSelectRegister().setRecStatCode("U");
									}
									formLocal.getSelectRegister().setLastUpdUser(loggedUser.getUserID());
									formLocal.getSelectRegister().setLastUpdDate(new Date());
									for (AuthorizationPersonAccountVO accVO : formLocal.getSelectRegister().getAccountList()) {
										accVO.setLastUpdDate(formLocal.getSelectRegister().getLastUpdDate());
										accVO.setLastUpdUser(loggedUser.getUserID());
										accVO.setRecStatCode(formLocal.getSelectRegister().getRecStatCode());
									}
									
									for (AuthorizationPersonAccountVO accVO : formLocal.getSelectRegister().getAccountListRemoved()) {
										accVO.setLastUpdDate(formLocal.getSelectRegister().getLastUpdDate());
										accVO.setLastUpdUser(loggedUser.getUserID());
										accVO.setRecStatCode("I");
									}
									
									service.saveForAprove(formLocal.getSelectRegister());
									formLocal.setOnlyView(true);
									formLocal.addMessage(RegisterAuthorizedForm.C_MESSAGE_SUCESS);
								}
								
							}else if(formLocal.getSelectRegister().getTableOrigin().equals(TableTypeEnum.MOVEMENT)){
								formLocal.getSelectRegister().setLastUpdDate(new Date());
								formLocal.getSelectRegister().setLastUpdUser(loggedUser.getUserID());
								
								for (AuthorizationPersonAccountVO accVO : formLocal.getSelectRegister().getAccountList()) {
									accVO.setLastUpdDate(formLocal.getSelectRegister().getLastUpdDate());
									accVO.setLastUpdUser(loggedUser.getUserID());
									accVO.setRecStatCode(formLocal.getSelectRegister().getRecStatCode());
								}
								
								for (AuthorizationPersonAccountVO accVO : formLocal.getSelectRegister().getAccountListRemoved()) {
									accVO.setLastUpdDate(formLocal.getSelectRegister().getLastUpdDate());
									accVO.setLastUpdUser(loggedUser.getUserID());
									accVO.setRecStatCode("I");
								}
								
								service.updateMov(formLocal.getSelectRegister());
								formLocal.addMessage(RegisterAuthorizedForm.C_MESSAGE_SUCESS);							
							}		
						}						
						forwardKey = "viewDetail";
					}else if ("delete".equalsIgnoreCase(method)){	
						forwardKey = "search";
						if(formLocal.getErrors()==null || formLocal.getErrors().isEmpty()){	
							if(formLocal.getLoggedUser()!=null && formLocal.getLoggedUser().getUserAccess()!=null 
									&& formLocal.getLoggedUser().getUserAccess().isHasAccessNovoCPBCliDelCadAut()){
								String emNbr = request.getParameter("processCode");
								
								if(StringUtils.isBlank(emNbr) && StringUtils.isBlank(emNbr)){
									if(formLocal!=null && !StringUtils.isBlank(formLocal.getSelectedEmNbr())){
										emNbr = formLocal.getSelectedEmNbr();
									}
								}
								
								ArrayList<AuthorizationPersonVO> resultListMov = service.listMov(emNbr, null, null, false);		
								
								if(resultListMov!=null && !resultListMov.isEmpty()){
									formLocal.setSelectRegister(resultListMov.get(0));
									formLocal.setOnlyView(true);									
									formLocal.addMessage(RegisterAuthorizedForm.CANNOT_DELETE_REGISTER_IN_MOVEMENT);
								}else{											
									formLocal.setShowConfirmDeletePopup(true);
								}
							
							}else{
								forwardKey = "accessDenied";
							}
						}						
					}else if ("deleteConfirm".equalsIgnoreCase(method)){
						
						if(formLocal.getErrors()==null || formLocal.getErrors().isEmpty()){	
							
							if(formLocal.getLoggedUser()!=null && formLocal.getLoggedUser().getUserAccess()!=null 
									&& formLocal.getLoggedUser().getUserAccess().isHasAccessNovoCPBCliDelCadAut()){
							
								String emNbr = request.getParameter("processCode");
								
								if(StringUtils.isBlank(emNbr) && StringUtils.isBlank(emNbr)){
									if(formLocal!=null && !StringUtils.isBlank(formLocal.getSelectedEmNbr())){
										emNbr = formLocal.getSelectedEmNbr();
									}
								}
								
								ArrayList<AuthorizationPersonVO> resultListCad = service.listCad(emNbr, null, null, false);					
								
								if(resultListCad!=null && !resultListCad.isEmpty()){
									formLocal.setSelectRegister(resultListCad.get(0));
									formLocal.getSelectRegister().setLastUpdDate(new Date());
									formLocal.getSelectRegister().setLastUpdUser(loggedUser.getUserID());
									formLocal.getSelectRegister().setRecStatCode("I");
									
									for (AuthorizationPersonAccountVO accVO : formLocal.getSelectRegister().getAccountList()) {
										accVO.setLastUpdDate(formLocal.getSelectRegister().getLastUpdDate());
										accVO.setLastUpdUser(loggedUser.getUserID());
										accVO.setRecStatCode(formLocal.getSelectRegister().getRecStatCode());
									}
									
									for (AuthorizationPersonAccountVO accVO : formLocal.getSelectRegister().getAccountListRemoved()) {
										accVO.setLastUpdDate(formLocal.getSelectRegister().getLastUpdDate());
										accVO.setLastUpdUser(loggedUser.getUserID());
										accVO.setRecStatCode("I");
									}
									
									service.saveForAprove(formLocal.getSelectRegister());
									formLocal.setOnlyView(true);
									formLocal.setShowConfirmDeletePopup(false);
									formLocal.addMessage(RegisterAuthorizedForm.C_MESSAGE_SUCESS);
								}
							}else{
								forwardKey = "accessDenied";
							}
						}						
					}else if ("deleteAccount".equalsIgnoreCase(method)){
						
						if(formLocal.getErrors()==null || formLocal.getErrors().isEmpty()){				
							
							if(formLocal.getLoggedUser()!=null && formLocal.getLoggedUser().getUserAccess()!=null 
									&& formLocal.getLoggedUser().getUserAccess().isHasAccessNovoCPBCliAprovRejCadAut()){														
								for (int i = 0; i < formLocal.getSelectRegister().getAccountList().size(); i++) {
									if((formLocal.getSelectedAcctNbr().equalsIgnoreCase(formLocal.getSelectRegister().getAccountList().get(i).getAcctNbr())) 
											&& (formLocal.getSelectedAuthInd().equalsIgnoreCase(formLocal.getSelectRegister().getAccountList().get(i).getAuthInd()))){
										if(formLocal.isUpdate()){
											formLocal.getSelectRegister().getAccountListRemoved().add(formLocal.getSelectRegister().getAccountList().get(i));
											formLocal.getSelectRegister().getAccountList().get(i).setRecStatCode("I");
										}										
										formLocal.getSelectRegister().getAccountList().remove(i);
										break;
									}
								}
								
								forwardKey = "viewDetail";
							}else{
								forwardKey = "accessDenied";
							}
						}						
					}else if ("viewEdit".equalsIgnoreCase(method)){
						if(formLocal.getErrors()==null || formLocal.getErrors().isEmpty()){				
							
							if(formLocal.getLoggedUser()!=null && formLocal.getLoggedUser().getUserAccess()!=null 
									&& !formLocal.getLoggedUser().getUserAccess().isHasAccessNovoCPBCliAtualCadAut()){
								formLocal.setOnlyView(true);
							}
							
							String emNbr = request.getParameter("processCode");
							
							if(StringUtils.isBlank(emNbr) && StringUtils.isBlank(emNbr)){
								if(formLocal!=null && !StringUtils.isBlank(formLocal.getSelectedEmNbr())){
									emNbr = formLocal.getSelectedEmNbr();
								}
							}
							
							ArrayList<AuthorizationPersonVO> resultListMov = service.listMov(emNbr, null, null, false);
							
							String fromApprove = request.getParameter("fromApprove");
							
							if(!StringUtils.isBlank(fromApprove) && fromApprove.equals("true")){
								formLocal.setFromApprove(true);
								if(resultListMov!=null && !resultListMov.isEmpty()){
									
									formLocal.setSelectRegister(resultListMov.get(0));
									
									ArrayList<String> idDiffList = service.getFieldsWithDifference(resultListMov.get(0));
									if(idDiffList!=null && !idDiffList.isEmpty()){
										formLocal.setIdDiffList(idDiffList.toString());
									}
									formLocal.setUpdate(true);	
								}else{
									formLocal.addError(RegisterAuthorizedForm.C_REGISTER_NOT_FOUND);
									
									ActionRedirect redirect = new ActionRedirect("NEWCPB.CentralApproval");
									redirect.addParameter("method", "search");
									if (formLocal != null) {
										saveMessages(request, cloneMessage(formLocal));
										saveErrors(request, (ActionMessages) cloneErrors(formLocal));
									}
									return redirect;
								}
								
								forwardKey = "viewDetail";
							}else{
							
								if(resultListMov!=null && !resultListMov.isEmpty()){
									formLocal.setSelectRegister(resultListMov.get(0));
									formLocal.setOnlyView(true);										
									formLocal.addMessage(RegisterAuthorizedForm.C_CANNOT_UPDATE_REGISTER_IN_MOVEMENT);
									forwardKey = "viewDetail";
								}else{
									
									ArrayList<AuthorizationPersonVO> resultListCad = service.listCad(emNbr, null, null, false);
									
									if(resultListCad!=null && !resultListCad.isEmpty()){
										formLocal.setSelectRegister(resultListCad.get(0));
										formLocal.setUpdate(true);											
										forwardKey = "viewDetail";
									}else{
										formLocal.addError(RegisterAuthorizedForm.C_REGISTER_NOT_FOUND);
									}
								}	
							}
						}					
					}else if ("viewApproveReprove".equalsIgnoreCase(method)){	
						if(formLocal.getErrors()==null || formLocal.getErrors().isEmpty()){				
							
							if(formLocal.getLoggedUser()!=null && formLocal.getLoggedUser().getUserAccess()!=null 
									&& formLocal.getLoggedUser().getUserAccess().isHasAccessNovoCPBCliAprovRejCadAut()){
								String emNbr = request.getParameter("processCode");
								
								if(StringUtils.isBlank(emNbr) && StringUtils.isBlank(emNbr)){
									if(formLocal!=null && !StringUtils.isBlank(formLocal.getSelectedEmNbr())){
										emNbr = formLocal.getFilterNumberEM();
									}
								}
								
								ArrayList<AuthorizationPersonVO> resultListMov = service.listMov(emNbr, null, null, false);
								
								if(resultListMov!=null && !resultListMov.isEmpty()){
																		
									formLocal.setSelectRegister(resultListMov.get(0));
									
									ArrayList<String> idDiffList = service.getFieldsWithDifference(resultListMov.get(0));
									if(idDiffList!=null && !idDiffList.isEmpty()){
										formLocal.setIdDiffList(idDiffList.toString());
									}
									
									formLocal.setApprove(true);
									formLocal.setOnlyView(true);
								}else{
									formLocal.addError(RegisterAuthorizedForm.C_REGISTER_NOT_FOUND);
									
									ActionRedirect redirect = new ActionRedirect("NEWCPB.CentralApproval");
									redirect.addParameter("method", "search");
									if (formLocal != null) {
										saveMessages(request, cloneMessage(formLocal));
										saveErrors(request, (ActionMessages) cloneErrors(formLocal));
									}
									return redirect;
								}
								
								forwardKey = "viewDetail";
							}else{
								forwardKey = "accessDenied";
							}
						}					
					}else if("approve".equalsIgnoreCase(method)){
						if(formLocal.getLoggedUser()!=null && formLocal.getLoggedUser().getUserAccess()!=null 
								&& formLocal.getLoggedUser().getUserAccess().isHasAccessNovoCPBEGAprovRejRisco()){
							formLocal.setSelectedModuleCode(ScreenNamesEnum.REGISTER_AUTHORIZED.getNome());
							service.approve(formLocal, new Date());
							formLocal.setOnlyView(true);
							formLocal.setIdDiffList("");
							request.getSession().setAttribute("fromApprove", true);
							forwardKey = "viewDetail";
						}else{
							forwardKey = "accessDenied";
						}

					}else if("reprove".equalsIgnoreCase(method)){
						if(formLocal.getLoggedUser()!=null && formLocal.getLoggedUser().getUserAccess()!=null 
								&& formLocal.getLoggedUser().getUserAccess().isHasAccessNovoCPBEGAprovRejRisco()){
							formLocal.setSelectedModuleCode(ScreenNamesEnum.REGISTER_AUTHORIZED.getNome());
							service.reprove(formLocal);
							formLocal.setOnlyView(true);
							formLocal.setIdDiffList("");
							request.getSession().setAttribute("fromApprove", true);
							forwardKey = "viewDetail";
						}else{
							forwardKey = "accessDenied";
						}

					}else if("backApprove".equalsIgnoreCase(method)){
						forwardKey = "backApprove";
					}						
				}

				// Publica as mensagens de erro e aviso, através das API do struts para publicação destes elementos.
				if (formLocal != null) {
					
					formLocal.setScreenTitle("Cliente - Inserir Cadastro de Autorizados");
					formLocal.setUpdate(false);
					
					if(formLocal.getSelectRegister()!=null){
						if(formLocal.getSelectRegister().getTableOrigin()==null){
							formLocal.setUpdate(false);
							formLocal.setScreenTitle("Cliente - Inserir Cadastro de Autorizados");
						}else if(formLocal.getSelectRegister().getTableOrigin().equals(TableTypeEnum.EFFECTIVE)){
							formLocal.setUpdate(true);
							formLocal.setScreenTitle("Cliente - Alterar Cadastro de Autorizados");
						}else if(!StringUtils.isBlank(formLocal.getSelectRegister().getRecStatCode())){
							if(formLocal.getSelectRegister().getRecStatCode().equalsIgnoreCase("U")){
								formLocal.setUpdate(true);
								formLocal.setScreenTitle("Cliente - Alterar Cadastro de Autorizados");
							}else if(formLocal.getSelectRegister().getRecStatCode().equalsIgnoreCase("R")){
								formLocal.setUpdate(false);
								formLocal.setScreenTitle("Cliente - Inserir Cadastro de Autorizados");
							}else if(formLocal.getSelectRegister().getRecStatCode().equalsIgnoreCase("I")){
								formLocal.setUpdate(false);
								formLocal.setScreenTitle("Cliente - Excluir Cadastro de Autorizados");
							}
						}
					}
					
					saveMessages(request, cloneMessage(formLocal));
					saveErrors(request, (ActionMessages) cloneErrors(formLocal));
					request.getSession().setAttribute("RegisterAuthorizedForm", formLocal);
				}
				
				actionForward = actionMapping.findForward(forwardKey);
				
			} catch (Throwable t_) {
				if(StringUtils.isBlank(forwardKey)){
					actionForward = actionMapping.findForward("search");
				}else{
					actionForward = actionMapping.findForward(forwardKey);
				}				
				String idLog = FormatUtils.generateIdLog();				
				if (formLocal != null) {
					formLocal.clearErrors();
					formLocal.clearMessages();
					formLocal.addError(RegisterAuthorizedForm.C_ERROR_GENERIC, idLog);
					saveMessages(request, cloneMessage(formLocal));
					saveErrors(request, (ActionMessages) cloneErrors(formLocal));
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
