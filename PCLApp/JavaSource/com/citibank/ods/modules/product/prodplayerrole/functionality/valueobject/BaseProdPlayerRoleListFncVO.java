package com.citibank.ods.modules.product.prodplayerrole.functionality.valueobject;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package
 *      com.citibank.ods.modules.product.playerproduct.functionality.valueobject;
 * @version 1.0
 * @author atilio.l.araujo,Apr 4, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseProdPlayerRoleListFncVO extends BaseODSFncVO
{
  //CNPJ do Player.
  private String m_plyrCnpjNbrSrc = "";

  //Nome do Player.
  private String m_plyrNameSrc = "";

  // Tipo do Papel do Player (Administrador, Gestor, Custodiante, Controlador,
  // Auditor)
  private String m_plyrRoleTypeCode = "";

  // Codigo do Produto.
  private String m_prodCodeSrc = "";

  // Codigo do sistema origem do cadastro do Produto.
  private String m_sysCodeSrc = "";

  // Codigo da segmentacao do sistema origem do cadastro do Produto.
  private String m_sysSegCodeSrc = "";

  // Nome do Produto
  private String m_prodNameSrc = "";

  //Resultado da Consulta
  private DataSet results;

  private String m_clickedSearch;

  private boolean isFromSearch;

  /*
   * Domínio de tipo de papel do player
   */
  private DataSet m_prodPlayerRoleDomain;

  /*
   * Nome do papel do Player.
   */
  private String m_plyrRoleNameSrc;

  /*
   * Último usuário alteração
   */
  private String m_lastUpdUserIdSrc;

  /**
   * @return Returns plyrCnpjNbrSrc.
   */
  public String getPlyrCnpjNbrSrc()
  {
    return m_plyrCnpjNbrSrc;
  }

  /**
   * @param plyrCnpjNbrSrc_ Field plyrCnpjNbrSrc to be setted.
   */
  public void setPlyrCnpjNbrSrc( String plyrCnpjNbrSrc_ )
  {
    m_plyrCnpjNbrSrc = plyrCnpjNbrSrc_;
  }

  public String getPlyrNameSrc()
  {
    return m_plyrNameSrc;
  }

  public void setPlyrNameSrc( String plyrNameSrc_ )
  {
    m_plyrNameSrc = plyrNameSrc_;
  }

  /**
   * @return Returns plyrRoleNameSrc.
   */
  public String getPlyrRoleNameSrc()
  {
    return m_plyrRoleNameSrc;
  }

  /**
   * @param plyrRoleNameSrc_ Field plyrRoleNameSrc to be setted.
   */
  public void setPlyrRoleNameSrc( String plyrRoleNameSrc_ )
  {
    m_plyrRoleNameSrc = plyrRoleNameSrc_;
  }

  /**
   * @return Returns plyrRoleTypeCode.
   */
  public String getPlyrRoleTypeCode()
  {
    return m_plyrRoleTypeCode;
  }

  /**
   * @param plyrRoleTypeCode_ Field plyrRoleTypeCode to be setted.
   */
  public void setPlyrRoleTypeCode( String plyrRoleTypeCode_ )
  {
    m_plyrRoleTypeCode = plyrRoleTypeCode_;
  }

  /**
   * @return Returns prodCodeSrc.
   */
  public String getProdCodeSrc()
  {
    return m_prodCodeSrc;
  }

  /**
   * @param prodCodeSrc_ Field prodCodeSrc to be setted.
   */
  public void setProdCodeSrc( String prodCodeSrc_ )
  {
    m_prodCodeSrc = prodCodeSrc_;
  }

  /**
   * @return Returns prodNameSrc.
   */
  public String getProdNameSrc()
  {
    return m_prodNameSrc;
  }

  /**
   * @param prodNameSrc_ Field prodNameSrc to be setted.
   */
  public void setProdNameSrc( String prodNameSrc_ )
  {
    m_prodNameSrc = prodNameSrc_;
  }

  /**
   * @return Returns prodPlayerRoleDomain.
   */
  public DataSet getProdPlayerRoleDomain()
  {
    return m_prodPlayerRoleDomain;
  }

  /**
   * @param prodPlayerRoleDomain_ Field prodPlayerRoleDomain to be setted.
   */
  public void setProdPlayerRoleDomain( DataSet prodPlayerRoleDomain_ )
  {
    m_prodPlayerRoleDomain = prodPlayerRoleDomain_;
  }

  /**
   * @return Returns sysCodeSrc.
   */
  public String getSysCodeSrc()
  {
    return m_sysCodeSrc;
  }

  /**
   * @param sysCodeSrc_ Field sysCodeSrc to be setted.
   */
  public void setSysCodeSrc( String sysCodeSrc_ )
  {
    m_sysCodeSrc = sysCodeSrc_;
  }

  /**
   * @return Returns sysSegCodeSrc.
   */
  public String getSysSegCodeSrc()
  {
    return m_sysSegCodeSrc;
  }

  /**
   * @param sysSegCodeSrc_ Field sysSegCodeSrc to be setted.
   */
  public void setSysSegCodeSrc( String sysSegCodeSrc_ )
  {
    m_sysSegCodeSrc = sysSegCodeSrc_;
  }

  /**
   * @return Returns results.
   */
  public DataSet getResults()
  {
    return results;
  }

  /**
   * @param results_ Field results to be setted.
   */
  public void setResults( DataSet results_ )
  {
    results = results_;
  }

  /**
   * @return Returns lastUpdUserIdSrc.
   */
  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }

  /**
   * @param lastUpdUserIdSrc_ Field lastUpdUserIdSrc to be setted.
   */
  public void setLastUpdUserIdSrc( String lastUpdUserIdSrc_ )
  {
    m_lastUpdUserIdSrc = lastUpdUserIdSrc_;
  }

  public boolean isFromSearch()
  {
    return isFromSearch;
  }

  public void setFromSearch( boolean isFromSearch_ )
  {
    isFromSearch = isFromSearch_;
  }

  public String getClickedSearch()
  {
    return m_clickedSearch;
  }

  public void setClickedSearch( String clickedSearch_ )
  {
    m_clickedSearch = clickedSearch_;
  }
}