/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.modules.client.er.functionality;

import java.util.ArrayList;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplErEntity;
import com.citibank.ods.entity.pl.TplErHistEntity;
import com.citibank.ods.entity.pl.TplErMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplErEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplErMovEntityVO;
import com.citibank.ods.modules.client.er.form.ERMovementDetailForm;
import com.citibank.ods.modules.client.er.functionality.valueobject.BaseERDetailFncVO;
import com.citibank.ods.modules.client.er.functionality.valueobject.ERMovementDetailFncVO;
import com.citibank.ods.persistence.pl.dao.TplErDAO;
import com.citibank.ods.persistence.pl.dao.TplErHistDAO;
import com.citibank.ods.persistence.pl.dao.TplErMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class ERMovementDetailFnc extends BaseERDetailFnc implements
 ODSMovementDetailFnc {

	
	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
	 */
	public BaseFncVO createFncVO() {		
		return new ERMovementDetailFncVO();
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void update(BaseFncVO fncVO_)
	{
	  validateUpdate( fncVO_ );
	  if ( !fncVO_.hasErrors() )
	  {
	    ERMovementDetailFncVO movementDetailFncVO = ( ERMovementDetailFncVO ) fncVO_;
		TplErMovEntityVO tplErMovEntityVO = (TplErMovEntityVO) movementDetailFncVO.getBaseTplErEntity().getData();

	    tplErMovEntityVO.setLastUpdDate( new Date() );
	    tplErMovEntityVO.setLastUpdUserId( fncVO_.getLoggedUser() != null ? fncVO_.getLoggedUser().getUserID()	: "" );

	    TplErMovDAO erMovDAO = ODSDAOFactory.getInstance().getTplErMovDAO();
        erMovDAO.update( ( TplErMovEntity ) movementDetailFncVO.getBaseTplErEntity() );
	  }

	}


	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#approve(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void approve(BaseFncVO fncVO_) {
	  // Instancia da Factory
	  ODSDAOFactory factory = ODSDAOFactory.getInstance();

	  // Declaração dos DAOs
	  TplErDAO tplErDAO = factory.getTplErDAO();
	  TplErMovDAO tplErMovDAO = factory.getTplErMovDAO();
	  TplErHistDAO tplErHistDAO = factory.getTplErHistDAO();

	  // FncVO do movimento
	  ERMovementDetailFncVO fncVO = ( ERMovementDetailFncVO ) fncVO_;	  

	  // Entity do movimento
	  TplErMovEntity tplErMovEntity = ( TplErMovEntity ) fncVO.getBaseTplErEntity();
	
	  String opernCode = ( ( TplErMovEntityVO ) tplErMovEntity.getData() ).getOpernCode();

	  TplErEntity tplErEntity = new TplErEntity(tplErMovEntity,
	  											fncVO_.getLoggedUser().getUserID(),
	  											new Date(),
	  											TplErEntity.C_REC_STAT_CODE_ACTIVE);

	  // Verifica qual operacao está sendo aprovada
	  if ( TplErMovEntity.C_OPERN_CODE_DELETE.equals( opernCode ) )
	  {
		//setar estatus como inativo
		TplErEntityVO tplErEntityVO  = ( TplErEntityVO ) tplErEntity.getData();
		tplErEntityVO.setRecStatCode( TplErEntity.C_REC_STAT_CODE_INACTIVE );
	  }
	  
	  TplErEntity tplErEntityOld;
	  try{
		tplErEntityOld = ( TplErEntity ) tplErDAO.find( tplErEntity );
	  }
	  catch(NoRowsReturnedException e){
		tplErEntityOld = null;
	  }
	  
	  if ( tplErEntityOld!= null)
	  {			
		 TplErHistEntity tplErHistEntity = new TplErHistEntity(tplErEntityOld,new Date() );
		 tplErHistDAO.insert(tplErHistEntity );
		 tplErDAO.update( tplErEntity );
	  }
	  else
	  {
		tplErDAO.insert( tplErEntity );
	  }
	  
	  tplErMovDAO.delete( tplErMovEntity );
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#reprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void reprove(BaseFncVO fncVO_)
	{
	  //realiza validação
	  validateReprove( fncVO_ );
	  if ( !fncVO_.hasErrors() )
	  {
	    ERMovementDetailFncVO fncVO = ( ERMovementDetailFncVO ) fncVO_;
	    TplErMovEntity tplErMovEntity = ( TplErMovEntity ) fncVO.getBaseTplErEntity();

	    TplErMovDAO tplErMovDAO = ODSDAOFactory.getInstance().getTplErMovDAO();
	    tplErMovDAO.delete( tplErMovEntity );
	  }	
		
	}

	/**
	 * Realiza as Validações - Update
	 */
	public void validateUpdate( BaseFncVO fncVO_ )
	{
	  ERMovementDetailFncVO erMovementDetailFncVO = ( ERMovementDetailFncVO ) fncVO_;
	  TplErMovEntityVO erMovEntityVO = ( TplErMovEntityVO ) erMovementDetailFncVO.getBaseTplErEntity().getData();

	  //testar usuário
	  if ( !( erMovementDetailFncVO.getLoggedUser().getUserID().equals( erMovEntityVO.getLastUpdUserId() ) ) )

	  {
		erMovementDetailFncVO.addError( BaseODSFncVO.C_ERROR_APPROVAL_UPDATE_USER_NOT_AUTHORIZED );
	  }
	  else
	  {
		// testar campos obrigatórios
		if ( erMovEntityVO.getErNbr() == null )
		{
			erMovementDetailFncVO.addError(BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_ER_NBR );
		}
	 }

	 //  Se opernCode = Delete, adicionar erros
	if ( !fncVO_.hasErrors() )
	{
		TplErMovDAO erMovDAO = ODSDAOFactory.getInstance().getTplErMovDAO();
		TplErMovEntity erMovEntity = ( TplErMovEntity ) erMovDAO.find( erMovementDetailFncVO.getBaseTplErEntity() );

		String opernCode = ( ( TplErMovEntityVO ) erMovEntity.getData() ).getOpernCode();

		if ( TplErMovEntity.C_OPERN_CODE_DELETE.equals( opernCode ) )
		{
			erMovementDetailFncVO.addError( ERMovementDetailFncVO.C_ERROR_CANNOT_UPD_DELETE_IN_MOVEMENT );
		}
	  }
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void validateApprove(BaseFncVO fncVO_) {
		
		
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateReprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void validateReprove(BaseFncVO fncVO_) {
		
		
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void loadForUpdate(BaseFncVO fncVO_) {

		ERMovementDetailFncVO movementDetailFncVO = ( ERMovementDetailFncVO ) fncVO_;
		this.load(movementDetailFncVO);
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void loadForApprove(BaseFncVO fncVO_) {
		ERMovementDetailFncVO fncVO = (ERMovementDetailFncVO) fncVO_;
		this.load(fncVO);		
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void loadForConsult(BaseFncVO fncVO_) {

		
	}
	
	private void load(BaseFncVO fncVO_){
		
		ERMovementDetailFncVO fncVO = (ERMovementDetailFncVO) fncVO_;
		fncVO.setBaseTplErEntity(ODSDAOFactory.getInstance().getTplErMovDAO().find(fncVO.getBaseTplErEntity()));
		super.loadDomains(fncVO);		
	}
	
	public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
	{

	  super.updateFormFromFncVO( form_, fncVO_ );

	  ERMovementDetailForm form = ( ERMovementDetailForm ) form_;
	  ERMovementDetailFncVO fncVO = ( ERMovementDetailFncVO ) fncVO_;
		
	  TplErMovEntityVO movEntityVO = ( TplErMovEntityVO ) fncVO.getBaseTplErEntity().getData();

	  String opernCode = movEntityVO.getOpernCode();		

	  if ( opernCode != null && !"".equals( opernCode ) )
	  {
		form.setOpernCode( ODSConstraintDecoder.decodeOpern( opernCode ) );
	  }
	}
		
	
}
