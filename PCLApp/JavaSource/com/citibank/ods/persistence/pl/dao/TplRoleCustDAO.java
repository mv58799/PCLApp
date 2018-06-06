package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.dataset.DataSet;

/**
 * @author leonardo.nakada
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface TplRoleCustDAO extends BaseTplRoleCustDAO
{
  public DataSet loadDomain();
}