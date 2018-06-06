package com.citibank.newcpb.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.UserTransaction;

import org.apache.commons.lang.StringUtils;


import com.citibank.newcpb.enun.AddressTypeRegisterEnum;
import com.citibank.newcpb.enun.CountryTypeRegisterEnum;
import com.citibank.newcpb.form.CentralApprovalForm;
import com.citibank.newcpb.form.RegisterDataCustomerForm;
import com.citibank.newcpb.persistence.dao.TplPrvtCustAddressDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtCustAddressMovDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtCustCellDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtCustCellMovDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtCustCountryDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtCustCountryMovDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtCustMailDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtCustMailMovDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtCustomerDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtCustomerMovDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtErEmDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtErEmMovDAO;
import com.citibank.newcpb.persistence.dao.VrhEmployeeRegistrationDAO;
import com.citibank.newcpb.util.FormatUtils;
import com.citibank.newcpb.vo.AddressVO;
import com.citibank.newcpb.vo.CustomerCountryVO;
import com.citibank.newcpb.vo.EmployeeRegistrationVO;
import com.citibank.newcpb.vo.ErEmVO;
import com.citibank.newcpb.vo.MailVO;
import com.citibank.newcpb.vo.RegisterDataCustomerVO;
import com.citibank.newcpb.vo.TelephoneVO;

public class RegisterDataCustomerService extends CommonService {

	public RegisterDataCustomerForm list(RegisterDataCustomerForm form) throws ParseException {

		TplPrvtCustomerDAO dao = new TplPrvtCustomerDAO();
		ArrayList<RegisterDataCustomerVO> resultList = dao.list(form.getName(), form.getNumberEM(), form.getNumberGFCID());

		form.setResultList(resultList);

		if (resultList.size() > 0) {
			form.addMessage(RegisterDataCustomerForm.C_MESSAGE_SUCESS);
		} else {
			form.addMessage(RegisterDataCustomerForm.C_NO_REGISTER_FOUND);
		}
		
		return form;
	}
	
	public RegisterDataCustomerVO getRegisterDataCustomer(String numberGFCID) throws ParseException {
		
		RegisterDataCustomerVO vo = null;
		
		if(!"".equals(numberGFCID)){
			
			//Dados Basicos
			TplPrvtCustomerDAO dao = new TplPrvtCustomerDAO();
			vo =  dao.getRegisterDataCustomer(numberGFCID);
			
			if(vo != null){
				
				if(!StringUtils.isBlank(vo.getCpfCnpj())){
					vo.setCpfCnpj(FormatUtils.formatterDoc(vo.getCpfCnpj()));
				}
				
				if(!StringUtils.isBlank(vo.getAdmCpf())){
					vo.setAdmCpf(FormatUtils.formatterDoc(vo.getAdmCpf()));
				}
				
				//Busca ER		
				if(!StringUtils.isBlank(vo.getNumberEM())){
					TplPrvtErEmDAO tplPrvtErEmDAO = new TplPrvtErEmDAO();
					ErEmVO erEmVO = tplPrvtErEmDAO.getErEm(vo.getNumberEM());
					vo.setEr_em(erEmVO);						
				}
				
				
				// Busca nome Banker
				if(!StringUtils.isBlank(vo.getSOEIDBankerNumber())) {
					EmployeeRegistrationVO employeeRegistrationVO = getEmployeeRegistration(vo.getSOEIDBankerNumber());
					if(employeeRegistrationVO!=null){
						vo.setSOEIDBankerName(employeeRegistrationVO.getEmployeeName());
					}
				}
				
				// Telefone
				TplPrvtCustCellDAO cellDAO = new TplPrvtCustCellDAO();	
				vo.setTelephoneList(cellDAO.list(numberGFCID));
				
				//Email
				TplPrvtCustMailDAO mailDAO = new TplPrvtCustMailDAO();	
				vo.setMailList(mailDAO.list(numberGFCID));
				
				//Cidadanias - Residencias Fiscais
				TplPrvtCustCountryDAO countryDAO = new TplPrvtCustCountryDAO();
				List <CustomerCountryVO> countryList = countryDAO.list(numberGFCID);
				
				if(countryList!=null){
					ArrayList<CustomerCountryVO> fiscalAddressList = null;
					ArrayList<CustomerCountryVO> citizenshipList = null;
					
					for(CustomerCountryVO countryVo : countryList){
						
						if(countryVo!=null && countryVo.getRegisterType().equals(CountryTypeRegisterEnum.FISCAL_ADDRESS.getValue())){
							
							if(fiscalAddressList==null){
								fiscalAddressList = new ArrayList<CustomerCountryVO>();
							}
							
							fiscalAddressList.add(countryVo);
							
						}else if(countryVo!=null && countryVo.getRegisterType().equals(CountryTypeRegisterEnum.CITIZENSHIP.getValue())){
							
							if(citizenshipList==null){
								citizenshipList = new ArrayList<CustomerCountryVO>();
							}
							
							citizenshipList.add(countryVo);
						}
					}	
					vo.setCitizenshipList(citizenshipList);
					vo.setFiscalResidenceList(fiscalAddressList);
				}
				
				//Endereços
				TplPrvtCustAddressDAO addresDAO = new TplPrvtCustAddressDAO();
				List <AddressVO> addressList = addresDAO.list(numberGFCID);
				
				if(addressList!=null){
					
					for(AddressVO addressVO :addressList){
						
						if(addressVO != null && addressVO.getAddrTypeCode().equals(AddressTypeRegisterEnum.RESIDENTIAL.getValue())){
							
							vo.setResidentialAddress(addressVO);
							
						}else if(addressVO!=null && addressVO.getAddrTypeCode().equals(AddressTypeRegisterEnum.OTHER.getValue())){
							
							vo.setOtherAddress(addressVO);
							
						}else if(addressVO!=null && addressVO.getAddrTypeCode().equals(AddressTypeRegisterEnum.COMMERCIAL.getValue())){
							
							if(vo.getCustomerType().equals("F")){
								vo.setBusinessAddress(addressVO);
							}else if(vo.getCustomerType().equals("J")){
								vo.setHeadOfficeAddress(addressVO);
							}
						}
					}	
				}
				
				
			}
		}
		
		return vo;
	}
	
