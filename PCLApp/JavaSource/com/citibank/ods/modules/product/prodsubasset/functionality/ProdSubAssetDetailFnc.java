/*
 * Created on 20/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodsubasset.functionality;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.TplProdSubAssetEntity;
import com.citibank.ods.entity.pl.TplProdSubAssetMovEntity;
import com.citibank.ods.modules.product.prodsubasset.functionality.valueobject.ProdSubAssetDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdSubAssetDAO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetTypeDAO;
import com.citibank.ods.persistence.pl.dao.TplProdSubAssetDAO;
import com.citibank.ods.persistence.pl.dao.TplProdSubAssetMovDAO;
import com.citibank.ods.persistence.pl.dao.TplProductDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProdSubAssetDetailFnc extends BaseProdSubAssetDetailFnc implements
	ODSDetailFnc
{

  private static final String C_PROD_SubAsset = "Sub Classe";

  private static final String C_TIPO_ATIVO = "Tipo de Ativo";

  /**
   * Insere um registro de agregador de produto
   */
  public void insert( BaseFncVO fncVO_ )
  {
	ProdSubAssetDetailFncVO detailFncVO = ( ProdSubAssetDetailFncVO ) fncVO_;
	validateInsert( detailFncVO );
	if ( !detailFncVO.hasErrors() )
	{
	  TplProdSubAssetEntity prodSubAssetEntity = ( TplProdSubAssetEntity ) detailFncVO.getBaseTplProdSubAssetEntity();
	  prodSubAssetEntity.getData().setLastUpdDate( new Date() );
	  prodSubAssetEntity.getData().setLastUpdUserId(
													 fncVO_.getLoggedUser() != null
																				   ? fncVO_.getLoggedUser().getUserID()
																				   : "" );

	  TplProdSubAssetMovEntity prvtMovEntity = new TplProdSubAssetMovEntity(
																			 prodSubAssetEntity,
																			 TplProdSubAssetMovEntity.C_OPERN_CODE_INSERT );
	  TplProdSubAssetMovDAO tplProdSubAssetMovDAO = ODSDAOFactory.getInstance().getTplProdSubAssetMovDAO();
	  tplProdSubAssetMovDAO.insert( prvtMovEntity );
	}

  }

  /**
   * Atualiza um registro de agregador de produto
   */
  public void update( BaseFncVO fncVO_ )
  {
	ProdSubAssetDetailFncVO detailFncVO = ( ProdSubAssetDetailFncVO ) fncVO_;
	validateUpdate( detailFncVO );
	if ( !detailFncVO.hasErrors() )
	{

	  //createMovementRecord
	  TplProdSubAssetEntity prodSubAssetEntity = ( TplProdSubAssetEntity ) detailFncVO.getBaseTplProdSubAssetEntity();
	  prodSubAssetEntity.getData().setLastUpdDate( new Date() );
	  prodSubAssetEntity.getData().setLastUpdUserId(
													 fncVO_.getLoggedUser() != null
																				   ? fncVO_.getLoggedUser().getUserID()
																				   : "" );
	  																	   

	  TplProdSubAssetMovEntity prvtMovEntity = new TplProdSubAssetMovEntity(
																			 prodSubAssetEntity,
																			 TplProdSubAssetMovEntity.C_OPERN_CODE_UPDATE );
	  TplProdSubAssetMovDAO tplProdSubAssetMovDAO = ODSDAOFactory.getInstance().getTplProdSubAssetMovDAO();
	  tplProdSubAssetMovDAO.insert( prvtMovEntity );

	}

  }

  /**
   * Remove um registro de agregador de produto
   */
  public void delete( BaseFncVO fncVO_ )
  {
	ProdSubAssetDetailFncVO detailFncVO = ( ProdSubAssetDetailFncVO ) fncVO_;

	validateDelete( detailFncVO );
	if ( !detailFncVO.hasErrors() )
	{

	  TplProdSubAssetDAO tplProdSubAssetDAO = ODSDAOFactory.getInstance().getTplProdSubAssetDAO();
	  TplProdSubAssetEntity prodSubAssetEntity = ( TplProdSubAssetEntity ) tplProdSubAssetDAO.find( detailFncVO.getBaseTplProdSubAssetEntity() );

	  TplProdSubAssetMovEntity prvtMovEntity = new TplProdSubAssetMovEntity(
																			 prodSubAssetEntity,
																			 TplProdSubAssetMovEntity.C_OPERN_CODE_DELETE );

	  prvtMovEntity.getData().setLastUpdDate( new Date() );
	  prodSubAssetEntity.getData().setLastUpdUserId(
													 fncVO_.getLoggedUser() != null
																				   ? fncVO_.getLoggedUser().getUserID()
																				   : "" );

	  TplProdSubAssetMovDAO tplProdSubAssetMovDAO = ODSDAOFactory.getInstance().getTplProdSubAssetMovDAO();
	  tplProdSubAssetMovDAO.insert( prvtMovEntity );

	}

  }

  /**
   * Realiza as validações - INSERT
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
	ProdSubAssetDetailFncVO prodSubAssetDetailFncVO = ( ProdSubAssetDetailFncVO ) fncVO_;

	// Validar Campos Obrigatórios
	/*if ( prodSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().getProdSubAssetCode() == null
		 || prodSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().getProdSubAssetCode().intValue() == 0 )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_PROD_SUBASSET_CODE );
	}*/

	if ( prodSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().getProdSubAssetText() == null
		 || prodSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().getProdSubAssetText().equals(
																									   "" ) )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_PROD_SUBASSET_TEXT );
	}
	
	if ( prodSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().getProdAssetCode() == null
	  || prodSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().getProdAssetCode().intValue() == 0 )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_PROD_ASSET_TEXT);
	}
	
	if(prodSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().getSubAssetClassRptOrderNbr() == null){
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_SUB_ASSET_CLASS_RPT_ORDER_NBR );
	}
	
  }

  /**
   * Realiza as validações - UPDATE
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
	ProdSubAssetDetailFncVO prodSubAssetDetailFncVO = ( ProdSubAssetDetailFncVO ) fncVO_;

	//  Validar Campos Obrigatórios
	if ( prodSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().getProdSubAssetText() == null
		 || prodSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().getProdSubAssetText().equals(""))
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_PROD_SUBASSET_TEXT );
	}

	if ( prodSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().getProdAssetCode() == null )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_PROD_ASSET_TEXT );
	}
	
	if(prodSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().getSubAssetClassRptOrderNbr() == null){
		fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_SUB_ASSET_CLASS_RPT_ORDER_NBR );
	}

	// Verifica se o registro está com status ativo
	if ( !existsActive( prodSubAssetDetailFncVO ) )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_DUPLICATE_PK );
	}
  }

  /**
   * Realiza as validações - DELETE
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
	ProdSubAssetDetailFncVO prodSubAssetDetailFncVO = ( ProdSubAssetDetailFncVO ) fncVO_;

	// Verifica se já existe algum registro com status ativo
	if ( !existsActive( prodSubAssetDetailFncVO ) )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_PK_NOT_FOUND );
	}

	// Validar se já existe movimento com este codigo, caso os campos
	// obrigatórios estejam presentes
	if ( !fncVO_.hasErrors() )
	{
	  if ( this.existsInMovement( prodSubAssetDetailFncVO ) )
	  {
		fncVO_.addError( BaseODSFncVO.C_ERROR_IN_MOVEMENT );
	  }
	}
    
	//Valida se algum asset type ativo esta utilizando esta Sub Asset
	if ( !fncVO_.hasErrors() )
	{
	  if ( this.existsAssetTypeActive( prodSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().getProdSubAssetCode() ) )
	  {
		fncVO_.addError( BaseODSFncVO.C_ERROR_IS_REFERENCED, C_PROD_SubAsset,
						 C_TIPO_ATIVO );
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
	ProdSubAssetDetailFncVO prodSubAssetDetailFncVO = ( ProdSubAssetDetailFncVO ) fncVO_;
	prodSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().setProdSubAssetCode(
																					  null );
	prodSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().setProdSubAssetText(
																				  null );
																				  
	prodSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().setProdAssetCode(null);
	prodSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().setSubAssetClassRptOrderNbr(null);	
	prodSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().setIxCode(null);	
	super.loadDomains(prodSubAssetDetailFncVO);
  }

  /**
   * Carregamentos iniciais - Update
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
	ProdSubAssetDetailFncVO prodSubAssetDetailFncVO = ( ProdSubAssetDetailFncVO ) fncVO_;
	// Verifica se o registro está ativo
	if ( !this.existsActive( prodSubAssetDetailFncVO ) )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_REG_NOT_EXISTS,
					   ProdSubAssetDetailFncVO.C_PROD_SUBASSET_CODE_DESCRIPTION );
	}	
	else{	
		if(!this.existsInMovement(prodSubAssetDetailFncVO))
		{		
			prodSubAssetDetailFncVO.setBaseTplProdSubAssetEntity(ODSDAOFactory.getInstance().getTplProdSubAssetDAO().find(prodSubAssetDetailFncVO.getBaseTplProdSubAssetEntity()));
			super.loadDomains(prodSubAssetDetailFncVO);
		}
		else
		{
			fncVO_.addError( ProdSubAssetDetailFncVO.C_ERROR_IN_MOVEMENT,
								   ProdSubAssetDetailFncVO.C_PROD_SUBASSET_CODE_DESCRIPTION );
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
	return new ProdSubAssetDetailFncVO();
  }

  /**
   * Retorna a instancia da DAO utilizado por este Fnc
   */
  protected BaseTplProdSubAssetDAO getDAO()
  {
	return ODSDAOFactory.getInstance().getTplProdSubAssetMovDAO();
  }

  /*
   * Verifica se existe um registro na tabela de movimento com os critérios
   * passados
   * 
   * @see com.citibank.ods.modules.product.prodriskcatprvt.functionality.BaseProdRiskCatPrvtDetailFnc#getDAO()
   */
  private boolean existsInMovement(
								   ProdSubAssetDetailFncVO prodSubAssetDetailFncVO_ )
  {
	TplProdSubAssetMovDAO tplProdSubAssetMovDAO = ODSDAOFactory.getInstance().getTplProdSubAssetMovDAO();

	TplProdSubAssetMovEntity prvtMovEntity = new TplProdSubAssetMovEntity();
	prvtMovEntity.getData().setProdSubAssetCode(
											 prodSubAssetDetailFncVO_.getBaseTplProdSubAssetEntity().getData().getProdSubAssetCode() );

	return tplProdSubAssetMovDAO.exists( prvtMovEntity );
  }

  /*
   * Verifica se existe algum registro com a chave passada e seu status é ativo
   */
  private boolean existsActive( ProdSubAssetDetailFncVO prodSubAssetDetailFncVO_ )
  {
	TplProdSubAssetDAO tplProdSubAssetDAO = ODSDAOFactory.getInstance().getTplProdSubAssetDAO();
	return tplProdSubAssetDAO.existsActive( ( TplProdSubAssetEntity ) prodSubAssetDetailFncVO_.getBaseTplProdSubAssetEntity() );
  }
  
  /*
   * Verifica se existe um produto ativo que utilize a qualificação de produto passada como parametro
   */
  private boolean existsAssetTypeActive( BigInteger subAssetCode_ )
  {
	TplProdAssetTypeDAO tplProdAssetTypeDAO = ODSDAOFactory.getInstance().getTplProdAssetTypeDAO();
	return tplProdAssetTypeDAO.existsAssetTypeByForeignKey(subAssetCode_);
  }

}
