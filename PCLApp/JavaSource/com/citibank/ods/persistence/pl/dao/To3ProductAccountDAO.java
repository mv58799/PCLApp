package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.To3ProductAccountEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de conta de produtos.
 */
public interface To3ProductAccountDAO extends BaseTo3ProductAccountDAO
{
  /**
   * Retorna Data Set com os campos do grid da consulta em lista.
   * 
   * @param reltnNbr_ - Número do relacionamento do cliente.
   * @param custNbr_ - Número do cliente no CMS.
   * @param curAcctNbr_ - Número da conta corrente associada ao produto.
   * @param prodCode_ - Código do produto.
   * @param curAcctNbrBlank_ - Indicador de conta corrente em branco.
   * @param custNbrBlank_ - Indicador de número do cliente em branco.
   * @param reltnNbrBlank_ - Indicador de número de relacionamento em branco.
   * @return DataSet - Data Set com os campos do grid da consulta em lista.
   */
  public DataSet list( BigInteger reltnNbr_, BigInteger custNbr_,
                      BigInteger curAcctNbr_, String prodCode_,
                      String curAcctNbrBlank_, String custNbrBlank_,
                      String reltnNbrBlank_, String custFullNameText_,String orderBy_,String checkBlank_ );

  /**
   * Atualiza dados da tabela de contas de produtos.
   * 
   * @param productAccountEntity_ - Entidade com os novos valores dos campos.
   */
  public void update( BaseTo3ProductAccountEntity productAccountEntity_ );

  /**
   * Retorna Data Set com os campos do grid da consulta em lista.
   * 
   * @param reltnNbr_ - Número do relacionamento do cliente.
   * @param custNbr_ - Número do cliente no CMS.
   * @param curAcctNbr_ - Número da conta corrente associada ao produto.
   * @param prodCode_ - Código do produto.
   * @param custFullNameText_ - Nome completo do cliente.
   * @return DataSet - Data Set com os campos do grid da consulta em lista.
   */
  public DataSet list( BigInteger reltnNbr_, BigInteger custNbr_,
                      BigInteger curAcctNbr_, String custFullNameText_ );

  public boolean exists( To3ProductAccountEntity to3ProductAccountEntity_ );

  public To3ProductAccountEntity insert(
                                        To3ProductAccountEntity to3ProductAccountEntity_ );

  public boolean existsActive( To3ProductAccountEntity to3ProductAccountEntity_ )
                                                                                 throws UnexpectedException;
                                                                                 
  public BaseTo3ProductAccountEntity findByCurAcct(BaseTo3ProductAccountEntity productAccountEntity_);

}