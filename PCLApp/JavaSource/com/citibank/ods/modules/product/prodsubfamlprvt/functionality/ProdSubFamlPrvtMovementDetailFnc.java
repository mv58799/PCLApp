/*
 * Created on Mar 18, 2007
 *
 */
package com.citibank.ods.modules.product.prodsubfamlprvt.functionality;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplProdSubFamlPrvtEntity;
import com.citibank.ods.entity.pl.TplProdSubFamlPrvtHistEntity;
import com.citibank.ods.entity.pl.TplProdSubFamlPrvtMovEntity;
import com.citibank.ods.entity.pl.TplProductFamilyPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdSubFamlPrvtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdSubFamlPrvtMovEntityVO;
import com.citibank.ods.modules.product.prodsubfamlprvt.form.ProdSubFamlPrvtMovementDetailForm;
import com.citibank.ods.modules.product.prodsubfamlprvt.functionality.valueobject.ProdSubFamlPrvtMovementDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdSubFamlPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProdSubFamlPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProdSubFamlPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.TplProdSubFamlPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.TplProductFamilyPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class ProdSubFamlPrvtMovementDetailFnc extends
    BaseProdSubFamlPrvtDetailFnc implements ODSMovementDetailFnc
{

  private static final String C_APPROVED = "Aprovado";

  /**
   * Carregamento inicial - Retorna instancia do FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new ProdSubFamlPrvtMovementDetailFncVO();
  }

  /**
   * Altera os dados de uma sub-família com pendência de aprovação
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void update( BaseFncVO fncVO_ )
  {
    validateUpdate( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      ProdSubFamlPrvtMovementDetailFncVO prodSubFamlPrvtMovementDetailFncVO = ( ProdSubFamlPrvtMovementDetailFncVO ) fncVO_;
      TplProdSubFamlPrvtMovEntityVO tplProdSubFamlPrvtMovEntityVO = ( TplProdSubFamlPrvtMovEntityVO ) prodSubFamlPrvtMovementDetailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData();

      tplProdSubFamlPrvtMovEntityVO.setLastUpdUserId( fncVO_.getLoggedUser().getUserID() );
      tplProdSubFamlPrvtMovEntityVO.setLastUpdDate( new Date() );

      TplProdSubFamlPrvtMovDAO prodSubFamlPrvtMovDAO = ODSDAOFactory.getInstance().getTplProductSubFamilyPrvtMovDAO();
      prodSubFamlPrvtMovDAO.update( ( TplProdSubFamlPrvtMovEntity ) prodSubFamlPrvtMovementDetailFncVO.getBaseTplProductSubFamilyPrvtEntity() );
    }

  }

  /**
   * Aprova os dados de uma sub-família com pendecia de aprovação
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#approve(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void approve( BaseFncVO fncVO_ )
  {
    validateApprove( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      TplProdSubFamlPrvtMovDAO prodSubFamlPrvtMovDAO = ODSDAOFactory.getInstance().getTplProductSubFamilyPrvtMovDAO();

      // Recupera o registro que está sendo aprovado
      TplProdSubFamlPrvtMovEntity movDetailEntity = ( TplProdSubFamlPrvtMovEntity ) prodSubFamlPrvtMovDAO.find( ( ( ProdSubFamlPrvtMovementDetailFncVO ) fncVO_ ).getBaseTplProductSubFamilyPrvtEntity() );

      // Constroi um objeto entity de Current com os dados de movimento
      TplProdSubFamlPrvtEntity prodSubFamlPrvtEntity = new TplProdSubFamlPrvtEntity(
                                                                                     movDetailEntity,
                                                                                     new Date(),
                                                                                     fncVO_.getLoggedUser().getUserID(),
                                                                                     TplProdSubFamlPrvtEntity.C_REC_STAT_CODE_ACTIVE );

      // Recupera o opernCode de movimento para as operacoes
      String movOpernCode = ( ( TplProdSubFamlPrvtMovEntityVO ) movDetailEntity.getData() ).getOpernCode();

      // 1. Se a operacao for de delete, seta recStatCode para inativo
      if ( TplProdSubFamlPrvtEntity.C_OPERN_CODE_DELETE.equals( movOpernCode ) )
      {
        ( ( TplProdSubFamlPrvtEntityVO ) prodSubFamlPrvtEntity.getData() ).setRecStatCode( TplProdSubFamlPrvtEntity.C_REC_STAT_CODE_INACTIVE );
      }

      // 2. Se existe um registro na tabela de Current com o mesmo código,
      // copia para histórico e atualiza Current
      TplProdSubFamlPrvtDAO prodSubFamlPrvtDAO = ODSDAOFactory.getInstance().getTplProductSubFamilyPrvtDAO();

      if ( prodSubFamlPrvtDAO.exists( prodSubFamlPrvtEntity ) )
      {
        TplProdSubFamlPrvtEntity prodSubFamlPrvtEntityOld = ( TplProdSubFamlPrvtEntity ) prodSubFamlPrvtDAO.find( prodSubFamlPrvtEntity );

        // Insere histórico
        TplProdSubFamlPrvtHistDAO prodSubFamlPrvtHistDAO = ODSDAOFactory.getInstance().getTplProductSubFamilyPrvtHistDAO();
        TplProdSubFamlPrvtHistEntity tplProdSubFamlPrvtHistEntity = new TplProdSubFamlPrvtHistEntity(
                                                                                                      prodSubFamlPrvtEntityOld,
                                                                                                      new Date() );
        prodSubFamlPrvtHistDAO.insert( tplProdSubFamlPrvtHistEntity );

        // atualiza Current
        prodSubFamlPrvtDAO.update( prodSubFamlPrvtEntity );
      }
      else
      {
        prodSubFamlPrvtDAO.insert( prodSubFamlPrvtEntity );
      }

      // 3. Remove o movimento
      prodSubFamlPrvtMovDAO.delete( movDetailEntity );
    }
  }

  /**
   * Reprova os dados de sub-família com pendencia de aprovação
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#reprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void reprove( BaseFncVO fncVO_ )
  {
    //realiza validação
    validateReprove( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      TplProdSubFamlPrvtMovDAO prodSubFamlPrvtMovDAO = ODSDAOFactory.getInstance().getTplProductSubFamilyPrvtMovDAO();
      //1 - Obtem o registro selecionado
      TplProdSubFamlPrvtMovEntity movDetailEntity = ( TplProdSubFamlPrvtMovEntity ) prodSubFamlPrvtMovDAO.find( ( ( ProdSubFamlPrvtMovementDetailFncVO ) fncVO_ ).getBaseTplProductSubFamilyPrvtEntity() );

      // 2 - Remove movimento
      prodSubFamlPrvtMovDAO.delete( movDetailEntity );
    }

  }

  /**
   * Validar a alteração 1. Usuário != nulo 2. Usuário = lastUpdUpserId (Usuário
   * que está alterando deve ser igual ao usuário que inseriu o registro p/
   * aprovação)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {

    ProdSubFamlPrvtMovementDetailFncVO prodSubFamlPrvtMovementDetailFncVO = ( ProdSubFamlPrvtMovementDetailFncVO ) fncVO_;
    TplProdSubFamlPrvtMovEntityVO prodSubFamlPrvtMovEntityVO = ( TplProdSubFamlPrvtMovEntityVO ) prodSubFamlPrvtMovementDetailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData();

    //Testar preenchimento dos campos
    if ( prodSubFamlPrvtMovEntityVO.getProdSubFamlCode() == null
         || prodSubFamlPrvtMovEntityVO.getProdSubFamlCode().intValue() == 0 )
    {
      fncVO_.addError(
                       ProdSubFamlPrvtMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProdSubFamlPrvtMovementDetailFncVO.C_PROD_SUB_FAML_CODE_DESCRIPTION );
    }

    if ( prodSubFamlPrvtMovEntityVO.getProdSubFamlName() == null
         || prodSubFamlPrvtMovEntityVO.getProdSubFamlName().equals( "" ) )
    {
      fncVO_.addError(
                       ProdSubFamlPrvtMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProdSubFamlPrvtMovementDetailFncVO.C_PROD_SUB_FAML_NAME_DESCRIPTION );
    }

    if ( prodSubFamlPrvtMovEntityVO.getProdFamlCode() == null
         || prodSubFamlPrvtMovEntityVO.getProdFamlCode().intValue() == 0 )
    {
      fncVO_.addError(
                       ProdSubFamlPrvtMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProdSubFamlPrvtMovementDetailFncVO.C_PROD_FAML_CODE_DESCRIPTION );
    }

    //testar usuário
    if ( prodSubFamlPrvtMovementDetailFncVO.getLoggedUser() == null
         || !prodSubFamlPrvtMovementDetailFncVO.getLoggedUser().getUserID().equals(
                                                                                    prodSubFamlPrvtMovEntityVO.getLastUpdUserId() ) )
    {

      prodSubFamlPrvtMovementDetailFncVO.addError( ProdSubFamlPrvtMovementDetailFncVO.C_ERROR_APPROVAL_USER_NOT_AUTHORIZED );

    }

    //Se opernCode = Delete, adicionar erros
    if ( !fncVO_.hasErrors() )
    {
      TplProdSubFamlPrvtMovDAO tplProdSubFamlPrvtMovDAO = ODSDAOFactory.getInstance().getTplProductSubFamilyPrvtMovDAO();
      TplProdSubFamlPrvtMovEntity tplProdSubFamlPrvtMovEntity = ( TplProdSubFamlPrvtMovEntity ) tplProdSubFamlPrvtMovDAO.find( prodSubFamlPrvtMovementDetailFncVO.getBaseTplProductSubFamilyPrvtEntity() );

      String opernCode = ( ( TplProdSubFamlPrvtMovEntityVO ) tplProdSubFamlPrvtMovEntity.getData() ).getOpernCode();

      if ( TplProdSubFamlPrvtMovEntity.C_OPERN_CODE_DELETE.equals( opernCode ) )
      {
        prodSubFamlPrvtMovementDetailFncVO.addError( ProdSubFamlPrvtMovementDetailFncVO.C_ERROR_CANNOT_UPD_DELETE_IN_MOVEMENT );
      }
    }

  }

  /**
   * Validar a aprovação 1. Usuário != nulo 2. Usuário != lastUpdUpserId
   * (Usuário que está aprovando deve ser diferente do usuário que inseriu o
   * registro p/ aprovação)
   */
  public void validateApprove( BaseFncVO fncVO_ )
  {

    ProdSubFamlPrvtMovementDetailFncVO prodSubFamlPrvtMovementDetailFncVO = ( ProdSubFamlPrvtMovementDetailFncVO ) fncVO_;
    TplProdSubFamlPrvtMovEntityVO prodSubFamlPrvtMovEntityVO = ( TplProdSubFamlPrvtMovEntityVO ) prodSubFamlPrvtMovementDetailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData();

    if ( !existsProductFamilyPrvtActive( prodSubFamlPrvtMovEntityVO ) )
    {
      fncVO_.addError(
                       ProdSubFamlPrvtMovementDetailFncVO.C_INVALID_FK,
                       C_APPROVED,
                       ProdSubFamlPrvtMovementDetailFncVO.C_PROD_FAML_CODE_DESCRIPTION );
    }

    //testar usuário
    if ( prodSubFamlPrvtMovementDetailFncVO.getLoggedUser() == null
         || prodSubFamlPrvtMovementDetailFncVO.getLoggedUser().getUserID().equals(
                                                                                   prodSubFamlPrvtMovEntityVO.getLastUpdUserId() ) )
    {

      prodSubFamlPrvtMovementDetailFncVO.addError( ProdSubFamlPrvtMovementDetailFncVO.C_ERROR_APPROVAL_USER_NOT_AUTHORIZED );
    }
  }

  /**
   * Validar Reprovação
   */
  public void validateReprove( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Carregamento inicial - Alteração
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    super.loadDomains( ( ProdSubFamlPrvtMovementDetailFncVO ) fncVO_ );
    super.loadProductSubFamily( ( ProdSubFamlPrvtMovementDetailFncVO ) fncVO_ );
  }

  /**
   * Carregamento inicial - Aprovação
   */
  public void loadForApprove( BaseFncVO fncVO_ )
  {
    super.loadDomains( ( ProdSubFamlPrvtMovementDetailFncVO ) fncVO_ );
    super.loadProductSubFamily( ( ProdSubFamlPrvtMovementDetailFncVO ) fncVO_ );
  }

  /**
   * Inserindo as informações no Form a partir do FncVO - Campos específicos
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {

    super.updateFormFromFncVO( form_, fncVO_ );

    ProdSubFamlPrvtMovementDetailFncVO prodSubFamlPrvtMovementDetailFncVO = ( ProdSubFamlPrvtMovementDetailFncVO ) fncVO_;
    TplProdSubFamlPrvtMovEntityVO prodSubFamlPrvtMovEntityVO = ( TplProdSubFamlPrvtMovEntityVO ) prodSubFamlPrvtMovementDetailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData();

    String opernCode = prodSubFamlPrvtMovEntityVO.getOpernCode();
    ProdSubFamlPrvtMovementDetailForm prodSubFamlPrvtMovementDetailForm = ( ProdSubFamlPrvtMovementDetailForm ) form_;

    if ( opernCode != null && !"".equals( opernCode ) )
    {
      prodSubFamlPrvtMovementDetailForm.setOpernCode( ODSConstraintDecoder.decodeOpern( opernCode ) );
    }
  }

  /**
   * Carregamento inicial - Detalhe
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Retorna o DAO
   */
  protected BaseTplProdSubFamlPrvtDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplProductSubFamilyPrvtMovDAO();
  }

  private boolean existsProductFamilyPrvtActive(
                                                TplProdSubFamlPrvtMovEntityVO tplProdSubFamlPrvtMovEntityVO_ )
  {

    TplProductFamilyPrvtDAO productFamilyPrvtDAO = ODSDAOFactory.getInstance().getTplProductFamilyPrvtDAO();
    TplProductFamilyPrvtEntity productFamilyPrvtEntity = new TplProductFamilyPrvtEntity();
    productFamilyPrvtEntity.getData().setProdFamlCode(
                                                       tplProdSubFamlPrvtMovEntityVO_.getProdFamlCode() );

    return productFamilyPrvtDAO.existsActive( productFamilyPrvtEntity );
  }

}