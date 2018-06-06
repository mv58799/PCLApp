/*
 * Created on 22/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplProdAssetTypeEntity;
import com.citibank.ods.entity.pl.TplProdAssetTypeEntity;
import com.citibank.ods.entity.pl.TplProdAssetTypeMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdAssetTypeMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetTypeMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class OracleTplProdAssetTypeMovDAO extends BaseOracleTplProdAssetTypeDAO
	implements TplProdAssetTypeMovDAO
{
  /*
   * Nome da tabela
   */
  private static final String C_TPL_ASSET_TYPE_MOV = C_PL_SCHEMA + "TPL_ASSET_TYPE_MOV";

  /*
   * Campos específicos da tabela
   */
  private String C_LAST_AUTH_USER_ID = "ASSET_TYPE_LAST_AUTH_USER_ID";

  private String C_LAST_AUTH_DATE = "ASSET_TYPE_LAST_AUTH_DATE";

  private String C_REC_STAT_CODE = "ASSET_TYPE_STAT_CODE";

  protected String C_REC_STAT_TEXT = "REC_STAT_TEXT";
  
  protected String C_OPERN_CODE = "ASSET_TYPE_OPERN_CODE";  
  
  /**
   * Este método retorna uma lista de qualificador de produto que se enquadre
   * com os critérios informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdAssetTypeDAO#list(java.math.BigInteger,
   *      java.lang.String)
   */  
  public DataSet list( BigInteger prodAssetTypeCode_, String prodQlfyText_, String lastUpdUserId )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	ResultSet resultSet = null;
	ResultSetDataSet rsds = null;
	StringBuffer query = new StringBuffer();

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "SELECT " );
	  query.append( C_PROD_ASSET_TYPE_CODE + ", " );
	  query.append( C_PROD_SUB_ASSET_CODE + ", " );
	  query.append( C_PROD_ASSET_TYPE_TEXT + ", " );
	  query.append( C_LAST_UPD_USER_ID + ", " );
	  query.append( C_LAST_UPD_DATE + ", " );
	  query.append( C_LAST_AUTH_DATE + ", " );
	  query.append( C_LAST_AUTH_USER_ID + ", " );
	  query.append( C_REC_STAT_CODE );
	  query.append( " FROM " );
	  query.append( C_TPL_ASSET_TYPE_MOV );

	  String criteria = "";

	  criteria = criteria + C_REC_STAT_CODE + " != '"
				 + BaseTplProdAssetTypeEntity.C_REC_STAT_CODE_INACTIVE
				 + "' AND ";

	  if ( prodAssetTypeCode_ != null && prodAssetTypeCode_.longValue() != 0 )
	  {
		criteria = criteria + C_PROD_ASSET_TYPE_CODE + " = ? AND ";
	  }
	  if ( prodQlfyText_ != null && !prodQlfyText_.equals( "" ) )
	  {
		criteria = criteria + "UPPER(\"" + C_PROD_ASSET_TYPE_TEXT + "\") LIKE ? AND ";
	  }
	  if ( criteria.length() > 0 )
	  {
		criteria = criteria.substring( 0, criteria.length() - 5 );
		query.append( "	WHERE " + criteria + " ORDER BY " + C_PROD_ASSET_TYPE_TEXT
					  + " ASC " + " , " + C_PROD_ASSET_TYPE_CODE );
	  }

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  int count = 1;

	  if ( prodAssetTypeCode_ != null && prodAssetTypeCode_.longValue() != 0 )
	  {
		preparedStatement.setLong( count++, prodAssetTypeCode_.longValue() );
	  }
	  if ( prodQlfyText_ != null && !prodQlfyText_.equals( "" ) )
	  {
		preparedStatement.setString( count++, "%" + prodQlfyText_.toUpperCase() + "%" );
	  }

	  resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

	  rsds = new ResultSetDataSet( resultSet );
	  resultSet.close();
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

	String[] codeColumn = { C_REC_STAT_CODE };
	String[] nameColumn = { C_REC_STAT_TEXT };
	rsds.outerJoin( ODSConstraintDecoder.decodeRecStatus(), codeColumn,
					codeColumn, nameColumn );

	return rsds;
  }

  /**
   * Este método insere um novo registro de qualificador de produto
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdAssetTypeDAO#insert(com.citibank.ods.entity.pl.TplProdAssetTypeEntity)
   */
  public TplProdAssetTypeMovEntity insert(
							TplProdAssetTypeMovEntity tplProdAssetTypeMovEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	StringBuffer query = new StringBuffer();

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "INSERT INTO " + C_TPL_ASSET_TYPE_MOV + " (" );
	  query.append( C_PROD_ASSET_TYPE_CODE + ",  " );
	  query.append( C_PROD_ASSET_TYPE_TEXT + ", " );
	  query.append( C_LAST_UPD_DATE + ", " );
	  query.append( C_LAST_UPD_USER_ID + ", " );
	  query.append( C_LAST_AUTH_DATE + ", " );
	  query.append( C_LAST_AUTH_USER_ID + ", " );
	  query.append( C_REC_STAT_CODE +", ");
	  query.append( C_PROD_SUB_ASSET_CODE + ", ");
	  query.append( C_OPERN_CODE + ", ");
	  query.append( C_ASSET_TYPE_CUST_RPT_ORDER_NBR);
	  query.append( ") VALUES ( " );
	  
	  if((( TplProdAssetTypeMovEntityVO )tplProdAssetTypeMovEntity_.getData()).getOpernCode().equals(TplProdAssetTypeMovEntity.C_OPERN_CODE_UPDATE)
	     || (( TplProdAssetTypeMovEntityVO )tplProdAssetTypeMovEntity_.getData()).getOpernCode().equals(TplProdAssetTypeMovEntity.C_OPERN_CODE_DELETE))
		
		query.append( "?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
	  else
		query.append( "PL.SQ_ASSET_TYPE_CODE.Nextval, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  int count = 1;

	  if((( TplProdAssetTypeMovEntityVO )tplProdAssetTypeMovEntity_.getData()).getOpernCode().equals(TplProdAssetTypeMovEntity.C_OPERN_CODE_UPDATE)
		 || (( TplProdAssetTypeMovEntityVO )tplProdAssetTypeMovEntity_.getData()).getOpernCode().equals(TplProdAssetTypeMovEntity.C_OPERN_CODE_DELETE))
	  {
		preparedStatement.setLong(
						   count++,
						   tplProdAssetTypeMovEntity_.getData().getProdAssetTypeCode().longValue() );
	  }

	  if ( tplProdAssetTypeMovEntity_.getData().getProdAssetTypeText() != null )
	  {
		preparedStatement.setString( count++,
							 tplProdAssetTypeMovEntity_.getData().getProdAssetTypeText() );
	  }

	  if ( tplProdAssetTypeMovEntity_.getData().getLastUpdDate() != null )
	  {
		preparedStatement.setTimestamp(
								count++,
								new Timestamp(
											   tplProdAssetTypeMovEntity_.getData().getLastUpdDate().getTime() ) );
	  }

	  if ( tplProdAssetTypeMovEntity_.getData().getLastUpdUserId() != null )
	  {
		preparedStatement.setString(
							 count++,
							 tplProdAssetTypeMovEntity_.getData().getLastUpdUserId() );
	  }

	  TplProdAssetTypeMovEntityVO tplProdAssetTypeMovEntityVO = ( TplProdAssetTypeMovEntityVO ) tplProdAssetTypeMovEntity_.getData();
	  
	  preparedStatement.setString( count++, null );
	  preparedStatement.setString( count++, null );
	  preparedStatement.setString( count++, "A" );
	  
	  if ( tplProdAssetTypeMovEntity_.getData().getProdSubAssetCode() != null )
	  {
		preparedStatement.setLong(
							 count++,
							 tplProdAssetTypeMovEntity_.getData().getProdSubAssetCode().longValue() );
	  }
	  
	  preparedStatement.setString(
				   count++,
					   (( TplProdAssetTypeMovEntityVO )tplProdAssetTypeMovEntity_.getData()).getOpernCode());
					   
	  if(tplProdAssetTypeMovEntity_.getData().getAssetTypeCustRptOrderNbr() != null){
	    
	    preparedStatement.setLong(
		  	                      count++,
								  tplProdAssetTypeMovEntity_.getData().getAssetTypeCustRptOrderNbr().longValue() );
	  	
	  }
	  else{
		preparedStatement.setString(count++,null);
	  }
	  	
	  preparedStatement.replaceParametersInQuery(query.toString());
	  preparedStatement.executeUpdate();
	  

	  return tplProdAssetTypeMovEntity_;
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
   * Este método altera os dados de um qualificador de produto
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdAssetTypeDAO#update(com.citibank.ods.entity.pl.TplProdAssetTypeEntity)
   */
  public void update( TplProdAssetTypeMovEntity tplProdAssetTypeMovEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	StringBuffer query = new StringBuffer();

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "UPDATE " + C_TPL_ASSET_TYPE_MOV );
	  query.append( " SET " );
	  query.append( C_PROD_ASSET_TYPE_TEXT + " = ?, " );
	  query.append( C_LAST_UPD_DATE + " = ?, " );
	  query.append( C_LAST_UPD_USER_ID + " = ?, " );	  
	  query.append( C_REC_STAT_CODE + " = ?, " );
	  query.append( C_OPERN_CODE + "= ?, ");
	  query.append( C_PROD_SUB_ASSET_CODE + "= ?, ");
	  query.append( C_ASSET_TYPE_CUST_RPT_ORDER_NBR + "= ? ");
	  query.append( " WHERE " );
	  query.append( C_PROD_ASSET_TYPE_CODE + "= ? " );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  int count = 1;

	  preparedStatement.setString( count++,
							 tplProdAssetTypeMovEntity_.getData().getProdAssetTypeText() );
	  

	  preparedStatement.setTimestamp(count++,
		new Timestamp(tplProdAssetTypeMovEntity_.getData().getLastUpdDate().getTime() ) );
	  

	  preparedStatement.setString(count++,tplProdAssetTypeMovEntity_.getData().getLastUpdUserId() );
	  

	  TplProdAssetTypeMovEntityVO tplProdAssetTypeMovEntityVO = ( TplProdAssetTypeMovEntityVO ) tplProdAssetTypeMovEntity_.getData();
	  	  
	  preparedStatement.setString( count++, tplProdAssetTypeMovEntityVO.getRecStatCode() );
	  
	  preparedStatement.setString(count++,
							 (( TplProdAssetTypeMovEntityVO )tplProdAssetTypeMovEntity_.getData()).getOpernCode());

	  preparedStatement.setLong(count++,
						 tplProdAssetTypeMovEntity_.getData().getProdSubAssetCode().longValue() );
	 
	  
	 if(tplProdAssetTypeMovEntity_.getData().getAssetTypeCustRptOrderNbr() != null){
	    
		preparedStatement.setLong(
						   count++,
						   tplProdAssetTypeMovEntity_.getData().getAssetTypeCustRptOrderNbr().longValue() );
	  	
	 }
	 else{
	    preparedStatement.setString(count++,null);
     }
     
	 preparedStatement.setLong(count++,
						   tplProdAssetTypeMovEntity_.getData().getProdAssetTypeCode().longValue() );	 
	  
	  preparedStatement.replaceParametersInQuery(query.toString());
	  preparedStatement.executeUpdate();

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
   * Este método efetua a deleção lógica de um qualificador de produto que se
   * enquadre nos critérios informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdAssetTypeDAO#delete(com.citibank.ods.entity.pl.TplProdAssetTypeMovEntity)
   */
  public void delete( TplProdAssetTypeMovEntity tplProdAssetTypeEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	StringBuffer query = new StringBuffer();

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( " DELETE " + C_TPL_ASSET_TYPE_MOV );
	  query.append( " WHERE " + C_PROD_ASSET_TYPE_CODE + " = ? " );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  
	  preparedStatement.setLong(1,
						 tplProdAssetTypeEntity_.getData().getProdAssetTypeCode().longValue() );

	  preparedStatement.replaceParametersInQuery(query.toString());
	  preparedStatement.executeUpdate();	  
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

  /**
   * Este método busca um qualficador de produto que se enquadre com os
   * critérios informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.BaseTplProdAssetTypeDAO#find(com.citibank.ods.entity.pl.BaseTplProdAssetTypeEntity)
   */
  public BaseTplProdAssetTypeEntity find(
										BaseTplProdAssetTypeEntity baseTplProdAssetTypeEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	ResultSet resultSet = null;
	StringBuffer query = new StringBuffer();

	ArrayList tplProdAssetTypeEntities;
	BaseTplProdAssetTypeEntity baseTplProdAssetTypeEntity = null;

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append("SELECT  ");
	  query.append("a." + C_PROD_ASSET_TYPE_CODE + ", ");
	  query.append("a." + C_PROD_ASSET_TYPE_TEXT + ", ");
	  query.append("a." + C_LAST_UPD_USER_ID + ", ");
	  query.append("a." + C_LAST_UPD_DATE + ", ");
	  query.append("a." + C_REC_STAT_CODE + ", ");
	  query.append("b." + C_SUB_ASSET_CODE + ", ");			
	  query.append("c." + C_ASSET_CODE + ", ");			
	  query.append("a." + C_ASSET_TYPE_CUST_RPT_ORDER_NBR + ", ");
	  query.append("a." + C_OPERN_CODE);
	  query.append(" FROM ");
	  query.append(C_TPL_ASSET_TYPE_MOV + " a, ");
	  query.append(C_TPL_SUB_ASSET_CLASS + " b, ");
	  query.append(C_TPL_ASSET_CLASS + " c ");
	  query.append(" WHERE ");
	  query.append(" a." + C_PROD_SUB_ASSET_CODE + " = b."	+ C_PROD_SUB_ASSET_CODE	+ " AND ");
	  query.append(" b." + C_ASSET_CODE + " = c." + C_ASSET_CODE);
	  query.append(" AND a." + C_PROD_ASSET_TYPE_CODE + " = ?");

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

	  preparedStatement.setLong(
						 1,
						 baseTplProdAssetTypeEntity_.getData().getProdAssetTypeCode().longValue() );

	  resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

	  tplProdAssetTypeEntities = instantiateFromResultSet( resultSet );

	  if ( tplProdAssetTypeEntities.size() == 0 )
	  {
		throw new NoRowsReturnedException();
	  }
	  else if ( tplProdAssetTypeEntities.size() > 1 )
	  {
		throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
	  }
	  else
	  {
		baseTplProdAssetTypeEntity = ( BaseTplProdAssetTypeEntity ) tplProdAssetTypeEntities.get( 0 );
	  }

	  return baseTplProdAssetTypeEntity;
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

  /**
   * Este método cria um array de entity apartir de um result set
   * 
   * @param resultSet_
   * @return ArrayList de Entity
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
	TplProdAssetTypeMovEntity tplProdAssetTypeMovEntity;
	Timestamp timestamp;
	Date date;
	ArrayList tplProdAssetTypeMovEntities = new ArrayList();

	try
	{
	  while ( resultSet_.next() )
	  {
		tplProdAssetTypeMovEntity = new TplProdAssetTypeMovEntity();

		tplProdAssetTypeMovEntity.getData().setProdAssetTypeCode(
														 new BigInteger(
																		 resultSet_.getString( C_PROD_ASSET_TYPE_CODE ) ) );
		tplProdAssetTypeMovEntity.getData().setProdAssetTypeText(
														 resultSet_.getString( C_PROD_ASSET_TYPE_TEXT ) );
		timestamp = resultSet_.getTimestamp( C_LAST_UPD_DATE );
		date = new Date( timestamp.getTime() );
		tplProdAssetTypeMovEntity.getData().setLastUpdDate( date );
		tplProdAssetTypeMovEntity.getData().setLastUpdUserId(resultSet_.getString( C_LAST_UPD_USER_ID ) );
		
		if(resultSet_.getString(C_ASSET_TYPE_CUST_RPT_ORDER_NBR) != null){
			tplProdAssetTypeMovEntity.getData().setAssetTypeCustRptOrderNbr(
							  new BigInteger(resultSet_.getString(C_ASSET_TYPE_CUST_RPT_ORDER_NBR)));						
		}else{
			tplProdAssetTypeMovEntity.getData().setAssetTypeCustRptOrderNbr(null);
		}
		
		tplProdAssetTypeMovEntity.getData().setProdSubAssetCode(
		  new BigInteger(resultSet_.getString(C_PROD_SUB_ASSET_CODE)));
				
		tplProdAssetTypeMovEntity.getData().setProdAssetCode(
		  new BigInteger(resultSet_.getString(C_ASSET_CODE)));
		
		// Casting para a atribuicao das colunas especificas
		TplProdAssetTypeMovEntityVO tplProdAssetTypeMovEntityVO = ( TplProdAssetTypeMovEntityVO ) tplProdAssetTypeMovEntity.getData();
		
		tplProdAssetTypeMovEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );
		tplProdAssetTypeMovEntityVO.setOpernCode( resultSet_.getString( C_OPERN_CODE ) );

		tplProdAssetTypeMovEntities.add( tplProdAssetTypeMovEntity );
	  }
	}
	catch ( SQLException e )
	{
	  throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
	}

	return tplProdAssetTypeMovEntities;
  }

  /**
   * Verifica se um determinado registro existe na base de dados.
   * 
   * @param tplProdAssetTypeEntity___ Entidade contendo o identificador do
   *          registro que será verificada a existência.
   * @return Indicador de existência do registro (true/false).
   *  
   */
  public boolean exists( TplProdAssetTypeMovEntity tplProdAssetTypeEntity_ )
  {
	boolean exists = true;

	try
	{
	  this.find( tplProdAssetTypeEntity_ );
	}
	catch ( NoRowsReturnedException e )
	{
	  exists = false;
	}

	return exists;
  }

  public boolean existsActive( TplProdAssetTypeEntity tplProdAssetTypeEntity_ )
  {
	boolean exists = true;

	try
	{
	  TplProdAssetTypeEntity prodAssetTypeEntity = ( TplProdAssetTypeEntity ) this.find( tplProdAssetTypeEntity_ );
	  if ( !TplProdAssetTypeEntity.C_REC_STAT_CODE_ACTIVE.equals( prodAssetTypeEntity.getData().getRecStatCode() ) )
	  {
		exists = false;
	  }
	}
	catch ( NoRowsReturnedException exception )
	{
	  exists = false;
	}

	return exists;
  }

  /**
   * Realiza o carregamento dos registros cadastrados na tabela de Current para
   * ser utilizado em outras transa- ções
   */
  public DataSet loadDomain()
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	ResultSet resultSet = null;
	ResultSetDataSet rsds = null;
	StringBuffer query = new StringBuffer();

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "SELECT " );
	  query.append( C_PROD_ASSET_TYPE_CODE + " , " );
	  query.append( C_PROD_ASSET_TYPE_TEXT );
	  query.append( " FROM " );
	  query.append( C_TPL_ASSET_TYPE_MOV + " " );
	  query.append( " WHERE " );
	  query.append( C_REC_STAT_CODE + " <> ?" );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  int count = 1;

	  preparedStatement.setString( count++,
						   TplProdAssetTypeEntity.C_REC_STAT_CODE_INACTIVE );

	  resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());


	  rsds = new ResultSetDataSet( resultSet );
	  resultSet.close();
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
	return rsds;
  }



}
