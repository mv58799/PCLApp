package com.citibank.ods.modules.client.contactcust.functionality;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.TplContactCustEntity;
import com.citibank.ods.modules.client.contactcust.functionality.valueobject.BaseContactCustDetailFncVO;
import com.citibank.ods.modules.client.contactcust.functionality.valueobject.ContactCustDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplContactCustDAO;
import com.citibank.ods.persistence.pl.dao.TplContactCustDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author hamilton.matos  
 * 
 */

 /*
  * @author Ronaldo Machado G&P Java Team
  * descrição: Manutenção realizada para corrigir a maneira em que é gravado o ID na tpl_contact_cust
  * data:04/07/2008
  */
public class ContactCustDetailFnc extends BaseContactCustDetailFnc implements
    ODSDetailFnc
{

  /*
   * Verifica se já existe um registro na tabela de "Current" com o código
   * passado e o seu status é "Ativo"
   */
  private boolean existsActive( ContactCustDetailFncVO contactCustDetailFncVO_ )
  {
    TplContactCustDAO contactCustDAO = ODSDAOFactory.getInstance().getTplContactCustDAO();
    return contactCustDAO.existsActive( ( TplContactCustEntity ) contactCustDetailFncVO_.getTplContactCustEntity() );
  }

  /**
   * Retorna uma instância do FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new ContactCustDetailFncVO();
  }

  /**
   * Retorna o DAO de "Current"
   * @see com.citibank.ods.modules.product.prodriskcatprvt.functionality.BaseProdRiskCatPrvtDetailFnc#getDAO()
   */
  protected BaseTplContactCustDAO getDAO()
  {
    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
    TplContactCustDAO tplContactCustDAO = odsDAOFactory.getTplContactCustDAO();
    return tplContactCustDAO;
  }

  /**
   * Insere os dados de um Contato de Cliente.
   */
  public void insert( BaseFncVO fncVO_ )
  {
    this.validateInsert( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      TplContactCustDAO tplContactCustDAO = ODSDAOFactory.getInstance().getTplContactCustDAO();

      ContactCustDetailFncVO contactCustDetailFncVO = ( ContactCustDetailFncVO ) fncVO_;
      TplContactCustEntity contactCustEntity = ( TplContactCustEntity ) contactCustDetailFncVO.getTplContactCustEntity();

      contactCustEntity.getData().setLastAuthDate( new Date() );

      contactCustEntity.getData().setLastAuthUserId(
                                                     contactCustDetailFncVO.getLoggedUser() != null
                                                                                                   ? contactCustDetailFncVO.getLoggedUser().getUserID()
                                                                                                   : "" );

      contactCustEntity.getData().setLastUpdDate( new Date() );

      contactCustEntity.getData().setLastUpdUserId(
                                                    contactCustDetailFncVO.getLoggedUser() != null
                                                                                                  ? contactCustDetailFncVO.getLoggedUser().getUserID()
                                                                                                  : "" );

      contactCustEntity.getData().setRecStatCode( "A" );
      
      //Gera um novo valor para sequence e retorna o mesmo
	  contactCustEntity.getData().setCtcNbr(new BigInteger(tplContactCustDAO.getNextValContactCustNbr().toString()));

      tplContactCustDAO.insert( contactCustEntity );
    }
  }

  /**
   * Atualiza os dados de um Contato de Cliente.
   */
  public void update( BaseFncVO fncVO_ )
  {
    validateUpdate( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      ContactCustDetailFncVO contactCustDetailFncVO = ( ContactCustDetailFncVO ) fncVO_;

      TplContactCustEntity contactCustEntity = ( TplContactCustEntity ) contactCustDetailFncVO.getTplContactCustEntity();

      contactCustEntity.getData().setLastAuthDate( new Date() );

      contactCustEntity.getData().setLastAuthUserId(
                                                     contactCustDetailFncVO.getLoggedUser() != null
                                                                                                   ? contactCustDetailFncVO.getLoggedUser().getUserID()
                                                                                                   : "" );

      contactCustEntity.getData().setLastUpdDate( new Date() );

      contactCustEntity.getData().setLastUpdUserId(
                                                    contactCustDetailFncVO.getLoggedUser() != null
                                                                                                  ? contactCustDetailFncVO.getLoggedUser().getUserID()
                                                                                                  : "" );

      contactCustEntity.getData().setRecStatCode(
                                                  TplContactCustEntity.C_REC_STAT_CODE_ACTIVE );

      TplContactCustDAO tplContactCustDAO = ODSDAOFactory.getInstance().getTplContactCustDAO();
      tplContactCustDAO.update( contactCustEntity );
    }
  }

  /**
   * Remove um Contato de Cliente.
   */
  public void delete( BaseFncVO fncVO_ )
  {
    ContactCustDetailFncVO detailFncVO = ( ContactCustDetailFncVO ) fncVO_;

    validateDelete( detailFncVO );
    if ( !detailFncVO.hasErrors() )
    {

      TplContactCustDAO tplContactCustDAO = ODSDAOFactory.getInstance().getTplContactCustDAO();
      TplContactCustEntity contactCustEntity = ( TplContactCustEntity ) tplContactCustDAO.find( detailFncVO.getTplContactCustEntity() );

      contactCustEntity.getData().setLastUpdDate( new Date() );
      contactCustEntity.getData().setLastUpdUserId(
                                                    fncVO_.getLoggedUser() != null
                                                                                  ? fncVO_.getLoggedUser().getUserID()
                                                                                  : "" );
      contactCustEntity.getData().setRecStatCode(
                                                  TplContactCustEntity.C_REC_STAT_CODE_INACTIVE );

      tplContactCustDAO.delete( contactCustEntity );
    }
  }

  /**
   * Carregamentos iniciais - Consult
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    BaseContactCustDetailFncVO fncVO = ( ContactCustDetailFncVO ) fncVO_;
    loadContactCust( fncVO );
    loadCustFullNameText( fncVO );
  }

  /**
   * Carregamentos iniciais - Insert
   */
  public void loadForInsert( BaseFncVO fncVO_ )
  {
    BaseContactCustDetailFncVO contactCustDetailFncVO = ( ContactCustDetailFncVO ) fncVO_;
    TplContactCustDAO tplContactCustDAO = ODSDAOFactory.getInstance().getTplContactCustDAO();
    TplContactCustEntity tplContactCustEntity = new TplContactCustEntity();

    tplContactCustEntity.getData().setCustNbr(
                                               contactCustDetailFncVO.getTplContactCustEntity().getData().getCustNbr() );
    
    /*tplContactCustEntity.getData().setCtcNbr(
                                          new BigInteger(
                                                          tplContactCustDAO.getNextContactCustNbr().toString() ) );*/


    contactCustDetailFncVO.setTplContactCustEntity( tplContactCustEntity );
    loadCustFullNameText( contactCustDetailFncVO );

  }

  /**
   * Carregamentos iniciais - Update
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    BaseContactCustDetailFncVO fncVO = ( ContactCustDetailFncVO ) fncVO_;
    loadContactCust( fncVO );
  }

  /**
   * Carregamentos iniciais - Delete
   */
  public void loadForDelete( BaseFncVO fncVO_ )
  {
    BaseContactCustDetailFncVO fncVO = ( ContactCustDetailFncVO ) fncVO_;
    loadContactCust( fncVO );
    loadCustFullNameText( fncVO );
  }

  /**
   * Realiza as validações - Insert
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
    ContactCustDetailFncVO contactCustDetailFncVO = ( ContactCustDetailFncVO ) fncVO_;

    /*if ( contactCustDetailFncVO.getTplContactCustEntity().getData().getCustNbr() == null
         || contactCustDetailFncVO.getTplContactCustEntity().getData().getCustNbr().intValue() == 0
         || contactCustDetailFncVO.getTplContactCustEntity().getData().getCtcNbr() == null
         || contactCustDetailFncVO.getTplContactCustEntity().getData().getCtcNbr().intValue() == 0 )*/
         
	/*if ( contactCustDetailFncVO.getTplContactCustEntity().getData().getCustNbr() == null
		|| contactCustDetailFncVO.getTplContactCustEntity().getData().getCustNbr().intValue() == 0)     
    {
      fncVO_.addError( ContactCustDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_CUST_NBR_DESCRIPTION );
      fncVO_.addError( ContactCustDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_CTC_NBR_DESCRIPTION );
    }*/

    // caso os campos obrigatórios estejam presentes
    /*if ( !fncVO_.hasErrors() )
    {
      //    Validar se já existe um registro com este codigo na "Current",
      if ( this.existsActive( contactCustDetailFncVO ) )
      {
        fncVO_.addError( ContactCustDetailFncVO.C_ERROR_DUPLICATE_PK );
      }
    }*/
  }

  /**
   * Realiza as validações - Update
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    ContactCustDetailFncVO contactCustDetailFncVO = ( ContactCustDetailFncVO ) fncVO_;

    if ( contactCustDetailFncVO.getTplContactCustEntity().getData().getCustNbr() == null
         || contactCustDetailFncVO.getTplContactCustEntity().getData().getCustNbr().intValue() == 0
         || contactCustDetailFncVO.getTplContactCustEntity().getData().getCtcNbr() == null
         || contactCustDetailFncVO.getTplContactCustEntity().getData().getCtcNbr().intValue() == 0 )
    {
      fncVO_.addError( ContactCustDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_CUST_NBR_DESCRIPTION );
      fncVO_.addError( ContactCustDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_CTC_NBR_DESCRIPTION );
    }

    //  Validar se existe um registro com este codigo na "Current",
    // caso os campos obrigatórios estejam presentes
    if ( !fncVO_.hasErrors() )
    {
      if ( !this.existsActive( contactCustDetailFncVO ) )
      {
        fncVO_.addError( ContactCustDetailFncVO.C_ERROR_PK_NOT_FOUND );
      }

    }
  }

  /**
   * Realiza as validações - Delete
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
    //
  }
}