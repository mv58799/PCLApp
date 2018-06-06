package com.citibank.newcpb.service;

import java.text.ParseException;
import java.util.ArrayList;


import javax.transaction.UserTransaction;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.form.StatusCpfCnpjForm;
import com.citibank.newcpb.persistence.dao.TplPrvtAuthPersnDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtCpfStatDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtCpfStatMovDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtCustomerDAO;
import com.citibank.newcpb.vo.AuthorizationPersonVO;
import com.citibank.newcpb.vo.RegisterDataCustomerVO;
import com.citibank.newcpb.vo.StatusCpfCnpjVO;

public class StatusCpfCnpjService extends CommonService{
	
	private TplPrvtCpfStatDAO cadDAO;
	private TplPrvtCpfStatMovDAO movDAO;
	private TplPrvtCustomerDAO custCadDAO;
	private TplPrvtAuthPersnDAO authCadDAO;

	public StatusCpfCnpjService() {
		cadDAO = new TplPrvtCpfStatDAO();
		movDAO = new TplPrvtCpfStatMovDAO();
		custCadDAO = new TplPrvtCustomerDAO();
		authCadDAO = new TplPrvtAuthPersnDAO();
	}

	public ArrayList<StatusCpfCnpjVO> listCad(String filterNumberEM, String filterCpfCnpj, String filterName, 
			String filterStatus, String filterMonthYear, String filterCpfCnpjList, boolean isLike) throws ParseException {

		ArrayList<StatusCpfCnpjVO> resultList = cadDAO.list(filterNumberEM, filterCpfCnpj, filterName, filterStatus, filterMonthYear, filterCpfCnpjList, isLike);		
		
		if(resultList!=null && !resultList.isEmpty()){
			for (StatusCpfCnpjVO vo : resultList) {
				ArrayList<RegisterDataCustomerVO> custList = custCadDAO.searchSimpleCustomerListByCpfCnpj(vo.getCpfCnpjNbr());
				if(custList!=null && !custList.isEmpty()){
					RegisterDataCustomerVO custVo = new RegisterDataCustomerVO();
					for (RegisterDataCustomerVO registerDataCustomerVO : custList) {
						if(!StringUtils.isBlank(custVo.getName())){
							custVo.setName(custVo.getName() + "<p>");
						}else{
							custVo.setName("");
						}
						if(!StringUtils.isBlank(custVo.getNumberEM())){
							custVo.setNumberEM(custVo.getNumberEM() + "<p>");
						}else{
							custVo.setNumberEM("");
						}
						custVo.setName(custVo.getName() + registerDataCustomerVO.getName());
						custVo.setNumberEM(custVo.getNumberEM() + registerDataCustomerVO.getNumberEM());
					}
					vo.setCustomer(custVo);
				}else{
					ArrayList<AuthorizationPersonVO> authList = authCadDAO.list(null, vo.getCpfCnpjNbr(), null, false);
					if(authList!=null && !authList.isEmpty()){
						RegisterDataCustomerVO custVo = new RegisterDataCustomerVO();
						for (AuthorizationPersonVO authorizationPersonVO : authList) {
							if(!StringUtils.isBlank(custVo.getName())){
								custVo.setName(custVo.getName() + "<p>");
							}else{
								custVo.setName("");
							}
							if(!StringUtils.isBlank(custVo.getNumberEM())){
								custVo.setNumberEM(custVo.getNumberEM() + "<p>");
							}else{
								custVo.setNumberEM("");
							}
							custVo.setName(custVo.getName() + authorizationPersonVO.getAuthPersnName());
							custVo.setNumberEM(custVo.getNumberEM() + authorizationPersonVO.getEmNbr());
						}
						vo.setCustomer(custVo);
					}
				}
			}
		}
		
		return resultList;

	}
	
	public ArrayList<StatusCpfCnpjVO> listMov(String filterNumberEM, String filterCpfCnpj, String filterName, 
			String filterStatus, String filterMonthYear, String filterCpfCnpjList, boolean isLike) throws ParseException {

		ArrayList<StatusCpfCnpjVO> resultList = movDAO.list(filterNumberEM, filterCpfCnpj, filterName, filterStatus, filterMonthYear, filterCpfCnpjList, isLike);		
		
		if(resultList!=null && !resultList.isEmpty()){
			for (StatusCpfCnpjVO vo : resultList) {
				ArrayList<RegisterDataCustomerVO> custList = custCadDAO.searchCustomerListByCpfCnpj(vo.getCpfCnpjNbr());
				if(custList!=null && !custList.isEmpty()){
					RegisterDataCustomerVO custVo = new RegisterDataCustomerVO();
					for (RegisterDataCustomerVO registerDataCustomerVO : custList) {
						if(!StringUtils.isBlank(custVo.getName())){
							custVo.setName(custVo.getName() + "<p>");
						}
						if(!StringUtils.isBlank(custVo.getNumberEM())){
							custVo.setNumberEM(custVo.getNumberEM() + "<p>");
						}
						custVo.setName(custVo.getName() + registerDataCustomerVO.getName());
						custVo.setNumberEM(custVo.getNumberEM() + registerDataCustomerVO.getNumberEM());
					}
					vo.setCustomer(custVo);
				}else{
					ArrayList<AuthorizationPersonVO> authList = authCadDAO.list(null, vo.getCpfCnpjNbr(), null, false);
					if(authList!=null && !authList.isEmpty()){
						RegisterDataCustomerVO custVo = new RegisterDataCustomerVO();
						for (AuthorizationPersonVO authorizationPersonVO : authList) {
							if(!StringUtils.isBlank(custVo.getName())){
								custVo.setName(custVo.getName() + "<p>");
							}else{
								custVo.setName("");
							}
							if(!StringUtils.isBlank(custVo.getNumberEM())){
								custVo.setNumberEM(custVo.getNumberEM() + "<p>");
							}else{
								custVo.setNumberEM("");
							}
							custVo.setName(custVo.getName() + authorizationPersonVO.getAuthPersnName());
							custVo.setNumberEM(custVo.getNumberEM() + authorizationPersonVO.getEmNbr());
						}
						vo.setCustomer(custVo);
					}
				}				
			}
		}
		
		return resultList;

	}

