/*
 * Created on 22/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author rcoelho
 * @since 22/08/2008
 */
public class BaseTplProdAssetTypeEntityVO extends BaseEntityVO {

	/**
	  * Codigo do Tipo da Classe.
	  */
	private BigInteger m_prodAssetTypeCode;

	/**
	  * Descrição do Tipo da Classe.
	  */
	private String m_prodAssetTypeText;

	/**
	  * Codigo do Usuário da última alteração.
	  */
	private String m_lastUpdUserId;

	/**
	  * Data da Última Alteração da Qualificação do Produto.
	  */
	private Date m_lastUpdDate;

	/**
	  * Codigo do Usuário da Autorização Última Atualização da Qualificação do Produto.
	  */
	private String m_lastAuthUserId;

	/**
	  * Data da Autorização da Última Atualização da Qualificação do Produto.
	  */
	private Date m_lastAuthDate;

	/**
	  * Codigo do Status da Qualificação do Produto.
	  */
	private String m_recStatCode;

	/**
	  * Codigo de Qualificacao do Produto Private.
	  */
	private BigInteger m_prodAssetCode;
	
	/**
	  * Código da Sub Classe Asset.
	  */
	private BigInteger m_prodSubAssetCode;
	
	/**
	  * Ordem em que o tipo de ativo deve aprarecer no relatorio para clientes.
	  */
	private BigInteger m_assetTypeCustRptOrderNbr;
	
	/**
	  * Data de Referencia do registro no historico.
	  */
	private Date m_assetTypeRefDate;
	
	/**
	 * @param BigInteger prodAssetCode_.
	 * Seta o Código da Classe Asset.
	 */
	public void setProdAssetCode(BigInteger prodAssetCode_) {
		m_prodAssetCode = prodAssetCode_;
	}

	/**
	 * @return Retorna o Codigo da Classe. 
	 */
	public BigInteger getProdAssetCode() {
		return m_prodAssetCode;
	}

	/**
	 * @return Retorna a Data da Autorização da Última Atualização da Qualificação do Produto.
	 */
	public Date getLastAuthDate() {
		return m_lastAuthDate;
	}
	/**
	 * @param Date authDate-.
	 * Seta a Data da Autorização da Última Atualização da Qualificação do Produto.
	 */
	public void setLastAuthDate(Date authDate_) {
		m_lastAuthDate = authDate_;
	}
	/**
	 * @return Retorna o Codigo do Usuário da Autorização Última Atualização da Qualificação do Produto.
	 */
	public String getLastAuthUserId() {
		return m_lastAuthUserId;
	}
	/**
	 * @param String authUserId_.
	 * Seta o Codigo do Usuário da Autorização Última Atualização da Qualificação do Produto.
	 */
	public void setLastAuthUserId(String authUserId_) {
		m_lastAuthUserId = authUserId_;
	}
	/**
	 * @return Retorna a Data da Última Alteração da Qualificação do Produto.
	 */
	public Date getLastUpdDate() {
		return m_lastUpdDate;
	}
	/**
	 * @param Date updDate_.
	 * Seta a Data da Última Alteração da Qualificação do Produto.
	 */
	public void setLastUpdDate(Date updDate_) {
		m_lastUpdDate = updDate_;
	}
	/**
	 * @return Retorna o Codigo do Usuário da última alteração.
	 */
	public String getLastUpdUserId() {
		return m_lastUpdUserId;
	}
	/**
	 * @param String updUserId_.
	 * Seta o Codigo do Usuário da última alteração.
	 */
	public void setLastUpdUserId(String updUserId_) {
		m_lastUpdUserId = updUserId_;
	}
	/**
	 * @return Retorna o Codigo do Tipo da Classe.
	 */
	public BigInteger getProdAssetTypeCode() {
		return m_prodAssetTypeCode;
	}
	/**
	 * @param BigInteger AssetTypeCode_.
	 * Seta o Codigo do Tipo da Classe.
	 */
	public void setProdAssetTypeCode(BigInteger AssetTypeCode_) {
		m_prodAssetTypeCode = AssetTypeCode_;
	}
	/**
	 * @return Retorna a Descrição do Tipo da Classe.
	 */
	public String getProdAssetTypeText() {
		return m_prodAssetTypeText;
	}
	/**
	 * @param AssetTypeText_.
	 * Seta a Descrição do Tipo da Classe.
	 */
	public void setProdAssetTypeText(String AssetTypeText_) {
		m_prodAssetTypeText = AssetTypeText_;
	}
	/**
	 * @return Retorna o Codigo do Status da Qualificação do Produto.
	 */
	public String getRecStatCode() {
		return m_recStatCode;
	}
	/**
	 * @param String statCode_.
	 * Seta o Codigo do Status da Qualificação do Produto.
	 */
	public void setRecStatCode(String statCode_) {
		m_recStatCode = statCode_;
	}
	/**
	 * @return Retorna o Código da Sub Classe Asset.
	 */
	public BigInteger getProdSubAssetCode() {
		return m_prodSubAssetCode;
	}

	/**
	 * @param BigInteger integer.
	 * Seta Código da Sub Classe Asset.
	 */
	public void setProdSubAssetCode(BigInteger integer) {
		m_prodSubAssetCode = integer;
	}

	/**
	 * @return Retorna a Ordem em que o tipo de ativo deve aprarecer no relatorio para clientes.
	 */
	public BigInteger getAssetTypeCustRptOrderNbr() {
		return m_assetTypeCustRptOrderNbr;
	}

	/**
	 * @param BigInteger m_assetTypeCustRptOrderNbr_.
	 * Seta a Ordem em que o tipo de ativo deve aprarecer no relatorio para clientes.
	 */
	public void setAssetTypeCustRptOrderNbr(BigInteger m_assetTypeCustRptOrderNbr_) {
		m_assetTypeCustRptOrderNbr = m_assetTypeCustRptOrderNbr_;
	}

	/**
	 * @return Retorna a Data de Referencia do registro no historico.
	 */
	public Date getAssetTypeRefDate() {
		return m_assetTypeRefDate;
	}

	/**
	 * @param Date m_assetTypeRefDate_.
	 * Seta a Data de Referencia do registro no historico.
	 */
	public void setAssetTypeRefDate(Date m_assetTypeRefDate_) {
		m_assetTypeRefDate = m_assetTypeRefDate_;
	}

}