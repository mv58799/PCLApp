/*
 * Created on Mar 14, 2007
 *
 */
package com.citibank.ods.modules.product.prodriskcatprvt.functionality;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplProdRiskCatPrvtEntity;
import com.citibank.ods.entity.pl.TplProdRiskCatPrvtHistEntity;
import com.citibank.ods.entity.pl.TplProdRiskCatPrvtMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdRiskCatPrvtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdRiskCatPrvtMovEntityVO;
import com.citibank.ods.modules.product.prodriskcatprvt.form.ProdRiskCatPrvtMovementDetailForm;
import com.citibank.ods.modules.product.prodriskcatprvt.functionality.valueobject.ProdRiskCatPrvtMovementDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdRiskCatPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class ProdRiskCatPrvtMovementDetailFnc extends
    BaseProdRiskCatPrvtDetailFnc implements ODSMovementDetailFnc
{
  /**
   * Altera os dados de uma Categoria de Risco do Produto com Pendência de
   * Aprovação.
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void update( BaseFncVO fncVO_ )
  {
    validateUpdate( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      ProdRiskCatPrvtMovementDetailFncVO movDetailFncVO = ( ProdRiskCatPrvtMovementDetailFncVO ) fncVO_;

      movDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().setLastUpdUserID(
                                                                                   fncVO_.getLoggedUser().getUserID() );
      movDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().setLastUpdDate(
                                                                                 new Date() );

      TplProdRiskCatPrvtMovDAO riskCatPrvtMovDAO = ODSDAOFactory.getInstance().getTplProdRiskCatPrvtMovDAO();
      /** *** 20110321 ***
       * As funcionalidades que utilizavam a tabela PL.TPL_PROD_RISK_CAT_PRVT devem utilizar a tabela
       * PL.TPL_RISK_INVST_PROD_RDIP (apenas funcionalidades que utilizem consulta, as telas de 
       * inserção e alteração serão removidas)
       */       
      //riskCatPrvtMovDAO.update( ( TplProdRiskCatPrvtMovEntity ) movDetailFncVO.getBaseTplProdRiskCatPrvtEntity() );
    }
  }

  /**
   * Aprova os dados de uma Categoria de Risco do Produto com Pendência de
   * Aprovação.
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#approve(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void approve( BaseFncVO fncVO_ )
  {
    validateApprove( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      TplProdRiskCatPrvtMovDAO riskCatPrvtMovDAO = ODSDAOFactory.getInstance().getTplProdRiskCatPrvtMovDAO();
      // Recupera o registro que está sendo aprovado
      TplProdRiskCatPrvtMovEntity movDetailEntity = ( TplProdRiskCatPrvtMovEntity ) riskCatPrvtMovDAO.find( ( ( ProdRiskCatPrvtMovementDetailFncVO ) fncVO_ ).getBaseTplProdRiskCatPrvtEntity() );

      // Constroi um objeto entity de Current com os dados de movimento
      TplProdRiskCatPrvtEntity riskCatPrvtEntity = new TplProdRiskCatPrvtEntity(
                                                                                 movDetailEntity,
                                                                                 new Date(),
                                                                                 fncVO_.getLoggedUser().getUserID(),
                                                                                 TplProdRiskCatPrvtMovEntity.C_REC_STAT_CODE_ACTIVE );

      // Recupera o opernCode de movimento para as operacoes
      String movOpernCode = ( ( TplProdRiskCatPrvtMovEntityVO ) movDetailEntity.getData() ).getOpernCode();

      // 1. Se a operacao for de delete, seta recStatCode para inativo
      if ( TplProdRiskCatPrvtEntity.C_OPERN_CODE_DELETE.equals( movOpernCode ) )
      {
        ( ( TplProdRiskCatPrvtEntityVO ) riskCatPrvtEntity.getData() ).setRecStatCode( TplProdRiskCatPrvtMovEntity.C_REC_STAT_CODE_INACTIVE );
      }

      // 2. Se existe um registro na tabela de Current com o mesmo código,
      // copia para histórico e atualiza Current
      TplProdRiskCatPrvtDAO riskCatPrvtDAO = ODSDAOFactory.getInstance().getTplProdRiskCatPrvtDAO();

      if ( riskCatPrvtDAO.exists( riskCatPrvtEntity ) )
      {
        TplProdRiskCatPrvtEntity prodRiskCatPrvtEntityOld = ( TplProdRiskCatPrvtEntity ) riskCatPrvtDAO.find( riskCatPrvtEntity );
        // Insere histórico
        TplProdRiskCatPrvtHistDAO riskCatPrvtHistDAO = ODSDAOFactory.getInstance().getTplProdRiskCatPrvtHistDAO();
        TplProdRiskCatPrvtHistEntity tplProdRiskCatPrvtHistEntity = new TplProdRiskCatPrvtHistEntity(
                                                                                                      prodRiskCatPrvtEntityOld,
                                                                                                      new Date() );
        /** *** 20110321 ***
         * As funcionalidades que utilizavam a tabela PL.TPL_PROD_RISK_CAT_PRVT devem utilizar a tabela
         * PL.TPL_RISK_INVST_PROD_RDIP (apenas funcionalidades que utilizem consulta, as telas de 
         * inserção e alteração serão removidas)
         */         
        //riskCatPrvtHistDAO.insert( tplProdRiskCatPrvtHistEntity );

        // atualiza Current
        riskCatPrvtDAO.update( riskCatPrvtEntity );
      }
      else
      {
        riskCatPrvtDAO.insert( riskCatPrvtEntity );
      }

      // 3. Remove o movimento
      /** *** 20110321 ***
      * As funcionalidades que utilizavam a tabela PL.TPL_PROD_RISK_CAT_PRVT devem utilizar a tabela
      * PL.TPL_RISK_INVST_PROD_RDIP (apenas funcionalidades que utilizem consulta, as telas de 
      * inserção e alteração serão removidas)
      */      
      //riskCatPrvtMovDAO.delete( movDetailEntity );
    }
  }

  /**
   * Rejeita os dados de uma Categoria de Risco do Produto com Pendência de
   * Aprovação.
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#reprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void reprove( BaseFncVO fncVO_ )
  {
    //  realiza validação
    validateReprove( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      TplProdRiskCatPrvtMovDAO riskCatPrvtMovDAO = ODSDAOFactory.getInstance().getTplProdRiskCatPrvtMovDAO();
      //1 - Obtem o registro selecionado
      TplProdRiskCatPrvtMovEntity movDetailEntity = ( TplProdRiskCatPrvtMovEntity ) riskCatPrvtMovDAO.find( ( ( ProdRiskCatPrvtMovementDetailFncVO ) fncVO_ ).getBaseTplProdRiskCatPrvtEntity() );

      // 2 - Remove movimento
      /** *** 20110321 ***
       * As funcionalidades que utilizavam a tabela PL.TPL_PROD_RISK_CAT_PRVT devem utilizar a tabela
       * PL.TPL_RISK_INVST_PROD_RDIP (apenas funcionalidades que utilizem consulta, as telas de 
       * inserção e alteração serão removidas)
       */       
      //riskCatPrvtMovDAO.delete( movDetailEntity );
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    ProdRiskCatPrvtMovementDetailFncVO prodRiskCatPrvtMovementDetailFncVO = ( ProdRiskCatPrvtMovementDetailFncVO ) fncVO_;
    TplProdRiskCatPrvtMovEntityVO prodRiskCatPrvtMovEntityVO = ( TplProdRiskCatPrvtMovEntityVO ) prodRiskCatPrvtMovementDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData();

    // Testar preenchimento dos campos
    if ( prodRiskCatPrvtMovEntityVO.getProdRiskCatCode() == null
         || prodRiskCatPrvtMovEntityVO.getProdRiskCatCode().intValue() == 0 )
    {
      fncVO_.addError(
                       ProdRiskCatPrvtMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_NAME_CODE );
    }

    if ( prodRiskCatPrvtMovEntityVO.getProdRiskCatText() == null
         || prodRiskCatPrvtMovEntityVO.getProdRiskCatText().equals( "" ) )
    {
      fncVO_.addError(
                       ProdRiskCatPrvtMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_NAME_TEXT );
    }

    // testar usuário
    if ( prodRiskCatPrvtMovementDetailFncVO.getLoggedUser() == null
         || !prodRiskCatPrvtMovementDetailFncVO.getLoggedUser().getUserID().equals(
                                                                                    prodRiskCatPrvtMovEntityVO.getLastUpdUserID() ) )
    {
      prodRiskCatPrvtMovementDetailFncVO.addError( ProdRiskCatPrvtMovementDetailFncVO.C_ERROR_APPROVAL_UPDATE_USER_NOT_AUTHORIZED );
    }

    // Se opernCode = Delete, adicionar erros
    if ( !fncVO_.hasErrors() )
    {
      TplProdRiskCatPrvtMovDAO tplProdRiskCatPrvtMovDAO = ODSDAOFactory.getInstance().getTplProdRiskCatPrvtMovDAO();
      TplProdRiskCatPrvtMovEntity tplProdRiskCatPrvtMovEntity = ( TplProdRiskCatPrvtMovEntity ) tplProdRiskCatPrvtMovDAO.find( prodRiskCatPrvtMovementDetailFncVO.getBaseTplProdRiskCatPrvtEntity() );

      String opernCode = ( ( TplProdRiskCatPrvtMovEntityVO ) tplProdRiskCatPrvtMovEntity.getData() ).getOpernCode();

      if ( TplProdRiskCatPrvtMovEntity.C_OPERN_CODE_DELETE.equals( opernCode ) )
      {
        prodRiskCatPrvtMovementDetailFncVO.addError( ProdRiskCatPrvtMovementDetailFncVO.C_ERROR_CANNOT_UPD_DELETE_IN_MOVEMENT );
      }
    }
  }

  /**
   * Validar a aprovação 1. Usuário != nulo 2. Usuário != lastUpdUpserId
   * (Usuário que está aprovando é diferente do usuário que inseriu o registro
   * p/ aprovação)
   */
  public void validateApprove( BaseFncVO fncVO_ )
  {
    ProdRiskCatPrvtMovementDetailFncVO prodRiskCatPrvtMovementDetailFncVO = ( ProdRiskCatPrvtMovementDetailFncVO ) fncVO_;
    TplProdRiskCatPrvtMovEntityVO prodRiskCatPrvtMovEntityVO = ( TplProdRiskCatPrvtMovEntityVO ) prodRiskCatPrvtMovementDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData();

    // testar usuário
    if ( prodRiskCatPrvtMovementDetailFncVO.getLoggedUser() == null
         || prodRiskCatPrvtMovementDetailFncVO.getLoggedUser().getUserID().equals(
                                                                                   prodRiskCatPrvtMovEntityVO.getLastUpdUserID() ) )
    {
      prodRiskCatPrvtMovementDetailFncVO.addError( ProdRiskCatPrvtMovementDetailFncVO.C_ERROR_APPROVAL_USER_NOT_AUTHORIZED );
    }
  }

  /**
   * Inserindo as informações no Form a partir do FncVO - Campos específicos
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );
    ProdRiskCatPrvtMovementDetailFncVO prodRiskCatPrvtMovementDetailFncVO = ( ProdRiskCatPrvtMovementDetailFncVO ) fncVO_;
    TplProdRiskCatPrvtMovEntityVO entityVO = ( TplProdRiskCatPrvtMovEntityVO ) prodRiskCatPrvtMovementDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData();
    String opernCode = entityVO.getOpernCode();
    ProdRiskCatPrvtMovementDetailForm form = ( ProdRiskCatPrvtMovementDetailForm ) form_;
    if ( opernCode != null && !"".equals( opernCode ) )
    {
      form.setOpernCode( ODSConstraintDecoder.decodeOpern( opernCode ) );
    }
  }

  /**
   * Validar reprovação
   */
  public void validateReprove( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Carregamento iniciais - Update
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    super.loadProdRiskCatPrvt( ( ProdRiskCatPrvtMovementDetailFncVO ) fncVO_ );
  }

  /**
   * Carregamento iniciais - Aprovacao
   */
  public void loadForApprove( BaseFncVO fncVO_ )
  {
    super.loadProdRiskCatPrvt( ( ProdRiskCatPrvtMovementDetailFncVO ) fncVO_ );

  }

  /**
   * Carregamento iniciais - Detail
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {

  }

  /**
   * Carregamento iniciais - Retorna instancia do FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new ProdRiskCatPrvtMovementDetailFncVO();
  }

  /**
   * Retorna o DAO
   */
  protected BaseTplProdRiskCatPrvtDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplProdRiskCatPrvtMovDAO();
  }
}