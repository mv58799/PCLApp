package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity;
import com.citibank.ods.entity.pl.TplContactCustEntity;
import com.citibank.ods.entity.pl.TplMrDocPrvtHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplMrDocPrvtHistEntityVO;
import com.citibank.ods.modules.client.mrdocprvt.ContactCustKey;
import com.citibank.ods.persistence.pl.dao.TplMrDocCallbackHistDAO;
import com.citibank.ods.persistence.pl.dao.TplMrDocPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Implementação da interface para acesso ao banco de dados do histórico de
 * Memória de Risco.
 */
public class OracleTplMrDocPrvtHistDAO extends BaseOracleTplMrDocPrvtDAO
    implements TplMrDocPrvtHistDAO
{
  // Data e Hora que o Usuario Aprovou o Registro Cadastrado.
  private static final String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  // Codigo do Usuario que Aprovou o Cadastro do Registro.
  private static final String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  // Status do Registro: 'S' (Ativo), 'N' (Inativo), 'A' (Aguardando
  // Aprovacao)
  private static final String C_REC_STAT_CODE = "REC_STAT_CODE";

  // Data de referência do histórico
  private static final String C_MR_DOC_REF_DATE = "MR_REF_DATE";

  // Tabela TPL_MR_DOC_PRVT_HIST
  private static final String C_TPL_MR_DOC_PRVT_HIST = C_PL_SCHEMA
                                                       + "TPL_MR_PRVT_HIST";

  // Tabela TO3_PRODUCT_ACCOUNT
  public static String C_TO3_PRODUCT_ACCOUNT = C_O3_SCHEMA
                                               + "TO3_PRODUCT_ACCOUNT";

  // Tabela TPL_CUSTOMER_PRVT
  public static String C_TPL_CUSTOMER_PRVT = C_PL_SCHEMA + "TPL_CUSTOMER_PRVT";

  //Número da conta corrente
  public static String C_CUR_ACCT_NBR = "CUR_ACCT_NBR";

  //Número do cliente
  public static String C_CUST_NBR = "CUST_NBR";

  //Nome do cliente
  public static String C_CUST_FULL_NAME_TEXT = "CUST_FULL_NAME_TEXT";

  //Indicador de conta CCI
  public static String C_MR_INVST_CUR_ACCT_IND = "PRVT_MR_INVST_CUR_ACCT_IND";

  //tabela TBG_POINT_ACCT_INVST - Alias
  private static final String C_ALIAS_TBG_POINT_ACCT_INVST = "CCI";

  // Tabela TPL_MR_DOC_PRVT_MOV - Alias
  private static final String C_ALIAS_TPL_MR_DOC_PRVT_HIST = "MR";

  // Tabela TPL_CUSTOMER_PRVT - Alias
  public static final String C_ALIAS_TPL_CUSTOMER_PRVT = "CUST";

  // Tabela TO3_PRODUCT_ACCOUNT - Alias
  public static final String C_ALIAS_TO3_PRODUCT_ACCOUNT = "PROD";

  //Indicador de Conta CCI - Yes
  public static final String C_IND_YES = "'S'";

  //Número da conta investimento
  private static final String C_INVST_CUR_ACCT_NBR = "INVST_CUR_ACCT_NBR";

  //tabela TBG_POINT_ACCT_INVST
  private static final String C_TBG_POINT_ACCT_INVST = C_BG_SCHEMA
                                                       + "TBG_POINT_ACCT_INVST";

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplMrDocPrvtDAO#find(com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity)
   */
  public BaseTplMrDocPrvtEntity find(
                                     BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_ )
  {

    TplContactCustEntity contactCustEntity;
    TplMrDocPrvtHistEntityVO mrDocPrvtHistEntityVO = ( TplMrDocPrvtHistEntityVO ) baseTplMrDocPrvtEntity_.getData();
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList mrDocPrvtEntities;
    BaseTplMrDocPrvtEntity mrDocPrvtEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_MR_DOC_CODE + ", " );
      query.append( C_MR_DOC_TEXT + ", " );
      query.append( C_MR_INVST_CUR_ACCT_IND + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_MR_DOC_REF_DATE + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_MR_DOC_PRVT_HIST );
      query.append( " WHERE " );
      query.append( C_MR_DOC_CODE + " = ? AND " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? AND " );
      query.append( C_MR_DOC_REF_DATE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, mrDocPrvtHistEntityVO.getMrDocCode().longValue() );
      preparedStatement.setLong( 2, mrDocPrvtHistEntityVO.getProdAcctCode().longValue() );
      preparedStatement.setLong(
                         3,
                         mrDocPrvtHistEntityVO.getProdUnderAcctCode().longValue() );
      preparedStatement.setTimestamp(
                              4,
                              new Timestamp(
                                             mrDocPrvtHistEntityVO.getMrDocRefDate().getTime() ) );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      mrDocPrvtEntities = instantiateFromResultSet( resultSet );

      if ( mrDocPrvtEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( mrDocPrvtEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        mrDocPrvtEntity = ( BaseTplMrDocPrvtEntity ) mrDocPrvtEntities.get( 0 );
      }

      // Cria mapa com as contatos associadas a memória de risco
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      TplMrDocCallbackHistDAO mrDocCallbackHistDAO = odsDAOFactory.getTplMrDocCallbackHistDAO();

      ArrayList contactCustEntitiesArray = ( ArrayList ) mrDocCallbackHistDAO.findAssociatedContactCustByPK(
                                                                                                             mrDocPrvtHistEntityVO.getMrDocCode(),
                                                                                                             mrDocPrvtHistEntityVO.getProdAcctCode(),
                                                                                                             mrDocPrvtHistEntityVO.getProdUnderAcctCode(),
                                                                                                             mrDocPrvtHistEntityVO.getMrDocRefDate() );
      HashMap contactCustEntitiesMap = new HashMap();
      Iterator contactCustEntitiesIt = contactCustEntitiesArray.iterator();
      while ( contactCustEntitiesIt.hasNext() )
      {
        contactCustEntity = ( TplContactCustEntity ) contactCustEntitiesIt.next();

        contactCustEntitiesMap.put(
                                    new ContactCustKey(
                                                        contactCustEntity.getData().getCustNbr(),
                                                        contactCustEntity.getData().getCtcNbr() ),
                                    contactCustEntity );
      }

      mrDocPrvtEntity.setTplContactCustEntities( contactCustEntitiesMap );

      return mrDocPrvtEntity;
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally {
		closeStatement(preparedStatement);
		closeConnection(connection);
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
    TplMrDocPrvtHistEntityVO mrDocPrvtHistEntityVO;
    TplMrDocPrvtHistEntity mrDocPrvtHistEntity;
    ArrayList mrDocPrvtEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        mrDocPrvtHistEntity = new TplMrDocPrvtHistEntity();
        mrDocPrvtHistEntityVO = ( TplMrDocPrvtHistEntityVO ) mrDocPrvtHistEntity.getData();

        mrDocPrvtHistEntityVO.setMrDocCode( new BigInteger(
                                                            resultSet_.getString( C_MR_DOC_CODE ) ) );

        if ( resultSet_.getString( C_MR_DOC_TEXT ) != null )
        {
          mrDocPrvtHistEntityVO.setMrDocText( resultSet_.getString( C_MR_DOC_TEXT ) );
        }

        if ( resultSet_.getString( C_MR_INVST_CUR_ACCT_IND ) != null )
        {
          mrDocPrvtHistEntityVO.setMrInvstCurAcctInd( resultSet_.getString( C_MR_INVST_CUR_ACCT_IND ) );
        }

        if ( resultSet_.getString( C_LAST_AUTH_USER_ID ) != null )
        {
          mrDocPrvtHistEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );
        }

        if ( resultSet_.getTimestamp( C_LAST_AUTH_DATE ) != null )
        {
          mrDocPrvtHistEntityVO.setLastAuthDate( resultSet_.getTimestamp( C_LAST_AUTH_DATE ) );
        }

        if ( resultSet_.getString( C_LAST_UPD_USER_ID ) != null )
        {
          mrDocPrvtHistEntityVO.setLastUpdUserId( resultSet_.getString( C_LAST_UPD_USER_ID ) );
        }

        if ( resultSet_.getTimestamp( C_LAST_UPD_DATE ) != null )
        {
          mrDocPrvtHistEntityVO.setLastUpdDate( resultSet_.getTimestamp( C_LAST_UPD_DATE ) );
        }

        if ( resultSet_.getString( C_REC_STAT_CODE ) != null )
        {
          mrDocPrvtHistEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );
        }

        if ( resultSet_.getTimestamp( C_MR_DOC_REF_DATE ) != null )
        {
          mrDocPrvtHistEntityVO.setMrDocRefDate( resultSet_.getTimestamp( C_MR_DOC_REF_DATE ) );

        }

        mrDocPrvtHistEntityVO.setProdAcctCode( new BigInteger(
                                                               resultSet_.getString( C_PROD_ACCT_CODE ) ) );

        mrDocPrvtHistEntityVO.setProdUnderAcctCode( new BigInteger(
                                                                    resultSet_.getString( C_PROD_UNDER_ACCT_CODE ) ) );

        mrDocPrvtEntities.add( mrDocPrvtHistEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return mrDocPrvtEntities;
  }

  /**
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocPrvtHistDAO#list( String
   *      mrDocCode_, String mrDocText_, String mrInvstCurAcct_,BigInteger
   *      curAcctNbr_, BigInteger custNbr_, Date refDate_ )
   */
  public DataSet list( BigInteger mrDocCode_, String mrDocText_,
                      String mrInvstCurAcctInd_, BigInteger custNbr_,
                      String custName_, BigInteger curAcctNbr_, Date refDate_ )
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
      query.append( C_ALIAS_TPL_MR_DOC_PRVT_HIST + "." + C_MR_DOC_CODE + ", " );
      query.append( C_ALIAS_TPL_MR_DOC_PRVT_HIST + "." + C_PROD_ACCT_CODE
                    + ", " );
      query.append( C_ALIAS_TPL_MR_DOC_PRVT_HIST + "." + C_PROD_UNDER_ACCT_CODE
                    + ", " );
      query.append( " ( CASE WHEN " + C_ALIAS_TPL_MR_DOC_PRVT_HIST + "."
                    + C_MR_INVST_CUR_ACCT_IND + " = " + C_IND_YES );
      query.append( " THEN " + "CCI." + C_INVST_CUR_ACCT_NBR );
      query.append( " ELSE '' END )AS " + C_INVST_CUR_ACCT_NBR + ", " );
      query.append( C_ALIAS_TPL_MR_DOC_PRVT_HIST + "." + C_MR_DOC_TEXT + ", " );
      query.append( C_ALIAS_TPL_MR_DOC_PRVT_HIST + "." + C_MR_DOC_REF_DATE
                    + ", " );
      query.append( C_ALIAS_TO3_PRODUCT_ACCOUNT + "." + C_CUR_ACCT_NBR + ", " );
      query.append( C_ALIAS_TO3_PRODUCT_ACCOUNT + "." + C_CUST_NBR + ", " );
      query.append( C_ALIAS_TPL_CUSTOMER_PRVT + "." + C_CUST_FULL_NAME_TEXT
                    + " " );
      query.append( " FROM " );
      query.append( C_TPL_MR_DOC_PRVT_HIST + " " + C_ALIAS_TPL_MR_DOC_PRVT_HIST
                    + ", " );
      query.append( C_TO3_PRODUCT_ACCOUNT + " " + C_ALIAS_TO3_PRODUCT_ACCOUNT
                    + ", " );
      query.append( C_TPL_CUSTOMER_PRVT + " " + C_ALIAS_TPL_CUSTOMER_PRVT
                    + ", " );
      query.append( C_TBG_POINT_ACCT_INVST + " " + C_ALIAS_TBG_POINT_ACCT_INVST );
      query.append( " WHERE " );
      query.append( C_ALIAS_TPL_MR_DOC_PRVT_HIST + "." + C_PROD_ACCT_CODE
                    + " = " + C_ALIAS_TO3_PRODUCT_ACCOUNT + "."
                    + C_PROD_ACCT_CODE + " AND " );
      query.append( C_ALIAS_TPL_MR_DOC_PRVT_HIST + "." + C_PROD_UNDER_ACCT_CODE
                    + " = " + C_ALIAS_TO3_PRODUCT_ACCOUNT + "."
                    + C_PROD_UNDER_ACCT_CODE + " AND " );
      query.append( C_ALIAS_TPL_CUSTOMER_PRVT + "." + C_CUST_NBR + " = " );
      query.append( C_ALIAS_TO3_PRODUCT_ACCOUNT + "." + C_CUST_NBR + " AND " );
      query.append( C_ALIAS_TO3_PRODUCT_ACCOUNT + "." + C_CUR_ACCT_NBR + " = " );
      query.append( C_ALIAS_TBG_POINT_ACCT_INVST + "." + C_CUR_ACCT_NBR
                    + "(+) AND " );
      query.append( " TRIM(" + C_PROD_CODE + ")= '" + C_PROD_CUR_CODE
                    + "' AND " );
      query.append( C_ALIAS_TO3_PRODUCT_ACCOUNT + "." + C_SYS_SEG_CODE + " = "
                    + C_SYS_SEG_VALUE );

      String criteria = "";
      if ( mrDocCode_ != null )
      {
        criteria = criteria + C_ALIAS_TPL_MR_DOC_PRVT_HIST + "."
                   + C_MR_DOC_CODE + " = ? AND ";
      }

      if ( mrDocText_ != null && !mrDocText_.equals( "" ) )
      {
        criteria = criteria + "UPPER(" + C_MR_DOC_TEXT + ") LIKE ? AND ";
      }

      if ( mrInvstCurAcctInd_ != null && !mrInvstCurAcctInd_.equals( "" ) )
      {
        criteria = criteria + "UPPER(" + C_ALIAS_TPL_MR_DOC_PRVT_HIST + "."
                   + C_MR_INVST_CUR_ACCT_IND + ") = ? AND ";
      }

      if ( custNbr_ != null )
      {
        criteria = criteria + C_ALIAS_TPL_CUSTOMER_PRVT + "." + C_CUST_NBR
                   + " = ? AND ";
      }

      if ( custName_ != null && !custName_.equals( "" ) )
      {
        criteria = criteria + " UPPER(" + C_ALIAS_TPL_CUSTOMER_PRVT + "."
                   + C_CUST_FULL_NAME_TEXT + ") LIKE ?  AND ";
      }

      if ( curAcctNbr_ != null )
      {
        criteria = criteria + C_ALIAS_TO3_PRODUCT_ACCOUNT + "."
                   + C_CUR_ACCT_NBR + " = ? AND ";
      }
      if ( refDate_ != null )
      {
        criteria = criteria + "TRUNC (" + C_MR_DOC_REF_DATE + ") >= ? AND ";
      }

      if ( !criteria.equals( "" ) )
      {
        query.append( " AND " + criteria );
        query.append( C_ALIAS_TPL_MR_DOC_PRVT_HIST + "." + C_REC_STAT_CODE
                      + " <> ? " );
      }

      query.append( " ORDER BY " + C_ALIAS_TPL_CUSTOMER_PRVT + "."
                    + C_CUST_FULL_NAME_TEXT );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( mrDocCode_ != null )
      {
        preparedStatement.setLong( count++, mrDocCode_.longValue() );
      }

      if ( mrDocText_ != null && !mrDocText_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + mrDocText_.toUpperCase() + "%" );
      }

      if ( mrInvstCurAcctInd_ != null && !mrInvstCurAcctInd_.equals( "" ) )
      {
        preparedStatement.setString( count++, mrInvstCurAcctInd_.toUpperCase() );
      }
      if ( custNbr_ != null )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
      }

      if ( custName_ != null && !custName_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + custName_.toUpperCase() + "%" );
      }

      if ( curAcctNbr_ != null )
      {
        preparedStatement.setLong( count++, curAcctNbr_.longValue() );
      }
      if ( refDate_ != null )
      {
        preparedStatement.setTimestamp( count++, new Timestamp( refDate_.getTime() ) );
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
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocPrvtHistDAO#insert(com.citibank.ods.entity.pl.TplMrDocPrvtHistEntity)
   */
  public void insert( TplMrDocPrvtHistEntity mrDocPrvtHistEntity_ )
  {
    TplMrDocPrvtHistEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtHistEntityVO ) mrDocPrvtHistEntity_.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_MR_DOC_PRVT_HIST + " ( " );
      query.append( C_MR_DOC_CODE + ", " );
      query.append( C_MR_DOC_REF_DATE + ", " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE );

      int attCount = 0;

      if ( mrDocPrvtMovEntityVO.getMrDocText() != null
           && !mrDocPrvtMovEntityVO.getMrDocText().equals( "" ) )
      {
        query.append( ", " + C_MR_DOC_TEXT );
        attCount++;
      }

      if ( mrDocPrvtMovEntityVO.getMrInvstCurAcctInd() != null
           && !mrDocPrvtMovEntityVO.getMrInvstCurAcctInd().equals( "" ) )
      {
        query.append( ", " + C_MR_INVST_CUR_ACCT_IND );
        attCount++;
      }

      if ( mrDocPrvtMovEntityVO.getLastAuthUserId() != null
           && !mrDocPrvtMovEntityVO.getLastAuthUserId().equals( "" ) )
      {
        query.append( ", " + C_LAST_AUTH_USER_ID );
        attCount++;
      }

      if ( mrDocPrvtMovEntityVO.getLastAuthDate() != null )
      {
        query.append( ", " + C_LAST_AUTH_DATE );
        attCount++;
      }

      if ( mrDocPrvtMovEntityVO.getLastUpdUserId() != null
           && !mrDocPrvtMovEntityVO.getLastUpdUserId().equals( "" ) )
      {
        query.append( ", " + C_LAST_UPD_USER_ID );
        attCount++;
      }

      if ( mrDocPrvtMovEntityVO.getLastUpdDate() != null )
      {
        query.append( ", " + C_LAST_UPD_DATE );
        attCount++;
      }

      if ( mrDocPrvtMovEntityVO.getRecStatCode() != null
           && !mrDocPrvtMovEntityVO.getRecStatCode().equals( "" ) )
      {
        query.append( ", " + C_REC_STAT_CODE );
        attCount++;
      }

      query.append( " ) " );

      query.append( "VALUES ( ?, ?, ?, ? " );

      for ( int i = 0; i < attCount; i++ )
      {
        query.append( ", ? " );
      }

      query.append( ") " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, mrDocPrvtMovEntityVO.getMrDocCode().longValue() );
      preparedStatement.setTimestamp(
                              2,
                              new Timestamp(
                                             mrDocPrvtMovEntityVO.getMrDocRefDate().getTime() ) );
      preparedStatement.setLong( 3, mrDocPrvtMovEntityVO.getProdAcctCode().longValue() );
      preparedStatement.setLong(
                         4,
                         mrDocPrvtMovEntityVO.getProdUnderAcctCode().longValue() );

      int index = 5;

      if ( mrDocPrvtMovEntityVO.getMrDocText() != null
           && !mrDocPrvtMovEntityVO.getMrDocText().equals( "" ) )
      {
        preparedStatement.setString( index++, mrDocPrvtMovEntityVO.getMrDocText() );
      }

      if ( mrDocPrvtMovEntityVO.getMrInvstCurAcctInd() != null
           && !mrDocPrvtMovEntityVO.getMrInvstCurAcctInd().equals( "" ) )
      {
        preparedStatement.setString( index++,
                             mrDocPrvtMovEntityVO.getMrInvstCurAcctInd() );
      }

      if ( mrDocPrvtMovEntityVO.getLastAuthUserId() != null
           && !mrDocPrvtMovEntityVO.getLastAuthUserId().equals( "" ) )
      {
        preparedStatement.setString( index++, mrDocPrvtMovEntityVO.getLastAuthUserId() );
      }

      if ( mrDocPrvtMovEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                index++,
                                new Timestamp(
                                               mrDocPrvtMovEntityVO.getLastAuthDate().getTime() ) );
      }

      if ( mrDocPrvtMovEntityVO.getLastUpdUserId() != null
           && !mrDocPrvtMovEntityVO.getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString( index++, mrDocPrvtMovEntityVO.getLastUpdUserId() );
      }

      if ( mrDocPrvtMovEntityVO.getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                index++,
                                new Timestamp(
                                               mrDocPrvtMovEntityVO.getLastUpdDate().getTime() ) );
      }

      if ( mrDocPrvtMovEntityVO.getRecStatCode() != null
           && !mrDocPrvtMovEntityVO.getRecStatCode().equals( "" ) )
      {
        preparedStatement.setString( index++, mrDocPrvtMovEntityVO.getRecStatCode() );
      }

      preparedStatement.execute();
	  preparedStatement.replaceParametersInQuery(query.toString());

    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally {
		closeStatement(preparedStatement);
		closeConnection(connection);
	}
  }

/* (non-Javadoc)
 * @see com.citibank.ods.persistence.pl.dao.BaseTplMrDocPrvtDAO#findById(com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity)
 */
public BaseTplMrDocPrvtEntity findById(BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_) {
	// TODO Auto-generated method stub
	return null;
}

/* (non-Javadoc)
 * @see com.citibank.ods.persistence.pl.dao.BaseTplMrDocPrvtDAO#findActive(com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity)
 */
public BaseTplMrDocPrvtEntity findActive(BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_) {
	// TODO Auto-generated method stub
	return null;
}
}