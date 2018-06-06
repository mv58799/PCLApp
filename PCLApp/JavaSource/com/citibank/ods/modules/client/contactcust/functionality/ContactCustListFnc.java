package com.citibank.ods.modules.client.contactcust.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.modules.client.contactcust.functionality.valueobject.BaseContactCustListFncVO;
import com.citibank.ods.modules.client.contactcust.functionality.valueobject.ContactCustListFncVO;
import com.citibank.ods.persistence.pl.dao.TplContactCustDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author hamilton.matos
 *  
 */
public class ContactCustListFnc extends BaseContactCustListFnc implements
    ODSListFnc
{

  /**
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new ContactCustListFncVO();
  }

  /**
   * Carregamentos iniciais da tela de consulta
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    BaseContactCustListFncVO contactCustListFncVO = ( ContactCustListFncVO ) fncVO_;
    loadCustomer( contactCustListFncVO );
    contactCustListFncVO.setCtcNbrSrc( null );
    contactCustListFncVO.setResults( null );
  }

  /**
   * Recupera o nome completo e o cpf do cliente
   */
  public void loadCustomer( BaseContactCustListFncVO contactFncVO_ )
  {
    if ( contactFncVO_.getCustNbrSrc() != null
         && contactFncVO_.getCustNbrSrc().longValue() > 0 )
    {
      TplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
      customerPrvtEntity.getData().setCustNbr( contactFncVO_.getCustNbrSrc() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplCustomerPrvtDAO tplCustomerPrvtDAO = factory.getTplCustomerPrvtDAO();

      //Verifica se o cliente existe na base de dados
      if ( tplCustomerPrvtDAO.exists( customerPrvtEntity ) )
      {
        customerPrvtEntity = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( customerPrvtEntity );

        //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
        contactFncVO_.setCustFullNameText( customerPrvtEntity.getData().getCustFullNameText() );
        contactFncVO_.setCustCpfCnpjNbr( customerPrvtEntity.getData().getCustCpfCnpjNbr() );
        contactFncVO_.setCustNbrSrc( customerPrvtEntity.getData().getCustNbr() );

      }
      else
      {
        contactFncVO_.setCustFullNameText( "" );
      }
    }
    else
    {
      contactFncVO_.setCustFullNameText( "" );

    }
  }

  /**
   * Executa a consulta em lista
   */
  public void list( BaseFncVO fncVO_ )
  {
    ContactCustListFncVO contactCustListFncVO = ( ContactCustListFncVO ) fncVO_;

    validateList( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplContactCustDAO contactCustDAO = factory.getTplContactCustDAO();
      // Recupera valores do DAO
      DataSet result = contactCustDAO.list(
                                            contactCustListFncVO.getCustNbrSrc(),
                                            contactCustListFncVO.getCtcNbrSrc(),
                                            contactCustListFncVO.getFullNameTextSrc() );

      //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
      contactCustListFncVO.setResults( result );

      if ( result.size() > 0 )
      {
        contactCustListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
      }
      else
      {
        contactCustListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
      }

      loadCustomer( contactCustListFncVO );

    }
  }

  /**
   * Realiza as validações iniciais para a consulta em lista
   * 
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    //
  }

}