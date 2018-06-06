package com.citibank.ods.modules.client.knowledgeexp.form;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;

@SuppressWarnings("serial")
public class BaseKnowledgeExperienceListForm extends BaseForm {

	/**
	 * Resultado da Consulta 
	 */
	private DataSet results;

	/**
	 *  Número do Cliente 
	 */
	private String clientNumber;

	/**
	 *  Nome do cliente
	 */
	private String clientNameText;

	/**
	 * Número do cliente selecionado na grid de resultado da pesquisa
	 */
	private String selectedClientNumber;
	
	/**
	 * Nome do cliente selecionado na grid de resultado da pesquisa
	 */
	private String selectedClientNameText;
	
	public String getClientNameText() {
		return clientNameText;
	}

	public void setClientNameText(String clientNameText) {
		this.clientNameText = clientNameText;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public DataSet getResults() {
		return results;
	}

	public void setResults(DataSet results) {
		this.results = results;
	}

	public String getSelectedClientNumber() {
		return selectedClientNumber;
	}

	public void setSelectedClientNumber(String selectedClientNumber) {
		this.selectedClientNumber = selectedClientNumber;
	}

	public String getSelectedClientNameText() {
		return selectedClientNameText;
	}

	public void setSelectedClientNameText(String selectedClientNameText) {
		this.selectedClientNameText = selectedClientNameText;
	}
	
	
}
