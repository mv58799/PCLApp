/*
 * Created on 19/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodasset.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;

/**
 * @author ronaldo.machado
 * @since 19/08/2008
 */
public class BaseProdAssetDetailForm extends BaseForm implements ProdAssetDetailable {

	//Data e hora da ultima atualizaca efetuada pelo usuario.
	private String m_lastUpdDate = "";

	//Codigo do usuario que efetuou a ultima atualizacao no registro.
	private String m_lastUpdUserId = "";

	//Codigo de Qualificacao do Produto Private.
	private String m_prodAssetCode = "";

	//Descricao da Qualificacao do Produto Private.
	private String m_prodAssetText = "";	
	
	//Ordem em que a classe de ativo deve aparecer no Relatorio pa ra Clientes.
	private String m_assetClassCustRptOrderNbr = "";

	/**
	 * @param String prodAssetCode_.
	 * Seta o Codigo de Qualificacao do Produto Private.
	 */
	public void setProdAssetCode(String prodAssetCode_) {
		m_prodAssetCode = prodAssetCode_;
	}
	/**
	 * @return Retorna o Codigo de Qualificacao do Produto Private.
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
	 * @return Retorna a Descricao da Qualificacao do Produto Private.
	 */
	public String getProdAssetText() {
		return m_prodAssetText;
	}

	/**
	 * @param String prodAssetText_.
	 * Seta a Descricao da Qualificacao do Produto Private.
	 */
	public void setProdAssetText(String prodAssetText_) {
		m_prodAssetText = prodAssetText_;
	}

	/**
	 * @return Retorna o Codigo de Qualificacao do Produto Private Selecionado.
	 */
	public String getSelectedProdAssetCode() {

		return null;
	}

	/**
	 * @param String setSelectedProdAssetCode_.
	 * Seta o Codigo de Qualificacao do Produto Private Selecionado.
	 */
		
		
	public void setSelectedProdAssetCode(String setSelectedProdAssetCode_) {

		m_prodAssetCode = setSelectedProdAssetCode_;

	}

	/*
	 * Realiza as validações de tipos e tamanhos
	 */
	public ActionErrors validate(
		ActionMapping actionMapping_,
		HttpServletRequest request_) {
		ActionErrors errors = new ActionErrors();

		/*ODSValidator.validateBigInteger(
										  BaseProdAssetPrvtDetailFncVO.C_PROD_Asset_CODE_DESCRIPTION,
										  m_prodAssetCode,
										  BaseTplProdAssetPrvtEntity.C_PROD_Asset_CODE_SIZE,
										  errors );
		
		ODSValidator.validateMaxLength(
										 BaseProdAssetPrvtDetailFncVO.C_PROD_Asset_TEXT_DESCRIPTION,
										 m_prodAssetText,
										 BaseTplProdAssetPrvtEntity.C_PROD_Asset_TEXT_SIZE,
										 errors );
										*/

		return errors;
	}
	
	/**
	 * @return Retorna a Ordem em que a classe de ativo deve aparecer no Relatorio para Clientes.
	 */
	public String getAssetClassCustRptOrderNbr() {
		return m_assetClassCustRptOrderNbr;
	}

	/**
	 * @param String m_assetClassCustRptOrderNbr_.
	 * Seta a Ordem em que a classe de ativo deve aparecer no Relatorio pa ra Clientes.
	 */
	public void setAssetClassCustRptOrderNbr(String m_assetClassCustRptOrderNbr_) {
		m_assetClassCustRptOrderNbr = m_assetClassCustRptOrderNbr_;
	}

}
