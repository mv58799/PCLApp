package com.citibank.newcpb.service;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.form.AccountMigrationDataForm;
import com.citibank.newcpb.persistence.dao.TplPrvtAcctMgrtDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtAcctMgrtMovDAO;
import com.citibank.newcpb.vo.AcctMgrtVO;
import javax.transaction.UserTransaction;

public class AccountMigrationDataService extends CommonService {
	
	private TplPrvtAcctMgrtDAO tplPrvtAcctMgrtDAO;
	private TplPrvtAcctMgrtMovDAO tplPrvtAcctMgrtMovDAO;
	
	
	public AcctMgrtVO getAccountMigrateMov(String accountGrbNumber, String accountCustodiaNumber, String accountCustodiaCpfCnpjNumber){
		
		tplPrvtAcctMgrtMovDAO = new TplPrvtAcctMgrtMovDAO();
		AcctMgrtVO vo = tplPrvtAcctMgrtMovDAO.getAccountMigrate(accountGrbNumber, accountCustodiaNumber, accountCustodiaCpfCnpjNumber);
		
		if(vo!=null){
			
			AcctMgrtVO voNameGrb = getNameCpfCustomerCitiBankAccount(vo.getAccountGrbNumber());
			AcctMgrtVO voNameCustodia = getNameCpfCustomerCustodiaAccount(vo.getAccountCustodiaNumber());
			
			if(voNameGrb!=null){
				vo.setAccountGrbName(voNameGrb.getAccountGrbName());
			}
			
			if(voNameCustodia!=null){
				vo.setAccountCustodiaName(voNameCustodia.getAccountCustodiaName());
			}
			
		}
		

		

		return vo;
	}
	
	public AcctMgrtVO getAccountMigrateCad(String accountGrbNumber, String accountCustodiaNumber, String accountCustodiaCpfCnpjNumber){
		
		tplPrvtAcctMgrtDAO = new TplPrvtAcctMgrtDAO();
		AcctMgrtVO vo = tplPrvtAcctMgrtDAO.getAccountMigrate(accountGrbNumber, accountCustodiaNumber, accountCustodiaCpfCnpjNumber);
				
		if(vo!=null){
			
			AcctMgrtVO voNameGrb = getNameCpfCustomerCitiBankAccount(vo.getAccountGrbNumber());
			AcctMgrtVO voNameCustodia = getNameCpfCustomerCustodiaAccount(vo.getAccountCustodiaNumber());
			
			if(voNameGrb!=null){
				vo.setAccountGrbName(voNameGrb.getAccountGrbName());
			}
			
			if(voNameCustodia!=null){
				vo.setAccountCustodiaName(voNameCustodia.getAccountCustodiaName());
			}
			
		}
		
		return vo;
	}
	
	
	public AcctMgrtVO getNameCpfCustomerCitiBankAccount(String accountNumber){
		
		tplPrvtAcctMgrtDAO = new TplPrvtAcctMgrtDAO();
		return tplPrvtAcctMgrtDAO.getNameCpfCustomerCitiBankAccount(accountNumber);
	}
	
	public AcctMgrtVO getNameCpfCustomerCustodiaAccount(String accountNumber){
		
		tplPrvtAcctMgrtDAO = new TplPrvtAcctMgrtDAO();
		return tplPrvtAcctMgrtDAO.getNameCpfCustomerCustodiaAccount(accountNumber);
	}
	
