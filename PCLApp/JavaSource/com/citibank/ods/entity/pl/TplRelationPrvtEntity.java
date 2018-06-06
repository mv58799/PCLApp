/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplRelationPrvtEntityVO;
/**
 * @author fernando.salgado
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */

public class TplRelationPrvtEntity extends BaseTplRelationPrvtEntity
{
  /*
   * Clientes agregados ao relacionamento
   */
  private TplCustomerPrvtEntity m_customerPrvtCmplEntity1;

  private TplCustomerPrvtEntity m_customerPrvtCmplEntity2;

  private TplCustomerPrvtEntity m_customerPrvtCmplEntity3;

  private TplCustomerPrvtEntity m_customerPrvtCmplEntity4;

  private TplCustomerPrvtEntity m_customerPrvtCmplEntity5;

  public TplRelationPrvtEntity()
  {
    m_data = new TplRelationPrvtEntityVO();
  }

  /**
   * @return Returns the customerPrvtCmplEntity1.
   */
  public TplCustomerPrvtEntity getCustomerPrvtCmplEntity1()
  {
    return m_customerPrvtCmplEntity1;
  }

  /**
   * @param customerPrvtCmplEntity1_ The customerPrvtCmplEntity1 to set.
   */
  public void setCustomerPrvtCmplEntity1(
                                         TplCustomerPrvtEntity customerPrvtCmplEntity1_ )
  {
    m_customerPrvtCmplEntity1 = customerPrvtCmplEntity1_;
  }

  /**
   * @return Returns the customerPrvtCmplEntity2.
   */
  public TplCustomerPrvtEntity getCustomerPrvtCmplEntity2()
  {
    return m_customerPrvtCmplEntity2;
  }

  /**
   * @param customerPrvtCmplEntity2_ The customerPrvtCmplEntity2 to set.
   */
  public void setCustomerPrvtCmplEntity2(
                                         TplCustomerPrvtEntity customerPrvtCmplEntity2_ )
  {
    m_customerPrvtCmplEntity2 = customerPrvtCmplEntity2_;
  }

  /**
   * @return Returns the customerPrvtCmplEntity3.
   */
  public TplCustomerPrvtEntity getCustomerPrvtCmplEntity3()
  {
    return m_customerPrvtCmplEntity3;
  }

  /**
   * @param customerPrvtCmplEntity3_ The customerPrvtCmplEntity3 to set.
   */
  public void setCustomerPrvtCmplEntity3(
                                         TplCustomerPrvtEntity customerPrvtCmplEntity3_ )
  {
    m_customerPrvtCmplEntity3 = customerPrvtCmplEntity3_;
  }

  /**
   * @return Returns the customerPrvtCmplEntity4.
   */
  public TplCustomerPrvtEntity getCustomerPrvtCmplEntity4()
  {
    return m_customerPrvtCmplEntity4;
  }

  /**
   * @param customerPrvtCmplEntity4_ The customerPrvtCmplEntity4 to set.
   */
  public void setCustomerPrvtCmplEntity4(
                                         TplCustomerPrvtEntity customerPrvtCmplEntity4_ )
  {
    m_customerPrvtCmplEntity4 = customerPrvtCmplEntity4_;
  }

  /**
   * @return Returns the customerPrvtCmplEntity5.
   */
  public TplCustomerPrvtEntity getCustomerPrvtCmplEntity5()
  {
    return m_customerPrvtCmplEntity5;
  }

  /**
   * @param customerPrvtCmplEntity5_ The customerPrvtCmplEntity5 to set.
   */
  public void setCustomerPrvtCmplEntity5(
                                         TplCustomerPrvtEntity customerPrvtCmplEntity5_ )
  {
    m_customerPrvtCmplEntity5 = customerPrvtCmplEntity5_;
  }
}