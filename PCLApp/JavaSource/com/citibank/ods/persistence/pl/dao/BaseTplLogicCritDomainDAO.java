package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.entity.pl.BaseTplLogicCritDomainEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de dom�nios de cr�tica l�gica.
 */
public interface BaseTplLogicCritDomainDAO
{
  /**
   * Retorna uma lista com as entidades de dom�nio relacionadas a cr�tica
   * l�gica.
   * 
   * @param logicCritCode_ - C�digo da cr�tica l�gicas.
   * @return ArrayList - Lista com as entidades de dom�nio relacionadas a
   *         cr�tica l�gica.
   */
  public ArrayList findDomainsByCriteriaPK( BigInteger logicCritCode_ );

  /**
   * Insere um dom�nio de cr�tica logica.
   * 
   * @param logicCritDomainEntity_ - A entidade com os dados a serem inseridos.
   */
  public void insert( BaseTplLogicCritDomainEntity logicCritDomainEntity_ );
  
  /**
   * Remove todos os dom�nios de uma determinada cr�tica l�gica.
   * 
   * @param logicCritCode_ - C�digo da cr�tica l�gica.
   */
  public void deleteAll( BigInteger logicCritCode_ );
  
  /**
   * Remove logicamente todos os dom�nios de uma determinada cr�tica l�gica.
   * 
   * @param logicCritCode_ - C�digo da cr�tica l�gica.
   */
  public void deleteLogicAll( BigInteger logicCritCode_ );
  
}