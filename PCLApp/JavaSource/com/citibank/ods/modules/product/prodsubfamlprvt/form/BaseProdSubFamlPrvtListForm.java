package com.citibank.ods.modules.product.prodsubfamlprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplProdSubFamlPrvtEntity;
import com.citibank.ods.modules.product.prodsubfamlprvt.functionality.valueobject.BaseProdSubFamlPrvtListFncVO;

/**
 * @author leonardo.nakada
 *  
 */

public class BaseProdSubFamlPrvtListForm extends BaseForm implements
    ProdSubFamlPrvtDetailable
{
  //Último usuário da atualização do Código de Produtos.
  private String m_lastUpdDate = "";

  //Data de última atualização da Sub-Familia de Produtos.
  private String m_lastUpdUserId = "";

  // Codigo da Sub-Familia de Produtos.
  private String m_prodSubFamlCodeSrc = "";

  // Nome da Sub-Familia de Produtos.
  private String m_prodSubFamlNameSrc = "";

  // Descricao da Sub-Familia de Produtos.
  private String m_prodSubFamlTextSrc = "";

  // selected Codigo
  private String m_selectedProdSubFamlCode = "";

  // resultado da consulta
  private DataSet m_results = null;

  /**
   * @return Returns the prodSubFamlCodeSrc.
   */
  public String getProdSubFamlCodeSrc()
  {
    return m_prodSubFamlCodeSrc;
  }

  /**
   * @param prodSubFamlCodeSrc_ The prodSubFamlCodeSrc to set.
   */
  public void setProdSubFamlCodeSrc( String prodSubFamlCodeSrc_ )
  {
    m_prodSubFamlCodeSrc = prodSubFamlCodeSrc_;
  }

  /**
   * @return Returns the prodSubFamlNameSrc.
   */
  public String getProdSubFamlNameSrc()
  {
    return m_prodSubFamlNameSrc;
  }

  /**
   * @param prodSubFamlNameSrc_ The prodSubFamlNameSrc to set.
   */
  public void setProdSubFamlNameSrc( String prodSubFamlNameSrc_ )
  {
    m_prodSubFamlNameSrc = prodSubFamlNameSrc_;
  }

  /**
   * @return Returns the prodSubFamlTextSrc.
   */
  public String getProdSubFamlTextSrc()
  {
    return m_prodSubFamlTextSrc;
  }

  /**
   * @param prodSubFamlTextSrc_ The prodSubFamlTextSrc to set.
   */
  public void setProdSubFamlTextSrc( String prodSubFamlTextSrc_ )
  {
    m_prodSubFamlTextSrc = prodSubFamlTextSrc_;
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
   * @return Returns the selectedProdSubFamlCode.
   */
  public String getSelectedProdSubFamlCode()
  {
    return m_selectedProdSubFamlCode;
  }

  /**
   * @param selectedProdSubFamlCode_ The selectedProdSubFamlCode to set.
   */
  public void setSelectedProdSubFamlCode( String selectedProdSubFamlCode_ )
  {
    m_selectedProdSubFamlCode = selectedProdSubFamlCode_;
  }

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

  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateBigInteger(
                                     BaseProdSubFamlPrvtListFncVO.C_PROD_SUB_FAML_CODE_DESCRIPTION,
                                     m_prodSubFamlCodeSrc,
                                     BaseTplProdSubFamlPrvtEntity.C_PROD_SUB_FAML_CODE_SIZE,
                                     errors );

    ODSValidator.validateMaxLength(
                                    BaseProdSubFamlPrvtListFncVO.C_PROD_SUB_FAML_NAME_DESCRIPTION,
                                    m_prodSubFamlNameSrc,
                                    BaseTplProdSubFamlPrvtEntity.C_PROD_SUB_FAML_NAME_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseProdSubFamlPrvtListFncVO.C_PROD_SUB_FAML_TEXT_DESCRIPTION,
                                    m_prodSubFamlTextSrc,
                                    BaseTplProdSubFamlPrvtEntity.C_PROD_SUB_FAML_TEXT_SIZE,
                                    errors );
    
    return errors;
  }

}