	public void validateIncludeAndAddInfo(AccountMigrationDataForm form, boolean isEdit){

		if(form.getErrors()!=null && form.getErrors().size()==0){
			
			
			tplPrvtAcctMgrtDAO = new TplPrvtAcctMgrtDAO();
			
			if(!isEdit){
				
				AcctMgrtVO acctMgrtVOCitibank = tplPrvtAcctMgrtDAO.hasRegisterAccountMgrtCitiBank(form.getSelectRegisterAccountMigrate().getAccountGrbNumber());
				
				if(acctMgrtVOCitibank!=null){
					form.addError(AccountMigrationDataForm.C_ERROR_ALREADY_ACCOUNT_CITIBANK_MGRT, acctMgrtVOCitibank.getAccountGrbNumber(),
							acctMgrtVOCitibank.getAccountCustodiaNumber());
				}
				
				AcctMgrtVO acctMgrtVOCustodia = tplPrvtAcctMgrtDAO.hasRegisterAccountMgrtCustodia(form.getSelectRegisterAccountMigrate().getAccountCustodiaNumber());
				
				if(acctMgrtVOCustodia!=null){
					form.addError(AccountMigrationDataForm.C_ERROR_ALREADY_ACCOUNT_CUSTODIA_MGRT, acctMgrtVOCustodia.getAccountCustodiaNumber(),
							acctMgrtVOCustodia.getAccountGrbNumber());
				}
				
			}else{
				
				AcctMgrtVO acctMgrtVOCustodia = tplPrvtAcctMgrtDAO.hasRegisterAccountMgrtCustodiaDifferentAccountCiti(
						form.getSelectRegisterAccountMigrate().getAccountCustodiaNumber(), form.getSelectRegisterAccountMigrate().getAccountGrbNumber() );
				
				if(acctMgrtVOCustodia!=null){
					form.addError(AccountMigrationDataForm.C_ERROR_ALREADY_ACCOUNT_CUSTODIA_MGRT, acctMgrtVOCustodia.getAccountCustodiaNumber(),
							acctMgrtVOCustodia.getAccountGrbNumber());
				}
			}
			
			

			
			
			
			// VALIDAÇÃO MESMO CPF E ADD INFO
			AcctMgrtVO acctMgrtVOCitibankInfo = tplPrvtAcctMgrtDAO.getNameCpfCustomerCitiBankAccount(form.getSelectRegisterAccountMigrate().getAccountGrbNumber());
			AcctMgrtVO acctMgrtVOCustodiaInfo = tplPrvtAcctMgrtDAO.getNameCpfCustomerCustodiaAccount(form.getSelectRegisterAccountMigrate().getAccountCustodiaNumber());
			
			
			
			if(acctMgrtVOCitibankInfo!=null && acctMgrtVOCustodiaInfo!=null ){
				
				form.getSelectRegisterAccountMigrate().setAccountCustodiaCpfCnpjNumber(acctMgrtVOCustodiaInfo.getAccountCustodiaCpfCnpjNumber());
				form.getSelectRegisterAccountMigrate().setEmNumber(acctMgrtVOCustodiaInfo.getEmNumber());
				form.getSelectRegisterAccountMigrate().setAccountGrbCpfCnpjNumber(acctMgrtVOCitibankInfo.getAccountCustodiaCpfCnpjNumber());
				form.getSelectRegisterAccountMigrate().setCustomerNumber(acctMgrtVOCitibankInfo.getCustomerNumber());
				form.getSelectRegisterAccountMigrate().setRelationNumber(acctMgrtVOCitibankInfo.getRelationNumber());
				
				//Valida mesmo CPF
				if (acctMgrtVOCitibankInfo.getAccountGrbCpfCnpjNumber() != null && acctMgrtVOCustodiaInfo.getAccountCustodiaCpfCnpjNumber() != null) {
					if (!acctMgrtVOCitibankInfo.getAccountGrbCpfCnpjNumber() .equals(acctMgrtVOCustodiaInfo.getAccountCustodiaCpfCnpjNumber())) {
						
						form.setShowConfirmRedirectPageRiskPopup(true);
						form.addError(AccountMigrationDataForm.C_ERROR_CPF_ACCOUNT_GRB_DIFFERENT_CPF_ACCOUNT_CUSTODIA,acctMgrtVOCitibankInfo.getAccountGrbCpfCnpjNumber(),
								acctMgrtVOCustodiaInfo.getAccountCustodiaCpfCnpjNumber());
					}
				} else if ((acctMgrtVOCitibankInfo.getAccountGrbCpfCnpjNumber() == null 
						&& acctMgrtVOCustodiaInfo.getAccountCustodiaCpfCnpjNumber() != null) || (acctMgrtVOCitibankInfo.getAccountGrbCpfCnpjNumber() == null && acctMgrtVOCustodiaInfo.getAccountCustodiaCpfCnpjNumber() != null)) {
					form.setShowConfirmRedirectPageRiskPopup(true);
					form.addError(AccountMigrationDataForm.C_ERROR_CPF_ACCOUNT_GRB_DIFFERENT_CPF_ACCOUNT_CUSTODIA,acctMgrtVOCitibankInfo.getAccountGrbCpfCnpjNumber(),
							acctMgrtVOCustodiaInfo.getAccountCustodiaCpfCnpjNumber());
				}
				
			}else{
				
				if(acctMgrtVOCitibankInfo==null){
					form.addError(AccountMigrationDataForm.C_ERROR_ACCOUNT_CITIBANK_NON_EXIST);
				}
				
				if(acctMgrtVOCustodiaInfo==null){
					form.addError(AccountMigrationDataForm.C_ERROR_ACCOUNT_CUSTODIA_NON_EXIST);
				}
			}
		}
	}
	
