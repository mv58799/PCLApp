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
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplMovEntity;
import com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject.CustomerPrvtCmplMovementDetailFncVO;
import com.citibank.ods.modules.client.officer.form.OfficerSearchable;


/**
 * @author bruno.zanetti
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class CustomerPrvtCmplMovementDetailForm extends BaseCustomerPrvtCmplDetailForm implements OfficerSearchable
{
  
  private String m_opernCode;

  private String m_lastUpdUserId;

  private String m_lastUpdDate; 
  
  /**
   * @return Returns lastUpdDate.
   */
  public String getLastUpdDate()
  {
    return m_lastUpdDate;
  }
  /**
   * @param lastUpdDate_ Field lastUpdDate to be setted.
   */
  public void setLastUpdDate( String lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }
  /**
   * @return Returns lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }
  /**
   * @param lastUpdUserId_ Field lastUpdUserId to be setted.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }
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
	
  public String getSelectedOffcrNbr()
  {    
    return null;
  }
  /* (non-Javadoc)
   * @see com.citibank.ods.modules.client.officer.form.OfficerSearchable#setSelectedOffcrNbr(java.lang.String)
   */
  public void setSelectedOffcrNbr( String selectedOffcrNbr_ )
  {
    this.setOffcrNbrSrc(selectedOffcrNbr_);
  }
  
  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                              HttpServletRequest request_ )
  {
        ActionErrors errors = super.validate (actionMapping_, request_ );

           ODSValidator.validateDate(
                                              CustomerPrvtCmplMovementDetailFncVO.C_LAST_UPD_DATE_DESCRIPTION,
                                    m_lastUpdDate    ,
                                    errors );

           ODSValidator.validateMaxLength(
                                              CustomerPrvtCmplMovementDetailFncVO.C_LAST_UPD_USER_ID_DESCRIPTION,
                                    m_lastUpdUserId ,
                                            TplCustomerPrvtCmplMovEntity.C_LAST_UPD_USER_ID_SIZE,
                                    errors );

   return errors;
 }

}