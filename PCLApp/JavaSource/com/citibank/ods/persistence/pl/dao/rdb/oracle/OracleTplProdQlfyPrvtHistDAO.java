/*
 * Created on 19/03/2007
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
import com.citibank.ods.entity.pl.BaseTplProdQlfyPrvtEntity;
import com.citibank.ods.entity.pl.TplProdQlfyPrvtHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdQlfyPrvtHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author angelica.almeida
 *  
 */
public class OracleTplProdQlfyPrvtHistDAO extends BaseOracleTplProdQlfyPrvtDAO
    implements TplProdQlfyPrvtHistDAO

{
  /*
   * Constante do nome da coluna da tabela de data de referência da tabela de
   * histórico
   */
  private String C_PROD_QLFY_REF_DATE = "PROD_QLFY_REF_DATE";

  /*
   * Constante que identifica o nome da tabel de histórico
   */
  private static final String C_TPL_PROD_QLFY_PRVT_HIST = C_PL_SCHEMA
                                                          + "TPL_PROD_QLFY_PRVT_HIST";

  /*
   * Campos específicos da tabela
   */
  private String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private String C_REC_STAT_CODE = "REC_STAT_CODE";

  protected String C_REC_STAT_TEXT = "REC_STAT_TEXT";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTplProdQlfyPrvtDAO#find(com.citibank.ods.entity.pl.BaseTplProdQlfyPrvtEntity)
   */
  public BaseTplProdQlfyPrvtEntity find(
                                        BaseTplProdQlfyPrvtEntity baseTplProdQlfyPrvtEntity_ )
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtHistDAO#list(java.math.BigInteger,
   *      java.lang.String, java.util.Date)
   */
  public DataSet list( BigInteger prodQlfyCode_, String prodQlfyText_,
                      Date prodQlfyRefDate_ )
  {

    return null;
  }

  /**
   * Insere um novo registro na tabela de histórico da tabela de Qualificador de
   * Produto
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtHistDAO#insert(com.citibank.ods.entity.pl.TplProdQlfyPrvtHistEntity)
   */
  public TplProdQlfyPrvtHistEntity insert(
                                          TplProdQlfyPrvtHistEntity tplProdQlfyPrvtHistEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_PROD_QLFY_PRVT_HIST + " (" );
      query.append( C_PROD_QLFY_CODE + ", " );
      query.append( C_PROD_QLFY_REF_DATE + ", " );
      query.append( C_PROD_QLFY_TEXT + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplProdQlfyPrvtHistEntity_.getData().getProdQlfyCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProdQlfyPrvtHistEntity_.getData().getProdQlfyCode().longValue() );
      }

      if ( ( ( TplProdQlfyPrvtHistEntityVO ) tplProdQlfyPrvtHistEntity_.getData() ).getProdQlfyRefDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplProdQlfyPrvtHistEntityVO ) tplProdQlfyPrvtHistEntity_.getData() ).getProdQlfyRefDate().getTime() ) );
      }

      if ( tplProdQlfyPrvtHistEntity_.getData().getProdQlfyText() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdQlfyPrvtHistEntity_.getData().getProdQlfyText() );
      }

      if ( tplProdQlfyPrvtHistEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProdQlfyPrvtHistEntity_.getData().getLastUpdDate().getTime() ) );
      }

      if ( tplProdQlfyPrvtHistEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdQlfyPrvtHistEntity_.getData().getLastUpdUserId() );
      }

      if ( ( ( TplProdQlfyPrvtHistEntityVO ) tplProdQlfyPrvtHistEntity_.getData() ).getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplProdQlfyPrvtHistEntityVO ) tplProdQlfyPrvtHistEntity_.getData() ).getLastAuthDate().getTime() ) );
      }

      if ( ( ( TplProdQlfyPrvtHistEntityVO ) tplProdQlfyPrvtHistEntity_.getData() ).getLastAuthUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplProdQlfyPrvtHistEntityVO ) tplProdQlfyPrvtHistEntity_.getData() ).getLastAuthUserId() );
      }

      if ( ( ( TplProdQlfyPrvtHistEntityVO ) tplProdQlfyPrvtHistEntity_.getData() ).getRecStatCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplProdQlfyPrvtHistEntityVO ) tplProdQlfyPrvtHistEntity_.getData() ).getRecStatCode() );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());
	  

      return tplProdQlfyPrvtHistEntity_;

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

}