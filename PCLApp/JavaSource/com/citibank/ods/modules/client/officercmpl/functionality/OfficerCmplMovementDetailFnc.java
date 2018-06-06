package com.citibank.ods.modules.client.officercmpl.functionality;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplOfficerCmplEntity;
import com.citibank.ods.entity.pl.TplOfficerCmplHistoryEntity;
import com.citibank.ods.entity.pl.TplOfficerCmplMovementEntity;
import com.citibank.ods.entity.pl.valueobject.TplOfficerCmplEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplOfficerCmplMovementEntityVO;
import com.citibank.ods.modules.client.officercmpl.form.OfficerCmplMovementDetailForm;
import com.citibank.ods.modules.client.officercmpl.functionality.valueobject.OfficerCmplDetailFncVO;
import com.citibank.ods.modules.client.officercmpl.functionality.valueobject.OfficerCmplMovementDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplOfficerCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplOfficerCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplOfficerCmplHistDAO;
import com.citibank.ods.persistence.pl.dao.TplOfficerCmplMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
//
//�2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.officercmpl.functionality;
 * @version 1.0
 * @author fabio.luppi.gil,Mar 5, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * Classe Fnc de detalhe dos dados complementares de Officer com pend�ncia de
 * Aprova��o
 * 
 * </PRE>
 */

