package com.citibank.newcpb.vo;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.enun.CustomerTypeEnum;
import com.citibank.newcpb.util.FormatUtils;

public class RegisterDataCustomerVO {
	
	//ID DO  REGISTRO
	private String cpfCnpj; // Numero do CPF ou CNPJ
	private String numberGFCID; // Numero do GFCID - origem AMC
	
	// Informações Cliente (PF e PJ)	
	private String customerTypeDesc; // Tipo de cliente F (fisica) / J (juridica)
	private String customerType; // Tipo de cliente F (fisica) / J (juridica)
	private String name; // Nome completo - origem AMC
	private String numberEM; // Numero do EM gerado no Onnesource
	private String nameBanker;
	
	// Informações Cliente PF		
	private String customerStatus;	// status do cliente A (Ativo) / E (Encerrado)
	private String customerTitulo;	// titulo do cliente ....
	private String birthDate; // Data de Nascimento
	private String gender; // Sexo do cliente F/ M
	private String filiation1; // Filiacao do cliente
	private String filiation2; // Filiacao do cliente
	private String civilState; // Codigo do estado civil (TPL_PRVT_DMN_CIVIL_STAT)
	private Integer numberDependents; // Numero de dependentes
	private String spouseName; // Nome do Conjuge
	private String countryBirth; // Pais de nascimento (TPL_PRVT_DMN_CTRY  ) (PF) e Codigo do  pais de constituicao da empresa (TPL_PRVT_DMN_CTRY  ) (PJ)
	private String placeOfBirth; // Cidade de nascimento
	private String ufPlaceOfBirth; // Estado de nascimento (TPL_PRVT_DMN_UF)
	private String identityDocument; // Documento de identidade
	private String emitType; // Sequencial do tipo de orgao emissor (tabela TPL_PRVT_DMN_EMIT_TYPE)
	private String emitDocumentUF; // Estado do documento de identidade (TPL_PRVT_DMN_UF) 
	private String emitDocumentDate; // Data de emissao do documento de identidade
	private String occupation; // Profissao 
	private String occupationNature; // Codigo da natureza da ocupacao (TPL_PRVT_DMN_OCCUP_NATR)
	private String declaredIncome; // Valor da renda declarada
	private String declaredHeritage; // Valor do patrimonio delcarado
	private Boolean isEmployee; // Indicador de funcionario. S (funcionario) / N (nao funcionario)
	private Boolean isDeceased; // Indicador de cliente falecido. S/N
	private String SOEIDBankerNumber; // SOEID do Officer
	private String SOEIDBankerName;
	private Boolean haveGreenCard; // Indicador se o cliente e portador de green card ou ssn. S (green card) / N (social security nbr)
	private String socialSecurityNumber; // Numero do Green Card ou SSN
	private Boolean exemptIR; // Indica se cliente ou empresa tem isencao de imposto de renda. S (isento) / N (nao isento)	
	
	// Informações Cliente PJ	
	private String activityMain; // Codigo da atividade principal da empresa (TPL_PRVT_DMN_CNAE)
	private String constType; // Codigo do tipo de empresa (TPL_PRVT_DMN_CO_TYPE)
	private String naicNumber; // Codigo NAIC (North American Industry Classification. TPL_PRVT_DMN_NAIC) - Origem AMC
	private String sicNumber; // Codigo SIC (Standard Industrial Classification. TPL_PRVT_DMN_SIC ) - Origem AMC
	private String foundationDate; // Data de fundacao da empresa
	private String countryConstitution; // Pais de Constituiçao
	private String averageMonthBilling; // Valor do faturamento mensal
	private String admName; // Nome do administrador da empresa 
	private String admCpf; // CPF do administrador da empresa
	private Boolean hasFlexAccount;	// Indicador de conta no Flex DDA. S / N
	
	// Classificação Citi
//	private String numberER;
//	private String customerRoleRelationship;
	
	public ErEmVO er_em;
	private Boolean isSensitive; // Indicador de cliente sensível. S/N 
	private String typeClass; // Tipo de figura publica S (senior public fig) / P (Prominent publc fig)
	private Boolean derogatoryInformation;	//Indicador de cliente derrogatorio.  S/N	
	
	private Date lastAuthDate; // Data e hora da última autorizacao
	private String lastAuthUser; // Usuario que realizou a ultima autorizacao
	private Date lastUpdDate; // Data e hora da última atualização
	private String lastUpdUser; // Usuario que realizou a ultima atualizacao
	private String recStatCode; // Status do Registro
	
	// Endereço
	private AddressVO residentialAddress;
	private AddressVO businessAddress;
	private AddressVO otherAddress;
	private AddressVO headOfficeAddress;
	
	//FATCA / CRS
	
	private Boolean isFatca; //	Indicador de Fatca S/N	
	private Boolean isCrs; //	Indicador de CRS S/N	
	
	private String formType; //	Tipo de formulario (w8) / 9 (w9)
	private String signatureDateFatca;
	private String signatureDateCrs;
	
	
	private Boolean custFatcaPjInUs;
	private Boolean custFatcaPjOutUs;
	private Boolean custFatcaPjOwnrUs;
	
	private Boolean custCrsPjAddrOutUs;
	private Boolean custCrsPjEnfLiab;
	private Boolean custCrsPjInvstOut;
	
	
	private String custCreateDate;
	private String custCreateUser;
	
	private Boolean isAnnualReview;
	private Date lastAnnualReviewDate;
	
	private String custCreateDateAndUser;
	private String lastAuthDateFormatedDDMMYYYYAndUser;
	


//	Indicador do tipo de w8. 1 (codigo da atividade) / 2 (participacao acionaria)
//	Data e hora de criação do cliente na PCL 
//	Usuario que autorizou a criação do cliente
//	Data da última revisão cadastral
	

	// Telefones
	private ArrayList<TelephoneVO> telephoneList;
	
	// Emails
	private ArrayList<MailVO> mailList;
	
	// Cidadanias
	private ArrayList<CustomerCountryVO> citizenshipList;
	
	// Residencias Fiscal
	private ArrayList<CustomerCountryVO> fiscalResidenceList;	
	
	private TableTypeEnum tableOrigin;
	
	public Date getLastAuthDate() {
		return lastAuthDate;
	}
	
	
	public String getLastAuthDateFormatedDDMMYYYY() {
		if(lastAuthDate!=null){
			return FormatUtils.dateToString(lastAuthDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
		}else{
			return "";
		}
	}
	
	
	public void setLastAuthDate(Date lastAuthDate) {
		this.lastAuthDate = lastAuthDate;
	}
	public String getLastAuthUser() {
		return lastAuthUser;
	}
	public void setLastAuthUser(String lastAuthUser) {
		this.lastAuthUser = lastAuthUser;
	}
	public Date getLastUpdDate() {
		return lastUpdDate;
	}
	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}
	public String getLastUpdDateFormatedSafe() {
		if(lastUpdDate!=null){
			return FormatUtils.dateToString(lastUpdDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY_HHMMSS);
		}else{
			return "";
		}
	}
	
