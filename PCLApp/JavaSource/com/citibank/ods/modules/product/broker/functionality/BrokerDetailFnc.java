package com.citibank.ods.modules.product.broker.functionality;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplBrokerEntity;
import com.citibank.ods.entity.pl.TplBrokerEntity;
import com.citibank.ods.entity.pl.TplBrokerMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplBrokerEntityVO;
import com.citibank.ods.modules.product.broker.form.BrokerDetailForm;
import com.citibank.ods.modules.product.broker.functionality.valueobject.BrokerDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplBrokerDAO;
import com.citibank.ods.persistence.pl.dao.TplBrokerDAO;
import com.citibank.ods.persistence.pl.dao.TplBrokerMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author Hamilton Matos
 *  
 */
public class BrokerDetailFnc extends BaseBrokerDetailFnc implements
    ODSDetailFnc
{

  /**
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#insert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void insert( BaseFncVO fncVO_ )
  {
    BaseTplBrokerEntity baseTplBrokerEntity;
    TplBrokerMovEntity brokerMovEntity;

    this.validateInsert( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      BrokerDetailFncVO detailFncVO = ( BrokerDetailFncVO ) fncVO_;
      TplBrokerEntity tplBrokerEntity = ( TplBrokerEntity ) detailFncVO.getBaseTplBrokerEntity();
      tplBrokerEntity.getData().setLastUpdDate( new Date() );
      tplBrokerEntity.getData().setLastUpdUserId(
                                                  fncVO_.getLoggedUser() != null
                                                                                ? fncVO_.getLoggedUser().getUserID()
                                                                                : "" );
      TplBrokerMovEntity tplBrokerMovEntity = new TplBrokerMovEntity(
                                                                      tplBrokerEntity,
                                                                      TplBrokerMovEntity.C_OPERN_CODE_INSERT );

      TplBrokerMovDAO tplBrokerMovDAO = ODSDAOFactory.getInstance().getTplBrokerMovDAO();
      tplBrokerMovDAO.insert( tplBrokerMovEntity );

    }

  }

  /**
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void update( BaseFncVO fncVO_ )
  {

    this.validateUpdate( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      BrokerDetailFncVO detailFncVO = ( BrokerDetailFncVO ) fncVO_;
      TplBrokerEntity tplBrokerEntity = ( TplBrokerEntity ) detailFncVO.getBaseTplBrokerEntity();
      tplBrokerEntity.getData().setLastUpdDate( new Date() );
      tplBrokerEntity.getData().setLastUpdUserId(
                                                  fncVO_.getLoggedUser() != null
                                                                                ? fncVO_.getLoggedUser().getUserID()
                                                                                : "" );
      TplBrokerMovEntity tplBrokerMovEntity = new TplBrokerMovEntity(
                                                                      tplBrokerEntity,
                                                                      TplBrokerMovEntity.C_OPERN_CODE_UPDATE );

      TplBrokerMovDAO tplBrokerMovDAO = ODSDAOFactory.getInstance().getTplBrokerMovDAO();
      tplBrokerMovDAO.insert( tplBrokerMovEntity );

    }

  }

  /**
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#delete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void delete( BaseFncVO fncVO_ )
  {

    BrokerDetailFncVO detailFncVO = ( BrokerDetailFncVO ) fncVO_;

    validateDelete( detailFncVO );

    if ( !detailFncVO.hasErrors() )
    {
      TplBrokerDAO tplBrokerDAO = ODSDAOFactory.getInstance().getTplBrokerDAO();
      TplBrokerEntity tplBrokerEntity = ( TplBrokerEntity ) tplBrokerDAO.find( detailFncVO.getBaseTplBrokerEntity() );

      tplBrokerEntity.getData().setLastUpdDate( new Date() );
      tplBrokerEntity.getData().setLastUpdUserId(
                                                  fncVO_.getLoggedUser() != null
                                                                                ? fncVO_.getLoggedUser().getUserID()
                                                                                : "" );

      TplBrokerMovEntity tplBrokerMovEntity = new TplBrokerMovEntity(
                                                                      tplBrokerEntity,
                                                                      TplBrokerMovEntity.C_OPERN_CODE_DELETE );

      TplBrokerMovDAO tplBrokerMovDAO = ODSDAOFactory.getInstance().getTplBrokerMovDAO();

      tplBrokerMovDAO.insert( tplBrokerMovEntity );

    }

  }

  /**
   * Realiza as validações de inserção
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
    BrokerDetailFncVO brokerDetailFncVO = ( BrokerDetailFncVO ) fncVO_;
    TplBrokerEntityVO tplBrokerEntityVO = ( TplBrokerEntityVO ) brokerDetailFncVO.getBaseTplBrokerEntity().getData();

    //Validar Campos Obrigatórios
    if ( tplBrokerEntityVO.getBkrCnpjNbr() == null
         || tplBrokerEntityVO.getBkrCnpjNbr().equals( "" ) )
    {
      fncVO_.addError( BrokerDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_BKR_CNPJ_NBR );
    }

    if ( tplBrokerEntityVO.getBkrNameText() == null
         || tplBrokerEntityVO.getBkrNameText().equals( "" ) )
    {
      fncVO_.addError( BrokerDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_BKR_NAME_TEXT );
    }

    if ( tplBrokerEntityVO.getBkrApprvStatCode() != null
         && !tplBrokerEntityVO.getBkrApprvStatCode().equals(
                                                             C_DISPLAY_BKR_APPRV_STAT_CODE_CONSTRAINT_1 )
         && !tplBrokerEntityVO.getBkrApprvStatCode().equals(
                                                             C_DISPLAY_BKR_APPRV_STAT_CODE_CONSTRAINT_2 )
         && !tplBrokerEntityVO.getBkrApprvStatCode().equals(
                                                             C_DISPLAY_BKR_APPRV_STAT_CODE_CONSTRAINT_3 ) )
    {
      fncVO_.addError(
                       BrokerDetailFncVO.C_INCORRECT_BROKER_BKR_APPRV_STAT_CODE,
                       C_DISPLAY_BKR_APPRV_STAT_CODE );
    }

    if ( !fncVO_.hasErrors() )
    {
      //Validar se já existe um registro com este codigo na "Current",
      if ( this.existsActive( brokerDetailFncVO ) )
      {
        fncVO_.addError( BrokerDetailFncVO.C_ERROR_DUPLICATE_PK );
      }

      //Validar se já existe movimento com este codigo
      if ( this.existsInMovement( brokerDetailFncVO ) )
      {
        fncVO_.addError( BrokerDetailFncVO.C_ERROR_IN_MOVEMENT );
      }
    }

  }

  /**
   * Realiza as validações de alteração
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    BrokerDetailFncVO brokerDetailFncVO = ( BrokerDetailFncVO ) fncVO_;
    TplBrokerEntityVO tplBrokerEntityVO = ( TplBrokerEntityVO ) brokerDetailFncVO.getBaseTplBrokerEntity().getData();

    // Valida Campos Obrigatórios
    if ( tplBrokerEntityVO.getBkrCnpjNbr() == null
         || tplBrokerEntityVO.getBkrCnpjNbr().equals( "" ) )
    {
      fncVO_.addError( BrokerDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_BKR_CNPJ_NBR );
    }

    if ( tplBrokerEntityVO.getBkrNameText() == null
         || tplBrokerEntityVO.getBkrNameText().equals( "" ) )
    {
      fncVO_.addError( BrokerDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_BKR_NAME_TEXT );
    }

    if ( tplBrokerEntityVO.getBkrApprvStatCode() != null
         && !tplBrokerEntityVO.getBkrApprvStatCode().equals(
                                                             C_DISPLAY_BKR_APPRV_STAT_CODE_CONSTRAINT_1 )
         && !tplBrokerEntityVO.getBkrApprvStatCode().equals(
                                                             C_DISPLAY_BKR_APPRV_STAT_CODE_CONSTRAINT_2 )
         && !tplBrokerEntityVO.getBkrApprvStatCode().equals(
                                                             C_DISPLAY_BKR_APPRV_STAT_CODE_CONSTRAINT_3 ) )
    {
      fncVO_.addError(
                       BrokerDetailFncVO.C_INCORRECT_BROKER_BKR_APPRV_STAT_CODE,
                       C_DISPLAY_BKR_APPRV_STAT_CODE );
    }

    if ( !fncVO_.hasErrors() )
    {
      //Validar se existe um registro com este codigo na "Current",
      if ( !this.existsActive( brokerDetailFncVO ) )
      {
        fncVO_.addError( BrokerDetailFncVO.C_ERROR_PK_NOT_FOUND );
      }

      //Validar se já existe movimento com este código
      if ( this.existsInMovement( brokerDetailFncVO ) )
      {
        fncVO_.addError( BrokerDetailFncVO.C_ERROR_IN_MOVEMENT );
      }

    }

  }

  /**
   * Realiza as validações de exclusão
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
    BrokerDetailFncVO brokerDetailFncVO = ( BrokerDetailFncVO ) fncVO_;
    if ( !fncVO_.hasErrors() )
    {
      //Valida se existe um registro com este codigo na "Current",
      if ( !this.existsActive( brokerDetailFncVO ) )
      {
        fncVO_.addError( BrokerDetailFncVO.C_ERROR_PK_NOT_FOUND );
      }

      // Valida se já existe movimento com este código
      if ( this.existsInMovement( brokerDetailFncVO ) )
      {
        fncVO_.addError( BrokerDetailFncVO.C_ERROR_IN_MOVEMENT );
      }

    }
  }

  /**
   * Carregamento inicial - detalhe
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    super.load( ( BrokerDetailFncVO ) fncVO_ );
  }

  /**
   * Carregamento inicial - inserção
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForInsert( BaseFncVO fncVO_ )
  {
    BrokerDetailFncVO detailFncVO = ( BrokerDetailFncVO ) fncVO_;
    TplBrokerEntityVO tplBrokerEntityVO = ( TplBrokerEntityVO ) detailFncVO.getBaseTplBrokerEntity().getData();

    tplBrokerEntityVO.setBkrCnpjNbr( null );
    tplBrokerEntityVO.setBkrNameText( null );
    tplBrokerEntityVO.setBkrAddrText( null );
    tplBrokerEntityVO.setBkrReqDate( null );
    tplBrokerEntityVO.setBkrApprvStatCode( null );
    tplBrokerEntityVO.setBkrApprvDate( null );
    tplBrokerEntityVO.setBkrRnwDate( null );
    tplBrokerEntityVO.setBkrReqCrLimDlrAmt( null );
    tplBrokerEntityVO.setBkrReqCrLimLcyAmt( null );
    tplBrokerEntityVO.setBkrApprvCrLimLcyAmt( null );
    tplBrokerEntityVO.setBkrApprvCrLimDlrAmt( null );
    tplBrokerEntityVO.setBkrSuplServText( null );
    tplBrokerEntityVO.setBkrBmfMktCode( null );
    tplBrokerEntityVO.setBkrBovespaMktCode( null );
    tplBrokerEntityVO.setBkrRbtBmfRate( null );
    tplBrokerEntityVO.setBkrRbtBovespaRate( null );
    tplBrokerEntityVO.setBkrCommentText( null );
    tplBrokerEntityVO.setBkrAuthProcSitText( null );
  }

  /**
   * Carregamento inicial - alteração
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    if ( this.existsInMovement( ( BrokerDetailFncVO ) fncVO_ ) )
    {
      fncVO_.addError( BrokerDetailFncVO.C_ERROR_IN_MOVEMENT );
    }
    else
    {
      super.load( ( BrokerDetailFncVO ) fncVO_ );

    }

  }

  /**
   * Carregamento inicial - exclusão
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForDelete( BaseFncVO fncVO_ )
  {
    if ( this.existsInMovement( ( BrokerDetailFncVO ) fncVO_ ) )
    {
      fncVO_.addError( BrokerDetailFncVO.C_ERROR_IN_MOVEMENT );
    }
    else
    {
      super.load( ( BrokerDetailFncVO ) fncVO_ );

    }
  }

  /**
   * Verifica se existe um registro na tabela de movimento com os critérios
   * passados
   */
  private boolean existsInMovement( BrokerDetailFncVO brokerDetailFncVO_ )
  {

    TplBrokerMovDAO tplBrokerMovDAO = ODSDAOFactory.getInstance().getTplBrokerMovDAO();

    TplBrokerMovEntity tplBrokerMovEntity = new TplBrokerMovEntity();
    tplBrokerMovEntity.getData().setBkrCnpjNbr(
                                                brokerDetailFncVO_.getBaseTplBrokerEntity().getData().getBkrCnpjNbr() );
    return tplBrokerMovDAO.exists( tplBrokerMovEntity );

  }

  /**
   * Verifica se já existe um registro na tabela de "Current" com o código
   * passado e o seu status é "Ativo"
   */
  private boolean existsActive( BrokerDetailFncVO brokerDetailFncVO_ )
  {

    TplBrokerDAO tplBrokerDAO = ODSDAOFactory.getInstance().getTplBrokerDAO();
    return tplBrokerDAO.existsActive( ( TplBrokerEntity ) brokerDetailFncVO_.getBaseTplBrokerEntity() );

  }

  /**
   * Retorna uma instancia do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new BrokerDetailFncVO();
  }

  /**
   * Seta na Form os campos específicos de current
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );
    BrokerDetailFncVO detailFncVO = ( BrokerDetailFncVO ) fncVO_;
    BrokerDetailForm detailForm = ( BrokerDetailForm ) form_;
    TplBrokerEntityVO tplBrokerEntityVO = ( TplBrokerEntityVO ) detailFncVO.getBaseTplBrokerEntity().getData();

    SimpleDateFormat dateFormat = new SimpleDateFormat(
                                                        Globals.FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );

    detailForm.setLastAuthUserId( tplBrokerEntityVO.getLastAuthUserId() );

    if ( tplBrokerEntityVO.getLastAuthDate() != null
         && !tplBrokerEntityVO.getLastAuthDate().equals( "" ) )
    {
      detailForm.setLastAuthDate( dateFormat.format( tplBrokerEntityVO.getLastAuthDate() ) );
    }
    else
    {
      detailForm.setLastAuthDate( null );
    }

    String recStatCode = ( ( TplBrokerEntityVO ) ( detailFncVO.getBaseTplBrokerEntity().getData() ) ).getRecStatCode();

    if ( recStatCode != null && !"".equals( recStatCode ) )
    {
      String strRecStatCode = ODSConstraintDecoder.decodeRecStatus( recStatCode );
      detailForm.setRecStatCode( strRecStatCode );
    }

  }

  /**
   * Converte uma data para o formato de apresentação.
   * 
   * @param date_ - A data a ser convertida.
   * @return String - A data no formato de apresentação.
   */
  protected String formatDateTime( Date date_ )
  {
    DateFormat dateFormat = new SimpleDateFormat(
                                                  Globals.FuncionalityFormatKeys.C_FORMAT_PRESENTATION );
    return dateFormat.format( date_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.broker.functionality.BaseBrokerDetailFnc#getDAO()
   */
  protected BaseTplBrokerDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplBrokerDAO();
  }

}