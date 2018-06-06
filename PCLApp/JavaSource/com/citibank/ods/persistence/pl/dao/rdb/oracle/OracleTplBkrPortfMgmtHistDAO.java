package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.sql.PreparedStatement;
import java.sql.Timestamp;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplBkrPortfMgmtEntity;
import com.citibank.ods.entity.pl.TplBkrPortfMgmtHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplBkrPortfMgmtHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author Hamilton Matos
 */

public class OracleTplBkrPortfMgmtHistDAO extends BaseOracleTplBkrPortfMgmtDAO
    implements TplBkrPortfMgmtHistDAO
{

  private static final String C_TABLE_BKR_PORTF_MGMT_HIST = C_PL_SCHEMA
                                                            + "TPL_BKR_PORTF_MGMT_HIST";

  private static final String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private static final String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private static final String C_BKR_PORTF_MGMT_REF_DATE = "BKR_PORTF_MGMT_REF_DATE";

  private String C_REC_STAT_CODE = "REC_STAT_CODE";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtDAO#insert(com.citibank.ods.entity.pl.TplBkrPortfMgmtEntity)
   */
  public TplBkrPortfMgmtHistEntity insert(
                                          TplBkrPortfMgmtHistEntity tplBkrPorftMgmtHistEntity_ )
                                                                                                throws UnexpectedException
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TABLE_BKR_PORTF_MGMT_HIST + " (" );
      query.append( C_BKR_CNPJ_NBR + ", " );
      query.append( C_PROD_ACCT_CODE + ", " );
      query.append( C_PROD_UNDER_ACCT_CODE + ", " );
      query.append( C_BKR_PORTF_MGMT_REF_DATE + ", " );
      query.append( C_BOVESPA_CUR_ACCT_NBR + ", " );
      query.append( C_BOVESPA_INVST_ACCT_NBR + ", " );
      query.append( C_BMF_CUR_ACCT_NBR + ", " );
      query.append( C_BMF_INVST_ACCT_NBR + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " ) VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplBkrPorftMgmtHistEntity_.getData().getBkrCnpjNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPorftMgmtHistEntity_.getData().getBkrCnpjNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      if ( tplBkrPorftMgmtHistEntity_.getData().getProdAcctCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplBkrPorftMgmtHistEntity_.getData().getProdAcctCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      if ( tplBkrPorftMgmtHistEntity_.getData().getProdUnderAcctCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplBkrPorftMgmtHistEntity_.getData().getProdUnderAcctCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( ( ( TplBkrPortfMgmtHistEntityVO ) tplBkrPorftMgmtHistEntity_.getData() ).getBkrPortfMgmtRefDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplBkrPortfMgmtHistEntityVO ) tplBkrPorftMgmtHistEntity_.getData() ).getBkrPortfMgmtRefDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPorftMgmtHistEntity_.getData().getBovespaCurAcctNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPorftMgmtHistEntity_.getData().getBovespaCurAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBkrPorftMgmtHistEntity_.getData().getBovespaInvstAcctNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPorftMgmtHistEntity_.getData().getBovespaInvstAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplBkrPorftMgmtHistEntity_.getData().getBmfCurAcctNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPorftMgmtHistEntity_.getData().getBmfCurAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplBkrPorftMgmtHistEntity_.getData().getBmfInvstAcctNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPorftMgmtHistEntity_.getData().getBmfInvstAcctNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplBkrPorftMgmtHistEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBkrPorftMgmtHistEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPorftMgmtHistEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplBkrPorftMgmtHistEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      TplBkrPortfMgmtHistEntityVO tplBkrPortfMgmtEntityVO = ( TplBkrPortfMgmtHistEntityVO ) tplBkrPorftMgmtHistEntity_.getData();

      if ( tplBkrPortfMgmtEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplBkrPortfMgmtEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPortfMgmtEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplBkrPortfMgmtEntityVO.getLastAuthUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplBkrPortfMgmtEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, tplBkrPortfMgmtEntityVO.getRecStatCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement.executeUpdate();
      
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplBkrPorftMgmtHistEntity_;

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

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTplBkrPortfMgmtDAO#find(com.citibank.ods.entity.pl.BaseTplBkrPortfMgmtEntity)
   */
  public BaseTplBkrPortfMgmtEntity find(
                                        BaseTplBkrPortfMgmtEntity baseTplBkrPortfMgmtEntity_ )
                                                                                              throws UnexpectedException
  {
    return null;
  }

}