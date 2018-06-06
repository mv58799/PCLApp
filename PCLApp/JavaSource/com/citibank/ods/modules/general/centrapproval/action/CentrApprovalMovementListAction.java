/*
 * Created on 06/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.general.centrapproval.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.general.centrapproval.functionality.CentrApprovalMovementListFnc;
import com.citibank.ods.modules.general.centrapproval.functionality.valueobject.CentrApprovalMovementListFncVO;

/**
 * @author Ronaldo Machado (G&P Java Team)
 * 
 */
public class CentrApprovalMovementListAction extends BaseODSListAction {
	
	/*
	* Parte do nome do módulo ou ação
	*/
    private static final String C_SCREEN_NAME = "CentrApproval.CentrApprovalMovementList";	

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.action.BaseODSAction#getFuncionality()
	 */
	protected BaseFnc getFuncionality() {
		// TODO Auto-generated method stub
		return new CentrApprovalMovementListFnc();
		
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.action.BaseODSAction#getScreenName()
	 */
	protected String getScreenName() {		
		return C_SCREEN_NAME;
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.action.BaseODSAction#extraActions(com.citibank.ods.common.functionality.valueobject.BaseFncVO, java.lang.String)
	 */
	protected String extraActions(BaseFncVO fncVO_, String invokePath_) {
		return null;		
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.action.BaseAction#getFncVOPublishName()
	 */
	public String getFncVOPublishName() {
		// TODO Auto-generated method stub
		return CentrApprovalMovementListFncVO.class.getName();		
	}

	protected String showAction( BaseFncVO fncVO_, String mode_ )
	{
		CentrApprovalMovementListFncVO fncVO = ( CentrApprovalMovementListFncVO ) fncVO_;

		if ( mode_.equals( C_MODE_LIST ) )
		{
			fncVO.setNameCleanSession(CentrApprovalMovementListFncVO.class.getName());
		}
		return super.showAction(fncVO_, mode_);
	}

}
