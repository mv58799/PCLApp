package com.citibank.newcpb.action;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
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
import org.apache.struts.upload.FormFile;

import com.citibank.newcpb.enums.ScreenNamesEnum;
import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.enun.QuestKeTypeEnum;
import com.citibank.newcpb.form.CentralApprovalForm;
import com.citibank.newcpb.form.QuestionsKeForm;
import com.citibank.newcpb.logger.ApplicationLogger;
import com.citibank.newcpb.logger.LoggerFactory;
import com.citibank.newcpb.service.ParamService;
import com.citibank.newcpb.service.QuestionsKeService;
import com.citibank.newcpb.service.ResultTableService;
import com.citibank.newcpb.service.ValidationService;
import com.citibank.newcpb.util.FormatUtils;
import com.citibank.newcpb.vo.KeCustFileVO;
import com.citibank.newcpb.vo.QuestionsKeVO;
import com.citibank.ods.common.action.BaseAction;
import com.citibank.ods.common.user.User;

public class QuestionsKeAction extends CommonAction {

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
					
			QuestionsKeForm questionsKeForm = (QuestionsKeForm) form;
			HttpSession session = request.getSession();
			loggedUser = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
			
			try {
				questionsKeForm.setLoggedUser(loggedUser);
				questionsKeForm.setApprove(false);				
				questionsKeForm.clearErrors();
				questionsKeForm.clearMessages();
				questionsKeForm.setOnlyView(false);
				
				if(method!=null && method.equals("download")){
					
					Integer selectNumberFile = questionsKeForm.getSelectNumberFile();					
					if(selectNumberFile!=null && questionsKeForm.getQuestionsKeVO().getFileList()!=null 
							&& questionsKeForm.getQuestionsKeVO().getFileList().size()>0){
						
						for( KeCustFileVO keCustFileVO : questionsKeForm.getQuestionsKeVO().getFileList()){
							
							if(keCustFileVO.getFileSeq().intValue()==selectNumberFile.intValue()){										
								
								response.setContentType("application/octet-stream");
								response.setHeader ("Content-Disposition", "attachment; filename=" +  keCustFileVO.getFileName());
							         
							    ByteArrayInputStream in = new ByteArrayInputStream(keCustFileVO.getFile());  
								PrintWriter output = response.getWriter();						         
							    int bit = 256;							     
							      while ((bit) >= 0) {
								    bit = in.read();
								    output.write(bit);
								 }						       
							      output.flush();
							      output.close();
							      in.close();  
							      break;
							}
						}						
					}
					
					saveMessages(request, cloneMessage(questionsKeForm));
					saveErrors(request, (ActionMessages) cloneErrors(questionsKeForm));
					request.getSession().setAttribute("QuestionsKeForm", questionsKeForm);
					
					return mapping.findForward("viewEdit");
					
					
				}else if(method!=null && method.equals("uploadDelete")){
					
					Integer selectNumberFile = questionsKeForm.getSelectNumberFile();					
					if(selectNumberFile!=null && questionsKeForm.getQuestionsKeVO().getFileList()!=null 
							&& questionsKeForm.getQuestionsKeVO().getFileList().size()>0){
						
						for( KeCustFileVO keCustFileVO : questionsKeForm.getQuestionsKeVO().getFileList()){
							
							if(keCustFileVO.getFileSeq().intValue()==selectNumberFile.intValue()){
								questionsKeForm.getQuestionsKeVO().getFileList().remove(keCustFileVO);
								break;
							}
						}						
					}
									
					saveMessages(request, cloneMessage(questionsKeForm));
					saveErrors(request, (ActionMessages) cloneErrors(questionsKeForm));
					request.getSession().setAttribute("QuestionsKeForm", questionsKeForm);
					return mapping.findForward("viewEdit");
					
				} else if(method!=null && method.equals("upload")){
					
					FormFile formFile = questionsKeForm.getFile();
					ValidationService validationService = new ValidationService();
					
					if(formFile!=null && formFile.getFileData()!=null && !StringUtils.isBlank(formFile.getFileName())){						
						
						ParamService paramService = new ParamService();
						String questKeMaxQtFile =  paramService.getParamValue("QUEST_KE_MAX_QT_FILE");
						
						if(questKeMaxQtFile==null || StringUtils.isBlank(questKeMaxQtFile) || !validationService.isNumberInteger(questKeMaxQtFile)){
							 questKeMaxQtFile = "0";
						}
						
						if(questionsKeForm.getQuestionsKeVO().getFileList().size() >= Integer.parseInt(questKeMaxQtFile.trim())){
							questionsKeForm.addError(QuestionsKeForm.C_ERROR_QUESTION_KE_QTD_MAX, questKeMaxQtFile);
						}
						
						if(formFile.getFileSize()>2000000){
							questionsKeForm.addError(QuestionsKeForm.C_ERROR_QUESTION_KE_LIMIT_SIZE);
						}
						
						formFile.getFileData();
						
						if(questionsKeForm.getErrors()!=null && questionsKeForm.getErrors().size()==0){
							
							KeCustFileVO keCustFileVO = new KeCustFileVO();
							keCustFileVO.setAcctNbr(questionsKeForm.getQuestionsKeVO().getAcctNbr());
							keCustFileVO.setFileI(formFile.getInputStream());
							keCustFileVO.setFile(formFile.getFileData());
							keCustFileVO.setFileName(formFile.getFileName());
							keCustFileVO.setFileSeq(questionsKeForm.getIdAuxContFile());
							keCustFileVO.setCreateDate(new Date());
							keCustFileVO.setCreateUser(questionsKeForm.getLoggedUser().getUserID());					
							questionsKeForm.getQuestionsKeVO().getFileList().add(keCustFileVO);		
						}
						
					}else{						
						questionsKeForm.addError(QuestionsKeForm.C_ERROR_QUESTION_KE_EMPTY);
					}
					
					
					questionsKeForm.setFile(null);
					saveMessages(request, cloneMessage(questionsKeForm));
					saveErrors(request, (ActionMessages) cloneErrors(questionsKeForm));
					request.getSession().setAttribute("QuestionsKeForm", questionsKeForm);
					return mapping.findForward("viewEdit");
					

				} else if (method!=null && method.equals("search")) {		
					
					questionsKeForm.setFromApprove(false);
					request.getSession().removeAttribute("lista");
					
					if(questionsKeForm!=null){
												
						if(StringUtils.isBlank(questionsKeForm.getFilterNumberAccount()) 
								&& StringUtils.isBlank(questionsKeForm.getFilterCpfCnpj()) 
								&& StringUtils.isBlank(questionsKeForm.getFilterNumberEM()) 
								&& StringUtils.isBlank(questionsKeForm.getFilterNumberGFCID()) 
								&& StringUtils.isBlank(questionsKeForm.getFilterName())){
							
							questionsKeForm.addError(QuestionsKeForm.C_ERROR_EMPTY_FILTER);
							
						}else{
							
							QuestionsKeService service = new QuestionsKeService();
							questionsKeForm = service.listAcctToQuestionsKe(questionsKeForm);
							
							if(questionsKeForm!=null && questionsKeForm.getResultList()!=null & questionsKeForm.getResultList().size()>0){
								request.getSession().setAttribute("lista",questionsKeForm.getResultList());
							}else{
								request.getSession().removeAttribute("lista");
							}
						}
					}

					saveMessages(request, cloneMessage(questionsKeForm));
					saveErrors(request, (ActionMessages) cloneErrors(questionsKeForm));
					request.getSession().setAttribute("QuestionsKeForm", questionsKeForm);
					return mapping.findForward("search");


				}else if(method!=null && method.equals("view")){
										
					request.getSession().removeAttribute("questionsKeVOList");
					questionsKeForm.setFromApprove(false);
					String selectNumberAccount = questionsKeForm.getSelectNumberAccount();
					String selectCpfCnpj = questionsKeForm.getSelectCpfCnpj();
					
					if(!StringUtils.isBlank(selectNumberAccount) && !StringUtils.isBlank(selectCpfCnpj)){
						
						QuestionsKeService service = new QuestionsKeService();
						QuestionsKeVO vo = service.getQuestionsKe(selectNumberAccount, selectCpfCnpj, null);						
						
						questionsKeForm.setQuestionsKeVO(vo);
						
						request.getSession().setAttribute("questionsKeVOList",vo.getQuestionsKeAnswVOList());
						ResultTableService resultTableService = new ResultTableService();	
						questionsKeForm.setKnowledgeExperienceProd(resultTableService.listDmnKeAnsw(QuestKeTypeEnum.PROD.getValue()));
						questionsKeForm.setAverageFrequencyByYear(resultTableService.listDmnKeAnsw(QuestKeTypeEnum.FMA.getValue()));
						questionsKeForm.setAverageVolumeByYear(resultTableService.listDmnKeAnsw(QuestKeTypeEnum.VMA.getValue()));
						questionsKeForm.setOnlyView(true);

					}
					
					request.getSession().setAttribute("QuestionsKeForm", questionsKeForm);
					return mapping.findForward("viewEdit");
					
	
				}else if(method!=null && method.equals("viewEdit")){
					
					String selectNumberAccount = request.getParameter("processCode1");
					String selectCpfCnpj = request.getParameter("processCode2");
					
					if(StringUtils.isBlank(selectNumberAccount)){
						if(questionsKeForm!=null && questionsKeForm.getSelectNumberAccount()!=null){
							selectNumberAccount = questionsKeForm.getSelectNumberAccount();
							selectCpfCnpj = questionsKeForm.getSelectCpfCnpj();
						}
					}
					
					QuestionsKeService service = new QuestionsKeService();
					QuestionsKeVO vo = new QuestionsKeVO();
					if(!StringUtils.isBlank(selectNumberAccount) && !StringUtils.isBlank(selectCpfCnpj)){
						
						String fromApprove = request.getParameter("fromApprove");
						
						if(!StringUtils.isBlank(fromApprove) && fromApprove.equals("true")){
							questionsKeForm.setFromApprove(true);
							
								vo = service.getQuestionsKeMov(selectNumberAccount, selectCpfCnpj, null);

						}else{							
							questionsKeForm.setFromApprove(false);
							if(!service.hasRegisterMov(selectNumberAccount, selectCpfCnpj)){
								vo = service.getQuestionsKe(selectNumberAccount, selectCpfCnpj, null);

							}else{	
								questionsKeForm.addError(QuestionsKeForm.C_ERROR_REGISTER_BLOCK);
								saveMessages(request, cloneMessage(questionsKeForm));
								saveErrors(request, (ActionMessages) cloneErrors(questionsKeForm));
								request.getSession().setAttribute("QuestionsKeForm", questionsKeForm);
								return mapping.findForward("search");	
							}	
						}						
												
						questionsKeForm.setQuestionsKeVO(vo);
					}
					
					ResultTableService resultTableService = new ResultTableService();	
					questionsKeForm.setKnowledgeExperienceProd(resultTableService.listDmnKeAnsw(QuestKeTypeEnum.PROD.getValue()));
					questionsKeForm.setAverageFrequencyByYear(resultTableService.listDmnKeAnsw(QuestKeTypeEnum.FMA.getValue()));
					questionsKeForm.setAverageVolumeByYear(resultTableService.listDmnKeAnsw(QuestKeTypeEnum.VMA.getValue()));		

					
					saveMessages(request, cloneMessage(questionsKeForm));
					saveErrors(request, (ActionMessages) cloneErrors(questionsKeForm));
					
					request.getSession().setAttribute("questionsKeVOList", vo.getQuestionsKeAnswVOList());
					request.getSession().setAttribute("QuestionsKeForm", questionsKeForm);
					return mapping.findForward("viewEdit");
					
					
				}else if (method!=null && method.equals("save")){
										
					
					if(questionsKeForm.getErrors()!=null && questionsKeForm.getErrors().size()==0){
						
						QuestionsKeService service = new QuestionsKeService();
						questionsKeForm.getQuestionsKeVO().setLastUpdUser(questionsKeForm.getLoggedUser().getUserID());
						
						if(questionsKeForm.getQuestionsKeVO().getQuestionsKeAnswVOList()!=null 
								&& questionsKeForm.getQuestionsKeVO().getTableOrigin()!=null){
							if(questionsKeForm.getQuestionsKeVO().getTableOrigin().equals(TableTypeEnum.EFFECTIVE)){
								service.saveForAprove(questionsKeForm.getQuestionsKeVO());
								questionsKeForm.setOnlyView(true);
								questionsKeForm.addMessage(QuestionsKeForm.C_MESSAGE_SUCESS);
							}else if(questionsKeForm.getQuestionsKeVO().getTableOrigin().equals(TableTypeEnum.MOVEMENT)){
								service.updateMov(questionsKeForm.getQuestionsKeVO());
								questionsKeForm.addMessage(QuestionsKeForm.C_MESSAGE_SUCESS);
							}
						}
						
					}

					saveMessages(request, cloneMessage(questionsKeForm));
					saveErrors(request, (ActionMessages) cloneErrors(questionsKeForm));
					request.getSession().setAttribute("QuestionsKeForm", questionsKeForm);
					return mapping.findForward("viewEdit");

				}else if("viewApproveReprove".equalsIgnoreCase(method)){
					
					questionsKeForm.setFromApprove(true);
					if(questionsKeForm.getLoggedUser()!=null && questionsKeForm.getLoggedUser().getUserAccess()!=null 
							&& questionsKeForm.getLoggedUser().getUserAccess().isHasAccessNovoCPBQuestionsKeAprovRej()){
						
						String selectNumberAccount = request.getParameter("processCode1");
						String selectCpfCnpj = request.getParameter("processCode2");
								
								
						
						if(StringUtils.isBlank(selectNumberAccount)){
							if(questionsKeForm!=null && questionsKeForm.getSelectNumberAccount()!=null){
								selectNumberAccount = questionsKeForm.getSelectNumberAccount();
								selectCpfCnpj = questionsKeForm.getSelectCpfCnpj();
							}
						}
						
						QuestionsKeService service = new QuestionsKeService();
						QuestionsKeVO vo = new QuestionsKeVO();
						if(!StringUtils.isBlank(selectNumberAccount)){						
							vo = service.getQuestionsKeMov(selectNumberAccount, selectCpfCnpj, null);
							questionsKeForm.setQuestionsKeVO(vo);
						}
						
						ResultTableService resultTableService = new ResultTableService();	
						questionsKeForm.setKnowledgeExperienceProd(resultTableService.listDmnKeAnsw(QuestKeTypeEnum.PROD.getValue()));
						questionsKeForm.setAverageFrequencyByYear(resultTableService.listDmnKeAnsw(QuestKeTypeEnum.FMA.getValue()));
						questionsKeForm.setAverageVolumeByYear(resultTableService.listDmnKeAnsw(QuestKeTypeEnum.VMA.getValue()));			

						questionsKeForm.setApprove(true);
						questionsKeForm.setOnlyView(true);
						if (questionsKeForm != null) {
							saveMessages(request, cloneMessage(questionsKeForm));
							saveErrors(request, (ActionMessages) cloneErrors(questionsKeForm));
						}
						
					
						request.getSession().setAttribute("questionsKeVOList",vo.getQuestionsKeAnswVOList());
						request.getSession().setAttribute("QuestionsKeForm", questionsKeForm);
						return mapping.findForward("viewEdit");
					}else{
						return mapping.findForward("accessDenied");
					}					
				}else if("approve".equalsIgnoreCase(method)){
					if(questionsKeForm.getLoggedUser()!=null && questionsKeForm.getLoggedUser().getUserAccess()!=null 
							&& questionsKeForm.getLoggedUser().getUserAccess().isHasAccessNovoCPBQuestionsKeAprovRej()){
						QuestionsKeService service = new QuestionsKeService();
						questionsKeForm.setSelectedModuleCode(ScreenNamesEnum.QUESTIONS_KE_CUSTOMER.getNome());
						questionsKeForm.getQuestionsKeVO().setLastAuthUser(questionsKeForm.getLoggedUser().getUserID());
						service.approve(questionsKeForm, new Date());
						if (questionsKeForm != null) {
							saveMessages(request, cloneMessage(questionsKeForm));
							saveErrors(request, (ActionMessages) cloneErrors(questionsKeForm));
						}
						request.getSession().setAttribute("QuestionsKeForm", questionsKeForm);
						return mapping.findForward("viewEdit");					
					}else{
						return mapping.findForward("accessDenied");
					}

				}else if("reprove".equalsIgnoreCase(method)){
					if(questionsKeForm.getLoggedUser()!=null && questionsKeForm.getLoggedUser().getUserAccess()!=null 
							&& questionsKeForm.getLoggedUser().getUserAccess().isHasAccessNovoCPBQuestionsKeAprovRej()){
						QuestionsKeService service = new QuestionsKeService();
						questionsKeForm.setSelectedModuleCode(ScreenNamesEnum.QUESTIONS_KE_AUTH.getNome());
						service.reprove(questionsKeForm);
						if (questionsKeForm != null) {
							saveMessages(request, cloneMessage(questionsKeForm));
							saveErrors(request, (ActionMessages) cloneErrors(questionsKeForm));
						}
						request.getSession().setAttribute("QuestionsKeForm", questionsKeForm);
						return mapping.findForward("viewEdit");					
					}else{
						return mapping.findForward("accessDenied");
					}

				}else if("backApprove".equalsIgnoreCase(method)){
					ActionRedirect redirect = new ActionRedirect("NEWCPB.CentralApproval");
					redirect.addParameter("method", "search");
					if (questionsKeForm != null) {
						saveMessages(request, cloneMessage(questionsKeForm));
						saveErrors(request, (ActionMessages) cloneErrors(questionsKeForm));
					}
					return redirect;

				}else{
					request.getSession().removeAttribute("lista");
					questionsKeForm.setFilterName("");
					questionsKeForm.setFilterNumberEM("");
					questionsKeForm.setFilterNumberGFCID("");
					questionsKeForm.setFilterCpfCnpj("");
					questionsKeForm.setFilterNumberAccount("");
					request.getSession().setAttribute("QuestionsKeForm", questionsKeForm);
					return mapping.findForward("search");

				}
			} catch (Throwable t_) {
				request.getSession().removeAttribute("lista");
				actionForward = mapping.findForward("search");
				String idLog = FormatUtils.generateIdLog();				
				if (questionsKeForm != null) {
					questionsKeForm.clearErrors();
					questionsKeForm.clearMessages();
					questionsKeForm.addError(CentralApprovalForm.C_ERROR_GENERIC, idLog);
					saveMessages(request, cloneMessage(questionsKeForm));
					saveErrors(request, (ActionMessages) cloneErrors(questionsKeForm));
				}
				applicationLogger.error("Ocorreu um erro interno na aplicação [LOG: " + idLog + "]: " + t_.toString(), t_);
				request.getSession().setAttribute("QuestionsKeForm", questionsKeForm);
				return actionForward;
			}
		} catch (Throwable t_) {			
			actionForward = mapping.findForward(forwardKey);
			applicationLogger.error("Erro inesperado: " + t_.getMessage(), t_);
			actionForward = mapping.findForward("SYSTEMERROR");
			return actionForward;
		}
	}
}
