package com.citibank.ods.modules.client.knowledgeexp.form;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;

@SuppressWarnings("serial")
public class BaseKnowledgeExperienceDetailForm extends BaseForm{

	/**
	 *  Número do Cliente Selecionado 
	 */
	private String selectedClientNumber;

	/**
	 *  Nome do cliente Selecionado
	 */
	private String selectedClientNameText;
	
	private DataSet results;
	
	public DataSet getResults() {
		return results;
	}

	public void setResults(DataSet results) {
		this.results = results;
	}

	public String getSelectedClientNameText() {
		return selectedClientNameText;
	}

	public void setSelectedClientNameText(String selectedClientNameText) {
		this.selectedClientNameText = selectedClientNameText;
	}

	public String getSelectedClientNumber() {
		return selectedClientNumber;
	}

	public void setSelectedClientNumber(String selectedClientNumber) {
		this.selectedClientNumber = selectedClientNumber;
	}	

}
