package com.citibank.ods.modules.client.officercmpl.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.form.HistoryDetailable;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.modules.client.officercmpl.functionality.valueobject.BaseOfficerCmplListFncVO;

/**
 * @author bruno.zanetti
 *  
 */
public class OfficerCmplHistoryListForm extends BaseOfficerCmplListForm
    implements HistoryDetailable
{

  private String m_offcrRefDateSrc = null;

  private String m_selectedOffcrRefDate = null;

  /**
   * @return Returns offcrRefDate.
   */
  public String getOffcrRefDateSrc()
  {
    return m_offcrRefDateSrc;
  }

  /**
   * @param offcrRefDate_ Field offcrRefDate to be setted.
   */
  public void setOffcrRefDateSrc( String offcrRefDate_ )
  {
    m_offcrRefDateSrc = offcrRefDate_;
  }

  /**
   * @return Returns selectedOffcrRefDate.
   */
  public String getSelectedRefDate()
  {
    return m_selectedOffcrRefDate;
  }

  /**
   * @param selectedOffcrRefDate_ Field selectedOffcrRefDate to be setted.
   */
  public void setSelectedRefDate( String selectedOffcrRefDate_ )
  {
    m_selectedOffcrRefDate = selectedOffcrRefDate_;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.client.officercmpl.form.BaseOfficerCmplListForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = super.validate( actionMapping_, request_ );

    ODSValidator.validateDate( BaseOfficerCmplListFncVO.C_OFFCR_REF_DATE,
                               m_offcrRefDateSrc, errors );

    return errors;
  }
}