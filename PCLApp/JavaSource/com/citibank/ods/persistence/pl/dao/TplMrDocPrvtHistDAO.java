package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplMrDocPrvtHistEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados do hist�rico de Memo de Risco.
 */
public interface TplMrDocPrvtHistDAO extends BaseTplMrDocPrvtDAO
{
  /**
   * Insere um registro na tabela de hist�rico de Memo de Risco.
   * 
   * @param TplMrDocPrvtHistEntity mrDocPrvtHistEntity_ - Objeto com os dados a
   *          serem inseridos.
   */
  public void insert( TplMrDocPrvtHistEntity mrDocPrvtHistEntity_ );

  /**
   * Retorna Data Set com os campos do grid da consulta em lista.
   * 
   * @param mrDocPrvt_ - C�digo Documento MR
   * @param mrDocText_ - Descri��o Documento MR
   * @param mrInvstCurAcctInd_ - Indicador de conta CCI
   * @param custNbr_ - N�mero do cliente
   * @param curAcctNbr_ - N�mero da conta corrente
   * @param refDate_ - Data de refer�ncia do hist�rico
   * @return DataSet - Data Set com os campos do grid da consulta em lista.
   */
  public DataSet list( BigInteger mrDocCode_, String mrDocText_,
                      String mrInvstCurAcctInd_, BigInteger custNbr_,
                      String custName_, BigInteger curAcctNbr_, Date refDate_ );
}