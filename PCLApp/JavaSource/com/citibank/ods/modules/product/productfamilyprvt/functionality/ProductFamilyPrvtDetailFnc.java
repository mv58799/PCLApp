/*
 * Created on Mar 18, 2007
 *
 */
package com.citibank.ods.modules.product.productfamilyprvt.functionality;

import java.util.Date;

import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.TplProdSubFamlPrvtEntity;
import com.citibank.ods.entity.pl.TplProductFamilyPrvtEntity;
import com.citibank.ods.entity.pl.TplProductFamilyPrvtMovEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplProductFamilyPrvtEntityVO;
import com.citibank.ods.modules.product.productfamilyprvt.functionality.valueobject.BaseProductFamilyPrvtDetailFncVO;
import com.citibank.ods.modules.product.productfamilyprvt.functionality.valueobject.ProductFamilyPrvtDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProductFamilyPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProdSubFamlPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProductFamilyPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProductFamilyPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class ProductFamilyPrvtDetailFnc extends BaseProductFamilyPrvtDetailFnc
    implements ODSDetailFnc
{
  /*
   * Constante fam�lia
   */
  private static final String C_PRODUCT_FAMILY_PRVT_REG = "O registro de fam�lia de produtos";

  /*
   * Sub-Fam�lia
   */
  private static final String C_PROD_SUB_FAML_NAME = "Sub-Fam�lias";

  /**
   * Retorna o DAO utilizado pelo m�todo load da super classe
   * 
   * @see com.citibank.ods.modules.product.productfamilyprvt.functionality.BaseProductFamilyPrvtDetailFnc#getDAO()
   */
  protected BaseTplProductFamilyPrvtDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplProductFamilyPrvtDAO();
  }

  /**
   * Retorna uma inst�ncia do FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new ProductFamilyPrvtDetailFncVO();
  }

  /**
   * Insere os dados de uma Fam�lia de Produtos.
   */
  public void insert( BaseFncVO fncVO_ )
  {
    validateInsert( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      ProductFamilyPrvtDetailFncVO detailFncVO = ( ProductFamilyPrvtDetailFncVO ) fncVO_;

      TplProductFamilyPrvtEntity tplProductFamilyPrvtEntity = ( TplProductFamilyPrvtEntity ) detailFncVO.getBaseTplProductFamilyPrvtEntity();
      BaseTplProductFamilyPrvtEntityVO familyPrvtEntityVO = tplProductFamilyPrvtEntity.getData();
      familyPrvtEntityVO.setLastUpdDate( new Date() );
      familyPrvtEntityVO.setLastUpdUserId( fncVO_.getLoggedUser() != null
                                                                         ? fncVO_.getLoggedUser().getUserID()
                                                                         : "" );

      TplProductFamilyPrvtMovEntity movEntity = new TplProductFamilyPrvtMovEntity(
                                                                                   tplProductFamilyPrvtEntity,
                                                                                   TplProductFamilyPrvtMovEntity.C_OPERN_CODE_INSERT );
      TplProductFamilyPrvtMovDAO productFamilyPrvtMovDAO = ODSDAOFactory.getInstance().getTplProductFamilyPrvtMovDAO();
      productFamilyPrvtMovDAO.insert( movEntity );
    }
  }

  /**
   * Atualiza os dados de uma Fam�lia de Produtos.
   */
  public void update( BaseFncVO fncVO_ )
  {
    validateUpdate( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      ProductFamilyPrvtDetailFncVO detailFncVO = ( ProductFamilyPrvtDetailFncVO ) fncVO_;

      TplProductFamilyPrvtEntity tplProductFamilyPrvtEntity = ( TplProductFamilyPrvtEntity ) detailFncVO.getBaseTplProductFamilyPrvtEntity();
      BaseTplProductFamilyPrvtEntityVO familyPrvtEntityVO = tplProductFamilyPrvtEntity.getData();
      familyPrvtEntityVO.setLastUpdDate( new Date() );
      familyPrvtEntityVO.setLastUpdUserId( fncVO_.getLoggedUser() != null
                                                                         ? fncVO_.getLoggedUser().getUserID()
                                                                         : "" );

      TplProductFamilyPrvtMovEntity movEntity = new TplProductFamilyPrvtMovEntity(
                                                                                   tplProductFamilyPrvtEntity,
                                                                                   TplProductFamilyPrvtMovEntity.C_OPERN_CODE_UPDATE );
      TplProductFamilyPrvtMovDAO productFamilyPrvtMovDAO = ODSDAOFactory.getInstance().getTplProductFamilyPrvtMovDAO();
      productFamilyPrvtMovDAO.insert( movEntity );
    }
  }

  /**
   * Remove uma Fam�lia de Produtos.
   */
  public void delete( BaseFncVO fncVO_ )
  {
    validateDelete( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      ProductFamilyPrvtDetailFncVO detailFncVO = ( ProductFamilyPrvtDetailFncVO ) fncVO_;

      super.loadProductFamily( detailFncVO );

      TplProductFamilyPrvtEntity tplProductFamilyPrvtEntity = ( TplProductFamilyPrvtEntity ) detailFncVO.getBaseTplProductFamilyPrvtEntity();
      BaseTplProductFamilyPrvtEntityVO familyPrvtEntityVO = tplProductFamilyPrvtEntity.getData();
      familyPrvtEntityVO.setLastUpdDate( new Date() );
      familyPrvtEntityVO.setLastUpdUserId( fncVO_.getLoggedUser() != null
                                                                         ? fncVO_.getLoggedUser().getUserID()
                                                                         : "" );

      TplProductFamilyPrvtMovEntity movEntity = new TplProductFamilyPrvtMovEntity(
                                                                                   tplProductFamilyPrvtEntity,
                                                                                   TplProductFamilyPrvtMovEntity.C_OPERN_CODE_DELETE );
      TplProductFamilyPrvtMovDAO productFamilyPrvtMovDAO = ODSDAOFactory.getInstance().getTplProductFamilyPrvtMovDAO();
      productFamilyPrvtMovDAO.insert( movEntity );
    }
  }

  /**
   * Realiza as valida��es - Insert
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
    ProductFamilyPrvtDetailFncVO detailFncVO = ( ProductFamilyPrvtDetailFncVO ) fncVO_;
    BaseTplProductFamilyPrvtEntityVO familyPrvtEntityVO = detailFncVO.getBaseTplProductFamilyPrvtEntity().getData();

    //  Validar Campos Obrigat�rios
    if ( familyPrvtEntityVO.getProdFamlCode() == null
         || familyPrvtEntityVO.getProdFamlCode().intValue() == 0 )
    {
      fncVO_.addError( ProductFamilyPrvtDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_NAME_CODE );
    }

    if ( familyPrvtEntityVO.getProdFamlName() == null
         || familyPrvtEntityVO.getProdFamlName().equals( "" ) )
    {
      fncVO_.addError( ProductFamilyPrvtDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_NAME );
    }

    // Validar se j� existe um registro com este codigo na "Current",
    // caso os campos obrigat�rios estejam presentes
    if ( !fncVO_.hasErrors() )
    {
      if ( this.existsActive( detailFncVO ) )
      {
        fncVO_.addError( ProductFamilyPrvtDetailFncVO.C_ERROR_DUPLICATE_PK );
      }
    }

    // Validar se j� existe movimento com este codigo, caso os campos
    // obrigat�rios estejam presentes
    if ( this.existsInMovement( detailFncVO ) )
    {
      fncVO_.addError( ProductFamilyPrvtDetailFncVO.C_ERROR_IN_MOVEMENT );
    }
  }

  /**
   * Realiza as valida��es - Update
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    ProductFamilyPrvtDetailFncVO detailFncVO = ( ProductFamilyPrvtDetailFncVO ) fncVO_;
    BaseTplProductFamilyPrvtEntityVO familyPrvtEntityVO = detailFncVO.getBaseTplProductFamilyPrvtEntity().getData();

    //  Validar Campos Obrigat�rios
    if ( familyPrvtEntityVO.getProdFamlCode() == null
         || familyPrvtEntityVO.getProdFamlCode().intValue() == 0 )
    {
      fncVO_.addError( ProductFamilyPrvtDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_NAME_CODE );
    }

    if ( familyPrvtEntityVO.getProdFamlName() == null
         || familyPrvtEntityVO.getProdFamlName().equals( "" ) )
    {
      fncVO_.addError( ProductFamilyPrvtDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_NAME );
    }

    //  Validar se existe um registro com este codigo na "Current",
    // caso os campos obrigat�rios estejam presentes
    if ( !fncVO_.hasErrors() )
    {
      if ( !this.existsActive( detailFncVO ) )
      {
        fncVO_.addError( ProductFamilyPrvtDetailFncVO.C_ERROR_PK_NOT_FOUND );
      }
    }

    // Validar se j� existe movimento com este codigo, caso os campos
    // obrigat�rios estejam presentes
    if ( !fncVO_.hasErrors() )
    {
      if ( this.existsInMovement( detailFncVO ) )
      {
        fncVO_.addError( ProductFamilyPrvtDetailFncVO.C_ERROR_IN_MOVEMENT );
      }
    }
  }

  /**
   * Realiza as valida��es - Delete
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
    ProductFamilyPrvtDetailFncVO detailFncVO = ( ProductFamilyPrvtDetailFncVO ) fncVO_;

    //  Validar se existe um registro com este codigo na "Current",
    // caso os campos obrigat�rios estejam presentes
    if ( !fncVO_.hasErrors() )
    {
      if ( !this.existsActive( detailFncVO ) )
      {
        fncVO_.addError( ProductFamilyPrvtDetailFncVO.C_ERROR_PK_NOT_FOUND );
      }

      if ( this.existsInMovement( detailFncVO ) )
      {
        // Validar se j� existe movimento com este codigo, caso os campos
        // obrigat�rios estejam presentes
        fncVO_.addError( ProductFamilyPrvtDetailFncVO.C_ERROR_IN_MOVEMENT );
      }

      // Verifica se algu�m deste registro
      if ( this.existsDependency( detailFncVO ) )
      {
        fncVO_.addError( ProductFamilyPrvtDetailFncVO.C_ERROR_IS_REFERENCED,
                         C_PRODUCT_FAMILY_PRVT_REG, C_PROD_SUB_FAML_NAME );
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
    ProductFamilyPrvtDetailFncVO detailFncVO = ( ProductFamilyPrvtDetailFncVO ) fncVO_;
    detailFncVO.getBaseTplProductFamilyPrvtEntity().getData().setProdFamlCode(
                                                                               null );
    detailFncVO.getBaseTplProductFamilyPrvtEntity().getData().setProdFamlName(
                                                                               null );
    detailFncVO.getBaseTplProductFamilyPrvtEntity().getData().setProdFamlText(
                                                                               null );
  }

  /**
   * Carregamentos iniciais - Update
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    ProductFamilyPrvtDetailFncVO detailFncVO = ( ProductFamilyPrvtDetailFncVO ) fncVO_;

    // 	Validar se j� existe movimento com este codigo, caso os campos
    // obrigat�rios estejam presentes
    if ( this.existsInMovement( detailFncVO ) )
    {
      fncVO_.addError( ProductFamilyPrvtDetailFncVO.C_ERROR_IN_MOVEMENT );
    }
    else
    {
      super.loadProductFamily( ( BaseProductFamilyPrvtDetailFncVO ) fncVO_ );
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
   * Verifica se existe um registro na tabela de movimento
   */
  private boolean existsInMovement( ProductFamilyPrvtDetailFncVO detailFncVO_ )
  {
    TplProductFamilyPrvtMovDAO familyPrvtMovDAO = ODSDAOFactory.getInstance().getTplProductFamilyPrvtMovDAO();

    TplProductFamilyPrvtMovEntity familyPrvtMovEntity = new TplProductFamilyPrvtMovEntity();
    familyPrvtMovEntity.getData().setProdFamlCode(
                                                   detailFncVO_.getBaseTplProductFamilyPrvtEntity().getData().getProdFamlCode() );

    return familyPrvtMovDAO.exists( familyPrvtMovEntity );
  }

  /*
   * Verifica se j� existe um registro na tabela de "Current" com o c�digo
   * passado e o seu status � "Ativo"
   */
  private boolean existsActive( ProductFamilyPrvtDetailFncVO detailFncVO_ )
  {
    TplProductFamilyPrvtDAO familyPrvtDAO = ODSDAOFactory.getInstance().getTplProductFamilyPrvtDAO();
    return familyPrvtDAO.existsActive( ( TplProductFamilyPrvtEntity ) detailFncVO_.getBaseTplProductFamilyPrvtEntity() );
  }

  /*
   * Verifica se existe alguma sub-fam�lia com status ativo, que utiliza esta
   * fam�lia
   */
  private boolean existsDependency( ProductFamilyPrvtDetailFncVO detailFncVO_ )
  {
    // Recupera a inst�ncia do DAO
    TplProdSubFamlPrvtDAO prodSubFamlPrvtDAO = ODSDAOFactory.getInstance().getTplProductSubFamilyPrvtDAO();

    // Cria uma entity de sub-fam�lia setando o id da fam�lia
    TplProdSubFamlPrvtEntity subFamlEntity = new TplProdSubFamlPrvtEntity();
    subFamlEntity.getData().setProdFamlCode(
                                             detailFncVO_.getBaseTplProductFamilyPrvtEntity().getData().getProdFamlCode() );

    // Verifica se existe alguma dependencia
    return prodSubFamlPrvtDAO.existsProductFamilyPrvtDependency( subFamlEntity );
  }
}