package com.citibank.ods.common.exception;

/**
 * 
 * RuntimeException utilizada pela aplicação para reportar exceções inesperadas
 * pela aplicação.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class UnexpectedException extends RuntimeException
{

  /**
   * @param code_ - código de erro da exceção.
   * @param message_ - mensagem informada pela exceção.
   */
  public UnexpectedException( int code_, String message_ )
  {
    super( "[" + code_ + "] " + message_ );
  }

  /**
   * @param message_ - mensagem informada pela exceção.
   */
  public UnexpectedException( String message_ )
  {
    super( message_ );
  }

  /**
   * @param code_ - código de erro da exceção.
   * @param message_ - mensagem informada pela exceção.
   * @param rootCause_ - exceção que originou esta exceção.
   */
  public UnexpectedException( int code_, String message_, Throwable rootCause_ )
  {
    super( rootCause_.getMessage() + " - [" + code_ + "] " + message_ + " - " + rootCause_.getClass().getName(), rootCause_ );
  }

  /**
   * @param message_ - mensagem informada pela exceção.
   * @param rootCause_ - exceção que originou esta exceção.
   */
  public UnexpectedException( String message_, Throwable rootCause_ )
  {
    super( message_ + " - " + rootCause_.getClass().getName(), rootCause_ );
  }
}