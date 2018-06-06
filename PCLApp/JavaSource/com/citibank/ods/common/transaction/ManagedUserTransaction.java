package com.citibank.ods.common.transaction;

import javax.transaction.UserTransaction;

import com.citibank.ods.common.BaseObject;
import com.citibank.ods.common.exception.UnexpectedException;

/**
 * 
 * Encapsula o UserTransaction que será utilizado pela Action para efetuar o
 * controle de Transações.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public abstract class ManagedUserTransaction extends BaseObject
{
  /**
   * m_userTransaction - UserTransaction encapsulado.
   */
  private UserTransaction m_userTransaction = null;

  /**
   * @param userTransaction_ - UserTransaction que será encapsulado.
   */
  protected ManagedUserTransaction( UserTransaction userTransaction_ )
  {
    if ( userTransaction_ == null )
    {
      throw new UnexpectedException( "userTransaction_ cannot be null." );
    }
    m_userTransaction = userTransaction_;
  }

  /**
   * 
   * Obtém o UserTransaction encapsulado.
   * 
   * @return
   */
  protected UserTransaction getUserTransaction()
  {
    return m_userTransaction;
  }
}