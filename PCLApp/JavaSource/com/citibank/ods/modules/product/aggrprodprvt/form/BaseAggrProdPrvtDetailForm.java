/*
 * Created on Mar 12, 2007
 *
 */
package com.citibank.ods.modules.product.aggrprodprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplAggrProdPrvtEntity;
import com.citibank.ods.modules.product.aggrprodprvt.functionality.valueobject.BaseAggrProdPrvtDetailFncVO;

/**
 * @author leonardo.nakada
 *  
 */
public class BaseAggrProdPrvtDetailForm extends BaseForm implements
    AggrProdPrvtDetailable
{
	
  /*
   * Codigo do Agrupador de Produtos Private
   */
  private String m_prvtProdAggrCode;

  /*
   * Descricao do agrupador de Produtos Private
   */
  private String m_prvtProdAggrText;

  /*
   * SELECIONADO - Codigo do Agrupador de Produtos Private
   */
  private String m_selectedPrvtProdAggrCode;

  

  private String m_ixCode;
  
  private DataSet m_AggrProdPrvtIxCodeDomain = null;

	
	public DataSet getAggrProdPrvtIxCodeDomain() {
	return m_AggrProdPrvtIxCodeDomain;
	}
	
	public void setAggrProdPrvtIxCodeDomain(DataSet m_AggrProdPrvtIxCodeDomain) {
		this.m_AggrProdPrvtIxCodeDomain = m_AggrProdPrvtIxCodeDomain;
	}

	public String getIxCode() {
		return m_ixCode;
	}
	
	public void setIxCode(String m_ixCode) {
		this.m_ixCode = m_ixCode;
	}
  
  /**
   * 
   * @see com.citibank.ods.modules.product.productaggr.form.ProductAggrDetailable#getSelectedPrvtProdAggrCode()
   */
  public String getSelectedPrvtProdAggrCode()
  {
    return m_prvtProdAggrCode;
  }

  /**
   * 
   * @see com.citibank.ods.modules.product.productaggr.form.ProductAggrDetailable#setSelectedPrvtProdAggrCode(java.lang.String)
   */
  public void setSelectedPrvtProdAggrCode( String selectedPrvtProdAggrCode_ )
  {
    m_prvtProdAggrCode = selectedPrvtProdAggrCode_;
  }

  /**
   * Restorna o código do Produto Agregador
   * @return Returns the prvtProdAggrCode.
   */
  public String getPrvtProdAggrCode()
  {
    return m_prvtProdAggrCode;
  }

  /**
   * Atribui o código do Produto Agregador
   * @param prvtProdAggrCode_ The prvtProdAggrCode to set.
   */
  public void setPrvtProdAggrCode( String prvtProdAggrCode_ )
  {
    m_prvtProdAggrCode = prvtProdAggrCode_;
  }

  /**
   * Reorna a descrição do Produto Agregador
   * @return Returns the prvtProdAggrText.
   */
  public String getPrvtProdAggrText()
  {
    return m_prvtProdAggrText;
  }

  /**
   * Atribui o valor da Descrição do Produto Agregador
   * @param prvtProdAggrText_ The prvtProdAggrText to set.
   */
  public void setPrvtProdAggrText( String prvtProdAggrText_ )
  {
    m_prvtProdAggrText = prvtProdAggrText_;
  }
  
  

  /**
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateMaxLength(
                                    BaseAggrProdPrvtDetailFncVO.C_PRVT_PROD_AGGR_TEXT_DESCRIPTION,
                                    m_prvtProdAggrText,
                                    BaseTplAggrProdPrvtEntity.C_PRVT_PROD_AGGR_TEXT_SIZE,
                                    errors );

    return errors;
  }
}