package com.citibank.ods.persistence.bg.dao;

import com.citibank.ods.entity.bg.BaseTbgCustAddressEntity;

/**
 * @author hamilton.matos
 */

public interface BaseTbgCustAddressDAO
{
  public BaseTbgCustAddressEntity find(
                                       BaseTbgCustAddressEntity tbgCustAdressEntity_ );
}