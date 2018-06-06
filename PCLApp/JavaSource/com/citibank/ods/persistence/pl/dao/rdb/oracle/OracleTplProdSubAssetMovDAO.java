/*
 * Created on 20/08/2008
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
import com.citibank.ods.entity.pl.BaseTplProdSubAssetEntity;
import com.citibank.ods.entity.pl.TplProdSubAssetEntity;
import com.citibank.ods.entity.pl.TplProdSubAssetMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdSubAssetEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdSubAssetMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdSubAssetMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class OracleTplProdSubAssetMovDAO extends BaseOracleTplProdSubAssetDAO
	implements TplProdSubAssetMovDAO
{
  /*
   * Nome da tabela
   */
  private static final String C_TPL_SUB_ASSET_CLASS_MOV = C_PL_SCHEMA + "TPL_SUB_ASSET_CLASS_MOV";

  /*
   * Campos específicos da tabela
   */
  private static final String C_LAST_AUTH_USER_ID = "SUB_ASSET_LAST_AUTH_USER_ID";

  private static final String C_LAST_AUTH_DATE = "SUB_ASSET_LAST_AUTH_DATE";

  private static final String C_REC_STAT_CODE = "SUB_ASSET_STAT_CODE";

  protected static final String C_REC_STAT_TEXT = "REC_STAT_TEXT";
  
  protected static final String C_OPERN_CODE = "SUB_ASSET_OPERN_CODE";
  
  protected static final String C_SUB_ASSET_CLASS_RPT_ORDER_NBR = "SUB_ASSET_CLASS_RPT_ORDER_NBR";

  /**
   * Este método retorna uma lista de qualificador de produto que se enquadre
   * com os critérios informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdSubAssetDAO#list(java.math.BigInteger,
   *      java.lang.String)
   */  
  public DataSet list( BigInteger prodSubAssetCode_, String prodQlfyText_, String lastUpdUserId )
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
	  query.append( C_PROD_SUB_ASSET_CODE + ", " );
	  query.append( C_PROD_ASSET_CODE + ", " );
	  query.append( C_PROD_SUB_ASSET_TEXT + ", " );
	  query.append( C_LAST_UPD_USER_ID + ", " );
	  query.append( C_LAST_UPD_DATE + ", " );
	  query.append( C_LAST_AUTH_DATE + ", " );
	  query.append( C_LAST_AUTH_USER_ID + ", " );
	  query.append( C_REC_STAT_CODE );
	  query.append( " FROM " );
	  query.append( C_TPL_SUB_ASSET_CLASS_MOV );

	  String criteria = "";

	  criteria = criteria + C_REC_STAT_CODE + " != '"
				 + BaseTplProdSubAssetEntity.C_REC_STAT_CODE_INACTIVE
				 + "' AND ";

	  if ( prodSubAssetCode_ != null && prodSubAssetCode_.longValue() != 0 )
	  {
		criteria = criteria + C_PROD_SUB_ASSET_CODE + " = ? AND ";
	  }
	  if ( prodQlfyText_ != null && !prodQlfyText_.equals( "" ) )
	  {
		criteria = criteria + "UPPER(\"" + C_PROD_SUB_ASSET_TEXT + "\") LIKE ? AND ";
	  }
	  if ( criteria.length() > 0 )
	  {
		criteria = criteria.substring( 0, criteria.length() - 5 );
		query.append( "	WHERE " + criteria + " ORDER BY " + C_PROD_SUB_ASSET_TEXT
					  + " ASC " + " , " + C_PROD_SUB_ASSET_CODE );
	  }

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  int count = 1;

	  if ( prodSubAssetCode_ != null && prodSubAssetCode_.longValue() != 0 )
	  {
		preparedStatement.setLong( count++, prodSubAssetCode_.longValue() );
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
   * @see com.citibank.ods.persistence.pl.dao.TplProdSubAssetDAO#insert(com.citibank.ods.entity.pl.TplProdSubAssetEntity)
   */
  public TplProdSubAssetMovEntity insert(
  							TplProdSubAssetMovEntity tplProdSubAssetMovEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	StringBuffer query = new StringBuffer();

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "INSERT INTO " + C_TPL_SUB_ASSET_CLASS_MOV + " (" );
	  query.append( C_PROD_SUB_ASSET_CODE + ",  " );
	  query.append( C_PROD_SUB_ASSET_TEXT + ", " );
	  query.append( C_LAST_UPD_DATE + ", " );
	  query.append( C_LAST_UPD_USER_ID + ", " );
	  query.append( C_LAST_AUTH_DATE + ", " );
	  query.append( C_LAST_AUTH_USER_ID + ", " );
	  query.append( C_REC_STAT_CODE +", ");
	  query.append( C_PROD_ASSET_CODE + ", ");
	  query.append( C_OPERN_CODE + ", ");
	  query.append( C_SUB_ASSET_CLASS_RPT_ORDER_NBR);
	  query.append( ") VALUES ( " );
	  
	  if((( TplProdSubAssetMovEntityVO )tplProdSubAssetMovEntity_.getData()).getOpernCode().equals(TplProdSubAssetMovEntity.C_OPERN_CODE_UPDATE)
	    || (( TplProdSubAssetMovEntityVO )tplProdSubAssetMovEntity_.getData()).getOpernCode().equals(TplProdSubAssetMovEntity.C_OPERN_CODE_DELETE)
	  ){
		query.append( "?, ?, ?, ?, ?, ?, ?, ?, ?,? )");
	  }	
	  else{
		query.append( "PL.SQ_SUB_ASSET_CLASS_CODE.Nextval, ?, ?, ?, ?, ?, ?, ?, ? , ?)" );
	  }
	  	

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  int count = 1;

	  if((( TplProdSubAssetMovEntityVO )tplProdSubAssetMovEntity_.getData()).getOpernCode().equals(TplProdSubAssetMovEntity.C_OPERN_CODE_UPDATE)
		|| (( TplProdSubAssetMovEntityVO )tplProdSubAssetMovEntity_.getData()).getOpernCode().equals(TplProdSubAssetMovEntity.C_OPERN_CODE_DELETE)
	  )
	  {
	  	preparedStatement.setLong(count++,
	  					   tplProdSubAssetMovEntity_.getData().getProdSubAssetCode().longValue() );
	  }

	  preparedStatement.setString( count++,
							 tplProdSubAssetMovEntity_.getData().getProdSubAssetText() );
	  

	  preparedStatement.setTimestamp(count++,
								new Timestamp(
											   tplProdSubAssetMovEntity_.getData().getLastUpdDate().getTime() ) );	  

	  preparedStatement.setString(count++,
							 tplProdSubAssetMovEntity_.getData().getLastUpdUserId() );
	  

	  TplProdSubAssetMovEntityVO tplProdSubAssetMovEntityVO = ( TplProdSubAssetMovEntityVO ) tplProdSubAssetMovEntity_.getData();
	  	  
	  preparedStatement.setString( count++, null );
	  preparedStatement.setString( count++, null );
	  preparedStatement.setString( count++, "A" );
	  
	  preparedStatement.setLong(count++,
							 tplProdSubAssetMovEntity_.getData().getProdAssetCode().longValue() );
	  
	  
	  preparedStatement.setString(count++,
		                    tplProdSubAssetMovEntityVO.getOpernCode());
					   
      preparedStatement.setLong(count++,tplProdSubAssetMovEntity_.getData().getSubAssetClassRptOrderNbr().longValue());					   
		
	  preparedStatement.replaceParametersInQuery(query.toString());
	  preparedStatement.executeUpdate();	  

	  return tplProdSubAssetMovEntity_;
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
   * @see com.citibank.ods.persistence.pl.dao.TplProdSubAssetDAO#update(com.citibank.ods.entity.pl.TplProdSubAssetEntity)
   */
  public void update( TplProdSubAssetMovEntity tplProdSubAssetMovEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	StringBuffer query = new StringBuffer();

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "UPDATE " + C_TPL_SUB_ASSET_CLASS_MOV );
	  query.append( " SET " );
	  query.append( C_PROD_SUB_ASSET_TEXT + " = ?, " );
	  query.append( C_LAST_UPD_DATE + " = ?, " );
	  query.append( C_LAST_UPD_USER_ID + " = ?, " );
	  query.append( C_PROD_ASSET_CODE + " = ?, " );
	  query.append( C_SUB_ASSET_CLASS_RPT_ORDER_NBR + "= ? " );
	  query.append( " WHERE " );
	  query.append( C_PROD_SUB_ASSET_CODE + "= ? " );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  int count = 1;

	  preparedStatement.setString( count++,
							 tplProdSubAssetMovEntity_.getData().getProdSubAssetText() );
	  

	  preparedStatement.setTimestamp(count++,
								new Timestamp(tplProdSubAssetMovEntity_.getData().getLastUpdDate().getTime() ) );	  

	  
	  preparedStatement.setString(count++,
							 tplProdSubAssetMovEntity_.getData().getLastUpdUserId() );
	  
	  preparedStatement.setLong(count++,
						   tplProdSubAssetMovEntity_.getData().getProdAssetCode().longValue() );
	  	  
	  
	  if(tplProdSubAssetMovEntity_.getData().getSubAssetClassRptOrderNbr()!=null){
		preparedStatement.setLong(
								 count++,
									   tplProdSubAssetMovEntity_.getData().getSubAssetClassRptOrderNbr().longValue());
	  }
	  else{
		preparedStatement.setString(count++,null);
	  }
	  
	  preparedStatement.setLong(count++,
								 tplProdSubAssetMovEntity_.getData().getProdSubAssetCode().longValue() );	  
	  
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
   * @see com.citibank.ods.persistence.pl.dao.TplProdSubAssetDAO#delete(com.citibank.ods.entity.pl.TplProdSubAssetEntity)
   */
  public void delete( TplProdSubAssetMovEntity tplProdSubAssetEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	StringBuffer query = new StringBuffer();

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( " DELETE " + C_TPL_SUB_ASSET_CLASS_MOV );
	  query.append( " WHERE " + C_PROD_SUB_ASSET_CODE + " = ? " );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  
	  preparedStatement.setLong(
						 1,
						 tplProdSubAssetEntity_.getData().getProdSubAssetCode().longValue() );
						 
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
   * @see com.citibank.ods.persistence.pl.dao.BaseTplProdSubAssetDAO#find(com.citibank.ods.entity.pl.BaseTplProdSubAssetEntity)
   */
  public BaseTplProdSubAssetEntity find(
										BaseTplProdSubAssetEntity baseTplProdSubAssetEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	ResultSet resultSet = null;
	StringBuffer query = new StringBuffer();

	ArrayList tplProdSubAssetEntities;
	BaseTplProdSubAssetEntity tplProdSubClasseEntity = null;

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "SELECT " );
	  query.append( C_PROD_SUB_ASSET_CODE + ", " );
	  query.append( C_PROD_SUB_ASSET_TEXT + ", " );
	  query.append( C_LAST_UPD_DATE + ", " );
	  query.append( C_LAST_UPD_USER_ID + ", " );
	  query.append( C_LAST_AUTH_DATE + ", " );
	  query.append( C_LAST_AUTH_USER_ID + ", " );
	  query.append( C_REC_STAT_CODE + ", " );
	  query.append( C_PROD_ASSET_CODE + ", ");
	  query.append( C_OPERN_CODE + ", " );
	  query.append( C_SUB_ASSET_CLASS_RPT_ORDER_NBR);
	  query.append( " FROM " );
	  query.append( C_TPL_SUB_ASSET_CLASS_MOV);
	  query.append( " WHERE " );
	  query.append( C_PROD_SUB_ASSET_CODE + " = ?" );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

	  preparedStatement.setLong(
						 1,
						 baseTplProdSubAssetEntity_.getData().getProdSubAssetCode().longValue() );

	  resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

	  tplProdSubAssetEntities = instantiateFromResultSet( resultSet );

	  if ( tplProdSubAssetEntities.size() == 0 )
	  {
		throw new NoRowsReturnedException();
	  }
	  else if ( tplProdSubAssetEntities.size() > 1 )
	  {
		throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
	  }
	  else
	  {
		tplProdSubClasseEntity = ( BaseTplProdSubAssetEntity ) tplProdSubAssetEntities.get( 0 );
	  }

	  return tplProdSubClasseEntity;
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
	TplProdSubAssetMovEntity tplProdSubAssetMovEntity;
	Timestamp timestamp;
	Date date;
	ArrayList tplProdSubAssetMovEntities = new ArrayList();

	try
	{
	  while ( resultSet_.next() )
	  {
		tplProdSubAssetMovEntity = new TplProdSubAssetMovEntity();

		tplProdSubAssetMovEntity.getData().setProdSubAssetCode(
														 new BigInteger(
																		 resultSet_.getString( C_PROD_SUB_ASSET_CODE ) ) );
		tplProdSubAssetMovEntity.getData().setProdSubAssetText(
														 resultSet_.getString( C_PROD_SUB_ASSET_TEXT ) );
		timestamp = resultSet_.getTimestamp(C_LAST_UPD_DATE);
				date = new Date(timestamp.getTime());
				
		tplProdSubAssetMovEntity.getData().setLastUpdDate(date);
		tplProdSubAssetMovEntity.getData().setLastUpdUserId(resultSet_.getString(C_LAST_UPD_USER_ID));
		// Casting para a atribuicao das colunas especificas
		TplProdSubAssetMovEntityVO tplProdSubAssetMovEntityVO = ( TplProdSubAssetMovEntityVO ) tplProdSubAssetMovEntity.getData();
		tplProdSubAssetMovEntityVO.setLastAuthDate(	resultSet_.getDate(C_LAST_AUTH_DATE));
		tplProdSubAssetMovEntityVO.setLastAuthUserId( resultSet_.getString(C_LAST_AUTH_USER_ID));
		tplProdSubAssetMovEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );
		tplProdSubAssetMovEntityVO.setOpernCode( resultSet_.getString( C_OPERN_CODE));
		tplProdSubAssetMovEntity.getData().setProdAssetCode(new BigInteger(
															resultSet_.getString( C_PROD_ASSET_CODE ) ) );
		
		if(resultSet_.getString(C_SUB_ASSET_CLASS_RPT_ORDER_NBR)!= null){
			tplProdSubAssetMovEntityVO.setSubAssetClassRptOrderNbr(new BigInteger(
																			  resultSet_.getString(C_SUB_ASSET_CLASS_RPT_ORDER_NBR) ) );			
		}
		else{
			tplProdSubAssetMovEntityVO.setSubAssetClassRptOrderNbr(null);
		}
		
		tplProdSubAssetMovEntities.add( tplProdSubAssetMovEntity );
	  }
	}
	catch ( SQLException e )
	{
	  throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
	}

	return tplProdSubAssetMovEntities;
  }

  /**
   * Verifica se um determinado registro existe na base de dados.
   * 
   * @param tplProdSubAssetEntity___ Entidade contendo o identificador do
   *          registro que será verificada a existência.
   * @return Indicador de existência do registro (true/false).
   *  
   */
  public boolean exists( TplProdSubAssetMovEntity tplProdSubAssetEntity_ )
  {
	boolean exists = true;

	try
	{
	  this.find( tplProdSubAssetEntity_ );
	}
	catch ( NoRowsReturnedException e )
	{
	  exists = false;
	}

	return exists;
  }

  public boolean existsActive( TplProdSubAssetEntity tplProdSubAssetEntity_ )
  {
	boolean exists = true;

	try
	{
	  TplProdSubAssetEntity prodSubAssetEntity = ( TplProdSubAssetEntity ) this.find( tplProdSubAssetEntity_ );
	  if ( !TplProdSubAssetEntity.C_REC_STAT_CODE_ACTIVE.equals( prodSubAssetEntity.getData().getRecStatCode() ) )
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
	  query.append( C_PROD_SUB_ASSET_CODE + " , " );
	  query.append( C_PROD_SUB_ASSET_TEXT );
	  query.append( " FROM " );
	  query.append( C_TPL_SUB_ASSET_CLASS_MOV + " " );
	  query.append( " WHERE " );
	  query.append( C_REC_STAT_CODE + " <> ?" );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  int count = 1;

	  preparedStatement.setString( count++,
						   TplProdSubAssetEntity.C_REC_STAT_CODE_INACTIVE );

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
