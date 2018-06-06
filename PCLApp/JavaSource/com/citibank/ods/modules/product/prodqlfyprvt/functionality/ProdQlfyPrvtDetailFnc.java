/*
 * Created on Mar 17, 2007
 *
 */
package com.citibank.ods.modules.product.prodqlfyprvt.functionality;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.TplProdQlfyPrvtEntity;
import com.citibank.ods.entity.pl.TplProdQlfyPrvtMovEntity;
import com.citibank.ods.modules.product.prodqlfyprvt.functionality.valueobject.ProdQlfyPrvtDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdQlfyPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.TplProductDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author fernando.salgado
 *  
 */
public class ProdQlfyPrvtDetailFnc extends BaseProdQlfyPrvtDetailFnc implements
    ODSDetailFnc
{

  private static final String C_PROD_QLFY = "Qualificação do Produto";

  private static final String C_PRODUCTS = "Produtos";

  /**
   * Insere um registro de agregador de produto
   */
  public void insert( BaseFncVO fncVO_ )
  {
    ProdQlfyPrvtDetailFncVO detailFncVO = ( ProdQlfyPrvtDetailFncVO ) fncVO_;
    validateInsert( detailFncVO );
    if ( !detailFncVO.hasErrors() )
    {
      TplProdQlfyPrvtEntity prodQlfyPrvtEntity = ( TplProdQlfyPrvtEntity ) detailFncVO.getBaseTplProdQlfyPrvtEntity();
      prodQlfyPrvtEntity.getData().setLastUpdDate( new Date() );
      prodQlfyPrvtEntity.getData().setLastUpdUserId(
                                                     fncVO_.getLoggedUser() != null
                                                                                   ? fncVO_.getLoggedUser().getUserID()
                                                                                   : "" );

      TplProdQlfyPrvtMovEntity prvtMovEntity = new TplProdQlfyPrvtMovEntity(
                                                                             prodQlfyPrvtEntity,
                                                                             TplProdQlfyPrvtMovEntity.C_OPERN_CODE_INSERT );
      TplProdQlfyPrvtMovDAO tplProdQlfyPrvtMovDAO = ODSDAOFactory.getInstance().getTplProdQlfyPrvtMovDAO();
      tplProdQlfyPrvtMovDAO.insert( prvtMovEntity );
    }

  }

  /**
   * Atualiza um registro de agregador de produto
   */
  public void update( BaseFncVO fncVO_ )
  {
    ProdQlfyPrvtDetailFncVO detailFncVO = ( ProdQlfyPrvtDetailFncVO ) fncVO_;
    validateUpdate( detailFncVO );
    if ( !detailFncVO.hasErrors() )
    {

      //createMovementRecord
      TplProdQlfyPrvtEntity prodQlfyPrvtEntity = ( TplProdQlfyPrvtEntity ) detailFncVO.getBaseTplProdQlfyPrvtEntity();
      prodQlfyPrvtEntity.getData().setLastUpdDate( new Date() );
      prodQlfyPrvtEntity.getData().setLastUpdUserId(
                                                     fncVO_.getLoggedUser() != null
                                                                                   ? fncVO_.getLoggedUser().getUserID()
                                                                                   : "" );

      TplProdQlfyPrvtMovEntity prvtMovEntity = new TplProdQlfyPrvtMovEntity(
                                                                             prodQlfyPrvtEntity,
                                                                             TplProdQlfyPrvtMovEntity.C_OPERN_CODE_UPDATE );
      TplProdQlfyPrvtMovDAO tplProdQlfyPrvtMovDAO = ODSDAOFactory.getInstance().getTplProdQlfyPrvtMovDAO();
      tplProdQlfyPrvtMovDAO.insert( prvtMovEntity );

    }

  }

  /**
   * Remove um registro de agregador de produto
   */
  public void delete( BaseFncVO fncVO_ )
  {
    ProdQlfyPrvtDetailFncVO detailFncVO = ( ProdQlfyPrvtDetailFncVO ) fncVO_;

    validateDelete( detailFncVO );
    if ( !detailFncVO.hasErrors() )
    {

      TplProdQlfyPrvtDAO tplProdQlfyPrvtDAO = ODSDAOFactory.getInstance().getTplProdQlfyPrvtDAO();
      TplProdQlfyPrvtEntity prodQlfyPrvtEntity = ( TplProdQlfyPrvtEntity ) tplProdQlfyPrvtDAO.find( detailFncVO.getBaseTplProdQlfyPrvtEntity() );

      TplProdQlfyPrvtMovEntity prvtMovEntity = new TplProdQlfyPrvtMovEntity(
                                                                             prodQlfyPrvtEntity,
                                                                             TplProdQlfyPrvtMovEntity.C_OPERN_CODE_DELETE );

      prvtMovEntity.getData().setLastUpdDate( new Date() );
      prodQlfyPrvtEntity.getData().setLastUpdUserId(
                                                     fncVO_.getLoggedUser() != null
                                                                                   ? fncVO_.getLoggedUser().getUserID()
                                                                                   : "" );

      TplProdQlfyPrvtMovDAO tplProdQlfyPrvtMovDAO = ODSDAOFactory.getInstance().getTplProdQlfyPrvtMovDAO();
      tplProdQlfyPrvtMovDAO.insert( prvtMovEntity );

    }

  }

  /**
   * Realiza as validações - INSERT
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
    ProdQlfyPrvtDetailFncVO prodQlfyPrvtDetailFncVO = ( ProdQlfyPrvtDetailFncVO ) fncVO_;

    // Validar Campos Obrigatórios
    if ( prodQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().getProdQlfyCode() == null
         || prodQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().getProdQlfyCode().intValue() == 0 )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_PROD_QLFY_CODE );
    }

    if ( prodQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().getProdQlfyText() == null
         || prodQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().getProdQlfyText().equals(
                                                                                                       "" ) )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_PROD_QLFY_TEXT );
    }

    //	  Verifica se já existe algum registro com status ativo
    if ( !fncVO_.hasErrors() )
    {
      if ( existsActive( prodQlfyPrvtDetailFncVO ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_DUPLICATE_PK );
      }
    }
    // Validar se já existe movimento com este codigo, caso os campos
    // obrigatórios estejam presentes
    if ( !fncVO_.hasErrors() )
    {
      if ( this.existsInMovement( prodQlfyPrvtDetailFncVO ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_IN_MOVEMENT );
      }
    }

  }

  /**
   * Realiza as validações - UPDATE
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    ProdQlfyPrvtDetailFncVO prodQlfyPrvtDetailFncVO = ( ProdQlfyPrvtDetailFncVO ) fncVO_;

    //  Validar Campos Obrigatórios
    if ( prodQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().getProdQlfyText() == null
         || prodQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().getProdQlfyText() == "" )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_PROD_QLFY_TEXT );
    }

    if ( prodQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().getProdQlfyText() == null
         || prodQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().getProdQlfyText().equals(
                                                                                                       "" ) )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_PROD_QLFY_TEXT );
    }

    // Verifica se já existe algum registro com status ativo
    if ( !existsActive( prodQlfyPrvtDetailFncVO ) )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_DUPLICATE_PK );
    }

    // Validar se já existe movimento com este codigo, caso os campos
    // obrigatórios estejam presentes
    if ( !fncVO_.hasErrors() )
    {
      if ( this.existsInMovement( prodQlfyPrvtDetailFncVO ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_IN_MOVEMENT );
      }
    }
  }

  /**
   * Realiza as validações - DELETE
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
    ProdQlfyPrvtDetailFncVO prodQlfyPrvtDetailFncVO = ( ProdQlfyPrvtDetailFncVO ) fncVO_;

    // Verifica se já existe algum registro com status ativo
    if ( !existsActive( prodQlfyPrvtDetailFncVO ) )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_PK_NOT_FOUND );
    }

    // Validar se já existe movimento com este codigo, caso os campos
    // obrigatórios estejam presentes
    if ( !fncVO_.hasErrors() )
    {
      if ( this.existsInMovement( prodQlfyPrvtDetailFncVO ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_IN_MOVEMENT );
      }
    }
    
    //Valida se algum produto ativo esta utilizando esta qualificação de produto
    if ( !fncVO_.hasErrors() )
    {
      if ( this.existsProductActive( prodQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().getProdQlfyCode() ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_IS_REFERENCED, C_PROD_QLFY,
                         C_PRODUCTS );
      }
    }

  }

  /**
   * Carregamentos iniciais - Detail
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Carregamentos iniciais - Insert
   */
  public void loadForInsert( BaseFncVO fncVO_ )
  {
    ProdQlfyPrvtDetailFncVO prodQlfyPrvtDetailFncVO = ( ProdQlfyPrvtDetailFncVO ) fncVO_;
    prodQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().setProdQlfyCode(
                                                                                      null );
    prodQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().setProdQlfyText(
                                                                                      null );
  }

  /**
   * Carregamentos iniciais - Update
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    ProdQlfyPrvtDetailFncVO prodQlfyPrvtDetailFncVO = ( ProdQlfyPrvtDetailFncVO ) fncVO_;
    // Verifica se o registro está ativo
    if ( !this.existsActive( prodQlfyPrvtDetailFncVO ) )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_REG_NOT_EXISTS,
                       ProdQlfyPrvtDetailFncVO.C_PROD_QLFY_CODE_DESCRIPTION );
    }
    else
    {
      // Verifica se já existe algum registro com status ativo
      if ( existsInMovement( prodQlfyPrvtDetailFncVO ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_IN_MOVEMENT );
      }
      else
      {
        super.loadProdQlfyPrvt( ( ProdQlfyPrvtDetailFncVO ) fncVO_ );
      }
    }
  }

  /**
   * Carregamentos iniciais - Delete
   */
  public void loadForDelete( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Retorna uma instancia de um FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new ProdQlfyPrvtDetailFncVO();
  }

  /**
   * Retorna a instancia da DAO utilizado por este Fnc
   */
  protected BaseTplProdQlfyPrvtDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplProdQlfyPrvtDAO();
  }

  /*
   * Verifica se existe um registro na tabela de movimento com os critérios
   * passados
   * 
   * @see com.citibank.ods.modules.product.prodriskcatprvt.functionality.BaseProdRiskCatPrvtDetailFnc#getDAO()
   */
  private boolean existsInMovement(
                                   ProdQlfyPrvtDetailFncVO prodQlfyPrvtDetailFncVO_ )
  {
    TplProdQlfyPrvtMovDAO tplProdQlfyPrvtMovDAO = ODSDAOFactory.getInstance().getTplProdQlfyPrvtMovDAO();

    TplProdQlfyPrvtMovEntity prvtMovEntity = new TplProdQlfyPrvtMovEntity();
    prvtMovEntity.getData().setProdQlfyCode(
                                             prodQlfyPrvtDetailFncVO_.getBaseTplProdQlfyPrvtEntity().getData().getProdQlfyCode() );

    return tplProdQlfyPrvtMovDAO.exists( prvtMovEntity );
  }

  /*
   * Verifica se existe algum registro com a chave passada e seu status é ativo
   */
  private boolean existsActive( ProdQlfyPrvtDetailFncVO prodQlfyPrvtDetailFncVO_ )
  {
    TplProdQlfyPrvtDAO tplProdQlfyPrvtDAO = ODSDAOFactory.getInstance().getTplProdQlfyPrvtDAO();
    return tplProdQlfyPrvtDAO.existsActive( ( TplProdQlfyPrvtEntity ) prodQlfyPrvtDetailFncVO_.getBaseTplProdQlfyPrvtEntity() );
  }
  
  /*
   * Verifica se existe um produto ativo que utilize a qualificação de produto passada como parametro
   */
  private boolean existsProductActive( BigInteger prodQlfyCode_ )
  {
    TplProductDAO tplProductDAO = ODSDAOFactory.getInstance().getTplProductDAO();
    return tplProductDAO.existsProductByForeignKey( null, prodQlfyCode_, null,
                                                    null,null );
  }

}