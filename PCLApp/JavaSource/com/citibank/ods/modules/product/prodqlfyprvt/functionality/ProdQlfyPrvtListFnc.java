/*
 * Created on Mar 17, 2007
 *
 */
package com.citibank.ods.modules.product.prodqlfyprvt.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.prodqlfyprvt.functionality.valueobject.ProdQlfyPrvtListFncVO;
import com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author fernando.salgado
 * 
 */
public class ProdQlfyPrvtListFnc extends BaseProdQlfyPrvtListFnc implements
    ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new ProdQlfyPrvtListFncVO();
  }

  public void list( BaseFncVO fncVO_ )
  {
    ProdQlfyPrvtListFncVO prodQlfyPrvtListFncVO = ( ProdQlfyPrvtListFncVO ) fncVO_;

    // 	Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();

    TplProdQlfyPrvtDAO tplProdQlfyPrvtDAO = factory.getTplProdQlfyPrvtDAO();
    DataSet results = tplProdQlfyPrvtDAO.list(
                                               prodQlfyPrvtListFncVO.getProdQlfyCodeSrc(),
                                               prodQlfyPrvtListFncVO.getProdQlfyTextSrc() );

    prodQlfyPrvtListFncVO.setResults( results );
    
    if ( results.size() > 0 )
    {
      prodQlfyPrvtListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      prodQlfyPrvtListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }
  }

  /**
   * Carregamentos iniciais da tela de consulta
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    //Limpando os dados para o carregamento
    ProdQlfyPrvtListFncVO prodQlfyPrvtListFncVO = ( ProdQlfyPrvtListFncVO ) fncVO_;
    prodQlfyPrvtListFncVO.setResults( null );
    prodQlfyPrvtListFncVO.setProdQlfyCodeSrc( null );
    prodQlfyPrvtListFncVO.setProdQlfyTextSrc( null );
  }

  /**
   * Realiza as validações iniciais para a consulta em lista
   * 
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    //
  }
}