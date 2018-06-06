package com.citibank.newcpb.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.transaction.UserTransaction;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.form.AccountComplementDataForm;
import com.citibank.newcpb.form.CentralApprovalForm;
import com.citibank.newcpb.persistence.dao.TplPrvtAcctCmplDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtAcctCmplMovDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtAcctEgDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtAcctEgMovDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtCustomerDAO;
import com.citibank.newcpb.util.FormatUtils;
import com.citibank.newcpb.vo.AcctCmplVO;
import com.citibank.newcpb.vo.AcctEgVO;
import com.citibank.newcpb.vo.RegisterDataCustomerVO;


public class AccountComplementDataService extends CommonService {
	
	private TplPrvtAcctCmplDAO tplPrvtAcctCmplDAO;
	private TplPrvtAcctCmplMovDAO tplPrvtAcctCmplMovDAO;
	private TplPrvtAcctEgDAO tplPrvtAcctEgDAO;
	private TplPrvtAcctEgMovDAO tplPrvtAcctEgMovDAO;
	
	public ArrayList<AcctCmplVO> list(AccountComplementDataForm form) {

		TplPrvtAcctCmplDAO dao = new TplPrvtAcctCmplDAO();
		ArrayList<AcctCmplVO> resultList = dao.listByFilter(form.getFilterNumberAccount(), form.getFilterNumberCpfCnpj(), form.getFilterAccountType());
		return resultList;
	}
	
	
	public AcctCmplVO getAccountComplement(String acctNbr, String cpfCnpjNbr, String accountType) throws ParseException{
		tplPrvtAcctCmplDAO = new TplPrvtAcctCmplDAO();
		AcctCmplVO acctCmplVO = tplPrvtAcctCmplDAO.getAccountComplement(acctNbr, cpfCnpjNbr, accountType);
		
		if(acctCmplVO!=null && !StringUtils.isBlank(acctCmplVO.getCpfCnpjNbr())){
			acctCmplVO.setCpfCnpjNbr(FormatUtils.formatterDoc(acctCmplVO.getCpfCnpjNbr()));
		}
		
		//Busca EG associado a conta
		tplPrvtAcctEgDAO = new TplPrvtAcctEgDAO();
		AcctEgVO acctEgVO  = tplPrvtAcctEgDAO.getAccountEg(acctNbr,true);
		if (acctEgVO != null) {
			acctCmplVO.setErNbr(acctEgVO.getErNbr());
			acctCmplVO.setEgNbr(acctEgVO.getEgNbr());
		}
		return acctCmplVO;
	}
	
	public AcctCmplVO getAccountComplementMov(String acctNbr, String cpfCnpjNbr, String accountType) throws ParseException{
		tplPrvtAcctCmplMovDAO = new TplPrvtAcctCmplMovDAO();
		AcctCmplVO acctCmplVO = tplPrvtAcctCmplMovDAO.getAccountComplement(acctNbr, cpfCnpjNbr, accountType);
		
		if(acctCmplVO!=null && !StringUtils.isBlank(acctCmplVO.getCpfCnpjNbr())){
			acctCmplVO.setCpfCnpjNbr(FormatUtils.formatterDoc(acctCmplVO.getCpfCnpjNbr()));
		}
		
		//Busca EG associado a conta
		tplPrvtAcctEgMovDAO = new TplPrvtAcctEgMovDAO();
		AcctEgVO acctEgVO  = tplPrvtAcctEgMovDAO.getAccountEg(acctNbr);
		if (acctEgVO != null) {			
			acctCmplVO.setErNbr(acctEgVO.getErNbr());
			acctCmplVO.setEgNbr(acctEgVO.getEgNbr());
		}
		
		return acctCmplVO;
	}
	
	
	public Boolean hasRegisterMov(String acctNbr, String cpfCnpjNbr, String accountType){
		tplPrvtAcctCmplMovDAO = new TplPrvtAcctCmplMovDAO();
		return tplPrvtAcctCmplMovDAO.hasRegisterAccountComplement(acctNbr, cpfCnpjNbr, accountType);
	}
	
