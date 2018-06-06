package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.TableTypeEnum;
import com.citibank.ods.common.util.Util;
import com.citibank.ods.entity.pl.valueobject.TplFundMortEntityVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

public class OracleTplFundMortDAO extends BaseOracleTplFundMortDAO {

	public TplFundMortEntityVO getFundsByName(String fundName, TableTypeEnum tableType){
		  ManagedRdbConnection connection = null;
		  PreparedStatement ps = null;
		    StringBuffer query = new StringBuffer();

		    try
		    {
		    	 connection = OracleODSDAOFactory.getConnection();
		    	 query.append("SELECT FUND_NAME_TEXT, FUND_CODE, ");
		    	 if (!tableType.equals(TableTypeEnum.MOVEMENT)){
		    		 query.append("LAST_APPRV_DATE,LAST_APPRV_USER_ID,");
		    	 }
		    	 if (!tableType.equals(TableTypeEnum.EFFECTIVE)){
		    		 query.append("OPERN_TYPE_CODE,");
		    	 }
		    	 query.append( " LAST_UPD_DATE, LAST_UPD_USER_ID FROM PL.TPL_FUND_MORT")
		    	 .append(tableType.getSufixo())
		    	 .append(" WHERE FUND_NAME_TEXT = ?");
		    	 
		    	 ps = connection.prepareCall(query.toString());
		    	 ps.setString(1, fundName);
		    	 
		    	 ResultSet rs = ps.executeQuery();
		    	 if (rs.next()){
		    		 TplFundMortEntityVO vo = new TplFundMortEntityVO();
		    		 String fundCode = rs.getString("FUND_CODE");
		    			 vo.setFundCode(fundCode);
		    		 
		    		 vo.setFundName(rs.getString("FUND_NAME_TEXT"));
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
	
	public List<TplFundMortEntityVO> getFundsByNameLike(String fundName, TableTypeEnum tableType){
		return getFundsByNameLike(fundName, tableType, true);
	}

	public List<TplFundMortEntityVO> getFundsByNameLike(String fundName, TableTypeEnum tableType, boolean fundWithCode){
		  ManagedRdbConnection connection = null;
		  PreparedStatement ps = null;
		    StringBuffer query = new StringBuffer();
		    List<TplFundMortEntityVO> ret = new ArrayList<TplFundMortEntityVO>();
		    try
		    {
		    	 connection = OracleODSDAOFactory.getConnection();
		    	 query.append("SELECT FUND_NAME_TEXT, FUND_CODE, ");
		    	 if (!tableType.equals(TableTypeEnum.MOVEMENT)){
		    		 query.append("LAST_APPRV_DATE,LAST_APPRV_USER_ID,");
		    	 }
		    	 if (!tableType.equals(TableTypeEnum.EFFECTIVE)){
		    		 query.append("OPERN_TYPE_CODE,");
		    	 }
		    	 query.append( " LAST_UPD_DATE, LAST_UPD_USER_ID FROM PL.TPL_FUND_MORT")
		    	 .append(tableType.getSufixo())
		    	 .append(" WHERE lower(FUND_NAME_TEXT) like lower(?)");
		    	 if (!fundWithCode){
		    		 query.append(" AND (FUND_CODE IS NULL)");
		    	 }else {
		    		 query.append(" AND (FUND_CODE IS NOT NULL )");
		    	 }
		    	 ps = connection.prepareCall(query.toString());
		    	 ps.setString(1,"%" +  fundName + "%");
		    	 
		    	 ResultSet rs = ps.executeQuery();
		    	 while (rs.next()){
		    		 TplFundMortEntityVO vo = new TplFundMortEntityVO();
		    		 String fundCode = rs.getString("FUND_CODE");
		    		 if (fundCode!= null){
		    			 vo.setFundCode(fundCode);
		    		 }
		    		 vo.setFundName(rs.getString("FUND_NAME_TEXT"));
		    		 if (!tableType.equals(TableTypeEnum.MOVEMENT)){
		    			 vo.setLastApprvDate(rs.getTimestamp("LAST_APPRV_DATE"));
		    			 vo.setLastApprvUserId(rs.getString("LAST_APPRV_USER_ID"));
		    		 }
		    		 if (!tableType.equals(TableTypeEnum.EFFECTIVE)){
		    			 vo.setOpernTypeCode(rs.getString("OPERN_TYPE_CODE"));
		    		 }
		    		 vo.setLastUpdDate(rs.getTimestamp("LAST_UPD_DATE"));
	    			 vo.setLastUpdUserId(rs.getString("LAST_UPD_USER_ID"));
	    			 ret.add(vo);
		    	 }
		    	 return ret;
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

	public void insert( TplFundMortEntityVO vo )
	  {
		insert(vo,true);
	  }
	public void insert( TplFundMortEntityVO vo, boolean autoCommit )
	  {
	    ManagedRdbConnection connection = null;
	    PreparedStatement ps = null;
	    StringBuffer query = new StringBuffer();

	    try
	    {
	      connection = OracleODSDAOFactory.getConnection();
	      connection.setAutoCommit(autoCommit);
	      query.append( "INSERT INTO PL.TPL_FUND_MORT").append(vo.getTableType().getSufixo())
	      		.append( "( FUND_NAME_TEXT ,  " ) 
	      		.append( "FUND_CODE ," );
	      if (vo.getTableType() != TableTypeEnum.MOVEMENT){
	      		query.append( "LAST_APPRV_DATE  ," ) ;
	      		query.append( "LAST_APPRV_USER_ID," ) ;
	      }
	      if (vo.getTableType() != TableTypeEnum.EFFECTIVE){
	    	  query.append( "OPERN_TYPE_CODE," ) ;
	      }
	      query.append( "LAST_UPD_DATE      ," ) 
	      		.append( "LAST_UPD_USER_ID    )" );
	      query.append( " VALUES " );
	      if (vo.getTableType() == TableTypeEnum.MOVEMENT){
	    	  query.append( " ( "+com.citibank.ods.common.util.Util.printNTimes("?", 5)+ ") " );
	      }else if (vo.getTableType() == TableTypeEnum.EFFECTIVE){
	    	  query.append( " ( "+com.citibank.ods.common.util.Util.printNTimes("?", 6)+ ") " );
	      }else {
	    	  query.append( " ( "+com.citibank.ods.common.util.Util.printNTimes("?", 7)+ ") " );
	      }

	      ps = connection.prepareStatement( query.toString());
	      int param = 0;
	     
	      ps.setString(++param, vo.getFundName());
	      if (vo.getFundCode()!= null &&  !Util.isEmpty(vo.getFundCode())  ){
	    	  ps.setString(++param, vo.getFundCode());
	      }else {	
	    	  ps.setNull(++param, Types.NUMERIC);
	      }
	      if (vo.getTableType() != TableTypeEnum.MOVEMENT){
		      ps.setTimestamp(++param,com.citibank.ods.common.util.Util.parseSqlTimestamp(vo.getLastApprvDate()));
		      ps.setString(++param,vo.getLastApprvUserId());
	      }
	      
	      if (vo.getTableType() != TableTypeEnum.EFFECTIVE){
	    	  ps.setString(++param,vo.getOpernTypeCode());
	      }
	      ps.setTimestamp(++param,com.citibank.ods.common.util.Util.parseSqlTimestamp(vo.getLastUpdDate()));
	      ps.setString(++param,vo.getLastUpdUserId());
	      
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
	
	
	public void update( TplFundMortEntityVO vo, String oldName )
	  {
	    ManagedRdbConnection connection = null;
	    PreparedStatement ps = null;
	    StringBuffer query = new StringBuffer();

	    try
	    {
	      connection = OracleODSDAOFactory.getConnection();
	      query.append( "UPDATE PL.TPL_FUND_MORT").append(vo.getTableType().getSufixo())
	      		.append( " SET FUND_NAME_TEXT = ? ,  " ) 
	      		.append( "FUND_CODE = ? ," );
	      if (vo.getTableType() != TableTypeEnum.MOVEMENT){
	      		query.append( "LAST_APPRV_DATE  = ? ," ) ;
	      		query.append( "LAST_APPRV_USER_ID = ? ," ) ;
	      }
	      if (vo.getTableType() != TableTypeEnum.EFFECTIVE){
	    	  query.append( "OPERN_TYPE_CODE = ? ," ) ;
	      }
	      query.append( "LAST_UPD_DATE = ?      ," ) 
	      		.append( "LAST_UPD_USER_ID = ? WHERE FUND_NAME_TEXT = ? " );
	      
	      ps = connection.prepareStatement( query.toString());
	      int param = 0;
	     
	      ps.setString(++param, vo.getFundName());
	      ps.setString(++param, vo.getFundCode());
	      if (vo.getTableType() != TableTypeEnum.MOVEMENT){
		      ps.setTimestamp(++param,com.citibank.ods.common.util.Util.parseSqlTimestamp(vo.getLastApprvDate()));
		      ps.setString(++param,vo.getLastApprvUserId());
	      }
	      
	      if (vo.getTableType() != TableTypeEnum.EFFECTIVE){
	    	  ps.setString(++param,vo.getOpernTypeCode());
	      }
	      ps.setTimestamp(++param,com.citibank.ods.common.util.Util.parseSqlTimestamp(vo.getLastUpdDate()));
	      ps.setString(++param,vo.getLastUpdUserId());
	      ps.setString(++param,oldName);
	      
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
	
	public void delete(String name, TableTypeEnum tableType )
	  {
	    ManagedRdbConnection connection = null;
	    PreparedStatement ps = null;
	    StringBuffer query = new StringBuffer();

	    try
	    {
	      connection = OracleODSDAOFactory.getConnection();
	      query.append( "DELETE FROM PL.TPL_FUND_MORT").append(tableType.getSufixo())
	      		.append( "  WHERE FUND_NAME_TEXT = ? " );
	      
	      ps = connection.prepareStatement( query.toString());
	      int param = 0;
	      ps.setString(++param,name);
	      
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
	
	
	
	public void updateDataSubscpt( String name ,String oldName, TableTypeEnum tableType)
	  {
	    ManagedRdbConnection connection = null;
	    PreparedStatement ps = null;
	    StringBuffer query = new StringBuffer();

	    try
	    {
	      connection = OracleODSDAOFactory.getConnection();
	      query.append( "UPDATE PL.TPL_DATA_SUBCPT").append(tableType.getSufixo())
	      		.append( " set FUND_NAME_TEXT = ?  WHERE FUND_NAME_TEXT = ?");
	      ps = connection.prepareStatement( query.toString());
	      int param = 0;
	     
	      ps.setString(++param, name);
	      ps.setString(++param, oldName);
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
	
}
