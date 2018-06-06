package com.citibank.ods.modules.client.bkrportfmgmt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 * @author Hamilton Matos
 *  
 */

public class BkrPortfMgmtMovementListForm extends BaseBkrPortfMgmtListForm
{
  
  // C�digo do Usu�rio que efetuou a �ltima atualiza��o registro
  private String m_lastUpdUserIdSrc;

  // Vari�vel utilizada no controle do bot�o de altera��o
  private String m_countUsr;

  // Vari�vel de controle para a��o de altera��o
  private String m_isUpdate;

  // Vari�vel de controle para a��o de aprova��o
  private String m_isApprove;

  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }

  public void setLastUpdUserIdSrc( String lastUpdUserIdSrc_ )
  {
    m_lastUpdUserIdSrc = lastUpdUserIdSrc_;
  }

  /**
   * @return Returns countUsr.
   */
  public String getCountUsr()
  {
    return m_countUsr;
  }

  /**
   * @param countUsr_ Field countUsr to be setted.
   */
  public void setCountUsr( String countUsr_ )
  {
    m_countUsr = countUsr_;
  }

  public String getIsApprove()
  {
    return m_isApprove;
  }

  public void setIsApprove( String isApprove_ )
  {
    m_isApprove = isApprove_;
  }

  public String getIsUpdate()
  {
    return m_isUpdate;
  }

  public void setIsUpdate( String isUpdate_ )
  {
    m_isUpdate = isUpdate_;
  }

  /*
   * Realiza valida��o de tamanho de campo
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {

    ActionErrors errors = new ActionErrors();

    errors = super.validate( actionMapping_, request_ );

    return errors;
  }
}