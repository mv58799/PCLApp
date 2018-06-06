/*
 * Created on 06/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.math.BigInteger;
import java.sql.Timestamp;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplProdSubAssetEntity;
import com.citibank.ods.entity.pl.TplProdSubAssetHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdSubAssetHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdSubAssetHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author lfabiano
 * @since 06/10/2008
 */
public class OracleTplProdSubAssetHistDAO extends
	BaseOracleTplProdSubAssetDAO implements TplProdSubAssetHistDAO
{
  /*
   * Campos específicos da tabela
   */
  
  private String C_SUB_ASSET_CLASS_REF_DATE = "SUB_ASSET_REF_DATE";
  
  private String C_LAST_AUTH_USER_ID = "SUB_ASSET_LAST_AUTH_USER_ID";

  private String C_LAST_AUTH_DATE = "SUB_ASSET_LAST_AUTH_DATE";

  private String C_REC_STAT_CODE = "SUB_ASSET_STAT_CODE";
  
  /*
   * Nome da tabela
   */
  private static final String C_TABLE_NAME = C_PL_SCHEMA + "TPL_SUB_ASSET_CLASS_HIST";
  
  /*
   * Método List
   */
  
  public DataSet list(BigInteger prodSubAssetCode_, BigInteger prodAssetCode_, String prodSubAssetText_, String lastUpdUserId_) 
  {
    return null;
  }
    
  /**
   * Realiza a inclusão de um registro de Classe de Ativo no histórico
   */
  public TplProdSubAssetHistEntity insert(TplProdSubAssetHistEntity prodAssetHistEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	StringBuffer query = new StringBuffer();

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "INSERT INTO " + C_TABLE_NAME + " (" );
	  query.append( C_PROD_SUB_ASSET_CODE + ", " );
	  query.append( C_SUB_ASSET_CLASS_REF_DATE + ", " );
	  query.append( C_PROD_ASSET_CODE + ", " );
	  query.append( C_PROD_SUB_ASSET_TEXT + ", " );
	  query.append( C_SUB_ASSET_CLASS_RPT_ORDER_NBR + ", " );
	  query.append( C_LAST_UPD_DATE + ", " );
	  query.append( C_LAST_UPD_USER_ID + ", " );
	  query.append( C_REC_STAT_CODE + ", " );
	  query.append( C_LAST_AUTH_DATE + ", " );
	  query.append( C_LAST_AUTH_USER_ID  );	  
	  query.append( ") VALUES ( " );
	  query.append( "?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );
	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  int count = 1;

	  
	  preparedStatement.setLong(count++,
						  prodAssetHistEntity_.getData().getProdSubAssetCode().longValue() );
	  
      
	  TplProdSubAssetHistEntityVO tplProdSubAssetHistEntityVO = (TplProdSubAssetHistEntityVO) prodAssetHistEntity_.getData();
      
	  preparedStatement.setTimestamp( count++,
						   (new Timestamp(tplProdSubAssetHistEntityVO.getSubAssetRefDate().getTime()) ));						   
						   
	  preparedStatement.setLong(count++,
	  							prodAssetHistEntity_.getData().getProdAssetCode().longValue() );
      
	  preparedStatement.setString( count++,prodAssetHistEntity_.getData().getProdSubAssetText() );
	  

	  if ( prodAssetHistEntity_.getData().getSubAssetClassRptOrderNbr() != null )
	  {
		preparedStatement.setLong( count++,
						prodAssetHistEntity_.getData().getSubAssetClassRptOrderNbr().longValue() );
	  }
	  else{
		preparedStatement.setString( count++,null);	  	
	  }	    

	  preparedStatement.setTimestamp(count++,
							  new Timestamp(prodAssetHistEntity_.getData().getLastUpdDate().getTime() ) );

	  preparedStatement.setString( count++,
			prodAssetHistEntity_.getData().getLastUpdUserId() );
	  	  

	  preparedStatement.setString( count++, tplProdSubAssetHistEntityVO.getRecStatCode() );
	  
	  if ( tplProdSubAssetHistEntityVO.getLastAuthDate() != null )
	  {
		preparedStatement.setTimestamp(count++,
									  new Timestamp(
									 tplProdSubAssetHistEntityVO.getLastAuthDate().getTime() ) );
	  }
	  else{
		  preparedStatement.setString( count++,null);	  	
	  }

	  if ( tplProdSubAssetHistEntityVO.getLastAuthUserId() != null )
	  {
		  preparedStatement.setString( count++,
		  tplProdSubAssetHistEntityVO.getLastAuthUserId() );
	  }
	  else{
		preparedStatement.setString( count++,null);	  	
	  }

	  preparedStatement.replaceParametersInQuery(query.toString());
	  preparedStatement.executeUpdate();

	  return prodAssetHistEntity_;

	}
	catch ( Exception e )
	{
	  throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, e );
	}
	finally
	{
	  closeStatement( preparedStatement );
	  closeConnection( connection );
	}
  }

  /**
   * Recupera um registro de histórico a partir
   * do código passado
   */
  public BaseTplProdSubAssetEntity find(BaseTplProdSubAssetEntity baseTplProdSubAssetEntity_ )
  {
	return null;
  }

}