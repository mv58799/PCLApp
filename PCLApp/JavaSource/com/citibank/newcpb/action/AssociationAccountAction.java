package com.citibank.newcpb.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.form.AssociationAccountForm;
import com.citibank.newcpb.form.CentralApprovalForm;
import com.citibank.newcpb.logger.ApplicationLogger;
import com.citibank.newcpb.logger.LoggerFactory;
import com.citibank.newcpb.service.AssociationAccountService;
import com.citibank.newcpb.util.FormatUtils;
import com.citibank.newcpb.vo.AcctEgVO;
import com.citibank.ods.common.action.BaseAction;
import com.citibank.ods.common.user.User;

public class AssociationAccountAction extends CommonAction {
	

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
			AssociationAccountForm associationAccountForm = (AssociationAccountForm) actionForm;
			AssociationAccountService service = new AssociationAccountService();
			
			
			try {
				HttpSession session = request.getSession();
				loggedUser = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
				associationAccountForm.setShowConfirmInsertPopup(false);
				associationAccountForm.setShowConfirmRedirectPageRiskPopup(false);
				associationAccountForm.setShowConfirmDeletePopup(false);
				associationAccountForm.setLoggedUser(loggedUser);
				associationAccountForm.setApprove(false);
				associationAccountForm.setOnlyView(false);
				associationAccountForm.setHasEmList(false);
				associationAccountForm.setHasAccountAss(false);
				associationAccountForm.setApprovedDisapproved(false);
				associationAccountForm.setUpdateFromApprove(false);

				boolean hasDataFormErrors = actionErrors != null && !actionErrors.isEmpty();

				associationAccountForm.clearErrors();
				associationAccountForm.clearMessages();
				
				String method = request.getParameter("method");
				applicationLogger.debug(className + " invoke:" + method);

				if (!hasDataFormErrors) {
					
					if(StringUtils.isBlank(method)){
						request.getSession().removeAttribute("listEM");
						forwardKey = "search";	
						
					}else if(StringUtils.isBlank(method) || "search".equalsIgnoreCase(method)){
						request.getSession().removeAttribute("listEM");
						if(StringUtils.isBlank(associationAccountForm.getFilterNumberEG()) && StringUtils.isBlank(associationAccountForm.getFilterNumberAccount()) &&
								StringUtils.isBlank(associationAccountForm.getFilterNumberER())){
							
							associationAccountForm.addError(AssociationAccountForm.C_ERROR_EMPTY_FILTER);
							forwardKey = "search";
							
						}else{
							AcctEgVO acctEgVO = new AcctEgVO();
							acctEgVO.setEgNbr(associationAccountForm.getFilterNumberEG());
							acctEgVO.setErNbr(associationAccountForm.getFilterNumberER());
							acctEgVO.setAcctNbr(associationAccountForm.getFilterNumberAccount());
							ArrayList<AcctEgVO> resultList = service.listByFilterWithLike(acctEgVO, TableTypeEnum.EFFECTIVE);	
							
							if(resultList!=null && resultList.size()>0){
								
								request.getSession().setAttribute("listEM", resultList);	
								
							}else{
								
								request.getSession().removeAttribute("listEM");
								associationAccountForm.addMessage(CentralApprovalForm.C_NO_REGISTER_FOUND);								
							}

							forwardKey = "search";
						}
													
					}
						
				}

				// Publica as mensagens de erro e aviso, através das API do struts para publicação destes elementos.
				if (associationAccountForm != null) {
					saveMessages(request, cloneMessage(associationAccountForm));
					saveErrors(request, (ActionMessages) cloneErrors(associationAccountForm));
					request.getSession().setAttribute("AssociationAccountForm", associationAccountForm);
				}
				
				actionForward = actionMapping.findForward(forwardKey);
				
			} catch (Throwable t_) {
				actionForward = actionMapping.findForward("search");
				String idLog = FormatUtils.generateIdLog();				
				if (associationAccountForm != null) {
					associationAccountForm.clearErrors();
					associationAccountForm.clearMessages();
					associationAccountForm.addError(AssociationAccountForm.C_ERROR_GENERIC, idLog);
					saveMessages(request, cloneMessage(associationAccountForm));
					saveErrors(request, (ActionMessages) cloneErrors(associationAccountForm));
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
