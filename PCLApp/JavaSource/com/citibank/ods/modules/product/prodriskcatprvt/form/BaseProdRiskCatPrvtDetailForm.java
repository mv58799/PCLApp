package com.citibank.ods.modules.product.prodriskcatprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplProdRiskCatPrvtEntity;
import com.citibank.ods.modules.product.prodriskcatprvt.functionality.valueobject.BaseProdRiskCatPrvtDetailFncVO;

/**
 * @author angelica.almeida
 *  
 */

public class BaseProdRiskCatPrvtDetailForm extends BaseForm implements
    ProdRiskCatPrvtDetailable
{

  // Data e hora da ultima atualizaca efetuada pelo usuario.
  private String m_lastUpdDate = "";

  // Codigo do usuario que efetuou a ultima atualizacao no registro.
  private String m_lastUpdUserId = "";

  // Codigo da Categoria de Risco Private
  private String m_prodRiskCatCode = "";

  // Descricao da Categoria de Risco Private
  private String m_prodRiskCatText = "";
  
  /**
   * @return Returns m_lastUpdDate.
   */
  public String getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_ Field m_lastUpdDate to be setted.
   */
  public void setLastUpdDate( String lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Returns m_lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_ Field m_lastUpdUserId to be setted.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * @return Returns m_prodRiskCatCode.
   */
  public String getProdRiskCatCode()
  {
    return m_prodRiskCatCode;
  }

  /**
   * @param prodRiskCatCode_ Field m_prodRiskCatCode to be setted.
   */
  public void setProdRiskCatCode( String prodRiskCatCode_ )
  {
    m_prodRiskCatCode = prodRiskCatCode_;
  }

  /**
   * @return Returns m_prodRiskCatText.
   */
  public String getProdRiskCatText()
  {
    return m_prodRiskCatText;
  }

  /**
   * @param prodRiskCatText_ Field m_prodRiskCatText to be setted.
   */
  public void setProdRiskCatText( String prodRiskCatText_ )
  {
    m_prodRiskCatText = prodRiskCatText_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.prodriskcatprvt.form.ProdRiskCatPrvtDetailable#getSelectedProdRiskCatCode()
   */
  public String getSelectedProdRiskCatCode()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.prodriskcatprvt.form.ProdRiskCatPrvtDetailable#setSelectedProdRiskCatCode(java.math.BigInteger)
   */
  public void setSelectedProdRiskCatCode( String setSelectedProdRiskCatCode_ )
  {
    m_prodRiskCatCode = setSelectedProdRiskCatCode_;
  }
  
  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateBigInteger(
                                     BaseProdRiskCatPrvtDetailFncVO.C_PROD_RISK_CAT_CODE_DESCRIPTION,
                                     m_prodRiskCatCode,
                                     BaseTplProdRiskCatPrvtEntity.C_PROD_INVST_RISK_CODE_SIZE,
                                     errors );
    
    ODSValidator.validateMaxLength(BaseProdRiskCatPrvtDetailFncVO.C_PROD_RISK_CAT_TEXT_DESCRIPTION,
                                   m_prodRiskCatText,
                                   BaseTplProdRiskCatPrvtEntity.C_PROD_RISK_CAT_TEXT_SIZE,
                                   errors);
    
    return errors;
  }
}