	public ArrayList<String> getFieldsWithDifference(AcctMgrtVO mov) {
		
		ArrayList<String> idDiffList = new ArrayList<String>();
		
		if(mov!=null && !StringUtils.isBlank(mov.getAccountGrbNumber()) && !StringUtils.isBlank(mov.getAccountCustodiaNumber())
				&& !StringUtils.isBlank(mov.getAccountCustodiaCpfCnpjNumber())){
			
			AcctMgrtVO cad = getAccountMigrateCad(mov.getAccountGrbNumber(), null, null);
			
			if(cad!=null){
				
				idDiffList = cad.equals(mov);
				
			}
		}
		
		return idDiffList;
	}
	
	
	public ArrayList<AcctMgrtVO> list(AccountMigrationDataForm form) {

		tplPrvtAcctMgrtDAO = new TplPrvtAcctMgrtDAO();
		
		ArrayList<AcctMgrtVO> resultList = tplPrvtAcctMgrtDAO.listByFilter
				(form.getFilterAccountCitiBankNumber(), form.getFilterAccountCustodiaNumber(), 
						form.getFilterAccountCustodiaCpfCnpjNumber(), form.getFilterCustomerNumber(), form.getFilterEmNumber());
		
		return resultList;
	}
	
	
	public Boolean hasRegisterMov(String accountNumberCitiBank , String accountNumberCustodia){
		tplPrvtAcctMgrtMovDAO = new TplPrvtAcctMgrtMovDAO();
		return tplPrvtAcctMgrtMovDAO.hasRegisterAccountMgrt(accountNumberCitiBank, accountNumberCustodia);
	}
	
		
	public void saveForAprove(AcctMgrtVO vo, String recStatCode) throws Exception{
		
		UserTransaction transaction = getUserTransaction();
		
		Date lastUpdate = new Date();
		
		
		if(vo!=null){
			beginTransaction(transaction);
			
			try {
				
				vo.setLastUpdDate(lastUpdate);
				vo.setRecStatCode(recStatCode);
				
				tplPrvtAcctMgrtMovDAO = new TplPrvtAcctMgrtMovDAO();
				tplPrvtAcctMgrtMovDAO.insert(vo);

				commitTransaction( transaction );	
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}	
		}		
	}
	