	public RegisterDataCustomerVO getRegisterDataCustomerMOV(String numberGFCID) throws ParseException {
		
		RegisterDataCustomerVO vo = null;
		
		if(!"".equals(numberGFCID)){
			
			//Dados Basicos
			TplPrvtCustomerMovDAO dao = new TplPrvtCustomerMovDAO();
			vo =  dao.getRegisterDataCustomer(numberGFCID);
			
			if(vo != null){
				
				if(vo!=null && !StringUtils.isBlank(vo.getCpfCnpj())){
					vo.setCpfCnpj(FormatUtils.formatterDoc(vo.getCpfCnpj()));
				}
				
				if(!StringUtils.isBlank(vo.getAdmCpf())){
					vo.setAdmCpf(FormatUtils.formatterDoc(vo.getAdmCpf()));
				}
				
				//Busca ER		
				if(!StringUtils.isBlank(vo.getNumberEM())){
					TplPrvtErEmMovDAO tplPrvtErEmMovDAO = new TplPrvtErEmMovDAO();
					ErEmVO erEmVO = tplPrvtErEmMovDAO.getErEm(vo.getNumberEM());
					vo.setEr_em(erEmVO);						
				}
				
				// Busca nome Banker
				if(!StringUtils.isBlank(vo.getSOEIDBankerNumber())) {
					EmployeeRegistrationVO employeeRegistrationVO = getEmployeeRegistration(vo.getSOEIDBankerNumber());
					if(employeeRegistrationVO!=null){
						vo.setSOEIDBankerName(employeeRegistrationVO.getEmployeeName());
					}
				}
				
				// Telefone
				TplPrvtCustCellMovDAO cellDAO = new TplPrvtCustCellMovDAO();	
				vo.setTelephoneList(cellDAO.list(numberGFCID));
				
				//Email
				TplPrvtCustMailMovDAO mailDAO = new TplPrvtCustMailMovDAO();	
				vo.setMailList(mailDAO.list(numberGFCID));
				
				//Cidadanias - Residencias Fiscais
				TplPrvtCustCountryMovDAO countryDAO = new TplPrvtCustCountryMovDAO();
				List <CustomerCountryVO> countryList = countryDAO.list(numberGFCID);
				
				if(countryList!=null){
					ArrayList<CustomerCountryVO> fiscalAddressList = null;
					ArrayList<CustomerCountryVO> citizenshipList = null;
					
					for(CustomerCountryVO countryVo : countryList){
						
						if(countryVo!=null && countryVo.getRegisterType().equals(CountryTypeRegisterEnum.FISCAL_ADDRESS.getValue())){
							
							if(fiscalAddressList==null){
								fiscalAddressList = new ArrayList<CustomerCountryVO>();
							}
							
							fiscalAddressList.add(countryVo);
							
						}else if(countryVo!=null && countryVo.getRegisterType().equals(CountryTypeRegisterEnum.CITIZENSHIP.getValue())){
							
							if(citizenshipList==null){
								citizenshipList = new ArrayList<CustomerCountryVO>();
							}
							
							citizenshipList.add(countryVo);
						}
					}	
					vo.setCitizenshipList(citizenshipList);
					vo.setFiscalResidenceList(fiscalAddressList);
				}
				
				//Endereços
				TplPrvtCustAddressMovDAO addresDAO = new TplPrvtCustAddressMovDAO();
				List <AddressVO> addressList = addresDAO.list(numberGFCID);
				
				if(addressList!=null){
					
					for(AddressVO addressVO :addressList){
						
						if(addressVO != null && addressVO.getAddrTypeCode().equals(AddressTypeRegisterEnum.RESIDENTIAL.getValue())){
							
							vo.setResidentialAddress(addressVO);
							
						}else if(addressVO!=null && addressVO.getAddrTypeCode().equals(AddressTypeRegisterEnum.OTHER.getValue())){
							
							vo.setOtherAddress(addressVO);
							
						}else if(addressVO!=null && addressVO.getAddrTypeCode().equals(AddressTypeRegisterEnum.COMMERCIAL.getValue())){
							
							if(vo.getCustomerType().equals("F")){
								vo.setBusinessAddress(addressVO);
							}else if(vo.getCustomerType().equals("J")){
								vo.setHeadOfficeAddress(addressVO);
							}
						}
					}	
				}
				
				
			}
		}
		
		return vo;
	}
	
	public ArrayList<String> getFieldsWithDifference(RegisterDataCustomerVO mov) throws ParseException {
		
		ArrayList<String> idDiffList = new ArrayList<String>();
		
		if(mov!=null && !StringUtils.isBlank(mov.getNumberGFCID())){
			
			RegisterDataCustomerVO cad = getRegisterDataCustomer(mov.getNumberGFCID());
			
			if(cad!=null && !StringUtils.isBlank(cad.getNumberGFCID())){
				
				idDiffList = cad.equals(mov);
				
			}
		}
		
		return idDiffList;
	}
	
