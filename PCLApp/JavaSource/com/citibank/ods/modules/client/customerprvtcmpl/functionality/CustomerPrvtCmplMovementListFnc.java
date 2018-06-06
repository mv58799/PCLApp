/*
 * Created on Mar 2, 2007
 *
 */
package com.citibank.ods.modules.client.customerprvtcmpl.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.client.customerprvtcmpl.form.CustomerPrvtCmplMovementListForm;
import com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject.CustomerPrvtCmplMovementListFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplCustomerPrvtCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author gerson.a.rodrigues
 *  
 */
public class CustomerPrvtCmplMovementListFnc extends
    BaseCustomerPrvtCmplListFnc implements ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.customer.functionality.CustomerListFnc#getDAO()
   */
  protected BaseTplCustomerPrvtCmplDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplCustomerPrvtCmplMovDAO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new CustomerPrvtCmplMovementListFncVO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    //  Instanciação do CustomerDAO, obtendo a instância da Factory
    //Execução do método listCustomer com os parâmetros de busca
    //Atualiza o DataSet do FncVO com o retornado pelo método, caso o
    // retorno não seja null

    // Obtém a instância do DAO necessário
    TplCustomerPrvtCmplMovDAO tplCustomerPrvtCmplMovDAO = ( TplCustomerPrvtCmplMovDAO ) this.getDAO();

    CustomerPrvtCmplMovementListFncVO customerCmplListFncVO = ( CustomerPrvtCmplMovementListFncVO ) fncVO_;

    DataSet results = tplCustomerPrvtCmplMovDAO.list(
                                                      customerCmplListFncVO.getEmNbrSrc(),
                                                      customerCmplListFncVO.getGlbRevenSysOffcrNbrSrc(),
                                                      customerCmplListFncVO.getPrvtCustNbrSrc(),
                                                      customerCmplListFncVO.getPrvtKeyNbrSrc(),
                                                      customerCmplListFncVO.getCustNbrSrc(),
                                                      customerCmplListFncVO.getWealthPotnlCodeSrc(),
                                                      customerCmplListFncVO.getOffcrNbrSrc(),
                                                      customerCmplListFncVO.getClassCmplcCodeSrc(),
                                                      customerCmplListFncVO.getLastUpdUserIdSrc(),
                                                      customerCmplListFncVO.getCustFullNameSrc(),
                                                      customerCmplListFncVO.getOffcrTextSrc(),
                                                      customerCmplListFncVO.getCustPrvtStatCodeSrc(),
                                                      customerCmplListFncVO.getPrvtCustTypeCodeSrc());
    if ( results.size() > 0 )
    {
      customerCmplListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      customerCmplListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

    customerCmplListFncVO.setResults( results );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    CustomerPrvtCmplMovementListFncVO customerPrvtCmplListFncVO = ( CustomerPrvtCmplMovementListFncVO ) fncVO_;
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
      customerPrvtCmplListFncVO.setResults( null );
    }
  }

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );

    //  Acertando os tipos
    CustomerPrvtCmplMovementListFncVO customerFncVO = ( CustomerPrvtCmplMovementListFncVO ) fncVO_;
    CustomerPrvtCmplMovementListForm customerForm = ( CustomerPrvtCmplMovementListForm ) form_;

    //  Atualizando os dados: Form -> FncVO
    customerFncVO.setLastUpdUserIdSrc( customerForm.getLastUpdUserIdSrc() );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {

  }
}