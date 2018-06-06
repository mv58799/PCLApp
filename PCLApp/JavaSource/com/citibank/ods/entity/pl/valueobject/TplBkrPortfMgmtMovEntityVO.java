package com.citibank.ods.entity.pl.valueobject;

/**
 * Classe que instancia os valores correspondente a um registro da tabela
 * Tpl_Bkr_Portf_Mgmt_Mov
 * @author Hamilton Matos
 */

public class TplBkrPortfMgmtMovEntityVO extends BaseTplBkrPortfMgmtEntityVO
{
  // C�digo de opera��o
  private String m_opernCode;

  /**
   * @return Returns opernCode.
   */
  public String getOpernCode()
  {
    return m_opernCode;
  }

  /**
   * @param opernCode_ Field opernCode to be setted.
   */
  public void setOpernCode( String opernCode_ )
  {
    m_opernCode = opernCode_;
  }
}