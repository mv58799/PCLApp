/*
 * Created on Mar 18, 2007
 *
 */
package com.citibank.ods.modules.product.productfamilyprvt.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.productfamilyprvt.functionality.valueobject.ProductFamilyPrvtListFncVO;
import com.citibank.ods.persistence.pl.dao.TplProductFamilyPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class ProductFamilyPrvtListFnc extends BaseProductFamilyPrvtListFnc
    implements ODSListFnc
{

  /**
   * Retorna instancia do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new ProductFamilyPrvtListFncVO();
  }

  /**
   * Realiza a consulta dos registros que estão na tabela de Current
   */
  public void list( BaseFncVO fncVO_ )
  {
    ProductFamilyPrvtListFncVO listFncVO = ( ProductFamilyPrvtListFncVO ) fncVO_;

    //	Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();

    TplProductFamilyPrvtDAO productFamilyPrvtDAO = factory.getTplProductFamilyPrvtDAO();
    DataSet results = productFamilyPrvtDAO.list(
                                                 listFncVO.getProdFamlCodeSrc(),
                                                 listFncVO.getProdFamlNameSrc(),
                                                 listFncVO.getProdFamlTextSrc() );

    listFncVO.setResults( results );

    if ( results.size() > 0 )
    {
      listFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      listFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }
  }

  /**
   * Carregamentos iniciais - Lista
   */
  public void load( BaseFncVO fncVO_ )
  {
    ProductFamilyPrvtListFncVO listFncVO = ( ProductFamilyPrvtListFncVO ) fncVO_;

    listFncVO.setProdFamlCodeSrc( null );
    listFncVO.setProdFamlNameSrc( null );
    listFncVO.setProdFamlTextSrc( null );
    listFncVO.setResults( null );
  }

  /**
   * Realiza as validações da lista
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    //
  }

}