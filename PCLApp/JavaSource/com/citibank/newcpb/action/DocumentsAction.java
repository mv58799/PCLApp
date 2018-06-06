package com.citibank.newcpb.action;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.citibank.newcpb.enun.DocumentsEnum;
import com.citibank.newcpb.form.DocumentsForm;
import com.citibank.newcpb.form.StatusCpfCnpjForm;
import com.citibank.newcpb.logger.ApplicationLogger;
import com.citibank.newcpb.logger.LoggerFactory;
import com.citibank.newcpb.service.DocumentsService;
import com.citibank.newcpb.service.RegisterDataCustomerService;
import com.citibank.newcpb.service.StatusCpfCnpjService;
import com.citibank.newcpb.service.ValidationService;
import com.citibank.newcpb.util.FormatUtils;
import com.citibank.newcpb.vo.DocumentsVO;
import com.citibank.newcpb.vo.StatusCpfCnpjVO;
import com.citibank.ods.common.action.BaseAction;
import com.citibank.ods.common.user.User;

public class DocumentsAction extends CommonAction {
	
	private DocumentsService service;
	private RegisterDataCustomerService custService;

	public DocumentsAction() {
		service = new DocumentsService();
		custService = new RegisterDataCustomerService();
	}

	public final ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward actionForward = null;
		ActionErrors actionErrors = null;
		User loggedUser = null;
		ApplicationLogger applicationLogger = null;
		String forwardKey = "";
		boolean searchInsert = false;

