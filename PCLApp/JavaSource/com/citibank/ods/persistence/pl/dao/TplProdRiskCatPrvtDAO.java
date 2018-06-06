/*
 * Created on Mar 14, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdRiskCatPrvtEntity;

/**
 * @author leonardo.nakada
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface TplProdRiskCatPrvtDAO extends BaseTplProdRiskCatPrvtDAO
{
  public DataSet list( BigInteger prodRiskCatCode_, String prodRiskCatText_ );

  public TplProdRiskCatPrvtEntity insert(
                                         TplProdRiskCatPrvtEntity tplProdRiskCatPrvtEntity_ );

  public void update( TplProdRiskCatPrvtEntity tplProdRiskCatPrvtEntity_ );

  public void delete( TplProdRiskCatPrvtEntity tplProdRiskCatPrvtEntity_ );
  
  public boolean exists( TplProdRiskCatPrvtEntity tplProdRiskCatPrvtEntity_ );
  
  public boolean existsActive( TplProdRiskCatPrvtEntity tplProdRiskCatPrvtEntity_ );
  
  public DataSet loadDomain();
}