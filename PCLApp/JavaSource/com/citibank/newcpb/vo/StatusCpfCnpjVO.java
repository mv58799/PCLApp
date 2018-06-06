package com.citibank.newcpb.vo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.util.FormatUtils;

public class StatusCpfCnpjVO {
	
	private String cpfCnpjNbr; // Numero do CPF CNPJ 
	private String cpfStatus; // Status do CPF/CNPJ. A(Ativo)/I(inativo)
	private String cpfUpdMthYr; // Mês e Ano da ultima atualizacao do Status do CPF
	private Date lastAuthDate; // Data e hora da última autorizacao	
	private String lastAuthUserId; // Usuario que realizou a ultima autorizacao
	private Date lastUpdDate; // Data e hora da última atualização
	private String lastUpdUserId; // Usuario que realizou a ultima atualizacao
	private String recStatCode; // Status do Registro
	private TableTypeEnum tableOrigin; // Tipo da Tabela de Origem (CAD/MOV/HIST)
	
	private RegisterDataCustomerVO customer;
	
	public String getRecStatCodeText() {
		if(recStatCode!=null){
			if(recStatCode.equals("A")){
				return "Alteração";
			}else{
				return "";
			}			
		}else{
			return "";
		}
	}
	
	public ArrayList<String> equals(StatusCpfCnpjVO other) {

		ArrayList<String> idDiffList = new ArrayList<String>();
		
		if (other != null){
			
			if (this.cpfCnpjNbr != null && other.cpfCnpjNbr != null) {
				if (!this.cpfCnpjNbr.equals(other.cpfCnpjNbr)) {
					idDiffList.add("cpfCnpjNbr");
				}
			} else if ((this.cpfCnpjNbr == null && other.cpfCnpjNbr != null) || (other.cpfCnpjNbr == null && this.cpfCnpjNbr != null)) {
				idDiffList.add("cpfCnpjNbr");
			}
			
			if (this.cpfStatus != null && other.cpfStatus != null) {
				if (!this.cpfStatus.equals(other.cpfStatus)) {
					idDiffList.add("cpfStatus");
				}
			} else if ((this.cpfStatus == null && other.cpfStatus != null) || (other.cpfStatus == null && this.cpfStatus != null)) {
				idDiffList.add("cpfStatus");
			}
			
			if (this.cpfUpdMthYr != null && other.cpfUpdMthYr != null) {
				if (!this.cpfUpdMthYr.equals(other.cpfUpdMthYr)) {
					idDiffList.add("cpfUpdMthYr");
				}
			} else if ((this.cpfUpdMthYr == null && other.cpfUpdMthYr != null) || (other.cpfUpdMthYr == null && this.cpfUpdMthYr != null)) {
				idDiffList.add("cpfUpdMthYr");
			}
			
		}else{
			idDiffList.add("cpfCnpjNbr");
			idDiffList.add("cpfStatus");
			idDiffList.add("cpfUpdMthYr");
		}
		
		return idDiffList;
	}

	public String getCpfCnpjNbr() {
		return cpfCnpjNbr;
	}
	
	public String getCpfCnpjNbrFormated() {
		try {
			return FormatUtils.formatterDoc(cpfCnpjNbr);
		} catch (ParseException e) {
			return "";
		}
	}

	public void setCpfCnpjNbr(String cpfCnpjNbr) {
		this.cpfCnpjNbr = cpfCnpjNbr;
	}

	public Date getLastAuthDate() {
		return lastAuthDate;
	}

	public void setLastAuthDate(Date lastAuthDate) {
		this.lastAuthDate = lastAuthDate;
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

	public Date getLastUpdDate() {
		return lastUpdDate;
	}

	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
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

	public String getCpfStatus() {
		return cpfStatus;
	}
	
	public String getCpfStatusText() {
		if(!StringUtils.isBlank(cpfStatus)){
			if(cpfStatus.equalsIgnoreCase("A")){
				return "Ativo";
			}else if(cpfStatus.equalsIgnoreCase("I")){
				return "Inativo";
			}
		}
		return cpfStatus;
	}

	public void setCpfStatus(String cpfStatus) {
		this.cpfStatus = cpfStatus;
	}

	public String getCpfUpdMthYrText() {
		if(!StringUtils.isBlank(cpfUpdMthYr) && cpfUpdMthYr.length()>2){
			StringBuilder cpfUpdMthYrFormated = new StringBuilder(cpfUpdMthYr);
			cpfUpdMthYrFormated.insert(2, '/');
			return cpfUpdMthYrFormated.toString();
		}else{
			return "";
		}
	}
	
	public String getCpfUpdMthYr() {
		return cpfUpdMthYr;
	}

	public void setCpfUpdMthYr(String cpfUpdMthYr) {
		this.cpfUpdMthYr = cpfUpdMthYr;
	}

	public String getLastAuthUserId() {
		return lastAuthUserId;
	}

	public void setLastAuthUserId(String lastAuthUserId) {
		this.lastAuthUserId = lastAuthUserId;
	}
	
	public String getLastUpdUserIdSafe() {
		if(lastUpdUserId!=null){
			return lastUpdUserId;
		}else{
			return "";
		}
	}

	public String getLastUpdUserId() {
		return lastUpdUserId;
	}

	public void setLastUpdUserId(String lastUpdUserId) {
		this.lastUpdUserId = lastUpdUserId;
	}

	public RegisterDataCustomerVO getCustomer() {
		return customer;
	}

	public void setCustomer(RegisterDataCustomerVO customer) {
		this.customer = customer;
	}
}
