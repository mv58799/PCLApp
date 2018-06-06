/*
 * Created on 19/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodassettype.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.prodassettype.functionality.valueobject.ProdAssetTypeListFncVO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetTypeDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author rcoelho
 */
public class ProdAssetTypeListFnc extends BaseProdAssetTypeListFnc implements
	ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
	return new ProdAssetTypeListFncVO();
  }

  public void list( BaseFncVO fncVO_ )
  {
	ProdAssetTypeListFncVO prodAssetTypeListFncVO = ( ProdAssetTypeListFncVO ) fncVO_;

	// 	Obtém a instância da Factory
	ODSDAOFactory factory = ODSDAOFactory.getInstance();

	TplProdAssetTypeDAO tplProdAssetTypeDAO = factory.getTplProdAssetTypeDAO();
	DataSet results = tplProdAssetTypeDAO.list(
											   prodAssetTypeListFncVO.getProdAssetTypeCodeSrc(),
											   prodAssetTypeListFncVO.getProdAssetTypeTextSrc() );

	prodAssetTypeListFncVO.setResults( results );
    
	if ( results.size() > 0 )
	{
	  prodAssetTypeListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
	}
	else
	{
	  prodAssetTypeListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
	}
  }

  /**
   * Carregamentos iniciais da tela de consulta
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
	//Limpando os dados para o carregamento
	ProdAssetTypeListFncVO prodAssetTypeListFncVO = ( ProdAssetTypeListFncVO ) fncVO_;
	prodAssetTypeListFncVO.setResults( null );
	prodAssetTypeListFncVO.setProdAssetTypeCodeSrc( null );
	prodAssetTypeListFncVO.setProdAssetTypeTextSrc( null );
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
