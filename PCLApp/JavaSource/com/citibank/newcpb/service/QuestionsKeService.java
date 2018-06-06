package com.citibank.newcpb.service;

import java.util.ArrayList;
import java.util.Date;
import javax.transaction.UserTransaction;
import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.enun.QuestKeTypeEnum;
import com.citibank.newcpb.form.CentralApprovalForm;
import com.citibank.newcpb.form.QuestionsKeForm;
import com.citibank.newcpb.persistence.dao.TplPrvtAcctCmplDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtAcctKeAnswDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtAcctKeAnswMovDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtAcctKeDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtAcctKeMovDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtDmnKeProdDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtDmnKeProdMovDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtKeCustFileDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtKeCustFileMovDAO;
import com.citibank.newcpb.persistence.dao.VrhEmployeeRegistrationDAO;
import com.citibank.newcpb.vo.AcctCmplVO;
import com.citibank.newcpb.vo.EmployeeRegistrationVO;
import com.citibank.newcpb.vo.KeCustFileVO;
import com.citibank.newcpb.vo.QuestionsKeAnswVO;
import com.citibank.newcpb.vo.QuestionsKeVO;

public class QuestionsKeService extends CommonService {
	
	
	public QuestionsKeForm listAcctToQuestionsKe(QuestionsKeForm form) {

		TplPrvtAcctCmplDAO dao = new TplPrvtAcctCmplDAO();
		ArrayList<AcctCmplVO> resultList = dao.listByFilterToQuestionKe(form.getFilterNumberAccount(), form.getFilterCpfCnpj(),
				form.getFilterNumberEM(), form.getFilterNumberGFCID(), form.getFilterName());
		
		form.setResultList(resultList);
		
		if (resultList.size() > 0) {
			form.addMessage(QuestionsKeForm.C_MESSAGE_SUCESS);
		} else {
			form.addMessage(QuestionsKeForm.C_NO_REGISTER_FOUND);
		}
		
		return form;
	}
	
	public QuestionsKeForm listAcctToQuestionsAuthorizedKe(QuestionsKeForm form) {

		TplPrvtAcctCmplDAO dao = new TplPrvtAcctCmplDAO();
		ArrayList<AcctCmplVO> resultList = dao.listByFilterAuthorizedToQuestionKe(form.getFilterNumberAccount(), form.getFilterCpfCnpj(),
				form.getFilterNumberEM(), form.getFilterName());
		
		form.setResultList(resultList);
		
		if (resultList.size() > 0) {
			form.addMessage(QuestionsKeForm.C_MESSAGE_SUCESS);
		} else {
			form.addMessage(QuestionsKeForm.C_NO_REGISTER_FOUND);
		}
		
		return form;
	}
	
