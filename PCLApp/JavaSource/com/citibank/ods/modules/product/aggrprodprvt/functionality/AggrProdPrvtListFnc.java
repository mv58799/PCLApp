/*
 * Created on Mar 12, 2007
 *
 */
package com.citibank.ods.modules.product.aggrprodprvt.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.aggrprodprvt.functionality.valueobject.AggrProdPrvtListFncVO;
import com.citibank.ods.modules.product.aggrprodprvt.functionality.valueobject.BaseAggrProdPrvtListFncVO;
import com.citibank.ods.persistence.pl.dao.TplAggrProdPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class AggrProdPrvtListFnc extends BaseAggrProdPrvtListFnc implements
    ODSListFnc
{

  /**
   * Retorna a instancia do VO de Product Aggregate
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new AggrProdPrvtListFncVO();
  }

  /**
   * Executa a consulta em lista
   */
  public void list( BaseFncVO fncVO_ )
  {
    AggrProdPrvtListFncVO productAggrListFncVO = ( AggrProdPrvtListFncVO ) fncVO_;

    // Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();

    TplAggrProdPrvtDAO aggrProdPrvtDAO = factory.getTplAggrProdPrvtDAO();
    DataSet result = aggrProdPrvtDAO.listProductAggregate(
                                                           productAggrListFncVO.getPrvtProdAggrCodeSrc(),
                                                           productAggrListFncVO.getPrvtProdAggrTextSrc() );
    productAggrListFncVO.setResults( result );

    if ( result.size() > 0 )
    {
      productAggrListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      productAggrListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Realiza os Carregamentos iniciais
   * 
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    BaseAggrProdPrvtListFncVO productAggrListFncVO = ( BaseAggrProdPrvtListFncVO ) fncVO_;
    productAggrListFncVO.setResults( null );
    productAggrListFncVO.setPrvtProdAggrCodeSrc( null );
    productAggrListFncVO.setPrvtProdAggrTextSrc( null );
  }
}