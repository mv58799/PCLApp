/*
 * Created on Mar 23, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplProdSubFamlPrvtEntity;
import com.citibank.ods.entity.pl.TplProdSubFamlPrvtHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdSubFamlPrvtHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdSubFamlPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class OracleTplProdSubFamlPrvtHistDAO extends
    BaseOracleTplProdSubFamlPrvtDAO implements TplProdSubFamlPrvtHistDAO
{
  /*
   * Campos específicos da tabela
   */
  private static final String C_PROD_SUB_FAML_REF_DATE = "PROD_SUB_FAML_REF_DATE";

  private static final String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private static final String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private static final String C_REC_STAT_CODE = "REC_STAT_CODE";

  /*
   * Nome da tabela
   */
  private static final String C_TABLE_NAME = C_PL_SCHEMA
                                             + "TPL_PROD_SUB_FAML_PRVT_HIST";

  /**
   * Realiza a consulta dos registros de sub-família de produtos na base de
   * histórico
   */
  public DataSet list( BigInteger prodSubFamlCode_, String prodSubFamlName_,
                      String prodSubFamlText_, Date m_prodSubFamlRefDate_ )
  {
    return null;
  }

  /**
   * Insere um registro de sub-família de produtos no histórico
   */
  public TplProdSubFamlPrvtHistEntity insert(
                                             TplProdSubFamlPrvtHistEntity subFamilyPrvtHistEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TABLE_NAME + " (" );
      query.append( C_PROD_SUB_FAML_CODE + ", " );
      query.append( C_PROD_SUB_FAML_REF_DATE + ", " );
      query.append( C_PROD_SUB_FAML_NAME + ", " );
      query.append( C_PROD_SUB_FAML_TEXT + ", " );
      query.append( C_PROD_FAML_CODE + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( subFamilyPrvtHistEntity_.getData().getProdSubFamlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           subFamilyPrvtHistEntity_.getData().getProdSubFamlCode().longValue() );
      } 
      preparedStatement.setTimestamp(
                              count++,
                              new Timestamp(
                                             ( ( TplProdSubFamlPrvtHistEntityVO ) subFamilyPrvtHistEntity_.getData() ).getProdSubFamlRefDate().getTime() ) );

      if ( subFamilyPrvtHistEntity_.getData().getProdSubFamlName() != null )
      {
        preparedStatement.setString(
                             count++,
                             subFamilyPrvtHistEntity_.getData().getProdSubFamlName() );
      } else {
    	  preparedStatement.setString( count++, null);  
      }

      if ( subFamilyPrvtHistEntity_.getData().getProdSubFamlText() != null )
      {
        preparedStatement.setString(
                             count++,
                             subFamilyPrvtHistEntity_.getData().getProdSubFamlText() );
      }else {
    	  preparedStatement.setString( count++, null);  
      }

      if ( subFamilyPrvtHistEntity_.getData().getProdFamlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           subFamilyPrvtHistEntity_.getData().getProdFamlCode().longValue() );
      }

      preparedStatement.setTimestamp(
                              count++,
                              new Timestamp(
                                             subFamilyPrvtHistEntity_.getData().getLastUpdDate().getTime() ) );

      if ( subFamilyPrvtHistEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             subFamilyPrvtHistEntity_.getData().getLastUpdUserId() );
      }

      TplProdSubFamlPrvtHistEntityVO prodSubFamlPrvtHistEntityVO = ( TplProdSubFamlPrvtHistEntityVO ) subFamilyPrvtHistEntity_.getData();

      if ( prodSubFamlPrvtHistEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               prodSubFamlPrvtHistEntityVO.getLastAuthDate().getTime() ) );
      }

      if ( prodSubFamlPrvtHistEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++,
                             prodSubFamlPrvtHistEntityVO.getLastAuthUserId() );
      }

      if ( prodSubFamlPrvtHistEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++,
                             prodSubFamlPrvtHistEntityVO.getRecStatCode() );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());
	  

      return subFamilyPrvtHistEntity_;

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
   * Recupera um registro de sub-familia de produtos no histórico
   */
  public BaseTplProdSubFamlPrvtEntity find(
                                           BaseTplProdSubFamlPrvtEntity baseTplProductFamilyPrvtEntity_ )
  {

    return null;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTplProdSubFamlPrvtDAO#findByProdCode(java.lang.String)
   */
  public BaseTplProdSubFamlPrvtEntity findByProdCode(String prodPk) {
	// TODO Auto-generated method stub
	return null;
  }

}