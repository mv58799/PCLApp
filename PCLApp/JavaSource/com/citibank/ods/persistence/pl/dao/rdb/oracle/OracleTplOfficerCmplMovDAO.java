package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplOfficerCmplEntity;
import com.citibank.ods.entity.pl.TplOfficerCmplMovementEntity;
import com.citibank.ods.entity.pl.valueobject.TplOfficerCmplMovementEntityVO;
import com.citibank.ods.persistence.pl.dao.TplOfficerCmplMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author gerson.a.rodrigues Classe DAO da tabela TPL_OFFICER_CMPL_MOV
 */
public class OracleTplOfficerCmplMovDAO extends BaseOracleTplOfficerCmplDAO
    implements TplOfficerCmplMovDAO
{

  /*
   * Tabelas utilizadas pelo DAO
   */
  private static final String C_TPL_OFFICER_CMPL_MOV = C_PL_SCHEMA
                                                       + "TPL_OFFICER_CMPL_MOV";

  private static final String C_TPL_OFFICER_TYPE = C_PL_SCHEMA
                                                   + "TPL_OFFICER_TYPE";

  private static final String C_TBG_OFFICER = C_BG_SCHEMA + "TBG_OFFICER";

  /*
   * Campos específicos da tabela
   */
  private String C_OPERN_CODE = "OPERN_CODE";

  private String C_OPERN_TEXT = "OPERN_TEXT";

  protected String C_OFFCR_TYPE_TEXT = "OFFCR_TYPE_TEXT";

  private String C_OFFCR_NAME_TEXT = "OFFCR_NAME_TEXT";

  /**
   * Realiza a atualização de um registro na tabela
   */
  public void update( TplOfficerCmplMovementEntity officerCmplMovementEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_OFFICER_CMPL_MOV + " SET " );
      query.append( C_OFFCR_TYPE_CODE + "= ?," );
      query.append( C_OFFCR_INTL_NBR + "= ?," );
      query.append( C_LAST_UPD_DATE + "= ?," );
      query.append( C_LAST_UPD_USER_ID + "= ?," );
      query.append( C_OPERN_CODE + "= ? " );
      query.append( "WHERE " + C_OFFCR_NBR + "= ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString(
                           1,
                           officerCmplMovementEntity_.getData().getOffcrTypeCode() );
      
      if (officerCmplMovementEntity_.getData().getOffcrIntlNbr() == null) {
    	  preparedStatement.setNull(
                  2, java.sql.Types.DOUBLE );
      } else {
    	  preparedStatement.setLong(
                         2,
                         officerCmplMovementEntity_.getData().getOffcrIntlNbr().longValue() );
      }
      
      Date lastUpdDate = officerCmplMovementEntity_.getData().getLastUpdDate();
      preparedStatement.setTimestamp( 3, new Timestamp( lastUpdDate.getTime() ) );

      preparedStatement.setString(
                           4,
                           officerCmplMovementEntity_.getData().getLastUpdUserId() );
      preparedStatement.setString(
                           5,
                           ( ( TplOfficerCmplMovementEntityVO ) officerCmplMovementEntity_.getData() ).getOpernCode() );
      preparedStatement.setLong(
                         6,
                         officerCmplMovementEntity_.getData().getOffcrNbr().longValue() );

      preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally {
		closeStatement(preparedStatement);
		closeConnection(connection);
	}    
  }

  /**
   * Realiza a remoção de um registro na tabela
   */
  public void delete( TplOfficerCmplMovementEntity officerCmplMovementEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "DELETE FROM " );
      query.append( C_TPL_OFFICER_CMPL_MOV );
      query.append( " WHERE " );
      query.append( C_OFFCR_NBR + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         officerCmplMovementEntity_.getData().getOffcrNbr().longValue() );

      preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally {
		closeStatement(preparedStatement);
		closeConnection(connection);
	}

  }

  /**
   * Realiza a inserção de um registro na tabela
   */
  public TplOfficerCmplMovementEntity insert(
                                             TplOfficerCmplMovementEntity officerCmplMovementEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_OFFICER_CMPL_MOV + " ( " );
      query.append( C_OFFCR_NBR + ", " );
      query.append( C_OFFCR_TYPE_CODE + ", " );
      query.append( C_OPERN_CODE + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_OFFCR_INTL_NBR );
      query.append( ") VALUES ( ?, ?, ?, ?, ?, ? )" );
      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      if ( officerCmplMovementEntity_.getData().getOffcrNbr() != null
           && officerCmplMovementEntity_.getData().getOffcrNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           1,
                           officerCmplMovementEntity_.getData().getOffcrNbr().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT );
      }

      if ( officerCmplMovementEntity_.getData().getOffcrTypeCode() != null
           && officerCmplMovementEntity_.getData().getOffcrTypeCode().length() > 0 )
      {
        preparedStatement.setString(
                             2,
                             officerCmplMovementEntity_.getData().getOffcrTypeCode() );
      }
      else
      {
        preparedStatement.setString( 2, "" );
      }

      if ( ( ( TplOfficerCmplMovementEntityVO ) officerCmplMovementEntity_.getData() ).getOpernCode() != null
           && ( ( TplOfficerCmplMovementEntityVO ) officerCmplMovementEntity_.getData() ).getOpernCode().length() > 0 )
        preparedStatement.setString(
                             3,
                             ( ( TplOfficerCmplMovementEntityVO ) officerCmplMovementEntity_.getData() ).getOpernCode() );

      else
      {
        preparedStatement.setString( 3, "" );
      }

      Date lastUpdDate = officerCmplMovementEntity_.getData().getLastUpdDate();
      preparedStatement.setTimestamp( 4, new Timestamp( lastUpdDate.getTime() ) );
      preparedStatement.setString(
                           5,
                           officerCmplMovementEntity_.getData().getLastUpdUserId() );
      if ( officerCmplMovementEntity_.getData().getOffcrIntlNbr() != null
           && officerCmplMovementEntity_.getData().getOffcrIntlNbr().longValue() > 0 )
      {
        preparedStatement.setLong(
                           6,
                           officerCmplMovementEntity_.getData().getOffcrIntlNbr().longValue() );
      }
      else
      {
        preparedStatement.setNull( 6, Types.INTEGER );
      }

      preparedStatement.execute();
	  preparedStatement.replaceParametersInQuery(query.toString());
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally {
		closeStatement(preparedStatement);
		closeConnection(connection);
	}
    return officerCmplMovementEntity_;
  }

  /**
   * Realiza a consulta em lista dos dados de movimento a partir dos critérios
   * de pesquisa passados por parâmetro
   */
  public DataSet list( BigInteger offcrIntnlNbr_, BigInteger offcrNbr_,
                      String offcrTypeCode_, String lastUpdUserId_,
                      String offcrNameText_ )
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
      query.append( C_TPL_OFFICER_CMPL_MOV + "." + C_OFFCR_NBR + ", " );
      query.append( C_TPL_OFFICER_TYPE + "." + C_OFFCR_TYPE_TEXT + ", " );
      query.append( C_TBG_OFFICER + "." + C_OFFCR_NAME_TEXT + ", " );
      query.append( C_TPL_OFFICER_CMPL_MOV + "." + C_OFFCR_TYPE_CODE + ", " );
      query.append( C_TPL_OFFICER_CMPL_MOV + "." + C_OFFCR_INTL_NBR + ", " );
      query.append( C_TPL_OFFICER_CMPL_MOV + "." + C_OPERN_CODE + ", " );
      query.append( C_TPL_OFFICER_CMPL_MOV + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( C_TPL_OFFICER_CMPL_MOV + "." + C_LAST_UPD_DATE + " " );
      query.append( " FROM " );
      query.append( C_TPL_OFFICER_CMPL_MOV + "," + C_TPL_OFFICER_TYPE + ","
                    + C_TBG_OFFICER );

      String criteria = "";

      criteria = criteria + C_TPL_OFFICER_CMPL_MOV + "." + C_OFFCR_TYPE_CODE
                 + " = " + C_TPL_OFFICER_TYPE + "." + C_OFFCR_TYPE_CODE
                 + " AND ";

      criteria = criteria + C_TPL_OFFICER_CMPL_MOV + "." + C_OFFCR_NBR + " = "
                 + C_TBG_OFFICER + "." + C_OFFCR_NBR + " AND ";

      if ( offcrNbr_ != null && offcrNbr_.longValue() != 0 )
      {
        criteria = criteria + C_TPL_OFFICER_CMPL_MOV + "." + C_OFFCR_NBR
                   + " = ? AND ";
      }
      if ( offcrTypeCode_ != null && !offcrTypeCode_.equals( "" ) )
      {
        criteria = criteria + C_TPL_OFFICER_CMPL_MOV + "." + C_OFFCR_TYPE_CODE
                   + " = ? AND ";
      }
      if ( offcrIntnlNbr_ != null && offcrIntnlNbr_.longValue() != 0 )
      {
        criteria = criteria + C_TPL_OFFICER_CMPL_MOV + "." + C_OFFCR_INTL_NBR
                   + " = ? AND ";
      }
      if ( lastUpdUserId_ != null && lastUpdUserId_.length() != 0 )
      {
        criteria = criteria + "UPPER(" + C_TPL_OFFICER_CMPL_MOV + "."
                   + C_LAST_UPD_USER_ID + ")like ? AND ";
      }
      if ( offcrNameText_ != null && !offcrNameText_.equals( "" ) )
      {
        criteria = criteria + "UPPER(" + C_TBG_OFFICER + "."
                   + C_OFFCR_NAME_TEXT + ") LIKE ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria );
      }

      query.append( " ORDER BY " + C_OFFCR_NAME_TEXT );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( offcrNbr_ != null && offcrNbr_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, offcrNbr_.longValue() );
      }
      if ( offcrTypeCode_ != null && !offcrTypeCode_.equals( "" ) )
      {
        preparedStatement.setString( count++, offcrTypeCode_ );
      }
      if ( offcrIntnlNbr_ != null && offcrIntnlNbr_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, offcrIntnlNbr_.longValue() );
      }
      if ( lastUpdUserId_ != null && lastUpdUserId_.length() != 0 )
      {
        preparedStatement.setString( count++, "%" + lastUpdUserId_.toUpperCase() + "%" );
      }
      if ( offcrNameText_ != null && !offcrNameText_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + offcrNameText_.toUpperCase() + "%" );
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
   * Recupera um registro do banco de dados
   */
  public BaseTplOfficerCmplEntity find( BaseTplOfficerCmplEntity officerEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList tplOfficerCmplEntities;
    BaseTplOfficerCmplEntity officerCmplEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_OFFCR_TYPE_CODE + ", " );
      query.append( C_OFFCR_INTL_NBR + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_OFFCR_NBR + ", " );
      query.append( C_OPERN_CODE + " " );
      query.append( " FROM " );
      query.append( C_TPL_OFFICER_CMPL_MOV );
      query.append( " WHERE " );
      query.append( C_OFFCR_NBR + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, officerEntity_.getData().getOffcrNbr().longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());


      tplOfficerCmplEntities = instantiateFromResultSet( resultSet );

      if ( tplOfficerCmplEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplOfficerCmplEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        officerCmplEntity = ( BaseTplOfficerCmplEntity ) tplOfficerCmplEntities.get( 0 );
      }

      return officerCmplEntity;
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally {
		closeStatement(preparedStatement);
		closeConnection(connection);
	}    
  }

  /**
   * 
   * Verifica se um determinado registro existe na base de dados.
   * 
   * @param officerMovementEntity__ Entidade contendo o identificador do
   *          registro que será verificada a existência.
   * @return Indicador de existência do registro (true/false).
   */
  public boolean exists( TplOfficerCmplMovementEntity officerMovementEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( officerMovementEntity_ );
    }
    catch ( NoRowsReturnedException e )
    {
      exists = false;
    }

    return exists;
  }

  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    TplOfficerCmplMovementEntity tplOfficerCmplMovementEntity;
    Timestamp tsLastUpdDate;
    BigInteger OffcrIntlNbr;
    ArrayList oracleTplOfficerCmplEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplOfficerCmplMovementEntity = new TplOfficerCmplMovementEntity();

        tsLastUpdDate = resultSet_.getTimestamp( this.C_LAST_UPD_DATE );
        tplOfficerCmplMovementEntity.getData().setLastUpdDate(
                                                               new Date(
                                                                         tsLastUpdDate.getTime() ) );
        tplOfficerCmplMovementEntity.getData().setLastUpdUserId(
                                                                 resultSet_.getString( this.C_LAST_UPD_USER_ID ) );
        OffcrIntlNbr = resultSet_.getString( this.C_OFFCR_INTL_NBR ) == null
                                                                            ? null
                                                                            : new BigInteger(
                                                                                              resultSet_.getString( this.C_OFFCR_INTL_NBR ) );
        tplOfficerCmplMovementEntity.getData().setOffcrIntlNbr( OffcrIntlNbr );
        tplOfficerCmplMovementEntity.getData().setOffcrNbr(
                                                            new BigInteger(
                                                                            resultSet_.getString( this.C_OFFCR_NBR ) ) );
        tplOfficerCmplMovementEntity.getData().setOffcrTypeCode(
                                                                 resultSet_.getString( this.C_OFFCR_TYPE_CODE ) );
        ( ( TplOfficerCmplMovementEntityVO ) tplOfficerCmplMovementEntity.getData() ).setOpernCode( resultSet_.getString( this.C_OPERN_CODE ) );

        oracleTplOfficerCmplEntities.add( tplOfficerCmplMovementEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return oracleTplOfficerCmplEntities;
  }
}