package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.entity.pl.BaseTplLogicCritDomainEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de domínios de crítica lógica.
 */
public interface BaseTplLogicCritDomainDAO
{
  /**
   * Retorna uma lista com as entidades de domínio relacionadas a crítica
   * lógica.
   * 
   * @param logicCritCode_ - Código da crítica lógicas.
   * @return ArrayList - Lista com as entidades de domínio relacionadas a
   *         crítica lógica.
   */
  public ArrayList findDomainsByCriteriaPK( BigInteger logicCritCode_ );

  /**
   * Insere um domínio de crítica logica.
   * 
   * @param logicCritDomainEntity_ - A entidade com os dados a serem inseridos.
   */
  public void insert( BaseTplLogicCritDomainEntity logicCritDomainEntity_ );
  
  /**
   * Remove todos os domínios de uma determinada crítica lógica.
   * 
   * @param logicCritCode_ - Código da crítica lógica.
   */
  public void deleteAll( BigInteger logicCritCode_ );
  
  /**
   * Remove logicamente todos os domínios de uma determinada crítica lógica.
   * 
   * @param logicCritCode_ - Código da crítica lógica.
   */
  public void deleteLogicAll( BigInteger logicCritCode_ );
  
}