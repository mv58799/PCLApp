/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplProdRiskCatPrvtEntityVO;

/**
 * @author fernando.salgado
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class TplProdRiskCatPrvtEntity extends BaseTplProdRiskCatPrvtEntity
{
  /**
   * Construtor padrão - sem argumentos 
   */
  public TplProdRiskCatPrvtEntity()
  {
    m_data = new TplProdRiskCatPrvtEntityVO();
  }

  /**
   * Construtor - Carrega os atributos com os atributos do movimento
   */
  public TplProdRiskCatPrvtEntity(
                                  TplProdRiskCatPrvtMovEntity tplProdRiskCatPrvtMovEntity_,
                                  Date lastAuthDate_, String lastAuthUserID_,
                                  String recStatCode_ )
  {
    m_data = new TplProdRiskCatPrvtEntityVO();
    
    m_data.setProdRiskCatCode(tplProdRiskCatPrvtMovEntity_.getData().getProdRiskCatCode());
    m_data.setProdRiskCatText(tplProdRiskCatPrvtMovEntity_.getData().getProdRiskCatText());
    m_data.setLastUpdDate(tplProdRiskCatPrvtMovEntity_.getData().getLastUpdDate());
    m_data.setLastUpdUserID(tplProdRiskCatPrvtMovEntity_.getData().getLastUpdUserID());
    ((TplProdRiskCatPrvtEntityVO)m_data).setLastAuthDate(lastAuthDate_);
    ((TplProdRiskCatPrvtEntityVO)m_data).setLastAuthUserID(lastAuthUserID_);
    ((TplProdRiskCatPrvtEntityVO)m_data).setRecStatCode(recStatCode_);
  }
}