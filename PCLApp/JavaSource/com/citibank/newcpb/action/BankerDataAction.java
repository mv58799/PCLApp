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
import com.citibank.newcpb.form.BankerDataForm;
import com.citibank.newcpb.form.CentralApprovalForm;
import com.citibank.newcpb.logger.ApplicationLogger;
import com.citibank.newcpb.logger.LoggerFactory;
import com.citibank.newcpb.service.BankerDataService;
import com.citibank.newcpb.service.ValidationService;
import com.citibank.newcpb.util.FormatUtils;
import com.citibank.newcpb.vo.EmployeeRegistrationVO;
import com.citibank.newcpb.vo.OfficerBankerVO;
import com.citibank.ods.common.action.BaseAction;
import com.citibank.ods.common.user.User;

public class BankerDataAction extends CommonAction {
	

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
			BankerDataForm bankerDataForm = (BankerDataForm) actionForm;
			BankerDataService service = new BankerDataService();
			
			
			try {
				HttpSession session = request.getSession();
				loggedUser = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
				bankerDataForm.setShowConfirmDeletePopup(false);
				bankerDataForm.setLoggedUser(loggedUser);
				bankerDataForm.setApprove(false);
				bankerDataForm.setOnlyView(false);
				bankerDataForm.setApprovedDisapproved(false);
				bankerDataForm.setUpdateFromApprove(false);
				bankerDataForm.setEdit(false);
				bankerDataForm.setIdDiffList("");
				bankerDataForm.clearErrors();
				bankerDataForm.clearMessages();			

				boolean hasDataFormErrors = actionErrors != null && !actionErrors.isEmpty();

				
				String method = request.getParameter("method");
				applicationLogger.debug(className + " invoke:" + method);

				if (!hasDataFormErrors) {
					
					if(StringUtils.isBlank(method)){
						request.getSession().removeAttribute("resultListBankerData");
						forwardKey = "search";	
						
					}else if(StringUtils.isBlank(method) || "search".equalsIgnoreCase(method)){
						
						bankerDataForm.setScreenTitle("Banker - Consulta de Banker");
						
						request.getSession().removeAttribute("resultListBankerData");
						
						if(StringUtils.isBlank(bankerDataForm.getFilterEmployeeSOEID()) 
								&& StringUtils.isBlank(bankerDataForm.getFilterEmployeeName())){
							
							bankerDataForm.addError(BankerDataForm.C_ERROR_EMPTY_FILTER);
							
							
							forwardKey = "search";
							
						}else{

							ArrayList<OfficerBankerVO> resultListBankerData = service.list(bankerDataForm.getFilterEmployeeSOEID(), 
									bankerDataForm.getFilterEmployeeName());
							
							if(resultListBankerData!=null && resultListBankerData.size()>0){
								
								request.getSession().setAttribute("resultListBankerData", resultListBankerData);	
								
							}else{
								
								request.getSession().removeAttribute("resultListBankerData");
								bankerDataForm.addMessage(CentralApprovalForm.C_NO_REGISTER_FOUND);								
							}

							forwardKey = "search";
						}
						
						
						
					}else if(method!=null && method.equals("view")){
						
						bankerDataForm.setEdit(false);
						bankerDataForm.setFromApprove(false);
						bankerDataForm.setOnlyView(true);
						bankerDataForm.setScreenTitle("Banker - Detalhe Banker");
						
						OfficerBankerVO vo = new OfficerBankerVO();
						if(!StringUtils.isBlank(bankerDataForm.getSelectBankerEmployeeSOEID())){
							vo = service.getOfficerBankerCad(bankerDataForm.getSelectBankerEmployeeSOEID());
						}
						 
						
						bankerDataForm.setSelectRegisterOfficerBanker(vo);
						
						request.getSession().setAttribute("BankerDataForm", bankerDataForm);
						forwardKey = "viewInsert";
						
						
					}else if(method!=null && method.equals("searchEployeeName")){
						
						bankerDataForm.setFromApprove(false);
						if(bankerDataForm.getSelectRegisterOfficerBanker()!=null){
							
							if(!StringUtils.isBlank(bankerDataForm.getSelectRegisterOfficerBanker().getEmployeeSOEID())){
								
								EmployeeRegistrationVO employeeName = service.getCustomerBySOEID(bankerDataForm.getSelectRegisterOfficerBanker().getEmployeeSOEID());

								if(employeeName!= null && !StringUtils.isBlank(employeeName.getEmployeeName())){
									bankerDataForm.getSelectRegisterOfficerBanker().setEmployeeName(employeeName.getEmployeeName());
									response.setContentType("text/text;charset=utf-8");
									response.setHeader("cache-control", "no-cache");
									PrintWriter out = response.getWriter();
									out.println(employeeName.getEmployeeName());
									out.flush();
									return null;
								}else{
									bankerDataForm.getSelectRegisterOfficerBanker().setEmployeeName("Nome não encontrado.");
									response.setContentType("text/text;charset=utf-8");
									response.setHeader("cache-control", "no-cache");
									PrintWriter out = response.getWriter();
									out.println("Nome não encontrado.");
									out.flush();
									return null;
								}
								
							}else{
								return null;
							}

						}

						return null;
						
					}else if(method!=null && method.equals("searchSupervisorName")){
						
						bankerDataForm.setFromApprove(false);
						if(bankerDataForm.getSelectRegisterOfficerBanker()!=null){
							
							if(!StringUtils.isBlank(bankerDataForm.getSelectRegisterOfficerBanker().getSupervisorSOEID())){
								
								EmployeeRegistrationVO supervisorName = service.getCustomerBySOEID(bankerDataForm.getSelectRegisterOfficerBanker().getSupervisorSOEID());

								if(supervisorName!= null && !StringUtils.isBlank(supervisorName.getEmployeeName())){
									bankerDataForm.getSelectRegisterOfficerBanker().setSupervisorName(supervisorName.getEmployeeName());
									response.setContentType("text/text;charset=utf-8");
									response.setHeader("cache-control", "no-cache");
									PrintWriter out = response.getWriter();
									out.println(supervisorName.getEmployeeName());
									out.flush();
									return null;
								}else{
									bankerDataForm.getSelectRegisterOfficerBanker().setSupervisorName("Nome não encontrado.");
									response.setContentType("text/text;charset=utf-8");
									response.setHeader("cache-control", "no-cache");
									PrintWriter out = response.getWriter();
									out.println("Nome não encontrado.");
									out.flush();
									return null;
								}
								
							}else{
								return null;
							}

						}

						return null;
						
					}else if(method!=null && method.equals("searchAssociateName")){
						
						bankerDataForm.setFromApprove(false);
						if(bankerDataForm.getSelectRegisterOfficerBanker()!=null){
							
							if(!StringUtils.isBlank(bankerDataForm.getSelectRegisterOfficerBanker().getAssociateSOEID())){
								
								EmployeeRegistrationVO associateName = service.getCustomerBySOEID(bankerDataForm.getSelectRegisterOfficerBanker().getAssociateSOEID());

								if(associateName!= null && !StringUtils.isBlank(associateName.getEmployeeName())){
									bankerDataForm.getSelectRegisterOfficerBanker().setAssociateName(associateName.getEmployeeName());
									response.setContentType("text/text;charset=utf-8");
									response.setHeader("cache-control", "no-cache");
									PrintWriter out = response.getWriter();
									out.println(associateName.getEmployeeName());
									out.flush();
									return null;
								}else{
									bankerDataForm.getSelectRegisterOfficerBanker().setAssociateName("Nome não encontrado.");
									response.setContentType("text/text;charset=utf-8");
									response.setHeader("cache-control", "no-cache");
									PrintWriter out = response.getWriter();
									out.println("Nome não encontrado.");
									out.flush();
									return null;
								}
								
							}else{
								return null;
							}

						}

						return null;

					}else if(method!=null && "viewInsert".equalsIgnoreCase(method)){
						
						bankerDataForm.setEdit(false);
						bankerDataForm.setFromApprove(false);
						bankerDataForm.setScreenTitle("Banker - Inserção / Alteração de Banker");
						 
						OfficerBankerVO vo = new OfficerBankerVO();
						bankerDataForm.setSelectRegisterOfficerBanker(vo);
						
						request.getSession().setAttribute("BankerDataForm", bankerDataForm);
						forwardKey = "viewInsert";
						
					}else if (method!=null && "viewUpdate".equalsIgnoreCase(method)){
						
						
						bankerDataForm.setEdit(true);
						OfficerBankerVO vo = null;
						String fromApprove = request.getParameter("fromApprove");
						
						if(!StringUtils.isBlank(fromApprove) && fromApprove.equals("true")){
							
							String employeeSOEID = request.getParameter("processCode1");

							
							bankerDataForm.setFromApprove(true);
							vo = service.getOfficerBankerMov(employeeSOEID);
							
							bankerDataForm.setScreenTitle("Aprovação - Banker - Inserção / Alteração de Banker");
							bankerDataForm.setSelectRegisterOfficerBanker(vo);

							request.getSession().setAttribute("BankerDataForm", bankerDataForm);
							
							forwardKey = "viewInsert";
									
						}else{
							
							bankerDataForm.setFromApprove(false);
							
							if(!service.hasRegisterMov(bankerDataForm.getSelectBankerEmployeeSOEID())){
								
								vo = service.getOfficerBankerCad(bankerDataForm.getSelectBankerEmployeeSOEID());
								
								bankerDataForm.setScreenTitle("Banker - Inserção / Alteração de Banker");
								bankerDataForm.setSelectRegisterOfficerBanker(vo);
								bankerDataForm.setEdit(true);

								request.getSession().setAttribute("BankerDataForm", bankerDataForm);
								
								forwardKey = "viewInsert";
								
							}else{
								
								bankerDataForm.addError(BankerDataForm.C_ERROR_REGISTER_BLOCK);
								saveMessages(request, cloneMessage(bankerDataForm));
								saveErrors(request, (ActionMessages) cloneErrors(bankerDataForm));
								request.getSession().setAttribute("BankerDataForm", bankerDataForm);
								forwardKey = "search";
							}	
						}
					}else if(!StringUtils.isBlank(method) && "confirmDeleteBanker".equalsIgnoreCase(method)){	
						
						
						if(service.hasRegisterMov(bankerDataForm.getSelectBankerEmployeeSOEID())){
							
							bankerDataForm.addError(BankerDataForm.CANNOT_DELETE_REGISTER_IN_MOVEMENT);
							
						}else{
							bankerDataForm.setShowConfirmDeletePopup(true);	
						}
						

						request.getSession().setAttribute("BankerDataForm", bankerDataForm);
						forwardKey = "search";
						
					}else if(!StringUtils.isBlank(method) && "deleteBanker".equalsIgnoreCase(method)){	
								
						OfficerBankerVO vo = service.getOfficerBankerCad(bankerDataForm.getSelectBankerEmployeeSOEID());
						
						vo.setLastUpdUser(loggedUser.getUserID());
						
						service.saveForAprove(vo, "I");
						
						bankerDataForm.addMessage(BankerDataForm.C_MESSAGE_SUCESS);
						request.getSession().setAttribute("BankerDataForm", bankerDataForm);
						forwardKey = "search";
						
					}else if (method!=null && method.equals("save")){
						
						
						
							ValidationService validationService = new ValidationService();
							validationService.validate(bankerDataForm);
							
							if(bankerDataForm.getErrors()!=null && bankerDataForm.getErrors().size()==0){
								
								if( bankerDataForm.getSelectRegisterOfficerBanker().getTableOrigin()!=null){
									
									bankerDataForm.setEdit(true);
									service.validateIncludeAndUpdate(bankerDataForm, true);
									
									if(bankerDataForm.getErrors()!=null && bankerDataForm.getErrors().size()==0){
	
										bankerDataForm.getSelectRegisterOfficerBanker().setLastUpdUser(bankerDataForm.getLoggedUser().getUserID());
										
										
										if(bankerDataForm.getSelectRegisterOfficerBanker().getTableOrigin().equals(TableTypeEnum.EFFECTIVE)){
											
											if(!service.hasRegisterMov(bankerDataForm.getSelectRegisterOfficerBanker().getEmployeeSOEID())){
												
												service.saveForAprove(bankerDataForm.getSelectRegisterOfficerBanker(), "U");
												bankerDataForm.setOnlyView(true);
												bankerDataForm.addMessage(BankerDataForm.C_MESSAGE_SUCESS);
												
											}else{
												bankerDataForm.addError(BankerDataForm.C_ERROR_REGISTER_BLOCK);
											}
											
										}else if(bankerDataForm.getSelectRegisterOfficerBanker().getTableOrigin().equals(TableTypeEnum.MOVEMENT)){
											service.updateMov(bankerDataForm.getSelectRegisterOfficerBanker());
											bankerDataForm.addMessage(BankerDataForm.C_MESSAGE_SUCESS);
										}

										
									}
									
									
								}else{
								
									bankerDataForm.setEdit(false);
									service.validateIncludeAndUpdate(bankerDataForm, false);
								
								
									if(bankerDataForm.getErrors()!=null && bankerDataForm.getErrors().size()==0){
										

											
											if(!service.hasRegisterMov(bankerDataForm.getSelectRegisterOfficerBanker().getEmployeeSOEID())){
												
												bankerDataForm.getSelectRegisterOfficerBanker().setLastUpdUser(bankerDataForm.getLoggedUser().getUserID());
												service.saveForAprove(bankerDataForm.getSelectRegisterOfficerBanker(), "A");
												bankerDataForm.setOnlyView(true);
												bankerDataForm.addMessage(BankerDataForm.C_MESSAGE_SUCESS);
												
											}else{
												bankerDataForm.addError(BankerDataForm.C_ERROR_REGISTER_BLOCK);
											}
											
										} 	
									}
								}else{
								
									if( bankerDataForm.getSelectRegisterOfficerBanker().getTableOrigin()!=null){
										bankerDataForm.setEdit(true);
									}
							}
						
							saveMessages(request, cloneMessage(bankerDataForm));
							saveErrors(request, (ActionMessages) cloneErrors(bankerDataForm));
							request.getSession().setAttribute("BankerDataForm", bankerDataForm);
							forwardKey = "viewInsert";

					
					
					}else if("viewApproveReprove".equalsIgnoreCase(method)){
						
							bankerDataForm.setFromApprove(true);
							if(bankerDataForm.getLoggedUser()!=null && bankerDataForm.getLoggedUser().getUserAccess()!=null 
									&& bankerDataForm.getLoggedUser().getUserAccess().isHasAccessNovoCPBBankerAprovRej()){
								

								String employeeSOEID = request.getParameter("processCode1");
								
								OfficerBankerVO vo = service.getOfficerBankerMov(employeeSOEID);
								
								bankerDataForm.setScreenTitle("Aprovação - Banker - Inserção / Alteração de Banker");
								
								
							
								bankerDataForm.setSelectRegisterOfficerBanker(vo);
								
								ArrayList<String> idDiffList = service.getFieldsWithDifference(vo);
								if(idDiffList!=null && !idDiffList.isEmpty()){
									bankerDataForm.setIdDiffList(idDiffList.toString());
								}
																
								bankerDataForm.setApprove(true);
								bankerDataForm.setOnlyView(true);
								if (bankerDataForm != null) {
									
									saveMessages(request, cloneMessage(bankerDataForm));
									saveErrors(request, (ActionMessages) cloneErrors(bankerDataForm));
								}
								
								request.getSession().setAttribute("BankerDataForm", bankerDataForm);
								forwardKey = "viewInsert";
							}else{
								forwardKey = "accessDenied";
							}	
							
							
						}else if("approve".equalsIgnoreCase(method)){
							if(bankerDataForm.getLoggedUser()!=null && bankerDataForm.getLoggedUser().getUserAccess()!=null 
									&& bankerDataForm.getLoggedUser().getUserAccess().isHasAccessNovoCPBBankerAprovRej()){
								
								if(!bankerDataForm.getLoggedUser().getUserID().equals(bankerDataForm.getSelectRegisterOfficerBanker().getLastUpdUserSafe())){
									service.approve(bankerDataForm, new Date());
									bankerDataForm.setEdit(false);
									bankerDataForm.setFromApprove(false);
									bankerDataForm.setOnlyView(true);
									bankerDataForm.setApprove(false);
								}else{
									
									bankerDataForm.setEdit(false);
									bankerDataForm.setFromApprove(true);
									bankerDataForm.setOnlyView(true);
									bankerDataForm.setApprove(true);
									bankerDataForm.addError(BankerDataForm.C_ERROR_SAME_USER);
								}
								
								bankerDataForm.setSelectedModuleCode(ScreenNamesEnum.BANKER_DATA.getNome());
								
								
								if (bankerDataForm != null) {
									saveMessages(request, cloneMessage(bankerDataForm));
									saveErrors(request, (ActionMessages) cloneErrors(bankerDataForm));
								}
								request.getSession().setAttribute("BankerDataForm", bankerDataForm);
								forwardKey = "viewInsert";				
							}else{
								forwardKey = "accessDenied";
							}

						}else if("reprove".equalsIgnoreCase(method)){
							if(bankerDataForm.getLoggedUser()!=null && bankerDataForm.getLoggedUser().getUserAccess()!=null 
									&& bankerDataForm.getLoggedUser().getUserAccess().isHasAccessNovoCPBBankerAprovRej()){
								
								bankerDataForm.setSelectedModuleCode(ScreenNamesEnum.BANKER_DATA.getNome());
								service.reprove(bankerDataForm);
								bankerDataForm.setEdit(false);
								bankerDataForm.setFromApprove(false);
								bankerDataForm.setOnlyView(true);
								if (bankerDataForm != null) {
									saveMessages(request, cloneMessage(bankerDataForm));
									saveErrors(request, (ActionMessages) cloneErrors(bankerDataForm));
								}
								request.getSession().setAttribute("BankerDataForm", bankerDataForm);
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
				if (bankerDataForm != null) {
					saveMessages(request, cloneMessage(bankerDataForm));
					saveErrors(request, (ActionMessages) cloneErrors(bankerDataForm));
					request.getSession().setAttribute("BankerDataForm", bankerDataForm);
				}
				
				actionForward = actionMapping.findForward(forwardKey);
				
			} catch (Throwable t_) {
				actionForward = actionMapping.findForward("search");
				String idLog = FormatUtils.generateIdLog();				
				if (bankerDataForm != null) {
					bankerDataForm.clearErrors();
					bankerDataForm.clearMessages();
					bankerDataForm.addError(BankerDataForm.C_ERROR_GENERIC, idLog);
					saveMessages(request, cloneMessage(bankerDataForm));
					saveErrors(request, (ActionMessages) cloneErrors(bankerDataForm));
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
