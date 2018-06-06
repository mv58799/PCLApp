package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity;
import com.citibank.ods.entity.pl.TplMrDocPrvtEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de Memória de Risco.
 */
public interface TplMrDocPrvtDAO extends BaseTplMrDocPrvtDAO
{

  /**
   * Retorna Data Set com os campos do grid da consulta em lista.
   * 
   * @param mrDocPrvt_ - Código Documento MR
   * @param mrDocText_ - Descrição Documento MR
   * @param mrInvstCurAcctInd_ - Indicador de conta CCI
   * @param custNbr_ - Número do cliente
   * @param curAcctNbr_ - Número da conta corrente
   * @return DataSet - Data Set com os campos do grid da consulta em lista.
   */
  public DataSet list( String custFullNameText_, String invstCurAcctNbr_,  
                       BigInteger curAcctNbr_ );

  /**
   * Recupera o próximo número sequencial para inserção de uma nova MR.
   * 
   * @return Integer - O próximo número sequencial para inserção de uma nova MR.
   */
  public Integer getNextMrDocCode();

  /**
   * Verifica se MR existe e está ativo.
   * 
   * @param BaseTplMrDocPrvtEntity - Entidade com o MR a ser verificado.
   * @return true se existe e está ativo, false caso contrário.
   */
  public boolean existsActive( BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_ );

  /**
   * Verifica se MR existe.
   * 
   * @param BaseTplMrDocPrvtEntity - Entidade com o MR a ser verificado.
   * @return true se existe, false caso contrário.
   */
  public boolean exists( BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_ );

  /**
   * Copia um registro da tabela corrente TPL_MR_DOC_PRVT para a tabela de
   * histórico TPL_MR_DOC_PRVT_HIST
   * 
   * @param BaseTplMrDocPrvtEntity - Entidade com as chaves do registro a ser
   *          copiado.
   * @param mrDocRefDate_ - Data de referência do histórico.
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