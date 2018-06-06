package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.BaseTplLoadProcExecEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de Execu��o de Processos de Carga.
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
   * @param loadProcNbr - C�digo do processo.
   * @param loadProcText - Descri��o do processo.
   * @param execRefDate - Data de ref�ncia de execu��o do processo de carga.
   * @return DataSet - Data Set com os campos do grid da consulta em lista.
   */
  public DataSet list( BigInteger loadProcNbr_, String loadProcText_,
                      Date execRefDate_ );

  /**
   * Recupera a descri��o de um processo.
   * 
   * @param loadProcNbr_ - O c�digo do processo
   * @return String - Retorna as descri��o do processo.
   */
  public String findLoadProcText( BigInteger loadProcNbr_ );

  /**
   * Retorna Data Set com os campos do grid do hist�rico de execu��es.
   * 
   * @param loadProcNbr - C�digo do processo.
   * @param execSeqNbr_ - N�mero de refer�ncia de execu��o do processo.
   * @param execRefDate - Data de ref�ncia de execu��o do processo de carga.
   * @return DataSet - Data Set com os campos do grid do hist�rico de execu��es.
   */
  public DataSet listLogicCriteria( BigInteger loadProcNbr_,
                                   BigInteger execSeqNbr_, Date execRefDate_ );
}