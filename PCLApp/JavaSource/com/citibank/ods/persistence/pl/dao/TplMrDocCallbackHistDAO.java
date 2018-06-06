package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.entity.pl.TplMrDocCallbackHistEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados do hist�rico de Telefones de
 * Confirma��o da Mem�ria de Risco.
 */
public interface TplMrDocCallbackHistDAO extends BaseTplMrDocCallbackDAO
{
  /**
   * Recupera a lista com os contatos associados a MR Documento Privado.
   * 
   * @param mrDocPrvt_ - C�digo da MR Documento Privado.
   * @param prodAcctCode_ - C�digo da Conta Produto.
   * @param ProdUnderAcctCode_ - C�digo da Sub-conta Produto.
   * @param mrDocRefDate_ - Data de refer�ncia do hist�rico.
   * 
   * 
   * @return List - Lista com os contatos associados a MR Documento Privado.
   */
  public ArrayList findAssociatedContactCustByPK( BigInteger mrDocPrvt_,
                                                 BigInteger prodAcctCode_,
                                                 BigInteger ProdUnderAcctCode_,
                                                 Date mrDocRefDate_ );

  /**
   * Insere dados na tabela de hist�rico.
   * 
   * @param mrDocCallbackHistEntity_ - Entidade de com os dados a serem
   *          inseridos.
   */
  public void insert( TplMrDocCallbackHistEntity mrDocCallbackHistEntity_ );
}