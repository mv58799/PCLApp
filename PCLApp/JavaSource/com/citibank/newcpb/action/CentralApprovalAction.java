package com.citibank.newcpb.action;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;

import com.citibank.newcpb.enums.ScreenNamesEnum;
import com.citibank.newcpb.form.CentralApprovalForm;
import com.citibank.newcpb.logger.ApplicationLogger;
import com.citibank.newcpb.logger.LoggerFactory;
import com.citibank.newcpb.service.CentralApprovalService;
import com.citibank.newcpb.util.FormatUtils;

public class CentralApprovalAction extends CommonAction {

	private static final String C_SCREEN_NAME = "NEWCPB.CentralApproval";

	public final ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward actionForward = null;
		ActionErrors actionErrors = null;
		ApplicationLogger applicationLogger = null;
		String forwardKey = "";

		try {

			LoggerFactory.initialize();
			applicationLogger = LoggerFactory.getApplicationLoggerInstance();
			handleLocale(request);
			handleForm(actionForm, request);			
			actionErrors = actionForm.validate(actionMapping, request);
			CentralApprovalForm formLocal = (CentralApprovalForm) actionForm;
			CentralApprovalService service = new CentralApprovalService();
			
			try {
				boolean hasDataFormErrors = actionErrors != null && !actionErrors.isEmpty();

				formLocal.clearErrors();
				formLocal.clearMessages();
				
				String method = request.getParameter("method");
				applicationLogger.debug(className + " invoke:" + method);

				if (!hasDataFormErrors) {
					
					if(StringUtils.isBlank(method) || "search".equalsIgnoreCase(method)){
						service.load(formLocal);
						service.list(formLocal);
						// Seta true quando a consulta é executada
						formLocal.setExecutedList(true);
						
						forwardKey = "search";
					
					}else if("approveReject".equalsIgnoreCase(method)){
						
						if(formLocal!=null && !StringUtils.isBlank(formLocal.getSelectedModuleCode())){
							if(formLocal.getLoggedUser()!=null && formLocal.getLoggedUser().getUserAccess()!=null 
									&& formLocal.getLoggedUser().getUserAccess().isHasAccessNovoCPBAprovRej(formLocal.getSelectedModuleCode())){
								if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.REGISTER_DATA_CUSTOMER.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.RegisterDataCustomer");
									redirect.addParameter("method", "viewApproveReprove");
									redirect.addParameter("processCode", formLocal.getSelectedCode());
									return redirect;
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.REGISTER_RISK.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.RegisterDataRisk");
									redirect.addParameter("method", "viewApproveReprove");
									if(!StringUtils.isBlank(formLocal.getSelectedCode())){
										String[] processCodeList = formLocal.getSelectedCode().split("&");
										if(processCodeList!=null && processCodeList.length>1){
											redirect.addParameter("processCode1", processCodeList[0]);
											redirect.addParameter("processCode2", processCodeList[1]);
										}
									}
									return redirect;
									
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.ACCOUNT_ASSOCIATION.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.AssociationAccount");
									redirect.addParameter("method", "viewApproveReprove");
									if(!StringUtils.isBlank(formLocal.getSelectedCode())){
										String[] processCodeList = formLocal.getSelectedCode().split("&");
										if(processCodeList!=null && processCodeList.length>1){
											redirect.addParameter("processCode1", processCodeList[0]);
											redirect.addParameter("processCode2", processCodeList[1]);
										}
									}
									return redirect;
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.REGISTER_AUTHORIZED.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.RegisterAuthorized");
									redirect.addParameter("method", "viewApproveReprove");
									redirect.addParameter("processCode", formLocal.getSelectedCode());
									return redirect;
									
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.CUSTOMER_DOC_STATUS.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.StatusCpfCnpj");
									redirect.addParameter("method", "viewApproveReprove");
									redirect.addParameter("processCode", formLocal.getSelectedCode());
									return redirect;
									
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.DOCUMENTS.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.Documents");
									redirect.addParameter("method", "viewApproveReprove");
									redirect.addParameter("processCode", formLocal.getSelectedCode());
									return redirect;
									
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.ACCOUNT_COMPLEMENTARY_DATA.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.AccountComplementData");
									redirect.addParameter("method", "viewApproveReprove");
									if(!StringUtils.isBlank(formLocal.getSelectedCode())){
										String[] processCodeList = formLocal.getSelectedCode().split("&");
										if(processCodeList!=null && processCodeList.length>1){
											redirect.addParameter("processCode1", processCodeList[0]);
											redirect.addParameter("processCode2", processCodeList[1]);
											redirect.addParameter("processCode3", processCodeList[2]);
										}
									}
									return redirect;
									
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.ACCOUNT_MIGRATED.getNome())){
									
									ActionRedirect redirect = new ActionRedirect("NEWCPB.AccountMigration");
									redirect.addParameter("method", "viewApproveReprove");
									if(!StringUtils.isBlank(formLocal.getSelectedCode())){
										String[] processCodeList = formLocal.getSelectedCode().split("&");
										if(processCodeList!=null && processCodeList.length>1){
											redirect.addParameter("processCode1", processCodeList[0]);
											redirect.addParameter("processCode2", processCodeList[1]);
											redirect.addParameter("processCode3", processCodeList[2]);
										}
									}
									return redirect;
								
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.BANKER_DATA.getNome())){
									
									ActionRedirect redirect = new ActionRedirect("NEWCPB.Banker");
									redirect.addParameter("method", "viewApproveReprove");
									redirect.addParameter("processCode1", formLocal.getSelectedCode());
	
									return redirect;
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.CUSTOMER_DOC_STATUS.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.StatusCpfCnpj");
									redirect.addParameter("method", "viewApproveReprove");
									redirect.addParameter("processCode", formLocal.getSelectedCode());
		
									return redirect;
									
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.DOCUMENTS.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.Documents");
									redirect.addParameter("method", "viewApproveReprove");
									redirect.addParameter("processCode", formLocal.getSelectedCode());
		
									return redirect;
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.QUESTIONS_KE_CUSTOMER.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.QuestionsKe");
									redirect.addParameter("method", "viewApproveReprove");
									if(!StringUtils.isBlank(formLocal.getSelectedCode())){
										String[] processCodeList = formLocal.getSelectedCode().split("&");
										if(processCodeList!=null && processCodeList.length>1){
											redirect.addParameter("processCode1", processCodeList[0]);
											redirect.addParameter("processCode2", processCodeList[1]);
										}
									}
									
		
									return redirect;
									
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.QUESTIONS_KE_AUTH.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.QuestionsKeAuthorized");
									redirect.addParameter("method", "viewApproveReprove");
									if(!StringUtils.isBlank(formLocal.getSelectedCode())){
										String[] processCodeList = formLocal.getSelectedCode().split("&");
										if(processCodeList!=null && processCodeList.length>1){
											redirect.addParameter("processCode1", processCodeList[0]);
											redirect.addParameter("processCode2", processCodeList[1]);
										}
									}
									
		
									return redirect;
								}
							}else{
								forwardKey = "accessDenied";
							}
						}else{
							// Seta true quando a consulta é executada
							formLocal.setExecutedList(true);
							forwardKey = "search";
						}					
					
					}else if("viewEdit".equalsIgnoreCase(method)){					
						
						if(formLocal!=null && !StringUtils.isBlank(formLocal.getSelectedModuleCode())){
							if(formLocal.getLoggedUser()!=null && formLocal.getLoggedUser().getUserAccess()!=null 
									&& formLocal.getLoggedUser().getUserAccess().isHasAccessNovoCPBAprovRej(formLocal.getSelectedModuleCode())){
								if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.REGISTER_DATA_CUSTOMER.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.RegisterDataCustomer");
									redirect.addParameter("method", "viewEdit");
									redirect.addParameter("fromApprove", "true");
									redirect.addParameter("processCode", formLocal.getSelectedCode());
									return redirect;
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.REGISTER_RISK.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.RegisterDataRisk");
									redirect.addParameter("method", "viewEdit");
									redirect.addParameter("fromApprove", "true");
									if(!StringUtils.isBlank(formLocal.getSelectedCode())){
										String[] processCodeList = formLocal.getSelectedCode().split("&");
										if(processCodeList!=null && processCodeList.length>1){
											redirect.addParameter("processCode1", processCodeList[0]);
											redirect.addParameter("processCode2", processCodeList[1]);
										}
									}
									
									return redirect;
									
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.ACCOUNT_ASSOCIATION.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.AssociationAccount");
									redirect.addParameter("method", "openUpdateAssociationEgAccount");
									if(!StringUtils.isBlank(formLocal.getSelectedCode())){
										String[] processCodeList = formLocal.getSelectedCode().split("&");
										if(processCodeList!=null && processCodeList.length>1){
											redirect.addParameter("processCode1", processCodeList[0]);
											redirect.addParameter("processCode2", processCodeList[1]);
											redirect.addParameter("fromApprove", "true");
										}
									}
									return redirect;
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.REGISTER_AUTHORIZED.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.RegisterAuthorized");
									redirect.addParameter("method", "viewEdit");
									redirect.addParameter("fromApprove", "true");
									redirect.addParameter("processCode", formLocal.getSelectedCode());
									
									return redirect;
									
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.ACCOUNT_COMPLEMENTARY_DATA.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.AccountComplementData");
									redirect.addParameter("method", "openAccountComplementUpdateView");
									redirect.addParameter("fromApprove", "true");
									if(!StringUtils.isBlank(formLocal.getSelectedCode())){
										String[] processCodeList = formLocal.getSelectedCode().split("&");
										if(processCodeList!=null && processCodeList.length>1){
											redirect.addParameter("processCode1", processCodeList[0]);
											redirect.addParameter("processCode2", processCodeList[1]);
											redirect.addParameter("processCode3", processCodeList[2]);
										}
									}
									return redirect;
									
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.ACCOUNT_MIGRATED.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.AccountMigration");
									redirect.addParameter("method", "openAccountComplementUpdateView");
									redirect.addParameter("fromApprove", "true");
									if(!StringUtils.isBlank(formLocal.getSelectedCode())){
										String[] processCodeList = formLocal.getSelectedCode().split("&");
										if(processCodeList!=null && processCodeList.length>1){
											redirect.addParameter("processCode1", processCodeList[0]);
											redirect.addParameter("processCode2", processCodeList[1]);
											redirect.addParameter("processCode3", processCodeList[2]);
										}
									}
									return redirect;
									
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.BANKER_DATA.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.Banker");
									redirect.addParameter("method", "viewUpdate");
									redirect.addParameter("fromApprove", "true");
									redirect.addParameter("processCode1", formLocal.getSelectedCode());
	
								return redirect;
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.CUSTOMER_DOC_STATUS.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.StatusCpfCnpj");
									redirect.addParameter("method", "viewEdit");
									redirect.addParameter("fromApprove", "true");
									redirect.addParameter("processCode", formLocal.getSelectedCode());
		
									return redirect;
									
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.DOCUMENTS.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.Documents");
									redirect.addParameter("method", "viewEdit");
									redirect.addParameter("fromApprove", "true");
									redirect.addParameter("processCode", formLocal.getSelectedCode());
		
									return redirect;
									
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.QUESTIONS_KE_CUSTOMER.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.QuestionsKe");
									redirect.addParameter("method", "viewEdit");
									redirect.addParameter("fromApprove", "true");
									if(!StringUtils.isBlank(formLocal.getSelectedCode())){
										String[] processCodeList = formLocal.getSelectedCode().split("&");
										if(processCodeList!=null && processCodeList.length>1){
											redirect.addParameter("processCode1", processCodeList[0]);
											redirect.addParameter("processCode2", processCodeList[1]);
										}
									}
									
		
									return redirect;
									
								}else if(formLocal.getSelectedModuleCode().equalsIgnoreCase(ScreenNamesEnum.QUESTIONS_KE_AUTH.getNome())){
									ActionRedirect redirect = new ActionRedirect("NEWCPB.QuestionsKeAuthorized");
									redirect.addParameter("method", "viewEdit");
									redirect.addParameter("fromApprove", "true");
									if(!StringUtils.isBlank(formLocal.getSelectedCode())){
										String[] processCodeList = formLocal.getSelectedCode().split("&");
										if(processCodeList!=null && processCodeList.length>1){
											redirect.addParameter("processCode1", processCodeList[0]);
											redirect.addParameter("processCode2", processCodeList[1]);
										}
									}
									
		
									return redirect;
								}
							}else{
								forwardKey = "accessDenied";
							}
							
						}else{
							// Seta true quando a consulta é executada
							formLocal.setExecutedList(true);
							forwardKey = "search";
						}	
					}
					
				} else {
					forwardKey = C_SCREEN_NAME;

					if (!StringUtils.isBlank(method) && method.equals(C_ACTION_EXECUTE)) {
						// Quando o modo é de Execute e tem erros, devemos copiar
						// cada msg do ActionErrors para o FncVO, para que estes erros
						// sejam mostrados para os usuários.
						for (Iterator ite = actionErrors.get(); ite.hasNext();) {
							ActionMessage message = (ActionMessage) ite.next();
							formLocal.addError(message.getKey(), message.getValues());
						}
					}
				}

				// Coloca o form de volta na sessão
				formLocal.setExecutedList(false);
				request.getSession().setAttribute("lista",formLocal.getResultList());

				// Publica as mensagens de erro e aviso, através das API do struts para publicação destes elementos.
				if (formLocal != null) {
					saveMessages(request, cloneMessage(formLocal));
					saveErrors(request, (ActionMessages) cloneErrors(formLocal));
				}
				
				actionForward = actionMapping.findForward(forwardKey);
				
			} catch (Throwable t_) {
				actionForward = actionMapping.findForward("search");
				String idLog = FormatUtils.generateIdLog();				
				if (formLocal != null) {
					formLocal.clearErrors();
					formLocal.clearMessages();
					formLocal.addError(CentralApprovalForm.C_ERROR_GENERIC, idLog);
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
