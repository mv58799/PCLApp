package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author m.nakamura
 * 
 * Representação da tabela de domínios de crítica lógica
 */
public class BaseTplLogicCritDomainEntityVO extends BaseEntityVO
{
  // Conteudo do dominio
  private String m_domainCntntText = "";

  // Numero sequencial / Referencial para o dominio
  private BigInteger m_domainSeqNbr = null;

  // Indica se havera necessidade de alteracoes no ETL (programa de carga)
  private String m_loadProgUpdInd = "";

  // Codigo da critica logica da carga.
  private BigInteger m_logicCritCode = null;

  // Codigo do Status do registro.
  private String m_recStatCode = "";

  /**
   * Recupera conteúdo do domínio.
   * 
   * @return Retorna conteúdo do domínio.
   */
  public String getDomainCntntText()
  {
    return m_domainCntntText;
  }

  /**
   * Seta conteúdo do domínio.
   * 
   * @param domainCntntText_ - Conteúdo do domínio.
   */
  public void setDomainCntntText( String domainCntntText_ )
  {
    m_domainCntntText = domainCntntText_;
  }

  /**
   * Recupera o número do domínio.
   * 
   * @return Retorna o número do domínio.
   */
  public BigInteger getDomainSeqNbr()
  {
    return m_domainSeqNbr;
  }

  /**
   * Seta o número do domínio.
   * 
   * @param domainSeqNbr_ - Número do domínio.
   */
  public void setDomainSeqNbr( BigInteger domainSeqNbr_ )
  {
    m_domainSeqNbr = domainSeqNbr_;
  }

  /**
   * Recupera o indicador de necessidade de alteracoes no ETL (programa de
   * carga)
   * 
   * @return Retorna o indicador de necessidade de alteracoes no ETL (programa
   *         de carga)
   */
  public String getLoadProgUpdInd()
  {
    return m_loadProgUpdInd;
  }

  /**
   * Seta o indicador de necessidade de alteracoes no ETL (programa de carga)
   * 
   * @param loadProgUpdInd_ - Indicador de necessidade de alteracoes no ETL
   *          (programa de carga)
   */
  public void setLoadProgUpdInd( String loadProgUpdInd_ )
  {
    m_loadProgUpdInd = loadProgUpdInd_;
  }

  /**
   * Recupera o codigo da critica logica da carga.
   * 
   * @return Retorna o codigo da critica logica da carga.
   */
  public BigInteger getLogicCritCode()
  {
    return m_logicCritCode;
  }

  /**
   * Seta o codigo da critica logica da carga.
   * 
   * @param logicCritCode_ - Codigo da critica logica da carga.
   */
  public void setLogicCritCode( BigInteger logicCritCode_ )
  {
    m_logicCritCode = logicCritCode_;
  }

  /**
   * Recupera o Codigo do Status do registro.
   * 
   * @return Retorna o Codigo do Status do registro.
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * Seta o Codigo do Status do registro.
   * 
   * @param recStatCode_ - Codigo do Status do registro.
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }
}