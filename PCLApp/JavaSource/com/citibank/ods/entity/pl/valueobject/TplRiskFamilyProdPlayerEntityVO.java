package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;


public class TplRiskFamilyProdPlayerEntityVO extends BaseTplRiskFamilyProdPlayerEntityVO {

	/**
	 * Data e Hora que o usuario aprovou o registro cadastrado
	 */

	private Date lastAuthDate;

	/**
	 * Codigo do usuario (SOE ID) que aprovou o cadastro do registro
	 */
	private String lastAuthUserId;

	/**
	 * Status Registro - Identifica se o registro esta ativo, inativo ou aguar
	 * dando aprovacao"
	 */
	private String recStatCode;

	public Date getLastAuthDate() {
		return lastAuthDate;
	}

	public void setLastAuthDate(Date lastAuthDate) {
		this.lastAuthDate = lastAuthDate;
	}

	public String getLastAuthUserId() {
		return lastAuthUserId;
	}

	public void setLastAuthUserId(String lastAuthUserId) {
		this.lastAuthUserId = lastAuthUserId;
	}

	public String getRecStatCode() {
		return recStatCode;
	}

	public void setRecStatCode(String recStatCode) {
		this.recStatCode = recStatCode;
	}

	
}
