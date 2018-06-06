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
  
  // Código do Usuário que efetuou a última atualização registro
  private String m_lastUpdUserIdSrc;

  // Variável utilizada no controle do botão de alteração
  private String m_countUsr;

  // Variável de controle para ação de alteração
  private String m_isUpdate;

  // Variável de controle para ação de aprovação
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
   * Realiza validação de tamanho de campo
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {

    ActionErrors errors = new ActionErrors();

    errors = super.validate( actionMapping_, request_ );

    return errors;
  }
}