	public void saveForAprove(AcctCmplVO vo) throws Exception{
		
		UserTransaction transaction = getUserTransaction();
		
		Date lastUpdate = new Date();
		String recStatCode = "A";
		
		if(vo!=null){
			beginTransaction(transaction);
			
			try {
				
				vo.setLastUpdDate(lastUpdate);
				vo.setRecStatCode(recStatCode);
				
				tplPrvtAcctCmplMovDAO = new TplPrvtAcctCmplMovDAO();
				tplPrvtAcctCmplMovDAO.insert(vo);
				
				tplPrvtAcctEgMovDAO = new TplPrvtAcctEgMovDAO();
				tplPrvtAcctEgMovDAO.delete(vo.getAcctNbr());
				
				if (vo.getEgNbr() != null && !StringUtils.isBlank(vo.getEgNbr())){
					AcctEgVO egVo = new AcctEgVO(); 
					egVo.setRecStatCode(recStatCode);
					egVo.setLastUpdDate(lastUpdate);
					egVo.setLastUpdUser(vo.getLastUpdUser());
					egVo.setAcctNbr(vo.getAcctNbr());
					egVo.setErNbr(vo.getErNbr());
					egVo.setEgNbr(vo.getEgNbr());
					tplPrvtAcctEgMovDAO.insert(egVo);
				}

				commitTransaction( transaction );	
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}	
		}		
	}
	
	public void updateMov(AcctCmplVO vo) throws Exception{
		
		UserTransaction transaction = getUserTransaction();		
		
		Date lastUpdate = new Date();
		String recStatCode = "A";
		
		if(vo!=null){
			beginTransaction(transaction);
			
			try {
				
				vo.setLastUpdDate(lastUpdate);
				vo.setRecStatCode(recStatCode);
				
				tplPrvtAcctCmplMovDAO = new TplPrvtAcctCmplMovDAO();
				tplPrvtAcctCmplMovDAO.update(vo);
				
				tplPrvtAcctEgMovDAO = new TplPrvtAcctEgMovDAO();
				tplPrvtAcctEgMovDAO.delete(vo.getAcctNbr());
				
				if (vo.getEgNbr() != null && !StringUtils.isBlank(vo.getEgNbr())){
					AcctEgVO egVo = new AcctEgVO(); 
					egVo.setRecStatCode(recStatCode);
					egVo.setLastUpdDate(lastUpdate);
					egVo.setLastUpdUser(vo.getLastUpdUser());
					egVo.setAcctNbr(vo.getAcctNbr());
					egVo.setErNbr(vo.getErNbr());
					egVo.setEgNbr(vo.getEgNbr());
					tplPrvtAcctEgMovDAO.insert(egVo);
				}
				
				commitTransaction( transaction );
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}
		}		
	}
	
	public ArrayList<String> getFieldsWithDifference(AcctCmplVO mov) throws ParseException {
		
		ArrayList<String> idDiffList = new ArrayList<String>();
		
		if(mov!=null && !StringUtils.isBlank(mov.getAcctNbr()) && !StringUtils.isBlank(mov.getCpfCnpjNbr())
				&& !StringUtils.isBlank(mov.getAccountType())){
			
			AcctCmplVO cad = getAccountComplement(mov.getAcctNbr(), mov.getCpfCnpjNbr(), mov.getAccountType());
			
			if(cad!=null && !StringUtils.isBlank(cad.getAcctNbr())){
				
				idDiffList = cad.equals(mov);
				
			}
		}
		
		return idDiffList;
	}
	
	
	
