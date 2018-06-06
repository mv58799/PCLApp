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
import com.citibank.ods.entity.pl.BaseTplPlayerEntity;
import com.citibank.ods.entity.pl.BaseTplProductEntity;
import com.citibank.ods.entity.pl.TplProductEntity;
import com.citibank.ods.entity.pl.valueobject.TplProductEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProductDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * Implementação Oracle para DAO da tabela TPL_PRODUCT
 * 
 * @author leonardo.nakada
 * @date 04/04/2007
 */
public class OracleTplProductDAO extends BaseOracleTplProductDAO implements
    TplProductDAO
{

  /*
   * Nome da tabela
   */
  private static final String C_TPL_PRODUCT = C_PL_SCHEMA + "TPL_PRODUCT";

  private static final String C_TPL_PROD_FAML = C_PL_SCHEMA
                                                + "TPL_PRODUCT_FAMILY_PRVT";

  private String ALIAS_PRODUCT = "PROD";

  private String ALIAS_PROD_FAML = "PROD_FAML";

  private static final String C_TPL_PROD_SUB_FAML = C_PL_SCHEMA
                                                    + "TPL_PRODUCT_SUB_FAMILY_PRVT";

  private String ALIAS_PROD_SUB_FAML = "SUB";
  
  private static final String C_TPL_PROD_MOV = C_PL_SCHEMA
	 												+ "TPL_PRODUCT_MOV";

  private String ALIAS_PROD_MOV = "MOV";
  
  /*
   * Campos específicos da tabela
   */
  private String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private String C_REC_STAT_CODE = "REC_STAT_CODE";

  private String C_REC_STAT_TEXT = "REC_STAT_TEXT";

  private String C_PROD_SUB_FAML_NAME = "PROD_SUB_FAML_NAME";

  private String C_PROD_FAML_NAME = "PROD_FAML_NAME";

  /**
   * Este método insere um novo registro
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProductDAO#insert(com.citibank.ods.entity.pl.TplProductEntity)
   */

  public TplProductEntity insert( TplProductEntity tplProductEntity_ )
                                                                      throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_PRODUCT + " (" );
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
      query.append( C_PROD_APPRV_DATE + ", " );
      query.append( C_PROD_OPERN_STA_DATE + ", " );
      query.append( C_PROD_CSTDY_CNPJ_NBR + ", " );
      query.append( C_PROD_AUDIT_CNPJ_NBR + ", " );
      query.append( C_PROD_MGMT_CNPJ_NBR + ", " );
      query.append( C_PROD_CTL_CNPJ_NBR + ", " );
      query.append( C_PROD_ADMIN_CNPJ_NBR + ", " );
      query.append( C_CITI_GRP_TIE_RELTN_PLCY_IND + ", " );
      query.append( C_CITI_GRP_TIE_RSTRN_PLCY_IND + ", " );
      //ISIN - Fase 3
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
      query.append( C_REC_STAT_CODE + ", " );
      
      query.append( C_PROD_SENT_IND + ", " );
      query.append( C_PRCLAS_PROD_ASSET_CLASS_CODE + ", " );
      query.append( C_PRCLAS_PROD_STYP_CODE + ", " );
      query.append( " PROD_ONESRC_ASSET_CLASS_CODE, " );
      query.append( C_PRCLAS_PROD_TYPE_CODE + " ) " );
      
      query.append( " VALUES ( " );
      query.append( " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " );
      query.append( " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?, ? )" );
      
      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      TplProductEntityVO tplProductEntityVO = ( TplProductEntityVO ) tplProductEntity_.getData();

      if ( tplProductEntity_.getData().getProdCode() != null )
      {
        preparedStatement.setString( count++, tplProductEntity_.getData().getProdCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProductEntity_.getData().getSysCode() != null )
      {
        preparedStatement.setString( count++, tplProductEntity_.getData().getSysCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProductEntity_.getData().getSysSegCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductEntity_.getData().getSysSegCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProductEntity_.getData().getProdName() != null )
      {
        preparedStatement.setString( count++, tplProductEntity_.getData().getProdName() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdText() != null )
      {
        preparedStatement.setString( count++, tplProductEntity_.getData().getProdText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdFamlCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdFamlCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdCcyCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdCcyCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdIsoCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdIsoCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdAnbidCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdAnbidCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdCetipCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdCetipCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdSelicCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdSelicCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdBovespaCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdBovespaCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdBmfCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdBmfCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdCreateDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductEntity_.getData().getProdCreateDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplProductEntity_.getData().getProdStatCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getPrvtProdAggrCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getPrvtProdAggrCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdCrTypeClassCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductEntity_.getData().getProdCrTypeClassCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdProcSysCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdProcSysCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdProcSysSegCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductEntity_.getData().getProdProcSysSegCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdApprvDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductEntity_.getData().getProdApprvDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplProductEntity_.getData().getProdOpernStaDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductEntity_.getData().getProdOpernStaDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplProductEntity_.getData().getProdCstdyCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdCstdyCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdAuditCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdAuditCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdMgmtCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdMgmtCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdCtlCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdCtlCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdAdminCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdAdminCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getCitiGrpTieReltnPlcyInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductEntity_.getData().getCitiGrpTieReltnPlcyInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getCitiGrpTieRstrnPlcyInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductEntity_.getData().getCitiGrpTieRstrnPlcyInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      //ISIN - Fase 3
      if ( tplProductEntity_.getData().getProdIsinCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductEntity_.getData().getProdIsinCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      

      if ( tplProductEntity_.getData().getProdRiskCatCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductEntity_.getData().getProdRiskCatCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdQlfyCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductEntity_.getData().getProdQlfyCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdSubFamlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductEntity_.getData().getProdSubFamlCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdLegalClassCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductEntity_.getData().getProdLegalClassCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++, tplProductEntityVO.getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplProductEntityVO.getProdAcctCode() != null )
      {
        preparedStatement.setString( count++, tplProductEntityVO.getProdAcctCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, tplProductEntityVO.getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      //Novos campos Fase2
      if ( tplProductEntityVO.getProdSentIaInd() != null )
      {
        preparedStatement.setString( count++, tplProductEntityVO.getProdSentIaInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      if ( tplProductEntityVO.getPrclasProdAssetClassCode() != null && !tplProductEntityVO.getPrclasProdAssetClassCode().trim().equals(""))
      {
        preparedStatement.setLong( count++, new BigInteger(tplProductEntityVO.getPrclasProdAssetClassCode()).longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntityVO.getPrclasProdStypCode() != null && !tplProductEntityVO.getPrclasProdStypCode().trim().equals(""))
      {
        preparedStatement.setLong( count++, new BigInteger(tplProductEntityVO.getPrclasProdStypCode()).longValue());
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      //Classificacao do produto no Onesource
      if (tplProductEntityVO.getAssetClassOnesrc() != null && !tplProductEntityVO.getAssetClassOnesrc().trim().equals(""))
      {
        preparedStatement.setString( count++, tplProductEntityVO.getAssetClassOnesrc());
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      if (tplProductEntityVO.getPrclasProdTypeCode() != null && !tplProductEntityVO.getPrclasProdTypeCode().trim().equals(""))
      {
        preparedStatement.setLong( count++, new BigInteger(tplProductEntityVO.getPrclasProdTypeCode()).longValue());
      }
      else
      {
        preparedStatement.setString( count++, null );
      }      
      

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplProductEntity_;

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
   * Atualiza uma linha na tabela TPL_PRODUCT de acordo com ID contido na
   * entidade passada como parâmetro.
   * 
   * @param tplProductEntity__
   * @throws UnexpectedException
   * @author leonardo.nakada
   * @date 04/04/2007
   */
  public void update( TplProductEntity tplProductEntity_ )
                                                          throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_PRODUCT );
      query.append( " SET " );
      query.append( C_PROD_NAME + " = ?, " );
      query.append( C_PROD_TEXT + " = ?, " );
      query.append( C_PROD_CCY_CODE + " = ?, " );
      query.append( C_PROD_ISO_CODE + " = ?, " );
      query.append( C_PROD_ANBID_CODE + " = ?, " );
      query.append( C_PROD_CETIP_CODE + " = ?, " );
      query.append( C_PROD_SELIC_CODE + " = ?, " );
      query.append( C_PROD_BOVESPA_CODE + " = ?, " );
      query.append( C_PROD_BMF_CODE + " = ?, " );
      query.append( C_PROD_CREATE_DATE + " = ?, " );
      query.append( C_PROD_STAT_CODE + " = ?, " );
      query.append( C_PRVT_PROD_AGGR_CODE + " = ?, " );
      query.append( C_PROD_CR_TYPE_CLASS_CODE + " = ?, " );
      query.append( C_PROD_PROC_SYS_CODE + " = ?, " );
      query.append( C_PROD_PROC_SYS_SEG_CODE + " = ?, " );
      query.append( C_PROD_APPRV_DATE + " = ?, " );
      query.append( C_PROD_OPERN_STA_DATE + " = ?, " );
      query.append( C_PROD_CSTDY_CNPJ_NBR + " = ?, " );
      query.append( C_PROD_AUDIT_CNPJ_NBR + " = ?, " );
      query.append( C_PROD_MGMT_CNPJ_NBR + " = ?, " );
      query.append( C_PROD_CTL_CNPJ_NBR + " = ?, " );
      query.append( C_PROD_ADMIN_CNPJ_NBR + " = ?, " );
      query.append( C_CITI_GRP_TIE_RELTN_PLCY_IND + " = ?, " );
      query.append( C_CITI_GRP_TIE_RSTRN_PLCY_IND + " = ?, " );
      //ISIN - fase 3
      query.append( C_PROD_ISIN_CODE + " = ?, " );
      
      query.append( C_PROD_INVST_RISK_CODE + " = ?, " );
      query.append( C_PROD_QLFY_CODE + " = ?, " );
      query.append( C_PROD_SUB_FAML_CODE + " = ?, " );
      query.append( C_PROD_LEGAL_CLASS_CODE + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_LAST_AUTH_USER_ID + " = ?, " );
      query.append( C_LAST_AUTH_DATE + " = ?, " );
      query.append( C_REC_STAT_CODE + " = ?, " );
      query.append( C_PROD_ACCT_CODE + " = ?, " );
	  query.append( C_ASSET_TYPE_CODE+ " = ?, " );
	  
      query.append( C_PROD_SENT_IND+ " = ?, " );
      query.append( C_PRCLAS_PROD_ASSET_CLASS_CODE+ " = ?, " );
      query.append( C_PRCLAS_PROD_STYP_CODE+ " = ?, " );
      query.append( C_PRCLAS_PROD_TYPE_CODE+ " = ?, " );
      query.append( "PROD_ONESRC_ASSET_CLASS_CODE = ? ");
	  
      query.append( " WHERE " );
      query.append( C_PROD_CODE + " = ? AND " );
      query.append( C_SYS_CODE + " = ? AND " );
      query.append( C_SYS_SEG_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      TplProductEntityVO tplProductEntityVO = ( TplProductEntityVO ) tplProductEntity_.getData();

      if ( tplProductEntity_.getData().getProdName() != null )
      {
        preparedStatement.setString( count++, tplProductEntity_.getData().getProdName() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getProdText() != null )
      {
        preparedStatement.setString( count++, tplProductEntity_.getData().getProdText() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getProdCcyCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdCcyCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getProdIsoCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdIsoCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getProdAnbidCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdAnbidCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getProdCetipCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdCetipCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getProdSelicCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdSelicCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getProdBovespaCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdBovespaCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }
      if ( tplProductEntity_.getData().getProdBmfCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdBmfCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getProdCreateDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductEntity_.getData().getProdCreateDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( tplProductEntity_.getData().getProdStatCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getPrvtProdAggrCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getPrvtProdAggrCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getProdCrTypeClassCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductEntity_.getData().getProdCrTypeClassCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getProdProcSysCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdProcSysCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getProdProcSysSegCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductEntity_.getData().getProdProcSysSegCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdApprvDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductEntity_.getData().getProdApprvDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( tplProductEntity_.getData().getProdOpernStaDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductEntity_.getData().getProdOpernStaDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( tplProductEntity_.getData().getProdCstdyCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdCstdyCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getProdAuditCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdAuditCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getProdMgmtCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdMgmtCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }
      if ( tplProductEntity_.getData().getProdCtlCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdCtlCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getProdAdminCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdAdminCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getCitiGrpTieReltnPlcyInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductEntity_.getData().getCitiGrpTieReltnPlcyInd() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getCitiGrpTieRstrnPlcyInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductEntity_.getData().getCitiGrpTieRstrnPlcyInd() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }
      //ISIN - Fase 3
      if ( tplProductEntity_.getData().getProdIsinCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductEntity_.getData().getProdIsinCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }
      

      if ( tplProductEntity_.getData().getProdRiskCatCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductEntity_.getData().getProdRiskCatCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplProductEntity_.getData().getProdQlfyCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductEntity_.getData().getProdQlfyCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdSubFamlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductEntity_.getData().getProdSubFamlCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getProdLegalClassCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductEntity_.getData().getProdLegalClassCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( tplProductEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++, tplProductEntityVO.getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProductEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setNull( count++, java.sql.Types.TIMESTAMP );
      }

      if ( tplProductEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, tplProductEntityVO.getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getProdAcctCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProductEntity_.getData().getProdAcctCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

	  if( tplProductEntity_.getData().getAssetTypeCode() != null){
	    preparedStatement.setLong( count++, tplProductEntity_.getData().getAssetTypeCode().longValue() );
	  }
	  else{
	    preparedStatement.setString( count++, null );
	  }
      
      //Novos campos Fase2
      if ( tplProductEntity_.getData().getProdSentIaInd() != null )
      {
        preparedStatement.setString( count++, tplProductEntity_.getData().getProdSentIaInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      if ( tplProductEntity_.getData().getPrclasProdAssetClassCode() != null && !tplProductEntity_.getData().getPrclasProdAssetClassCode().trim().equals(""))
      {
        preparedStatement.setLong( count++, new BigInteger(tplProductEntity_.getData().getPrclasProdAssetClassCode()).longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProductEntity_.getData().getPrclasProdStypCode() != null && !tplProductEntity_.getData().getPrclasProdStypCode().trim().equals(""))
      {
        preparedStatement.setLong( count++, new BigInteger(tplProductEntity_.getData().getPrclasProdStypCode()).longValue());
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      if (tplProductEntity_.getData().getPrclasProdTypeCode() != null && !tplProductEntity_.getData().getPrclasProdTypeCode().trim().equals(""))
      {
        preparedStatement.setLong( count++, new BigInteger(tplProductEntity_.getData().getPrclasProdTypeCode()).longValue());
      }
      else
      {
        preparedStatement.setString( count++, null );
      }  
      
      //Classificacao do produto no Onesource
      if (tplProductEntityVO.getAssetClassOnesrc() != null && !tplProductEntityVO.getAssetClassOnesrc().trim().equals(""))
      {
        preparedStatement.setString( count++, tplProductEntityVO.getAssetClassOnesrc());
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      //where	  
      if ( tplProductEntity_.getData().getProdCode() != null )
      {
        preparedStatement.setString( count++, tplProductEntity_.getData().getProdCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }
      

      if ( tplProductEntity_.getData().getSysCode() != null )
      {
        preparedStatement.setString( count++, tplProductEntity_.getData().getSysCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplProductEntity_.getData().getSysSegCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductEntity_.getData().getSysSegCode().longValue() );
      }

      else
      {
        preparedStatement.setString( count++, null );
      }

	  preparedStatement.replaceParametersInQuery(query.toString());
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
   * Este metodo remove um registro baseado na chave fornecida
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProductDAO#delete(java.math.BigInteger)
   */
  public void delete( TplProductEntity tplProductEntity_ )
                                                          throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " UPDATE " + C_TPL_PRODUCT );
      query.append( " SET " );
      query.append( C_REC_STAT_CODE + "= ?" );
      query.append( " WHERE " + "TRIM(" + C_PROD_CODE + ") = ? " );
      query.append( " AND " + C_SYS_CODE + " = ? " );
      query.append( " AND " + C_SYS_SEG_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      preparedStatement.setString(
                           count++,
                           ( ( TplProductEntityVO ) tplProductEntity_.getData() ).getRecStatCode() );

      preparedStatement.setString( count++, tplProductEntity_.getData().getProdCode() );

      preparedStatement.setString( count++, tplProductEntity_.getData().getSysCode() );

      preparedStatement.setLong(
                         count++,
                         tplProductEntity_.getData().getSysSegCode().longValue() );

      preparedStatement.executeUpdate();
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
   * Realiza a consulta em lista dos dados complementares de Produto
   */
  public DataSet list( String prodCode_, BigInteger prodFamlCode_,
                      String prodName_, BigInteger prodQlfyCode_,
                      BigInteger prodRiskCatCode_, BigInteger prodSubFamlCode_,String orderBy_ )

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
      query.append( ALIAS_PRODUCT + "." + C_LAST_UPD_DATE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_LAST_AUTH_USER_ID + ", " );
      query.append( ALIAS_PRODUCT + "." + C_LAST_AUTH_DATE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_REC_STAT_CODE + " , " );
	  query.append( " DECODE(MOV.PROD_CODE, NULL, '0', '1') APROVACAO " );
	  query.append( " FROM " );
      query.append( C_TPL_PRODUCT + " " + ALIAS_PRODUCT + ", " );
      query.append( C_TPL_PROD_SUB_FAML + " " + ALIAS_PROD_SUB_FAML + ", " );
	  query.append( C_TPL_PROD_MOV + " " + ALIAS_PROD_MOV + ", " );
      query.append( C_TPL_PROD_FAML + " " + ALIAS_PROD_FAML );
	  
      String criteria = ALIAS_PRODUCT + "." + C_PROD_SUB_FAML_CODE + " = "
                        + ALIAS_PROD_SUB_FAML + "." + C_PROD_SUB_FAML_CODE
                        + " (+) AND " + ALIAS_PROD_SUB_FAML + "."
                        + C_PROD_FAML_CODE + " = " + ALIAS_PROD_FAML + "."
                        + C_PROD_FAML_CODE + "(+) AND "
		                + ALIAS_PRODUCT + "." + C_PROD_CODE + " = " 
						+ ALIAS_PROD_MOV + "." + C_PROD_CODE + "(+) AND ";
      criteria = criteria + ALIAS_PRODUCT + "." + C_REC_STAT_CODE + " != '"
                 + BaseTplPlayerEntity.C_REC_STAT_CODE_INACTIVE + "' AND ";

      if ( prodCode_ != null && !"".equals( prodCode_ ) )
      {
        criteria = criteria + "TRIM(UPPER(" + ALIAS_PRODUCT + "." + C_PROD_CODE + ")) like ? AND ";
      }

      if ( prodFamlCode_ != null && prodFamlCode_.intValue() != 0 )
      {
        criteria = criteria + ALIAS_PROD_SUB_FAML + "." + C_PROD_FAML_CODE
                   + " = ? AND ";
      }

      if ( prodName_ != null && !"".equals( prodName_ ) )
      {
        criteria = criteria + "UPPER(" + ALIAS_PRODUCT + "." + C_PROD_NAME + ") like ? AND ";
      }

      if ( prodQlfyCode_ != null )
      {
        criteria = criteria + C_PROD_QLFY_CODE + " = ? AND ";
      }

      if ( prodRiskCatCode_ != null )
      {
        criteria = criteria + ALIAS_PRODUCT + "." + C_PROD_INVST_RISK_CODE + " = ? AND ";
      }

      if ( prodSubFamlCode_ != null )
      {
        criteria = criteria + ALIAS_PRODUCT + "." + C_PROD_SUB_FAML_CODE
                   + " = ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria );
      }

      
      if(!orderBy_.equals("")){
		query.append( " ORDER BY " + orderBy_);
      }
      else {
		query.append( " ORDER BY " + ALIAS_PRODUCT + "." +C_PROD_NAME + " ASC " + " , " + ALIAS_PRODUCT + "." +C_PROD_CODE
							+ " , " + ALIAS_PRODUCT + "." +C_SYS_CODE + " , " + ALIAS_PRODUCT + "." +C_SYS_SEG_CODE );
      }
      

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( prodCode_ != null && !"".equals( prodCode_ ) )
      {
        preparedStatement.setString( count++, "%" + prodCode_.toUpperCase() + "%");
      }

      if ( prodFamlCode_ != null && !"".equals( prodFamlCode_ ) )
      {
        preparedStatement.setLong( count++, prodFamlCode_.intValue() );
      }

      if ( prodName_ != null && !"".equals( prodName_ ) )
      {
        preparedStatement.setString( count++, "%" + prodName_.toUpperCase() + "%" );
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

    String[] codeColumn = { C_REC_STAT_CODE };
    String[] nameColumn = { C_REC_STAT_TEXT };
    rsds.outerJoin( ODSConstraintDecoder.decodeRecStatus(), codeColumn,
                    codeColumn, nameColumn );

    return rsds;
  }

  /**
   * Retorna o registro de acordo com os dados informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProductDAO#find(com.citibank.ods.entity.pl.TplProductEntity)
   */

  public BaseTplProductEntity find( BaseTplProductEntity tplProductEntity_ )

  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();
    ArrayList tplProductEntities;
    TplProductEntity tplProductEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_SYS_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_SYS_SEG_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_NAME + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_TEXT + ", " );
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
      query.append( ALIAS_PRODUCT + "." + C_PROD_INVST_RISK_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_QLFY_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_FAML_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_SUB_FAML_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_LEGAL_CLASS_CODE + ", " );
      query.append( ALIAS_PROD_SUB_FAML + "." + C_PROD_SUB_FAML_NAME + ", " );
      query.append( ALIAS_PROD_FAML + "." + C_PROD_FAML_NAME + ", " );
      query.append( ALIAS_PRODUCT + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( ALIAS_PRODUCT + "." + C_LAST_UPD_DATE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_LAST_AUTH_USER_ID + ", " );
      query.append( ALIAS_PRODUCT + "." + C_LAST_AUTH_DATE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PROD_ACCT_CODE + ", " );
	  query.append( ALIAS_PRODUCT + "." + C_ASSET_TYPE_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_REC_STAT_CODE + ", " );
      //Fase 3
      query.append( ALIAS_PRODUCT + "." + C_PROD_ISIN_CODE + ", " );
      //Fase 2
      query.append( ALIAS_PRODUCT + "." + C_PROD_SENT_IND + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PRCLAS_PROD_ASSET_CLASS_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PRCLAS_PROD_STYP_CODE + ", " );
      query.append( ALIAS_PRODUCT + "." + C_PRCLAS_PROD_TYPE_CODE + ", " );
      
      //Classificacao do produto no onesource
      query.append( ALIAS_PRODUCT + ".PROD_ONESRC_ASSET_CLASS_CODE ");
      
      query.append(" FROM " );
      
      query.append( C_TPL_PRODUCT + " " + ALIAS_PRODUCT + ","
                    + C_TPL_PROD_SUB_FAML + " " + ALIAS_PROD_SUB_FAML + ","
                    + C_TPL_PROD_FAML + " " + ALIAS_PROD_FAML );

      String criteria = ALIAS_PRODUCT + "." + C_PROD_SUB_FAML_CODE + "="
                        + ALIAS_PROD_SUB_FAML + "." + C_PROD_SUB_FAML_CODE
                        + " (+) AND " + ALIAS_PROD_SUB_FAML + "."
                        + C_PROD_FAML_CODE + "=" + ALIAS_PROD_FAML + "."
                        + C_PROD_FAML_CODE + " (+) AND ";

      criteria = criteria + ALIAS_PRODUCT + "." + C_REC_STAT_CODE + " != '"
                 + BaseTplPlayerEntity.C_REC_STAT_CODE_INACTIVE + "' AND ";

      TplProductEntityVO tplProductEntityVO = ( TplProductEntityVO ) tplProductEntity_.getData();

      if ( tplProductEntityVO.getProdCode() != null
           && !tplProductEntityVO.getProdCode().equals( "" ) )
      {
        criteria = criteria + "UPPER(TRIM(" + C_PROD_CODE + ")) = ? AND ";
      }

      if ( tplProductEntityVO.getSysCode() != null
           && !tplProductEntityVO.getSysCode().equals( "" ) )
      {
        criteria = criteria + C_SYS_CODE + " = ? AND ";
      }

      if ( tplProductEntityVO.getSysSegCode() != null
           && !tplProductEntityVO.getSysSegCode().equals( "" ) )
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

      if ( tplProductEntityVO.getProdCode() != null
           && !tplProductEntityVO.getProdCode().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplProductEntityVO.getProdCode().toUpperCase().trim() );
      }

      if ( tplProductEntityVO.getSysCode() != null
           && !tplProductEntityVO.getSysCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductEntityVO.getSysCode() );
      }

      if ( tplProductEntityVO.getSysSegCode() != null
           && tplProductEntityVO.getSysSegCode().longValue() != 0 )
      {
        preparedStatement.setLong( count++,
                           tplProductEntityVO.getSysSegCode().longValue() );
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
        tplProductEntity = ( TplProductEntity ) tplProductEntities.get( 0 );
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

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.persistence.pl.dao.BaseTplProductDAO#find(com.citibank.ods.entity.pl.BaseTplProductEntity)
   */
  public TplProductEntity find( TplProductEntity baseTplProductEntity_ )
                                                                        throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList tplProductEntities;
    BaseTplProductEntity tplProductEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
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
      query.append( C_PROD_ISIN_CODE + ", " );
      query.append( C_PROD_INVST_RISK_CODE + ", " );
      query.append( C_PROD_QLFY_CODE + ", " );
      query.append( C_PROD_FAML_CODE + ", " );
      query.append( C_PROD_SUB_FAML_CODE + ", " );
      query.append( C_PROD_LEGAL_CLASS_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_PROD_ACCT_CODE + ", " );
	  query.append( C_ASSET_TYPE_CODE + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      
      query.append( C_PROD_SENT_IND + ", " );
      query.append( C_PRCLAS_PROD_ASSET_CLASS_CODE + ", " );
      query.append( C_PRCLAS_PROD_STYP_CODE + ", " );
      query.append( C_PRCLAS_PROD_TYPE_CODE + ", ");
      
      //Classificacao do produto no onesource
      query.append( " PROD_ONESRC_ASSET_CLASS_CODE ");
      
	  query.append(" FROM " );
      
      query.append( C_TPL_PRODUCT );

      String criteria = "";

      criteria = criteria + C_REC_STAT_CODE + " != '"
                 + BaseTplPlayerEntity.C_REC_STAT_CODE_INACTIVE + "' AND ";

      TplProductEntityVO tplProductEntityVO = ( TplProductEntityVO ) baseTplProductEntity_.getData();

      if ( tplProductEntityVO.getProdCode() != null
           && !tplProductEntityVO.getProdCode().equals( "" ) )
      {
        criteria = criteria + "TRIM(" + C_PROD_CODE + ") = ? AND ";
      }

      if ( tplProductEntityVO.getSysCode() != null
           && !tplProductEntityVO.getSysCode().equals( "" ) )
      {
        criteria = criteria + C_SYS_CODE + " = ? AND ";
      }

      if ( tplProductEntityVO.getSysSegCode() != null
           && !tplProductEntityVO.getSysSegCode().equals( "" ) )
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

      if ( tplProductEntityVO.getProdCode() != null
           && !tplProductEntityVO.getProdCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductEntityVO.getProdCode().trim() );
      }

      if ( tplProductEntityVO.getSysCode() != null
           && !tplProductEntityVO.getSysCode().equals( "" ) )
      {
        preparedStatement.setString( count++, tplProductEntityVO.getSysCode() );
      }

      if ( tplProductEntityVO.getSysSegCode() != null
           && tplProductEntityVO.getSysSegCode().longValue() != 0 )
      {
        preparedStatement.setLong( count++,
                           tplProductEntityVO.getSysSegCode().longValue() );
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
        tplProductEntity = ( BaseTplProductEntity ) tplProductEntities.get( 0 );
      }

      //      return tplProductEntity;
      return ( TplProductEntity ) tplProductEntity;
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
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProductDAO#exists(com.citibank.ods.entity.pl.TplProductEntity)
   */
  public boolean exists( TplProductEntity tplProductEntity_ )
                                                             throws UnexpectedException
  {

    boolean exists = true;

    try
    {
      this.find( tplProductEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  /*
   * Retorna uma coleção de entities a partir do rs
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    TplProductEntityVO tplProductEntityVO;
    TplProductEntity tplProductEntity;
    ArrayList tplProductEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplProductEntity = new TplProductEntity();

        tplProductEntity.getData().setProdCode(
                                                resultSet_.getString( C_PROD_CODE ) );
        tplProductEntity.getData().setSysCode(
                                               resultSet_.getString( C_SYS_CODE ) );
        tplProductEntity.getData().setSysSegCode(
                                                  new BigInteger(
                                                                  resultSet_.getString( C_SYS_SEG_CODE ) ) );
        tplProductEntity.getData().setProdName(
                                                resultSet_.getString( C_PROD_NAME ) );
        tplProductEntity.getData().setProdText(
                                                resultSet_.getString( C_PROD_TEXT ) );
        tplProductEntity.getData().setProdFamlCode(
                                                    resultSet_.getString( C_PROD_FAML_CODE ) );

        tplProductEntity.getData().setProdCcyCode(
                                                   resultSet_.getString( C_PROD_CCY_CODE ) );
        tplProductEntity.getData().setProdIsoCode(
                                                   resultSet_.getString( C_PROD_ISO_CODE ) );
        tplProductEntity.getData().setProdAnbidCode(
                                                     resultSet_.getString( C_PROD_ANBID_CODE ) );
        tplProductEntity.getData().setProdCetipCode(
                                                     resultSet_.getString( C_PROD_CETIP_CODE ) );
        tplProductEntity.getData().setProdSelicCode(
                                                     resultSet_.getString( C_PROD_SELIC_CODE ) );
        tplProductEntity.getData().setProdBovespaCode(
                                                       resultSet_.getString( C_PROD_BOVESPA_CODE ) );
        tplProductEntity.getData().setProdBmfCode(
                                                   resultSet_.getString( C_PROD_BMF_CODE ) );
        if ( resultSet_.getDate( C_PROD_CREATE_DATE ) != null )
        {
          tplProductEntity.getData().setProdCreateDate(
                                                        new Timestamp(
                                                                       resultSet_.getDate(
                                                                                           C_PROD_CREATE_DATE ).getTime() ) );
        }
        else
        {
          tplProductEntity.getData().setProdCreateDate( null );
        }

        tplProductEntity.getData().setProdStatCode(
                                                    resultSet_.getString( C_PROD_STAT_CODE ) );
        tplProductEntity.getData().setPrvtProdAggrCode(
                                                        resultSet_.getString( C_PRVT_PROD_AGGR_CODE ) );
        tplProductEntity.getData().setProdCrTypeClassCode(
                                                           resultSet_.getString( C_PROD_CR_TYPE_CLASS_CODE ) );
        tplProductEntity.getData().setProdProcSysCode(
                                                       resultSet_.getString( C_PROD_PROC_SYS_CODE ) );
        if ( resultSet_.getString( C_PROD_PROC_SYS_SEG_CODE ) != null )
        {
          tplProductEntity.getData().setProdProcSysSegCode(
                                                            new BigInteger(
                                                                            resultSet_.getString( C_PROD_PROC_SYS_SEG_CODE ) ) );
        }
        else
        {
          tplProductEntity.getData().setProdProcSysSegCode( null );
        }

        if ( resultSet_.getDate( C_PROD_APPRV_DATE ) != null )
        {
          tplProductEntity.getData().setProdApprvDate(
                                                       new Timestamp(
                                                                      resultSet_.getDate(
                                                                                          C_PROD_APPRV_DATE ).getTime() ) );
        }
        else
        {
          tplProductEntity.getData().setProdApprvDate( null );
        }
        if ( resultSet_.getDate( C_PROD_OPERN_STA_DATE ) != null )
        {
          tplProductEntity.getData().setProdOpernStaDate(
                                                          new Timestamp(
                                                                         resultSet_.getDate(
                                                                                             C_PROD_OPERN_STA_DATE ).getTime() ) );
        }
        else
        {
          tplProductEntity.getData().setProdOpernStaDate( null );
        }
        tplProductEntity.getData().setProdCstdyCnpjNbr(
                                                        resultSet_.getString( C_PROD_CSTDY_CNPJ_NBR ) );
        tplProductEntity.getData().setProdAuditCnpjNbr(
                                                        resultSet_.getString( C_PROD_AUDIT_CNPJ_NBR ) );
        tplProductEntity.getData().setProdMgmtCnpjNbr(
                                                       resultSet_.getString( C_PROD_MGMT_CNPJ_NBR ) );
        tplProductEntity.getData().setProdCtlCnpjNbr(
                                                      resultSet_.getString( C_PROD_CTL_CNPJ_NBR ) );
        tplProductEntity.getData().setProdAdminCnpjNbr(
                                                        resultSet_.getString( C_PROD_ADMIN_CNPJ_NBR ) );
        tplProductEntity.getData().setCitiGrpTieReltnPlcyInd(
                                                              resultSet_.getString( C_CITI_GRP_TIE_RELTN_PLCY_IND ) );
        tplProductEntity.getData().setCitiGrpTieRstrnPlcyInd(
                                                              resultSet_.getString( C_CITI_GRP_TIE_RSTRN_PLCY_IND ) );

        if ( resultSet_.getString( C_PROD_INVST_RISK_CODE ) != null )
        {
          tplProductEntity.getData().setProdRiskCatCode(
                                                         new BigInteger(
                                                                         resultSet_.getString( C_PROD_INVST_RISK_CODE ) ) );
        }
        else
        {
          tplProductEntity.getData().setProdRiskCatCode( null );
        }
        if ( resultSet_.getString( C_PROD_QLFY_CODE ) != null )
        {
          tplProductEntity.getData().setProdQlfyCode(
                                                      new BigInteger(
                                                                      resultSet_.getString( C_PROD_QLFY_CODE ) ) );
        }
        else
        {
          tplProductEntity.getData().setProdQlfyCode( null );
        }
        if ( resultSet_.getString( C_PROD_SUB_FAML_CODE ) != null )
        {
          tplProductEntity.getData().setProdSubFamlCode(
                                                         new BigInteger(
                                                                         resultSet_.getString( C_PROD_SUB_FAML_CODE ) ) );
        }
        else
        {
          tplProductEntity.getData().setProdSubFamlCode( null );
        }

        tplProductEntity.getData().setProdLegalClassCode(
                                                          resultSet_.getString( C_PROD_LEGAL_CLASS_CODE ) );

        if ( resultSet_.getDate( C_LAST_UPD_DATE ) != null )
        {
          tplProductEntity.getData().setLastUpdDate(
                                                     new Timestamp(
                                                                    resultSet_.getDate(
                                                                                        C_LAST_UPD_DATE ).getTime() ) );
        }
        else
        {
          tplProductEntity.getData().setLastUpdDate( null );
        }
        tplProductEntity.getData().setLastUpdUserId(
                                                     resultSet_.getString( C_LAST_UPD_USER_ID ) );

        // Casting para a atribuicao das colunas especificas
        tplProductEntityVO = ( TplProductEntityVO ) tplProductEntity.getData();

        if ( resultSet_.getDate( C_LAST_AUTH_DATE ) != null )
        {
          tplProductEntityVO.setLastAuthDate( new Timestamp(
                                                             resultSet_.getDate(
                                                                                 C_LAST_AUTH_DATE ).getTime() ) );
        }
        else
        {
          tplProductEntityVO.setLastAuthDate( null );
        }
        if ( resultSet_.getString( C_PROD_ACCT_CODE ) != null )
        {
          tplProductEntityVO.setProdAcctCode( resultSet_.getString( C_PROD_ACCT_CODE ) );
        }
        else
        {
          tplProductEntityVO.setProdAcctCode( null );
        }
        
		if ( resultSet_.getString( C_ASSET_TYPE_CODE ) != null )
		{
		  tplProductEntityVO.setAssetTypeCode( new BigInteger(resultSet_.getString( C_ASSET_TYPE_CODE )) );
	    }
		else
		{
		  tplProductEntityVO.setAssetTypeCode( null );
		}
        
        
        tplProductEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );
        tplProductEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );
        
        //Novos campos fase2
        if (resultSet_.getString(C_PROD_SENT_IND) != null) {
        	tplProductEntityVO.setProdSentIaInd(resultSet_.getString(C_PROD_SENT_IND));
		} else {
			tplProductEntityVO.setProdSentIaInd(null);
		}
        

        
        //Formato do valor do campo Classificacao Global = 1_2_3
        String classGlobalCode = "";
        
        if (resultSet_.getString(C_PRCLAS_PROD_ASSET_CLASS_CODE) != null) {
			
        	String valor = resultSet_.getString(C_PRCLAS_PROD_ASSET_CLASS_CODE);
        	
        	tplProductEntityVO.setPrclasProdAssetClassCode(valor);
			
			classGlobalCode = valor;
			
		} else {
			tplProductEntityVO.setPrclasProdAssetClassCode(null);
		}
        
        if (resultSet_.getString(C_PRCLAS_PROD_STYP_CODE) != null) {
        	
        	String valor = resultSet_.getString(C_PRCLAS_PROD_STYP_CODE);
        		
        	tplProductEntityVO.setPrclasProdStypCode(valor);
			
			classGlobalCode = classGlobalCode + "_" + valor;
			
		} else {
			tplProductEntityVO.setPrclasProdStypCode(null);
		}
        
        if (resultSet_.getString(C_PRCLAS_PROD_TYPE_CODE) != null) {
			
        	String valor = resultSet_.getString(C_PRCLAS_PROD_TYPE_CODE);
        	
        	tplProductEntityVO.setPrclasProdTypeCode(valor);
			
			classGlobalCode = classGlobalCode + "_" + valor;
		} else {
			tplProductEntityVO.setPrclasProdTypeCode(null);
		}   
        
        tplProductEntityVO.setAssocClassProdCode(classGlobalCode);
        
        //Novos campos fase3
        if (resultSet_.getString(C_PROD_ISIN_CODE) != null) {        	
        	tplProductEntity.getData().setProdIsinCode(resultSet_.getString(C_PROD_ISIN_CODE));        	
		} else {
			tplProductEntity.getData().setProdIsinCode(null);
		}
        
        tplProductEntity.getData().setAssetClassOnesrc(resultSet_.getString("PROD_ONESRC_ASSET_CLASS_CODE"));

        tplProductEntities.add( tplProductEntity );
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
   * Retorna se um registro existente esta ativo
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProductDAO#existsActive(com.citibank.ods.entity.pl.TplProductEntity)
   */
  public boolean existsActive( TplProductEntity TplProductEntity_ )
                                                                   throws UnexpectedException
  {
    boolean exists = true;

    try
    {
      TplProductEntity tplProductEntity = ( TplProductEntity ) this.find( TplProductEntity_ );
      TplProductEntityVO productEntityVO = ( TplProductEntityVO ) tplProductEntity.getData();
      if ( !TplProductEntity.C_REC_STAT_CODE_ACTIVE.equals( productEntityVO.getRecStatCode() ) )
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

  public DataSet loadDomain()
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
      query.append( C_PROD_CODE + ", " );
      query.append( C_PROD_NAME );
      query.append( " FROM " );
      query.append( C_TPL_PRODUCT );
      query.append( " WHERE " );
      query.append( C_REC_STAT_CODE + " <> ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++, TplProductEntity.C_REC_STAT_CODE_INACTIVE );

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

  //Combo de Produtos PMA - Utilizado na tela de Contratos - Módulo Controle.
  public DataSet loadDomainPMA()
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
      query.append( C_PROD_CODE + ", " );
      query.append( C_PROD_NAME );
      query.append( " FROM " );
      query.append( C_TPL_PRODUCT );
      query.append( " WHERE " );
      query.append( C_REC_STAT_CODE + " <> ?" );
      query.append( " AND " );
      query.append( C_PROD_CODE + " = " );
      query.append( "'" + TplProductEntity.C_PROD_CODE_PEA + "'" );
      query.append( " OR " );
      query.append( C_PROD_CODE + " = " );
      query.append( "'" + TplProductEntity.C_PROD_CODE_PEA_1 + "'" );
      query.append( " OR " );
      query.append( C_PROD_CODE + " = " );
      query.append( "'" + TplProductEntity.C_PROD_CODE_PEA_2 + "'" );
      query.append( " OR " );
      query.append( C_PROD_CODE + " = " );
      query.append( "'" + TplProductEntity.C_PROD_CODE_PEA_3 + "'" );
      query.append( " OR " );
      query.append( C_PROD_CODE + " = " );
      query.append( "'" + TplProductEntity.C_PROD_CODE_PEA_4 + "'" );
      query.append( " OR " );
      query.append( C_PROD_CODE + " = " );
      query.append( "'" + TplProductEntity.C_PROD_CODE_PEA_5 + "'" );
      query.append( " OR " );
      query.append( C_PROD_CODE + " = " );
      query.append( "'" + TplProductEntity.C_PROD_CODE_PMA + "'" );
      query.append( " OR " );
      query.append( C_PROD_CODE + " = " );
      query.append( "'" + TplProductEntity.C_PROD_CODE_PMA_1 + "'" );
      query.append( " OR " );
      query.append( C_PROD_CODE + " = " );
      query.append( "'" + TplProductEntity.C_PROD_CODE_PMA_2 + "'" );
      query.append( " OR " );
      query.append( C_PROD_CODE + " = " );
      query.append( "'" + TplProductEntity.C_PROD_CODE_PMA_3 + "'" );
      query.append( " OR " );
      query.append( C_PROD_CODE + " = " );
      query.append( "'" + TplProductEntity.C_PROD_CODE_PMA_4 + "'" );
      query.append( " OR " );
      query.append( C_PROD_CODE + " = " );
      query.append( "'" + TplProductEntity.C_PROD_CODE_PMA_5 + "'" );
      query.append( " OR " );
      query.append( C_PROD_CODE + " = " );
      query.append( "'" + TplProductEntity.C_PROD_CODE_PMA_FAKE + "'" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++, TplProductEntity.C_REC_STAT_CODE_INACTIVE );

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
   * Verifica se existe um produto ativo que utilize a foreign key passada como
   * parametro
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProductDAO#existsProductByForeignKey(java.lang.String,
   *      java.math.BigInteger, java.math.BigInteger, java.math.BigInteger)
   */
  public boolean existsProductByForeignKey( String prvtProdAggrCode_,
                                           BigInteger prodQlfyCode_,
                                           BigInteger prodRiskCatCode_,
                                           BigInteger prodSubFamlCode_,
                                           BigInteger assetTypeCode_ )
                                                                        throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT COUNT(*) FROM " );
      query.append( C_TPL_PRODUCT );
      query.append( " WHERE " );
      query.append( C_REC_STAT_CODE + " !=  ? " );

      if ( prvtProdAggrCode_ != null && !prvtProdAggrCode_.equals( "" ) )
      {
        query.append( " AND " + C_PRVT_PROD_AGGR_CODE + " = ? " );
      }

      if ( prodQlfyCode_ != null && !prodQlfyCode_.equals( "" ) )
      {
        query.append( " AND " + C_PROD_QLFY_CODE + " = ? " );
      }

      if ( prodRiskCatCode_ != null && !prodRiskCatCode_.equals( "" ) )
      {
        query.append( " AND " + C_PROD_INVST_RISK_CODE + " = ? " );
      }

      if ( prodSubFamlCode_ != null && !prodSubFamlCode_.equals( "" ) )
      {
        query.append( " AND " + C_PROD_SUB_FAML_CODE + " = ? " );
      }
      
      if(assetTypeCode_!= null){
		query.append( " AND " + C_ASSET_TYPE_CODE + " = ? " );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++, TplProductEntity.C_REC_STAT_CODE_INACTIVE );

      if ( prvtProdAggrCode_ != null && !prvtProdAggrCode_.equals( "" ) )
      {
        preparedStatement.setString( count++, prvtProdAggrCode_ );
      }

      if ( prodQlfyCode_ != null && !prodQlfyCode_.equals( "" ) )
      {
        preparedStatement.setLong( count++, prodQlfyCode_.longValue() );
      }

      if ( prodRiskCatCode_ != null && !prodRiskCatCode_.equals( "" ) )
      {
        preparedStatement.setLong( count++, prodRiskCatCode_.longValue() );
      }

      if ( prodSubFamlCode_ != null && !prodSubFamlCode_.equals( "" ) )
      {
        preparedStatement.setLong( count++, prodSubFamlCode_.longValue() );
      }
      
	  if(assetTypeCode_!= null){
		preparedStatement.setLong( count++, assetTypeCode_.longValue() ); 		  
	  }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.next() )
      {
        if ( resultSet.getInt( 1 ) != 0 )
        {
          return true;
        }
        else
        {
          return false;
        }
      }
      else
      {
        return false;
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
  }
}

