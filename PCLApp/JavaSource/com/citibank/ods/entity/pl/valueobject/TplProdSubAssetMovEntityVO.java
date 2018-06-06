/*
 * Created on 06/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.entity.pl.valueobject;

/**
 * @author lfabiano
 * @since 06/10/2008
 */
public class TplProdSubAssetMovEntityVO extends
	BaseTplProdSubAssetEntityVO
{
  //Codigo da Operação.
  private String m_opernCode;

  /**
   * @return Retorna o Codigo da Operação.
   */
  public String getOpernCode()
  {
	return m_opernCode;
  }

  /**
   * @param String opernCode_.
   * Seta o Codigo da Operação.
   */
  public void setOpernCode( String opernCode_ )
  {
	m_opernCode = opernCode_;
  }
}