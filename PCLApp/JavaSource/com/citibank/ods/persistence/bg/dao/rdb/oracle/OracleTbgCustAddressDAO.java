package com.citibank.ods.persistence.bg.dao.rdb.oracle;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.bg.BaseTbgCustAddressEntity;
import com.citibank.ods.entity.bg.TbgCustAddressEntity;
import com.citibank.ods.entity.bg.valueobject.TbgCustAddressEntityVO;
import com.citibank.ods.persistence.bg.dao.TbgCustAddressDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author hamilton.matos
 */

public class OracleTbgCustAddressDAO extends BaseOracleTbgCustAddressDAO
    implements TbgCustAddressDAO
{

  // Nome da tabela
  private static final String C_TBG_CUST_ADDRESS = C_BG_SCHEMA + "TBG_CUST_ADDRESS";

  public void update( TbgCustAddressEntity custAddressEntity_ )
  {
    //
  }

  public void delete( TbgCustAddressEntity custAddressCurrentEntity_ )
  {
    //
  }

  public TbgCustAddressEntity insert(
                                     TbgCustAddressEntity custAddressCurrentEntity_ )
  {
    return null;
  }

  /**
   * Este método busca uma lista de Endereço de cliente que se enquadre com os
   * critérios informados e que esteja com status ativo.
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtDAO#list(java.lang.String,
   *      java.lang.String)
   */
  public DataSet list( BigInteger custNbr_, BigInteger custCpfCnpjNbr_,
                      String custFullNameText_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet resultSetDataSet = null;
    StringBuffer query = new StringBuffer();
    String custAddress = "custAddress";
    String customerPrvt = "customerPrvt";

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( custAddress + "." + C_CUST_NBR + ", " );
      query.append( customerPrvt + "."
                    + OracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT + ", " );
      query.append( custAddress + "." + C_ADDR_SEQ_NBR + ", " );
      query.append( custAddress + "." + C_ADDR_TYPE_CODE + ", " );
      query.append( custAddress + "." + C_ADDR_NAME_TEXT + ", " );
      query.append( custAddress + "." + C_ADDR_CITY_TEXT + ", " );
      query.append( custAddress + "." + C_ADDR_STATE_CODE + ", " );
      query.append( custAddress + "." + C_ZIP_CODE + ", " );
      query.append( custAddress + "." + C_ZIP_EXTN_CODE + " " );
      query.append( " FROM " );
      query.append( C_TBG_CUST_ADDRESS + " custAddress, " );
      query.append( OracleTplCustomerPrvtDAO.C_TPL_CUSTOMER_PRVT + " "
                    + customerPrvt + " " );

      String criteria = "";

      if ( custNbr_ != null && custNbr_.longValue() != 0 )
      {
        criteria = criteria + "AND " + customerPrvt + "."
                   + OracleTplCustomerPrvtDAO.C_CUST_NBR + "    = ? ";
      }

      if ( custCpfCnpjNbr_ != null && custCpfCnpjNbr_.longValue() != 0 )
      {
        criteria = criteria + "AND " + customerPrvt + "."
                   + OracleTplCustomerPrvtDAO.C_CUST_CPF_CNPJ_NBR + "    = ? ";
      }

      if ( custFullNameText_ != null && !( custFullNameText_.equals( "" ) ) )
      {
        criteria = criteria + "AND UPPER ( " + customerPrvt + "."
                   + OracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT
                   + ") LIKE ( ? ) ";
      }

      criteria = criteria + " ORDER BY " + customerPrvt + "."
                 + OracleTplCustomerPrvtDAO.C_CUST_FULL_NAME_TEXT;

      query.append( "WHERE customerPrvt.CUST_NBR = custAddress.CUST_NBR AND customerPrvt.REC_STAT_CODE != 'I'"
                    + criteria );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( custNbr_ != null && custNbr_.longValue() != 0 )
      {
        preparedStatement.setLong( count++, custNbr_.longValue() );
      }

      if ( custCpfCnpjNbr_ != null && custCpfCnpjNbr_.longValue() != 0 )
      {
        preparedStatement.setString( count++, custCpfCnpjNbr_.toString() );
      }

      if ( custFullNameText_ != null && !( custFullNameText_.equals( "" ) ) )
      {
        preparedStatement.setString( count++, "%"
                                              + custFullNameText_.toUpperCase()
                                              + "%" );
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

    String[] codeColumn = { C_ADDR_TYPE_CODE };
    String[] nameColumn = { C_ADDR_TYPE_TEXT };
    resultSetDataSet.outerJoin( ODSConstraintDecoder.decodeAddrType(),
                                codeColumn, codeColumn, nameColumn );

    return resultSetDataSet;
  }

  /**
   * Este método busca um endereço de cliente que se enquadre com os critérios
   * informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.BaseTplProdRiskCatPrvtDAO#find(com.citibank.ods.entity.pl.BaseTplProdRiskCatPrvtEntity)
   */
  public BaseTbgCustAddressEntity find(
                                       BaseTbgCustAddressEntity baseTbgCustAddressEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList tbgCustAddressEntities;
    BaseTbgCustAddressEntity custAddressEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_CUST_NBR + ", " );
      query.append( C_ADDR_SEQ_NBR + ", " );
      query.append( C_ADDR_TYPE_CODE + ", " );
      query.append( C_ADDR_NAME_TEXT + ", " );
      query.append( C_ADDR_NEIGHB_TEXT + ", " );
      query.append( C_ADDR_CITY_TEXT + ", " );
      query.append( C_ADDR_STATE_CODE + ", " );
      query.append( C_ADDR_CNTRY_CODE + ", " );
      query.append( C_MAIL_BOX_NBR + ", " );
      query.append( C_ZIP_CODE + ", " );
      query.append( C_ZIP_EXTN_CODE + ", " );
      query.append( C_PHONE_AREA_CODE + ", " );
      query.append( C_PHONE_OP_CODE + ", " );
      query.append( C_PHONE_NBR + ", " );
      query.append( C_PHONE_EXTN_NBR + ", " );
      query.append( C_TELEX_AREA_CODE + ", " );
      query.append( C_TELEX_NBR + ", " );
      query.append( C_FAX_AREA_CODE + ", " );
      query.append( C_FAX_NBR + ", " );
      query.append( C_ZIP_CODE_CHANGE_TEXT );
      query.append( " FROM " );
      query.append( C_TBG_CUST_ADDRESS );
      query.append( " WHERE " );
      query.append( C_CUST_NBR + " = ? AND " );
      query.append( C_ADDR_SEQ_NBR + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                                 1,
                                 baseTbgCustAddressEntity_.getData().getCustNbr().longValue() );
      preparedStatement.setString(
                                   2,
                                   baseTbgCustAddressEntity_.getData().getAddrSeqNbr() );

      resultSet = preparedStatement.executeQuery();

	  preparedStatement.replaceParametersInQuery(query.toString());

      tbgCustAddressEntities = instantiateFromResultSet( resultSet );

      if ( tbgCustAddressEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tbgCustAddressEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        custAddressEntity = ( BaseTbgCustAddressEntity ) tbgCustAddressEntities.get( 0 );
      }
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
    return custAddressEntity;

  }

  /*
   * Retorna uma coleção de entities a partir do rs
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {

    TbgCustAddressEntity tbgCustAddressEntity;
    TbgCustAddressEntityVO tbgCustAddressEntityVO;
    ArrayList oracleTbgCustAddressEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tbgCustAddressEntity = new TbgCustAddressEntity();
        tbgCustAddressEntityVO = ( TbgCustAddressEntityVO ) tbgCustAddressEntity.getData();

        tbgCustAddressEntityVO.setCustNbr( new Long(
                                                     resultSet_.getString( this.C_CUST_NBR ) ) );
        tbgCustAddressEntityVO.setAddrCityText( resultSet_.getString( this.C_ADDR_CITY_TEXT ) );
        tbgCustAddressEntityVO.setAddrCntryCode( resultSet_.getString( this.C_ADDR_CNTRY_CODE ) );
        tbgCustAddressEntityVO.setAddrNameText( resultSet_.getString( this.C_ADDR_NAME_TEXT ) );
        tbgCustAddressEntityVO.setAddrNeighbText( resultSet_.getString( this.C_ADDR_NEIGHB_TEXT ) );
        tbgCustAddressEntityVO.setAddrSeqNbr( resultSet_.getString( this.C_ADDR_SEQ_NBR ) );
        tbgCustAddressEntityVO.setAddrStateCode( resultSet_.getString( this.C_ADDR_STATE_CODE ) );
        tbgCustAddressEntityVO.setAddrTypeCode( resultSet_.getString( this.C_ADDR_TYPE_CODE ) );
        tbgCustAddressEntityVO.setFaxAreaCode( resultSet_.getString( this.C_FAX_AREA_CODE ) );
        tbgCustAddressEntityVO.setFaxNbr( resultSet_.getString( this.C_FAX_NBR ) );
        tbgCustAddressEntityVO.setMailBoxNbr( resultSet_.getString( this.C_MAIL_BOX_NBR ) );
        tbgCustAddressEntityVO.setPhoneAreaCode( resultSet_.getString( this.C_PHONE_AREA_CODE ) );
        tbgCustAddressEntityVO.setPhoneExtnNbr( resultSet_.getString( this.C_PHONE_EXTN_NBR ) );
        tbgCustAddressEntityVO.setPhoneNbr( resultSet_.getString( this.C_PHONE_NBR ) );
        tbgCustAddressEntityVO.setPhoneOpCode( resultSet_.getString( this.C_PHONE_OP_CODE ) );
        tbgCustAddressEntityVO.setTelexAreaCode( resultSet_.getString( this.C_TELEX_AREA_CODE ) );
        tbgCustAddressEntityVO.setTelexNbr( resultSet_.getString( this.C_TELEX_NBR ) );
        tbgCustAddressEntityVO.setZipCode( resultSet_.getString( this.C_ZIP_CODE ) );
        tbgCustAddressEntityVO.setZipCodeChangeText( resultSet_.getString( this.C_ZIP_CODE_CHANGE_TEXT ) );
        tbgCustAddressEntityVO.setZipExtnCode( resultSet_.getString( this.C_ZIP_EXTN_CODE ) );

        oracleTbgCustAddressEntities.add( tbgCustAddressEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return oracleTbgCustAddressEntities;
  }
}