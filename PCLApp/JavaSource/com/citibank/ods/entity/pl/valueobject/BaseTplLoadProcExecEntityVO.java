package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author m.nakamura
 * 
 * Representação da tabela de Execução de Processos de Carga.
 */
public class BaseTplLoadProcExecEntityVO extends BaseEntityVO
{

  // Data de termino da execucao
  private Date m_execEndDate = null;

  // Quantidade de segundos da duracao de uma determinada execucao batch.
  private BigInteger m_execPerSecQty = null;

  // Data de referencia de execucao
  private Date m_execRefDate = null;

  // Numero de Sequencia / Referencial da execucao
  private BigInteger m_execSeqNbr = null;

  // Quantidade total de registros inseridos na execucao de determinado Job.
  private BigInteger m_inpRecQty = null;

  // Data e hora da ultima atualizacao efetuada pelo usuario.
  private Date m_lastUpdDate = null;

  // Codigo do usuario (SOE ID) que efetuou a ultima atualizacao no registro.
  private String m_lastUpdUserId = "";

  // Codigo da situacao da execucao da carga
  private BigInteger m_loadExecSitCode = null;

  // Numero do processo de carga: Codigo do batch que sera executado em
  // umadeterminada carga.
  private BigInteger m_loadProcNbr = null;

  // Nome do batch que sera executado em uma determinada carga.
  private String m_loadProcText = "";

  // Indicador de Execucao Manual
  private String m_mnlExecInd = "";

  // Quantidade total de registros lidos na execucao de determinado Job.
  private BigInteger m_readRecQty = null;

  // Quantidade total de registros rejeitados na execucao de determinado Job.
  private BigInteger m_rejcRecQty = null;

  // Quantidade total de registros atualizados na execucao de determinado Job.
  private BigInteger m_updRecQty = null;

  /**
   * Recupera data de término da execução.
   * 
   * @return Retorna a data de término da execução.
   */
  public Date getExecEndDate()
  {
    return m_execEndDate;
  }

  /**
   * Seta data de término da execução.
   * 
   * @param execEndDate_ - A data de término da execução a ser setada.
   */
  public void setExecEndDate( Date execEndDate_ )
  {
    m_execEndDate = execEndDate_;
  }

  /**
   * Recupera a quantidade de segundos da duracao de uma determinada execucao
   * batch.
   * 
   * @return Retorna a quantidade de segundos da duracao de uma determinada
   *         execucao batch.
   */
  public BigInteger getExecPerSecQty()
  {
    return m_execPerSecQty;
  }

  /**
   * Seta a quantidade de segundos da duracao de uma determinada execucao batch.
   * 
   * @param execPerSecQty_ - A quantidade de segundos da duracao de uma
   *          determinada execucao batch a ser setado.
   */
  public void setExecPerSecQty( BigInteger execPerSecQty_ )
  {
    m_execPerSecQty = execPerSecQty_;
  }

  /**
   * Recupera a data de referencia de execucao.
   * 
   * @return Retorna a data de referencia de execucao.
   */
  public Date getExecRefDate()
  {
    return m_execRefDate;
  }

  /**
   * Seta a data de referencia de execucao.
   * 
   * @param execRefDate_ - A data de referencia de execucao a ser setado.
   */
  public void setExecRefDate( Date execRefDate_ )
  {
    m_execRefDate = execRefDate_;
  }

  /**
   * Recupera o Numero de Sequencia / Referencial da execucao.
   * 
   * @return Retorna o Numero de Sequencia / Referencial da execucao.
   */
  public BigInteger getExecSeqNbr()
  {
    return m_execSeqNbr;
  }

  /**
   * Seta o Numero de Sequencia / Referencial da execucao.
   * 
   * @param execSeqNbr_ - O Numero de Sequencia / Referencial da execucao a ser
   *          setado.
   */
  public void setExecSeqNbr( BigInteger execSeqNbr_ )
  {
    m_execSeqNbr = execSeqNbr_;
  }

  /**
   * Recupera a quantidade total de registros inseridos na execucao de
   * determinado Job.
   * 
   * @return Retorna a quantidade total de registros inseridos na execucao de
   *         determinado Job.
   */
  public BigInteger getInpRecQty()
  {
    return m_inpRecQty;
  }

  /**
   * Seta a quantidade total de registros inseridos na execucao de determinado
   * Job.
   * 
   * @param inpRecQty_ - A quantidade total de registros inseridos na execucao
   *          de determinado Job a ser setado.
   */
  public void setInpRecQty( BigInteger inpRecQty_ )
  {
    m_inpRecQty = inpRecQty_;
  }

