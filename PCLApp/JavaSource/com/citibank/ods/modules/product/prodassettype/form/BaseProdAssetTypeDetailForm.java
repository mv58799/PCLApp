/*
 * Created on 19/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodassettype.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;

/**
 * @author rcoelho
 * @since 19/08/2008
 */
public class BaseProdAssetTypeDetailForm extends BaseForm implements ProdAssetTypeDetailable {

	//Domain da Classe de Ativos.
	private DataSet m_prodAssetCodeDomain = null;

	//Codigo da Classe de Ativos.
	private String m_prodAssetCode = null;

	//Domain da Sub Classe de Ativos.
	private DataSet m_prodSubAssetCodeDomain = null;

	//Codigo da Sub Classe de Ativos.
	private String m_prodSubAssetCode = null;

	//Data e hora da ultima atualizaca efetuada pelo usuario.
	private String m_lastUpdDate = "";

	//Codigo do usuario que efetuou a ultima atualizacao no registro.
	private String m_lastUpdUserId = "";

	//Codigo do tipo de Ativo.
	private String m_prodAssetTypeCode = "";

	//Descricao do Tipo de Ativo.
	private String m_prodAssetTypeText = "";
	
    //Flag que controla a busca de sub-família.
	private String m_findType = "";
	
	//Ordem em que o Tipo de Ativo deve Aprarecer no Relatorio para Clientes.
	private String m_assetTypeCustRptOrderNbr = "";
	
	/**
      * @param DataSet prodAssetCodeDomain_.
      * Seta o Domain da Classe de Ativos.
	  */
	public void setProdAssetCodeDomain(DataSet prodAssetCodeDomain_) {
		m_prodAssetCodeDomain = prodAssetCodeDomain_;
	}

	/**
	  * @return Retorna o Domain da Classe de Ativos.
	  */
	public DataSet getProdAssetCodeDomain() {
		return m_prodAssetCodeDomain;
	}

	/**
	  * @param String prodAssetCode_.
	  * Seta o Codigo da Classe de Ativos.
	  */
	public void setProdAssetCode(String prodAssetCode_) {
		m_prodAssetCode = prodAssetCode_;
	}

	/**
	  * @return Retorna o Codigo da Classe de Ativos.
	  */
	public String getProdAssetCode() {
		return m_prodAssetCode;
	}

	/**
	  * @return Retorna a Data e hora da ultima atualizaca efetuada pelo usuario.
	  */
	public String getLastUpdDate() {
		return m_lastUpdDate;
	}

	/**
	  * @param String lastUpdDate_.
	  * Seta a Data e hora da ultima atualizaca efetuada pelo usuario.
	  */
	public void setLastUpdDate(String lastUpdDate_) {
		m_lastUpdDate = lastUpdDate_;
	}

	/**
	  * @return Retorna o Codigo do usuario que efetuou a ultima atualizacao no registro.
	  */
	public String getLastUpdUserId() {
		return m_lastUpdUserId;
	}

	/**
	  * @param String lastUpdUserId_.
	  * Seta o Codigo do usuario que efetuou a ultima atualizacao no registro.
	  */
	public void setLastUpdUserId(String lastUpdUserId_) {
		m_lastUpdUserId = lastUpdUserId_;
	}

	/**
	  * @return Retorna o Codigo do tipo de Ativo.
	  */
	public String getProdAssetTypeCode() {
		return m_prodAssetTypeCode;
	}

	/**
	  * @param String prodAssetTypeCode_.
	  * Seta o Codigo do tipo de Ativo.
	  */
	public void setProdAssetTypeCode(String prodAssetTypeCode_) {
		m_prodAssetTypeCode = prodAssetTypeCode_;
	}

	/**
	  * @return Retorna a Descricao do Tipo de Ativo.
	  */
	public String getProdAssetTypeText() {
		return m_prodAssetTypeText;
	}

	/**
	  * @param String prodAssetTypeText_.
	  * Seta a Descricao do Tipo de Ativo.
	  */
	public void setProdAssetTypeText(String prodAssetTypeText_) {
		m_prodAssetTypeText = prodAssetTypeText_;
	}

	/**
	  * @return Retorna a Seleção da lista do Tipo de Ativo.
	  */
	public String getSelectedProdAssetTypeCode() {

		return null;
	}

	/**
	  * @param String setSelectedProdAssetTypeCode_.
	  * Seta a Seleção da lista do Tipo de Ativo.
	  */
	public void setSelectedProdAssetTypeCode(String setSelectedProdAssetTypeCode_) {

		m_prodAssetTypeCode = setSelectedProdAssetTypeCode_;

	}

	/*
	 * Realiza as validações de tipos e tamanhos
	 */
	public ActionErrors validate(
		ActionMapping actionMapping_,
		HttpServletRequest request_) {
		ActionErrors errors = new ActionErrors();

		return errors;
	}

	/**
	  * @return Retorna o Codigo da Sub Classe de Ativos.
	  */
	public String getProdSubAssetCode() {
		return m_prodSubAssetCode;
	}

	/**
	  * @return Retorna o Domain da Sub Classe de Ativos.
	  */
	public DataSet getProdSubAssetCodeDomain() {
		return m_prodSubAssetCodeDomain;
	}

	/**
	  * @param String string.
	  * Seta o Codigo da Sub Classe de Ativos.
	  */
	public void setProdSubAssetCode(String string) {
		m_prodSubAssetCode = string;
	}

	/**
	  * @param DataSet set.
	  * Seta o Domain da Sub Classe de Ativos.
	  */
	public void setProdSubAssetCodeDomain(DataSet set) {
		m_prodSubAssetCodeDomain = set;
	}

	/**
	  * @return Retorna a Flag que controla a busca de sub-família.
	  */
	public String getFindType() {
		return m_findType;
	}

	/**
	  * @param String m_findType_.
	  * Seta a Flag que controla a busca de sub-família.
	  */
	public void setFindType(String m_findType_) {
		m_findType = m_findType_;
	}

	/**
	  * @return Retorna a Ordem em que o Tipo de Ativo deve Aprarecer no Relatorio para Clientes.
	  */
	public String getAssetTypeCustRptOrderNbr() {
		return m_assetTypeCustRptOrderNbr;
	}

	/**
	  * @param String m_assetTypeCustRptOrderNbr_.
	  * Seta a Ordem em que o Tipo de Ativo deve Aprarecer no Relatorio para Clientes.
	  */
	public void setAssetTypeCustRptOrderNbr(String m_assetTypeCustRptOrderNbr_) {
		m_assetTypeCustRptOrderNbr = m_assetTypeCustRptOrderNbr_;
	}

}
