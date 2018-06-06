/*
 * Created on 19/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodsubasset.form;

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
    public class BaseProdSubAssetDetailForm	extends BaseForm implements ProdSubAssetDetailable {

	//Data e hora da ultima atualizaca efetuada pelo usuario.
	private String m_lastUpdDate = "";

	//Codigo do usuario que efetuou a ultima atualizacao no registro.
	private String m_lastUpdUserId = "";

	//Codigo de Qualificacao do Produto Private.
	private String m_prodSubAssetCode = "";

	//Descricao da Qualificacao do Produto Private.
	private String m_prodSubAssetText = "";

	//Domain da Sub Classe de Ativos.
	private DataSet m_prodAssetCodeDomain = null;

	//Domain da Sub Classe de Ativos.
	private String m_prodAssetCode = null;
	
	//Ordem em que a Sub Classe de Ativo deve Aparecer no Relatorio para Clientes.
	private String m_subAssetClassRptOrderNbr = null;

	private String m_ixCode;
	  
	public String getIxCode() {
		return m_ixCode;
	}
	
	public void setIxCode(String m_ixCode) {
		this.m_ixCode = m_ixCode;
	}
	/**
	 * @param DataSet prodAssetCodeDomain_.
	 * Seta o Domain da Sub Classe de Ativos.
	 */
	public void setProdAssetCodeDomain(DataSet prodAssetCodeDomain_) {
		m_prodAssetCodeDomain = prodAssetCodeDomain_;
	}

	/**
	 * @return Retorna o Domain da Sub Classe de Ativos.
	 */
	public DataSet getProdAssetCodeDomain() {
		return m_prodAssetCodeDomain;
	}

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
	 * @return Retorna o Codigo de Qualificacao do Produto Private.
	 */
	public String getProdSubAssetCode() {
		return m_prodSubAssetCode;
	}

	/**
	  * @param String prodSubAssetCode_.
	  * Seta o Codigo de Qualificacao do Produto Private.
	  */
	public void setProdSubAssetCode(String prodSubAssetCode_) {
		m_prodSubAssetCode = prodSubAssetCode_;
	}

	/**
	  * @return Retorna a Descricao da Qualificacao do Produto Private.
	  */
	public String getProdSubAssetText() {
		return m_prodSubAssetText;
	}

	/**
	  * @param String prodSubAssetText_.
	  * Seta a Descricao da Qualificacao do Produto Private.
	  */
	public void setProdSubAssetText(String prodSubAssetText_) {
		m_prodSubAssetText = prodSubAssetText_;
	}

	/**
	  * @return Retorna a Seleção da lista da Sub Asset.
	  */
	public String getSelectedProdSubAssetCode() {

		return null;
	}

	/**
	  * @param String setSelectedProdSubAssetCode_.
	  * Seta a Seleção da lista da Sub Asset.
	  */
	public void setSelectedProdSubAssetCode(String setSelectedProdSubAssetCode_) {

		m_prodSubAssetCode = setSelectedProdSubAssetCode_;

	}

	/*
	 * Realiza as validações de tipos e tamanhos
	 */
	public ActionErrors validate(
		ActionMapping actionMapping_,
		HttpServletRequest request_) {
		ActionErrors errors = new ActionErrors();

		/*ODSValidator.validateBigInteger(
										  BaseProdSubAssetPrvtDetailFncVO.C_PROD_SubAsset_CODE_DESCRIPTION,
										  m_prodSubAssetCode,
										  BaseTplProdSubAssetPrvtEntity.C_PROD_SubAsset_CODE_SIZE,
										  errors );
		
		ODSValidator.validateMaxLength(
										 BaseProdSubAssetPrvtDetailFncVO.C_PROD_SubAsset_TEXT_DESCRIPTION,
										 m_prodSubAssetText,
										 BaseTplProdSubAssetPrvtEntity.C_PROD_SubAsset_TEXT_SIZE,
										 errors );
										*/

		return errors;
	}

	/**
	  * @return Retorna a Ordem em que a Sub Classe de Ativo deve Aparecer no Relatorio para Clientes.
	  */
	public String getSubAssetClassRptOrderNbr() {
		return m_subAssetClassRptOrderNbr;
	}

	/**
	  * @param String m_subAssetClassRptOrderNbr_.
	  * Seta a Ordem em que a Sub Classe de Ativo deve Aparecer no Relatorio para Clientes.
	  */
	public void setSubAssetClassRptOrderNbr(String m_subAssetClassRptOrderNbr_) {
		m_subAssetClassRptOrderNbr = m_subAssetClassRptOrderNbr_;
	}

}
