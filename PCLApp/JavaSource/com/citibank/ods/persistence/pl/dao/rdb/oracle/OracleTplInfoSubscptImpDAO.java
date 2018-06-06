package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.logger.ApplicationLogger;
import com.citibank.ods.common.util.TableTypeEnum;
import com.citibank.ods.entity.pl.valueobject.TplInfoSubscptImpEntityVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class OracleTplInfoSubscptImpDAO extends BaseOracleTplInfoSubscptImpDAO {

	public Long getSequence(){
		String seqName = "PL.SQ_TPL_INFO_SUBSCPT_IMP";
		  ManagedRdbConnection connection = null;
		  PreparedStatement ps = null;
		    StringBuffer query = new StringBuffer();

		    try
		    {
		    	 connection = OracleODSDAOFactory.getConnection();
		    	 connection.setAutoCommit(false);
		    	 query.append("SELECT ").append(seqName).append(".NEXTVAL FROM DUAL");
		    	 
		    	 log(query.toString());
		    	 CallableStatement prepareCall = connection.prepareCall(query.toString());
		    	 ResultSet rs = prepareCall.executeQuery();
		    	 if (rs.next())
		    		 return rs.getLong(1);
		    	 else 
		    		 return null;
		    }
		    catch ( SQLException e )
		    {
		      throw new UnexpectedException( e.getErrorCode(),
		                                     C_ERROR_EXECUTING_STATEMENT, e );
		    }
		    finally
		    {
		      closeStatement( ps );
		    	  closeConnection( connection );
		    }
	}
	
	
	public TplInfoSubscptImpEntityVO searchImpSubscript(Long fileImportCode, TableTypeEnum tableType ){
		
		  ResultSet rs = null;
		    PreparedStatement ps = null;
		    ManagedRdbConnection connection = null;
		    StringBuffer query = new StringBuffer();
		    try
		    {
		      connection = OracleODSDAOFactory.getConnection();
		      query.append( "SELECT FILE_IMPORT_CODE, ")
		      		.append("FILE_IMPORT_DATE, ")
		      		.append("IMPORT_FILE_NAME_TEXT , ")
		      		.append("FILE_IMPORT_REC_QTY , ");
		      	if (!tableType.equals(TableTypeEnum.MOVEMENT)){
			      		query.append(" LAST_APPRV_DATE  , ");
			      		query.append(" LAST_APPRV_USER_ID , ");
		      	}
		      	if (!tableType.equals(TableTypeEnum.EFFECTIVE)){
		      		query.append(" OPERN_TYPE_CODE, ");
		      	}
		      		query.append("LAST_UPD_DATE, ")
		      		.append("LAST_UPD_USER_ID ")
		      		.append("FROM PL.TPL_INFO_SUBSCPT_IMP").append(tableType.getSufixo())
		      		.append(" WHERE FILE_IMPORT_CODE = ?");
		      
		      		
		      		log(query.toString(),fileImportCode );
		      ps = connection.prepareStatement( query.toString());
		      ps.setLong(1, fileImportCode);
		      ps = new CitiStatement(ps);
		      
		      rs = ps.executeQuery();
			  if (rs.next()){
				  TplInfoSubscptImpEntityVO vo = new TplInfoSubscptImpEntityVO();
				  vo.setFileImportCode(rs.getLong("FILE_IMPORT_CODE"));
				  vo.setFileImportDate(rs.getTimestamp("FILE_IMPORT_DATE"));
				  vo.setFileImportRecQty(com.citibank.ods.common.util.Util.parseBigInteger(rs.getLong("FILE_IMPORT_REC_QTY")));
				  vo.setImportFileNameText(rs.getString("IMPORT_FILE_NAME_TEXT"));
				 if (!tableType.equals(TableTypeEnum.MOVEMENT)){
					 vo.setLastApprvDate(rs.getTimestamp("LAST_APPRV_DATE"));
					 vo.setLastApprvUserId(rs.getString("LAST_APPRV_USER_ID"));
				 }
			 	if (!tableType.equals(TableTypeEnum.EFFECTIVE)){
			 		vo.setOpernTypeCode(rs.getString("OPERN_TYPE_CODE"));
			 	}
			 	 vo.setLastUpdDate(rs.getTimestamp("LAST_UPD_DATE"));
				 vo.setLastUpdUserId(rs.getString("LAST_UPD_USER_ID"));
				  return vo;
			  }
			  return null;
		    }
		    catch ( SQLException e )
		    {
		      throw new UnexpectedException( e.getErrorCode(),
		                                     C_ERROR_EXECUTING_STATEMENT, e );
		    }
		    finally
		    {
		      closeStatement( ps );
		      closeConnection( connection );
		    }
		
	}
	
	public void insert( TplInfoSubscptImpEntityVO vo )
	  {
		insert(vo, true);
	  }

	public void insert( TplInfoSubscptImpEntityVO vo, boolean autoCommit )
	  {
	    ManagedRdbConnection connection = null;
	    PreparedStatement ps = null;
	    StringBuffer query = new StringBuffer();

	    
	    try
	    {
	      connection = getConnection();

	      connection.setAutoCommit(autoCommit);
	      
	      query.append( "INSERT INTO PL.TPL_INFO_SUBSCPT_IMP").append(vo.getTableType().getSufixo() )
	    		  .append( " (FILE_IMPORT_CODE, ")
	    		  .append( "FILE_IMPORT_DATE, ")
	      		.append("IMPORT_FILE_NAME_TEXT, ")
	      		.append("FILE_IMPORT_REC_QTY, ");
	      if (vo.getTableType() == TableTypeEnum.EFFECTIVE){
	      		query.append("LAST_APPRV_DATE, " );
	      		query.append("LAST_APPRV_USER_ID, ");
	      }else if (vo.getTableType() == TableTypeEnum.MOVEMENT){
	    	  	query.append("OPERN_TYPE_CODE, ");
	      }
	      query.append( "LAST_UPD_DATE, LAST_UPD_USER_ID )" );
	      query.append( " VALUES " );
	      if (vo.getTableType() == TableTypeEnum.EFFECTIVE){
	    	  query.append( " ( "+com.citibank.ods.common.util.Util.printNTimes("?", 8)+ ") " );
	      }else {
	    	  query.append( " ( "+com.citibank.ods.common.util.Util.printNTimes("?", 7)+ ") " );
	      }
	      ps = connection.prepareStatement( query.toString());
	      int param =0;
	      ps.setLong(++param, vo.getFileImportCode());
	      com.citibank.ods.common.util.Util.setTimestamp(++param,ps, vo.getFileImportDate());
	      ps.setString(++param, vo.getImportFileNameText());
	      ps.setInt(++param, com.citibank.ods.common.util.Util.parseInteger(vo.getFileImportRecQty()));
	      if (vo.getTableType() == TableTypeEnum.EFFECTIVE){
	    	  com.citibank.ods.common.util.Util.setTimestamp(++param,ps, vo.getLastApprvDate());
	    	  ps.setString(++param, vo.getLastApprvUserId());
	      }else {
	    	  ps.setString(++param, vo.getOpernTypeCode());
	      }
	      com.citibank.ods.common.util.Util.setTimestamp(++param,ps, vo.getLastUpdDate());
    	  ps.setString(++param, vo.getLastUpdUserId());
    	  log(query.toString());
	      ps.execute();
	      
	    }
	    catch ( SQLException e )
	    {
	      throw new UnexpectedException( e.getErrorCode(),
	                                     C_ERROR_EXECUTING_STATEMENT, e );
	    }
	    finally
	    {
	      closeStatement( ps );
	      if (connection.isAutoCommit())
	    	  closeConnection( connection );
	    }
	  }
	

	public void update( TplInfoSubscptImpEntityVO vo)
	  {
		update(vo, true);
	  }
	public void update( TplInfoSubscptImpEntityVO vo, boolean autoCommit )
	  {
	    ManagedRdbConnection connection = null;
	    PreparedStatement ps = null;
	    StringBuffer query = new StringBuffer();

	    try
	    {
	      connection = OracleODSDAOFactory.getConnection();
	      connection.setAutoCommit(autoCommit);
	      query.append( "UPDATE PL.TPL_INFO_SUBSCPT_IMP").append(vo.getTableType().getSufixo() )
	    		  .append( "FILE_IMPORT_DATE = ?, ")
	      		.append("IMPORT_FILE_NAME_TEXT = ?, ")
	      		.append("FILE_IMPORT_REC_QTY = ?, ");
	      if (vo.getTableType() == TableTypeEnum.EFFECTIVE){
	      		query.append("LAST_APPRV_DATE = ?, " );
	      		query.append("LAST_APPRV_USER_ID = ?, ");
	      }else if (vo.getTableType() == TableTypeEnum.MOVEMENT){
	    	  	query.append("OPERN_TYPE_CODE = ?, ");
	      }
	      query.append( "LAST_UPD_DATE = ?, LAST_UPD_USER_ID = ? WHERE FILE_IMPORT_CODE = ?" );
	      
	      ps = connection.prepareStatement( query.toString());
	      int param =0;
	      com.citibank.ods.common.util.Util.setTimestamp(++param,ps, vo.getFileImportDate());
	      ps.setString(++param, vo.getImportFileNameText());
	      ps.setInt(++param, com.citibank.ods.common.util.Util.parseInteger(vo.getFileImportRecQty()));
	      if (vo.getTableType() == TableTypeEnum.EFFECTIVE){
	    	  com.citibank.ods.common.util.Util.setTimestamp(++param,ps, vo.getLastApprvDate());
	    	  ps.setString(++param, vo.getLastApprvUserId());
	      }else {
	    	  ps.setString(++param, vo.getOpernTypeCode());
	      }
	      com.citibank.ods.common.util.Util.setTimestamp(++param,ps, vo.getLastUpdDate());
    	  ps.setString(++param, vo.getLastUpdUserId());
    	  ps.setLong(++param, vo.getFileImportCode());
    	  
    	  log(query.toString());
	      ps.execute();
	      
	    }
	    catch ( SQLException e )
	    {
	      throw new UnexpectedException( e.getErrorCode(),
	                                     C_ERROR_EXECUTING_STATEMENT, e );
	    }
	    finally
	    {
	      closeStatement( ps );
	      closeConnection( connection );
	    }
	  }


	public void delete(Long impCode, TableTypeEnum tableType) {
		  ManagedRdbConnection connection = null;
		    PreparedStatement ps = null;
		    StringBuffer query = new StringBuffer();

		    try
		    {
		      connection = OracleODSDAOFactory.getConnection();
		      query.append( "DELETE FROM PL.TPL_INFO_SUBSCPT_IMP").append(tableType.getSufixo() )
		      .append(" WHERE FILE_IMPORT_CODE = ?" );
		      log(query.toString(), impCode);
		      ps = connection.prepareStatement( query.toString());
		      int param =0;
	    	  ps.setLong(++param,impCode);
		      ps.execute();
		      
		    }
		    catch ( SQLException e )
		    {
		      throw new UnexpectedException( e.getErrorCode(),
		                                     C_ERROR_EXECUTING_STATEMENT, e );
		    }
		    finally
		    {
		      closeStatement( ps );
		      closeConnection( connection );
		    }
	}
	
	private ManagedRdbConnection connection;
	private ManagedRdbConnection getConnection(){
		if (connection==null)
			connection = OracleODSDAOFactory.getConnection();
		
		return connection;
	}
	
	public void setConnection(ManagedRdbConnection connection){
		this.connection = connection;
	}
	
	public static void log(String str, Object... var){
		if (str==null)
		return;
		
		try {
			str = str.replaceAll("?", "%s");
			
			ApplicationLogger.getInstance().info(String.format(str, var));
		}catch(Exception e ){
			ApplicationLogger.getInstance().info(str);
		}
	}
	
}
