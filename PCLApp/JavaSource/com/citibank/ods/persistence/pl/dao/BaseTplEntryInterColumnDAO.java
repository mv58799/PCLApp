package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.BaseTplEntryInterColumnEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de Interface de Entrada de Colunas.
 */
public interface BaseTplEntryInterColumnDAO
{
  /**
   * Retorna Data Set com os campos do grid da consulta em lista.
   * 
   * @param entryInterText_ - Descricao da interface de entrada.
   * @param colName_ - Nome do atributo.
   * @param colText_ - Descrição do atributo.
   * @return DataSet - DataSet com os campos do grid da consulta em lista.
   */
  public DataSet list( String entryInterText_, String colName_,
                      String colText_ );

  /**
   * Retorna o DataSet com os valores existentes de código do Tipo de Dados da
   * Coluna para popular o combo.
   * 
   * @return DataSet - DataSet com os valores existentes de código do Tipo de
   *         Dados da Coluna para popular o combo.
   */
  public DataSet loadColDataTypeCodeDomain();

  /**
   * Retorna o DataSet com os valores existentes de código de tipo de Interface
   * de Entrada.
   * 
   * @return DataSet - DataSet com os valores existentes de código de tipo de
   *         Interface de Entrada.
   */
  public DataSet loadEntryTypeCodeDomain();

  /**
   * Retorna o DataSet com os valores existentes de código de Interface de
   * Entrada.
   * 
   * @return DataSet - DataSet com os valores existentes de código de Interface
   *         de Entrada.
   */
  public DataSet loadEntryInterCodeDomain();
  
  /**
   * Retorna entidade com os valores da tela de detalhe.
   * 
   * @param entryInterCode_ - Código da interface de entrada.
   * @param colName_ - Nome do atributo.
   * 
   * @return BaseTplEntryInterEntity - Entidade com os valores da tela de
   *         detalhe.
   */
  public BaseTplEntryInterColumnEntity find( BigInteger entryInterCode_,
                                            String colName_ );
}