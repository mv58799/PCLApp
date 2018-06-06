package com.citibank.ods.persistence.bg.dao.rdb.oracle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.bg.TbgPointAcctInvstEntity;
import com.citibank.ods.entity.bg.valueobject.TbgPointAcctInvstEntityVO;
import com.citibank.ods.persistence.bg.dao.TbgPointAcctInvstDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.persistence.bg.dao.rdb.oracle;
 * @version 1.0
 * @author michele.monteiro,19/06/2007
 *  
 */

public class OracleTbgPointAcctInvstDAO extends BaseOracleTbgPointAcctInvstDAO
    implements TbgPointAcctInvstDAO
{

  // Tabela TBG_POINT_ACCT_INVST
  private static final String C_TBG_POINT_ACCT_INVST = C_BG_SCHEMA
                                                       + "TBG_POINT_ACCT_INVST";

  /**
   * Retorna os dados da tabela de acordo com a entity passada por parâmentro
   *  
   */
  public TbgPointAcctInvstEntity find(
                                      TbgPointAcctInvstEntity tbgPointAcctInvstEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList tbgPointEntities;
    TbgPointAcctInvstEntity tbgPointAcctInvstEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "SELECT " );
      query.append( C_ACCT_BRCH_NBR + ", " );
      query.append( C_ACCT_BUS_NBR + ", " );
      query.append( C_CUR_ACCT_NBR + ", " );
      query.append( C_CUST_MOV_ORIG_CODE + ", " );
      query.append( C_INVST_ACCT_BRCH_NBR + ", " );
      query.append( C_INVST_ACCT_BUS_NBR + ", " );
      query.append( C_INVST_CUR_ACCT_NBR );
      query.append( " FROM " );
      query.append( C_TBG_POINT_ACCT_INVST );
      query.append( " WHERE TRIM(" );
      query.append( C_CUR_ACCT_NBR + " )= ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString(
                           1,
                           tbgPointAcctInvstEntity_.getData().getCurAcctNbr().trim() );

      resultSet = preparedStatement.executeQuery();

	  preparedStatement.replaceParametersInQuery(query.toString());

      tbgPointEntities = instantiateFromResultSet( resultSet );

      if ( tbgPointEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tbgPointEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tbgPointAcctInvstEntity = ( TbgPointAcctInvstEntity ) tbgPointEntities.get( 0 );
      }

      return tbgPointAcctInvstEntity;
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
   * Instancia entidades a partir do ResultSet.
   * 
   * @param resultSet_ - ResultSet utilizado para instanciar entidades.
   * @return ArrayList - Entidades instanciadas a partir do ResultSet.
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    ArrayList tbgPointEntities = new ArrayList();
    TbgPointAcctInvstEntity tbgPointAcctInvstEntity;
    TbgPointAcctInvstEntityVO tbgPointAcctInvstEntityVO;

    try
    {
      while ( resultSet_.next() )
      {
        tbgPointAcctInvstEntity = new TbgPointAcctInvstEntity();

        tbgPointAcctInvstEntityVO = ( TbgPointAcctInvstEntityVO ) tbgPointAcctInvstEntity.getData();

        tbgPointAcctInvstEntityVO.setAcctBrchNbr( resultSet_.getString( this.C_ACCT_BRCH_NBR ) );
        tbgPointAcctInvstEntityVO.setInvstAcctBrchNbr( resultSet_.getString( this.C_INVST_ACCT_BRCH_NBR ) );
        tbgPointAcctInvstEntityVO.setAcctBusNbr( resultSet_.getString( this.C_ACCT_BUS_NBR ) );
        tbgPointAcctInvstEntityVO.setCustMovOrigCode( resultSet_.getString( this.C_CUST_MOV_ORIG_CODE ) );
        tbgPointAcctInvstEntityVO.setInvstCurAcctNbr( resultSet_.getString( this.C_INVST_CUR_ACCT_NBR ) );
        tbgPointAcctInvstEntityVO.setInvstAcctBusNbr( resultSet_.getString( this.C_INVST_ACCT_BUS_NBR ) );
        tbgPointAcctInvstEntityVO.setCurAcctNbr( resultSet_.getString( this.C_CUR_ACCT_NBR ) );

        tbgPointEntities.add( tbgPointAcctInvstEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return tbgPointEntities;
  }

  public boolean exists( TbgPointAcctInvstEntity tbgPointAcctInvstEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tbgPointAcctInvstEntity_ );
    }
    catch ( NoRowsReturnedException e )
    {
      exists = false;
    }

    return exists;
  }
}