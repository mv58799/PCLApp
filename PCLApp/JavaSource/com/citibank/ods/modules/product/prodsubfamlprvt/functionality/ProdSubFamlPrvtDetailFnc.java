/*
 * Created on Mar 18, 2007
 *
 */
package com.citibank.ods.modules.product.prodsubfamlprvt.functionality;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.TplProdSubFamlPrvtEntity;
import com.citibank.ods.entity.pl.TplProdSubFamlPrvtMovEntity;
import com.citibank.ods.entity.pl.TplProductFamilyPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplProdSubFamlPrvtEntityVO;
import com.citibank.ods.modules.product.prodsubfamlprvt.functionality.valueobject.ProdSubFamlPrvtDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdSubFamlPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProdSubFamlPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProdSubFamlPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.TplProductDAO;
import com.citibank.ods.persistence.pl.dao.TplProductFamilyPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class ProdSubFamlPrvtDetailFnc extends BaseProdSubFamlPrvtDetailFnc
    implements ODSDetailFnc
{
  /*
   * Constante que identifica a inserção
   */
  private static final String C_INSERT_OPERATION = "Inserção";

  private static final String C_PROD_SUB_FAML = "Sub-Família de Produto";

  private static final String C_PRODUCTS = "Produtos";

  /**
   * Retorna uma instância do FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new ProdSubFamlPrvtDetailFncVO();
  }

  /**
   * Insere os dados de uma Sub Família de Produtos.
   */
  public void insert( BaseFncVO fncVO_ )
  {
    validateInsert( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      this.insertInMovement( fncVO_,
                             TplProdSubFamlPrvtEntity.C_OPERN_CODE_INSERT );
    }
  }

  /**
   * Altera os dados de uma Sub Família de Produtos.
   */
  public void update( BaseFncVO fncVO_ )
  {
    validateUpdate( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      this.insertInMovement( fncVO_,
                             TplProdSubFamlPrvtEntity.C_OPERN_CODE_UPDATE );
    }
  }

  /**
   * Remove uma Sub Família de Produtos.
   */
  public void delete( BaseFncVO fncVO_ )
  {
    validateDelete( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      ProdSubFamlPrvtDetailFncVO detailFncVO = ( ProdSubFamlPrvtDetailFncVO ) fncVO_;
      super.loadProductSubFamily( detailFncVO );
      this.insertInMovement( fncVO_,
                             TplProdSubFamlPrvtEntity.C_OPERN_CODE_DELETE );
    }
  }

  /*
   * Método genérico utilizado para inserir um elemento na tabela de movimento
   */
  private void insertInMovement( BaseFncVO fncVO_, String opernCode_ )
  {
    ProdSubFamlPrvtDetailFncVO detailFncVO = ( ProdSubFamlPrvtDetailFncVO ) fncVO_;
    TplProdSubFamlPrvtEntity subFamlPrvtEntity = ( TplProdSubFamlPrvtEntity ) detailFncVO.getBaseTplProductSubFamilyPrvtEntity();
    BaseTplProdSubFamlPrvtEntityVO subFamlPrvtEntityVO = subFamlPrvtEntity.getData();

    subFamlPrvtEntityVO.setLastUpdDate( new Date() );
    subFamlPrvtEntityVO.setLastUpdUserId( fncVO_.getLoggedUser() != null
                                                                        ? fncVO_.getLoggedUser().getUserID()
                                                                        : "" );

    TplProdSubFamlPrvtMovEntity subFamlPrvtMovEntity = new TplProdSubFamlPrvtMovEntity(
                                                                                        subFamlPrvtEntity,
                                                                                        opernCode_ );
    TplProdSubFamlPrvtMovDAO famlPrvtMovDAO = ODSDAOFactory.getInstance().getTplProductSubFamilyPrvtMovDAO();
    famlPrvtMovDAO.insert( subFamlPrvtMovEntity );
  }

  /**
   * Validação de Insert
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
    ProdSubFamlPrvtDetailFncVO detailFncVO = ( ProdSubFamlPrvtDetailFncVO ) fncVO_;
    BaseTplProdSubFamlPrvtEntityVO subFamlPrvtEntityVO = detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData();

    //  Validar Campos Obrigatórios
    if ( subFamlPrvtEntityVO.getProdSubFamlCode() == null
         || subFamlPrvtEntityVO.getProdSubFamlCode().intValue() == 0 )
    {
      fncVO_.addError(
                       ProdSubFamlPrvtDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProdSubFamlPrvtDetailFncVO.C_PROD_SUB_FAML_CODE_DESCRIPTION );
    }

    if ( subFamlPrvtEntityVO.getProdSubFamlName() == null
         || subFamlPrvtEntityVO.getProdSubFamlName().equals( "" ) )
    {
      fncVO_.addError(
                       ProdSubFamlPrvtDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProdSubFamlPrvtDetailFncVO.C_PROD_SUB_FAML_NAME_DESCRIPTION );
    }

    if ( subFamlPrvtEntityVO.getProdFamlCode() == null
         || subFamlPrvtEntityVO.getProdFamlCode().intValue() == 0 )
    {
      fncVO_.addError( ProdSubFamlPrvtDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProdSubFamlPrvtDetailFncVO.C_PROD_FAML_CODE_DESCRIPTION );
    }

    //  Validar se já existe um registro com este codigo na "Current",
    // caso os campos obrigatórios estejam presentes
    if ( !fncVO_.hasErrors() )
    {
      if ( this.existsActive( detailFncVO ) )
      {
        fncVO_.addError( ProdSubFamlPrvtDetailFncVO.C_ERROR_DUPLICATE_PK );
      }

      //  Validar se já existe movimento com este codigo
      if ( this.existsInMovement( detailFncVO ) )
      {
        fncVO_.addError( ProdSubFamlPrvtDetailFncVO.C_ERROR_IN_MOVEMENT );
      }

      // Validar se a família de produtos passada existe e é válida
      if ( !this.existsProductFamilyPrvtActive( detailFncVO ) )
      {
        fncVO_.addError(
                         ProdSubFamlPrvtDetailFncVO.C_INVALID_FK,
                         C_INSERT_OPERATION,
                         ProdSubFamlPrvtDetailFncVO.C_PROD_FAML_CODE_DESCRIPTION );
      }
    }
  }

  /**
   * Validação de Update
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    ProdSubFamlPrvtDetailFncVO detailFncVO = ( ProdSubFamlPrvtDetailFncVO ) fncVO_;
    BaseTplProdSubFamlPrvtEntityVO subFamlPrvtEntityVO = detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData();

    //  Validar Campos Obrigatórios
    if ( subFamlPrvtEntityVO.getProdSubFamlCode() == null
         || subFamlPrvtEntityVO.getProdSubFamlCode().intValue() == 0 )
    {
      fncVO_.addError(
                       ProdSubFamlPrvtDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProdSubFamlPrvtDetailFncVO.C_PROD_SUB_FAML_CODE_DESCRIPTION );
    }

    if ( subFamlPrvtEntityVO.getProdSubFamlName() == null
         || subFamlPrvtEntityVO.getProdSubFamlName().equals( "" ) )
    {
      fncVO_.addError(
                       ProdSubFamlPrvtDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProdSubFamlPrvtDetailFncVO.C_PROD_SUB_FAML_NAME_DESCRIPTION );
    }

    if ( subFamlPrvtEntityVO.getProdFamlCode() == null
         || subFamlPrvtEntityVO.getProdFamlCode().intValue() == 0 )
    {
      fncVO_.addError( ProdSubFamlPrvtDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProdSubFamlPrvtDetailFncVO.C_PROD_FAML_CODE_DESCRIPTION );
    }

    //  caso os campos obrigatórios estejam presentes
    if ( !fncVO_.hasErrors() )
    {
      if ( !this.existsActive( detailFncVO ) )
      {
        fncVO_.addError( ProdSubFamlPrvtDetailFncVO.C_ERROR_PK_NOT_FOUND );
      }

      //  Validar se já existe movimento com este codigo
      if ( this.existsInMovement( detailFncVO ) )
      {
        fncVO_.addError( ProdSubFamlPrvtDetailFncVO.C_ERROR_IN_MOVEMENT );
      }
    }
  }

  /**
   * Validação de Delete
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
    ProdSubFamlPrvtDetailFncVO detailFncVO = ( ProdSubFamlPrvtDetailFncVO ) fncVO_;
    //  caso os campos obrigatórios estejam presentes
    if ( !fncVO_.hasErrors() )
    {
      if ( !this.existsActive( detailFncVO ) )
      {
        fncVO_.addError( ProdSubFamlPrvtDetailFncVO.C_ERROR_PK_NOT_FOUND );
      }

      //  Validar se já existe movimento com este codigo
      if ( this.existsInMovement( detailFncVO ) )
      {
        fncVO_.addError( ProdSubFamlPrvtDetailFncVO.C_ERROR_IN_MOVEMENT );
      }
    }

    //  Valida se algum produto ativo esta utilizando esta sub-família
    if ( !fncVO_.hasErrors() )
    {
      if ( this.existsProductActive( detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().getProdSubFamlCode() ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_IS_REFERENCED, C_PROD_SUB_FAML,
                         C_PRODUCTS );
      }
    }
  }

  /**
   * Carregamentos iniciais - Tela de detalhe
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
    ProdSubFamlPrvtDetailFncVO detailFncVO = ( ProdSubFamlPrvtDetailFncVO ) fncVO_;

    detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().setProdSubFamlCode(
                                                                                     null );
    detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().setProdSubFamlName(
                                                                                     null );
    detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().setProdSubFamlText(
                                                                                     null );
    detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().setProdFamlCode(
                                                                                  null );

    super.loadDomains( detailFncVO );
  }

  /**
   * Carregamentos iniciais - Update
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    ProdSubFamlPrvtDetailFncVO detailFncVO = ( ProdSubFamlPrvtDetailFncVO ) fncVO_;

    // 	Validar se já existe movimento com este codigo, caso os campos
    // obrigatórios estejam presentes
    if ( this.existsInMovement( detailFncVO ) )
    {
      fncVO_.addError( ProdSubFamlPrvtDetailFncVO.C_ERROR_IN_MOVEMENT );
    }
    else
    {
      super.loadDomains( detailFncVO );
      super.loadProductSubFamily( detailFncVO );
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
   * Retorna o DAO utilizado pelo método load da super classe
   */
  protected BaseTplProdSubFamlPrvtDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplProductSubFamilyPrvtDAO();
  }

  /**
   * Verifica se existe um registro na tabela de movimento
   */
  private boolean existsInMovement( ProdSubFamlPrvtDetailFncVO detailFncVO_ )
  {
    TplProdSubFamlPrvtMovDAO familyPrvtMovDAO = ODSDAOFactory.getInstance().getTplProductSubFamilyPrvtMovDAO();

    TplProdSubFamlPrvtMovEntity familyPrvtMovEntity = new TplProdSubFamlPrvtMovEntity();
    familyPrvtMovEntity.getData().setProdSubFamlCode(
                                                      detailFncVO_.getBaseTplProductSubFamilyPrvtEntity().getData().getProdSubFamlCode() );

    return familyPrvtMovDAO.exists( familyPrvtMovEntity );
  }

  /*
   * Verifica se já existe um registro na tabela de "Current" com o código
   * passado e o seu status é "Ativo"
   */
  private boolean existsActive( ProdSubFamlPrvtDetailFncVO detailFncVO_ )
  {
    TplProdSubFamlPrvtDAO familyPrvtDAO = ODSDAOFactory.getInstance().getTplProductSubFamilyPrvtDAO();
    return familyPrvtDAO.existsActive( ( TplProdSubFamlPrvtEntity ) detailFncVO_.getBaseTplProductSubFamilyPrvtEntity() );
  }

  /*
   * Verifica se existe um produto ativo que utilize a sub-família passada como
   * parametro
   */
  private boolean existsProductActive( BigInteger prodSubFamlCode_ )
  {
    TplProductDAO tplProductDAO = ODSDAOFactory.getInstance().getTplProductDAO();
    return tplProductDAO.existsProductByForeignKey( null, null, null,
                                                    prodSubFamlCode_,null );
  }

  /*
   * @author leonardo.nakada Verifica se a família de produtos passada é valida
   */
  private boolean existsProductFamilyPrvtActive(
                                                ProdSubFamlPrvtDetailFncVO detailFncVO_ )
  {
    TplProductFamilyPrvtDAO familyPrvtDAO = ODSDAOFactory.getInstance().getTplProductFamilyPrvtDAO();
    TplProductFamilyPrvtEntity entity = new TplProductFamilyPrvtEntity();
    entity.getData().setProdFamlCode(
                                      detailFncVO_.getBaseTplProductSubFamilyPrvtEntity().getData().getProdFamlCode() );
    return familyPrvtDAO.existsActive( entity );
  }
}