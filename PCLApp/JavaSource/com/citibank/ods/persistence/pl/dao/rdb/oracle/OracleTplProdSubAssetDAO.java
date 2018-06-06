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
import com.citibank.ods.entity.pl.BaseTplProdSubAssetEntity;
import com.citibank.ods.entity.pl.TplProdSubAssetEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdSubAssetEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdSubAssetDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author rcoelho
 */
public class OracleTplProdSubAssetDAO extends BaseOracleTplProdSubAssetDAO	implements TplProdSubAssetDAO {
	/*
	 * Nome da tabela
	 */
	private static final String C_TPL_PROD_SUB_ASSET_PRVT =
		C_PL_SCHEMA + "TPL_SUB_ASSET_CLASS";

	private static final String C_TPL_PROD_ASSET =
		C_PL_SCHEMA + "TPL_ASSET_CLASS";

	/*
	 * Campos específicos da tabela
	 */
	private String C_LAST_AUTH_USER_ID = "SUB_ASSET_LAST_AUTH_USER_ID";

	private String C_LAST_AUTH_DATE = "SUB_ASSET_LAST_AUTH_DATE";

	private String C_REC_STAT_CODE = "SUB_ASSET_STAT_CODE";

	protected String C_REC_STAT_TEXT = "SUB_ASSET_STAT_TEXT";

	protected String C_ASSET_TEXT = "ASSET_CLASS_TEXT";
	
	protected String C_ASSET_CODE = "ASSET_CLASS_CODE";
	
	protected String C_SUB_ASSET_CLASS_RPT_ORDER_NBR = "SUB_ASSET_CLASS_RPT_ORDER_NBR";

	/**
	 * Este método retorna uma lista de qualificador de produto que se enquadre
	 * com os critérios informados
	 * 
	 * @see com.citibank.ods.persistence.pl.dao.TplProdSubAssetDAO#list(java.math.BigInteger,
	 *      java.lang.String)
	 */
	public DataSet list(BigInteger prodSubAssetCode_, String prodQlfyText_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetDataSet rsds = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("SELECT  ");
			query.append("a." + C_PROD_SUB_ASSET_CODE + ", ");
			query.append("b." + C_PROD_ASSET_CODE + ", ");
			query.append("b." + C_ASSET_TEXT + ", ");
			query.append("a." + C_PROD_SUB_ASSET_TEXT + ", ");
			query.append("a." + C_LAST_UPD_USER_ID + ", ");
			query.append("a." + C_LAST_UPD_DATE + ", ");
			query.append("a." + C_LAST_AUTH_DATE + ", ");
			query.append("a." + C_LAST_AUTH_USER_ID + ", ");
			query.append("a." + C_REC_STAT_CODE + ", ");
			query.append("a." + C_SUB_ASSET_CLASS_RPT_ORDER_NBR);
			query.append(" FROM ");
			query.append(C_TPL_PROD_SUB_ASSET_PRVT + " a,");
			query.append(C_TPL_PROD_ASSET + " b ");
			query.append(" WHERE ");
			query.append(
				" a." + C_PROD_ASSET_CODE + " = b." + C_PROD_ASSET_CODE);

			String criteria = "";

			criteria =
				criteria
					+ "a."
					+ C_REC_STAT_CODE
					+ " != '"
					+ BaseTplProdSubAssetEntity.C_REC_STAT_CODE_INACTIVE
					+ "' AND ";

			if (prodSubAssetCode_ != null
				&& prodSubAssetCode_.longValue() != 0) {
				criteria = criteria + C_PROD_SUB_ASSET_CODE + " = ? AND ";
			}
			if (prodQlfyText_ != null && !prodQlfyText_.equals("")) {
				criteria =
					criteria
						+ "UPPER(\""
						+ C_PROD_SUB_ASSET_TEXT
						+ "\") LIKE ? AND ";
			}
			if (criteria.length() > 0) {
				criteria = criteria.substring(0, criteria.length() - 5);
				query.append(
					"	AND "
						+ criteria
						+ " ORDER BY "
						+ C_PROD_SUB_ASSET_TEXT
						+ " ASC "
						+ " , "
						+ C_PROD_SUB_ASSET_CODE);
			}

			preparedStatement =
				new CitiStatement(
					connection.prepareStatement(query.toString()));
			int count = 1;

			if (prodSubAssetCode_ != null
				&& prodSubAssetCode_.longValue() != 0) {
				preparedStatement.setLong(
					count++,
					prodSubAssetCode_.longValue());
			}
			if (prodQlfyText_ != null && !prodQlfyText_.equals("")) {
				preparedStatement.setString(
					count++,
					"%" + prodQlfyText_.toUpperCase() + "%");
			}

			resultSet = preparedStatement.executeQuery();
			preparedStatement.replaceParametersInQuery(query.toString());

			rsds = new ResultSetDataSet(resultSet);
			resultSet.close();
		} catch (SQLException e) {
			throw new UnexpectedException(
				e.getErrorCode(),
				C_ERROR_EXECUTING_STATEMENT,
				e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}

		/*String[] codeColumn = { C_REC_STAT_CODE };
		String[] nameColumn = { C_REC_STAT_TEXT };
		rsds.outerJoin(
			ODSConstraintDecoder.decodeRecStatus(),
			codeColumn,
			codeColumn,
			nameColumn);*/

		return rsds;
	}

