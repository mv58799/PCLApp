/*
 * Created on 13/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.math.BigInteger;
import java.sql.Timestamp;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.BaseTplProdAssetTypeEntity;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplProdAssetTypeHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdAssetTypeHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetTypeHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author lfabiano
 * @since 13/10/2008
 */
public class OracleTplProdAssetTypeHistDAO extends
	BaseOracleTplProdAssetTypeDAO implements TplProdAssetTypeHistDAO
{
  /*
   * Campos específicos da tabela
   */
  
  private String C_ASSET_TYPE_REF_DATE = "ASSET_TYPE_REF_DATE";
  
  private String C_LAST_AUTH_USER_ID = "ASSET_TYPE_LAST_AUTH_USER_ID";

  private String C_LAST_AUTH_DATE = "ASSET_TYPE_LAST_AUTH_DATE";

  private String C_REC_STAT_CODE = "ASSET_TYPE_STAT_CODE";
  
  
  
  /*
   * Nome da tabela
   */
  private static final String C_TABLE_NAME = C_PL_SCHEMA + "TPL_ASSET_TYPE_HIST";
  
  /*
   * Método List
   */
  
  public DataSet list(BigInteger prodAssetTypeCode_, BigInteger prodSubAssetCode_, String prodAssetTypeText_, String lastUpdUserId_) 
  {
	return null;
  }
    
  /**
   * Realiza a inclusão de um registro de Classe de Ativo no histórico
   */
  public TplProdAssetTypeHistEntity insert(TplProdAssetTypeHistEntity prodAssetTypeHistEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	StringBuffer query = new StringBuffer();

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "INSERT INTO " + C_TABLE_NAME + " (" );
	  query.append( C_PROD_ASSET_TYPE_CODE + ", " );
	  query.append( C_ASSET_TYPE_REF_DATE + ", " );
	  query.append( C_PROD_SUB_ASSET_CODE + ", " );
	  query.append( C_PROD_ASSET_TYPE_TEXT + ", " );
	  query.append( C_ASSET_TYPE_CUST_RPT_ORDER_NBR + ", " );
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
						  prodAssetTypeHistEntity_.getData().getProdAssetTypeCode().longValue() );
	  
      
	  TplProdAssetTypeHistEntityVO tplProdAssetTypeHistEntityVO = (TplProdAssetTypeHistEntityVO) prodAssetTypeHistEntity_.getData();
      
	  preparedStatement.setTimestamp( count++,
	  						(new Timestamp(tplProdAssetTypeHistEntityVO.getAssetTypeRefDate().getTime()) ));
	  
	  preparedStatement.setLong(count++,prodAssetTypeHistEntity_.getData().getProdSubAssetCode().longValue() );
	  
	  preparedStatement.setString( count++,prodAssetTypeHistEntity_.getData().getProdAssetTypeText() );
		
	  if ( prodAssetTypeHistEntity_.getData().getAssetTypeCustRptOrderNbr() != null )
		{
		  preparedStatement.setLong( count++,
						  prodAssetTypeHistEntity_.getData().getAssetTypeCustRptOrderNbr().longValue() );
		 }
	     else{
		  preparedStatement.setString( count++,null);	  	
		  }	
	  
	  preparedStatement.setTimestamp(count++,
							  new Timestamp(prodAssetTypeHistEntity_.getData().getLastUpdDate().getTime() ) );

	  preparedStatement.setString( count++,prodAssetTypeHistEntity_.getData().getLastUpdUserId() );
	  	  

	  preparedStatement.setString( count++, tplProdAssetTypeHistEntityVO.getRecStatCode() );
	  
	  if ( tplProdAssetTypeHistEntityVO.getLastAuthDate() != null )
	  {
		preparedStatement.setTimestamp(count++,
									  new Timestamp(
									 tplProdAssetTypeHistEntityVO.getLastAuthDate().getTime() ) );
	  }
	  else{
		  preparedStatement.setString( count++,null);	  	
	  }

	  if ( tplProdAssetTypeHistEntityVO.getLastAuthUserId() != null )
	  {
		  preparedStatement.setString( count++,
		  tplProdAssetTypeHistEntityVO.getLastAuthUserId() );
	  }
	  else{
		preparedStatement.setString( count++,null);	  	
	  }

	  preparedStatement.replaceParametersInQuery(query.toString());
	  preparedStatement.executeUpdate();

	  return prodAssetTypeHistEntity_;

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
  public BaseTplProdAssetTypeEntity find(BaseTplProdAssetTypeEntity baseTplProdAssetTypeEntity_ )
  {
	return null;
  }

}