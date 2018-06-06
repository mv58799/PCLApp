package com.citibank.ods.modules.product.prodriskcatprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplProdRiskCatPrvtEntity;
import com.citibank.ods.modules.product.prodriskcatprvt.functionality.valueobject.BaseProdRiskCatPrvtListFncVO;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseProdRiskCatPrvtListForm extends BaseForm implements
    ProdRiskCatPrvtDetailable
{
  // Codigo da Categoria de Risco Private
  private String m_prodRiskCatCodeSrc = "";

  // Descricao da Categoria de Risco Private
  private String m_prodRiskCatTextSrc = "";

  // Data e hora da ultima atualizaca efetuada pelo usuario.
  private String m_lastUpdDate = "";

  // Codigo do usuario que efetuou a ultima atualizacao no registro.
  private String m_lastUpdUserId = "";

  //Resultado da consulta
  private DataSet m_results;

  /**
   * @return Returns the prodRiskCatCodeSrc.
   */
  public String getProdRiskCatCodeSrc()
  {
    return m_prodRiskCatCodeSrc;
  }

  /**
   * @param prodRiskCatCodeSrc_ The prodRiskCatCodeSrc to set.
   */
  public void setProdRiskCatCodeSrc( String prodRiskCatCodeSrc_ )
  {
    m_prodRiskCatCodeSrc = prodRiskCatCodeSrc_;
  }

  /**
   * @return Returns the prodRiskCatTextSrc.
   */
  public String getProdRiskCatTextSrc()
  {
    return m_prodRiskCatTextSrc;
  }

  /**
   * @param prodRiskCatTextSrc_ The prodRiskCatTextSrc to set.
   */
  public void setProdRiskCatTextSrc( String prodRiskCatTextSrc_ )
  {
    m_prodRiskCatTextSrc = prodRiskCatTextSrc_;
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
   * @return Returns the m_selectedProdRiskCatCode.
   */
  public String getSelectedProdRiskCatCode()
  {
    return m_prodRiskCatCodeSrc;
  }

  /**
   * @param prodRiskCatCode The m_selectedProdRiskCatCode to set.
   */
  public void setSelectedProdRiskCatCode( String prodRiskCatCode_ )
  {
    m_prodRiskCatCodeSrc = prodRiskCatCode_;
  }

  /**
   * @return Returns the lastUpdDate.
   */
  public String getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_ The lastUpdDate to set.
   */
  public void setLastUpdDate( String lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Returns the lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_ The lastUpdUserId to set.
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
                                     BaseProdRiskCatPrvtListFncVO.C_PROD_RISK_CAT_CODE_DESCRIPTION,
                                     m_prodRiskCatCodeSrc,
                                     BaseTplProdRiskCatPrvtEntity.C_PROD_INVST_RISK_CODE_SIZE,
                                     errors );

    ODSValidator.validateMaxLength(
                                    BaseProdRiskCatPrvtListFncVO.C_PROD_RISK_CAT_TEXT_DESCRIPTION,
                                    m_prodRiskCatTextSrc,
                                    BaseTplProdRiskCatPrvtEntity.C_PROD_RISK_CAT_TEXT_SIZE,
                                    errors );

    return errors;
  }
}