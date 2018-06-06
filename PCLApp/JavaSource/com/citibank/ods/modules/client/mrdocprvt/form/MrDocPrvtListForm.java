package com.citibank.ods.modules.client.mrdocprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity;
import com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject.MrDocPrvtListFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * Form da classe MrDocPrvtListView.jsp
 * 
 * @author michele.monteiro,20/04/2007
 *  
 */

public class MrDocPrvtListForm extends BaseMrDocPrvtListForm
{

  // Código do documento MR
  private String m_mrDocPrvtSrc = "";

  /**
   * @return Retorna o código do documento MR.
   */
  public String getMrDocPrvtSrc()
  {
    return m_mrDocPrvtSrc;
  }

  /**
   * @param mrDocPrvtSrc_.Seta o código do documento MR.
   */
  public void setMrDocPrvtSrc( String mrDocPrvtSrc_ )
  {
    m_mrDocPrvtSrc = mrDocPrvtSrc_;
  }
  
  /**
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = super.validate( actionMapping_, request_ );
    

    ODSValidator.validateMaxLength( MrDocPrvtListFncVO.C_MR_DOC_PRVT,
                                    m_mrDocPrvtSrc,
                                    BaseTplMrDocPrvtEntity.C_MR_DOC_PRVT_SIZE,
                                    errors );

    return errors;
  }


}