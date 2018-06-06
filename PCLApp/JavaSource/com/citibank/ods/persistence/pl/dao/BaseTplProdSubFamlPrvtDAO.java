/*
 * Created on Mar 18, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.entity.pl.BaseTplProdSubFamlPrvtEntity;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public interface BaseTplProdSubFamlPrvtDAO
{
  public BaseTplProdSubFamlPrvtEntity find(
                                                BaseTplProdSubFamlPrvtEntity baseTplProductFamilyPrvtEntity_ );

  public BaseTplProdSubFamlPrvtEntity findByProdCode(String prodPk);
                                                
}
