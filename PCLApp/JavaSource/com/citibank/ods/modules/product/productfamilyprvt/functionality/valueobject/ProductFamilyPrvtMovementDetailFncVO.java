/*
 * Created on Mar 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.productfamilyprvt.functionality.valueobject;

import com.citibank.ods.entity.pl.TplProductFamilyPrvtMovEntity;

/**
 * @author leonardo.nakada
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ProductFamilyPrvtMovementDetailFncVO extends
    BaseProductFamilyPrvtDetailFncVO
{
  /**
   * Construtor sem argumento
   */
  public ProductFamilyPrvtMovementDetailFncVO()
  {
    m_baseTplProductFamilyPrvtEntity = new TplProductFamilyPrvtMovEntity();
  }
}