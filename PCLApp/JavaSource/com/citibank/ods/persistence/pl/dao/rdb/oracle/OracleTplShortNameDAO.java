package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplPlayerRoleEntity;
import com.citibank.ods.entity.pl.TplShortNamePlayerEntity;
import com.citibank.ods.entity.pl.valueobject.TplShortNamePlayerEntityVO;
import com.citibank.ods.modules.product.player.functionality.valueobject.ShortNameVO;
import com.citibank.ods.persistence.pl.dao.TplShortNamePlayerDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class OracleTplShortNameDAO extends BaseOracleTplShortNamePlayerDAO implements TplShortNamePlayerDAO {
	  
	/**
	 * Nome da tabela
	 */
	private static final String C_TPL_SHORT_NAME_PLAYER = C_PL_SCHEMA + "TPL_SHORT_NAME_PLAYER";

	public void update(String plyrCnpjNbr) {
		// TODO Auto-generated method stub
		
	}

	public void insert(TplShortNamePlayerEntity tplShortNamePlayerEntity) {
		ManagedRdbConnection connection = null;
	    CitiStatement preparedStatement = null;
	    StringBuffer query = new StringBuffer();
	    try{
	    	connection = OracleODSDAOFactory.getConnection();
	        query.append( "INSERT INTO " + C_TPL_SHORT_NAME_PLAYER + " (" );
	        query.append( C_ISSUE_SHORT_NAME + ", " );
	        query.append( C_PLYR_CNPJ_NBR + ", " );
	        query.append( C_LAST_UPD_USER_ID + ", " );
	        query.append( C_LAST_UPD_DATE + ", " );
	        query.append( C_LAST_AUTH_USER_ID + ", " );
	        query.append( C_LAST_AUTH_DATE + ", " );
	        query.append( C_REC_STAT_CODE  );
	        query.append( " ) VALUES ( ");
	        query.append( "?, ?, ?, ?, ?, ?, ?)");
	        
	        preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	        int count = 1;
	        
	        TplShortNamePlayerEntityVO tplShortNamePlayerEntityVO = ( TplShortNamePlayerEntityVO ) tplShortNamePlayerEntity.getData();
	        
	        if(tplShortNamePlayerEntityVO.getIssueShortName()!= null){
	        	preparedStatement.setString( count++, tplShortNamePlayerEntityVO.getIssueShortName() );
	        }else{
	        	preparedStatement.setString( count++, null );
	        }
	        
	        if(tplShortNamePlayerEntityVO.getPlyrCnpjNbr() != null){
	        	preparedStatement.setString( count++, tplShortNamePlayerEntityVO.getPlyrCnpjNbr() );
	        }else{
	        	preparedStatement.setString( count++, null );
	        }
	        
	        if(tplShortNamePlayerEntityVO.getLastUpdUserId() != null){
	        	preparedStatement.setString( count++, tplShortNamePlayerEntityVO.getLastUpdUserId() );	        	
	        }else{
	        	preparedStatement.setString( count++, null );
	        }
	        
	        if(tplShortNamePlayerEntityVO.getLastUpdDate()!= null){
	        	preparedStatement.setTimestamp( count++ , new Timestamp( tplShortNamePlayerEntityVO.getLastUpdDate().getTime() ));
	        }else{
	        	preparedStatement.setTimestamp ( count++, null );
	        }
	        
	        if(tplShortNamePlayerEntityVO.getLastAuthUserId() != null){
	        	preparedStatement.setString( count++, tplShortNamePlayerEntityVO.getLastAuthUserId() );	        	
	        }else{
	        	preparedStatement.setString( count++, null );
	        }
	        
	        if(tplShortNamePlayerEntityVO.getLastAuthDate()!= null){
	        	preparedStatement.setTimestamp( count++ , new Timestamp( tplShortNamePlayerEntityVO.getLastAuthDate().getTime() ));
	        }else{
	        	preparedStatement.setTimestamp ( count++, null );
	        }
	        
	        if(tplShortNamePlayerEntityVO.getRecStatCode() != null){
	        	preparedStatement.setString( count++, tplShortNamePlayerEntityVO.getRecStatCode() );	        	
	        }else{
	        	preparedStatement.setString( count++, null );
	        }	
	        
	        preparedStatement.executeUpdate();
	  	    preparedStatement.replaceParametersInQuery(query.toString());
	    	
	    }catch ( Exception e ){
	    	throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, e );
	    }finally{
	        closeStatement( preparedStatement );
	        closeConnection( connection );
	    }
	}

	public List<ShortNameVO> selectByPlyr(String plyrCnpjNbr_) {
		  String criteria = "";
		  List<ShortNameVO> shortNameList = new ArrayList<ShortNameVO>();  
		  ManagedRdbConnection connection = null;
		  CitiStatement preparedStatement = null;
		  ResultSet resultSet = null;
		  StringBuffer query = new StringBuffer();
		  try {
		      connection = OracleODSDAOFactory.getConnection();
		      query.append( "SELECT " );
		      query.append( C_PLYR_CNPJ_NBR + ", " );
		      query.append( C_ISSUE_SHORT_NAME + ", " );
		      query.append( C_LAST_UPD_USER_ID);
		      query.append( " FROM " );
		      query.append( C_TPL_SHORT_NAME_PLAYER );  
		      
		      criteria = criteria + C_REC_STAT_CODE + "!= '" + BaseODSEntity.C_REC_STAT_CODE_INACTIVE + "'";
		      
		      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
		      {
		        criteria = criteria + " AND " + C_PLYR_CNPJ_NBR + " = ? ";		        
		      }
		      
		      query.append( "	WHERE " + criteria );

		      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

		      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
		      {
		        preparedStatement.setString( 1, plyrCnpjNbr_ );
		      }
		      resultSet = preparedStatement.executeQuery();
		      while(resultSet.next()){
		    	  ShortNameVO shortNameVO = new ShortNameVO();
		    	  shortNameVO.setPlyrCnpjNbr(resultSet.getString(C_PLYR_CNPJ_NBR));
		    	  shortNameVO.setIssueShortName(resultSet.getString(C_ISSUE_SHORT_NAME));
		    	  shortNameVO.setLastUpdUserId(resultSet.getString(C_LAST_UPD_USER_ID));
		    	  shortNameList.add(shortNameVO);
		      }
		      resultSet.close();
		    }
		    catch ( SQLException e ){
		      throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, e );
		    }
		    finally {
		      closeStatement( preparedStatement );
		      closeConnection( connection );
		    }
		  return shortNameList;
	}
	
	public List<ShortNameVO> selectByPlyrCnpj( String plyrCnpjNbr_ ) {
		  String criteria = "";
		  List<ShortNameVO> shortNameList = new ArrayList<ShortNameVO>();  
		  ManagedRdbConnection connection = null;
		  CitiStatement preparedStatement = null;
		  ResultSet resultSet = null;
		  StringBuffer query = new StringBuffer();
		  try {
		      connection = OracleODSDAOFactory.getConnection();
		      query.append( "SELECT " );
		      query.append( C_PLYR_CNPJ_NBR + ", " );
		      query.append( C_ISSUE_SHORT_NAME + ", " );
		      query.append( C_LAST_UPD_USER_ID);
		      query.append( " FROM " );
		      query.append( C_TPL_SHORT_NAME_PLAYER );  
		      
		      criteria = criteria + C_REC_STAT_CODE + "!= '" + BaseODSEntity.C_REC_STAT_CODE_INACTIVE + "'";

		      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
		      {
		    	  criteria = criteria + " AND " + C_PLYR_CNPJ_NBR + " != ? ";	
		      }
		      
		      query.append( "	WHERE " + criteria );

		      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

		      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
		      {
		        preparedStatement.setString( 1, plyrCnpjNbr_ );
		      }
		      resultSet = preparedStatement.executeQuery();
		      while(resultSet.next()){
		    	  ShortNameVO shortNameVO = new ShortNameVO();
		    	  shortNameVO.setPlyrCnpjNbr(resultSet.getString(C_PLYR_CNPJ_NBR));
		    	  shortNameVO.setIssueShortName(resultSet.getString(C_ISSUE_SHORT_NAME));
		    	  shortNameVO.setLastUpdUserId(resultSet.getString(C_LAST_UPD_USER_ID));
		    	  shortNameList.add(shortNameVO);
		      }
		      resultSet.close();
		    }
		    catch ( SQLException e ){
		      throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, e );
		    }
		    finally {
		      closeStatement( preparedStatement );
		      closeConnection( connection );
		    }
		  return shortNameList;
	  }

	public List<ShortNameVO> selectByPk( String plyrCnpjNbr_ )
	  {
	    ManagedRdbConnection connection = null;
	    CitiStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    StringBuffer query = new StringBuffer();
	    List<ShortNameVO> shortNameList = new ArrayList<ShortNameVO>();  
	    try
	    {
	      connection = OracleODSDAOFactory.getConnection();
	      query.append( "SELECT " );
	      query.append( C_ISSUE_SHORT_NAME + ", " );
          query.append( C_PLYR_CNPJ_NBR + ", " );
          query.append( C_LAST_UPD_USER_ID + ", " );
          query.append( C_LAST_UPD_DATE + ", " );
          query.append( C_LAST_AUTH_USER_ID + ", " );
          query.append( C_LAST_AUTH_DATE + ", " );
          query.append( C_REC_STAT_CODE  );
	      query.append( " FROM " );
	      query.append( C_TPL_SHORT_NAME_PLAYER );  

	      String criteria = "";

	      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
	      {
	        criteria = criteria + C_PLYR_CNPJ_NBR + " = ? AND ";
	        criteria = criteria + C_REC_STAT_CODE + "!= '" + BaseODSEntity.C_REC_STAT_CODE_INACTIVE + "'";
	        query.append( "	WHERE " + criteria );
	      }
	      else
	      {
	        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
	      }

	      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

	      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
	      {
	        preparedStatement.setString( 1, plyrCnpjNbr_ );
	      }

	      resultSet = preparedStatement.executeQuery();
	      while(resultSet.next()){
	    	  ShortNameVO shortNameVO = new ShortNameVO();
	    	  shortNameVO.setIssueShortName(resultSet.getString(C_ISSUE_SHORT_NAME));
	    	  shortNameVO.setPlyrCnpjNbr(resultSet.getString(C_PLYR_CNPJ_NBR));	    	  
	    	  shortNameVO.setLastUpdUserId(resultSet.getString(C_LAST_UPD_USER_ID));
	    	  shortNameVO.setLastUpdDate(resultSet.getDate(C_LAST_UPD_DATE));
	    	  shortNameVO.setLastAuthUserId(resultSet.getString(C_LAST_AUTH_USER_ID));
	    	  shortNameVO.setLastAuthDate(resultSet.getDate(C_LAST_AUTH_DATE));
	    	  shortNameVO.setRecStatCode(resultSet.getString(C_REC_STAT_CODE));
	    	  shortNameList.add(shortNameVO);
	      }
	      resultSet.close();
	    }
	    catch ( SQLException e )
	    {
	      throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, e );
	    }
	    finally
	    {
	      closeStatement( preparedStatement );
	      closeConnection( connection );
	    }
	    return shortNameList;
	  }
	
	public void inactivate( String plyrCnpjNbr_ ){
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();
		
		try{
			connection = OracleODSDAOFactory.getConnection();
			query.append( " UPDATE " + C_TPL_SHORT_NAME_PLAYER );
			query.append( " SET " );
			query.append( C_REC_STAT_CODE + "= ?" );
			query.append( " WHERE " + C_PLYR_CNPJ_NBR + " = ? " );

			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			int count = 1;

			preparedStatement.setString( count++,TplPlayerRoleEntity.C_REC_STAT_CODE_INACTIVE );

			if ( plyrCnpjNbr_ != null && !plyrCnpjNbr_.equals( "" ) ){
				preparedStatement.setString( count++, plyrCnpjNbr_ );
			}else{
				throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
			}		
			preparedStatement.executeUpdate();
			preparedStatement.replaceParametersInQuery(query.toString());
		}catch ( SQLException e ){
			throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, e );
		}finally{
			closeStatement( preparedStatement );
			closeConnection( connection );
		}
	}
	
	public boolean exists( TplShortNamePlayerEntity tplShortNamePlayerEntity ){
		ManagedRdbConnection connection = null;
	    CitiStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    StringBuffer query = new StringBuffer();
	    try{
	    	connection = OracleODSDAOFactory.getConnection();
	        query.append( "SELECT COUNT(*)" );
	        query.append( " FROM " );
	        query.append( C_TPL_SHORT_NAME_PLAYER );
	        query.append( " WHERE " + C_ISSUE_SHORT_NAME + " = ?" );
	                
	        preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	        int count = 1;
	        
	        if ( tplShortNamePlayerEntity.getData().getIssueShortName() != null
	                && !tplShortNamePlayerEntity.getData().getIssueShortName().equals( "" ) )
	        {
	        	preparedStatement.setString( count++, tplShortNamePlayerEntity.getData().getIssueShortName() );
	        }
	        
	        resultSet = preparedStatement.executeQuery();
	  	  	preparedStatement.replaceParametersInQuery(query.toString());
	  	  	
	        if ( resultSet.next() ){
	        	if ( resultSet.getInt( 1 ) != 0 ){
	        		return true;
	        	}else{
	        		return false;
	        	}
	        }else{
	        	return false;
	        }
	    
	    }catch ( SQLException e ){
	        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, e );
	    }
	    finally{
	        closeStatement( preparedStatement );
	        closeConnection( connection );
	    }
	}
	
	public void activate( TplShortNamePlayerEntity tplShortNamePlayerEntity ){
		ManagedRdbConnection connection = null;
	    CitiStatement preparedStatement = null;
	    StringBuffer query = new StringBuffer();
	    
	    try{
	    	connection = OracleODSDAOFactory.getConnection();	    	
	        query.append( "UPDATE " + C_TPL_SHORT_NAME_PLAYER + "  SET " );
	        query.append( C_PLYR_CNPJ_NBR + " = ?, " );	        
	        query.append( C_LAST_UPD_USER_ID + " = ?, " );
	        query.append( C_LAST_UPD_DATE + " = ?, " );
	        query.append( C_LAST_AUTH_USER_ID + " = ?, " );
	        query.append( C_LAST_AUTH_DATE + " = ?, " );
	        query.append( C_REC_STAT_CODE + " = ? " );
	        query.append( " WHERE " );
	        query.append( C_ISSUE_SHORT_NAME + " = ?" );

	        preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	        int count = 1;
	        
	        TplShortNamePlayerEntityVO tplShortNamePlayerEntityVO = ( TplShortNamePlayerEntityVO ) tplShortNamePlayerEntity.getData();
	        
	        if(tplShortNamePlayerEntityVO.getPlyrCnpjNbr() != null){
	        	preparedStatement.setString( count++, tplShortNamePlayerEntityVO.getPlyrCnpjNbr() );
	        }else{
	        	throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
	        }
	        
	        if(tplShortNamePlayerEntityVO.getLastUpdUserId() != null){
	        	preparedStatement.setString( count++, tplShortNamePlayerEntityVO.getLastUpdUserId() );
	        }else{
	        	throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
	        }
	        
	        if(tplShortNamePlayerEntityVO.getLastUpdDate() != null){
	        	preparedStatement.setTimestamp( count++, new Timestamp( tplShortNamePlayerEntityVO.getLastUpdDate().getTime() ) );
	        }else{
	        	throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
	        }
	        
	        if(tplShortNamePlayerEntityVO.getLastAuthUserId() != null){
	        	preparedStatement.setString( count++, tplShortNamePlayerEntityVO.getLastAuthUserId() );
	        }else{
	        	throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
	        }
	        
	        if(tplShortNamePlayerEntityVO.getLastAuthDate() != null){
	        	preparedStatement.setTimestamp( count++, new Timestamp( tplShortNamePlayerEntityVO.getLastAuthDate().getTime() ) );
	        }else{
	        	throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
	        }
	        
	        if(tplShortNamePlayerEntityVO.getRecStatCode() != null){
	        	preparedStatement.setString( count++, tplShortNamePlayerEntityVO.getRecStatCode() );
	        }else{
	        	throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
	        }
	        
	        if(tplShortNamePlayerEntityVO.getIssueShortName() != null){
	        	preparedStatement.setString( count++, tplShortNamePlayerEntityVO.getIssueShortName() );
	        }else{
	        	throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
	        }
	        
	        preparedStatement.executeUpdate();
	  	  	preparedStatement.replaceParametersInQuery(query.toString());	        
	    	
	    } catch ( Exception e ) {
	    	throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, e );
	    } finally {
	    	closeStatement( preparedStatement );
	        closeConnection( connection );
	    }
	}
}
