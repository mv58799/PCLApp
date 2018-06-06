package com.citibank.ods.modules.product.prodsubfamlprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplProdSubFamlPrvtEntity;
import com.citibank.ods.modules.product.prodsubfamlprvt.functionality.valueobject.BaseProdSubFamlPrvtDetailFncVO;

/**
 * @author fernando.salgado
 *  
 */

public class BaseProdSubFamlPrvtDetailForm extends BaseForm implements
    ProdSubFamlPrvtDetailable
{
  public static final String C_PROD_SUB_FAML_CODE_DESCRIPTION = "Código da Sub-Família";
  public static final String C_PROD_SUB_FAML_NAME_DESCRIPTION = "Nome da Sub-Família";
  public static final String C_PROD_SUB_FAML_TEXT_DESCRIPTION = "Desc. da Sub-Família";
  public static final String C_PROD_FAML_CODE_DESCRIPTION = "Codigo da Familia";

  // Data e hora da ultima atualizaca efetuada pelo usuario.
  private String m_lastUpdDate = "";

  // Codigo do usuario que efetuou a ultima atualizacao no registro.
  private String m_lastUpdUserId = "";

  // Codigo da Familia de Produtos
  private String m_prodFamlCode = "";

  // Codigo da Sub-Familia de Produtos.
  private String m_prodSubFamlCode = "";

  // Nome da Sub-Familia de Produtos.
  private String m_prodSubFamlName = "";

  // Descricao da Sub-Familia de Produtos.
  private String m_prodSubFamlText = "";

  // Domain da Familia de Produtos
  private DataSet m_prodFamlCodeDomain = null;

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
   * @return Returns m_prodSubFamlCode.
   */
  public String getProdSubFamlCode()
  {
    return m_prodSubFamlCode;
  }

  /**
   * @param prodSubFamlCode_ Field m_prodSubFamlCode to be setted.
   */
  public void setProdSubFamlCode( String prodSubFamlCode_ )
  {
    m_prodSubFamlCode = prodSubFamlCode_;
  }

  /**
   * @return Returns m_prodSubFamlName.
   */
  public String getProdSubFamlName()
  {
    return m_prodSubFamlName;
  }

  /**
   * @param prodSubFamlName_ Field m_prodSubFamlName to be setted.
   */
  public void setProdSubFamlName( String prodSubFamlName_ )
  {
    m_prodSubFamlName = prodSubFamlName_;
  }

  /**
   * @return Returns m_prodSubFamlText.
   */
  public String getProdSubFamlText()
  {
    return m_prodSubFamlText;
  }

  /**
   * @param prodSubFamlText_ Field m_prodSubFamlText to be setted.
   */
  public void setProdSubFamlText( String prodSubFamlText_ )
  {
    m_prodSubFamlText = prodSubFamlText_;
  }

  /**
   * @return Returns the prodFamlCodeDomain.
   */
  public DataSet getProdFamlCodeDomain()
  {
    return m_prodFamlCodeDomain;
  }

  /**
   * @param prodFamlCodeDomain_ The prodFamlCodeDomain to set.
   */
  public void setProdFamlCodeDomain( DataSet prodFamlCodeDomain_ )
  {
    m_prodFamlCodeDomain = prodFamlCodeDomain_;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.product.prodsubfamlprvt.form.ProdSubFamlPrvtDetailable#getSelectedProdSubFamlCode()
   */
  public String getSelectedProdSubFamlCode()
  {
    return null;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.product.prodsubfamlprvt.form.ProdSubFamlPrvtDetailable#setSelectedProdSubFamlCode(java.lang.String)
   */
  public void setSelectedProdSubFamlCode( String selectedProdFamlCode_ )
  {
    this.m_prodSubFamlCode = selectedProdFamlCode_;
  }
  
  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateBigInteger(
                                     BaseProdSubFamlPrvtDetailFncVO.C_PROD_SUB_FAML_CODE_DESCRIPTION,
                                     m_prodSubFamlCode,
                                     BaseTplProdSubFamlPrvtEntity.C_PROD_SUB_FAML_CODE_SIZE,
                                     errors );

    ODSValidator.validateMaxLength(
                                    BaseProdSubFamlPrvtDetailFncVO.C_PROD_SUB_FAML_NAME_DESCRIPTION,
                                    m_prodSubFamlName,
                                    BaseTplProdSubFamlPrvtEntity.C_PROD_SUB_FAML_NAME_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseProdSubFamlPrvtDetailFncVO.C_PROD_SUB_FAML_TEXT_DESCRIPTION,
                                    m_prodSubFamlText,
                                    BaseTplProdSubFamlPrvtEntity.C_PROD_SUB_FAML_TEXT_SIZE,
                                    errors );
    
    ODSValidator.validateBigInteger(
						            BaseProdSubFamlPrvtDetailFncVO.C_PROD_FAML_CODE_DESCRIPTION,
						            m_prodFamlCode,
						            BaseTplProdSubFamlPrvtEntity.C_PROD_FAML_CODE_SIZE,
						            errors );

    return errors;
  }
}