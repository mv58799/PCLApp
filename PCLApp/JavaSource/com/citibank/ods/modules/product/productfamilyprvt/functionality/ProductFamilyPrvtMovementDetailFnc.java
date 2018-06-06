/*
 * Created on Mar 18, 2007
 *
 */
package com.citibank.ods.modules.product.productfamilyprvt.functionality;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplProductFamilyPrvtEntity;
import com.citibank.ods.entity.pl.TplProductFamilyPrvtHistEntity;
import com.citibank.ods.entity.pl.TplProductFamilyPrvtMovEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplProductFamilyPrvtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductFamilyPrvtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductFamilyPrvtMovEntityVO;
import com.citibank.ods.modules.product.productfamilyprvt.form.ProductFamilyPrvtMovementDetailForm;
import com.citibank.ods.modules.product.productfamilyprvt.functionality.valueobject.ProductFamilyPrvtMovementDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProductFamilyPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProductFamilyPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProductFamilyPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.TplProductFamilyPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class ProductFamilyPrvtMovementDetailFnc extends
    BaseProductFamilyPrvtDetailFnc implements ODSMovementDetailFnc
{

  /**
   * Retorna o DAO utilizado pelo método load da super classe
   * 
   * @see com.citibank.ods.modules.product.productfamilyprvt.functionality.BaseProductFamilyPrvtDetailFnc#getDAO()
   */
  protected BaseTplProductFamilyPrvtDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplProductFamilyPrvtMovDAO();
  }

  /**
   * Retorna uma instância do FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new ProductFamilyPrvtMovementDetailFncVO();
  }

  /**
   * Realiza a atualização de um registro de movimento
   */
  public void update( BaseFncVO fncVO_ )
  {
    validateUpdate( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      ProductFamilyPrvtMovementDetailFncVO movementDetailFncVO = ( ProductFamilyPrvtMovementDetailFncVO ) fncVO_;
      BaseTplProductFamilyPrvtEntityVO baseTplProductFamilyPrvtEntityVO = movementDetailFncVO.getBaseTplProductFamilyPrvtEntity().getData();

      baseTplProductFamilyPrvtEntityVO.setLastUpdDate( new Date() );
      baseTplProductFamilyPrvtEntityVO.setLastUpdUserId( fncVO_.getLoggedUser() != null
                                                                                       ? fncVO_.getLoggedUser().getUserID()
                                                                                       : "" );

      TplProductFamilyPrvtMovDAO familyPrvtMovDAO = ODSDAOFactory.getInstance().getTplProductFamilyPrvtMovDAO();
      familyPrvtMovDAO.update( ( TplProductFamilyPrvtMovEntity ) movementDetailFncVO.getBaseTplProductFamilyPrvtEntity() );
    }
  }

  /**
   * Realiza a aprovação de um registro que está pendente de aprovação
   */
  public void approve( BaseFncVO fncVO_ )
  {
    validateApprove( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      // Instancia da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();

      // Declaracao dos DAOs
      TplProductFamilyPrvtDAO tplProductFamilyPrvtDAO = factory.getTplProductFamilyPrvtDAO();
      TplProductFamilyPrvtMovDAO tplProductFamilyPrvtMovDAO = factory.getTplProductFamilyPrvtMovDAO();
      TplProductFamilyPrvtHistDAO tplProductFamilyPrvtHistDAO = factory.getTplProductFamilyPrvtHistDAO();

      // FncVO do movimento
      ProductFamilyPrvtMovementDetailFncVO movementDetailFncVO = ( ProductFamilyPrvtMovementDetailFncVO ) fncVO_;
      super.loadProductFamily( movementDetailFncVO );

      // Entity do movimento
      TplProductFamilyPrvtMovEntity familyPrvtMovEntity = ( TplProductFamilyPrvtMovEntity ) movementDetailFncVO.getBaseTplProductFamilyPrvtEntity();

      // OpernCode
      String opernCode = ( ( TplProductFamilyPrvtMovEntityVO ) familyPrvtMovEntity.getData() ).getOpernCode();

      TplProductFamilyPrvtEntity tplProductFamilyPrvtEntity = new TplProductFamilyPrvtEntity(
                                                                                              familyPrvtMovEntity,
                                                                                              new Date(),
                                                                                              fncVO_.getLoggedUser().getUserID(),
                                                                                              TplProductFamilyPrvtEntity.C_REC_STAT_CODE_ACTIVE );

      // Verifica qual operacao está sendo aprovada
      if ( TplProductFamilyPrvtMovEntity.C_OPERN_CODE_DELETE.equals( opernCode ) )
      {
        //setar estatus como inativo
        TplProductFamilyPrvtEntityVO tplProdutFamilyPrvtEntityVO = ( TplProductFamilyPrvtEntityVO ) tplProductFamilyPrvtEntity.getData();
        tplProdutFamilyPrvtEntityVO.setRecStatCode( TplProductFamilyPrvtEntity.C_REC_STAT_CODE_INACTIVE );
      }

      if ( tplProductFamilyPrvtDAO.exists( tplProductFamilyPrvtEntity ) )
      {
        TplProductFamilyPrvtEntity tplProductFamilyPrvtEntityOld = ( TplProductFamilyPrvtEntity ) tplProductFamilyPrvtDAO.find( tplProductFamilyPrvtEntity );
        TplProductFamilyPrvtHistEntity productFamilyPrvtHistEntity = new TplProductFamilyPrvtHistEntity(
                                                                                                         tplProductFamilyPrvtEntityOld,
                                                                                                         new Date() );
        tplProductFamilyPrvtHistDAO.insert( productFamilyPrvtHistEntity );

        tplProductFamilyPrvtDAO.update( tplProductFamilyPrvtEntity );
      }
      else
      {
        tplProductFamilyPrvtDAO.insert( tplProductFamilyPrvtEntity );
      }
      tplProductFamilyPrvtMovDAO.delete( familyPrvtMovEntity );
    }

  }

  /**
   * Rejeita uma ação que está pendente de aprovação
   */
  public void reprove( BaseFncVO fncVO_ )
  {
    //  realiza validação
    validateReprove( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      ProductFamilyPrvtMovementDetailFncVO movementDetailFncVO = ( ProductFamilyPrvtMovementDetailFncVO ) fncVO_;
      TplProductFamilyPrvtMovEntity familyPrvtMovEntity = ( TplProductFamilyPrvtMovEntity ) movementDetailFncVO.getBaseTplProductFamilyPrvtEntity();

      TplProductFamilyPrvtMovDAO familyPrvtMovDAO = ODSDAOFactory.getInstance().getTplProductFamilyPrvtMovDAO();
      familyPrvtMovDAO.delete( familyPrvtMovEntity );
    }
  }

  /**
   * Realiza as Validações - Update
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    ProductFamilyPrvtMovementDetailFncVO productFamilyPrvtMovementDetailFncVO = ( ProductFamilyPrvtMovementDetailFncVO ) fncVO_;
    TplProductFamilyPrvtMovEntityVO productFamilyPrvtMovEntityVO = ( TplProductFamilyPrvtMovEntityVO ) productFamilyPrvtMovementDetailFncVO.getBaseTplProductFamilyPrvtEntity().getData();

    //testar usuário
    if ( !( productFamilyPrvtMovementDetailFncVO.getLoggedUser().getUserID().equals( productFamilyPrvtMovEntityVO.getLastUpdUserId() ) ) )

    {
      productFamilyPrvtMovementDetailFncVO.addError( BaseODSFncVO.C_ERROR_APPROVAL_UPDATE_USER_NOT_AUTHORIZED );
    }
    else
    {
      // testar campos obrigatórios
      if ( productFamilyPrvtMovEntityVO.getProdFamlCode() == null
           || productFamilyPrvtMovEntityVO.getProdFamlCode().intValue() == 0 )
      {
        productFamilyPrvtMovementDetailFncVO.addError(
                                                       BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                                                       C_DISPLAY_NAME_CODE );
      }

      if ( productFamilyPrvtMovEntityVO.getProdFamlName() == null
           || productFamilyPrvtMovEntityVO.getProdFamlName().equals( "" ) )
      {
        productFamilyPrvtMovementDetailFncVO.addError(
                                                       BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                                                       C_DISPLAY_NAME );
      }
    }

    //  Se opernCode = Delete, adicionar erros
    if ( !fncVO_.hasErrors() )
    {
      TplProductFamilyPrvtMovDAO tplProductFamilyPrvtMovDAO = ODSDAOFactory.getInstance().getTplProductFamilyPrvtMovDAO();
      TplProductFamilyPrvtMovEntity tplProductFamilyPrvtMovEntity = ( TplProductFamilyPrvtMovEntity ) tplProductFamilyPrvtMovDAO.find( productFamilyPrvtMovementDetailFncVO.getBaseTplProductFamilyPrvtEntity() );

      String opernCode = ( ( TplProductFamilyPrvtMovEntityVO ) tplProductFamilyPrvtMovEntity.getData() ).getOpernCode();

      if ( TplProductFamilyPrvtMovEntity.C_OPERN_CODE_DELETE.equals( opernCode ) )
      {
        productFamilyPrvtMovementDetailFncVO.addError( ProductFamilyPrvtMovementDetailFncVO.C_ERROR_CANNOT_UPD_DELETE_IN_MOVEMENT );
      }
    }
  }

  /**
   * Realiza as validações - Approve
   */
  public void validateApprove( BaseFncVO fncVO_ )
  {
    ProductFamilyPrvtMovementDetailFncVO productFamilyPrvtMovementDetailFncVO = ( ProductFamilyPrvtMovementDetailFncVO ) fncVO_;
    TplProductFamilyPrvtMovEntityVO productFamilyPrvtMovEntityVO = ( TplProductFamilyPrvtMovEntityVO ) productFamilyPrvtMovementDetailFncVO.getBaseTplProductFamilyPrvtEntity().getData();

    //testar usuário
    if ( productFamilyPrvtMovementDetailFncVO.getLoggedUser().getUserID().equals(
                                                                                  productFamilyPrvtMovEntityVO.getLastUpdUserId() ) )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_APPROVAL_USER_NOT_AUTHORIZED );
    }

  }

  /**
   * Realiza as validações - Reprove
   */
  public void validateReprove( BaseFncVO fncVO_ )
  {
    // TODO Auto-generated method stub

  }

  /**
   * Carregamentos iniciais - Update
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    ProductFamilyPrvtMovementDetailFncVO movementDetailFncVO = ( ProductFamilyPrvtMovementDetailFncVO ) fncVO_;
    super.loadProductFamily( movementDetailFncVO );
  }

  /**
   * Carregamentos iniciais - Approve
   */
  public void loadForApprove( BaseFncVO fncVO_ )
  {
    ProductFamilyPrvtMovementDetailFncVO movementDetailFncVO = ( ProductFamilyPrvtMovementDetailFncVO ) fncVO_;
    super.loadProductFamily( movementDetailFncVO );
  }

  /**
   * Carregamentos iniciais - Detail
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    // TODO Auto-generated method stub

  }

  /**
   * Inserindo as informações no Form a partir do FncVO - Campos específicos
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {

    super.updateFormFromFncVO( form_, fncVO_ );

    ProductFamilyPrvtMovementDetailFncVO productFamilyPrvtMovementDetailFncVO = ( ProductFamilyPrvtMovementDetailFncVO ) fncVO_;
    TplProductFamilyPrvtMovEntityVO productFamilyPrvtMovEntityVO = ( TplProductFamilyPrvtMovEntityVO ) productFamilyPrvtMovementDetailFncVO.getBaseTplProductFamilyPrvtEntity().getData();

    String opernCode = productFamilyPrvtMovEntityVO.getOpernCode();
    ProductFamilyPrvtMovementDetailForm productFamilyPrvtMovementDetailForm = ( ProductFamilyPrvtMovementDetailForm ) form_;

    if ( opernCode != null && !"".equals( opernCode ) )
    {
      productFamilyPrvtMovementDetailForm.setOpernCode( ODSConstraintDecoder.decodeOpern( opernCode ) );
    }
  }

}