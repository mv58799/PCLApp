package com.citibank.newcpb.vo;

import java.util.Date;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.util.FormatUtils;

public class AcctEgVO {	
	
	private String id;
	private String erNbr; // Numero do ER
	private String acctNbr; // Numero Conta
	private String egNbr; // Numero do EG
	private Date lastAuthDate; // Data e hora da última autorizacao
	private String lastAuthUser; // Usuario que realizou a ultima autorizacao
	private Date lastUpdDate; // Data e hora da última atualização
	private String lastUpdUser; // Usuario que realizou a ultima atualizacao
	private String recStatCode; // Status do Registro
	private TableTypeEnum tableOrigin; // Tipo da Tabela de Origem (CAD/MOV/HIST)
	private String customerName; // Nome Cliente 

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getLastUpdUserSafe() {
		if(lastUpdUser!=null){
			return lastUpdUser;
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
	
	public String getLastUpdDateFormatedSafe() {
		if(lastUpdDate!=null){
			return FormatUtils.dateToString(lastUpdDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY_HHMMSS);
		}else{
			return "";
		}
	}
}
