/*
 * Created on 02/04/2007
 *
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplPlayerRoleEntityVO;

/**
 * @author angelica.almeida
 *  
 */
public class BaseTplPlayerRoleEntity extends BaseODSEntity
{
  /**
   * Constante do tamanho do campo CNPJ do player.
   */
  public static final int C_PLYR_CNPJ_NBR_SIZE = 14;

  /**
   * Constante do tamanho do campo Tipo do Papel do Player (Administrador,
   * Gestor, Custodiante, Controlador, Auditor)
   */
  public static final int C_PLYR_ROLE_TYPE_CODE_SIZE = 3;

  /**
   * EntityVO de Player Role
   */
  protected BaseTplPlayerRoleEntityVO m_data;

  public BaseTplPlayerRoleEntity()
  {
    m_data = new BaseTplPlayerRoleEntityVO();
  }
  
  /**
   * Retorna o EntityVO do Player Role
   * @return
   */
  public BaseTplPlayerRoleEntityVO getData()
  {
    return m_data;
  }
}