	public QuestionsKeVO getQuestionsKe(String acctNbr, String cpfCnpjNbr, String accountType) {
		
		TplPrvtAcctKeDAO tplPrvtAcctKeDAO = new TplPrvtAcctKeDAO();
		QuestionsKeVO vo = tplPrvtAcctKeDAO.getAcctKe(acctNbr, cpfCnpjNbr);
		
		if(vo!=null){
			vo.setHasQuestionsKe(true);
		}else{
			vo = new QuestionsKeVO();
			vo.setHasQuestionsKe(false);
			vo.setTableOrigin(TableTypeEnum.EFFECTIVE);
		}
		
		TplPrvtKeCustFileDAO tplPrvtKeCustFileDAO = new TplPrvtKeCustFileDAO();
		ArrayList<KeCustFileVO>  keCustFileList = tplPrvtKeCustFileDAO.list(acctNbr, cpfCnpjNbr);
		
		TplPrvtDmnKeProdDAO tplPrvtDmnKeProdDAO  = new TplPrvtDmnKeProdDAO();
		AcctCmplVO acctCmplVO = getAcctToQuestionsKe(acctNbr, null, null);
		
		ArrayList<QuestionsKeAnswVO> questionsKeDmnVOList = tplPrvtDmnKeProdDAO.listQuestionsKeDmn(acctNbr, cpfCnpjNbr);
		ArrayList<QuestionsKeAnswVO> questionsKeAnswVOList = loadQuestionsKe(acctNbr,cpfCnpjNbr,accountType,questionsKeDmnVOList);
		
		vo.setAcctNbr(acctNbr);
		vo.setCpfCnpjNbr(cpfCnpjNbr);
		vo.setFileList(keCustFileList);
		vo.setAcctCmplVO(acctCmplVO);
		vo.setQuestionsKeAnswVOList(questionsKeAnswVOList);
		
		return vo;
	}
	
	
	public QuestionsKeVO getQuestionsAuthorizedKe(String acctNbr, String cpfCnpjNbr, String accountType) {
		
		TplPrvtAcctKeDAO tplPrvtAcctKeDAO = new TplPrvtAcctKeDAO();
		QuestionsKeVO vo = tplPrvtAcctKeDAO.getAcctKe(acctNbr, cpfCnpjNbr);
		
		if(vo!=null){
			vo.setHasQuestionsKe(true);
		}else{
			vo = new QuestionsKeVO();
			vo.setHasQuestionsKe(false);
			vo.setTableOrigin(TableTypeEnum.EFFECTIVE);
		}
		
		TplPrvtKeCustFileDAO tplPrvtKeCustFileDAO = new TplPrvtKeCustFileDAO();
		ArrayList<KeCustFileVO>  keCustFileList = tplPrvtKeCustFileDAO.list(acctNbr, cpfCnpjNbr);
		
		TplPrvtDmnKeProdDAO tplPrvtDmnKeProdDAO  = new TplPrvtDmnKeProdDAO();
		AcctCmplVO acctCmplVO = getAcctToQuestionsAuthorizedKe(acctNbr, cpfCnpjNbr, null);
		
		ArrayList<QuestionsKeAnswVO> questionsKeDmnVOList = tplPrvtDmnKeProdDAO.listQuestionsKeDmn(acctNbr, cpfCnpjNbr);
		ArrayList<QuestionsKeAnswVO> questionsKeAnswVOList = loadQuestionsKe(acctNbr,cpfCnpjNbr,accountType,questionsKeDmnVOList);
		
		vo.setAcctNbr(acctNbr);
		vo.setCpfCnpjNbr(cpfCnpjNbr);
		vo.setFileList(keCustFileList);
		vo.setAcctCmplVO(acctCmplVO);
		vo.setQuestionsKeAnswVOList(questionsKeAnswVOList);
		
		return vo;
	}
		
	public QuestionsKeVO  getQuestionsKeMov(String acctNbr, String cpfCnpjNbr, String accountType) {
		
		TplPrvtAcctKeMovDAO tplPrvtAcctKeMovDAO = new TplPrvtAcctKeMovDAO();
		QuestionsKeVO vo = tplPrvtAcctKeMovDAO.getAcctKe(acctNbr, cpfCnpjNbr);
		
		if(vo!=null){
			vo.setHasQuestionsKe(true);
		}else{
			vo = new QuestionsKeVO();
			vo.setHasQuestionsKe(false);
			vo.setTableOrigin(TableTypeEnum.MOVEMENT);
		}
		
		TplPrvtKeCustFileMovDAO tplPrvtKeCustFileMovDAO = new TplPrvtKeCustFileMovDAO();
		ArrayList<KeCustFileVO>  keCustFileList = tplPrvtKeCustFileMovDAO.list(acctNbr, cpfCnpjNbr);
		
		TplPrvtDmnKeProdMovDAO tplPrvtDmnKeProdMovDAO  = new TplPrvtDmnKeProdMovDAO();
		AcctCmplVO acctCmplVO = getAcctToQuestionsKe(acctNbr, cpfCnpjNbr, null);
		
		ArrayList<QuestionsKeAnswVO> questionsKeDmnVOList = tplPrvtDmnKeProdMovDAO.listQuestionsKeDmn(acctNbr, cpfCnpjNbr);	
		ArrayList<QuestionsKeAnswVO> questionsKeAnswVOList = loadQuestionsKe(acctNbr,cpfCnpjNbr,accountType,questionsKeDmnVOList);
		
		vo.setAcctNbr(acctNbr);
		vo.setCpfCnpjNbr(cpfCnpjNbr);
		vo.setFileList(keCustFileList);
		vo.setAcctCmplVO(acctCmplVO);
		vo.setQuestionsKeAnswVOList(questionsKeAnswVOList);
		
		return vo;
	}
	
