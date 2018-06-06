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
 * Preferences - Java - Code Style - Code Templates
 */
public class AuditTrailLogger extends BaseLogger
{
  private static AuditTrailLogger ms_instance = null;

  private AuditTrailLogger()
  {
    super( "PL-AuditTrailLogger" );
  }

  public void info( String message_ )
  {
    m_logger.logInfo( message_ );
  }

  public void warn( String message_ )
  {
    m_logger.logWarning( message_ );
  }

  public void error( String message_, Throwable t_ )
  {
    String stackTrace = "";
    if ( t_ != null )
    {
      StackTraceElement[] elements = t_.getStackTrace();
      for ( int i = 0; i < elements.length; i++ )
      {
        stackTrace = stackTrace + elements[ i ].toString() + "\n";
      }
    }

    m_logger.logError( message_ + "\n StackTrace: " + stackTrace );
  }

  public void error( String message_ )
  {
    m_logger.logError( message_ );
  }

  public static AuditTrailLogger getInstance()
  {
    if ( ms_instance == null )
    {
      throw new RuntimeException( "Audit Trail Logger not initialized" );
    }
    return ms_instance;
  }

  public static void init()
  {
    if ( ms_instance == null )
    {
      createSingletonInstance();
      /**
       * Configure Logger
       */
    }
  }

  private static synchronized void createSingletonInstance()
  {
    if ( ms_instance == null )
    {
      ms_instance = new AuditTrailLogger();
    }
  }
}