/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.modules.client.er.action;

import com.citibank.ods.common.action.BaseODSDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.er.functionality.ERDetailFnc;
import com.citibank.ods.modules.client.er.functionality.valueobject.ERDetailFncVO;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class ERDetailAction extends BaseODSDetailAction
{

	/*
	 * Parte do nome do módulo ou ação
	 */
	private static final String C_SCREEN_NAME = "ER.ERDetail";

	/**
	 * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
	 */
	public String getFncVOPublishName()
	{
	  return ERDetailFncVO.class.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
	 */
	protected BaseFnc getFuncionality()
	{
	  return new ERDetailFnc();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.common.action.BaseODSAction#getScreenName()
	 */
	protected String getScreenName()
	{
	  return C_SCREEN_NAME;
	}

	/**
	 * Realiza a pesquisa
	 */
	protected String searchActions( BaseFncVO fncVO_, String invokePath_ )
	{
	  return null;

	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.action.BaseODSAction#extraActions(com.citibank.ods.common.functionality.valueobject.BaseFncVO, java.lang.String)
	 */
	protected String extraActions(BaseFncVO fncVO_, String invokePath_) {
		
		return null;
	}
}
