package com.citibank.ods.common.exception;

/**
 * 
 * RuntimeException utilizada pela aplica��o para reportar exce��es inesperadas
 * pela aplica��o.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class UnexpectedException extends RuntimeException
{

  /**
   * @param code_ - c�digo de erro da exce��o.
   * @param message_ - mensagem informada pela exce��o.
   */
  public UnexpectedException( int code_, String message_ )
  {
    super( "[" + code_ + "] " + message_ );
  }

  /**
   * @param message_ - mensagem informada pela exce��o.
   */
  public UnexpectedException( String message_ )
  {
    super( message_ );
  }

  /**
   * @param code_ - c�digo de erro da exce��o.
   * @param message_ - mensagem informada pela exce��o.
   * @param rootCause_ - exce��o que originou esta exce��o.
   */
  public UnexpectedException( int code_, String message_, Throwable rootCause_ )
  {
    super( rootCause_.getMessage() + " - [" + code_ + "] " + message_ + " - " + rootCause_.getClass().getName(), rootCause_ );
  }

  /**
   * @param message_ - mensagem informada pela exce��o.
   * @param rootCause_ - exce��o que originou esta exce��o.
   */
  public UnexpectedException( String message_, Throwable rootCause_ )
  {
    super( message_ + " - " + rootCause_.getClass().getName(), rootCause_ );
  }
}