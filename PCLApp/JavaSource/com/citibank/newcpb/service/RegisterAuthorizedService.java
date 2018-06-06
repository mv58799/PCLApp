package com.citibank.newcpb.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.transaction.UserTransaction;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.form.RegisterAuthorizedForm;
import com.citibank.newcpb.persistence.dao.TplPrvtAutPrsnAcctDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtAutPrsnAcctMovDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtAuthPersnDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtAuthPersnMovDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtCustomerDAO;
import com.citibank.newcpb.vo.AcctCmplVO;
import com.citibank.newcpb.vo.AuthorizationPersonAccountVO;
import com.citibank.newcpb.vo.AuthorizationPersonVO;
import com.citibank.newcpb.vo.RegisterDataCustomerVO;

public class RegisterAuthorizedService extends CommonService{
	
	private TplPrvtAuthPersnDAO dao;
	private TplPrvtAutPrsnAcctDAO acctDao;
	private TplPrvtAuthPersnMovDAO movDao;
	private TplPrvtAutPrsnAcctMovDAO acctMovDao;
	private TplPrvtCustomerDAO custDao;
	

	public RegisterAuthorizedService() {
		dao = new TplPrvtAuthPersnDAO();
		acctDao = new TplPrvtAutPrsnAcctDAO();
		movDao = new TplPrvtAuthPersnMovDAO();
		acctMovDao = new TplPrvtAutPrsnAcctMovDAO();
		custDao = new TplPrvtCustomerDAO();
	}

	public ArrayList<AuthorizationPersonVO> listCad(String filterNumberEM, String filterCpfCnpj, String filterName, boolean isLike) throws ParseException {

		ArrayList<AuthorizationPersonVO> resultList = dao.list(filterNumberEM, filterCpfCnpj, filterName, isLike);
		
		if(resultList!=null && !resultList.isEmpty()){
			for (AuthorizationPersonVO authorizationPersonVO : resultList) {				
				ArrayList<AuthorizationPersonAccountVO> accountList = acctDao.list(authorizationPersonVO.getEmNbr(), null, false);
				
				if(accountList!=null && !accountList.isEmpty()){
					for (AuthorizationPersonAccountVO acctVo : accountList) {
						ArrayList<RegisterDataCustomerVO> customerList = custDao.listByAccountNumber(acctVo.getAcctNbr());
						if(customerList!=null && !customerList.isEmpty()){																			
							ArrayList<AcctCmplVO> acctCmplVOList = new ArrayList<AcctCmplVO>();
							 for (RegisterDataCustomerVO customerVO : customerList) {
								 AcctCmplVO acctCmplVO = new AcctCmplVO();
								 acctCmplVO.setAcctNbr(acctVo.getAcctNbr());
								 acctCmplVO.setCustomerName(customerVO.getName());
								 acctCmplVO.setEmNbr(customerVO.getNumberEM());
								 acctCmplVOList.add(acctCmplVO);
							 }	
							 acctVo.setCustomerAcctList(acctCmplVOList);
						}
						if(!StringUtils.isBlank(acctVo.getRecStatCode()) && !acctVo.getRecStatCode().trim().equalsIgnoreCase("I")){
							authorizationPersonVO.getAccountList().add(acctVo);
						}else{
							authorizationPersonVO.getAccountListRemoved().add(acctVo);
						}
					}
				}							
			}
		}
		return resultList;

	}
	
	public ArrayList<AuthorizationPersonVO> listMov(String filterNumberEM, String filterCpfCnpj, String filterName, boolean isLike) throws ParseException {

		ArrayList<AuthorizationPersonVO> resultList = movDao.list(filterNumberEM, filterCpfCnpj, filterName, isLike);
		
		if(resultList!=null && !resultList.isEmpty()){
			for (AuthorizationPersonVO authorizationPersonVO : resultList) {			
				ArrayList<AuthorizationPersonAccountVO> accountList = acctMovDao.list(authorizationPersonVO.getEmNbr(), null, false);
				
				if(accountList!=null && !accountList.isEmpty()){
					for (AuthorizationPersonAccountVO acctVo : accountList) {
						ArrayList<RegisterDataCustomerVO> customerList = custDao.listByAccountNumber(acctVo.getAcctNbr());
						if(customerList!=null && !customerList.isEmpty()){																			
							ArrayList<AcctCmplVO> acctCmplVOList = new ArrayList<AcctCmplVO>();
							 for (RegisterDataCustomerVO customerVO : customerList) {
								 AcctCmplVO acctCmplVO = new AcctCmplVO();
								 acctCmplVO.setAcctNbr(acctVo.getAcctNbr());
								 acctCmplVO.setCustomerName(customerVO.getName());
								 acctCmplVO.setEmNbr(customerVO.getNumberEM());
								 acctCmplVOList.add(acctCmplVO);
							 }	
							 acctVo.setCustomerAcctList(acctCmplVOList);
						}
						
						if(!StringUtils.isBlank(acctVo.getRecStatCode()) && !acctVo.getRecStatCode().trim().equalsIgnoreCase("I")){
							authorizationPersonVO.getAccountList().add(acctVo);
						}else{
							authorizationPersonVO.getAccountListRemoved().add(acctVo);
						}
					}
				}							
			}
		}
		return resultList;

	}
	
