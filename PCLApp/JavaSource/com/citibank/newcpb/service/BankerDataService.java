package com.citibank.newcpb.service;

import java.util.ArrayList;
import java.util.Date;
import javax.transaction.UserTransaction;
import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.form.BankerDataForm;
import com.citibank.newcpb.persistence.dao.TplPrvtOfficerDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtOfficerMovDAO;
import com.citibank.newcpb.persistence.dao.VrhEmployeeRegistrationDAO;
import com.citibank.newcpb.vo.EmployeeRegistrationVO;
import com.citibank.newcpb.vo.OfficerBankerVO;

public class BankerDataService extends CommonService {
	
	private TplPrvtOfficerDAO tplPrvtOfficerDAO;
	private TplPrvtOfficerMovDAO tplPrvtOfficerMovDAO;
	private VrhEmployeeRegistrationDAO vrhEmployeeRegistrationDAO;
	
	public ArrayList<OfficerBankerVO> list(String employeeSOEID, String employeeName) {

		tplPrvtOfficerDAO = new TplPrvtOfficerDAO();
		
		ArrayList<OfficerBankerVO> resultList = tplPrvtOfficerDAO.listBySOEIDAndName(employeeSOEID, employeeName);
		
		return resultList;
	}
	
	public EmployeeRegistrationVO getCustomerBySOEID(String employeeSOEID){
		
		vrhEmployeeRegistrationDAO = new VrhEmployeeRegistrationDAO();
		return vrhEmployeeRegistrationDAO.getEmployeeRegistration(employeeSOEID);
	}
	
	public OfficerBankerVO getOfficerBankerCad(String employeeSOEID){
		
		tplPrvtOfficerDAO = new TplPrvtOfficerDAO();
		OfficerBankerVO vo = tplPrvtOfficerDAO.getOfficer(employeeSOEID);
		
		if(vo!=null){
			
			EmployeeRegistrationVO voEmployeeName = getCustomerBySOEID(employeeSOEID);
			if(voEmployeeName!=null){
				
				vo.setEmployeeName(voEmployeeName.getEmployeeName());
			}
			
			if(!StringUtils.isBlank(vo.getSupervisorSOEID())){
				
				EmployeeRegistrationVO voSupervisorName = getCustomerBySOEID(vo.getSupervisorSOEID());
				if(voSupervisorName!=null){
					
					vo.setSupervisorName(voSupervisorName.getEmployeeName());
				}
			}
			
			if(!StringUtils.isBlank(vo.getAssociateSOEID())){
				
				EmployeeRegistrationVO voAssociateName = getCustomerBySOEID(vo.getAssociateSOEID());
				if(voAssociateName!=null){
					
					vo.setAssociateName(voAssociateName.getEmployeeName());
				}
			}
				
		}
			

		return vo;
	}
	
	public OfficerBankerVO getOfficerBankerMov(String employeeSOEID){
		
		tplPrvtOfficerMovDAO = new TplPrvtOfficerMovDAO();
		OfficerBankerVO vo = tplPrvtOfficerMovDAO.getOfficer(employeeSOEID);
		
		if(vo!=null){
			
			EmployeeRegistrationVO voEmployeeName = getCustomerBySOEID(employeeSOEID);
			if(voEmployeeName!=null){
				
				vo.setEmployeeName(voEmployeeName.getEmployeeName());
			}
			
			if(!StringUtils.isBlank(vo.getSupervisorSOEID())){
				
				EmployeeRegistrationVO voSupervisorName = getCustomerBySOEID(vo.getSupervisorSOEID());
				if(voSupervisorName!=null){
					
					vo.setSupervisorName(voSupervisorName.getEmployeeName());
				}
			}
			
			if(!StringUtils.isBlank(vo.getAssociateSOEID())){
				
				EmployeeRegistrationVO voAssociateName = getCustomerBySOEID(vo.getAssociateSOEID());
				if(voAssociateName!=null){
					
					vo.setAssociateName(voAssociateName.getEmployeeName());
				}
			}
				
		}
			

		return vo;
	}
	
	public Boolean hasRegisterMov(String employeeSOEID){
		
		boolean hasRegisterMov = false;
		
		tplPrvtOfficerMovDAO = new TplPrvtOfficerMovDAO();
		OfficerBankerVO vo = tplPrvtOfficerMovDAO.getOfficer(employeeSOEID);
		
		if(vo!=null){
			hasRegisterMov = true;
		}
		
		return hasRegisterMov;
	}


	
	public void validateIncludeAndUpdate(BankerDataForm form, boolean isEdit){

		if(form.getErrors()!=null && form.getErrors().size()==0){
			
			tplPrvtOfficerMovDAO = new TplPrvtOfficerMovDAO();
			tplPrvtOfficerDAO = new TplPrvtOfficerDAO();
			
			if(!isEdit){
				
				if(tplPrvtOfficerDAO.getOfficer(form.getSelectRegisterOfficerBanker().getEmployeeSOEID())!=null){
					form.addError(BankerDataForm.C_ERROR_ALREADY_SAME_EMPLOYEE_SOEID, form.getSelectRegisterOfficerBanker().getEmployeeSOEID());
					
				}	
			}
			
			
			if(!StringUtils.isBlank(form.getSelectRegisterOfficerBanker().getEmployeeSOEID())){
				if(getCustomerBySOEID(form.getSelectRegisterOfficerBanker().getEmployeeSOEID()) ==null){
					form.addError(BankerDataForm.C_ERROR_EMPLOYEE_SOEID_NON_EXIST, form.getSelectRegisterOfficerBanker().getEmployeeSOEID());
				}
			}
			
			if(!StringUtils.isBlank(form.getSelectRegisterOfficerBanker().getSupervisorSOEID())){
				if(getCustomerBySOEID(form.getSelectRegisterOfficerBanker().getSupervisorSOEID()) == null){
					form.addError(BankerDataForm.C_ERROR_SUPERVISOR_SOEID_NON_EXIST, form.getSelectRegisterOfficerBanker().getSupervisorSOEID());
				}
			}
			
			if(!StringUtils.isBlank(form.getSelectRegisterOfficerBanker().getAssociateSOEID())){
				if(getCustomerBySOEID(form.getSelectRegisterOfficerBanker().getAssociateSOEID()) == null){
					form.addError(BankerDataForm.C_ERROR_ASSOCIATE_SOEID_NON_EXIST, form.getSelectRegisterOfficerBanker().getAssociateSOEID());
				}
			}
			
		}		
	}
	
