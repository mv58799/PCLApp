package com.citibank.ods.modules.client.erem.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.modules.client.erem.functionality.valueobject.EREMHistoryListFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.erem.form;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 9, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class EREMHistoryListForm extends BaseEREMListForm
{

  private String m_EREMRefDateSrc = "";

  private String m_selectedEREMRefDate = "";

  private String m_erNbrHistSrc = "";

  private String m_emNbrHistSrc = "";

  private boolean m_isFromCurrent;

  /**
   * @return Returns selectedEREMRefDate.
   */
  public String getSelectedEREMRefDate()
  {
    return m_selectedEREMRefDate;
  }

  /**
   * @param selectedEREMRefDate_ Field selectedEREMRefDate to be setted.
   */
  public void setSelectedEREMRefDate( String selectedEREMRefDate_ )
  {
    m_selectedEREMRefDate = selectedEREMRefDate_;
  }

  /**
   * @return Returns erEmRefDate.
   */
  public String getEREMRefDateSrc()
  {
    return m_EREMRefDateSrc;
  }

  /**
   * @param erEmRefDate_ Field erEmRefDate to be setted.
   */
  public void setEREMRefDateSrc( String EREMRefDateSrc_ )
  {
    m_EREMRefDateSrc = EREMRefDateSrc_;
  }

  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = super.validate( actionMapping_, request_ );

    ODSValidator.validateDate(
                               EREMHistoryListFncVO.C_ER_EM_REF_DATE_DESCRIPTION,
                               m_EREMRefDateSrc, errors );

    return errors;
  }

  public void setSelectedERNbr( String selectedERNbr_ )
  {
    if ( selectedERNbr_ != null && !selectedERNbr_.equals( "" ) )
    {
      m_erNbrHistSrc = selectedERNbr_;
      setFromCurrent( true );
    }
  }

  public void setSelectedEMNbr( String selectedEMNbr_ )
  {
    if ( selectedEMNbr_ != null && !selectedEMNbr_.equals( "" ) )
    {
      m_emNbrHistSrc = selectedEMNbr_;
    }
  }

  public String getEmNbrHistSrc()
  {
    return m_emNbrHistSrc;
  }

  public void setEmNbrHistSrc( String emNbrHistSrc_ )
  {
    m_emNbrHistSrc = emNbrHistSrc_;
  }

  public String getErNbrHistSrc()
  {
    return m_erNbrHistSrc;
  }

  public void setErNbrHistSrc( String erNbrHistSrc_ )
  {
    m_erNbrHistSrc = erNbrHistSrc_;
  }

  public boolean isFromCurrent()
  {
    return m_isFromCurrent;
  }

  public void setFromCurrent( boolean isFromCurrent_ )
  {
    m_isFromCurrent = isFromCurrent_;
  }
}