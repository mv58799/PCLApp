package com.citibank.ods.common.exception;
//
//�2002-2007 Accenture. All rights reserved. 
//
/**
 * Exce��o lan�ada pelas classes de acesso a objetos (DAO) quando o m�todo
 * find n�o localiza o registro que foi procurado.
 * 
 *@see package com.citibank.ods.common.exception; 
 *@version 1.0
 *@author fabio.luppi.gil,Mar 15, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class NoRowsReturnedException extends UnexpectedException
{
  public NoRowsReturnedException()
  {
    super( "No rows returned." );
  }
}
