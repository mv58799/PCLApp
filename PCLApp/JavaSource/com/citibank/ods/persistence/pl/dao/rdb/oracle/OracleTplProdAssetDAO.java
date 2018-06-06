/*
 * Created on 20/08/2008
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
import com.citibank.ods.entity.pl.valueobject.TplProdAssetEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author rcoelho
 */
public class OracleTplProdAssetDAO extends BaseOracleTplProdAssetDAO
	implements TplProdAssetDAO
{
  /*
   * Nome da tabela
   */
  //private static final String C_TPL_PROD_ASSET = C_PL_SCHEMA + "TPL_SUB_ASSET_CLASS";
  
  private static final String C_TPL_PROD_ASSET = C_PL_SCHEMA + "TPL_ASSET_CLASS";

  /*
   * Campos específicos da tabela
   */
  private String C_LAST_AUTH_USER_ID = "ASSET_CLASS_LAST_AUTH_USER_ID";

  private String C_LAST_AUTH_DATE = "ASSET_CLASS_LAST_AUTH_DATE";  

  protected String C_REC_STAT_TEXT = "REC_STAT_TEXT";
  
  protected String C_ASSET_TEXT = "ASSET_CLASS_TEXT";  
  

  /**
   * Este método retorna uma lista de qualificador de produto que se enquadre
   * com os critérios informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdAssetDAO#list(java.math.BigInteger,
   *      java.lang.String)
   */
  public DataSet list( BigInteger prodAssetCode_, String prodAssetText_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	ResultSet resultSet = null;
	ResultSetDataSet rsds = null;
	StringBuffer query = new StringBuffer();

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "SELECT  " );
	  query.append(C_PROD_ASSET_CODE + ", " );	  
	  query.append(C_ASSET_TEXT + ", " );	  
	  query.append(C_LAST_UPD_USER_ID + ", " );
	  query.append(C_LAST_UPD_DATE + ", " );
	  query.append(C_LAST_AUTH_DATE + ", " );
	  query.append(C_LAST_AUTH_USER_ID + ", " );
	  query.append(C_REC_STAT_CODE + ", " );
	  query.append(C_ASSET_CLASS_CUST_RPT_ORDER_NBR);
	  query.append( " FROM " );	  
	  query.append( C_TPL_PROD_ASSET);	  
	  String criteria = "";

	  criteria = criteria + C_REC_STAT_CODE + " != '"
				 + BaseTplProdAssetEntity.C_REC_STAT_CODE_INACTIVE
				 + "' AND ";

	  if ( prodAssetCode_ != null && prodAssetCode_.longValue() != 0 )
	  {
		criteria = criteria + C_PROD_ASSET_CODE + " = ? AND ";
	  }
	  if ( prodAssetText_ != null && !prodAssetText_.equals( "" ) )
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
	  if ( prodAssetText_ != null && !prodAssetText_.equals( "" ) )
	  {
		preparedStatement.setString( count++, "%" + prodAssetText_.toUpperCase() + "%" );
	  }

	  preparedStatement.replaceParametersInQuery(query.toString());
	  resultSet = preparedStatement.executeQuery();
	  

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

	/*String[] codeColumn = { C_REC_STAT_CODE };
	String[] nameColumn = { C_REC_STAT_TEXT };
	rsds.outerJoin( ODSConstraintDecoder.decodeRecStatus(), codeColumn,
					codeColumn, nameColumn );*/

	return rsds;
  }

  /**
   * Este método insere um novo registro de qualificador de produto
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdAssetDAO#insert(com.citibank.ods.entity.pl.TplProdAssetEntity)
   */
  public TplProdAssetEntity insert(
									  TplProdAssetEntity tplProdAssetEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	StringBuffer query = new StringBuffer();

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "INSERT INTO " + C_TPL_PROD_ASSET + " (" );
	  query.append( C_PROD_ASSET_CODE + ", " );
	  query.append( C_PROD_ASSET_TEXT + ", " );
	  query.append( C_ASSET_CLASS_CUST_RPT_ORDER_NBR + ", " );
	  query.append( C_LAST_UPD_DATE + ", " );
	  query.append( C_LAST_UPD_USER_ID + ", " );
	  query.append( C_REC_STAT_CODE +", ");
	  query.append( C_LAST_AUTH_DATE + ", " );
	  query.append( C_LAST_AUTH_USER_ID);
	  query.append( ") VALUES ( " );
	  query.append( "?, ?, ?, ?, ?, ?, ?, ? )" );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  int count = 1;

	  preparedStatement.setLong(count++,
						   tplProdAssetEntity_.getData().getProdAssetCode().longValue() );
	  
	  preparedStatement.setString( count++,
							 tplProdAssetEntity_.getData().getProdAssetText() );
	  
	  preparedStatement.setLong (count++,
		tplProdAssetEntity_.getData().getAssetClassCustRptOrderNbr().longValue());				  
	  
	  preparedStatement.setTimestamp(count++,
								new Timestamp(
											   tplProdAssetEntity_.getData().getLastUpdDate().getTime() ) );
	  
      preparedStatement.setString(count++,
							 tplProdAssetEntity_.getData().getLastUpdUserId() );
	  
	  TplProdAssetEntityVO tplProdAssetEntityVO = ( TplProdAssetEntityVO ) tplProdAssetEntity_.getData();

	  
	  preparedStatement.setString( count++, 
	  								tplProdAssetEntityVO.getRecStatCode() );
			
	  
	  
	  preparedStatement.setTimestamp(	count++,
								new Timestamp(
											   tplProdAssetEntityVO.getLastAuthDate().getTime() ) );
	  
	  preparedStatement.setString( count++,
							        tplProdAssetEntityVO.getLastAuthUserId() );
	  
	  preparedStatement.replaceParametersInQuery(query.toString());
	  preparedStatement.executeUpdate();
	  

	  return tplProdAssetEntity_;
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
  public void update( TplProdAssetEntity tplProdAssetEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	StringBuffer query = new StringBuffer();

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( "UPDATE " + C_TPL_PROD_ASSET );
	  query.append( " SET " );
	  query.append( C_ASSET_TEXT + " = ?, " );
	  query.append( C_ASSET_CLASS_CUST_RPT_ORDER_NBR + " = ?, " );
	  query.append( C_LAST_UPD_DATE + " = ?, " );
	  query.append( C_LAST_UPD_USER_ID + " = ?, " );
	  query.append( C_LAST_AUTH_DATE + " = ?, " );
	  query.append( C_LAST_AUTH_USER_ID + " = ?, " );
	  query.append( C_REC_STAT_CODE + " = ? " );	  
	  query.append( " WHERE " );
	  query.append( C_PROD_ASSET_CODE + "= ? " );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  int count = 1;

	  preparedStatement.setString( count++,
							 tplProdAssetEntity_.getData().getProdAssetText() );
							 
	  						 
	  if(tplProdAssetEntity_.getData().getAssetClassCustRptOrderNbr() != null){
	  	preparedStatement.setLong(count++, tplProdAssetEntity_.getData().getAssetClassCustRptOrderNbr().longValue());	  	
	  }
	  else{
	  	preparedStatement.setString(count++,null);
	  }

	  preparedStatement.setTimestamp(count++,
								new Timestamp(
								tplProdAssetEntity_.getData().getLastUpdDate().getTime() ) );
	  
	  preparedStatement.setString(
							 count++,
							 tplProdAssetEntity_.getData().getLastUpdUserId() );
	  

	  TplProdAssetEntityVO tplProdAssetEntityVO = ( TplProdAssetEntityVO ) tplProdAssetEntity_.getData();

	  preparedStatement.setTimestamp(
								count++,
								new Timestamp(
											   tplProdAssetEntityVO.getLastAuthDate().getTime() ) );	  
	  
	  preparedStatement.setString( count++,
							 tplProdAssetEntityVO.getLastAuthUserId() );
	  

	  preparedStatement.setString( count++, tplProdAssetEntityVO.getRecStatCode() );	  
	  
	  preparedStatement.setLong(count++,
						   tplProdAssetEntity_.getData().getProdAssetCode().longValue() );
	  
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
   * @see com.citibank.ods.persistence.pl.dao.TplProdAssetDAO#delete(com.citibank.ods.entity.pl.TplProdAssetEntity)
   */
  public void delete( TplProdAssetEntity tplProdAssetEntity_ )
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	StringBuffer query = new StringBuffer();

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
	  query.append( " UPDATE " + C_TPL_PROD_ASSET );
	  query.append( " SET " );
	  query.append( C_REC_STAT_CODE + "= ?" );
	  //query.append( " WHERE " + C_PROD_SUB_ASSET_TEXT + " = ? " );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

	  preparedStatement.setString(
						   1,
						   ( ( TplProdAssetEntityVO ) tplProdAssetEntity_.getData() ).getRecStatCode() );

	  preparedStatement.setLong(
						 2,
						 tplProdAssetEntity_.getData().getProdAssetCode().longValue() );

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
	BaseTplProdAssetEntity tplProdSubClasseEntity = null;

	try
	{
	  connection = OracleODSDAOFactory.getConnection();	  
	  
	  query.append( "SELECT  " );	  
	  query.append(C_PROD_ASSET_CODE + ", " );	  
	  query.append(C_ASSET_TEXT + ", " );	  
	  query.append(C_LAST_UPD_USER_ID + ", " );
	  query.append(C_LAST_UPD_DATE + ", " );
	  query.append(C_LAST_AUTH_DATE + ", " );
	  query.append(C_LAST_AUTH_USER_ID + ", " );
	  query.append(C_REC_STAT_CODE + ", " );
	  query.append(C_ASSET_CLASS_CUST_RPT_ORDER_NBR);	  
	  query.append( " FROM " );	  
	  query.append(C_TPL_PROD_ASSET + " ");
	  query.append(" Where ");
	  query.append(C_PROD_ASSET_CODE + " = ?");


	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

	  preparedStatement.setLong(
						 1,
						 baseTplProdAssetEntity_.getData().getProdAssetCode().longValue() );

	  preparedStatement.replaceParametersInQuery(query.toString());
	  resultSet = preparedStatement.executeQuery();
	  

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
		tplProdSubClasseEntity = ( BaseTplProdAssetEntity ) tplProdAssetEntities.get( 0 );
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
	TplProdAssetEntity tplProdAssetEntity;
	Timestamp timestamp;
	Date date;
	ArrayList tplProdAssetEntities = new ArrayList();

	try
	{
	  while ( resultSet_.next() )
	  {
		tplProdAssetEntity = new TplProdAssetEntity();

		tplProdAssetEntity.getData().setProdAssetCode(
														 new BigInteger(
																		 resultSet_.getString( C_PROD_ASSET_CODE ) ) );
		tplProdAssetEntity.getData().setProdAssetText(
														 resultSet_.getString( C_PROD_ASSET_TEXT ) );
		timestamp = resultSet_.getTimestamp( C_LAST_UPD_DATE );
		date = new Date( timestamp.getTime() );
		tplProdAssetEntity.getData().setLastUpdDate( date );
		
		tplProdAssetEntity.getData().setLastUpdUserId(resultSet_.getString( C_LAST_UPD_USER_ID ) );
		
		// Casting para a atribuicao das colunas especificas
		TplProdAssetEntityVO tplProdAssetEntityVO = ( TplProdAssetEntityVO ) tplProdAssetEntity.getData();
		
		timestamp = resultSet_.getTimestamp( C_LAST_AUTH_DATE );
		date = new Date( timestamp.getTime() );
		tplProdAssetEntityVO.setLastAuthDate( date);
		
		tplProdAssetEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );
		tplProdAssetEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );
		tplProdAssetEntityVO.setProdAssetCode( new BigInteger(resultSet_.getString( C_PROD_ASSET_CODE )) );
		
		if(resultSet_.getString( C_ASSET_CLASS_CUST_RPT_ORDER_NBR ) != null){
			tplProdAssetEntityVO.setAssetClassCustRptOrderNbr(new BigInteger(
			                       resultSet_.getString( C_ASSET_CLASS_CUST_RPT_ORDER_NBR )));			
		}
		else{
			tplProdAssetEntityVO.setAssetClassCustRptOrderNbr(null);
		}
		

		tplProdAssetEntities.add( tplProdAssetEntity );
	  }
	}
	catch ( SQLException e )
	{
	  throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
	}

	return tplProdAssetEntities;
  }

  /**
   * Verifica se um determinado registro existe na base de dados.
   * 
   * @param tplProdAssetEntity___ Entidade contendo o identificador do
   *          registro que será verificada a existência.
   * @return Indicador de existência do registro (true/false).
   *  
   */
  public boolean exists( TplProdAssetEntity tplProdAssetEntity_ )
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
	  BaseTplProdAssetEntity tpp = this.find( tplProdAssetEntity_ );
	  TplProdAssetEntity prodAssetEntity = ( TplProdAssetEntity ) tpp;
	  tplProdAssetEntity_ = prodAssetEntity;
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
	  query.append( C_PROD_ASSET_CODE + ", " );
	  query.append( C_ASSET_TEXT);
	  query.append( " FROM " );
	  query.append( C_TPL_PROD_ASSET + " " );
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
