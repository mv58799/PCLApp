/*
 * Created on Mar 18, 2007
 *
 */
package com.citibank.ods.modules.product.prodsubfamlprvt.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.prodsubfamlprvt.functionality.valueobject.ProdSubFamlPrvtHistoryListFncVO;
import com.citibank.ods.persistence.pl.dao.TplProdSubFamlPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class ProdSubFamlPrvtHistoryListFnc extends BaseProdSubFamlPrvtListFnc
    implements ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new ProdSubFamlPrvtHistoryListFncVO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    ProdSubFamlPrvtHistoryListFncVO prvtHistoryListFncVO = ( ProdSubFamlPrvtHistoryListFncVO ) fncVO_;

    // 	Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();

    TplProdSubFamlPrvtHistDAO tplProductSubFamilyPrvtHistDAO = factory.getTplProductSubFamilyPrvtHistDAO();
    DataSet results = tplProductSubFamilyPrvtHistDAO.list(
                                                           prvtHistoryListFncVO.getProdSubFamlCodeSrc(),
                                                           prvtHistoryListFncVO.getProdSubFamlNameSrc(),
                                                           prvtHistoryListFncVO.getProdSubFamlTextSrc(),
                                                           prvtHistoryListFncVO.getProdSubFamlRefDate() );

    prvtHistoryListFncVO.setResults( results );

    if ( results.size() > 0 )
    {
      prvtHistoryListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      prvtHistoryListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    ProdSubFamlPrvtHistoryListFncVO prvtHistoryListFncVO = ( ProdSubFamlPrvtHistoryListFncVO ) fncVO_;

    prvtHistoryListFncVO.setProdSubFamlCodeSrc( null );
    prvtHistoryListFncVO.setProdSubFamlNameSrc( null );
    prvtHistoryListFncVO.setProdSubFamlTextSrc( null );
    prvtHistoryListFncVO.setProdSubFamlRefDate( null );
    prvtHistoryListFncVO.setResults( null );

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