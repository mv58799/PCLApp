package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.BaseTplLoadProcExecRequestEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de Requerimento de Execu��o de
 * Processos de Carga.
 */
public interface BaseTplLoadProcExecRequestDAO
{
  /**
   * Atualiza dados da tabela de Requerimento de Execu��o de Processos de Carga
   * e da tabela de Processos de Carga.
   * 
   * @param loadProcExecRequestEntity_ - Entidade com os novos valores dos
   *          campos.
   */
  public void update(
                     BaseTplLoadProcExecRequestEntity loadProcExecRequestEntity_ );

  /**
   * Retorna Data Set com os campos do grid da consulta em lista.
   * 
   * @param loadProcNbr_ - C�digo do processo.
   * @param execReasText_ - Motivo da re-execu��o.
   * @param execRefDate_ - Data da re-execu��o.
   * @return DataSet - Data Set com os campos do grid da consulta em lista.
   */
  public DataSet list( BigInteger loadProcNbr_, String execReasText_,
                      Date execRefDate_ );

  /**
   * Retorna uma entidade com os campos iniciais de update.
   * 
   * @param loadProcExecRequestEntity_ - Entidade que possui a chave usada para
   *          busca na tabela.
   * @return BaseTplLoadProcExecRequestEntity - Entidade com os campos iniciais
   *         de update.
   */
  public BaseTplLoadProcExecRequestEntity find(
                                               BaseTplLoadProcExecRequestEntity loadProcExecRequestEntity_ );

  /**
   * Insere dados na tabela de Requerimento de Execu��o de Processos de Carga e
   * na tabela de Processos de Carga.
   * 
   * @param BaseTplLoadProcExecRequestEntity - Entidade que representa a tabela
   *          de Requerimento de Execu��o de Processos de Carga.
   */
  public void insert( BaseTplLoadProcExecRequestEntity loadProcExecRequestEntity );

  /**
   * Deleta dados da tabela de Requerimento de Execu��o de Processos de Carga.
   * 
   * @param loadProcNbr_ - C�digo do processo selecionado.
   * @param execRefDate_ - Data de reexecu��o do processo selecionado.
   */
  public void delete( BigInteger loadProcNbr_, Date execRefDate_ );

  /**
   * Verifica se o processo tem pelo menos uma cr�tica associada. A presen�a se
   * uma ou mais cr�ticas no processo indica que este n�o foi executado com
   * sucesso.
   * 
   * @param loadProcExecRequestEntity_ - Entidade que possui os dados para
   *          verifica��o.
   * @return true se o processo tiver pelo menos uma cr�tica associada, ou false
   *         caso contrario.
   */
  public boolean hasCriteria(
                             BaseTplLoadProcExecRequestEntity loadProcExecRequestEntity_ );
  
  /**
   * Verifica se a reexecu��o existe. 
   * 
   * @param loadProcNbr - C�digo do processo de carga.
   * @param execRefDate - Data de refer�ncia.
   * @return true se a reexecu��o existe, false caso contr�rio.
   */
  public boolean existReexecution(
                                  BigInteger loadProcNbr, Date execRefDate );
}