	public QuestionsKeVO  getQuestionsAuthorizedKeMov(String acctNbr, String cpfCnpjNbr, String accountType) {
		
		TplPrvtAcctKeMovDAO tplPrvtAcctKeMovDAO = new TplPrvtAcctKeMovDAO();
		QuestionsKeVO vo = tplPrvtAcctKeMovDAO.getAcctKe(acctNbr,cpfCnpjNbr);
		
		if(vo!=null){
			vo.setHasQuestionsKe(true);
		}else{
			vo = new QuestionsKeVO();
			vo.setHasQuestionsKe(false);
			vo.setTableOrigin(TableTypeEnum.MOVEMENT);
		}
		
		TplPrvtKeCustFileMovDAO tplPrvtKeCustFileMovDAO = new TplPrvtKeCustFileMovDAO();
		ArrayList<KeCustFileVO>  keCustFileList = tplPrvtKeCustFileMovDAO.list(acctNbr, cpfCnpjNbr);
		
		TplPrvtDmnKeProdMovDAO tplPrvtDmnKeProdMovDAO  = new TplPrvtDmnKeProdMovDAO();
		AcctCmplVO acctCmplVO = getAcctToQuestionsAuthorizedKe(acctNbr, cpfCnpjNbr, null);
		
		ArrayList<QuestionsKeAnswVO> questionsKeDmnVOList = tplPrvtDmnKeProdMovDAO.listQuestionsKeDmn(acctNbr, cpfCnpjNbr);	
		ArrayList<QuestionsKeAnswVO> questionsKeAnswVOList = loadQuestionsKe(acctNbr,cpfCnpjNbr,accountType,questionsKeDmnVOList);
		
		vo.setAcctNbr(acctNbr);
		vo.setCpfCnpjNbr(cpfCnpjNbr);
		vo.setFileList(keCustFileList);
		vo.setAcctCmplVO(acctCmplVO);
		vo.setQuestionsKeAnswVOList(questionsKeAnswVOList);
		
		return vo;
	}
	
	public AcctCmplVO getAcctToQuestionsKe(String acctNbr, String cpfCnpjNbr, String accountType) {
		TplPrvtAcctCmplDAO dao = new TplPrvtAcctCmplDAO();
		AcctCmplVO result = dao.getAccountComplementToQuestionKe(acctNbr, cpfCnpjNbr, accountType);
		if(result!=null){
			EmployeeRegistrationVO employeeRegistrationVO = getEmployeeRegistration(result.getOfficeSoeId());
			if(employeeRegistrationVO!=null){
				result.setBankerName(employeeRegistrationVO.getEmployeeName());
			}
		}

		return result;
	}
	
	
	public AcctCmplVO getAcctToQuestionsAuthorizedKe(String acctNbr, String cpfCnpjNbr, String accountType) {
		TplPrvtAcctCmplDAO dao = new TplPrvtAcctCmplDAO();
		AcctCmplVO result = dao.getAccountComplementAuthorizedToQuestionKe(acctNbr, cpfCnpjNbr, accountType);
		if(result!=null){
			EmployeeRegistrationVO employeeRegistrationVO = getEmployeeRegistration(result.getOfficeSoeId());
			if(employeeRegistrationVO!=null){
				result.setBankerName(employeeRegistrationVO.getEmployeeName());
			}
		}

		return result;
	}

