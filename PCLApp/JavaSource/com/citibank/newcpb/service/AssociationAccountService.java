package com.citibank.newcpb.service;

import java.text.ParseException;
import java.util.ArrayList;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.form.AssociationAccountForm;
import com.citibank.newcpb.persistence.dao.TplPrvtAcctEgDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtAcctEgMovDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtCustomerDAO;
import com.citibank.newcpb.vo.AcctEgVO;
import com.citibank.newcpb.vo.RegisterDataCustomerVO;

public class AssociationAccountService extends CommonService{
	
	
	private TplPrvtCustomerDAO tplPrvtCustomerDAO;
	
	
	public void validateSessionInclude(AssociationAccountForm form){
		
		if(form.getErrors()!=null && form.getErrors().size()==0){
			
			if(form.getAccountAssociationList()!=null){
				
				for(AcctEgVO acctEgVO : form.getAccountAssociationList()){
					
					if(!(form.getAcctEgVO().getEgNbr() + form.getAcctEgVO().getErNbr()).equals(acctEgVO.getEgNbr() + acctEgVO.getErNbr())){
						form.addError(AssociationAccountForm.C_ERROR_ONLY_SAME_EG_ASSOCIATION);
						break;
					}	
				}	
				
				for(AcctEgVO acctEgVO : form.getAccountAssociationList()){
					
					if(form.getAcctEgVO().getAcctNbr().equals(acctEgVO.getAcctNbr())){
						form.addError(AssociationAccountForm.C_ERROR_NUMBER_ACCT_ALREADY_ASSOCIATION_IN_SESSION);
						break;
					}	
				}
			}	
		}
	}
	
	public AcctEgVO setFullNameCustomerAccount(AcctEgVO acctEgVO){
		
		tplPrvtCustomerDAO = new TplPrvtCustomerDAO();
		ArrayList<RegisterDataCustomerVO> list  = tplPrvtCustomerDAO.listByAccountNumber(acctEgVO.getAcctNbr());
		if(list!=null && list.size()>0){	
			acctEgVO.setCustomerName(list.get(0).getName());
		}else{
			acctEgVO.setCustomerName("Não Cadastrado");
		}
		
		return acctEgVO;
	}
	
	
	
	public ArrayList<AcctEgVO> listByFilter(AcctEgVO filter, TableTypeEnum tabletype, Boolean notInative) {

		ArrayList<AcctEgVO> resultList = null;
		if(tabletype!=null && tabletype.equals(TableTypeEnum.EFFECTIVE)){
			TplPrvtAcctEgDAO dao = new TplPrvtAcctEgDAO();
			resultList = dao.listByFilter(filter, notInative);
		}else if(tabletype!=null && tabletype.equals(TableTypeEnum.MOVEMENT)){
			TplPrvtAcctEgMovDAO dao = new TplPrvtAcctEgMovDAO();
			resultList = dao.listByFilter(filter, notInative);
		}
		return resultList;

	}
	
	public ArrayList<RegisterDataCustomerVO> searchCustomerListByER(String numberER) throws ParseException {

		TplPrvtCustomerDAO dao = new TplPrvtCustomerDAO();
		ArrayList<RegisterDataCustomerVO> resultList = dao.searchCustomerListByER(numberER);

		return resultList;

	}
	
	public ArrayList<AcctEgVO> listByFilterWithLike(AcctEgVO filter, TableTypeEnum tabletype) {

		ArrayList<AcctEgVO> resultList = null;
		if(tabletype!=null && tabletype.equals(TableTypeEnum.EFFECTIVE)){
			TplPrvtAcctEgDAO dao = new TplPrvtAcctEgDAO();
			resultList = dao.listByFilterWithLike(filter);
		}else if(tabletype!=null && tabletype.equals(TableTypeEnum.MOVEMENT)){
			
		}
		return resultList;

	}
	
}
