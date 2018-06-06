/*
 * Created on 19/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodsubasset.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplProdSubAssetEntity;
import com.citibank.ods.modules.product.prodsubasset.functionality.valueobject.BaseProdSubAssetListFncVO;

/**
 * @author rcoelho
 * @since 19/08/2008
 */
public class BaseProdSubAssetListForm extends BaseForm implements ProdSubAssetDetailable{
	
	//Sele��o da lista.
	private String m_selectedProdSubAssetCode;

	//C�digo da Qualifica��o do Produto.
	private String m_prodSubAssetCodeSrc;

	//Descri��o da Qualifica��o do Produto.
	private String m_prodSubAssetTextSrc;

	//Resultado da Consulta.
	private DataSet m_results;

	/**
	 * @return Retorna a Sele��o da lista.
	 */
	public String getSelectedProdSubAssetCode()
	{
	  return m_selectedProdSubAssetCode;
	}

	/**
	 * @param String selectedProdSubAssetCode_.
	 * Seta a Sele��o da lista.
	 */
	public void setSelectedProdSubAssetCode( String selectedProdSubAssetCode_ )
	{
	  m_selectedProdSubAssetCode = selectedProdSubAssetCode_;
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
	 * @return Retorna o C�digo da Qualifica��o do Produto.
	 */
	public String getProdSubAssetCodeSrc()
	{
	  return m_prodSubAssetCodeSrc;
	}

	/**
	 * @param String prodSubAssetCodeSrc_.
	 * Seta o C�digo da Qualifica��o do Produto.
	 */
	public void setProdSubAssetCodeSrc( String prodSubAssetCodeSrc_ )
	{
	  m_prodSubAssetCodeSrc = prodSubAssetCodeSrc_;
	}

	/**
	 * @return Retorna a Descri��o da Qualifica��o do Produto.
	 */
	public String getProdSubAssetTextSrc()
	{
	  return m_prodSubAssetTextSrc;
	}

	/**
	 * @param String prodSubAssetTextSrc_.
	 * Seta a Descri��o da Qualifica��o do Produto.
	 */
	public void setProdSubAssetTextSrc( String prodSubAssetTextSrc_ )
	{
	  m_prodSubAssetTextSrc = prodSubAssetTextSrc_;
	}
  
	/**
	 * Realiza as valida��es de tipos e tamanhos
	 */
	public ActionErrors validate( ActionMapping actionMapping_,
								HttpServletRequest request_ )
	{
		 ActionErrors errors = new ActionErrors();

			  ODSValidator.validateBigInteger(
									  BaseProdSubAssetListFncVO.C_PROD_SUBASSET_CODE_DESCRIPTION,
									  m_prodSubAssetCodeSrc,
											  BaseTplProdSubAssetEntity.C_PROD_SUBASSET_CODE_SIZE,
									  errors );

			 ODSValidator.validateMaxLength(
												BaseProdSubAssetListFncVO.C_PROD_SUBASSET_TEXT_DESCRIPTION,
									  m_prodSubAssetTextSrc,
											  BaseTplProdSubAssetEntity.C_PROD_SUBASSET_TEXT_SIZE,
									  errors );
			

	 return errors;
   }
}