public class OfficerCmplMovementDetailFnc extends BaseOfficerCmplDetailFnc
    implements ODSMovementDetailFnc
{

  /**
   * Realiza os carregamentos iniciais da tela de detalhe
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    super.loadOfficerTypeDomain( ( OfficerCmplMovementDetailFncVO ) fncVO_ );
    super.loadOfficerCmpl( ( OfficerCmplMovementDetailFncVO ) fncVO_ );
    super.loadOfficerNameText( ( OfficerCmplMovementDetailFncVO ) fncVO_ );
  }

  /**
   * Realiza as valida��es da aprova��o
   */
  public void validateApprove( BaseFncVO fncVO_ )
  {
    OfficerCmplMovementDetailFncVO officerCmplMovementDetailFncVO = ( OfficerCmplMovementDetailFncVO ) fncVO_;
    TplOfficerCmplMovementEntityVO officerCmplMovementEntityVO = ( TplOfficerCmplMovementEntityVO ) officerCmplMovementDetailFncVO.getTplOfficerCmplEntity().getData();

    super.loadOfficerCmpl( officerCmplMovementDetailFncVO );

    // testar usu�rio
    if ( officerCmplMovementDetailFncVO.getLoggedUser() == null
         || officerCmplMovementDetailFncVO.getLoggedUser().getUserID().equals(
                                                                               officerCmplMovementEntityVO.getLastUpdUserId() ) )
    {
      officerCmplMovementDetailFncVO.addError( OfficerCmplMovementDetailFncVO.C_ERROR_APPROVAL_USER_NOT_AUTHORIZED );
    }

  }

  /**
   * Realiza as valida��es de reprova��o. N�o se aplica � esta transa��o
   */
  public void validateReprove( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Realiza as valida��es de atualiza��o
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    OfficerCmplMovementDetailFncVO officerCmplMovementDetailFncVO = ( OfficerCmplMovementDetailFncVO ) fncVO_;
    TplOfficerCmplMovementEntityVO officerCmplMovementEntityVO = ( TplOfficerCmplMovementEntityVO ) officerCmplMovementDetailFncVO.getTplOfficerCmplEntity().getData();

    if ( officerCmplMovementEntityVO.getOffcrTypeCode() == null
         || "".equals( officerCmplMovementEntityVO.getOffcrTypeCode() ) )
    {
      fncVO_.addError( OfficerCmplDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       OfficerCmplDetailFncVO.C_OFFCR_TYPE_CODE_DESCRIPTION );
    }

    // caso os campos obrigat�rios estejam presentes
    if ( !fncVO_.hasErrors() )
    {
      //  testar usu�rio
      if ( !officerCmplMovementDetailFncVO.getLoggedUser().getUserID().equals(
                                                                               officerCmplMovementEntityVO.getLastUpdUserId() ) )
      {
        officerCmplMovementDetailFncVO.addError( OfficerCmplMovementDetailFncVO.C_ERROR_APPROVAL_UPDATE_USER_NOT_AUTHORIZED );
      }

      // Se opernCode = Delete, adicionar erros
      if ( !fncVO_.hasErrors() )
      {
        TplOfficerCmplMovDAO tplOfficerCmplMovDAO = ODSDAOFactory.getInstance().getTplOfficerCmplMovDAO();
        TplOfficerCmplMovementEntity tplOfficerCmplMovEntity = ( TplOfficerCmplMovementEntity ) tplOfficerCmplMovDAO.find( officerCmplMovementDetailFncVO.getTplOfficerCmplEntity() );

        String opernCode = ( ( TplOfficerCmplMovementEntityVO ) tplOfficerCmplMovEntity.getData() ).getOpernCode();

        if ( TplOfficerCmplMovementEntity.C_OPERN_CODE_DELETE.equals( opernCode ) )
        {
          officerCmplMovementDetailFncVO.addError( OfficerCmplMovementDetailFncVO.C_ERROR_CANNOT_UPD_DELETE_IN_MOVEMENT );
        }
      }
    }
  }

  /**
   * Recupera inst�ncia do DAO
   */
  protected BaseTplOfficerCmplDAO getDAO()
  {
    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
    TplOfficerCmplMovDAO tplOfficerCmplMovDAO = odsDAOFactory.getTplOfficerCmplMovDAO();
    return tplOfficerCmplMovDAO;
  }

  /**
   * Recupera uma inst�ncia de um FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new OfficerCmplMovementDetailFncVO();
  }

  /**
   * Realiza a atualiza��o de um registro
   */
  public void update( BaseFncVO fncVO_ )
  {
    // realiza valida��o
    validateUpdate( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      OfficerCmplMovementDetailFncVO officerCmplMovementDetailFncVO = ( OfficerCmplMovementDetailFncVO ) fncVO_;
      //entity atual que ser� utilizada para alterar registro do movimento.
      TplOfficerCmplMovementEntity officerCmplMovementEntity = ( TplOfficerCmplMovementEntity ) officerCmplMovementDetailFncVO.getTplOfficerCmplEntity();

      ODSDAOFactory odsDaoFactory = ODSDAOFactory.getInstance();
      TplOfficerCmplMovDAO tplOfficerCmplMovDAO = odsDaoFactory.getTplOfficerCmplMovDAO();

      officerCmplMovementEntity.getData().setLastUpdDate( new Date() );
      officerCmplMovementEntity.getData().setLastUpdUserId(
                                                            officerCmplMovementDetailFncVO.getLoggedUser().getUserID() );

      tplOfficerCmplMovDAO.update( officerCmplMovementEntity );
    }
  }

  /**
   * Realiza as valida��es de aprova��o
   */
  public void approve( BaseFncVO fncVO_ )
  {
    //  realiza valida��o
    validateApprove( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      TplOfficerCmplMovDAO tplOfficerMovDAO = ODSDAOFactory.getInstance().getTplOfficerCmplMovDAO();
      // Recupera o registro que est� sendo aprovado
      TplOfficerCmplMovementEntity movDetailEntity = ( TplOfficerCmplMovementEntity ) tplOfficerMovDAO.find( ( ( OfficerCmplMovementDetailFncVO ) fncVO_ ).getTplOfficerCmplEntity() );

      // Constroi um objeto entity de Current com os dados de movimento
      TplOfficerCmplEntity tplOfficerCmplEntity = new TplOfficerCmplEntity(
                                                                            movDetailEntity,
                                                                            new Date(),
                                                                            fncVO_.getLoggedUser().getUserID(),
                                                                            TplOfficerCmplMovementEntity.C_REC_STAT_CODE_ACTIVE );

      // Recupera o opernCode de movimento para as operacoes
      String movOpernCode = ( ( TplOfficerCmplMovementEntityVO ) movDetailEntity.getData() ).getOpernCode();

      // 1. Se a operacao for de delete, seta recStatCode para inativo
      if ( TplOfficerCmplEntity.C_OPERN_CODE_DELETE.equals( movOpernCode ) )
      {
        ( ( TplOfficerCmplEntityVO ) tplOfficerCmplEntity.getData() ).setRecStatCode( TplOfficerCmplMovementEntity.C_REC_STAT_CODE_INACTIVE );
      }

      // 2. Se existe um registro na tabela de Current com o mesmo c�digo,
      // copia para hist�rico e atualiza Current
      TplOfficerCmplDAO tplOfficerCmplDAO = ODSDAOFactory.getInstance().getTplOfficerCmplDAO();

      if ( tplOfficerCmplDAO.exists( tplOfficerCmplEntity ) )
      {
        TplOfficerCmplEntity officerCmplEntity = ( TplOfficerCmplEntity ) tplOfficerCmplDAO.find( tplOfficerCmplEntity );

        // Insere hist�rico
        TplOfficerCmplHistDAO riskCatPrvtHistDAO = ODSDAOFactory.getInstance().getTplOfficerCmplHistDAO();
        TplOfficerCmplHistoryEntity tplOfficerCmplHistEntity = new TplOfficerCmplHistoryEntity(
                                                                                                officerCmplEntity,
                                                                                                new Date() );
        riskCatPrvtHistDAO.insert( tplOfficerCmplHistEntity );

        // atualiza Current
        tplOfficerCmplDAO.update( tplOfficerCmplEntity );
      }
      else
      {
        tplOfficerCmplDAO.insert( tplOfficerCmplEntity );
      }

      // 3. Remove o movimento
      tplOfficerMovDAO.delete( movDetailEntity );

    }
  }

  /**
   * Realiza a execu��o da reprova��o
   */
  public void reprove( BaseFncVO fncVO_ )
  {
    //  realiza valida��o
    validateReprove( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      TplOfficerCmplMovDAO tplOfficerCmplMovDAO = ODSDAOFactory.getInstance().getTplOfficerCmplMovDAO();
      //1 - Obtem o registro selecionado
      TplOfficerCmplMovementEntity tplOfficerCmplMovementEntity = ( TplOfficerCmplMovementEntity ) tplOfficerCmplMovDAO.find( ( ( OfficerCmplMovementDetailFncVO ) fncVO_ ).getTplOfficerCmplEntity() );

      // 2 - Remove movimento
      tplOfficerCmplMovDAO.delete( tplOfficerCmplMovementEntity );
    }
  }

  /**
   * Realiza o carregamento inicial para a atualiza��o
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    super.loadOfficerTypeDomain( ( OfficerCmplMovementDetailFncVO ) fncVO_ );
    super.loadOfficerCmpl( ( OfficerCmplMovementDetailFncVO ) fncVO_ );
    super.loadOfficerNameText( ( OfficerCmplMovementDetailFncVO ) fncVO_ );
  }

  /**
   * Realiza os carregamentos iniciais para a aprova��o
   */
  public void loadForApprove( BaseFncVO fncVO_ )
  {
    super.loadOfficerTypeDomain( ( OfficerCmplMovementDetailFncVO ) fncVO_ );
    super.loadOfficerCmpl( ( OfficerCmplMovementDetailFncVO ) fncVO_ );
    super.loadOfficerNameText( ( OfficerCmplMovementDetailFncVO ) fncVO_ );
  }

  /**
   * Atualiza os dados do Form com os dados vindos do FncVO
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );

    //  Acertando os tipos
    OfficerCmplMovementDetailFncVO officerCmplMovementFncVO = ( OfficerCmplMovementDetailFncVO ) fncVO_;
    OfficerCmplMovementDetailForm officerCmplMovementForm = ( OfficerCmplMovementDetailForm ) form_;
    TplOfficerCmplMovementEntityVO officerCmplMovementEntityVO = ( TplOfficerCmplMovementEntityVO ) officerCmplMovementFncVO.getTplOfficerCmplEntity().getData();

    String opernCode = "";
    if ( officerCmplMovementEntityVO.getOpernCode() != null
         && officerCmplMovementEntityVO.getOpernCode().length() > 0 )
    {
      opernCode = ODSConstraintDecoder.decodeOpern( officerCmplMovementEntityVO.getOpernCode() );
    }

    officerCmplMovementForm.setOpernCode( opernCode );
  }
}