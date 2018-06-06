/*
 * Created on 19/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodasset.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplProdAssetEntity;
import com.citibank.ods.modules.product.prodasset.functionality.valueobject.BaseProdAssetListFncVO;

/**
 * @author ronaldo.machado
 * @since 19/08/2008
 */
public class BaseProdAssetListForm extends BaseForm implements ProdAssetDetailable{

	//Seleção da lista.
	private String m_selectedProdAssetCode;

	//Código da Qualificação do Produto.
	private String m_prodAssetCodeSrc;

	//Descrição da Qualificação do Produto.
	private String m_prodAssetTextSrc;

	//Resultado da Consulta.
	private DataSet m_results;

	/**
	 * @return Retorna a Seleção da lista.
	 */
	public String getSelectedProdAssetCode()
	{
	  return m_selectedProdAssetCode;
	}

	/**
	 * @param String selectedProdAssetCode_.
	 * Seta a Seleção da lista.
	 */
	public void setSelectedProdAssetCode( String selectedProdAssetCode_ )
	{
	  m_selectedProdAssetCode = selectedProdAssetCode_;
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
	 * @return Retorna a Descrição da Qualificação do Produto.
	 */
	public String getProdAssetCodeSrc()
	{
	  return m_prodAssetCodeSrc;
	}

	/**
	 * @param String prodAssetCodeSrc_.
	 * Seta a Descrição da Qualificação do Produto.
	 */
	public void setProdAssetCodeSrc( String prodAssetCodeSrc_ )
	{
	  m_prodAssetCodeSrc = prodAssetCodeSrc_;
	}

	/**
	 * @return Retorna a Descrição da Qualificação do Produto.
	 */
	public String getProdAssetTextSrc()
	{
	  return m_prodAssetTextSrc;
	}

	/**
	 * @param prodAssetTextSrc_.
	 * Seta a Descrição da Qualificação do Produto.
	 */
	public void setProdAssetTextSrc( String prodAssetTextSrc_ )
	{
	  m_prodAssetTextSrc = prodAssetTextSrc_;
	}
  
	/**
	 * Realiza as validações de tipos e tamanhos
	 */
	public ActionErrors validate( ActionMapping actionMapping_,
								HttpServletRequest request_ )
	{
		 ActionErrors errors = new ActionErrors();

			  ODSValidator.validateBigInteger(
									  BaseProdAssetListFncVO.C_PROD_ASSET_CODE_DESCRIPTION,
									  m_prodAssetCodeSrc,
											  BaseTplProdAssetEntity.C_PROD_SUBASSET_CODE_SIZE,
									  errors );

			 ODSValidator.validateMaxLength(
												BaseProdAssetListFncVO.C_PROD_ASSET_TEXT_DESCRIPTION,
									  m_prodAssetTextSrc,
											  BaseTplProdAssetEntity.C_PROD_SUBASSET_TEXT_SIZE,
									  errors );
			

	 return errors;
   }
}
