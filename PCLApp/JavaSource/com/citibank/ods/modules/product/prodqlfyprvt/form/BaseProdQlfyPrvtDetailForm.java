package com.citibank.ods.modules.product.prodqlfyprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplProdQlfyPrvtEntity;
import com.citibank.ods.modules.product.prodqlfyprvt.functionality.valueobject.BaseProdQlfyPrvtDetailFncVO;

/**
 * @author angelica.almeida
 *  
 */

public class BaseProdQlfyPrvtDetailForm extends BaseForm implements
    ProdQlfyPrvtDetailable
{

  // Data e hora da ultima atualizaca efetuada pelo usuario.
  private String m_lastUpdDate = "";

  // Codigo do usuario que efetuou a ultima atualizacao no registro.
  private String m_lastUpdUserId = "";

  // Codigo de Qualificacao do Produto Private
  private String m_prodQlfyCode = "";

  // Descricao da Qualificacao do Produto Private
  private String m_prodQlfyText = "";

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
   * @return Returns m_prodQlfyCode.
   */
  public String getProdQlfyCode()
  {
    return m_prodQlfyCode;
  }

  /**
   * @param prodQlfyCode_ Field m_prodQlfyCode to be setted.
   */
  public void setProdQlfyCode( String prodQlfyCode_ )
  {
    m_prodQlfyCode = prodQlfyCode_;
  }

  /**
   * @return Returns m_prodQlfyText.
   */
  public String getProdQlfyText()
  {
    return m_prodQlfyText;
  }

  /**
   * @param prodQlfyText_ Field m_prodQlfyText to be setted.
   */
  public void setProdQlfyText( String prodQlfyText_ )
  {
    m_prodQlfyText = prodQlfyText_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.prodqlfyprvt.form.ProdQlfyPrvtDetailable#getSelectedProdQlfyCode()
   */
  public String getSelectedProdQlfyCode()
  {

    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.prodqlfyprvt.form.ProdQlfyPrvtDetailable#setSelectedProdQlfyCode(java.lang.String)
   */
  public void setSelectedProdQlfyCode( String setSelectedProdQlfyCode_ )
  {

    m_prodQlfyCode = setSelectedProdQlfyCode_;

  }

  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateBigInteger(
                                     BaseProdQlfyPrvtDetailFncVO.C_PROD_QLFY_CODE_DESCRIPTION,
                                     m_prodQlfyCode,
                                     BaseTplProdQlfyPrvtEntity.C_PROD_QLFY_CODE_SIZE,
                                     errors );

    ODSValidator.validateMaxLength(
                                    BaseProdQlfyPrvtDetailFncVO.C_PROD_QLFY_TEXT_DESCRIPTION,
                                    m_prodQlfyText,
                                    BaseTplProdQlfyPrvtEntity.C_PROD_QLFY_TEXT_SIZE,
                                    errors );

    return errors;
  }

}