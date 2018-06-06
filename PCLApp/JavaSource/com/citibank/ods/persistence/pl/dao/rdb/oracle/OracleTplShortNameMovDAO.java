package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplShortNamePlayerMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplShortNamePlayerMovEntityVO;
import com.citibank.ods.modules.product.player.functionality.valueobject.ShortNameVO;
import com.citibank.ods.persistence.pl.dao.TplShortNamePlayerMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * 
 * @author aribas
 *
 */
public class OracleTplShortNameMovDAO extends BaseOracleTplShortNamePlayerDAO implements TplShortNamePlayerMovDAO {
  /*
   * Nome da tabela
   */
  private static final String C_TPL_SHORT_NAME_PLAYER_MOV = C_PL_SCHEMA + "TPL_SHORT_NAME_PLAYER_MOV";
  
  public TplShortNamePlayerMovEntity insert( TplShortNamePlayerMovEntity tplShortNamePlayerMovEntity){
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();
    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_SHORT_NAME_PLAYER_MOV + " ( " );
      query.append( C_ISSUE_SHORT_NAME + ", " );
      query.append( C_PLYR_CNPJ_NBR + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_OPERN_CODE + ") " );
      query.append( " VALUES ( ?, ?, ?, ?, ? ) " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      TplShortNamePlayerMovEntityVO tplShortNamePlayerMovEntityVO = ( TplShortNamePlayerMovEntityVO ) tplShortNamePlayerMovEntity.getData();
      int count = 1;
      
      if ( tplShortNamePlayerMovEntityVO.getIssueShortName() != null
              && !tplShortNamePlayerMovEntityVO.getIssueShortName().equals( "" ) )
     {
       preparedStatement.setString( count++, tplShortNamePlayerMovEntity.getData().getIssueShortName() );
     }
     else
     {
       preparedStatement.setString( count++, null );
     }

      if ( tplShortNamePlayerMovEntityVO.getPlyrCnpjNbr() != null
           && !tplShortNamePlayerMovEntityVO.getPlyrCnpjNbr().equals( "" ) )
      {
        preparedStatement.setString( count++, tplShortNamePlayerMovEntity.getData().getPlyrCnpjNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplShortNamePlayerMovEntityVO.getLastUpdUserId() != null
           && !tplShortNamePlayerMovEntityVO.getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString( count++, tplShortNamePlayerMovEntity.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      if ( tplShortNamePlayerMovEntityVO.getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                		tplShortNamePlayerMovEntityVO.getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      
      if ( tplShortNamePlayerMovEntityVO.getOpernCode() != null )
      {
        preparedStatement.setString( count++, tplShortNamePlayerMovEntityVO.getOpernCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally{
        closeStatement( preparedStatement );
        closeConnection( connection );
    }
    return tplShortNamePlayerMovEntity;
  }
  
  public void delete( String plyrCnpjNbr ){
	  ManagedRdbConnection connection = null;
	  CitiStatement preparedStatement = null;
	  StringBuffer query = new StringBuffer();
	  try {
	      connection = OracleODSDAOFactory.getConnection();
	      query.append( " DELETE FROM  " + C_TPL_SHORT_NAME_PLAYER_MOV );
	      query.append( " WHERE " + C_PLYR_CNPJ_NBR + " = ? " );

	      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	      int count = 1;

	      if ( plyrCnpjNbr != null ){
	        preparedStatement.setString( count++, plyrCnpjNbr );
	      }else{
	        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
	      }
	      preparedStatement.executeUpdate();
		  preparedStatement.replaceParametersInQuery(query.toString());
	    }
	    catch ( SQLException e )
	    {
	      throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
	    }
	    finally
	    {
	      closeStatement( preparedStatement );
	      closeConnection( connection );
	    }
  }
  
  public List<ShortNameVO> selectByPlyr( String plyrCnpjNbr_ ) {
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
	      query.append( C_LAST_UPD_USER_ID + ", ");
	      query.append( C_LAST_UPD_DATE);
	      query.append( " FROM " );
	      query.append( C_TPL_SHORT_NAME_PLAYER_MOV );  

	      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
	      {
	        criteria = criteria + C_PLYR_CNPJ_NBR + " = ? ";
	        query.append( "	WHERE " + criteria );
	      }

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
	    	  shortNameVO.setLastUpdDate(resultSet.getDate(C_LAST_UPD_DATE));
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
	      query.append( C_TPL_SHORT_NAME_PLAYER_MOV );  

	      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
	      {
	        criteria = criteria + C_PLYR_CNPJ_NBR + " <> ? ";
	        query.append( "	WHERE " + criteria );
	      }

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

}
