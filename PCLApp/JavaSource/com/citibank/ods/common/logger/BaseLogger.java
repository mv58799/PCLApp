/*
 * Created on Apr 20, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.common.logger;

import java.util.PropertyResourceBundle;

import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.DefStaticVars;
import com.citibank.sad.logmanager.ILogger;
import com.citibank.sad.logmanager.LogManager;

/**
 * @author marcelo.s.oliveira
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseLogger
{
  protected ILogger m_logger = null;

  protected BaseLogger( String loggerName_ )
  {
    try
    {
      String logProperties = DefStaticVars.getInstance().logProperties;

      PropertyResourceBundle resourceBundle = new PropertyResourceBundle(
                                                                          getClass().getClassLoader().getResourceAsStream(
                                                                                                          logProperties ) );

      m_logger = LogManager.getLogger( loggerName_, ILogger.LOGGER_TO_FILE,
                                       resourceBundle );
    }
    catch ( Exception e )
    {
      throw new UnexpectedException( "Erro ao obter instância do logger.", e );
    }

  }
}