		try {

			LoggerFactory.initialize();
			applicationLogger = LoggerFactory.getApplicationLoggerInstance();
			handleLocale(request);
			handleForm(actionForm, request);			
			actionErrors = actionForm.validate(actionMapping, request);
			DocumentsForm formLocal = (DocumentsForm) actionForm;
			request.getSession().removeAttribute("lista");
			
			try {
				HttpSession session = request.getSession();
				loggedUser = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
				formLocal.setApprove(false);
				formLocal.setOnlyView(false);
				formLocal.setUpdate(false);
				formLocal.setLoggedUser(loggedUser);
				formLocal.setScreenTitle("Cliente - Alterar Status CPF/CNPJ");
				formLocal.setIdDiffList("");

				boolean hasDataFormErrors = actionErrors != null && !actionErrors.isEmpty();

				formLocal.clearErrors();
				formLocal.clearMessages();
				
				String method = request.getParameter("method");
				applicationLogger.debug(className + " invoke:" + method);
				forwardKey = "search";
				
				if (!hasDataFormErrors) {
					
					if(StringUtils.isBlank(method)){
						formLocal = new DocumentsForm();
					}else if(StringUtils.isBlank(method) || "search".equalsIgnoreCase(method)){
						if(StringUtils.isBlank(formLocal.getFilterNumberEM()) && StringUtils.isBlank(formLocal.getFilterCpfCnpj()) &&
								StringUtils.isBlank(formLocal.getFilterName()) && StringUtils.isBlank(formLocal.getFilterTitulo())
								 && StringUtils.isBlank(formLocal.getFilterMonthYear())){
							
							formLocal.addError(DocumentsForm.C_ERROR_EMPTY_FILTER);
							formLocal.setResultList(new ArrayList<DocumentsVO>());
							
						}else{
							
							ArrayList<DocumentsVO> resultList = service.listCad(formLocal.getFilterCpfCnpj(), formLocal.getFilterName(), formLocal.getFilterTitulo(), true);				
							formLocal.setResultList(resultList);

							if (resultList == null || resultList.isEmpty()) {
							   formLocal.addMessage(StatusCpfCnpjForm.C_NO_REGISTER_FOUND);
							}
						}
					
					}else if(StringUtils.isBlank(method) || "searchInsert".equalsIgnoreCase(method)){
					    if(StringUtils.isBlank(formLocal.getFilterCpfCnpj()) &&
							 StringUtils.isBlank(formLocal.getFilterName())
							 && StringUtils.isBlank(formLocal.getFilterProcPA())){
						
						     formLocal.addError(DocumentsForm.C_ERROR_EMPTY_FILTER);
						     formLocal.setResultList(new ArrayList<DocumentsVO>());
						
					}else{
						
						ArrayList<DocumentsVO> resultList = service.listCadInsert(formLocal.getFilterCpfCnpj(), formLocal.getFilterName(), formLocal.getFilterProcPA());				
						formLocal.setResultList(resultList);
						searchInsert = true;

						if (resultList == null || resultList.isEmpty()) {
						   formLocal.addMessage(StatusCpfCnpjForm.C_NO_REGISTER_FOUND);
						}
					}
				
				}else if("viewInsert".equalsIgnoreCase(method)){
						if(formLocal.getLoggedUser()!=null && formLocal.getLoggedUser().getUserAccess()!=null 
								&& formLocal.getLoggedUser().getUserAccess().isHasAccessNovoCPBEGInsRisco()){
							formLocal.setTituloValues(DocumentsEnum.getTypeList());
							formLocal.setSelectedRegister(new StatusCpfCnpjVO());
							forwardKey = "viewDetail";	
						}else{
							forwardKey = "accessDenied";	
						}
					
						}else if ("save".equalsIgnoreCase(method)){
						
						forwardKey = "viewDetail";
						
						ValidationService validationService = new ValidationService();
						validationService.validate(formLocal);
						
						if(formLocal.getErrors()==null || formLocal.getErrors().isEmpty()){
							
							if(formLocal.getSelectedRegister().getTableOrigin()==null
									|| formLocal.getSelectedRegister().getTableOrigin().equals(TableTypeEnum.EFFECTIVE)){
								
							//	ArrayList<StatusCpfCnpjVO> resultListMov = service.listMov(null, null, null, null, null, FormatUtils.unformatterDoc(formLocal.getSelectedRegister().getCpfCnpjNbr()), false);		
								
							/*	if(resultListMov!=null && !resultListMov.isEmpty()){
									ArrayList<String> cpfInMovList = new ArrayList<String>();
									for (StatusCpfCnpjVO statusCpfCnpjVO : resultListMov) {
										cpfInMovList.add(statusCpfCnpjVO.getCpfCnpjNbr());
									}								
									formLocal.setOnlyView(true);
									formLocal.addError(StatusCpfCnpjForm.C_CANNOT_UPDATE_REGISTER_IN_MOVEMENT_STATUS_CPF, cpfInMovList);									
								}else{
									
									String[] selectedCpfCnpjlist = FormatUtils.unformatterDoc(formLocal.getSelectedRegister().getCpfCnpjNbr()).replaceAll("\r", "").replaceAll("\n", "").replaceAll(" ", "").split(",");
									Date now = new Date();
									boolean hasError = false;
									ArrayList<String> cpfinvalidList = new ArrayList<String>();
									for (String cpfCnpj : selectedCpfCnpjlist) {
										
										if(!service.validaExistCpfCnpj(cpfCnpj)){
											hasError = true;
											cpfinvalidList.add(cpfCnpj);
										}
									}
									
									if(!hasError){
										for (String cpfCnpj : selectedCpfCnpjlist) {
											if(!StringUtils.isBlank(cpfCnpj)){
												formLocal.getSelectedRegister().setCpfCnpjNbr(cpfCnpj);
												formLocal.getSelectedRegister().setLastUpdUserId(loggedUser.getUserID());
												formLocal.getSelectedRegister().setLastUpdDate(now);
												formLocal.getSelectedRegister().setRecStatCode("A");										
												service.saveForAprove(formLocal.getSelectedRegister());												
												formLocal.setOnlyView(true);
											}
										}
										String selectedCpfCnpjlistString = Arrays.toString(selectedCpfCnpjlist);
										selectedCpfCnpjlistString = selectedCpfCnpjlistString.replace("[", "").replace("]", "");
										formLocal.getSelectedRegister().setCpfCnpjNbr(selectedCpfCnpjlistString);
										formLocal.addMessage(StatusCpfCnpjForm.C_MESSAGE_SUCESS);
									}else{
										formLocal.addError(StatusCpfCnpjForm.C_ERROR_STATUS_CPF_CNPJ_INVALID, cpfinvalidList.toString());		
									}
								} */
								
							}else if(formLocal.getSelectedRegister().getTableOrigin().equals(TableTypeEnum.MOVEMENT)){
								
								String[] selectedCpfCnpjlist = FormatUtils.unformatterDoc(formLocal.getSelectedRegister().getCpfCnpjNbr()).replaceAll("\r", "").replaceAll("\n", "").replaceAll(" ", "").split(",");
								Date now = new Date();
								boolean hasError = false;
								ArrayList<String> cpfinvalidList = new ArrayList<String>();
								for (String cpfCnpj : selectedCpfCnpjlist) {
									
									/*if(!service.validaExistCpfCnpj(cpfCnpj)){
										hasError = true;
										cpfinvalidList.add(cpfCnpj);
									}*/
								}
								
								/*if(!hasError){
									service.deleteMovByMonthYear(formLocal.getSelectedRegister().getCpfUpdMthYr());
									for (String cpfCnpj : selectedCpfCnpjlist) {
										if(!StringUtils.isBlank(cpfCnpj)){
											formLocal.getSelectedRegister().setCpfCnpjNbr(cpfCnpj);
											formLocal.getSelectedRegister().setLastUpdUserId(loggedUser.getUserID());
											formLocal.getSelectedRegister().setLastUpdDate(now);
											formLocal.getSelectedRegister().setRecStatCode("A");										
											service.saveForAprove(formLocal.getSelectedRegister());												
											formLocal.setOnlyView(true);
										}
									}
									String selectedCpfCnpjlistString = Arrays.toString(selectedCpfCnpjlist);
									selectedCpfCnpjlistString = selectedCpfCnpjlistString.replace("[", "").replace("]", "");
									formLocal.getSelectedRegister().setCpfCnpjNbr(selectedCpfCnpjlistString);
									formLocal.addMessage(StatusCpfCnpjForm.C_MESSAGE_SUCESS);
								}else{
									formLocal.addError(StatusCpfCnpjForm.C_ERROR_STATUS_CPF_CNPJ_INVALID, cpfinvalidList.toString());		
								}						
							}	*/
						}						
					}else if ("viewEdit".equalsIgnoreCase(method)){
						if(formLocal.getErrors()==null || formLocal.getErrors().isEmpty()){				
							
							if(formLocal.getLoggedUser()!=null && formLocal.getLoggedUser().getUserAccess()!=null 
									&& formLocal.getLoggedUser().getUserAccess().isHasAccessNovoCPBCliAtualCadAut()){
							
								String monthYear = request.getParameter("processCode");
								
								if(StringUtils.isBlank(monthYear) && StringUtils.isBlank(monthYear)){
									if(formLocal!=null && !StringUtils.isBlank(formLocal.getSelectedRegister().getCpfUpdMthYr())){
										monthYear = formLocal.getSelectedRegister().getCpfUpdMthYr();
									}
								}
								
							//	ArrayList<StatusCpfCnpjVO> resultListMov = service.listMov(null, null, null, null, monthYear, null, false);
								
								String fromApprove = request.getParameter("fromApprove");
								
								if(!StringUtils.isBlank(fromApprove) && fromApprove.equals("true")){
									formLocal.setFromApprove(true);
								/*	if(resultListMov!=null && !resultListMov.isEmpty()){
										
										formLocal.setSelectedRegister(resultListMov.get(0));
										String cpfList = "";
										for (StatusCpfCnpjVO vo : resultListMov) {
											if(!StringUtils.isBlank(cpfList)){
												cpfList +=", ";
											}
											cpfList += vo.getCpfCnpjNbr();
										}
										formLocal.getSelectedRegister().setCpfCnpjNbr(cpfList);
										
										ArrayList<String> idDiffList = service.getFieldsWithDifference(resultListMov.get(0));
										if(idDiffList!=null && !idDiffList.isEmpty()){
											formLocal.setIdDiffList(idDiffList.toString());
										}
										
										if(!StringUtils.isBlank(formLocal.getSelectedRegister().getCpfUpdMthYr())){
											StringBuilder cpfUpdMthYrFormated = new StringBuilder(formLocal.getSelectedRegister().getCpfUpdMthYr());
											cpfUpdMthYrFormated.insert(2, '/');
											formLocal.getSelectedRegister().setCpfUpdMthYr(cpfUpdMthYrFormated.toString());
										}
										
										formLocal.setUpdate(true);	
									}else{
										formLocal.addMessage(StatusCpfCnpjForm.C_REGISTER_NOT_FOUND);
										
										ActionRedirect redirect = new ActionRedirect("NEWCPB.CentralApproval");
										redirect.addParameter("method", "search");
										if (formLocal != null) {
											saveMessages(request, cloneMessage(formLocal));
											saveErrors(request, (ActionMessages) cloneErrors(formLocal));
										}
										return redirect;
									}  */
									
									forwardKey = "viewDetail";
								}else{
								
									/*if(resultListMov!=null && !resultListMov.isEmpty()){
										formLocal.setSelectedRegister(resultListMov.get(0));
										String cpfList = "";
										for (StatusCpfCnpjVO vo : resultListMov) {
											if(!StringUtils.isBlank(cpfList)){
												cpfList +=", ";
											}
											cpfList += vo.getCpfCnpjNbr();
										}
										formLocal.getSelectedRegister().setCpfCnpjNbr(cpfList);
										formLocal.setOnlyView(true);										
										formLocal.addError(StatusCpfCnpjForm.C_CANNOT_UPDATE_REGISTER_IN_MOVEMENT);
										forwardKey = "viewDetail";
									}else{
										
										ArrayList<StatusCpfCnpjVO> resultListCad = service.listCad(null, null, null, null, monthYear, null, false);
										
										if(resultListCad!=null && !resultListCad.isEmpty()){											
											formLocal.setSelectedRegister(resultListCad.get(0));
											String cpfList = "";
											for (StatusCpfCnpjVO vo : resultListMov) {
												if(!StringUtils.isBlank(cpfList)){
													cpfList +=", ";
												}
												cpfList += vo.getCpfCnpjNbr();
											}
											formLocal.getSelectedRegister().setCpfCnpjNbr(cpfList);
											
											if(!StringUtils.isBlank(formLocal.getSelectedRegister().getCpfUpdMthYr())){
												StringBuilder cpfUpdMthYrFormated = new StringBuilder(formLocal.getSelectedRegister().getCpfUpdMthYr());
												cpfUpdMthYrFormated.insert(2, '/');
												formLocal.getSelectedRegister().setCpfUpdMthYr(cpfUpdMthYrFormated.toString());
											}
											
											formLocal.setUpdate(true);											
											forwardKey = "viewDetail";
										}else{
											formLocal.addError(StatusCpfCnpjForm.C_REGISTER_NOT_FOUND);
										}
									}	 */
								}
							}else{
								forwardKey = "accessDenied";
							}
						}					
					}else if ("viewApproveReprove".equalsIgnoreCase(method)){	
						if(formLocal.getErrors()==null || formLocal.getErrors().isEmpty()){				
							
							if(formLocal.getLoggedUser()!=null && formLocal.getLoggedUser().getUserAccess()!=null 
									&& formLocal.getLoggedUser().getUserAccess().isHasAccessNovoCPBCliAprovRejCadAut()){
								String monthYear = request.getParameter("processCode");
								
								if(StringUtils.isBlank(monthYear) && StringUtils.isBlank(monthYear)){
									if(formLocal!=null && !StringUtils.isBlank(formLocal.getSelectedRegister().getCpfUpdMthYr())){
										monthYear = formLocal.getSelectedRegister().getCpfUpdMthYr();
									}
								}
								
								//ArrayList<StatusCpfCnpjVO> resultListMov = service.listMov(null, null, null, null, monthYear, null, false);
								
								/*if(resultListMov!=null && !resultListMov.isEmpty()){
																		
									formLocal.setSelectedRegister(resultListMov.get(0));
									String cpfList = "";
									for (StatusCpfCnpjVO vo : resultListMov) {
										if(!StringUtils.isBlank(cpfList)){
											cpfList +=", ";
										}
										cpfList += vo.getCpfCnpjNbr();
									}
									formLocal.getSelectedRegister().setCpfCnpjNbr(cpfList);
									
									if(!StringUtils.isBlank(formLocal.getSelectedRegister().getCpfUpdMthYr())){
										StringBuilder cpfUpdMthYrFormated = new StringBuilder(formLocal.getSelectedRegister().getCpfUpdMthYr());
										cpfUpdMthYrFormated.insert(2, '/');
										formLocal.getSelectedRegister().setCpfUpdMthYr(cpfUpdMthYrFormated.toString());
									}
									
									ArrayList<String> idDiffList = service.getFieldsWithDifference(resultListMov.get(0));
									if(idDiffList!=null && !idDiffList.isEmpty()){
										formLocal.setIdDiffList(idDiffList.toString());
									}
									
									formLocal.setApprove(true);
									formLocal.setOnlyView(true);
								}else{
									formLocal.addError(StatusCpfCnpjForm.C_REGISTER_NOT_FOUND);
									
									ActionRedirect redirect = new ActionRedirect("NEWCPB.CentralApproval");
									redirect.addParameter("method", "search");
									if (formLocal != null) {
										saveMessages(request, cloneMessage(formLocal));
										saveErrors(request, (ActionMessages) cloneErrors(formLocal));
									} 
									return redirect;
								} */
								
								forwardKey = "viewDetail";
							}else{
								forwardKey = "accessDenied";
							}
						}					
					}else if("approve".equalsIgnoreCase(method)){
						if(formLocal.getLoggedUser()!=null && formLocal.getLoggedUser().getUserAccess()!=null 
								&& formLocal.getLoggedUser().getUserAccess().isHasAccessNovoCPBEGAprovRejRisco()){
							formLocal.setSelectedModuleCode(ScreenNamesEnum.CUSTOMER_DOC_STATUS.getNome());
							
							String[] selectedCpfCnpjlist = FormatUtils.unformatterDoc(formLocal.getSelectedRegister().getCpfCnpjNbr()).replaceAll("\r", "").replaceAll("\n", "").replaceAll(" ", "").split(",");
							Date now = new Date();
							for (String cpfCnpj : selectedCpfCnpjlist) {
								if(!StringUtils.isBlank(cpfCnpj)){
									formLocal.getSelectedRegister().setCpfCnpjNbr(cpfCnpj);
									formLocal.getSelectedRegister().setLastUpdUserId(loggedUser.getUserID());
									formLocal.getSelectedRegister().setLastUpdDate(now);
									formLocal.getSelectedRegister().setRecStatCode("A");										
									//service.approve(formLocal.getSelectedRegister());												
									formLocal.setOnlyView(true);
								}
							}
							
							String selectedCpfCnpjlistString = Arrays.toString(selectedCpfCnpjlist);
							selectedCpfCnpjlistString = selectedCpfCnpjlistString.replace("[", "").replace("]", "");
							formLocal.getSelectedRegister().setCpfCnpjNbr(selectedCpfCnpjlistString);
							formLocal.addMessage(StatusCpfCnpjForm.C_MESSAGE_SUCESS);
							
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
							formLocal.setSelectedModuleCode(ScreenNamesEnum.CUSTOMER_DOC_STATUS.getNome());
							//service.reprove(formLocal);
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

					formLocal.setTituloValues(DocumentsEnum.getTypeList());					
					saveMessages(request, cloneMessage(formLocal));
					saveErrors(request, (ActionMessages) cloneErrors(formLocal));
					request.getSession().setAttribute("DocumentsForm", formLocal);
				}
				
				
				if(searchInsert){
					actionForward = actionMapping.findForward("searchInsert");	
				}else{
					actionForward = actionMapping.findForward(forwardKey);	
				}
				
				
				}} catch (Throwable t_) {
				if(StringUtils.isBlank(forwardKey)){
					actionForward = actionMapping.findForward("search");
				}else{
					actionForward = actionMapping.findForward(forwardKey);
				}				
				String idLog = FormatUtils.generateIdLog();				
				if (formLocal != null) {
					formLocal.clearErrors();
					formLocal.clearMessages();
					formLocal.addError(StatusCpfCnpjForm.C_ERROR_GENERIC, idLog);
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

	public DocumentsService getService() {
		return service;
	}

	public void setService(DocumentsService service) {
		this.service = service;
	}
}