	public void saveForAprove(RegisterDataCustomerVO vo) throws Exception{
		
		UserTransaction transaction = getUserTransaction();
		
		Date lastUpdate = new Date();
		String recStatCode = "U";
		String userId = vo.getLastUpdUser();
		
		
		if(vo!=null){
			beginTransaction(transaction);
			
			try {
				
				//Insert Dados Basicos
				vo.setLastUpdDate(lastUpdate);
				vo.setRecStatCode(recStatCode);
				
				TplPrvtCustomerMovDAO dao = new TplPrvtCustomerMovDAO();
				dao.insert(vo);
				
				TplPrvtErEmMovDAO tplPrvtErEmMovDAO = new TplPrvtErEmMovDAO();
				//Insert ER
				if(!StringUtils.isBlank(vo.getNumberEM()) && vo.getEr_em()!=null && !StringUtils.isBlank(vo.getEr_em().getErNbr())
						&& !StringUtils.isBlank(vo.getEr_em().getRoleCustCode())){					
					
					vo.getEr_em().setEmNbr(vo.getNumberEM());
					vo.getEr_em().setRecStatCode(recStatCode);
					vo.getEr_em().setLastUpdDate(lastUpdate);
					vo.getEr_em().setLastUpdUser(userId);
					tplPrvtErEmMovDAO.insert(vo.getEr_em());					
				}else{
					tplPrvtErEmMovDAO.deleteByEM(vo.getNumberEM());
				}
				
				
				//Insert Telefones
				if(vo.getTelephoneList()!=null && vo.getTelephoneList().size()>0){
					int telCount = 1;
					TplPrvtCustCellMovDAO cellDAO = new TplPrvtCustCellMovDAO();
					for(TelephoneVO voTelephone : vo.getTelephoneList()){
						
						if(!StringUtils.isBlank(voTelephone.getDdd()) && !StringUtils.isBlank(voTelephone.getNumber())){
							voTelephone.setCustGfcidNbr(Long.parseLong(vo.getNumberGFCID()));
							voTelephone.setPosition(telCount);
							voTelephone.setLastUpdDate(lastUpdate);
							voTelephone.setRecStatCode(recStatCode);
							voTelephone.setLastUpdUser(userId);
							cellDAO.insert(voTelephone);
							telCount =  telCount +1;
						}	
					}				
				}
				
				//Insert Emails
				if(vo.getMailList()!=null){
					int mailCount = 1;
					TplPrvtCustMailMovDAO mailDAO = new TplPrvtCustMailMovDAO();
					for(MailVO voMail : vo.getMailList()){
						
						if(!StringUtils.isBlank(voMail.getMail())){
							voMail.setCustGfcidNbr(Long.parseLong(vo.getNumberGFCID()));
							voMail.setPosition(mailCount);
							voMail.setLastUpdDate(lastUpdate);
							voMail.setRecStatCode(recStatCode);
							voMail.setLastUpdUser(userId);
							mailDAO.insert(voMail);
							mailCount =  mailCount +1;
						}	
					}				
				}
				
				//Insert Cidadanias
				if(vo.getCitizenshipList()!=null){
					int countryCitizenCount = 1;
					TplPrvtCustCountryMovDAO countryCitizenDAO = new TplPrvtCustCountryMovDAO();
					for(CustomerCountryVO customerCountryVO : vo.getCitizenshipList()){
						
						if(!StringUtils.isBlank(customerCountryVO.getCountry())){
							customerCountryVO.setCustGfcidNbr(Long.parseLong(vo.getNumberGFCID()));
							customerCountryVO.setRegisterType(CountryTypeRegisterEnum.CITIZENSHIP.getValue());
							customerCountryVO.setPosition(countryCitizenCount);
							customerCountryVO.setLastUpdDate(lastUpdate);
							customerCountryVO.setRecStatCode(recStatCode);
							customerCountryVO.setLastUpdUser(userId);
							countryCitizenDAO.insert(customerCountryVO);
							countryCitizenCount =  countryCitizenCount +1;
						}	
					}				
				}
				
				//Insert Residencias Fiscais
				if(vo.getFiscalResidenceList()!=null){
					int countryFiscalCount = 1;
					TplPrvtCustCountryMovDAO countryFiscalDAO = new TplPrvtCustCountryMovDAO();
					for(CustomerCountryVO customerCountryVO : vo.getFiscalResidenceList()){
						
						if(!StringUtils.isBlank(customerCountryVO.getCountry())){
							customerCountryVO.setCustGfcidNbr(Long.parseLong(vo.getNumberGFCID()));
							customerCountryVO.setRegisterType(CountryTypeRegisterEnum.FISCAL_ADDRESS.getValue());
							customerCountryVO.setPosition(countryFiscalCount);
							customerCountryVO.setLastUpdDate(lastUpdate);
							customerCountryVO.setRecStatCode(recStatCode);
							customerCountryVO.setLastUpdUser(userId);
							countryFiscalDAO.insert(customerCountryVO);
							countryFiscalCount =  countryFiscalCount +1;
						}	
					}				
				}	

				int addressCount = 1;
				TplPrvtCustAddressMovDAO addresDAO = new TplPrvtCustAddressMovDAO();
				
				//Endereço Outros
				
				if(vo.getOtherAddress()!=null && !StringUtils.isBlank(vo.getOtherAddress().getStreet())){
					vo.getOtherAddress().setAddrTypeCode(AddressTypeRegisterEnum.OTHER.getValue());
					vo.getOtherAddress().setCustGfcidNbr(Long.parseLong(vo.getNumberGFCID()));
					vo.getOtherAddress().setAddrSeqNbr(addressCount);
					vo.getOtherAddress().setLastUpdDate(lastUpdate);
					vo.getOtherAddress().setRecStatCode(recStatCode);
					vo.getOtherAddress().setLastUpdUser(userId);
					addresDAO.insert(vo.getOtherAddress());
					addressCount = addressCount + 1;
				}
				
				//Endereço Pessoa Fisica
				if(vo.getCustomerType().equals("F")){
					
					//Endereço Residencial
					if(vo.getResidentialAddress()!=null && !StringUtils.isBlank(vo.getResidentialAddress().getStreet())){
						vo.getResidentialAddress().setAddrTypeCode(AddressTypeRegisterEnum.RESIDENTIAL.getValue());
						vo.getResidentialAddress().setCustGfcidNbr(Long.parseLong(vo.getNumberGFCID()));
						vo.getResidentialAddress().setAddrSeqNbr(addressCount);
						vo.getResidentialAddress().setLastUpdDate(lastUpdate);
						vo.getResidentialAddress().setRecStatCode(recStatCode);
						vo.getResidentialAddress().setLastUpdUser(userId);
						addresDAO.insert(vo.getResidentialAddress());
						addressCount = addressCount + 1;
					}
					
					//Endereço Comercial
					if(vo.getBusinessAddress()!=null && !StringUtils.isBlank(vo.getBusinessAddress().getStreet())){
						vo.getBusinessAddress().setAddrTypeCode(AddressTypeRegisterEnum.COMMERCIAL.getValue());
						vo.getBusinessAddress().setCustGfcidNbr(Long.parseLong(vo.getNumberGFCID()));
						vo.getBusinessAddress().setAddrSeqNbr(addressCount);
						vo.getBusinessAddress().setLastUpdDate(lastUpdate);
						vo.getBusinessAddress().setRecStatCode(recStatCode);
						vo.getBusinessAddress().setLastUpdUser(userId);
						addresDAO.insert(vo.getBusinessAddress());
						addressCount = addressCount + 1;
					}
					
					
				//Endereço Pessoa Juridica	
				}else if(vo.getCustomerType().equals("J")){
					
					//Endereço Sede Comercial
					if(vo.getHeadOfficeAddress()!=null && !StringUtils.isBlank(vo.getHeadOfficeAddress().getStreet())){
						vo.getHeadOfficeAddress().setAddrTypeCode(AddressTypeRegisterEnum.COMMERCIAL.getValue());
						vo.getHeadOfficeAddress().setCustGfcidNbr(Long.parseLong(vo.getNumberGFCID()));
						vo.getHeadOfficeAddress().setAddrSeqNbr(addressCount);
						vo.getHeadOfficeAddress().setLastUpdDate(lastUpdate);
						vo.getHeadOfficeAddress().setRecStatCode(recStatCode);
						vo.getHeadOfficeAddress().setLastUpdUser(userId);
						addresDAO.insert(vo.getHeadOfficeAddress());
						addressCount = addressCount + 1;
					}
				}
				commitTransaction( transaction );	
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}	
		}		
	}

