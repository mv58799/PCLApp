package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.To3ProductAccountEntity;
import com.citibank.ods.entity.pl.valueobject.To3ProductAccountEntityVO;
import com.citibank.ods.persistence.pl.dao.To3ProductAccountDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

import static com.citibank.ods.common.util.BusinessConstants.*;

/**
 * @author m.nakamura
 * 
 * Implementação da interface para acesso ao banco de dados de conta de
 * produtos.
 */
public class OracleTo3ProductAccountDAO extends BaseOracleTo3ProductAccountDAO
    implements To3ProductAccountDAO
{
  //Tabela TO3_PRODUCT_ACCOUNT
   private static final String C_TO3_PRODUCT_ACCOUNT_MOV = C_O3_SCHEMA
													   + "TO3_PRODUCT_ACCOUNT_MOV";
  
  // Tabela TO3_PRODUCT_ACCOUNT
  private static final String C_TO3_PRODUCT_ACCOUNT = C_O3_SCHEMA
                                                      + "TO3_PRODUCT_ACCOUNT";

  // Tabela TPL_CUSTOMER_PRVT
  private static final String C_TPL_CUSTOMER_PRVT = C_PL_SCHEMA
                                                    + "TPL_CUSTOMER_PRVT";

  // Tabela TPL_PRODUCT
  private static final String C_TPL_PRODUCT = C_PL_SCHEMA + "TPL_PRODUCT";

  //Tabele TBG_POINT_ACCT
  private static final String C_TBG_POINT_ACCT_INVST = C_BG_SCHEMA
                                                       + "TBG_POINT_ACCT_INVST";

  // Tabela TO3_PROD_ACCT_CUR_ACCOUNT
  private static final String C_TO3_PROD_ACCT_CUR_ACCOUNT = C_O3_SCHEMA
                                                            + "TO3_PROD_ACCT_CUR_ACCOUNT";

  // Tabela TPL_MR_PRVT
  private static final String C_TPL_MR_PRVT = C_PL_SCHEMA + "TPL_MR_PRVT";

  //Usuário que aprovou cadastro do registro
  protected String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  //Data/hora de aprovação do cadastro do registro
  protected String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  //Número da conta investimento
  protected String C_INVST_CUR_ACCT_NBR = "INVST_CUR_ACCT_NBR";

  //Código de Classificação de Risco do Cliente - RDIP
  protected String C_INVST_RISK_ACCT_TYPE_CODE = "INVST_RISK_ACCT_TYPE_CODE";
  
  //Descrição de Classificação de Risco do Cliente - RDIP
  protected String C_INVST_RISK_ACCT_TYPE_DESC = "INVST_RISK_ACCT_TYPE_DESC";
  
  //Segmento do Sistema - Conta Corrente
  protected static final int C_SYS_SEG_VALUE = 1;

  protected final String C_CUR_ACCT_STOP_CODE = "CUR_ACCT_STOP_CODE";

  protected final String C_CUR_ACCT_TYPE_CODE = "CUR_ACCT_TYPE_CODE";

  protected final String C_CUR_ACCT_MKT_CAT_CODE = "CUR_ACCT_MKT_CAT_CODE";

  protected final String C_CUR_ACCT_ACCT_GLC_CODE = "CUR_ACCT_GLC_CODE";

  protected final String C_CUR_ACCT_MOV_LST_DATE = "CUR_ACCT_MOV_LST_DATE";

  protected final String C_CUR_ACCT_FRGN_ACCT_IND = "CUR_ACCT_FRGN_ACCT_IND";

  protected final String C_CUR_ACCT_CPMF_EXMP_IND = "CUR_ACCT_CPMF_EXMP_IND";

  protected final String C_CUR_ACCT_STAT_CODE = "CUR_ACCT_STAT_CODE";

  protected final String C_PRVT_MR_CODE = "PRVT_MR_CODE";
  
  protected final String C_BAL_REF_DATE = "BAL_REF_DATE";
  
  protected final String C_ACCT_AMT = "ACCT_AMT";
  

  /**
   * @see com.citibank.ods.persistence.pl.dao.To3ProductAccountDAO#list(java.math.BigInteger,
   *      java.math.BigInteger, java.math.BigInteger, java.lang.String,
   *      java.lang.String, java.lang.String, java.lang.String)
   */
  public DataSet list( BigInteger reltnNbr_, BigInteger custNbr_,
                      BigInteger curAcctNbr_, String prodCode_,
                      String curAcctNbrBlank_, String custNbrBlank_,
                      String reltnNbrBlank_, String custFullNameText_,
                      String orderBy_,String checkBlank_ )

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
		query.append( "ACCT." + C_PROD_ACCT_CODE + ", " );
		query.append( "ACCT." + C_PROD_UNDER_ACCT_CODE + ", " );
		query.append( "PROD." + C_PROD_NAME + ", " );
		query.append( "ACCT." + C_SYS_SEG_CODE + ", " );
		query.append( "ACCT." + C_PROD_CODE + ", " );
		query.append( "ACCT." + C_SYS_CODE + ", " );
		query.append( "ACCT." + C_CUST_NBR + ", " );
		query.append( "ACCT." + C_RELTN_NBR + ", " );
		query.append( "ACCT." + C_CUR_ACCT_NBR + ", " );
		query.append( "CUST." + C_CUST_FULL_NAME_TEXT + ", " );
		query.append( "POINT." + C_INVST_CUR_ACCT_NBR + ", ");
		query.append( "DECODE(MOV.PROD_ACCT_CODE,NULL,'0','1') APROVACAO");      
		query.append( " FROM " );
		query.append( C_TO3_PRODUCT_ACCOUNT + " ACCT, " );      
		query.append( C_TPL_CUSTOMER_PRVT + " CUST, " );
		query.append( C_TPL_PRODUCT + " PROD, " );
		query.append( C_TO3_PRODUCT_ACCOUNT_MOV + " MOV, " );
		query.append( C_TBG_POINT_ACCT_INVST + " POINT  " );
		query.append( " WHERE " );
		query.append( "ACCT."+ C_CUST_NBR + " = " + "CUST." + C_CUST_NBR + " (+) " );
		query.append( "AND ACCT." + C_PROD_CODE + " = " + "PROD." + C_PROD_CODE + " (+) " );
		query.append( "AND ACCT." + C_SYS_CODE + " = " + "PROD." + C_SYS_CODE + " (+) " );
		query.append( "AND ACCT." + C_SYS_SEG_CODE + " = " + "PROD." + C_SYS_SEG_CODE + " (+) " );
		query.append( "AND LPAD(ACCT." + C_CUR_ACCT_NBR + ",11,'0') = " + "POINT." + C_CUR_ACCT_NBR + " (+) " );
		query.append( "AND ACCT." + C_PROD_ACCT_CODE + " = " + "MOV." + C_PROD_ACCT_CODE + " (+) " );
		query.append( "AND ACCT." + C_PROD_UNDER_ACCT_CODE + " = " + "MOV." + C_PROD_UNDER_ACCT_CODE + " (+) " );

		String criteria = "";

	  if ( checkBlank_ != null && checkBlank_.equals( "true" ) )
	  {
	  	criteria = criteria + "mov.prod_acct_code is null AND ";
	  }
      
      // Verifica se número de relacionamento deve ser NULL (checkbox
      // selecionado)
      if ( reltnNbrBlank_ != null && reltnNbrBlank_.equals( "true" ) )
      {
        criteria = criteria + "ACCT." + C_RELTN_NBR + " IS NULL AND ";
      }
      else if ( reltnNbr_ != null )
      {
        criteria = criteria + "ACCT." + C_RELTN_NBR + " = ? AND ";
      }

      // Verifica se número do client deve ser NULL (checkbox selecionado)
      if ( custNbrBlank_ != null && custNbrBlank_.equals( "true" ) )
      {
        criteria = criteria + "ACCT." + C_CUST_NBR + " IS NULL AND ";
      }
      else if ( custNbr_ != null )
      {
        criteria = criteria + "ACCT." + C_CUST_NBR + " = ? AND ";
      }

      // Verifica se número da conta deve ser NULL (checkbox selecionado)
      if ( curAcctNbrBlank_ != null && curAcctNbrBlank_.equals( "true" ) )
      {
        criteria = criteria + "ACCT." + C_CUR_ACCT_NBR + " IS NULL AND ";
      }
      else if ( curAcctNbr_ != null )
      {
        criteria = criteria + "ACCT." + C_CUR_ACCT_NBR + " = ? AND ";
      }

      if ( prodCode_ != null && !prodCode_.equals( "" ) )
      {
        criteria = criteria + "UPPER(ACCT."+C_PROD_CODE+") LIKE UPPER(?) AND ";
      }

      if ( custFullNameText_ != null && custFullNameText_.length() > 0 )
      {
        criteria = criteria + "UPPER( CUST." + C_CUST_FULL_NAME_TEXT
                   + ") like ? AND ";
      }

      if ( !criteria.equals( "" ) )
      {
        query.append( " AND " + criteria );
        query.append( "ACCT." + C_REC_STAT_CODE + " <> ? " );
      }
      else
      {
        query.append( " AND ACCT." + C_REC_STAT_CODE + " <> ? " );
      }

      if(!orderBy_.equals("")){
		query.append( " ORDER BY " + orderBy_ );      	
      }
      else{
		query.append( " ORDER BY " + C_CUST_FULL_NAME_TEXT );      	
      }      

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( reltnNbr_ != null && reltnNbrBlank_.equals( "false" )
           && reltnNbr_ != null )
      {
        preparedStatement.setLong( count++, reltnNbr_.longValue() );
      }

      if ( custNbr_ != null && custNbrBlank_.equals( "false" ) )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
      }

      if ( curAcctNbr_ != null && curAcctNbrBlank_.equals( "false" ) )
      {
        preparedStatement.setLong( count++, curAcctNbr_.longValue() );
      }

      if ( prodCode_ != null && !prodCode_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%"+prodCode_+"%");
      }

      if ( custFullNameText_ != null && custFullNameText_.length() > 0 )
      {
        preparedStatement.setString( count++, "%" + custFullNameText_.toUpperCase()
                                      + "%" );
      }

      preparedStatement.setString( count++, "I" );

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
  
  
  public BaseTo3ProductAccountEntity findByCurAcct(BaseTo3ProductAccountEntity productAccountEntity_){
  	
	ManagedRdbConnection connection = null;
	CitiStatement preparedStatement = null;
	ResultSet resultSet = null;
	StringBuffer query = new StringBuffer();
	ArrayList productAccountEntities;
	BaseTo3ProductAccountEntity productAccountEntity = null;	
	To3ProductAccountEntityVO productAccountEntityVO;
	
	try{
		connection = OracleODSDAOFactory.getConnection();
		query.append( "SELECT " );
		query.append( "acct.prod_acct_code, " ); 
		query.append( "acct.prod_under_acct_code, " );
		query.append( "acct.cust_nbr, " );
		query.append( "acct.cur_acct_nbr, " );		
		query.append( "point.invst_cur_acct_nbr " );
		query.append( "from  " );
		query.append( "  o3.to3_product_account acct, " );
		query.append( "  BG.TBG_POINT_ACCT_INVST POINT " );		
		query.append( "where  " ); 
		query.append( "	acct.prod_code = '010' " );
		query.append( "and acct.sys_seg_code = 1 " );
		query.append( "and acct.sys_code = 'DA' " );
		query.append( "AND ACCT.CUR_ACCT_NBR = POINT.CUR_ACCT_NBR " );		
		if(productAccountEntity_.getData().getCurAcctNbr() !=null){
			query.append( "and acct.cur_acct_nbr = ? " );			
		}
		else{
			query.append( "AND POINT.INVST_CUR_ACCT_NBR = ? " );
		}		
		
		preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

		if(productAccountEntity_.getData().getCurAcctNbr() !=null){
			preparedStatement.setLong(1,productAccountEntity_.getData().getCurAcctNbr().longValue() );
		}
		else{
			preparedStatement.setString(1,productAccountEntity_.getData().getInvestCurAcctNbr() );
		}
		
		
		preparedStatement.replaceParametersInQuery(query.toString());
		resultSet = preparedStatement.executeQuery();
				
		int resultLength = 0;
		while(resultSet.next()){
			productAccountEntity = new To3ProductAccountEntity();
			productAccountEntityVO = ( To3ProductAccountEntityVO ) productAccountEntity.getData();	
			
			productAccountEntityVO.setProdAcctCode( new BigInteger(resultSet.getString( this.C_PROD_ACCT_CODE ) ) );
			productAccountEntityVO.setProdUnderAcctCode( new BigInteger(resultSet.getString( this.C_PROD_UNDER_ACCT_CODE ) ) );
			productAccountEntityVO.setCustNbr( new BigInteger(resultSet.getString( this.C_CUST_NBR ) ) );
			productAccountEntityVO.setCurAcctNbr( new BigInteger(resultSet.getString( this.C_CUR_ACCT_NBR ) ) );
			productAccountEntityVO.setInvestCurAcctNbr(resultSet.getString( this.C_INVST_CUR_ACCT_NBR));
			resultLength ++;
		}
		
		if(resultLength == 0){
			throw new NoRowsReturnedException();
		}
		else if (resultLength > 1){
			throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );						
		}
		
		return productAccountEntity;
		
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
   * @see com.citibank.ods.persistence.pl.dao.BaseTo3ProductAccountDAO#find(com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity)
   */
  public BaseTo3ProductAccountEntity find(
                                          BaseTo3ProductAccountEntity productAccountEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList productAccountEntities;
    BaseTo3ProductAccountEntity productAccountEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( "ACCT." +C_PROD_ACCT_CODE + ", " );
      query.append( "ACCT." +C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( "ACCT." +C_CUST_NBR + ", " );
      query.append( "ACCT." + C_RELTN_NBR + ", " );
      query.append( "ACCT." + C_CUR_ACCT_NBR + ", " );
	  query.append( "POINT.INVST_CUR_ACCT_NBR, ");
      query.append( "ACCT." +C_PROD_CODE + ", " );
      query.append( "ACCT." +C_SYS_CODE + ", " );
      query.append( "ACCT." +C_SYS_SEG_CODE + ", " );
      query.append( "ACCT." +C_ORIG_PROD_ACCT_NBR + ", " );
      query.append( "ACCT." +C_PROD_ACCT_STA_DATE + ", " );
      query.append( "ACCT." +C_PROD_ACCT_END_DATE + ", " );
      query.append( "ACCT." +C_PROD_ACCT_SIT_CODE + ", " );
      query.append( "ACCT." +C_PROD_PLCY_23A_IND + ", " );
      query.append( "ACCT." +C_PROD_PLCY_23B_IND + ", " );
      query.append( "ACCT." +C_PROD_ACCT_LEGAL_BUS_CODE + ", " );
      query.append( "ACCT." +C_PROD_ACCT_PORTF_MGMT_CODE + "," );
      query.append( "ACCT." +C_PROD_ACCT_ISIN_CODE + ", " );
      query.append( "ACCT." +C_REC_STAT_CODE + ", ");
      query.append( "DECODE( BAL.BAL_REF_DATE,NULL,MBAL.BAL_REF_DATE,BAL.BAL_REF_DATE ) BAL_REF_DATE, " );
	  query.append( "DECODE( BAL.BAL_ACCT_AMT,NULL,MBAL.Total_Actl_Amt,BAL.BAL_ACCT_AMT ) ACCT_AMT " ); 
      query.append( " FROM " );
      query.append( " O3.TO3_BALANCE bal," );
	  query.append( " o3.TO3_PORTF_MGMT_BALANCE mBal, " );
	  query.append( " o3.to3_product_account acct, ");
	  query.append( " bg.tbg_point_acct_invst point, ");	  
	  query.append( " (select b.prod_acct_code," );
	  query.append( " b.prod_under_acct_code," );
	  query.append( " max(b.bal_ref_date) dt_ref" );
	  query.append( " from o3.TO3_BALANCE b" );
	  query.append( " group by b.prod_acct_code," );     
	  query.append( " b.prod_under_acct_code) bal_tmp," );
      query.append( " (select b.prod_acct_code," );
	  query.append( " b.prod_under_acct_code," );
	  query.append( " max(b.bal_ref_date) dt_ref" );
	  query.append( " from o3.TO3_PORTF_MGMT_BALANCE b" );
	  query.append( " group by b.prod_acct_code," );
	  query.append( " b.prod_under_acct_code) mbal_tmp" );       
	  query.append( " WHERE " );
	  query.append( " acct.PROD_ACCT_CODE = bal_tmp.PROD_ACCT_CODE(+)" );
	  query.append( " AND acct.PROD_UNDER_ACCT_CODE = bal_tmp.PROD_UNDER_ACCT_CODE(+)" );  
   	  query.append( " AND acct.PROD_ACCT_CODE = mbal_tmp.PROD_ACCT_CODE(+)" );
	  query.append( " AND acct.PROD_UNDER_ACCT_CODE = mbal_tmp.PROD_UNDER_ACCT_CODE(+)" );  
   	  query.append( " AND bal_tmp.PROD_ACCT_CODE       = bal.PROD_ACCT_CODE(+)" );
	  query.append( " AND bal_tmp.PROD_UNDER_ACCT_CODE = bal.PROD_UNDER_ACCT_CODE(+)" );
	  query.append( " AND bal_tmp.DT_REF               = bal.bal_ref_date(+)" );
   	  query.append( " AND mbal_tmp.PROD_ACCT_CODE = mbal.PROD_ACCT_CODE(+)" );
	  query.append( " AND mbal_tmp.PROD_UNDER_ACCT_CODE = mbal.PROD_UNDER_ACCT_CODE(+)" );
	  query.append( " AND Mbal_tmp.DT_REF               = mbal.bal_ref_date(+) " );
	  query.append( " AND acct.cur_acct_nbr = POINT.CUR_ACCT_NBR(+) " );
      query.append( " AND ACCT.PROD_ACCT_CODE = ? " );
	  query.append( " AND ACCT.PROD_UNDER_ACCT_CODE = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         productAccountEntity_.getData().getProdAcctCode().longValue() );
      preparedStatement.setLong(
                         2,
                         productAccountEntity_.getData().getProdUnderAcctCode().longValue() );

      resultSet = preparedStatement.executeQuery();

	  preparedStatement.replaceParametersInQuery(query.toString());

      productAccountEntities = instantiateFromResultSet( resultSet );

      if ( productAccountEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( productAccountEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        productAccountEntity = ( BaseTo3ProductAccountEntity ) productAccountEntities.get( 0 );
      }

      return productAccountEntity;
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
  
  public BaseTo3ProductAccountEntity findByPK(BaseTo3ProductAccountEntity productAccountEntity_ )
  {
      ManagedRdbConnection connection = null;
	  CitiStatement preparedStatement = null;
	  ResultSet resultSet = null;
	  StringBuffer query = new StringBuffer();
	  ArrayList productAccountEntities;
	  BaseTo3ProductAccountEntity productAccountEntity = null;

	  try
	  {
		connection = OracleODSDAOFactory.getConnection();
		query.append( "SELECT " );
		query.append( "ACCT." +C_PROD_ACCT_CODE + ", " );
		query.append( "ACCT." +C_PROD_UNDER_ACCT_CODE + ", " );
		query.append( "ACCT." +C_CUST_NBR + ", " );
		query.append( "ACCT." + C_RELTN_NBR + ", " );
		query.append( "ACCT." + C_CUR_ACCT_NBR + ", " );
		query.append( "POINT.INVST_CUR_ACCT_NBR, ");
		query.append( "ACCT." +C_PROD_CODE + ", " );
		query.append( "ACCT." +C_SYS_CODE + ", " );
		query.append( "ACCT." +C_SYS_SEG_CODE + ", " );
		query.append( "ACCT." +C_ORIG_PROD_ACCT_NBR + ", " );
		query.append( "ACCT." +C_PROD_ACCT_STA_DATE + ", " );
		query.append( "ACCT." +C_PROD_ACCT_END_DATE + ", " );
		query.append( "ACCT." +C_PROD_ACCT_SIT_CODE + ", " );
		query.append( "ACCT." +C_PROD_PLCY_23A_IND + ", " );
		query.append( "ACCT." +C_PROD_PLCY_23B_IND + ", " );
		query.append( "ACCT." +C_PROD_ACCT_LEGAL_BUS_CODE + ", " );
		query.append( "ACCT." +C_PROD_ACCT_PORTF_MGMT_CODE + "," );
		query.append( "ACCT." +C_PROD_ACCT_ISIN_CODE + ", " );
		query.append( "ACCT." +C_REC_STAT_CODE + ", ");
		query.append( "'' BAL_REF_DATE, " );
		query.append( "'' ACCT_AMT " );		 
		query.append( " FROM " );		
		query.append( " o3.to3_product_account acct, ");
		query.append( " bg.tbg_point_acct_invst point "); 
		       
		query.append( " WHERE " );		
		query.append( "     acct.cur_acct_nbr = POINT.CUR_ACCT_NBR(+) " );
		query.append( " AND ACCT.PROD_ACCT_CODE = ? " );
		query.append( " AND ACCT.PROD_UNDER_ACCT_CODE = ? " );

		preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

		preparedStatement.setLong(1,productAccountEntity_.getData().getProdAcctCode().longValue() );
		preparedStatement.setLong(2,productAccountEntity_.getData().getProdUnderAcctCode().longValue() );

		resultSet = preparedStatement.executeQuery();

		preparedStatement.replaceParametersInQuery(query.toString());

		productAccountEntities = instantiateFromResultSet( resultSet );

		if ( productAccountEntities.size() == 0 )
		{
		  throw new NoRowsReturnedException();
		}
		else if ( productAccountEntities.size() > 1 )
		{
		  throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
		}
		else
		{
		  productAccountEntity = ( BaseTo3ProductAccountEntity ) productAccountEntities.get( 0 );
		}

		return productAccountEntity;
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
    ArrayList productAccountEntities = new ArrayList();
    To3ProductAccountEntity productAccountEntity;
    To3ProductAccountEntityVO productAccountEntityVO;

    try
    {
      while ( resultSet_.next() )
      {
        productAccountEntity = new To3ProductAccountEntity();
        productAccountEntityVO = ( To3ProductAccountEntityVO ) productAccountEntity.getData();
		        
        productAccountEntityVO.setProdAcctCode( new BigInteger(
                                                                resultSet_.getString( this.C_PROD_ACCT_CODE ) ) );
        productAccountEntityVO.setProdUnderAcctCode( new BigInteger(
                                                                     resultSet_.getString( this.C_PROD_UNDER_ACCT_CODE ) ) );
        if ( resultSet_.getString( this.C_CUST_NBR ) != null )
        {
          productAccountEntityVO.setCustNbr( new BigInteger(
                                                             resultSet_.getString( this.C_CUST_NBR ) ) );
        }
        if ( resultSet_.getString( this.C_RELTN_NBR ) != null )
        {
          productAccountEntityVO.setReltnNbr( new BigInteger(
                                                              resultSet_.getString( this.C_RELTN_NBR ) ) );
        }

        productAccountEntityVO.setProdCode( resultSet_.getString( this.C_PROD_CODE ) );
        productAccountEntityVO.setSysCode( resultSet_.getString( this.C_SYS_CODE ) );
        if ( resultSet_.getString( this.C_SYS_SEG_CODE ) != null )
        {
          productAccountEntityVO.setSysSegCode( new BigInteger(
                                                                resultSet_.getString( this.C_SYS_SEG_CODE ) ) );
        }

        if ( resultSet_.getString( this.C_CUR_ACCT_NBR ) != null )
        {
          productAccountEntityVO.setCurAcctNbr( new BigInteger(
                                                                resultSet_.getString( this.C_CUR_ACCT_NBR ) ) );
        }

        if ( resultSet_.getString( this.C_ORIG_PROD_ACCT_NBR ) != null )
        {
          productAccountEntityVO.setOrigProdAcctNbr( resultSet_.getString( this.C_ORIG_PROD_ACCT_NBR ) );
        }

        if ( resultSet_.getTimestamp( this.C_PROD_ACCT_STA_DATE ) != null )
        {
          productAccountEntityVO.setProdAcctStaDate( resultSet_.getTimestamp( this.C_PROD_ACCT_STA_DATE ) );
        }

        if ( resultSet_.getTimestamp( this.C_PROD_ACCT_END_DATE ) != null )
        {
          productAccountEntityVO.setProdAcctEndDate( resultSet_.getTimestamp( this.C_PROD_ACCT_END_DATE ) );
        }

        if ( resultSet_.getString( this.C_PROD_ACCT_SIT_CODE ) != null )
        {
          productAccountEntityVO.setProdAcctSitCode( resultSet_.getString( this.C_PROD_ACCT_SIT_CODE ) );
        }

        if ( resultSet_.getString( this.C_REC_STAT_CODE ) != null )
        {
          productAccountEntityVO.setRecStatCode( resultSet_.getString( this.C_REC_STAT_CODE ) );
        }
		
        if ( resultSet_.getString( this.C_PROD_ACCT_LEGAL_BUS_CODE ) != null )
        {
          productAccountEntityVO.setProdAcctLegalBusCode( new BigInteger(
                                                                          resultSet_.getString( this.C_PROD_ACCT_LEGAL_BUS_CODE ) ) );
        }
        productAccountEntityVO.setProdAcctIsinCode( resultSet_.getString( this.C_PROD_ACCT_ISIN_CODE ) );
        productAccountEntityVO.setProdAcctPlcy23aInd( resultSet_.getString( this.C_PROD_PLCY_23A_IND ) );
        productAccountEntityVO.setProdAcctPlcy23bInd( resultSet_.getString( this.C_PROD_PLCY_23B_IND ) );
        productAccountEntityVO.setProdAcctPortfMgmtCode( resultSet_.getString( this.C_PROD_ACCT_PORTF_MGMT_CODE ) );
        
		if ( resultSet_.getTimestamp( this.C_BAL_REF_DATE ) != null )
		{
			productAccountEntityVO.setBalRefDate( resultSet_.getTimestamp( this.C_BAL_REF_DATE ) );
		}
        		
		if(resultSet_.getString( this.C_ACCT_AMT ) != null){
			productAccountEntityVO.setAcctAmt( new BigDecimal(resultSet_.getString( this.C_ACCT_AMT ) ) );	
		}
		
		if(resultSet_.getString("INVST_CUR_ACCT_NBR" ) != null){
			productAccountEntityVO.setInvestCurAcctNbr(resultSet_.getString("INVST_CUR_ACCT_NBR" ));	
		}

        productAccountEntities.add( productAccountEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return productAccountEntities;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTo3ProductAccountDAO#update(com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity)
   */
  public void update( BaseTo3ProductAccountEntity productAccountEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "UPDATE " );
      query.append( C_TO3_PRODUCT_ACCOUNT );
      query.append( " SET " );
      query.append( C_CUST_NBR + " = ?, " );
      query.append( C_RELTN_NBR + " = ?, " );
      query.append( C_CUR_ACCT_NBR + " = ?, " );
      query.append( C_PROD_CODE + " = ?, " );
      query.append( C_PROD_PLCY_23A_IND + " = ?, " );
      query.append( C_PROD_PLCY_23B_IND + " = ?, " );
      query.append( C_PROD_ACCT_ISIN_CODE + " = ?, " );
      query.append( C_PROD_ACCT_PORTF_MGMT_CODE + " = ?, " );
      query.append( C_PROD_ACCT_LEGAL_BUS_CODE + " = ?, " );
      query.append( C_LAST_UPD_DATE).append(" =  ?, ");
      query.append( C_LAST_UPD_USER_ID).append(" =  ?, ");
      query.append( C_LAST_AUTH_DATE).append(" =  ?, ");
      query.append( C_LAST_AUTH_USER_ID).append(" =  ? ");
      
      query.append( "WHERE " );
      query.append( C_PROD_ACCT_CODE + " = ? AND " );
      query.append( C_PROD_UNDER_ACCT_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;
      if ( productAccountEntity_.getData().getCustNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           productAccountEntity_.getData().getCustNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( productAccountEntity_.getData().getReltnNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           productAccountEntity_.getData().getReltnNbr().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, java.sql.Types.BIGINT );
      }

      if ( productAccountEntity_.getData().getCurAcctNbr() != null )
      {
        preparedStatement.setLong(
                           count++,
                           productAccountEntity_.getData().getCurAcctNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( productAccountEntity_.getData().getProdCode() != null )
      {
        preparedStatement.setString( count++,
                             productAccountEntity_.getData().getProdCode() );
      }
      else
      {
        preparedStatement.setNull( count++, Types.BIGINT );
      }

      if ( productAccountEntity_.getData().getProdAcctPlcy23aInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             productAccountEntity_.getData().getProdAcctPlcy23aInd() );
      }
      else
      {
        preparedStatement.setNull( count++, Types.BIGINT );
      }

      if ( productAccountEntity_.getData().getProdAcctPlcy23bInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             productAccountEntity_.getData().getProdAcctPlcy23bInd() );
      }
      else
      {
        preparedStatement.setNull( count++, Types.BIGINT );
      }

      if ( productAccountEntity_.getData().getProdAcctIsinCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             productAccountEntity_.getData().getProdAcctIsinCode() );
      }
      else
      {
        preparedStatement.setNull( count++, Types.BIGINT );
      }

      if ( productAccountEntity_.getData().getProdAcctPortfMgmtCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             productAccountEntity_.getData().getProdAcctPortfMgmtCode() );
      }
      else
      {
        preparedStatement.setNull( count++, Types.BIGINT );
      }

      if ( productAccountEntity_.getData().getProdAcctLegalBusCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           productAccountEntity_.getData().getProdAcctLegalBusCode().longValue() );
      }
      else
      {
        preparedStatement.setNull( count++, Types.BIGINT );
      }
      
      if ( productAccountEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                           count++,
                           new Timestamp(productAccountEntity_.getData().getLastUpdDate().getTime()) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }
      
      if ( productAccountEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                           count++,
                           productAccountEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setNull( count++, Types.VARCHAR );
      }

      if ( productAccountEntity_.getData().getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                           count++,
                           new Timestamp(
                        		   productAccountEntity_.getData().getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, Types.TIMESTAMP );
      }

      if ( productAccountEntity_.getData().getLastAuthUserId() != null )
      {
        preparedStatement.setString(
                           count++,
                           productAccountEntity_.getData().getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setNull( count++, Types.VARCHAR );
      }
      

      if ( productAccountEntity_.getData().getProdAcctCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           productAccountEntity_.getData().getProdAcctCode().longValue() );
      }
      else
      {
        preparedStatement.setNull( count++, Types.BIGINT );
      }

      if ( productAccountEntity_.getData().getProdUnderAcctCode() != null )
      {
        preparedStatement.setLong(
        				count++,
                           productAccountEntity_.getData().getProdUnderAcctCode().longValue() );
      }
      else
      {
        preparedStatement.setNull( count++, Types.BIGINT );
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

  /**
   * @see com.citibank.ods.persistence.pl.dao.To3ProductAccountDAO#list(java.math.BigInteger,
   *      java.math.BigInteger, java.math.BigInteger, java.lang.String,
   *      java.lang.String)
   */
  public DataSet list( BigInteger reltnNbr_, BigInteger custNbr_,
                      BigInteger curAcctNbr_, String custFullNameText_ )
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
      query.append( "ACCT." + C_SYS_CODE + ", " );
      query.append( "CUST." + C_CUST_NBR + ", " );
      query.append( "ACCT." + C_PROD_ACCT_CODE + ", " );
      query.append( "ACCT." + C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( "CUST." + C_CUST_FULL_NAME_TEXT + ", " );
      query.append( "ACCT." + C_RELTN_NBR + ", " );
      query.append( "ACCT." + C_CUR_ACCT_NBR + ", " );
      query.append( "ACCOUNT." + C_CUR_ACCT_ACCT_GLC_CODE + ", " );
      query.append( "ACCOUNT." + C_CUR_ACCT_CPMF_EXMP_IND + ", " );
      query.append( "ACCOUNT." + C_CUR_ACCT_FRGN_ACCT_IND + ", " );
      query.append( "ACCOUNT." + C_CUR_ACCT_MKT_CAT_CODE + ", " );
      query.append( "ACCOUNT." + C_CUR_ACCT_STAT_CODE + ", " );
      query.append( "ACCOUNT." + C_CUR_ACCT_STOP_CODE + ", " );
      query.append( "ACCOUNT." + C_CUR_ACCT_TYPE_CODE + ", " );
      query.append( "ACCOUNT." + C_CUR_ACCT_MOV_LST_DATE + ", " );
      query.append( "POINT." + C_INVST_CUR_ACCT_NBR + ", " );
      query.append( "MR." + C_PRVT_MR_CODE + ", " );
      query.append( "(Case when MR." + C_PROD_ACCT_CODE
                    + " IS NULL THEN 0  ELSE 1 END) AS QTD," );

      /*
       * Objetivo: Implementação RDIP - Classificação de Risco
       * Responsável: Alexandre Bianchini - Eversystems
      */
      query.append(" CASE ACCOUNT." + C_INVST_RISK_ACCT_TYPE_CODE);
      query.append(	" WHEN "+ RECOMMENDED_ACCOUNT_CODE +" THEN '"+getCurrentAccountDescription(RECOMMENDED_ACCOUNT_CODE)+"'");
      query.append(	" WHEN "+ TRADING_ACCOUNT_CODE +" THEN '"+getCurrentAccountDescription(TRADING_ACCOUNT_CODE)+"'");
      query.append(	" WHEN "+ DESIGNATED_ACCOUNT_CODE +" THEN '"+getCurrentAccountDescription(DESIGNATED_ACCOUNT_CODE)+"'");
      query.append(	" ELSE '"+getCurrentAccountDescription(BLANK_ACCOUNT_CODE)+"'");
      query.append(" END AS "+ C_INVST_RISK_ACCT_TYPE_DESC);
      
      query.append( " FROM " );
      query.append( C_TO3_PRODUCT_ACCOUNT + " ACCT, " );
      query.append( " PL.TPL_RELATION_PRVT RELTN, " );
      query.append( C_TPL_CUSTOMER_PRVT + " CUST, " );
      query.append( C_TO3_PROD_ACCT_CUR_ACCOUNT + " ACCOUNT, " );
      query.append( C_TBG_POINT_ACCT_INVST + " POINT, " );
      query.append( C_TPL_MR_PRVT + " MR " );
      query.append( " WHERE ACCT.SYS_CODE = 'DA' AND " );
      query.append( "ACCT.PROD_CODE = '010' AND SYS_SEG_CODE = 1 AND " );
      query.append( "ACCT." + C_RELTN_NBR + " = " );
      query.append( "RELTN." + C_RELTN_NBR + " AND " );
      query.append( "RELTN.RELTN_CUST_1_NBR = " );
      query.append( "CUST." + C_CUST_NBR + " AND " );
      query.append( "ACCT." + C_CUR_ACCT_NBR + " = " );
      query.append( "POINT." + C_CUR_ACCT_NBR + "(+) AND " );
      query.append( "ACCT." + C_PROD_ACCT_CODE + " = ACCOUNT."
                    + C_PROD_ACCT_CODE + "(+) AND " );
      query.append( "ACCT." + C_PROD_UNDER_ACCT_CODE + " = ACCOUNT."
                    + C_PROD_UNDER_ACCT_CODE + "(+) AND " );
      query.append( "ACCT." + C_PROD_ACCT_CODE + " = MR." + C_PROD_ACCT_CODE
                    + "(+) AND " );
      query.append( "ACCT." + C_PROD_UNDER_ACCT_CODE + " = MR."
                    + C_PROD_UNDER_ACCT_CODE + "(+) AND " );
      query.append( "(MR." + C_PROD_ACCT_CODE + " IS NULL OR MR."
                    + C_REC_STAT_CODE + " <> 'I')" );

      String criteria = "";

      if ( reltnNbr_ != null )
      {
        criteria = criteria + "ACCT." + C_RELTN_NBR + " = ? AND ";
      }

      if ( custNbr_ != null )
      {
        criteria = criteria + "CUST." + C_CUST_NBR + " = ? AND ";
      }

      if ( curAcctNbr_ != null )
      {
        criteria = criteria + "ACCT." + C_CUR_ACCT_NBR + " = ? AND ";
      }

      if ( custFullNameText_ != null )
      {
        criteria = criteria + "UPPER(\"" + C_CUST_FULL_NAME_TEXT
                   + "\") like ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        query.append( " AND " + criteria.substring( 0, criteria.length() - 5 ) );
      }

      query.append( " ORDER BY " + C_CUST_FULL_NAME_TEXT );

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

      if ( custFullNameText_ != null )
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
   * @see com.citibank.ods.persistence.pl.dao.BaseTo3ProductAccountDAO#loadProdCodeDomain()
   */
  public DataSet loadProdCodeDomain()
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT DISTINCT " );
      query.append( C_PROD_CODE + ", " );
      query.append( "PROD_NAME" );
      query.append( " FROM " );
      query.append( C_TPL_PRODUCT );
      query.append( " ORDER BY " + C_PROD_CODE );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

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

  public ArrayList selectByPk( BigInteger prodAcctCode_,
                              BigInteger prodUnderAcctCode_ )
  {
    return null;
  }

  /**
   * Verifica se existe um registro com o código passado
   */
  public boolean exists( To3ProductAccountEntity to3ProductAccountEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( to3ProductAccountEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  public To3ProductAccountEntity insert(
                                        To3ProductAccountEntity to3ProductAccountEntity_ )
  {

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TO3_PRODUCT_ACCOUNT + " ( " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE + "," );
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
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID );

      query.append( "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         to3ProductAccountEntity_.getData().getProdAcctCode().longValue() );
      preparedStatement.setLong(
                         2,
                         to3ProductAccountEntity_.getData().getProdUnderAcctCode().longValue() );
      preparedStatement.setLong(
                         3,
                         to3ProductAccountEntity_.getData().getCustNbr().longValue() );
      preparedStatement.setLong(
                         4,
                         to3ProductAccountEntity_.getData().getReltnNbr().longValue() );
      preparedStatement.setLong(
                         5,
                         to3ProductAccountEntity_.getData().getCurAcctNbr().longValue() );
      preparedStatement.setString( 6, to3ProductAccountEntity_.getData().getProdCode() );
      preparedStatement.setString(
                           7,
                           to3ProductAccountEntity_.getData().getProdAcctPlcy23aInd() );
      preparedStatement.setString(
                           8,
                           to3ProductAccountEntity_.getData().getProdAcctPlcy23bInd() );
      preparedStatement.setString(
                           9,
                           to3ProductAccountEntity_.getData().getProdAcctIsinCode() );
      preparedStatement.setString(
                           10,
                           to3ProductAccountEntity_.getData().getProdAcctPortfMgmtCode() );
      preparedStatement.setLong(
                         11,
                         to3ProductAccountEntity_.getData().getProdAcctLegalBusCode().longValue() );
      preparedStatement.setString( 12, to3ProductAccountEntity_.getData().getSysCode() );
      preparedStatement.setInt(
                        13,
                        to3ProductAccountEntity_.getData().getSysSegCode().intValue() );

      preparedStatement.setString(
                           14,
                           to3ProductAccountEntity_.getData().getOrigProdAcctNbr() );

      preparedStatement.setTimestamp(
                              15,
                              new Timestamp(
                                             to3ProductAccountEntity_.getData().getLastUpdDate().getTime() ) );

      preparedStatement.setString(
                           16,
                           to3ProductAccountEntity_.getData().getLastUpdUserId() );

      preparedStatement.setTimestamp(
                              17,
                              new Timestamp(
                                             to3ProductAccountEntity_.getData().getLastAuthDate().getTime() ) );

      preparedStatement.setString(
                           18,
                           to3ProductAccountEntity_.getData().getLastAuthUserId() );

      preparedStatement.execute();
      
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
    return to3ProductAccountEntity_;
  }

  /**
   * Retorna se um registro existente esta ativo
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProductDAO#existsActive(com.citibank.ods.entity.pl.TplProductEntity)
   */
  public boolean existsActive( To3ProductAccountEntity to3ProductAccountEntity_ )
                                                                                 throws UnexpectedException
  {
    boolean exists = true;

    try
    {
      To3ProductAccountEntity to3ProductAccountEntity = ( To3ProductAccountEntity ) this.find( to3ProductAccountEntity_ );
      To3ProductAccountEntityVO to3ProductAccountEntityVO = ( To3ProductAccountEntityVO ) to3ProductAccountEntity.getData();
      if ( !To3ProductAccountEntity.C_REC_STAT_CODE_ACTIVE.equals( to3ProductAccountEntityVO.getRecStatCode() ) )
      {
        exists = false;
      }
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }
}