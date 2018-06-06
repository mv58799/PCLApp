/*
 * Created on 20/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author rcoelho
 * @since 20/08/2008
 */
public class BaseTplProdSubAssetEntityVO extends BaseEntityVO {

	/**
	  * Codigo da Sub Classe.
	  */
	private BigInteger m_prodSubAssetCode;

	/**
	  * Descrição da Sub Classe.
	  */
	private String m_prodSubAssetText;

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
	  * Ordem em que a Sub Classe de Ativo deve Aparecer no Relatorio para Clientes.
	  */
	private BigInteger m_subAssetClassRptOrderNbr;
	

	  private String m_ixCode;
	  
	  
	
	public String getIxCode() {
		return m_ixCode;
	}
	
	public void setIxCode(String m_ixCode) {
		this.m_ixCode = m_ixCode;
	}
	
	
	/**
	  * @param BigInteger prodAssetCode_.
	  * Seta o Codigo de Qualificacao do Produto Private.
	  */
	public void setProdAssetCode(BigInteger prodAssetCode_) {
		m_prodAssetCode = prodAssetCode_;
	}

	/**
	  * @return Retorna o Codigo de Qualificacao do Produto Private.
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
	 * @param Data authDate_.
	 * Seta a Data da Autorização da Última Atualização da Qualificação do Produto.
	 */
	public void setLastAuthDate(Date authDate_) {
		m_lastAuthDate = authDate_;
	}
	/**
	 * @return Retorna o Codigo do Usuário da última alteração.
	 */
	public String getLastAuthUserId() {
		return m_lastAuthUserId;
	}
	/**
	 * @param String authUserId_.
	 * Seta o Codigo do Usuário da última alteração.
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
	 * @return Retorna o Codigo do Usuário da Autorização Última Atualização da Qualificação do Produto.
	 */
	public String getLastUpdUserId() {
		return m_lastUpdUserId;
	}
	/**
	 * @param String updUserId_.
	 * Seta o Codigo do Usuário da Autorização Última Atualização da Qualificação do Produto.
	 */
	public void setLastUpdUserId(String updUserId_) {
		m_lastUpdUserId = updUserId_;
	}
	/**
	 * @return Retorna o Codigo da Sub Classe.
	 */
	public BigInteger getProdSubAssetCode() {
		return m_prodSubAssetCode;
	}
	/**
	 * @param BigInteger SubAssetCode_.
	 * Seta o Codigo da Sub Classe.
	 */
	public void setProdSubAssetCode(BigInteger prodSubAssetCode_) {
		m_prodSubAssetCode = prodSubAssetCode_;
	}
	/**
	 * @return Retorna a Descrição da Sub Classe.
	 */
	public String getProdSubAssetText() {
		return m_prodSubAssetText;
	}
	/**
	 * @param SubAssetText_.
	 * Seta a Descrição da Sub Classe.
	 */
	public void setProdSubAssetText(String prodSubAssetText_) {
		m_prodSubAssetText = prodSubAssetText_;
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
	 * @return Retorna a Ordem em que a Sub Classe de Ativo deve Aparecer no Relatorio para Clientes.
	 */
	public BigInteger getSubAssetClassRptOrderNbr() {
		return m_subAssetClassRptOrderNbr;
	}

	/**
	 * @param BigInteger m_subAssetClassRptOrderNbr_.
	 * Seta a Ordem em que a Sub Classe de Ativo deve Aparecer no Relatorio para Clientes.
	 */
	public void setSubAssetClassRptOrderNbr(BigInteger subAssetClassRptOrderNbr_) {
		m_subAssetClassRptOrderNbr = subAssetClassRptOrderNbr_;
	}

}