	public void updateMov(RegisterDataCustomerVO vo) throws Exception{
		
		UserTransaction transaction = getUserTransaction();		
		
		Date lastUpdate = new Date();
		String recStatCode = "U";
		String userId = vo.getLastUpdUser();
		
		
		if(vo!=null){
			beginTransaction(transaction);
			
			try {
				//Update Dados Basicos
				vo.setLastUpdDate(lastUpdate);
				vo.setRecStatCode(recStatCode);
				
				TplPrvtCustomerMovDAO dao = new TplPrvtCustomerMovDAO();
				dao.update(vo);
				
				TplPrvtErEmMovDAO tplPrvtErEmMovDAO = new TplPrvtErEmMovDAO();
				//Update ER				
				if(!StringUtils.isBlank(vo.getNumberEM())){					
					tplPrvtErEmMovDAO.deleteByEM(vo.getNumberEM());
					
					if(vo.getEr_em()!=null && !StringUtils.isBlank(vo.getEr_em().getErNbr())
							&& !StringUtils.isBlank(vo.getEr_em().getRoleCustCode())){					
						vo.getEr_em().setEmNbr(vo.getNumberEM());
						vo.getEr_em().setRecStatCode(recStatCode);
						vo.getEr_em().setLastUpdDate(lastUpdate);
						vo.getEr_em().setLastUpdUser(userId);
						tplPrvtErEmMovDAO.insert(vo.getEr_em());					
					}else{
						tplPrvtErEmMovDAO.deleteByEM(vo.getNumberEM());
					}						
				}else{
					tplPrvtErEmMovDAO.deleteByEM(vo.getNumberEM());
				}
				
				
				//Insert Telefones
				if(vo.getTelephoneList()!=null && vo.getTelephoneList().size()>0){
					int telCount = 1;
					TplPrvtCustCellMovDAO cellDAO = new TplPrvtCustCellMovDAO();
					cellDAO.deleteByGFCID(vo.getNumberGFCID());
					for(TelephoneVO voTelephone : vo.getTelephoneList()){
						
						if(!StringUtils.isBlank(voTelephone.getDdd()) && !StringUtils.isBlank(voTelephone.getNumber())){
							voTelephone.setCustGfcidNbr(Long.parseLong(vo.getNumberGFCID()));
							voTelephone.setPosition(telCount);
							voTelephone.setLastUpdDate(lastUpdate);
							voTelephone.setRecStatCode(recStatCode);
							voTelephone.setLastUpdUser(userId);
							cellDAO.insert(voTelephone);
							telCount =  telCount +1;
						}	
					}				
				}
				
				//Insert Emails
				if(vo.getMailList()!=null){
					int mailCount = 1;
					TplPrvtCustMailMovDAO mailDAO = new TplPrvtCustMailMovDAO();
					mailDAO.deleteByGFCID(vo.getNumberGFCID());
					for(MailVO voMail : vo.getMailList()){
						
						if(!StringUtils.isBlank(voMail.getMail())){
							voMail.setCustGfcidNbr(Long.parseLong(vo.getNumberGFCID()));
							voMail.setPosition(mailCount);
							voMail.setLastUpdDate(lastUpdate);
							voMail.setRecStatCode(recStatCode);
							voMail.setLastUpdUser(userId);
							mailDAO.insert(voMail);
							mailCount =  mailCount +1;
						}	
					}				
				}
				
				//Insert Cidadanias
				TplPrvtCustCountryMovDAO countryCitizenDAO = new TplPrvtCustCountryMovDAO();
				countryCitizenDAO.deleteByGFCID(vo.getNumberGFCID());
				
				if(vo.getCitizenshipList()!=null){
					int countryCitizenCount = 1;
					for(CustomerCountryVO customerCountryVO : vo.getCitizenshipList()){
						
						if(!StringUtils.isBlank(customerCountryVO.getCountry())){
							customerCountryVO.setCustGfcidNbr(Long.parseLong(vo.getNumberGFCID()));
							customerCountryVO.setRegisterType(CountryTypeRegisterEnum.CITIZENSHIP.getValue());
							customerCountryVO.setPosition(countryCitizenCount);
							customerCountryVO.setLastUpdDate(lastUpdate);
							customerCountryVO.setRecStatCode(recStatCode);
							customerCountryVO.setLastUpdUser(userId);
							countryCitizenDAO.insert(customerCountryVO);
							countryCitizenCount =  countryCitizenCount +1;
						}	
					}				
				}
				
				//Insert Residencias Fiscais
				if(vo.getFiscalResidenceList()!=null){
					int countryFiscalCount = 1;
					TplPrvtCustCountryMovDAO countryFiscalDAO = new TplPrvtCustCountryMovDAO();
					for(CustomerCountryVO customerCountryVO : vo.getFiscalResidenceList()){
						
						if(!StringUtils.isBlank(customerCountryVO.getCountry())){
							customerCountryVO.setCustGfcidNbr(Long.parseLong(vo.getNumberGFCID()));
							customerCountryVO.setRegisterType(CountryTypeRegisterEnum.FISCAL_ADDRESS.getValue());
							customerCountryVO.setPosition(countryFiscalCount);
							customerCountryVO.setLastUpdDate(lastUpdate);
							customerCountryVO.setRecStatCode(recStatCode);
							customerCountryVO.setLastUpdUser(userId);
							countryFiscalDAO.insert(customerCountryVO);
							countryFiscalCount =  countryFiscalCount +1;
						}	
					}				
				}	

				int addressCount = 1;
				TplPrvtCustAddressMovDAO addresDAO = new TplPrvtCustAddressMovDAO();
				addresDAO.deleteByGFCID(vo.getNumberGFCID());
				//Endereço Outros
				
				if(vo.getOtherAddress()!=null && !StringUtils.isBlank(vo.getOtherAddress().getStreet())){
					vo.getOtherAddress().setAddrTypeCode(AddressTypeRegisterEnum.OTHER.getValue());
					vo.getOtherAddress().setCustGfcidNbr(Long.parseLong(vo.getNumberGFCID()));
					vo.getOtherAddress().setAddrSeqNbr(addressCount);
					vo.getOtherAddress().setLastUpdDate(lastUpdate);
					vo.getOtherAddress().setRecStatCode(recStatCode);
					vo.getOtherAddress().setLastUpdUser(userId);
					addresDAO.insert(vo.getOtherAddress());
					addressCount = addressCount + 1;
				}
				
				//Endereço Pessoa Fisica
				if(vo.getCustomerType().equals("F")){
					
					//Endereço Residencial
					if(vo.getResidentialAddress()!=null && !StringUtils.isBlank(vo.getResidentialAddress().getStreet())){
						vo.getResidentialAddress().setAddrTypeCode(AddressTypeRegisterEnum.RESIDENTIAL.getValue());
						vo.getResidentialAddress().setCustGfcidNbr(Long.parseLong(vo.getNumberGFCID()));
						vo.getResidentialAddress().setAddrSeqNbr(addressCount);
						vo.getResidentialAddress().setLastUpdDate(lastUpdate);
						vo.getResidentialAddress().setRecStatCode(recStatCode);
						vo.getResidentialAddress().setLastUpdUser(userId);
						addresDAO.insert(vo.getResidentialAddress());
						addressCount = addressCount + 1;
					}
					
					//Endereço Comercial
					if(vo.getBusinessAddress()!=null && !StringUtils.isBlank(vo.getBusinessAddress().getStreet())){
						vo.getBusinessAddress().setAddrTypeCode(AddressTypeRegisterEnum.COMMERCIAL.getValue());
						vo.getBusinessAddress().setCustGfcidNbr(Long.parseLong(vo.getNumberGFCID()));
						vo.getBusinessAddress().setAddrSeqNbr(addressCount);
						vo.getBusinessAddress().setLastUpdDate(lastUpdate);
						vo.getBusinessAddress().setRecStatCode(recStatCode);
						vo.getBusinessAddress().setLastUpdUser(userId);
						addresDAO.insert(vo.getBusinessAddress());
						addressCount = addressCount + 1;
					}
					
					
				//Endereço Pessoa Juridica	
				}else if(vo.getCustomerType().equals("J")){
					
					//Endereço Sede Comercial
					if(vo.getHeadOfficeAddress()!=null && !StringUtils.isBlank(vo.getHeadOfficeAddress().getStreet())){
						vo.getHeadOfficeAddress().setAddrTypeCode(AddressTypeRegisterEnum.COMMERCIAL.getValue());
						vo.getHeadOfficeAddress().setCustGfcidNbr(Long.parseLong(vo.getNumberGFCID()));
						vo.getHeadOfficeAddress().setAddrSeqNbr(addressCount);
						vo.getHeadOfficeAddress().setLastUpdDate(lastUpdate);
						vo.getHeadOfficeAddress().setRecStatCode(recStatCode);
						vo.getHeadOfficeAddress().setLastUpdUser(userId);
						addresDAO.insert(vo.getHeadOfficeAddress());
						addressCount = addressCount + 1;
					}
				}
				commitTransaction( transaction );
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}
		}		
	}
	
