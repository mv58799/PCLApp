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
import com.citibank.ods.entity.pl.TplMrDocPrvtEntity;
import com.citibank.ods.entity.pl.TplMrDocPrvtHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplMrDocPrvtEntityVO;
import com.citibank.ods.modules.client.mrdocprvt.ContactCustKey;
import com.citibank.ods.persistence.pl.dao.TplMrDocCallbackDAO;
import com.citibank.ods.persistence.pl.dao.TplMrDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplMrDocPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Implementação da interface para acesso ao banco de dados de Memória de Risco.
 */
public class OracleTplMrDocPrvtDAO extends BaseOracleTplMrDocPrvtDAO implements
    TplMrDocPrvtDAO
{
  // Data e Hora que o Usuario Aprovou o Registro Cadastrado.
  private static final String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  // Codigo do Usuario que Aprovou o Cadastro do Registro.
  private static final String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  // Codigo Documento MR
  private static final String C_MR_DOC_PRVT = "PRVT_MR_CODE";

  // Status do Registro: 'S' (Ativo), 'N' (Inativo), 'A' (Aguardando
  // Aprovacao)
  private static final String C_REC_STAT_CODE = "REC_STAT_CODE";

  // Tabela TPL_MR_DOC_PRVT
  private static final String C_TPL_MR_DOC_PRVT = C_PL_SCHEMA + "TPL_MR_PRVT";

  // Tabela TPL_MR_DOC_PRVT_MOV
  private static final String C_TPL_MR_DOC_PRVT_MOV = C_PL_SCHEMA
                                                      + "TPL_MR_PRVT_MOV";

  // Tabela TPL_CUSTOMER_PRVT
  public static final String C_TPL_CUSTOMER_PRVT = C_PL_SCHEMA
                                                   + "TPL_CUSTOMER_PRVT";

  // Tabela TO3_PRODUCT_ACCOUNT
  public static final String C_TO3_PRODUCT_ACCOUNT = C_O3_SCHEMA
                                                     + "TO3_PRODUCT_ACCOUNT";
  //CPF Cliente
  public static final String C_CUST_CPF_CNPJ_NBR = "CUST_CPF_CNPJ_NBR";
  
  //Agencia Bancaria
  public static final String C_AGN_NBR = "AGN_NBR";
  
  //Numero do Banco
  public static final String C_BANK_NBR = "BANK_NBR"; 
  
  //Número da conta corrente
  public static final String C_CUR_ACCT_NBR = "CUR_ACCT_NBR";

  //Nome do cliente
  public static final String C_CUST_FULL_NAME_TEXT = "CUST_FULL_NAME_TEXT";

  //Número do cliente
  public static final String C_CUST_NBR = "CUST_NBR";

  //Conta CCI
  public static final String C_MR_INVST_CUR_ACCT = "PRVT_MR_INVST_CUR_ACCT";

  //Indicador de Conta CCI - Yes
  public static final String C_IND_YES = "'S'";

  //Número da conta investimento
  private static final String C_INVST_CUR_ACCT_NBR = "INVST_CUR_ACCT_NBR";
  
  //Tipo de Operação
  private static final String C_TIPO = "TIPO";

  //tabela TBG_POINT_ACCT_INVST
  private static final String C_TBG_POINT_ACCT_INVST = C_BG_SCHEMA
                                                       + "TBG_POINT_ACCT_INVST";

  //tabela TBG_POINT_ACCT_INVST - Alias
  private static final String C_ALIAS_TBG_POINT_ACCT_INVST = "CCI";

  // Tabela TPL_MR_DOC_PRVT_MOV - Alias
  private static final String C_ALIAS_TPL_MR_DOC_PRVT = "MR";

  // Tabela TPL_CUSTOMER_PRVT - Alias
  public static final String C_ALIAS_TPL_CUSTOMER_PRVT = "CUST";

  // Tabela TO3_PRODUCT_ACCOUNT - Alias
  public static final String C_ALIAS_TO3_PRODUCT_ACCOUNT = "PROD";

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplMrDocPrvtDAO#find(com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity)
   */
  public BaseTplMrDocPrvtEntity find(
                                     BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_ )
  {

    TplContactCustEntity contactCustEntity;
    TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) baseTplMrDocPrvtEntity_.getData();
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
      query.append( C_MR_DOC_PRVT + ", " );
      query.append( C_MR_DOC_TEXT + ", " );
      query.append( C_MR_INVST_CUR_ACCT_IND + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_MR_DOC_PRVT );
      query.append( " WHERE " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? AND " );
      query.append( C_REC_STAT_CODE + " = 'A' " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, mrDocPrvtEntityVO.getProdAcctCode().longValue() );
      preparedStatement.setLong( 2,
                         mrDocPrvtEntityVO.getProdUnderAcctCode().longValue() );

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
      TplMrDocCallbackDAO mrDocCallbackDAO = odsDAOFactory.getTplMrDocCallbackDAO();

	  TplMrDocPrvtEntityVO vo = (TplMrDocPrvtEntityVO )mrDocPrvtEntity.getData();
	  ArrayList contactCustEntitiesArray = ( ArrayList ) mrDocCallbackDAO.findAssociatedContactCustByPK(vo.getMrDocPrvt(),
	                                                                                                    mrDocPrvtEntityVO.getProdAcctCode(),
	                                                                                                    mrDocPrvtEntityVO.getProdUnderAcctCode() );

 

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
  
  public BaseTplMrDocPrvtEntity findActive(BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_ )
	{

	  TplContactCustEntity contactCustEntity;
	  TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) baseTplMrDocPrvtEntity_.getData();
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
	  query.append( C_MR_DOC_PRVT + ", " );
	  query.append( C_MR_DOC_TEXT + ", " );
	  query.append( C_MR_INVST_CUR_ACCT_IND + ", " );
	  query.append( C_LAST_AUTH_USER_ID + ", " );
	  query.append( C_LAST_AUTH_DATE + ", " );
	  query.append( C_LAST_UPD_USER_ID + ", " );
	  query.append( C_LAST_UPD_DATE + ", " );
	  query.append( C_REC_STAT_CODE + ", " );
	  query.append( C_PROD_ACCT_CODE + ", " );
	  query.append( C_PROD_UNDER_ACCT_CODE );
	  query.append( " FROM " );
	  query.append( C_TPL_MR_DOC_PRVT );
	  query.append( " WHERE " );
	  query.append( C_PROD_ACCT_CODE + " = ? AND " );
	  query.append( C_PROD_UNDER_ACCT_CODE + " = ? AND " );
	  query.append( C_REC_STAT_CODE + " = 'A'	 " );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

	  preparedStatement.setLong( 1, mrDocPrvtEntityVO.getProdAcctCode().longValue() );
	  preparedStatement.setLong( 2,
						 mrDocPrvtEntityVO.getProdUnderAcctCode().longValue() );

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
	  TplMrDocCallbackDAO mrDocCallbackDAO = odsDAOFactory.getTplMrDocCallbackDAO();

	  TplMrDocPrvtEntityVO vo = (TplMrDocPrvtEntityVO )mrDocPrvtEntity.getData();
	  ArrayList contactCustEntitiesArray = ( ArrayList ) mrDocCallbackDAO.findAssociatedContactCustByPK(vo.getMrDocPrvt(),
																											mrDocPrvtEntityVO.getProdAcctCode(),
																											mrDocPrvtEntityVO.getProdUnderAcctCode() );

 

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
		throw new UnexpectedException( e.getErrorCode(),C_ERROR_EXECUTING_STATEMENT, e );
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

    TplMrDocPrvtEntityVO mrDocPrvtEntityVO;
    TplMrDocPrvtEntity mrDocPrvtEntity;
    ArrayList mrDocPrvtEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        mrDocPrvtEntity = new TplMrDocPrvtEntity();
        mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) mrDocPrvtEntity.getData();

        mrDocPrvtEntityVO.setMrDocPrvt( new BigInteger(
                                                        resultSet_.getString( C_MR_DOC_PRVT ) ) );

        if ( resultSet_.getString( C_MR_DOC_TEXT ) != null )
        {
          mrDocPrvtEntityVO.setMrDocText( resultSet_.getString( C_MR_DOC_TEXT ) );
        }

        if ( resultSet_.getString( C_MR_INVST_CUR_ACCT_IND ) != null )
        {
          mrDocPrvtEntityVO.setMrInvstCurAcctInd( resultSet_.getString( C_MR_INVST_CUR_ACCT_IND ) );
        }

        if ( resultSet_.getString( C_LAST_AUTH_USER_ID ) != null )
        {
          mrDocPrvtEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );
        }

        if ( resultSet_.getTimestamp( C_LAST_AUTH_DATE ) != null )
        {
          mrDocPrvtEntityVO.setLastAuthDate( resultSet_.getTimestamp( C_LAST_AUTH_DATE ) );
        }

        if ( resultSet_.getString( C_LAST_UPD_USER_ID ) != null )
        {
          mrDocPrvtEntityVO.setLastUpdUserId( resultSet_.getString( C_LAST_UPD_USER_ID ) );
        }

        if ( resultSet_.getTimestamp( C_LAST_UPD_DATE ) != null )
        {
          mrDocPrvtEntityVO.setLastUpdDate( resultSet_.getTimestamp( C_LAST_UPD_DATE ) );
        }

        if ( resultSet_.getString( C_REC_STAT_CODE ) != null )
        {
          mrDocPrvtEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );
        }

        mrDocPrvtEntityVO.setProdAcctCode( new BigInteger(
                                                           resultSet_.getString( C_PROD_ACCT_CODE ) ) );

        mrDocPrvtEntityVO.setProdUnderAcctCode( new BigInteger(
                                                                resultSet_.getString( C_PROD_UNDER_ACCT_CODE ) ) );

        mrDocPrvtEntities.add( mrDocPrvtEntity );
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
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocPrvtDAO#list(java.math.BigInteger,
   *      java.lang.String, java.lang.String, java.math.BigInteger,
   *      java.math.BigInteger)
   */
  public DataSet list( String custFullNameText_, String invstCurAcctNbr_,  
  					   BigInteger curAcctNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

	  query.append( "SELECT TIPO, ");
	  query.append( "MODULE_CODE, ");
	  query.append( "MODULE_SUB_CODE, ");
	  query.append( "PROD_ACCT_CODE, ");
	  query.append( "PROD_UNDER_ACCT_CODE, ");       
      query.append( " CUST_FULL_NAME_TEXT, ");       
      query.append( " DECODE(AGN_NBR,0,NULL,AGN_NBR) AGN_NBR, ");       
	  query.append( " DECODE(BANK_NBR,0,NULL,BANK_NBR) BANK_NBR, ");
	  query.append( " DECODE(CUR_ACCT_NBR_BENE, 0, NULL, CUR_ACCT_NBR_BENE) CUR_ACCT_NBR_BENE, ");       
	  query.append( " CUR_ACCT_NBR, ");       
	  query.append( " INVST_CUR_ACCT_NBR, ");       
	  query.append( " DECODE(BENE_CPF_CNPJ_NBR,0,NULL,BENE_CPF_CNPJ_NBR) BENE_CPF_CNPJ_NBR, ");    
	  query.append( " BENE_NAME_TEXT, ");       
	  query.append( " LAST_UPD_USER_ID, ");       
	  query.append( " LAST_UPD_DATE, ");       
	  query.append( " LAST_AUTH_USER_ID, ");       
	  query.append( " LAST_AUTH_DATE, ");       
	  query.append( " REC_STAT_CODE ");       
	  query.append( " FROM( ");       
	  query.append( " SELECT 'MR' TIPO, ");                  
	  query.append( " MR.PRVT_MR_CODE MODULE_CODE, ");
	  query.append( " 0 MODULE_SUB_CODE, ");	  
	  query.append( " MR.PROD_ACCT_CODE, ");
	  query.append( " MR.PROD_UNDER_ACCT_CODE, ");
	  query.append( " CUST.CUST_FULL_NAME_TEXT, ");       
	  query.append( " 0 AGN_NBR, ");       
	  query.append( " 0 BANK_NBR, ");
	  query.append( " 0 CUR_ACCT_NBR_BENE, ");
	  query.append( " CUR_ACCT.CUR_ACCT_NBR, ");       
	  query.append( " POINT.INVST_CUR_ACCT_NBR, ");       
	  query.append( " 0 BENE_CPF_CNPJ_NBR, ");
	  query.append( " '' BENE_NAME_TEXT, ");         
	  query.append( " MR.LAST_UPD_USER_ID, ");       
	  query.append( " MR.LAST_UPD_DATE, ");       
	  query.append( " MR.LAST_AUTH_USER_ID, ");       
	  query.append( " MR.LAST_AUTH_DATE, ");       
	  query.append( " MR.REC_STAT_CODE ");       
	  query.append( " FROM PL.TPL_MR_PRVT MR, ");                   
	  query.append( " PL.TPL_CUSTOMER_PRVT cust, ");       
	  query.append( " (SELECT PROD_ACCT_CODE, ");       
	  query.append( " PROD_UNDER_ACCT_CODE, ");       
	  query.append( " CUST_NBR, ");       
	  query.append( " RELTN_NBR, ");       
	  query.append( " CUR_ACCT_NBR, ");       
	  query.append( " PROD_CODE, ");       
	  query.append( " SYS_SEG_CODE ");       
	  query.append( " FROM O3.TO3_PRODUCT_ACCOUNT ");       
	  query.append( " WHERE PROD_CODE = '010' ");       
	  query.append( " AND SYS_SEG_CODE = 1 ");       
	  query.append( " AND SYS_CODE = 'DA') CUR_ACCT, ");       
	  query.append( " BG.TBG_POINT_ACCT_INVST POINT ");       
	  query.append( " WHERE ");       
	  query.append( " MR.PROD_ACCT_CODE = CUR_ACCT.PROD_ACCT_CODE ");       
	  query.append( " AND MR.PROD_UNDER_ACCT_CODE = CUR_ACCT.PROD_UNDER_ACCT_CODE ");       
	  query.append( " AND CUR_ACCT.CUST_NBR = CUST.CUST_NBR ");       
	  query.append( " AND CUR_ACCT.CUR_ACCT_NBR = POINT.CUR_ACCT_NBR(+) ");       
	  query.append( " AND MR.REC_STAT_CODE = 'A' ");       
	  query.append( " UNION ALL ");       
	  query.append( " SELECT 'IP' TIPO, ");
	  query.append( " IPDOCPRVT.PRMNT_INSTR_CODE MODULE_CODE, ");
	  query.append( " IPDATA.PRMNT_INSTR_TRF_DATA_CODE MODULE_SUB_CODE, ");	  
	  query.append( " 0 PROD_ACCT_CODE, ");
	  query.append( " 0 PROD_UNDER_ACCT_CODE, ");
	  query.append( " CUST.CUST_FULL_NAME_TEXT, ");       
	  query.append( " IPDATA.AGN_NBR, ");       
	  query.append( " IPDATA.BANK_NBR, ");  
	  query.append( " IPDATA.CUR_ACCT_NBR CUR_ACCT_NBR_BENE, ");     
	  query.append( " CUR_ACCT.CUR_ACCT_NBR, ");       
	  query.append( " POINT.INVST_CUR_ACCT_NBR, ");       
	  query.append( " IPDATA.BENE_CPF_CNPJ_NBR, ");       
	  query.append( " IPDATA.BENE_NAME_TEXT, ");
	  query.append( " IPDOCPRVT.LAST_UPD_USER_ID, ");       
	  query.append( " IPDOCPRVT.LAST_UPD_DATE, ");       
	  query.append( " IPDOCPRVT.LAST_AUTH_USER_ID, ");       
	  query.append( " IPDOCPRVT.LAST_AUTH_DATE, ");       
	  query.append( " IPDOCPRVT.REC_STAT_CODE ");       
	  query.append( " FROM PL.TPL_PRMNT_INSTR_PRVT IPDOCPRVT, ");        
	  query.append( " PL.TPL_CUSTOMER_PRVT CUST, ");       
	  query.append( " PL.TPL_PRMNT_INSTR_DATA_TRF IPDATA, ");       
	  query.append( " PL.TPL_CUR_ACCT_PRMNT_INSTR IP_CUR_ACCT, ");       
	  query.append( " (SELECT PROD_ACCT_CODE, ");       
	  query.append( " PROD_UNDER_ACCT_CODE, ");       
	  query.append( " CUST_NBR, ");       
	  query.append( " RELTN_NBR, ");       
	  query.append( " CUR_ACCT_NBR, ");       
	  query.append( " PROD_CODE, ");       
	  query.append( " SYS_SEG_CODE ");       
	  query.append( " FROM O3.TO3_PRODUCT_ACCOUNT ");       
	  query.append( " WHERE PROD_CODE = '010' ");       
	  query.append( " AND SYS_SEG_CODE = 1 ");       
	  query.append( " AND SYS_CODE = 'DA') CUR_ACCT, ");       
	  query.append( " BG.TBG_POINT_ACCT_INVST POINT ");       
	  query.append( " WHERE IPDOCPRVT.PRMNT_INSTR_CODE = IP_CUR_ACCT.PRMNT_INSTR_CODE ");       
	  query.append( " AND IP_CUR_ACCT.PROD_ACCT_CODE = CUR_ACCT.PROD_ACCT_CODE ");       
	  query.append( " AND IP_CUR_ACCT.PROD_UNDER_ACCT_CODE = CUR_ACCT.PROD_UNDER_ACCT_CODE ");       
	  query.append( " AND CUR_ACCT.CUR_ACCT_NBR = POINT.CUR_ACCT_NBR(+) ");       
	  query.append( " AND IPDOCPRVT.CUST_NBR = CUST.CUST_NBR ");       
	  query.append( " AND IPDOCPRVT.PRMNT_INSTR_CODE = IPDATA.PRMNT_INSTR_CODE ");            
	  query.append( " AND IPDOCPRVT.REC_STAT_CODE = 'A' ");       
	  query.append( " ) "); 
	  query.append( " WHERE " );
	  query.append( "REC_STAT_CODE <> 'I'" );
	  
	  String criteria = "";
	  
	 if ( custFullNameText_ != null && !( custFullNameText_.equals( "" ) ) )
	 {
		criteria = criteria + "AND UPPER (CUST_FULL_NAME_TEXT ) LIKE ( UPPER (?) ) ";
	 }
	 if ( curAcctNbr_ != null && curAcctNbr_.longValue() != 0 )
	 {
		criteria = criteria + "AND CUR_ACCT_NBR = ?  ";
	 }
	 if ( invstCurAcctNbr_ != null && !(invstCurAcctNbr_.equals("") ) )
	 {
		criteria = criteria + " AND UPPER (INVST_CUR_ACCT_NBR ) LIKE ( ? ) ";
	 }
	 
	 if(criteria.length() > 0){
		query.append(criteria);
	 }
	 
	 query.append( " ORDER BY CUR_ACCT_NBR " );
		
     preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

	  if ( custFullNameText_ != null && !custFullNameText_.equals( "" ) )
	  {
	    preparedStatement.setString( count++, "%" + custFullNameText_.toUpperCase() + "%" );
	  }	  	  
	  
      if ( curAcctNbr_ != null && curAcctNbr_.longValue() != 0 )
	  {
		preparedStatement.setLong( count++, curAcctNbr_.longValue() );
	  }
	
	  if ( invstCurAcctNbr_ != null && !invstCurAcctNbr_.equals( "" ) )
	  {
		preparedStatement.setString( count++, "%" + invstCurAcctNbr_.toUpperCase() + "%" );
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

    return rsds;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocPrvtDAO#getNextMrDocCode()
   */
  public Integer getNextMrDocCode()
  {

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    Integer nextVal = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "select max(ult) as ultimo from ((select max(prvt_mr_code) as ult from pl.tpl_mr_prvt) union all (select max(prvt_mr_code) as ult from pl.tpl_mr_prvt_mov))");

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.next() )
      {
        nextVal = new Integer( resultSet.getInt( "ultimo" )+1 );
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

  /**
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocPrvtDAO#existsActive(com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity)
   */
  public boolean existsActive( BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_ )
  {
    boolean exists = false;

    try
    {
      TplMrDocPrvtEntity mrDocPrvtEntity = ( TplMrDocPrvtEntity ) find( baseTplMrDocPrvtEntity_ );
      TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) mrDocPrvtEntity.getData();
      if ( mrDocPrvtEntityVO.getRecStatCode().equals( "A" ) )
      {
        exists = true;
      }
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocPrvtDAO#exists(com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity)
   */
  public boolean exists( BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_ )
  {
    boolean exists = false;

    try
    {
      find( baseTplMrDocPrvtEntity_ );
      exists = true;
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocPrvtDAO#copyFromCurrentToHist(com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity,
   *      java.util.Date)
   */
  public void copyFromCurrentToHist(
                                    BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_,
                                    Date mrDocRefDate_ )
  {
    BaseTplMrDocPrvtEntity mrDocPrvtEntity = this.findById( baseTplMrDocPrvtEntity_ );
    TplMrDocPrvtHistEntity mrDocPrvtHistEntity = new TplMrDocPrvtHistEntity(
                                                                             ( TplMrDocPrvtEntity ) mrDocPrvtEntity,
                                                                             mrDocRefDate_ );

    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
    TplMrDocPrvtHistDAO mrDocPrvtHistDAO = odsDAOFactory.getTplMrDocPrvtHistDAO();
    mrDocPrvtHistDAO.insert( mrDocPrvtHistEntity );
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocPrvtDAO#update(com.citibank.ods.entity.pl.TplMrDocPrvtEntity)
   */
  public void update( TplMrDocPrvtEntity mrDocPrvtEntity_ )
  {
    TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) mrDocPrvtEntity_.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " );
      query.append( C_TPL_MR_DOC_PRVT );
      query.append( " SET " );
      query.append( C_MR_DOC_TEXT + " = ?, " );
      query.append( C_MR_INVST_CUR_ACCT_IND + " = ?, " );
      query.append( C_LAST_AUTH_USER_ID + " = ?, " );
      query.append( C_LAST_AUTH_DATE + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_REC_STAT_CODE + " = ? " );
      query.append( " WHERE " );
      query.append( C_MR_DOC_PRVT + " = ? AND " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString( 1, mrDocPrvtEntityVO.getMrDocText() );

      preparedStatement.setString( 2, mrDocPrvtEntityVO.getMrInvstCurAcctInd() );

      preparedStatement.setString( 3, mrDocPrvtEntityVO.getLastAuthUserId() );

      preparedStatement.setTimestamp(
                              4,
                              new Timestamp(
                                             mrDocPrvtEntityVO.getLastAuthDate().getTime() ) );

      preparedStatement.setString( 5, mrDocPrvtEntityVO.getLastUpdUserId() );

      preparedStatement.setTimestamp(
                              6,
                              new Timestamp(
                                             mrDocPrvtEntityVO.getLastUpdDate().getTime() ) );

      preparedStatement.setString( 7, mrDocPrvtEntityVO.getRecStatCode() );

      preparedStatement.setLong( 8, mrDocPrvtEntityVO.getMrDocPrvt().longValue() );

      preparedStatement.setLong( 9, mrDocPrvtEntityVO.getProdAcctCode().longValue() );

      preparedStatement.setLong( 10,
                         mrDocPrvtEntityVO.getProdUnderAcctCode().longValue() );

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
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocPrvtDAO#delete(com.citibank.ods.entity.pl.TplMrDocPrvtEntity)
   */
  public void delete( TplMrDocPrvtEntity mrDocPrvtEntity_ )
  {
    TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) mrDocPrvtEntity_.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "DELETE FROM " );
      query.append( C_TPL_MR_DOC_PRVT );
      query.append( " WHERE " );
      query.append( C_MR_DOC_PRVT + " = ? AND " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, mrDocPrvtEntityVO.getMrDocPrvt().longValue() );

      preparedStatement.setLong( 2, mrDocPrvtEntityVO.getProdAcctCode().longValue() );

      preparedStatement.setLong( 3,
                         mrDocPrvtEntityVO.getProdUnderAcctCode().longValue() );

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
   * @see com.citibank.ods.persistence.pl.dao.TplMrDocPrvtDAO#insert(com.citibank.ods.entity.pl.TplMrDocPrvtEntity)
   */
  public void insert( TplMrDocPrvtEntity mrDocPrvtEntity_ )
  {
    TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) mrDocPrvtEntity_.getData();

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_MR_DOC_PRVT + " ( " );
      query.append( C_MR_DOC_PRVT + ", " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE );

      int attCount = 0;
      if ( mrDocPrvtEntityVO.getMrDocText() != null
           && !mrDocPrvtEntityVO.getMrDocText().equals( "" ) )
      {
        query.append( ", " + C_MR_DOC_TEXT );
        attCount++;
      }

      if ( mrDocPrvtEntityVO.getMrInvstCurAcctInd() != null
           && !mrDocPrvtEntityVO.getMrInvstCurAcctInd().equals( "" ) )
      {
        query.append( ", " + C_MR_INVST_CUR_ACCT_IND );
        attCount++;
      }

      if ( mrDocPrvtEntityVO.getLastAuthUserId() != null
           && !mrDocPrvtEntityVO.getLastAuthUserId().equals( "" ) )
      {
        query.append( ", " + C_LAST_AUTH_USER_ID );
        attCount++;
      }

      if ( mrDocPrvtEntityVO.getLastAuthDate() != null )
      {
        query.append( ", " + C_LAST_AUTH_DATE );
        attCount++;
      }

      if ( mrDocPrvtEntityVO.getLastUpdUserId() != null
           && !mrDocPrvtEntityVO.getLastUpdUserId().equals( "" ) )
      {
        query.append( ", " + C_LAST_UPD_USER_ID );
        attCount++;
      }

      if ( mrDocPrvtEntityVO.getLastUpdDate() != null )
      {
        query.append( ", " + C_LAST_UPD_DATE );
        attCount++;
      }

      if ( mrDocPrvtEntityVO.getRecStatCode() != null
           && !mrDocPrvtEntityVO.getRecStatCode().equals( "" ) )
      {
        query.append( ", " + C_REC_STAT_CODE );
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

      preparedStatement.setLong( 1, mrDocPrvtEntityVO.getMrDocPrvt().longValue() );
      preparedStatement.setLong( 2, mrDocPrvtEntityVO.getProdAcctCode().longValue() );
      preparedStatement.setLong( 3,
                         mrDocPrvtEntityVO.getProdUnderAcctCode().longValue() );

      int index = 4;

      if ( mrDocPrvtEntityVO.getMrDocText() != null
           && !mrDocPrvtEntityVO.getMrDocText().equals( "" ) )
      {
        preparedStatement.setString( index++, mrDocPrvtEntityVO.getMrDocText() );
      }

      if ( mrDocPrvtEntityVO.getMrInvstCurAcctInd() != null
           && !mrDocPrvtEntityVO.getMrInvstCurAcctInd().equals( "" ) )
      {
        preparedStatement.setString( index++, mrDocPrvtEntityVO.getMrInvstCurAcctInd() );
      }

      if ( mrDocPrvtEntityVO.getLastAuthUserId() != null
           && !mrDocPrvtEntityVO.getLastAuthUserId().equals( "" ) )
      {
        preparedStatement.setString( index++, mrDocPrvtEntityVO.getLastAuthUserId() );
      }

      if ( mrDocPrvtEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                index++,
                                new Timestamp(
                                               mrDocPrvtEntityVO.getLastAuthDate().getTime() ) );
      }

      if ( mrDocPrvtEntityVO.getLastUpdUserId() != null
           && !mrDocPrvtEntityVO.getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString( index++, mrDocPrvtEntityVO.getLastUpdUserId() );
      }

      if ( mrDocPrvtEntityVO.getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                index++,
                                new Timestamp(
                                               mrDocPrvtEntityVO.getLastUpdDate().getTime() ) );
      }

      if ( mrDocPrvtEntityVO.getRecStatCode() != null
           && !mrDocPrvtEntityVO.getRecStatCode().equals( "" ) )
      {
        preparedStatement.setString( index++, mrDocPrvtEntityVO.getRecStatCode() );
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
   TplContactCustEntity contactCustEntity;
   TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) baseTplMrDocPrvtEntity_.getData();
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
	 query.append( C_MR_DOC_PRVT + ", " );
	 query.append( C_MR_DOC_TEXT + ", " );
	 query.append( C_MR_INVST_CUR_ACCT_IND + ", " );
	 query.append( C_LAST_AUTH_USER_ID + ", " );
	 query.append( C_LAST_AUTH_DATE + ", " );
	 query.append( C_LAST_UPD_USER_ID + ", " );
	 query.append( C_LAST_UPD_DATE + ", " );
	 query.append( C_REC_STAT_CODE + ", " );
	 query.append( C_PROD_ACCT_CODE + ", " );
	 query.append( C_PROD_UNDER_ACCT_CODE );
	 query.append( " FROM " );
	 query.append( C_TPL_MR_DOC_PRVT );
	 query.append( " WHERE " );
	 query.append( C_MR_DOC_CODE + " = ? " );	  

	 preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

	 preparedStatement.setLong( 1, mrDocPrvtEntityVO.getMrDocPrvt().longValue() );	  

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
	 TplMrDocCallbackDAO mrDocCallbackDAO = odsDAOFactory.getTplMrDocCallbackDAO();

	 TplMrDocPrvtEntityVO vo = (TplMrDocPrvtEntityVO )mrDocPrvtEntity.getData();
	 ArrayList contactCustEntitiesArray = ( ArrayList ) mrDocCallbackDAO.findAssociatedContactCustByPK(vo.getMrDocPrvt(),
																									   mrDocPrvtEntityVO.getProdAcctCode(),
																									   mrDocPrvtEntityVO.getProdUnderAcctCode() );

 

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

 /* (non-Javadoc)
 * @see com.citibank.ods.persistence.pl.dao.TplMrDocPrvtDAO#existsById(com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity)
 */
 public boolean existsById( BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_ )
 {
   boolean exists = false;

   try
   {
	 findById( baseTplMrDocPrvtEntity_ );
	 exists = true;
   }
   catch ( NoRowsReturnedException exception )
   {
	 exists = false;
   }

	   return exists;
 }
}