package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplPlayerEntity;
import com.citibank.ods.entity.pl.TplPlayerHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplPlayerHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplPlayerHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author acacio.domingos,05/04/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class OracleTplPlayerHistDAO extends BaseOracleTplPlayerDAO implements
    TplPlayerHistDAO
{

  /*
   * Constante que identifica o nome da tabela de histórico
   */
  private static final String C_TPL_PLAYER_HIST = C_PL_SCHEMA + "TPL_PLAYER_HIST";

  /*
   * Campos específicos da tabela
   */
  private String C_PLYR_REF_DATE = "PLYR_REF_DATE";

  private String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private String C_REC_STAT_CODE = "REC_STAT_CODE";

  /**
   * Realiza a consulta de registros de acordo com um filtro pré-determinado.
   * @see com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtHistDAO#list(java.math.BigInteger,
   *      java.lang.String, java.util.Date)
   */
  public DataSet list( String plyrCnpjNbr_, String plyrName_, Date plyrRefDate_ )
  {
    return null;
  }

  /**
   * Insere um registro de historico do player
   * @see com.citibank.ods.persistence.pl.dao.TplPlayerHistDAO#insert(com.citibank.ods.entity.pl.TplPlayerHistEntity)
   */
  public TplPlayerHistEntity insert( TplPlayerHistEntity tplPlayerHistEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();
    
    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_PLAYER_HIST + " (" );
      query.append( C_PLYR_CNPJ_NBR + ", " );
      query.append( C_PLYR_REF_DATE + ", " );
      query.append( C_PLYR_NAME + ", " );
      query.append( C_PLYR_ADDR_TEXT + ", " );
      query.append( C_PLYR_DUE_DLG_DATE + ", " );
      query.append( C_PLYR_DUE_DLG_EXEC_IND + ", " );
      query.append( C_PLYR_DUE_DLG_END_DATE + ", " );
      query.append( C_PLYR_DUE_DLG_RNW_DATE + ", " );
      query.append( C_PLYR_INVST_CMTTE_APPRV_DATE + ", " );
      query.append( C_PLYR_APPRV_RSTRN_TEXT + ", " );
      query.append( C_PLYR_SUPL_SERV_TEXT + ", " );
      query.append( C_PLYR_CMNT_TEXT + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplPlayerHistEntity_.getData().getPlyrCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerHistEntity_.getData().getPlyrCnpjNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( ( ( TplPlayerHistEntityVO ) tplPlayerHistEntity_.getData() ).getPlyrRefDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplPlayerHistEntityVO ) tplPlayerHistEntity_.getData() ).getPlyrRefDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerHistEntity_.getData().getPlyrName() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerHistEntity_.getData().getPlyrName() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      if ( tplPlayerHistEntity_.getData().getPlyrAddrText() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerHistEntity_.getData().getPlyrAddrText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      if ( tplPlayerHistEntity_.getData().getPlyrDueDlgDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerHistEntity_.getData().getPlyrDueDlgDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }
      
      if ( tplPlayerHistEntity_.getData().getPlyrDueDlgExecInd() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplPlayerHistEntity_.getData().getPlyrDueDlgExecInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      if ( tplPlayerHistEntity_.getData().getPlyrDueDlgEndDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerHistEntity_.getData().getPlyrDueDlgEndDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }
      
      if ( tplPlayerHistEntity_.getData().getPlyrDueDlgRnwDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerHistEntity_.getData().getPlyrDueDlgRnwDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }
      
      if ( tplPlayerHistEntity_.getData().getPlyrInvstCmtteApprvDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerHistEntity_.getData().getPlyrInvstCmtteApprvDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }
      
      if ( tplPlayerHistEntity_.getData().getPlyrApprvRstrnText() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplPlayerHistEntity_.getData().getPlyrApprvRstrnText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      if ( tplPlayerHistEntity_.getData().getPlyrSuplServText() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplPlayerHistEntity_.getData().getPlyrSuplServText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      if ( tplPlayerHistEntity_.getData().getPlyrCmntText() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerHistEntity_.getData().getPlyrCmntText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      if ( ( ( TplPlayerHistEntityVO ) tplPlayerHistEntity_.getData() ).getRecStatCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplPlayerHistEntityVO ) tplPlayerHistEntity_.getData() ).getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      
      if ( tplPlayerHistEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerHistEntity_.getData().getLastUpdUserId() );
      }
      else 
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      
      if ( tplPlayerHistEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerHistEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else 
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      
      if ( ( ( TplPlayerHistEntityVO ) tplPlayerHistEntity_.getData() ).getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplPlayerHistEntityVO ) tplPlayerHistEntity_.getData() ).getLastAuthDate().getTime() ) );
      }
      else 
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      
      if ( ( ( TplPlayerHistEntityVO ) tplPlayerHistEntity_.getData() ).getLastAuthUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplPlayerHistEntityVO ) tplPlayerHistEntity_.getData() ).getLastAuthUserId() );
      }
      else 
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
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
    
    return tplPlayerHistEntity_;
  }

  /**
   * Recupera um player conforme a chave determinada
   * @see com.citibank.ods.persistence.pl.dao.BaseTplPlayerDAO#find(com.citibank.ods.entity.pl.BaseTplPlayerEntity)
   */
  public BaseTplPlayerEntity find( BaseTplPlayerEntity baseTplPlayerEntity_ )
  {
    return null;
  }

}