package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de conta de produtos.
 */
public interface BaseTo3ProductAccountDAO
{
  /**
   * Retorna uma entidade com os campos de detalhes.
   * 
   * @param productAccountEntity_ - Entidade que possui as chaves usada para
   *          busca na tabela.
   * @return BaseTo3ProductAccountEntity - Entidade com os campos de detalhes.
   */
  public BaseTo3ProductAccountEntity find(
                                          BaseTo3ProductAccountEntity productAccountEntity_ );
                                          
  public BaseTo3ProductAccountEntity findByPK(
											  BaseTo3ProductAccountEntity productAccountEntity_ );                                          

  public ArrayList selectByPk( BigInteger prodAcctCode_,
                              BigInteger prodUnderAcctCode_ );

}