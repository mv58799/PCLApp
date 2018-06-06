/*
 * Created on Dec 13, 2006
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.BaseTplPotentialWealthEntityVO;

import com.citibank.ods.common.entity.BaseODSEntity;
/**
 * @author User
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BaseTplPotentialWealthEntity extends BaseODSEntity {

  protected BaseTplPotentialWealthEntityVO m_data;

  public BaseTplPotentialWealthEntityVO getData()
  {
    return m_data;
  }
}