	/**
	 * Este método insere um novo registro de qualificador de produto
	 * 
	 * @see com.citibank.ods.persistence.pl.dao.TplProdSubAssetDAO#insert(com.citibank.ods.entity.pl.TplProdSubAssetEntity)
	 */
	public TplProdSubAssetEntity insert(TplProdSubAssetEntity tplProdSubAssetEntity_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("INSERT INTO " + C_TPL_PROD_SUB_ASSET_PRVT + " (");
			query.append(C_PROD_SUB_ASSET_CODE + ", ");
			query.append(C_PROD_SUB_ASSET_TEXT + ", ");
			query.append(C_LAST_UPD_DATE + ", ");
			query.append(C_LAST_UPD_USER_ID + ", ");
			query.append(C_LAST_AUTH_DATE + ", ");
			query.append(C_LAST_AUTH_USER_ID + ", ");
			query.append(C_REC_STAT_CODE + ", ");
			query.append(C_PROD_ASSET_CODE + ", ");
			query.append(C_SUB_ASSET_CLASS_RPT_ORDER_NBR);
			query.append(") VALUES ( ");
			query.append("?, ?, ?, ?, ?, ?, ?, ?, ? )");

			preparedStatement =
				new CitiStatement(
					connection.prepareStatement(query.toString()));
			int count = 1;

			if (tplProdSubAssetEntity_.getData().getProdSubAssetCode()!= null) {
				preparedStatement.setLong(count++,
					tplProdSubAssetEntity_.getData().getProdSubAssetCode().longValue());
			}

			if (tplProdSubAssetEntity_.getData().getProdSubAssetText()!= null) {
				preparedStatement.setString(count++,
					tplProdSubAssetEntity_.getData().getProdSubAssetText());
			}

			if (tplProdSubAssetEntity_.getData().getLastUpdDate() != null) {
				preparedStatement.setTimestamp(	count++,new Timestamp(
						tplProdSubAssetEntity_.getData().getLastUpdDate().getTime()));
			}

			if (tplProdSubAssetEntity_.getData().getLastUpdUserId() != null) {
				preparedStatement.setString(count++,
					tplProdSubAssetEntity_.getData().getLastUpdUserId());
			}

			TplProdSubAssetEntityVO tplProdSubAssetEntityVO =(TplProdSubAssetEntityVO) tplProdSubAssetEntity_.getData();

			if (tplProdSubAssetEntityVO.getLastAuthDate() != null) {
				preparedStatement.setTimestamp(	count++,new Timestamp(
						tplProdSubAssetEntityVO.getLastAuthDate().getTime()));
			}

			if (tplProdSubAssetEntityVO.getLastAuthUserId() != null) {
				preparedStatement.setString(count++,
					tplProdSubAssetEntityVO.getLastAuthUserId());
			}

			if (tplProdSubAssetEntityVO.getRecStatCode() != null) {
				preparedStatement.setString(count++,
					tplProdSubAssetEntityVO.getRecStatCode());
			}

			if (tplProdSubAssetEntity_.getData().getProdAssetCode() != null) {
				preparedStatement.setLong(count++,
					tplProdSubAssetEntity_.getData().getProdAssetCode().longValue());
			}
			
			preparedStatement.setLong(count++,
						tplProdSubAssetEntity_.getData().getSubAssetClassRptOrderNbr().longValue());

			preparedStatement.replaceParametersInQuery(query.toString());
			preparedStatement.executeUpdate();

			return tplProdSubAssetEntity_;
		} catch (Exception e) {
			throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}

	}

	/**
	 * Este método altera os dados de um qualificador de produto
	 * 
	 * @see com.citibank.ods.persistence.pl.dao.TplProdSubAssetDAO#update(com.citibank.ods.entity.pl.TplProdSubAssetEntity)
	 */
	public void update(TplProdSubAssetEntity tplProdSubAssetEntity_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("UPDATE " + C_TPL_PROD_SUB_ASSET_PRVT);
			query.append(" SET ");
			query.append(C_PROD_SUB_ASSET_TEXT + " = ?, ");
			query.append(C_LAST_UPD_DATE + " = ?, ");
			query.append(C_LAST_UPD_USER_ID + " = ?, ");
			query.append(C_LAST_AUTH_DATE + " = ?, ");
			query.append(C_LAST_AUTH_USER_ID + " = ?, ");
			query.append(C_REC_STAT_CODE + " = ?, ");
			query.append(C_PROD_ASSET_CODE + " = ?, ");
			query.append(C_SUB_ASSET_CLASS_RPT_ORDER_NBR + " = ?");
			query.append(" WHERE ");
			query.append(C_PROD_SUB_ASSET_CODE + "= ? ");

			preparedStatement =
				new CitiStatement(
					connection.prepareStatement(query.toString()));
			int count = 1;

			
			preparedStatement.setString(count++,
					tplProdSubAssetEntity_.getData().getProdSubAssetText());			

			preparedStatement.setTimestamp(count++,new Timestamp(
			        tplProdSubAssetEntity_.getData().getLastUpdDate().getTime()));
			

			preparedStatement.setString(count++,
					tplProdSubAssetEntity_.getData().getLastUpdUserId());
			

			TplProdSubAssetEntityVO tplProdSubAssetEntityVO =(TplProdSubAssetEntityVO) tplProdSubAssetEntity_.getData();

			preparedStatement.setTimestamp(count++,new Timestamp(
						tplProdSubAssetEntityVO.getLastAuthDate().getTime()));
			

			preparedStatement.setString(count++,tplProdSubAssetEntityVO.getLastAuthUserId());
			

			preparedStatement.setString(count++,
					tplProdSubAssetEntityVO.getRecStatCode());
			
			preparedStatement.setLong(count++,
								tplProdSubAssetEntity_.getData().getProdAssetCode().longValue());
			
			if (tplProdSubAssetEntityVO.getSubAssetClassRptOrderNbr() != null) {
							preparedStatement.setLong(count++,
								tplProdSubAssetEntityVO.getSubAssetClassRptOrderNbr().longValue());
			}			
			
			preparedStatement.setLong(count++,
					tplProdSubAssetEntity_.getData().getProdSubAssetCode().longValue());
			
			preparedStatement.replaceParametersInQuery(query.toString());
			preparedStatement.executeUpdate();
			

		} catch (Exception e) {
			throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}

	}

	/**
	 * Este método efetua a deleção lógica de um qualificador de produto que se
	 * enquadre nos critérios informados
	 * 
	 * @see com.citibank.ods.persistence.pl.dao.TplProdSubAssetDAO#delete(com.citibank.ods.entity.pl.TplProdSubAssetEntity)
	 */
	public void delete(TplProdSubAssetEntity tplProdSubAssetEntity_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append(" UPDATE " + C_TPL_PROD_SUB_ASSET_PRVT);
			query.append(" SET ");
			query.append(C_REC_STAT_CODE + "= ?");
			query.append(" WHERE " + C_PROD_SUB_ASSET_TEXT + " = ? ");

			preparedStatement =
				new CitiStatement(
					connection.prepareStatement(query.toString()));

			preparedStatement.setString(
				1,
				((TplProdSubAssetEntityVO) tplProdSubAssetEntity_.getData())
					.getRecStatCode());

			preparedStatement.setLong(
				2,
				tplProdSubAssetEntity_
					.getData()
					.getProdSubAssetCode()
					.longValue());

			preparedStatement.executeUpdate();
			preparedStatement.replaceParametersInQuery(query.toString());
		} catch (SQLException e) {
			throw new UnexpectedException(
				e.getErrorCode(),
				C_ERROR_EXECUTING_STATEMENT,
				e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}

	}

	/**
	 * Este método busca um qualficador de produto que se enquadre com os
	 * critérios informados
	 * 
	 * @see com.citibank.ods.persistence.pl.dao.BaseTplProdSubAssetDAO#find(com.citibank.ods.entity.pl.BaseTplProdSubAssetEntity)
	 */
	public BaseTplProdSubAssetEntity find(BaseTplProdSubAssetEntity baseTplProdSubAssetEntity_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuffer query = new StringBuffer();

		ArrayList tplProdSubAssetEntities;
		BaseTplProdSubAssetEntity tplProdSubClasseEntity = null;

		try {
			connection = OracleODSDAOFactory.getConnection();

			query.append("SELECT  ");
			query.append("a." + C_PROD_SUB_ASSET_CODE + ", ");
			query.append("b." + C_PROD_ASSET_CODE + ", ");
			query.append("b." + C_ASSET_TEXT + ", ");
			query.append("a." + C_PROD_SUB_ASSET_TEXT + ", ");
			query.append("a." + C_LAST_UPD_USER_ID + ", ");
			query.append("a." + C_LAST_UPD_DATE + ", ");
			query.append("a." + C_LAST_AUTH_DATE + ", ");
			query.append("a." + C_LAST_AUTH_USER_ID + ", ");
			query.append("a." + C_REC_STAT_CODE + ", ");
			query.append("a." + C_SUB_ASSET_CLASS_RPT_ORDER_NBR);
			query.append(" FROM ");
			query.append(C_TPL_PROD_SUB_ASSET_PRVT + " a,");
			query.append(C_TPL_PROD_ASSET + " b ");
			query.append(" WHERE ");
			query.append(
				" a." + C_PROD_ASSET_CODE + " = b." + C_PROD_ASSET_CODE);
			query.append(" AND " + C_PROD_SUB_ASSET_CODE + " = ?");

			preparedStatement =
				new CitiStatement(
					connection.prepareStatement(query.toString()));

			preparedStatement.setLong(
				1,
				baseTplProdSubAssetEntity_
					.getData()
					.getProdSubAssetCode()
					.longValue());

			resultSet = preparedStatement.executeQuery();
			preparedStatement.replaceParametersInQuery(query.toString());

			tplProdSubAssetEntities = instantiateFromResultSet(resultSet);

			if (tplProdSubAssetEntities.size() == 0) {
				throw new NoRowsReturnedException();
			} else if (tplProdSubAssetEntities.size() > 1) {
				throw new UnexpectedException(C_ERROR_TOO_MANY_ROWS_RETURNED);
			} else {
				tplProdSubClasseEntity =
					(BaseTplProdSubAssetEntity) tplProdSubAssetEntities.get(0);
			}

			return tplProdSubClasseEntity;
		} catch (SQLException e) {
			throw new UnexpectedException(
				e.getErrorCode(),
				C_ERROR_EXECUTING_STATEMENT,
				e);
		}
		 finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		 }
	}

	/**
	 * Este método cria um array de entity apartir de um result set
	 * 
	 * @param resultSet_
	 * @return ArrayList de Entity
	 */
	private ArrayList instantiateFromResultSet(ResultSet resultSet_) {
		TplProdSubAssetEntity tplProdSubAssetEntity;
		Timestamp timestamp;
		Date date;
		ArrayList tplProdSubAssetEntities = new ArrayList();

		try {
			while (resultSet_.next()) {
				tplProdSubAssetEntity = new TplProdSubAssetEntity();

				tplProdSubAssetEntity.getData().setProdSubAssetCode(new BigInteger(						
						resultSet_.getString(C_PROD_SUB_ASSET_CODE)));
				
				tplProdSubAssetEntity.getData().setProdSubAssetText(
					resultSet_.getString(C_PROD_SUB_ASSET_TEXT));
					
				timestamp = resultSet_.getTimestamp(C_LAST_UPD_DATE);
				date = new Date(timestamp.getTime());
				
				tplProdSubAssetEntity.getData().setLastUpdDate(date);
				tplProdSubAssetEntity.getData().setLastUpdUserId(
					resultSet_.getString(C_LAST_UPD_USER_ID));
					
				// Casting para a atribuicao das colunas especificas
				TplProdSubAssetEntityVO tplProdSubAssetEntityVO =
					(TplProdSubAssetEntityVO) tplProdSubAssetEntity.getData();				
				
				timestamp = resultSet_.getTimestamp(C_LAST_AUTH_DATE);
				date = new Date(timestamp.getTime());
				
				tplProdSubAssetEntityVO.setLastAuthDate(date);
				
				tplProdSubAssetEntityVO.setLastAuthUserId(
					resultSet_.getString(C_LAST_AUTH_USER_ID));
				
				tplProdSubAssetEntityVO.setRecStatCode(
					resultSet_.getString(C_REC_STAT_CODE));
				
				tplProdSubAssetEntityVO.setProdAssetCode(
					new BigInteger(resultSet_.getString(C_PROD_ASSET_CODE)));
				
				tplProdSubAssetEntityVO.setSubAssetClassRptOrderNbr(
				   new BigInteger(resultSet_.getString(C_SUB_ASSET_CLASS_RPT_ORDER_NBR)));	

				tplProdSubAssetEntities.add(tplProdSubAssetEntity);
			}
		} catch (SQLException e) {
			throw new UnexpectedException(
				e.getErrorCode(),
				C_ERROR_INSTANTIATE_FROM_RESULT_SET,
				e);
		}

		return tplProdSubAssetEntities;
	}

	/**
	 * Verifica se um determinado registro existe na base de dados.
	 * 
	 * @param tplProdSubAssetEntity___ Entidade contendo o identificador do
	 *          registro que será verificada a existência.
	 * @return Indicador de existência do registro (true/false).
	 *  
	 */
	public boolean exists(TplProdSubAssetEntity tplProdSubAssetEntity_) {
		boolean exists = true;

		try {
			this.find(tplProdSubAssetEntity_);
		} catch (NoRowsReturnedException e) {
			exists = false;
		}

		return exists;
	}

	public boolean existsActive(TplProdSubAssetEntity tplProdSubAssetEntity_) {
		boolean exists = true;

		try {
			BaseTplProdSubAssetEntity tpp = this.find(tplProdSubAssetEntity_);
			TplProdSubAssetEntity prodSubAssetEntity =
				(TplProdSubAssetEntity) tpp;
			tplProdSubAssetEntity_ = prodSubAssetEntity;
			if (!TplProdSubAssetEntity
				.C_REC_STAT_CODE_ACTIVE
				.equals(prodSubAssetEntity.getData().getRecStatCode())) {
				exists = false;
			}
		} catch (NoRowsReturnedException exception) {
			exists = false;
		}

		return exists;
	}

	/**
	 * Realiza o carregamento dos registros cadastrados na tabela de Current para
	 * ser utilizado em outras transa- ções
	 */
	public DataSet loadDomain() {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetDataSet rsds = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("SELECT ");
			query.append(C_PROD_ASSET_CODE + ", ");
			query.append(C_ASSET_TEXT);
			query.append(" FROM ");
			query.append(C_TPL_PROD_ASSET + " ");
			query.append(" WHERE ");
			query.append("ASSET_CLASS_STAT_CODE <> ?");

			preparedStatement =
				new CitiStatement(
					connection.prepareStatement(query.toString()));
			int count = 1;

			preparedStatement.setString(
				count++,
				TplProdSubAssetEntity.C_REC_STAT_CODE_INACTIVE);

			resultSet = preparedStatement.executeQuery();
			preparedStatement.replaceParametersInQuery(query.toString());

			rsds = new ResultSetDataSet(resultSet);
			resultSet.close();
		} catch (SQLException e) {
			throw new UnexpectedException(
				e.getErrorCode(),
				C_ERROR_EXECUTING_STATEMENT,
				e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return rsds;
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.TplProdSubAssetDAO#existsSubAssetByForeignKey(java.math.BigInteger)
	 */
	public boolean existsSubAssetByForeignKey(BigInteger assetCode_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuffer query = new StringBuffer();
		
		try
		{
		connection = OracleODSDAOFactory.getConnection();
		query.append( "SELECT COUNT(*) FROM " );
		query.append( C_TPL_PROD_SUB_ASSET_PRVT );
		query.append( " WHERE " );
		query.append( C_REC_STAT_CODE + " !=  ? " );

		if ( assetCode_ != null && !assetCode_.equals( "" ) )
		{
		  query.append( " AND " + C_ASSET_CODE + " = ? " );
		}

		preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
		int count = 1;

		preparedStatement.setString( count++, TplProdSubAssetEntity.C_REC_STAT_CODE_INACTIVE );

		if ( assetCode_ != null && !assetCode_.equals( "" ) )
		{
			preparedStatement.setLong( count++, assetCode_.longValue() );
		}

		preparedStatement.replaceParametersInQuery(query.toString());
		resultSet = preparedStatement.executeQuery();

		if ( resultSet.next() )
		{
		  if ( resultSet.getInt( 1 ) != 0 )
		  {
		    return true;
		  }
		  else
		  {
		    return false;
		  }
	    }
		else
		{
		  return false;
		}
	  }
	  catch ( SQLException e )
	  {
	    throw new UnexpectedException( e.getErrorCode(),
											 C_ERROR_EXECUTING_STATEMENT, e );
	  }
	  finally
	  {
	    closeStatement( preparedStatement );
	    closeConnection( connection );
	  }

	}

}
