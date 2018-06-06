/*
 * Created on Dec 17, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.common.conversor;

import java.math.BigDecimal;

import org.apache.struts.action.ActionMessage;

import com.citibank.ods.common.constants.MessagesConstants;

/**
 * @author User
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TypeConversor
{
  /**
   * Converte uma String em um BigDecimal. Caso a String nao seja valida para
   * transforma-la em um BigDecimal o retorno do metodo sera null.
   * @param value_ String que representa o numero a ser convertido
   * @return BigDecimal convertido. Null se a String nao representar um numero
   *         valido.
   */
  public static BigDecimal toBigDecimal( String value_ )
  {
    BigDecimal value = null;

    if ( value_ != null )
    {
      try
      {
        value = new BigDecimal( value_ );
      }
      catch ( NumberFormatException e_ )
      {
        //to-do logar erro
      }
    }
    return value;
  }

  /**
   * Verifica se o conteudo de uma String representa um BigDecimal.
   * @param value_ String que sera verificada.
   * @param fieldName_ nome do campo preenchido com o valor a ser verificado
   * @return null, se a String representar um BigDecimal; ActionMessage
   *         indicando que o campo nao foi preenchido, se for este o caso;
   *         ActionMessage indicando que o campo foi preenchido com um valor
   *         invalido para ser um BigDecimal, se este for o caso.
   */
  public static ActionMessage validateBigDecimal( String value_,
                                                 String fieldName_ )
  {
    ActionMessage errorMessage = null;
    if ( value_ == null )
    {
      errorMessage = new ActionMessage(
                                        MessagesConstants.C_GENERIC_FIELD_NUMERIC_NULL,
                                        fieldName_ );
    }
    else
    {
      try
      {
        new BigDecimal( value_ );
      }
      catch ( NumberFormatException e_ )
      {
        errorMessage = new ActionMessage(
                                          MessagesConstants.C_GENERIC_FIELD_NUMERIC_INVALID,
                                          fieldName_ );
      }
    }
    return errorMessage;
  }
}