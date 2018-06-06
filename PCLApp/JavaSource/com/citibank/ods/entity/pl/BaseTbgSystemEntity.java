package com.citibank.ods.entity.pl;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTbgSystemEntityVO;
import com.citibank.ods.persistence.pl.dao.BaseTbgSystemDAO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@author michele.monteiro,02/05/2007
 *
 */

public class BaseTbgSystemEntity extends BaseODSEntity implements BaseTbgSystemDAO
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
  

  /* (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.rdb.oracle.BaseTbgSystemDAO#find(com.citibank.ods.entity.pl.BaseTbgSystemEntity)
   */
  public BaseTbgSystemEntity find( BaseTbgSystemEntity systemEntity_ )
  {
    return null;
  }
  /* (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.rdb.oracle.BaseTbgSystemDAO#loadSysCodeDomain()
   */
  public DataSet loadSysCodeDomain()
  {

    return null;
  }
}
