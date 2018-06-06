/*
 * Created on 13/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodassettype.functionality;

import java.math.BigInteger;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplProdAssetTypeEntity;
import com.citibank.ods.entity.pl.TplProdAssetTypeHistEntity;
import com.citibank.ods.entity.pl.TplProdAssetTypeMovEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplProdAssetTypeEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdAssetTypeMovEntityVO;
import com.citibank.ods.modules.product.prodassettype.form.ProdAssetTypeMovementDetailForm;
import com.citibank.ods.modules.product.prodassettype.functionality.valueobject.ProdAssetTypeMovementDetailFncVO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetTypeDAO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetTypeHistDAO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetTypeMovDAO;
import com.citibank.ods.persistence.pl.dao.TplProductDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.persistence.pl.dao.BaseTplProdAssetTypeDAO;
import com.citibank.ods.entity.pl.valueobject.TplProdAssetTypeEntityVO;

/**
 * @author lfabiano
 * @since 13/10/2008
 */
public class ProdAssetTypeMovementDetailFnc extends
	  BaseProdAssetTypeDetailFnc implements ODSMovementDetailFnc
{
	/**
	   * Retorna o DAO utilizado pelo método load da super classe
	   * 
	   * @see com.citibank.ods.modules.product.productfamilyprvt.functionality.BaseProductFamilyPrvtDetailFnc#getDAO()
	   */
	  protected BaseTplProdAssetTypeDAO getDAO()
	  {
		return ODSDAOFactory.getInstance().getTplProdAssetTypeMovDAO();
	  }

	  /**
	   * Retorna uma instância do FncVO
	   */
	  public BaseFncVO createFncVO()
	  {
		return new ProdAssetTypeMovementDetailFncVO();		
	  }

	  /**
	   * Realiza a atualização de um registro de movimento
	   */
	  public void update( BaseFncVO fncVO_ )
	  {
		validateUpdate( fncVO_ );

		if ( !fncVO_.hasErrors() )
		{
			ProdAssetTypeMovementDetailFncVO movementDetailFncVO = ( ProdAssetTypeMovementDetailFncVO ) fncVO_;
			BaseTplProdAssetTypeEntityVO baseTplProdAssetTypeEntityVO = movementDetailFncVO.getBaseTplProdAssetTypeEntity().getData();

		  baseTplProdAssetTypeEntityVO.setLastUpdDate( new Date() );
		  baseTplProdAssetTypeEntityVO.setLastUpdUserId( fncVO_.getLoggedUser() != null
																						   ? fncVO_.getLoggedUser().getUserID()
																						   : "" );

		  TplProdAssetTypeMovDAO prodAssetTypeMovDAO = ODSDAOFactory.getInstance().getTplProdAssetTypeMovDAO();
		  prodAssetTypeMovDAO.update( ( TplProdAssetTypeMovEntity ) movementDetailFncVO.getBaseTplProdAssetTypeEntity() );
		}
	  }

	/**
	   * Realiza a aprovação de um registro que está pendente de aprovação
	   */
	  public void approve( BaseFncVO fncVO_ )
	  {
		fncVO_.clearErrors();
		
		validateApprove( fncVO_ );

		if ( !fncVO_.hasErrors() )
		{
		  // Instancia da Factory
		  ODSDAOFactory factory = ODSDAOFactory.getInstance();

		  // Declaracao dos DAOs
		  TplProdAssetTypeDAO tplProdAssetTypeDAO = factory.getTplProdAssetTypeDAO();
		  TplProdAssetTypeMovDAO tplProdAssetTypeMovDAO = factory.getTplProdAssetTypeMovDAO();
		  TplProdAssetTypeHistDAO tplProdAssetTypeHistDAO = factory.getTplProdAssetTypeHistDAO();

		  // FncVO do movimento
		  ProdAssetTypeMovementDetailFncVO movementDetailFncVO = ( ProdAssetTypeMovementDetailFncVO ) fncVO_;
			 super.loadProdAssetType( movementDetailFncVO );
		  
		  // Entity do movimento
		  TplProdAssetTypeMovEntity prodAssetTypeMovEntity = ( TplProdAssetTypeMovEntity ) movementDetailFncVO.getBaseTplProdAssetTypeEntity();

		  // OpernCode
		  String opernCode = ( ( TplProdAssetTypeMovEntityVO ) prodAssetTypeMovEntity.getData() ).getOpernCode();

		  TplProdAssetTypeEntity tplProdAssetTypeEntity = new TplProdAssetTypeEntity(prodAssetTypeMovEntity,
																		new Date(),
																		fncVO_.getLoggedUser().getUserID(),
																		TplProdAssetTypeEntity.C_REC_STAT_CODE_ACTIVE );

		  // Verifica qual operacao está sendo aprovada
		  if ( TplProdAssetTypeMovEntity.C_OPERN_CODE_DELETE.equals( opernCode ) )
		  {
			//setar estatus como inativo
			TplProdAssetTypeEntityVO tplProdutFamilyPrvtEntityVO = ( TplProdAssetTypeEntityVO ) tplProdAssetTypeEntity.getData();
			tplProdutFamilyPrvtEntityVO.setRecStatCode( TplProdAssetTypeEntity.C_REC_STAT_CODE_INACTIVE );
		  }

		  if ( tplProdAssetTypeDAO.exists( tplProdAssetTypeEntity ) )
		  {
			TplProdAssetTypeEntity tplProdAssetTypeEntityOld = ( TplProdAssetTypeEntity ) tplProdAssetTypeDAO.find( tplProdAssetTypeEntity );
			TplProdAssetTypeHistEntity prodAssetTypeHistEntity = new TplProdAssetTypeHistEntity(
																					tplProdAssetTypeEntityOld,
																					new Date() );
			tplProdAssetTypeHistDAO.insert( prodAssetTypeHistEntity );

			tplProdAssetTypeDAO.update( tplProdAssetTypeEntity );
		  }
		  else
		  {
			tplProdAssetTypeDAO.insert( tplProdAssetTypeEntity );
		  }
			tplProdAssetTypeMovDAO.delete( prodAssetTypeMovEntity );
		}

	  }

	  /**
	   * Rejeita uma ação que está pendente de aprovação
	   */
	  public void reprove( BaseFncVO fncVO_ )
	  {
		fncVO_.clearErrors();
		//  realiza validação
		validateReprove( fncVO_ );

		if ( !fncVO_.hasErrors() )
		{
		  ProdAssetTypeMovementDetailFncVO movementDetailFncVO = ( ProdAssetTypeMovementDetailFncVO ) fncVO_;
		  TplProdAssetTypeMovEntity prodAssetTypeMovEntity = ( TplProdAssetTypeMovEntity ) movementDetailFncVO.getBaseTplProdAssetTypeEntity();

		  TplProdAssetTypeMovDAO prodAssetTypeMovDAO = ODSDAOFactory.getInstance().getTplProdAssetTypeMovDAO();
		  prodAssetTypeMovDAO.delete( prodAssetTypeMovEntity );
		}
	  }

	  /**
	   * Realiza as Validações - Update
	   */
	  public void validateUpdate( BaseFncVO fncVO_ )
	  {
		ProdAssetTypeMovementDetailFncVO prodAssetTypeMovementDetailFncVO = ( ProdAssetTypeMovementDetailFncVO ) fncVO_;
		TplProdAssetTypeMovEntityVO ProdAssetTypeMovEntityVO = ( TplProdAssetTypeMovEntityVO ) prodAssetTypeMovementDetailFncVO.getBaseTplProdAssetTypeEntity().getData();

		//testar usuário
		if (( !prodAssetTypeMovementDetailFncVO.getLoggedUser().getUserID().equals( ProdAssetTypeMovEntityVO.getLastUpdUserId() ) ) )

		{
		  prodAssetTypeMovementDetailFncVO.addError( BaseODSFncVO.C_ERROR_APPROVAL_UPDATE_USER_NOT_AUTHORIZED );
		}
		else
		{
		  // testar campos obrigatórios
		  if ( ProdAssetTypeMovEntityVO.getProdAssetTypeText() == null
			   || ProdAssetTypeMovEntityVO.getProdAssetTypeText().equals( "" ) )
		  {
			prodAssetTypeMovementDetailFncVO.addError(BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
												   C_PROD_ASSETTYPE_TEXT );
		  }
		  if ( prodAssetTypeMovementDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdSubAssetCode() == null
			   || prodAssetTypeMovementDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdSubAssetCode().equals("") )
		  {
			fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_PROD_SUB_ASSET_TEXT );
		  }
		  
	   }

	   //  Se opernCode = Delete, adicionar erros
	  if ( !fncVO_.hasErrors() )
	  {
		  TplProdAssetTypeMovDAO tplProdAssetTypeMovDAO = ODSDAOFactory.getInstance().getTplProdAssetTypeMovDAO();
		  TplProdAssetTypeMovEntity tplProdAssetTypeMovEntity = ( TplProdAssetTypeMovEntity ) tplProdAssetTypeMovDAO.find( prodAssetTypeMovementDetailFncVO.getBaseTplProdAssetTypeEntity() );

		  String opernCode = ( ( TplProdAssetTypeMovEntityVO ) tplProdAssetTypeMovEntity.getData() ).getOpernCode();

		  if ( TplProdAssetTypeMovEntity.C_OPERN_CODE_DELETE.equals( opernCode ) )
		  {
			prodAssetTypeMovementDetailFncVO.addError( ProdAssetTypeMovementDetailFncVO.C_ERROR_CANNOT_UPD_DELETE_IN_MOVEMENT );
		  }
		}
	  }

	  /**
	   * Realiza as validações - Approve
	   */
	  public void validateApprove( BaseFncVO fncVO_ )
	  {
		ProdAssetTypeMovementDetailFncVO ProdAssetTypeMovementDetailFncVO = ( ProdAssetTypeMovementDetailFncVO ) fncVO_;
		TplProdAssetTypeMovEntityVO ProdAssetTypeMovEntityVO = ( TplProdAssetTypeMovEntityVO ) ProdAssetTypeMovementDetailFncVO.getBaseTplProdAssetTypeEntity().getData();

		//testar usuário
		if ( ProdAssetTypeMovementDetailFncVO.getLoggedUser().getUserID().equals(
																					  ProdAssetTypeMovEntityVO.getLastUpdUserId() ) )
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
		ProdAssetTypeMovementDetailFncVO movementDetailFncVO = ( ProdAssetTypeMovementDetailFncVO ) fncVO_;
		
		if(movementDetailFncVO.isRefreshAssetCode()){
		  if(movementDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdSubAssetCode()!= null){
			super.loadAssetBySubAsset(movementDetailFncVO);
		  }
		  else{
		    movementDetailFncVO.getBaseTplProdAssetTypeEntity().getData().setProdAssetCode(null);
		  }
		}
		else{
			super.loadProdAssetType( movementDetailFncVO );
			super.loadDomains(movementDetailFncVO);
		}
		
	  }

	  /**
	   * Carregamentos iniciais - Approve
	   */
	  public void loadForApprove( BaseFncVO fncVO_ )
	  {
		ProdAssetTypeMovementDetailFncVO movementDetailFncVO = ( ProdAssetTypeMovementDetailFncVO ) fncVO_;
		super.loadProdAssetType( movementDetailFncVO );
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
		
		//Acertando os Tipos
		ProdAssetTypeMovementDetailForm prodAssetTypeForm = ( ProdAssetTypeMovementDetailForm ) form_;
		ProdAssetTypeMovementDetailFncVO prodAssetTypeFncVO = ( ProdAssetTypeMovementDetailFncVO ) fncVO_;
		
        //Atualizando os dados: FncVO -> Form
		TplProdAssetTypeMovEntityVO prodAssetTypeMovEntityVO = ( TplProdAssetTypeMovEntityVO ) prodAssetTypeFncVO.getBaseTplProdAssetTypeEntity().getData();

		String opernCode = prodAssetTypeMovEntityVO.getOpernCode();		

		if ( opernCode != null && !"".equals( opernCode ) )
		{
		  prodAssetTypeForm.setOpernCode( ODSConstraintDecoder.decodeOpern( opernCode ) );
		}
	  }



}