	public void approve(StatusCpfCnpjVO vo) throws Exception {
		
		UserTransaction transaction = getUserTransaction();
		try {
			
			beginTransaction(transaction);
			cadDAO.delete(vo.getCpfCnpjNbr());			
			cadDAO.insert(vo);	
			movDAO.delete(vo.getCpfCnpjNbr());
			
			commitTransaction( transaction );
		} catch (Exception e) {
			rollbackTransaction(transaction);
			throw e;
		}
	}

	public void reprove(StatusCpfCnpjForm form) throws Exception {

		if (!form.hasErrors()) {
		UserTransaction transaction = getUserTransaction();
			try {					
				beginTransaction(transaction);
					
				StatusCpfCnpjVO vo = form.getSelectedRegister();	
				
				// 2 - Remove movimento					
				movDAO.deleteByMonthYear(vo.getCpfUpdMthYr());
				
				commitTransaction( transaction );
				
				form.addMessage(StatusCpfCnpjForm.C_MESSAGE_SUCESS);
				form.setOnlyView(true);
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}
		}
	}
	
	public void saveForAprove(StatusCpfCnpjVO vo) throws Exception{
		
		UserTransaction transaction = getUserTransaction();
		
		if(vo!=null){
			beginTransaction(transaction);			
			try {
				movDAO.delete(vo.getCpfCnpjNbr());
				movDAO.insert(vo);
				
				commitTransaction( transaction );	
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}	
		}		
	}
	
	public void updateMov(StatusCpfCnpjVO vo) throws Exception{
		
		UserTransaction transaction = getUserTransaction();
		
		if(vo!=null){
			beginTransaction(transaction);			
			try {
				movDAO.delete(vo.getCpfCnpjNbr());
				movDAO.insert(vo);
				
				commitTransaction( transaction );	
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}	
		}		
	}
	
	public void updateCad(StatusCpfCnpjVO vo) throws Exception{
		
		UserTransaction transaction = getUserTransaction();
		
		if(vo!=null){
			beginTransaction(transaction);			
			try {
				cadDAO.delete(vo.getCpfCnpjNbr());
				cadDAO.insert(vo);
				
				commitTransaction( transaction );	
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}	
		}		
	}
	
	public ArrayList<String> getFieldsWithDifference(StatusCpfCnpjVO mov) throws ParseException {
		
		ArrayList<String> idDiffList = new ArrayList<String>();
		
		if(mov!=null && !StringUtils.isBlank(mov.getCpfCnpjNbr())){
			
			ArrayList<StatusCpfCnpjVO> listCad = listCad(null, mov.getCpfCnpjNbr(), null, null, null, null, false);
			
			if(listCad!=null && !listCad.isEmpty() 
					&& !StringUtils.isBlank(listCad.get(0).getCpfCnpjNbr())){
				
				idDiffList = listCad.get(0).equals(mov);
				
			}
		}
		
		return idDiffList;
	}
	
	public void deleteCadByMonthYear(String monthYear) throws Exception{
		
		UserTransaction transaction = getUserTransaction();
		
		if(!StringUtils.isBlank(monthYear)){
			beginTransaction(transaction);			
			try {
				
				cadDAO.deleteByMonthYear(monthYear);
				
				commitTransaction( transaction );	
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}	
		}		
	}
	
	public void deleteMovByMonthYear(String monthYear) throws Exception{
		
		UserTransaction transaction = getUserTransaction();
		
		if(!StringUtils.isBlank(monthYear)){
			beginTransaction(transaction);			
			try {
				
				movDAO.deleteByMonthYear(monthYear);
				
				commitTransaction( transaction );	
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}	
		}		
	}
	
	public boolean validaExistCpfCnpj(String cpfCnpj) throws ParseException {
		
		boolean exist = false;

		TplPrvtCustomerDAO dao = new TplPrvtCustomerDAO();
		ArrayList<RegisterDataCustomerVO> resultList = dao.searchCustomerListByCpfCnpj(cpfCnpj);
		
		TplPrvtAuthPersnDAO authDao = new TplPrvtAuthPersnDAO();
		ArrayList<AuthorizationPersonVO> resultList2 = authDao.list(null, cpfCnpj, null, false);
		
		if((resultList!=null && !resultList.isEmpty()) || (resultList2!=null && !resultList2.isEmpty())){
			exist = true;
		}

		return exist;

	}
}
