package com.citibank.newcpb.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.form.AccountComplementDataForm;
import com.citibank.newcpb.form.AccountMigrationDataForm;
import com.citibank.newcpb.form.AssociationAccountForm;
import com.citibank.newcpb.form.BankerDataForm;
import com.citibank.newcpb.form.DocumentsForm;
import com.citibank.newcpb.form.RegisterAuthorizedForm;
import com.citibank.newcpb.form.RegisterDataCustomerForm;
import com.citibank.newcpb.form.StatusCpfCnpjForm;
import com.citibank.newcpb.persistence.dao.TplPrvtAuthPersnDAO;
import com.citibank.newcpb.util.FormatUtils;
import com.citibank.newcpb.util.ValidaCpfCnpj;
import com.citibank.newcpb.vo.AcctCmplVO;
import com.citibank.newcpb.vo.AcctMgrtVO;
import com.citibank.newcpb.vo.AuthorizationPersonVO;
import com.citibank.newcpb.vo.EgVO;
import com.citibank.newcpb.vo.KeCustFileVO;
import com.citibank.newcpb.vo.OfficerBankerVO;
import com.citibank.newcpb.vo.RegisterDataCustomerVO;

public class ValidationService {
	
	public List<String> validate(RegisterDataCustomerForm form){
		
		RegisterDataCustomerVO vo = form.getRegisterConsumer();
		List<String> errors = new ArrayList<String>();
		
		if(vo!=null){
			
			//valida Tipo cliente
			if (vo.getCustomerType() == null || "".equals(vo.getCustomerType().trim())) {
				form.addError(RegisterDataCustomerForm.C_ERROR_EMPTY_CUSTOMER_TYPE);
			}
			
			
			
			if(vo.getCustomerType()!=null && vo.getCustomerType().trim().equals("F")){
				
				// Valida CPF
				if (vo.getCpfCnpj() == null || "".equals(vo.getCpfCnpj().trim())) {
					form.addError(RegisterDataCustomerForm.C_ERROR_EMPTY_CUSTOMER_CPF);
					
				}else if(!ValidaCpfCnpj.isValidCPF(vo.getCpfCnpj())){
					form.addError(RegisterDataCustomerForm.C_ERROR_INVALID_CPF_CUSTOMER);
				}
				
				// Valida Data de Nascimento se preenchida
				if (vo.getBirthDate() != null && !"".equals(vo.getBirthDate().trim())) {
					
					if(!FormatUtils.isDateValidate(vo.getBirthDate())){
						form.addError(RegisterDataCustomerForm.C_ERROR_INVALID_DATE_BIRTH);
					}	
				}
				
				
				// Valida Data de Emissão do Doc se preenchida
				if (vo.getEmitDocumentDate() != null && !"".equals(vo.getEmitDocumentDate().trim())) {
					
					if(!FormatUtils.isDateValidate(vo.getEmitDocumentDate())){
						form.addError(RegisterDataCustomerForm.C_ERROR_INVALID_DATE_DOC_EMIT);
					}	
				}
				
				
				
			}else if(vo.getCustomerType()!=null && vo.getCustomerType().trim().equals("J")){
				
				//Valida CNPJ
				if (vo.getCpfCnpj() == null || "".equals(vo.getCpfCnpj().trim())) {
					form.addError(RegisterDataCustomerForm.C_ERROR_EMPTY_CUSTOMER_CNPJ);
				}else if(!ValidaCpfCnpj.isValidCNPJ(vo.getCpfCnpj())){
					form.addError(RegisterDataCustomerForm.C_ERROR_INVALID_CNPJ_CUSTOMER);
				}
				
				
				// Valida Data de Fundação Empresa
				if (vo.getFoundationDate() != null && !"".equals(vo.getFoundationDate().trim())) {
					
					if(!FormatUtils.isDateValidate(vo.getFoundationDate())){
						form.addError(RegisterDataCustomerForm.C_ERROR_INVALID_DATE_FUND);
					}	
				}
				
				
				// Valida CPF do ADM
				if (vo.getAdmCpf() != null && !"".equals(vo.getAdmCpf().trim())) {
					
					if(!ValidaCpfCnpj.isValidCPF(vo.getAdmCpf())){
						form.addError(RegisterDataCustomerForm.C_ERROR_INVALID_CPF_ADM);
					}
				}
				
				
			}
			
			// Valida Data de Assinatura Fatca
	
			if(!StringUtils.isBlank(vo.getSignatureDateFatca())){
				
				if(!FormatUtils.isDateValidate(vo.getSignatureDateFatca())){
					form.addError(RegisterDataCustomerForm.C_ERROR_INVALID_DATE_SIGNATURE_W8);
				}	
			}				
			
			
			// Valida Data de Assinatura Crs
	
			if(!StringUtils.isBlank(vo.getSignatureDateCrs())){
				
				if(!FormatUtils.isDateValidate(vo.getSignatureDateCrs())){
					form.addError(RegisterDataCustomerForm.C_ERROR_INVALID_DATE_SIGNATURE_CRS);
				}	
			}				
			
			
			
			//valida Status cliente
			if (vo.getCustomerStatus() == null || "".equals(vo.getCustomerStatus().trim())) {
				form.addError(RegisterDataCustomerForm.C_ERROR_EMPTY_CUSTOMER_STATUS);
			}
			
			//valida Titulo cliente
			if (vo.getCustomerTitulo() == null || "".equals(vo.getCustomerTitulo().trim())) {
				form.addError(RegisterDataCustomerForm.C_ERROR_EMPTY_CUSTOMER_STATUS);
			}
			
			//valida Nome cliente
			if (vo.getName() == null || "".equals(vo.getName().trim())) {
				form.addError(RegisterDataCustomerForm.C_ERROR_EMPTY_CUSTOMER_NAME);
			}
			
			//valida GFCID cliente
			if (vo.getNumberGFCID() == null || "".equals(vo.getNumberGFCID().trim())) {
				form.addError(RegisterDataCustomerForm.C_ERROR_EMPTY_CUSTOMER_GFCID);
			}
			
			//valida EM cliente
			if (vo.getNumberEM() == null || "".equals(vo.getNumberEM().trim())) {
				form.addError(RegisterDataCustomerForm.C_ERROR_EMPTY_CUSTOMER_EM);
			}
			
			
			if(vo.getEr_em()!=null){
				
				if(!StringUtils.isBlank(vo.getEr_em().getErNbr()) || !StringUtils.isBlank(vo.getEr_em().getRoleCustCode())){
					
					if(StringUtils.isBlank(vo.getEr_em().getErNbr())){
						form.addError(RegisterDataCustomerForm.C_ERROR_EMPTY_CUSTOMER_ER);
					}
					
					if(StringUtils.isBlank(vo.getEr_em().getRoleCustCode())){
						form.addError(RegisterDataCustomerForm.C_ERROR_EMPTY_CUSTOMER_ROLE);
					}
					
				}
				
			}
					
		
		}

		return errors;

	}
	