	public void approve(AccountComplementDataForm accountComplementDataForm, Date now) throws Exception {
		
		accountComplementDataForm.clearErrors();

		if (!accountComplementDataForm.hasErrors()) {
			UserTransaction transaction = getUserTransaction();
			try {
				
				beginTransaction(transaction);
				
				// 1 - Copia dados da entidade de movimento para a de cadastro					
				tplPrvtAcctCmplMovDAO = new TplPrvtAcctCmplMovDAO();	
				tplPrvtAcctEgMovDAO =  new TplPrvtAcctEgMovDAO();
				

				// 2 - Realiza a atualização do registro na tabela de cadastro
				tplPrvtAcctCmplDAO = new TplPrvtAcctCmplDAO();			
				tplPrvtAcctEgDAO =  new TplPrvtAcctEgDAO();
				AcctCmplVO acctCmplVO = accountComplementDataForm.getSelectRegisterAccountComplement();
				
				if("I".equals(acctCmplVO.getRecStatCode())){
					acctCmplVO.setRecStatCode("U");
				}			
				if(accountComplementDataForm.getLoggedUser()!=null){
					acctCmplVO.setLastAuthUser(accountComplementDataForm.getLoggedUser().getUserID());
				}
				if(now!=null){
					acctCmplVO.setLastAuthDate(now);
				}


				tplPrvtAcctCmplDAO.update(acctCmplVO);					
				tplPrvtAcctCmplMovDAO.delete(acctCmplVO.getAcctNbr(), acctCmplVO.getCpfCnpjNbr(), acctCmplVO.getAccountType());
				
				//deleta de todos egs da conta , pois 1 conta so pode estar associada a 1 EG
				tplPrvtAcctEgDAO.delete(acctCmplVO.getAcctNbr());
				
				//Insere EG x cc 
				if (!StringUtils.isBlank(acctCmplVO.getEgNbr())){
					AcctEgVO egVo = new AcctEgVO(); 
					egVo.setRecStatCode("A");
					egVo.setLastUpdDate(acctCmplVO.getLastUpdDate());
					egVo.setLastUpdUser(acctCmplVO.getLastUpdUser());
					egVo.setLastAuthDate(acctCmplVO.getLastAuthDate());
					egVo.setLastAuthUser(acctCmplVO.getLastAuthUser());
					egVo.setAcctNbr(acctCmplVO.getAcctNbr());					
					egVo.setErNbr(acctCmplVO.getErNbr());
					egVo.setEgNbr(acctCmplVO.getEgNbr());
					tplPrvtAcctEgDAO.insert(egVo);
				}
				//deleta de acct x eg de movimento
				tplPrvtAcctEgMovDAO.delete(acctCmplVO.getAcctNbr());
				
		
				commitTransaction( transaction );
				
				accountComplementDataForm.addMessage(CentralApprovalForm.C_MESSAGE_SUCESS);
				accountComplementDataForm.setOnlyView(true);
				accountComplementDataForm.setFromApprove(true);
			} catch (Exception e) {
					rollbackTransaction(transaction);
				throw e;
			}
		}
	}
	
	public void reprove(AccountComplementDataForm accountComplementDataForm) throws Exception {

		if (!accountComplementDataForm.hasErrors()) {
				UserTransaction transaction = getUserTransaction();
			try {					
				beginTransaction(transaction);
				
				AcctCmplVO vo = accountComplementDataForm.getSelectRegisterAccountComplement();
				tplPrvtAcctCmplMovDAO = new TplPrvtAcctCmplMovDAO();
				tplPrvtAcctEgMovDAO = new TplPrvtAcctEgMovDAO();

				
				tplPrvtAcctCmplMovDAO.delete(vo.getAcctNbr(), vo.getCpfCnpjNbr(),vo.getAccountType());
				tplPrvtAcctEgMovDAO.delete(vo.getAcctNbr());

				
				commitTransaction( transaction );
				
				accountComplementDataForm.addMessage(CentralApprovalForm.C_MESSAGE_SUCESS);
				accountComplementDataForm.setOnlyView(true);
				accountComplementDataForm.setFromApprove(true);
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}
		}
	}
	
	public ArrayList<RegisterDataCustomerVO> searchCustomerListByER(String numberER) throws ParseException {

		TplPrvtCustomerDAO dao = new TplPrvtCustomerDAO();
		ArrayList<RegisterDataCustomerVO> resultList = dao.searchCustomerListByER(numberER);

		return resultList;

	}
	
	

}
