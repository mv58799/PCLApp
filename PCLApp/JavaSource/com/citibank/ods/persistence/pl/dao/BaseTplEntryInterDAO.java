package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.BaseTplEntryInterColumnEntity;
import com.citibank.ods.entity.pl.BaseTplEntryInterEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de Interface de Entrada.
 */
public interface BaseTplEntryInterDAO
{
  /**
   * Retorna Data Set com os campos do grid da consulta em lista.
   * 
   * @param entryInterCode_ - Código da interface de entrada.
   * @return DataSet - DataSet com os campos do grid da consulta em lista.
   */
  public DataSet list( BigInteger entryInterCode_ );

  /**
   * Retorna o DataSet com os valores existentes de código de tipo de Interface
   * de Entrada.
   * 
   * @return DataSet - DataSet com os valores existentes de código de tipo de
   *         Interface de Entrada.
   */
  public DataSet loadEntryTypeCodeDomain();
  
  /**
   * Retorna entidade com os valores da tela de detalhe.
   * 
   * @param entryInterCode_ - Código da interface de entrada.
   * @param entryTypeCode_ - Código do typo de atributo.
   * @param entryInterColumnEntity_ - Entidade com os dados do atributo.
   * 
   * @return BaseTplEntryInterEntity - Entidade com os valores da tela de
   *         detalhe.
   */
  public BaseTplEntryInterEntity find(
                                      BigInteger entryInterCode_,
                                      BigInteger entryTypeCode_,
                                      BaseTplEntryInterColumnEntity entryInterColumnEntity_ );
}