package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplProductEntity;
import com.citibank.ods.entity.pl.TplProductHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplProductHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProductHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
/**
 * 
 * *@author acacio.domingos,Apr 16, 2007
 *  
 */

public class OracleTplProductHistDAO extends BaseOracleTplProductDAO implements
    TplProductHistDAO
{
  /*
   * Constante que identifica o nome da tabela de histórico
   */
  private static final String C_TPL_PRODUCT_HIST = C_PL_SCHEMA
                                                   + "TPL_PRODUCT_HIST";

  /*
   * Campos específicos da tabela
   */
  private String C_PROD_REF_DATE = "PROD_REF_DATE";

  private String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private String C_REC_STAT_CODE = "REC_STAT_CODE";
  
  private String C_ASSET_TYPE_CODE = "ASSET_TYPE_CODE";

  /**
   * Insere um registro de Produto
   * @see com.citibank.ods.persistence.pl.dao.TplProductHistDAO#insert(com.citibank.ods.entity.pl.TplProductHistEntity)
   */
  public TplProductHistEntity insert( TplProductHistEntity tplProductHistEntity_ )
                                                                                  throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_PRODUCT_HIST + " (" );
      query.append( C_PROD_CODE + ", " );
      query.append( C_SYS_CODE + ", " );
      query.append( C_SYS_SEG_CODE + ", " );
      query.append( C_PROD_REF_DATE + ", " );
      query.append( C_PROD_NAME + ", " );
      query.append( C_PROD_TEXT + ", " );
      query.append( C_PROD_FAML_CODE + ", " );
      query.append( C_PROD_CCY_CODE + ", " );
      query.append( C_PROD_ISO_CODE + ", " );
      query.append( C_PROD_ANBID_CODE + ", " );
      query.append( C_PROD_CETIP_CODE + ", " );
      query.append( C_PROD_SELIC_CODE + ", " );
      query.append( C_PROD_BOVESPA_CODE + ", " );
      query.append( C_PROD_BMF_CODE + ", " );
      query.append( C_PROD_CREATE_DATE + ", " );
      query.append( C_PROD_STAT_CODE + ", " );
      query.append( C_PROD_CR_TYPE_CLASS_CODE + ", " );
      query.append( C_PRVT_PROD_AGGR_CODE + ", " );
      query.append( C_PROD_PROC_SYS_CODE + ", " );
      query.append( C_PROD_PROC_SYS_SEG_CODE + ", " );
      query.append( C_PROD_APPRV_DATE + ", " );
      query.append( C_PROD_OPERN_STA_DATE + ", " );
      query.append( C_PROD_CSTDY_CNPJ_NBR + ", " );
      query.append( C_PROD_AUDIT_CNPJ_NBR + ", " );
      query.append( C_PROD_MGMT_CNPJ_NBR + ", " );
      query.append( C_PROD_CTL_CNPJ_NBR + ", " );
      query.append( C_PROD_ADMIN_CNPJ_NBR + ", " );
      query.append( C_CITI_GRP_TIE_RELTN_PLCY_IND + ", " );
      query.append( C_CITI_GRP_TIE_RSTRN_PLCY_IND + ", " );
      query.append( C_PROD_ISIN_CODE + ", " );
      query.append( C_PROD_INVST_RISK_CODE + ", " );
      query.append( C_PROD_QLFY_CODE + ", " );
      query.append( C_PROD_SUB_FAML_CODE + ", " );
      query.append( C_PROD_LEGAL_CLASS_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_REC_STAT_CODE + ", ");
	  query.append( C_ASSET_TYPE_CODE + ", " );
	  
      query.append( C_PROD_SENT_IND + ", " );
      query.append( C_PRCLAS_PROD_ASSET_CLASS_CODE + ", " );
      query.append( C_PRCLAS_PROD_STYP_CODE + ", " );
      query.append( C_PRCLAS_PROD_TYPE_CODE );  	  
	  
      query.append( " ) VALUES ( " );
      query.append( " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " );
      query.append( " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplProductHistEntity_.getData().getProdCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductHistEntity_.getData().getProdCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProductHistEntity_.getData().getSysCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductHistEntity_.getData().getSysCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProductHistEntity_.getData().getSysSegCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductHistEntity_.getData().getSysSegCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      TplProductHistEntityVO tplProductHistEntityVO = ( TplProductHistEntityVO ) tplProductHistEntity_.getData();

      if ( tplProductHistEntityVO.getProdRefDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductHistEntityVO.getProdRefDate().getTime() ) );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdName() != null )
      {
        preparedStatement.setString( count++,
                             tplProductHistEntity_.getData().getProdName() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdText() != null )
      {
        preparedStatement.setString( count++,
                             tplProductHistEntity_.getData().getProdText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdFamlCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductHistEntity_.getData().getProdFamlCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdCcyCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductHistEntity_.getData().getProdCcyCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdIsoCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductHistEntity_.getData().getProdIsoCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdAnbidCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductHistEntity_.getData().getProdAnbidCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdCetipCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductHistEntity_.getData().getProdCetipCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdSelicCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductHistEntity_.getData().getProdSelicCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdBovespaCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductHistEntity_.getData().getProdBovespaCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdBmfCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductHistEntity_.getData().getProdBmfCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdCreateDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductHistEntity_.getData().getProdCreateDate().getTime() ) );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdStatCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductHistEntity_.getData().getProdStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdCrTypeClassCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductHistEntity_.getData().getProdCrTypeClassCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getPrvtProdAggrCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductHistEntity_.getData().getPrvtProdAggrCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdProcSysCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductHistEntity_.getData().getProdProcSysCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdProcSysSegCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductHistEntity_.getData().getProdProcSysSegCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdApprvDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductHistEntity_.getData().getProdApprvDate().getTime() ) );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdOpernStaDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductHistEntity_.getData().getProdOpernStaDate().getTime() ) );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdCstdyCnpjNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductHistEntity_.getData().getProdCstdyCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdAuditCnpjNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductHistEntity_.getData().getProdAuditCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdMgmtCnpjNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductHistEntity_.getData().getProdMgmtCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdCtlCnpjNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductHistEntity_.getData().getProdCtlCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdAdminCnpjNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductHistEntity_.getData().getProdAdminCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getCitiGrpTieReltnPlcyInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductHistEntity_.getData().getCitiGrpTieReltnPlcyInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getCitiGrpTieRstrnPlcyInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductHistEntity_.getData().getCitiGrpTieRstrnPlcyInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      //ISIN - Fase 3
      if ( tplProductHistEntity_.getData().getProdIsinCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductHistEntity_.getData().getProdIsinCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdRiskCatCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductHistEntity_.getData().getProdRiskCatCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdQlfyCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductHistEntity_.getData().getProdQlfyCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdSubFamlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductHistEntity_.getData().getProdSubFamlCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getProdLegalClassCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductHistEntity_.getData().getProdLegalClassCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplProductHistEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductHistEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplProductHistEntityVO.getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductHistEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntityVO.getProdAcctCode() != null )
      {
        preparedStatement.setString( count++, tplProductHistEntityVO.getProdAcctCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplProductHistEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, tplProductHistEntityVO.getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
	  if ( tplProductHistEntity_.getData().getAssetTypeCode() != null )
	  {
	     preparedStatement.setLong(
								 count++,
								 tplProductHistEntity_.getData().getAssetTypeCode().longValue() );
	  }
	  else
	  {
	    preparedStatement.setString( count++, null );
	  }
	  
      //Novos campos Fase2
      if ( tplProductHistEntity_.getData().getProdSentIaInd() != null )
      {
        preparedStatement.setString( count++, tplProductHistEntity_.getData().getProdSentIaInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      if ( tplProductHistEntity_.getData().getPrclasProdAssetClassCode() != null && !tplProductHistEntity_.getData().getPrclasProdAssetClassCode().trim().equals(""))
      {
    	  preparedStatement.setLong( count++, new BigInteger(tplProductHistEntity_.getData().getPrclasProdAssetClassCode()).longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductHistEntity_.getData().getPrclasProdStypCode() != null && !tplProductHistEntity_.getData().getPrclasProdStypCode().trim().equals(""))
      {
        preparedStatement.setLong( count++, new BigInteger(tplProductHistEntity_.getData().getPrclasProdStypCode()).longValue());
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      if (tplProductHistEntity_.getData().getPrclasProdTypeCode() != null && !tplProductHistEntity_.getData().getPrclasProdTypeCode().trim().equals(""))
      {
        preparedStatement.setLong( count++, new BigInteger(tplProductHistEntity_.getData().getPrclasProdTypeCode()).longValue());
      }
      else
      {
        preparedStatement.setString( count++, null );
      }    	  

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

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

    return tplProductHistEntity_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplProductHistDAO#list(java.lang.String,
   *      java.lang.String, java.lang.String, java.math.BigInteger,
   *      java.math.BigInteger, java.math.BigInteger, java.util.Date)
   */
  public DataSet list( String prodCode_, String prodFamlCode_,
                      String prodName_, BigInteger prodQlfyCode_,
                      BigInteger prodRiskCatCode_, BigInteger prodSubFamlCode_,
                      Date refDate_ )
  {

    return null;
  }

  /**
   * Encontra um registro baseado nos criterios informados
   * @see com.citibank.ods.persistence.pl.dao.BaseTplProductDAO#find(com.citibank.ods.entity.pl.BaseTplProductEntity)
   */
  public BaseTplProductEntity find( BaseTplProductEntity BaseTplProductEntity_ )
                                                                                throws UnexpectedException
  {
    return null;
  }

}