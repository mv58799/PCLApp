package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.pl.TplRelationEgHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplRelationEgHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplRelationEgHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * Implementação Oracle para DAO da tabela TPL_RELATION_EGHist
 * @author leonardo.nakada
 * @date 15/04/2007
 */
public class OracleTplRelationEgHistDAO extends BaseOracleTplRelationEgDAO
    implements TplRelationEgHistDAO
{
  private static final String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private static final String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private static final String C_REC_STAT_CODE = "REC_STAT_CODE";

  private static final String C_RELTN_EG_REF_DATE = "RELTN_EG_REF_DATE";

  private static final String C_TABLE_NAME = C_PL_SCHEMA
                                             + "TPL_RELATION_EG_HIST";

  //Número do ER
  private static final String C_ER_NBR = "ER_NBR";

  /**
   * Inseri uma nova linha na tabela TPL_RELATION_EG com os dados da entidade
   * correspondente passada como parametro
   * @param tplRelationEgEntity__
   * @throws UnexpectedException
   * @author leonardo.nakada
   * @date 15/04/2007
   */
  public TplRelationEgHistEntity insert(
                                        TplRelationEgHistEntity tplRelationEgEntity_ )
                                                                                      throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer sqlQuery = new StringBuffer();

    sqlQuery.append( "INSERT INTO " + C_TABLE_NAME + "( " );
    sqlQuery.append( C_RELTN_NBR + "," );
    sqlQuery.append( C_ER_NBR + ", " );
    sqlQuery.append( C_RELTN_EG_REF_DATE + "," );
    sqlQuery.append( C_EG_NBR + "," );
    sqlQuery.append( C_LAST_UPD_DATE + "," );
    sqlQuery.append( C_LAST_UPD_USER_ID + "," );
    sqlQuery.append( C_LAST_AUTH_DATE + "," );
    sqlQuery.append( C_LAST_AUTH_USER_ID + "," );
    sqlQuery.append( C_REC_STAT_CODE );
    sqlQuery.append( ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)" );
    try
    {
      connection = OracleODSDAOFactory.getConnection();

      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

      int count = 1;

      TplRelationEgHistEntityVO tplRelationEgEntityVO = ( TplRelationEgHistEntityVO ) tplRelationEgEntity_.getData();

      // RELTN_NBR
      if ( tplRelationEgEntityVO.getReltnNbr() != null )
      {
        preparedStatement.setLong( count++,
                           tplRelationEgEntityVO.getReltnNbr().longValue() );
      }
      else
      {
        preparedStatement.setInt( count++, 0 );
      }
      //ER_NBR
      if ( tplRelationEgEntityVO.getErNbr() != null )
      {
        preparedStatement.setString( count++, tplRelationEgEntityVO.getErNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      //  REF_DATE
      Timestamp tsReltnEgRefDate = new Timestamp(
                                                  tplRelationEgEntityVO.getReltnEgRefDate().getTime() );
      preparedStatement.setTimestamp( count++, tsReltnEgRefDate );

      // EG_NBR
      preparedStatement.setString( count++, tplRelationEgEntityVO.getEgNbr() );

      // LAST_UPD_DATE
      Timestamp tsLastUpdDate = new Timestamp(
                                               tplRelationEgEntityVO.getLastUpdDate().getTime() );
      preparedStatement.setTimestamp( count++, tsLastUpdDate );

      // LAST_UPD_USER_ID
      preparedStatement.setString( count++, tplRelationEgEntityVO.getLastUpdUserId() );

      // LAST_AUTH_DATE
      Timestamp tsLastAuthDate = new Timestamp(
                                                tplRelationEgEntityVO.getLastAuthDate().getTime() );
      preparedStatement.setTimestamp( count++, tsLastAuthDate );

      // LAST_AUTH_USER_ID
      preparedStatement.setString( count++, tplRelationEgEntityVO.getLastAuthUserId() );

      // REC_STAT_CODE
      preparedStatement.setString( count++, tplRelationEgEntityVO.getRecStatCode() );

	  preparedStatement.replaceParametersInQuery(sqlQuery.toString());
      preparedStatement.execute();
	  

      return tplRelationEgEntity_;
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