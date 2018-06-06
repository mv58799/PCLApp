/*
 * Created on Mar 19, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplProductFamilyPrvtEntity;
import com.citibank.ods.entity.pl.TplProductFamilyPrvtHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplProductFamilyPrvtHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProductFamilyPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author leonardo.nakada
 *
 */
public class OracleTplProductFamilyPrvtHistDAO extends
    BaseOracleTplProductFamilyPrvtDAO implements TplProductFamilyPrvtHistDAO
{
  /*
   * Campos específicos da tabela
   */
  private static final String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private static final String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private static final String C_REC_STAT_CODE = "REC_STAT_CODE";

  private String C_PROD_FAML_REF_DATE = "PROD_FAML_REF_DATE";

  /*
   * Nome da tabela
   */
  private static final String C_TABLE_NAME = C_PL_SCHEMA + "TPL_PRODUCT_FAMILY_PRVT_HIST";

  /**
   * Realiza a consulta das família de produtos que estão no histórico
   */
  public DataSet list( BigInteger prodFamlCode_, String prodFamlName_,
                      String prodFamlText_, Date m_prodFamlRefDate_ )
  {
    //Este item não será implementado nesta fase.
    return null;
  }

  /**
   * Realiza a inclusão de um registro de família de produtos no histórico
   */
  public TplProductFamilyPrvtHistEntity insert(
                                               TplProductFamilyPrvtHistEntity familyPrvtHistEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TABLE_NAME + " (" );
      query.append( C_PROD_FAML_CODE + ", " );
      query.append( C_PROD_FAML_REF_DATE + ", " );
      query.append( C_PROD_FAML_NAME + ", " );
      query.append( C_PROD_FAML_TEXT + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ?, ? )" );
      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( familyPrvtHistEntity_.getData().getProdFamlCode() != null )
      {
        preparedStatement.setLong(
                          count++,
                          familyPrvtHistEntity_.getData().getProdFamlCode().longValue() );
      }
      
      TplProductFamilyPrvtHistEntityVO tplProductFamilyPrvtHistEntityVO = (TplProductFamilyPrvtHistEntityVO) familyPrvtHistEntity_.getData();
      
      if ( (tplProductFamilyPrvtHistEntityVO.getProdFamlRefDate() != null ))
      {
        preparedStatement.setTimestamp( count++,
                           (new Timestamp(tplProductFamilyPrvtHistEntityVO.getProdFamlRefDate().getTime()) ));
      }else {
    	  preparedStatement.setNull(count++, java.sql.Types.DATE);  
      }

      
      if ( familyPrvtHistEntity_.getData().getProdFamlName() != null )
      {
        preparedStatement.setString( count++,
                             familyPrvtHistEntity_.getData().getProdFamlName() );
      }else {
    	  preparedStatement.setNull(count++, java.sql.Types.VARCHAR);  
      }


      if ( familyPrvtHistEntity_.getData().getProdFamlText() != null )
      {
        preparedStatement.setString( count++,
                             familyPrvtHistEntity_.getData().getProdFamlText() );
      }else {
    	  preparedStatement.setNull(count++, java.sql.Types.VARCHAR);  
      }


      preparedStatement.setTimestamp(
                              count++,
                              new Timestamp(
                                             familyPrvtHistEntity_.getData().getLastUpdDate().getTime() ) );

      if ( familyPrvtHistEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             familyPrvtHistEntity_.getData().getLastUpdUserId() );
      }else {
    	  preparedStatement.setNull(count++, java.sql.Types.VARCHAR);  
      }


      TplProductFamilyPrvtHistEntityVO familyPrvtHistEntityVO = ( TplProductFamilyPrvtHistEntityVO ) familyPrvtHistEntity_.getData();

      if ( familyPrvtHistEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               familyPrvtHistEntityVO.getLastAuthDate().getTime() ) );
      }

      if ( familyPrvtHistEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++,
                             familyPrvtHistEntityVO.getLastAuthUserId() );
      }

      if ( familyPrvtHistEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, familyPrvtHistEntityVO.getRecStatCode() );
      }else {
    	  preparedStatement.setNull(count++, java.sql.Types.VARCHAR);  
      }


      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());
	  

      return familyPrvtHistEntity_;

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
   * Recupera um registro de histórico a partir
   * do código passado
   */
  public BaseTplProductFamilyPrvtEntity find(
                                             BaseTplProductFamilyPrvtEntity baseTplProductFamilyPrvtEntity_ )
  {

    return null;
  }

}