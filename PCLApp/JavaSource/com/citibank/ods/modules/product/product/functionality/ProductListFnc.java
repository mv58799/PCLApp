/*
 * Created on Apr 4, 2007
 *
 */
package com.citibank.ods.modules.product.product.functionality;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.DataSetRow;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.broker.functionality.ProductListGrid;
import com.citibank.ods.modules.product.product.functionality.valueobject.BaseProductListFncVO;
import com.citibank.ods.modules.product.product.functionality.valueobject.ProductListFncVO;
import com.citibank.ods.persistence.pl.dao.TplProductDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author acacio.domingos
 * 
 * Fnc da consulta em lista dos dados complementares de Produtos
 */
public class ProductListFnc extends BaseProductListFnc
{

  /**
   * Retorna instância de um FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new ProductListFncVO();
  }

  /**
   * Executar a consulta em lista
   */
  public void list( BaseFncVO fncVO_ )
  {
    ProductListFncVO catProductFncVO = ( ProductListFncVO ) fncVO_;
	ArrayList list = null;
	DataSetRow row;
	ProductListGrid productListGrid = null;

    ODSDAOFactory factory = ODSDAOFactory.getInstance();

    TplProductDAO tplProductDAO = factory.getTplProductDAO();

    DataSet results = tplProductDAO.list(
                                          catProductFncVO.getProdCodeSrc(),
                                          catProductFncVO.getProdFamlCodeSrc(),
                                          catProductFncVO.getProdNameSrc(),
                                          catProductFncVO.getProdQlfyCodeSrc(),
                                          catProductFncVO.getProdRiskCatCodeSrc(),
                                          catProductFncVO.getProdSubFamlCodeSrc(),
										  catProductFncVO.getOrderBy() );
    //Monta o arrayList que será utilizado para o DisplayTable
	if(results.size() > 0){
	  list = new ArrayList();
	}
	
	for ( int indexRow = 0; indexRow < results.size(); indexRow++ )
	{
	  row = results.getRow( indexRow );			

	  productListGrid = new ProductListGrid();
	  
	  if(row.getStringByName("PROD_CODE") != null){
		productListGrid.setProdCode(row.getStringByName("PROD_CODE").toString());	  
	  }
	  
	  if(row.getStringByName("SYS_CODE") != null){
		productListGrid.setSysCode(row.getStringByName("SYS_CODE").toString());	  
	  }
	  
	  if(row.getStringByName("SYS_SEG_CODE") != null){
		productListGrid.setSysSegCode(BigInteger.valueOf(row.getBigDecimalByName("SYS_SEG_CODE").longValue()));	  
	  }
	  
	  if(row.getStringByName("PROD_NAME") != null){
	    productListGrid.setProdName(row.getStringByName("PROD_NAME").toString());	  
	  }
	  
	  if(row.getStringByName("PROD_TEXT") != null){
	    productListGrid.setProdText(row.getStringByName("PROD_TEXT").toString());	  
	  }
	  
	  if(row.getStringByName("PROD_FAML_NAME") != null){
	    productListGrid.setProdFamlName(row.getStringByName("PROD_FAML_NAME").toString());	  
	  }	  
	  
	  if(row.getStringByName("PROD_SUB_FAML_NAME") != null){
	    productListGrid.setProdSubFamlName(row.getStringByName("PROD_SUB_FAML_NAME").toString());	  
	  }
	  if(row.getStringByName("APROVACAO") != null){
		productListGrid.setAprovacao(row.getStringByName("APROVACAO").toString());
      }
	  
	  list.add( productListGrid );
	}	
    
	catProductFncVO.setListProduct(list);
    catProductFncVO.setResults(results);
    
    if ( results.size() > 0 )
    {
      catProductFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      catProductFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }
  }
  /**
   * Carregameto inicial - consulta em lista
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    //Limpando os dados para o carregamento
    ProductListFncVO productListFncVO = ( ProductListFncVO ) fncVO_;
    productListFncVO.setResults(null);
	productListFncVO.setListProduct(null);
    productListFncVO.setProdCodeSrc(null);
    productListFncVO.setProdNameSrc(null);
    productListFncVO.setProdQlfyCodeSrc(null);
    productListFncVO.setProdFamlCodeSrc(null);
    productListFncVO.setProdSubFamlCodeSrc(null);
    productListFncVO.setProdRiskCatCodeSrc(null);
    super.loadDomains( ( BaseProductListFncVO ) fncVO_ );
  }
  
  //Botão Limpar 
  public void clearPage( BaseFncVO fncVO_ )
  {
    //Limpando os dados para o carregamento
    ProductListFncVO productListFncVO = ( ProductListFncVO ) fncVO_;
    
    productListFncVO.clearErrors();
    productListFncVO.clearMessages();
    productListFncVO.setResults(null);
    productListFncVO.setProdCodeSrc(null);
    productListFncVO.setProdNameSrc(null);
    productListFncVO.setProdQlfyCodeSrc(null);
    productListFncVO.setProdFamlCodeSrc(null);
    productListFncVO.setProdSubFamlCodeSrc(null);
    productListFncVO.setProdRiskCatCodeSrc(null);
    
  }
  
}
