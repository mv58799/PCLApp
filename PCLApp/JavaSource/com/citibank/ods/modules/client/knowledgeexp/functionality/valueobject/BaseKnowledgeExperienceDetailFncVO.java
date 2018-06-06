package com.citibank.ods.modules.client.knowledgeexp.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

@SuppressWarnings("serial")
public class BaseKnowledgeExperienceDetailFncVO extends BaseODSFncVO{
	
	/**
	 *  Número do Cliente 
	 */
	private BigInteger clientNumber;

	/**
	 *  Nome do cliente
	 */
	private String clientNameText;
	
	private DataSet results;
	
	public DataSet getResults() {
		return results;
	}

	public void setResults(DataSet result) {
		this.results = result;
	}

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

}
