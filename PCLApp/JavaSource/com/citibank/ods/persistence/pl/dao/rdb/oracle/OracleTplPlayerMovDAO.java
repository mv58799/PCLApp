package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
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
import com.citibank.ods.entity.pl.TplPlayerMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplPlayerMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplPlayerMovDAO;
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

public class OracleTplPlayerMovDAO extends BaseOracleTplPlayerDAO implements
    TplPlayerMovDAO
{

  /*
   * Nome da tabela
   */
  private static final String C_TPL_PLAYER_MOV = C_PL_SCHEMA + "TPL_PLAYER_MOV";

  private static final String C_TPL_PLAYER_ROLE_MOV = C_PL_SCHEMA + "TPL_PLAYER_ROLE_MOV";

  /*
   * Campos específicos da tabela
   */
  private String C_OPERN_CODE = "OPERN_CODE";

  protected String C_OPERN_TEXT = "OPERN_TEXT";

  /**
   * Este método altera os dados de um player
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplPlayerMovDAO#update(com.citibank.ods.entity.pl.TplPlayerMovEntity)
   */
  public TplPlayerMovEntity update( TplPlayerMovEntity playerMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_PLAYER_MOV + " SET " );
      query.append( C_PLYR_NAME + "= ?," );
      query.append( C_PLYR_ADDR_TEXT + "= ?," );
      query.append( C_PLYR_DUE_DLG_DATE + "= ?," );
      query.append( C_PLYR_DUE_DLG_EXEC_IND + "= ?," );
      query.append( C_PLYR_DUE_DLG_END_DATE + "= ?," );
      query.append( C_PLYR_DUE_DLG_RNW_DATE + "= ?," );
      query.append( C_PLYR_INVST_CMTTE_APPRV_DATE + "= ?," );
      query.append( C_PLYR_APPRV_RSTRN_TEXT + "= ?," );
      query.append( C_PLYR_SUPL_SERV_TEXT + "= ?," );
      query.append( C_PLYR_CMNT_TEXT + "= ?," );
      query.append( C_LAST_UPD_USER_ID + "= ?," );
      query.append( C_LAST_UPD_DATE + "= ?," );
      query.append( C_OPERN_CODE + "= ?" );
      query.append( " WHERE " + C_PLYR_CNPJ_NBR + "= ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      TplPlayerMovEntityVO tplPlayerMovEntityVO = ( TplPlayerMovEntityVO ) playerMovEntity_.getData();
      int count = 1;

      if ( tplPlayerMovEntityVO.getPlyrName() != null
           && !tplPlayerMovEntityVO.getPlyrName().equals( "" ) )
      {
        preparedStatement.setString( count++, playerMovEntity_.getData().getPlyrName() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrAddrText() != null
           && !tplPlayerMovEntityVO.getPlyrAddrText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             playerMovEntity_.getData().getPlyrAddrText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrDueDlgDate() != null
           && !tplPlayerMovEntityVO.getPlyrDueDlgDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               playerMovEntity_.getData().getPlyrDueDlgDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrDueDlgExecInd() != null
           && !tplPlayerMovEntityVO.getPlyrDueDlgExecInd().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             playerMovEntity_.getData().getPlyrDueDlgExecInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrDueDlgEndDate() != null
           && !tplPlayerMovEntityVO.getPlyrDueDlgEndDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               playerMovEntity_.getData().getPlyrDueDlgEndDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrDueDlgRnwDate() != null
           && !tplPlayerMovEntityVO.getPlyrDueDlgRnwDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               playerMovEntity_.getData().getPlyrDueDlgRnwDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrInvstCmtteApprvDate() != null
           && !tplPlayerMovEntityVO.getPlyrInvstCmtteApprvDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               playerMovEntity_.getData().getPlyrInvstCmtteApprvDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrApprvRstrnText() != null
           && !tplPlayerMovEntityVO.getPlyrApprvRstrnText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             playerMovEntity_.getData().getPlyrApprvRstrnText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrSuplServText() != null
           && !tplPlayerMovEntityVO.getPlyrSuplServText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             playerMovEntity_.getData().getPlyrSuplServText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrCmntText() != null
           && !tplPlayerMovEntityVO.getPlyrCmntText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             playerMovEntity_.getData().getPlyrCmntText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerMovEntityVO.getLastUpdUserId() != null
           && !tplPlayerMovEntityVO.getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             playerMovEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerMovEntityVO.getLastUpdDate() != null
           && !tplPlayerMovEntityVO.getLastUpdDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               playerMovEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerMovEntityVO.getOpernCode() != null
           && !tplPlayerMovEntityVO.getOpernCode().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplPlayerMovEntityVO ) playerMovEntity_.getData() ).getOpernCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrCnpjNbr() != null
           && !tplPlayerMovEntityVO.getPlyrCnpjNbr().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             playerMovEntity_.getData().getPlyrCnpjNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return playerMovEntity_;

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
   * Este método exclui um player que se enquadre com o critérios informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplPlayerMovDAO#delete(com.citibank.ods.entity.pl.TplPlayerMovEntity)
   */

  public TplPlayerMovEntity delete( TplPlayerMovEntity playerMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "DELETE FROM " );
      query.append( C_TPL_PLAYER_MOV );
      query.append( " WHERE " );
      query.append( C_PLYR_CNPJ_NBR + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString( 1, playerMovEntity_.getData().getPlyrCnpjNbr() );

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return playerMovEntity_;
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
   * Este método insere um novo registro de player
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplPlayerMovDAO#insert(com.citibank.ods.entity.pl.TplPlayerMovEntity)
   */

  public TplPlayerMovEntity insert( TplPlayerMovEntity playerMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_PLAYER_MOV + " ( " );
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
      query.append( C_OPERN_CODE + ")" );
      query.append( " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      TplPlayerMovEntityVO tplPlayerMovEntityVO = ( TplPlayerMovEntityVO ) playerMovEntity_.getData();
      int count = 1;

      if ( tplPlayerMovEntityVO.getPlyrCnpjNbr() != null
           && !tplPlayerMovEntityVO.getPlyrCnpjNbr().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             playerMovEntity_.getData().getPlyrCnpjNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrName() != null
           && !tplPlayerMovEntityVO.getPlyrName().equals( "" ) )
      {
        preparedStatement.setString( count++, playerMovEntity_.getData().getPlyrName() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrAddrText() != null
           && !tplPlayerMovEntityVO.getPlyrAddrText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             playerMovEntity_.getData().getPlyrAddrText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrDueDlgDate() != null
           && !tplPlayerMovEntityVO.getPlyrDueDlgDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               playerMovEntity_.getData().getPlyrDueDlgDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrDueDlgExecInd() != null
           && !tplPlayerMovEntityVO.getPlyrDueDlgExecInd().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             playerMovEntity_.getData().getPlyrDueDlgExecInd() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrDueDlgEndDate() != null
           && !tplPlayerMovEntityVO.getPlyrDueDlgEndDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               playerMovEntity_.getData().getPlyrDueDlgEndDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrDueDlgRnwDate() != null
           && !tplPlayerMovEntityVO.getPlyrDueDlgRnwDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               playerMovEntity_.getData().getPlyrDueDlgRnwDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrInvstCmtteApprvDate() != null
           && !tplPlayerMovEntityVO.getPlyrInvstCmtteApprvDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               playerMovEntity_.getData().getPlyrInvstCmtteApprvDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrApprvRstrnText() != null
           && !tplPlayerMovEntityVO.getPlyrApprvRstrnText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             playerMovEntity_.getData().getPlyrApprvRstrnText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrSuplServText() != null
           && !tplPlayerMovEntityVO.getPlyrSuplServText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             playerMovEntity_.getData().getPlyrSuplServText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerMovEntityVO.getPlyrCmntText() != null
           && !tplPlayerMovEntityVO.getPlyrCmntText().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             playerMovEntity_.getData().getPlyrCmntText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerMovEntityVO.getLastUpdUserId() != null
           && !tplPlayerMovEntityVO.getLastUpdUserId().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             playerMovEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerMovEntityVO.getLastUpdDate() != null
           && !tplPlayerMovEntityVO.getLastUpdDate().equals( "" ) )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               playerMovEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerMovEntityVO.getOpernCode() != null
           && !tplPlayerMovEntityVO.getOpernCode().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             ( ( TplPlayerMovEntityVO ) playerMovEntity_.getData() ).getOpernCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

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
    return playerMovEntity_;
  }

  /**
   * Este método retorna uma lista de players que se enquadre com os critérios
   * informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplPlayerMovDAO#update(
   *      java.math.BigInteger,java.lang.String,java.lang.String)
   */

  public DataSet list( String plyrCnpjNbr_, String plyrName_,
                      String plyrRoleTypeCode_, String lastUpdUserId_ )
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
      query.append( C_TPL_PLAYER_MOV + "." + C_PLYR_CNPJ_NBR + ", " );
      query.append( C_TPL_PLAYER_MOV + "." + C_PLYR_NAME + ", " );
      query.append( C_TPL_PLAYER_MOV + "." + C_PLYR_ADDR_TEXT + ", " );
      query.append( C_TPL_PLAYER_MOV + "." + C_PLYR_DUE_DLG_DATE + ", " );
      query.append( C_TPL_PLAYER_MOV + "." + C_PLYR_DUE_DLG_EXEC_IND + ", " );
      query.append( C_TPL_PLAYER_MOV + "." + C_PLYR_DUE_DLG_END_DATE + ", " );
      query.append( C_TPL_PLAYER_MOV + "." + C_PLYR_DUE_DLG_RNW_DATE + ", " );
      query.append( C_TPL_PLAYER_MOV + "." + C_PLYR_INVST_CMTTE_APPRV_DATE
                    + ", " );
      query.append( C_TPL_PLAYER_MOV + "." + C_PLYR_APPRV_RSTRN_TEXT + ", " );
      query.append( C_TPL_PLAYER_MOV + "." + C_PLYR_SUPL_SERV_TEXT + ", " );
      query.append( C_TPL_PLAYER_MOV + "." + C_PLYR_CMNT_TEXT + ", " );
      query.append( C_TPL_PLAYER_MOV + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( C_TPL_PLAYER_MOV + "." + C_LAST_UPD_DATE + ", " );
      query.append( C_TPL_PLAYER_MOV + "." + C_OPERN_CODE );
      query.append( " FROM " );
      query.append( C_TPL_PLAYER_MOV + ", " );
      query.append( C_TPL_PLAYER_ROLE_MOV );

      String criteria = "";

      criteria = criteria + C_TPL_PLAYER_MOV + "." + C_PLYR_CNPJ_NBR + " = "
                 + C_TPL_PLAYER_ROLE_MOV + "." + C_PLYR_CNPJ_NBR + " AND ";

      if ( plyrCnpjNbr_ != null && !plyrCnpjNbr_.equals( "" ) )
      {
        criteria = criteria + "UPPER(" + C_TPL_PLAYER_MOV + "."
                   + C_PLYR_CNPJ_NBR + ") LIKE ? AND ";
      }

      if ( plyrName_ != null && !plyrName_.equals( "" ) )
      {
        criteria = criteria + "UPPER(\"" + C_PLYR_NAME + "\") LIKE ? AND ";
      }

      if ( plyrRoleTypeCode_ != null && !plyrRoleTypeCode_.equals( "" ) )
      {
        criteria = criteria + "UPPER(\"" + C_PLYR_ROLE_TYPE_CODE
                   + "\") = ? AND ";
      }

      if ( lastUpdUserId_ != null && !lastUpdUserId_.equals( "" ) )
      {
        criteria = criteria + "UPPER(" + C_TPL_PLAYER_MOV + "."
                   + C_LAST_UPD_USER_ID + ") = ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria );
      }

      query.append( " ORDER BY " + C_PLYR_NAME );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( plyrCnpjNbr_ != null && !plyrCnpjNbr_.equals( "" ) )
      {
        preparedStatement.setString( count++, plyrCnpjNbr_ );
      }

      if ( plyrName_ != null && !plyrName_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + plyrName_.toUpperCase() + "%" );
      }

      if ( plyrRoleTypeCode_ != null && !plyrRoleTypeCode_.equals( "" ) )
      {
        preparedStatement.setString( count++, plyrRoleTypeCode_.toUpperCase() );
      }

      if ( lastUpdUserId_ != null && lastUpdUserId_.length() != 0 )
      {
        preparedStatement.setString( count++, lastUpdUserId_.toUpperCase() );
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

    String[] codeColumn = { C_OPERN_CODE };
    String[] nameColumn = { C_OPERN_TEXT };
    rsds.outerJoin( ODSConstraintDecoder.decodeOpern(), codeColumn, codeColumn,
                    nameColumn );

    return rsds;
  }

  /**
   * Este método busca uma categoria de risco que se enquadre com os critérios
   * informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtMovDAO#insert(com.citibank.ods.entity.pl.TplProdRiskCatPrvtMovEntity)
   */
  public BaseTplPlayerEntity find( BaseTplPlayerEntity tplPlayerEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList tplPlayerMovEntities;
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
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_OPERN_CODE );
      query.append( " FROM " );
      query.append( C_TPL_PLAYER_MOV );
      query.append( " WHERE " );
      query.append( C_PLYR_CNPJ_NBR + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString( 1, tplPlayerEntity_.getData().getPlyrCnpjNbr() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      tplPlayerMovEntities = instantiateFromResultSet( resultSet );

      if ( tplPlayerMovEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplPlayerMovEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplPlayerEntity = ( BaseTplPlayerEntity ) tplPlayerMovEntities.get( 0 );
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
    TplPlayerMovEntityVO tplPlayerMovEntityVO;
    TplPlayerMovEntity tplPlayerMovEntity;
    ArrayList tplPlayerEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplPlayerMovEntity = new TplPlayerMovEntity();

        tplPlayerMovEntity.getData().setPlyrCnpjNbr(
                                                     resultSet_.getString( C_PLYR_CNPJ_NBR ) );
        tplPlayerMovEntity.getData().setPlyrName(
                                                  resultSet_.getString( C_PLYR_NAME ) );
        tplPlayerMovEntity.getData().setPlyrAddrText(
                                                      resultSet_.getString( C_PLYR_ADDR_TEXT ) );
        tplPlayerMovEntity.getData().setPlyrDueDlgDate(
                                                        resultSet_.getDate( C_PLYR_DUE_DLG_DATE ) );
        tplPlayerMovEntity.getData().setPlyrDueDlgExecInd(
                                                           resultSet_.getString( C_PLYR_DUE_DLG_EXEC_IND ) );
        tplPlayerMovEntity.getData().setPlyrDueDlgEndDate(
                                                           resultSet_.getDate( C_PLYR_DUE_DLG_END_DATE ) );
        tplPlayerMovEntity.getData().setPlyrDueDlgRnwDate(
                                                           resultSet_.getDate( C_PLYR_DUE_DLG_RNW_DATE ) );
        tplPlayerMovEntity.getData().setPlyrInvstCmtteApprvDate(
                                                                 resultSet_.getDate( C_PLYR_INVST_CMTTE_APPRV_DATE ) );
        tplPlayerMovEntity.getData().setPlyrApprvRstrnText(
                                                            resultSet_.getString( C_PLYR_APPRV_RSTRN_TEXT ) );
        tplPlayerMovEntity.getData().setPlyrSuplServText(
                                                          resultSet_.getString( C_PLYR_SUPL_SERV_TEXT ) );
        tplPlayerMovEntity.getData().setPlyrCmntText(
                                                      resultSet_.getString( C_PLYR_CMNT_TEXT ) );
        tplPlayerMovEntity.getData().setLastUpdUserId(
                                                       resultSet_.getString( C_LAST_UPD_USER_ID ) );
        tplPlayerMovEntity.getData().setLastUpdDate(
                                                     resultSet_.getTimestamp( C_LAST_UPD_DATE ) );
        //      Casting para a atribuicao das colunas especificas

        tplPlayerMovEntityVO = ( TplPlayerMovEntityVO ) tplPlayerMovEntity.getData();

        tplPlayerMovEntityVO.setOpernCode( resultSet_.getString( C_OPERN_CODE ) );

        tplPlayerEntities.add( tplPlayerMovEntity );
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

  public boolean exists( TplPlayerMovEntity TplPlayerMovEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( TplPlayerMovEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

}