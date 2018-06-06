package com.citibank.newcpb.action;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.ModuleUtils;

import com.citibank.newcpb.form.BaseForm;
import com.citibank.ods.common.configuration.Configuration;
import com.citibank.ods.common.user.User;

public abstract class CommonAction extends Action {

	protected String className = this.getClass().getSimpleName();
	public static final String C_ACTION_EXECUTE = "Execute";
	public static final String C_MODE_LIST = "List";

	/** Nome da vari�vel de sess�o que armazena o usu�rio logado. */
	public static final String C_USER_SESSION_ID = "user";

	/**
	 * Foward para tela de LogOff do caminho completo JNDI do UserTransaction
	 */
	protected static String C_LOGOFF_FOWARD_KEY = "ODSExit";

	/**
	 * 
	 * Remove refer�ncia de Value Objects e Forms carregados na mem�ria.
	 * 
	 * @param request_
	 *            - HttpServletRequest de onde ser�o obtidos os objetos que
	 *            estar�o sem se��o que v�o ser removidos.
	 */
	public void clearVOandFormsfromSession(HttpServletRequest request_, ActionForm form_) {
		Enumeration attributeNames = request_.getSession().getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String nextSessionKey = attributeNames.nextElement().toString();
			if (nextSessionKey.endsWith("VO")) {
				request_.getSession().removeAttribute(nextSessionKey);
			}
			if (nextSessionKey.endsWith("Form") && !form_.getClass().getName().endsWith(nextSessionKey)) {
				request_.getSession().removeAttribute(nextSessionKey);
			}

		}
	}

	/**
	 * 
	 * Adiciona ao form, por refer�ncia, os objetos necess�rios ao processo de
	 * forma��o e parsing de valores com m�scaras: - Locale da sess�o e
	 * MessageResources da aplica��o. - MessageResources da aplica��o.
	 * 
	 * @param actionForm_
	 *            inst�ncia do Form onde os objetos Locale MessageResources
	 *            ser�o adicionados.
	 * @param request_
	 *            - HttpServletRequest de onde ser�o obtidos os objetos que
	 *            ser�o adicionados ao Form.
	 */
	protected void handleForm(ActionForm actionForm_, HttpServletRequest request_) {
		if (actionForm_ instanceof BaseForm) {
			BaseForm baseForm = (BaseForm) actionForm_;
			HttpSession session = request_.getSession();
			baseForm.setLoggedUser((User) session.getAttribute(C_USER_SESSION_ID));
			baseForm.setCurrentLocale((Locale) session.getAttribute(Globals.LOCALE_KEY));

			MessageResources messageResources = (MessageResources) request_.getAttribute(Globals.MESSAGES_KEY);
			if (messageResources == null) {
				ServletContext servletContext = session.getServletContext();
				ModuleConfig moduleConfig = ModuleUtils.getInstance().getModuleConfig(null, request_, servletContext);
				messageResources = (MessageResources) servletContext.getAttribute(Globals.MESSAGES_KEY + moduleConfig.getPrefix());
				if (messageResources == null) {
					messageResources = (MessageResources) servletContext.getAttribute(Globals.MESSAGES_KEY);
				}
			}
			baseForm.setCurrentMessageResources(messageResources);
		}
	}

	/**
	 * 
	 * Publica em sess�o um Locale parametrizado para a aplica��o. Caso n�o
	 * exista uma parametriza��o de Locale para a aplica��o, ser� utilizado o
	 * Locale default do request.
	 * 
	 * @param request_-
	 *            HttpServletRequest de onde ser� obtido o Locale defult.
	 */
	protected void handleLocale(HttpServletRequest request_) {
		HttpSession session = request_.getSession();
		Locale userLocale = (Locale) session.getAttribute(Globals.LOCALE_KEY);
		if (userLocale == null) {

			// Obt�m a configura��o para languageCode
			String languageCode = Configuration.getInstance().getValue("locale.language");
			// Obt�m a configura��o para countryCode
			String countryCode = Configuration.getInstance().getValue("locale.country");

			if (languageCode != null) {
				if (countryCode != null) {
					// cria inst�ncia de Locale sem definir a caracter�stica de
					// Variant
					userLocale = new Locale(languageCode, countryCode);
				} else {
					// cria inst�ncia de Locale sem definir as caracter�sticas
					// de CountryCode e Variant
					userLocale = new Locale(languageCode);
				}
			}

			if (userLocale == null) {
				userLocale = request_.getLocale();
			}
			session.setAttribute(Globals.LOCALE_KEY, userLocale);
		}
	}

	public ActionMessages cloneMessage(BaseForm form) {

		ActionMessages messages = new ActionMessages();

		if (form.getMessages() != null) {
			Iterator actualMessages = form.getMessages().get();

			while (actualMessages.hasNext()) {
				ActionMessage actualMsg = (ActionMessage) actualMessages.next();
				ActionMessage newMessage = new ActionMessage(actualMsg.getKey(), actualMsg.getValues());

				messages.add(newMessage.getKey(), newMessage);

			}
		}
		return messages;
	}

	public ActionMessages cloneErrors(BaseForm form) {

		ActionErrors errors = new ActionErrors();

		if (form.getErrors() != null) {
			Iterator actualErrors = form.getErrors().get();

			while (actualErrors.hasNext()) {
				ActionMessage teste = (ActionMessage) actualErrors.next();
				ActionMessage newMessage = new ActionMessage(teste.getKey(), teste.getValues());

				errors.add(newMessage.getKey(), newMessage);

			}
		}
		return errors;
	}

}