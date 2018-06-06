/*
 * Created on 19/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodasset.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.prodasset.functionality.valueobject.ProdAssetListFncVO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author rcoelho
 */
public class ProdAssetListFnc extends BaseProdAssetListFnc implements
	ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
	return new ProdAssetListFncVO();
  }

  public void list( BaseFncVO fncVO_ )
  {
	ProdAssetListFncVO prodAssetListFncVO = ( ProdAssetListFncVO ) fncVO_;

	// 	Obtém a instância da Factory
	ODSDAOFactory factory = ODSDAOFactory.getInstance();

	TplProdAssetDAO tplProdAssetDAO = factory.getTplProdAssetDAO();
	DataSet results = tplProdAssetDAO.list(
											   prodAssetListFncVO.getProdAssetCodeSrc(),
											   prodAssetListFncVO.getProdAssetTextSrc() );

	prodAssetListFncVO.setResults( results );
    
	if ( results.size() > 0 )
	{
	  prodAssetListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
	}
	else
	{
	  prodAssetListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
	}
  }

  /**
   * Carregamentos iniciais da tela de consulta
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
	//Limpando os dados para o carregamento
	ProdAssetListFncVO prodAssetListFncVO = ( ProdAssetListFncVO ) fncVO_;
	prodAssetListFncVO.setResults( null );
	prodAssetListFncVO.setProdAssetCodeSrc( null );
	prodAssetListFncVO.setProdAssetTextSrc( null );
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
