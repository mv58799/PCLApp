package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.BaseTplLogicCritEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de Crítica Lógica
 */
public interface BaseTplLogicCritDAO
{

  /**
   * Retorna uma entidade com os campos de detalhes.
   * 
   * @param logicCritEntity_ - Entidade que possui a chave usada para busca na
   *          tabela.
   * @return BaseTplLogicCritEntity - Entidade com os campos de detalhes.
   */
  public BaseTplLogicCritEntity find( BaseTplLogicCritEntity logicCritEntity_ );

  /**
   * Insere uma crítica e os domínios associados.
   * 
   * @param logicCritEntity_ - Entidade que possui a chave usada para busca na
   *          tabela.
   */
  public void insert( BaseTplLogicCritEntity logicCritEntity_ );

  /**
   * Altera uma crítica lógica e seus domínios associados.
   * 
   * @param logicCritEntity_ - Entidade que possui os dados a serem alterados
   */
  public void update( BaseTplLogicCritEntity logicCritEntity_ );

  /**
   * Remove todos os domínios de uma determinada crítica lógica.
   * 
   * @param logicCritEntity_ - A entidade com os dados da crítica lógica.
   */
  public void delete( BaseTplLogicCritEntity logicCritEntity_ );

  /**
   * Retorna Data Set com os campos do grid da consulta em lista.
   * 
   * @param logicCritCode_ - Código da crítica lógica.
   * @param logicCritText_ - Descrição da crítica lógica.
   * @return DataSet - Data Set com os campos do grid da consulta em lista.
   */
  public DataSet list( BigInteger logicCritCode_, String logicCritText_ );

  /**
   * Retorna próximo código de crítica lógica para inserção (código seqüencial).
   * 
   * @return BigInteger - Próximo código de crítica lógica para inserção (código
   *         seqüencial).
   */
  public Integer getNextLogicCritCode();

  /**
   * Verifica se a crítica lógica pode possuir domínios ativos.
   * 
   * @param logicCritEntity_ - A entidade com os dados da crítica lógica.
   * @return boolean - true se a crítica lógica pode possuir domínios, false
   *         caso contrário.
   */
  public boolean canHasLogicCritDom( BaseTplLogicCritEntity logicCritEntity_ );

  /**
   * Retorna uma entidade com os dados da crítica lógica procurada.
   * 
   * @param logicCritCode_ - Código da crítica lógica.
   * @return BaseTplLogicCritEntity - A entidade com os dados da crítica lógica
   *         procurada.
   */
  public BaseTplLogicCritEntity find( BigInteger logicCritCode_ );

  /**
   * Retorna uma lista de entidades de críticas lógicas associadas ao atributo.
   * 
   * @param entryInterCode_ - Código da interface de entrada.
   * @param colName_ - Nome do atributo.
   * @return ArrayList - Lista de entidades de críticas lógicas associadas ao
   *         atributo.
   */
  public ArrayList findAssociatedLogicCritByPK( BigInteger entryInterCode_,
                                               String colName_ );

  /**
   * Atualiza a tabela de relacionamento entre atributo e crítica lógica.
   * 
   * @param lastUpdDate_ - Data da última atualização
   * @param lastUpdUserId_ - Id do último usuário a fazer alteração
   * @param recStatCode_ - Estado atual do registro
   * @param logicCritCode_ - Código da crítica lógica
   * @param entryInterCode_ - Código da interface de entrada
   * @param colName_ - Nome do atributo
   */
  public void updateColLogicCrit( Date lastUpdDate_, String lastUpdUserId_,
                                 String recStatCode_,
                                 BigInteger logicCritCode_,
                                 BigInteger entryInterCode_, String colName_ );

  /**
   * Insere relacionamento entre atributo e crítica lógica.
   * 
   * @param lastUpdDate_ - Data da última atualização
   * @param lastUpdUserId_ - Id do último usuário a fazer alteração
   * @param recStatCode_ - Estado atual do registro
   * @param logicCritCode_ - Código da crítica lógica
   * @param entryInterCode_ - Código da interface de entrada
   * @param colName_ - Nome do atributo
   */
  public void insertColLogicCrit( Date lastUpdDate_, String lastUpdUserId_,
                                 String recStatCode_,
                                 BigInteger logicCritCode_,
                                 BigInteger entryInterCode_, String colName_ );

  /**
   * Verifica se uma crítica está associada a um atributo.
   * 
   * @param logicCritCode_ - Código da crítica lógica
   * @param entryInterCode_ - Código da interface de entrada
   * @param colName_ - Nome do atributo
   * @return true se a crítica está associada ao atributo, false caso contrário.
   */
  public boolean existColLogicCrit( BigInteger logicCritCode_,
                                   BigInteger entryInterCode_, String colName_ );
}