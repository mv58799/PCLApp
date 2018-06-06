package com.citibank.newcpb.logger;

import java.util.PropertyResourceBundle;

import com.citibank.newcpb.exception.UnexpectedException;
import com.citibank.newcpb.util.DefStaticVars;
import com.citibank.sad.logmanager.ILogger;
import com.citibank.sad.logmanager.LogManager;

public class ApplicationLogger {
	private static ApplicationLogger ms_instance = null;
	protected ILogger m_logger = null;

	private ApplicationLogger() {
		try {
			String logProperties = DefStaticVars.getInstance().logProperties;

			PropertyResourceBundle resourceBundle = new PropertyResourceBundle(
					getClass().getClassLoader().getResourceAsStream(logProperties));

			m_logger = LogManager.getLogger("PrivateLayerApplication", ILogger.LOGGER_TO_FILE, resourceBundle);
		} catch (Exception e) {
			throw new UnexpectedException("Erro ao obter instância do logger.", e);
		}
	}

	public void info(String message_) {
		m_logger.logInfo(message_);
	}

	public void warn(String message_) {
		m_logger.logWarning(message_);
	}

	public void debug(String message_) {
		m_logger.logDebug(message_);
	}

	public void error(String message_, Throwable t_) {
		m_logger.logError(message_ + "\n StackTrace: ");
		m_logger.logException(t_);
	}

	public void error(String message_) {
		m_logger.logError(message_);
	}

	public static ApplicationLogger getInstance() {
		if (ms_instance == null) {
			throw new RuntimeException("Application Logger not initialized");
		}
		return ms_instance;
	}

	public static void init() {
		if (ms_instance == null) {
			createSingletonInstance();
		}
	}

	private static synchronized void createSingletonInstance() {
		if (ms_instance == null) {
			ms_instance = new ApplicationLogger();
		}
	}
}