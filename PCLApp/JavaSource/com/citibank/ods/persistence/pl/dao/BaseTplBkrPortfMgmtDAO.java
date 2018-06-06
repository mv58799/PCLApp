package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplBkrPortfMgmtEntity;

/**
 * @author Hamilton Matos
 *  
 */

public interface BaseTplBkrPortfMgmtDAO
{

  /**
   * Métodos Abstratos
   *  
   */

  public BaseTplBkrPortfMgmtEntity find(
                                        BaseTplBkrPortfMgmtEntity baseTplBkrPortfMgmtEntity_ )
                                                                                              throws UnexpectedException;
}