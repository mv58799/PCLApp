package com.citibank.newcpb.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;

import com.citibank.newcpb.enums.ScreenNamesEnum;
import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.enun.CustomerStatusEnum;
import com.citibank.newcpb.enun.DocumentsEnum;
import com.citibank.newcpb.enun.GenderTypeEnum;
import com.citibank.newcpb.form.CentralApprovalForm;
import com.citibank.newcpb.form.RegisterDataCustomerForm;
import com.citibank.newcpb.logger.ApplicationLogger;
import com.citibank.newcpb.logger.LoggerFactory;
import com.citibank.newcpb.service.RegisterDataCustomerService;
import com.citibank.newcpb.service.ResultTableService;
import com.citibank.newcpb.service.ValidationService;
import com.citibank.newcpb.util.FormatUtils;
import com.citibank.newcpb.vo.CustomerCountryVO;
import com.citibank.newcpb.vo.EmployeeRegistrationVO;
import com.citibank.newcpb.vo.MailVO;
import com.citibank.newcpb.vo.RegisterDataCustomerVO;
import com.citibank.newcpb.vo.TelephoneVO;
import com.citibank.ods.common.action.BaseAction;
import com.citibank.ods.common.user.User;

public class RegisterDataCustomerAction extends CommonAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {		
		
		ActionForward actionForward = null;
		User loggedUser = null;
		ApplicationLogger applicationLogger = null;
		String forwardKey = "";

