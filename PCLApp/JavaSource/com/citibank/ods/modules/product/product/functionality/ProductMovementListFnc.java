/*
 * Created on Apr 4, 2007
 *
 */
package com.citibank.ods.modules.product.product.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.product.functionality.valueobject.BaseProductListFncVO;
import com.citibank.ods.modules.product.product.functionality.valueobject.ProductMovementListFncVO;
import com.citibank.ods.persistence.pl.dao.TplProductMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author acacio.domingos
 * 
 * Fnc da consulta em lista dos dados complementares de Produtos
 */
public class ProductMovementListFnc extends BaseProductListFnc
{

  /**
   * Retorna instância de um FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new ProductMovementListFncVO();
  }

  /**
   * Executar a consulta em lista
   */
  public void list( BaseFncVO fncVO_ )
  {
    ProductMovementListFncVO catProductMovementFncVO = ( ProductMovementListFncVO ) fncVO_;

    ODSDAOFactory factory = ODSDAOFactory.getInstance();

    TplProductMovDAO tplProductMovDAO = factory.getTplProductMovDAO();

    DataSet results = tplProductMovDAO.list(
                                          catProductMovementFncVO.getProdCodeSrc(),
                                          catProductMovementFncVO.getProdFamlCodeSrc(),
                                          catProductMovementFncVO.getProdNameSrc(),
                                          catProductMovementFncVO.getProdQlfyCodeSrc(),
                                          catProductMovementFncVO.getProdRiskCatCodeSrc(),
                                          catProductMovementFncVO.getProdSubFamlCodeSrc() ,
                                          catProductMovementFncVO.getLastUpdUserIdSrc() );

    catProductMovementFncVO.setResults( results );

    if ( results.size() > 0 )
    {
      catProductMovementFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      catProductMovementFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }
  }
  public void load( BaseFncVO fncVO_ )
  {
    super.loadDomains( ( BaseProductListFncVO ) fncVO_ );

    //Limpando os dados para o carregamento
    ProductMovementListFncVO productMovementListFncVO = ( ProductMovementListFncVO ) fncVO_;
    productMovementListFncVO.setResults(null);
    productMovementListFncVO.setProdCodeSrc(null);
    productMovementListFncVO.setProdNameSrc(null);
    productMovementListFncVO.setProdQlfyCodeSrc(null);
    productMovementListFncVO.setProdFamlCodeSrc(null);
    productMovementListFncVO.setProdSubFamlCodeSrc(null);
    productMovementListFncVO.setProdRiskCatCodeSrc(null);

    
  }
}