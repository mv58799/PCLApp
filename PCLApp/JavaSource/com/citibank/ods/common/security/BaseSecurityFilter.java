// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)
// Source File Name: BaseSecurityFilter.java

package com.citibank.ods.common.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.citibank.ods.common.configuration.Configuration;
import com.citibank.ods.common.user.User;

// Referenced classes of package com.citibank.ods.common.security:
//            AuthorizationSG

public abstract class BaseSecurityFilter implements Filter {
	// Tela inicial do sistema
	private static final String C_STARTUP_ACTION_PATH = "/FrontController/Startup.Execute";

	// Tela de login no ambiente de desenvolvimento
	private static final String C_STARTUP_ACTION_PATH_DEV = "/View/StartWithMockSG.jsp";

	public BaseSecurityFilter() {
		m_attribute = null;
	}

	public void init(FilterConfig filterConfig_) throws ServletException {
		m_attribute = filterConfig_.getInitParameter("attribute");
	}

	public void doFilter(ServletRequest request_, ServletResponse response_,
			FilterChain chain_) throws IOException, ServletException {
		// Casting de objetos request e respone para HTTP para redirecionamento
		HttpServletRequest m_request = (HttpServletRequest) request_;
		HttpServletResponse m_response = (HttpServletResponse) response_;

		// Configura filtro caso ainda não esteja
		if (m_attribute != null) {
			request_.setAttribute(m_attribute, this);
		}
		// Inicialização de variaveis.
		String redirectPage = "";
		String sgFunction = "";
		int requestedPathSize = 0;
		// Flag do ambiente de desenvolvimento
		String isMock = Configuration.getInstance().getValue("isMock");
		
		
		boolean hasProblem = false;
		requestedPathSize = m_request.getPathInfo().length();
		sgFunction = m_request.getPathInfo();
		// Verifica se a URI não é de inicialização ou a de Menu
		if (isRestrictedAction(m_request.getPathInfo())) {

			// Verifica se usuário esta logado.
			if (isLogged(m_request)) {
			
				// Recupera seção de usuário logado.
				User user = (User) m_request.getSession().getAttribute(
						getUserSessionReference());

				if (!"true".equals(isMock)) {
						
	
					if (m_request.getHeader("sm_user").trim().equals(user.getUserID())) {
						

						if(sgFunction.contains("NEWCPB")){
							if (!validateNEWCPBAccess(user, sgFunction)) {
								redirectPage = getAccessDeniedPage();
								hasProblem = true;
							}
						}else{
							// Retira sufixo de limpeza caso existir
							if (sgFunction.substring(requestedPathSize - 5).equals(
									"Clear")) {
								sgFunction = sgFunction.substring(0,
										requestedPathSize - 5);
							}

							// Compacta a função passada como parametro na customTag
							SGFunctionTrimmer sgFuncTrimmer = new SGFunctionTrimmer();
							String sgCompactedFunction = sgFuncTrimmer
									.compactSGFunction(sgFunction);

							// Verifica se URI compactada esta Não na lista de
							// funções
							// carregadas
							// na instância do usuário.
							if (!validateAccess(user, sgCompactedFunction)) {
								// Recupera página de acesso negado segundo
								// configuração
								// específica
								// da classe filha.
								redirectPage = getAccessDeniedPage();
								hasProblem = true;
							}
						}
					} else {
						// Recupera página de erro de autenticação segundo
						// configuração
						// específica da classe filha.

						redirectPage = C_STARTUP_ACTION_PATH;
						hasProblem = true;
					}
				} else {
					
					if(sgFunction.contains("NEWCPB")){
						if (!validateNEWCPBAccess(user, sgFunction)) {
							redirectPage = getAccessDeniedPage();
							hasProblem = true;
						}
					}else{
					
						// Retira sufixo de limpeza caso existir
						if (sgFunction.substring(requestedPathSize - 5).equals(
								"Clear")) {
							sgFunction = sgFunction.substring(0,
									requestedPathSize - 5);
						}
	
						// Compacta a função passada como parametro na customTag
						SGFunctionTrimmer sgFuncTrimmer = new SGFunctionTrimmer();
						String sgCompactedFunction = sgFuncTrimmer
								.compactSGFunction(sgFunction);
	
						// Verifica se URI compactada esta Não na lista de funções
						// carregadas
						// na instância do usuário.
						if (!validateAccess(user, sgCompactedFunction)) {
							// Recupera página de acesso negado segundo configuração
							// específica
							// da classe filha.
							redirectPage = getAccessDeniedPage();
							hasProblem = true;
						}
					}
				}
			} else {

				// Recupera página de erro de autenticação segundo configuração
				// específica da classe filha.
				redirectPage = C_STARTUP_ACTION_PATH;
				hasProblem = true;

			}
		}

		// Finaliza filtro
		if (!hasProblem) {
			chain_.doFilter(request_, response_);
		} else {
			// Redirenciona sem finalizar o filtro
			RequestDispatcher rd = m_request.getRequestDispatcher(redirectPage);
			rd.forward(m_request, m_response);
		}
	}

	public abstract boolean isRestrictedAction(String pathInfo_);

	private boolean validateAccess(User user_, String pathInfo_) {
		boolean hasAccess = false;
		AuthorizationSG autorizationSG = new AuthorizationSG();
		hasAccess = autorizationSG.hasAccess(user_, pathInfo_);
		return hasAccess;
	}
	
	private boolean validateNEWCPBAccess(User user_, String pathInfo_) {
		boolean hasAccess = false;
		AuthorizationSG autorizationSG = new AuthorizationSG();
		hasAccess = autorizationSG.hasNEWCPBAccess(user_, pathInfo_);
		return hasAccess;
	}

	private boolean isLogged(HttpServletRequest request_) {
		if (request_.getSession().getAttribute("user") != null) {
			return true;
		} else {
			return false;
		}
	}

	public void destroy() {
	}

	public abstract String getNotLoggedPage();


	public abstract String getAccessDeniedPage();
	public abstract String getUserSessionReference();

	private FilterConfig m_filterConfig;

	private String m_attribute;
}

