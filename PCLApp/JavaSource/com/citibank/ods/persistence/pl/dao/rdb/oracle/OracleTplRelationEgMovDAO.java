package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.DataSetRow;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.TplRelationEgMovEntity;
import com.citibank.ods.entity.pl.TplRelationPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplCustomerPrvtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRelationEgMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRelationPrvtEntityVO;
import com.citibank.ods.persistence.pl.dao.TplRelationEgMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * Implementação Oracle para DAO da tabela TPL_RELATION_EGMov
 * @author leonardo.nakada
 * @date 15/04/2007
 */
public class OracleTplRelationEgMovDAO extends BaseOracleTplRelationEgDAO
    implements TplRelationEgMovDAO
{
  private static final String C_OPERN_CODE = "OPERN_CODE";

  private static final String C_OPERN_TEXT = "OPERN_TEXT";

  /*
   * Nomes das tabelas utilizadas
   */
  private static final String C_TABLE_NAME = C_PL_SCHEMA
                                             + "TPL_RELATION_EG_MOV";

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

  //Número do ER
  private static final String C_ER_NBR = "ER_NBR";

  /**
   * Insere uma nova linha na tabela TPL_RELATION_EG com os dados da entidade
   * correspondente passada como parametro
   * @param tplRelationEgEntity__
   * @throws UnexpectedException
   * @author leonardo.nakada
   * @date 15/04/2007
   */
  public TplRelationEgMovEntity insert(
                                       TplRelationEgMovEntity tplRelationEgEntity_ )
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
    sqlQuery.append( C_OPERN_CODE );
    sqlQuery.append( ") VALUES (?, ?, ?, ?, ?, ?)" );
    try
    {
      connection = OracleODSDAOFactory.getConnection();

      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

      int count = 1;

      TplRelationEgMovEntityVO tplRelationEgEntityVO = ( TplRelationEgMovEntityVO ) tplRelationEgEntity_.getData();

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

      // ER_NBR

      preparedStatement.setString( count++, tplRelationEgEntityVO.getErNbr() );
      // EG_NBR
      preparedStatement.setString( count++, tplRelationEgEntityVO.getEgNbr() );

      // LAST_UPD_DATE
      Timestamp tsLastUpdDate = new Timestamp(
                                               tplRelationEgEntityVO.getLastUpdDate().getTime() );
      preparedStatement.setTimestamp( count++, tsLastUpdDate );

      // LAST_UPD_USER_ID
      preparedStatement.setString( count++, tplRelationEgEntityVO.getLastUpdUserId() );

      // OPERN_CODE
      preparedStatement.setString( count++, tplRelationEgEntityVO.getOpernCode() );

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
   * Remove uma linha na tabela TPL_RELATION_EGMov de acordo com ID passado como
   * parâmetro.
   * @param erNbr
   * @param egNbr
   * @param reltnNbr
   * @throws UnexpectedException
   * @author leonardo.nakada
   * @date 15/04/2007
   */

  public void deleteRelations( String erNbr, String egNbr, BigInteger reltnNbr) throws UnexpectedException
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
      sqlQuery.append( C_EG_NBR + " = ? AND " );
      sqlQuery.append( C_ER_NBR + " = ? AND " );
      sqlQuery.append( C_RELTN_NBR + " = ? " );
      

      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

      preparedStatement.setString( 1, egNbr );
      preparedStatement.setString( 2, erNbr );
      preparedStatement.setLong(3, reltnNbr.longValue());

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
   * Procura um registro ou um conjunto de registro uma linha na tabela
   * TPL_RELATION_EGMov de acordo com os parametos relacionados a opções de
   * buscas na tela. Este método deve ser utilizado para consulta em lista com
   * filtros.
   * @param StringopernCode_ StringegNbr_ DatelastUpdDate_ StringlastUpdUserId_
   *          BigIntegerreltnNbr_ BigIntegerreltnSeqNbr_*
   * @returns dataSet_
   * @throws UnexpectedException
   * @author leonardo.nakada
   * @date 15/04/2007
   */

  /*
   * public DataSet list( String opernCode_, String egNbr_, Date lastUpdDate_,
   * String lastUpdUserId_, BigInteger reltnNbr_, BigInteger reltnSeqNbr_ )
   * throws UnexpectedException{
   * 
   * ResultSet resultSet = null; ResultSetDataSet resultSetDataSet = null;
   * CitiStatement preparedStatement = null; ManagedRdbConnection connection = null;
   * StringBuffer query = new StringBuffer(); try { connection =
   * OracleDAOFactory.createConnection(); sqlQuery.append("SELECT ");
   * sqlQuery.append(C_TABLE_COLUMNS); sqlQuery.append("FROM ");
   * sqlQuery.append(C_TABLE_NAME); sqlQuery.append("WHERE "); if(opernCode =!
   * null && opernCode.notequals("")) { sqlQuery.append(" OPERN_CODE = ? "); }
   * if(egNbr =! null && egNbr.notequals("")) { sqlQuery.append(" EG_NBR = ? AND
   * "); } if(lastUpdDate =! null && lastUpdDate.notequals("")) {
   * sqlQuery.append(" LAST_UPD_DATE = ? AND "); } if(lastUpdUserId =! null &&
   * lastUpdUserId.notequals("")) { sqlQuery.append(" LAST_UPD_USER_ID = ? AND
   * "); } if(reltnNbr =! null && reltnNbr.notequals("")) { sqlQuery.append("
   * RELTN_NBR = ? "); } if(reltnSeqNbr =! null && reltnSeqNbr.notequals("")) {
   * sqlQuery.append(" RELTN_SEQ_NBR = ? "); } sqlQuery.append("ORDER BY
   * RELTN_NBR, RELTN_SEQ_NBR"); preparedStatement = connection.prepareStatement(
   * query.toString() ); if(opernCode =! null || opernCode.notequals("")) {
   * preparedStatement.setString( 1, tplRelationEgMovEntity_.getData().getOpernCode() ); }
   * if(egNbr =! null || egNbr.notequals("")) { preparedStatement.setString( 2,
   * tplRelationEgMovEntity_.getData().getEgNbr() ); } if(lastUpdDate =! null ||
   * lastUpdDate.notequals("")) { preparedStatement.setDate( 3,
   * tplRelationEgMovEntity_.getData().getLastUpdDate().getDate() );
   * if(lastUpdUserId =! null || lastUpdUserId.notequals("")) {
   * preparedStatement.setString( 4,
   * tplRelationEgMovEntity_.getData().getLastUpdUserId() ); } if(reltnNbr =!
   * null || reltnNbr.notequals("")) { preparedStatement.setBigInteger( 5,
   * tplRelationEgMovEntity_.getData().getReltnNbr() ); } if(reltnSeqNbr =! null ||
   * reltnSeqNbr.notequals("")) { preparedStatement.setBigInteger( 6,
   * tplRelationEgMovEntity_.getData().getReltnSeqNbr() ); } resultSet =
   * preparedStatement.executeQuery(); resultSetDataSet = new ResultSetDataSet(
   * 
   * resultSet ); resultSet.close(); catch( SQLException e ) { throw new
   * UnexpectedException( C_ERROR_EXECUTING_STATEMENT, e ); } finally {
   * closeStatement( preparedStatement ); closeConnection( connection ); } return
   * resultSetDataSet;
   */

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplRelationEgMovDAO#list(java.lang.String,
   *      java.lang.String, java.math.BigInteger)
   */
  public DataSet list( String egNbr_, String lastUpdUserId_,
                      BigInteger reltnNbr_, String erNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      // Query -> Consulta
      // Adiciona à consulta o nome do Customer que está na tabela
      // TPL_CUSTOMER_PRVT
      // a partir do valor RELTN_CUST_1_NBR que está na tabela TPL_RELATION_PRVT
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
      // Adiciona à consulta o nome do Customer que está na tabela
      // TPL_CUSTOMER_PRVT
      // a partir do valor RELTN_CUST_2_NBR que está na tabela TPL_RELATION_PRVT
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
      // Adiciona à consulta o nome do Customer que está na tabela
      // TPL_CUSTOMER_PRVT
      // a partir do valor RELTN_CUST_3_NBR que está na tabela TPL_RELATION_PRVT
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
      query.append( C_TABLE_NAME + "." + C_ER_NBR + ", " );
      query.append( C_TABLE_NAME + "." + C_EG_NBR + ", " );
      query.append( C_TABLE_NAME + "." + C_LAST_UPD_DATE + ", " );
      query.append( C_TABLE_NAME + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( C_TABLE_NAME + "." + C_OPERN_CODE + ", " );
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

      if ( egNbr_ != null && !"".equals( egNbr_ ) )
      {
        criteria = criteria + C_TABLE_NAME + "." + C_EG_NBR + " like ? AND ";
      }

      if ( erNbr_ != null && !"".equals( erNbr_ ) )
      {
        criteria = criteria + C_TABLE_NAME + "." + C_ER_NBR + " like ? AND ";
      }

      if ( lastUpdUserId_ != null && !"".equals( lastUpdUserId_ ) )
      {
        criteria = criteria + "UPPER(" + C_TABLE_NAME + "."
                   + C_LAST_UPD_USER_ID + ") like ? AND ";
      }

      if ( reltnNbr_ != null )
      {
        criteria = criteria + C_TABLE_NAME + "." + C_RELTN_NBR + " = ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	AND " + criteria );
      }

      query.append( " ORDER BY " + newColumnNameCust1 );

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

      if ( lastUpdUserId_ != null && !"".equals( lastUpdUserId_ ) )
      {
        preparedStatement.setString( count++, "%" + lastUpdUserId_.toUpperCase() + "%" );
      }

      if ( reltnNbr_ != null )
      {
        preparedStatement.setLong( count++, reltnNbr_.longValue() );
      }

	  preparedStatement.replaceParametersInQuery(query.toString());
      resultSet = preparedStatement.executeQuery();
	  

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

    String[] codeColumn = { C_OPERN_CODE };
    String[] nameColumn = { C_OPERN_TEXT };
    rsds.outerJoin( ODSConstraintDecoder.decodeOpern(), codeColumn, codeColumn,
                    nameColumn );

    return rsds;
  }

  /**
   * Realiza uma consulta em lista na tabela de movimento e cria uma ArrayList
   * de Entities para ser utilizada
   */
  public ArrayList listByEgNbr( String egNbr_, String erNbr_ )
  {
    TplRelationEgMovEntity tplRelationEgEntity;
    TplRelationEgMovEntityVO tplRelationEgMovEntityVO;
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

    DataSet rds = this.list( egNbr_, null, null, erNbr_ );
    for ( int indexRow = 0; indexRow < rds.size(); indexRow++ )
    {

      // Setando os valores de TplRelationEg
      tplRelationEgEntity = new TplRelationEgMovEntity();
      tplRelationEgMovEntityVO = ( TplRelationEgMovEntityVO ) tplRelationEgEntity.getData();

      row = rds.getRow( indexRow );

      reltnNbr = row.getBigDecimalByName( C_RELTN_NBR );
      if ( reltnNbr != null )
      {
        tplRelationEgMovEntityVO.setReltnNbr( BigInteger.valueOf( reltnNbr.longValue() ) );
      }

      tplRelationEgMovEntityVO.setEgNbr( row.getStringByName( C_EG_NBR ) );
      tplRelationEgMovEntityVO.setLastUpdDate( row.getDateByName( C_LAST_UPD_DATE ) );
      tplRelationEgMovEntityVO.setLastUpdUserId( row.getStringByName( C_LAST_UPD_USER_ID ) );
      tplRelationEgMovEntityVO.setOpernCode( row.getStringByName( C_OPERN_CODE ) );
      tplRelationEgMovEntityVO.setErNbr( row.getStringByName( C_ER_NBR ) );

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
   * Verifica se existe um relacionamento com este EG na tabela de movimento
   */
  public boolean existsRelation( String egNbr_, String erNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    boolean existsRelation = false;

    try
    {
      query.append( "SELECT " );
      query.append( "COUNT ( " + C_EG_NBR + ") " );
      query.append( "FROM " + C_TABLE_NAME );
      query.append( " WHERE " );
      query.append( C_EG_NBR + " like ? AND " );
      query.append( C_ER_NBR + " like ? " );

      connection = OracleODSDAOFactory.getConnection();

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++, egNbr_ );
      preparedStatement.setString( count++, erNbr_ );

	  preparedStatement.replaceParametersInQuery(query.toString());
      resultSet = preparedStatement.executeQuery();
	  
	  

      if ( resultSet.next() )
      {
        int rowNumber = resultSet.getInt( 1 );
        if ( rowNumber > 0 )
        {
          existsRelation = true;
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

    return existsRelation;
  }
  
  /**
   * Remove uma linha na tabela TPL_RELATION_EGMov de acordo com ID passado como
   * parâmetro.
   * @param erNbr
   * @param egNbr
   * @throws UnexpectedException
   */

  public void deleteRelationsByEgNr( String erNbr, String egNbr) throws UnexpectedException
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
      sqlQuery.append( C_EG_NBR + " = ? AND " );
      sqlQuery.append( C_ER_NBR + " = ?  " );
      
      

      preparedStatement = new CitiStatement(connection.prepareStatement( sqlQuery.toString() ));

      preparedStatement.setString( 1, egNbr );
      preparedStatement.setString( 2, erNbr );

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
}