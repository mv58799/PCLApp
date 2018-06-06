package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

public class TplShortNamePlayerEntityVO {
	
	//Nome do Mnemonico
	private String issueShortName;
	//CNPJ do Player
	private String plyrCnpjNbr;
	//Codigo do Usuario que efetuou a ultima atualizacao registro.
	private String lastUpdUserId;
	//Data da Atualização
	private Date lastUpdDate;
	//Codigo da Operacao realizada no registro: inclusao, alteracao, exclusão
	private String opernCode;
	//
	private Date issueShortRefDate;
	//Codigo do Usuario que aprovou o registro.
	private String lastAuthUserId;
	//Data e Hora da aprovacao do registro.
	private Date lastAuthDate;
	//Status da aprovação
	private String recStatCode;
	
	public String getIssueShortName() {
		return issueShortName;
	}
	public void setIssueShortName(String issueShortName) {
		this.issueShortName = issueShortName;
	}
	public String getLastUpdUserId() {
		return lastUpdUserId;
	}
	public void setLastUpdUserId(String lastUpdUserId) {
		this.lastUpdUserId = lastUpdUserId;
	}
	public String getPlyrCnpjNbr() {
		return plyrCnpjNbr;
	}
	public void setPlyrCnpjNbr(String plyrCnpjNbr) {
		this.plyrCnpjNbr = plyrCnpjNbr;
	}
	public String getOpernCode() {
		return opernCode;
	}
	public void setOpernCode(String opernCode) {
		this.opernCode = opernCode;
	}
	public Date getLastUpdDate() {
		return lastUpdDate;
	}
	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}
	public Date getIssueShortRefDate() {
		return issueShortRefDate;
	}
	public void setIssueShortRefDate(Date issueShortRefDate) {
		this.issueShortRefDate = issueShortRefDate;
	}
	public String getLastAuthUserId() {
		return lastAuthUserId;
	}
	public void setLastAuthUserId(String lastAuthUserId) {
		this.lastAuthUserId = lastAuthUserId;
	}
	public Date getLastAuthDate() {
		return lastAuthDate;
	}
	public void setLastAuthDate(Date lastAuthDate) {
		this.lastAuthDate = lastAuthDate;
	}
	public String getRecStatCode() {
		return recStatCode;
	}
	public void setRecStatCode(String recStatCode) {
		this.recStatCode = recStatCode;
	}
}
