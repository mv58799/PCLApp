package com.citibank.ods.modules.client.officercmpl.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.TplOfficerCmplMovementEntity;
import com.citibank.ods.modules.client.officercmpl.functionality.valueobject.BaseOfficerCmplListFncVO;

/**
 * @author bruno.zanetti
 *  
 */
public class OfficerCmplMovementListForm extends BaseOfficerCmplListForm
{

  private String m_lastUpdUserIdSrc = null;

  /**
   * @return Returns lastUpdUserIdSrc.
   */
  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }

  /**
   * @param lastUpdUserIdSrc_ Field lastUpdUserIdSrc to be setted.
   */
  public void setLastUpdUserIdSrc( String lastUpdUserIdSrc_ )
  {
    m_lastUpdUserIdSrc = lastUpdUserIdSrc_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.officercmpl.form.BaseOfficerCmplListForm#validate(org.apache.struts.action.ActionMapping,
   *      javax.servlet.http.HttpServletRequest)
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = super.validate( actionMapping_, request_ );

    ODSValidator.validateMaxLength(
                                    BaseOfficerCmplListFncVO.C_LAST_UPD_USER_ID_DESCRIPTION,
                                    m_lastUpdUserIdSrc,
                                    TplOfficerCmplMovementEntity.C_LAST_UPD_USER_ID_SIZE,
                                    errors );

    return errors;
  }
}