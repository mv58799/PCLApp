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

	/** Nome da variável de sessão que armazena o usuário logado. */
	public static final String C_USER_SESSION_ID = "user";

	/**
	 * Foward para tela de LogOff do caminho completo JNDI do UserTransaction
	 */
	protected static String C_LOGOFF_FOWARD_KEY = "ODSExit";

	/**
	 * 
	 * Remove referência de Value Objects e Forms carregados na memória.
	 * 
	 * @param request_
	 *            - HttpServletRequest de onde serão obtidos os objetos que
	 *            estarão sem seção que vão ser removidos.
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
	 * Adiciona ao form, por referência, os objetos necessários ao processo de
	 * formação e parsing de valores com máscaras: - Locale da sessão e
	 * MessageResources da aplicação. - MessageResources da aplicação.
	 * 
	 * @param actionForm_
	 *            instância do Form onde os objetos Locale MessageResources
	 *            serão adicionados.
	 * @param request_
	 *            - HttpServletRequest de onde serão obtidos os objetos que
	 *            serão adicionados ao Form.
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
	 * Publica em sessão um Locale parametrizado para a aplicação. Caso não
	 * exista uma parametrização de Locale para a aplicação, será utilizado o
	 * Locale default do request.
	 * 
	 * @param request_-
	 *            HttpServletRequest de onde será obtido o Locale defult.
	 */
	protected void handleLocale(HttpServletRequest request_) {
		HttpSession session = request_.getSession();
		Locale userLocale = (Locale) session.getAttribute(Globals.LOCALE_KEY);
		if (userLocale == null) {

			// Obtém a configuração para languageCode
			String languageCode = Configuration.getInstance().getValue("locale.language");
			// Obtém a configuração para countryCode
			String countryCode = Configuration.getInstance().getValue("locale.country");

			if (languageCode != null) {
				if (countryCode != null) {
					// cria instância de Locale sem definir a característica de
					// Variant
					userLocale = new Locale(languageCode, countryCode);
				} else {
					// cria instância de Locale sem definir as características
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