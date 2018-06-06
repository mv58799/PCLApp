package com.citibank.ods.common.transaction;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.citibank.ods.common.exception.UnexpectedException;

/**
 * 
 * Classe que disponibiliza para a Action as operações de controle de transações
 * do UserTransaction
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class ActionManagedUserTransaction extends ManagedUserTransaction
{
  /**
   * @param userTransaction_ - instância de UserTransaction.
   */
  public ActionManagedUserTransaction( UserTransaction userTransaction_ )
  {
    super( userTransaction_ );
  }

  /**
   * 
   * Inicia uma Transação.
   * 
   */
  public void beginTransaction()
  {
    UserTransaction userTransaction = getUserTransaction();
    try
    {
      userTransaction.begin();
    }
    catch ( SystemException e_ )
    {
      throw new UnexpectedException( "Could not begin transaction.", e_ );
    }
    catch ( NotSupportedException e_ )
    {
      throw new UnexpectedException( "Nested Transaction not supported.", e_ );
    }
  }

  
  /**
   * 
   * Efetua o commit de uma Transação.
   * 
   */
  public void commitTransaction()
  {
    UserTransaction userTransaction = getUserTransaction();
    try
    {
      userTransaction.commit();
    }
    catch ( HeuristicMixedException e_ )
    {
      throw new UnexpectedException( "There are rolled back updates.", e_ );
    }
    catch ( SystemException e_ )
    {
      throw new UnexpectedException( "Could not commit transaction.", e_ );
    }
    catch ( RollbackException e_ )
    {
      throw new UnexpectedException(
                                     
                                     "Transaction has been marked for rollback only or the transaction has been rolled back instead of committed.", e_ );
    }
    catch ( HeuristicRollbackException e_ )
    {
      throw new UnexpectedException(
                                     
                                     "All relevant updates have been rolled back.", e_ );
    }
  }

  
  /**
   * 
   * Efetua o Rollback de uma Transação.
   * 
   */
  public void rollbackTransaction()
  {
    UserTransaction userTransaction = getUserTransaction();
    try
    {
      userTransaction.rollback();
    }
    catch ( SystemException e_ )
    {
      throw new UnexpectedException( "Could not rollback transaction.", e_ );
    }
  }

}