	public Boolean hasRegisterMov(String numberGFCID){
		TplPrvtCustomerMovDAO dao = new TplPrvtCustomerMovDAO();
		return dao.hasRegisterCustomer(numberGFCID);
	}
	
	public EmployeeRegistrationVO getEmployeeRegistration(String numberSOEID){
		VrhEmployeeRegistrationDAO dao = new VrhEmployeeRegistrationDAO();
		return dao.getEmployeeRegistration(numberSOEID);
	}
	
	public void approve(RegisterDataCustomerForm registerDataCustomerForm, Date now) throws Exception {
		
		registerDataCustomerForm.clearErrors();

		if (!registerDataCustomerForm.hasErrors()) {
			UserTransaction transaction = getUserTransaction();
			try {
				
				beginTransaction(transaction);
				
				// 1 - Copia dados da entidade de movimento para a de cadastro					
				TplPrvtCustomerMovDAO customerMovDAO = new TplPrvtCustomerMovDAO();	

				// 2 - Realiza a atualização do registro na tabela de cadastro
				TplPrvtCustomerDAO customerDAO = new TplPrvtCustomerDAO();			
				TplPrvtCustAddressDAO addresDAO = new TplPrvtCustAddressDAO();	
				TplPrvtCustCellDAO cellDAO = new TplPrvtCustCellDAO();	
				TplPrvtCustMailDAO mailDAO = new TplPrvtCustMailDAO();	
				TplPrvtCustCountryDAO countryDAO = new TplPrvtCustCountryDAO();
				TplPrvtErEmDAO tplPrvtErEmDAO = new TplPrvtErEmDAO();
				
				if("I".equals(registerDataCustomerForm.getRegisterConsumer().getRecStatCode())){
					registerDataCustomerForm.getRegisterConsumer().setRecStatCode("U");
				}			
				if(registerDataCustomerForm.getLoggedUser()!=null){
					registerDataCustomerForm.getRegisterConsumer().setLastAuthUser(registerDataCustomerForm.getLoggedUser().getUserID());
				}
				if(now!=null){
					registerDataCustomerForm.getRegisterConsumer().setLastAuthDate(now);
				}
				if(registerDataCustomerForm.getRegisterConsumer().getCustCreateDate() == null){
					registerDataCustomerForm.getRegisterConsumer().setCustCreateDate(FormatUtils.dateToString(now, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY_HHMMSS));
				}
				if(registerDataCustomerForm.getRegisterConsumer().getCustCreateUser() == null){
					registerDataCustomerForm.getRegisterConsumer().setCustCreateUser(registerDataCustomerForm.getLoggedUser().getUserID());
				}
				customerDAO.update(registerDataCustomerForm.getRegisterConsumer());					
				
				//Update ER				
				if(registerDataCustomerForm.getRegisterConsumer().getEr_em()!=null 
						&& !StringUtils.isBlank(registerDataCustomerForm.getRegisterConsumer().getEr_em().getErNbr()) 
						&& !StringUtils.isBlank(registerDataCustomerForm.getRegisterConsumer().getNumberEM())){
										
					tplPrvtErEmDAO.deleteByEM_ER(registerDataCustomerForm.getRegisterConsumer().getNumberEM(), 
							registerDataCustomerForm.getRegisterConsumer().getEr_em().getErNbr());
					
					if(!StringUtils.isBlank(registerDataCustomerForm.getRegisterConsumer().getNumberEM()) 
							&& registerDataCustomerForm.getRegisterConsumer().getEr_em()!=null 
							&& !StringUtils.isBlank(registerDataCustomerForm.getRegisterConsumer().getEr_em().getErNbr())
							&& !StringUtils.isBlank(registerDataCustomerForm.getRegisterConsumer().getEr_em().getRoleCustCode())){
						

						
						ArrayList<String> idDiffList = this.getFieldsWithDifference(registerDataCustomerForm.getRegisterConsumer());
						//Se alterou o numero do ER, entao deleta todos ER do EM do cliente 
						if (idDiffList != null && idDiffList.contains("er_em.erNbr")){
							tplPrvtErEmDAO.deleteByEM_ER(registerDataCustomerForm.getRegisterConsumer().getNumberEM(),null);							
						}	
						
						registerDataCustomerForm.getRegisterConsumer().getEr_em().setEmNbr(registerDataCustomerForm.getRegisterConsumer().getNumberEM());
						registerDataCustomerForm.getRegisterConsumer().getEr_em().setLastAuthDate(now);
						registerDataCustomerForm.getRegisterConsumer().getEr_em().setLastAuthUser(registerDataCustomerForm.getLoggedUser().getUserID());
						tplPrvtErEmDAO.insert(registerDataCustomerForm.getRegisterConsumer().getEr_em());					
					}else{
						tplPrvtErEmDAO.deleteByEM_ER(registerDataCustomerForm.getRegisterConsumer().getNumberEM(), 
								registerDataCustomerForm.getRegisterConsumer().getEr_em().getErNbr());
					}					
					
				}else{
					tplPrvtErEmDAO.deleteByEM_ER(registerDataCustomerForm.getRegisterConsumer().getNumberEM(), 
							registerDataCustomerForm.getRegisterConsumer().getEr_em().getErNbr());
				}
				
				addresDAO.deleteByGFCID(registerDataCustomerForm.getRegisterConsumer().getNumberGFCID());
				if(registerDataCustomerForm.getRegisterConsumer().getResidentialAddress()!=null && registerDataCustomerForm.getRegisterConsumer().getResidentialAddress().getCustGfcidNbr()!=null){
					
					if(registerDataCustomerForm.getLoggedUser()!=null){
						registerDataCustomerForm.getRegisterConsumer().getResidentialAddress().setLastAuthUser(registerDataCustomerForm.getLoggedUser().getUserID());
					}
					if(now!=null){
						registerDataCustomerForm.getRegisterConsumer().getResidentialAddress().setLastAuthDate(now);
					}						
					addresDAO.insert(registerDataCustomerForm.getRegisterConsumer().getResidentialAddress());
				}
				
				if(registerDataCustomerForm.getRegisterConsumer().getBusinessAddress()!=null && registerDataCustomerForm.getRegisterConsumer().getBusinessAddress().getCustGfcidNbr()!=null){
					if(registerDataCustomerForm.getLoggedUser()!=null){
						registerDataCustomerForm.getRegisterConsumer().getBusinessAddress().setLastAuthUser(registerDataCustomerForm.getLoggedUser().getUserID());
					}
					if(now!=null){
						registerDataCustomerForm.getRegisterConsumer().getBusinessAddress().setLastAuthDate(now);
					}
					addresDAO.insert(registerDataCustomerForm.getRegisterConsumer().getBusinessAddress());
				}
				
				if(registerDataCustomerForm.getRegisterConsumer().getOtherAddress()!=null && registerDataCustomerForm.getRegisterConsumer().getOtherAddress().getCustGfcidNbr()!=null){
					if(registerDataCustomerForm.getLoggedUser()!=null){
						registerDataCustomerForm.getRegisterConsumer().getOtherAddress().setLastAuthUser(registerDataCustomerForm.getLoggedUser().getUserID());
					}
					if(now!=null){
						registerDataCustomerForm.getRegisterConsumer().getOtherAddress().setLastAuthDate(now);
					}
					addresDAO.insert(registerDataCustomerForm.getRegisterConsumer().getOtherAddress());			
				}
				
				if(registerDataCustomerForm.getRegisterConsumer().getHeadOfficeAddress()!=null && registerDataCustomerForm.getRegisterConsumer().getHeadOfficeAddress().getCustGfcidNbr()!=null){
					if(registerDataCustomerForm.getLoggedUser()!=null){
						registerDataCustomerForm.getRegisterConsumer().getHeadOfficeAddress().setLastAuthUser(registerDataCustomerForm.getLoggedUser().getUserID());
					}
					if(now!=null){
						registerDataCustomerForm.getRegisterConsumer().getHeadOfficeAddress().setLastAuthDate(now);
					}
					addresDAO.insert(registerDataCustomerForm.getRegisterConsumer().getHeadOfficeAddress());
				}
				
				cellDAO.deleteByGFCID(registerDataCustomerForm.getRegisterConsumer().getNumberGFCID());
				if(registerDataCustomerForm.getRegisterConsumer().getTelephoneList()!=null && !registerDataCustomerForm.getRegisterConsumer().getTelephoneList().isEmpty()){
					for (TelephoneVO telVO : registerDataCustomerForm.getRegisterConsumer().getTelephoneList()) {
						
						if(registerDataCustomerForm.getLoggedUser()!=null){
							telVO.setLastAuthUser(registerDataCustomerForm.getLoggedUser().getUserID());
						}
						if(now!=null){
							telVO.setLastAuthDate(now);
						}
						cellDAO.insert(telVO);
					}
				}
				
				mailDAO.deleteByGFCID(registerDataCustomerForm.getRegisterConsumer().getNumberGFCID());
				if(registerDataCustomerForm.getRegisterConsumer().getMailList()!=null && !registerDataCustomerForm.getRegisterConsumer().getMailList().isEmpty()){
					for (MailVO mailVO : registerDataCustomerForm.getRegisterConsumer().getMailList()) {
						if(registerDataCustomerForm.getLoggedUser()!=null){
							mailVO.setLastAuthUser(registerDataCustomerForm.getLoggedUser().getUserID());
						}
						if(now!=null){
							mailVO.setLastAuthDate(now);
						}
						mailDAO.insert(mailVO);
					}
				}
				
				countryDAO.deleteByGFCID(registerDataCustomerForm.getRegisterConsumer().getNumberGFCID());
				if(registerDataCustomerForm.getRegisterConsumer().getCitizenshipList()!=null && !registerDataCustomerForm.getRegisterConsumer().getCitizenshipList().isEmpty()){
					for (CustomerCountryVO citizenshipVO : registerDataCustomerForm.getRegisterConsumer().getCitizenshipList()) {
						if(registerDataCustomerForm.getLoggedUser()!=null){
							citizenshipVO.setLastAuthUser(registerDataCustomerForm.getLoggedUser().getUserID());
						}
						if(now!=null){
							citizenshipVO.setLastAuthDate(now);
						}
						countryDAO.insert(citizenshipVO);
					}
				}
				if(registerDataCustomerForm.getRegisterConsumer().getFiscalResidenceList()!=null && !registerDataCustomerForm.getRegisterConsumer().getFiscalResidenceList().isEmpty()){
					for (CustomerCountryVO fiscalResidenceVO : registerDataCustomerForm.getRegisterConsumer().getFiscalResidenceList()) {
						if(registerDataCustomerForm.getLoggedUser()!=null){
							fiscalResidenceVO.setLastAuthUser(registerDataCustomerForm.getLoggedUser().getUserID());
						}
						if(now!=null){
							fiscalResidenceVO.setLastAuthDate(now);
						}
						countryDAO.insert(fiscalResidenceVO);
					}
				}

				// 3 - Remove movimento		
				TplPrvtCustAddressMovDAO addresMovDAO = new TplPrvtCustAddressMovDAO();	
				TplPrvtCustCellMovDAO cellMovDAO = new TplPrvtCustCellMovDAO();	
				TplPrvtCustMailMovDAO mailMovDAO = new TplPrvtCustMailMovDAO();	
				TplPrvtCustCountryMovDAO countryMovDAO = new TplPrvtCustCountryMovDAO();
				TplPrvtErEmMovDAO tplPrvtErEmMovDAO = new TplPrvtErEmMovDAO();
				
				customerMovDAO.delete(registerDataCustomerForm.getRegisterConsumer().getNumberGFCID());
				addresMovDAO.deleteByGFCID(registerDataCustomerForm.getRegisterConsumer().getNumberGFCID());
				cellMovDAO.deleteByGFCID(registerDataCustomerForm.getRegisterConsumer().getNumberGFCID());
				mailMovDAO.deleteByGFCID(registerDataCustomerForm.getRegisterConsumer().getNumberGFCID());
				countryMovDAO.deleteByGFCID(registerDataCustomerForm.getRegisterConsumer().getNumberGFCID());
				
				if(!StringUtils.isBlank(registerDataCustomerForm.getRegisterConsumer().getNumberEM())){
					tplPrvtErEmMovDAO.deleteByEM(registerDataCustomerForm.getRegisterConsumer().getNumberEM());
				}
							
					commitTransaction( transaction );
				
				registerDataCustomerForm.addMessage(CentralApprovalForm.C_MESSAGE_SUCESS);
				registerDataCustomerForm.setOnlyView(true);
				registerDataCustomerForm.setFromApprove(true);
			} catch (Exception e) {
					rollbackTransaction(transaction);
				throw e;
			}
		}
	}
	
