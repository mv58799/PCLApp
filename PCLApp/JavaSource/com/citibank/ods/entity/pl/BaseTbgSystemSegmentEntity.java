package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTbgSystemEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.entity; 
 *@version 1.0
 *@author michele.monteiro,02/05/2007
 *

 */

public class BaseTbgSystemSegmentEntity extends BaseODSEntity
{
  
  // A entidade de Interface de Entrada.
  protected BaseTbgSystemEntityVO m_data;

  /**
   * Recupera o entity VO de Interface de Entrada.
   * 
   * @return BaseTbgSystemEntityVO - o entity VO de Interface de Entrada.
   */
  public BaseTbgSystemEntityVO getData()
  {
    return m_data;
  }

}
