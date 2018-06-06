/*
 * Created on Mar 2, 2007
 *
 */
package com.citibank.ods.modules.client.customerprvtcmpl.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject.BaseCustomerPrvtCmplListFncVO;
import com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject.CustomerPrvtCmplListFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplCustomerPrvtCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author gerson.a.rodrigues
 *  
 */
public class CustomerPrvtCmplListFnc extends BaseCustomerPrvtCmplListFnc
    implements ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.customer.functionality.CustomerListFnc#getDAO()
   */
  protected BaseTplCustomerPrvtCmplDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplCustomerPrvtCmplDAO();
  }

  /*
   * Cria uma Instancia do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new CustomerPrvtCmplListFncVO();
  }

  /*
   * O metodo list List conforme os paramentros de busca
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    //  Instanciação do CustomerDAO, obtendo a instância da Factory
    //Execução do método listCustomer com os parâmetros de busca
    //Atualiza o DataSet do FncVO com o retornado pelo método, caso o
    // retorno não seja null

    // Obtém a instância do DAO necessário

    TplCustomerPrvtCmplDAO tplCustomerPrvtCmplDAO = ( TplCustomerPrvtCmplDAO ) this.getDAO();

    CustomerPrvtCmplListFncVO customerCmplListFncVO = ( CustomerPrvtCmplListFncVO ) fncVO_;

    DataSet results = tplCustomerPrvtCmplDAO.list(
                                                   customerCmplListFncVO.getEmNbrSrc(), //                                                           
                                                   customerCmplListFncVO.getGlbRevenSysOffcrNbrSrc(),
                                                   customerCmplListFncVO.getPrvtCustNbrSrc(), //
                                                   customerCmplListFncVO.getPrvtKeyNbrSrc(), //
                                                   customerCmplListFncVO.getCustNbrSrc(), //
                                                   customerCmplListFncVO.getWealthPotnlCodeSrc(), //
                                                   customerCmplListFncVO.getOffcrNbrSrc(), //
                                                   customerCmplListFncVO.getClassCmplcCodeSrc(),//
                                                   BaseODSEntity.C_REC_STAT_CODE_ACTIVE,//
                                                   customerCmplListFncVO.getCustFullNameSrc());//    
	                                               
    customerCmplListFncVO.setResults( results );
    if ( results.size() > 0 )
    {
      super.load( ( BaseCustomerPrvtCmplListFncVO ) fncVO_ );
      customerCmplListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      customerCmplListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    CustomerPrvtCmplListFncVO customerPrvtCmplListFncVO = ( CustomerPrvtCmplListFncVO ) fncVO_;
    getDomainLists( customerPrvtCmplListFncVO );
    if ( customerPrvtCmplListFncVO.isFromSearch() )
    {
      loadCustomerText( customerPrvtCmplListFncVO );
      loadOfficerText( customerPrvtCmplListFncVO );
      customerPrvtCmplListFncVO.setFromSearch( false );
    }
    else
    {
      customerPrvtCmplListFncVO.setCustNbrSrc( null );
      customerPrvtCmplListFncVO.setCustTextSrc( null );
      customerPrvtCmplListFncVO.setOffcrNbrSrc( null );
      customerPrvtCmplListFncVO.setOffcrTextSrc( null );
      customerPrvtCmplListFncVO.setGlbRevenSysOffcrNbrSrc( null );
      customerPrvtCmplListFncVO.setResults( null );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */

  }
}