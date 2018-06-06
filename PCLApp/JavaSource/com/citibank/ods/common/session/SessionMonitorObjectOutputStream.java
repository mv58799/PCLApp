/*
 * Created on May 3, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.common.session;

/**
 * @author marcelo.s.oliveira
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SessionMonitorObjectOutputStream extends ObjectOutputStream
{
  private long m_previousValidSize = 0;

  private Object m_previousValidObject;

  private ByteArrayOutputStream m_byteArrayOutputStream = null;

  public SessionMonitorObjectOutputStream( ByteArrayOutputStream baos_ )
                                                                        throws IOException
  {
    super( baos_ );
    m_byteArrayOutputStream = baos_;
    this.enableReplaceObject( true );
  }

  public Object replaceObject( Object obj_ ) throws IOException
  {
    Object replacedObject = null;
    if ( obj_ != null )
    {
      if ( obj_ instanceof Serializable )
      {
        this.flush();
        if ( m_previousValidObject != null )
        {

        }
        m_previousValidObject = obj_;
        m_previousValidSize = m_byteArrayOutputStream.size();

        replacedObject = obj_;
      }
      else
      {
        replacedObject = null;

      }
    }
    return replacedObject;
  }

  public void writeBoolean( boolean val ) throws IOException
  {

    super.writeBoolean( val );
    this.flush();
  }

  public void writeByte( byte val ) throws IOException
  {

    super.writeByte( val );
    this.flush();
  }

  public void writeChar( char val ) throws IOException
  {

    super.writeChar( val );
    this.flush();
  }

  public void writeDouble( double val ) throws IOException
  {

    super.writeDouble( val );
    this.flush();
  }

  public void writeFloat( float val ) throws IOException
  {

    super.writeFloat( val );
    this.flush();
  }

  public void writeInt( int val ) throws IOException
  {

    super.writeInt( val );
    this.flush();
  }

  public void writeLong( long val ) throws IOException
  {

    super.writeLong( val );
    this.flush();
  }

  public void writeShort( int val ) throws IOException
  {

    super.writeShort( val );
    this.flush();
  }
}

