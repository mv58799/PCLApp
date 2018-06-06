package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
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
import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.To3ProductAccountHistEntity;
import com.citibank.ods.entity.pl.valueobject.To3ProductAccountHistEntityVO;
import com.citibank.ods.persistence.pl.dao.To3ProductAccountHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Implementação da interface para acesso ao banco de dados de histórico de
 * conta de produtos.
 */
public class OracleTo3ProductAccountHistDAO extends
    BaseOracleTo3ProductAccountDAO implements To3ProductAccountHistDAO
{

  // Tabela TO3_PRODUCT_ACCOUNT_HIST
  private static final String C_TO3_PRODUCT_ACCOUNT_HIST = C_O3_SCHEMA
                                                           + "TO3_PRODUCT_ACCOUNT_HIST";

  // Data de referência do registro no histórico
  protected String C_PROD_ACCT_REF_DATE = "PROD_ACCT_REF_DATE";

  // Tabela TPL_CUSTOMER_PRVT
  private static final String C_TPL_CUSTOMER_PRVT = C_PL_SCHEMA
                                                    + "TPL_CUSTOMER_PRVT";

  //Usuário que aprovou cadastro do registro
  protected String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  //Data/hora de aprovação do cadastro do registro
  protected String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  // Tabela TPL_PRODUCT
  private static final String C_TPL_PRODUCT = C_PL_SCHEMA + "TPL_PRODUCT";

  //Tabele TBG_POINT_ACCT
  private static final String C_TBG_POINT_ACCT_INVST = C_BG_SCHEMA
                                                       + "TBG_POINT_ACCT_INVST";

  //Número da conta investimento
  protected String C_INVST_CUR_ACCT_NBR = "INVST_CUR_ACCT_NBR";

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTo3ProductAccountDAO#find(com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity)
   */
  public BaseTo3ProductAccountEntity find(
                                          BaseTo3ProductAccountEntity productAccountEntity_ )
  {
    // Converte para o tipo correto para tornar acessível os atibutos da classe
    // filha.
    To3ProductAccountHistEntity productAccountEntity = ( To3ProductAccountHistEntity ) productAccountEntity_;
    To3ProductAccountHistEntityVO productAccountEntityVO = ( To3ProductAccountHistEntityVO ) productAccountEntity.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList productAccountHistEntities;
    To3ProductAccountHistEntity productAccountHistEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_RELTN_NBR + ", " );
      query.append( C_CUR_ACCT_NBR + ", " );
      query.append( C_PROD_CODE + ", " );
      query.append( C_SYS_CODE + ", " );
      query.append( C_SYS_SEG_CODE + ", " );
      query.append( C_ORIG_PROD_ACCT_NBR + ", " );
      query.append( C_PROD_ACCT_STA_DATE + ", " );
      query.append( C_PROD_ACCT_END_DATE + ", " );
      query.append( C_PROD_ACCT_SIT_CODE + ", " );
      query.append( C_PROD_ACCT_ISIN_CODE + ", " );
      query.append( C_PROD_ACCT_LEGAL_BUS_CODE + ", " );
      query.append( C_PROD_PLCY_23A_IND + ", " );
      query.append( C_PROD_PLCY_23B_IND + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_PROD_ACCT_PORTF_MGMT_CODE + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_PROD_ACCT_REF_DATE );
      query.append( " FROM " );
      query.append( C_TO3_PRODUCT_ACCOUNT_HIST );
      query.append( " WHERE " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_ACCT_REF_DATE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1,
                         productAccountEntityVO.getProdAcctCode().longValue() );
      preparedStatement.setLong(
                         2,
                         productAccountEntityVO.getProdUnderAcctCode().longValue() );

      preparedStatement.setTimestamp(
                              3,
                              new Timestamp(
                                             productAccountEntityVO.getProdAcctRefDate().getTime() ) );

      resultSet = preparedStatement.executeQuery();

	  preparedStatement.replaceParametersInQuery(query.toString());

      productAccountHistEntities = instantiateFromResultSet( resultSet );

      if ( productAccountHistEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( productAccountHistEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        productAccountHistEntity = ( To3ProductAccountHistEntity ) productAccountHistEntities.get( 0 );
      }

      return productAccountHistEntity;
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
   * Instancia entidades a partir do ResultSet.
   * 
   * @param resultSet_ - ResultSet utilizado para instanciar entidades.
   * @return ArrayList - Entidades instanciadas a partir do ResultSet.
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {

    To3ProductAccountHistEntity productAccountHistEntity;
    To3ProductAccountHistEntityVO productAccountHistEntityVO;
    ArrayList productAccountHistEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        productAccountHistEntity = new To3ProductAccountHistEntity();
        productAccountHistEntityVO = ( To3ProductAccountHistEntityVO ) productAccountHistEntity.getData();

        productAccountHistEntityVO.setProdAcctCode( new BigInteger(
                                                                    resultSet_.getString( this.C_PROD_ACCT_CODE ) ) );
        productAccountHistEntityVO.setProdUnderAcctCode( new BigInteger(
                                                                         resultSet_.getString( this.C_PROD_UNDER_ACCT_CODE ) ) );
        if ( resultSet_.getString( this.C_CUST_NBR ) != null )
        {
          productAccountHistEntityVO.setCustNbr( new BigInteger(
                                                                 resultSet_.getString( this.C_CUST_NBR ) ) );

        }
        if ( resultSet_.getString( this.C_RELTN_NBR ) != null )
        {
          productAccountHistEntityVO.setReltnNbr( new BigInteger(
                                                                  resultSet_.getString( this.C_RELTN_NBR ) ) );

        }
        if ( resultSet_.getString( this.C_CUR_ACCT_NBR ) != null )
        {
          productAccountHistEntityVO.setCurAcctNbr( new BigInteger(
                                                                    resultSet_.getString( this.C_CUR_ACCT_NBR ) ) );

        }
        if ( resultSet_.getString( this.C_PROD_CODE ) != null )
        {
          productAccountHistEntityVO.setProdCode( resultSet_.getString( this.C_PROD_CODE ) );
        }

        if ( resultSet_.getString( this.C_SYS_CODE ) != null )
        {
          productAccountHistEntityVO.setSysCode( resultSet_.getString( this.C_SYS_CODE ) );
        }
        if ( resultSet_.getString( this.C_SYS_SEG_CODE ) != null )
        {
          productAccountHistEntityVO.setSysSegCode( new BigInteger(
                                                                    resultSet_.getString( this.C_SYS_SEG_CODE ) ) );

        }
        if ( resultSet_.getString( this.C_ORIG_PROD_ACCT_NBR ) != null )
        {
          productAccountHistEntityVO.setOrigProdAcctNbr( resultSet_.getString( this.C_ORIG_PROD_ACCT_NBR ) );

        }
        if ( resultSet_.getTimestamp( this.C_PROD_ACCT_STA_DATE ) != null )
        {
          productAccountHistEntityVO.setProdAcctStaDate( resultSet_.getTimestamp( this.C_PROD_ACCT_STA_DATE ) );
        }

        if ( resultSet_.getTimestamp( this.C_PROD_ACCT_END_DATE ) != null )
        {
          productAccountHistEntityVO.setProdAcctEndDate( resultSet_.getTimestamp( this.C_PROD_ACCT_END_DATE ) );
        }
        if ( resultSet_.getTimestamp( this.C_PROD_ACCT_REF_DATE ) != null )
        {
          productAccountHistEntityVO.setProdAcctRefDate( resultSet_.getTimestamp( this.C_PROD_ACCT_REF_DATE ) );
        }
        if ( resultSet_.getString( this.C_PROD_ACCT_SIT_CODE ) != null )
        {
          productAccountHistEntityVO.setProdAcctSitCode( resultSet_.getString( this.C_PROD_ACCT_SIT_CODE ) );
        }

        if ( resultSet_.getString( this.C_PROD_ACCT_LEGAL_BUS_CODE ) != null )
        {
          productAccountHistEntityVO.setProdAcctLegalBusCode( new BigInteger(
                                                                              resultSet_.getString( this.C_PROD_ACCT_LEGAL_BUS_CODE ) ) );
        }

        productAccountHistEntityVO.setLastAuthDate( resultSet_.getTimestamp( this.C_LAST_AUTH_DATE ) );
        productAccountHistEntityVO.setLastAuthUserId( resultSet_.getString( this.C_LAST_AUTH_USER_ID ) );
        productAccountHistEntityVO.setLastUpdDate( resultSet_.getTimestamp( this.C_LAST_UPD_DATE ) );
        productAccountHistEntityVO.setLastUpdUserId( resultSet_.getString( this.C_LAST_UPD_USER_ID ) );
        productAccountHistEntityVO.setProdAcctIsinCode( resultSet_.getString( this.C_PROD_ACCT_ISIN_CODE ) );
        productAccountHistEntityVO.setProdAcctPortfMgmtCode( resultSet_.getString( this.C_PROD_ACCT_PORTF_MGMT_CODE ) );
        productAccountHistEntityVO.setProdAcctPlcy23aInd( resultSet_.getString( this.C_PROD_PLCY_23A_IND ) );
        productAccountHistEntityVO.setProdAcctPlcy23bInd( resultSet_.getString( this.C_PROD_PLCY_23B_IND ) );
        productAccountHistEntityVO.setRecStatCode( resultSet_.getString( this.C_REC_STAT_CODE ) );
        productAccountHistEntities.add( productAccountHistEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return productAccountHistEntities;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.To3ProductAccountHistDAO#list(java.math.BigInteger,
   *      java.math.BigInteger, java.math.BigInteger, java.lang.String,
   *      java.util.Date)
   */
  public DataSet list( BigInteger reltnNbr_, BigInteger custNbr_,
                      BigInteger curAcctNbr_, String prodCode_,
                      Date prodAcctRefDate_, String custFullNameText_ )
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
      query.append( "ACCT." + C_RELTN_NBR + ", " );
      query.append( "ACCT." + C_CUST_NBR + ", " );
      query.append( "ACCT." + C_CUR_ACCT_NBR + ", " );
      query.append( "PROD." + C_PROD_NAME + ", " );
      query.append( "ACCT." + C_SYS_SEG_CODE + ", " );
      query.append( "ACCT." + C_PROD_CODE + ", " );
      query.append( "ACCT." + C_SYS_CODE + ", " );
      query.append( "ACCT." + C_PROD_ACCT_CODE + ", " );
      query.append( "ACCT." + C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( "ACCT." + C_PROD_ACCT_REF_DATE + ", " );
      query.append( "CUST." + C_CUST_FULL_NAME_TEXT + ", " );
      query.append( " POINT." + C_INVST_CUR_ACCT_NBR );
      query.append( " FROM " );
      query.append( C_TO3_PRODUCT_ACCOUNT_HIST + " ACCT " );
      query.append( " LEFT JOIN " );
      query.append( C_TPL_CUSTOMER_PRVT + " CUST " );
      query.append( " ON " );
      query.append( " ACCT." + C_CUST_NBR + " = " + "CUST." + C_CUST_NBR );
      query.append( " LEFT JOIN " );
      query.append( C_TPL_PRODUCT + " PROD " );
      query.append( " ON " );
      query.append( " ACCT.PROD_CODE = " + "PROD.PROD_CODE AND" );
      query.append( " ACCT.SYS_SEG_CODE = " + "PROD.SYS_SEG_CODE " );
      query.append( " LEFT JOIN " );
      query.append( C_TBG_POINT_ACCT_INVST + " POINT  " );
      query.append( " ON " );
      query.append( " POINT." + C_CUR_ACCT_NBR + " = " );
      query.append( "ACCT." + C_CUR_ACCT_NBR );

      String criteria = "";
      if ( reltnNbr_ != null )
      {
        criteria = criteria + " ACCT." + C_RELTN_NBR + " = ? AND ";
      }
      if ( custNbr_ != null )
      {
        criteria = criteria + " ACCT." + C_CUST_NBR + " = ? AND ";
      }

      if ( curAcctNbr_ != null )
      {
        criteria = criteria + "ACCT." + C_CUR_ACCT_NBR + " = ? AND ";
      }
      if ( prodCode_ != null && !prodCode_.equals( "" ) )
      {
        criteria = criteria + "UPPER(\"" + C_PROD_CODE + "\") LIKE ? AND ";
      }
      if ( prodAcctRefDate_ != null )
      {
        criteria = criteria + "TRUNC (ACCT." + C_PROD_ACCT_REF_DATE + ") >= ? AND ";
      }

      if ( custFullNameText_ != null && custFullNameText_.length() > 0 )
      {
        criteria = criteria + "UPPER( CUST." + C_CUST_FULL_NAME_TEXT
                   + ") like ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        query.append( " WHERE " + criteria.substring( 0, criteria.length() - 5 ) );
      }

      query.append( " ORDER BY " + C_PROD_ACCT_REF_DATE );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( reltnNbr_ != null )
      {
        preparedStatement.setLong( count++, reltnNbr_.longValue() );
      }
      if ( custNbr_ != null )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
      }

      if ( curAcctNbr_ != null )
      {
        preparedStatement.setLong( count++, curAcctNbr_.longValue() );
      }
      if ( prodCode_ != null && !prodCode_.equals( "" ) )
      {
        preparedStatement.setString( count++, prodCode_.toUpperCase() );
      }
      if ( prodAcctRefDate_ != null )
      {
        preparedStatement.setTimestamp( count++,
                                new Timestamp( prodAcctRefDate_.getTime() ) );
      }

      if ( custFullNameText_ != null && custFullNameText_.length() > 0 )
      {
        preparedStatement.setString( count++, "%" + custFullNameText_.toUpperCase()
                                      + "%" );
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

    return rsds;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTo3ProductAccountDAO#update(com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity)
   */
  public void insert( To3ProductAccountHistEntity productAccountHistEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    //   	Casting para a atribuicao das colunas especificas
    To3ProductAccountHistEntityVO to3ProductAccountHistEntityVO = ( To3ProductAccountHistEntityVO ) productAccountHistEntity_.getData();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "INSERT INTO " );
      query.append( C_TO3_PRODUCT_ACCOUNT_HIST );
      query.append( " ( " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_PROD_ACCT_REF_DATE + ", " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_RELTN_NBR + ", " );
      query.append( C_CUR_ACCT_NBR + ", " );
      query.append( C_PROD_CODE + ", " );
      query.append( C_PROD_PLCY_23A_IND + ", " );
      query.append( C_PROD_PLCY_23B_IND + ", " );
      query.append( C_PROD_ACCT_ISIN_CODE + ", " );
      query.append( C_PROD_ACCT_PORTF_MGMT_CODE + ", " );
      query.append( C_PROD_ACCT_LEGAL_BUS_CODE + ", " );
      query.append( C_SYS_CODE + ", " );
      query.append( C_SYS_SEG_CODE + ", " );
      query.append( C_ORIG_PROD_ACCT_NBR + ", " );
      query.append( C_PROD_ACCT_SIT_CODE + "," );
      query.append( C_PROD_ACCT_END_DATE + "," );
      query.append( C_PROD_ACCT_STA_DATE + ", " );
      query.append( C_REC_STAT_CODE + ") " );
      query.append( "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      preparedStatement.setLong(
                         count++,
                         productAccountHistEntity_.getData().getProdAcctCode().longValue() );

      preparedStatement.setLong(
                         count++,
                         productAccountHistEntity_.getData().getProdUnderAcctCode().longValue() );

      preparedStatement.setTimestamp(
                              count++,
                              new Timestamp(
                                             to3ProductAccountHistEntityVO.getProdAcctRefDate().getTime() ) );

      if ( productAccountHistEntity_.getData().getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           productAccountHistEntity_.getData().getCustNbr().longValue() );

      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( productAccountHistEntity_.getData().getReltnNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           productAccountHistEntity_.getData().getReltnNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( productAccountHistEntity_.getData().getCurAcctNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           productAccountHistEntity_.getData().getCurAcctNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( productAccountHistEntity_.getData().getProdCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             productAccountHistEntity_.getData().getProdCode().trim() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( productAccountHistEntity_.getData().getProdAcctPlcy23aInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             productAccountHistEntity_.getData().getProdAcctPlcy23aInd() );

      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( productAccountHistEntity_.getData().getProdAcctPlcy23bInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             productAccountHistEntity_.getData().getProdAcctPlcy23bInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( productAccountHistEntity_.getData().getProdAcctIsinCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             productAccountHistEntity_.getData().getProdAcctIsinCode() );

      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( productAccountHistEntity_.getData().getProdAcctPortfMgmtCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             productAccountHistEntity_.getData().getProdAcctPortfMgmtCode() );

      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( productAccountHistEntity_.getData().getProdAcctLegalBusCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           productAccountHistEntity_.getData().getProdAcctLegalBusCode().longValue() );

      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( productAccountHistEntity_.getData().getSysCode() != null )
      {
        preparedStatement.setString( count++,
                             productAccountHistEntity_.getData().getSysCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( productAccountHistEntity_.getData().getSysSegCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           productAccountHistEntity_.getData().getSysSegCode().longValue() );

      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( productAccountHistEntity_.getData().getOrigProdAcctNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             productAccountHistEntity_.getData().getOrigProdAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( productAccountHistEntity_.getData().getProdAcctSitCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             productAccountHistEntity_.getData().getProdAcctSitCode() );

      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( productAccountHistEntity_.getData().getProdAcctEndDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               productAccountHistEntity_.getData().getProdAcctEndDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( productAccountHistEntity_.getData().getProdAcctStaDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               productAccountHistEntity_.getData().getProdAcctStaDate().getTime() ) );

      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( productAccountHistEntity_.getData().getRecStatCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             productAccountHistEntity_.getData().getRecStatCode() );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      preparedStatement.executeQuery();

	  preparedStatement.replaceParametersInQuery(query.toString());

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

  public ArrayList selectByPk( BigInteger prodAcctCode_,
                              BigInteger prodUnderAcctCode_ )
  {
    return null;
  }

/* (non-Javadoc)
 * @see com.citibank.ods.persistence.pl.dao.BaseTo3ProductAccountDAO#findByPK(com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity)
 */
public BaseTo3ProductAccountEntity findByPK(BaseTo3ProductAccountEntity productAccountEntity_) {
	// TODO Auto-generated method stub
	return null;
}
}