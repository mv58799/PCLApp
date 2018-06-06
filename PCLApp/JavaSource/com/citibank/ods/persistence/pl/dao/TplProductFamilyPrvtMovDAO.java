/*
 * Created on Mar 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProductFamilyPrvtMovEntity;

/**
 * @author leonardo.nakada
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface TplProductFamilyPrvtMovDAO extends BaseTplProductFamilyPrvtDAO
{
  public DataSet list( BigInteger prodFamlCode_, String prodFamlName_,
                      String prodFamlText_, String lastUpdUserId_ );

  public TplProductFamilyPrvtMovEntity insert(
                                              TplProductFamilyPrvtMovEntity productFamilyPrvtMovEntity_ );

  public void update( TplProductFamilyPrvtMovEntity productFamilyPrvtMovEntity_ );

  public void delete( TplProductFamilyPrvtMovEntity productFamilyPrvtMovEntity_ );

  public boolean exists(
                        TplProductFamilyPrvtMovEntity productFamilyPrvtMovEntity_ );
}