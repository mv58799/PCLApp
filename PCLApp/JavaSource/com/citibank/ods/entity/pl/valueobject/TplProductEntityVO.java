/*
 * Created on Mar 1, 2007
 *
 */
package com.citibank.ods.entity.pl.valueobject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerEntity;

/**
 * @author fernando.salgado
 *  
 */
public class TplProductEntityVO extends BaseTplProductEntityVO
{
  /**
   * Data e Hora que o usuario aprovou o registro cadastrado
   */

  private Date m_lastAuthDate;

  /**
   * Codigo do usuario (SOE ID) que aprovou o cadastro do registro
   */
  private String m_lastAuthUserId;

  /**
   * Status Registro - Identifica se o registro esta ativo, inativo ou aguar
   * dando aprovacao"
   */
  private String m_recStatCode;
  
  //Lista de Emissores do Produto
  private List<TplRiskFamilyProdPlayerEntity> listTplRiskFamilyProdPlayerEntity;

  public TplProductEntityVO(){
	  listTplRiskFamilyProdPlayerEntity = new ArrayList<TplRiskFamilyProdPlayerEntity>();
  }  
  
  /**
   * @return Returns lastAuthDate.
   */
  public Date getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * @param lastAuthDate_ Field lastAuthDate to be setted.
   */
  public void setLastAuthDate( Date lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
   * @return Returns lastAuthUserId.
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * @param lastAuthUserId_ Field lastAuthUserId to be setted.
   */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
  }

  /**
   * @return Returns recStatCode.
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * @param recStatCode_ Field recStatCode to be setted.
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }
  
	public List<TplRiskFamilyProdPlayerEntity> getListProductPlayerRiskVO() {
		return listTplRiskFamilyProdPlayerEntity;
	}
	
	public void setListProductPlayerRiskVO(List<TplRiskFamilyProdPlayerEntity> listTplRiskFamilyProdPlayerEntity) {
		this.listTplRiskFamilyProdPlayerEntity = listTplRiskFamilyProdPlayerEntity;
	}  
  
}