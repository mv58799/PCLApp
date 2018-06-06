package com.citibank.newcpb.vo;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.enun.AccountTypeEnum;
import com.citibank.newcpb.enun.AccountTypeRDIPEnum;
import com.citibank.newcpb.util.FormatUtils;

public class AcctCmplVO {	
	
	private Integer id;
	private String erNbr; // Numero do ER
	private String acctNbr; // Numero Conta
	private String egNbr; // Numero do EG
	private String customerName; // Nome Cliente 
	private String accountType;
	private String accountTypeDesc;
	private String cpfCnpjNbr;
	private String cpfCnpjNbrFormated;
	private String contractSignatureDate;
	private boolean hasAngent;
	private String hasAngentString;
	private boolean hasQuestionsKe;
	private String hasQuestionsKeString;
	private String emNbr; 
	private String gfcid;
	private String officeSoeId;
	private String bankerName;

	
	private String accountClosingDate;
	private String accountClosingReason;
	private String accountOpenDate;
	private String cetipNumber;
	private String bovespaNumber;
	private String bmfNumber;
	private String selicNumber;
	private String bvrjNumber;
	private String riskLevelCode; // Codigo do risco do EG 
	private String lastIosRevDate; // data da ultima revisao no IOS
	private String accountTypeRDIP; //Tipo de conta RDIP
	private String accountTypeRDIPDesc;
	

	private Date lastAuthDate; // Data e hora da última autorizacao
	private String lastAuthUser; // Usuario que realizou a ultima autorizacao
	private Date lastUpdDate; // Data e hora da última atualização
	private String lastUpdUser; // Usuario que realizou a ultima atualizacao


	private String recStatCode; // Status do Registro
	private TableTypeEnum tableOrigin; // Tipo da Tabela de Origem (CAD/MOV/HIST)
	
	
	private String effectiveDateAuthPerson;
	private String effectiveDateAgent;
	private Boolean authPersonInd;
	private Boolean agentInd;

	public String getErNbr() {
		return erNbr;
	}

	public void setErNbr(String erNbr) {
		this.erNbr = erNbr;
	}

	public String getEgNbr() {
		return egNbr;
	}

	public void setEgNbr(String egNbr) {
		this.egNbr = egNbr;
	}
	
