/*
 * Created on 19/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodassettype.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplProdAssetTypeEntity;
import com.citibank.ods.modules.product.prodassettype.functionality.valueobject.BaseProdAssetTypeListFncVO;

/**
 * @author rcoelho
 * @since 19/08/2008
 */
public class BaseProdAssetTypeListForm extends BaseForm implements ProdAssetTypeDetailable{
	
	//Seleção da lista.
	private String m_selectedProdAssetTypeCode;

	//Código da Qualificação do Produto.
	private String m_prodAssetTypeCodeSrc;

	//Descrição da Qualificação do Produto.
	private String m_prodAssetTypeTextSrc;

	//Resultado da Consulta.
	private DataSet m_results;

	/**
	 * @return Retorna a Seleção da lista.
	 */
	public String getSelectedProdAssetTypeCode()
	{
	  return m_selectedProdAssetTypeCode;
	}

	/**
	 * @param String selectedProdAssetTypeCode_.
	 * Seta a Seleção da lista.
	 */
	public void setSelectedProdAssetTypeCode( String selectedProdAssetTypeCode_ )
	{
	  m_selectedProdAssetTypeCode = selectedProdAssetTypeCode_;
	}

	/**
	 * @return Retorna o Resultado da Consulta.
	 */
	public DataSet getResults()
	{
	  return m_results;
	}

	/**
	 * @param DataSet results_.
	 * Seta o Resultado da Consulta.
	 */
	public void setResults( DataSet results_ )
	{
	  m_results = results_;
	}

	/**
	 * @return Retorna o Código da Qualificação do Produto.
	 */
	public String getProdAssetTypeCodeSrc()
	{
	  return m_prodAssetTypeCodeSrc;
	}

	/**
	 * @param String prodAssetTypeCodeSrc_.
	 * Seta o Código da Qualificação do Produto.
	 */
	public void setProdAssetTypeCodeSrc( String prodAssetTypeCodeSrc_ )
	{
	  m_prodAssetTypeCodeSrc = prodAssetTypeCodeSrc_;
	}

	/**
	 * @return Retorna a Descrição da Qualificação do Produto.
	 */
	public String getProdAssetTypeTextSrc()
	{
	  return m_prodAssetTypeTextSrc;
	}

	/**
	 * @param String prodAssetTypeTextSrc_.
	 * Seta a Descrição da Qualificação do Produto.
	 */
	public void setProdAssetTypeTextSrc( String prodAssetTypeTextSrc_ )
	{
	  m_prodAssetTypeTextSrc = prodAssetTypeTextSrc_;
	}
  
	/**
	 * Realiza as validações de tipos e tamanhos
	 */
	public ActionErrors validate( ActionMapping actionMapping_,
								HttpServletRequest request_ )
	{
		 ActionErrors errors = new ActionErrors();

			  ODSValidator.validateBigInteger(
									  BaseProdAssetTypeListFncVO.C_PROD_ASSETTYPE_CODE_DESCRIPTION,
									  m_prodAssetTypeCodeSrc,
											  BaseTplProdAssetTypeEntity.C_PROD_ASSETTYPE_CODE_SIZE,
									  errors );

			 ODSValidator.validateMaxLength(
												BaseProdAssetTypeListFncVO.C_PROD_ASSETTYPE_TEXT_DESCRIPTION,
									  m_prodAssetTypeTextSrc,
											  BaseTplProdAssetTypeEntity.C_PROD_ASSETTYPE_TEXT_SIZE,
									  errors );
			

	 return errors;
   }
}
