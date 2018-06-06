/*
 * Created on 06/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodsubasset.functionality;

import java.math.BigInteger;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplProdSubAssetEntity;
import com.citibank.ods.entity.pl.TplProdSubAssetMovEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplProdSubAssetEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdSubAssetEntityVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdSubAssetDAO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetTypeDAO;
import com.citibank.ods.persistence.pl.dao.TplProdSubAssetDAO;
import com.citibank.ods.persistence.pl.dao.TplProdSubAssetHistDAO;
import com.citibank.ods.persistence.pl.dao.TplProdSubAssetMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.modules.product.prodsubasset.functionality.valueobject.ProdSubAssetDetailFncVO;
import com.citibank.ods.modules.product.prodsubasset.functionality.valueobject.ProdSubAssetMovementDetailFncVO;
import com.citibank.ods.entity.pl.valueobject.TplProdSubAssetMovEntityVO;
import com.citibank.ods.modules.product.prodsubasset.form.ProdSubAssetMovementDetailForm;
import com.citibank.ods.entity.pl.TplProdSubAssetHistEntity;

/**
 * @author lfabiano
 * @since 06/10/208
 */
public class ProdSubAssetMovementDetailFnc extends
	  BaseProdSubAssetDetailFnc implements ODSMovementDetailFnc
{
	/**
	   * Retorna o DAO utilizado pelo método load da super classe
	   * 
	   * @see com.citibank.ods.modules.product.productfamilyprvt.functionality.BaseProductFamilyPrvtDetailFnc#getDAO()
	   */
	  protected BaseTplProdSubAssetDAO getDAO()
	  {
		return ODSDAOFactory.getInstance().getTplProdSubAssetMovDAO();
	  }

	  /**
	   * Retorna uma instância do FncVO
	   */
	  public BaseFncVO createFncVO()
	  {
		return new ProdSubAssetMovementDetailFncVO();		
	  }

	  /**
	   * Realiza a atualização de um registro de movimento
	   */
	  public void update( BaseFncVO fncVO_ )
	  {
		validateUpdate( fncVO_ );

		if ( !fncVO_.hasErrors() )
		{
			ProdSubAssetMovementDetailFncVO movementDetailFncVO = ( ProdSubAssetMovementDetailFncVO ) fncVO_;
			BaseTplProdSubAssetEntityVO baseTplProdSubAssetEntityVO = movementDetailFncVO.getBaseTplProdSubAssetEntity().getData();

		  baseTplProdSubAssetEntityVO.setLastUpdDate( new Date() );
		  baseTplProdSubAssetEntityVO.setLastUpdUserId( fncVO_.getLoggedUser() != null
																						   ? fncVO_.getLoggedUser().getUserID()
																						   : "" );

		  TplProdSubAssetMovDAO prodSubAssetMovDAO = ODSDAOFactory.getInstance().getTplProdSubAssetMovDAO();
		  prodSubAssetMovDAO.update( ( TplProdSubAssetMovEntity ) movementDetailFncVO.getBaseTplProdSubAssetEntity() );
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
		  TplProdSubAssetDAO tplProdSubAssetDAO = factory.getTplProdSubAssetDAO();
		  TplProdSubAssetMovDAO tplProdSubAssetMovDAO = factory.getTplProdSubAssetMovDAO();
		  TplProdSubAssetHistDAO tplProdSubAssetHistDAO = factory.getTplProdSubAssetHistDAO();

		  // FncVO do movimento
		  ProdSubAssetMovementDetailFncVO movementDetailFncVO = ( ProdSubAssetMovementDetailFncVO ) fncVO_;
		     super.loadProdSubAsset( movementDetailFncVO );
		  
		  // Entity do movimento
		  TplProdSubAssetMovEntity prodSubAssetMovEntity = ( TplProdSubAssetMovEntity ) movementDetailFncVO.getBaseTplProdSubAssetEntity();

		  // OpernCode
		  String opernCode = ( ( TplProdSubAssetMovEntityVO ) prodSubAssetMovEntity.getData() ).getOpernCode();

		  TplProdSubAssetEntity tplProdSubAssetEntity = new TplProdSubAssetEntity(prodSubAssetMovEntity,
																		new Date(),
																		fncVO_.getLoggedUser().getUserID(),
																		TplProdSubAssetEntity.C_REC_STAT_CODE_ACTIVE );

		  // Verifica qual operacao está sendo aprovada
		  if ( TplProdSubAssetMovEntity.C_OPERN_CODE_DELETE.equals( opernCode ) )
		  {
			//setar estatus como inativo
			TplProdSubAssetEntityVO tplProdutFamilyPrvtEntityVO = ( TplProdSubAssetEntityVO ) tplProdSubAssetEntity.getData();
			tplProdutFamilyPrvtEntityVO.setRecStatCode( TplProdSubAssetEntity.C_REC_STAT_CODE_INACTIVE );
		  }

		  if ( tplProdSubAssetDAO.exists( tplProdSubAssetEntity ) )
		  {
			TplProdSubAssetEntity tplProdSubAssetEntityOld = ( TplProdSubAssetEntity ) tplProdSubAssetDAO.find( tplProdSubAssetEntity );
			TplProdSubAssetHistEntity prodSubAssetHistEntity = new TplProdSubAssetHistEntity(
																					tplProdSubAssetEntityOld,
																					new Date() );
			tplProdSubAssetHistDAO.insert( prodSubAssetHistEntity );

			tplProdSubAssetDAO.update( tplProdSubAssetEntity );
		  }
		  else
		  {
			tplProdSubAssetDAO.insert( tplProdSubAssetEntity );
		  }
			tplProdSubAssetMovDAO.delete( prodSubAssetMovEntity );
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
		  ProdSubAssetMovementDetailFncVO movementDetailFncVO = ( ProdSubAssetMovementDetailFncVO ) fncVO_;
		  TplProdSubAssetMovEntity prodSubAssetMovEntity = ( TplProdSubAssetMovEntity ) movementDetailFncVO.getBaseTplProdSubAssetEntity();

		  TplProdSubAssetMovDAO prodSubAssetMovDAO = ODSDAOFactory.getInstance().getTplProdSubAssetMovDAO();
		  prodSubAssetMovDAO.delete( prodSubAssetMovEntity );
		}
	  }

	  /**
	   * Realiza as Validações - Update
	   */
	  public void validateUpdate( BaseFncVO fncVO_ )
	  {
		ProdSubAssetMovementDetailFncVO prodSubAssetMovementDetailFncVO = ( ProdSubAssetMovementDetailFncVO ) fncVO_;
		TplProdSubAssetMovEntityVO ProdSubAssetMovEntityVO = ( TplProdSubAssetMovEntityVO ) prodSubAssetMovementDetailFncVO.getBaseTplProdSubAssetEntity().getData();

		//testar usuário
		if (( !prodSubAssetMovementDetailFncVO.getLoggedUser().getUserID().equals( ProdSubAssetMovEntityVO.getLastUpdUserId() ) ) )

		{
		  prodSubAssetMovementDetailFncVO.addError( BaseODSFncVO.C_ERROR_APPROVAL_UPDATE_USER_NOT_AUTHORIZED );
		}
		else
		{
		  // testar campos obrigatórios
		  if ( ProdSubAssetMovEntityVO.getProdSubAssetText() == null
			   || ProdSubAssetMovEntityVO.getProdSubAssetText().equals( "" ) )
		  {
			prodSubAssetMovementDetailFncVO.addError(BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
												   C_PROD_SUBASSET_TEXT );
		  }
		  
		  if ( prodSubAssetMovementDetailFncVO.getBaseTplProdSubAssetEntity().getData().getProdAssetCode() == null )
		  {
			fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_PROD_ASSET_TEXT );
		  }
		  
	}

	   //  Se opernCode = Delete, adicionar erros
	  if ( !fncVO_.hasErrors() )
	  {
		  TplProdSubAssetMovDAO tplProdSubAssetMovDAO = ODSDAOFactory.getInstance().getTplProdSubAssetMovDAO();
		  TplProdSubAssetMovEntity tplProdSubAssetMovEntity = ( TplProdSubAssetMovEntity ) tplProdSubAssetMovDAO.find( prodSubAssetMovementDetailFncVO.getBaseTplProdSubAssetEntity() );

		  String opernCode = ( ( TplProdSubAssetMovEntityVO ) tplProdSubAssetMovEntity.getData() ).getOpernCode();

		  if ( TplProdSubAssetMovEntity.C_OPERN_CODE_DELETE.equals( opernCode ) )
		  {
			prodSubAssetMovementDetailFncVO.addError( ProdSubAssetMovementDetailFncVO.C_ERROR_CANNOT_UPD_DELETE_IN_MOVEMENT );
		  }
		}
	  }

	  /**
	   * Realiza as validações - Approve
	   */
	  public void validateApprove( BaseFncVO fncVO_ )
	  {
		ProdSubAssetMovementDetailFncVO ProdSubAssetMovementDetailFncVO = ( ProdSubAssetMovementDetailFncVO ) fncVO_;
		TplProdSubAssetMovEntityVO ProdSubAssetMovEntityVO = ( TplProdSubAssetMovEntityVO ) ProdSubAssetMovementDetailFncVO.getBaseTplProdSubAssetEntity().getData();

		//testar usuário
		if ( ProdSubAssetMovementDetailFncVO.getLoggedUser().getUserID().equals(
																					  ProdSubAssetMovEntityVO.getLastUpdUserId() ) )
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
		ProdSubAssetMovementDetailFncVO movementDetailFncVO = ( ProdSubAssetMovementDetailFncVO ) fncVO_;
		super.loadProdSubAsset( movementDetailFncVO );
		super.loadDomains(movementDetailFncVO);
	  }

	  /**
	   * Carregamentos iniciais - Approve
	   */
	  public void loadForApprove( BaseFncVO fncVO_ )
	  {
		ProdSubAssetMovementDetailFncVO movementDetailFncVO = ( ProdSubAssetMovementDetailFncVO ) fncVO_;
		super.loadProdSubAsset( movementDetailFncVO );
		super.loadDomains(movementDetailFncVO);
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

		ProdSubAssetMovementDetailForm prodSubAssetMovementDetailForm = ( ProdSubAssetMovementDetailForm ) form_;
		ProdSubAssetMovementDetailFncVO prodSubAssetMovementDetailFncVO = ( ProdSubAssetMovementDetailFncVO ) fncVO_;
		
		TplProdSubAssetMovEntityVO prodSubAssetMovEntityVO = ( TplProdSubAssetMovEntityVO ) prodSubAssetMovementDetailFncVO.getBaseTplProdSubAssetEntity().getData();

		String opernCode = prodSubAssetMovEntityVO.getOpernCode();
		

		if ( opernCode != null && !"".equals( opernCode ) )
		{
		  prodSubAssetMovementDetailForm.setOpernCode( ODSConstraintDecoder.decodeOpern( opernCode ) );
		}
	  }



}