	public ArrayList<QuestionsKeAnswVO>  loadQuestionsKe(String acctNbr, String cpfCnpjNbr, String accountType
			, ArrayList<QuestionsKeAnswVO> questionsKeDmnVOList) {
		
		ArrayList<QuestionsKeAnswVO> questionsKeDmnVOForView = new ArrayList<QuestionsKeAnswVO>();
		
		for(QuestionsKeAnswVO vo : questionsKeDmnVOList){
			if(vo.getProdKeCode().equals(vo.getProdFamlKeCode())){
				
				vo.setNeedAnswer(true);
				vo.setQuestionName("&nbsp;" + vo.getProdFamlSeq()+ "." + vo.getProdFamlKeText());
				vo.setColorBackground("background-color:#b4b4b4;font-weight: bold;");
				questionsKeDmnVOForView.add(vo);				
			}else{
				if(hasQuestionFaml(questionsKeDmnVOForView, vo.getProdFamlKeCode(), false)){	  
					vo.setNeedAnswer(true);
					vo.setQuestionName("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + vo.getProdKeText());
					vo.setProdFamlKeCode(vo.getProdFamlKeCode());
					vo.setColorBackground("");
					questionsKeDmnVOForView.add(vo);
					
				}else{		
					vo.setNeedAnswer(false);
					vo.setQuestionName("&nbsp;" + vo.getProdFamlSeq() + "." + vo.getProdFamlKeText());
					vo.setColorBackground("background-color:#b4b4b4;font-weight: bold;");
					questionsKeDmnVOForView.add(vo);
					
					QuestionsKeAnswVO needAnswerVo = new QuestionsKeAnswVO();				
					needAnswerVo.setNeedAnswer(true);
					needAnswerVo.setQuestionName("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + vo.getProdKeText());
					needAnswerVo.setColorBackground("");
					needAnswerVo.setAcctNbr(vo.getAcctNbr());
					needAnswerVo.setCpfCnpjNbr(vo.getCpfCnpjNbr());
					needAnswerVo.setFmaAnswer(vo.getFmaAnswer());
					needAnswerVo.setProdAnswer(vo.getProdAnswer());
					needAnswerVo.setVmaAnswer(vo.getVmaAnswer());
					needAnswerVo.setProdFamlKeCode(vo.getProdFamlKeCode());
					needAnswerVo.setProdKeCode(vo.getProdKeCode());
					needAnswerVo.setProdSeq(vo.getProdSeq());
					needAnswerVo.setProdFamlSeq(vo.getProdFamlSeq());					
					questionsKeDmnVOForView.add(needAnswerVo);					
				}	
			}		
		}
		 return questionsKeDmnVOForView;
	}
		
	public boolean hasQuestionFaml(ArrayList<QuestionsKeAnswVO> questionsKeDmnVOForView , String codeFaml, boolean needAnswer){		
		boolean hasQuestionFaml = false;
		for(QuestionsKeAnswVO vo: questionsKeDmnVOForView){
			
			if(vo.getProdFamlKeCode().equals(codeFaml) && vo.isNeedAnswer() == needAnswer ){
				hasQuestionFaml = true;
				break;
			}
		}
		return hasQuestionFaml;
	}		
	
	public void saveForAprove(QuestionsKeVO vo) throws Exception{
		
		UserTransaction transaction = getUserTransaction();
		
		Date lastUpdate = new Date();
		String recStatCode = "";
		if(vo.isHasQuestionsKe()){
			recStatCode = "U";			
		}else{
			recStatCode = "A";
		}
				
		if(vo!=null){
			
			beginTransaction(transaction);
			
			try {				
				//Insert Dados Principais
				TplPrvtAcctKeMovDAO tplPrvtAcctKeMovDAO = new TplPrvtAcctKeMovDAO();
				vo.setLastUpdDate(lastUpdate);
				vo.setRecStatCode(recStatCode);
				tplPrvtAcctKeMovDAO.insertAcctKe(vo);
				
				//Insert Arquivos
				if(vo.getFileList()!=null && vo.getFileList().size()>0){
					int fileCount = 1;
					TplPrvtKeCustFileMovDAO tplPrvtKeCustFileMovDAO = new TplPrvtKeCustFileMovDAO();
					for(KeCustFileVO keCustFileVO : vo.getFileList()){						
						if(!StringUtils.isBlank(keCustFileVO.getAcctNbr())){
							keCustFileVO.setFileSeq(fileCount);
							keCustFileVO.setRecStatCode(recStatCode);
							keCustFileVO.setAcctNbr(vo.getAcctNbr());
							keCustFileVO.setCpfCnpjNbr(vo.getCpfCnpjNbr());
							tplPrvtKeCustFileMovDAO.insert(keCustFileVO);
							tplPrvtKeCustFileMovDAO.getInsertBlob(keCustFileVO.getAcctNbr(), keCustFileVO.getCpfCnpjNbr(), keCustFileVO.getFileSeq(), keCustFileVO.getFile());
							fileCount =  fileCount +1;
						}	
					}				
				}

				//Insert Respostas
				TplPrvtAcctKeAnswMovDAO tplPrvtQuestionKeMovDAO = new TplPrvtAcctKeAnswMovDAO();
				
				if(vo.getQuestionsKeAnswVOList()!=null && vo.getQuestionsKeAnswVOList().size() > 0){
					
					for(QuestionsKeAnswVO questionsKeAnswVO : vo.getQuestionsKeAnswVOList()){		
						questionsKeAnswVO.setRecStatCode(recStatCode);
						if(questionsKeAnswVO.isNeedAnswer()){
							if(!StringUtils.isBlank(questionsKeAnswVO.getProdAnswer())){
								tplPrvtQuestionKeMovDAO.insertAcctQuestionsKeAnswer(questionsKeAnswVO, QuestKeTypeEnum.PROD.getValue(), 
										questionsKeAnswVO.getProdAnswer());								
							}
							
							if(!StringUtils.isBlank(questionsKeAnswVO.getFmaAnswer())){
								tplPrvtQuestionKeMovDAO.insertAcctQuestionsKeAnswer(questionsKeAnswVO, QuestKeTypeEnum.FMA.getValue(), 
										questionsKeAnswVO.getFmaAnswer());
							}
							
							if(!StringUtils.isBlank(questionsKeAnswVO.getVmaAnswer())){
								tplPrvtQuestionKeMovDAO.insertAcctQuestionsKeAnswer(questionsKeAnswVO, QuestKeTypeEnum.VMA.getValue(), 
										questionsKeAnswVO.getVmaAnswer());
							}							
						}
					}			
				}
				
				commitTransaction( transaction );	
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}	
		}		
	}

	public void updateMov(QuestionsKeVO vo) throws Exception{
		
		UserTransaction transaction = getUserTransaction();		
		
		Date lastUpdate = new Date();
		String recStatCode = vo.getRecStatCode();
				
		if(vo!=null){
			beginTransaction(transaction);			
			try {
				//Update Dados Basicos

				TplPrvtAcctKeMovDAO tplPrvtAcctKeMovDAO = new TplPrvtAcctKeMovDAO();
				vo.setLastUpdDate(lastUpdate);
				vo.setRecStatCode(recStatCode);
				tplPrvtAcctKeMovDAO.updateAcctKe(vo);
				
				
				//Insert Arquivos
				TplPrvtKeCustFileMovDAO tplPrvtKeCustFileMovDAO = new TplPrvtKeCustFileMovDAO();
				tplPrvtKeCustFileMovDAO.deleteByAcctNbr(vo.getAcctNbr(), vo.getCpfCnpjNbr());
				if(vo.getFileList()!=null && vo.getFileList().size()>0){
					int fileCount = 1;

					for(KeCustFileVO keCustFileVO : vo.getFileList()){						
						if(!StringUtils.isBlank(keCustFileVO.getAcctNbr())){
							keCustFileVO.setFileSeq(fileCount);
							keCustFileVO.setRecStatCode(recStatCode);
							keCustFileVO.setAcctNbr(vo.getAcctNbr());
							keCustFileVO.setCpfCnpjNbr(vo.getCpfCnpjNbr());
							tplPrvtKeCustFileMovDAO.insert(keCustFileVO);
							tplPrvtKeCustFileMovDAO.getInsertBlob(keCustFileVO.getAcctNbr(), keCustFileVO.getCpfCnpjNbr(), keCustFileVO.getFileSeq(),keCustFileVO.getFile());
							fileCount =  fileCount +1;
						}	
					}				
				}
				
				//Update Respostas
				TplPrvtAcctKeAnswMovDAO tplPrvtQuestionKeMovDAO = new TplPrvtAcctKeAnswMovDAO();
				tplPrvtQuestionKeMovDAO.deleteByAcctNbr(vo.getAcctNbr(), vo.getCpfCnpjNbr());
				if(vo.getQuestionsKeAnswVOList()!=null && vo.getQuestionsKeAnswVOList().size() > 0){
					
					for(QuestionsKeAnswVO questionsKeAnswVO : vo.getQuestionsKeAnswVOList()){	
						questionsKeAnswVO.setRecStatCode(recStatCode);					
						if(questionsKeAnswVO.isNeedAnswer()){
							
							if(!StringUtils.isBlank(questionsKeAnswVO.getProdAnswer())){
								tplPrvtQuestionKeMovDAO.insertAcctQuestionsKeAnswer(questionsKeAnswVO, QuestKeTypeEnum.PROD.getValue(), 
										questionsKeAnswVO.getProdAnswer());
							}
							
							if(!StringUtils.isBlank(questionsKeAnswVO.getFmaAnswer())){
								tplPrvtQuestionKeMovDAO.insertAcctQuestionsKeAnswer(questionsKeAnswVO, QuestKeTypeEnum.FMA.getValue(), 
										questionsKeAnswVO.getFmaAnswer());								
							}

							if(!StringUtils.isBlank(questionsKeAnswVO.getVmaAnswer())){
								tplPrvtQuestionKeMovDAO.insertAcctQuestionsKeAnswer(questionsKeAnswVO, QuestKeTypeEnum.VMA.getValue(), 
										questionsKeAnswVO.getVmaAnswer());
							}							
						}
					}			
				}
				
				commitTransaction( transaction );
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}
		}		
	}
	
	public Boolean hasRegisterMov(String selectNumberAccount, String selectCpfCnpj){
		boolean hasRegisterMov = false;
		
		TplPrvtAcctKeMovDAO dao = new TplPrvtAcctKeMovDAO();
		QuestionsKeVO vo = dao.getAcctKe(selectNumberAccount, selectCpfCnpj);
		if(vo!=null){
			hasRegisterMov = true;
		}
		return hasRegisterMov;
	}
	
	public EmployeeRegistrationVO getEmployeeRegistration(String numberSOEID){
		VrhEmployeeRegistrationDAO dao = new VrhEmployeeRegistrationDAO();
		return dao.getEmployeeRegistration(numberSOEID);
	}
	
	public void approve(QuestionsKeForm form, Date now) throws Exception {
		
		form.clearErrors();

		if (!form.hasErrors()) {
			UserTransaction transaction = getUserTransaction();
			try {
							
				beginTransaction(transaction);
			
				QuestionsKeVO vo = form.getQuestionsKeVO();
				TplPrvtAcctKeDAO tplPrvtAcctKeDAO = new TplPrvtAcctKeDAO();
				TplPrvtAcctKeAnswDAO tplPrvtQuestionKeDAO = new TplPrvtAcctKeAnswDAO();
				TplPrvtKeCustFileDAO tplPrvtKeCustFileDAO =  new TplPrvtKeCustFileDAO();
				
				tplPrvtAcctKeDAO.deleteByAcctNbr(vo.getAcctNbr(), vo.getCpfCnpjNbr());
				tplPrvtQuestionKeDAO.deleteByAcctNbr(vo.getAcctNbr(), vo.getCpfCnpjNbr());
				tplPrvtKeCustFileDAO.deleteByAcctNbr(vo.getAcctNbr(), vo.getCpfCnpjNbr());

				vo.setLastAuthDate(now);
				tplPrvtAcctKeDAO.insertAcctKe(vo);				
				
				//Insert Arquivos

				if(vo.getFileList()!=null && vo.getFileList().size()>0){
					int fileCount = 1;

					for(KeCustFileVO keCustFileVO : vo.getFileList()){						
						if(!StringUtils.isBlank(keCustFileVO.getAcctNbr())){
							keCustFileVO.setFileSeq(fileCount);
							keCustFileVO.setRecStatCode(vo.getRecStatCode());
							keCustFileVO.setAcctNbr(vo.getAcctNbr());
							keCustFileVO.setCpfCnpjNbr(vo.getCpfCnpjNbr());
							tplPrvtKeCustFileDAO.insert(keCustFileVO);
							tplPrvtKeCustFileDAO.getInsertBlob(keCustFileVO.getAcctNbr(), keCustFileVO.getCpfCnpjNbr(),
									keCustFileVO.getFileSeq(), keCustFileVO.getFile());
							fileCount =  fileCount +1;
						}	
					}				
				}
				
				//Insert Respostas				
				if(vo.getQuestionsKeAnswVOList() !=null && vo.getQuestionsKeAnswVOList().size() > 0){
					
					for(QuestionsKeAnswVO questionsKeAnswVO : vo.getQuestionsKeAnswVOList()){	
						questionsKeAnswVO.setRecStatCode(vo.getRecStatCode());
						if(questionsKeAnswVO.isNeedAnswer()){
							
							if(!StringUtils.isBlank(questionsKeAnswVO.getProdAnswer())){
								tplPrvtQuestionKeDAO.insertAcctQuestionsKeAnswer(questionsKeAnswVO, QuestKeTypeEnum.PROD.getValue(), 
										questionsKeAnswVO.getProdAnswer());
							}
							
							if(!StringUtils.isBlank(questionsKeAnswVO.getFmaAnswer())){
								tplPrvtQuestionKeDAO.insertAcctQuestionsKeAnswer(questionsKeAnswVO, QuestKeTypeEnum.FMA.getValue(), 
										questionsKeAnswVO.getFmaAnswer());
							}
							
							if(!StringUtils.isBlank(questionsKeAnswVO.getVmaAnswer())){
								tplPrvtQuestionKeDAO.insertAcctQuestionsKeAnswer(questionsKeAnswVO, QuestKeTypeEnum.VMA.getValue(), 
										questionsKeAnswVO.getVmaAnswer());	
							}						
						}
					}			
				}
							
				// 3 - Remove movimento		
				TplPrvtAcctKeMovDAO tplPrvtAcctKeMovDAO = new TplPrvtAcctKeMovDAO();
				TplPrvtAcctKeAnswMovDAO tplPrvtQuestionKeMovDAO = new TplPrvtAcctKeAnswMovDAO();
				TplPrvtKeCustFileMovDAO tplPrvtKeCustFileMovDAO = new TplPrvtKeCustFileMovDAO();
				tplPrvtKeCustFileMovDAO.deleteByAcctNbr(vo.getAcctNbr(),vo.getCpfCnpjNbr());
				tplPrvtAcctKeMovDAO.deleteByAcctNbr(vo.getAcctNbr(),vo.getCpfCnpjNbr());
				tplPrvtQuestionKeMovDAO.deleteByAcctNbr(vo.getAcctNbr(),vo.getCpfCnpjNbr());
							
					commitTransaction( transaction );
				
				form.addMessage(CentralApprovalForm.C_MESSAGE_SUCESS);
				form.setOnlyView(true);
				form.setFromApprove(true);
			} catch (Exception e) {
					rollbackTransaction(transaction);
				throw e;
			}
		}
	}
	
	public void reprove(QuestionsKeForm form) throws Exception {

		if (!form.hasErrors()) {
				UserTransaction transaction = getUserTransaction();
			try {					
				beginTransaction(transaction);
				
				QuestionsKeVO vo = form.getQuestionsKeVO();
				
				// 3 - Remove movimento		
				TplPrvtAcctKeMovDAO tplPrvtAcctKeMovDAO = new TplPrvtAcctKeMovDAO();
				TplPrvtAcctKeAnswMovDAO tplPrvtQuestionKeMovDAO = new TplPrvtAcctKeAnswMovDAO();
				TplPrvtKeCustFileMovDAO tplPrvtKeCustFileMovDAO = new TplPrvtKeCustFileMovDAO();
				tplPrvtAcctKeMovDAO.deleteByAcctNbr(vo.getAcctNbr(),vo.getCpfCnpjNbr());
				tplPrvtQuestionKeMovDAO.deleteByAcctNbr(vo.getAcctNbr(),vo.getCpfCnpjNbr());
				tplPrvtKeCustFileMovDAO.deleteByAcctNbr(vo.getAcctNbr(), vo.getCpfCnpjNbr());
				
				commitTransaction( transaction );
				
				form.addMessage(CentralApprovalForm.C_MESSAGE_SUCESS);
				form.setOnlyView(true);
				form.setFromApprove(true);
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}
		}
	}
}
