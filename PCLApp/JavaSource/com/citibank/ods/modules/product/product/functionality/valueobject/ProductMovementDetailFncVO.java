package com.citibank.ods.modules.product.product.functionality.valueobject;

import com.citibank.ods.entity.pl.TplProductCorpMovEntity;
import com.citibank.ods.entity.pl.TplProductMovEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.modules.product.product.functionality.valueobject; 
 *@version 1.0
 *@author atilio.l.araujo,Apr 19, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class ProductMovementDetailFncVO extends BaseProductDetailFncVO
{
  	public ProductMovementDetailFncVO()
  	{
  	  m_baseTplProductEntity = new TplProductMovEntity();
  	  
  	  
  	  baseTplProductCorpEntity = new TplProductCorpMovEntity();
  	}
}