	public Date getLastAuthDate() {
		return lastAuthDate;
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

	public String getLastUpdUser() {
		return lastUpdUser;
	}

	public void setLastUpdUser(String lastUpdUser) {
		this.lastUpdUser = lastUpdUser;
	}

	public String getRecStatCode() {
		return recStatCode;
	}

	public void setRecStatCode(String recStatCode) {
		this.recStatCode = recStatCode;
	}

	public TableTypeEnum getTableOrigin() {
		return tableOrigin;
	}

	public void setTableOrigin(TableTypeEnum tableOrigin) {
		this.tableOrigin = tableOrigin;
	}

	public String getAcctNbr() {
		return acctNbr;
	}

	public void setAcctNbr(String acctNbr) {
		this.acctNbr = acctNbr;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getCpfCnpjNbr() {
		return cpfCnpjNbr;
	}

	public void setCpfCnpjNbr(String cpfCnpjNbr) {
		this.cpfCnpjNbr = cpfCnpjNbr;
	}

	public String getContractSignatureDate() {
		return contractSignatureDate;
	}

	public void setContractSignatureDate(String contractSignatureDate) {
		this.contractSignatureDate = contractSignatureDate;
	}

	public boolean isHasAngent() {
		return hasAngent;
	}

	public void setHasAngent(boolean hasAngent) {
		this.hasAngent = hasAngent;
	}

	public String getHasAngentString() {		
		if(hasAngent){
			hasAngentString = "Sim";
		}else{
			hasAngentString = "Não";
		}	
		return hasAngentString;
	}

	public void setHasAngentString(String hasAngentString) {
		this.hasAngentString = hasAngentString;
	}

	public boolean isHasQuestionsKe() {
		return hasQuestionsKe;
	}

	public void setHasQuestionsKe(boolean hasQuestionsKe) {
		this.hasQuestionsKe = hasQuestionsKe;
	}

	public String getHasQuestionsKeString() {
		
		if(hasQuestionsKe){
			hasQuestionsKeString = "Sim";
		}else{
			hasQuestionsKeString = "Não";
		}
		
		return hasQuestionsKeString;
	}

	public void setHasQuestionsKeString(String hasQuestionsKeString) {
		this.hasQuestionsKeString = hasQuestionsKeString;
	}

	public String getAccountTypeDesc() {
		
		if(!StringUtils.isBlank(accountType)){
			
			if(AccountTypeEnum.fromValue(accountType)!=null){
				accountTypeDesc = AccountTypeEnum.fromValue(accountType).getDesc();
			}
			
		}
		return accountTypeDesc;
	}

	public void setAccountTypeDesc(String accountTypeDesc) {
		this.accountTypeDesc = accountTypeDesc;
	}

	public String getCpfCnpjNbrFormated() {
		return cpfCnpjNbrFormated;
	}

	public void setCpfCnpjNbrFormated(String cpfCnpjNbrFormated) {
		this.cpfCnpjNbrFormated = cpfCnpjNbrFormated;
	}
	
	public String getEmNbr() {
		return emNbr;
	}

	public void setEmNbr(String emNbr) {
		this.emNbr = emNbr;
	}

	public String getAccountClosingDate() {
		return accountClosingDate;
	}

	public void setAccountClosingDate(String accountClosingDate) {
		this.accountClosingDate = accountClosingDate;
	}

	public String getAccountClosingReason() {
		return accountClosingReason;
	}

	public void setAccountClosingReason(String accountClosingReason) {
		this.accountClosingReason = accountClosingReason;
	}

	public String getAccountOpenDate() {
		return accountOpenDate;
	}

	public void setAccountOpenDate(String accountOpenDate) {
		this.accountOpenDate = accountOpenDate;
	}

	public String getCetipNumber() {
		return cetipNumber;
	}

	public void setCetipNumber(String cetipNumber) {
		this.cetipNumber = cetipNumber;
	}

	public String getBovespaNumber() {
		return bovespaNumber;
	}

	public void setBovespaNumber(String bovespaNumber) {
		this.bovespaNumber = bovespaNumber;
	}

	public String getBmfNumber() {
		return bmfNumber;
	}

	public void setBmfNumber(String bmfNumber) {
		this.bmfNumber = bmfNumber;
	}

	public String getSelicNumber() {
		return selicNumber;
	}

	public void setSelicNumber(String selicNumber) {
		this.selicNumber = selicNumber;
	}

	public String getBvrjNumber() {
		return bvrjNumber;
	}

	public void setBvrjNumber(String bvrjNumber) {
		this.bvrjNumber = bvrjNumber;
	}
	
	public String getRiskLevelCode() {
		return riskLevelCode;
	}

	public void setRiskLevelCode(String riskLevelCode) {
		this.riskLevelCode = riskLevelCode;
	}

	public String getLastIosRevDate() {
		return lastIosRevDate;
	}

	public void setLastIosRevDate(String lastIosRevDate) {
		this.lastIosRevDate = lastIosRevDate;
	}

	public String getAccountTypeRDIP() {
		return accountTypeRDIP;
	}

	public void setAccountTypeRDIP(String accountTypeRDIP) {
		this.accountTypeRDIP = accountTypeRDIP;
	}

	public String getAccountTypeRDIPDesc() {
		if(!StringUtils.isBlank(accountTypeRDIP)){
			
			if(AccountTypeRDIPEnum.fromValue(accountTypeRDIP)!=null){
				accountTypeRDIPDesc = AccountTypeRDIPEnum.fromValue(accountTypeRDIP).getDesc();
			}
			
		}
		return accountTypeRDIPDesc;
	}

	public void setAccountTypeRDIPDesc(String accountTypeRDIPDesc) {
		this.accountTypeRDIPDesc = accountTypeRDIPDesc;
	}

	
	public String getLastUpdUserSafe() {
		if(lastUpdUser!=null){
			return lastUpdUser;
		}else{
			return "";
		}
	}
	
	public String getLastUpdDateFormatedSafe() {
		if(lastUpdDate!=null){
			return FormatUtils.dateToString(lastUpdDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY_HHMMSS);
		}else{
			return "";
		}
	}
	
	public String getRecStatCodeText() {
		if(recStatCode!=null){
			if(recStatCode.equals("U")){
				return "Alteração";
			}else if(recStatCode.equals("A")){
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
	
	
	public String getGfcid() {
		return gfcid;
	}

	public void setGfcid(String gfcid) {
		this.gfcid = gfcid;
	}

	/**
	 * @return the officeSoeId
	 */
	public String getOfficeSoeId() {
		return officeSoeId;
	}

	/**
	 * @param officeSoeId the officeSoeId to set
	 */
	public void setOfficeSoeId(String officeSoeId) {
		this.officeSoeId = officeSoeId;
	}

	public ArrayList<String> equals(AcctCmplVO other) {

		ArrayList<String> idDiffList = new ArrayList<String>();
		
		if (other != null){
			
			if (this.acctNbr != null && other.acctNbr != null) {
				if (!this.acctNbr.equals(other.acctNbr)) {
					idDiffList.add("acctNbr");
				}
			} else if ((this.acctNbr == null && other.acctNbr != null) || (other.acctNbr == null && this.acctNbr != null)) {
				idDiffList.add("acctNbr");
			}
			
			if (this.accountType != null && other.accountType != null) {
				if (!this.accountType.equals(other.accountType)) {
					idDiffList.add("accountType");
				}
			} else if ((this.accountType == null && other.accountType != null) || (other.accountType == null && this.accountType != null)) {
				idDiffList.add("accountType");
			}
			
			if (this.cpfCnpjNbr != null && other.cpfCnpjNbr != null) {
				if (!this.cpfCnpjNbr.equals(other.cpfCnpjNbr)) {
					idDiffList.add("cpfCnpjNbr");
				}
			} else if ((this.cpfCnpjNbr == null && other.cpfCnpjNbr != null) || (other.cpfCnpjNbr == null && this.cpfCnpjNbr != null)) {
				idDiffList.add("cpfCnpjNbr");
			}
			
			if (this.contractSignatureDate != null && other.contractSignatureDate != null) {
				if (!this.contractSignatureDate.equals(other.contractSignatureDate)) {
					idDiffList.add("contractSignatureDate");
				}
			} else if ((this.contractSignatureDate == null && other.contractSignatureDate != null) || (other.contractSignatureDate == null && this.contractSignatureDate != null)) {
				idDiffList.add("contractSignatureDate");
			}
			
			if (this.accountOpenDate != null && other.accountOpenDate != null) {
				if (!this.accountOpenDate.equals(other.accountOpenDate)) {
					idDiffList.add("accountOpenDate");
				}
			} else if ((this.accountOpenDate == null && other.accountOpenDate != null) || (other.accountOpenDate == null && this.accountOpenDate != null)) {
				idDiffList.add("accountOpenDate");
			}
			
			if (this.accountClosingDate != null && other.accountClosingDate != null) {
				if (!this.accountClosingDate.equals(other.accountClosingDate)) {
					idDiffList.add("accountClosingDate");
				}
			} else if ((this.accountClosingDate == null && other.accountClosingDate != null) || (other.accountClosingDate == null && this.accountClosingDate != null)) {
				idDiffList.add("accountClosingDate");
			}
			
			if (this.accountClosingReason != null && other.accountClosingReason != null) {
				if (!this.accountClosingReason.equals(other.accountClosingReason)) {
					idDiffList.add("accountClosingReason");
				}
			} else if ((this.accountClosingReason == null && other.accountClosingReason != null) || (other.accountClosingReason == null && this.accountClosingReason != null)) {
				idDiffList.add("accountClosingReason");
			}
			
			if (this.cetipNumber != null && other.cetipNumber != null) {
				if (!this.cetipNumber.equals(other.cetipNumber)) {
					idDiffList.add("cetipNumber");
				}
			} else if ((this.cetipNumber == null && other.cetipNumber != null) || (other.cetipNumber == null && this.cetipNumber != null)) {
				idDiffList.add("cetipNumber");
			}
			
			if (this.selicNumber != null && other.selicNumber != null) {
				if (!this.selicNumber.equals(other.selicNumber)) {
					idDiffList.add("selicNumber");
				}
			} else if ((this.selicNumber == null && other.selicNumber != null) || (other.selicNumber == null && this.selicNumber != null)) {
				idDiffList.add("selicNumber");
			}
			
			if (this.bovespaNumber != null && other.bovespaNumber != null) {
				if (!this.bovespaNumber.equals(other.bovespaNumber)) {
					idDiffList.add("bovespaNumber");
				}
			} else if ((this.bovespaNumber == null && other.bovespaNumber != null) || (other.bovespaNumber == null && this.bovespaNumber != null)) {
				idDiffList.add("bovespaNumber");
			}
			
			if (this.bvrjNumber != null && other.bvrjNumber != null) {
				if (!this.bvrjNumber.equals(other.bvrjNumber)) {
					idDiffList.add("bvrjNumber");
				}
			} else if ((this.bvrjNumber == null && other.bvrjNumber != null) || (other.bvrjNumber == null && this.bvrjNumber != null)) {
				idDiffList.add("bvrjNumber");
			}
			
			if (this.bmfNumber != null && other.bmfNumber != null) {
				if (!this.bmfNumber.equals(other.bmfNumber)) {
					idDiffList.add("bmfNumber");
				}
			} else if ((this.bmfNumber == null && other.bmfNumber != null) || (other.bmfNumber == null && this.bmfNumber != null)) {
				idDiffList.add("bmfNumber");
			}
			
			
			if (this.hasAngent != other.hasAngent) {
				idDiffList.add("hasAngent");
			}
			
			if (!StringUtils.isBlank(this.riskLevelCode) && !StringUtils.isBlank(other.riskLevelCode)) {
				if (!this.riskLevelCode.equals(other.riskLevelCode)) {
					idDiffList.add("riskLevelCode");
				}
			} else if ((StringUtils.isBlank(this.riskLevelCode) && !StringUtils.isBlank(other.riskLevelCode))
					|| (StringUtils.isBlank(riskLevelCode) && !StringUtils.isBlank(this.riskLevelCode))) {
				idDiffList.add("riskLevelCode");
			}
			
			if (!StringUtils.isBlank(this.lastIosRevDate) && !StringUtils.isBlank(other.lastIosRevDate)) {
				if (!this.lastIosRevDate.equals(other.lastIosRevDate)) {
					idDiffList.add("lastIosRevDate");
				}
			} else if ((StringUtils.isBlank(this.lastIosRevDate) && !StringUtils.isBlank(other.lastIosRevDate))
					|| (StringUtils.isBlank(lastIosRevDate) && !StringUtils.isBlank(this.lastIosRevDate))) {
				idDiffList.add("lastIosRevDate");
			}
			
			if (this.accountTypeRDIP != null && other.accountTypeRDIP != null) {
				if (!this.accountTypeRDIP.equals(other.accountTypeRDIP)) {
					idDiffList.add("accountTypeRDIP");
				}
			} else if ((this.accountTypeRDIP == null && other.accountTypeRDIP != null) || (other.accountTypeRDIP == null && this.accountTypeRDIP != null)) {
				idDiffList.add("accountTypeRDIP");
			}
			
			if (!StringUtils.isBlank(this.erNbr) && !StringUtils.isBlank(other.erNbr)) {
				if (!this.erNbr.equals(other.erNbr)) {
					idDiffList.add("erNbr");
				}
			} else if ((StringUtils.isBlank(this.erNbr) && !StringUtils.isBlank(other.erNbr))
					|| (StringUtils.isBlank(erNbr) && !StringUtils.isBlank(this.erNbr))) {
				idDiffList.add("erNbr");
			}
			
			if (!StringUtils.isBlank(this.egNbr) && !StringUtils.isBlank(other.egNbr)) {
				if (!this.egNbr.equals(other.egNbr)) {
					idDiffList.add("egNbr");
				}
			} else if ((StringUtils.isBlank(this.egNbr) && !StringUtils.isBlank(other.egNbr))
					|| (StringUtils.isBlank(egNbr) && !StringUtils.isBlank(this.egNbr))) {
				idDiffList.add("egNbr");
			}		
			
			
		}else{
			idDiffList.add("acctNbr");
			idDiffList.add("accountType");
			idDiffList.add("cpfCnpjNbr");
			idDiffList.add("contractSignatureDate");
			idDiffList.add("accountOpenDate");
			idDiffList.add("accountClosingDate");
			idDiffList.add("accountClosingReason");
			idDiffList.add("cetipNumber");
			idDiffList.add("selicNumber");
			idDiffList.add("bovespaNumber");
			idDiffList.add("bvrjNumber");
			idDiffList.add("bmfNumber");
			idDiffList.add("hasAngent");			
			idDiffList.add("riskLevelCode");
			idDiffList.add("lastIosRevDate");
			idDiffList.add("accountTypeRDIP");
			idDiffList.add("erNbr");
			idDiffList.add("egNbr");
			
			
		}
		return idDiffList;
	}

	/**
	 * @return the bankerName
	 */
	public String getBankerName() {
		return bankerName;
	}

	/**
	 * @param bankerName the bankerName to set
	 */
	public void setBankerName(String bankerName) {
		this.bankerName = bankerName;
	}

	public String getEffectiveDateAuthPerson() {
		return effectiveDateAuthPerson;
	}

	public void setEffectiveDateAuthPerson(String effectiveDateAuthPerson) {
		this.effectiveDateAuthPerson = effectiveDateAuthPerson;
	}

	public String getEffectiveDateAgent() {
		return effectiveDateAgent;
	}

	public void setEffectiveDateAgent(String effectiveDateAgent) {
		this.effectiveDateAgent = effectiveDateAgent;
	}

	public Boolean getAuthPersonInd() {
		return authPersonInd;
	}

	public void setAuthPersonInd(Boolean authPersonInd) {
		this.authPersonInd = authPersonInd;
	}

	public Boolean getAgentInd() {
		return agentInd;
	}

	public void setAgentInd(Boolean agentInd) {
		this.agentInd = agentInd;
	}
}
