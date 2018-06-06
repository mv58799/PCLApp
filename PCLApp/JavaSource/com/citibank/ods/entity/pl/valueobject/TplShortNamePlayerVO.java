package com.citibank.ods.entity.pl.valueobject;

public class TplShortNamePlayerVO {
	
	//Nome do Mnemonico
	private String issueShortName;
	//CNPJ do Player
	private String plyrCnpjNbr;
	//Codigo do Usuario que efetuou a ultima atualizacao registro.
	private String lastUpdUserId;
	
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

}
