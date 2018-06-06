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
import com.citibank.ods.entity.pl.BaseTplProdAssetTypeEntity;
import com.citibank.ods.entity.pl.TplProdAssetTypeEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdAssetTypeEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetTypeDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class OracleTplProdAssetTypeDAO extends BaseOracleTplProdAssetTypeDAO implements TplProdAssetTypeDAO {
	
	/*
	 * Campos específicos da tabela
	 */
	private String C_LAST_AUTH_USER_ID = "ASSET_TYPE_LAST_AUTH_USER_ID";

	private String C_LAST_AUTH_DATE = "ASSET_TYPE_LAST_AUTH_DATE";

	private String C_ASSET_TYPE_STAT_CODE = "ASSET_TYPE_STAT_CODE";
	
	private static final String C_ASSET_CLASS_STAT_CODE = "ASSET_CLASS_STAT_CODE";
	
	private static final String C_SUB_ASSET_STAT_CODE = "SUB_ASSET_STAT_CODE";
	
	private static final String C_ASSET_TYPE_CUST_RPT_ORDER_NBR = "ASSET_TYPE_CUST_RPT_ORDER_NBR";

	protected String C_REC_STAT_TEXT = "REC_STAT_TEXT";
	
	protected String C_OPERN_CODE = "ASSET_TYPE_OPERN_CODE"; 

	
	/**
	 * Este método retorna uma lista de qualificador de produto que se enquadre
	 * com os critérios informados
	 * 
	 * @see com.citibank.ods.persistence.pl.dao.TplProdAssetTypeDAO#list(java.math.BigInteger,
	 *      java.lang.String)
	 */
	public DataSet list(BigInteger prodAssetTypeCode_, String prodQlfyText_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetDataSet rsds = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("SELECT  ");
			query.append("a." + C_PROD_ASSET_TYPE_CODE + ", ");
			query.append("a." + C_PROD_ASSET_TYPE_TEXT + ", ");
			query.append("a." + C_LAST_UPD_USER_ID + ", ");
			query.append("a." + C_LAST_UPD_DATE + ", ");
			query.append("a." + C_LAST_AUTH_DATE + ", ");
			query.append("a." + C_LAST_AUTH_USER_ID + ", ");
			query.append("a." + C_ASSET_TYPE_STAT_CODE + ", ");
			query.append("b." + C_SUB_ASSET_CODE + ", ");
			query.append("b." + C_SUB_ASSET_TEXT + ", ");
			query.append("c." + C_ASSET_CODE + ", ");
			query.append("c." + C_ASSET_TEXT + ", ");
			query.append("a." + C_ASSET_TYPE_CUST_RPT_ORDER_NBR);
			query.append(" FROM ");
			query.append(C_TPL_ASSET_TYPE + " a, ");
			query.append(C_TPL_SUB_ASSET_CLASS + " b, ");
			query.append(C_TPL_ASSET_CLASS + " c ");
			query.append(" WHERE ");
			query.append(
				" a."
					+ C_PROD_SUB_ASSET_CODE
					+ " = b."
					+ C_PROD_SUB_ASSET_CODE
					+ " AND ");
			query.append(" b." + C_ASSET_CODE + " = c." + C_ASSET_CODE);

			String criteria = "";

			criteria =
				criteria
					+ "a."
					+ C_ASSET_TYPE_STAT_CODE
					+ " != '"
					+ BaseTplProdAssetTypeEntity.C_REC_STAT_CODE_INACTIVE
					+ "' AND ";

			if (prodAssetTypeCode_ != null
				&& prodAssetTypeCode_.longValue() != 0) {
				criteria = criteria + C_PROD_ASSET_TYPE_CODE + " = ? AND ";
			}
			if (prodQlfyText_ != null && !prodQlfyText_.equals("")) {
				criteria =
					criteria
						+ "UPPER(\""
						+ C_PROD_ASSET_TYPE_TEXT
						+ "\") LIKE ? AND ";
			}
			if (criteria.length() > 0) {
				criteria = criteria.substring(0, criteria.length() - 5);
				query.append(
					"	AND "
						+ criteria
						+ " ORDER BY "
						+ C_PROD_ASSET_TYPE_TEXT
						+ " ASC "
						+ " , "
						+ C_PROD_ASSET_TYPE_CODE);
			}

			preparedStatement =
				new CitiStatement(
					connection.prepareStatement(query.toString()));
			int count = 1;

			if (prodAssetTypeCode_ != null
				&& prodAssetTypeCode_.longValue() != 0) {
				preparedStatement.setLong(
					count++,
					prodAssetTypeCode_.longValue());
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
		
		return rsds;
	}

	/**
	 * Este método insere um novo registro de qualificador de produto
	 * 
	 * @see com.citibank.ods.persistence.pl.dao.TplProdAssetTypeDAO#insert(com.citibank.ods.entity.pl.TplProdAssetTypeEntity)
	 */
	public TplProdAssetTypeEntity insert(TplProdAssetTypeEntity tplProdAssetTypeEntity_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("INSERT INTO " + C_TPL_ASSET_TYPE + " (");
			query.append(C_PROD_ASSET_TYPE_CODE + ", ");
			query.append(C_PROD_ASSET_TYPE_TEXT + ", ");
			query.append(C_LAST_UPD_DATE + ", ");
			query.append(C_LAST_UPD_USER_ID + ", ");
			query.append(C_LAST_AUTH_DATE + ", ");
			query.append(C_LAST_AUTH_USER_ID + ", ");
			query.append(C_ASSET_TYPE_STAT_CODE + ", ");
			query.append(C_PROD_SUB_ASSET_CODE + ", ");
			query.append(C_ASSET_TYPE_CUST_RPT_ORDER_NBR);
			query.append(") VALUES ( ");
			query.append("?, ?, ?, ?, ?, ?, ?, ?, ? )");

			preparedStatement =
				new CitiStatement(
					connection.prepareStatement(query.toString()));
			int count = 1;

			if (tplProdAssetTypeEntity_.getData().getProdAssetTypeCode()
				!= null) {
				preparedStatement.setLong(
					count++,
					tplProdAssetTypeEntity_
						.getData()
						.getProdAssetTypeCode()
						.longValue());
			}

			if (tplProdAssetTypeEntity_.getData().getProdAssetTypeText()
				!= null) {
				preparedStatement.setString(
					count++,
					tplProdAssetTypeEntity_.getData().getProdAssetTypeText());
			}

			if (tplProdAssetTypeEntity_.getData().getLastUpdDate() != null) {
				preparedStatement.setTimestamp(
					count++,
					new Timestamp(
						tplProdAssetTypeEntity_
							.getData()
							.getLastUpdDate()
							.getTime()));
			}

			if (tplProdAssetTypeEntity_.getData().getLastUpdUserId() != null) {
				preparedStatement.setString(
					count++,
					tplProdAssetTypeEntity_.getData().getLastUpdUserId());
			}

			TplProdAssetTypeEntityVO tplProdAssetTypeEntityVO =
				(TplProdAssetTypeEntityVO) tplProdAssetTypeEntity_.getData();

			if (tplProdAssetTypeEntityVO.getLastAuthDate() != null) {
				preparedStatement.setTimestamp(
					count++,
					new Timestamp(
						tplProdAssetTypeEntityVO.getLastAuthDate().getTime()));
			}

			if (tplProdAssetTypeEntityVO.getLastAuthUserId() != null) {
				preparedStatement.setString(
					count++,
					tplProdAssetTypeEntityVO.getLastAuthUserId());
			}

			if (tplProdAssetTypeEntityVO.getRecStatCode() != null) {
				preparedStatement.setString(
					count++,
					tplProdAssetTypeEntityVO.getRecStatCode());
			}

			if (tplProdAssetTypeEntity_.getData().getProdSubAssetCode() != null) {
				preparedStatement.setLong(
					count++,
					tplProdAssetTypeEntity_
						.getData()
						.getProdSubAssetCode()
						.longValue());
			}
			
		   if(tplProdAssetTypeEntity_.getData().getAssetTypeCustRptOrderNbr() != null){
	    
			 preparedStatement.setLong(
									   count++,
									   tplProdAssetTypeEntity_.getData().getAssetTypeCustRptOrderNbr().longValue() );
	  	
			}
		   else{
			 preparedStatement.setString(count++,null);
			}

			preparedStatement.replaceParametersInQuery(query.toString());
			preparedStatement.executeUpdate();
			

			return tplProdAssetTypeEntity_;
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
	 * @see com.citibank.ods.persistence.pl.dao.TplProdAssetTypeDAO#update(com.citibank.ods.entity.pl.TplProdAssetTypeEntity)
	 */
	public void update(TplProdAssetTypeEntity tplProdAssetTypeEntity_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("UPDATE " + C_TPL_ASSET_TYPE);
			query.append(" SET ");
			query.append(C_PROD_ASSET_TYPE_TEXT + " = ?, ");
			query.append(C_LAST_UPD_DATE + " = ?, ");
			query.append(C_LAST_UPD_USER_ID + " = ?, ");
			query.append(C_LAST_AUTH_DATE + " = ?, ");
			query.append(C_LAST_AUTH_USER_ID + " = ?, ");
			query.append(C_ASSET_TYPE_STAT_CODE + " = ?,");
			query.append(C_PROD_SUB_ASSET_CODE + " = ?,");
			query.append(C_ASSET_TYPE_CUST_RPT_ORDER_NBR + " = ? " );
			query.append(" WHERE ");
			query.append(C_PROD_ASSET_TYPE_CODE + "= ? ");

			preparedStatement =
				new CitiStatement(
					connection.prepareStatement(query.toString()));
			int count = 1;
            
			if(tplProdAssetTypeEntity_.getData().getProdAssetTypeText()!= null){
				
			preparedStatement.setString(count++,
					tplProdAssetTypeEntity_.getData().getProdAssetTypeText());
			}		
			
			preparedStatement.setTimestamp(count++,
					new Timestamp(tplProdAssetTypeEntity_.getData().getLastUpdDate().getTime()));
			
			preparedStatement.setString(count++,
					tplProdAssetTypeEntity_.getData().getLastUpdUserId());
			
			TplProdAssetTypeEntityVO tplProdAssetTypeEntityVO =
				(TplProdAssetTypeEntityVO) tplProdAssetTypeEntity_.getData();

			preparedStatement.setTimestamp(count++,
					new Timestamp(tplProdAssetTypeEntityVO.getLastAuthDate().getTime()));
			

			preparedStatement.setString(count++,tplProdAssetTypeEntityVO.getLastAuthUserId());
			

			preparedStatement.setString(count++,tplProdAssetTypeEntityVO.getRecStatCode());
			
			preparedStatement.setLong(count++,
								tplProdAssetTypeEntity_.getData().getProdSubAssetCode().longValue());
			
			if(tplProdAssetTypeEntity_.getData().getAssetTypeCustRptOrderNbr()!= null){
				preparedStatement.setLong(count++,
						tplProdAssetTypeEntity_.getData().getAssetTypeCustRptOrderNbr().longValue());
			}
			else{
				preparedStatement.setString(count++,null);
			}
			
			preparedStatement.setLong(count++,
					tplProdAssetTypeEntity_.getData().getProdAssetTypeCode().longValue());
			
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
	 * @see com.citibank.ods.persistence.pl.dao.TplProdAssetTypeDAO#delete(com.citibank.ods.entity.pl.TplProdAssetTypeEntity)
	 */
	public void delete(TplProdAssetTypeEntity tplProdAssetTypeEntity_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append(" UPDATE " + C_TPL_ASSET_TYPE);
			query.append(" SET ");
			query.append(C_ASSET_TYPE_STAT_CODE + "= ?");
			query.append(" WHERE " + C_PROD_ASSET_TYPE_TEXT + " = ? ");

			preparedStatement =
				new CitiStatement(
					connection.prepareStatement(query.toString()));

			preparedStatement.setString(
				1,
				((TplProdAssetTypeEntityVO) tplProdAssetTypeEntity_.getData())
					.getRecStatCode());

			preparedStatement.setLong(
				2,
				tplProdAssetTypeEntity_
					.getData()
					.getProdAssetTypeCode()
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
	 * @see com.citibank.ods.persistence.pl.dao.BaseTplProdAssetTypeDAO#find(com.citibank.ods.entity.pl.BaseTplProdAssetTypeEntity)
	 */
	public BaseTplProdAssetTypeEntity find(BaseTplProdAssetTypeEntity baseTplProdAssetTypeEntity_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuffer query = new StringBuffer();

		ArrayList tplProdAssetTypeEntities;
		BaseTplProdAssetTypeEntity tplProdSubClasseEntity = null;

		try {
			connection = OracleODSDAOFactory.getConnection();

			query.append("SELECT  ");
			query.append("a." + C_PROD_ASSET_TYPE_CODE + ", ");
			query.append("a." + C_PROD_ASSET_TYPE_TEXT + ", ");
			query.append("a." + C_LAST_UPD_DATE + ", ");
			query.append("a." + C_LAST_UPD_USER_ID + ", ");			
			query.append("a." + C_LAST_AUTH_DATE + ", ");
			query.append("a." + C_LAST_AUTH_USER_ID + ", ");
			query.append("a." + C_ASSET_TYPE_STAT_CODE + ", ");
			query.append("b." + C_SUB_ASSET_CODE + ", ");
			query.append("b." + C_SUB_ASSET_TEXT + ", ");
			query.append("c." + C_ASSET_CODE + ", ");
			query.append("c." + C_ASSET_TEXT + ", ");
			query.append("a." + C_ASSET_TYPE_CUST_RPT_ORDER_NBR);
			query.append(" FROM ");
			query.append(C_TPL_ASSET_TYPE + " a, ");
			query.append(C_TPL_SUB_ASSET_CLASS + " b, ");
			query.append(C_TPL_ASSET_CLASS + " c ");
			query.append(" WHERE ");
			query.append(
				" a."
					+ C_PROD_SUB_ASSET_CODE
					+ " = b."
					+ C_PROD_SUB_ASSET_CODE
					+ " AND ");
			query.append(" b." + C_ASSET_CODE + " = c." + C_ASSET_CODE);
			query.append(" AND a." + C_PROD_ASSET_TYPE_CODE + " = ?");

			preparedStatement =
				new CitiStatement(
					connection.prepareStatement(query.toString()));

			preparedStatement.setLong(
				1,
				baseTplProdAssetTypeEntity_
					.getData()
					.getProdAssetTypeCode()
					.longValue());

			resultSet = preparedStatement.executeQuery();
			preparedStatement.replaceParametersInQuery(query.toString());

			tplProdAssetTypeEntities = instantiateFromResultSet(resultSet);

			if (tplProdAssetTypeEntities.size() == 0) {
				throw new NoRowsReturnedException();
			} else if (tplProdAssetTypeEntities.size() > 1) {
				throw new UnexpectedException(C_ERROR_TOO_MANY_ROWS_RETURNED);
			} else {
				tplProdSubClasseEntity =
					(BaseTplProdAssetTypeEntity) tplProdAssetTypeEntities.get(
						0);
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
		TplProdAssetTypeEntity tplProdAssetTypeEntity;
		Timestamp timestamp;
		Date date;
		ArrayList tplProdAssetTypeEntities = new ArrayList();

		try {
			while (resultSet_.next()) {
				tplProdAssetTypeEntity = new TplProdAssetTypeEntity();

				tplProdAssetTypeEntity.getData().setProdAssetTypeCode(
					new BigInteger(
						resultSet_.getString(C_PROD_ASSET_TYPE_CODE)));
				
				tplProdAssetTypeEntity.getData().setProdAssetTypeText(
					resultSet_.getString(C_PROD_ASSET_TYPE_TEXT));
				
				timestamp = resultSet_.getTimestamp(C_LAST_UPD_DATE);
				date = new Date(timestamp.getTime());
				
				tplProdAssetTypeEntity.getData().setLastUpdDate(date);
				
				tplProdAssetTypeEntity.getData().setLastUpdUserId(
					resultSet_.getString(C_LAST_UPD_USER_ID));
					
				if(resultSet_.getString(C_ASSET_TYPE_CUST_RPT_ORDER_NBR) != null){
					tplProdAssetTypeEntity.getData().setAssetTypeCustRptOrderNbr(
					  new BigInteger(resultSet_.getString(C_ASSET_TYPE_CUST_RPT_ORDER_NBR)));						
				}else{
					tplProdAssetTypeEntity.getData().setAssetTypeCustRptOrderNbr(null);
				}
				
				
				// Casting para a atribuicao das colunas especificas
				TplProdAssetTypeEntityVO tplProdAssetTypeEntityVO =
					(TplProdAssetTypeEntityVO) tplProdAssetTypeEntity.getData();
				
				timestamp = resultSet_.getTimestamp(C_LAST_AUTH_DATE);
				date = new Date(timestamp.getTime());
				
				tplProdAssetTypeEntityVO.setLastAuthDate(date);					
				
				tplProdAssetTypeEntityVO.setLastAuthUserId(
					resultSet_.getString(C_LAST_AUTH_USER_ID));
				
				tplProdAssetTypeEntityVO.setRecStatCode(
					resultSet_.getString(C_ASSET_TYPE_STAT_CODE));
				
				tplProdAssetTypeEntityVO.setProdSubAssetCode(
					new BigInteger(
						resultSet_.getString(C_PROD_SUB_ASSET_CODE)));
				
				tplProdAssetTypeEntityVO.setProdAssetCode(
					new BigInteger(resultSet_.getString(C_ASSET_CODE)));

				tplProdAssetTypeEntities.add(tplProdAssetTypeEntity);
			}
		} catch (SQLException e) {
			throw new UnexpectedException(
				e.getErrorCode(),
				C_ERROR_INSTANTIATE_FROM_RESULT_SET,
				e);
		}

		return tplProdAssetTypeEntities;
	}

	/**
	 * Verifica se um determinado registro existe na base de dados.
	 * 
	 * @param tplProdAssetTypeEntity___ Entidade contendo o identificador do
	 *          registro que será verificada a existência.
	 * @return Indicador de existência do registro (true/false).
	 *  
	 */
	public boolean exists(TplProdAssetTypeEntity tplProdAssetTypeEntity_) {
		boolean exists = true;

		try {
			this.find(tplProdAssetTypeEntity_);
		} catch (NoRowsReturnedException e) {
			exists = false;
		}

		return exists;
	}

	public boolean existsActive(TplProdAssetTypeEntity tplProdAssetTypeEntity_) {
		boolean exists = true;

		try {
			BaseTplProdAssetTypeEntity tpp = this.find(tplProdAssetTypeEntity_);
			TplProdAssetTypeEntity prodAssetTypeEntity =
				(TplProdAssetTypeEntity) tpp;
			tplProdAssetTypeEntity_ = prodAssetTypeEntity;
			if (!TplProdAssetTypeEntity
				.C_REC_STAT_CODE_ACTIVE
				.equals(prodAssetTypeEntity.getData().getRecStatCode())) {
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
			query.append(C_ASSET_CODE + ", ");
			query.append(C_ASSET_TEXT);
			query.append(" FROM ");
			query.append(C_TPL_ASSET_CLASS + " ");
			query.append(" WHERE ");
			query.append(C_ASSET_CLASS_STAT_CODE + " <> ?");

			preparedStatement =
				new CitiStatement(
					connection.prepareStatement(query.toString()));
			int count = 1;

			preparedStatement.setString(
				count++,
				TplProdAssetTypeEntity.C_REC_STAT_CODE_INACTIVE);

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

	/**
		 * Realiza o carregamento dos registros cadastrados na tabela de Current para
		 * ser utilizado em outras transa- ções
		 */
	public DataSet loadSubAsset() {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetDataSet rsds = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("SELECT ");
			query.append(C_SUB_ASSET_CODE + ", ");
			query.append(C_SUB_ASSET_TEXT);
			query.append(" FROM ");
			query.append(C_TPL_SUB_ASSET_CLASS + " ");
			query.append(" WHERE ");
			query.append(C_SUB_ASSET_STAT_CODE + " <> ?");

			preparedStatement =
				new CitiStatement(
					connection.prepareStatement(query.toString()));
			int count = 1;

			preparedStatement.setString(
				count++,
				TplProdAssetTypeEntity.C_REC_STAT_CODE_INACTIVE);

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

	public BigInteger loadAssetBySubAsset(BigInteger subAssetCode_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("SELECT ");
			query.append(C_ASSET_CODE);
			query.append(" FROM ");
			query.append(C_TPL_SUB_ASSET_CLASS + " ");
			query.append(" WHERE ");
			query.append(C_SUB_ASSET_CODE + " = ?");

			preparedStatement =
				new CitiStatement(
					connection.prepareStatement(query.toString()));
			int count = 1;

			preparedStatement.setLong(count++, subAssetCode_.longValue());

			preparedStatement.replaceParametersInQuery(query.toString());
			resultSet = preparedStatement.executeQuery();

			BigInteger assetCodeReturn = null;
			while (resultSet.next()) {
				assetCodeReturn =
					new BigInteger(resultSet.getString(C_ASSET_CODE));
			}

			resultSet.close();
			return assetCodeReturn;

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

	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.TplProdAssetTypeDAO#loadFullDomain()
	 */
	public DataSet loadFullDomain() {

		//Terminar consulta
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetDataSet rsds = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("SELECT ");
			query.append("tp.asset_type_code, ");
			query.append(
				"tp.asset_type_text || ' -> ' || sb.sub_asset_class_text || ' -> ' || ass.asset_class_text as Asset_Text ");
			query.append(" FROM ");
			query.append(" PL.Tpl_Asset_Type tp, ");
			query.append(" pl.tpl_sub_asset_class sb, ");
			query.append(" pl.tpl_asset_class ass ");
			query.append(" where ");
			query.append(" tp.sub_asset_class_code = sb.sub_asset_class_code ");
			query.append(" and sb.asset_class_code = ass.asset_class_code ");
			query.append(" and tp.asset_type_stat_code <> 'I' ");

			preparedStatement =
				new CitiStatement(
					connection.prepareStatement(query.toString()));

			preparedStatement.replaceParametersInQuery(query.toString());
			resultSet = preparedStatement.executeQuery();

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
	 * @see com.citibank.ods.persistence.pl.dao.TplProdAssetTypeDAO#existsAssetTypeByForeignKey(java.math.BigInteger)
	 */
	public boolean existsAssetTypeByForeignKey(BigInteger subAssetCode_) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("SELECT COUNT(*) FROM ");
			query.append(C_TPL_ASSET_TYPE);
			query.append(" WHERE ");
			query.append(C_ASSET_TYPE_STAT_CODE + " !=  ? ");
			query.append(" AND " + C_SUB_ASSET_CODE + " = ? ");

			preparedStatement =
				new CitiStatement(
					connection.prepareStatement(query.toString()));
			int count = 1;

			preparedStatement.setString(
				count++,
				TplProdAssetTypeEntity.C_REC_STAT_CODE_INACTIVE);
			preparedStatement.setLong(count++, subAssetCode_.longValue());

			preparedStatement.replaceParametersInQuery(query.toString());
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				if (resultSet.getInt(1) != 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
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

}

