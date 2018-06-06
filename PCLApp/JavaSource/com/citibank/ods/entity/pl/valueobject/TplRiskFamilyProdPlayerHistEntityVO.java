package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

public class TplRiskFamilyProdPlayerHistEntityVO extends BaseTplRiskFamilyProdPlayerEntityVO {
	/**
	 * Data e Hora que o usuario aprovou o registro cadastrado
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	private Date lastAuthDate;

	/**
	 * Codigo do usuario (SOE ID) que aprovou o cadastro do registro
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	private String lastAuthUserId;

	/**
	 * Status Registro - Identifica se o registro esta ativo, inativo ou aguar
	 * dando aprovacao"
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	private String recStatCode;

	/**
	 * Data de Referencia do registro no historico.
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	private Date riskFamlProdPlyrRefDate ;
	

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

	public Date getRiskFamlProdPlyrRefDate() {
		return riskFamlProdPlyrRefDate;
	}

	public void setRiskFamlProdPlyrRefDate(Date riskFamlProdPlyrRefDate) {
		this.riskFamlProdPlyrRefDate = riskFamlProdPlyrRefDate;
	}



}
