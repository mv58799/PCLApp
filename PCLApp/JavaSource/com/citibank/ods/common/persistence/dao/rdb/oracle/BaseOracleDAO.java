package com.citibank.ods.common.persistence.dao.rdb.oracle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.persistence.dao.BaseDAO;

/**
 * 
 * Classe base para as classes do tipo OracleDAO. O objetivo das classes do tipo
 * OracleDAO é implementar os acessos aos dados persistidos em banco de dados
 * ORACLE. Esta classe base implementa funcionalidades para auxiliar o
 * gerenciamento de conexões e preparedStatements.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class BaseOracleDAO extends BaseDAO
{

  /**
   * Constantes utilizadas para indicar erro na execução de preparedStatements.
   */
  protected static final String C_ERROR_EXECUTING_STATEMENT = "Error executing preparedStatement.";

  protected static final String C_ERROR_TOO_MANY_ROWS_RETURNED = "Too many rows returned.";

  protected static final String C_ERROR_INSTANTIATE_FROM_RESULT_SET = "Error creating entity from resultset.";

  /**
   * Constantes utilizadas para indicar os schemas utilizados.
   */
  protected static final String C_PL_SCHEMA = "PL.";

  protected static final String C_BG_SCHEMA = "BG.";

  protected static final String C_O3_SCHEMA = "O3.";

  protected static final String C_RH_SCHEMA = "RH.";

	protected void closeResultSet(ResultSet resultSet_) throws UnexpectedException {
		try {
			if (resultSet_ != null) {
				resultSet_.close();
			}

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), "Error closing resultSet", e);
		}
	}

  /**
   * Fecha um preparedStatement.
   * @param preparedStatement_ - preparedStatement que será fechado.
   * @throws UnexpectedException se ocorrer uma SQLException ao executar
   *           Statement.close().
   */
  protected void closeStatement( Statement preparedStatement_ )
                                                       throws UnexpectedException
  {
    try
    {
      if ( preparedStatement_ != null )
      {
        preparedStatement_.close();
      }

    }
    catch ( SQLException e )
    {

      throw new UnexpectedException( e.getErrorCode(), "Error closing preparedStatement", e );
    }

  }

  /**
   * Fecha uma conexão.
   * @param connection_ - Conexão que será fechada.
   * @throws UnexpectedException se ocorrer uma SQLException ao executar
   *           ManagedRdbConnection.close().
   */
  protected void closeConnection( ManagedRdbConnection connection_ )
                                                                    throws UnexpectedException
  {
    try
    {
      if ( connection_ != null )
      {
        connection_.close();
      }

    }
    catch ( SQLException e )
    {
      //to-do: log error
      throw new UnexpectedException( e.getErrorCode(), "error closing connection", e );
    }
  }
  
  protected String readSqlFile(String sqlName)throws IOException {
	
	String resourcePath = "/com/citibank/ods/resources/";
	String path = this.getClass().getClassLoader().getResource(resourcePath+sqlName).toString().replaceAll("file:/","");
		
	BufferedReader reader = new BufferedReader((new FileReader(path)));	
	StringBuffer sb = new StringBuffer();

	String line;		
	while ((line = reader.readLine()) != null) {
	  sb.append(line+" ");
	}
		
	reader.close();
	return sb.toString();				
  }

}

