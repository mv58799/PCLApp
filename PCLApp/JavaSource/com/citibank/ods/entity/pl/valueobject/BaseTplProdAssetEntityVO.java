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
   * @author ronaldo.machado
   * @since 20/08/2008
   */
  public class BaseTplProdAssetEntityVO extends BaseEntityVO{

  /**
   * Codigo da Sub Classe.
   */
  private BigInteger m_prodAssetCode;
	
  /**
   * Descrição da Sub Classe.
   */
  private String m_prodAssetText;
	
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
   * Ordem em que a classe de ativo deve aparecer no Relatorio para Clientes.	
   */
  private BigInteger m_assetClassCustRptOrderNbr;	
				
  /**
   * @return Retorna a Data da Última Alteração da Qualificação do Produto.
   */
  public Date getLastAuthDate() 
  {
	return m_lastAuthDate;
  }
  /**
   * @param Date authDate_.
   * Seta a Data da Última Alteração da Qualificação do Produto.
   */
  public void setLastAuthDate(Date authDate_) 
  {
	m_lastAuthDate = authDate_;
  }
  /**
   * @return Retorna o Codigo do Usuário da Autorização Última Atualização da Qualificação do Produto.
   */
  public String getLastAuthUserId() 
  {
	return m_lastAuthUserId;
  }
  /**
   * @param String authUserId_.
   * Seta o Codigo do Usuário da Autorização Última Atualização da Qualificação do Produto.
   */
  public void setLastAuthUserId(String authUserId_) 
  {
	m_lastAuthUserId = authUserId_;
  }
  /**
   * @return Retorna a Data da Última Alteração da Qualificação do Produto.
   */
  public Date getLastUpdDate() 
  {
	return m_lastUpdDate;
  }
  /**
   * @param Date updDate_.
   * Seta a Data da Última Alteração da Qualificação do Produto.
   */
  public void setLastUpdDate(Date updDate_) 
  {
	m_lastUpdDate = updDate_;
  }
  /**
   * @return Retorna o Codigo do Usuário da última alteração.
   */
  public String getLastUpdUserId() 
  {
	return m_lastUpdUserId;
  }
  /**
   * @param String updUserId_.
   * Seta o Codigo do Usuário da última alteração.
   */
  public void setLastUpdUserId(String updUserId_) 
  {
	m_lastUpdUserId = updUserId_;
  }
  /**
   * @return Retorna o Codigo da Sub Classe.
   */
  public BigInteger getProdAssetCode() 
  {
	return m_prodAssetCode;
  }
  /**
   * @param BigInteger AssetCode_.
   * Seta o Codigo da Sub Classe.
   */
  public void setProdAssetCode(BigInteger AssetCode_) 
  {
	m_prodAssetCode = AssetCode_;
  }
  /**
   * @return Retorna a Descrição da Sub Classe.
   */
  public String getProdAssetText() 
  {
	return m_prodAssetText;
  }
  /**
   * @param String AssetText_.
   * Seta a Descrição da Sub Classe.
   */
  public void setProdAssetText(String AssetText_) 
  {
	m_prodAssetText = AssetText_;
  }
  /**
   * @return Retorna o Codigo do Status da Qualificação do Produto.
   */
  public String getRecStatCode() 
  {
	return m_recStatCode;
  }
  /**
   * @param String statCode_.
   * Seta o Codigo do Status da Qualificação do Produto.
   */
  public void setRecStatCode(String statCode_) 
  {
  	m_recStatCode = statCode_;
  }
  /**
   * @return Retorna a Ordem em que a classe de ativo deve aparecer no Relatorio para Clientes.	
   */
  public BigInteger getAssetClassCustRptOrderNbr() 
  {
	return m_assetClassCustRptOrderNbr;
  }

  /**
   * @param BigInteger m_assetClassCustRptOrderNbr_.
   * Seta a Ordem em que a classe de ativo deve aparecer no Relatorio para Clientes.	
   */
  public void setAssetClassCustRptOrderNbr(BigInteger m_assetClassCustRptOrderNbr_) 
  {
	m_assetClassCustRptOrderNbr = m_assetClassCustRptOrderNbr_;
  }

}
