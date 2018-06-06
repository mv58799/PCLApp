package com.citibank.ods.modules.product.productfamilyprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplProductFamilyPrvtEntity;
import com.citibank.ods.modules.product.productfamilyprvt.functionality.valueobject.BaseProductFamilyPrvtDetailFncVO;

/**
*@author angelica.almeida
*
*/

public class BaseProductFamilyPrvtDetailForm extends BaseForm implements ProductFamilyPrvtDetailable
{

  // Data e hora da ultima atualizaca efetuada pelo usuario.
  private String m_lastUpdDate = "";

  // Codigo do usuario que efetuou a ultima atualizacao no registro.
  private String m_lastUpdUserId = "";

  // Codigo da Familia de Produtos
  private String m_prodFamlCode = "";

  // Nome da Familia de Produtos
  private String m_prodFamlName = "";

  // Descricao da Familia de Produtos.
  private String m_prodFamlText = "";

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
  * @return Returns m_prodFamlCode.
  */
  public String getProdFamlCode()
  {
    return m_prodFamlCode;
  }

  /**
  * @param prodFamlCode_ Field m_prodFamlCode to be setted.
  */
  public void setProdFamlCode( String prodFamlCode_ )
  {
    m_prodFamlCode = prodFamlCode_;
  }

  /**
  * @return Returns m_prodFamlName.
  */
  public String getProdFamlName()
  {
    return m_prodFamlName;
  }

  /**
  * @param prodFamlName_ Field m_prodFamlName to be setted.
  */
  public void setProdFamlName( String prodFamlName_ )
  {
    m_prodFamlName = prodFamlName_;
  }

  /**
  * @return Returns m_prodFamlText.
  */
  public String getProdFamlText()
  {
    return m_prodFamlText;
  }

  /**
  * @param prodFamlText_ Field m_prodFamlText to be setted.
  */
  public void setProdFamlText( String prodFamlText_ )
  {
    m_prodFamlText = prodFamlText_;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.product.productfamilyprvt.form.ProductFamilyPrvtDetailable#getSelectedProdFamlCode()
   */
  public String getSelectedProdFamlCode()
  {
    return null;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.product.productfamilyprvt.form.ProductFamilyPrvtDetailable#setSelectedProdFamlCode(java.lang.String)
   */
  public void setSelectedProdFamlCode( String selectedProdFamlCode_ )
  {
    m_prodFamlCode = selectedProdFamlCode_;
  }
  
  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                              HttpServletRequest request_ )
  {
       ActionErrors errors = new ActionErrors();

            ODSValidator.validateBigInteger(
                                    BaseProductFamilyPrvtDetailFncVO.C_PROD_FAML_CODE_DESCRIPTION,
                                    m_prodFamlCode,
                                            BaseTplProductFamilyPrvtEntity.C_PROD_FAML_CODE_SIZE,
                                    errors );

           ODSValidator.validateMaxLength(
                                              BaseProductFamilyPrvtDetailFncVO.C_PROD_FAML_NAME_DESCRIPTION,
                                    m_prodFamlName,
                                            BaseTplProductFamilyPrvtEntity.C_PROD_FAML_NAME_SIZE,
                                    errors );

           ODSValidator.validateMaxLength(
                                              BaseProductFamilyPrvtDetailFncVO.C_PROD_FAML_TEXT_DESCRIPTION,
                                    m_prodFamlText,
                                            BaseTplProductFamilyPrvtEntity.C_PROD_FAML_TEXT_SIZE,
                                    errors );


   return errors;
 }


}
