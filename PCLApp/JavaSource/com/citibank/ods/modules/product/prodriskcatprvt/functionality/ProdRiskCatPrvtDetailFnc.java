/*
 * Created on Mar 14, 2007
 *
 */
package com.citibank.ods.modules.product.prodriskcatprvt.functionality;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.TplProdRiskCatPrvtEntity;
import com.citibank.ods.entity.pl.TplProdRiskCatPrvtMovEntity;
import com.citibank.ods.modules.product.prodriskcatprvt.functionality.valueobject.ProdRiskCatPrvtDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdRiskCatPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.TplProductDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class ProdRiskCatPrvtDetailFnc extends BaseProdRiskCatPrvtDetailFnc
    implements ODSDetailFnc
{

  private static final String C_PROD_RISK_CAT = "Categoria de Risco";

  private static final String C_PRODUCTS = "Produtos";

  /**
   * Retorna uma instância do FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new ProdRiskCatPrvtDetailFncVO();
  }

  /**
   * Insere os dados de uma Categoria de Risco do Produto.
   */
  public void insert( BaseFncVO fncVO_ )
  {
    this.validateInsert( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      ProdRiskCatPrvtDetailFncVO detailFncVO = ( ProdRiskCatPrvtDetailFncVO ) fncVO_;
      TplProdRiskCatPrvtEntity prodRiskCatPrvtEntity = ( TplProdRiskCatPrvtEntity ) detailFncVO.getBaseTplProdRiskCatPrvtEntity();
      prodRiskCatPrvtEntity.getData().setLastUpdDate( new Date() );
      prodRiskCatPrvtEntity.getData().setLastUpdUserID(
                                                        fncVO_.getLoggedUser() != null
                                                                                      ? fncVO_.getLoggedUser().getUserID()
                                                                                      : "" );

      TplProdRiskCatPrvtMovEntity prvtMovEntity = new TplProdRiskCatPrvtMovEntity(
                                                                                   prodRiskCatPrvtEntity,
                                                                                   TplProdRiskCatPrvtMovEntity.C_OPERN_CODE_INSERT );
      TplProdRiskCatPrvtMovDAO tplProdRiskCatPrvtMovDAO = ODSDAOFactory.getInstance().getTplProdRiskCatPrvtMovDAO();
      
      /** *** 20110321 ***
       * As funcionalidades que utilizavam a tabela PL.TPL_PROD_RISK_CAT_PRVT devem utilizar a tabela
       * PL.TPL_RISK_INVST_PROD_RDIP (apenas funcionalidades que utilizem consulta, as telas de 
       * inserção e alteração serão removidas)
       */      
      //tplProdRiskCatPrvtMovDAO.insert( prvtMovEntity );
    }
  }

  /**
   * Atualiza os dados de uma Categoria de Risco do Produto.
   */
  public void update( BaseFncVO fncVO_ )
  {
    validateUpdate( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      ProdRiskCatPrvtDetailFncVO detailFncVO = ( ProdRiskCatPrvtDetailFncVO ) fncVO_;

      //createMovementRecord
      TplProdRiskCatPrvtEntity prodRiskCatPrvtEntity = ( TplProdRiskCatPrvtEntity ) detailFncVO.getBaseTplProdRiskCatPrvtEntity();
      prodRiskCatPrvtEntity.getData().setLastUpdDate( new Date() );
      prodRiskCatPrvtEntity.getData().setLastUpdUserID(
                                                        fncVO_.getLoggedUser() != null
                                                                                      ? fncVO_.getLoggedUser().getUserID()
                                                                                      : "" );

      TplProdRiskCatPrvtMovEntity prvtMovEntity = new TplProdRiskCatPrvtMovEntity(
                                                                                   prodRiskCatPrvtEntity,
                                                                                   TplProdRiskCatPrvtMovEntity.C_OPERN_CODE_UPDATE );
      TplProdRiskCatPrvtMovDAO tplProdRiskCatPrvtMovDAO = ODSDAOFactory.getInstance().getTplProdRiskCatPrvtMovDAO();
      
      /** *** 20110321 ***
       * As funcionalidades que utilizavam a tabela PL.TPL_PROD_RISK_CAT_PRVT devem utilizar a tabela
       * PL.TPL_RISK_INVST_PROD_RDIP (apenas funcionalidades que utilizem consulta, as telas de 
       * inserção e alteração serão removidas)
       */      
      //tplProdRiskCatPrvtMovDAO.insert( prvtMovEntity );

    }
  }

  /**
   * Remove uma Categoria de Risco do Produto.
   */
  public void delete( BaseFncVO fncVO_ )
  {
    ProdRiskCatPrvtDetailFncVO detailFncVO = ( ProdRiskCatPrvtDetailFncVO ) fncVO_;

    validateDelete( detailFncVO );
    if ( !detailFncVO.hasErrors() )
    {

      TplProdRiskCatPrvtDAO tplProdRiskCatPrvtDAO = ODSDAOFactory.getInstance().getTplProdRiskCatPrvtDAO();
      TplProdRiskCatPrvtEntity prodRiskCatPrvtEntity = ( TplProdRiskCatPrvtEntity ) tplProdRiskCatPrvtDAO.find( detailFncVO.getBaseTplProdRiskCatPrvtEntity() );

      prodRiskCatPrvtEntity.getData().setLastUpdDate( new Date() );
      prodRiskCatPrvtEntity.getData().setLastUpdUserID(
                                                        fncVO_.getLoggedUser() != null
                                                                                      ? fncVO_.getLoggedUser().getUserID()
                                                                                      : "" );

      TplProdRiskCatPrvtMovEntity prvtMovEntity = new TplProdRiskCatPrvtMovEntity(
                                                                                   prodRiskCatPrvtEntity,
                                                                                   TplProdRiskCatPrvtMovEntity.C_OPERN_CODE_DELETE );

      TplProdRiskCatPrvtMovDAO tplProdRiskCatPrvtMovDAO = ODSDAOFactory.getInstance().getTplProdRiskCatPrvtMovDAO();
      
      /** *** 20110321 ***
       * As funcionalidades que utilizavam a tabela PL.TPL_PROD_RISK_CAT_PRVT devem utilizar a tabela
       * PL.TPL_RISK_INVST_PROD_RDIP (apenas funcionalidades que utilizem consulta, as telas de 
       * inserção e alteração serão removidas)
       */      
      //tplProdRiskCatPrvtMovDAO.insert( prvtMovEntity );

    }
  }

  /**
   * Realiza as validações - Insert
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
    ProdRiskCatPrvtDetailFncVO catPrvtDetailFncVO = ( ProdRiskCatPrvtDetailFncVO ) fncVO_;

    // Validar Campos Obrigatórios
    if ( catPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().getProdRiskCatCode() == null
         || catPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().getProdRiskCatCode().intValue() == 0 )
    {
      fncVO_.addError( ProdRiskCatPrvtDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_NAME_CODE );
    }

    if ( catPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().getProdRiskCatText() == null
         || catPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().getProdRiskCatText().equals(
                                                                                                        "" ) )
    {
      fncVO_.addError( ProdRiskCatPrvtDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_NAME_TEXT );
    }

    // caso os campos obrigatórios estejam presentes
    if ( !fncVO_.hasErrors() )
    {
      //    Validar se já existe um registro com este codigo na "Current",
      if ( this.existsActive( catPrvtDetailFncVO ) )
      {
        fncVO_.addError( ProdRiskCatPrvtDetailFncVO.C_ERROR_DUPLICATE_PK );
      }

      // Validar se já existe movimento com este codigo, caso os campos
      // obrigatórios estejam presentes
      if ( this.existsInMovement( catPrvtDetailFncVO ) )
      {
        fncVO_.addError( ProdRiskCatPrvtDetailFncVO.C_ERROR_IN_MOVEMENT );
      }
    }
  }

  /**
   * Realiza as validações - Update
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    ProdRiskCatPrvtDetailFncVO catPrvtDetailFncVO = ( ProdRiskCatPrvtDetailFncVO ) fncVO_;

    //  Validar Campos Obrigatórios
    if ( catPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().getProdRiskCatText() == null
         || catPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().getProdRiskCatText() == "" )
    {
      fncVO_.addError( ProdRiskCatPrvtDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_NAME_CODE );
    }

    if ( catPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().getProdRiskCatText() == null
         || catPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().getProdRiskCatText().equals(
                                                                                                        "" ) )
    {
      fncVO_.addError( ProdRiskCatPrvtDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_NAME_TEXT );
    }

    //  Validar se existe um registro com este codigo na "Current",
    // caso os campos obrigatórios estejam presentes
    if ( !fncVO_.hasErrors() )
    {
      if ( !this.existsActive( catPrvtDetailFncVO ) )
      {
        fncVO_.addError( ProdRiskCatPrvtDetailFncVO.C_ERROR_PK_NOT_FOUND );
      }

      if ( this.existsInMovement( catPrvtDetailFncVO ) )
      {
        fncVO_.addError( ProdRiskCatPrvtDetailFncVO.C_ERROR_IN_MOVEMENT );
      }
    }
  }

  /**
   * Realiza as validações - Delete
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
    ProdRiskCatPrvtDetailFncVO catPrvtDetailFncVO = ( ProdRiskCatPrvtDetailFncVO ) fncVO_;

    // Validar se existe um registro com este codigo na "Current",
    // caso os campos obrigatórios estejam presentes
    if ( !fncVO_.hasErrors() )
    {
      if ( !this.existsActive( catPrvtDetailFncVO ) )
      {
        fncVO_.addError( ProdRiskCatPrvtDetailFncVO.C_ERROR_PK_NOT_FOUND );
      }

      if ( this.existsInMovement( catPrvtDetailFncVO ) )
      {
        fncVO_.addError( ProdRiskCatPrvtDetailFncVO.C_ERROR_IN_MOVEMENT );
      }
    }

    //  Valida se algum produto ativo esta utilizando esta categoria de risco
    if ( !fncVO_.hasErrors() )
    {
      if ( this.existsProductActive( catPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().getProdRiskCatCode() ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_IS_REFERENCED, C_PROD_RISK_CAT,
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
    ProdRiskCatPrvtDetailFncVO catPrvtDetailFncVO = ( ProdRiskCatPrvtDetailFncVO ) fncVO_;
    catPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().setProdRiskCatCode(
                                                                                       null );
    catPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().setProdRiskCatText(
                                                                                       null );
  }

  /**
   * Carregamentos iniciais - Update
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    if ( this.existsInMovement( ( ProdRiskCatPrvtDetailFncVO ) fncVO_ ) )
    {
      fncVO_.addError( ProdRiskCatPrvtDetailFncVO.C_ERROR_IN_MOVEMENT );
    }
    else
    {
      super.loadProdRiskCatPrvt( ( ProdRiskCatPrvtDetailFncVO ) fncVO_ );
    }
  }

  /**
   * Carregamentos iniciais - Delete
   */
  public void loadForDelete( BaseFncVO fncVO_ )
  {
    //
  }

  /*
   * Verifica se existe um registro na tabela de movimento com os critérios
   * passados
   */
  
  private boolean existsInMovement(
                                   ProdRiskCatPrvtDetailFncVO catPrvtDetailFncVO_ )
  {
    TplProdRiskCatPrvtMovDAO tplProdRiskCatPrvtMovDAO = ODSDAOFactory.getInstance().getTplProdRiskCatPrvtMovDAO();

    TplProdRiskCatPrvtMovEntity prvtMovEntity = new TplProdRiskCatPrvtMovEntity();
    prvtMovEntity.getData().setProdRiskCatCode(
                                                catPrvtDetailFncVO_.getBaseTplProdRiskCatPrvtEntity().getData().getProdRiskCatCode() );

    /** *** 20110321 ***
    * As funcionalidades que utilizavam a tabela PL.TPL_PROD_RISK_CAT_PRVT devem utilizar a tabela
    * PL.TPL_RISK_INVST_PROD_RDIP (apenas funcionalidades que utilizem consulta, as telas de 
    * inserção e alteração serão removidas)
    */
    //return tplProdRiskCatPrvtMovDAO.exists( prvtMovEntity );
    return false;
  }

  /*
   * Verifica se já existe um registro na tabela de "Current" com o código
   * passado e o seu status é "Ativo"
   */
  private boolean existsActive( ProdRiskCatPrvtDetailFncVO catPrvtDetailFncVO_ )
  {
    TplProdRiskCatPrvtDAO catPrvtDAO = ODSDAOFactory.getInstance().getTplProdRiskCatPrvtDAO();
    return catPrvtDAO.existsActive( ( TplProdRiskCatPrvtEntity ) catPrvtDetailFncVO_.getBaseTplProdRiskCatPrvtEntity() );
  }

  /*
   * Verifica se existe um produto ativo que utilize a categoria de risco
   * passada como parametro
   */
  private boolean existsProductActive( BigInteger prodRiskCatCode_ )
  {
    TplProductDAO tplProductDAO = ODSDAOFactory.getInstance().getTplProductDAO();
    return tplProductDAO.existsProductByForeignKey( null, null,
                                                    prodRiskCatCode_, null,null );
  }

  /**
   * Retorna o DAO de "Current"
   * @see com.citibank.ods.modules.product.prodriskcatprvt.functionality.BaseProdRiskCatPrvtDetailFnc#getDAO()
   */
  protected BaseTplProdRiskCatPrvtDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplProdRiskCatPrvtDAO();
  }
}