	public String getLastUpdDateFormatedDDMMYYYY() {
		if(lastUpdDate!=null){
			return FormatUtils.dateToString(lastUpdDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
		}else{
			return "";
		}
	}
	
	public String getLastUpdUser() {
		return lastUpdUser;
	}	
	
	public void setLastUpdUser(String lastUpdUser) {
		this.lastUpdUser = lastUpdUser;
	}
	public String getLastUpdUserSafe() {
		if(lastUpdUser!=null){
			return lastUpdUser;
		}else{
			return "";
		}
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumberEM() {
		return numberEM;
	}

	public void setNumberEM(String numberEM) {
		this.numberEM = numberEM;
	}

	public String getNumberGFCID() {
		return numberGFCID;
	}

	public void setNumberGFCID(String numberGFCID) {
		this.numberGFCID = numberGFCID;
	}

	public String getNameBanker() {
		return nameBanker;
	}

	public void setNameBanker(String nameBanker) {
		this.nameBanker = nameBanker;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
	
	public String getCustomerTitulo() {
		return customerTitulo;
	}

	public void setCustomerTitulo(String customerTitulo) {
		this.customerTitulo = customerTitulo;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCivilState() {
		return civilState;
	}

	public void setCivilState(String civilState) {
		this.civilState = civilState;
	}

	public Integer getNumberDependents() {
		return numberDependents;
	}

	public void setNumberDependents(Integer numberDependents) {
		this.numberDependents = numberDependents;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getCountryBirth() {
		return countryBirth;
	}

	public void setCountryBirth(String countryBirth) {
		this.countryBirth = countryBirth;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getUfPlaceOfBirth() {
		return ufPlaceOfBirth;
	}

	public void setUfPlaceOfBirth(String ufPlaceOfBirth) {
		this.ufPlaceOfBirth = ufPlaceOfBirth;
	}

	public String getIdentityDocument() {
		return identityDocument;
	}

	public void setIdentityDocument(String identityDocument) {
		this.identityDocument = identityDocument;
	}

	public String getEmitType() {
		return emitType;
	}

	public void setEmitType(String emitType) {
		this.emitType = emitType;
	}

	public String getEmitDocumentUF() {
		return emitDocumentUF;
	}

	public void setEmitDocumentUF(String emitDocumentUF) {
		this.emitDocumentUF = emitDocumentUF;
	}

	public String getEmitDocumentDate() {
		return emitDocumentDate;
	}

	public void setEmitDocumentDate(String emitDocumentDate) {
		this.emitDocumentDate = emitDocumentDate;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getOccupationNature() {
		return occupationNature;
	}

	public void setOccupationNature(String occupationNature) {
		this.occupationNature = occupationNature;
	}

	public String getDeclaredIncome() {
		return declaredIncome;
	}

	public void setDeclaredIncome(String declaredIncome) {
		this.declaredIncome = declaredIncome;
	}

	public String getDeclaredHeritage() {
		return declaredHeritage;
	}

	public void setDeclaredHeritage(String declaredHeritage) {
		this.declaredHeritage = declaredHeritage;
	}

	public Boolean getIsEmployee() {
		return isEmployee;
	}

	public void setIsEmployee(Boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	public Boolean getIsDeceased() {
		return isDeceased;
	}

	public void setIsDeceased(Boolean isDeceased) {
		this.isDeceased = isDeceased;
	}

	public String getSOEIDBankerNumber() {
		return SOEIDBankerNumber;
	}

	public void setSOEIDBankerNumber(String sOEIDBankerNumber) {
		SOEIDBankerNumber = sOEIDBankerNumber;
	}

	public String getSOEIDBankerName() {
		return SOEIDBankerName;
	}

	public void setSOEIDBankerName(String sOEIDBankerName) {
		SOEIDBankerName = sOEIDBankerName;
	}

	public Boolean getHaveGreenCard() {
		return haveGreenCard;
	}

	public void setHaveGreenCard(Boolean haveGreenCard) {
		this.haveGreenCard = haveGreenCard;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public Boolean getExemptIR() {
		return exemptIR;
	}

	public void setExemptIR(Boolean exemptIR) {
		this.exemptIR = exemptIR;
	}

	public String getActivityMain() {
		return activityMain;
	}

	public void setActivityMain(String activityMain) {
		this.activityMain = activityMain;
	}

	public String getConstType() {
		return constType;
	}

	public void setConstType(String constType) {
		this.constType = constType;
	}

	public String getNaicNumber() {
		return naicNumber;
	}

	public void setNaicNumber(String naicNumber) {
		this.naicNumber = naicNumber;
	}

	public String getSicNumber() {
		return sicNumber;
	}

	public void setSicNumber(String sicNumber) {
		this.sicNumber = sicNumber;
	}

	public String getFoundationDate() {
		return foundationDate;
	}

	public void setFoundationDate(String foundationDate) {
		this.foundationDate = foundationDate;
	}

	public String getAverageMonthBilling() {
		return averageMonthBilling;
	}

	public void setAverageMonthBilling(String averageMonthBilling) {
		this.averageMonthBilling = averageMonthBilling;
	}

	public String getAdmName() {
		return admName;
	}

	public void setAdmName(String admName) {
		this.admName = admName;
	}

	public String getAdmCpf() {
		return admCpf;
	}

	public void setAdmCpf(String admCpf) {
		this.admCpf = admCpf;
	}

	public Boolean getHasFlexAccount() {
		return hasFlexAccount;
	}

	public void setHasFlexAccount(Boolean hasFlexAccount) {
		this.hasFlexAccount = hasFlexAccount;
	}

	public Boolean getIsSensitive() {
		return isSensitive;
	}

	public void setIsSensitive(Boolean isSensitive) {
		this.isSensitive = isSensitive;
	}

	public String getTypeClass() {
		return typeClass;
	}

	public void setTypeClass(String typeClass) {
		this.typeClass = typeClass;
	}

	public Boolean getDerogatoryInformation() {
		return derogatoryInformation;
	}

	public void setDerogatoryInformation(Boolean derogatoryInformation) {
		this.derogatoryInformation = derogatoryInformation;
	}

	public AddressVO getResidentialAddress() {
		if(residentialAddress ==null){
			residentialAddress = new AddressVO();
		}
		return residentialAddress;
	}

	public void setResidentialAddress(AddressVO residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	public AddressVO getBusinessAddress() {
		if(businessAddress ==null){
			businessAddress = new AddressVO();
		}
		return businessAddress;
	}

	public void setBusinessAddress(AddressVO businessAddress) {
		this.businessAddress = businessAddress;
	}

	public AddressVO getOtherAddress() {		
		if(otherAddress ==null){
			otherAddress = new AddressVO();
		}
		return otherAddress;
	}

	public void setOtherAddress(AddressVO otherAddress) {
		this.otherAddress = otherAddress;
	}

	public AddressVO getHeadOfficeAddress() {
		if(headOfficeAddress ==null){
			headOfficeAddress = new AddressVO();
		}
		return headOfficeAddress;
	}

	public void setHeadOfficeAddress(AddressVO headOfficeAddress) {
		this.headOfficeAddress = headOfficeAddress;
	}

	public ArrayList<TelephoneVO> getTelephoneList() {
		return telephoneList;
	}

	public void setTelephoneList(ArrayList<TelephoneVO> telephoneList) {
		this.telephoneList = telephoneList;
	}

	public ArrayList<MailVO> getMailList() {
		return mailList;
	}

	public void setMailList(ArrayList<MailVO> mailList) {
		this.mailList = mailList;
	}

	public ArrayList<CustomerCountryVO> getCitizenshipList() {
		return citizenshipList;
	}

	public void setCitizenshipList(ArrayList<CustomerCountryVO> citizenshipList) {
		this.citizenshipList = citizenshipList;
	}

	public TableTypeEnum getTableOrigin() {
		return tableOrigin;
	}

	public void setTableOrigin(TableTypeEnum tableOrigin) {
		this.tableOrigin = tableOrigin;
	}

	public ArrayList<CustomerCountryVO> getFiscalResidenceList() {
		return fiscalResidenceList;
	}

	public void setFiscalResidenceList(ArrayList<CustomerCountryVO> fiscalResidenceList) {
		this.fiscalResidenceList = fiscalResidenceList;
	}
	public Boolean getIsFatca() {
		return isFatca;
	}
	public void setIsFatca(Boolean isFatca) {
		this.isFatca = isFatca;
	}
	public Boolean getIsCrs() {
		return isCrs;
	}
	public void setIsCrs(Boolean isCrs) {
		this.isCrs = isCrs;
	}
	public String getFormType() {
		return formType;
	}
	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getRecStatCode() {
		return recStatCode;
	}
	public void setRecStatCode(String recStatCode) {
		this.recStatCode = recStatCode;
	}
	public String getRecStatCodeText() {
		if(recStatCode!=null){
			if(recStatCode.equals("A")){
				return "Alteração";
			}else if(recStatCode.equals("U")){
				return "Inclusão";
			}else if(recStatCode.equals("I")){
				return "Exclusão";
			}else{
				return "";
			}			
		}else{
			return "";
		}
	}
	
	public String getCustomerTypeDesc() {
		if(!StringUtils.isBlank(customerType)){
			if(CustomerTypeEnum.fromValue(customerType)!=null){
				customerTypeDesc = CustomerTypeEnum.fromValue(customerType).getDesc();
			}
			 
		}
		return customerTypeDesc;
	}
	public void setCustomerTypeDesc(String customerTypeDesc) {
		this.customerTypeDesc = customerTypeDesc;
	}
	


	public String getCustCreateDate() {
		return custCreateDate;
	}
	public void setCustCreateDate(String custCreateDate) {
		this.custCreateDate = custCreateDate;
	}
	public String getCustCreateUser() {
		return custCreateUser;
	}
	public void setCustCreateUser(String custCreateUser) {
		this.custCreateUser = custCreateUser;
	}
	public String getCountryConstitution() {
		return countryConstitution;
	}
	public void setCountryConstitution(String countryConstitution) {
		this.countryConstitution = countryConstitution;
	}
	public Boolean getIsAnnualReview() {
		return isAnnualReview;
	}

	public void setIsAnnualReview(Boolean isAnnualReview) {
		this.isAnnualReview = isAnnualReview;
	}
	public ErEmVO getEr_em() {
		
		if(er_em ==null){
			er_em = new ErEmVO();
		}
		
		return er_em;
	}
	
	public void setEr_em(ErEmVO er_em) {
		this.er_em = er_em;
	}
	public Date getLastAnnualReviewDate() {
		return lastAnnualReviewDate;
	}
	public void setLastAnnualReviewDate(Date lastAnnualReviewDate) {
		this.lastAnnualReviewDate = lastAnnualReviewDate;
	}
	
	public String getLastAnnualReviewDateFormatedDDMMYYYY() {
		if(lastAnnualReviewDate!=null){
			return FormatUtils.dateToString(lastAnnualReviewDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
		}else{
			return "";
		}
	}
	

	public String getCustCreateDateAndUser() {
		
		if(!StringUtils.isBlank(getCustCreateDate()) || !StringUtils.isBlank(getCustCreateUser())){
			return getCustCreateDate() + " - " +getCustCreateUser();
		}else{
			return "";
		}	
	}


	public void setCustCreateDateAndUser(String custCreateDateAndUser) {
		this.custCreateDateAndUser = custCreateDateAndUser;
	}


	public String getLastAuthDateFormatedDDMMYYYYAndUser() {
		if(!StringUtils.isBlank(getLastAuthDateFormatedDDMMYYYY()) || !StringUtils.isBlank(getLastAuthUser()) || !StringUtils.isBlank(getLastUpdUserSafe())){
			return getLastAuthDateFormatedDDMMYYYY() + " - " + getLastUpdUserSafe() + " - " + getLastAuthUser();
		}else{
			return "";
		}
		
	}


	public void setLastAuthDateFormatedDDMMYYYYAndUser(
			String lastAuthDateFormatedDDMMYYYYAndUser) {
		this.lastAuthDateFormatedDDMMYYYYAndUser = lastAuthDateFormatedDDMMYYYYAndUser;
	}


	public String getFiliation1() {
		return filiation1;
	}


	public void setFiliation1(String filiation1) {
		this.filiation1 = filiation1;
	}


	public String getFiliation2() {
		return filiation2;
	}


	public void setFiliation2(String filiation2) {
		this.filiation2 = filiation2;
	}


	public String getSignatureDateFatca() {
		return signatureDateFatca;
	}


	public void setSignatureDateFatca(String signatureDateFatca) {
		this.signatureDateFatca = signatureDateFatca;
	}


	public String getSignatureDateCrs() {
		return signatureDateCrs;
	}


	public void setSignatureDateCrs(String signatureDateCrs) {
		this.signatureDateCrs = signatureDateCrs;
	}



	public Boolean getCustFatcaPjInUs() {
		return custFatcaPjInUs;
	}


	public void setCustFatcaPjInUs(Boolean custFatcaPjInUs) {
		this.custFatcaPjInUs = custFatcaPjInUs;
	}


	public Boolean getCustFatcaPjOutUs() {
		return custFatcaPjOutUs;
	}


	public void setCustFatcaPjOutUs(Boolean custFatcaPjOutUs) {
		this.custFatcaPjOutUs = custFatcaPjOutUs;
	}


	public Boolean getCustFatcaPjOwnrUs() {
		return custFatcaPjOwnrUs;
	}


	public void setCustFatcaPjOwnrUs(Boolean custFatcaPjOwnrUs) {
		this.custFatcaPjOwnrUs = custFatcaPjOwnrUs;
	}


	public Boolean getCustCrsPjAddrOutUs() {
		return custCrsPjAddrOutUs;
	}


	public void setCustCrsPjAddrOutUs(Boolean custCrsPjAddrOutUs) {
		this.custCrsPjAddrOutUs = custCrsPjAddrOutUs;
	}


	public Boolean getCustCrsPjEnfLiab() {
		return custCrsPjEnfLiab;
	}


	public void setCustCrsPjEnfLiab(Boolean custCrsPjEnfLiab) {
		this.custCrsPjEnfLiab = custCrsPjEnfLiab;
	}


	public Boolean getCustCrsPjInvstOut() {
		return custCrsPjInvstOut;
	}


	public void setCustCrsPjInvstOut(Boolean custCrsPjInvstOut) {
		this.custCrsPjInvstOut = custCrsPjInvstOut;
	}


	public ArrayList<String> equals(RegisterDataCustomerVO other) {

		ArrayList<String> idDiffList = new ArrayList<String>();
		
		if (other != null){
			
			if (this.cpfCnpj != null && other.cpfCnpj != null) {
				if (!this.cpfCnpj.equals(other.cpfCnpj)) {
					idDiffList.add("cpfCnpj");
				}
			} else if ((this.cpfCnpj == null && other.cpfCnpj != null) || (other.cpfCnpj == null && this.cpfCnpj != null)) {
				idDiffList.add("cpfCnpj");
			}
			
			if (this.numberGFCID != null && other.numberGFCID != null) {
				if (!this.numberGFCID.equals(other.numberGFCID)) {
					idDiffList.add("numberGFCID");
				}
			} else if ((this.numberGFCID == null && other.numberGFCID != null) || (other.numberGFCID == null && this.numberGFCID != null)) {
				idDiffList.add("numberGFCID");
			}
			
			if (this.customerType != null && other.customerType != null) {
				if (!this.customerType.equals(other.customerType)) {
					idDiffList.add("customerType");
				}
			} else if ((this.customerType == null && other.customerType != null) || (other.customerType == null && this.customerType != null)) {
				idDiffList.add("customerType");
			}
			
			if (this.name != null && other.name != null) {
				if (!this.name.equals(other.name)) {
					idDiffList.add("name");
				}
			} else if ((this.name == null && other.name != null) || (other.name == null && this.name != null)) {
				idDiffList.add("name");
			}
			
			if (this.numberEM != null && other.numberEM != null) {
				if (!this.numberEM.equals(other.numberEM)) {
					idDiffList.add("numberEM");
				}
			} else if ((this.numberEM == null && other.numberEM != null) || (other.numberEM == null && this.numberEM != null)) {
				idDiffList.add("numberEM");
			}
			
			if (this.nameBanker != null && other.nameBanker != null) {
				if (!this.nameBanker.equals(other.nameBanker)) {
					idDiffList.add("nameBanker");
				}
			} else if ((this.nameBanker == null && other.nameBanker != null) || (other.nameBanker == null && this.nameBanker != null)) {
				idDiffList.add("nameBanker");
			}
			
			if (this.customerStatus != null && other.customerStatus != null) {
				if (!this.customerStatus.equals(other.customerStatus)) {
					idDiffList.add("customerStatus");
				}
			} else if ((this.customerStatus == null && other.customerStatus != null) || (other.customerStatus == null && this.customerStatus != null)) {
				idDiffList.add("customerStatus");
			}
			
			if (this.birthDate != null && other.birthDate != null) {
				if (!this.birthDate.equals(other.birthDate)) {
					idDiffList.add("birthDate");
				}
			} else if ((this.birthDate == null && other.birthDate != null) || (other.birthDate == null && this.birthDate != null)) {
				idDiffList.add("birthDate");
			}
			
			if (this.gender != null && other.gender != null) {
				if (!this.gender.equals(other.gender)) {
					idDiffList.add("gender");
				}
			} else if ((this.gender == null && other.gender != null) || (other.gender == null && this.gender != null)) {
				idDiffList.add("gender");
			}
			
			if (this.filiation1 != null && other.filiation1 != null) {
				if (!this.filiation1.equals(other.filiation1)) {
					idDiffList.add("filiation1");
				}
			} else if ((this.filiation1 == null && other.filiation1 != null) || (other.filiation1 == null && this.filiation1 != null)) {
				idDiffList.add("filiation1");
			}
			
			if (this.filiation2 != null && other.filiation2 != null) {
				if (!this.filiation2.equals(other.filiation2)) {
					idDiffList.add("filiation2");
				}
			} else if ((this.filiation2 == null && other.filiation2 != null) || (other.filiation2 == null && this.filiation2 != null)) {
				idDiffList.add("filiation2");
			}
			
			if (this.civilState != null && other.civilState != null) {
				if (!this.civilState.equals(other.civilState)) {
					idDiffList.add("civilState");
				}
			} else if ((this.civilState == null && other.civilState != null) || (other.civilState == null && this.civilState != null)) {
				idDiffList.add("civilState");
			}
			
			if (this.numberDependents != null && other.numberDependents != null) {
				if (!this.numberDependents.equals(other.numberDependents)) {
					idDiffList.add("numberDependents");
				}
			} else if ((this.numberDependents == null && other.numberDependents != null) || (other.numberDependents == null && this.numberDependents != null)) {
				idDiffList.add("numberDependents");
			}
			
			if (this.spouseName != null && other.spouseName != null) {
				if (!this.spouseName.equals(other.spouseName)) {
					idDiffList.add("spouseName");
				}
			} else if ((this.spouseName == null && other.spouseName != null) || (other.spouseName == null && this.spouseName != null)) {
				idDiffList.add("spouseName");
			}
			
			if (this.countryBirth != null && other.countryBirth != null) {
				if (!this.countryBirth.equals(other.countryBirth)) {
					idDiffList.add("countryBirth");
				}
			} else if ((this.countryBirth == null && other.countryBirth != null) || (other.countryBirth == null && this.countryBirth != null)) {
				idDiffList.add("countryBirth");
			}
			
			if (this.placeOfBirth != null && other.placeOfBirth != null) {
				if (!this.placeOfBirth.equals(other.placeOfBirth)) {
					idDiffList.add("");
				}
			} else if ((this.placeOfBirth == null && other.placeOfBirth != null) || (other.placeOfBirth == null && this.placeOfBirth != null)) {
				idDiffList.add("placeOfBirth");
			}
			
			if (this.ufPlaceOfBirth != null && other.ufPlaceOfBirth != null) {
				if (!this.ufPlaceOfBirth.equals(other.ufPlaceOfBirth)) {
					idDiffList.add("ufPlaceOfBirth");
				}
			} else if ((this.ufPlaceOfBirth == null && other.ufPlaceOfBirth != null) || (other.ufPlaceOfBirth == null && this.ufPlaceOfBirth != null)) {
				idDiffList.add("ufPlaceOfBirth");
			}
			
			if (this.identityDocument != null && other.identityDocument != null) {
				if (!this.identityDocument.equals(other.identityDocument)) {
					idDiffList.add("identityDocument");
				}
			} else if ((this.identityDocument == null && other.identityDocument != null) || (other.identityDocument == null && this.identityDocument != null)) {
				idDiffList.add("identityDocument");
			}
			
			if (this.emitType != null && other.emitType != null) {
				if (!this.emitType.equals(other.emitType)) {
					idDiffList.add("emitType");
				}
			} else if ((this.emitType == null && other.emitType != null) || (other.emitType == null && this.emitType != null)) {
				idDiffList.add("emitType");
			}
			
			if (this.emitDocumentUF != null && other.emitDocumentUF != null) {
				if (!this.emitDocumentUF.equals(other.emitDocumentUF)) {
					idDiffList.add("emitDocumentUF");
				}
			} else if ((this.emitDocumentUF == null && other.emitDocumentUF != null) || (other.emitDocumentUF == null && this.emitDocumentUF != null)) {
				idDiffList.add("emitDocumentUF");
			}
			
			if (this.emitDocumentDate != null && other.emitDocumentDate != null) {
				if (!this.emitDocumentDate.equals(other.emitDocumentDate)) {
					idDiffList.add("emitDocumentDate");
				}
			} else if ((this.emitDocumentDate == null && other.emitDocumentDate != null) || (other.emitDocumentDate == null && this.emitDocumentDate != null)) {
				idDiffList.add("emitDocumentDate");
			}
			
			if (this.occupation != null && other.occupation != null) {
				if (!this.occupation.equals(other.occupation)) {
					idDiffList.add("occupation");
				}
			} else if ((this.occupation == null && other.occupation != null) || (other.occupation == null && this.occupation != null)) {
				idDiffList.add("occupation");
			}
			
			if (this.occupationNature != null && other.occupationNature != null) {
				if (!this.occupationNature.equals(other.occupationNature)) {
					idDiffList.add("occupationNature");
				}
			} else if ((this.occupationNature == null && other.occupationNature != null) || (other.occupationNature == null && this.occupationNature != null)) {
				idDiffList.add("occupationNature");
			}
			
			if (this.declaredIncome != null && other.declaredIncome != null) {
				if (!this.declaredIncome.equals(other.declaredIncome)) {
					idDiffList.add("declaredIncome");
				}
			} else if ((this.declaredIncome == null && other.declaredIncome != null) || (other.declaredIncome == null && this.declaredIncome != null)) {
				idDiffList.add("declaredIncome");
			}
			
			if (this.declaredHeritage != null && other.declaredHeritage != null) {
				if (!this.declaredHeritage.equals(other.declaredHeritage)) {
					idDiffList.add("declaredHeritage");
				}
			} else if ((this.declaredHeritage == null && other.declaredHeritage != null) || (other.declaredHeritage == null && this.declaredHeritage != null)) {
				idDiffList.add("declaredHeritage");
			}
			
			if (this.isEmployee != null && other.isEmployee != null) {
				if (!this.isEmployee.equals(other.isEmployee)) {
					idDiffList.add("employeeDiv");
				}
			} else if ((this.isEmployee == null && other.isEmployee != null) || (other.isEmployee == null && this.isEmployee != null)) {
				idDiffList.add("employeeDiv");
			}
			
			if (this.isDeceased != null && other.isDeceased != null) {
				if (!this.isDeceased.equals(other.isDeceased)) {
					idDiffList.add("deceasedDiv");
				}
			} else if ((this.isDeceased == null && other.isDeceased != null) || (other.isDeceased == null && this.isDeceased != null)) {
				idDiffList.add("deceasedDiv");
			}
			
			if (this.SOEIDBankerNumber != null && other.SOEIDBankerNumber != null) {
				if (!this.SOEIDBankerNumber.equals(other.SOEIDBankerNumber)) {
					idDiffList.add("SOEIDBankerNumber");
				}
			} else if ((this.SOEIDBankerNumber == null && other.SOEIDBankerNumber != null) || (other.SOEIDBankerNumber == null && this.SOEIDBankerNumber != null)) {
				idDiffList.add("SOEIDBankerNumber");
			}
			
			if (this.SOEIDBankerName != null && other.SOEIDBankerName != null) {
				if (!this.SOEIDBankerName.equals(other.SOEIDBankerName)) {
					idDiffList.add("SOEIDBankerName");
				}
			} else if ((this.SOEIDBankerName == null && other.SOEIDBankerName != null) || (other.SOEIDBankerName == null && this.SOEIDBankerName != null)) {
				idDiffList.add("SOEIDBankerName");
			}
			
			if (this.haveGreenCard != null && other.haveGreenCard != null) {
				if (!this.haveGreenCard.equals(other.haveGreenCard)) {
					idDiffList.add("haveGreenCardDiv");
				}
			} else if ((this.haveGreenCard == null && other.haveGreenCard != null) || (other.haveGreenCard == null && this.haveGreenCard != null)) {
				idDiffList.add("haveGreenCardDiv");
			}
			
			if (this.socialSecurityNumber != null && other.socialSecurityNumber != null) {
				if (!this.socialSecurityNumber.equals(other.socialSecurityNumber)) {
					idDiffList.add("socialSecurityNumber");
				}
			} else if ((this.socialSecurityNumber == null && other.socialSecurityNumber != null) || (other.socialSecurityNumber == null && this.socialSecurityNumber != null)) {
				idDiffList.add("socialSecurityNumber");
			}
			
			if (this.exemptIR != null && other.exemptIR != null) {
				if (!this.exemptIR.equals(other.exemptIR)) {
					idDiffList.add("exemptIRDiv");
				}
			} else if ((this.exemptIR == null && other.exemptIR != null) || (other.exemptIR == null && this.exemptIR != null)) {
				idDiffList.add("exemptIRDiv");
			}
			
			if (this.activityMain != null && other.activityMain != null) {
				if (!this.activityMain.equals(other.activityMain)) {
					idDiffList.add("activityMain");
				}
			} else if ((this.activityMain == null && other.activityMain != null) || (other.activityMain == null && this.activityMain != null)) {
				idDiffList.add("activityMain");
			}
			
			if (this.constType != null && other.constType != null) {
				if (!this.constType.equals(other.constType)) {
					idDiffList.add("constType");
				}
			} else if ((this.constType == null && other.constType != null) || (other.constType == null && this.constType != null)) {
				idDiffList.add("constType");
			}
			
			if (this.naicNumber != null && other.naicNumber != null) {
				if (!this.naicNumber.equals(other.naicNumber)) {
					idDiffList.add("naicNumber");
				}
			} else if ((this.naicNumber == null && other.naicNumber != null) || (other.naicNumber == null && this.naicNumber != null)) {
				idDiffList.add("naicNumber");
			}
			
			if (this.sicNumber != null && other.sicNumber != null) {
				if (!this.sicNumber.equals(other.sicNumber)) {
					idDiffList.add("sicNumber");
				}
			} else if ((this.sicNumber == null && other.sicNumber != null) || (other.sicNumber == null && this.sicNumber != null)) {
				idDiffList.add("sicNumber");
			}
			
			if (this.foundationDate != null && other.foundationDate != null) {
				if (!this.foundationDate.equals(other.foundationDate)) {
					idDiffList.add("foundationDate");
				}
			} else if ((this.foundationDate == null && other.foundationDate != null) || (other.foundationDate == null && this.foundationDate != null)) {
				idDiffList.add("foundationDate");
			}
			
			if (this.averageMonthBilling != null && other.averageMonthBilling != null) {
				if (!this.averageMonthBilling.equals(other.averageMonthBilling)) {
					idDiffList.add("averageMonthBilling");
				}
			} else if ((this.averageMonthBilling == null && other.averageMonthBilling != null) || (other.averageMonthBilling == null && this.averageMonthBilling != null)) {
				idDiffList.add("averageMonthBilling");
			}
			
			if (this.admName != null && other.admName != null) {
				if (!this.admName.equals(other.admName)) {
					idDiffList.add("admName");
				}
			} else if ((this.admName == null && other.admName != null) || (other.admName == null && this.admName != null)) {
				idDiffList.add("admName");
			}
			
			if (this.admCpf != null && other.admCpf != null) {
				if (!this.admCpf.equals(other.admCpf)) {
					idDiffList.add("admCpf");
				}
			} else if ((this.admCpf == null && other.admCpf != null) || (other.admCpf == null && this.admCpf != null)) {
				idDiffList.add("admCpf");
			}
			
			if (this.hasFlexAccount != null && other.hasFlexAccount != null) {
				if (!this.hasFlexAccount.equals(other.hasFlexAccount)) {
					idDiffList.add("hasFlexAccountDiv");
				}
			} else if ((this.hasFlexAccount == null && other.hasFlexAccount != null) || (other.hasFlexAccount == null && this.hasFlexAccount != null)) {
				idDiffList.add("hasFlexAccountDiv");
			}
			
			if (this.isSensitive != null && other.isSensitive != null) {
				if (!this.isSensitive.equals(other.isSensitive)) {
					idDiffList.add("sensitiveDiv");
				}
			} else if ((this.isSensitive == null && other.isSensitive != null) || (other.isSensitive == null && this.isSensitive != null)) {
				idDiffList.add("sensitiveDiv");
			}
			
			if (this.typeClass != null && other.typeClass != null) {
				if (!this.typeClass.equals(other.typeClass)) {
					idDiffList.add("typeClassDiv");
				}
			} else if ((this.typeClass == null && other.typeClass != null) || (other.typeClass == null && this.typeClass != null)) {
				idDiffList.add("typeClassDiv");
			}
			
			if (this.derogatoryInformation != null && other.derogatoryInformation != null) {
				if (!this.derogatoryInformation.equals(other.derogatoryInformation)) {
					idDiffList.add("derogatoryInformationDiv");
				}
			} else if ((this.derogatoryInformation == null && other.derogatoryInformation != null) || (other.derogatoryInformation == null && this.derogatoryInformation != null)) {
				idDiffList.add("derogatoryInformationDiv");
			}
			
			if (this.isFatca != null && other.isFatca != null) {
				if (!this.isFatca.equals(other.isFatca)) {
					idDiffList.add("isFatcaTr");
				}
			} else if ((this.isFatca == null && other.isFatca != null) || (other.isFatca == null && this.isFatca != null)) {
				idDiffList.add("isFatcaTr");
			}
			
			if (this.isCrs != null && other.isCrs != null) {
				if (!this.isCrs.equals(other.isCrs)) {
					idDiffList.add("isCrsTr");
				}
			} else if ((this.isCrs == null && other.isCrs != null) || (other.isCrs == null && this.isCrs != null)) {
				idDiffList.add("isCrsTr");
			}
			
			if (this.formType != null && other.formType != null) {
				if (!this.formType.equals(other.formType)) {
					idDiffList.add("formTypeDiv");
				}
			} else if ((this.formType == null && other.formType != null) || (other.formType == null && this.formType != null)) {
				idDiffList.add("formTypeDiv");
			}
			
			if (this.signatureDateFatca != null && other.signatureDateFatca != null) {
				if (!this.signatureDateFatca.equals(other.signatureDateFatca)) {
					idDiffList.add("signatureDateFatca");
				}
			} else if ((this.signatureDateFatca == null && other.signatureDateFatca != null) || (other.signatureDateFatca == null && this.signatureDateFatca != null)) {
				idDiffList.add("signatureDateFatca");
			}
			
			if (this.signatureDateCrs != null && other.signatureDateCrs != null) {
				if (!this.signatureDateCrs.equals(other.signatureDateCrs)) {
					idDiffList.add("signatureDateCrs");
				}
			} else if ((this.signatureDateCrs == null && other.signatureDateCrs != null) || (other.signatureDateCrs == null && this.signatureDateCrs != null)) {
				idDiffList.add("signatureDateCrs");
			}
			
			String residentialAddressType ="residentialAddress." ;
			String businessAddressType ="businessAddress." ;
			String otherAddressType = "otherAddress.";
			String headOfficeAddressType = "headOfficeAddress." ;
			
			
			if (this.residentialAddress != null && other.residentialAddress != null) {
				idDiffList.addAll(this.residentialAddress.equals(other.residentialAddress, residentialAddressType));
			} else if ((this.residentialAddress == null && other.residentialAddress != null) || (other.residentialAddress == null && this.residentialAddress != null)) {
				idDiffList.add(residentialAddressType+"street");
				idDiffList.add(residentialAddressType+"neighborhood");
				idDiffList.add(residentialAddressType+"city");
				idDiffList.add(residentialAddressType+"uf");
				idDiffList.add(residentialAddressType+"zipCode");
				idDiffList.add(residentialAddressType+"isCorrespondenceDiv");
			}
			
			if (this.businessAddress != null && other.businessAddress != null) {
				idDiffList.addAll(this.businessAddress.equals(other.businessAddress,businessAddressType));
			} else if ((this.businessAddress == null && other.businessAddress != null) || (other.businessAddress == null && this.businessAddress != null)) {
				idDiffList.add(businessAddressType+"street");
				idDiffList.add(businessAddressType+"neighborhood");
				idDiffList.add(businessAddressType+"city");
				idDiffList.add(businessAddressType+"uf");
				idDiffList.add(businessAddressType+"zipCode");
				idDiffList.add(businessAddressType+"isCorrespondenceDiv");
			}
			
			if (this.otherAddress != null && other.otherAddress != null) {
				idDiffList.addAll(this.otherAddress.equals(other.otherAddress, otherAddressType));
			} else if ((this.otherAddress == null && other.otherAddress != null) || (other.otherAddress == null && this.otherAddress != null)) {
				idDiffList.add(otherAddressType+"street");
				idDiffList.add(otherAddressType+"neighborhood");
				idDiffList.add(otherAddressType+"city");
				idDiffList.add(otherAddressType+"uf");
				idDiffList.add(otherAddressType+"zipCode");
				idDiffList.add(otherAddressType+"isCorrespondenceDiv");
			}
			
			if (this.headOfficeAddress != null && other.headOfficeAddress != null) {
				idDiffList.addAll(this.headOfficeAddress.equals(other.headOfficeAddress, headOfficeAddressType));
			} else if ((this.headOfficeAddress == null && other.headOfficeAddress != null) || (other.headOfficeAddress == null && this.headOfficeAddress != null)) {
				idDiffList.add(headOfficeAddressType+"street");
				idDiffList.add(headOfficeAddressType+"neighborhood");
				idDiffList.add(headOfficeAddressType+"city");
				idDiffList.add(headOfficeAddressType+"uf");
				idDiffList.add(headOfficeAddressType+"zipCode");
				idDiffList.add(headOfficeAddressType+"isCorrespondenceDiv");
			}
			
			if (this.telephoneList != null && other.telephoneList != null) {
				ArrayList<TelephoneVO> largerList = new ArrayList<TelephoneVO>();
				ArrayList<TelephoneVO> smallerList = new ArrayList<TelephoneVO>();
				if(this.telephoneList.size() > other.telephoneList.size()){
					smallerList = other.telephoneList;
					largerList	= this.telephoneList;
				}else{
					smallerList = this.telephoneList;
					largerList	= other.telephoneList;
				}
				for (TelephoneVO item : largerList) {
					boolean isNew = true;
					for (TelephoneVO itemOther : smallerList) {
						if(item.getPosition()!=null && itemOther.getPosition()!=null && item.getPosition().equals(itemOther.getPosition())){
							idDiffList.addAll(item.equals(itemOther));
							isNew = false;
						}
					}
					if(isNew){
						idDiffList.add("telephoneList["+item.getPositionIndex()+"].ddd");
						idDiffList.add("telephoneList["+item.getPositionIndex()+"].number");
					}
				}
				
			}else if(this.telephoneList == null && other.telephoneList != null){
				for (TelephoneVO item : other.telephoneList) {
					if(item.getPosition()!=null){
						idDiffList.add("telephoneList["+item.getPositionIndex()+"].ddd");
						idDiffList.add("telephoneList["+item.getPositionIndex()+"].number");
					}
				}
			}else if(other.telephoneList == null && this.telephoneList != null){
				for (TelephoneVO item : this.telephoneList) {
					if(item.getPosition()!=null){
						idDiffList.add("telephoneList["+item.getPositionIndex()+"].ddd");
						idDiffList.add("telephoneList["+item.getPositionIndex()+"].number");
					}
				}
			}
			
			if (this.mailList != null && other.mailList != null) {
				ArrayList<MailVO> largerList = new ArrayList<MailVO>();
				ArrayList<MailVO> smallerList = new ArrayList<MailVO>();
				if(this.mailList.size() > other.mailList.size()){
					smallerList = other.mailList;
					largerList	= this.mailList;
				}else{
					smallerList = this.mailList;
					largerList	= other.mailList;
				}
				for (MailVO item : largerList) {
					boolean isNew = true;
					for (MailVO itemOther : smallerList) {
						if(item.getPosition()!=null && itemOther.getPosition()!=null && item.getPosition().equals(itemOther.getPosition())){
							idDiffList.addAll(item.equals(itemOther));
							isNew = false;
						}
					}
					if(isNew){
						idDiffList.add("mailList["+item.getPositionIndex()+"].mail");
					}
				}
			}else if(this.mailList == null && other.mailList != null){
				for (MailVO item : other.mailList) {
					if(item.getPosition()!=null){
						idDiffList.add("mailList["+item.getPositionIndex()+"].mail");
					}
				}
			}else if(other.mailList == null && this.mailList != null){
				for (MailVO item : this.mailList) {
					if(item.getPosition()!=null){
						idDiffList.add("mailList["+item.getPositionIndex()+"].mail");
					}
				}
			}
			
			if (this.citizenshipList != null && other.citizenshipList != null) {
				ArrayList<CustomerCountryVO> largerList = new ArrayList<CustomerCountryVO>();
				ArrayList<CustomerCountryVO> smallerList = new ArrayList<CustomerCountryVO>();
				if(this.citizenshipList.size() > other.citizenshipList.size()){
					smallerList = other.citizenshipList;
					largerList	= this.citizenshipList;
				}else{
					smallerList = this.citizenshipList;
					largerList	= other.citizenshipList;
				}
				for (CustomerCountryVO item : largerList) {
					boolean isNew = true;
					for (CustomerCountryVO itemOther : smallerList) {
						if(item.getPosition()!=null && itemOther.getPosition()!=null && item.getPosition().equals(itemOther.getPosition())){
							idDiffList.addAll(item.equals(itemOther));
							isNew = false;
						}
					}
					if(isNew){
						idDiffList.add("citizenshipList["+item.getPositionIndex()+"].country");
					}
				}
			}else if(this.citizenshipList == null && other.citizenshipList != null){
				for (CustomerCountryVO item : other.citizenshipList) {
					if(item.getPosition()!=null){
						idDiffList.add("citizenshipList["+item.getPositionIndex()+"].country");
					}
				}
			}else if(other.citizenshipList == null && this.citizenshipList != null){
				for (CustomerCountryVO item : this.citizenshipList) {
					if(item.getPosition()!=null){
						idDiffList.add("citizenshipList["+item.getPositionIndex()+"].country");
					}
				}
			}
			
			if (this.fiscalResidenceList != null && other.fiscalResidenceList != null) {
				ArrayList<CustomerCountryVO> largerList = new ArrayList<CustomerCountryVO>();
				ArrayList<CustomerCountryVO> smallerList = new ArrayList<CustomerCountryVO>();
				if(this.fiscalResidenceList.size() > other.fiscalResidenceList.size()){
					smallerList = other.fiscalResidenceList;
					largerList	= this.fiscalResidenceList;
				}else{
					smallerList = this.fiscalResidenceList;
					largerList	= other.fiscalResidenceList;
				}
				for (CustomerCountryVO item : largerList) {
					boolean isNew = true;
					for (CustomerCountryVO itemOther : smallerList) {
						if(item.getPosition()!=null && itemOther.getPosition()!=null && item.getPosition().equals(itemOther.getPosition())){
							idDiffList.addAll(item.equals(itemOther));
							isNew = false;
						}
					}
					if(isNew){
						idDiffList.add("fiscalResidenceList["+item.getPositionIndex()+"].country");
					}
				}
			}else if(this.fiscalResidenceList == null && other.fiscalResidenceList != null){
				for (CustomerCountryVO item : other.fiscalResidenceList) {
					if(item.getPosition()!=null){
						idDiffList.add("fiscalResidenceList["+item.getPositionIndex()+"].country");
					}
				}
			}else if(other.fiscalResidenceList == null && this.fiscalResidenceList != null){
				for (CustomerCountryVO item : this.fiscalResidenceList) {
					if(item.getPosition()!=null){
						idDiffList.add("fiscalResidenceList["+item.getPositionIndex()+"].country");
					}
				}
			}
			
			if (this.er_em != null && other.er_em != null) {
				idDiffList.addAll(this.er_em.equals(other.er_em));
			} else if ((this.er_em == null && other.er_em != null) || (other.er_em == null && this.er_em != null)) {
				idDiffList.add("er_em");
			}
			
		}else{
			idDiffList.add("cpfCnpj");
			idDiffList.add("numberGFCID");
			idDiffList.add("customerType");
			idDiffList.add("name");
			idDiffList.add("numberEM");
			idDiffList.add("nameBanker");
			idDiffList.add("customerStatus");
			idDiffList.add("birthDate");
			idDiffList.add("gender");
			idDiffList.add("filiation1");
			idDiffList.add("filiation2");
			idDiffList.add("civilState");
			idDiffList.add("numberDependents");
			idDiffList.add("spouseName");
			idDiffList.add("countryBirth");
			idDiffList.add("");
			idDiffList.add("ufPlaceOfBirth");
			idDiffList.add("identityDocument");
			idDiffList.add("emitType");
			idDiffList.add("emitDocumentUF");
			idDiffList.add("emitDocumentDate");
			idDiffList.add("occupation");
			idDiffList.add("occupationNature");
			idDiffList.add("declaredIncome");
			idDiffList.add("declaredHeritage");
			idDiffList.add("employeeDiv");
			idDiffList.add("deceasedDiv");
			idDiffList.add("SOEIDBankerNumber");
			idDiffList.add("SOEIDBankerName");
			idDiffList.add("haveGreenCardDiv");
			idDiffList.add("socialSecurityNumber");
			idDiffList.add("exemptIRDiv");
			idDiffList.add("activityMain");
			idDiffList.add("constType");
			idDiffList.add("naicNumber");
			idDiffList.add("sicNumber");
			idDiffList.add("foundationDate");
			idDiffList.add("averageMonthBilling");
			idDiffList.add("admName");
			idDiffList.add("admCpf");
			idDiffList.add("hasFlexAccountDiv");
			idDiffList.add("sensitiveDiv");
			idDiffList.add("typeClassDiv");
			idDiffList.add("derogatoryInformationDiv");
			idDiffList.add("isFatcaTr");
			idDiffList.add("isCrsTr");
			idDiffList.add("formTypeDiv");
			idDiffList.add("signatureDateCrs");
			idDiffList.add("signatureDateFatca");
			
			idDiffList.addAll(this.residentialAddress.equals(null, "residentialAddress."));
			idDiffList.addAll(this.businessAddress.equals(null,"businessAddress."));
			idDiffList.addAll(this.otherAddress.equals(null, "otherAddress."));
			idDiffList.addAll(this.headOfficeAddress.equals(null, "headOfficeAddress."));
			
			if (this.telephoneList!=null) {
				for (TelephoneVO item : this.telephoneList) {
					idDiffList.add("telephoneList["+item.getPosition()+"].ddd");
					idDiffList.add("telephoneList["+item.getPosition()+"].number");
				}
			}
			
			if (this.mailList!=null) {
				for (MailVO item : this.mailList) {
					idDiffList.add("mailList["+item.getPosition()+"].mail");
				}
			}
			
			if (this.citizenshipList!=null) {
				for (CustomerCountryVO item : this.citizenshipList) {
					idDiffList.add("citizenshipList["+item.getPosition()+"].country");
				}
			}
			
			if (this.fiscalResidenceList!=null) {
				for (CustomerCountryVO item : this.fiscalResidenceList) {
					idDiffList.add("fiscalResidenceList["+item.getPosition()+"].country");
				}
			}
			
			idDiffList.add("er_em");
		}
		
		if (this.custFatcaPjInUs != null && other.custFatcaPjInUs != null) {
			if (!this.custFatcaPjInUs.equals(other.custFatcaPjInUs)) {
				idDiffList.add("custFatcaPjInUsDiv");
			}
		} else if ((this.custFatcaPjInUs == null && other.custFatcaPjInUs != null) || (other.custFatcaPjInUs == null && this.custFatcaPjInUs != null)) {
			idDiffList.add("custFatcaPjInUsDiv");
		}
		
		
		if (this.custFatcaPjOutUs != null && other.custFatcaPjOutUs != null) {
			if (!this.custFatcaPjOutUs.equals(other.custFatcaPjOutUs)) {
				idDiffList.add("custFatcaPjOutUsDiv");
			}
		} else if ((this.custFatcaPjOutUs == null && other.custFatcaPjOutUs != null) || (other.custFatcaPjOutUs == null && this.custFatcaPjOutUs != null)) {
			idDiffList.add("custFatcaPjOutUsDiv");
		}
		
		
		if (this.custFatcaPjOwnrUs != null && other.custFatcaPjOwnrUs != null) {
			if (!this.custFatcaPjOwnrUs.equals(other.custFatcaPjOwnrUs)) {
				idDiffList.add("custFatcaPjOwnrUsDiv");
			}
		} else if ((this.custFatcaPjOwnrUs == null && other.custFatcaPjOwnrUs != null) || (other.custFatcaPjOwnrUs == null && this.custFatcaPjOwnrUs != null)) {
			idDiffList.add("custFatcaPjOwnrUsDiv");
		}
		
		if (this.custCrsPjAddrOutUs != null && other.custCrsPjAddrOutUs != null) {
			if (!this.custCrsPjAddrOutUs.equals(other.custCrsPjAddrOutUs)) {
				idDiffList.add("custCrsPjAddrOutUsDiv");
			}
		} else if ((this.custCrsPjAddrOutUs == null && other.custCrsPjAddrOutUs != null) || (other.custCrsPjAddrOutUs == null && this.custCrsPjAddrOutUs != null)) {
			idDiffList.add("custCrsPjAddrOutUsDiv");
		}
		
		if (this.custCrsPjEnfLiab != null && other.custCrsPjEnfLiab != null) {
			if (!this.custCrsPjEnfLiab.equals(other.custCrsPjEnfLiab)) {
				idDiffList.add("custCrsPjEnfLiabDiv");
			}
		} else if ((this.custCrsPjEnfLiab == null && other.custCrsPjEnfLiab != null) || (other.custCrsPjEnfLiab == null && this.custCrsPjEnfLiab != null)) {
			idDiffList.add("custCrsPjEnfLiabDiv");
		}
		
		if (this.custCrsPjInvstOut != null && other.custCrsPjInvstOut != null) {
			if (!this.custCrsPjInvstOut.equals(other.custCrsPjInvstOut)) {
				idDiffList.add("custCrsPjInvstOutDiv");
			}
		} else if ((this.custCrsPjInvstOut == null && other.custCrsPjInvstOut != null) || (other.custCrsPjInvstOut == null && this.custCrsPjInvstOut != null)) {
			idDiffList.add("custCrsPjInvstOutDiv");
		}
		
		return idDiffList;
	}
	
}
