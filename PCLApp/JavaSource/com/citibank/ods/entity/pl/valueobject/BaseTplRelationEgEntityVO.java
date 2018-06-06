package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;
/**
 * Classe que instancia os valores correspondente a um registro da tabela :
 * BaseTplRelationEg
 * @author leonardo.nakada
 * @date 15/04/2007
 */

public class BaseTplRelationEgEntityVO extends BaseEntityVO
{

  private String m_egNbr;

  private Date m_lastUpdDate;

  private String m_lastUpdUserId;

  private BigInteger m_reltnNbr;

  private String m_erNbr;

  /**
   * Retorna atributo egNbr
   * @author leonardo.nakada
   * @date 15/04/2007
   */
  public String getEgNbr()
  {
    return m_egNbr;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_egNbr
   * @param egNbr
   */
  public void setEgNbr( String egNbr_ )
  {
    m_egNbr = egNbr_;
  }

  /**
   * Retorna atributo lastUpdDate
   * @author leonardo.nakada
   * @date 15/04/2007
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_lastUpdDate
   * @param lastUpdDate
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * Retorna atributo lastUpdUserId
   * @author leonardo.nakada
   * @date 15/04/2007
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_lastUpdUserId
   * @param lastUpdUserId
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * Retorna atributo reltnNbr
   * @author leonardo.nakada
   * @date 15/04/2007
   */
  public BigInteger getReltnNbr()
  {
    return m_reltnNbr;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_reltnNbr
   * @param reltnNbr
   */
  public void setReltnNbr( BigInteger reltnNbr_ )
  {
    m_reltnNbr = reltnNbr_;
  }

  /**
   * Retorna atributo m_erNbr
   */
  public String getErNbr()
  {
    return m_erNbr;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_erNbr
   * @param erNbr_
   */
  public void setErNbr( String erNbr_ )
  {
    m_erNbr = erNbr_;
  }
}