	public void updateMov(AcctMgrtVO vo) throws Exception{
		
		UserTransaction transaction = getUserTransaction();		
		
		Date lastUpdate = new Date();
		String recStatCode = "A";
		
		if(vo!=null){
			beginTransaction(transaction);
			
			try {
				
				vo.setLastUpdDate(lastUpdate);
				vo.setRecStatCode(recStatCode);
				
				tplPrvtAcctMgrtMovDAO = new TplPrvtAcctMgrtMovDAO();
				//tplPrvtAcctMgrtMovDAO.update(vo);
				
				commitTransaction( transaction );
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}
		}		
	}
	

	
	public void approve(AccountMigrationDataForm accountMigrationDataForm, Date now) throws Exception {
		
		accountMigrationDataForm.clearErrors();

		if (!accountMigrationDataForm.hasErrors()) {
			UserTransaction transaction = getUserTransaction();
			try {
				
				beginTransaction(transaction);
							
				tplPrvtAcctMgrtMovDAO = new TplPrvtAcctMgrtMovDAO();	

				tplPrvtAcctMgrtDAO = new TplPrvtAcctMgrtDAO();							
			
				if(accountMigrationDataForm.getLoggedUser()!=null){
					accountMigrationDataForm.getSelectRegisterAccountMigrate().setLastAuthUser(accountMigrationDataForm.getLoggedUser().getUserID());
				}
				if(now!=null){
					accountMigrationDataForm.getSelectRegisterAccountMigrate().setLastAuthDate(now);
				}
				
				if(accountMigrationDataForm.getSelectRegisterAccountMigrate().getRecStatCode().equals("I")){
					
					tplPrvtAcctMgrtDAO.delete(accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountGrbNumber(),
							accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountCustodiaNumber(),
							accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountCustodiaCpfCnpjNumber());
					tplPrvtAcctMgrtMovDAO.delete(accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountGrbNumber(),
							accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountCustodiaNumber(),
							accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountCustodiaCpfCnpjNumber());
					
				}else if(accountMigrationDataForm.getSelectRegisterAccountMigrate().getRecStatCode().equals("A")){
					
					tplPrvtAcctMgrtDAO.insert(accountMigrationDataForm.getSelectRegisterAccountMigrate());
					tplPrvtAcctMgrtMovDAO.delete(accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountGrbNumber(),
							accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountCustodiaNumber(),
							accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountCustodiaCpfCnpjNumber());
					
				}else if(accountMigrationDataForm.getSelectRegisterAccountMigrate().getRecStatCode().equals("U")){
					
					
					tplPrvtAcctMgrtDAO.update(accountMigrationDataForm.getSelectRegisterAccountMigrate());
					tplPrvtAcctMgrtMovDAO.delete(accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountGrbNumber(),
							accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountCustodiaNumber(),
							accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountCustodiaCpfCnpjNumber());
					
				}		
					commitTransaction( transaction );
				
				accountMigrationDataForm.addMessage(AccountMigrationDataForm.C_MESSAGE_SUCESS);
				accountMigrationDataForm.setOnlyView(true);
				accountMigrationDataForm.setFromApprove(true);
			} catch (Exception e) {
					rollbackTransaction(transaction);
				throw e;
			}
		}
	}
	
	public void reprove(AccountMigrationDataForm accountMigrationDataForm) throws Exception {

		if (!accountMigrationDataForm.hasErrors()) {
				UserTransaction transaction = getUserTransaction();
			try {					
				beginTransaction(transaction);
				
				tplPrvtAcctMgrtMovDAO = new TplPrvtAcctMgrtMovDAO();		
				tplPrvtAcctMgrtMovDAO.delete(accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountGrbNumber(),
						accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountCustodiaNumber(),
						accountMigrationDataForm.getSelectRegisterAccountMigrate().getAccountCustodiaCpfCnpjNumber());

				
				commitTransaction( transaction );
				
				accountMigrationDataForm.addMessage(AccountMigrationDataForm.C_MESSAGE_SUCESS);
				accountMigrationDataForm.setOnlyView(true);
				accountMigrationDataForm.setFromApprove(true);
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}
		}
	}
	
	

}
