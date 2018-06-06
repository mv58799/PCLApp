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
import com.citibank.ods.common.dataset.DataSetRow;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity;
import com.citibank.ods.entity.pl.TplContactCustEntity;
import com.citibank.ods.entity.pl.TplMrDocPrvtEntity;
import com.citibank.ods.entity.pl.TplMrDocPrvtMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplMrDocPrvtMovEntityVO;
import com.citibank.ods.modules.client.mrdocprvt.ContactCustKey;
import com.citibank.ods.persistence.pl.dao.TplMrDocCallbackMovDAO;
import com.citibank.ods.persistence.pl.dao.TplMrDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplMrDocPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Implementação da interface para acesso ao banco de dados de movimento de
 * Memória de Risco.
 */
public class OracleTplMrDocPrvtMovDAO extends BaseOracleTplMrDocPrvtDAO
    implements TplMrDocPrvtMovDAO
{

  // Código da ação que originou o registro
  private static final String C_OPERN_CODE = "OPERN_CODE";

  // Descrição da ação.
  private static final String C_OPERN_TEXT = "OPERN_TEXT";

  // Tabela TPL_MR_DOC_PRVT_MOV
  private static final String C_TPL_MR_DOC_PRVT_MOV = C_PL_SCHEMA
                                                      + "TPL_MR_PRVT_MOV";

  // Tabela O3.TO3_PRODUCT_ACCOUNT
  private static final String C_TO3_PRODUCT_ACCOUNT = C_O3_SCHEMA
                                                      + "TO3_PRODUCT_ACCOUNT";

  // Tabela TPL_CUSTOMER_PRVT
  public static String C_TPL_CUSTOMER_PRVT = C_PL_SCHEMA + "TPL_CUSTOMER_PRVT";

  //tabela TBG_POINT_ACCT_INVST - Alias
  private static final String C_ALIAS_TBG_POINT_ACCT_INVST = "CCI";

  // Tabela TPL_MR_DOC_PRVT_MOV - Alias
  private static final String C_ALIAS_TPL_MR_DOC_PRVT_MOV = "MR";

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

  //Número da conta corrente
  public static String C_CUR_ACCT_NBR = "CUR_ACCT_NBR";

  //Número do cliente
  public static String C_CUST_NBR = "CUST_NBR";

  //Nome do cliente
  public static String C_CUST_FULL_NAME_TEXT = "CUST_FULL_NAME_TEXT";

  //Indicador de conta CCI
  public static String C_MR_INVST_CUR_ACCT_IND = "PRVT_MR_INVST_CUR_ACCT_IND";

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplMrDocPrvtDAO#find(com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity)
   */
  public BaseTplMrDocPrvtEntity find(
                                     BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_ )
  {
    TplContactCustEntity contactCustEntity;
    TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) baseTplMrDocPrvtEntity_.getData();
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList mrDocPrvtMovEntities;
    BaseTplMrDocPrvtEntity mrDocPrvtMovEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );	  
      query.append( C_MR_DOC_CODE + ", " );
      query.append( C_MR_DOC_TEXT + ", " );
      query.append( C_MR_INVST_CUR_ACCT_IND + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_OPERN_CODE + ", " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_MR_DOC_PRVT_MOV );
      query.append( " WHERE " );
      //query.append( C_MR_DOC_CODE + " = ? AND " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      //preparedStatement.setLong( 1, mrDocPrvtMovEntityVO.getMrDocCode().longValue() );
      preparedStatement.setLong( 1, mrDocPrvtMovEntityVO.getProdAcctCode().longValue() );
      preparedStatement.setLong(
                         2,
                         mrDocPrvtMovEntityVO.getProdUnderAcctCode().longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      mrDocPrvtMovEntities = instantiateFromResultSet( resultSet );

      if ( mrDocPrvtMovEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( mrDocPrvtMovEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        mrDocPrvtMovEntity = ( BaseTplMrDocPrvtEntity ) mrDocPrvtMovEntities.get( 0 );
      }

      // Cria mapa com as contatos associadas a memória de risco
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      TplMrDocCallbackMovDAO mrDocCallbackMovDAO = odsDAOFactory.getTplMrDocCallbackMovDAO();

      //      ArrayList contactCustEntitiesArray = ( ArrayList )
      // mrDocCallbackMovDAO.findAssociatedContactCustByPK(
      //                                                                                                            mrDocPrvtMovEntityVO.getMrDocCode(),
      //                                                                                                            mrDocPrvtMovEntityVO.getProdAcctCode(),
      //                                                                                                            mrDocPrvtMovEntityVO.getProdUnderAcctCode() );

      
	  //Seta o código do MR
	  TplMrDocPrvtMovEntityVO movVO = (TplMrDocPrvtMovEntityVO)mrDocPrvtMovEntity.getData();	  
	  mrDocPrvtMovEntityVO.setMrDocCode(movVO.getMrDocCode());	  
      
      ArrayList contactCustEntitiesArray = ( ArrayList ) mrDocCallbackMovDAO.findContactCustByPK(
                                                                                                  mrDocPrvtMovEntityVO.getMrDocCode(),
                                                                                                  mrDocPrvtMovEntityVO.getProdAcctCode(),
                                                                                                  mrDocPrvtMovEntityVO.getProdUnderAcctCode() );

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
      
      mrDocPrvtMovEntity.setTplContactCustEntities( contactCustEntitiesMap );
      

      return mrDocPrvtMovEntity;
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
  
  public BaseTplMrDocPrvtEntity findById(
									   BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_ )
  {
	TplContactCustEntity contactCustEntity;
	TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO =
		(TplMrDocPrvtMovEntityVO) baseTplMrDocPrvtEntity_.getData();
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	ResultSet resultSet = null;
	StringBuffer query = new StringBuffer();
	ArrayList mrDocPrvtMovEntities;
	BaseTplMrDocPrvtEntity mrDocPrvtMovEntity = null;

	try {
		connection = OracleODSDAOFactory.getConnection();
		query.append("SELECT ");
		query.append(C_MR_DOC_CODE + ", ");
		query.append(C_MR_DOC_TEXT + ", ");
		query.append(C_MR_INVST_CUR_ACCT_IND + ", ");
		query.append(C_LAST_UPD_USER_ID + ", ");
		query.append(C_LAST_UPD_DATE + ", ");
		query.append(C_OPERN_CODE + ", ");
		query.append(C_PROD_ACCT_CODE + ", ");
		query.append(C_PROD_UNDER_ACCT_CODE);
		query.append(" FROM ");
		query.append(C_TPL_MR_DOC_PRVT_MOV);
		query.append(" WHERE ");
		query.append(C_MR_DOC_CODE + " = ? ");

		preparedStatement =
			new CitiStatement(connection.prepareStatement(query.toString()));

		preparedStatement.setLong(1,mrDocPrvtMovEntityVO.getMrDocCode().longValue());
		resultSet = preparedStatement.executeQuery();
		preparedStatement.replaceParametersInQuery(query.toString());

		mrDocPrvtMovEntities = instantiateFromResultSet(resultSet);

		if (mrDocPrvtMovEntities.size() == 0) {
			throw new NoRowsReturnedException();
		} else if (mrDocPrvtMovEntities.size() > 1) {
			throw new UnexpectedException(C_ERROR_TOO_MANY_ROWS_RETURNED);
		} else {
			mrDocPrvtMovEntity =
				(BaseTplMrDocPrvtEntity) mrDocPrvtMovEntities.get(0);
		}

		// Cria mapa com as contatos associadas a memória de risco
		ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
		TplMrDocCallbackMovDAO mrDocCallbackMovDAO =
			odsDAOFactory.getTplMrDocCallbackMovDAO();

		//Seta o código do MR
		TplMrDocPrvtMovEntityVO movVO =
			(TplMrDocPrvtMovEntityVO) mrDocPrvtMovEntity.getData();
		mrDocPrvtMovEntityVO.setMrDocCode(movVO.getMrDocCode());

		ArrayList contactCustEntitiesArray =
			(ArrayList) mrDocCallbackMovDAO.findContactCustByPK(
				mrDocPrvtMovEntityVO.getMrDocCode(),
				mrDocPrvtMovEntityVO.getProdAcctCode(),
				mrDocPrvtMovEntityVO.getProdUnderAcctCode());

		HashMap contactCustEntitiesMap = new HashMap();
		Iterator contactCustEntitiesIt = contactCustEntitiesArray.iterator();
		while (contactCustEntitiesIt.hasNext()) {
			contactCustEntity =
				(TplContactCustEntity) contactCustEntitiesIt.next();

			contactCustEntitiesMap.put(
				new ContactCustKey(
					contactCustEntity.getData().getCustNbr(),
					contactCustEntity.getData().getCtcNbr()),
				contactCustEntity);
		}

		mrDocPrvtMovEntity.setTplContactCustEntities(contactCustEntitiesMap);

		return mrDocPrvtMovEntity;
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
  
  public boolean findExists(BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_ )
	{
	  TplContactCustEntity contactCustEntity;
	  TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) baseTplMrDocPrvtEntity_.getData();
	  ManagedRdbConnection connection = null;
	  CitiStatement preparedStatement = null;
	  ResultSet resultSet = null;
	  StringBuffer query = new StringBuffer();
	  ArrayList mrDocPrvtMovEntities;
	  BaseTplMrDocPrvtEntity mrDocPrvtMovEntity = null;

	  try
	  {
		connection = OracleODSDAOFactory.getConnection();
		query.append( "SELECT " );	  
		query.append( C_MR_DOC_CODE + ", " );
		query.append( C_MR_DOC_TEXT + ", " );
		query.append( C_MR_INVST_CUR_ACCT_IND + ", " );
		query.append( C_LAST_UPD_USER_ID + ", " );
		query.append( C_LAST_UPD_DATE + ", " );
		query.append( C_OPERN_CODE + ", " );
		query.append( C_PROD_ACCT_CODE + ", " );
		query.append( C_PROD_UNDER_ACCT_CODE );
		query.append( " FROM " );
		query.append( C_TPL_MR_DOC_PRVT_MOV );
		query.append( " WHERE " );		
		query.append( C_PROD_ACCT_CODE + " = ? AND " );
		query.append( C_PROD_UNDER_ACCT_CODE + " = ? " );

		preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));		
		preparedStatement.setLong( 1, mrDocPrvtMovEntityVO.getProdAcctCode().longValue() );
		preparedStatement.setLong( 2,mrDocPrvtMovEntityVO.getProdUnderAcctCode().longValue() );

		preparedStatement.replaceParametersInQuery(query.toString());
		resultSet = preparedStatement.executeQuery();		

		if(resultSet.next()){
			return true;
		}
		else{
			return false;
		}
		
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
    TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO;
    TplMrDocPrvtMovEntity mrDocPrvtMovEntity;
    ArrayList mrDocPrvtMovEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        mrDocPrvtMovEntity = new TplMrDocPrvtMovEntity();
        mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) mrDocPrvtMovEntity.getData();

        mrDocPrvtMovEntityVO.setMrDocCode( new BigInteger(
                                                           resultSet_.getString( C_MR_DOC_CODE ) ) );

        if ( resultSet_.getString( C_MR_DOC_TEXT ) != null )
        {
          mrDocPrvtMovEntityVO.setMrDocText( resultSet_.getString( C_MR_DOC_TEXT ) );
        }

        if ( resultSet_.getString( C_MR_INVST_CUR_ACCT_IND ) != null )
        {
          mrDocPrvtMovEntityVO.setMrInvstCurAcctInd( resultSet_.getString( C_MR_INVST_CUR_ACCT_IND ) );
        }

        if ( resultSet_.getString( C_LAST_UPD_USER_ID ) != null )
        {
          mrDocPrvtMovEntityVO.setLastUpdUserId( resultSet_.getString( C_LAST_UPD_USER_ID ) );
        }

        if ( resultSet_.getTimestamp( C_LAST_UPD_DATE ) != null )
        {
          mrDocPrvtMovEntityVO.setLastUpdDate( resultSet_.getTimestamp( C_LAST_UPD_DATE ) );
        }

        if ( resultSet_.getString( C_OPERN_CODE ) != null )
        {
          mrDocPrvtMovEntityVO.setOpernCode( resultSet_.getString( C_OPERN_CODE ) );
        }

        mrDocPrvtMovEntityVO.setProdAcctCode( new BigInteger(
                                                              resultSet_.getString( C_PROD_ACCT_CODE ) ) );

        mrDocPrvtMovEntityVO.setProdUnderAcctCode( new BigInteger(
                                                                   resultSet_.getString( C_PROD_UNDER_ACCT_CODE ) ) );

        mrDocPrvtMovEntities.add( mrDocPrvtMovEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return mrDocPrvtMovEntities;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocPrvtMovDAO#insert(com.citibank.ods.entity.pl.TplMrDocPrvtMovEntity)
   */
  public void insert( TplMrDocPrvtMovEntity mrDocPrvtMovEntity_ )
  {
    TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) mrDocPrvtMovEntity_.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_MR_DOC_PRVT_MOV + " ( " );
      query.append( C_MR_DOC_CODE + ", " );
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

      if ( mrDocPrvtMovEntityVO.getOpernCode() != null
           && !mrDocPrvtMovEntityVO.getOpernCode().equals( "" ) )
      {
        query.append( ", " + C_OPERN_CODE );
        attCount++;
      }

      query.append( " ) " );

      query.append( "VALUES ( ?, ?, ? " );

      for ( int i = 0; i < attCount; i++ )
      {
        query.append( ", ? " );
      }

      query.append( ") " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, mrDocPrvtMovEntityVO.getMrDocCode().longValue() );
      preparedStatement.setLong( 2, mrDocPrvtMovEntityVO.getProdAcctCode().longValue() );
      preparedStatement.setLong(
                         3,
                         mrDocPrvtMovEntityVO.getProdUnderAcctCode().longValue() );

      int index = 4;

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

      if ( mrDocPrvtMovEntityVO.getOpernCode() != null
           && !mrDocPrvtMovEntityVO.getOpernCode().equals( "" ) )
      {
        preparedStatement.setString( index++, mrDocPrvtMovEntityVO.getOpernCode() );
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

  /**
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocPrvtMovDAO#existsMovement(com.citibank.ods.entity.pl.TplMrDocPrvtMovEntity)
   */
  public boolean existsMovement( TplMrDocPrvtMovEntity mrDocPrvtMovEntity_ )
  {
	/*boolean exists = findExists(mrDocPrvtMovEntity_);

    try
    {
      TplMrDocPrvtMovEntity mrDocPrvtMovEntity = ( TplMrDocPrvtMovEntity ) find( mrDocPrvtMovEntity_ );
      exists = true;
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }*/

    return findExists(mrDocPrvtMovEntity_);
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocPrvtMovDAO#list(java.math.BigInteger,
   *      java.lang.String, java.lang.String, java.math.BigInteger,
   *      java.math.BigInteger, java.lang.String)
   */
  public DataSet list( BigInteger mrDocCode_, String mrDocText_,
                      String mrInvstCurAcctInd_, BigInteger custNbr_,
                      String custName_, BigInteger curAcctNbr_,
                      String lastUpdUserId_, Date lastUpdDate_,BigInteger prodAcctCode_,BigInteger prodAcctUnderCode_ )
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
      query.append( C_ALIAS_TPL_MR_DOC_PRVT_MOV + "." + C_MR_DOC_CODE + ", " );
      query.append( C_ALIAS_TPL_MR_DOC_PRVT_MOV + "." + C_PROD_ACCT_CODE + ", " );
      query.append( C_ALIAS_TPL_MR_DOC_PRVT_MOV + "." + C_PROD_UNDER_ACCT_CODE
                    + ", " );
      query.append( " ( CASE WHEN " + C_ALIAS_TPL_MR_DOC_PRVT_MOV + "."
                    + C_MR_INVST_CUR_ACCT_IND + " = " + C_IND_YES );
      query.append( " THEN " + "CCI." + C_INVST_CUR_ACCT_NBR );
      query.append( " ELSE '' END )AS " + C_INVST_CUR_ACCT_NBR + ", " );
      query.append( C_ALIAS_TPL_MR_DOC_PRVT_MOV + "." + C_MR_DOC_TEXT + ", " );
      query.append( C_ALIAS_TPL_MR_DOC_PRVT_MOV + "." + C_LAST_UPD_DATE + ", " );
      query.append( C_ALIAS_TPL_MR_DOC_PRVT_MOV + "." + C_LAST_UPD_USER_ID
                    + ", " );
      query.append( C_ALIAS_TPL_MR_DOC_PRVT_MOV + "." + C_OPERN_CODE + ", " );
      query.append( C_ALIAS_TO3_PRODUCT_ACCOUNT + "." + C_CUR_ACCT_NBR + ", " );
      query.append( C_ALIAS_TO3_PRODUCT_ACCOUNT + "." + C_CUST_NBR + ", " );
      query.append( C_ALIAS_TPL_CUSTOMER_PRVT + "." + C_CUST_FULL_NAME_TEXT
                    + " " );
      query.append( " FROM " );
      query.append( C_TPL_MR_DOC_PRVT_MOV + " " + C_ALIAS_TPL_MR_DOC_PRVT_MOV
                    + ", " );
      query.append( C_TO3_PRODUCT_ACCOUNT + " " + C_ALIAS_TO3_PRODUCT_ACCOUNT
                    + ", " );
      query.append( C_TPL_CUSTOMER_PRVT + " " + C_ALIAS_TPL_CUSTOMER_PRVT
                    + ", " );
      query.append( C_TBG_POINT_ACCT_INVST + " " + C_ALIAS_TBG_POINT_ACCT_INVST );
      query.append( " WHERE " );
      query.append( C_ALIAS_TPL_MR_DOC_PRVT_MOV + "." + C_PROD_ACCT_CODE
                    + " = " + C_ALIAS_TO3_PRODUCT_ACCOUNT + "."
                    + C_PROD_ACCT_CODE + " AND " );
      query.append( C_ALIAS_TPL_MR_DOC_PRVT_MOV + "." + C_PROD_UNDER_ACCT_CODE
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
        criteria = criteria + C_ALIAS_TPL_MR_DOC_PRVT_MOV + "." + C_MR_DOC_CODE
                   + " = ? AND ";
      }

      if ( mrDocText_ != null && !mrDocText_.equals( "" ) )
      {
        criteria = criteria + " UPPER(" + C_MR_DOC_TEXT + ") LIKE ? AND ";
      }

      if ( mrInvstCurAcctInd_ != null )
      {
        criteria = criteria + C_MR_INVST_CUR_ACCT_IND + " = ?  AND ";
      }

      if ( lastUpdUserId_ != null && !lastUpdUserId_.equals( "" ) )
      {
        criteria = criteria + " UPPER(" + C_ALIAS_TPL_MR_DOC_PRVT_MOV + "."
                   + C_LAST_UPD_USER_ID + ") LIKE ?  AND ";
      }

      if ( lastUpdDate_ != null )
      {
        criteria = criteria + " TRUNC (" + C_ALIAS_TPL_MR_DOC_PRVT_MOV + "."
                   + C_LAST_UPD_DATE + ", \'DD\') = ? AND ";
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
      
	  if ( prodAcctCode_ != null )
	  {
		  criteria = criteria + C_ALIAS_TO3_PRODUCT_ACCOUNT + "."
						 + C_PROD_ACCT_CODE + " = ? AND ";
	  }
	  
	  if ( prodAcctUnderCode_ != null )
	  {
		  criteria = criteria + C_ALIAS_TO3_PRODUCT_ACCOUNT + "."
							   + C_PROD_UNDER_ACCT_CODE + " = ? AND ";
	  }


      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( " AND " + criteria );
      }

      query.append( " ORDER BY " + C_ALIAS_TPL_CUSTOMER_PRVT + "."
                    + C_CUST_FULL_NAME_TEXT );
      query.append( ",  " + C_ALIAS_TO3_PRODUCT_ACCOUNT + "." + C_CUR_ACCT_NBR  );
      query.append( ",  " + C_ALIAS_TPL_MR_DOC_PRVT_MOV + "." + C_MR_DOC_CODE  );
      
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

      if ( mrInvstCurAcctInd_ != null )
      {
        preparedStatement.setString( count++, mrInvstCurAcctInd_ );
      }

      if ( lastUpdUserId_ != null && !lastUpdUserId_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + lastUpdUserId_.toUpperCase() + "%" );
      }

      if ( lastUpdDate_ != null )
      {
        preparedStatement.setDate( count++, new java.sql.Date( lastUpdDate_.getTime() ) );
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
        preparedStatement.setString( count++, curAcctNbr_.toString() );
      }
      
	  if ( prodAcctCode_ != null )
	  {
		preparedStatement.setString( count++, prodAcctCode_.toString() );
	  }
	  
	  if ( prodAcctUnderCode_ != null )
	  {
		preparedStatement.setString( count++, prodAcctUnderCode_.toString() );
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
  
  public ArrayList list(BigInteger prodAcctCode_,BigInteger prodAcctUnderCode_ )
  {
		TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO;
		TplMrDocPrvtMovEntity mrDocPrvtMovEntity;
		ArrayList mrDocPrvtMovEntities = new ArrayList();
		DataSetRow row;

		DataSet result =
			this.list(
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				prodAcctCode_,
				prodAcctUnderCode_);

		for (int indexRow = 0; indexRow < result.size(); indexRow++)
		{
			row = result.getRow( indexRow );
			mrDocPrvtMovEntity = new TplMrDocPrvtMovEntity();
			mrDocPrvtMovEntityVO = (TplMrDocPrvtMovEntityVO) mrDocPrvtMovEntity.getData();

			mrDocPrvtMovEntityVO.setMrDocCode(BigInteger.valueOf(row.getBigDecimalByName(C_MR_DOC_CODE).longValue()));				
				
			if(row.getStringByName(C_MR_DOC_TEXT) != null){
				mrDocPrvtMovEntityVO.setMrDocText(row.getStringByName(C_MR_DOC_TEXT));
			}
			
			if(row.getStringByName(C_LAST_UPD_USER_ID) != null){
			  mrDocPrvtMovEntityVO.setLastUpdUserId(row.getStringByName(C_LAST_UPD_USER_ID));
			}
			
			if(row.getStringByName(C_LAST_UPD_DATE) != null){
			  mrDocPrvtMovEntityVO.setLastUpdDate(row.getDateByName(C_LAST_UPD_DATE));
			}
			
			if(row.getStringByName(C_OPERN_CODE) != null){
			  mrDocPrvtMovEntityVO.setOpernCode(row.getStringByName(C_OPERN_CODE));
			}
			
			mrDocPrvtMovEntityVO.setProdAcctCode(BigInteger.valueOf(row.getBigDecimalByName(C_PROD_ACCT_CODE).longValue()));
			mrDocPrvtMovEntityVO.setProdUnderAcctCode(BigInteger.valueOf(row.getBigDecimalByName(C_PROD_UNDER_ACCT_CODE).longValue()));
			
			mrDocPrvtMovEntities.add(mrDocPrvtMovEntity);

		}

		return mrDocPrvtMovEntities;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocPrvtMovDAO#update(com.citibank.ods.entity.pl.TplMrDocPrvtMovEntity)
   */
  public void update( TplMrDocPrvtMovEntity mrDocPrvtMovEntity_ )
  {
    TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) mrDocPrvtMovEntity_.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " );
      query.append( C_TPL_MR_DOC_PRVT_MOV );
      query.append( " SET " );
      query.append( C_MR_DOC_TEXT + " = ?, " );
      query.append( C_MR_INVST_CUR_ACCT_IND + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?," );
      query.append( C_OPERN_CODE + " = ? " );
      query.append( " WHERE " );
      query.append( C_MR_DOC_CODE + " = ? AND " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString( 1, mrDocPrvtMovEntityVO.getMrDocText() );

      preparedStatement.setString( 2, mrDocPrvtMovEntityVO.getMrInvstCurAcctInd() );

      preparedStatement.setString( 3, mrDocPrvtMovEntityVO.getLastUpdUserId() );

      preparedStatement.setTimestamp(
                              4,
                              new Timestamp(
                                             mrDocPrvtMovEntityVO.getLastUpdDate().getTime() ) );

      preparedStatement.setString( 5, mrDocPrvtMovEntityVO.getOpernCode() );

      preparedStatement.setLong( 6, mrDocPrvtMovEntityVO.getMrDocCode().longValue() );

      preparedStatement.setLong( 7, mrDocPrvtMovEntityVO.getProdAcctCode().longValue() );

      preparedStatement.setLong(
                         8,
                         mrDocPrvtMovEntityVO.getProdUnderAcctCode().longValue() );

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

  /**
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocPrvtMovDAO#delete(com.citibank.ods.entity.pl.TplMrDocPrvtMovEntity)
   */
  public void delete( TplMrDocPrvtMovEntity mrDocPrvtMovEntity_ )
  {
    TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) mrDocPrvtMovEntity_.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "DELETE FROM " );
      query.append( C_TPL_MR_DOC_PRVT_MOV );
      query.append( " WHERE " );
      query.append( C_MR_DOC_CODE + " = ? AND " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, mrDocPrvtMovEntityVO.getMrDocCode().longValue() );

      preparedStatement.setLong( 2, mrDocPrvtMovEntityVO.getProdAcctCode().longValue() );

      preparedStatement.setLong(
                         3,
                         mrDocPrvtMovEntityVO.getProdUnderAcctCode().longValue() );

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

  /**
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocPrvtMovDAO#copyFromMovToCurrent(com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity,
   *      java.util.Date, java.lang.String)
   */
  public void copyFromMovToCurrent(
                                   BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_,
                                   Date lastAuthDate, String lastAuthUserId )
  {
    TplMrDocPrvtMovEntity mrDocPrvtMovEntity = ( TplMrDocPrvtMovEntity ) findById( baseTplMrDocPrvtEntity_ );
	

    // Monta entidade de Currente com dados de Movimento
    TplMrDocPrvtEntity mrDocPrvtEntity = new TplMrDocPrvtEntity(
                                                                 mrDocPrvtMovEntity,
                                                                 lastAuthDate,
                                                                 lastAuthUserId );

    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
    TplMrDocPrvtDAO mrDocPrvtDAO = odsDAOFactory.getTplMrDocPrvtDAO();

    // Insere dados de Movemento na tabela Corrente.
    mrDocPrvtDAO.insert( mrDocPrvtEntity );
  }
  
  public BigInteger getNextVal()
  {
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	ResultSet resultSet = null;
	StringBuffer query = new StringBuffer();
	BigInteger nextVal = null;

	try
	{
	  connection = OracleODSDAOFactory.getConnection();
		
	  query.append("select pl.sq_mr_prvt_code.nextval id from dual");
	
	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  preparedStatement.replaceParametersInQuery(query.toString());
	  resultSet = preparedStatement.executeQuery();

	  if ( resultSet.next() )
	  {		  
		nextVal = new BigInteger( resultSet.getString( "id" ));		  
	  }

	  return nextVal;
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
 * @see com.citibank.ods.persistence.pl.dao.BaseTplMrDocPrvtDAO#findActive(com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity)
 */
public BaseTplMrDocPrvtEntity findActive(BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_) {
	// TODO Auto-generated method stub
	return null;
}

  
  
}