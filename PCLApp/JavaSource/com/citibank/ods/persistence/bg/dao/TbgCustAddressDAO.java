package com.citibank.ods.persistence.bg.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.bg.TbgCustAddressEntity;

/**
 * @author hamilton.matos
 */

public interface TbgCustAddressDAO extends BaseTbgCustAddressDAO
{
  public void update( TbgCustAddressEntity custAddressEntity_ );

  public void delete( TbgCustAddressEntity custAddressEntity_ );

  public TbgCustAddressEntity insert( TbgCustAddressEntity custAddressEntity_ );

  public DataSet list( BigInteger custNbr_, BigInteger custCpfCnpjNbr_,
                      String custFullNameText_ );

}