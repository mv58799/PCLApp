/*
 * Created on 20/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodassettype.functionality;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.TplProdAssetTypeEntity;
import com.citibank.ods.entity.pl.TplProdAssetTypeMovEntity;
import com.citibank.ods.modules.product.prodassettype.functionality.valueobject.ProdAssetTypeDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdAssetTypeDAO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetTypeDAO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetTypeMovDAO;
import com.citibank.ods.persistence.pl.dao.TplProductDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProdAssetTypeDetailFnc extends BaseProdAssetTypeDetailFnc implements
	ODSDetailFnc
{

  private static final String C_PROD_ASSETTYPE = "Tipo de Ativo";

  private static final String C_PRODUCTS = "Produtos";

  /**
   * Insere um registro de agregador de produto
   */
  public void insert( BaseFncVO fncVO_ )
  {
	ProdAssetTypeDetailFncVO detailFncVO = ( ProdAssetTypeDetailFncVO ) fncVO_;
	validateInsert( detailFncVO );
	if ( !detailFncVO.hasErrors() )
	{
	  TplProdAssetTypeEntity prodAssetTypeEntity = ( TplProdAssetTypeEntity ) detailFncVO.getBaseTplProdAssetTypeEntity();
	  prodAssetTypeEntity.getData().setLastUpdDate( new Date() );
	  prodAssetTypeEntity.getData().setLastUpdUserId(
													 fncVO_.getLoggedUser() != null
																				   ? fncVO_.getLoggedUser().getUserID()
																				   : "" );

	  TplProdAssetTypeMovEntity prvtMovEntity = new TplProdAssetTypeMovEntity(
																			 prodAssetTypeEntity,
																			 TplProdAssetTypeMovEntity.C_OPERN_CODE_INSERT );
	  TplProdAssetTypeMovDAO tplProdAssetTypeMovDAO = ODSDAOFactory.getInstance().getTplProdAssetTypeMovDAO();
	  tplProdAssetTypeMovDAO.insert( prvtMovEntity );
	}

  }

  /**
   * Atualiza um registro de agregador de produto
   */
  public void update( BaseFncVO fncVO_ )
  {
	ProdAssetTypeDetailFncVO detailFncVO = ( ProdAssetTypeDetailFncVO ) fncVO_;
	validateUpdate( detailFncVO );
	if ( !detailFncVO.hasErrors() )
	{
	  
	  TplProdAssetTypeEntity prodAssetTypeEntity = ( TplProdAssetTypeEntity ) detailFncVO.getBaseTplProdAssetTypeEntity();
	  prodAssetTypeEntity.getData().setLastUpdDate( new Date() );
	  prodAssetTypeEntity.getData().setLastUpdUserId(
													 fncVO_.getLoggedUser() != null
																				   ? fncVO_.getLoggedUser().getUserID()
																				   : "" );
																				   
	  TplProdAssetTypeMovEntity prvtMovEntity = new TplProdAssetTypeMovEntity(
																			 prodAssetTypeEntity,
																			 TplProdAssetTypeMovEntity.C_OPERN_CODE_UPDATE );
	  TplProdAssetTypeMovDAO tplProdAssetTypeMovDAO = ODSDAOFactory.getInstance().getTplProdAssetTypeMovDAO();
	  tplProdAssetTypeMovDAO.insert( prvtMovEntity );

	}

  }

  /**
   * Remove um registro de agregador de produto
   */
  public void delete( BaseFncVO fncVO_ )
  {
	ProdAssetTypeDetailFncVO detailFncVO = ( ProdAssetTypeDetailFncVO ) fncVO_;

	validateDelete( detailFncVO );
	if ( !detailFncVO.hasErrors() )
	{

	  TplProdAssetTypeDAO tplProdAssetTypeDAO = ODSDAOFactory.getInstance().getTplProdAssetTypeDAO();
	  TplProdAssetTypeEntity prodAssetTypeEntity = ( TplProdAssetTypeEntity ) tplProdAssetTypeDAO.find( detailFncVO.getBaseTplProdAssetTypeEntity() );

	  

	  prodAssetTypeEntity.getData().setLastUpdDate( new Date() );
	  prodAssetTypeEntity.getData().setLastUpdUserId(
													 fncVO_.getLoggedUser() != null
																				   ? fncVO_.getLoggedUser().getUserID()
																				   : "" );
	  
	  TplProdAssetTypeMovEntity prvtMovEntity = new TplProdAssetTypeMovEntity(
				 prodAssetTypeEntity,
				 TplProdAssetTypeMovEntity.C_OPERN_CODE_DELETE );

	  TplProdAssetTypeMovDAO tplProdAssetTypeMovDAO = ODSDAOFactory.getInstance().getTplProdAssetTypeMovDAO();
	  tplProdAssetTypeMovDAO.insert( prvtMovEntity );

	}

  }

  /**
   * Realiza as validações - INSERT
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
	ProdAssetTypeDetailFncVO prodAssetTypeDetailFncVO = ( ProdAssetTypeDetailFncVO ) fncVO_;

	// Validar Campos Obrigatórios
	if ( prodAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdAssetTypeText() == null
		 || prodAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdAssetTypeText().equals("") )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_PROD_ASSETTYPE_TEXT );
	}
	
	if ( prodAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdSubAssetCode() == null)
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_PROD_SUB_ASSET_TEXT);
	}
	
	
  }

  /**
   * Realiza as validações - UPDATE
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
	ProdAssetTypeDetailFncVO prodAssetTypeDetailFncVO = ( ProdAssetTypeDetailFncVO ) fncVO_;

	//  Validar Campos Obrigatórios
	if ( prodAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdAssetTypeText() == null
		 || prodAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdAssetTypeText().equals("") )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_PROD_ASSETTYPE_TEXT );
	}

	if ( prodAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdSubAssetCode() == null
		 || prodAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdSubAssetCode().equals("") )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_PROD_SUB_ASSET_TEXT );
	}

	// Verifica se o registro não está ativo
	if ( !existsActive( prodAssetTypeDetailFncVO ) )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_DUPLICATE_PK );
	}
  }

  /**
   * Realiza as validações - DELETE
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
	ProdAssetTypeDetailFncVO prodAssetTypeDetailFncVO = ( ProdAssetTypeDetailFncVO ) fncVO_;

	// Verifica se já existe algum registro com status ativo
	if ( !existsActive( prodAssetTypeDetailFncVO ) )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_PK_NOT_FOUND );
	}

	// Validar se já existe movimento com este codigo, caso os campos
	// obrigatórios estejam presentes
	if ( !fncVO_.hasErrors() )
	{
	  if ( this.existsInMovement( prodAssetTypeDetailFncVO ) )
	  {
		fncVO_.addError( BaseODSFncVO.C_ERROR_IN_MOVEMENT );
	  }
	}
    
	//Valida se o Tipo de Ativo esta existe em algum Produto com status ativo
	if ( !fncVO_.hasErrors() )
	{
	  if ( this.existsProductActive( prodAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdAssetTypeCode() ) )
	  {
		fncVO_.addError( BaseODSFncVO.C_ERROR_IS_REFERENCED, C_PROD_ASSETTYPE,
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
	ProdAssetTypeDetailFncVO prodAssetTypeDetailFncVO = ( ProdAssetTypeDetailFncVO ) fncVO_;
	
	if(prodAssetTypeDetailFncVO.isRefreshAssetCode()){
	  
	  if(prodAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdSubAssetCode()!= null){
		super.loadAssetBySubAsset(prodAssetTypeDetailFncVO);
	  }
	  else{
		prodAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().setProdAssetCode(null);
	  }
	  
	}
	else{
		prodAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().setProdAssetTypeCode(
																							  null );
		prodAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().setProdAssetTypeText(
																						  null );
																				  
		prodAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().setProdSubAssetCode(null);
		prodAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().setProdAssetCode(null);		
		prodAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().setAssetTypeCustRptOrderNbr(null);
																				  
		super.loadDomains(prodAssetTypeDetailFncVO);		
	}
	
	
  }

  /**
   * Carregamentos iniciais - Update
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
	ProdAssetTypeDetailFncVO prodAssetTypeDetailFncVO = ( ProdAssetTypeDetailFncVO ) fncVO_;
	
	if(prodAssetTypeDetailFncVO.isRefreshAssetCode()){
	  if(prodAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdSubAssetCode()!= null){
		super.loadAssetBySubAsset(prodAssetTypeDetailFncVO);
	  }
	  else{
	    prodAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().setProdAssetCode(null);
	  }
	}
	else{
		
		//Verifica se o registro está ativo
		 if ( !this.existsActive( prodAssetTypeDetailFncVO ) )
		 {
		   fncVO_.addError( BaseODSFncVO.C_ERROR_REG_NOT_EXISTS,
							ProdAssetTypeDetailFncVO.C_PROD_SUBASSET_CODE_DESCRIPTION );
		 }	
		 else{	
			 if(!this.existsInMovement(prodAssetTypeDetailFncVO))
			 {		
			
				 prodAssetTypeDetailFncVO.setBaseTplProdAssetTypeEntity(ODSDAOFactory.getInstance().getTplProdAssetTypeDAO().find(prodAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity()));
				 super.loadDomains(prodAssetTypeDetailFncVO);
			 }
			 else
			 {
				 fncVO_.addError( ProdAssetTypeDetailFncVO.C_ERROR_IN_MOVEMENT,
										ProdAssetTypeDetailFncVO.C_PROD_SUBASSET_CODE_DESCRIPTION );
			 }
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
	return new ProdAssetTypeDetailFncVO();
  }

  /**
   * Retorna a instancia da DAO utilizado por este Fnc
   */
  protected BaseTplProdAssetTypeDAO getDAO()
  {
	return ODSDAOFactory.getInstance().getTplProdAssetTypeMovDAO();
  }

  /*
   * Verifica se existe um registro na tabela de movimento com os critérios
   * passados
   * 
   * @see com.citibank.ods.modules.product.prodriskcatprvt.functionality.BaseProdRiskCatPrvtDetailFnc#getDAO()
   */
  private boolean existsInMovement(
								   ProdAssetTypeDetailFncVO prodAssetTypeDetailFncVO_ )
  {
	TplProdAssetTypeMovDAO tplProdAssetTypeMovDAO = ODSDAOFactory.getInstance().getTplProdAssetTypeMovDAO();

	TplProdAssetTypeMovEntity prvtMovEntity = new TplProdAssetTypeMovEntity();
	prvtMovEntity.getData().setProdAssetTypeCode(
											 prodAssetTypeDetailFncVO_.getBaseTplProdAssetTypeEntity().getData().getProdAssetTypeCode() );

	return tplProdAssetTypeMovDAO.exists( prvtMovEntity );
  }

  /*
   * Verifica se existe algum registro com a chave passada e seu status é ativo
   */
  private boolean existsActive( ProdAssetTypeDetailFncVO prodAssetTypeDetailFncVO_ )
  {
	TplProdAssetTypeDAO tplProdAssetTypeDAO = ODSDAOFactory.getInstance().getTplProdAssetTypeDAO();
	return tplProdAssetTypeDAO.existsActive( ( TplProdAssetTypeEntity ) prodAssetTypeDetailFncVO_.getBaseTplProdAssetTypeEntity() );
  }
  
  /*
   * Verifica se existe um produto ativo que utilize a qualificação de produto passada como parametro
   */
  private boolean existsProductActive( BigInteger prodAssetTypeCode_ )
  {
	TplProductDAO tplProductDAO = ODSDAOFactory.getInstance().getTplProductDAO();
	return tplProductDAO.existsProductByForeignKey( null, null, null,
													null,prodAssetTypeCode_ );
  }

}