	public List<String> validate(BankerDataForm form){
		
		OfficerBankerVO vo = form.getSelectRegisterOfficerBanker();
		List<String> errors = new ArrayList<String>();
		
		if(vo!=null){
			
			
			if (StringUtils.isBlank(vo.getEmployeeSOEID())) {
				form.addError(BankerDataForm.C_ERROR_EMPTY_EMPLOYEE_SOEID);
			}else if(vo.getEmployeeSOEID().trim().length()>20){
				form.addError(BankerDataForm.C_ERROR_MAX_LENGTH_EMPLOYEE_SOEID);
			}		
		}else{
			form.addError(BankerDataForm.C_ERROR_EMPTY);
		}
		return errors;
	}
	
	public boolean isNumberInteger(String value){
		
		try {
			new Integer(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
			
		}
	}
	
	public boolean isNumberBigInteger(String value){
		
		try {
			new BigInteger(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
			
		}
	}
	
	public boolean isNumberBigDecimal(String value){
		
		try {
			FormatUtils.formatStringToBigDecimalScale(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
			
		}
	}
	
	public static boolean isLongNumber(String value){
		
		try {
			Long.parseLong(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public List<String> validate(RegisterAuthorizedForm form) throws ParseException{
		
		AuthorizationPersonVO vo = form.getSelectRegister();
		List<String> errors = new ArrayList<String>();
		TplPrvtAuthPersnDAO authDao = new TplPrvtAuthPersnDAO();
		
		if(vo!=null){
			
			//valida numero EM
			if (StringUtils.isBlank(vo.getEmNbr())){
				form.addError(RegisterAuthorizedForm.C_ERROR_AUTH_EMPTY_EM);
			}else if (authDao.validateEmNbr(vo.getEmNbr()) && form.isUpdate() == false){
			    form.addError(RegisterAuthorizedForm.C_ERROR_AUTH_INVALID_EM);
			}
			
			
			
			//valida minimo 1 conta	
			if (vo.getAccountList()==null || vo.getAccountList().isEmpty()) {
				form.addError(RegisterAuthorizedForm.C_ERROR_AUTH_EMPTY_ACCOUNT_LIST);
			}
			
			// Valida CPF
			if (StringUtils.isBlank(vo.getCpfCnpjNbr())) {
				form.addError(RegisterAuthorizedForm.C_ERROR_AUTH_EMPTY_CPF_CNPJ);
				
			}else if(FormatUtils.unformatterDoc(vo.getCpfCnpjNbr()).length()<=11){
				if(!ValidaCpfCnpj.isValidCPF(vo.getCpfCnpjNbr())){
					form.addError(RegisterAuthorizedForm.C_ERROR_AUTH_INVALID_CPF_CNPJ);
				}				
			}else if(FormatUtils.unformatterDoc(vo.getCpfCnpjNbr()).length()<=14){
				if(!ValidaCpfCnpj.isValidCNPJ(vo.getCpfCnpjNbr())){
					form.addError(RegisterAuthorizedForm.C_ERROR_AUTH_INVALID_CPF_CNPJ);
				}
			}else{
				form.addError(RegisterAuthorizedForm.C_ERROR_AUTH_INVALID_CPF_CNPJ);
			}
			
			// Valida Data de Nascimento se preenchida
			if (StringUtils.isBlank(vo.getBirthDate())) {				
				form.addError(RegisterAuthorizedForm.C_ERROR_AUTH_EMPTY_DATE_BIRTH);				
			}else if(!FormatUtils.isDateValidate(vo.getBirthDate())){
				form.addError(RegisterAuthorizedForm.C_ERROR_INVALID_DATE_BIRTH);
			}
			
			//valida Nome			
			if (StringUtils.isBlank(vo.getAuthPersnName())) {
				form.addError(RegisterAuthorizedForm.C_ERROR_AUTH_EMPTY_NAME);
			}
		}
		return errors;
	}
	
	public List<String> validate(StatusCpfCnpjForm form) throws ParseException{
		
		List<String> errors = new ArrayList<String>();
		
		if(form!=null){
			
			// Valida Status
			if (StringUtils.isBlank(form.getSelectedRegister().getCpfStatus())) {
				form.addError(RegisterAuthorizedForm.C_ERROR_STATUS_CPF_CNPJ_EMPTY_CPF_STATUS);
			}
			
			// Valida CPF/CNPJ	
			if (StringUtils.isBlank(form.getSelectedRegister().getCpfCnpjNbr())) {
				form.addError(RegisterAuthorizedForm.C_ERROR_STATUS_CPF_CNPJ_EMPTY_CPF_LIST);
			}else{
				String[] cpfList = FormatUtils.unformatterDoc(form.getSelectedRegister().getCpfCnpjNbr()).split(",");
				if(cpfList==null || cpfList.length==0){
					form.addError(RegisterAuthorizedForm.C_ERROR_STATUS_CPF_CNPJ_EMPTY_CPF_LIST);
				}else if(cpfList.length>200){
					form.addError(RegisterAuthorizedForm.C_ERROR_STATUS_CPF_CNPJ_LIST_MAX);
				}
			}
			
			// Valida Mes/Ano de Atualização
			if (StringUtils.isBlank(form.getSelectedRegister().getCpfUpdMthYr())) {				
				form.addError(RegisterAuthorizedForm.C_ERROR_STATUS_CPF_CNPJ_EMPTY_MONTH_YEAR);				
			}else if(!FormatUtils.isDateValidate("01/" + form.getSelectedRegister().getCpfUpdMthYr())){
				form.addError(RegisterAuthorizedForm.C_ERROR_STATUS_CPF_CNPJ_INVALID_MONTH_YEAR);
			}
		}
		return errors;
	}
	
    public List<String> validate(DocumentsForm form) throws ParseException{
		
		List<String> errors = new ArrayList<String>();
		
		if(form!=null){
			
			// Valida Titulo
			if (StringUtils.isBlank(form.getSelectedRegister().getCpfStatus())) {
				form.addError(RegisterAuthorizedForm.C_ERROR_STATUS_CPF_CNPJ_EMPTY_CPF_STATUS);
			}
			
			// Valida CPF/CNPJ	
			if (StringUtils.isBlank(form.getSelectedRegister().getCpfCnpjNbr())) {
				form.addError(RegisterAuthorizedForm.C_ERROR_STATUS_CPF_CNPJ_EMPTY_CPF_LIST);
			}else{
				String[] cpfList = FormatUtils.unformatterDoc(form.getSelectedRegister().getCpfCnpjNbr()).split(",");
				if(cpfList==null || cpfList.length==0){
					form.addError(RegisterAuthorizedForm.C_ERROR_STATUS_CPF_CNPJ_EMPTY_CPF_LIST);
				}else if(cpfList.length>200){
					form.addError(RegisterAuthorizedForm.C_ERROR_STATUS_CPF_CNPJ_LIST_MAX);
				}
			}
			
			
		}
		return errors;
	}

	public List<String> validate(AccountComplementDataForm form) throws ParseException{
		
		AcctCmplVO vo = form.getSelectRegisterAccountComplement();
		List<String> errors = new ArrayList<String>();
		
		if(vo!=null){
		
			
			if (StringUtils.isBlank(vo.getAcctNbr())) {
				form.addError(AccountComplementDataForm.C_ERROR_ACCOUNT_COMPL_EMPTY_ACCT_NBR);
			}
			
			if (StringUtils.isBlank(vo.getAccountType())) {
				form.addError(AccountComplementDataForm.C_ERROR_ACCOUNT_COMPL_EMPTY_TYPE);
			}
			
						
			// Valida CPF
			if (StringUtils.isBlank(vo.getCpfCnpjNbr())) {
				form.addError(AccountComplementDataForm.C_ERROR_ACCOUNT_COMPL_EMPTY_CPF_CNPJ);
				
			}
			
			
			if (!StringUtils.isBlank(vo.getAccountOpenDate())) {	
				
				if(!FormatUtils.isDateValidate(vo.getAccountOpenDate())){
					form.addError(AccountComplementDataForm.C_ERROR_ACCOUNT_INVALID_DATE_OPEN_ACCOUNT);
				}				
			}
			
			if (!StringUtils.isBlank(vo.getAccountClosingDate())) {	
				
				if(!FormatUtils.isDateValidate(vo.getAccountClosingDate())){
					form.addError(AccountComplementDataForm.C_ERROR_ACCOUNT_INVALID_DATE_END_ACCOUNT);
				}				
			}
			
			if (!StringUtils.isBlank(vo.getContractSignatureDate())) {	
				
				if(!FormatUtils.isDateValidate(vo.getContractSignatureDate())){
					form.addError(AccountComplementDataForm.C_ERROR_ACCOUNT_INVALID_DATE_ASS_ACCOUNT);
				}				
			}
			
				
			if (!StringUtils.isBlank(vo.getLastIosRevDate())) {	
				
				if(!FormatUtils.isDateValidate(vo.getLastIosRevDate())){
					form.addError(AccountComplementDataForm.C_ERROR_ACCOUNT_INVALID_DATE_IOS_LAST_REV);
				}				
			}

			if(!StringUtils.isBlank(vo.getErNbr())){
			
				if(vo.getErNbr().trim().length()>30){
					form.addError(AssociationAccountForm.C_ERROR_MAX_LENGTH_ER);
				}
				
				if (StringUtils.isBlank(vo.getEgNbr())) {
					form.addError(AccountComplementDataForm.C_ERROR_EMPTY_EG);
					form.setSelectRegister(new EgVO());
				} else {
				
					AccountComplementDataService acctService = new AccountComplementDataService();
					ArrayList<RegisterDataCustomerVO> resultList = acctService.searchCustomerListByER(vo.getErNbr());
			
					if (resultList == null || resultList.size()==0) {			
						form.setHasEmList(false);
						form.addError(AccountComplementDataForm.C_ERROR_ER_NOT_EXIST);
						form.setSelectRegister(new EgVO());
					} else {
						EgVO egVo = new EgVO();
						egVo.setCustomerList(resultList);
						egVo.setErNbr(vo.getErNbr());
						form.setHasEmList(true);
						form.setSelectRegister(egVo);	
					}
				}
			}
			
			if(!StringUtils.isBlank(vo.getEgNbr())){
				if(vo.getEgNbr().trim().length()>4){
					form.addError(AssociationAccountForm.C_ERROR_MAX_LENGTH_EG);
				}			
				
				if (StringUtils.isBlank(vo.getErNbr())) {
					form.addError(AccountComplementDataForm.C_ERROR_EMPTY_ER);
					form.setSelectRegister(new EgVO());
				} else {
				
					AccountComplementDataService acctService = new AccountComplementDataService();
					ArrayList<RegisterDataCustomerVO> resultList = acctService.searchCustomerListByER(vo.getErNbr());
			
					if (resultList == null || resultList.size()==0) {			
						form.setHasEmList(false);
						form.addError(AccountComplementDataForm.C_ERROR_ER_NOT_EXIST);
						form.setSelectRegister(new EgVO());
					}
				}
			}
		
		}
		return errors;
	}
	
	public List<String> validate(AccountMigrationDataForm form){
		
		AcctMgrtVO vo = form.getSelectRegisterAccountMigrate();
		List<String> errors = new ArrayList<String>();
		
		if(vo!=null){
			
			if (StringUtils.isBlank(vo.getAccountGrbNumber())) {
				form.addError(AccountMigrationDataForm.C_ERROR_EMPTY_ACCOUNT_CITIBANK_MGRT_ACCOUNT);
			}
					
			if (StringUtils.isBlank(vo.getAccountCustodiaNumber())) {
				form.addError(AccountMigrationDataForm.C_ERROR_EMPTY_ACCOUNT_CUSTODIA_MGRT_ACCOUNT);
			}

			if (StringUtils.isBlank(vo.getMigrationDateString())) {				
				form.addError(AccountMigrationDataForm.C_ERROR_EMPTY_DATE_MGRT_ACCOUNT);				
			}else if(!FormatUtils.isDateValidate(vo.getMigrationDateString())){
				form.addError(AccountMigrationDataForm.C_ERROR_INVALID_DATE_MGRT_ACCOUNT);
			}

		}
		return errors;
	}
	
	

}
