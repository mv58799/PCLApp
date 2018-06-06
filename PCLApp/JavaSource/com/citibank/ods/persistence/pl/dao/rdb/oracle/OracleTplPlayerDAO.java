package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplPlayerEntity;
import com.citibank.ods.entity.pl.TplPlayerEntity;
import com.citibank.ods.entity.pl.TplProductFamilyPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplPlayerEntityVO;
import com.citibank.ods.persistence.pl.dao.TplPlayerDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author acacio.domingos,04/04/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class OracleTplPlayerDAO extends BaseOracleTplPlayerDAO implements
    TplPlayerDAO
{

  /*
   * Nome da tabela
   */
  private static final String C_TPL_PLAYER = C_PL_SCHEMA + "TPL_PLAYER";

  private static final String C_TPL_PLAYER_ROLE = C_PL_SCHEMA + "TPL_PLAYER_ROLE";

  /*
   * Campos específicos da tabela
   */
  private String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private String C_REC_STAT_CODE = "REC_STAT_CODE";

  private String C_REC_STAT_TEXT = "REC_STAT_TEXT";

  /**
   * Este método busca uma lista de Categoria de Risco do produto que se
   * enquadre com os critérios informados e que esteja com status ativo.
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtDAO#list(java.lang.String,
   *      java.lang.String)
   */

  public DataSet list( String plyrCnpjNbr_, String plyrName_,
                      String plyrRoleTypeCode_ )
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
      query.append( C_TPL_PLAYER + "." + C_PLYR_CNPJ_NBR + ", " );
      query.append( C_TPL_PLAYER + "." + C_PLYR_NAME + ", " );
      query.append( C_TPL_PLAYER + "." + C_PLYR_ADDR_TEXT + ", " );
      query.append( C_TPL_PLAYER + "." + C_PLYR_DUE_DLG_DATE + ", " );
      query.append( C_TPL_PLAYER + "." + C_PLYR_DUE_DLG_EXEC_IND + ", " );
      query.append( C_TPL_PLAYER + "." + C_PLYR_DUE_DLG_END_DATE + ", " );
      query.append( C_TPL_PLAYER + "." + C_PLYR_DUE_DLG_RNW_DATE + ", " );
      query.append( C_TPL_PLAYER + "." + C_PLYR_INVST_CMTTE_APPRV_DATE + ", " );
      query.append( C_TPL_PLAYER + "." + C_PLYR_APPRV_RSTRN_TEXT + ", " );
      query.append( C_TPL_PLAYER + "." + C_PLYR_SUPL_SERV_TEXT + ", " );
      query.append( C_TPL_PLAYER + "." + C_PLYR_CMNT_TEXT + ", " );
      query.append( C_TPL_PLAYER + "." + C_REC_STAT_CODE + ", " );
      query.append( C_TPL_PLAYER + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( C_TPL_PLAYER + "." + C_LAST_UPD_DATE + ", " );
      query.append( C_TPL_PLAYER + "." + C_LAST_AUTH_DATE + ", " );
      query.append( C_TPL_PLAYER + "." + C_LAST_AUTH_USER_ID );
      query.append( " FROM " );
      query.append( C_TPL_PLAYER + ", " );
      query.append( C_TPL_PLAYER_ROLE );

      String criteria = "";

      criteria = criteria + C_TPL_PLAYER + "." + C_REC_STAT_CODE + " != '"
                 + BaseTplPlayerEntity.C_REC_STAT_CODE_INACTIVE + "' AND "
                 + C_TPL_PLAYER + "." + C_PLYR_CNPJ_NBR + " = "
                 + C_TPL_PLAYER_ROLE + "." + C_PLYR_CNPJ_NBR + " AND ";

      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
      {
        criteria = criteria + C_TPL_PLAYER + "." + C_PLYR_CNPJ_NBR
                   + " = ? AND ";
      }

      if ( plyrName_ != null && !"".equals( plyrName_ ) )
      {
        criteria = criteria + "UPPER(\"" + C_PLYR_NAME + "\") like ? AND ";
      }

      if ( plyrRoleTypeCode_ != null && !"".equals( plyrRoleTypeCode_ ) )
      {
        criteria = criteria + "UPPER(\"" + C_PLYR_ROLE_TYPE_CODE
                   + "\") = ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria );
      }

      query.append( " ORDER BY " + C_PLYR_NAME );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
      {
        preparedStatement.setString( count++, plyrCnpjNbr_ );
      }

      if ( plyrName_ != null && !"".equals( plyrName_ ) )
      {
        preparedStatement.setString( count++, "%" + plyrName_.toUpperCase() + "%" );
      }

      if ( plyrRoleTypeCode_ != null && !"".equals( plyrRoleTypeCode_ ) )
      {
        preparedStatement.setString( count++, plyrRoleTypeCode_.toUpperCase() );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      rsds = new ResultSetDataSet( resultSet );
      resultSet.close();
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
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
   * Este método insere um novo registro de um player
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplPlayerDAO#insert(com.citibank.ods.entity.pl.TplPlayerEntity)
   */

  public TplPlayerEntity insert( TplPlayerEntity tplPlayerEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_PLAYER + " (" );
      query.append( C_PLYR_CNPJ_NBR + ", " );
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
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " ) VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplPlayerEntity_.getData().getPlyrCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerEntity_.getData().getPlyrCnpjNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrName() != null )
      {
        preparedStatement.setString( count++, tplPlayerEntity_.getData().getPlyrName() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrAddrText() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerEntity_.getData().getPlyrAddrText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrDueDlgDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrDueDlgExecInd() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerEntity_.getData().getPlyrDueDlgExecInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrDueDlgEndDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerEntity_.getData().getPlyrDueDlgEndDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrDueDlgRnwDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerEntity_.getData().getPlyrDueDlgRnwDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrInvstCmtteApprvDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerEntity_.getData().getPlyrInvstCmtteApprvDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrApprvRstrnText() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerEntity_.getData().getPlyrApprvRstrnText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrSuplServText() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerEntity_.getData().getPlyrSuplServText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrCmntText() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerEntity_.getData().getPlyrCmntText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      TplPlayerEntityVO tplPlayerEntityVO = ( TplPlayerEntityVO ) tplPlayerEntity_.getData();

      if ( tplPlayerEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++, tplPlayerEntityVO.getLastAuthUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, tplPlayerEntityVO.getRecStatCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplPlayerEntity_;

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
   * Altera os dados de um Player.
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplPlayerDAO#update(com.citibank.ods.entity.pl.TplPlayerEntity)
   */

  public void update( TplPlayerEntity tplPlayerEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_PLAYER );
      query.append( " SET " );
      query.append( C_PLYR_NAME + " = ?, " );
      query.append( C_PLYR_ADDR_TEXT + " = ?, " );
      query.append( C_PLYR_DUE_DLG_DATE + " = ?, " );
      query.append( C_PLYR_DUE_DLG_EXEC_IND + " = ?, " );
      query.append( C_PLYR_DUE_DLG_END_DATE + " = ?, " );
      query.append( C_PLYR_DUE_DLG_RNW_DATE + " = ?, " );
      query.append( C_PLYR_INVST_CMTTE_APPRV_DATE + " = ?, " );
      query.append( C_PLYR_APPRV_RSTRN_TEXT + " = ?, " );
      query.append( C_PLYR_SUPL_SERV_TEXT + " = ?, " );
      query.append( C_PLYR_CMNT_TEXT + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_LAST_AUTH_DATE + " = ?, " );
      query.append( C_LAST_AUTH_USER_ID + " = ?, " );
      query.append( C_REC_STAT_CODE + " = ? " );
      query.append( " WHERE " );
      query.append( C_PLYR_CNPJ_NBR + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplPlayerEntity_.getData().getPlyrName() != null )
      {
        preparedStatement.setString( count++, tplPlayerEntity_.getData().getPlyrName() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrAddrText() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerEntity_.getData().getPlyrAddrText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrDueDlgDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrDueDlgExecInd() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerEntity_.getData().getPlyrDueDlgExecInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrDueDlgEndDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerEntity_.getData().getPlyrDueDlgEndDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrDueDlgRnwDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerEntity_.getData().getPlyrDueDlgRnwDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrInvstCmtteApprvDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerEntity_.getData().getPlyrInvstCmtteApprvDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrApprvRstrnText() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerEntity_.getData().getPlyrApprvRstrnText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrSuplServText() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerEntity_.getData().getPlyrSuplServText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrCmntText() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerEntity_.getData().getPlyrCmntText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      TplPlayerEntityVO tplPlayerEntityVO = ( TplPlayerEntityVO ) tplPlayerEntity_.getData();

      if ( tplPlayerEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++, tplPlayerEntityVO.getLastAuthUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, tplPlayerEntityVO.getRecStatCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerEntity_.getData().getPlyrCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerEntity_.getData().getPlyrCnpjNbr() );
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
  }

  /**
   * Remove um registro de Player.
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplPlayerDAO#delete(com.citibank.ods.entity.pl.TplplayerEntity)
   */

  public void delete( TplPlayerEntity tplPlayerEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " UPDATE " + C_TPL_PLAYER );
      query.append( " SET " );
      query.append( C_REC_STAT_CODE + "= ?" );
      query.append( " WHERE " + C_PLYR_CNPJ_NBR + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString(
                           1,
                           ( ( TplPlayerEntityVO ) tplPlayerEntity_.getData() ).getRecStatCode() );

      preparedStatement.setString( 2, tplPlayerEntity_.getData().getPlyrCnpjNbr() );

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }
  }

  /**
   * Este método busca um Player que se enquadre com os critérios informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.BaseTplplayerDAO#find(com.citibank.ods.entity.pl.BaseTplplayerEntity)
   */
  public BaseTplPlayerEntity find( BaseTplPlayerEntity baseTplPlayerEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    ArrayList tplPlayerEntities;
    BaseTplPlayerEntity tplPlayerEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_PLYR_CNPJ_NBR + ", " );
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
      query.append( C_PLYR_DV_CODE + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID );
      query.append( " FROM " );
      query.append( C_TPL_PLAYER );
      query.append( " WHERE " );
      query.append( C_PLYR_CNPJ_NBR + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString( 1, baseTplPlayerEntity_.getData().getPlyrCnpjNbr() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      tplPlayerEntities = instantiateFromResultSet( resultSet );

      if ( tplPlayerEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }

      else if ( tplPlayerEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }

      else
      {
        tplPlayerEntity = ( BaseTplPlayerEntity ) tplPlayerEntities.get( 0 );
      }

      return tplPlayerEntity;
    }

    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
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
  {
    TplPlayerEntity tplPlayerEntity;
    ArrayList tplPlayerEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplPlayerEntity = new TplPlayerEntity();
        tplPlayerEntity.getData().setPlyrCnpjNbr(
                                                  resultSet_.getString( C_PLYR_CNPJ_NBR ) );
        tplPlayerEntity.getData().setPlyrName(
                                               resultSet_.getString( C_PLYR_NAME ) );
        tplPlayerEntity.getData().setPlyrAddrText(
                                                   resultSet_.getString( C_PLYR_ADDR_TEXT ) );
        tplPlayerEntity.getData().setPlyrDueDlgDate(
                                                     resultSet_.getDate( C_PLYR_DUE_DLG_DATE ) );
        tplPlayerEntity.getData().setPlyrDueDlgExecInd(
                                                        resultSet_.getString( C_PLYR_DUE_DLG_EXEC_IND ) );
        tplPlayerEntity.getData().setPlyrDueDlgEndDate(
                                                        resultSet_.getDate( C_PLYR_DUE_DLG_END_DATE ) );
        tplPlayerEntity.getData().setPlyrDueDlgRnwDate(
                                                        resultSet_.getDate( C_PLYR_DUE_DLG_RNW_DATE ) );
        tplPlayerEntity.getData().setPlyrInvstCmtteApprvDate(
                                                              resultSet_.getDate( C_PLYR_INVST_CMTTE_APPRV_DATE ) );
        tplPlayerEntity.getData().setPlyrApprvRstrnText(
                                                         resultSet_.getString( C_PLYR_APPRV_RSTRN_TEXT ) );
        tplPlayerEntity.getData().setPlyrSuplServText(
                                                       resultSet_.getString( C_PLYR_SUPL_SERV_TEXT ) );
        tplPlayerEntity.getData().setPlyrCmntText(
                                                   resultSet_.getString( C_PLYR_CMNT_TEXT ) );

        tplPlayerEntity.getData().setPlyrDvCode(
                                                 resultSet_.getString( C_PLYR_DV_CODE ) != null
                                                                                               ? new BigInteger(
                                                                                                                 resultSet_.getString( C_PLYR_DV_CODE ) )
                                                                                               : null );

        tplPlayerEntity.getData().setLastUpdUserId(
                                                    resultSet_.getString( C_LAST_UPD_USER_ID ) );
        tplPlayerEntity.getData().setLastUpdDate(
                                                  resultSet_.getDate( C_LAST_UPD_DATE ) );
        //      Casting para a atribuicao das colunas especificas
        TplPlayerEntityVO tplPlayerEntityVO = ( TplPlayerEntityVO ) tplPlayerEntity.getData();
        tplPlayerEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );
        tplPlayerEntityVO.setLastAuthDate( resultSet_.getDate( C_LAST_AUTH_DATE ) );
        tplPlayerEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );

        tplPlayerEntities.add( tplPlayerEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return tplPlayerEntities;
  }

  /**
   * Verifica se existe um registro com o código passado
   */
  public boolean exists( TplPlayerEntity TplPlayerEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( TplPlayerEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  /**
   * Verifica se existe um registro com o código passado e o status deste
   * registro é Ativo
   */

  public boolean existsActive( TplPlayerEntity TplPlayerEntity_ )
  {
    boolean exists = true;

    try
    {
      TplPlayerEntity tplPlayerEntity = ( TplPlayerEntity ) this.find( TplPlayerEntity_ );
      TplPlayerEntityVO playerEntityVO = ( TplPlayerEntityVO ) tplPlayerEntity.getData();
      if ( !TplPlayerEntity.C_REC_STAT_CODE_ACTIVE.equals( playerEntityVO.getRecStatCode() ) )
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

  /**
   * 
   * Lista de Emissor para o combo da tela de Produto
   * 
   */
	public DataSet listEmissor() {

		ManagedRdbConnection connection = null;
	    CitiStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    ResultSetDataSet rsds = null;
	    StringBuffer query = new StringBuffer();

	    try
	    {
	      connection = OracleODSDAOFactory.getConnection();
	      
			query.append("SELECT DISTINCT ");
			query.append(C_TPL_PLAYER + "." + C_PLYR_CNPJ_NBR + ", ");
			query.append(C_TPL_PLAYER + "." + C_PLYR_NAME );
			query.append(" FROM ");
			query.append(C_TPL_PLAYER);

			query.append(" INNER JOIN " + C_TPL_PLAYER_ROLE + " ON " + C_TPL_PLAYER_ROLE + ".PLYR_CNPJ_NBR = " + C_TPL_PLAYER + ".PLYR_CNPJ_NBR " 
					+ " AND " + C_TPL_PLAYER_ROLE + ".PLYR_ROLE_TYPE_CODE = '" + BaseTplPlayerEntity.C_PLYR_ROLE_TYPE_CODE_EMISSOR + "' ");

			query.append(" ORDER BY " + C_TPL_PLAYER + "." + C_PLYR_NAME);

	      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

	      resultSet = preparedStatement.executeQuery();
		  preparedStatement.replaceParametersInQuery(query.toString());


	      rsds = new ResultSetDataSet( resultSet );
	      resultSet.close();
	    }
	    catch ( SQLException e )
	    {
	      throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
	    }
	    finally
	    {
	      closeStatement( preparedStatement );
	      closeConnection( connection );
	    }
	    return rsds;
		
	}

}