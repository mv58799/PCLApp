package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

/**
 * 
 * @author aribas
 *
 */

public class TplShortNamePlayerMovEntityVO {
		
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
	
	
}
