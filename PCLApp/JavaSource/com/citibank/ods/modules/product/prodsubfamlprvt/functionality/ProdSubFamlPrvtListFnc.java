/*
 * Created on Mar 18, 2007
 *
 */
package com.citibank.ods.modules.product.prodsubfamlprvt.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.prodsubfamlprvt.functionality.valueobject.ProdSubFamlPrvtListFncVO;
import com.citibank.ods.persistence.pl.dao.TplProdSubFamlPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class ProdSubFamlPrvtListFnc extends BaseProdSubFamlPrvtListFnc
    implements ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new ProdSubFamlPrvtListFncVO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    ProdSubFamlPrvtListFncVO listFncVO = ( ProdSubFamlPrvtListFncVO ) fncVO_;

    // 	Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();

    TplProdSubFamlPrvtDAO productSubFamilyPrvtDAO = factory.getTplProductSubFamilyPrvtDAO();
    DataSet results = productSubFamilyPrvtDAO.list(
                                                    listFncVO.getProdSubFamlCodeSrc(),
                                                    listFncVO.getProdSubFamlNameSrc(),
                                                    listFncVO.getProdSubFamlTextSrc() );

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

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    ProdSubFamlPrvtListFncVO listFncVO = ( ProdSubFamlPrvtListFncVO ) fncVO_;

    listFncVO.setProdSubFamlCodeSrc( null );
    listFncVO.setProdSubFamlNameSrc( null );
    listFncVO.setProdSubFamlTextSrc( null );
    listFncVO.setResults( null );
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