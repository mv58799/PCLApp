/*
 * Created on 11/07/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.util;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;

import com.citibank.ods.common.logger.ApplicationLogger;
import com.citibank.ods.common.logger.LoggerFactory;

/**
 * [Class description]
 * 
 * @author  fpacheco
 * @version 1.0
 */
public class CitiStatement implements PreparedStatement {
	
	/* (non-Javadoc)
	 * Objeto PreparedStatement
	 * @see java.sql.PreparedStatement
	 * */
	PreparedStatement preparedStatement;
	
	/*
	 * HashMap de valores dos parametros da query
	 * */
	HashMap parametersMap;
	/*
	 * Construtor para instanciar o PreparedStatement e um novo HashMap de parametros 
	 * */
	public CitiStatement(PreparedStatement preparedStatement ){
		this.preparedStatement = preparedStatement;
		this.parametersMap = new HashMap();
	}
	
	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#executeQuery()
	 */
	public ResultSet executeQuery() throws SQLException {
		return preparedStatement.executeQuery();
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#executeUpdate()
	 */
	public int executeUpdate() throws SQLException {
		return preparedStatement.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setNull(int, int)
	 */
	public void setNull(int arg0, int arg1) throws SQLException {
		parametersMap.put(new Integer(arg0),new Integer(arg1));
		preparedStatement.setNull(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setBoolean(int, boolean)
	 */
	public void setBoolean(int arg0, boolean arg1) throws SQLException {
		parametersMap.put(new Integer(arg0),new Boolean(arg1));
		preparedStatement.setBoolean(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setByte(int, byte)
	 */
	public void setByte(int arg0, byte arg1) throws SQLException {
		parametersMap.put(new Integer(arg0),new Byte(arg1));
		preparedStatement.setByte(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setShort(int, short)
	 */
	public void setShort(int arg0, short arg1) throws SQLException {
		parametersMap.put(new Integer(arg0),new Short(arg1));
		preparedStatement.setShort(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setInt(int, int)
	 */
	public void setInt(int arg0, int arg1) throws SQLException {
		parametersMap.put(new Integer(arg0),new Integer(arg1));
		preparedStatement.setInt(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setLong(int, long)
	 */
	public void setLong(int arg0, long arg1) throws SQLException {
		parametersMap.put(new Integer(arg0),new Long(arg1));
		preparedStatement.setLong(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setFloat(int, float)
	 */
	public void setFloat(int arg0, float arg1) throws SQLException {
		parametersMap.put(new Integer(arg0),new Float(arg1));
		preparedStatement.setFloat(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setDouble(int, double)
	 */
	public void setDouble(int arg0, double arg1) throws SQLException {
		parametersMap.put(new Integer(arg0),new Double(arg1));
		preparedStatement.setDouble(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setBigDecimal(int, java.math.BigDecimal)
	 */
	public void setBigDecimal(int arg0, BigDecimal arg1) throws SQLException {
		parametersMap.put(new Integer(arg0),arg1);
		preparedStatement.setBigDecimal(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setString(int, java.lang.String)
	 */
	public void setString(int arg0, String arg1) throws SQLException {
		parametersMap.put(new Integer(arg0),arg1);
		preparedStatement.setString(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setBytes(int, byte[])
	 */
	public void setBytes(int arg0, byte[] arg1) throws SQLException {

		parametersMap.put(new Integer(arg0),arg1);
		preparedStatement.setBytes(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setDate(int, java.sql.Date)
	 */
	public void setDate(int arg0, Date arg1) throws SQLException {

		parametersMap.put(new Integer(arg0),arg1);
		preparedStatement.setDate(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setTime(int, java.sql.Time)
	 */
	public void setTime(int arg0, Time arg1) throws SQLException {

		parametersMap.put(new Integer(arg0),arg1);
		preparedStatement.setTime(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setTimestamp(int, java.sql.Timestamp)
	 */
	public void setTimestamp(int arg0, Timestamp arg1) throws SQLException {

		parametersMap.put(new Integer(arg0),arg1);
		preparedStatement.setTimestamp(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setAsciiStream(int, java.io.InputStream, int)
	 */
	public void setAsciiStream(int arg0, InputStream arg1, int arg2)
		throws SQLException {

		parametersMap.put(new Integer(arg0),arg1);
		preparedStatement.setAsciiStream(arg0,arg1,arg2);

	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setUnicodeStream(int, java.io.InputStream, int)
	 */
	@Deprecated
	public void setUnicodeStream(int arg0, InputStream arg1, int arg2)
		throws SQLException {

		parametersMap.put(new Integer(arg0),arg1);
		preparedStatement.setUnicodeStream(arg0,arg1,arg2);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setBinaryStream(int, java.io.InputStream, int)
	 */
	public void setBinaryStream(int arg0, InputStream arg1, int arg2)
		throws SQLException {

		parametersMap.put(new Integer(arg0),arg1);
		preparedStatement.setBinaryStream(arg0,arg1,arg2);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#clearParameters()
	 */
	public void clearParameters() throws SQLException {

		preparedStatement.clearParameters();
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setObject(int, java.lang.Object, int, int)
	 */
	public void setObject(int arg0, Object arg1, int arg2, int arg3)
		throws SQLException {

		preparedStatement.setObject(arg0,arg1,arg2,arg3);

	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setObject(int, java.lang.Object, int)
	 */
	public void setObject(int arg0, Object arg1, int arg2)
		throws SQLException {
		preparedStatement.setObject(arg0,arg1,arg2);

	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setObject(int, java.lang.Object)
	 */
	public void setObject(int arg0, Object arg1) throws SQLException {

		parametersMap.put(new Integer(arg0),arg1);
		preparedStatement.setObject(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#execute()
	 */
	public boolean execute() throws SQLException {

		return preparedStatement.execute();		
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#addBatch()
	 */
	public void addBatch() throws SQLException {

		preparedStatement.addBatch();
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setCharacterStream(int, java.io.Reader, int)
	 */
	public void setCharacterStream(int arg0, Reader arg1, int arg2)
		throws SQLException {
		preparedStatement.setCharacterStream(arg0,arg1,arg2);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setRef(int, java.sql.Ref)
	 */
	public void setRef(int arg0, Ref arg1) throws SQLException {

		parametersMap.put(new Integer(arg0),arg1);
		preparedStatement.setRef(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setBlob(int, java.sql.Blob)
	 */
	public void setBlob(int arg0, Blob arg1) throws SQLException {

		parametersMap.put(new Integer(arg0),arg1);
		preparedStatement.setBlob(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setClob(int, java.sql.Clob)
	 */
	public void setClob(int arg0, Clob arg1) throws SQLException {

		parametersMap.put(new Integer(arg0),arg1);
		preparedStatement.setClob(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setArray(int, java.sql.Array)
	 */
	public void setArray(int arg0, Array arg1) throws SQLException {

		parametersMap.put(new Integer(arg0),arg1);
		preparedStatement.setArray(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#getMetaData()
	 */
	public ResultSetMetaData getMetaData() throws SQLException {

		return preparedStatement.getMetaData();		
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setDate(int, java.sql.Date, java.util.Calendar)
	 */
	public void setDate(int arg0, Date arg1, Calendar arg2)
		throws SQLException {

		preparedStatement.setDate(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setTime(int, java.sql.Time, java.util.Calendar)
	 */
	public void setTime(int arg0, Time arg1, Calendar arg2)
		throws SQLException {

		preparedStatement.setTime(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setTimestamp(int, java.sql.Timestamp, java.util.Calendar)
	 */
	public void setTimestamp(int arg0, Timestamp arg1, Calendar arg2)
		throws SQLException {

		preparedStatement.setTimestamp(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setNull(int, int, java.lang.String)
	 */
	public void setNull(int arg0, int arg1, String arg2) throws SQLException {

		preparedStatement.setNull(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setURL(int, java.net.URL)
	 */
	public void setURL(int arg0, URL arg1) throws SQLException {

		parametersMap.put(new Integer(arg0),arg1);
		preparedStatement.setURL(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#getParameterMetaData()
	 */
	public ParameterMetaData getParameterMetaData() throws SQLException {

		return preparedStatement.getParameterMetaData();		
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#executeQuery(java.lang.String)
	 */
	public ResultSet executeQuery(String arg0) throws SQLException {

		return preparedStatement.executeQuery(arg0);		
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#executeUpdate(java.lang.String)
	 */
	public int executeUpdate(String arg0) throws SQLException {

		return preparedStatement.executeUpdate(arg0);		
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#close()
	 */
	public void close() throws SQLException {

		preparedStatement.close();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getMaxFieldSize()
	 */
	public int getMaxFieldSize() throws SQLException {

		return preparedStatement.getMaxFieldSize();		
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#setMaxFieldSize(int)
	 */
	public void setMaxFieldSize(int arg0) throws SQLException {

		preparedStatement.setMaxFieldSize(arg0);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getMaxRows()
	 */
	public int getMaxRows() throws SQLException {

		return preparedStatement.getMaxRows();		
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#setMaxRows(int)
	 */
	public void setMaxRows(int arg0) throws SQLException {

		preparedStatement.setMaxRows(arg0);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#setEscapeProcessing(boolean)
	 */
	public void setEscapeProcessing(boolean arg0) throws SQLException {

		preparedStatement.setEscapeProcessing(arg0);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getQueryTimeout()
	 */
	public int getQueryTimeout() throws SQLException {

		return preparedStatement.getQueryTimeout();		
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#setQueryTimeout(int)
	 */
	public void setQueryTimeout(int arg0) throws SQLException {

		preparedStatement.setQueryTimeout(arg0);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#cancel()
	 */
	public void cancel() throws SQLException {

		preparedStatement.cancel();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getWarnings()
	 */
	public SQLWarning getWarnings() throws SQLException {

		return preparedStatement.getWarnings();		
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#clearWarnings()
	 */
	public void clearWarnings() throws SQLException {

		preparedStatement.clearWarnings();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#setCursorName(java.lang.String)
	 */
	public void setCursorName(String arg0) throws SQLException {

		preparedStatement.setCursorName(arg0);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#execute(java.lang.String)
	 */
	public boolean execute(String arg0) throws SQLException {

		return preparedStatement.execute(arg0);		
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getResultSet()
	 */
	public ResultSet getResultSet() throws SQLException {

		return preparedStatement.getResultSet();		
		
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getUpdateCount()
	 */
	public int getUpdateCount() throws SQLException {

		return preparedStatement.getUpdateCount();		
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getMoreResults()
	 */
	public boolean getMoreResults() throws SQLException {

		return preparedStatement.getMoreResults();		
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#setFetchDirection(int)
	 */
	public void setFetchDirection(int arg0) throws SQLException {

		preparedStatement.setFetchDirection(arg0);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getFetchDirection()
	 */
	public int getFetchDirection() throws SQLException {

		return preparedStatement.getFetchDirection();		
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#setFetchSize(int)
	 */
	public void setFetchSize(int arg0) throws SQLException {

		preparedStatement.setFetchSize(arg0);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getFetchSize()
	 */
	public int getFetchSize() throws SQLException {

		return preparedStatement.getFetchSize();		
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getResultSetConcurrency()
	 */
	public int getResultSetConcurrency() throws SQLException {

		return preparedStatement.getResultSetConcurrency();		
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getResultSetType()
	 */
	public int getResultSetType() throws SQLException {

		return preparedStatement.getResultSetType();		
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#addBatch(java.lang.String)
	 */
	public void addBatch(String arg0) throws SQLException {

		preparedStatement.addBatch(arg0);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#clearBatch()
	 */
	public void clearBatch() throws SQLException {

		preparedStatement.clearBatch();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#executeBatch()
	 */
	public int[] executeBatch() throws SQLException {

		return preparedStatement.executeBatch();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getConnection()
	 */
	public Connection getConnection() throws SQLException {

		return preparedStatement.getConnection();		
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getMoreResults(int)
	 */
	public boolean getMoreResults(int arg0) throws SQLException {

		return preparedStatement.getMoreResults(arg0);		
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getGeneratedKeys()
	 */
	public ResultSet getGeneratedKeys() throws SQLException {

		return preparedStatement.getGeneratedKeys();		
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#executeUpdate(java.lang.String, int)
	 */
	public int executeUpdate(String arg0, int arg1) throws SQLException {

		return preparedStatement.executeUpdate(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#executeUpdate(java.lang.String, int[])
	 */
	public int executeUpdate(String arg0, int[] arg1) throws SQLException {

		return preparedStatement.executeUpdate(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#executeUpdate(java.lang.String, java.lang.String[])
	 */
	public int executeUpdate(String arg0, String[] arg1) throws SQLException {

		return preparedStatement.executeUpdate(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#execute(java.lang.String, int)
	 */
	public boolean execute(String arg0, int arg1) throws SQLException {

		return preparedStatement.execute(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#execute(java.lang.String, int[])
	 */
	public boolean execute(String arg0, int[] arg1) throws SQLException {

		return preparedStatement.execute(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#execute(java.lang.String, java.lang.String[])
	 */
	public boolean execute(String arg0, String[] arg1) throws SQLException {

		return preparedStatement.execute(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getResultSetHoldability()
	 */
	public int getResultSetHoldability() throws SQLException {

		return preparedStatement.getResultSetHoldability();
	}
	/*
	 * Monta a query com os parametros e exibe no log do Application
	 * */
	public void replaceParametersInQuery(String query){
		int cont = 1;
		String retorno = new String();
		int inicio = 0;
		String tmp =  new String();
		tmp = query.substring(inicio);
		while (tmp.indexOf("?") != -1){
			String parm = new String();
			if(parametersMap.get(new Integer(cont))!=null)
				parm = parametersMap.get(new Integer(cont)) instanceof String ? "'" + parametersMap.get(new Integer(cont)).toString() + "'" : parametersMap.get(new Integer(cont)).toString();
			else
				parm = "null";
				 
			retorno = retorno + tmp.substring(0,tmp.indexOf("?")) + parm;
			
			inicio = tmp.indexOf("?") + 1;
			if(inicio < tmp.length())
				tmp = tmp.substring(inicio);
			else
				tmp = ""; 
			cont++;
		}
		if(!tmp.equals(""))	
			retorno = retorno + tmp;

		LoggerFactory.initialize();
			
		//Recupera instância de Aplication
		ApplicationLogger applicationLogger = LoggerFactory.getApplicationLoggerInstance();
		applicationLogger.info(retorno);
			 
	}

	public void closeOnCompletion() throws SQLException {
		preparedStatement.closeOnCompletion();
		
	}

	public boolean isCloseOnCompletion() throws SQLException {
		return preparedStatement.isCloseOnCompletion();
	}

	public boolean isClosed() throws SQLException {
		return preparedStatement.isClosed();
	}

	public boolean isPoolable() throws SQLException {
		return preparedStatement.isPoolable();
	}

	public void setPoolable(boolean arg0) throws SQLException {
		 preparedStatement.setPoolable( arg0);		
	}

	public boolean isWrapperFor(Class<?> arg0) throws SQLException {		
		return preparedStatement.isWrapperFor(arg0);
	}

	public <T> T unwrap(Class<T> arg0) throws SQLException {
		return preparedStatement.unwrap(arg0);
	}

	public void setAsciiStream(int arg0, InputStream arg1) throws SQLException {
		preparedStatement.setAsciiStream(arg0, arg1);
		
	}

	public void setAsciiStream(int arg0, InputStream arg1, long arg2)
			throws SQLException {
		preparedStatement.setAsciiStream(arg0,arg1,arg2 );
		
	}

	public void setBinaryStream(int arg0, InputStream arg1) throws SQLException {
		preparedStatement.setBinaryStream(arg0, arg1);
	}

	public void setBinaryStream(int arg0, InputStream arg1, long arg2)
			throws SQLException {
		preparedStatement.setBinaryStream(arg0, arg1, arg2);
		
	}

	public void setBlob(int arg0, InputStream arg1) throws SQLException {
		preparedStatement.setBlob(arg0, arg1);
	}

	public void setBlob(int arg0, InputStream arg1, long arg2)
			throws SQLException {
		preparedStatement.setBlob(arg0, arg1,arg2);
		
	}

	public void setCharacterStream(int arg0, Reader arg1) throws SQLException {
		preparedStatement.setCharacterStream(arg0, arg1);
	}

	public void setCharacterStream(int arg0, Reader arg1, long arg2)
			throws SQLException {
		preparedStatement.setCharacterStream(arg0, arg1, arg2);
		
	}

	public void setClob(int arg0, Reader arg1) throws SQLException {
		preparedStatement.setClob(arg0, arg1);
		
	}

	public void setClob(int arg0, Reader arg1, long arg2) throws SQLException {
		preparedStatement.setClob(arg0, arg1, arg2);
		
	}

	public void setNCharacterStream(int arg0, Reader arg1) throws SQLException {
		preparedStatement.setNCharacterStream(arg0, arg1);
		
	}

	public void setNCharacterStream(int arg0, Reader arg1, long arg2)
			throws SQLException {
		preparedStatement.setNCharacterStream(arg0, arg1,  arg2);
	}

	public void setNClob(int arg0, NClob arg1) throws SQLException {
		preparedStatement.setNClob(arg0, arg1);
		
	}

	public void setNClob(int arg0, Reader arg1) throws SQLException {
		preparedStatement.setNClob(arg0, arg1);
		
	}

	public void setNClob(int arg0, Reader arg1, long arg2) throws SQLException {
		preparedStatement.setNClob(arg0, arg1,  arg2);
		
	}

	public void setNString(int arg0, String arg1) throws SQLException {
		preparedStatement.setNString(arg0, arg1);
		
	}

	public void setRowId(int arg0, RowId arg1) throws SQLException {
		preparedStatement.setRowId(arg0, arg1);
		
	}

	public void setSQLXML(int arg0, SQLXML arg1) throws SQLException {
		preparedStatement.setSQLXML(arg0, arg1);
		
	}

}
