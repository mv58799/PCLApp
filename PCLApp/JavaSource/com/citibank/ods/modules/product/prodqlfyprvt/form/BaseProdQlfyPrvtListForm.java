/*
 * Created on Mar 16, 2007
 *
 */
package com.citibank.ods.modules.product.prodqlfyprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplProdQlfyPrvtEntity;
import com.citibank.ods.modules.product.prodqlfyprvt.functionality.valueobject.BaseProdQlfyPrvtListFncVO;
/**
 * @author fernando.salgado
 *  
 */
public class BaseProdQlfyPrvtListForm extends BaseForm implements
    ProdQlfyPrvtDetailable
{

  //Seleção da lista
  private String m_selectedProdQlfyCode;

  //Código da Qualificação do Produto
  private String m_prodQlfyCodeSrc;

  //Descrição da Qualificação do Produto
  private String m_prodQlfyTextSrc;

  //Resultado da Consulta
  private DataSet m_results;

  /**
   * @return Returns selectedProdQlfyCode.
   */
  public String getSelectedProdQlfyCode()
  {
    return m_selectedProdQlfyCode;
  }

  /**
   * @param selectedProdQlfyCode_ Field selectedProdQlfyCode to be setted.
   */
  public void setSelectedProdQlfyCode( String selectedProdQlfyCode_ )
  {
    m_selectedProdQlfyCode = selectedProdQlfyCode_;
  }

  /**
   * @return Returns the results.
   */
  public DataSet getResults()
  {
    return m_results;
  }

  /**
   * @param results_ The results to set.
   */
  public void setResults( DataSet results_ )
  {
    m_results = results_;
  }

  /**
   * @return Returns prodQlfyCodeSrc.
   */
  public String getProdQlfyCodeSrc()
  {
    return m_prodQlfyCodeSrc;
  }

  /**
   * @param prodQlfyCodeSrc_ Field prodQlfyCodeSrc to be setted.
   */
  public void setProdQlfyCodeSrc( String prodQlfyCodeSrc_ )
  {
    m_prodQlfyCodeSrc = prodQlfyCodeSrc_;
  }

  /**
   * @return Returns prodQlfyTextSrc.
   */
  public String getProdQlfyTextSrc()
  {
    return m_prodQlfyTextSrc;
  }

  /**
   * @param prodQlfyTextSrc_ Field prodQlfyTextSrc to be setted.
   */
  public void setProdQlfyTextSrc( String prodQlfyTextSrc_ )
  {
    m_prodQlfyTextSrc = prodQlfyTextSrc_;
  }
  
  /**
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                              HttpServletRequest request_ )
  {
       ActionErrors errors = new ActionErrors();

            ODSValidator.validateBigInteger(
                                    BaseProdQlfyPrvtListFncVO.C_PROD_QLFY_CODE_DESCRIPTION,
                                    m_prodQlfyCodeSrc,
                                            BaseTplProdQlfyPrvtEntity.C_PROD_QLFY_CODE_SIZE,
                                    errors );

           ODSValidator.validateMaxLength(
                                              BaseProdQlfyPrvtListFncVO.C_PROD_QLFY_TEXT_DESCRIPTION,
                                    m_prodQlfyTextSrc,
                                            BaseTplProdQlfyPrvtEntity.C_PROD_QLFY_TEXT_SIZE,
                                    errors );

   return errors;
 }
}