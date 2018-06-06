package com.citibank.ods.modules.product.product.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplProductEntity;
import com.citibank.ods.modules.product.product.functionality.valueobject.BaseProductDetailFncVO;

/**
 * @author leonardo.nakada
 *  
 */

public class BaseProductListForm extends BaseForm implements ProductSearchable,
    ProductDetailable
{
  // Codigo do Produto.
  private String m_prodCodeSrc = "";

  //Descricao do Produto
  private String m_prodTextSrc = "";

  // Codigo da Familia de Produtos (redundante seguindo atributo existente no
  // BG)
  private String m_prodFamlCodeSrc = "";

  // Nome do Produto
  private String m_prodNameSrc = "";

  // Codigo de Qualificacao do Prod uto Private
  private String m_prodQlfyCodeSrc = "";

  // Codigo da Categoria de Risco Private.
  private String m_prodRiskCatCodeSrc = "";

  // Codigo da Sub-Familia de Produtos.
  private String m_prodSubFamlCodeSrc = "";

  //Codigo do Sistema
  private String m_sysCodeSrc = "";

  //Codigo do Segmento
  private String m_sysSegCodeSrc = "";

  private String m_selectedProdCode = "";

  private String m_selectedSysCode = "";

  private String m_selectedSysSegCode = "";

  private DataSet m_results = null;

  private DataSet m_prodQlfyCodeDomain;

  private DataSet m_prodFamlCodeDomain;

  private DataSet m_prodRiskCodeDomain;

  private DataSet m_prodSubFamlCodeDomain;

  private String m_lastUpdUserIdSrc = "";
  
  private ArrayList m_listProduct;

  /**
   * @return Returns lastUpdUserId.
   */
  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }

  /**
   * @param lastUpdUserId_ Field lastUpdUserId to be setted.
   */
  public void setLastUpdUserIdSrc( String lastUpdUserIdSrc_ )
  {
    m_lastUpdUserIdSrc = lastUpdUserIdSrc_;
  }

  /**
   * @return Returns prodSubFamlCodeDomain.
   */
  public DataSet getProdSubFamlCodeDomain()
  {
    return m_prodSubFamlCodeDomain;
  }

  /**
   * @param prodSubFamlCodeDomain_ Field prodSubFamlCodeDomain to be setted.
   */
  public void setProdSubFamlCodeDomain( DataSet prodSubFamlCodeDomain_ )
  {
    m_prodSubFamlCodeDomain = prodSubFamlCodeDomain_;
  }

  /**
   * @return Returns prodFamlCodeDomain.
   */
  public DataSet getProdFamlCodeDomain()
  {
    return m_prodFamlCodeDomain;
  }

  /**
   * @param prodFamlCodeDomain_ Field prodFamlCodeDomain to be setted.
   */
  public void setProdFamlCodeDomain( DataSet prodFamlCodeDomain_ )
  {
    m_prodFamlCodeDomain = prodFamlCodeDomain_;
  }

  /**
   * @return Returns prodQlfCodeDomain.
   */
  public DataSet getProdQlfyCodeDomain()
  {
    return m_prodQlfyCodeDomain;
  }

  /**
   * @param prodQlfCodeDomain_ Field prodQlfCodeDomain to be setted.
   */
  public void setProdQlfyCodeDomain( DataSet prodQlfyCodeDomain_ )
  {
    m_prodQlfyCodeDomain = prodQlfyCodeDomain_;
  }

  /**
   * @return Returns prodRiskCodeDomain.
   */
  public DataSet getProdRiskCodeDomain()
  {
    return m_prodRiskCodeDomain;
  }

  /**
   * @param prodRiskCodeDomain_ Field prodRiskCodeDomain to be setted.
   */
  public void setProdRiskCodeDomain( DataSet prodRiskCodeDomain_ )
  {
    m_prodRiskCodeDomain = prodRiskCodeDomain_;
  }

  /**
   * @return Returns the prodCodeSrc.
   */
  public String getProdCodeSrc()
  {
    return m_prodCodeSrc;
  }

  /**
   * @param prodCodeSrc_ The prodCodeSrc to set.
   */
  public void setProdCodeSrc( String prodCodeSrc_ )
  {
    m_prodCodeSrc = prodCodeSrc_;
  }

  /**
   * @return Returns the prodFamlCodeSrc.
   */
  public String getProdFamlCodeSrc()
  {
    return m_prodFamlCodeSrc;
  }

  /**
   * @param prodFamlCodeSrc_ The prodFamlCodeSrc to set.
   */
  public void setProdFamlCodeSrc( String prodFamlCodeSrc_ )
  {
    m_prodFamlCodeSrc = prodFamlCodeSrc_;
  }

  /**
   * @return Returns the prodNameSrc.
   */
  public String getProdNameSrc()
  {
    return m_prodNameSrc;
  }

  /**
   * @param prodNameSrc_ The prodNameSrc to set.
   */
  public void setProdNameSrc( String prodNameSrc_ )
  {
    m_prodNameSrc = prodNameSrc_;
  }

  /**
   * @return Returns the prodQlfyCodeSrc.
   */
  public String getProdQlfyCodeSrc()
  {
    return m_prodQlfyCodeSrc;
  }

  /**
   * @param prodQlfyCodeSrc_ The prodQlfyCodeSrc to set.
   */
  public void setProdQlfyCodeSrc( String prodQlfyCodeSrc_ )
  {
    m_prodQlfyCodeSrc = prodQlfyCodeSrc_;
  }

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
   * @return Returns the prodTextSrc.
   */
  public String getProdTextSrc()
  {
    return m_prodTextSrc;
  }

  /**
   * @param prodTextSrc_ The prodTextSrc to set.
   */
  public void setProdTextSrc( String prodTextSrc_ )
  {
    m_prodTextSrc = prodTextSrc_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductSearchable#setSelectedProdCode(java.lang.String)
   */
  public void setSelectedProdCode( String selectedProdCode_ )
  {
    setProdCodeSrc( selectedProdCode_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductSearchable#getSelectedProdCode()
   */
  public String getSelectedProdCode()
  {
    return m_selectedProdCode;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductSearchable#setSelectedSysCode(java.lang.String)
   */
  public void setSelectedSysCode( String selectedSysCode_ )
  {
    setSysCodeSrc( selectedSysCode_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductSearchable#getSelectedSysCode()
   */
  public String getSelectedSysCode()
  {
    return m_selectedSysCode;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductSearchable#setSelectedSysSegCode(java.lang.String)
   */
  public void setSelectedSysSegCode( String selectedSysSegCode_ )
  {
    setSysSegCodeSrc( selectedSysSegCode_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductSearchable#getSelectedSysSegCode()
   */
  public String getSelectedSysSegCode()
  {
    return m_selectedSysSegCode;
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

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductSearchable#setSysCodeSrc(java.lang.String)
   */
  public void setSysCodeSrc( String selectedSysCode_ )
  {
    m_sysCodeSrc = selectedSysCode_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductSearchable#getSysCodeSrc()
   */
  public String getSysCodeSrc()
  {
    return m_sysCodeSrc;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductSearchable#setSysSegCodeSrc(java.lang.String)
   */
  public void setSysSegCodeSrc( String selectedSysSegCode_ )
  {
    m_sysSegCodeSrc = selectedSysSegCode_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductSearchable#getSysSegCodeSrc()
   */
  public String getSysSegCodeSrc()
  {
    return m_sysSegCodeSrc;
  }
  public ActionErrors validate( ActionMapping actionMapping_,
                                HttpServletRequest request_ )
   {
     ActionErrors errors = new ActionErrors();

     ODSValidator.validateMaxLength(
                                     BaseProductDetailFncVO.C_SYS_CODE_DESCRIPTION,
                                     m_sysCodeSrc,
                                     BaseTplProductEntity.C_SYS_CODE_SIZE,
                                     errors );     
     ODSValidator.validateMaxLength(
                                     BaseProductDetailFncVO.C_PROD_FAML_CODE_DESCRIPTION,
                                     m_prodFamlCodeSrc,
                                     BaseTplProductEntity.C_PROD_FAML_NAME_SIZE,
                                     errors );
     ODSValidator.validateMaxLength(
                                     BaseProductDetailFncVO.C_PROD_CODE_DESCRIPTION,
                                     m_prodCodeSrc,
                                     BaseTplProductEntity.C_PROD_CODE_SIZE,
                                     errors );
     ODSValidator.validateMaxLength(
                                     BaseProductDetailFncVO.C_PROD_NAME_DESCRIPTION,
                                     m_prodNameSrc,
                                     BaseTplProductEntity.C_PROD_NAME_SIZE,
                                     errors );  

     return errors;
   }

	/**
 	* @return
 	*/
	public ArrayList getListProduct() {
		return m_listProduct;
	}

	/**
 	* @param list
 	*/
	public void setListProduct(ArrayList m_listProduct_) {
		m_listProduct = m_listProduct_;
	}

}