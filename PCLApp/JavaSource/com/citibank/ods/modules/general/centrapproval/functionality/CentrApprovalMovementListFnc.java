/*
 * Created on 06/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.general.centrapproval.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.general.centrapproval.functionality.valueobject.CentrApprovalMovementListFncVO;
import com.citibank.ods.persistence.pl.dao.TplCentrApprovalMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author Ronaldo Machado (G&P Java Team) 
 */
public class CentrApprovalMovementListFnc extends BaseCentrApprovaListFnc {

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
	 */
	public BaseFncVO createFncVO() {
		return new CentrApprovalMovementListFncVO();
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void list(BaseFncVO fncVO_) {
		
	  boolean hasAccess5001 = false;
	  if(fncVO_!=null && fncVO_.getLoggedUser()!=null && fncVO_.getLoggedUser().getUserAccess()!=null){
	  	  hasAccess5001 = fncVO_.getLoggedUser().getUserAccess().isHasAccessNovoCPBCustomerCPB();
	  }
		
	  CentrApprovalMovementListFncVO listFncVO = (CentrApprovalMovementListFncVO) fncVO_;
	  //Obtém a instância da Factory
	  ODSDAOFactory factory = ODSDAOFactory.getInstance();
	  //Obtém a instância do DAO necessário
	  TplCentrApprovalMovDAO dao = factory.getTplCentrApprovalMovDAO();
	  DataSet results = dao.list(listFncVO.getMduleProcessText(),  							 
								 listFncVO.getLastUpdUserIdSrc(), hasAccess5001);
	  							 
	  listFncVO.setResults( results );

	  if ( results.size() > 0 )
	  {
		 listFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
	  }
	  else
	  {
	     listFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
	  }
	  
	  //Carrega novamente as informações do combo
	  getDomainLists(listFncVO, hasAccess5001);
	  		
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void load(BaseFncVO fncVO_) {
		
		boolean hasAccess5001 = false;
		if(fncVO_!=null && fncVO_.getLoggedUser()!=null && fncVO_.getLoggedUser().getUserAccess()!=null){
			hasAccess5001 = fncVO_.getLoggedUser().getUserAccess().isHasAccessNovoCPBCustomerCPB();
		}
		
		CentrApprovalMovementListFncVO centrApprovalMovementListFncVO = (CentrApprovalMovementListFncVO) fncVO_;		
		getDomainLists(centrApprovalMovementListFncVO, hasAccess5001);
		centrApprovalMovementListFncVO.setResults(null);
		
	}

}
