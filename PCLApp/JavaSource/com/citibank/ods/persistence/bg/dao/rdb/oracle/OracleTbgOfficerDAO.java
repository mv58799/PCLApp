package com.citibank.ods.persistence.bg.dao.rdb.oracle;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTbgOfficerEntity;
import com.citibank.ods.entity.pl.TbgOfficerEntity;
import com.citibank.ods.persistence.bg.dao.TbgOfficerDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 26, 2007
 *  
 */

public class OracleTbgOfficerDAO extends BaseOracleTbgOfficerDAO implements
    TbgOfficerDAO
{
  //Tabela TPL_OFFICER_CMPL
  private static final String C_TPL_OFFICER_CMPL = C_PL_SCHEMA
                                                   + "TPL_OFFICER_CMPL";

  //Tabela TPL_OFFICER_TYPE
  private static final String C_TPL_OFFICER_TYPE = C_PL_SCHEMA
                                                   + "TPL_OFFICER_TYPE";

  //Descrição do tipo de Banker
  protected static final String C_OFFCR_TYPE_TEXT = "OFFCR_TYPE_TEXT";

  //Tabela TBG_OFFICER
  private static final String C_TBG_OFFICER = C_BG_SCHEMA + "TBG_OFFICER";

  //Tabela TBG_OFFICER - Alias
  private static final String C_TBG_OFFICER_ALIAS = "BANK";

  //Tabela TPL_OFFICER_TYPE - Alias
  private static final String C_TPL_OFFICER_TYPE_ALIAS = "OFFC_TYPE";

  //Tabela TPL_OFFICER_CMPL - ALIAS
  private static final String C_TPL_OFFICER_CMPL_ALIAS = "CMPL";

  //Tipo de Bakner
  protected String C_OFFCR_TYPE_CODE = "OFFCR_TYPE_CODE";

  //Número internacional do officer
  protected String C_OFFCR_INTL_NBR = "OFFCR_INTL_NBR";

  //Status do registro
  protected String C_REC_STAT_CODE = "REC_STAT_CODE";

  //Constante número da categoria
  private static final String C_OFFCR_CAT_CODE_VALUE = "'1'";

  private static final String C_OFFCR_STAT_CODE_VALUE = "'2'";

  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {

    TbgOfficerEntity tbgOfficerEntity;
    ArrayList oracleTbgOfficerEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tbgOfficerEntity = new TbgOfficerEntity();
        tbgOfficerEntity.getData().setOffcrCatCode(
                                                    resultSet_.getString( this.C_OFFCR_CAT_CODE ) );
        tbgOfficerEntity.getData().setOffcrChnnlCode(
                                                      resultSet_.getString( this.C_OFFCR_CHNNL_CODE ) != null
                                                                                                             ? new BigInteger(
                                                                                                                               resultSet_.getString( this.C_OFFCR_CHNNL_CODE ) )
                                                                                                             : null );
        tbgOfficerEntity.getData().setOffcrEmailName(
                                                      resultSet_.getString( this.C_OFFCR_EMAIL_NAME ) );
        tbgOfficerEntity.getData().setOffcrNameText(
                                                     resultSet_.getString( this.C_OFFCR_NAME_TEXT ) );
        tbgOfficerEntity.getData().setOffcrNbr(
                                                resultSet_.getString( this.C_OFFCR_NBR ) != null
                                                                                                ? new BigInteger(
                                                                                                                  resultSet_.getString( this.C_OFFCR_NBR ) )
                                                                                                : null );

        tbgOfficerEntity.getData().setOffcrRealNbr(
                                                    resultSet_.getString( this.C_OFFCR_REAL_NBR ) != null
                                                                                                         ? new BigInteger(
                                                                                                                           resultSet_.getString( this.C_OFFCR_REAL_NBR ) )
                                                                                                         : null );

        tbgOfficerEntity.getData().setOffcrStartDate(
                                                      resultSet_.getDate( this.C_OFFCR_START_DATE ) );
        tbgOfficerEntity.getData().setOffcrStatCode(
                                                     resultSet_.getString( this.C_OFFCR_STAT_CODE ) );

        oracleTbgOfficerEntities.add( tbgOfficerEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return oracleTbgOfficerEntities;
  } /*
     * (non-Javadoc)
     * @see com.citibank.ods.persistence.pl.dao.TbgCustAddressDAO#update(com.citibank.ods.entity.pl.TbgCustAddressEntity)
     */

  public void update( TbgOfficerEntity officerEntity_ )
  {
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TbgCustAddressDAO#delete(com.citibank.ods.entity.pl.TbgCustAddressEntity)
   */
  public void delete( TbgOfficerEntity officerEntity_ )
  {
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TbgCustAddressDAO#insert(com.citibank.ods.entity.pl.TbgCustAddressEntity)
   */
  public TbgOfficerEntity insert( TbgOfficerEntity officerEntity_ )
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTbgOfficerDAO#find(com.citibank.ods.entity.pl.BaseTbgOfficerEntity)
   */
  public BaseTbgOfficerEntity find( BaseTbgOfficerEntity tbgOfficerEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList tplOfficerEntities;
    BaseTbgOfficerEntity officerEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_OFFCR_CAT_CODE + ", " );
      query.append( C_OFFCR_CHNNL_CODE + ", " );
      query.append( C_OFFCR_EMAIL_NAME + ", " );
      query.append( C_OFFCR_NAME_TEXT + ", " );
      query.append( C_OFFCR_NBR + ", " );
      query.append( C_OFFCR_REAL_NBR + ", " );
      query.append( C_OFFCR_START_DATE + ", " );
      query.append( C_OFFCR_STAT_CODE + " " );
      query.append( " FROM " );
      query.append( C_TBG_OFFICER );
      query.append( " WHERE " );
      query.append( C_OFFCR_NBR + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1,
                         tbgOfficerEntity_.getData().getOffcrNbr().longValue() );

      resultSet = preparedStatement.executeQuery();

      tplOfficerEntities = instantiateFromResultSet( resultSet );

      if ( tplOfficerEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplOfficerEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        officerEntity = ( BaseTbgOfficerEntity ) tplOfficerEntities.get( 0 );
      }

      return officerEntity;
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
   * @see com.citibank.ods.persistence.pl.dao.TbgOfficerDAO#list(java.math.BigInteger,
   *      java.lang.String, java.math.BigInteger)
   */
  public DataSet list( BigInteger offcrNbr_, String offcrNameText_,
                      BigInteger offcrRealNbr_, BigInteger offcrIntnlNbr_,
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
      query.append( "SELECT DISTINCT " );
      query.append( C_TBG_OFFICER_ALIAS + "." + C_OFFCR_NBR + ", " );
      query.append( C_TBG_OFFICER_ALIAS + "." + C_OFFCR_NAME_TEXT + ", " );
      query.append( C_TBG_OFFICER_ALIAS + "." + C_OFFCR_REAL_NBR + ", " );
      query.append( C_TPL_OFFICER_CMPL_ALIAS + "." + C_OFFCR_TYPE_TEXT + ", " );
      query.append( C_TPL_OFFICER_CMPL_ALIAS + "." + C_OFFCR_INTL_NBR + ", " );
      query.append( "(Case when  " + C_TPL_OFFICER_CMPL_ALIAS + "."
                    + C_OFFCR_NBR + " IS NULL THEN 0  ELSE 1 END) AS QTD" );
      query.append( " FROM " );
      query.append( C_TBG_OFFICER + " " + C_TBG_OFFICER_ALIAS + ", " );
      query.append( "(SELECT " + C_OFFCR_NBR + ", " + C_OFFCR_TYPE_TEXT + ", "
                    + C_TPL_OFFICER_TYPE_ALIAS + "." + C_OFFCR_TYPE_CODE + ", "
                    + C_OFFCR_INTL_NBR + " FROM " + C_TPL_OFFICER_CMPL + " "
                    + C_TPL_OFFICER_CMPL_ALIAS + ", " + C_TPL_OFFICER_TYPE
                    + " " + C_TPL_OFFICER_TYPE_ALIAS );
     
      query.append( " WHERE " + C_TPL_OFFICER_CMPL_ALIAS + "."
                    + C_REC_STAT_CODE + " <> '" );
      query.append( BaseODSEntity.C_REC_STAT_CODE_INACTIVE + "' AND " );
      query.append( C_TPL_OFFICER_CMPL_ALIAS + "." + C_OFFCR_TYPE_CODE + " = " );
      query.append( C_TPL_OFFICER_TYPE_ALIAS + "." + C_OFFCR_TYPE_CODE + " ) " );
      query.append( C_TPL_OFFICER_CMPL_ALIAS );
//DUVIDA======================================================
		  query.append(" ,BG.TBG_PORTFOLIO PORT ");                  
//DUVIDA======================================================
      query.append( " WHERE " );
      query.append( C_TBG_OFFICER_ALIAS + "." + C_OFFCR_NBR + " = " );
      query.append( C_TPL_OFFICER_CMPL_ALIAS + "." + C_OFFCR_NBR + "(+) AND " );
//DUVIDA======================================================
	  query.append(" BANK.OFFCR_NBR = PORT.PORTF_OFFCR_NBR ");
	  query.append(" AND   SUBSTR(PORT.PORTF_CODE,5,2)>=80 ");
	  query.append(" AND   SUBSTR(PORT.PORTF_CODE,5,2)<=89 and ");
//DUVIDA======================================================
      query.append( C_TBG_OFFICER_ALIAS + "." + C_OFFCR_CAT_CODE + " = "
                    + C_OFFCR_CAT_CODE_VALUE + " AND " );
      query.append( C_TBG_OFFICER_ALIAS + "." + C_OFFCR_STAT_CODE + " = "
                    + C_OFFCR_STAT_CODE_VALUE );

      String criteria = "";

      if ( offcrNbr_ != null )
      {
        criteria = criteria + C_TBG_OFFICER_ALIAS + "." + C_OFFCR_NBR
                   + " = ? AND ";
      }
      if ( offcrNameText_ != null && offcrNameText_ != "" )
      {
        criteria = criteria + "UPPER(" + C_TBG_OFFICER_ALIAS + "."
                   + C_OFFCR_NAME_TEXT + ") like ? AND ";
      }
      if ( offcrRealNbr_ != null )
      {
        criteria = criteria + C_TBG_OFFICER_ALIAS + "." + C_OFFCR_REAL_NBR
                   + " = ? AND ";
      }

      if ( offcrIntnlNbr_ != null )
      {
        criteria = criteria + C_TPL_OFFICER_CMPL_ALIAS + "." + C_OFFCR_INTL_NBR
                   + " = ? AND ";
      }

      if ( offcrTypeCode_ != null && !offcrTypeCode_.equals( "" ) )
      {
        criteria = criteria + C_TPL_OFFICER_CMPL_ALIAS + "."
                   + C_OFFCR_TYPE_CODE + " = ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	AND " + criteria );
      }

      query.append( " ORDER BY " );
      query.append( C_TBG_OFFICER_ALIAS + "." + C_OFFCR_NAME_TEXT );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( offcrNbr_ != null )
      {
        preparedStatement.setLong( count++, offcrNbr_.longValue() );
      }

      if ( offcrNameText_ != null && !offcrNameText_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + offcrNameText_.toUpperCase() + "%" );
      }
      if ( offcrRealNbr_ != null )
      {
        preparedStatement.setLong( count++, offcrRealNbr_.longValue() );
      }
      if ( offcrIntnlNbr_ != null )
      {
        preparedStatement.setLong( count++, offcrIntnlNbr_.longValue() );
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
    return rsds;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TbgOfficerDAO#exists(com.citibank.ods.entity.pl.TbgOfficerEntity)
   */
  public boolean exists( TbgOfficerEntity officerEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( officerEntity_ );
    }
    catch ( NoRowsReturnedException e )
    {
      exists = false;
    }

    return exists;
  }
}