/*
 * Created on Apr 23, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.client.erem.functionality.valueobject;

import com.citibank.ods.entity.pl.TplErEmMovEntity;
import com.citibank.ods.entity.pl.TplErEntity;

/**
 * @author leonardo.nakada
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EREMMovementDetailFncVO extends BaseEREMDetailFncVO
{
  private String m_currentOpernCode;

  /**
   * Construtor
   */
  public EREMMovementDetailFncVO()
  {
    m_baseTplErEmEntity = new TplErEmMovEntity();
	baseTplErEntity = new TplErEntity();
  }

  /**
   * @return Returns the currentOpernCode.
   */
  public String getCurrentOpernCode()
  {
    return m_currentOpernCode;
  }

  /**
   * @param currentOpernCode_ The currentOpernCode to set.
   */
  public void setCurrentOpernCode( String currentOpernCode_ )
  {
    m_currentOpernCode = currentOpernCode_;
  }
}