		try {
			LoggerFactory.initialize();
			applicationLogger = LoggerFactory.getApplicationLoggerInstance();
			String method = request.getParameter("method");
			
			RegisterDataCustomerForm registerDataCustomerForm = (RegisterDataCustomerForm) form;
			HttpSession session = request.getSession();
			loggedUser = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
			
			try {
				registerDataCustomerForm.setLoggedUser(loggedUser);
				registerDataCustomerForm.setApprove(false);				
				registerDataCustomerForm.clearErrors();
				registerDataCustomerForm.clearMessages();
				registerDataCustomerForm.setOnlyView(false);
				registerDataCustomerForm.setIdDiffList("");
		
				if(method!=null && method.equals("searchBanker")){
					
					registerDataCustomerForm.setFromApprove(false);
					if(registerDataCustomerForm.getRegisterConsumer()!=null){
						
						if(!StringUtils.isBlank(registerDataCustomerForm.getRegisterConsumer().getSOEIDBankerNumber())){
							
							RegisterDataCustomerService service = new RegisterDataCustomerService();
							EmployeeRegistrationVO employee = service.getEmployeeRegistration(registerDataCustomerForm.getRegisterConsumer().getSOEIDBankerNumber());
							
							if(employee!= null && !StringUtils.isBlank(employee.getEmployeeName())){
								registerDataCustomerForm.getRegisterConsumer().setSOEIDBankerName(employee.getEmployeeName());
								response.setContentType("text/text;charset=utf-8");
								response.setHeader("cache-control", "no-cache");
								PrintWriter out = response.getWriter();
								out.println(employee.getEmployeeName());
								out.flush();
								return null;
							}else{
								registerDataCustomerForm.getRegisterConsumer().setSOEIDBankerName("Banker não encontrado.");
								response.setContentType("text/text;charset=utf-8");
								response.setHeader("cache-control", "no-cache");
								PrintWriter out = response.getWriter();
								out.println("Banker não encontrado.");
								out.flush();
								return null;
							}
							
						}else{
							registerDataCustomerForm.getRegisterConsumer().setSOEIDBankerName("SOEID Banker Obrigatório.");
							response.setContentType("text/text;charset=utf-8");
							response.setHeader("cache-control", "no-cache");
							PrintWriter out = response.getWriter();
							out.println("SOEID Banker Obrigatório.");
							out.flush();
							return null;
						}

					}

					return null;
					
				} else if (method!=null && method.equals("search")) {		
					
					registerDataCustomerForm.setFromApprove(false);
					request.getSession().removeAttribute("lista");
					
					if(registerDataCustomerForm!=null){
						
						if(StringUtils.isBlank(registerDataCustomerForm.getName()) && StringUtils.isBlank(registerDataCustomerForm.getNumberEM()) &&
								StringUtils.isBlank(registerDataCustomerForm.getNumberGFCID())){
							
							registerDataCustomerForm.addError(RegisterDataCustomerForm.C_ERROR_EMPTY_FILTER);
							
						}else{
							
							RegisterDataCustomerService service = new RegisterDataCustomerService();
							registerDataCustomerForm = service.list(registerDataCustomerForm);
							
							if(registerDataCustomerForm!=null && registerDataCustomerForm.getResultList()!=null & registerDataCustomerForm.getResultList().size()>0){
								request.getSession().setAttribute("lista",registerDataCustomerForm.getResultList());
							}else{
								request.getSession().removeAttribute("lista");
							}
						}
					}

					saveMessages(request, cloneMessage(registerDataCustomerForm));
					saveErrors(request, (ActionMessages) cloneErrors(registerDataCustomerForm));
					request.getSession().setAttribute("RegisterDataCustomerForm", registerDataCustomerForm);
					return mapping.findForward("search");


				}else if(method!=null && method.equals("view")){
					
					registerDataCustomerForm.setFromApprove(false);
					String selectCustomeGFCID = registerDataCustomerForm.getSelectCustomeGFCID();
					if(!StringUtils.isBlank(selectCustomeGFCID)){
						RegisterDataCustomerService service = new RegisterDataCustomerService();
						RegisterDataCustomerVO vo = service.getRegisterDataCustomer(selectCustomeGFCID);
						
						vo.setIsAnnualReview(false);
						registerDataCustomerForm.setCustomerType(vo.getCustomerType());
						registerDataCustomerForm.setRegisterConsumer(vo);
						
						ResultTableService resultTableService = new ResultTableService();			
						registerDataCustomerForm.setCivilStateValues(resultTableService.listCivilState());
						registerDataCustomerForm.setCountryValues(resultTableService.listCountry());
						registerDataCustomerForm.setEmitTypeValues(resultTableService.listEmitType());
						registerDataCustomerForm.setOccupationNatureValues(resultTableService.listOccupationNature());
						registerDataCustomerForm.setUfValues(resultTableService.listUf());
						registerDataCustomerForm.setActivityMainValues(resultTableService.listActivityMain());
						registerDataCustomerForm.setConstTypeValues(resultTableService.listConstType());
						registerDataCustomerForm.setCustomerStatusValues(CustomerStatusEnum.getCustomerTypeList());
						registerDataCustomerForm.setCustomerTituloValues(DocumentsEnum.getCustomerTypeList());
						registerDataCustomerForm.setGenderTypeValues(GenderTypeEnum.getGenderTypeList());
						registerDataCustomerForm.setActivityMainValues(resultTableService.listActivityMain());
						registerDataCustomerForm.setConstTypeValues(resultTableService.listConstType());
						registerDataCustomerForm.setCustomerRoleRelationshipValues(resultTableService.listRoleCust());
						registerDataCustomerForm.setOnlyView(true);
					}
					request.getSession().setAttribute("RegisterDataCustomerForm", registerDataCustomerForm);
					return mapping.findForward("viewEdit");
				} else if(method!=null && method.equals("viewEdit")){
					
					String selectCustomeGFCID = request.getParameter("processCode");
					
					if(StringUtils.isBlank(selectCustomeGFCID)){
						if(registerDataCustomerForm!=null && registerDataCustomerForm.getSelectCustomeGFCID()!=null){
							selectCustomeGFCID = registerDataCustomerForm.getSelectCustomeGFCID();
						}
					}
					
					RegisterDataCustomerService service = new RegisterDataCustomerService();
					
					if(!StringUtils.isBlank(selectCustomeGFCID)){
						
						RegisterDataCustomerVO vo;
						
						String fromApprove = request.getParameter("fromApprove");
						
						if(!StringUtils.isBlank(fromApprove) && fromApprove.equals("true")){
							registerDataCustomerForm.setFromApprove(true);
							vo = service.getRegisterDataCustomerMOV(selectCustomeGFCID);
						}else{
							
							registerDataCustomerForm.setFromApprove(false);
							if(!service.hasRegisterMov(selectCustomeGFCID)){
								vo = service.getRegisterDataCustomer(selectCustomeGFCID);
								vo.setIsAnnualReview(false);
							}else{
								
								registerDataCustomerForm.addError(RegisterDataCustomerForm.C_ERROR_REGISTER_BLOCK);
								saveMessages(request, cloneMessage(registerDataCustomerForm));
								saveErrors(request, (ActionMessages) cloneErrors(registerDataCustomerForm));
								request.getSession().setAttribute("RegisterDataCustomerForm", registerDataCustomerForm);
								return mapping.findForward("search");	
							}	
						}						
						
						//Alerta Usuario
						if(StringUtils.isBlank(vo.getNumberEM())){
							registerDataCustomerForm.addError(RegisterDataCustomerForm.C_ERROR_EMPTY_CUSTOMER_EM);
						}
						
						vo.setTelephoneList(loadImputTelephone(vo.getTelephoneList()));
						vo.setMailList(loadImputMail(vo.getMailList()));
						vo.setCitizenshipList(loadImputCitizenShip(vo.getCitizenshipList()));
						vo.setFiscalResidenceList(loadImputFiscalAddress(vo.getFiscalResidenceList()));
						registerDataCustomerForm.setRegisterConsumer(vo);
						registerDataCustomerForm.setCustomerType(vo.getCustomerType());
					}
					
					ResultTableService resultTableService = new ResultTableService();			
					registerDataCustomerForm.setCivilStateValues(resultTableService.listCivilState());
					registerDataCustomerForm.setCountryValues(resultTableService.listCountry());
					registerDataCustomerForm.setEmitTypeValues(resultTableService.listEmitType());
					registerDataCustomerForm.setOccupationNatureValues(resultTableService.listOccupationNature());
					registerDataCustomerForm.setUfValues(resultTableService.listUf());
					registerDataCustomerForm.setActivityMainValues(resultTableService.listActivityMain());
					registerDataCustomerForm.setConstTypeValues(resultTableService.listConstType());
					registerDataCustomerForm.setCustomerStatusValues(CustomerStatusEnum.getCustomerTypeList());
					registerDataCustomerForm.setCustomerTituloValues(DocumentsEnum.getCustomerTypeList());
					registerDataCustomerForm.setGenderTypeValues(GenderTypeEnum.getGenderTypeList());
					registerDataCustomerForm.setActivityMainValues(resultTableService.listActivityMain());
					registerDataCustomerForm.setConstTypeValues(resultTableService.listConstType());
					registerDataCustomerForm.setCustomerRoleRelationshipValues(resultTableService.listRoleCust());
					
					saveMessages(request, cloneMessage(registerDataCustomerForm));
					saveErrors(request, (ActionMessages) cloneErrors(registerDataCustomerForm));
					
					request.getSession().setAttribute("RegisterDataCustomerForm", registerDataCustomerForm);
					return mapping.findForward("viewEdit");
					
				}else if (method!=null && method.equals("save")){
										
					ValidationService validationService = new ValidationService();
					validationService.validate(registerDataCustomerForm);
					
					if(registerDataCustomerForm.getErrors()!=null && registerDataCustomerForm.getErrors().size()==0){
						
						RegisterDataCustomerService service = new RegisterDataCustomerService();
						registerDataCustomerForm.getRegisterConsumer().setLastUpdUser(registerDataCustomerForm.getLoggedUser().getUserID());
						if(registerDataCustomerForm.getRegisterConsumer()!=null && registerDataCustomerForm.getRegisterConsumer().getTableOrigin()!=null){
							if(registerDataCustomerForm.getRegisterConsumer().getTableOrigin().equals(TableTypeEnum.EFFECTIVE)){
								service.saveForAprove(registerDataCustomerForm.getRegisterConsumer());
								registerDataCustomerForm.setOnlyView(true);
								registerDataCustomerForm.addMessage(RegisterDataCustomerForm.C_MESSAGE_SUCESS);
							}else if(registerDataCustomerForm.getRegisterConsumer().getTableOrigin().equals(TableTypeEnum.MOVEMENT)){
								service.updateMov(registerDataCustomerForm.getRegisterConsumer());
								registerDataCustomerForm.addMessage(RegisterDataCustomerForm.C_MESSAGE_SUCESS);
							}
						}
						
					}

					saveMessages(request, cloneMessage(registerDataCustomerForm));
					saveErrors(request, (ActionMessages) cloneErrors(registerDataCustomerForm));
					request.getSession().setAttribute("RegisterDataCustomerForm", registerDataCustomerForm);
					return mapping.findForward("viewEdit");

				}else if("viewApproveReprove".equalsIgnoreCase(method)){
					registerDataCustomerForm.setFromApprove(true);
					if(registerDataCustomerForm.getLoggedUser()!=null && registerDataCustomerForm.getLoggedUser().getUserAccess()!=null 
							&& registerDataCustomerForm.getLoggedUser().getUserAccess().isHasAccessNovoCPBCliAprovRejDadosCad()){
						String selectCustomeGFCID = request.getParameter("processCode");
						
						if(StringUtils.isBlank(selectCustomeGFCID)){
							if(registerDataCustomerForm!=null && registerDataCustomerForm.getSelectCustomeGFCID()!=null){
								selectCustomeGFCID = registerDataCustomerForm.getSelectCustomeGFCID();
							}
						}
						
						RegisterDataCustomerService service = new RegisterDataCustomerService();
						
						if(!StringUtils.isBlank(selectCustomeGFCID)){
							RegisterDataCustomerVO vo = service.getRegisterDataCustomerMOV(selectCustomeGFCID);
							
							registerDataCustomerForm.setRegisterConsumer(vo);
							registerDataCustomerForm.setCustomerType(vo.getCustomerType());
							
							ArrayList<String> idDiffList = service.getFieldsWithDifference(vo);
							if(idDiffList!=null && !idDiffList.isEmpty()){
								registerDataCustomerForm.setIdDiffList(idDiffList.toString());
							}
						}
						
						ResultTableService resultTableService = new ResultTableService();			
						registerDataCustomerForm.setCivilStateValues(resultTableService.listCivilState());
						registerDataCustomerForm.setCountryValues(resultTableService.listCountry());
						registerDataCustomerForm.setEmitTypeValues(resultTableService.listEmitType());
						registerDataCustomerForm.setOccupationNatureValues(resultTableService.listOccupationNature());
						registerDataCustomerForm.setUfValues(resultTableService.listUf());
						registerDataCustomerForm.setActivityMainValues(resultTableService.listActivityMain());
						registerDataCustomerForm.setConstTypeValues(resultTableService.listConstType());
						registerDataCustomerForm.setCustomerStatusValues(CustomerStatusEnum.getCustomerTypeList());
						registerDataCustomerForm.setCustomerTituloValues(DocumentsEnum.getCustomerTypeList());
						registerDataCustomerForm.setGenderTypeValues(GenderTypeEnum.getGenderTypeList());
						registerDataCustomerForm.setActivityMainValues(resultTableService.listActivityMain());
						registerDataCustomerForm.setConstTypeValues(resultTableService.listConstType());
						registerDataCustomerForm.setCustomerRoleRelationshipValues(resultTableService.listRoleCust());
						
						registerDataCustomerForm.setApprove(true);
						registerDataCustomerForm.setOnlyView(true);
						if (registerDataCustomerForm != null) {
							saveMessages(request, cloneMessage(registerDataCustomerForm));
							saveErrors(request, (ActionMessages) cloneErrors(registerDataCustomerForm));
						}
						request.getSession().setAttribute("RegisterDataCustomerForm", registerDataCustomerForm);
						return mapping.findForward("viewEdit");
					}else{
						return mapping.findForward("accessDenied");
					}					
				}else if("approve".equalsIgnoreCase(method)){
					if(registerDataCustomerForm.getLoggedUser()!=null && registerDataCustomerForm.getLoggedUser().getUserAccess()!=null 
							&& registerDataCustomerForm.getLoggedUser().getUserAccess().isHasAccessNovoCPBCliAprovRejDadosCad()){
						RegisterDataCustomerService service = new RegisterDataCustomerService();
						registerDataCustomerForm.setSelectedModuleCode(ScreenNamesEnum.REGISTER_DATA_CUSTOMER.getNome());
						service.approve(registerDataCustomerForm, new Date());
						if (registerDataCustomerForm != null) {
							saveMessages(request, cloneMessage(registerDataCustomerForm));
							saveErrors(request, (ActionMessages) cloneErrors(registerDataCustomerForm));
						}
						request.getSession().setAttribute("RegisterDataCustomerForm", registerDataCustomerForm);
						return mapping.findForward("viewEdit");					
					}else{
						return mapping.findForward("accessDenied");
					}

				}else if("reprove".equalsIgnoreCase(method)){
					if(registerDataCustomerForm.getLoggedUser()!=null && registerDataCustomerForm.getLoggedUser().getUserAccess()!=null 
							&& registerDataCustomerForm.getLoggedUser().getUserAccess().isHasAccessNovoCPBCliAprovRejDadosCad()){
						RegisterDataCustomerService service = new RegisterDataCustomerService();
						registerDataCustomerForm.setSelectedModuleCode(ScreenNamesEnum.REGISTER_DATA_CUSTOMER.getNome());
						service.reprove(registerDataCustomerForm);
						if (registerDataCustomerForm != null) {
							saveMessages(request, cloneMessage(registerDataCustomerForm));
							saveErrors(request, (ActionMessages) cloneErrors(registerDataCustomerForm));
						}
						request.getSession().setAttribute("RegisterDataCustomerForm", registerDataCustomerForm);
						return mapping.findForward("viewEdit");					
					}else{
						return mapping.findForward("accessDenied");
					}

				}else if("backApprove".equalsIgnoreCase(method)){
					ActionRedirect redirect = new ActionRedirect("NEWCPB.CentralApproval");
					redirect.addParameter("method", "search");
					if (registerDataCustomerForm != null) {
						saveMessages(request, cloneMessage(registerDataCustomerForm));
						saveErrors(request, (ActionMessages) cloneErrors(registerDataCustomerForm));
					}
					return redirect;

				}else{
					request.getSession().removeAttribute("lista");
					registerDataCustomerForm.setName("");
					registerDataCustomerForm.setNumberEM("");
					registerDataCustomerForm.setNumberGFCID("");
					request.getSession().setAttribute("RegisterDataCustomerForm", registerDataCustomerForm);
					return mapping.findForward("search");

				}
			} catch (Throwable t_) {
				request.getSession().removeAttribute("lista");
				actionForward = mapping.findForward("search");
				String idLog = FormatUtils.generateIdLog();				
				if (registerDataCustomerForm != null) {
					registerDataCustomerForm.clearErrors();
					registerDataCustomerForm.clearMessages();
					registerDataCustomerForm.addError(CentralApprovalForm.C_ERROR_GENERIC, idLog);
					saveMessages(request, cloneMessage(registerDataCustomerForm));
					saveErrors(request, (ActionMessages) cloneErrors(registerDataCustomerForm));
				}
				applicationLogger.error("Ocorreu um erro interno na aplicação [LOG: " + idLog + "]: " + t_.toString(), t_);
				request.getSession().setAttribute("RegisterDataCustomerForm", registerDataCustomerForm);
				return actionForward;
			}
		} catch (Throwable t_) {			
			actionForward = mapping.findForward(forwardKey);
			applicationLogger.error("Erro inesperado: " + t_.getMessage(), t_);
			actionForward = mapping.findForward("SYSTEMERROR");
			return actionForward;
		}
	}
	
	public ArrayList<TelephoneVO> loadImputTelephone(ArrayList<TelephoneVO> listTelephone){
		
		int quantMax = 10;
		if(listTelephone!=null && listTelephone.size()>0){			
			quantMax = quantMax - listTelephone.size();
		}else{
			listTelephone = new ArrayList<TelephoneVO>();
		}
		
		int i = 0;
		while (i<quantMax) {
			TelephoneVO voTel = new TelephoneVO();
			listTelephone.add(voTel);
			i++;
		}
		
		return listTelephone;
	}
	
	
	public ArrayList<MailVO> loadImputMail(ArrayList<MailVO> listMail){
		
		int quantMax = 10;
		if(listMail!=null && listMail.size()>0){			
			quantMax = quantMax - listMail.size();
		}else{
			listMail = new ArrayList<MailVO>();
		}
		
		int i = 0;
		while (i<quantMax) {
			MailVO voMail = new MailVO();
			listMail.add(voMail);
			i++;
		}
		
		return listMail;
		
	}
	
	public ArrayList<CustomerCountryVO> loadImputCitizenShip(ArrayList<CustomerCountryVO> listCitizen){
		
		int quantMax = 10;
		if(listCitizen!=null && listCitizen.size()>0){			
			quantMax = quantMax - listCitizen.size();
		}else{
			listCitizen = new ArrayList<CustomerCountryVO>();
		}
		
		int i = 0;
		while (i<quantMax) {
			CustomerCountryVO voCitizen = new CustomerCountryVO();
			listCitizen.add(voCitizen);
			i++;
		}
		
		return listCitizen;
	}
	
	public ArrayList<CustomerCountryVO> loadImputFiscalAddress(ArrayList<CustomerCountryVO> listFiscalAddress){
		
		int quantMax = 10;
		if(listFiscalAddress!=null && listFiscalAddress.size()>0){			
			quantMax = quantMax - listFiscalAddress.size();
		}else{
			listFiscalAddress = new ArrayList<CustomerCountryVO>();
		}
		
		int i = 0;
		while (i<quantMax) {
			CustomerCountryVO voFiscalAddress = new CustomerCountryVO();
			listFiscalAddress.add(voFiscalAddress);
			i++;
		}
		
		return listFiscalAddress;
	}
	

}
