package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import com.citibank.ods.entity.pl.BaseTplProdRiskCatPrvtEntity;
import com.citibank.ods.entity.pl.TplOfficerCmplEntity;
import com.citibank.ods.entity.pl.valueobject.TplOfficerCmplEntityVO;
import com.citibank.ods.persistence.pl.dao.TplOfficerCmplDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
/**
 * @author bruno.zanetti
 * 
 * Classe DAO da tabela Tpl_Officer_Cmpl
 */
public class OracleTplOfficerCmplDAO extends BaseOracleTplOfficerCmplDAO
    implements TplOfficerCmplDAO
{
  private static final String C_TPL_OFFICER_CMPL = C_PL_SCHEMA
                                                   + "TPL_OFFICER_CMPL";

  private static final String C_TPL_OFFICER_TYPE = C_PL_SCHEMA
                                                   + "TPL_OFFICER_TYPE";

  private static final String C_TBG_OFFICER = C_BG_SCHEMA + "TBG_OFFICER";

  protected static final String C_OFFCR_TYPE_TEXT = "OFFCR_TYPE_TEXT";

  /**
   * Realiza a atualização de um registro na tabela
   */
  public void update( TplOfficerCmplEntity officerCmplEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_OFFICER_CMPL + " SET " );
      query.append( C_OFFCR_TYPE_CODE + "= ?," );
      query.append( C_OFFCR_INTL_NBR + "= ?," );
      query.append( C_LAST_UPD_DATE + "= ?," );
      query.append( C_LAST_UPD_USER_ID + "= ?," );
      query.append( C_LAST_AUTH_DATE + "= ?," );
      query.append( C_LAST_AUTH_USER_ID + "= ?," );
      query.append( C_REC_STAT_CODE + "= ? " );
      query.append( "WHERE " + C_OFFCR_NBR + "= ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      TplOfficerCmplEntityVO tplOfficerCmplEntityVO = ( TplOfficerCmplEntityVO ) officerCmplEntity_.getData();

      preparedStatement.setString( 1, tplOfficerCmplEntityVO.getOffcrTypeCode() );

      if (tplOfficerCmplEntityVO.getOffcrIntlNbr() ==  null) {
    	  preparedStatement.setNull(2, java.sql.Types.DOUBLE);
      } else {
    	  preparedStatement.setLong( 2,
                         tplOfficerCmplEntityVO.getOffcrIntlNbr().longValue() );
      }

      Date lastUpdDate = tplOfficerCmplEntityVO.getLastUpdDate();
      preparedStatement.setTimestamp( 3, new Timestamp( lastUpdDate.getTime() ) );

      preparedStatement.setString( 4, tplOfficerCmplEntityVO.getLastUpdUserId() );

      Date lastAuthDate = tplOfficerCmplEntityVO.getLastAuthDate();
      preparedStatement.setTimestamp( 5, new Timestamp( lastAuthDate.getTime() ) );
      preparedStatement.setString( 6, tplOfficerCmplEntityVO.getLastAuthUserId() );
      preparedStatement.setString( 7, tplOfficerCmplEntityVO.getRecStatCode() );
      preparedStatement.setLong( 8, tplOfficerCmplEntityVO.getOffcrNbr().longValue() );

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
  public void delete( TplOfficerCmplEntity officerCmplCurrentEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " );
      query.append( C_TPL_OFFICER_CMPL + " " );
      query.append( "SET " );
      query.append( C_REC_STAT_CODE + "= ? " );
      query.append( "WHERE " + C_OFFCR_NBR + "= ? " );

      TplOfficerCmplEntityVO tplOfficerCmplEntityVO = ( TplOfficerCmplEntityVO ) officerCmplCurrentEntity_.getData();

      int count = 1;

      preparedStatement.setLong( count++,
                         tplOfficerCmplEntityVO.getOffcrNbr().longValue() );

      preparedStatement.setString( count++, tplOfficerCmplEntityVO.getRecStatCode() );

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
   * Realiza a inserção de um registro na tabela
   */
  public TplOfficerCmplEntity insert(
                                     TplOfficerCmplEntity officerCmplCurrentEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_OFFICER_CMPL + " (" );
      query.append( C_OFFCR_NBR + ", " );
      query.append( C_OFFCR_TYPE_CODE + ", " );
      query.append( C_OFFCR_INTL_NBR + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      TplOfficerCmplEntityVO tplOfficerCmplEntityVO = ( TplOfficerCmplEntityVO ) officerCmplCurrentEntity_.getData();

      if ( tplOfficerCmplEntityVO.getOffcrNbr() != null )
      {
        preparedStatement.setLong( count++,
                           tplOfficerCmplEntityVO.getOffcrNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      preparedStatement.setString( count++, tplOfficerCmplEntityVO.getOffcrTypeCode() );

      if ( tplOfficerCmplEntityVO.getOffcrIntlNbr() != null )
      {
        preparedStatement.setLong( count++,
                           tplOfficerCmplEntityVO.getOffcrIntlNbr().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplOfficerCmplEntityVO.getLastUpdDate() != null )
      {
        Timestamp ts = new Timestamp(
                                      tplOfficerCmplEntityVO.getLastUpdDate().getTime() );
        preparedStatement.setTimestamp( count++, ts );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      preparedStatement.setString( count++, tplOfficerCmplEntityVO.getLastUpdUserId() );

      if ( tplOfficerCmplEntityVO.getLastAuthDate() != null )
      {
        Timestamp ts = new Timestamp(
                                      tplOfficerCmplEntityVO.getLastAuthDate().getTime() );
        preparedStatement.setTimestamp( count++, ts );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      preparedStatement.setString( count++, tplOfficerCmplEntityVO.getLastAuthUserId() );

      preparedStatement.setString( count++, tplOfficerCmplEntityVO.getRecStatCode() );

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return officerCmplCurrentEntity_;
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
   * Realiza a consulta em lista dos registros da tabela a partir dos critérios
   * de pesquisa passados por parâmetro
   */
  public DataSet list( BigInteger offcrIntnlNbr_, BigInteger offcrNbr_,
                      String offcrTypeCode_ )
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
      query.append( C_TPL_OFFICER_CMPL + "." + C_OFFCR_TYPE_CODE + ", " );
      query.append( C_TPL_OFFICER_TYPE + "." + C_OFFCR_TYPE_TEXT + ", " );
      query.append( C_TBG_OFFICER + "." + C_OFFCR_NAME_TEXT + ", " );
      query.append( C_TPL_OFFICER_CMPL + "." + C_OFFCR_INTL_NBR + ", " );
      query.append( C_TPL_OFFICER_CMPL + "." + C_LAST_UPD_DATE + ", " );
      query.append( C_TPL_OFFICER_CMPL + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( C_TPL_OFFICER_CMPL + "." + C_LAST_AUTH_DATE + ", " );
      query.append( C_TPL_OFFICER_CMPL + "." + C_OFFCR_NBR + ", " );
      query.append( C_TPL_OFFICER_CMPL + "." + C_LAST_AUTH_USER_ID + ", " );
      query.append( C_TPL_OFFICER_CMPL + "." + C_REC_STAT_CODE + " " );
      query.append( " FROM " );
      query.append( C_TPL_OFFICER_CMPL + "," + C_TPL_OFFICER_TYPE + ","
                    + C_TBG_OFFICER );

      String criteria = "";

      criteria = criteria + C_TPL_OFFICER_CMPL + "." + C_OFFCR_TYPE_CODE
                 + " = " + C_TPL_OFFICER_TYPE + "." + C_OFFCR_TYPE_CODE
                 + " AND ";

      criteria = criteria + C_TPL_OFFICER_CMPL + "." + C_OFFCR_NBR + " = "
                 + C_TBG_OFFICER + "." + C_OFFCR_NBR + " AND ";

      criteria = criteria + C_TPL_OFFICER_CMPL + "." + C_REC_STAT_CODE
                 + " != '"
                 + BaseTplProdRiskCatPrvtEntity.C_REC_STAT_CODE_INACTIVE
                 + "' AND ";

      if ( offcrIntnlNbr_ != null && offcrIntnlNbr_.longValue() != 0 )
      {
        criteria = criteria + C_TPL_OFFICER_CMPL + "." + C_OFFCR_INTL_NBR
                   + " = ? AND ";
      }
      if ( offcrNbr_ != null && offcrNbr_.longValue() != 0 )
      {
        criteria = criteria + C_TPL_OFFICER_CMPL + "." + C_OFFCR_NBR
                   + " = ? AND ";
      }
      if ( offcrTypeCode_ != null && !offcrTypeCode_.equals( "" ) )
      {
        criteria = criteria + C_TPL_OFFICER_CMPL + "." + C_OFFCR_TYPE_CODE
                   + " = ? AND ";
      }
      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria );
      }

      query.append( " ORDER BY " + C_OFFCR_NAME_TEXT );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( offcrIntnlNbr_ != null && offcrIntnlNbr_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, offcrIntnlNbr_.longValue() );
      }
      if ( offcrNbr_ != null && offcrNbr_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, offcrNbr_.longValue() );
      }
      if ( offcrTypeCode_ != null && !offcrTypeCode_.equals( "" ) )
      {
        preparedStatement.setString( count++, offcrTypeCode_ );
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

    String[] codeColumn = { C_REC_STAT_CODE };
    String[] nameColumn = { C_REC_STAT_TEXT };
    rsds.outerJoin( ODSConstraintDecoder.decodeRecStatus(), codeColumn,
                    codeColumn, nameColumn );

    return rsds;
  }

  /**
   * Recupera um registro da tabela
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
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_OFFCR_NBR + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE + " " );
      query.append( " FROM " );
      query.append( C_TPL_OFFICER_CMPL );
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

  /*
   * Recupera um ArrayList a partir de um ResultSet
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    Timestamp lastUpdDateTs;
    TplOfficerCmplEntityVO tplOfficerCmplEntityVO;
    TplOfficerCmplEntity tplOfficerCmplEntity;
    ArrayList oracleTplOfficerCmplEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplOfficerCmplEntity = new TplOfficerCmplEntity();
        tplOfficerCmplEntityVO = ( TplOfficerCmplEntityVO ) tplOfficerCmplEntity.getData();

        lastUpdDateTs = resultSet_.getTimestamp( this.C_LAST_UPD_DATE );
        tplOfficerCmplEntityVO.setLastUpdDate( new Date(
                                                         lastUpdDateTs.getTime() ) );

        tplOfficerCmplEntityVO.setLastUpdUserId( resultSet_.getString( this.C_LAST_UPD_USER_ID ) );
        
        tplOfficerCmplEntityVO.setOffcrIntlNbr( null);
        if (resultSet_.getString( this.C_OFFCR_INTL_NBR ) != null ){
        	tplOfficerCmplEntityVO.setOffcrIntlNbr( new BigInteger(
                    resultSet_.getString( this.C_OFFCR_INTL_NBR ) ) );
        }
        
        tplOfficerCmplEntityVO.setOffcrNbr( new BigInteger(
                                                            resultSet_.getString( this.C_OFFCR_NBR ) ) );
        tplOfficerCmplEntityVO.setOffcrTypeCode( resultSet_.getString( this.C_OFFCR_TYPE_CODE ) );
        tplOfficerCmplEntityVO.setRecStatCode( resultSet_.getString( this.C_REC_STAT_CODE ) );

        tplOfficerCmplEntityVO.setLastAuthUserId( resultSet_.getString( this.C_LAST_AUTH_USER_ID ) );

        Timestamp lastAuthDateTs = resultSet_.getTimestamp( this.C_LAST_AUTH_DATE );
        tplOfficerCmplEntityVO.setLastAuthDate( new Date(
                                                          lastAuthDateTs.getTime() ) );

        oracleTplOfficerCmplEntities.add( tplOfficerCmplEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return oracleTplOfficerCmplEntities;
  }

  /**
   * Verifica um determinado registro existe na tabela
   */
  public boolean exists( TplOfficerCmplEntity officerCmplEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( officerCmplEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  /**
   * Verifica se um determinado registro existe com status ativo na tabela
   */
  public boolean existsActive( TplOfficerCmplEntity officerCmplEntity_ )
  {
    boolean exists = true;

    try
    {
      TplOfficerCmplEntity tplOfficerCmplEntity = ( TplOfficerCmplEntity ) this.find( officerCmplEntity_ );
      TplOfficerCmplEntityVO tplOfficerCmplEntityVO = ( TplOfficerCmplEntityVO ) tplOfficerCmplEntity.getData();
      if ( !TplOfficerCmplEntity.C_REC_STAT_CODE_ACTIVE.equals( tplOfficerCmplEntityVO.getRecStatCode() ) )
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