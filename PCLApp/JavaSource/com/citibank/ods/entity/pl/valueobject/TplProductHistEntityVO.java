/* Created on Apr 14, 2007
 *
 */
package com.citibank.ods.entity.pl.valueobject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerHistEntity;
import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerMovEntity;

/**
 * @author acacio.domingos
 *  
 */
public class TplProductHistEntityVO extends BaseTplProductEntityVO
{
  /**
   * Data e Hora que o usuario aprovou o registro cadastrado
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_lastAuthDate;

  /**
   * Codigo do usuario (SOE ID) que aprovou o cadastro do registro
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_lastAuthUserId;

  /**
   * Status Registro - Identifica se o registro esta ativo, inativo ou aguar
   * dando aprovacao"
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_recStatCode;

  /**
   * Data de Referencia do registro no historico.
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_prodRefDate;
  
  //Lista de Emissores do Produto
  private List<TplRiskFamilyProdPlayerHistEntity> listTplRiskFamilyProdPlayerHistEntity;

  public TplProductHistEntityVO(){
	  listTplRiskFamilyProdPlayerHistEntity = new ArrayList<TplRiskFamilyProdPlayerHistEntity>();
  }   

  /**
   * @return Returns the lastAuthDate.
   */
  public Date getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * @param lastAuthDate_ The lastAuthDate to set.
   */
  public void setLastAuthDate( Date lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
   * @return Returns the lastAuthUserId.
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * @param lastAuthUserId_ The lastAuthUserId to set.
   */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
  }

  /**
   * @return Returns the ProdRefDate.
   */
  public Date getProdRefDate()
  {
    return m_prodRefDate;
  }

  /**
   * @param prodRefDate__ The prodRefDate to set.
   */
  public void setProdRefDate( Date prodRefDate_ )
  {
    m_prodRefDate = prodRefDate_;
  }

  /**
   * @return Returns the recStatCode.
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * @param recStatCode_ The recStatCode to set.
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }
  
	public List<TplRiskFamilyProdPlayerHistEntity> getListProductPlayerRiskVO() {
		return listTplRiskFamilyProdPlayerHistEntity;
	}
	
	public void setListProductPlayerRiskVO(List<TplRiskFamilyProdPlayerHistEntity> listTplRiskFamilyProdPlayerHistEntity) {
		this.listTplRiskFamilyProdPlayerHistEntity = listTplRiskFamilyProdPlayerHistEntity;
	}   
  
}