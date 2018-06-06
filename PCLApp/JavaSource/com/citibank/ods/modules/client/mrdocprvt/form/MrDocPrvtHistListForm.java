package com.citibank.ods.modules.client.mrdocprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.TplMrDocPrvtHistEntity;
import com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject.MrDocPrvtHistListFncVO;

//
//�2002-2007 Accenture. All rights reserved. 
//
/**
 * Form da classe MrDocPrvtHistListView.jsp
 * 
 * @author michele.monteiro,24/04/2007
 *  
 */

public class MrDocPrvtHistListForm extends BaseMrDocPrvtListForm
{

  //C�digo do documento MR
  private String m_mrDocCodeSrc = "";

  //Data de refer�ncia do hist�rico
  private String m_mrDocRefDateSrc = "";

  //C�digo do documento MR selecionado.
  private String m_selectedMrDocCode = "";

  //Data de refer�ncia do documento MR.
  private String m_selectedMrDocRefDate = "";

  /**
   * @return Retorna o c�digo do documento MR.
   */
  public String getMrDocCodeSrc()
  {
    return m_mrDocCodeSrc;
  }

  /**
   * @param mrDocCode_.Seta o c�digo do documento MR.
   */
  public void setMrDocCodeSrc( String mrDocCode_ )
  {
    m_mrDocCodeSrc = mrDocCode_;
  }

  /**
   * @return Retorna a data de refer�ncia do hist�rico.
   */
  public String getMrDocRefDateSrc()
  {
    return m_mrDocRefDateSrc;
  }

  /**
   * @param mrDocRefDate_. Seta a data de refer�ncia do hist�rico.
   */
  public void setMrDocRefDateSrc( String mrDocRefDate_ )
  {
    m_mrDocRefDateSrc = mrDocRefDate_;
  }

  /**
   * @return Retorna o n�mero do documento MR selecionado.
   */
  public String getSelectedMrDocCode()
  {
    return m_selectedMrDocCode;
  }

  /**
   * @param selectedMrDocCode_.Seta o n�mero do documento MR selecionado.
   */
  public void setSelectedMrDocCode( String selectedMrDocCode_ )
  {
    m_selectedMrDocCode = selectedMrDocCode_;
  }

  /**
   * @return Retorna a data de refer�ncia do Hist�rico.
   */
  public String getSelectedMrDocRefDate()
  {
    return m_selectedMrDocRefDate;
  }

  /**
   * @param selectedMrDocRefDate_.Seta a data de refer�ncia do Hist�rico.
   */
  public void setSelectedMrDocRefDate( String selectedMrDocRefDate_ )
  {
    m_selectedMrDocRefDate = selectedMrDocRefDate_;
  }

  /**
   * Realiza as valida��es de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = super.validate( actionMapping_, request_ );

    ODSValidator.validateMaxLength( MrDocPrvtHistListFncVO.C_MR_DOC_CODE,
                                    m_mrDocCodeSrc,
                                    TplMrDocPrvtHistEntity.C_MR_DOC_CODE_SIZE,
                                    errors );
    ODSValidator.validateDate( MrDocPrvtHistListFncVO.C_MR_DATE_REF,
                               m_mrDocRefDateSrc, errors );

    return errors;
  }
}