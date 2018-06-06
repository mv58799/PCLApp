/*
 * Created on Mar 14, 2007
 *
 */
package com.citibank.ods.modules.product.prodriskcatprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.modules.product.prodriskcatprvt.functionality.valueobject.ProdRiskCatPrvtHistoryListFncVO;

/**
 * @author leonardo.nakada
 *  
 */
public class ProdRiskCatPrvtHistoryListForm extends BaseProdRiskCatPrvtListForm
{
  /**
   * Data de Referencia do registro no historico.
   * 
   * Comment for <code>m_prodRiskRefDate</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prodRiskCatRefDate;
  
  /**
   * Código da categoria de risco no registro de historico
   */
  private String m_prodRiskCatCodeHistSrc;

  /**
   * @return Returns the prodRiskCatRefDate.
   */
  public String getProdRiskCatRefDate()
  {
    return m_prodRiskCatRefDate;
  }

  /**
   * @param prodRiskCatRefDate_ The prodRiskCatRefDate to set.
   */
  public void setProdRiskCatRefDate( String prodRiskCatRefDate_ )
  {
    m_prodRiskCatRefDate = prodRiskCatRefDate_;
  }

  /**
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = super.validate( actionMapping_, request_ );

    ODSValidator.validateDate(
                               ProdRiskCatPrvtHistoryListFncVO.C_PROD_RISK_CAT_REF_DATE_DESCRIPTION,
                               m_prodRiskCatRefDate, errors );

    return errors;
  }

  public String getProdRiskCatCodeHistSrc()
  {
    return m_prodRiskCatCodeHistSrc;
  }

  public void setProdRiskCatCodeHistSrc( String prodRiskCatCodeHistSrc_ )
  {
    m_prodRiskCatCodeHistSrc = prodRiskCatCodeHistSrc_;
  }
  
  public void setSelectedProdRiskCatCode( String prodRiskCatCode_ )
  {
    m_prodRiskCatCodeHistSrc = prodRiskCatCode_;
  }
}