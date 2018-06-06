/*
 * Created on Mar 17, 2007
 *
 */
package com.citibank.ods.modules.product.prodqlfyprvt.functionality;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplProdQlfyPrvtEntity;
import com.citibank.ods.entity.pl.TplProdQlfyPrvtEntity;
import com.citibank.ods.entity.pl.TplProdQlfyPrvtHistEntity;
import com.citibank.ods.entity.pl.TplProdQlfyPrvtMovEntity;
import com.citibank.ods.entity.pl.TplProductFamilyPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdQlfyPrvtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdQlfyPrvtMovEntityVO;
import com.citibank.ods.modules.product.prodqlfyprvt.form.ProdQlfyPrvtMovementDetailForm;
import com.citibank.ods.modules.product.prodqlfyprvt.functionality.valueobject.ProdQlfyPrvtMovementDetailFncVO;
import com.citibank.ods.modules.product.prodriskcatprvt.functionality.valueobject.ProdRiskCatPrvtMovementDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdQlfyPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author fernando.salgado
 *  
 */
public class ProdQlfyPrvtMovementDetailFnc extends BaseProdQlfyPrvtDetailFnc
    implements ODSMovementDetailFnc
{

  /**
   * Altera os dados de um Qualificador de Produto com Pendência de Aprovação.
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void update( BaseFncVO fncVO_ )
  {
    validateUpdate( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      ProdQlfyPrvtMovementDetailFncVO prodQlfyPrvtMovementDetailFncVO = ( ProdQlfyPrvtMovementDetailFncVO ) fncVO_;

      prodQlfyPrvtMovementDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().setLastUpdUserId(
                                                                                                 fncVO_.getLoggedUser().getUserID() );
      prodQlfyPrvtMovementDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().setLastUpdDate(
                                                                                               new Date() );

      TplProdQlfyPrvtMovDAO prodQlfyPrvtMovDAO = ODSDAOFactory.getInstance().getTplProdQlfyPrvtMovDAO();
      prodQlfyPrvtMovDAO.update( ( TplProdQlfyPrvtMovEntity ) prodQlfyPrvtMovementDetailFncVO.getBaseTplProdQlfyPrvtEntity() );
    }

  }

  /**
   * Aprova os dados de um Qualificador de Produto com Pendência de Aprovação.
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#approve(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void approve( BaseFncVO fncVO_ )
  {
    validateApprove( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      TplProdQlfyPrvtMovDAO prodQlfyPrvtMovDAO = ODSDAOFactory.getInstance().getTplProdQlfyPrvtMovDAO();
      
      TplProdQlfyPrvtMovEntity movDetailEntity = ( TplProdQlfyPrvtMovEntity ) prodQlfyPrvtMovDAO.find( ( ( ProdQlfyPrvtMovementDetailFncVO ) fncVO_ ).getBaseTplProdQlfyPrvtEntity() );

      BaseTplProdQlfyPrvtEntity baseTplProdQlfyPrvtEntity = new TplProdQlfyPrvtEntity();
      baseTplProdQlfyPrvtEntity.getData().setProdQlfyCode(
                                                           movDetailEntity.getData().getProdQlfyCode() );

      String movOpernCode = ( ( TplProdQlfyPrvtMovEntityVO ) movDetailEntity.getData() ).getOpernCode();
      TplProdQlfyPrvtEntity tplProdQlfyPrvtEntity = new TplProdQlfyPrvtEntity(
                                                                               movDetailEntity,
                                                                               new Date(),
                                                                               fncVO_.getLoggedUser().getUserID(),
                                                                               TplProductFamilyPrvtEntity.C_REC_STAT_CODE_ACTIVE );

      // Verifica qual operacao está sendo aprovada
      if ( TplProdQlfyPrvtMovEntity.C_OPERN_CODE_DELETE.equals( movOpernCode ) )
      {
        //setar estatus como inativo
        TplProdQlfyPrvtEntityVO tplProdQlfyPrvtEntityVO = ( TplProdQlfyPrvtEntityVO ) tplProdQlfyPrvtEntity.getData();
        tplProdQlfyPrvtEntityVO.setRecStatCode( TplProdQlfyPrvtEntity.C_REC_STAT_CODE_INACTIVE );
      }

      TplProdQlfyPrvtDAO prodQlfyPrvtDAO = ODSDAOFactory.getInstance().getTplProdQlfyPrvtDAO();
      if ( prodQlfyPrvtDAO.exists( tplProdQlfyPrvtEntity ) )
      {
      	TplProdQlfyPrvtEntity prodQlfyPrvtEntityOld = (TplProdQlfyPrvtEntity)prodQlfyPrvtDAO.find(tplProdQlfyPrvtEntity);
      	
        TplProdQlfyPrvtHistDAO prodQlfyPrvtHistDAO = ODSDAOFactory.getInstance().getTplProdQlfyPrvtHistDAO();
        TplProdQlfyPrvtHistEntity prodQlfyPrvtHistEntity = new TplProdQlfyPrvtHistEntity(
        																				  prodQlfyPrvtEntityOld,
                                                                                          new Date() );
        prodQlfyPrvtHistDAO.insert( prodQlfyPrvtHistEntity );

        prodQlfyPrvtDAO.update( tplProdQlfyPrvtEntity );
      }
      else
      {
        prodQlfyPrvtDAO.insert( tplProdQlfyPrvtEntity );
      }
      prodQlfyPrvtMovDAO.delete( movDetailEntity );
    }
  }

  /**
   * Reprova os dados de um Qualificador de Produto com Pendência de Aprovação.
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#reprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void reprove( BaseFncVO fncVO_ )
  {
    // realiza validação
    validateReprove( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      TplProdQlfyPrvtMovDAO prodQlfyPrvtMovDAO = ODSDAOFactory.getInstance().getTplProdQlfyPrvtMovDAO();
      //1 - Obtem o registro selecionado
      TplProdQlfyPrvtMovEntity movDetailEntity = ( TplProdQlfyPrvtMovEntity ) prodQlfyPrvtMovDAO.find( ( ( ProdQlfyPrvtMovementDetailFncVO ) fncVO_ ).getBaseTplProdQlfyPrvtEntity() );

      // 2 - Remove movimento
      prodQlfyPrvtMovDAO.delete( movDetailEntity );
    }

  }

  /**
   * Faz a validação das Alterações
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    ProdQlfyPrvtMovementDetailFncVO prodQlfyPrvtMovementDetailFncVO = ( ProdQlfyPrvtMovementDetailFncVO ) fncVO_;
    TplProdQlfyPrvtMovEntityVO prodQlfyPrvtMovEntityVO = ( TplProdQlfyPrvtMovEntityVO ) prodQlfyPrvtMovementDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData();

    //testar usuário
    if ( !( prodQlfyPrvtMovementDetailFncVO.getLoggedUser().getUserID().equals( prodQlfyPrvtMovEntityVO.getLastUpdUserId() ) ) )

    {
      prodQlfyPrvtMovementDetailFncVO.addError( BaseODSFncVO.C_ERROR_APPROVAL_UPDATE_USER_NOT_AUTHORIZED );
    }
    else
    {
      // testar preenchimento dos campos
      if ( prodQlfyPrvtMovEntityVO.getProdQlfyCode() == null
           || prodQlfyPrvtMovEntityVO.getProdQlfyCode().intValue() == 0 )
      {
        prodQlfyPrvtMovementDetailFncVO.addError(
                                                  BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                                                  C_PROD_QLFY_CODE );
      }
      if ( prodQlfyPrvtMovEntityVO.getProdQlfyText() == null
           || prodQlfyPrvtMovEntityVO.getProdQlfyText().equals( "" ) )
      {
        prodQlfyPrvtMovementDetailFncVO.addError(
                                                  BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                                                  C_PROD_QLFY_TEXT );
      }
    }
    
    //  Se opernCode = Delete, adicionar erros
    if (!fncVO_.hasErrors())
    {
      TplProdQlfyPrvtMovDAO tplProdQlfyPrvtMovDAO = ODSDAOFactory.getInstance().getTplProdQlfyPrvtMovDAO();
      TplProdQlfyPrvtMovEntity tplProdQlfyPrvtMovEntity = (TplProdQlfyPrvtMovEntity)tplProdQlfyPrvtMovDAO.find(prodQlfyPrvtMovementDetailFncVO.getBaseTplProdQlfyPrvtEntity()); 
      
      
      String opernCode = ((TplProdQlfyPrvtMovEntityVO)tplProdQlfyPrvtMovEntity.getData()).getOpernCode();
      if (TplProdQlfyPrvtMovEntity.C_OPERN_CODE_DELETE.equals(opernCode)){
        prodQlfyPrvtMovementDetailFncVO.addError( ProdRiskCatPrvtMovementDetailFncVO.C_ERROR_CANNOT_UPD_DELETE_IN_MOVEMENT );
      }
    }
  }

  /**
   * Faz a validação das Aprovações
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateApprove( BaseFncVO fncVO_ )
  {
    ProdQlfyPrvtMovementDetailFncVO prodQlfyPrvtMovementDetailFncVO = ( ProdQlfyPrvtMovementDetailFncVO ) fncVO_;
    TplProdQlfyPrvtMovEntityVO prodQlfyPrvtMovEntityVO = ( TplProdQlfyPrvtMovEntityVO ) prodQlfyPrvtMovementDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData();

    //testar usuário
    if ( prodQlfyPrvtMovementDetailFncVO.getLoggedUser().getUserID().equals(
                                                                             prodQlfyPrvtMovEntityVO.getLastUpdUserId() ) )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_APPROVAL_USER_NOT_AUTHORIZED );
    }
  }

  /**
   * Faz a validação das Reprovações
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateReprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateReprove( BaseFncVO fncVO_ )
  {
    // TODO Auto-generated method stub

  }

  /**
   * Carrega os dados do registro selecionado para a página de alteração 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    super.loadProdQlfyPrvt( ( ProdQlfyPrvtMovementDetailFncVO ) fncVO_ );
  }

  /**
   * Carrega os dados do registro selecionado para a página de Aprovação
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForApprove( BaseFncVO fncVO_ )
  {
    super.loadProdQlfyPrvt( ( ProdQlfyPrvtMovementDetailFncVO ) fncVO_ );
  }

  /**
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    // TODO Auto-generated method stub

  }

  /**
   * Retorna uma nova instância de FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new ProdQlfyPrvtMovementDetailFncVO();
  }

  /**
   * Retorna o DAO
   * @see com.citibank.ods.modules.product.prodqlfyprvt.functionality.BaseProdQlfyPrvtDetailFnc#getDAO()
   */
  protected BaseTplProdQlfyPrvtDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplProdQlfyPrvtMovDAO();
  }

  /**
   * Seta na Form os campos específicos de movimento.
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );
    ProdQlfyPrvtMovementDetailFncVO detailFncVO = ( ProdQlfyPrvtMovementDetailFncVO ) fncVO_;
    ProdQlfyPrvtMovementDetailForm movementDetailForm = ( ProdQlfyPrvtMovementDetailForm ) form_;

    String opernCode = ( ( TplProdQlfyPrvtMovEntityVO ) detailFncVO.getBaseTplProdQlfyPrvtEntity().getData() ).getOpernCode();
    if ( opernCode != null && !"".equals( opernCode ) )
    {
      String strOpernCode = ODSConstraintDecoder.decodeOpern( opernCode );
      movementDetailForm.setOpernCode( strOpernCode );
    }
  }
}