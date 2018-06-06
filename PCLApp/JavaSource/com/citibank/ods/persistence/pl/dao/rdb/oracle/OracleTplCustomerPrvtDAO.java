package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BusinessConstants;
import com.citibank.ods.entity.pl.BaseTplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplCustomerPrvtEntityVO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author l.braga,14/03/2007
 * 
 */

public class OracleTplCustomerPrvtDAO extends BaseOracleTplCustomerPrvtDAO
    implements TplCustomerPrvtDAO
{
  private static final String C_TABLE_COLUMNS = "CUST_NBR,CUST_CPF_CNPJ_NBR,CUST_SHORT_NAME_TEXT,CUST_KEY_NAME_TEXT,"
                                                + "CUST_TYPE_CODE,CUST_FULL_NAME_TEXT,CUST_ACTL_STAT_CODE,CUST_STAT_DATE,"
                                                + "CUST_ESTAB_DATE,CUST_ESTAB_TIME,CUST_ESTAB_OP_ID,CUST_CNR_LAST_MTH_AMT,"
                                                + "CUST_CNR_YTD_AMT,CUST_CNR_LAST_YR_AMT,CUST_CNR_LAST_SIX_MTH_CODE,"
                                                + "LAST_UPDATE_DATE,LAST_UPDATE_TIME,LAST_UPDATE_OP_ID,APPRV_DATE,APPRV_TIME,"
                                                + "CUST_INPUT_ORIG_CODE,CUST_DUP_CODE,CUST_CORP_BKR_APPL_PRFL_IND,"
                                                + "CUST_CORP_FUND_APPL_PRFL_IND,CUST_CETIP_NBR,CUST_BMF_NBR,CUST_BOVESPA_NBR,"
                                                + "CUST_BVRJ_NBR,CUST_SELIC_NBR,CUST_MKT_CAT_CODE,CUST_NO_CPF_IND,CUST_NAT_ID,"
                                                + "CUST_NAT_ID_APPL_DATE,CUST_NAT_ID_EMIT_CODE,CUST_NAT_ID_EMIT_NAME,"
                                                + "CUST_NAT_ID_EMIT_STATE_CODE,CUST_CIVIL_STAT_CODE,CUST_SEX_CODE,CUST_BIRTH_DATE,"
                                                + "CUST_BIRTH_CITY_TEXT,CUST_BIRTH_STATE_CODE,CUST_BIRTH_CNTRY_CODE,CUST_PROF_CODE,"
                                                + "CUST_EMPL_IND,CUST_DEPND_NBR,CUST_DEPND_NBR_DATE,CUST_OCCUP_CODE,"
                                                + "CUST_MGMT_INCO_MIN_SAL_COUNT,CUST_CHK_DATE,CUST_GRCARD_IND,CUST_SOC_SCTY_NBR,"
                                                + "CUST_CPF_OWN_IND,CUST_PARENT_LEVEL_IND,CUST_USA_CTZN_AUTH_IND,"
                                                + "CUST_USA_CTZN_AUTH_OP_ID,CUST_INDIV_PUBLIC_IND,CUST_CITI_GRP_TIE_IND,"
                                                + "CUST_BIRTH_CNTRY_CO_CODE,CUST_ACTY_AREA_CODE,CUST_NO_CGC_IND,CUST_FNDTN_DATE,"
                                                + "CUST_CO_TYPE_CODE,CUST_GRP_CODE,CUST_SUB_GRP_CODE,CUST_INTL_NBR,"
                                                + "CUST_IRRF_EXEMPT_IND,REC_STAT_CODE";

  public static String C_TPL_CUSTOMER_PRVT_CMPL = C_PL_SCHEMA
                                                  + "TPL_CUSTOMER_PRVT_CMPL";

  public static String C_TPL_CUSTOMER_PRVT_CMPL_MOV = C_PL_SCHEMA
												  + "TPL_CUSTOMER_PRVT_CMPL_MOV"; 
  
  public static String C_TBG_OFFICER = C_BG_SCHEMA + "TBG_OFFICER";

  public static String C_TPL_CUSTOMER_PRVT = C_PL_SCHEMA + "TPL_CUSTOMER_PRVT";

  public static String C_TPL_RELATION_PRVT = C_PL_SCHEMA + "TPL_RELATION_PRVT";

  public static String C_TO3_PRODUCT_ACCOUNT = C_O3_SCHEMA + "TO3_PRODUCT_ACCOUNT";

  public static String C_RELTN_NBR = "RELTN_NBR";
  
  public static final String C_ROLE_CUST_CODE = "ROLE_CUST_CODE";	

  public static String C_CUR_ACCT_NBR = "CUR_ACCT_NBR";
  
  public static String C_CUST_INVST_CUR_ACCT_NBR = "INVST_CUR_ACCT_NBR";

  public static String C_CUST_ALIAS = "CUST";
  
  public static String C_CUST_CMPL_MOV_ALIAS = "MOV"; 
  
  public static String C_CUST_2_ALIAS = "CUST_2";
  
  public static String C_CUST_3_ALIAS = "CUST_3";
  
  public static String C_CUST_4_ALIAS = "CUST_4";

  public static String C_CUST_CMPL_ALIAS = "CUSTCMPL";
  
  public static String C_CUST_POINT = "POINT";

  public static String C_OFFICER_ALIAS = "OFFC";

  public static String C_RELATION_ALIAS = "RELTN";

  public static String C_CUR_ACCT_ALIAS = "CUR_ACCT";

  public static String C_PROD_CODE = "PROD_CODE";

  public static String C_PROD_CODE_ACCT = "'010'";

  public static String C_SYS_SEG_CODE = "SYS_SEG_CODE";

  public static String C_SYS_SEG_CODE_ACCT = "1";

  public static String C_RELTN_CUST_1_NBR = "RELTN_CUST_1_NBR";
  
  public static String C_RELTN_CUST_2_NBR = "RELTN_CUST_2_NBR";
  
  public static String C_RELTN_CUST_3_NBR = "RELTN_CUST_3_NBR";
  
  public static String C_RELTN_CUST_4_NBR = "RELTN_CUST_4_NBR";

  public static String C_REC_STAT_CODE_ACTIVE = "'A'";

  public static final String C_PRVT_CUST_ATTD_STAT_CODE = "PRVT_CUST_ATTD_STAT_CODE";
  
  public static final String C_POINT_ACCT_INVST_POINT = C_BG_SCHEMA + "TBG_POINT_ACCT_INVST";
  
  /* 
   * RDIP - Inclusão do descritivo de risco 
   * Data: 05/01/2011
   * Responsável: Eversystems
   */
  public static final String C_RDIP_DESC_FULL_TEXT = "RDIP_DESC_FULL_TEXT";

  /*
   * Este método busca uma lista de Categoria de Risco do produto que se
   * enquadre com os critérios informados e que esteja com status ativo.
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO#listCustomerPrvt(java.math.BigInteger,
   *      java.lang.String, java.math.BigInteger)
   */
  public DataSet listCustomerPrvt( BigInteger custNbr_,
                                  String custFullNameText_,
	                              String custFullName2Text_, 
								  String custFullName3Text_, 
								  String custFullName4Text_,
                                  BigInteger custCpfCnpjNbr_,
	                              BigInteger curAcctNbr_, 
	                              String invstCurAcctNbr_, BigInteger reltnNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();
    String criteria;

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( " SELECT " );
      query.append( C_CUST_ALIAS + "." + C_CUST_NBR + ", " );
      query.append( C_CUST_ALIAS + "." + C_CUST_FULL_NAME_TEXT + ", " );
      query.append( C_CUST_ALIAS + "." + C_CUST_CPF_CNPJ_NBR + ", " );
      query.append( C_CUR_ACCT_ALIAS + "." + C_CUR_ACCT_NBR + ", " );
      query.append( C_RELATION_ALIAS + "." + C_RELTN_NBR );
      query.append( " FROM " );
      query.append( C_TPL_CUSTOMER_PRVT + " " + C_CUST_ALIAS + ", " );
      query.append( C_TPL_RELATION_PRVT + " " + C_RELATION_ALIAS + ", " );
      query.append( " ( SELECT " + C_CUST_NBR + ", " + C_RELTN_NBR + ", "
                    + C_CUR_ACCT_NBR + ", " + C_PROD_CODE + ", "
                    + C_SYS_SEG_CODE );
      query.append( " FROM " + C_TO3_PRODUCT_ACCOUNT );
      query.append( " WHERE " + C_PROD_CODE + " = " + C_PROD_CODE_ACCT );
      query.append( " AND " + C_SYS_SEG_CODE + " = " + C_SYS_SEG_CODE_ACCT
                    + " ) " + C_CUR_ACCT_ALIAS );
      query.append( " WHERE " + C_CUST_ALIAS + "." + C_CUST_NBR + " = "
                    + C_CUR_ACCT_ALIAS + "." + C_CUST_NBR + "(+)" );
      query.append( " AND " + C_CUST_ALIAS + "." + C_CUST_NBR + " = "
                    + C_RELATION_ALIAS + "." + C_RELTN_CUST_1_NBR + "(+)" );

      criteria = "";

      if ( custNbr_ != null && custNbr_.longValue() != 0 )
      {
        criteria = criteria + C_CUST_ALIAS + "." + C_CUST_NBR + " = ? AND ";
      }
      else
      {
        if ( custFullNameText_ != null && custFullNameText_.length() > 0 )
        {
          criteria = criteria + "UPPER(" + C_CUST_ALIAS + "."
                     + C_CUST_FULL_NAME_TEXT + ") like ? AND ";
        }

        if ( custCpfCnpjNbr_ != null && custCpfCnpjNbr_.longValue() != 0 )
        {
          criteria = criteria + C_CUST_ALIAS + "." + C_CUST_CPF_CNPJ_NBR
                     + " = ? AND ";
        }

        if ( reltnNbr_ != null && reltnNbr_.longValue() != 0 )
        {
          criteria = criteria + C_RELATION_ALIAS + "." + C_RELTN_NBR
                     + " = ? AND ";

        }

        if ( curAcctNbr_ != null && curAcctNbr_.longValue() != 0 )
        {
          criteria = criteria + C_CUR_ACCT_ALIAS + "." + C_CUR_ACCT_NBR
                     + " = ? AND ";
        }

      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( " AND " + criteria );
      }
      query.append( " ORDER BY " + C_CUST_ALIAS + "." + C_CUST_FULL_NAME_TEXT );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( custNbr_ != null && custNbr_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
      }
      else
      {
        if ( custFullNameText_ != null && custFullNameText_.length() > 0 )
        {
          preparedStatement.setString( count++, "%" + custFullNameText_.toUpperCase()
                                        + "%" );
        }

        if ( custCpfCnpjNbr_ != null && custCpfCnpjNbr_.longValue() != 0 )
        {
          preparedStatement.setLong( count++, custCpfCnpjNbr_.longValue() );
        }

        if ( reltnNbr_ != null && reltnNbr_.longValue() != 0 )
        {
          preparedStatement.setLong( count++, reltnNbr_.longValue() );

        }

        if ( curAcctNbr_ != null && curAcctNbr_.longValue() != 0 )
        {
          preparedStatement.setLong( count++, curAcctNbr_.longValue() );
        }
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

  /*
   * Este método busca uma lista de Clientes Private que se enquadre com os
   * criterios informados juntamente com os dados complementares caso exista e
   * que estes dados esteja com status diferente de inativado.
   * @see com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO#listCustomerPrvt(java.math.BigInteger,
   *      java.lang.String, java.math.BigInteger)
   */
  public DataSet list( BigInteger custNbr_, String custFullNameText_,
                       String custFullName2Text_, String custFullName3Text_, 
		    		   String custFullName4Text_, BigInteger curAcctNbr_, 
	                   String invstCurAcctNbr_, BigInteger custCpfCnpjNbr_, 
		    		   BigInteger reltnNbr_, BigInteger prvtCustNbr_,
                       BigInteger prvtKeyNbr_, String emNbr_,
                       BigInteger offcrNbr_, BigInteger wealthPotnlCode_,
                       BigInteger classCmplcCode_, String officerName_,
                       String custPrvtStatCode_, BigInteger prvtCustTypeCode_, 
                       String orderBy_ )

  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();
    String criteria;

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( " SELECT " );
	  query.append( C_CUST_POINT + "." + C_CUST_INVST_CUR_ACCT_NBR + ", " );
      query.append( "CASE WHEN "
                    + C_CUST_CMPL_ALIAS
                    + "."
                    + C_CUST_NBR
                    + " IS NULL THEN 0 ELSE 1 END as CMPL_DATA_BUTTON_CONTROL, " );
      query.append( C_CUST_ALIAS + "." + C_CUST_NBR + ", " );
      query.append( C_CUST_ALIAS + "." + C_CUST_FULL_NAME_TEXT + ", " );
      query.append( C_CUST_ALIAS + "." + C_CUST_CPF_CNPJ_NBR + ", " );
      query.append( C_CUR_ACCT_ALIAS + "." + C_CUR_ACCT_NBR + ", " );
      query.append( "DECODE(RELTN.RELTN_NBR, NULL,0,RELTN.RELTN_NBR) RELTN_NBR, " );
      // Informações referentes a dados complementares de cliente
      query.append( C_CUST_CMPL_ALIAS + "." + C_GLB_REVEN_SYS_OFFCR_NBR + ", " );
      query.append( C_CUST_CMPL_ALIAS + "." + C_PRVT_CUST_NBR + ", " );
      query.append( C_CUST_CMPL_ALIAS + "." + C_PRVT_KEY_NBR + ", " );
      query.append( C_CUST_CMPL_ALIAS + "." + C_LAST_AUTH_DATE + ", " );
      query.append( C_CUST_CMPL_ALIAS + "." + C_LAST_AUTH_USER_ID + ", " );
      query.append( C_CUST_CMPL_ALIAS + "." + C_LAST_UPD_DATE + ", " );
      query.append( C_CUST_CMPL_ALIAS + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( C_CUST_CMPL_ALIAS + "." + C_REC_STAT_CODE + ", " );
      query.append( C_CUST_CMPL_ALIAS + "." + C_WEALTH_POTNL_CODE + ", " );
      query.append( C_CUST_CMPL_ALIAS + "." + C_OFFCR_NBR + ", " );
      query.append( C_OFFICER_ALIAS + "." + C_OFFCR_NAME_TEXT + ", " );
	  query.append( C_CUST_CMPL_ALIAS + "." + C_CLASS_CMPLC_CODE + ", " );
      query.append( "DECODE(" + C_CUST_CMPL_MOV_ALIAS + "." + C_CUST_NBR + ", NULL, '0', '1') APROVACAO " );
      query.append( " FROM " );
	  query.append( C_POINT_ACCT_INVST_POINT + " "+C_CUST_POINT + ", ");
	  query.append( C_TPL_CUSTOMER_PRVT_CMPL + " " + C_CUST_CMPL_ALIAS + ", " );
	  query.append( C_TPL_CUSTOMER_PRVT_CMPL_MOV + " " + C_CUST_CMPL_MOV_ALIAS + ", " );
      query.append( C_TBG_OFFICER + " " + C_OFFICER_ALIAS + ", " );
      query.append( C_TPL_CUSTOMER_PRVT + " " + C_CUST_ALIAS + ", " );
      query.append( C_TPL_RELATION_PRVT + " " + C_RELATION_ALIAS + ", " );
      query.append( " ( SELECT " + C_CUST_NBR + ", " + C_RELTN_NBR + ", "
                    + C_CUR_ACCT_NBR + ", " + C_PROD_CODE + ", "
                    + C_SYS_SEG_CODE );
      query.append( " FROM " + C_TO3_PRODUCT_ACCOUNT );
      query.append( " WHERE " + C_PROD_CODE + " = " + C_PROD_CODE_ACCT );
      query.append( " AND " + C_SYS_SEG_CODE + " = " + C_SYS_SEG_CODE_ACCT );
      query.append( " AND SYS_CODE = 'DA') " + C_CUR_ACCT_ALIAS );
      query.append( " WHERE " + C_CUST_ALIAS + "." + C_CUST_NBR + " = "
                    + C_RELATION_ALIAS + "." + C_RELTN_CUST_1_NBR + "(+)" );
      query.append( " AND " + C_RELATION_ALIAS + "." + C_RELTN_NBR + " = "
                    + C_CUR_ACCT_ALIAS + "." + C_RELTN_NBR + "(+)" );
      query.append( " AND " + C_CUST_ALIAS + "." + C_CUST_NBR + " = "
                    + C_CUST_CMPL_ALIAS + "." + C_CUST_NBR + "(+)" );
      query.append( " AND " + C_CUST_CMPL_ALIAS + "." + C_OFFCR_NBR + " = "
                    + C_OFFICER_ALIAS + "." + C_OFFCR_NBR + "(+)" );
	  query.append( " AND " + C_CUST_ALIAS + "." + C_CUST_NBR + " = " +  C_CUST_CMPL_MOV_ALIAS + "." + C_CUST_NBR + "(+)" );      
      query.append( " AND (" + C_CUST_CMPL_ALIAS + "." + C_REC_STAT_CODE
                    + " = " + C_REC_STAT_CODE_ACTIVE + " OR "
                    + C_CUST_CMPL_ALIAS + "." + C_CUST_NBR + " IS NULL ) " );                    
	  query.append( " AND LPAD(" + C_CUR_ACCT_ALIAS + "." + C_CUR_ACCT_NBR
					+ " ,11,'0') = " + C_CUST_POINT+"." + C_CUR_ACCT_NBR + "(+)");
					
      criteria = "";

      if ( custNbr_ != null && custNbr_.longValue() != 0 )
      {
        criteria = criteria + C_CUST_ALIAS + "." + C_CUST_NBR + " = ? AND ";
      }

      if ( custFullNameText_ != null && custFullNameText_.length() > 0 )
      {
        criteria = criteria + "UPPER(" + C_CUST_ALIAS + "."
                   + C_CUST_FULL_NAME_TEXT + ") like ? AND ";
      }

      if ( custCpfCnpjNbr_ != null && custCpfCnpjNbr_.longValue() != 0 )
      {
        criteria = criteria + C_CUST_ALIAS + "." + C_CUST_CPF_CNPJ_NBR
                   + " = ? AND ";
      }

      if ( reltnNbr_ != null && reltnNbr_.longValue() != 0 )
      {
        criteria = criteria + C_RELATION_ALIAS + "." + C_RELTN_NBR
                   + " = ? AND ";

      }

      if ( curAcctNbr_ != null && curAcctNbr_.longValue() != 0 )
      {
        criteria = criteria + C_CUR_ACCT_ALIAS + "." + C_CUR_ACCT_NBR
                   + " = ? AND ";
      }

      // Informações referentes a dados complementares de cliente

      if ( prvtCustNbr_ != null && prvtCustNbr_.longValue() != 0 )
      {
        criteria = criteria + "CUSTCMPL." + C_PRVT_CUST_NBR + " = ? AND ";
      }

      if ( prvtKeyNbr_ != null && prvtKeyNbr_.longValue() != 0 )
      {
        criteria = criteria + "CUSTCMPL." + C_PRVT_KEY_NBR + " = ? AND ";
      }

      if ( emNbr_ != null && !emNbr_.equals( "" ) )
      {
       criteria = criteria + "UPPER(CUSTCMPL." + C_EM_NBR + ") like ? AND ";
      }

      if ( offcrNbr_ != null && offcrNbr_.longValue() != 0 )
      {
        criteria = criteria + "CUSTCMPL." + C_OFFCR_NBR + "= ? AND ";
      }

      if ( wealthPotnlCode_ != null && wealthPotnlCode_.longValue() != 0 )
      {
        criteria = criteria + "CUSTCMPL." + C_WEALTH_POTNL_CODE + "= ? AND ";
      }

      if ( classCmplcCode_ != null && classCmplcCode_.longValue() != 0 )
      {
        criteria = criteria + "CUSTCMPL." + C_CLASS_CMPLC_CODE + "= ? AND ";
      }

      if ( officerName_ != null && !officerName_.equals( "" ) )
      {
        criteria = criteria + "UPPER(" + C_OFFICER_ALIAS + "."
                   + C_OFFCR_NAME_TEXT + ")like? AND ";
      }
      if ( custPrvtStatCode_ != null && !custPrvtStatCode_.equals( "" ) )
      {
        criteria = criteria + "CUSTCMPL." + C_PRVT_CUST_ATTD_STAT_CODE + " =? AND ";
      }
	  if ( prvtCustTypeCode_!= null && prvtCustTypeCode_.longValue() != 0 )
	  {
	  	criteria = criteria + "CUSTCMPL." + C_PRVT_CUST_TYPE_CODE + " =? AND ";
	  }
      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( " AND " + criteria );
      }

      
      if(!orderBy_.equals("")){
		query.append( " ORDER BY " + orderBy_);      	
      }
      else{
		query.append( " ORDER BY " + C_CUST_ALIAS + "." + C_CUST_FULL_NAME_TEXT );      	
      }
      

      
	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  
      int count = 1;

      if ( custNbr_ != null && custNbr_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
      }

      if ( custFullNameText_ != null && custFullNameText_.length() > 0 )
      {
        preparedStatement.setString( count++, "%" + custFullNameText_.toUpperCase()
                                      + "%" );
      }

      if ( custCpfCnpjNbr_ != null && custCpfCnpjNbr_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, custCpfCnpjNbr_.longValue() );
      }

      if ( reltnNbr_ != null && reltnNbr_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, reltnNbr_.longValue() );

      }

      if ( curAcctNbr_ != null && curAcctNbr_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, curAcctNbr_.longValue() );
      }

      // Informações referentes a dados complementares de cliente

      if ( prvtCustNbr_ != null )
      {
        preparedStatement.setLong( count++, prvtCustNbr_.longValue() );
      }

      if ( prvtKeyNbr_ != null )
      {
        preparedStatement.setLong( count++, prvtKeyNbr_.longValue() );
      }

      if ( emNbr_ != null && !emNbr_.equals( "" ) )
      {
        preparedStatement.setString( count++, emNbr_.toUpperCase() );
      }

      if ( offcrNbr_ != null )
      {
        preparedStatement.setLong( count++, offcrNbr_.longValue() );
      }

      if ( wealthPotnlCode_ != null )
      {
        preparedStatement.setLong( count++, wealthPotnlCode_.longValue() );
      }

      if ( classCmplcCode_ != null )
      {
        preparedStatement.setLong( count++, classCmplcCode_.longValue() );
      }

      if ( officerName_ != null && !officerName_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + officerName_.toUpperCase() + "%" );
      }
      if ( custPrvtStatCode_ != null && !custPrvtStatCode_.equals( "" ) )
      {
        preparedStatement.setString( count++, custPrvtStatCode_ );
      }  
	  if ( prvtCustTypeCode_ != null )
	  {
	  	preparedStatement.setLong( count++, prvtCustTypeCode_.longValue() );
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

  /*
   * Este método busca uma categoria de risco do produto que se enquadre com os
   * critérios informados
   * @see com.citibank.ods.persistence.pl.dao.BaseTplCustomerPrvtDAO#find(com.citibank.ods.entity.pl.BaseTplCustomerPrvtEntity)
   */
  public BaseTplCustomerPrvtEntity find(
                                        BaseTplCustomerPrvtEntity customerEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList tplCustomerPrvtEntites;
    BaseTplCustomerPrvtEntity customerPrvtEntity = null;
    try
    {
      connection = OracleODSDAOFactory.getConnection();
	  query.append( "SELECT " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_NBR + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_CPF_CNPJ_NBR + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_SHORT_NAME_TEXT + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_KEY_NAME_TEXT + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_TYPE_CODE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_FULL_NAME_TEXT + ", " );
	  query.append( " '' " + C_CUST_FULL_NAME_2_TEXT + " , " );
	  query.append( " '' " + C_CUST_FULL_NAME_3_TEXT + ", " );
	  query.append( " '' " + C_CUST_FULL_NAME_4_TEXT + ", " );
	  query.append( " '' " + C_CUR_ACCT_NBR + ", " );
	  query.append( " '' " + C_INVST_CUR_ACCT_NBR + ", " );	
	  query.append( C_CUST_ALIAS + "." + C_CUST_ACTL_STAT_CODE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_STAT_DATE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_ESTAB_DATE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_ESTAB_TIME + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_ESTAB_OP_ID + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_CNR_LAST_MTH_AMT + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_CNR_YTD_AMT + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_CNR_LAST_YR_AMT + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_CNR_LAST_SIX_MTH_CODE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_LAST_UPDATE_DATE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_LAST_UPDATE_TIME + ", " );
	  query.append( C_CUST_ALIAS + "." + C_LAST_UPDATE_OP_ID + ", " );
	  query.append( C_CUST_ALIAS + "." + C_APPRV_DATE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_APPRV_TIME + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_INPUT_ORIG_CODE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_DUP_CODE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_CORP_BKR_APPL_PRFL_IND + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_CORP_FUND_APPL_PRFL_IND + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_CETIP_NBR + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_BMF_NBR + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_BOVESPA_NBR + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_BVRJ_NBR + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_SELIC_NBR + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_MKT_CAT_CODE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_NO_CPF_IND + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_NAT_ID + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_NAT_ID_APPL_DATE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_NAT_ID_EMIT_CODE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_NAT_ID_EMIT_NAME + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_NAT_ID_EMIT_STATE_CODE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_CIVIL_STAT_CODE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_SEX_CODE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_BIRTH_DATE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_BIRTH_CITY_TEXT + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_BIRTH_STATE_CODE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_BIRTH_CNTRY_CODE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_PROF_CODE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_EMPL_IND + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_DEPND_NBR + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_DEPND_NBR_DATE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_OCCUP_CODE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_MGMT_INCO_MIN_SAL_COUNT + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_CHK_DATE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_GRCARD_IND + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_SOC_SCTY_NBR + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_CPF_OWN_IND + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_PARENT_LEVEL_IND + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_USA_CTZN_AUTH_IND + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_USA_CTZN_AUTH_OP_ID + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_INDIV_PUBLIC_IND + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_CITI_GRP_TIE_IND + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_BIRTH_CNTRY_CO_CODE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_ACTY_AREA_CODE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_NO_CGC_IND + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_FNDTN_DATE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_CO_TYPE_CODE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_GRP_CODE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_SUB_GRP_CODE + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_INTL_NBR + ", " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_IRRF_EXEMPT_IND + ", " );
	  query.append( C_CUST_ALIAS + "." + C_REC_STAT_CODE + ", " );
	  query.append( C_CUST_CMPL_ALIAS + "." + C_EM_NBR + ", " );
	  query.append( C_CUST_CMPL_ALIAS + "." + C_MAIL_RECV_IND + ", " );
	  query.append( C_CUST_CMPL_ALIAS + "." + C_OFFCL_MAIL_RECV_IND + ", " );
	  query.append( C_CUST_CMPL_ALIAS + "." + C_PRVT_CUST_NBR + ", " );
	  query.append( C_CUST_CMPL_ALIAS + "." + C_PRVT_KEY_NBR + ", " );
	  query.append( C_CUST_CMPL_ALIAS + "." + C_WEALTH_POTNL_CODE + ", " );
	  query.append( C_CUST_CMPL_ALIAS + "." + C_CLASS_CMPLC_CODE + ", " );
	  query.append( C_CUST_CMPL_ALIAS + "." + C_OFFCR_NBR + ", " );
	  query.append( C_CUST_CMPL_ALIAS + "." + C_GLB_REVEN_SYS_OFFCR_NBR + ", " );
	  query.append( C_CUST_CMPL_ALIAS + "." + C_LAST_UPD_DATE + ", " );
	  query.append( C_CUST_CMPL_ALIAS + "." + C_LAST_UPD_USER_ID + ", " );
	  query.append( C_CUST_CMPL_ALIAS + "." + C_LAST_AUTH_DATE + ", " );
	  query.append( C_CUST_CMPL_ALIAS + "." + C_LAST_AUTH_USER_ID + ", " );
	  query.append( C_CUST_CMPL_ALIAS + "." + C_REC_STAT_CODE + ", ");

	  /*
	   * RDIP
	   */
	  query.append( " CASE ");
	  query.append( "  WHEN ( DESCRDIP.RDIP_DESC_RDIP_CODE IS NOT NULL ) THEN DESCRDIP.RDIP_DESC_RDIP_CODE || ' - ' || DESCRDIP.RDIP_DESC_FULL_TEXT ");
	  query.append( "  ELSE ' ' ");
	  query.append( " END AS " + C_RDIP_DESC_FULL_TEXT);	  
	  
	  query.append( " FROM " );
	  query.append( C_TPL_CUSTOMER_PRVT + " " + C_CUST_ALIAS );
	  query.append( " LEFT JOIN " + C_TPL_CUSTOMER_PRVT_CMPL + " "+ C_CUST_CMPL_ALIAS + " ON " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_NBR + " = " + C_CUST_CMPL_ALIAS + "." + C_CUST_NBR );

	  /*
	   * RDIP
	   */
	  query.append( " LEFT JOIN ");
	  query.append( " BG.TBG_DATA_ADDITIONAL_CUST DTADDCUST ");
	  query.append( " ON CUST.CUST_NBR = DTADDCUST.CUST_NBR ");
	  query.append( " LEFT JOIN BG.TBG_DESC_RDIP_SEG_BUS DESCRDIP ");
	  query.append( " ON DTADDCUST.CUST_RDIP_CODE  =  DESCRDIP.RDIP_DESC_RDIP_CODE ");
	  query.append( " AND DESCRDIP.RDIP_DESC_STAT_CODE = 'A' ");
	  query.append( " AND DESCRDIP.RDIP_DESC_BUS_SEG_CODE = '2' ");
	  
	  query.append( " WHERE " );
	  query.append( C_CUST_ALIAS + "." + C_CUST_NBR + " = ? " );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	
	  preparedStatement.setLong( 1, customerEntity_.getData().getCustNbr().longValue() );
	  preparedStatement.replaceParametersInQuery(query.toString());
      resultSet = preparedStatement.executeQuery();
	  

      tplCustomerPrvtEntites = instantiateFromResultSet( resultSet );

      if ( tplCustomerPrvtEntites.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplCustomerPrvtEntites.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        customerPrvtEntity = ( BaseTplCustomerPrvtEntity ) tplCustomerPrvtEntites.get( 0 );
      }
      return customerPrvtEntity;
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
  
  /*
	 * Este método busca uma categoria de risco do produto que se enquadre com os
	 * critérios informados
	 * @see com.citibank.ods.persistence.pl.dao.BaseTplCustomerPrvtDAO#findByReltn(com.citibank.ods.entity.pl.BaseTplCustomerPrvtEntity)
	 */
	public BaseTplCustomerPrvtEntity findByReltn(
										  BaseTplCustomerPrvtEntity customerEntity_ )
	{
	  ManagedRdbConnection connection = null;
	  CitiStatement preparedStatement = null;
	  ResultSet resultSet = null;
	  StringBuffer query = new StringBuffer();
	  ArrayList tplCustomerPrvtEntites;
	  BaseTplCustomerPrvtEntity customerPrvtEntity = null;
	  try
	  {
		connection = OracleODSDAOFactory.getConnection();
		query.append( "SELECT " );
		query.append( C_CUST_ALIAS + "." + C_CUST_NBR + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_CPF_CNPJ_NBR + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_SHORT_NAME_TEXT + ", " );
		query.append( C_CUST_2_ALIAS + "." + C_CUST_FULL_NAME_TEXT + " " + C_CUST_FULL_NAME_2_TEXT + ", " );
		query.append( C_CUST_3_ALIAS + "." + C_CUST_FULL_NAME_TEXT + " " + C_CUST_FULL_NAME_3_TEXT + ", " );
		query.append( C_CUST_4_ALIAS + "." + C_CUST_FULL_NAME_TEXT + " " + C_CUST_FULL_NAME_4_TEXT + ", " );
		query.append( C_CUR_ACCT_ALIAS + "." + C_CUR_ACCT_NBR + ", " );
		query.append( C_CUST_POINT + "." + C_INVST_CUR_ACCT_NBR + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_KEY_NAME_TEXT + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_TYPE_CODE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_FULL_NAME_TEXT + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_ACTL_STAT_CODE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_STAT_DATE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_ESTAB_DATE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_ESTAB_TIME + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_ESTAB_OP_ID + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_CNR_LAST_MTH_AMT + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_CNR_YTD_AMT + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_CNR_LAST_YR_AMT + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_CNR_LAST_SIX_MTH_CODE + ", " );
		query.append( C_CUST_ALIAS + "." + C_LAST_UPDATE_DATE + ", " );
		query.append( C_CUST_ALIAS + "." + C_LAST_UPDATE_TIME + ", " );
		query.append( C_CUST_ALIAS + "." + C_LAST_UPDATE_OP_ID + ", " );
		query.append( C_CUST_ALIAS + "." + C_APPRV_DATE + ", " );
		query.append( C_CUST_ALIAS + "." + C_APPRV_TIME + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_INPUT_ORIG_CODE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_DUP_CODE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_CORP_BKR_APPL_PRFL_IND + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_CORP_FUND_APPL_PRFL_IND + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_CETIP_NBR + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_BMF_NBR + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_BOVESPA_NBR + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_BVRJ_NBR + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_SELIC_NBR + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_MKT_CAT_CODE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_NO_CPF_IND + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_NAT_ID + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_NAT_ID_APPL_DATE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_NAT_ID_EMIT_CODE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_NAT_ID_EMIT_NAME + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_NAT_ID_EMIT_STATE_CODE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_CIVIL_STAT_CODE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_SEX_CODE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_BIRTH_DATE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_BIRTH_CITY_TEXT + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_BIRTH_STATE_CODE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_BIRTH_CNTRY_CODE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_PROF_CODE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_EMPL_IND + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_DEPND_NBR + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_DEPND_NBR_DATE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_OCCUP_CODE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_MGMT_INCO_MIN_SAL_COUNT + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_CHK_DATE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_GRCARD_IND + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_SOC_SCTY_NBR + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_CPF_OWN_IND + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_PARENT_LEVEL_IND + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_USA_CTZN_AUTH_IND + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_USA_CTZN_AUTH_OP_ID + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_INDIV_PUBLIC_IND + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_CITI_GRP_TIE_IND + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_BIRTH_CNTRY_CO_CODE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_ACTY_AREA_CODE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_NO_CGC_IND + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_FNDTN_DATE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_CO_TYPE_CODE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_GRP_CODE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_SUB_GRP_CODE + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_INTL_NBR + ", " );
		query.append( C_CUST_ALIAS + "." + C_CUST_IRRF_EXEMPT_IND + ", " );
		query.append( C_CUST_ALIAS + "." + C_REC_STAT_CODE + ", " );
		query.append( C_CUST_CMPL_ALIAS + "." + C_EM_NBR + ", " );
		query.append( C_CUST_CMPL_ALIAS + "." + C_MAIL_RECV_IND + ", " );
		query.append( C_CUST_CMPL_ALIAS + "." + C_OFFCL_MAIL_RECV_IND + ", " );
		query.append( C_CUST_CMPL_ALIAS + "." + C_PRVT_CUST_NBR + ", " );
		query.append( C_CUST_CMPL_ALIAS + "." + C_PRVT_KEY_NBR + ", " );
		query.append( C_CUST_CMPL_ALIAS + "." + C_WEALTH_POTNL_CODE + ", " );
		query.append( C_CUST_CMPL_ALIAS + "." + C_CLASS_CMPLC_CODE + ", " );
		query.append( C_CUST_CMPL_ALIAS + "." + C_OFFCR_NBR + ", " );
		query.append( C_CUST_CMPL_ALIAS + "." + C_GLB_REVEN_SYS_OFFCR_NBR + ", " );
		query.append( C_CUST_CMPL_ALIAS + "." + C_LAST_UPD_DATE + ", " );
		query.append( C_CUST_CMPL_ALIAS + "." + C_LAST_UPD_USER_ID + ", " );
		query.append( C_CUST_CMPL_ALIAS + "." + C_LAST_AUTH_DATE + ", " );
		query.append( C_CUST_CMPL_ALIAS + "." + C_LAST_AUTH_USER_ID + ", " );
		query.append( C_CUST_CMPL_ALIAS + "." + C_REC_STAT_CODE + ", " );
		query.append( "null as " + C_RDIP_DESC_FULL_TEXT);
		query.append( " FROM " );
		query.append( C_TPL_CUSTOMER_PRVT + " " + C_CUST_ALIAS + ", " );
		query.append( " ( SELECT " + C_CUST_NBR + ", " + C_RELTN_NBR + ", "
							+ C_CUR_ACCT_NBR + ", " + C_PROD_CODE + ", "
							+ C_SYS_SEG_CODE );
		query.append( " FROM " + C_TO3_PRODUCT_ACCOUNT );
		query.append( " WHERE " + C_PROD_CODE + " = " + C_PROD_CODE_ACCT );
		query.append( " AND " + C_SYS_SEG_CODE + " = " + C_SYS_SEG_CODE_ACCT );
		query.append( " AND SYS_CODE = 'DA') " + C_CUR_ACCT_ALIAS + ", " );
		query.append( C_TPL_CUSTOMER_PRVT + " " + C_CUST_2_ALIAS + ", " );
		query.append( C_TPL_CUSTOMER_PRVT + " " + C_CUST_3_ALIAS + ", " );
		query.append( C_TPL_CUSTOMER_PRVT + " " + C_CUST_4_ALIAS + ", " );
		query.append( C_TPL_RELATION_PRVT + " " + C_RELATION_ALIAS + ", " );
		query.append( C_POINT_ACCT_INVST_POINT + " " + C_CUST_POINT + ", " );
		query.append( C_TPL_CUSTOMER_PRVT_CMPL + " " + C_CUST_CMPL_ALIAS );
		query.append( " WHERE " );
		query.append( C_CUST_ALIAS + "." + C_CUST_NBR + " = " + C_CUST_CMPL_ALIAS + "." + C_CUST_NBR + " (+) " );
		query.append( " AND " + C_CUST_ALIAS + "." + C_CUST_NBR + " = " + C_RELATION_ALIAS + "." + C_RELTN_CUST_1_NBR );
		query.append( " AND " + C_RELATION_ALIAS + "." + C_RELTN_CUST_2_NBR + " = " + C_CUST_2_ALIAS + "." + C_CUST_NBR + " (+) " );	 
		query.append( " AND " + C_RELATION_ALIAS + "." + C_RELTN_CUST_3_NBR + " = " + C_CUST_3_ALIAS + "." + C_CUST_NBR + " (+) " );
		query.append( " AND " + C_RELATION_ALIAS + "." + C_RELTN_CUST_4_NBR + " = " + C_CUST_4_ALIAS + "." + C_CUST_NBR + " (+) " );
		query.append( " AND " + C_CUST_ALIAS + "." + C_CUST_NBR + " = " + C_RELATION_ALIAS + "." + C_RELTN_CUST_1_NBR + " (+) ");
		query.append( " AND " + C_RELATION_ALIAS + "." + C_RELTN_NBR + " = " + C_CUR_ACCT_ALIAS + "." + C_RELTN_NBR + " (+) ");
	    query.append( " AND " + C_CUR_ACCT_ALIAS + "." + C_CUR_ACCT_NBR + " = " + C_CUST_POINT + "." + C_CUR_ACCT_NBR + " (+) ");
		query.append( " AND " + C_RELATION_ALIAS + "." + C_RELTN_NBR + " = ? " );
		
		preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
		preparedStatement.setLong( 1, customerEntity_.getData().getReltnNbr().longValue() );
		preparedStatement.replaceParametersInQuery(query.toString());
		resultSet = preparedStatement.executeQuery();
	  

		tplCustomerPrvtEntites = instantiateFromResultSet( resultSet );

		if ( tplCustomerPrvtEntites.size() == 0 )
		{
		  throw new NoRowsReturnedException();
		}
		else if ( tplCustomerPrvtEntites.size() > 1 )
		{
		  throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
		}
		else
		{
		  customerPrvtEntity = ( BaseTplCustomerPrvtEntity ) tplCustomerPrvtEntites.get( 0 );
		}
		return customerPrvtEntity;
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
  

  /*
   * Retorna uma coleção de entities a partir do rs
   */

  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
                                                                    throws UnexpectedException
  {
    TplCustomerPrvtEntity tplCustomerPrvtEntity;
    TplCustomerPrvtEntityVO tplCustomerPrvtEntityVO;
    BigInteger custCpfCnpjNbr;
    BigInteger custEstab;
    BigInteger custCnrLast;
    BigInteger CustCnrYtd;
    BigInteger CustCrnLastYr;
    BigInteger lastUpdate;
    BigInteger Apprv;
    BigInteger custCetip;
    BigInteger custBmf;
    BigInteger custBovespa;
    BigInteger custBvrj;
    BigInteger custSelic;
    BigInteger custDepnd;
    BigInteger custSoc;
    BigInteger custGrp;
    BigInteger custSubGrp;
    BigInteger custIntl;
	BigInteger curAcctNbr;
    String invstCurAcctNbr;

    ArrayList TplCustomerPrvtEntities = new ArrayList();
    try
    {
      while ( resultSet_.next() )
      {
        tplCustomerPrvtEntity = new TplCustomerPrvtEntity();
        tplCustomerPrvtEntityVO = ( TplCustomerPrvtEntityVO ) tplCustomerPrvtEntity.getData();

        tplCustomerPrvtEntityVO.setCustNbr( new BigInteger(
                                                            resultSet_.getString( C_CUST_NBR ) ) );
                                                  

        custCpfCnpjNbr = ( resultSet_.getString( C_CUST_CPF_CNPJ_NBR ) ) == null
                                                                                ? null
                                                                                : new BigInteger(
                                                                                                  resultSet_.getString( C_CUST_CPF_CNPJ_NBR ) );

        tplCustomerPrvtEntityVO.setCustCpfCnpjNbr( custCpfCnpjNbr );

        tplCustomerPrvtEntityVO.setCustShortNameText( resultSet_.getString( C_CUST_SHORT_NAME_TEXT ) );

        tplCustomerPrvtEntityVO.setCustKeyNameText( resultSet_.getString( C_CUST_KEY_NAME_TEXT ) );

        tplCustomerPrvtEntityVO.setCustTypeCode( resultSet_.getString( C_CUST_TYPE_CODE ) );

        tplCustomerPrvtEntityVO.setCustFullNameText( resultSet_.getString( C_CUST_FULL_NAME_TEXT ) );
        
		tplCustomerPrvtEntityVO.setCustFullName2Text( resultSet_.getString( C_CUST_FULL_NAME_2_TEXT ) );
		
		tplCustomerPrvtEntityVO.setCustFullName3Text( resultSet_.getString( C_CUST_FULL_NAME_3_TEXT ) );
		
		tplCustomerPrvtEntityVO.setCustFullName4Text( resultSet_.getString( C_CUST_FULL_NAME_4_TEXT ) );

		curAcctNbr = ( resultSet_.getString( C_CUR_ACCT_NBR ) ) == null
																				? null
																				: new BigInteger(
																								  resultSet_.getString( C_CUR_ACCT_NBR ) );
		tplCustomerPrvtEntityVO.setCurAcctNbr( curAcctNbr );
																										  																						  
		tplCustomerPrvtEntityVO.setInvstCurAcctNbr( resultSet_.getString( C_INVST_CUR_ACCT_NBR ) );
				
	    tplCustomerPrvtEntityVO.setCustActlStatCode( resultSet_.getString( C_CUST_ACTL_STAT_CODE ) );

        tplCustomerPrvtEntityVO.setCustStatDate( resultSet_.getDate( C_CUST_STAT_DATE ) );

        tplCustomerPrvtEntityVO.setCustEstabDate( resultSet_.getDate( C_CUST_ESTAB_DATE ) );

        custEstab = ( resultSet_.getString( C_CUST_ESTAB_TIME ) ) == null
                                                                         ? null
                                                                         : new BigInteger(
                                                                                           resultSet_.getString( C_CUST_ESTAB_TIME ) );
        tplCustomerPrvtEntityVO.setCustEstabTime( custEstab );

        tplCustomerPrvtEntityVO.setCustEstabOpId( resultSet_.getString( C_CUST_ESTAB_OP_ID ) );

        custCnrLast = ( resultSet_.getString( C_CUST_CNR_LAST_MTH_AMT ) ) == null
                                                                                 ? null
                                                                                 : new BigInteger(
                                                                                                   resultSet_.getString( C_CUST_CNR_LAST_MTH_AMT ) );
        tplCustomerPrvtEntityVO.setCustCnrLastMthAmt( custCnrLast );

        CustCnrYtd = ( resultSet_.getString( C_CUST_CNR_YTD_AMT ) ) == null
                                                                           ? null
                                                                           : new BigInteger(
                                                                                             resultSet_.getString( C_CUST_CNR_YTD_AMT ) );
        tplCustomerPrvtEntityVO.setCustCnrYtdAmt( CustCnrYtd );

        CustCrnLastYr = ( resultSet_.getString( C_CUST_CNR_LAST_YR_AMT ) ) == null
                                                                                  ? null
                                                                                  : new BigInteger(
                                                                                                    resultSet_.getString( C_CUST_CNR_LAST_YR_AMT ) );
        tplCustomerPrvtEntityVO.setCustCnrLastYrAmt( CustCrnLastYr );

        tplCustomerPrvtEntityVO.setCustCnrLastSixMthCode( resultSet_.getString( C_CUST_CNR_LAST_SIX_MTH_CODE ) );

        tplCustomerPrvtEntityVO.setLastUpdateDate( resultSet_.getDate( C_LAST_UPDATE_DATE ) );

        lastUpdate = ( resultSet_.getString( C_LAST_UPDATE_TIME ) ) == null
                                                                           ? null
                                                                           : new BigInteger(
                                                                                             resultSet_.getString( C_LAST_UPDATE_TIME ) );
        tplCustomerPrvtEntityVO.setLastUpdateTime( lastUpdate );

        tplCustomerPrvtEntityVO.setLastUpdateOpId( resultSet_.getString( C_LAST_UPDATE_OP_ID ) );

        tplCustomerPrvtEntityVO.setApprvDate( resultSet_.getDate( C_APPRV_DATE ) );

        Apprv = ( resultSet_.getString( C_APPRV_TIME ) ) == null
                                                                ? null
                                                                : new BigInteger(
                                                                                  resultSet_.getString( C_APPRV_TIME ) );
        tplCustomerPrvtEntityVO.setApprvTime( Apprv );

        tplCustomerPrvtEntityVO.setCustInputOrigCode( resultSet_.getString( C_CUST_INPUT_ORIG_CODE ) );

        tplCustomerPrvtEntityVO.setCustDupCode( resultSet_.getString( C_CUST_DUP_CODE ) );

        tplCustomerPrvtEntityVO.setCustCorpBkrApplPrflInd( resultSet_.getString( C_CUST_CORP_BKR_APPL_PRFL_IND ) );

        tplCustomerPrvtEntityVO.setCustCorpFundApplPrflInd( resultSet_.getString( C_CUST_CORP_FUND_APPL_PRFL_IND ) );

        custCetip = ( resultSet_.getString( C_CUST_CETIP_NBR ) ) == null
                                                                        ? null
                                                                        : new BigInteger(
                                                                                          resultSet_.getString( C_CUST_CETIP_NBR ) );
        tplCustomerPrvtEntityVO.setCustCetipNbr( custCetip );

        custBmf = ( resultSet_.getString( C_CUST_BMF_NBR ) ) == null
                                                                    ? null
                                                                    : new BigInteger(
                                                                                      resultSet_.getString( C_CUST_BMF_NBR ) );
        tplCustomerPrvtEntityVO.setCustBmfNbr( custBmf );

        custBovespa = ( resultSet_.getString( C_CUST_BOVESPA_NBR ) ) == null
                                                                            ? null
                                                                            : new BigInteger(
                                                                                              resultSet_.getString( C_CUST_BOVESPA_NBR ) );
        tplCustomerPrvtEntityVO.setCustBovespaNbr( custBovespa );

        custBvrj = ( resultSet_.getString( C_CUST_BVRJ_NBR ) ) == null
                                                                      ? null
                                                                      : new BigInteger(
                                                                                        resultSet_.getString( C_CUST_BVRJ_NBR ) );
        tplCustomerPrvtEntityVO.setCustBvrjNbr( custBvrj );

        custSelic = ( resultSet_.getString( C_CUST_SELIC_NBR ) ) == null
                                                                        ? null
                                                                        : new BigInteger(
                                                                                          resultSet_.getString( C_CUST_SELIC_NBR ) );
        tplCustomerPrvtEntityVO.setCustSelicNbr( custSelic );

        tplCustomerPrvtEntityVO.setCustMktCatCode( resultSet_.getString( C_CUST_MKT_CAT_CODE ) );

        tplCustomerPrvtEntityVO.setCustNoCpfInd( resultSet_.getString( C_CUST_NO_CPF_IND ) );

        tplCustomerPrvtEntityVO.setCustNatId( resultSet_.getString( C_CUST_NAT_ID ) );

        tplCustomerPrvtEntityVO.setCustNatIdApplDate( resultSet_.getDate( C_CUST_NAT_ID_APPL_DATE ) );

        tplCustomerPrvtEntityVO.setCustNatIdEmitCode( resultSet_.getString( C_CUST_NAT_ID_EMIT_CODE ) );

        tplCustomerPrvtEntityVO.setCustNatIdEmitName( resultSet_.getString( C_CUST_NAT_ID_EMIT_NAME ) );

        tplCustomerPrvtEntityVO.setCustNatIdEmitStateCode( resultSet_.getString( C_CUST_NAT_ID_EMIT_STATE_CODE ) );

        tplCustomerPrvtEntityVO.setCustCivilStatCode( resultSet_.getString( C_CUST_CIVIL_STAT_CODE ) );

        tplCustomerPrvtEntityVO.setCustSexCode( resultSet_.getString( C_CUST_SEX_CODE ) );

        tplCustomerPrvtEntityVO.setCustBirthDate( resultSet_.getDate( C_CUST_BIRTH_DATE ) );

        tplCustomerPrvtEntityVO.setCustBirthCityText( resultSet_.getString( C_CUST_BIRTH_CITY_TEXT ) );

        tplCustomerPrvtEntityVO.setCustBirthStateCode( resultSet_.getString( C_CUST_BIRTH_STATE_CODE ) );

        tplCustomerPrvtEntityVO.setCustBirthCntryCode( resultSet_.getString( C_CUST_BIRTH_CNTRY_CODE ) );

        tplCustomerPrvtEntityVO.setCustProfCode( resultSet_.getString( C_CUST_PROF_CODE ) );

        tplCustomerPrvtEntityVO.setCustEmplInd( resultSet_.getString( C_CUST_EMPL_IND ) );

        custDepnd = ( resultSet_.getString( C_CUST_DEPND_NBR ) ) == null
                                                                        ? null
                                                                        : new BigInteger(
                                                                                          resultSet_.getString( C_CUST_DEPND_NBR ) );
        tplCustomerPrvtEntityVO.setCustDepndNbr( custDepnd );

        tplCustomerPrvtEntityVO.setCustDepndNbrDate( resultSet_.getDate( C_CUST_DEPND_NBR_DATE ) );

        tplCustomerPrvtEntityVO.setCustOccupCode( resultSet_.getString( C_CUST_OCCUP_CODE ) );

        tplCustomerPrvtEntityVO.setCustMgmtIncoMinSalCount( resultSet_.getString( C_CUST_MGMT_INCO_MIN_SAL_COUNT ) );

        tplCustomerPrvtEntityVO.setCustChkDate( resultSet_.getDate( C_CUST_CHK_DATE ) );

        tplCustomerPrvtEntityVO.setCustGrcardInd( resultSet_.getString( C_CUST_GRCARD_IND ) );

        custSoc = ( resultSet_.getString( C_CUST_SOC_SCTY_NBR ) ) == null
                                                                         ? null
                                                                         : new BigInteger(
                                                                                           resultSet_.getString( C_CUST_SOC_SCTY_NBR ) );
        tplCustomerPrvtEntityVO.setCustSocSctyNbr( custSoc );

        tplCustomerPrvtEntityVO.setCustCpfOwnInd( resultSet_.getString( C_CUST_CPF_OWN_IND ) );

        tplCustomerPrvtEntityVO.setCustParentLevelInd( resultSet_.getString( C_CUST_PARENT_LEVEL_IND ) );

        tplCustomerPrvtEntityVO.setCustUsaCtznAuthInd( resultSet_.getString( C_CUST_USA_CTZN_AUTH_IND ) );

        tplCustomerPrvtEntityVO.setCustUsaCtznAuthOpId( resultSet_.getString( C_CUST_USA_CTZN_AUTH_OP_ID ) );

        tplCustomerPrvtEntityVO.setCustIndivPublicInd( resultSet_.getString( C_CUST_INDIV_PUBLIC_IND ) );

        tplCustomerPrvtEntityVO.setCustCitiGrpTieInd( resultSet_.getString( C_CUST_CITI_GRP_TIE_IND ) );

        tplCustomerPrvtEntityVO.setCustBirthCntryCoCode( resultSet_.getString( C_CUST_BIRTH_CNTRY_CO_CODE ) );

        tplCustomerPrvtEntityVO.setCustActyAreaCode( resultSet_.getString( C_CUST_ACTY_AREA_CODE ) );

        tplCustomerPrvtEntityVO.setCustNoCgcInd( resultSet_.getString( C_CUST_NO_CGC_IND ) );

        tplCustomerPrvtEntityVO.setCustFndtnDate( resultSet_.getDate( C_CUST_FNDTN_DATE ) );

        tplCustomerPrvtEntityVO.setCustCoTypeCode( resultSet_.getString( C_CUST_CO_TYPE_CODE ) );

        custGrp = ( resultSet_.getString( C_CUST_GRP_CODE ) ) == null
                                                                     ? null
                                                                     : new BigInteger(
                                                                                       resultSet_.getString( C_CUST_GRP_CODE ) );
        tplCustomerPrvtEntityVO.setCustGrpCode( custGrp );

        custSubGrp = ( resultSet_.getString( C_CUST_SUB_GRP_CODE ) ) == null
                                                                            ? null
                                                                            : new BigInteger(
                                                                                              resultSet_.getString( C_CUST_SUB_GRP_CODE ) );

        tplCustomerPrvtEntityVO.setCustSubGrpCode( custSubGrp );

        custIntl = ( resultSet_.getString( C_CUST_INTL_NBR ) ) == null
                                                                      ? null
                                                                      : new BigInteger(
                                                                                        resultSet_.getString( C_CUST_INTL_NBR ) );
        tplCustomerPrvtEntityVO.setCustIntlNbr( custIntl );

        tplCustomerPrvtEntityVO.setCustIrrfExemptInd( resultSet_.getString( C_CUST_IRRF_EXEMPT_IND ) );

        tplCustomerPrvtEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );

        // Informações referentes a dados complementares de cliente

        if ( resultSet_.getString( C_CLASS_CMPLC_CODE ) != null )
        {
          tplCustomerPrvtEntityVO.setClassCmplcCode( ( new BigInteger(
                                                                       resultSet_.getString( C_CLASS_CMPLC_CODE ) ) ) );

        }
        tplCustomerPrvtEntityVO.setEmNbr( resultSet_.getString( C_EM_NBR ) );
        tplCustomerPrvtEntityVO.setLastAuthDate( resultSet_.getDate( C_LAST_AUTH_DATE ) );
        tplCustomerPrvtEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );
        tplCustomerPrvtEntityVO.setLastUpdDate( resultSet_.getDate( C_LAST_UPD_DATE ) );
        tplCustomerPrvtEntityVO.setLastUpdUserId( resultSet_.getString( C_LAST_UPD_USER_ID ) );
        tplCustomerPrvtEntityVO.setMailRecvInd( resultSet_.getString( C_MAIL_RECV_IND ) );
        tplCustomerPrvtEntityVO.setOffclMailRecvInd( resultSet_.getString( C_OFFCL_MAIL_RECV_IND ) );
        if ( resultSet_.getString( C_PRVT_KEY_NBR ) != null )
        {
          tplCustomerPrvtEntityVO.setPrvtKeyNbr( new BigInteger(
                                                                       resultSet_.getString( C_PRVT_KEY_NBR ) ) );
        }
        if ( resultSet_.getString( C_OFFCR_NBR ) != null )
        {
          tplCustomerPrvtEntityVO.setOffcrNbr( new BigInteger(
                                                               resultSet_.getString( C_OFFCR_NBR ) ) );
        }
        if ( resultSet_.getString( C_GLB_REVEN_SYS_OFFCR_NBR ) != null )
        {
          tplCustomerPrvtEntityVO.setGlbRevenSysOffcrNbr( new BigInteger(
                                                                          resultSet_.getString( C_GLB_REVEN_SYS_OFFCR_NBR ) ) );
        }
        if ( resultSet_.getString( C_PRVT_CUST_NBR ) != null )
        {
          tplCustomerPrvtEntityVO.setPrvtCustNbr( new BigInteger(
                                                                  resultSet_.getString( C_PRVT_CUST_NBR ) ) );

        }

        if ( resultSet_.getString( C_WEALTH_POTNL_CODE ) != null )
        {
          tplCustomerPrvtEntityVO.setWealthPotnlCode( new BigInteger(
                                                                      resultSet_.getString( C_WEALTH_POTNL_CODE ) ) );

        }
        
        /*
         * RDIP
         */
        if (resultSet_.getString( C_RDIP_DESC_FULL_TEXT ) != null){
        	tplCustomerPrvtEntityVO.setRdipDescription( resultSet_.getString( C_RDIP_DESC_FULL_TEXT ) );
        }
        
        TplCustomerPrvtEntities.add( tplCustomerPrvtEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    
    return TplCustomerPrvtEntities;
  }

  /*
   * Objetivo: Método que lista clientes com questionário de K&E
   * Projeto: RDIP
   * Responsável: Eversystems
   * Data: 10/01/2011
   */
  public DataSet list( BigInteger customerNumber, String customerNameText )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( " SELECT * FROM ( " );

      query.append( " SELECT DISTINCT" );
      query.append( " cust_prvt.CUST_NBR, " );
      query.append( " cust_prvt.CUST_FULL_NAME_TEXT " );
      query.append( " FROM " );
      query.append( " PL.TPL_CUSTOMER_PRVT cust_prvt ");
      query.append( " INNER JOIN " );
      query.append( " PL.TPL_ANSW_CUST_QUEST cust_quest ");
      query.append( " ON cust_quest.QUEST_CUST_ANSW_CUST_NBR = cust_prvt.CUST_NBR " );
      query.append( " WHERE	 ( ? IS NULL OR cust_prvt.CUST_NBR = ? ) " );
      query.append( " AND	 ( ? IS NULL OR UPPER(cust_prvt.CUST_FULL_NAME_TEXT) LIKE ? ) " );   
      query.append( " ORDER BY  " );
      query.append( " cust_prvt.CUST_NBR, " );
      query.append( " cust_prvt.CUST_FULL_NAME_TEXT " );
      
      query.append( " ) WHERE ROWNUM <=  " + BusinessConstants.KNOW_EXP_EXCEEDED_LIMIT );
      
	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
	  
      int count = 1;

      if ( customerNumber != null && customerNumber.longValue() != 0 )
      {
        preparedStatement.setInt( count++, customerNumber.intValue() );
        preparedStatement.setInt( count++, customerNumber.intValue() );
      }else{
    	preparedStatement.setNull( count++, Types.INTEGER );
    	preparedStatement.setNull( count++, Types.INTEGER );
      }

      if ( customerNameText != null && customerNameText.length() > 0 )
      {
        preparedStatement.setString( count++, "%" + customerNameText.toUpperCase() + "%" );
        preparedStatement.setString( count++, "%" + customerNameText.toUpperCase() + "%" );
      }else{
      	preparedStatement.setNull( count++, Types.VARCHAR );
      	preparedStatement.setNull( count++, Types.VARCHAR );
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

  /*
   * Objetivo: Método que lista o questionário de K&E de determinado cliente
   * Projeto: RDIP
   * Responsável: Eversystems
   * Data: 13/01/2011
   */
  public DataSet findQuestionary( BigInteger customerNumber ){	  
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( " SELECT    group_knwlg.KNWLG_GROUP_TEXT as PRODUTO," );
      query.append( " 			answ_quest.QUEST_ANSW_TEXT as RESPOSTA" );
      query.append( " FROM      PL.TPL_CUSTOMER_PRVT cust_prvt," );
      query.append( " 			PL.TPL_ANSW_CUST_QUEST cust_quest," );
      query.append( " 			PL.TPL_OPTN_ANSW_ASK_QUEST answ_ask_quest," );
      query.append( " 			PL.TPL_OPTN_ANSW_QUEST answ_quest," );
      query.append( " 			PL.TPL_GROUP_KNWLG group_knwlg," );
      query.append( " 			PL.TPL_ASK_QUEST ask_quest" );
      query.append( " WHERE     cust_prvt.CUST_NBR = ?" );
      query.append( " AND       cust_quest.QUEST_CUST_ANSW_CUST_NBR = cust_prvt.CUST_NBR" );
      query.append( " AND       cust_quest.QUEST_ANSW_ASK_CODE = answ_ask_quest.QUEST_ANSW_ASK_CODE" );
      query.append( " AND       cust_quest.QUEST_ASK_ANSW_CODE = answ_ask_quest.QUEST_ASK_ANSW_CODE" );
      query.append( " AND       answ_ask_quest.QUEST_ASK_ANSW_CODE = answ_quest.QUEST_ANSW_CODE" );
      query.append( " AND       answ_ask_quest.QUEST_ANSW_ASK_CODE= ask_quest.QUEST_ASK_CODE" );
      query.append( " AND       cust_quest.QUEST_CUST_KNWLG_GROUP_CODE = group_knwlg.KNWLG_GROUP_CODE" );
      query.append( " ORDER BY  group_knwlg.KNWLG_GROUP_CODE" );

	  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      preparedStatement.setInt( count++, customerNumber.intValue() );

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
  
  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO#exists(com.citibank.ods.entity.pl.TplCustomerPrvtEntity)
   */
  public boolean exists( TplCustomerPrvtEntity tplCustomerPrvtEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tplCustomerPrvtEntity_ );
    }
    catch ( NoRowsReturnedException e )
    {
      exists = false;
    }

    return exists;
  }
}