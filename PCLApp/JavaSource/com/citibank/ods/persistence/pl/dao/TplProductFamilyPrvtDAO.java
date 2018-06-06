/*
 * Created on Mar 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProductFamilyPrvtEntity;

/**
 * @author leonardo.nakada
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface TplProductFamilyPrvtDAO extends BaseTplProductFamilyPrvtDAO
{
  public DataSet list( BigInteger prodFamlCode_, String prodFamlName_,
                      String prodFamlText_ );

  public TplProductFamilyPrvtEntity insert(
                                           TplProductFamilyPrvtEntity tplProductFamilyPrvtEntity_ );

  public void update( TplProductFamilyPrvtEntity tplProductFamilyPrvtEntity_ );

  public void delete( TplProductFamilyPrvtEntity tplProductFamilyPrvtEntity_ );

  public boolean exists( TplProductFamilyPrvtEntity tplProductFamilyPrvtEntity_ );
  
  public boolean existsActive( TplProductFamilyPrvtEntity tplProductFamilyPrvtEntity_ );
  
  public DataSet loadDomain();
}