	public void approve(RegisterAuthorizedForm form, Date now) throws Exception {
				
		form.clearErrors();

		if (!form.hasErrors()) {
			
			UserTransaction transaction = getUserTransaction();
			try {
				
				beginTransaction(transaction);
				
				AuthorizationPersonVO vo = form.getSelectRegister();

				// 1 - Realiza a atualização do registro na tabela de cadastro				
				
				vo.setLastAuthUser(form.getLoggedUser().getUserID());
				vo.setLastAuthDate(now);
				if("A".equals(vo.getRecStatCode())){
					dao.insert(vo);
				}else if("U".equals(vo.getRecStatCode())){
					vo.setRecStatCode("A");
					dao.update(vo);
				}else if("I".equals(vo.getRecStatCode())){
					dao.delete(vo.getEmNbr());
				}
				
				// 2 - Remove movimento	
				
				movDao.delete(vo.getEmNbr());
				acctMovDao.delete(vo.getEmNbr());
				acctDao.delete(vo.getEmNbr());
				
				
				if(vo.getAccountList()!=null && !vo.getAccountList().isEmpty()){
					for (AuthorizationPersonAccountVO acctVo : vo.getAccountList()) {	
						acctVo.setLastAuthUser(form.getLoggedUser().getUserID());
						acctVo.setLastAuthDate(now);	
						acctVo.setRecStatCode(vo.getRecStatCode());						
						if(vo.getRecStatCode()!=null && !vo.getRecStatCode().equals("I")){
							acctDao.insert(acctVo);
						}						
						
					}
				}
				
				commitTransaction( transaction );
				
				form.addMessage(RegisterAuthorizedForm.C_MESSAGE_SUCESS);
				form.setOnlyView(true);
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}
		}
	}

	public void reprove(RegisterAuthorizedForm form) throws Exception {

		if (!form.hasErrors()) {
		UserTransaction transaction = getUserTransaction();
			try {					
				beginTransaction(transaction);
					
				AuthorizationPersonVO vo = form.getSelectRegister();	
				
				// 2 - Remove movimento					
				movDao.delete(vo.getEmNbr());
				acctMovDao.delete(vo.getEmNbr());
				
				commitTransaction( transaction );
				
				form.addMessage(RegisterAuthorizedForm.C_MESSAGE_SUCESS);
				form.setOnlyView(true);
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}
		}
	}
	
	public void saveForAprove(AuthorizationPersonVO vo) throws Exception{
		
		UserTransaction transaction = getUserTransaction();
		
		if(vo!=null){
			beginTransaction(transaction);			
			try {
				
				movDao.insert(vo);
				acctMovDao.delete(vo.getEmNbr());
				if(vo.getAccountList()!=null && !vo.getAccountList().isEmpty()){
					for (AuthorizationPersonAccountVO accVo : vo.getAccountList()) {
						TplPrvtAutPrsnAcctMovDAO acctDao = new TplPrvtAutPrsnAcctMovDAO();
						accVo.setEmNbr(vo.getEmNbr());
						acctDao.insert(accVo);
					}
				}
				
				commitTransaction( transaction );	
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}	
		}		
	}
	
	public void updateMov(AuthorizationPersonVO vo) throws Exception{
		
		UserTransaction transaction = getUserTransaction();
		
		if(vo!=null){
			beginTransaction(transaction);			
			try {
				
				movDao.update(vo);
				acctMovDao.delete(vo.getEmNbr());
				if(vo.getAccountList()!=null && !vo.getAccountList().isEmpty()){
					for (AuthorizationPersonAccountVO accVo : vo.getAccountList()) {
						TplPrvtAutPrsnAcctMovDAO acctDao = new TplPrvtAutPrsnAcctMovDAO();
						accVo.setEmNbr(vo.getEmNbr());						
						acctDao.insert(accVo);
					}
				}
							
				commitTransaction( transaction );	
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}	
		}		
	}
	
