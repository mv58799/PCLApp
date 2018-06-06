package com.citibank.ods.modules.client.custaddress.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.modules.client.custaddress.functionality.valueobject.BaseCustAddressListFncVO;
import com.citibank.ods.modules.client.custaddress.functionality.valueobject.CustAddressListFncVO;
import com.citibank.ods.persistence.bg.dao.TbgCustAddressDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author hamilton.matos
 *  
 */
public class CustAddressListFnc extends BaseCustAddressListFnc implements
    ODSListFnc
{

  /**
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
  }

  /**
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new CustAddressListFncVO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    CustAddressListFncVO custAddressListFncVO = ( CustAddressListFncVO ) fncVO_;

    validateList( fncVO_ );

    if ( !custAddressListFncVO.hasErrors() )
    {
      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TbgCustAddressDAO custAddressDAO = factory.getTbgCustAddressDAO();
      // Recupera valores do DAO
      DataSet result = custAddressDAO.list(
                                            custAddressListFncVO.getCustNbrSrc(),
                                            custAddressListFncVO.getCustCpfCnpjNbrSrc(),
                                            custAddressListFncVO.getCustFullNameTextSrc() );

      //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
      custAddressListFncVO.setResults( result );
      if ( result.size() > 0 )
      {
        custAddressListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
      }
      else
      {
        custAddressListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
      }

      loadCustomer( custAddressListFncVO );
    }

  }

  /**
   * Carregamentos iniciais da tela de consulta
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    CustAddressListFncVO custAddressListFncVO = ( CustAddressListFncVO ) fncVO_;
    loadCustomer( custAddressListFncVO );
    custAddressListFncVO.setResults( null );
  }

  /**
   * Recupera o nome completo e o cpf do cliente
   */
  public void loadCustomer( BaseCustAddressListFncVO custAddressListFncVO_ )
  {
    if ( custAddressListFncVO_.getCustNbrSrc() != null
         && custAddressListFncVO_.getCustNbrSrc().longValue() > 0 )
    {
      TplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
      customerPrvtEntity.getData().setCustNbr(
                                               custAddressListFncVO_.getCustNbrSrc() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplCustomerPrvtDAO tplCustomerPrvtDAO = factory.getTplCustomerPrvtDAO();

      //Realiza a consulta no DAO
      customerPrvtEntity = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( customerPrvtEntity );

      //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
      custAddressListFncVO_.setCustFullNameTextSrc( customerPrvtEntity.getData().getCustFullNameText() );
      custAddressListFncVO_.setCustCpfCnpjNbrSrc( customerPrvtEntity.getData().getCustCpfCnpjNbr() );
    }
  }
}