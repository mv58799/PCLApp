package com.citibank.newcpb.action;

import java.io.PrintWriter;
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

import com.citibank.newcpb.enums.ScreenNamesEnum;
import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.form.AccountComplementDataForm;
import com.citibank.newcpb.form.AccountMigrationDataForm;
import com.citibank.newcpb.form.CentralApprovalForm;
import com.citibank.newcpb.logger.ApplicationLogger;
import com.citibank.newcpb.logger.LoggerFactory;
import com.citibank.newcpb.service.AccountMigrationDataService;
import com.citibank.newcpb.service.ValidationService;
import com.citibank.newcpb.util.FormatUtils;
import com.citibank.newcpb.vo.AcctMgrtVO;
import com.citibank.ods.common.action.BaseAction;
import com.citibank.ods.common.user.User;

public class AccountMigrationDataAction extends CommonAction {
	

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
			AccountMigrationDataForm accountMigrationDataForm = (AccountMigrationDataForm) actionForm;
			AccountMigrationDataService service = new AccountMigrationDataService();
			
			
			try {
				HttpSession session = request.getSession();
				loggedUser = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
				accountMigrationDataForm.setShowConfirmInsertPopup(false);
				accountMigrationDataForm.setShowConfirmRedirectPageRiskPopup(false);
				accountMigrationDataForm.setShowConfirmDeletePopup(false);
				accountMigrationDataForm.setLoggedUser(loggedUser);
				accountMigrationDataForm.setApprove(false);
				accountMigrationDataForm.setOnlyView(false);
				accountMigrationDataForm.setApprovedDisapproved(false);
				accountMigrationDataForm.setUpdateFromApprove(false);
				accountMigrationDataForm.setEdit(false);
				accountMigrationDataForm.setIdDiffList("");
				accountMigrationDataForm.clearErrors();
				accountMigrationDataForm.clearMessages();
			
				

				boolean hasDataFormErrors = actionErrors != null && !actionErrors.isEmpty();

				accountMigrationDataForm.clearErrors();
				accountMigrationDataForm.clearMessages();
				
				String method = request.getParameter("method");
				applicationLogger.debug(className + " invoke:" + method);

				if (!hasDataFormErrors) {
					
					if(StringUtils.isBlank(method)){
						request.getSession().removeAttribute("resultListAccountMigrationData");
						forwardKey = "search";	
						
					}else if(method!=null && method.equals("searchGrbName")){
						
						accountMigrationDataForm.setFromApprove(false);
						if(accountMigrationDataForm.getSelectRegisterAccountMigrate()!=null){
							
							if(!StringUtils.isBlank(accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountGrbNumber())){
								
								AcctMgrtVO grbName = service.getNameCpfCustomerCitiBankAccount(
										accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountGrbNumber());

								if(grbName!= null && !StringUtils.isBlank(grbName.getAccountGrbName())){
									accountMigrationDataForm.getSelectRegisterAccountMigrate().setAccountGrbName(grbName.getAccountGrbName());
									response.setContentType("text/text;charset=utf-8");
									response.setHeader("cache-control", "no-cache");
									PrintWriter out = response.getWriter();
									out.println(grbName.getAccountGrbName());
									out.flush();
									return null;
								}else{
									accountMigrationDataForm.getSelectRegisterAccountMigrate().setAccountGrbName("Nome não encontrado.");
									response.setContentType("text/text;charset=utf-8");
									response.setHeader("cache-control", "no-cache");
									PrintWriter out = response.getWriter();
									out.println("Nome não encontrado.");
									out.flush();
									return null;
								}
								
							}else{
								accountMigrationDataForm.getSelectRegisterAccountMigrate().setAccountGrbName("Conta Obrigatório.");
								response.setContentType("text/text;charset=utf-8");
								response.setHeader("cache-control", "no-cache");
								PrintWriter out = response.getWriter();
								out.println("Conta Obrigatório.");
								out.flush();
								return null;
							}

						}

						return null;
						
						
					}else if(method!=null && method.equals("searchCustodiaName")){
						
						accountMigrationDataForm.setFromApprove(false);
						if(accountMigrationDataForm.getSelectRegisterAccountMigrate()!=null){
							
							if(!StringUtils.isBlank(accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountCustodiaNumber())){
								
								AcctMgrtVO custodiaName = service.getNameCpfCustomerCustodiaAccount(
										accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountCustodiaNumber());
										

								if(custodiaName!= null && !StringUtils.isBlank(custodiaName.getAccountCustodiaName())){
									accountMigrationDataForm.getSelectRegisterAccountMigrate().setAccountCustodiaName(custodiaName.getAccountCustodiaName());
									response.setContentType("text/text;charset=utf-8");
									response.setHeader("cache-control", "no-cache");
									PrintWriter out = response.getWriter();
									out.println(custodiaName.getAccountCustodiaName());
									out.flush();
									return null;
								}else{
									accountMigrationDataForm.getSelectRegisterAccountMigrate().setAccountCustodiaName("Nome não encontrado.");
									response.setContentType("text/text;charset=utf-8");
									response.setHeader("cache-control", "no-cache");
									PrintWriter out = response.getWriter();
									out.println("Nome não encontrado.");
									out.flush();
									return null;
								}
								
							}else{
								accountMigrationDataForm.getSelectRegisterAccountMigrate().setAccountCustodiaName("Conta Obrigatório.");
								response.setContentType("text/text;charset=utf-8");
								response.setHeader("cache-control", "no-cache");
								PrintWriter out = response.getWriter();
								out.println("Conta Obrigatório.");
								out.flush();
								return null;
							}

						}

						return null;
						
						
					}else if(StringUtils.isBlank(method) || "search".equalsIgnoreCase(method)){
						
						accountMigrationDataForm.setScreenTitle("Contas - Consultar Contas Migradas (De-Para)");
						
						request.getSession().removeAttribute("resultListAccountMigrationData");
						
						if(StringUtils.isBlank(accountMigrationDataForm.getFilterAccountCitiBankNumber()) 
								&& StringUtils.isBlank(accountMigrationDataForm.getFilterAccountCustodiaNumber()) &&
								StringUtils.isBlank(accountMigrationDataForm.getFilterAccountCustodiaCpfCnpjNumber())&&
								StringUtils.isBlank(accountMigrationDataForm.getFilterEmNumber())&&
								StringUtils.isBlank(accountMigrationDataForm.getFilterCustomerNumber())){
							
							accountMigrationDataForm.addError(AccountMigrationDataForm.C_ERROR_EMPTY_FILTER);
							
							
							forwardKey = "search";
							
						}else{

							ArrayList<AcctMgrtVO> resultListAccountMigrationData = service.list(accountMigrationDataForm);	
							
							if(resultListAccountMigrationData!=null && resultListAccountMigrationData.size()>0){
								
								request.getSession().setAttribute("resultListAccountMigrationData", resultListAccountMigrationData);	
								
							}else{
								
								request.getSession().removeAttribute("resultListAccountComplementData");
								accountMigrationDataForm.addMessage(CentralApprovalForm.C_NO_REGISTER_FOUND);								
							}

							forwardKey = "search";
						}

					}else if(method!=null && "viewInsert".equalsIgnoreCase(method)){
						
						accountMigrationDataForm.setFromApprove(false);
						accountMigrationDataForm.setScreenTitle("Contas - Inserir Contas Migradas (De-Para)");
						 
						AcctMgrtVO vo = new AcctMgrtVO();
						accountMigrationDataForm.setSelectRegisterAccountMigrate(vo);
						
						request.getSession().setAttribute("AccountMigrationDataForm", accountMigrationDataForm);
						forwardKey = "viewInsert";
						
					}else if (method!=null && "viewUpdate".equalsIgnoreCase(method)){
						
						
						AcctMgrtVO vo = null;
						String fromApprove = request.getParameter("fromApprove");
						
						if(!StringUtils.isBlank(fromApprove) && fromApprove.equals("true")){
							
							String accountGrbNumber = request.getParameter("processCode1");
							String accountCustodiaCpfCnpjNumber = request.getParameter("processCode2");
							String accountCustodiaNumber = request.getParameter("processCode3");
							
							accountMigrationDataForm.setFromApprove(true);
							vo = service.getAccountMigrateMov(accountGrbNumber, accountCustodiaNumber, accountCustodiaCpfCnpjNumber);
							
							accountMigrationDataForm.setScreenTitle("Aprovação - Conta - Alterar Conta Migrada (De-Para)");
							accountMigrationDataForm.setSelectRegisterAccountMigrate(vo);

							request.getSession().setAttribute("AccountMigrationDataForm", accountMigrationDataForm);
							
							forwardKey = "viewInsert";
									
						}else{
							
							if(accountMigrationDataForm.getLoggedUser()!=null && accountMigrationDataForm.getLoggedUser().getUserAccess()!=null 
									&& !accountMigrationDataForm.getLoggedUser().getUserAccess().isHasAccessNovoCPBContaInsContasMigradas()){
								accountMigrationDataForm.setOnlyView(true);
							}
							
							accountMigrationDataForm.setFromApprove(false);
							
							if(!service.hasRegisterMov(accountMigrationDataForm.getSelectRegisterAccountGrbNumber(),
									accountMigrationDataForm.getSelectRegisterAccountCustodiaCpfCnpjNumber())){
								
								vo = service.getAccountMigrateCad(accountMigrationDataForm.getSelectRegisterAccountGrbNumber(),
										accountMigrationDataForm.getSelectRegisterAccountCustodiaNumber(),
										accountMigrationDataForm.getSelectRegisterAccountCustodiaCpfCnpjNumber());
								
								accountMigrationDataForm.setScreenTitle("Contas - Alterar Contas Migradas (De-Para)");
								accountMigrationDataForm.setSelectRegisterAccountMigrate(vo);
								accountMigrationDataForm.setEdit(true);

								request.getSession().setAttribute("AccountMigrationDataForm", accountMigrationDataForm);
								
								forwardKey = "viewInsert";
								
							}else{
								
								accountMigrationDataForm.addError(AccountMigrationDataForm.C_ERROR_REGISTER_BLOCK);
								saveMessages(request, cloneMessage(accountMigrationDataForm));
								saveErrors(request, (ActionMessages) cloneErrors(accountMigrationDataForm));
								request.getSession().setAttribute("AccountMigrationDataForm", accountMigrationDataForm);
								forwardKey = "search";
							}	
						}
					
						
					}else if(!StringUtils.isBlank(method) && "confirmDeleteAccountMigrate".equalsIgnoreCase(method)){	
						
						
						if(service.getAccountMigrateMov(accountMigrationDataForm.getSelectRegisterAccountGrbNumber(),
								accountMigrationDataForm.getFilterCustomerNumber(),
								accountMigrationDataForm.getFilterAccountCustodiaCpfCnpjNumber()) !=null){
							
							accountMigrationDataForm.addError(accountMigrationDataForm.CANNOT_DELETE_REGISTER_IN_MOVEMENT);
							
						}else{
							accountMigrationDataForm.setShowConfirmDeletePopup(true);	
						}
						

						request.getSession().setAttribute("AccountMigrationDataForm", accountMigrationDataForm);
						forwardKey = "search";
						
					}else if(!StringUtils.isBlank(method) && "deleteAccountMigrate".equalsIgnoreCase(method)){	
								
						AcctMgrtVO vo = service.getAccountMigrateCad(accountMigrationDataForm.getSelectRegisterAccountGrbNumber(),
								accountMigrationDataForm.getSelectRegisterAccountCustodiaNumber(),
								accountMigrationDataForm.getSelectRegisterAccountCustodiaCpfCnpjNumber());
						
						vo.setLastUpdUser(loggedUser.getUserID());
						
						service.saveForAprove(vo, "I");
						
						accountMigrationDataForm.addMessage(accountMigrationDataForm.C_MESSAGE_SUCESS);
						request.getSession().setAttribute("AccountMigrationDataForm", accountMigrationDataForm);
						forwardKey = "search";
						
					}else if (method!=null && method.equals("save")){
						
						
						
							ValidationService validationService = new ValidationService();
							validationService.validate(accountMigrationDataForm);
							
							if(accountMigrationDataForm.getErrors()!=null && accountMigrationDataForm.getErrors().size()==0){
								
								if( accountMigrationDataForm.getSelectRegisterAccountMigrate().getTableOrigin()!=null){
									
									accountMigrationDataForm.setEdit(true);
									service.validateIncludeAndAddInfo(accountMigrationDataForm, true);
									
									if(accountMigrationDataForm.getErrors()!=null && accountMigrationDataForm.getErrors().size()==0){
										
										if(accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountCustodiaCpfCnpjNumber()!=null){
											
											
											accountMigrationDataForm.getSelectRegisterAccountMigrate().setLastUpdUser(accountMigrationDataForm.getLoggedUser().getUserID());
											
											
											if(accountMigrationDataForm.getSelectRegisterAccountMigrate().getTableOrigin().equals(TableTypeEnum.EFFECTIVE)){
												
												if(!service.hasRegisterMov(accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountGrbNumber(),
														accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountCustodiaNumber())){
													
													service.saveForAprove(accountMigrationDataForm.getSelectRegisterAccountMigrate(), "U");
													accountMigrationDataForm.setOnlyView(true);
													accountMigrationDataForm.addMessage(AccountMigrationDataForm.C_MESSAGE_SUCESS);
													
												}else{
													accountMigrationDataForm.addError(AccountMigrationDataForm.C_ERROR_REGISTER_BLOCK);
												}
												
											}else if(accountMigrationDataForm.getSelectRegisterAccountMigrate().getTableOrigin().equals(TableTypeEnum.MOVEMENT)){
												service.updateMov(accountMigrationDataForm.getSelectRegisterAccountMigrate());
												accountMigrationDataForm.addMessage(AccountMigrationDataForm.C_MESSAGE_SUCESS);
											}

										}else{
											accountMigrationDataForm.addError(AccountMigrationDataForm.C_ERROR_CPF_ACCOUNT_CUSTODIA_NON_EXIST);
										}
									}
									
									
								}else{
								
									accountMigrationDataForm.setEdit(false);
									service.validateIncludeAndAddInfo(accountMigrationDataForm, false);
								
								
									if(accountMigrationDataForm.getErrors()!=null && accountMigrationDataForm.getErrors().size()==0){
										
										if(accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountCustodiaCpfCnpjNumber()!=null){
											
											if(!service.hasRegisterMov(accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountGrbNumber(),
													accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountCustodiaNumber())){
												
												accountMigrationDataForm.getSelectRegisterAccountMigrate().setLastUpdUser(accountMigrationDataForm.getLoggedUser().getUserID());
												service.saveForAprove(accountMigrationDataForm.getSelectRegisterAccountMigrate(), "A");
												accountMigrationDataForm.setOnlyView(true);
												accountMigrationDataForm.addMessage(AccountMigrationDataForm.C_MESSAGE_SUCESS);
												
											}else{
												accountMigrationDataForm.addError(AccountMigrationDataForm.C_ERROR_REGISTER_BLOCK);
											}
											
										} else{
											accountMigrationDataForm.addError(AccountMigrationDataForm.C_ERROR_CPF_ACCOUNT_CUSTODIA_NON_EXIST);
										}	
									}
								}
							}else{
								
								if( accountMigrationDataForm.getSelectRegisterAccountMigrate().getTableOrigin()!=null){
									accountMigrationDataForm.setEdit(true);
								}
							}
						
							saveMessages(request, cloneMessage(accountMigrationDataForm));
							saveErrors(request, (ActionMessages) cloneErrors(accountMigrationDataForm));
							request.getSession().setAttribute("AccountMigrationDataForm", accountMigrationDataForm);
							forwardKey = "viewInsert";

					
					
					}else if("viewApproveReprove".equalsIgnoreCase(method)){
						
						accountMigrationDataForm.setFromApprove(true);
							if(accountMigrationDataForm.getLoggedUser()!=null && accountMigrationDataForm.getLoggedUser().getUserAccess()!=null 
									&& accountMigrationDataForm.getLoggedUser().getUserAccess().isHasAccessNovoCPBContaAprovRejContasMigradas()){
								
								String accountGrbNumber = request.getParameter("processCode1");
								String accountCustodiaCpfCnpjNumber = request.getParameter("processCode2");
								String accountCustodiaNumber = request.getParameter("processCode3");
								
								

								AcctMgrtVO vo = service.getAccountMigrateMov(accountGrbNumber, accountCustodiaNumber, accountCustodiaCpfCnpjNumber);
								
								accountMigrationDataForm.setScreenTitle("Aprovação - Conta - Inserir Conta Migrada (De-Para)");
								
								
							
								accountMigrationDataForm.setSelectRegisterAccountMigrate(vo);
								
								ArrayList<String> idDiffList = service.getFieldsWithDifference(vo);
								if(idDiffList!=null && !idDiffList.isEmpty()){
									accountMigrationDataForm.setIdDiffList(idDiffList.toString());
								}
																
								accountMigrationDataForm.setApprove(true);
								accountMigrationDataForm.setOnlyView(true);
								if (accountMigrationDataForm != null) {
									
									saveMessages(request, cloneMessage(accountMigrationDataForm));
									saveErrors(request, (ActionMessages) cloneErrors(accountMigrationDataForm));
								}
								
								request.getSession().setAttribute("AccountMigrationDataForm", accountMigrationDataForm);
								forwardKey = "viewInsert";
							}else{
								forwardKey = "accessDenied";
							}	
							
							
						}else if("approve".equalsIgnoreCase(method)){
							if(accountMigrationDataForm.getLoggedUser()!=null && accountMigrationDataForm.getLoggedUser().getUserAccess()!=null 
									&& accountMigrationDataForm.getLoggedUser().getUserAccess().isHasAccessNovoCPBContaAprovRejContasMigradas()){
								
								accountMigrationDataForm.setSelectedModuleCode(ScreenNamesEnum.ACCOUNT_MIGRATED.getNome());
								
								service.approve(accountMigrationDataForm, new Date());
								if (accountMigrationDataForm != null) {
									saveMessages(request, cloneMessage(accountMigrationDataForm));
									saveErrors(request, (ActionMessages) cloneErrors(accountMigrationDataForm));
								}
								request.getSession().setAttribute("AccountMigrationDataForm", accountMigrationDataForm);
								forwardKey = "viewInsert";				
							}else{
								forwardKey = "accessDenied";
							}

						}else if("reprove".equalsIgnoreCase(method)){
							if(accountMigrationDataForm.getLoggedUser()!=null && accountMigrationDataForm.getLoggedUser().getUserAccess()!=null 
									&& accountMigrationDataForm.getLoggedUser().getUserAccess().isHasAccessNovoCPBContaAprovRejContasMigradas()){
								
								accountMigrationDataForm.setSelectedModuleCode(ScreenNamesEnum.ACCOUNT_MIGRATED.getNome());
								service.reprove(accountMigrationDataForm);
								if (accountMigrationDataForm != null) {
									saveMessages(request, cloneMessage(accountMigrationDataForm));
									saveErrors(request, (ActionMessages) cloneErrors(accountMigrationDataForm));
								}
								request.getSession().setAttribute("AccountMigrationDataForm", accountMigrationDataForm);
								forwardKey = "viewInsert";				
							}else{
								forwardKey = "accessDenied";
							}

					}else if("backApprove".equalsIgnoreCase(method)){
						
						forwardKey = "backApprove";
					
					}else{
						
						forwardKey = "search";
					}
					
				}

				// Publica as mensagens de erro e aviso, através das API do struts para publicação destes elementos.
				if (accountMigrationDataForm != null) {
					saveMessages(request, cloneMessage(accountMigrationDataForm));
					saveErrors(request, (ActionMessages) cloneErrors(accountMigrationDataForm));
					request.getSession().setAttribute("AccountComplementData", accountMigrationDataForm);
				}
				
				actionForward = actionMapping.findForward(forwardKey);
				
			} catch (Throwable t_) {
				actionForward = actionMapping.findForward("search");
				String idLog = FormatUtils.generateIdLog();				
				if (accountMigrationDataForm != null) {
					accountMigrationDataForm.clearErrors();
					accountMigrationDataForm.clearMessages();
					accountMigrationDataForm.addError(AccountComplementDataForm.C_ERROR_GENERIC, idLog);
					saveMessages(request, cloneMessage(accountMigrationDataForm));
					saveErrors(request, (ActionMessages) cloneErrors(accountMigrationDataForm));
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
