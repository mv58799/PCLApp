/*
 * Created on Mar 12, 2007
 *
 */
package com.citibank.ods.modules.product.aggrprodprvt.functionality;

import java.util.Date;

import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.TplAggrProdPrvtEntity;
import com.citibank.ods.entity.pl.TplAggrProdPrvtHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplAggrProdPrvtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplAggrProdPrvtHistEntityVO;
import com.citibank.ods.modules.product.aggrprodprvt.functionality.valueobject.AggrProdPrvtDetailFncVO;
import com.citibank.ods.modules.product.aggrprodprvt.functionality.valueobject.BaseAggrProdPrvtDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplAggrProdPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplAggrProdPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplAggrProdPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.TplProductDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class AggrProdPrvtDetailFnc extends BaseAggrProdPrvtDetailFnc implements
    ODSDetailFnc
{

  private static final String C_PROD_AGGR = "Agregador de Produto";

  private static final String C_PRODUCTS = "Produtos";

  /**
   * Realiza a inserção do agregador de Produtos
   */
  public void insert( BaseFncVO fncVO_ )
  {
    this.validateInsert( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      AggrProdPrvtDetailFncVO productAggrDetailFncVO = ( AggrProdPrvtDetailFncVO ) fncVO_;
      TplAggrProdPrvtEntity tplAggrProdPrvtEntity = ( TplAggrProdPrvtEntity ) productAggrDetailFncVO.getAggrProdPrvtEntity();

      // Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();

      TplAggrProdPrvtDAO aggrProdPrvtDAO = factory.getTplAggrProdPrvtDAO();
      tplAggrProdPrvtEntity.getData().setRecStatCode(
                                                      TplAggrProdPrvtEntity.C_REC_STAT_CODE_ACTIVE );
      tplAggrProdPrvtEntity.getData().setLastUpdDate( new Date() );
      tplAggrProdPrvtEntity.getData().setLastUpdUserID(
                                                        fncVO_.getLoggedUser() != null
                                                                                      ? fncVO_.getLoggedUser().getUserID()
                                                                                      : "" );
      if ( !exists( productAggrDetailFncVO ) )
      {
        aggrProdPrvtDAO.insert( tplAggrProdPrvtEntity );
      }
      else
      {
        // Copia registro para o histórico
        TplAggrProdPrvtHistEntity histEntity = new TplAggrProdPrvtHistEntity(
                                                                              tplAggrProdPrvtEntity,
                                                                              new Date() );

        TplAggrProdPrvtHistDAO tplAggrProdPrvtHistDAO = factory.getTplAggrProdPrvtHistDAO();
        tplAggrProdPrvtHistDAO.insert( histEntity );

        // Atualiza o registro
        aggrProdPrvtDAO.update( tplAggrProdPrvtEntity );
      }
    }
  }

  /**
   * Realiza a alteracao do agregador de Produtos
   */
  public void update( BaseFncVO fncVO_ )
  {
    this.validateUpdate( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {

      AggrProdPrvtDetailFncVO productAggrDetailFncVO = ( AggrProdPrvtDetailFncVO ) fncVO_;
      TplAggrProdPrvtEntity tplAggrProdPrvtEntity = ( TplAggrProdPrvtEntity ) productAggrDetailFncVO.getAggrProdPrvtEntity();

      // Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();

      TplAggrProdPrvtDAO aggrProdPrvtDAO = factory.getTplAggrProdPrvtDAO();
      TplAggrProdPrvtEntity tplAggrProdPrvtEntityOLD = ( TplAggrProdPrvtEntity ) aggrProdPrvtDAO.find( tplAggrProdPrvtEntity );

      // Copia o registro para o histórico
      TplAggrProdPrvtHistDAO aggrProdPrvtHistDAO = factory.getTplAggrProdPrvtHistDAO();
      TplAggrProdPrvtHistEntity tplAggrProdPrvtHistEntity = new TplAggrProdPrvtHistEntity(
                                                                                           tplAggrProdPrvtEntityOLD,
                                                                                           new Date() );
      ( ( TplAggrProdPrvtHistEntityVO ) tplAggrProdPrvtHistEntity.getData() ).setPrvtProdAggrRefDate( new Date() );
      aggrProdPrvtHistDAO.insert( tplAggrProdPrvtHistEntity );

      // Atualiza o usuário que está realizando a operação
      tplAggrProdPrvtEntity.getData().setLastUpdUserID(
                                                        fncVO_.getLoggedUser() != null
                                                                                      ? fncVO_.getLoggedUser().getUserID()
                                                                                      : "" );
      tplAggrProdPrvtEntity.getData().setLastUpdDate( new Date() );

      // Atualiza o registro na tabela
      aggrProdPrvtDAO.update( tplAggrProdPrvtEntity );
    }
  }

  /**
   * Realiza a delecao do agregador de Produtos
   */
  public void delete( BaseFncVO fncVO_ )
  {
    this.validateDelete( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      AggrProdPrvtDetailFncVO productAggrDetailFncVO = ( AggrProdPrvtDetailFncVO ) fncVO_;
      TplAggrProdPrvtEntity tplAggrProdPrvtEntity = ( TplAggrProdPrvtEntity ) productAggrDetailFncVO.getAggrProdPrvtEntity();

      //Coloca o status como inativo
      tplAggrProdPrvtEntity.getData().setRecStatCode(
                                                      TplAggrProdPrvtEntity.C_REC_STAT_CODE_INACTIVE );

      // Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();

      TplAggrProdPrvtDAO aggrProdPrvtDAO = factory.getTplAggrProdPrvtDAO();
      TplAggrProdPrvtEntity tplAggrProdPrvtEntityOLD = ( TplAggrProdPrvtEntity ) aggrProdPrvtDAO.find( tplAggrProdPrvtEntity );

      //  Copia o registro para o histórico
      TplAggrProdPrvtHistDAO aggrProdPrvtHistDAO = factory.getTplAggrProdPrvtHistDAO();
      TplAggrProdPrvtHistEntity tplAggrProdPrvtHistEntity = new TplAggrProdPrvtHistEntity(
                                                                                           tplAggrProdPrvtEntityOLD,
                                                                                           new Date() );
      ( ( TplAggrProdPrvtHistEntityVO ) tplAggrProdPrvtHistEntity.getData() ).setPrvtProdAggrRefDate( new Date() );
      aggrProdPrvtHistDAO.insert( tplAggrProdPrvtHistEntity );

      //  Atualiza o usuário que está realizando a operação
      tplAggrProdPrvtEntity.getData().setLastUpdUserID(
                                                        fncVO_.getLoggedUser() != null
                                                                                      ? fncVO_.getLoggedUser().getUserID()
                                                                                      : "" );
      tplAggrProdPrvtEntity.getData().setLastUpdDate( new Date() );

      // remove o registro (altera o status para inativo - I)
      aggrProdPrvtDAO.delete( tplAggrProdPrvtEntity );
    }
  }

  /**
   * Carregamentos iniciais - Detalhe
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    // Nao ha carregamento iniciais nesta tela

  }

  /**
   * Carregamentos iniciais - Insert
   */
  public void loadForInsert( BaseFncVO fncVO_ )
  {
    AggrProdPrvtDetailFncVO productAggrDetailFncVO = ( AggrProdPrvtDetailFncVO ) fncVO_;
    productAggrDetailFncVO.getAggrProdPrvtEntity().getData().setPrvtProdAggrCode(
                                                                                  null );
    productAggrDetailFncVO.getAggrProdPrvtEntity().getData().setPrvtProdAggrText(
                                                                                  null );
    ODSDAOFactory factory = ODSDAOFactory.getInstance();

    TplAggrProdPrvtDAO aggrProdPrvtDAO = factory.getTplAggrProdPrvtDAO();
    
    productAggrDetailFncVO.getAggrProdPrvtEntity().getData().setAggrProdPrvtIxCodeDomain(aggrProdPrvtDAO.loadIndexAggrs());
  }

  /**
   * Carregamentos iniciais - Update
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
	  BaseAggrProdPrvtDetailFncVO fnc = ( BaseAggrProdPrvtDetailFncVO ) fncVO_;
    super.load( fnc );
    ODSDAOFactory factory = ODSDAOFactory.getInstance();

    TplAggrProdPrvtDAO aggrProdPrvtDAO = factory.getTplAggrProdPrvtDAO();
    
    fnc.getAggrProdPrvtEntity().getData().setAggrProdPrvtIxCodeDomain(aggrProdPrvtDAO.loadIndexAggrs());
    
  }

  /**
   * Carregamentos iniciais - Delete
   */
  public void loadForDelete( BaseFncVO fncVO_ )
  {
    // Nao ha carregamento iniciais nesta tela
  }

  /**
   * Retorna uma instancia do FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new AggrProdPrvtDetailFncVO();
  }

  /**
   * Retorna uma instancia do DAO
   */
  protected BaseTplAggrProdPrvtDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplAggrProdPrvtDAO();
  }

  /**
   * Realiza as validações de Insert
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
    AggrProdPrvtDetailFncVO productAggrDetailFncVO = ( AggrProdPrvtDetailFncVO ) fncVO_;

    // Validar campos obrigatórios
    TplAggrProdPrvtEntityVO prvtEntityVO = ( TplAggrProdPrvtEntityVO ) productAggrDetailFncVO.getAggrProdPrvtEntity().getData();
    if ( prvtEntityVO.getPrvtProdAggrCode() == null
         || prvtEntityVO.getPrvtProdAggrCode().equals( "" ) )
    {
      fncVO_.addError( AggrProdPrvtDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_NAME_CODE );
    }

    if ( prvtEntityVO.getPrvtProdAggrText() == null
         || prvtEntityVO.getPrvtProdAggrText().equals( "" ) )
    {
      fncVO_.addError( AggrProdPrvtDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_NAME_TEXT );
    }

    // Verifica se já existe algum registro com status ativo
    if ( existsActive( productAggrDetailFncVO ) )
    {
      fncVO_.addError( AggrProdPrvtDetailFncVO.C_ERROR_DUPLICATE_PK );
    }
  }

  /*
   * Verifica se existe algum registro com a chave passada
   */
  private boolean exists( AggrProdPrvtDetailFncVO productAggrDetailFncVO )
  {
    TplAggrProdPrvtDAO tplAggrProdPrvtDAO = ODSDAOFactory.getInstance().getTplAggrProdPrvtDAO();
    return tplAggrProdPrvtDAO.exists( ( TplAggrProdPrvtEntity ) productAggrDetailFncVO.getAggrProdPrvtEntity() );
  }

  /*
   * Verifica se existe algum registro com a chave passada e seu status é ativo
   */
  private boolean existsActive( AggrProdPrvtDetailFncVO productAggrDetailFncVO )
  {
    TplAggrProdPrvtDAO tplAggrProdPrvtDAO = ODSDAOFactory.getInstance().getTplAggrProdPrvtDAO();
    return tplAggrProdPrvtDAO.existsActive( ( TplAggrProdPrvtEntity ) productAggrDetailFncVO.getAggrProdPrvtEntity() );
  }

  /**
   * Realiza as validações para o update
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    AggrProdPrvtDetailFncVO productAggrDetailFncVO = ( AggrProdPrvtDetailFncVO ) fncVO_;

    // Validar campos obrigatórios
    TplAggrProdPrvtEntityVO prvtEntityVO = ( TplAggrProdPrvtEntityVO ) productAggrDetailFncVO.getAggrProdPrvtEntity().getData();
    if ( prvtEntityVO.getPrvtProdAggrCode() == null
         || prvtEntityVO.getPrvtProdAggrCode().equals( "" ) )
    {
      fncVO_.addError( AggrProdPrvtDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_NAME_CODE );
    }

    if ( prvtEntityVO.getPrvtProdAggrText() == null
         || prvtEntityVO.getPrvtProdAggrText().equals( "" ) )
    {
      fncVO_.addError( AggrProdPrvtDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_NAME_TEXT );
    }

    if ( !fncVO_.hasErrors() )
    {
      if ( !existsActive( productAggrDetailFncVO ) )
      {
        fncVO_.addError( AggrProdPrvtDetailFncVO.C_ERROR_PK_NOT_FOUND );
      }
    }
  }

  /**
   * Realiza as validações para o delete
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
    AggrProdPrvtDetailFncVO productAggrDetailFncVO = ( AggrProdPrvtDetailFncVO ) fncVO_;

    if ( !fncVO_.hasErrors() )
    {
      if ( !existsActive( productAggrDetailFncVO ) )
      {
        fncVO_.addError( AggrProdPrvtDetailFncVO.C_ERROR_PK_NOT_FOUND );
      }
    }

    //  Valida se algum produto ativo esta utilizando este agregador de
    // produto
    if ( !fncVO_.hasErrors() )
    {
      if ( this.existsProductActive( productAggrDetailFncVO.getAggrProdPrvtEntity().getData().getPrvtProdAggrCode() ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_IS_REFERENCED, C_PROD_AGGR,
                         C_PRODUCTS );
      }
    }
  }
  
  /*
   * Verifica se existe um produto ativo que utilize o agregador de produto passado como parametro
   */
  private boolean existsProductActive( String prodAggrCode_ )
  {
    TplProductDAO tplProductDAO = ODSDAOFactory.getInstance().getTplProductDAO();
    return tplProductDAO.existsProductByForeignKey( prodAggrCode_, null, null,
                                                    null,null );
  }
}