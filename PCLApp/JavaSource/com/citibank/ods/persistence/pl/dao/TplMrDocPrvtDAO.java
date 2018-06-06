package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity;
import com.citibank.ods.entity.pl.TplMrDocPrvtEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de Mem�ria de Risco.
 */
public interface TplMrDocPrvtDAO extends BaseTplMrDocPrvtDAO
{

  /**
   * Retorna Data Set com os campos do grid da consulta em lista.
   * 
   * @param mrDocPrvt_ - C�digo Documento MR
   * @param mrDocText_ - Descri��o Documento MR
   * @param mrInvstCurAcctInd_ - Indicador de conta CCI
   * @param custNbr_ - N�mero do cliente
   * @param curAcctNbr_ - N�mero da conta corrente
   * @return DataSet - Data Set com os campos do grid da consulta em lista.
   */
  public DataSet list( String custFullNameText_, String invstCurAcctNbr_,  
                       BigInteger curAcctNbr_ );

  /**
   * Recupera o pr�ximo n�mero sequencial para inser��o de uma nova MR.
   * 
   * @return Integer - O pr�ximo n�mero sequencial para inser��o de uma nova MR.
   */
  public Integer getNextMrDocCode();

  /**
   * Verifica se MR existe e est� ativo.
   * 
   * @param BaseTplMrDocPrvtEntity - Entidade com o MR a ser verificado.
   * @return true se existe e est� ativo, false caso contr�rio.
   */
  public boolean existsActive( BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_ );

  /**
   * Verifica se MR existe.
   * 
   * @param BaseTplMrDocPrvtEntity - Entidade com o MR a ser verificado.
   * @return true se existe, false caso contr�rio.
   */
  public boolean exists( BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_ );

  /**
   * Copia um registro da tabela corrente TPL_MR_DOC_PRVT para a tabela de
   * hist�rico TPL_MR_DOC_PRVT_HIST
   * 
   * @param BaseTplMrDocPrvtEntity - Entidade com as chaves do registro a ser
   *          copiado.
   * @param mrDocRefDate_ - Data de refer�ncia do hist�rico.
   */
  public void copyFromCurrentToHist(
                                    BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_,
                                    Date mrDocRefDate_ );

  /**
   * Atualiza dados da tabela de Memo de Risco.
   * 
   * @param BaseTplMrDocPrvtEntity - Entidade com os dados a serem atualizados.
   */
  public void update( TplMrDocPrvtEntity mrDocPrvtEntity_ );

  /**
   * Deleta registro da tabela de Memo de Risco.
   * 
   * @param BaseTplMrDocPrvtEntity - Entidade com as chaves do registro a ser
   *          deletado.
   */
  public void delete( TplMrDocPrvtEntity mrDocPrvtEntity_ );

  /**
   * Insere registro na tabela de Memo de Risco.
   * 
   * @param BaseTplMrDocPrvtEntity - Entidade com os dados a serem inseridos.
   */
  public void insert( TplMrDocPrvtEntity mrDocPrvtEntity_ );
  
  public boolean existsById( BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_ );

}