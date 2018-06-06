package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.entity.pl.BaseTplMrDocCallbackEntity;
import com.citibank.ods.entity.pl.TplMrDocCallbackMovEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de movimento de Telefones de
 * Confirmação da Memória de Risco.
 */
public interface TplMrDocCallbackMovDAO extends BaseTplMrDocCallbackDAO
{
  /**
   * Insere dados na tabela de movimento de relacionamento entre Memória de
   * Risco e Contatos.
   * 
   * @param mrDocCallbackMovEntity_ - Entidade com os dados a serem inseridos.
   */
  public void insert( TplMrDocCallbackMovEntity mrDocCallbackMovEntity_ );

  /**
   * Recupera a lista com os contatos em movimento associados a MR Documento
   * Privado.
   * 
   * @param mrDocPrvt_ - Código da MR Documento Privado.
   * @param prodAcctCode_ - Código da Conta Produto.
   * @param ProdUnderAcctCode_ - Código da Sub-conta Produto.
   * 
   * @return List - Lista com os contatos em movimento associados a MR Documento
   *         Privado.
   */
  public ArrayList findAssociatedContactCustByPK( BigInteger mrDocPrvt_,
                                                 BigInteger prodAcctCode_,
                                                 BigInteger ProdUnderAcctCode_ );

  /**
   * Deleta registro da tabela de movimento de relacionamento entre Memo de
   * Risco e Contatos.
   * 
   * @param mrDocCallbackMovEntity_ - Entidade com as chaves do registro a ser
   *          deletado.
   */
  public void delete( TplMrDocCallbackMovEntity mrDocCallbackMovEntity_ );

  /**
   * Verifica se Callback existe em movimento.
   * 
   * @param mrDocCallbackMovEntity_ - Entidade com Callback a ser verificado.
   * @return true se Callback existe em movimento, false caso contrário.
   */
  public boolean existsMovement(
                                TplMrDocCallbackMovEntity mrDocCallbackMovEntity_ );

  /**
   * Deleta todos registro relacionado a uma MR.
   * 
   * @param mrDocCallbackMovEntity_ - Entidade com as chaves da MR relacionada
   *          aos registros a serem deletados
   */
  public void deleteAll( TplMrDocCallbackMovEntity mrDocCallbackMovEntity_ );

  /**
   * Recupera lista com os contatos que foram adicionados (opernCode_ = "I") ou
   * removidos (opernCode_ = "E").
   * 
   * @param mrDocPrvt_ - Código da MR Documento Privado.
   * @param prodAcctCode_ - Código da Conta Produto.
   * @param ProdUnderAcctCode_ - Código da Sub-conta Produto.
   * @param opernCode_ - Tipo de operação realizada.
   * @return ArrayList - Lista com os contatos que foram adicionados (opernCode_ =
   *         "I") ou removidos (opernCode_ = "E").
   */
  public ArrayList findContactCustByPK( BigInteger mrDocPrvt_,
                                       BigInteger prodAcctCode_,
                                       BigInteger prodUnderAcctCode );

  /**
   * Copia dados de Mov para Current.
   * 
   * @param baseTplMrDocCallbackEntity_ - Entidade com os dados de Mov
   * @param lastAuthDate - Data de aprovação.
   * @param lastAuthUserId - Usuário de aprovação.
   */
  public void copyFromMovToCurrent(
                                   BaseTplMrDocCallbackEntity baseTplMrDocCallbackEntity_,
                                   Date lastAuthDate, String lastAuthUserId );

  /**
   * Recupera o opernCode dos contatos adicionados ao memo de risco
   * 
   * @param mrDocPrvt_ - Código da MR Documento Privado.
   * @param prodAcctCode_ - Código da Conta Produto.
   * @param ProdUnderAcctCode_ - Código da Sub-conta Produto.
   * @param opernCode_ - Tipo de operação realizada.
   * @return ArrayList - Lista com os contatos que foram adicionados (opernCode_ =
   *         "I") ou removidos (opernCode_ = "E").
   */
  public  TplMrDocCallbackMovEntity findCallBack(  TplMrDocCallbackMovEntity tplMrDocCallbackMovEntity_ );
  
  /**
   * Atualiza dados na tabela de movimento de relacionamento entre Memória de
   * Risco e Contatos.
   * 
   * @param mrDocCallbackMovEntity_ - Entidade com os dados a serem atualizados.
   */
  public void update( TplMrDocCallbackMovEntity mrDocCallbackMovEntity_ );
}