package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.bg.BaseTbgSegmentEntity;
import com.citibank.ods.entity.bg.TbgSegmentEntity;
import com.citibank.ods.entity.pl.TbgOfficerEntity;
import com.citibank.ods.persistence.pl.dao.TbgSegmentDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author acacio.domingos,Apr 24, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class OracleTbgSegmentDAO extends BaseOracleTbgSegmentDAO implements
    TbgSegmentDAO
{

  private static final String C_TBG_SEGMENT = C_BG_SCHEMA + "TBG_SEGMENT";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TbgSegmentDAO#update(com.citibank.ods.entity.bg.TbgSegmentEntity)
   */
  public void update( TbgSegmentEntity segmentEntity_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TbgSegmentDAO#delete(com.citibank.ods.entity.bg.TbgSegmentEntity)
   */
  public void delete( TbgSegmentEntity segmentEntity_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TbgSegmentDAO#insert(com.citibank.ods.entity.bg.TbgSegmentEntity)
   */
  public TbgOfficerEntity insert( TbgSegmentEntity segmentEntity_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TbgSegmentDAO#list(java.lang.String)
   */
  public DataSet list( String segNameCode_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TbgSegmentDAO#exists(com.citibank.ods.entity.bg.TbgSegmentEntity)
   */
  public boolean exists( TbgSegmentEntity segmentEntity_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    return false;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TbgSegmentDAO#loadDomain(com.citibank.ods.entity.bg.TbgSegmentEntity)
   */
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
      query.append( " TRIM( " + C_SEG_NAME_CODE + ") AS " + C_SEG_NAME_CODE
                    + ", " );
      query.append( C_SEG_NAME_TEXT );
      query.append( " FROM " );
      query.append( C_TBG_SEGMENT );

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

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTbgSegmentDAO#find(com.citibank.ods.entity.bg.BaseTbgSegmentEntity)
   */
  public BaseTbgSegmentEntity find( BaseTbgSegmentEntity segmentEntity_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    return null;
  }

}