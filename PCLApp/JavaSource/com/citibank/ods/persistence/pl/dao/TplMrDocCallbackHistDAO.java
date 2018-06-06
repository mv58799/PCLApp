package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.entity.pl.TplMrDocCallbackHistEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados do histórico de Telefones de
 * Confirmação da Memória de Risco.
 */
public interface TplMrDocCallbackHistDAO extends BaseTplMrDocCallbackDAO
{
  /**
   * Recupera a lista com os contatos associados a MR Documento Privado.
   * 
   * @param mrDocPrvt_ - Código da MR Documento Privado.
   * @param prodAcctCode_ - Código da Conta Produto.
   * @param ProdUnderAcctCode_ - Código da Sub-conta Produto.
   * @param mrDocRefDate_ - Data de referência do histórico.
   * 
   * 
   * @return List - Lista com os contatos associados a MR Documento Privado.
   */
  public ArrayList findAssociatedContactCustByPK( BigInteger mrDocPrvt_,
                                                 BigInteger prodAcctCode_,
                                                 BigInteger ProdUnderAcctCode_,
                                                 Date mrDocRefDate_ );

  /**
   * Insere dados na tabela de histórico.
   * 
   * @param mrDocCallbackHistEntity_ - Entidade de com os dados a serem
   *          inseridos.
   */
  public void insert( TplMrDocCallbackHistEntity mrDocCallbackHistEntity_ );
}