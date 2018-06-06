/*
 * Created on May 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.common.session;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;

import javax.servlet.http.HttpSession;

import com.citibank.ods.common.BaseObject;

/**
 * @author marcelo.s.oliveira
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SessionMonitor
extends BaseObject
{
public SessionMonitor( HttpSession session_ )
  throws IOException
{
  super();
  m_session = session_;
  m_byteArrayOutputStream = new ByteArrayOutputStream();
  m_objectOutputStream = new SessionMonitorObjectOutputStream( m_byteArrayOutputStream );

  m_objectOutputStream.writeObject( m_session );
  m_objectOutputStream.flush();
  m_objectOutputStream.close();
}

private static String formatNumber( long number_ )
{
  return NumberFormat.getInstance().format( number_ ) + " bytes";
}

private HttpSession m_session = null;
private ByteArrayOutputStream m_byteArrayOutputStream = null;
private ObjectOutputStream m_objectOutputStream = null;

}
