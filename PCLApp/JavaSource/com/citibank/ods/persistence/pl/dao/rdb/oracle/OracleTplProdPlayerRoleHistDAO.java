package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.pl.TplProdPlayerRoleHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdPlayerRoleHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @author acacio.domingos,Apr 12, 2007
 *  
 */

public class OracleTplProdPlayerRoleHistDAO extends
    BaseOracleTplProdPlayerRoleDAO implements TplProdPlayerRoleHistDAO
{

  /*
   * Constante que identifica o nome da tabela de histórico
   */
  private static final String C_TPL_PROD_PLAYER_ROLE_HIST = C_PL_SCHEMA + "TPL_PROD_PLAYER_ROLE_HIST";

  /*
   * Campos específicos da tabela
   */
  private String C_PROD_PLYR_REF_DATE = "PROD_PLYR_REF_DATE";

  private String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private String C_REC_STAT_CODE = "REC_STAT_CODE";

  /**
   * Realiza a consulta de registros de acordo com um filtro pré-determinado.
   * @see com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleHistDAO#list(java.lang.String,
   *      java.lang.String, java.util.Date)
   */
  public DataSet list( String plyrCnpjNbr_, String plyrName_, Date plyrRefDate_ )
  {

    return null;
  }

  /**
   * Insere um registro
   * @see com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleHistDAO#insert(com.citibank.ods.entity.pl.TplProdPlayerRoleHistEntity)
   */
  public TplProdPlayerRoleHistEntity insert(
                                            TplProdPlayerRoleHistEntity tplProdPlayerRoleHistEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();
    
    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_PROD_PLAYER_ROLE_HIST + " (" );
      query.append( C_PROD_CODE + ", " );
      query.append( C_SYS_CODE + ", " );
      query.append( C_SYS_SEG_CODE + ", " );
      query.append( C_PLYR_CNPJ_NBR + ", " );
      query.append( C_PLYR_ROLE_TYPE_CODE + ", " );
      query.append( C_PROD_PLYR_REF_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " ) VALUES ( " );
      query.append( " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplProdPlayerRoleHistEntity_.getData().getProdCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdPlayerRoleHistEntity_.getData().getProdCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplProdPlayerRoleHistEntity_.getData().getSysCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdPlayerRoleHistEntity_.getData().getSysCode() );
      }
      else
      {
        preparedStatement.setString(
                             count++,
                             tplProdPlayerRoleHistEntity_.getData().getSysCode() );
      }
      if ( tplProdPlayerRoleHistEntity_.getData().getSysSegCode() != null )
      {
        preparedStatement.setLong(
                          count++,
                          tplProdPlayerRoleHistEntity_.getData().getSysSegCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplProdPlayerRoleHistEntity_.getData().getPlyrCnpjNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdPlayerRoleHistEntity_.getData().getPlyrCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplProdPlayerRoleHistEntity_.getData().getPlyrRoleTypeCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdPlayerRoleHistEntity_.getData().getPlyrRoleTypeCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      //   	Casting para a atribuicao das colunas especificas
      TplProdPlayerRoleHistEntityVO tplProdPlayerRoleHistEntityVO = ( TplProdPlayerRoleHistEntityVO ) tplProdPlayerRoleHistEntity_.getData();
      if ( tplProdPlayerRoleHistEntityVO.getprodPlyrRefDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProdPlayerRoleHistEntityVO.getprodPlyrRefDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }
      if ( tplProdPlayerRoleHistEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdPlayerRoleHistEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplProdPlayerRoleHistEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProdPlayerRoleHistEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }
      if ( tplProdPlayerRoleHistEntityVO.getlastAuthUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplProdPlayerRoleHistEntityVO.getlastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplProdPlayerRoleHistEntityVO.getlastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProdPlayerRoleHistEntityVO.getlastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }
      if ( tplProdPlayerRoleHistEntityVO.getrecStatCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProdPlayerRoleHistEntityVO.getrecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
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

    return tplProdPlayerRoleHistEntity_;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTplProdPlayerRoleDAO#selectByPlyr(java.lang.String)
   */
  public ArrayList selectByPlyr( String plyrCnpjNbr_ )
  {
    return null;
  }

}