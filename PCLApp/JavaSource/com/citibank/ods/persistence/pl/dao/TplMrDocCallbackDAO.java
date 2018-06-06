package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.entity.pl.BaseTplMrDocCallbackEntity;
import com.citibank.ods.entity.pl.TplMrDocCallbackEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de Telefones de Confirmação da
 * Memória de Risco.
 */
public interface TplMrDocCallbackDAO extends BaseTplMrDocCallbackDAO
{
  /**
   * Recupera a lista com os contatos associados a MR Documento Privado.
   * 
   * @param mrDocPrvt_ - Código da MR Documento Privado.
   * @param prodAcctCode_ - Código da Conta Produto.
   * @param ProdUnderAcctCode_ - Código da Sub-conta Produto.
   * 
   * @return List - Lista com os contatos associados a MR Documento Privado.
   */
  public ArrayList findAssociatedContactCustByPK( BigInteger mrDocPrvt_,
                                                 BigInteger prodAcctCode_,
                                                 BigInteger ProdUnderAcctCode_ );

  /**
   * Copia registro de Current para Hist.
   * 
   * @param baseTplMrDocCallbackEntity_ - Entidade com as chaves do registro a
   *          ser copiado.
   * @param mrDocRefDate_ - Data de referência do histórico.
   */
  public void copyFromCurrentToHist(
                                    BaseTplMrDocCallbackEntity baseTplMrDocCallbackEntity_,
                                    Date mrDocRefDate_ );

  /**
   * Atualiza registro da tabela Current de Callback.
   * 
   * @param mrDocCallbackEntity_ - Entidade com os dados a serem atualizados.
   */
  public void update( TplMrDocCallbackEntity mrDocCallbackEntity_ );

  /**
   * Insere dados na tabela Current de Callback.
   * 
   * @param mrDocCallbackEntity_ - Entidade com os dados a serem inseridos.
   */
  public void insert( TplMrDocCallbackEntity mrDocCallbackEntity_ );
  
  /**
   * Método que retorna a sequence do call back na tela de memo de risco.
   */
  public Integer getNextMrCallBackCode();
  
  public boolean exists( TplMrDocCallbackEntity tplMrDocCallbackEntity_ );
  

  public void inativateAllMrDocCallBacks(
                                         TplMrDocCallbackEntity tplMrDocCallbackEntity_ );
}