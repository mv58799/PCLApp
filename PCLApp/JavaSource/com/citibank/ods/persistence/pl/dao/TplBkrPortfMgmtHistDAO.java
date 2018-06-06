package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.entity.pl.TplBkrPortfMgmtHistEntity;

/**
 * 
 * @author Hamilton Matos
 */

public interface TplBkrPortfMgmtHistDAO extends BaseTplBkrPortfMgmtDAO
{
  public TplBkrPortfMgmtHistEntity insert(
                                          TplBkrPortfMgmtHistEntity tplBkrPortfMgmtHistEntity_ );
}