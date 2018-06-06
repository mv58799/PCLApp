package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplContactCustEntity;

/**
 * @author Hamilton Matos
 */

public interface BaseTplContactCustDAO
{
  public BaseTplContactCustEntity find(
                                       BaseTplContactCustEntity baseTplContactCustEntity_ )
                                                                                           throws UnexpectedException;
}

