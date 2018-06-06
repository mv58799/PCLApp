package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplProdRiskCatPrvtEntity;
import com.citibank.ods.persistence.pl.dao.TplAssetClassOnesrcDao;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class OracleTplAssetClassOnesrcDao extends BaseOracleTplAssetClassOnesrcDao implements TplAssetClassOnesrcDao{

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
	      query.append( " select prod_onesrc_asset_class_code, " );
	      query.append( "        prod_onesrc_asset_class_text " );
	      query.append( " from PL.TPL_ASSET_CLASS_ONESRC " );
	      query.append( " order by prod_onesrc_asset_class_text " );

	      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

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

	public BaseTplProdRiskCatPrvtEntity find(BaseTplProdRiskCatPrvtEntity baseTplProdRiskCatPrvtEntity_) {
		return null;
	}
	

}
