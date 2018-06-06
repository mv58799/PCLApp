/*
 * Created on Mar 18, 2007
 *
 */
package com.citibank.ods.modules.product.prodsubfamlprvt.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.prodsubfamlprvt.functionality.valueobject.ProdSubFamlPrvtMovementListFncVO;
import com.citibank.ods.persistence.pl.dao.TplProdSubFamlPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class ProdSubFamlPrvtMovementListFnc extends BaseProdSubFamlPrvtListFnc
    implements ODSListFnc
{

  /**
   * Retorna instancia do FncVO
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new ProdSubFamlPrvtMovementListFncVO();
  }

  /**
   * Retorna uma lista de sub-famílias de produto de acordo como os critérios
   * especificados
   * 
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    ProdSubFamlPrvtMovementListFncVO movementListFncVO = ( ProdSubFamlPrvtMovementListFncVO ) fncVO_;

    // 	Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();

    TplProdSubFamlPrvtMovDAO familyPrvtMovDAO = factory.getTplProductSubFamilyPrvtMovDAO();
    DataSet results = familyPrvtMovDAO.list(
                                             movementListFncVO.getProdSubFamlCodeSrc(),
                                             movementListFncVO.getProdSubFamlNameSrc(),
                                             movementListFncVO.getProdSubFamlTextSrc(),
                                             movementListFncVO.getLastUpdUserIdSrc() );
    movementListFncVO.setResults( results );

    if ( results.size() > 0 )
    {
      movementListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      movementListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    ProdSubFamlPrvtMovementListFncVO movementListFncVO = ( ProdSubFamlPrvtMovementListFncVO ) fncVO_;
    movementListFncVO.setProdSubFamlCodeSrc( null );
    movementListFncVO.setProdSubFamlNameSrc( null );
    movementListFncVO.setProdSubFamlTextSrc( null );
    movementListFncVO.setResults( null );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    //
  }

}