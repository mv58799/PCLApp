package com.citibank.ods.modules.client.knowledgeexp.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;

@SuppressWarnings("serial")
public class BaseKnowledgeExperienceListFncVO extends BaseFncVO{

	/**
	 * Resultado da Consulta 
	 */
	private DataSet results;

	/**
	 *  Número do Cliente 
	 */
	private BigInteger clientNumber;

	/**
	 *  Nome do cliente
	 */
	private String clientNameText;

	public String getClientNameText() {
		return clientNameText;
	}

	public void setClientNameText(String clientNameText) {
		this.clientNameText = clientNameText;
	}

	public BigInteger getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(BigInteger clientNumber) {
		this.clientNumber = clientNumber;
	}

	public DataSet getResults() {
		return results;
	}

	public void setResults(DataSet results) {
		this.results = results;
	}	
	
}
