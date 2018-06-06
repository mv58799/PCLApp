package com.citibank.newcpb.logger;

public final class LoggerFactory {

	public static void initialize() {
		ApplicationLogger.init();
	}
	
	public static ApplicationLogger getApplicationLoggerInstance() {
		return ApplicationLogger.getInstance();
	}
}
