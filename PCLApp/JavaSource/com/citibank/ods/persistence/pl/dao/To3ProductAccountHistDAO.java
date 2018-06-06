package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.To3ProductAccountHistEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de hist�rico de conta de produtos.
 */
public interface To3ProductAccountHistDAO extends BaseTo3ProductAccountDAO
{
  /**
   * Retorna Data Set com os campos do grid da consulta em lista de hist�rico.
   * 
   * @param reltnNbr_ - N�mero do relacionamento do cliente.
   * @param custNbr_ - N�mero do cliente no CMS.
   * @param curAcctNbr_ - N�mero da conta corrente associada ao produto.
   * @param prodCode_ - C�digo do produto.
   * @param prodAcctRefDate_ - Data de refer�ncia do registro no hist�rico;
   * @param custFullNameText_ - Nome do cliente;
   * @return DataSet - Data Set com os campos do grid da consulta em lista.
   */
  public DataSet list( BigInteger reltnNbr_, BigInteger custNbr_,
                      BigInteger curAcctNbr, String prodCode,
                      Date prodAcctRefDate_, String custFullNameText_ );

  /**
   * Insere dados da tabela de hist�rico de contas de produtos.
   * 
   * @param productAccountEntity_ - Entidade com os novos valores dos campos.
   */
  public void insert( To3ProductAccountHistEntity productAccountHistEntity_ );
}