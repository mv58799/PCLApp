/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.modules.client.er.functionality;

import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;

import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.TplErEntity;
import com.citibank.ods.entity.pl.TplErMovEntity;
import com.citibank.ods.modules.client.er.functionality.valueobject.BaseERDetailFncVO;
import com.citibank.ods.modules.client.er.functionality.valueobject.ERDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplErDAO;
import com.citibank.ods.persistence.pl.dao.TplErDAO;
import com.citibank.ods.persistence.pl.dao.TplErMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;


/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class ERDetailFnc extends BaseERDetailFnc implements ODSDetailFnc
{
	protected static final String C_ER_NBR = "Número de ER";

	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.client.erem.functionality.BaseEREMDetailFnc#getDAO()
	 */
	protected BaseTplErDAO getDAO() {

		return null;
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
	 */
	public BaseFncVO createFncVO() {		
		return new ERDetailFncVO();
	}

	/**
	 * Realiza a inserção dos dados
     */
	public void insert( BaseFncVO fncVO_ )
	{
	  ERDetailFncVO detailFncVO = ( ERDetailFncVO ) fncVO_;
	  validateInsert( detailFncVO );
	  if ( !detailFncVO.hasErrors() )
	  {
		TplErEntity tplErEntity = ( TplErEntity ) detailFncVO.getBaseTplErEntity();
		tplErEntity.getData().setLastUpdDate( new Date() );
		tplErEntity.getData().setRecStatCode(TplErMovEntity.C_REC_STAT_CODE_ACTIVE);
		tplErEntity.getData().setLastUpdUserId(
												fncVO_.getLoggedUser() != null
																			 ? fncVO_.getLoggedUser().getUserID()
																			 : "" );
		
		
		TplErMovEntity tplErMovEntity = new TplErMovEntity(
		                                                     tplErEntity,
		                                                     TplErMovEntity.C_OPERN_CODE_INSERT );
		                                                     
		TplErMovDAO tplErMovDAO = ODSDAOFactory.getInstance().getTplErMovDAO();
		tplErMovDAO.insert( tplErMovEntity );
	  }

	}

 	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void update(BaseFncVO fncVO_) {
	  ERDetailFncVO detailFncVO = ( ERDetailFncVO ) fncVO_;
	  validateInsert( detailFncVO );
	  if ( !detailFncVO.hasErrors() )
	  {
	 	TplErEntity tplErEntity = ( TplErEntity ) detailFncVO.getBaseTplErEntity();
		tplErEntity.getData().setLastUpdDate( new Date() );
		tplErEntity.getData().setRecStatCode(TplErMovEntity.C_REC_STAT_CODE_ACTIVE);
		tplErEntity.getData().setLastUpdUserId(fncVO_.getLoggedUser() != null ? fncVO_.getLoggedUser().getUserID() : "" );
		
		
		TplErMovEntity tplErMovEntity = new TplErMovEntity(tplErEntity,TplErMovEntity.C_OPERN_CODE_UPDATE );
		                                                     
		TplErMovDAO tplErMovDAO = ODSDAOFactory.getInstance().getTplErMovDAO();
		tplErMovDAO.insert( tplErMovEntity );
	  }		
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#delete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void delete(BaseFncVO fncVO_) {
		
		
	}

	/**
	 * Realiza as validações - INSERT
	 */
	public void validateInsert( BaseFncVO fncVO_ )
	{
		ERDetailFncVO erDetailFncVO = ( ERDetailFncVO ) fncVO_;

	  // Validar Campos Obrigatórios
	  if ( erDetailFncVO.getBaseTplErEntity().getData().getErNbr() == null )
	  {
		fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_ER_NBR );
	  }
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void validateUpdate(BaseFncVO fncVO_) {
		
		
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void validateDelete(BaseFncVO fncVO_) {
		
		
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void loadForConsult(BaseFncVO fncVO_) {
		
		
	}

	/**
	 * Carregamentos iniciais - Insert
	 */
	public void loadForInsert( BaseFncVO fncVO_ )	
	{
		BaseERDetailFncVO fncVO = (BaseERDetailFncVO)fncVO_;		
		super.loadDomains(fncVO); 
		
		fncVO.getBaseTplErEntity().getData().setErNbr(null);
		fncVO.getBaseTplErEntity().getData().setErReltnTrfInd(null);
		fncVO.getBaseTplErEntity().getData().setReltnEndReasCode(null);
		fncVO.getBaseTplErEntity().getData().setReltnEndReasText(null);
		fncVO.getBaseTplErEntity().getData().setEquityClassCode(null);
	}
	
	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void loadForUpdate(BaseFncVO fncVO_) 
	{
	  BaseERDetailFncVO fncVO = (BaseERDetailFncVO)fncVO_;
	  
	  if(this.exists(fncVO))
	  {
		
		if(!this.existsInMovement(fncVO))
		{		
			fncVO.setBaseTplErEntity(ODSDAOFactory.getInstance().getTplErDAO().find(fncVO.getBaseTplErEntity()));
			super.loadDomains(fncVO);			
		}
		else
		{
			fncVO.addError( BaseODSFncVO.C_ERROR_IN_MOVEMENT );
		}
	  	
	  }
	  else{
		  fncVO.addError(BaseODSFncVO.C_ERROR_PK_NOT_FOUND);
	  }	  
		
	}

	/**
	 * Carregamentos iniciais - Delete
	 */
	public void loadForDelete( BaseFncVO fncVO_ )
	{
	  ERDetailFncVO erDetailFncVO = ( ERDetailFncVO ) fncVO_;
	
	  
	  if ( this.existsInMovement( erDetailFncVO ) )
	  {
		erDetailFncVO.addError( ERDetailFncVO.C_ERROR_RELATION_IN_MOVEMENT,
								ERDetailFncVO.C_ER_NBR_DESCRIPTION );
	  }
	  else
	  {
		String erNbr = erDetailFncVO.getBaseTplErEntity().getData().getErNbr();
		TplErDAO tplErDAO = ODSDAOFactory.getInstance().getTplErDAO();
	  }	  
	  
	}
	
	private boolean existsInMovement(BaseERDetailFncVO baseERDetailFncVO)
	{
	  TplErMovDAO erMovDAO = ODSDAOFactory.getInstance().getTplErMovDAO();

	  TplErMovEntity tplErMovEntity = new TplErMovEntity();
	  tplErMovEntity.getData().setErNbr(baseERDetailFncVO.getBaseTplErEntity().getData().getErNbr());

	  return erMovDAO.existsMovement(tplErMovEntity);
	  
	}
	
	private boolean exists(BaseERDetailFncVO baseERDetailFncVO)
	{
		TplErDAO erDAO = ODSDAOFactory.getInstance().getTplErDAO();
		TplErEntity tplErEntity;
		
		try{
			tplErEntity  = (TplErEntity)erDAO.find(baseERDetailFncVO.getBaseTplErEntity());
		}
		catch(NoRowsReturnedException e){
			tplErEntity = null;
		}
		
		if(tplErEntity != null){
			return true; 
		}
		else{
			return false;
		}		
				
	}


}
