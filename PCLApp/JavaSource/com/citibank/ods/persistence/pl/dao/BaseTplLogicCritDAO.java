package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.BaseTplLogicCritEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de Cr�tica L�gica
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
   * Insere uma cr�tica e os dom�nios associados.
   * 
   * @param logicCritEntity_ - Entidade que possui a chave usada para busca na
   *          tabela.
   */
  public void insert( BaseTplLogicCritEntity logicCritEntity_ );

  /**
   * Altera uma cr�tica l�gica e seus dom�nios associados.
   * 
   * @param logicCritEntity_ - Entidade que possui os dados a serem alterados
   */
  public void update( BaseTplLogicCritEntity logicCritEntity_ );

  /**
   * Remove todos os dom�nios de uma determinada cr�tica l�gica.
   * 
   * @param logicCritEntity_ - A entidade com os dados da cr�tica l�gica.
   */
  public void delete( BaseTplLogicCritEntity logicCritEntity_ );

  /**
   * Retorna Data Set com os campos do grid da consulta em lista.
   * 
   * @param logicCritCode_ - C�digo da cr�tica l�gica.
   * @param logicCritText_ - Descri��o da cr�tica l�gica.
   * @return DataSet - Data Set com os campos do grid da consulta em lista.
   */
  public DataSet list( BigInteger logicCritCode_, String logicCritText_ );

  /**
   * Retorna pr�ximo c�digo de cr�tica l�gica para inser��o (c�digo seq�encial).
   * 
   * @return BigInteger - Pr�ximo c�digo de cr�tica l�gica para inser��o (c�digo
   *         seq�encial).
   */
  public Integer getNextLogicCritCode();

  /**
   * Verifica se a cr�tica l�gica pode possuir dom�nios ativos.
   * 
   * @param logicCritEntity_ - A entidade com os dados da cr�tica l�gica.
   * @return boolean - true se a cr�tica l�gica pode possuir dom�nios, false
   *         caso contr�rio.
   */
  public boolean canHasLogicCritDom( BaseTplLogicCritEntity logicCritEntity_ );

  /**
   * Retorna uma entidade com os dados da cr�tica l�gica procurada.
   * 
   * @param logicCritCode_ - C�digo da cr�tica l�gica.
   * @return BaseTplLogicCritEntity - A entidade com os dados da cr�tica l�gica
   *         procurada.
   */
  public BaseTplLogicCritEntity find( BigInteger logicCritCode_ );

  /**
   * Retorna uma lista de entidades de cr�ticas l�gicas associadas ao atributo.
   * 
   * @param entryInterCode_ - C�digo da interface de entrada.
   * @param colName_ - Nome do atributo.
   * @return ArrayList - Lista de entidades de cr�ticas l�gicas associadas ao
   *         atributo.
   */
  public ArrayList findAssociatedLogicCritByPK( BigInteger entryInterCode_,
                                               String colName_ );

  /**
   * Atualiza a tabela de relacionamento entre atributo e cr�tica l�gica.
   * 
   * @param lastUpdDate_ - Data da �ltima atualiza��o
   * @param lastUpdUserId_ - Id do �ltimo usu�rio a fazer altera��o
   * @param recStatCode_ - Estado atual do registro
   * @param logicCritCode_ - C�digo da cr�tica l�gica
   * @param entryInterCode_ - C�digo da interface de entrada
   * @param colName_ - Nome do atributo
   */
  public void updateColLogicCrit( Date lastUpdDate_, String lastUpdUserId_,
                                 String recStatCode_,
                                 BigInteger logicCritCode_,
                                 BigInteger entryInterCode_, String colName_ );

  /**
   * Insere relacionamento entre atributo e cr�tica l�gica.
   * 
   * @param lastUpdDate_ - Data da �ltima atualiza��o
   * @param lastUpdUserId_ - Id do �ltimo usu�rio a fazer altera��o
   * @param recStatCode_ - Estado atual do registro
   * @param logicCritCode_ - C�digo da cr�tica l�gica
   * @param entryInterCode_ - C�digo da interface de entrada
   * @param colName_ - Nome do atributo
   */
  public void insertColLogicCrit( Date lastUpdDate_, String lastUpdUserId_,
                                 String recStatCode_,
                                 BigInteger logicCritCode_,
                                 BigInteger entryInterCode_, String colName_ );

  /**
   * Verifica se uma cr�tica est� associada a um atributo.
   * 
   * @param logicCritCode_ - C�digo da cr�tica l�gica
   * @param entryInterCode_ - C�digo da interface de entrada
   * @param colName_ - Nome do atributo
   * @return true se a cr�tica est� associada ao atributo, false caso contr�rio.
   */
  public boolean existColLogicCrit( BigInteger logicCritCode_,
                                   BigInteger entryInterCode_, String colName_ );
}