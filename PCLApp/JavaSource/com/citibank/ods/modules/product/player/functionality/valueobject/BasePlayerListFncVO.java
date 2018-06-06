/*
 * Created on Mar 29, 2007
 *
 */
package com.citibank.ods.modules.product.player.functionality.valueobject;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

/**
 * @author angelica.almeida
 *  
 */
public class BasePlayerListFncVO extends BaseODSFncVO
{
  
  public static final String C_PLYR_CNPJ_NBR_DESCRIPTION = "CNPJ";

  public static final String C_PLYR_NAME_DESCRIPTION = "Razão Social Player";
  
  /**
   * CNPJ do Player
   */
  private String m_plyrCnpjNbrScr;

  /**
   * Nome do Player
   */
  private String m_plyrNameScr;

  /**
   * Código do papel do player
   */
  private String m_plyrRoleTypeCodeScr;

  /**
   * Domínio de tipo de papel do player
   */
  private DataSet m_playerRoleTypeDomain;

  /**
   * Resultado da consulta
   */
  private DataSet m_results;

  /**
   * @return Returns plyrCnpjNbrScr.
   */
  public String getPlyrCnpjNbrScr()
  {
    return m_plyrCnpjNbrScr;
  }

  /**
   * @param plyrCnpjNbrScr_ Field plyrCnpjNbrScr to be setted.
   */
  public void setPlyrCnpjNbrScr( String plyrCnpjNbrScr_ )
  {
    m_plyrCnpjNbrScr = plyrCnpjNbrScr_;
  }

  /**
   * @return Returns plyrNameScr.
   */
  public String getPlyrNameScr()
  {
    return m_plyrNameScr;
  }

  /**
   * @param plyrNameScr_ Field plyrNameScr to be setted.
   */
  public void setPlyrNameScr( String plyrNameScr_ )
  {
    m_plyrNameScr = plyrNameScr_;
  }

  /**
   * @return Returns playerRoleTypeDomain.
   */
  public DataSet getPlayerRoleTypeDomain()
  {
    return m_playerRoleTypeDomain;
  }

  public String getPlyrRoleTypeCodeScr()
  {
    return m_plyrRoleTypeCodeScr;
  }

  public void setPlyrRoleTypeCodeScr( String plyrRoleTypeCodeScr_ )
  {
    m_plyrRoleTypeCodeScr = plyrRoleTypeCodeScr_;
  }

  /**
   * @param playerRoleTypeDomain__ Field playerRoleTypeDomain to be setted.
   */
  public void setPlayerRoleTypeDomain( DataSet playerRoleTypeDomain_ )
  {
    m_playerRoleTypeDomain = playerRoleTypeDomain_;
  }

  /**
   * @return Returns results.
   */
  public DataSet getResults()
  {
    return m_results;
  }

  /**
   * @param results_ Field results to be setted.
   */
  public void setResults( DataSet results_ )
  {
    m_results = results_;
  }
}