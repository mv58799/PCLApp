package com.citibank.ods.persistence.pl.dao;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de dom�nios de cr�tica l�gica.
 */
public interface TplLogicCritDomainDAO extends BaseTplLogicCritDomainDAO
{
  /**
   * Retorna a sequence do dom�nio da cr�tica l�gica na tela de inser��o de
   * cr�tica l�gica.
   */
  public Integer getNextLogicCritDomain();

}