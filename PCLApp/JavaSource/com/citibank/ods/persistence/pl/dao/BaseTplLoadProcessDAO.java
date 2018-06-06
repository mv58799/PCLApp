package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.BaseTplLoadProcessEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de Processos de Carga.
 */
public interface BaseTplLoadProcessDAO
{

  /**
   * Retorna uma entidade com os campos de detalhes.
   * 
   * @param loadProcessEntity_ - Entidade que possui a chave usada para busca na
   *          tabela.
   * @return BaseTplLoadProcessEntity - Entidade com os campos de detalhes.
   */
  public BaseTplLoadProcessEntity find(
                                       BaseTplLoadProcessEntity loadProcessEntity_ );

  /**
   * Retorna Data Set com os campos do grid da consulta em lista.
   * 
   * @param loadProcNbr - Código do processo.
   * @param loadProcText - Descrição do processo.
   * @param execRefDate - Data de refência de execução do processo de carga.
   * @param reexecuitonInd - Indicador de re-execução
   * @return DataSet - Data Set com os campos do grid da consulta em lista.
   */
  public DataSet list( BigInteger loadProcNbr, String loadProcText,
                      Date execRefDate, String reexecutionInd_,Date lastExecDate_, String sysCode_  );
  
  /**
   * Retorna Data Set com os campos do grid de interface.
   * 
   * @param loadProcNbr - Código do processo.
   * @return DataSet - Data Set com os campos da tabela de interface.
   */
  public DataSet listInterfaceByProcess( BigInteger loadProcNbr);

}