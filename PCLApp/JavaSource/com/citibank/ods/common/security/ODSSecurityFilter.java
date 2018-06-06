//
//©2002-2007 Accenture. All rights reserved. 
//
/**
* Filtro de autenticação com implementações específicas para o ODS
* 
* @see pcom.citibank.ods.common.taglib;
* @version 1.0
* @author marcelo.s.oliveira,June 1 , 2007
* 
*/
package com.citibank.ods.common.security;

import com.citibank.ods.common.configuration.Configuration;


public class ODSSecurityFilter extends BaseSecurityFilter{
	private static final String C_NOT_LOGGED_PAGE = "/View/Error/notLogged.jsp";
	private static final String C_ACCESS_DENIED_PAGE = "/View/Error/accessDenied.jsp";
	private static final String C_USER_SESSION_REFERENCE = "user";
	private static final String C_MENU_ACTION = "/ODSMenu.Show";
	private static final String C_DISCLAIMER_ACTION = "/Disclaimer.show";
	private static final String C_LOGOFF_ACTION = "/ODSLogoff.Show";
	private static final String C_MENU_CLIENT_ACTION = "/ODSMenuClient.Show";
	private static final String C_MENU_CONTROL_ACTION = "/ODSMenuControl.Show";
	private static final String C_MENU_PRODUCTS_ACTION = "/ODSMenuProducts.Show";
	private static final String C_MENU_NEW_CPB_ACTION = "/ODSMenuNewCPB.Show";
	private static final String C_STARTUP_ACTION = (Configuration.getInstance()==null?"/Startup.Execute":Configuration.getInstance().getValue("startUpAction" ));
	
	public boolean isRestrictedAction( String pathInfo_ )
	{
		boolean isRestricted = true;
		if (!( pathInfo_.equalsIgnoreCase( C_MENU_ACTION ) ||
				pathInfo_.equalsIgnoreCase( C_LOGOFF_ACTION ) ||
				pathInfo_.equalsIgnoreCase( C_DISCLAIMER_ACTION ) ||
				pathInfo_.equalsIgnoreCase( C_MENU_CLIENT_ACTION ) ||
				pathInfo_.equalsIgnoreCase( C_MENU_CONTROL_ACTION ) ||
				pathInfo_.equalsIgnoreCase( C_MENU_PRODUCTS_ACTION ) ||
				pathInfo_.equalsIgnoreCase( C_MENU_NEW_CPB_ACTION ) ||
				pathInfo_.equalsIgnoreCase( C_STARTUP_ACTION )) )
				{
					isRestricted = true;
				}else{
					isRestricted = false;
				}
		return isRestricted;
	}
	
	public String getNotLoggedPage() {
		return C_NOT_LOGGED_PAGE;
	}
	
	public String getAccessDeniedPage(){
		return C_ACCESS_DENIED_PAGE;
	}

	public String getUserSessionReference() {
		return C_USER_SESSION_REFERENCE;
	}
	
}
