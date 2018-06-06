package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.BaseTplLoadProcExecEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de Execução de Processos de Carga.
 */
public interface BaseTplLoadProcExecDAO
{

  /**
   * Retorna uma entidade com os campos de detalhes.
   * 
   * @param loadProcExecEntity_ - Entidade que possui a chave usada para busca
   *          na tabela.
   * @return BaseTplLoadProcExecEntity - Entidade com os campos de detalhes.
   */
  public BaseTplLoadProcExecEntity find(
                                        BaseTplLoadProcExecEntity loadProcExecEntity_ );

  /**
   * Retorna Data Set com os campos do grid da consulta em lista.
   * 
   * @param loadProcNbr - Código do processo.
   * @param loadProcText - Descrição do processo.
   * @param execRefDate - Data de refência de execução do processo de carga.
   * @return DataSet - Data Set com os campos do grid da consulta em lista.
   */
  public DataSet list( BigInteger loadProcNbr_, String loadProcText_,
                      Date execRefDate_ );

  /**
   * Recupera a descrição de um processo.
   * 
   * @param loadProcNbr_ - O código do processo
   * @return String - Retorna as descrição do processo.
   */
  public String findLoadProcText( BigInteger loadProcNbr_ );

  /**
   * Retorna Data Set com os campos do grid do histórico de execuções.
   * 
   * @param loadProcNbr - Código do processo.
   * @param execSeqNbr_ - Número de referência de execução do processo.
   * @param execRefDate - Data de refência de execução do processo de carga.
   * @return DataSet - Data Set com os campos do grid do histórico de execuções.
   */
  public DataSet listLogicCriteria( BigInteger loadProcNbr_,
                                   BigInteger execSeqNbr_, Date execRefDate_ );
}