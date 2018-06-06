package com.citibank.ods.modules.client.ipdocprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.IpDocPrvtHistoryListFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.contactcust.form;
 * @version 1.0
 * @author l.braga,26/03/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class IpDocPrvtHistoryListForm extends BaseIpDocPrvtListForm
{

  // Data de referencia do historico
  private String m_ipDocRefDateSrc;

  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = super.validate( actionMapping_, request_ );

    ODSValidator.validateDate(
                               IpDocPrvtHistoryListFncVO.C_IP_DOC_REF_DATE_DESCRIPTION,
                               m_ipDocRefDateSrc, errors );

    return errors;
  }

  /**
   * @return Returns ipDocRefDateSrc.
   */
  public String getIpDocRefDateSrc()
  {
    return m_ipDocRefDateSrc;
  }

  /**
   * @param ipDocRefDateSrc_ Field ipDocRefDateSrc to be setted.
   */
  public void setIpDocRefDateSrc( String ipDocRefDateSrc_ )
  {
    m_ipDocRefDateSrc = ipDocRefDateSrc_;
  }
}