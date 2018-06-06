/*
 * Created on Mar 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdSubFamlPrvtHistEntity;

/**
 * @author leonardo.nakada
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface TplProdSubFamlPrvtHistDAO extends
    BaseTplProdSubFamlPrvtDAO
{
  public DataSet list( BigInteger prodSubFamlCode_, String prodSubFamlName_,
                      String prodSubFamlText_, Date m_prodSubFamlRefDate_ );

  public TplProdSubFamlPrvtHistEntity insert(
                                                  TplProdSubFamlPrvtHistEntity subFamilyPrvtHistEntity_ );
}