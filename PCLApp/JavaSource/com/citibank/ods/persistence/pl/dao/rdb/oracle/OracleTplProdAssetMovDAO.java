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
import com.citibank.ods.entity.pl.BaseTplProdAssetEntity;
import com.citibank.ods.entity.pl.TplProdAssetEntity;
import com.citibank.ods.entity.pl.TplProdAssetMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdAssetEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdAssetMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class OracleTplProdAssetMovDAO extends BaseOracleTplProdAssetDAO
	implements TplProdAssetMovDAO
{
  /*
   * Nome da tabela
   */
  private static final String C_TPL_PROD_ASSET_MOV = C_PL_SCHEMA + "TPL_ASSET_CLASS_MOV";

  /*
   * Campos específicos da tabela
   */
  private String C_LAST_AUTH_USER_ID = "ASSET_CLASS_LAST_AUTH_USER_ID";

  private String C_LAST_AUTH_DATE = "ASSET_CLASS_LAST_AUTH_DATE";

  private String C_REC_STAT_CODE = "ASSET_CLASS_STAT_CODE";

  protected String C_REC_STAT_TEXT = "REC_STAT_TEXT";
  
  protected String C_OPERN_CODE = "ASSET_CLASS_OPERN_CODE";
  
  protected String C_ASSET_CLASS_CUST_RPT_ORDER_NBR = "ASSET_CLASS_CUST_RPT_ORDER_NBR";

  /**
   * Este método retorna uma lista de qualificador de produto que se enquadre
   * com os critérios informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdAssetDAO#list(java.math.BigInteger,
   *      java.lang.String)
   */  
  public DataSet list( BigInteger prodAssetCode_, String prodQlfyText_, String lastUpdUserId )
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
	  query.append( C_PROD_ASSET_CODE + ", " );
	  query.append( C_PROD_ASSET_CODE + ", " );
	  query.append( C_PROD_ASSET_TEXT + ", " );
	  query.append( C_LAST_UPD_USER_ID + ", " );
	  query.append( C_LAST_UPD_DATE + ", " );
	  query.append( C_LAST_AUTH_DATE + ", " );
	  query.append( C_LAST_AUTH_USER_ID + ", " );
	  query.append( C_REC_STAT_CODE );
	  query.append( " FROM " );
	  query.append( C_TPL_PROD_ASSET_MOV );

	  String criteria = "";

	  criteria = criteria + C_REC_STAT_CODE + " != '"
				 + BaseTplProdAssetEntity.C_REC_STAT_CODE_INACTIVE
				 + "' AND ";

	  if ( prodAssetCode_ != null && prodAssetCode_.longValue() != 0 )
	  {
		criteria = criteria + C_PROD_ASSET_CODE + " = ? AND ";
	  }
	  if ( prodQlfyText_ != null && !prodQlfyText_.equals( "" ) )
	  {
		criteria = criteria + "UPPER(\"" + C_PROD_ASSET_TEXT + "\") LIKE ? AND ";
	  }
	  if ( criteria.length() > 0 )
	  {
		criteria = criteria.substring( 0, criteria.length() - 5 );
		query.append( "	WHERE " + criteria + " ORDER BY " + C_PROD_ASSET_TEXT
					  + " ASC " + " , " + C_PROD_ASSET_CODE );
	  }

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  int count = 1;

	  if ( prodAssetCode_ != null && prodAssetCode_.longValue() != 0 )
	  {
		preparedStatement.setLong( count++, prodAssetCode_.longValue() );
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
   * @see com.citibank.ods.persistence.pl.dao.TplProdAssetDAO#insert(com.citibank.ods.entity.pl.TplProdAssetEntity)
   */
  public TplProdAssetMovEntity insert(
  							TplProdAssetMovEntity tplProdAssetMovEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	StringBuffer query = new StringBuffer();

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "INSERT INTO " + C_TPL_PROD_ASSET_MOV + " (" );
	  query.append( C_PROD_ASSET_CODE + ",  " );
	  query.append( C_PROD_ASSET_TEXT + ", " );
	  query.append( C_LAST_UPD_DATE + ", " );
	  query.append( C_LAST_UPD_USER_ID + ", " );
	  query.append( C_LAST_AUTH_DATE + ", " );
	  query.append( C_LAST_AUTH_USER_ID + ", " );
	  query.append( C_REC_STAT_CODE +", ");	  
	  query.append( C_OPERN_CODE + ", ");
	  query.append( C_ASSET_CLASS_CUST_RPT_ORDER_NBR );
	  query.append( ") VALUES ( " );
	  
	  if((( TplProdAssetMovEntityVO )tplProdAssetMovEntity_.getData()).getOpernCode().equals(TplProdAssetMovEntity.C_OPERN_CODE_UPDATE)
	   || (( TplProdAssetMovEntityVO )tplProdAssetMovEntity_.getData()).getOpernCode().equals(TplProdAssetMovEntity.C_OPERN_CODE_DELETE) 
	  )
	  	query.append( "?, ?, ?, ?, ?, ?, ?, ? ,?)");
	  else
	  	query.append( "PL.SQ_ASSET_CLASS_CODE.Nextval, ?, ?, ?, ?, ?, ?, ?,? )" );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  int count = 1;

	  if((( TplProdAssetMovEntityVO )tplProdAssetMovEntity_.getData()).getOpernCode().equals(TplProdAssetMovEntity.C_OPERN_CODE_UPDATE)
	   || (( TplProdAssetMovEntityVO )tplProdAssetMovEntity_.getData()).getOpernCode().equals(TplProdAssetMovEntity.C_OPERN_CODE_DELETE) 
	  ){
	  
	  	preparedStatement.setLong(
	  					   count++,
	  					   tplProdAssetMovEntity_.getData().getProdAssetCode().longValue() );
	  }

	  if ( tplProdAssetMovEntity_.getData().getProdAssetText() != null )
	  {
		preparedStatement.setString( count++,
							 tplProdAssetMovEntity_.getData().getProdAssetText() );
	  }

	  if ( tplProdAssetMovEntity_.getData().getLastUpdDate() != null )
	  {
		preparedStatement.setTimestamp(
								count++,
								new Timestamp(
											   tplProdAssetMovEntity_.getData().getLastUpdDate().getTime() ) );
	  }

	  if ( tplProdAssetMovEntity_.getData().getLastUpdUserId() != null )
	  {
		preparedStatement.setString(
							 count++,
							 tplProdAssetMovEntity_.getData().getLastUpdUserId() );
	  }
	  
	  preparedStatement.setString( count++, null );
	  preparedStatement.setString( count++, null);
	  
	  preparedStatement.setString( count++, "A" );
	  	  	  
	  
	  TplProdAssetMovEntityVO tplProdAssetMovEntityVO = (TplProdAssetMovEntityVO)tplProdAssetMovEntity_.getData();
	  
	  preparedStatement.setString(
				   count++,
				   tplProdAssetMovEntityVO.getOpernCode());	
					   	
	  
	  if(tplProdAssetMovEntity_.getData().getAssetClassCustRptOrderNbr() != null){
		preparedStatement.setLong(count++,
								  tplProdAssetMovEntity_.getData().getAssetClassCustRptOrderNbr().longValue() );	  	
	  }
	  else{
		preparedStatement.setString( count++, null );
	  }
	  
	  preparedStatement.replaceParametersInQuery(query.toString());
	  preparedStatement.executeUpdate();
	  

	  return tplProdAssetMovEntity_;
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
   * @see com.citibank.ods.persistence.pl.dao.TplProdAssetDAO#update(com.citibank.ods.entity.pl.TplProdAssetEntity)
   */
  public void update( TplProdAssetMovEntity tplProdAssetMovEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	StringBuffer query = new StringBuffer();

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "UPDATE " + C_TPL_PROD_ASSET_MOV );
	  query.append( " SET " );
	  query.append( C_PROD_ASSET_TEXT + " = ?, " );
	  query.append( C_ASSET_CLASS_CUST_RPT_ORDER_NBR + " = ?, " );
	  query.append( C_LAST_UPD_DATE + " = ?, " );
	  query.append( C_LAST_UPD_USER_ID + " = ?, " );	  
	  query.append( C_OPERN_CODE + "= ? ");
	  query.append( " WHERE " );
	  query.append( C_PROD_ASSET_CODE + "= ? " );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  int count = 1;

	  preparedStatement.setString( count++,
							 tplProdAssetMovEntity_.getData().getProdAssetText() );
	  	  
	  if(tplProdAssetMovEntity_.getData().getAssetClassCustRptOrderNbr()!= null){
		preparedStatement.setLong(count++,tplProdAssetMovEntity_.getData().getAssetClassCustRptOrderNbr().longValue());	  	
	  }
	  else{
		preparedStatement.setString(count++,null);
	  }
	  
	  preparedStatement.setTimestamp(count++,
									  new Timestamp(
									  tplProdAssetMovEntity_.getData().getLastUpdDate().getTime() ) );	  

	  
	  preparedStatement.setString(count++,
							 tplProdAssetMovEntity_.getData().getLastUpdUserId() );
	  
	  preparedStatement.setString(
								 count++,
									 (( TplProdAssetMovEntityVO )tplProdAssetMovEntity_.getData()).getOpernCode());	  

	  preparedStatement.setLong(count++,
						   tplProdAssetMovEntity_.getData().getProdAssetCode().longValue() );
	  

	  preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

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
   * @see com.citibank.ods.persistence.pl.dao.TplProdAssetDAO#delete(com.citibank.ods.entity.pl.TplProdAssetEntity)
   */
  public void delete( TplProdAssetMovEntity tplProdAssetEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	StringBuffer query = new StringBuffer();

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( " DELETE " + C_TPL_PROD_ASSET_MOV );
	  query.append( " WHERE " + C_PROD_ASSET_CODE + " = ? " );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  preparedStatement.setLong(1,
						 tplProdAssetEntity_.getData().getProdAssetCode().longValue() );

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
   * @see com.citibank.ods.persistence.pl.dao.BaseTplProdAssetDAO#find(com.citibank.ods.entity.pl.BaseTplProdAssetEntity)
   */
  public BaseTplProdAssetEntity find(
										BaseTplProdAssetEntity baseTplProdAssetEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	ResultSet resultSet = null;
	StringBuffer query = new StringBuffer();

	ArrayList tplProdAssetEntities;
	TplProdAssetMovEntity tplProdAssetMovEntity = null;

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "SELECT " );
	  query.append( C_PROD_ASSET_CODE + ", " );
	  query.append( C_PROD_ASSET_TEXT + ", " );
	  query.append( C_ASSET_CLASS_CUST_RPT_ORDER_NBR + ", " );
	  query.append( C_LAST_UPD_DATE + ", " );
	  query.append( C_LAST_UPD_USER_ID + ", " );
	  //query.append( C_LAST_AUTH_DATE + ", " );
	  //query.append( C_LAST_AUTH_USER_ID + ", " );
	  query.append( C_OPERN_CODE + ", " );	  
	  query.append( C_REC_STAT_CODE );
	  query.append( " FROM " );
	  query.append( C_TPL_PROD_ASSET_MOV);
	  query.append( " WHERE " );
	  query.append( C_PROD_ASSET_CODE + " = ?" );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

	  preparedStatement.setLong(
						 1,
						 baseTplProdAssetEntity_.getData().getProdAssetCode().longValue() );

	  resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

	  tplProdAssetEntities = instantiateFromResultSet( resultSet );

	  if ( tplProdAssetEntities.size() == 0 )
	  {
		throw new NoRowsReturnedException();
	  }
	  else if ( tplProdAssetEntities.size() > 1 )
	  {
		throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
	  }
	  else
	  {
		tplProdAssetMovEntity = ( TplProdAssetMovEntity ) tplProdAssetEntities.get( 0 );
	  }

	  return tplProdAssetMovEntity;
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
	TplProdAssetMovEntity tplProdAssetMovEntity;
	Timestamp timestamp;
	Date date;
	ArrayList tplProdAssetMovEntities = new ArrayList();

	try
	{
	  while ( resultSet_.next() )
	  {
		tplProdAssetMovEntity = new TplProdAssetMovEntity();

		tplProdAssetMovEntity.getData().setProdAssetCode(
														 new BigInteger(
																		 resultSet_.getString( C_PROD_ASSET_CODE ) ) );
		tplProdAssetMovEntity.getData().setProdAssetText(
														 resultSet_.getString( C_PROD_ASSET_TEXT ) );
		timestamp = resultSet_.getTimestamp( C_LAST_UPD_DATE );
		date = new Date( timestamp.getTime() );
		tplProdAssetMovEntity.getData().setLastUpdDate( date );
		tplProdAssetMovEntity.getData().setLastUpdUserId(
														  resultSet_.getString( C_LAST_UPD_USER_ID ) );
		
		tplProdAssetMovEntity.getData().setRecStatCode(resultSet_.getString( C_REC_STAT_CODE ));
		
		if(resultSet_.getString( C_ASSET_CLASS_CUST_RPT_ORDER_NBR ) != null){
			tplProdAssetMovEntity.getData().setAssetClassCustRptOrderNbr(new BigInteger(
										   resultSet_.getString( C_ASSET_CLASS_CUST_RPT_ORDER_NBR )));			
		}
		else{
			tplProdAssetMovEntity.getData().setAssetClassCustRptOrderNbr(null);
		}
		
		// Casting para a atribuicao das colunas especificas
		TplProdAssetMovEntityVO tplProdAssetMovEntityVO = ( TplProdAssetMovEntityVO ) tplProdAssetMovEntity.getData();
		tplProdAssetMovEntityVO.setOpernCode(resultSet_.getString( C_OPERN_CODE ));
		

		tplProdAssetMovEntities.add( tplProdAssetMovEntity );
	  }
	}
	catch ( SQLException e )
	{
	  throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
	}

	return tplProdAssetMovEntities;
  }

  /**
   * Verifica se um determinado registro existe na base de dados.
   * 
   * @param tplProdAssetEntity___ Entidade contendo o identificador do
   *          registro que será verificada a existência.
   * @return Indicador de existência do registro (true/false).
   *  
   */
  public boolean exists( TplProdAssetMovEntity tplProdAssetEntity_ )
  {
	boolean exists = true;

	try
	{
	  this.find( tplProdAssetEntity_ );
	}
	catch ( NoRowsReturnedException e )
	{
	  exists = false;
	}

	return exists;
  }

  public boolean existsActive( TplProdAssetEntity tplProdAssetEntity_ )
  {
	boolean exists = true;

	try
	{
	  TplProdAssetEntity prodAssetEntity = ( TplProdAssetEntity ) this.find( tplProdAssetEntity_ );
	  if ( !TplProdAssetEntity.C_REC_STAT_CODE_ACTIVE.equals( prodAssetEntity.getData().getRecStatCode() ) )
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
	  query.append( C_PROD_ASSET_CODE + " , " );
	  query.append( C_PROD_ASSET_TEXT );
	  query.append( " FROM " );
	  query.append( C_TPL_PROD_ASSET_MOV + " " );
	  query.append( " WHERE " );
	  query.append( C_REC_STAT_CODE + " <> ?" );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  int count = 1;

	  preparedStatement.setString( count++,
						   TplProdAssetEntity.C_REC_STAT_CODE_INACTIVE );

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
