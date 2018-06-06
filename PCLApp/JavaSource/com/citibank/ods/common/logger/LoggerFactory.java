/*
 * Created on Apr 20, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.common.logger;

/**
 * @author marcelo.s.oliveira
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public final class LoggerFactory {	

	/**
	 * Cria as instancias dos loggers
	 *
	 */
	public static void initialize()
	{
		//TraceLogger.init();
		ApplicationLogger.init();
		//AuditTrailLogger.init();
	}
	
	/**
	 * Obtem a instancia do TraceLogger
	 * @return
	 */
	public static TraceLogger getTraceLoggerInstance()
	{
		return TraceLogger.getInstance();
	}
	
	public static AuditTrailLogger getAuditTrailLoggerInstance()
	{
		return AuditTrailLogger.getInstance();
	}
	
	public static ApplicationLogger getApplicationLoggerInstance()
	{
		return ApplicationLogger.getInstance();
	}
}
