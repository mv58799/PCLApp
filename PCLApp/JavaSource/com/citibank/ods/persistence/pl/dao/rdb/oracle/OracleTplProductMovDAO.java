package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplProductEntity;
import com.citibank.ods.entity.pl.TplProductMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplProductMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProductMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;
/**
 * 
 * @author acacio.domingos,Apr 14, 2007
 *  
 */

public class OracleTplProductMovDAO extends BaseOracleTplProductDAO implements
    TplProductMovDAO
{

  /*
   * Nome da tabela
   */
  private static final String C_TPL_PRODUCT_MOV = C_PL_SCHEMA
                                                  + "TPL_PRODUCT_MOV";

  /*
   * Campos específicos da tabela
   */
  private String C_OPERN_CODE = "OPERN_CODE";

  protected String C_OPERN_TEXT = "OPERN_TEXT";

  private static final String C_TPL_PRODUCT = C_PL_SCHEMA + "TPL_PRODUCT";

  private static final String C_TPL_PROD_FAML = C_PL_SCHEMA
                                                + "TPL_PRODUCT_FAMILY_PRVT";

  private String ALIAS_PRODUCT = "PROD";

  private String ALIAS_PROD_FAML = "PROD_FAML";

  private static final String C_TPL_PROD_SUB_FAML = C_PL_SCHEMA
                                                    + "TPL_PRODUCT_SUB_FAMILY_PRVT";

  private String ALIAS_PROD_SUB_FAML = "SUB";

  private String C_PROD_SUB_FAML_NAME = "PROD_SUB_FAML_NAME";

  private String C_PROD_FAML_NAME = "PROD_FAML_NAME";

  /**
   * Este método insere um registro
   * @see com.citibank.ods.persistence.pl.dao.TplProductDAO#insert(com.citibank.ods.entity.pl.TplProductEntity)
   */
  public TplProductMovEntity insert( TplProductMovEntity tplProductMovEntity_ )

  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_PRODUCT_MOV + " ( " );
      query.append( C_PROD_CODE + ", " );
      query.append( C_SYS_CODE + ", " );
      query.append( C_SYS_SEG_CODE + ", " );
      query.append( C_PROD_NAME + ", " );
      query.append( C_PROD_TEXT + ", " );
      query.append( C_PROD_CCY_CODE + ", " );
      query.append( C_PROD_ISO_CODE + ", " );
      query.append( C_PROD_ANBID_CODE + ", " );
      query.append( C_PROD_CETIP_CODE + ", " );
      query.append( C_PROD_SELIC_CODE + ", " );
      query.append( C_PROD_BOVESPA_CODE + ", " );
      query.append( C_PROD_BMF_CODE + ", " );
      query.append( C_PROD_CREATE_DATE + ", " );
      query.append( C_PROD_STAT_CODE + ", " );
      query.append( C_PRVT_PROD_AGGR_CODE + ", " );
      query.append( C_PROD_CR_TYPE_CLASS_CODE + ", " );
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
      //ISIN - fase 3
      query.append( C_PROD_ISIN_CODE + ", " );
      
      query.append( C_PROD_INVST_RISK_CODE + ", " );
      query.append( C_PROD_QLFY_CODE + ", " );
      query.append( C_PROD_SUB_FAML_CODE + ", " );
      query.append( C_PROD_LEGAL_CLASS_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_PROD_ACCT_CODE + ", " );
	  query.append( C_ASSET_TYPE_CODE + ", " );
      query.append( C_OPERN_CODE + ", " );
      
      query.append( C_PROD_SENT_IND + ", " );
      query.append( C_PRCLAS_PROD_ASSET_CLASS_CODE + ", " );
      query.append( C_PRCLAS_PROD_STYP_CODE + ", " );
      query.append( " PROD_ONESRC_ASSET_CLASS_CODE, " );
      query.append( C_PRCLAS_PROD_TYPE_CODE + " ) " );      
      
      query.append( " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " );
      query.append( " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?, ? )" );
      
      //TODO incluir os novos campos

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      TplProductMovEntityVO tplProductMovEntityVO = ( TplProductMovEntityVO ) tplProductMovEntity_.getData();

      int count = 1;

      if ( tplProductMovEntityVO.getProdCode() != null
           && !tplProductMovEntityVO.getProdCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProductMovEntityVO.getSysCode() != null
           && !tplProductMovEntityVO.getSysCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getSysCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProductMovEntityVO.getSysSegCode() != null
           && !tplProductMovEntityVO.getSysSegCode().equals( "" ) )
      {
        preparedStatement.setLong( count++,
                           tplProductMovEntityVO.getSysSegCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProductMovEntityVO.getProdName() != null
           && !tplProductMovEntityVO.getProdName().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdName() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdText() != null
           && !tplProductMovEntityVO.getProdText().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdCcyCode() != null
           && !tplProductMovEntityVO.getProdCcyCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdCcyCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdIsoCode() != null
           && !tplProductMovEntityVO.getProdIsoCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdIsoCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdAnbidCode() != null
           && !tplProductMovEntityVO.getProdAnbidCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdAnbidCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdCetipCode() != null
           && !tplProductMovEntityVO.getProdCetipCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdCetipCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdSelicCode() != null
           && !tplProductMovEntityVO.getProdSelicCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdSelicCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdBovespaCode() != null
           && !tplProductMovEntityVO.getProdBovespaCode().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getProdBovespaCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdBmfCode() != null
           && !tplProductMovEntityVO.getProdBmfCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdBmfCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdCreateDate() != null
           && !tplProductMovEntityVO.getProdCreateDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductMovEntityVO.getProdCreateDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplProductMovEntityVO.getProdStatCode() != null
           && !tplProductMovEntityVO.getProdStatCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getPrvtProdAggrCode() != null
           && !tplProductMovEntityVO.getPrvtProdAggrCode().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getPrvtProdAggrCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdCrTypeClassCode() != null
           && !tplProductMovEntityVO.getProdCrTypeClassCode().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getProdCrTypeClassCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdProcSysCode() != null
           && !tplProductMovEntityVO.getProdProcSysCode().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getProdProcSysCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdProcSysSegCode() != null
           && !tplProductMovEntityVO.getProdProcSysSegCode().equals( "" ) )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductMovEntityVO.getProdProcSysSegCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdApprvDate() != null
           && !tplProductMovEntityVO.getProdApprvDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductMovEntityVO.getProdApprvDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplProductMovEntityVO.getProdOpernStaDate() != null
           && !tplProductMovEntityVO.getProdOpernStaDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductMovEntityVO.getProdOpernStaDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplProductMovEntityVO.getProdCstdyCnpjNbr() != null
           && !tplProductMovEntityVO.getProdCstdyCnpjNbr().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getProdCstdyCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdAuditCnpjNbr() != null
           && !tplProductMovEntityVO.getProdAuditCnpjNbr().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getProdAuditCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdMgmtCnpjNbr() != null
           && !tplProductMovEntityVO.getProdMgmtCnpjNbr().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getProdMgmtCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdCtlCnpjNbr() != null
           && !tplProductMovEntityVO.getProdCtlCnpjNbr().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdCtlCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdAdminCnpjNbr() != null
           && !tplProductMovEntityVO.getProdAdminCnpjNbr().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getProdAdminCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getCitiGrpTieReltnPlcyInd() != null
           && !tplProductMovEntityVO.getCitiGrpTieReltnPlcyInd().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getCitiGrpTieReltnPlcyInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getCitiGrpTieRstrnPlcyInd() != null
           && !tplProductMovEntityVO.getCitiGrpTieRstrnPlcyInd().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getCitiGrpTieRstrnPlcyInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      //ISIN - Fase 3
      if ( tplProductMovEntityVO.getProdIsinCode() != null
              && !tplProductMovEntityVO.getProdIsinCode().equals( "" ) )
     {
       preparedStatement.setString( count++,
                            tplProductMovEntityVO.getProdIsinCode() );
     }
     else
     {
       preparedStatement.setString( count++, null );
     }

      if ( tplProductMovEntityVO.getProdRiskCatCode() != null
           && !tplProductMovEntityVO.getProdRiskCatCode().equals( "" ) )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductMovEntityVO.getProdRiskCatCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdQlfyCode() != null
           && !tplProductMovEntityVO.getProdQlfyCode().equals( "" ) )
      {
        preparedStatement.setLong( count++,
                           tplProductMovEntityVO.getProdQlfyCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdSubFamlCode() != null
           && !tplProductMovEntityVO.getProdSubFamlCode().equals( "" ) )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductMovEntityVO.getProdSubFamlCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdLegalClassCode() != null
           && !tplProductMovEntityVO.getProdLegalClassCode().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getProdLegalClassCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getLastUpdUserId() != null
           && !tplProductMovEntityVO.getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getLastUpdDate() != null
           && !tplProductMovEntityVO.getLastUpdDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductMovEntityVO.getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplProductMovEntityVO.getProdAcctCode() != null
           && !tplProductMovEntityVO.getProdAcctCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdAcctCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      
	  if ( tplProductMovEntityVO.getAssetTypeCode() != null
	    && !tplProductMovEntityVO.getAssetTypeCode().equals( "" ) )
	  {
	     preparedStatement.setLong(
								 count++,
								 tplProductMovEntityVO.getAssetTypeCode().longValue() );
	  }else
	  {
		preparedStatement.setString( count++, null );
	  }
      
      
      
      if ( tplProductMovEntityVO.getOpernCode() != null
           && !tplProductMovEntityVO.getOpernCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getOpernCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      //Novos campos Fase2
      if ( tplProductMovEntityVO.getProdSentIaInd() != null )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdSentIaInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      if ( tplProductMovEntityVO.getPrclasProdAssetClassCode() != null && !tplProductMovEntityVO.getPrclasProdAssetClassCode().trim().equals(""))
      {
        preparedStatement.setLong( count++, new BigInteger(tplProductMovEntityVO.getPrclasProdAssetClassCode()).longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getPrclasProdStypCode() != null && !tplProductMovEntityVO.getPrclasProdStypCode().trim().equals(""))
      {
        preparedStatement.setLong( count++, new BigInteger(tplProductMovEntityVO.getPrclasProdStypCode()).longValue());
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      //Classificacao do produto no Onesource
      if (tplProductMovEntityVO.getAssetClassOnesrc() != null && !tplProductMovEntityVO.getAssetClassOnesrc().trim().equals(""))
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getAssetClassOnesrc());
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      if (tplProductMovEntityVO.getPrclasProdTypeCode() != null && !tplProductMovEntityVO.getPrclasProdTypeCode().trim().equals(""))
      {
        preparedStatement.setLong( count++, new BigInteger(tplProductMovEntityVO.getPrclasProdTypeCode()).longValue());
      }
      else
      {
        preparedStatement.setString( count++, null );
      }        

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
    return tplProductMovEntity_;
  }

  /**
   * Este método atualiza um registro com base nos criterios informados
   * @see com.citibank.ods.persistence.pl.dao.TplProductDAO#update(com.citibank.ods.entity.pl.TplProductEntity)
   */
  public TplProductMovEntity update( TplProductMovEntity tplProductMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_PRODUCT_MOV + " SET " );
      query.append( C_PROD_NAME + "= ?," );
      query.append( C_PROD_TEXT + "= ?," );
      query.append( C_PROD_CCY_CODE + "= ?," );
      query.append( C_PROD_ISO_CODE + "= ?," );
      query.append( C_PROD_ANBID_CODE + "= ?," );
      query.append( C_PROD_CETIP_CODE + "= ?," );
      query.append( C_PROD_SELIC_CODE + "= ?," );
      query.append( C_PROD_BOVESPA_CODE + "= ?," );
      query.append( C_PROD_BMF_CODE + "= ?," );
      query.append( C_PROD_CREATE_DATE + "= ?," );
      query.append( C_PROD_STAT_CODE + "= ?," );
      query.append( C_PRVT_PROD_AGGR_CODE + "= ?," );
      query.append( C_PROD_CR_TYPE_CLASS_CODE + "= ?," );
      query.append( C_PROD_PROC_SYS_CODE + "= ?," );
      query.append( C_PROD_PROC_SYS_SEG_CODE + "= ?," );
      query.append( C_PROD_APPRV_DATE + "= ?," );
      query.append( C_PROD_OPERN_STA_DATE + "= ?," );
      query.append( C_PROD_CSTDY_CNPJ_NBR + "= ?," );
      query.append( C_PROD_AUDIT_CNPJ_NBR + "= ?," );
      query.append( C_PROD_MGMT_CNPJ_NBR + "= ?," );
      query.append( C_PROD_CTL_CNPJ_NBR + "= ?," );
      query.append( C_PROD_ADMIN_CNPJ_NBR + "= ?," );
      query.append( C_CITI_GRP_TIE_RELTN_PLCY_IND + "= ?," );
      query.append( C_CITI_GRP_TIE_RSTRN_PLCY_IND + "= ?," );
      //ISIN - Fase 3
      query.append( C_PROD_ISIN_CODE + "= ?," );
      
      query.append( C_PROD_INVST_RISK_CODE + "= ?," );
      query.append( C_PROD_QLFY_CODE + "= ?," );
      query.append( C_PROD_SUB_FAML_CODE + "= ?," );
      query.append( C_PROD_LEGAL_CLASS_CODE + "= ?," );
      query.append( C_LAST_UPD_USER_ID + "= ?," );
      query.append( C_LAST_UPD_DATE + "= ?," );
      query.append( C_PROD_ACCT_CODE + " = ?," );
      query.append( C_OPERN_CODE + "= ?, " );
	  query.append( C_ASSET_TYPE_CODE +" = ?, " );
	  
      query.append( C_PROD_SENT_IND+ " = ?, " );
      query.append( C_PRCLAS_PROD_ASSET_CLASS_CODE+ " = ?, " );
      query.append( C_PRCLAS_PROD_STYP_CODE+ " = ?, " );
      query.append( C_PRCLAS_PROD_TYPE_CODE+ " = ?, " );
      query.append( "PROD_ONESRC_ASSET_CLASS_CODE = ? ");
	  
      query.append( " WHERE " + C_PROD_CODE + "= ? " );
      query.append( " AND  " + C_SYS_CODE + "= ? " );
      query.append( " AND  " + C_SYS_SEG_CODE + "= ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      TplProductMovEntityVO tplProductMovEntityVO = ( TplProductMovEntityVO ) tplProductMovEntity_.getData();

      int count = 1;

      if ( tplProductMovEntityVO.getProdName() != null
           && !tplProductMovEntityVO.getProdName().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdName() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdText() != null
           && !tplProductMovEntityVO.getProdText().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplProductMovEntityVO.getProdCcyCode() != null
           && !tplProductMovEntityVO.getProdCcyCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdCcyCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdIsoCode() != null
           && !tplProductMovEntityVO.getProdIsoCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdIsoCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdAnbidCode() != null
           && !tplProductMovEntityVO.getProdAnbidCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdAnbidCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdCetipCode() != null
           && !tplProductMovEntityVO.getProdCetipCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdCetipCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdSelicCode() != null
           && !tplProductMovEntityVO.getProdSelicCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdSelicCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdBovespaCode() != null
           && !tplProductMovEntityVO.getProdBovespaCode().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getProdBovespaCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdBmfCode() != null
           && !tplProductMovEntityVO.getProdBmfCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdBmfCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdCreateDate() != null
           && !tplProductMovEntityVO.getProdCreateDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductMovEntityVO.getProdCreateDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplProductMovEntityVO.getProdStatCode() != null
           && !tplProductMovEntityVO.getProdStatCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getPrvtProdAggrCode() != null
           && !tplProductMovEntityVO.getPrvtProdAggrCode().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getPrvtProdAggrCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdCrTypeClassCode() != null
           && !tplProductMovEntityVO.getProdCrTypeClassCode().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getProdCrTypeClassCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdProcSysCode() != null
           && !tplProductMovEntityVO.getProdProcSysCode().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getProdProcSysCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdProcSysSegCode() != null
           && !tplProductMovEntityVO.getProdProcSysSegCode().equals( "" ) )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductMovEntityVO.getProdProcSysSegCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdApprvDate() != null
           && !tplProductMovEntityVO.getProdApprvDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductMovEntityVO.getProdApprvDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplProductMovEntityVO.getProdOpernStaDate() != null
           && !tplProductMovEntityVO.getProdOpernStaDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductMovEntityVO.getProdOpernStaDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplProductMovEntityVO.getProdCstdyCnpjNbr() != null
           && !tplProductMovEntityVO.getProdCstdyCnpjNbr().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getProdCstdyCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdAuditCnpjNbr() != null
           && !tplProductMovEntityVO.getProdAuditCnpjNbr().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getProdAuditCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdMgmtCnpjNbr() != null
           && !tplProductMovEntityVO.getProdMgmtCnpjNbr().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getProdMgmtCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdCtlCnpjNbr() != null
           && !tplProductMovEntityVO.getProdCtlCnpjNbr().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdCtlCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdAdminCnpjNbr() != null
           && !tplProductMovEntityVO.getProdAdminCnpjNbr().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getProdAdminCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getCitiGrpTieReltnPlcyInd() != null
           && !tplProductMovEntityVO.getCitiGrpTieReltnPlcyInd().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getCitiGrpTieReltnPlcyInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getCitiGrpTieRstrnPlcyInd() != null
           && !tplProductMovEntityVO.getCitiGrpTieRstrnPlcyInd().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getCitiGrpTieRstrnPlcyInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      //ISIN - Fase 3 
      if ( tplProductMovEntityVO.getProdIsinCode() != null
              && !tplProductMovEntityVO.getProdIsinCode().equals( "" ) )
     {
       preparedStatement.setString( count++,
                            tplProductMovEntityVO.getProdIsinCode() );
     }
     else
     {
       preparedStatement.setString( count++, null );
     }
      
      if ( tplProductMovEntityVO.getProdRiskCatCode() != null
           && !tplProductMovEntityVO.getProdRiskCatCode().equals( "" ) )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductMovEntityVO.getProdRiskCatCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdQlfyCode() != null
           && !tplProductMovEntityVO.getProdQlfyCode().equals( "" ) )
      {
        preparedStatement.setLong( count++,
                           tplProductMovEntityVO.getProdQlfyCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdSubFamlCode() != null
           && !tplProductMovEntityVO.getProdSubFamlCode().equals( "" ) )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductMovEntityVO.getProdSubFamlCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getProdLegalClassCode() != null
           && !tplProductMovEntityVO.getProdLegalClassCode().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplProductMovEntityVO.getProdLegalClassCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getLastUpdUserId() != null
           && !tplProductMovEntityVO.getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProductMovEntityVO.getLastUpdDate() != null
           && !tplProductMovEntityVO.getLastUpdDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductMovEntityVO.getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      if ( tplProductMovEntityVO.getProdAcctCode() != null
           && !tplProductMovEntityVO.getProdAcctCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdAcctCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getOpernCode() != null
           && !tplProductMovEntityVO.getOpernCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getOpernCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      
	  if ( tplProductMovEntityVO.getAssetTypeCode() != null
	     && !tplProductMovEntityVO.getAssetTypeCode().equals( "" ) )
	  {
	 	 preparedStatement.setLong(
	         	                   count++,
								   tplProductMovEntityVO.getAssetTypeCode().longValue() );
	  }else
	  {
		 preparedStatement.setString( count++, null );
	  }
      
      //Novos campos Fase2
      if ( tplProductMovEntityVO.getProdSentIaInd() != null )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdSentIaInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      if ( tplProductMovEntityVO.getPrclasProdAssetClassCode() != null && !tplProductMovEntityVO.getPrclasProdAssetClassCode().trim().equals(""))
      {
        preparedStatement.setLong( count++, new BigInteger(tplProductMovEntityVO.getPrclasProdAssetClassCode()).longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductMovEntityVO.getPrclasProdStypCode() != null && !tplProductMovEntityVO.getPrclasProdStypCode().trim().equals(""))
      {
        preparedStatement.setLong( count++, new BigInteger(tplProductMovEntityVO.getPrclasProdStypCode()).longValue());
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      if (tplProductMovEntityVO.getPrclasProdTypeCode() != null && !tplProductMovEntityVO.getPrclasProdTypeCode().trim().equals(""))
      {
        preparedStatement.setLong( count++, new BigInteger(tplProductMovEntityVO.getPrclasProdTypeCode()).longValue());
      }
      else
      {
        preparedStatement.setString( count++, null );
      } 
      
      //Classificacao do produto no Onesource
      if (tplProductMovEntityVO.getAssetClassOnesrc() != null && !tplProductMovEntityVO.getAssetClassOnesrc().trim().equals(""))
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getAssetClassOnesrc());
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      
      //where
      if ( tplProductMovEntityVO.getProdCode() != null
           && !tplProductMovEntityVO.getProdCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getProdCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProductMovEntityVO.getSysCode() != null
           && !tplProductMovEntityVO.getSysCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getSysCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProductMovEntityVO.getSysSegCode() != null
           && !tplProductMovEntityVO.getSysSegCode().equals( "" ) )
      {
        preparedStatement.setLong( count++,
                           tplProductMovEntityVO.getSysSegCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      
      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplProductMovEntity_;

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
   * Remove um registro Logicamente com base nas chaves fornecidas
   * @see com.citibank.ods.persistence.pl.dao.TplProductDAO#delete(java.math.BigInteger)
   */
  public TplProductMovEntity delete( TplProductMovEntity tplProductMovEntity_ )
                                                                               throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "DELETE FROM " );
      query.append( C_TPL_PRODUCT_MOV );
      query.append( " WHERE " );
      query.append( C_PROD_CODE + " = ?" );
      query.append( " AND " + C_SYS_CODE + " = ?" );
      query.append( " AND " + C_SYS_SEG_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++,
                           tplProductMovEntity_.getData().getProdCode() );
      preparedStatement.setString( count++, tplProductMovEntity_.getData().getSysCode() );
      preparedStatement.setLong(
                         count++,
                         tplProductMovEntity_.getData().getSysSegCode().longValue() );

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplProductMovEntity_;
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
   * Lista os produtos de acordo com os argumentos informados
   * @see com.citibank.ods.persistence.pl.dao.TplProductDAO#list(java.lang.String,
   *      java.lang.String, java.lang.String, java.math.BigInteger,
   *      java.math.BigInteger, java.math.BigInteger)
   */
  public DataSet list( String prodCode_, BigInteger prodFamlCode_,
                      String prodName_, BigInteger prodQlfyCode_,
                      BigInteger prodRiskCatCode_, BigInteger prodSubFamlCode_,
                      String lastUpdUserId_ )
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
      query.append( ALIAS_PRODUCT + "." + C_PROD_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_SYS_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_SYS_SEG_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_NAME + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_TEXT + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_FAML_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_CCY_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_ISO_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_ANBID_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_CETIP_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_SELIC_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_BOVESPA_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_BMF_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_CREATE_DATE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_STAT_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PRVT_PROD_AGGR_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_CR_TYPE_CLASS_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_PROC_SYS_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_PROC_SYS_SEG_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_APPRV_DATE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_OPERN_STA_DATE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_CSTDY_CNPJ_NBR + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_AUDIT_CNPJ_NBR + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_MGMT_CNPJ_NBR + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_CTL_CNPJ_NBR + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_ADMIN_CNPJ_NBR + ", " );
      query.append( ALIAS_PRODUCT + "." + C_CITI_GRP_TIE_RELTN_PLCY_IND + ", " );
      query.append( ALIAS_PRODUCT + "." + C_CITI_GRP_TIE_RSTRN_PLCY_IND + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_ISIN_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_INVST_RISK_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_QLFY_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_SUB_FAML_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_LEGAL_CLASS_CODE + ", " );
      query.append( ALIAS_PROD_SUB_FAML + "." + C_PROD_SUB_FAML_NAME + ", " );
      query.append( ALIAS_PROD_FAML + "." + C_PROD_FAML_NAME + ", " );
      query.append( ALIAS_PRODUCT + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( ALIAS_PRODUCT + "." + C_OPERN_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_LAST_UPD_DATE + " FROM " );
      query.append( C_TPL_PRODUCT_MOV + " " + ALIAS_PRODUCT + ","
                    + C_TPL_PROD_SUB_FAML + " " + ALIAS_PROD_SUB_FAML + ","
                    + C_TPL_PROD_FAML + " " + ALIAS_PROD_FAML );

      String criteria = ALIAS_PRODUCT + "." + C_PROD_SUB_FAML_CODE + "="
                        + ALIAS_PROD_SUB_FAML + "." + C_PROD_SUB_FAML_CODE
                        + " (+) AND " + ALIAS_PROD_SUB_FAML + "."
                        + C_PROD_FAML_CODE + "=" + ALIAS_PROD_FAML + "."
                        + C_PROD_FAML_CODE + " (+) AND ";

      if ( prodCode_ != null && !prodCode_.equals( "" ) )
      {
        criteria = criteria + "UPPER(TRIM(" + C_PROD_CODE + ")) = ? AND ";
      }

      if ( prodFamlCode_ != null && !prodFamlCode_.equals( "" ) )
      {
        criteria = criteria + ALIAS_PROD_FAML + "." + C_PROD_FAML_CODE
                   + " = ? AND ";
      }

      if ( prodQlfyCode_ != null && !prodQlfyCode_.equals( "" ) )
      {
        criteria = criteria + "UPPER(TRIM(" + C_PROD_QLFY_CODE + ")) = ? AND ";
      }

      if ( prodRiskCatCode_ != null && !prodRiskCatCode_.equals( "" ) )
      {
        criteria = criteria + "UPPER(TRIM(" + C_PROD_INVST_RISK_CODE
                   + ")) = ? AND ";
      }

      if ( prodSubFamlCode_ != null && !prodSubFamlCode_.equals( "" ) )
      {
        criteria = criteria + "UPPER(TRIM(" + ALIAS_PRODUCT + "."
                   + C_PROD_SUB_FAML_CODE + ")) = ? AND ";
      }

      if ( lastUpdUserId_ != null && !lastUpdUserId_.equals( "" ) )
      {
        criteria = criteria + "UPPER(TRIM(" + ALIAS_PRODUCT + "."
                   + C_LAST_UPD_USER_ID + ")) LIKE ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;
      if ( prodCode_ != null && !prodCode_.equals( "" ) )
      {
        preparedStatement.setString( count++, prodCode_ );
      }

      if ( prodFamlCode_ != null && !prodFamlCode_.equals( "" ) )
      {
        preparedStatement.setLong( count++, prodFamlCode_.intValue() );
      }

      if ( prodQlfyCode_ != null )
      {
        preparedStatement.setLong( count++, prodQlfyCode_.longValue() );
      }

      if ( prodRiskCatCode_ != null )
      {
        preparedStatement.setLong( count++, prodRiskCatCode_.longValue() );
      }

      if ( prodSubFamlCode_ != null )
      {
        preparedStatement.setLong( count++, prodSubFamlCode_.longValue() );
      }

      if ( lastUpdUserId_ != null && !lastUpdUserId_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + lastUpdUserId_.toUpperCase() + "%" );
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

    String[] codeColumn = { C_OPERN_CODE };
    String[] nameColumn = { C_OPERN_TEXT };
    rsds.outerJoin( ODSConstraintDecoder.decodeOpern(), codeColumn, codeColumn,
                    nameColumn );

    return rsds;
  }

  /**
   * Este metodo busca um produto com base nos criterios informados
   * @see com.citibank.ods.persistence.pl.dao.BaseTplProductDAO#find(com.citibank.ods.entity.pl.BaseTplProductEntity)
   */

  public BaseTplProductEntity find( BaseTplProductEntity tplProductEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();
    ArrayList tplProductEntities;
    TplProductMovEntity tplProductEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_SYS_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_SYS_SEG_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_NAME + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_TEXT + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_FAML_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_CCY_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_ISO_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_ANBID_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_CETIP_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_SELIC_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_BOVESPA_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_BMF_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_CREATE_DATE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_STAT_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PRVT_PROD_AGGR_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_CR_TYPE_CLASS_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_PROC_SYS_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_PROC_SYS_SEG_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_APPRV_DATE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_OPERN_STA_DATE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_CSTDY_CNPJ_NBR + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_AUDIT_CNPJ_NBR + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_MGMT_CNPJ_NBR + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_CTL_CNPJ_NBR + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_ADMIN_CNPJ_NBR + ", " );
      query.append( ALIAS_PRODUCT + "." + C_CITI_GRP_TIE_RELTN_PLCY_IND + ", " );
      query.append( ALIAS_PRODUCT + "." + C_CITI_GRP_TIE_RSTRN_PLCY_IND + ", " );
      //ISIN - Fase 3
      query.append( ALIAS_PRODUCT + "." + C_PROD_ISIN_CODE + ", " );
      
      query.append( ALIAS_PRODUCT + "." + C_PROD_INVST_RISK_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_QLFY_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_SUB_FAML_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_LEGAL_CLASS_CODE + ", " );
      query.append( ALIAS_PROD_SUB_FAML + "." + C_PROD_SUB_FAML_NAME + ", " );
      query.append( ALIAS_PROD_FAML + "." + C_PROD_FAML_NAME + ", " );
      query.append( ALIAS_PRODUCT + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( ALIAS_PRODUCT + "." + C_OPERN_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_ACCT_CODE + ", " );
	  query.append( "PROD.ASSET_TYPE_CODE , " );
      query.append( ALIAS_PRODUCT + "." + C_LAST_UPD_DATE + ", "  );
      
      //Fase 3
      query.append( ALIAS_PRODUCT + "." + C_PROD_ISIN_CODE + ", " );
      
      query.append( ALIAS_PRODUCT + "." + C_PROD_SENT_IND + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PRCLAS_PROD_ASSET_CLASS_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PRCLAS_PROD_STYP_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PRCLAS_PROD_TYPE_CODE + ", ");   
      
      //Classificacao do produto no onesource
      query.append( ALIAS_PRODUCT + ".PROD_ONESRC_ASSET_CLASS_CODE ");
      
      query.append(" FROM " );
      
      query.append( C_TPL_PRODUCT_MOV + " " + ALIAS_PRODUCT + ","
                    + C_TPL_PROD_SUB_FAML + " " + ALIAS_PROD_SUB_FAML + ","
                    + C_TPL_PROD_FAML + " " + ALIAS_PROD_FAML );

      String criteria = ALIAS_PRODUCT + "." + C_PROD_SUB_FAML_CODE + "="
                        + ALIAS_PROD_SUB_FAML + "." + C_PROD_SUB_FAML_CODE
                        + " (+) AND " + ALIAS_PROD_SUB_FAML + "."
                        + C_PROD_FAML_CODE + "=" + ALIAS_PROD_FAML + "."
                        + C_PROD_FAML_CODE + " (+) AND ";

      TplProductMovEntityVO tplProductMovEntityVO = ( TplProductMovEntityVO ) tplProductEntity_.getData();

      if ( tplProductMovEntityVO.getProdCode() != null
           && !tplProductMovEntityVO.getProdCode().equals( "" ) )
      {
        criteria = criteria + "UPPER(TRIM(" + C_PROD_CODE + ")) = ? AND ";
      }

      if ( tplProductMovEntityVO.getSysCode() != null
           && !tplProductMovEntityVO.getSysCode().equals( "" ) )
      {
        criteria = criteria + C_SYS_CODE + " = ? AND ";
      }

      if ( tplProductMovEntityVO.getSysSegCode() != null
           && !tplProductMovEntityVO.getSysSegCode().equals( "" ) )
      {
        criteria = criteria + C_SYS_SEG_CODE + " = ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplProductMovEntityVO.getProdCode() != null
           && !tplProductMovEntityVO.getProdCode().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplProductMovEntityVO.getProdCode().toUpperCase().trim() );
      }

      if ( tplProductMovEntityVO.getSysCode() != null
           && !tplProductMovEntityVO.getSysCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductMovEntityVO.getSysCode() );
      }

      if ( tplProductMovEntityVO.getSysSegCode() != null
           && tplProductMovEntityVO.getSysSegCode().longValue() != 0 )
      {
        preparedStatement.setLong( count++,
                           tplProductMovEntityVO.getSysSegCode().longValue() );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      tplProductEntities = instantiateFromResultSet( resultSet );

      if ( tplProductEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplProductEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplProductEntity = ( TplProductMovEntity ) tplProductEntities.get( 0 );
      }

      return tplProductEntity;
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

  //  public BaseTplProductEntity find( BaseTplProductEntity tplProductMovEntity_
  // )
  //                                                                               throws UnexpectedException
  //
  //  {
  //    ManagedRdbConnection connection = null;
  //    CitiStatement preparedStatement = null;
  //    ResultSet resultSet = null;
  //    StringBuffer query = new StringBuffer();
  //    ArrayList tplProductMovEntities;
  //    BaseTplProductEntity tplProductEntity = null;
  //
  //    try
  //    {
  //      connection = OracleODSDAOFactory.getConnection();
  //      query.append( "SELECT " );
  //      query.append( C_PROD_CODE + ", " );
  //      query.append( C_SYS_CODE + ", " );
  //      query.append( C_SYS_SEG_CODE + ", " );
  //      query.append( C_PROD_NAME + ", " );
  //      query.append( C_PROD_TEXT + ", " );
  //      query.append( C_PROD_FAML_CODE + ", " );
  //      query.append( C_PROD_CCY_CODE + ", " );
  //      query.append( C_PROD_ISO_CODE + ", " );
  //      query.append( C_PROD_ANBID_CODE + ", " );
  //      query.append( C_PROD_CETIP_CODE + ", " );
  //      query.append( C_PROD_SELIC_CODE + ", " );
  //      query.append( C_PROD_BOVESPA_CODE + ", " );
  //      query.append( C_PROD_BMF_CODE + ", " );
  //      query.append( C_PROD_CREATE_DATE + ", " );
  //      query.append( C_PROD_STAT_CODE + ", " );
  //      query.append( C_PRVT_PROD_AGGR_CODE + ", " );
  //      query.append( C_PROD_CR_TYPE_CLASS_CODE + ", " );
  //      query.append( C_PROD_PROC_SYS_CODE + ", " );
  //      query.append( C_PROD_PROC_SYS_SEG_CODE + ", " );
  //      query.append( C_PROD_APPRV_DATE + ", " );
  //      query.append( C_PROD_OPERN_STA_DATE + ", " );
  //      query.append( C_PROD_CSTDY_CNPJ_NBR + ", " );
  //      query.append( C_PROD_AUDIT_CNPJ_NBR + ", " );
  //      query.append( C_PROD_MGMT_CNPJ_NBR + ", " );
  //      query.append( C_PROD_CTL_CNPJ_NBR + ", " );
  //      query.append( C_PROD_ADMIN_CNPJ_NBR + ", " );
  //      query.append( C_CITI_GRP_TIE_RELTN_PLCY_IND + ", " );
  //      query.append( C_CITI_GRP_TIE_RSTRN_PLCY_IND + ", " );
  //      query.append( C_PROD_INVST_RISK_CODE + ", " );
  //      query.append( C_PROD_QLFY_CODE + ", " );
  //      query.append( C_PROD_SUB_FAML_CODE + ", " );
  //      query.append( C_PROD_LEGAL_CLASS_CODE + ", " );
  //      query.append( C_LAST_UPD_USER_ID + ", " );
  //      query.append( C_LAST_UPD_DATE + ", " );
  //      query.append( C_OPERN_CODE );
  //      query.append( " FROM " + C_TPL_PRODUCT_MOV );
  //      query.append( " WHERE " );
  //      query.append( C_PROD_CODE + " = ?" + " AND " );
  //      query.append( C_SYS_CODE + " = ?" + " AND " );
  //      query.append( C_SYS_SEG_CODE + " = ?" );
  //
  //      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
  //      int count = 1;
  //
  //      preparedStatement.setString( count++,
  //                           tplProductMovEntity_.getData().getProdCode() );
  //
  //      preparedStatement.setString( count++, tplProductMovEntity_.getData().getSysCode()
  // );
  //
  //      preparedStatement.setLong(
  //                         count++,
  //                         tplProductMovEntity_.getData().getSysSegCode().longValue() );
  //
  //      resultSet = preparedStatement.executeQuery();
  
  //
  //      tplProductMovEntities = instantiateFromResultSet( resultSet );
  //
  //      if ( tplProductMovEntities.size() == 0 )
  //      {
  //        throw new NoRowsReturnedException();
  //      }
  //      else if ( tplProductMovEntities.size() > 1 )
  //      {
  //        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
  //      }
  //      else
  //      {
  //        tplProductEntity = ( BaseTplProductEntity ) tplProductMovEntities.get( 0 );
  //      }
  //
  //      return tplProductEntity;
  //    }
  //    catch ( SQLException e )
  //    {
  //      throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, e );
  //    }
  //  }

  /*
   * Retorna uma coleção de entities a partir do rs
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    TplProductMovEntity tplProductMovEntity;
    TplProductMovEntityVO tplProductMovEntityVO;
    ArrayList tplProductEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplProductMovEntity = new TplProductMovEntity();

        tplProductMovEntityVO = ( TplProductMovEntityVO ) tplProductMovEntity.getData();

        tplProductMovEntity.getData().setProdCode(
                                                   resultSet_.getString( C_PROD_CODE ) );
        tplProductMovEntity.getData().setSysCode(
                                                  resultSet_.getString( C_SYS_CODE ) );
        if ( resultSet_.getString( C_SYS_SEG_CODE ) != null
             && !resultSet_.getString( C_SYS_SEG_CODE ).equals( "" ) )
        {
          tplProductMovEntity.getData().setSysSegCode(
                                                       new BigInteger(
                                                                       resultSet_.getString( C_SYS_SEG_CODE ) ) );
        }
        else
        {
          tplProductMovEntity.getData().setSysSegCode( null );
        }
        tplProductMovEntity.getData().setProdName(
                                                   resultSet_.getString( C_PROD_NAME ) );
        tplProductMovEntity.getData().setProdText(
                                                   resultSet_.getString( C_PROD_TEXT ) );
        tplProductMovEntity.getData().setProdFamlCode(
                                                       resultSet_.getString( C_PROD_FAML_CODE ) );
        tplProductMovEntity.getData().setProdCcyCode(
                                                      resultSet_.getString( C_PROD_CCY_CODE ) );
        tplProductMovEntity.getData().setProdIsoCode(
                                                      resultSet_.getString( C_PROD_ISO_CODE ) );
        tplProductMovEntity.getData().setProdAnbidCode(
                                                        resultSet_.getString( C_PROD_ANBID_CODE ) );
        tplProductMovEntity.getData().setProdCetipCode(
                                                        resultSet_.getString( C_PROD_CETIP_CODE ) );
        tplProductMovEntity.getData().setProdSelicCode(
                                                        resultSet_.getString( C_PROD_SELIC_CODE ) );
        tplProductMovEntity.getData().setProdBovespaCode(
                                                          resultSet_.getString( C_PROD_BOVESPA_CODE ) );
        tplProductMovEntity.getData().setProdBmfCode(
                                                      resultSet_.getString( C_PROD_BMF_CODE ) );
        tplProductMovEntity.getData().setProdCreateDate(
                                                         resultSet_.getDate( C_PROD_CREATE_DATE ) );
        tplProductMovEntity.getData().setProdStatCode(
                                                       resultSet_.getString( C_PROD_STAT_CODE ) );
        tplProductMovEntity.getData().setPrvtProdAggrCode(
                                                           resultSet_.getString( C_PRVT_PROD_AGGR_CODE ) );
        tplProductMovEntity.getData().setProdCrTypeClassCode(
                                                              resultSet_.getString( C_PROD_CR_TYPE_CLASS_CODE ) );
        tplProductMovEntity.getData().setProdProcSysCode(
                                                          resultSet_.getString( C_PROD_PROC_SYS_CODE ) );

        if ( resultSet_.getString( C_PROD_PROC_SYS_SEG_CODE ) != null
             && !resultSet_.getString( C_PROD_PROC_SYS_SEG_CODE ).equals( "" ) )
        {
          tplProductMovEntity.getData().setProdProcSysSegCode(
                                                               new BigInteger(
                                                                               resultSet_.getString( C_PROD_PROC_SYS_SEG_CODE ) ) );
        }
        else
        {
          tplProductMovEntity.getData().setProdProcSysSegCode( null );
        }
        tplProductMovEntity.getData().setProdApprvDate(
                                                        resultSet_.getTimestamp( C_PROD_APPRV_DATE ) );
        tplProductMovEntity.getData().setProdOpernStaDate(
                                                           resultSet_.getTimestamp( C_PROD_OPERN_STA_DATE ) );
        tplProductMovEntity.getData().setProdCstdyCnpjNbr(
                                                           resultSet_.getString( C_PROD_CSTDY_CNPJ_NBR ) );
        tplProductMovEntity.getData().setProdAuditCnpjNbr(
                                                           resultSet_.getString( C_PROD_AUDIT_CNPJ_NBR ) );
        tplProductMovEntity.getData().setProdMgmtCnpjNbr(
                                                          resultSet_.getString( C_PROD_MGMT_CNPJ_NBR ) );
        tplProductMovEntity.getData().setProdCtlCnpjNbr(
                                                         resultSet_.getString( C_PROD_CTL_CNPJ_NBR ) );
        tplProductMovEntity.getData().setProdAdminCnpjNbr(
                                                           resultSet_.getString( C_PROD_ADMIN_CNPJ_NBR ) );
        tplProductMovEntity.getData().setCitiGrpTieReltnPlcyInd(
                                                                 resultSet_.getString( C_CITI_GRP_TIE_RELTN_PLCY_IND ) );
        tplProductMovEntity.getData().setCitiGrpTieRstrnPlcyInd(
                                                                 resultSet_.getString( C_CITI_GRP_TIE_RSTRN_PLCY_IND ) );

        if ( resultSet_.getString( C_PROD_INVST_RISK_CODE ) != null
             && !resultSet_.getString( C_PROD_INVST_RISK_CODE ).equals( "" ) )
        {
          tplProductMovEntity.getData().setProdRiskCatCode(
                                                            new BigInteger(
                                                                            resultSet_.getString( C_PROD_INVST_RISK_CODE ) ) );
        }
        else
        {
          tplProductMovEntity.getData().setProdRiskCatCode( null );
        }

        if ( resultSet_.getString( C_PROD_QLFY_CODE ) != null
             && !resultSet_.getString( C_PROD_QLFY_CODE ).equals( "" ) )
        {
          tplProductMovEntity.getData().setProdQlfyCode(
                                                         new BigInteger(
                                                                         resultSet_.getString( C_PROD_QLFY_CODE ) ) );
        }
        else
        {
          tplProductMovEntity.getData().setProdQlfyCode( null );
        }

        if ( resultSet_.getString( C_PROD_SUB_FAML_CODE ) != null
             && !resultSet_.getString( C_PROD_SUB_FAML_CODE ).equals( "" ) )
        {
          tplProductMovEntity.getData().setProdSubFamlCode(
                                                            new BigInteger(
                                                                            resultSet_.getString( C_PROD_SUB_FAML_CODE ) ) );
        }
        else
        {
          tplProductMovEntity.getData().setProdSubFamlCode( null );
        }

        tplProductMovEntity.getData().setProdLegalClassCode(
                                                             resultSet_.getString( C_PROD_LEGAL_CLASS_CODE ) );

        tplProductMovEntity.getData().setLastUpdUserId(
                                                        resultSet_.getString( C_LAST_UPD_USER_ID ) );
        tplProductMovEntity.getData().setLastUpdDate(
                                                      resultSet_.getTimestamp( C_LAST_UPD_DATE ) );
        if ( resultSet_.getString( C_PROD_ACCT_CODE ) != null )
        {
          tplProductMovEntity.getData().setProdAcctCode(
                                                         resultSet_.getString( C_PROD_ACCT_CODE ) );
        }
        else
        {
          tplProductMovEntity.getData().setProdAcctCode( null );

        }
        
        if(resultSet_.getString("ASSET_TYPE_CODE") != null){
			tplProductMovEntity.getData().setAssetTypeCode(new BigInteger(resultSet_.getString("ASSET_TYPE_CODE")));        	
        }
        else{
			tplProductMovEntity.getData().setAssetTypeCode(null);
        }
        
        tplProductMovEntityVO.setOpernCode( resultSet_.getString( C_OPERN_CODE ) );
        
        //Novos campos fase2
        if (resultSet_.getString(C_PROD_SENT_IND) != null) {
			tplProductMovEntity.getData().setProdSentIaInd(resultSet_.getString(C_PROD_SENT_IND));
		} else {
			tplProductMovEntity.getData().setProdSentIaInd(null);
		}
        
        //Formato do valor do campo Classificacao Global = 1_2_3
        String classGlobalCode = "";
        
        if (resultSet_.getString(C_PRCLAS_PROD_ASSET_CLASS_CODE) != null) {
			
        	String valor = resultSet_.getString(C_PRCLAS_PROD_ASSET_CLASS_CODE);
        	
        	tplProductMovEntity.getData().setPrclasProdAssetClassCode(valor);
			
			classGlobalCode = valor;
			
		} else {
			tplProductMovEntity.getData().setPrclasProdAssetClassCode(null);
		}
        
        if (resultSet_.getString(C_PRCLAS_PROD_STYP_CODE) != null) {
        	
        	String valor = resultSet_.getString(C_PRCLAS_PROD_STYP_CODE);
        		
			tplProductMovEntity.getData().setPrclasProdStypCode(valor);
			
			classGlobalCode = classGlobalCode + "_" + valor;
			
		} else {
			tplProductMovEntity.getData().setPrclasProdStypCode(null);
		}
        
        if (resultSet_.getString(C_PRCLAS_PROD_TYPE_CODE) != null) {
			
        	String valor = resultSet_.getString(C_PRCLAS_PROD_TYPE_CODE);
        	
        	tplProductMovEntity.getData().setPrclasProdTypeCode(valor);
			
			classGlobalCode = classGlobalCode + "_" + valor;
		} else {
			tplProductMovEntity.getData().setPrclasProdTypeCode(null);
		} 
        
        tplProductMovEntity.getData().setAssocClassProdCode(classGlobalCode);
        
        //Novos campos fase 3
        if (resultSet_.getString(C_PROD_ISIN_CODE) != null) {
			tplProductMovEntity.getData().setProdIsinCode(resultSet_.getString(C_PROD_ISIN_CODE));
		} else {
			tplProductMovEntity.getData().setProdIsinCode(null);
		}
        
        tplProductMovEntity.getData().setAssetClassOnesrc(resultSet_.getString("PROD_ONESRC_ASSET_CLASS_CODE"));
        
        
        tplProductEntities.add( tplProductMovEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return tplProductEntities;
  }

  /**
   * Verifica se existe um registro com o código passado
   */
  public boolean exists( TplProductMovEntity TplProductMovEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( TplProductMovEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

}

