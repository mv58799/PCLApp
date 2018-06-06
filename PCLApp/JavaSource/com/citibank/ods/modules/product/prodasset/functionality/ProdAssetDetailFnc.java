/*
 * Created on 20/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodasset.functionality;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.TplProdAssetEntity;
import com.citibank.ods.entity.pl.TplProdAssetMovEntity;
import com.citibank.ods.modules.product.prodasset.functionality.valueobject.ProdAssetDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdAssetDAO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetDAO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetMovDAO;
import com.citibank.ods.persistence.pl.dao.TplProdSubAssetDAO;
import com.citibank.ods.persistence.pl.dao.TplProductDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProdAssetDetailFnc extends BaseProdAssetDetailFnc implements
	ODSDetailFnc
{

  private static final String C_PROD_Asset = "Sub Classe";

  private static final String C_PRODUCTS = "Produtos";
  
  private static final String C_ASSET_CLASS_REG = "O registro de Classe de Ativo";
  
  private static final String C_SUB_ASSET_CLASS_REG = "Sub Classe de Ativo";

  /**
   * Insere um registro de agregador de produto
   */
  public void insert( BaseFncVO fncVO_ )
  {
	ProdAssetDetailFncVO detailFncVO = ( ProdAssetDetailFncVO ) fncVO_;
	validateInsert( detailFncVO );
	if ( !detailFncVO.hasErrors() )
	{
	  TplProdAssetEntity prodAssetEntity = ( TplProdAssetEntity ) detailFncVO.getBaseTplProdAssetEntity();
	  prodAssetEntity.getData().setLastUpdDate( new Date() );
	  prodAssetEntity.getData().setLastUpdUserId(
													 fncVO_.getLoggedUser() != null
																				   ? fncVO_.getLoggedUser().getUserID()
																				   : "" );

	  TplProdAssetMovEntity prvtMovEntity = new TplProdAssetMovEntity(
																			 prodAssetEntity,
																			 TplProdAssetMovEntity.C_OPERN_CODE_INSERT );
	  TplProdAssetMovDAO tplProdAssetMovDAO = ODSDAOFactory.getInstance().getTplProdAssetMovDAO();
	  tplProdAssetMovDAO.insert( prvtMovEntity );
	}

  }

  /**
   * Atualiza um registro de agregador de produto
   */
  public void update( BaseFncVO fncVO_ )
  {
	ProdAssetDetailFncVO detailFncVO = ( ProdAssetDetailFncVO ) fncVO_;
	validateUpdate( detailFncVO );
	if ( !detailFncVO.hasErrors() )
	{

	  //createMovementRecord
	  TplProdAssetEntity prodAssetEntity = ( TplProdAssetEntity ) detailFncVO.getBaseTplProdAssetEntity();
	  prodAssetEntity.getData().setLastUpdDate( new Date() );
	  prodAssetEntity.getData().setLastUpdUserId(
													 fncVO_.getLoggedUser() != null
																				   ? fncVO_.getLoggedUser().getUserID()
																				   : "" );

	  TplProdAssetMovEntity prvtMovEntity = new TplProdAssetMovEntity(
																			 prodAssetEntity,
																			 TplProdAssetMovEntity.C_OPERN_CODE_UPDATE );
	  TplProdAssetMovDAO tplProdAssetMovDAO = ODSDAOFactory.getInstance().getTplProdAssetMovDAO();
	  tplProdAssetMovDAO.insert( prvtMovEntity );

	}

  }

  /**
   * Remove um registro de agregador de produto
   */
  public void delete( BaseFncVO fncVO_ )
  {
	ProdAssetDetailFncVO detailFncVO = ( ProdAssetDetailFncVO ) fncVO_;

	validateDelete( detailFncVO );
	if ( !detailFncVO.hasErrors() )
	{

	  TplProdAssetDAO tplProdAssetDAO = ODSDAOFactory.getInstance().getTplProdAssetDAO();
	  TplProdAssetEntity prodAssetEntity = ( TplProdAssetEntity ) tplProdAssetDAO.find( detailFncVO.getBaseTplProdAssetEntity() );

	  TplProdAssetMovEntity prvtMovEntity = new TplProdAssetMovEntity(
																			 prodAssetEntity,
																			 TplProdAssetMovEntity.C_OPERN_CODE_DELETE );

	  prvtMovEntity.getData().setLastUpdDate( new Date() );
	  prodAssetEntity.getData().setLastUpdUserId(
													 fncVO_.getLoggedUser() != null
																				   ? fncVO_.getLoggedUser().getUserID()
																				   : "" );

	  TplProdAssetMovDAO tplProdAssetMovDAO = ODSDAOFactory.getInstance().getTplProdAssetMovDAO();
	  tplProdAssetMovDAO.insert( prvtMovEntity );

	}

  }

  /**
   * Realiza as validações - INSERT
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
	ProdAssetDetailFncVO prodAssetDetailFncVO = ( ProdAssetDetailFncVO ) fncVO_;

	// Validar Campos Obrigatórios
	if ( prodAssetDetailFncVO.getBaseTplProdAssetEntity().getData().getProdAssetText() == null
		 || prodAssetDetailFncVO.getBaseTplProdAssetEntity().getData().getProdAssetText().equals(
																									   "" ) )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_PROD_ASSET_TEXT );
	}
  }

  /**
   * Realiza as validações - UPDATE
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
	ProdAssetDetailFncVO prodAssetDetailFncVO = ( ProdAssetDetailFncVO ) fncVO_;

	//  Validar Campos Obrigatórios
	if ( prodAssetDetailFncVO.getBaseTplProdAssetEntity().getData().getProdAssetText() == null
		 || prodAssetDetailFncVO.getBaseTplProdAssetEntity().getData().getProdAssetText().equals(""))
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_PROD_ASSET_TEXT );
	}

	// Verifica se já existe algum registro com status ativo
	if ( !existsActive( prodAssetDetailFncVO ) )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_DUPLICATE_PK );
	}
  }

  /**
   * Realiza as validações - DELETE
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
	ProdAssetDetailFncVO prodAssetDetailFncVO = ( ProdAssetDetailFncVO ) fncVO_;

	// Verifica se o registro está ativo
	if ( !existsActive( prodAssetDetailFncVO ) )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_PK_NOT_FOUND );
	}

	// Validar se já existe movimento com este codigo, caso os campos
	// obrigatórios estejam presentes
	if ( !fncVO_.hasErrors() )
	{
	  if ( this.existsInMovement( prodAssetDetailFncVO ) )
	  {
		fncVO_.addError( BaseODSFncVO.C_ERROR_IN_MOVEMENT );
	  }
	}
    
	//Valida se alguma Sub Classe de Ativo está utilizando a Classe de Ativo a ser deletada
	if ( !fncVO_.hasErrors() )
	{
	  if ( this.existsSubAssetActive( prodAssetDetailFncVO.getBaseTplProdAssetEntity().getData().getProdAssetCode() ) )
	  {
		fncVO_.addError( BaseODSFncVO.C_ERROR_IS_REFERENCED,C_ASSET_CLASS_REG,C_SUB_ASSET_CLASS_REG);
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
	ProdAssetDetailFncVO prodAssetDetailFncVO = ( ProdAssetDetailFncVO ) fncVO_;
	prodAssetDetailFncVO.getBaseTplProdAssetEntity().getData().setProdAssetCode(null );
	prodAssetDetailFncVO.getBaseTplProdAssetEntity().getData().setProdAssetText(null );
	prodAssetDetailFncVO.getBaseTplProdAssetEntity().getData().setAssetClassCustRptOrderNbr(null);
	
  }

  /**
   * Carregamentos iniciais - Update
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
	ProdAssetDetailFncVO prodAssetDetailFncVO = ( ProdAssetDetailFncVO ) fncVO_;
	// Verifica se o registro está ativo
	if ( !this.existsActive( prodAssetDetailFncVO ) )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_REG_NOT_EXISTS,
					   ProdAssetDetailFncVO.C_PROD_ASSET_CODE_DESCRIPTION );
	}	
	else{	
		if(!this.existsInMovement(prodAssetDetailFncVO))
		{		
			prodAssetDetailFncVO.setBaseTplProdAssetEntity(ODSDAOFactory.getInstance().getTplProdAssetDAO().find(prodAssetDetailFncVO.getBaseTplProdAssetEntity()));
			
		}
		else
		{
			fncVO_.addError( ProdAssetDetailFncVO.C_ERROR_IN_MOVEMENT,
								   ProdAssetDetailFncVO.C_PROD_ASSET_CODE_DESCRIPTION );
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
	return new ProdAssetDetailFncVO();
  }

  /**
   * Retorna a instancia da DAO utilizado por este Fnc
   */
  protected BaseTplProdAssetDAO getDAO()
  {
	return ODSDAOFactory.getInstance().getTplProdAssetMovDAO();
  }

  /*
   * Verifica se existe um registro na tabela de movimento com os critérios
   * passados
   * 
   * @see com.citibank.ods.modules.product.prodriskcatprvt.functionality.BaseProdRiskCatPrvtDetailFnc#getDAO()
   */
  private boolean existsInMovement(
								   ProdAssetDetailFncVO prodAssetDetailFncVO_ )
  {
	TplProdAssetMovDAO tplProdAssetMovDAO = ODSDAOFactory.getInstance().getTplProdAssetMovDAO();

	TplProdAssetMovEntity prvtMovEntity = new TplProdAssetMovEntity();
	prvtMovEntity.getData().setProdAssetCode(
											 prodAssetDetailFncVO_.getBaseTplProdAssetEntity().getData().getProdAssetCode() );

	return tplProdAssetMovDAO.exists( prvtMovEntity );
  }

  /*
   * Verifica se existe algum registro com a chave passada e seu status é ativo
   */
  private boolean existsActive( ProdAssetDetailFncVO prodAssetDetailFncVO_ )
  {
	TplProdAssetDAO tplProdAssetDAO = ODSDAOFactory.getInstance().getTplProdAssetDAO();
	return tplProdAssetDAO.existsActive( ( TplProdAssetEntity ) prodAssetDetailFncVO_.getBaseTplProdAssetEntity() );
  }
  
  /*
   * Verifica se existe uma SubAsset ativo que utilize a AssetClass passada como parametro
   */
  private boolean existsSubAssetActive( BigInteger prodAssetCode_ )
  {	
	TplProdSubAssetDAO tplProdSubAssetDAO = ODSDAOFactory.getInstance().getTplProdSubAssetDAO();
	return tplProdSubAssetDAO.existsSubAssetByForeignKey(prodAssetCode_);
  }

}
