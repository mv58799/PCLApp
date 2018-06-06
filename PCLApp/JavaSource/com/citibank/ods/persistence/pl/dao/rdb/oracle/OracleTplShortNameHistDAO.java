package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.sql.Timestamp;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplShortNamePlayerHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplShortNamePlayerHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplShortNamePlayerHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class OracleTplShortNameHistDAO extends BaseOracleTplShortNamePlayerDAO implements TplShortNamePlayerHistDAO {
	/**
	 * Nome da tabela
	 */
	private static final String C_TPL_SHORT_NAME_PLAYER_HIST = C_PL_SCHEMA + "TPL_SHORT_NAME_PLAYER_HIST";
	
	public void insert( TplShortNamePlayerHistEntity tplShortNamePlayerHistEntity ){
		ManagedRdbConnection connection = null;
	    CitiStatement preparedStatement = null;
	    StringBuffer query = new StringBuffer();
	    try{
	    	connection = OracleODSDAOFactory.getConnection();
	        query.append( "INSERT INTO " + C_TPL_SHORT_NAME_PLAYER_HIST + " (" );
	        query.append( C_ISSUE_SHORT_NAME + ", " );
	        query.append( C_ISSUE_SHORT_REF_DATE + ", " );
	        query.append( C_PLYR_CNPJ_NBR + ", " );
	        query.append( C_LAST_UPD_USER_ID + ", " );
	        query.append( C_LAST_UPD_DATE + ", " );
	        query.append( C_LAST_AUTH_USER_ID + ", " );
	        query.append( C_LAST_AUTH_DATE + ", " );
	        query.append( C_REC_STAT_CODE  );
	        query.append( " ) VALUES ( ");
	        query.append( "?, ?, ?, ?, ?, ?, ?, ? )");
	        
	        preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	        int count = 1;
	        
	        TplShortNamePlayerHistEntityVO tplShortNamePlayerHistEntityVO = ( TplShortNamePlayerHistEntityVO ) tplShortNamePlayerHistEntity.getData();
	        
	        if(tplShortNamePlayerHistEntityVO.getIssueShortName()!= null){
	        	preparedStatement.setString( count++, tplShortNamePlayerHistEntityVO.getIssueShortName() );
	        }else{
	        	preparedStatement.setString( count++, null );
	        }
	        
	        if(tplShortNamePlayerHistEntityVO.getIssueShortRefDate()!= null){
	        	preparedStatement.setTimestamp( count++ , new Timestamp( tplShortNamePlayerHistEntityVO.getIssueShortRefDate().getTime() ));
	        }else{
	        	preparedStatement.setTimestamp ( count++, null );
	        }
	        
	        if(tplShortNamePlayerHistEntityVO.getPlyrCnpjNbr() != null){
	        	preparedStatement.setString( count++, tplShortNamePlayerHistEntityVO.getPlyrCnpjNbr() );
	        }else{
	        	preparedStatement.setString( count++, null );
	        }
	        
	        if(tplShortNamePlayerHistEntityVO.getLastUpdUserId() != null){
	        	preparedStatement.setString( count++, tplShortNamePlayerHistEntityVO.getLastUpdUserId() );	        	
	        }else{
	        	preparedStatement.setString( count++, null );
	        }
	        
	        if(tplShortNamePlayerHistEntityVO.getLastUpdDate()!= null){
	        	preparedStatement.setTimestamp( count++ , new Timestamp( tplShortNamePlayerHistEntityVO.getLastUpdDate().getTime() ));
	        }else{
	        	preparedStatement.setTimestamp ( count++, null );
	        }
	        
	        if(tplShortNamePlayerHistEntityVO.getLastAuthUserId() != null){
	        	preparedStatement.setString( count++, tplShortNamePlayerHistEntityVO.getLastAuthUserId() );	        	
	        }else{
	        	preparedStatement.setString( count++, null );
	        }
	        
	        if(tplShortNamePlayerHistEntityVO.getLastAuthDate()!= null){
	        	preparedStatement.setTimestamp( count++ , new Timestamp( tplShortNamePlayerHistEntityVO.getLastAuthDate().getTime() ));
	        }else{
	        	preparedStatement.setTimestamp ( count++, null );
	        }
	        
	        if(tplShortNamePlayerHistEntityVO.getRecStatCode() != null){
	        	preparedStatement.setString( count++, tplShortNamePlayerHistEntityVO.getRecStatCode() );	        	
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

}
