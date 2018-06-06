/*
 * Created on Mar 12, 2007
 *
 */
package com.citibank.ods.modules.product.aggrprodprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.modules.product.aggrprodprvt.functionality.valueobject.AggrProdPrvtHistoryListFncVO;

/**
 * @author leonardo.nakada
 *  
 */
public class AggrProdPrvtHistoryListForm extends BaseAggrProdPrvtListForm
{
  /**
   * Data de Referencia do registro no historico
   */
  private String m_prvtProdAggrRefDate;
  
  /**
   * Código do agregador do registro no historico
   */
  private String m_prvtProdAggrCodeHistSrc;

  /**
   * Retorna o valor da Data de Refência do Registro no Historico
   * @return Returns the prvtProdAggrRefDate.
   */
  public String getPrvtProdAggrRefDate()
  {
    return m_prvtProdAggrRefDate;
  }

  /**
   * Atribui o valor da Data de Refência do Registro no Historico
   * @param prvtProdAggrRefDate_ The prvtProdAggrRefDate to set.
   */
  public void setPrvtProdAggrRefDate( String prvtProdAggrRefDate_ )
  {
    m_prvtProdAggrRefDate = prvtProdAggrRefDate_;
  }

  public void setSelectedPrvtProdAggrCode( String selectedPrvtProdAggrCode_ )
  {

    m_prvtProdAggrCodeHistSrc = selectedPrvtProdAggrCode_;
  }

  /**
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = super.validate( actionMapping_, request_ );

    ODSValidator.validateDate(
                               AggrProdPrvtHistoryListFncVO.C_PRVT_PROD_AGGR_REF_DATE_DESCRIPTION,
                               m_prvtProdAggrRefDate, errors );

    return errors;
  }

  public String getPrvtProdAggrCodeHistSrc()
  {
    return m_prvtProdAggrCodeHistSrc;
  }

  public void setPrvtProdAggrCodeHistSrc( String prvtProdAggrCodeHistSrc_ )
  {
    m_prvtProdAggrCodeHistSrc = prvtProdAggrCodeHistSrc_;
  }
}