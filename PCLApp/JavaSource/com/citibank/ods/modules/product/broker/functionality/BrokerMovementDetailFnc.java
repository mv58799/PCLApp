package com.citibank.ods.modules.product.broker.functionality;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplBrokerEntity;
import com.citibank.ods.entity.pl.TplBrokerHistEntity;
import com.citibank.ods.entity.pl.TplBrokerMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplBrokerEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplBrokerMovEntityVO;
import com.citibank.ods.modules.product.broker.form.BrokerMovementDetailForm;
import com.citibank.ods.modules.product.broker.functionality.valueobject.BrokerMovementDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplBrokerDAO;
import com.citibank.ods.persistence.pl.dao.TplBrokerDAO;
import com.citibank.ods.persistence.pl.dao.TplBrokerHistDAO;
import com.citibank.ods.persistence.pl.dao.TplBrokerMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author Hamilton Matos
 */
public class BrokerMovementDetailFnc extends BaseBrokerDetailFnc implements
    ODSMovementDetailFnc
{

  /**
   * Altera dos dados de uma Corretora com pendência de aprovação.
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void update( BaseFncVO fncVO_ )
  {

    validateUpdate( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      BrokerMovementDetailFncVO movDetailFncVO = ( BrokerMovementDetailFncVO ) fncVO_;
      TplBrokerMovEntityVO tplBrokerMovEntityVO = ( TplBrokerMovEntityVO ) movDetailFncVO.getBaseTplBrokerEntity().getData();

      //Preenche o usuário e a data de última alteração
      tplBrokerMovEntityVO.setLastUpdUserId( fncVO_.getLoggedUser().getUserID() );
      tplBrokerMovEntityVO.setLastUpdDate( new Date() );

      TplBrokerMovDAO tplBrokerMovDAO = ODSDAOFactory.getInstance().getTplBrokerMovDAO();
      tplBrokerMovDAO.update( ( TplBrokerMovEntity ) movDetailFncVO.getBaseTplBrokerEntity() );

    }
  }

  /**
   * Aprova os dados de uma Corretora com pendência de aprovação.
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#approve(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void approve( BaseFncVO fncVO_ )
  {

    validateApprove( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      BrokerMovementDetailFncVO brokerMovementDetailFncVO = ( BrokerMovementDetailFncVO ) fncVO_;
      TplBrokerMovDAO tplBrokerMovDAO = ODSDAOFactory.getInstance().getTplBrokerMovDAO();

      // Recupera registro a ser aprovado
      TplBrokerMovEntity tplBrokerMovEntity = ( TplBrokerMovEntity ) tplBrokerMovDAO.find( brokerMovementDetailFncVO.getBaseTplBrokerEntity() );

      //Cria um objeto de entity current com os dados de movimento
      TplBrokerEntity tplBrokerEntity = new TplBrokerEntity(
                                                             tplBrokerMovEntity,
                                                             new Date(),
                                                             fncVO_.getLoggedUser().getUserID(),
                                                             TplBrokerMovEntity.C_REC_STAT_CODE_ACTIVE );

      //Recupera o opernCode de movimento
      String movOpernCode = ( ( TplBrokerMovEntityVO ) tplBrokerMovEntity.getData() ).getOpernCode();

      //Intancia DAO da current
      TplBrokerDAO tplBrokerDAO = ODSDAOFactory.getInstance().getTplBrokerDAO();

      if ( tplBrokerDAO.exists( tplBrokerEntity ) )
      {
        //Recupera o registro atual da current
        TplBrokerEntity tplBrokerEntityOld = ( TplBrokerEntity ) tplBrokerDAO.find( tplBrokerEntity );

        //Cria entity history com os dados de current
        TplBrokerHistEntity tplBrokerHistEntity = new TplBrokerHistEntity(
                                                                           tplBrokerEntityOld,
                                                                           new Date() );

        //Insere historico
        TplBrokerHistDAO tplBrokerHistDAO = ODSDAOFactory.getInstance().getTplBrokerHistDAO();

        tplBrokerHistDAO.insert( tplBrokerHistEntity );

        //Se operação for delete seta o recStatusCode como Inativo
        if ( TplBrokerMovEntity.C_OPERN_CODE_DELETE.equals( movOpernCode ) )
        {
          ( ( TplBrokerEntityVO ) tplBrokerEntity.getData() ).setRecStatCode( TplBrokerEntity.C_REC_STAT_CODE_INACTIVE );
        }

        //Atualiza current
        tplBrokerDAO.update( tplBrokerEntity );

      }
      else
      {
        tplBrokerDAO.insert( tplBrokerEntity );
      }

      //Deleta registro da movimento

      tplBrokerMovDAO.delete( tplBrokerMovEntity );

    }
  }

  /**
   * Rejeita os dados de uma Corretora com pendência de aprovação.
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#reprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void reprove( BaseFncVO fncVO_ )
  {
    validateReprove( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      BrokerMovementDetailFncVO brokerMovementDetailFncVO = ( BrokerMovementDetailFncVO ) fncVO_;
      TplBrokerMovDAO tplBrokerMovDAO = ODSDAOFactory.getInstance().getTplBrokerMovDAO();

      //Recupera registro que esta sendo reprovado
      TplBrokerMovEntity tplBrokerMovEntity = ( TplBrokerMovEntity ) tplBrokerMovDAO.find( brokerMovementDetailFncVO.getBaseTplBrokerEntity() );

      // Deleta registro da movimento
      tplBrokerMovDAO.delete( tplBrokerMovEntity );

    }
  }

  /**
   * Realiza validação de alteração de uma Corretora com pendência de aprovação.
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    BrokerMovementDetailFncVO brokerMovementDetailFncVO = ( BrokerMovementDetailFncVO ) fncVO_;
    TplBrokerMovEntityVO tplBrokerMovEntityVO = ( TplBrokerMovEntityVO ) brokerMovementDetailFncVO.getBaseTplBrokerEntity().getData();

    // Valida campos obrigatórios
    if ( tplBrokerMovEntityVO.getBkrCnpjNbr() == null
         || tplBrokerMovEntityVO.getBkrCnpjNbr().equals( "" ) )
    {
      fncVO_.addError( BrokerMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_BKR_CNPJ_NBR );
    }

    if ( tplBrokerMovEntityVO.getBkrNameText() == null
         || tplBrokerMovEntityVO.getBkrNameText().equals( "" ) )
    {
      fncVO_.addError( BrokerMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_BKR_NAME_TEXT );
    }

    // Valida usuário
    if ( brokerMovementDetailFncVO.getLoggedUser() == null
         || !brokerMovementDetailFncVO.getLoggedUser().getUserID().equals(
                                                                           tplBrokerMovEntityVO.getLastUpdUserId() ) )
    {
      fncVO_.addError( BrokerMovementDetailFncVO.C_ERROR_APPROVAL_UPDATE_USER_NOT_AUTHORIZED );
    }

    // Valida operação, se for delete adiciona erro
    if ( !fncVO_.hasErrors() )
    {
      TplBrokerMovDAO tplBrokerMovDAO = ODSDAOFactory.getInstance().getTplBrokerMovDAO();
      TplBrokerMovEntity tplBrokerMovEntity = ( TplBrokerMovEntity ) tplBrokerMovDAO.find( brokerMovementDetailFncVO.getBaseTplBrokerEntity() );

      String opernCode = ( ( TplBrokerMovEntityVO ) tplBrokerMovEntity.getData() ).getOpernCode();

      if ( TplBrokerMovEntity.C_OPERN_CODE_DELETE.equals( opernCode ) )
      {
        brokerMovementDetailFncVO.addError( BrokerMovementDetailFncVO.C_ERROR_CANNOT_UPD_DELETE_IN_MOVEMENT );
      }
    }

  }

  /**
   * Realiza a validação de aprovação de uma Corretora com pendência de
   * aprovação.
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateApprove( BaseFncVO fncVO_ )
  {
    BrokerMovementDetailFncVO brokerMovementDetailFncVO = ( BrokerMovementDetailFncVO ) fncVO_;
    TplBrokerMovEntityVO tplBrokerMovEntityVO = ( TplBrokerMovEntityVO ) brokerMovementDetailFncVO.getBaseTplBrokerEntity().getData();

    // Valida usuário
    if ( brokerMovementDetailFncVO.getLoggedUser() == null
         || brokerMovementDetailFncVO.getLoggedUser().getUserID().equals(
                                                                          tplBrokerMovEntityVO.getLastUpdUserId() ) )
    {
      fncVO_.addError( BrokerMovementDetailFncVO.C_ERROR_APPROVAL_USER_NOT_AUTHORIZED );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    super.load( ( BrokerMovementDetailFncVO ) fncVO_ );
  }

  /**
   * Carregamento inicial - alteração.
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    super.load( ( BrokerMovementDetailFncVO ) fncVO_ );
  }

  /**
   * Carregamento inicial - aprovação.
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForApprove( BaseFncVO fncVO_ )
  {
    super.load( ( BrokerMovementDetailFncVO ) fncVO_ );
  }

  /**
   * Retorna instância do FncVO.
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new BrokerMovementDetailFncVO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateReprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateReprove( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Seta na Form os campos específicos de movimento.
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );
    BrokerMovementDetailFncVO detailfncVO = ( BrokerMovementDetailFncVO ) fncVO_;
    BrokerMovementDetailForm movementDetailForm = ( BrokerMovementDetailForm ) form_;
	//ate aqui ta certo
    String opernCode = ( ( TplBrokerMovEntityVO ) ( detailfncVO.getBaseTplBrokerEntity().getData() ) ).getOpernCode();
    if ( opernCode != null && !"".equals( opernCode ) )
    {
      String strOpernCode = ODSConstraintDecoder.decodeOpern( opernCode );
      movementDetailForm.setOpernCode( strOpernCode );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.broker.functionality.BaseBrokerDetailFnc#getDAO()
   */
  protected BaseTplBrokerDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplBrokerMovDAO();
  }
}