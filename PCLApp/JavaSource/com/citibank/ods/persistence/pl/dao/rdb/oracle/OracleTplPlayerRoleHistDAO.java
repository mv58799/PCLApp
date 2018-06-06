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
import com.citibank.ods.entity.pl.BaseTplPlayerRoleEntity;
import com.citibank.ods.entity.pl.TplPlayerRoleHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplPlayerRoleHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplPlayerRoleHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author acacio.domingos,Apr 9, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class OracleTplPlayerRoleHistDAO extends BaseOracleTplPlayerRoleDAO
    implements TplPlayerRoleHistDAO
{
  
  /*
   * Constante que identifica o nome da tabela de histórico
   */
  private static final String C_TPL_PLAYER_ROLE_HIST = C_PL_SCHEMA + "TPL_PLAYER_ROLE_HIST";
  
  /*
   * Campos específicos da tabela
   */
  private String C_PLYR_ROLE_REF_DATE = "PLYR_ROLE_REF_DATE";
  
  private String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";
  
  private String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";
  
  private String C_REC_STAT_CODE = "REC_STAT_CODE";
  
  /**
   * Realiza a consulta de registros de acordo com um filtro pré-determinado.
   * @see com.citibank.ods.persistence.pl.dao.TplPlayerRoleHistDAO#list(java.lang.String,
   *      java.lang.String, java.util.Date)
   */
  public DataSet list( String plyrCnpjNbr_, String plyrName_, Date plyrRefDate_ )
  {
    return null;
  }

  /**
   * Insere um registro de historio de PlayerRole
   * @see com.citibank.ods.persistence.pl.dao.TplPlayerRoleHistDAO#insert(com.citibank.ods.entity.pl.TplPlayerRoleHistEntity)
   */
  public TplPlayerRoleHistEntity insert(
                                        TplPlayerRoleHistEntity tplPlayerRoleHistEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();
   
    try
    {
      connection = OracleODSDAOFactory.getConnection(); 
      query.append( "INSERT INTO " + C_TPL_PLAYER_ROLE_HIST + " (" );
      query.append( C_PLYR_CNPJ_NBR + ", " );
      query.append( C_PLYR_ROLE_TYPE_CODE + ", " );
      query.append( C_PLYR_ROLE_REF_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE  );
      query.append( " ) VALUES ( ");
      query.append( "?, ?, ?, ?, ?, ?, ?, ? )");
      
      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;
      
      /*
       * Casting para obter os campos especificos da tabela
       */
      
      TplPlayerRoleHistEntityVO tplPlayerRoleHistEntityVO = ( TplPlayerRoleHistEntityVO ) tplPlayerRoleHistEntity_.getData();
      
      if ( tplPlayerRoleHistEntity_.getData().getPlyrCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerRoleHistEntity_.getData().getPlyrCnpjNbr() );
        
      }
      else
      {
        preparedStatement.setString ( count++,
                              null );
      }
      if ( tplPlayerRoleHistEntity_.getData().getPlyrRoleTypeCode() != null )
      {
        preparedStatement.setString( count++ ,
                             tplPlayerRoleHistEntity_.getData().getPlyrRoleTypeCode() );
      }
      else
      {
        preparedStatement.setString ( count++,
                              null );
      }
      if  ( tplPlayerRoleHistEntityVO.getPlyrRoleRefDate()  != null )
      {
        preparedStatement.setTimestamp( count++ ,
                                new Timestamp( tplPlayerRoleHistEntityVO.getPlyrRoleRefDate().getTime() ));
      }
      else
      {
        preparedStatement.setTimestamp ( count++,
                                 null );
      }
      if ( tplPlayerRoleHistEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerRoleHistEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++,
                             null );
      }
      if ( tplPlayerRoleHistEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp( count++,
                                new Timestamp (
                                               tplPlayerRoleHistEntity_.getData().getLastUpdDate().getTime() ));
      }
      else
      {
        preparedStatement.setTimestamp ( count++,
                                 null );
      }
      if ( tplPlayerRoleHistEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp ( count++,
                                 new Timestamp (
                                                tplPlayerRoleHistEntityVO.getLastAuthDate().getTime() ));
      }
      else
      {
        preparedStatement.setTimestamp ( count++ ,
                                 null );
      }
      if ( tplPlayerRoleHistEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerRoleHistEntityVO.getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++ ,
                             null );
      }
      if ( tplPlayerRoleHistEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerRoleHistEntityVO.getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++,
                             null );
      }
      
      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());
      
      return null;
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
   * Recupera um Role conforme a chave determinada
   * @see com.citibank.ods.persistence.pl.dao.BaseTplPlayerRoleDAO#find(com.citibank.ods.entity.pl.BaseTplPlayerRoleEntity)
   */
  public BaseTplPlayerRoleEntity find(
                                      BaseTplPlayerRoleEntity baseTplPlayerRoleEntity_ )
  {

    return null;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTplPlayerRoleDAO#selectByPk(java.lang.String)
   */
  public ArrayList selectByPk( String plyrCnpjNbr_ )
  {
     return null;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTplPlayerRoleDAO#selectByPlyr(java.lang.String)
   */
  public DataSet selectByPlyr( String plyrCnpjNbr_ )
  {
    return null;
  }

}