/*
 * Created on 14/11/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.client.ipdocprvt.action;

import com.citibank.ods.common.action.BaseODSDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.ipdocprvt.functionality.IpDocTransFinancDetailFnc;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.IpDocTransFinancDetailFncVO;

/**
 * @author lfabiano
 * @since 14/11/2008
 */
public class IpDocTransFinancDetailAction extends BaseODSDetailAction {

	/*
	 * Parte do nome do módulo ou ação
	 */
	private static final String C_SCREEN_NAME =	"IpDocPrvt.IpDocTransFinancDetail";

		/**
	 * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
	 */
	public String getFncVOPublishName() {
		return IpDocTransFinancDetailFncVO.class.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
	 */
	protected BaseFnc getFuncionality() {
		return new IpDocTransFinancDetailFnc();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.common.action.BaseODSAction#getScreenName()
	 */
	protected String getScreenName() {
		return C_SCREEN_NAME;
	}

	/* (non-Javadoc)
	* @see com.citibank.ods.common.action.BaseODSAction#extraActions(com.citibank.ods.common.functionality.valueobject.BaseFncVO, java.lang.String)
	*/
	protected String extraActions( BaseFncVO fncVO_, String invokePath_ )	
   {
	String[] splittedInvokePath = invokePath_.split( "\\." );
	IpDocTransFinancDetailFnc detailFnc = ( IpDocTransFinancDetailFnc ) getFuncionality();
	String forwardKey = "";
	
	return forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ]; 
	
   }



}