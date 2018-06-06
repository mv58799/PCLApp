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
import com.citibank.ods.entity.pl.TplProductFamilyPrvtHistEntity;

/**
 * @author leonardo.nakada
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface TplProductFamilyPrvtHistDAO extends
    BaseTplProductFamilyPrvtDAO
{
  public DataSet list( BigInteger prodFamlCode_, String prodFamlName_,
                      String prodFamlText_, Date m_prodFamlRefDate_ );

  public TplProductFamilyPrvtHistEntity insert(
                                               TplProductFamilyPrvtHistEntity familyPrvtHistEntity_ );
}