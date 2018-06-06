package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.entity.pl.TplContactCustEntity;
import com.citibank.ods.entity.pl.valueobject.TplContactCustEntityVO;
import com.citibank.ods.persistence.pl.dao.BaseTplMrDocCallbackDAO;

/**
 * @author m.nakamura
 * 
 * Classe abstrata para acesso ao banco de dados de Telefones de Confirmação da
 * Memória de Risco.
 */
public abstract class BaseOracleTplMrDocCallbackDAO extends BaseOracleDAO
    implements BaseTplMrDocCallbackDAO
{
  // Numero do Contato
  protected String C_CTC_NBR = "CTC_NBR";

  // Numero do Cliente no CMS (Customer Number)
  protected String C_CUST_NBR = "CUST_NBR";

  // Data e Hora da Ultima Atualizacao Efetuada pelo Usuario.
  protected String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  // Codigo do Usuario que Efetuou a Ultima Atualizacao no Registro.
  protected String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  // Codigo do Callback
  protected String C_MR_CALLBACK_CODE = "MR_CALLBACK_CODE";

  // Codigo da Conta Produto
  protected String C_PROD_ACCT_CODE = "PROD_ACCT_CODE";

  // Codigo da Sub-conta Produto
  protected String C_PROD_UNDER_ACCT_CODE = "PROD_UNDER_ACCT_CODE";

  /**
   * Instancia entidades de contatos a partir do ResultSet.
   * 
   * @param resultSet_ - ResultSet utilizado para instanciar entidades.
   * @return ArrayList - Entidades instanciadas a partir do ResultSet.
   */
  protected ArrayList instantiateContactCustFromResultSet( ResultSet resultSet_ )
  {

    TplContactCustEntity contactCustEntity;
    TplContactCustEntityVO contactCustEntityVO;
    ArrayList contactCustEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        contactCustEntity = new TplContactCustEntity();
        contactCustEntityVO = ( TplContactCustEntityVO ) contactCustEntity.getData();

        contactCustEntityVO.setCtcNbr( new BigInteger(
                                                       resultSet_.getString( C_CTC_NBR ) ) );

        contactCustEntityVO.setCustNbr( new BigInteger(
                                                        resultSet_.getString( C_CUST_NBR ) ) );

        if ( resultSet_.getString( "FULL_NAME_TEXT" ) != null )
        {
          contactCustEntityVO.setFullNameText( resultSet_.getString( "FULL_NAME_TEXT" ) );
        }
        
		if ( resultSet_.getString( "FULL_NAME_1_TEXT" ) != null )
		{
		  contactCustEntityVO.setFullNameText_2( resultSet_.getString( "FULL_NAME_1_TEXT" ) );
		}

		if ( resultSet_.getString( "FULL_NAME_2_TEXT" ) != null )
		{
		  contactCustEntityVO.setFullNameText_3( resultSet_.getString( "FULL_NAME_2_TEXT" ) );
		}

        if ( resultSet_.getString( "PHONE_DDD_CODE" ) != null )
        {
          contactCustEntityVO.setPhoneDddCode( new BigInteger(
                                                               resultSet_.getString( "PHONE_DDD_CODE" ) ) );
        }

        if ( resultSet_.getString( "PHONE_NBR" ) != null )
        {
          contactCustEntityVO.setPhoneNbr( new BigInteger(
                                                           resultSet_.getString( "PHONE_NBR" ) ) );
        }

        if ( resultSet_.getString( "PHONE_EXTN_NBR" ) != null )
        {
          contactCustEntityVO.setPhoneExtnNbr( new BigInteger(
                                                               resultSet_.getString( "PHONE_EXTN_NBR" ) ) );
        }

        if ( resultSet_.getTimestamp( "LAST_AUTH_DATE" ) != null )
        {
          contactCustEntityVO.setLastAuthDate( resultSet_.getTimestamp( "LAST_AUTH_DATE" ) );
        }

        if ( resultSet_.getString( "LAST_AUTH_USER_ID" ) != null )
        {
          contactCustEntityVO.setLastAuthUserId( resultSet_.getString( "LAST_AUTH_USER_ID" ) );
        }

        if ( resultSet_.getTimestamp( "LAST_UPD_DATE" ) != null )
        {
          contactCustEntityVO.setLastUpdDate( resultSet_.getTimestamp( "LAST_UPD_DATE" ) );
        }

        if ( resultSet_.getString( "LAST_UPD_USER_ID" ) != null )
        {
          contactCustEntityVO.setLastUpdUserId( resultSet_.getString( "LAST_UPD_USER_ID" ) );
        }

        if ( resultSet_.getString( "REC_STAT_CODE" ) != null )
        {
          contactCustEntityVO.setRecStatCode( resultSet_.getString( "REC_STAT_CODE" ) );
        }
        
        try {        
        	if (resultSet_.getString( "OPERN_CODE" ) != null )
        	{
        		contactCustEntityVO.setOpernCode( resultSet_.getString( "OPERN_CODE" ) );
        	}
        } catch ( SQLException e )
            {
            }	
        
        	

        contactCustEntities.add( contactCustEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return contactCustEntities;
  }
}