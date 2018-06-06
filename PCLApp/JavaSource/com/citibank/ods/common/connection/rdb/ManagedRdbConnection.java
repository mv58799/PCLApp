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
 * Classe que encapsula uma conexão com o banco de dados. O principal objetivo
 * desta classe é garantir que a conexão será fechada mesmo que isso ocorra
 * devido à invocação do método finalize() em função da destruição desta
 * instância pelo Garbage Collector.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class ManagedRdbConnection extends BaseConnection
{
  /**
   * instância da conexão gerenciada.
   */
  private Connection m_connection;

  /**
   * @param connection_ instância da conexão que será gerenciada.
   * @throws UnexpectedException quando a conexão informada for null.
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
   * Garante que a conexão será fechada quando o Garbage Collector destruir a
   * instância deste objeto.
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
   * encapsula o método prepareCall da conexão encapsulada.
   * 
   * @param sqlStatement_ - Statement SQL que será utilizado para a criação do
   *          CallableStatement.
   * @return Instância do CallableStatement criado pela conexão m_connection.
   * @throws SQLException lançada pela invocação Connection.prepareCall(
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
   * encapsula o método prepareStatement da conexão encapsulada.
   * 
   * @param sqlStatement_ - Statement SQL que será utilizado para a criação do
   *          PreparedStatement.
   * @return Instância do PreparedStatement criado pela conexão m_connection.
   * @throws SQLException lançada pela invocação Connection.prepareStatement(
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
   * Executa o commit pela conexão.
   * 
   * @throws SQLException lançada pela invocação Connection.commit().
   */
  public void commit() throws SQLException
  {
    m_connection.commit();
  }

  /**
   * 
   * Executa o rollback pela conexão.
   * 
   * @throws SQLException lançada pela invocação Connection.rollback().
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
   * Fecha a conexão.
   * 
   * @throws SQLException lançada pela invocação Connection.close();
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
   * Indica se a conexão está fechada.
   * @return true, se a conexão estiver fechada. false, caso contrário. 
   * @throws SQLException lançada pela invocação Connection.isClosed();
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