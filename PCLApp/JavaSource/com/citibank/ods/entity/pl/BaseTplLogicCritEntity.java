package com.citibank.ods.entity.pl;

import java.util.ArrayList;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplLogicCritEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de crítica lógica. Esta entidade pode agregar várias
 * entidades que agregam domínios de crítica lógica.
 */
public class BaseTplLogicCritEntity extends BaseODSEntity
{
  // O entity VO de Crítica Lógica.
  protected BaseTplLogicCritEntityVO m_data;

  // Lista com as entidades que agregam domínios de crítica lógica.
  protected ArrayList m_logicCritDomainEntities;

  /**
   * Recupera o entity VO de Crítica Lógica.
   * 
   * @return BaseTplLoadProcessEntityVO - o entity VO de Crítica Lógica.
   */
  public BaseTplLogicCritEntityVO getData()
  {
    return m_data;
  }

  /**
   * Seta as entidades que agregam domínios de crítica lógica.
   * 
   * @param logicCritDomainEntities_ - Entidades que agregam domínios de crítica
   *          lógica.
   */
  public void setLogicCritDomainEntities( ArrayList logicCritDomainEntities_ )
  {
    m_logicCritDomainEntities = logicCritDomainEntities_;
  }

  /**
   * Recupera as entidades que agregam domínios de crítica lógica.
   * 
   * @return ArrayList - Lista com as entidades que agregam domínios de crítica
   *         lógica.
   */
  public ArrayList getLogicCritDomainEntities()
  {
    return m_logicCritDomainEntities;
  }
  
  /**
   *  Tamanho da coluna código da crítica na tabela
   */
  
  public static final int C_LOGIC_CRIT_CODE_SIZE=4;
  
  
  /**
   *  Tamanho da coluna na tabela
   */
  public static final int C_LOGIC_CRIT_TEXT_SIZE=18;  
  
  /**
   *  Tamanho do conteúdo do comínio
   */
  public static final int C_DOMAIN_CNTNT_TEXT_SIZE = 25;  
  
}