	public void reprove(RegisterDataCustomerForm registerDataCustomerForm) throws Exception {

		if (!registerDataCustomerForm.hasErrors()) {
				UserTransaction transaction = getUserTransaction();
			try {					
				beginTransaction(transaction);
				
				RegisterDataCustomerVO vo = registerDataCustomerForm.getRegisterConsumer();
				TplPrvtCustomerMovDAO customerMovDAO = new TplPrvtCustomerMovDAO();			
				TplPrvtCustAddressMovDAO addresMovDAO = new TplPrvtCustAddressMovDAO();	
				TplPrvtCustCellMovDAO cellMovDAO = new TplPrvtCustCellMovDAO();	
				TplPrvtCustMailMovDAO mailMovDAO = new TplPrvtCustMailMovDAO();	
				TplPrvtCustCountryMovDAO countryMovDAO = new TplPrvtCustCountryMovDAO();
				TplPrvtErEmMovDAO tplPrvtErEmMovDAO = new TplPrvtErEmMovDAO();
				
				customerMovDAO.delete(vo.getNumberGFCID());
				addresMovDAO.deleteByGFCID(vo.getNumberGFCID());
				cellMovDAO.deleteByGFCID(vo.getNumberGFCID());
				mailMovDAO.deleteByGFCID(vo.getNumberGFCID());
				countryMovDAO.deleteByGFCID(vo.getNumberGFCID());
				
				if(!StringUtils.isBlank(registerDataCustomerForm.getRegisterConsumer().getNumberEM())){
					tplPrvtErEmMovDAO.deleteByEM(registerDataCustomerForm.getRegisterConsumer().getNumberEM());
				}
				
				commitTransaction( transaction );
				
				registerDataCustomerForm.addMessage(CentralApprovalForm.C_MESSAGE_SUCESS);
				registerDataCustomerForm.setOnlyView(true);
				registerDataCustomerForm.setFromApprove(true);
			} catch (Exception e) {
				rollbackTransaction(transaction);
				throw e;
			}
		}
	}
}
