/*
 * Created on Mar 18, 2007
 *
 */
package com.citibank.ods.modules.product.productfamilyprvt.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.productfamilyprvt.functionality.valueobject.ProductFamilyPrvtHistoryListFncVO;
import com.citibank.ods.persistence.pl.dao.TplProductFamilyPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class ProductFamilyPrvtHistoryListFnc extends
    BaseProductFamilyPrvtListFnc implements ODSListFnc
{

  /**
   * Retorna instancia do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new ProductFamilyPrvtHistoryListFncVO();
  }

  /**
   * Realiza a consulta dos registros na tabela de histórico
   */
  public void list( BaseFncVO fncVO_ )
  {
    ProductFamilyPrvtHistoryListFncVO familyPrvtHistoryListFncVO = ( ProductFamilyPrvtHistoryListFncVO ) fncVO_;

    // 	Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();

    TplProductFamilyPrvtHistDAO familyPrvtHistDAO = factory.getTplProductFamilyPrvtHistDAO();
    DataSet results = familyPrvtHistDAO.list(
                                              familyPrvtHistoryListFncVO.getProdFamlCodeSrc(),
                                              familyPrvtHistoryListFncVO.getProdFamlNameSrc(),
                                              familyPrvtHistoryListFncVO.getProdFamlTextSrc(),
                                              familyPrvtHistoryListFncVO.getProdFamlRefDate() );

    familyPrvtHistoryListFncVO.setResults( results );

    if ( results.size() > 0 )
    {
      familyPrvtHistoryListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      familyPrvtHistoryListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }
  }

  /**
   * Realiza os carregamentos iniciais da consulta
   */
  public void load( BaseFncVO fncVO_ )
  {
    ProductFamilyPrvtHistoryListFncVO familyPrvtHistoryListFncVO = ( ProductFamilyPrvtHistoryListFncVO ) fncVO_;

    familyPrvtHistoryListFncVO.setProdFamlCodeSrc( null );
    familyPrvtHistoryListFncVO.setProdFamlNameSrc( null );
    familyPrvtHistoryListFncVO.setProdFamlTextSrc( null );
    familyPrvtHistoryListFncVO.setProdFamlRefDate( null );
    familyPrvtHistoryListFncVO.setResults( null );

  }

  /**
   * Realiza as validações de consulta
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    // TODO Auto-generated method stub

  }

}