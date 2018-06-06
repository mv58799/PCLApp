package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

public class TplProductCorpHistEntityVO extends BaseTplProductCorpEntityVO{
	/**
	 * Data e Hora que o usuario aprovou o registro cadastrado
	 */

	private Date m_lastAuthDate;

	/**
	 * Codigo do usuario (SOE ID) que aprovou o cadastro do registro
	 */
	private String m_lastAuthUserId;

	/**
	 * Status Registro - Identifica se o registro esta ativo, inativo ou aguar
	 * dando aprovacao"
	 */
	private String m_recStatCode;

	/**
	 * Data de Referencia do registro no historico.
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	private Date m_prodRefDate;

	public Date getProdRefDate() {
		return m_prodRefDate;
	}

	public void setProdRefDate(Date refDate) {
		m_prodRefDate = refDate;
	}

	public Date getM_lastAuthDate() {
		return m_lastAuthDate;
	}

	public void setM_lastAuthDate(Date authDate) {
		m_lastAuthDate = authDate;
	}

	public String getM_lastAuthUserId() {
		return m_lastAuthUserId;
	}

	public void setM_lastAuthUserId(String authUserId) {
		m_lastAuthUserId = authUserId;
	}

	public String getM_recStatCode() {
		return m_recStatCode;
	}

	public void setM_recStatCode(String statCode) {
		m_recStatCode = statCode;
	}

}
