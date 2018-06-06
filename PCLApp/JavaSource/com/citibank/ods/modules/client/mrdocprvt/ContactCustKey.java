package com.citibank.ods.modules.client.mrdocprvt;

import java.math.BigInteger;

/**
 * @author m.nakamura
 * 
 * Objeto que representa a chave do Contato do Cliente.
 */
public class ContactCustKey
{
  BigInteger ctcNbr;

  BigInteger custNbr;

  /**
   * Cria novo objeto ContactCust com os valores de número do cliente e número
   * do contato a serem usados como chave.
   * 
   * @param custNbr_ - O número do cliente.
   * @param ctcNbr_ - O número do contato.
   */
  public ContactCustKey( BigInteger custNbr_, BigInteger ctcNbr_ )
  {
    custNbr = custNbr_;
    ctcNbr = ctcNbr_;
  }

  /**
   * Recupera o número do cliente.
   * 
   * @return BigInteger - Número do cliente.
   */
  public BigInteger getCustNbr()
  {
    return custNbr;
  }

  /**
   * Recupera o número do contato.
   * 
   * @return BigInteger - Número do contato.
   */
  public BigInteger getCtcNbr()
  {
    return ctcNbr;
  }

  /**
   * Sobrescreve método equals da classe Object. Verifica se os dois valores da
   * chave (número do cliente e número do contato) são numericamente iguais nos
   * dois objetos.
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  public boolean equals( Object obj_ )
  {
    ContactCustKey key = ( ContactCustKey ) obj_;

    if ( ctcNbr == null || custNbr == null || key.getCtcNbr() == null
         || key.getCustNbr() == null )
    {
      return false;
    }

    if ( ctcNbr.equals( key.getCtcNbr() ) && custNbr.equals( key.getCustNbr() ) )
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  /**
   * @see java.lang.Object#hashCode()
   */
  public int hashCode() {
    int result = 17;
    result = 37 * result + ctcNbr.intValue();
    result = 37 * result + custNbr.intValue();
    return result;
  }
}