/*
 * Created on 19/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodsubasset.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.prodsubasset.functionality.valueobject.ProdSubAssetListFncVO;
import com.citibank.ods.persistence.pl.dao.TplProdSubAssetDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author rcoelho
 */
public class ProdSubAssetListFnc extends BaseProdSubAssetListFnc implements
	ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
	return new ProdSubAssetListFncVO();
  }

  public void list( BaseFncVO fncVO_ )
  {
	ProdSubAssetListFncVO prodSubAssetListFncVO = ( ProdSubAssetListFncVO ) fncVO_;

	// 	Obtém a instância da Factory
	ODSDAOFactory factory = ODSDAOFactory.getInstance();

	TplProdSubAssetDAO tplProdSubAssetDAO = factory.getTplProdSubAssetDAO();
	DataSet results = tplProdSubAssetDAO.list(
											   prodSubAssetListFncVO.getProdSubAssetCodeSrc(),
											   prodSubAssetListFncVO.getProdSubAssetTextSrc() );

	prodSubAssetListFncVO.setResults( results );
    
	if ( results.size() > 0 )
	{
	  prodSubAssetListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
	}
	else
	{
	  prodSubAssetListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
	}
  }

  /**
   * Carregamentos iniciais da tela de consulta
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
	//Limpando os dados para o carregamento
	ProdSubAssetListFncVO prodSubAssetListFncVO = ( ProdSubAssetListFncVO ) fncVO_;
	prodSubAssetListFncVO.setResults( null );
	prodSubAssetListFncVO.setProdSubAssetCodeSrc( null );
	prodSubAssetListFncVO.setProdSubAssetTextSrc( null );
  }

  /**
   * Realiza as validações iniciais para a consulta em lista
   * 
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
	
  }

}
