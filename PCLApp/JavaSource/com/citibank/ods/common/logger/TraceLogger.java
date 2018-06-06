/*
 * Created on Apr 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.common.logger;

/**
 * @author marcelo.s.oliveira
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TraceLogger extends BaseLogger
{
  private static TraceLogger ms_instance = null;

  /**
   * Construtor privado para o singleton
   *  
   */
  private TraceLogger()
  {
    /**
     * Rever como obter o nome do logger quando for definido configuration
     */
    super( "PL-TraceLogger" );
  }

  /**
   * 
   * @param message_
   */
  public void debug( String message_ )
  {
    m_logger.logDebug( message_ );
  }

  public static TraceLogger getInstance()
  {
    if ( ms_instance == null )
    {
      throw new RuntimeException( "Trace Logger not initialized" );
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
      ms_instance = new TraceLogger();
    }
  }
}