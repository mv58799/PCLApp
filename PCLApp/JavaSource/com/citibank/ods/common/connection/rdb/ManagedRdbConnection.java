package com.citibank.ods.common.connection.rdb;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;

import com.citibank.ods.common.connection.BaseConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.logger.ApplicationLogger;

/**
 * 
 * Classe que encapsula uma conex�o com o banco de dados. O principal objetivo
 * desta classe � garantir que a conex�o ser� fechada mesmo que isso ocorra
 * devido � invoca��o do m�todo finalize() em fun��o da destrui��o desta
 * inst�ncia pelo Garbage Collector.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class ManagedRdbConnection extends BaseConnection
{
  /**
   * inst�ncia da conex�o gerenciada.
   */
  private Connection m_connection;

  /**
   * @param connection_ inst�ncia da conex�o que ser� gerenciada.
   * @throws UnexpectedException quando a conex�o informada for null.
   */
  public ManagedRdbConnection( Connection connection_ )
                                                       throws UnexpectedException
  {
    if ( connection_ == null )
    {
      throw new UnexpectedException( "Connection cannot be null." );
    }

    m_connection = connection_;
  }

  /*
   * Garante que a conex�o ser� fechada quando o Garbage Collector destruir a
   * inst�ncia deste objeto.
   * @see java.lang.Object#finalize()
   */
  protected void finalize()
  {
    if ( m_connection != null )
    {
      try
      {
        m_connection.close();
      }
      catch ( SQLException e )
      {
        //TODO: Logar erro.
      }
    }
  }

  /**
   * 
   * encapsula o m�todo prepareCall da conex�o encapsulada.
   * 
   * @param sqlStatement_ - Statement SQL que ser� utilizado para a cria��o do
   *          CallableStatement.
   * @return Inst�ncia do CallableStatement criado pela conex�o m_connection.
   * @throws SQLException lan�ada pela invoca��o Connection.prepareCall(
   *           sqlStatement_ ).
   */
  public CallableStatement prepareCall( String sqlStatement_ )
                                                              throws SQLException
  {
    CallableStatement returnCallableStatement = null;
    returnCallableStatement = m_connection.prepareCall( sqlStatement_ );
    return returnCallableStatement;
  }

  /**
   * 
   * encapsula o m�todo prepareStatement da conex�o encapsulada.
   * 
   * @param sqlStatement_ - Statement SQL que ser� utilizado para a cria��o do
   *          PreparedStatement.
   * @return Inst�ncia do PreparedStatement criado pela conex�o m_connection.
   * @throws SQLException lan�ada pela invoca��o Connection.prepareStatement(
   *           sqlStatement_ ).
   */
  public PreparedStatement prepareStatement( String sqlStatement_ )
                                                                   throws SQLException
  {
    PreparedStatement returnPreparedStatement = null;
    returnPreparedStatement = m_connection.prepareStatement( sqlStatement_ );
    return returnPreparedStatement;
  }

  /**
   * 
   * Executa o commit pela conex�o.
   * 
   * @throws SQLException lan�ada pela invoca��o Connection.commit().
   */
  public void commit() throws SQLException
  {
    m_connection.commit();
  }

  /**
   * 
   * Executa o rollback pela conex�o.
   * 
   * @throws SQLException lan�ada pela invoca��o Connection.rollback().
   */
  public void rollback() throws SQLException
  {
    if ( m_connection != null )
    {
      m_connection.rollback();
    }
  }

  /**
   * 
   * Fecha a conex�o.
   * 
   * @throws SQLException lan�ada pela invoca��o Connection.close();
   */
  public final void close() throws SQLException
  {
    if ( m_connection != null)
    {
      m_connection.close();
      m_connection = null;
    }
  }

  /**
   * Indica se a conex�o est� fechada.
   * @return true, se a conex�o estiver fechada. false, caso contr�rio. 
   * @throws SQLException lan�ada pela invoca��o Connection.isClosed();
   */
  public boolean isClosed() throws SQLException
  {
    return m_connection.isClosed();
  }

  public void setAutoCommit(boolean autoCommit) throws SQLException{
	  m_connection.setAutoCommit(autoCommit);
	  
  }
  public boolean isAutoCommit() {
	try {
		if (m_connection== null)
			return true;
		
		return m_connection.getAutoCommit();
	} catch (SQLException e) {
		ApplicationLogger.getInstance().error("error getting autocommit", e);
		return false;
	}
  }
}