	public void updateCad(AuthorizationPersonVO vo) throws Exception{
		
		UserTransaction transaction = getUserTransaction();
		
		if(vo!=null){
			beginTransaction(transaction);			
			try {
				
				dao.update(vo);
				acctDao.delete(vo.getEmNbr());
				
				if(vo.getAccountList()!=null && !vo.getAccountList().isEmpty()){
					for (AuthorizationPersonAccountVO accVo : vo.getAccountList()) {
						TplPrvtAutPrsnAcctMovDAO acctDao = new TplPrvtAutPrsnAcctMovDAO();
						accVo.setEmNbr(vo.getEmNbr());					
						acctDao.insert(accVo);
					}
				}
				
				commitTransaction( transaction );	
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}	
		}		
	}
	
	public ArrayList<String> getFieldsWithDifference(AuthorizationPersonVO mov) throws ParseException {
		
		ArrayList<String> idDiffList = new ArrayList<String>();
		
		if(mov!=null && !StringUtils.isBlank(mov.getEmNbr())){
			
			ArrayList<AuthorizationPersonVO> listCad = listCad(mov.getEmNbr(), null, null, false);
			
			if(listCad!=null && !listCad.isEmpty() 
					&& !StringUtils.isBlank(listCad.get(0).getEmNbr())){
				
				idDiffList = listCad.get(0).equals(mov);
				
			}
		}
		
		return idDiffList;
	}
	
	public void validateExistAccount(RegisterAuthorizedForm form){
		
		if(form.getSelectRegister().getAccountList()==null){
			form.getSelectRegister().setAccountList(new ArrayList<AuthorizationPersonAccountVO>());
		}
		
		if(!StringUtils.isBlank(form.getFilterAccountList())){
			String[] accountList = form.getFilterAccountList().split(",");
			
			if(accountList!=null && accountList.length != 0){
				String accountErroList = "";
				for (String account : accountList) {
					
					boolean containsAcct = false;
					for (int i = 0; i < form.getSelectRegister().getAccountList().size(); i++) {
						if((account.equalsIgnoreCase(form.getSelectRegister().getAccountList().get(i).getAcctNbr()))
								&& (form.getSelectRegister().getAuthInd().equalsIgnoreCase(form.getSelectRegister().getAccountList().get(i).getAuthInd()))){
							containsAcct = true;
							break;
						}
					}
										
					if(!containsAcct){
						ArrayList<RegisterDataCustomerVO> customerList = custDao.listByAccountNumber(account);
						if(customerList==null || customerList.size()==0){
							if(!StringUtils.isBlank(accountErroList)){
								accountErroList += ", " + account;
							}else{
								accountErroList += account;
							}
							
						}else{
							form.setFilterAccountList("");
							AuthorizationPersonAccountVO accVO = new AuthorizationPersonAccountVO();
							accVO.setAcctNbr(account);
							accVO.setAuthInd(form.getSelectRegister().getAuthInd());
							accVO.setAuthComment(form.getSelectRegister().getComments());
							accVO.setEffectiveDate(form.getSelectRegister().getEffectiveDate());
							form.getSelectRegister().getAccountList().add(accVO);	
							
							if(customerList!=null && !customerList.isEmpty()){
								ArrayList<AcctCmplVO> acctCmplVOList = new ArrayList<AcctCmplVO>();
								for (RegisterDataCustomerVO customerVO : customerList) {
									AcctCmplVO acctCmplVO = new AcctCmplVO();
									acctCmplVO.setAcctNbr(account);
									acctCmplVO.setCustomerName(customerVO.getName());
									acctCmplVO.setEmNbr(customerVO.getNumberEM());			 
									acctCmplVOList.add(acctCmplVO);
								}								
								 accVO.setCustomerAcctList(acctCmplVOList);
							}else{
								if(!StringUtils.isBlank(accountErroList)){
									accountErroList += ", " + account;
								}else{
									accountErroList += account;
								}
							}
						}
					}else{
						form.addError(RegisterAuthorizedForm.C_ERROR_AUTH_ACCOUNT_NUMBER_ALREADY, account);						
					}
					if(!StringUtils.isBlank(accountErroList)){
						form.addError(RegisterAuthorizedForm.C_ERROR_AUTH_ACCOUNT_NUMBER_INVALID_LIST, accountErroList);
					}				
				}
			}else{
				form.addError(RegisterAuthorizedForm.C_ERROR_AUTH_EMPTY_ACCOUNT_NUMBER_OUT);
			}
		}else{
			form.addError(RegisterAuthorizedForm.C_ERROR_AUTH_EMPTY_ACCOUNT_NUMBER_OUT);
		}
	}
}
