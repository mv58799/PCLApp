package com.citibank.ods.persistence.bg.dao.rdb.oracle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.persistence.bg.dao.BaseTbgCustMailDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

//
//�2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.bg.dao.rdb.oracle;
 * @version 1.0
 * @author gerson.a.rodrigues,May 26, 2006
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseOracleTbgCustMailDAO extends BaseOracleDAO implements
    BaseTbgCustMailDAO
{

  public String C_CUST_NBR = "CUST_NBR";

  public String C_EMAIL_SEQ_NBR = "EMAIL_SEQ_NBR";

  public String C_EMAIL_TEXT = "EMAIL_TEXT";

  public String C_EMAIL_MAIL_IND = "EMAIL_MAIL_IND";

  public String C_TBG_CUST_MAIL = C_BG_SCHEMA + "TBG_CUST_MAIL";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.bg.dao.BaseTbgCustCellDAO#list(java.math.BigInteger)
   */
  public DataSet list( Long custNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet resultSetDataSet = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_EMAIL_SEQ_NBR + ", " );
      query.append( C_EMAIL_TEXT + ", " );
      query.append( C_EMAIL_MAIL_IND );      
      query.append( " FROM " );
      query.append( C_TBG_CUST_MAIL );

      String criteria = "";

      if ( custNbr_ != null && custNbr_.longValue() != 0 )
      {
        criteria = criteria + OracleTplCustomerPrvtDAO.C_CUST_NBR + " = ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( custNbr_ != null && custNbr_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
      }

      resultSet = preparedStatement.executeQuery();

	  preparedStatement.replaceParametersInQuery(query.toString());

      resultSetDataSet = new ResultSetDataSet( resultSet );

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

    return resultSetDataSet;
  }

}