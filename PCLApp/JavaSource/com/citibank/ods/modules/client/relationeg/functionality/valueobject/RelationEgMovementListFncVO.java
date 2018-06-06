/*
 * Created on Apr 15, 2007
 *
 */
package com.citibank.ods.modules.client.relationeg.functionality.valueobject;

/**
 * @author leonardo.nakada
 * 
 */
public class RelationEgMovementListFncVO extends BaseRelationEgListFncVO
{
  public static final String C_LAST_UPD_USER_ID_DESCRIPTION = "Usuário última atualização";

  private String m_lastUpdUserIdSrc;


  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }

  public void setLastUpdUserIdSrc( String lastUpdUserIdSrc_ )
  {
    m_lastUpdUserIdSrc = lastUpdUserIdSrc_;
  }
}