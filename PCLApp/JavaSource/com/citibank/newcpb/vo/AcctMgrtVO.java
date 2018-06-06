package com.citibank.newcpb.vo;

import java.util.ArrayList;
import java.util.Date;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.util.FormatUtils;

public class AcctMgrtVO {	
	

	private String accountGrbNumber;
	private String accountGrbCpfCnpjNumber;
	private String accountGrbName;
	private String accountCustodiaNumber;
	private String accountCustodiaCpfCnpjNumber;
	private String accountCustodiaName;
	private String migrationDateString;
	private Date migrationDate;
	private String customerNumber;
	private String relationNumber;
	private String emNumber;
	private Date lastAuthDate; // Data e hora da última autorizacao
	private String lastAuthUser; // Usuario que realizou a ultima autorizacao
	private Date lastUpdDate; // Data e hora da última atualização
	private String lastUpdUser; // Usuario que realizou a ultima atualizacao
	private String recStatCode; // Status do Registro
	private TableTypeEnum tableOrigin; // Tipo da Tabela de Origem (CAD/MOV/HIST)
		
	
	


	public String getAccountGrbNumber() {
		return accountGrbNumber;
	}

	public void setAccountGrbNumber(String accountGrbNumber) {
		this.accountGrbNumber = accountGrbNumber;
	}

	public String getAccountGrbCpfCnpjNumber() {
		return accountGrbCpfCnpjNumber;
	}

	public void setAccountGrbCpfCnpjNumber(String accountGrbCpfCnpjNumber) {
		this.accountGrbCpfCnpjNumber = accountGrbCpfCnpjNumber;
	}

	public String getAccountGrbName() {
		return accountGrbName;
	}

	public void setAccountGrbName(String accountGrbName) {
		this.accountGrbName = accountGrbName;
	}

	public String getAccountCustodiaCpfCnpjNumber() {
		return accountCustodiaCpfCnpjNumber;
	}

	public void setAccountCustodiaCpfCnpjNumber(String accountCustodiaCpfCnpjNumber) {
		this.accountCustodiaCpfCnpjNumber = accountCustodiaCpfCnpjNumber;
	}

	public String getAccountCustodiaName() {
		return accountCustodiaName;
	}

	public void setAccountCustodiaName(String accountCustodiaName) {
		this.accountCustodiaName = accountCustodiaName;
	}

	public String getAccountCustodiaNumber() {
		return accountCustodiaNumber;
	}

	public void setAccountCustodiaNumber(String accountCustodiaNumber) {
		this.accountCustodiaNumber = accountCustodiaNumber;
	}

	public String getMigrationDateString() {
		return migrationDateString;
	}

	public void setMigrationDateString(String migrationDateString) {
		this.migrationDateString = migrationDateString;
	}

	public Date getMigrationDate() {
		return migrationDate;
	}

	public void setMigrationDate(Date migrationDate) {
		this.migrationDate = migrationDate;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getRelationNumber() {
		return relationNumber;
	}

	public void setRelationNumber(String relationNumber) {
		this.relationNumber = relationNumber;
	}

	public String getEmNumber() {
		return emNumber;
	}

	public void setEmNumber(String emNumber) {
		this.emNumber = emNumber;
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


	
	
	public ArrayList<String> equals(AcctMgrtVO other) {

		ArrayList<String> idDiffList = new ArrayList<String>();
		
		if (other != null){
			
			if (this.accountGrbNumber != null && other.accountGrbNumber != null) {
				if (!this.accountGrbNumber.equals(other.accountGrbNumber)) {
					idDiffList.add("accountGrbNumber");
				}
			} else if ((this.accountGrbNumber == null && other.accountGrbNumber != null) || (other.accountGrbNumber == null && this.accountGrbNumber != null)) {
				idDiffList.add("accountGrbNumber");
			}
			
			if (this.accountCustodiaNumber != null && other.accountCustodiaNumber != null) {
				if (!this.accountCustodiaNumber.equals(other.accountCustodiaNumber)) {
					idDiffList.add("accountCustodiaNumber");
				}
			} else if ((this.accountCustodiaNumber == null && other.accountCustodiaNumber != null) || (other.accountCustodiaNumber == null && this.accountCustodiaNumber != null)) {
				idDiffList.add("accountCustodiaNumber");
			}
			
			if (this.migrationDateString != null && other.migrationDateString != null) {
				if (!this.migrationDateString.equals(other.migrationDateString)) {
					idDiffList.add("migrationDateString");
				}
			} else if ((this.migrationDateString == null && other.migrationDateString != null) || (other.migrationDateString == null && this.migrationDateString != null)) {
				idDiffList.add("migrationDateString");
			}
			

				
			
			
		}else{
			idDiffList.add("accountGrbNumber");
			idDiffList.add("accountCustodiaNumber");
			idDiffList.add("migrationDateString");			
		}
		return idDiffList;
	}
	
}
