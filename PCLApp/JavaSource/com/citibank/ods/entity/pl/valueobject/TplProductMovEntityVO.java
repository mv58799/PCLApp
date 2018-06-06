/*
 * Created on Mar 1, 2007
 *
 */
package com.citibank.ods.entity.pl.valueobject;

import java.util.ArrayList;
import java.util.List;

import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerEntity;
import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerMovEntity;


/**
 * @author fernando.salgado
 * 
 */
public class TplProductMovEntityVO extends BaseTplProductEntityVO
{
  /**
   * Codigo da Operacao realizada no registro: inclusao, alteracao, exclusao
   */
  private String m_opernCode;

  //Lista de Emissores do Produto
  private List<TplRiskFamilyProdPlayerMovEntity> listTplRiskFamilyProdPlayerMovEntity;

  public TplProductMovEntityVO(){
	  listTplRiskFamilyProdPlayerMovEntity = new ArrayList<TplRiskFamilyProdPlayerMovEntity>();
  }    
  
  /**
   * @return Returns the opernCode.
   */
  public String getOpernCode()
  {
    return m_opernCode;
  }

  /**
   * @param opernCode_ The opernCode to set.
   */
  public void setOpernCode( String opernCode_ )
  {
    m_opernCode = opernCode_;
  }
  
	public List<TplRiskFamilyProdPlayerMovEntity> getListProductPlayerRiskVO() {
		return listTplRiskFamilyProdPlayerMovEntity;
	}
	
	public void setListProductPlayerRiskVO(List<TplRiskFamilyProdPlayerMovEntity> listTplRiskFamilyProdPlayerMovEntity) {
		this.listTplRiskFamilyProdPlayerMovEntity = listTplRiskFamilyProdPlayerMovEntity;
	}   
  
}