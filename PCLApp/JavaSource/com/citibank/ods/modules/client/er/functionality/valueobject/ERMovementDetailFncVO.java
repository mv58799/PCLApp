/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.modules.client.er.functionality.valueobject;

import com.citibank.ods.entity.pl.TplErMovEntity;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class ERMovementDetailFncVO extends BaseERDetailFncVO
{
  private String m_currentOpernCode;

  /**
   * Construtor
   */
  public ERMovementDetailFncVO()
  {
	baseTplErEntity = new TplErMovEntity();
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

  public void setFromSearch(boolean b) 
  {
	
  }

  public void setClickedSearch(String nextPage)
  {

		
  }
}