package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplAggrProdPrvtHistEntityVO;

/**
 * @author fernando.salgado
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class TplAggrProdPrvtHistEntity extends BaseTplAggrProdPrvtEntity
{
  
  /**
   * Comment for <code>tplAggrProdPrvtHistEntityVO</code>
   * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  public TplAggrProdPrvtHistEntity()
  {
    m_data = new TplAggrProdPrvtHistEntityVO();
  }
  
  /**
   * Construtor com a entity base
   * 
   * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  public TplAggrProdPrvtHistEntity(TplAggrProdPrvtEntity aggrProdPrvtEntity_, Date refDate_)
  {
    m_data = new TplAggrProdPrvtHistEntityVO();
    
    m_data.setLastUpdDate(aggrProdPrvtEntity_.getData().getLastUpdDate());
    m_data.setLastUpdUserID(aggrProdPrvtEntity_.getData().getLastUpdUserID());
    m_data.setPrvtProdAggrCode(aggrProdPrvtEntity_.getData().getPrvtProdAggrCode());
    m_data.setPrvtProdAggrText(aggrProdPrvtEntity_.getData().getPrvtProdAggrText());
    m_data.setRecStatCode(aggrProdPrvtEntity_.getData().getRecStatCode());
    ((TplAggrProdPrvtHistEntityVO)m_data).setPrvtProdAggrRefDate(refDate_);
  }
}