  /**
   * Recupera a data e hora da ultima atualizacao efetuada pelo usuario.
   * 
   * @return Retorna a data e hora da ultima atualizacao efetuada pelo usuario.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * Seta a data e hora da ultima atualizacao efetuada pelo usuario.
   * 
   * @param lastUpdDate_ - A data e hora da ultima atualizacao efetuada pelo
   *          usuario a ser setada.
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * Recupera o codigo do usuario (SOE ID) que efetuou a ultima atualizacao no
   * registro.
   * 
   * @return Retorna o codigo do usuario (SOE ID) que efetuou a ultima
   *         atualizacao no registro.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * Seta o codigo do usuario (SOE ID) que efetuou a ultima atualizacao no
   * registro.
   * 
   * @param lastUpdUserId_ - O codigo do usuario (SOE ID) que efetuou a ultima
   *          atualizacao no registro a ser setado.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * Recupera o codigo da situacao da execucao da carga.
   * 
   * @return Retorna o codigo da situacao da execucao da carga.
   */
  public BigInteger getLoadExecSitCode()
  {
    return m_loadExecSitCode;
  }

  /**
   * Seta o codigo da situacao da execucao da carga.
   * 
   * @param loadExecSitCode_ - O codigo da situacao da execucao da carga a ser
   *          setado.
   */
  public void setLoadExecSitCode( BigInteger loadExecSitCode_ )
  {
    m_loadExecSitCode = loadExecSitCode_;
  }

  /**
   * Recupera o numero do processo de carga: Codigo do batch que sera executado
   * em uma determinada carga.
   * 
   * @return Retorna o numero do processo de carga: Codigo do batch que sera
   *         executado em uma determinada carga.
   */
  public BigInteger getLoadProcNbr()
  {
    return m_loadProcNbr;
  }

  /**
   * Seta o numero do processo de carga: Codigo do batch que sera executado em
   * uma determinada carga.
   * 
   * @param loadProcNbr_ - O numero do processo de carga: Codigo do batch que
   *          sera executado em uma determinada carga a ser setado.
   */
  public void setLoadProcNbr( BigInteger loadProcNbr_ )
  {
    m_loadProcNbr = loadProcNbr_;
  }

  /**
   * Recupera o Indicador de Execucao Manual.
   * 
   * @return Retorna o Indicador de Execucao Manual.
   */
  public String getMnlExecInd()
  {
    return m_mnlExecInd;
  }

  /**
   * Seta o Indicador de Execucao Manual.
   * 
   * @param mnlExecInd_ - O Indicador de Execucao Manual a ser setado.
   */
  public void setMnlExecInd( String mnlExecInd_ )
  {
    m_mnlExecInd = mnlExecInd_;
  }

  /**
   * Recupera a quantidade total de registros lidos na execucao de determinado
   * Job.
   * 
   * @return Retona a quantidade total de registros lidos na execucao de
   *         determinado Job.
   */
  public BigInteger getReadRecQty()
  {
    return m_readRecQty;
  }

  /**
   * Seta a quantidade total de registros lidos na execucao de determinado Job.
   * 
   * @param readRecQty_ - A quantidade total de registros lidos na execucao de
   *          determinado Job a ser setada.
   */
  public void setReadRecQty( BigInteger readRecQty_ )
  {
    m_readRecQty = readRecQty_;
  }

  /**
   * Recupera a quantidade total de registros rejeitados na execucao de
   * determinado Job.
   * 
   * @return Retorna a quantidade total de registros rejeitados na execucao de
   *         determinado Job.
   */
  public BigInteger getRejcRecQty()
  {
    return m_rejcRecQty;
  }

  /**
   * Seta a quantidade total de registros rejeitados na execucao de determinado
   * Job.
   * 
   * @param rejcRecQty_ - A quantidade total de registros rejeitados na execucao
   *          de determinado Job a ser setada.
   */
  public void setRejcRecQty( BigInteger rejcRecQty_ )
  {
    m_rejcRecQty = rejcRecQty_;
  }

  /**
   * Recupera a quantidade total de registros atualizados na execucao de
   * determinado Job.
   * 
   * @return Retorna a quantidade total de registros atualizados na execucao de
   *         determinado Job.
   */
  public BigInteger getUpdRecQty()
  {
    return m_updRecQty;
  }

  /**
   * Seta a quantidade total de registros atualizados na execucao de determinado
   * Job.
   * 
   * @param updRecQty_ - A quantidade total de registros atualizados na execucao
   *          de determinado Job a ser setada.
   */
  public void setUpdRecQty( BigInteger updRecQty_ )
  {
    m_updRecQty = updRecQty_;
  }

  /**
   * Recupera o nome do batch que será executado em uma determinada carga.
   * 
   * @return Retorna o nome do batch que será executado em uma determinada
   *         carga.
   */
  public String getLoadProcText()
  {
    return m_loadProcText;
  }

  /**
   * Seta o nome do batch que será executado em uma determinada carga.
   * 
   * @param loadProcText_ - O nome do batch que será executado em uma
   *          determinada carga a ser setado.
   */
  public void setLoadProcText( String loadProcText_ )
  {
    m_loadProcText = loadProcText_;
  }
}