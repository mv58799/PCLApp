/*
 * Created on 02/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplProdAssetEntity;
import com.citibank.ods.entity.pl.TplProdAssetHistEntity;
import com.citibank.ods.entity.pl.TplProductFamilyPrvtHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdAssetHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author lfabiano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class OracleTplProdAssetHistDAO extends
	BaseOracleTplProdAssetDAO implements TplProdAssetHistDAO
{
  /*
   * Campos específicos da tabela
   */
  
  private String C_ASSET_CLASS_REF_DATE = "ASSET_CLASS_REF_DATE";
  
  private String C_LAST_AUTH_USER_ID = "ASSET_CLASS_LAST_AUTH_USER_ID";

  private String C_LAST_AUTH_DATE = "ASSET_CLASS_LAST_AUTH_DATE";

  /*
   * Nome da tabela
   */
  private static final String C_TABLE_NAME = C_PL_SCHEMA + "TPL_ASSET_CLASS_HIST";
  
  /**
   * Realiza a inclusão de um registro de Classe de Ativo no histórico
   */
  public TplProdAssetHistEntity insert(TplProdAssetHistEntity prodAssetHistEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	StringBuffer query = new StringBuffer();

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "INSERT INTO " + C_TABLE_NAME + " (" );
	  query.append( C_PROD_ASSET_CODE + ", " );
	  query.append( C_ASSET_CLASS_REF_DATE + ", " );
	  query.append( C_PROD_ASSET_TEXT + ", " );
	  query.append( C_ASSET_CLASS_CUST_RPT_ORDER_NBR + ", " );
	  query.append( C_LAST_UPD_DATE + ", " );
	  query.append( C_LAST_UPD_USER_ID + ", " );
	  query.append( C_REC_STAT_CODE + ", " );
	  query.append( C_LAST_AUTH_DATE + ", " );
	  query.append( C_LAST_AUTH_USER_ID  );	  
	  query.append( ") VALUES ( " );
	  query.append( "?, ?, ?, ?, ?, ?, ?, ?, ? )" );
	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  int count = 1;

	  
	  preparedStatement.setLong(count++,
						  prodAssetHistEntity_.getData().getProdAssetCode().longValue() );
	  
      
	  TplProdAssetHistEntityVO tplProdAssetHistEntityVO = (TplProdAssetHistEntityVO) prodAssetHistEntity_.getData();
      
	  preparedStatement.setTimestamp( count++,
						   (new Timestamp(tplProdAssetHistEntityVO.getAssetClassRefDate().getTime()) ));
	  
      
	  preparedStatement.setString( count++,prodAssetHistEntity_.getData().getProdAssetText() );
	  

	  if ( prodAssetHistEntity_.getData().getAssetClassCustRptOrderNbr() != null )
	  {
		preparedStatement.setLong( count++,
						prodAssetHistEntity_.getData().getAssetClassCustRptOrderNbr().longValue() );
	  }
	  else{
		preparedStatement.setString( count++,null);	  	
	  }	    

	  preparedStatement.setTimestamp(count++,
							  new Timestamp(prodAssetHistEntity_.getData().getLastUpdDate().getTime() ) );

	  preparedStatement.setString( count++,
			prodAssetHistEntity_.getData().getLastUpdUserId() );
	  	  

	  preparedStatement.setString( count++, tplProdAssetHistEntityVO.getRecStatCode() );
	  
	  if ( tplProdAssetHistEntityVO.getLastAuthDate() != null )
	  {
		preparedStatement.setTimestamp(count++,
								      new Timestamp(
		                             tplProdAssetHistEntityVO.getLastAuthDate().getTime() ) );
	  }
	  else{
		  preparedStatement.setString( count++,null);	  	
	  }

	  if ( tplProdAssetHistEntityVO.getLastAuthUserId() != null )
	  {
		  preparedStatement.setString( count++,
		  tplProdAssetHistEntityVO.getLastAuthUserId() );
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
  public BaseTplProdAssetEntity find(BaseTplProdAssetEntity baseTplProdAssetEntity_ )
  {
  	return null;
  }


/* (non-Javadoc)
 * @see com.citibank.ods.persistence.pl.dao.TplProdAssetHistDAO#list(java.math.BigInteger, java.lang.String, java.lang.String)
 */
  public DataSet list(BigInteger prodAssetCode_, String prodAssetText_, String lastUpdUserId_) {
	// TODO Auto-generated method stub
	return null;
  }

}