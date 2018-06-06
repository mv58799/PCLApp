/*
 * Created on Mar 4, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplErEmHistEntity;

/**
 * @author fabio.luppi.gil
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface TplErEmHistDAO extends BaseTplErEmDAO
{

  public TplErEmHistEntity insert( TplErEmHistEntity erEmEntity_ );

  public DataSet list( String emNbrSrc_, String erNbrSrc_, Date erEmRefDate_,
                      BigInteger custNbr_, String custFullName_,
                      BigInteger reltnNbr_, BigInteger curAcctNbr_ );

  public DataSet listHistory( TplErEmHistEntity erEmEntity_ );
}