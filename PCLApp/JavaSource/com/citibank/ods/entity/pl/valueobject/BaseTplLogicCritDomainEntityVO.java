package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author m.nakamura
 * 
 * Representa��o da tabela de dom�nios de cr�tica l�gica
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
   * Recupera conte�do do dom�nio.
   * 
   * @return Retorna conte�do do dom�nio.
   */
  public String getDomainCntntText()
  {
    return m_domainCntntText;
  }

  /**
   * Seta conte�do do dom�nio.
   * 
   * @param domainCntntText_ - Conte�do do dom�nio.
   */
  public void setDomainCntntText( String domainCntntText_ )
  {
    m_domainCntntText = domainCntntText_;
  }

  /**
   * Recupera o n�mero do dom�nio.
   * 
   * @return Retorna o n�mero do dom�nio.
   */
  public BigInteger getDomainSeqNbr()
  {
    return m_domainSeqNbr;
  }

  /**
   * Seta o n�mero do dom�nio.
   * 
   * @param domainSeqNbr_ - N�mero do dom�nio.
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