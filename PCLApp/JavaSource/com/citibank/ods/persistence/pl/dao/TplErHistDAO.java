/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplErHistEntity;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
  public interface TplErHistDAO extends BaseTplErDAO
  {

  public TplErHistEntity insert( TplErHistEntity erEntity_ );

  public DataSet list( String erNbrSrc_, Date erRefDate_  );

  public DataSet listHistory( TplErHistEntity erEntity_ );
  }