	public ArrayList<String> getFieldsWithDifference(OfficerBankerVO mov) {
		
		ArrayList<String> idDiffList = new ArrayList<String>();
		
		if(mov!=null && !StringUtils.isBlank(mov.getEmployeeSOEID())){
			
			OfficerBankerVO cad = getOfficerBankerCad(mov.getEmployeeSOEID());
			
			if(cad!=null){
				
				idDiffList = cad.equals(mov);
				
			}
		}
		
		return idDiffList;
	}
	
		
	public void saveForAprove(OfficerBankerVO vo, String recStatCode) throws Exception{
		
		UserTransaction transaction = getUserTransaction();
		
		Date lastUpdate = new Date();
		
		
		if(vo!=null){
			beginTransaction(transaction);
			
			try {
				
				vo.setLastUpdDate(lastUpdate);
				vo.setRecStatCode(recStatCode);
				
				tplPrvtOfficerMovDAO = new TplPrvtOfficerMovDAO();
				tplPrvtOfficerMovDAO.insert(vo);

				commitTransaction( transaction );	
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}	
		}		
	}
	
	public void updateMov(OfficerBankerVO vo) throws Exception{
		
		UserTransaction transaction = getUserTransaction();		
		
		Date lastUpdate = new Date();
		String recStatCode = "A";
		
		if(vo!=null){
			beginTransaction(transaction);
			
			try {
				
				vo.setLastUpdDate(lastUpdate);
				vo.setRecStatCode(recStatCode);
				
				tplPrvtOfficerMovDAO = new TplPrvtOfficerMovDAO();
				tplPrvtOfficerMovDAO.update(vo);
				
				commitTransaction( transaction );
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}
		}		
	}
	

	
	public void approve(BankerDataForm bankerDataForm, Date now) throws Exception {
		
		bankerDataForm.clearErrors();

		if (!bankerDataForm.hasErrors()) {
			UserTransaction transaction = getUserTransaction();
			try {
				
				beginTransaction(transaction);
							
				tplPrvtOfficerMovDAO = new TplPrvtOfficerMovDAO();
				tplPrvtOfficerDAO = new TplPrvtOfficerDAO();	
							
			
				if(bankerDataForm.getLoggedUser()!=null){
					bankerDataForm.getSelectRegisterOfficerBanker().setLastAuthUser(bankerDataForm.getLoggedUser().getUserID());
				}
				if(now!=null){
					bankerDataForm.getSelectRegisterOfficerBanker().setLastAuthDate(now);
				}
				
				if(bankerDataForm.getSelectRegisterOfficerBanker().getRecStatCode().equals("I")){
					
					tplPrvtOfficerDAO.delete(bankerDataForm.getSelectRegisterOfficerBanker().getEmployeeSOEID());
					tplPrvtOfficerMovDAO.delete(bankerDataForm.getSelectRegisterOfficerBanker().getEmployeeSOEID());
					
				}else if(bankerDataForm.getSelectRegisterOfficerBanker().getRecStatCode().equals("A")){
					
					tplPrvtOfficerDAO.insert(bankerDataForm.getSelectRegisterOfficerBanker());
					tplPrvtOfficerMovDAO.delete(bankerDataForm.getSelectRegisterOfficerBanker().getEmployeeSOEID());
					
				}else if(bankerDataForm.getSelectRegisterOfficerBanker().getRecStatCode().equals("U")){
					
					
					tplPrvtOfficerDAO.update(bankerDataForm.getSelectRegisterOfficerBanker());
					tplPrvtOfficerMovDAO.delete(bankerDataForm.getSelectRegisterOfficerBanker().getEmployeeSOEID());
					
				}		
					commitTransaction( transaction );
				
				bankerDataForm.addMessage(BankerDataForm.C_MESSAGE_SUCESS);
				bankerDataForm.setOnlyView(true);
				bankerDataForm.setFromApprove(true);
			} catch (Exception e) {
					rollbackTransaction(transaction);
				throw e;
			}
		}
	}
	
	public void reprove(BankerDataForm bankerDataForm) throws Exception {

		if (!bankerDataForm.hasErrors()) {
				UserTransaction transaction = getUserTransaction();
			try {					
				beginTransaction(transaction);
				
				tplPrvtOfficerMovDAO = new TplPrvtOfficerMovDAO();		
				tplPrvtOfficerMovDAO.delete(bankerDataForm.getSelectRegisterOfficerBanker().getEmployeeSOEID());

				
				commitTransaction( transaction );
				
				bankerDataForm.addMessage(BankerDataForm.C_MESSAGE_SUCESS);
				bankerDataForm.setOnlyView(true);
				bankerDataForm.setFromApprove(true);
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}
		}
	}
	
	

}
