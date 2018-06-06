/*
 * Created on 01/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodasset.functionality;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplProdAssetEntity;
import com.citibank.ods.entity.pl.TplProdAssetHistEntity;
import com.citibank.ods.entity.pl.TplProdAssetMovEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplProdAssetEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdAssetEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdAssetMovEntityVO;
import com.citibank.ods.modules.product.prodasset.form.ProdAssetMovementDetailForm;
import com.citibank.ods.modules.product.prodasset.functionality.valueobject.ProdAssetMovementDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdAssetDAO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetDAO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetHistDAO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author lfabiano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
  public class ProdAssetMovementDetailFnc extends
      BaseProdAssetDetailFnc implements ODSMovementDetailFnc
{
	/**
	   * Retorna o DAO utilizado pelo método load da super classe
	   * 
	   * @see com.citibank.ods.modules.product.productfamilyprvt.functionality.BaseProductFamilyPrvtDetailFnc#getDAO()
	   */
	  protected BaseTplProdAssetDAO getDAO()
	  {
		return ODSDAOFactory.getInstance().getTplProdAssetMovDAO();
	  }

	  /**
	   * Retorna uma instância do FncVO
	   */
	  public BaseFncVO createFncVO()
	  {
		return new ProdAssetMovementDetailFncVO();		
	  }

	  /**
	   * Realiza a atualização de um registro de movimento
	   */
	  public void update( BaseFncVO fncVO_ )
	  {
		validateUpdate( fncVO_ );

		if ( !fncVO_.hasErrors() )
		{
			ProdAssetMovementDetailFncVO movementDetailFncVO = ( ProdAssetMovementDetailFncVO ) fncVO_;
			BaseTplProdAssetEntityVO baseTplProdAssetEntityVO = movementDetailFncVO.getBaseTplProdAssetEntity().getData();

		  baseTplProdAssetEntityVO.setLastUpdDate( new Date() );
		  baseTplProdAssetEntityVO.setLastUpdUserId( fncVO_.getLoggedUser() != null
																						   ? fncVO_.getLoggedUser().getUserID()
																						   : "" );

		  TplProdAssetMovDAO prodAssetMovDAO = ODSDAOFactory.getInstance().getTplProdAssetMovDAO();
		  prodAssetMovDAO.update( ( TplProdAssetMovEntity ) movementDetailFncVO.getBaseTplProdAssetEntity() );
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
		  TplProdAssetDAO tplProdAssetDAO = factory.getTplProdAssetDAO();
		  TplProdAssetMovDAO tplProdAssetMovDAO = factory.getTplProdAssetMovDAO();
		  TplProdAssetHistDAO tplProdAssetHistDAO = factory.getTplProdAssetHistDAO();

		  // FncVO do movimento
		  ProdAssetMovementDetailFncVO movementDetailFncVO = ( ProdAssetMovementDetailFncVO ) fncVO_;
		  super.loadProdAsset( movementDetailFncVO );

		  // Entity do movimento
		  TplProdAssetMovEntity prodAssetMovEntity = ( TplProdAssetMovEntity ) movementDetailFncVO.getBaseTplProdAssetEntity();

		  // OpernCode
		  String opernCode = ( ( TplProdAssetMovEntityVO ) prodAssetMovEntity.getData() ).getOpernCode();

		  TplProdAssetEntity tplProdAssetEntity = new TplProdAssetEntity(prodAssetMovEntity,
																		new Date(),
																		fncVO_.getLoggedUser().getUserID(),
																		TplProdAssetEntity.C_REC_STAT_CODE_ACTIVE );

		  // Verifica qual operacao está sendo aprovada
		  if ( TplProdAssetMovEntity.C_OPERN_CODE_DELETE.equals( opernCode ) )
		  {
			//setar estatus como inativo
			TplProdAssetEntityVO tplProdutFamilyPrvtEntityVO = ( TplProdAssetEntityVO ) tplProdAssetEntity.getData();
			tplProdutFamilyPrvtEntityVO.setRecStatCode( TplProdAssetEntity.C_REC_STAT_CODE_INACTIVE );
		  }

		  if ( tplProdAssetDAO.exists( tplProdAssetEntity ) )
		  {
			TplProdAssetEntity tplProdAssetEntityOld = ( TplProdAssetEntity ) tplProdAssetDAO.find( tplProdAssetEntity );
			TplProdAssetHistEntity prodAssetHistEntity = new TplProdAssetHistEntity(
																					tplProdAssetEntityOld,
																					new Date() );
			tplProdAssetHistDAO.insert( prodAssetHistEntity );

			tplProdAssetDAO.update( tplProdAssetEntity );
		  }
		  else
		  {
			tplProdAssetDAO.insert( tplProdAssetEntity );
		  }
		    tplProdAssetMovDAO.delete( prodAssetMovEntity );
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
		  ProdAssetMovementDetailFncVO movementDetailFncVO = ( ProdAssetMovementDetailFncVO ) fncVO_;
		  TplProdAssetMovEntity prodAssetMovEntity = ( TplProdAssetMovEntity ) movementDetailFncVO.getBaseTplProdAssetEntity();

		  TplProdAssetMovDAO prodAssetMovDAO = ODSDAOFactory.getInstance().getTplProdAssetMovDAO();
		  prodAssetMovDAO.delete( prodAssetMovEntity );
		}
	  }

	  /**
	   * Realiza as Validações - Update
	   */
	  public void validateUpdate( BaseFncVO fncVO_ )
	  {
		ProdAssetMovementDetailFncVO prodAssetMovementDetailFncVO = ( ProdAssetMovementDetailFncVO ) fncVO_;
		TplProdAssetMovEntityVO ProdAssetMovEntityVO = ( TplProdAssetMovEntityVO ) prodAssetMovementDetailFncVO.getBaseTplProdAssetEntity().getData();

		//testar usuário
		if ( !( prodAssetMovementDetailFncVO.getLoggedUser().getUserID().equals( ProdAssetMovEntityVO.getLastUpdUserId() ) ) )

		{
		  prodAssetMovementDetailFncVO.addError( BaseODSFncVO.C_ERROR_APPROVAL_UPDATE_USER_NOT_AUTHORIZED );
		}
		else
		{
		  // testar campos obrigatórios
		  if ( ProdAssetMovEntityVO.getProdAssetText() == null
			   || ProdAssetMovEntityVO.getProdAssetText().equals( "" ) )
		  {
			prodAssetMovementDetailFncVO.addError(BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
												   C_PROD_ASSET_TEXT );
		  }
	   }

	   //  Se opernCode = Delete, adicionar erros
	  if ( !fncVO_.hasErrors() )
	  {
		  TplProdAssetMovDAO tplProdAssetMovDAO = ODSDAOFactory.getInstance().getTplProdAssetMovDAO();
		  TplProdAssetMovEntity tplProdAssetMovEntity = ( TplProdAssetMovEntity ) tplProdAssetMovDAO.find( prodAssetMovementDetailFncVO.getBaseTplProdAssetEntity() );

		  String opernCode = ( ( TplProdAssetMovEntityVO ) tplProdAssetMovEntity.getData() ).getOpernCode();

		  if ( TplProdAssetMovEntity.C_OPERN_CODE_DELETE.equals( opernCode ) )
		  {
			prodAssetMovementDetailFncVO.addError( ProdAssetMovementDetailFncVO.C_ERROR_CANNOT_UPD_DELETE_IN_MOVEMENT );
		  }
		}
	  }

	  /**
	   * Realiza as validações - Approve
	   */
	  public void validateApprove( BaseFncVO fncVO_ )
	  {
		ProdAssetMovementDetailFncVO ProdAssetMovementDetailFncVO = ( ProdAssetMovementDetailFncVO ) fncVO_;
		TplProdAssetMovEntityVO ProdAssetMovEntityVO = ( TplProdAssetMovEntityVO ) ProdAssetMovementDetailFncVO.getBaseTplProdAssetEntity().getData();

		//testar usuário
		if ( ProdAssetMovementDetailFncVO.getLoggedUser().getUserID().equals(
																					  ProdAssetMovEntityVO.getLastUpdUserId() ) )
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
		ProdAssetMovementDetailFncVO movementDetailFncVO = ( ProdAssetMovementDetailFncVO ) fncVO_;
		super.loadProdAsset( movementDetailFncVO );
	  }

	  /**
	   * Carregamentos iniciais - Approve
	   */
	  public void loadForApprove( BaseFncVO fncVO_ )
	  {
		ProdAssetMovementDetailFncVO movementDetailFncVO = ( ProdAssetMovementDetailFncVO ) fncVO_;
		super.loadProdAsset( movementDetailFncVO );
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

		ProdAssetMovementDetailForm prodAssetMovementDetailForm = ( ProdAssetMovementDetailForm ) form_;
		ProdAssetMovementDetailFncVO prodAssetMovementDetailFncVO = ( ProdAssetMovementDetailFncVO ) fncVO_;
		
		TplProdAssetMovEntityVO prodAssetMovEntityVO = ( TplProdAssetMovEntityVO ) prodAssetMovementDetailFncVO.getBaseTplProdAssetEntity().getData();

		String opernCode = prodAssetMovEntityVO.getOpernCode();
		

		if ( opernCode != null && !"".equals( opernCode ) )
		{
		  prodAssetMovementDetailForm.setOpernCode( ODSConstraintDecoder.decodeOpern( opernCode ) );
		}
	  }



}
