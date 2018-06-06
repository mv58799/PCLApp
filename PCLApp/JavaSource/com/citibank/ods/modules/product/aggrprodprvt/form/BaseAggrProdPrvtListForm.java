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
import com.citibank.ods.modules.product.aggrprodprvt.functionality.valueobject.BaseAggrProdPrvtListFncVO;

/**
 * @author leonardo.nakada
 * 
 *  
 */
public class BaseAggrProdPrvtListForm extends BaseForm implements
    AggrProdPrvtDetailable
{
  /**
   * Codigo do Agrupador de Produtos Private
   */
  private String m_prvtProdAggrCodeSrc;

  /**
   * Descricao do agrupador de Produtos Private
   */
  private String m_prvtProdAggrTextSrc;

  /**
   * SELECIONADO - Codigo do Agrupador de Produtos Private
   */
  private String m_selectedPrvtProdAggrCode;

  /**
   * Resultado
   */
  private DataSet m_results;

  /**
   * Retorna o Ítem selecionado do Grid
   * @see com.citibank.ods.modules.product.productaggr.form.ProductAggrDetailable#getSelectedPrvtProdAggrCode()
   */
  public String getSelectedPrvtProdAggrCode()
  {
    return null;
  }

  /**
   * Atribui o valor do Cógidigo do Produto Agregador
   * @see com.citibank.ods.modules.product.productaggr.form.ProductAggrDetailable#setSelectedPrvtProdAggrCode(java.lang.String)
   */
  public void setSelectedPrvtProdAggrCode( String selectedPrvtProdAggrCode_ )
  {
   
      m_prvtProdAggrCodeSrc = selectedPrvtProdAggrCode_;
   
  }

  /**
   * Retorna o valor do Cógidigo do Produto Agregador
   * @return Returns the prvtProdAggrCodeSrc.
   */
  public String getPrvtProdAggrCodeSrc()
  {
    return m_prvtProdAggrCodeSrc;
  }

  /**
   * Atribui o valor do Cógidigo do Produto Agregador
   * @param prvtProdAggrCodeSrc_ The prvtProdAggrCodeSrc to set.
   */
  public void setPrvtProdAggrCodeSrc( String prvtProdAggrCodeSrc_ )
  {
    m_prvtProdAggrCodeSrc = prvtProdAggrCodeSrc_;
  }

  /**
   * Retorna o Grid para lista a Consulta
   * @return Returns the results.
   */
  public DataSet getResults()
  {
    return m_results;
  }

  /**
   * Atribui o Grid para lista a Consulta
   * @param results_ The results to set.
   */
  public void setResults( DataSet results_ )
  {
    m_results = results_;
  }

  /**
   * @return Returns the prvtProdAggrTextSrc.
   */
  public String getPrvtProdAggrTextSrc()
  {
    return m_prvtProdAggrTextSrc;
  }

  /**
   * @param prvtProdAggrTextSrc_ The prvtProdAggrTextSrc to set.
   */
  public void setPrvtProdAggrTextSrc( String prvtProdAggrTextSrc_ )
  {
    m_prvtProdAggrTextSrc = prvtProdAggrTextSrc_;
  }

  /**
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateMaxLength(
                                    BaseAggrProdPrvtListFncVO.C_PRVT_PROD_AGGR_TEXT_DESCRIPTION,
                                    m_prvtProdAggrTextSrc,
                                    BaseTplAggrProdPrvtEntity.C_PRVT_PROD_AGGR_TEXT_SIZE,
                                    errors );

    return errors;
  }
}