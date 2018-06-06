/*
 * Created on Mar 18, 2007
 *
 */
package com.citibank.ods.modules.product.productfamilyprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplProductFamilyPrvtEntity;
import com.citibank.ods.modules.product.productfamilyprvt.functionality.valueobject.BaseProductFamilyPrvtListFncVO;

/**
 * @author leonardo.nakada
 * 
 */
public class BaseProductFamilyPrvtListForm extends BaseForm implements
		ProductFamilyPrvtDetailable {
	// Codigo da Familia de Produtos
	private String m_prodFamlCodeSrc = "";

	// Nome da Familia de Produtos
	private String m_prodFamlNameSrc = "";

	// Descricao da Familia de Produtos.
	private String m_prodFamlTextSrc = "";

	//elemento detailable
	private String m_selectedProdFamlCode = "";

	// resultado da consulta
	private DataSet m_results = null;

	/**
	 * @return Returns the prodFamlCodeSrc.
	 */
	public String getProdFamlCodeSrc() {
		return m_prodFamlCodeSrc;
	}

	/**
	 * @param prodFamlCodeSrc_
	 *            The prodFamlCodeSrc to set.
	 */
	public void setProdFamlCodeSrc(String prodFamlCodeSrc_) {
		m_prodFamlCodeSrc = prodFamlCodeSrc_;
	}

	/**
	 * @return Returns the prodFamlNameSrc.
	 */
	public String getProdFamlNameSrc() {
		return m_prodFamlNameSrc;
	}

	/**
	 * @param prodFamlNameSrc_
	 *            The prodFamlNameSrc to set.
	 */
	public void setProdFamlNameSrc(String prodFamlNameSrc_) {
		m_prodFamlNameSrc = prodFamlNameSrc_;
	}

	/**
	 * @return Returns the prodFamlTextSrc.
	 */
	public String getProdFamlTextSrc() {
		return m_prodFamlTextSrc;
	}

	/**
	 * @param prodFamlTextSrc_
	 *            The prodFamlTextSrc to set.
	 */
	public void setProdFamlTextSrc(String prodFamlTextSrc_) {
		m_prodFamlTextSrc = prodFamlTextSrc_;
	}

	/**
	 * @return Returns the selectedProdFamlCode.
	 */
	public String getSelectedProdFamlCode() {
		return m_selectedProdFamlCode;
	}

	/**
	 * @param selectedProdFamlCode_
	 *            The selectedProdFamlCode to set.
	 */
	public void setSelectedProdFamlCode(String selectedProdFamlCode_) {
		m_selectedProdFamlCode = selectedProdFamlCode_;
	}

	/**
	 * @return Returns the results.
	 */
	public DataSet getResults() {
		return m_results;
	}

	/**
	 * @param results_
	 *            The results to set.
	 */
	public void setResults(DataSet results_) {
		m_results = results_;
	}

	/*
	 * Realiza as validações de tipos e tamanhos
	 */
	public ActionErrors validate(ActionMapping actionMapping_,
			HttpServletRequest request_) {
		ActionErrors errors = new ActionErrors();

		ODSValidator.validateBigInteger(
				BaseProductFamilyPrvtListFncVO.C_PROD_FAML_CODE_DESCRIPTION,
				m_prodFamlCodeSrc,
				BaseTplProductFamilyPrvtEntity.C_PROD_FAML_CODE_SIZE, errors);

		ODSValidator.validateMaxLength(
				BaseProductFamilyPrvtListFncVO.C_PROD_FAML_NAME_DESCRIPTION,
				m_prodFamlNameSrc,
				BaseTplProductFamilyPrvtEntity.C_PROD_FAML_NAME_SIZE, errors);

		ODSValidator.validateMaxLength(
				BaseProductFamilyPrvtListFncVO.C_PROD_FAML_TEXT_DESCRIPTION,
				m_prodFamlTextSrc,
				BaseTplProductFamilyPrvtEntity.C_PROD_FAML_TEXT_SIZE, errors);

		return errors;
	}

}