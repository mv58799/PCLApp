package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * Classe que instancia os valores correspondente a um registro da tabela :
 * BaseTplIpDocPrvt
 * @author Hamilton Matos
 * @date 11/04/2007
 */

public class BaseTplIpDocPrvtEntityVO extends BaseEntityVO
{

  /**
   * Numero do Cliente no CMS (Customer Number)
   */
  private BigInteger m_custNbr;

  /**
   * Codigo do Documento IP
   */
  private BigInteger m_ipDocCode;

  /**
   * Descricao do Instrucao Permanente
   */
  private String m_ipDocText;

  /**
   * "Indicador de Utilizacao de Conta CCI Constraint: 'S' (Sim), 'N' (Nao)"
   */
  private String m_ipInvstCurAcctInd;

  /**
   * Data e Hora da Ultima Atualizacao Efetuada pelo Usuario.
   */
  private Date m_lastUpdDate;

  /**
   * Codigo do Usuario que Efetuou a Ultima Atualizacao no Registro.
   */
  private String m_lastUpdUserId;

  /**
   * Retorna atributo custNbr
   * @author Hamilton Matos
   * @date 11/04/2007
   */
  public BigInteger getCustNbr()
  {
    return m_custNbr;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_custNbr
   * @param custNbr
   */
  public void setCustNbr( BigInteger custNbr_ )
  {
    m_custNbr = custNbr_;
  }

  /**
   * Retorna atributo ipDocCode
   * @author Hamilton Matos
   * @date 11/04/2007
   */
  public BigInteger getIpDocCode()
  {
    return m_ipDocCode;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_ipDocCode
   * @param ipDocCode
   */
  public void setIpDocCode( BigInteger ipDocCode_ )
  {
    m_ipDocCode = ipDocCode_;
  }

  /**
   * Retorna atributo ipDocText
   * @author Hamilton Matos
   * @date 11/04/2007
   */
  public String getIpDocText()
  {
    return m_ipDocText;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_ipDocText
   * @param ipDocText
   */
  public void setIpDocText( String ipDocText_ )
  {
    m_ipDocText = ipDocText_;
  }

  /**
   * Retorna atributo ipInvstCurAcctInd
   * @author Hamilton Matos
   * @date 11/04/2007
   */
  public String getIpInvstCurAcctInd()
  {
    return m_ipInvstCurAcctInd;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_ipInvstCurAcctInd
   * @param ipInvstCurAcctInd
   */
  public void setIpInvstCurAcctInd( String ipInvstCurAcctInd_ )
  {
    m_ipInvstCurAcctInd = ipInvstCurAcctInd_;
  }

  /**
   * Retorna atributo lastUpdDate
   * @author Hamilton Matos
   * @date 11/04/2007
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
   * @author Hamilton Matos
   * @date 11/04/2007
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
}