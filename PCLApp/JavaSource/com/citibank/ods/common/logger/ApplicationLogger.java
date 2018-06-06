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
 * Preferences - Java - Code Style - Code Templates
 */
public class ApplicationLogger extends BaseLogger
{
  private static ApplicationLogger ms_instance = null;

  private ApplicationLogger()
  {
    super( "PrivateLayerApplication" );
  }

  public void info( String message_ )
  {
    m_logger.logInfo( message_ );
  }

  public void warn( String message_ )
  {
    m_logger.logWarning( message_ );
  }

  public void debug( String message_ )
  {
    m_logger.logDebug( message_ );
  }

  public void error( String message_, Throwable t_ )
  {
    m_logger.logError( message_ + "\n StackTrace: " );
    m_logger.logException( t_ );
  }

  public void error( String message_ )
  {
    m_logger.logError( message_ );
  }

  public static ApplicationLogger getInstance()
  {
    if ( ms_instance == null )
    {
      throw new RuntimeException( "Application Logger not initialized" );
    }
    return ms_instance;
  }

  public static void init()
  {
    if ( ms_instance == null )
    {
      createSingletonInstance();
    }
  }

  private static synchronized void createSingletonInstance()
  {
    if ( ms_instance == null )
    {
      ms_instance = new ApplicationLogger();
    }
  }
}