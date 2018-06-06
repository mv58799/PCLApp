package com.citibank.ods.entity.pl;

import java.util.ArrayList;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplLogicCritEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de cr�tica l�gica. Esta entidade pode agregar v�rias
 * entidades que agregam dom�nios de cr�tica l�gica.
 */
public class BaseTplLogicCritEntity extends BaseODSEntity
{
  // O entity VO de Cr�tica L�gica.
  protected BaseTplLogicCritEntityVO m_data;

  // Lista com as entidades que agregam dom�nios de cr�tica l�gica.
  protected ArrayList m_logicCritDomainEntities;

  /**
   * Recupera o entity VO de Cr�tica L�gica.
   * 
   * @return BaseTplLoadProcessEntityVO - o entity VO de Cr�tica L�gica.
   */
  public BaseTplLogicCritEntityVO getData()
  {
    return m_data;
  }

  /**
   * Seta as entidades que agregam dom�nios de cr�tica l�gica.
   * 
   * @param logicCritDomainEntities_ - Entidades que agregam dom�nios de cr�tica
   *          l�gica.
   */
  public void setLogicCritDomainEntities( ArrayList logicCritDomainEntities_ )
  {
    m_logicCritDomainEntities = logicCritDomainEntities_;
  }

  /**
   * Recupera as entidades que agregam dom�nios de cr�tica l�gica.
   * 
   * @return ArrayList - Lista com as entidades que agregam dom�nios de cr�tica
   *         l�gica.
   */
  public ArrayList getLogicCritDomainEntities()
  {
    return m_logicCritDomainEntities;
  }
  
  /**
   *  Tamanho da coluna c�digo da cr�tica na tabela
   */
  
  public static final int C_LOGIC_CRIT_CODE_SIZE=4;
  
  
  /**
   *  Tamanho da coluna na tabela
   */
  public static final int C_LOGIC_CRIT_TEXT_SIZE=18;  
  
  /**
   *  Tamanho do conte�do do com�nio
   */
  public static final int C_DOMAIN_CNTNT_TEXT_SIZE = 25;  
  
}