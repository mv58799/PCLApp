/*
 * Created on Apr 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.product.functionality.valueobject;

import com.citibank.ods.entity.pl.TplProductCorpEntity;
import com.citibank.ods.entity.pl.TplProductEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplProductEntityVO;

/**
 * @author leonardo.nakada
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ProductDetailFncVO extends BaseProductDetailFncVO
{
  public ProductDetailFncVO()
  {
    m_baseTplProductEntity = new TplProductEntity();
    baseTplProductCorpEntity = new TplProductCorpEntity();
  }

  /**
   * Verifica se é o mesmo objeto. No caso deste submódulo, um produto é
   * considerado igual qdo sua chave é igual (prodCode, sysCode e sysSegCode)
   */
  public boolean equals( Object obj_ )
  {
    boolean isEqual = true;
    ProductDetailFncVO detailFncVO = ( ProductDetailFncVO ) obj_;

    BaseTplProductEntityVO baseTplProductEntityVO = detailFncVO.getBaseTplProductEntity().getData();
    BaseTplProductEntityVO thisBaseTplProductEntityVO = this.getBaseTplProductEntity().getData();

    if ( thisBaseTplProductEntityVO.getProdCode() != null
         && thisBaseTplProductEntityVO.getSysCode() != null
         && thisBaseTplProductEntityVO.getSysSegCode() != null )
    {

      if ( !( thisBaseTplProductEntityVO.getProdCode().equals(
                                                               baseTplProductEntityVO.getProdCode() )
              && thisBaseTplProductEntityVO.getSysCode().equals(
                                                                 baseTplProductEntityVO.getSysCode() ) && thisBaseTplProductEntityVO.getSysSegCode().equals(
                                                                                                                                                             baseTplProductEntityVO.getSysSegCode() ) ) )
      {
        isEqual = false;
      }

    }
    else
    {
      isEqual = false;
    }

    return isEqual;
  }
}