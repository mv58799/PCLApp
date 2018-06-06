package com.citibank.ods.persistence.pl.dao;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de domínios de crítica lógica.
 */
public interface TplLogicCritDomainDAO extends BaseTplLogicCritDomainDAO
{
  /**
   * Retorna a sequence do domínio da crítica lógica na tela de inserção de
   * crítica lógica.
   */
  public Integer getNextLogicCritDomain();

}