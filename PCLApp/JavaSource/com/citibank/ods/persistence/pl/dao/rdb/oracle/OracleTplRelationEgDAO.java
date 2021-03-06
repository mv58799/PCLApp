package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.DataSetRow;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.TplRelationEgEntity;
import com.citibank.ods.entity.pl.TplRelationPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplCustomerPrvtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRelationEgEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRelationPrvtEntityVO;
import com.citibank.ods.persistence.pl.dao.TplRelationEgDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * Implementa��o Oracle para DAO da tabela TPL_RELATION_EG
 * @author leonardo.nakada
 * @date 15/04/2007
 */
public class OracleTplRelationEgDAO extends BaseOracleTplRelationEgDAO
    implements TplRelationEgDAO
{
  /*
   * Colunas da tabela
   */
  private static final String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private static final String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private static final String C_REC_STAT_CODE = "REC_STAT_CODE";

  private static final String C_REC_STAT_TEXT = "REC_STAT_TEXT";

  /*
   * Nomes das tabelas utilizadas
   */
  private static final String C_TABLE_NAME = C_PL_SCHEMA + "TPL_RELATION_EG";

  private static final String C_TB_RELATION = C_PL_SCHEMA + "TPL_RELATION_PRVT";

  private static final String C_TB_CUST = C_PL_SCHEMA + "TPL_CUSTOMER_PRVT";

  /*
   * Colunas da tabela TPL_RELATION
   */
  private static final String C_RELTN_CUST_1_NBR = "RELTN_CUST_1_NBR";

  private static final String C_RELTN_CUST_2_NBR = "RELTN_CUST_2_NBR";

  private static final String C_RELTN_CUST_3_NBR = "RELTN_CUST_3_NBR";

  private static final String C_RELTN_CUST_4_NBR = "RELTN_CUST_4_NBR";

  private static final String C_RELTN_CUST_5_NBR = "RELTN_CUST_5_NBR";

  /*
   * Colunas da tabela TPL_CUSTOMER_PRVT
   */
  private static final String C_CUST_NBR = "CUST_NBR";

  private static final String C_CUST_SHORT_NAME_TEXT = "CUST_SHORT_NAME_TEXT";

  //N�mero do ER
  private static final String C_ER_NBR = "ER_NBR";

  /**
   * Inseri uma nova linha na tabela TPL_RELATION_EG com os dados da entidade
   * correspondente passada como parametro
   * @param tplRelationEgEntity__
   * @throws UnexpectedException
   * @author leonardo.nakada
   * @date 15/04/2007
   */
  public TplRelationEgEntity insert( TplRelationEgEntity tplRelationEgEntity_ )
                                                                               throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer sqlQuery = new StringBuffer();

    sqlQuery.append( "INSERT INTO " + C_TABLE_NAME + "( " );
    sqlQuery.append( C_RELTN_NBR + "," );
    sqlQuery.append( C_ER_NBR + ", " );
    sqlQuery.append( C_EG_NBR + "," );
    sqlQuery.append( C_LAST_UPD_DATE + "," );
    sqlQuery.append( C_LAST_UPD_USER_ID + "," );
    sqlQuery.append( C_LAST_AUTH_DATE + "," );
    sqlQuery.append( C_LAST_AUTH_USER_ID + "," );
    sqlQuery.append( C_REC_STAT_CODE );
    sqlQuery.append( ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)" );
    try
    {
      connection = OracleODSDAOFactory.getConnection();

      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

      int count = 1;

      TplRelationEgEntityVO tplRelationEgEntityVO = ( TplRelationEgEntityVO ) tplRelationEgEntity_.getData();

      // RELTN_NBR
      if ( tplRelationEgEntityVO.getReltnNbr() != null )
      {
        preparedStatement.setLong( count++,
                           tplRelationEgEntityVO.getReltnNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      //ER_NBR
      preparedStatement.setString( count++, tplRelationEgEntityVO.getErNbr() );
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

  /**
   * Realiza a remo�ao dos registros que tenham este EgNbr
   */
  public void deleteRelations( String egNbr_ ) throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer sqlQuery = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      sqlQuery.append( " DELETE FROM " );
      sqlQuery.append( C_TABLE_NAME );
      sqlQuery.append( " WHERE " );
      sqlQuery.append( C_EG_NBR + " like ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

      preparedStatement.setString( 1, egNbr_ );

	  preparedStatement.replaceParametersInQuery(sqlQuery.toString());
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
   * Realiza a consulta em Lista
   */
  public DataSet list( BigInteger reltnNbr_, String egNbr_, String erNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      // Query -> Consulta
      // Adiciona � consulta o nome do Customer que est� na tabela
      // TPL_CUSTOMER_PRVT
      // a partir do valor RELTN_CUST_1_NBR que est� na tabela TPL_RELATION_PRVT
      StringBuffer nestedQueryCust1 = new StringBuffer();
      String newColumnNameCust1 = C_CUST_SHORT_NAME_TEXT + "1";
      nestedQueryCust1.append( "SELECT " );
      nestedQueryCust1.append( C_TB_RELATION + "." + C_RELTN_NBR + ", " );
      nestedQueryCust1.append( C_TB_RELATION + "." + C_RELTN_CUST_1_NBR + ", " );
      nestedQueryCust1.append( C_TB_RELATION + "." + C_RELTN_CUST_2_NBR + ", " );
      nestedQueryCust1.append( C_TB_RELATION + "." + C_RELTN_CUST_3_NBR + ", " );
      nestedQueryCust1.append( C_TB_RELATION + "." + C_RELTN_CUST_4_NBR + ", " );
      nestedQueryCust1.append( C_TB_RELATION + "." + C_RELTN_CUST_5_NBR + ", " );
      nestedQueryCust1.append( C_TB_CUST + "." + C_CUST_SHORT_NAME_TEXT + " "
                               + newColumnNameCust1 );
      nestedQueryCust1.append( " FROM " );
      nestedQueryCust1.append( C_TB_RELATION );
      nestedQueryCust1.append( " LEFT JOIN " );
      nestedQueryCust1.append( C_TB_CUST );
      nestedQueryCust1.append( " ON " );
      nestedQueryCust1.append( C_TB_RELATION + "." + C_RELTN_CUST_1_NBR + " = " );
      nestedQueryCust1.append( C_TB_CUST + "." + C_CUST_NBR );

      //  Query -> Consulta
      // Adiciona � consulta o nome do Customer que est� na tabela
      // TPL_CUSTOMER_PRVT
      // a partir do valor RELTN_CUST_2_NBR que est� na tabela TPL_RELATION_PRVT
      StringBuffer nestedQueryCust2 = new StringBuffer();
      String tbNmCust2 = "REL2";
      String newColumnNameCust2 = C_CUST_SHORT_NAME_TEXT + "2";
      nestedQueryCust2.append( "SELECT " );
      nestedQueryCust2.append( tbNmCust2 + "." + C_RELTN_NBR + ", " );
      nestedQueryCust2.append( tbNmCust2 + "." + C_RELTN_CUST_1_NBR + ", " );
      nestedQueryCust2.append( tbNmCust2 + "." + C_RELTN_CUST_2_NBR + ", " );
      nestedQueryCust2.append( tbNmCust2 + "." + C_RELTN_CUST_3_NBR + ", " );
      nestedQueryCust2.append( tbNmCust2 + "." + C_RELTN_CUST_4_NBR + ", " );
      nestedQueryCust2.append( tbNmCust2 + "." + C_RELTN_CUST_5_NBR + ", " );
      nestedQueryCust2.append( tbNmCust2 + "." + newColumnNameCust1 + ", " );
      nestedQueryCust2.append( C_TB_CUST + "." + C_CUST_SHORT_NAME_TEXT + " "
                               + newColumnNameCust2 );
      nestedQueryCust2.append( " FROM " );
      nestedQueryCust2.append( "( " );
      nestedQueryCust2.append( nestedQueryCust1 );
      nestedQueryCust2.append( ") " + tbNmCust2 );
      nestedQueryCust2.append( " LEFT JOIN " );
      nestedQueryCust2.append( C_TB_CUST );
      nestedQueryCust2.append( " ON " );
      nestedQueryCust2.append( tbNmCust2 + "." + C_RELTN_CUST_2_NBR + " = " );
      nestedQueryCust2.append( C_TB_CUST + "." + C_CUST_NBR );

      //  Query -> Consulta
      // Adiciona � consulta o nome do Customer que est� na tabela
      // TPL_CUSTOMER_PRVT
      // a partir do valor RELTN_CUST_3_NBR que est� na tabela TPL_RELATION_PRVT
      StringBuffer nestedQueryCust3 = new StringBuffer();
      String tbNmCust3 = "REL3";
      String newColumnNameCust3 = C_CUST_SHORT_NAME_TEXT + "3";
      nestedQueryCust3.append( "SELECT " );
      nestedQueryCust3.append( tbNmCust3 + "." + C_RELTN_NBR + ", " );
      nestedQueryCust3.append( tbNmCust3 + "." + C_RELTN_CUST_1_NBR + ", " );
      nestedQueryCust3.append( tbNmCust3 + "." + C_RELTN_CUST_2_NBR + ", " );
      nestedQueryCust3.append( tbNmCust3 + "." + C_RELTN_CUST_3_NBR + ", " );
      nestedQueryCust3.append( tbNmCust3 + "." + C_RELTN_CUST_4_NBR + ", " );
      nestedQueryCust3.append( tbNmCust3 + "." + C_RELTN_CUST_5_NBR + ", " );
      nestedQueryCust3.append( tbNmCust3 + "." + newColumnNameCust1 + ", " );
      nestedQueryCust3.append( tbNmCust3 + "." + newColumnNameCust2 + ", " );
      nestedQueryCust3.append( C_TB_CUST + "." + C_CUST_SHORT_NAME_TEXT + " "
                               + newColumnNameCust3 );
      nestedQueryCust3.append( " FROM " );
      nestedQueryCust3.append( "( " );
      nestedQueryCust3.append( nestedQueryCust2 );
      nestedQueryCust3.append( ") " + tbNmCust3 );
      nestedQueryCust3.append( " LEFT JOIN " );
      nestedQueryCust3.append( C_TB_CUST );
      nestedQueryCust3.append( " ON " );
      nestedQueryCust3.append( tbNmCust3 + "." + C_RELTN_CUST_3_NBR + " = " );
      nestedQueryCust3.append( C_TB_CUST + "." + C_CUST_NBR );

      String tbNmCusts = "CUSTS_RELTN";
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_TABLE_NAME + "." + C_RELTN_NBR + ", " );
      query.append( C_TABLE_NAME + "." + C_EG_NBR + ", " );
      query.append( C_TABLE_NAME + "." + C_LAST_UPD_DATE + ", " );
      query.append( C_TABLE_NAME + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( C_TABLE_NAME + "." + C_LAST_AUTH_DATE + ", " );
      query.append( C_TABLE_NAME + "." + C_LAST_AUTH_USER_ID + ", " );
      query.append( C_TABLE_NAME + "." + C_REC_STAT_CODE + ", " );
      query.append( C_TABLE_NAME + "." + C_ER_NBR + ", " );
      query.append( tbNmCusts + "." + C_RELTN_CUST_1_NBR + ", " );
      query.append( tbNmCusts + "." + C_RELTN_CUST_2_NBR + ", " );
      query.append( tbNmCusts + "." + C_RELTN_CUST_3_NBR + ", " );
      query.append( tbNmCusts + "." + C_RELTN_CUST_4_NBR + ", " );
      query.append( tbNmCusts + "." + C_RELTN_CUST_5_NBR + ", " );
      query.append( tbNmCusts + "." + newColumnNameCust1 + ", " );
      query.append( tbNmCusts + "." + newColumnNameCust2 + ", " );
      query.append( tbNmCusts + "." + newColumnNameCust3 );
      query.append( " FROM " );
      query.append( C_TABLE_NAME + ", (" + nestedQueryCust3 + ") " + tbNmCusts );
      query.append( " WHERE " );
      query.append( tbNmCusts + "." + C_RELTN_NBR + "=" );
      query.append( C_TABLE_NAME + "." + C_RELTN_NBR );

      String criteria = "";

      criteria = criteria + " AND " + C_TABLE_NAME + "." + C_REC_STAT_CODE
                 + " != '" + BaseODSEntity.C_REC_STAT_CODE_INACTIVE + "' ";

      if ( egNbr_ != null && !"".equals( egNbr_ ) )
      {
        criteria = criteria + " AND " + C_TABLE_NAME + "." + C_EG_NBR
                   + " like ? ";
      }
      if ( erNbr_ != null && !"".equals( erNbr_ ) )
      {
        criteria = criteria + " AND " + C_TABLE_NAME + "." + C_ER_NBR
                   + " like ? ";
      }
      if ( reltnNbr_ != null )
      {
        criteria = criteria + " AND " + C_TABLE_NAME + "." + C_RELTN_NBR
                   + " = ? ";
      }

      if ( criteria.length() > 0 )
      {
        query.append( criteria );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( egNbr_ != null && !"".equals( egNbr_ ) )
      {
        preparedStatement.setString( count++, egNbr_ );
      }

      if ( erNbr_ != null && !"".equals( erNbr_ ) )
      {
        preparedStatement.setString( count++, erNbr_ );
      }

      if ( reltnNbr_ != null )
      {
        preparedStatement.setLong( count++, reltnNbr_.longValue() );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      rsds = new ResultSetDataSet( resultSet );
      resultSet.close();
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

    String[] codeColumn = { C_REC_STAT_CODE };
    String[] nameColumn = { C_REC_STAT_TEXT };
    rsds.outerJoin( ODSConstraintDecoder.decodeRecStatus(), codeColumn,
                    codeColumn, nameColumn );

    return rsds;
  }

  /**
   * Realiza uma consulta em lista na tabela de movimento e cria uma ArrayList
   * de Entities para ser utilizada
   */
  public ArrayList listByEgNbr( String egNbr_, String erNbr_ )
  {
    TplRelationEgEntityVO tplRelationEgEntityVO;
    TplRelationEgEntity tplRelationEgEntity;
    DataSetRow row;
    BigDecimal reltnNbr;
    TplRelationPrvtEntity relationPrvtEntity;
    TplRelationPrvtEntityVO relationPrvtEntityVO;
    BigDecimal reltnCust1;
    BigDecimal reltnCust2;
    BigDecimal reltnCust3;
    BigDecimal reltnCust4;
    BigDecimal reltnCust5;
    TplCustomerPrvtEntity tplCustomerPrvtEntity1;
    TplCustomerPrvtEntityVO customerPrvtEntityVO1;
    TplCustomerPrvtEntity tplCustomerPrvtEntity2;
    TplCustomerPrvtEntityVO customerPrvtEntityVO2;
    TplCustomerPrvtEntity tplCustomerPrvtEntity3;
    TplCustomerPrvtEntityVO customerPrvtEntityVO3;

    ArrayList result = new ArrayList();

    DataSet rds = this.list( null, egNbr_, erNbr_ );
    for ( int indexRow = 0; indexRow < rds.size(); indexRow++ )
    {

      // Setando os valores de TplRelationEg
      tplRelationEgEntity = new TplRelationEgEntity();
      tplRelationEgEntityVO = ( TplRelationEgEntityVO ) tplRelationEgEntity.getData();

      row = rds.getRow( indexRow );

      reltnNbr = row.getBigDecimalByName( C_RELTN_NBR );
      if ( reltnNbr != null )
      {
        tplRelationEgEntityVO.setReltnNbr( BigInteger.valueOf( reltnNbr.longValue() ) );
      }

      tplRelationEgEntityVO.setEgNbr( row.getStringByName( C_EG_NBR ) );
      tplRelationEgEntityVO.setLastUpdDate( row.getDateByName( C_LAST_UPD_DATE ) );
      tplRelationEgEntityVO.setLastUpdUserId( row.getStringByName( C_LAST_UPD_USER_ID ) );
      tplRelationEgEntityVO.setLastAuthDate( row.getDateByName( C_LAST_AUTH_DATE ) );
      tplRelationEgEntityVO.setLastAuthUserId( row.getStringByName( C_LAST_AUTH_USER_ID ) );
      tplRelationEgEntityVO.setRecStatCode( row.getStringByName( C_REC_STAT_CODE ) );
      tplRelationEgEntityVO.setErNbr( row.getStringByName( C_ER_NBR ) );

      // Setando os valores de relation
      relationPrvtEntity = new TplRelationPrvtEntity();
      relationPrvtEntityVO = ( TplRelationPrvtEntityVO ) relationPrvtEntity.getData();

      tplRelationEgEntity.setRelationPrvtEntity( relationPrvtEntity );

      reltnCust1 = row.getBigDecimalByName( C_RELTN_CUST_1_NBR );
      if ( reltnCust1 != null )
      {
        relationPrvtEntityVO.setReltnCust1Nbr( BigInteger.valueOf( reltnCust1.longValue() ) );
      }

      reltnCust2 = row.getBigDecimalByName( C_RELTN_CUST_2_NBR );
      if ( reltnCust2 != null )
      {
        relationPrvtEntityVO.setReltnCust2Nbr( BigInteger.valueOf( reltnCust2.longValue() ) );
      }

      reltnCust3 = row.getBigDecimalByName( C_RELTN_CUST_3_NBR );
      if ( reltnCust3 != null )
      {
        relationPrvtEntityVO.setReltnCust3Nbr( BigInteger.valueOf( reltnCust3.longValue() ) );
      }

      reltnCust4 = row.getBigDecimalByName( C_RELTN_CUST_4_NBR );
      if ( reltnCust4 != null )
      {
        relationPrvtEntityVO.setReltnCust4Nbr( BigInteger.valueOf( reltnCust4.longValue() ) );
      }

      reltnCust5 = row.getBigDecimalByName( C_RELTN_CUST_5_NBR );
      if ( reltnCust5 != null )
      {
        relationPrvtEntityVO.setReltnCust5Nbr( BigInteger.valueOf( reltnCust5.longValue() ) );
      }

      // Setando valores de Customer
      tplCustomerPrvtEntity1 = new TplCustomerPrvtEntity();
      customerPrvtEntityVO1 = ( TplCustomerPrvtEntityVO ) tplCustomerPrvtEntity1.getData();
      customerPrvtEntityVO1.setCustShortNameText( row.getStringByName( C_CUST_SHORT_NAME_TEXT
                                                                       + "1" ) );
      relationPrvtEntity.setCustomerPrvtCmplEntity1( tplCustomerPrvtEntity1 );

      tplCustomerPrvtEntity2 = new TplCustomerPrvtEntity();
      customerPrvtEntityVO2 = ( TplCustomerPrvtEntityVO ) tplCustomerPrvtEntity2.getData();
      customerPrvtEntityVO2.setCustShortNameText( row.getStringByName( C_CUST_SHORT_NAME_TEXT
                                                                       + "2" ) );
      relationPrvtEntity.setCustomerPrvtCmplEntity2( tplCustomerPrvtEntity2 );

      tplCustomerPrvtEntity3 = new TplCustomerPrvtEntity();
      customerPrvtEntityVO3 = ( TplCustomerPrvtEntityVO ) tplCustomerPrvtEntity3.getData();
      customerPrvtEntityVO3.setCustShortNameText( row.getStringByName( C_CUST_SHORT_NAME_TEXT
                                                                       + "3" ) );
      relationPrvtEntity.setCustomerPrvtCmplEntity3( tplCustomerPrvtEntity3 );

      result.add( tplRelationEgEntity );
    }

    return result;
  }

  /**
   * Existe alguma associacao ativa na Current?
   */
  public boolean existsRelationActive( String egNbr_, String erNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    boolean existsRelationActive = false;

    try
    {
      query.append( "SELECT " );
      query.append( "COUNT ( " + C_EG_NBR + ") " );
      query.append( "FROM " + C_TABLE_NAME );
      query.append( " WHERE " );
      query.append( C_EG_NBR + " like ? AND " );
      query.append( C_ER_NBR + " like ? AND " );
      query.append( C_REC_STAT_CODE + " like '"
                    + TplRelationEgEntity.C_REC_STAT_CODE_ACTIVE + "'" );

      connection = OracleODSDAOFactory.getConnection();

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++, egNbr_ );
      preparedStatement.setString( count++, erNbr_ );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.next() )
      {
        int rowNumber = resultSet.getInt( 1 );
        if ( rowNumber > 0 )
        {
          existsRelationActive = true;
        }
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

    return existsRelationActive;
  }

  /**
   * Existe relation
   */
  public boolean existsRelation( String egNbr_, BigInteger reltnNbr_,
                                String erNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    boolean existsRelationActive = false;

    try
    {
      query.append( "SELECT " );
      query.append( "COUNT (" + C_EG_NBR + ") " );
      query.append( "FROM " + C_TABLE_NAME );
      query.append( " WHERE " );
      query.append( C_EG_NBR + " like ? AND " );
      query.append( C_ER_NBR + " like ? " );

      String criteria = "";
      connection = OracleODSDAOFactory.getConnection();

      if ( reltnNbr_ != null )
      {
        criteria = "AND " + C_RELTN_NBR + " = ? ";
      }

      if ( criteria.length() > 0 )
      {
        query.append( criteria );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      preparedStatement.setString( count++, egNbr_ );
      preparedStatement.setString( count++, erNbr_ );

      if ( reltnNbr_ != null )
      {
        preparedStatement.setLong( count++, reltnNbr_.longValue() );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.next() )
      {
        int rowNumber = resultSet.getInt( 1 );
        if ( rowNumber > 0 )
        {
          existsRelationActive = true;
        }
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

    return existsRelationActive;
  }

  public TplRelationEgEntity update( TplRelationEgEntity tplRelationEgEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TABLE_NAME + "  SET " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_LAST_AUTH_USER_ID + " = ?, " );
      query.append( C_LAST_AUTH_DATE + " = ?, " );
      query.append( C_REC_STAT_CODE + " = ? " );
      query.append( " WHERE " );
      query.append( C_EG_NBR + " = ? AND " );
      query.append( C_RELTN_NBR + " = ? AND " );
      query.append( C_ER_NBR + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplRelationEgEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplRelationEgEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplRelationEgEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplRelationEgEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      // Casting para Obter os campos especificos da tabela
      TplRelationEgEntityVO tplRelationEgEntityVO = ( TplRelationEgEntityVO ) tplRelationEgEntity_.getData();

      if ( tplRelationEgEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++, tplRelationEgEntityVO.getLastAuthUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplRelationEgEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplRelationEgEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      if ( tplRelationEgEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, tplRelationEgEntityVO.getRecStatCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplRelationEgEntityVO.getEgNbr() != null )
      {
        preparedStatement.setString( count++, tplRelationEgEntityVO.getEgNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplRelationEgEntityVO.getReltnNbr() != null )
      {
        preparedStatement.setLong( count++,
                           tplRelationEgEntityVO.getReltnNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      if ( tplRelationEgEntityVO.getErNbr() != null )
      {
        preparedStatement.setString( count++, tplRelationEgEntityVO.getErNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplRelationEgEntity_;
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
   * Existe EG 
   */
  public boolean existsEG( BigInteger reltnNbr_ ) {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    boolean existsRelationActive = false;

    try
    {
      query.append( "SELECT " );
      query.append( "COUNT (RELTN_NBR) " );
      query.append( "FROM PL.TPL_RELATION_EG "  );
      query.append( " WHERE " );
      query.append( "RELTN_NBR = ? ");
      query.append( "AND REC_STAT_CODE = '" + TplRelationEgEntity.C_REC_STAT_CODE_ACTIVE + "'" );
      
      connection = OracleODSDAOFactory.getConnection();

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      preparedStatement.setLong( count++, reltnNbr_.longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.next() )
      {
        int rowNumber = resultSet.getInt( 1 );
        if ( rowNumber > 0 )
        {
          existsRelationActive = true;
        }
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

    return existsRelationActive;
  }
}