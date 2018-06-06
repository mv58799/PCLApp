package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author m.nakamura
 * 
 * Representa��o da tabela de Processos de Carga.
 */
public class BaseTplLoadProcessEntityVO extends BaseEntityVO
{

  // Codigo da interface de entrada
  private BigInteger m_entryInterCode = null;

  // Tipo da interface de entrada
  private BigInteger m_entryTypeCode = null;

  // Descri��o da interface de entrada
  private String m_entryInterText = "";

  // Indicador de Obrigatoriedade de execucao.
  private String m_execObligInd = "";

  // Data de Referencia de execucao do processo de carga.
  private Date m_execRefDate = null;

  // Data da ultima execucao do processo de carga.
  private Date m_lastExecDate = null;

  // Codigo da periodicidade da Carga.
  private BigInteger m_loadPrdctyCode = null;

  // Numero do processo de carga: Codigo do batch que sera executado em
  // umadeterminada carga.
  private BigInteger m_loadProcNbr = null;

  // Nome do batch que sera executado em uma determinada carga.
  private String m_loadProcText = "";

  // Indicador de bloqueio do processo nos casos de erro.
  private String m_procBlockInd = "";

  // Codigo do Sistema
  private String m_sysCode = "";

  // Segmento do sistema
  private String m_sysSegCode = "";

  /**
   * Recupera o c�digo da interface de entrada.
   * 
   * @return Retorna o c�digo da interface de entrada.
   */
  public BigInteger getEntryInterCode()
  {
    return m_entryInterCode;
  }

  /**
   * Seta o c�digo da interface de entrada.
   * 
   * @param entryInterCode_ - O c�digo da interface de entrada a ser setado.
   */
  public void setEntryInterCode( BigInteger entryInterCode_ )
  {
    m_entryInterCode = entryInterCode_;
  }

  /**
   * Recupera o tipo da interface de entrada.
   * 
   * @return Retorna o tipo da interface de entrada.
   */
  public BigInteger getEntryTypeCode()
  {
    return m_entryTypeCode;
  }

  /**
   * Seta o c�digo da interface de entrada.
   * 
   * @param entryTypeCode_ - O tipo da interface de entrada a ser setado.
   */
  public void setEntryTypeCode( BigInteger entryTypeCode_ )
  {
    m_entryTypeCode = entryTypeCode_;
  }

  /**
   * Recupera indicador de obrigatoriedade de execu��o.
   * 
   * @return Retorna o indicador de obrigatoriedade de execu��o.
   */
  public String getExecObligInd()
  {
    return m_execObligInd;
  }

  /**
   * Seta o indicador de obrigatoriedade de execu��o.
   * 
   * @param execObligInd_ - O indicador de obrigatoriedade de execu��o.
   */
  public void setExecObligInd( String execObligInd_ )
  {
    m_execObligInd = execObligInd_;
  }

  /**
   * Recupera a data de refer�ncia de execu��o do processo de carga.
   * 
   * @return Recupera a data de refer�ncia de execu��o do processo de carga.
   */
  public Date getExecRefDate()
  {
    return m_execRefDate;
  }

  /**
   * Seta a data de refer�ncia de execu��o do processo de carga.
   * 
   * @param execRefDate_ - A data de refer�ncia de execu��o do processo de carga
   *          a ser setado.
   */
  public void setExecRefDate( Date execRefDate_ )
  {
    m_execRefDate = execRefDate_;
  }

  /**
   * Recupera a data da ultima execucao do processo de carga.
   * 
   * @return Retorna a data da ultima execucao do processo de carga.
   */
  public Date getLastExecDate()
  {
    return m_lastExecDate;
  }

  /**
   * Seta a data da ultima execucao do processo de carga.
   * 
   * @param lastExecDate_ - A data da ultima execucao do processo de carga a ser
   *          setado.
   */
  public void setLastExecDate( Date lastExecDate_ )
  {
    m_lastExecDate = lastExecDate_;
  }

  /**
   * Recupera o c�digo da periodicidade da Carga.
   * 
   * @return Retorna o c�digo da periodicidade da Carga.
   */
  public BigInteger getLoadPrdctyCode()
  {
    return m_loadPrdctyCode;
  }

  /**
   * Seta o c�digo da periodicidade da Carga.
   * 
   * @param loadPrdctyCode_ - O c�digo da periodicidade da Carga a ser setado.
   */
  public void setLoadPrdctyCode( BigInteger loadPrdctyCode_ )
  {
    m_loadPrdctyCode = loadPrdctyCode_;
  }

  /**
   * Recupera o n�mero do processo de carga.
   * 
   * @return Retorna o n�mero do processo de carga.
   */
  public BigInteger getLoadProcNbr()
  {
    return m_loadProcNbr;
  }

  /**
   * Seta o n�mero do processo de carga.
   * 
   * @param loadProcNbr_ - O n�mero do processo de carga a ser setado.
   */
  public void setLoadProcNbr( BigInteger loadProcNbr_ )
  {
    m_loadProcNbr = loadProcNbr_;
  }

  /**
   * Recupera o nome do batch que ser� executado em uma determinada carga.
   * 
   * @return Retorna o nome do batch que ser� executado em uma determinada
   *         carga.
   */
  public String getLoadProcText()
  {
    return m_loadProcText;
  }

  /**
   * Seta o nome do batch que ser� executado em uma determinada carga.
   * 
   * @param loadProcText_ - O nome do batch que ser� executado em uma
   *          determinada carga a ser setado.
   */
  public void setLoadProcText( String loadProcText_ )
  {
    m_loadProcText = loadProcText_;
  }

  /**
   * Recupera o indicador de bloqueio do processo nos casos de erro.
   * 
   * @return Retorna o indicador de bloqueio do processo nos casos de erro.
   */
  public String getProcBlockInd()
  {
    return m_procBlockInd;
  }

  /**
   * Seta o indicador de bloqueio do processo nos casos de erro.
   * 
   * @param procBlockInd_ - O indicador de bloqueio do processo nos casos de
   *          erro a ser setado.
   */
  public void setProcBlockInd( String procBlockInd_ )
  {
    m_procBlockInd = procBlockInd_;
  }

  /**
   * Recupera o c�digo do sistema.
   * 
   * @return Retorna o c�digo do sistema.
   */
  public String getSysCode()
  {
    return m_sysCode;
  }

  /**
   * Seta o c�digo do sistema.
   * 
   * @param sysCode_ - O c�digo do sistema a ser setado.
   */
  public void setSysCode( String sysCode_ )
  {
    m_sysCode = sysCode_;
  }

  /**
   * Recupera o segmento do sistema.
   * 
   * @return Retorna o segmento do sistema.
   */
  public String getSysSegCode()
  {
    return m_sysSegCode;
  }

  /**
   * Seta o segmento do sistema.
   * 
   * @param sysSegCode_ - O segmento do sistema a ser setado.
   */
  public void setSysSegCode( String sysSegCode_ )
  {
    m_sysSegCode = sysSegCode_;
  }

  /**
   * Recupera a descri��o da interface de entrada.
   * 
   * @return Retorna a descri��o da interface de entrada.
   */
  public String getEntryInterText()
  {
    return m_entryInterText;
  }

  /**
   * Seta a descri��o da interface de entrada
   * 
   * @param entryInterText_ - A descri��o da interface de entrada a ser setado.
   */
  public void setEntryInterText( String entryInterText_ )
  {
    m_entryInterText = entryInterText_;
  }
}