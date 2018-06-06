/*
 * Created on Mar 2, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.client.customerprvtcmpl.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject.CustomerPrvtCmplHistoryDetailFncVO;


/**
 * @author bruno.zanetti
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class CustomerPrvtCmplHistoryDetailForm extends BaseCustomerPrvtCmplDetailForm implements CustomerPrvtCmplDetailable 
{
	private String m_custRefDate;	
	
  /**
   * @return Returns custRefDate.
   */
  public String getCustRefDate()
  {
    return m_custRefDate;
  }
  /**
   * @param custRefDate_ Field custRefDate to be setted.
   */
  public void setCustRefDate( String custRefDate_ )
  {
    m_custRefDate = custRefDate_;
  }
  
  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                              HttpServletRequest request_ )
  {
        ActionErrors errors = super.validate (actionMapping_, request_ );

           ODSValidator.validateDate(
                                              CustomerPrvtCmplHistoryDetailFncVO.C_CUST_REF_DATE_DESCRIPTION,
                                    m_custRefDate,
                                    errors );

   return errors;
 }
}