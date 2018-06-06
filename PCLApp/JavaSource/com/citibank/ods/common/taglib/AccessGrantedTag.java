//
//�2002-2007 Accenture. All rights reserved. 
//
/**
* CustomTag que verifica se o usu�rio possui permiss�o para acessar uma funcionalidade do sistema
* 
* @see com.citibank.ods.common.taglib;
* @version 1.0
* @author marcelo.s.oliveira,June 1 , 2007
* 
*/
package com.citibank.ods.common.taglib;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;

import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.security.AuthorizationSG;
import com.citibank.ods.common.security.SGFunctionTrimmer;
import com.citibank.ods.common.user.User;


public class AccessGrantedTag extends BaseBodyTag{
	private String m_SGFunction;

	public int doStartTag() throws JspException {
		//get the user object from the session
		HttpSession session = pageContext.getSession();
		User user = (User)session.getAttribute("user");
				
		boolean hasAccess = false;
		try {
			if(m_SGFunction.contains("NEWCPB")){
				hasAccess = validateAccessNEWCPB(user,m_SGFunction);
			}else{
				//Compacta a fun��o passada como parametro na customTag
				SGFunctionTrimmer sgFuncTrimmer = new SGFunctionTrimmer();		
				String  compactedSGFunction = sgFuncTrimmer.compactSGFunction(m_SGFunction);
				
				// Validates if the user has acces to the URI
				hasAccess = validateAccess(user,compactedSGFunction);				
			}
		} catch (UnexpectedException ex) {
			throw new JspException(ex.getMessage());
		} 

		if (hasAccess) {
			return super.EVAL_BODY_BUFFERED;
		} else {
			return super.SKIP_BODY;
		}
	}
	
	private boolean validateAccess(User user_, String pathInfo_)
    {
        boolean hasAccess = false;
        AuthorizationSG autorizationSG = new AuthorizationSG();
        hasAccess = autorizationSG.hasAccess(user_, pathInfo_);
        return hasAccess;
    }
	
	private boolean validateAccessNEWCPB(User user_, String pathInfo_)
    {
        boolean hasAccess = false;
        AuthorizationSG autorizationSG = new AuthorizationSG();
        hasAccess = autorizationSG.hasNEWCPBAccess(user_, pathInfo_);
        return hasAccess;
    }
	
	/**
	 * Writes the accumulated body contents to the JspWriter.
	 */
	public int doEndTag() throws JspException {
		// Test if bodyContent is set, since it will be null if the
		// body was never evaluated (doStartTag returned SKIP_BODY)
		if (getBodyContent() != null) {
			try {
				getPreviousOut().print(getBodyContent().getString());
			} catch (IOException e) {
				throw new JspException("error writing bodycontent");
			}
		}
		return EVAL_PAGE;
	}
	
	public void setm_SGFunction(String sgFunction_) {
	      this.m_SGFunction = sgFunction_;
	}

}