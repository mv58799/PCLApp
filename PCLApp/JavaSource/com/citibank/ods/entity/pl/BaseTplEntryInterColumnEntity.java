package com.citibank.ods.entity.pl;

import java.util.Map;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplEntryInterColumnEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de Interface de Entrada de atributos.
 */
public class BaseTplEntryInterColumnEntity extends BaseODSEntity
{

  // O entity VO de Interface de Entrada.
  protected BaseTplEntryInterColumnEntityVO m_data;

  // Mapa das entidades de crítica lógica.
  protected Map m_tplLogicCritEntities = null;

  /**
   * Recupera o entity VO de Interface de Entrada de atributos.
   * 
   * @return BaseTplEntryInterColumnEntityVO - o entity VO de Interface de
   *         Entrada de atributos.
   */
  public BaseTplEntryInterColumnEntityVO getData()
  {
    return m_data;
  }

  /**
   * Recupera o mapa das entidades de crítica lógica.
   * 
   * @return Map - O mapa das entidades de crítica lógica.
   */
  public Map getTplLogicCritEntities()
  {
    return m_tplLogicCritEntities;
  }

  /**
   * Seta o mapa das entidades de crítica lógica.
   * 
   * @param tplLogicCritEntities_ - O mapa das entidades de crítica lógica.
   */
  public void setTplLogicCritEntities( Map tplLogicCritEntities_ )
  {
    m_tplLogicCritEntities = tplLogicCritEntities_;
  }

  /**
   * 
   * Tamanho da coluna nome do atributo
   */

  public static final int C_COL_NAME_SIZE = 20;

  /**
   * 
   * Tamanho da coluna descrição do atributo
   */

  public static final int C_COL_TEXT_SIZE = 50;

}